var pageii = null;
var grid = null;
$(function() {
	var list_url = '/kanban/getMaterialConsumptionDataFindByPage.shtml';
	//展示列：物料编号、物料名称、库存总数、消耗数量、结存数量、库存上限、安全库存数。
	//调整列宽
	//Modify 2017/08/31
	grid = lyGrid({
		pagId : 'material_table',
		l_column : [ {
			colkey : "item",
			name : "物料编号",
		    width : "30%"
		}, {
			colkey : "item_desc",
			name : "物料名称",
		    width : "30%"
		}, {
			colkey : "zs",
			name : "库存总数"
		},{
			colkey : "us",
			name : "消耗数量"
		}, {
			colkey : "kc",
			name : "结存数量",
		},{
			colkey : "balance_up",
			name : "库存上限"
		},{
			colkey : "balance_down",
			name : "安全库存数",
			width : "15%"
		}],
		jsonUrl : rootPath + list_url,
		dymCol:true,
		pageSize:20,
		usePage:false,
		checkbox : false,
		serNumber : false
	});
	$("#material_table tr th").css({"color":"white","background-color":"red","font-size":"25px"});
	$("#material_table tr td").css({"color":"white","background-color":"black","font-size":"25px"});
	setInterval("updateData()",10000);
	setInterval("updateTime()",1000);
});

function p(s) {
    return s < 10 ? '0' + s: s;
}

function updateData(){
	grid.loadData();
	$("#material_table tr th").css({"color":"white","background-color":"red","font-size":"25px"});
	$("#material_table tr td").css({"color":"white","background-color":"black","font-size":"25px"});
}

function updateTime(){
	var myDate = new Date();
	//获取当前年
	var year=myDate.getFullYear();
	//获取当前月
	var month=myDate.getMonth()+1;
	//获取当前日
	var date=myDate.getDate(); 
	var h=myDate.getHours();       //获取当前小时数(0-23)
	var m=myDate.getMinutes();     //获取当前分钟数(0-59)
	var s=myDate.getSeconds();  

	var now = year+'-'+p(month)+"-"+p(date)+" "+p(h)+':'+p(m)+":"+p(s);
	
	//设置 kanban_date 的值
	var kanban_date = $("#kanban_date").html(now); 
}
