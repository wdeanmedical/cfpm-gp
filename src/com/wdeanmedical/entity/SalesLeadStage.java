package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sales_lead_stage")
public class SalesLeadStage extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -6225339620410191038L;
  public static String[] PHI_FIELDS = new String[] {};
  public static final int NONE = 1;
  public static final int CONTACTED_CALL = 2;
  public static final int CONTACTED_EMAIL = 3;
  public static final int CONSULT = 4;
  
  private String name;

  public SalesLeadStage() {
  }

  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
