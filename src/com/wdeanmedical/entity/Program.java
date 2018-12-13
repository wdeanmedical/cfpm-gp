package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "program")
public class Program extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -8469819533877517799L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private String name;
  private String code;
    

   public Program() {
   }
    
  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  @Column(name = "code")
  public String getCode() { return code; }
  public void setCode(String code) { this.code = code; }
    
}
