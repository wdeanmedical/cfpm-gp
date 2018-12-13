package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sales_lead_age_range")
public class SalesLeadAgeRange extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -2184505604292255918L;
  public static String[] PHI_FIELDS = new String[] {};
  public static final int TWENTIES = 1;
  public static final int THIRTIES = 2;
  public static final int FORTIES = 3;
  public static final int FIFTIES = 4;
  public static final int SIXTY_PLUS = 5;
  
  private String name;

  public SalesLeadAgeRange() {
  }

  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
