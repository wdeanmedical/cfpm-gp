package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "referral_source_type")
public class ReferralSourceType extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -1809615188907998496L;
  public static String[] PHI_FIELDS = new String[] {};
  
  public static final int PATIENT = 1;
  public static final int PHYSICIAN = 2;
  
  private String name;

  public ReferralSourceType() {
  }

  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
