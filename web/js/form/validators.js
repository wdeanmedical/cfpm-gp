var form__validators = {
  dob: "form_validate_dob"
}

function form_validators_get(name) {
  return window[form__validators[name]];
}