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

import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "obgyn_encounter_data")
@Inheritance(strategy = InheritanceType.JOINED)
public class Obgyn extends WDMForm implements Serializable {
  private static final long serialVersionUID = -8119723615984914088L;

  private static final String NAME = "obgyn";

  private String g;
  private String p;
  private String t;
  private String a;
  private String l;
  private String pregStatus;
  private String breastfeeding;
  private String breastfeedingMonths;
  private String lastPeriod;
  private String ageFirstPeriod;
  private String papSmearStatus;
  private String birthControlStatus;
  private String birthControlType;
  private String history;
  private String historyOther;

  public Obgyn() {
    this.name = NAME;
  }
  
  @Column(name = "g")
  public String getG() {
    return g;
  }

  public void setG(String g) {
    this.g = g;
  }

  @Column(name = "p")
  public String getP() {
    return p;
  }

  public void setP(String p) {
    this.p = p;
  }

  @Column(name = "t")
  public String getT() {
    return t;
  }

  public void setT(String t) {
    this.t = t;
  }

  @Column(name = "a")
  public String getA() {
    return a;
  }

  public void setA(String a) {
    this.a = a;
  }

  @Column(name = "l")
  public String getL() {
    return l;
  }

  public void setL(String l) {
    this.l = l;
  }

  @Column(name = "preg_status")
  public String getPregStatus() {
    return pregStatus;
  }

  public void setPregStatus(String pregStatus) {
    this.pregStatus = pregStatus;
  }

  @Column(name = "breastfeeding")
  public String getBreastfeeding() {
    return breastfeeding;
  }

  public void setBreastfeeding(String breastfeeding) {
    this.breastfeeding = breastfeeding;
  }

  @Column(name = "breastfeeding_months")
  public String getBreastfeedingMonths() {
    return breastfeedingMonths;
  }

  public void setBreastfeedingMonths(String breastfeedingMonths) {
    this.breastfeedingMonths = breastfeedingMonths;
  }

  @Column(name = "last_period")
  public String getLastPeriod() {
    return lastPeriod;
  }

  public void setLastPeriod(String lastPeriod) {
    this.lastPeriod = lastPeriod;
  }

  @Column(name = "age_first_period")
  public String getAgeFirstPeriod() {
    return ageFirstPeriod;
  }

  public void setAgeFirstPeriod(String ageFirstPeriod) {
    this.ageFirstPeriod = ageFirstPeriod;
  }

  @Column(name = "pap_smear_status")
  public String getPapSmearStatus() {
    return papSmearStatus;
  }

  public void setPapSmearStatus(String papSmearStatus) {
    this.papSmearStatus = papSmearStatus;
  }

  @Column(name = "birth_control_status")
  public String getBirthControlStatus() {
    return birthControlStatus;
  }

  public void setBirthControlStatus(String birthControlStatus) {
    this.birthControlStatus = birthControlStatus;
  }

  @Column(name = "birth_control_type")
  public String getBirthControlType() {
    return birthControlType;
  }

  public void setBirthControlType(String birthControlType) {
    this.birthControlType = birthControlType;
  }

  @Column(name = "history")
  public String getHistory() {
    return history;
  }

  public void setHistory(String history) {
    this.history = history;
  }

  @Column(name = "history_other")
  public String getHistoryOther() {
    return historyOther;
  }

  public void setHistoryOther(String historyOther) {
    this.historyOther = historyOther;
  }
}
