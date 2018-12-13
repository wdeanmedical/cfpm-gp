package com.wdeanmedical.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "contact_message")
public class ContactMessage extends BaseEntity implements Serializable {
  private static final long serialVersionUID = -3669765831273241736L;
  public static String[] PHI_FIELDS = new String[] {};
  public static final Integer EXECUTIVE_BOTOX = 1;
  public static final Integer PERFORMANCE_MD = 2;
  
  private String subject;
  private String email;
  private String phone;
  private Date date;
  private String from;
  private String content;
  private Integer companyId;

  public ContactMessage() {
  }

  @Column(name = "subject")
  public String getSubject() { return subject; }
  public void setSubject(String subject) { this.subject = subject; }

  @Column(name = "date")
  public Date getDate() { return date; }
  public void setDate(Date date) { this.date = date; }

  @Column(name = "sent_from")
  public String getFrom() { return from; }
  public void setFrom(String from) { this.from = from; }

  @Column(name = "content", columnDefinition = "text")
  public String getContent() { return content; }
  public void setContent(String content) { this.content = content; }

  @Column(name = "email")
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  @Column(name = "phone")
  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }

  @Column(name = "company_id")
  public Integer getCompanyId() { return companyId; }
  public void setCompanyId(Integer companyId) { this.companyId = companyId; }
  
}
