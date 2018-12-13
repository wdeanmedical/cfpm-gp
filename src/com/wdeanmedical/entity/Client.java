package com.wdeanmedical.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.wdeanmedical.interfaces.IPassword;
import com.wdeanmedical.util.Util;

@MappedSuperclass()
public class Client extends BaseEntity implements IPassword {

  public static String[] PHI_FIELDS = new String[] {};
  
  public static final String PATIENT = "patient";
  public static final String USER = "user";
  public static final String GUARDIAN = "guardian";

  public static final int PATIENT_ACCESS_LEVEL = 1;
  
  public static final Integer STATUS_AUTHORIZED = 1;
  
  public static final int RECOVERY_ACTIVATION = 2;

  public static final Integer STATUS_NOT_FOUND = 0;
  public static final Integer STATUS_INVALID_PASSWORD = -1;
  public static final Integer STATUS_INACTIVE = -2;
  public static final Integer STATUS_PASSWORD_ALREADY_CREATED = -3;
  public static final Integer STATUS_RECOVERY_CODE_EXPIRED = -4;  
  public static final Integer STATUS_RECOVERY_CODE_ALREADY_USED = -5;
  public static final Integer STATUS_ACTIVATION_CODE_EXPIRED = -6;
  public static final Integer STATUS_ACTIVATION_CODE_ALREADY_USED = -7;

  public static final int ACTIVE   =  1;
  public static final int INACTIVE =  2;
  public static final int DECEASED = 3;
  public static final int DELETED = 4;
  public static final int WAIT_LIST = 5;

  private Integer authStatus;
  private String clientType;
  private String email;
  private String fax;
  private String firstName;
  private Date lastLoginTime;
  private String lastName;
  private String middleName;
  private String pager;
  private String password;
  private Boolean passwordCreated;
  private String previousLoginTime;
  private String primaryPhone;
  private String salt;
  private String secondaryPhone;
  private String username;
  private String sessionId;
  private Integer status = ACTIVE;
  private RecoveryCode recoveryCode;
  
  @Transient
  public Integer getAuthStatus() { return authStatus; }
  public void setAuthStatus(Integer authStatus) { this.authStatus = authStatus; }
  
  @Column(name = "client_type")
  public String getClientType() { return clientType; }
  public void setClientType(String clientType) { this.clientType = clientType; }
  
  @Column(name = "email")
  public String getEmail() { 
    if (email == null) {
      return "";
    }
    else {
      return email; 
    }  
  }
  public void setEmail(String email) { this.email = email; }
  
  @Transient
  public String getFullName() {
    return Util.buildFullName(getFirstName(), getMiddleName(), getLastName());
  }
  
  @Column(name = "fax")
  public String getFax() { return fax; }
  public void setFax(String fax) { this.fax = fax; }
  
  @Column(name = "first_name")
  @Basic(optional = false)
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  
  @Column(name = "last_login_time")
  public Date getLastLoginTime() { return lastLoginTime; }
  public void setLastLoginTime(Date lastLoginTime) { this.lastLoginTime = lastLoginTime; }
  
  @Column(name = "last_name")
  @Basic(optional = false)
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  
  @Column(name = "middle_name")
  public String getMiddleName() { return middleName; }
  public void setMiddleName(String middleName) { this.middleName = middleName; }
  
  @Column(name = "pager")
  public String getPager() { return pager; }
  public void setPager(String pager) { this.pager = pager; }

  @Column(name = "password")
  @Basic(optional = false)
  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
  
  @Column(name = "password_created", columnDefinition = "tinyint default false")
  public Boolean getPasswordCreated() { return passwordCreated; }
  public void setPasswordCreated(Boolean passwordCreated) { this.passwordCreated = passwordCreated; }
  
  @OneToOne()
  @JoinColumn(name = "recovery_code")
  @Cascade({CascadeType.SAVE_UPDATE})
  public RecoveryCode getRecoveryCode() { return recoveryCode; }
  public void setRecoveryCode(RecoveryCode recoveryCode) { this.recoveryCode = recoveryCode; }
  
  @Transient
  public String getPreviousLoginTime() { return previousLoginTime; }
  public void setPreviousLoginTime(String previousLoginTime) { this.previousLoginTime = previousLoginTime; }
  
  @Column(name = "primary_phone")
  public String getPrimaryPhone() { return primaryPhone; }
  public void setPrimaryPhone(String primaryPhone) { this.primaryPhone = primaryPhone; }
  
  @Column(name = "salt")
  public String getSalt() { return salt; }
  public void setSalt(String salt) { this.salt = salt; }

  @Column(name = "secondary_phone")
  public String getSecondaryPhone() { return secondaryPhone; }
  public void setSecondaryPhone(String secondaryPhone) { this.secondaryPhone = secondaryPhone; }

  @Transient
  public String getSessionId() { return sessionId; }
  public void setSessionId(String sessionId) { this.sessionId = sessionId; }
  
  @Column(name = "status", nullable=false)
  public Integer getStatus() { 
    return status;  
  }
  
  public void setStatus(Integer status) { this.status = status; }
  
  @Column(name = "username")
  @Basic(optional = false)
  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

}
