var VALIDATORS = [
  "cancellation_policy", "client_contact", "client_rights", 
  "consent", "notice_privacy", "emergency_info", 
  "patient_info", "release", "texting_waiver"
]

var FORM_LOADERS = [
 "cancellation_policy", "client_rights", "client_contact", 
 "consent",  "notice_privacy", "release",
 "self_rating", "texting_waiver", "emergency_info", 
]

var FORMS;

function loader__getForms() {
  return load_scripts_getFilePaths(SPECIALTY_FORMS, "js/form/" + SPECIALTY);
}

function loader__getFormLoaders() {
  return load_scripts_getFilePaths(_.intersection(FORMS, FORM_LOADERS), "js/form/loaders");
}


function loader_getValidators() {
  return load_scripts_getFilePaths(_.intersection(FORMS, VALIDATORS), "js/form/validators");
}

function loader_getModels() {
  return load_scripts_getFilePaths(MODELS, "js/model");
}

function loader_loadSpecialtyScripts(callback) {
  head.load(["js/load_scripts/" + SPECIALTY + "_load_scripts.js"], function(){
    FORMS =  _.chain(SPECIALTY_FORMS).concat(SPECIALTY_INTAKE_FORMS).flatten().compact().value();
    var _scripts = loader__getForms()
      .concat(loader__getFormLoaders())
      .concat(loader_getModels())
      .concat(loader_getValidators()).concat([]);
    var scripts = []
     _.each(_scripts, function(path){
      entry = {}
      entry[path.replace(/\//g, '-').replace('.js', '')] = path;
      scripts.push(entry);
    });
    if (scripts.length) { 
      head.load(scripts, callback);
    } else {
      callback();
    }
  });
}