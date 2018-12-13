package com.wdeanmedical.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wdeanmedical.dto.AppDTO;
import com.wdeanmedical.dto.PatientDTO;
import com.wdeanmedical.entity.ActivityLog;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.PatientHistoryMedication;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.gp.entity.CPT;
import com.wdeanmedical.gp.entity.CPTModifier;
import com.wdeanmedical.gp.entity.DxCode;
import com.wdeanmedical.gp.entity.EncounterType;
import com.wdeanmedical.gp.entity.ICD9;
import com.wdeanmedical.gp.entity.Lab;
import com.wdeanmedical.gp.entity.TxCode;
import com.wdeanmedical.gp.entity.form.ChiefComplaint;
import com.wdeanmedical.gp.entity.form.Encounter;
import com.wdeanmedical.gp.entity.form.Exam;
import com.wdeanmedical.gp.entity.form.FamilyHistory;
import com.wdeanmedical.gp.entity.form.MedicalHistory;
import com.wdeanmedical.gp.entity.form.Obgyn;
import com.wdeanmedical.gp.entity.form.SoapNote;
import com.wdeanmedical.gp.entity.form.VitalSign;
import com.wdeanmedical.interfaces.ICreator;
import com.wdeanmedical.persistence.AppDAO;
import com.wdeanmedical.persistence.PatientDAO;
import com.wdeanmedical.util.DataEncryptor;

public class GPPatientService{

  PatientDAO patientDAO; 
  AppDAO appDAO;
  AppService appService;
  
  public GPPatientService(AppService appService) {
    patientDAO = appService.getPatientDAO();
    appDAO = appService.getAppDAO();
    this.appService = appService;
  }

  private static Log log = LogFactory.getLog(GPPatientService.class);

  public Encounter newEncounter(AppDTO dto) throws Exception {
    Patient patient = patientDAO.findById(Patient.class, dto.patientId);
    User clinician = (User)appDAO.findClientBySessionId(dto.sessionId);
    Encounter encounter = createEncounter(patient, clinician);
    for (int i=0; i<3; i++) {
      addDxCode(encounter); 
      addTxCode(encounter); 
      addPatientMedication(patient); 
    }
    appService.logEvent(dto, ActivityLog.CREATE_FORM, "GPPatientService newEncounter()", "Patient Encounter", null);
    return encounter;
  }

  public void addDxCode(Encounter encounter) throws Exception {
    DxCode dxCode = new DxCode();
    dxCode.setEncounterId(encounter.getId());
    patientDAO.create(dxCode);
    return;
  }
  
  public void addTxCode(Encounter encounter) throws Exception {
    TxCode txCode = new TxCode();
    txCode.setEncounterId(encounter.getId());
    patientDAO.create(txCode);
    return;
  }
  
  public void addPatientMedication(Patient patient) throws Exception {
    PatientHistoryMedication patientHistoryMedication = new PatientHistoryMedication();
    patientHistoryMedication.setPatientId(patient.getId());
    patientDAO.create(patientHistoryMedication);
    return;
  }
  
