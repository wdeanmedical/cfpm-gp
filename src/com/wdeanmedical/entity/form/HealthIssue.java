package com.wdeanmedical.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "health_issue")
public class HealthIssue extends BaseEntity implements Serializable {
  private static final long serialVersionUID = 1071010127032550447L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private String symptoms;
  private String onset;
  private String duration;
  private String dxtx;
  private Integer patientId;

  public HealthIssue() {
  }

  
  @Column(name = "symptoms")
  public String getSymptoms() { return symptoms; }
  public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

  @Column(name = "onset")
  public String getOnset() { return onset; }
  public void setOnset(String onset) { this.onset = onset; }

  @Column(name = "duration")
  public String getDuration() { return duration; }
  public void setDuration(String duration) { this.duration = duration; }

  @Column(name = "dxtx")
  public String getDxtx() { return dxtx; }
  public void setDxtx(String dxtx) { this.dxtx = dxtx; }

  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }
}
