package com.wdeanmedical.service;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.wdeanmedical.core.Core;
import com.wdeanmedical.core.ExcludedFields;
import com.wdeanmedical.core.ExcludedObjects;
import com.wdeanmedical.core.Statics;
import com.wdeanmedical.dto.PatientDTO;
import com.wdeanmedical.entity.ActivityLog;
import com.wdeanmedical.entity.BaseEntity;
import com.wdeanmedical.entity.CalendarEvent;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.ClientSession;
import com.wdeanmedical.entity.Guardian;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.PatientClinician;
import com.wdeanmedical.entity.PatientGuardian;
import com.wdeanmedical.entity.PatientIntake;
import com.wdeanmedical.entity.RecoveryCode;
import com.wdeanmedical.entity.USState;
import com.wdeanmedical.entity.form.HealthIssue;
import com.wdeanmedical.entity.form.Invoice;
import com.wdeanmedical.entity.form.InvoiceLineItem;
import com.wdeanmedical.entity.form.MedicalCondition;
import com.wdeanmedical.entity.form.PatientForm;
import com.wdeanmedical.entity.form.PatientMedication;
import com.wdeanmedical.entity.form.PracticeForm;
import com.wdeanmedical.entity.form.PresentingProblem;
import com.wdeanmedical.entity.form.WDMForm;
import com.wdeanmedical.factory.GuardianFactory;
import com.wdeanmedical.factory.PatientFactory;
import com.wdeanmedical.interfaces.ICriteriaTransformer;
import com.wdeanmedical.interfaces.IFilterable;
import com.wdeanmedical.interfaces.IItemTransformer;
import com.wdeanmedical.interfaces.IListMapper;
import com.wdeanmedical.model.BodyAnnotation;
import com.wdeanmedical.model.PatientPersonalInfo;
import com.wdeanmedical.model.PatientRecentActivity;
import com.wdeanmedical.model.SearchCriteria;
import com.wdeanmedical.persistence.PatientDAO;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.MailHandler;
import com.wdeanmedical.util.OneWayPasswordEncoder;
import com.wdeanmedical.util.PatientComparator;
import com.wdeanmedical.util.Util;

public class PatientService extends AppService {

  private static Log log = LogFactory.getLog(PatientService.class);
    
  protected PatientDAO patientDAO;
  public static int RECENT_PATIENT_SIZE = 5;


  public PatientService() throws MalformedURLException {
    super();
    patientDAO = (PatientDAO) wac.getBean("patientDAO");
  }
  
  public <T> T buildNewClient(Client client, Class<T> Klass) {
    client.setFirstName("");
    client.setLastName("");
    client.setPassword("not a password");
    client.setUsername(""); 
    return Klass.cast(client);
  }


  public void addHealthIssue(PatientDTO dto) throws Exception {
    dto.object = addHealthIssue(dto.patientId);
  }
  
  

  public HealthIssue addHealthIssue(Integer patientId) throws Exception {
    HealthIssue item = new HealthIssue();
    item.setPatientId(patientId);
    appDAO.create(item);
    return item;
  }


  public void addMedicalCondition(PatientDTO dto) throws Exception {
    MedicalCondition item = new MedicalCondition();
    item.setPatientId(dto.patientId);
    appDAO.create(item);
    dto.object = item;
  }


  public void addPresentingProblem(PatientDTO dto) throws Exception {
    dto.id = addPresentingProblem(dto.patientId);
  }



  public Integer addPresentingProblem(Integer patientId) throws Exception {
    PresentingProblem item = new PresentingProblem();
    item.setPatientId(patientId);
    appDAO.create(item);
    return item.getId();
  }

  
  public Boolean closePatientIntake(PatientDTO dto, HttpServletRequest request)
      throws Exception {
    Integer patientId = dto.id;
    Patient patient = appDAO.findById(Patient.class, patientId);
    patient.setIntakeClosed(true);
    appDAO.update(patient);

    List<Integer> practiceFormIds = appDAO.findPatientFormPracticeFormIds(patientId, true);
    appDAO.closeIntakePracticeForms(practiceFormIds, patientId);

    DataEncryptor.decryptPatient(patient);
    String patientFullName = patient.getFirstName() + " " + patient.getLastName();
    String title = patientFullName + " completed patient intake";
    patient.setGuardian(getPatientService().getPatientGuardian(patient.getId()));
    MailHandler.sendSystemEmail("portal_signup_notification", title, patient, null, request, null, null, null);
    title = patientFullName + ", welcome to " + Core.practiceClientProperties.getProperty("app.business_name");
    MailHandler.sendSystemEmail("portal_signup_confirmation", title, patient, null, request, "/portal", null, null);
    logEvent(dto, ActivityLog.CLOSE_PATIENT_INTAKE, "PatientService closePatientIntake()", null, null);
    return true;
  }
  
