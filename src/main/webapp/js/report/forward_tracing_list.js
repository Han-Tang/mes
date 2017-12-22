var pageii = null;
var grid = null;
$(function() {
	var cp_sfc = $('#SFC_input').val();
	var list_url = '/capacity_report/forward_tracing_findByPage.shtml?SFC='+cp_sfc;
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "sfc",
			name : "产品SFC"
		}, {
			colkey : "shoporder_item",
			name : "产品物料号"
		}, {
			colkey : "item_name",
			name : "产品物料名称"
		}, {
			colkey : "item_sfc",
			name : "装配物料SFC"
		},{
			colkey : "item_type",
			name : "组件类别"
		}, {
			colkey : "create_time",
			name : "装配时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "use_num",
			name : "装配数量"
		}, {
			colkey : "workcenter",
			name : "车间"
		},{
			colkey : "workcenter_child",
			name : "产线"
		},{
			colkey : "operation",
			name : "操作"
		},{
			colkey : "default_resource",
			name : "资源"
		},{
			colkey : "byUser",
			name : "创建人"
		}],
		jsonUrl : rootPath + list_url,
		dymCol:true,
		pageSize:5,
		checkbox : false,
		serNumber : false
	});
	
});
