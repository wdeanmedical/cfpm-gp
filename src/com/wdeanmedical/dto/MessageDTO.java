package com.wdeanmedical.dto; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wdeanmedical.model.MessageDetails;
import com.wdeanmedical.model.MessageRecipient;


public class MessageDTO extends AppDTO {

  public String apptFrom;
  public String apptTo;
  public Boolean draft;
  public String letterStatus;
  public String letterType;
  public String letterTypeLabel;
  public MessageDetails messageDetails;
  public Boolean notifyRecipients;
  public Boolean notifyBCCRecipients;
  public Boolean notifyCCRecipients;
  public String preferredTimes;
  public Integer recipientId;
  public String recipientClientType;
  
  
  public List<MessageRecipient> messagePrimaryRecipients = new ArrayList<MessageRecipient>();
  public List<MessageRecipient> messageBCCRecipients = new ArrayList<MessageRecipient>();
  public List<MessageRecipient> messageCCRecipients = new ArrayList<MessageRecipient>();
  public Map<String,List<?>> messages = new HashMap<String,List<?>>();
  public List<MessageRecipient> validMessageRecipients = new ArrayList<MessageRecipient>();
}
