package com.wdeanmedical.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "complaint_slider")
public class ComplaintSlider extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = -790803638888477921L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private String min;
  private String max;
  private Integer complaintId;

  
  public ComplaintSlider() {
  }


  @Column(name = "min_val", columnDefinition = "varchar(80)")
  public String getMin() { return min; }
  public void setMin(String min) { this.min = min; }

  @Column(name = "max_val", columnDefinition = "varchar(80)")
  public String getMax() { return max; }
  public void setMax(String max) { this.max = max; }

  @Column(name = "complaint_id")
  public Integer getComplaintId() { return complaintId; }
  public void setComplaintId(Integer complaintId) { this.complaintId = complaintId; }

}
