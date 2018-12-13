package com.wdeanmedical.entity.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "patient_lab")
public class PatientLab extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = -790803638888477921L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private Date dateOrdered;
  private Date dateDue;
  private String desc;
  private Integer patientId;

  
  public PatientLab() {
  }

  
  @Column(name = "date_ordered")
  public Date getDateOrdered() { return dateOrdered; }
  public void setDateOrdered(Date dateOrdered) { this.dateOrdered = dateOrdered; }

  @Column(name = "date_due")
  public Date getDateDue() { return dateDue; }
  public void setDateDue(Date dateDue) { this.dateDue = dateDue; }

  @Column(name = "description")
  public String getDesc() { return desc; }
  public void setDesc(String desc) { this.desc = desc; }

  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }
  
}
