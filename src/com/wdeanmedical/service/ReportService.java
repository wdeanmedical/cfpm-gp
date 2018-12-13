package com.wdeanmedical.service;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import com.wdeanmedical.dto.ReportDTO;
import com.wdeanmedical.entity.ActivityLog;
import com.wdeanmedical.entity.Gender;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.SalesLead;
import com.wdeanmedical.entity.SalesLeadAgeRange;
import com.wdeanmedical.entity.SalesLeadGoal;
import com.wdeanmedical.entity.USState;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.model.report.ClinicianActivity;
import com.wdeanmedical.model.report.PatientActivity;
import com.wdeanmedical.persistence.ReportDAO;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.Util;

public class ReportService extends AppService {

  private static Log log = LogFactory.getLog(ReportService.class);

  private ReportDAO reportDAO;


  public ReportService() throws MalformedURLException {
    super();
    reportDAO = (ReportDAO) wac.getBean("reportDAO");
  }
  

  public void exportCsv(ReportDTO dto, HttpServletResponse response) throws Exception {

    getActivityLogs(dto);

    HSSFWorkbook workbook = new HSSFWorkbook();
    // create a new Excel sheet
    HSSFSheet sheet = workbook.createSheet("Activity Logs");
    sheet.setDefaultColumnWidth(30);

    // create style for header cells
    CellStyle style = workbook.createCellStyle();
    Font font = workbook.createFont();
    font.setFontName("Arial");
    style.setFillForegroundColor(HSSFColor.BLUE.index);
    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    font.setColor(HSSFColor.WHITE.index);
    style.setFont(font);

    // create header row
    HSSFRow header = sheet.createRow(0);

    header.createCell(0).setCellValue("User Id");
    header.getCell(0).setCellStyle(style);

    header.createCell(1).setCellValue("Patient Id");
    header.getCell(1).setCellStyle(style);

    header.createCell(2).setCellValue("Time Performed");
    header.getCell(2).setCellStyle(style);

    header.createCell(3).setCellValue("Clinician Id");
    header.getCell(3).setCellStyle(style);

    header.createCell(4).setCellValue("Encounter Id");
    header.getCell(4).setCellStyle(style);

    header.createCell(5).setCellValue("Field Name");
    header.getCell(5).setCellStyle(style);

    header.createCell(6).setCellValue("Activity");
    header.getCell(6).setCellStyle(style);

    header.createCell(7).setCellValue("Module");
    header.getCell(7).setCellStyle(style);

    // create data rows
    int rowCount = 1;

    for (Object item : dto.list) {
      ActivityLog activityLog = (ActivityLog) item;
      HSSFRow aRow = sheet.createRow(rowCount++);
      if (activityLog.getUserName() != null) { aRow.createCell(0).setCellValue(activityLog.getUserName()); } else { aRow.createCell(0).setCellValue(""); }
      if (activityLog.getPatientName() != null) { aRow.createCell(1).setCellValue(activityLog.getPatientName()); } else { aRow.createCell(1).setCellValue(""); }
      if (activityLog.getTimePerformed() != null) { aRow.createCell(2).setCellValue(activityLog.getTimePerformed()); } else { aRow.createCell(2).setCellValue(""); }
      if (activityLog.getClinicianName() != null) { aRow.createCell(3).setCellValue(activityLog.getClinicianName()); } else { aRow.createCell(3).setCellValue(""); }
      if (activityLog.getEncounterId() != null) { aRow.createCell(4).setCellValue(activityLog.getEncounterId()); } else { aRow.createCell(4).setCellValue(""); }
      if (activityLog.getFieldName() != null) { aRow.createCell(5).setCellValue(activityLog.getFieldName()); } else { aRow.createCell(5).setCellValue(""); }
      if (activityLog.getActivity() != null) { aRow.createCell(6).setCellValue(activityLog.getActivity()); } else { aRow.createCell(6).setCellValue(""); }
      if (activityLog.getModule() != null) { aRow.createCell(7).setCellValue(activityLog.getModule()); } else { aRow.createCell(7).setCellValue(""); }
    }
    
    response.setContentType("application/vnd.ms-excel");
    response.setHeader("Content-Disposition", "attachment; filename=ActivityLog.xls");
    workbook.write(response.getOutputStream());
    response.getOutputStream().close();
    logEvent(dto, ActivityLog.EXPORT_REPORT, "ReportService exportCsv()", null, null);
  }
  
  
  
