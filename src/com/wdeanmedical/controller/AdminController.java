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
import com.wdeanmedical.dto.AdminDTO;
import com.wdeanmedical.service.AdminService;
import com.wdeanmedical.util.JSONUtils;
import com.wdeanmedical.util.Util;

public class AdminController extends AppController  {
  
  private static final long serialVersionUID = 8930535029294936488L;

  private static final Logger log = Logger.getLogger(AdminController.class);
  
  private AdminService adminService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    try{
      adminService = new AdminService();
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
    AdminDTO dto = gson.fromJson(data, AdminDTO.class); 
    if (dto == null) { dto = new AdminDTO(); }
    Util.logServletPath(log, dto.module, servletPath, pathInfo); 
     
    try { 
      if (isValidSession(request, response) == false) {
        appService.logout(dto);  
      }
      else { 
        if (pathInfo.equals("/activateUser")) { adminService.activateUser(dto); }
        else if (pathInfo.equals("/deactivateUser")) { adminService.deactivateUser(dto); }
        else if (pathInfo.equals("/getUsers")) { adminService.getUsers(dto); }
        else if (pathInfo.equals("/deleteUser")) { adminService.deleteUser(dto); }
        else if (pathInfo.equals("/saveNewUser")) { adminService.saveNewUser(dto); }
        else if (pathInfo.equals("/updateUser")) { adminService.updateUser(dto); }
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