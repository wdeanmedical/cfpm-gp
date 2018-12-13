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
@Table(name = "emergency_info_form")
@Inheritance(strategy = InheritanceType.JOINED)
public class EmergencyInfo extends WDMForm implements Serializable {
  private static final long serialVersionUID = -5959635972005319376L;
  public static final String NAME = "emergency_info";
  public static String[] PHI_FIELDS = new String[] {};
  
  private String ssn;
  private String lang;
  private String firstName;
  private String middleName;
  private String lastName;
  private Date dob;
  private String streetAddress;
  private String city;
  private USState usState;
  private String postalCode;
  private String phone;
  private String dropOffName;
  private String dropOffPhone;
  private String pickUpName;
  private String pickUpPhone;
  private String insurance;
  private String policyNumber;
  private String contactName1;
  private String contactRel1;
  private String contactPhone1;
  private String contactName2;
  private String contactRel2;
  private String contactPhone2;
  private String pcpName;
  private String pcpPhone;
  private String prescriberName;
  private String prescriberPhone;
  private String specName;
  private String specPhone;
  private String hospital;
  private String pharmacy;
  private String dx1;
  private String dx2;
  private String dx3;
  private String allergy1;
  private String allergyLevel1;
  private String allergy2;
  private String allergyLevel2;
  private String allergy3;
  private String allergyLevel3;
  private String allergyInfo;
  private String med1;
  private String dose1;
  private String freq1;
  private String prescriber1;
  private String med2;
  private String dose2;
  private String freq2;
  private String prescriber2;
  private String med3;
  private String dose3;
  private String freq3;
  private String prescriber3;
  private String medInfo;
  private String sigPatient;
  private Date sigDate;
  private String sigRep;
  private Date sigRepDate;
  private String sigRel;
  private Boolean readDoc;
  
  public EmergencyInfo() { 
    this.name = EmergencyInfo.NAME;
  }
  
  
  @Column(name = "ssn")
  public String getSsn() { return ssn; }
  public void setSsn(String ssn) { this.ssn = ssn; }
  
  @Column(name = "lang")
  public String getLang() { return lang; }
  public void setLang(String lang) { this.lang = lang; }
  
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

  @Column(name = "drop_off_name")
  public String getDropOffName() { return dropOffName; }
  public void setDropOffName(String dropOffName) { this.dropOffName = dropOffName; }

  @Column(name = "drop_off_phone")
  public String getDropOffPhone() { return dropOffPhone; }
  public void setDropOffPhone(String dropOffPhone) { this.dropOffPhone = dropOffPhone; }

  @Column(name = "pick_up_name")
  public String getPickUpName() { return pickUpName; }
  public void setPickUpName(String pickUpName) { this.pickUpName = pickUpName; }

  @Column(name = "pick_up_phone")
  public String getPickUpPhone() { return pickUpPhone; }
  public void setPickUpPhone(String pickUpPhone) { this.pickUpPhone = pickUpPhone; }

  @Column(name = "insurance")
  public String getInsurance() { return insurance; }
  public void setInsurance(String insurance) { this.insurance = insurance; }

