// test for Grade A browser support.
if (!bowser.a) { 
  document.location = 'browser_upgrade.html';
}


/************      @JQUERY INIT    *******************/
$(document).ready(function() {
  $(document).mousemove( function(){ app_timerReset(); });
  window.onbeforeunload = app_confirmBeforeUnload; 
  app_getClientProperties();
});
/***********      @JQUERY INIT    *******************/


function app_getPatientFormId() {
  if (app_patientForm) {
    return app_patientForm.parentPatientFormId || app_patientForm.id;
  } else {
    return app_patientFormId;
  }
}

function app_blockingSpinnerEnd() {
  $("#app-blocking-spinner-layer").css({display:'none'});
}

function app_setEvalMode(mode) {
  app_evalMode = mode;
}

function app_updateField(jsonData, callback, $elem, options={}) {
  var afterUpdateHandler;
  if ($elem) {
   afterUpdateHandler = $elem.data('afterUpdate');
  }
  var updateFieldPath = options.multiple ? "app/updateFields" : "app/updateField";
  app_post(updateFieldPath, jsonData, function(parsedData) {
    if ($elem) {
      util_clearItemError($elem);
    }
    if (parsedData.result == false && $elem) {
        util_showError($elem, parsedData.errorMsg);   
    }
    if (afterUpdateHandler) {
      window[afterUpdateHandler]($elem, parsedData.result, parsedData);
    }
    if (parsedData.result == true && callback) {
      callback(parsedData);
    }  
  }) 
}


function app_blockingSpinnerStart() {
  $("#app-blocking-spinner-layer").css({display:'flex'});
  setTimeout(app_blockingSpinnerEnd, 5000);
}



function app_confirmBeforeUnload() {
  if (app_client && app_client != null) {
    return "Please log out first in order to save your data."; 
  }
}



function app_displayNotification(text, sticky) {
  $('.wdm-notification-text').html(text);
  if (sticky) {
    $('.wdm-notification-text').fadeIn(400);
  }
  else {
    $('.wdm-notification-text').fadeIn(400).delay(3000).fadeOut(400); 
  }
}


function app_getEntityClassName(entity, options={}) {
  var specialty = SPECIALTY;
  if (options.withoutSpecialty) {
    specialty = undefined
  }
  return _.without(["com.wdeanmedical", specialty, "entity", entity], undefined).join('.');
}

function app_getClientProperties() {
  var jsonData = JSON.stringify({id:0});
  $.post("app/getClientProperties", {data:jsonData}, function(data) {
    parsedData = $.parseJSON(data);
    app_clientProperties = parsedData.clientProperties;
    app_practiceClientProperties = parsedData.practiceClientProperties;
    app_specialtyClientProperties = parsedData.specialtyClientProperties;
    PRACTICE = app_practiceClientProperties['practice'];
    SPECIALTY = app_practiceClientProperties['specialty'];
    loader_loadSpecialtyScripts(function() {
      module_selectModuleFromUrl();
    })
 });
}



function app_getClinicianPatients() {
  var jsonData = JSON.stringify({id: (app_selectedClinician != "" ? app_selectedClinician : 0), sessionId: app_getSessionId(), module:app_module });
  $.post("app/getPatients", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_clinicianPatients = parsedData.list;

    app_clinicianPatientsSelectOptions = "<option value=''>choose</option>";   
    for (var i=0;i<app_clinicianPatients.length;i++){
      app_clinicianPatientsSelectOptions += "<option value='"+app_clinicianPatients[i].patient.id+"'>"+
      util_buildFullName(app_clinicianPatients[i].patient.firstName, app_clinicianPatients[i].patient.middleName, app_clinicianPatients[i].patient.lastName) +
      "</option>";
    }
    $("#app-appt-patient").html(app_clinicianPatientsSelectOptions);  
  });
}



