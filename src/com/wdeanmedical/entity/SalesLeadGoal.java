package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sales_lead_goal")
public class SalesLeadGoal extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = 396186552502597969L;
  public static String[] PHI_FIELDS = new String[] {};
  
  public static final int BODY_COMPOSITION = 1;
  public static final int WORK_PRODUCTIVITY = 2;
  public static final int SEXUAL_PERFORMANCE = 3;
  
  private String name;

  public SalesLeadGoal() {
  }

  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