  public void filterSalesLeads(ReportDTO dto) throws Exception {
    SalesLeadAgeRange salesLeadAgeRange = null;
    USState usState = null;
    Gender gender = null;  
    Integer id = null;
    SalesLeadGoal salesLeadGoal = null;
  
    try { id = new Integer(dto.ageRangeId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null){ salesLeadAgeRange = reportDAO.findById(SalesLeadAgeRange.class, id); }
    if (null != dto.usState){ usState = reportDAO.findUSStateByName(dto.usState); }
    
    try { id = new Integer(dto.genderId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) { gender = reportDAO.findById(Gender.class, id); }
    
    try { id = new Integer(dto.goalId); } catch (NumberFormatException nfe) {id = null;}
    if (id != null) { salesLeadGoal = reportDAO.findById(SalesLeadGoal.class, id); }
    
    dto.list = reportDAO.filterSalesLeads(salesLeadAgeRange, usState, gender, salesLeadGoal); 
  }
  
  
  
  private List<ActivityLog> _getActivityLogs(ReportDTO dto) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    Date dateFrom = null;
    Date dateTo = null;
    
    if (dto.dateFrom.length() > 0) { dateFrom = sdf.parse(dto.dateFrom); }
    if (dto.dateTo.length() > 0) { dateTo = sdf.parse(dto.dateTo); }   
    
    List<Integer> userIds = new ArrayList<Integer>();
    List<Integer> patientIds = new ArrayList<Integer>();
    
    if (dto.clinicianName.length() > 0) { 
      userIds = reportDAO.findClinicianIdsByFullName(dto.clinicianName);
    }
    if (dto.patientName.length() > 0) { 
      patientIds = reportDAO.findPatientIdsByFullName(dto.patientName);
    }    
    
    return reportDAO.findActivityLogs(dateFrom, dateTo, userIds, dto.activityName, patientIds);
  }
  
  
  
  public void getActivityLogs(ReportDTO dto) throws Exception {
    dto.activityLogs = _getActivityLogs(dto);
    logEvent(dto, ActivityLog.VIEW_ACTIVITY_LOGS, "ReportService getActivityLogs()", null, null);
  }
  
  
  
  public void getClinicianActivity(ReportDTO dto) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    Date dateFrom = null;
    Date dateTo = null;
    
    List<ClinicianActivity> cliniciansActivity = new ArrayList<ClinicianActivity>();
    
    if (dto.dateFrom.length() > 0) { dateFrom = sdf.parse(dto.dateFrom); }
    if (dto.dateTo.length() > 0) { dateTo = Util.datePlusOne(sdf.parse(dto.dateTo)); }    
    
    List<Integer> userIds = new ArrayList<Integer>();
    List<Integer> patientIds = new ArrayList<Integer>();
  
    Boolean clinicianPatientNotFound = false;
    
    if (dto.clinicianName.length() > 0) { 
      userIds = reportDAO.findClinicianIdsByFullName(dto.clinicianName);
      if (userIds.isEmpty()) {
        clinicianPatientNotFound = true ;
      }
    }
    if (dto.patientName.length() > 0) { 
      patientIds = reportDAO.findPatientIdsByFullName(dto.patientName);
      if (patientIds.isEmpty()) {
        clinicianPatientNotFound = true ;
      }
    }   
    if (!clinicianPatientNotFound) {
      cliniciansActivity = reportDAO.findActivityByClinician(dateFrom, dateTo, userIds, dto.activityName, patientIds);
    }
    dto.cliniciansActivity = cliniciansActivity;
    logEvent(dto, ActivityLog.VIEW_CLINICIAN_ACTIVITY, "ReportService getClinicianActivity()", null, null);
  } 
  
  
  
