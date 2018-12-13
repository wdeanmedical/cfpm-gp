package com.wdeanmedical.service;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wdeanmedical.core.Core;
import com.wdeanmedical.core.ExcludedFields;
import com.wdeanmedical.core.ExcludedObjects;
import com.wdeanmedical.core.Statics;
import com.wdeanmedical.dto.MessageDTO;
import com.wdeanmedical.entity.ActivityLog;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.ClientSession;
import com.wdeanmedical.entity.Letter;
import com.wdeanmedical.entity.Message;
import com.wdeanmedical.entity.MessageMessageRecipient;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.model.MessageDetails;
import com.wdeanmedical.model.MessageRecipient;
import com.wdeanmedical.model.MessageType;
import com.wdeanmedical.model.ValidationResult;
import com.wdeanmedical.persistence.MessageDAO;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.MailHandler;
import com.wdeanmedical.util.MessageRecipientComparator;
import com.wdeanmedical.util.Util;

public class MessageService extends AppService {

  private static Log log = LogFactory.getLog(MessageService.class);

  private MessageDAO messageDAO;



  public MessageService() throws MalformedURLException {
    super();
    messageDAO = (MessageDAO) wac.getBean("messageDAO");
  }


  public String buildAppointmentMessage(MessageDTO dto) throws Exception {
    Patient patient = messageGetClientSession(dto).getPatient();
    DataEncryptor.decryptPatient(patient); 
    String message = "Appointment Request from: ";
    String patientName = Util.buildFullName(patient.getFirstName(), patient.getMiddleName(), patient.getLastName());
    message += patientName + "<br>";
    message += "Visit Reason: " + dto.visitReason + "<br>"; 
    User clinician = messageDAO.findById(User.class, dto.clinicianId);
    User altClinician = messageDAO.findById(User.class, dto.altClinicianId);
    String clinicianName = Util.buildFullName(clinician.getFirstName(), clinician.getMiddleName(), clinician.getLastName());
    message += "Wants to See: " + clinicianName + "<br>"; 
    if (altClinician != null) {
      String altClinicianName = Util.buildFullName(altClinician.getFirstName(), altClinician.getMiddleName(), altClinician.getLastName());
      message += "Would See: " + altClinicianName + "<br>"; 
    }
    message += "Preferred Dates: " + dto.apptFrom + " - " + dto.apptTo + "<br>"; 
    message += "Preferred Times: " + dto.preferredTimes + "<br>"; 
    dto.patientId = patient.getId();
    logEvent(dto, ActivityLog.VIEW_PATIENT, "ExternalService buildAppointmentMessageuth()", null, null);
    return message;
  }



  public void deleteLetter(MessageDTO dto) throws Exception {
    Letter letter = messageDAO.findById(Letter.class, dto.id);
    messageDAO.delete(letter);
    logEvent(dto, ActivityLog.DELETE_LETTER, "MessageService deleteLetter()", null, null);
  }



  public void deleteMessage(MessageDTO dto) throws Exception {
    ClientSession clientSession = messageGetClientSession(dto);
    MessageMessageRecipient mmr = messageDAO.findMessageMessageRecipient(dto.id, clientSession.getClientId());
    mmr.setDeleted(true);
    messageDAO.update(mmr);
    logEvent(dto, ActivityLog.DELETE_MESSAGE, "MessageService deleteMessage()", null, null);
  }



