package com.wdeanmedical.entity;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wdeanmedical.util.TimeUtil;

@Entity
@Table(name = "recovery_code")
public class RecoveryCode extends BaseEntity implements Serializable {
	public static Duration RECOVERY_EXPIRATION_WINDOW;
	public static Duration ACTIVATION_EXPIRATION_WINDOW;
	
	private static final long serialVersionUID = -477170761362409623L;
	
	private Integer clientId;
	private String clientType;
	private String code;
	private Date expiresAt;
	private Boolean recovered = false;
	
	public RecoveryCode() {}
	
	public RecoveryCode(String code, Date expiresAt) {
		this.setCode(code);
		this.setExpiresAt(expiresAt);
	}
	
	@Column(name = "client_id")
  public Integer getClientId() { return clientId; }
  public void setClientId(Integer clientId) { this.clientId = clientId; }

  @Column(name = "client_type")
  public String getClientType() { return clientType; }
  public void setClientType(String clientType) { this.clientType = clientType; }

  @Column(name = "code", nullable=false)
  public String getCode() { return code; }
  public void setCode(String recoveryCode) { this.code = recoveryCode; }
 
  @Column(name = "expires_at", nullable=false)
	public Date getExpiresAt() { return expiresAt;}
	public void setExpiresAt(Date expiresAt) { this.expiresAt = expiresAt;}

	@Column(columnDefinition = "boolean default false")
	public Boolean getRecovered() {
		return recovered;
	}

	public void setRecovered(Boolean recovered) {
		this.recovered = recovered;
	}

	@Transient
  public boolean isInvalid() {
    return recovered || !TimeUtil.isBeforeNow(expiresAt);
  }

}
