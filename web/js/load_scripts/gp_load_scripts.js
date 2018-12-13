SPECIALTY_INTAKE_FORMS = [
  "client_contact", "client_rights", "consent", "texting_waiver", 
  "release", "emergency_info", "self_rating"
]

MODELS = ["intake_form_validator"]

head.js (
  "js/screen/gp/patient_intake.js",           
  "js/screen/billing_ticket.js",     
  "js/form/gp/forms.js",
  "js/form/gp/instances.js",
  "js/form/gp/print.js",
  "js/patient_chart/gp/patient_chart.js",
  "js/patient_chart/gp/encounters.js",
  "js/patient_chart/gp/encounters/vital_signs.js",
  "js/patient_chart/gp/encounters/patient.js",
  "js/patient_chart/gp/encounters/medical_history.js",
  "js/patient_chart/gp/encounters/exam.js",
  "js/patient_chart/gp/encounters/chief_complaint.js",
);