  public void getMessageDetails(MessageDTO dto) throws Exception {
    Integer clientId = null;
    ClientSession clientSession = messageGetClientSession(dto);
    String clientType = clientSession.getClientType();

    if (Client.USER.equals(clientType)) {
      clientId = clientSession.getUser().getId();
    }
    else if (Client.PATIENT.equals(clientType)) {
      clientId = clientSession.getPatient().getId();
    }

    Message message = messageDAO.findById(Message.class, dto.id);
    MessageDetails messageDetails = new MessageDetails();
    messageDetails.message = message;
    messageDetails.sender = messageDAO.findMessageSender(message);

    List<MessageMessageRecipient> mmrs = messageDAO.findMessageRecipients(message);
    String recipientClientType; 
    for (MessageMessageRecipient mmr : mmrs) {

      MessageRecipient mr = new MessageRecipient();
      recipientClientType = mmr.getRecipientClientType();
      if (recipientClientType == null) { recipientClientType = Client.USER; }
      if (recipientClientType.equals(clientType) && clientId == mmr.getRecipientId()) {
        messageDetails.isRecipient = true;
      };
      // get full details based on client type
      if (Client.USER.equals(recipientClientType)) {
        User user = messageDAO.findById(User.class, mmr.getRecipientId());
        mr.clientId = user.getId();
        mr.clientType = Client.USER;
        mr.firstName = user.getFirstName();
        mr.middleName = user.getMiddleName();
        mr.lastName = user.getLastName();
        mr.fullName = Util.buildFullName(mr.firstName, mr.middleName, mr.lastName);
        mr.email = user.getEmail();
      }
      else if (Client.PATIENT.equals(recipientClientType)) {
        Patient patient = messageDAO.findById(Patient.class, mmr.getRecipientId());
        mr.clientId = patient.getId();
        mr.clientType = Client.PATIENT;
        mr.firstName = DataEncryptor.decrypt(patient.getFirstName());
        mr.middleName = DataEncryptor.decrypt(patient.getMiddleName());
        mr.lastName = DataEncryptor.decrypt(patient.getLastName());
        mr.fullName = Util.buildFullName(mr.firstName, mr.middleName, mr.lastName);
        mr.email = patient.getEmail();
      }

      // add to appropriate list of recipients base on recipient mode
      if (MessageMessageRecipient.PRIMARY.equals(mmr.getRecipientMode())) {
        messageDetails.messagePrimaryRecipients.add(mr);
      }
      else if (MessageMessageRecipient.CC.equals(mmr.getRecipientMode())) {
        messageDetails.messageCCRecipients.add(mr);
      }
      else if (MessageMessageRecipient.BCC.equals(mmr.getRecipientMode())) {
        messageDetails.messageBCCRecipients.add(mr);
      }
    }

    dto.messageDetails = messageDetails;
    logEvent(dto, ActivityLog.GET_MESSAGE_DETAILS, "MessageService getMessageDetails()", null, null);
  }



  public void getMessages(MessageDTO dto) throws Exception {
    Map<String,List<?>> map = new HashMap<String,List<?>>();
    ClientSession clientSession = messageGetClientSession(dto);
    String clientType = clientSession.getClientType();
    Integer clientId = clientSession.getClientId();

    List<Message> items = messageDAO.findMessages(null, null, clientSession.getClientId(), clientType, false, false);
    for (Message item : items) { ExcludedObjects.excludeObjects(item); ExcludedFields.excludeFields(item); }
    map.put(MessageMessageRecipient.INBOX, items);

    items = messageDAO.findMessages(null, clientType, null, null, true, false);
    for (Message item : items) { ExcludedObjects.excludeObjects(item); ExcludedFields.excludeFields(item); }
    map.put(MessageMessageRecipient.SAVED, items);

    items = messageDAO.findMessages(clientId, clientType, null, null, false, false);
    for (Message item : items) { ExcludedObjects.excludeObjects(item); ExcludedFields.excludeFields(item); }
    map.put(MessageMessageRecipient.SENT, items);

    items = messageDAO.findMessages(clientId, clientType, null, null, false, true);
    for (Message item : items) { ExcludedObjects.excludeObjects(item); ExcludedFields.excludeFields(item); }
    map.put(MessageMessageRecipient.DRAFT, items);

    dto.messages = map;
    logEvent(dto, ActivityLog.GET_MESSAGES, "MessageService getMessages()", null, null);
  }

  public void getPatientToClinicianMessages(MessageDTO dto) throws Exception {
  }



  public void getValidMessageRecipients(MessageDTO dto, HttpServletRequest request) throws Exception {
    List<MessageRecipient> validMessageRecipients = new ArrayList<MessageRecipient>();
    List<User> clinicians = null;
    List<User> users = null;
    List<Patient> patients = null;
    ClientSession clientSession = messageDAO.findClientSessionBySessionId(dto.sessionId);

    String clientType = clientSession.getClientType();

    if (Client.USER.equals(clientType)) {
      clinicians = appDAO.findUsers(User.CLINICAL);
      users = appDAO.findUsers(User.OFFICE);
      patients = appDAO.findPatients();
    }
    for (User clinician : clinicians) {
      MessageRecipient mr = new MessageRecipient();
      mr.clientId = clinician.getId();
      mr.firstName = clinician.getFirstName();
      mr.middleName = clinician.getMiddleName();
      mr.lastName = clinician.getLastName();
      mr.fullName = Util.buildFullName(mr.firstName, mr.middleName, mr.lastName);
      mr.email = clinician.getEmail();
      validMessageRecipients.add(mr);
    } 
    for (User user : users) {
      MessageRecipient mr = new MessageRecipient();
      mr.clientId = user.getId();
      mr.clientType = Client.USER;
      mr.firstName = user.getFirstName();
      mr.middleName = user.getMiddleName();
      mr.lastName = user.getLastName();
      mr.fullName = Util.buildFullName(mr.firstName, mr.middleName, mr.lastName);
      mr.email = user.getEmail();
      validMessageRecipients.add(mr);
    } 
    for (Patient patient : patients) {
      MessageRecipient mr = new MessageRecipient();
      DataEncryptor.decryptPatient(patient);
      patient.setGuardian(getPatientService().getPatientGuardian(patient.getId()));
      mr.clientId = patient.getId();
      mr.clientType = Client.PATIENT;
      mr.firstName = patient.getFirstName();
      mr.middleName = patient.getMiddleName();
      mr.lastName = patient.getLastName();
      mr.fullName = Util.buildFullName(mr.firstName, mr.middleName, mr.lastName);
      mr.email = patient.getEmail();
      validMessageRecipients.add(mr);
    }
    Collections.sort(validMessageRecipients, new MessageRecipientComparator());
    dto.validMessageRecipients = validMessageRecipients;
  }