function app_getClinicians() {
  var jsonData = JSON.stringify({sessionId: app_getSessionId(),  module:app_module});
  $.post("app/getClinicians", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_clinicians = parsedData.list;
    RenderUtil.render('component/person_select_options', {options:app_clinicians}, function(s) {
      $(".patient-clinician").html(s);
    });
  });
}



function app_getColumnValue(column, item) {
  var value = '';
  var columnFields = column.field.split('.'); 
    
  if (column.type == 'simple' || column.type == 'numeric') {
    if (item[column.field] === undefined) {
      value = ''; 
    }
    else {
      value = item[column.field];
    }
  }
  else if (column.type == 'yes-no') {
    value = item[column.field] == true ? 'Yes' : 'No';
  }
  else if (column.type == 'closed') {
    value = item[column.field] == true ? 'Closed' : 'Open';
  }
  else if (column.type == 'currency' && item[column.field]) {
    value = item[column.field].formatMoney();
  }
  else if (column.type == 'dollar' && item[column.field]) {
    value = '$' + item[column.field].formatMoney();
  }
  else if (column.type == 'date') {
    if (item[column.field]) {
      value = dateFormat(item[column.field], 'mm/dd/yyyy')
    }
  }
  else if (column.type == 'date-time') {
    if (item[column.field]) {
      value = dateFormat(item[column.field], 'mm/dd/yyyy hh:mm')
    }
  }
   else if (column.type == 'patient') {
    value = item[column.field];
    value = util_buildFullName(item['firstName'], item['middleName'], item['lastName'])
  }
   else if (column.type == 'clinician') {
    value = item[column.field];
    if (!value) {
      if (item.clinician) {
        var clinician = item.clinician;
        value = util_buildFullName(clinician['firstName'], clinician['middleName'], clinician['lastName'])
      } else {
        value = "";
      }
    }  
  } else if (column.type == 'double') {
    var field0 = columnFields[0];
    if (field0 && item[field0]) {
      var field1 = columnFields[1];
      value = item[field0][field1];
    }
  }  
  else if (column.type == 'double-date') {
    var field0 = columnFields[0];
    if (field0 && item[field0]) {
      var field1 = columnFields[1];
      value = item[field0][field1];
      value = dateFormat(value, 'mm/dd/yyyy')
    }
  }
  else if (column.type == 'double-patient') {
    var field0 = columnFields[0];
    var field1 = columnFields[1];
    value = item[field0][field1];
    value = util_buildFullName(item[field0]['firstName'], item[field0]['middleName'], item[field0]['lastName'])
  }
  else if (column.type == 'triple') {
    var columnFields = column.field.split('.'); 
    var field0 = columnFields[0];
    var field1 = columnFields[1];
    var field2 = columnFields[2];
    value = item[field0][field1][field2];
  }
  else if (column.type == 'html') {
    if (item[column.field] === undefined) {
      value = ''; 
    }
    else{
      value = util_stripHtml(item[column.field]);
    }
  }
  else if (column.type == 'soap-note') {
    if (item[column.field] === undefined) {
      value = ''; 
    }
    else{
      value = util_stripHtml(item[column.field]);
      value =  util_truncate(value, 50);
    }
  }
  else if (column.type == 'strip-html') {
    if (item[column.field] === undefined) {
      value = ''; 
    }
    else{
      value = util_stripHtml(item[column.field]);
      value =  util_truncate(value, 100);
    }
  }
  else if (column.type == 'double-person') {
    var field0 = columnFields[0];
    var field1 = columnFields[1];
    if (item[field0] === undefined) {
      return value; 
    }
    value = util_buildFullName(item[field0]['firstName'], item[field0]['middleName'], item[field0]['lastName'])
  }
  else if (column.type == 'triple-person') {
    var field0 = columnFields[0];
    var field1 = columnFields[1];
    var field2 = columnFields[2];
    if (item[field0] === undefined || item[field0][field1] === undefined) {
      return value; 
    }  
    value = util_buildFullName(item[field0][field1]['firstName'], item[field0][field1]['middleName'], item[field0][field1]['lastName'])
  }
  else if (column.type == 'triple') {
    var field0 = columnFields[0];
    var field1 = columnFields[1];
    var field2 = columnFields[2];
    if (item[field0] === undefined || item[field0][field1] === undefined) {
      return value; 
    }  
    value = item[field0][field1][field2];
  }
  else if (column.type == 'triple-date') {
    var field0 = columnFields[0];
    var field1 = columnFields[1];
    var field2 = columnFields[2];
    if (item[field0] === undefined || item[field0][field1] === undefined) {
      return value; 
    }  
    value = dateFormat(item[field0][field1][field2], 'mm/dd/yyyy')
  }
  else if (column.type == 'quad') {
    var field0 = columnFields[0];
    var field1 = columnFields[1];
    var field2 = columnFields[2];
    var field3 = columnFields[3];
    if (item[field0] === undefined || item[field0][field1] === undefined || item[field0][field1][field2] === undefined) {
      return value; 
    }  
    value = item[field0][field1][field2][field3];
  }
  return value;
}



