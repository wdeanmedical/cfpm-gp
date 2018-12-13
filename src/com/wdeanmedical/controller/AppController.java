package com.wdeanmedical.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.wdeanmedical.core.Core;
import com.wdeanmedical.core.Permissions;
import com.wdeanmedical.dto.AppDTO;
import com.wdeanmedical.entity.RecoveryCode;
import com.wdeanmedical.model.ValidationResult;
import com.wdeanmedical.service.AppService;
import com.wdeanmedical.service.BackgroundService;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.JSONUtils;
import com.wdeanmedical.util.Util;

public class AppController extends HttpServlet  {

  private static final long serialVersionUID = 5141268230082988870L;
  private static final Logger log = Logger.getLogger(AppController.class);

  protected AppService appService;

  public void shutDown() {
    BackgroundService.shutDown();
  }

  @Override
  public void init(ServletConfig config) throws ServletException {
  
    super.init(config);
    ServletContext context = getServletContext();
    Core.servletContext = context;
    try{ 
      appService = new AppService(); 
    } catch(MalformedURLException e){ e.printStackTrace(); }
    if (Core.initComplete == true) {
      return;
    }
    
    RecoveryCode.RECOVERY_EXPIRATION_WINDOW = Duration.ofSeconds(new Integer(context.getInitParameter("recoveryCodeExpiration")));
    RecoveryCode.ACTIVATION_EXPIRATION_WINDOW = Duration.ofSeconds(new Integer(context.getInitParameter("activationCodeExpiration")));
    Core.appHtml = context.getInitParameter("appHtml");
    Core.specialty = context.getInitParameter("specialty");
    Core.practice = context.getInitParameter("practice");
    Core.sessionTimeout = Integer.parseInt(context.getInitParameter("appSessionTimeout"));
    Core.appBaseDir = context.getRealPath("/") +"/";
    setCoreProperties();
    
    Core.encryptionKey = Core.appProperties.getProperty("app.encryption_key");
    try { DataEncryptor.setEncryptionKey(Core.encryptionKey); } catch (Exception e) { e.printStackTrace();}
    Core.filesHome = Core.appProperties.getProperty("app.files_home");
    Core.headshotPlaceholder = Core.appProperties.getProperty("app.headshot_placeholder");
    Core.imageMagickHome = Core.appProperties.getProperty("app.image_magick_home");
    Core.mailAuthenticationPassword = Core.appProperties.getProperty("mail.auth_password");
    Core.mailAuthenticationUser = Core.appProperties.getProperty("mail.auth_user");
    Core.mailFrom = Core.appProperties.getProperty("mail.from");
    Core.patientDirPath = Core.practiceAppProperties.getProperty("app.patient_dir_path");
    Core.sendMail = new Boolean(Core.appProperties.getProperty("mail.send"));
    Core.smtphost = Core.appProperties.getProperty("mail.smtp_host");
    Core.smtpport = Core.appProperties.getProperty("mail.smtp_port");
    Core.stripeApiVersion = Core.appProperties.getProperty("app.stripe_api_version");
    Core.stripeTestKey = Core.appProperties.getProperty("app.stripe_test_key");
    Core.smtpport = Core.appProperties.getProperty("mail.smtp_port");
    Core.timeZone = Core.appProperties.getProperty("app.time_zone");
    Core.tmpDir = Core.appProperties.getProperty("app.tmp_dir");
    Core.useImageMagick = new Boolean(Core.appProperties.getProperty("app.use_image_magick"));
    
    Permissions.buildClientPermissionsMap();
    Core.initComplete = true;
  }



