var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
		}, {
			colkey : "item",
			name : "物料编号",
		}, {
			colkey : "item_sfc",
			name : "SFC",
		}, {
			colkey : "receive",
			name : "接收数量",
		}, {
			colkey : "site",
			name : "挂靠站点"
		}, {
			colkey : "by_user",
			name : "创建人"
		}, {
			colkey : "create_time",
			name : "创建时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}],
		jsonUrl : rootPath + '/workshop_inventory/workshop_inventory_findByPage.shtml',
		dymCol:true,
		checkbox : true,
		serNumber : false
	});
	$("#addFun").click("click", function() {
		addAccount();
	});
	$("#search").click("click", function() {
		var searchParams = $("#searchForm").serializeJson();
		grid.setOptions({
			data : searchParams
		});
	});
});

function addAccount() {
	pageii = layer.open({
		title : "开始接收批次",
		type : 2,
		area : [ "80%", "100%" ],
		content : rootPath + '/workshop_inventory/workshop_inventory_addUI.shtml'
	});
}