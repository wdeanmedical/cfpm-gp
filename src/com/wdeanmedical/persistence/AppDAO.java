package com.wdeanmedical.persistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.wdeanmedical.core.Core;
import com.wdeanmedical.core.Statics;
import com.wdeanmedical.entity.AddressType;
import com.wdeanmedical.entity.ApptType;
import com.wdeanmedical.entity.BaseEntity;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.ClientSession;
import com.wdeanmedical.entity.Complaint;
import com.wdeanmedical.entity.Credential;
import com.wdeanmedical.entity.Diagnosis;
import com.wdeanmedical.entity.Facility;
import com.wdeanmedical.entity.Gender;
import com.wdeanmedical.entity.Guardian;
import com.wdeanmedical.entity.MaritalStatus;
import com.wdeanmedical.entity.Message;
import com.wdeanmedical.entity.NetworkMarketingSource;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.PatientClinician;
import com.wdeanmedical.entity.PatientIntake;
import com.wdeanmedical.entity.Program;
import com.wdeanmedical.entity.RecoveryCode;
import com.wdeanmedical.entity.ReferralSourceType;
import com.wdeanmedical.entity.Role;
import com.wdeanmedical.entity.SalesLeadAgeRange;
import com.wdeanmedical.entity.SalesLeadCallStatus;
import com.wdeanmedical.entity.SalesLeadEmailStatus;
import com.wdeanmedical.entity.SalesLeadGoal;
import com.wdeanmedical.entity.SalesLeadSource;
import com.wdeanmedical.entity.SalesLeadStage;
import com.wdeanmedical.entity.SalesLeadStatus;
import com.wdeanmedical.entity.USState;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.entity.UserFacility;
import com.wdeanmedical.entity.UserNetworkMarketingSource;
import com.wdeanmedical.entity.form.HealthIssue;
import com.wdeanmedical.entity.form.MedicalCondition;
import com.wdeanmedical.entity.form.PatientForm;
import com.wdeanmedical.entity.form.PatientMedication;
import com.wdeanmedical.entity.form.PracticeForm;
import com.wdeanmedical.entity.form.PresentingProblem;
import com.wdeanmedical.entity.form.WDMForm;
import com.wdeanmedical.gp.entity.CPTModifier;
import com.wdeanmedical.interfaces.IBillingTicket;
import com.wdeanmedical.interfaces.IBillingTicketEntry;
import com.wdeanmedical.interfaces.ICriteriaTransformer;
import com.wdeanmedical.interfaces.IItemTransformer;
import com.wdeanmedical.interfaces.IListMapper;
import com.wdeanmedical.model.ClientInfo;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.OneWayPasswordEncoder;
import com.wdeanmedical.util.TimeUtil;
import com.wdeanmedical.util.Util;

@Transactional
public class AppDAO extends BaseDAO {

  private static final Logger log = Logger.getLogger(AppDAO.class);

  private SessionFactory sessionFactory;

