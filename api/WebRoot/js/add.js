

//-----------------------------添加项目----------------------------------------
function toAddProject() {
	$("#page-wrapper").empty();
	$("#page-wrapper").append($("#project").html());
}
 
 



//-------------------------------------显示添加返回参数说明-------------------------------------------
function openOrCloseValueExplain(parameterId,exampleId){
	$("#valueExplain"+exampleId).toggleClass('div-display');
	$("#"+exampleId+"valueExplainParameterId").val(parameterId);
	 
}