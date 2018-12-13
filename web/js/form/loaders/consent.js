function app_loadConsentForm(form) {
  var data;
  var className;
  
  if (form && form.data) {
    data = form.data;
    className = form.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#consent-form');
  }
  else {
    var data = app_form;
    className = data.practiceForm.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#consent-form');
  }
  
  util_debug('in app_loadConsentForm()');
  $('#consent-client-name').val(data.clientName);
  $('#consent-initials-0').val(data.initials0);
  $('#consent-initials-1').val(data.initials1);
  $('#consent-initials-2').val(data.initials2);
  $('#consent-initials-3').val(data.initials3);
  $('#consent-initials-4').val(data.initials4);
  $('#consent-initials-5').val(data.initials5);
  $('#consent-initials-6').val(data.initials6);
  $('#consent-initials-7').val(data.initials7);
  $('#consent-initials-8').val(data.initials8);
  $('#consent-initials-9').val(data.initials9);
  $('#consent-initials-10').val(data.initials10);
  $('#consent-initials-11').val(data.initials11);
  $('#consent-initials-12').val(data.initials12);
  $('#consent-dob').mask("99/99/9999");
  if(data.dob != null) { $('#consent-dob').val(dateFormat(data.dob, 'mm/dd/yyyy')); }
  $('input[name=consent-signed]').prop("checked", data.signed);
  $('#consent-resp-name').val(data.respName);
  $('#consent-resp-date').mask("99/99/9999");
  if(data.respDate != null) { $('#consent-resp-date').val(dateFormat(data.respDate, 'mm/dd/yyyy')); }
  $('#consent-client-rel').val(data.clientRel);
  $('#consent-witness').val(data.witness);
  $('#consent-witness-date').mask("99/99/9999");
  if(data.witnessDate != null) { $('#consent-witness-date').val(dateFormat(data.witnessDate, 'mm/dd/yyyy')); }
}
