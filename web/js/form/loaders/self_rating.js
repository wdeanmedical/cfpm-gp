function app_loadSelfRatingForm(form) {
  var data;
  var className;
  
  if (form && form.data) {
    data = form.data;
    className = form.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#self-rating-form');
  }
  else {
    var data = app_form;
    className = data.practiceForm.className.replace("com.wdeanmedical.","").replaceAll(".","-");
    form_addForm(className, data, '#self-rating-form');
  }
  
  util_debug('in app_loadSelfRatingForm()');
  $('#sr-rel').val(data.rel);
  $('#sr-freq').val(data.freq);
  var formSections = ['report'];
  form_initRadioButtonGroups(formSections, data, className, data.id);
}