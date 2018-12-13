
function pm_clearGuardianForm() {
  $('#guardian-first-name').val('');
  $('#guardian-middle-name').val('');
  $('#guardian-last-name').val('');
  $('#guardian-rel').val('');
  $('#guardian-address1').val('');
  $('#guardian-city').val('');
  $('#guardian-postal-code').val('');
  $('#guardian-email').val('');
  $('#guardian-phone').val('');
  $('#guardian-state').val('');
}

function guardian___clientAddressFields($container) {
  var fieldVals = {};
  var $clientAddress = $container.closest('form').find('#client-address');
  $.each($clientAddress.find('.address-field'), function(_, elem){
    var fieldVal = $(elem).val();
    var fieldType = $(elem).data('field-type');
    fieldVals[fieldType] = fieldVal;
  })
  return fieldVals;
}

function guardian___updatables($container, props={}) {
  var updatables =[]
  var fieldVals = guardian___clientAddressFields($container); 
  $.each($container.find('.address-field'), function(_, elem){
    var property = $(elem).data('property');
    var fieldType = $(elem).data('fieldType');
    var propertyValue = props.clientVals == true ? fieldVals[fieldType] : $(elem).val();
    updatables.push(new Updateable(property, propertyValue, fieldType));
  })
  var $checkbox = $container.find('.same-as-client-checkbox');
  var isChecked = $checkbox.is(':checked');
  var checkedProperty = $checkbox.data('property');
  var checkedFieldType = $checkbox.data('fieldType');
  updatables.push(new Updateable(checkedProperty, isChecked, _.last(checkedProperty.split('.'))) );
  return updatables;
}

function guardian___props(updatables) {
  var guardianProps = {}
  _.each(updatables, function(updatable){
      guardianProps[updatable.updatePropertyFieldType] = updatable.updatePropertyValue;
  })
  return guardianProps;  
}

function guardian___updateAddressWithClients(id, $container, callback) {
  var updatables = guardian___updatables($container, {clientVals: true});
  jsonData = JSON.stringify({sessionId: app_getSessionId(), id: id,  module:app_module, list: updatables});
  app_updateField(jsonData, function(parsedData){
     callback(parsedData.list);
  }, null, {multiple: true})
}

function guardian___renderAddress(updatables, $container, options, afterRender) {
  var guardianProps = guardian___props(updatables);
  RenderUtil.render('component/guardian/address', _.extend(guardianProps, options), function(s) { 
    $container.html(s);
    afterRender();
  })
}

function guardian___disableAddressFields(guardian, $container, instanceName, afterRender) {
  guardian___updateAddressWithClients(guardian.id, $container, function(updatables) {
    guardian___renderAddress(updatables, $container, {id: guardian.id, readonly:true, instanceName: instanceName}, afterRender);
  })
}

function guardian___enableAddressFields(guardian, $container, instanceName, afterRender) {
  var updatables = guardian___updatables($container);
  var sameAsClientUpdatable = _.find(updatables, function(updatable) { return updatable.updatePropertyFieldType.match(/sameAsClient/i)});
  jsonData = JSON.stringify(
    _.extend({sessionId: app_getSessionId(), id: guardian.id,  module:app_module}, sameAsClientUpdatable)
  );
  app_updateField(jsonData, function(parsedData){
    guardian___renderAddress(updatables, $container, {id: guardian.id, readonly:false, instanceName: instanceName}, afterRender);
  })
}

function app_guardianHandleSameAsClient() {
  $('#patient-form-guardians').off('change.same-as-client-checkbox').on('change', '.same-as-client-checkbox', function(event){
    var isChecked = $(event.currentTarget).is(':checked');
    var guardianId = $(event.currentTarget).data('id');
    var guardian = _.find(app_patientForm.guardians, function(instance) { return instance.id == guardianId});
    var $container = $(event.currentTarget).closest('.row.guardian-same-as-client')
    var instanceName = $(event.currentTarget).data('instanceName');
    function afterRender() {
      RenderUtil.render('component/basic_select_options', {options:app_usStates, collection:'app_usStates', choose:true}, function(s) { 
        var $usState = $container.find('.input-us-state')
        var val = $usState.data('value');
        $usState.html(s).val(val); 
      });
      $container.find('.list-item-field').off().on('blur', function(e) { form_updateListItem($(this)); });
      $container.find('.postal-code-field').mask("99999");
    }
    if (isChecked) {
      guardian___disableAddressFields(guardian, $container, instanceName, afterRender);
    } else {
      guardian___enableAddressFields(guardian, $container, instanceName, afterRender);
    }
  }) 
  $('#client-address').off().on('change .address-field', function(){
    $.each($('.same-as-client-checkbox:checked'), function(_, checkbox) {
      $(checkbox).trigger('change')
    })
  })
}

