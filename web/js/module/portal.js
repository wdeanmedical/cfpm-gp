
function module_initPORTAL() {
  patientFile_loadScreen({portal:true});

  $(document).attr('title', app_practiceClientProperties['portal.app.title']);
  app_viewStack('signin-screen', DO_SCROLL);
    
  $('#portal-welcome-screen-image').attr('src', 'assets/images/practice/'+PRACTICE+'/'+app_practiceClientProperties['portal.app.main_image']); 
  $('.portal.practice-logo').attr('src', 'assets/images/practice/'+PRACTICE+'/'+app_practiceClientProperties['portal.app.practice_logo_sm']); 
  
  $('#app-signin-submit').click(function(){
    app_login($(this).data('client-type')); 
  });
  $('#app-change-patient').click(function() { 
    portal_patientSearchDialog();
  });
  $('#app-retrieve-credentials').click(function(){ app_showCredentialsRecoveryDialog(); });
  $('#dashboard-screen-btn').click(function(){portal_dashboardScreen()});
  $('#app-intake-forms-panel-btn').click(function(){portal_intakeFormsScreen()});
  $('#app-family-records-panel-btn').click(function(){portal_rxRenewalScreen()});
  $('#app-letters-panel-btn').click(function(){portal_lettersScreen()});
  $('#app-messages-panel-btn').click(function(){portal_messagesScreen()});
  $('#app-files-panel-btn').click(function(){portal_filesScreen()});
  $('#app-forms-panel-btn').click(function(){portal_formsScreen()});
  $('#app-payments-panel-btn').click(function(){portal_paymentsScreen()});
  $('#app-appointments-panel-btn').click(function(){portal_appointmentsScreen()});
  $('#app-send-message-panel-btn').click(function(){portal_sendMessageScreen()});
  $('#app-settings-panel-btn').click(function(){portal_settingsScreen()});
  $('#app-logout-submit').click(function(e){e.preventDefault();app_logout();});
  $('#send-message-submit').click(function(e){portal_sendMessageToProvider();});
  $('#app-rx-request-btn').click(function(e){portal_rxRequest_clearForm();});
  $('#rx-request-submit').click(function(e){portal_rxRequest();});
  
  $('#appt-request-from').datepicker();
  $('#appt-request-to').datepicker();
    
  var fromOffice = $.QueryString["fromOffice"];
  if (fromOffice == "true") {
    var tempSessionId = $.QueryString["tempSessionId"];
    app_module = $.QueryString["module"];
    portal_validateFromOffice(tempSessionId);
  }
    
  var activateUser = $.QueryString["activateUser"];
  if (activateUser == "true") {
    var activationCode = $.QueryString["activationCode"];
    app_logout(function(){
      portal_validateViaActivation(activationCode);
    })
  }
  
  var passwordRecovery = $.QueryString["passwordRecovery"];
  if (passwordRecovery == "true") {
    var recoveryCode = $.QueryString["recoveryCode"];
    portal_validateViaRecovery(recoveryCode);
  }
  
  setupAppointmentScreen();
}

function portal_patientSearchDialog(callback) {
  $(".modal-backdrop").remove(); 
  var args = {
    title: "Patient Select",
    list: app_patients
  }
  RenderUtil.render('dialog/pot/guardian_patient_select', args, function(s) {
    $('#modal-patient-select').remove(); 
    var $template = $(s);
    $('#modals-placement').html($template);
    $('#modal-patient-select').modal('show'); 
    $template.find('#guardian-patients').on('change', function(){
      app_patientId = $(this).val();
      $('#modal-patient-select').modal('hide'); 
      portal_getPatient();
    })
  });
}

function portal_getGuardianPatients(callback, filter={}) {
  var jsonData = _.extend({ 
    module:app_module,
    id: app_guardian.id, 
    sessionId: app_getSessionId() 
  }, filter);

  app_post("patient/getGuardianPatients", jsonData, function(parsedData){
    callback(parsedData);
  })  
}

function portal_appointmentsScreen() {
  app_viewStack('appointments-screen', DO_SCROLL);
  app_loadCalendar();
}


function portal_buildFormControls() {
   portal_getPatientClinicians();
}

function portal_showNoPatientsDialog(){
  dialog({
    modalTitle:"No clients", 
    modalBodyText:"You do not have any clients.",
    okButton:"Ok"
  });
}

