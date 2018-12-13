package com.wdeanmedical.persistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.wdeanmedical.core.Statics;
import com.wdeanmedical.dto.PatientDTO;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.ClientSession;
import com.wdeanmedical.entity.Complaint;
import com.wdeanmedical.entity.Country;
import com.wdeanmedical.entity.Diagnosis;
import com.wdeanmedical.entity.Gender;
import com.wdeanmedical.entity.Guardian;
import com.wdeanmedical.entity.Letter;
import com.wdeanmedical.entity.MaritalStatus;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.PatientClinician;
import com.wdeanmedical.entity.PatientComplaint;
import com.wdeanmedical.entity.PatientGuardian;
import com.wdeanmedical.entity.PatientImage;
import com.wdeanmedical.entity.PatientIntake;
import com.wdeanmedical.entity.RecoveryCode;
import com.wdeanmedical.entity.USState;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.entity.form.Invoice;
import com.wdeanmedical.entity.form.InvoiceLineItem;
import com.wdeanmedical.entity.form.MedicalCondition;
import com.wdeanmedical.entity.form.PatientFoodAllergy;
import com.wdeanmedical.entity.form.PatientForm;
import com.wdeanmedical.entity.form.PatientLab;
import com.wdeanmedical.entity.form.PatientMedAllergy;
import com.wdeanmedical.entity.form.PatientMedication;
import com.wdeanmedical.entity.form.PatientSupplement;
import com.wdeanmedical.entity.form.PracticeForm;
import com.wdeanmedical.entity.form.WDMForm;
import com.wdeanmedical.interfaces.ICriteriaTransformer;
import com.wdeanmedical.interfaces.IListMapper;
import com.wdeanmedical.service.PatientService;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.TimeUtil;
import com.wdeanmedical.util.Util;

@Transactional
public class PatientDAO extends BaseDAO {

  private static final Logger log = Logger.getLogger(PatientDAO.class);

  private SessionFactory sessionFactory;

