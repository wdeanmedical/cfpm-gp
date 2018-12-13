/*
 * WDean Medical is distributed under the
 * GNU Lesser General Public License (GNU LGPL).
 * For details see: http://www.wdeanmedical.com
 * copyright 2013-2014 WDean Medical
 */
 
package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "vital_sign")
@Inheritance(strategy = InheritanceType.JOINED)
  public class VitalSign extends ClosedEncounters  implements Serializable {

  private static final long serialVersionUID = 1342327774068897447L;

  private static final String NAME = "vital_sign";
  private static Float ZEROF = new Float(0); 
  private Float height = ZEROF;
  private Float weight = ZEROF;
  private Float bmi = ZEROF;
  private Float ofc = ZEROF;
  private Integer pulse = 0;
  private Integer respiration = 0;
  private Integer systolic = 0;
  private Integer diastolic = 0;
  private Float oximetry = ZEROF;
  private Float temperature = ZEROF;

  public VitalSign() {
    this.name = NAME;
  }
  
  @Column(name = "height", nullable=false, columnDefinition="float default 0")
  public Float getHeight() { return height; }
  public void setHeight(Float height) { this.height = height; }

  @Column(name = "weight", nullable=false, columnDefinition="float default 0")
  public Float getWeight() { return weight; }
  public void setWeight(Float weight) { this.weight = weight; }

  @Column(name = "bmi")
  public Float getBmi() { return bmi; }
  public void setBmi(Float bmi) { this.bmi = bmi; }

  @Column(name = "ofc", nullable=false, columnDefinition="float default 0")
  public Float getOfc() { return ofc; }
  public void setOfc(Float ofc) { this.ofc = ofc; }

  @Column(name = "temperature", nullable=false, columnDefinition="float default 0")
  public Float getTemperature() { return temperature; }
  public void setTemperature(Float temperature) { this.temperature = temperature; }

  @Column(name = "pulse", nullable=false, columnDefinition="int default 0")
  public Integer getPulse() { return pulse; }
  public void setPulse(Integer pulse) { this.pulse = pulse; }

  @Column(name = "respiration")
  public Integer getRespiration() { return respiration; }
  public void setRespiration(Integer respiration) { this.respiration = respiration; }

  @Column(name = "systolic", nullable=false, columnDefinition="int default 0")
  public Integer getSystolic() { return systolic; }
  public void setSystolic(Integer systolic) { this.systolic = systolic; }

  @Column(name = "diastolic", nullable=false, columnDefinition="int default 0")
  public Integer getDiastolic() { return diastolic; }
  public void setDiastolic(Integer diastolic) { this.diastolic = diastolic; }

  @Column(name = "oximetry", nullable=false, columnDefinition="float default 0")
  public Float getOximetry() { return oximetry; }
  public void setOximetry(Float oximetry) { this.oximetry = oximetry; }
}
