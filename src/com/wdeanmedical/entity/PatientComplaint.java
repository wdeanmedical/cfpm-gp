package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "patient_complaint")
public class PatientComplaint extends BaseEntity implements Serializable {
  private static final long serialVersionUID = 3884674357787342160L;

  public static String[] PHI_FIELDS = new String[] {};
  public static final String OPEN = "open";
  public static final String CLOSED = "closed";
  
  private Integer patientId;
  private Integer complaintId;
  private String status;

  public PatientComplaint() {
  }

  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }

  @Column(name = "complaint_id")
  public Integer getComplaintId() { return complaintId; }
  public void setComplaintId(Integer complaintId) { this.complaintId = complaintId; }

  @Column(name = "complaint_status")
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  
  

}