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
@Table(name = "texting_waiver_form")
@Inheritance(strategy = InheritanceType.JOINED)
public class TextingWaiver extends WDMForm implements Serializable {
  
  private static final long serialVersionUID = -5640685600645176914L;
  public static String[] PHI_FIELDS = new String[] {};
  public static final String NAME = "texting_waiver";
  
  private String clientName;
  private Date dob;
  private String guardian;
  private Date guardianDate;
  private String clientRel;
  private String provider;
  private Date providerDate;
  
  public TextingWaiver() { 
    this.name = TextingWaiver.NAME;
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
  
  @Column(name = "guardian_date")
  public Date getGuardianDate() { return guardianDate; }
  public void setGuardianDate(Date guardianDate) { this.guardianDate = guardianDate; }
  
  @Column(name = "client_rel")
  public String getClientRel() { return clientRel; }
  public void setClientRel(String clientRel) { this.clientRel = clientRel; }
  
  @Column(name = "provider")
  public String getProvider() { return provider; }
  public void setProvider(String provider) { this.provider = provider; }
  
  @Column(name = "provider_date")
  public Date getProviderDate() { return providerDate; }
  public void setProviderDate(Date providerDate) { this.providerDate = providerDate; }
  
}
