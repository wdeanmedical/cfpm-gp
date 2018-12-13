package com.wdeanmedical.service;


import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mysql.jdbc.StringUtils;
import com.wdeanmedical.dto.AppDTO;
import com.wdeanmedical.dto.LeadMgmtDTO;
import com.wdeanmedical.entity.AddressType;
import com.wdeanmedical.entity.BaseEntity;
import com.wdeanmedical.entity.CalendarEvent;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.ClientSession;
import com.wdeanmedical.entity.Gender;
import com.wdeanmedical.entity.NetworkMarketingSource;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.PatientClinician;
import com.wdeanmedical.entity.PatientIntake;
import com.wdeanmedical.entity.ReferralSourceType;
import com.wdeanmedical.entity.SalesLead;
import com.wdeanmedical.entity.SalesLeadAction;
import com.wdeanmedical.entity.SalesLeadAgeRange;
import com.wdeanmedical.entity.SalesLeadCallStatus;
import com.wdeanmedical.entity.SalesLeadEmailStatus;
import com.wdeanmedical.entity.SalesLeadGoal;
import com.wdeanmedical.entity.SalesLeadStage;
import com.wdeanmedical.entity.SalesLeadStatus;
import com.wdeanmedical.entity.SalesLeadTask;
import com.wdeanmedical.entity.SalesLeadTaskUser;
import com.wdeanmedical.entity.SalesLeadUser;
import com.wdeanmedical.entity.USState;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.entity.form.PatientForm;
import com.wdeanmedical.entity.form.PracticeForm;
import com.wdeanmedical.entity.form.WDMForm;
import com.wdeanmedical.persistence.LeadMgmtDAO;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.MailHandler;
import com.wdeanmedical.util.Util;

public class LeadMgmtService extends AppService {

  private static Log log = LogFactory.getLog(LeadMgmtService.class);
  
  private LeadMgmtDAO leadMgmtDAO;
  
  private static int RECENT_SALES_LEADS_SIZE = 5;


  public LeadMgmtService() throws MalformedURLException {
    super();
    leadMgmtDAO = (LeadMgmtDAO) wac.getBean("leadMgmtDAO");
  }
  
  
  
  public void completeSalesLeadTask(LeadMgmtDTO dto) throws Exception {
    SalesLeadAction salesLeadAction = new SalesLeadAction();
    salesLeadAction.setDate(new Date());
    if (dto.name != null) { salesLeadAction.setName(dto.name); }
    salesLeadAction.setNotes(dto.notes);
    ClientSession clientSession = appDAO.findClientSessionBySessionId(dto.sessionId);
    Integer userId = clientSession.getUser().getId();
    salesLeadAction.setUserId(userId);
    SalesLeadTask salesLeadTask = leadMgmtDAO.findById(SalesLeadTask.class, dto.id);
    salesLeadAction.setSalesLeadId(salesLeadTask.getSalesLeadId());
    leadMgmtDAO.delete(salesLeadTask);
    leadMgmtDAO.create(salesLeadAction);
    CalendarEvent event = leadMgmtDAO.findCalendarEventByTaskId(dto.id);
    leadMgmtDAO.delete(event);
  }
 
  
  
