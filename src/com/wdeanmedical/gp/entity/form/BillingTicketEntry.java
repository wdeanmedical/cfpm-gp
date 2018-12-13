package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wdeanmedical.entity.BaseEntity;
import com.wdeanmedical.interfaces.IBillingTicketEntry;

@Entity
@Table(name = "billing_ticket_entry")
public class BillingTicketEntry extends BaseEntity implements Serializable, IBillingTicketEntry {
  private static final long serialVersionUID = -4588981751533722025L;
  public static String[] PHI_FIELDS = new String[] {};

  Integer patientId;  
  Integer billingTicketId;  
  private Integer clinicianId;
  Date date;
  String totalTime;
  String dx;
  String service;
  String copayMethod;
  String userName;
  String patientName;

  public BillingTicketEntry() {
  }
  

  @Column(name = "clinician_id")
  public Integer getClinicianId() { return clinicianId; }
  public void setClinicianId(Integer clinicianId) { this.clinicianId = clinicianId; }
  
  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }

  @Column(name = "billing_ticket_id")
  public Integer getBillingTicketId() { return billingTicketId; }
  public void setBillingTicketId(Integer billingTicketId) { this.billingTicketId = billingTicketId; }

  @Column(name = "date")
  public Date getDate() { return date; }
  public void setDate(Date date) { this.date = date; }

  @Column(name = "total_time")
  public String getTotalTime() { return totalTime; }
  public void setTotalTime(String totalTime) { this.totalTime = totalTime; }

  @Column(name = "dx")
  public String getDx() { return dx; }
  public void setDx(String dx) { this.dx = dx; }

  @Column(name = "service")
  public String getService() { return service; }
  public void setService(String service) { this.service = service; }

  @Column(name = "copay_method")
  public String getCopayMethod() { return copayMethod; }
  public void setCopayMethod(String copayMethod) { this.copayMethod = copayMethod; }

  @Transient
  public String getUserName() { return userName; }
  public void setUserName(String userName) { this.userName = userName; }

  @Transient
  public String getPatientName() { return patientName; }
  public void setPatientName(String patientName) { this.patientName = patientName; }

}