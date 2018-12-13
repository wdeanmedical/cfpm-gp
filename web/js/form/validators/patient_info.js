function app_pm_patientInfoFormValidator(form) {
  form_validate_empty('#patient-info-first-name','Patient Info: first name');
  form_validate_empty('#patient-info-last-name','Patient Info: last name');
  form_validate_dob('#patient-info-birth-date','Patient Info: birth date');
  form_validate_empty('#patient-info-gender','Patient Info: gender');
  form_validate_empty('#patient-info-address','Patient Info: address');
  form_validate_empty('#patient-info-city','Patient Info: city');
  form_validate_empty('#patient-info-us-state','Patient Info: state');
  form_validate_empty('#patient-info-postal-code','Patient Info: zip');
  form_validate_empty('#patient-info-home-phone','Patient Info: at least one phone required');
  form_validate_checked('#patient-info-info-true', 'Patient Info: information true');
  form_validate_empty('#patient-info-completed-by','Patient Info: completed by');
  form_validate_date('#patient-info-completed-by-date','Patient Info: date signed');
}