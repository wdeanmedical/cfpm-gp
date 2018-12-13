
var pm_addressTypes;
var pm_currentSalesLead;
var pm_currentSalesActionId;
var pm_currentSalesLeadAction;
var pm_currentSalesLeadId;
var pm_currentSalesLeadTask;
var pm_currentSalesLeadTaskId;
var pm_frontSheet;
var pm_hideElementListCache;
var pm_networkMarketingSources;
var pm_referralSourceTypes;
var pm_salesLeads;
var pm_salesLeadActions;
var pm_salesLeadAgeRanges;
var pm_salesLeadCallStatuses;
var pm_salesLeadEmailStatuses;
var pm_salesLeadFullName;
var pm_salesLeadGoals;
var pm_salesLeadSources;
var pm_salesLeadStages;
var pm_salesLeadStatuses;
var pm_salesLeadTasks;
var pm_selectedLocationId;





function module_initPM() {
  patientFile_loadScreen();
  $(document).attr('title', app_practiceClientProperties['pm.app.title']);
  $('#app-signin-username').val(app_practiceClientProperties['pm.demo_username']);
  $('#app-signin-password').val(app_practiceClientProperties['pm.demo_password']);
  
  app_viewStack('signin-screen', DO_SCROLL);
    
  $('#about').click(function() { 
    RenderUtil.render('dialog/about', {}, function(s) { 
      $('#modal-about').remove(); 
      $('#modals-placement').html(s);
      $('#modal-about').modal('show'); 
    });
  });

  
  $('#app-dropdown-park').click(function(){ app_showParkDialog() });
  
  $('#app-close-chart').click(function() { 
    app_patient = null;
    app_patientId = null;
    $('#section-notification').css({display: "none"});
    $('#section-notification-text').html("");
    pm_viewDashboard();
  });
  
  $('#app-change-patient').click(function() { 
    app_patientSearchDialog();
  });
  
  $('.patient-button-group').click(function() { 
    if (app_patientIsActive()) {
      if (app_screen != 'patient-chart-screen') {
        app_getPatient();
      }
      return;
    }
    app_patientSearchDialog();
  });
  
  
  $('#btn-patient-search').click(function(){ 
    if (SPECIALTY == POT_SPECIALTY) {
      app_getFilteredPatientForms(); 
    }
    else {
      app_getFilteredPatients(); 
    }
  });
  
  $('#patient-chart-summary-link').click(function(){ 
    RenderUtil.render('form/chart_summary', {}, function(s) {
      $('#modal-chart-summary').remove(); 
      $('#modals-placement').html(s);
      $('#modal-chart-summary').modal('show'); 
      getPatientChartSummary();
      app_loadPatientInfo();
    });
  });
  
  
  $('#images-all-chart-sections-link').click(function() { 
    RenderUtil.render('images_all_chart_sections', {}, function(s) {
    $('#images-all-chart-sections').remove(); 
    $('#modals-placement').html(s);
    $('#images-all-chart-sections').modal('show'); 
    app_loadPatientInfo();
  });
  });
  
  $('#app-signin-submit').click(function(){ app_login(); });
  $('#app-retrieve-credentials').click(function(){ app_showCredentialsRecoveryDialog(); });
  $('.app-exit').click(function(){ app_logout(); });
  
  $('.app-dashboard-link').click(function(){ pm_viewDashboard(); });
  $('.app-admin-link').click(function(){ app_viewAdminScreen(); });
  $('.app-print-link').click(function(){ app_printCurrentlyOpenedForm(); });  
  $('.app-messages-link').click(function(){ app_viewMessages(MESSAGE_INBOX);});
  $('.app-schedule-link').click(function(){ pm_viewSchedule(); });
  $('#message-view-button').click(function(){ app_viewClinicianMessage(); });
  $('#medical-history-link').click(function(){ pm_viewMedicalHistory(); });
  $('.app-reports-link').click(function() { pm_viewReports(); });
  
  $('a.nav').click(function() { 
    $('a.nav-selected').addClass('nav');
    $('a.nav').removeClass('nav-selected');
    if ($(this).hasClass('toggle-selectable') ) {
      $(this).removeClass('nav');
      $(this).addClass('nav-selected');
    }
  });
  
  
  var passwordRecovery = $.QueryString["passwordRecovery"];
  if (passwordRecovery == "true") {
    var recoveryCode = $.QueryString["recoveryCode"];
    pm_validateViaRecovery(recoveryCode);
  }
}



