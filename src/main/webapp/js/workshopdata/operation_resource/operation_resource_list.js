var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
		}, {
			colkey : "operationResource_no",
			name : "资源编号",
		}, {
			colkey : "operationResource_desc",
			name : "资源描述",
		}, {
			colkey : "operation",
			name : "默认操作",
		}, {
			colkey : "site",
			name : "挂靠站点"
		}, {
			colkey : "byUser",
			name : "创建人"
		}, {
			colkey : "createTime",
			name : "创建时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}],
		jsonUrl : rootPath + '/workshop/operation_resource_findByPage.shtml',
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
		title : "编辑资源",
		type : 2,
		area : [ "800px", "100%" ],
		content : rootPath + '/workshop/operation_resource_editUI.shtml?id=' + cbox
	});
}
function addAccount() {
	pageii = layer.open({
		title : "新增资源",
		type : 2,
		area : [ "800px", "100%" ],
		content : rootPath + '/workshop/operation_resource_addUI.shtml'
	});
}
function delAccount() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/workshop/operation_resource_deleteEntity.shtml';
		var s = CommnUtil.ajax(url, {
			ids : cbox.join(",")
		}, "json");
		if (s == "success") {
			layer.msg('删除成功');
			grid.loadData();
		} else {
			layer.msg('删除失败');
		}
	});
}