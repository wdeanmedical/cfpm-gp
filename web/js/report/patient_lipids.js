function getPatientLipids(id) {
  var jsonData = JSON.stringify({ id: patient.id, sessionId: app_getSessionId(), module:app_module });
  $.post("app/getPatientLipids", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    var columns = [
      {title:'Date', field:'date', type:'date'}, 
      {title:'CHOL', field:'chol', type:'simple'},
      {title:'HDL', field:'hdl', type:'simple'},
      {title:'LDL', field:'ldl', type:'simple'},
      {title:'TRIG', field:'trig', type:'simple'},
      {title:'ALT', field:'alt', type:'simple'}
    ];
    patientLipids = parsedData.list;
    RenderUtil.render('component/simple_data_table', 
    {items:patientLipids, 
    title:'Lipids', 
    clickable:false, 
    columns:columns
   },
   function(s) {
     $('#patient_health_issue_detail_table').html(s);
  
     var cellIndexMap = {};
      for (i=0;i<columns.length;i++) {
        cellIndexMap[i] = columns[i].field;
      }
  
      patientLipids.reverse(); 
      app_chartMap = {};
      for (i=0;i<patientLipids.length;i++) {
        var obj = patientLipids[i];
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
      var data = app_chartMap['chol'];
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