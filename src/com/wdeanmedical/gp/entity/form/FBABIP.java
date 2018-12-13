package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "fba_bip")
@Inheritance(strategy = InheritanceType.JOINED)
public class FBABIP extends WDMForm implements Serializable {
  private static final long serialVersionUID = -5816424850028159166L;
  public static final String NAME = "fba_bip";
  public static String[] PHI_FIELDS = new String[] {};
  
  private String clinicianName;
  private String background;
  private String impact;
  private String behaviorist;
  private String sources;
  private String methods;
  private String rewards;
  private String interventionPlan;
  private Date interventionDate;
  private String behavioristSig;
  private Date behavioristSigDate;
  private String bcbaSig;
  private Date bcbaSigDate;
  private String guardianSig;
  private Date guardianSigDate;
  
  private List<BehaviorFunction> behaviorFunctions = new ArrayList<BehaviorFunction>();
  private List<InterventionPlan> interventionPlans = new ArrayList<InterventionPlan>();
  private List<ProblemAnalysis> problemAnalyses = new ArrayList<ProblemAnalysis>();
  private List<TargetBehavior> targetBehaviors = new ArrayList<TargetBehavior>();
  
  public FBABIP() { 
    this.name = FBABIP.NAME;
  }


  @Column(name = "clinician_name")
  public String getClinicianName() { return clinicianName; }
  public void setClinicianName(String clinicianName) { this.clinicianName = clinicianName; }

  @Column(name = "background", columnDefinition="text")
  public String getBackground() { return background; }
  public void setBackground(String background) { this.background = background; }

  @Column(name = "impact", columnDefinition="text")
  public String getImpact() { return impact; }
  public void setImpact(String impact) { this.impact = impact; }

  @Column(name = "behaviorist")
  public String getBehaviorist() { return behaviorist; }
  public void setBehaviorist(String behaviorist) { this.behaviorist = behaviorist; }

  @Column(name = "sources")
  public String getSources() { return sources; }
  public void setSources(String sources) { this.sources = sources; }

  @Column(name = "methods")
  public String getMethods() { return methods; }
  public void setMethods(String methods) { this.methods = methods; }

  @Column(name = "rewards", columnDefinition="text")
  public String getRewards() { return rewards; }
  public void setRewards(String rewards) { this.rewards = rewards; }

  @Column(name = "intervention_plan", columnDefinition="text")
  public String getInterventionPlan() { return interventionPlan; }
  public void setInterventionPlan(String interventionPlan) { this.interventionPlan = interventionPlan; }

  @Column(name = "intervention_date")
  public Date getInterventionDate() { return interventionDate; }
  public void setInterventionDate(Date interventionDate) { this.interventionDate = interventionDate; }

  @Column(name = "behaviorist_sig")
  public String getBehavioristSig() { return behavioristSig; }
  public void setBehavioristSig(String behavioristSig) { this.behavioristSig = behavioristSig; }

  @Column(name = "behaviorist_sig_date")
  public Date getBehavioristSigDate() { return behavioristSigDate; }
  public void setBehavioristSigDate(Date behavioristSigDate) { this.behavioristSigDate = behavioristSigDate; }

  @Column(name = "bcba_sig")
  public String getBcbaSig() { return bcbaSig; }
  public void setBcbaSig(String bcbaSig) { this.bcbaSig = bcbaSig; }

  @Column(name = "bcba_sig_date")
  public Date getBcbaSigDate() { return bcbaSigDate; }
  public void setBcbaSigDate(Date bcbaSigDate) { this.bcbaSigDate = bcbaSigDate; }

  @Column(name = "guardian_sig")
  public String getGuardianSig() { return guardianSig; }
  public void setGuardianSig(String guardianSig) { this.guardianSig = guardianSig; }

  @Column(name = "guardian_sig_date")
  public Date getGuardianSigDate() { return guardianSigDate; }
  public void setGuardianSigDate(Date guardianSigDate) { this.guardianSigDate = guardianSigDate; }

  @Transient
  public List<BehaviorFunction> getBehaviorFunctions() { return behaviorFunctions; }
  public void setBehaviorFunctions(List<BehaviorFunction> behaviorFunctions) { this.behaviorFunctions = behaviorFunctions; }

  @Transient
  public List<InterventionPlan> getInterventionPlans() { return interventionPlans; }
  public void setInterventionPlans(List<InterventionPlan> interventionPlans) { this.interventionPlans = interventionPlans; }

  @Transient
  public List<ProblemAnalysis> getProblemAnalyses() { return problemAnalyses; }
  public void setProblemAnalyses(List<ProblemAnalysis> problemAnalyses) { this.problemAnalyses = problemAnalyses; }

  @Transient
  public List<TargetBehavior> getTargetBehaviors() { return targetBehaviors; }
  public void setTargetBehaviors(List<TargetBehavior> targetBehaviors) { this.targetBehaviors = targetBehaviors; }

}
