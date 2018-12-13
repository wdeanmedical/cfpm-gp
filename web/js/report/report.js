

function pm_clearReportsFilter() {
  $('#report-clinician-search-full-name').val('');
  $('#report-patient-search-full-name').val('');
  $('#report-activity').val('');
  $('#report-date-from').val('');
  $('#report-date-to').val('');
  pm_runReport();
}



function pm_getReportsList() {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module });
  $.post("reports/getReportList", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_reportList = parsedData.list;
  });
}



function pm_getActivityLogs() {
  var jsonData = JSON.stringify({ 
    module:app_module,
    dateFrom: $.trim($("#report-date-from").val()),
    dateTo: $.trim($("#report-date-to").val()),
    clinicianName: $.trim($("#report-clinician-search-full-name").val()),
    activityName: $.trim($("#report-activity").val()),
    patientName: $.trim($("#report-patient-search-full-name").val()),
    sessionId: app_getSessionId() 
  });
  $.post("reports/getActivityLogs", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_activityLogs = parsedData.activityLogs;
    RenderUtil.render('component/simple_data_table', {
      items: app_activityLogs, 
      title: 'Activity Logs', 
      tableName: 'report-content', 
      clickable: false,
      columns:[
        {title:'Client Name', field:'clientName', type:'simple'},
        {title:'Client Type', field:'clientType', type:'simple'},
        {title:'Time Performed', field:'timePerformed', type:'simple'},
        {title:'Field Name', field:'fieldName', type:'simple'},
        {title:'Activity', field:'activity', type:'simple'},
        {title:'Module', field:'module', type:'simple'}
      ]}, function(s) {
        $('#report-content').html(s);
        $('#report-csv-btn').show();
        $('#report-print-btn').show();
    });
  });
}



function pm_getPatientActivity() {
  var jsonData = JSON.stringify({ 
    module:app_module,
    dateFrom: $.trim($("#report-date-from").val()),
    dateTo: $.trim($("#report-date-to").val()),
    clinicianName: $.trim($("#report-clinician-search-full-name").val()),
    activityName: $.trim($("#report-activity").val()),
    patientName: $.trim($("#report-patient-search-full-name").val()),
    sessionId: app_getSessionId() 
  });
  $.post("reports/getPatientActivity", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      app_activityLogs = parsedData.patientsActivity;

      if (!util_checkSessionResponse(parsedData)) return false;
      RenderUtil.render('component/reports/patient_activity', {
        patientsActivity: app_activityLogs, 
        title: 'Patients',
        tableName: 'report-content', 
        clickable: false
        }, function(s) {
        $('#report-content').html(s);
        $('#report-csv-btn').show();
        $('#report-print-btn').show();
      });
  });
}

function pm_getClinicianActivity() {
  var jsonData = JSON.stringify({ 
    module:app_module,
    dateFrom: $.trim($("#report-date-from").val()),
    dateTo: $.trim($("#report-date-to").val()),
    clinicianName: $.trim($("#report-clinician-search-full-name").val()),
    activityName: $.trim($("#report-activity").val()),
    patientName: $.trim($("#report-patient-search-full-name").val()),
    sessionId: app_getSessionId() 
  });
  $.post("reports/getClinicianActivity", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      app_activityLogs = parsedData.cliniciansActivity;
      if (!util_checkSessionResponse(parsedData)) return false;
      RenderUtil.render('component/reports/clinician_activity', {
        cliniciansActivity: app_activityLogs, 
        title: 'Clinicians', 
        tableName: 'report-content', 
        clickable: false
        }, function(s) {
        $('#report-content').html(s);
        $('#report-csv-btn').show();
        $('#report-print-btn').show();
      });
  });
}



function pm_getWaitList() {
    var jsonData = JSON.stringify({ 
    module:app_module,
    clinicianName: $.trim($("#report-clinician-search-full-name").val()),
    patientName: $.trim($("#report-patient-search-full-name").val()),
    sessionId: app_getSessionId() 
  });
  $.post("reports/getWaitListPatients", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_waitListPatients = parsedData.list;
    RenderUtil.render('component/simple_data_table', {
      items: app_waitListPatients, 
      title: 'Wait List', 
      tableName: 'report-content', 
      clickable: false,
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
        $('#report-content').html(s);
        $('#report-csv-btn').show();
        $('#report-print-btn').show();
    });
  });
}



