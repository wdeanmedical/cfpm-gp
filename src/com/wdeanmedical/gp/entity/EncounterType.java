/*
 * WDean Medical is distributed under the
 * GNU Lesser General Public License (GNU LGPL).
 * For details see: http://www.wdeanmedical.com
 * copyright 2013-2014 WDean Medical
 */
 
package com.wdeanmedical.gp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "encounter_type")
public class EncounterType extends BaseEntity implements Serializable {
  private static final long serialVersionUID = -3127553778387743952L;

  public static final Integer CHECK_UP = 1;
  public static final Integer FOLLOW_UP = 2;
  public static final Integer URGENT_CARE = 3;
  public static final Integer IMMUNIZATION = 4;

  private String name;
  
  public EncounterType() {
  }

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
