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
import com.wdeanmedical.dto.BillingDTO;
import com.wdeanmedical.service.BillingService;
import com.wdeanmedical.util.JSONUtils;
import com.wdeanmedical.util.Util;

public class BillingController extends AppController {
  private static final long serialVersionUID = -1190620491365236175L;

  private static final Logger log = Logger.getLogger(BillingController.class);

  private BillingService billingService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    try {
      billingService = new BillingService();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    String returnString = "";
    String pathInfo = request.getPathInfo();
    String servletPath = request.getServletPath();
    boolean isBinaryResponse = false;
    
    String data = request.getParameter("data");
    Gson gson = JSONUtils.getGson();
    BillingDTO dto = gson.fromJson(data, BillingDTO.class);
    if (dto == null) { dto = new BillingDTO(); }
    Util.logServletPath(log, dto.module, servletPath, pathInfo); 

    try {
      if (isValidSession(request, response) == false) {
        appService.logout(dto);
      } 
      else {
        if (pathInfo.equals("/chargeCustomer")) { billingService.chargeCustomer(dto, request); } 
        else if (pathInfo.equals("/createOrUpdateCustomer")) { billingService.createOrUpdateCustomer(dto); } 
        else if (pathInfo.equals("/chargeCustomerStatus")) { billingService.chargeCustomerStatus(dto); } 
        else if (pathInfo.equals("/deleteCustomer")) { billingService.deleteCustomer(dto); } 
        else if (pathInfo.equals("/getCustomer")) { billingService.getCustomer(dto); } 
      }
      
      returnString = gson.toJson(dto);
      ServletOutputStream out = null;
      response.setContentType("text/plain");

      if (isBinaryResponse == true) {
        out = response.getOutputStream();
        out.println(returnString);
        out.close();
      } else {
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
