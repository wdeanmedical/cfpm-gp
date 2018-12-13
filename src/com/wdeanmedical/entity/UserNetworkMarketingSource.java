package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_network_marketing_source")
public class UserNetworkMarketingSource extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = 3875721281855658020L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private Integer userId;
  private NetworkMarketingSource source;

  public UserNetworkMarketingSource() {
  }

  @Column(name = "user_id")
  public Integer getUserId() { return userId; }
  public void setUserId(Integer userId) { this.userId = userId; }

  @JoinColumn(name = "source", referencedColumnName = "id")
  @ManyToOne(optional = false)
  public NetworkMarketingSource getSource() { return source; }
  public void setSource(NetworkMarketingSource source) { this.source = source; }

}