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
import com.wdeanmedical.dto.SiteDTO;
import com.wdeanmedical.service.AppService;
import com.wdeanmedical.service.SiteService;
import com.wdeanmedical.util.JSONUtils;
import com.wdeanmedical.util.Util;

public class SiteController extends AppController  {
  
  private static final long serialVersionUID = -9056758215062714739L;

  private static final Logger log = Logger.getLogger(SiteController.class);
  
  private AppService appService;
  private SiteService siteService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    try{
      appService = new AppService();
      siteService = new SiteService();
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
    SiteDTO dto = gson.fromJson(data, SiteDTO.class); 
    if (dto == null) { dto = new SiteDTO(); }
    Util.logServletPath(log, dto.module, servletPath, pathInfo); 
     
    try { 
  
     if (pathInfo.equals("/getClientProperties")) {
        siteService.getClientProperties(dto);  
      }
     else if (pathInfo.equals("/sendContactMessage")) {
        siteService.sendContactMessage(request, dto);  
      }
     else {
      
      if (isValidSession(request, response) == false) {
        appService.logout(dto);  
      }
      else { 
      }
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