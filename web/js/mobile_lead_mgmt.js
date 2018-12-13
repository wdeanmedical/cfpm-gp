
var CLIENT_STATUS_AUTHORIZED = 1;
var CLIENT_STATUS_NOT_FOUND = 0;
var CLIENT_STATUS_INVALID_PASSWORD = -1;
var CLIENT_STATUS_INACTIVE = -2;

var app_client = null;
var app_clientFullName;
var app_clinicians;
var app_network;
var app_location;
var app_module = "pm";
var app_clientType = "user";



$('#app-signin-submit').off('click').on('click', function(){ app_login(); });
$('#patient-submit-btn').off('click').on('click', function(){ 
 if(app_evaluateSalesLeadInput()){
  app_saveNewSalesLead();
 } 
});



$('#add-another-patient-btn').off('click').on('click', function() { 
  app_clearForm();
  $('#sales-lead-screen').show();
  $('#sales-lead-saved').hide();
});



function app_clearForm() {
  util_clearErrors();  
  $('#patient-first-name').val('');
  $('#patient-middle-name').val('');
  $('#patient-last-name').val('');
  $('#patient-gender').val('');
  $('#patient-phone').val('');
  $('#patient-email').val('');
  $('#best-time').val('');
  $('#patient-note').val('');
}



function app_displayNotification(text, sticky) {
  $('.wdm-notification-text').html(text);
  if (sticky) {
    $('.wdm-notification').fadeIn(400);
  }
  else {
    $('.wdm-notification').fadeIn(400).delay(3000).fadeOut(400); 
  }
}



function app_evaluateSalesLeadInput() {
  var isValid = true;
  util_clearErrors();
  
  if (util_isFieldEmpty('#patient-first-name')) {
    util_showError($('#patient-first-name')); 
    isValid = false; 
  }
  if (util_isFieldEmpty('#patient-last-name')) {
    util_showError($('#patient-last-name')); 
    isValid = false; 
  }
  if (util_isFieldEmpty('#patient-gender')) {
    util_showError($('#patient-gender')); 
    isValid = false; 
  }
  if (util_isFieldEmpty('#patient-age-range')) {
    util_showError($('#patient-age-range')); 
    isValid = false; 
  }
  if (util_isFieldEmpty('#patient-phone') && util_isFieldEmpty('#patient-email')) {
  util_showError($('#patient-phone'), 'Phone or Email is required'); 
    isValid = false; 
  }
  if (!util_isFieldEmpty('#patient-email')) {
    var emailValid = util_checkRegexp($.trim($("#patient-email").val()), util_emailRegexObj);
    if(emailValid == false) { 
      util_showError($('#patient-email'), 'invalid email address'); 
      isValid = false;
    }
  }
  if (!util_isFieldEmpty('#patient-phone')){
    var phoneValid = util_checkRegexp($.trim($("#patient-phone").val()), util_phoneRegexObj);
    if(phoneValid == false) { 
      util_showError($('#patient-phone'), 'invalid phone number'); 
      isValid = false;
    }
  }  
  return isValid;
}



function app_getConsultantInfo() {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module});
  $.post("../app/getConsultantInfo", {data:jsonData}, function(data) {
    parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_location = parsedData.facility;
    app_network = parsedData.network;
    $('#location').html(app_location.name);
    $('#network').html(app_network.name);
 });
}



function app_login() {
  var notificationText;
  
  if($.trim($("#app-signin-username").val()).length < 1 || $.trim($("#app-signin-password").val()).length < 1) { 
    return;
  }
  var username = $.trim($("#app-signin-username").val());
  var password = $.trim($("#app-signin-password").val());
  
  var jsonData = JSON.stringify({ username: username, password: password, clientType:app_clientType, module: app_module});
  $.post("../app/login", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_client = parsedData.client;

    if (app_client.authStatus == CLIENT_STATUS_AUTHORIZED) {
      app_clientFullName = util_buildFullName(app_client.firstName, app_client.middleName, app_client.lastName);
      notificationText = app_clientFullName + ' logged in.';
      app_getConsultantInfo();
      app_clearForm();
      $('#signin-screen').hide();
      $('#sales-lead-screen').show();    
      $('#consultant-name').html(app_clientFullName);
    }  
    else  {
      if (app_client.authStatus == CLIENT_STATUS_NOT_FOUND) {
        notificationText = 'User not found in system';
      }
      else if (app_client.authStatus == CLIENT_STATUS_INVALID_PASSWORD) {
        notificationText = 'Invalid password';
      }
      else if (app_client.authStatus == CLIENT_STATUS_INACTIVE) {
        notificationText = 'User is inactive';
      }
    }
    app_displayNotification(notificationText);
  }
  );  
}

function app_saveNewSalesLead() {
  var jsonData = JSON.stringify({
  sessionId: app_getSessionId(),
  module:app_module,
  firstName: $.trim($('#patient-first-name').val()),
  middleName: $.trim($('#patient-middle-name').val()),
  lastName: $.trim($('#patient-last-name').val()),
  ageRangeId: $.trim($('#patient-age-range').val()),
  genderId: $.trim($('#patient-gender').val()),
  primaryPhone: $.trim($('#patient-phone').val()),
  email: $.trim($('#patient-email').val()),
  bestTimeToContact: $.trim($('#best-time').val()),
  note: $.trim($('#patient-note').val())
  });
   $.post("../leadmgmt/saveNewSalesLead", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    $('#sales-lead-screen').hide(); 
    $('#sales-lead-saved').show();
  });
}