function app_clearNewGuardianForm() {
  $('#guardian-first-name').val('');
  $('#guardian-middle-name').val('');
  $('#guardian-last-name').val('');
  $('#guardian-rel').val('');
  $('#guardian-address1').val('');
  $('#guardian-city').val('');
  $('#guardian-postal-code').val('');
  $('#guardian-email').val('');
  $('#guardian-phone').val('');
  $('#guardian-state').val('');
}

function guardian_afterEmailUpdate($emailField, success, parsedData) {
  var $replaceWithExistingBtn = $emailField.closest('.row').find('.guardian-replace-with-existing-btn');
  util_clearItemError($replaceWithExistingBtn);
  if (!success) {
    if (parsedData.errorType == "entity_exists") {
      $replaceWithExistingBtn.attr('data-existing-email', $emailField.val());
      $replaceWithExistingBtn.show();
    } else {
      $replaceWithExistingBtn.hide();
    }
  }
}

//$elem is replace existing button
function guardian_confirmReplaceWithExisting(guardianId, existingEmail, $elem) {
    var args = {
    modalTitle:"Replace With Existing Guardian", 
    modalH3:"Replace With Existing?",
    modalH4:"This will delete this guardian and load the existing",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').off('click').on('click', function() {  
      var jsonData = JSON.stringify({ 
        sessionId: app_getSessionId(),
        patientId: app_patient.id,
        email: existingEmail,
        patientFormId: app_patientForm.id,
        guardianId: guardianId
      });
      app_post("patient/replaceGuardianWithExisting", jsonData, function(parsedData){
        if (parsedData.result == false) {
          util_showError($elem, parsedData.errorMsg);
        } else {
          var $guardianEl = $elem.closest('.patient-form-guardian'); 
          var replacement = parsedData.guardian
          pot_pm_updatePatientFormGuardian($guardianEl, replacement);
        }
      })
    });
 });
}
function pm_guardianHandleReplaceWithExisting() {
  $('#patient-form-guardians').on('click', '.guardian-replace-with-existing-btn', function() {
    var guardianId = $(this).data('id');
    var existingEmail = $(this).data('existingEmail');
    guardian_confirmReplaceWithExisting(guardianId, existingEmail, $(this));
  })
}

function pm_updatePatientGuardianSensitiveInfo($guardian) {
  if ($guardian.length == 0) {
    return;
  }  
  var guardianId = $guardian.attr('id').replace('guardian-', '');
  var guardian = {
    email: $guardian.find('.email-field').val(),
    primaryPhone: $guardian.find('.primary-phone-field').val(),
    otherPhone: $guardian.find('#guardian-secondary-phone-'+guardianId)
  }
  pm_renderPatientGuardianSensitiveInfo(guardian);
}

function pm_guardianHandleUseThisEmail() {
  $('#patient-form-guardians').on('change', '.use-this-email-checkbox', function(event){
    var $elem = $(event.currentTarget);
    var $parent = $elem.closest('#patient-form-guardians');
    $parent.find('.use-this-email-checkbox').not(this).prop('checked', false);  
    var checked = $elem.is(':checked');
    var $form = $elem.closest('form');
    var formId = $form.data('instanceId');
    var formKey = $form.data('formKey');
    var guardianId = $elem.data('id');
    var form = app_forms[formKey];
    var formClassName=form.className
    if (SPECIALTY==POT_SPECIALTY){
       formClassName="pot-entity-form-POTPatientForm"
       formId=app_patient.patientFormId  
    }
    var property = formClassName + ".emailGuardianId";
   
    var propertyValue = checked ? guardianId : undefined;
    var updatable = new Updateable(property, propertyValue);
    jsonData = JSON.stringify(
      _.extend({
        sessionId: app_getSessionId(), 
        id: formId,  
        module:app_module
      }, updatable)
    );
    app_updateField(jsonData, function(){
      pm_setGuardianDefault();
    });
  })
}

function pm_setGuardianDefault() {
  var $defaultGuardian = $('#patient-form-guardians .use-this-email-checkbox:checked');
  if ($defaultGuardian.length == 0) {
    $('#patient-form-guardians .use-this-email-checkbox').first()
    .prop('checked', true).trigger('change');
    $defaultGuardian = $('#patient-form-guardians .use-this-email-checkbox:checked');
  }
  pm_updatePatientGuardianSensitiveInfo($defaultGuardian.closest('.patient-form-guardian'));
}

