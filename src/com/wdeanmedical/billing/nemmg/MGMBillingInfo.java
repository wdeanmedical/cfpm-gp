package com.wdeanmedical.billing.nemmg;

import java.util.ArrayList;

public class MGMBillingInfo {

  private String practice;

  private String facilityAddress1;
  private String facilityAddress2;
  private String facilityCity;
  private String facilityState;
  private String facilityZip;

  private String patientName;
  private String patientAddress1;
  private String patientAddress2;
  private String patientCity;
  private String patientState;
  private String patientZip;
  private String patientDOB;
  private String patientSex;

  private String insPrimName;
  private String insPrimSubId;
  private String insPrimGroupNum;
  private String insSecName;
  private String insSecSubId;
  private String insSecGroupNum;

  private String dateOfService;

  private String guarantor;
  private String provider;

  private ArrayList<String> diags = new ArrayList<String>();

  private ArrayList<CPTInfo> cptInfo = new ArrayList<CPTInfo>();

  private double patientPayment;

  public String getPractice() { return practice; }
  public void setPractice(String practice) { this.practice = practice; }

  public String getFacilityAddress1() { return facilityAddress1; }
  public void setFacilityAddress1(String facilityAddress1) { this.facilityAddress1 = facilityAddress1; }

  public String getFacilityAddress2() { return facilityAddress2; }
  public void setFacilityAddress2(String facilityAddress2) { this.facilityAddress2 = facilityAddress2; }

  public String getFacilityCity() { return facilityCity; }
  public void setFacilityCity(String facilityCity) { this.facilityCity = facilityCity; }

  public String getFacilityState() { return facilityState; }
  public void setFacilityState(String facilityState) { this.facilityState = facilityState; }

  public String getFacilityZip() { return facilityZip; }
  public void setFacilityZip(String facilityZip) { this.facilityZip = facilityZip; }

  public String getPatientName() { return patientName; }
  public void setPatientName(String patientName) { this.patientName = patientName; }

  public String getPatientAddress1() { return patientAddress1; }
  public void setPatientAddress1(String patientAddress1) { this.patientAddress1 = patientAddress1; }

  public String getPatientAddress2() { return patientAddress2; }
  public void setPatientAddress2(String patientAddress2) { this.patientAddress2 = patientAddress2; }

  public String getPatientCity() { return patientCity; }
  public void setPatientCity(String patientCity) { this.patientCity = patientCity; }

  public String getPatientState() { return patientState; }
  public void setPatientState(String patientState) { this.patientState = patientState; }

  public String getPatientZip() { return patientZip; }
  public void setPatientZip(String patientZip) { this.patientZip = patientZip; }

  public String getPatientDOB() { return patientDOB; }
  public void setPatientDOB(String patientDOB) { this.patientDOB = patientDOB; }

  public String getPatientSex() { return patientSex; }
  public void setPatientSex(String patientSex) { this.patientSex = patientSex; }

  public String getInsPrimName() { return insPrimName; }
  public void setInsPrimName(String insPrimName) { this.insPrimName = insPrimName; }

  public String getInsPrimSubId() { return insPrimSubId; }
  public void setInsPrimSubId(String insPrimSubId) { this.insPrimSubId = insPrimSubId; }

  public String getInsPrimGroupNum() { return insPrimGroupNum; }
  public void setInsPrimGroupNum(String insPrimGroupNum) { this.insPrimGroupNum = insPrimGroupNum; }

  public String getInsSecName() { return insSecName; }
  public void setInsSecName(String insSecName) { this.insSecName = insSecName; }

  public String getInsSecSubId() { return insSecSubId; }
  public void setInsSecSubId(String insSecSubId) { this.insSecSubId = insSecSubId; }

  public String getInsSecGroupNum() { return insSecGroupNum; }
  public void setInsSecGroupNum(String insSecGroupNum) { this.insSecGroupNum = insSecGroupNum; }

  public String getDateOfService() { return dateOfService; }
  public void setDateOfService(String dateOfService) { this.dateOfService = dateOfService; }

  public String getGuarantor() { return guarantor; }
  public void setGuarantor(String guarantor) { this.guarantor = guarantor; }

  public String getProvider() { return provider; }
  public void setProvider(String provider) { this.provider = provider; }

  public ArrayList<String> getDiags() { return diags; }
  public void addDiag(String diag) { this.diags.add(diag); }

  public void setDiags(ArrayList<String> diags) { this.diags = diags; }
  public ArrayList<CPTInfo> getCptInfo() { return cptInfo; }

  public void addCptInfo(CPTInfo cptInfo) { this.cptInfo.add(cptInfo); }
  public void setCptInfo(ArrayList<CPTInfo> cptInfo) { this.cptInfo = cptInfo; }

  public double getPatientPayment() { return patientPayment; }
  public void setPatientPayment(double patientPayment) { this.patientPayment = patientPayment; }

}
