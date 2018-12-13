package com.wdeanmedical.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tika.Tika;

import com.google.gson.Gson;
import com.wdeanmedical.core.Core;
import com.wdeanmedical.dto.PatientFileDTO;
import com.wdeanmedical.entity.ActivityLog;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.ClientSession;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.PatientFile;
import com.wdeanmedical.entity.PatientImage;
import com.wdeanmedical.model.BodyAnnotation;
import com.wdeanmedical.model.ValidationResult;
import com.wdeanmedical.persistence.PatientFileDAO;
import com.wdeanmedical.util.Base64Encoder;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.JSONUtils;
import com.wdeanmedical.util.MailHandler;

public class PatientFileService extends AppService {
  private static Log log = LogFactory.getLog(PatientFileService.class);
  
  private PatientFileDAO patientFileDAO;
  File uploadDir;
  
  
  public PatientFileService() throws MalformedURLException {
    super();
    patientFileDAO = (PatientFileDAO) wac.getBean("patientFileDAO");
    uploadDir = Paths.get(Core.filesHome, Core.tmpDir).toFile();
  }



  public void addNewFile(PatientFileDTO dto, HttpServletRequest request) throws Exception {
    PatientFile patientFile = new PatientFile();
    patientFile.setDescription(dto.description);
    patientFile.setName(dto.name);
    String path = moveUploadedToPatientFolder(dto);
    patientFile.setPath(path);
    patientFile.setType(dto.type);
    patientFile.setPatientId(dto.patientId);
    ClientSession clientSession = getClientSession(dto.sessionId, dto.patientId);
    patientFile.setClientId(clientSession.getClientId());
    patientFile.setClientType(clientSession.getClientType());
    patientFileDAO.create(patientFile);
    dto.id = patientFile.getId();
    dto.clientType = patientFile.getClientType();
    
    if (Client.PATIENT.equals(patientFile.getClientType()) == false) {
      Patient patient = appDAO.findById(Patient.class, dto.patientId);
      DataEncryptor.decryptPatient(patient);
      String patientFullName = patient.getFirstName() + " " + patient.getLastName();
      String title = "New patient file for  " + patientFullName;
      Map<String,String> customAttributes = new HashMap<String,String>();
      customAttributes.put("title", title);
      patient.setGuardian(getPatientService().getPatientGuardian(patient.getId()));
      MailHandler.sendSystemEmail("portal_new_file", title, patient, null, request, "/portal", customAttributes, null);
    }  
    logEvent(dto, ActivityLog.ADD_FILE, "PatientFileService addNewFile()", dto.name, null);
  }


  
  public void deleteFile(PatientFileDTO dto) throws Exception {
    PatientFile file = patientFileDAO.findPatientFileById(dto.id);
    if (file.getDeleted()) {
      return;
    }
    moveFileToPatientTrashFolder(file);
    file.setDeleted(true);
    patientFileDAO.update(file);
    dto.deleted = true;
   logEvent(dto, ActivityLog.DELETE_FILE, "PatientFileService deleteFile()", file.getName(), null);
  }
  
  
  
  public static void getFile(String path, String name, String type, HttpServletResponse response) throws Exception {
    response.setContentType(type);  
    File file = new File(path);
    response.setHeader("Content-Disposition", "attachment;filename="+ name);
    response.setContentLength((int) file.length());
    FileInputStream in = new FileInputStream(file);
    OutputStream out = response.getOutputStream();
    byte[] buf = new byte[1024];
    int count = 0;
    while ((count = in.read(buf)) >= 0) {
      out.write(buf, 0, count);
    }
    out.close();
    in.close();
  }
  
  
  
  public void getFile(PatientFileDTO dto, HttpServletResponse response) throws Exception {
    PatientFile file = patientFileDAO.findPatientFileById(dto.id);
    PatientFileService.getFile(file.getPath(), file.getName(),  file.getType(), response);
  }
  
  
  
  public void getFiles(PatientFileDTO dto) {
    List<PatientFile> files = patientFileDAO.findPatientFiles(dto.patientId); 
    List<PatientFileDTO> result = new ArrayList<PatientFileDTO>();
    Gson gson = JSONUtils.getGson();
    String json;
    for(PatientFile file: files) {
      json = gson.toJson(file);
      result.add(gson.fromJson(json, PatientFileDTO.class));
    }
    dto.list = result;
  }
  
