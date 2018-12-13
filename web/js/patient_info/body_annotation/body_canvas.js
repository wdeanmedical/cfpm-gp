function bodyCanvas_showWorkInProgress() {
  bodyCanvas__workInProgressEl().style.display="block";
}

function bodyCanvas_hideWorkInProgress() {
  bodyCanvas__workInProgressEl().style.display="none";
}

function bodyCanvas_hideControls() {
  bodyCanvas__controls.style.display="none";
}

function bodyCanvas_loadCanvas(canvas, savedJson) {
  var filtered = bodyCanvas_filterSavedArrows(savedJson);
  bodyCanvas_loadFromJSON(canvas, filtered.filteredJSON);
  bodyCanvas_addArrows(canvas, filtered.arrows);
}

function bodyCanvas_setBackgroundImage(canvas) {
  canvas.setBackgroundImage('assets/images/body.jpg', canvas.renderAll.bind(canvas));
}

function bodyCanvas___removeEl(el) {
  el.parentNode.removeChild(el);
}
function bodyCanvas___imageFilePath(imageFileName) {
  return "app/getFile?patientId=" + app_patient.id + "&sessionId=" + app_getSessionId() + "&path=" + imageFileName; 
}
function bodyCanvas_showStatic(savedJson) {
  var canvas = new fabric.StaticCanvas('canvas');
  if (savedJson) {
    bodyCanvas_loadCanvas(canvas, savedJson);
  } else {
    bodyCanvas_setBackgroundImage(canvas);
  }
  bodyCanvas_initVars(canvas);
  bodyCanvas_showWorkInProgress(); 
  bodyCanvas_hideControls();
}

function bodyCanvas_showFinalized(imageFileName) {
   var bodyCanvasFinishedImagecEl= document.getElementById('canvas-finished-image');
   bodyCanvasFinishedImagecEl.src = bodyCanvas___imageFilePath(imageFileName);
   bodyCanvas_hideWorkInProgress();
   document.getElementById('canvas-static').style.display="block";
   bodyCanvas___removeEl(document.getElementById('canvas-wip'));
   bodyCanvas__finalized = true;
}

function bodyCanvas_activate(canvasSerializer, savedJson) {
  var canvas = new fabric.Canvas('canvas', {
      isDrawingMode: false
  });
  
  bodyCanvas__serializer = canvasSerializer;
  canvas.freeDrawingBrush = new fabric['PencilBrush'](canvas);
  canvas.freeDrawingBrush.width = canvas.freeDrawingBrush.width * 2;
  if (savedJson) {
    bodyCanvas_loadCanvas(canvas, savedJson);
  } else {
    bodyCanvas_setBackgroundImage(canvas);
  }
  
  bodyCanvas_initVars(canvas);
  bodyCanvas_showWorkInProgress();
  bodyCanvas_onPencilMode(canvas);
  bodyCanvas_onEraserMode(canvas);
  bodyCanvas_onAddTextBox(canvas);
  bodyCanvas_onAddArrow(canvas);
  bodyCanvas_onSelectMode(canvas);
  bodyCanvas_onClearCanvas(canvas);
  bodyCanvas_onFinish(canvas);
  bodyCanvas_handleDoubleClick(canvas);
  bodyCanvas_handleMouseEvents(canvas);
  bodyCanvas_autoSave(canvas);
  bodyCanvas_onAfterRender(canvas);
  bodyCanvas_onUndo(canvas);
  bodyCanvas_activateToolTip();
  bodyCanvas_onToggleTooltip(canvas);

  bodyCanvas__canvas = canvas;
}

