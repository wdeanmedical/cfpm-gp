package com.wdeanmedical.dto; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wdeanmedical.entity.ActivityLog;
import com.wdeanmedical.model.report.ClinicianActivity;
import com.wdeanmedical.model.report.PatientActivity;

public class ReportDTO extends AppDTO {

  public String activityName;
  public String ageRangeId;
  public String clinicianName;
  public String dateFrom;
  public String dateTo;
  public Integer genderId;
  public String goalId;
  public String patientName;
  public String usState;
  
  public List<ActivityLog> activityLogs;
  public Map<String, List<String>> reportTypeAheads = new HashMap<String, List<String>>();
  public Map<String, Set<String>> salesLeadCitySearchTypeAheads = new HashMap<String, Set<String>>();
  public Map<String, Set<String>> salesLeadFirstNameSearchTypeAheads = new HashMap<String, Set<String>>();
  public Map<String, Set<String>> salesLeadLastNameSearchTypeAheads = new HashMap<String, Set<String>>();
  public Map<String, Set<String>> salesLeadMiddleNameSearchTypeAheads = new HashMap<String, Set<String>>();
  public Map<String, Set<String>> salesLeadUSStateSearchTypeAheads = new HashMap<String, Set<String>>();
  public List<ClinicianActivity> cliniciansActivity;
  public List<PatientActivity> patientsActivity;

}
