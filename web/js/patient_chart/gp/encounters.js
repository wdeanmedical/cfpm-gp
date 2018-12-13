function gp_loadEncounterSubForm(encounterId, form, subForm, formName, formPrefix, formScreenName, formClassName, callback, options={}) {
  var args = {encounterId: encounterId, formKey: formClassName.replace('com.wdeanmedical.', '').replace(/\./g, '-'), formPrefix: formPrefix, formName: formName, id: form.id}
  return RenderUtil.render("component/gp/encounter/" + subForm , args, function(html) {
    var $html = $(html);
    $('#'+ formScreenName + ' #form-placeholder').html($html);
    return RenderUtil.render("component/gp/encounter/form_footer", _.extend({section: subForm, formName: formName}, options), function(html) {
      var formWrapper = $('#'+ formScreenName + ' #' + formName).parent();
      $(html).insertAfter(formWrapper);
      gp_encounterEnablePrintSubForm(form, formName);
      form_activateClear(formScreenName);
      var loader = window['gp_load_' + subForm];
      var afterLoad = function() {
        form_loadForm(form, formPrefix);
        if (options.editable) {
          form_renderEditable($html);
          gp_editableSubformRendered(form, formName);
        } else {
          form_renderClosedForm();
        }
        if (callback) { return callback(); }
      }  
      if (loader) {
        form.encounterId=encounterId
        return loader(form, afterLoad, formPrefix);
      } else {
        return afterLoad();
      }  
    })   
  })  
}

function gp_editableSubformRendered(form, formName) {
   var handler = window["gp_editable_" + formName.replace(/-/g, '_') + "_rendered"];
   if (handler) {
    handler(form);
   }
}

function gp_enableCompleteEncounter(formName) {
  $('#' + formName + '-close-record-btn').off('click').on('click', function(e){ form_closeFormConfirm(e); });
}

function gp_renderEncounterDialog() {
  app_currentEncounter = app_form;
  var encounterId = app_currentEncounter.id;
  var args = {
    isActive:true, 
    isClosed: app_form.closed,
    id: encounterId,
    formPrefix: app_templateFormPrefix ,
    formName: app_templateFormName};
  RenderUtil.render('dialog/gp/encounter', args, function(s) { 
    $('#modal-encounter').remove();
    $('#modals-placement').html(s);
    $('#modal-encounter').modal('show'); 
    var editableOptions = {editable: !app_form.closed}
    var formPrefix, propertyName, className, fullClassName;
    gp_loadEncounterSubForm(encounterId, app_currentEncounter.patient, "patient", "patient-form", "patient", "demographics-screen", "entity.Patient" , null, editableOptions);
    var subForms = ["vital_sign", "soap_note", "chief_complaint", "obgyn", "family_history", "medical_history", "exam"];
    var renderSubForms = function() {
      var subForm = subForms.shift();
      if (subForm) {
        formPrefix = subForm.replace('_', '-');
        propertyName = util_toPropertyName(subForm);
        className = util_capitalize(propertyName, {skipFull: true});
        fullClassName = app_getEntityClassName('form.' + className);
        gp_loadEncounterSubForm(encounterId, app_currentEncounter[propertyName], subForm, formPrefix + '-form', formPrefix, formPrefix +'-screen', fullClassName, renderSubForms, editableOptions);
      } else {
        if (!editableOptions.editable) {
          form_renderClosedForm();
        }
      }
    }
    renderSubForms();
    form_enablePrint(app_templateFormPrefix);
    gp_enableCompleteEncounter(app_templateFormName);
  });
}

var _GP_PRINT_SECTION_TITLES = {
  family_history: "Social & Family History",
  vital_sign: "Vitals"
}

function gp_printEncounterForm(template, title, _form) { 
  var currentDate = dateFormat(new Date(), 'mm/dd/yyyy');
  var form = _form || app_form;
  var name = form.name;
  RenderUtil.render('print/gp/encounter/'+ template,  {form:form, formPrefix: name, currentDate:currentDate}, function(html) {
    var $html = $(html);
    form_loadForm(form, name, $html);
    var $formatted = gp_renderPrintEncounterForm($html, {subForm:true})
    printer_printTemplate($formatted, title);
  });
}

function gp_encounterEnablePrintSubForm(form, formName) {
  $('#encounter-' + formName + '-print').on('click', function(){
    var section = $(this).data('section');
    var template = section.replace('-', '_');
    var title = _GP_PRINT_SECTION_TITLES[template];
    if (!title) {
      title = util_capitalizeSplit(template, '_');
    }
    title = title.toUpperCase();
    gp_printEncounterForm(template, title, form);
  })
}

var _GP_FORM_TITLES = {
  encounter: "Patient Encounter",
  obgyn: "OB/GYN",
  family_history: "Social & Family History",
  soap_notes: "SOAP Notes"
}

function _GpPcFormDetails(formName, entity) {
  var _title = _GP_FORM_TITLES[formName];
  if (!_title) {
    _title = util_capitalizeSplit(formName, '_'); 
  }
  var entityName = entity || util_capitalizeSplit(formName, '_').split(' ').join('');
  return {
    name: formName,
    entityName: app_getEntityClassName('form.' + entityName),
    title: _title
  }
}

var GP_ENCOUNTER_FORMS = {};

function gp__pcFormDetails(name, entity) {
  return _GpPcFormDetails(name.replace('-', '_'), entity);
}

function gp_onViewPatientChartScreen() {
  $('.pc-open-form-multiple').off('click').on('click', function(){ 
    var form = gp__pcFormDetails($(this).data('name'), $(this).data('entity'));
    GP_ENCOUNTER_FORMS[form.name]=form.name;
    gp_pm_renderScreenForFormWithMultiple(form.name, form.entityName, form.title);
  });
  $('.pc-open-form').off('click').on('click', function(){ 
    var form = gp__pcFormDetails($(this).data('name'), $(this).data('entity'));
    GP_ENCOUNTER_FORMS[form.name]=form.name;
    gp_pm_renderScreenForForm(form.name, form.entityName, form.title);
  });
}