function portal_setClientInfo() {
  app_patientFullName = util_buildFullName(app_patient.firstName, app_patient.middleName, app_patient.lastName);
  $('#dashboard-patient-full-name').html(app_patientFullName);
  app_patientProfileImage = app_getPatientProfileImagePath();
  $('#dashboard-patient-profile-photo').attr('src', app_patientProfileImage);
  var jsonData = { id: app_patient.id, sessionId: app_getSessionId() };
  app_post("patient/getClientInfo", jsonData, function(parsedData) {  
    var recentActivity = parsedData.recentActivity;
    RenderUtil.render('portal/dashboard/recent_activity', {clientId: app_patient.id, recentActivity: recentActivity}, function(s) {
        $("#dashboard-recent-activity").html(s);
    });
    var personalInfo = parsedData.personalInfo;
    RenderUtil.render('portal/dashboard/your_information', {personalInfo: personalInfo}, function(s) {
        $("#dashboard-your-information").html(s);
    });
  })   
}

function portal_dashboardScreen() {
  app_viewStack('dashboard-screen', DO_SCROLL);
  portal_setClientInfo();
}

function portal_filesScreen() {
  patientFile_viewPatientFiles();
}



function portal_formsScreen() {
  app_viewStack('portal-forms-screen', DO_SCROLL);
  app_getPatientForms();
}



function portal_getAppLists() {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), clientType:app_clientType});
  $.post("app/getAppLists", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    var appLists = parsedData.appLists;
    app_patientClinicians = appLists.patientClinicians;
 });
}



function portal_getPatientClinicians() {
  var jsonData = JSON.stringify({ id: app_client.id, sessionId: app_getSessionId() });
  $.post("patient/getPatientClinicians", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_patientClinicians = parsedData.list;
    RenderUtil.render('component/patient_clinician_select_options', {options:app_patientClinicians}, function(s) {
      $(".app-patient-clinicians-select").html(s);
    });
  });
}



function portal_intakeFormsScreen() {
  app_renderPatientIntakeScreen();
  app_viewStack('intake-forms-screen', DO_SCROLL);
}



function portal_lettersScreen() {
  app_viewStack('letters-screen', DO_SCROLL);
  portal_getPatientLetters();
}



function portal_messagesScreen() {
  app_viewStack('messages-screen', DO_SCROLL);
  portal_getPatientMessages();
}



function portal_rxRenewalScreen() {
  app_viewStack('rx-renewal-screen', DO_SCROLL);
}

function portal_settingsScreen() {
  app_viewStack('settings-screen', DO_SCROLL);
  $('#patient-photo').attr('src', app_getPatientProfileImagePath())
  app_patientId = app_patient.id;
  var sizeLimit = 1000 * 1024;
  app_setupPictureUpload(undefined, {
    sizeLimit: sizeLimit
  });
  var $passwordForm = $('#settings-password-form');
  var $password = $passwordForm.find('#password');
  var $passwordConfirm = $passwordForm.find('#password_confirmation');

  $('#password-form-submit').on('click', function(){
    if (app_validatePasswordForm($password, $passwordConfirm)) {
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), id: app_patient.id, password: $password.val()});
      app_post('patient/updatePassword', jsonData, function(parsedData) {
        util_clearItemError($password);
        if (parsedData.returnCode == RETURN_CODE_VALID) {
          app_displayNotification('Password successfully changed');
        }
        else {
          if (parsedData.returnCode == RETURN_CODE_INVALID_PASSWORD) {
            util_showError($password, parsedData.errorMsg); 
          }
        }   
      });
    }
  })
}



function portal_sendMessageScreen() {
  app_viewStack('send-message-screen', DO_SCROLL);
  $('#send-message-clinician').val('');
  $('#send-message-subject').val('');
  $('#send-message-message').val('');
  util_clearErrors();
}



