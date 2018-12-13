package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sales_lead_task_user")
public class SalesLeadTaskUser extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = 694563333592997449L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private Integer salesLeadTaskId;
  private Integer userId;

  public SalesLeadTaskUser() {
  }

  @Column(name = "sales_lead_task_id")
  public Integer getSalesLeadTaskId() { return salesLeadTaskId; }
  public void setSalesLeadTaskId(Integer salesLeadTaskId) { this.salesLeadTaskId = salesLeadTaskId; }

  @Column(name = "user_id")
  public Integer getUserId() { return userId; }
  public void setUserId(Integer userId) { this.userId = userId; }

}
