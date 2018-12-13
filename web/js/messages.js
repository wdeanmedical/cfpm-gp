
function app_buildMessage(messageDetails, draft) {
  var str = "";
  if (draft == false) {
    str += "From: " + messageDetails.sender.fullName + "<br>" + 
    app_buildMessageRecipientList("To: ", messageDetails.messagePrimaryRecipients, '<br>') +
    app_buildMessageRecipientList("CC: ", messageDetails.messageCCRecipients, '<br>') +
    app_buildMessageRecipientList("BCC: ", messageDetails.messageBCCRecipients, '<br>') +
    "<br>"+
    "Subject: " + messageDetails.message.subject +
    "<hr>" + 
    "<p>" + messageDetails.message.content + "</p>";
  }
  else {
    str = messageDetails.message.content;
  }
    
  return str;
} 


function app_buildReplyMessage(messageDetails, messageLabel) {
  var str = "\n\n=========  " + messageLabel + " ===========\n";
  str += "From: " + messageDetails.sender.fullName + "\n" + 
  app_buildMessageRecipientList("To: ", messageDetails.messagePrimaryRecipients, '\n') +
  app_buildMessageRecipientList("CC: ", messageDetails.messageCCRecipients, '\n') +
  app_buildMessageRecipientList("BCC: ", messageDetails.messageBCCRecipients, '\n') +
  "\n"+
  "Subject: " + messageDetails.message.subject +
  "\n" + messageDetails.message.content;
  return str;
}



function app_buildMessageRecipientList(label, list, newline) {
  if (list != null && list.length > 0) {
    var str = label;
    for(i=0;i<list.length;i++) {
      str += list[i].fullName + ", "; 
    }
    str = str.substring(0, str.length-2) + newline;
  }
  return str || "";
} 



function app_cancelMessageConfirm() {
  var args = {
    modalTitle:"Cancel Message?", 
    modalH3:"Cancel Message?",
    modalH4:"",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function() {  
      app_resetMessageComposeForm();
      $('#inbox').tab('show');
      app_displayNotification('Message Cancelled');
    });
  });
}

function app_closeMessage() {
  $('#messages-message').css({display: "none"});
  $('#messages-mailboxes').css({display: "block"});
  app_toggleMessageScreenButtons(NOT_READING_MESSAGE);
}



function app_composeForwardedMessage() {
  app_message = undefined;
  app_toggleMessageScreenButtons(NOT_READING_MESSAGE);
  $('#message-compose-btn').tab('show');
  $('#messages-mailboxes').css({display: "block"}); 
  $('#messages-message').css({display: "none"}); 
  $('#message-content-text').val(app_buildReplyMessage(app_messageDetails, 'forwarded message'));
  $('#message-subject').val('FW: ' + app_messageDetails.message.subject);
  app_initValidMessageRecipientsTypeaheads();
  $('#message-compose-title').html('Message Forward');
}



function app_composeReplyMessage() {
  app_message = undefined;
  app_toggleMessageScreenButtons(NOT_READING_MESSAGE);
  $('#message-compose-btn').tab('show');
  $('#messages-mailboxes').css({display: "block"}); 
  $('#messages-message').css({display: "none"}); 
  $('#message-content-text').val(app_buildReplyMessage(app_messageDetails, 'original message'));
  $('#message-subject').val('RE: ' + app_messageDetails.message.subject);
  app_initValidMessageRecipientsTypeaheads();
  $('#message-primary-recipients').tagsinput('add', app_messageDetails.sender);
  $('#message-compose-title').html('Message Reply');
}



function app_deleteMessageConfirm() {
  var args = {
    modalTitle:"Delete Message?", 
    modalH3:"Delete Message?",
    modalH4:"",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function() {  
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), id: app_message.id, module:app_module });
      $.post("msg/deleteMessage", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        app_displayNotification('Message Deleted');
        app_viewMessages(MESSAGE_INBOX);
      }); 
    });
  });
}



function app_editDraftMessage() {
  $('#message-compose-btn').tab('show');
  $('#messages-mailboxes').css({display: "block"}); 
  $('#messages-message').css({display: "none"}); 
  $('#message-content-text').val(app_buildMessage(app_messageDetails, DRAFT));
  $('#message-subject').val(app_messageDetails.message.subject);
  app_initValidMessageRecipientsTypeaheads();
  form_addTagsToInput($('#message-primary-recipients'), app_messageDetails.messagePrimaryRecipients);
  form_addTagsToInput($('#message-cc-recipients'), app_messageDetails.messageCCRecipients);
  form_addTagsToInput($('#message-bcc-recipients'), app_messageDetails.messageBCCRecipients);
  $('#message-compose-title').html('Edit Draft Message');
}



