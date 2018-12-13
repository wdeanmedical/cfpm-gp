function bodyCanvas_addArrow(canvas, fromLine) {
  var coords = [50, 50, 100, 100], arrowAngle = -45, topLeft = {};

  if (fromLine) {
    coords = fromLine.points;
    arrowAngle = fromLine.arrowAngle;
    topLeft = fromLine.topLeft;
  }
  
  var line, arrow, circle;
  
  line = new fabric.Line(coords, _.extend({
      stroke: '#000',
      selectable: true,
      strokeWidth: '2',
      hasBorders: true,
      hasControls: false,
      padding: 100,
      originX: 'center',
      originY: 'center',
      perPixelTargetFind: true,  
      targetFindTolerance: 4,
      lockScalingX: true,
      lockScalingY: true
  }, topLeft) );

  var centerX = (line.x1 + line.x2) / 2,
      centerY = (line.y1 + line.y2) / 2;

  deltaX = line.left - centerX,
  deltaY = line.top - centerY;

  arrow = new fabric.Triangle({
      left: line.get('x1') + deltaX,
      top: line.get('y1') + deltaY,
      originX: 'center',
      originY: 'center',
      hasBorders: false,
      hasControls: false,
      lockScalingX: true,
      lockScalingY: true,
      lockRotation: true,
      pointType: 'arrow_start',
      angle: arrowAngle,
      width: 20,
      height: 20,
      fill: '#000'
  });

  circle = new fabric.Circle({
      left: line.get('x2') + deltaX,
      top: line.get('y2') + deltaY,
      radius: 3,
      stroke: '#000',
      strokeWidth: 3,
      originX: 'center',
      originY: 'center',
      hasBorders: false,
      hasControls: false,
      lockScalingX: true,
      lockScalingY: true,
      lockRotation: true,
      selectable: false,
      pointType: 'arrow_end',
      fill: '#000'
  });

  circle.line = arrow.line = line; 
  
  line.arrow = arrow; 
  line.circle = circle;

  line.customType = arrow.customType = circle.customType = 'arrow';
  
  line.on('removed', function(){
    canvas.remove(arrow);
    canvas.remove(circle);
  })

  arrow.on('removed', function(){
    canvas.remove(line);
    canvas.remove(circle);
  })

  circle.on('removed', function(){
    canvas.remove(line);
    canvas.remove(arrow);
  })

  var lineToObject = line.toObject 

  line.toObject = function() {
    return _.extend(lineToObject.call(line), {
      arrowAngle:arrow.get('angle')
    })
  }
  
  arrow.on('moving', function () {
      bodyCanvas__arrow___moveEnd(canvas, arrow);
  });

  circle.on('moving', function () {
      bodyCanvas__arrow___moveEnd(canvas, circle);
  });

  line.on('moving', function () {
      bodyCanvas__arrow___moveLine(line);
  });

  canvas.add(line, arrow, circle);
  
  canvas.discardActiveObject();
}

function bodyCanvas__arrow___calcArrowAngle(x1, y1, x2, y2) {
  var angle = 0, x, y;

  x = (x2 - x1);
  y = (y2 - y1);

  if (x === 0) {
      angle = (y === 0) ? 0 : (y > 0) ? Math.PI / 2 : Math.PI * 3 / 2;
  } else if (y === 0) {
      angle = (x > 0) ? 0 : Math.PI;
  } else {
      angle = (x < 0) ? Math.atan(y / x) + Math.PI : (y < 0) ? Math.atan(y / x) + (2 * Math.PI) : Math.atan(y / x);
  }

  return (angle * 180 / Math.PI);
}

function bodyCanvas__arrow___moveEnd(canvas, obj) {
  var x1, y1, x2, y2;

  var line = obj.line;
  
  if (obj.pointType === 'arrow_end') {
      line.set('x2', obj.get('left'));
      line.set('y2', obj.get('top'));
  } else {
      line.set('x1', obj.get('left'));
      line.set('y1', obj.get('top'));
  }

  var line = obj.line;

  line._setWidthHeight();

  x1 = line.get('x1');
  y1 = line.get('y1');
  x2 = line.get('x2');
  y2 = line.get('y2');

  angle = bodyCanvas__arrow___calcArrowAngle(x1, y1, x2, y2);

  if (obj.pointType === 'arrow_end') {
      obj.arrow.set('angle', angle - 90);
  } else {
      obj.set('angle', angle - 90);
  }
  canvas.renderAll();
}

function bodyCanvas__arrow___moveLine(line) {
  var oldCenterX = (line.x1 + line.x2) / 2,
      oldCenterY = (line.y1 + line.y2) / 2,
      deltaX = line.left - oldCenterX,
      deltaY = line.top - oldCenterY;

  line.arrow.set({
      'left': line.x1 + deltaX,
      'top': line.y1 + deltaY
  }).setCoords();

  line.circle.set({
      'left': line.x2 + deltaX,
      'top': line.y2 + deltaY
  }).setCoords();

  line.set({
      'x1': line.x1 + deltaX,
      'y1': line.y1 + deltaY,
      'x2': line.x2 + deltaX,
      'y2': line.y2 + deltaY
  });

  line.set({
      'left': (line.x1 + line.x2) / 2,
      'top': (line.y1 + line.y2) / 2
  }).setCoords();
}

function bodyCanvas_filterSavedArrows(savedJson) {
  var json = JSON.parse(savedJson);
  var arrows = [];
  var nonarrows = [];
  json.objects.forEach(function(object){
    if (object.type === "line") {
      arrows.push(object)
    } else if (object.type != "triangle" && object.type != "circle") {
      nonarrows.push(object);
    }
  }) 
  json.objects = nonarrows;
  return {filteredJSON: JSON.stringify(json), arrows: arrows}
}

function bodyCanvas_addArrows(canvas, arrows) {
  var arrowsToAdd = Array.from(arrows);
  var arrow = arrowsToAdd.pop();
  var fromLine;
  if (arrow) {
    fabric.Line.fromObject(arrow, function(line){
      fromLine = {
        arrowAngle: line.get('arrowAngle'),
        points: [line.x1, line.y1, line.x2, line.y2],
        topLeft:{top: line.top, left: line.left}
      }
    })  
    bodyCanvas_addArrow(canvas, fromLine)
    bodyCanvas_addArrows(canvas, arrowsToAdd);
  } else {
    if (_.isFunction(canvas.discardActiveObject)) {
      canvas.discardActiveObject();
    }
  }
}