  private File getPatientFilesFolder(Integer patientId) throws IOException {
    Path path = Paths.get(Core.filesHome, Core.patientDirPath, patientId.toString(), "files" );
    File folder = path.toFile();
    if (!folder.exists()) {
      Files.createDirectories(folder.toPath());
    }
    return folder;
  }
  
  
  
  private File getPatientTrashFolder(Integer patientId) throws IOException {
    File files = getPatientFilesFolder(patientId);
    Path path = Paths.get(files.getPath(), "trash");
    File folder = path.toFile();
    if (!folder.exists()) {
      Files.createDirectories(folder.toPath());
    }
    return folder;
  }
 
  private void moveFileToPatientTrashFolder(PatientFile file) throws IOException {
    File folder = getPatientTrashFolder(file.getPatientId());
    Path source = Paths.get(file.getPath());
    String name = source.toFile().getName();
    String parentDir = folder.getPath();
    Path destination = Paths.get(parentDir, name);
    Files.move(source, destination);
  }
 
  private String moveUploadedToPatientFolder(PatientFileDTO dto) throws IOException {
    File folder = getPatientFilesFolder(dto.patientId);
    Path source = Paths.get(dto.path);
    String parentDir = folder.getPath();
    Path destination = Paths.get(parentDir, dto.name);
    if (Files.exists(destination)) {
      Long timeStamp = System.currentTimeMillis();
      destination = Paths.get(parentDir, timeStamp + "-" + dto.name );
    }
    Files.move(source, destination);
    return destination.toString();
  }
 
  public void uploadBodyDiagram(PatientFileDTO dto, HttpServletRequest request, HttpServletResponse response) throws Exception {
    InputStream is = null;
    FileOutputStream fos = null;
    is = request.getInputStream();
    File f = File.createTempFile("body_", ".png", new File(Core.filesHome + Core.tmpDir));
    String filename = f.getName();
    fos = new FileOutputStream(new File(Core.filesHome + Core.tmpDir + "/" + filename));

    //byte[] decodedBytes = new BASE64Decoder().decodeBuffer(is);
    //fos.write(decodedBytes);
    IOUtils.copy(is, fos);
    response.setStatus(HttpServletResponse.SC_OK);
    fos.close();
    is.close();

    log.info("\n" + filename + " uploaded");
    
    Integer patientId; try { patientId = new Integer(request.getParameter("patientId")); } catch (NumberFormatException nfe) {patientId = null;}
    String imageTempPath = Core.filesHome + Core.tmpDir + "/" + filename;

    String filesHomePatientDirPath =  Core.filesHome  + Core.patientDirPath + "/" + patientId + "/";
    new File(filesHomePatientDirPath).mkdirs(); 
    File imageTempPathFile = new File(imageTempPath);
    imageTempPathFile.renameTo(new File(filesHomePatientDirPath + "/" + filename)); 
    dto.filename = filename;
    logEvent(dto, ActivityLog.UPLOAD_IMAGE, "PatientFileService uploadBodyDiagram()", null, null);
  }
  
  
  
  public void uploadFile(PatientFileDTO dto, HttpServletRequest request,  HttpServletResponse response) throws Exception{
    InputStream is = null;
    FileOutputStream fos = null;
    is = request.getInputStream();
    String submittedFilename = request.getHeader("X-File-Name");
    String extension = submittedFilename.substring(submittedFilename.indexOf("."));
    File out = File.createTempFile("new-file", extension, uploadDir);
    fos = new FileOutputStream(out);
    IOUtils.copy(is, fos);
    response.setStatus(HttpServletResponse.SC_OK);
    fos.close();
    is.close(); 
    log.info("\n" + out.getName() + " uploaded");
    dto.name = submittedFilename;
    dto.path = out.getAbsolutePath();
    Tika tika = new Tika();
    dto.type =  tika.detect(out);
    log.info("File Type: " + dto.type);
    logEvent(dto, ActivityLog.UPLOAD_FILE, "PatientFileService uploadFile()", submittedFilename, null);
  }
  
  
  
