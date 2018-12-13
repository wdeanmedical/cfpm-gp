function gp_load_exam(form, callback=function(){}) {
  exam_load_tx_codes(form.txCodes, form.encounterId, function(){
    exam_load_dx_codes(form.dxCodes, form.encounterId, function() {
      exam_load_lab(form.lab || {}, callback);
    });
  });
}

function exam_load_lab(lab, callback) {
  var labPrefix = "lab";
  var args = {
    formName: labPrefix + "-form",
    formPrefix: labPrefix,
    formKey: "gp-entity-Lab",
    id: lab.id
  }
  RenderUtil.render('component/gp/encounter/lab', args, function(s) { 
    var $template = $(s);
    $('#exam-form #lab-placeholder').replaceWith($template);
    form_loadForm(lab, labPrefix);
    form_renderEditable($template);
    callback();
  })
}

function exam_load_tx_codes(list, encounterId, callback) {
  var args = {
    items: list, 
    parentId: encounterId,
    parentIdName: "encounterId",
    resource: "tx-code",
    itemClass: "gp-entity-TxCode"
  }
  RenderUtil.render('component/gp/encounter/exam/tx_codes', args, function(s) { 
    $('#exam-form #tx-code-list').replaceWith(s);
    exam_renderCPTModifiers();
    var $list = $('#exam-form #tx-code-list');
    form_list_populateList($list, list);
    callback();
  })
}

function exam_load_dx_codes(list, encounterId, callback) {
  var args = {
    items: list, 
    parentId: encounterId,
    parentIdName: "encounterId",
    resource: "dx-code",
    itemClass: "gp-entity-DxCode"
  }
  RenderUtil.render('component/gp/encounter/exam/dx_codes', args, function(s) { 
    $('#exam-form #dx-code-list').replaceWith(s);
    form_list_populateList($('#exam-form #dx-code-list'), list);
    callback();
  })
}

function exam_updateTxCode($input) {
  app_post("patient/updateTxCode", form_field_getUpdateParams($input));    
}


function exam_updateDxCode($input) {
  app_post("patient/updateDxCode", form_field_getUpdateParams($input));    
}

function exam_renderCPTModifiers() {
 RenderUtil.render('component/basic_select_options', {options:app_cptModifiers, collection:'app_cptModifiers', choose: true}, function(s) {
    $('.cpt-modifier').html(s);

  });
}

function exam__updateDxTx(ev, datum){
  var $field = $(ev.currentTarget)
  $field.data('value', datum.id)
  form_updateListItem($field)
}

function exam_initDxTypeAheads(formName) {
  var $form = $('#' + formName);
  var dxCode = new Bloodhound({
    datumTokenizer: function(icd9List) { 
      return Bloodhound.tokenizers.whitespace(icd9List.value);
    },
    queryTokenizer: Bloodhound.tokenizers.whitespace,
    limit: 100,
    remote: {
      url: 'app/searchICD9?sessionId='+app_getSessionId()+'&searchText=%QUERY',
      filter: function(response) {      
        return response.list;
      }
    }
  });
  dxCode.initialize();
  $form.find('.icd9-typeahead').typeahead( { 
    hint: true, 
    highlight: true, 
    limit: 10, 
    minLength: 3 
  }, { 
    name: 'icd9List', 
    displayKey: function(icd9List) {
    return icd9List.code + " " + icd9List.codeText;        
   },
    source: dxCode.ttAdapter() 
  }).on('typeahead:selected', function(ev, datum) {
     exam__updateDxTx(ev, datum)
  }); 
} 

function exam_initTxTypeAheads(formName) {
  var $form = $('#' + formName);
  var cpt = new Bloodhound({
    datumTokenizer: function(cptList) { 
      return Bloodhound.tokenizers.whitespace(cptList.value);
    },
    queryTokenizer: Bloodhound.tokenizers.whitespace,
    limit: 100,
    remote: {
      url: 'app/searchCPT?sessionId='+app_getSessionId()+'&searchText=%QUERY',
      filter: function(response) {      
         return response.list;
       }
    }
  });
  cpt.initialize()
  $form.find('.cpt-typeahead').typeahead( { 
      hint: true, 
      highlight: true, 
      minLength: 3
  }, { 
    name: 'cptList', 
    displayKey: function(cptList) {
      return cptList.code + " " + cptList.description;        
    },
    source: cpt.ttAdapter() 
  }).on('typeahead:selected', function(ev, datum) {
    exam__updateDxTx(ev, datum)
  }); 
} 

function exam_setup_newDxBtn(encounterId, formName) {
   var args = {
    parentId: encounterId,
    parentIdName: "encounterId",
    entityClass: "gp-entity-DxCode",
    resourceName: "Dx"
  }
  form_list_setupList(formName + ' #dx-codes', 'component/list/new', args, function(list, callback){
    exam_load_dx_codes(list, encounterId, callback);
    exam_initDxTypeAheads(formName);
  })
}

function exam_setup_newTxBtn(encounterId, formName) {
   var args = {
    parentId: encounterId,
    parentIdName: "encounterId",
    entityClass: "gp-entity-TxCode",
    resourceName: "Tx"
  }
  form_list_setupList(formName + ' #tx-codes', 'component/list/new', args, function(list, callback){
    exam_load_tx_codes(list, encounterId, callback);
    exam_initTxTypeAheads(formName);
  })
}

function gp_editable_exam_form_rendered(form) {
  var formName = 'exam-form';
  exam_setup_newDxBtn(form.encounterId, formName);
  exam_setup_newTxBtn(form.encounterId, formName);
  exam_initTxTypeAheads(formName);
  exam_initDxTypeAheads(formName);
}