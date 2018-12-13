package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sales_lead_action_user")
public class SalesLeadActionUser extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = 9174560217974514983L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private Integer salesLeadActionId;
  private Integer userId;

  public SalesLeadActionUser() {
  }

  @Column(name = "sales_lead_action_id")
  public Integer getSalesLeadActionId() { return salesLeadActionId; }
  public void setSalesLeadActionId(Integer salesLeadActionId) { this.salesLeadActionId = salesLeadActionId; }

  @Column(name = "user_id")
  public Integer getUserId() { return userId; }
  public void setUserId(Integer userId) { this.userId = userId; }

}
