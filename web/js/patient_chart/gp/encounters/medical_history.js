function gp_load_medical_history(form, callback) {
  var list =  form.medicationList;
  var args = {
    items: list, 
    parentId: form.id,
    parentIdName: "medicalHistoryId",
    resource: "patient-history-medication",
    itemClass: "entity-PatientHistoryMedication"
  }
  RenderUtil.render('component/gp/encounter/medical_history/medications', args, function(s) { 
    $('#medical-history-form #patient-history-medication-list').replaceWith(s);
    form_list_populateList($('#medical-history-form #patient-history-medication-list'), list);
    if (callback) callback();
  })
}
function gp_editable_medical_history_form_rendered(medicalHistory) {
  var medicalHistoryId = medicalHistory.id; 
  var args = {
    parentId: medicalHistoryId,
    parentIdName: "medicalHistoryId",
    entityClass: "entity-PatientHistoryMedication",
    resourceName: "Medication"
  }
  form_list_setupList("medical-history-form", 'component/list/new', args, function(list, callback){
    gp_load_medical_history({medicationList: list, id: medicalHistoryId}, callback);
  })
}