
function pm_clearActionForm() {
  util_clearErrors();  
  $('#lead-mgmt-action-date').val('');
  $('#lead-mgmt-action-name').val('');
  $('#lead-mgmt-action-email-status').val('');
  $('#lead-mgmt-action-call-status').val('');
  $('#lead-mgmt-action-notes').val('');
}



function pm_deleteSalesLeadActionConfirm(e, id) {
  var args = {
    modalTitle:"Delete Sales Lead Action", 
    modalH3:"Delete Sales Lead Action?",
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
      $.post("leadmgmt/deleteSalesLeadAction", {data:jsonData}, function(data) {
       if (!util_checkSessionResponse(parsedData)) return false;
        $('#modal-confirm').modal('hide').on('hidden.bs.modal', function (e) {
          $('#modal-confirm').remove();
        });
        $('#modal-event').modal('hide');
        app_displayNotification('Sales Lead Action Deleted.');
        pm_getSalesLeadActions();
      }); 
    });
  });
}



function pm_getSalesLeadActions() {
  var jsonData = JSON.stringify({ id: pm_currentSalesLeadId, sessionId: app_getSessionId() });
  $.post("leadmgmt/getSalesLeadActions", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    pm_currentSalesLeadActions = parsedData.list;
    pm_loadSalesLeadActions();
  });
}



function pm_loadSalesLeadActionForm() {
  for (var i=0;i<pm_currentSalesLeadActions.length;i++) {
    if (pm_currentSalesLeadActions[i].id == pm_currentSalesLeadActionId) {
      pm_clearActionForm();
      var pm_currentSalesLeadAction = pm_currentSalesLeadActions[i];
      if(pm_currentSalesLeadAction.date != null) { 
        $('#lead-mgmt-action-date').val(dateFormat(pm_currentSalesLeadAction.date, 'mm/dd/yyyy'));
      }
      $('#lead-mgmt-action-name').val(pm_currentSalesLeadAction.name);
      $('#lead-mgmt-action-notes').val(pm_currentSalesLeadAction.notes);
      $('#lead-mgmt-new-action-panel').show();
      $('#lead-mgmt-action-save-btn').hide();
      $('#lead-mgmt-action-update-btn').show();
      $('#lead-mgmt-action-delete-btn').show();
      $("#lead-mgmt-action-user").val(app_clientFullName);
      $('#lead-mgmt-action-update-btn').off('click').on('click', function(){ if(pm_validateSalesLeadActionForm()){pm_updateSalesLeadAction(); }});
      $('#lead-mgmt-action-delete-btn').off('click').on('click', function(e){ pm_deleteSalesLeadActionConfirm(e, pm_currentSalesLeadActionId); });
      return;
    }
  }
}



function pm_loadSalesLeadActions() {
  var args =  {
  items:pm_currentSalesLeadActions, 
  title:'Actions', 
  tableName:'lead-mgmt-action-list', 
  clickable:true, 
  columns:[
   {title:'Date', field:'date', type:'date'},
   {title:'Action', field:'name', type:'simple'},
   {title:'Notes', field:'notes', type:'simple'}
  ]};
  RenderUtil.render('component/simple_data_table', args, function(s) {
    $('#lead-mgmt-action-list').html(s);
    $('#lead-mgmt-new-action-panel').hide();
    $('.clickable-table-row').on('click',  function(e){ 
      $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
      app_handleClickableRow(e); 
    });
  });
}



function pm_newActionForm() {
  pm_clearActionForm();
  $('#lead-mgmt-new-action-panel').show();
  $('#lead-mgmt-action-save-btn').show();
  $('#lead-mgmt-action-update-btn').hide();
  $('#lead-mgmt-action-delete-btn').hide();
  $('#lead-mgmt-action-user').val(app_clientFullName);
  $('#lead-mgmt-action-date').val(dateFormat(new Date(), 'mm/dd/yyyy'));
}



function pm_saveSalesLeadAction(){  
  var jsonData = JSON.stringify({     
    sessionId: app_getSessionId(),
    id: pm_currentSalesLeadId,
    name:$.trim($('#lead-mgmt-action-name').val()),
    notes:$.trim($('#lead-mgmt-action-notes').val()),
  });
  $.post("leadmgmt/saveNewSalesLeadAction", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    $('#lead-mgmt-new-action-panel').hide();
    pm_getSalesLeadActions();
  });  
}



function pm_updateSalesLeadAction() {
  var jsonData = JSON.stringify({     
    sessionId: app_getSessionId(),
    salesLeadId: pm_currentSalesLeadId,
    id: pm_currentSalesLeadActionId,
    name:$.trim($('#lead-mgmt-action-name').val()),
    notes:$.trim($('#lead-mgmt-action-notes').val()),
  });
  $.post("leadmgmt/updateSalesLeadAction", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    $('#lead-mgmt-new-action-panel').hide();
    pm_getSalesLeadActions();
  });  
}



function pm_validateSalesLeadActionForm(){
  var isValid = true;
  util_clearErrors();
  if (util_isFieldEmpty('#lead-mgmt-action-name')) {
    util_showError($('#lead-mgmt-action-name'));
    isValid = false; 
  }
  return isValid;
}
