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
@Table(name = "consent_form")
@Inheritance(strategy = InheritanceType.JOINED)
public class Consent extends WDMForm implements Serializable {
  
  private static final long serialVersionUID = -319647803868784657L;
  public static String[] PHI_FIELDS = new String[] {};
  public static final String NAME = "consent";
  
  private String clientName;
  private Date dob;
  private Boolean signed;
  private String respName;
  private Date respDate;
  private String clientRel;
  private String witness;
  private Date witnessDate;
  private String initials0;
  private String initials1;
  private String initials2;
  private String initials3;
  private String initials4;
  private String initials5;
  private String initials6;
  private String initials7;
  private String initials8;
  private String initials9;
  private String initials10;
  private String initials11;
  private String initials12;
  
  
  public Consent() { 
    this.name = Consent.NAME;
  }
  
  @Column(name = "client_name")
  public String getClientName() { return clientName; }
  public void setClientName(String clientName) { this.clientName = clientName; }
  
  @Column(name = "dob")
  public Date getDob() { return dob; }
  public void setDob(Date dob) { this.dob = dob; }
  
  @Column(name = "signed")
  public Boolean getSigned() { return signed; }
  public void setSigned(Boolean signed) { this.signed = signed; }
  
  @Column(name = "resp_name")
  public String getRespName() { return respName; }
  public void setRespName(String respName) { this.respName = respName; }
  
  @Column(name = "resp_date")
  public Date getRespDate() { return respDate; }
  public void setRespDate(Date respDate) { this.respDate = respDate; }
  
  @Column(name = "client_rel")
  public String getClientRel() { return clientRel; }
  public void setClientRel(String clientRel) { this.clientRel = clientRel; }
  
  @Column(name = "witness")
  public String getWitness() { return witness; }
  public void setWitness(String witness) { this.witness = witness; }
  
  @Column(name = "witness_date")
  public Date getWitnessDate() { return witnessDate; }
  public void setWitnessDate(Date witnessDate) { this.witnessDate = witnessDate; }
  
  @Column(name = "initials0")
  public String getInitials0() { return initials0; }
  public void setInitials0(String initials0) { this.initials0 = initials0; }

  @Column(name = "initials1")
  public String getInitials1() { return initials1; }
  public void setInitials1(String initials1) { this.initials1 = initials1; }
  
  @Column(name = "initials2")
  public String getInitials2() { return initials2; }
  public void setInitials2(String initials2) { this.initials2 = initials2; }
  
  @Column(name = "initials3")
  public String getInitials3() { return initials3; }
  public void setInitials3(String initials3) { this.initials3 = initials3; }
  
  @Column(name = "initials4")
  public String getInitials4() { return initials4; }
  public void setInitials4(String initials4) { this.initials4 = initials4; }

  @Column(name = "initials5")
  public String getInitials5() { return initials5; }
  public void setInitials5(String initials5) { this.initials5 = initials5; }

  @Column(name = "initials6")
  public String getInitials6() { return initials6; }
  public void setInitials6(String initials6) { this.initials6 = initials6; }

  @Column(name = "initials7")
  public String getInitials7() { return initials7; }
  public void setInitials7(String initials7) { this.initials7 = initials7; }

  @Column(name = "initials8")
  public String getInitials8() { return initials8; }
  public void setInitials8(String initials8) { this.initials8 = initials8; }

  @Column(name = "initials9")
  public String getInitials9() { return initials9; }
  public void setInitials9(String initials9) { this.initials9 = initials9; }

  @Column(name = "initials10")
  public String getInitials10() { return initials10; }
  public void setInitials10(String initials10) { this.initials10 = initials10; }

  @Column(name = "initials11")
  public String getInitials11() { return initials11; }
  public void setInitials11(String initials11) { this.initials11 = initials11; }

  @Column(name = "initials12")
  public String getInitials12() { return initials12; }
  public void setInitials12(String initials12) { this.initials12 = initials12; }
  
  }
