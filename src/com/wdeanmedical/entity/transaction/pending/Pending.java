package com.wdeanmedical.entity.transaction.pending;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.wdeanmedical.entity.BaseEntity;
import com.wdeanmedical.entity.Patient;

@MappedSuperclass()
public class Pending extends BaseEntity {

  @Column(name = "errorMessage")
  private String errorMessage;

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }


  Patient patient;

  @OneToOne(optional=false)
  @JoinColumn(name="patient")
  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  @Transient
  public Boolean hasError() {
    return StringUtils.isNotEmpty(errorMessage);
  }
}
