function  gp_load_patient(form, callback, formPrefix) {
  var formName = formPrefix + '-form';
  form_field_initUsStateDropDown(formName);
  form_mask_activateInputMask(formName);
  var $dateField = $('#' + formName + " #patient-created-date");
  form_field_loadField($dateField, form.createdDate);
  var $imageField = $('#' + formName + " #patient-photo");
  if (form.profileImagePath) {
    form_field_loadField($imageField, patient_getProfileImagePath(form));
  }
  $('#' + formName + " #encounter-notes").val(app_currentEncounter.notes);
  if (!app_currentEncounter.closed) {
    RenderUtil.render("component/patient_photo_upload", {formPrefix: formPrefix}, function(html){
      $('#' + formName + ' #patient-photo-upload-placeholder').replaceWith(html);
      app_setupPictureUpload();
      callback();
    })
  } else {
    callback();
  }  
} 