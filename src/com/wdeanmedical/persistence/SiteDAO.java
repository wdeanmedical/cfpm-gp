package com.wdeanmedical.persistence;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class SiteDAO extends BaseDAO {

  private static final Logger log = Logger.getLogger(AdminDAO.class);

  private SessionFactory sessionFactory;

  public SiteDAO() {
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
}
