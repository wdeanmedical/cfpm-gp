package com.wdeanmedical.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.wdeanmedical.entity.CalendarEvent;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.interfaces.ICriteriaTransformer;

@Transactional
public class CalendarDAO extends BaseDAO {

  private static final Logger log = Logger.getLogger(CalendarDAO.class);

  private SessionFactory sessionFactory;

  public CalendarDAO() {
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

  public List<CalendarEvent> findAllCalendarEvents() throws Exception {
    return getList(CalendarEvent.class);
  }
  
  public List<CalendarEvent> findCalendarEventsByPatient(final Patient patient) throws Exception {
    return getList(CalendarEvent.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patient", patient));
      }
    });
  }
    
  public List<CalendarEvent> findCalendarEventsByDay(final Date startTime) throws Exception {
    return getList(CalendarEvent.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, 1); 
        Date endTime = cal.getTime();
        crit.add(Restrictions.ge("startTime", startTime));
        crit.add(Restrictions.lt("endTime", endTime));
        crit.addOrder(Order.asc("startTime"));
      }
    });
  }

  public CalendarEvent getLastEvent(Patient patient) {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(CalendarEvent.class);
    crit.createAlias("patient", "patient");
    crit.add(Restrictions.eq("patient.id", patient.getId()) );
    crit.add(Restrictions.lt("startTime", new Date()));
    crit.addOrder(Order.desc("startTime"));
    crit.setMaxResults(1);
    return (CalendarEvent) crit.uniqueResult();
  }
  
  public CalendarEvent getUpcomingEvent(Patient patient) {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(CalendarEvent.class);
    crit.createAlias("patient", "patient");
    crit.add(Restrictions.eq("patient.id", patient.getId()) );
    crit.add(Restrictions.gt("startTime", new Date()));
    crit.addOrder(Order.asc("startTime"));
    crit.setMaxResults(1);
    return (CalendarEvent) crit.uniqueResult();
  }
}
