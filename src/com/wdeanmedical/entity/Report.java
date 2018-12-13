package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "report")
public class Report extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 4625281727164584469L;
  public static String[] PHI_FIELDS = new String[] {};

  private String color;
  private String icon;
  private String name;
  private String title;
  private String description;
  private int sortOrder;
    

  public Report() {
  }
   
   
  @Column(name = "color")
  public String getColor() { return color; }
  public void setColor(String color) { this.color = color; }

  @Column(name = "icon")
  public String getIcon() { return icon; }
  public void setIcon(String icon) { this.icon = icon; }

  @Column(name = "name")
  @Basic(optional = false)  
   public String getName() { return name; }
   public void setName(String name) { this.name = name; }
    
  @Column(name = "title")
  @Basic(optional = false)  
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
   
  @Column(name = "description")
  @Basic(optional = false)  
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
   
  @Column(name = "sort_order")
  @Basic(optional = false)  
  public int getSortOrder() { return sortOrder; }
  public void setSortOrder(int sortOrder) { this.sortOrder = sortOrder; }
    
}
