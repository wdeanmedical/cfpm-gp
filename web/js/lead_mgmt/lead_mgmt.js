
function pm_clearSalesLeadSearch() {
  $('#sales-lead-search-first-name').val('');
  $('#sales-lead-search-middle-name').val('');
  $('#sales-lead-search-last-name').val('');
  $('#sales-lead-search-gender').val('');
  $('#sales-lead-search-dob').val('');
  $('#sales-lead-search-city').val('');
  pm_getRecentSalesLeads();  
}



function pm_convertSalesLeadToPatient() {
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(), 
    id: pm_currentSalesLead.id,
    title: $.trim($('#lead-mgmt-title').val()),
    firstName: $.trim($('#lead-mgmt-first-name').val()),
    middleName: $.trim($('#lead-mgmt-middle-name').val()),
    lastName: $.trim($('#lead-mgmt-last-name').val()),
    genderId: $.trim($('#lead-mgmt-gender').val()),
    ssn: $.trim($('#lead-mgmt-ssn').val()),
    driversLicense: $.trim($('#lead-mgmt-drivers-license').val()),
    occupation: $.trim($('#lead-mgmt-occupation').val()),
    email: $.trim($('#lead-mgmt-email').val()),
    primaryPhone: $.trim($('#lead-mgmt-primary-phone').val()),
    secondaryPhone: $.trim($('#lead-mgmt-secondary-phone').val()),
    dob: $.trim($('#lead-mgmt-dob').val()),
    streetAddress1: $.trim($('#lead-mgmt-primary-address').val()),
    city: $.trim($('#lead-mgmt-primary-city').val()),
    usStateId: $.trim($('#lead-mgmt-primary-us-state').val()),
    postalCode: $.trim($('#lead-mgmt-primary-postal-code').val()),
    prepaymentAmount: $.trim($('#new-patient-prepayment-amount').val()),
    sendPortalInvite: $('#new-patient-send-portal-invite').prop('checked') == true,
    forms: $('input[name=forms]:checked').map(function() {return this.value;}).get().join(',')
  });
  $.post("leadmgmt/convertSalesLeadToPatient", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    $('#lead-mgmt-screen').hide();
    pm_salesLeadSearchDialog();
    app_displayNotification('The Sales Lead was successfully converted to a ' + app_practiceClientProperties['app.patient_label'] + '.');
  }); 
}



function pm_deleteSalesLeadConfirm(e, id) {
  e.preventDefault();
  var args = {
    modalTitle:"Delete Sales Lead", 
    modalH3:"Delete Sales Lead?",
    modalH4:"",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').html(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').on('click', function(){  
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), id: id });
      $.post("leadmgmt/deleteSalesLead", {data:jsonData}, function(data) {
       var parsedData = $.parseJSON(data);
       if (!util_checkSessionResponse(parsedData)) return false;
        $('#modal-confirm').modal('hide').on('hidden.bs.modal', function (e) {
          $('#modal-confirm').remove();
        });
        $('#lead-mgmt-screen').hide();
        $('#modal-event').modal('hide');
        app_displayNotification('Sales Lead Deleted.');
        pm_salesLeadSearchDialog();  
      }); 
    });
  });
}



function app_getCliniciansByLocation(checkOffSelectedClinicians) {
  var cliniciansByLocation = [];

  if (pm_selectedLocationId == null && checkOffSelectedClinicians == true) {
    return;
  }
 
  for (var i=0;i<app_clinicians.length;i++) {
    var clinician = app_clinicians[i];
    for (var j=0;j<app_clinicianLocations.length;j++) {
      var clinicianLocation = app_clinicianLocations[j];
      if (clinicianLocation.clinicianId == clinician.id && clinicianLocation.facility.id == pm_selectedLocationId) {
        cliniciansByLocation.push(clinician);
      }
    }
  }
  RenderUtil.render('component/person_select_options_multiple', {options:cliniciansByLocation}, function(s) {
    $("#lead-mgmt-clinician, #lead-mgmt-action-clinician, #lead-mgmt-task-clinician, #new-lead-clinician, #new-lead-action-clinician, #new-lead-task-clinician").html(s);
    $("#lead-mgmt-clinician, #lead-mgmt-action-clinician, #lead-mgmt-task-clinician, #new-lead-clinician, #new-lead-action-clinician, #new-lead-task-clinician").selectpicker('refresh');
    if (checkOffSelectedClinicians) {
       $("#lead-mgmt-clinician, #new-lead-clinician").selectpicker('val', pm_currentSalesLead.salesAgentIds);
       $("#lead-mgmt-clinician, #new-lead-clinician").selectpicker('render');
    }
  });
}



