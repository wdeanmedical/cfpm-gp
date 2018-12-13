function form_renderScreenForFormWithMultiple(formTitle, formName, formClassName, listViewOptions = {}) {
 form__renderViewWithList(formTitle, formName, formClassName, function(){
  return ["screen", SPECIALTY, app_module, formName + "_screen" ].join('/');
 }, listViewOptions)
}


function form__renderViewWithList(formTitle, formName, formClassName, templateFn, listViewOptions = {}) {
  app_templateFormPrefix = formName.replaceAll("_","-");
  app_templateFormName = app_templateFormPrefix + '-form';
  app_templateScreenName = app_templateFormPrefix + '-screen';
  app_templateClassName = formClassName.replace("com.wdeanmedical.","").replaceAll(".","-");
  app_templateFormTitle = formTitle;
  var args = _.extend({ 
    formPrefix: app_templateFormPrefix, 
    templateFormName: app_templateFormName, 
    templateScreenName: app_templateScreenName, 
    formTitle: app_templateFormTitle, 
    formClassName: app_templateClassName 
  }, listViewOptions);
  RenderUtil.render(templateFn(), args, function(s) {
    var templateFormName = formName.replaceAll("_","-") + '-form';
    var templateScreenName = formName.replaceAll("_","-") + '-screen';
    $("#patient-chart-item-screen").html(s);
    app_showScreen(formTitle, app_patientChartItemCache);
    app_chartItemStack($('#'+templateScreenName), true); 
    form_loadPracticeFormInstances(formName, formClassName, formTitle, function(){
       $('#app-new-'+app_templateFormPrefix+'-btn').click( function() { 
         form_newMultipleInstanceForm(formName, formClassName, formTitle, function(){
          app_formNewInstanceId = app_form.id;
          form_loadPracticeFormInstances(formName, formClassName, formTitle, function(){
            app_formNewInstanceId = undefined;
          });
         }) 
      }); 
    });
  })  
}


function form_loadForm(form, formPrefix, _$root) {
  var $root, formName = formPrefix + '-form';
  if (_$root) {
    $root = _$root;
  } else {
    $root = $('#' + formName);
  }
  var $field, val;
  form__load(
    form,
    $root.find('.input-field, .input-checkbox, .input-checkbox-group, .input-select'),
    formPrefix
  )
}

function form__load(form, $fields, formPrefix){
  $fields.each(function(_, field) {
    $field = $(field);
    property = form__field_getProperty($field, formPrefix);
    if(form.hasOwnProperty(property)){
      val = form[property];
      if (!util_isObject(val)) {
        val = $.trim(val);
      }
      form_field_loadField($field, val);
    }
  })  
}

function form_renderEditable(template) {
  var $field;
  var $form = $(template).find('form');
  var templateFormName = $form.attr('id');
  var formPrefix = templateFormName.replace('-form', '');
  var className = $form.data('formKey').replace(/-id.*/, '');
  var property;
  $form.find('.input-field, .input-select, .input-checkbox, .input-checkbox-group').each(function(_, field){
    $field = $(field);
    form_field_makeUpdateable($(field), className, formPrefix);
  })   
  form_list_renderEditable($form);
}

function form_activateClear(formScreenName) {
  var $screen = $('#' + formScreenName);
  $screen.find('.form-clear').on('click', function(){
    $screen.find('form')[0].reset();
  })
}

function form_loadPracticeForm(loader, form, afterLoad) {
  var thenable = window[loader](form);
  if (thenable) {
    $.when(thenable).then(afterLoad);
  } else {
    afterLoad();
  }
}
 
function form_getFormKey(className, formId) {
  return className+'-id'+ formId;
}

function form_appForms_setForm(className, formId) {
  return app_forms[className+'-id'+ formId];
}

function form_addForm(className, data, formSelector) {
  app_templateClassName = className;
  var formKey = form_getFormKey(className, data.id);
  app_forms[formKey] = {className: className, id: data.id, data: data};
  $(formSelector).attr('data-instance-id', data.id);
  $(formSelector).attr('data-form-key', formKey);
}

function form_enablePrint(formPrefix) {
  var formName = formPrefix + '-form';
  var $form = form_getFormElement(formName);
  var buttonId = formPrefix + '-print-btn';
  var $printButton = $form.find('#' + buttonId);
  if (!$printButton.length) {
    $printButton = $form.closest('.panel').find('#' + buttonId);
  }
  if ($printButton.length) {
    $printButton.off('click').on('click', function(e){ form_printForm(); })
  }
}

