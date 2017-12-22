var pageii = null;
var grid = null;
$(function() {
	var sfc = $('#sfc').val();
	var operation = $('#operation').val();
	var list_url = '/report/sfc_assembly_info_listData.shtml?sfc='+sfc+"&operation="+operation;
	
	//SFC装配信息列表展示列：物料编号、物料名称、物料描述、物料类型、接收时间、操作员、物料Sfc、批次号。。
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ 
		{
			colkey : "item",
			name : "物料编号"
		}, {
			colkey : "item_name",
			name : "物料名称"
		},{
			colkey : "item_desc",
			name : "物料描述"		
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
			colkey : "create_time",
			name : "接收时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}	
		}, {
			colkey : "by_user",
			name : "操作员"	
		}, {
			colkey : "item_sfc",
			name : "物料Sfc"	
		}, {
			colkey : "batch",
			name : "批次号"	
		}],
		jsonUrl : rootPath + list_url,
		dymCol:true,
		pageSize:20,
		checkbox : false,
		serNumber : false
	});	
});