function pm_confirmGuardianRemoval() {
    var args = {
    modalTitle:"Remove Guardian", 
    modalH3:"Remove Guardian?",
    modalH4:"This will remove the guardian from the client ",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').off('click').on('click', function() {  
      var jsonData = JSON.stringify({ 
        sessionId: app_getSessionId(),
        id: app_patientId,
        updateProperty:'entity-Patient.guardian',
        updatePropertyValue: null,
        updatePropertyFieldType:'object'
      });

      app_updateField(jsonData, function(parsedData) {
        app_displayNotification('Guardian Removed');
        app_patient.guardian = null;
        $('#modal-confirm').modal('hide'); 
        $('#patient-form-guardian-section').hide();
        $('#patient-guardian').val('');
        $('#patient-guardian-form-group').hide();
        $("input[name=hasGuardian][value=true]").attr("checked", false);
        $('#patient-email-form-group').show();
      });
    });
    
    $('#app-modal-cancel-btn').off('click').on('click', function() {  
      $('#patient-guardian-status').prop('checked', true);
      $('#patient-guardian').val(app_patient && app_patient.guardian ? app_patient.guardian.id : '');
    });
 });
}

function pm_findGuardianById(id) {
  if (id) {
    for (var i=0;i<app_guardians.length;i++) {
      if (app_guardians[i].id == id) {
        return app_guardians[i];
      }
    }
  }
  return null;
}

function app_patientFormUpdateGuardianSelection(id) {
  app_guardian = pm_findGuardianById(id);
  if (app_guardian != null) {
    app_loadGuardianSection();
  } else {
    $('#patient-form-guardian-section').hide();
  }  
  if (app_patient) {
    var jsonData = { 
      sessionId: app_getSessionId(),
      patientId: app_patient.id
    };
    if(id.length){
      jsonData.id=id;
    }
    if(app_patient.guardian != app_guardian){
      app_post("patient/updatePatientGuardian", jsonData, function(parsedData) {
        app_patient.guardian = app_guardian;
      });
    }
  }
}

function guardian__loadGuardian(guardianId){
  if (guardianId) {
    $('#patient-guardian').val(guardianId);
    app_guardian = pm_findGuardianById(guardianId);
    app_loadGuardianSection();
  } else {
     $('#patient-guardian').val('');
  }   
}

function app_getGuardians(guardianId, callback) {
  jsonData = JSON.stringify({sessionId: app_getSessionId(), patientId:app_patient.id, module:app_module});
    $.post("patient/getGuardians", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      if (!util_checkSessionResponse(parsedData)) return false;
      app_guardians = parsedData.list;
      RenderUtil.render('component/person_select_options', {options:app_guardians}, function(s) {
        $("#patient-guardian").html(s);        
        guardian__loadGuardian(guardianId);          
        $('#patient-guardian').off('change').on('change', function() { 
          var guardianSelection = $(this).val();
          app_patientFormUpdateGuardianSelection(guardianSelection);
        });
        if (callback){
          callback()
        }
    });
  });
}

function app_handleHasGuardianClick() {
  $('#patient-guardian-status').on('change', function(){
    $('#patient-guardian').val('');
    if ($(this).prop('checked') == false) {
      $('#patient-guardian').val('').trigger('change');
      $('#patient-form-guardian-section').hide();
      $('#patient-guardian-form-group').hide();
      $('#patient-email-form-group').show();
      $('#patient-new-guardian-btn').hide();
    } else {
      $('#patient-new-guardian-btn').show();
      $('#patient-guardian-form-group').show();
      $('#patient-email-form-group').css({display: "none"});
      $('#patient-email').val('');
    }
  })
}

function app_loadGuardianSection(_guardian) {
  var guardian = app_guardian;
  if(_guardian){
    guardian = _guardian;
  }
  app_clearNewGuardianForm();
  $('#patient-form-guardian-section').show();
  $('#guardian-first-name').val(guardian.firstName);
  $('#guardian-middle-name').val(guardian.middleName);
  $('#guardian-last-name').val(guardian.lastName);
  $('#guardian-rel').val(guardian.relation);
  $('#guardian-address1').val(guardian.streetAddress1);
  $('#guardian-city').val(guardian.city);
  $('#guardian-postal-code').val(guardian.postalCode);
  $('#guardian-email').val(guardian.email);
  $('#guardian-phone').mask("(999) 999-9999");
  $('#guardian-phone').val(guardian.primaryPhone);
  $('#guardian-state').val(guardian.usState ? guardian.usState.id : ''); 
  $('#patient-form-guardian-section').attr('data-instance-id', guardian.id);
}



