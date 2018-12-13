function patientIntake_bodyAnnotationCloser(closer) {
  var saveBodyAnnotation = false;
  if (typeof bodyCanvas__finalized != 'undefined') {
    if (bodyCanvas__finalized == false && bodyCanvas__readonly != true) {
      saveBodyAnnotation = true;
    }
  }    
  if (saveBodyAnnotation && _.isObject(bodyCanvas__canvas)) { 
    return bodyCanvas__saveAndShowFinalized(bodyCanvas__canvas, function(){
      if (closer) {
        return closer();
      }  
    })
  } else {
   return defer(function() {
      if (closer) {
        return closer();
      }
    })    
  }  
}

function app_patientIntakeValidate() {
  form_validate_clearErrors();
  _.each(app_intakeFormValidators, function(validator) {
    validator.validate();
  })
  if (app_missingFields.length > 0) {
    var missingFieldsTitle = "<h4>" + "The following fields are required to complete the intake:" + "</h4>";
    var missingFieldsText = "<ul class='text-danger'>";
    for (var i=0;i<app_missingFields.length;i++) {
      missingFieldsText += "<li>" + app_missingFields[i] + "</li>";
    }
    missingFieldsText += "</ul>";   
    $('#pi-missing-fields').html(missingFieldsText);
    $('.intake-forms-step-'+app_finalScreenButton+'-btn').addClass('disabled');
    $('#pi-no-errors').hide();
    $('#pi-with-errors').show();
  } else {
    $('.intake-forms-step-'+app_finalScreenButton+'-btn').removeClass('disabled');
    $('.intake-forms-step-'+app_finalScreenButton+'-btn').off('click').on('click', function(){ 
      patientIntake_bodyAnnotationCloser(app_closePatientIntake);
    });    
    $('#pi-no-errors').show();
    $('#pi-with-errors').hide();
  }
}

function app_closePatientIntake() {
  var jsonData = JSON.stringify({ id: app_patient.id, sessionId: app_getSessionId(), module:app_module });
  $.post("patient/closePatientIntake", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_patientIntake = parsedData;
    app_patient.intakeClosed = true;
    
    var modalTitle = app_practiceClientProperties['app.patient_label'] + ' Intake Forms Submitted';
    var modalH3 = app_practiceClientProperties['app.patient_label'] + ' Intake Forms Successfully Submitted';
    var modalH4 = 'You may now either continue on to view your ' + app_practiceClientProperties['app.patient_label'] + ' Portal or log out.';
    var cancelButton = 'Logout';
    var okButton = 'Continue';
    
    if (app_module == PM_MODULE) {
      modalTitle = app_practiceClientProperties['app.patient_label'] + ' Intake Closed';
      modalH3 = app_practiceClientProperties['app.patient_label'] + ' Intake Successfully Closed'; 
      modalH4 = '';
      cancelButton = null;
      okButton: 'Ok'
    }
    
    var args = {
      modalTitle: modalTitle,
      modalH3: modalH3,
      modalH4: modalH4,
      cancelButton: cancelButton,
      okButton: okButton
    };
    RenderUtil.render('dialog/confirm', args, function(s) { 
      $('#modal-confirm').remove(); 
      $('#modals-placement').html(s);
      $('#modal-confirm').modal('show'); 
      $('#app-modal-confirmation-btn').click(function(){  
        $('#modal-confirm').modal('hide');
        if (app_module == PM_MODULE) {
           pm_viewPatientChartScreen();
        } else{
          portal_dashboardScreen();
        }
      });
      $('#app-modal-cancel-btn').click(function(){  
        app_logout();
      });
    });
    
  });
}



