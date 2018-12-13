package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "intervention_plan")
public class InterventionPlan extends BaseEntity implements Serializable {
  private static final long serialVersionUID = 8461256577967056608L;

  public static String[] PHI_FIELDS = new String[] {};

  Integer fbabipId;  
  String description;
  String skillsDefecits;
  String replacement;
  String implementedWhere;
  String antecedent;
  String reinforcements;
  String rewards;
  String maintenance;
  String goals;
  String crisis;
  

  public InterventionPlan() {
  }


  @Column(name = "fbabip_id")
  public Integer getFbabipId() { return fbabipId; }
  public void setFbabipId(Integer fbabipId) { this.fbabipId = fbabipId; }

  @Column(name = "description", columnDefinition="text")
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  @Column(name = "skillsDefecits", columnDefinition="text")
  public String getSkillsDefecits() { return skillsDefecits; }
  public void setSkillsDefecits(String skillsDefecits) { this.skillsDefecits = skillsDefecits; }

  @Column(name = "replacement", columnDefinition="text")
  public String getReplacement() { return replacement; }
  public void setReplacement(String replacement) { this.replacement = replacement; }
  
  @Column(name = "implementedWhere", columnDefinition="text")
  public String getImplementedWhere() { return implementedWhere; }
  public void setImplementedWhere(String implementedWhere) { this.implementedWhere = implementedWhere; }

  @Column(name = "antecedent", columnDefinition="text")
  public String getAntecedent() { return antecedent; }
  public void setAntecedent(String antecedent) { this.antecedent = antecedent; }

  @Column(name = "reinforcements", columnDefinition="text")
  public String getReinforcements() { return reinforcements; }
  public void setReinforcements(String reinforcements) { this.reinforcements = reinforcements; }
  
  @Column(name = "rewards", columnDefinition="text")
  public String getRewards() { return rewards; }
  public void setRewards(String rewards) { this.rewards = rewards; }

  @Column(name = "goals", columnDefinition="text")
  public String getMaintenance() { return maintenance; }
  public void setMaintenance(String maintenance) { this.maintenance = maintenance; }

  @Column(name = "crisis", columnDefinition="text")
  public String getGoals() { return goals; }
  public void setGoals(String goals) { this.goals = goals; } 


  
  
  


}