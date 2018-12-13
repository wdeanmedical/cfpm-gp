package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 5163314558413813222L;
  public static String[] PHI_FIELDS = new String[] {};
  private String name;

  public Department() {
  }


  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
