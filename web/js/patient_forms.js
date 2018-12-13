

function app_getPatientFormById(patientFormId) {
  for (var i=0;i<app_patientForms.length;i++){
    if (app_patientForms[i].id == patientFormId) {
      return app_patientForms[i];
    }
  }
}



function app_assignPatientFormConfirm() {
  util_clearErrors();  

  if($("#app-practice-form").val().length < 1) { 
    util_showError($('#app-practice-form'));
    return;
  }
  
  var formTitle = $("#app-practice-form option:selected").text();
  
  var args = {
    modalTitle:"Assign " + formTitle + " ?", 
    modalH3:"Assign " + formTitle + " to " + app_patientFullName + "?",
    modalH4:"",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function() {  
      var jsonData = JSON.stringify({ 
        sessionId: app_getSessionId(), 
        id:app_patient.id, 
        practiceFormId: $("#app-practice-form").val(),
        module:app_module 
     });
      $.post("patient/createPatientForm", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        app_displayNotification(formTitle + ' assigned to ' + app_patientFullName);
        app_getPatientForms();
      }); 
    });
  });
}



function app_getPatientForms() {
  var jsonData = JSON.stringify({ 
    id: app_patient.id, 
    intake: false,
    sessionId: app_getSessionId(), 
    module:app_module 
  });
  $.post("patient/getForms", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_patientForms = parsedData.list;
    RenderUtil.render('component/simple_data_table', 
     {items:app_patientForms, 
      title:app_practiceClientProperties['app.patient_label'] + ' Forms', 
      tableName:'app-patient-forms-list', 
      clickable:true, 
      columns:[
        {title:'Type', field:'practiceForm.title', type:'double'},
        {title:'Date', field:'createdDate', type:'date'},
        {title:'Status', field:'status', type:'simple'}
      ]}, function(s) {
      $('#app-patient-forms-list').html(s);
      $('#app-patient-forms-list-title').html(app_practiceClientProperties['app.patient_label'] + " Form History");
      $('.clickable-table-row').click( function(e){ 
        $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        app_patientFormId = $(this).attr('name');
        app_viewPatientForm(app_patientFormId);
      });
    });
  });
}



function app_getPracticeForms() {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module });
  $.post("patient/getPracticeForms", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_practiceForms = parsedData.list;
    RenderUtil.render('component/basic_select_options', {options:app_practiceForms, collection:'app_practiceForms', choose:true}, function(s) { 
      $("#app-practice-form").html(s); 
    });
 });
}



function app_viewPatientForm(patientFormId) {
  app_patientForm = app_getPatientFormById(patientFormId);
  app_pm_nestedFormOffsetClass = undefined;
  var jsonData = { 
    id: app_patientForm.practiceFormInstanceId, 
    className: app_patientForm.practiceForm.className,
    name: app_patientForm.practiceForm.name,
    markAsRead: app_module == PORTAL_MODULE,
    patientId: app_patient.id,
    patientFormId: app_patientForm.id,
    sessionId: app_getSessionId(), 
    module:app_module 
  };
  
  app_post("patient/getPracticeFormInstance", jsonData, function(parsedData) {
    app_form = parsedData.object;

    app_form.practiceForm = app_patientForm.practiceForm;
    app_form.className = app_patientForm.practiceForm.className;
    var formName = app_patientForm.practiceForm.name;
    var formClassName = app_patientForm.practiceForm.className;
    var formTitle = app_patientForm.practiceForm.title;
    app_templateClassName = formClassName.replace("com.wdeanmedical.","").replaceAll(".","-");
    var templateFormName = app_templateFormName = formName.replaceAll("_","-") + '-form';
    app_templateFormPrefix = formName.replaceAll("_","-");
     
    var args = { 
      templateFormName: templateFormName, 
      templateFormScreen: 'patient-form', 
      formTitle: formTitle, 
      formPrefix: app_templateFormPrefix, 
      formClassName: app_templateClassName 
    };
    RenderUtil.render('form/'+SPECIALTY+'/'+formName+'_form', args, function(s) {
      $('#'+templateFormName).remove();
      $("#patient-form-screen").html(s);
      $("#patient-form-screen").show();
      $(".forms-subscreen").show();
      $('#' + app_templateFormPrefix + '-form-wrapper').show();
      $("#patient-form-screen .panel-footer").hide();
      $("#patient-form-print-btn").show();
      
      if (app_patientForm.status != 'submitted' && app_module == PORTAL_MODULE) {
        $("#patient-form-submit-btn").show();
        $("#patient-form-submitted-notification").hide();
        $('#patient-form-submit-btn').off('click').on('click', function(e){ 
          var validator = form_specialtyValidator(formName, app_patientForm.practiceForm.validator);
          if (!_.isFunction(validator)) {
            validator = function() {};
          }
          $('#pi-missing-fields').remove();
          form_validate( function() {
            form_closeFormConfirm(e); 
          }, {validator: validator})  
        });
      }
      else {
        $("#patient-form-submit-btn").hide();
        $("#patient-form-submitted-notification").show();
      }

      form_addForm(app_templateClassName, app_form, '#'+templateFormName);
      
      $('#'+templateFormName).attr('data-instance-id', app_form.id);
      app_form.readonly = app_form.closed == true || app_module != PORTAL_MODULE;
      form_loadPracticeForm(app_patientForm.practiceForm.loader, app_form, function(){
        form_mask_activateInputMask(templateFormName);
        if (app_form.readonly) {
           form_renderClosedForm();
         } else {
           form_initHandlers();
         }
         $('#patient-form-print-btn').off('click').on('click', function(){ form_printForm(); });
      })
    });
  });
}
