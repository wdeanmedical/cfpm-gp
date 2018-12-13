package com.wdeanmedical.dto; 

import java.util.Date;

import com.wdeanmedical.entity.CalendarEvent;


public class CalendarDTO extends AppDTO {
  public String apptFrom;
  public Integer apptLengthInMinutes;
  public String apptTo;
  public String apptType;
  public CalendarEvent calendarEvent;
  public Integer dayOfWeek;
  public String endTime;
  public Date newApptEndTime;
  public Date newApptStartTime;
  public Boolean notifyRecipients;
  public Boolean notifyBCCRecipients;
  public Boolean notifyCCRecipients;
  public String preferredTimes;
  public String startTime;
}
