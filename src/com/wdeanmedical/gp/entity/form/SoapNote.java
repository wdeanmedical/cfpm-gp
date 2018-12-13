/*
 * WDean Medical is distributed under the
 * GNU Lesser General Public License (GNU LGPL).
 * For details see: http://www.wdeanmedical.com
 * copyright 2013-2014 WDean Medical
 */
 
package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "soap_note")
@Inheritance(strategy = InheritanceType.JOINED)
public class SoapNote extends ClosedEncounters implements Serializable {
  private static final long serialVersionUID = -952472298193805980L;

  private static final String NAME = "soap_note";
  
  private String subjective;
  private String objective;
  private String assessment;
  private String plan;

  public SoapNote() {
    this.name = NAME;
  }

  @Column(name = "subjective", columnDefinition="text")
  public String getSubjective() { return subjective; }
  public void setSubjective(String subjective) { this.subjective = subjective; }

  @Column(name = "objective", columnDefinition="text")
  public String getObjective() { return objective; }
  public void setObjective(String objective) { this.objective = objective; }

  @Column(name = "assessment", columnDefinition="text")
  public String getAssessment() { return assessment; }
  public void setAssessment(String assessment) { this.assessment = assessment; }

  @Column(name = "plan", columnDefinition="text")
  public String getPlan() { return plan; }
  public void setPlan(String plan) { this.plan = plan; }

}