  public void getPatientActivity(ReportDTO dto) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    Date dateFrom = null;
    Date dateTo = null;
    
    if (dto.dateFrom.length() > 0) { dateFrom = sdf.parse(dto.dateFrom); }
    if (dto.dateTo.length() > 0) { dateTo = Util.datePlusOne(sdf.parse(dto.dateTo)); }    
    
    List<Integer> userIds = new ArrayList<Integer>();
    List<Integer> patientIds = new ArrayList<Integer>();
  
    List<PatientActivity> patientsActivity =  new ArrayList<PatientActivity>();
    Boolean clinicianPatientNotFound = false;
    
    if (dto.clinicianName.length() > 0) { 
      userIds = reportDAO.findClinicianIdsByFullName(dto.clinicianName);
      if (userIds.isEmpty()) {
        clinicianPatientNotFound = true ;
      }
    }
    if (dto.patientName.length() > 0) { 
      patientIds = reportDAO.findPatientIdsByFullName(dto.patientName);
      if (patientIds.isEmpty()) {
        clinicianPatientNotFound = true;
      }
    }   
    if (!clinicianPatientNotFound) {
      patientsActivity = reportDAO.findActivityByPatient(dateFrom, dateTo, userIds, dto.activityName, patientIds);
    }
    dto.patientsActivity = patientsActivity;
    logEvent(dto, ActivityLog.VIEW_ACTIVITY_LOGS, "ReportService getPatientActivity()", null, null);
  }
  
  
  public void getReportList(ReportDTO dto) throws Exception {
    dto.list =  reportDAO.findReportList();
  }
  
  public boolean getReportSearchTypeAheads(ReportDTO dto) throws Exception {
    List<User> users = reportDAO.findUsers();
    List<Patient> patients = reportDAO.findPatients();
    List<String> userFullNames = new ArrayList<String>();
    List<String> patientFullNames = new ArrayList<String>();
    
    for(User user : users) {
      userFullNames.add(Util.buildFullName(user.getFirstName(), user.getMiddleName(), user.getLastName()));
    }
    
    for (Patient patient : patients) {
      DataEncryptor.decryptPatient(patient);
      patientFullNames.add(Util.buildFullName(patient.getFirstName(), patient.getMiddleName(), patient.getLastName()));
    }
    
    dto.reportTypeAheads.put("userFullNames", userFullNames);    
    dto.reportTypeAheads.put("patientFullNames", patientFullNames);
    dto.reportTypeAheads.put("clinicianActivities", ActivityLog.ACTIVITIES);
    
    return true;
  }
  
  
  
  public boolean getSalesLeadSearchTypeAheads(ReportDTO dto) throws Exception {
    List<USState> usStateList = reportDAO.findUSStates();
    Set<String> usStates = new TreeSet<String>();
    for(USState usState : usStateList){
      if(usState.getName() != null){
        usStates.add(usState.getName());
      }
    }
    
    dto.salesLeadUSStateSearchTypeAheads.put("usStates", usStates);
    return true;
  }

  
  
  public List<SalesLead> getSalesLeads(ReportDTO dto) throws Exception {    
    return reportDAO.findSalesLeads(); 
  }
  
  
  public void getWaitListPatients(ReportDTO dto) throws Exception {
    List<Integer> userIds = null;
    List<Integer> patientIds = null;
    
    if (dto.clinicianName.length() > 0) { 
      userIds = reportDAO.findClinicianIdsByFullName(dto.clinicianName);
    }
    if (dto.patientName.length() > 0) { 
      patientIds = reportDAO.findPatientIdsByFullName(dto.patientName);
    }   
    
    List<Patient> patients  = reportDAO.findWaitListPatients(userIds, patientIds);
    for (Patient patient : patients) {
      DataEncryptor.decryptPatient(patient);
    }
    dto.list = patients;
    logEvent(dto, ActivityLog.VIEW_WAIT_LIST, "ReportService getWaitListPatients()", null, null);
  }

}