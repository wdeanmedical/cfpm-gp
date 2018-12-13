
function pm_addInvoiceLineItem(mode) {
  var jsonData = JSON.stringify({ id: app_invoice.id, sessionId: app_getSessionId(), module:app_module });
  $.post("app/addInvoiceLineItem", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_viewInvoice(mode);
  });
}



function portal_cancelEditCreditCard() {
  portal_getCreditCard();
}



function portal_deleteCreditCardConfirm() {
  var args = {
    modalTitle: 'Delete credit card?',
    modalH3: 'Delete credit card?',
    modalH4: null,
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function() {  
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module });
      $.post("billing/deleteCustomer", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        app_displayNotification('Credit card deleted');
        portal_getCreditCard();
      });
    });   
  });
}



function pm_deleteInvoiceConfirm(mode) {
  var args = {
    modalTitle: (mode == CANCEL ? 'Cancel' : 'Delete') + ' Invoice for ' + app_patientFullName + '?', 
    modalH3: (mode == CANCEL ? 'Cancel' : 'Delete') + ' Invoice for ' + app_patientFullName + '?', 
    modalH4:"This invoice will be discarded.",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function() {  
      var jsonData = JSON.stringify({ id: app_invoice.id, sessionId: app_getSessionId(), module:app_module });
      $.post("app/deleteInvoice", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        app_displayNotification('Invoice for ' + app_patientFullName + (mode == CANCEL ? ' cancelled.' : ' deleted'));
        pm_toggleInvoiceScreenControls(DELETED);
        app_getPatientInvoices();
      });
    });   
  });
}



function portal_disableCreditCardForm() {
  $('#cc-auth-header, #cc-auth-footer, #cc-auth-btn, #cc-cancel-btn').hide();
  $('#cc-delete-btn, #cc-edit-btn').show();
  var templateFormName = 'cc-auth-form';
  $('#'+templateFormName+' .input-field').attr("readonly", "readonly");
  $('#'+templateFormName+' .input-field').attr("disabled", "disabled");
  $('#'+templateFormName+' .selectpicker').attr("disabled", "disabled");
  $('#'+templateFormName+' .input-select').attr("disabled", "disabled");
  $('#'+templateFormName+' .input-checkbox').attr("disabled", "disabled");
}



function portal_editCreditCard() {
  $('#cc-edit-btn').hide();
  portal_initCreditCardForm();
  $('#cc-auth-header, #cc-auth-footer, #cc-auth-btn, #cc-cancel-btn').show();
}



function pm_editInvoice() {
  app_viewInvoice(EDIT);
}

var INVOICING_REFRESH_REATE=3000

function invoicing__autoRefresh(refresher){
  setTimeout(refresher, INVOICING_REFRESH_REATE);
}

function portal_getCreditCard() {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module });
  app_post("billing/getCustomer", jsonData, function(parsedData) {
    app_card = parsedData.object;
    var $el =  $('#cc-auth-form-panel');
    var pendingTransaction=parsedData.errorType==PENDING_TRANSACTION;
    if (app_card != null) {
      var data = app_card.sources.data[0];
      var formPrefix = 'cc-auth';
      $('#'+formPrefix+'-cardholder-name').val(data.name);
      $('#'+formPrefix+'-cc-type').val(data.brand);
      $('#'+formPrefix+'-cc-number').val('**** **** **** ' + data.last4);
      $('#'+formPrefix+'-cvc').val('');
      $('#'+formPrefix+'-postal-code').val(data.addressZip);
      $('#'+formPrefix+'-city').val(data.addressCity);
      $('#'+formPrefix+'-street-address').val(data.addressLine1);
      $('#'+formPrefix+'-us-state').val(data.addressState); 
      $('#'+formPrefix+'-exp').val(data.expMonth+data.expYear.toString().slice(-2)).mask('99/99'); 
      portal_disableCreditCardForm();
    }
    else {
      portal_initCreditCardForm();
    }
    var $pendingTransaction=$el.find('#pending-transaction');
    var $authForm=$el.find('#cc-auth-form');
    if (pendingTransaction){
      var errMessage=parsedData.errorMsg || "";
      var progressMessage="Credit card transaction is still in progress, try again in a minute or two."
      var $pendingTransactionMessage
      var autoRefresh=false;
      if (errMessage.length>0){
       $pendingTransactionMessage=invoicing__pendingTransactionEl({errMessage:errMessage})
      } else {
       $pendingTransactionMessage=invoicing__pendingTransactionEl({progressMessage:progressMessage})      
       autoRefresh=true
      }
      $pendingTransactionMessage.find('#refresh-progress').on('click',function(){
        invoicing__unlessTransactionPending(portal_getCreditCard)
      })
      $pendingTransaction.find('#progress-message').html($pendingTransactionMessage)
      if(autoRefresh){
        invoicing__autoRefresh(portal_getCreditCard)
      }
      $pendingTransaction.show()
      $authForm.hide();
    } else{
      $pendingTransaction.hide();
      $authForm.show();
    }
  });
}

