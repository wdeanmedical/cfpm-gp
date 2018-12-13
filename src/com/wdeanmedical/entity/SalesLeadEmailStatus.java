package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sales_lead_email_status")
public class SalesLeadEmailStatus extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = -6743326992372498206L;
  public static String[] PHI_FIELDS = new String[] {};
  
  public static final int FIRST_EMAIL = 1;
  public static final int SECOND_EMAIL = 2;
  public static final int THIRD_EMAIL = 3;
  public static final int FOURTH_EMAIL = 4;
  
  private String name;

  public SalesLeadEmailStatus() {
  }

  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