  public AppDAO() {
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

  public void adjustItemPriorities(String formClassName, Integer priority) throws Exception {
    Session session = this.getSession();
    String hql = "update " + formClassName + " set priority = priority - 1 where priority >= :priority";
    Query query = session.createQuery(hql);
    query.setParameter("priority", priority);
    query.executeUpdate();
  }
  
  public Client authenticateClient(String username, String password, String clientType) throws Exception {
    Session session = this.getSession();
    Client client = null;
    Criteria crit = null;
    String encryptedUsername = DataEncryptor.encrypt(username);

    if (Client.USER.equals(clientType)) { client = findUserByUsername(username); }
    else if (Client.PATIENT.equals(clientType)) { client = findPatientByEmail(encryptedUsername); }
    else if (Client.GUARDIAN.equals(clientType)) { client = findGuardianByEmail(encryptedUsername); }
    if (client == null) {
      client = new Client();
      client.setAuthStatus(Client.STATUS_NOT_FOUND);
      return client;
    }
    if (client.getSalt() == null || client.getPassword() == null) {
        client.setAuthStatus(Client.STATUS_INACTIVE);
       return client;
    }
    String encodedPassword = OneWayPasswordEncoder.getInstance().encode(password, client.getSalt());

    if (Client.USER.equals(clientType)) {
      crit = session.createCriteria(User.class);
      crit.add(Restrictions.eq("username", username));
      crit.add(Restrictions.eq("password", encodedPassword));
      client = (Client) crit.uniqueResult();
    }
    else if (Client.PATIENT.equals(clientType)) {
      crit = session.createCriteria(Patient.class);
      crit.add(Restrictions.eq("email", encryptedUsername));
      crit.add(Restrictions.eq("password", encodedPassword));
      client = (Client) crit.uniqueResult();
    }
    else if (Client.GUARDIAN.equals(clientType)) {
      crit = session.createCriteria(Guardian.class);
      crit.add(Restrictions.eq("email", encryptedUsername));
      crit.add(Restrictions.eq("password", encodedPassword));
      client = (Client) crit.uniqueResult();
    }
    
    if (client != null) {
      client.setAuthStatus(Client.STATUS_AUTHORIZED);
      if (client.getStatus() == Client.INACTIVE) {
        client.setAuthStatus(Client.STATUS_INACTIVE);
      } 
      else {
        DateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy h:mm a");
        client.setPreviousLoginTime(client.getLastLoginTime() != null ? df.format(client.getLastLoginTime().getTime()) : "");
        client.setLastLoginTime(new Date());
        update(client);
      }
    } 
    else {
      client = new Client();
      client.setAuthStatus(Client.STATUS_INVALID_PASSWORD);
    }
    return client;
  }
 
  private Client findGuardianByEmail(String email) throws Exception {
    return findByColumn(Guardian.class, "email", email);
  }

  public User authenticateUserViaRecoveryCode(String code) throws Exception {
    User user = findUserByRecoveryCode(code);
    if (user == null) {
      user = new User();
      user.setAuthStatus(Client.STATUS_NOT_FOUND);
      return user;
    }
    else {
    		RecoveryCode recoveryCode = user.getRecoveryCode();
    	  if (TimeUtil.isBeforeNow(recoveryCode.getExpiresAt())) {
    	  		user.setAuthStatus(Client.STATUS_RECOVERY_CODE_EXPIRED);
    	  } else if (recoveryCode.getRecovered()) {
    	  		user.setAuthStatus(Client.STATUS_RECOVERY_CODE_ALREADY_USED);
    	  } else {
	      user.setAuthStatus(Client.STATUS_AUTHORIZED);
	      if (user.getStatus() == Client.INACTIVE) {
	        user.setAuthStatus(Client.STATUS_INACTIVE);
	      } else if (user.getPasswordCreated() == true && StringUtils.isNotEmpty(user.getEmail())) {
	      		user.setAuthStatus(Client.STATUS_PASSWORD_ALREADY_CREATED);
	      }
	      else {
	        DateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy h:mm a");
	        user.setPreviousLoginTime(user.getLastLoginTime() != null ? df.format(user.getLastLoginTime().getTime()) : "");
	        user.setLastLoginTime(new Date());
	        user.setSessionId(UUID.randomUUID().toString());
	        recoveryCode.setRecovered(true);
	        update(recoveryCode);
	        update(user);
	      }
    	  }  
    }
    return user;
  }

  public Integer checkForOpenForm(String className, Integer patientId, Integer id, String evalMode) throws Exception {
    Integer formId = null;
    Session session = this.getSession();
    Class<?> formClass = Class.forName(className);
    Criteria crit = session.createCriteria(formClass);
    if (id != null) { crit.add(Restrictions.eq("id", id)); }
    if (patientId != null) { crit.add(Restrictions.eq("patientId", patientId)); }
    crit.add(Restrictions.eq("closed", false));
    crit.add(Restrictions.eq("deleted", false));
    if ("com.wdeanmedical.pot.entity.form.OTEval".equals(className)) {
      crit.add(Restrictions.eq("evalMode", evalMode));
    }
    WDMForm form = (WDMForm)crit.uniqueResult();
    if (form != null) {
      formId = form.getId();
    }
    return formId;
  }
  
  public void closeIntakePracticeForms(List<Integer> patientFormIds, Integer patientId) throws Exception {
    if (patientFormIds == null || patientFormIds.isEmpty()) {
      return;
    }
    Session session = this.getSession();
    Criteria crit = session.createCriteria(PracticeForm.class);
    crit.add(Restrictions.in("id", patientFormIds));
    crit.add(Restrictions.eq("formType", PracticeForm.PATIENT_INTAKE));
    crit.addOrder(Order.asc("sortOrder"));    
    for (Object object : crit.list()) {
      PracticeForm practiceForm = (PracticeForm) object;
      Class<?> formClass = Class.forName(practiceForm.getClassName());
      crit = session.createCriteria(PatientForm.class);
      crit.add(Restrictions.eq("practiceFormId", practiceForm.getId()));
      crit.add(Restrictions.eq("patientId", patientId));
      crit.add(Restrictions.eq("intake", true));
      PatientForm patientForm = (PatientForm) crit.uniqueResult();
      if (patientForm != null) {
        WDMForm patientFormInstance = (WDMForm) findById(formClass, patientForm.getPracticeFormInstanceId() );
        patientFormInstance.setClosed(true);
        update(patientFormInstance);
        formClass.cast(patientFormInstance);
        practiceForm.setData(patientFormInstance);
        
      }
    }
  }
    
  public void deleteClientSession(String sessionId) {
    Session session = this.getSession();
    String hql = "delete from ClientSession cs where cs.sessionId = :sessionId";
    Query query = session.createQuery(hql);
    query.setParameter("sessionId", sessionId);
    query.executeUpdate();
  }
  
  public void deleteExpiredClientSessions() throws Exception {
    Session session = getSession(); 
    Calendar timeoutThreshold = Calendar.getInstance();
    timeoutThreshold.add(Calendar.MINUTE, 0 - Core.sessionTimeout);
    Date  expireTime = timeoutThreshold.getTime();
    String hql = "delete from ClientSession cs where cs.parked = false and cs.lastAccessTime < :expireTime";
    Query query = session.createQuery(hql);
    query.setParameter("expireTime", expireTime);
    query.executeUpdate();
  }

  public void deleteInvoiceLineItems(Integer invoiceId) {
    Session session = this.getSession();
    String hql = "delete from InvoiceLineItem i where i.invoiceId = :invoiceId";
    Query query = session.createQuery(hql);
    query.setParameter("invoiceId", invoiceId);
    query.executeUpdate();
  }
  
  public void deletePatientForms(Integer patientId) {
    Session session = this.getSession();
    String hql = "delete from POTPatientForm pf where pf.patientId = :patientId";
    Query query = session.createQuery(hql);
    query.setParameter("patientId", patientId);
    query.executeUpdate();
  }
 
  public List<AddressType> findAddressTypes() throws Exception {
    return getList(AddressType.class);
  } 
  
  public List<ApptType> findApptTypes() throws Exception {
    return getList(ApptType.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.addOrder(Order.asc("sortOrder"));
      }
    });
  } 
      
  public IBillingTicket findBillingTicketByPatientId(Class<? extends IBillingTicket> Klass, Integer patientId) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Klass);
    crit.add(Restrictions.eq("patientId", patientId));
    return cast(Klass, crit.uniqueResult());
  }
  
  public List <IBillingTicketEntry> findBillingTicketEntries(Class<? extends IBillingTicketEntry> Klass, final IBillingTicket billingTicket) throws Exception {
   return findBillingTicketEntries(Klass, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientId", billingTicket.getPatientId()));
        crit.add(Restrictions.eq("clinicianId", billingTicket.getClinicianId()));
      }
    });
  }
  
  public List <IBillingTicketEntry> findBillingTicketEntries(Class<? extends IBillingTicketEntry> Klass, ICriteriaTransformer transformer) throws Exception {
    return getList(Klass, transformer, new IListMapper<IBillingTicketEntry>(){
      @Override
      public IBillingTicketEntry map(Object item) throws Exception {
        return (IBillingTicketEntry)item;
      }
    });
  }
 
  public List <? extends IBillingTicketEntry> findBillingTicketEntries(Class<? extends IBillingTicketEntry> Klass, ICriteriaTransformer transformer, IItemTransformer itemTransformer) throws Exception {
    return getList(Klass, transformer, itemTransformer);
  }
 
  public List<PatientClinician> findClinicianPatients(final User clinician) throws Exception {
    return getList(PatientClinician.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("clinician", clinician));
      }
    });
  }
  
  public List<Complaint> findComplaints() throws Exception {
    return getList(Complaint.class);
  }
 
  public List<Credential> findCredentials() throws Exception {
    return getList(Credential.class);
  }
 
  public List<Diagnosis> findDiagnoses() throws Exception {
    return getList(Diagnosis.class);
  }
     
  public List<Facility> findFacilities() throws Exception {
    return getList(Facility.class);
  }
 
  public List<Patient> findFilteredPatients(final List<Object> filters) throws Exception {
    return getList(Patient.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        PatientDAO.patientFilterCriteriaTransformer(filters, crit);
      }
    });
  }
    
  public List<Gender> findGender() throws Exception {
    return getList(Gender.class);
  }
  
  public Gender findGenderByCode(String code) throws Exception {
    Session session = getSession();
    Criteria crit = session.createCriteria(Gender.class);
    crit.add(Restrictions.eq("code", code));
    return (Gender)crit.uniqueResult();
  }
  
  public List <HealthIssue> findHealthIssues(final Integer patientId) throws Exception {
    return getList(HealthIssue.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientId", patientId));
      }
    });
  }
   
  public List<Object> findIntakePatientFormInstances(List<PracticeForm> practiceForms, Integer patientId) throws Exception {
    PatientForm patientForm = null; 
    List<Object> patientFormInstances = new ArrayList<Object>();
    if (practiceForms == null || practiceForms.isEmpty()) {
      return patientFormInstances;
    }
    
    for (PracticeForm practiceForm : practiceForms) {
      Class<?> formClass = Class.forName(practiceForm.getClassName());
      Session session = this.getSession();
      Criteria crit = session.createCriteria(PatientForm.class);
      crit.add(Restrictions.eq("practiceFormId", practiceForm.getId()));
      crit.add(Restrictions.eq("patientId", patientId));
      patientForm = (PatientForm) crit.uniqueResult();
      if (patientForm != null) {
        BaseEntity patientFormInstance = (BaseEntity) findById(formClass, patientForm.getPracticeFormInstanceId() );
        formClass.cast(patientFormInstance);
        patientFormInstances.add(patientFormInstance);
      }
    }  
    return patientFormInstances;
  }
  
  public List<PracticeForm> findIntakePracticeForms(final List<Integer> patientFormIds, Integer patientId, IItemTransformer itemTransformer) throws Exception {
    if (patientFormIds == null || patientFormIds.isEmpty()) {
      return new ArrayList<PracticeForm>();
    }
    List<PracticeForm> practiceForms = getList(PracticeForm.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.in("id", patientFormIds));
        crit.add(Restrictions.eq("formType", PracticeForm.PATIENT_INTAKE));
        crit.addOrder(Order.asc("sortOrder"));
      }
    }); 
    for (PracticeForm practiceForm : practiceForms) {
      itemTransformer.transform(practiceForm);
    }
    return practiceForms;
  }
  
  public List<String> findIntakePracticeFormNames(final List<Integer> patientFormIds) throws Exception {
    if (patientFormIds == null || patientFormIds.isEmpty()) {
      return new ArrayList<String>();
    }
    return getList(PracticeForm.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.in("id", patientFormIds));
        crit.add(Restrictions.eq("formType", PracticeForm.PATIENT_INTAKE));
        crit.addOrder(Order.asc("sortOrder"));
        crit.setProjection(Projections.property("name"));
      }
    }, String.class);
  }
  
  public BaseEntity findItemByPriority(Class<?> formClass, Integer priority, String parentName, Integer parentId) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(formClass);
    crit.add(Restrictions.eq("priority", priority));
    crit.add(Restrictions.eq(parentName, parentId));
    BaseEntity item = (BaseEntity) crit.uniqueResult();
    return item;
  }
  
  public List<MaritalStatus> findMaritalStatus() throws Exception {
    return getList(MaritalStatus.class);
  }
 
  public List <MedicalCondition> findMedicalConditions(final Integer patientId) throws Exception {
    return getList(MedicalCondition.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientId", patientId));
      }
    });
  }
  
  public List <PatientMedication> findMedications(final Integer patientId) throws Exception {
    return getList(PatientMedication.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientId", patientId));
      }
    });
  }
  
  public List<NetworkMarketingSource> findNetworkMarketingSources() throws Exception {
    return getList(NetworkMarketingSource.class);
  }  
  
  public BaseEntity findOldEntity(BaseEntity newEntity) throws Exception {
    BaseEntity oldEntity = null;
    if (newEntity instanceof Patient) {
      oldEntity = findById(Patient.class, newEntity.getId());
    }
    return oldEntity;
  } 
 
  public List<WDMForm> findOpenedForms(final String className, final Integer patientId, final String evalMode) throws Exception {
    Class <?> formClass = Class.forName(className);
    return getList(formClass, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        if ("com.wdeanmedical.pot.entity.form.OTEval".equals(className)) {
          crit.add(Restrictions.eq("evalMode", evalMode));
        }
        if (patientId != null) { crit.add(Restrictions.eq("patientId", patientId)); }
        crit.add(Restrictions.eq("closed", false));
        crit.add(Restrictions.eq("deleted", false));
      }
    }, WDMForm.class);
  }
   
  public Patient findPatientByEmail(String username) throws Exception {
    return findByColumn(Patient.class, "email", username);
  }
   
  public List<PatientClinician> findPatientClinicians(final Patient patient) throws Exception {
    return getList(PatientClinician.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patient", patient));
      }
    });
  }

  public List<PatientClinician> findPatientCliniciansByClinician(final User clinician) throws Exception {
    return getList(PatientClinician.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("clinician", clinician));
      }
    });
  }
  
  public PatientForm findPatientFormWithPracticeFormName(String formName, Integer patientId, Integer practiceFormId) {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(PatientForm.class);
    crit.add(Restrictions.eq("patientId", patientId));
    crit.add(Restrictions.eq("practiceFormId", practiceFormId));
    return (PatientForm)crit.uniqueResult();
  }    
   
  public List<ClientInfo> findPatientInfo() throws Exception {
    return getList(Patient.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.ne("status", Client.DELETED));
      }
    }, new IListMapper<ClientInfo>() {
      @Override
      public <T> ClientInfo map(T item) throws Exception {
        Patient patient = (Patient) item;
        ClientInfo info = new ClientInfo();
        info.clientId = patient.getId();
        info.clientType = Client.PATIENT;
        String patientName = Util.buildFullName(DataEncryptor.decrypt(patient.getFirstName()), DataEncryptor.decrypt(patient.getMiddleName()), DataEncryptor.decrypt(patient.getLastName()));
        info.fullName = patientName;
        return info;
      }
    });
  }

  public PatientIntake findPatientIntakeByPatientId(Integer patientId)throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(PatientIntake.class);
    crit.add(Restrictions.eq("patientId", patientId));
    return (PatientIntake)crit.uniqueResult();
  }
  
  public List<Integer> findPatientFormPracticeFormIds(final Integer patientId, final Boolean intake) throws Exception {
    return getList(PatientForm.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientId", patientId));
        crit.setProjection(Projections.property("practiceFormId"));
        if (intake != null) {
          crit.add(Restrictions.eq("intake", intake));
        }
      }
    }, Integer.class);
  }
 
  public List<PatientForm> findPatientFormPracticeForms(final Integer patientId) throws Exception {
    return getList(PatientForm.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientId", patientId));
      }
    });
  }
  
  public List<PatientMedication> findPatientMedications(final Patient patient) throws Exception {
    return getList(PatientMedication.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patient", patient));
      }
    });
  }
  
  public List<Patient> findPatients() throws Exception {
    return getList(Patient.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.ne("status", Client.DELETED));
      }
    });
  }
  
  public List<Message> findPatientToClinicianMessages() throws Exception {
    return getList(Message.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.addOrder(Order.desc("date"));
      }
    });
  }

  public PracticeForm findPracticeFormByName(String name) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(PracticeForm.class);
    crit.add(Restrictions.eq("name", name));
    PracticeForm item = (PracticeForm) crit.uniqueResult();
    return item;
  }
  
  public List<Integer> findPracticeFormInstanceIds(final Integer patientId) throws Exception {
    return getList(Message.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientId", patientId));
        crit.setProjection(Projections.property("practiceFormInstanceId"));
      }
    }, Integer.class);
  }
  
  public List <PresentingProblem> findPresentingProblems(final Integer patientId) throws Exception {
    return getList(PresentingProblem.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientId", patientId));
      }
    });
  }
  
  public List<Program> findPrograms() throws Exception {
    return getList(Program.class);
  }
 
  public List<ReferralSourceType> findReferralSourceTypes() throws Exception {
    return getList(ReferralSourceType.class);
  }

  public List<Role> findRoles() throws Exception {
    return getList(Role.class);
  }
  
  public List<SalesLeadAgeRange> findSalesLeadAgeRanges() throws Exception {
    return getList(SalesLeadAgeRange.class);
  }
    
  public List<SalesLeadCallStatus> findSalesLeadCallStatuses() throws Exception {
    return getList(SalesLeadCallStatus.class);
  }
  
  public List<SalesLeadEmailStatus> findSalesLeadEmailStatuses() throws Exception {
    return getList(SalesLeadEmailStatus.class);
  }
  
  public List<SalesLeadGoal> findSalesLeadGoals() throws Exception {
    return getList(SalesLeadGoal.class);
  }
  
  public List<SalesLeadSource> findSalesLeadSources() throws Exception {
    return getList(SalesLeadSource.class);
  }  

  public List<SalesLeadStatus> findSalesLeadStatuses() throws Exception {
    return getList(SalesLeadStatus.class);
  }
 
  public List<SalesLeadStage> findSalesLeadStages() throws Exception {
    return getList(SalesLeadStage.class);
  }
  
  public List<PatientClinician> findUnassignedPatients() throws Exception {
    Session session = this.getSession();
    String hql = "from PatientClinician pc where pc.clinician = null";
    Query query = session.createQuery(hql);
    List<PatientClinician> list = query.list();
    return list; 
  }
  
  public List<UserFacility> findUserFacilities() throws Exception {
    List<UserFacility> list = getList(UserFacility.class);
    List<UserFacility> filteredList =  new ArrayList<UserFacility>();
    for ( UserFacility uf : list) {  
      User user = this.findById(User.class, uf.getUserId());
      if (user != null && user.getStatus() == Client.ACTIVE) {
        filteredList.add(uf);
      }
    }
    return filteredList;
  }
  
  public List<UserFacility> findUserFacilitiesByUserId(final Integer userId) throws Exception {
    return getList(UserFacility.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("userId", userId));
      }
    });
  }
 
  public List<ClientInfo> findUserInfo() throws Exception {
    List<User> list = getList(User.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.ne("status", Client.DELETED));
      }
    });
    List<ClientInfo> userInfo =  new ArrayList<ClientInfo>();
    for (User item : list) {
      ClientInfo info = new ClientInfo();
      info.clientId = item.getId();
      info.clientType = Client.USER;
      String userName = Util.buildFullName(item.getFirstName(), item.getMiddleName(), item.getLastName());
      info.fullName = userName;
      userInfo.add(info);
    }
    return userInfo;
  }
        
  
  public List<Message> findUserMessages(User user) throws Exception {
    return getList(Message.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.addOrder(Order.desc("date"));
      }
    });
  }
 
  public List<UserNetworkMarketingSource> findUserNetworkMarketingSourceByUserId(final Integer userId) throws Exception {
    return getList(UserNetworkMarketingSource.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("userId", userId));
      }
    });
  }
  
  public User findUserByEmail(String email) throws Exception {
    Session session = this.getSession();
    User user = null;
    Criteria crit = session.createCriteria(User.class);
    crit.add(Restrictions.eq("email", email));
    user = (User) crit.uniqueResult();
    return user;
  }
  
  public User findUserByRecoveryCode(String code) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(User.class);
    crit.createAlias("recoveryCode", "recoveryCode");
    crit.add(Restrictions.eq("recoveryCode.code", code));
    crit.add(Restrictions.eq("recoveryCode.clientType", Client.USER));
    User user = (User) crit.uniqueResult();
    return user;
  }
  
  public User findUserBySessionId(String sessionId) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(ClientSession.class);
    crit.add(Restrictions.eq("sessionId", sessionId));
    ClientSession clientSession = (ClientSession) crit.uniqueResult();
    return this.findById(User.class, clientSession.getUser().getId());
  } 
 
  public User findUserByUsername(String username) throws Exception {
    Session session = this.getSession();
    User user = null;
    Criteria crit = session.createCriteria(User.class);
    crit.add(Restrictions.eq("username", username));
    user = (User) crit.uniqueResult();
    return user;
  }
  
  public List<User> findUsers(final String userType) throws Exception {
    return getList(User.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.ne("status", Client.DELETED));
        crit.add(Restrictions.eq("userType", userType));
      }
    });
  }
  
  public List<USState> findUSStates() throws Exception {
    return getList(USState.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.addOrder(Order.asc("name"));
      }
    });
  }
  
  public void parkClientSession(String sessionId)  throws Exception {
    ClientSession clientSession = findClientSessionBySessionId(sessionId);
    clientSession.setParked(true);
    update(clientSession);
  }
  
  public void unparkClientSession(String sessionId)  throws Exception {
    ClientSession clientSession = findClientSessionBySessionId(sessionId);
    clientSession.setParked(false);
    update(clientSession);
  }
  
  public void updateItemPriority(String formClassName, Integer id, String parentName, Integer parentId, Integer priority, String direction) throws Exception {
    Class<?> formClass = Class.forName(formClassName);
    BaseEntity currentItem;
    BaseEntity nextItem;
    
    List<?> list = getList(formClass);
    
    //  if only one row, stop
    if (list.size() == 1) {
      return;
    }
    // if priority is 1 and higher priority is requested, stop
    if (priority == 1 && Statics.INCREASE.equals(direction))  {
      return;
    }
    // if priority is the size of the list and lower prioirity is requested, stop
    if (priority == list.size() && Statics.DECREASE.equals(direction))  {
      return;
    }
    
    currentItem = (BaseEntity)findById(formClass, id);
    
    Session session = this.getSession();
    // swap this item's priority with the priority (higher or lower) that it is going to replace.
    if (Statics.INCREASE.equals(direction)) {
      nextItem = findItemByPriority(formClass, priority - 1, parentName, parentId);
    }
    else {
      nextItem = findItemByPriority(formClass, priority + 1, parentName, parentId);
    }
    
    Integer currentPriority = new Integer(BeanUtils.getProperty(currentItem, "priority"));
    Integer nextPriority = new Integer(BeanUtils.getProperty(nextItem, "priority"));
    BeanUtils.setProperty(currentItem, "priority", nextPriority);
    BeanUtils.setProperty(nextItem, "priority", currentPriority);
    
    session.update(currentItem);
    session.update(nextItem);
  }
 
  public List<?> getCPTModifiers() {
    return getList(CPTModifier.class);
  }
}
