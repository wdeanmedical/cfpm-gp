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
@Table(name = "clinic_discharge")
@Inheritance(strategy = InheritanceType.JOINED)
public class ClinicDischarge extends WDMForm implements Serializable {
  private static final long serialVersionUID = 635372152121350704L;
  public static final String NAME = "clinic_discharge";
  public static String[] PHI_FIELDS = new String[] {};
  
  private String guardian;
  private String guardianRel;
  private String guardianPhone;
  private String clientSchool;
  private Date intakeDate;
  private Date dischargeDate;
  private String dischargedBy;
  private String services;
  private String discharged;
  private String dischargedReason;
  private String goals;
  private String goalsMet;
  private String goalsNotMetReason;
  private String axis1;
  private String axis2;
  private String axis3;
  private String axis4;
  private String axis5;
  private String med1;
  private String dose1;
  private String freq1;
  private String prescriber1;
  private String med2;
  private String dose2;
  private String freq2;
  private String prescriber2;
  private String med3;
  private String dose3;
  private String freq3;
  private String prescriber3;
  private String medInfo;
  private String services2;
  private Boolean service1 = false;
  private String serviceRef1;
  private String serviceResp1;
  private Boolean service2 = false;
  private String serviceRef2;
  private String serviceResp2;
  private Boolean service3 = false;
  private String serviceRef3;
  private String serviceResp3;
  private Boolean service4 = false;
  private String serviceRef4;
  private String serviceResp4;
  private Boolean service5 = false;
  private String serviceRef5;
  private String serviceResp5;
  private Boolean service6 = false;
  private String serviceRef6;
  private String serviceResp6;
  private Boolean service7 = false;
  private String serviceRef7;
  private String serviceResp7;
  private Boolean service8 = false;
  private String serviceRef8;
  private String serviceResp8;
  private Boolean service9 = false;
  private String serviceRef9;
  private String serviceResp9;
  private Boolean service10 = false;
  private String serviceRef10;
  private String serviceResp10;
  private Boolean readDoc = false;
  private String clientSig;
  private String clientSigRel;
  private Date clientSigDate;
  private String witnessSig;
  private Date witnessSigDate;
  
  public ClinicDischarge() { 
    this.name = ClinicDischarge.NAME;
  }
  
  
  @Column(name = "guardian")
  public String getGuardian() { return guardian; }
  public void setGuardian(String guardian) { this.guardian = guardian; }
  
  @Column(name = "guardian_rel")
  public String getGuardianRel() { return guardianRel; }
  public void setGuardianRel(String guardianRel) { this.guardianRel = guardianRel; }
  
  @Column(name = "guardian_phone")
  public String getGuardianPhone() { return guardianPhone; }
  public void setGuardianPhone(String guardianPhone) { this.guardianPhone = guardianPhone; }

  @Column(name = "client_school")
  public String getClientSchool() { return clientSchool; }
  public void setClientSchool(String clientSchool) { this.clientSchool = clientSchool; }

  @Column(name = "intake_date")
  public Date getIntakeDate() { return intakeDate; }
  public void setIntakeDate(Date intakeDate) { this.intakeDate = intakeDate; }

  @Column(name = "discharge_date")
  public Date getDischargeDate() { return dischargeDate; }
  public void setDischargeDate(Date dischargeDate) { this.dischargeDate = dischargeDate; }

  @Column(name = "discharged_by")
  public String getDischargedBy() { return dischargedBy; }
  public void setDischargedBy(String dischargedBy) { this.dischargedBy = dischargedBy; }

  @Column(name = "services")
  public String getServices() { return services; }
  public void setServices(String services) { this.services = services; }

  @Column(name = "discharged")
  public String getDischarged() { return discharged; }
  public void setDischarged(String discharged) { this.discharged = discharged; }

  @Column(name = "discharged_reason")
  public String getDischargedReason() { return dischargedReason; }
  public void setDischargedReason(String dischargedReason) { this.dischargedReason = dischargedReason; }

  @Column(name = "goals", columnDefinition="text")
  public String getGoals() { return goals; }
  public void setGoals(String goals) { this.goals = goals; }

  @Column(name = "goals_met", columnDefinition="text")
  public String getGoalsMet() { return goalsMet; }
  public void setGoalsMet(String goalsMet) { this.goalsMet = goalsMet; }

