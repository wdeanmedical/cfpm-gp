package com.wdeanmedical.query;

import java.util.Date;

public class ActivityResult {
  public Date timePerformed;
  public String activity;
  public String module;
  public String firstName;
  public String lastName;
  public String middleName;
  public Integer activityOwnerId;
  public Integer id;
  public ActivityResult() {}
  public ActivityResult(Integer activityId, Date timePerformed, String activity, 
         String module, Integer activityOwnerId, String firstName, String lastName, String middleName) {
    this.id = activityId;
    this.timePerformed = timePerformed;
    this.activity = activity;
    this.module = module;
    this.activityOwnerId = activityOwnerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
  }
}