function app_getMessageById(mailbox) {
  for (var i=0;i<app_messages[mailbox].length;i++){
    if (app_messages[mailbox][i].id == app_messageId) {
      return app_messages[mailbox][i];
    }
  }
}



function portal_getPatientSentMessages() {
  var jsonData = JSON.stringify({ id: app_client.id, sessionId: app_getSessionId(), module:app_module });
  $.post("msg/getPatientSentMessages", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    portal_patientMessages = parsedData.list;
    RenderUtil.render('component/simple_data_table', 
    {items:portal_patientMessages, 
    title:'Messages', 
    tableId:'patient_sent_messages', 
    columns:[{title:'Date', field:'date', type:'date'}, 
             {title:'To', field:'clinician.firstName', type:'clinician'}, 
             {title:'Subject', field:'subject', type:'simple'}]},
    function(s) {
      $('#patient_sent_messages_table').html(s);
      $('.clickable-table-row').click( function(e) { 
        $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        portal_handleClickablePatientMessageRow(e); 
      });
    });
  });
}



function portal_getPatientMessages() {
  var jsonData = JSON.stringify({ patientId: app_patient.id, sessionId: app_getSessionId(), module:app_module });
  $.post("msg/getMessages", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_messages = parsedData.messages;
    app_loadMailbox(MESSAGE_INBOX);
    app_loadMailbox(MESSAGE_SENT, MESSAGES_TO);
  });
}



function app_getPracticeMessages() {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module });
  $.post("msg/getMessages", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_messages = parsedData.messages;
    app_loadMailbox(MESSAGE_INBOX);
    app_loadMailbox(MESSAGE_DRAFT, MESSAGES_TO);
    app_loadMailbox(MESSAGE_SAVED);
    app_loadMailbox(MESSAGE_SENT, MESSAGES_TO);
  });
}
  
  

function app_getValidMessageRecipients() {
  var jsonData = JSON.stringify({sessionId: app_getSessionId(), module:app_module });
  $.post("msg/getValidMessageRecipients", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_validMessageRecipients = parsedData.validMessageRecipients;
    app_initValidMessageRecipientsTypeaheads();
  });
}


function messages__getTagsinputClass(item) {
   switch (item.clientType) {
     case 'patient'   : return 'label label-primary';
     case 'user'   : return 'label label-default';
     default: return 'label label-success';
   }
}

function app_initValidMessageRecipientsTypeaheads() {
    app_messagePrimaryRecipientsInput = $('#message-primary-recipients');
    app_messagePrimaryRecipientsInput.tagsinput({
      tagClass: messages__getTagsinputClass,
      itemValue: 'clientId',
      itemText: 'fullName',
      typeaheadjs: {
        name: 'recipients',
        displayKey: 'fullName',
        source: util_objectSubstringMatcher(app_validMessageRecipients, 'fullName')
      }
    });
    
    app_messageCCRecipientsInput = $('#message-cc-recipients');
    app_messageCCRecipientsInput.tagsinput({
       tagClass: messages__getTagsinputClass,
      itemValue: 'clientId',
      itemText: 'fullName',
      typeaheadjs: {
        name: 'recipients',
        displayKey: 'fullName',
        source: util_objectSubstringMatcher(app_validMessageRecipients, 'fullName')
      }
    });
    
    app_messageBCCRecipientsInput = $('#message-bcc-recipients');
    app_messageBCCRecipientsInput.tagsinput({
       tagClass: messages__getTagsinputClass,
      itemValue: 'clientId',
      itemText: 'fullName',
      typeaheadjs: {
        name: 'recipients',
        displayKey: 'fullName',
        source: util_objectSubstringMatcher(app_validMessageRecipients, 'fullName')
      }
    });
    
    $("#msg-compose .twitter-typeahead").css('display', 'inline');
    $("#msg-compose .bootstrap-tagsinput").css('width', '100%');
}

var MESSAGES_COLUMNS_TO = [
  {title:'Date', field:'date', type:'date'},
  {title:'To', field:'receiverFullName', type:'simple'},
  {title:'Subject', field:'subject', type:'simple'}
]

