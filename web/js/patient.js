
function pm_buildPatientMRN(id) {
  $('#patient-mrn').val(util_padString(id, 6));
}



function app_clearPatientSearch() {
  $('.patient-search-field').val('');
  if (SPECIALTY == POT_SPECIALTY) {
    app_getFilteredPatientForms(); 
  }
  else {
    app_getFilteredPatients(); 
  }
}



function app_deletePatientConfirm() {
  var args = {
    modalTitle:"Delete " + app_practiceClientProperties['app.patient_label'], 
    modalH3:"Delete " + app_practiceClientProperties['app.patient_label'] + " " + app_patientFullName + "?",
    modalH4:"This will remove the patient from the system ",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function() {  
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), id: app_patientId, module:app_module });
      $.post("patient/deletePatient", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        app_displayNotification('Patient ' + app_patientFullName + ' Deleted');
        pm_closeChart(); 
        $('#modal-confirm').modal('hide'); 
        $('#modal-patient').modal('hide');
        app_patientSearchDialog(); 
      }); 
    });
  });
}

function app_getFilteredPatients() {
  var jsonData = JSON.stringify(
    _.extend({ 
        module:app_module,
        id: app_client.id, 
        sessionId: app_getSessionId() 
    }, app_patientFilter())
  );
    
  $.post("patient/getFilteredPatients", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_patients = parsedData.list;
    RenderUtil.render('component/simple_data_table', 
     {items:app_patients, 
      title: app_practiceClientProperties['app.patient_label'] + 's', 
      tableName:'patient-search-results', 
      clickable:true, 
      columns:[
        {title:'Full Name', field:'firstName', type:'patient'},
        {title:'Date of Birth', field:'dob', type:'date'},
        {title:'Gender', field:'gender.name', type:'double'},
        {title:'City', field:'city', type:'simple'},
        {title:'Primary Phone', field:'primaryPhone', type:'simple'},
        {title:'Secondary Phone', field:'secondaryPhone', type:'simple'},
        {title:'MRN', field:'mrn', type:'simple'},
        {title:'Email', field:'email', type:'simple'}
      ]}, function(s) {
      $('#patient-search-results').html(s);
      $('#patient-search-results-title').html(app_practiceClientProperties['app.patient_label'] + " Search");
      $('.clickable-table-row').click( function(e){ 
        $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        app_patientId = $(this).attr('name');
        $('#modal-patient-search').modal('hide'); 
        app_getPatient();
      });
    });
  });
}
      
function patient_getProfileImagePath(patient) {
  return 'patient/getPatientProfileImage?sessionId=' + app_getSessionId() + "&patientId=" + patient.id  + 
    "&profileImagePath=" + patient.profileImagePath + "&module=" + app_module;
}      
function app_getPatientProfileImagePath() {
  return patient_getProfileImagePath(app_patient);  
}

