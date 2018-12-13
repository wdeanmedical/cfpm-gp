package com.wdeanmedical.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "activity_log")
public class ActivityLog extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -3369919758390281272L;
  public static String[] PHI_FIELDS = new String[] {};
  
  public static final String ACTIVATE_PATIENT = "activate patient";
  public static final String ACTIVATE_USER = "activate user";
  public static final String ADD_EVAL_SUBTEST = "add eval subtest";
  public static final String ADD_EVAL_TEST = "add eval test";
  public static final String ADD_FILE = "add file";
  public static final String ADD_FORM = "add form";
  public static final String ADD_ITEM = "add item";
  public static final String ADD_LTG = "add long term goal";
  public static final String ADD_STG = "add short term goal";
  public static final String CHANGE_APPT_TIME = "change appt time";
  public static final String CHANGE_TASK_TIME = "change task time";
  public static final String CHARGE_CUSTOMER = "charge customer";
  public static final String CLOSE_FORM = "close form";
  public static final String CLOSE_PATIENT_INTAKE = "close patient intake";
  public static final String CREATE_APPT = "create appt";
  public static final String CREATE_BILLING_TICKET = "create billing ticket";
  public static final String CREATE_BILLING_TICKET_ENTRY = "create billing ticket entry";
  public static final String CREATE_ENCOUNTER = "create encounter";
  public static final String CREATE_FORM = "create form";
  public static final String CREATE_GOAL_BANK_ITEM = "create goal bank item";
  public static final String CREATE_GUARDIAN = "create guardian";
  public static final String CREATE_LETTER = "create letter";
  public static final String CREATE_MESSAGE = "create message";
  public static final String CREATE_PATIENT = "create patient";
  public static final String CREATE_PATIENT_FORM = "create patient form";
  public static final String CREATE_POT_PATIENT_FORM = "create POT patient form";
  public static final String CREATE_TX_NOTE = "create tx note";
  public static final String CREATE_TX_NOTE_COMPLAINT = "create tx note complaint";
  public static final String CREATE_TX_NOTE_DIAGNOSIS = "create tx note diagnosis";
  public static final String CREATE_USER = "create user";
  public static final String DEACTIVATE_USER = "deactivate user";
  public static final String DELETE_APPT = "delete appt";
  public static final String DELETE_BILLING_TICKET_ENTRY = "delete billing ticket entry";
  public static final String DELETE_CREDIT_CARD = "delete credit card";
  public static final String DELETE_EVAL_SUBTEST = "delete eval subtest";
  public static final String DELETE_EVAL_SUBTESTS = "delete eval subtests";
  public static final String DELETE_EVAL_TEST = "delete eval test";
  public static final String DELETE_FILE = "delete file";
  public static final String DELETE_GUARDIAN = "delete guardian";
  public static final String DELETE_INVOICE = "delete invoice";
  public static final String DELETE_ITEM = "delete item";
  public static final String DELETE_LETTER = "delete letter";
  public static final String DELETE_LTG = "delete long term goal";
  public static final String DELETE_MESSAGE = "delete message";
  public static final String DELETE_PATIENT = "delete patient";
  public static final String DELETE_PATIENT_FORM = "delete patient form";
  public static final String DELETE_PATIENT_MEDICATION = "delete patient medication";
  public static final String DELETE_STG = "delete short term goal";
  public static final String DELETE_TASK = "delete task";
  public static final String DELETE_USER = "delete user";
  public static final String EDIT_PATIENT_FIELD = "edit patient field";
  public static final String EDIT_PATIENT_ENCOUNTER_FIELD = "edit patient encounter field";
  public static final String EVENT = "event";
  public static final String EXPORT_REPORT = "export report";
  public static final String GET_BILLING_TICKET_ENTRIES = "get billing ticket entries";
  public static final String GET_CALENDAR = "get calendar";
  public static final String GET_CALENDAR_EVENT = "get calendar event";
  public static final String GET_CONSULTANT_INFO = "get consultant info";
  public static final String GET_FORM = "get form";
  public static final String GET_MESSAGE_DETAILS = "get message details";
  public static final String GET_MESSAGES = "get messages";
  public static final String GET_PATIENT_INTAKE = "get patient intake";
  public static final String LOGIN = "login";
  public static final String LOGOUT = "logout";
  public static final String PATIENT_PASSWORD_RESET = "patient password reset";
  public static final String PATIENT_PASSWORD_RESET_REQUEST = "patient password reset request";
  public static final String PARK_USER = "park user";
  public static final String REOPEN_FORM = "reopen form";
  public static final String REPLACE_CREDIT_CARD = "replace credit card";
  public static final String SAVE_BODY_ANNOTATION = "save body annotation";
  public static final String SAVE_BILLING_TICKET_ENTRY = "save billing ticket entry";
  public static final String SAVE_CREDIT_CARD = "save credit card";
  public static final String SAVE_INVOICE = "save invoice";
  public static final String SAVE_MESSAGE = "save message";
  public static final String SEND_INVOICE = "send invoice";
  public static final String SEND_LETTER = "send letter";
  public static final String SEND_MESSAGE = "send message";
  public static final String SEND_PORTAL_INVITATION = "send portal invitation";
  public static final String UNPARK_USER = "unpark user";
  public static final String UPDATE_APPT = "update appt";
  public static final String UPDATE_CREDIT_CARD = "update credit card";
  public static final String UPDATE_FIELD = "update field";
  public static final String UPDATE_FORM = "update form";
  public static final String UPDATE_GOAL_BANK_ITEM = "update goal bank item";
  public static final String UPDATE_GUARDIAN = "update guardian";
  public static final String UPDATE_ITEM = "update item";
  public static final String UPDATE_LETTER = "update letter";
  public static final String UPDATE_MESSAGE = "update message";
  public static final String UPDATE_PATIENT_INFO_FORMS = "update patient info forms";
  public static final String UPDATE_USER = "update user";
  public static final String UPLOAD_FILE = "upload file";
  public static final String UPLOAD_IMAGE = "upload image";
  public static final String USER_PASSWORD_RESET = "user password reset";
  public static final String USER_PASSWORD_RESET_REQUEST = "user password reset request";
  public static final String VIEW_ACTIVITY_LOGS = "view activity logs";
  public static final String VIEW_CLINICIANS = "view clinicians";
  public static final String VIEW_GUARDIANS = "view guardians";
  public static final String VIEW_PATIENT = "view patient";
  public static final String VIEW_PATIENTS = "view patients";
  public static final String VIEW_USERS = "view users";
  public static final String VIEW_PATIENT_ENCOUNTER = "view patient encounter";
  public static final String VIEW_TX_NOTES = "view tx notes";
  public static final String VIEW_WAIT_LIST = "view wait list";
  public static final String VIEW_CLINICIAN_ACTIVITY = "view clinician activity";

  public static final List<String> ACTIVITIES = Arrays.asList(ACTIVATE_PATIENT, ACTIVATE_USER, ADD_EVAL_SUBTEST, ADD_EVAL_TEST,
  ADD_FILE, ADD_FORM, ADD_ITEM, ADD_LTG, ADD_STG, CHANGE_APPT_TIME, CHANGE_TASK_TIME, CHARGE_CUSTOMER, CLOSE_FORM, CLOSE_PATIENT_INTAKE,
  CREATE_APPT, CREATE_BILLING_TICKET, CREATE_BILLING_TICKET_ENTRY, CREATE_ENCOUNTER, CREATE_FORM, CREATE_GOAL_BANK_ITEM, CREATE_GUARDIAN,
  CREATE_LETTER, CREATE_MESSAGE, CREATE_PATIENT, CREATE_PATIENT_FORM, CREATE_POT_PATIENT_FORM, CREATE_TX_NOTE, CREATE_TX_NOTE_COMPLAINT,
  CREATE_TX_NOTE_DIAGNOSIS, CREATE_USER, DEACTIVATE_USER, DELETE_APPT, DELETE_BILLING_TICKET_ENTRY, DELETE_CREDIT_CARD, DELETE_EVAL_SUBTEST, 
  DELETE_EVAL_SUBTESTS, DELETE_EVAL_TEST, DELETE_FILE, DELETE_GUARDIAN, DELETE_INVOICE, DELETE_ITEM, DELETE_LETTER, DELETE_LTG, DELETE_MESSAGE,
  DELETE_PATIENT, DELETE_PATIENT_FORM, DELETE_PATIENT_MEDICATION, DELETE_STG, DELETE_TASK, DELETE_USER, EDIT_PATIENT_ENCOUNTER_FIELD, 
  EDIT_PATIENT_FIELD, EVENT, EXPORT_REPORT, LOGIN, GET_BILLING_TICKET_ENTRIES, GET_CALENDAR, GET_CALENDAR_EVENT, GET_CONSULTANT_INFO,
  GET_FORM, GET_MESSAGE_DETAILS, GET_MESSAGES, GET_PATIENT_INTAKE, LOGIN, LOGOUT, PATIENT_PASSWORD_RESET, PATIENT_PASSWORD_RESET_REQUEST, 
  PARK_USER, REOPEN_FORM, REPLACE_CREDIT_CARD, SAVE_BODY_ANNOTATION, SAVE_BILLING_TICKET_ENTRY, SAVE_CREDIT_CARD, SAVE_INVOICE, SAVE_MESSAGE,
  SEND_PORTAL_INVITATION, UNPARK_USER, UPDATE_APPT, UPDATE_CREDIT_CARD, UPDATE_FIELD, UPDATE_FORM, UPDATE_GOAL_BANK_ITEM, UPDATE_GUARDIAN,
  UPDATE_ITEM, UPDATE_LETTER, UPDATE_MESSAGE, UPDATE_PATIENT_INFO_FORMS, UPDATE_USER, UPLOAD_FILE, UPLOAD_IMAGE, USER_PASSWORD_RESET,
  USER_PASSWORD_RESET_REQUEST, VIEW_ACTIVITY_LOGS, VIEW_CLINICIANS, VIEW_GUARDIANS, VIEW_PATIENT, VIEW_PATIENTS, VIEW_USERS,
  VIEW_PATIENT_ENCOUNTER, VIEW_TX_NOTES, VIEW_WAIT_LIST, VIEW_CLINICIAN_ACTIVITY );

  private String activity;
  private String clinicianName;
  private Integer clientId;
  private String clientName;
  private String clientType;
  private Integer encounterId;
  private String fieldName;
  private String module;
  private String notes;
  private Integer patientId;
  private String patientName;
  private String serviceMethod;
  private Date timePerformed;
  private Integer userId;
  private String userName;

  public ActivityLog() {
  }

  @Column(name = "user_id")
  public Integer getUserId() { return userId; }
  public void setUserId(Integer userId) { this.userId = userId; }

  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }

  @Column(name = "time_performed")
  public Date getTimePerformed() { return timePerformed; }
  public void setTimePerformed(Date timePerformed) { this.timePerformed = timePerformed; }

  @Column(name = "encounter_id")
  public Integer getEncounterId() { return encounterId; }
  public void setEncounterId(Integer encounterId) { this.encounterId = encounterId; }

  @Column(name = "field_name")
  public String getFieldName() { return fieldName; }
  public void setFieldName(String fieldName) { this.fieldName = fieldName; }

  @Column(name = "activity")
  public String getActivity() { return activity; }
  public void setActivity(String activity) { this.activity = activity; }

  @Column(name = "module")
  public String getModule() { return module; }
  public void setModule(String module) { this.module = module; }
  
  @Column(name = "client_id")
  public Integer getClientId() { return clientId; }
  public void setClientId(Integer clientId) { this.clientId = clientId; }

  @Column(name = "client_type")
  public String getClientType() { return clientType; }
  public void setClientType(String clientType) { this.clientType = clientType; }

  @Column(name = "service_method")
  public String getServiceMethod() { return serviceMethod; }
  public void setServiceMethod(String serviceMethod) { this.serviceMethod = serviceMethod; }

  @Column(name = "notes", columnDefinition = "text")
  public String getNotes() { return notes; }
  public void setNotes(String notes) { this.notes = notes; }

  @Transient  
  public String getClientName() { return clientName; }
  public void setClientName(String clientName) { this.clientName = clientName; }

  @Transient  
  public String getClinicianName() { return clinicianName; }
  public void setClinicianName(String clinicianName) { this.clinicianName = clinicianName; }

  @Transient  
  public String getUserName() { return userName; }
  public void setUserName(String userName) { this.userName = userName; }

  @Transient  
  public String getPatientName() { return patientName; }
  public void setPatientName(String patientName) { this.patientName = patientName; }
}