  private void setCoreProperties() {
	  
    Core.appProperties = new Properties();
    try { 
      InputStream inputStream = new FileInputStream(Core.appBaseDir + "/WEB-INF/properties/app.properties");
      Core.appProperties.load(inputStream);
    } 
    catch (IOException e) { log.info("Failed to load app.properties " + e.getMessage()); }
    
    Core.clientProperties = new Properties();
    try { 
      InputStream inputStream = new FileInputStream(Core.appBaseDir + "/WEB-INF/properties/client.properties");
      Core.clientProperties.load(inputStream);
    } 
    catch (IOException e) { log.info("Failed to load client.properties " + e.getMessage()); }
    
    Core.specialtyClientProperties = new Properties();
    try { 
      InputStream inputStream = new FileInputStream(Core.appBaseDir + "/WEB-INF/properties/specialty/"+Core.specialty+".properties");
      Core.specialtyClientProperties.load(inputStream);
    } 
    catch (IOException e) { log.info("Failed to load "+Core.specialty+".properties " + e.getMessage()); }
    
    Core.practiceAppProperties = new Properties();
    try { 
      InputStream inputStream = new FileInputStream(Core.appBaseDir + "/WEB-INF/properties/practice/"+Core.practice+"/"+Core.practice+"_app.properties");
      Core.practiceAppProperties.load(inputStream);
    } 
    catch (IOException e) { log.info("Failed to load "+Core.practice+"_app.properties " + e.getMessage()); }
    
    Core.practiceClientProperties = new Properties();
    try { 
      InputStream inputStream = new FileInputStream(Core.appBaseDir + "/WEB-INF/properties/practice/"+Core.practice+"/"+Core.practice+"_client.properties");
      Core.practiceClientProperties.load(inputStream);
    } 
    catch (IOException e) { log.info("Failed to load "+Core.practice+"_client.properties " + e.getMessage()); }
  }
  
  

