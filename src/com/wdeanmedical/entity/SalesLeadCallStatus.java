package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sales_lead_call_status")
public class SalesLeadCallStatus extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = -3480897394451427408L;
  public static String[] PHI_FIELDS = new String[] {};
  
  public static final int FIRST_CALL = 1;
  public static final int SECOND_CALL = 2;
  public static final int CONSULT = 3;
  public static final int CLOSED_SALE = 4;
  
  private String name;

  public SalesLeadCallStatus() {
  }

  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
