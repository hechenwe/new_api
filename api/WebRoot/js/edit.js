var projectId = $("#input_projectId").val();

 

//------------------------关闭修改模块的div------------------------------------------------------------------
function colseModuleDiv(moduleId) {
	
		$("#div-"+moduleId).toggleClass('div-display');
}
 
 
 

//--------------------移动模块--------------------------------------------------------
function upModule() {
	    var dataKeys = $("#context-menu").attr("data-key").split(":");
	    var type = "up";
	    var moduleId = dataKeys[0];
	    var weight = dataKeys[1];
	    var urlString= '../module/upOrdownModule.html?projectId='+projectId+'&moduleId=' + moduleId+'&type='+type +'&weight='+weight;
		$.get(urlString, function(data) {
			 window.location.reload();//刷新当前页面.
		})
		
}
function downModule() {
	var dataKeys = $("#context-menu").attr("data-key").split(":");
	var type = "down";
	var moduleId = dataKeys[0];
	var weight = dataKeys[1];
	var urlString= '../module/upOrdownModule.html?projectId='+projectId+'&moduleId=' + moduleId+'&type='+type +'&weight='+weight;
	$.get(urlString, function(data) {
		window.location.reload();//刷新当前页面.
	})
	
}




