package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "race")
public class Race extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = 1434408073591785698L;
  public static String[] PHI_FIELDS = new String[] {};
  private String name;

  public Race() {
  }

  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
}