  @Column(name = "goals_not_met_reason", columnDefinition="text")
  public String getGoalsNotMetReason() { return goalsNotMetReason; } 
  public void setGoalsNotMetReason(String goalsNotMetReason) { this.goalsNotMetReason = goalsNotMetReason; }
  
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
  
  @Column(name = "med_1")
  public String getMed1() { return med1; }
  public void setMed1(String med1) { this.med1 = med1; }

  @Column(name = "dose_1")
  public String getDose1() { return dose1; }
  public void setDose1(String dose1) { this.dose1 = dose1; }

  @Column(name = "freq_1")
  public String getFreq1() { return freq1; }
  public void setFreq1(String freq1) { this.freq1 = freq1; }

  @Column(name = "prescriber_1")
  public String getPrescriber1() { return prescriber1; }
  public void setPrescriber1(String prescriber1) { this.prescriber1 = prescriber1; }

  @Column(name = "med_2")
  public String getMed2() { return med2; }
  public void setMed2(String med2) { this.med2 = med2; }

  @Column(name = "dose_2")
  public String getDose2() { return dose2; } 
  public void setDose2(String dose2) { this.dose2 = dose2; } 

  @Column(name = "freq_2")
  public String getFreq2() { return freq2; }
  public void setFreq2(String freq2) { this.freq2 = freq2; }

  @Column(name = "prescriber_2")
  public String getPrescriber2() { return prescriber2; }
  public void setPrescriber2(String prescriber2) { this.prescriber2 = prescriber2; }

  @Column(name = "med_3")
  public String getMed3() { return med3; }
  public void setMed3(String med3) { this.med3 = med3; }
  
  @Column(name = "dose_3")
  public String getDose3() { return dose3; }
  public void setDose3(String dose3) { this.dose3 = dose3; }

  @Column(name = "freq_3")
  public String getFreq3() { return freq3; }
  public void setFreq3(String freq3) { this.freq3 = freq3; }

  @Column(name = "prescriber_3")
  public String getPrescriber3() { return prescriber3; } 
  public void setPrescriber3(String prescriber3) { this.prescriber3 = prescriber3; }

  @Column(name = "med_info", columnDefinition="text")
  public String getMedInfo() { return medInfo; }
  public void setMedInfo(String medInfo) { this.medInfo = medInfo; }
  
  @Column(name = "services2", columnDefinition="text")
  public String getServices2() { return services2; }
  public void setServices2(String services2) { this.services2 = services2; }

  @Column(name = "service_1")
  public Boolean getService1() { return service1; }
  public void setService1(Boolean service1) { this.service1 = service1; }

  @Column(name = "service_ref_1")
  public String getServiceRef1() { return serviceRef1; }
  public void setServiceRef1(String serviceRef1) { this.serviceRef1 = serviceRef1; }

  @Column(name = "service_resp_1")
  public String getServiceResp1() { return serviceResp1; }
  public void setServiceResp1(String serviceResp1) { this.serviceResp1 = serviceResp1; }

  @Column(name = "service_2")
  public Boolean getService2() { return service2; }
  public void setService2(Boolean service2) { this.service2 = service2; }

  @Column(name = "service_ref_2")
  public String getServiceRef2() { return serviceRef2; }
  public void setServiceRef2(String serviceRef2) { this.serviceRef2 = serviceRef2; }

  @Column(name = "service_resp_2")
  public String getServiceResp2() { return serviceResp2; }
  public void setServiceResp2(String serviceResp2) { this.serviceResp2 = serviceResp2; }

  @Column(name = "service_3")
  public Boolean getService3() { return service3; }
  public void setService3(Boolean service3) { this.service3 = service3; }

  @Column(name = "service_ref_3")
  public String getServiceRef3() { return serviceRef3; }
  public void setServiceRef3(String serviceRef3) { this.serviceRef3 = serviceRef3; }

  @Column(name = "service_resp_3")
  public String getServiceResp3() { return serviceResp3; }
  public void setServiceResp3(String serviceResp3) { this.serviceResp3 = serviceResp3; }

  @Column(name = "service_4")
  public Boolean getService4() { return service4; }
  public void setService4(Boolean service4) { this.service4 = service4; }

  @Column(name = "service_ref_4")
  public String getServiceRef4() { return serviceRef4; }
  public void setServiceRef4(String serviceRef4) { this.serviceRef4 = serviceRef4; }

  @Column(name = "service_resp_4")
  public String getServiceResp4() { return serviceResp4; }
  public void setServiceResp4(String serviceResp4) { this.serviceResp4 = serviceResp4; }

