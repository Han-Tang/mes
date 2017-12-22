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
		}],
		jsonUrl : rootPath + '/item_resource/getItemFindByPage.shtml',
		dymCol:true,
		checkbox : true,
		serNumber : false
	});
	
});