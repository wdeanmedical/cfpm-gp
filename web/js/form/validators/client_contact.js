function app_pm_clientContactFormValidator() {
  form_validate_empty('#cc-first-name', 'Client Contact: first name');   
  form_validate_empty('#cc-last-name', 'Client Contact: last name');  
  form_validate_empty('#cc-signer', 'Client Contact: signer');  
  form_validate_empty('#cc-signer-rel', 'Client Contact: relationship to client');  
  form_validate_empty('#cc-vm-ok', 'Client Contact: voicemail OK');  
  form_validate_empty('#cc-msg-ok', 'Client Contact: message OK');  
  form_validate_empty('#cc-text-ok', 'Client Contact: texting OK');  
  form_validate_empty('#cc-call-work-ok', 'Client Contact: call work OK');  
  form_validate_empty('#cc-cell-msg-ok', 'Client Contact: cell OK');  
  form_validate_empty('#cc-msg-work-ok', 'Client Contact: message work OK');  
  form_validate_empty('#cc-no-info', 'Client Contact: no information left');  
  form_validate_empty('#cc-home-phone', 'Client Contact: phone');  
  form_validate_empty('#cc-completed-by', 'Client Contact: completed by');  
  form_validate_dob('#cc-dob','Client Contact: DOB');
  form_validate_date('#cc-completed-by-date','Client Contact: completed by date');
}