var pageii = null;
var grid = null;
$(document).ready(function() {	
//	基于准备好的dom，初始化echarts实例
	var myChart1 = echarts.init(document.getElementById('main1'));
	var myChart2 = echarts.init(document.getElementById('main2'));
	var myChart3 = echarts.init(document.getElementById('main3'));
	// 指定图表的配置项和数据
	option1 = {
			title : {
				text: '不良统计',

			},
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:['工单数量','不良数量']
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
					dataView : {show: true, readOnly: false},
					magicType : {show: true, type: ['line']},
					restore : {show: true},
					saveAsImage : {show: true}
				}
			},
			calculable : true,
			xAxis : [
			         {
			        	 type : 'category',
			        	 boundaryGap : false,
			        	 data : []  //工单
			         }
			         ],
			         yAxis : [
			                  {
			                	  type : 'value'
			                  }
			                  ],
			                  series : [
{
	name:'不良数量',
	type:'line',
	data:[],  // 不良数量
},
{
	name:'工单数量',
	type:'line',
	data:[],  // 工单数量
}

]
	};
	if (option1 && typeof option1 === "object") {
		myChart1.setOption(option1, true);
	}
	myChart1.showLoading();
	$.ajax({
		type : 'POST',
		data:'formMap.reportMethod=getShopOrderAndNcData',
		url : rootPath+'/report/getReportData.shtml',
		success : function(e) {
			var data = JSON.parse(e);
			var jsonData = data.data;
			if (data.result) {
				var newoption = myChart1.getOption();
				var xAxis_data = [];
				var serie_data = [];
				var serie_data1 = [];
				for (var i = 0;i<jsonData.length;i++) {
					xAxis_data.push(jsonData[i].shoporder_no);
					serie_data.push(jsonData[i].shoporder_numbers);
					serie_data1.push(jsonData[i].nc);
				}
				newoption.series[1].data = serie_data;
				newoption.series[0].data = serie_data1;
				newoption.xAxis[0].data = xAxis_data;
			}
			myChart1.hideLoading();
			myChart1.setOption(newoption);
		},
		error : function(xhr, e) {
			layer.alert('请求失败,请联系管理员');
		}
	});
	// 使用刚指定的配置项和数据显示图表。
	//myChart1.setOption(option1);

	option2 = {
			title : {
				text: '工单进度',
			},
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:['完成数量','工单数量']
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
					dataView : {show: true, readOnly: false},
					magicType : {show: true, type: ['bar','line']},
					restore : {show: true},
					saveAsImage : {show: true}
				}
			},
			calculable : true,
			xAxis : [
			         {
			        	 type : 'category',
			        	 data : [] // 工单
			         }
			         ],
			         yAxis : [
			                  {
			                	  type : 'value'
			                  }
			                  ],
			                  series : [
			                            {
			                            	name:'完成数量',
			                            	type:'bar',
			                            	data:[],  // 完成数量
			                            },
			                            {
			                            	name:'工单数量',
			                            	type:'bar',
			                            	data:[], //工单数量
			                            }
			                            ]
	};
	if (option2 && typeof option2 === "object") {
		myChart2.setOption(option2, true);
	}
	myChart2.showLoading();
	$.ajax({
		type : 'POST',
		data:'formMap.reportMethod=getShopOrderAndCompleteData',
		url : rootPath+'/report/getReportData.shtml',
		success : function(e) {
			var data = JSON.parse(e);
			var jsonData = data.data;
			if (data.result) {
				var newoption = myChart2.getOption();
				var xAxis_data = [];
				var serie_data = [];
				var serie_data1 = [];
				for (var i = 0;i<jsonData.length;i++) {
					xAxis_data.push(jsonData[i].shoporder_no);
					serie_data.push(jsonData[i].shoporder_numbers);
					serie_data1.push(jsonData[i].complete);
				}
				newoption.series[1].data = serie_data;
				newoption.series[0].data = serie_data1;
				newoption.xAxis[0].data = xAxis_data;
			}
			myChart2.hideLoading();
			myChart2.setOption(newoption);
		},
		error : function(xhr, e) {
			layer.alert('请求失败,请联系管理员');
		}
	});
	//myChart2.setOption(option2);


	option3 = {
			title : {
				text: '物料库存',
				x:'center'
			},
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient : 'vertical',
				x : 'left',
				data:[]
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
					dataView : {show: true, readOnly: false},
					magicType : {
						show: true, 
						type: ['pie', 'funnel'],
						option: {
							funnel: {
								x: '25%',
								width: '50%',
								funnelAlign: 'left',
								max: 1548
							}
						}
					},
					restore : {show: true},
					saveAsImage : {show: true}
				}
			},
			calculable : true,
			series : [
			          {
			        	  name:'物料库存',
			        	  type:'pie',
			        	  radius : '55%',
			        	  center: ['50%', '60%'],
			        	  data:[]
			          }
			          ]
	};

	if (option3 && typeof option3 === "object") {
		myChart3.setOption(option3, true);
	}
	myChart3.showLoading();
	$.ajax({
		type : 'POST',
		data:'formMap.reportMethod=getInventoryData',
		url : rootPath+'/report/getReportData.shtml',
		success : function(e) {
			var data = JSON.parse(e);
			var jsonData = data.data;
			if (data.result) {
				var newoption = myChart3.getOption();
				var legend_data = [];
				var serie_data = [];
				for (var i = 0;i<jsonData.length;i++) {
					legend_data.push(jsonData[i].item_name);
					serie_data.push({
						name: jsonData[i].item_name,
						value: jsonData[i].kc
					});
				}
				newoption.series[0].data = serie_data;
				newoption.legend[0].data = legend_data;
			}
			myChart3.hideLoading();
			myChart3.setOption(newoption);
		},
		error : function(xhr, e) {
			layer.alert('请求失败,请联系管理员');
		}
	});
	//myChart3.setOption(option3);


	grid2 = lyGrid({
		pagId : 'paging',
		l_column : [  {
			colkey : "shoporder_no",
			name : "工单编号",
		}, {
			colkey : "shoporder_numbers",
			name : "订单数量",
		}, {
			colkey : "shoporder_item",
			name : "物料编号",
		}, {
			colkey : "process_route",
			name : "工艺路线",
		}, {
			colkey : "shoporder_status",
			name : "工单状态",
			renderData: function(rowindex,data, rowdata, column) {
				switch(data)
				{
				case "0":
					return "未开始";
					break;
				case "1":
					return "正在进行";
					break;
				case "2":
					return "已完成";
					break;
				}
			}
		},{
			colkey : "shoporder_start_date",
			name : "订单开始时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd ");
			}
		}, {
			colkey : "shoporder_end_date",
			name : "订单完成时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd ");
			}
		}],
		jsonUrl : rootPath + '/product_plan/shoporder_maintenance_findByPage.shtml',
		dymCol:true,
		checkbox : false,
		serNumber : true,
		usePage : true,// 是否分页
		pageSize : 5, // 每页显示多少条数据
	});

});
