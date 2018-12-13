function getPatientVitalSigns(id) {
  var jsonData = JSON.stringify({ id: patient.id, sessionId: app_getSessionId(), module:app_module });
  $.post("app/getPatientVitalSigns", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    var columns = [
      {title:'Date', field:'date', type:'date'}, 
      {title:'Weight', field:'weight', type:'simple'},
      {title:'BMI', field:'bmi', type:'simple'},
      {title:'OFC', field:'ofc', type:'simple'},
      {title:'Temp', field:'temperature', type:'simple'},
      {title:'Pulse', field:'pulse', type:'simple'},
      {title:'Resp', field:'respiration', type:'simple'},
      {title:'Syst', field:'systolic', type:'simple'},
      {title:'Dia', field:'diastolic', type:'simple'},
      {title:'Ox', field:'oximetry', type:'simple'}
    ];
    patientVitalSigns = parsedData.list;
    RenderUtil.render('component/simple_data_table', 
    {items:patientVitalSigns, 
    title:'Vital Signs', 
    clickable:false, 
    columns:columns
    },
    function(s) { 
      $('#patient_health_issue_detail_table').html(s);
   
      var cellIndexMap = {};
      for (i=0;i<columns.length;i++) {
        cellIndexMap[i] = columns[i].field;
      }
      patientVitalSigns.reverse(); 
      app_chartMap = {};
      for (i=0;i<patientVitalSigns.length;i++) {
        var obj = patientVitalSigns[i];
        for (var property in obj) {
          if (obj.hasOwnProperty(property)) {
            if (property in app_chartMap == false){
              var values = [];
              app_chartMap[property] = values; 
            }
            if (property == 'date') {
              obj[property] = dateFormat(obj[property], 'mm/dd/yyyy');
            }
            app_chartMap[property].push(obj[property]);
          }
        }
      }
      var labels = app_chartMap['date'];
      var data = app_chartMap['weight'];
      renderLineChart(labels, data);
    
      $("#patient_health_issue_detail_table th.highlightable:nth-child(2)").addClass('highlighted').siblings().removeClass('highlighted');
      $("#patient_health_issue_detail_table td.highlightable:nth-child(2)").addClass('highlighted').siblings().removeClass('highlighted');
    
      $('#patient_health_issue_detail_table th.highlightable').on('click', function(e){ 
        var cellIndex = this.cellIndex;	
        if (cellIndex > 0) {
          var jqCellIndex = cellIndex+1;
          $(this).addClass('highlighted').siblings().removeClass('highlighted');
          $("#patient_health_issue_detail_table td.highlightable:nth-child("+jqCellIndex+")").addClass('highlighted').siblings().removeClass('highlighted');
          var labels = app_chartMap['date'];
          var data = app_chartMap[cellIndexMap[cellIndex]];
          renderLineChart(labels, data);
        }
      });
    });
  });
}
