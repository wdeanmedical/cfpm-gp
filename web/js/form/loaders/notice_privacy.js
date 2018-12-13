function app_loadNoticePrivacyForm(form) {
  var data;
  var className;
  
  if (form && form.data) {
    data = form.data;
    className = form.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#notice-privacy-form');
  }
  else {
    var data = app_form;
    className = data.practiceForm.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#notice-privacy-form');
  }

  util_debug('in app_loadNoticePrivacyForm()');
  $('#notice-privacy-read-doc').prop("checked", data.readDoc);
  $('#notice-privacy-completed-by').val(data.completedBy);
  if(data.completedByDate != null) { $('#notice-privacy-completed-by-date').val(dateFormat(data.completedByDate, 'mm/dd/yyyy')); }
  $('#notice-privacy-relationship-to-patient').val(data.relationshipToPatient); 
  $('.input-date').mask("99/99/9999");
}