function pm_getRecentSalesLeads() {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId() });
  $.post("leadmgmt/getRecentSalesLeads", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    pm_salesLeads = parsedData.list;
    RenderUtil.render('component/simple_data_table', 
     {items:pm_salesLeads, 
      title:'Recent Sales Leads', 
      tableName:'sales-lead-search-results', 
      clickable:true, 
      columns:[
        {title:'Full Name', field:'firstName', type:'patient'},
        {title:'Date of Birth', field:'dob', type:'date'},
        {title:'City', field:'city', type:'simple'}
      ]}, function(s) {
      $('#sales-lead-search-results').html(s);
      $('#sales-lead-search-results-title').html("Recent Sales Leads");
      $('.clickable-table-row').on('click', function(e){ 
        $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        pm_currentSalesLeadId = $(this).attr('data-row-key');
        $('#modal-sales-lead-search').modal('hide'); 
        pm_salesLeadScreen(); 
      });
    });
  });
}



function pm_getSalesLead() {
  var jsonData = JSON.stringify({ id: pm_currentSalesLeadId, sessionId: app_getSessionId() });
  $.post("leadmgmt/getSalesLead", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    pm_currentSalesLead = parsedData.salesLead;
    pm_currentSalesLeadActions = pm_currentSalesLead.actions;
    pm_currentSalesLeadTasks = pm_currentSalesLead.tasks;
    pm_currentSalesLeadFullName = util_buildFullName(pm_currentSalesLead.firstName, pm_currentSalesLead.middleName, pm_currentSalesLead.lastName);
    $('#lead-mgmt-sales-lead-name').html(pm_currentSalesLeadFullName);
    pm_loadSalesLeadForm();
  });
}



function pm_getUsersByLocation(checkOffSelectedUsers) {
  var usersByLocation = [];

  if (pm_selectedLocationId == null && checkOffSelectedUsers == true) {
    return;
  }
 
  for (var i=0;i<app_users.length;i++) {
    var user = app_users[i];
    for (var j=0;j<app_userLocations.length;j++) {
      var userLocation = app_userLocations[j];
      if (userLocation.userId == user.id && userLocation.facility.id == pm_selectedLocationId) {
        usersByLocation.push(user);
      }
    }
  }
  RenderUtil.render('component/person_select_options_multiple', {options:usersByLocation}, function(s) {
    $("#lead-mgmt-user, #lead-mgmt-task-user, #new-lead-user, #new-lead-task-user").html(s);
    $("#lead-mgmt-user, #lead-mgmt-task-user, #new-lead-user, #new-lead-task-user").selectpicker('refresh');
    if (checkOffSelectedUsers) {
       $("#lead-mgmt-user, #new-lead-user").selectpicker('val', pm_currentSalesLead.salesAgentIds);
       $("#lead-mgmt-user, #new-lead-user").selectpicker('render');
    }
  });
}



function pm_handleSalesLeadConversion(e) {
  var salesLeadFullName = util_buildFullName(pm_currentSalesLead.firstName, pm_currentSalesLead.middleName, pm_currentSalesLead.lastName);
  var cancelButton = 'Cancel';
  var okButton = 'Confirm';
  var modalH4Text = "";
  var modalBodyText = "";
  var missingFields = []; 
  if (util_isFieldEmpty('#lead-mgmt-first-name')) { missingFields.push('First Name'); }
  if (util_isFieldEmpty('#lead-mgmt-last-name')) { missingFields.push('Last Name'); }
  if (util_isFieldEmpty('#lead-mgmt-ssn') && util_isFieldEmpty('#lead-mgmt-drivers-license')) { 
    missingFields.push('SSN or Drivers License'); 
  }
  if (util_isFieldEmpty('#lead-mgmt-dob')) { missingFields.push('Date of Birth'); }
  if (util_isFieldEmpty('#lead-mgmt-gender')) { missingFields.push('Gender'); }
  if (util_isFieldEmpty('#lead-mgmt-primary-address')) { missingFields.push('Street Address'); }
  if (util_isFieldEmpty('#lead-mgmt-primary-city')) { missingFields.push('City'); }
  if (util_isFieldEmpty('#lead-mgmt-primary-us-state')) { missingFields.push('State'); }
  if (util_isFieldEmpty('#lead-mgmt-primary-postal-code')) { missingFields.push('ZIP Code'); }
  if (util_isFieldEmpty('#lead-mgmt-primary-phone')) { missingFields.push('Primary Phone'); }
  if (util_isFieldEmpty('#lead-mgmt-email')) { missingFields.push('Email'); }
 
  if (missingFields.length > 0) {
    modalH4Text += "The following fields are required in this process:";
    modalBodyText += "<ul>";
    for (var i=0;i<missingFields.length;i++) {
      modalBodyText += "<li>" + missingFields[i] + "</li>";
    }
    modalBodyText += "</ul>";
    modalBodyText +="<h4>Additionally review the " + app_practiceClientProperties['app.patient_label'] + " Intake options now presented.</h4>";
    
    okButton = 'Ok';
  }
  
  var args = {
    modalTitle: app_practiceClientProperties['app.patient_label'] + " Conversion", 
    modalH3:"Convert " + salesLeadFullName + " to a " + app_practiceClientProperties['app.patient_label'] + "?",
    modalH4: modalH4Text,
    modalBodyText: modalBodyText,
    cancelButton: cancelButton,
    okButton: okButton
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').html(s);
    $('#modal-confirm').modal('show'); 
    
    $('#app-modal-confirmation-btn').click(function() {  
      $('#modal-confirm').modal('hide'); 
      $('#lead-mgmt-ssn').removeAttr('disabled');
      $('#lead-mgmt-drivers-license').removeAttr('disabled');
      $('.field-is-required-for-conversion').css({visibility: "visible"});
      $('#lead-mgmt-patient-intake-setup').show();
      if (missingFields.length == 0) {
        pm_convertSalesLeadToPatient();
      }
    });
    
    $('#app-modal-cancel-btn').click(function() {  
      $('#lead-mgmt-ssn').attr('disabled', 'disabled');
      $('#lead-mgmt-drivers-license').attr('disabled', 'disabled');
    });
      
  });
}



