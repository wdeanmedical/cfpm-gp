package com.wdeanmedical.persistence;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.wdeanmedical.entity.BaseEntity;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.ClientSession;
import com.wdeanmedical.entity.Guardian;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.interfaces.ICriteriaTransformer;
import com.wdeanmedical.interfaces.IItemTransformer;
import com.wdeanmedical.interfaces.IListMapper;

@Transactional
public class BaseDAO {

  private static final Logger log = Logger.getLogger(BaseDAO.class);
  private SessionFactory sessionFactory;

  public <T> T cast(Class<T> Klass, Object o) {
    return Klass.cast(o);
  }
 
  public void saveOrUpdate(BaseEntity entity) {
    Session session = this.getSession();
    Date today = new Date();
    entity.setLastAccessed(today);
    entity.setLastUpdated(today);
    if(entity.getCreatedDate()==null) {
      entity.setCreatedDate(today);
    }
    session.saveOrUpdate(entity);
  }
  
  private Class<? extends Client> getClientClass(String clientType) {
    switch(clientType) {
      case Client.GUARDIAN:
        return Guardian.class;
      case Client.PATIENT:
        return Patient.class;  
      default:   
        return User.class;
    }
  }
  
  public Client findClientById(String clientType, Integer id) throws Exception {
    Class<? extends Client> klass = getClientClass(clientType);
    return findById(klass, id);
  }

