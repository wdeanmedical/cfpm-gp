var MASKS = {
  phone: "(999) 999-9999",
  date: "99/99/9999",
  zipcode: "99999",
  ssn: "999-99-9999"
}
function form_mask__elem($elem) {
  return MASKS[$elem.data('maskType')];
}
function form_mask_activateInputMask(formName, el) {
  var $elem; 
  var $form = $('#' + formName);
  if (el) {
    $form = $(el);
  }
  $form.find(' .input-masked').each(function(_, elem) {
    $elem = $(elem);
    var mask = form_mask__elem($elem);
    if (mask) {
      $elem.mask(mask);
    }  
  })
}