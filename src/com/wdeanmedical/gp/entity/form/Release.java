package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wdeanmedical.entity.USState;
import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "release_form")
@Inheritance(strategy = InheritanceType.JOINED)
public class Release extends WDMForm implements Serializable {
  
  private static final long serialVersionUID = 8725355595355373888L;
  public static final String NAME = "release";
  public static String[] PHI_FIELDS = new String[] {};
  
  
  private String firstName;
  private String middleName;
  private String lastName;
  private Date dob;
  private String streetAddress;
  private String city;
  private USState usState;
  private String postalCode;
  private String phone;
  private String alias;
  private String initials1;
  private String agencyName;
  private String agencyContact;
  private String agencyStreetAddress;
  private String agencyCity;
  private USState agencyUSState;
  private String agencyPostalCode;
  private String agencyPhone;
  private String agencyFax;
  private String infoType;
  private String infoTypeOther;
  private String purpose;
  private String purposeOther;
  private String initials2;
  private String initials3;
  private String initials4;
  private Date expDate;
  private String sigPatient;
  private Date sigDate;
  private String sigRep;
  private Date sigRepDate;
  private String sigRel;
  private Boolean readDoc;
  
  public Release() { 
    this.name = Release.NAME;
  }
  
  @Column(name = "first_name")
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  
  @Column(name = "middle_name")
  public String getMiddleName() { return middleName; }
  public void setMiddleName(String middleName) { this.middleName = middleName; }
  
  @Column(name = "last_name")
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  
  @Column(name = "dob")
  public Date getDob() { return dob; }
  public void setDob(Date dob) { this.dob = dob; }
  
  @Column(name = "street_address")
  public String getStreetAddress() { return streetAddress; }
  public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }
  
  @Column(name = "city")
  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }
  
  @JoinColumn(name = "us_state", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public USState getUsState() { return usState; }
  public void setUsState(USState usState) { this.usState = usState; }
  
  @Column(name = "postal_code")
  public String getPostalCode() { return postalCode; }
  public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
  
  @Column(name = "phone")
  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }
  
  @Column(name = "alias")
  public String getAlias() { return alias; }
  public void setAlias(String alias) { this.alias = alias; }
  
  @Column(name = "initials1")
  public String getInitials1() { return initials1; }
  public void setInitials1(String initials1) { this.initials1 = initials1; }
  
  @Column(name = "agency_name")
  public String getAgencyName() { return agencyName; }
  public void setAgencyName(String agencyName) { this.agencyName = agencyName; }
  
  @Column(name = "agency_contact")
  public String getAgencyContact() { return agencyContact; }
  public void setAgencyContact(String agencyContact) { this.agencyContact = agencyContact; }
  
  @Column(name = "agency_street_address")
  public String getAgencyStreetAddress() { return agencyStreetAddress; }
  public void setAgencyStreetAddress(String agencyStreetAddress) { this.agencyStreetAddress = agencyStreetAddress; }
  
  @Column(name = "agency_city")
  public String getAgencyCity() { return agencyCity; }
  public void setAgencyCity(String agencyCity) { this.agencyCity = agencyCity; }
  
  @JoinColumn(name = "agency_us_state", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public USState getAgencyUSState() { return agencyUSState; }
  public void setAgencyUSState(USState agencyUSState) { this.agencyUSState = agencyUSState; }
  
  @Column(name = "agency_postal_code")
  public String getAgencyPostalCode() { return agencyPostalCode; }
  public void setAgencyPostalCode(String agencyPostalCode) { this.agencyPostalCode = agencyPostalCode; }
  
  @Column(name = "agency_phone")
  public String getAgencyPhone() { return agencyPhone; }
  public void setAgencyPhone(String agencyPhone) { this.agencyPhone = agencyPhone; }
  
  @Column(name = "agency_fax")
  public String getAgencyFax() { return agencyFax; }
  public void setAgencyFax(String agencyFax) { this.agencyFax = agencyFax; }
  
  @Column(name = "info_type")
  public String getInfoType() { return infoType; }
  public void setInfoType(String infoType) { this.infoType = infoType; }
  
  @Column(name = "info_type_other")
  public String getInfoTypeOther() { return infoTypeOther; }
  public void setInfoTypeOther(String infoTypeOther) { this.infoTypeOther = infoTypeOther; }
  
  @Column(name = "purpose")
  public String getPurpose() { return purpose; }
  public void setPurpose(String purpose) { this.purpose = purpose; }
  
  @Column(name = "purpose_other")
  public String getPurposeOther() { return purposeOther; }
  public void setPurposeOther(String purposeOther) { this.purposeOther = purposeOther; }
  
  @Column(name = "initials2")
  public String getInitials2() { return initials2; }
  public void setInitials2(String initials2) { this.initials2 = initials2; }
  
  @Column(name = "initials3")
  public String getInitials3() { return initials3; }
  public void setInitials3(String initials3) { this.initials3 = initials3; }
  
  @Column(name = "initials4")
  public String getInitials4() { return initials4; }
  public void setInitials4(String initials4) { this.initials4 = initials4; }
  
  @Column(name = "exp_date")
  public Date getExpDate() { return expDate; }
  public void setExpDate(Date expDate) { this.expDate = expDate; }
  
  @Column(name = "sig_patient")
  public String getSigPatient() { return sigPatient; }
  public void setSigPatient(String sigPatient) { this.sigPatient = sigPatient; }
  
  @Column(name = "sig_date")
  public Date getSigDate() { return sigDate; }
  public void setSigDate(Date sigDate) { this.sigDate = sigDate; }
  
  @Column(name = "sig_rep")
  public String getSigRep() { return sigRep; }
  public void setSigRep(String sigRep) { this.sigRep = sigRep; }
  
  @Column(name = "sig_rel")
  public String getSigRel() { return sigRel; }
  public void setSigRel(String sigRel) { this.sigRel = sigRel; }
  
  @Column(name = "sig_rep_date")
  public Date getSigRepDate() { return sigRepDate; }
  public void setSigRepDate(Date sigRepDate) { this.sigRepDate = sigRepDate; }
  
  @Column(name = "readDoc")
  public Boolean getReadDoc() { return readDoc; }
  public void setReadDoc(Boolean readDoc) { this.readDoc = readDoc; }
  
}
