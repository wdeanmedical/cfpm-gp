package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "youth_self_report")
@Inheritance(strategy = InheritanceType.JOINED)
public class YouthSelfReport extends WDMForm implements Serializable {
  private static final long serialVersionUID = -3644106707174126271L;
  public static final String NAME = "youth_self_report";
  public static String[] PHI_FIELDS = new String[] {};
  
  private String grade;
  private Boolean notAttending;
  private String ethnicity;
  private String work;
  private String parentWork1;
  private String parentWork2;
  private Boolean noSports;
  private String sport1;
  private String sportTime1;
  private String sportAbility1;
  private String sport2;
  private String sportTime2;
  private String sportAbility2;
  private String sport3;
  private String sportTime3;
  private String sportAbility3;
  private Boolean noHobbies;
  private String hobby1;
  private String hobbyTime1;
  private String hobbyAbility1;
  private String hobby2;
  private String hobbyTime2;
  private String hobbyAbility2;
  private String hobby3;
  private String hobbyTime3;
  private String hobbyAbility3;
  private Boolean noOrgs;
  private String org1;
  private String orgTime1;
  private String org2;
  private String orgTime2;
  private String org3;
  private String orgTime3;
  private Boolean noChores;
  private String chore1;
  private String choreAbility1;
  private String chore2;
  private String choreAbility2;
  private String chore3;
  private String choreAbility3;
  private String friendCount;
  private String friendFreq;
  private Boolean noSiblings;
  private String siblingA;
  private String siblingB;
  private String siblingC;
  private String siblingD;
  private Boolean noSchool;
  private String noSchoolReason;
  private String schoolA;
  private String schoolB;
  private String schoolC;
  private String schoolD;
  private String schoolE;
  private String schoolNameE;
  private String schoolF;
  private String schoolNameF;
  private String schoolG;
  private String schoolNameG;
  private String sped;
  private String spedDesc;
  private String concernsSchool;
  private String concerns; 
  private String best;
  private String report = 
  "{\"q1\":{\"level\":0, \"desc\":\"\"}, \"q2\":{\"level\":0, \"desc\":\"\"}, \"q3\":{\"level\":0, \"desc\":\"\"}, \"q4\":{\"level\":0, \"desc\":\"\"},\"q5\":{\"level\":0, \"desc\":\"\"}, \"q6\":{\"level\":0, \"desc\":\"\"},\"q7\":{\"level\":0, \"desc\":\"\"},\"q8\":{\"level\":0, \"desc\":\"\"},\"q9\":{\"level\":0, \"desc\":\"\"},\"q10\":{\"level\":0, \"desc\":\"\"}," +
  "\"q11\":{\"level\":0, \"desc\":\"\"}, \"q12\":{\"level\":0, \"desc\":\"\"}, \"q13\":{\"level\":0, \"desc\":\"\"}, \"q14\":{\"level\":0, \"desc\":\"\"},\"q15\":{\"level\":0, \"desc\":\"\"}, \"q16\":{\"level\":0, \"desc\":\"\"},\"q17\":{\"level\":0, \"desc\":\"\"},\"q18\":{\"level\":0, \"desc\":\"\"},\"q19\":{\"level\":0, \"desc\":\"\"},\"q20\":{\"level\":0, \"desc\":\"\"}," +
  "\"q21\":{\"level\":0, \"desc\":\"\"}, \"q22\":{\"level\":0, \"desc\":\"\"}, \"q23\":{\"level\":0, \"desc\":\"\"}, \"q24\":{\"level\":0, \"desc\":\"\"},\"q25\":{\"level\":0, \"desc\":\"\"}, \"q26\":{\"level\":0, \"desc\":\"\"},\"q27\":{\"level\":0, \"desc\":\"\"},\"q28\":{\"level\":0, \"desc\":\"\"},\"q29\":{\"level\":0, \"desc\":\"\"},\"q30\":{\"level\":0, \"desc\":\"\"}," +
  "\"q31\":{\"level\":0, \"desc\":\"\"}, \"q32\":{\"level\":0, \"desc\":\"\"}, \"q33\":{\"level\":0, \"desc\":\"\"}, \"q34\":{\"level\":0, \"desc\":\"\"},\"q35\":{\"level\":0, \"desc\":\"\"}, \"q36\":{\"level\":0, \"desc\":\"\"},\"q37\":{\"level\":0, \"desc\":\"\"},\"q38\":{\"level\":0, \"desc\":\"\"},\"q39\":{\"level\":0, \"desc\":\"\"},\"q40\":{\"level\":0, \"desc\":\"\"}," +
  "\"q41\":{\"level\":0, \"desc\":\"\"}, \"q42\":{\"level\":0, \"desc\":\"\"}, \"q43\":{\"level\":0, \"desc\":\"\"}, \"q44\":{\"level\":0, \"desc\":\"\"},\"q45\":{\"level\":0, \"desc\":\"\"}, \"q46\":{\"level\":0, \"desc\":\"\"},\"q47\":{\"level\":0, \"desc\":\"\"},\"q48\":{\"level\":0, \"desc\":\"\"},\"q49\":{\"level\":0, \"desc\":\"\"},\"q50\":{\"level\":0, \"desc\":\"\"}," +
  "\"q51\":{\"level\":0, \"desc\":\"\"}, \"q52\":{\"level\":0, \"desc\":\"\"}, \"q53\":{\"level\":0, \"desc\":\"\"}, \"q54\":{\"level\":0, \"desc\":\"\"},\"q55\":{\"level\":0, \"desc\":\"\"}, \"q56a\":{\"level\":0, \"desc\":\"\"},\"q56b\":{\"level\":0, \"desc\":\"\"},\"q56c\":{\"level\":0, \"desc\":\"\"},\"q56d\":{\"level\":0, \"desc\":\"\"},\"q56e\":{\"level\":0, \"desc\":\"\"}," +
  "\"q56f\":{\"level\":0, \"desc\":\"\"}, \"q56g\":{\"level\":0, \"desc\":\"\"}, \"q56h\":{\"level\":0, \"desc\":\"\"}, \"q57\":{\"level\":0, \"desc\":\"\"},\"q58\":{\"level\":0, \"desc\":\"\"},\"q59\":{\"level\":0, \"desc\":\"\"},\"q60\":{\"level\":0, \"desc\":\"\"},\"q61\":{\"level\":0, \"desc\":\"\"},\"q62\":{\"level\":0, \"desc\":\"\"}, \"q63\":{\"level\":0, \"desc\":\"\"}," +
  "\"q64\":{\"level\":0, \"desc\":\"\"},\"q65\":{\"level\":0, \"desc\":\"\"}, \"q66\":{\"level\":0, \"desc\":\"\"},\"q67\":{\"level\":0, \"desc\":\"\"},\"q68\":{\"level\":0, \"desc\":\"\"},\"q69\":{\"level\":0, \"desc\":\"\"},\"q70\":{\"level\":0, \"desc\":\"\"}," +
  "\"q71\":{\"level\":0, \"desc\":\"\"}, \"q72\":{\"level\":0, \"desc\":\"\"}, \"q73\":{\"level\":0, \"desc\":\"\"}, \"q74\":{\"level\":0, \"desc\":\"\"},\"q75\":{\"level\":0, \"desc\":\"\"}, \"q76\":{\"level\":0, \"desc\":\"\"},\"q77\":{\"level\":0, \"desc\":\"\"},\"q78\":{\"level\":0, \"desc\":\"\"},\"q79\":{\"level\":0, \"desc\":\"\"},\"q80\":{\"level\":0, \"desc\":\"\"}," +
  "\"q81\":{\"level\":0, \"desc\":\"\"}, \"q82\":{\"level\":0, \"desc\":\"\"}, \"q83\":{\"level\":0, \"desc\":\"\"}, \"q84\":{\"level\":0, \"desc\":\"\"},\"q85\":{\"level\":0, \"desc\":\"\"}, \"q86\":{\"level\":0, \"desc\":\"\"},\"q87\":{\"level\":0, \"desc\":\"\"},\"q88\":{\"level\":0, \"desc\":\"\"},\"q89\":{\"level\":0, \"desc\":\"\"},\"q90\":{\"level\":0, \"desc\":\"\"}," +
  "\"q91\":{\"level\":0, \"desc\":\"\"}, \"q92\":{\"level\":0, \"desc\":\"\"}, \"q93\":{\"level\":0, \"desc\":\"\"}, \"q94\":{\"level\":0, \"desc\":\"\"},\"q95\":{\"level\":0, \"desc\":\"\"}, \"q96\":{\"level\":0, \"desc\":\"\"},\"q97\":{\"level\":0, \"desc\":\"\"},\"q98\":{\"level\":0, \"desc\":\"\"},\"q99\":{\"level\":0, \"desc\":\"\"},\"q100\":{\"level\":0, \"desc\":\"\"}," +
  "\"q101\":{\"level\":0, \"desc\":\"\"}, \"q102\":{\"level\":0, \"desc\":\"\"}, \"q103\":{\"level\":0, \"desc\":\"\"}, \"q104\":{\"level\":0, \"desc\":\"\"},\"q105\":{\"level\":0, \"desc\":\"\"}, \"q106\":{\"level\":0, \"desc\":\"\"},\"q107\":{\"level\":0, \"desc\":\"\"},\"q108\":{\"level\":0, \"desc\":\"\"},\"q109\":{\"level\":0, \"desc\":\"\"},\"q110\":{\"level\":0, \"desc\":\"\"}," +
  "\"q111\":{\"level\":0, \"desc\":\"\"}, \"q112\":{\"level\":0, \"desc\":\"\"}}";
  
  
  public YouthSelfReport() { 
    this.name = YouthSelfReport.NAME;
  }


