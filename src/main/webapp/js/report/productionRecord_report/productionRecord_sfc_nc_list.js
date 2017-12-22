var pageii = null;
var grid = null;
$(function() {
	var cp_sfc = $('#SFC_input').val();
	var operation = $('#operation').val();
	var list_url = '/report/getProductionRecord_sfc_ncListData.shtml?SFC='+cp_sfc+"&operation="+operation;
	
	//不良信息列表展示列：SFC、操作、资源、不良代码、不良描述、不良代码组、处理结果、备注
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ 
		{
			colkey : "sfc",
			name : "SFC"
		}, {
			colkey : "operation_description",
			name : "操作"
		},{
			colkey : "operationResource_no",
			name : "资源"		
		}, {			
			colkey : "nc_code",
			name : "不良代码"	
		}, {
			colkey : "nc_code_group",
			name : "不良代码组"
		}, {
			colkey : "status",
			name : "处理结果"
		}, {
			colkey : "repair_desc",
			name : "备注"
		}, {
			colkey : "handleTime",
			name : "处理时间",
			renderData : function(rowindex,data, rowdata, column) {
				return data;
				if (data == null || data == ""){
					return "";
				}
				else{
					return new Date(data).format("yyyy-MM-dd hh:mm:ss")
				}				
			}						
		}],
		jsonUrl : rootPath + list_url,
		dymCol:true,
		pageSize:20,
		checkbox : false,
		serNumber : false
	});	
});
