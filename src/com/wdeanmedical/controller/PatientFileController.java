package com.wdeanmedical.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.wdeanmedical.dto.PatientFileDTO;
import com.wdeanmedical.service.PatientFileService;
import com.wdeanmedical.util.JSONUtils;
import com.wdeanmedical.util.Util;

public class PatientFileController extends AppController {

  private static final long serialVersionUID = 1913450042684975977L;

  private static final Logger log = Logger.getLogger(PatientFileController.class);

  private PatientFileService patientFileService;
  
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    try {
      patientFileService = new PatientFileService();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    String returnString = "";
    String pathInfo = request.getPathInfo();
    String servletPath = request.getServletPath();
    String data = request.getParameter("data");
    Gson gson = JSONUtils.getGson();
    PatientFileDTO dto = gson.fromJson(data, PatientFileDTO.class);
    if (dto == null) { dto = new PatientFileDTO(); }
    Util.logServletPath(log, dto.module, servletPath, pathInfo); 
    try {
    
      if (pathInfo.equals("/uploadBodyDiagram")) { patientFileService.uploadBodyDiagram(dto, request, response); }
      
      else if (isValidSession(request, response) == false) {
        appService.logout(dto);
      } 
      else {
        if (pathInfo.equals("/addNewFile")) { patientFileService.addNewFile(dto, request); } 
        else if (pathInfo.equals("/deleteFile")) { patientFileService.deleteFile(dto); } 
        else if (pathInfo.equals("/getFiles")) { patientFileService.getFiles(dto); } 
        else if (pathInfo.equals("/getFile")) { patientFileService.getFile(dto, response); } 
        else if (pathInfo.equals("/uploadFile")) { patientFileService.uploadFile(dto, request, response); } 
        else if (pathInfo.equals("/uploadProfileImage")) { patientFileService.uploadProfileImage(dto, request, response); }
        else if (pathInfo.equals("/uploadTxTongueImage")) { patientFileService.uploadTxTongueImage(dto, request, response); }
      }
   
      returnString = gson.toJson(dto);
      if (!response.isCommitted()) {
        response.setContentType("text/plain");
        PrintWriter ajaxOut = response.getWriter();
        ajaxOut.write(returnString);
        ajaxOut.close();
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);
  } 
  

}
