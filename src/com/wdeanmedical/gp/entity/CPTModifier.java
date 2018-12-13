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
@Table(name = "cpt_modifier")
public class CPTModifier extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 3465057457460175412L;

  private String code;
  private String shortDescription;
  private String description;

  public CPTModifier() {
  }
  
  
  @Column(name = "short_description")
  public String getShortDescription() { return shortDescription; }
  public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

  @Column(name = "description")
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  @Column(name = "code")
  public String getCode() { return code; }
  public void setCode(String code) { this.code = code; }
}
