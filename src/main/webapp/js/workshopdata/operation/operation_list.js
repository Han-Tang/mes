var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
		}, {
			colkey : "operation_no",
			name : "操作编号",
		}, {
			colkey : "operation_description",
			name : "操作描述",
		}, {
			colkey : "operation_type",
			name : "操作类型",
		}, {
			colkey : "resoucre_type",
			name : "资源类型",
		}, {
			colkey : "default_resource",
			name : "默认资源",
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
		jsonUrl : rootPath + '/workshop/operation_findByPage.shtml',
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

	//扩展
	$("#op_ok2").click("click", function() {
		var data = grid.selectRow()[0];
		window.parent.call_input(data);
		window.parent.findButton(data.operation_no);
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引  
		parent.layer.close(index);
	});
	
	$("#op_canner2").click("click", function() {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引  
		parent.layer.close(index);
	});
	
});


function editAccount() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑操作",
		type : 2,
		area : [ "800px", "100%" ],
		content : rootPath + '/workshop/operation_editUI.shtml?id=' + cbox
	});
}
function addAccount() {
	pageii = layer.open({
		title : "新增操作",
		type : 2,
		area : [ "800px", "100%" ],
		content : rootPath + '/workshop/operation_addUI.shtml'
	});
}
function delAccount() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/workshop/operation_deleteEntity.shtml';
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