package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "treatment_plan")
@Inheritance(strategy = InheritanceType.JOINED)
public class TreatmentPlan extends WDMForm implements Serializable {
  private static final long serialVersionUID = -5451781367800894235L;
  
  public static final String NAME = "treatment_plan";
  public static String[] PHI_FIELDS = new String[] {};
  
  private String problems;
  private String dx;
  private String axis1;
  private String axis2;
  private String axis3;
  private String axis4;
  private String axis5;
  private String goals;
  private String goalDetails;
  private String medications;
  private String prescriber;
  private String discharge;
  private Boolean release;
  private String clinicianSig;
  private Date clinicianSigDate;
  
  public TreatmentPlan() { 
    this.name = TreatmentPlan.NAME;
  }

  @Column(name = "health_issues", columnDefinition="text")
  public String getProblems() { return problems; }
  public void setProblems(String problems) { this.problems = problems; }

  @Column(name = "dx")
  public String getDx() { return dx; }
  public void setDx(String dx) { this.dx = dx; }

  @Column(name = "axis_1")
  public String getAxis1() { return axis1; }
  public void setAxis1(String axis1) { this.axis1 = axis1; }

  @Column(name = "axis_2")
  public String getAxis2() { return axis2; }
  public void setAxis2(String axis2) { this.axis2 = axis2; }

  @Column(name = "axis_3")
  public String getAxis3() { return axis3; }
  public void setAxis3(String axis3) { this.axis3 = axis3; }

  @Column(name = "axis_4")
  public String getAxis4() { return axis4; }
  public void setAxis4(String axis4) { this.axis4 = axis4; }

  @Column(name = "axis_5")
  public String getAxis5() { return axis5; }
  public void setAxis5(String axis5) { this.axis5 = axis5; }

  @Column(name = "goals", columnDefinition="text")
  public String getGoals() { return goals; }
  public void setGoals(String goals) { this.goals = goals; }

  @Column(name = "goal_details", columnDefinition="text")
  public String getGoalDetails() { return goalDetails; }
  public void setGoalDetails(String goalDetails) { this.goalDetails = goalDetails; }

  @Column(name = "medications", columnDefinition="text")
  public String getMedications() { return medications; }
  public void setMedications(String medications) { this.medications = medications; }

  @Column(name = "prescriber")
  public String getPrescriber() { return prescriber; }
  public void setPrescriber(String prescriber) { this.prescriber = prescriber; }

  @Column(name = "discharge", columnDefinition="text")
  public String getDischarge() { return discharge; }
  public void setDischarge(String discharge) { this.discharge = discharge; }

  @Column(name = "release_on_file")
  public Boolean getRelease() { return release; }
  public void setRelease(Boolean release) { this.release = release; }

  @Column(name = "clinician_sig")
  public String getClinicianSig() { return clinicianSig; }
  public void setClinicianSig(String clinicianSig) { this.clinicianSig = clinicianSig; }

  @Column(name = "clinician_sig_date")
  public Date getClinicianSigDate() { return clinicianSigDate; }
  public void setClinicianSigDate(Date clinicianSigDate) { this.clinicianSigDate = clinicianSigDate; }
  
  

}
