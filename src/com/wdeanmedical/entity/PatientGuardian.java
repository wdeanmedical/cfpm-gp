package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wdeanmedical.gp.entity.BaseEntity;

@Entity
@Table(name = "patient_guardian")
public class PatientGuardian extends BaseEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Integer patientId;
  private Guardian guardian;
  private Boolean isPrimary = false;
  private String relationship;
  
  @Column(nullable=true)
  public String getRelationship() {
    return relationship;
  }
  public void setRelationship(String relationship) {
    this.relationship = relationship;
  }
  @Column(name="is_primary", columnDefinition="tinyint(1) default 0")
  public Boolean getIsPrimary() {
    return isPrimary;
  }
  public void setIsPrimary(Boolean isPrimary) {
    this.isPrimary = isPrimary;
  }
  
  @Column(name="patient", nullable=false)
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patient) {
    this.patientId = patient;
  }

  @OneToOne
  @JoinColumn(name="guardian", nullable=false)
  public Guardian getGuardian() {
    return guardian;
  }

  public void setGuardian(Guardian guardian) {
    this.guardian = guardian;
  }
}