  public boolean createPassword(PatientDTO dto, HttpServletRequest request) throws Exception {
    Patient patient = appDAO.findById(Patient.class, dto.id);
    Boolean driversLicenseValid = new Boolean(true);
    Boolean ssnValid = new Boolean(true);

    String submittedGovtId = dto.govtId;
    String govtId = DataEncryptor.decrypt(patient.getGovtId());

    if (submittedGovtId.length() < 4 || !govtId.substring(govtId.length() - 4, govtId.length()).equals( submittedGovtId)) {
      ssnValid = false;
    }

    String submittedDriversLicense = dto.driversLicense;
    String driversLicense = DataEncryptor.decrypt(patient.getDriversLicense());
    if (submittedDriversLicense.length() < 4 || !driversLicense.substring(driversLicense.length() - 4, driversLicense.length()).equals(submittedDriversLicense)) {
      driversLicenseValid = false;
    }
    if (new Boolean(Core.practiceAppProperties.getProperty("app.security_questions")) == true) {
      if (driversLicenseValid == false && ssnValid == false) {
        dto.result = false;
        dto.errorMsg = "Incorrect Drivers License or SSN";
        dto.returnCode = RETURN_CODE_INVALID_SSN_OR_DL;
        return false;
      }

      if (testPassword(dto.password) == false) {
        dto.result = false;
        dto.errorMsg = "Insufficient Password";
        dto.returnCode = RETURN_CODE_INVALID_PASSWORD;
        return false;
      }
    }

    String newSalt = UUID.randomUUID().toString();
    String encodedPassword = OneWayPasswordEncoder.getInstance().encode(dto.password, newSalt);
    patient.setPassword(encodedPassword);
    patient.setSalt(newSalt);
    patient.setPasswordCreated(true);
    patientDAO.update(patient);
    dto.returnCode = RETURN_CODE_VALID;

    DataEncryptor.decryptPatient(patient);
    String patientFullName = patient.getFirstName() + " " + patient.getLastName();
    String title = patientFullName + ", welcome to " + Core.practiceClientProperties.getProperty("app.business_name");
    patient.setGuardian(getPatientService().getPatientGuardian(patient.getId()));
    MailHandler.sendSystemEmail("password_creation_confirmation", title, patient, null, request, "", null, null);
    return true;
  }
  
  
  
  public void createPatientInvoice(PatientDTO dto) throws Exception {
    Invoice invoice = new Invoice();
    invoice.setPatientId(dto.patientId);
    invoice.setIssueDate(new Date());
    Calendar cal = Calendar.getInstance();
    cal.setTime(invoice.getIssueDate());
    cal.add(Calendar.DATE, 30);
    invoice.setDueDate(cal.getTime());
    invoice.setInvoiceStatus(Invoice.STATUS_NEW);
    ClientSession clientSession = appDAO.findClientSessionBySessionId(dto.sessionId);
    invoice.setUserId(clientSession.getClientId());
    patientDAO.create(invoice);
    Integer invoiceNumber = invoice.getId() + 10000;
    invoice.setInvoiceNumber(invoiceNumber.toString());
    patientDAO.update(invoice);

    InvoiceLineItem item = new InvoiceLineItem();
    item.setInvoiceId(invoice.getId());
    item.setPrice((float) 0);
    appDAO.create(item);

    dto.id = invoice.getId();
  }



  public void createPatientForm(PatientDTO dto, HttpServletRequest request)
      throws Exception {
    PracticeForm practiceForm = appDAO.findById(PracticeForm.class, dto.practiceFormId);
    Patient patient = appDAO.findById(Patient.class, dto.id);
    PatientForm patientForm = new PatientForm();
    patientForm.setPracticeFormId(dto.practiceFormId);
    patientForm.setStatus(PatientForm.ASSIGNED);
    patientForm.setPatientId(dto.id);
    Integer practiceFormInstanceId = savePracticeFormInstance(practiceForm, patient);
    patientForm.setPracticeFormInstanceId(practiceFormInstanceId);
    DataEncryptor.decryptPatient(patient);
    
    patient.setGuardian(getPatientService().getPatientGuardian(patient.getId()));

    appDAO.create(patientForm);
    String title = "New form from " + Core.practiceClientProperties.getProperty("app.business_name");
    MailHandler.sendSystemEmail("portal_new_form", title, patient, null, request, "/portal", null, null);
    logEvent(dto, ActivityLog.CREATE_PATIENT_FORM, "PatientService createPatientForm()", practiceForm.getName(), null);
  }


  public void deleteHealthIssue(PatientDTO dto) throws Exception {
    HealthIssue item = appDAO.findById(HealthIssue.class, dto.id);
    appDAO.delete(item);
  }


  public void deleteMedicalCondition(PatientDTO dto) throws Exception {
    MedicalCondition medicalCondition = appDAO.findById(MedicalCondition.class, dto.id);
    appDAO.delete(medicalCondition);
  }