  public void uploadProfileImage(PatientFileDTO dto, HttpServletRequest request, HttpServletResponse response) throws Exception {
    Patient patient = null;
    InputStream is = null;
    FileOutputStream fos = null;
    is = request.getInputStream();
    String submittedFilename = request.getHeader("X-File-Name");
    String extension = submittedFilename.substring(submittedFilename.indexOf("."));
    File f = File.createTempFile("profile_", extension, new File(Core.filesHome + Core.tmpDir));
    String filename = f.getName().replaceAll("\\%20", "_");
    fos = new FileOutputStream(new File(Core.filesHome + Core.tmpDir + "/" + filename));
    IOUtils.copy(is, fos);
    response.setStatus(HttpServletResponse.SC_OK);
    fos.close();
    is.close();
   
    String[] imageMagickArgs = {
      Core.imageMagickHome + "magick", 
      Core.filesHome + Core.tmpDir + "/" + filename, 
      "-resize", 
      "160x160", 
      Core.filesHome + Core.tmpDir + "/" + filename 
    };
    
    if (Core.useImageMagick == true) {
      Runtime runtime = Runtime.getRuntime();
      Process process = runtime.exec(imageMagickArgs);
      InputStream pis = process.getInputStream();
      InputStreamReader isr = new InputStreamReader(pis);
      BufferedReader br = new BufferedReader(isr);
    
      String line;
      log.info("Output of running "+ Arrays.toString(imageMagickArgs) + "is: ");

      while ((line = br.readLine()) != null) {
        log.info(line);
      }
    }
    
    log.info("\n" + filename + " uploaded");
    
    // grab patient id and if it is not null, save file to patient record
    String patientIdString = request.getParameter("patientId");  
    if (StringUtils.isNotEmpty(patientIdString)) {
      patient = (Patient)appDAO.findByIdString(Patient.class, patientIdString);
    }
    if (patient != null) {
      dto.patientId = patient.getId();
      String profileImageTempPath = Core.filesHome + Core.tmpDir + "/" + filename;
      String filesHomePatientDirPath =  Core.filesHome  + Core.patientDirPath + "/" + patient.getId() + "/";
      new File(filesHomePatientDirPath).mkdirs(); 
      File profileImageTempPathFile = new File(profileImageTempPath);
      profileImageTempPathFile.renameTo(new File(filesHomePatientDirPath + "/" + filename)); 
      patientDAO.updatePatientProfileImage(patient.getId(), filename); 
    }
    
    dto.filename = filename;
    logEvent(dto, ActivityLog.UPLOAD_IMAGE, "PatientFileService uploadProfileImage()", null, null);
  } 
 
  private File uploadFormDataFile(HttpServletRequest request, String name, String extension) throws Exception {
    ServletFileUpload upload = new ServletFileUpload();
    FileItemIterator iter = upload.getItemIterator(request);
    String uploadDir = Core.filesHome + Core.tmpDir;
    File tempFile = File.createTempFile(name, extension, new File(uploadDir));
    FileOutputStream fos = new FileOutputStream(tempFile);
    while (iter.hasNext()) {
      FileItemStream item = iter.next();
      if (!item.isFormField()) {
           IOUtils.copy(item.openStream(), fos);
           fos.close();
           break;
      }  
    }
    return tempFile;
  }
 
  public void uploadTxTongueImage(PatientFileDTO dto, HttpServletRequest request, HttpServletResponse response) throws Exception {
    File uploadedTempFile = uploadFormDataFile(request, "tongue_", ".jpg");
    response.setStatus(HttpServletResponse.SC_OK);

    String fileName = uploadedTempFile.getName();
    
    log.info("\n" + fileName + " uploaded " );
    
    Integer patientId; try { patientId = new Integer(request.getParameter("patientId")); } catch (NumberFormatException nfe) {patientId = null;}
    Integer txNoteId; try { txNoteId = new Integer(request.getParameter("txNoteId")); } catch (NumberFormatException nfe) {txNoteId = null;}
    
    String imageTempPath = uploadedTempFile.getPath();
    String description = URLDecoder.decode(request.getParameter("description"), "utf-8");

    String filesHomePatientDirPath =  Core.filesHome  + Core.patientDirPath + "/" + patientId + "/";
    new File(filesHomePatientDirPath).mkdirs(); 
    File imageTempPathFile = new File(imageTempPath);
    imageTempPathFile.renameTo(new File(filesHomePatientDirPath + "/" + fileName)); 
    
    PatientImage patientImage = new PatientImage();
    patientImage.setPatientId(patientId);
    patientImage.setPath(fileName);
    appDAO.create(patientImage);

    
    dto.filename = fileName;
    logEvent(dto, ActivityLog.UPLOAD_IMAGE, "PatientFileService uploadTxTongueImage()", null, null);
  }
 
}
