function form_list_populateList($list, list) {
  var $field, id, property, val;
  var idList = util_idList(list);
  var $wrapper = $list;
  if (!util_stringContains($wrapper.attr('class'), 'list-item-wrapper')) {
    $wrapper = $list.find('.list-item-wrapper');
  }
  var itemPrefix = $wrapper.data('itemPrefix');
  $wrapper.find('.list-item-field').each(function(_, field) {
    $field = $(field);
    id = $field.data('id');
    property = form__field_getProperty($field, itemPrefix);
    val = idList[id][property];
    form_field_loadField($field, val);
  })
}

function form_list_load(itemTemplate, itemClass, resource, list, readonly, listSelector, callback = undefined, options = {}) {
  RenderUtil.render(itemTemplate, _.extend({}, options, {itemClass: itemClass, resource: resource, list: list, readonly:readonly}), function(s) { 
    var $list = $(listSelector);
    $list.html(s);
    var $wrapper = $list.find('.list-item-wrapper');
    form_list_populateList($wrapper, list);
    if (readonly) {
     form_renderDisabled($list);
    } else {
     form_list_renderEditable(null, $wrapper);
     form_mask_activateInputMask(null, $wrapper);
    }
    if (callback) callback();
  })
}


function form_list_setupDelete($form, loader) {
  $form.find('.list-item-delete').show();
  $form.on('click', '.list-item-delete', function() { form_list_deleteItem($(this), loader); }) 
}

function form_list_setupNew($form, newHtml, loader) {
  $form.find('#new-resource-btn-placeholder').replaceWith(newHtml);
  $form.find('.new-list-sub-item-btn').off().on('click', function() { form_list_addSubItem($(this), loader); })
}

function form_list_renderEditable($form, $wrapper) {
  var itemClass, itemPrefix;
  var $items = $wrapper;
  if ($form) {
    $items = $form.find('.list-item-wrapper');
  }
  $items.each(function(_, item){
    $item = $(item);
    itemClass = $item.data('itemClass');
    itemPrefix = $item.data('itemPrefix');
    $item.find('.list-item-field').each(function(_, field){
      $field = $(field);
      form_field_makeUpdateable($(field), itemClass, itemPrefix, {listItem:true});
    })    
  })   
}

function form_list_setupList(formName, newResourceTemplate, newResourceTemplateArgs, listLoader, wrapper) {
  return RenderUtil.render(newResourceTemplate, newResourceTemplateArgs, function(newHtml){
    var $form = $(wrapper); 
    if (formName) {
      $form = $('#' + formName);
    }  
    var showDeleteAfterUpdate = function(list, callback) {
      listLoader(list, function(){
        $form.find('.list-item-delete').show();
        if (callback) callback();
      });
    }
    form_list_setupNew($form, newHtml, showDeleteAfterUpdate);
    form_list_setupDelete($form, showDeleteAfterUpdate);
  })
}

function form_list_appFormPatientId() {
  return (app_form.patientId || app_form.data.patientId);
}
function form_list_addSubItem($addControl, loader) {
  var className = $addControl.data('entityClass');
  var parentId = $addControl.data('parentId');
  var parentIdName = $addControl.data('parentIdName');
  if (!parentId) {
    parentId = form_list_appFormPatientId(),
    parentIdName = "patientId";
  }
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(), 
    formClassName: className,
    parentId: parentId, 
    parentIdName: parentIdName
  });
  app_post("app/addSubItem", jsonData, function(parsedData) { 
    form_list_getSubItems(className, parentId, parentIdName, function(list){
      loader(list, function(){
        var $wrapper = $('.list-item-wrapper[data-item-class="'+ className + '"]'); 
        form_list_renderEditable(null, $wrapper);
      });
    })
  })
}

function form_list_getSubItems(className, parentId, parentIdName, loader) {
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(), 
    formClassName: className,
    parentId: parentId,
    parentIdName: parentIdName
  });
  app_post("app/getSubItems", jsonData, function(parsedData) { 
    loader(parsedData.list);
  })
}

