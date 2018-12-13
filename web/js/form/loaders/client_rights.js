function app_loadClientRightsForm(form) {
  var data;
  var className;
  
  if (form && form.data) {
    data = form.data;
    className = form.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#client-rights-form');
  }
  else {
    var data = app_form;
    className = data.practiceForm.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#client-rights-form');
  }
  
  util_debug('in app_loadClientRightsForm()');
  $('#cr-client-name').val(data.clientName);
  $('#cr-dob').mask("99/99/9999");
  if(data.dob != null) { $('#cr-dob').val(dateFormat(data.dob, 'mm/dd/yyyy')); }
  $('#cr-guardian').val(data.guardian);
  $('#cr-signed-date').mask("99/99/9999");
  if(data.signedDate != null) { $('#cr-signed-date').val(dateFormat(data.signedDate, 'mm/dd/yyyy')); }
  $('#cr-guardian-rel').val(data.guardianRel);
  $('#cr-clinician-sig-date').mask("99/99/9999");
  if(data.clinicianSigDate != null) { $('#cr-clinician-sig-date').val(dateFormat(data.clinicianSigDate, 'mm/dd/yyyy')); }
  $('#cr-clinician-sig').val(data.clinicianSig);
  $('#cr-child').prop("checked", data.child);
}