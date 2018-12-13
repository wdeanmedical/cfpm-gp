var MimeTypes = (function() {
  var genericIcon = "file-o"
  var typeKey = function(type) {
    var split = type.split('/')
    var first = split[0]
    if (first == "application") {
      return type
    } else {
      return first
    }
  }
   var FA_ICON_LIST = {
    'image' : 'fa-file-image-o',
    'audio' : 'fa-file-audio-o',
    'video' : 'fa-file-video-o',
    'application/pdf' : 'fa-file-pdf-o',
    'text/plain' : 'fa-file-text-o',
    'text/html' : 'fa-file-code-o',
    'application/zip' : 'fa-file-archive-o',
    'application/vnd.ms-excel': 'fa-file-excel-o '
  }
  return {
    faIcon: function(type) {
      var icon = FA_ICON_LIST[typeKey(type)];
      if (_.isUndefined(icon)) {
        icon = genericIcon
      }
      return icon
    }
  }
})()