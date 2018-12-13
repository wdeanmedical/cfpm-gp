package com.wdeanmedical.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "presenting_problem")
public class PresentingProblem extends BaseEntity implements Serializable {
  private static final long serialVersionUID = 5229397893381084439L;

  public static String[] PHI_FIELDS = new String[] {};
  
  private String problem;
  private String duration;
  private String info;
  private Integer patientId;

  public PresentingProblem() {
  }


  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }

  @Column(name = "problem")
  public String getProblem() { return problem; }
  public void setProblem(String problem) { this.problem = problem; }

  @Column(name = "duration")
  public String getDuration() { return duration; } 
  public void setDuration(String duration) { this.duration = duration; }

  @Column(name = "info")
  public String getInfo() { return info; }
  public void setInfo(String info) { this.info = info; }
  
  
}
