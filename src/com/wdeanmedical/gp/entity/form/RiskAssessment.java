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
@Table(name = "risk_assessment_form")
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskAssessment extends WDMForm implements Serializable {
  private static final long serialVersionUID = 5265791586377249864L;
  public static final String NAME = "risk_assessment";

  public static String[] PHI_FIELDS = new String[] {};
  
  private Date incidentDate;
  private Date dateAssessed;
  private String clientAction;
  private String clinicalImpression;
  private String furtherAction;
  private String clinicianSig;
  private Boolean sig;
  private Date clinicianSigDate;
  
  
  public RiskAssessment() { 
    this.name = RiskAssessment.NAME;
  }
  
  @Column(name = "incident_date")
  public Date getIncidentDate() { return incidentDate; }
  public void setIncidentDate(Date incidentDate) { this.incidentDate = incidentDate; }
  
  @Column(name = "date_assessed")
  public Date getDateAssessed() { return dateAssessed; }
  public void setDateAssessed(Date dateAssessed) { this.dateAssessed = dateAssessed; }

  @Column(name = "client_action", columnDefinition="text")
  public String getClientAction() { return clientAction; }
  public void setClientAction(String clientAction) { this.clientAction = clientAction; }

  @Column(name = "clinical_impression")
  public String getClinicalImpression() { return clinicalImpression; }
  public void setClinicalImpression(String clinicalImpression) { this.clinicalImpression = clinicalImpression; }

  @Column(name = "further_action")
  public String getFurtherAction() { return furtherAction; }
  public void setFurtherAction(String furtherAction) { this.furtherAction = furtherAction; }

  @Column(name = "sig")
  public Boolean getSig() { return sig; }
  public void setSig(Boolean sig) { this.sig = sig; }
  
  @Column(name = "clinician_sig")
  public String getClinicianSig() { return clinicianSig; }
  public void setClinicianSig(String clinicianSig) { this.clinicianSig = clinicianSig; }

  @Column(name = "clinician_sig_date")
  public Date getClinicianSigDate() { return clinicianSigDate; }
  public void setClinicianSigDate(Date clinicianSigDate) { this.clinicianSigDate = clinicianSigDate; }

}