  @Column(name = "grade")
  public String getGrade() { return grade; }
  public void setGrade(String grade) { this.grade = grade; }

  @Column(name = "not_attending")
  public Boolean getNotAttending() { return notAttending; }
  public void setNotAttending(Boolean notAttending) { this.notAttending = notAttending; }

  @Column(name = "ethnicity")
  public String getEthnicity() { return ethnicity; }
  public void setEthnicity(String ethnicity) { this.ethnicity = ethnicity; }

  @Column(name = "parent_work_1")
  public String getParentWork1() { return parentWork1; }
  public void setParentWork1(String parentWork1) { this.parentWork1 = parentWork1; }

  @Column(name = "parent_work_2")
  public String getParentWork2() { return parentWork2; }
  public void setParentWork2(String parentWork2) { this.parentWork2 = parentWork2; }

  @Column(name = "no_sports")
  public Boolean getNoSports() { return noSports; }
  public void setNoSports(Boolean noSports) { this.noSports = noSports; }

  @Column(name = "sport_1")
  public String getSport1() { return sport1; }
  public void setSport1(String sport1) { this.sport1 = sport1; }

  @Column(name = "sport_time_1")
  public String getSportTime1() { return sportTime1; }
  public void setSportTime1(String sportTime1) { this.sportTime1 = sportTime1; }

