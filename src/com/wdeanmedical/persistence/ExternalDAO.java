package com.wdeanmedical.persistence;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.wdeanmedical.entity.Credential;
import com.wdeanmedical.entity.Role;
import com.wdeanmedical.entity.User;

@Transactional
public class ExternalDAO extends BaseDAO {

  private static final Logger log = Logger.getLogger(AdminDAO.class);

  private SessionFactory sessionFactory;

  public ExternalDAO() {
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
  
  public void createUser(User user) throws Exception {
    Session session = this.getSession();
    user.setLastAccessed(new Date());
    session.save(user);
  }
  
  public Role findRoleById(int id ) throws Exception {
    return this.findById(Role.class, id);
  }
  
  public Credential findCredentialById(int id ) throws Exception {
    return this.findById(Credential.class, id);
  }
  
  public Boolean checkUsername(String username) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(User.class);
    crit.add(Restrictions.eq("username", username));
    User user = (User) crit.uniqueResult();
    return (user == null);
  }
  
  public User findUserById(int id ) throws Exception {
    return this.findById(User.class, id);
  }
}