function pm_initLeadMgmtTypeAheads() {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId() });
  $.post("leadmgmt/getSalesLeadSearchTypeAheads", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    var salesLeadFirstNameSearchTypeAheads = parsedData.salesLeadFirstNameSearchTypeAheads;
    var salesLeadMiddleNameSearchTypeAheads = parsedData.salesLeadMiddleNameSearchTypeAheads;
    var salesLeadLastNameSearchTypeAheads = parsedData.salesLeadLastNameSearchTypeAheads;
    var salesLeadCitySearchTypeAheads = parsedData.salesLeadCitySearchTypeAheads;
    $('#sales-lead-search-first-name').typeahead(
      { hint: true, highlight: true, minLength: 1 },
      { name: 'firstName', displayKey: 'value', source: util_substringMatcher(salesLeadFirstNameSearchTypeAheads.firstName)
    });
    $('#sales-lead-search-middle-name').typeahead(
      { hint: true, highlight: true, minLength: 1 },
      { name: 'middleName', displayKey: 'value', source: util_substringMatcher(salesLeadMiddleNameSearchTypeAheads.middleName)
    });
    $('#sales-lead-search-last-name').typeahead(
      { hint: true, highlight: true, minLength: 1 },
      { name: 'lastName', displayKey: 'value', source: util_substringMatcher(salesLeadLastNameSearchTypeAheads.lastName)
    });
    $('#sales-lead-search-city').typeahead(
      { hint: true, highlight: true, minLength: 1 },
      { name: 'city', displayKey: 'value', source: util_substringMatcher(salesLeadCitySearchTypeAheads.city)
    });
  });      
}



