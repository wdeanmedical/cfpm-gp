function defer(callback) {
  var dfd = jQuery.Deferred();
  if (callback) dfd.then(callback);
  dfd.resolve();
  return dfd.promise();
}