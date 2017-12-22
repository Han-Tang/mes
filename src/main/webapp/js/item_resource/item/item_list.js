var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
		}, {
			colkey : "item_no",
			name : "物料编号",
		}, {
			colkey : "item_name",
			name : "物料名称",
		}, {
			colkey : "item_desc",
			name : "物料描述",
		}, {
			colkey : "bom_no",
			name : "物料BOM编号",
		}, {
			colkey : "item_type",
			name : "物料类型",
			renderData : function(rowindex,data, rowdata, column) {
				if (data=="purchase"){
					return "外采购";
				}else if (data=="machining"){
					return "自生产";
				}
			}
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
		jsonUrl : rootPath + '/item_resource/item_findByPage.shtml',
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
		title : "编辑物料",
		type : 2,
		area : [ "800px", "100%" ],
		content : rootPath + '/item_resource/item_editUI.shtml?id=' + cbox
	});
}
function addAccount() {
	pageii = layer.open({
		title : "新增物料",
		type : 2,
		area : [ "800px", "100%" ],
		content : rootPath + '/item_resource/item_addUI.shtml'
	});
}
function delAccount() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/item_resource/item_deleteEntity.shtml';
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