function form_list_deleteItem($deleteControl, loader) {
  var $wrapper = $deleteControl.closest('.list-item-wrapper');
  var itemClass = $wrapper.data('itemClass');
  var parentId = $wrapper.data('parentId');
  var parentIdName = $wrapper.data('parentIdName');
  if (!parentId) {
    parentId = form_list_appFormPatientId(),
    parentIdName = "patientId";
  }
  var itemId = $deleteControl.data('id');
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(), 
    formClassName: itemClass,
    id: itemId,
  });
  app_post("app/deleteItem", jsonData, function(parsedData) { 
    form_list_getSubItems(itemClass, parentId, parentIdName, function(list){
      loader(list);
    })
  })
}

function pm_addBehaviorFunction($this) {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), fbabipId:app_fbabipId});
  $.post("patient/addBehaviorFunction", {data:jsonData}, function(data) { 
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    RenderUtil.render('component/'+SPECIALTY+'/behavior_function', {list: [parsedData.object], instanceName:'bh-entity-form-BehaviorFunction', readonly:NOT_CLOSED}, function(s) { 
      $('#behavior-functions').append(s); 
      $('.list-item-field').on('blur', function(e) { form_updateListItem($(this)); });
      $('.list-item-select').on('change', function(e) { form_updateListItem($(this)); });
      $('.behavior-function-delete-control').on('click', function() { pm_deleteBehaviorFunctionConfirm($(this)); });
    });
  }); 
}



function app_addBillingTicketEntry($this) {
  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(), 
    patientId: app_patient.id,
    clinicianId: app_client.id,
    formClassName: app_getEntityClassName('form.BillingTicketEntry'), 
    parentId:app_billingTicket.id}
  );
  $.post("app/addBillingTicketEntry", {data:jsonData}, function(data) { 
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    var args = {
      list: [parsedData.object],
      instanceName: SPECIALTY + '-entity-form-BillingTicketEntry', 
      readonly:NOT_CLOSED,
      clientName: app_patientFullName
    }
    RenderUtil.render('component/pm/billing_ticket_entry',  args, function(s) { 
      $('#billing-ticket-entries').append(s); 
      $('#ticket-date-'+parsedData.object.id).mask("99/99/9999");
      $('.list-item-field').on('blur', function(e) { form_updateListItem($(this)); });
      $('.entry-delete-control').on('click', function() { app_deleteBillingTicketEntryConfirm($(this)); });
    });
  }); 
}

function pm_addInterventionPlan($this) {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), fbabipId:app_fbabipId});
  $.post("patient/addInterventionPlan", {data:jsonData}, function(data) { 
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    RenderUtil.render('component/'+SPECIALTY+'/intervention_plan', {list: [parsedData.object], instanceName:'bh-entity-form-InterventionPlan', readonly:NOT_CLOSED}, function(s) { 
      $('#intervention-plans').append(s); 
      $('.list-item-field').on('blur', function(e) { form_updateListItem($(this)); });
      $('.list-item-select').on('change', function(e) { form_updateListItem($(this)); });
      $('.intervention-plan-delete-control').on('click', function() { pm_deleteInterventionPlanConfirm($(this)); });
    });
  }); 
}

function app_addPresentingProblem($this) {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), patientId:app_patient.id});
  $.post("patient/addPresentingProblem", {data:jsonData}, function(data) { 
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    RenderUtil.render('component/presenting_problem', {list: [parsedData.object], instanceName:'bh-entity-form-PresentingProblem'}, function(s) { 
      $('#presenting-problems').append(s); 
      $('.list-item-field').on('blur', function(e) { form_updateListItem($(this)); });
      $('.presenting-problem-delete-control').on('click', function() { app_deletePresentingProblem($(this)); });
    });
  }); 
}

