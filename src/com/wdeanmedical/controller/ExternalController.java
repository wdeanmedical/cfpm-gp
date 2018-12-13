package com.wdeanmedical.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.wdeanmedical.dto.AppDTO;
import com.wdeanmedical.dto.PatientDTO;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.external.fhir.PatientFullRecordFHIR;
import com.wdeanmedical.external.fhir.PatientsFHIR;
import com.wdeanmedical.service.AppService;
import com.wdeanmedical.service.ExternalService;
import com.wdeanmedical.util.JSONUtils;


public class ExternalController extends AppController  {
  
  private static final long serialVersionUID = 9191453435522888626L;

  private static final Logger log = Logger.getLogger(ExternalController.class);
  
  private static final int FORMAT = 0; 
  private static final int METHOD = 1; 
  private static final int ARG1 = 2; 
  private static final String JSON = "json"; 
  private static final String XML = "xml"; 
  
  private AppService appService;
  private ExternalService externalService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    try{
      appService = new AppService();
      externalService = new ExternalService();
    }
    catch(MalformedURLException e){
      e.printStackTrace();
    }
  }
    
  @Override
  public void doPost( HttpServletRequest request, HttpServletResponse response) {
    String returnString = "";
    String format = ""; 
    String arg1 = "";
    String pathInfo = request.getPathInfo();
    boolean isBinaryResponse = false;
    
    String[] paths = pathInfo.substring(1).split("/"); 
    if(paths.length < 2 || !(paths[0].equals("json") || paths[0].equals("xml"))) {
      returnString = "USAGE:\n\n"
          + "{GET/POST} /ext/{json/xml}/{method}/{arg1}/{arg2}/{arg3}\n\n"
          + "Method List:\n\n"
          + "POST /ext/{json/xml}/auth\n"
          + "Authorize a service client by credentials submitted via POST.\n\n"
          + "GET /ext/{json/xml}/getPatient/{patientMRN}\n"
          + "Get a patient by ID\n\n"
          + "GET /ext/{json/xml}/getPatients\n"
          + "Get all patients\n\n"
          + "GET /ext/{json/xml}/getPatientFullRecord/{patientMRN}\n"
          + "Get the patient's full record, including all encounters\n\n"
          + "POST /ext/{json/xml}/updatePatient/{patientMRN}\n"
          + "Update a patient by ID with FHIR formatted patient data in POST header.\n\n"
          + "POST /ext/{json/xml}/importPatients\n"
          + "Import patients from FHIR formatted patient data in POST header.\n\n";
      ServletOutputStream ouut = null;
      response.setContentType("text/plain");
      try {
        ouut = response.getOutputStream();
        ouut.println(returnString);
        ouut.close();
        return;
      } 
      catch( Exception e ) {
        e.printStackTrace();
      }
    }
    
    if (XML.equals(paths[FORMAT])) {
      format = XML;
    }
    else if (JSON.equals(paths[FORMAT])) {
      format = JSON;
    }
    
    String method = paths[METHOD];
    if (paths.length > 2) {arg1 = paths[ARG1];}
    if (paths.length > 3) {}
    if (paths.length > 4) {}
    
     
    try { 
      if (method.equals("auth")) {
        returnString = auth(request, response, format);  
      }
      else { 
        if (isValidSession(request, response) == false) {
          returnString = logout(request, response);  
        }
        else {
          if (method.equals("getPatient")) {
            returnString = getPatient(arg1, format);  
          }
          else if (method.equals("getPatientFullRecord")) {
            returnString = getPatientFullRecord(arg1, format);  
          }
          else if (method.equals("getPatients")) {
            returnString = getPatients(request, response, format);  
          }
          else if (method.equals("importPatients")) {
            returnString = importPatients(request, response, format);  
          }
          else if (method.equals("updatePatient")) {
            returnString = updatePatient(request, response, format);  
          }
        }
      }
     
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
  
  
  
  public String auth(HttpServletRequest request, HttpServletResponse response, String format) throws Exception {
    String data = request.getParameter("data");
    String ipAddress = request.getRemoteHost();
    AppDTO dto = null;
    String returnString = null;
    if(JSON.equals(format)){
      Gson gson = JSONUtils.getGson();
      if(!(JSONUtils.isJSONValid(data, AppDTO.class))) {
        return "Invalid JSON.";
      }
      dto = gson.fromJson(data, AppDTO.class);        
      dto = externalService.auth(dto, ipAddress); 
      returnString = gson.toJson(dto);
    }
    else if (XML.equals(format)) {
      JAXBContext jaxbRequestContext = JAXBContext.newInstance(AppDTO.class);
      Unmarshaller jaxbUnmarshaller = jaxbRequestContext.createUnmarshaller();
      StringReader stringReader = new StringReader(data);
      dto = (AppDTO)jaxbUnmarshaller.unmarshal(stringReader);
      dto = externalService.auth(dto, ipAddress); 
      StringWriter out = new StringWriter();
      JAXBContext jaxbResponseContext = JAXBContext.newInstance(AppDTO.class);
      Marshaller jaxbMarshaller = jaxbResponseContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      jaxbMarshaller.marshal(dto, out);
      returnString = out.toString();
    }
    return returnString;
  }
  
  
  
  public String getPatient(String mrn, String format) throws Exception {
    org.hl7.fhir.Patient patientFHIR = externalService.getPatient(mrn);
    String returnString = null;
    if (JSON.equals(format)) {
      Gson gson = JSONUtils.getGson();
      returnString = gson.toJson(patientFHIR);
    }else  if (XML.equals(format)) {
      StringWriter out = new StringWriter();
      JAXBContext jaxbContext = JAXBContext.newInstance(org.hl7.fhir.Patient.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      jaxbMarshaller.marshal(patientFHIR, out);
      returnString = out.toString();
   }
    return returnString;
  }
  
  
    
  public String getPatients(HttpServletRequest request, HttpServletResponse response, String format) throws Exception {
    String data = request.getParameter("data");
    Gson gson = JSONUtils.getGson();
    PatientDTO dto = gson.fromJson(data, PatientDTO.class); 
    List<Patient> patients = appService.getPatients(dto); 

    PatientsFHIR patientsFHIR = externalService.buildPatientResource(patients);    
    if (format.equals(XML)) {
      StringWriter out = new StringWriter();
      JAXBContext jaxbContext = JAXBContext.newInstance(PatientsFHIR.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      jaxbMarshaller.marshal(patientsFHIR, out);
      return out.toString();
    } 
    String json = gson.toJson(patientsFHIR);
    return json;
  }

  
  
  public String importPatients(HttpServletRequest request, HttpServletResponse response, String format) throws Exception {
    String data = request.getParameter("data");
    PatientsFHIR patientsFHIR = null;    
    if(format.equals(JSON)){
      Gson gson = JSONUtils.getGson();
      patientsFHIR = gson.fromJson(data, PatientsFHIR.class); 
    }else{
      JAXBContext jaxbContext = JAXBContext.newInstance(PatientsFHIR.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      StringReader stringReader = new StringReader(data);
      patientsFHIR = (PatientsFHIR)jaxbUnmarshaller.unmarshal(stringReader);
    }    
    if(patientsFHIR != null){
      externalService.importPatients(patientsFHIR);
    }    
    return null;
  }
  
  
  
  public String updatePatient(HttpServletRequest request, HttpServletResponse response, String format) throws Exception {
    String data = request.getParameter("data");
    org.hl7.fhir.Patient patientFHIR = null;
    if (format.equals(XML)) {
        JAXBContext jaxbContext = JAXBContext.newInstance(org.hl7.fhir.Patient.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader stringReader = new StringReader(data);
        patientFHIR = (org.hl7.fhir.Patient)jaxbUnmarshaller.unmarshal(stringReader);
    }
    else{
      Gson gson = JSONUtils.getGson();
      patientFHIR = gson.fromJson(data, org.hl7.fhir.Patient.class);   
    }
    if(patientFHIR != null) {
      externalService.updatePatient(patientFHIR);
    }
    return null;
  }
  
  
  
  public String getPatientFullRecord(String mrn, String format) throws Exception {
    PatientFullRecordFHIR patientFullRecordFHIR = externalService.getPatientFullRecord(mrn);
    if(format.equals(JSON)){
      Gson gson = JSONUtils.getGson();
      String json = gson.toJson(patientFullRecordFHIR);
        return json;
      }else{
        StringWriter stringWriter = new StringWriter();   
      JAXBContext jaxbContext = JAXBContext.newInstance(PatientFullRecordFHIR.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      jaxbMarshaller.marshal(patientFullRecordFHIR, stringWriter);
      return stringWriter.toString();
   }
  }
  
  
  
  public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String data = request.getParameter("data");
    Gson gson = JSONUtils.getGson();
    AppDTO dto = gson.fromJson(data, AppDTO.class);  
    appService.logout(dto);
    dto.authenticated = false;
    String json = gson.toJson(dto);
    return json;
  }
 
}
 
 
