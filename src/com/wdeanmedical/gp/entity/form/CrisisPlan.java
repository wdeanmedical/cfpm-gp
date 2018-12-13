package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wdeanmedical.entity.form.WDMForm;

@Entity
@Table(name = "crisis_plan")
@Inheritance(strategy = InheritanceType.JOINED)
public class CrisisPlan extends WDMForm implements Serializable {
  private static final long serialVersionUID = 5104531545271129243L;
  public static final String NAME = "crisis_plan";
  public static String[] PHI_FIELDS = new String[] {};
  
  private String homePhone;
  private String workPhone;
  private String cellPhone;
  private String healthIssues;
  private String serviceProviders;
  private String signals;
  private String triggers;
  private String helpful;
  private String deescalation;
  private String positive;
  private String notMention;
  private String callOrder;
  private String clientSig;
  private Date clientSigDate;
  private String providerSig;
  private Date providerSigDate;
  
  
  public CrisisPlan() { 
    this.name = CrisisPlan.NAME;
  }


  @Column(name = "home_phone")
  public String getHomePhone() { return homePhone; }
  public void setHomePhone(String homePhone) { this.homePhone = homePhone; }

  @Column(name = "work_phone")
  public String getWorkPhone() { return workPhone; }
  public void setWorkPhone(String workPhone) { this.workPhone = workPhone; }

  @Column(name = "cell_phone")
  public String getCellPhone() { return cellPhone; }
  public void setCellPhone(String cellPhone) { this.cellPhone = cellPhone; }

  @Column(name = "health_issues", columnDefinition="text")
  public String getHealthIssues() { return healthIssues; }
  public void setHealthIssues(String healthIssues) { this.healthIssues = healthIssues; }

  @Column(name = "service_providers", columnDefinition="text")
  public String getServiceProviders() { return serviceProviders; }
  public void setServiceProviders(String serviceProviders) { this.serviceProviders = serviceProviders; }

  @Column(name = "signals", columnDefinition="text")
  public String getSignals() { return signals; }
  public void setSignals(String signals) { this.signals = signals; }

  @Column(name = "triggers", columnDefinition="text")
  public String getTriggers() { return triggers; }
  public void setTriggers(String triggers) { this.triggers = triggers; }

  @Column(name = "helpful", columnDefinition="text")
  public String getHelpful() { return helpful; }
  public void setHelpful(String helpful) { this.helpful = helpful; }

  @Column(name = "deescalation", columnDefinition="text")
  public String getDeescalation() { return deescalation; }
  public void setDeescalation(String deescalation) { this.deescalation = deescalation; }

  @Column(name = "positive", columnDefinition="text")
  public String getPositive() { return positive; }
  public void setPositive(String positive) { this.positive = positive; }

  @Column(name = "not_mention", columnDefinition="text")
  public String getNotMention() { return notMention; }
  public void setNotMention(String notMention) { this.notMention = notMention; }

  @Column(name = "call_order", columnDefinition="text")
  public String getCallOrder() { return callOrder; }
  public void setCallOrder(String callOrder) { this.callOrder = callOrder; }

  @Column(name = "client_sig")
  public String getClientSig() { return clientSig; }
  public void setClientSig(String clientSig) { this.clientSig = clientSig; }

  @Column(name = "client_sig_date")
  public Date getClientSigDate() { return clientSigDate; }
  public void setClientSigDate(Date clientSigDate) { this.clientSigDate = clientSigDate; }

  @Column(name = "provider_sig")
  public String getProviderSig() { return providerSig; }
  public void setProviderSig(String providerSig) { this.providerSig = providerSig; }

  @Column(name = "provider_sig_date")
  public Date getProviderSigDate() { return providerSigDate; }
  public void setProviderSigDate(Date providerSigDate) { this.providerSigDate = providerSigDate; }

}
