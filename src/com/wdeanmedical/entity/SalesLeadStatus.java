package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sales_lead_status")
public class SalesLeadStatus extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -2184505604292255918L;
  public static String[] PHI_FIELDS = new String[] {};
  public static final int UNREAD = 1;
  public static final int ACTIVE = 2;
  public static final int INACTIVE = 3;
  public static final int SALE = 4;
  
  private String name;

  public SalesLeadStatus() {
  }

  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
