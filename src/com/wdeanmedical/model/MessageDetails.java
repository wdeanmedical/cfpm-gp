package com.wdeanmedical.model;

import java.util.ArrayList;
import java.util.List;

import com.wdeanmedical.entity.Message;

public class MessageDetails {
  public Boolean isRecipient = false;
  public Message message;
  public List<MessageRecipient> messagePrimaryRecipients = new ArrayList<MessageRecipient>();
  public List<MessageRecipient> messageBCCRecipients = new ArrayList<MessageRecipient>();
  public List<MessageRecipient> messageCCRecipients = new ArrayList<MessageRecipient>();
  public MessageRecipient sender;
}