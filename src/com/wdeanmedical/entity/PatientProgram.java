package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patient_program")
public class PatientProgram extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = 5764462986433377033L;

  public static String[] PHI_FIELDS = new String[] {};
  
  private Integer patientId;
  private Program program;

  public PatientProgram() {
  }

  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }

  @JoinColumn(name = "program", referencedColumnName = "id")
  @ManyToOne(optional = false)
  public Program getProgram() { return program; }
  public void setProgram(Program program) { this.program = program; }

}