package com.wdeanmedical.persistence;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.Credential;
import com.wdeanmedical.entity.Department;
import com.wdeanmedical.entity.Division;
import com.wdeanmedical.entity.Role;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.interfaces.ICriteriaTransformer;
import com.wdeanmedical.interfaces.IItemTransformer;

@Transactional
public class AdminDAO extends BaseDAO {

  private static final Logger log = Logger.getLogger(AdminDAO.class);

  private SessionFactory sessionFactory;

  public AdminDAO() {
    super();
  }

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
  
  public Boolean checkEmail(String email) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(User.class);
    crit.add(Restrictions.eq("email", email));
    crit.add(Restrictions.ne("status", Client.DELETED));
    User user = (User) crit.uniqueResult();
    return (user == null);
  }
  
  public Boolean checkUsername(String username) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(User.class);
    crit.add(Restrictions.eq("username", username));
    User user = (User) crit.uniqueResult();
    return (user == null);
  }
  
  public List<User> findUsers() throws Exception {
    return getList(User.class, new ICriteriaTransformer(){
      public void transform(Criteria crit) {
        crit.add(Restrictions.ne("status", Client.DELETED));
      }
    }, new IItemTransformer(){
      public <T> void transform(T item) throws Exception {
        User user = (User) item;
        user.setCredential(findById(Credential.class, user.getCredentialId()));
        user.setDepartment(findById(Department.class, user.getDepartmentId()));
        user.setDivision(findById(Division.class, user.getDivisionId()));
        user.setRole(findById(Role.class, user.getRoleId()));
      }
    });
  }
}
