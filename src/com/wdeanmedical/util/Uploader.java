package com.wdeanmedical.util;

import java.io.File;

import com.wdeanmedical.core.Core;
import com.wdeanmedical.dto.PatientDTO;

public class Uploader {

  public static void processPatientUpload(PatientDTO dto) {
    String profileImageTempPath = Core.filesHome + Core.tmpDir + "/" + dto.profileImageTempPath;
    String filesHomePatientDirPath =  Core.filesHome  + Core.patientDirPath + "/" + dto.id + "/";
    new File(filesHomePatientDirPath).mkdirs(); 
    File profileImageTempPathFile = new File(profileImageTempPath);
    profileImageTempPathFile.renameTo( new File(filesHomePatientDirPath + "/" + dto.profileImageTempPath));     
  }
  
}
