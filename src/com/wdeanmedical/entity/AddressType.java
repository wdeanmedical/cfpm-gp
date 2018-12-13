package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address_type")
public class AddressType extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = -3742327939402268463L;
  public static String[] PHI_FIELDS = new String[] {};
  
  public static final int HOME = 1;
  public static final int OFFICE = 2;
  public static final int BILLING = 3;
  
  private String name;

  public AddressType() {
  }

  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
