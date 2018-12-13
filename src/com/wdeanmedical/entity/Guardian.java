package com.wdeanmedical.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wdeanmedical.core.Code;
import com.wdeanmedical.core.Messages;
import com.wdeanmedical.interfaces.IPassword;
import com.wdeanmedical.model.ValidationResult;
import com.wdeanmedical.persistence.PatientDAO;
import com.wdeanmedical.service.AppService;

@Entity
@Table(name = "guardian")
@Inheritance(strategy = InheritanceType.JOINED)
public class Guardian extends Client implements Serializable, IPassword {

  private static final long serialVersionUID = 5963957101514207030L;
  
  public static String[] PHI_FIELDS = new String[] {
    "customerKey",
    "email",
    "firstName",
    "lastName",
    "middleName",
    "streetAddress1",
    "city",
    "primaryPhone",
    "secondaryPhone",
    "postalCode"
  };


  private String city;
  private String customerKey;
  private Boolean encrypted = true;
  private String goals;
  private MaritalStatus maritalStatus;  
  private String maritalOther;  
  private Date portalInviteDate;
  private String postalCode;
  private Integer patientFormId;
  private String profession;  
  private String relation;
  private String streetAddress1;
  private USState usState;
  private Boolean addressSameAsClient = false;
  
  public Guardian() {
  }

  @Column(name = "city")
  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }
  
  @Column(name = "customer_key")
  public String getCustomerKey() { return customerKey; }
  public void setCustomerKey(String customerKey) { this.customerKey = customerKey; }
  
  @Transient
  public Boolean isEncrypted() { return encrypted; }
  public void setEncrypted(Boolean encrypted) { this.encrypted = encrypted; }
  
  @Column(name = "goals", columnDefinition = "text")
  public String getGoals() { return goals; }
  public void setGoals(String goals) { this.goals = goals; }
  
  @JoinColumn(name = "marital_status", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public MaritalStatus getMaritalStatus() { return maritalStatus; }
  public void setMaritalStatus(MaritalStatus maritalStatus) { this.maritalStatus = maritalStatus; }
  
  @Column(name = "marital_other")
  public String getMaritalOther() { return maritalOther; }
  public void setMaritalOther(String maritalOther) { this.maritalOther = maritalOther; }
  
  @Column(name = "patient_form_id")
  public Integer getPatientFormId() { return patientFormId; }
  public void setPatientFormId(Integer patientFormId) { this.patientFormId = patientFormId; }
  
  @Transient
  public Date getPortalInviteDate() { return portalInviteDate; }
  public void setPortalInviteDate(Date portalInviteDate) { this.portalInviteDate = portalInviteDate; }

  @Column(name = "postal_code")
  public String getPostalCode() { return postalCode; }
  public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
  
  @Column(name = "profession")
  public String getProfession() { return profession; }
  public void setProfession(String profession) { this.profession = profession; }
  
  @Transient
  public String getRelation() { return relation; }
  public void setRelation(String relation) { this.relation = relation; }

  @Column(name = "street_address1")
  public String getStreetAddress1() { return streetAddress1; }
  public void setStreetAddress1(String streetAddress1) { this.streetAddress1 = streetAddress1; }

  @JoinColumn(name = "us_state", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public USState getUsState() { return usState; }
  public void setUsState(USState usState) { this.usState = usState; }

  @Column(name = "address_same_as_client", nullable = false)
  public Boolean getAddressSameAsClient() {
    return addressSameAsClient;
  }

  public void setAddressSameAsClient(Boolean addressSameAsClient) {
    this.addressSameAsClient = addressSameAsClient;
  }
  
  @Override
  public ValidationResult validateBeforeUpdate(AppService service, String property, final Object value, final Integer id) throws Exception{
    PatientDAO patientDAO = service.getPatientDAO();
    if (property.equals("email")) {
      Boolean exists = patientDAO.findExistingGuardianByEmail((String)value, id) != null;
      if (exists) {
        return new ValidationResult().invalid(Messages.exists(Guardian.class), Code.RETURN_CODE_ENTITY_EXISTS);
      } 
    }
    return super.validateBeforeUpdate(service, property, value, id);
  }


  
}