var MESSAGES_COLUMNS_FROM = [
  {title:'Date', field:'date', type:'date'},
  {title:'From', field:'senderFullName', type:'simple'},
  {title:'Subject', field:'subject', type:'simple'}
]

var MESSAGES_TO = "messages_to";
var MESSAGES_FROM = "messages_from";

function app_loadMailbox(mailbox, fromTo) {
  var columns = fromTo == MESSAGES_TO ? MESSAGES_COLUMNS_TO : MESSAGES_COLUMNS_FROM;   
  RenderUtil.render('component/simple_data_table', 
  {items:app_messages[mailbox], 
    title:'Messages', 
    tableName: mailbox, 
    tableId: mailbox, 
    clickable:true, 
    columns: columns
  }, function (s) {
    $('#messages-'+mailbox).html(s);
    $('.clickable-table-row').off('click').on('click', function(e) { 
      $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
      app_messageId = $(this).attr('name');
      if (app_module == PORTAL_MODULE) { 
        app_messageMailbox = $(this).attr('data-table-name');
        portal_viewMessage();
      }
      else {
        app_viewMessage();
      }
    });
  });
}

function app_resetMessageComposeForm() {
  util_clearErrors();
  app_messagePrimaryRecipientsInput.tagsinput('removeAll');
  app_messageBCCRecipientsInput.tagsinput('removeAll');
  app_messageCCRecipientsInput.tagsinput('removeAll');
  $('#message-notify-recipients').prop('checked', false);
  $('#message-notify-cc-recipients').prop('checked', false);
  $('#message-notify-bcc-recipients').prop('checked', false);
  $('#message-subject').val('');
  $('#message-content-text').val('');
}



function portal_rxRequest() {
  var isValid = true;
  util_clearErrors();
  
  if($("#rx-request-clinician").val().length < 1) { 
    util_showError($('#rx-request-clinician-validation'));
    isValid = false;
  }
  if($("#rx-request-message").val().length < 1) { 
    util_showError($('#rx-request-message-validation'));
    isValid = false;
  }
  
  if (isValid == false) {
    return;
  }
  
  var jsonData = JSON.stringify({ 
    patientId: app_client.id,
    sessionId: app_getSessionId(), 
    module:app_module,
    subject: "Rx Renewal Request", 
    clinicianId: $('#rx-request-clinician').val(), 
    content: $('#rx-request-message').val(), 
    messageType: MESSAGE_TYPE_RX_RENEWAL
  });
  $.post("msg/processPatientMessage", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    $('#rx-request-clinician').val('');
    $('#rx-request-message').val('');
    util_clearErrors();
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_displayNotification('Prescription renewal request sent.');
  });
}



function app_saveMessageAsDraft() {
  var isValid = true;
  util_clearErrors(); 
  
  if($("#message-subject").val().length < 1) { 
    util_showError($('#message-subject'));
    isValid = false;
  }
  if(util_isFieldEmpty('#message-content-text')) {
    util_showError($('#message-content-text'));
    isValid = false;
  }
  
  if (isValid == false) {
    return;
  }
  
  var messageId = (app_messageMailbox != MESSAGE_COMPOSE) ? app_message.id : null;
  
  var jsonData = JSON.stringify({ 
    id: messageId,
    messagePrimaryRecipients: app_messagePrimaryRecipientsInput.tagsinput('items'),
    messageCCRecipients: app_messageCCRecipientsInput.tagsinput('items'),
    messageBCCRecipients: app_messageBCCRecipientsInput.tagsinput('items'),
    notifyRecipients: $('#message-notify-recipients').prop('checked') == true,
    notifyCCRecipients: $('#message-notify-cc-recipients').prop('checked') == true,
    notifyBCCRecipients: $('#message-notify-bcc-recipients').prop('checked') == true,
    sessionId: app_getSessionId(), 
    module:app_module,
    subject: $('#message-subject').val(), 
    content: $('#message-content-text').val(),
    draft: DRAFT
  });
    
  $.post("msg/saveMessageAsDraft", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_displayNotification('Message Saved as Draft');
    if(app_messageMailbox != MESSAGE_COMPOSE) {
      app_viewMessages(MESSAGE_INBOX);
    }
  }); 
}