function pm_getInvoices() {
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(), 
    module:app_module 
  });
  $.post("patient/getInvoices", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_patientInvoices = parsedData.list;
    RenderUtil.render('component/simple_data_table', 
     {items:app_patientInvoices, 
      title:app_practiceClientProperties['app.patient_label'] + ' Invoices', 
      tableName:'patient-invoices-list', 
      clickable:true, 
      columns:[
        {title:'Number', field:'invoiceNumber', type:'simple', width:'10%'},
        {title: app_practiceClientProperties['app.patient_label'], field:'patientName', type:'simple', width:'20%'},
        {title:'Issue Date', field:'issueDate', type:'date', width:'10%'},
        {title:'Description', field:'title', type:'simple', width:'40%'},
        {title:'Amount', field:'total', type:'dollar', width:'10%'},
        {title:'Status', field:'invoiceStatus', type:'simple', width:'10%'}
      ]}, function(s) {
      $('#patient-invoices-list').html(s);
      $('#patient-invoices-list-title').html("Invoice History");
      $('#patient-invoices-list .clickable-table-row').click( function(e){ 
        $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        app_invoiceId = $(this).attr('name');
        app_viewInvoice(VIEW);
      });
    });
  });
}



function app_getPatientInvoices() {
  var jsonData = JSON.stringify({ 
    patientId: (app_module == PORTAL_MODULE ? app_client.id : app_patientId), 
    sessionId: app_getSessionId(), 
    module:app_module 
  });
  $.post("patient/getPatientInvoices", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_patientInvoices = parsedData.list;
    RenderUtil.render('component/simple_data_table', 
     {items:app_patientInvoices, 
      title:app_practiceClientProperties['app.patient_label'] + ' Invoices', 
      tableName:'patient-invoices-list', 
      clickable:true, 
      columns:[
        {title:'Invoice Number', field:'invoiceNumber', type:'simple', width:'10%'},
        {title:'Issue Date', field:'issueDate', type:'date', width:'10%'},
        {title:'Description', field:'title', type:'simple', width:'60%'},
        {title:'Amount', field:'total', type:'dollar', width:'10%'},
        {title:'Status', field:'invoiceStatus', type:'simple', width:'10%'}
      ]}, function(s) {
      $('#patient-invoices-list').html(s);
      $('#patient-invoices-list-title').html("Invoice History");
      $('#patient-invoice-form').hide();
      $('#patient-invoices-list .clickable-table-row').click( function(e){ 
        $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
        app_invoiceId = $(this).attr('name');
        app_viewInvoice(VIEW);
      });
    });
  });
}



function portal_initCreditCardForm() {
  $('#cc-cancel-btn, #cc-delete-btn, #cc-edit-btn').hide();
  var templateFormName = 'cc-auth-form';
  $('#'+templateFormName+' .input-field').removeAttr("readonly");
  $('#'+templateFormName+' .input-field').removeAttr("disabled");
  $('#'+templateFormName+' .selectpicker').removeAttr("disabled");
  $('#'+templateFormName+' .input-select').removeAttr("disabled");
  $('#'+templateFormName+' .input-checkbox').removeAttr("disabled");
  var formPrefix = 'cc-auth';
  $('#'+formPrefix+'-cc-number').mask("9999 9999 9999 9999");
  $('#'+formPrefix+'-cc-exp').mask("99/99");
  $('#'+formPrefix+'-cvc').mask("999");
  $('#'+formPrefix+'-postal-code').mask("99999");
}



