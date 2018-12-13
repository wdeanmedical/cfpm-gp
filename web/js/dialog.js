function dialog_okConfirm(h4Text, bodyText) {
  var args = { 
    modalH4: h4Text,
    modalBodyText: bodyText,   
    okButton: 'Ok',
    cancelButton: null
  }
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove();
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
  });
}

function dialog(args) {

  RenderUtil.render('dialog/dialog', args, function(s) { 
    $('#modal-dialog').remove();
    $('#modals-placement').append(s);
    $('#modal-dialog').modal('show'); 
  });
}
