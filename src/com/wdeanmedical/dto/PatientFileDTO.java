package com.wdeanmedical.dto;

import java.util.List;

import com.wdeanmedical.model.BodyAnnotation;

public class PatientFileDTO extends AppDTO {
  public BodyAnnotation bodyAnnotation;
  public Integer clientId;
  public Boolean deleted;
  public List<PatientFileDTO> files;
  public Integer patientInfoId;
}
