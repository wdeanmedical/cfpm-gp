package com.wdeanmedical.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "patient_med_allergy")
public class PatientMedAllergy extends BaseEntity implements Serializable {
  private static final long serialVersionUID = -5833185301137151271L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private String desc;
  private Integer level;
  private Integer patientId;
  private Integer patientFormId;
  private String notes = "";

  public PatientMedAllergy() {
  }

  @Column(name = "description")
  public String getDesc() { return desc; }
  public void setDesc(String desc) { this.desc = desc; }

  @Column(name = "level")
  public Integer getLevel() { return level; }
  public void setLevel(Integer level) { this.level = level; }

  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }
  
  @Column(name = "patient_form_id")
  public Integer getPatientFormId() { return patientFormId; }
  public void setPatientFormId(Integer patientFormId) { this.patientFormId = patientFormId; }

  @Column(name = "notes", columnDefinition = "text")
  public String getNotes() { return notes; }
  public void setNotes(String notes) { this.notes = notes; }
  
}
