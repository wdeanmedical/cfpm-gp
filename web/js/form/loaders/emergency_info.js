function app_loadEmergencyInfoForm(form) {
  var data;
  var className;
  
  if (form && form.data) {
    data = form.data;
    className = form.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#emergency-info-form');
  }
  else {
    var data = app_form;
    className = data.practiceForm.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#emergency-info-form');
  }

  util_debug('in app_loadEmergencyInfo()');
  $('#emerg-date').mask("99/99/9999");
  if(data.date != null) { $('#emerg-date').val(dateFormat(data.date, 'mm/dd/yyyy')); }
  $('#emerg-ssn').val(data.ssn);
  $('#emerg-lang').val(data.lang);
  $('#emerg-first-name').val(data.firstName);
  $('#emerg-middle-name').val(data.middleName);
  $('#emerg-last-name').val(data.lastName);
  $('#emerg-dob').mask("99/99/9999");
  if(data.dob != null) { 
    $('#emerg-dob').val(dateFormat(data.dob, 'mm/dd/yyyy')); 
    $('#emerg-age').val(util_calculateAgeFromBirthDate(data.dob));
  }
  $('#emerg-street-address').val(data.streetAddress);
  $('#emerg-city').val(data.city);
  RenderUtil.render('component/basic_select_options', {options:app_usStates, collection:'app_usStates', choose:true}, function(s) { 
    $('#emerg-us-state').html(s); 
    $('#emerg-us-state').val(data.usState ? data.usState.id: '');
  });
  $('#emerg-postal-code').mask("99999");
  $('#emerg-postal-code').val(data.postalCode);
  $('#emerg-phone').mask("(999) 999-9999");
  $('#emerg-phone').val(data.phone);
  $('#emerg-drop-off-name').val(data.dropOffName);
  $('#emerg-drop-off-phone').mask("(999) 999-9999");
  $('#emerg-drop-off-phone').val(data.dropOffPhone);
  $('#emerg-pick-up-name').val(data.pickUpName);
  $('#emerg-pick-up-phone').mask("(999) 999-9999");
  $('#emerg-pick-up-phone').val(data.pickUpPhone);
  $('#emerg-emerg-name').val(data.emergName);
  $('#emerg-emerg-phone').mask("(999) 999-9999");
  $('#emerg-emerg-phone').val(data.emergPhone);
  $('#emerg-insurance').val(data.insurance);
  $('#emerg-policy-number').val(data.policyNumber);
  $('#emerg-contact-name-1').val(data.contactName1);
  $('#emerg-contact-rel-1').val(data.contactRel1);
  $('#emerg-contact-phone-1').mask("(999) 999-9999");
  $('#emerg-contact-phone-1').val(data.contactPhone1);
  $('#emerg-contact-name-2').val(data.contactName2);
  $('#emerg-contact-rel-2').val(data.contactRel2);
  $('#emerg-contact-phone-2').mask("(999) 999-9999");
  $('#emerg-contact-phone-2').val(data.contactPhone2);
  $('#emerg-pcp-name').val(data.pcpName);
  $('#emerg-pcp-phone').mask("(999) 999-9999");
  $('#emerg-pcp-phone').val(data.pcpPhone);
  $('#emerg-prescriber-name').val(data.prescriberName);
  $('#emerg-prescriber-phone').mask("(999) 999-9999");
  $('#emerg-prescriber-phone').val(data.prescriberPhone);
  $('#emerg-spec-name').val(data.specName);
  $('#emerg-spec-phone').mask("(999) 999-9999");
  $('#emerg-spec-phone').val(data.specPhone);
  $('#emerg-hospital').val(data.hospital);
  $('#emerg-pharmacy').val(data.pharmacy);
  $('#emerg-dx-1').val(data.dx1);
  $('#emerg-dx-2').val(data.dx2);
  $('#emerg-dx-3').val(data.dx3);
  $('#emerg-allergy-1').val(data.allergy1);
  $('#emerg-allergy-level-1').val(data.allergyLevel1);
  $('#emerg-allergy-2').val(data.allergy2);
  $('#emerg-allergy-level-2').val(data.allergyLevel2);
  $('#emerg-allergy-3').val(data.allergy3);
  $('#emerg-allergy-level-3').val(data.allergyLevel3);
  $('#emerg-allergy-info').val(data.allergyInfo);
  $('#emerg-med-1').val(data.med1);
  $('#emerg-dose-1').val(data.dose1);
  $('#emerg-freq-1').val(data.freq1);
  $('#emerg-prescriber-1').val(data.prescriber1);
  $('#emerg-med-2').val(data.med2);
  $('#emerg-dose-2').val(data.dose2);
  $('#emerg-freq-2').val(data.freq2);
  $('#emerg-prescriber-2').val(data.prescriber2);
  $('#emerg-med-3').val(data.med3);
  $('#emerg-dose-3').val(data.dose3);
  $('#emerg-freq-3').val(data.freq3);
  $('#emerg-prescriber-3').val(data.prescriber3);
  $('#emerg-med-info').val(data.medInfo);
  $('#emerg-sig-patient').val(data.sigPatient);
  $('#emerg-sig-date').mask("99/99/9999");
  if(data.sigDate != null) { $('#emerg-sig-date').val(dateFormat(data.sigDate, 'mm/dd/yyyy')); }
  $('#emerg-sig-rep').val(data.sigRep);
  $('#emerg-sig-rep-date').mask("99/99/9999");
  if(data.sigRepDate != null) { $('#emerg-sig-rep-date').val(dateFormat(data.sigRepDate, 'mm/dd/yyyy')); }
  $('#emerg-sig-rel').val(data.sigRel);
  $('#emerg-read-doc').prop("checked", data.readDoc);
}
