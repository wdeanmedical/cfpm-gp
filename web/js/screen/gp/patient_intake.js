
function app_evaluatePatientIntakeInput() {
  form_validate_clearErrors();
  _.each(app_intakeFormValidators, function(validator) {
    validator.validate();
  })
  if (app_missingFields.length > 0) {
    var missingFieldsTitle = "<h4>" + "The following fields are required to proceed:" + "</h4>";
    var missingFieldsText = "<ul class='text-danger'>";
    for (var i=0;i<app_missingFields.length;i++) {
      missingFieldsText += "<li>" + app_missingFields[i] + "</li>";
    }
    missingFieldsText += "</ul>";   
    $('#pi-missing-fields').html(missingFieldsText);
    $('.intake-forms-step-'+app_finalScreenButton+'-btn').addClass('disabled');
    $('#pi-no-errors').hide();
    $('#pi-with-errors').show();
  } else {
    $('.intake-forms-step-'+app_finalScreenButton+'-btn').removeClass('disabled');
    $('.intake-forms-step-'+app_finalScreenButton+'-btn').off('click').on('click', function(){ app_closePatientIntake(); });
    $('#pi-no-errors').show();
    $('#pi-with-errors').hide();
  }
}