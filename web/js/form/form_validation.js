function form_validate_addError($field, label) {
  app_missingFields = app_missingFields.concat(label);
}

function dob_validator($field) {
  var val = $.trim($field.val());
  if (val.length == 0) {
    return "Birth date cannot be empty";
  }
  var dob = new Date(val);
  var today = new Date();
  if (dob > today) {
    return "Birth date cannot be in the future";
  }
}

function form_validate_dob(selector, label) {
  if (_.isObject(selector)) {
    return dob_validator(selector);
  } else {
    form_validate__each(selector, dob_validator, label);
  }
}

function form_validate_checked(selector, label) {
 var checkedValidator = function($field) {
  if(!$field.is(':checked')) {
    return "must be checked";
  }
 } 
 form_validate__each(selector, checkedValidator, label);
}

function form_validate_date(selector, label) {
  var dateValidator = function($field) {
    if (util_isDate($.trim($field.val())) == false ) {
      return 'must be valid date';
    } 
  }
  form_validate__each(selector, dateValidator, label);
}

function form_validate_clearErrors() {
  app_missingFields = [];
  app_formError = false;
  util_clearErrors();
}

function form_validate(callback, options={}) {
  var validator = options.validator; 
  if (!validator) return;
  var form = options.form || app_form;
  form_validate_clearErrors();
  if (validator) {
    validator(form);
  }
  if (app_formError === true || app_missingFields.length > 0) {
    var missingFieldsTitle = "";
    var missingFieldsText = "";
    if (app_missingFields.length > 0) {
      missingFieldsTitle = "<h4>" + "The following fields are required to proceed:" + "</h4>";
      missingFieldsText = "<ul class='text-danger'>";
      for (var i=0;i<app_missingFields.length;i++) {
        missingFieldsText += "<li>" + app_missingFields[i] + "</li>";
      }
      missingFieldsText += "</ul>";
    }
    var showDialog = $('#pi-missing-fields').length == 0;
    if (showDialog || options.dialog) {
      var modalH4Text = "The following fields are required to proceed:";
      var modalBodyText = missingFieldsText;
      if (app_formError) {
        modalH4Text = "Please fix the highlighted errors on the page to proceed."
        modalBodyText = "";
      }
      var args = _.extend({ 
        modalH4: modalH4Text,
        modalBodyText: modalBodyText
      }, 
      options.dialog, { 
        okButton: 'Ok',
        cancelButton: null
      });

      RenderUtil.render('dialog/confirm', args, function(s) { 
        $('#modal-confirm').remove();
        $('#modals-placement').append(s);
        $('#modal-confirm').modal('show'); 
      });
    } else {
      $('#pi-missing-fields').html(missingFieldsTitle + missingFieldsText);
      $('#pi-no-errors').hide();
      $('#pi-with-errors').show();
    }    
  } else {
    callback();
  }
}

function form_validate__each(selector, errorFn, label) {
  var $field, errMsg; 
  $(selector).each(function(_, field){
    $field = $(field);
    if (errMsg = errorFn($field)) {
      form_validate_addError($field, label, errMsg);
      util_showError($field, errMsg == true ? undefined : errMsg);
    } else {
       util_clearItemError($field);
    }
  })
}

function form_validate_empty(selector, label) {
  form_validate__each(selector, util_isFieldEmpty, label)
}

function form_validate_regex(selector, regex, label) {
  var regexValidator = function($field) {
    return util_checkRegexp($.trim($field.val()), regex) == false;
  }
  form_validate__each(selector, regexValidator, label)
}

