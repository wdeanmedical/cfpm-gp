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
import com.wdeanmedical.dto.MessageDTO;
import com.wdeanmedical.service.MessageService;
import com.wdeanmedical.util.JSONUtils;
import com.wdeanmedical.util.Util;

public class MessageController extends AppController  {
  
  private static final long serialVersionUID = 8930535029294936488L;

  private static final Logger log = Logger.getLogger(MessageController.class);
  
  private MessageService messageService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    try{
      messageService = new MessageService();
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
    MessageDTO dto = gson.fromJson(data, MessageDTO.class); 
    if (dto == null) { dto = new MessageDTO(); }
    Util.logServletPath(log, dto.module, servletPath, pathInfo); 
     
    try { 
      if (isValidSession(request, response) == false) {
        appService.logout(dto);  
      }
      else { 
        if (pathInfo.equals("/deleteLetter")) { messageService.deleteLetter(dto); }
        else if (pathInfo.equals("/deleteMessage")) { messageService.deleteMessage(dto); }
        else if (pathInfo.equals("/getMessageDetails")) { messageService.getMessageDetails(dto); }
        else if (pathInfo.equals("/getLetters")) { messageService.getLetters(dto); }
        else if (pathInfo.equals("/getMessages")) { messageService.getMessages(dto); }
        else if (pathInfo.equals("/getPatientSentMessages")) { /* messageService.getPatientSentMessages(dto);*/ }
        else if (pathInfo.equals("/getPatientToClinicianMessages")) { messageService.getPatientToClinicianMessages(dto); }
        else if (pathInfo.equals("/getValidMessageRecipients")) { messageService.getValidMessageRecipients(dto, request); }
        else if (pathInfo.equals("/processPatientMessage")) { messageService.processPatientMessage(dto); }
        else if (pathInfo.equals("/requestAppointment")) { messageService.processPatientMessage(dto); }
        else if (pathInfo.equals("/saveLetter")) { messageService.saveOrSendLetter(dto, request); }
        else if (pathInfo.equals("/sendLetter")) { messageService.saveOrSendLetter(dto, request); }
        else if (pathInfo.equals("/saveMessage")) { messageService.saveMessage(dto); }
        else if (pathInfo.equals("/saveMessageAsDraft")) { messageService.sendOrSaveMessageFromProvider(dto, request); }
        else if (pathInfo.equals("/sendMessageFromProvider")) { messageService.sendOrSaveMessageFromProvider(dto, request); }
        else if (pathInfo.equals("/updateLetterStatus")) { messageService.updateLetterStatus(dto); }
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