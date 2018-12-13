package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sales_lead_source")
public class SalesLeadSource extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -6225339620410191038L;
  public static String[] PHI_FIELDS = new String[] {};
  public static final int CALL_IN = 1;
  public static final int EMAIL_INQUIRY = 2;
  public static final int NETWORK_MARKETING = 3;
  public static final int REFERRED_BY = 4;
  public static final int WALK_IN = 5;
  public static final int WEBSITE_REGISTRATION = 6;
  
  private String name;

  public SalesLeadSource() {
  }

  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
