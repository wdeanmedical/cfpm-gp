function bodyCanvas_toJSON(canvas) {
  return JSON.stringify(canvas.toJSON());
}

function bodyCanvas_saveLocalStorage(canvas, currentState) {
  var canvasData = currentState || canvasToJSON(canvas);
  localStorage.setItem("bodyCanvas", canvasData);
}

function bodyCanvas___patientInfoId() {
  return bodyCanvas__workInProgressEl().closest('form').attr('data-instance-id');
}
function bodyCanvas_saveServerSide(canvas, currentState) {
  var canvasData = currentState || bodyCanvas_toJSON(canvas);
  bodyCanvas__canvas = canvas;
  var jsonData = {    
    patientInfoId: bodyCanvas__patientInfoId,
    sessionId: bodyCanvas___sessionId, 
    module:app_module,
    bodyAnnotation: {
      canvasJSON: canvasData
    }  
  };
  app_post("patient/saveBodyAnnotation", jsonData);
}


function bodyCanvas_saveBodyAnnotationImage(canvas, callback) {
  var imageData = bodyCanvas_toPngUrl(canvas);
  
  var jsonData = {    
    patientId: (app_module == PORTAL_MODULE ? app_client.id : app_patientId), 
    patientInfoId: bodyCanvas__patientInfoId,
    sessionId: app_getSessionId(), 
    module:app_module,
    bodyAnnotation: {
      byte64ImageData: imageData
    }  
  };
  return app_post("patientFile/saveBodyAnnotation", jsonData, function(parsedData) {
     if (parsedData.result === false && parsedData.errorMsg){
      app_displayNotification(parsedData.errorMsg)
    } else{
      if (callback) callback(parsedData.bodyAnnotation);
    }
  })
}

function bodyCanvas_getCanvasDataServerSide(callback) {
  var jsonData = {    
    patientInfoId: bodyCanvas__patientInfoId,
    sessionId: app_getSessionId(), 
    module:app_module
  };
  app_post("patient/getBodyAnnotation", jsonData, function(parsedData) {
    callback(parsedData.bodyAnnotation || {});
  })
}  


function bodyCanvas_getCanvasDataLocalStorage(callback) {
  callback({bodyCanvasJSON: localStorage.getItem("bodyCanvas")});
}

function bodyCanvas_toPngUrl(canvas) {
  return canvas.toDataURL({format: 'png'});
} 

function bodyCanvas_loadFromJSON(canvas, json) {
  canvas.loadFromJSON(json, canvas.renderAll.bind(canvas));
}

function bodyCanvas_getSerializer(options) {
  var _options = options || {};
  var type = _options.type || "local";
  function serializer(type) {
    return {
      serialize: type
    }
  }
  switch(type) {
    case "local":
      return serializer(bodyCanvas_saveLocalStorage)
    default: 
      return serializer(bodyCanvas_saveServerSide)
  }
}