function pm_loadSalesLeadForm() {
  pm_loadSalesLeadActions();
  pm_loadSalesLeadTasks();
  
  $('.lead-mgmt-action-date, .lead-mgmt-task-date').datepicker();
  $('.lead-mgmt-action-date, .lead-mgmt-task-date').datepicker('update', new Date());
  RenderUtil.render('component/basic_select_options', {options:app_usStates, collection:'app_usStates', choose:true}, function(s) { 
    $(".lead-mgmt-primary-us-state, .lead-mgmt-secondary-us-state").html(s); 
    if(pm_currentSalesLead.usState != null){ $('#lead-mgmt-primary-us-state').val(pm_currentSalesLead.usState.id); }
    if(pm_currentSalesLead.altUSState != null){ $('#lead-mgmt-secondary-us-state').val(pm_currentSalesLead.altUSState.id); }
  });
  RenderUtil.render('component/basic_select_options', {options:pm_salesLeadStatuses, collection:'pm_salesLeadStatuses', choose:true}, function(s) { 
    $(".lead-mgmt-status").html(s); 
    if(pm_currentSalesLead.status != null){ $('#lead-mgmt-status').val(pm_currentSalesLead.status.id); }
  });
  RenderUtil.render('component/basic_select_options', {options:pm_salesLeadStages, collection:'pm_salesLeadStages', choose:true}, function(s) { 
    $(".lead-mgmt-stage").html(s); 
    if(pm_currentSalesLead.stage != null){ $('#lead-mgmt-stage').val(pm_currentSalesLead.stage.id); }
  });
  RenderUtil.render('component/basic_select_options', {options:pm_salesLeadAgeRanges, collection:'pm_salesLeadAgeRanges', choose:true}, function(s) { 
    $(".lead-mgmt-age-range").html(s); 
    if(pm_currentSalesLead.ageRange != null){ $('#lead-mgmt-age-range').val(pm_currentSalesLead.ageRange.id); }
  });
  RenderUtil.render('component/basic_select_options', {options:pm_salesLeadSources, collection:'pm_salesLeadSources', choose:true}, function(s) { 
    $(".lead-mgmt-source").html(s); 
    if(pm_currentSalesLead.source != null){ $('#lead-mgmt-source').val(pm_currentSalesLead.source.id); }
  });
  RenderUtil.render('component/basic_select_options', {options:pm_networkMarketingSources, collection:'pm_networkMarketingSources', choose:true}, function(s) { 
    $(".lead-mgmt-network-marketing-source").html(s); 
    if(pm_currentSalesLead.networkMarketingSource != null){ $('#lead-mgmt-network-marketing-source').val(pm_currentSalesLead.networkMarketingSource.id); }
  });
  RenderUtil.render('component/basic_select_options', {options:pm_referralSourceTypes, collection:'pm_referralSourceTypes', choose:true}, function(s) { 
    $(".lead-mgmt-source-type").html(s); 
    if(pm_currentSalesLead.sourceType != null){ $('#lead-mgmt-source-type').val(pm_currentSalesLead.sourceType.id); }
  });
  RenderUtil.render('component/basic_select_options', {options:pm_addressTypes, collection:'pm_addressTypes', choose:true}, function(s) { 
    $(".lead-mgmt-primary-address-type, .lead-mgmt-secondary-address-type").html(s); 
    if(pm_currentSalesLead.addressType != null){ $('#lead-mgmt-primary-address-type').val(pm_currentSalesLead.addressType.id); }
    if(pm_currentSalesLead.altAddressType != null){ $('#lead-mgmt-secondary-address-type').val(pm_currentSalesLead.altAddressType.id); }
  });
  RenderUtil.render('component/basic_select_options', {options:pm_salesLeadGoals, collection:'pm_salesLeadGoals', choose:true}, function(s) { 
    $(".lead-mgmt-goal").html(s); 
    if(pm_currentSalesLead.goal != null){ $('#lead-mgmt-goal').val(pm_currentSalesLead.goal.id); }
  });
  RenderUtil.render('component/basic_select_options', {options:app_locations, collection:'app_locations', choose:true}, function(s) { 
    $(".lead-mgmt-location").html(s); 
    $('#lead-mgmt-location').val(pm_currentSalesLead.facilityId);
  });
  RenderUtil.render('component/basic_select_options', {options:app_gender, collection:'app_gender', choose:true}, function(s) { 
    $(".lead-mgmt-gender").html(s); 
    if(pm_currentSalesLead.gender != null){ $('#lead-mgmt-gender').val(pm_currentSalesLead.gender.id); }
  });
  
  $('#lead-mgmt-title').val(pm_currentSalesLead.title);
  $('#lead-mgmt-first-name').val(pm_currentSalesLead.firstName);
  $('#lead-mgmt-middle-name').val(pm_currentSalesLead.middleName);
  $('#lead-mgmt-last-name').val(pm_currentSalesLead.lastName);
  $('#lead-mgmt-best-time').val(pm_currentSalesLead.bestTimeToContact);
  $('#lead-mgmt-occupation').val(pm_currentSalesLead.occupation);
  $('#lead-mgmt-primary-phone').mask("(999) 999-9999");
  $('#lead-mgmt-primary-phone').val(pm_currentSalesLead.primaryPhone);
  $('#lead-mgmt-secondary-phone').mask("(999) 999-9999");
  $('#lead-mgmt-secondary-phone').val(pm_currentSalesLead.secondaryPhone);
  $('#lead-mgmt-email').val(pm_currentSalesLead.email);
  $('#lead-mgmt-dob').datepicker();
  if(pm_currentSalesLead.dob != null) { $('#lead-mgmt-dob').val(dateFormat(pm_currentSalesLead.dob, 'mm/dd/yyyy')); }
  $('#lead-mgmt-primary-address').val(pm_currentSalesLead.streetAddress1);
  $('#lead-mgmt-primary-city').val(pm_currentSalesLead.city);
  $('#lead-mgmt-primary-postal-code').val(pm_currentSalesLead.postalCode);
  $('#lead-mgmt-secondary-address').val(pm_currentSalesLead.altStreetAddress1);
  $('#lead-mgmt-secondary-city').val(pm_currentSalesLead.altCity);
  $('#lead-mgmt-secondary-postal-code').val(pm_currentSalesLead.altPostalCode);
  $('#lead-mgmt-notes').val(pm_currentSalesLead.note);
  pm_selectedLocationId = pm_currentSalesLead.facilityId;
  pm_getUsersByLocation(true);
  $('#lead-mgmt-action-date').datepicker();
  $('#lead-mgmt-action-save-btn').off('click').on('click', function(){ if(pm_validateSalesLeadActionForm()){pm_saveSalesLeadAction(); }})
  $('#lead-mgmt-action-cancel-btn').off('click').on('click', function(){ clearActionForm(); $('#lead-mgmt-new-action-panel').hide(); })
  $('#lead-mgmt-task-due-date').datepicker();
  $('#lead-mgmt-task-save-btn').off('click').on('click', function(){ if(pm_validateSalesLeadTaskForm()){ pm_saveSalesLeadTask(); }})
  $('#lead-mgmt-task-cancel-btn').off('click').on('click', function(){ clearTaskForm(); $('#lead-mgmt-new-task-panel').hide(); })
  
  $('.intake-field').blur(function() {
     pm_validateAndUpdateSalesLeadField(this);
  });
  
  $("select[id='lead-mgmt-user'].selectpicker").on('change', function() {
    var selectedValues = "";
    $( "select[id='lead-mgmt-user'] option:selected" ).each(function() { selectedValues += $( this ).val() + ","; });
    pm_updateSalesAgentIds('lead-mgmt-user', 'salesLead.salesAgentIds', selectedValues);
    $("select[id='lead-mgmt-user'].selectpicker").selectpicker('refresh');
  });
  $('#lead-mgmt-delete').off('click').on('click', function(e) {
    e.preventDefault();
    pm_deleteSalesLeadConfirm(e, pm_currentSalesLeadId);
  });
  
  $('#lead-mgmt-convert-to-patient').off('click').on('click', function(e) {
    e.preventDefault();
    pm_handleSalesLeadConversion(e);
  });
  
  $('#lead-mgmt-cancel').off('click').on('click', function(e) {
    e.preventDefault();
    pm_viewDashboard();
  });
  
  $('#lead-mgmt-location').on('change',function() { 
    pm_selectedLocationId = $('#lead-mgmt-location').val(); 
    pm_getUsersByLocation(false); 
  });
  
  $('#lead-mgmt-new-action-btn').on('click', function(){ pm_newActionForm(); });
  $('#lead-mgmt-new-task-btn').on('click', function(){ pm_newTaskForm(); });
}



