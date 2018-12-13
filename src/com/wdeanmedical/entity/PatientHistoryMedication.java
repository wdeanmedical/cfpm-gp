package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "patient_history_medication")
public class PatientHistoryMedication extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 548926604494760704L;
  public static String[] PHI_FIELDS = new String[] {};

  private String medication;
  private String dose;
  private String frequency;
  private int patientId;
  private Integer medicalHistoryId;

  public PatientHistoryMedication() {
  }

  @Column(name = "medication")
  public String getMedication() { return medication; }
  public void setMedication(String medication) { this.medication = medication; }

  @Column(name = "dose")
  public String getDose() { return dose; }
  public void setDose(String dose) { this.dose = dose; }

  @Column(name = "frequency")
  public String getFrequency() { return frequency; }
  public void setFrequency(String frequency) { this.frequency = frequency; }

  @Column(name = "patient_id")
  public int getPatientId() { return patientId; }
  public void setPatientId(int patientId) { this.patientId = patientId; }    
  
  @Column(name = "patient_medical_history_id")
  public Integer getMedicalHistoryId() { return medicalHistoryId; }
  public void setMedicalHistoryId(Integer medicalHistoryId) { this.medicalHistoryId = medicalHistoryId; }

}