function app_newGuardianForm() {
  RenderUtil.render('form/'+SPECIALTY+'/guardian_form', {}, function(s) {
    $('#modal-new-guardian').remove();
    $('#modals-placement').append(s);
    $('#modal-new-guardian').modal('show');
    app_clearNewGuardianForm();
     var $el = $('#modal-new-guardian');
    RenderUtil.render('component/basic_select_options', {options:app_usStates, collection:'app_usStates', choose:true}, function(s) {
      $el.find("#guardian-state").html(s); 
    });
    $el.find('#guardian-postal-code').mask("99999");
    $el.find('#guardian-phone').mask("(999) 999-9999");

    $("#modal-new-guardian").on('hidden.bs.modal', function () { 
      $(this).data('bs.modal', null); 
      $('#modal-new-guardian').empty();
    });
    $('#app-create-guardian-submit').off('click').on('click', function(){ app_saveNewGuardian() });
  });
}

function app_saveNewGuardian() {
  var isValid = true;
  util_clearErrors();  
  var $el = $('#modal-new-guardian');
  function el(selector){
    return $el.find(selector)
  }
  if(el("#guardian-first-name").val().length < 1) { 
    util_showError(el('#guardian-first-name'));
    isValid = false;
  }
  if(el("#guardian-last-name").val().length < 1) { 
    util_showError(el('#guardian-last-name'));
    isValid = false;
  }
  var emailValid = util_checkRegexp($.trim(el("#guardian-email").val()), util_emailRegexObj);
  if(emailValid == false) {
    util_showError(el('#guardian-email'), 'invalid email address');
    isValid = false;
  }
  
  if (isValid == false) {
    return;
  }
  
  var jsonData = { 
    module:app_module,
    lastName: el('#guardian-last-name').val(), 
    firstName: el('#guardian-first-name').val(), 
    middleName: el('#guardian-middle-name').val(), 
    address1: el('#guardian-address1').val(), 
    city: el('#guardian-city').val(), 
    usState: el('#guardian-us-state').val() ? el('#guardian-us-state').val() : 0,
    postalCode: el('#guardian-postal-code').val(), 
    relation: el('#guardian-rel').val(), 
    primaryPhone: el('#guardian-phone').val(), 
    email: el('#guardian-email').val(),
    patientId: app_patient ? app_patient.id : null,
    sessionId: app_getSessionId()
  };
  app_post("patient/saveNewGuardian", jsonData, function(parsedData) {
    if (parsedData.returnCode == RETURN_CODE_DUP_EMAIL) {
      var args = {
        modalTitle:"Email Address Already In Use", 
        modalH3:"Email Address Already In Use", 
        modalH4:"Please try again with a different email address.",
        cancelButton: null,
        okButton: 'OK'
      };
      RenderUtil.render('dialog/confirm', args, function(s) { 
        $('#modal-confirm').remove(); 
        $('#modals-placement').append(s);
        $('#modal-confirm').modal('show'); 
        $('#app-modal-confirmation-btn').click(function(){  $('#modal-confirm').modal('hide');});
      });
      return;
    }
    $('#modal-new-guardian').modal('hide')
    app_clearNewGuardianForm();
    app_getGuardians(parsedData.id);
    app_displayNotification('New guardian '+ parsedData.firstName + ' ' + parsedData.lastName + ' created.');
  });
}

function app_setupGuardian() {
  var guardianId;

  if (app_patientIsActive() && app_patient.guardian != null) {
    $("#patient-guardian-status").prop("checked", true);
    guardianId = app_patient.guardian.id;
  }
  RenderUtil.render('component/basic_select_options', {options:app_usStates, collection:'app_usStates', choose:true}, function(s) {
    $("#guardian-state").html(s); 
    if (app_patientIsActive() && app_patient.guardian != null) {
      $('#guardian-state').val(app_patient.usState ? app_patient.usState.id : ''); 
      $('#patient-form-guardian-section').show();
      $('#patient-guardian-form-group').show();
    }
  });
  app_handleHasGuardianClick();
  $('#patient-guardian-status').trigger('change')
  app_getGuardians(guardianId);
  var $guardianRel = $('#guardian-rel')
  $guardianRel.on("blur", function(){
    var id = form_field_getInstanceId($(this));
    var _guardian = pm_findGuardianById(id);
    _guardian.relation = $guardianRel.val();
    form_updateField($guardianRel)
  })

  $("#patient-new-guardian-btn").off('click').on('click', function(){ app_newGuardianForm(); });
}
