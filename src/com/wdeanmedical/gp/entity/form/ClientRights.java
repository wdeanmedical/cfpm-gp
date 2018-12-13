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
@Table(name = "client_rights_form")
@Inheritance(strategy = InheritanceType.JOINED)
public class ClientRights extends WDMForm implements Serializable {
  
  private static final long serialVersionUID = 6849301935504295639L;
  public static final String NAME = "client_rights";
  public static String[] PHI_FIELDS = new String[] {};
  
  private String clientName;
  private Date dob;
  private String guardian;
  private Date signedDate;
  private String guardianRel;
  private String clinicianSig;
  private Date clinicianSigDate;
  private Boolean child;
  
  
  public ClientRights() { 
    this.name = ClientRights.NAME;
  }
  
  @Column(name = "client_name")
  public String getClientName() { return clientName; }
  public void setClientName(String clientName) { this.clientName = clientName; }
  
  @Column(name = "dob")
  public Date getDob() { return dob; }
  public void setDob(Date dob) { this.dob = dob; }
  
  @Column(name = "guardian")
  public String getGuardian() { return guardian; }
  public void setGuardian(String guardian) { this.guardian = guardian; }
  
  @Column(name = "signed_date")
  public Date getSignedDate() { return signedDate; }
  public void setSignedDate(Date signedDate) { this.signedDate = signedDate; }
  
  @Column(name = "guardian_rel")
  public String getGuardianRel() { return guardianRel; }
  public void setGuardianRel(String guardianRel) { this.guardianRel = guardianRel; }
  
  @Column(name = "clinician_sig")
  public String getClinicianSig() { return clinicianSig; }
  public void setClinicianSig(String clinicianSig) { this.clinicianSig = clinicianSig; }
  
  @Column(name = "clinician_sig_date")
  public Date getClinicianSigDate() { return clinicianSigDate; }
  public void setClinicianSigDate(Date clinicianSigDate) { this.clinicianSigDate = clinicianSigDate; }

  @Column(name = "child")
  public Boolean getChild() { return child; }
  public void setChild(Boolean child) { this.child = child; }
  
}

