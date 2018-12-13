package com.wdeanmedical.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.wdeanmedical.core.Core;
import com.wdeanmedical.core.ExcludedFields;
import com.wdeanmedical.core.ExcludedObjects;
import com.wdeanmedical.core.Permissions;
import com.wdeanmedical.core.Statics;
import com.wdeanmedical.dto.AppDTO;
import com.wdeanmedical.dto.MessageDTO;
import com.wdeanmedical.dto.PatientDTO;
import com.wdeanmedical.entity.ActivityLog;
import com.wdeanmedical.entity.BaseEntity;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.ClientSession;
import com.wdeanmedical.entity.ContactMessage;
import com.wdeanmedical.entity.Facility;
import com.wdeanmedical.entity.Guardian;
import com.wdeanmedical.entity.NetworkMarketingSource;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.PatientClinician;
import com.wdeanmedical.entity.RecoveryCode;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.entity.form.Invoice;
import com.wdeanmedical.entity.form.InvoiceLineItem;
import com.wdeanmedical.entity.form.PatientForm;
import com.wdeanmedical.entity.form.PracticeForm;
import com.wdeanmedical.entity.form.WDMForm;
import com.wdeanmedical.gp.entity.CPT;
import com.wdeanmedical.gp.entity.ICD9;
import com.wdeanmedical.interfaces.IBillingTicket;
import com.wdeanmedical.interfaces.IBillingTicketEntry;
import com.wdeanmedical.interfaces.ICriteriaTransformer;
import com.wdeanmedical.interfaces.IPassword;
import com.wdeanmedical.model.MessageType;
import com.wdeanmedical.model.Updateable;
import com.wdeanmedical.model.ValidationResult;
import com.wdeanmedical.persistence.AppDAO;
import com.wdeanmedical.persistence.CalendarDAO;
import com.wdeanmedical.persistence.MessageDAO;
import com.wdeanmedical.persistence.PatientDAO;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.JSONUtils;
import com.wdeanmedical.util.MailHandler;
import com.wdeanmedical.util.OneWayPasswordEncoder;
import com.wdeanmedical.util.TimeUtil;
import com.wdeanmedical.util.Util;

public class AppService {

  private static Log log = LogFactory.getLog(AppService.class);
  
  public static int RETURN_CODE_DUP_EMAIL = -1;
  public static int RETURN_CODE_INVALID_PASSWORD = -2;
  public static int RETURN_CODE_VALID = 1;
  public static int RETURN_CODE_INVALID_SSN = -3;
  public static int RETURN_CODE_INVALID_DRIVERS_LICENSE = -3;
  public static int RETURN_CODE_INVALID_SSN_OR_DL = -3; 

  protected ServletContext context;
  protected WebApplicationContext wac;
  protected AppDAO appDAO;
  protected PatientDAO patientDAO;
  protected MessageDAO messageDAO;
  protected CalendarDAO calendarDAO;
  
  GPPatientService gpPatientService = null;

	private Map<String, Object> services = new HashMap<String, Object>();
  
  
  public AppService() throws MalformedURLException {
    context = Core.servletContext;
    wac = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
    appDAO = (AppDAO) wac.getBean("appDAO");
    messageDAO = (MessageDAO) wac.getBean("messageDAO");
    patientDAO = (PatientDAO) wac.getBean("patientDAO");
    calendarDAO = (CalendarDAO) wac.getBean("calendarDAO");
  }

  public PatientDAO getPatientDAO() {
    return patientDAO;
  }
 

  
  public void addBillingTicketEntry(AppDTO dto) throws Exception {
    Object item = getFormInstance(dto);
    String[] properties = {
        "patientId", "clinicianId"
    };
    Util.setProperties(item, properties, dto);
    BaseEntity entity = (BaseEntity) item;
    appDAO.create(entity);
    dto.object = entity;
    logEvent(dto, ActivityLog.CREATE_BILLING_TICKET_ENTRY, "AppService addBillingTicketEntry()", null, null);
  }
  
  

  public void addForm(AppDTO dto) throws Exception {
    if (appDAO.findOpenedForms(dto.formClassName, dto.patientId, dto.evalMode).size() > 1) {
      log.error("an instance is currently open");
      return;
    }
    Class<?> formClass  = Class.forName(dto.formClassName);
    Object genericInstance = formClass.newInstance();
    Class<?>[] params = new Class[]{genericInstance.getClass(), AppService.class, AppDTO.class};
    Method method = null; 
    try {
    method = WDMFormService.class.getMethod("newInstance", params);
    } catch(Exception e) {
    }
    if (method != null) {
      method.invoke(null, genericInstance, this, dto);
    } 
    WDMForm formClassInstance = (WDMForm)genericInstance;
    ValidationResult validationResult = formClassInstance.validateBeforeCreate(this, dto);
    if (validationResult.isValid) {
	    if (method == null) {
	      appDAO.create(formClassInstance);
	    }
	    if (dto.patientId != null) {
	      BeanUtils.setProperty(formClassInstance, "patientId", dto.patientId);
	    }
	    ClientSession clientSession = appDAO.findClientSessionBySessionId(dto.sessionId);
	    if (Client.USER.equals(clientSession.getClientType())) {
	      BeanUtils.setProperty(formClassInstance, "userId", clientSession.getClientId());
	    }
	    String name = BeanUtils.getProperty(formClassInstance, "name");
	    PracticeForm practiceForm = appDAO.findPracticeFormByName(name);
	    BeanUtils.setProperty(formClassInstance, "practiceForm", practiceForm);
	    appDAO.update(formClassInstance);
	    addFormItems(dto, formClassInstance);
	    dto.object = formClassInstance;
    } else {
    		dto.setError(validationResult);
    }
    logEvent(dto, ActivityLog.ADD_FORM, "AppService addForm()", null, null);
  }
  