function pm_newInvoice() {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), patientId: app_patientId, module:app_module });
  $.post("patient/createPatientInvoice", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_invoiceId =  parsedData.id;
    $("#patient-invoice-form").show();
    var subtotal = 0;
    var total = 0;
    $('#invoice-sub-total').html('$'+subtotal.formatMoney());
    $('#invoice-total').html('$'+total.formatMoney());
    $('#patient-invoice-title').html('New Invoice');
    app_getPatientInvoices();
    app_viewInvoice(NEW);
  });
}

function invoicing__pendingTransactionEl(options={}){
  var $el
  if(options.errMessage){
   $el = $('<div class="panel-body"><button id="refresh-progress" class="pull-right">Refresh</button>'
        + '<h3 id="progress-message">'+options.errMessage+'</h3></div></div>')
  } else if(options.progressMessage){
    $el = $('<div class="panel-body"><center id="loader"><i class="fa fa-refresh fa-spin fa-4x fa-fw"></i></span><center>'
      + '<h3 id="progress-message">'+options.progressMessage+'</h3></div></div>')
  }
  return $el;
}

function invoicing__unlessTransactionPending(callback){
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module });
  app_post("billing/chargeCustomerStatus", jsonData, function(parsedData){
    var pendingTransaction=parsedData.errorType==PENDING_TRANSACTION;
    if (pendingTransaction){ 
     var errMessage=parsedData.errorMsg || "";
     var progressMessage="Invoice transaction is still in progress, please wait a minute or two."
     var $pendingTransaction
     var autoRefresh=false;
     if (errMessage.length>0){
       $pendingTransaction=invoicing__pendingTransactionEl({errMessage:errMessage})
     } else {
       $pendingTransaction=invoicing__pendingTransactionEl({progressMessage:progressMessage})      
      autoRefresh=true;
     }
     var refresh = function() {
        invoicing__unlessTransactionPending(callback)
     }
     $pendingTransaction.find('#refresh-progress').on('click',refresh)
     $('#patient-invoices-list').html($pendingTransaction);
     $('#patient-invoice-form').hide();
     if(autoRefresh){
      invoicing__autoRefresh(refresh)
     }
    } else{
      callback()
    }
   })
}

function portal_getPatientInvoices(){
  invoicing__unlessTransactionPending(function(){
    app_getPatientInvoices();
  })
}
function portal_payInvoiceConfirm() {
  var args = {
    modalTitle: 'Pay invoice?',
    modalH3: 'Pay invoice #'+app_invoice.invoiceNumber+'?',
    modalH4: '$'+app_invoice.total.formatMoney() + ' will be charged to the card ending in ' + app_card.sources.data[0].last4,
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function() {  
     invoicing__unlessTransactionPending(function(){ 
      var jsonData = JSON.stringify({ invoiceId: app_invoice.id, sessionId: app_getSessionId(), module:app_module });
      app_post("billing/chargeCustomer", jsonData, function(parsedData){
        app_displayNotification('#'+app_invoice.invoiceNumber + ' charged to card ending in ' + app_card.sources.data[0].last4);
        portal_getPatientInvoices();
        $('#patient-invoice-form').hide();
      });
     })  
    });
  })  
}

function invoicing__ifHasCreditCard(callback){
  if(app_card!=null){
    callback()
  }else{
    dialog({
      modalTitle:"Missing Saved Credit Card",
      modalBodyText:"<p>Please add a credit card and try again.</p>"
    })
  }
}

