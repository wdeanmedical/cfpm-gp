function load_scripts_getFilePaths(files, base) {
  var _files = files || [];
  var toLoad = _files.map(function(file){
    return base + "/" + file + ".js";
  });
  return toLoad;
}