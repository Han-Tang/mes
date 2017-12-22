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
		 	colkey : "process_route",
		 	name : "工艺路线",
		 	isSort : true
		 }, {
		 	colkey : "process_route_type",
		 	name : "工艺路线类型",
		 	isSort : true
		 }, {
		 	colkey : "process_route_desc",
		 	name : "工艺路线描述"
		 }],
		jsonUrl : rootPath + '/workshop/routing_findByPage.shtml',
		dymCol:true,
		checkbox : true,
		serNumber : false
	});
	
});