

function InstanceParams(options) {
  var getUrl = "patient/getPracticeFormInstances";
  var instanceOptions = options || {}

  var defaultGetInstancesFn = function(parsedData) {
    return parsedData.list;
  }
  
  var defaultColumns = [
        {title:'Date', field:'date', type:'date'},
        {title:'Clinician', field:'clinicianName', type:'clinician'},
        {title:'Status', field:'closed', type:'closed'}
  ]

  var dataTableInitFn = function(instances, formName, formClassName, formPrefix) {
    app_formId = app_formNewInstanceId || instances[0].id;
    form_viewInstance(app_formId, formClassName, formName);
    var $row = $('#'+ formPrefix+'-list-'+app_formId);
    $row.addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
    $('.clickable-table-row').click( function(e) { 
      $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
      app_formId = $(this).attr('name');
      form_viewInstance(app_formId, formClassName, formName);
    }); 
  }

  var defaultDataTableOptions = {
    clickable: true,
    template: "simple_data_table",
    initFn: dataTableInitFn
  }

  var instanceColumns = instanceOptions.columns || defaultColumns;
  var instanceGetInstancesFn = instanceOptions.getInstancesFn || defaultGetInstancesFn;
  var dataTableOptions = _.extend(defaultDataTableOptions, (instanceOptions.dataTableOptions || {}));

  var defaultLoadOptionsFn = function() { return {} }  

  return {
    getUrl: getUrl,
    columns: instanceColumns,
    viewInstanceFn: instanceOptions.viewInstanceFn,
    getInstancesFn: instanceGetInstancesFn,
    dataTableOptions: dataTableOptions,
    loadOptionsFn: instanceOptions.loadOptionsFn || defaultLoadOptionsFn
  }
} 

var form_instanceParams = {}

function form_practiceFormInstanceParams(practiceForm) {
  if (form_instanceParams[SPECIALTY]) {
    return form_instanceParams[SPECIALTY](practiceForm);
  } else {
    return new InstanceParams();
  }  
}

function form_instance_setStatus(status) {
  $statusSelector = form_instanceStatusSelector();
  if ($statusSelector.data('type') != 'simple') {
    $statusSelector.html(status);
  }
}

function form_viewInstance(formId, formClassName, formName) {
  var instanceParams = form_practiceFormInstanceParams(formName);
   var jsonData = _.extend({ 
    id: formId,
    byPatientId: false,
    createIfNull: false, 
    sessionId: app_getSessionId(), 
    module:app_module,
    formClassName: formClassName
  }, instanceParams.loadOptionsFn());

  app_post("app/getForm", jsonData, function(parsedData) {
    app_form = parsedData.object;
    form_instance_setStatus(app_form.closed ? "Closed" : "Open"); 
    if (instanceParams.viewInstanceFn) {
      app_formId = formId; 
      app_formClassName = formClassName;
      return instanceParams.viewInstanceFn();
    }
    form_appFormInit(function(){
      form_loadForm(app_form, app_templateFormPrefix); 
      $('#'+app_templateFormName+'-wrapper').show();   
    });
 });
}



function form_instanceStatusSelector(formId) {
  var _formId = formId || app_form.id;
  var $status = $('#'+app_templateFormPrefix+'-list-'+_formId+' .status');
  if ($status.length) {
    return $status; 
  } else {
    return $('#'+app_templateFormPrefix+'-list-'+_formId+'-2');
  }
}

function form_closeInstance(formId) {
  form_instanceStatusSelector(formId).html('Closed');
}


function form_reopenInstance(formId, formClassName, formName) {
  form_viewInstance(formId, formClassName, formName);
}


function form_loadPracticeFormInstances(formName, formClassName, formTitle, callback) {
  var instanceParams = form_practiceFormInstanceParams(formName);
  var formPrefix = formName.replace('-form').replace('_', '-');
  var jsonData = _.extend({ 
    id: app_patient.id, 
    sessionId: app_getSessionId(), 
    module:app_module,
    formClassName: formClassName  
  }, instanceParams.loadOptionsFn());
  app_post(instanceParams.getUrl, jsonData, function(parsedData) {
    var instances = instanceParams.getInstancesFn(parsedData)
    var dataTableOptions = instanceParams.dataTableOptions;
    var clickable = dataTableOptions.clickable;
    RenderUtil.render('component/'+ dataTableOptions.template, 
     {items:instances, 
      title:formTitle+'s', 
      tableName: formPrefix+'-list', 
      clickable:clickable, 
      columns:instanceParams.columns
    }, function(s) {
      $('#'+ formPrefix+'-list').html(s);
      $('#'+ formPrefix+'-list-title').html(formTitle+' History', dataTableOptions);
      if (instances && instances.length > 0) {
        dataTableOptions.initFn(instances, formName, formClassName, formPrefix);
      }
      if (callback) {
        callback();
      } 
    });
  });
}