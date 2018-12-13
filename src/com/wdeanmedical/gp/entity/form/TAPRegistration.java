package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wdeanmedical.entity.Gender;
import com.wdeanmedical.entity.USState;
import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "tap_registration_form")
@Inheritance(strategy = InheritanceType.JOINED)
public class TAPRegistration extends WDMForm implements Serializable {
  private static final long serialVersionUID = -4585187556586089201L;
  public static String[] PHI_FIELDS = new String[] {};
  public static final String NAME = "tap_registration";
  
  private String grade;
  private String repName;
  private String repRel;
  private String guardianName;
  private Gender guardianGender;
  private String guardianAddress;
  private String guardianCity;
  private USState guardianUSState;
  private String guardianPostalCode;
  private String guardianHomePhone;
  private String guardianCellPhone;
  private String guardianEmail;
  private String days;
  private String arrivingBy;
  private String leavingBy;
  private String privateRide;
  private String pickUpName1;
  private String pickUpRel1;
  private String pickUpPhone1;
  private String pickUpName2;
  private String pickUpRel2;
  private String pickUpPhone2;
  private String livesWith;
  private String livesWithOther;
  private String lang; 
  private String langOther; 
  private String pcpName; 
  private String pcpPhone; 
  private String pcpAddress; 
  private String insurance; 
  private String policyNumber; 
  private String groupNumber; 
  private String insured; 
  private String ssn; 
  private String hospital; 
  private String emergName1;
  private String emergRel1;
  private String emergPhone1;
  private String emergName2;
  private String emergRel2;
  private String emergPhone2;
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
  private String allergy1;
  private String allergyLevel1;
  private String allergy2;
  private String allergyLevel2;
  private String allergy3;
  private String allergyLevel3;
  private String allergyInfo;
  private String goals;
  private String prevProgram;
  private String prevProgramAssess;
  private String iepInfo;
  private String interests;
  private String food;
  private String gpsTransport;
  private String violence;
  private String violenceDesc;
  private String medRelSig;
  private Date medRelSigDate;
  private String medAdminSig;
  private Date medAdminSigDate;
  private String reportingSig;
  private Date reportingSigDate;
  private String photo;
  private String photoSig;
  private Date photoSigDate;
  private String disciplineSig;
  private Date disciplineSigDate;
  private String attendenceSig;
  private Date attendenceSigDate;
  private Boolean sig;
  private String guardianSig;
  private Date guardianSigDate;
  private String childSig;
  private Date childSigDate;
  private String intakeCoordSig;
  private Date intakeCoordSigDate;
  
  
  public TAPRegistration() {
    this.name = TAPRegistration.NAME;
  }


  @Column(name = "grade")
  public String getGrade() { return grade; }
  public void setGrade(String grade) { this.grade = grade; }

  @Column(name = "rep_name")
  public String getRepName() { return repName; }
  public void setRepName(String repName) { this.repName = repName; }

  @Column(name = "rep_rel")
  public String getRepRel() { return repRel; }
  public void setRepRel(String repRel) { this.repRel = repRel; }

  @Column(name = "guardian_name")
  public String getGuardianName() { return guardianName; }
  public void setGuardianName(String guardianName) { this.guardianName = guardianName; }

  @Column(name = "guardian_gender")
  public Gender getGuardianGender() { return guardianGender; }
  public void setGuardianGender(Gender guardianGender) { this.guardianGender = guardianGender; }

  @Column(name = "guardian_address")
  public String getGuardianAddress() { return guardianAddress; }
  public void setGuardianAddress(String guardianAddress) { this.guardianAddress = guardianAddress; }

  @Column(name = "guardian_city")
  public String getGuardianCity() { return guardianCity; }
  public void setGuardianCity(String guardianCity) { this.guardianCity = guardianCity; }

  @Column(name = "guardian_us_state")
  public USState getGuardianUSState() { return guardianUSState; }
  public void setGuardianUSState(USState guardianUSState) { this.guardianUSState = guardianUSState; }

  @Column(name = "guardian_postal_code")
  public String getGuardianPostalCode() { return guardianPostalCode; }
  public void setGuardianPostalCode(String guardianPostalCode) { this.guardianPostalCode = guardianPostalCode; }

