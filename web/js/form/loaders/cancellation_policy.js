
function app_loadCancellationPolicyForm(form) {
  var data;
  var className;
  
  if (form && form.data) {
    data = form.data;
    className = form.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#cancellation-policy-form');
  }
  else {
    var data = app_form;
    className = data.practiceForm.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#cancellation-policy-form');
    $('#cancellation-policy-form').attr('data-instance-id', data.id);
  }
  
  util_debug('in app_loadCancellationPolicyForm()');
  $('#patient-intake-cancellation-name').val(data.name);
  $('#patient-intake-cancellation-patient-name').val(data.patientName);
  $('#patient-intake-cancellation-read-doc').prop("checked", data.readDoc);
  $('#patient-intake-cancellation-completed-by').val(data.completedBy);
  if(data.completedByDate != null){ $('#patient-intake-cancellation-completed-by-date').val(dateFormat(data.completedByDate, 'mm/dd/yyyy')); }
  $('.input-date').mask("99/99/9999");
}
