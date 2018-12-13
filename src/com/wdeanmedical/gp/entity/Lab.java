/*
 * WDean Medical is distributed under the
 * GNU Lesser General Public License (GNU LGPL).
 * For details see: http://www.wdeanmedical.com
 * copyright 2013-2014 WDean Medical
 */
 
package com.wdeanmedical.gp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lab")
public class Lab extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1462745762564975233L;

  private Date date;
  private Integer encounterId;
  private String hb;
  private String glucose;
  private String urineDip;

  private Integer clinicianId;

  private Integer patientId;

  public Lab() {
  }

  @Column(name = "encounter_id")
  public Integer getEncounterId() { return encounterId; }
  public void setEncounterId(Integer encounterId) { this.encounterId = encounterId; }


  @Column(name = "date")
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }

  @Column(name = "clinician_id")
  public Integer getClinicianId() { return clinicianId; }
  public void setClinicianId(Integer clinicianId) { this.clinicianId = clinicianId; }

  @Column(name = "hb")
  public String getHb() {
    return hb;
  }

  public void setHb(String hb) {
    this.hb = hb;
  }

  @Column(name = "glucose")
  public String getGlucose() {
    return glucose;
  }

  public void setGlucose(String glucose) {
    this.glucose = glucose;
  }

  @Column(name = "urine_dip")
  public String getUrineDip() {
    return urineDip;
  }

  public void setUrineDip(String urineDip) {
    this.urineDip = urineDip;
  }
}
