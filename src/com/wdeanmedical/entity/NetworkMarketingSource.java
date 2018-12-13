package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "network_marketing_source")
public class NetworkMarketingSource extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 317667566629951729L;
  public static String[] PHI_FIELDS = new String[] {};
  
  public static final int GYM = 1;
  public static final int OTHER_1 = 2;
  public static final int OTHER_2 = 3;
  public static final int OTHER_3 = 4;
  public static final int OTHER_4 = 5;
  
  private String name;

  public NetworkMarketingSource() {
  }

  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

}