function app_getPatient(callback) {
  var jsonData = JSON.stringify({ id: app_patientId, sessionId: app_getSessionId(), module:app_module });
  app_post("patient/getPatient", jsonData, function(parsedData) {
    app_patient = parsedData.patient;
    if (callback) {
      callback();
    } else {
      app_patientFullName = util_buildFullName(app_patient.firstName, app_patient.middleName, app_patient.lastName);
      app_patientProfileImage = app_getPatientProfileImagePath();
      $('#section-notification').css({display: "block"});
      $('.patient-navbar-btn').css("display", "inline-block");
      $('#section-notification-text').html("Patient: " + app_patientFullName);
      if (app_module == PM_MODULE) {
        pm_viewPatientChartScreen();
      }
      app_renderPatientChartHeader();
    }  
  });
}
  


  function pm_getPatientChartSummary() {
    var jsonData = JSON.stringify({ id: app_patientId, clinicianId: clinician.id, sessionId: app_getSessionId(), module:app_module });
    $.post("app/getPatientChartSummary", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      if (!util_checkSessionResponse(parsedData)) return false;
      patientChartSummary = parsedData.patientChartSummary;
      
      RenderUtil.render('component/simple_data_table', 
       {items:patientChartSummary.patientEncounters, 
        title:'Visits', 
        clickable:true, 
        columns:[
          {title:'Date', field:'date', type:'date'},
          {title:'Type', field:'encounterType.name', type:'double'}
        ]}, function(s) {
        $('#patient-chart-summary-visits').html(s);
        $('.clickable-table-row').click( function(e){ 
          $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        });
      });
      
      RenderUtil.render('component/simple_data_table', 
       {items:patientChartSummary.patientVitalSigns, 
        title:'Vital Signs', 
        clickable:true, 
        columns:[
          {title:'Height', field:'height', type:'numeric'},
          {title:'Weight', field:'weight', type:'numeric'},
          {title:'BMI', field:'bmi', type:'numeric'},
          {title:'OFC', field:'ofc', type:'numeric'},
          {title:'Temp', field:'temperature', type:'numeric'},
          {title:'Pulse', field:'pulse', type:'numeric'},
          {title:'Resp', field:'respiration', type:'numeric'},
          {title:'Syst', field:'systolic', type:'numeric'},
          {title:'Dia', field:'diastolic', type:'numeric'},
          {title:'Ox', field:'oximetry', type:'numeric'}
        ]}, function(s) {
        $('#patient-chart-summary-vital-signs').html(s);
        $('.clickable-table-row').click( function(e){ 
          $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        });
      });
      
      RenderUtil.render('component/simple_data_table', 
       {items:patientChartSummary.patientHealthIssues, 
        title:'Health Maintenance', 
        clickable:true, 
        columns:[
         {title:'Health Issue', field:'healthIssue.name', type:'double'}, 
         {title:'Date', field:'date', type:'date'}
        ]}, function(s) {
        $('#patient-chart-summary-hm').html(s);
        $('.clickable-table-row').click( function(e){ 
          $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        });
      });
     
      RenderUtil.render('component/simple_data_table', 
       {items:patientChartSummary.patientAllergens, 
        title:'Allergens', 
        clickable:true, 
        columns:[
          {title:'Allergen', field:'allergen.name', type:'double'}, 
          {title:'Reaction', field:'comment', type:'simple'}
        ]}, function(s) {
        $('#patient-chart-summary-allergens').html(s);
        $('.clickable-table-row').click( function(e){ 
          $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        });
      });
      
      RenderUtil.render('component/simple_data_table', 
       {items:patientChartSummary.patientMedications, 
        title:'Medication', 
        clickable:true, 
        columns:[
          {title:'Medication', field:'medication.name', type:'double'}, 
          {title:'Date', field:'date', type:'date'},
          {title:'Unit', field:'unit', type:'simple'},
          {title:'Instructions', field:'instructions', type:'simple'}
        ]}, function(s) {
        $('#patient-chart-summary-medication').html(s);
        $('.clickable-table-row').click( function(e){ 
          $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        });
      });
      
      RenderUtil.render('component/simple_data_table', 
       {items:patientChartSummary.patientMedicalProcedures, 
        title:'Procedures', 
        clickable:true, 
        columns:[
          {title:'Procedure', field:'medicalProcedure.name', type:'double'}, 
          {title:'Due Date', field:'date', type:'date'},
          {title:'Status', field:'status.name', type:'double'},
          {title:'Last Done', field:'date', type:'date'}
        ]}, function(s) {
        $('#patient-chart-summary-procedures').html(s);
        $('.clickable-table-row').click( function(e){ 
          $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        });
      });
    });
  }
  
  
function app_getPatientClinicians(assignedClinicianId){
  var jsonData = {sessionId: app_getSessionId(),  module:app_module}
  app_post("app/getClinicians", jsonData, function(parsedData) {
    app_clinicians = parsedData.list;
    RenderUtil.render('component/person_select_options', {options:app_clinicians}, function(s) {
      $("#patient-clinician").html(s);
      $('#patient-clinician').val(assignedClinicianId ? assignedClinicianId : '');
    });
  });
}
  