function form_addPrintButtonIfPrintable(formPrefix, printTemplate) {
  var templateFormName = formPrefix + '-form';
  if (printTemplate) {
    var $form = form_getFormElement(templateFormName);
    var $panel = $form.closest('.panel')
    var $formFooter = $panel.find('.panel-footer')
    if ($formFooter.length == 0 || $formFooter[0].className.includes('print-special')) {
      return 
    }
    var buttonId = formPrefix + '-print-btn'
    var $printButton = $formFooter.find('#' + buttonId)
    if ($printButton.length == 0) {
      var $printButton = $(
        '<button id="'+ buttonId + '" type="button" class="btn btn-primary btn-lg">Print</button>'
      )
      $formFooter.append($printButton)
    }
    $printButton.off('click').on('click', function(e){ form_printForm(); })
  }
}



function form_addTagsToInput($tagElement, list) {
  for(i=0;i<list.length;i++) {
    $tagElement.tagsinput('add', list[i]);
  }
}

function form_specialtyValidator(formName, defaultValidator) {
  var args = [SPECIALTY, 'form', formName, 'validator'].join('_');
  if (window[args]) {
    return window[args];
  } else {
    return window[defaultValidator];
  }
}

function form_specialtyOpenFormCloseDialogArgs(formName, defaultArgs) {
  var args = [SPECIALTY, 'form', formName, 'openFormCloseDialogArgs'].join('_');
  if (window[args]) {
    return window[args];
  } else {
    return defaultArgs;
  }
}

function form_checkForOpenForm(formClassName, formTitle, ifOneOpened, ifNoneOpened) {
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(), 
    patientId: app_patient.id, 
    formClassName:formClassName,
    evalMode: app_evalMode,
    module:app_module 
  });
  app_post("app/checkForOpenForm", jsonData, function(parsedData) {
    app_openFormId = parsedData.id;
    
    // current form instance needs to be closed first
    if (app_openFormId && app_openFormId != null) {
      var defaultArgs =  {
        modalTitle:'Close Current '+formTitle+'?', 
        modalH3:'Close Current '+formTitle+'?',
        modalH4:'Once completed, the '+formTitle+' form will be read-only.',
        cancelButton: 'Cancel',
        okButton: 'Confirm'
      }
      var args = form_specialtyOpenFormCloseDialogArgs(app_form.practiceForm.name, defaultArgs);
      RenderUtil.render('dialog/confirm', args, function(s) { 
        $('#modal-confirm').remove(); 
        $('#modals-placement').append(s);
        $('#modal-confirm').modal('show'); 
        $('#app-modal-confirmation-btn').click(function() { 
          $('#modal-confirm').modal('hide').on('hidden.bs.modal', function (e) {
            ifOneOpened();  
          })
        })   
      })    
    } else {
      ifNoneOpened()
    }    
  })    
}

function form_specialtyCloseFormDialogArgs(formName, defaultArgs) {
  var args = [SPECIALTY, 'form', formName, 'closeFormDialogArgs'].join('_');
  if (window[args]) {
    return window[args];
  } else {
    return defaultArgs
  }
}

function form_closeFormConfirm(e) {
  var formId =  app_form.id;
  var formName = app_form.practiceForm.name;
  var formTitle = app_form.practiceForm.title;
  var formClassName = app_form.practiceForm.className;
  var modalTitle = 'Complete '+formTitle+' Form?'; 
  var modalH3 = 'Complete '+formTitle+' Form?'; 
  var modalH4Text = 'Once completed, the '+formTitle+' form will be read-only.';
  
  if (app_module == PORTAL_MODULE) {
    modalH4Text = 'Your form will be submitted to the practice.';
    modalTitle = 'Submit '+formTitle+' Form?'; 
    modalH3 = 'Submit '+formTitle+' Form?'; 
  }

  var defaultArgs = {
    modalTitle:modalTitle,
    modalH3:modalH3,
    modalH4:modalH4Text,
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };

  var args = form_specialtyCloseFormDialogArgs(formName, defaultArgs);

  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').on('click', function() {  
      $('#modal-confirm').modal('hide').on('hidden.bs.modal', function (e) {
        $('#modal-confirm').remove();
      });
      
      var jsonData = JSON.stringify({ 
        sessionId: app_getSessionId(), 
        id:formId, 
        formClassName:formClassName, 
        patientFormId: app_patientFormId,
        action:FORM_CLOSE
      });
      app_post("app/updateForm", jsonData, function(parsedData) {
        var practiceForm = app_form.practiceForm
        app_form = parsedData.object;
        app_form.practiceForm = practiceForm;       
        var closer =  window[app_form.practiceForm.closer];
        var withCloser = function(onClosed) {
          var thenable;
          if (closer) {
            thenable = closer(); 
          }  
          if (thenable) {
              $.when(thenable).then(function() { onClosed(); } );
          } else {
            onClosed();
          }
        }
        if (app_module == PORTAL_MODULE) {
            app_getPatientForms();
            $("#patient-form-submit-btn").hide();
            $("#patient-form-submitted-notification").show();
            var showCompleted = function() {
              app_displayNotification(formTitle+' Submitted.');
              form_renderClosedForm();
            }
            withCloser(showCompleted);
        } else {
          form_closeInstance();
          var showCompleted = function() {
            app_displayNotification(formTitle+' Completed.');
            form_renderClosedForm();
          }
          withCloser(showCompleted);
        }
      }); 
      
    }); 
  });
}