function portal_paymentsScreen() {
  app_viewStack('payments-screen', DO_SCROLL);
  app_getPatientInvoices();
  $('#invoice-print-btn').off('click').on('click', function() { app_printInvoice() });
  $('#invoice-pay-btn').off('click').on('click', function() { invoicing__ifHasCreditCard(portal_payInvoiceConfirm) });
  $('#cc-delete-btn').off('click').on('click', function() { portal_deleteCreditCardConfirm() });
  $('#cc-edit-btn').off('click').on('click', function() { portal_editCreditCard() });
  $('#cc-auth-btn').off('click').on('click', function() { portal_saveCreditCard() });
  $('#cc-cancel-btn').off('click').on('click', function() { portal_cancelEditCreditCard() });
  var formPrefix = 'cc-auth';
  $('#'+formPrefix+'-cc-number').mask("9999 9999 9999 9999");
  $('#'+formPrefix+'-exp').mask("99/99");
  $('#'+formPrefix+'-cvc').mask("999");
  $('#'+formPrefix+'-postal-code').mask("99999");
  RenderUtil.render('component/us_state_code_select_options', {options:app_usStates, collection:'app_usStates', choose:true}, function(s) { $("#cc-auth-us-state").html(s); });
  $('#cc-auth-form-panel').find('#refresh-cc-progress').on("click", function(){
    portal_getCreditCard()
  })
  portal_getCreditCard();
}



function app_printInvoice() {
   var args = {
     obj:app_invoice,
     items:app_invoice.invoiceLineItemList, 
     tableName:'patient-invoice-item-list', 
     columns:[
       {title:'Item', field:'description', type:'simple', width:'90%'},
       {title:'Fee', field:'price', type:'currency'}
     ]
  } 
     
  RenderUtil.render('print/invoice_print', args, function(s) {
    $("#app-print-buffer").html(s);
    $("#app-print-buffer").printThis({
      debug: false,        
      importCSS: true,    
      importStyle: true, 
      printContainer: true, 
      loadCSS: DEFAULT_APP_PATH + "css/all.css", 
      pageTitle: app_invoice.title,       
      removeInline: false,  
      printDelay: 333,  
      header: null,    
      formValues: true
    });
  });
}



function pm_renderInvoicingScreen() {
  RenderUtil.render('screen/patient_invoicing_screen', {}, function(s) {
    $("#patient-chart-item-screen").html(s);
    app_showScreen('Invoicing', app_patientChartItemCache);
    app_chartItemStack($('#invoicing-screen'), true);
    app_getPatientInvoices();
    $('#app-new-invoice-btn').on('click', function() { pm_newInvoice() });
    $('#invoice-cancel-btn').on('click', function() { pm_deleteInvoiceConfirm(CANCEL) });
    $('#invoice-save-btn').on('click', function() { pm_saveInvoice() });
    $('#invoice-print-btn').on('click', function() { app_printInvoice() });
    $('#invoice-send-btn').on('click', function() { pm_sendInvoiceConfirm() });
    $('#invoice-delete-btn').on('click', function() { pm_deleteInvoiceConfirm(DELETE) });
    $('#invoice-edit-btn').on('click', function() { pm_editInvoice() });
  });
}



function pm_renderInvoicingAdminScreen() {
 RenderUtil.render('screen/invoicing_screen', {}, function(s) {
  $("#patient-chart-item-screen").html(s);
  app_showScreen('Invoicing', app_patientChartItemCache);
  app_chartItemStack($('#invoicing-screen'), true);
  pm_getInvoices();
  $('#invoice-print-btn').on('click', function() { app_printInvoice() });
 });  
}