  public void convertSalesLeadToPatient(LeadMgmtDTO dto, HttpServletRequest request) throws Exception {    
    SalesLead salesLead = leadMgmtDAO.findById(SalesLead.class, dto.id);  
    Patient patient = new Patient();
    patient.setEncrypted(false);
    
    patient.setPrimaryPhone(dto.primaryPhone);
    patient.setSecondaryPhone(dto.secondaryPhone);
    patient.setStreetAddress1(dto.streetAddress1);
    patient.setCity(dto.city);
    Integer id = null; try { id = new Integer(dto.usStateId); } catch (NumberFormatException nfe) {id = null;}
    patient.setUsState(leadMgmtDAO.findById(USState.class, id));
    patient.setPostalCode(dto.postalCode);
    patient.setOccupation(dto.occupation);
    patient.setGender(salesLead.getGender());
    patient.setDob(salesLead.getDob());
    patient.setFirstName(dto.firstName);
    patient.setMiddleName(dto.middleName);
    patient.setLastName(dto.lastName);
    patient.setEmail(dto.email);
    patient.setUsername(dto.email);
    patient.setGovtId(dto.ssn);
    patient.setDriversLicense(dto.driversLicense);
    patient.setActivationCode(newActivationCode());
    patient.setPassword("not a password"); 
    patient.setStatus(Client.ACTIVE);
    patient.setPrepaymentAmount(dto.prepaymentAmount);
    patient.setPasswordCreated(false);
    patient.setIntakeClosed(false);
    leadMgmtDAO.create(patient);
    
    PatientIntake patientIntake = new PatientIntake();
    patientIntake.setPatientId(patient.getId());
    appDAO.create(patientIntake);
    
    String[] forms = dto.forms.split(",");
    for (String name : forms) {
      PracticeForm practiceForm = appDAO.findPracticeFormByName(name); 
      PatientForm patientForm = new PatientForm();
      patientForm.setPracticeFormId(practiceForm.getId());
      Class<?> formClass = Class.forName(practiceForm.getClassName());
      BaseEntity baseEntity = appDAO.create((BaseEntity)formClass.newInstance());
      patientForm.setPracticeFormInstanceId(baseEntity.getId());
      appDAO.create(patientForm);
      patientForm.setPatientId(patient.getId());
    }
    
    
    DataEncryptor.encryptPatient(patient);
    
    leadMgmtDAO.update(patient);
    
    salesLead.setPatientId(patient.getId());
    leadMgmtDAO.update(salesLead);      
    
    PatientClinician pc = new PatientClinician();
    pc.setPatient(patient);
    User anyClinician = appDAO.findById(User.class, User.ANY_CLINICIAN);
    pc.setClinician(anyClinician);
    appDAO.create(pc);
    
    if (dto.sendPortalInvite == true) {
      dto.id = patient.getId();
      sendPortalInvitation(dto, request);
    }
  }
  
  
  
  public void sendPortalInvitation(LeadMgmtDTO dto, HttpServletRequest request) throws Exception {
    Patient patient = appDAO.findById(Patient.class, dto.id);
    String patientFullName = patient.getFirstName() + " " + patient.getLastName();
    String title = "Patient Portal Invitation for  " + patientFullName;
    String linkPath = "/portal?activateUser=true&activationCode=" + patient.getActivationCode().getCode();
    patient.setGuardian(getPatientService().getPatientGuardian(patient.getId()));
    MailHandler.sendSystemEmail("portal_invitation", title, patient, null, request, linkPath, null, null);
    PatientIntake patientIntake = appDAO.findPatientIntakeByPatientId(dto.id);
    patientIntake.setPortalInviteDate(new Date());
    appDAO.update(patientIntake);
  }
  
  
    
  public void deleteSalesLead(LeadMgmtDTO dto) throws Exception {    
    SalesLead salesLead = leadMgmtDAO.findById(SalesLead.class, dto.id);  
    leadMgmtDAO.delete(salesLead);    
  }
  
  
  
  public void deleteSalesLeadAction(LeadMgmtDTO dto) throws Exception {    
    SalesLeadAction salesLeadAction  = leadMgmtDAO.findById(SalesLeadAction.class, dto.id);  
    leadMgmtDAO.delete(salesLeadAction);    
  }
  
  
  
  public void deleteSalesLeadTask(LeadMgmtDTO dto) throws Exception {    
    SalesLeadTask salesLeadTask = leadMgmtDAO.findById(SalesLeadTask.class, dto.id);
    leadMgmtDAO.delete(salesLeadTask);    
    CalendarEvent event = leadMgmtDAO.findCalendarEventByTaskId(salesLeadTask.getId());
    leadMgmtDAO.delete(event);
  }

  public void getFilteredSalesLeads(LeadMgmtDTO dto) throws Exception {
    Gender gender = (Gender) WDMForm.processNonStringField(null,  dto.genderId, "gender", appDAO);
    Date date = (Date) WDMForm.processNonStringField(null, dto.dob, "date", appDAO);
    dto.list = leadMgmtDAO.findSalesLeads(dto.firstName, dto.middleName, dto.lastName, gender, date, dto.city);
  }  
   
  public void getRecentSalesLeads(LeadMgmtDTO dto) throws Exception {
    dto.list = leadMgmtDAO.findRecentSalesLeads(RECENT_SALES_LEADS_SIZE);
  }
  
