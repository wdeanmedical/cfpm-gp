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
@Table(name = "chief_complaint")
@Inheritance(strategy = InheritanceType.JOINED)
public class ChiefComplaint extends ClosedEncounters implements Serializable {

  private static final long serialVersionUID = 1462745762564975233L;

  private static final String NAME = "chief_complaint";

  private String description;
  private String occursWhen;
  private String occursOther;
  private String specificLocation;
  private Integer hoursSince;
  private Integer daysSince;
  private Integer weeksSince;
  private Integer monthsSince;
  private Integer yearsSince;
  private String howLongOther;
  private Integer painXHour;
  private Integer painXDay;
  private Integer painXWeek;
  private Integer painXMonth;
  private String painDuration;
  private Integer painScale;
  private String painType;
  private String denies;
  private String deniesOther;

  public ChiefComplaint() {
    this.name = NAME;
  }

  @Column(name = "description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "occurs_when")
  public String getOccursWhen() {
    return occursWhen;
  }

  public void setOccursWhen(String occursWhen) {
    this.occursWhen = occursWhen;
  }

  @Column(name = "specific_location")
  public String getSpecificLocation() {
    return specificLocation;
  }

  public void setSpecificLocation(String specificLocation) {
    this.specificLocation = specificLocation;
  }

  @Column(name = "hours_since")
  public Integer getHoursSince() {
    return hoursSince;
  }

  public void setHoursSince(Integer hoursSince) {
    this.hoursSince = hoursSince;
  }

  @Column(name = "days_since")
  public Integer getDaysSince() {
    return daysSince;
  }

  public void setDaysSince(Integer daysSince) {
    this.daysSince = daysSince;
  }

  @Column(name = "weeks_since")
  public Integer getWeeksSince() {
    return weeksSince;
  }

  public void setWeeksSince(Integer weeksSince) {
    this.weeksSince = weeksSince;
  }

  @Column(name = "months_since")
  public Integer getMonthsSince() {
    return monthsSince;
  }

  public void setMonthsSince(Integer monthsSince) {
    this.monthsSince = monthsSince;
  }

  @Column(name = "years_since")
  public Integer getYearsSince() {
    return yearsSince;
  }

  public void setYearsSince(Integer yearsSince) {
    this.yearsSince = yearsSince;
  }

  @Column(name = "how_long_other")
  public String getHowLongOther() {
    return howLongOther;
  }

  public void setHowLongOther(String howLongOther) {
    this.howLongOther = howLongOther;
  }

  @Column(name = "pain_x_hour")
  public Integer getPainXHour() {
    return painXHour;
  }

  public void setPainXHour(Integer painXHour) {
    this.painXHour = painXHour;
  }

  @Column(name = "pain_x_day")
  public Integer getPainXDay() {
    return painXDay;
  }

  public void setPainXDay(Integer painXDay) {
    this.painXDay = painXDay;
  }

  @Column(name = "pain_x_week")
  public Integer getPainXWeek() {
    return painXWeek;
  }

  public void setPainXWeek(Integer painXWeek) {
    this.painXWeek = painXWeek;
  }

  @Column(name = "pain_x_month")
  public Integer getPainXMonth() {
    return painXMonth;
  }

  public void setPainXMonth(Integer painXMonth) {
    this.painXMonth = painXMonth;
  }

  @Column(name = "pain_duration")
  public String getPainDuration() {
    return painDuration;
  }

  public void setPainDuration(String painDuration) {
    this.painDuration = painDuration;
  }

  @Column(name = "pain_scale")
  public Integer getPainScale() {
    return painScale;
  }

  public void setPainScale(Integer painScale) {
    this.painScale = painScale;
  }

  @Column(name = "pain_type")
  public String getPainType() {
    return painType;
  }

  public void setPainType(String painType) {
    this.painType = painType;
  }

  @Column(name = "denies")
  public String getDenies() {
    return denies;
  }

  public void setDenies(String denies) {
    this.denies = denies;
  }

  @Column(name = "denies_other")
  public String getDeniesOther() {
    return deniesOther;
  }

  public void setDeniesOther(String deniesOther) {
    this.deniesOther = deniesOther;
  }

  @Column(name = "occurs_other")
  public String getOccursOther() {
    return occursOther;
  }

  public void setOccursOther(String occursOther) {
    this.occursOther = occursOther;
  }
}