  public PatientDAO() {
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



  public void authenticateClientVia(PatientDTO dto, Client authenticated, String clientType, PatientService patientService, Integer recoveryMode) throws Exception {    
    Optional<Client> optClient = Optional.ofNullable(authenticated);
    dto.clientType = clientType;
    Client client = authenticated;
    Patient patient = null;
    if (!optClient.isPresent()) {
      dto.authStatus = Client.STATUS_NOT_FOUND;
      return;
    }
    else {
      Guardian guardian = null;
      if (clientType == Client.PATIENT) {
        patient = (Patient)authenticated;
        guardian = patientService.getPatientGuardian(patient.getId());
      }
      if (guardian != null) {
        dto.clientType = Client.GUARDIAN;
        client = guardian;
      } 
      RecoveryCode recoveryCode; 
      Boolean isActivation = false;
      if (recoveryMode != null) { isActivation = recoveryMode == Client.RECOVERY_ACTIVATION; } 
      if (isActivation) {
        recoveryCode = patient.getActivationCode();
      } else {
        recoveryCode = client.getRecoveryCode();
      }
      if (TimeUtil.isBeforeNow(recoveryCode.getExpiresAt())) {
        if (isActivation) {
          client.setAuthStatus(Client.STATUS_ACTIVATION_CODE_EXPIRED);
        } else {
          client.setAuthStatus(Client.STATUS_RECOVERY_CODE_EXPIRED);
        }
      } else if (recoveryCode.getRecovered()) {
        if (isActivation) {
          client.setAuthStatus(Client.STATUS_ACTIVATION_CODE_ALREADY_USED);
        } else {
          client.setAuthStatus(Client.STATUS_RECOVERY_CODE_ALREADY_USED);
        }
      } else {
        Boolean inactive = false;   
        Boolean passwordCreated = false; 
        if (client.getStatus() == Client.INACTIVE) {
          client.setAuthStatus(Client.STATUS_INACTIVE);
          inactive = true;
        } else {
          String clientEmail = DataEncryptor.decrypt(client.getEmail());
          Optional<Boolean> optPasswordCreated = Optional.ofNullable(client.getPasswordCreated());
          if (optPasswordCreated.isPresent() && optPasswordCreated.get().booleanValue()
              && StringUtils.isNotEmpty(clientEmail)) {
            passwordCreated = true;
            client.setAuthStatus(Client.STATUS_PASSWORD_ALREADY_CREATED);
          }
        } 
        if (!(passwordCreated || inactive)) {
          client.setAuthStatus(Client.STATUS_AUTHORIZED);
          DateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy h:mm a");
          client.setPreviousLoginTime(client.getLastLoginTime() != null ? df.format(client.getLastLoginTime().getTime()) : "");
          client.setLastLoginTime(new Date());
          client.setSessionId(UUID.randomUUID().toString());
          update(client);
          recoveryCode.setRecovered(true);
          update(recoveryCode);
        }
      }
    }     
    dto.client = client;
  } 



  public void authenticatePatientViaActivationCode(PatientDTO dto, String activationCode, PatientService patientService) throws Exception {
    authenticateClientVia(dto, findPatientByActivationCode(activationCode), Client.PATIENT, patientService, Client.RECOVERY_ACTIVATION);
  }



  public void authenticatePatientViaRecoveryCode(PatientDTO dto, String recoveryCode, PatientService patientService) throws Exception {
    String clientType = Client.PATIENT;
    Patient patient = findByRecoveryCode(Patient.class, clientType, recoveryCode);
    Client client = patient;
    if (patient == null) {
      clientType = Client.GUARDIAN;
      client = findByRecoveryCode(Guardian.class, clientType, recoveryCode);
    }
    authenticateClientVia(dto, client, clientType, patientService, null);
  }


  public void closePatientOTProgs(Integer patientId) {
    Session session = this.getSession();
    String hql = "update OTProg prog set closed = true where prog.patientId = :patientId";
    Query query = session.createQuery(hql);
    query.setParameter("patientId", patientId);
    query.executeUpdate();
  }



  public void closePatientOTTxs(Integer patientId) {
    Session session = this.getSession();
    String hql = "update OTTx tx set closed = true where tx.patientId = :patientId";
    Query query = session.createQuery(hql);
    query.setParameter("patientId", patientId);
    query.executeUpdate();
  }


  public void deletePatientForm(Integer id) throws Exception {
    Session session = this.getSession();

    String hql = "delete from POTPatientForm pf where pf.id = :id";
    Query query = session.createQuery(hql);
    query.setParameter("id", id);
    query.executeUpdate();

    hql = "delete from PatientMedAllergy p where p.patientFormId = :id";
    query = session.createQuery(hql);
    query.setParameter("id", id);
    query.executeUpdate();

    hql = "delete from PatientFoodAllergy p where p.patientFormId = :id";
    query = session.createQuery(hql);
    query.setParameter("id", id);
    query.executeUpdate();

    hql = "delete from Evaluation e where e.patientFormId = :id";
    query = session.createQuery(hql);
    query.setParameter("id", id);
    query.executeUpdate();

    hql = "delete from Treatment t where t.patientFormId = :id";
    query = session.createQuery(hql);
    query.setParameter("id", id);
    query.executeUpdate();
  }



  public void deleteSubtestsByTestId(Integer testId) throws Exception {
    Session session = this.getSession();
    String hql = "delete from EvalSubtest st where st.evalTestId = :testId";
    Query query = session.createQuery(hql);
    query.setParameter("testId", testId);
    query.executeUpdate();
  }



  public void deleteTxNoteComplaintSliders(Integer txNoteComplaintId) throws Exception {
    Session session = this.getSession();
    String hql = "delete from TxNoteComplaintSlider s where s.txNoteCompliantId = :txNoteComplaintId";
    Query query = session.createQuery(hql);
    query.setParameter("txNoteComplaintId", txNoteComplaintId);
    query.executeUpdate();
  }


  public <T> T findByRecoveryCode(Class<T> Klass, String clientType, String code) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Klass);
    crit.createAlias("recoveryCode", "recoveryCode");
    crit.add(Restrictions.eq("recoveryCode.code", code));
    crit.add(Restrictions.eq("recoveryCode.clientType", clientType));
    return cast(Klass, crit.uniqueResult());
  }



  public List<PatientClinician> findClinicianPatients(final User clinician) throws Exception {
    return getList(PatientClinician.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("clinician", clinician));
      }
    });
  }



  public Complaint findComplaintByName(String name) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Complaint.class);
    crit.add(Restrictions.eq("name", name));
    Complaint complaint = (Complaint) crit.uniqueResult();
    return complaint;
  }


  public Country findCountryByName(String name) throws Exception {
    Session session = getSession();
    Criteria crit = session.createCriteria(Country.class);
    crit.add(Restrictions.eq("name", name));
    return (Country)crit.uniqueResult();
  }  



  public Diagnosis findDiagnosisByName(String name) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Diagnosis.class);
    crit.add(Restrictions.eq("name", name));
    Diagnosis diagnosis = (Diagnosis) crit.uniqueResult();
    return diagnosis;
  }


  public List<?> findFormInstancesByPatientId(Integer patientId, Class<? extends WDMForm> formClass) {
    return getList(formClass, getPracticeFormPatientCriteriaTransformer(patientId));
  }



  private ICriteriaTransformer getPracticeFormPatientCriteriaTransformer(final Integer patientId) {
    return  new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientId", patientId));
        crit.addOrder(Order.desc("createdDate"));
        crit.addOrder(Order.desc("date"));
        crit.addOrder(Order.asc("closed"));
      }
    };
  }



  public List<?> findFormInstancesByPatientIdWithSearchCriteria(Integer patientId, Class<? extends WDMForm> formClass, ICriteriaTransformer searchCriteria) {
    return getList(formClass, getPracticeFormPatientCriteriaTransformer(patientId), searchCriteria);
  }


  public Gender findGenderByCode(String code) throws Exception {
    Session session = getSession();
    Criteria crit = session.createCriteria(Gender.class);
    crit.add(Restrictions.eq("code", code));
    return (Gender)crit.uniqueResult();
  }

  public List<String> findGuardianLastNames() throws Exception {
    return getList(Guardian.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.setProjection(Projections.property("lastName"));     
      }
    }, String.class);
  }

  public List<Guardian> getPatientGuardians(final Integer patientId) throws Exception {
    return getList(PatientGuardian.class, new ICriteriaTransformer() {
      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.eq("patientId",patientId));
      }
    }, new IListMapper<Guardian>() {

      @Override
      public <T> Guardian map(T item) throws Exception {
        PatientGuardian pg = (PatientGuardian)item;
        Guardian guardian = pg.getGuardian();
        guardian.setRelation(pg.getRelationship());
        return guardian;
      }
    });  
  }  
  
  public PatientGuardian findPatientPatientGuardian(final Integer patientId, final Guardian guardian) {
    return findOne(PatientGuardian.class, new ICriteriaTransformer() {
      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.eq("patientId",patientId));
        criteria.createAlias("guardian", "guardian");
        criteria.add(Restrictions.eq("guardian.id",guardian.getId()));
      }
    });  
  }
  
  public List<Guardian> findGuardians() throws Exception {
    return getList(Guardian.class);
  }

  public List<Guardian> findGuardians(ICriteriaTransformer transformer) throws Exception {
    return getList(Guardian.class, transformer);
  }

  public Invoice findInvoice(Integer id) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Invoice.class);
    crit.add(Restrictions.eq("id", id));
    Invoice invoice = (Invoice) crit.uniqueResult();
    crit = session.createCriteria(InvoiceLineItem.class);
    crit.add(Restrictions.eq("invoiceId", invoice.getId()));
    List<InvoiceLineItem> list =  crit.list();
    invoice.setInvoiceLineItemList(list); 
    return invoice;
  }


  public List<Letter> findLetters(final Integer patientId) throws Exception {
    return getList(Letter.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("recipientId", patientId));
        crit.add(Restrictions.eq("recipientClientType", Client.PATIENT));

      }
    });
  }

  public MaritalStatus findMaritalStatusByCode(String code) throws Exception {
    Session session = getSession();
    Criteria crit = session.createCriteria(MaritalStatus.class);
    crit.add(Restrictions.eq("code", code));
    return (MaritalStatus)crit.uniqueResult();
  }

  public Integer findNextOrdinal(Class<?> formClass, String property) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(formClass);
    ProjectionList proj = Projections.projectionList();
    proj = proj.add(Projections.max(property));
    crit = crit.setProjection(proj);
    Integer id = (Integer)crit.uniqueResult();
    return (id == null ? 1 : id + 1);
  }


  public PatientComplaint findOrCreatePatientComplaint(Integer patientId, Integer complaintId) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(PatientComplaint.class);
    crit.add(Restrictions.eq("patientId", patientId));
    crit.add(Restrictions.eq("complaintId", complaintId));
    PatientComplaint item = (PatientComplaint) crit.uniqueResult();
    if (item == null) {
      item = new PatientComplaint();
      item.setPatientId(patientId);
      item.setComplaintId(complaintId);
      create(item);
    } 
    return item;
  }



  public Patient findPatientByActivationCode(String code) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Patient.class);
    crit.createAlias("activationCode", "activationCode");
    crit.add(Restrictions.eq("activationCode.code", code));
    Patient patient = (Patient) crit.uniqueResult();
    return patient;  
  } 



  public Patient findPatientByMrn(String mrn) throws Exception {
    Session session = getSession();
    Criteria crit = session.createCriteria(Patient.class);
    crit.add(Restrictions.eq("mrn", mrn));
    Patient patient = (Patient)crit.uniqueResult();
    return patient;
  }



  public Patient findPatientBySessionId(String sessionId) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(ClientSession.class);
    crit.add(Restrictions.eq("sessionId", sessionId));
    ClientSession clientSession = (ClientSession) crit.uniqueResult();
    return this.findById(Patient.class, clientSession.getPatient().getId());
  }



  public List<PatientClinician> findPatientClinicians(final Patient patient) throws Exception {
    return getList(PatientClinician.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patient", patient));
      }
    });
  }



  public List<PatientFoodAllergy> findPatientFoodAllergiesByPatientFormId(final Integer patientFormId) throws Exception {
    return getList(PatientFoodAllergy.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientFormId", patientFormId));
      }
    });
  }



  public List<PatientForm> findPatientForms(final Integer patientId, final Boolean intake) throws Exception {
    return getList(PatientForm.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientId", patientId));
        crit.add(Restrictions.eq("intake", intake));
        crit.addOrder(Order.desc("createdDate"));
      }
    });
  }



  public PatientIntake findPatientIntakeByPatientId(Integer patientId) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(PatientIntake.class);
    crit.add(Restrictions.eq("patientId", patientId));
    PatientIntake patientIntake = (PatientIntake) crit.uniqueResult();
    return patientIntake;
  }



  public List<Invoice> findPatientInvoices() throws Exception {
    return getList(Invoice.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.addOrder(Order.desc("issueDate"));
      }
    });
  }



  public List<Invoice> findPatientInvoicesByPatientId(final Integer patientId) throws Exception {
    return getList(Invoice.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientId", patientId));
        crit.addOrder(Order.desc("issueDate"));
      }
    });
  }



  public List<PatientMedication> findPatientMedicationsByPatientFormId(final Integer patientFormId) throws Exception {
    return getList(PatientMedication.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientFormId", patientFormId));
      }
    });
  }   



  public List<PatientMedAllergy> findPatientMedAllergiesByPatientFormId(final Integer patientFormId) throws Exception {
    return getList(PatientMedAllergy.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientFormId", patientFormId));
      }
    });
  } 



   private PatientClinician getPatientClinician(final Patient patient, Integer clinicianId) throws Exception {
     final User clinician = findById(User.class, clinicianId);
     return findOne(PatientClinician.class, new ICriteriaTransformer() {
       @Override
       public void transform(Criteria criteria) {
        criteria.createAlias("patient", "patient");
        criteria.createAlias("clinician", "clinician");
        criteria.add(Restrictions.eq("patient", patient));  
        criteria.add(Restrictions.eq("clinician", clinician));        
      }    
    });
  }
  public void createPatientClinician(final Patient patient, Integer clinicianId, Integer previousClinicianId) throws Exception {
    User clinician = (User)findClientById(Client.USER, clinicianId);
    if (previousClinicianId != null) {
      PatientClinician previous = getPatientClinician(patient, previousClinicianId);
      if(previous!=null) {
        delete(previous);
      }
    }
    PatientClinician patientClinician = getPatientClinician(patient, clinicianId);
    if (patientClinician == null) {
      patientClinician = new PatientClinician(patient, clinician);
    } else {
      patientClinician.setClinician(clinician);
    }
    saveOrUpdate(patientClinician);
  }


  public List<PracticeForm> findPracticeForms() throws Exception {
    return getList(PracticeForm.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.ne("formType", PracticeForm.BUILT_IN));
        crit.addOrder(Order.asc("title"));
      }
    });
  }


  public List<Patient> findRecentPatients(final int limit) throws Exception {
    return getList(Patient.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.ne("status", Client.DELETED));
        crit.addOrder(Order.desc("lastAccessed"));
        crit.setMaxResults(limit);
      }
    });
  }


  public USState findUSStateByName(String name) throws Exception {
    Session session = getSession();
    Criteria crit = session.createCriteria(USState.class);
    crit.add(Restrictions.eq("name", name));
    return (USState)crit.uniqueResult();
  }



  public List<Guardian> searchGuardian(final String searchText) throws Exception {
    return getList(Guardian.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.ilike("lastName", searchText, MatchMode.ANYWHERE));
      }
    });
  }



  public void updatePatientProfileImage(Integer patientId, String path) throws Exception {
    Session session = this.getSession();
    Patient patient = findById(Patient.class, patientId);
    patient.setProfileImagePath(path);
    session.update(patient);
  }



  public PatientForm findPatientForm(Integer practiceFormId, Integer patientId) {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(PatientForm.class);
    crit.add(Restrictions.eq("practiceFormId", practiceFormId));
    crit.add(Restrictions.eq("patientId", patientId));
    return (PatientForm) crit.uniqueResult();
  }

  public List<?> getGuardianPatients(final Guardian guardian, final List<Object> filters) {
    final Integer guardianId = guardian.getId();
    final List<Integer> patientIds = getGuardianPatientIds(guardianId);
    if(patientIds.isEmpty()) {
      return new ArrayList<Patient>();
    }
    List<Patient> filteredList = getList(Patient.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.in("id", patientIds));
        PatientDAO.patientFilterCriteriaTransformer(filters, criteria);
      }
    });
    return filteredList;
  }


  public List<Integer> getGuardianPatientIds(final Integer guardianId) {
    return getList(PatientGuardian.class, new ICriteriaTransformer() {

      @Override
      public void transform(Criteria criteria) {
        criteria.createAlias("guardian", "guardian");
        criteria.add(Restrictions.eq("guardian.id",guardianId));
        criteria.add(Restrictions.eq("isPrimary", true));
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("patientId"));
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
      }

    }, Integer.class); 

  }

  public Guardian getGuardian(PatientGuardian pg) {
    Guardian guardian = null;
    if(pg!=null) {
      guardian = pg.getGuardian();
      guardian.setRelation(pg.getRelationship());
    }
    return guardian;
  }

  public PatientGuardian findPatientPatientGuardianByGuardianId(final Integer guardianId) {
    return findOne(PatientGuardian.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria criteria) {          
        criteria.createAlias("guardian", "guardian");
        criteria.add(Restrictions.eq("guardian.id", guardianId));
      }
    });
  }

  public Guardian findExistingGuardianById(final Integer id) {
    return getGuardian(findPatientPatientGuardianByGuardianId(id));
  }

  public Guardian findExistingGuardianByEmail(final String email, final Integer id) {
    return findOne(Guardian.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria criteria) {          
        criteria.add(Restrictions.eq("email", email));
        criteria.add(Restrictions.ne("id", id));
      }
    });
  }

  public static void patientFilterCriteriaTransformer(List<Object> filters, Criteria crit) {
    final String firstNameFilter = (String) filters.get(0);
    final String middleNameFilter = (String) filters.get(1);
    final String lastNameFilter = (String) filters.get(2);
    final String mrnFilter = (String) filters.get(3);
    final String cityFilter = (String) filters.get(4);
    final String genderFilter = (String) filters.get(5);
    final Date dobFilter = (Date) filters.get(6);
    final String phoneFilter = (String) filters.get(7);
    final Integer statusFilter = (Integer) filters.get(8);
    crit.createAlias("gender", "gender");
    if (StringUtils.isNotBlank(firstNameFilter)) {crit.add(Restrictions.eq("firstName", firstNameFilter));}
    if (StringUtils.isNotBlank(middleNameFilter)) {crit.add(Restrictions.eq("middleName", middleNameFilter));}
    if (StringUtils.isNotBlank(lastNameFilter)) {crit.add(Restrictions.eq("lastName", lastNameFilter));}
    if (StringUtils.isNotBlank(mrnFilter)) {crit.add(Restrictions.eq("mrn", mrnFilter));}
    if (StringUtils.isNotBlank(cityFilter)) {crit.add(Restrictions.eq("city", cityFilter));}
    if (dobFilter != null) {crit.add(Restrictions.eq("dob", dobFilter));}
    if (StringUtils.isNotBlank(genderFilter)) {crit.add(Restrictions.eq("gender.code", genderFilter));}
    if (StringUtils.isNotBlank(phoneFilter)) {
      crit.add(Restrictions.or(Restrictions.eq("primaryPhone", phoneFilter),Restrictions.eq("primaryPhone", phoneFilter)));
    }
    if (statusFilter != null) {crit.add(Restrictions.eq("status", statusFilter));}
    crit.add(Restrictions.ne("status", Client.DELETED));    
  }



  public Patient findOtherPatientWithEmail(final String email, final Integer id) {
    return findOne(Patient.class, new ICriteriaTransformer() {

      @Override
      public void transform(Criteria criteria) {
        criteria.add(Restrictions.eq("email", email)); 
        criteria.add(Restrictions.ne("id", id));
      }

    });
  }
}
