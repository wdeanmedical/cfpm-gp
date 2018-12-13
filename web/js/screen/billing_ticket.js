
function pm_addBillingTicketEntryDialog() {
  RenderUtil.render('dialog/pm/billing_ticket_entry', {title:'Add Billing Ticket Entry',deleteButton:null,submitButtonLabel:'Add'}, function(s) {
    $('#modal-billing-ticket-entry').remove(); 
    $('#modals-placement').html(s);
    $('#modal-billing-ticket-entry').modal('show'); 
    $('#ticket-date').mask('99/99/9999');
    
      
    $('#ticket-patient').tagsinput({
      itemValue: 'clientId',
      itemText: 'fullName',
      maxTags: 1,
      typeaheadjs: {
        name: 'patientInfo',
        displayKey: 'fullName',
        source: util_objectSubstringMatcher(app_patientInfo, 'fullName')
      }
    });
  
    $('#ticket-clinician').tagsinput({
      itemValue: 'clientId',
      itemText: 'fullName',
      maxTags: 1,
      typeaheadjs: {
        name: 'clinicianInfo',
        displayKey: 'fullName',
        source: util_objectSubstringMatcher(app_clinicianInfo, 'fullName')
      }
    });
    
    $("#modal-billing-ticket-entry .twitter-typeahead").css('display', 'inline');
    $("#modal-billing-ticket-entry .bootstrap-tagsinput").css('width', '100%');
    $("#modal-billing-ticket-entry .bootstrap-tagsinput").css('height', '30px');
  
    $('#billing-ticket-entry-submit').off('click').on("click", function (e) { 
      pm_saveBillingTicketEntry(CREATE);
    });
  });
}

function pm_editBillingTicketEntryDialog() {
  RenderUtil.render('dialog/pm/billing_ticket_entry', {title:'Edit Billing Ticket Entry',deleteButton:null,submitButtonLabel:'Update'}, function(s) {
    $('#modal-billing-ticket-entry').remove(); 
    $('#modals-placement').html(s);
    $('#modal-billing-ticket-entry').modal('show'); 
    $('#ticket-date').mask('99/99/9999');
          
    $('#ticket-patient').tagsinput({
      itemValue: 'clientId',
      itemText: 'fullName',
      maxTags: 1,
      typeaheadjs: {
        name: 'patientInfo',
        displayKey: 'fullName',
        source: util_objectSubstringMatcher(app_patientInfo, 'fullName')
      }
    });
  
    $('#ticket-clinician').tagsinput({
      itemValue: 'clientId',
      itemText: 'fullName',
      maxTags: 1,
      typeaheadjs: {
        name: 'clinicianInfo',
        displayKey: 'fullName',
        source: util_objectSubstringMatcher(app_clinicianInfo, 'fullName')
      }
    });
    
    
    form_addTagsToInput($('#ticket-patient'), [app_findListItemById(app_patientInfo, app_billingTicketEntry.patientId, 'clientId')]);
    form_addTagsToInput($('#ticket-clinician'), [app_findListItemById( app_clinicianInfo, app_billingTicketEntry.clinicianId, 'clientId')]);
    $("#modal-billing-ticket-entry .twitter-typeahead").css('display', 'inline');
    $("#modal-billing-ticket-entry .bootstrap-tagsinput").css('width', '100%');
    $("#modal-billing-ticket-entry .bootstrap-tagsinput").css('height', '30px');
    
    if(app_billingTicketEntry.date != null) { $('#ticket-date').val(dateFormat(app_billingTicketEntry.date, 'mm/dd/yyyy')); }
    $('#ticket-total-time').val(app_billingTicketEntry.totalTime);
    $('#ticket-dx').val(app_billingTicketEntry.dx);
    $('#ticket-service').val(app_billingTicketEntry.service);
    $('#ticket-copay').val(app_billingTicketEntry.copayMethod);

    $('#billing-ticket-entry-submit').off('click').on("click", function (e) { 
      pm_saveBillingTicketEntry(UPDATE);
    });
  });
}



function pm_findBillingTicketEntryById(id) {
  for (i=0;i<app_billingTicketEntries.length;i++) {
    if (app_billingTicketEntries[i].id == id) {
      return app_billingTicketEntries[i];
    }
  }
}

function app_billingTicketClassName() {
  return app_getEntityClassName("form.BillingTicket");
}

function app_billingTicketEntryClassName() {
  return app_getEntityClassName("form.BillingTicketEntry");
}