function portal_validateFromOffice(sessionId) {
  app_viewStack('signin-screen', DO_SCROLL);
  var jsonData = JSON.stringify({sessionId: sessionId, module:app_module });
  $.post("patient/validateFromOffice", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_client = parsedData.client;
    app_patient = app_client;
    
    if (app_client.authStatus == CLIENT_STATUS_AUTHORIZED) {
      app_clientFullName = util_buildFullName(app_client.firstName, app_client.middleName, app_client.lastName);
      app_notificationText = app_clientFullName + ' logged in.';
      portal__setPatientApptName();
      $('.home-today').html(dateFormat("fullDate"));
      app_runIdleTimer(); 
      portal_dashboardScreen();
      if (app_client.intakeClosed == false) {
        app_notificationText = app_clientFullName + ' ready for activation.';
        app_renderPatientIntakeScreen();
      }
      else {
        portal_buildFormControls();
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
function portal_getPatient() {
  app_getPatient(function(){
    portal_patientInit();
  });
}

function portal__setPatientApptName(){
  var patientName=app_clientFullName;
  if(app_client.mrn){
    patientName+= "[" + app_client.mrn + "]";
  }
  $('.app-patient-appt-name').text(patientName);
}
function portal_patientInit() {
  app_clientFullName = util_buildFullName(app_client.firstName, app_client.middleName, app_client.lastName);
  portal__setPatientApptName();
  if (app_patient.intakeClosed == false) {
    app_renderPatientIntakeScreen();
  }
  else {
    portal_dashboardScreen(); 
    app_displayNotification(app_notificationText);
  }    
  portal_buildFormControls();
}

function portal_onLogin() {
  app_runIdleTimer(); 
  $('.home-today').html(dateFormat("fullDate"));
  app_clientFullName = util_buildFullName(app_client.firstName, app_client.middleName, app_client.lastName);
  app_notificationText = app_clientFullName + ' logged in.';
  if (app_clientType == "patient") {
    app_patient = app_client;
    portal_patientInit();
  } else if (app_clientType == "guardian")  {
    portal_getGuardianPatients(function(parsedData) {
      app_patients = parsedData.list;
      var numPatients = app_patients.length
      $('#app-change-patient').parent().hide()
      if (numPatients > 1) {
        $('#app-change-patient').parent().show()
        portal_patientSearchDialog();
      } else if (numPatients==1) {
        app_patient = app_client = app_patients[0];
        portal_patientInit();
      } else if(numPatients==0) {
        portal_showNoPatientsDialog();
      }
    })
  }
}
function portal_checkRenderPasswordForm(option={}, callback) {
  if (app_guardian) {
    if (app_guardian.passwordCreated != true) {
      app_renderPasswordForm(option, callback);
      return;
    }
  }
  else {
    if (app_client.passwordCreated != true) {
      app_renderPasswordForm(_.extend(option, {isPatient: true}), callback);
      return;
    }
  }    
  }

function portal_validateViaActivation(activationCode) {
  var notificationText = '';
  app_viewStack('signin-screen', DO_SCROLL);
  var jsonData = JSON.stringify({activationCode: activationCode, module:app_module });
    app_post("patient/validateViaActivation", jsonData, function(parsedData) {
    app_setClient(parsedData);
    if (app_client.authStatus == CLIENT_STATUS_AUTHORIZED) {
      portal_checkRenderPasswordForm({}, portal_onLogin);
    }  
    else  {
      if (app_client.authStatus == CLIENT_STATUS_ACTIVATION_CODE_EXPIRED) {
         app_notificationText = "Activation code expired.";
      } else if (app_client.authStatus == CLIENT_STATUS_ACTIVATION_CODE_ALREADY_USED) {
         app_notificationText = "Activation code has already been used.";
      } else if (app_client.authStatus == CLIENT_STATUS_PASSWORD_ALREADY_CREATED) {
         app_notificationText = "Please login";
      } else if (app_client.authStatus == CLIENT_STATUS_NOT_FOUND) {
        app_notificationText = 'User not found in system';
      }
      else if (app_client.authStatus == CLIENT_STATUS_INVALID_PASSWORD) {
        app_notificationText = 'Invalid password';
      }
      else if (app_client.authStatus == CLIENT_STATUS_INACTIVE) {
        app_notificationText = 'User is inactive';
      }
      app_displayNotification(app_notificationText);
    }
  });
}

function portal_validateViaRecovery(recoveryCode) {
  var notificationText = '';
  app_viewStack('signin-screen', DO_SCROLL);
  var jsonData = {recoveryCode: recoveryCode, module:app_module };
    app_post("patient/validateViaRecovery", jsonData, function(parsedData) {
    app_setClient(parsedData);
    if (app_client.authStatus == CLIENT_STATUS_AUTHORIZED) {
      portal_checkRenderPasswordForm({mode:PASSWORD_RESET}, portal_onLogin);
    }  
    else  { 
      if (app_client.authStatus == CLIENT_STATUS_RECOVERY_CODE_ALREADY_USED) {
         app_notificationText = "Recovery code has already been used.";
      }  else if (app_client.authStatus == CLIENT_STATUS_RECOVERY_CODE_EXPIRED) {
         app_notificationText = "Recovery code expired.  Please proceed to reset your password.";
      } else if (app_client.authStatus == CLIENT_STATUS_PASSWORD_ALREADY_CREATED) {
         app_notificationText = "Please login";
      } else if (app_client.authStatus == CLIENT_STATUS_NOT_FOUND) {
        app_notificationText = 'User not found in system';
      }
      else if (app_client.authStatus == CLIENT_STATUS_INVALID_PASSWORD) {
        app_notificationText = 'Invalid password';
      }
      else if (app_client.authStatus == CLIENT_STATUS_INACTIVE) {
        app_notificationText = 'User is inactive';
      }
      app_displayNotification(app_notificationText);
    }
  });
}
