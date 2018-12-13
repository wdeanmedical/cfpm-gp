function app_loadClientContactForm(form) {
  
  var data;
  var className;
  
  if (form && form.data) {
    data = form.data;
    className = form.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#client-contact-form');
  }
  else {
    var data = app_form;
    className = data.practiceForm.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#client-contact-form');
    $('#client-contact-form').attr('data-instance-id', data.id);
  }
  
  util_debug('in app_loadClientContactForm()');
  $('#cc-first-name').val(data.firstName);
  $('#cc-middle-name').val(data.middleName);
  $('#cc-last-name').val(data.lastName);
  $('#cc-dob').mask("99/99/9999");
  if(data.dob != null) { $('#cc-dob').val(dateFormat(data.dob, 'mm/dd/yyyy')); }
  $('#cc-signer').val(data.signer);
  $('#cc-signer-rel').val(data.signerRelationship);
  $('#cc-vm-ok').val(util_booleanToInteger(data.voicemailOk));
  $('#cc-cell-msg-ok').val(util_booleanToInteger(data.cellMsgOk));
  $('#cc-msg-ok').val(util_booleanToInteger(data.msgOk));
  $('#cc-msg-names').val(data.msgNames);
  $('#cc-text-ok').val(util_booleanToInteger(data.textOk));
  $('#cc-text-waiver-signed').val(util_booleanToInteger(data.textWaiverSigned));
  $('#cc-email-ok').val(util_booleanToInteger(data.emailOk));
  $('#cc-email').val(data.email);
  $('#cc-call-work-ok').val(util_booleanToInteger(data.callWorkOk));
  $('#cc-msg-work-ok').val(util_booleanToInteger(data.msgWorkOk));
  $('#cc-contact-other').val(util_booleanToInteger(data.contactOther));
  $('#cc-contact-other-detail').val(data.contactOtherDetail);
  $('#cc-no-info').val(util_booleanToInteger(data.noInfo));
  $('#cc-home-phone').mask("(999) 999-9999");
  $('#cc-home-phone').val(data.homePhone);
  $('#cc-cell-phone').mask("(999) 999-9999");
  $('#cc-cell-phone').val(data.cellPhone);
  $('#cc-work-phone').mask("(999) 999-9999");
  $('#cc-work-phone').val(data.workPhone);
  $('#cc-completed-by').val(data.completedBy);
  $('#cc-completed-by-date').mask("99/99/9999");
  if(data.completedByDate != null) { $('#cc-completed-by-date').val(dateFormat(data.completedByDate, 'mm/dd/yyyy')); }
}
