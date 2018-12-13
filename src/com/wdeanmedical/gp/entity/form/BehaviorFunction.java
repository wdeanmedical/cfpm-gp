package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "behavior_function")
public class BehaviorFunction extends BaseEntity implements Serializable {
  private static final long serialVersionUID = 8070987783411345277L;

  public static String[] PHI_FIELDS = new String[] {};

  Integer fbabipId;  
  String abc;
  String antecedents;
  String consequences;
  String modeling;
  String communicate;
  String hypothesized;
  String behavior;
  

  public BehaviorFunction() {
  }


  @Column(name = "fbabip_id")
  public Integer getFbabipId() { return fbabipId; }
  public void setFbabipId(Integer fbabipId) { this.fbabipId = fbabipId; }

  @Column(name = "abc", columnDefinition="text")
  public String getAbc() { return abc; }
  public void setAbc(String abc) { this.abc = abc; }

  @Column(name = "antecedents", columnDefinition="text")
  public String getAntecedents() { return antecedents; }
  public void setAntecedents(String antecedents) { this.antecedents = antecedents; } 

  @Column(name = "consequences", columnDefinition="text")
  public String getConsequences() { return consequences; }
  public void setConsequences(String consequences) { this.consequences = consequences; }

  @Column(name = "modeling", columnDefinition="text")
  public String getModeling() { return modeling; }
  public void setModeling(String modeling) { this.modeling = modeling; }

  @Column(name = "communicate", columnDefinition="text")
  public String getCommunicate() { return communicate; }
  public void setCommunicate(String communicate) { this.communicate = communicate; }

  @Column(name = "hypothesized", columnDefinition="text")
  public String getHypothesized() { return hypothesized; } 
  public void setHypothesized(String hypothesized) { this.hypothesized = hypothesized; }

  @Column(name = "behavior", columnDefinition="text")
  public String getBehavior() { return behavior; }
  public void setBehavior(String behavior) { this.behavior = behavior; }

}