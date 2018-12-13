

function pm_handleIntakePacketWizardNavigation(screenNumber){
  pm_intakePacketStack($('#intake-packet-subscreen-'+screenNumber), true);
}



function pm_intakePacketStack(screen, doScroll) {
  app_previousScreen = app_screen;
  $('.intake-packet-subscreen').css({display: "none"});
  screen.css({display: "block"});
  app_screen = screen;
  if (doScroll) {scroll(0,0);}
}



function pm_renderDischargeForm() {
 RenderUtil.render('screen/'+SPECIALTY+'/pm/discharge_form_screen', {}, function(s) {
  $("#patient-chart-item-screen").html(s);
  app_showScreen('Discharge Form', app_patientChartItemCache);
  app_chartItemStack($('#discharge-form-screen'), true);
 });
}



function pm_renderEdAdvocateContract() {
 RenderUtil.render('screen/'+SPECIALTY+'/pm/ed_advocate_contract_screen', {}, function(s) {
  $("#patient-chart-item-screen").html(s);
  app_showScreen('Ed Advocate', app_patientChartItemCache);
  app_chartItemStack($('#ed-advocate-contract-screen'), true);
 });
}



function pm_renderIntakePacketScreen(){
 RenderUtil.render('intake_packet_screen', {}, function(s) {
  $("#patient-chart-item-screen").html(s);
  showScreen('Intake Packet', intakePacketCache);
  app_intakePacketStack($('#intake-packet-subscreen-1'), true);
    $('.intake-packet-step-btn, .stepwizard-btn-circle').click(function(){handleIntakePacketWizardNavigation($(this).attr('name'))});
 });  
}



function pm_renderReferralForm() {
 RenderUtil.render('screen/'+SPECIALTY+'/pm/referral_form_screen', {}, function(s) {
  $("#patient-chart-item-screen").html(s);
  app_showScreen('Referral', app_patientChartItemCache);
  app_chartItemStack($('#referral-form-screen'), true);
 });
}



function pm_renderSchoolConsent() {
 RenderUtil.render('screen/'+SPECIALTY+'/pm/school_consent_screen', {}, function(s) {
  $("#patient-chart-item-screen").html(s);
  app_showScreen('School Consent', app_patientChartItemCache);
  app_chartItemStack($('#school-consent-screen'), true);
 });
}
