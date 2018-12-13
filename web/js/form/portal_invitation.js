function app_pm_specialtyPatientFormValidator() {
  var validatorFn = window[SPECIALTY + "_pm_patientFormValidator"];
  if (validatorFn) {
    return validatorFn();
  }
}

function app_pm_validatePatientFormAndDo(callback, options={}) {
  var patientFormValidator = app_pm_specialtyPatientFormValidator();
  if (!_.isFunction(patientFormValidator)) {
    patientFormValidator = function() {
      var isValid = pm_patientFormValidate();
      app_formError = !isValid;
    }
  } 
  var validatorOptions = {validator: patientFormValidator}
  form_validate(callback, _.extend(options, validatorOptions))
}

function app_pm_getPortalInviteDate() {
  return (app_patient || app_patientForm).portalInviteDate;
}

function app_pm_sendPortalInvitation(options={}) {
  var action = "Send";
  var h4 = "The client will receive a clickable link to the " + app_practiceClientProperties['app.patient_label'] + " Portal";
  if (options.resend) {
    action = "Resend";
    h4 = "";
  }
  var args = {
    modalTitle:action + " Portal Invitation", 
    modalH3:action + " Portal Invitation?",
    modalH4: h4,
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function(){  
      var intakeForms = [];
      $.each($('#intake-forms-screen :checked'), function(_, checkbox) {
        intakeForms.push($(checkbox).val());
      })
      var jsonData = { 
          sessionId: app_getSessionId(), 
          id: app_patientId, 
          module:app_module
      };
      app_post("patient/sendPortalInvitation", jsonData, function(parsedData) {
        $('#modal-confirm').modal('hide'); 
        app_displayNotification('Patient Portal invitation sent');
        app_pm_renderPortalInvitation(parsedData.portalInviteDate);
        app_pm_renderIntakeForms(intakeForms, true); 
      }); 
    });
  });
}


function app_pm_specialtyIntakeForms() {
  var intakeFormFn = window[SPECIALTY + "_pm_intakeForms"];
  if (_.isFunction(intakeFormFn)) {
    return intakeFormFn();
  } else {
    return [];
  }
}

function app_pm_renderIntakeForms(savedIntakeForms, portalInviteSent) {
  var saved = savedIntakeForms;
  var options = {
    intakeForms: app_pm_specialtyIntakeForms(),
    saved: saved
  }
  RenderUtil.render('component/intake_forms', options, function(s) {
    var $s = $(s);
    $('#intake-forms-screen').html($s);
    if (!portalInviteSent) {
      var fieldClass="entity-PatientIntake";
      var formPrefix = "";
      $s.attr('data-instance-id', app_patientIntakeId);
      $s.find('input').each(function(_, input){
       form_field_makeUpdateable($(input), fieldClass, formPrefix); 
      })
    }
  })
}

function app_pm_renderPortalInvitation(inviteDate) {
  var portalInviteDate = app_pm_getPortalInviteDate();
  if (inviteDate) {
    portalInviteDate = inviteDate;;
  }
  var portalInviteSent = false;
  var sentAt; 
  if (portalInviteDate) {
    portalInviteSent = true; 
    sentAt = dateFormat(new Date(portalInviteDate), 'mm/dd/yyyy  h:MM TT');
  }
  var options = {
    portalInviteSent: portalInviteSent,
    sentAt: sentAt 
  }
  RenderUtil.render('component/portal_invitation', options, function(s) {
    var $screen = $('#portal-invitation-screen');
    var dialog =  {
      modalTitle: "Resend Portal Invitation",
      modalH3: ""
    }
    $screen.html(s);
    if (portalInviteSent) {
      $screen.find('#patient-form-resend-portal-invitation').off().on("click", function(){
        app_pm_validatePatientFormAndDo(app_pm_sendPortalInvitation, {dialog: dialog});
      });
    } else {
      $screen.find('#patient-form-send-portal-invitation').off().on("click", function(){
        app_pm_validatePatientFormAndDo(app_pm_sendPortalInvitation,  {dialog: dialog});
      });
    }  
  }) 
  return portalInviteSent; 
}
