package com.wdeanmedical.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.wdeanmedical.dto.AppDTO;
import com.wdeanmedical.model.ValidationResult;
import com.wdeanmedical.service.AppService;

@MappedSuperclass()
public class BaseEntity {
  private Integer id;
  private Date lastAccessed;
  private Date lastUpdated;
  private Date createdDate;
  
  public static String[] PHI_FIELDS = new String[] {};

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }

  @Column(name = "last_accessed")
  public Date getLastAccessed() { return lastAccessed; }
  public void setLastAccessed(Date lastAccessed) { this.lastAccessed = lastAccessed; }

  @Column(name = "last_updated")
  public Date getLastUpdated() { return lastUpdated; }
  public void setLastUpdated(Date lastUpdated) { this.lastUpdated = lastUpdated; }

  @Column(name = "created_date")
  public Date getCreatedDate() { return createdDate; }
  public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
  
  @Transient
  public BaseEntity getBaseEntity() {
    return this;
  }
  
  @Transient
  public String getName() {
    return this.getClass().getName();
  }
  
  public ValidationResult validateBeforeUpdate(AppService service, String property, Object value, Integer id) throws Exception {
    return new ValidationResult().valid();
  }
  
  public ValidationResult validateBeforeCreate(AppService service, AppDTO dto) throws Exception {
    return new ValidationResult().valid();
  }
}
