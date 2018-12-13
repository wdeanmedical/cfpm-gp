function bodyCanvas_activateToolTip() {
  $("[data-toggle='tooltip']").on('show.bs.tooltip', function(event){
    var tooltip = $(event.currentTarget).data('original-title');
    $('#canvas-help').text(tooltip);
  });
}
function bodyCanvas_enableDisableTooltip(mode) {
  $('[data-toggle="tooltip"]').tooltip(mode); 
}