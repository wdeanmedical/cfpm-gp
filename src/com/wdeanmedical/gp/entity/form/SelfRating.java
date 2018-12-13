package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "self_rating_form")
@Inheritance(strategy = InheritanceType.JOINED)
public class SelfRating extends WDMForm implements Serializable {
  private static final long serialVersionUID = -6268886727603245290L;
  public static final String NAME = "self_rating";

  public static String[] PHI_FIELDS = new String[] {};
  
  private String rel;
  private String freq;
  private String report = "{\"1\":{\"level\":0}, \"2\":{\"level\":0}, \"3\":{\"level\":0}, \"4\":{\"level\":0}," +
  "\"5\":{\"level\":0}, \"6\":{\"level\":0},\"7\":{\"level\":0},\"8\":{\"level\":0},\"9\":{\"level\":0}," + 
  "\"10\":{\"level\":0},\"11\":{\"level\":0},\"12\":{\"level\":0},\"13\":{\"level\":0},\"14\":{\"level\":0}," +
  "\"15\":{\"level\":0},\"16\":{\"level\":0},\"17\":{\"level\":0},\"18\":{\"level\":0},\"19\":{\"level\":0}," +
  "\"20\":{\"level\":0},\"21\":{\"level\":0},\"22\":{\"level\":0},\"23\":{\"level\":0}}";

  public SelfRating() { 
    this.name = SelfRating.NAME;
  }


  @Column(name = "rel")
  public String getRel() { return rel; }
  public void setRel(String rel) { this.rel = rel; }

  @Column(name = "freq")
  public String getFreq() { return freq; }
  public void setFreq(String freq) { this.freq = freq; }

  @Column(name = "report", columnDefinition="text")
  public String getReport() { return report; }
  public void setReport(String report) { this.report = report; }

}
