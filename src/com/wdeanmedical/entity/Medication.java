package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "medication")
public class Medication extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 7024794861680044112L;
  public static String[] PHI_FIELDS = new String[] {};
  private String name;

  public Medication() {
  }


  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
