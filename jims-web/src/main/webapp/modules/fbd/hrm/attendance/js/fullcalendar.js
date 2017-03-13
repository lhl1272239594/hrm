
$(function() {
	$('#calendar').fullCalendar({
		contentHeight: 550,
		header: {
			 left:   'prevYear,prev,today,next,nextYear',
			 center: 'title',
			 right:  'month,agendaWeek,agendaDay'
		},
	    monthNames: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
        monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        dayNames: ["周日", "星期一", "周二", "周三", "周四", "周五", "周六"],
        dayNamesShort: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
        firstDay: 1,
		selectable:true,
		buttonText:{
			 today:    '今天',
			 month:    '月',
			 week:     '周',
			 day:      '日'
		},
		editable: true,//判断该日程能否拖动
		/*viewDisplay: function (view) {
			asyncboxPrompt();
			var a = new Date();
            $("#calendar").fullCalendar('removeEvents');
            $.getJSON('QueryEventsServlet?t='+a.getTime(),  function (data) {
                for(var i=0;i<data.length;i++) {  
                    var obj = new Object();
                    obj.id = data[i].id;
                    obj.title = data[i].event;
                    obj.start = data[i].startDate;
                    obj.end = data[i].endDate;
                    obj.description = data[i].remark;
                    if(data[i].allDay=="true"){
                    	obj.allDay = true;
                    }else{
                    	obj.allDay = false;
                    }
                    $("#calendar").fullCalendar('renderEvent',obj,true); //把从后台取出的数据进行封装以后在页面上以fullCalendar的方式进行显示 
            	}
            });
            $.close('tishiId');
        },*/
	    eventMouseover:function( event, jsEvent, view ) {
			$(this).css('border-color', 'red');
		},
		eventMouseout:function( event, jsEvent, view ) {
			$(this).css('border-color', '');
		},
	    eventClick: function(event, element) {
	    	var startDate = $.fullCalendar.formatDate(event.start, "yyyy-MM-dd HH:mm:ss");//格式化起始日期
	        var endDate = $.fullCalendar.formatDate(event.end, "yyyy-MM-dd HH:mm:ss");//格式化截止日期
	        showOptionDialog(event.title,startDate,endDate,event.description,event.allDay,'update');//显示更新数据的对话框
	        //修改被点击的事件
	        $("#optionButton").click(function(){
	        	$.ajax( {
					async : true,
					type : "post",
					contentType : "application/x-www-form-urlencoded",
					cache : true,
					url:"UpdateEventsServlet",
					data:{//设置数据源
                        eventId:event.id,
                        eventContent:$("#eventContent").val(),
                        startDate:$("#startDate").val(),
                        endDate:$("#endDate").val(),
                        isAllDay:$('.eventHandTab input[name="isAllDay"]:checked').val(),
                        remark:$("#remark").val()
                    },
					timeout : 6000,
					beforeSend : function() {
                    	if(!checkDateTime()){  
                    	    asyncbox.alert("结束时间必须晚于开始时间！","系统提示",function(){});
                    	    return false;  
                    	}
						$("#sab").html("<img src='asyncbox/skins/ZCMS/images/wait.gif' alt='数据正在处理中...'/>");
					},
					success : function(result) {
						if(result=="True"){
							event.title=$("#eventContent").val();
				            event.start=$("#startDate").val();
				            event.end=$("#endDate").val();
				            event.description=$("#remark").val();
				            if($('.eventHandTab input[name="isAllDay"]:checked').val()=="true"){
				            	event.allDay = true;
				            }else{
				            	event.allDay = false;
				            } 
				        	$('#calendar').fullCalendar('updateEvent', event);//修改事件
						}else{
							asyncbox.alert("事件修改失败！","系统提示",function(){});
						}
						$.close("eventCL");
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(XMLHttpRequest.responseText);
					}
				});
	        
	        	
	        });
	        $("#deleteEvent").click(function (){
	        	asyncbox.confirm('您确定要删除该事件?','系统提示',function(action){
	     		　　if(action == 'ok'){
		     			$.ajax( {
							async : true,
							type : "post",
							contentType : "application/x-www-form-urlencoded",
							cache : true,
							url:"DeleteEventsServlet",
							data:{//设置数据源
		                        eventId:event.id 
		                    },
							timeout : 6000,
							beforeSend : function() {
		            			$("#DE").html("<img src='asyncbox/skins/ZCMS/images/wait.gif' alt='数据正在处理中...'/>");
							},
							success : function(result) {
								$.close("eventCL");
								if(result=="True"){
									$('#calendar').fullCalendar('removeEvents',event.id);  //删除事件
								}else{
									asyncbox.alert("事件删除失败！","系统提示",function(){});
								}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								alert(XMLHttpRequest.responseText);
							}
						});
	     		　　　}
     		　　　if(action == 'cancel'||action == 'close'){}
     		　   });
	        	
	        });
	    },
	});
})