function form_deleteItemConfirm(name, id, formClassName, callback) {
  var modalTitle = 'Delete '+name+'?'; 
  var modalH3 = 'Delete '+name+'?'; 
  var modalH4Text = '';
  
  var args = {
    modalTitle:modalTitle,
    modalH3:modalH3,
    modalH4:modalH4Text,
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').on('click', function() {  
      $('#modal-confirm').modal('hide').on('hidden.bs.modal', function (e) {
        $('#modal-confirm').remove();
      });
      
      var jsonData = JSON.stringify({ 
        sessionId: app_getSessionId(), 
        id:id, 
        formClassName:formClassName,
        module:app_module
      });
      $.post("app/deleteItem", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        app_displayNotification(name+' Deleted.');
        if (callback) {callback();}
      }); 
      
    }); 
  });
}



function form_formIsOpened() {
  var templateName = form_getCurrentTemplateFormName();
  if (templateName) {
    var $form = form_getFormElement(templateName);
    return $form.length > 0;
  }
}

function form_initHandlers() {
  var formId = app_form.id;
  var formName = app_form.practiceForm.name;
  var formTitle = app_form.practiceForm.title;
  var formClassName = app_form.practiceForm.className;
  app_templateFormPrefix = formName.replaceAll("_","-");
  var templateFormName = formName.replaceAll("_","-") + '-form';
  
  $('#'+templateFormName+' .input-field').removeAttr("readonly");
  $('#'+templateFormName+' .input-field').removeAttr("disabled");
  $('#'+templateFormName+' .selectpicker').removeAttr("disabled");
  $('#'+templateFormName+' .select-group').removeAttr("disabled");
  $('#'+templateFormName+' .input-select').removeAttr("disabled");
  $('#'+templateFormName+' .input-checkbox').removeAttr("disabled");
  $('#'+templateFormName+' .input-checkbox-group').removeAttr("disabled");
  $('#'+templateFormName+' a.btn').removeAttr("disabled");
  $('#'+templateFormName+' .summernote').summernote('enable');
  $('#'+templateFormName+' .selectpicker').selectpicker('refresh');
  $('#'+templateFormName+' .input-field').off('blur').on('blur', function() { form_validateAndUpdateField(this) });
  $('#'+templateFormName+' .input-select').off('change').on('change', function() { form_validateAndUpdateField(this) });
  $('#'+templateFormName+' .input-checkbox').not('.manual').off('click').on('click', function() { form_validateAndUpdateField(this) });
  $('#'+templateFormName+' .input-checkbox-group').off('click').on('click', function() { form_updateField($(this)) });
     
  $('#'+templateFormName+' .new-list-item-btn').show();
  $('#'+templateFormName+'-close-record-btn').show();
  $('#'+templateFormName+'-open-record').hide();
  $('#'+templateFormName+'-close-record-btn').off('click').on('click', function(e){ form_closeFormConfirm(e); });
} 

