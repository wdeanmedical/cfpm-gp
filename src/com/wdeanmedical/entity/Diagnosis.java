package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "diagnosis")
public class Diagnosis extends BaseEntity implements Serializable {
  private static final long serialVersionUID = -83481799760154580L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private String name;

  public Diagnosis() {
  }


  @Column(name = "name")
  @Basic(optional = false)
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
