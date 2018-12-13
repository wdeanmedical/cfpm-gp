package com.wdeanmedical.model.report;

import java.util.ArrayList;
import java.util.List;

public class ClinicianActivity {
  String clinicianName;
  Integer id;
  List<ActivityLog> activityLogs;
  public ClinicianActivity(String clinician, Integer clinicianId) {
    this.clinicianName = clinician;
    this.id = clinicianId;
    this.activityLogs = new ArrayList<ActivityLog>();
  }
  public void addActivity(ActivityLog activityLog) {
    this.activityLogs.add(activityLog);
  }
}
