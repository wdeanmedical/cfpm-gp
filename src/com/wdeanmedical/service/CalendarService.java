package com.wdeanmedical.service;


import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wdeanmedical.core.Statics;
import com.wdeanmedical.dto.CalendarDTO;
import com.wdeanmedical.entity.ActivityLog;
import com.wdeanmedical.entity.CalendarEvent;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.ClientSession;
import com.wdeanmedical.entity.Guardian;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.SalesLeadTask;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.MailHandler;
import com.wdeanmedical.util.Util;

public class CalendarService extends AppService {

  private static Log log = LogFactory.getLog(CalendarService.class);
  
  public CalendarService() throws MalformedURLException {
    super();
  }
  
  public String buildAppointmentMessage(CalendarDTO dto) throws Exception {
    Patient patient = appDAO.findById(Patient.class, dto.patientId);
    DataEncryptor.decryptPatient(patient); 
    String message = "Appointment Request from: ";
    String patientName = Util.buildFullName(patient.getFirstName(), patient.getMiddleName(), patient.getLastName());
    message += patientName + "<br>";
    message += "Visit Reason: " + dto.visitReason + "<br>"; 
    User clinician = appDAO.findById(User.class, dto.clinicianId);
    User altClinician = appDAO.findById(User.class, dto.altClinicianId);
    String clinicianName = Util.buildFullName(clinician.getFirstName(), clinician.getMiddleName(), clinician.getLastName());
    message += "Wants to See: " + clinicianName + "<br>"; 
    if (altClinician != null) {
      String altClinicianName = Util.buildFullName(altClinician.getFirstName(), altClinician.getMiddleName(), altClinician.getLastName());
      message += "Would See: " + altClinicianName + "<br>"; 
    }
    message += "Preferred Dates: " + dto.apptFrom + " - " + dto.apptTo + "<br>"; 
    message += "Preferred Times: " + dto.preferredTimes + "<br>"; 
    dto.patientId = patient.getId();
    logEvent(dto, ActivityLog.VIEW_PATIENT, "CalendarService buildAppointmentMessage()", null, null);
    return message;
  }
  
  
  
