var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
		}, {
			colkey : "shoporder_no",
			name : "工单编号",
		}, {
			colkey : "shoporder_numbers",
			name : "订单数量",
		}, {
			colkey : "shoporder_item",
			name : "物料编号",
		}, {
			colkey : "process_route",
			name : "工艺路线",
		}, {
			colkey : "status",
			name : "工单状态",
			renderData : function(rowindex,data, rowdata, column) {
				if (data=="0") {
					return "已创建";
				}else if (data=="1") {
					return "已下达";
				}else if (data=="2") {
					return "生产中";
				}else if (data=="3") {
					return "已完成";
				}else if (data=="4") {
					return "关闭";
				}else if (data=="5") {
					return "挂起";
				}else if (data=="6") {
					return "已删除";
				}
				return data;
		}
		},{
			colkey : "shoporder_start_date",
			name : "订单开始时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
		}}, {
			colkey : "shoporder_end_date",
			name : "订单完成时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
		}}, {
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
		jsonUrl : rootPath + '/product_plan/shoporder_maintenance_findByPage.shtml',
		dymCol:true,
		checkbox : true,
		serNumber : false
	});
	
	$("#addFun").click("click", function() {
		addAccount();
	});
	$("#editFun").click("click", function() {
		editAccount();
	});
	$("#delFun").click("click", function() {
		delAccount();
	});
	$("#search").click("click", function() {
		var searchParams = $("#searchForm").serializeJson();
		grid.setOptions({
			data : searchParams
		});
	});

});
function editAccount() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑工单",
		type : 2,
		area : [ "800px", "100%" ],
		content : rootPath + '/product_plan/shoporder_maintenance_editUI.shtml?id=' + cbox
	});
}
function addAccount() {
	pageii = layer.open({
		title : "新增工单",
		type : 2,
		area : [ "800px", "100%" ],
		content : rootPath + '/product_plan/shoporder_maintenance_addUI.shtml'
	});
}