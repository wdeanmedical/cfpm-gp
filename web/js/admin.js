
function pm_getUsersList() {
  var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module });
  $.post("admin/getUsers", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    if (!util_checkSessionResponse(parsedData)) return false;
    app_users = parsedData.list;
    RenderUtil.render('component/user_admin_data_table', 
     {items:app_users, 
      title:'Users', 
      tableName:'user-admin-users-list', 
      clickable:true
      }, function(s) {
      $('#user-admin-users-list').html(s);
      $('#user-admin-users-list-title').html("Users");
      $('.user-admin-user-details').click( function(e){ pm_handleUserDetails(e); });
      $('.user-admin-change-user-status').click( function(e){ pm_handleChangeUserStatus(e); });
      $('.user-admin-delete-user').click( function(e){ pm_handleDeleteUser(e); });
    });
  });
}



function pm_handleChangeUserStatus(e) {
  var id = e.currentTarget.name;
  var userToChange = app_findListItemById(app_users, id, 'id');
  var userFullName = util_buildFullName(userToChange.firstName, userToChange.middleName, userToChange.lastName);
  var activationMode = userToChange.status == USER_ACTIVE ? "deactivate"  : "activate";
  
  RenderUtil.render('dialog/change_user_status', {activationMode:activationMode, userFullName: userFullName}, function(s) { 
    $('#modal-admin-change-user-status').remove(); 
    $('#modals-placement').html(s);
    $('#modal-admin-change-user-status').modal('show'); 
    $('#app-modal-confirmation-btn').click(function(){  
      var method = activationMode == "deactivate" ? "deactivateUser"  : "activateUser";
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module, id: id });
      $.post("admin/"+method, {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        $('#modal-admin-change-user-status').modal('hide'); 
        app_displayNotification('User '+userFullName+' '+activationMode+'d.');
        pm_getUsersList();
      }); 
    });
  });
}
    


function pm_handleDeleteUser(e) {
  var id = e.currentTarget.name;
  var userToChange = app_findListItemById(app_users, id, 'id');
  var userFullName = util_buildFullName(userToChange.firstName, userToChange.middleName, userToChange.lastName);
  var args = {
    modalTitle:"Delete User", 
    modalH3:"Delete User "+userFullName+"?",
    modalH4:"Once deleted, the user will remain in the system but not be visible to the application.",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').html(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function(){  
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), module:app_module, id: id });
      $.post("admin/deleteUser", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        $('#modal-confirm').modal('hide'); 
        app_displayNotification('User '+userFullName+' deleted.');
        pm_getUsersList();
      }); 
    });
  });
}



function pm_handleUserDetails(e) {
  var id = e.currentTarget.name;
  var userToChange = app_findListItemById(app_users, id, 'id');
  
  RenderUtil.render('form/user_form', {formMode:'edit'}, function(s) { 
    $('#modal-admin-user-form').remove(); 
    $('#modals-placement').html(s);
    $('#modal-admin-user-form').modal('show'); 
    $('#admin-user-form-title').html("View/Edit User");
    $('#user-form').attr('data-instance-id', id);
    
    RenderUtil.render('component/basic_select_options', {options:app_roles, collection:'app_roles', choose:true}, function(s) { $("#user-form-role").html(s); });
    RenderUtil.render('component/basic_select_options', {options:app_credentials, collection:'app_credentials', choose:true}, function(s) { $("#user-form-credential").html(s); });
    
    $('#user-form-first-name').val(userToChange.firstName);
    $('#user-form-middle-name').val(userToChange.middleName);
    $('#user-form-last-name').val(userToChange.lastName);
    $('#user-form-username').val(userToChange.username);
    $('#user-form-group-name').val(userToChange.groupName);
    $('#user-form-practice-name').val(userToChange.practiceName);
    $('#user-form-role').val(userToChange.roleId);
    $('#user-form-is-therapist').val(userToChange.userType == CLINICAL ? '1' : '0');
    $('#user-form-credential').val(userToChange.credentialId);
    $('#user-form-primary-phone').mask("(999) 999-9999");
    $('#user-form-primary-phone').val(userToChange.primaryPhone);
    $('#user-form-secondary-phone').mask("(999) 999-9999");
    $('#user-form-secondary-phone').val(userToChange.secondaryPhone);
    $('#user-form-email').val(userToChange.email);
    $('#user-form-pager').mask("(999) 999-9999");
    $('#user-form-pager').val(userToChange.pager);
    $('#user-form-password').val(userToChange.password);
    
    $('#admin-user-form-cancel').on('click', function() {
      return admin_validateUserForm();
    });

    $('#user-form .input-field').off('blur').on('blur', function() { pm_updateSavedUser($(this)) });
    $('#user-form .input-select').off('change').on('change', function() { pm_updateSavedUser($(this)) });
  });
}