  public void addFormItems(AppDTO dto, WDMForm formClassInstance) throws Exception {
  }
  
  
  public void addInvoiceLineItem(AppDTO dto) throws Exception {
    InvoiceLineItem item = new InvoiceLineItem();
    item.setInvoiceId(dto.id);
    item.setPrice((float) 0);
    appDAO.create(item);
    dto.object = item;
  }
  
  
  public void addItem(AppDTO dto) throws Exception {
    Class<?> formClass = Class.forName(dto.formClassName);
    BaseEntity formClassInstance = (BaseEntity)formClass.newInstance();
    appDAO.create(formClassInstance);
    if (dto.patientId != null) {
      BeanUtils.setProperty(formClassInstance, "patientId", dto.patientId);
      appDAO.update(formClassInstance);
    }
    ClientSession clientSession = appDAO.findClientSessionBySessionId(dto.sessionId);
    if (Client.USER.equals(clientSession.getClientType())) {
      BeanUtils.setProperty(formClassInstance, "userId", clientSession.getClientId());
      appDAO.update(formClassInstance);
    }
    processAddItem(dto, formClassInstance);
    dto.object = formClassInstance;
    logEvent(dto, ActivityLog.ADD_ITEM, "AppService addItem()", null, null);
  }
  

  
  public void addSubItem(AppDTO dto) throws Exception {
    Class<?> formClass = getFormClass(dto);
    BaseEntity formClassInstance = (BaseEntity)formClass.newInstance();
    BeanUtils.setProperty(formClassInstance, dto.parentIdName, dto.parentId);
    appDAO.create(formClassInstance);
    dto.object = formClassInstance;
    logEvent(dto, ActivityLog.ADD_ITEM, "AppService addSubItem()", null, null);
  }
  
  
  
  public void calculateInvoiceTotal(Invoice invoice) throws Exception {
    Float subtotal = (float) 0.0;
    for (InvoiceLineItem item : invoice.getInvoiceLineItemList()) {
      subtotal += item.getPrice();  
    }
    invoice.setSubtotal(subtotal);
    invoice.setTotal(subtotal + invoice.getSalesTax());
    appDAO.update(invoice);
  }
  
  
  
  public void checkForOpenForm(AppDTO dto) throws Exception {
    Integer id = appDAO.checkForOpenForm(dto.formClassName, dto.patientId, dto.id, dto.evalMode);
    dto.id = id;
  }
  
  

  public void closeAndCreateForm(AppDTO dto) throws Exception {
    updateForm(dto);
    addForm(dto);
  }
  
  
  
  public void closeForm(AppDTO dto) throws Exception {
    updateForm(dto);
    logEvent(dto, ActivityLog.CLOSE_FORM, "AppService closeForm()", null, null);
  }

  
  
  public boolean createPassword(AppDTO dto, HttpServletRequest request) throws Exception {
    Client client = appDAO.findClientById(dto.clientType, dto.id);
    updatePassword(client, dto);
    if (dto.clientType.equals(Client.GUARDIAN)) {
      DataEncryptor.decryptGuardian((Guardian)client);
    } else if (dto.clientType.equals(Client.PATIENT)) {
      Patient patient = (Patient)client;
      DataEncryptor.decryptPatient(patient);
      patient.setGuardian(getPatientService().getPatientGuardian(patient.getId()));
    }
    String userFullName = client.getFirstName() + " " + client.getLastName();
    String title;
    String confirmationTemplate;
		if (!dto.mode.toLowerCase().equals(Statics.PASSWORD_RESET)) {
    		title = userFullName + ", welcome to " + Core.practiceClientProperties.getProperty("app.business_name");
    		confirmationTemplate = "password_creation_confirmation";
    } else {
    		title = userFullName + " your password has been updated successfully.";
    		confirmationTemplate = "password_reset_confirmation";
    }
    MailHandler.sendSystemEmail(confirmationTemplate, title, null, client, request, "", null, null);
    return true;
  }

  
  public String decrypt(String encrypted) throws Exception {
     return DataEncryptor.decrypt(encrypted);
  }
  

  
  public void deleteBillingTicketEntry(AppDTO dto) throws Exception {
    Class<?> EntryKlass = getFormClass(dto);
    BaseEntity item = (BaseEntity) appDAO.findById(EntryKlass.newInstance().getClass(), dto.id);
    appDAO.delete(item);
    logEvent(dto, ActivityLog.DELETE_BILLING_TICKET_ENTRY, "AppService deleteBillingTicketEntry()", null, null);
  }
  
  
  
  public void deleteInvoice(AppDTO dto) throws Exception {
    Invoice item = appDAO.findById(Invoice.class, dto.id);
    appDAO.delete(item);
    appDAO.deleteInvoiceLineItems(dto.id);
    logEvent(dto, ActivityLog.DELETE_INVOICE, "AppService deleteInvoice()", null, null);
  } 
  
  
  
  public void deleteItem(AppDTO dto) throws Exception {
    BaseEntity formClassInstance = null;
    Class<?> formClass = getFormClass(dto);
    formClassInstance = (BaseEntity)appDAO.findById(formClass, dto.id);
    processDeleteItem(dto, formClassInstance);
    appDAO.delete(formClassInstance);
    logEvent(dto, ActivityLog.DELETE_ITEM, "AppService deleteItem()", dto.formClassName, null);
  }
  
  public void processDeleteItem(AppDTO dto, BaseEntity formClassInstance) throws Exception {
  }
  
  public String encrypt(String string) throws Exception {
    return StringUtils.isNotBlank(string) ? DataEncryptor.encrypt(string) : string;
  }


  public void fixPatients() throws Exception {
    List<Patient> ps = appDAO.findPatients();
    for (Patient p : ps) {
      Integer id = p.getId();
      String name = DataEncryptor.decrypt(p.getLastName());
      p.setLastName(DataEncryptor.encrypt(name + id.toString()));
      patientDAO.update(p);
     
    }
  }