function app_getPatientInfo() {  
  var jsonData = JSON.stringify({ id: app_patientId, sessionId: app_getSessionId(), module:app_module });
  $.post("patient/getPatientInfo", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    var patient = parsedData.patient;
    app_patient = patient;
    app_patientIntakeId = parsedData.patientIntakeId;
    var updateExtra = JSON.stringify({patientId:patient.id})

    RenderUtil.render('form/'+SPECIALTY+'/patient_form', {formMode:'edit', updateExtra}, function(s) {
      $('#modal-patient').remove();
      $('#modals-placement').html(s);
      $('#patient-info-form').empty()
      $('#modal-patient .form-control').css({display: "block"}); 
      $('#modal-patient').modal('show');
      $('#patient-program-div').hide();
      form_addForm('entity.patient', patient, '#patient-form');
      RenderUtil.render('component/basic_select_options', {options:app_usStates, collection:'app_usStates', choose:true}, function(s) {
        $("#patient-us-state").html(s); $('#patient-us-state').val(patient.usState ? patient.usState.id : ''); 
      });
      $('#patient-form-title').html("View/Edit " + app_practiceClientProperties['app.patient_label']);
      app_profileImageTempPath = "";
      $('#patient-ssn').mask("999-99-9999");
      $('#patient-dob').mask("99/99/9999");
      $('#patient-postal-code').mask("99999");
      $('#patient-primary-phone').mask("(999) 999-9999");
      $('#patient-secondary-phone').mask("(999) 999-9999");
      $('#patient-mrn').val(patient.mrn);
      $('#patient-first-name').val(patient.firstName);
      $('#patient-middle-name').val(patient.middleName);
      $('#patient-last-name').val(patient.lastName);
      $('#patient-preferred-name').val(patient.preferredName);
      $('#patient-ssn').val(patient.govtId);
      $('#patient-drivers-license').val(patient.driversLicense);
      if(patient.dob != null) { $('#patient-dob').val(dateFormat(patient.dob, 'mm/dd/yyyy')); }
      $('#patient-gender').val(patient.gender ? patient.gender.id : '');
      $('#patient-marital-status').val(patient.maritalStatus ? patient.maritalStatus.id : '');
      
      app_getPatientClinicians(patient.assignedClinicianId);

      $('#patient-race').val(patient.race ? patient.race.id : '');
      $('#patient-ethnicity').val(patient.ethnicity ? patient.ethnicity.id : '');
      $('#patient-address1').val(patient.streetAddress1);
      $('#patient-address2').val(patient.streetAddress2);
      $('#patient-prepayment-amount').val(patient.prepaymentAmount);
      $('#patient-city').val(patient.city);
      $('#patient-postal-code').val(patient.postalCode);
      $('#patient-status').val(patient.status);
      $('#patient-occupation').val(patient.occupation);
      $('#patient-employed').val(patient.employmentStatus);
      $('#patient-employer').val(patient.employer);
      $('#patient-school-status').val(patient.schoolStatus);
      $('#patient-school-name').val(patient.schoolName);
      $('#patient-primary-phone').mask("(999) 999-9999");
      $('#patient-primary-phone').val(patient.primaryPhone);
      $('#patient-secondary-phone').mask("(999) 999-9999");
      $('#patient-secondary-phone').val(patient.secondaryPhone);
      $('#patient-email').val(patient.email);
      $('#patient-insured-name').val(patient.insuredName);
      $('#patient-member-number').val(patient.memberNumber);
      $('#patient-group-number').val(patient.groupNumber);
      $('#patient-ins-carr').val(patient.insuranceCarrier);

      var portalInviteSent = app_pm_renderPortalInvitation();
      app_pm_renderIntakeForms(patient.forms, portalInviteSent);
      
      util_selectCheckboxesFromList(patient.programs, 'programs');
      $("input[name=programs]").off('click').on('click',  function() { app_updatePatientInfoPrograms() }); 
      
      $('#patient-photo').error(function() {
          $(this).attr('src',PROFILE_PLACEHOLDER_SM);
      });
      
      $('#patient-form .input-field').off('blur').on('blur', function() { form_validateAndUpdateField(this) });
      $('#patient-form .input-select').off('change').on('change', function() { form_validateAndUpdateField(this) });
      
      var profilePhoto = $('#patient-photo');
      profilePhoto.attr('src', app_patientProfileImage); 
      if (SPECIALTY != AC_SPECIALTY){
        app_setupGuardian();
      }
      app_setupPictureUpload(profilePhoto);
      $('#patient-photo-upload').off('blur').on('blur', function() { val = app_profileImageTempPath; });
      
      $('#patient-form-delete-btn').off('click').on('click', function(){ app_deletePatientConfirm(); });
      $('#patient-form-close-btn').off('click').on('click', function(){ app_getPatient(); });
    });
  });
}