  public Boolean isValidMessageRecipient(MessageRecipient recipient, Client sender) throws Exception {
    // TODO: Eventually add permissions by user or client role
    return true;
  } 

  private ClientSession messageGetClientSession(MessageDTO dto) throws Exception {
    return getClientSession(dto.sessionId, dto.patientId);
  }

  public void processPatientActionMessage(MessageDTO dto) throws Exception {
    Patient patient = messageGetClientSession(dto).getPatient();
    if (patient == null) {
      setPatientNotFoundError(dto);
      return;
    }
    Integer assignedClinicianId = patient.getAssignedClinicianId();
    User clinician = null;
    if (assignedClinicianId != null) {
      clinician = messageDAO.findById(User.class, assignedClinicianId);
    }
    if (clinician == null) return;
    DataEncryptor.decryptPatient(patient);
    String patientFullName = Util.buildFullName(patient.getFirstName(), patient.getMiddleName(), patient.getLastName());

    Message message = new Message();
    String content = ""; 

    if (dto.messageType == MessageType.SUBMITTED_FORM) {  
      content = "Form submitted by " + patientFullName; 
    }

    message.setContent(content);
    message.setDate(new Date());
    message.setSubject(content);
    message.setSenderId(patient.getId());
    message.setSenderClientType(Client.PATIENT);
    message.setSenderFullName(patientFullName);
    message.setMessageType(dto.messageType);
    messageDAO.create(message);

    MessageMessageRecipient mmr = new MessageMessageRecipient();
    mmr.setMessageId(message.getId());
    mmr.setRecipientMode(MessageMessageRecipient.PRIMARY);
    mmr.setRecipientId(clinician.getId());
    mmr.setRecipientClientType(Client.USER);
    mmr.setNotify(false);
    messageDAO.create(mmr);
  }


  private void setPatientNotFoundError(MessageDTO dto) {
    ValidationResult result = new ValidationResult();
    dto.setError(result.invalid("Patient not found"));  
  }

  public void processPatientMessage(MessageDTO dto) throws Exception {
    Patient patient = messageGetClientSession(dto).getPatient();
    if (patient == null) {
      setPatientNotFoundError(dto);
      return;
    }
    User clinician = messageDAO.findById(User.class, dto.clinicianId);
    DataEncryptor.decryptPatient(patient);
    String patientFullName = Util.buildFullName(patient.getFirstName(), patient.getMiddleName(), patient.getLastName());

    Message message = new Message();
    String content = dto.content;
    if (dto.messageType == MessageType.APPT_REQUEST) {  
      content = buildAppointmentMessage(dto);
      content 
      += "======================================<br>" +
          "Patient Message: <br>" +
          dto.content;
    }
    message.setContent(content);
    message.setDate(new Date());
    message.setSubject(dto.subject);
    message.setSenderId(patient.getId());
    message.setSenderClientType(Client.PATIENT);
    message.setSenderFullName(patientFullName);
    message.setMessageType(dto.messageType);
    messageDAO.create(message);

    MessageMessageRecipient mmr = new MessageMessageRecipient();
    mmr.setMessageId(message.getId());
    mmr.setRecipientMode(MessageMessageRecipient.PRIMARY);
    mmr.setRecipientId(clinician.getId());
    mmr.setRecipientClientType(Client.USER);
    mmr.setNotify(false);
    messageDAO.create(mmr);
    logEvent(dto, ActivityLog.CREATE_MESSAGE, "MessageService processPatientMessage()", null, null);
  }

