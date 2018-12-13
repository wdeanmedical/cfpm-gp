package com.wdeanmedical.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wdeanmedical.core.Core;

public class ModuleController extends AppController  {
  
  private static final long serialVersionUID = -4753005449212833123L;
  private static final Logger log = Logger.getLogger(AdminController.class);
  
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }
    
  @Override
  public void doPost( HttpServletRequest request, HttpServletResponse response) {
    
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Core.appHtml);
    
    try {
      dispatcher.forward(request,response);
    } 
    catch (ServletException e) {
      e.printStackTrace();
    } 
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);  
  }

}