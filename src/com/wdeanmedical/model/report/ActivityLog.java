package com.wdeanmedical.model.report;

import java.util.Date;

public class ActivityLog {
    Date timePerformed;
    String activity;
    String module;
    Integer id;
    public ActivityLog(Integer activityId, Date timePerformed, String activity, String module) {
      this.id = activityId;
      this.timePerformed = timePerformed;
      this.activity = activity;
      this.module = module; 
    }
}