  @Column(name = "policy_number")
  public String getPolicyNumber() { return policyNumber; }
  public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }

  @Column(name = "contact_name1")
  public String getContactName1() { return contactName1; }
  public void setContactName1(String contactName1) { this.contactName1 = contactName1; }

  @Column(name = "contact_rel1")
  public String getContactRel1() { return contactRel1; }
  public void setContactRel1(String contactRel1) { this.contactRel1 = contactRel1; }

  @Column(name = "contact_phone1")
  public String getContactPhone1() { return contactPhone1; }
  public void setContactPhone1(String contactPhone1) { this.contactPhone1 = contactPhone1; }

  @Column(name = "contact_name2")
  public String getContactName2() { return contactName2; }
  public void setContactName2(String contactName2) { this.contactName2 = contactName2; }

  @Column(name = "contact_rel2")
  public String getContactRel2() { return contactRel2; }
  public void setContactRel2(String contactRel2) { this.contactRel2 = contactRel2; }

  @Column(name = "contact_phone2")
  public String getContactPhone2() { return contactPhone2; }
  public void setContactPhone2(String contactPhone2) { this.contactPhone2 = contactPhone2; }

  @Column(name = "pcp_name")
  public String getPcpName() { return pcpName; }
  public void setPcpName(String pcpName) { this.pcpName = pcpName; }

  @Column(name = "pcp_phone")
  public String getPcpPhone() { return pcpPhone; }
  public void setPcpPhone(String pcpPhone) { this.pcpPhone = pcpPhone; }

  @Column(name = "prescriber_name")
  public String getPrescriberName() { return prescriberName; }
  public void setPrescriberName(String prescriberName) { this.prescriberName = prescriberName; }

  @Column(name = "prescriber_phone")
  public String getPrescriberPhone() { return prescriberPhone; }
  public void setPrescriberPhone(String prescriberPhone) { this.prescriberPhone = prescriberPhone; }

  @Column(name = "spec_name")
  public String getSpecName() { return specName; }
  public void setSpecName(String specName) { this.specName = specName; }

  @Column(name = "spec_phone")
  public String getSpecPhone() { return specPhone; }
  public void setSpecPhone(String specPhone) { this.specPhone = specPhone; }

  @Column(name = "hospital")
  public String getHospital() { return hospital; }
  public void setHospital(String hospital) { this.hospital = hospital; }

  @Column(name = "pharmacy")
  public String getPharmacy() { return pharmacy; }
  public void setPharmacy(String pharmacy) { this.pharmacy = pharmacy; }
  
  @Column(name = "dx1")
  public String getDx1() { return dx1; }
  public void setDx1(String dx1) { this.dx1 = dx1; }
  
  @Column(name = "dx2")
  public String getDx2() { return dx2; }
  public void setDx2(String dx2) { this.dx2 = dx2; }
  
  @Column(name = "dx3")
  public String getDx3() { return dx3; }
  public void setDx3(String dx3) { this.dx3 = dx3; }
  
  @Column(name = "allergy_1", columnDefinition = "varchar(20)")
  public String getAllergy1() { return allergy1; }
  public void setAllergy1(String allergy1) { this.allergy1 = allergy1; }

  @Column(name = "allergy_level_1", columnDefinition = "varchar(20)")
  public String getAllergyLevel1() { return allergyLevel1; }
  public void setAllergyLevel1(String allergyLevel1) { this.allergyLevel1 = allergyLevel1; }

  @Column(name = "allergy_2", columnDefinition = "varchar(20)")
  public String getAllergy2() { return allergy2; }
  public void setAllergy2(String allergy2) { this.allergy2 = allergy2; }

  @Column(name = "allergy_level_2", columnDefinition = "varchar(20)")
  public String getAllergyLevel2() { return allergyLevel2; }
  public void setAllergyLevel2(String allergyLevel2) { this.allergyLevel2 = allergyLevel2; }

  @Column(name = "allergy_3", columnDefinition = "varchar(20)")
  public String getAllergy3() { return allergy3; }
  public void setAllergy3(String allergy3) { this.allergy3 = allergy3; }

  @Column(name = "allergy_level_3", columnDefinition = "varchar(20)")
  public String getAllergyLevel3() { return allergyLevel3; }
  public void setAllergyLevel3(String allergyLevel3) { this.allergyLevel3 = allergyLevel3; }

  @Column(name = "allergy_info", columnDefinition="text")
  public String getAllergyInfo() { return allergyInfo; }
  public void setAllergyInfo(String allergyInfo) { this.allergyInfo = allergyInfo; }

  @Column(name = "med_1", columnDefinition = "varchar(20)")
  public String getMed1() { return med1; }
  public void setMed1(String med1) { this.med1 = med1; }

  @Column(name = "dose_1", columnDefinition = "varchar(20)")
  public String getDose1() { return dose1; }
  public void setDose1(String dose1) { this.dose1 = dose1; }

  @Column(name = "freq_1", columnDefinition = "varchar(20)")
  public String getFreq1() { return freq1; }
  public void setFreq1(String freq1) { this.freq1 = freq1; }

  @Column(name = "prescriber_1", columnDefinition = "varchar(20)")
  public String getPrescriber1() { return prescriber1; }
  public void setPrescriber1(String prescriber1) { this.prescriber1 = prescriber1; }
  
  @Column(name = "med_2", columnDefinition = "varchar(20)")
  public String getMed2() { return med2; }
  public void setMed2(String med2) { this.med2 = med2; }

  @Column(name = "dose_2", columnDefinition = "varchar(20)")
  public String getDose2() { return dose2; }
  public void setDose2(String dose2) { this.dose2 = dose2; }

  @Column(name = "freq_2", columnDefinition = "varchar(20)")
  public String getFreq2() { return freq2; }
  public void setFreq2(String freq2) { this.freq2 = freq2; }

  @Column(name = "prescriber_2", columnDefinition = "varchar(20)")
  public String getPrescriber2() { return prescriber2; }
  public void setPrescriber2(String prescriber2) { this.prescriber2 = prescriber2; }

  @Column(name = "med_3", columnDefinition = "varchar(20)")
  public String getMed3() { return med3; }
  public void setMed3(String med3) { this.med3 = med3; }

  @Column(name = "dose_3", columnDefinition = "varchar(20)")
  public String getDose3() { return dose3; }
  public void setDose3(String dose3) { this.dose3 = dose3; }

  @Column(name = "freq_3", columnDefinition = "varchar(20)")
  public String getFreq3() { return freq3; } 
  public void setFreq3(String freq3) { this.freq3 = freq3; }

  @Column(name = "prescriber_3", columnDefinition = "varchar(20)")
  public String getPrescriber3() { return prescriber3; }
  public void setPrescriber3(String prescriber3) { this.prescriber3 = prescriber3; }

  @Column(name = "med_info", columnDefinition="text")
  public String getMedInfo() { return medInfo; }
  public void setMedInfo(String medInfo) { this.medInfo = medInfo; }
  
  @Column(name = "sig_patient")
  public String getSigPatient() { return sigPatient; }
  public void setSigPatient(String sigPatient) { this.sigPatient = sigPatient; }

  @Column(name = "sig_date")
  public Date getSigDate() { return sigDate; }
  public void setSigDate(Date sigDate) { this.sigDate = sigDate; }

  @Column(name = "sig_rep")
  public String getSigRep() { return sigRep; }
  public void setSigRep(String sigRep) { this.sigRep = sigRep; }

  @Column(name = "sig_rep_date")
  public Date getSigRepDate() { return sigRepDate; }
  public void setSigRepDate(Date sigRepDate) { this.sigRepDate = sigRepDate; }

  @Column(name = "sig_rel")
  public String getSigRel() { return sigRel; }
  public void setSigRel(String sigRel) { this.sigRel = sigRel; }

  @Column(name = "read_doc")
  public Boolean getReadDoc() { return readDoc; }
  public void setReadDoc(Boolean readDoc) { this.readDoc = readDoc; }
}