  public static <T> List<T> cast(Class<T> Klass, List<?> list) {
    ArrayList<T> items = new ArrayList<T>();
    for(Object object: list) {
      T t = Klass.cast(object);
      items.add(t);
    }
    return items;
  }
  
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public <T> List<T> getList(Class<T> Klass) {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Klass);
    return cast(Klass, crit.list());
  }
  
  public <T> List<T> getList(Class<T> Klass, ICriteriaTransformer transformer) {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Klass);
    transformer.transform(crit);
    return cast(Klass, crit.list());
  }
  
  public <T> List<T> getList(Class<T> Klass, ICriteriaTransformer transformer, ICriteriaTransformer searchCriteria) {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Klass);
    transformer.transform(crit);
    searchCriteria.transform(crit);
    return cast(Klass, crit.list());
  }
  
  public <R, T> List<R> getList(Class<T> Klass, ICriteriaTransformer transformer, Class<R> ReturnKlass) {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Klass);
    transformer.transform(crit);
    return cast(ReturnKlass, crit.list());
  }
  
  public void evict(Object object) {
    this.getSession().evict(object);
  }
  
  public <T> List<T> getList(Class<T> Klass, ICriteriaTransformer transformer, IItemTransformer itemTransformer) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Klass);
    transformer.transform(crit);
    List<T> list = cast(Klass, crit.list());
    for (T item : list) {
       itemTransformer.transform(item);
    }
    return list;
  }
  
  public <T, R> List<R> getList(Class<T> Klass, ICriteriaTransformer transformer, IListMapper<R> mapper) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Klass);
    transformer.transform(crit);
    List<T> list = cast(Klass, crit.list());
    List<R> mapped = new ArrayList<R>();
    for (T item : list) {
       mapped.add(mapper.map(item));
    }
    return mapped;
  }

  public Session getSession() {
    return this.sessionFactory.getCurrentSession();
  }
  
  public <T> T findByColumn(Class<T> Klass, String column , Object value) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Klass);
    crit.add(Restrictions.eq(column, value));
    return cast(Klass, crit.uniqueResult());
  }

  public BaseEntity create(BaseEntity entity) throws Exception {
    Session session = this.getSession();
    entity.setCreatedDate(new Date());
    entity.setLastAccessed(new Date());
    entity.setLastUpdated(new Date());
    session.save(entity);
    return entity;
  }
  
  public void delete(BaseEntity entity) throws Exception {
    Session session = this.getSession();
    if (entity != null) {
      session.delete(entity);
    }
  }

  public <T> void deleteAll(List<T> list, Class<T> Klass) throws Exception {
    if (list.isEmpty()) return;
    Session session = this.getSession();
    List<Integer> ids = new ArrayList<Integer>();
    for(T t: list) {
      BaseEntity entity = (BaseEntity) t;
      ids.add(entity.getId());
    }
    Query q = session.createQuery("DELETE FROM "+ Klass.getName() +" e WHERE e.id IN (:ids)");
    q.setParameterList("ids", ids);
    q.executeUpdate();
  }

  
  public List<Object[]> findAllByQuery(String sqlQuery, int start, int limit) throws Exception {
    Session session = this.getSession();
    SQLQuery query = session.createSQLQuery(sqlQuery);
    query.setFirstResult(start);
    query.setMaxResults(limit);
    List<Object[]> list = query.list();
    return list;
  }
  
  public <T> List<T> findAllByIds(final List<Integer> ids, Class<T> Klass) throws Exception {
    if (ids.isEmpty()) return new ArrayList<T>();
    return getList(Klass, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria criteria) {   
        criteria.add(Restrictions.in("id", ids));
      }
    });
  }
  
  public <T> T findById(Class<T> entityClass, Integer id) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(entityClass);
    crit.add(Restrictions.eq("id", id));
    BaseEntity entity = (BaseEntity) crit.uniqueResult();
    if (entity != null) {
      entity.setLastAccessed(new Date());
      session.update(entity);
    }
    return cast(entityClass, entity);
  }
  
  public BaseEntity findByIdString(Class<?> entityClass, String stringId) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(entityClass);
    Integer id = new Integer(stringId);
    crit.add(Restrictions.eq("id", id));
    BaseEntity entity = (BaseEntity) crit.uniqueResult();
    if (entity != null) {
      entity.setLastAccessed(new Date());
      session.update(entity);
    }
    return entity;
  }
  
  public <T> T findByPatientId(Class<T> entityClass, Integer patientId) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(entityClass);
    crit.add(Restrictions.eq("patientId", patientId));
    BaseEntity entity = (BaseEntity) crit.uniqueResult();
    if (entity != null) {
      entity.setLastAccessed(new Date());
      session.update(entity);
    }
    return cast(entityClass, entity);
  }
  
  public Client findClientByClientTypeAndId(Integer id, String clientType) throws Exception {
    Client client = null;
    if (Client.USER.equals(clientType)) { client = this.findById(User.class, id); }
    else if (Client.PATIENT.equals(clientType)) { client = this.findById(Patient.class, id); }
    return client;
  }
  
  public Client findClientBySessionId(String sessionId) throws Exception {
    Client client = null;
    Session session = this.getSession();
    Criteria crit = session.createCriteria(ClientSession.class);
    crit.add(Restrictions.eq("sessionId", sessionId));
    ClientSession clientSession = (ClientSession) crit.uniqueResult();
    String clientType = clientSession.getClientType();
    if (Client.USER.equals(clientType)) { client = this.findById(User.class, clientSession.getClientId()); }
    else if (Client.PATIENT.equals(clientType)) { client = this.findById(Patient.class, clientSession.getClientId()); }
    return client;
  }
  
  public int findTotalByQuery(String sqlQuery) throws Exception {
    Session session = this.getSession();
    SQLQuery query = session.createSQLQuery(sqlQuery);
    BigInteger bi = (BigInteger)query.uniqueResult();
    return bi.intValue();
  }
  
  public int findTotalCount(Class<?> entityClass) throws Exception {
    Session session = this.getSession();
    return ((Long)session.createQuery("select count(*) from " + entityClass.getName()).iterate().next()).intValue();
  }

  public void update(BaseEntity entity) throws Exception {
    Session session = this.getSession();
    entity.setLastAccessed(new Date());
    entity.setLastUpdated(new Date());
    session.update(entity);
  }
  
  public ClientSession findClientSessionBySessionId(String sessionId) throws Exception {
    Session session = this.getSession();
    ClientSession item = null;
    Criteria crit = session.createCriteria(ClientSession.class);
    crit.add(Restrictions.eq("sessionId", sessionId));
    item = (ClientSession) crit.uniqueResult();
    return item;
  }
  
  public Boolean checkEmail(String email) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Patient.class);
    crit.add(Restrictions.eq("email", email));
    crit.add(Restrictions.ne("status", Client.DELETED));
    Patient patient = (Patient) crit.uniqueResult();
    return (patient == null);
  }

  public Boolean checkEmail(String email, String clientType) throws Exception {
    Session session = this.getSession();
    Class<? extends Client> klass = getClientClass(clientType);
    Criteria crit = session.createCriteria(klass);
    crit.add(Restrictions.eq("email", email));
    crit.add(Restrictions.ne("status", Client.DELETED));
    return crit.uniqueResult() == null;
  }

  public <T> T findOne(Class<T> Klass, ICriteriaTransformer transformer) {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Klass);
    transformer.transform(crit);
    crit.setMaxResults(1);
    return cast(Klass, crit.uniqueResult());    
  }
  
  public <T> Boolean exists(Class<T> Klass, Integer id) {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Klass);
    crit.add(Restrictions.eq("id", id));
    crit.setMaxResults(1);
    return crit.uniqueResult() != null;    
  }
  
  public <T> T findLast(Class<T> Klass, ICriteriaTransformer transformer) {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(Klass);
    transformer.transform(crit);
    crit.addOrder(Order.desc("createdDate"));
    crit.setMaxResults(1);
    return cast(Klass, crit.uniqueResult());    
  }
}
