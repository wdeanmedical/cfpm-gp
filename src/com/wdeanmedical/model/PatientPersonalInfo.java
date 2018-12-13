package com.wdeanmedical.model;

import com.wdeanmedical.interfaces.IPatientInfo;

public class PatientPersonalInfo {
  public String primaryPhone;
  public String cityStateZip;
  public String email;
  public String streetAddress;
  public PatientPersonalInfo(IPatientInfo patientInfo) {
    this.primaryPhone = patientInfo.getPrimaryPhone();
    this.streetAddress = patientInfo.getStreetAddress1() + " " + patientInfo.getStreetAddress2();
    String usState = "";
    if (patientInfo.getUsState() != null) {
      usState = patientInfo.getUsState().getName();
    }
    this.cityStateZip = patientInfo.getCity() + " " 
    + usState + " "
    + patientInfo.getPostalCode();
    this.email = patientInfo.getEmail();
  }
}
