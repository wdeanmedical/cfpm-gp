function TemplateInfo(form) {
  var formName = form.name;
  var className = form.className;
  var prefix = formName.replaceAll("_","-");
  return {
     prefix:prefix, 
     formName: prefix + '-form',
     className: className.replace("com.wdeanmedical.","").replaceAll(".","-")
  }
}