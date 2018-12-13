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
import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "teacher_cbc")
@Inheritance(strategy = InheritanceType.JOINED)
public class TeacherCBC extends WDMForm implements Serializable {
  private static final long serialVersionUID = -8311428647586405462L;
  public static final String NAME = "teacher_cbc";
  public static String[] PHI_FIELDS = new String[] {};
  
    
  private String grade;
  private String ethnicity;
  private Gender teacherGender;
  private String teacherRole;
  private String teacherOtherRole;
  private String schoolName;
  private String schoolAddress;
  private String parentWork1;
  private String parentWork2;
  private String monthsKnown;
  private String knowWell;
  private String timeInClass;
  private String serviceType;
  private String referred;
  private String serviceInfo;
  private String repeated;
  private String repeatInfo;
  private String academic1;
  private String academicSubject1;
  private String academic2;
  private String academicSubject2;
  private String academic3;
  private String academicSubject3;
  private String academic4;
  private String academicSubject4;
  private String academic5;
  private String academicSubject5;
  private String academic6;
  private String academicSubject6;
  private String compare1;
  private String compare2;
  private String compare3;
  private String compare4;
  private String testName1;
  private String testSubject1;
  private Date testDate1;
  private String testGrade1;
  private String testName2;
  private String testSubject2;
  private Date testDate2;
  private String testGrade2;
  private String testName3;
  private String testSubject3;
  private Date testDate3;
  private String testGrade3;
  private String testName4;
  private String testSubject4;
  private Date testDate4;
  private String testGrade4;
  private String testName5;
  private String testSubject5;
  private Date testDate5;
  private String testGrade5;
  private String iqName1;
  private Date iqDate1;
  private String iqScore1;
  private String iqName2;
  private Date iqDate2;
  private String iqScore2;
  private String iqName3;
  private Date iqDate3;
  private String iqScore3;
  private String iqName4;
  private Date iqDate4;
  private String iqScore4;
  private String iqName5;
  private Date iqDate5;
  private String iqScore5;
  private String illness;
  private String illnessDesc;
  private String concerns; 
  private String best;
  private String comments;
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
  "\"q111\":{\"level\":0, \"desc\":\"\"}, \"q112\":{\"level\":0, \"desc\":\"\"}, \"q113\":{\"level\":0, \"desc\":\"\"}, \"q114\":{\"level\":0, \"desc\":\"\"},\"q115\":{\"level\":0, \"desc\":\"\"}, \"q116\":{\"level\":0, \"desc\":\"\"}}";
  
  
  
  public TeacherCBC() { 
    this.name = TeacherCBC.NAME;
  }



  @Column(name = "grade")
  public String getGrade() { return grade; }
  public void setGrade(String grade) { this.grade = grade; }

  @Column(name = "ethnicity")
  public String getEthnicity() { return ethnicity; }
  public void setEthnicity(String ethnicity) { this.ethnicity = ethnicity; } 
  