  private <T> T getOrCreateByPatientId(Class<T> Klass, Integer patientId, ICreator creator) throws Exception {
  		T instance = patientDAO.findByPatientId(Klass, patientId);
  		if (instance == null) {
  			instance = Klass.newInstance();
  			creator.init(instance);
  		}
  		return instance;
  }
  private Encounter createEncounter(final Patient patient, final User clinician) throws Exception {
    final Encounter encounter = new Encounter(); 
    EncounterType encounterType = patientDAO.findById(EncounterType.class, EncounterType.CHECK_UP);
    encounter.setEncounterType(encounterType); 
    encounter.setLastAccessed(new Date());
    encounter.setCreatedDate(new Date());
    encounter.setDate(new Date());
    encounter.setClinicianId(clinician.getId());
    encounter.setPatient(patient);
    patientDAO.saveOrUpdate(encounter);
    
    ChiefComplaint cc = new ChiefComplaint();
    cc.setPatientId(patient.getId());
    cc.setEncounter(encounter);
    cc.setClinicianId(clinician.getId());
    encounter.setCc(cc);
    
    Integer patientId = patient.getId();
    
    MedicalHistory medHistory = getOrCreateByPatientId(MedicalHistory.class, patientId,
     new ICreator() {
			@Override
      public <T> void init(T item) throws Exception {
				MedicalHistory medHistory = (MedicalHistory) item;
				medHistory.setPatientId(patient.getId());
		    medHistory.setClinicianId(clinician.getId());
		    medHistory.setDate(encounter.getDate());
			}
    });
    encounter.setMedicalHistory(medHistory);

    FamilyHistory familyHistory = getOrCreateByPatientId(FamilyHistory.class, patientId,
        new ICreator() {
   			@Override
        public <T> void init(T item) throws Exception {
   				FamilyHistory familyHistory = (FamilyHistory) item;
			    familyHistory.setPatientId(patient.getId());
			    familyHistory.setClinicianId(clinician.getId());
			    familyHistory.setDate(encounter.getDate());
   			}
    });			
    encounter.setFamilyHistory(familyHistory);
  
    VitalSign vitals = new VitalSign();
    vitals.setPatientId(patient.getId());
    vitals.setClinicianId(clinician.getId());
    vitals.setEncounter(encounter);
    vitals.setDate(encounter.getDate());
    encounter.setVitals(vitals);
    
    SoapNote soapNote = new SoapNote();
    soapNote.setPatientId(patient.getId());
    soapNote.setClinicianId(clinician.getId());
    soapNote.setEncounter(encounter);
    soapNote.setDate(encounter.getDate());
    encounter.setSOAPNote(soapNote);
    
    Exam exam = new Exam();
    exam.setPatientId(patient.getId());
    exam.setEncounter(encounter);
    exam.setClinicianId(clinician.getId());
    encounter.setExam(exam);
    
    Lab lab = new Lab();
    lab.setPatientId(patient.getId());
    lab.setEncounterId(encounter.getId());
    lab.setClinicianId(clinician.getId());
    encounter.setLab(lab);
    
    Obgyn obgyn = getOrCreateByPatientId(Obgyn.class, patientId,
    		new ICreator() {
   			@Override
        public <T> void init(T item) throws Exception {
   				Obgyn obgyn = (Obgyn) item;
			    obgyn.setPatientId(patient.getId());
			    obgyn.setClinicianId(clinician.getId());
   			}
    });		
    encounter.setObgyn(obgyn);
    
    patientDAO.saveOrUpdate(encounter);
    return encounter;
  }

  public void loadEncounter(Encounter encounter) throws Exception {
    DataEncryptor.decryptPatient(encounter.getPatient());
    encounter.getMedicalHistory().initialize(appService);
    encounter.getExam().initialize(appService);
  }
  
  public void updateDxCode(PatientDTO dto) throws Exception {
    DxCode dxCode = patientDAO.findById(DxCode.class, dto.id);
    String property = dto.updateProperty;
    dxCode.setIcd9(patientDAO.findById(ICD9.class, new Integer(dto.updatePropertyValue)));
    patientDAO.update(dxCode);
    appService.logEvent(dto, ActivityLog.EDIT_PATIENT_ENCOUNTER_FIELD, "GPPatientService updateDxCode()", property, null); 
  }
  
  public void updateTxCode(PatientDTO dto) throws Exception {
    TxCode txCode = patientDAO.findById(TxCode.class, dto.id);
    String property = StringUtils.substringAfter(dto.updateProperty, ".");
    if (property.equals("cpt")) {
      txCode.setCpt(patientDAO.findById(CPT.class, new Integer(dto.updatePropertyValue)));
    }
    else if (property.equals("cptModifier")) {
      txCode.setCptModifier(patientDAO.findById(CPTModifier.class, new Integer(dto.updatePropertyValue)));
    }
    Set<String> fieldSet = new HashSet<String>();
    fieldSet.add(property);
    patientDAO.update(txCode);
    appService.logEvent(dto, ActivityLog.EDIT_PATIENT_ENCOUNTER_FIELD, "GPPatientService updateTxCode()", property, null); 
  }

  public void setTxcodeModifier(TxCode txCode) throws Exception {
    CPTModifier modifier = patientDAO.findById(CPTModifier.class, txCode.getCptModifierId());
    if (modifier != null) {
      txCode.setCptModifier(modifier);
      patientDAO.update(txCode);
    }
  }
}
