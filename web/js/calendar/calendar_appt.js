
function app_deleteApptConfirm(e, id) {
  var args = {
    modalTitle:"Delete Appointment", 
    modalH3:"Delete Appointment?",
    modalH4:"",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove();
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function(){  
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), id:id, module:app_module });
      $.post("cal/deleteAppt", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        $('#modal-confirm').modal('hide'); 
        $('#modal-appt').modal('hide');
        app_displayNotification('Appointment Deleted.');
        app_loadCalendar();
      }); 
    });
  });
}

function app_showApptForm(calEvent) {
  var start = calEvent.start;
  var end = calEvent.end;
  var id = calEvent.id;
  var title = 'Appointment';
  RenderUtil.render('dialog/appt_show', {title:title}, function(s) {
    $('#modal-show-appt').remove();
    var $modal=$(s)
    $('#modals-placement').html($modal);
    $('#modal-show-appt').modal('show'); 
    $('#app-appt-start').val(dateFormat(start, 'h:MM TT'));
    $('#app-appt-end').val(dateFormat(end, 'h:MM TT'));
    $('#app-appt-date').datetimepicker({ format: 'L', defaultDate: start });
    $('#app-appt-desc').val(calEvent.desc);    
    $('#app-appt-clinician').val(calEvent.clinicianName)
    $('#app-appt-patient').val(calEvent.patientName)
    $('#app-appt-type').val(calEvent.apptType)
    $modal.find('.form-control').attr('disabled','disabled');
  })  
}

function app_editApptForm(calEvent) {
  var start = calEvent.start;
  var end = calEvent.end;
  var id = calEvent.id;
  var offset = new Date().getTimezoneOffset();
  start.add(offset,'m');
  end.add(offset,'m');
  var title = 'Edit Appointment';
  RenderUtil.render('dialog/appt', {title:title, deleteButton:'Delete',submitButtonLabel:'Update'}, function(s) {
    $('#modal-appt').remove();
    $('#modals-placement').html(s);
    $('#modal-appt').modal('show'); 
    $('#app-appt-date').datetimepicker({ format: 'L', defaultDate: start });
    $('#modal-appt .input-timepicker').timepicker();
    $('#app-appt-start').val(dateFormat(start, 'h:MM TT'));
    $('#app-appt-end').val(dateFormat(end, 'h:MM TT'));
    $('#app-appt-desc').val(calEvent.desc);
    
    var jsonData = JSON.stringify({ 
      sessionId: app_getSessionId(),
      module:app_module,
      id: calEvent.id
    });
  
    $.post("cal/getCalendarEvent", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      if (!util_checkSessionResponse(parsedData)) return false;
      var appt = parsedData.calendarEvent;
      $('#app-appt-patient').tagsinput({
        itemValue: 'clientId',
        itemText: 'fullName',
        maxTags: 1,
        typeaheadjs: {
          name: 'patientInfo',
          displayKey: 'fullName',
          source: util_objectSubstringMatcher(app_patientInfo, 'fullName')
        }
      });
  
      $('#app-appt-clinician').tagsinput({
        itemValue: 'clientId',
        itemText: 'fullName',
        maxTags: 1,
        typeaheadjs: {
          name: 'clinicianInfo',
          displayKey: 'fullName',
          source: util_objectSubstringMatcher(app_clinicianInfo, 'fullName')
        }
      });
    
      $("#modal-appt .twitter-typeahead").css('display', 'inline');
      $("#modal-appt .bootstrap-tagsinput").css('width', '100%');
      $("#modal-appt .bootstrap-tagsinput").css('height', '30px');
      form_addTagsToInput($('#app-appt-patient'), [app_findListItemById(app_patientInfo, appt.patient.id, 'clientId')]);
      form_addTagsToInput($('#app-appt-clinician'), [app_findListItemById( app_clinicianInfo, appt.clinician.id, 'clientId')]);
      RenderUtil.render('component/basic_select_options', {options:app_apptTypes, collection:'app_apptTypes', choose:true}, function(s) { 
        $('#app-appt-type').html(s); 
        $('#app-appt-type').val(appt.apptType);
      });
    });
    
    $('#app-appt-submit').off('click').on("click", function (e) { 
      var dateString = $("#app-appt-date").find("input").val();
      app_handleUpdateAppt(e, dateString, id); 
    });
    $('#app-appt-delete').off('click').on("click", function (e) { app_deleteApptConfirm(e, id); });
  });
}



