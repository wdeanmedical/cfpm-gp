function app_loadTextingWaiverForm(form) {
  var data;
  var className;
  
  if (form && form.data) {
    data = form.data;
    className = form.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#texting-waiver-form');
  }
  else {
    var data = app_form;
    className = data.practiceForm.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#texting-waiver-form');
  }

  util_debug('in app_loadTextingWaiverForm()');
  $('#texting-client-name').val(data.clientName);
  $('#texting-dob').mask("99/99/9999");
  if(data.dob != null) { $('#texting-dob').val(dateFormat(data.dob, 'mm/dd/yyyy')); }
  $('#texting-guardian').val(data.guardian);
  $('#texting-guardian-date').mask("99/99/9999");
  if(data.guardianDate != null) { $('#texting-guardian-date').val(dateFormat(data.guardianDate, 'mm/dd/yyyy')); }
  $('#texting-client-rel').val(data.clientRel);
  $('#texting-provider').val(data.provider);
  $('#texting-provider-date').mask("99/99/9999");
  if(data.providerDate != null) { $('#texting-provider-date').val(dateFormat(data.providerDate, 'mm/dd/yyyy')); }
}