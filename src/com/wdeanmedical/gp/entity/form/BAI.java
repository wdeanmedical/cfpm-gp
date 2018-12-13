package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "bai_form")
@Inheritance(strategy = InheritanceType.JOINED)
public class BAI extends WDMForm implements Serializable {
  private static final long serialVersionUID = -3125429409083593052L;
  
  public static final String NAME = "bai";
  public static String[] PHI_FIELDS = new String[] {};
  
  
  private String report = "{\"q1\":{\"level\":0}, \"q2\":{\"level\":0}, \"q3\":{\"level\":0}, \"q4\":{\"level\":0}," +
  "\"q5\":{\"level\":0}, \"q6\":{\"level\":0},\"q7\":{\"level\":0},\"q8\":{\"level\":0},\"q9\":{\"level\":0}," + 
  "\"q10\":{\"level\":0},\"q11\":{\"level\":0},\"q12\":{\"level\":0},\"q13\":{\"level\":0},\"q14\":{\"level\":0}," +
  "\"q15\":{\"level\":0},\"q16\":{\"level\":0},\"q17\":{\"level\":0},\"q18\":{\"level\":0},\"q19\":{\"level\":0}," +
  "\"q20\":{\"level\":0},\"q21\":{\"level\":0}}";
  
  
  public BAI() { 
    this.name = BAI.NAME;
  }
  
  

  @Column(name = "report", columnDefinition="text")
  public String getReport() { return report; }
  public void setReport(String report) { this.report = report; }

}