  public void getAppLists(AppDTO dto) throws Exception {
    Map<String,List<?>> map = new HashMap<String,List<?>>();
    ClientSession clientSession = appDAO.findClientSessionBySessionId(dto.sessionId);
    String clientType = clientSession.getClientType();
    
    if (Client.USER.equals(clientType)) {
      map.put("apptTypes", appDAO.findApptTypes());
      map.put("clinicianInfo", appDAO.findUserInfo());
      map.put("patientInfo", appDAO.findPatientInfo());
      map.put("clinicians", appDAO.findUsers(User.CLINICAL));
      map.put("locations", appDAO.findFacilities());
      map.put("users", appDAO.findUsers(User.OFFICE));
      map.put("userLocations", appDAO.findUserFacilities());
      map.put("clinicianLocations", appDAO.findUserFacilities());
      map.put("networkMarketingSources", appDAO.findNetworkMarketingSources());
    }
    else if (Client.PATIENT.equals(clientType)) {
      Patient patient = patientDAO.findPatientBySessionId(clientSession.getSessionId());
      map.put("patientUsers", appDAO.findPatientClinicians(patient));
    }

    dto.appLists = map;
  }

   
  private static Set<String> getChangedFields(Object oldEntity, Object newEntity) throws Exception {
    Set<String> hashSet = new HashSet<String>();
    Method[] oldEntityMethods = null;
    if (oldEntity != null) {
      oldEntityMethods = oldEntity.getClass().getMethods();
    }
    Method[] newEntityMethods = newEntity.getClass().getMethods();
    if (oldEntityMethods == null) {
      for (Method newEntityMethod : newEntityMethods) {
        if (newEntityMethod.getName().startsWith("get") || newEntityMethod.getName().startsWith("is")) {
          Object newEntityReturnedValue = newEntityMethod.invoke(newEntity);
          if (newEntityReturnedValue == null) {
            continue;
          } else if (newEntityMethod.getName().startsWith("get")) {
            hashSet.add(newEntityMethod.getName().substring(3));
          } else if (newEntityMethod.getName().startsWith("is")) {
            hashSet.add(newEntityMethod.getName().substring(2));
          }
        }
      }
    } else {
      outer: for (Method oldEntityMethod : oldEntityMethods) {
        Object oldEntityReturnedValue = null;
        Object newEntityReturnedValue = null;
        String oldEntityMethodName = null;
        String newEntityMethodName = null;
        if (oldEntityMethod.getName().startsWith("get") || oldEntityMethod.getName().startsWith("is")) {
          oldEntityMethodName = oldEntityMethod.getName();
          oldEntityReturnedValue = oldEntityMethod.invoke(oldEntity);
          if (oldEntityReturnedValue == null) {
            continue;
          }
          for (Method newEntityMethod : newEntityMethods) {

            if (newEntityMethod.getName().startsWith("get") || newEntityMethod.getName().startsWith("is")) {
              newEntityMethodName = newEntityMethod.getName();
              if (oldEntityMethodName.equals(newEntityMethodName)) {
                newEntityReturnedValue = newEntityMethod.invoke(newEntity);
                if (newEntityReturnedValue == null) {
                  continue outer;
                }
                if (oldEntityReturnedValue.equals(newEntityReturnedValue)) {
                  continue outer;
                } else if (newEntityMethod.getName().startsWith("get")) {
                  hashSet.add(newEntityMethod.getName().substring(3));
                } else if (newEntityMethod.getName().startsWith("is")) {
                  hashSet.add(newEntityMethod.getName().substring(2));
                }
                continue outer;
              }
            }
          }

        }
      }
    }
    return hashSet;
  }
  
  
  
  public void getClientProperties(AppDTO dto) throws Exception {
    Core.practiceClientProperties.setProperty("practice", Core.practice);
    Core.practiceClientProperties.setProperty("specialty", Core.specialty);
    dto.practiceClientProperties = Core.practiceClientProperties;
    dto.specialtyClientProperties = Core.specialtyClientProperties;
    dto.clientProperties = Core.clientProperties;
  }
  
  
  
  protected ClientSession getClientSession(String sessionId, Integer patientId) throws Exception {
    ClientSession clientSession = appDAO.findClientSessionBySessionId(sessionId); 
    if (clientSession.getClientType().equals(Client.GUARDIAN)) {
      Patient patient = getPatient(patientId, clientSession);
      clientSession = new ClientSession();
    clientSession.setClientType(Client.PATIENT);
    clientSession.setClientId(patient.getId());
    clientSession.setPatient(patient);
    }
  return clientSession;
  }
  
  
  
  public String getClinicianName(Integer clinicianId) throws Exception {
      User clinician = appDAO.findById(User.class, clinicianId);
      String clinicianName = null;
      if (clinician != null) {
         clinicianName = Util.buildFullName(clinician.getFirstName(), clinician.getMiddleName(), clinician.getLastName());
      }
    return clinicianName;
  }
  
  
  
  public void getClinicians(AppDTO dto) throws Exception {
    List<User> items = appDAO.findUsers(User.CLINICAL);
    for (User item : items) {
      ExcludedFields.excludeFields(item);
      ExcludedObjects.excludeObjects(item);
    }
    dto.list = items;
    logEvent(dto, ActivityLog.VIEW_CLINICIANS, "AppService getClinicians()", null, null);
  }
  

  
  public List<PatientClinician> getClinicianPatients(AppDTO dto) throws Exception {
    User clinician = appDAO.findById(User.class, dto.id);
    dto.clinicianId = clinician.getId();
    List<PatientClinician> pcs = appDAO.findClinicianPatients(clinician);
    for (PatientClinician pc : pcs) { DataEncryptor.decryptPatient(pc.getPatient()); }
    logEvent(dto, ActivityLog.VIEW_CLINICIANS, "AppService getClinicianPatients()", null, null);
    return pcs;
  }
  
  

  public void getConsultantInfo(AppDTO dto) throws Exception {
    User user = appDAO.findUserBySessionId(dto.sessionId);
    Facility facility = (appDAO.findUserFacilitiesByUserId(user.getId())).get(0).getFacility();
    dto.facility = facility;
    NetworkMarketingSource source = (appDAO.findUserNetworkMarketingSourceByUserId(user.getId())).get(0).getSource();
    dto.network = source;
    logEvent(dto, ActivityLog.GET_CONSULTANT_INFO, "AppService getConsultantInfo()", null, null);
  }
  
  
  
  public void getFile(AppDTO dto, HttpServletRequest request, HttpServletResponse response) throws Exception {
    String patientId = request.getParameter("patientId");
    String path = request.getParameter("path");
    String filesHomePatientDirPath =  Core.filesHome  + Core.patientDirPath + "/" + patientId + "/";
    getFile(request, response, request.getServletContext(), filesHomePatientDirPath, path);
  }
  
  
  
