/*
 * WDean Medical is distributed under the
 * GNU Lesser General Public License (GNU LGPL).
 * For details see: http://www.wdeanmedical.com
 * copyright 2013-2014 WDean Medical
 */
 
package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wdeanmedical.entity.PatientHistoryMedication;
import com.wdeanmedical.entity.form.WDMForm;
import com.wdeanmedical.service.AppService;

@Entity
@Table(name = "patient_medical_history")
@Inheritance(strategy = InheritanceType.JOINED)
public class MedicalHistory extends WDMForm implements Serializable {

  private static final long serialVersionUID = 795401034269323351L;

  private static final String NAME = "medical_history";
  
  private String pastSM;
  private String famHist;
  private String famHistOther;
  private String famHistNotes;
  private String allergFood;
  private String allergDrug;
  private String allergEnv;
  private String vacc;
  private String vaccNotes;
  private String subst;
  private Float smokePksDay;
  private Float yearsSmoked;
  private Float smokeYearsQuit;
  private Float etohUnitsWeek;
  private String currentDrugs;
  private List<PatientHistoryMedication> medicationList;

  public MedicalHistory() {
    this.name = NAME;
  }
  
  @Column(name = "past_sm")
  public String getPastSM() { return pastSM; }
  public void setPastSM(String pastSM) { this.pastSM = pastSM; }

  @Column(name = "fam_hist")
  public String getFamHist() { return famHist; }
  public void setFamHist(String famHist) { this.famHist = famHist; }

  @Column(name = "fam_hist_other")
  public String getFamHistOther() { return famHistOther; }
  public void setFamHistOther(String famHistOther) { this.famHistOther = famHistOther; }

  @Column(name = "fam_hist_notes")
  public String getFamHistNotes() { return famHistNotes; }
  public void setFamHistNotes(String famHistNotes) { this.famHistNotes = famHistNotes; }

  @Column(name = "allerg_food")
  public String getAllergFood() { return allergFood; }
  public void setAllergFood(String allergFood) { this.allergFood = allergFood; }

  @Column(name = "allerg_drug")
  public String getAllergDrug() { return allergDrug; }
  public void setAllergDrug(String allergDrug) { this.allergDrug = allergDrug; }

  @Column(name = "allerg_evn")
  public String getAllergEnv() { return allergEnv; }
  public void setAllergEnv(String allergEnv) { this.allergEnv = allergEnv; }

  @Column(name = "vacc")
  public String getVacc() { return vacc; }
  public void setVacc(String vacc) { this.vacc = vacc; }

  @Column(name = "vacc_notes")
  public String getVaccNotes() { return vaccNotes; }
  public void setVaccNotes(String vaccNotes) { this.vaccNotes = vaccNotes; }

  @Column(name = "subst")
  public String getSubst() { return subst; }
  public void setSubst(String subst) { this.subst = subst; }

  @Column(name = "smoke_pks_day")
  public Float getSmokePksDay() { return smokePksDay; }
  public void setSmokePksDay(Float smokePksDay) { this.smokePksDay = smokePksDay; }

  @Column(name = "years_smoked")
  public Float getYearsSmoked() { return yearsSmoked; }
  public void setYearsSmoked(Float yearsSmoked) { this.yearsSmoked = yearsSmoked; }

  @Column(name = "smoke_years_quit")
  public Float getSmokeYearsQuit() { return smokeYearsQuit; }
  public void setSmokeYearsQuit(Float smokeYearsQuit) { this.smokeYearsQuit = smokeYearsQuit; }

  @Column(name = "etoh_units_week")
  public Float getEtohUnitsWeek() { return etohUnitsWeek; }
  public void setEtohUnitsWeek(Float etohUnitsWeek) { this.etohUnitsWeek = etohUnitsWeek; }

  @Column(name = "current_drugs")
  public String getCurrentDrugs() { return currentDrugs; }
  public void setCurrentDrugs(String currentDrugs) { this.currentDrugs = currentDrugs; }
  
  @Transient
  public List<PatientHistoryMedication> getPatientMedicationList() { return medicationList; }
  public void setPatientMedicationList( List<PatientHistoryMedication> patientMedicationList) { this.medicationList = patientMedicationList; }

  protected void init(AppService service) throws Exception {
    service.loadList(PatientHistoryMedication.class, "medicalHistoryId", "patientMedicationList", this);
  } 
}
