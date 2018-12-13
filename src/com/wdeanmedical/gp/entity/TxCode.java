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

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "tx_code")
public class TxCode extends BaseEntity implements Serializable {
	
  private static final long serialVersionUID = 5720513734442717987L;
  
  private CPT cpt;
  private CPTModifier cptModifier;
  private Integer encounterId;

  private Integer cptModifierId;

  public TxCode() {
  }

  @Column(name = "encounter_id")
  public Integer getEncounterId() { return encounterId; }
  public void setEncounterId(Integer encounterId) { this.encounterId = encounterId; }

  @JoinColumn(name = "cpt", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public CPT getCpt() { return cpt; }
  public void setCpt(CPT cpt) { this.cpt = cpt; }

  @JoinColumn(name = "cpt_modifier", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public CPTModifier getCptModifier() { return cptModifier; }
  public void setCptModifier(CPTModifier cptModifier) { this.cptModifier = cptModifier; }

  public void setCptModifierId(Integer id) {
    this.cptModifierId = id;
  }

  public Integer getCptModifierId() {
    return cptModifierId;
  }
}
