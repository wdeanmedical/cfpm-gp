function chart_renderLineChart(labels, data, canvas) {
  var lineChartData = {
    labels : labels,
    datasets : [
      {
        fillColor : "rgba(151,187,205,0.5)",
        strokeColor : "rgba(151,187,205,1)",
        pointColor : "rgba(151,187,205,1)",
        pointStrokeColor : "#fff",
        data : data
      }
    ]
  }
  var options = {scaleFontSize:14,bezierCurve:false, animation:false};
  new Chart(canvas.getContext("2d")).Line(lineChartData, options);
}