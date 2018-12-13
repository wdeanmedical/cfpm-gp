

function app_cancelLetterConfirm() {
  var args = {
    modalTitle:"Cancel Letter to " + app_patientFullName + "?", 
    modalH3:"Cancel Letter to " + app_patientFullName + "?",
    modalH4:"All edits to this letter will be discarded.",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function() {  
      app_displayNotification('Letter to ' + app_patientFullName + ' cancelled.');
      app_toggleLetterScreenControls(CANCELLED_LETTER_MODE);
    });   
  });
}

function letters_deleteLetter(){
   var $existing = $('#letter-content')
   if ($existing.length){
     $existing.summernote('destroy')
     $existing.empty()
   }
   app_letterId=null
   app_letter = null
   $('#letter-form').hide()
}

function app_deleteLetterConfirm() {
  var args = {
    modalTitle:" Delete Letter to " + app_patientFullName + "?", 
    modalH3:"Delete Letter to " + app_patientFullName + "?",
    modalH4:"",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  if (!app_letter) return
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function() {  
      var jsonData = JSON.stringify({ 
        sessionId: app_getSessionId(), 
        id:app_letter.id, 
        recipientId: app_patient.id, 
        module:app_module 
     });
      $.post("msg/deleteLetter", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        letters_deleteLetter()
        app_displayNotification('Letter to ' + app_patientFullName + ' deleted.');
        app_getPatientLetters();
      }); 
    });
  });
}



function app_getLetterById(letterId) {
  for (var i=0;i<app_patientLetters.length;i++){
    if (app_patientLetters[i].id == letterId) {
      return app_patientLetters[i];
    }
  }
}



function app_getPatientLetters() {
  var jsonData = JSON.stringify({ id: app_patient.id, sessionId: app_getSessionId(), module:app_module });
  $.post("msg/getLetters", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_patientLetters = parsedData.list;
    RenderUtil.render('component/simple_data_table', 
     {items:app_patientLetters, 
      title:'Patient Letters', 
      tableName:'app-letters-list', 
      clickable:true, 
      columns:[
        {title:'Date', field:'createdDate', type:'date'},
        {title:'Type', field:'letterTypeLabel', type:'simple'},
        {title:'Status', field:'letterStatus', type:'simple'}
      ]}, function(s) {
      $('#app-letters-list').html(s);
      $('#app-letters-list-title').html(app_practiceClientProperties['app.patient_label'] + " Letter History");
      $('.clickable-table-row').click( function(e){ 
        $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        app_letterId = $(this).attr('name');
        app_viewLetter(app_letterId);
      });
    });
  });
}



function portal_getPatientLetters() {
  var jsonData = JSON.stringify({ patientId: app_patient.id, sessionId: app_getSessionId(), module:app_module });
  $.post("msg/getLetters", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_patientLetters = parsedData.list;
    RenderUtil.render('component/simple_data_table', 
     {items:app_patientLetters, 
      title:'Patient Letters', 
      tableName:'app-letters-list', 
      clickable:true, 
      columns:[
        {title:'Date', field:'createdDate', type:'date'},
        {title:'From', field:'senderFullName', type:'simple'},
        {title:'Subject', field:'letterTypeLabel', type:'simple'}
      ]}, function(s) {
      $('#app-letters-list').html(s);
      $('#app-letters-list-title').html(app_practiceClientProperties['app.patient_label'] + " Letter History");
      $('.clickable-table-row').click( function(e){ 
        $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        app_letterId = $(this).attr('name');
        portal_viewLetter(app_letterId);
      });
    });
  });
}



function app_newLetter() {
  util_clearErrors();  

  if($("#app-new-letter-type").val().length < 1) { 
    util_showError($('#app-new-letter-type'));
    return;
  }
  
  var args = {
    date: dateFormat(new Date(), 'mm/dd/yyyy') , 
    patientName:  app_patientFullName,
    clinicianName: app_clientFullName,
    practicePhone: app_practiceClientProperties['app.business_phone'],
    practiceEmail: app_client.email
  };
  
  var letter = $("#app-new-letter-type").val();
  letters_deleteLetter()
  console.log('letters_deleteLetter')
  RenderUtil.render('letter/'+letter, args, function(s) { 
    console.log('update content!!!!!', s)
    $('#letter-content').html(s).summernote({
      height: 800,
      toolbar: [
      ['style', ['bold', 'italic', 'underline', 'clear']],
      ['font', ['strikethrough', 'superscript', 'subscript']],
      ['fontsize', ['fontsize']],
      ['color', ['color']],
      ['para', ['ul', 'ol', 'paragraph']],
      ['height', ['height']],
      ['misc', ['print']]
    ]});
  });  
  app_toggleLetterScreenControls(NEW_LETTER_MODE);
  $('#letter-title').html('New Letter - ' + $("#app-new-letter-type option:selected").text());
  $("#letter-form").show();
}



