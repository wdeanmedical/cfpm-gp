var bodyCanvas__clearEl ,
bodyCanvas__readonly,
bodyCanvas__canvasHelpEl,
bodyCanvas__toggleTooltipEl,
bodyCanvas__pencilModeEl,
bodyCanvas__finalized = false,
bodyCanvas__eraserModeEl,
bodyCanvas___defaultCursor,
bodyCanvas__selectModeEl,
bodyCanvas__addArrowEl,
bodyCanvas__addRectangleEl,
bodyCanvas__undoEl,
bodyCanvas__canvasWorkspaceEl,
bodyCanvas__canvasFinishedEl,
bodyCanvas__addTextBoxEl,
bodyCanvas__finishEl,
bodyCanvas__canvas,
bodyCanvas___isErasingMode,
bodyCanvas___activeObjects,
bodyCanvas___canvasWidth,
bodyCanvas___canvasHeight,
bodyCanvas___isUndoing,
bodyCanvas__serializer,
bodyCanvas___toolTipEnabled,
bodyCanvas__controls,
bodyCanvas__patientInfoId,
bodyCanvas___savedStates,
bodyCanvas___sessionId;

function bodyCanvas__workInProgressEl() {
  return document.getElementById('canvas-wip');
}

function bodyCanvas_initVars(canvas) {
  bodyCanvas__clearEl = document.getElementById('canvas-clear');
  bodyCanvas__controls = document.getElementById('canvas-controls');
  bodyCanvas__canvasHelpEl = document.getElementById('canvas-help');
  bodyCanvas__toggleTooltipEl = document.getElementById('canvas-toggle-tooltip');
  bodyCanvas__pencilModeEl = document.getElementById('canvas-pencil-mode');
  bodyCanvas__eraserModeEl = document.getElementById('canvas-eraser-mode');
  bodyCanvas___defaultCursor = canvas.defaultCursor;
  bodyCanvas__selectModeEl = document.getElementById('canvas-select-mode');
  bodyCanvas__addArrowEl = document.getElementById('canvas-add-arrow');
  bodyCanvas__addRectangleEl = document.getElementById('canvas-add-rectangle');
  bodyCanvas__undoEl = document.getElementById('canvas-undo');
  bodyCanvas__canvasWorkspaceEl = document.getElementById('canvas-wip');
  bodyCanvas__canvasFinishedEl = document.getElementById('canvas-finished');
  bodyCanvas__addTextBoxEl = document.getElementById('canvas-add-textbox');
  bodyCanvas__finishEl = document.getElementById('canvas-finish');
  bodyCanvas___isErasingMode = false;
  bodyCanvas___activeObjects;
  bodyCanvas___canvasWidth = canvas.getWidth();
  bodyCanvas___canvasHeight = canvas.getHeight();
  bodyCanvas___isUndoing = false;
  bodyCanvas___toolTipEnabled = true;
  bodyCanvas___savedStates = [];
  bodyCanvas___sessionId = app_getSessionId();
}