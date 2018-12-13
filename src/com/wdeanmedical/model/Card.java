package com.wdeanmedical.model;
public class Card {
	
  private String id;
  private String type;
  private String lastFour;
  private String expiration;
  
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getLastFour() {
    return lastFour;
  }
  public void setLastFour(String lastFour) {
    this.lastFour = lastFour;
  }
  public String getExpiration() {
    return expiration;
  }
  public void setExpiration(String expiration) {
    this.expiration = expiration;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
}
