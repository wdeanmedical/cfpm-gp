function app_pm_releaseFormValidator(form) {
  form_validate_empty('#release-first-name','Release Authorization: first name');
  form_validate_empty('#release-last-name','Release Authorization: last name');
  form_validate_dob('#release-dob','Release Authorization: dob');
  form_validate_empty('#release-street-address','Release Authorization: street address');
  form_validate_empty('#release-city','Release Authorization: city');
  form_validate_empty('#release-us-state','Release Authorization: state');
  form_validate_empty('#release-postal-code','Release Authorization: postal code');
  form_validate_empty('#release-phone','Release Authorization: phone');
  form_validate_checked('#release-read-doc', 'Release Authorization: information verification');
}