  @Column(name = "guardian_home_phone")
  public String getGuardianHomePhone() { return guardianHomePhone; }
  public void setGuardianHomePhone(String guardianHomePhone) { this.guardianHomePhone = guardianHomePhone; }

  @Column(name = "guardian_cell_phone")
  public String getGuardianCellPhone() { return guardianCellPhone; }
  public void setGuardianCellPhone(String guardianCellPhone) { this.guardianCellPhone = guardianCellPhone; }

  @Column(name = "guardian_email")
  public String getGuardianEmail() { return guardianEmail; }
  public void setGuardianEmail(String guardianEmail) { this.guardianEmail = guardianEmail; }

  @Column(name = "days")
  public String getDays() { return days; }
  public void setDays(String days) { this.days = days; }

  @Column(name = "arriving_by")
  public String getArrivingBy() { return arrivingBy; }
  public void setArrivingBy(String arrivingBy) { this.arrivingBy = arrivingBy; }

  @Column(name = "leaving_by")
  public String getLeavingBy() { return leavingBy; } 
  public void setLeavingBy(String leavingBy) { this.leavingBy = leavingBy; }

  @Column(name = "private_ride")
  public String getPrivateRide() { return privateRide; }
  public void setPrivateRide(String privateRide) { this.privateRide = privateRide; }

  @Column(name = "pick_up_name_1")
  public String getPickUpName1() { return pickUpName1; }
  public void setPickUpName1(String pickUpName1) { this.pickUpName1 = pickUpName1; }

  @Column(name = "pick_up_rel_1")
  public String getPickUpRel1() { return pickUpRel1; }
  public void setPickUpRel1(String pickUpRel1) { this.pickUpRel1 = pickUpRel1; }

  @Column(name = "pick_up_phone_1")
  public String getPickUpPhone1() { return pickUpPhone1; }
  public void setPickUpPhone1(String pickUpPhone1) { this.pickUpPhone1 = pickUpPhone1; }

  @Column(name = "pick_up_name_2")
  public String getPickUpName2() { return pickUpName2; } 
  public void setPickUpName2(String pickUpName2) { this.pickUpName2 = pickUpName2; }

  @Column(name = "pick_up_rel_2")
  public String getPickUpRel2() { return pickUpRel2; }
  public void setPickUpRel2(String pickUpRel2) { this.pickUpRel2 = pickUpRel2; }

  @Column(name = "pick_up_phone_2")
  public String getPickUpPhone2() { return pickUpPhone2; }
  public void setPickUpPhone2(String pickUpPhone2) { this.pickUpPhone2 = pickUpPhone2; }

  @Column(name = "lives_with")
  public String getLivesWith() { return livesWith; }
  public void setLivesWith(String livesWith) { this.livesWith = livesWith; }

  @Column(name = "lives_with_other")
  public String getLivesWithOther() { return livesWithOther; }
  public void setLivesWithOther(String livesWithOther) { this.livesWithOther = livesWithOther; }

  @Column(name = "lang")
  public String getLang() { return lang; }
  public void setLang(String lang) { this.lang = lang; }

  @Column(name = "lang_other")
  public String getLangOther() { return langOther; }
  public void setLangOther(String langOther) { this.langOther = langOther; }

  @Column(name = "pcp_name")
  public String getPcpName() { return pcpName; }
  public void setPcpName(String pcpName) { this.pcpName = pcpName; }

  @Column(name = "pcp_phone")
  public String getPcpPhone() { return pcpPhone; }
  public void setPcpPhone(String pcpPhone) { this.pcpPhone = pcpPhone; }

  @Column(name = "pcp_address")
  public String getPcpAddress() { return pcpAddress; }
  public void setPcpAddress(String pcpAddress) { this.pcpAddress = pcpAddress; }

  @Column(name = "insurance")
  public String getInsurance() { return insurance; }
  public void setInsurance(String insurance) { this.insurance = insurance; }

