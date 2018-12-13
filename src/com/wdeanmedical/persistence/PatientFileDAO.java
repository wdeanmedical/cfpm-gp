package com.wdeanmedical.persistence;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.wdeanmedical.entity.PatientFile;
import com.wdeanmedical.interfaces.ICriteriaTransformer;

@Transactional
public class PatientFileDAO extends BaseDAO {
  private static final Logger log = Logger.getLogger(PatientFileDAO.class);

  private SessionFactory sessionFactory;
    
  public PatientFileDAO() {
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
  
  public PatientFile findPatientFileById(int id) throws Exception {
    PatientFile patientFile = this.findById(PatientFile.class, id);
    return patientFile;
  }
    
  public List<PatientFile> findPatientFiles(final Integer patientId) {
    return getList(PatientFile.class, new ICriteriaTransformer(){
      @Override
      public void transform(Criteria crit) {
        crit.add(Restrictions.eq("patientId", patientId));
      }
    });
  }
}