function pm_addProblemAnalysis($this) {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), fbabipId:app_fbabipId});
  $.post("patient/addProblemAnalysis", {data:jsonData}, function(data) { 
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    RenderUtil.render('component/'+SPECIALTY+'/problem_analysis', {list: [parsedData.object], instanceName:'bh-entity-form-ProblemAnalysis', readonly:NOT_CLOSED}, function(s) { 
      $('#problem-analyses').append(s); 
      $('.list-item-field').on('blur', function(e) { form_updateListItem($(this)); });
      $('.list-item-select').on('change', function(e) { form_updateListItem($(this)); });
      $('.problem-analysis-delete-control').on('click', function() { pm_deleteProblemAnalysisConfirm($(this)); });
    });
  }); 
}



function pm_addTargetBehavior($this) {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), fbabipId:app_fbabipId});
  $.post("patient/addTargetBehavior", {data:jsonData}, function(data) { 
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    RenderUtil.render('component/'+SPECIALTY+'/target_behavior', {list: [parsedData.object], instanceName:'bh-entity-form-TargetBehavior', readonly:NOT_CLOSED}, function(s) { 
      $('#target-behaviors').append(s); 
      $('.list-item-field').on('blur', function(e) { form_updateListItem($(this)); });
      $('.list-item-select').on('change', function(e) { form_updateListItem($(this)); });
      $('.target-behavior-delete-control').on('click', function() { pm_deleteTargetBehaviorConfirm($(this)); });
    });
  }); 
}



function pm_deleteBehaviorFunctionConfirm($this) {
  var args = {
    modalTitle:"Delete Behavior Function?", 
    modalH3:"Delete Behavior Function?",
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
    
      var id = $this.attr('data-id');
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), id:id});
      $.post("patient/deleteBehaviorFunction", {data:jsonData}, function(data) { 
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        $('#behavior-function-'+id).remove();
      });  
    }); 
  }); 
}



function app_deleteBillingTicketEntryConfirm($this) {
  var args = {
    modalTitle:"Delete Billing Ticket Entry?", 
    modalH3:"Delete Billing Ticket Entry?",
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
    
      var id = $this.attr('data-id');
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), id:id, formClassName: app_getEntityClassName('form.BillingTicketEntry'), 
 });
      $.post("app/deleteBillingTicketEntry", {data:jsonData}, function(data) { 
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        $('#billing-ticket-entry-'+id).remove();
      });  
    }); 
  }); 
}

function pm_deleteInterventionPlanConfirm($this) {
  var args = {
    modalTitle:"Delete Intervention Plan?", 
    modalH3:"Delete Intervention Plan?",
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
    
      var id = $this.attr('data-id');
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), id:id});
      $.post("patient/deleteInterventionPlan", {data:jsonData}, function(data) { 
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        $('#intervention-plan-'+id).remove();
      });  
    }); 
  }); 
}



function app_deleteListItemById(list, id, key) {
  for (i=0;i<list.length;i++) {
    if (list[i][key] == id) {
      list.splice(i, 1);
    }
  }
}

function app_deletePresentingProblem($this) {
  var id = $this.attr('data-id');
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), id:id});
  $.post("patient/deletePresentingProblem", {data:jsonData}, function(data) { 
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    $('#presenting-problem-'+id).remove();
  });  
}



function pm_deleteProblemAnalysisConfirm($this) {
  var args = {
    modalTitle:"Delete Problem Analysis?", 
    modalH3:"Delete Problem Analysis?",
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
    
      var id = $this.attr('data-id');
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), id:id});
      $.post("patient/deleteProblemAnalysis", {data:jsonData}, function(data) { 
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        $('#problem-analysis-'+id).remove();
      });  
    }); 
  }); 
}



function pm_deleteTargetBehaviorConfirm($this) {
  var args = {
    modalTitle:"Delete Target Behavior?", 
    modalH3:"Delete Target Behavior?",
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
    
      var id = $this.attr('data-id');
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), id:id});
      $.post("patient/deleteTargetBehavior", {data:jsonData}, function(data) { 
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        $('#target-behavior-'+id).remove();
      });  
    }); 
  }); 
}

function app_findListItemById(list, id, key) {
  for (i=0;i<list.length;i++) {
    if (list[i][key] == id) {
      return list[i];
    }
  }
}