function form_initRadioButtonGroups(formSections, data, className, formId) {
  $('form').off('click', '.radio-btn-group a').on('click', '.radio-btn-group a', function() {
    var sel = $(this).data('title');
    var tog = $(this).data('toggle');
    $('#'+tog).prop('value', sel);
    $('a[data-toggle="'+tog+'"]').not('[data-title="'+sel+'"]').removeClass('active').addClass('not-active');
    $('a[data-toggle="'+tog+'"][data-title="'+sel+'"]').removeClass('not-active').addClass('active');
    $('a[data-toggle="'+tog+'"][data-title="0"]').removeClass('active').addClass('not-active');
    
    if (sel != "0") {
      $('#'+tog+'-desc').show();
    }
    else {
      $('#'+tog+'-desc').val('');
      $('#'+tog+'-desc').hide();
      form_updateRadioButtonField($(this), 'desc', className, formId)
    }
    // determine if this is part of a list element or not.
    if ($('#'+tog).data('id')) {
      form_updateListItem($('#'+tog));
    }
    else {
      form_updateRadioButtonField($('#'+tog), 'level', className, formId);
    }
  });  
  form_loadRadioButtonGroups(data, formSections, className, formId);
} 
 


function form_getCurrentTemplateFormName() {
  if (app_form) {
    return form_getCurrentTemplateFormPrefix() + '-form';
  } 
}



function form_getCurrentTemplateFormPrefix() {
  return app_form.practiceForm.name.replaceAll("_","-");
}


function form_specialtyFormTemplate(formName, formClassName) {
  var templateFn = [SPECIALTY, 'form', 'specialtyFormTemplate'].join('_');
  var defaultTemplate = 'form/'+SPECIALTY+'/'+formName+'_form';
  var template;
  if (window[templateFn]) {
    template = window[templateFn](formName, formClassName);
  } 
  if (template) {
    return template 
  } else {
    return defaultTemplate;
  }
}

function form_loadCurrentForm(formName, formClassName, formTitle, byPatientId, createIfNull, doReload) {
  
  if (doReload == true) {
    formName = app_form.practiceForm.name;
    formClassName = app_form.practiceForm.className;
    formTitle = app_form.practiceForm.title;
  }
 
  app_templateFormPrefix = formName.replaceAll("_","-");
  app_templateFormName = app_templateFormPrefix + '-form';
  app_templateScreenName = app_templateFormPrefix + '-screen';
  app_templateClassName = formClassName.replace("com.wdeanmedical.","").replaceAll(".","-");
  app_templateFormTitle = formTitle;
 
  var args = { 
    formPrefix: app_templateFormPrefix, 
    templateFormName: app_templateFormName, 
    templateScreenName: app_templateScreenName, 
    templateFormScreen: app_templateScreenName, 
    formTitle: app_templateFormTitle, 
    formClassName: app_templateClassName,
  };
  RenderUtil.render(form_specialtyFormTemplate(formName, formClassName), args, function(s) {
    $('#' + app_templateScreenName).remove();
    $("#patient-chart-item-screen").html(s);
    app_showScreen(app_templateFormTitle, app_patientChartItemCache);
    app_chartItemStack($('#'+app_templateScreenName), true);
    var jsonData = JSON.stringify({ 
      patientId: app_patient.id, 
      byPatientId: byPatientId, 
      createIfNull: createIfNull, 
      sessionId: app_getSessionId(), 
      formClassName: formClassName
    });
    app_post("app/getForm", jsonData, function(parsedData) {
      app_form = parsedData.object;
      form_appFormInit();
    });
  });
}

function form_specialtyRenderClosedForm(form) {
  var renderClosedForm = [SPECIALTY, 'form', 'renderClosedForm'].join('_');
  var render; 
  if (window[renderClosedForm]) {
    render = window[renderClosedForm](form.name);
  }
  if (render != undefined) {
    return render; 
  } else {
    return form.closed;
  }
}

function form_appFormInit(callback) {
  app_formId = app_form.id;
  var initCalled = false;
  var init = function() {
    form_addForm(app_templateClassName , app_form, '#'+ app_templateFormName);
    if (SPECIALTY != POT_SPECIALTY) {
      form_addPrintButtonIfPrintable(app_templateFormPrefix , app_form.practiceForm.printTemplate);  
    } else {
      form_enablePrint(app_templateFormPrefix);
    }
    if (form_specialtyRenderClosedForm(app_form)) {
       form_renderClosedForm();
    } else {
      form_initHandlers();
    }
    if (callback) {
      callback();
    }
  }
  var thenable;
  if (window[app_form.practiceForm.loader]) {
    thenable = window[app_form.practiceForm.loader](app_form);
  }
  if (thenable) {
    $.when(thenable).then(function() { init(); } );
  } else {
    init()
  }
}

