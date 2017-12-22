<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>ECharts</title>
<script type="text/javascript">
</script>
</head>
<body style="height: 100%; margin: 0" onload="alert('111！');">		
		<!-- 报表div -->
		<div style="height:10px"></div>
		<div id="container" style="height: 75%; width: 100%; y:'10%'; border: 2px solid #E5EDF2;"></div>
		<script type="text/javascript">
			var dom = document.getElementById("container");
			var myChart = echarts.init(dom);
			myChart.showLoading();
			var app = {};
			option = null;
	
	
			option = {
					//backgroundColor:'#B0C4DE',
					backgroundColor:{
						type: 'linear',
					    x: 0,
					    y: 0,
					    x2: 0,
					    y2: 1,
					    colorStops: [{
					        offset: 0, color: '#F0F0F0' // 0% 处的颜色
					    }, {
					        offset: 1, color: '#B0C4DE' // 100% 处的颜色
					    }],
					    globalCoord: false // 缺省为 false
					},
				    color: ['#8600df'],
				    tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },				    
					legend : {
						data:['库存数量','安全库存数'],
						x : '80%', 
						y : '1%'
					},
				    title:{
				    	text:"车间库存报表",
				          x: '45%',
				          //y:'25px'
				          y : '1%'
				    	},
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    xAxis : [
				        {
				            type : 'category',
				            data : [],
				            axisTick: {
				                alignWithLabel: true
				            }
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				            //min : 0,
				            //max : function(value) {
					               // return value.max + 5;
				            //}
				        }
				    ],
				    series : [
				        {
				        	name:'库存数量',
				            type:'bar',
				            barWidth: '20%',
				            color: ['#7EC0EE'],
				            data:[]
				        },
				        {
				        	name:'安全库存数',
				            type:'line',
				            color: ['#8968CD'],
				            linestytle:{
				            	normal:{
				            		width:10,
				            		type:'solid',
				            		shadowColor: 'rgba(0, 0, 0, 0.5)',
				            	    shadowBlur: 10,
				            	    shadowOffsetY : 5
				            	}
				            },
				            label:{
				            	normal:{show:true}
				                  },
				            data:[]
				        }
				    ]
				};
			myChart.showLoading();
			var data = $("#report_scheduler_form").serialize();
			data = 'formMap.reportMethod=getInventoryData&'+data
			$.ajax({
				type: 'POST', 
				data: data, 
				url: '/IE-MES/report/getReportData.shtml',
		        success: function (e) {
		        	 var data= JSON.parse(e);
		        	 if (data.result) {
		        		var newoption = myChart.getOption();
		    			var xAxisData = [];
		    			var yAxisData = [];
		    			var yAxisData2 = []; 
		        		for (var d in data.data){
		        			xAxisData.push(data.data[d].item);
		        			yAxisData.push(data.data[d].kc);
		        			yAxisData2.push(data.data[d].balance_down);
		        		}

		    			//结存
		        		newoption.series[0].data=yAxisData;		        		
		    			newoption.xAxis[0].data = xAxisData;
		    			//安全数
		    			newoption.series[1].data=yAxisData2;
		    			
		         		myChart.hideLoading();
		        		myChart.setOption(newoption);

		        	}else {
		        		layer.msg("执行失败："+data.message);
		        	}
		        },
		        error: function (xhr,e) {
		        	layer.alert('请求失败,请联系管理员');
		        } 
		    });
			myChart.hideLoading();
			if (option && typeof option === "object") {
				myChart.setOption(option, true);
			}
		</script>
		<!-- 报表div -->
	
</body>
</html>