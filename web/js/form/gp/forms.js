
function gp_pm_intakeForms() {
  return [
      IntakeForm("client_contact", "Client Contact"), 
      IntakeForm("client_rights", "Client Rights"), 
      IntakeForm("consent", "Consent For Medical Services"), 
      IntakeForm("texting_waiver", "Texting Waiver"),
      IntakeForm("release", "Release of information"),
      IntakeForm("emergency_info", "In Case of Emergency"),
      IntakeForm("self_rating", "Self Rating"),
    ];
}

function app_closeEncounterForm() {
  var $completed = form_instanceStatusSelector(app_form.id);
  $completed.html(true);
  app_displayNotification('Patient Encounter Record Completed');
  $('#modal-encounter').modal('hide');
}

function app_loadEncounterSubForm() {
  var formName = app_form.name;
  var editable = !gp_form_renderClosedForm(formName);
  if (app_form.closed) {
    editable = false;
  } 
  var editableOptions = {editable: editable};
  return gp_loadEncounterSubForm(app_form.encounterId, app_form, formName, app_templateFormName, app_templateFormPrefix, app_templateScreenName, app_form.practiceForm.className, null, editableOptions);
}