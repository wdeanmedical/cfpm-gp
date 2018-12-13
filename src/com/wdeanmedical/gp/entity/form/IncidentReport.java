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
@Table(name = "incident_report")
@Inheritance(strategy = InheritanceType.JOINED)
public class IncidentReport extends WDMForm implements Serializable {
  private static final long serialVersionUID = 5604029548046525876L;
  public static final String NAME = "incident_report";
  public static String[] PHI_FIELDS = new String[] {};
  
  private Date incidentDate;
  private String incidentTime;
  private Date reportDate;
  private String provider;
  private String programName;
  private String service;
  private String reportAuthor;
  private String eventCodes;
  private String description;
  private String persons;
  private String witnesses;
  private String factors;
  private String consequences;
  private String authorities;
  private String family;
  private String clinicianSig;
  private Date clinicianSigDate;
  private String witnessSig;
  private Date witnessSigDate;
  private String mgmtSig;
  private Date mgmtSigDate;
  private String investigation;
  private String investigationStatus;
  
  
  public IncidentReport() { 
    this.name = IncidentReport.NAME;
  }


  @Column(name = "incident_date")
  public Date getIncidentDate() { return incidentDate; }
  public void setIncidentDate(Date incidentDate) { this.incidentDate = incidentDate; }

  @Column(name = "incident_time")
  public String getIncidentTime() { return incidentTime; }
  public void setIncidentTime(String incidentTime) { this.incidentTime = incidentTime; }

  @Column(name = "report_date")
  public Date getReportDate() { return reportDate; } 
  public void setReportDate(Date reportDate) { this.reportDate = reportDate; }

  @Column(name = "provider")
  public String getProvider() { return provider; }
  public void setProvider(String provider) { this.provider = provider; }

  @Column(name = "program_name")
  public String getProgramName() { return programName; }
  public void setProgramName(String programName) { this.programName = programName; }

  @Column(name = "service")
  public String getService() { return service; }
  public void setService(String service) { this.service = service; }

  @Column(name = "report_author")
  public String getReportAuthor() { return reportAuthor; }
  public void setReportAuthor(String reportAuthor) { this.reportAuthor = reportAuthor; }

  @Column(name = "event_codes")
  public String getEventCodes() { return eventCodes; }
  public void setEventCodes(String eventCodes) { this.eventCodes = eventCodes; }

  @Column(name = "description", columnDefinition="text")
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  @Column(name = "persons", columnDefinition="text")
  public String getPersons() { return persons; }
  public void setPersons(String persons) { this.persons = persons; }

  @Column(name = "witnesses", columnDefinition="text")
  public String getWitnesses() { return witnesses; }
  public void setWitnesses(String witnesses) { this.witnesses = witnesses; }

  @Column(name = "factors", columnDefinition="text")
  public String getFactors() { return factors; }
  public void setFactors(String factors) { this.factors = factors; }

  @Column(name = "consequences", columnDefinition="text")
  public String getConsequences() { return consequences; }
  public void setConsequences(String consequences) { this.consequences = consequences; }

  @Column(name = "authorities", columnDefinition="text")
  public String getAuthorities() { return authorities; }
  public void setAuthorities(String authorities) { this.authorities = authorities; }

  @Column(name = "family", columnDefinition="text")
  public String getFamily() { return family; }
  public void setFamily(String family) { this.family = family; }

  @Column(name = "clinician_sig")
  public String getClinicianSig() { return clinicianSig; }
  public void setClinicianSig(String clinicianSig) { this.clinicianSig = clinicianSig; }

  @Column(name = "clinician_sig_date")
  public Date getClinicianSigDate() { return clinicianSigDate; }
  public void setClinicianSigDate(Date clinicianSigDate) { this.clinicianSigDate = clinicianSigDate; }

  @Column(name = "witness_sig")
  public String getWitnessSig() { return witnessSig; }
  public void setWitnessSig(String witnessSig) { this.witnessSig = witnessSig; }

  @Column(name = "witness_sig_date")
  public Date getWitnessSigDate() { return witnessSigDate; }
  public void setWitnessSigDate(Date witnessSigDate) { this.witnessSigDate = witnessSigDate; }

  @Column(name = "mgmt_sig")
  public String getMgmtSig() { return mgmtSig; }
  public void setMgmtSig(String mgmtSig) { this.mgmtSig = mgmtSig; }

  @Column(name = "mgmt_sig_date")
  public Date getMgmtSigDate() { return mgmtSigDate; }
  public void setMgmtSigDate(Date mgmtSigDate) { this.mgmtSigDate = mgmtSigDate; }

  @Column(name = "investigation")
  public String getInvestigation() { return investigation; }
  public void setInvestigation(String investigation) { this.investigation = investigation; }

  @Column(name = "investigation_status")
  public String getInvestigationStatus() { return investigationStatus; }
  public void setInvestigationStatus(String investigationStatus) { this.investigationStatus = investigationStatus; }

}
