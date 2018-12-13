function bodyAnnotation_loadScreen(data, readonly) {
  var params = _.extend({portal:false}, data || {})
  return RenderUtil.render('component/body_annotation', params, function(screen) { 
    var $bodyAnnotationScreenEl = $('#body-annotation-screen');
    $bodyAnnotationScreenEl.html(screen);
    bodyCanvas__patientInfoId = $bodyAnnotationScreenEl.closest('form').attr('data-instance-id');
      bodyCanvas_getCanvasDataServerSide(function(canvasData){
      bodyCanvas__finalized = canvasData.finalized;
      bodyCanvas__readonly = readonly;
      if (bodyCanvas__finalized === true) {
        bodyCanvas_showFinalized(canvasData.imageFileName);
      } else if (readonly === true) {
        bodyCanvas_showStatic(canvasData.canvasJSON);
      } else {
        var canvasSerializer = bodyCanvas_getSerializer({type: "server"});
        bodyCanvas_activate(canvasSerializer, canvasData.canvasJSON);
      }   
    })
  })
}  

(function(){
  var BODY_ANNOTATION_FILES = [
  "actions", 
  "arrow", 
  "auto_save",
  "body_canvas",
  "events",
  "serialization",
  "tooltip",
  "vars"
  ].map(function(file){
    return "js/patient_info/body_annotation/" + file + ".js";
  });
  head.load(BODY_ANNOTATION_FILES.concat("js/lib/fabric.js"));
})()
