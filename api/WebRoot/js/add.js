

//-----------------------------添加项目----------------------------------------
function toAddProject() {
	$("#page-wrapper").empty();
	$("#page-wrapper").append($("#project").html());
}
 


// -----------------------------添加参数的约束----------------------------------------
/*function toAddParameterConstraint(parameterId) {
	$("#page-wrapper").empty();
	$("#page-wrapper").append($("#parameter_constraint").html());
	$("#parameterId").val(parameterId);
}*/
// -----------------------------添加参数的约束----------------------------------------
function toAddExample(parameterId) {
/*	$("#page-wrapper").empty();
	$("#page-wrapper").append($("#parameter_constraint").html());
	$("#parameterId").val(parameterId);
}*/

}
//-------------------------------------显示添加参数约束-------------------------------------------
function divDisplay(){
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var interfacId = dataKeys[0]; 
	 var parameterId = dataKeys[1]; 
	
	$("#div_parameter_constraint_"+interfacId).toggleClass('div-display');
	 
	$("#"+interfacId+"_parameterId").val(parameterId);
	 
}

//-------------------------------------显示添加返回示例-------------------------------------------
function openOrColseEaxmple(){
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var interfacId = dataKeys[0]; 
	$("#returnExample"+interfacId).toggleClass('div-display');
	 
}
//-------------------------------------显示添加返回参数-------------------------------------------
function openOrColseEaxmpleReturn(){
	 
	 
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var interfacId = dataKeys[0]; 
	 var exampleId = dataKeys[1]; 
	$("#example-parameter"+exampleId).toggleClass('div-display');
	 
}
//-------------------------------------显示添加返回参数说明-------------------------------------------
function openOrCloseValueExplain(parameterId,exampleId){
	$("#valueExplain"+exampleId).toggleClass('div-display');
	$("#"+exampleId+"valueExplainParameterId").val(parameterId);
	 
}