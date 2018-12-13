package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wdeanmedical.entity.Gender;
import com.wdeanmedical.entity.USState;
import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "child_history")
@Inheritance(strategy = InheritanceType.JOINED)
public class ChildHistory extends WDMForm implements Serializable {
  private static final long serialVersionUID = -1596518772738187867L;
  public static final String NAME = "child_history";
  public static String[] PHI_FIELDS = new String[] {};
  
  private String authorName;
  private String relationship;
  private String grade;
  private Boolean adopted;
  private String motherFirstName;
  private String motherMiddleName;
  private String motherLastName;
  private String motherPrimaryPhone;
  private String motherSecondaryPhone;
  private String motherOccupation;
  private String motherAddress;
  private String motherCity;
  private USState motherState;
  private String motherPostalCode;
  private String fatherFirstName;
  private String fatherMiddleName;
  private String fatherLastName;
  private String fatherPrimaryPhone;
  private String fatherSecondaryPhone;
  private String fatherOccupation;
  private String fatherAddress;
  private String fatherCity;
  private USState fatherState;
  private String fatherPostalCode;
  private String caregiverFirstName;
  private String caregiverMiddleName;
  private String caregiverLastName;
  private String caregiverPrimaryPhone;
  private String caregiverSecondaryPhone;
  private String caregiverOccupation;
  private String caregiverAddress;
  private String caregiverCity;
  private USState caregiverState;
  private String caregiverPostalCode;
  private String emergFirstName;
  private String emergMiddleName;
  private String emergLastName;
  private String emergPrimaryPhone;
  private String emergSecondaryPhone;
  private String emergOccupation;
  private String emergAddress;
  private String emergCity;
  private USState emergState;
  private String emergPostalCode;
  private String concerns;
  private String ethnicity;
  private String ethnicityNotes;
  private String religion;
  private String religionNotes;
  private String siblingName1;
  private Gender siblingGender1;
  private Date siblingDob1;
  private String siblingRel1;
  private String siblingName2;
  private Gender siblingGender2;
  private Date siblingDob2;
  private String siblingRel2;
  private String siblingName3;
  private Gender siblingGender3;
  private Date siblingDob3;
  private String siblingRel3;
  private String siblingName4;
  private Gender siblingGender4;
  private Date siblingDob4;
  private String siblingRel4;
  private String siblingName5;
  private Gender siblingGender5;
  private Date siblingDob5;
  private String siblingRel5;
  private String siblingName6;
  private Gender siblingGender6;
  private Date siblingDob6;
  private String siblingRel6;
  private String otherPersons;
  private String languages;
  private String prof1;
  private String profAge1;
  private String prof2;
  private String profAge2;
  private String prof3;
  private String profAge3;
  private String prof4;
  private String profAge4;
  private Boolean medicalProblems;
  private String medicalProblemsDuration;
  private Boolean specialDiet;
  private String specialDietDuration;
  private Boolean medications;
  private String medicationsDuration;
  private String preg;
  private String weeksBirth;
  private String accidents;
  private String accidentsDuration;
  private String motherAge;
  private Boolean complications;
  private String complicationsDesc;
  private String birthWeight;
  private Boolean oxygen;
  private String oxygenReason;
  private Boolean specialCare;
  private String specialCareReason;
  private String hospitalDuration;
  private String motherHospitalDuration;
  private Boolean easyBaby;
  private Boolean enjoysPeople;
  private Boolean irritable;
  private Boolean soothe;
  private Boolean sleep;
  private Boolean quiet;
  private Boolean sick;
  private Boolean feeding;
  private Boolean sensory;
  private Boolean colic;
  private String illness;
  private String sat;
  private String crawled;
  private String walked;
  private String words;
  private String sentences;
  private String slept;
  private String daytimeWetting;
  private String bedWetting;
  private String bowelControl;
  private String pcpName;
  private String pcpAddress;
  private String pcpPhone;
  private String lastExam;
  private String allergy1;
  private String allergyLevel1;
  private String allergy2;
  private String allergyLevel2;
  private String allergy3;
  private String allergyLevel3;
  private String allergyInfo;
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
  private String problem1;
  private String problem1Desc;
  private String problem2;
  private String problem2Desc;
  private String problem3;
  private String problem3Desc;
  private String problem4;
  private String problem4Desc;
  private String problem5;
  private String problem5Desc;
  private String problem6;
  private String problem6Desc;
  private String problem7;
  private String problem7Desc;
  private String problem8;
  private String problem8Desc;
  private String problem9;
  private String problem9Desc;
  private String problem10;
  private String problem10Desc;
  private String problem11;
  private String problem11Desc;
  private String problem12;
  private String problem12Desc;
  private String problem13;
  private String problem13Desc;
  private String problem14;
  private String problem14Desc;
  private String problem15;
  private String problem15Desc;
  private String problem16;
  private String problem16Desc;
  private String problem17;
  private String problem17Desc;
  private String problem18;
  private String problem18Desc;
  private String problem19;
  private String problem19Desc;
  private String problem20;
  private String problem20Desc;
  private String problem21;
  private String problem21Desc;
  private String problem22;
  private String problem22Desc;
  private String problem23;
  private String problem23Desc;
  private String problem24;
  private String problem24Desc;
  private String problem25;
  private String problem25Desc;
  private String problem26;
  private String problem26Desc;
  private String problem27;
  private String problem27Desc;
  private String disease1;
  private String disease1Desc;
  private String disease2;
  private String disease2Desc;
  private String disease3;
  private String disease3Desc;
  private String disease4;
  private String disease4Desc;
  private String disease5;
  private String disease5Desc;
  private String disease6;
  private String disease6Desc;
  private Boolean soc1;
  private Boolean soc2;
  private Boolean soc3;
  private Boolean soc4;
  private Boolean school;
  private String schoolName;
  private String schoolGrade;
  private Boolean sped;
  private String spedName;
  private Boolean repeated;
  private String repeatedDesc;
  private Boolean dis;
  private String disDesc;
  private String bedTime;
  private String sleepTime;
  private String sleepHours;
  private Boolean snore;
  private Boolean stressors;
  private String stressorsDesc;
  private String familyStressors;
  private String information;
  
  
  @Column(name = "author_name", columnDefinition = "varchar(80)")
  public String getAuthorName() { return authorName; }
  public void setAuthorName(String authorName) { this.authorName = authorName; }

  @Column(name = "relationship", columnDefinition = "varchar(20)")
  public String getRelationship() { return relationship; }
  public void setRelationship(String relationship) { this.relationship = relationship; }

  @Column(name = "grade", columnDefinition = "varchar(20)")
  public String getGrade() { return grade; }
  public void setGrade(String grade) { this.grade = grade; }

  @Column(name = "adopted")
  public Boolean getAdopted() { return adopted; }
  public void setAdopted(Boolean adopted) { this.adopted = adopted; }