function form_loadRadioButtonGroups(data, formSections, className, formId) {
  
  if (!formSections || formSections == null) {
    return;
  }
  
  for (i=0;i<formSections.length;i++) {
    var formSection = formSections[i];
    
    // set parsed JSON object back onto the form section data element so that it can now be locally mutated.
    // NOTE: Needed to comment this out for AC, will probably break others -- why?
    //if(!data.formSectionsParsed) {
      var formSectionData = $.parseJSON(data[formSection]);
      data[formSection] = formSectionData;
      data.formSectionsParsed = true;
    //}
    //else {
    var formSectionData = data[formSection];
    //}
    $.each(formSectionData, function(key, obj) {
      if (obj.level >= 0 || (obj.level && obj.level.length)) {
        $('a[data-toggle="'+formSection+'-'+key+'"][data-title="'+obj.level+'"]').removeClass('not-active').addClass('active');
        $('.desc-text').filter('[data-property="'+className+'.'+formSection+'.'+key+'"]').val(obj.desc);
        $('.desc-text').filter('[data-property="'+className+'.'+formSection+'.'+key+'"]').show();
        if (obj.level == 0) {
          $('.desc-text').filter('[data-property="'+className+'.'+formSection+'.'+key+'"]').hide();
        } 
      }
    });
    $('.desc-text').off('blur').on('blur', function() { form_updateRadioButtonField($(this), 'desc', className, formId) });
  }
} 



function form_newMultipleInstanceForm(formName, formClassName, formTitle, callback) {
  app_templateFormPrefix = formName.replaceAll("_","-");
  app_templateFormName = app_templateFormPrefix + '-form';
  app_templateScreenName = app_templateFormPrefix + '-screen';
  app_templateClassName = formClassName.replace("com.wdeanmedical.","").replaceAll(".","-");
  app_templateFormTitle = formTitle;
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(), 
    patientId: app_patient.id, 
    formClassName:formClassName,
    evalMode: app_evalMode,
    module:app_module 
  });
  form_checkForOpenForm(formClassName, formTitle, function(){
    var jsonData = JSON.stringify({ 
      sessionId: app_getSessionId(), 
      id: app_openFormId, 
      previousId: app_openFormId,
      clinicianId: app_client.id, 
      byPatientId: false, 
      evalMode: app_evalMode,
      createIfNull: true, 
      action:FORM_CLOSE,
      patientId: app_patientId, 
      formClassName: formClassName,
      module:app_module 
    });
    app_post("app/closeAndCreateForm", jsonData, function(parsedData) {
      app_form = parsedData.object;
      form_closeInstance(app_openFormId);
      callback();
    });   
  }, function() {
     var jsonData = JSON.stringify({ 
        patientId: app_patient.id, 
        byPatientId: true, 
        createIfNull: true, 
        sessionId: app_getSessionId(), 
        evalMode: app_evalMode,
        formClassName: formClassName,
        module:app_module 
      });
      app_post("app/addForm", jsonData, function(parsedData) {
        if (parsedData.result == false) {
          dialog_okConfirm("Add Form", parsedData.errorMsg);
        }
        else {
          app_form = parsedData.object;
          callback()
        }  
      });
  })
}

function form_getFormElement(templateFormName) {
  return $('#'+ templateFormName);
}

function form_toPrint(templateFormName, _$form) {
  var $form = _$form || form_getFormElement(templateFormName);
  var $panel = $form.closest('.panel')
  var $cpanel = $panel.clone()
  var $inputs = $panel.find('input[type="text"], .input-checkbox, textarea, select, input[type="checkbox"]')
  var $cinputs = $cpanel.find('input[type="text"], .input-checkbox, textarea, select, input[type="checkbox"]')
  $cinputs.each(function(i){
   var $this = $(this)
   var $existing = $inputs[i]
   var type = $existing.type
   var checked = false
   if (type == "checkbox" || type=="radio") {
     if ($existing.checked == true) {
      checked = true
    } 
   }
   var $newInput 
   var id = $existing.id
   var name = $existing.name
   var klass = $existing.className
   if (type == "textarea") {
    $newInput = $('<textarea name="' + name +  '" id="' + id  +'" class="'+ klass +'">'+  $existing.value + "</textarea>")
   } else {
    if (checked){
      $newInput =  $('<input name="' + name + '" id="' + id + '" type="'+ type + '" class="' + klass +  '" value="'+ $existing.value +'" checked="checked"></input>')
    } else{
      $newInput =  $('<input  id="' + id + '" type="'+ type + '" class="' + klass +  '" value="'+ $existing.value +'"></input>')      
    }
   }
   if (!id) {
    $newInput.removeAttr('id');
   }
    if (!name) {
    $newInput.removeAttr('name');
   }
   $newInput.insertAfter($this)
   $this.remove()
  })
  $cpanel.find('.panel-footer').remove();
  return $cpanel;
}