function app_getStaticLists() {
  var jsonData = JSON.stringify({module:app_module});
  app_post("app/getStaticLists", jsonData, function(parsedData) {
    var staticLists = parsedData.staticLists;
    pm_addressTypes = staticLists.addressTypes;
    app_complaints = staticLists.complaints;
    app_credentials = staticLists.credentials;
    app_diagnoses = staticLists.diagnoses;
    app_gender = staticLists.gender;
    app_maritalStatus = staticLists.maritalStatus;
    pm_networkMarketingSources = staticLists.networkMarketingSources;
    app_programs = staticLists.programs;
    pm_referralSourceTypes = staticLists.referralSourceTypes;
    app_roles = staticLists.roles;
    pm_salesLeadStatuses = staticLists.salesLeadStatuses;
    pm_salesLeadEmailStatuses = staticLists.salesLeadEmailStatuses;
    pm_salesLeadCallStatuses = staticLists.salesLeadCallStatuses;
    pm_salesLeadStages = staticLists.salesLeadStages;
    pm_salesLeadAgeRanges = staticLists.salesLeadAgeRanges;
    pm_salesLeadSources = staticLists.salesLeadSources;
    pm_salesLeadGoals = staticLists.salesLeadGoals;
    app_usStates = staticLists.usStates;
    app_cptModifiers = staticLists.cptModifiers;
 });
}



function app_handleClickableRow(e) {
  var id = undefined;
  var tableId = undefined;
  var tableName = undefined;
  var attributes = e.currentTarget.attributes;
  for (i=0;i<attributes.length;i++) {
    if (attributes[i].name == 'name') {
      id = attributes[i].value; 
    }
    else if (attributes[i].name == 'id') {
      tableId = attributes[i].value; 
    }
    else if (attributes[i].name == 'data-table-name') {
      tableName = attributes[i].value; 
    }
  }

  if (id !== undefined) {
    if (tableName == 'lead-mgmt-action-list') {
      pm_currentSalesLeadActionId = id; 
      pm_loadSalesLeadActionForm();
    }
    else if (tableName == 'lead-mgmt-task-list') {
      pm_currentSalesLeadTaskId = id; 
      pm_loadSalesLeadTaskForm();
    }
  }
}

function app_patientFilter() {
  var dob = util_processDob("#patient-search-dob", dob);
  var filter =  {
    firstNameFilter: $.trim($("#patient-search-first-name").val()),
    middleNameFilter: $.trim($("#patient-search-middle-name").val()),
    lastNameFilter: $.trim($("#patient-search-last-name").val()),
    mrnFilter: $.trim($("#patient-search-mrn").val()),
    cityFilter: $.trim($("#patient-search-city").val()),
    genderFilter: $.trim($("#patient-search-gender").val()),
    phoneFilter: $.trim($("#patient-search-phone").val()),
    dobFilter: dob
  }  
  if ($("#patient-search-status").length) {
   filter =  _.extend(
      filter,  
      { statusFilter: $.trim($("#patient-search-status").val()) }
    )
    
  }
  return filter;
}

