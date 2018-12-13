package com.wdeanmedical.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "client_session")
public class ClientSession extends BaseEntity implements Serializable {
  private static final long serialVersionUID = -4156072292651636347L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private Integer clientId;
  private String clientType;
  private String ipAddress;
  private Date lastAccessTime;
  private boolean parked;
  private Patient patient;
  private String sessionId;
  private User user;
  private Guardian guardian;

  public ClientSession() {
  }

  @Column(name = "client_id")
  public Integer getClientId() { return clientId; }
  public void setClientId(Integer clientId) { this.clientId = clientId; }

  @Column(name = "ip_address")
  public String getIpAddress() { return ipAddress; }
  public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

  @JoinColumn(name = "user", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public User getUser() { return user; }
  public void setUser(User user) { this.user = user; }
  
  @JoinColumn(name = "patient", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public Patient getPatient() { return patient; }
  public void setPatient(Patient patient) { this.patient = patient; }

  @Column(name = "session_id")
  public String getSessionId() { return sessionId; }
  public void setSessionId(String sessionId) { this.sessionId = sessionId; }

  @Column(name = "last_access_time")
  @Temporal(TemporalType.TIMESTAMP)
  @Basic(optional = false)
  public Date getLastAccessTime() { return lastAccessTime; }
  public void setLastAccessTime(Date lastAccessTime) { this.lastAccessTime = lastAccessTime; }

  @Column(name = "parked")
  public boolean isParked() { return parked; }
  public void setParked(boolean parked) { this.parked = parked; }

  @Column(name = "client_type")
  public String getClientType() { return clientType; }
  public void setClientType(String clientType) { this.clientType = clientType; }

  @JoinColumn(name = "guardian", referencedColumnName = "id")
  @ManyToOne(optional = true)
  public Guardian getGuardian() { return guardian; }
  public void setGuardian(Guardian guardian) { this.guardian = guardian; }


}