function app_saveMessageConfirm() {
  var args = {
    modalTitle:"Save Message?", 
    modalH3:"Save Message?",
    modalH4:"This will move the message to the Saved folder",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function() {  
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), id: app_message.id, module:app_module });
      $.post("msg/saveMessage", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        app_displayNotification('Message moved to Saved folder');
        app_viewMessages(MESSAGE_INBOX);
      }); 
    });
  });
}



function app_sendMessageFromProvider() {
  var isValid = true;
  util_clearErrors(); 

 if (app_messagePrimaryRecipientsInput.tagsinput('items').length < 1 && 
     app_messageCCRecipientsInput.tagsinput('items').length < 1 &&
     app_messageBCCRecipientsInput.tagsinput('items').length < 1) { 
    util_showError($('#message-primary-recipients'));
    isValid = false;
  }
  if($("#message-subject").val().length < 1) { 
    util_showError($('#message-subject'));
    isValid = false;
  }
  if(util_isFieldEmpty('#message-content-text')) {
    util_showError($('#message-content-text'));
    isValid = false;
  }  
  if (isValid == false) {
    return;
  }
  
  var messageId = (app_message != null) ? app_message.id : null;
  
  var jsonData = JSON.stringify({ 
    id: messageId,
    messagePrimaryRecipients: app_messagePrimaryRecipientsInput.tagsinput('items'),
    messageCCRecipients: app_messageCCRecipientsInput.tagsinput('items'),
    messageBCCRecipients: app_messageBCCRecipientsInput.tagsinput('items'),
    notifyRecipients: $('#message-notify-recipients').prop('checked') == true,
    notifyCCRecipients: $('#message-notify-cc-recipients').prop('checked') == true,
    notifyBCCRecipients: $('#message-notify-bcc-recipients').prop('checked') == true,
    sessionId: app_getSessionId(), 
    module:app_module,
    subject: $('#message-subject').val(), 
    content: $('#message-content-text').val(),
    draft: NOT_DRAFT
  });
  
  $.post("msg/sendMessageFromProvider", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_displayNotification('Message Sent.');
    app_resetMessageComposeForm();
  });
}



function portal_sendMessageToProvider() {
  var isValid = true;
  util_clearErrors();
  
  if($("#send-message-clinician").val().length < 1) { 
    util_showError($('#send-message-clinician-validation'));
    isValid = false;
  }
  if($("#send-message-message").val().length < 1) { 
    util_showError($('#send-message-message-validation'));
    isValid = false;
  }
  
  if (isValid == false) {
    return;
  }
  
  var jsonData = JSON.stringify({ 
    patientId: app_client.id,
    sessionId: app_getSessionId(), 
    module:app_module,
    subject: $('#send-message-subject').val(), 
    clinicianId: $('#send-message-clinician').val(), 
    content: $('#send-message-message').val(),
    messageType: MESSAGE_TYPE_MEDICAL_ADVICE
  });
  $.post("msg/processPatientMessage", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    $('#send-message-clinician').val('');
    $('#send-message-subject').val('');
    $('#send-message-message').val('');
    util_clearErrors();
    app_displayNotification('Message sent to provider.');
  });
}



function app_toggleMessageScreenButtons(readingMessage) {
  $('.messages-button').hide();
  if (readingMessage == false) {
    $('#message-cancel-btn, #message-send-btn, #message-draft-btn').show();
  }
  else if (readingMessage == true) {
    $('#message-close-btn').show()
    $('#message-print-btn, #message-delete-btn').show();
    if(app_messageMailbox == MESSAGE_INBOX || app_messageMailbox == MESSAGE_SENT) {
      $('#message-forward-btn, #message-save-btn').show();
    }
    if(app_messageMailbox == MESSAGE_DRAFT) {
      $('#message-send-btn, #message-edit-btn').show();
    }
    if(app_messageMailbox == MESSAGE_INBOX || app_messageMailbox == MESSAGE_SENT && app_messageDetails.isRecipient) {
      $('#message-reply-btn').show();
    }
  }
}



