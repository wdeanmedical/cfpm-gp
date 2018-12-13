/*
 * WDean Medical is distributed under the
 * GNU Lesser General Public License (GNU LGPL).
 * For details see: http://www.wdeanmedical.com
 * copyright 2013-2014 WDean Medical
 */
 
package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "family_history")
@Inheritance(strategy = InheritanceType.JOINED)
public class FamilyHistory extends WDMForm implements Serializable {

  private static final long serialVersionUID = -8340318118443065374L;

  private static final String NAME = "family_history";

  private String motherName;
  private Date motherDOB;
  private String caretakerName;
  private String caretakerRelationship;
  private Integer numResidents;
  private String jobType;
  private Boolean motherAlive;
  private String motherDeathReason;
  private Boolean fatherAlive;
  private String fatherDeathReason;
  private Boolean partnerAlive;
  private String partnerDeathReason;
  private Integer numSiblings;
  private Integer numBrothers;
  private Integer numSisters;
  private Integer numChildren;
  private Integer numSons;
  private Integer numDaughters;
  private Boolean saved = false;

  public FamilyHistory() {
    this.name = NAME;
  }

  @Column(name = "mother_name")
  public String getMotherName() { return motherName; }
  public void setMotherName(String motherName) { this.motherName = motherName; }

  @Column(name = "mother_dob")
  public Date getMotherDOB() { return motherDOB; }
  public void setMotherDOB(Date motherDOB) { this.motherDOB = motherDOB; }

  @Column(name = "caretaker_name")
  public String getCaretakerName() { return caretakerName; }
  public void setCaretakerName(String caretakerName) { this.caretakerName = caretakerName; }

  @Column(name = "caretaker_relationship")
  public String getCaretakerRelationship() { return caretakerRelationship; }
  public void setCaretakerRelationship(String caretakerRelationship) { this.caretakerRelationship = caretakerRelationship; }

  @Column(name = "num_residents")
  public Integer getNumResidents() { return numResidents; }
  public void setNumResidents(Integer numResidents) { this.numResidents = numResidents; }

  @Column(name = "job_type")
  public String getJobType() { return jobType; }
  public void setJobType(String jobType) { this.jobType = jobType; }

  @Column(name = "mother_alive")
  public Boolean isMotherAlive() { return motherAlive; }
  public void setMotherAlive(Boolean motherAlive) { this.motherAlive = motherAlive; }

  @Column(name = "mother_death_reason")
  public String getMotherDeathReason() { return motherDeathReason; }
  public void setMotherDeathReason(String motherDeathReason) { this.motherDeathReason = motherDeathReason; }

  @Column(name = "father_alive")
  public Boolean isFatherAlive() { return fatherAlive; }
  public void setFatherAlive(Boolean fatherAlive) { this.fatherAlive = fatherAlive; }

  @Column(name = "father_death_reason")
  public String getFatherDeathReason() { return fatherDeathReason; }
  public void setFatherDeathReason(String fatherDeathReason) { this.fatherDeathReason = fatherDeathReason; }

  @Column(name = "partner_alive")
  public Boolean isPartnerAlive() { return partnerAlive; }
  public void setPartnerAlive(Boolean partnerAlive) { this.partnerAlive = partnerAlive; }

  @Column(name = "partner_death_reason")
  public String getPartnerDeathReason() { return partnerDeathReason; }
  public void setPartnerDeathReason(String partnerDeathReason) { this.partnerDeathReason = partnerDeathReason; }

  @Column(name = "num_siblings")
  public Integer getNumSiblings() { return numSiblings; }
  public void setNumSiblings(Integer numSiblings) { this.numSiblings = numSiblings; }

  @Column(name = "num_brothers")
  public Integer getNumBrothers() { return numBrothers; }
  public void setNumBrothers(Integer numBrothers) { this.numBrothers = numBrothers; }

  @Column(name = "num_sisters")
  public Integer getNumSisters() { return numSisters; }
  public void setNumSisters(Integer numSisters) { this.numSisters = numSisters; }

  @Column(name = "num_children")
  public Integer getNumChildren() { return numChildren; }
  public void setNumChildren(Integer numChildren) { this.numChildren = numChildren; }

  @Column(name = "num_sons")
  public Integer getNumSons() { return numSons; }
  public void setNumSons(Integer numSons) { this.numSons = numSons; }

  @Column(name = "num_daughters")
  public Integer getNumDaughters() { return numDaughters; }
  public void setNumDaughters(Integer numDaughters) { this.numDaughters = numDaughters; }
  
  @Column(name = "saved")
  public Boolean getSaved() { return saved; }
  public void setSaved(Boolean saved) { this.saved = saved; }  
}
