package com.wdeanmedical.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.wdeanmedical.dto.CalendarDTO;
import com.wdeanmedical.service.CalendarService;
import com.wdeanmedical.util.JSONUtils;
import com.wdeanmedical.util.Util;

public class CalendarController extends AppController  {
  
  private static final long serialVersionUID = 8930535029294936488L;

  private static final Logger log = Logger.getLogger(CalendarController.class);
  
  private CalendarService calendarService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    try{
      calendarService = new CalendarService();
    }
    catch(MalformedURLException e){
      e.printStackTrace();
    }
  }
    
  @Override
  public void doPost( HttpServletRequest request, HttpServletResponse response) {
    String returnString = "";
    String pathInfo = request.getPathInfo();
    String servletPath = request.getServletPath();
    boolean isBinaryResponse = false;
    
    String data = request.getParameter("data");
    Gson gson = JSONUtils.getGson();
    CalendarDTO dto = gson.fromJson(data, CalendarDTO.class); 
    if (dto == null) { dto = new CalendarDTO(); }
    Util.logServletPath(log, dto.module, servletPath, pathInfo); 
     
    try { 
      if (isValidSession(request, response) == false) {
        appService.logout(dto);  
      }
      else { 
        // place sessionId and module in dto for GET requests
        if (dto.sessionId == null) {
          dto.sessionId = request.getParameter("sessionId");
          dto.module = request.getParameter("module");
        }
        
        if (pathInfo.equals("/changeApptTime")) { calendarService.changeApptTime(dto); }
          else if (pathInfo.equals("/changeTaskTime")) { calendarService.changeTaskTime(dto); }
          else if (pathInfo.equals("/deleteAppt")) { calendarService.deleteAppt(dto, request); }
          else if (pathInfo.equals("/deleteTask")) { calendarService.deleteTask(dto, request); }
          else if (pathInfo.equals("/getCalendarEvent")) { calendarService.getCalendarEvent(dto); }
          else if (pathInfo.equals("/getCalendarEvents")) { calendarService.getCalendarEvents(dto); }
          else if (pathInfo.equals("/newAppt")) { calendarService.newAppt(dto, request); }
          else if (pathInfo.equals("/suggestApptSlot")) { calendarService.suggestApptSlot(dto); }
          else if (pathInfo.equals("/updateAppt")) { calendarService.updateAppt(dto); }
      }
      
      returnString = gson.toJson(dto);
      ServletOutputStream  out = null;
      response.setContentType("text/plain");
     
      if (isBinaryResponse == false) { 
        out = response.getOutputStream();
        out.println(returnString);
        out.close();
      }
      else { 
        PrintWriter ajaxOut = response.getWriter();
        ajaxOut.write(returnString);
        ajaxOut.close();
      }
    }  
    catch( IOException ioe ) {
      ioe.printStackTrace();
    } 
    catch( Exception e ) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);  
  }

}