function app_setClient(parsedData) {
   app_client = parsedData.client;
   app_guardian = null;
   if (parsedData.guardian) {
    app_guardian = parsedData.guardian;
    app_guardian.sessionId = parsedData.sessionId;
    app_client = app_guardian;
   } 
   if (parsedData.clientType) {
    app_clientType = parsedData.clientType;
   }
   if (!app_client) {
    app_client = {authStatus: parsedData.authStatus}
   }
}

function app_login(clientType) {
  if($.trim($("#app-signin-username").val()).length < 1 || $.trim($("#app-signin-password").val()).length < 1) { 
    return;
  }
  var username = $.trim($("#app-signin-username").val());
  var password = $.trim($("#app-signin-password").val());
    
  app_blockingSpinnerStart();
  
  var jsonData = JSON.stringify({ username: username, password: password, clientType:(clientType || app_clientType), module:app_module});
  $.post("app/login", {data:jsonData}, function(data) {
    app_blockingSpinnerEnd();
    $('#app-login-error').css({display:'none'});
    var parsedData = $.parseJSON(data);
    app_setClient(parsedData);
    
    if (app_client.authStatus == CLIENT_STATUS_AUTHORIZED) {
      app_clientFullName = util_buildFullName(app_client.firstName, app_client.middleName, app_client.lastName);
      app_notificationText = app_clientFullName + ' logged in.';
      app_runIdleTimer(); 
      
      if (app_module == PM_MODULE) {
        pm_getAppLists();
        app_viewStack('dashboard-screen', DO_SCROLL); 
        app_client.roleId == 1 ? $('#main-nav-admin').show() : $('#main-nav-admin').hide();
      }
      else if (app_module == PORTAL_MODULE) {
        portal_onLogin();
      }
    }  
    else  {
      if (app_client.authStatus == CLIENT_STATUS_NOT_FOUND) {
        app_notificationText = 'User not found in system';
      }
      else if (app_client.authStatus == CLIENT_STATUS_INVALID_PASSWORD) {
        app_notificationText = 'Invalid password';
      }
      else if (app_client.authStatus == CLIENT_STATUS_INACTIVE) {
        app_notificationText = 'User is inactive';
      }
    }
    app_displayNotification(app_notificationText);
  });  
}

function app_post(path, data, callback) {
  var jsonData = data;
  if (_.isObject(data)) {
    jsonData = JSON.stringify(data);
  }
  return $.post(path, {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    if (callback) {
      callback(parsedData)
    }
  })  
}


function app_logout(callback) {
  if (app_client == null) {
    if (callback) {
      callback();
    }
    return;
  }
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module });
  $.post("app/logout", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    app_viewStack('signin-screen', DO_SCROLL);
    app_notificationText = app_clientFullName + ' logged out.';
    if (app_idleInterval) {clearInterval(app_idleInterval)};
    app_displayNotification(app_notificationText);
    app_client = null;
    app_patient = null;
    app_patientId = null;
    if (callback) {
      callback();
    }
  });
}



function pm_park() {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module });
  $.post("app/park", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    if (app_idleInterval) {clearInterval(app_idleInterval)};
  });
}



function app_printCurrentlyOpenedForm() {
  if (form_formIsOpened()) {
    form_printForm();
  }
}



function app_renderPasswordForm(_option={}, callback) {
  var option = _.extend({mode: "Create"}, _option);
  var mode = option.mode;
  var defaultArgs = {
    title: mode + " Password",
    modalH2: "Please ".concat(mode).concat(" Your User Password.")
  }
  var args = _.extend(defaultArgs, option, {securityQuestions:false});
  RenderUtil.render('dialog/password', args, function(s) { 
    $('#modal-create-password').remove(); 
    $('#modals-placement').html(s);
    $('#modal-create-password').modal('show'); 
    $('#app-create-password-submit').click(function(){ app_submitCreatePasswordForm(option, callback); });
  });
}



