package com.wdeanmedical.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wdeanmedical.entity.form.PracticeForm;



@Entity
@Table(name = "patient_intake")
public class PatientIntake extends BaseEntity {

  public static final String BILLING_FORM = "billing";
  public static final String PATIENT_INFO_FORM = "info";
  
  public static String[] PHI_FIELDS = new String[] {
    "patientInfo.city"
  };
  
  private Boolean encrypted = true;   
  private String forms; 
  private Integer patientId;
  private Date portalInviteDate;
  private List<PracticeForm> practiceForms;
  
  
  @Column(name = "patient_id", unique=true)
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }
  
  @Column(name = "portal_invite_date")
  public Date getPortalInviteDate() { return portalInviteDate; }
  public void setPortalInviteDate(Date portalInviteDate) { this.portalInviteDate = portalInviteDate; }
  
  @Transient
  public Boolean isEncrypted() { return encrypted; }
  public void setEncrypted(Boolean encrypted) { this.encrypted = encrypted; }
  
  @Transient
  public String getForms() { return forms; }
  public void setForms(String forms) { this.forms = forms; }
  
  @Transient
  public List<PracticeForm> getPracticeForms() { return practiceForms; }
  public void setPracticeForms(List<PracticeForm> practiceForms) { this.practiceForms = practiceForms; }
  
}