function form_formatForPrint(templateFormName, $form) {
    var $panel = form_toPrint(templateFormName, $form)
    return '<div id="' + templateFormName + '-print" class="container">' +
                '<img src="' 
                  + DEFAULT_APP_PATH
                  + 'assets/images/practice/' 
                  + PRACTICE 
                  + '/'
                  + app_practiceClientProperties['app.image.practice_logo_med']
                  +'" class="pull-left" height="100">'
                + '<div class="col-sm-12 clear-both">' 
                + $panel[0].innerHTML
                +'</div></div>'
}



function form_printForm() {
  var args = {
    obj:app_form,
    form: app_form,
    formPrefix: app_form.name,
    currentDate: dateFormat(new Date(), 'mm/dd/yyyy'),
    patient:app_patient
  };
  var isOriginalForm = false;

  if (SPECIALTY == POT_SPECIALTY) {
    if (!isOriginalForm && app_form.practiceForm.printLoader) {
      window[app_form.practiceForm.printLoader](app_form);
    } 
  }  
  RenderUtil.render(app_form.practiceForm.printTemplate, args, function(s) {
    var printTemplate = $(s);
    if (SPECIALTY != POT_SPECIALTY){
      isOriginalForm = !app_form.practiceForm.printTemplate.endsWith('_print');
      if (isOriginalForm) {
        var templateFormName = form_getCurrentTemplateFormName();
        printTemplate = form_formatForPrint(templateFormName);
      }
    }
    if (!isOriginalForm && app_form.practiceForm.printLoader) {
      window[app_form.practiceForm.printLoader](printTemplate);
    } 
    if (app_form.practiceForm.printRenderer) {
      var result = window[app_form.practiceForm.printRenderer](printTemplate)
      if (result) {
        printTemplate=result
      }  
    }
    $("#app-print-buffer").html(printTemplate);
    printer_printAppBuffer(app_form.practiceForm.title);
  });
}

function form_removeTableRow(tableId, id) {
  $('#'+tableId+' tr[name="' + id + '"]').remove();
}

function form_renderDisabled($form) {
  $form.find('.input-field').attr("disabled", "disabled");
  $form.find('.list-item-field').attr("disabled", "disabled");
  $form.find('.selectpicker').attr("disabled", "disabled");
  $form.find('.input-select').attr("disabled", "disabled");
  $form.find('.select-group').attr("disabled", "disabled");
  $form.find('.input-checkbox').attr("disabled", "disabled");
  $form.find('.radio-btn-group').attr("disabled", "disabled");
  $form.find('.input-checkbox-group').attr("disabled", "disabled");
  $form.find('a.btn').attr("disabled", true);
  $form.find('.new-list-item-btn, .list-item-delete, .list-item-delete-top, .input-submit').hide();
  $form.find('.summernote').summernote('disable');
}
function form_enablePrintScreen(formPrefix, title){
  var $btn=$('#'+formPrefix+'-print-btn')
  if (!$btn.length) return
  $btn.off('click').on('click', function(e){ 
     var $printable=$(
      form_formatForPrint(formPrefix+'-form')
    )  
     form_renderDisabled($printable)
     $('#app-print-buffer').html($printable)
     printer_printAppBuffer(title)
  })
}
function form_renderClosedForm() {
  var formId = app_form.id;
  var formName = app_form.practiceForm.name;
  var formTitle = app_form.practiceForm.title;
  var formClassName = app_form.practiceForm.className;
  var templateFormName = formName.replaceAll("_","-") + '-form';
  app_templateFormPrefix = formName.replaceAll("_","-");
  form_renderDisabled($('#' + templateFormName));
  
  $('#'+templateFormName+'-close-record-btn').hide();

  if (app_module != PORTAL_MODULE) {
    $('#'+templateFormName+'-open-record').show();
    $('#'+templateFormName+'-reopen-record-btn').off('click').on('click', function(e){ form_reopenFormConfirm(e); });
  }
  if(app_form.practiceForm.listloader) { window[app_form.practiceForm.listloader]();}
}



