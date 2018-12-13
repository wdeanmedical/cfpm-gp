package com.wdeanmedical.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sales_lead_action")
public class SalesLeadAction extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = -6384627783556128056L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private Integer salesLeadId;
  private String name;
  private Date date;
  private String notes;
  private Integer userId;

  public SalesLeadAction() {
  }
  
  @Column(name = "sales_lead_id")
  public Integer getSalesLeadId() { return salesLeadId; }
  public void setSalesLeadId(Integer salesLeadId) { this.salesLeadId = salesLeadId; }

  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
  @Column(name = "date")
  public Date getDate() { return date; }
  public void setDate(Date date) { this.date = date; }

  @Column(name = "notes", columnDefinition="text")
  public String getNotes() { return notes; }
  public void setNotes(String notes) { this.notes = notes; }

  @Column(name = "user_id")
  public Integer getUserId() { return userId; }
  public void setUserId(Integer userId) { this.userId = userId; }
  
}
