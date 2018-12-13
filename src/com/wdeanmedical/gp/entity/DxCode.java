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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dx_code")
public class DxCode extends BaseEntity implements Serializable {
	
  private static final long serialVersionUID = -652619755689294564L;
  
  private Integer encounterId;
  private ICD9 icd9;

  public DxCode() {
  }
  

  @Column(name = "encounter_id")
  public Integer getEncounterId() { return encounterId; }
  public void setEncounterId(Integer encounterId) { this.encounterId = encounterId; }

  @JoinColumn(name = "icd_9", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public ICD9 getIcd9() { return icd9; }
  public void setIcd9(ICD9 icd9) { this.icd9 = icd9; }
}
