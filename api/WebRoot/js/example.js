//修改返回示例
function toUpdateExample(){
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var interfacId = dataKeys[0];
	 var exampleId = dataKeys[1];
	var url = "../example/toUpdateExample.html?exampleId="+exampleId;
	$.get(url,function (data){
		$("#div-exam-"+interfacId).append(data);
		$("#example"+exampleId).addClass('div-display')
	})
	
}

//-------------------------------------显示添加返回示例-------------------------------------------
function openOrColseEaxmple(){
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var interfacId = dataKeys[0]; 
	$("#returnExample"+interfacId).toggleClass('div-display');
	 
}