package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "cc_auth_form")
@Inheritance(strategy = InheritanceType.JOINED)
public class CCAuth extends WDMForm implements Serializable {
  
  private static final long serialVersionUID = 6849301935504295639L;
  public static final String NAME = "cc_auth";
  public static String[] PHI_FIELDS = new String[] {};
  
  private String cardholderName;
  private String ccType;
  private String ccNumber;
  private String ccExp;
  private String cvc;
  private String postalCode;
  private String email;
  private String initials;
  private Boolean sig;
  
  
  public CCAuth() { 
    this.name = ClientRights.NAME;
  }


  @Column(name = "cardholder_name")
  public String getCardholderName() { return cardholderName; }
  public void setCardholderName(String cardholderName) { this.cardholderName = cardholderName; }

  @Column(name = "cc_type")
  public String getCcType() { return ccType; }
  public void setCcType(String ccType) { this.ccType = ccType; }

  @Column(name = "cc_number")
  public String getCcNumber() { return ccNumber; }
  public void setCcNumber(String ccNumber) { this.ccNumber = ccNumber; }

  @Column(name = "cc_exp")
  public String getCcExp() { return ccExp; }
  public void setCcExp(String ccExp) { this.ccExp = ccExp; }

  @Column(name = "cvc")
  public String getCvc() { return cvc; }
  public void setCvc(String cvc) { this.cvc = cvc; }

  @Column(name = "postal_code")
  public String getPostalCode() { return postalCode; }
  public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

  @Column(name = "email")
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  @Column(name = "initials")
  public String getInitials() { return initials; }
  public void setInitials(String initials) { this.initials = initials; }

  @Column(name = "sig")
  public Boolean getSig() { return sig; }
  public void setSig(Boolean sig) { this.sig = sig; }
  
}

