package com.wdeanmedical.model;

import com.wdeanmedical.core.Core;
import com.wdeanmedical.entity.Patient;

public class BodyAnnotation {
  public String canvasJSON;
  public String byte64ImageData; 
  public Boolean finalized;
  public String imageFileName; 
  public static String imageFolder(Patient patient) {
    return Core.filesHome  + Core.patientDirPath + "/" + patient.getId() + "/";
  }
}
