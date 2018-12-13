package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "appt_type")
public class ApptType extends BaseEntity implements Serializable {
  private static final long serialVersionUID = -6340500159038204128L;
  
  public static String[] PHI_FIELDS = new String[] {};
  private String name;
  private String code;
  private Integer sortOrder;
    

  public ApptType() {
  }
    
  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
   
  @Column(name = "code")
  public String getCode() { return code; }
  public void setCode(String code) { this.code = code; }

  @Column(name = "sort_order")
  public Integer getSortOrder() { return sortOrder; }
  public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    
}