function app_handleNewAppt(e, dateString) {
  util_clearErrors();  
  var isValid = true;
  
  var apptStartValid = util_checkRegexp($.trim($("#app-appt-start").val()), util_timeRegexObj);
  if (apptStartValid == false) {
    util_showError($('#app-appt-start'), 'invalid time format');
    isValid = false;
  }
  
  var apptEndValid = util_checkRegexp($.trim($("#app-appt-end").val()), util_timeRegexObj);
  if (apptEndValid == false) {
    util_showError($('#app-appt-end'), 'invalid time format');
    isValid = false;
  }
  
  if($("#app-appt-clinician").val().length < 1) { 
    util_showError($('#app-appt-clinician'));
    isValid = false;
  }
  if(!$("#app-appt-patient").val() || $("#app-appt-patient").val().length < 1) { 
    util_showError($('#app-appt-patient'));
    isValid = false;
  }
  
  app_date = dateString;
  var startTimeString = dateString + " " + $('#app-appt-start').val();
  var endTimeString = dateString + " " + $('#app-appt-end').val();
  var startDate = new Date(startTimeString);
  var endDate = new Date(endTimeString);
  var startTimestamp = startDate.getTime();
  var endTimestamp = endDate.getTime();
 
  if (endTimestamp < startTimestamp) {
    isValid = false;
    util_showError($('#app-appt-end'), 'invalid time range');
  }
  else if (endTimestamp - startTimestamp < 900000) {
    isValid = false;
    util_showError($('#app-appt-end'), 'Appointment must be at least 15 minutes long.');
  }
  
  if (isValid == false) {
    return;
  }
  
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(),
    module:app_module,
    startTime: startTimeString,
    endTime: endTimeString,
    apptType: $('#app-appt-type').val(), 
    clinicianId: $('#app-appt-clinician').val(), 
    patientId: $('#app-appt-patient').val(),
    desc: $('#app-appt-desc').val() 
  });
  
  $.post("cal/newAppt", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_displayNotification('New appointment created.');
    $('#app-appt-start').val('');
    $('#app-appt-end').val('');
    $('#app-appt-patient').tagsinput('removeAll');
    $('#app-appt-clinician').tagsinput('removeAll');
    $('#app-appt-clinician').val('');
    $('#app-appt-patient').val('');
    $('#app-appt-desc').val('');
    $('#modal-appt').modal('hide');
    app_loadCalendar();
  });
}



function app_handleUpdateAppt(e, dateString, id) {
  util_clearErrors();  
  var isValid = true;
  
  var apptStartValid = util_checkRegexp($.trim($("#app-appt-start").val()), util_timeRegexObj);
  if (apptStartValid == false) {
    util_showError($('#app-appt-start'), 'invalid time format');
    isValid = false;
  }
  
  var apptEndValid = util_checkRegexp($.trim($("#app-appt-end").val()), util_timeRegexObj);
  if (apptEndValid == false) {
    util_showError($('#app-appt-end'), 'invalid time format');
    isValid = false;
  }
  if($("#app-appt-clinician").val().length < 1) { 
    util_showError($('#app-appt-clinician'));
    isValid = false;
  }

  if (isValid == false) {
    return;
  }
  
  app_date = dateString;
  var startTimeString = dateString + " " + $('#app-appt-start').val();
  var endTimeString = dateString + " " + $('#app-appt-end').val();
  var startDate = new Date(startTimeString);
  var endDate = new Date(endTimeString);
  var startTimestamp = startDate.getTime();
  var endTimestamp = endDate.getTime();
 
  if (endTimestamp < startTimestamp) {
    isValid = false;
    util_showError($('#app-appt-end'), 'invalid time range');
  }
  else if (endTimestamp - startTimestamp < 900000) {
    isValid = false;
    util_showError($('#app-appt-end'), 'Appointment must be at least 15 minutes long.');
  }
  
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(),
    module:app_module,
    id: id,
    clinicianId: $('#app-appt-clinician').val(), 
    patientId: $('#app-appt-patient').val(),
    startTime: startTimeString,
    endTime: endTimeString,
    apptType: $('#app-appt-type').val(), 
    desc: $('#app-appt-desc').val() 
  });
  
  $.post("cal/updateAppt", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    $('#app-appt-start').val('');
    $('#app-appt-end').val('');
    $('#app-appt-patient').tagsinput('removeAll');
    $('#app-appt-clinician').tagsinput('removeAll');
    $('#app-appt-clinician').val('');
    $('#app-appt-patient').val('');
    $('#app-appt-desc').val('');
    app_displayNotification('Appointment updated.');
    $('#modal-appt').modal('hide');
    app_loadCalendar();
  });
 
}


