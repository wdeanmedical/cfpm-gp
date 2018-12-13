var RenderUtil = (function(){
  return {
    templates: {},

    // Get and Render template by name from hash of preloaded templates
    // and fetch from server if not yet loaded into cache.    
    render: function (templateName, templateValues, callback) {
      var that = this;
      if (templateName in this.templates == false) {
        util_debug('Fetching template: ' + templateName);
        return $.get("template/" + templateName + '.html', function(data) {
         that.templates[templateName] = data;
         return that.render(templateName, templateValues, callback);
        });
      } else {
        util_debug('Loading preloaded template: ' + templateName);
        var s = $.tmpl(this.templates[templateName], templateValues);
        return defer(function() { callback(s) });
      }
    }
  };
})()