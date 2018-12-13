package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "credential")
public class Credential extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 7767446198830568118L;
  public static String[] PHI_FIELDS = new String[] {};
  private String name;

  public Credential() {
  }

  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
