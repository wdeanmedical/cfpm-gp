
function pm_deleteTaskConfirm(e, id) {
  var args = {
    modalTitle:"Delete Task", 
    modalH3:"Delete Task?",
    modalH4:"",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function(){  
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module, id:id });
      $.post("cal/deleteTask", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        $('#modal-confirm').modal('hide'); 
        $('#modal-task').modal('hide');
        app_displayNotification('Task Deleted.');
        app_loadCalendar();
      }); 
    });
  });
}



function pm_editTaskForm(calEvent) {
  var start = calEvent.start;
  var end = calEvent.end;
  var id = calEvent.id;
  var offset = new Date().getTimezoneOffset();
  start.add(offset,'m');
  end.add(offset,'m');
  var title = 'Edit Task';
  RenderUtil.render('dialog/task', {title:title, deleteButton:'Delete',submitButtonLabel:'Update'}, function(s) {
    $('#modal-task').remove(); 
    $('#modals-placement').html(s);
    $('#modal-task').modal('show'); 
    $('#app-task-due-date').datetimepicker({ format: 'L', defaultDate: start });
    
    if(calEvent.allDay == false) { 
      $('#modal-task .input-timepicker').timepicker();
      $('#app-task-due-time').val(dateFormat(start, 'h:MM TT'));
      $('#app-task-due-time').removeAttr('readonly');
    }
    else {
      $('#app-task-due-time').attr('readonly', 'readonly');
      $('#app-task-due-time').val('');
      $('#app-task-due-time-btn').off('click').on('click', function(e) { 
        $('#modal-task .input-timepicker').timepicker();
        $('#app-task-due-time').removeAttr('readonly');
      });
    }
          
    $('#app-task-notes').val(calEvent.desc);
    
    var jsonData = JSON.stringify({ 
      sessionId: app_getSessionId(),
      module:app_module,
      id: calEvent.taskId
    });
  
    $.post("leadmgmt/getSalesLeadTask", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      if (!util_checkSessionResponse(parsedData)) return false;
      var salesLeadTask = parsedData.salesLeadTask
      var task = parsedData.calendarEvent;
      $("#app-task-users").val(salesLeadTask.salesAgentNames.join(', '));
    });
    
    $('#app-task-submit').off('click').on("click", function (e) { 
      var dateString = $("#app-task-date").find("input").val();
      pm_handleUpdateTask(e, dateString, id); 
    });
    $('#app-task-complete').off('click').on("click", function (e) { 
      var dateString = $("#app-task-date").find("input").val();
      pm_handleCompleteTask(e, dateString, calEvent.taskId); 
    });
    $('#app-task-delete').off('click').on("click", function (e) { pm_deleteTaskConfirm(e, id); });
  });
}



function pm_handleCompleteTask(e, dateString, id) {
  var isValid = true;
  pm_handleUpdateTask_clearErrors();
  
  if (isValid == false) {
    return;
  }
  
  var dueDate = $("#app-task-due-date").find("input").val();
  if ($("#app-task-due-time").is('[readonly]') == false) { dueDate += " " + $('#app-task-due-time').val()}
  
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(),
    module:app_module,
    id: id,
    startTime: dueDate,
    notes:$.trim( $('#app-task-notes').val())
  });
  
  $.post("leadmgmt/completeSalesLeadTask", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    pm_handleUpdateTask_clearForm();
    app_displayNotification('Task completed.');
    $('#modal-task').modal('hide');
    app_loadCalendar();
  });
}



function pm_handleUpdateTask_clearErrors() {
  $('#app-task-start-validation').css({visibility: "hidden"});
  $('#app-task-due-date-validation').css({visibility: "hidden"});
  $('#app-task-due-time-validation').css({visibility: "hidden"});
}



function pm_handleUpdateTask_clearForm() {
  $('#app-task-due-time').val('');
  $('#app-task-due-date').val('');
  $('#app-task-notes').val('');
  pm_handleUpdateTask_clearErrors();
}



function pm_handleUpdateTask(e, dateString, id) {
  var isValid = true;
  pm_handleUpdateTask_clearErrors();
  
  if (isValid == false) {
    return;
  }
  
  var dueDate = $("#app-task-due-date").find("input").val();
  if ($("#app-task-due-time").is('[readonly]') == false) { dueDate += " " + $('#app-task-due-time').val()}
  
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(),
    module:app_module,
    id: id,
    startTime: dueDate,
    notes:$.trim( $('#app-task-notes').val()),
    timeSpecified: $("#app-task-due-time").is('[readonly]') == false
  });
  
  $.post("cal/updateTask", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    pm_handleUpdateTask_clearForm();
    app_displayNotification('Task updated.');
    $('#modal-task').modal('hide');
    app_loadCalendar();
  });
}



function pm_moveTask(event, jsEvent, ui, view) {
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
  
  $.post("cal/changeTaskTime", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_displayNotification('Task moved.');
    app_loadCalendar();
  });
}



function pm_resizeTask(event, jsEvent, ui, view) {
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
  
  $.post("cal/changeTaskTime", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_displayNotification('Task length changed.');
    app_loadCalendar();
  });
}