function pm_renderBehaviorFunctions(items, closed) {
  RenderUtil.render('component/'+SPECIALTY+'/behavior_function', {list: items, instanceName:'bh-entity-form-BehaviorFunction', readonly:closed}, function(s) { 
    $('#behavior-functions').html(s); 
    $('.list-item-field').on('blur', function(e) { form_updateListItem($(this)); });
    $('.behavior-function-delete-control').click(function() { pm_deleteBehaviorFunctionConfirm($(this)); });
    $('#new-behavior-function-btn').click(function() { pm_addBehaviorFunction($(this)); });
  });
}

function app_renderBillingTicketEntries(entries, closed) {
  var args = {
      list: entries, 
      instanceName: SPECIALTY + '-entity-form-BillingTicketEntry', 
      readonly:closed,
      clientName: app_patientFullName
  }
  RenderUtil.render('component/pm/billing_ticket_entry', args, function(s) { 
    $('#billing-ticket-entries').html(s); 
    $('.list-item-field').on('blur', function(e) { form_updateListItem($(this)); });
    $('.entry-delete-control').click(function() { app_deleteBillingTicketEntryConfirm($(this)); });
    $('#new-entry-btn').click(function() { app_addBillingTicketEntry($(this)); });
  });
}

function pm_renderInterventionPlans(items, closed) {
  RenderUtil.render('component/'+SPECIALTY+'/intervention_plan', {list: items, instanceName:'bh-entity-form-InterventionPlan', readonly:closed}, function(s) { 
    $('#intervention-plans').html(s); 
    $('.list-item-field').on('blur', function(e) { form_updateListItem($(this)); });
    $('.intervention-plan-delete-control').click(function() { pm_deleteInterventionPlanConfirm($(this)); });
    $('#new-intervention-plan-btn').click(function() { pm_addInterventionPlan($(this)); });
  });
}

function dom_app_renderMedications(medications) {
  RenderUtil.render('component/dom/dom_patient_medication', {list: medications, instanceName:'entity-form-PatientMedication'}, function(s) { 
    $('#medications').html(s); 
    $('.list-item-field').on('blur', function(e) { form_updateListItem($(this)); });
    $('.medication-delete-control').on('click', function() { app_deleteMedication($(this)); });
    $('#new-medication-btn').click(function() { app_addMedication($(this)); });
  });
}

function pm_renderPresentingProblems(items, closed) {
  RenderUtil.render('component/presenting_problem', {list: items, instanceName:'entity-form-PresentingProblem', readonly:closed}, function(s) { 
    $('#presenting-problems').html(s); 
    $('.list-item-field').on('blur', function(e) { form_updateListItem($(this)); });
    $('.presenting-problem-delete-control').click(function() { pm_deletePresentingProblemConfirm($(this)); });
    $('#new-presenting-problem-btn').click(function() { pm_addPresentingProblem($(this)); });
  });
}

function pm_renderProblemAnalyses(items, closed) {
  RenderUtil.render('component/'+SPECIALTY+'/problem_analysis', {list: items, instanceName:'bh-entity-form-ProblemAnalysis', readonly:closed}, function(s) { 
    $('#problem-analyses').html(s); 
    $('.list-item-field').on('blur', function(e) { form_updateListItem($(this)); });
    $('.problem-analysis-delete-control').click(function() { pm_deleteProblemAnalysisConfirm($(this)); });
    $('#new-problem-analysis-btn').click(function() { pm_addProblemAnalysis($(this)); });
  });
}

function pm_renderTargetBehaviors(items, closed) {
  RenderUtil.render('component/'+SPECIALTY+'/target_behavior', {list: items, instanceName:'bh-entity-form-TargetBehavior', readonly:closed}, function(s) { 
    $('#target-behaviors').html(s); 
    $('.list-item-field').on('blur', function(e) { form_updateListItem($(this)); });
    $('.target-behavior-delete-control').click(function() { pm_deleteTargetBehaviorConfirm($(this)); });
    $('#new-target-behavior-btn').click(function() { pm_addTargetBehavior($(this)); });
  });
}
