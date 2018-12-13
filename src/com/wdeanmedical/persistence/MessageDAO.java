package com.wdeanmedical.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.ContactMessage;
import com.wdeanmedical.entity.Message;
import com.wdeanmedical.entity.MessageMessageRecipient;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.interfaces.ICriteriaTransformer;
import com.wdeanmedical.model.MessageRecipient;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.Util;

@Transactional
public class MessageDAO extends BaseDAO {

  private static final Logger log = Logger.getLogger(MessageDAO.class);

  private SessionFactory sessionFactory;

  public MessageDAO() {
    super();
  }

  @Override
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Session getSession() {
    return this.sessionFactory.getCurrentSession();
  }

  public Message findClinicianMessageById(int id) throws Exception {
    Message message = this.findById(Message.class, id);
    return message;
  }

  public List<ContactMessage> findContactMessages() throws Exception {
    return getList(ContactMessage.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.addOrder(Order.desc("date"));
      }
    });
  }

  public List<Message> findMessages(final Integer senderId, final String senderClientType, final Integer recipientId, final String recipientClientType, final Boolean saved, final Boolean draft) throws Exception {
    List<MessageMessageRecipient> messageRecipients = getList(MessageMessageRecipient.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit2) {
        crit2.add(Restrictions.eq("deleted", false));
        if (recipientId != null) { 
          crit2.add(Restrictions.eq("recipientId", recipientId)); 
          crit2.add(Restrictions.eq("recipientClientType", recipientClientType)); 
        }
        if (saved != null) { crit2.add(Restrictions.eq("saved", saved)); }
      } 
    });
    final List<Integer> messageIds = new ArrayList<Integer>();
    Integer messageId; 
    HashMap<Integer, String> recipients = new HashMap<Integer, String>();
    Client client;
    String clientType;
    String fullName;
    for(MessageMessageRecipient mr: messageRecipients) {
      messageId = mr.getMessageId();
      messageIds.add(messageId);
      if (!recipients.containsKey(messageId)) {
        clientType = mr.getRecipientClientType();
        client = findClientById(clientType, mr.getRecipientId());
        evict(client);
        if (clientType.equals(Client.PATIENT)) {
          DataEncryptor.decryptPatient((Patient)client);
        }
        fullName = client.getFullName();
        recipients.put(messageId, fullName);
      }
    }

    if (messageIds.isEmpty()) {
      return new ArrayList<Message>();
    }
    List<Message> messages = getList(Message.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        if (senderId != null) { 
          crit.add(Restrictions.eq("senderId", senderId)); 
          crit.add(Restrictions.eq("senderClientType", senderClientType));
        }; 
        if (draft != null) { 
          crit.add(Restrictions.eq("draft", draft)); 
        }
        if (messageIds != null && !messageIds.isEmpty()) { 
          crit.add(Restrictions.in("id", messageIds)); 
        }
        crit.addOrder(Order.desc("date"));
      }
    });
    for(Message message: messages) {
      fullName = recipients.get(message.getId());
      message.setReceiverFullName(fullName);
    }
    return messages;
  }

  public MessageMessageRecipient findMessageMessageRecipient(Integer messageId, Integer recipientId) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(MessageMessageRecipient.class);
    crit.add(Restrictions.eq("messageId", messageId));
    crit.add(Restrictions.eq("recipientId", recipientId));
    MessageMessageRecipient mmr = (MessageMessageRecipient)crit.uniqueResult();
    return mmr;
  }

  public MessageRecipient findMessageSender(Message message) throws Exception {
    MessageRecipient mr = new MessageRecipient();

    if (Client.USER.equals(message.getSenderClientType())) {
      User user = findById(User.class, message.getSenderId());
      mr.clientId = user.getId();
      mr.clientType = Client.USER;
      mr.firstName = user.getFirstName();
      mr.middleName = user.getMiddleName();
      mr.lastName = user.getLastName();
      mr.fullName = Util.buildFullName(mr.firstName, mr.middleName, mr.lastName);
      mr.email = user.getEmail();
    }
    else if (Client.PATIENT.equals(message.getSenderClientType())) {
      Patient patient = findById(Patient.class, message.getSenderId());
      mr.clientId = patient.getId();
      mr.clientType = Client.PATIENT;
      mr.firstName = DataEncryptor.decrypt(patient.getFirstName());
      mr.middleName = DataEncryptor.decrypt(patient.getMiddleName());
      mr.lastName = DataEncryptor.decrypt(patient.getLastName());;
      mr.fullName = Util.buildFullName(mr.firstName, mr.middleName, mr.lastName);
      mr.email = patient.getEmail();
    }
    return mr;
  }

  public List<MessageMessageRecipient> findMessageRecipients(final Message message) throws Exception {
    return getList(MessageMessageRecipient.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("messageId", message.getId()));
      }
    });
  }

  public List<Message> findPatientMessages(final Patient patient) throws Exception {
    return getList(Message.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patient", patient));
      }
    });
  }

  public List<Message> findPatientMessagesByUser(final User user) throws Exception {
    return getList(Message.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("user", user));
        crit.addOrder(Order.desc("date"));
      }
    });
  }

  public MessageMessageRecipient getRecipientMessage(final String recipientMode, final Integer messageId, final Integer recipientId,
      final String recipientClientType) {
    return findOne(MessageMessageRecipient.class, new ICriteriaTransformer() {
      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.eq("recipientMode", recipientMode));
        criteria.add(Restrictions.eq("recipientId", recipientId));
        criteria.add(Restrictions.eq("recipientClientType", recipientClientType));
        criteria.add(Restrictions.eq("messageId", messageId));
      }
    });
  }
}
