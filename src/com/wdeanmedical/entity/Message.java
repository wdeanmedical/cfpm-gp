package com.wdeanmedical.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wdeanmedical.model.MessageType;

@Entity
@Table(name = "message")
public class Message extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -568848644986949555L;
  public static String[] PHI_FIELDS = new String[] {};
  
  private String subject;
  private Date date;
  private String content;
  private String from;
  private Integer senderId;
  private String senderClientType;
  private String senderFullName;
  private String receiverFullName;
  private Integer messageType = MessageType.DEFAULT;
  private Boolean draft = false;

  public Message() {
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

  @Column(name = "message_type")
  public Integer getMessageType() { return messageType; }
  public void setMessageType(Integer messageType) { this.messageType = messageType; }

  @Column(name = "sender_id")
  public Integer getSenderId() { return senderId; }
  public void setSenderId(Integer senderId) { this.senderId = senderId; }

  @Column(name = "sender_client_type")
  public String getSenderClientType() { return senderClientType; }
  public void setSenderClientType(String senderClientType) { this.senderClientType = senderClientType; }

  @Column(name = "sender_full_name")
  public String getSenderFullName() { return senderFullName; }
  public void setSenderFullName(String senderFullName) { this.senderFullName = senderFullName; }

  @Column(name = "draft")
  public Boolean getDraft() { return draft; }
  public void setDraft(Boolean draft) { this.draft = draft; }

  @Transient
  public String getReceiverFullName() {
    return receiverFullName;
  }
	
  public void setReceiverFullName(String receiverFullName) {
    this.receiverFullName = receiverFullName;
  }

}
