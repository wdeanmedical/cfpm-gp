package com.wdeanmedical.persistence;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.wdeanmedical.entity.ActivityLog;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.Gender;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.Report;
import com.wdeanmedical.entity.SalesLead;
import com.wdeanmedical.entity.SalesLeadAgeRange;
import com.wdeanmedical.entity.SalesLeadGoal;
import com.wdeanmedical.entity.USState;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.interfaces.ICriteriaTransformer;
import com.wdeanmedical.model.report.ClinicianActivity;
import com.wdeanmedical.model.report.PatientActivity;
import com.wdeanmedical.query.ActivityResult;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.Util;
import com.wdeanmedical.util.WDMConstants;

@Transactional
public class ReportDAO extends BaseDAO {

  private static final Logger log = Logger.getLogger(ReportDAO.class);

  private SessionFactory sessionFactory;

  public ReportDAO() {
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
  
  
  
  public List<SalesLead> filterSalesLeads(final SalesLeadAgeRange salesLeadAgeRange, final USState usState, final Gender gender, final SalesLeadGoal salesLeadGoal) {
    return getList(SalesLead.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        if (salesLeadAgeRange != null) {crit.add(Restrictions.eq("ageRange", salesLeadAgeRange));}
        if (usState != null) {crit.add(Restrictions.eq("usState", usState));}
        if (gender != null) {crit.add(Restrictions.eq("gender", gender));}
        if (salesLeadGoal != null) {crit.add(Restrictions.eq("goal", salesLeadGoal));}
      }
    });
  }  
  
  
  
  
  public List<ClinicianActivity> findActivityByClinician(Date dateFrom, Date dateTo, List<Integer> userIds, String activity, List<Integer> patientIds) {
    String from = "select activityLog.id, activityLog.timePerformed, activityLog.activity, activityLog.module, activityLog.clientId, user.firstName, user.lastName, user.middleName from ActivityLog activityLog";
    StringBuilder query = new StringBuilder("");
    query.append(" User user ");
    ArrayList<String> cond = new ArrayList<String>();
    Date timePerformedFrom = null;
    Date timePerformed = null;
    cond.add(" where user.id = activityLog.clientId and user.userType = 'clinical'");
    if (dateFrom != null) {
      cond.add(" activityLog.timePerformed >= :timePerformedFrom");
      timePerformedFrom = dateFrom;
    }
    if (dateTo != null) {
      cond.add(" activityLog.timePerformed < :timePerformed");
      timePerformed = dateTo;
    }
    Boolean setUserIds = false;
    if (!userIds.isEmpty()) {cond.add(" activityLog.clientId IN (:userIds)"); setUserIds = true;}
    Boolean setActivity = false;
    if (!activity.trim().isEmpty()) {cond.add(" activityLog.activity = :activity"); setActivity = true;}
    Boolean setPatientIds = false;
    if (!patientIds.isEmpty()) {cond.add(" activityLog.patientId IN (:patientIds) "); setPatientIds = true;}
    query.append(StringUtils.join(cond.toArray(), " and "));
    Query result = this.getSession().createQuery(from + ", "+ query.toString() + " order by activityLog.createdDate desc");
    if (timePerformedFrom != null) {
      result.setDate("timePerformedFrom", timePerformedFrom);
    }
    if (timePerformed != null) {
      result.setDate("timePerformed", timePerformed);
    }
    if (setActivity) { result.setParameter("activity", activity); }
    
    if (setUserIds) { result.setParameterList("userIds", userIds);}
    
    if (setPatientIds) { result.setParameterList("patientIds", patientIds); }
    Map<Integer, ClinicianActivity> map = new HashMap<Integer, ClinicianActivity>();
    List <?> activityLogs = result.list();
    ClinicianActivity clinicianActivity;
    ActivityResult clinicianActivityResult;
    Object[] tuple;
    Integer clientId;
    for(Object log: activityLogs) {
      tuple = (Object[])log;
      clinicianActivityResult = new ActivityResult((Integer)tuple[0],  (Date)tuple[1], (String)tuple[2], (String)tuple[3], (Integer)tuple[4], (String)tuple[5], (String)tuple[6], (String)tuple[7]);
      clientId = clinicianActivityResult.activityOwnerId;
      if (!map.containsKey(clientId)) {
        String clinicianName = Util.buildFullName(clinicianActivityResult.firstName, clinicianActivityResult.middleName, clinicianActivityResult.lastName);
        clinicianActivity = new ClinicianActivity(clinicianName, clientId);
        map.put(clientId, clinicianActivity);
      } else {
        clinicianActivity = map.get(clientId);
      }
      com.wdeanmedical.model.report.ActivityLog activityLog = new com.wdeanmedical.model.report.ActivityLog(clinicianActivityResult.id, clinicianActivityResult.timePerformed, clinicianActivityResult.activity, clinicianActivityResult.module);
      clinicianActivity.addActivity(activityLog);
    }
    List<ClinicianActivity> list = new ArrayList<ClinicianActivity>();
    for(ClinicianActivity value: map.values()) {
      list.add(value);
    }
    return list;
  }
  
  
  
  public List<PatientActivity> findActivityByPatient(Date dateFrom, Date dateTo, List<Integer> userIds, String activity, List<Integer> patientIds) throws Exception {
    String from = "select activityLog.id, activityLog.timePerformed, activityLog.activity, activityLog.module, activityLog.patientId, patient.firstName, patient.lastName, patient.middleName from ActivityLog activityLog";
    StringBuilder query = new StringBuilder("");
    query.append(" Patient patient ");
    ArrayList<String> cond = new ArrayList<String>();
    cond.add(" where activityLog.patientId = patient.id and activityLog.patientId IS NOT NULL");
    Date timePerformedFrom = null;
    Date timePerformed = null;
    if (dateFrom != null) {
      cond.add(" activityLog.timePerformed >= :timePerformedFrom");
      timePerformedFrom = dateFrom;
    }
    if (dateTo != null) {
      cond.add(" activityLog.timePerformed < :timePerformed");
      timePerformed = dateTo;
    }
    Boolean setUserIds = false;
    if (!userIds.isEmpty()) {cond.add(" activityLog.clientId IN (:userIds)"); setUserIds = true;}
    Boolean setActivity = false;
    if (!activity.trim().isEmpty()) {cond.add(" activityLog.activity = :activity"); setActivity = true;}
    Boolean setPatientIds = false;
    if (!patientIds.isEmpty()) {cond.add(" activityLog.patientId IN (:patientIds) "); setPatientIds = true;}
    query.append(StringUtils.join(cond.toArray(), " and "));
    Query result = this.getSession().createQuery(from + ", "+ query.toString() + " order by activityLog.createdDate desc");
    if (timePerformedFrom != null) {
      result.setDate("timePerformedFrom", timePerformedFrom);
    }
    if (timePerformed != null) {
      result.setDate("timePerformed", timePerformed);
    }
    if (setActivity) { result.setParameter("activity", activity); }
    
    if (setUserIds) { result.setParameterList("userIds", userIds);}
    
    if (setPatientIds) { result.setParameterList("patientIds", patientIds); }
  
    Map<Integer, PatientActivity> map = new HashMap<Integer, PatientActivity>();
    List <?> activityLogs = result.list();
    PatientActivity patientActivity;
    ActivityResult patientActivityResult;
    Object[] tuple;
    Integer activityPatientId;
    for(Object log: activityLogs) {
      tuple = (Object[])log;
      patientActivityResult = new ActivityResult((Integer)tuple[0],  (Date)tuple[1], (String)tuple[2], (String)tuple[3], (Integer)tuple[4], (String)tuple[5], (String)tuple[6], (String)tuple[7]);
      activityPatientId = patientActivityResult.activityOwnerId;
      if (!map.containsKey(activityPatientId)) {
        String patientName = Util.buildFullName(DataEncryptor.decrypt(patientActivityResult.firstName), DataEncryptor.decrypt(patientActivityResult.middleName), DataEncryptor.decrypt(patientActivityResult.lastName));
        patientActivity = new PatientActivity(patientName, activityPatientId);
        map.put(activityPatientId, patientActivity);
      } else {
        patientActivity = map.get(activityPatientId);
      }
      com.wdeanmedical.model.report.ActivityLog activityLog = new com.wdeanmedical.model.report.ActivityLog(patientActivityResult.id, patientActivityResult.timePerformed, patientActivityResult.activity, patientActivityResult.module);
      patientActivity.addActivity(activityLog);
    }
    List<PatientActivity> list = new ArrayList<PatientActivity>();
    for(PatientActivity value: map.values()) {
      list.add(value);
    }
    return list;
  }
  
  

  public List<ActivityLog> findActivityLogs(final Date dateFrom, final Date dateTo, final Integer userId, final String activity, final Integer patientId) throws Exception {
    List<ActivityLog> list = getList(ActivityLog.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.addOrder(Order.desc("createdDate"));
        crit.setMaxResults(500);
        
        if (dateFrom != null) {crit.add(Restrictions.ge("timePerformed", dateFrom));}
        if (dateFrom != null && dateTo != null) { 
          crit.add(Restrictions.le("timePerformed", dateTo));
        }
        else if (dateFrom != null && dateTo == null){
          crit.add(Restrictions.le("timePerformed", new Date()));
        }
        if (userId != null) {crit.add(Restrictions.eq("userId", userId));}
        if (StringUtils.isNotEmpty(activity)) {crit.add(Restrictions.eq("activity", activity));}
        if (patientId != null) {crit.add(Restrictions.eq("patientId", patientId));}
      }
    });
    return list;
  }
  
  

  public List<ActivityLog> findActivityLogs(Date dateFrom, Date dateTo, List<Integer> userIds, String activity, List<Integer> patientIds) throws Exception {
    return findActivityLogs(dateFrom, dateTo, userIds, activity, patientIds, 500);
  }
    
    
    
  public List<ActivityLog> findActivityLogs(Date dateFrom, Date dateTo, List<Integer> userIds, String activity, List<Integer> patientIds, Integer limit) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(ActivityLog.class);
    crit.addOrder(Order.desc("createdDate"));
    if (limit != null) {
      crit.setMaxResults(limit);
    }
    if (dateFrom != null) {crit.add(Restrictions.ge("timePerformed", dateFrom));}
    if (dateTo != null) { 
      crit.add(Restrictions.lt("timePerformed", Util.datePlusOne(dateTo)));
    }
    if (!userIds.isEmpty()) {crit.add(Restrictions.in("clientId", userIds)); }
    if (StringUtils.isNotEmpty(activity)) {crit.add(Restrictions.eq("activity", activity));}
    if (!patientIds.isEmpty()) {crit.add(Restrictions.in("patientId", patientIds));}
    
    List<ActivityLog> list =  cast(ActivityLog.class, crit.list());

    for (ActivityLog activityLog : list) {  
      User user = findById(User.class, activityLog.getUserId());
      if (user != null) {
        activityLog.setUserName(Util.buildFullName(user.getFirstName(), user.getMiddleName(), user.getLastName()));
      }
      
      Patient patient = findById(Patient.class, activityLog.getPatientId());
      if (patient != null) {
        activityLog.setPatientName(Util.buildFullName(DataEncryptor.decrypt(patient.getFirstName()), DataEncryptor.decrypt(patient.getMiddleName()), DataEncryptor.decrypt(patient.getLastName())));
      }
      
      Client client = findClientByClientTypeAndId(activityLog.getClientId(), activityLog.getClientType());
      if (client != null) {
        if (Client.PATIENT.equals(activityLog.getClientType())) {
          activityLog.setClientName(Util.buildFullName(DataEncryptor.decrypt(client.getFirstName()), DataEncryptor.decrypt(client.getMiddleName()), DataEncryptor.decrypt(client.getLastName())));
        }
        else {
          activityLog.setClientName(Util.buildFullName(client.getFirstName(), client.getMiddleName(), client.getLastName()));
        }
      }
    }
    return list;    
  }
  
  
  
  
  public List<Integer> findClinicianIdsByFullName(String clinicianName) throws Exception {
    List<Integer> userIds = new ArrayList<Integer>();
    for(User user: findCliniciansByFullName(clinicianName)) {
      userIds.add(user.getId());
    }
    return userIds;
  }
  
  
  
  public List<User> findCliniciansByFullName(String clinicianFullName) throws Exception {
    String[] name = clinicianFullName.split(WDMConstants.SINGLE_SPACE);
    Session session = this.getSession();
    Criteria crit = session.createCriteria(User.class);
    if(name.length > 0) {
      {crit.add(Restrictions.eq("firstName", name[0]));}
      if(name.length == 2) {
        {crit.add(Restrictions.eq("lastName", name[1]));}
      }
      else if(name.length == 3){
        {crit.add(Restrictions.eq("middleName", name[1]));}
        {crit.add(Restrictions.eq("lastName", name[2]));}      
      }
    } 
    return cast(User.class, crit.list());
  }


   
  public List<ActivityLog> findPatientActivity(final Date dateFrom, final Date dateTo, final Integer userId, final String activity, final Integer patientId) {
    return getList(ActivityLog.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        if (dateFrom != null && dateTo != null) {
          crit.add(Restrictions.le("timePerformed", dateTo));
        }
        else if (dateFrom != null && dateTo == null) {
          crit.add(Restrictions.le("timePerformed", new Date()));
        }
        if (userId != null) {crit.add(Restrictions.eq("userId", userId));}
        if (activity != null) {crit.add(Restrictions.eq("activity", activity));}
        if (patientId != null) {crit.add(Restrictions.eq("patientId", patientId));}
         
        crit.setProjection(Projections.projectionList().add(Projections.groupProperty("patientId")));
      }
    });
  }
  
  

  public List<Integer> findPatientIdsByFullName(String patientName) throws Exception {
    List<Integer> patientIds = new ArrayList<Integer>();
    for(Patient patient: findPatientsByFullName(patientName)) {
      patientIds.add(patient.getId());
    }
    return patientIds;
  }
  
  
  
  public List<Patient> findPatients() throws Exception {
    return getList(Patient.class);
  }
  
  

  public List<Patient> findPatientsByFullName(String patientFullName) throws Exception {
    String[] name = patientFullName.split(WDMConstants.SINGLE_SPACE);
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Patient.class, "patient");
    String encryptedFirstNameFilter = null;
    String encryptedMiddleNameFilter = null;
    String encryptedLastNameFilter = null;  
    if(name.length > 0) {
      encryptedFirstNameFilter = DataEncryptor.encrypt(Util.capitalize(name[0]));
      {crit.add(Restrictions.eq("firstName", encryptedFirstNameFilter));}      
   
      if(name.length == 2) {
        encryptedLastNameFilter = DataEncryptor.encrypt(Util.capitalize(name[1]));
        {crit.add(Restrictions.eq("lastName", encryptedLastNameFilter));}
      }
      else if(name.length == 3) {
        encryptedMiddleNameFilter = DataEncryptor.encrypt(Util.capitalize(name[1]));
        encryptedLastNameFilter = DataEncryptor.encrypt(Util.capitalize(name[2]));
        {crit.add(Restrictions.eq("middleName", encryptedMiddleNameFilter));}
        {crit.add(Restrictions.eq("lastName", encryptedLastNameFilter));}      
      }
    }  
    return crit.list();
  }


  
  public List<Report> findReportList() {
    Session session = this.getSession();
    Query reportListQuery = session.createQuery("SELECT r FROM Report r  ORDER BY r.sortOrder ASC");
    return reportListQuery.list();
  }
  
  
  
  public SalesLeadGoal findSalesLeadGoalByName(String name) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(SalesLeadGoal.class);
    crit.add(Restrictions.eq("name", name));
    return (SalesLeadGoal)crit.uniqueResult();
  }
  
  
  
  public List<SalesLead> findSalesLeads() throws Exception {
    return getList(SalesLead.class);
  }
    
    
    
  public USState findUSStateByName(String name) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(USState.class);
    crit.add(Restrictions.eq("name", name));
    return (USState)crit.uniqueResult();
  }

 

  public List<User> findUsers() throws Exception {
    return getList(User.class);
  }
  
  
  
  public List<USState> findUSStates() throws Exception {
    return getList(USState.class);
  }
  
  

  public List<Patient> findWaitListPatients(List<Integer> userIds, List<Integer> patientIds) {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Patient.class);
    if (userIds != null) {crit.add(Restrictions.in("assignedClinicianId", userIds));}
    if (patientIds != null) {crit.add(Restrictions.in("id", patientIds));}
    crit.add(Restrictions.eq("status", Client.WAIT_LIST));
    List<Patient> list =  crit.list();
    return list;
  }
  
  
}
