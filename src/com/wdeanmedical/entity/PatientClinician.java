package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patient_clinician")
public class PatientClinician extends BaseEntity implements Serializable {
	
  private static final long serialVersionUID = 1835725779170301037L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private Patient patient;
  private User clinician;

  public PatientClinician() {
  }

  public PatientClinician(Patient patient, User clinician) {
    this.patient=patient;
    this.clinician=clinician;
  }

  @JoinColumn(name = "patient", referencedColumnName = "id")
  @ManyToOne(optional = false)
  public Patient getPatient() { return patient; }
  public void setPatient(Patient patient) { this.patient = patient; }
  
  @JoinColumn(name = "clinician", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public User getClinician() { return clinician; }
  public void setClinician(User clinician) { this.clinician = clinician; }

}
