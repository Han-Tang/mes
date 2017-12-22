var pageii = null;
var grid = null;
$(function() {
	
	var shopOrderNo = $('#workorder_input').val();
	var operationNo = $('#operation_input').val();
	var wc_sfc = $('#wc_sfc').val();
	var list_url = '/produce/pod_bom_findByPage.shtml?operationNo='+operationNo+'&shopOrderNo='+shopOrderNo+'&wc_sfc='+wc_sfc;
	
	grid = lyGrid({
		pagId : 'paging2',
		l_column : [ {
			colkey : "id",
			name : "ID"
		},{
			colkey : "operation_no",
			name : "操作/步骤标识"
		}, {
			colkey : "sequence",
			name : "顺序"
		}, {
			colkey : "item_no",
			name : "组件/版本"
		}, {
			colkey : "item_name",
			name : "组件名称"
		}, {
			colkey : "item_desc",
			name : "描述"
		}, {
			colkey : "use_number",
			name : "装配所需数量"
		}, {
			colkey : "surplus_number",
			name : "剩余装配数量",
			renderData : function( rowindex ,data, rowdata, colkey){
				if (data=="0") {
					//变色
					//.table-striped>tbody>tr:nth-child(odd)>td,.table-striped>tbody>tr:nth-child(odd)>th
					$("table tr").eq(rowindex+3).toggleClass("clickTr");
					$("table tr").eq(rowindex+3).css("background-color","#229922")
				}
				return data;
			}
		}],
		jsonUrl : rootPath + list_url,
		dymCol:true,
		pageSize:5,
		checkbox : true,
		serNumber : false
	});
	
});
