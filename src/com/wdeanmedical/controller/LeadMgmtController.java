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
import com.wdeanmedical.dto.LeadMgmtDTO;
import com.wdeanmedical.service.LeadMgmtService;
import com.wdeanmedical.util.JSONUtils;
import com.wdeanmedical.util.Util;

public class LeadMgmtController extends AppController  {
  private static final long serialVersionUID = 1067483907544747578L;

  private static final Logger log = Logger.getLogger(LeadMgmtController.class);
  
  private LeadMgmtService leadMgmtService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    try{
      leadMgmtService = new LeadMgmtService();
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
    LeadMgmtDTO dto = gson.fromJson(data, LeadMgmtDTO.class); 
    if (dto == null) { dto = new LeadMgmtDTO(); }
    Util.logServletPath(log, dto.module, servletPath, pathInfo); 
     
    try { 
      if (isValidSession(request, response) == false) {
        appService.logout(dto);  
      }
      else { 
        if (pathInfo.equals("/completeSalesLeadTask")) { leadMgmtService.completeSalesLeadTask(dto); }
        else if (pathInfo.equals("/convertSalesLeadToPatient")) { leadMgmtService.convertSalesLeadToPatient(dto, request); }    
        else if (pathInfo.equals("/deleteSalesLead")) { leadMgmtService.deleteSalesLead(dto); }
        else if (pathInfo.equals("/deleteSalesLeadAction")) { leadMgmtService.deleteSalesLeadAction(dto); }
        else if (pathInfo.equals("/deleteSalesLeadTask")) { leadMgmtService.deleteSalesLeadTask(dto); }
        else if (pathInfo.equals("/getFilteredSalesLeads")) { leadMgmtService.deleteSalesLeadTask(dto); }
        else if (pathInfo.equals("/getRecentSalesLeads")) { leadMgmtService.getRecentSalesLeads(dto); }
        else if (pathInfo.equals("/getSalesLead")) { leadMgmtService.getSalesLead(dto); }
        else if (pathInfo.equals("/getSalesLeadActions")) { leadMgmtService.getSalesLeadActions(dto); }
        else if (pathInfo.equals("/getSalesLeadTask")) { leadMgmtService.getSalesLeadTask(dto); }
        else if (pathInfo.equals("/getSalesLeadTasks")) { leadMgmtService.getSalesLeadTasks(dto); }
        else if (pathInfo.equals("/getSalesLeadSearchTypeAheads")) { leadMgmtService.getSalesLeadSearchTypeAheads(dto); }
        else if (pathInfo.equals("/saveNewSalesLead")) { leadMgmtService.saveNewSalesLead(dto, request); }
        else if (pathInfo.equals("/saveNewSalesLeadAction")) { leadMgmtService.saveNewSalesLeadAction(dto); }
        else if (pathInfo.equals("/saveNewSalesLeadTask")) { leadMgmtService.saveNewSalesLeadTask(dto, request); }
        else if (pathInfo.equals("/updateSalesAgentIds")) { leadMgmtService.updateSalesAgentIds(dto); }
        else if (pathInfo.equals("/updateSalesLead")) { leadMgmtService.updateField(dto); }
        else if (pathInfo.equals("/updateSalesLeadAction")) { leadMgmtService.updateSalesLeadAction(dto); }
        else if (pathInfo.equals("/updateSalesLeadTask")) { leadMgmtService.updateSalesLeadTask(dto); }
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