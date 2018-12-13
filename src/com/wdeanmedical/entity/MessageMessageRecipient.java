package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "message_message_recipient")
public class MessageMessageRecipient extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = 5941028502038398835L;
  
  public static final String PRIMARY = "primary";
  public static final String CC = "cc";
  public static final String BCC = "bcc";
  
  public static final String DELETED = "deleted";
  public static final String INBOX = "inbox";
  public static final String DRAFT = "draft";
  public static final String SAVED = "saved";
  public static final String SENT = "sent";
  
  public static String[] PHI_FIELDS = new String[] {};
  
  private Integer messageId;
  private Integer recipientId;
  private String recipientClientType;
  private String recipientMode;
  private Boolean readByRecipient = false;
  private Boolean notify = false;
  private Boolean saved = false;
  private Boolean deleted = false;
  
  public MessageMessageRecipient() {
  }


  @Column(name = "message_id")
  public Integer getMessageId() { return messageId; }
  public void setMessageId(Integer messageId) { this.messageId = messageId; }

  @Column(name = "recipient_id")
  public Integer getRecipientId() { return recipientId; }
  public void setRecipientId(Integer recipientId) { this.recipientId = recipientId; }

  @Column(name = "recipient_client_type", nullable=false)
  public String getRecipientClientType() { return recipientClientType; }
  public void setRecipientClientType(String recipientClientType) { this.recipientClientType = recipientClientType; }

  @Column(name = "read_by_recipient")
  public Boolean getReadByRecipient() { return readByRecipient; }
  public void setReadByRecipient(Boolean readByRecipient) { this.readByRecipient = readByRecipient; }

  @Column(name = "recipient_mode")
  public String getRecipientMode() { return recipientMode; }
  public void setRecipientMode(String recipientMode) { this.recipientMode = recipientMode; }

  @Column(name = "notify")
  public Boolean getNotify() { return notify; }
  public void setNotify(Boolean notify) { this.notify = notify; }

  @Column(name = "saved")
  public Boolean getSaved() { return saved; }
  public void setSaved(Boolean saved) { this.saved = saved; }

  @Column(name = "deleted")
  public Boolean getDeleted() { return deleted; }
  public void setDeleted(Boolean deleted) { this.deleted = deleted; }

}  