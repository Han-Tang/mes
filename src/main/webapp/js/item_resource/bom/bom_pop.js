var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
		}, {
			colkey : "item_bom_no",
			name : "物料BOM编号",
		}, {
			colkey : "item_bom_name",
			name : "物料BOM名称",
		}, {
			colkey : "item_bom_desc",
			name : "物料BOM描述",
		}],
		jsonUrl : rootPath + '/item_resource/bom_findByPage.shtml',
		dymCol:true,
		checkbox : true,
		serNumber : false
	});
	
});