var pageii = null;
var item_lsit_grid = null;
$(function() {
	
	var data = $("#process_condition_form").serialize();
	
	item_lsit_grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "wc_sfc",
			name : "车间作业控制SFC"
		},{
			colkey : "workorderNo",
			name : "工单"
		}, {
			colkey : "operation",
			name : "操作/步骤"
		}, {
			colkey : "resource",
			name : "资源"
		}, {
			colkey : "state",
			name : "状态"
		}],
		jsonUrl : rootPath + '/produce/process_start_list.shtml?'+data,
		dymCol:true,
		pageSize:5,
		checkbox : false,
		serNumber : false
	});
});