function pm_getBillingTicketEntries(patientId, clinicianId, startDate, endDate) {
  var jsonData = JSON.stringify({ 
    clinicianId: clinicianId,
    patientId: patientId,
    startDate: startDate,
    endDate: endDate,
    formClassName: app_billingTicketEntryClassName(),
    sessionId: app_getSessionId(), 
    module:app_module 
  });
  app_post("app/getBillingTicketEntries", jsonData, function(parsedData) {
    app_billingTicketEntries = parsedData.list;
    RenderUtil.render('component/simple_data_table', 
     {items:app_billingTicketEntries, 
      title:'Billing Ticket Entries', 
      tableName:'billing-ticket-entries-list', 
      clickable:true, 
      columns:[
        {title:'Client', field:'patientName', type:'simple'},
        {title:'Clinician', field:'clinicianName', type:'simple'},
        {title:'Date', field:'date', type:'date'},
        {title:'Total Time', field:'totalTime', type:'simple'},
        {title:'Dx', field:'dx', type:'simple'},
        {title:'Service', field:'service', type:'simple'},
        {title:'Copay/Method', field:'copayMethod', type:'simple'}
      ]}, function(s) {
      $('#billing-ticket-entries-list').html(s);
      $('.clickable-table-row').click( function(e) { 
        $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        app_billingTicketEntryId = $(this).attr('name');
        app_billingTicketEntry = pm_findBillingTicketEntryById(app_billingTicketEntryId);
   
        $('#app-delete-billing-ticket-entry-btn').removeAttr('disabled');
        $('#app-edit-billing-ticket-entry-btn').removeAttr('disabled');
      });
    });
  });
}

function app_loadBillingTicket() {
  $("#bt-clinician-name").val(app_clientFullName);
  $('#bt-week-of').datepicker();
  app_renderBillingTicketEntries(app_billingTicket.entries, NOT_CLOSED);
}


function app_renderBillingTicket() {
  var formPrefix="bt"
   var args = { 
     formPrefix: formPrefix, 
     formClassName: app_billingTicketClassName()
    };
 RenderUtil.render('screen/pm/billing_ticket_screen', args, function(s) {
  $("#patient-chart-item-screen").html(s);
  app_showScreen('Billing Ticket', app_patientChartItemCache);
  app_chartItemStack($('#billing-ticket-screen'), true);
  console.log('enable print screen!!!')
  form_enablePrintScreen(formPrefix, "Billing Ticket")
  var jsonData = JSON.stringify({ 
    patientId: app_patient.id, sessionId: app_getSessionId(),
    formClassName: app_billingTicketClassName()
  });
  app_post("app/getOrCreateBillingTicket", jsonData, function(parsedData) {
     app_billingTicket = parsedData.object;
     app_loadBillingTicket();
   });
 });
}

function pm_saveBillingTicketEntry(mode) { 
  var isValid = true;
  util_clearErrors();  
  
  if (util_isFieldEmpty('#ticket-patient')) {
    util_showError($('#ticket-patient'));
    isValid = false;
  }
  if (util_isFieldEmpty('#ticket-clinician')) {
    util_showError($('#ticket-clinician'));
    isValid = false;
  }
  if (util_isFieldEmpty('#ticket-date')) {
    util_showError($('#ticket-date'));
    isValid = false;
  }
  if (util_isFieldEmpty('#ticket-total-time')) {
    util_showError($('#ticket-total-time'));
    isValid = false;
  }
  if (util_isFieldEmpty('#ticket-dx')) {
    util_showError($('#ticket-dx'));
    isValid = false;
  }
  if (util_isFieldEmpty('#ticket-service')) {
    util_showError($('#ticket-service'));
    isValid = false;
  }
  if (util_isFieldEmpty('#ticket-copay')) {
    util_showError($('#ticket-copay'));
    isValid = false;
  }
  
  if (isValid == false) {
    return;
  }
  
  var jsonData = JSON.stringify({
    id: mode == UPDATE ? app_billingTicketEntryId : null,
    sessionId: app_getSessionId(),
    patientId: $('#ticket-patient').val(),
    clinicianId: $('#ticket-clinician').val(),
    date: $('#ticket-date').val(),
    totalTime: $('#ticket-total-time').val(),
    dx: $('#ticket-dx').val(),
    service: $('#ticket-service').val(),
    copayMethod: $('#ticket-copay').val(),
    formClassName: app_billingTicketEntryClassName(),
    mode: mode,
    module: app_module
   });
   
   app_post('app/saveBillingTicketEntry', jsonData, function(parsedData) {
     app_displayNotification('Billing Ticket Entry '+mode+'d.');
     $('#modal-billing-ticket-entry').modal('hide'); 
     pm_getBillingTicketEntries(null, null, null, null);
  });
}



