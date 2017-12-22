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
		}],
		jsonUrl : rootPath + '/workshop/workcenter_child_findByPage.shtml?workcenter='+$('#workcenter_p').val(),
		dymCol:true,
		checkbox : true,
		serNumber : false
	});
});