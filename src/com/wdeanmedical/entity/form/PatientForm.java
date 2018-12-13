package com.wdeanmedical.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "patient_form")
public class PatientForm extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = 486700533743058365L;
  
  public static final String UNASSIGNED = "unassigned";
  public static final String ASSIGNED = "assigned";
  public static final String IN_PROGRESS = "in progress";
  public static final String SUBMITTED = "submitted";
  
  public static String[] PHI_FIELDS = new String[] {};
  
  private Integer practiceFormId;
  private Integer parentPatientFormId;
  private Integer practiceFormInstanceId;
  private Integer patientId;
  private String status;
  private Boolean intake = new Boolean(false);
  private PracticeForm practiceForm;
  
  
  @Column(name = "practice_form_id")
  public Integer getPracticeFormId() { return practiceFormId; }
  public void setPracticeFormId(Integer practiceFormId) { this.practiceFormId = practiceFormId; }
  
  @Column(name = "practice_form_instance_id")
  public Integer getPracticeFormInstanceId() { return practiceFormInstanceId; }
  public void setPracticeFormInstanceId(Integer practiceFormInstanceId) { this.practiceFormInstanceId = practiceFormInstanceId; }
  
  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }
  
  @Column(name = "status")
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  
  @Column(name = "intake")
  public Boolean getIntake() { return intake; }
  public void setIntake(Boolean intake) { this.intake = intake; }
  
  @Transient
  public PracticeForm getPracticeForm() { return practiceForm; }
  public void setPracticeForm(PracticeForm practiceForm) { this.practiceForm = practiceForm; }
  
  @Column(name = "parent_patient_form_id")
  public Integer getParentPatientFormId() {
    return parentPatientFormId;
  }
  public void setParentPatientFormId(Integer parentFormId) {
    this.parentPatientFormId = parentFormId;
  }
  
}