function app_viewMessage() {
  app_message = app_getMessageById(app_messageMailbox);  
  $('#messages-message').css({display: "block"});
  $('#messages-mailboxes').css({display: "none"});
  $('#messages-message-subject').html(app_message.subject);
  
  var jsonData = JSON.stringify({ id: app_message.id, sessionId: app_getSessionId() });
    $.post("msg/getMessageDetails", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    app_messageDetails = parsedData.messageDetails;
    if (!util_checkSessionResponse(parsedData)) return false;
     $('#message-content').html(app_buildMessage(app_messageDetails, NOT_DRAFT));
     $('#message-message-date').html(app_message.date);
     app_toggleMessageScreenButtons(READING_MESSAGE);
  });
}



function portal_viewMessage() {
  app_message = app_getMessageById(app_messageMailbox);  
  
  var jsonData = JSON.stringify({ patientId: app_patient.id, id: app_message.id, sessionId: app_getSessionId() });
  $.post("msg/getMessageDetails", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    app_messageDetails = parsedData.messageDetails;
    if (!util_checkSessionResponse(parsedData)) return false;
    var messageContent = app_buildMessage(app_messageDetails, NOT_DRAFT);
    var tableId = app_messageMailbox+'-'+app_message.id;
    $('#message-message-date').html(app_message.date);

    if ($('#'+tableId+'-content td').html() != '') {
      $('#'+tableId+'-content td').html('');
      $('#'+tableId+'-content td').css({"padding-bottom":"0px"});
      $('tr.row-content').css({"display":"none"});
      return;
    }
  
    $('tr.row-content').css({"display":"table-row"});
    $('.row-content td').html('');
    $('.row-content td').css({"padding-bottom":"0px"});
    
    $('tr.row-content').hide();
    $('#'+tableId+'-content').show();
    
    $('#'+tableId+'-content td').html(messageContent.replace(/\n/g, '<br>'));
    $('#'+tableId+'-content td').css({"padding-bottom":"20px"});
  });
}



function app_viewMessages(mailbox) {
  app_viewStack('messages-screen', DO_SCROLL);
  RenderUtil.render('screen/messages_screen', {}, function(s) {
    $("#messages-screen").html(s);
    
    app_getValidMessageRecipients();
    app_messageMailbox = mailbox;
    if (app_module == PM_MODULE) {
      app_getPracticeMessages();
    }
    
    $('#msg-'+mailbox+'-link').addClass('active');
    $('#message-send-btn').off('click').on('click', function() { app_sendMessageFromProvider() });
    $('#message-cancel-btn').off('click').on('click', function() { app_cancelMessageConfirm() });
    $('#message-draft-btn').off('click').on('click', function() { app_saveMessageAsDraft() });
    $('#message-edit-btn').off('click').on('click', function() { app_editDraftMessage() });
    $('#message-delete-btn').off('click').on('click', function() { app_deleteMessageConfirm() });
    $('#message-close-btn').off('click').on('click', function() { app_closeMessage() });
    $('#message-save-btn').off('click').on('click', function() { app_saveMessageConfirm() });
    $('#message-reply-btn').off('click').on('click', function() { app_composeReplyMessage() });
    $('#message-forward-btn').off('click').on('click', function() { app_composeForwardedMessage() });
    
    $('#message-print-btn').off('click').on('click', function() { 
      $("#message-content-panel").printThis({
        debug: false,        
        importCSS: false,    
        importStyle: false, 
        printContainer: true, 
        loadCSS: DEFAULT_APP_PATH + "css/all.css", 
        pageTitle: "Selected Message",       
        removeInline: false,  
        printDelay: 333,  
        header: null,    
        formValues: true
      });
    });
    
    $('#sent, #draft, #saved, #inbox, #message-compose-btn').on('click', function() { 
      $('#messages-mailboxes').css({display: "block"}); 
      $('#messages-message').css({display: "none"}); 
      app_messageMailbox = $(this).attr('id'); 
      app_toggleMessageScreenButtons(NOT_READING_MESSAGE);
    });
    
    $("a[href='#msg-compose']").on('shown.bs.tab', function(e) { app_resetMessageComposeForm(); });
    $("a[href='#msg-inbox']").on('show.bs.tab', function(e) { app_viewMessages(MESSAGE_INBOX); });
    $("a[href='#msg-saved']").on('show.bs.tab', function(e) { app_viewMessages(MESSAGE_SAVED); });
    $("a[href='#msg-sent']").on('show.bs.tab', function(e) { app_viewMessages(MESSAGE_SENT); });
    $("a[href='#msg-draft']").on('show.bs.tab', function(e) { app_viewMessages(MESSAGE_DRAFT); });
  }); 
}