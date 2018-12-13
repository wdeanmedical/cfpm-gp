package com.wdeanmedical.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "sales_lead")
public class SalesLead extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 6065121813524506956L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private Integer patientId;
  private Integer facilityId;
  private Integer clinicianId;
  private String title;
  private String firstName;
  private String middleName;
  private String lastName;
  private String occupation;
  private String email;
  private String primaryPhone;
  private String secondaryPhone;
  private Date dob;
  private Gender gender;
  private SalesLeadStatus status;
  private SalesLeadStage stage;
  private SalesLeadAgeRange ageRange;
  private SalesLeadSource source;
  private ReferralSourceType sourceType;
  private NetworkMarketingSource networkMarketingSource;
 
  private AddressType addressType; 
  private String streetAddress1;
  private String city;
  private USState usState;
  private String postalCode;
  
  private AddressType altAddressType; 
  private String altStreetAddress1;
  private String altCity;
  private USState altUSState;
  private String altPostalCode;
  
  private SalesLeadGoal goal;
  private Integer creatorId;
  
  private String bestTimeToContact;  
  private String note;
  
  private SalesLeadCallStatus callStatus;
  private SalesLeadEmailStatus emailStatus;

  private List<SalesLeadAction> actions = new ArrayList<SalesLeadAction>();
  private List<SalesLeadTask> tasks = new ArrayList<SalesLeadTask>();
  private List<Integer> salesAgentIds = new ArrayList<Integer>();

  public SalesLead() {
  }

  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }
  
  @Column(name = "facility_id")
  public Integer getFacilityId() { return facilityId; }
  public void setFacilityId(Integer facilityId) { this.facilityId = facilityId; }

  @Column(name = "clinician_id")
  public Integer getClinicianId() { return clinicianId; }
  public void setClinicianId(Integer clinicianId) { this.clinicianId = clinicianId; }
  
  @Column(name = "title")
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  
  @Column(name = "first_name")
  @Basic(optional = false)
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }

  @Column(name = "middle_name")
  public String getMiddleName() { return middleName; }
  public void setMiddleName(String middleName) { this.middleName = middleName; }

  @Column(name = "last_name")
  @Basic(optional = false)
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  
  @JoinColumn(name = "status", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public SalesLeadStatus getStatus() { return status; }
  public void setStatus(SalesLeadStatus status) { this.status = status; }

  @JoinColumn(name = "stage", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public SalesLeadStage getStage() { return stage; }
  public void setStage(SalesLeadStage stage) { this.stage = stage; }

  @JoinColumn(name = "age_range", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public SalesLeadAgeRange getAgeRange() { return ageRange; }
  public void setAgeRange(SalesLeadAgeRange ageRange) { this.ageRange = ageRange; }
  
  @JoinColumn(name = "source", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public SalesLeadSource getSource() { return source; }
  public void setSource(SalesLeadSource source) { this.source = source; }

  @JoinColumn(name = "source_type", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public ReferralSourceType getSourceType() { return sourceType; }
  public void setSourceType(ReferralSourceType sourceType) { this.sourceType = sourceType; }
  
  @JoinColumn(name = "mktg_source", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public NetworkMarketingSource getNetworkMarketingSource() { return networkMarketingSource; }
  public void setNetworkMarketingSource( NetworkMarketingSource networkMarketingSource) { this.networkMarketingSource = networkMarketingSource; }
  
  @JoinColumn(name = "address_type", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public AddressType getAddressType() { return addressType; }
  public void setAddressType(AddressType addressType) { this.addressType = addressType; }
  
  @Column(name = "street_address1")
  public String getStreetAddress1() { return streetAddress1; }
  public void setStreetAddress1(String streetAddress1) { this.streetAddress1 = streetAddress1; }
  
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
  
  @JoinColumn(name = "alt_address_type", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public AddressType getAltAddressType() { return altAddressType; }
  public void setAltAddressType(AddressType altAddressType) { this.altAddressType = altAddressType; }
  
  @Column(name = "alt_street_address1")
  public String getAltStreetAddress1() { return altStreetAddress1; }
  public void setAltStreetAddress1(String altStreetAddress1) { this.altStreetAddress1 = altStreetAddress1; }

  @Column(name = "alt_city")
  public String getAltCity() { return altCity; }
  public void setAltCity(String altCity) { this.altCity = altCity; }
  
  @JoinColumn(name = "alt_us_state", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public USState getAltUSState() { return altUSState; }
  public void setAltUSState(USState altUSState) { this.altUSState = altUSState; }

  @Column(name = "alt_postal_code")
  public String getAltPostalCode() { return altPostalCode; }
  public void setAltPostalCode(String altPostalCode) { this.altPostalCode = altPostalCode; }

  @Column(name = "occupation")
  public String getOccupation() { return occupation; }
  public void setOccupation(String occupation) { this.occupation = occupation; }
  
  @Column(name = "dob")
  public Date getDob() { return dob; }
  public void setDob(Date dob) { this.dob = dob; }  
  
  @JoinColumn(name = "gender", referencedColumnName = "id")
  @ManyToOne(optional = false)
  public Gender getGender() { return gender; }
  public void setGender(Gender gender) { this.gender = gender; }

  @Column(name = "email")
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  @Column(name = "primary_phone")
  public String getPrimaryPhone() { return primaryPhone; }
  public void setPrimaryPhone(String primaryPhone) { this.primaryPhone = primaryPhone; }

  @Column(name = "secondary_phone")
  public String getSecondaryPhone() { return secondaryPhone; }
  public void setSecondaryPhone(String secondaryPhone) { this.secondaryPhone = secondaryPhone; }

  @JoinColumn(name = "call_status", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public SalesLeadCallStatus getCallStatus() { return callStatus; }
  public void setCallStatus(SalesLeadCallStatus callStatus) { this.callStatus = callStatus; }
  
  @JoinColumn(name = "email_status", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public SalesLeadEmailStatus getEmailStatus() { return emailStatus; }
  public void setEmailStatus(SalesLeadEmailStatus emailStatus) { this.emailStatus = emailStatus; }
 
  @JoinColumn(name = "sales_lead_goal", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public SalesLeadGoal getGoal() { return goal; }
  public void setGoal(SalesLeadGoal goal) { this.goal = goal; }

  @Column(name = "creator_id")
  public Integer getCreatorId() { return creatorId; }
  public void setCreatorId(Integer creatorId) { this.creatorId = creatorId; }  

  @Column(name = "best_time_to_contact")
  public String getBestTimeToContact() { return bestTimeToContact; }
  public void setBestTimeToContact(String bestTimeToContact) { this.bestTimeToContact = bestTimeToContact; }

  @Column(name = "note")
  public String getNote() { return note; }
  public void setNote(String note) { this.note = note; }

  @Transient 
  public List<SalesLeadAction> getActions() { return actions; }
  public void setActions(List<SalesLeadAction> actions) { this.actions = actions; }

  @Transient 
  public List<SalesLeadTask> getTasks() { return tasks; }
  public void setTasks(List<SalesLeadTask> tasks) { this.tasks = tasks; }

  @Transient 
  public List<Integer> getSalesAgentIds() { return salesAgentIds; }
  public void setSalesAgentIds(List<Integer> salesAgentIds) { this.salesAgentIds = salesAgentIds; }
}