function app_getRecentPatients() {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module });
  $.post("patient/getRecentPatients", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_patients = parsedData.list;
    RenderUtil.render('component/simple_data_table', 
     {items:app_patients, 
      title:'Recent ' + app_practiceClientProperties['app.patient_label'] + 's', 
      tableName:'patient-search-results', 
      clickable:true, 
      columns:[
        {title:'Full Name', field:'firstName', type:'patient'},
        {title:'Date of Birth', field:'dob', type:'date'},
        {title:'Gender', field:'gender.name', type:'double'},
        {title:'City', field:'city', type:'simple'},
        {title:'Primary Phone', field:'primaryPhone', type:'simple'},
        {title:'Secondary Phone', field:'secondaryPhone', type:'simple'},
        {title:'MRN', field:'mrn', type:'simple'},
        {title:'Email', field:'email', type:'simple'}
      ]}, function(s) {
      $('#patient-search-results').html(s);
      $('#patient-search-results-title').html('Recent ' + app_practiceClientProperties['app.patient_label'] + 's');
      $('.clickable-table-row').click( function(e){ 
        $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        app_patientId = $(this).attr('name');
        $('#modal-patient-search').modal('hide'); 
        app_getPatient();
      });
    });
  });
}



function pm_initPatientFormSearchTypeAheads() {
  var jsonData = JSON.stringify({sessionId: app_getSessionId(), module:app_module });
  $.post("patient/getPatientFormSearchTypeAheads", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_patientSearchTypeAheads = parsedData.patientSearchTypeAheads;
    $('#patient-search-first-name').typeahead (
      { hint: true, highlight: true, minLength: 3 },
      { name: 'firstNames', displayKey: 'value', source: util_substringMatcher(app_patientSearchTypeAheads.firstNames) }); 
    $('#patient-search-middle-name').typeahead (
      { hint: true, highlight: true, minLength: 3 },
      { name: 'middleNames', displayKey: 'value', source: util_substringMatcher(app_patientSearchTypeAheads.middleNames) }); 
    $('#patient-search-last-name').typeahead (
      { hint: true, highlight: true, minLength: 3 },
      { name: 'lastNames', displayKey: 'value', source: util_substringMatcher(app_patientSearchTypeAheads.lastNames) }); 
    $('#patient-search-city').typeahead (
      { hint: true, highlight: true, minLength: 3 },
      { name: 'cities', displayKey: 'value', source: util_substringMatcher(app_patientSearchTypeAheads.cities) }); 
  });
}



function app_initPatientSearchTypeAheads() {
  var jsonData = JSON.stringify({sessionId: app_getSessionId(), module:app_module });
  $.post("app/getPatientSearchTypeAheads", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_patientSearchTypeAheads = parsedData.patientSearchTypeAheads;
    $('#patient-search-first-name').typeahead (
      { hint: true, highlight: true, minLength: 3 },
      { name: 'firstNames', displayKey: 'value', source: util_substringMatcher(app_patientSearchTypeAheads.firstNames) }); 
    $('#patient-search-middle-name').typeahead (
      { hint: true, highlight: true, minLength: 3 },
      { name: 'middleNames', displayKey: 'value', source: util_substringMatcher(app_patientSearchTypeAheads.middleNames) }); 
    $('#patient-search-last-name').typeahead (
      { hint: true, highlight: true, minLength: 3 },
      { name: 'lastNames', displayKey: 'value', source: util_substringMatcher(app_patientSearchTypeAheads.lastNames) }); 
    $('#patient-search-mrn').typeahead (
      { hint: true, highlight: true, minLength: 3 },
      { name: 'mrns', displayKey: 'value', source: util_substringMatcher(app_patientSearchTypeAheads.mrns) }); 
    $('#patient-search-city').typeahead (
      { hint: true, highlight: true, minLength: 3 },
      { name: 'cities', displayKey: 'value', source: util_substringMatcher(app_patientSearchTypeAheads.cities) }); 
    $('#patient-search-phone').typeahead (
      { hint: true, highlight: true, minLength: 3 },
      { name: 'phoneNumbers', displayKey: 'value', source: util_substringMatcher(app_patientSearchTypeAheads.phoneNumbers) }); 
  });
}
  


