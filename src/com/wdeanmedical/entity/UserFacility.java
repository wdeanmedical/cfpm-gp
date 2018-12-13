package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_facility")
public class UserFacility extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = -4179723365717330501L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private Integer userId;
  private Facility facility;

  public UserFacility() {
  }

  @Column(name = "user_id")
  public Integer getUserId() { return userId; }
  public void setUserId(Integer userId) { this.userId = userId; }

  @JoinColumn(name = "facility", referencedColumnName = "id")
  @ManyToOne(optional = false)
  public Facility getFacility() { return facility; }
  public void setFacility(Facility facility) { this.facility = facility; }

}