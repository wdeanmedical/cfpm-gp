function gp_pm_renderScreenForForm(formName, formClassName, formTitle) {
  form_loadCurrentForm(formName, formClassName, formTitle, true, true, false);
}

function gp_pm_renderScreenForFormWithMultiple(formName, formClassName, formTitle) {
  app_templateFormPrefix = formName.replaceAll("_","-");
  app_templateFormName = app_templateFormPrefix + '-form';
  app_templateScreenName = app_templateFormPrefix + '-screen';
  app_templateClassName = formClassName.replace("com.wdeanmedical.","").replaceAll(".","-");
  app_templateFormTitle = formTitle;
 
  var args = { 
    formPrefix: app_templateFormPrefix, 
    templateFormName: app_templateFormName, 
    templateScreenName: app_templateScreenName, 
    templateFormScreen: app_templateScreenName, 
    formTitle: app_templateFormTitle, 
    formClassName: app_templateClassName 
  };
  RenderUtil.render(form_specialtyFormTemplate(formName, formClassName), args, function(s) {
    var templateFormName = formName.replaceAll("_","-") + '-form';
    var templateScreenName = formName.replaceAll("_","-") + '-screen';
    $('#' + app_templateScreenName).remove();
    $("#patient-chart-item-screen").html(s);
    app_showScreen(formTitle, app_patientChartItemCache);
    app_chartItemStack($('#'+templateScreenName), true); 
    form_loadPracticeFormInstances(formName, formClassName, formTitle, function(){
       $('#app-new-'+app_templateFormPrefix+'-btn').click( function() { 
         form_newMultipleInstanceForm(formName, formClassName, formTitle, function(){
          app_formNewInstanceId = app_form.id;
          form_loadPracticeFormInstances(formName, formClassName, formTitle, function(){
            app_formNewInstanceId = undefined;
          });
         }) 
      }); 
    });
  })  
}

var gp_form_encounter_openFormCloseDialogArgs =  {
  modalTitle:"Complete Encounter Confirmation", 
  modalH3:"Ready To Complete The Currently Open Encounter?", 
  modalH4:"In order to start a new encounter the current one needs to be completed.",
  cancelButton: 'Cancel',
  okButton: 'Confirm'
}

var gp_form_encounter_closeFormDialogArgs =  {
  modalTitle:"Complete Encounter Confirmation", 
  modalH3:"Ready To Complete The Encounter?", 
  modalH4:"Once completed, the encounter is locked.",
  cancelButton: 'Cancel',
  okButton: 'Confirm'
};

function gp_pm_view_encounter() {
  gp_renderEncounterDialog();
}

var GP_ENCOUNTER_OPENED_FORMS = [
 "encounter", "medical_history", "family_history", "obgyn"
]

function gp_form_renderClosedForm(formName) {
  if (GP_ENCOUNTER_FORMS[formName] && !_.contains(GP_ENCOUNTER_OPENED_FORMS, formName)) {
    return true;
  } 
  return false;
}

function gp_form_specialtyFormTemplate(formName, formClassName) {
  if (GP_ENCOUNTER_FORMS[formName] && formName != "encounter") {
    return "form/gp/encounter/" + formName + "_form";
  } 
}

var gp_pm_columns_encounter =  [
  {title:'Date', field:'date', type:'date'},
  {title:'Clinician', field:'clinicianName', type:'clinician'},
  {title:'Completed', field:'closed', class: 'status', type:'simple'},
  {title:'Notes', field:'notes', type:'simple'}
]

var gp_pm_columns_vital_sign = [
  {title:'Date', field:'date', type:'date'}, 
  {title:'Weight', field:'weight', type:'simple'},
  {title:'BMI', field:'bmi', type:'simple'},
  {title:'OFC', field:'ofc', type:'simple'},
  {title:'Temp', field:'temperature', type:'simple'},
  {title:'Pulse', field:'pulse', type:'simple'},
  {title:'Resp', field:'respiration', type:'simple'},
  {title:'Syst', field:'systolic', type:'simple'},
  {title:'Dia', field:'diastolic', type:'simple'},
  {title:'Ox', field:'oximetry', type:'simple'}
]

var gp_pm_data_table_options_vital_sign = {
  clickable:false,
  template: "simple_data_table_highlightable" ,
  initFn: function(instances) { gp_init_vital_sign_data_table(instances); }
}

var gp_renderPrintEncounterForm = function($html, options={}){
  var $panel="<div class='panel'></div>"
  var $wrapped = undefined
  if (options.subForm === true) $wrapped = $html.wrap($panel)
  var $formatted = form_toPrint(null, $wrapped || $html);
  return $formatted.unwrap($panel)
}

form_instanceParams[GP_SPECIALTY] = function(practiceFormName) {
  var viewInstanceFn = window["gp_pm_view_" + practiceFormName];
  var columns = window["gp_pm_columns_" + practiceFormName];
  var dataTableOptions = window["gp_pm_data_table_options_" + practiceFormName];
  return new InstanceParams({viewInstanceFn: viewInstanceFn, columns: columns, dataTableOptions: dataTableOptions});
}