function app_moveAppt(event, jsEvent, ui, view) {
  var start = event.start;
  var end = event.end;
  var offset = new Date().getTimezoneOffset();
  start.add(offset,'m');
  end.add(offset,'m');
  app_date = dateFormat(start, 'mm/dd/yyyy');
  var startTimeString = dateFormat(start, 'mm/dd/yyyy') + " " + dateFormat(start, 'h:MM TT');
  var endTimeString = dateFormat(end, 'mm/dd/yyyy') + " " + dateFormat(end, 'h:MM TT');
  
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(),
    module:app_module,
    startTime: startTimeString,
    endTime: endTimeString,
    id: event.id
  });
  
  $.post("cal/changeApptTime", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_displayNotification('Appointment moved.');
    app_loadCalendar();
  });
}



function app_newApptForm(start, end) {
  var offset = new Date().getTimezoneOffset();
  start.add(offset,'m');
  end.add(offset,'m');
  var title = 'New Appointment';
  RenderUtil.render('dialog/appt', {title:title,deleteButton:null,submitButtonLabel:'Add'}, function(s) {
    $('#modal-appt').remove(); 
    $('#modals-placement').html(s);
    $('#modal-appt').modal('show'); 
    $('#app-appt-date').datetimepicker({ format: 'L', defaultDate: start });
    $('#modal-appt .input-timepicker').timepicker();
    
    if (app_calendarView == 'month') {
      var startTimeString = dateFormat(start, 'mm/dd/yyyy') + ' 9:00 AM';
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module, startTime: startTimeString, apptLengthInMinutes: 30 });
      $.post("cal/suggestApptSlot", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        $('#app-appt-start').val(dateFormat(parsedData.newApptStartTime, 'h:MM TT'));
        $('#app-appt-end').val(dateFormat(parsedData.newApptEndTime, 'h:MM TT'));
      });
    }
    else {
      $('#app-appt-start').val(dateFormat(start, 'h:MM TT'));
      $('#app-appt-end').val(dateFormat(end, 'h:MM TT'));
    }
    
    $('#app-appt-patient').tagsinput({
      itemValue: 'clientId',
      itemText: 'fullName',
      maxTags: 1,
      typeaheadjs: {
        name: 'patientInfo',
        displayKey: 'fullName',
        source: util_objectSubstringMatcher(app_patientInfo, 'fullName')
      }
    });
  
    $('#app-appt-clinician').tagsinput({
      itemValue: 'clientId',
      itemText: 'fullName',
      maxTags: 1,
      typeaheadjs: {
        name: 'clinicianInfo',
        displayKey: 'fullName',
        source: util_objectSubstringMatcher(app_clinicianInfo, 'fullName')
      }
    });
    
    $("#modal-appt .twitter-typeahead").css('display', 'inline');
    $("#modal-appt .bootstrap-tagsinput").css('width', '100%');
    $("#modal-appt .bootstrap-tagsinput").css('height', '30px');
    
    RenderUtil.render('component/basic_select_options', {options:app_apptTypes, collection:'app_apptTypes', choose:true}, function(s) { 
      $('#app-appt-type').html(s); 
    });
    
    $('#app-appt-submit').off('click').on("click", function (e) { 
      var dateString = $("#app-appt-date").find("input").val();
      app_handleNewAppt(e, dateString, offset); 
    });
  });
}



function app_resizeAppt(event, jsEvent, ui, view) {
  var start = event.start;
  var end = event.end;
  var offset = new Date().getTimezoneOffset();
  start.add(offset,'m');
  end.add(offset,'m');
  app_date = dateFormat(start, 'mm/dd/yyyy');
  var startTimeString = dateFormat(start, 'mm/dd/yyyy') + " " + dateFormat(start, 'h:MM TT');
  var endTimeString = dateFormat(end, 'mm/dd/yyyy') + " " + dateFormat(end, 'h:MM TT');
  
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(),
    module:app_module,
    startTime: startTimeString,
    endTime: endTimeString,
    id: event.id
  });
  
  $.post("cal/changeApptTime", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_displayNotification('Appointment length changed.');
    app_loadCalendar();
  });
}

