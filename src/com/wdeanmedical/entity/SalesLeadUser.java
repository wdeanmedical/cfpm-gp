package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sales_lead_user")
public class SalesLeadUser extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = -2338956687532548968L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private Integer salesLeadId;
  private Integer userId;

  public SalesLeadUser() {
  }

  @Column(name = "sales_lead_id")
  public Integer getSalesLeadId() { return salesLeadId; }
  public void setSalesLeadId(Integer salesLeadId) { this.salesLeadId = salesLeadId; }

  @Column(name = "user_id")
  public Integer getUserId() { return userId; }
  public void setUserId(Integer userId) { this.userId = userId; }

}