function app_runIdleTimer() {
  app_idleTime = 0;
  if (app_idleInterval) {clearInterval(app_idleInterval)};
  app_idleInterval = setInterval(app_timerIncrement, ONE_MINUTE);
}



function app_sendCredentialsRecovery(labelUC) {
  util_clearErrors();
  var email = $.trim($('#credentials-recovery-email').val());
  var emailValid = util_checkRegexp(email, util_emailRegexObj);
  if(emailValid == false) {
    util_showError($('#credentials-recovery-email'), 'invalid email address');
    return;
  }
  $("#credentials-recovery-email-form, #credentials-recovery-cancel, #credentials-recovery-submit").hide();
  $("#credentials-recovery-response, #credentials-recovery-ok").show();
  $("#credentials-recovery-response").html(labelUC + ' recovery information sent to ' + email);
  var jsonData = JSON.stringify({ email: email, module:app_module});
  $.post("app/sendCredentialsRecovery", {data:jsonData},
    function(data) {
      var parsedData = $.parseJSON(data);
      if (!util_checkSessionResponse(parsedData)) return false;
  });
}



function app_showCredentialsRecoveryDialog() {
  var  labelUC = app_module == PORTAL_MODULE ? 'Password' : 'Credentials';
  var  label = app_module == PORTAL_MODULE ? 'password' : 'credentials';
  RenderUtil.render('dialog/credentials_recovery', {label:label, labelUC:labelUC}, function(s) { 
    $('#modal-credentials-recovery').remove(); 
    $('#modals-placement').html(s);
    $('#modal-credentials-recovery').modal('show'); 
    $('#credentials-recovery-submit').click(function(){ app_sendCredentialsRecovery(labelUC); });
  });
}



function app_showParkDialog() {
  RenderUtil.render('dialog/park', {}, function(s) { 
    $('#modal-park').remove(); 
    $('#modals-placement').html(s);
    $('#app-parked-full-name').html(app_clientFullName);
    $('#modal-park').modal('show'); 
    pm_park();
    $('#app-unpark-submit').click(function(){ app_unpark(); });
    $('#app-park-logout').click(function(){ app_logout(); });
  });
}

function app_validatePasswordForm($password, $passwordConfirm) {
  var password = $.trim($password.val());
  var passwordConfirm = $.trim($passwordConfirm.val());
  var isValid = true;
  if(password.length < 1) { 
    util_showError($password); isValid = false; 
  }
  if (password.length > 0) {
    if(password.length < 6 || passwordConfirm.length < 6) { 
      util_showError($password, 'must be at least 6 chararcters long'); 
      isValid = false; 
    }
    if(util_checkPassword(password) == false) { 
      util_showError($password, 'must contain a lowercase, uppercase, digit, and special character'); 
      isValid = false; 
    }
    if(password != passwordConfirm) { 
      util_showError($password, 'passwords must match'); 
      isValid = false; 
    }
  }
  return isValid;
}

function app_getPasswordUserId() {
  if (app_module == PM_MODULE) {
    return app_client.id 
  } else {
    if (app_guardian) {
      return app_guardian.id;
    } else {
      return app_client.id
    }
  } 
}