  @Column(name = "service_5")
  public Boolean getService5() { return service5; }
  public void setService5(Boolean service5) { this.service5 = service5; }

  @Column(name = "service_ref_5")
  public String getServiceRef5() { return serviceRef5; }
  public void setServiceRef5(String serviceRef5) { this.serviceRef5 = serviceRef5; }

  @Column(name = "service_resp_5")
  public String getServiceResp5() { return serviceResp5; }
  public void setServiceResp5(String serviceResp5) { this.serviceResp5 = serviceResp5; }

  @Column(name = "service_6")
  public Boolean getService6() { return service6; }
  public void setService6(Boolean service6) { this.service6 = service6; }

  @Column(name = "service_ref_6")
  public String getServiceRef6() { return serviceRef6; }
  public void setServiceRef6(String serviceRef6) { this.serviceRef6 = serviceRef6; }

  @Column(name = "service_resp_6")
  public String getServiceResp6() { return serviceResp6; }
  public void setServiceResp6(String serviceResp6) { this.serviceResp6 = serviceResp6; }

  @Column(name = "service_7")
  public Boolean getService7() { return service7; }
  public void setService7(Boolean service7) { this.service7 = service7; }

  @Column(name = "service_ref_7")
  public String getServiceRef7() { return serviceRef7; }
  public void setServiceRef7(String serviceRef7) { this.serviceRef7 = serviceRef7; }

  @Column(name = "service_resp_7")
  public String getServiceResp7() { return serviceResp7; }
  public void setServiceResp7(String serviceResp7) { this.serviceResp7 = serviceResp7; }

  @Column(name = "service_8")
  public Boolean getService8() { return service8; }
  public void setService8(Boolean service8) { this.service8 = service8; }

  @Column(name = "service_ref_8")
  public String getServiceRef8() { return serviceRef8; }
  public void setServiceRef8(String serviceRef8) { this.serviceRef8 = serviceRef8; }

  @Column(name = "service_resp_8")
  public String getServiceResp8() { return serviceResp8; }
  public void setServiceResp8(String serviceResp8) { this.serviceResp8 = serviceResp8; }

  @Column(name = "service_9")
  public Boolean getService9() { return service9; }
  public void setService9(Boolean service9) { this.service9 = service9; }

  @Column(name = "service_ref_9")
  public String getServiceRef9() { return serviceRef9; }
  public void setServiceRef9(String serviceRef9) { this.serviceRef9 = serviceRef9; }

  @Column(name = "service_resp_9")
  public String getServiceResp9() { return serviceResp9; }
  public void setServiceResp9(String serviceResp9) { this.serviceResp9 = serviceResp9; }

  @Column(name = "service_10")
  public Boolean getService10() { return service10; }
  public void setService10(Boolean service10) { this.service10 = service10; }

  @Column(name = "service_ref_10")
  public String getServiceRef10() { return serviceRef10; }
  public void setServiceRef10(String serviceRef10) { this.serviceRef10 = serviceRef10; }

  @Column(name = "service_resp_10")
  public String getServiceResp10() { return serviceResp10; }
  public void setServiceResp10(String serviceResp10) { this.serviceResp10 = serviceResp10; }


  @Column(name = "read_doc")
  public Boolean getReadDoc() { return readDoc; }
  public void setReadDoc(Boolean readDoc) { this.readDoc = readDoc; }

  @Column(name = "client_sig")
  public String getClientSig() { return clientSig; }
  public void setClientSig(String clientSig) { this.clientSig = clientSig; }

  @Column(name = "client_sig_rel")
  public String getClientSigRel() { return clientSigRel; }
  public void setClientSigRel(String clientSigRel) { this.clientSigRel = clientSigRel; }

  @Column(name = "client_sig_date")
  public Date getClientSigDate() { return clientSigDate; }
  public void setClientSigDate(Date clientSigDate) { this.clientSigDate = clientSigDate; }

  @Column(name = "witness_sig")
  public String getWitnessSig() { return witnessSig; }
  public void setWitnessSig(String witnessSig) { this.witnessSig = witnessSig; }

  @Column(name = "witness_sig_date")
  public Date getWitnessSigDate() { return witnessSigDate; }
  public void setWitnessSigDate(Date witnessSigDate) { this.witnessSigDate = witnessSigDate; }
  
}
