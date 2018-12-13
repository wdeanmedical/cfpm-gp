package com.wdeanmedical.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.wdeanmedical.entity.CalendarEvent;
import com.wdeanmedical.entity.Gender;
import com.wdeanmedical.entity.SalesLead;
import com.wdeanmedical.entity.SalesLeadAction;
import com.wdeanmedical.entity.SalesLeadActionUser;
import com.wdeanmedical.entity.SalesLeadTask;
import com.wdeanmedical.entity.SalesLeadTaskUser;
import com.wdeanmedical.entity.SalesLeadUser;
import com.wdeanmedical.interfaces.ICriteriaTransformer;

@Transactional
public class LeadMgmtDAO extends BaseDAO {

  private static final Logger log = Logger.getLogger(LeadMgmtDAO.class);

  private SessionFactory sessionFactory;

  public LeadMgmtDAO() {
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
    
  private static Date addDays(Date date, int days){
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, days); 
    return cal.getTime();
  }
 
  public SalesLead createSalesLead(Integer userId) throws Exception {
    Session session = this.getSession();
    SalesLead salesLead = new SalesLead();
    salesLead.setCreatorId(userId);
    salesLead.setLastAccessed(new Date());
    session.save(salesLead);
    return salesLead;
  }
  
  public List<SalesLead> findAllSalesLeads() throws Exception {
    return getList(SalesLead.class);
  }
  
  public CalendarEvent findCalendarEventByTaskId(Integer taskId) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(CalendarEvent.class);
    crit.add(Restrictions.eq("taskId", taskId));
    CalendarEvent calendarEvent = (CalendarEvent) crit.uniqueResult();
    return calendarEvent;
  }
 
  public List<SalesLead> findRecentSalesLeads(final int limit) throws Exception {
    return getList(SalesLead.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.isNull("patientId"));
        crit.addOrder(Order.desc("lastAccessed"));
        crit.setMaxResults(limit);
      }
    });
  }
  
  public List<SalesLeadAction> findSalesLeadActions(final Integer salesLeadId) throws Exception {
    return getList(SalesLeadAction.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("salesLeadId", salesLeadId));
        crit.addOrder(Order.desc("date"));
      }
    });
  }
  
  public List<SalesLeadActionUser> findSalesLeadActionUsers(final Integer salesLeadActionId) throws Exception {
    return getList(SalesLeadActionUser.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("salesLeadActionId", salesLeadActionId));
      }
    });
  }
  
  public List<Integer> findSalesLeadActionUserIds(final Integer salesLeadActionId) throws Exception {
    return getList(SalesLeadActionUser.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("salesLeadActionId", salesLeadActionId));
        crit.setProjection(Projections.property("userId"));
      }
    }, Integer.class);
  }
 
  public List<SalesLeadTask> findSalesLeadTasks(final Integer salesLeadId) throws Exception {
    return getList(SalesLeadTask.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("salesLeadId", salesLeadId));
        crit.addOrder(Order.desc("dueDate"));
      }
    });
  }
  
  public List<Integer> findSalesLeadUserIds(final Integer salesLeadId) throws Exception {
    return getList(SalesLeadUser.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("salesLeadId", salesLeadId));
        crit.setProjection(Projections.property("userId"));
      }
    }, Integer.class);
  }
  
  public List<SalesLeadUser> findSalesLeadUsers(final Integer salesLeadId) throws Exception {
    return getList(SalesLeadUser.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("salesLeadId", salesLeadId));
      }
    });
  }  
  
  public List<Integer> findSalesLeadTaskUserIds(final Integer salesLeadTaskId) throws Exception {
    return getList(SalesLeadTaskUser.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("salesLeadTaskId", salesLeadTaskId));
        crit.setProjection(Projections.property("userId"));
      }
    }, Integer.class);
  }
  
  public List<SalesLead> findSalesLeads(final String firstName, final String middleName, final String lastName, final Gender gender, final Date dob, final String city) throws Exception {
    return getList(SalesLead.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        if (!StringUtils.isNullOrEmpty(firstName)) {crit.add(Restrictions.eq("firstName", firstName));}
        if (!StringUtils.isNullOrEmpty(middleName)) {crit.add(Restrictions.eq("middleName", middleName));}
        if (!StringUtils.isNullOrEmpty(lastName)) {crit.add(Restrictions.eq("lastName", lastName));}
        if (gender != null) {crit.add(Restrictions.eq("gender", gender));}
        if (dob != null) {crit.add(Restrictions.ge("dob", dob));}
        if (dob != null) {crit.add(Restrictions.lt("dob", addDays(dob, 1)));}
        if (!StringUtils.isNullOrEmpty(city)) {crit.add(Restrictions.eq("city", city));}
      }
    });
  }
  
  public List<SalesLeadTaskUser> findSalesLeadTaskUsers(final Integer salesLeadTaskId) throws Exception {
    return getList(SalesLeadTaskUser.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("salesLeadTaskId", salesLeadTaskId));
      }
    });
  }
}