  @Override
  public void doPost( HttpServletRequest request, HttpServletResponse response) {
    String returnString = "";
    String pathInfo = request.getPathInfo();
    String servletPath = request.getServletPath();
    boolean isBinaryResponse = false;
    
    String data = request.getParameter("data");
    Gson gson = JSONUtils.getGson();
    AppDTO dto = new AppDTO();
    try {
      dto = gson.fromJson(data, AppDTO.class); 
    } catch(Exception e) {
      ValidationResult result = new ValidationResult();
      dto.setError(result.invalid("Invalid Input", null));
    }
    if (dto == null) { dto = new AppDTO(); }
    Util.logServletPath(log, dto.module, servletPath, pathInfo); 

    try { 
      if (dto.result != null && dto.result == false) {}
      else if (pathInfo.equals("/getClientProperties")) {
        appService.getClientProperties(dto);  
      }
      else if (pathInfo.equals("/getStaticLists")) {
        appService.getStaticLists(dto);  
      }
      else if (pathInfo.equals("/login")) {
        appService.login(dto, request);  
      }
      else if (pathInfo.equals("/sendCredentialsRecovery")) { 
        appService.sendCredentialsRecovery(dto, request); 
      }
      else if (pathInfo.equals("/validateViaRecovery")) { 
        appService.validateViaRecovery(dto, request); 
      }
      else { 
        if (isValidSession(request, response) == false) {
          appService.logout(dto);
        }
        else {
          if (pathInfo.equals("/addBillingTicketEntry")) { appService.addBillingTicketEntry(dto); }
          else if (pathInfo.equals("/addForm")) { appService.addForm(dto); }
          else if (pathInfo.equals("/addItem")) { appService.addItem(dto); }
          else if (pathInfo.equals("/addInvoiceLineItem")) { appService.addInvoiceLineItem(dto); }
          else if (pathInfo.equals("/checkForOpenForm")) { appService.checkForOpenForm(dto); }
          else if (pathInfo.equals("/closeAndCreateForm")) { appService.closeAndCreateForm(dto); }
          else if (pathInfo.equals("/closeForm")) { appService.closeForm(dto); }
          else if (pathInfo.equals("/createPassword")) { appService.createPassword(dto, request); }
          else if (pathInfo.equals("/deleteBillingTicketEntry")) { appService.deleteBillingTicketEntry(dto); }
          else if (pathInfo.equals("/deleteInvoice")) { appService.deleteInvoice(dto); }
          else if (pathInfo.equals("/deleteItem")) { appService.deleteItem(dto); }
          else if (pathInfo.equals("/getAppLists")) { appService.getAppLists(dto); }
          else if (pathInfo.equals("/getOrCreateBillingTicket")) { appService.getOrCreateBillingTicket(dto); }
          else if (pathInfo.equals("/getClinicians")) { appService.getClinicians(dto); }
          else if (pathInfo.equals("/getConsultantInfo")) { appService.getConsultantInfo(dto); }
          else if (pathInfo.equals("/getFile")) { isBinaryResponse = true; appService.getFile(dto, request, response); }
          else if (pathInfo.equals("/getForm")) { appService.getForm(dto); }
          else if (pathInfo.equals("/getForms")) { appService.getForms(dto); }
          else if (pathInfo.equals("/getLocations")) { appService.getLocations(dto); }
          else if (pathInfo.equals("/getPatients")) { appService.getPatients(dto); }
          else if (pathInfo.equals("/getPatientSearchTypeAheads")) { appService.getPatientSearchTypeAheads(dto); }
          else if (pathInfo.equals("/getUserDashboard")) { appService.getUserDashboard(dto); }
          else if (pathInfo.equals("/logout")) { appService.logout(dto); }
          else if (pathInfo.equals("/park")) { appService.park(dto); }
          else if (pathInfo.equals("/saveBillingTicketEntry")) { appService.saveBillingTicketEntry(dto); }
          else if (pathInfo.equals("/sendInvoice")) { appService.sendInvoice(dto, request); }
          else if (pathInfo.equals("/updateField")) { appService.updateField(dto); }
          else if (pathInfo.equals("/updateFields")) { appService.updateFields(dto); }
          else if (pathInfo.equals("/updateForm")) { appService.updateForm(dto); }
          else if (pathInfo.equals("/updateItem")) { appService.updateItem(dto); }
          else if (pathInfo.equals("/addSubItem")) { appService.addSubItem(dto); }
          else if (pathInfo.equals("/getSubItems")) { appService.getSubItems(dto); }
          else if (pathInfo.equals("/deleteItem")) { appService.deleteItem(dto); }
          else if (pathInfo.equals("/updateItemPriority")) { appService.updateItemPriority(dto); }
          else if (pathInfo.equals("/unpark")) { appService.unpark(dto, request); }
          else if (pathInfo.equals("/searchCPT")) { appService.searchCPT(dto, request); }
          else if (pathInfo.equals("/searchICD9")) { appService.searchICD9(dto, request); }
        }
      }
      
      returnString = gson.toJson(dto);
      ServletOutputStream out = null;
      response.setContentType("text/plain");
      
      if (isBinaryResponse == true) { 
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
  
  

  protected  boolean isValidSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String ipAddress = request.getRemoteHost();
    String data = request.getParameter("data");
    Gson gson = JSONUtils.getGson();
    AppDTO dto = gson.fromJson(data, AppDTO.class);  
    if (dto == null){
      dto = new AppDTO();
      dto.sessionId = request.getParameter("sessionId");
    }
    String path = request.getPathInfo();
    if(path.substring(1).split("/").length > 1) {
      path = path.substring(1).split("/")[1];
    } 
    path = request.getServletPath() + path;
    return appService.isValidSession(dto, ipAddress, path);
  }
  


  public String checkSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String data = request.getParameter("data");
    Gson gson = JSONUtils.getGson();
    AppDTO dto = gson.fromJson(data, AppDTO.class);  
    if (dto == null) {
      dto = new AppDTO();
    }
    dto.authenticated = isValidSession(request, response);
    String json = gson.toJson(dto);
    return json;
  }

}