function pm_newUserForm() {
  RenderUtil.render('form/user_form', {formMode:'add'}, function(s) { 
    $('#modal-admin-user-form').remove(); 
    $('#modals-placement').html(s);
    $('#modal-admin-user-form').modal('show'); 
    $('#modal-admin-user-form .form-control-unsaved').css({display: "block"});
    $('#modal-admin-user-form .form-control-saved').css({display: "none"});
    $('#admin-user-form-title').html("Add User");
    $('#user-form-primary-phone').mask("(999) 999-9999");
    $('#user-form-secondary-phone').mask("(999) 999-9999");
    $('#user-form-pager').mask("(999) 999-9999");
    RenderUtil.render('component/basic_select_options', {options:app_roles, collection:'app_roles', choose:true}, function(s) { $("#user-form-role").html(s); });
    RenderUtil.render('component/basic_select_options', {options:app_credentials, collection:'app_credentials', choose:true}, function(s) { $("#user-form-credential").html(s); });
    $('#admin-user-form-save').click(function() { pm_saveNewUser() });
  
  });
} 



function app_renderAdminChartHeader() {
  RenderUtil.render('component/admin_chart_header', {}, function(s) {
    $('#app-chart-header').html(s);
  });
}



function pm_renderUserAdminScreen() {
 RenderUtil.render('screen/user_admin_screen', {}, function(s) {
  $("#admin-item-screen").html(s);
  app_showScreen('User Admin', app_adminScreenItemCache);
  app_chartItemStack($('#user-admin-screen'), true);
  $('#user-admin-new-user').click(function() { pm_newUserForm() }); 
  pm_getUsersList();
 });  
}

function admin_validateUserForm() {
  var isValid = true;
  util_clearErrors();  

  if($("#user-form-first-name").val().length < 1) {util_showError($('#user-form-first-name')); isValid = false; }
  if($("#user-form-last-name").val().length < 1) {util_showError($('#user-form-last-name')); isValid = false; }
  if($("#user-form-username").val().length < 1) {util_showError($('#user-form-username')); isValid = false; }
  if($("#user-form-role").val().length < 1) {util_showError($('#user-form-role')); isValid = false; }
  if($("#user-form-credential").val().length < 1) {util_showError($('#user-form-credential')); isValid = false; }
  if($("#user-form-is-therapist").val().length < 1) {util_showError($('#user-form-is-therapist')); isValid = false; }
  if (util_checkRegexp($.trim($('#user-form-primary-phone').val()), util_phoneRegexObj) == false) { util_showError($('#user-form-primary-phone'), 'must be valid phone'); isValid = false; }

  if ($.trim($("#user-form-password").val()) != PASSWORD_PLACEHOLDER) {  
    if($.trim($("#user-form-password").val()).length < 1) { util_showError($('#user-form-password')); isValid = false; }
    if ($.trim($("#user-form-password").val()).length > 0) {
      if($.trim($("#user-form-password").val()).length < 6 ) {
        util_showError('#user-form-password', 'must be at least 6 chararcters long'); 
        isValid = false; 
      }
      if(util_checkPassword($.trim($("#user-form-password").val())) == false) { 
        util_showError($('#user-form-password'), 'must contain a lowercase, uppercase, digit, and special character'); 
        isValid = false; 
      }
      var $passwordConfirm = $("#user-form-password-confirm");
      if ($passwordConfirm.length) {
        if($.trim($("#user-form-password").val()) != $.trim($passwordConfirm.val())) { 
          util_showError($('#user-form-password'), 'passwords must match'); 
          isValid = false; 
        }
      }  
    }
  }
  
  if($.trim($("#user-form-email").val()).length < 1) { util_showError($('#user-form-email')); isValid = false; }
  var emailValid = util_checkRegexp($.trim($("#user-form-email").val()), util_emailRegexObj);
  if(emailValid == false) { util_showError($('#user-form-email'), 'invalid email address'); isValid = false; }
  var $emailConfirm = $("#user-form-email-confirm");
  if ($emailConfirm.length) {
    if($.trim($("#user-form-email").val()) != $.trim($emailConfirm.val())) { 
      util_showError($('#user-form-email'), 'email addresses must match'); 
      isValid = false; 
    }
  }  
  return isValid;
}

