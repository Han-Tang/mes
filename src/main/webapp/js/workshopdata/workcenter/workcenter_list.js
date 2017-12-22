var pageii = null;
var grid = null;
$(function() {

	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id"
		}, {
			colkey : "workcenter_no",
			name : "工作中心编号"
		}, {
			colkey : "workcenter_name",
			name : "工作中心名称"
		}, {
			colkey : "workcenter_description",
			name : "工作中心描述"
		},{
			colkey : "workcenter_class",
			name : "工作中心层级"
		},{
			colkey : "workcenter_class",
			name : "工作中心类型",
			renderData : function(rowindex,data, rowdata, column) {
				if(data == "workshop")
				{
					return "车间";
				}
				else if(data == "production_line")
				{
					return "产线";					
				}
				else
				{
					return "未知";
				}
			}
		},{
			colkey : "workcenter_version",
			name : "版本"
		}, {
			colkey : "workcenter_parent",
			name : "父工作中心"
		},{
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
		jsonUrl : rootPath + '/workshop/workcenter_findByPage.shtml',
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
		title : "编辑工作中心",
		type : 2,
		area : [ "800px", "100%" ],
		content : rootPath + '/workshop/workcenter_edit.shtml?id=' + cbox
	});
}
function addAccount() {
	pageii = layer.open({
		title : "新增工作中心",
		type : 2,
		area : [ "800px", "100%" ],
		content : rootPath + '/workshop/workcenter_add.shtml'
	});
}
function delAccount() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/workshop/workcenter_deleteEntity.shtml';
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