function portal_saveCreditCard() {
  var isValid = true;
  util_clearErrors();  

  if($("#cc-auth-cardholder-name").val().length < 1) {util_showError($('#cc-auth-cardholder-name')); isValid = false;} 
  if($("#cc-auth-cc-type").val().length < 1) {util_showError($('#cc-auth-cc-type')); isValid = false;} 
  if (util_checkRegexp($("#cc-auth-cc-number").val(), util_ccNumberRegexObj) == false) { util_showError($("#cc-auth-cc-number")); isValid = false;}
  if (util_checkRegexp($("#cc-auth-exp").val(), util_ccExpRegexObj) == false) { util_showError($("#cc-auth-exp")); isValid = false;}
  if (util_checkRegexp($("#cc-auth-cvc").val(), util_cvcRegexObj) == false) { util_showError($("#cc-auth-cvc")); isValid = false;}
  if($("#cc-auth-street-address").val().length < 1) {util_showError($('#cc-auth-street-address')); isValid = false;} 
  if($("#cc-auth-city").val().length < 1) {util_showError($('#cc-auth-city')); isValid = false;} 
  if($("#cc-auth-us-state").val().length < 1) {util_showError($('#cc-auth-us-state')); isValid = false;} 
  if (util_checkRegexp($("#cc-auth-postal-code").val(), util_postalCodeRegexObj) == false) { util_showError($("#cc-auth-postal-code")); isValid = false;}
  if($("#cc-auth-initials").val().length < 1) {util_showError($('#cc-auth-initials')); isValid = false;} 
  if ($('#cc-auth-sig').prop("checked") == false) { util_showError($('#cc-auth-sig'), 'this must be checked'); isValid = false;}
  
  if (isValid == false) {
    return;
  }
  
  var expDate = $('#cc-auth-exp').val(); 
  var expMonth = expDate.split('/')[0]; 
  var expYear = expDate.split('/')[1];
  
  var jsonData = JSON.stringify({ 
    module:app_module,
    cardNumber: $('#cc-auth-cc-number').val(), 
    expMonth: expMonth,
    expYear: expYear,
    cvc: $('#cc-auth-cvc').val(), 
    cardholderName: $.trim($('#cc-auth-cardholder-name').val()), 
    streetAddress: $.trim($('#cc-auth-street-address').val()), 
    city: $.trim($('#cc-auth-city').val()), 
    usStateCode: $('#cc-auth-us-state').val(), 
    postalCode: $.trim($('#cc-auth-postal-code').val()), 
    sessionId: app_getSessionId()
  });
  
  $.post("billing/createOrUpdateCustomer", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    if (parsedData.returnCode == RETURN_CODE_DUP_USERNAME) {
      var args = {
        modalTitle:"Username Already In Use", 
        modalH3:"Username Already In Use", 
        modalH4:"Please try again with a different username.",
        cancelButton: null,
        okButton: 'OK'
      };
      RenderUtil.render('dialog/confirm', args, function(s) { 
        $('#modal-confirm').remove(); 
        $('#modals-placement').append(s);
        $('#modal-confirm').modal('show'); 
        $('#app-modal-confirmation-btn').click(function(){  $('#modal-confirm').modal('hide');});
      });
      return;
    }
    if (parsedData.returnCode == RETURN_CODE_DUP_USER_EMAIL) {
      var args = {
        modalTitle:"Email Already In Use", 
        modalH3:"Email Already In Use", 
        modalH4:"Please try again with a different email address.",
        cancelButton: null,
        okButton: 'OK'
      };
      RenderUtil.render('dialog/confirm', args, function(s) { 
        $('#modal-confirm').remove(); 
        $('#modals-placement').append(s);
        $('#modal-confirm').modal('show'); 
        $('#app-modal-confirmation-btn').click(function(){  $('#modal-confirm').modal('hide');});
      });
      return;
    }
    app_displayNotification('Credit card saved');
    portal_getCreditCard();
  });
}



function pm_saveInvoice() {
  app_displayNotification('Invoice saved');
  app_viewInvoice(VIEW);
}



function pm_sendInvoiceConfirm() {
  var args = {
    modalTitle: 'Send Invoice to ' + app_patientFullName + '?', 
    modalH3: 'Send Invoice to ' + app_patientFullName + '?', 
    modalH4: null,
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function() {  
      var jsonData = JSON.stringify({ 
        id: app_invoice.id, 
        patientId: app_patientId,
        sessionId: app_getSessionId(), 
        module:app_module 
      });
      $.post("app/sendInvoice", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        app_displayNotification('Invoice for ' + app_patientFullName + ' sent');
        pm_toggleInvoiceScreenControls(SENT);
        app_getPatientInvoices();
      });
    });   
  });
}



function pm_toggleInvoiceScreenControls(mode) {
  $('.invoice-button').hide();
  $('#invoice-print-btn').show();
  
  if (mode == SENT || mode == DELETED) {
    $('#patient-invoice-title').html('');
    $("#patient-invoice-form").hide();
  }
  else if (mode == EDIT || mode == NEW) {
    $('#invoice-send-btn').show();
    $('#invoice-save-btn').show();
    if (mode == NEW) {
      $('#invoice-cancel-btn').show();
    }
    else {
      $('#invoice-delete-btn').show();
    }
    $('#invoice-issue-date').show();
    $('#invoice-issue-date-saved').hide();
  }
  else if (mode == VIEW) {
    if (app_invoice.invoiceStatus != SENT) {
      $('#invoice-send-btn').show();
      $('#invoice-delete-btn').show();
      $('#invoice-edit-btn').show();
    }
    $('#invoice-issue-date').hide();
    $('#invoice-issue-date-saved').show();
    if (app_invoice.invoiceStatus != PAID) {
      $('#invoice-pay-btn').show();
    }
  }
}



