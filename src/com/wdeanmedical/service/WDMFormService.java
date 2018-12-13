package com.wdeanmedical.service;
import org.apache.commons.beanutils.PropertyUtils;

import com.wdeanmedical.dto.AppDTO;
import com.wdeanmedical.dto.PatientDTO;
import com.wdeanmedical.entity.Guardian;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.PatientIntake;
import com.wdeanmedical.entity.form.WDMForm;
import com.wdeanmedical.gp.entity.TxCode;
import com.wdeanmedical.gp.entity.form.Encounter;

public class WDMFormService {

  public static void newInstance(Encounter encounterForm, AppService service, AppDTO dto) throws Exception {    
    PropertyUtils.copyProperties(encounterForm, service.getGPPatientService().newEncounter(dto));
  }
  
  public static void formClosed(Encounter encounter, AppService service, AppDTO dto) throws Exception {    
    Object[] list = new Object[] {encounter.getExam(), encounter.getCc(), encounter.getVitals(), encounter.getSOAPNote()};
      for(Object object: list) {
        WDMForm subForm = (WDMForm) object;
        subForm.setClosed(true);
        service.appDAO.update(subForm);
      }
   }
  
  public static void processUpdateField(PatientIntake intake, AppService service, AppDTO dto) throws Exception {    
    if (dto.updateProperty.contains(".forms")) {
      PatientService patientService = service.getPatientService();
      Patient patient = patientService.getPatient(intake.getPatientId());
      PatientDTO patientDTO = new PatientDTO();
      patientDTO.forms = dto.updatePropertyValue;
      patientDTO.patientFormId = patient.getPatientFormId();
      patientService.savePatientIntakeForms(patient, patientDTO);
    }     
  }
  
  public static void processUpdateField(TxCode txCode, AppService service, AppDTO dto) throws Exception {    
    if (dto.updateProperty.contains("cptModifierId")) {
      service.getGPPatientService().setTxcodeModifier(txCode);    
    }     
  }
  public static void processUpdateField(Guardian guardian, AppService service, AppDTO dto) throws Exception {    
    if (dto.updateProperty.contains("relation")) {
      PatientService patientService = service.getPatientService();
      patientService.updatePatientGuardianRelation(guardian, dto.patientId);
    }     
  }
  
}
