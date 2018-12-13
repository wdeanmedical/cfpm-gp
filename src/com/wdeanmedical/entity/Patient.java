package com.wdeanmedical.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.wdeanmedical.core.Code;
import com.wdeanmedical.core.Messages;
import com.wdeanmedical.interfaces.IPatientInfo;
import com.wdeanmedical.model.PatientRecentActivity;
import com.wdeanmedical.model.ValidationResult;
import com.wdeanmedical.persistence.PatientDAO;
import com.wdeanmedical.service.AppService;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.Util;

@Entity
@Table(name = "patient")
@Inheritance(strategy = InheritanceType.JOINED)
public class Patient extends Client implements Serializable, IPatientInfo {

  private static final long serialVersionUID = 5963957101514207030L;
  
  public static String[] PHI_FIELDS = new String[] {
    "customerKey",
    "firstName",
    "middleName",
    "lastName",
    "govtId",
    "driversLicense",
    "email",
    "username",
    "streetAddress1",
    "streetAddress2",
    "city",
    "employer",
    "postalCode",
    "primaryPhone",
    "secondaryPhone",
    "schoolName",
    "preferredName",
    "occupation",
    "insuranceCarrier",
    "memberNumber",
    "groupNumber",
    "insuredName"
  };

  private RecoveryCode activationCode;
  private String additionalName;
  private Integer assignedClinicianId;
  private String city;
  private Country country;
  private String customerKey;
  private Date dob;
  private String driversLicense;
  private String employer;
  private String employmentStatus;
  private Boolean encrypted = true;
  private Ethnicity ethnicity;
  private String forms;
  private Gender gender;
  private String govtId;
  private String groupNumber;
  private Boolean intakeClosed;
  private String insuranceCarrier;
  private String insuredName;
  private MaritalStatus maritalStatus;
  private String memberNumber;
  private String mrn;
  private Guardian guardian;
  private String occupation;
  private Date openingDate;
  private Date portalInviteDate;
  private String postalCode;
  private String prepaymentAmount;
  private String profileImagePath;
  private String programs;
  private String preferredName;
  private Race race;
  private String guardianEmail; 
  private String salesLeadSource;
  private String schoolStatus;
  private String schoolName;
  private String streetAddress1;
  private String streetAddress2;
  private USState usState;
  private Integer patientFormId;
  private Integer emailGuardianId;

  private PatientRecentActivity recentActivity;
  
  public Patient() {
  }

  @OneToOne()
  @JoinColumn(name="activate_code")
  @Cascade({CascadeType.SAVE_UPDATE})
  public RecoveryCode getActivationCode() { return activationCode; }
  public void setActivationCode(RecoveryCode activationCode) { this.activationCode = activationCode; }
  
  @Column(name = "additional_name")
  public String getAdditionalName() { return additionalName; }
  public void setAdditionalName(String additionalName) { this.additionalName = additionalName; }
  
  @Column(name = "assigned_clinician_id")
  public Integer getAssignedClinicianId() { return assignedClinicianId; }
  public void setAssignedClinicianId(Integer assignedClinicianId) { this.assignedClinicianId = assignedClinicianId; }

