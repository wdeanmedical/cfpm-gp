package com.wdeanmedical.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sales_lead_task")
public class SalesLeadTask extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = -8685985962518903958L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private Integer salesLeadId;
  private String name;
  private String notes;
  private Date dueDate;
  private Boolean timeSpecified = false;
  private List<Integer> salesAgentIds;
  private List<String> salesAgentNames;

  public SalesLeadTask() {
  }

  @Column(name = "sales_lead_id")
  public Integer getSalesLeadId() { return salesLeadId; }
  public void setSalesLeadId(Integer salesLeadId) { this.salesLeadId = salesLeadId; }
  
  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
 
  @Column(name = "due_date")
  public Date getDueDate() { return dueDate; }
  public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
  
  @Column(name = "notes", columnDefinition="text")
  public String getNotes() { return notes; }
  public void setNotes(String notes) { this.notes = notes; }
  
  @Column(name = "time_specified")
  public Boolean getTimeSpecified() { return timeSpecified; }
  public void setTimeSpecified(Boolean timeSpecified) { this.timeSpecified = timeSpecified; }
  

  @Transient 
  public List<Integer> getSalesAgentIds() { return salesAgentIds; }
  public void setSalesAgentIds(List<Integer> salesAgentIds) { this.salesAgentIds = salesAgentIds; }

  @Transient 
  public List<String> getSalesAgentNames() { return salesAgentNames; }
  public void setSalesAgentNames(List<String> salesAgentNames) { this.salesAgentNames = salesAgentNames; }

}
