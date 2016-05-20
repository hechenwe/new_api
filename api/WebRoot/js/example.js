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

//-----------------------------------------------------删除返回示例----------------------------------------------
function deleteExample() {
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 
	 var exampleId = dataKeys[1]; 
	if (confirm("确认要删除？")) {
		var url = '../example/deleteExample.html?exampleId=' + exampleId;

		$.get(url, function(data) {

			if (data == "1") {
				$("#example"+exampleId).remove();
			}
		})
	}
}


//-----------------------------------------------------保存-返回示例----------------------------------------------
  function saveExample(interfacId) {
	 
	  
	var exampleId = Math.uuid();
	var exampleType = "SUCCESS";//$("#"+ interfacId +"exampleType").val();
	var exampleContent = $("#"+ interfacId +"exampleContent").val();
    
	var urlString ="../example/savaExample.html"+
	               "?interfacId="+interfacId+
	               "&exampleId="+exampleId+
	               "&exampleType="+exampleType+
	               "&exampleContent="+exampleContent;
	//alert(urlString);
	$.get(urlString, function(data) {
		 
		$("#div-example"+interfacId).append(data);
		$("#"+ interfacId +"exampleContent").val("");
		 
		$("#returnExample"+interfacId).addClass("div-display");
	})	
}  
