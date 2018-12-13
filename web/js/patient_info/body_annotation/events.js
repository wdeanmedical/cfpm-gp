function bodyCanvas_onPencilMode(canvas) {
  bodyCanvas__pencilModeEl.onclick = function() {
    bodyCanvas_setIsErasingMode(canvas, false);
    bodyCanvas_setIsDrawingMode(canvas, true);
  }
}  

function bodyCanvas_onSelectMode(canvas) {
  bodyCanvas__selectModeEl.onclick = function() {
    bodyCanvas_setIsErasingMode(canvas, false);
    bodyCanvas_setIsDrawingMode(canvas, false);
    canvas.freeDrawingCursor = 'default';
  }
}

function bodyCanvas_onClearCanvas(canvas) {
  bodyCanvas__clearEl.onclick = function() { 
    if (confirm("Are you sure, this will clear the canvas?")) {
      canvas.clear(); 
      bodyCanvas_setBackgroundImage(canvas); 
    }
  }
}   

function bodyCanvas_onAfterRender(canvas) {
  canvas.on('after:render', function() {
    canvas.contextContainer.strokeStyle = '#555';
    canvas.forEachObject(function(obj) {
      if (obj.isType('textbox')) {
        var bound = obj.getBoundingRect();
        canvas.contextContainer.strokeRect(
          bound.left - 5,
          bound.top - 5,
          bound.width + 15,
          bound.height + 15
        );
      } 
    }) 
  })   
}

function bodyCanvas_onEraserMode(canvas) {
  bodyCanvas__eraserModeEl.onclick = function() {
    bodyCanvas_setIsDrawingMode(canvas, false);
    bodyCanvas_setIsErasingMode(canvas, true);
    bodyCanvas_setActiveObjects(canvas);
    bodyCanvas_removeActiveObjects(canvas);
  }
}  

function bodyCanvas_onAddTextBox(canvas) {
  bodyCanvas__addTextBoxEl.onclick = function() {
    bodyCanvas_setIsErasingMode(canvas, false);
    bodyCanvas_setIsDrawingMode(canvas, false);
    bodyCanvas_addTextBox(canvas);
  }
}  

function bodyCanvas_onAddArrow(canvas) {
  bodyCanvas__addArrowEl.onclick = function() {
    bodyCanvas_setIsErasingMode(canvas, false);
    bodyCanvas_setIsDrawingMode(canvas, false);
    bodyCanvas_addArrow(canvas); 
  }
} 

function bodyCanvas_onUndo(canvas) {
  bodyCanvas__undoEl.onclick=function() {
    bodyCanvas___isUndoing = true;
    var previousState = bodyCanvas___savedStates.pop();
    if (previousState) {
      bodyCanvas_loadCanvas(canvas, previousState);
      bodyCanvas__serializer.serialize(canvas, previousState);
    } else {
      util_debug('undo: no previous state to restore');
    } 
  }
  function bodyCanvas___preventDefault(e) {
    e.preventDefault();
    return false;
  }
  bodyCanvas__workInProgressEl().addEventListener("click", 
   function(e) {
      var nodeName = e.target.nodeName.toLowerCase();
      if (e.target && (nodeName == "a" || nodeName == "i" )) {
        bodyCanvas___isUndoing = false;
      }  
      return bodyCanvas___preventDefault(e);
    }
  )
} 

function bodyCanvas_onAddRectangle(canvas) {
  bodyCanvas__addRectangleEl.onclick = function() {
    bodyCanvas_setIsErasingMode(canvas, false);
    bodyCanvas_setIsDrawingMode(canvas, false);
    bodyCanvas_addRectangleToCanvas(canvas); 
  }
} 

function bodyCanvas_onToggleTooltip(canvas) {
  bodyCanvas__toggleTooltipEl.onclick = function() {
    bodyCanvas__canvasHelpEl.innerHTML = "";
    var mode = bodyCanvas___toolTipEnabled ? "enable" : "disable";
    bodyCanvas___toolTipEnabled = !bodyCanvas___toolTipEnabled;
    bodyCanvas_enableDisableTooltip(mode);
  }
  bodyCanvas__toggleTooltipEl.click();
} 

function bodyCanvas__saveAndShowFinalized(canvas, callback) {
  return bodyCanvas_saveBodyAnnotationImage(canvas, function(annotation){
    if (annotation.finalized === true) {
      bodyCanvas_showFinalized(annotation.imageFileName);
    } 
    if (callback) {
      return callback();
    }
  });
}

function bodyCanvas___finishConfirm(canvas) {
  var args = {
    modalTitle: 'Finish annotation?',
    modalH3: "You will not be able to continue with the annotation afterwards.",
    modalH4: "",
    cancelButton: 'Cancel',
    okButton: 'Confirm'
  };
  RenderUtil.render('dialog/confirm', args, function(s) { 
    $('#modal-confirm').remove(); 
    $('#modals-placement').append(s);
    $('#modal-confirm').modal('show'); 
    $('#app-modal-confirmation-btn').click(function() {  
      bodyCanvas__saveAndShowFinalized(canvas);
    });   
  });
}


function bodyCanvas_onFinish(canvas) {
  bodyCanvas__finishEl.onclick = function() {
    bodyCanvas___finishConfirm(canvas);  
  }
} 