function pm_newSalesLeadForm() {
  RenderUtil.render('form/new_sales_lead', {}, function(s) {
    $('#modal-new-sales-lead').remove();
    $('#modals-placement').html(s);
    $('#modal-new-sales-lead').modal('show');
    $('#new-lead-dob').datepicker();
    
    RenderUtil.render('component/basic_select_options', {options:app_usStates, collection:'app_usStates', choose:true}, function(s) { $(".lead-mgmt-primary-us-state, .lead-mgmt-secondary-us-state").html(s); });
    RenderUtil.render('component/basic_select_options', {options:pm_salesLeadStatuses, collection:'pm_salesLeadStatuses', choose:true}, function(s) { $(".lead-mgmt-status").html(s); });
    RenderUtil.render('component/basic_select_options', {options:pm_salesLeadStages, collection:'pm_salesLeadStages', choose:true}, function(s) { $(".lead-mgmt-stage").html(s); });
    RenderUtil.render('component/basic_select_options', {options:pm_salesLeadAgeRanges, collection:'pm_salesLeadAgeRanges', choose:true}, function(s) { $(".lead-mgmt-age-range").html(s); });
    RenderUtil.render('component/basic_select_options', {options:pm_salesLeadSources, collection:'pm_salesLeadSources', choose:true}, function(s) { $(".lead-mgmt-source").html(s); });
    RenderUtil.render('component/basic_select_options', {options:pm_networkMarketingSources, collection:'pm_networkMarketingSources', choose:true}, function(s) { $(".lead-mgmt-network-marketing-source").html(s); });
    RenderUtil.render('component/basic_select_options', {options:pm_referralSourceTypes, collection:'pm_referralSourceTypes', choose:true}, function(s) { $(".lead-mgmt-source-type").html(s);  });
    RenderUtil.render('component/basic_select_options', {options:pm_addressTypes, collection:'pm_addressTypes', choose:true}, function(s) { $(".lead-mgmt-primary-address-type, .lead-mgmt-secondary-address-type").html(s); });
    RenderUtil.render('component/basic_select_options', {options:pm_salesLeadGoals, collection:'pm_salesLeadGoals', choose:true}, function(s) { $(".lead-mgmt-goal").html(s); });
    RenderUtil.render('component/basic_select_options', {options:app_locations, collection:'app_locations', choose:true}, function(s) { $(".lead-mgmt-location").html(s); });
    RenderUtil.render('component/basic_select_options', {options:app_gender, collection:'app_gender', choose:true}, function(s) { $(".lead-mgmt-gender").html(s); });
    
    $('#new-lead-location').on('change',function() { 
      pm_selectedLocationId = $('#new-lead-location').val(); 
      pm_getUsersByLocation(false); 
    });
  
    $('#new-sales-lead-save-btn').on('click', function(){ pm_saveNewSalesLead(); });
        
    $('#new-sales-lead-cancel-btn').on('click', function() {
      $('#modal-new-sales-lead').hide();
      pm_salesLeadSearchDialog();
    });
  });
}



