package com.wdeanmedical.entity.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "medical_condition")
public class MedicalCondition extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = 1927516178344037650L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private Date date;
  private String desc;
  private Integer patientId;

  public MedicalCondition() {
  }

  @Column(name = "date")
  public Date getDate() { return date; }
  public void setDate(Date date) { this.date = date; }
  
  @Column(name = "description")
  public String getDesc() { return desc; }
  public void setDesc(String desc) { this.desc = desc; }

  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }
  
}
