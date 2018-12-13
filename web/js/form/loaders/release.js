function app_loadReleaseForm(form) {
  var data;
  var className;
  
  if (form && form.data) {
    data = form.data;
    className = form.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#release-form');
  }
  else {
    var data = app_form;
    className = data.practiceForm.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#release-form');
  }
  
  util_debug('in app_loadReleaseForm()');
  $('#release-first-name').val(data.firstName);
  $('#release-middle-name').val(data.middleName);
  $('#release-last-name').val(data.lastName);
  $('#release-dob').mask("99/99/9999");
  if(data.dob != null) { $('#release-dob').val(dateFormat(data.dob, 'mm/dd/yyyy')); }
  $('#release-street-address').val(data.streetAddress);
  $('#release-city').val(data.city);
  RenderUtil.render('component/basic_select_options', {options:app_usStates, collection:'app_usStates', choose:true}, function(s) { 
    $('#release-us-state').html(s); 
    $('#release-us-state').val(data.usState ? data.usState.id: '');
  });
  $('#release-postal-code').mask("99999");
  $('#release-postal-code').val(data.postalCode);
  $('#release-phone').val(data.phone);
  $('#release-alias').val(data.alias);
  $('#release-initials-1').val(data.initials1);
  $('#release-agency-name').val(data.agencyName);
  $('#release-agency-contact').val(data.agencyContact);
  $('#release-agency-street-address').val(data.agencyStreetAddress);
  $('#release-agency-city').val(data.agencyCity);
  RenderUtil.render('component/basic_select_options', {options:app_usStates, collection:'app_usStates', choose:true}, function(s) { 
    $('#release-agency-us-state').html(s); 
    $('#release-agency-us-state').val(data.agencyUSState ? data.agencyUSState.id: '');
  });
  $('#release-agency-postal-code').mask("99999");
  $('#release-agency-postal-code').val(data.agencyPostalCode);
  $('#release-agency-phone').val(data.agencyPhone);
  $('#release-agency-fax').val(data.agencyFax);
  util_selectCheckboxesFromList(data.infoType, 'infoType');
  $('#release-info-type-other').val(data.infoTypeOther);
  util_selectCheckboxesFromList(data.purpose, 'purpose');
  $('#release-purpose-other').val(data.purposeOther);
  $('#release-initials-2').val(data.initials2);
  $('#release-initials-3').val(data.initials3);
  $('#release-initials-4').val(data.initials4);
  $('#release-exp-date').mask("99/99/9999");
  if(data.expDate != null) { $('#release-exp-date').val(dateFormat(data.expDate, 'mm/dd/yyyy')); }
  $('#release-sig-patient').val(data.sigPatient);
  $('#release-sig-date').mask("99/99/9999");
  if(data.sigDate != null) { $('#release-sig-date').val(dateFormat(data.sigDate, 'mm/dd/yyyy')); }
  $('#release-sig-rep').val(data.sigRep);
  $('#release-sig-rep-date').mask("99/99/9999");
  if(data.sigRepDate != null) { $('#release-sig-rep-date').val(dateFormat(data.sigRepDate, 'mm/dd/yyyy')); }
  $('#release-sig-rel').val(data.sigRel);
  $('#release-read-doc').prop("checked", data.readDoc);
}