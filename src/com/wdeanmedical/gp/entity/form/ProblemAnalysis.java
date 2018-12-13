package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "problem_analysis")
public class ProblemAnalysis extends BaseEntity implements Serializable {
  private static final long serialVersionUID = 2822622576927245090L;

  public static String[] PHI_FIELDS = new String[] {};

  Integer fbabipId;  
  String reactivity;
  String distortion;
  String constitutional;
  String modeling;
  

  public ProblemAnalysis() {
  }


  @Column(name = "fbabip_id")
  public Integer getFbabipId() { return fbabipId; }
  public void setFbabipId(Integer fbabipId) { this.fbabipId = fbabipId; }

  @Column(name = "reactivity", columnDefinition="text")
  public String getReactivity() { return reactivity; } 
  public void setReactivity(String reactivity) { this.reactivity = reactivity; }

  @Column(name = "distortion", columnDefinition="text")
  public String getDistortion() { return distortion; }
  public void setDistortion(String distortion) { this.distortion = distortion; } 

  @Column(name = "constitutional", columnDefinition="text")
  public String getConstitutional() { return constitutional; }
  public void setConstitutional(String constitutional) { this.constitutional = constitutional; }

  @Column(name = "modeling", columnDefinition="text")
  public String getModeling() { return modeling; }
  public void setModeling(String modeling) { this.modeling = modeling; }

}