function pm_initReportSearchTypeAheads() {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module });
  $.post("reports/getReportSearchTypeAheads", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_activityLogClinicianSearchTypeAheads = parsedData.reportTypeAheads.userFullNames;
    app_activityLogPatientSearchTypeAheads = parsedData.reportTypeAheads.patientFullNames;
    app_clinicianActivityTypeAheads = parsedData.reportTypeAheads.clinicianActivities;
    $('#report-activity').typeahead(
    { hint: true, highlight: true, minLength: 3 },
      { name: 'clinicianActivities', displayKey: 'value', source: util_substringMatcher(app_clinicianActivityTypeAheads) }); 
    $('#report-clinician-search-full-name').typeahead(
      { hint: true, highlight: true, minLength: 3 },
      { name: 'clinicianFullNames', displayKey: 'value', source: util_substringMatcher(app_activityLogClinicianSearchTypeAheads) }); 
    $('#report-patient-search-full-name').typeahead(
      { hint: true, highlight: true, minLength: 3 },
      { name: 'patientFullNames', displayKey: 'value', source: util_substringMatcher(app_activityLogPatientSearchTypeAheads) });
  });      
}



function pm_loadReportButtons() {
  var array = [];
  for(var i=0;i< app_reportList.length;i++) {
    var report = app_reportList[i];
    var column = i % 4 + 1;
    var args = {
        color: report.color,
        icon: report.icon,
        id: report.id,
        title: report.title,
        column: column
    };
    array.push(args);
  }
  
  for(var i=0;i< array.length;i++) {
    var args2 = array[i];
    RenderUtil.render('component/chart_button', args2, function(s) { 
      $('#chart-col-'+ args2.column).append(s); 
    });
  }
}



function pm_renderReportChartHeader() {
  RenderUtil.render('component/report_chart_header', {}, function(s) {
    $('#report-chart-header').html(s);
  });
}



function pm_printReport() {
  $("#report-content").printThis({
    debug: false,        
    importCSS: true,    
    importStyle: true, 
    printContainer: true, 
    loadCSS: DEFAULT_APP_PATH + "css/all.css", 
    pageTitle: app_reportTitle,       
    removeInline: false,  
    printDelay: 333,  
    header: null,    
    formValues: true
  });
}



function pm_runReport() {
  if (app_reportName == 'activity_log') {
    pm_getActivityLogs();
  }
  else if (app_reportName == 'patient_activity') {
    pm_getPatientActivity();
  } 
  else if (app_reportName == 'clinician_activity') {
    pm_getClinicianActivity();
  } 
  else if (app_reportName == 'wait_list') {
    pm_getWaitList();
  } 
}



function pm_viewReport(reportName, reportTitle) {
  app_reportName = reportName;
  app_reportTitle = reportTitle;
  $("#reports-view").hide();
  $("#report-view").show();
  $("#report-view-header").html(app_reportTitle);
  
  if (app_reportName == 'activity_log') {
    $("#report-activity-form-group").show();
    $("#report-date-from-form-group").show();
    $("#report-date-to-form-group").show();
  }
  else if (app_reportName == 'patient_activity') {
    $("#report-activity-form-group").show();
    $("#report-date-from-form-group").show();
    $("#report-date-to-form-group").show();
  }
  else if (app_reportName == 'clinician_activity') {
    $("#report-activity-form-group").show();
    $("#report-date-from-form-group").show();
    $("#report-date-to-form-group").show();
  }
  else if (app_reportName == 'wait_list') {
    $("#report-activity-form-group").css({visibility:"hidden"});
    $("#report-date-from-form-group").hide();
    $("#report-date-to-form-group").hide();
  }
}



function pm_viewReports() {
 RenderUtil.render('screen/report_screen', {}, function(s) {
   $("#reports-screen").html(s);
   app_viewStack('reports-screen', DO_SCROLL);
   pm_renderReportChartHeader();
   pm_initReportSearchTypeAheads();
   
   $('#report-close-button').click(function(){ pm_viewReports(); });
   $('#report-clear-btn').click(function(){ pm_clearReportsFilter(); });
   $('#report-print-btn').click(function() { pm_printReport(); });
   $('#report-run-btn').click(function(){ pm_runReport(); });
   
   $('#report-activity-log-btn').click(function(){ pm_viewReport('activity_log', 'Activity Log'); });
   $('#report-patient-activity-btn').click(function(){ pm_viewReport('patient_activity', 'Patient Activity'); });
   $('#report-clinician-activity-btn').click(function(){ pm_viewReport('clinician_activity', 'Clinician Activity'); });
   $('#report-wait-list-btn').click(function(){ pm_viewReport('wait_list', 'Wait List'); });
   
   $('#report-date-from').mask("99/99/9999");
   $('#report-date-to').mask("99/99/9999");
   $('#report-csv-btn').click(function() { util_exportTableToCSV.apply(this, [$('#report-content>table'), 'report.csv']); });
  });  
} 


