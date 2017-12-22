<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>ECharts</title>
<script type="text/javascript">
	function getData() {
		if (!CommnUtil.notNull($('#scheduler_input').val())) {
			layer.msg("工单不能为空");
			return;
		}
		$("#container").show();
		var dom = document.getElementById("container");
		var myChart = echarts.init(dom);
		myChart.showLoading();
		var app = {};
		option = null;

		app.title = '嵌套环形图';

		option = {
				color:['#A2CD5A','#8B8970',	'#696969'],
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
			title : [ {
				text : '生产进度报表',
				x : '45%',
			}, {
				text : '当天生产情况',
				x : '25%',
				y : '12%',
				textAlign : 'center'
			}, {
				text : '生产进度',
				x : '75%',
				y : '12%',
				textAlign : 'center'
			} ],
			tooltip : {},
			grid : [ {
				top : 200,
				width : '50%',
				bottom : '25%',
				left : 35,
				containLabel : true
			} ],
			xAxis : [ {
				type : 'category',
				data : [],
				axisTick : {
					alignWithLabel : true
				}
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				gridIndex : 1,
				name : '生产进度',
				type : 'pie',
				radius : '45%',
				center : [ '75%', '45%' ],
				data : [],
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
						
				}
			}, {
				color : [ '#7EC0EE' ],
				name : '已生产',
				type : 'bar',
				barWidth : '60%',
				data : []
			} ]
		};

		myChart.hideLoading();

		if (option && typeof option === "object") {
			myChart.setOption(option, true);
		}

		myChart.showLoading();

		var data = $("#report_scheduler_form").serialize();
		data = 'formMap.reportMethod=getScheduleReportData&' + data
		$.ajax({
			type : 'POST',
			data : data,
			url : '/IE-MES/report/getReportData.shtml',
			success : function(e) {
				var data = JSON.parse(e);
				if (data.result) {
					var newoption = myChart.getOption();
					var xAxisData = [];
					var yAxisData = [];
					if (data.data.length == 0) {
						layer.msg("未查询到该工单下的数据");
						myChart.hideLoading();
						newoption.series[0].data = [];
						newoption.xAxis[0].data = [];
						newoption.series[1].data = [];
						myChart.setOption(newoption);
						return;
					}
					var total = data.data[0].all;
					//var done = 0;
					var done = data.data[0].value;
					var scrapValue = data.data[0].scrapvalue;
					
					//for ( var i = 0; i < data.data.length; i++) {
						// done += data.data[i].value;
					//}

					 /*if (data.data.length > 7) {
						var a = data.data.slice(data.data.length - 7);
						for ( var i = 0; i < a.length; i++) {
							if (a[i].name != null) {
								xAxisData.push(a[i].name);
								yAxisData.push(a[i].value);
							}

						}
					} else {
						for ( var i = 0; i < 7 && i < data.data.length; i++) {
							if (data.data[i].name != null) {
								xAxisData.push(data.data[i].name);
								yAxisData.push(data.data[i].value);
							}
						}
					}*/
					
					var todayDoneNum = 0;

					for ( var i = 0;i < data.data.length; i++) {
						if (data.data[i].name != null) {
							xAxisData.push(data.data[i].name);
							yAxisData.push(data.data[i].rangeDoneNum);
							
							todayDoneNum += data.data[i].rangeDoneNum;
						}
					}

					newoption.title[1].subtext = "工单总计:" + data.data[0].all
							+ ";当天生产：" + todayDoneNum;
					var jsondata = [
							{
								value : done,
								name : '已生产('
										+ (done / total * 100).toPrecision(3)
										+ '%)'
							},
							{
								value : (total - done -scrapValue),
								name : '未生产('
										+ ((total - done - scrapValue) / total * 100)
												.toPrecision(3) + '%)'
							},
							{
								value : scrapValue,
								name : '报废('
										+ (scrapValue / total * 100)
												.toPrecision(3) + '%)'
							}];
					newoption.series[0].data = jsondata;
					newoption.xAxis[0].data = xAxisData;
					newoption.series[1].data = yAxisData;
					myChart.hideLoading();
					myChart.setOption(newoption);

				} else {
					layer.msg("执行失败：" + data.message);
				}
			},
			error : function(xhr, e) {
				layer.alert('请求失败,请联系管理员');
			}
		});
	}

	function reset() {
		$('#report_scheduler_form').reset();
	}
</script>
</head>
<body style="height: 100%; margin: 0" onload="init()">

	<!-- 查询条件div -->
	<div style="border: 2px solid #E5EDF2;">
		<form id="report_scheduler_form" name="report_scheduler_form"
			class="form-horizontal">
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-5 control-label">工单</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请选择工单"
								id="scheduler_input" name="formMap.scheduler"> <span
								class="input-group-btn">
								<button class="btn btn-info" id="scheduler" type="button"
									style="height: 34px;"
									onclick="getWorkOrder('scheduler_input','shoporder_no');">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group" align="center">
					<button type="button" class="btn btn-success btn-s-xs"
						onclick="getData()">查询</button>
					<button type="button" class="btn btn-success btn-s-xs"
						onclick="reset()">清空</button>
				</div>
			</div>
		</form>
	</div>
	<!-- 查询条件div -->

	<!-- 报表div -->
	<div style="height: 10px"></div>
	<div id="container"
		style="height: 75%; width: 100%; border: 2px solid #E5EDF2;"></div>
	<!-- 		<script type="text/javascript">

		</script> -->
	<!-- 报表div -->

</body>
</html>