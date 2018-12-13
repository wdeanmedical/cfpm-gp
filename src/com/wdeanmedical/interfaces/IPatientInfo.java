package com.wdeanmedical.interfaces;

import com.wdeanmedical.entity.USState;

public interface IPatientInfo {

  String getPrimaryPhone();

  Integer getId();

  String getStreetAddress2();

  String getStreetAddress1();

  USState getUsState();

  String getEmail();

  String getPostalCode();

  String getCity();

}