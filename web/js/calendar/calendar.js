var APPT = 1;
var TASK = 2;


function app_loadCalendar() {
  request = $.ajax({
    type: "GET",
    contentType: "application/json",
    url: "cal/getCalendarEvents?sessionId="+app_getSessionId()+"&module=" + app_module,
    dataType: "json",
    success: function(data) {
      app_calendar.fullCalendar('destroy');
      var calendar =  app_calendar.fullCalendar({
        defaultView: app_calendarView,
        defaultDate: app_date,
        header: {
          left: 'prev,next today',
          center: 'title',
          right: 'month,agendaWeek,agendaDay'
        },
        windowResize: true,
        selectable: true,
        selectHelper: true,
        select: function(start, end) {
          if(app_module==PORTAL_MODULE){
            return false
          }else{
           app_newApptForm(start, end)
          } 
        },
        eventDrop: function(event, delta, reverseFunc, jsEvent, ui, view) {
          if (app_module==PORTAL_MODULE) {
            reverseFunc();
            return false;
          } 
          if (event.eventType == APPT) {
            app_moveAppt(event, jsEvent, ui, view);
          }
          else if (event.eventType == TASK) {
            pm_moveTask(event, jsEvent, ui, view);
          }
        },
        eventResize: function(event, jsEvent, ui, view) {
          if (event.eventType == APPT) {
            app_resizeAppt(event, jsEvent, ui, view);
          }
          else if (event.eventType == TASK) {
            pm_resizeTask(event, jsEvent, ui, view);
          }
        },
        lazyFetching: true,
        editable: true,
        eventRender: function(event, element) {
          startDate = event.start.format('h:mm');
          endDate = event.end.format('h:mm');
          eventTitle = event.title; 
          element.find('.fc-event-time').html(startDate + ' - ' + endDate);
          element.find('.fc-event-title').html(eventTitle);
          if (event.eventType == TASK) {
            element.addClass('fc-task-event');
          }
        },
        eventMouseover: function(calEvent, jsEvent) {
          var tooltip = 
          '<div class="tooltipevetn" style="min-width:110px;max-width:400px;min-height:20px;background:#C0C0C0;color: black;'+
          'font-size: small; font-weight: bold;position:absolute;z-index:10001;">' + 
          calEvent.patientName+' '+calEvent.apptType+' with '+calEvent.clinicianName+'<br>'+calEvent.desc+'</div>';
          $("body").append(tooltip);
          $(this).mouseover(function(e) {
            $(this).css('z-index', 10000);
            $('.tooltipevetn').fadeIn('500');
            $('.tooltipevetn').fadeTo('10', 1.9);
          }).mousemove(function(e) {
            $('.tooltipevetn').css('top', e.pageY + 10);
            $('.tooltipevetn').css('left', e.pageX + 20);
          });
        },
        eventMouseout: function(calEvent, jsEvent) {
          $(this).css('z-index', 8);
          $('.tooltipevetn').remove();
        },
        eventClick: function(calEvent, jsEvent, view) {
          //alert('Event: ' + calEvent.title);
          //alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
          //alert('View: ' + view.name);
          // change the border color just for fun
          //$(this).css('border-color', 'red');
          if (calEvent.eventType == APPT) {
            if(app_module==PORTAL_MODULE){
             app_showApptForm(calEvent)
            }else{
              app_editApptForm(calEvent);
            }  
          }
          else if (calEvent.eventType == TASK) { 
            pm_editTaskForm(calEvent);
          }
        },
        events: $.map(data.list, function(item, i) {
          var event = new Object();
          if (item != null) {
            event.id = item.id;
            event.className = item.className;
            event.start = item.startTime;
            event.end = item.endTime;
            event.desc = item.desc;
            event.apptType = item.apptType;
            event.patientName = item.patientName;
            event.clinicianName = item.clinicianName;
            event.title = item.patientName+'<br>'+item.apptType+'<br>'+item.clinicianName;
            event.eventType = item.eventType;
            event.allDay = !item.timeSpecified;
            event.taskId = item.taskId;
            return event;
          }
        }),
        allDayDefault: false
      });
      $('.fc-button-agendaDay').click(function() { app_calendarView = 'agendaDay'});
      $('.fc-button-agendaWeek').click(function() { app_calendarView = 'agendaWeek'});
      $('.fc-button-month').click(function() { app_calendarView = 'month'});
    },
    error: function(XMLHttpRequest, textStatus, errorThrown) {
      util_debug('There was an error while fetching data for calendar.');
    }
  });
}