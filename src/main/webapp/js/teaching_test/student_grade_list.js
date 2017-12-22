var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "站点",
			name : "站点"
		}, {
			colkey : "姓名",
			name : "姓名"
		}, {
			colkey : "POD操作流",
			name : "POD操作流"
		}, {
			colkey : "工艺路线",
			name : "工艺路线"
		}, {
			colkey : "POD操作功能按钮",
			name : "POD操作功能按钮"
		},{
			colkey : "工作中心维护",
			name : "工作中心维护"
		}, {
			colkey : "操作维护",
			name : "操作维护"
		}, {
			colkey : "资源维护",
			name : "资源维护"
		}, {
			colkey : "站点维护",
			name : "站点维护"
		}, {
			colkey : "物料维护",
			name : "物料维护"
		},   {
			colkey : "物料组维护",
			name : "物料组维护"
		}, {
			colkey : "物料接收",
			name : "物料接收"
		}, {
			colkey : "不合格代码维护",
			name : "不合格代码维护"
		}, {
			colkey : "不合格组维护",
			name : "不合格组维护"
		}, {
			colkey : "不合格品处置",
			name : "不合格品处置"
		},{
			colkey : "车间物料消耗看板",
			name : "车间物料消耗看板"
		}, {
			colkey : "产能报表",
			name : "产能报表"
		}, {
			colkey : "生产进度报表",
			name : "生产进度报表"
		}, {
			colkey : "正向追溯报表",
			name : "正向追溯报表"
		}, {
			colkey : "反向追溯报表",
			name : "反向追溯报表"
		},  
		{
			colkey : "不合格报表",
			name : "不合格报表",
		},  
		{
			colkey : "总分",
			name : "总分",
		}],
		jsonUrl : rootPath + '/point_grade/point_grade_findByPage.shtml',
		dymCol:true,
		checkbox : true,
		serNumber : false
	});
	

	$("#search").click("click", function() {
		var searchParams = $("#searchForm").serializeJson();
		grid.setOptions({
			data : searchParams
		});
	});
});