function form_validateField($this) {
  var isValid = true;
  util_clearItemError($this);
  var id = $this.attr('id');
  var updateProperty = $this.attr('data-property');
  
  if (typeof updateProperty == 'undefined') {
    return;
  }
  var errorFns = form_field_getValidators($this);
  var testValidators = function() {
    errorFn = errorFns.shift();
    if (errorFn) {
      if (errMsg = errorFn($this)) {
        util_showError($this, errMsg);
        return false;
      } else {
        testValidators();
      }   
    }
  }
  testValidators();

  var updatePropertyValue = $this.val();
  var updatePropertyArray = updateProperty.split('.'); 
  var className = updatePropertyArray[0];
  var property = updatePropertyArray[1];
  
  
  if (className == "bh-entity-form-ClientContact") {
    if (property == 'firstName' && util_isFieldEmpty('#cc-first-name')) {
      util_showError($('#cc-first-name')); 
      return false; 
    }
    else if (property == 'lastName' && util_isFieldEmpty('#cc-last-name')) {
      util_showError($('#cc-last-name')); 
      return false; 
    }
    else if (property == 'dob' && util_isDate($.trim($('#cc-dob').val())) == false) {
      util_showError($('#cc-dob'), 'must be valid date'); 
      return false; 
    }
    else if (property == 'signer' && util_isFieldEmpty('#cc-signer')) {
      util_showError($('#cc-signer')); 
      return false; 
    }
    else if (property == 'signerRelationship' && util_isFieldEmpty('#cc-signer-rel')) {
      util_showError($('#cc-signer-rel')); 
      return false; 
    }
    else if (property == 'voicemailOk' && util_isFieldEmpty('#cc-voicemail-ok')) {
      util_showError($('#cc-voicemail-ok')); 
      return false; 
    }
    else if (property == 'msgOk' && util_isFieldEmpty('#cc-msg-ok')) {
      util_showError($('#cc-msg-ok')); 
      return false; 
    }
    else if (property == 'cellMsgOk' && util_isFieldEmpty('#cc-cell-msg-ok')) {
      util_showError($('#cc-cell-msg-ok')); 
      return false; 
    }
    else if (property == 'textOk' && util_isFieldEmpty('#cc-text-msg-ok')) {
      util_showError($('#cc-cell-text-ok')); 
      return false; 
    }
    else if (property == 'textWaiverSigned' && util_isFieldEmpty('#cc-text-waiver-signed')) {
      util_showError($('#cc-cell-text-waiver-signed')); 
      return false; 
    }
    else if (property == 'callWorkOk' && util_isFieldEmpty('#cc-call-work-ok')) {
      util_showError($('#cc-call-work-ok')); 
      return false; 
    }
    else if (property == 'msgWorkOk' && util_isFieldEmpty('#cc-msg-work-ok')) {
      util_showError($('#cc-msg-work-ok')); 
      return false; 
    }
    else if (property == 'noInfo' && util_isFieldEmpty('#cc-no-info')) {
      util_showError($('#cc-no-info')); 
      return false; 
    }
    else if (property == 'completedBy' && util_isFieldEmpty('#cc-completed-by')) {
      util_showError($('#cc-completed-by')); 
      return false; 
    }
    else if (property == 'completedByDate' && util_isDate($.trim($('#cc-completed-by-date').val())) == false) {
      util_showError($('#cc-completed-by-date'), 'must be valid date'); 
      return false; 
    }
  }
  else if (className == "bh-entity-form-ClientRights") {
    if (property == 'name' && util_isFieldEmpty('#cr-name')) {
      util_showError($('#cr-name')); 
      return false; 
    }
    else if (property == 'dob' && util_isDate($.trim($('#cr-dob').val())) == false) {
      util_showError($('#cr-dob'), 'must be valid date'); 
      return false; 
    }
    else if (property == 'signedDate' && util_isDate($.trim($('#cr-signed-date').val())) == false) {
      util_showError($('#cr-signed-date'), 'must be valid date'); 
      return false; 
    }
  }
  else if (className == "bh-entity-form-Consent") {
    if (property == 'respName' && util_isFieldEmpty('#consent-resp-name')) {
      util_showError($('#consent-resp-name')); 
      return false; 
    }
    else if (property == 'respDate' && util_isDate($.trim($('#consent-resp-date').val())) == false) {
      util_showError($('#consent-resp-date'), 'must be valid date'); 
      return false; 
    }
  }
  else if (className == "bh-entity-form-TextingWaiver") {
    if (property == 'name' && util_isFieldEmpty('#texting-name')) {
      util_showError($('#texting-name')); 
      return false; 
    }
    else if (property == 'guardian' && util_isFieldEmpty('#texting-guardian')) {
      util_showError($('#texting-guardian')); 
      return false; 
    }
    else if (property == 'guardianDate' && util_isDate($.trim($('#texting-guardian-date').val())) == false) {
      util_showError($('#texting-guardian-date'), 'must be valid date'); 
      return false; 
    }
    else if (property == 'provider' && util_isFieldEmpty('#texting-provider')) {
      util_showError($('#texting-provider')); 
      return false; 
    }
    else if (property == 'providerDate' && util_isDate($.trim($('#texting-provider-date').val())) == false) {
      util_showError($('#texting-provider-date'), 'must be valid date'); 
      return false; 
    }
  }
  else if (className == "pot-entity-form-POTPatientForm") {
    if (property == 'govtId' && util_checkRegexp($.trim($("#patient-form-ssn").val()), util_ssnRegexObj) == false) {
      util_showError($('#patient-form-ssn'), 'invalid ssn'); 
      return false; 
    }
  }
  else if (className == "entity-form-Invoice") {
    if (property == 'issueDate' && util_isDate($.trim($('#invoice-issue-date').val())) == false) {
      util_showError($('#invoice-issue-date'), 'must be valid date'); 
      return false; 
    }
  }
  
  return true;
}