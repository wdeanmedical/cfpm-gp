function form_list__wrapperSetup(entityClass, resourceName, resource, listTemplate, listSelector, items, readonly, options={}, afterLoad) {
  if (!readonly) {
    form_list_setupList(null, 'component/list/new', _.extend({}, options, {entityClass: entityClass, resourceName: resourceName}), function(list, callback){
       form_list_load(listTemplate, entityClass, resource, list, false, listSelector, callback , options); 
    }, $(listSelector).parent());
  } 
  form_list_load(listTemplate, entityClass, resource, items, readonly, listSelector, afterLoad, options); 
}

function app_renderHealthIssues(items, readonly) {
  form_list__wrapperSetup("entity-form-HealthIssue", "Health Issue", "health-issue", 'component/health_issue', '#health-issues', items, readonly);
}

function app_renderMedicalConditions(items, readonly) {
  form_list__wrapperSetup("entity-form-MedicalCondition", "Illness", "medical-condition", 'component/medical_condition', '#medical-conditions' , items, readonly);
}

function app_renderMedications(items, readonly, parentId, parentIdName) {
  form_list__wrapperSetup("entity-form-PatientMedication", "Medication", "medication", 'component/patient_medication', '#medications', items, readonly, {parentId: parentId, parentIdName: parentIdName});
}

function pot__form_list_wrapperSetup(entityClass, resourceName, resource, listTemplate, listSelector, items, readonly, parentId, parentIdName, options={}) {
  var afterLoad;
  if (options.skipAfterLoad != true) {
     afterLoad = function() {
      return defer(function() { form_initRadioButtonGroups() });
    }
  }
  form_list__wrapperSetup(entityClass, resourceName, resource, listTemplate, listSelector, items, readonly, _.extend({}, options.templateOptions || {}, {parentId: parentId, parentIdName: parentIdName}), afterLoad);
}

function pot_pm_renderPatientFormMedAllergies(items, readonly, parentId, parentIdName) {
  pot__form_list_wrapperSetup("entity-form-PatientMedAllergy", "Med Allergy", "med-allergy", 'component/patient_med_allergy', '#medical-allergies', items, readonly, parentId, parentIdName);
}

function pot_pm_renderPatientFormFoodAllergies(items, readonly, parentId, parentIdName) {
  pot__form_list_wrapperSetup("entity-form-PatientFoodAllergy", "Food Allergy", 'food-allergy', 'component/patient_food_allergy', '#food-allergies', items, readonly, parentId, parentIdName);
}

function pot_pm_renderPatientFormEvaluations(items, readonly, parentId, parentIdName, templateOptions) {
  pot__form_list_wrapperSetup("pot-entity-form-Evaluation", "Evaluation", 'evaluation', 'component/evaluation', '#evaluations', items, readonly, parentId, parentIdName, {skipAfterLoad: true, templateOptions: templateOptions});
}

function pot_pm_renderPatientFormTreatments(items, readonly, parentId, parentIdName, templateOptions) {
  pot__form_list_wrapperSetup("pot-entity-form-Treatment", "Treatment", 'treatment', 'component/treatment', '#treatments', items, readonly, parentId, parentIdName, {skipAfterLoad: true, templateOptions: templateOptions});
}
