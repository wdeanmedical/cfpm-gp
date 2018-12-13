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
import com.wdeanmedical.dto.PatientDTO;
import com.wdeanmedical.service.PatientService;
import com.wdeanmedical.util.JSONUtils;
import com.wdeanmedical.util.Util;

public class PatientController extends AppController  {
  private static final long serialVersionUID = -6261155778552623025L;

  private static final Logger log = Logger.getLogger(PatientController.class);
  
  private PatientService patientService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    try{
      patientService = new PatientService();
    }
    catch(MalformedURLException e){
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
    PatientDTO dto = gson.fromJson(data, PatientDTO.class); 
    if (dto == null) { dto = new PatientDTO(); }
    Util.logServletPath(log, dto.module, servletPath, pathInfo); 
     
    try { 
      if (pathInfo.equals("/validateFromOffice")) { 
        patientService.validateFromOffice(dto, request); 
      }
      else if (pathInfo.equals("/validateViaRecovery")) { 
        patientService.validateViaRecovery(dto, request); 
      }
      else if (pathInfo.equals("/validateViaActivation")) { 
        patientService.validateViaActivation(dto, request); 
      }
      else {
        if (isValidSession(request, response) == false) {
          appService.logout(dto);  
        }
        else { 
          if (pathInfo.equals("/addHealthIssue")) { patientService.addHealthIssue(dto); }
          else if (pathInfo.equals("/addMedicalCondition")) { patientService.addMedicalCondition(dto); }
          else if (pathInfo.equals("/addPresentingProblem")) { patientService.addPresentingProblem(dto); }
          else if (pathInfo.equals("/closePatientIntake")) { patientService.closePatientIntake(dto, request); }
          else if (pathInfo.equals("/createPassword")) { patientService.createPassword(dto, request); }
          else if (pathInfo.equals("/createNewPatient")) { patientService.createNewPatient(dto, request); }
          else if (pathInfo.equals("/createPatientForm")) { patientService.createPatientForm(dto, request); }
          else if (pathInfo.equals("/createPatientInvoice")) { patientService.createPatientInvoice(dto); }
          else if (pathInfo.equals("/deleteHealthIssue")) { patientService.deleteHealthIssue(dto); }
          else if (pathInfo.equals("/deleteMedicalCondition")) { patientService.deleteMedicalCondition(dto); }
          else if (pathInfo.equals("/deletePatient")) { patientService.deletePatient(dto); }
          else if (pathInfo.equals("/deletePatientMedication")) { patientService.deletePatientMedication(dto); }
          else if (pathInfo.equals("/deletePresentingProblem")) { patientService.deletePresentingProblem(dto); }
          else if (pathInfo.equals("/getClientInfo")) { patientService.getClientInfo(dto); }
          else if (pathInfo.equals("/getFilteredPatients")) { patientService.getFilteredPatients(dto); }
          else if (pathInfo.equals("/getForms")) { patientService.getForms(dto); }
          else if (pathInfo.equals("/getGuardians")) { patientService.getGuardians(dto); }
          else if (pathInfo.equals("/getGuardianLastNameTypeAheads")) { patientService.getGuardianLastNameTypeAheads(dto); }
          else if (pathInfo.equals("/getInvoices")) { patientService.getInvoices(dto); }
          else if (pathInfo.equals("/getNextPatientId")) { patientService.getNextPatientId(dto); }
          else if (pathInfo.equals("/getPatient")) { patientService.getPatient(dto); }
          else if (pathInfo.equals("/getPatientClinicians")) { patientService.getPatientClinicians(dto); }
          else if (pathInfo.equals("/getPatientInfo")) { patientService.getPatientInfo(dto); }
          else if (pathInfo.equals("/getPatientIntake")) { patientService.getPatientIntake(dto); }
          else if (pathInfo.equals("/getPatientInvoice")) { patientService.getPatientInvoice(dto); }
          else if (pathInfo.equals("/getPatientInvoices")) { patientService.getPatientInvoices(dto); }
          else if (pathInfo.equals("/getPatientProfileImage")) { isBinaryResponse = true; patientService.getPatientProfileImage(dto, request, response); }
          else if (pathInfo.equals("/getPracticeFormInstance")) { patientService.getPracticeFormInstance(dto); }
          else if (pathInfo.equals("/getPracticeFormInstances")) { patientService.getPracticeFormInstances(dto); }
          else if (pathInfo.equals("/getPracticeForms")) { patientService.getPracticeForms(dto); }
          else if (pathInfo.equals("/getRecentPatients")) { patientService.getRecentPatients(dto); }
          else if (pathInfo.equals("/getTempPatientProfileImage")) {isBinaryResponse = true; patientService.getTempPatientProfileImage(dto, request, response); }
          else if (pathInfo.equals("/saveNewGuardian")) { patientService.saveNewGuardian(dto, request); }
          else if (pathInfo.equals("/saveNewPatient")) { patientService.saveNewPatient(dto, request); }
          else if (pathInfo.equals("/searchGuardian")) { patientService.searchGuardian(dto); }
          else if (pathInfo.equals("/sendPortalInvitation")) { patientService.sendPortalInvitation(dto, request); }
          else if (pathInfo.equals("/updatePatient")) { appService.updateField(dto); }
          else if (pathInfo.equals("/updatePassword")) { patientService.updatePassword(dto); }
          else if (pathInfo.equals("/updatePatientGuardian")) { patientService.updatePatientGuardian(dto); }
          else if (pathInfo.equals("/updatePatientInfoForms")) { patientService.updatePatientInfoForms(dto); }
          else if (pathInfo.equals("/updateDxCode")) { patientService.updateDxCode(dto); }
          else if (pathInfo.equals("/updateTxCode")) { patientService.updateTxCode(dto); }

        }
      }
      
      returnString = gson.toJson(dto);
      ServletOutputStream  out = null;
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

}