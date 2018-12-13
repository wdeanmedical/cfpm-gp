function IntakeFormValidator(validator, $form) {
  return {
    validator: window[validator],
    form: $form,
    validate: function() {
      this.validator($form);
    }
  }
} 