function form_reopenFormConfirm(e) {
  var formId = app_form.id;
  var formName = app_form.practiceForm.name;
  var formTitle = app_form.practiceForm.title;
  var formClassName = app_form.practiceForm.className;
  
  var confirmReopen = function() {
    var args = {
      modalTitle:'Reopen '+formTitle+' Form?', 
      modalH3:'Reopen '+formTitle+' Form?',
      modalH4:"",
      cancelButton: 'Cancel',
      okButton: 'Confirm'
    };
    RenderUtil.render('dialog/confirm', args, function(s) { 
      $('#modal-confirm').remove(); 
      $('#modals-placement').append(s);
      $('#modal-confirm').modal('show'); 
      $('#app-modal-confirmation-btn').on('click', function() {  
        $('#modal-confirm').modal('hide').on('hidden.bs.modal', function (e) {
          $('#modal-confirm').remove();
        });
        
        var jsonData = JSON.stringify({ 
          sessionId: app_getSessionId(), 
          id:formId, 
          formClassName:formClassName,
          action:FORM_OPEN 
        });
        $.post("app/updateForm", {data:jsonData}, function(data) {
          var parsedData = $.parseJSON(data);
          if (!util_checkSessionResponse(parsedData)) return false;
          app_displayNotification(formTitle+' Reopened.');
          if (app_form.practiceForm.single == true) {
            form_loadCurrentForm(null, null, null, true, false, DO_RELOAD);
          }
          else {
            app_form.closed = false;
            form_reopenInstance(formId, formClassName, formName);
            if (app_form.practiceForm.reopener) { window[app_form.practiceForm.reopener]() };
          }
        }); 
      }); 
    });
  }
  
  
  
  form_checkForOpenForm(formClassName, formTitle, function(){
    var jsonData = JSON.stringify({ 
      sessionId: app_getSessionId(), 
      id: app_openFormId, 
      clinicianId: app_client.id, 
      byPatientId: false, 
      evalMode: app_evalMode,
      createIfNull: true, 
      action:FORM_CLOSE,
      patientId: app_patientId, 
      formClassName: formClassName,
      module:app_module 
    });
    app_post("app/closeForm", jsonData, function(parsedData) {
      form_closeInstance(app_openFormId);
      confirmReopen();
    });   
  }, function(){
    confirmReopen();
  })  
}



function form_updateField($this, callback) {
  var id;
  var isValid = true
  if ($this.parents("#patient-form-guardian-section").length == 1) {
    id = $('#patient-form-guardian-section').attr('data-instance-id');
    
    if (app_patient.guardian != null) {
      if ($("#guardian-first-name").val().length < 1) { 
        //util_showError($('#guardian-first-name'));
        util_debug('Guardian first name is a required field and cannot be set to blank.');
        isValid = false;
      }
      if ($("#guardian-last-name").val().length < 1) { 
        //util_showError($('#guardian-last-name'));
        util_debug('Guardian last name is a required field and cannot be set to blank.');
        isValid = false;
      }
      var guardianEmailValid = util_checkRegexp($.trim($("#guardian-email").val()), util_emailRegexObj);
      if(guardianEmailValid == false) {
        //util_showError($('#guardian-email'), 'invalid email address');
        util_debug('Guardian email is a required field and cannot be set to an invalid value.');
        isValid = false;
      }
      if (isValid == false) {
        return;
      }
    }
  }
  else {
    var $formByClass=$this.closest('.form')
    if ($formByClass.length){
      id=$formByClass.attr('data-instance-id')
    }
    if (_.isEmpty(id)){
     id = $this.closest('form').attr('data-instance-id');
    }
    if (id === undefined || id.length == 0) {
      return;
    }
  }
  
  var updatePropertyValue = undefined;
  var updateProperty = $this.attr('data-property');
  var updatePropertyFieldType = $this.attr('data-field-type');
  
  var updatePropertyValue = $this.is(':checkbox') ? $this.is(':checked') : $this.val(); 
  if($this.attr('data-escape') == 'true') { updatePropertyValue = escape(updatePropertyValue); }
  
  var name = $this.attr('name');
  
  if ($this.hasClass('selectpicker'))  { 
    $this.selectpicker('refresh');
    if ($this.val() != null)  { 
      updatePropertyValue = $this.val()
      if ($this.attr('multiple') !== undefined){
        updatePropertyValue= updatePropertyValue.join(',').replace(/\+/,''); 
     }   
    }
  }
  if ($this.hasClass('input-checkbox-group'))  { updatePropertyValue = $('input[name='+name+']:checked').map(function() {return this.value;}).get().join(','); } 
  var updateExtra = JSON.parse($this.attr('data-update-extra') || "{}");
  var jsonData = JSON.stringify(_.extend({ 
    sessionId: app_getSessionId(),
    module:app_module,
    id: id,
    updateProperty:updateProperty,
    updatePropertyValue:updatePropertyValue,
    updatePropertyFieldType:updatePropertyFieldType
  }, updateExtra));
  app_updateField(jsonData, function(parsedData){
     if (updateProperty == 'pot-entity-form-POTPatientForm.dob') {
        pot_pm_updatePatientFormAge(updatePropertyValue);  
      } else {
        var ageId = $this.data('age-id');
        if (_.isString(ageId)) {
          var dob = util_calculateAgeFromBirthDate($this.val());
          if (!_.isNaN(dob)) { $(ageId).val(dob); }
        }
      }
      form_updateFieldLocal($this);
      if (callback) {
        callback();
      }
  })
}