function pm_onPatientButtonClick() {
  $('.clickable-table-row').removeClass('table-row-highlight');
}



function app_patientSearchDialog() {
  $(".modal-backdrop").remove(); 
  RenderUtil.render('form/patient_search', {}, function(s) {
    $('#modal-patient-search').remove(); 
    $('#modals-placement').html(s);
    $('#modal-patient-search').modal('show'); 
    $('.patient-search-field').val('');
    $('#patient-search-dob').mask("99/99/9999");
    $('.clickable-table-row').removeClass('table-row-highlight');
    $('#btn-patient-search').click(function(){ app_getFilteredPatients(); });
    $('#btn-patient-search-clear').click(function(){ app_clearPatientSearch(); });
    if (SPECIALTY == POT_SPECIALTY) {
      pot_app_getRecentPatientForms();
      pm_initPatientFormSearchTypeAheads();
    }
    else {
      app_getRecentPatients();
      app_initPatientSearchTypeAheads();
    }
    if (app_module == PM_MODULE) {
      $('#btn-patient-search-new-patient').show();
      $('#btn-patient-search-new-patient').click(function() { pm_renderNewPatientForm() });
    }
 });
}

function pm_renderNewPatientForm(){
  var jsonData = { sessionId: app_getSessionId() };
  pm_closeChart(); 
  app_patient = {}
  if (SPECIALTY==POT_SPECIALTY){
    patient__renderNewPatientForm()
  } else {
   app_post("patient/getNextPatientId", jsonData, function(parsedData){
     app_patient = {id: parsedData.id};
      patient__renderNewPatientForm();
   })
  }
}

function patient__renderNewPatientForm() {
  var updateExtra = JSON.stringify({patientId:app_patient.id})
  RenderUtil.render('form/'+SPECIALTY+'/patient_form', {formMode:'add', updateExtra: updateExtra}, function(s) {
    $('#patient-form').remove();
    $('#modal-patient').remove();
    $('#modal-patient-form').remove();
    $('#modals-placement').append(s);
    var jsonData = JSON.stringify({ sessionId: app_getSessionId() });
    if (SPECIALTY != POT_SPECIALTY) {
      $('#modal-patient').modal('show');
      $('#patient-program-div').show();
      if (SPECIALTY != AC_SPECIALTY){
       app_setupGuardian();
      }
      RenderUtil.render('component/basic_select_options', {options:app_programs, collection:'app_programs', choose:false}, function(s) { $("#patient-program").html(s); });
      RenderUtil.render('component/basic_select_options', {options:app_usStates, collection:'app_usStates', choose:true}, function(s) { $("#patient-us-state").html(s); });
      app_profileImageTempPath = "";
      $('#patient-ssn').mask("999-99-9999");
      $('#patient-dob').mask("99/99/9999");
      $('#patient-postal-code').mask("99999");
      $('#patient-primary-phone').mask("(999) 999-9999");
      $('#patient-secondary-phone').mask("(999) 999-9999");
      $('#patient-cancel').off('click').on('click', function(){ $('#modal-patient').modal('hide'); });
      $('#patient-save').off('click').on('click', function(){ pm_saveNewPatient() });
      $('#patient-photo-upload').click(function() { $('#patient-photo-upload-progress .progress-bar').css('width', '0'); });
      app_setupPictureUpload();
      app_getClinicians();
      pm_buildPatientMRN(app_patient.id);
      $('#portal-invitation-screen').html('<div class="checkbox">'
        + '<label><input type="checkbox" id="patient-send-portal-invite" name="patient-send-portal-invite" value="true" checked="checked">' 
        + 'Send Portal Invitation'
        + '</label></div>'
      );
    }
    else if (SPECIALTY == POT_SPECIALTY) {
      $('#modal-patient-form').modal('show');
      $.post("patient/createPOTPatientForm", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      if (!util_checkSessionResponse(parsedData)) return false;
      app_patientForm = parsedData.patientForm;
      pot_pm_loadPatientForm();
    });
    }
  });
}

