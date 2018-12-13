package com.wdeanmedical.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "calendar_event")
public class CalendarEvent extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 4685445741037638706L;
  public static String[] PHI_FIELDS = new String[] {};
  
  public static final int APPT = 1;  
  public static final int TASK = 2;  
  
  private Integer eventType;
  private Boolean timeSpecified = false;
  private Date startTime;
  private Date endTime;
  private boolean override;
  private Patient patient;
  private User clinician;
  private User user;
  private String title;
  private String apptType;
  private String desc;
  private Integer taskId;
  private String clinicianName;
  private String patientName;

  public CalendarEvent() {
  }


  @Column(name = "event_type")
  public Integer getEventType() { return eventType; }
  public void setEventType(Integer eventType) { this.eventType = eventType; }

  @Column(name = "time_specified")
  public Boolean getTimeSpecified() { return timeSpecified; }
  public void setTimeSpecified(Boolean timeSpecified) { this.timeSpecified = timeSpecified; }

  @Column(name = "start_time")
  public Date getStartTime() { return startTime; }
  public void setStartTime(Date startTime) { this.startTime = startTime; }

  @Column(name = "end_time")
  public Date getEndTime() { return endTime; }
  public void setEndTime(Date endTime) { this.endTime = endTime; }

  @Column(name = "title")
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }

  @Column(name = "description", columnDefinition = "text")
  public String getDesc() { return desc; }
  public void setDesc(String desc) { this.desc = desc; }
  
  @JoinColumn(name = "patient", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public Patient getPatient() { return patient; }
  public void setPatient(Patient patient) { this.patient = patient; }

  @JoinColumn(name = "clinician", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public User getClinician() { return clinician; }
  public void setClinician(User clinician) { this.clinician = clinician; }
  
  @Column(name = "appt_type")
  public String getApptType() { return apptType; }
  public void setApptType(String apptType) { this.apptType = apptType; }

  @JoinColumn(name = "user", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public User getUser() { return user; }
  public void setUser(User user) { this.user = user; }

  @Column(name = "override")
  public boolean isOverride() { return override; }
  public void setOverride(boolean override) { this.override = override; }

  @Column(name = "task_id")
  public Integer getTaskId() { return taskId; }
  public void setTaskId(Integer taskId) { this.taskId = taskId; }

  @Transient
  public String getClinicianName() { return clinicianName; }
  public void setClinicianName(String clinicianName) { this.clinicianName = clinicianName; }

  @Transient
  public String getPatientName() { return patientName; }
  public void setPatientName(String patientName) { this.patientName = patientName; }

}
