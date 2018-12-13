var PatientFiles = {
  toProvider: {},
  fromProvider: {},
  deleted: {},
  isPatient: function(file) {
    return file.clientType == "patient"
  },
  isProvider: function(file) {
    return file.clientType != "patient"
  },
  clear: function() {
    this.fromProvider = {};
    this.deleted = {};
    this.toProvider = {};
  }
}


function patientFile_viewPatientFiles() {
  app_viewStack('patient-files-screen', DO_SCROLL);
  PatientFiles.loadFiles();
}



function patientFile_loadScreen(data) {
  var params = _.extend({portal:false}, data || {})
  RenderUtil.render('screen/patient_file_screen', params, function(screen) { 
    $('#patient-files-screen').html(screen)
    initPatientFile()
  });


   function setFileTypeToIcon(file) {
      var icon = {icon: MimeTypes.faIcon(file.type)}
      file.type = _.template('<span class=file-type-icon><i class="fa <%= icon %>"></i></span>')(icon)
    }

  function addNewPatientFile(patientFile, $modal) {
    var sessionData = { sessionId: app_getSessionId()}
    var jsonData = JSON.stringify(_.extend({}, sessionData, patientFile));
    $.post("patientFile/addNewFile", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      if (!util_checkSessionResponse(parsedData)) return false;
      var patientFile = $.parseJSON(data);
      setFileTypeToIcon(patientFile);
      var section = "fromProvider";
      var tab = "#from-provider-folder";
      if (PatientFiles.isPatient(patientFile)) {
        section = "toProvider";
        tab = "#to-provider-folder"
      }  
      PatientFiles[section][patientFile.id] = patientFile;
      PatientFiles.update[section]();
      $(tab).trigger('click')
      $modal.modal('hide');
    });
  }
  
  
  

  function deletePatientFile(fileId, section, callback) {
    var done = function() {
      var list = PatientFiles[section]
      PatientFiles.deleted[fileId] = PatientFiles[section][fileId]
      PatientFiles[section] = _.omit(list, fileId.toString())
      PatientFiles.update[section]()
      PatientFiles.update.deleted()
      if (callback) { callback()}
    }
    var jsonData = JSON.stringify({ sessionId: app_getSessionId(), id:fileId});
    $.post("patientFile/deleteFile", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      if (!util_checkSessionResponse(parsedData)) return false;
      done()
    });
  } 

  
  
  
  function downloadPatientFile(fileId) {
    var jsonData = encodeURIComponent(
      JSON.stringify({ sessionId: app_getSessionId(), id:fileId})
    );
    var url = "patientFile/getFile?data="+ jsonData;
    window.open(url);
  }
  
  


  function setupPatientFileUpload($modal) {
    var $upload = $modal.find('#new-file-upload')
    var $progressBar = $modal.find('#new-file-upload-progress .progress-bar')
    $modal.find('#new-file-upload').click(function(){ 
      $progressBar.css('width', '0');
    }).trigger('click');
    var $fileName = $modal.find('#file-name');
    var $path = $modal.find('#path');
    var $name = $modal.find('#name');
    var $type = $modal.find('#type');
    var $addBtn = $modal.find('#btn-add');
    var $uploadedList = $modal.find('#uploaded-list');
    var $description = $modal.find('#description');
    var enableAddBtn = function() {
      if ($path.val().length > 0 && $description.val().length > 0) {
        $addBtn.removeAttr('disabled');
      }
    }
    $description.blur(function(){
      if ($(this).val().length > 0) {
        enableAddBtn();
      }
    })
    $addBtn.click(function(){
      var newFile = {
        description: $description.val(),
        path: $path.val(),
        name: $name.val(),
        type: $type.val(),
        patientId: app_patient.id
      }
      addNewPatientFile(newFile, $modal)
    })
    uploader = new qq.FileUploader({
     element: $upload.get(0),
     action: 'patientFile/uploadFile?patientId=' + app_patient.id +'&sessionId=' + app_getSessionId(),
     debug: true,
     multiple:false,
     uploadButtonText: "Choose a file...",
     sizeLimit: 1048576,   
     onProgress: function(id, fileName, loaded, total) {
        var progress = parseInt(loaded / total * 100, 10);
        $progressBar.css('width', progress + '%');
     },
    onSubmit:function() {
      $progressBar.parent().show();
      $addBtn.attr('disabled', 'disabled');
      $uploadedList.hide()
    },
     onComplete: function(id, fileName, responseJSON) {
       $progressBar.css('width', '100%');
       var response = parsedData = $.parseJSON(responseJSON);
       $fileName.text(response.name);
       $path.val(response.path);
       $name.val(response.name);
       $type.val(response.type);
       enableAddBtn();
       $uploadedList.show();
     }
    }); 
  }
  
  function confirmDelete(callback) {
     var args = {
      modalTitle:"Delete File", 
      modalH3:"Delete File?",
      modalH4: "",
      cancelButton: 'Cancel',
      okButton: 'Confirm'
    };
    RenderUtil.render('dialog/confirm', args, function(s) { 
      $('#modal-confirm').remove(); 
      $('#modals-placement').append(s);
      $('#modal-confirm').modal('show'); 
      $('#app-modal-confirmation-btn').click(function() {  
        callback();
        $('#modal-confirm').modal('hide'); 
        app_displayNotification(' Deleted');
      }) 
    })
  }

  function initPatientFile() {
    $('#new-patient-file').click(function() { 
      RenderUtil.render('form/new_patient_file', {}, function(s) { 
        $('#modals-placement').html(s);
        var $modal = $('#modal-new-patient-file')
        $modal.modal('show');
        setupPatientFileUpload($modal)
      });
    })
    function currentClientIsPatientAndOwner(file) {
      if (!_.isNull(file) && PatientFiles.isPatient(file)) {
        return app_client.id == file.patientId 
      }
    }
  
    function loadPatientFiles() {
      var jsonData = JSON.stringify({ sessionId: app_getSessionId(), patientId:app_patient.id});
      $.post("patientFile/getFiles", {data:jsonData}, function(data) {
        var parsedData = $.parseJSON(data);
        if (!util_checkSessionResponse(parsedData)) return false;
        PatientFiles.clear();
        _.each(parsedData.list, function(file){
          setFileTypeToIcon(file)
          if (file.deleted) {
            PatientFiles.deleted[file.id] = file
          } else if (PatientFiles.isPatient(file)) {
            PatientFiles.toProvider[file.id] = file
          } else {
            PatientFiles.fromProvider[file.id] = file
          }
        })
        var tab = "#to-provider-folder"
        if (currentClientIsProvider()) {
          tab = "#from-provider-folder"
        }
        $(tab).trigger('click')
        PatientFiles.updateAll()
      });
    }
    PatientFiles.loadFiles = loadPatientFiles;
    var GUARDIAN_PATIENT = ["patient", "guardian"];
    function currentClientIsProvider() {
      return !_.contains(GUARDIAN_PATIENT, app_clientType); 
    }
    var $fromProviderTable = $('#files-from-provider .table-responsive');
    var $toProviderTable = $('#files-to-provider .table-responsive');
    var $trashTable = $('#files-deleted .table-responsive');
    var $fileAction = $('#patient-files-screen #file-action');
    var $download = $fileAction.find('.download');
    var $delete = $fileAction.find('.delete');
    var $fileActions = $fileAction.find('a');
    var $tab = $('#patient-files-screen .nav a');
    var $selectedRow = null;
    var $currentTab = null;
    var $rows = $('#patient-files-screen .clickable-table-row');
    var disableFileActions = function() {
      $fileActions.attr('disabled', 'disabled'); 
    }
    $delete.click(function(){
      if (!_.isNull($selectedRow)) {
        var id = $selectedRow.data('row-key'); 
        var file = PatientFiles.toProvider[id];
        if (currentClientIsProvider() || currentClientIsPatientAndOwner(file)) {
          var callback = function() {
            deletePatientFile(id, $currentTab.data('folder'), disableFileActions);
          }  
          confirmDelete(callback);
        } 
      }  
    })
    $download.click(function(){
      var id = $selectedRow.data('row-key');
      downloadPatientFile(id);
    })
    $tab.click(function(){
      disableFileActions();
      if (!_.isNull($selectedRow)) {
        $selectedRow.removeClass('table-row-highlight');
        $selectedRow = null;
      }
      $currentTab = $(this);
    })
    var $download = $fileAction.find('a.download')
    var $delete = $fileAction.find('a.delete')
    function updateSection(title, tableName, data, $element) { 
      RenderUtil.render('component/simple_data_table', 
       {items:_.values(data), 
        title:title, 
        tableName:tableName, 
        clickable:true, 
        columns:[
          {title:'Name', field:'name', type:'simple'},
          {title:'Description', field:'description', type:'simple'},
          {title:'Type', field:'type', type:'simple'}
        ]}, function (template) {
        var $template = $(template);  
        $element.html($template);

        $template.find('.clickable-table-row').click( function(e){
          if ($currentTab.data('folder') != "trash") {
            $download.removeAttr('disabled')
            if (currentClientIsProvider()) {
              $delete.removeAttr('disabled')
            }
            else if ($currentTab.data('folder') == "toProvider") {
              $delete.removeAttr('disabled')
            }
          }  
          var $row = $(this)   
          $row.addClass('table-row-highlight').siblings().removeClass('table-row-highlight');
          $selectedRow = $row
        });
      });
    }
    PatientFiles.update = {}
    function updatePatientFilesFromProvider() {
      updateSection('Files From Provider', 
        'files-from-provider', 
        PatientFiles.fromProvider,
        $fromProviderTable
      )
    }
    PatientFiles.update.fromProvider = updatePatientFilesFromProvider
    function updatePatientFilesToProvider() {
      updateSection('Files To Provider', 
        'files-to-provider', 
        PatientFiles.toProvider,
        $toProviderTable
      )
    }
    PatientFiles.update.toProvider = updatePatientFilesToProvider
    function updatePatientFilesDeleted() {
      updateSection('Deleted Files', 
        'files-deleted', 
        PatientFiles.deleted,
        $trashTable
      )
    }
    PatientFiles.update.deleted = updatePatientFilesDeleted
    PatientFiles.updateAll = function() {
      updatePatientFilesFromProvider();
      updatePatientFilesToProvider();  
      updatePatientFilesDeleted();
    }  
  }
  
  
}
