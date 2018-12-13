package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "parent_meeting")
@Inheritance(strategy = InheritanceType.JOINED)
public class ParentMeeting extends WDMForm implements Serializable {
  private static final long serialVersionUID = 3385598274747468884L;
  public static final String NAME = "parent_meeting";
  public static String[] PHI_FIELDS = new String[] {};
  
  
  private String behavior;
  private String goals;
  private String progress;
  private String strengths;
  private String concerns;
  private String comments;
  
  public ParentMeeting() { 
    this.name = ParentMeeting.NAME; 
  }
  
  
  @Column(name = "behavior", columnDefinition="text")
  public String getBehavior() { return behavior; }
  public void setBehavior(String behavior) { this.behavior = behavior; } 
  
  @Column(name = "goals", columnDefinition="text")
  public String getGoals() { return goals; }
  public void setGoals(String goals) { this.goals = goals; }
  
  @Column(name = "progress", columnDefinition="text")
  public String getProgress() { return progress; }
  public void setProgress(String progress) { this.progress = progress; } 
  
  @Column(name = "strengths", columnDefinition="text")
  public String getStrengths() { return strengths; } 
  public void setStrengths(String strengths) { this.strengths = strengths; }
  
  @Column(name = "concerns", columnDefinition="text")
  public String getConcerns() { return concerns; }
  public void setConcerns(String concerns) { this.concerns = concerns; }
  
  @Column(name = "comments", columnDefinition="text")
  public String getComments() { return comments; }
  public void setComments(String comments) { this.comments = comments; }
  
  

}
