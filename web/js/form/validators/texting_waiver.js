function app_pm_textingWaiverFormValidator(form) {
  form_validate_empty('#texting-client-name','Texting Waiver: client name');
  form_validate_empty('#texting-guardian','Texting Waiver: guardian');
  form_validate_empty('#texting-provider','Texting Waiver: provider');
  form_validate_date('#texting-provider-date', 'Texting Waiver: date signed by provider');
  form_validate_date('#texting-guardian-date', 'Texting Waiver: date signed by guardian');
}