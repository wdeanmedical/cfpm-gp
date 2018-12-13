function app_pm_renderScreenForFormWithMultiple(formName, formClassName, formTitle) {
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