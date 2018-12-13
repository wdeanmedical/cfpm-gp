
function app_renderPatientFormsScreen() {
  RenderUtil.render('screen/patient_forms_screen', {}, function(s) {
    $("#patient-chart-item-screen").html(s);
    app_showScreen('Forms', app_patientChartItemCache);
    app_chartItemStack($('#patient-forms-screen'), true);
    app_getPatientForms();
    app_getPracticeForms();
    $('#app-new-patient-form-btn').on('click', function() { app_assignPatientFormConfirm() });
  });
}

function pm_specialtyOnViewPatientChartScreeen() {
  var func = window[SPECIALTY + "_onViewPatientChartScreen"]
  if (func) {
    func();
  }
}

function pm_viewPatientChartScreen() {
  RenderUtil.render('screen/'+SPECIALTY+'/pm/patient_chart_screen', {}, function(s) {
    $("#patient-chart-screen").html(s);
    app_viewStack('patient-chart-screen', DO_SCROLL);
    $("#patient-chart-screen").show();
    
    pm_specialtyOnViewPatientChartScreeen();

    if (SPECIALTY != POT_SPECIALTY) {
      $('#pc-billing-ticket-link').click(function(){ app_renderBillingTicket(); });
    }
    if (SPECIALTY == BH_SPECIALTY) {     
      $('#pc-edt-registration-link').click(function(){ form_loadCurrentForm('edt_registration', 'com.wdeanmedical.bh.entity.form.EDTRegistration', 'EDT Regisitration', true, true, NOT_A_RELOAD); });
      $('#pc-tap-registration-link').click(function(){ form_loadCurrentForm('tap_registration', 'com.wdeanmedical.bh.entity.form.TAPRegistration', 'TAP Regisitration', true, true, NOT_A_RELOAD); });
      $('#pc-bai-link').click(function(){ app_pm_renderScreenForFormWithMultiple('bai', 'com.wdeanmedical.bh.entity.form.BAI', 'Beck Anxiety Inventory'); });
      $('#pc-risk-assessment-link').click(function(){ app_pm_renderScreenForFormWithMultiple('risk_assessment', 'com.wdeanmedical.bh.entity.form.RiskAssessment', 'Risk Assessment'); });
      $('#pc-treatment-plan-link').click(function(){ app_pm_renderScreenForFormWithMultiple('treatment_plan', 'com.wdeanmedical.bh.entity.form.TreatmentPlan', 'Treatment Plan'); });
      $('#pc-crisis-plan-link').click(function(){ app_pm_renderScreenForFormWithMultiple('crisis_plan', 'com.wdeanmedical.bh.entity.form.CrisisPlan', 'Crisis Plan'); });
      $('#pc-child-history-link').click(function(){ form_loadCurrentForm('child_history', 'com.wdeanmedical.bh.entity.form.ChildHistory', "Child's History", true, true, NOT_A_RELOAD); });
      $('#pc-parent-meeting-link').click(function(){ app_pm_renderScreenForFormWithMultiple('parent_meeting', 'com.wdeanmedical.bh.entity.form.ParentMeeting', 'Parent Meeting'); });
      $('#pc-incident-report-link').click(function(){ app_pm_renderScreenForFormWithMultiple('incident_report', 'com.wdeanmedical.bh.entity.form.IncidentReport', 'Incident Report'); });
      $('#pc-clinical-progress-link').click(function(){ app_pm_renderScreenForFormWithMultiple('clinical_progress', 'com.wdeanmedical.bh.entity.form.ClinicalProgress', 'Clinical Progress'); });
      $('#pc-parent-cbc-link').click(function(){ form_loadCurrentForm('parent_cbc', 'com.wdeanmedical.bh.entity.form.ParentCBC', 'Parent CBC', true, true, NOT_A_RELOAD); });
      $('#pc-teacher-cbc-link').click(function(){ form_loadCurrentForm('teacher_cbc', 'com.wdeanmedical.bh.entity.form.TeacherCBC', 'Teacher CBC', true, true, NOT_A_RELOAD); });
      $('#pc-fba-bip-link').click(function(){ app_pm_renderScreenForFormWithMultiple('fba_bip', 'com.wdeanmedical.bh.entity.form.FBABIP', 'FBA/BIP'); });
      $('#pc-youth-self-report-link').click(function(){ form_loadCurrentForm('youth_self_report', 'com.wdeanmedical.bh.entity.form.YouthSelfReport', 'Youth Self Report', true, true, NOT_A_RELOAD); });
      $('#pc-ihb-progress-link').click(function(){ app_pm_renderScreenForFormWithMultiple('ihb_progress', 'com.wdeanmedical.bh.entity.form.IHBProgress', 'IHB Progress'); });
      $('#pc-clinic-discharge-link').click(function(){ app_pm_renderScreenForFormWithMultiple('clinic_discharge', 'com.wdeanmedical.bh.entity.form.ClinicDischarge', 'Clinic Discharge'); });
      $('#beaconeot-link').click(function(){ pm_renderBeaconEOT(); });
      $('#referral-form-link').click(function(){ pm_renderReferralForm(); });
      $('#discharge-form-link').click(function(){ pm_renderDischargeForm(); });
      $('#school-consent-link').click(function(){ pm_renderSchoolConsent(); });
      $('#ed-advocate-contract-link').click(function(){ pm_renderEdAdvocateContract(); });
      
      if (app_patient.programs) {
        var programs = app_patient.programs.split(",");
        if (programs[0]) { $('a.pc-tile').hide(); }
        $('a.pc-tile.ALL').show();
        for (var i=0;i<programs.length;i++) {
          var program = programs[i];
          if (program) { $('a.pc-tile.'+program).show(); }
        }
      }
    }
    else if (SPECIALTY == AC_SPECIALTY) {
      $('#treatment-notes-link').click(function(){ app_pm_renderScreenForFormWithMultiple('treatment_note', 'com.wdeanmedical.ac.entity.form.TreatmentNote', 'Treatment Note'); });
    }
    
    if (SPECIALTY == POT_SPECIALTY) {
      if (app_patientIsActive()) {
        $(".pc-tile").show();
        $('#ot-intake-form-link').click(function(){ form_renderScreenForFormWithMultiple('OT Intake', 'ot_intake', 'com.wdeanmedical.pot.entity.form.OTIntake'); });
        $('#speech-intake-form-link').click(function(){ pot_pm_renderSpeechIntakeScreen(); });
        $('#aac-intake-form-link').click(function(){ pot_pm_renderAACIntakeScreen(); });
        $('#ot-eval-form-link').click(function(){ app_setEvalMode(EVAL); form_renderScreenForFormWithMultiple('OT Evaluation', 'ot_eval', 'com.wdeanmedical.pot.entity.form.OTEval', pot_pm_evalOptions()); });
        $('#ot-cons-form-link').click(function(){ app_setEvalMode(CONS); form_renderScreenForFormWithMultiple('OT Consultation', 'ot_eval', 'com.wdeanmedical.pot.entity.form.OTEval', pot_pm_evalOptions()); });
        $('#ot-init-tx-form-link').click(function(){ app_setEvalMode(TX); form_renderScreenForFormWithMultiple('OT Treatment Plan', 'ot_eval', 'com.wdeanmedical.pot.entity.form.OTEval', pot_pm_evalOptions()); });
        $('#ot-tx-form-link').click(function(){ form_renderScreenForFormWithMultiple('OT Treatment Note', 'ot_tx', 'com.wdeanmedical.pot.entity.form.OTTx'); });
        $('#ot-prog-form-link').click(function(){ form_renderScreenForFormWithMultiple('OT Progress Note', 'ot_prog', 'com.wdeanmedical.pot.entity.form.OTProg'); });
        $('#dev-hx-form-link').click(function(){ form_loadCurrentForm('dev_hx', 'com.wdeanmedical.pot.entity.form.DevHx', 'Developmental Hx', true, true, NOT_A_RELOAD); });
        $('#speech-eval-form-link').click(function(){ pot_pm_renderSpeechEvalScreen(); });
        $('#aac-eval-form-link').click(function(){ pot_pm_renderAACEvalScreen(); });
        $('#pc-patient-info-link').click(function(){ pot_renderPatientInfoScreen(); });
      }
    }
    else {
      $('#pc-patient-info-link').click(function(){ app_getPatientInfo(); });
    } 
    
    $('#pc-files-link').click(function(){ pm_viewPatientFiles(); });
    $('#pc-intake-forms-link').click(function(){ app_renderPatientIntakeScreen(); });
    $('#pc-letters-link').click(function(){ app_renderLettersScreen(); });
    $('#pc-invoicing-link').click(function(){ pm_renderInvoicingScreen(); });
    $('#pc-patient-forms-link').click(function(){ app_renderPatientFormsScreen(); });
    
  });
}