function pm_saveNewUser() {
  var isValid = admin_validateUserForm();

  if (!isValid) {return;}
  
  var jsonData = JSON.stringify({ 
    module:app_module,
    firstName: $('#user-form-first-name').val(), 
    middleName: $('#user-form-middle-name').val(), 
    lastName: $('#user-form-last-name').val(), 
    username: $('#user-form-username').val(), 
    roleId: $('#user-form-role').val(), 
    userType: $('#user-form-is-therapist').val() == '1' ? CLINICAL : OFFICE, 
    credentialId: $('#user-form-credential').val(), 
    primaryPhone: $('#user-form-primary-phone').val(), 
    secondaryPhone: $('#user-form-secondary-phone').val(), 
    email: $('#user-form-email').val(),
    password: $('#user-form-password').val(),
    pager: $('#user-form-pager').val(),
    sessionId: app_getSessionId()
  });

  $.post("admin/saveNewUser", {data:jsonData}, function(data) {
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
    $('#modal-admin-user-form').modal('hide');
    app_displayNotification('New user '+ parsedData.firstName + ' ' + parsedData.lastName + ' created.');
    pm_getUsersList();
  });
}




function pm_updateSavedUser($this) {
  var id = $this.closest('form').attr('data-instance-id');
  var updateProperty = $this.attr('data-property').split('.')[1];
  var updatePropertyValue = $this.val(); 
  
  var isValid = true;
  util_clearErrors();  
  console.log('clear errors!!!!!')
  if(updateProperty == 'firstName' && $("#user-form-first-name").val().length < 1) {console.log('whoops errr first name'); util_showError($('#user-form-first-name')); isValid = false; }
  if(updateProperty == 'lastName' && $("#user-form-last-name").val().length < 1) {util_showError($('#user-form-last-name')); isValid = false; }
  if(updateProperty == 'username' && $("#user-form-username").val().length < 1) {util_showError($('#user-form-username')); isValid = false; }
  if(updateProperty == 'roleId' && $("#user-form-role").val().length < 1) {util_showError($('#user-form-role')); isValid = false; }
  if(updateProperty == 'credentialId' && $("#user-form-credential").val().length < 1) {util_showError($('#user-form-credential')); isValid = false; }
  if(updateProperty == 'userType' && $("#user-form-is-therapist").val().length < 1) {util_showError($('#user-form-is-therapist')); isValid = false; }
  if(updateProperty == 'primaryPhone' && util_checkRegexp($.trim($('#user-form-primary-phone').val()), util_phoneRegexObj) == false) {util_showError($('#user-form-primary-phone'), 'must be valid phone'); isValid = false; }

  if (updateProperty == 'password' && $.trim($("#user-form-password").val()) != PASSWORD_PLACEHOLDER) {  
    if($.trim($("#user-form-password").val()).length < 1) { util_showError($('#user-form-password')); isValid = false; }
    if ($.trim($("#user-form-password").val()).length > 0) {
      if($.trim($("#user-form-password").val()).length < 6) { 
        util_showError('#user-form-password', 'must be at least 6 chararcters long'); 
        isValid = false; 
      }
      if(util_checkPassword($.trim($("#user-form-password").val())) == false) { 
        util_showError($('#user-form-password'), 'must contain a lowercase, uppercase, digit, and special character'); 
        isValid = false; 
      }
    }
  }
  
  if(updateProperty == 'email' && $.trim($("#user-form-email").val()).length < 1) { util_showError($('#user-form-email')); isValid = false; }
  var emailValid = util_checkRegexp($.trim($("#user-form-email").val()), util_emailRegexObj);
  if(updateProperty == 'email' && emailValid == false) { util_showError($('#user-form-email'), 'invalid email address'); isValid = false; }
  
  if (!isValid) {return;}

  var jsonData = JSON.stringify({ 
    sessionId: app_getSessionId(), 
    module:app_module,
    updateProperty:updateProperty,
    updatePropertyValue:updatePropertyValue,
    id: id
  });
  $.post("admin/updateUser", {data:jsonData}, function(data) {
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
    else {
      pm_updateUserInList(id, updateProperty, updatePropertyValue);
      pm_getUsersList();
    }
  }); 
}



function pm_updateUserInList(id, property, value) {
  for (i=0;i< app_users.length;i++) {
    if (app_users[i].id == id) {
      app_users[i][property] = value;
    }
  }
}



function app_viewAdminScreen() {
  app_viewStack('admin-screen', DO_SCROLL);
  RenderUtil.render('screen/'+SPECIALTY+'/'+app_module+'/admin_screen', {}, function(s) {
    $("#admin-screen").html(s);
    app_renderAdminChartHeader();
    $('#admin-user-link').click(function(){ pm_renderUserAdminScreen(); });
    $('#admin-goal-bank-link').click(function(){ pot_pm_renderGoalBankScreen(); });
    $('#admin-claims-link').click(function(){ bh_pm_renderClaimsScreen(); });
    $('#admin-invoicing-link').click(function(){ pm_renderInvoicingAdminScreen(); });
  }); 
}