function pm_renderPatientGuardianSensitiveInfo(patient) {
  var patientGuardian = patient;
  if (SPECIALTY==POT_SPECIALTY && patient.guardian){
    patientGuardian=patient.guardian;
  }
  var email = patientGuardian.guardianEmail || patientGuardian.email
  $('.patient-chart-email').html(email);
  $('.patient-chart-primary-phone').html(patientGuardian.primaryPhone);
  $('.patient-chart-secondary-phone').html(patientGuardian.secondaryPhone);
}

function app_renderPatientChartHeader() {
  RenderUtil.render('component/patient_chart_header', {}, function(s) {
    $('#app-chart-header').html(s);
    $('.patient-chart-full-name').html(app_patientFullName);
    $('.patient-chart-dob').html(dateFormat(app_patient.dob, 'mm/dd/yyyy'));
    $('.patient-chart-gender').html(app_patient.gender ? app_patient.gender.code: '');
    $('.patient-chart-mrn').html(app_patient.mrn);
    $('.patient-chart-headshot').attr('src', app_patientProfileImage);
    $('.patient-chart-address').html(app_patient.streetAddress1);
    $('.patient-chart-city').html(app_patient.city);
    $('.patient-chart-us-state').html(app_patient.usState ? app_patient.usState.code : '');
    $('.patient-chart-postal-code').html(app_patient.postalCode);
    pm_renderPatientGuardianSensitiveInfo(app_patient);
    var recentActivity = app_patient.recentActivity
    if (recentActivity){
     var lastAppointment = recentActivity.lastAppointment ;
     var nextAppointment = recentActivity.nextAppointment 
     $('.patient-chart-last-appt').html(lastAppointment && recentActivity.lastAppointment.startTime);
     $('.patient-chart-next-appt').html(nextAppointment && nextAppointment.startTime);
    }
    $('#section-notification-text').html("Patient: " + app_patientFullName);
  });
} 

function pm_patientFormValidate() {
  var isValid = true;
  util_clearErrors();  

  if($("#patient-first-name").val().length < 1) { 
    util_showError($('#patient-first-name'));
    isValid = false;
  }
  if($("#patient-last-name").val().length < 1) { 
    util_showError($('#patient-last-name'));
    isValid = false;
  }
  if(util_checkRegexp($.trim($('#patient-primary-phone').val()), util_phoneRegexObj) == false) { 
    util_showError($('#patient-primary-phone'), 'must be valid phone');
    isValid = false;
  }
 
  if (SPECIALTY != AC_SPECIALTY && SPECIALTY != DOM_SPECIALTY) {
    if($("#patient-dob").val().length < 1) { 
      util_showError($('#patient-dob'));
      isValid = false;
    }
    if($("#patient-address1").val().length < 1) { 
      util_showError($('#patient-address1'));
      isValid = false;
    }
    if($("#patient-city").val().length < 1) { 
      util_showError($('#patient-city'));
      isValid = false;
    }
    if($("#patient-us-state").val().length < 1) { 
      util_showError($('#patient-us-state'));
      isValid = false;
    }
    if($("#patient-postal-code").val().length < 1) { 
      util_showError($('#patient-postal-code'));
      isValid = false;
    }
    if($("#patient-gender").val().length < 1) { 
      util_showError($('#patient-gender'));
      isValid = false;
    }
  }
  
  if (SPECIALTY != POT_SPECIALTY && SPECIALTY != AC_SPECIALTY) {
    if($("#patient-ins-carr").val().length < 1) { 
      util_showError($('#patient-ins-carr'));
      isValid = false;
    } 
    if($("#patient-member-number").val().length < 1) { 
      util_showError($('#patient-member-number'));
      isValid = false;
    } 
    if($("#patient-group-number").val().length < 1) { 
      util_showError($('#patient-group-number'));
      isValid = false;
    } 
    if($("#patient-insured-name").val().length < 1) { 
      util_showError($('#patient-insured-name'));
      isValid = false;
    } 
    if ($('#patient-guardian-status').prop('checked') == true) {
      if (!app_guardian) {
        util_showError($('#patient-guardian'), "Please select a guardian");
        isValid = false;
      }
    }
    if (app_guardian != null) {
      if ($("#guardian-first-name").val().length < 1) { 
        util_showError($('#guardian-first-name'));
        isValid = false;
      }
      if ($("#guardian-last-name").val().length < 1) { 
        util_showError($('#guardian-last-name'));
        isValid = false;
      }
      var guardianEmailValid = util_checkRegexp($.trim($("#guardian-email").val()), util_emailRegexObj);
      if(guardianEmailValid == false) {
        util_showError($('#guardian-email'), 'invalid email address');
        isValid = false;
      }
    }
    else { 
      var emailValid = util_checkRegexp($.trim($("#patient-email").val()), util_emailRegexObj);
      if(emailValid == false) {
        util_showError($('#patient-email'), 'invalid email address');
        isValid = false;
      }
    }
  } 
  else {  
    var emailValid = util_checkRegexp($.trim($("#patient-email").val()), util_emailRegexObj);
    if(emailValid == false) {
      util_showError($('#patient-email'), 'invalid email address');
      isValid = false;
    }
  }
  return isValid;
}

