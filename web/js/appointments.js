

function setupAppointmentScreen() {
  $('#app-past-appointments-btn').click(function(e){getPastAppointments();});
  $('#app-upcoming-appointments-btn').click(function(e){getUpcomingAppointments();});
  $('#app-appt-request-btn').click(function(e){apptRequest_clearForm();});
  $('#appt-request-submit').click(function(e){apptRequest();});
}



function getUpcomingAppointments() {
  var jsonData = JSON.stringify({ id: app_patient.id, sessionId: app_getSessionId(), module:app_module });
  $.post("cal/getUpcomingAppointments", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    upcomingAppointments = parsedData.list;
    RenderUtil.render('component/simple_data_table', 
    {items:upcomingAppointments, 
    title:'Appointments', 
    columns:[{title:'Date/Time', field:'startTime', type:'date-time'}, 
             {title:'Description', field:'clinician.firstName', type:'description'}, 
             {title:'Department', field:'department.name', type:'name'}]},
    function(s) {
      $('#upcoming_appointments_table').html(s);
    });
  });
}
  

function getPastAppointments() {
  var jsonData = JSON.stringify({ id: app_patient.id, sessionId: app_getSessionId(), module:app_module });
  $.post("cal/getPastAppointments", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    pastAppointments = parsedData.list;
    RenderUtil.render('component/simple_data_table', 
    {items:pastAppointments, 
    title:'Appointments', 
    columns:[{title:'Date/Time', field:'startTime', type:'date-time'}, 
             {title:'Description', field:'clinician.firstName', type:'description'}, 
             {title:'Department', field:'department.name', type:'name'}]},
    function(s) {
      $('#past_appointments_table').html(s);
    });
  });
}


function apptRequest() {
  var isValid = true;
  apptRequest_clearErrors();
  
  if($("#appt-request-clinician").val().length < 1) { 
    util_showError($('#appt-request-clinician'));
    isValid = false;
  }
  if($("#appt-request-visit-reason").val().length < 1) { 
    util_showError($('#appt-request-visit-reason'));
    isValid = false;
  }
  
  if (isValid == false) {
    return;
  }
  
  var jsonData = JSON.stringify({ 
    patientId: app_client.id,
    sessionId: app_getSessionId(), 
    module:app_module,
    clinicianId: $('#appt-request-clinician').val(), 
    altClinicianId: $('#appt-request-would-see').val(), 
    visitReason: $('#appt-request-visit-reason').val(), 
    apptFrom: $('#appt-request-from').val(), 
    apptTo: $('#appt-request-to').val(), 
    preferredTimes: getApptRequestPreferredTimes(),
    content: $('#appt-request-message').val(), 
    subject: "Appointment Request", 
    messageType: MESSAGE_TYPE_APPT_REQUEST
  });
  $.post("msg/processPatientMessage", {data:jsonData}, function(data) {
    apptRequest_clearForm();
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_displayNotification('Appointment request sent.');
  });
}



function getApptRequestPreferredTimes() {
  var preferredTimesArray = 
    [$('#ar-mon-morn'),$('#ar-mon-aft'),$('#ar-tues-morn'),$('#ar-tues-aft'),$('#ar-wed-morn'),  
     $('#ar-wed-aft'),$('#ar-thurs-morn'),$('#ar-thurs-aft'),$('#ar-fri-morn'),$('#ar-fri-aft')]; 
  var preferredTimes = ""; 
  for (var i=0;i<preferredTimesArray.length;i++) {
    if (preferredTimesArray[i].prop('checked') == true) {
      preferredTimes += preferredTimesArray[i].val() + ", ";
    }
  }  
  return preferredTimes.substring(0, preferredTimes.length - 2);
}



function apptRequest_clearForm() {
  $('#appt-request-clinician').val('');
  $("#appt-request-would-see").val('');
  $("#appt-request-visit-reason").val('');
  $('#appt-request-from').val('');
  $('#appt-request-to').val('');
  $('#appt-request-message').val('');
  $('.appt-request-cb').prop('checked', false);
  apptRequest_clearErrors();
}



function apptRequest_clearErrors() {
  $('#appt-request-clinician-validation').css({visibility: "hidden"});
  $('#appt-request-would-see-validation').css({visibility: "hidden"});
  $('#appt-request-visit-reason-validation').css({visibility: "hidden"});
}