var pageii = null;
var grid = null;
$(function() {
	var list_url = '/capacity_report/get_sfc_findByPage.shtml';
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [{
			colkey : "sfc",
			name : "产品SFC"
		}, {
			colkey : "item_no",
			name : "产品物料号"
		}, {
			colkey : "item_name",
			name : "产品物料名称"
		}, {
			colkey : "item_desc",
			name : "物料描述"
		},{
			colkey : "bom_no",
			name : "物料BOM编号"
		}, {
			colkey : "item_type",
			name : "物料类型"
		}, {
			colkey : "create_time",
			name : "装配时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		},{
			colkey : "site",
			name : "挂靠站点"
		},{
			colkey : "by_user",
			name : "创建人"
		}],
		jsonUrl : rootPath + list_url,
		checkValue : 'sfc',
		dymCol:true,
		pageSize:5,
		checkbox : true,
		serNumber : false
	});
	
});