function app_handlePatientIntakeWizardNavigation(screenNumber) {
  app_intakeFormsViewStack($('#forms-subscreen-'+screenNumber), true);
  $('.stepwizard').hide();
  $('.stepwizard-buttons').hide();
  $('.stepwizard-'+screenNumber).show();
  $('#stepwizard-buttons-'+screenNumber).show();
  $('.stepwizard-step a').removeClass('active');
  $('.stepwizard-step a[name='+screenNumber+']').addClass('active');
  
  if (screenNumber == app_finalScreenNumber) {
    app_evaluatePatientIntakeInput();
  }
}


  
function app_loadIntakeForm(intakeForm, formIndex) {
  var name = intakeForm.name;
  var loader = intakeForm.loader;
  intakeForm.readonly = app_patient.intakeClosed;
  app_form = intakeForm;
  util_debug('in loadIntakeform(' + name + ')');
  
  // Top Nav Circles
  RenderUtil.render('component/stepwizard', {i:formIndex, intakeFormIndices:app_intakeFormIndices}, function(s) { 
    var $s = $(s);    
    if (intakeForm.validator) {
      app_intakeFormValidators.push(new IntakeFormValidator(intakeForm.validator, $s));
    }
    $('#intake-forms').append(s); 
    $('.stepwizard').hide();
    if (app_patient.intakeClosed == true) { $('.stepwizard-1').show();}
    $('.stepwizard-step a[name=1]').addClass('active');
    var formName = intakeForm.name;
    var formClassName = app_templateClassName = intakeForm.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    var formPrefix = app_templateFormPrefix = formName.replaceAll("_","-");
    var templateFormName = app_templateFormName = formName.replaceAll("_","-") + '-form';
  
    // The Form Body
    RenderUtil.render(intakeForm.template, {i:formIndex, id: intakeForm.id, templateFormName: templateFormName, formClassName: formClassName, formPrefix: formPrefix}, function(s) {
      $('#intake-forms').append(s);
      var formSelector = '#forms-subscreen-'+formIndex;
      if (app_patient.intakeClosed == true) { 
        $(formSelector+' .input-field').attr("disabled", "disabled");
        $(formSelector+' .input-select').attr("disabled", "disabled");
        $(formSelector+' .selectpicker').attr("disabled", "disabled");
        $(formSelector+' .input-checkbox').attr("disabled", "disabled");
        $(formSelector+' .input-radio').attr("disabled", "disabled");
        $(formSelector+' .desc-text').attr("readonly", "readonly");
        $(formSelector+' .radio-btn-group a').attr("disabled", "disabled");
        $(formSelector+' .new-list-item-btn').hide();
      }
      else {
        $(formSelector+' .input-field').blur( function() { form_validateAndUpdateField(this) });
        $(formSelector+' .input-checkbox').click( function() { form_validateAndUpdateField(this) });
        $(formSelector+' .input-checkbox-group').click(function() { form_validateAndUpdateField($(this)) });
        $(formSelector+' .input-radio').click(function() { form_validateAnUpdateFieldField($(this)) });
        $(formSelector+' .input-select').change(function() { form_validateAndUpdateField($(this)) });
        $(formSelector+' .new-list-item-btn').show();
        $('.input-date').mask("99/99/9999");
      }  
      form_addForm(formClassName, intakeForm.data, '#' + templateFormName);
      form_loadPracticeForm(loader, intakeForm, function(){
        form_mask_activateInputMask(templateFormName);
        if (app_patient.intakeClosed == true) { $("#forms-subscreen-1").show(); }
        // Bottom Nav Circles
        RenderUtil.render('component/stepwizard', {i:formIndex, intakeFormIndices:app_intakeFormIndices}, function(s) { 
          $('#intake-forms').append(s); 
          $('.stepwizard').hide();
          if (app_patient.intakeClosed == true) { $('.stepwizard-1').show(); }
          $('.stepwizard-step a[name=1]').addClass('active');
          
          // Bottom Nav Buttons
          RenderUtil.render('component/stepwizard_buttons', {i:formIndex}, function(s) { 
            $('#intake-forms').append(s); 
            if (app_patient.intakeClosed == true) { 
              $('#stepwizard-buttons-1').show(); 
              $('.intake-forms-step-'+app_finalScreenNumber+'-btn').hide();
              $('.intake-forms-step-0-btn').hide();
            } 
            $('.intake-forms-step-btn, .stepwizard-btn-circle').off('click').on('click', function() { 
              app_handlePatientIntakeWizardNavigation($(this).attr('name')) 
            });
          });
        });
      })  
    });
  });
}

