function app_pm_cancellationPolicyFormValidator(form) {
  form_validate_empty('#patient-intake-cancellation-patient-name','Cancellation: patient name');
  form_validate_empty('#patient-intake-cancellation-completed-by','Cancellation: completed by');
  form_validate_date('#patient-intake-cancellation-completed-by-date','Cancellation: date signed');
  form_validate_checked('#patient-intake-cancellation-read-doc', 'Cancellation: read doc');
}