function app_getSessionId() {
  if (app_guardian && app_guardian.sessionId) {
    return app_guardian.sessionId;
  } else {
    return app_client.sessionId;
  }
}
function app_submitCreatePasswordForm(option, callback) {
  var isValid = true;
  util_clearErrors();
  var ssn = "";
  var driversLicense = "";
  var $newPassword = $("#app-new-password");
  isValid = app_validatePasswordForm($newPassword, $("#app-new-password-confirm"))
  var withSecurityQuestions = app_practiceClientProperties['app.security_questions'] == true;
  if (withSecurityQuestions) {
    ssn = $.trim($("#app-new-password-ssn").val());
    driversLicense = $.trim($("#app-new-password-drivers-license").val());
    if (ssn.length < 4 && driversLicense.length < 4) { 
      util_showError($('#app-new-password-ssn'), 'SSN or Drivers License must be 4 digits'); 
      isValid = false; 
    }
  }
  
  if (isValid) {  
    var password = $newPassword.val();
    var params = { id: app_getPasswordUserId(), 
      module:app_module,
      mode: option.mode,
      password: password,
      sessionId: app_getSessionId(),
      clientType: app_clientType  
    }
    if (withSecurityQuestions) {
      params = _.extend(params, {
         govtId: ssn, 
        driversLicense: driversLicense
      })
    }    
    var jsonData = params;   
    var isPatient = option.isPatient;                         
    app_post((isPatient == true && withSecurityQuestions ? 'patient' : 'app') + '/createPassword', jsonData, function(parsedData) {
      if (parsedData.returnCode == RETURN_CODE_VALID) {
        $('#modal-create-password').modal('hide');
        var event = option.mode == "Create" ? "Created" : "Updated";
        app_displayNotification('Password Successfully ' + event);
        if (callback) {callback()}
      }
      else {
        if (parsedData.returnCode == RETURN_CODE_INVALID_PASSWORD) {
          util_showError($('#app-new-password'), parsedData.errorMsg); 
        }
        else if (parsedData.returnCode == RETURN_CODE_INVALID_SSN) {
          util_showError($('#app-new-password-ssn'), parsedData.errorMsg); 
        }
      }
    });
  }
}



function app_timerIncrement() {
  app_idleTime++;
  if (app_idleTime == 660) {
    if (app_module == PM_MODULE) {
      app_displayNotification('Your session will soon be automatically parked if still idle', true);
      app_parkWarningDisplayed = true;
    }
    else if (app_module == PORTAL_MODULE) {
      app_displayNotification('You will soon be automatically signed out if still idle', true);
    }
  }
  else if (app_idleTime == 690) {
    if (app_module == PM_MODULE) {
      app_showParkDialog();
    }
    else if (app_module == PORTAL_MODULE) {
      app_logout();
    }
  }
}



function app_timerReset() {
  if (app_parkWarningDisplayed) { 
    $('#wdm-notification-text').html('');
    app_parkWarningDisplayed = false;
  }
  app_idleTime = 0;
}



function app_unpark() {
  var notificationText;
  var username = $.trim($("#app-unpark-username").val());
  var password = $.trim($("#app-unpark-password").val());
  
  if (username.length < 1 || password.length < 1) { 
    notificationText = 'Username and Password Required';
    $("#app-unpark-notification").html(notificationText);
    return;
  }
  
  var jsonData = JSON.stringify({ username: username, password: password, sessionId: app_getSessionId(), clientType:app_clientType});
  $.post("app/unpark", {data:jsonData},
    function(data) {
      var parsedData = $.parseJSON(data);
      if (!util_checkSessionResponse(parsedData)) return false;
      var authStatus = parsedData.client.authStatus;
        
      if (authStatus == CLIENT_STATUS_AUTHORIZED) {
        notificationText = app_clientFullName + ' unparked.';
        $('#modal-park').modal('hide'); 
        app_displayNotification(notificationText);
        app_runIdleTimer(); 
      }  
      else  {
        if (authStatus == CLIENT_STATUS_NOT_FOUND) {
          notificationText = 'User not found in system';
        }
        else if (authStatus == CLIENT_STATUS_INVALID_PASSWORD) {
          notificationText = 'Invalid password';
        }
        else if (authStatus == CLIENT_STATUS_INACTIVE) {
          notificationText = 'User is inactive';
        }
        $("#app-unpark-notification").html(notificationText);
      }
    }
  ); 
}