function app_loadIntakeForms() {
  app_finalScreenNumber = app_patientIntake.practiceForms.length+1;
  app_finalScreenButton = app_finalScreenNumber+1;
  app_intakeFormValidators = [];

  $("#intake-forms").html('');
  
  app_intakeFormIndices = [];
  for (var i=0;i<app_patientIntake.practiceForms.length;i++) {
    app_intakeFormIndices.push({'i': i+1});
  }
  
  for (var i=0;i<app_patientIntake.practiceForms.length;i++) {
    util_debug('inspecting: ' + app_patientIntake.practiceForms[i].name)
    app_loadIntakeForm(app_patientIntake.practiceForms[i], i+1);
  }
}



function app_renderPatientIntakeScreen() {
  var jsonData = { id: app_patient.id, module:app_module, sessionId: app_getSessionId() };
  app_post("patient/getPatientIntake", jsonData, function(parsedData) {
    app_patientIntake = parsedData.object;
    
    if (app_patientIntake.practiceForms.length == 0) {
      RenderUtil.render('screen/intake_records_placeholder', {}, function(s) {
        if (app_module == PM_MODULE) {
          $("#patient-chart-item-screen").html(s);
          app_showScreen('Patient Intake', app_patientChartItemCache);
          $("#patient-chart-item-screen").show();
          app_chartItemStack($('#intake-forms-screen'), true);
        }
        else if (app_module == PORTAL_MODULE) {
          $("#intake-forms-screen").html(s);
          app_viewStack('intake-forms-screen', DO_SCROLL);
        }
        $('#pi-doctor-profile-image').attr('src', 'assets/images/practice/'+PRACTICE+'/'+app_practiceClientProperties['app.image.practitioner']);
        $('.practice-logo').attr('src', 'assets/images/practice/'+PRACTICE+'/'+app_practiceClientProperties['app.image.practice_logo']); 
        $('.business-address').html(app_practiceClientProperties['app.business_address']); 
      });
      return;
    } else {
      app_notificationText = app_clientFullName + ' ready for activation.';
      app_displayNotification(app_notificationText);
    }
    
    app_finalScreenNumber = app_patientIntake.forms.split(',').length + 1;
    app_finalScreenButton = app_finalScreenNumber + 1;
    
    RenderUtil.render('screen/intake_records_screen', {lastScreenNum:app_finalScreenNumber}, function(s) {
      
      if (app_module == PM_MODULE) {
        $("#patient-chart-item-screen").html(s);
        app_showScreen('Patient Intake', app_patientChartItemCache);
        $("#patient-chart-item-screen").show();
        app_chartItemStack($('#intake-forms-screen'), true);
      }
      else if (app_module == PORTAL_MODULE) {
        $("#intake-forms-screen").html(s);
        app_viewStack('intake-forms-screen', DO_SCROLL);
      }
      if (app_patient.intakeClosed == true) {
        app_intakeFormsViewStack($('#forms-subscreen-1'), true);
        $("#forms-subscreen-1").show();
      }
      else {
        app_intakeFormsViewStack($('#forms-subscreen-0'), true);
        $("#forms-subscreen-0").show();
        $('#top-nav-panel').hide();
      }
      
      $('#pi-doctor-profile-image').attr('src', 'assets/images/practice/'+PRACTICE+'/'+app_practiceClientProperties['app.image.practitioner']);
      $('.practice-logo').attr('src', 'assets/images/practice/'+PRACTICE+'/'+app_practiceClientProperties['app.image.practice_logo']); 
      $('.business-address').html(app_practiceClientProperties['app.business_address']); 
       if (SPECIALTY == POT_SPECIALTY) {
        app_post("patient/potGetPatientForm", jsonData, function(parsedData) {
          app_patientForm = parsedData.patientForm;
          app_loadIntakeForms();
        })  
      } else {
        app_loadIntakeForms();
      }  
    });
  });
}