  private Client getClient(ClientSession clientSession) throws Exception {
    Client client = null;
    if (clientSession.getClientType().equals(Client.USER)) {
      client = clientSession.getUser();
    } else if (clientSession.getClientType().equals(Client.PATIENT)) {
      client = clientSession.getPatient();	
      DataEncryptor.decryptPatient((Patient) client);
    }  
    return client;
  }

  private String getRecipientClientType(String recipientClientType) {
    if (recipientClientType == null) {
      return Client.USER;
    }
    return recipientClientType;
  }

  public void saveOrSendLetter(MessageDTO dto, HttpServletRequest request) throws Exception {
    ClientSession clientSession = messageGetClientSession(dto);
    Client sender = getClient(clientSession);
    Boolean isNew = true;   
    Letter letter = null;

    if (dto.id != null) {
      letter = messageDAO.findById(Letter.class, dto.id);
      isNew = false;
    }
    else {
      letter = new Letter();
    }
    letter.setContent(dto.content);
    letter.setDate(new Date());
    letter.setRecipientId(dto.recipientId);
    String recipientClientType = getRecipientClientType(dto.recipientClientType);
    letter.setRecipientClientType(recipientClientType);
    letter.setSenderId(sender.getId());
    letter.setSenderClientType(clientSession.getClientType());
    letter.setSenderFullName(Util.buildFullName(sender.getFirstName(), sender.getMiddleName(), sender.getLastName()));
    if (isNew) {
      letter.setLetterType(dto.letterType);
      letter.setLetterTypeLabel(dto.letterTypeLabel);
    }
    letter.setLetterStatus(dto.draft == true ? Letter.STATUS_SAVED : Letter.STATUS_SENT);
    if (dto.id != null) {
      messageDAO.update(letter);
      logEvent(dto, ActivityLog.UPDATE_LETTER, "MessageService saveOrSendLetter()", null, null);
    }
    else {
      messageDAO.create(letter);
      logEvent(dto, ActivityLog.CREATE_LETTER, "MessageService saveOrSendLetter()", null, null);
    }
    if(dto.draft == false) {
      sendLetterNotification(letter, dto.recipientId, recipientClientType, request);
      logEvent(dto, ActivityLog.SEND_LETTER, "MessageService saveOrSendLetter()", null, null);
    }
    dto.id = letter.getId();
  }



  public void saveMessage(MessageDTO dto) throws Exception {
    ClientSession clientSession = messageGetClientSession(dto);
    MessageMessageRecipient mmr = messageDAO.findMessageMessageRecipient(dto.id, clientSession.getClientId());
    mmr.setSaved(true);
    messageDAO.update(mmr);
    logEvent(dto, ActivityLog.SAVE_MESSAGE, "MessageService saveMessage()", null, null);
  }


  public void sendLetterNotification(Letter letter, Integer recipientId, String recipientClientType, HttpServletRequest request) throws Exception {
    Client recipient = messageDAO.findClientByClientTypeAndId(recipientId, recipientClientType);
    if (Client.PATIENT.equals(recipientClientType)) {
      Patient patient=(Patient)recipient;
      DataEncryptor.decryptPatient(patient);
      patient.setGuardian(getPatientService().getPatientGuardian(patient.getId()));
    }
    log.info("Sending letter notification to : " + recipient.getEmail()); 

    String template = "letter_internal";   
    String emailTitle = "Letter from " + Core.practiceClientProperties.getProperty("app.business_name");
    if (Client.PATIENT.equals(recipientClientType)) { 
      template = "letter_external";      
    }

    Map<String,String> customAttributes = new HashMap<String,String>();
    customAttributes.put("name", Util.buildFullName(recipient.getFirstName(), recipient.getMiddleName(), recipient.getLastName()));
    customAttributes.put("sender", letter.getSenderFullName());
    MailHandler.sendSystemEmail(template, emailTitle, null, null, request, "", customAttributes, recipient.getEmail());
  }

  public void getLetters(MessageDTO dto) throws Exception {
    Integer id = dto.id;
    if (Statics.PORTAL_MODULE.equals(dto.module)) {
      ClientSession clientSession = messageGetClientSession(dto);
      id = clientSession.getClientId();
    }
    dto.list = patientDAO.findLetters(id);
  }