  public List<PatientGuardian> getPatientPatientGuardians(final Integer patientId){
    return patientDAO.getList(PatientGuardian.class, new ICriteriaTransformer() {

      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.eq("patientId", patientId));
      }
      
    });
  }

  public void deletePatient(PatientDTO dto) throws Exception {
    Patient patient = appDAO.findById(Patient.class, dto.id);
    if (patient == null) return;
    String patientName = Util.buildFullName(patient.getFirstName(), patient.getMiddleName(), patient.getLastName());
    patientDAO.deleteAll(
        getPatientPatientGuardians(dto.id),
        PatientGuardian.class
    );
    if (patient.getStatus().equals(Client.INACTIVE)) {
      patientDAO.delete(patient);
    } else {
     patient.setStatus(Client.DELETED);
     patientDAO.update(patient);
    }
    logEvent(dto, ActivityLog.DELETE_PATIENT, "PatientService deletePatient()", patientName, null);
  }



  public void deletePatientMedication(PatientDTO dto) throws Exception {
    PatientMedication patientMedication = appDAO.findById(PatientMedication.class, dto.id);
    appDAO.delete(patientMedication);
    logEvent(dto, ActivityLog.DELETE_PATIENT_MEDICATION, "PatientService deletePatientMedication()", null, null);
  }



  public void deletePresentingProblem(PatientDTO dto) throws Exception {
    PresentingProblem item = appDAO.findById(PresentingProblem.class, dto.id);
    appDAO.delete(item);
  }


  public void encryptPatients(PatientDTO dto) throws Exception {
    List<Patient> ps = appDAO.findPatients();
    for (Patient p : ps) {
      DataEncryptor.encryptPatient(p);
    }
  }

  private PatientRecentActivity getRecentActivity(Patient patient) {
    CalendarEvent last = calendarDAO.getLastEvent(patient);
    CalendarEvent upcoming = calendarDAO.getUpcomingEvent(patient);
    PatientRecentActivity recentActivity = new PatientRecentActivity();
    recentActivity.lastAppointment = last;
    recentActivity.nextAppointment = upcoming;
    return recentActivity;
  }
  
  public void getClientInfo(PatientDTO dto) throws Exception {
    Patient patient = patientDAO.findById(Patient.class, dto.id);
    DataEncryptor.decryptPatient(patient);
    Guardian guardian = getPatientGuardian(patient.getId());
    patient.setGuardian(guardian);
    if (patient != null) {
      dto.recentActivity = getRecentActivity(patient);
      PatientPersonalInfo personalInfo = null;
      personalInfo = new PatientPersonalInfo(patient);
      dto.personalInfo = personalInfo; 
    }
  }
  
  
  
  protected List<Object> getPatientFilters(PatientDTO dto) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    Date dobFilter = null;
    if (dto.dobFilter != null) {
      try {
        dobFilter = sdf.parse(dto.dobFilter);
      } 
      catch (ParseException pe) {
        dobFilter = null;
      }
    }
    String firstNameFilter = dto.firstNameFilter;
    String middleNameFilter = dto.middleNameFilter;
    String lastNameFilter = dto.lastNameFilter;
    String mrnFilter = dto.mrnFilter;
    String cityFilter = dto.cityFilter;
    String phoneFilter = dto.phoneFilter;
    String encryptedFirstNameFilter = encrypt(firstNameFilter);
    String encryptedMiddleNameFilter = encrypt(middleNameFilter);
    String encryptedLastNameFilter = encrypt(lastNameFilter);
    String encryptedCityFilter = encrypt(cityFilter);
    String encryptedPhoneFilter = encrypt(phoneFilter);
    Integer statusFilter;
    try {
      statusFilter = new Integer(dto.statusFilter);
    } 
    catch (NumberFormatException nfe) {
      statusFilter = null;
    }
    
    List<Object> filters = Arrays.asList(
        (Object) encryptedFirstNameFilter, 
        encryptedMiddleNameFilter,
        encryptedLastNameFilter, 
        mrnFilter, 
        encryptedCityFilter,
        dto.genderFilter, 
        dobFilter, 
        encryptedPhoneFilter, 
        statusFilter
    );
    return filters;
  }
  
  
  
  public void getFilteredPatients(PatientDTO dto) throws Exception {
    List<Patient> patients = appDAO.findFilteredPatients(getPatientFilters(dto));
    
    for (Patient p : patients) {
      DataEncryptor.decryptPatient(p);
      ExcludedFields.excludeFields(p);
      ExcludedObjects.excludeObjects(p);
    }
    
    Collections.sort(patients, new PatientComparator());
    dto.list = patients;
    logEvent(dto, ActivityLog.VIEW_PATIENTS, "PatientService getFilteredPatients()", null, null);
  }
  
  

  public void getForms(PatientDTO dto) throws Exception {
    Integer id = dto.id;
    List<PatientForm> patientForms = patientDAO.findPatientForms(id, dto.intake);
    for (PatientForm patientForm : patientForms) {
      patientForm.setPracticeForm(appDAO.findById(PracticeForm.class, patientForm.getPracticeFormId()));
    }
    dto.list = patientForms;
  }
  
  

  public void getGuardianLastNameTypeAheads(PatientDTO dto) throws Exception {
    List<String> lastNames = patientDAO.findGuardianLastNames();
    List<String> decryptedLastNames = new ArrayList<String>();
    for (String lastName : lastNames) {
      decryptedLastNames.add(DataEncryptor.decrypt(lastName));
    }
    dto.list = decryptedLastNames;
  }
  
  

  public void getGuardians(final PatientDTO dto) throws Exception {
    List<Guardian> guardians = patientDAO.getList(Guardian.class);
    List<PatientGuardian> pgs = patientDAO.getList(PatientGuardian.class, new ICriteriaTransformer() {
      @Override
      public void transform(Criteria criteria) {
       criteria.add(Restrictions.eq("patientId", dto.patientId));        
      } 
    });
    Map<Integer, String> relations = new HashMap<Integer, String>();
    for(PatientGuardian pg: pgs) {
      relations.put(pg.getGuardian().getId(), pg.getRelationship());
    }
    decryptGuardians(relations, guardians);
    dto.list = guardians;
  }
  
  public void decryptGuardians(Map<Integer, String> relations, List<Guardian> guardians) throws Exception {
    for (Guardian g : guardians) {
      DataEncryptor.decryptGuardian(g);
      g.setRelation(relations.get(g.getId()));  
    }
  }

  public void getInvoices(PatientDTO dto) throws Exception {
    List<Invoice> items = patientDAO.findPatientInvoices();
    for (Invoice item : items) {
      Patient patient = appDAO.findById(Patient.class, item.getPatientId());
      if (patient != null) {
        String patientName = Util.buildFullName(
          DataEncryptor.decrypt(patient.getFirstName()),
          DataEncryptor.decrypt(patient.getMiddleName()),
          DataEncryptor.decrypt(patient.getLastName()));
        item.setPatientName(patientName);
      }
    }
    dto.list = items;
  }

  public Integer getNextPatientId() throws Exception {
    return patientDAO.findNextOrdinal(Patient.class, "id");
  }
  
  public void getNextPatientId(PatientDTO dto) throws Exception {
    dto.id = getNextPatientId();
  }
  
  protected void setPatientInfo(Patient patient) throws Exception {
  }
  
  public Patient getPatient(Integer patientId) throws Exception {
    Patient patient = patientDAO.findById(Patient.class, patientId);
    setPatientInfo(patient);
    return patient;
  }
  
  private Patient getPatientUnEncrypted(Integer patientId) throws Exception {
    Patient patient = getPatient(patientId);
    DataEncryptor.decryptPatient(patient);
    Guardian guardian = getPatientGuardian(patient.getId());
    DataEncryptor.decryptGuardian(guardian);
    patient.setGuardian(guardian);
    patientDAO.evict(guardian);
    return patient;
  }
  
  public void getPatient(PatientDTO dto) throws Exception {
    Patient patient = getPatientUnEncrypted(dto.id);
    patient.setRecentActivity(getRecentActivity(patient));
    dto.patient = patient;
    String patientName = patient.getFullName();
    logEvent(dto, ActivityLog.VIEW_PATIENT, "PatientService getPatient()", patientName, null);
  }



  public void getPatientClinicians(PatientDTO dto) throws Exception {
    Patient patient = appDAO.findById(Patient.class, dto.id);
    List<PatientClinician> patientClinicians = patientDAO.findPatientClinicians(patient);
    for (PatientClinician patientClinician : patientClinicians) {
      ExcludedObjects.excludeObjects(patientClinician.getPatient());
    }
    dto.list = patientClinicians;
  }
  
  
  public Guardian getPatientGuardian(final Integer patientId) throws Exception {
    return patientDAO.getGuardian(appDAO.findOne(PatientGuardian.class, new ICriteriaTransformer() {
      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.eq("patientId",patientId));
        criteria.add(Restrictions.eq("isPrimary",true));        
      }
    }));  
  }  

  public void getPracticeFormInstance(PatientDTO dto) throws Exception {
    Class<?> formClass = Class.forName(dto.className);
    BaseEntity practiceFormInstance = (BaseEntity) appDAO.findById(formClass, dto.id);
    if (dto.markAsRead == true) {
      PatientForm patientForm = appDAO.findById(PatientForm.class, dto.patientFormId);
      if (patientForm.getStatus().equals(PatientForm.ASSIGNED)) {
        patientForm.setStatus(PatientForm.IN_PROGRESS);
        appDAO.update(patientForm);
      }
    }
    WDMForm form = (WDMForm) practiceFormInstance;
    form.initialize(this);
    dto.object = practiceFormInstance;
  }



  private ICriteriaTransformer getSearchCriteria(IFilterable filterable, PatientDTO dto) {
   if (dto.criteria != null) {
     return new SearchCriteria(dto.criteria);
   }
   return filterable.searchCriteria();
  }
  
  
  
  public void getPracticeFormInstances(PatientDTO dto) throws Exception {
    Class<? extends WDMForm> formClass = Class.forName(dto.formClassName).asSubclass(WDMForm.class);
    WDMForm instance = formClass.newInstance();
    ICriteriaTransformer searchCriteria = getSearchCriteria(instance, dto);
    
    if (searchCriteria != null) { 
      dto.list = patientDAO.findFormInstancesByPatientIdWithSearchCriteria(dto.id, formClass, searchCriteria);
    } 
    else {
      dto.list = patientDAO.findFormInstancesByPatientId(dto.id, formClass);
    }
    
    for (Object item : dto.list) {
      WDMForm form = (WDMForm) item;
      form.initialize(this);
    }
  }



  public void getPatientInfo(PatientDTO dto) throws Exception {
    getPatient(dto);
    Patient patient = dto.patient;
    Integer patientId = patient.getId();
    PatientIntake patientIntake = patientDAO.findPatientIntakeByPatientId(patientId);
    List<Integer> formIds = appDAO.findPatientFormPracticeFormIds(patientId, true);
    List<String> formNames = appDAO.findIntakePracticeFormNames(formIds);
    patient.setForms(StringUtils.join(formNames, ","));
    
    if (patientIntake != null) {
      dto.patientIntakeId = patientIntake.getId();
      patient.setPortalInviteDate(patientIntake.getPortalInviteDate());
    }
    dto.patient = patient;
  }



  protected List<PracticeForm> findIntakePracticeForms(List<Integer> patientFormIds, final Integer patientId, final AppService appService) throws Exception {
    return appDAO.findIntakePracticeForms(patientFormIds, patientId, new IItemTransformer(){
      @Override
      public <T> void transform(T item) throws Exception {
        final PracticeForm practiceForm = (PracticeForm)item;
        Class<?> formClass = Class.forName(practiceForm.getClassName());
        PatientForm patientForm = patientDAO.findOne(PatientForm.class, new ICriteriaTransformer(){
          @Override
          public void transform(Criteria crit) {
            crit.add(Restrictions.eq("practiceFormId", practiceForm.getId()));
            crit.add(Restrictions.eq("patientId", patientId));
            crit.add(Restrictions.eq("intake", true));
          }
        });
        if (patientForm != null) {
          BaseEntity practiceFormInstance = (BaseEntity) patientDAO.findById(formClass, patientForm.getPracticeFormInstanceId() );
          practiceForm.setData(practiceFormInstance);
          WDMForm form = (WDMForm) practiceFormInstance;
          form.initialize(appService);
        }
      } 
    });
  } 
  
  
  
  public void getPatientIntake(PatientDTO dto) throws Exception {
    PatientIntake patientIntake = patientDAO.findPatientIntakeByPatientId(dto.id);
    if (patientIntake == null) {
      patientIntake = new PatientIntake();
      patientIntake.setPatientId(dto.id);
    }
    List<Integer> formIds = appDAO.findPatientFormPracticeFormIds(patientIntake.getPatientId(), true);
    List<String> formNames = appDAO.findIntakePracticeFormNames(formIds);
    patientIntake.setForms(StringUtils.join(formNames, ","));
    patientIntake.setPracticeForms(findIntakePracticeForms(formIds, patientIntake.getPatientId(), this) );
    dto.object = patientIntake;    
    logEvent(dto, ActivityLog.GET_PATIENT_INTAKE, "PatientService getPatientIntake()", null, null);
  }
  


  public List<PatientForm> getPatientIntakeForms(final Integer patientId) throws Exception {
    return appDAO.getList(PatientForm.class, new ICriteriaTransformer() {
      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.eq("intake", true));
        criteria.add(Restrictions.eq("patientId", patientId));
      }
    });
  }
  
  

  public void getPatientInvoice(PatientDTO dto) throws Exception {
    Invoice invoice = patientDAO.findInvoice(dto.id);
    Patient patient = appDAO.findById(Patient.class, new Integer(invoice.getPatientId()));
    if (patient != null) {
      String patientName = Util.buildFullName(
        DataEncryptor.decrypt(patient.getFirstName()),
        DataEncryptor.decrypt(patient.getMiddleName()),
        DataEncryptor.decrypt(patient.getLastName())
      );
      invoice.setPatientName(patientName);
      invoice.setStreetAddress1(DataEncryptor.decrypt(patient.getStreetAddress1()));
      invoice.setCity(DataEncryptor.decrypt(patient.getCity()));
      invoice.setUsState(patient.getUsState());
      invoice.setPostalCode(DataEncryptor.decrypt(patient.getPostalCode()));
      invoice.setPrimaryPhone(DataEncryptor.decrypt(patient.getPrimaryPhone()));
    }
    dto.object = invoice;
  }



  public void getPatientInvoices(PatientDTO dto) throws Exception {
    dto.list = patientDAO.findPatientInvoicesByPatientId(dto.patientId);
  }
  
  

  public void getPatientProfileImage(PatientDTO dto,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String patientId = request.getParameter("patientId");
    String filesHomePatientDirPath = Core.filesHome + Core.patientDirPath + "/" + patientId + "/";
    Patient patient = appDAO.findById(Patient.class, new Integer(patientId));
    if (patient.getProfileImagePath() == null) {
      getFile(request, response, request.getServletContext(), Core.appBaseDir + "/assets/images/", Core.headshotPlaceholder);
    } 
    else {
      try {
        getFile(request, response, request.getServletContext(), filesHomePatientDirPath, patient.getProfileImagePath());
      } 
      catch (Exception e) {
        log.info("Patient profile image " + patient.getProfileImagePath() + " not found in file system");
        getFile(request, response, request.getServletContext(), Core.appBaseDir + "assets/images/", Core.headshotPlaceholder);
      }
    }
  }
  
  

  public void getPracticeForms(PatientDTO dto) throws Exception {
    dto.list = patientDAO.findPracticeForms();
  }
  
  
  
  protected List<PracticeForm> getPracticeForms(List<PatientForm> patientForms) throws Exception {
    List<PracticeForm> forms;
    Set<Integer> ids = new HashSet<Integer>();
    for (PatientForm patientForm : patientForms) {
      ids.add(patientForm.getPracticeFormId());
    }
    List<Integer> list = new ArrayList<Integer>(ids);
    forms = appDAO.findAllByIds(list, PracticeForm.class);
    return forms;
  }
  
  
  
  public void getRecentPatients(PatientDTO dto) throws Exception {
    List<Patient> patients = patientDAO.findRecentPatients(RECENT_PATIENT_SIZE);
    for (Patient p : patients) {
      DataEncryptor.decryptPatient(p);
    }
    dto.list = patients;
  }



  public void getTempPatientProfileImage(PatientDTO dto, HttpServletRequest request, HttpServletResponse response) throws Exception {
    String profileImagePath = request.getParameter("profileImagePath");
    getFile(request, response, request.getServletContext(), Core.filesHome + Core.tmpDir + "/", profileImagePath);
  }



  public void loadPatientFieldsIntoPatientForm(Integer patientId, Class<?> Klass, WDMForm entity) throws Exception {
    String className = Klass.getCanonicalName();
    Patient patient = getPatientUnEncrypted(patientId);
    String patientName = patient.getFullName();
    patientDAO.evict(patient);
  }
  
 
  public static PatientGuardian getPatientPrimaryGuardian(PatientDAO dao, final Integer patientId) {
    return dao.findOne(PatientGuardian.class, new ICriteriaTransformer() {

      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.eq("isPrimary", true));
        criteria.add(Restrictions.eq("patientId", patientId));
      }
      
    });
  }

  public static void addPatientPatientGuardian(PatientDAO dao, Integer patientId, Guardian guardian, Boolean isPrimary) throws Exception {
    PatientGuardian pg = new PatientGuardian();
    pg.setPatientId(patientId);
    pg.setGuardian(guardian);
    pg.setIsPrimary(isPrimary);
    pg.setRelationship(guardian.getRelation());
    dao.create(pg);
  }
  
  public static void setPatientPrimaryGuardian(PatientDAO dao, Integer patientId, Guardian guardian) throws Exception {
     PatientGuardian existing = getPatientPrimaryGuardian(dao, patientId);
     Boolean existingIsNullOrDifferent = existing == null;
     if (existing != null) {
       existing.setIsPrimary(false); 
      if (guardian != null) {
        existingIsNullOrDifferent = existing.getGuardian().getId() != guardian.getId();
      }
      if (guardian == null || existingIsNullOrDifferent) dao.update(existing);
     }
     if (guardian == null) {
       return;
     }     
     if (existingIsNullOrDifferent) {
      PatientGuardian existingPg = dao.findPatientPatientGuardian(patientId, guardian);
      if (existingPg != null) {
        existingPg.setIsPrimary(true);
        dao.update(existingPg);
      } else {
       addPatientPatientGuardian(dao, patientId, guardian, true);
      }
     }
  }
  
  public void saveNewGuardian(PatientDTO dto, HttpServletRequest request) throws Exception {
    Guardian guardian = new GuardianFactory(appDAO).createNewGuardian(dto);
    if( guardian == null) {
      return;
    }
    guardian.setRelation(dto.relation);
    if (dto.patientId != null) {
      setPatientPrimaryGuardian(patientDAO, dto.patientId, guardian); 
    }

    dto.id = guardian.getId();
    logEvent(dto, ActivityLog.CREATE_GUARDIAN, "PatientService saveNewGuardian()", null, null);
  }

  private Integer savePracticeFormInstance(PracticeForm practiceForm, Patient patient) throws Exception {
    Class<?> formClass = Class.forName(practiceForm.getClassName());
    WDMForm practiceFormInstance = (WDMForm) formClass.newInstance();
    practiceFormInstance.setPatientId(patient.getId());
    BaseEntity baseEntity = appDAO.create(practiceFormInstance);
    return baseEntity.getId();
  }


  
  public void saveNewPatient(PatientDTO dto, HttpServletRequest request) throws Exception {
    Patient patient= new PatientFactory(patientDAO).createNewPatient(dto);        
    if (patient != null) {
     savePatientIntakeForms(patient, dto);
     logEvent(dto, ActivityLog.CREATE_PATIENT, "PatientService saveNewPatient()", null, null);
     if (dto.sendPortalInvite == true) {
      sendPortalInvitation(dto, request);
     }
    }
  }
  
  public void savePatientIntakeForms(Patient patient, PatientDTO dto) throws Exception {
    Integer patientId = patient.getId();
    
    List<PatientForm> existingIntakeForms = getPatientIntakeForms(patientId);
    Map<Integer, PatientForm> intakeFormsIdsToDelete = new HashMap<Integer, PatientForm>();
    
    for(PatientForm existingForm: existingIntakeForms) {
     intakeFormsIdsToDelete.put(existingForm.getPracticeFormId(), existingForm); 
    }
        
    if (StringUtils.isEmpty(dto.forms) == false) {
      Object[] forms = new HashSet<String>(Arrays.asList(dto.forms.split(","))).toArray();
      Boolean shouldNotCreatePracticeFormInstance;  
      Integer practiceFormId;
      for (Object name : forms) {
        PracticeForm practiceForm = appDAO.findPracticeFormByName((String)name);
        shouldNotCreatePracticeFormInstance = practiceForm == null;  
        if (practiceForm != null) {
          practiceFormId = practiceForm.getId();
          if(shouldNotCreatePracticeFormInstance=intakeFormsIdsToDelete.containsKey(practiceFormId)) {
            intakeFormsIdsToDelete.remove(practiceFormId);
          }          
        }      
        if (shouldNotCreatePracticeFormInstance) continue;
        PatientForm patientForm = new PatientForm();
        if (dto.patientFormId != null) {
          patientForm.setParentPatientFormId(dto.patientFormId);
        }
        patientForm.setIntake(true);
        patientForm.setPatientId(patientId);
        patientForm.setPracticeFormId(practiceForm.getId());
        Integer practiceFormInstanceId = savePracticeFormInstance(practiceForm, patient);
        patientForm.setPracticeFormInstanceId(practiceFormInstanceId);
        appDAO.create(patientForm);
      }
    }
    List<PatientForm> unused = new ArrayList<PatientForm>(intakeFormsIdsToDelete.values());
    patientDAO.deleteAll(unused, PatientForm.class);
  }



  public void searchGuardian(PatientDTO dto) throws Exception {
    List<Guardian> guardians = patientDAO.searchGuardian(dto.searchText);
    for (Guardian g : guardians) {
      DataEncryptor.decryptGuardian(g);
    }
    dto.list = guardians;
    logEvent(dto, ActivityLog.VIEW_GUARDIANS, "PatientService searchGuardian()", null, null);
  }



  public void sendPortalInvitation(PatientDTO dto, HttpServletRequest request) throws Exception {
    Patient patient = appDAO.findById(Patient.class, dto.id);
    PatientIntake patientIntake = appDAO.findPatientIntakeByPatientId(dto.id);
    Date portalInviteDate = new Date();
    if (patientIntake != null) {
      patientIntake.setPortalInviteDate(portalInviteDate);
      appDAO.update(patientIntake);
    }
    patient.setPortalInviteDate(portalInviteDate);
    RecoveryCode recoveryCode = patient.getActivationCode();
    if (recoveryCode == null || recoveryCode.isInvalid()) {
      setActivationCode(patient);
    }
    appDAO.update(patient);
    DataEncryptor.decryptPatient(patient);
    String patientName = Util.buildFullName(patient.getFirstName(), patient.getMiddleName(), patient.getLastName());
    String title = "Patient Portal Invitation for  " + patientName;
   
    String linkPath = "/portal?activateUser=true&activationCode=" + patient.getActivationCode().getCode();
    dto.portalInviteDate = patient.getPortalInviteDate();
    
    Guardian guardian = getPatientService().getPatientGuardian(patient.getId());
    DataEncryptor.decryptGuardian(guardian);
    patient.setGuardian(guardian);
    MailHandler.sendSystemEmail("portal_invitation", title, patient, null, request, linkPath, null, null);
    logEvent(dto, ActivityLog.SEND_PORTAL_INVITATION, "PatientService sendPortalInvitation()", patientName, null);
  }
  
  
  
  public static void setActivationCode(Patient patient) {
    patient.setActivationCode(newActivationCode());
  }
  


  private void setClientSession(PatientDTO dto, HttpServletRequest request) throws Exception {
    String ipAddress = request.getRemoteHost();
    Client client = (Client) dto.client; 
    Integer authStatus = dto.authStatus;
    if (client != null) {
        authStatus = client.getAuthStatus();
    }
    if (authStatus == Client.STATUS_AUTHORIZED) {
      ClientSession clientSession = new ClientSession();
      clientSession.setSessionId(client.getSessionId());
      clientSession.setClientType(dto.clientType);
      if (dto.clientType == Client.PATIENT) {
        clientSession.setPatient((Patient) client);
      } 
      else {
        clientSession.setGuardian((Guardian) client);
      }
      clientSession.setIpAddress(ipAddress);
      clientSession.setLastAccessTime(new Date());
      clientSession.setParked(false);
      patientDAO.create(clientSession);
      dto.clientType = clientSession.getClientType();
      dto.sessionId = clientSession.getSessionId();
     
      if (dto.clientType == Client.PATIENT) {
          dto.patientId = client.getId();
        DataEncryptor.decryptPatient((Patient) client);
      } 
      else {
        dto.guardian = (Guardian) client;
        DataEncryptor.decryptGuardian(dto.guardian);
      }
      dto.authStatus = authStatus;
    }   
  }


  
  public void updateDxCode(PatientDTO dto) throws Exception {
      getGPPatientService().updateDxCode(dto);
  }
  
  

  public void updatePassword(PatientDTO dto) throws Exception {
    Patient patient = patientDAO.findById(Patient.class, dto.id);
    Guardian guardian = getPatientGuardian(patient.getId());
    if (guardian != null) {
      updatePassword(guardian, dto);
    } 
    else {
      updatePassword(patient, dto);
    }
  }
  
  public void updatePatientGuardian(PatientDTO dto) throws Exception {
    Guardian guardian = null;
    if(dto.id != null) {
    	guardian=appDAO.findById(Guardian.class, dto.id);
    }    
    setPatientPrimaryGuardian(patientDAO, dto.patientId, guardian);    
    logEvent(dto, ActivityLog.UPDATE_GUARDIAN, "PatientService updatePatientGuardian()", null, null);
  }

  public void updatePatientInfoForms(PatientDTO dto) throws Exception {
    String[] formNames = dto.forms.split(",");
    List<PatientForm> patientForms = appDAO .findPatientFormPracticeForms(dto.patientId);

    // delete any patient forms whose related practice form is not found in the
    // new forms list string
    for (PatientForm patientForm : patientForms) {
      PracticeForm practiceForm = appDAO.findById(PracticeForm.class, patientForm.getPracticeFormId());
      if (ArrayUtils.contains(formNames, practiceForm.getName()) == false) {
        appDAO.delete(patientForm);
      }
    }

    // Add any patient forms that are in the new forms list string but are not
    // in patient forms
    if (StringUtils.isEmpty(dto.forms)) {
      return;
    }
    for (String formName : formNames) {
      PracticeForm practiceForm = appDAO.findPracticeFormByName(formName);
      if (appDAO.findPatientFormWithPracticeFormName(formName, dto.patientId, practiceForm.getId()) == null) {
        PatientForm patientForm = new PatientForm();
        patientForm.setPatientId(dto.patientId);
        patientForm.setPracticeFormId(practiceForm.getId());
        Class<?> formClass = Class.forName(practiceForm.getClassName());
        BaseEntity baseEntity = appDAO.create((BaseEntity) formClass.newInstance());
        patientForm.setPracticeFormInstanceId(baseEntity.getId());
        appDAO.create(patientForm);
      }
    }
    logEvent(dto, ActivityLog.UPDATE_PATIENT_INFO_FORMS, "PatientService updatePatientInfoForms()", null, null);

  }



  public void updateTxCode(PatientDTO dto) throws Exception {
    getGPPatientService().updateTxCode(dto); 
  }
  
  

  public void validateFromOffice(PatientDTO dto, HttpServletRequest request) throws Exception {
    Patient patient = null;
    ClientSession clientSession = appDAO.findClientSessionBySessionId(dto.sessionId);
    if (clientSession != null) {
      patient = clientSession.getPatient();
      String newSessionId = UUID.randomUUID().toString();
      patient.setSessionId(newSessionId);
      patientDAO.update(patient);
      clientSession.setSessionId(newSessionId);
      patientDAO.update(clientSession);
      dto.patientId = patient.getId();
      DataEncryptor.decryptPatient(patient);
      patient.setAuthStatus(Client.STATUS_AUTHORIZED);
    }
    dto.client = patient;
    logEvent(dto, ActivityLog.LOGIN, "PatientService validateFromOffice()", null, null);
  }
  
  
  
  public void validateViaActivation(PatientDTO dto, HttpServletRequest request) throws Exception {
    patientDAO.authenticatePatientViaActivationCode(dto, dto.activationCode, this);
    setClientSession(dto, request);
    logEvent(dto, ActivityLog.LOGIN, "PatientService validateViaActivation()", null, null);
  }
  
  

  public void validateViaRecovery(PatientDTO dto, HttpServletRequest request) throws Exception {
    patientDAO.authenticatePatientViaRecoveryCode(dto, dto.recoveryCode, this);
    setClientSession(dto, request);
    logEvent(dto, ActivityLog.LOGIN, "PatientService validateViaRecovery()", null, null);
  }


  public void updatePatientClinician(Integer patientId, Integer assignedClinicianId, Integer previousClinicianId) throws Exception {
     patientDAO.createPatientClinician(getPatient(patientId), assignedClinicianId, previousClinicianId);    
  }

  public void updatePatientPrimaryGuardian(Integer patientId, Integer guardianId) throws Exception {
    Guardian guardian = patientDAO.findById(Guardian.class, guardianId);
    setPatientPrimaryGuardian(patientDAO, patientId, guardian);
  }

  public void createNewPatient(PatientDTO dto, HttpServletRequest request) throws Exception {
    Patient patient = buildNewClient(new Patient(), Patient.class);   
    patientDAO.create(patient);
    dto.id=patient.getId();
    dto.patient=patient;
  }

  public void updatePatientGuardianRelation(Guardian guardian, Integer patientId) throws Exception {
     PatientGuardian pg = patientDAO.findPatientPatientGuardian(patientId, guardian);
     pg.setRelationship(guardian.getRelation());
     patientDAO.update(pg);
  }

}
