// -----------------------------添加参数----------------------------------------
function toAddParameter() {
	
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var interfacId = dataKeys[0]; 
	
	var url = '../interface/getInterfaceParameterFormatParameterSize.html?interfacId=' + interfacId;

	$.get(url, function(data) {
		var parameterFormat = data.parameterFormat;
		var parameterSize = data.parameterSize;

		if (parameterFormat == "JSON") {
			if (parameterSize == 0) {
				 
				$("#div_parameter"+interfacId).toggleClass('div-display');
			} else {
				alert("JSON格式的参数已添加!");
			}
		} else {
			 
			$("#div_parameter"+interfacId).toggleClass('div-display');
		}
		$("#interfacId").val(interfacId);
	})

}
function updateParameter(){
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var interfacId = dataKeys[0];
	 var parameterId = dataKeys[1];
	 var url = '../parameter/updateParameter.html?parameterId='+parameterId;
	$.get(url,function (data){
		//$("#up-parameter"+interfacId).empty();
		$("#up-parameter"+interfacId).append(data);//.toggleClass('div-display');;
		 
	})
}

//-----------------------------------------------------保存参数约束说明----------------------------------------------
function deleteParameter() {
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var parameterId = dataKeys[1];
	if (confirm("确认要删除？")) {
	var urlString = "../parameter/deleteParameter.html?parameterId="+parameterId;
	 
    $.get(urlString, function(data) {
		
		if(data=="1"){
			//alert(parameterId);
			$("#tr-"+parameterId).remove();
		}else{
			alert("删除失败!");
		}
	})
	
	}	  
}