  @Column(name = "policy_number")
  public String getPolicyNumber() { return policyNumber; }
  public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }

  @Column(name = "group_number")
  public String getGroupNumber() { return groupNumber; }
  public void setGroupNumber(String groupNumber) { this.groupNumber = groupNumber; }

  @Column(name = "insured")
  public String getInsured() { return insured; }
  public void setInsured(String insured) { this.insured = insured; }

  @Column(name = "ssn")
  public String getSsn() { return ssn; }
  public void setSsn(String ssn) { this.ssn = ssn; }

  @Column(name = "hospital")
  public String getHospital() { return hospital; }
  public void setHospital(String hospital) { this.hospital = hospital; }
  
  @Column(name = "emerg_name_1")
  public String getEmergName1() { return emergName1; }
  public void setEmergName1(String emergName1) { this.emergName1 = emergName1; }

  @Column(name = "emerg_rel_1")
  public String getEmergRel1() { return emergRel1; }
  public void setEmergRel1(String emergRel1) { this.emergRel1 = emergRel1; }

  @Column(name = "emerg_phone_1")
  public String getEmergPhone1() { return emergPhone1; }
  public void setEmergPhone1(String emergPhone1) { this.emergPhone1 = emergPhone1; }

  @Column(name = "emerg_name_2")
  public String getEmergName2() { return emergName2; } 
  public void setEmergName2(String emergName2) { this.emergName2 = emergName2; }

  @Column(name = "emerg_rel_2")
  public String getEmergRel2() { return emergRel2; }
  public void setEmergRel2(String emergRel2) { this.emergRel2 = emergRel2; }

  @Column(name = "emerg_phone_2")
  public String getEmergPhone2() { return emergPhone2; }
  public void setEmergPhone2(String emergPhone2) { this.emergPhone2 = emergPhone2; }


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

  @Column(name = "med_info")
  public String getMedInfo() { return medInfo; }
  public void setMedInfo(String medInfo) { this.medInfo = medInfo; }

  @Column(name = "allergy_1")
  public String getAllergy1() { return allergy1; }
  public void setAllergy1(String allergy1) { this.allergy1 = allergy1; }

  @Column(name = "allergy_level_1")
  public String getAllergyLevel1() { return allergyLevel1; }
  public void setAllergyLevel1(String allergyLevel1) { this.allergyLevel1 = allergyLevel1; }

  @Column(name = "allergy_2")
  public String getAllergy2() { return allergy2; }
  public void setAllergy2(String allergy2) { this.allergy2 = allergy2; }

  @Column(name = "allergy_level_2")
  public String getAllergyLevel2() { return allergyLevel2; }
  public void setAllergyLevel2(String allergyLevel2) { this.allergyLevel2 = allergyLevel2; }

  @Column(name = "allergy_3")
  public String getAllergy3() { return allergy3; }
  public void setAllergy3(String allergy3) { this.allergy3 = allergy3; }

  @Column(name = "allergy_level_3")
  public String getAllergyLevel3() { return allergyLevel3; }
  public void setAllergyLevel3(String allergyLevel3) { this.allergyLevel3 = allergyLevel3; }

  @Column(name = "allergy_info")
  public String getAllergyInfo() { return allergyInfo; }
  public void setAllergyInfo(String allergyInfo) { this.allergyInfo = allergyInfo; }

  @Column(name = "goals")
  public String getGoals() { return goals; }
  public void setGoals(String goals) { this.goals = goals; }

  @Column(name = "prev_program")
  public String getPrevProgram() { return prevProgram; }
  public void setPrevProgram(String prevProgram) { this.prevProgram = prevProgram; }

  @Column(name = "prev_program_assess")
  public String getPrevProgramAssess() { return prevProgramAssess; }
  public void setPrevProgramAssess(String prevProgramAssess) { this.prevProgramAssess = prevProgramAssess; }

  @Column(name = "iep_info")
  public String getIepInfo() { return iepInfo; }
  public void setIepInfo(String iepInfo) { this.iepInfo = iepInfo; }

  @Column(name = "interests")
  public String getInterests() { return interests; }
  public void setInterests(String interests) { this.interests = interests; }

  @Column(name = "food")
  public String getFood() { return food; }
  public void setFood(String food) { this.food = food; }

  @Column(name = "gps_transport")
  public String getGpsTransport() { return gpsTransport; }
  public void setGpsTransport(String gpsTransport) { this.gpsTransport = gpsTransport; }

  @Column(name = "violence")
  public String getViolence() { return violence; }
  public void setViolence(String violence) { this.violence = violence; }

  @Column(name = "violence_desc")
  public String getViolenceDesc() { return violenceDesc; }
  public void setViolenceDesc(String violenceDesc) { this.violenceDesc = violenceDesc; }

  @Column(name = "med_rel_sig")
  public String getMedRelSig() { return medRelSig; }
  public void setMedRelSig(String medRelSig) { this.medRelSig = medRelSig; }

  @Column(name = "med_rel_sig_date")
  public Date getMedRelSigDate() { return medRelSigDate; }
  public void setMedRelSigDate(Date medRelSigDate) { this.medRelSigDate = medRelSigDate; }

  @Column(name = "med_admin_sig")
  public String getMedAdminSig() { return medAdminSig; }
  public void setMedAdminSig(String medAdminSig) { this.medAdminSig = medAdminSig; }

  @Column(name = "med_admin_sig_date")
  public Date getMedAdminSigDate() { return medAdminSigDate; }
  public void setMedAdminSigDate(Date medAdminSigDate) { this.medAdminSigDate = medAdminSigDate; }

  @Column(name = "reporting_sig")
  public String getReportingSig() { return reportingSig; }
  public void setReportingSig(String reportingSig) { this.reportingSig = reportingSig; }

  @Column(name = "reporting_sig_date")
  public Date getReportingSigDate() { return reportingSigDate; }
  public void setReportingSigDate(Date reportingSigDate) { this.reportingSigDate = reportingSigDate; }

  @Column(name = "photo")
  public String getPhoto() { return photo; }
  public void setPhoto(String photo) { this.photo = photo; }

  @Column(name = "photo_sig")
  public String getPhotoSig() { return photoSig; }
  public void setPhotoSig(String photoSig) { this.photoSig = photoSig; }

  @Column(name = "photo_sig_date")
  public Date getPhotoSigDate() { return photoSigDate; }
  public void setPhotoSigDate(Date photoSigDate) { this.photoSigDate = photoSigDate; }

  @Column(name = "discipline_sig")
  public String getDisciplineSig() { return disciplineSig; }
  public void setDisciplineSig(String disciplineSig) { this.disciplineSig = disciplineSig; }

  @Column(name = "discipline_sig_date")
  public Date getDisciplineSigDate() { return disciplineSigDate; }
  public void setDisciplineSigDate(Date disciplineSigDate) { this.disciplineSigDate = disciplineSigDate; }

  @Column(name = "attendence_sig")
  public String getAttendenceSig() { return attendenceSig; }
  public void setAttendenceSig(String attendenceSig) { this.attendenceSig = attendenceSig; }

  @Column(name = "attendence_sig_date")
  public Date getAttendenceSigDate() { return attendenceSigDate; }
  public void setAttendenceSigDate(Date attendenceSigDate) { this.attendenceSigDate = attendenceSigDate; }

  @Column(name = "sig")
  public Boolean getSig() { return sig; }
  public void setSig(Boolean sig) { this.sig = sig; }

  @Column(name = "guardian_sig")
  public String getGuardianSig() { return guardianSig; }
  public void setGuardianSig(String guardianSig) { this.guardianSig = guardianSig; }

  @Column(name = "guardian_sig_date")
  public Date getGuardianSigDate() { return guardianSigDate; }
  public void setGuardianSigDate(Date guardianSigDate) { this.guardianSigDate = guardianSigDate; }

  @Column(name = "child_sig")
  public String getChildSig() { return childSig; }
  public void setChildSig(String childSig) { this.childSig = childSig; }

  @Column(name = "child_sig_date")
  public Date getChildSigDate() { return childSigDate; }
  public void setChildSigDate(Date childSigDate) { this.childSigDate = childSigDate; }

  @Column(name = "intake_coord_sig")
  public String getIntakeCoordSig() { return intakeCoordSig; }
  public void setIntakeCoordSig(String intakeCoordSig) { this.intakeCoordSig = intakeCoordSig; }

  @Column(name = "intake_coord_sig_date")
  public Date getIntakeCoordSigDate() { return intakeCoordSigDate; }
  public void setIntakeCoordSigDate(Date intakeCoordSigDate) { this.intakeCoordSigDate = intakeCoordSigDate; }

}
