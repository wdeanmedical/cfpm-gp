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
import com.wdeanmedical.dto.ReportDTO;
import com.wdeanmedical.service.ReportService;
import com.wdeanmedical.util.JSONUtils;
import com.wdeanmedical.util.Util;

public class ReportController extends AppController {
  private static final long serialVersionUID = 32856105721934537L;

  private static final Logger log = Logger.getLogger(ReportController.class);

  private ReportService reportService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    try {
      reportService = new ReportService();
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
    ReportDTO dto = gson.fromJson(data, ReportDTO.class);
    if (dto == null) { dto = new ReportDTO(); }
    Util.logServletPath(log, dto.module, servletPath, pathInfo); 

    try {
      if (isValidSession(request, response) == false) {
        appService.logout(dto);
      } 
      else {
        if (pathInfo.equals("/exportCsv")) { reportService.exportCsv(dto, response); } 
        else if (pathInfo.equals("/filterSalesLeads")) { reportService.filterSalesLeads(dto);  }
        else if (pathInfo.equals("/getActivityLogs")) { reportService.getActivityLogs(dto);  } 
        else if (pathInfo.equals("/getPatientActivity")) { reportService.getPatientActivity(dto);  }
        else if (pathInfo.equals("/getClinicianActivity")) { reportService.getClinicianActivity(dto);  }
        else if (pathInfo.equals("/getReportList")) { reportService.getReportList(dto); } 
        else if (pathInfo.equals("/getReportSearchTypeAheads")) { reportService.getReportSearchTypeAheads(dto);  }
        else if (pathInfo.equals("/getSalesLeads")) { reportService.getSalesLeads(dto);  } 
        else if (pathInfo.equals("/getSalesLeadSearchTypeAheads")) { reportService.getSalesLeadSearchTypeAheads(dto);  }
        else if (pathInfo.equals("/getWaitListPatients")) { reportService.getWaitListPatients(dto);  }
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