function pm_getAppLists() {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), clientType:app_clientType});
  $.post("app/getAppLists", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    var appLists = parsedData.appLists;
    app_apptTypes = appLists.apptTypes;
    app_clinicians = appLists.clinicians;
    app_clinicianInfo = appLists.clinicianInfo;
    app_patientInfo = appLists.patientInfo;
    app_users = appLists.users;
    app_locations = appLists.locations;
    app_clinicianLocations = appLists.clinicianLocations;
    app_userLocations = appLists.userLocations;
    pm_networkMarketingSources = appLists.networkMarketingSources;
    RenderUtil.render('component/basic_select_options', {options:app_locations, collection:'app_locations', choose:true}, function(s) { $("#lead-mgmt-location").html(s); });
    
   });
   if (SPECIALTY == POT_SPECIALTY) {
      pot_pm_getGoalBankDescriptions();
   }
}


function pot_pm_getGoalBankDescriptions() {
  app_ltgDescriptions = null;
  app_stgDescriptions = null;
  var jsonData = JSON.stringify({sessionId: app_getSessionId(), module:app_module });
  $.post("patient/getGoalBankDescriptions", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_ltgDescriptions = parsedData.ltgDescriptions;
    app_stgDescriptions = parsedData.stgDescriptions;
  });
}


function pm_viewPatientFiles() {
  patientFile_viewPatientFiles()
}

function pm_viewSchedule() {
  app_viewStack('schedule-screen', DO_SCROLL);
}

function pm_viewAdminScreen() {
  app_viewStack('admin-screen', DO_SCROLL);
}


function pm_viewDashboard() {
  app_viewStack('dashboard-screen', DO_SCROLL);
}

function pm_viewFrontSheet(){  
  app_viewStack('front-sheet-screen', DO_SCROLL); 
}

function pm_viewMedicalHistory() {  
 app_viewStack('medical-history-screen', DO_SCROLL);
}


function pm_closeChart() {
  app_patient = null;
  app_patientId = null;
  app_guardian = null;
  $('#section-notification').css({display: "none"});
  $('#section-notification-text').html("");
  pm_viewDashboard();
}



function pm_getUserDashboard() {
  var jsonData = JSON.stringify({ id: app_client.id, sessionId: app_getSessionId(), module:app_module });
  $.post("app/getUserDashboard", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_userDashboard = parsedData.dashboard;
    RenderUtil.render('component/simple_data_table', 
     {items: app_userDashboard.messages, 
      title:'Messages', 
      clickable:true, 
      columns:[
        {title:'Date', field:'date', type:'date'}, 
        {title:'From', field:'from', type:'simple'}, 
        {title:'Subject', field:'subject', type:'simple'}
      ]}, function(s) {
        $('#user-dashboard-contact-messages').html(s);
        $('.clickable-table-row').click( function(e){ 
          $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
      });
    });
  });
}



function pm_validateViaRecovery(recoveryCode) {
  var notificationText = '';
  var jsonData = {recoveryCode: recoveryCode, module:app_module };
    app_post("app/validateViaRecovery", jsonData, function(parsedData) {
    app_client = parsedData.client;
    if (app_client.authStatus == CLIENT_STATUS_AUTHORIZED) {
      app_clientFullName = util_buildFullName(app_client.firstName, app_client.middleName, app_client.lastName);
      app_notificationText = app_clientFullName + ' logged in.';
      app_runIdleTimer(); 
      app_viewStack('dashboard-screen', DO_SCROLL); 
      app_renderPasswordForm({isPatient: false, mode: PASSWORD_RESET});
    }  
    else  {
      if (app_client.authStatus == CLIENT_STATUS_RECOVERY_CODE_ALREADY_USED) {
         app_notificationText = "Recovery code has already been used.";
      } else if (app_client.authStatus == CLIENT_STATUS_RECOVERY_CODE_EXPIRED) {
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
    }
    app_displayNotification(app_notificationText);
  });
}


