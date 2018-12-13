var bodyCanvas__AUTO_SAVE_INTERVAL = 10000,
  bodyCanvas__MAX_SAVED_STATES = 10;

function bodyCanvas___updateSavedStates(newState) {
  bodyCanvas___savedStates.push(newState);
  if (bodyCanvas___savedStates.length > bodyCanvas__MAX_SAVED_STATES) {
    bodyCanvas___savedStates.shift();
  }
}

function bodyCanvas_autoSave(canvas) {
  var counter = 0;
  var savedState;
  var currentState;
  var intervalId;  
  intervalId = setInterval(function(){ 
    if (bodyCanvas__workInProgressEl()) {
      if (!bodyCanvas___isUndoing) {
        currentState = bodyCanvas_toJSON(canvas); 
        if (currentState != savedState) {
          util_debug('auto saving body canvas');
          savedState = currentState;
          bodyCanvas___updateSavedStates(currentState);
          bodyCanvas__serializer.serialize(canvas, currentState);
        }
      }   
    } else {
      util_debug('clearing autosave');
      clearInterval(intervalId);
    }
  }, bodyCanvas__AUTO_SAVE_INTERVAL);
}