  @Column(name = "sport_ability_1")
  public String getSportAbility1() { return sportAbility1; }
  public void setSportAbility1(String sportAbility1) { this.sportAbility1 = sportAbility1; }

  @Column(name = "sport_2")
  public String getSport2() { return sport2; }
  public void setSport2(String sport2) { this.sport2 = sport2; }

  @Column(name = "sport_time_2")
  public String getSportTime2() { return sportTime2; }
  public void setSportTime2(String sportTime2) { this.sportTime2 = sportTime2; }

  @Column(name = "sport_ability_2")
  public String getSportAbility2() { return sportAbility2; }
  public void setSportAbility2(String sportAbility2) { this.sportAbility2 = sportAbility2; }

  @Column(name = "sport_3")
  public String getSport3() { return sport3; }
  public void setSport3(String sport3) { this.sport3 = sport3; }

  @Column(name = "sport_time_3")
  public String getSportTime3() { return sportTime3; }
  public void setSportTime3(String sportTime3) { this.sportTime3 = sportTime3; }

  @Column(name = "sport_ability_3")
  public String getSportAbility3() { return sportAbility3; }
  public void setSportAbility3(String sportAbility3) { this.sportAbility3 = sportAbility3; }

  @Column(name = "no_hobbies")
  public Boolean getNoHobbies() { return noHobbies; }
  public void setNoHobbies(Boolean noHobbies) { this.noHobbies = noHobbies; }