function app_renderLettersScreen() {
  RenderUtil.render('screen/letters_screen', {}, function(s) {
    $("#patient-chart-item-screen").html(s);
    app_showScreen('Letters', app_patientChartItemCache);
    app_chartItemStack($('#letters-screen'), true);
    app_getPatientLetters();
    $('#app-new-letter-btn').on('click', function() { app_newLetter() });
    $('#letter-send-btn').on('click', function() { app_sendLetterConfirm() });
    $('#letter-cancel-btn').on('click', function() { app_cancelLetterConfirm() });
    $('#letter-save-btn').on('click', function() { app_saveLetter() });
    $('#letter-delete-btn').on('click', function() { app_deleteLetterConfirm() });
  });
}



function app_saveLetter() {
  var content = escape($('#letter-content').summernote('code'));
  var newOpts={
    letterType: $("#app-new-letter-type").val(),
    letterTypeLabel: $("#app-new-letter-type option:selected").text()
  }
  var opts={}
  if (!app_letterId){
    $.extend(opts, newOpts)
  }
  var jsonData = JSON.stringify($.extend({ 
    sessionId: app_getSessionId(), 
    id: app_letterId, 
    draft: true, 
    recipientId: app_patient.id,
    recipientClientType: PATIENT_CLIENT_TYPE,
    content:content, 
    module:app_module 
  }, opts ));
  $.post("msg/saveLetter", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_displayNotification('Letter saved');
    if (!app_letterId){
      app_letterId=parsedData.id
    }
    app_getPatientLetters();
  }); 
}



function app_sendLetterConfirm() {
  var letterType  = app_letter != null ? app_letter.letterType : $("#app-new-letter-type").val();
  var letterTypeLabel  = app_letter != null ? app_letter.letterTypeLabel : $("#app-new-letter-type option:selected").text();
  var content = escape($('#letter-content').summernote('code'));
  var args = {
    modalTitle:"Send Letter to " + app_patientFullName + "?", 
    modalH3:"Send Letter to " + app_patientFullName + "?",
    modalH4:"",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function() {  
      var jsonData = JSON.stringify({ 
        sessionId: app_getSessionId(), 
        id:app_letterId, 
        draft: false, 
        letterType: letterType,
        letterTypeLabel: letterTypeLabel,
        recipientId: app_patient.id, 
        recipientClientType: PATIENT_CLIENT_TYPE,
        content:content, 
        module:app_module 
     });
      $.post("msg/sendLetter", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        app_displayNotification('Letter sent to ' + app_patientFullName);
        app_getPatientLetters();
        app_toggleLetterScreenControls(SENT_LETTER_MODE);
      }); 
    });
  });
}



function app_toggleLetterScreenControls(mode) {
  $('.letters-button').hide();
  if (mode == SENT_LETTER_MODE || mode == CANCELLED_LETTER_MODE) {
    $('#letter-content').summernote('reset'); 
    $("#app-new-letter-type").val('');
    $('#letter-title').html('');
    $("#letter-form").hide();
  }
  else if (mode == NEW_LETTER_MODE) {
    $("#letter-form .note-editable").attr("contenteditable","true");
    $('#letter-send-btn, #letter-cancel-btn, #letter-save-btn').show();
  }
  else if (mode == VIEW_LETTER_MODE) {
    $("#app-new-letter-type").val('');
    if (app_letter.letterStatus == LETTER_SAVED) {
      $('#letter-delete-btn, #letter-send-btn, #letter-save-btn').show();
      $("#letter-form .note-editable").attr("contenteditable","true");
    }
    else {
      $("#letter-form .note-editable").attr("contenteditable","false");
    }
  }
}



function portal_updateLetterStatus(app_letter, letterStatus) {
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(), 
    id: app_letter.id, 
    module: app_module, 
    letterStatus: letterStatus
  });
  $.post("msg/updateLetterStatus", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
  }); 
}



function app_viewLetter(letterId) {
  app_letter = app_getLetterById(letterId);
  $('#letter-content').summernote({
      height: 800,
      toolbar: [
      ['style', ['bold', 'italic', 'underline', 'clear']],
      ['font', ['strikethrough', 'superscript', 'subscript']],
      ['fontsize', ['fontsize']],
      ['color', ['color']],
      ['para', ['ul', 'ol', 'paragraph']],
      ['height', ['height']],
      ['misc', ['print']]
    ]});
  $('#letter-content').summernote('code', unescape(app_letter.content));
  $('#letter-title').html(app_letter.letterTypeLabel + '  -  ' + app_letter.date);
  $("#letter-form").show();
  app_toggleLetterScreenControls(VIEW_LETTER_MODE);
}



function portal_viewLetter(letterId) {
  app_letter = app_getLetterById(letterId);
  $("#letter-form").show();
  $('#letter-content').summernote({
      height: 800,
      toolbar: [ ['misc', ['print']] ]
  });
  $('#letter-content').summernote('disable')
  $('#letter-content').summernote('code', unescape(app_letter.content));
  $('#letter-title').html(app_letter.letterTypeLabel + '  -  ' + app_letter.date);
  if (app_letter.letterStatus != LETTER_READ) {
    portal_updateLetterStatus(app_letter, LETTER_READ);
  }
}
