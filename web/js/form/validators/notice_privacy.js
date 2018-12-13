function app_pm_noticePrivacyFormValidator(form) {
  form_validate_empty('#notice-privacy-completed-by','Notice Privacy: completed by');
  form_validate_date('#notice-privacy-completed-by-date', 'Notice Privacy: date signed');
  form_validate_checked('#notice-privacy-read-doc', 'Notice Privacy: read doc');
}