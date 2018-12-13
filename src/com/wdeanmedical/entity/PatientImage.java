package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "patient_image")
public class PatientImage extends BaseEntity implements Serializable {
  private static final long serialVersionUID = 5486566722756430504L;

  public static String[] PHI_FIELDS = new String[] { };

  private String path;
  private Integer patientId;

  
  
  public PatientImage() {
  }


  @Column(name = "path")
  public String getPath() { return path; }
  public void setPath(String path) { this.path = path; }
  
  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }

}
