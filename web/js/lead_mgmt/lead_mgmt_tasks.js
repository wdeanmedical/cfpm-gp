
function pm_clearTaskForm() {
  util_clearErrors();
  $('#lead-mgmt-task-due-date').val('');
  $('#lead-mgmt-task-name').val('');
  $('#lead-mgmt-task-notes').val('');
}



function pm_completeSalesLeadTask() {  
  var dueDate = $.trim($('#lead-mgmt-task-due-date').val());
  if ($("#lead-mgmt-task-due-time").is('[readonly]') == false) { dueDate += " " + $('#lead-mgmt-task-due-time').val()}
  var jsonData = JSON.stringify({     
    sessionId: app_getSessionId(),
    id: pm_currentSalesLeadTaskId,
    name:$.trim($('#lead-mgmt-task-name').val()),
    dueDate: dueDate,
    notes:$.trim($('#lead-mgmt-task-notes').val()),
    salesAgentIdsString:$.trim($('#lead-mgmt-task-user').val())
  });
  $.post("leadmgmt/completeSalesLeadTask", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    $('#lead-mgmt-new-task-panel').hide();
    app_displayNotification('Sales Lead Task Completed.');
    pm_getSalesLeadTasks();
    pm_getSalesLeadActions();
  });  
}



function pm_deleteSalesLeadTaskConfirm(e, id) {
  var args = {
    modalTitle:"Delete Sales Lead Task", 
    modalH3:"Delete Sales Lead Task?",
    modalH4:"",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').html(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').off('click').on('click', function(){  
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), id: id });
      $.post("leadmgmt/deleteSalesLeadTask", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        $('#modal-confirm').modal('hide').on('hidden.bs.modal', function (e) {
          $('#modal-confirm').remove();
        });
        $('#modal-event').modal('hide');
        app_displayNotification('Sales Lead Task Deleted.');
        pm_getSalesLeadTasks();
      }); 
    });
 });
}



function pm_getSalesLeadTasks() {
  var jsonData = JSON.stringify({ id: pm_currentSalesLeadId, sessionId: app_getSessionId() });
  $.post("leadmgmt/getSalesLeadTasks", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    pm_currentSalesLeadTasks = parsedData.list;
    pm_loadSalesLeadTasks();
  });
}



function pm_loadSalesLeadTaskForm() {
  for (var i=0;i<pm_currentSalesLeadTasks.length;i++) {
    if (pm_currentSalesLeadTasks[i].id == pm_currentSalesLeadTaskId) {
      pm_clearTaskForm();
      pm_currentSalesLeadTask = pm_currentSalesLeadTasks[i];
      if(pm_currentSalesLeadTask.dueDate != null) { 
        $('#lead-mgmt-task-due-date').val(dateFormat(pm_currentSalesLeadTask.dueDate, 'mm/dd/yyyy'));
      }

      if(pm_currentSalesLeadTask.timeSpecified == true) { 
        $('#lead-mgmt-task-due-time-enabled').prop("checked", true);
        $('#lead-mgmt-task-due-time-btn').removeAttr('disabled');
        $('#lead-mgmt-new-task-panel .input-timepicker').timepicker();
        $('#lead-mgmt-task-due-time').val(dateFormat(pm_currentSalesLeadTask.dueDate, 'h:MM TT'));
      }
      else {
        $('#lead-mgmt-task-due-time-enabled').prop("checked", false);
        $('#lead-mgmt-task-due-time-btn').attr('disabled', 'disabled');
        $('#lead-mgmt-task-due-time').attr('readonly', 'readonly');
        $('#lead-mgmt-task-due-time').val('');
      }
     
      $('#lead-mgmt-task-due-time-enabled').off('click').on('click', function(e) { 
        if (pm_currentSalesLeadTask.timeSpecified == false) {
          pm_currentSalesLeadTask.timeSpecified = true;
          $('#lead-mgmt-task-due-time-btn').removeAttr('disabled');
          $('#lead-mgmt-new-task-panel .input-timepicker').timepicker();
          $('#lead-mgmt-task-due-time').removeAttr('readonly');
        }
        else {
          pm_currentSalesLeadTask.timeSpecified = false;
          $('#lead-mgmt-task-due-time-btn').attr('disabled', 'disabled');
          $('#lead-mgmt-task-due-time').attr('readonly', 'readonly');
          $('#lead-mgmt-task-due-time').val('');
        }
      });
        
      $('#lead-mgmt-task-name').val(pm_currentSalesLeadTask.name);
      $('#lead-mgmt-task-notes').val(pm_currentSalesLeadTask.notes);
      $('#lead-mgmt-new-task-panel').show();
      $('#lead-mgmt-task-save-btn').hide();
      $('#lead-mgmt-task-update-btn').show();
      $('#lead-mgmt-task-complete-btn').show();
      $('#lead-mgmt-task-delete-btn').show();
      $("#lead-mgmt-task-user").selectpicker('val', pm_currentSalesLeadTask.salesAgentIds);
      $("#lead-mgmt-task-user").selectpicker('render');
      $('#lead-mgmt-task-update-btn').off('click').on('click', function(){ if (pm_validateSalesLeadTaskForm()){ pm_updateSalesLeadTask(); }});
      $('#lead-mgmt-task-delete-btn').off('click').on('click', function(e){ pm_deleteSalesLeadTaskConfirm(e, pm_currentSalesLeadTaskId); });
      $('#lead-mgmt-task-complete-btn').off('click').on('click', function(){ pm_completeSalesLeadTask(); });
    }
  }
}



