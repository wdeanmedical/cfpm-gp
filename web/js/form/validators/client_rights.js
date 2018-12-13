function app_pm_clientRightsFormValidator(form) {
  form_validate_empty('#cr-client-name','Client Rights: client name');
  form_validate_empty('#cr-clinician-sig','Client Rights: clinician name');
  form_validate_date('#cr-dob', 'Client Rights: dob');
  form_validate_date('#cr-signed-date', 'Client Rights: date signed');
  form_validate_date('#cr-clinician-sig-date', 'Client Rights: clinician date signed');
}