  @Column(name = "mother_first_name", columnDefinition = "varchar(20)")
  public String getMotherFirstName() { return motherFirstName; }
  public void setMotherFirstName(String motherFirstName) { this.motherFirstName = motherFirstName; }

  @Column(name = "mother_middle_name", columnDefinition = "varchar(20)")
  public String getMotherMiddleName() { return motherMiddleName; }
  public void setMotherMiddleName(String motherMiddleName) { this.motherMiddleName = motherMiddleName; }

  @Column(name = "mother_last_name", columnDefinition = "varchar(20)")
  public String getMotherLastName() { return motherLastName; }
  public void setMotherLastName(String motherLastName) { this.motherLastName = motherLastName; }

  @Column(name = "mother_primary_phone", columnDefinition = "varchar(20)")
  public String getMotherPrimaryPhone() { return motherPrimaryPhone; }
  public void setMotherPrimaryPhone(String motherPrimaryPhone) { this.motherPrimaryPhone = motherPrimaryPhone; }

  @Column(name = "mother_secondary_phone", columnDefinition = "varchar(20)")
  public String getMotherSecondaryPhone() { return motherSecondaryPhone; }
  public void setMotherSecondaryPhone(String motherSecondaryPhone) { this.motherSecondaryPhone = motherSecondaryPhone; }

  @Column(name = "mother_occupation", columnDefinition = "varchar(20)")
  public String getMotherOccupation() { return motherOccupation; }
  public void setMotherOccupation(String motherOccupation) { this.motherOccupation = motherOccupation; }

  @Column(name = "mother_address")
  public String getMotherAddress() { return motherAddress; }
  public void setMotherAddress(String motherAddress) { this.motherAddress = motherAddress; }

  @Column(name = "mother_city", columnDefinition = "varchar(80)")
  public String getMotherCity() { return motherCity; }
  public void setMotherCity(String motherCity) { this.motherCity = motherCity; }