  public void changeApptTime(CalendarDTO dto) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    CalendarEvent appt = calendarDAO.findById(CalendarEvent.class, dto.id);
    Date startTime; try { startTime = sdf.parse(dto.startTime); } catch (ParseException pe) {startTime = null;}
    appt.setStartTime(startTime);
    Date endTime; try { endTime = sdf.parse(dto.endTime); } catch (ParseException pe) {endTime = null;}
    appt.setEndTime(endTime);
    calendarDAO.update(appt);
    logEvent(dto, ActivityLog.CHANGE_APPT_TIME, "CalendarService changeApptTime()", null, null);
  }
  
  
  
  public void changeTaskTime(CalendarDTO dto) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    CalendarEvent calendarEvent = calendarDAO.findById(CalendarEvent.class, dto.id);
    Date startTime; try { startTime = sdf.parse(dto.startTime); } catch (ParseException pe) {startTime = null;}
    calendarEvent.setStartTime(startTime);
    Date endTime; try { endTime = sdf.parse(dto.endTime); } catch (ParseException pe) {endTime = null;}
    calendarEvent.setEndTime(endTime);
    calendarDAO.update(calendarEvent);
    SalesLeadTask salesLeadTask = calendarDAO.findById(SalesLeadTask.class, calendarEvent.getTaskId());
    salesLeadTask.setDueDate(startTime);
    calendarDAO.update(salesLeadTask);
    logEvent(dto, ActivityLog.CHANGE_TASK_TIME, "CalendarService changeTaskTime()", null, null);
  }
    

  
  public void deleteAppt(CalendarDTO dto, HttpServletRequest request) throws Exception {
    CalendarEvent appt = calendarDAO.findById(CalendarEvent.class, dto.id);
    calendarDAO.delete(appt);
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    String apptTimeString = sdf.format(appt.getStartTime()); 
    User clinician = appt.getClinician();
    Patient patient = appt.getPatient();
    DataEncryptor.decryptPatient(patient);
    dto.patientId = patient.getId();
    String clinicianFullName = clinician.getFirstName() + " " + clinician.getLastName();
    String title = "CalendarEvent on " + apptTimeString + " with " + clinicianFullName + " cancelled.";
    Map<String,String> customAttributes = new HashMap<String,String>();
    customAttributes.put("apptTime", apptTimeString);
    patient.setGuardian(getPatientService().getPatientGuardian(patient.getId()));
    MailHandler.sendSystemEmail("appt_cancelled", title, patient, clinician, request, title, customAttributes, null);
    logEvent(dto, ActivityLog.DELETE_APPT, "CalendarService deleteAppt()", null, null);
  }
  
  
  
  public void deleteTask(CalendarDTO dto, HttpServletRequest request) throws Exception {
    CalendarEvent calendarEvent = calendarDAO.findById(CalendarEvent.class, dto.id);
    calendarDAO.delete(calendarEvent);
    SalesLeadTask salesLeadTask = calendarDAO.findById(SalesLeadTask.class, calendarEvent.getTaskId());
    calendarDAO.delete(salesLeadTask);
    logEvent(dto, ActivityLog.DELETE_TASK, "CalendarService deleteTask()", null, null);
  }
  
  
  
  public void getCalendarEvent(CalendarDTO dto) throws Exception {
    CalendarEvent calendarEvent = appDAO.findById(CalendarEvent.class, dto.id);
    DataEncryptor.decryptPatient(calendarEvent.getPatient());
    dto.calendarEvent = calendarEvent;
    logEvent(dto, ActivityLog.GET_CALENDAR_EVENT, "CalendarService getCalendarEvent()", null, null);
  }
  
  
  
  public void getCalendarEvents(CalendarDTO dto) throws Exception {
  
    List<CalendarEvent> calendarEvents; 
  
    if (Statics.PORTAL_MODULE.equals(dto.module)) {
      ClientSession clientSession = appDAO.findClientSessionBySessionId(dto.sessionId);
      Patient patient = patientDAO.findPatientBySessionId(clientSession.getSessionId());
      calendarEvents = calendarDAO.findCalendarEventsByPatient(patient);
    }
    else {
      calendarEvents = calendarDAO.findAllCalendarEvents();
    }
    
    for (CalendarEvent event : calendarEvents) { 
      if (event.getPatient() != null) {
        DataEncryptor.decryptPatient(event.getPatient()); 
        event.setPatientName(Util.buildFullName(event.getPatient().getFirstName(), event.getPatient().getMiddleName(), event.getPatient().getLastName()));
      }
      if (event.getClinician() != null) {
        event.setClinicianName(Util.buildFullName(event.getClinician().getFirstName(), event.getClinician().getMiddleName(), event.getClinician().getLastName()));
      }
    }
    dto.list = calendarEvents;
    logEvent(dto, ActivityLog.GET_CALENDAR, "CalendarService getCalendarEvents()", null, null);
  }
  
  
  
  public void newAppt(CalendarDTO dto, HttpServletRequest request) throws Exception{
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    User clinician = calendarDAO.findById(User.class, dto.clinicianId);
    Patient patient = calendarDAO.findById(Patient.class, dto.patientId);
    DataEncryptor.decryptPatient(patient);
    String patientFullName = patient.getFirstName() + " " + patient.getLastName();
    String clinicianFullName = clinician.getFirstName() + " " + clinician.getLastName();
    CalendarEvent appt = new CalendarEvent();
    appt.setEventType(CalendarEvent.APPT);
    appt.setClinician(clinician);
    Date startTime; try { startTime = sdf.parse(dto.startTime); } catch (ParseException pe) {startTime = null;}
    appt.setStartTime(startTime);
    Date endTime; try { endTime = sdf.parse(dto.endTime); } catch (ParseException pe) {endTime = null;}
    appt.setEndTime(endTime);
    appt.setTimeSpecified(true);
    appt.setOverride(false);
    appt.setPatient(patient);
    appt.setTitle(patientFullName);
    appt.setApptType(dto.apptType);
    appt.setDesc(dto.desc);
    appDAO.create(appt);
    
    String apptTimeString = sdf.format(startTime); 
    String title = "CalendarEvent on " + apptTimeString + " with " + clinicianFullName;
    dto.patientId = patient.getId();
    Map<String,String> customAttributes = new HashMap<String,String>();
    customAttributes.put("apptTime", apptTimeString);
    patient.setGuardian(getPatientService().getPatientGuardian(patient.getId()));
    MailHandler.sendSystemEmail("appt_scheduled", title, patient, clinician, request, title, customAttributes, null);
    logEvent(dto, ActivityLog.CREATE_APPT, "CalendarService newAppt()", null, null);
  }
  
  
  
  public void suggestApptSlot(CalendarDTO dto) throws Exception {
    int nextInterval = 15;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    Integer apptLengthInMinutes = dto.apptLengthInMinutes;
    Date startTime; try { startTime = sdf.parse(dto.startTime); } catch (ParseException pe) {startTime = null;}
    Calendar cal = Calendar.getInstance();
    cal.setTime(startTime);
    cal.add(Calendar.MINUTE, apptLengthInMinutes); 
    Date endTime = cal.getTime();
    Date originalStartTime = new Date(startTime.getTime());
    Date originalEndTime = new Date(endTime.getTime());
    
    List<CalendarEvent> calendarEvents =  calendarDAO.findCalendarEventsByDay(startTime);
    
    for (Iterator<CalendarEvent> iter = calendarEvents.iterator(); iter.hasNext(); ) {
      CalendarEvent appt = iter.next();
      if (endTime.before(appt.getStartTime())) {
        dto.newApptStartTime = startTime;
        dto.newApptEndTime = endTime;
        return;
      }
      if (iter.hasNext()) {
        CalendarEvent nextAppt = iter.next();
        if (startTime.after(appt.getEndTime()) && endTime.before(nextAppt.getStartTime())) {
          dto.newApptStartTime = startTime;
          dto.newApptEndTime = endTime;
          return;
        }
      }
      else if (startTime.after(appt.getEndTime())) {
        dto.newApptStartTime = startTime;
        dto.newApptEndTime = endTime;
        return;
      }
      else {
        dto.newApptStartTime = appt.getEndTime();
        cal.setTime(appt.getEndTime());
        cal.add(Calendar.MINUTE, apptLengthInMinutes); 
        dto.newApptEndTime = cal.getTime();
        return;
      }
      
      cal.setTime(startTime);
      cal.add(Calendar.MINUTE, nextInterval); 
      startTime = cal.getTime();
      
      cal.setTime(endTime);
      cal.add(Calendar.MINUTE, nextInterval); 
      endTime = cal.getTime();
    }
    
    dto.newApptStartTime = originalStartTime;
    dto.newApptEndTime = originalEndTime;
  }
  
  
    
  public boolean updateAppt(CalendarDTO dto) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    CalendarEvent appt = calendarDAO.findById(CalendarEvent.class, dto.id);
    User clinician = calendarDAO.findById(User.class, dto.clinicianId);
    appt.setClinician(clinician);
    Patient patient = calendarDAO.findById(Patient.class, dto.patientId);
    appt.setPatient(patient);
    Date startTime; try { startTime = sdf.parse(dto.startTime); } catch (ParseException pe) {startTime = null;}
    appt.setStartTime(startTime);
    Date endTime; try { endTime = sdf.parse(dto.endTime); } catch (ParseException pe) {endTime = null;}
    appt.setEndTime(endTime);
    appt.setDesc(dto.desc);
    appt.setApptType(dto.apptType);
    calendarDAO.update(appt);
    logEvent(dto, ActivityLog.UPDATE_APPT, "CalendarService updateAppt()", null, null);
    return true;
  }

}
