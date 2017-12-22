var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
		}, {
			colkey : "item",
			name : "物料编号",
		}, {
			colkey : "item_sfc",
			name : "物料SFC",
		}, {
			colkey : "receive",
			name : "剩余数量",
		}, {
			colkey : "by_user",
			name : "接收人",
		}, {
			colkey : "site",
			name : "站点",
		}, {
			colkey : "create_time",
			name : "接收时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}],
		jsonUrl : rootPath + '/PopupTable/assemble_sfclist.shtml?itemNo='+$('#hidden_item_no').val(),
		dymCol:true,
		checkbox : true,
		serNumber : false
	});
});