function pm_salesLeadScreen() {
  RenderUtil.render('screen/lead_mgmt_screen', {}, function(s) {
    $("#lead-mgmt-screen").html(s);
    app_viewStack('lead-mgmt-screen', DO_SCROLL);
    $('.field-is-required-for-conversion').css({visibility: "hidden"});
    $(".modal-backdrop").remove();
    pm_getSalesLead();
  });
}
    
      
function salesLeadSearch() {
  if (!util_isFieldEmpty('#sales-lead-search-dob')){
    if (util_isDate($.trim($('#sales-lead-search-dob').val())) == false) {
      util_showError($('#sales-lead-search-dob'), 'must be valid date'); 
      return; 
    } 
  }
  var jsonData = JSON.stringify({
    sessionId: app_getSessionId(),
    firstName: $.trim($('#sales-lead-search-first-name').val()),
    middleName: $.trim($('#sales-lead-search-middle-name').val()),
    lastName: $.trim($('#sales-lead-search-last-name').val()),
    genderId: $.trim($('#sales-lead-search-gender').val()),
    dob: $.trim($('#sales-lead-search-dob').val()),
    city: $.trim($('#sales-lead-search-city').val())
  });
  $.post("leadmgmt/getFilteredSalesLeads", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    RenderUtil.render('component/simple_data_table', 
     {items: parsedData, 
      title: 'Sales Leads Search Results', 
      tableName: 'sales-lead-search-results', 
      clickable: true, 
      columns:[
        {title:'Full Name', field:'firstName', type:'patient'},
        {title:'Date of Birth', field:'dob', type:'date'},
        {title:'City', field:'city', type:'simple'}
      ]}, function(s) {
        $('#sales-lead-search-results').html(s);
        $('#sales-lead-search-results-title').html("Sales Leads Search Results");
        
        $('.clickable-table-row').on('click',  function(e) { 
          $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
          pm_currentSalesLeadId = $(this).attr('data-row-key');
          $('#modal-sales-lead-search').modal('hide'); 
          pm_salesLeadScreen(); 
        });
    });
  });
}



function pm_salesLeadSearchDialog() {
  RenderUtil.render('form/sales_lead_search', {}, function(s) {
    $('#modal-sales-lead-search').remove(); 
    $('#modals-placement').html(s);
    $('#modal-sales-lead-search').modal('show'); 
    $('#btn-sales-lead-search-ok').prop('disabled', true);
    $('#btn-sales-lead-search-ok').addClass('disabled');
    $('#sales-lead-search-dob').mask("99/99/9999");
    $('.clickable-table-row').removeClass('table-row-highlight');
    RenderUtil.render('component/basic_select_options', {options:app_gender, collection:'app_gender', choose:true}, function(s) { $("#sales-lead-search-gender").html(s); });
    $('#btn-sales-lead-search-clear').on('click', function() {
      pm_clearSalesLeadSearch();
    });
    $('#btn-sales-lead-search-search').on('click', function() {
      pm_salesLeadSearch();
    });
    $('#btn-sales-lead-search-ok').on('click', function(){ app_getPatient(); });
    pm_initLeadMgmtTypeAheads();
    pm_getRecentSalesLeads();  
    $('#btn-sales-lead-search-new-sales-lead').on('click', function() { 
      pm_newSalesLeadForm(); 
    });
    
    $('#btn-sales-lead-search-cancel').off('click').on('click', function() {
      $('#modal-sales-lead-search').modal('hide').on('hidden.bs.modal', function (e) {
          $(".modal-backdrop").remove();
      });
    });
  });  
}



function pm_saveNewSalesLead() { 
  
  var isValid = true;
  util_clearErrors();  
  
  if (util_isFieldEmpty('#new-lead-first-name')) {
    util_showError($('#new-lead-first-name'));
    isValid = false;
  }
  if (util_isFieldEmpty('#new-lead-last-name')) {
    util_showError($('#new-lead-last-name'));
    isValid = false;
  }
  if (util_isFieldEmpty('#new-lead-age-range')) {
    util_showError($('#new-lead-age-range'));
    isValid = false;
  }
  if (util_isFieldEmpty('#new-lead-email') && util_isFieldEmpty('#new-lead-primary-phone')) {
    util_showError($('#new-lead-email'), 'Email or Primary Phone required');
    isValid = false;
  }
  if($.trim($("#new-lead-gender").val()) == 0) {
    util_showError($('#new-lead-gender')); 
    isValid = false;    
  }
  if (!util_isFieldEmpty('#new-lead-email')) {
    var emailValid = util_checkRegexp($.trim($("#new-lead-email").val()), util_emailRegexObj);
    if(emailValid == false) {
      util_showError($('#new-lead-email'), 'invalid email address');
      isValid = false;
    }
  }
  if (!util_isFieldEmpty('#new-lead-primary-phone')) {
    var phoneValid = util_checkRegexp($.trim($("#new-lead-primary-phone").val()), util_phoneRegexObj);
    if(phoneValid == false) {
      util_showError($('#new-lead-primary-phone'), 'invalid phone number');
      isValid = false;
    }
  }
  if (!util_isFieldEmpty('#new-lead-dob')) {
    if (util_isDate($.trim($('#new-lead-dob').val())) == false) {
      util_showError($('#new-lead-dob'), 'must be valid date');
      isValid = false;
    }
  }
  
  if (isValid == false) {
    return;
  }
  
  var selectedValues = "";
  
  $( "select[id='new-lead-user'] option:selected" ).each(function() {
    selectedValues += $( this ).val() + ",";
  });
  
  var jsonData = JSON.stringify({
    sessionId: app_getSessionId(),
    facilityId: $('#new-lead-location').val(),
    userIds: selectedValues,
    title: $.trim($('#new-lead-title').val()),
    firstName: $.trim($('#new-lead-first-name').val()),
    middleName: $.trim($('#new-lead-middle-name').val()),
    lastName: $.trim($('#new-lead-last-name').val()),
    bestTimeToContact: $.trim($('#new-lead-best-time').val()),
    genderId: $.trim($('#new-lead-gender').val()),
    occupation: $.trim($('#new-lead-occupation').val()),
    email: $.trim($('#new-lead-email').val()),
    primaryPhone: $.trim($('#new-lead-primary-phone').val()),
    secondaryPhone: $.trim($('#new-lead-secondary-phone').val()),
    dob: $.trim($('#new-lead-dob').val()),
    statusId: $.trim($('#new-lead-status').val()),
    stageId: $.trim($('#new-lead-stage').val()),
    ageRangeId: $.trim($('#new-lead-age-range').val()),
    sourceId: $.trim($('#new-lead-source').val()),
    sourceTypeId: $.trim($('#new-lead-source-type').val()),
    networkMarketingSourceId: $.trim($('#new-lead-network-marketing-source').val()),
    addressTypeId: $.trim($('#new-lead-primary-address-type').val()),
    streetAddress1: $.trim($('#new-lead-primary-address').val()),
    city: $.trim($('#new-lead-primary-city').val()),
    usStateId: $.trim($('#new-lead-primary-us-state').val()),
    postalCode: $.trim($('#new-lead-primary-postal-code').val()),
    altAddressTypeId: $.trim($('#new-lead-secondary-address-type').val()),
    altStreetAddress1: $.trim($('#new-lead-secondary-address').val()),
    altCity: $.trim($('#new-lead-secondary-city').val()),
    altUSStateId: $.trim($('#new-lead-secondary-us-state').val()),
    altPostalCode: $.trim($('#new-lead-secondary-postal-code').val()),
    goalId: $.trim($('#new-lead-goal').val()),
    note: $.trim($('#new-lead-notes').val())
   });
   $.post("leadmgmt/saveNewSalesLead", {data:jsonData}, function(data) {
     var parsedData = $.parseJSON(data);
     if (!util_checkSessionResponse(parsedData)) return false;
     pm_salesLeadSearchDialog();  
  });
}