function pm_saveNewPatient() {
  var isValid = pm_patientFormValidate();

  if (isValid == false) {
    return;
  }
  $('#patient-save').attr("disabled", "disabled");
  app_blockingSpinnerStart();
  var mrn = $('#patient-mrn').val();
  if ($('#patient-program').length) {
    mrn =  $('#patient-program').val() + mrn;
  }
  var jsonData = JSON.stringify({ 
    module:app_module,
    mrn:  mrn,
    firstName: $('#patient-first-name').val(), 
    lastName: $('#patient-last-name').val(), 
    middleName: $('#patient-middle-name').val(), 
    preferredName: $('#patient-preferred-name').val(), 
    assignedClinicianId: $('#patient-clinician').val(), 
    ssn: $('#patient-ssn').val(), 
    driversLicense: $('#patient-drivers-license').val(), 
    dob: $('#patient-dob').val(), 
    genderId: $('#patient-gender').val() ? $('#patient-gender').val() : 0,
    maritalStatus: $('#patient-marital-status').val(), 
    race: $('#patient-race').val(), 
    ethnicity: $('#patient-ethnicity').val(), 
    address1: $('#patient-address1').val(), 
    address2: $('#patient-address2').val(), 
    city: $('#patient-city').val(), 
    usState: $('#patient-us-state').val() ? $('#patient-us-state').val() : 0,
    postalCode: $('#patient-postal-code').val(), 
    status: $('#patient-status').val(), 
    occupation: $('#patient-occupation').val(), 
    employed: $('#patient-employed').val(), 
    employer: $('#patient-employer').val(), 
    school: $('#patient-school').val(), 
    schoolName: $('#patient-schoolName').val(), 
    primaryPhone: $('#patient-primary-phone').val(), 
    secondaryPhone: $('#patient-secondary-phone').val(), 
    email: $('#patient-email').val(),
    profileImageTempPath: app_profileImageTempPath,
    forms: $('input[name=forms]:checked').map(function() {return this.value;}).get().join(','),
    programs: $('input[name=programs]:checked').map(function() {return this.value;}).get().join(','),
    sendPortalInvite: $('#patient-send-portal-invite').prop('checked') == true,
    prepaymentAmount: $('#patient-prepayment-amount').val(), 
    insuranceCarrier: $('#patient-ins-carr').val(), 
    memberNumber: $('#patient-member-number').val(), 
    groupNumber: $('#patient-group-number').val(), 
    insuredName: $('#patient-insured-name').val(), 
    guardianId: app_guardian != null ? app_guardian.id : null,
    id: app_patient.id,
    sessionId: app_getSessionId()
  });
  $.post("patient/saveNewPatient", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    app_blockingSpinnerEnd();
    if (!util_checkSessionResponse(parsedData)) return false;
    if (parsedData.returnCode == RETURN_CODE_DUP_EMAIL) {
      var args = {
        modalTitle:"Email Address Already In Use", 
        modalH3:"Email Address Already In Use", 
        modalH4:"Please try again with a different email address.",
        cancelButton: null,
        okButton: 'OK'
      };
      RenderUtil.render('dialog/confirm', args, function(s) { 
        $('#modal-confirm').remove(); 
        $('#modals-placement').append(s);
        $('#modal-confirm').modal('show'); 
        $('#app-modal-confirmation-btn').click(function(){  $('#modal-confirm').modal('hide');});
        $('#patient-save').removeAttr("disabled");
      });
      return;
    }
    $('#modal-patient').modal('hide');
    $('#modal-patient-search').modal('hide'); 
    pm_saveNewPatient_clearForm();
    app_displayNotification('New patient '+ parsedData.firstName + ' ' + parsedData.lastName + ' created.');
    app_patientSearchDialog(); 
  });
}