function form_updateFieldLocal($this) {
  var id = $this.closest('form').attr('data-instance-id');
  var updateProperty = $this.attr('data-property');
  var name = $this.attr('name');
  var updatePropertyValue = $this.is(':checkbox') ? $this.is(':checked') : $this.val();  
  if ($this.hasClass('selectpicker'))  { 
    $this.selectpicker('refresh');
    if ($this.val() != null)  { 
      updatePropertyValue = $this.val()
      if ($this.attr('multiple') !== undefined){
        updatePropertyValue= updatePropertyValue.join(','); 
     }   
    }
  }
  if ($this.hasClass('input-checkbox-group'))  { updatePropertyValue = $('input[name='+name+']:checked').map(function() {return this.value;}).get().join(','); } 
  if (updateProperty) {
    var formEntityPath = updateProperty.split('.'); 
    var className = formEntityPath[0];
    var property = formEntityPath[1];
    var formKey = form_getFormKey(className, id);
    if (app_form) { app_form[property] = updatePropertyValue; }
    if (app_forms[formKey]) {  app_forms[formKey]['data'][property] = updatePropertyValue;}
  }
}



function form_updateFieldManual(id, updateProperty, updatePropertyValue, updatePropertyFieldType) { 
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(),
    module:app_module,
    id: id,
    updateProperty:updateProperty,
    updatePropertyValue:updatePropertyValue,
    updatePropertyFieldType:updatePropertyFieldType
  });
  $.post("app/updateField", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    if (updateProperty == 'pot-entity-form-POTPatientForm.dob') {
      pot_pm_updatePatientFormAge(updatePropertyValue);  
    }
  }); 
}



function form_updateItemPriority(formClassName, id, priority, direction, parentName, parentId, callback) {
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(),
    module:app_module,
    id: id,
    formClassName: formClassName,
    priority: priority,
    parentName: parentName,
    parentId: parentId,
    direction: direction
  });
  $.post("app/updateItemPriority", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    if (callback) {callback();}
  });
}

function form_updateListItem($this) {
  var updater = $this.data('updater');
  if (updater) {
    return window[updater]($this);
  }
  var jsonData = form_field_getUpdateParams($this);
  app_updateField(jsonData, undefined, $this);
}



function form_updateRadioButtonField($this, type, className, formId) {
  var id = $this.closest('form').attr('data-instance-id');
  var formKey = form_getFormKey(className, id);
  var property = $this.data('property');
  var propArray = property.split(".");
  className = propArray[0];
  var formSection = propArray[1];
  var formElement = propArray[2];
  var formSectionData = app_forms[formKey]['data'][formSection];
  formSectionData[formElement][type] = $this.val();
  
  // if type is 'level' and level is 0, clear description in json as well
  /*
  if (type == 'level' && $this.val() == "0") {
    formSectionData[formElement]['desc'] = '';
  }
  */
  app_forms[formKey]['data'][formSection][type] = $this.val(); 
  
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(),
    module:app_module,
    id: app_forms[formKey]['data'].id,
    updateProperty: className+'.'+formSection,
    updatePropertyValue: JSON.stringify(formSectionData),
    updatePropertyFieldType:'text'
  });
  $.post("app/updateField", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
  }); 
}

function form_validateAndUpdateField(element) {
  if (form_validateField($(element))) {
    form_updateField($(element));
  } 
}