  public void getFile(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, 
                      String filePath, String fileName) throws Exception {
    String mime = servletContext.getMimeType(fileName);
    if (mime == null) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return;
    }
    response.setContentType(mime);  
    File file = new File(filePath + fileName);
    response.setContentLength((int) file.length());
    FileInputStream in = new FileInputStream(file);
    OutputStream out = response.getOutputStream();
    byte[] buf = new byte[1024];
    int count = 0;
    while ((count = in.read(buf)) >= 0) {
      out.write(buf, 0, count);
    }
    out.close();
    in.close();
  }
  
  
  
  public void getForm(AppDTO dto) throws Exception {
    WDMForm instance = null, formClassInstance = null;
    Class<?> formClass = Class.forName(dto.formClassName);
    WDMForm newInstance = (WDMForm)formClass.newInstance();
    String name = "";
    
    if (dto.byPatientId != null && dto.byPatientId == true) {
      formClassInstance = (WDMForm)appDAO.findByPatientId(formClass, dto.patientId);
    }
    else {
      formClassInstance = (WDMForm)appDAO.findById(formClass, dto.id);
    }
    if (formClassInstance == null  && dto.createIfNull == true) {
    	addForm(dto);
    }
    else {
      name = newInstance.getName();
      PracticeForm practiceForm = appDAO.findPracticeFormByName(name);
      if (formClassInstance == null) {
        instance = newInstance;
      } else {
        instance = formClassInstance;
      }
      BeanUtils.setProperty(instance, "practiceForm", practiceForm);
      instance.initialize(this);
      getFormItems(dto, instance);
      dto.object = instance;
    }
    logEvent(dto, ActivityLog.GET_FORM, "AppService getForm()", name, null);
  }
  
  
  
  private Class<?> getFormClass(AppDTO dto) throws Exception {
    String formClassName = "com.wdeanmedical." + dto.formClassName.replace("com.wdeanmedical.", "").replace('-', '.');
    return Class.forName(formClassName);
  }
  
  
  
  private Object getFormInstance(AppDTO dto) throws Exception {
    Class<?> Klass = getFormClass(dto);
    return Klass.newInstance();
  }
  

  
  public void getFormItems(AppDTO dto, BaseEntity formClassInstance) throws Exception {
  } 
  
  
  
  public void getForms(AppDTO dto) throws Exception {
  }
  
  
  
  public GPPatientService getGPPatientService() throws Exception {
    return getService(GPPatientService.class);
  }
  
  
      
  public Set<String> getListOfChangedFields(BaseEntity newEntity) throws Exception {
    return getChangedFields(appDAO.findOldEntity(newEntity), newEntity);
  }
  
  
  
  public void getLocations(AppDTO dto) throws Exception {
    dto.list = appDAO.findFacilities();
  }
  
  

  public void getOrCreateBillingTicket(AppDTO dto) throws Exception {
    ClientSession clientSession = appDAO.findClientSessionBySessionId(dto.sessionId);
    Class<?> BillingTicketKlass = getFormClass(dto);
    IBillingTicket newBillingTicket = (IBillingTicket) BillingTicketKlass.newInstance();
    IBillingTicket billingTicket = appDAO.findBillingTicketByPatientId(newBillingTicket.getClass(), dto.patientId);
    if (billingTicket == null) {
      billingTicket = newBillingTicket;
      PropertyUtils.setProperty(billingTicket, "patientId", dto.patientId);
      PropertyUtils.setProperty(billingTicket, "clinicianId", clientSession.getClientId());
      appDAO.create((BaseEntity)billingTicket);
      logEvent(dto, ActivityLog.CREATE_BILLING_TICKET, "AppService getOrCreateBillingTicket()", null, null);
    }
    IBillingTicketEntry newBillingTicketEntry = (IBillingTicketEntry) newBillingTicket.getEntryClass().newInstance();
    List<IBillingTicketEntry> list = appDAO.findBillingTicketEntries(newBillingTicketEntry.getClass(), billingTicket);
    billingTicket.setEntries(list);
    dto.object = billingTicket;
  }


  
  private Patient getPatient(Integer patientId, ClientSession clientSession) throws Exception {
    if (clientSession.getClientType().equals(Client.PATIENT)) {
        return clientSession.getPatient();
    } else {
    Guardian guardian = clientSession.getGuardian();
    List<Integer> patientIds = patientDAO.getGuardianPatientIds(guardian.getId());
    if (patientIds.contains(patientId)) {
      Patient patient = (Patient) appDAO.findClientById(Client.PATIENT, patientId);
      return patient;
    }
    }
    return null;
  }
  
 
 
  public void getPatients(AppDTO dto) throws Exception {
    List<PatientClinician> patients = getClinicianPatients(dto); 
    List<PatientClinician> unassignedPatients = getUnassignedPatients(dto); 
    patients.addAll(unassignedPatients);
    dto.list = patients;
    logEvent(dto, ActivityLog.VIEW_PATIENTS, "AppService getPatients()", null, null);
  }
  
 
 
   public List<Patient> getPatients(PatientDTO dto) throws Exception {
    List<Patient> patients = appDAO.findPatients();
    for (Patient p : patients) { 
      DataEncryptor.decryptPatient(p); 
      ExcludedFields.excludeFields(p);
      ExcludedObjects.excludeObjects(p);
    }
    logEvent(dto, ActivityLog.VIEW_PATIENTS, "AppService getPatients()", null, null);
    return patients;
  }



  public boolean getPatientSearchTypeAheads(AppDTO dto) throws Exception {
    List<Patient> patients = appDAO.findPatients();
    Set<String> firstNames = new TreeSet<String>();
    Set<String> middleNames = new TreeSet<String>();
    Set<String> lastNames = new TreeSet<String>();
    Set<String> mrns = new TreeSet<String>();
    Set<String> cities = new TreeSet<String>();
    Set<String> phoneNumbers = new TreeSet<String>();
    
    for (Patient patient : patients) {
      DataEncryptor.decryptPatient(patient);
      firstNames.add(patient.getFirstName());
      if (patient.getMiddleName() != null) {
        middleNames.add(patient.getMiddleName());
      }
      lastNames.add(patient.getLastName());
      cities.add(patient.getCity());
      if (patient.getPrimaryPhone() != null) {
        phoneNumbers.add(patient.getPrimaryPhone());
      }
      if (patient.getSecondaryPhone() != null) {
        phoneNumbers.add(patient.getSecondaryPhone());
      }
      String mrn = patient.getMrn();
      if (mrn != null) mrns.add(mrn);
    }
    
    dto.patientSearchTypeAheads.put("firstNames", firstNames);
    dto.patientSearchTypeAheads.put("middleNames", middleNames);
    dto.patientSearchTypeAheads.put("lastNames", lastNames);
    dto.patientSearchTypeAheads.put("mrns", mrns);
    dto.patientSearchTypeAheads.put("cities", cities);
    dto.patientSearchTypeAheads.put("phoneNumbers", phoneNumbers);
    return true;
  }



  public PatientService getPatientService() throws Exception {
    return getService(PatientService.class);
  }


  public <R> R getService(Class<R> Klass) throws Exception {
      String className = Klass.getCanonicalName();
      Object instance = services.get(className);
      if (instance == null) {
        Object[] args = null;
        Constructor<?> constructor = null; 
        try {
        constructor =  Klass.getConstructor(AppService.class);
        } catch(NoSuchMethodException e) {}
        if (constructor == null) {
          constructor = Klass.getConstructor();
        } else {
          args = new Object[] {this};
      }
        instance = constructor.newInstance(args);
        services.put(className, instance);
        
      }
      return appDAO.cast(Klass, instance);
  }
  
    
    
  public void getStaticLists(AppDTO dto) throws Exception{
    Map<String,List<?>> map = new HashMap<String,List<?>>();
    map.put("addressTypes", appDAO.findAddressTypes());
    map.put("complaints", appDAO.findComplaints());
    map.put("credentials", appDAO.findCredentials());
    map.put("diagnoses", appDAO.findDiagnoses());
    map.put("gender", appDAO.findGender());
    map.put("maritalStatus", appDAO.findMaritalStatus());
    map.put("programs", appDAO.findPrograms());
    map.put("referralSourceTypes", appDAO.findReferralSourceTypes());
    map.put("roles", appDAO.findRoles());
    map.put("salesLeadStatuses", appDAO.findSalesLeadStatuses());
    map.put("salesLeadStages", appDAO.findSalesLeadStages());
    map.put("salesLeadAgeRanges", appDAO.findSalesLeadAgeRanges());
    map.put("salesLeadSources", appDAO.findSalesLeadSources());
    map.put("salesLeadGoals", appDAO.findSalesLeadGoals());
    map.put("salesLeadEmailStatuses", appDAO.findSalesLeadEmailStatuses());
    map.put("salesLeadCallStatuses", appDAO.findSalesLeadCallStatuses());
    map.put("usStates", appDAO.findUSStates());
    map.put("cptModifiers", appDAO.getCPTModifiers());

    dto.staticLists = map;
    
  }
  
 
  
  public void getSubItems(final AppDTO dto) throws Exception {
    Class<?> formClass = getFormClass(dto);
    dto.list = appDAO.getList(formClass, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.eq(dto.parentIdName, dto.parentId));
      }   
    });
    logEvent(dto, ActivityLog.ADD_ITEM, "AppService getSubItems()", null, null);
  }
  
  
  
  public List<PatientClinician> getUnassignedPatients(AppDTO dto) throws Exception {
    List<PatientClinician> pcs = appDAO.findUnassignedPatients();
    for (PatientClinician pc : pcs) { DataEncryptor.decryptPatient(pc.getPatient()); }
    logEvent(dto, ActivityLog.VIEW_PATIENTS, "AppService getUnassignedPatients()", null, null);
    return pcs;
  }
  
  
 
  public void getUserDashboard(AppDTO dto) throws Exception {
    List<ContactMessage> messages = messageDAO.findContactMessages(); 
    dto.dashboard.put("messages", messages);
  }
  
  public  boolean isValidSession(AppDTO dto, String ipAddress, String path) throws Exception {
    Integer accessLevel = null;
    Integer clientId = null;
    
    appDAO.deleteExpiredClientSessions();
    ClientSession clientSession = appDAO.findClientSessionBySessionId(dto.sessionId);

    if (dto == null || dto.sessionId == null) {
      log.info("isValidSession() no session id submitted by user at ip address of " + ipAddress); 
      return false;
    }
    if (clientSession == null) {   
      log.info("isValidSession() no session found for : " + dto.sessionId); 
      return false;
    }
    if (clientSession.getIpAddress().equals(ipAddress) == false) {
      log.info("isValidSession() submitted IP address is of " + ipAddress + " does not match the one found in current session"); 
      return false;
    }
    
    String clientType = clientSession.getClientType();
    
    if (Client.USER.equals(clientType)) {
      clientId = clientSession.getUser().getId();
      accessLevel = clientSession.getUser().getRoleId();
    }
    else if (Client.PATIENT.equals(clientType)) {
      clientId = clientSession.getPatient().getId();
      accessLevel = Client.PATIENT_ACCESS_LEVEL;
    }
    else if (Client.GUARDIAN.equals(clientType)) {
      clientId = clientSession.getGuardian().getId();
      accessLevel = Client.PATIENT_ACCESS_LEVEL;
    }
    
    if (Permissions.clientPermissionsMap.get(path) != null && accessLevel != null) {
      if (Permissions.clientPermissionsMap.get(path)[accessLevel - 1] == false) {
        log.info("isValidSession() client lacks permission level to execute " + path); 
        return false;
      }
    }
    else {
      if (Permissions.clientPermissionsMap.get(path) == null) {
        log.info("ERROR isValidSession() Path: " + path + " not found"); 
      }
      else if (accessLevel == null) {
        log.info("ERROR isValidSession() access level for " + clientType + " id: " + clientId  + " not found"); 
      }
      return false;
    }
    clientSession.setLastAccessTime(new Date());
    appDAO.update(clientSession);
    return true;
  }
  


  public <T> void loadAssociation(Class<T> Klass, final String foreignKey, String setter, final BaseEntity entity) throws Exception {
   T target = appDAO.findOne(Klass, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.eq(foreignKey, entity.getId()));  
      }
    });
    PropertyUtils.setProperty(entity, setter, target);
  }
  

  public <T> void loadAssociation(Class<T> Klass, final String foreignKey, final Object foreignKeyValue, String setter, final BaseEntity entity) throws Exception {
   T target = appDAO.findOne(Klass, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.eq(foreignKey, foreignKeyValue));  
      }
    });
    PropertyUtils.setProperty(entity, setter, target);
  }


  public <T> void loadList(Class<T> Klass, final String foreignKey, String listSetter, final BaseEntity entity) throws Exception {
    List<T> list = appDAO.getList(Klass, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.eq(foreignKey, entity.getId()));  
      }
    });
    PropertyUtils.setProperty(entity, listSetter, list);
  }
  
  public <T> void loadList(Class<T> Klass, final String foreignKey, final Object foreignKeyValue, String listSetter, final BaseEntity entity) throws Exception {
    List<T> list = appDAO.getList(Klass, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.eq(foreignKey, foreignKeyValue));  
      }
    });
    PropertyUtils.setProperty(entity, listSetter, list);
  }
  
  
  
  public void logEvent(AppDTO dto, String activity, String serviceMethod, String fieldName, String notes) throws Exception {
    ClientSession clientSession = appDAO.findClientSessionBySessionId(dto.sessionId);
    ActivityLog activityLog = new ActivityLog();
    if (clientSession != null) {
      activityLog.setClientId(clientSession.getClientId());
      activityLog.setClientType(clientSession.getClientType());
    }
    activityLog.setTimePerformed(new Date());
    activityLog.setActivity(activity);
    activityLog.setModule(dto.module);
    activityLog.setPatientId(dto.patientId);
    activityLog.setUserId(dto.userId);
    activityLog.setEncounterId(dto.encounterId);
    activityLog.setUserId(dto.userId);
    activityLog.setFieldName(fieldName);
    activityLog.setServiceMethod(serviceMethod);
    activityLog.setNotes(notes);
    appDAO.create(activityLog);
  }
  

  

  public void login(AppDTO dto, HttpServletRequest request) throws Exception {
    if (Statics.PORTAL_MODULE.equals(dto.module)) {
      if (appDAO.findByColumn(Guardian.class, "email", encrypt(dto.username)) != null) {
        dto.clientType = Client.GUARDIAN;
      } else {
        dto.clientType = Client.PATIENT;
      }
    }
    String clientType = dto.clientType; 
    String ipAddress = request.getRemoteHost();
    ClientSession clientSession = new ClientSession();
    clientSession.setClientType(clientType);
    clientSession.setIpAddress(ipAddress);
    clientSession.setLastAccessTime(new Date());
    clientSession.setParked(false);
    Client client = appDAO.authenticateClient(dto.username, dto.password, clientType);
    client.setSessionId(UUID.randomUUID().toString());
    if (client.getAuthStatus() == Client.STATUS_AUTHORIZED) {
      clientSession.setSessionId(client.getSessionId());
      clientSession.setClientId(client.getId());
      if (Client.USER.equals(clientType)) { clientSession.setUser((User)client); }
      else if (Client.PATIENT.equals(clientType)) { clientSession.setPatient((Patient)client); }
      else if (Client.GUARDIAN.equals(clientType)) {
        Guardian guardian = (Guardian)client;
        clientSession.setGuardian(guardian); 
        dto.guardian = guardian;
        dto.sessionId = clientSession.getSessionId();
      }
      appDAO.create(clientSession);
      if (Client.PATIENT.equals(clientType)) {
        DataEncryptor.decryptPatient((Patient)client);
      } else if (Client.GUARDIAN.equals(clientType)) {
        DataEncryptor.decryptGuardian(dto.guardian);
      } 
      logEvent(dto, ActivityLog.LOGIN, "AppService login()", null, null);
    }
    dto.client = client;
  }



  public void logout(AppDTO dto) throws Exception {
    dto.authenticated = false;
    logEvent(dto, ActivityLog.LOGOUT, "AppService logout()", null, null);
    ClientSession clientSession = appDAO.findClientSessionBySessionId(dto.sessionId);
    if (clientSession == null) {
      return;
    }
    appDAO.unparkClientSession(dto.sessionId);
    appDAO.deleteClientSession(dto.sessionId);

  }

  
  
  public RecoveryCode newRecoveryCode() {
    return new RecoveryCode(UUID.randomUUID().toString(), TimeUtil.fromNow(RecoveryCode.RECOVERY_EXPIRATION_WINDOW));
  }
  
  
  
  public static RecoveryCode newActivationCode() {
    return new RecoveryCode(UUID.randomUUID().toString(), TimeUtil.fromNow(RecoveryCode.ACTIVATION_EXPIRATION_WINDOW));
  }
  
  

  public void park(AppDTO dto) throws Exception {
    appDAO.parkClientSession(dto.sessionId);
    logEvent(dto, ActivityLog.PARK_USER, "AppService park()", null, null);
  }
  
  
  
  public void processAddItem(AppDTO dto, BaseEntity formClassInstance) throws Exception {
    if (dto.formClassName.contains("BillingTicketEntry")) {
      IBillingTicketEntry item = (IBillingTicketEntry) formClassInstance;
      item.setBillingTicketId(dto.parentId);
      appDAO.update(formClassInstance);
    }
  }

  public void processUpdateField(AppDTO dto, BaseEntity formClassInstance, String propertyName, Object newValue) throws Exception {
    if (dto.formClassName.equals("com.wdeanmedical.entity.form.InvoiceLineItem")) {
      InvoiceLineItem item = ((InvoiceLineItem)formClassInstance);
      Invoice invoice = patientDAO.findInvoice(item.getInvoiceId());
      calculateInvoiceTotal(invoice);
    }
    appDAO.update(formClassInstance);
  }
  
  
  
  public void saveBillingTicketEntry(AppDTO dto) throws Exception {
    Object item = getFormInstance(dto);
    if (!dto.mode.equals(Statics.CREATE)) {
      item = appDAO.findById(item.getClass(), dto.id);
    }   
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    Date date = null; if (dto.date != null) {try { date = sdf.parse(dto.date); } catch (ParseException pe) {date = null;}}
    String[] properties = {
      "patientId",
      "clinicianId",
      "date",
      "totalTime",
      "copayMethod",
      "dx",
      "service"
    };
    Object[] values = {
        dto.patientId,
        dto.clinicianId,
        date,
        dto.totalTime,
        dto.copayMethod,
        dto.dx,
        dto.service
    };
    Util.setProperties(item, properties, values);
    BaseEntity entity = (BaseEntity) item;
    if (dto.mode.equals(Statics.CREATE)) {
      appDAO.create(entity);
    } else {
      appDAO.update(entity);
    }
    dto.id = entity.getId();
    logEvent(dto, ActivityLog.SAVE_BILLING_TICKET_ENTRY, "AppService saveBillingTicketEntry()", null, null);
  }
  
  
    
  public void sendCredentialsRecovery(AppDTO dto, HttpServletRequest request) throws Exception {
    Map<String,String> customAttributes = new HashMap<String,String>();
    String encryptedEmail = encrypt(dto.email);
    RecoveryCode recoveryCode = newRecoveryCode();
    if (Statics.PORTAL_MODULE.equals(dto.module)) {
      Patient patient = appDAO.findPatientByEmail(encryptedEmail);
      Guardian guardian;
      Client client = null;
      if (patient != null) {
        patient.setPasswordCreated(false);
        recoveryCode.setClientId(patient.getId());
        recoveryCode.setClientType(Client.PATIENT);
        patientDAO.create(recoveryCode);
        patient.setRecoveryCode(recoveryCode);
        appDAO.update(patient);
        DataEncryptor.decryptPatient(patient);
        patient.setGuardian(getPatientService().getPatientGuardian(patient.getId()));
      } else {
	      guardian = appDAO.findByColumn(Guardian.class, "email", encryptedEmail);
	      if (guardian != null) {
	      	guardian.setPasswordCreated(false);
          recoveryCode.setClientId(guardian.getId());
          recoveryCode.setClientType(Client.GUARDIAN);
          appDAO.create(recoveryCode);
          guardian.setRecoveryCode(recoveryCode);
          appDAO.update(guardian);
          client = guardian;
	      }
      } 
      String title = "Password Recovery";
      String linkPath = "/portal?passwordRecovery=true&recoveryCode=" + recoveryCode.getCode();
      MailHandler.sendSystemEmail("patient_credentials_recovery", title, patient, client, request, linkPath, null, null);
      logEvent(dto, ActivityLog.PATIENT_PASSWORD_RESET_REQUEST, "AppService credentialsRecoveryRequest()", null, null);
    } 
    else if (Statics.PM_MODULE.equals(dto.module)) {
      User user = appDAO.findUserByEmail(dto.email);
      if (user != null) {
        user.setPasswordCreated(false);
        recoveryCode.setClientId(user.getId());
        recoveryCode.setClientType(Client.USER);
        patientDAO.create(recoveryCode);
        user.setRecoveryCode(recoveryCode);
        appDAO.update(user);
        customAttributes.put("username", user.getUsername());
        String title = "Password Recovery";
        String linkPath = "/pm?passwordRecovery=true&recoveryCode=" + user.getRecoveryCode().getCode();
        MailHandler.sendSystemEmail("user_credentials_recovery", title, null, user, request, linkPath, customAttributes, null);
        logEvent(dto, ActivityLog.USER_PASSWORD_RESET_REQUEST, "AppService credentialsRecoveryRequest()", null, null);
      }
    } 
  }
 
 
 
  public void sendInvoice(AppDTO dto, HttpServletRequest request) throws Exception {
    Invoice item = appDAO.findById(Invoice.class, dto.id);
    item.setInvoiceStatus(Invoice.STATUS_SENT);
    appDAO.update(item);
    String emailTitle = "New invoice from " + Core.practiceClientProperties.getProperty("app.business_name");
    Map<String,String> customAttributes = new HashMap<String,String>();
    Patient patient = appDAO.findById(Patient.class, dto.patientId);
    DataEncryptor.decryptPatient(patient);
    patient.setGuardian(getPatientService().getPatientGuardian(patient.getId()));
    String patientName = Util.buildFullName(patient.getFirstName(), patient.getMiddleName(), patient.getLastName());
    customAttributes.put("name", patientName);
    MailHandler.sendSystemEmail("invoice", emailTitle, null, null, request, "", customAttributes, patient.getEmail());
    logEvent(dto, ActivityLog.SEND_INVOICE, "AppService sendInvoice()", null, null);
  } 
  
  
  
  public boolean testPassword(String password) {
   if (password.length() < 6) {
    log.info("Submitted password is not at least six characters long");
    return false;
    }
    Pattern lowerCasePattern = Pattern.compile("[a-z]+");
    Matcher lowerCaseMatcher = lowerCasePattern.matcher(password);
        
    Pattern upperCasePattern = Pattern.compile("[A-Z]+");
    Matcher upperCaseMatcher = upperCasePattern.matcher(password);
        
    if (lowerCaseMatcher.find() == false || upperCaseMatcher.find() == false) {
      log.info("Sumitted passwords does not include at least one uppercase and one lowercase letter");
      return false;
    }
          
    Pattern numericPattern = Pattern.compile("\\d+");
    Matcher numericMatcher = numericPattern.matcher(password);
        
    Pattern punctuationPattern = Pattern.compile("\\p{Punct}+");
    Matcher punctuationMatcher = punctuationPattern.matcher(password);
         
    if (numericMatcher.find() == false || punctuationMatcher.find() == false) {
      log.info("Submitted passwords does not include at least one numeric character and one punctuation character");
      return false;
    }
    return true;
  }
  
  
  public void unpark(AppDTO dto, HttpServletRequest request) throws Exception {
    String clientType = dto.clientType; 
    Client client = appDAO.authenticateClient(dto.username, dto.password, clientType);
    if (client.getAuthStatus() == Client.STATUS_AUTHORIZED) {
      appDAO.unparkClientSession(dto.sessionId);
    }
    dto.client = client;
    logEvent(dto, ActivityLog.UNPARK_USER, "AppService unpark()", null, null);
  }


   
  public void updateField(AppDTO dto) throws Exception {
    String updateProperty = dto.updateProperty;
    String[] splitProperty = updateProperty.split("\\.");
    String className = splitProperty[0]; 
    String propertyName = splitProperty[1];
    dto.formClassName = "com.wdeanmedical." + className.replace('-', '.');
    Class<?> fieldClass  = getFormClass(dto);
    BaseEntity classInstance = (BaseEntity)appDAO.findById(fieldClass, dto.id);
    String updatePropertyValue = dto.updatePropertyValue;
    String updatePropertyFieldType = dto.updatePropertyFieldType;
    Object newValue = null;

    // encrypt the field if found in the encrypted properties list
    if (WDMForm.isPHI(propertyName, fieldClass)) { updatePropertyValue = DataEncryptor.encrypt(updatePropertyValue);}
    
    // handle special cases
    if (WDMForm.isNonStringField(updatePropertyFieldType)) { 
      newValue = WDMForm.processNonStringField(propertyName, updatePropertyValue, updatePropertyFieldType, appDAO);
    }
    else { 
      newValue = updatePropertyValue;
    }
    dto.updatePreviousValue = PropertyUtils.getProperty(classInstance, propertyName);
    // find the setter for the form section property and set the form section property value to it. 
    if (newValue == null) {
      PropertyUtils.setProperty(classInstance, propertyName, newValue);
    }
    else {
      BeanUtils.setProperty(classInstance, propertyName, newValue);
    }
    ValidationResult validationResult = classInstance.validateBeforeUpdate(this, propertyName, newValue, dto.id);
    if (validationResult == null || validationResult.isValid) {
      appDAO.update(classInstance);
      Method method = Util.getMethod(WDMFormService.class, "processUpdateField", classInstance.getClass(), AppService.class, AppDTO.class);
      if (method != null) {
        method.invoke(null, classInstance, this, dto);
      } 
      processUpdateField(dto, classInstance, propertyName, newValue);
      dto.result = true;
    } else {
      dto.setError(validationResult);
    }
    logEvent(dto, ActivityLog.UPDATE_FIELD, "AppService updateField()", propertyName, null);
  }



  public void updateFields(AppDTO dto) throws Exception {
    Gson gson = JSONUtils.getGson();  
    for(Object o: dto.list) {
      Updateable updatable = gson.fromJson(gson.toJson(o), Updateable.class);
      dto.updateProperty = updatable.updateProperty;
      dto.updatePropertyFieldType = updatable.updatePropertyFieldType;
      dto.updatePropertyValue = updatable.updatePropertyValue;
      updateField(dto);
    }
  }



  public void updateForm(AppDTO dto) throws Exception {
  	ClientSession clientSession = appDAO.findClientSessionBySessionId(dto.sessionId);
    String clientType = clientSession.getClientType();
    String name = "";
    
    WDMForm formClassInstance = null;
    Class<?> formClass = Class.forName(dto.formClassName);
    formClassInstance = (WDMForm)formClass.newInstance();
    
    if (dto.byPatientId != null && dto.byPatientId == true) {
      formClassInstance = (WDMForm)appDAO.findByPatientId(formClass, dto.patientId);
    }
    else {
      formClassInstance = (WDMForm)appDAO.findById(formClass, dto.id);
    }
    
    if (WDMForm.OPEN.equals(dto.action)) {
      BeanUtils.setProperty(formClassInstance, "closed", false);
      appDAO.update(formClassInstance);
    }
    else if (WDMForm.CLOSE.equals(dto.action)) {
      BeanUtils.setProperty(formClassInstance, "closed", true);
      Method method = Util.getMethod(WDMFormService.class, "formClosed", formClassInstance.getClass(), AppService.class, AppDTO.class);
      if (method != null) {
        method.invoke(null, formClassInstance, this, dto);
      } 
      appDAO.update(formClassInstance);
      if (Client.PATIENT.equals(clientType))  {
        MessageDTO mdto = new MessageDTO();
        mdto.sessionId = dto.sessionId;
        mdto.messageType = MessageType.SUBMITTED_FORM;
        new MessageService().processPatientActionMessage(mdto);
      }
      if (Client.PATIENT.equals(clientType) || Client.GUARDIAN.equals(clientType)) {
        PatientForm patientForm = appDAO.findById(PatientForm.class, dto.patientFormId);
        patientForm.setStatus(PatientForm.SUBMITTED);
        appDAO.update(patientForm);
      }
    }
    else if (WDMForm.DELETE.equals(dto.action)) {
      BeanUtils.setProperty(formClassInstance, "deleted", true);
      appDAO.update(formClassInstance);
    }
    formClassInstance.initialize(this);
    getFormItems(dto, formClassInstance);
    dto.object = formClassInstance;
    name = BeanUtils.getProperty(formClassInstance, "name");
    logEvent(dto, ActivityLog.UPDATE_FORM, "AppService updateForm()", name, dto.action);
  }
  
  
  
  public void updateItem(AppDTO dto) throws Exception {
    String name = "";    
    BaseEntity formClassInstance = null;
    Class<?> formClass = Class.forName(dto.formClassName);
    formClassInstance = (BaseEntity)formClass.newInstance();
    
    if (dto.byPatientId != null && dto.byPatientId == true) {
      formClassInstance = (BaseEntity) appDAO.findByPatientId(formClass, dto.patientId);
    }
    else {
      formClassInstance = (BaseEntity)appDAO.findById(formClass, dto.id);
    }
    appDAO.update(formClassInstance);
    dto.object = formClassInstance;
    name = BeanUtils.getProperty(formClassInstance, "name");
    logEvent(dto, ActivityLog.UPDATE_ITEM, "AppService updateItem()", name, null);
  }
  
  public void updateItemPriority(AppDTO dto) throws Exception {
    appDAO.updateItemPriority(dto.formClassName, dto.id, dto.parentName, dto.parentId, dto.priority, dto.direction);
  }
  
  
  
  public void validateViaRecovery(AppDTO dto, HttpServletRequest request) throws Exception {
    User user = appDAO.authenticateUserViaRecoveryCode(dto.recoveryCode);
    String ipAddress = request.getRemoteHost();
    Integer authStatus = user.getAuthStatus();
    if (authStatus == Client.STATUS_AUTHORIZED) {
      ClientSession clientSession = new ClientSession();
      clientSession.setClientType(Client.USER);
      clientSession.setIpAddress(ipAddress);
      clientSession.setLastAccessTime(new Date());
      clientSession.setParked(false);
      clientSession.setUser(user);
      clientSession.setSessionId(user.getSessionId());
      patientDAO.create(clientSession);
    } 
    dto.userId = user.getId();
    dto.client = user;
    logEvent(dto, ActivityLog.LOGIN, "AppService validateViaRecovery()", null, null);
  }



  public AppDAO getAppDAO() {
    return appDAO;
  }


  
  public void searchCPT(AppDTO dto, HttpServletRequest request) {
    final String searchText = request.getParameter("searchText");
    dto.list = appDAO.getList(CPT.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria criteria) {
         criteria.add(Restrictions.ilike("description", searchText, MatchMode.ANYWHERE));    
      }
    });
  }



  public void searchICD9(final AppDTO dto, HttpServletRequest request) {
    final String searchText = request.getParameter("searchText");
    dto.list = appDAO.getList(ICD9.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.ilike("codeText", searchText, MatchMode.ANYWHERE));    
      }
    });
  }


  protected void updatePassword(IPassword ipassword, AppDTO dto) throws Exception {
    if (ipassword == null) {
      dto.result = false;
      dto.errorMsg = StringUtils.capitalize(dto.clientType) + " Not Found";
      dto.returnCode = RETURN_CODE_INVALID_PASSWORD;
    } else if (testPassword(dto.password) == false) {
      dto.result = false;
      dto.errorMsg = "Insufficient Password";
      dto.returnCode = RETURN_CODE_INVALID_PASSWORD;
    } else {
      String newSalt = UUID.randomUUID().toString();
      String encodedPassword = OneWayPasswordEncoder.getInstance().encode(dto.password, newSalt);
      ipassword.setPassword(encodedPassword);
      ipassword.setSalt(newSalt);
      ipassword.setPasswordCreated(true);
      appDAO.update(ipassword.getBaseEntity());
      dto.returnCode = RETURN_CODE_VALID;
    }  
  }
}
