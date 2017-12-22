var pageii = null;
var grid = null;
var opData = {};
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
		 	colkey : "id",
		 	name : "id",
		 }, {
		 	colkey : "siteBo",
		 	name : "站点"
		 },	{
		 	colkey : "process_route",
		 	name : "工艺路线",
		 },{
		 	colkey : "process_route_desc",
		 	name : "工艺路线描述"
		 }, {
		 	colkey : "state",
		 	name : "状态",
		 	renderData : function (rowindex, data, rowdata, column) {
		 		if (data=="1" || data==1) {
		 			return "可用";
		 		}else if (data=="0" || data==0){
		 			return "不可用";
		 		}
		 	}
		 }, {
		 	colkey : "allowpass",
		 	name : "是否允许跳过"
		 }, {
		 	colkey : "createTime",
		 	name : "创建时间",
		 	renderData : function (rowindex, data, rowdata, column) {
		 		return new Date(data).format("yyyy-MM-dd hh:mm:ss");
		 	}
		 }, {
		 	colkey : "createUser",
		 	name : "创建人"
		 }, {
			 	colkey : "data",
			 	name : "数据源",
			 	hide : true
		 }],
		jsonUrl : rootPath + '/workshop/routing_findByPage.shtml?site=123',
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
		title : "工艺路线编辑",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/process_designer/gooFlow/routing_edit.html'
	});
}
function addAccount() {
	$.ajax({
        type : "POST",  
        url : rootPath + "/workshop/routing_getOperation.shtml",  
        success : function(result) {
        	var rs = JSON.parse(result);
        	opData = rs.data;
            if (rs.success) {
            	show();
            } else {
            	layer.msg('查询失败', {
          		  offset: 't',
          		  anim: 6
          		});
            }  
        }  
    });
}

function show(){
	pageii = layer.open({
		title : "工艺路线绘制",
		type : 2,
		area : [ "80%", "90%" ],
		content : rootPath + '/process_designer/gooFlow/routing_add.html'
	}); 
}

function delAccount() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/workshop/routing_deleteEntity.shtml';
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