  public void getSalesLead(LeadMgmtDTO dto) throws Exception {
    SalesLead salesLead = leadMgmtDAO.findById(SalesLead.class, dto.id);  
    List<SalesLeadAction> actions = leadMgmtDAO.findSalesLeadActions(dto.id);
    salesLead.setActions(actions);
    List<SalesLeadTask> tasks = leadMgmtDAO.findSalesLeadTasks(dto.id);
    for (SalesLeadTask task : tasks) { 
      List<Integer> userIds =  leadMgmtDAO.findSalesLeadTaskUserIds(task.getId());
      task.setSalesAgentIds(userIds);
    }
    salesLead.setTasks(tasks);
    List<Integer> userIds = leadMgmtDAO.findSalesLeadUserIds(dto.id);
    salesLead.setSalesAgentIds(userIds);
    dto.salesLead = salesLead;
  }
     
  public void getSalesLeadActions(LeadMgmtDTO dto) throws Exception {
    dto.list = leadMgmtDAO.findSalesLeadActions(dto.id);
  } 
    
  public  List<SalesLead> getSalesLeads(LeadMgmtDTO dto) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    Integer genderId; try { genderId = new Integer(dto.genderId); } catch (NumberFormatException nfe) {genderId = null;}
    Gender gender = leadMgmtDAO.findById(Gender.class, genderId);
    Date dob; try { dob = sdf.parse(dto.dob); } catch (ParseException pe) {dob = null;}
    List<SalesLead> salesLeads = leadMgmtDAO.findSalesLeads(dto.firstName, dto.middleName, dto.lastName, gender, dob, dto.city);
    return salesLeads;
  }
  
  public void getSalesLeadTasks(LeadMgmtDTO dto) throws Exception {
    List<SalesLeadTask> tasks = leadMgmtDAO.findSalesLeadTasks(dto.id);
    for (SalesLeadTask task : tasks) { 
      List<Integer> userIds =  leadMgmtDAO.findSalesLeadTaskUserIds(task.getId());
      task.setSalesAgentIds(userIds);
    }
    dto.list = tasks;
  }
     
  public boolean getSalesLeadSearchTypeAheads(LeadMgmtDTO dto) throws Exception {
    List<SalesLead> salesLeadList = leadMgmtDAO.findAllSalesLeads();
    Set<String> firstName = new TreeSet<String>();
    Set<String> middleName = new TreeSet<String>();
    Set<String> lastName = new TreeSet<String>();
    Set<String> city = new TreeSet<String>();
    
    for(SalesLead salesLead : salesLeadList){
      if(salesLead.getFirstName() != null){
        firstName.add(salesLead.getFirstName());
      }
      if(salesLead.getMiddleName() != null){
        middleName.add(salesLead.getMiddleName());
      }
      if(salesLead.getLastName() != null){
        lastName.add(salesLead.getLastName());
      }
      if(salesLead.getCity() != null){
        city.add(salesLead.getCity());
      }
    }
    
    dto.salesLeadFirstNameSearchTypeAheads.put("firstName", firstName);
    dto.salesLeadMiddleNameSearchTypeAheads.put("middleName", middleName);
    dto.salesLeadLastNameSearchTypeAheads.put("lastName", lastName);
    dto.salesLeadCitySearchTypeAheads.put("city", city);
    
    return true;
  }
  
  
  
  public void getSalesLeadTask(LeadMgmtDTO dto) throws Exception {
    SalesLeadTask salesLeadTask = leadMgmtDAO.findById(SalesLeadTask.class, dto.id);
    
    List<Integer> userIds =  leadMgmtDAO.findSalesLeadTaskUserIds(salesLeadTask.getId());
    salesLeadTask.setSalesAgentIds(userIds);
    
    List<SalesLeadTaskUser> taskUsers =  leadMgmtDAO.findSalesLeadTaskUsers(salesLeadTask.getId());
    List<String> userNames = new ArrayList<String>();
    for (SalesLeadTaskUser taskUser : taskUsers) { 
      User user = leadMgmtDAO.findById(User.class, taskUser.getUserId());
      String name  = Util.buildFullName(user.getFirstName(), user.getMiddleName(), user.getLastName());
      userNames.add(name);
    }
    salesLeadTask.setSalesAgentNames(userNames);
    dto.salesLeadTask = salesLeadTask;
  }
  

    
  public void saveNewSalesLead(LeadMgmtDTO dto, HttpServletRequest request) throws Exception {
    ClientSession clientSession = appDAO.findClientSessionBySessionId(dto.sessionId);
    Integer userId = clientSession.getUser().getId();
    Integer id = null;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    SalesLead salesLead = new SalesLead(); 
    salesLead.setTitle(dto.title);
    salesLead.setFirstName(dto.firstName);
    salesLead.setMiddleName(dto.middleName);
    salesLead.setLastName(dto.lastName);
    try { id = new Integer(dto.facilityId); } catch (NumberFormatException nfe) {id = null;} salesLead.setFacilityId(id);
    salesLead.setEmail(dto.email);
    salesLead.setPrimaryPhone(dto.primaryPhone);
    salesLead.setSecondaryPhone(dto.secondaryPhone);
    
    Date dob; try { dob = sdf.parse(dto.dob); } catch (Exception e) {dob = null;}
    salesLead.setDob(dob);
    
    try { id = new Integer(dto.statusId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) {salesLead.setStatus(leadMgmtDAO.findById(SalesLeadStatus.class, id));} 
    
    try { id = new Integer(dto.stageId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) {salesLead.setStage(leadMgmtDAO.findById(SalesLeadStage.class, id));}
    
    try { id = new Integer(dto.genderId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) {salesLead.setGender(leadMgmtDAO.findById(Gender.class, id));}
    
    try { id = new Integer(dto.ageRangeId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) {salesLead.setAgeRange(leadMgmtDAO.findById(SalesLeadAgeRange.class, id));}
    
    try { id = new Integer(dto.ageRangeId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) {salesLead.setAgeRange(leadMgmtDAO.findById(SalesLeadAgeRange.class, id));}
    
    try { id = new Integer(dto.sourceTypeId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) {salesLead.setSourceType(leadMgmtDAO.findById(ReferralSourceType.class, id));}
    
    try { id = new Integer(dto.networkMarketingSourceId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) {salesLead.setNetworkMarketingSource(leadMgmtDAO.findById(NetworkMarketingSource.class, id));}
    
    salesLead.setOccupation(dto.occupation);
    
    try { id = new Integer(dto.addressTypeId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) {salesLead.setAddressType(leadMgmtDAO.findById(AddressType.class, id));}
    
    salesLead.setStreetAddress1(dto.streetAddress1);
    salesLead.setCity(dto.city);
    
    try { id = new Integer(dto.usStateId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) {salesLead.setUsState(leadMgmtDAO.findById(USState.class, id));}
    
    salesLead.setPostalCode(dto.postalCode);
    
    try { id = new Integer(dto.altAddressTypeId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) {salesLead.setAltAddressType(leadMgmtDAO.findById(AddressType.class, id));}
    
    salesLead.setAltStreetAddress1(dto.altStreetAddress1);
    
    salesLead.setAltCity(dto.altCity);
    
    try { id = new Integer(dto.altUSStateId); } catch (NumberFormatException nfe) {id = null;}
    if (dto.altUSStateId != null) {salesLead.setAltUSState(leadMgmtDAO.findById(USState.class, id));}
    
    salesLead.setAltPostalCode(dto.altPostalCode);
    
    try { id = new Integer(dto.goalId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) {salesLead.setGoal(leadMgmtDAO.findById(SalesLeadGoal.class, id));}
    
    salesLead.setCreatorId(userId);
    
    salesLead.setBestTimeToContact(dto.bestTimeToContact);
    salesLead.setNote(dto.note);
    
    try { id = new Integer(dto.callStatusId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) {salesLead.setCallStatus(leadMgmtDAO.findById(SalesLeadCallStatus.class, id));}
    
    try { id = new Integer(dto.emailStatusId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) {salesLead.setEmailStatus(leadMgmtDAO.findById(SalesLeadEmailStatus.class, id));}
        
    leadMgmtDAO.create(salesLead);
    
    if(!StringUtils.isNullOrEmpty(dto.userIds)) {
      String[] userIds = dto.userIds.split(",");
      for (String userIdString : userIds) {
        SalesLeadUser salesLeadUser = new SalesLeadUser();
        salesLeadUser.setSalesLeadId(salesLead.getId());
        salesLeadUser.setUserId(Integer.parseInt(userIdString));
        leadMgmtDAO.create(salesLeadUser);
      }
    }
    
    String salesLeadName  = Util.buildFullName(salesLead.getFirstName(), salesLead.getMiddleName(), salesLead.getLastName());
    String title = "New Sales Lead " + salesLeadName + " created.";
    String salesLeadEmail = StringUtils.isNullOrEmpty(dto.email) ? "[unknown email]" : dto.email;
    String salesLeadPhone = StringUtils.isNullOrEmpty(dto.primaryPhone) ? "[unknown phone]" : dto.primaryPhone;
    
    Map<String,String> customAttributes = new HashMap<String,String>();
    customAttributes.put("salesLeadFullName", salesLeadName);
    customAttributes.put("email", salesLeadEmail);
    customAttributes.put("phone", salesLeadPhone);
    
    MailHandler.sendSystemEmail("new_sales_lead", title, null, null, request, "", customAttributes, null);
    
    List<Integer>  salesLeadUserIds = leadMgmtDAO.findSalesLeadUserIds(salesLead.getId());
    for(Integer salesLeadUserId : salesLeadUserIds){
      leadMgmtDAO.findById(User.class, salesLeadUserId);
      MailHandler.sendSystemEmail("new_sales_lead", title, null, null, request, "", customAttributes, null);
    }
    dto.salesLead = salesLead;
  } 
  
    
  
  public void saveNewSalesLeadAction(LeadMgmtDTO dto) throws Exception {
    ClientSession userSession = appDAO.findClientSessionBySessionId(dto.sessionId);
    Integer userId = userSession.getUser().getId();
    SalesLeadAction salesLeadAction = new SalesLeadAction();
    salesLeadAction.setSalesLeadId(dto.id);
    salesLeadAction.setName(dto.name);
    salesLeadAction.setDate(new Date());
    salesLeadAction.setNotes(dto.notes);
    salesLeadAction.setUserId(userId);
    leadMgmtDAO.create(salesLeadAction);
  }
  
  
    
  public void saveNewSalesLeadTask(LeadMgmtDTO dto, HttpServletRequest request) throws Exception {
    SalesLeadTask salesLeadTask = new SalesLeadTask();
    salesLeadTask.setSalesLeadId(dto.id);
    salesLeadTask.setName(dto.name);
    salesLeadTask.setTimeSpecified(dto.timeSpecified);
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    if (salesLeadTask.getTimeSpecified() == true) {
      sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    }
    if(!StringUtils.isNullOrEmpty(dto.dueDate)){ salesLeadTask.setDueDate(sdf.parse(dto.dueDate)); }
    salesLeadTask.setNotes(dto.notes);
    leadMgmtDAO.create(salesLeadTask);
    
    if(!StringUtils.isNullOrEmpty(dto.salesAgentIdsString)) {
      String salesAgentIds = dto.salesAgentIdsString;
      String[] userIds = salesAgentIds.split(",");
      SalesLeadTaskUser salesLeadTaskUser = null;
      for(String userId : userIds) {
        salesLeadTaskUser = new SalesLeadTaskUser();
        salesLeadTaskUser.setSalesLeadTaskId(salesLeadTask.getId());
        salesLeadTaskUser.setUserId(Integer.parseInt(userId));
        leadMgmtDAO.create(salesLeadTaskUser);
      }
    }
    
    SalesLead salesLead = leadMgmtDAO.findById(SalesLead.class, dto.id);  
    String salesLeadName  = Util.buildFullName(salesLead.getFirstName(), salesLead.getMiddleName(), salesLead.getLastName());
    User creatorUser = leadMgmtDAO.findById(User.class, salesLead.getCreatorId());
    String email = creatorUser.getEmail();
    String title = "New Sales Lead Task " + salesLeadTask.getName() + " for " + salesLeadName + " created.";
    
    Map<String,String> customAttributes = new HashMap<String,String>();
    customAttributes.put("salesLeadFullName", salesLeadName);
    customAttributes.put("email", email);
    customAttributes.put("dueDate", dto.dueDate);
    customAttributes.put("notes", dto.notes);
    customAttributes.put("name", salesLeadTask.getName());
    MailHandler.sendSystemEmail("new_sales_lead_task", title, null, null, request, "", customAttributes, null);
    
    List<Integer>  salesLeadTaskUserIds = leadMgmtDAO.findSalesLeadTaskUserIds(salesLeadTask.getId());
    
    for(Integer salesTaskLeadUserId : salesLeadTaskUserIds){
      User salesLeadUser = leadMgmtDAO.findById(User.class, salesTaskLeadUserId);
      customAttributes.put("email", salesLeadUser.getEmail());
      customAttributes.put("phone", salesLeadUser.getPrimaryPhone());
      MailHandler.sendSystemEmail("new_sales_lead_task", title, null, null, request, "", customAttributes, null);
    }
    
    
    CalendarEvent event = new CalendarEvent();
    event.setEventType(CalendarEvent.TASK);
    event.setStartTime(salesLeadTask.getDueDate());
    event.setTimeSpecified(salesLeadTask.getTimeSpecified());
    User anyClinician = appDAO.findById(User.class, User.ANY_CLINICIAN);
    event.setClinician(anyClinician);    
    Calendar cal = Calendar.getInstance();
    cal.setTime(event.getStartTime());
    cal.add(Calendar.MINUTE, 60); 
    Date endTime = cal.getTime();
    event.setEndTime(endTime);
    event.setOverride(false);
    event.setTitle(salesLeadTask.getName());
    event.setDesc(salesLeadTask.getNotes());
    event.setTaskId(salesLeadTask.getId());
    appDAO.create(event);
  }
  
    
    
  public void updateSalesAgentIds(LeadMgmtDTO dto) throws Exception {
    Integer salesLeadId = dto.id; 
    List<SalesLeadUser> salesLeadUsers =  leadMgmtDAO.findSalesLeadUsers(salesLeadId);
  
    for(SalesLeadUser salesLeadUser : salesLeadUsers) {
      leadMgmtDAO.delete(salesLeadUser);
    }
  
    if (!StringUtils.isNullOrEmpty(dto.updatePropertyValue)) {
      String updatePropertyValue = dto.updatePropertyValue;
      String[] salesAgentIds = updatePropertyValue.split(",");  
    
      for (String salesAgentId : salesAgentIds) {
        SalesLeadUser salesLeadUser = new SalesLeadUser();
        salesLeadUser.setSalesLeadId(salesLeadId);
        salesLeadUser.setUserId(Integer.parseInt(salesAgentId));
        leadMgmtDAO.create(salesLeadUser);
      }
    }
  }



  @Override
  public void updateField(AppDTO dto) throws Exception {
    updateField(dto);
  }
  
  
  
  public void updateSalesLeadAction(LeadMgmtDTO dto) throws Exception {
    SalesLeadAction salesLeadAction  = leadMgmtDAO.findById(SalesLeadAction.class, dto.id);  
    salesLeadAction.setName(dto.name);
    salesLeadAction.setNotes(dto.notes);
    leadMgmtDAO.update(salesLeadAction);
  }
  
  
  
  public void updateSalesLeadTask(LeadMgmtDTO dto) throws Exception {
    SalesLeadTask salesLeadTask = leadMgmtDAO.findById(SalesLeadTask.class, dto.id);
    salesLeadTask.setName(dto.name);
    salesLeadTask.setTimeSpecified(dto.timeSpecified);
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    if (salesLeadTask.getTimeSpecified() == true) {
      sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    }
    if(!StringUtils.isNullOrEmpty(dto.dueDate)){ salesLeadTask.setDueDate(sdf.parse(dto.dueDate)); }
    salesLeadTask.setNotes(dto.notes);
    leadMgmtDAO.update(salesLeadTask);
    
    if(!StringUtils.isNullOrEmpty(dto.salesAgentIdsString)) {
      List<SalesLeadTaskUser> salesLeadTaskUsers = leadMgmtDAO.findSalesLeadTaskUsers(salesLeadTask.getId());
      for(SalesLeadTaskUser salesLeadTaskUser : salesLeadTaskUsers){
        leadMgmtDAO.delete(salesLeadTaskUser);
      }
      String salesAgentIds = dto.salesAgentIdsString;
      String[] userIds = salesAgentIds.split(",");
      SalesLeadTaskUser salesLeadTaskUser = null;
      for(String userId : userIds) {
        salesLeadTaskUser = new SalesLeadTaskUser();
        salesLeadTaskUser.setSalesLeadTaskId(salesLeadTask.getId());
        salesLeadTaskUser.setUserId(Integer.parseInt(userId));
        leadMgmtDAO.create(salesLeadTaskUser);
      }
    }
    
    CalendarEvent event = leadMgmtDAO.findCalendarEventByTaskId(salesLeadTask.getId());
    event.setStartTime(salesLeadTask.getDueDate());
    event.setTimeSpecified(salesLeadTask.getTimeSpecified());
    Calendar cal = Calendar.getInstance();
    cal.setTime(event.getStartTime());
    cal.add(Calendar.MINUTE, 60); 
    Date endTime = cal.getTime();
    event.setEndTime(endTime);
    event.setTitle(salesLeadTask.getName());
    event.setDesc(salesLeadTask.getNotes());
    leadMgmtDAO.update(event);
  }


}
