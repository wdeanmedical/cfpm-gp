function app_loadPrintEncounterForm($template) {
 ["patient", "vitalSign", "soapNote", "chiefComplaint", "medicalHistory", "familyHistory", "exam"].forEach(function(form){
   form_loadForm(app_form[form], form, $template)
 })   
}