  public void sendOrSaveMessageFromProvider(MessageDTO dto, HttpServletRequest request) throws Exception {
    ClientSession clientSession = messageGetClientSession(dto);
    Client sender = getClient(clientSession);
    Message message = null;

    if (dto.id != null) {
      message = messageDAO.findById(Message.class, dto.id);
    }
    else {
      message = new Message();
    }

    message.setContent(dto.content);
    message.setDate(new Date());
    message.setDraft(dto.draft);
    message.setSenderId(sender.getId());
    message.setSenderClientType(clientSession.getClientType());
    message.setSenderFullName(Util.buildFullName(sender.getFirstName(), sender.getMiddleName(), sender.getLastName()));
    message.setSubject(dto.subject);
    if (dto.id != null) {
      messageDAO.update(message);
      logEvent(dto, ActivityLog.UPDATE_MESSAGE, "MessageService sendOrSaveMessageFromProvider()", null, null);
    }
    else {
      messageDAO.create(message);
      logEvent(dto, ActivityLog.CREATE_MESSAGE, "MessageService sendOrSaveMessageFromProvider()", null, null);
    }

    Integer messageId = message.getId();

    for (MessageRecipient recipient : dto.messagePrimaryRecipients) {
      if (isValidMessageRecipient(recipient, sender) ) {
        MessageMessageRecipient mmr = getOrCreateRecipientMessage(MessageMessageRecipient.PRIMARY, messageId, recipient.clientId, recipient.clientType);
        mmr.setMessageId(message.getId());
        mmr.setRecipientMode(MessageMessageRecipient.PRIMARY);
        mmr.setRecipientId(recipient.clientId);
        mmr.setRecipientClientType(getRecipientClientType(recipient.clientType));
        messageDAO.saveOrUpdate(mmr);
        if (dto.notifyRecipients == true && message.getDraft() == false) {
          sendMessageNotification(recipient, message, request);
        }
      }
    }

    for (MessageRecipient recipient : dto.messageCCRecipients) {
      if (isValidMessageRecipient(recipient, sender) ) {
        MessageMessageRecipient mmr = getOrCreateRecipientMessage(MessageMessageRecipient.PRIMARY, messageId, recipient.clientId, recipient.clientType);
        mmr.setMessageId(message.getId());
        mmr.setRecipientMode(MessageMessageRecipient.CC);
        mmr.setRecipientId(recipient.clientId);
        mmr.setRecipientClientType(getRecipientClientType(recipient.clientType));
        mmr.setNotify(dto.notifyCCRecipients);
        messageDAO.saveOrUpdate(mmr);
        if (dto.notifyCCRecipients == true && message.getDraft() == false) {
          sendMessageNotification(recipient, message, request);
        }
      }
    }

    for (MessageRecipient recipient : dto.messageBCCRecipients) {
      if (isValidMessageRecipient(recipient, sender) ) {
        MessageMessageRecipient mmr = getOrCreateRecipientMessage(MessageMessageRecipient.PRIMARY, messageId, recipient.clientId, recipient.clientType);
        mmr.setMessageId(message.getId());
        mmr.setRecipientMode(MessageMessageRecipient.BCC);
        mmr.setRecipientId(recipient.clientId);
        mmr.setRecipientClientType(getRecipientClientType(recipient.clientType));
        mmr.setNotify(dto.notifyBCCRecipients);
        messageDAO.saveOrUpdate(mmr);
        if (dto.notifyRecipients == true && message.getDraft() == false) {
          sendMessageNotification(recipient, message, request);
        }
      }
    }
    logEvent(dto, ActivityLog.SEND_MESSAGE, "MessageService sendOrSaveMessageFromProvider()", null, null);
  }

  private MessageMessageRecipient getOrCreateRecipientMessage(String recipientMode, Integer messageId, Integer recipientId, String recipientClientType) {
    MessageMessageRecipient mmr = messageDAO.getRecipientMessage(recipientMode, messageId, recipientId, recipientClientType);
    if (mmr == null) {
      mmr = new MessageMessageRecipient();
    }
    return mmr;
  }

  public void sendMessageNotification(MessageRecipient recipient, Message message, HttpServletRequest request) throws Exception {
    log.info("Sending message notification [subject: " + message.getSubject() + "] to : " + recipient.email); 

    String template = "message_internal";   
    String emailTitle = "Message from " + Core.practiceClientProperties.getProperty("app.business_name");
    if (Client.PATIENT.equals(recipient.clientType)) { 
      template = "message_external"; 
    }

    Map<String,String> customAttributes = new HashMap<String,String>();
    customAttributes.put("name", recipient.fullName);
    customAttributes.put("message", message.getContent());
    customAttributes.put("sender", message.getSenderFullName());

    MailHandler.sendSystemEmail(template, emailTitle, null, null, request, "", customAttributes, recipient.email);
  }



  public void updateLetterStatus(MessageDTO dto) throws Exception {
    Letter letter = messageDAO.findById(Letter.class, dto.id);
    letter.setLetterStatus(dto.letterStatus);
    messageDAO.update(letter);
  }


}
