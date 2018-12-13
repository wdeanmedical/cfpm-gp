
function app_showScreen(screenName, screen, hideHeaderFooter) {
  $('#app-screen-name').html(screenName);
  app_hideElementListCache.css({display: "none"});
  screen.css({display: "block"});
  if (!hideHeaderFooter) {
    app_headerFooterCache.css({display: "block"});
  }
}



function app_viewStack(screen, doScroll) {
  app_previousScreen = app_screen;
  if (app_module == PM_MODULE) { $('body').removeClass('signin-pm'); }
  else if (app_module == PORTAL_MODULE) { $('body').removeClass('signin-portal'); }
  $('#top-nav-panel').css({display: "none"});
  $('#app-logout-submit').css({display: "inline-block"});
  $('#portal-footer').show();
  
  switch (screen) {
    case 'admin-screen':
      app_showScreen('Administration', app_adminScreenCache);
    break;
    case 'appointments-screen':
      app_showScreen('', app_appointmentsCache);
      app_calendar = $('#app-calendar');
      app_loadCalendar();
    break;
    case 'dashboard-screen':
      app_showScreen('Dashboard', app_dashboardCache);
      if (app_module == PM_MODULE) { pm_getUserDashboard(); }
      app_calendar = $('#dash-calendar');
      app_calendarView = 'agendaWeek';
      app_loadCalendar();
    break;
    case 'front-sheet-screen':
      pm_renderFrontSheet();
    break;
    case 'intake-forms-screen':
      app_showScreen('', portal_intakeFormsCache);
    break;
    case 'invoicing-screen':
      pm_renderInvoicingScreen();
      break;
    case 'lead-mgmt-screen':
      app_showScreen('Lead Mgmt', app_leadMgmtCache);
    break;
    case 'letters-screen':
      app_showScreen('Letters', app_lettersCache);
    break;
    case 'medical-consent-screen':
      pm_renderMedicalConsent();
    break;
    case 'patient-files-screen':
      app_showScreen('Patient Files', app_patientFilesCache);
    break;
    case 'medical-history-screen':
      pm_renderMedicalHistory();
    break;
    case 'messages-screen':
      app_showScreen('Messages', app_messagesCache);
    break;
    case 'payments-screen':
      app_showScreen('Forms', portal_paymentsCache);
    break;
    case 'patient-chart-screen':
      app_showScreen('Patient Chart', app_patientChartCache);
    break;
    case 'physical-examination-screen':
      pm_renderPhysicalExamination();
    break;
    case 'portal-forms-screen':
      app_showScreen('Forms', portal_patientFormsCache);
    break;
    case 'progress-note-screen':
      pm_renderProgressNote();
    break;
    case 'reports-screen':
      app_showScreen('Reports', app_reportsCache);
    break;
    case 'rx-renewal-screen':
      app_showScreen('', app_rxRenewalCache);
    break; 
    case 'schedule-screen':
      app_showScreen('Schedule', app_scheduleCache);
      app_calendar = $('#app-calendar');
      app_loadCalendar();
    break; 
    case 'send-message-screen':
      app_showScreen('', app_sendMessageCache);
    break;
    case 'settings-screen':
      app_showScreen('', app_settingsCache);
    break;
    case 'signin-screen':
      if (app_module == PM_MODULE) { $('body').addClass('signin-pm'); }
      else if (app_module == PORTAL_MODULE) { $('body').addClass('signin-portal'); $('#portal-footer').hide(); }
      app_showScreen('', app_signinCache, true);
      $('#app-logout-submit').css({display: "none"});
    break;
  }
  app_screen = screen;
  if (doScroll) {scroll(0,0);}
}



function app_chartItemStack(screen, doScroll) {
  app_previousScreen = app_screen;
  screen.css({display: "block"});
  app_screen = screen;
  if (doScroll) {scroll(0,0);}
}



function app_intakeFormsViewStack(screen, doScroll) {
  app_previousScreen = app_screen;
  $('.forms-subscreen').css({display: "none"});
  screen.css({display: "block"});
  app_screen = screen;
  if (doScroll) {scroll(0,0);}
}
