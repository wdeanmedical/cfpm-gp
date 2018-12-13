package com.wdeanmedical.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "letter")
public class Letter extends BaseEntity implements Serializable {
  private static final long serialVersionUID = -7098483258820339407L;

  public static String[] PHI_FIELDS = new String[] {};
  
  public static final String STATUS_SAVED = "saved";
  public static final String STATUS_SENT = "sent";
  public static final String STATUS_READ = "read";
  public static final String TYPE_DEFAULT = "default";
  public static final String TYPE_MISSED_APPT = "missed_appt";
  
  private Date date;
  private String content;
  private Integer senderId;
  private String recipientClientType;
  private String senderClientType;
  private String senderFullName;
  private Integer recipientId;
  private String letterType;
  private String letterTypeLabel;
  private String letterStatus;

  public Letter() {
  }


  @Column(name = "date")
  public Date getDate() { return date; }
  public void setDate(Date date) { this.date = date; }

  @Column(name = "content", columnDefinition = "text")
  public String getContent() { return content; }
  public void setContent(String content) { this.content = content; }

  @Column(name = "letter_type")
  public String getLetterType() { return letterType; }
  public void setLetterType(String letterType) { this.letterType = letterType; }

  @Column(name = "letter_type_label")
  public String getLetterTypeLabel() { return letterTypeLabel; }
  public void setLetterTypeLabel(String letterTypeLabel) { this.letterTypeLabel = letterTypeLabel; }

  @Column(name = "sender_id")
  public Integer getSenderId() { return senderId; }
  public void setSenderId(Integer senderId) { this.senderId = senderId; }

  @Column(name = "sender_client_type")
  public String getSenderClientType() { return senderClientType; }
  public void setSenderClientType(String senderClientType) { this.senderClientType = senderClientType; }

  @Column(name = "sender_full_name")
  public String getSenderFullName() { return senderFullName; }
  public void setSenderFullName(String senderFullName) { this.senderFullName = senderFullName; }

  @Column(name = "letter_status")
  public String getLetterStatus() { return letterStatus; }
  public void setLetterStatus(String letterStatus) { this.letterStatus = letterStatus; }

  @Column(name = "recipient_client_type", nullable=false)
  public String getRecipientClientType() { return recipientClientType; }
  public void setRecipientClientType(String recipientClientType) { this.recipientClientType = recipientClientType; }

  @Column(name = "recipient_id")
  public Integer getRecipientId() { return recipientId; }
  public void setRecipientId(Integer recipientId) { this.recipientId = recipientId; }
  
}