function pm_updateInvoiceItem($this) {
  util_clearItemError($this)
  
  var id = $this.attr('name');
  var updatePropertyValue = $this.val(); 
  var updateProperty = $this.attr('data-property');
  var fieldName = $this.attr('data-property').split('.')[1];
  var updatePropertyFieldType = $this.attr('data-field-type');
  
  if (fieldName == "price") {
    if ($.trim(updatePropertyValue) == "") {
      updatePropertyValue = 0;
    }
    else if (util_checkRegexp($.trim(updatePropertyValue), util_currencyRegexObj) == false) { 
      util_showError($this, 'must be dollar value'); 
      return;
    }
  }
  
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(),
    id: id,
    updateProperty:updateProperty,
    updatePropertyValue:updatePropertyValue,
    updatePropertyFieldType:updatePropertyFieldType
  });
  $.post("app/updateField", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_viewInvoice(EDIT);
    app_getPatientInvoices();
  }); 
}



function app_viewInvoice(mode) {
  var jsonData = JSON.stringify({ id: app_invoiceId, sessionId: app_getSessionId(), module:app_module });
  $.post("patient/getPatientInvoice", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_invoice = parsedData.object;
    app_form = app_invoice;
    app_invoiceId = app_invoice.id;
    $("#patient-invoice-form").show();
    form_addForm('entity-form-Invoice', app_invoice, '#invoice-form');
    
    $('#invoice-issue-date').mask("99/99/9999");
    if(app_invoice.issueDate != null) { $('#invoice-issue-date-saved').html(dateFormat(app_invoice.issueDate, 'mm/dd/yyyy')); }
    if(app_invoice.issueDate != null) { $('#invoice-issue-date').val(dateFormat(app_invoice.issueDate, 'mm/dd/yyyy')); }
    $('#invoice-patient-name').html(app_invoice.patientName);
    $('#invoice-invoice-number').html(app_invoice.invoiceNumber);
    $('#invoice-patient-primary-phone').html(app_invoice.primaryPhone);
    $('#invoice-patient-street-address').html(app_invoice.streetAddress1);
    $('#invoice-patient-city').html(app_invoice.city);
    if(app_invoice.usState){
     $('#invoice-patient-us-state').html(app_invoice.usState.code);
    }
    $('#invoice-patient-postal-code').html(app_invoice.postalCode);
    $('#invoice-title').html(app_invoice.title);
    $('#invoice-description').html(app_invoice.description);
    $('#invoice-sub-total').html('$'+app_invoice.subtotal.formatMoney());
    $('#invoice-total').html('$'+app_invoice.total.formatMoney());
    
    $('#invoice-form .input-field').off('blur').on('blur', function() { form_validateAndUpdateField(this) });
    
    pm_toggleInvoiceScreenControls(mode);
    
    RenderUtil.render('component/' + (mode == EDIT || mode == NEW ? 'editable_' : 'simple_') + 'data_table', 
     {items:app_invoice.invoiceLineItemList, 
      title:'items', 
      tableName:'patient-invoice-item-list', 
      clickable:true, 
      columns:[
        {title:'Item', field:'description', type:'simple', width:'90%'},
        {title:'Fee', field:'price', type:'currency'}
      ],
      instanceName:'entity-form-InvoiceLineItem' 
     }, 
     function(s) {
      $('#patient-invoice-item-list').html(s);
      if (mode == EDIT || mode == NEW) {
        $('#invoice-form .list-item-field').off('blur').on('blur', function(e) { pm_updateInvoiceItem($(this)); });
        $('#add-item-btn').on('click', function() { pm_addInvoiceLineItem(mode) });
      }
      else if (mode == VIEW) {
        $('#patient-invoice-item-list-table tr').each(function() {
          $(this).find('td:last').addClass('text-align-right');
        });
      }
      $('.clickable-table-row').click( function(e){ 
        $(this).addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
      });
    });
  });
}