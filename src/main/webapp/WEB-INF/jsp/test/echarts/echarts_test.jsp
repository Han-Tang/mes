<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>ECharts</title>
<script type="text/javascript">
function getData(){
	myChart.showLoading();
	var data = $("#report_test_form").serialize();
	data = 'formMap.reportMethod=getTestReportData&'+data
	$.ajax({
		type: 'POST', 
		data: data, 
		url: '/IE-MES/report/getReportData.shtml',
        success: function (e) {
        	var data= JSON.parse(e);
        	if (data.result) {
        		layer.msg("操作成功");
        		var series = [];
        		var xAxis_data = [];
        		var serie_data = [];
        		var serie_data2 = [];
        		
        		for (var d in data.data){
        			xAxis_data.push(data.data[d].item);
        			serie_data.push(data.data[d].getnum);
        			serie_data2.push(data.data[d].usenum);
        		}
        		var series1 = new Object();
        		var series2 = new Object();
        		var series3 = new Object();
        		var series4 = new Object();
        		
        		series1.name = '接收数量-柱';
        		series1.type = 'bar';
        		series1.data = serie_data;
        		
        		series2.name = '消耗数量-柱';
        		series2.type = 'bar';
        		series2.data = serie_data2;
        		
        		series3.name = '接收数量-线';
        		series3.type = 'line';
        		series3.data = serie_data;
        		
        		series4.name = '消耗数量-线';
        		series4.type = 'line';
        		series4.data = serie_data2;
        		
        		series.push(series1);
        		series.push(series2);
        		series.push(series3);
        		series.push(series4);
        		
        		var newoption = myChart.getOption();
        		newoption.legend[0].data = ['接收数量-柱','消耗数量-柱','接收数量-线','消耗数量-线'];	//X轴
        		newoption.xAxis[0].data = xAxis_data;	//X轴
        		newoption.series = series;
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
}

function reset(){
	$('#report_test_form').reset();
}
</script>
</head>
<body id="report_test_body" style="height: 100%; margin: 0">

	<!-- 查询条件div -->
	<div style="border: 2px solid #E5EDF2;">
		<form id="report_test_form" name="report_test_form"
			class="form-horizontal">
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-5 control-label">车间</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请选择操作"
								id="operation_input" name="formMap.operation"> <span
								class="input-group-btn">
								<button class="btn btn-info" id="operation" type="button"
									style="height: 34px;"
									onclick="getOperation('operation_input','operation_no');">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-5 control-label">车间</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请选择操作"
								id="operation_input" name="formMap.operation"> <span
								class="input-group-btn">
								<button class="btn btn-info" id="operation" type="button"
									style="height: 34px;"
									onclick="getOperation('operation_input','operation_no');">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-5 control-label">车间</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请选择操作"
								id="operation_input" name="formMap.operation"> <span
								class="input-group-btn">
								<button class="btn btn-info" id="operation" type="button"
									style="height: 34px;"
									onclick="getOperation('operation_input','operation_no');">
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
		style="height: 60%; width: 100%; border: 2px solid #E5EDF2;"></div>
	<script type="text/javascript">
			var dom = document.getElementById("container");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
	
			app.title = '嵌套环形图';
	
			option = {
			    tooltip : {
			        trigger: 'axis'
			    },
			    toolbox: {
			        show : true,
			        y: 'bottom',
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    legend: {
			        data: []
			    },
			    xAxis : [
			        {
			            type : 'category',
			            splitLine : {show : false},
			            data : []
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            position: 'right'
			        }
			    ],
			    series : []
			};
			if (option && typeof option === "object") {
				myChart.setOption(option, true);
			}
		</script>
	<!-- 报表div -->

</body>
</html>