  @Column(name = "hobby_1")
  public String getHobby1() { return hobby1; }
  public void setHobby1(String hobby1) { this.hobby1 = hobby1; }

  @Column(name = "hobby_time_1")
  public String getHobbyTime1() { return hobbyTime1; }
  public void setHobbyTime1(String hobbyTime1) { this.hobbyTime1 = hobbyTime1; }

  @Column(name = "hobby_ability_1")
  public String getHobbyAbility1() { return hobbyAbility1; }
  public void setHobbyAbility1(String hobbyAbility1) { this.hobbyAbility1 = hobbyAbility1; }

  @Column(name = "hobby_2")
  public String getHobby2() { return hobby2; }
  public void setHobby2(String hobby2) { this.hobby2 = hobby2; }

  @Column(name = "hobby_time_2")
  public String getHobbyTime2() { return hobbyTime2; }
  public void setHobbyTime2(String hobbyTime2) { this.hobbyTime2 = hobbyTime2; }

  @Column(name = "hobby_ability_2")
  public String getHobbyAbility2() { return hobbyAbility2; }
  public void setHobbyAbility2(String hobbyAbility2) { this.hobbyAbility2 = hobbyAbility2; }

  @Column(name = "hobby_3")
  public String getHobby3() { return hobby3; }
  public void setHobby3(String hobby3) { this.hobby3 = hobby3; }

  @Column(name = "hobby_time_3")
  public String getHobbyTime3() { return hobbyTime3; }
  public void setHobbyTime3(String hobbyTime3) { this.hobbyTime3 = hobbyTime3; }

  @Column(name = "hobby_ability_3")
  public String getHobbyAbility3() { return hobbyAbility3; }
  public void setHobbyAbility3(String hobbyAbility3) { this.hobbyAbility3 = hobbyAbility3; }

  @Column(name = "no_orgs")
  public Boolean getNoOrgs() { return noOrgs; }
  public void setNoOrgs(Boolean noOrgs) { this.noOrgs = noOrgs; }

  @Column(name = "org_1")
  public String getOrg1() { return org1; }
  public void setOrg1(String org1) { this.org1 = org1; }

  @Column(name = "org_time_1")
  public String getOrgTime1() { return orgTime1; }
  public void setOrgTime1(String orgTime1) { this.orgTime1 = orgTime1; }

  @Column(name = "org_2")
  public String getOrg2() { return org2; }
  public void setOrg2(String org2) { this.org2 = org2; }

  @Column(name = "org_time_2")
  public String getOrgTime2() { return orgTime2; }
  public void setOrgTime2(String orgTime2) { this.orgTime2 = orgTime2; }

  @Column(name = "org_3")
  public String getOrg3() { return org3; }
  public void setOrg3(String org3) { this.org3 = org3; }

  @Column(name = "org_time_3")
  public String getOrgTime3() { return orgTime3; }
  public void setOrgTime3(String orgTime3) { this.orgTime3 = orgTime3; }

  @Column(name = "no_chores")
  public Boolean getNoChores() { return noChores; }
  public void setNoChores(Boolean noChores) { this.noChores = noChores; }

  @Column(name = "chore_1")
  public String getChore1() { return chore1; }
  public void setChore1(String chore1) { this.chore1 = chore1; }

  @Column(name = "chore_ability_1")
  public String getChoreAbility1() { return choreAbility1; }
  public void setChoreAbility1(String choreAbility1) { this.choreAbility1 = choreAbility1; }

  @Column(name = "chore_2")
  public String getChore2() { return chore2; }
  public void setChore2(String chore2) { this.chore2 = chore2; }

  @Column(name = "chore_ability_2")
  public String getChoreAbility2() { return choreAbility2; }
  public void setChoreAbility2(String choreAbility2) { this.choreAbility2 = choreAbility2; }

  @Column(name = "chore_3")
  public String getChore3() { return chore3; }
  public void setChore3(String chore3) { this.chore3 = chore3; }

  @Column(name = "chore_ability_3")
  public String getChoreAbility3() { return choreAbility3; }
  public void setChoreAbility3(String choreAbility3) { this.choreAbility3 = choreAbility3; }

  @Column(name = "friend_count")
  public String getFriendCount() { return friendCount; }
  public void setFriendCount(String friendCount) { this.friendCount = friendCount; }

