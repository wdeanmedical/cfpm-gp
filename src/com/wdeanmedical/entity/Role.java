package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -8100416106575008609L;
  public static String[] PHI_FIELDS = new String[] {};

  private String name;

  public Role() {
  }

  @Column(name = "name")
  @Basic(optional = false)
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
