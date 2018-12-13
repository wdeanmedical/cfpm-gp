function chief_complaint_setPainScaleValue(value) {
  $('#chief-complaint-pain-scale-value').html(value);
}
function chief_complaint_updatePainScale($field, value) {
  $field.val(value);
  form_updateField($field);
}
function gp_load_chief_complaint(form, callback) {
  chief_complaint_setPainScaleValue(form.painScale);
  callback();
}

function gp_editable_chief_complaint_form_rendered(form) {
  RenderUtil.render("component/gp/encounter/pain_scale_slider", {formPrefix: "chief-complaint"}, function(html) {
    $('#chief-complaint-pain-scale-placeholder').replaceWith(html);    
    form_renderEditable('#chief-complaint-screen');
    $('#chief-complaint-pain-scale').css('width', '100px').slider(
      {value:form.painScale}
    ).on('slide', function(){
      chief_complaint_setPainScaleValue(this.value);
      chief_complaint_updatePainScale($(this), this.value);
    })
  })
}