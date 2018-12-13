function gp_init_vital_sign_data_table(patientVitalSigns) {
  var columns = gp_pm_columns_vital_sign;
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
            var values = [0,1,2,3,4];
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
    var vitalSignCanvasChart = $('#vital-sign-canvas-chart')[0];
    chart_renderLineChart(labels, data, vitalSignCanvasChart);
    var $vitalSignTable = $('#vital-sign-list-table');
    $vitalSignTable.find("th.highlightable:nth-child(2)").addClass('highlighted').siblings().removeClass('highlighted');
    $vitalSignTable.find("td.highlightable:nth-child(2)").addClass('highlighted').siblings().removeClass('highlighted');
    $vitalSignTable.find('th.highlightable').on('click', function(e){ 
      var cellIndex = this.cellIndex; 
      if (cellIndex > 0) {
        var jqCellIndex = cellIndex+1;
        $(this).addClass('highlighted').siblings().removeClass('highlighted');
        $vitalSignTable.find("td.highlightable:nth-child("+jqCellIndex+")").addClass('highlighted').siblings().removeClass('highlighted');
        var labels = app_chartMap['date'];
        var data = app_chartMap[cellIndexMap[cellIndex]];
        chart_renderLineChart(labels, data, vitalSignCanvasChart);
      }
    });
    var $printWrapper = $('#vital-sign-print').parent();
    $printWrapper.on('click', function(){
      var $template = $($('#vital-sign-screen')[0].outerHTML);
      $template.find('.print-wrapper').remove();
      $template.find('#vital-sign-canvas-chart').remove();
      var $canvasImg = $template.find('#vital-sign-canvas-img');
      $canvasImg.attr('src', vitalSignCanvasChart.toDataURL());
      $canvasImg.show();
      console.log($template)
      printer_printTemplate($template, 'Vital Signs');
    })
}