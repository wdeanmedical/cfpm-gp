
function module_loadModule() {
  
  if (app_module == SITE_MODULE) {
    module_initSITE();
    return;
  }
  
  RenderUtil.render('module/'+app_module+'_module', {}, function(s) {
    $('#app-container').html(s);
    $('.version-string').html(app_clientProperties['app.version_string']);
    
    app_hideElementListCache = $('#admin-screen,#admin-item-screen, #app-bottom-nav,#app-dropdown-logout,#app-dropdown-settings,' +
    '#app-header,#app-sub-navbar,#appointments-screen,#dashboard-screen,#rx-renewal-screen,#intake-forms-screen,#lead-mgmt-screen,' + 
    '#letters-screen,#main-navigation,#messages-inbox,#patient-files-screen,#messages-screen,#patient-chart-item-screen,#payments-screen,'+
    '#app-chart-header,#patient-chart-screen,#portal-forms-screen,#reports-list,#reports-screen,#reports-view,#schedule-screen,'+
    '#send-message-screen, #settings-screen,#signin-screen,#intake-forms-screen');

    app_adminScreenCache = $('#admin-screen,#app-chart-header, #app-dropdown-settings,#app-dropdown-logout,#top-nav-panel');
    app_adminScreenItemCache = $('#admin-item-screen,#app-chart-header, #app-dropdown-settings,#app-dropdown-logout,#top-nav-panel');
    app_appointmentsCache = $('#appointments-screen,#top-nav-panel');
    app_dashboardCache = $('#dashboard-screen,#app-dropdown-settings,#app-dropdown-logout,#top-nav-panel');
    app_headerFooterCache = $('#main-navigation,#app-header,#app-bottom-nav');
    portal_intakeFormsCache = $('#intake-forms-screen,#top-nav-panel');
    app_leadMgmtCache = $('#lead-mgmt-screen,#top-nav-panel,#app-dropdown-settings');
    app_lettersCache = $('#letters-screen,#app-dropdown-settings,#app-dropdown-logout,#top-nav-panel');
    app_messagesCache = $('#messages-screen,#app-dropdown-settings,#app-dropdown-logout,#messages-inbox,#top-nav-panel');
    app_patientFilesCache = $('#patient-files-screen,#top-nav-panel,#app-chart-header');
    app_patientChartCache = $('#patient-chart-screen,#app-dropdown-settings,#app-dropdown-logout,#app-sub-navbar,#app-chart-header');
    app_patientChartItemCache = $('#patient-chart-item-screen,#app-dropdown-settings,#app-sub-navbar,#app-chart-header');
    portal_patientFormsCache = $('#portal-forms-screen,#top-nav-panel');
    portal_paymentsCache = $('#payments-screen,#top-nav-panel');
    app_reportsCache = $('#reports-screen,#reports-list,#app-dropdown-settings,#app-dropdown-logout,#main-navigation');
    app_rxRenewalCache = $('#rx-renewal-screen,#top-nav-panel');
    app_scheduleCache = $('#schedule-screen,#app-dropdown-settings,#app-dropdown-logout');
    app_sendMessageCache = $('#send-message-screen,#top-nav-panel');
    app_settingsCache = $('#settings-screen,#top-nav-panel');
    app_signinCache = $('#signin-screen');
    
    if (app_module == PM_MODULE) {
      app_clientType = USER_CLIENT_TYPE;
      module_initPM();
    }
    else if (app_module == PORTAL_MODULE) {
      app_clientType = PATIENT_CLIENT_TYPE;
      module_initPORTAL();
    }
    else if (app_module == SITE_MODULE) {
      module_initSITE();
    }
    else if (app_module == STORE_MODULE) {
      module_initSTORE();
    }
    app_getStaticLists();
  });
}



function module_selectModuleFromUrl() {
  var url = util_parseUrl(document.location);
  var pathnameArray = url.pathname.split("/");
  if (pathnameArray.length > 1) {
    app_module = pathnameArray[2];
    module_loadModule();
  }
}