function pm_updateSalesAgentIds(id, updateProperty, updatePropertyValue){
  util_debug("updateSalesAgentIds("+id+", "+updateProperty+", "+updatePropertyValue+");");
  var jsonData = JSON.stringify({
    sessionId: app_getSessionId(),
    id: pm_currentSalesLead.id,
    updateProperty:updateProperty,
    updatePropertyValue:updatePropertyValue
  });
  $.post("leadmgmt/updateSalesAgentIds", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
  }); 
}



function pm_updateSalesLeadField($this) {
  var id = $this.attr('id');
  if (typeof id === "undefined") {
    id = $this.attr('data-id');
  }
  var updateProperty = $this.attr('data-property');
  var updatePropertyValue = $this.is(':checkbox') ? $this.is(':checked') : $this.val();
  if (typeof updatePropertyValue === 'undefined' || updatePropertyValue.length < 1) {
    updatePropertyValue = $this.data('title');
  }
  if(typeof updatePropertyValue === 'undefined' || updatePropertyValue.trim().length < 1){
    return;
  }
  util_debug("updateSalesLead("+id+", "+updateProperty+", "+updatePropertyValue+");");
  var jsonData = JSON.stringify({
    sessionId: app_getSessionId(),
    id: pm_currentSalesLead.id,
    updateProperty: updateProperty,
    updatePropertyValue: $.trim(updatePropertyValue)
  });
  $.post("leadmgmt/updateSalesLead", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
  }); 
}



function pm_validateAndUpdateSalesLeadField(element) {
  if (pm_validateSalesLeadField($(element))) {
    pm_updateSalesLeadField($(element));
  }
}