  @Column(name = "friend_freq")
  public String getFriendFreq() { return friendFreq; }
  public void setFriendFreq(String friendFreq) { this.friendFreq = friendFreq; }

  @Column(name = "no_siblings")
  public Boolean getNoSiblings() { return noSiblings; }
  public void setNoSiblings(Boolean noSiblings) { this.noSiblings = noSiblings; }

  @Column(name = "sibling_a")
  public String getSiblingA() { return siblingA; }
  public void setSiblingA(String siblingA) { this.siblingA = siblingA; }

  @Column(name = "sibling_b")
  public String getSiblingB() { return siblingB; }
  public void setSiblingB(String siblingB) { this.siblingB = siblingB; }

  @Column(name = "sibling_c")
  public String getSiblingC() { return siblingC; }
  public void setSiblingC(String siblingC) { this.siblingC = siblingC; }

  @Column(name = "sibling_d")
  public String getSiblingD() { return siblingD; }
  public void setSiblingD(String siblingD) { this.siblingD = siblingD; }

  @Column(name = "no_school")
  public Boolean getNoSchool() { return noSchool; }
  public void setNoSchool(Boolean noSchool) { this.noSchool = noSchool; }

  @Column(name = "no_school_reason")
  public String getNoSchoolReason() { return noSchoolReason; }
  public void setNoSchoolReason(String noSchoolReason) { this.noSchoolReason = noSchoolReason; }

  @Column(name = "school_a")
  public String getSchoolA() { return schoolA; }
  public void setSchoolA(String schoolA) { this.schoolA = schoolA; }

  @Column(name = "school_b")
  public String getSchoolB() { return schoolB; }
  public void setSchoolB(String schoolB) { this.schoolB = schoolB; }

  @Column(name = "school_c")
  public String getSchoolC() { return schoolC; }
  public void setSchoolC(String schoolC) { this.schoolC = schoolC; }

  @Column(name = "school_d")
  public String getSchoolD() { return schoolD; }
  public void setSchoolD(String schoolD) { this.schoolD = schoolD; }

  @Column(name = "school_e")
  public String getSchoolE() { return schoolE; }
  public void setSchoolE(String schoolE) { this.schoolE = schoolE; }

  @Column(name = "school_name_e")
  public String getSchoolNameE() { return schoolNameE; }
  public void setSchoolNameE(String schoolNameE) { this.schoolNameE = schoolNameE; }

  @Column(name = "school_f")
  public String getSchoolF() { return schoolF; }
  public void setSchoolF(String schoolF) { this.schoolF = schoolF; }

  @Column(name = "school_name_f")
  public String getSchoolNameF() { return schoolNameF; }
  public void setSchoolNameF(String schoolNameF) { this.schoolNameF = schoolNameF; }

  @Column(name = "school_g")
  public String getSchoolG() { return schoolG; }
  public void setSchoolG(String schoolG) { this.schoolG = schoolG; }

  @Column(name = "school_name_g")
  public String getSchoolNameG() { return schoolNameG; }
  public void setSchoolNameG(String schoolNameG) { this.schoolNameG = schoolNameG; }

  @Column(name = "sped")
  public String getSped() { return sped; }
  public void setSped(String sped) { this.sped = sped; }

  @Column(name = "sped_desc")
  public String getSpedDesc() { return spedDesc; }
  public void setSpedDesc(String spedDesc) { this.spedDesc = spedDesc; }
  
  @Column(name = "concerns", columnDefinition="text")
  public String getConcerns() { return concerns; }
  public void setConcerns(String concerns) { this.concerns = concerns; }

  @Column(name = "best", columnDefinition="text")
  public String getBest() { return best; }
  public void setBest(String best) { this.best = best; }

  @Column(name = "work")
  public String getWork() { return work; }
  public void setWork(String work) { this.work = work; }

  @Column(name = "concerns_school", columnDefinition="text")
  public String getConcernsSchool() { return concernsSchool; }
  public void setConcernsSchool(String concernsSchool) { this.concernsSchool = concernsSchool; }

  @Column(name = "report", columnDefinition="text")
  public String getReport() { return report; }
  public void setReport(String report) { this.report = report; }
}
