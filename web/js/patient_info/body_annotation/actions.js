function bodyCanvas_addTextBox(canvas) {
  var textSample = new fabric.Textbox('Add some text', {
    fontSize: 20,
    fontFamily: 'helvetica',
    top: bodyCanvas___canvasHeight/2,
    left: bodyCanvas___canvasWidth/2,
    fontWeight: '',
    originX: 'center',
    originY: 'center',
    width: 300,
    selectable: true,
    hasRotatingPoint: true,
    centerTransform: true
  });
  canvas.add(textSample);
}   

function bodyCanvas_setIsDrawingMode(canvas, mode) {
  canvas.isDrawingMode = mode;
  if (canvas.isDrawingMode) {
    canvas.freeDrawingCursor = "url(assets/images/body_annotation/pencil.png) 4 24, auto";
  } 
}

function bodyCanvas_setIsErasingMode(canvas, mode) {
  bodyCanvas___isErasingMode = mode; 
  if (bodyCanvas___isErasingMode) {
    canvas.defaultCursor = "url(assets/images/body_annotation/eraser.png) 4 24, auto";
  } else {
    canvas.defaultCursor = bodyCanvas___defaultCursor;
  }
}

function bodyCanvas_setActiveObjects(canvas) {
  bodyCanvas___activeObjects = canvas.getActiveObjects();
}

function bodyCanvas_handleMouseEvents(canvas) {
  canvas.on('mouse:down', function(event) {
    if (bodyCanvas___isErasingMode) {
      bodyCanvas_setActiveObjects(canvas);
    }
  })
}  

function bodyCanvas_removeActiveObjects(canvas) {
  if (bodyCanvas___isErasingMode) {
    if (bodyCanvas___activeObjects) {
      canvas.discardActiveObject();
      bodyCanvas___activeObjects.forEach(function(object){
        object.off("mousedblclick");
        canvas.remove(object); 
      })
    }
  }  
}  

function bodyCanvas_handleDoubleClick(canvas) {
  canvas.on('mouse:dblclick', function(event) {
    bodyCanvas_removeActiveObjects(canvas);
  })  
}