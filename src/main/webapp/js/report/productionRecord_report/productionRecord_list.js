var pageii = null;
var grid = null;
var query_form = $('#query_form').val();
var query_data = eval('(' + query_form + ')');
$(function() {
	var list_url = '/report/getProductionRecordListData.shtml';
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ 
		{
			colkey : "shoporder_no",
			name : "产品工单",
			width : '12%'
		},{
			colkey : "sfc",
			name : "产品SFC",
			width : '12%'
		}, {
			colkey : "sfcStatus",
			name : "SFC状态",
			width : '5%',
			renderData : function(rowindex,data, rowdata, column) {
				if (data==0 || data=="0") {
					return "创建";
				}else if (data==1 || data=="1") {
					return "生产中";
				}else if (data==2 || data=="2") {
					return "已完成";
				}else if (data==3 || data=="3") {
					return "报废";
				}else if (data==4 || data=="4") {
					return "记录不良";
				}
			}
		}, {
			colkey : "shoporder_item",
			name : "产品物料编号",
			width : '12%'
		}, {
			colkey : "process_route",
			name : "工艺路线",
			width : '12%'
		}, {
			colkey : "shopOrderStatus",
			name : "工单状态",
			width : '5%',
			renderData : function(rowindex,data, rowdata, column) {
				if (data==0 || data=="0") {
					return "创建";
				}else if (data==1 || data=="1") {
					return "<font color='#B69F4A'>已下达</font>";
				}else if (data==2 || data=="2") {
					return "<font color='#00B0F0'>生产中</font>";
				}else if (data==3 || data=="3") {
					return "<font color='#92D050'>已完成</font>";
				}else if (data==4 || data=="4") {
					return "<font color='#7030A0'>关闭</font>";
				}else if (data==5 || data=="5") {
					return "<font color='#A5A5A5'>挂起</font>";
				}else if (data==6 || data=="6") {
					return "<font color='red'>已删除</font>";
				}
			}
		}, {
			colkey : "site",
			name : "站点",
			width : '6%'
		},{
			colkey : "workcenter",
			name : "车间",
			width : '11%'
		}, {
			colkey : "workline",
			name : "产线",
			width : '11%'
		},{
			colkey : "by_user",
			name : "创建人",
			width : '4%'
		}, {
			colkey : "create_time",
			name : "工单创建时间",
			width : '10%',
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		},{		
			colkey : "sfc",
			name : "详细信息",
			width : '6%',
			renderData : function(rowindex,data, rowdata, column) {
				return "<a href='javascript:;' onClick=\"showMore('"+data+"');\">sfc详细信息</a>";
			}
		}],
		data : query_data,
		jsonUrl : rootPath + list_url,
		isFixed: false,
		checkbox : false
	});
	
});

function showMore(sfc){
	window.open(rootPath + "/report/get_productionRecord_sfc_list.shtml?sfc="+sfc);
}