  @JoinColumn(name = "guardian_gender", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public Gender getTeacherGender() { return teacherGender; }
  public void setTeacherGender(Gender teacherGender) { this.teacherGender = teacherGender; }

  @Column(name = "teacher_role")
  public String getTeacherRole() { return teacherRole; }
  public void setTeacherRole(String teacherRole) { this.teacherRole = teacherRole; }

  @Column(name = "teacher_other_role")
  public String getTeacherOtherRole() { return teacherOtherRole; }
  public void setTeacherOtherRole(String teacherOtherRole) { this.teacherOtherRole = teacherOtherRole; }

  @Column(name = "school_name")
  public String getSchoolName() { return schoolName; }
  public void setSchoolName(String schoolName) { this.schoolName = schoolName; }

  @Column(name = "school_address")
  public String getSchoolAddress() { return schoolAddress; }
  public void setSchoolAddress(String schoolAddress) { this.schoolAddress = schoolAddress; }

  @Column(name = "parent_work_1")
  public String getParentWork1() { return parentWork1; }
  public void setParentWork1(String parentWork1) { this.parentWork1 = parentWork1; }

  @Column(name = "parent_work_2")
  public String getParentWork2() { return parentWork2; }
  public void setParentWork2(String parentWork2) { this.parentWork2 = parentWork2; }

  @Column(name = "months_known")
  public String getMonthsKnown() { return monthsKnown; }
  public void setMonthsKnown(String monthsKnown) { this.monthsKnown = monthsKnown; }

  @Column(name = "know_well")
  public String getKnowWell() { return knowWell; }
  public void setKnowWell(String knowWell) { this.knowWell = knowWell; }

  @Column(name = "time_in_class")
  public String getTimeInClass() { return timeInClass; }
  public void setTimeInClass(String timeInClass) { this.timeInClass = timeInClass; }

  @Column(name = "service_type")
  public String getServiceType() { return serviceType; }
  public void setServiceType(String serviceType) { this.serviceType = serviceType; }

  @Column(name = "referred")
  public String getReferred() { return referred; }
  public void setReferred(String referred) { this.referred = referred; }

  @Column(name = "service_info")
  public String getServiceInfo() { return serviceInfo; }
  public void setServiceInfo(String serviceInfo) { this.serviceInfo = serviceInfo; }

  @Column(name = "repeated")
  public String getRepeated() { return repeated; }
  public void setRepeated(String repeated) { this.repeated = repeated; }

  @Column(name = "repeat_info")
  public String getRepeatInfo() { return repeatInfo; }
  public void setRepeatInfo(String repeatInfo) { this.repeatInfo = repeatInfo; }

  @Column(name = "academic_1")
  public String getAcademic1() { return academic1; }
  public void setAcademic1(String academic1) { this.academic1 = academic1; }

  @Column(name = "academic_2")
  public String getAcademic2() { return academic2; }
  public void setAcademic2(String academic2) { this.academic2 = academic2; }

  @Column(name = "academic_3")
  public String getAcademic3() { return academic3; }
  public void setAcademic3(String academic3) { this.academic3 = academic3; }

  @Column(name = "academic_4")
  public String getAcademic4() { return academic4; }
  public void setAcademic4(String academic4) { this.academic4 = academic4; }

  @Column(name = "academin_5")
  public String getAcademic5() { return academic5; }
  public void setAcademic5(String academic5) { this.academic5 = academic5; }

  @Column(name = "academic_6")
  public String getAcademic6() { return academic6; }
  public void setAcademic6(String academic6) { this.academic6 = academic6; }

  @Column(name = "compare_1")
  public String getCompare1() { return compare1; }
  public void setCompare1(String compare1) { this.compare1 = compare1; }

  @Column(name = "compare_2")
  public String getCompare2() { return compare2; }
  public void setCompare2(String compare2) { this.compare2 = compare2; }

  @Column(name = "compare_3")
  public String getCompare3() { return compare3; }
  public void setCompare3(String compare3) { this.compare3 = compare3; }

  @Column(name = "compare_4")
  public String getCompare4() { return compare4; }
  public void setCompare4(String compare4) { this.compare4 = compare4;}

  @Column(name = "test_name_1")
  public String getTestName1() { return testName1; }
  public void setTestName1(String testName1) { this.testName1 = testName1; }

  @Column(name = "test_subject_1")
  public String getTestSubject1() { return testSubject1; }
  public void setTestSubject1(String testSubject1) { this.testSubject1 = testSubject1; }

  @Column(name = "test_date_1")
  public Date getTestDate1() { return testDate1; }
  public void setTestDate1(Date testDate1) { this.testDate1 = testDate1; }

  @Column(name = "test_grade_1")
  public String getTestGrade1() { return testGrade1; }
  public void setTestGrade1(String testGrade1) { this.testGrade1 = testGrade1; }

  @Column(name = "test_name_2")
  public String getTestName2() { return testName2; }
  public void setTestName2(String testName2) { this.testName2 = testName2; }

  @Column(name = "test_subject_2")
  public String getTestSubject2() { return testSubject2; }
  public void setTestSubject2(String testSubject2) { this.testSubject2 = testSubject2; }
  
  @Column(name = "test_date_2")
  public Date getTestDate2() { return testDate2; }
  public void setTestDate2(Date testDate2) { this.testDate2 = testDate2; }
  
  @Column(name = "test_grade_2")
  public String getTestGrade2() { return testGrade2; }
  public void setTestGrade2(String testGrade2) { this.testGrade2 = testGrade2; }
  
  @Column(name = "test_name_3")
  public String getTestName3() { return testName3; }
  public void setTestName3(String testName3) { this.testName3 = testName3; }
 
  @Column(name = "test_subject_3")
  public String getTestSubject3() { return testSubject3; }
  public void setTestSubject3(String testSubject3) { this.testSubject3 = testSubject3; }
  
  @Column(name = "test_date_3")
  public Date getTestDate3() { return testDate3; }
  public void setTestDate3(Date testDate3) { this.testDate3 = testDate3; }
  
  @Column(name = "test_grade_3")
  public String getTestGrade3() { return testGrade3; }
  public void setTestGrade3(String testGrade3) { this.testGrade3 = testGrade3; }
  
  @Column(name = "test_name_4")
  public String getTestName4() { return testName4; }
  public void setTestName4(String testName4) { this.testName4 = testName4; }

  @Column(name = "test_subject_4")
  public String getTestSubject4() { return testSubject4; }
  public void setTestSubject4(String testSubject4) { this.testSubject4 = testSubject4; }

  @Column(name = "test_date_4")
  public Date getTestDate4() { return testDate4; }
  public void setTestDate4(Date testDate4) { this.testDate4 = testDate4; }

  @Column(name = "test_grade_4")
  public String getTestGrade4() { return testGrade4; }
  public void setTestGrade4(String testGrade4) { this.testGrade4 = testGrade4; }

  @Column(name = "test_name_5")
  public String getTestName5() { return testName5; }
  public void setTestName5(String testName5) { this.testName5 = testName5; }

  @Column(name = "test_subject_5")
  public String getTestSubject5() { return testSubject5; }
  public void setTestSubject5(String testSubject5) { this.testSubject5 = testSubject5; }

  @Column(name = "test_date_5")
  public Date getTestDate5() { return testDate5; }
  public void setTestDate5(Date testDate5) { this.testDate5 = testDate5; }

  @Column(name = "test_grade_5")
  public String getTestGrade5() { return testGrade5; }
  public void setTestGrade5(String testGrade5) { this.testGrade5 = testGrade5; }

  @Column(name = "iq_name_1")
  public String getIqName1() { return iqName1; }
  public void setIqName1(String iqName1) { this.iqName1 = iqName1; }

  @Column(name = "iq_date_1")
  public Date getIqDate1() { return iqDate1; }
  public void setIqDate1(Date iqDate1) { this.iqDate1 = iqDate1; }

  @Column(name = "ig_score_1")
  public String getIqScore1() { return iqScore1; }
  public void setIqScore1(String iqScore1) { this.iqScore1 = iqScore1; }

  @Column(name = "iq_name_2")
  public String getIqName2() { return iqName2; }
  public void setIqName2(String iqName2) { this.iqName2 = iqName2; }

  @Column(name = "iq_date_2")
  public Date getIqDate2() { return iqDate2; }
  public void setIqDate2(Date iqDate2) { this.iqDate2 = iqDate2; }

  @Column(name = "iq_score_2")
  public String getIqScore2() { return iqScore2; }
  public void setIqScore2(String iqScore2) { this.iqScore2 = iqScore2; }

  @Column(name = "iq_name_3")
  public String getIqName3() { return iqName3; }
  public void setIqName3(String iqName3) { this.iqName3 = iqName3; }
  
  @Column(name = "iq_date_3")
  public Date getIqDate3() { return iqDate3; }
  public void setIqDate3(Date iqDate3) { this.iqDate3 = iqDate3; }
  
  @Column(name = "iq_score_3")
  public String getIqScore3() { return iqScore3; }
  public void setIqScore3(String iqScore3) { this.iqScore3 = iqScore3; }

  @Column(name = "i1_name_4")
  public String getIqName4() { return iqName4; }
  public void setIqName4(String iqName4) { this.iqName4 = iqName4; }

  @Column(name = "iq_date_4")
  public Date getIqDate4() { return iqDate4; }
  public void setIqDate4(Date iqDate4) { this.iqDate4 = iqDate4; }

  @Column(name = "iq_score_4")
  public String getIqScore4() { return iqScore4; }
  public void setIqScore4(String iqScore4) { this.iqScore4 = iqScore4; }

  @Column(name = "iq_name_5")
  public String getIqName5() { return iqName5; }
  public void setIqName5(String iqName5) { this.iqName5 = iqName5; }

  @Column(name = "iq_date_5")
  public Date getIqDate5() { return iqDate5; }
  public void setIqDate5(Date iqDate5) { this.iqDate5 = iqDate5; }

  @Column(name = "iq_score_5")
  public String getIqScore5() { return iqScore5; }
  public void setIqScore5(String iqScore5) { this.iqScore5 = iqScore5; }

  @Column(name = "illness")
  public String getIllness() { return illness; }
  public void setIllness(String illness) { this.illness = illness; }

  @Column(name = "illness_desc", columnDefinition="text")
  public String getIllnessDesc() { return illnessDesc; }
  public void setIllnessDesc(String illnessDesc) { this.illnessDesc = illnessDesc; }

  @Column(name = "concerns", columnDefinition="text")
  public String getConcerns() { return concerns; }
  public void setConcerns(String concerns) { this.concerns = concerns; }

  @Column(name = "best", columnDefinition="text")
  public String getBest() { return best; }
  public void setBest(String best) { this.best = best; }

  @Column(name = "comments", columnDefinition="text")
  public String getComments() { return comments; }
  public void setComments(String comments) { this.comments = comments; }

  @Column(name = "report", columnDefinition="text")
  public String getReport() { return report; }
  public void setReport(String report) { this.report = report; }

  @Column(name = "academic_subject_1", columnDefinition="text")
  public String getAcademicSubject1() { return academicSubject1; }
  public void setAcademicSubject1(String academicSubject1) { this.academicSubject1 = academicSubject1; }

  @Column(name = "academic_subject_2", columnDefinition="text")
  public String getAcademicSubject2() { return academicSubject2; }
  public void setAcademicSubject2(String academicSubject2) { this.academicSubject2 = academicSubject2; }

  @Column(name = "academic_subject_3", columnDefinition="text")
  public String getAcademicSubject3() { return academicSubject3; }
  public void setAcademicSubject3(String academicSubject3) { this.academicSubject3 = academicSubject3; }

  @Column(name = "academic_subject_4", columnDefinition="text")
  public String getAcademicSubject4() { return academicSubject4; }
  public void setAcademicSubject4(String academicSubject4) { this.academicSubject4 = academicSubject4; }

  @Column(name = "academic_subject_5", columnDefinition="text")
  public String getAcademicSubject5() { return academicSubject5; }
  public void setAcademicSubject5(String academicSubject5) { this.academicSubject5 = academicSubject5; }

  @Column(name = "academic_subject_6", columnDefinition="text")
  public String getAcademicSubject6() { return academicSubject6; }
  public void setAcademicSubject6(String academicSubject6) { this.academicSubject6 = academicSubject6; }

}