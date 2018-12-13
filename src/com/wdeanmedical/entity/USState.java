package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "us_state")
public class USState extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 4749957909713623155L;
  public static String[] PHI_FIELDS = new String[] {};
  private String name;
  private String code;

  public USState() {
  }

  @Column(name = "name")
  @Basic(optional = false)
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
  @Column(name = "code")
  public String getCode() { return code; }
  public void setCode(String code) { this.code = code; }

}