function pm_saveNewPatient_clearForm() {
  $('#patient-first-name').val('');
  $('#patient-middle-name').val('');
  $('#patient-last-name').val('');
  $('#patient-preferred-name').val('');
  $('#patient-ssn').val('');
  $('#patient-drivers-license').val('');
  $('#patient-dob').val('');
  $('#patient-gender').val('');
  $('#patient-marital-status').val('');
  $('#patient-race').val('');
  $('#patient-ethnicity').val('');
  $('#patient-address1').val('');
  $('#patient-address2').val('');
  $('#patient-city').val('');
  $('#patient-us-state').val('');
  $('#patient-postal-code').val('');
  $('#patient-status').val('');
  $('#patient-occupation').val('');
  $('#patient-employed').val('');
  $('#patient-employer').val('');
  $('#patient-school').val('');
  $('#patient-school-name').val('');
  $('#patient-primary-phone').val('');
  $('#patient-secondary-phone').val('');
  $('#patient-email').val('');
  $('#patient-ins-carr').val('');
  $('#patient-member-number').val('');
  $('#patient-group-number').val('');
  $('#patient-insured-name').val('');
  $('#patient-clinician').val('');
  util_clearErrors();  
}

function app_patientIsActive() {
  return app_patient && (app_patient.status == USER_ACTIVE)
}
function app_setupPictureUpload($photo, options={}) {
  $('#patient-photo-upload').click(function(){ 
    $('#patient-photo-upload-progress .progress-bar').css('width', '0');
  });
  uploader = new qq.FileUploader(_.extend({
   element: document.getElementById('patient-photo-upload'),
   action: 'patientFile/uploadProfileImage?sessionId=' + app_getSessionId() + (app_patientId != null ? '&patientId='+app_patientId : ''),
   debug: true,
   allowedExtensions: ['jpg', 'jpeg', 'png', 'gif'],
   sizeLimit: 1048576,  
   onProgress: function(id, fileName, loaded, total) {
      var progress = parseInt(loaded / total * 100, 10);
      $('#patient-photo-upload-progress .progress-bar').css('width', progress + '%');
   },
   onComplete: function(id, fileName, responseJSON) {
     $('#patient-photo-upload-progress .progress-bar').css('width', '100%');
     var response = parsedData = $.parseJSON(responseJSON);
     app_profileImageTempPath = response.filename;
     var photoEl = $photo || $("#patient-photo");
     if (app_patientIsActive()) {
       app_patientProfileImage = 
       'patient/getPatientProfileImage?sessionId=' + app_getSessionId() + '&patientId=' + app_patient.id  + '&module=' + app_module;
       photoEl.attr('src', app_patientProfileImage);
     }
     else {
       photoEl.attr('src', 'patient/getTempPatientProfileImage?sessionId=' + app_getSessionId() + "&profileImagePath=" + app_profileImageTempPath);
     }
   }
  }, options)); 
}



function app_updatePatientInfoForms() {
 
  var forms = $('input[name=forms]:checked').map(function() {return this.value;}).get().join(',');
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(),
    patientId: app_patientId,
    forms: forms
  });
  $.post("patient/updatePatientInfoForms", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
  });
 
}



function app_updatePatientInfoPrograms() {
  var programs = $('input[name=programs]:checked').map(function() {return this.value;}).get().join(',');
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(),
    id: app_patientId,
    updateProperty:'entity-Patient.programs',
    updatePropertyValue: programs,
    updatePropertyFieldType:'text'
  });
  $.post("app/updateField", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
  });
}



function pm_updateSavedPatient(property, value, id) {
  var jsonData = JSON.stringify({
    sessionId: app_getSessionId(), 
    module:app_module,
    updateProperty:property,
    updatePropertyValue:value,
    id: id
  });
  $.post("patient/updatePatient", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
  }); 
}