function pm_loadSalesLeadTasks() {
  var args =  {items:pm_currentSalesLeadTasks, 
  title:'Tasks', 
  tableName:'lead-mgmt-task-list', 
  clickable:true, 
  columns:[
   {title:'Due Date', field:'dueDate', type:'date'},
   {title:'Name', field:'name', type:'simple'},
   {title:'Notes', field:'notes', type:'simple'}
  ]};
  RenderUtil.render('component/simple_data_table', args, function(s) {
    $('#lead-mgmt-task-list').html(s);
    $('#lead-mgmt-new-task-panel').hide();
    $('.clickable-table-row').on('click',  function(e) { 
      $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
      app_handleClickableRow(e); 
    });
  });
}



function pm_newTaskForm() {
  pm_clearTaskForm();
  $('#lead-mgmt-new-task-panel').show();
  $('#lead-mgmt-task-save-btn').show();
  $('#lead-mgmt-task-update-btn').hide();
  $('#lead-mgmt-task-complete-btn').hide();
  $('#lead-mgmt-task-delete-btn').hide();
  $('#lead-mgmt-task-due-time-btn').attr('disabled','disabled');
  $('#lead-mgmt-task-due-time').attr('readonly','readonly');
  $('#lead-mgmt-task-due-time').val('');
  
  $('#lead-mgmt-task-due-time-enabled').off('click').on('click', function(e) { 
    if ($("#lead-mgmt-task-due-time").is('[readonly]')) {  
      $('#lead-mgmt-task-due-time-btn').removeAttr('disabled');
      $('#lead-mgmt-task-due-time').removeAttr('readonly');
      $('#lead-mgmt-new-task-panel .input-timepicker').timepicker();
    }
    else {
      $('#lead-mgmt-task-due-time-btn').attr('disabled','disabled');
      $('#lead-mgmt-task-due-time').attr('readonly','readonly');
      $('#lead-mgmt-task-due-time').val('');
    }
  });
}



function pm_saveSalesLeadTask() {
  var dueDate = $.trim($('#lead-mgmt-task-due-date').val());
  if ($("#lead-mgmt-task-due-time").is('[readonly]') == false) { dueDate += " " + $('#lead-mgmt-task-due-time').val()}
  var jsonData = JSON.stringify({     
    sessionId: app_getSessionId(),
    id: pm_currentSalesLeadId,
    name:$.trim($('#lead-mgmt-task-name').val()),
    dueDate: dueDate,
    notes:$.trim( $('#lead-mgmt-task-notes').val()),
    timeSpecified: $("#lead-mgmt-task-due-time").is('[readonly]') == false,
    salesAgentIdsString: $.trim($('#lead-mgmt-task-user').val())
  });
  $.post("leadmgmt/saveNewSalesLeadTask", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    $('#lead-mgmt-new-task-panel').hide();
    pm_getSalesLeadTasks();
  });  
}



function pm_updateSalesLeadTask() {  
  var dueDate = $.trim($('#lead-mgmt-task-due-date').val());
  if ($("#lead-mgmt-task-due-time").is('[readonly]') == false) { dueDate += " " + $('#lead-mgmt-task-due-time').val()}
  var jsonData = JSON.stringify({     
    sessionId: app_getSessionId(),
    salesLeadId: pm_currentSalesLeadId,
    id: pm_currentSalesLeadTaskId,
    name:$.trim($('#lead-mgmt-task-name').val()),
    dueDate: dueDate,
    notes:$.trim($('#lead-mgmt-task-notes').val()),
    timeSpecified: pm_currentSalesLeadTask.timeSpecified,
    salesAgentIdsString:$.trim($('#lead-mgmt-task-user').val())
  });
  $.post("leadmgmt/updateSalesLeadTask", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    $('#lead-mgmt-new-task-panel').hide();
    pm_getSalesLeadTasks();
  });  
}



function pm_validateSalesLeadTaskForm() {
  var isValid = true;
  util_clearErrors();
  if (util_isFieldEmpty('#lead-mgmt-task-due-date')) {
    util_showError($('#lead-mgmt-task-due-date'));
    isValid = false; 
  }
  if (util_isFieldEmpty('#lead-mgmt-task-name')) {
    util_showError($('#lead-mgmt-task-name'));
    isValid = false; 
  }
  if (!util_isFieldEmpty('#lead-mgmt-task-due-date')){
    if (util_isDate($.trim($('#lead-mgmt-task-due-date').val())) == false) {
      util_showError($('#lead-mgmt-task-due-date'), 'must be valid date');
      isValid = false; 
    } 
  }
  return isValid;
}
