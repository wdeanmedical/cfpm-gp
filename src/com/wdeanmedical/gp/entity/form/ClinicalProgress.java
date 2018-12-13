package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "clinical_progress")
@Inheritance(strategy = InheritanceType.JOINED)
public class ClinicalProgress extends WDMForm implements Serializable {
  private static final long serialVersionUID = 4928764474983141478L;
  public static final String NAME = "clinical_progress";
  public static String[] PHI_FIELDS = new String[] {};
  
  private String time;
  private String contactType =  "{\"q1\":{\"level\":0}}";
  private String personsPresent = "{\"q1\":{\"level\":0}}";
  private String personsPresentDetails;
  private String functioning;
  private String issue;
  private String goal;
  private String interventions;
  private String interventionsOther;
  private String interventionDesc;
  private String goal1;
  private String goalProgress1;
  private String goalDesc1;
  private String goal2;
  private String goalProgress2;
  private String goalDesc2;
  private String plan;
  private String providerSig;
  private Date nextApptDate;
  private String clientSig;
    
  public ClinicalProgress() { 
    this.name = ClinicalProgress.NAME;
  }

  @Column(name = "time")
  public String getTime() { return time; }
  public void setTime(String time) { this.time = time; }

  @Column(name = "contact_type")
  public String getContactType() { return contactType; }
  public void setContactType(String contactType) { this.contactType = contactType; }

  @Column(name = "persons_present")
  public String getPersonsPresent() { return personsPresent; }
  public void setPersonsPresent(String personsPresent) { this.personsPresent = personsPresent; }

  @Column(name = "persons_present_details", columnDefinition="text")
  public String getPersonsPresentDetails() { return personsPresentDetails; }
  public void setPersonsPresentDetails(String personsPresentDetails) { this.personsPresentDetails = personsPresentDetails; }

  @Column(name = "functioning", columnDefinition="text")
  public String getFunctioning() { return functioning; }
  public void setFunctioning(String functioning) { this.functioning = functioning; }

  @Column(name = "issue", columnDefinition="text")
  public String getIssue() { return issue; }
  public void setIssue(String issue) { this.issue = issue; }

  @Column(name = "goal", columnDefinition="text")
  public String getGoal() { return goal; }
  public void setGoal(String goal) { this.goal = goal; }

  @Column(name = "interventions")
  public String getInterventions() { return interventions; }
  public void setInterventions(String interventions) { this.interventions = interventions; }

  @Column(name = "interventions_other")
  public String getInterventionsOther() { return interventionsOther; }
  public void setInterventionsOther(String interventionsOther) { this.interventionsOther = interventionsOther; }

  @Column(name = "intervention_desc", columnDefinition="text")
  public String getInterventionDesc() { return interventionDesc; }
  public void setInterventionDesc(String interventionDesc) { this.interventionDesc = interventionDesc; }

  @Column(name = "goal_1")
  public String getGoal1() { return goal1; }
  public void setGoal1(String goal1) { this.goal1 = goal1; }

  @Column(name = "goal_progress_1")
  public String getGoalProgress1() { return goalProgress1; }
  public void setGoalProgress1(String goalProgress1) { this.goalProgress1 = goalProgress1; }

  @Column(name = "goal_desc_1", columnDefinition="text")
  public String getGoalDesc1() { return goalDesc1; }
  public void setGoalDesc1(String goalDesc1) { this.goalDesc1 = goalDesc1; }

  @Column(name = "goal_2")
  public String getGoal2() { return goal2; }
  public void setGoal2(String goal2) { this.goal2 = goal2; }

  @Column(name = "goal_progress_2")
  public String getGoalProgress2() { return goalProgress2; }
  public void setGoalProgress2(String goalProgress2) { this.goalProgress2 = goalProgress2; }

  @Column(name = "goal_desc_2", columnDefinition="text")
  public String getGoalDesc2() { return goalDesc2; }
  public void setGoalDesc2(String goalDesc2) { this.goalDesc2 = goalDesc2; }

  @Column(name = "plan", columnDefinition="text")
  public String getPlan() { return plan; }
  public void setPlan(String plan) { this.plan = plan; }

  @Column(name = "provider_sig")
  public String getProviderSig() { return providerSig; }
  public void setProviderSig(String providerSig) { this.providerSig = providerSig; }

  @Column(name = "next_appt_date")
  public Date getNextApptDate() { return nextApptDate; }
  public void setNextApptDate(Date nextApptDate) { this.nextApptDate = nextApptDate; }

  @Column(name = "client_sig")
  public String getClientSig() { return clientSig; }
  public void setClientSig(String clientSig) { this.clientSig = clientSig; }

}
