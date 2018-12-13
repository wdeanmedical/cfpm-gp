package com.wdeanmedical.model.report;

import java.util.ArrayList;
import java.util.List;

public class PatientActivity {
  String patientName;
  Integer id;
  List<ActivityLog> activityLogs;
  public PatientActivity(String patient, Integer patientId) {
    this.patientName = patient;
    this.id = patientId;
    this.activityLogs = new ArrayList<ActivityLog>();
  }
  public void addActivity(ActivityLog activityLog) {
    this.activityLogs.add(activityLog);
  }
}
