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
@Table(name = "client_contact_form")
@Inheritance(strategy = InheritanceType.JOINED)
public class ClientContact extends WDMForm implements Serializable {
  private static final long serialVersionUID = 4322900973195279983L;
  public static final String NAME = "child_contact";
  public static String[] PHI_FIELDS = new String[] {};
  
  private String firstName;
  private String middleName;
  private String lastName;
  private Date dob;
  private String signer;
  private String signerRelationship;
  private Boolean voicemailOk;
  private Boolean msgOk;
  private String msgNames;
  private Boolean cellMsgOk;
  private Boolean textOk;
  private Boolean textWaiverSigned;
  private Boolean callWorkOk;
  private Boolean emailOk;
  private String email;
  private Boolean msgWorkOk;
  private Boolean contactOther;
  private String contactOtherDetail;
  private Boolean noInfo;
  private String homePhone;
  private String workPhone;
  private String cellPhone;
  private String completedBy;
  private Date completedByDate;
  
  public ClientContact() { 
    this.name = ClientContact.NAME;
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
  
  @Column(name = "signer")
  public String getSigner() { return signer; }
  public void setSigner(String signer) { this.signer = signer; }
  
  @Column(name = "signer_rel")
  public String getSignerRelationship() { return signerRelationship; }
  public void setSignerRelationship(String signerRelationship) { this.signerRelationship = signerRelationship; }
  
  @Column(name = "vm_ok")
  public Boolean getVoicemailOk() { return voicemailOk; }
  public void setVoicemailOk(Boolean voicemailOk) { this.voicemailOk = voicemailOk; }
  
  @Column(name = "msg_ok")
  public Boolean getMsgOk() { return msgOk; }
  public void setMsgOk(Boolean msgOk) { this.msgOk = msgOk; }
  
  @Column(name = "msg_names")
  public String getMsgNames() { return msgNames; }
  public void setMsgNames(String msgNames) { this.msgNames = msgNames; }
  
  @Column(name = "cell_msg_ok")
  public Boolean getCellMsgOk() { return cellMsgOk; }
  public void setCellMsgOk(Boolean cellMsgOk) { this.cellMsgOk = cellMsgOk; }
  
  @Column(name = "text_ok")
  public Boolean getTextOk() { return textOk; }
  public void setTextOk(Boolean textOk) { this.textOk = textOk; }
  
  @Column(name = "text_waiver")
  public Boolean getTextWaiverSigned() { return textWaiverSigned; }
  public void setTextWaiverSigned(Boolean textWaiverSigned) { this.textWaiverSigned = textWaiverSigned; }
  
  @Column(name = "call_work_ok")
  public Boolean getCallWorkOk() { return callWorkOk; }
  public void setCallWorkOk(Boolean callWorkOk) { this.callWorkOk = callWorkOk; }
  
  @Column(name = "msg_work_ok")
  public Boolean getMsgWorkOk() { return msgWorkOk; }
  public void setMsgWorkOk(Boolean msgWorkOk) { this.msgWorkOk = msgWorkOk; }
  
  @Column(name = "contact_other")
  public Boolean getContactOther() { return contactOther; }
  public void setContactOther(Boolean contactOther) { this.contactOther = contactOther; }
  
  @Column(name = "contact_other_detail")
  public String getContactOtherDetail() { return contactOtherDetail; }
  public void setContactOtherDetail(String contactOtherDetail) { this.contactOtherDetail = contactOtherDetail; }
  
  @Column(name = "no_info")
  public Boolean getNoInfo() { return noInfo; }
  public void setNoInfo(Boolean noInfo) { this.noInfo = noInfo; }
  
  @Column(name = "home_phone")
  public String getHomePhone() { return homePhone; }
  public void setHomePhone(String homePhone) { this.homePhone = homePhone; }
  
  @Column(name = "work_phone")
  public String getWorkPhone() { return workPhone; }
  public void setWorkPhone(String workPhone) { this.workPhone = workPhone; }
  
  @Column(name = "cell_phone")
  public String getCellPhone() { return cellPhone; }
  public void setCellPhone(String cellPhone) { this.cellPhone = cellPhone; }
  
  @Column(name = "completed_by")
  public String getCompletedBy() { return completedBy; }
  public void setCompletedBy(String completedBy) { this.completedBy = completedBy; }
  
  @Column(name = "completed_by_date")
  public Date getCompletedByDate() { return completedByDate; }
  public void setCompletedByDate(Date completedByDate) { this.completedByDate = completedByDate; }
  
  @Column(name = "email_ok")
  public Boolean getEmailOk() { return emailOk; }
  public void setEmailOk(Boolean emailOk) { this.emailOk = emailOk; }
  
  @Column(name = "email")
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }   
  
}
