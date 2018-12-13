function form_field_getValidator($field) {
  return form_validators_get($field.data('validator'));
}

function form_field_getInstanceId($field){
  return $field.closest('#patient-form-guardian-section').attr('data-instance-id');
}

function form_field_getValidators($field) {
  var validators = $field.data('validator');
  if (validators) {
    validators = validators.split(',');
  }  else {
    validators = [];
  }
  return _.map(validators, function(name){
    return form_validators_get(name);
  })
}

function form_field_getUpdateParams($this) {
  var id = $this.attr('data-id');
  var updatePropertyValue = $this.data('value') || $this.val(); 
  var updateProperty = $this.attr('data-property');
  var updatePropertyFieldType = $this.attr('data-field-type');
  var updateProperty = $this.attr('data-property');
  var updateExtra = JSON.parse($this.attr('data-update-extra') || "{}");

  return _.extend({ 
    module:app_module,
    sessionId: app_getSessionId(),
    id: id,
    updateProperty:updateProperty,
    updatePropertyValue:updatePropertyValue,
    updatePropertyFieldType:updatePropertyFieldType
  }, updateExtra)
}

function form_field_loadField($field, val) {
  var inputType = $field[0].type;
  var fieldType = $field.data('fieldType');
  if (fieldType == "selectpicker"){
    var inputVal = val;
    if ($field.attr('multiple') !== undefined){
      inputVal = inputVal.split(',');
    }
    $field.selectpicker('val', inputVal)
    $field.selectpicker('refresh')
  }
  else if (inputType == "radio") {
    var valString = val + ""; 
    if ($field.val() == valString) {
       $field.prop('checked', true);
    }else {
      $field.removeAttr('checked');
    }
    return;
  }
  if (inputType == "checkbox") {
    var isChecked = val == "true";
    var fieldVal = $.trim($field.val());
    if (_.isString(val) && (fieldType != "boolean")) {
      isChecked = fieldVal.length != 0 && util_stringContains(val, fieldVal);
    } 
    if (isChecked) {
      $field.prop('checked', true);
    } else {
      $field.removeAttr('checked');
    }
    return;
  }
  var fieldVal = val; 
  if (val !== undefined) {
    if (fieldType == "image") {
      $field.attr('src', fieldVal);
      return;
    } else if (fieldType == 'int-boolean') {
      fieldVal = val == "true" ? 1 : 0;
    } else if (fieldType == 'date') {
      fieldVal = dateFormat(fieldVal, 'mm/dd/yyyy');
    } else if (fieldType == "icd9") {
      fieldVal = val.code+" "+val.codeText;
    } else if (fieldType == "cpt") {
      fieldVal = val.code + " "+val.description;
    } else if (_.isObject(fieldVal)) {
      fieldVal = fieldVal.id;
    }
  } 
  if (inputType === undefined) {
    $field.html(fieldVal);
  } else{
    $field.val(fieldVal);
  }
}

function form_field_disable($field) {
  $field.prop('disabled', 'disabled');
  $field.data('property', '');
}

function form_field_changeEvent($field) {
  var fieldClass = $field.attr('class');
  if (util_stringContains(fieldClass, 'input-field')) {
    return "blur";
  } else {
    return 'change';
  }
}
function form_field_makeUpdateable($field, fieldClass, fieldPrefix, options={}) {
  var property = form__field_getProperty($field, fieldPrefix);
  if ($field.data('property') === undefined) {
    $field.attr('data-property', fieldClass + '.' + property);
  }
  $field.prop('disabled', false);
  if ($field.data('updater') === undefined && $field.type != 'hidden') {  
    var changeEvent = form_field_changeEvent($field);
    if (options.listItem) {
      $field.off(changeEvent).on(changeEvent, function() { form_updateListItem($(this)); })
    } else {
      $field.off(changeEvent).on(changeEvent, function() { form_updateField($(this)); })
    }
  }  
}
function form_field__idname($field) {
  var val = $field.attr('id');
  if (val) return val;
  return $field.attr('name');
}

function form__field_getProperty($field, formPrefix) {
  var idName = form_field__idname($field);
  if (!idName) return;
  var id = idName.replace(formPrefix + '-', '').replace(/--id.*$/, '');
  var property = id.replace(/-/g, '_');
  property = util_toPropertyName(property);
  return property;
}

function form_field_initUsStateDropDown(formName) {
  return RenderUtil.render('component/basic_select_options', {options:app_usStates, collection:'app_usStates'}, function(s) {
    $('#' + formName + ' .input-form-us-state').each(function(_, elem){
      $(elem).html(s);
    })
  });
}

function form_field_initGenderDropDown(formName) {
  return RenderUtil.render('component/basic_select_options', {options:app_gender, collection:'app_gender', choose:true}, function(s) {
    $('#' + formName + ' .input-form-gender').each(function(_, elem){
      $(elem).html(s);
    })
  });
}

function form_field_initMaritalStatusDropDown(formName) {
  return RenderUtil.render('component/basic_select_options', {options:app_maritalStatus, collection:'app_maritalStatus', choose:true}, function(s) { 
    $('#' + formName + ' .input-form-marital-status').each(function(_, elem){
      $(elem).html(s);
    })
  });
}