  @JoinColumn(name = "mother_state", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public USState getMotherState() { return motherState; }
  public void setMotherState(USState motherState) { this.motherState = motherState; }

  @Column(name = "mother_postal_code", columnDefinition = "varchar(20)")
  public String getMotherPostalCode() { return motherPostalCode; }
  public void setMotherPostalCode(String motherPostalCode) { this.motherPostalCode = motherPostalCode; }

  @Column(name = "father_first_name", columnDefinition = "varchar(20)")
  public String getFatherFirstName() { return fatherFirstName; }
  public void setFatherFirstName(String fatherFirstName) { this.fatherFirstName = fatherFirstName; }

  @Column(name = "father_middle_name", columnDefinition = "varchar(20)")
  public String getFatherMiddleName() { return fatherMiddleName; }
  public void setFatherMiddleName(String fatherMiddleName) { this.fatherMiddleName = fatherMiddleName; }

  @Column(name = "father_last_name", columnDefinition = "varchar(20)")
  public String getFatherLastName() { return fatherLastName; }
  public void setFatherLastName(String fatherLastName) { this.fatherLastName = fatherLastName; }

  @Column(name = "father_primary_phone", columnDefinition = "varchar(20)")
  public String getFatherPrimaryPhone() { return fatherPrimaryPhone; }
  public void setFatherPrimaryPhone(String fatherPrimaryPhone) { this.fatherPrimaryPhone = fatherPrimaryPhone; }

  @Column(name = "father_secondary_phone", columnDefinition = "varchar(20)")
  public String getFatherSecondaryPhone() { return fatherSecondaryPhone; }
  public void setFatherSecondaryPhone(String fatherSecondaryPhone) { this.fatherSecondaryPhone = fatherSecondaryPhone; }

  @Column(name = "father_occupation", columnDefinition = "varchar(20)")
  public String getFatherOccupation() { return fatherOccupation; }
  public void setFatherOccupation(String fatherOccupation) { this.fatherOccupation = fatherOccupation; }

  @Column(name = "father_address")
  public String getFatherAddress() { return fatherAddress; }
  public void setFatherAddress(String fatherAddress) { this.fatherAddress = fatherAddress; }

  @Column(name = "father_city", columnDefinition = "varchar(80)")
  public String getFatherCity() { return fatherCity; }
  public void setFatherCity(String fatherCity) { this.fatherCity = fatherCity; }
  
  @JoinColumn(name = "father_state", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public USState getFatherState() { return fatherState; }
  public void setFatherState(USState fatherState) { this.fatherState = fatherState; }
  
  @Column(name = "father_postal_code", columnDefinition = "varchar(20)")
  public String getFatherPostalCode() { return fatherPostalCode; }
  public void setFatherPostalCode(String fatherPostalCode) { this.fatherPostalCode = fatherPostalCode; }

  @Column(name = "caregiver_first_name", columnDefinition = "varchar(20)")
  public String getCaregiverFirstName() { return caregiverFirstName; }
  public void setCaregiverFirstName(String caregiverFirstName) { this.caregiverFirstName = caregiverFirstName; }

  @Column(name = "caregiver_middle_name", columnDefinition = "varchar(20)")
  public String getCaregiverMiddleName() { return caregiverMiddleName; }
  public void setCaregiverMiddleName(String caregiverMiddleName) { this.caregiverMiddleName = caregiverMiddleName; }

  @Column(name = "caregiver_last_name", columnDefinition = "varchar(20)")
  public String getCaregiverLastName() { return caregiverLastName; }
  public void setCaregiverLastName(String caregiverLastName) { this.caregiverLastName = caregiverLastName; }

  @Column(name = "caregiver_primary_phone", columnDefinition = "varchar(20)")
  public String getCaregiverPrimaryPhone() { return caregiverPrimaryPhone; }
  public void setCaregiverPrimaryPhone(String caregiverPrimaryPhone) { this.caregiverPrimaryPhone = caregiverPrimaryPhone; }

  @Column(name = "caregiver_secondary_phone", columnDefinition = "varchar(20)")
  public String getCaregiverSecondaryPhone() { return caregiverSecondaryPhone; }
  public void setCaregiverSecondaryPhone(String caregiverSecondaryPhone) { this.caregiverSecondaryPhone = caregiverSecondaryPhone; }

  @Column(name = "caregiver_occupation", columnDefinition = "varchar(20)")
  public String getCaregiverOccupation() { return caregiverOccupation; }
  public void setCaregiverOccupation(String caregiverOccupation) { this.caregiverOccupation = caregiverOccupation; }

  @Column(name = "caregiver_address")
  public String getCaregiverAddress() { return caregiverAddress; }
  public void setCaregiverAddress(String caregiverAddress) { this.caregiverAddress = caregiverAddress; }

  @Column(name = "caregiver_city", columnDefinition = "varchar(20)")
  public String getCaregiverCity() { return caregiverCity; }
  public void setCaregiverCity(String caregiverCity) { this.caregiverCity = caregiverCity; }

  @JoinColumn(name = "caregiver_state", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public USState getCaregiverState() { return caregiverState; }
  public void setCaregiverState(USState caregiverState) { this.caregiverState = caregiverState; }

  @Column(name = "caregiver_postal_code", columnDefinition = "varchar(20)")
  public String getCaregiverPostalCode() { return caregiverPostalCode; }
  public void setCaregiverPostalCode(String caregiverPostalCode) { this.caregiverPostalCode = caregiverPostalCode; }

  @Column(name = "emerg_first_name", columnDefinition = "varchar(20)")
  public String getEmergFirstName() { return emergFirstName; }
  public void setEmergFirstName(String emergFirstName) { this.emergFirstName = emergFirstName; }

  @Column(name = "emerg_middle_name", columnDefinition = "varchar(20)")
  public String getEmergMiddleName() { return emergMiddleName; }
  public void setEmergMiddleName(String emergMiddleName) { this.emergMiddleName = emergMiddleName; }

  @Column(name = "emerg_last_name", columnDefinition = "varchar(20)")
  public String getEmergLastName() { return emergLastName; }
  public void setEmergLastName(String emergLastName) { this.emergLastName = emergLastName; }

  @Column(name = "emerg_primary_phone", columnDefinition = "varchar(20)")
  public String getEmergPrimaryPhone() { return emergPrimaryPhone; }
  public void setEmergPrimaryPhone(String emergPrimaryPhone) { this.emergPrimaryPhone = emergPrimaryPhone; }

  @Column(name = "emerg_secondary_phone", columnDefinition = "varchar(20)")
  public String getEmergSecondaryPhone() { return emergSecondaryPhone; }
  public void setEmergSecondaryPhone(String emergSecondaryPhone) { this.emergSecondaryPhone = emergSecondaryPhone; }

  @Column(name = "emerg_occupation", columnDefinition = "varchar(20)")
  public String getEmergOccupation() { return emergOccupation; } 
  public void setEmergOccupation(String emergOccupation) { this.emergOccupation = emergOccupation; }

  @Column(name = "emerg_address")
  public String getEmergAddress() { return emergAddress; }
  public void setEmergAddress(String emergAddress) { this.emergAddress = emergAddress; }

  @Column(name = "emerg_city", columnDefinition = "varchar(20)")
  public String getEmergCity() { return emergCity; }
  public void setEmergCity(String emergCity) { this.emergCity = emergCity; }

  @JoinColumn(name = "emerg_state", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public USState getEmergState() { return emergState; }
  public void setEmergState(USState emergState) { this.emergState = emergState; }

  @Column(name = "emerg_postal_code", columnDefinition = "varchar(20)")
  public String getEmergPostalCode() { return emergPostalCode; }
  public void setEmergPostalCode(String emergPostalCode) { this.emergPostalCode = emergPostalCode; }

  @Column(name = "concerns", columnDefinition="text")
  public String getConcerns() { return concerns; }
  public void setConcerns(String concerns) { this.concerns = concerns; }

  @Column(name = "ethnicity", columnDefinition = "varchar(80)")
  public String getEthnicity() { return ethnicity; }
  public void setEthnicity(String ethnicity) { this.ethnicity = ethnicity; }

  @Column(name = "ethnicity_notes")
  public String getEthnicityNotes() { return ethnicityNotes; }
  public void setEthnicityNotes(String ethnicityNotes) { this.ethnicityNotes = ethnicityNotes; }

  @Column(name = "religion", columnDefinition = "varchar(20)")
  public String getReligion() { return religion; }
  public void setReligion(String religion) { this.religion = religion; }

  @Column(name = "religion_notes")
  public String getReligionNotes() { return religionNotes; }
  public void setReligionNotes(String religionNotes) { this.religionNotes = religionNotes; }

  @Column(name = "sibling_name_1", columnDefinition = "varchar(20)")
  public String getSiblingName1() { return siblingName1; }
  public void setSiblingName1(String siblingName1) { this.siblingName1 = siblingName1; }

  @JoinColumn(name = "sibling_gender_1", referencedColumnName = "id")
  @ManyToOne(optional = true) 
  public Gender getSiblingGender1() { return siblingGender1; }
  public void setSiblingGender1(Gender siblingGender1) { this.siblingGender1 = siblingGender1; }

  @Column(name = "sibling_dob_1")
  public Date getSiblingDob1() { return siblingDob1; }
  public void setSiblingDob1(Date siblingDob1) { this.siblingDob1 = siblingDob1; }

  @Column(name = "sibling_rel_1", columnDefinition = "varchar(20)")
  public String getSiblingRel1() { return siblingRel1; }
  public void setSiblingRel1(String siblingRel1) { this.siblingRel1 = siblingRel1; }

  @Column(name = "sibling_name_2", columnDefinition = "varchar(20)")
  public String getSiblingName2() { return siblingName2; }
  public void setSiblingName2(String siblingName2) { this.siblingName2 = siblingName2; }

  @JoinColumn(name = "sibling_gender_2", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public Gender getSiblingGender2() { return siblingGender2; }
  public void setSiblingGender2(Gender siblingGender2) { this.siblingGender2 = siblingGender2; }

  @Column(name = "sibling_dob_2")
  public Date getSiblingDob2() { return siblingDob2; }
  public void setSiblingDob2(Date siblingDob2) { this.siblingDob2 = siblingDob2; }

  @Column(name = "sibling_rel_2", columnDefinition = "varchar(20)")
  public String getSiblingRel2() { return siblingRel2; }
  public void setSiblingRel2(String siblingRel2) { this.siblingRel2 = siblingRel2; }

  @Column(name = "sibling_name_3", columnDefinition = "varchar(20)")
  public String getSiblingName3() { return siblingName3; }
  public void setSiblingName3(String siblingName3) { this.siblingName3 = siblingName3; }

  @JoinColumn(name = "sibling_gender_3", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public Gender getSiblingGender3() { return siblingGender3; }
  public void setSiblingGender3(Gender siblingGender3) { this.siblingGender3 = siblingGender3; }

  @Column(name = "sibling_dob_3")
  public Date getSiblingDob3() { return siblingDob3; }
  public void setSiblingDob3(Date siblingDob3) { this.siblingDob3 = siblingDob3; }

  @Column(name = "sibling_rel_3", columnDefinition = "varchar(20)")
  public String getSiblingRel3() { return siblingRel3; }
  public void setSiblingRel3(String siblingRel3) { this.siblingRel3 = siblingRel3; }

  @Column(name = "sibling_name_4", columnDefinition = "varchar(20)")
  public String getSiblingName4() { return siblingName4; }
  public void setSiblingName4(String siblingName4) { this.siblingName4 = siblingName4; }

  @JoinColumn(name = "sibling_gender_4", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public Gender getSiblingGender4() { return siblingGender4; }
  public void setSiblingGender4(Gender siblingGender4) { this.siblingGender4 = siblingGender4; }

  @Column(name = "sibling_dob_4")
  public Date getSiblingDob4() { return siblingDob4; }
  public void setSiblingDob4(Date siblingDob4) { this.siblingDob4 = siblingDob4; }
  
  @Column(name = "sibling_rel_4", columnDefinition = "varchar(20)")
  public String getSiblingRel4() { return siblingRel4; }
  public void setSiblingRel4(String siblingRel4) { this.siblingRel4 = siblingRel4; }

  @Column(name = "sibling_name_5", columnDefinition = "varchar(20)")
  public String getSiblingName5() { return siblingName5; }
  public void setSiblingName5(String siblingName5) { this.siblingName5 = siblingName5; }

  @JoinColumn(name = "sibling_gender_5", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public Gender getSiblingGender5() { return siblingGender5; }
  public void setSiblingGender5(Gender siblingGender5) { this.siblingGender5 = siblingGender5; }

  @Column(name = "sibling_dob_5")
  public Date getSiblingDob5() { return siblingDob5; }
  public void setSiblingDob5(Date siblingDob5) { this.siblingDob5 = siblingDob5; }

  @Column(name = "sibling_rel_5", columnDefinition = "varchar(20)")
  public String getSiblingRel5() { return siblingRel5; }
  public void setSiblingRel5(String siblingRel5) { this.siblingRel5 = siblingRel5; }

  @Column(name = "sibling_name_6", columnDefinition = "varchar(20)")
  public String getSiblingName6() { return siblingName6; }
  public void setSiblingName6(String siblingName6) { this.siblingName6 = siblingName6; }
  
  @JoinColumn(name = "sibling_gender_6", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public Gender getSiblingGender6() { return siblingGender6; }
  public void setSiblingGender6(Gender siblingGender6) { this.siblingGender6 = siblingGender6; }

  @Column(name = "sibling_dob_6")
  public Date getSiblingDob6() { return siblingDob6; }
  public void setSiblingDob6(Date siblingDob6) { this.siblingDob6 = siblingDob6; }

  @Column(name = "sibling_rel_6", columnDefinition = "varchar(20)")
  public String getSiblingRel6() { return siblingRel6; }
  public void setSiblingRel6(String siblingRel6) { this.siblingRel6 = siblingRel6; }

  @Column(name = "other_persons", columnDefinition="text")
  public String getOtherPersons() { return otherPersons; }
  public void setOtherPersons(String otherPersons) { this.otherPersons = otherPersons; }

  @Column(name = "languages")
  public String getLanguages() { return languages; }
  public void setLanguages(String languages) { this.languages = languages; }

  @Column(name = "prof_1", columnDefinition = "varchar(80)")
  public String getProf1() { return prof1; }
  public void setProf1(String prof1) { this.prof1 = prof1; }

  @Column(name = "prof_age_1", columnDefinition = "varchar(4)")
  public String getProfAge1() { return profAge1; }
  public void setProfAge1(String profAge1) { this.profAge1 = profAge1; }

  @Column(name = "prof_2", columnDefinition = "varchar(80)")
  public String getProf2() { return prof2; }
  public void setProf2(String prof2) { this.prof2 = prof2; }

  @Column(name = "prof_age_2", columnDefinition = "varchar(4)")
  public String getProfAge2() { return profAge2; }
  public void setProfAge2(String profAge2) { this.profAge2 = profAge2; }
  
  @Column(name = "prof_3", columnDefinition = "varchar(80)")
  public String getProf3() { return prof3; }
  public void setProf3(String prof3) { this.prof3 = prof3; }

  @Column(name = "prof_age_3", columnDefinition = "varchar(4)")
  public String getProfAge3() { return profAge3; }
  public void setProfAge3(String profAge3) { this.profAge3 = profAge3; }

  @Column(name = "prof_4", columnDefinition = "varchar(80)")
  public String getProf4() { return prof4; }
  public void setProf4(String prof4) { this.prof4 = prof4; }

  @Column(name = "prof_age_4", columnDefinition = "varchar(4)")
  public String getProfAge4() { return profAge4; }
  public void setProfAge4(String profAge4) { this.profAge4 = profAge4; }

  @Column(name = "medical_problems")
  public Boolean getMedicalProblems() { return medicalProblems; }
  public void setMedicalProblems(Boolean medicalProblems) { this.medicalProblems = medicalProblems; }

  @Column(name = "medical_problems_duration", columnDefinition = "varchar(80)")
  public String getMedicalProblemsDuration() { return medicalProblemsDuration; }
  public void setMedicalProblemsDuration(String medicalProblemsDuration) { this.medicalProblemsDuration = medicalProblemsDuration; }

  @Column(name = "special_diet")
  public Boolean getSpecialDiet() { return specialDiet; }
  public void setSpecialDiet(Boolean specialDiet) { this.specialDiet = specialDiet; }

  @Column(name = "special_diet_duration", columnDefinition = "varchar(80)")
  public String getSpecialDietDuration() { return specialDietDuration; }
  public void setSpecialDietDuration(String specialDietDuration) { this.specialDietDuration = specialDietDuration; }

  @Column(name = "medications")
  public Boolean getMedications() { return medications; }
  public void setMedications(Boolean medications) { this.medications = medications; }

  @Column(name = "medications_duration", columnDefinition = "varchar(80)")
  public String getMedicationsDuration() { return medicationsDuration; }
  public void setMedicationsDuration(String medicationsDuration) { this.medicationsDuration = medicationsDuration; }

  @Column(name = "preg")
  public String getPreg() { return preg; }
  public void setPreg(String preg) { this.preg = preg; }

  @Column(name = "weeks_birth", columnDefinition = "varchar(8)")
  public String getWeeksBirth() { return weeksBirth; }
  public void setWeeksBirth(String weeksBirth) { this.weeksBirth = weeksBirth; }

  @Column(name = "accidents")
  public String getAccidents() { return accidents; }
  public void setAccidents(String accidents) { this.accidents = accidents; }

  @Column(name = "accidents_duration", columnDefinition = "varchar(80)")
  public String getAccidentsDuration() { return accidentsDuration; }
  public void setAccidentsDuration(String accidentsDuration) { this.accidentsDuration = accidentsDuration; }

  @Column(name = "mother_age", columnDefinition = "varchar(8)")
  public String getMotherAge() { return motherAge; } 
  public void setMotherAge(String motherAge) { this.motherAge = motherAge; }

  @Column(name = "complications")
  public Boolean getComplications() { return complications; }
  public void setComplications(Boolean complications) { this.complications = complications; }

  @Column(name = "complications_desc")
  public String getComplicationsDesc() { return complicationsDesc; }
  public void setComplicationsDesc(String complicationsDesc) { this.complicationsDesc = complicationsDesc; }

  @Column(name = "birth_weight", columnDefinition = "varchar(8)")
  public String getBirthWeight() { return birthWeight; }
  public void setBirthWeight(String birthWeight) { this.birthWeight = birthWeight; }
  
  @Column(name = "oxygen")
  public Boolean getOxygen() { return oxygen; }
  public void setOxygen(Boolean oxygen) { this.oxygen = oxygen; }

  @Column(name = "oxygen_reason")
  public String getOxygenReason() { return oxygenReason; }
  public void setOxygenReason(String oxygenReason) { this.oxygenReason = oxygenReason; }

  @Column(name = "special_care")
  public Boolean getSpecialCare() { return specialCare; }
  public void setSpecialCare(Boolean specialCare) { this.specialCare = specialCare; }

  @Column(name = "special_care_reason")
  public String getSpecialCareReason() { return specialCareReason; }
  public void setSpecialCareReason(String specialCareReason) { this.specialCareReason = specialCareReason; }

  @Column(name = "hospital_duration", columnDefinition = "varchar(80)")
  public String getHospitalDuration() { return hospitalDuration; }
  public void setHospitalDuration(String hospitalDuration) { this.hospitalDuration = hospitalDuration; }

  @Column(name = "mother_hospital_duration", columnDefinition = "varchar(80)")
  public String getMotherHospitalDuration() { return motherHospitalDuration; }
  public void setMotherHospitalDuration(String motherHospitalDuration) { this.motherHospitalDuration = motherHospitalDuration; }
  
  @Column(name = "easy_baby")
  public Boolean getEasyBaby() { return easyBaby; }
  public void setEasyBaby(Boolean easyBaby) { this.easyBaby = easyBaby; }

  @Column(name = "enjoys_people")
  public Boolean getEnjoysPeople() { return enjoysPeople; }
  public void setEnjoysPeople(Boolean enjoysPeople) { this.enjoysPeople = enjoysPeople; }

  @Column(name = "irrirable")
  public Boolean getIrritable() { return irritable; }
  public void setIrritable(Boolean irritable) { this.irritable = irritable; }

  @Column(name = "soothe")
  public Boolean getSoothe() { return soothe; }
  public void setSoothe(Boolean soothe) { this.soothe = soothe; }

  @Column(name = "sleep")
  public Boolean getSleep() { return sleep; }
  public void setSleep(Boolean sleep) { this.sleep = sleep; }

  @Column(name = "quiet")
  public Boolean getQuiet() { return quiet; }
  public void setQuiet(Boolean quiet) { this.quiet = quiet; }

  @Column(name = "sick")
  public Boolean getSick() { return sick; }
  public void setSick(Boolean sick) { this.sick = sick; }

  @Column(name = "feeding")
  public Boolean getFeeding() { return feeding; }
  public void setFeeding(Boolean feeding) { this.feeding = feeding; }

  @Column(name = "sensory")
  public Boolean getSensory() { return sensory; }
  public void setSensory(Boolean sensory) { this.sensory = sensory; }

  @Column(name = "colic")
  public Boolean getColic() { return colic; }
  public void setColic(Boolean colic) { this.colic = colic; }

  @Column(name = "illness", columnDefinition="text")
  public String getIllness() { return illness; }
  public void setIllness(String illness) { this.illness = illness; }

  @Column(name = "sat", columnDefinition = "varchar(20)")
  public String getSat() { return sat; }
  public void setSat(String sat) { this.sat = sat; }

  @Column(name = "crawled", columnDefinition = "varchar(20)")
  public String getCrawled() { return crawled; }
  public void setCrawled(String crawled) { this.crawled = crawled; }

  @Column(name = "walked", columnDefinition = "varchar(20)")
  public String getWalked() { return walked; }
  public void setWalked(String walked) { this.walked = walked; }

  @Column(name = "words", columnDefinition = "varchar(20)")
  public String getWords() { return words; }
  public void setWords(String words) { this.words = words; }

  @Column(name = "sentences", columnDefinition = "varchar(20)")
  public String getSentences() { return sentences; }
  public void setSentences(String sentences) { this.sentences = sentences; }

  @Column(name = "slept", columnDefinition = "varchar(20)")
  public String getSlept() { return slept; }
  public void setSlept(String slept) { this.slept = slept; }

  @Column(name = "daytime_wetting", columnDefinition = "varchar(20)")
  public String getDaytimeWetting() { return daytimeWetting; }
  public void setDaytimeWetting(String daytimeWetting) { this.daytimeWetting = daytimeWetting; }

  @Column(name = "bed_wetting", columnDefinition = "varchar(20)")
  public String getBedWetting() { return bedWetting; }
  public void setBedWetting(String bedWetting) { this.bedWetting = bedWetting; }

  @Column(name = "bowel_control", columnDefinition = "varchar(20)")
  public String getBowelControl() { return bowelControl; }
  public void setBowelControl(String bowelControl) { this.bowelControl = bowelControl; }

  @Column(name = "pcp_name", columnDefinition = "varchar(80)")
  public String getPcpName() { return pcpName; }
  public void setPcpName(String pcpName) { this.pcpName = pcpName; }

  @Column(name = "pcp_address")
  public String getPcpAddress() { return pcpAddress; }
  public void setPcpAddress(String pcpAddress) { this.pcpAddress = pcpAddress; }

  @Column(name = "pcp_phone", columnDefinition = "varchar(20)")
  public String getPcpPhone() { return pcpPhone; }
  public void setPcpPhone(String pcpPhone) { this.pcpPhone = pcpPhone; }

  @Column(name = "last_exam")
  public String getLastExam() { return lastExam; }
  public void setLastExam(String lastExam) { this.lastExam = lastExam; }

  @Column(name = "allergy_1", columnDefinition = "varchar(20)")
  public String getAllergy1() { return allergy1; }
  public void setAllergy1(String allergy1) { this.allergy1 = allergy1; }

  @Column(name = "allergy_level_1", columnDefinition = "varchar(20)")
  public String getAllergyLevel1() { return allergyLevel1; }
  public void setAllergyLevel1(String allergyLevel1) { this.allergyLevel1 = allergyLevel1; }

  @Column(name = "allergy_2", columnDefinition = "varchar(20)")
  public String getAllergy2() { return allergy2; }
  public void setAllergy2(String allergy2) { this.allergy2 = allergy2; }

  @Column(name = "allergy_level_2", columnDefinition = "varchar(20)")
  public String getAllergyLevel2() { return allergyLevel2; }
  public void setAllergyLevel2(String allergyLevel2) { this.allergyLevel2 = allergyLevel2; }

  @Column(name = "allergy_3", columnDefinition = "varchar(20)")
  public String getAllergy3() { return allergy3; }
  public void setAllergy3(String allergy3) { this.allergy3 = allergy3; }

  @Column(name = "allergy_level_3", columnDefinition = "varchar(20)")
  public String getAllergyLevel3() { return allergyLevel3; }
  public void setAllergyLevel3(String allergyLevel3) { this.allergyLevel3 = allergyLevel3; }

  @Column(name = "allergy_info", columnDefinition="text")
  public String getAllergyInfo() { return allergyInfo; }
  public void setAllergyInfo(String allergyInfo) { this.allergyInfo = allergyInfo; }

  @Column(name = "med_1", columnDefinition = "varchar(20)")
  public String getMed1() { return med1; }
  public void setMed1(String med1) { this.med1 = med1; }

  @Column(name = "dose_1", columnDefinition = "varchar(20)")
  public String getDose1() { return dose1; }
  public void setDose1(String dose1) { this.dose1 = dose1; }

  @Column(name = "freq_1", columnDefinition = "varchar(20)")
  public String getFreq1() { return freq1; }
  public void setFreq1(String freq1) { this.freq1 = freq1; }

  @Column(name = "prescriber_1", columnDefinition = "varchar(20)")
  public String getPrescriber1() { return prescriber1; }
  public void setPrescriber1(String prescriber1) { this.prescriber1 = prescriber1; }
  
  @Column(name = "med_2", columnDefinition = "varchar(20)")
  public String getMed2() { return med2; }
  public void setMed2(String med2) { this.med2 = med2; }

  @Column(name = "dose_2", columnDefinition = "varchar(20)")
  public String getDose2() { return dose2; }
  public void setDose2(String dose2) { this.dose2 = dose2; }

  @Column(name = "freq_2", columnDefinition = "varchar(20)")
  public String getFreq2() { return freq2; }
  public void setFreq2(String freq2) { this.freq2 = freq2; }

  @Column(name = "prescriber_2", columnDefinition = "varchar(20)")
  public String getPrescriber2() { return prescriber2; }
  public void setPrescriber2(String prescriber2) { this.prescriber2 = prescriber2; }

  @Column(name = "med_3", columnDefinition = "varchar(20)")
  public String getMed3() { return med3; }
  public void setMed3(String med3) { this.med3 = med3; }

  @Column(name = "dose_3", columnDefinition = "varchar(20)")
  public String getDose3() { return dose3; }
  public void setDose3(String dose3) { this.dose3 = dose3; }

  @Column(name = "freq_3", columnDefinition = "varchar(20)")
  public String getFreq3() { return freq3; } 
  public void setFreq3(String freq3) { this.freq3 = freq3; }

  @Column(name = "prescriber_3", columnDefinition = "varchar(20)")
  public String getPrescriber3() { return prescriber3; }
  public void setPrescriber3(String prescriber3) { this.prescriber3 = prescriber3; }

  @Column(name = "med_info", columnDefinition="text")
  public String getMedInfo() { return medInfo; }
  public void setMedInfo(String medInfo) { this.medInfo = medInfo; }

  @Column(name = "problem_1", columnDefinition = "varchar(20)")
  public String getProblem1() { return problem1; }
  public void setProblem1(String problem1) { this.problem1 = problem1; }

  @Column(name = "problem_1_desc", columnDefinition = "varchar(80)")
  public String getProblem1Desc() { return problem1Desc; }
  public void setProblem1Desc(String problem1Desc) { this.problem1Desc = problem1Desc; }

  @Column(name = "problem_2", columnDefinition = "varchar(20)")
  public String getProblem2() { return problem2; }
  public void setProblem2(String problem2) { this.problem2 = problem2; }

  @Column(name = "problem_2_desc", columnDefinition = "varchar(80)")
  public String getProblem2Desc() { return problem2Desc; }
  public void setProblem2Desc(String problem2Desc) { this.problem2Desc = problem2Desc; }

  @Column(name = "problem_3", columnDefinition = "varchar(20)")
  public String getProblem3() { return problem3; }
  public void setProblem3(String problem3) { this.problem3 = problem3; }

  @Column(name = "problem_3_desc", columnDefinition = "varchar(80)")
  public String getProblem3Desc() { return problem3Desc; }
  public void setProblem3Desc(String problem3Desc) { this.problem3Desc = problem3Desc; }

  @Column(name = "problem_4", columnDefinition = "varchar(20)")
  public String getProblem4() { return problem4; }
  public void setProblem4(String problem4) { this.problem4 = problem4; }

  @Column(name = "problem_4_desc", columnDefinition = "varchar(80)")
  public String getProblem4Desc() { return problem4Desc; }
  public void setProblem4Desc(String problem4Desc) { this.problem4Desc = problem4Desc; }
  
  @Column(name = "problem_5", columnDefinition = "varchar(20)")
  public String getProblem5() { return problem5; }
  public void setProblem5(String problem5) { this.problem5 = problem5; }

  @Column(name = "problem_5_desc", columnDefinition = "varchar(80)")
  public String getProblem5Desc() { return problem5Desc; }
  public void setProblem5Desc(String problem5Desc) { this.problem5Desc = problem5Desc; }

  @Column(name = "problem_6", columnDefinition = "varchar(20)")
  public String getProblem6() { return problem6; }
  public void setProblem6(String problem6) { this.problem6 = problem6; }

  @Column(name = "problem_6_desc", columnDefinition = "varchar(80)")
  public String getProblem6Desc() { return problem6Desc; }
  public void setProblem6Desc(String problem6Desc) { this.problem6Desc = problem6Desc; }

  @Column(name = "problem_7", columnDefinition = "varchar(20)")
  public String getProblem7() { return problem7; }
  public void setProblem7(String problem7) { this.problem7 = problem7; }

  @Column(name = "problem_7_desc", columnDefinition = "varchar(80)")
  public String getProblem7Desc() { return problem7Desc; }
  public void setProblem7Desc(String problem7Desc) { this.problem7Desc = problem7Desc; }

  @Column(name = "problem_8", columnDefinition = "varchar(20)")
  public String getProblem8() { return problem8; }
  public void setProblem8(String problem8) { this.problem8 = problem8; }

  @Column(name = "problem_8_desc", columnDefinition = "varchar(80)")
  public String getProblem8Desc() { return problem8Desc; }
  public void setProblem8Desc(String problem8Desc) { this.problem8Desc = problem8Desc; }
  
  @Column(name = "problem_9", columnDefinition = "varchar(20)")
  public String getProblem9() { return problem9; }
  public void setProblem9(String problem9) { this.problem9 = problem9; }

  @Column(name = "problem_9_desc", columnDefinition = "varchar(80)")
  public String getProblem9Desc() { return problem9Desc; }
  public void setProblem9Desc(String problem9Desc) { this.problem9Desc = problem9Desc; }

  @Column(name = "problem_10", columnDefinition = "varchar(20)")
  public String getProblem10() { return problem10; }
  public void setProblem10(String problem10) { this.problem10 = problem10; }

  @Column(name = "problem_10_desc", columnDefinition = "varchar(80)")
  public String getProblem10Desc() { return problem10Desc; }
  public void setProblem10Desc(String problem10Desc) { this.problem10Desc = problem10Desc; }

  @Column(name = "problem_11", columnDefinition = "varchar(20)")
  public String getProblem11() { return problem11; }
  public void setProblem11(String problem11) { this.problem11 = problem11; }

  @Column(name = "problem_11_desc", columnDefinition = "varchar(80)")
  public String getProblem11Desc() { return problem11Desc; }
  public void setProblem11Desc(String problem11Desc) { this.problem11Desc = problem11Desc; }

  @Column(name = "problem_12", columnDefinition = "varchar(20)")
  public String getProblem12() { return problem12; }
  public void setProblem12(String problem12) { this.problem12 = problem12; }

  @Column(name = "problem_12_desc", columnDefinition = "varchar(80)")
  public String getProblem12Desc() { return problem12Desc; }
  public void setProblem12Desc(String problem12Desc) { this.problem12Desc = problem12Desc; }

  @Column(name = "problem_13", columnDefinition = "varchar(20)")
  public String getProblem13() { return problem13; }
  public void setProblem13(String problem13) { this.problem13 = problem13; }

  @Column(name = "problem_13_desc", columnDefinition = "varchar(80)")
  public String getProblem13Desc() { return problem13Desc; }
  public void setProblem13Desc(String problem13Desc) { this.problem13Desc = problem13Desc; }

  @Column(name = "problem_14", columnDefinition = "varchar(20)")
  public String getProblem14() { return problem14; }
  public void setProblem14(String problem14) { this.problem14 = problem14; }

  @Column(name = "problem_14_desc", columnDefinition = "varchar(80)")
  public String getProblem14Desc() { return problem14Desc; }
  public void setProblem14Desc(String problem14Desc) { this.problem14Desc = problem14Desc; }

  @Column(name = "problem_15", columnDefinition = "varchar(20)")
  public String getProblem15() { return problem15; }
  public void setProblem15(String problem15) { this.problem15 = problem15; }

  @Column(name = "problem_15_desc", columnDefinition = "varchar(80)")
  public String getProblem15Desc() { return problem15Desc; }
  public void setProblem15Desc(String problem15Desc) { this.problem15Desc = problem15Desc; }

  @Column(name = "problem_16", columnDefinition = "varchar(20)")
  public String getProblem16() { return problem16; }
  public void setProblem16(String problem16) { this.problem16 = problem16; }

  @Column(name = "problem_16_desc", columnDefinition = "varchar(80)")
  public String getProblem16Desc() { return problem16Desc; }
  public void setProblem16Desc(String problem16Desc) { this.problem16Desc = problem16Desc; }

  @Column(name = "problem_17", columnDefinition = "varchar(20)")
  public String getProblem17() { return problem17; }
  public void setProblem17(String problem17) { this.problem17 = problem17; }

  @Column(name = "problem_17_desc", columnDefinition = "varchar(80)")
  public String getProblem17Desc() { return problem17Desc; }
  public void setProblem17Desc(String problem17Desc) { this.problem17Desc = problem17Desc; }

  @Column(name = "problem_18", columnDefinition = "varchar(20)")
  public String getProblem18() { return problem18; }
  public void setProblem18(String problem18) { this.problem18 = problem18; }

  @Column(name = "problem_18_desc", columnDefinition = "varchar(80)")
  public String getProblem18Desc() { return problem18Desc; }
  public void setProblem18Desc(String problem18Desc) { this.problem18Desc = problem18Desc; }

  @Column(name = "problem_19", columnDefinition = "varchar(20)")
  public String getProblem19() { return problem19; }
  public void setProblem19(String problem19) { this.problem19 = problem19; }

  @Column(name = "problem_19_desc", columnDefinition = "varchar(80)")
  public String getProblem19Desc() { return problem19Desc; }
  public void setProblem19Desc(String problem19Desc) { this.problem19Desc = problem19Desc; }

  @Column(name = "problem_20", columnDefinition = "varchar(20)")
  public String getProblem20() { return problem20; }
  public void setProblem20(String problem20) { this.problem20 = problem20; }

  @Column(name = "problem_20_desc", columnDefinition = "varchar(80)")
  public String getProblem20Desc() { return problem20Desc; }
  public void setProblem20Desc(String problem20Desc) { this.problem20Desc = problem20Desc; }

  @Column(name = "problem_21", columnDefinition = "varchar(20)")
  public String getProblem21() { return problem21; }
  public void setProblem21(String problem21) { this.problem21 = problem21; }

  @Column(name = "problem_21_desc", columnDefinition = "varchar(80)")
  public String getProblem21Desc() { return problem21Desc; }
  public void setProblem21Desc(String problem21Desc) { this.problem21Desc = problem21Desc; }

  @Column(name = "problem_22", columnDefinition = "varchar(20)")
  public String getProblem22() { return problem22; }
  public void setProblem22(String problem22) { this.problem22 = problem22; }

  @Column(name = "problem_22_desc", columnDefinition = "varchar(80)")
  public String getProblem22Desc() { return problem22Desc; }
  public void setProblem22Desc(String problem22Desc) { this.problem22Desc = problem22Desc; }

  @Column(name = "problem_23", columnDefinition = "varchar(20)")
  public String getProblem23() { return problem23; }
  public void setProblem23(String problem23) { this.problem23 = problem23; }

  @Column(name = "problem_23_desc", columnDefinition = "varchar(80)")
  public String getProblem23Desc() { return problem23Desc; }
  public void setProblem23Desc(String problem23Desc) { this.problem23Desc = problem23Desc; }

  @Column(name = "problem_24", columnDefinition = "varchar(20)")
  public String getProblem24() { return problem24; }
  public void setProblem24(String problem24) { this.problem24 = problem24; }

  @Column(name = "problem_24_desc", columnDefinition = "varchar(80)")
  public String getProblem24Desc() { return problem24Desc; }
  public void setProblem24Desc(String problem24Desc) { this.problem24Desc = problem24Desc; }

  @Column(name = "problem_25", columnDefinition = "varchar(20)")
  public String getProblem25() { return problem25; }
  public void setProblem25(String problem25) { this.problem25 = problem25; }

  @Column(name = "problem_25_desc", columnDefinition = "varchar(80)")
  public String getProblem25Desc() { return problem25Desc; }
  public void setProblem25Desc(String problem25Desc) { this.problem25Desc = problem25Desc; }

  @Column(name = "problem_26", columnDefinition = "varchar(20)")
  public String getProblem26() { return problem26; }
  public void setProblem26(String problem26) { this.problem26 = problem26; }

  @Column(name = "problem_26_desc", columnDefinition = "varchar(80)")
  public String getProblem26Desc() { return problem26Desc; }
  public void setProblem26Desc(String problem26Desc) { this.problem26Desc = problem26Desc; }

  @Column(name = "problem_27", columnDefinition = "varchar(20)")
  public String getProblem27() { return problem27; }
  public void setProblem27(String problem27) { this.problem27 = problem27; }

  @Column(name = "problem_27_desc", columnDefinition = "varchar(80)")
  public String getProblem27Desc() { return problem27Desc; }
  public void setProblem27Desc(String problem27Desc) { this.problem27Desc = problem27Desc; }

  @Column(name = "disease_1", columnDefinition = "varchar(20)")
  public String getDisease1() { return disease1; }
  public void setDisease1(String disease1) { this.disease1 = disease1; }
  
  @Column(name = "disease_1_desc", columnDefinition = "varchar(20)")
  public String getDisease1Desc() { return disease1Desc; }
  public void setDisease1Desc(String disease1Desc) { this.disease1Desc = disease1Desc; }

  @Column(name = "disease_2", columnDefinition = "varchar(20)")
  public String getDisease2() { return disease2; }
  public void setDisease2(String disease2) { this.disease2 = disease2; }

  @Column(name = "disease_2_desc", columnDefinition = "varchar(20)")
  public String getDisease2Desc() { return disease2Desc; }
  public void setDisease2Desc(String disease2Desc) { this.disease2Desc = disease2Desc; }
  
  @Column(name = "disease_3", columnDefinition = "varchar(20)")
  public String getDisease3() { return disease3; }
  public void setDisease3(String disease3) { this.disease3 = disease3; }

  @Column(name = "disease_3_desc", columnDefinition = "varchar(20)")
  public String getDisease3Desc() { return disease3Desc; }
  public void setDisease3Desc(String disease3Desc) { this.disease3Desc = disease3Desc; }

  @Column(name = "disease_4", columnDefinition = "varchar(20)")
  public String getDisease4() { return disease4; }
  public void setDisease4(String disease4) { this.disease4 = disease4; }

  @Column(name = "disease_4_desc", columnDefinition = "varchar(20)")
  public String getDisease4Desc() { return disease4Desc; }
  public void setDisease4Desc(String disease4Desc) { this.disease4Desc = disease4Desc; }
  
  @Column(name = "disease_5", columnDefinition = "varchar(20)")
  public String getDisease5() { return disease5; }
  public void setDisease5(String disease5) { this.disease5 = disease5; }

  @Column(name = "disease_5_desc", columnDefinition = "varchar(20)")
  public String getDisease5Desc() { return disease5Desc; }
  public void setDisease5Desc(String disease5Desc) { this.disease5Desc = disease5Desc; }
  
  @Column(name = "disease_6", columnDefinition = "varchar(20)")
  public String getDisease6() { return disease6; }
  public void setDisease6(String disease6) { this.disease6 = disease6; }
  
  @Column(name = "disease_6_desc", columnDefinition = "varchar(20)")
  public String getDisease6Desc() { return disease6Desc; }
  public void setDisease6Desc(String disease6Desc) { this.disease6Desc = disease6Desc; }
  
  @Column(name = "soc_1", columnDefinition = "varchar(20)")
  public Boolean getSoc1() { return soc1; }
  public void setSoc1(Boolean soc1) { this.soc1 = soc1; }
  
  @Column(name = "soc_2", columnDefinition = "varchar(20)")
  public Boolean getSoc2() { return soc2; }
  public void setSoc2(Boolean soc2) { this.soc2 = soc2; }
  
  @Column(name = "soc_3", columnDefinition = "varchar(20)")
  public Boolean getSoc3() { return soc3; }
  public void setSoc3(Boolean soc3) { this.soc3 = soc3; }

  @Column(name = "soc_4", columnDefinition = "varchar(20)")
  public Boolean getSoc4() { return soc4; }
  public void setSoc4(Boolean soc4) { this.soc4 = soc4; }

  @Column(name = "school")
  public Boolean getSchool() { return school; }
  public void setSchool(Boolean school) { this.school = school; }

  @Column(name = "school_name", columnDefinition = "varchar(80)")
  public String getSchoolName() { return schoolName; }
  public void setSchoolName(String schoolName) { this.schoolName = schoolName; }
  
  @Column(name = "school_grade", columnDefinition = "varchar(20)")
  public String getSchoolGrade() { return schoolGrade; }
  public void setSchoolGrade(String schoolGrade) { this.schoolGrade = schoolGrade; }
  
  @Column(name = "sped")
  public Boolean getSped() { return sped; } 
  public void setSped(Boolean sped) { this.sped = sped; }

  @Column(name = "sped_name", columnDefinition = "varchar(20)")
  public String getSpedName() { return spedName; }
  public void setSpedName(String spedName) { this.spedName = spedName; }

  @Column(name = "repeated", columnDefinition="text")
  public Boolean getRepeated() { return repeated; }
  public void setRepeated(Boolean repeated) { this.repeated = repeated; }

  @Column(name = "repeated_desc", columnDefinition = "varchar(80)")
  public String getRepeatedDesc() { return repeatedDesc; }
  public void setRepeatedDesc(String repeatedDesc) { this.repeatedDesc = repeatedDesc; }

  @Column(name = "dis")
  public Boolean getDis() { return dis; }
  public void setDis(Boolean dis) { this.dis = dis; }

  @Column(name = "dis_desc", columnDefinition="text")
  public String getDisDesc() { return disDesc; }
  public void setDisDesc(String disDesc) { this.disDesc = disDesc; }

  @Column(name = "bed_time", columnDefinition = "varchar(80)")
  public String getBedTime() { return bedTime; }
  public void setBedTime(String bedTime) { this.bedTime = bedTime; } 

  @Column(name = "sleep_time", columnDefinition = "varchar(80)")
  public String getSleepTime() { return sleepTime; }
  public void setSleepTime(String sleepTime) { this.sleepTime = sleepTime; }

  @Column(name = "sleep_hours", columnDefinition = "varchar(80)")
  public String getSleepHours() { return sleepHours; }
  public void setSleepHours(String sleepHours) { this.sleepHours = sleepHours; }

  @Column(name = "snore")
  public Boolean getSnore() { return snore; }
  public void setSnore(Boolean snore) { this.snore = snore; }

  @Column(name = "stressors")
  public Boolean getStressors() { return stressors; }
  public void setStressors(Boolean stressors) { this.stressors = stressors; }
  
  @Column(name = "stressors_desc", columnDefinition="text")
  public String getStressorsDesc() { return stressorsDesc; }
  public void setStressorsDesc(String stressorsDesc) { this.stressorsDesc = stressorsDesc;}  
  
  @Column(name = "family_stressors", columnDefinition="text")
  public String getFamilyStressors() { return familyStressors; }
  public void setFamilyStressors(String familyStressors) { this.familyStressors = familyStressors; }

  @Column(name = "information", columnDefinition="text")
  public String getInformation() { return information; }
  public void setInformation(String information) { this.information = information; }



  public ChildHistory() { 
    this.name = ChildHistory.NAME;
  }
  
  

}
