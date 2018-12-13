package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "target_behavior")
public class TargetBehavior extends BaseEntity implements Serializable {
  private static final long serialVersionUID = -6128989300189118563L;

  public static String[] PHI_FIELDS = new String[] {};

  Integer fbabipId;  
  String description;
  String settings;
  String freq;
  String intensity;
  String duration;
  String previousIntervention;
  String behavior;
  

  public TargetBehavior() {
  }


  @Column(name = "fbabip_id")
  public Integer getFbabipId() { return fbabipId; }
  public void setFbabipId(Integer fbabipId) { this.fbabipId = fbabipId; }

  @Column(name = "description", columnDefinition="text")
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  @Column(name = "settings", columnDefinition="text")
  public String getSettings() { return settings; }
  public void setSettings(String settings) { this.settings = settings; }

  @Column(name = "freq", columnDefinition="text")
  public String getFreq() { return freq; }
  public void setFreq(String freq) { this.freq = freq; }

  @Column(name = "intensity", columnDefinition="text")
  public String getIntensity() { return intensity; }
  public void setIntensity(String intensity) { this.intensity = intensity; } 

  @Column(name = "duration", columnDefinition="text")
  public String getDuration() { return duration; }
  public void setDuration(String duration) { this.duration = duration; }

  @Column(name = "previous_intervention", columnDefinition="text")
  public String getPreviousIntervention() { return previousIntervention; }
  public void setPreviousIntervention(String previousIntervention) { this.previousIntervention = previousIntervention; }
  

  @Column(name = "behavior", columnDefinition="text")
  public String getBehavior() { return behavior; }
  public void setBehavior(String behavior) { this.behavior = behavior; }

}