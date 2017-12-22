var pageii = null;
var grid = null;
$(function() {
	var cp_sfc = $('#SFC_input').val();
	var cp_sfc = $('#SFC_input').val();
	var list_url = '/report/getProductionRecord_sfcListData.shtml?SFC='+cp_sfc;
	
	//SFC详细列表展示列：操作、资源、SFC、物料号、工单、步骤状态、创建人、创建时间、站点、装配信息、不良信息。
	grid = lyGrid({
		pagId : 'paging',
		l_column : [
		{
			colkey : "sfc",
			name : "产品SFC"		
		},
		{
			colkey : "operation_no",
			name : "操作",
			hide : true
		},
		{
			colkey : "operation_description",
			name : "操作描述"
		}, {
			colkey : "operationResource_no",
			name : "资源"
		}, {
			colkey : "shoporder_item",
			name : "物料号"
		}, {
			colkey : "shoporder_no",
			name : "工单"
		}, {
			colkey : "stepStatus",
			name : "SFC步骤状态",
			renderData : function(rowindex,data, rowdata, column) {
				if (data==0 || data=="0") {
					return "创建";
				}else if (data==1 || data=="1") {
					return "<font color='#00B0F0'>生产中</font>";
				}else if (data==2 || data=="2") {
					return "<font color='#00B050'>已完成</font>";
				}else if (data==3 || data=="3") {
					return "<font color='red'>报废</font>";
				}else if (data==4 || data=="4") {
					return "<font color='#E46D0A'>记录不良</font>";
				}
			}
		}, {
			colkey : "byUser",
			name : "创建人"		
		}, {
			colkey : "createTime",
			name : "工单创建时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "site",
			name : "站点"		
		},{		
			colkey : "sfc",
			name : "装配信息",
				renderData : function(rowindex,data, rowdata, column) {
					var assemblyNum = 0;
					if(rowdata.assemblyNum == null || rowdata.assemblyNum == ""){
						assemblyNum = 0;
					}	
					else{
						assemblyNum = rowdata.assemblyNum;
					}
					return "<a href='javascript:;' onClick=\"showAssemblyInfo('"+data+"','"
					+rowdata.operation_no+"');\">装配信息&nbsp;<span class='badge pull-right'>"
					+ assemblyNum+"</span></a>";
			}
		},{		
			colkey : "sfc",
			name : "不良信息",
			renderData : function(rowindex,data, rowdata, column) {
				var ncNum = 0;
				if(rowdata.ncNum == null || rowdata.ncNum == ""){
					ncNum = 0;
				}
				else{
					ncNum = rowdata.ncNum;
				}
				return "<a href='javascript:;' onClick=\"showNcInfo('"+data+"','"
				+rowdata.operation_no+"');\">不良信息&nbsp;<span class='badge pull-right'>"
				+ ncNum + "</span></a>";
			}
		}, {
			colkey : "assemblyNum",
			name : "装配数量",
			hide : true
		}, {
			colkey : "ncNum",
			name : "不良数量",
			hide : true
		}],
		jsonUrl : rootPath + list_url,
		dymCol:true,
		pageSize:20,
		checkbox : false,
		serNumber : false
	});	
});

function showAssemblyInfo(sfc,operation){
	window.open(rootPath + "/report/get_productionRecord_sfc_assembly_list.shtml?sfc="+sfc+"&operation="+operation);
}

function showNcInfo(sfc,operation){	
	window.open(rootPath + "/report/get_productionRecord_sfc_nc_list.shtml?sfc="+sfc+"&operation="+operation);
}
