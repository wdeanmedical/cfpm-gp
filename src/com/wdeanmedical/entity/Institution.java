package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "institution")
public class Institution extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -8554785824824817938L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private String name;
  private String primaryPhone;
  private String secondaryPhone;
  private String streetAddress1;
  private String streetAddress2;
  private String city;
  private USState usState;
  private String postalCode;

  public Institution() {
  }


  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  @Column(name = "postal_code")
  public String getPostalCode() { return postalCode; }
  public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

  @Column(name = "primary_phone")
  public String getPrimaryPhone() { return primaryPhone; }
  public void setPrimaryPhone(String primaryPhone) { this.primaryPhone = primaryPhone; }

  @Column(name = "secondary_phone")
  public String getSecondaryPhone() { return secondaryPhone; }
  public void setSecondaryPhone(String secondaryPhone) { this.secondaryPhone = secondaryPhone; }

  @Column(name = "street_address1")
  public String getStreetAddress1() { return streetAddress1; }
  public void setStreetAddress1(String streetAddress1) { this.streetAddress1 = streetAddress1; }

  @Column(name = "street_address2")
  public String getStreetAddress2() { return streetAddress2; }
  public void setStreetAddress2(String streetAddress2) { this.streetAddress2 = streetAddress2; }

  @Column(name = "city")
  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }

  @JoinColumn(name = "us_state", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public USState getUsState() { return usState; }
  public void setUsState(USState usState) { this.usState = usState; }

}
