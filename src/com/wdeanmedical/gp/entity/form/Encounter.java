package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.form.WDMForm;
import com.wdeanmedical.gp.entity.DxCode;
import com.wdeanmedical.gp.entity.EncounterType;
import com.wdeanmedical.gp.entity.Lab;
import com.wdeanmedical.gp.entity.TxCode;
import com.wdeanmedical.service.AppService;
import com.wdeanmedical.service.GPPatientService;

@Entity
@Table(name = "encounter")
@Inheritance(strategy = InheritanceType.JOINED)
public class Encounter extends WDMForm implements Serializable {
  
  private static final long serialVersionUID = -5684824571238266328L;

  public static final Integer LOCK_FREE = 0;
  public static final Integer LOCK_LOCKED = 1;
  public static final Integer LOCK_OVERRIDDEN = 2;

  private static final String NAME = "encounter";

  private EncounterType encounterType;
  private ChiefComplaint chiefComplaint;
  private VitalSign vitalSign;
  private Exam exam;
  private Lab lab;
  private SoapNote soapNote;
  private MedicalHistory medicalHistory;
  private FamilyHistory familyHistory;
  private Obgyn obgyn;
  private Integer lockStatus;
  private String notes;
  private Patient patient;
  
  private List<TxCode> txCodes;
  private List<DxCode> dxCodes;
  
  public Encounter() {
    this.name = NAME;
  }
  
  @JoinColumn(name = "patient_id", referencedColumnName = "id", insertable=false, updatable=false)
  @ManyToOne(optional = false)
  public Patient getPatient() { return patient; }
  public void setPatient(Patient patient) { this.patient = patient; }
  
  @JoinColumn(name = "vital_signs", referencedColumnName = "id")
  @ManyToOne(optional = true)
  @Cascade({CascadeType.SAVE_UPDATE})
  public VitalSign getVitals() { return vitalSign; }
  public void setVitals(VitalSign vitals) { this.vitalSign = vitals; }

  @JoinColumn(name = "chief_complaint", referencedColumnName = "id")
  @ManyToOne(optional = true)
  @Cascade({CascadeType.SAVE_UPDATE})
  public ChiefComplaint getCc() { return chiefComplaint; }
  public void setCc(ChiefComplaint cc) { this.chiefComplaint = cc; }

  @JoinColumn(name = "medical_history", referencedColumnName = "id")
  @ManyToOne(optional = true)
  @Cascade({CascadeType.SAVE_UPDATE})
  public MedicalHistory getMedicalHistory() { return medicalHistory; }
  public void setMedicalHistory(MedicalHistory medicalHistory) { this.medicalHistory = medicalHistory; }

  @JoinColumn(name = "family_history", referencedColumnName = "id")
  @ManyToOne(optional = true)
  @Cascade({CascadeType.SAVE_UPDATE})
  public FamilyHistory getFamilyHistory() { return familyHistory; }
  public void setFamilyHistory(FamilyHistory familyHistory) { this.familyHistory = familyHistory; }

  @JoinColumn(name = "exam", referencedColumnName = "id")
  @ManyToOne(optional = true)
  @Cascade({CascadeType.SAVE_UPDATE})
  public Exam getExam() { return exam; }
  public void setExam(Exam exam) { this.exam = exam; }

  @JoinColumn(name = "lab", referencedColumnName = "id")
  @ManyToOne(optional = true)
  @Cascade({CascadeType.SAVE_UPDATE})
  public Lab getLab() { return lab; }
  public void setLab(Lab lab) { this.lab = lab; }

  @JoinColumn(name = "ob_gyn", referencedColumnName = "id")
  @ManyToOne(optional = true)
  @Cascade({CascadeType.SAVE_UPDATE})
  public Obgyn getObgyn() { return obgyn; }
  public void setObgyn(Obgyn obgyn) { this.obgyn = obgyn; }

  @JoinColumn(name = "soap_note", referencedColumnName = "id")
  @ManyToOne(optional = true)
  @Cascade({CascadeType.SAVE_UPDATE})
  public SoapNote getSOAPNote() { return soapNote; }
  public void setSOAPNote(SoapNote soapNote) { this.soapNote = soapNote; }

  @JoinColumn(name = "encounter_type", referencedColumnName = "id")
  @ManyToOne(optional = false)
  public EncounterType getEncounterType() { return encounterType; }
  public void setEncounterType(EncounterType encounterType) { this.encounterType = encounterType; }

  @Column(name = "lock_status")
  public Integer getLockStatus() { return lockStatus; }
  public void setLockStatus(Integer lockStatus) { this.lockStatus = lockStatus; }

  @Column(name = "notes")
  public String getNotes() { return notes; }
  public void setNotes(String notes) { this.notes = notes; }
  
  @Transient
  public List<DxCode> getDxCodes() { return dxCodes; }
  public void setDxCodes(List<DxCode> dxCodes) { this.dxCodes = dxCodes; }

  @Transient 
  public List<TxCode> getTxCodes() { return txCodes; }
  public void setTxCodes(List<TxCode> txCodes) { this.txCodes = txCodes; }
  
  @Override
  protected void init(AppService service) throws Exception {
    service.getService(GPPatientService.class).loadEncounter(this);
  } 
}
