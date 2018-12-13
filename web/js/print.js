function printer_printAppBuffer(title) {
  var buffer = $("#app-print-buffer")[0].innerHTML.replace(/src="assets/g, 'src="' + DEFAULT_APP_PATH + "assets");
  $("#app-print-buffer").html(buffer);  
  $("#app-print-buffer").printThis({
    debug: false,        
    importCSS: false,    
    importStyle: false, 
    printContainer: true, 
    loadCSS: DEFAULT_APP_PATH + "css/all.css", 
    pageTitle: title,  
    removeInline: false,  
    printDelay: 333,  
    header: null,    
    formValues: true
  });
}

function printer_printTemplate(template, title) {
  $("#app-print-buffer").html(template);  
  printer_printAppBuffer(title);
}