function pm_validateSalesLeadField($this) {  
  var isValid = true;
  util_clearItemError($this)
  var id = $this.attr('id');
  var updateProperty = $this.attr('data-property');
  var updatePropertyValue = $this.val();
  var patientIntakePath = updateProperty.split('.'); 
  var className = patientIntakePath[0];
  var property = patientIntakePath[1];
  
  if (className == "salesLeadNew") {
    if (property == 'firstName' && util_isFieldEmpty('#new-lead-first-name')) {
      util_showError($('#new-lead-first-name')); 
      isValid = false; 
    }
    if (property == 'lastName' && util_isFieldEmpty('#new-lead-last-name')) {
      util_showError($('#new-lead-last-name')); 
      isValid = false; 
    }
    if ((property == 'email'|| property == 'primaryPhone') && (util_isFieldEmpty('#new-lead-email') && util_isFieldEmpty('#new-lead-primary-phone'))) {
      util_showError($('#new-lead-email'), 'Email or Primary Phone required'); 
      isValid = false; 
    }
    if (property == 'email' && !util_isFieldEmpty('#new-lead-email')) {
      var emailValid = util_checkRegexp($.trim($("#new-lead-email").val()), util_emailRegexObj);
      if(emailValid == false) { 
        util_showError($('#new-lead-email'), 'invalid email address'); 
        isValid = false;
      }
    }
    if(property == 'gender' && $.trim($("#new-lead-gender").val()) == 0) {
      util_showError($('#new-lead-gender'), 'Gender is required'); 
      isValid = false;    
    }
    if (property == 'primaryPhone' && !util_isFieldEmpty('#new-lead-primary-phone')) {
      var phoneValid = util_checkRegexp($.trim($("#new-lead-primary-phone").val()), util_phoneRegexObj);
      if(phoneValid == false) { 
        util_showError($('#new-lead-primary-phone'), 'invalid phone number'); 
        isValid = false;
      }
    }
    if (property == 'dob' && !util_isFieldEmpty('#new-lead-dob')) {
      if (util_isDate($.trim($('#new-lead-dob').val())) == false) {
        util_showError($('#new-lead-dob'), 'must be valid date'); 
        isValid = false; 
      } 
    }
  }
  if (className == "salesLead") {
    if (property == 'firstName' && util_isFieldEmpty('#lead-mgmt-first-name')) {
      util_showError($('#lead-mgmt-first-name')); 
      isValid = false; 
    }
    if (property == 'lastName' && util_isFieldEmpty('#lead-mgmt-last-name')) {
      util_showError($('#lead-mgmt-last-name')); 
      isValid = false; 
    }
    if ((property == 'ssn' || property == 'driversLicense') && (util_isFieldEmpty('#lead-mgmt-ssn') && util_isFieldEmpty('#lead-mgmt-drivers-license'))) {
      util_showError($('#lead-mgmt-ssn'), 'SSN or Drivers License required'); 
      isValid = false; 
    }
    if ((property == 'email' || property == 'primaryPhone') && (util_isFieldEmpty('#lead-mgmt-email') && util_isFieldEmpty('#lead-mgmt-primary-phone'))) {
      util_showError($('#lead-mgmt-email'), 'Email or Primary Phone required'); 
      isValid = false; 
    }
    if (property == 'email' && !util_isFieldEmpty('#lead-mgmt-email')){
      var emailValid = util_checkRegexp($.trim($("#lead-mgmt-email").val()), util_emailRegexObj);
      if(emailValid == false) { 
        util_showError($('#lead-mgmt-email'), 'invalid email address'); 
        isValid = false;
      }
    }
    if(property == 'gender' && $.trim($("#lead-mgmt-gender").val()) == 0){
      util_showError($('#lead-mgmt-gender'), 'Gender is required'); 
      isValid = false;    
    }
    if (property == 'primaryPhone' && !util_isFieldEmpty('#lead-mgmt-primary-phone')) {
      var phoneValid = util_checkRegexp($.trim($("#lead-mgmt-primary-phone").val()), util_phoneRegexObj);
      if(phoneValid == false) { 
        util_showError($('#lead-mgmt-primary-phone'), 'invalid phone number'); 
        isValid = false;
      }
    }
    if (property == 'dob' && !util_isFieldEmpty('#lead-mgmt-dob')) {
      if (util_isDate($.trim($('#lead-mgmt-dob').val())) == false) {
        util_showError($('#lead-mgmt-dob'), 'must be valid date'); 
        isValid = false; 
      } 
    }    
  }
  
  return isValid;
}



function pm_validateSalesLeadRequiredFields(){
  var isValid = true;
  util_clearErrors();
  
  if (util_isFieldEmpty('#lead-mgmt-first-name')) {
    util_showError($('#lead-mgmt-first-name')); 
    isValid = false; 
  }
  if (util_isFieldEmpty('#lead-mgmt-last-name')) {
    util_showError($('#lead-mgmt-last-name')); 
    isValid = false; 
  }
  if (util_isFieldEmpty('#lead-mgmt-email') && util_isFieldEmpty('#lead-mgmt-primary-phone')) {
    util_showError($('#lead-mgmt-email'), 'Email or Primary Phone required'); 
    isValid = false; 
  }
  if (!util_isFieldEmpty('#lead-mgmt-email')) {
    var emailValid = util_checkRegexp($.trim($("#lead-mgmt-email").val()), util_emailRegexObj);
    if(emailValid == false) { 
      util_showError($('#lead-mgmt-email'), 'invalid email address'); 
      isValid = false;
    }
  }
  if (!util_isFieldEmpty('#lead-mgmt-primary-phone')) {
    var phoneValid = util_checkRegexp($.trim($("#lead-mgmt-primary-phone").val()), util_phoneRegexObj);
    if(phoneValid == false) { 
      util_showError($('#lead-mgmt-primary-phone'), 'invalid phone number'); 
      isValid = false;
    }
  }
  if (!util_isFieldEmpty('#lead-mgmt-dob')) {
    if (util_isDate($.trim($('#lead-mgmt-dob').val())) == false) {
      util_showError($('#lead-mgmt-dob'), 'must be valid date'); 
      isValid = false; 
    } 
  }
  return isValid;
}
