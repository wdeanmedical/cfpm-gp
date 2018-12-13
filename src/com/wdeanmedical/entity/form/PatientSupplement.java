package com.wdeanmedical.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "patient_supplement")
public class PatientSupplement extends BaseEntity implements Serializable {
  private static final long serialVersionUID = -3528431093847443211L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private String supp;
  private String dose;
  private String reason;
  private Integer patientId;

  public PatientSupplement() {
  }


  @Column(name = "supp")
  public String getSupp() { return supp; }
  public void setSupp(String supp) { this.supp = supp; }

  @Column(name = "reason")
  public String getReason() { return reason; }
  public void setReason(String reason) { this.reason = reason; }

  @Column(name = "dose")
  public String getDose() { return dose; }
  public void setDose(String dose) { this.dose = dose; }

  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }
  
}