  @Column(name = "city")
  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }
  
  @JoinColumn(name = "country", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public Country getCountry() { return country; }
  public void setCountry(Country country) { this.country = country; }
  
  @Column(name = "customer_key")
  public String getCustomerKey() { return customerKey; }
  public void setCustomerKey(String customerKey) { this.customerKey = customerKey; }

  @Column(name = "dob")
  public Date getDob() { return dob; }
  public void setDob(Date dob) { this.dob = dob; }
  
  @Column(name = "drivers_license")
  public String getDriversLicense() { return driversLicense; }
  public void setDriversLicense(String driversLicense) { this.driversLicense = driversLicense; }
  
  @Column(name = "employer")
  public String getEmployer() { return employer; }
  public void setEmployer(String employer) { this.employer = employer; }
  
  @Column(name = "employment_status")
  public String getEmploymentStatus() { return employmentStatus; }
  public void setEmploymentStatus(String employmentStatus) { this.employmentStatus = employmentStatus; }
  
  @JoinColumn(name = "ethnicity", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public Ethnicity getEthnicity() { return ethnicity; }
  public void setEthnicity(Ethnicity ethnicity) { this.ethnicity = ethnicity; }
  
  @Transient
  public Boolean isEncrypted() { return encrypted; }
  public void setEncrypted(Boolean encrypted) { this.encrypted = encrypted; }
  
  @Transient
  public String getForms() { return forms; }
  public void setForms(String forms) { this.forms = forms; }
  
  @JoinColumn(name = "gender", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public Gender getGender() { return gender; }
  public void setGender(Gender gender) { this.gender = gender; }
  
  @Column(name = "govt_id")
  public String getGovtId() { return govtId; }
  public void setGovtId(String govtId) { this.govtId = govtId; }
 
  @Column(name = "group_number")
  public String getGroupNumber() { return groupNumber; }
  public void setGroupNumber(String groupNumber) { this.groupNumber = groupNumber; }
  
  @Column(name = "insurance_carrier")
  public String getInsuranceCarrier() { return insuranceCarrier; }
  public void setInsuranceCarrier(String insuranceCarrier) { this.insuranceCarrier = insuranceCarrier; }

  @Column(name = "insured_name")
  public String getInsuredName() { return insuredName; }
  public void setInsuredName(String insuredName) { this.insuredName = insuredName; }
  
  @Column(name = "intake_closed")
  public Boolean getIntakeClosed() { return intakeClosed; }
  public void setIntakeClosed(Boolean intakeClosed) { this.intakeClosed = intakeClosed; }
  
  @JoinColumn(name = "marital_status", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public MaritalStatus getMaritalStatus() { return maritalStatus; }
  public void setMaritalStatus(MaritalStatus maritalStatus) { this.maritalStatus = maritalStatus; }
  
  @Column(name = "member_number")
  public String getMemberNumber() { return memberNumber; }
  public void setMemberNumber(String memberNumber) { this.memberNumber = memberNumber; }
  
  @Column(name = "mrn", nullable = true, unique=true)
  public String getMrn() { return mrn; }
  public void setMrn(String mrn) { this.mrn = mrn; }
  
  @Column(name = "occupation")
  public String getOccupation() { return occupation; }
  public void setOccupation(String occupation) { this.occupation = occupation; }
  
  @Column(name = "opening_date")
  public Date getOpeningDate() { return openingDate; }
  public void setOpeningDate(Date openingDate) { this.openingDate = openingDate; }
  
  @Transient
  public Date getPortalInviteDate() { return portalInviteDate; }
  public void setPortalInviteDate(Date portalInviteDate) { this.portalInviteDate = portalInviteDate; }
  
  @Column(name = "postal_code")
  public String getPostalCode() { return postalCode; }
  public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
  
  @Column(name = "preferred_name")
  public String getPreferredName() { return preferredName; }
  public void setPreferredName(String preferredName) { this.preferredName = preferredName; }
  
  @Column(name = "prepayment_amount")
  public String getPrepaymentAmount() { return prepaymentAmount; }
  public void setPrepaymentAmount(String prepaymentAmount) { this.prepaymentAmount = prepaymentAmount; }

  @Column(name = "profile_image_path")
  public String getProfileImagePath() { return profileImagePath; }
  public void setProfileImagePath(String profileImagePath) { this.profileImagePath = profileImagePath; }
  
  @Column(name = "programs")
  public String getPrograms() { return programs; }
  public void setPrograms(String programs) { this.programs = programs; }

  @JoinColumn(name = "race", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public Race getRace() { return race; }
  public void setRace(Race race) { this.race = race; }
  
  @Column(name = "sales_lead_source")
  public String getSalesLeadSource() { return salesLeadSource; }
  public void setSalesLeadSource(String salesLeadSource) { this.salesLeadSource = salesLeadSource; }
  
  @Column(name = "school_name")
  public String getSchoolName() { return schoolName; }
  public void setSchoolName(String schoolName) { this.schoolName = schoolName; }

  @Column(name = "school_status")
  public String getSchoolStatus() { return schoolStatus; }
  public void setSchoolStatus(String schoolStatus) { this.schoolStatus = schoolStatus; }

  @Column(name = "street_address1")
  public String getStreetAddress1() { return streetAddress1; }
  public void setStreetAddress1(String streetAddress1) { this.streetAddress1 = streetAddress1; }

  @Column(name = "street_address2")
  public String getStreetAddress2() { return streetAddress2; }
  public void setStreetAddress2(String streetAddress2) { this.streetAddress2 = streetAddress2; }

  @JoinColumn(name = "us_state", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public USState getUsState() { return usState; }
  public void setUsState(USState usState) { this.usState = usState; }

  @Transient 
  public String getFullName() {
  		return Util.buildFullName(this.getFirstName(),
          this.getMiddleName(), this.getLastName());
  }

  @Override
  @Transient
  public String getEmail() {
    if (guardianEmail != null && !isEncrypted()) {
      return guardianEmail;
    }
    return super.getEmail();
  }
  
  public void setGuardian(Guardian guardian) throws Exception {
    this.guardian = guardian;
    if (this.guardian == null) return;
    this.guardianEmail = guardian.getEmail();
    if(guardian.isEncrypted() && !isEncrypted()) {
      this.guardianEmail=DataEncryptor.decrypt(this.guardianEmail);
    }
  }
  
  public void setGuardianEmail(String guardianEmail) {
    this.guardianEmail = guardianEmail;
  }
  
  @Transient
  public Integer getPatientFormId() {
    return patientFormId;
  }

  public void setPatientFormId(Integer patientFormId) {
    this.patientFormId = patientFormId;
  }
  @Transient
  public Integer getEmailGuardianId() {
    return emailGuardianId;
  }

  public void setEmailGuardianId(Integer emailGuardianId) {
    this.emailGuardianId = emailGuardianId;
  }

  @Override
  public ValidationResult validateBeforeUpdate(AppService service, String property, final Object value, final Integer id) throws Exception{
    PatientDAO patientDAO = service.getPatientDAO();
    if (property.equals("email")) {
      Patient existing = patientDAO.findOtherPatientWithEmail(service.encrypt((String)value), id);
      if (existing != null) {
        return new ValidationResult().invalid(Messages.exists(Patient.class), Code.RETURN_CODE_ENTITY_EXISTS);
      } 
    }
    return super.validateBeforeUpdate(service, property, value, id);
  }

  @Transient 
  public String getGuardianFullName() {
    if (guardian != null) {
      return guardian.getFullName();
    }
    return null;
  }

  public void setRecentActivity(PatientRecentActivity recentActivity) {
     this.recentActivity=recentActivity;    
  }

  @Transient
  public PatientRecentActivity getRecentActivity() {
    return recentActivity;
  }
}
