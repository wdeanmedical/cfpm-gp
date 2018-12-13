package com.wdeanmedical.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "patient_medication")
public class PatientMedication extends BaseEntity implements Serializable {
  private static final long serialVersionUID = 1842371745388621408L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private String medication;
  private String dose;
  private String condition;
  private Integer patientId;
  private Integer patientFormId;

  public PatientMedication() {
  }

  @Column(name = "medication")
  public String getMedication() { return medication; }
  public void setMedication(String medication) { this.medication = medication; }

  @Column(name = "dose")
  public String getDose() { return dose; }
  public void setDose(String dose) { this.dose = dose; }

  @Column(name = "med_cond")
  public String getCondition() { return condition; }
  public void setCondition(String condition) { this.condition = condition; }

  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }
  
  @Column(name = "patient_form_id")
  public Integer getPatientFormId() { return patientFormId; }
  public void setPatientFormId(Integer patientFormId) { this.patientFormId = patientFormId; }
  
}
