<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>capacityReport</title>
<script type="text/javascript">
	function getData() {
		if (!CommnUtil.notNull($('#shoporder_item').val())) {
			layer.msg("物料不能为空");
			return;
		}
		if (!CommnUtil.notNull($('#workcenter').val())) {
			layer.msg("车间不能为空");
			return;
		}
		if (!CommnUtil.notNull($('#workcenter_input').val())
				&& CommnUtil.notNull($('#resource_input').val())) {
			layer.msg("选择层级为资源时，产线不能为空");
			return;
		}

		$("#container1").show();
		// 初始化内容
		var dom = document.getElementById("container1");
		var myChart = echarts.init(dom);
		var app = {};
		option = null;

		option = {
			title : {
				text : '产能报表',
				x : '40%',
				y : '5%'

			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '产值' ],
				x : '80%',
				y : '5%'
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'bar' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				data : []
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			grid : [ {
				top : 100,
				left : 35,
				containLabel : true
			} ],
			series : [ {
				name : '产值',
				type : 'bar',
				data : [],
				barWidth : 80,
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				}
			} ]
		};

		if (option && typeof option === "object") {
			myChart.setOption(option, true);
		}
		myChart.showLoading();
		var data = $("#report_test_form").serialize();
		$.ajax({
			type : 'POST',
			data : data,
			url : '/IE-MES/capacity_report/getReportData.shtml',
			success : function(e) {
				var data = JSON.parse(e);
				if (data.result) {
					layer.msg("操作成功");
					var newoption = myChart.getOption();
					var series = [];
					var xAxis_data = [];
					var serie_data = [];
					var title_data = [];
					if (data.data != null) {
						for ( var d in data.data) {
							if (data.data[d].fdate != null) {
								xAxis_data.push(data.data[d].fdate);
								serie_data.push(data.data[d].num);
								title_data.push(data.data[d].shoporder_item);
							}

						}
						newoption.series[0].data = serie_data;
						newoption.xAxis[0].data = xAxis_data;
						newoption.title[0].subtext = title_data[0] + "最近产能图";
					}
					newoption.title[0].subtext = $("#shoporder_item").val()
							+ "最近产能图";
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
					<label class="col-sm-1 control-label">产品物料 *</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入产品物料"
								name="formMap.shoporder_item" id="shoporder_item"> <span
								class="input-group-btn">
								<button class="btn btn-info" id="workorder" type="button"
									style="height: 34px;"
									onclick="getItem('shoporder_item','item_no');">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</div>

					<label class="col-sm-2 control-label">产线</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入产线编号"
								name="formMap.workcenter2" id="workcenter_input"> <span
								class="input-group-btn">
								<button class="btn btn-info" id="workorder2" type="button"
									style="height: 34px;"
									onclick="getfWorkCenterChild('workcenter_input','workcenter_no');">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label" for="ds_host">车间 *</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入车间编号"
								name="formMap.workcenter" id="workcenter"> <span
								class="input-group-btn">
								<button class="btn btn-info" id="workorder" type="button"
									style="height: 34px;"
									onclick="getfWorkCenter('workcenter','workcenter_no');">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</div>
					<label class="col-sm-2 control-label" for="ds_name">资源</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" class="form-control" id="resource_input"
								placeholder="请选择资源" name="formMap.resource"> <span
								class="input-group-btn">
								<button class="btn btn-info" id="operation_resource"
									type="button" style="height: 34px;"
									onclick="getResource('resource_input','operationResource_no');">
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
	<div style="height: 10px;"></div>

	<div id="container1"
		style="height: 60%; width: 100%; border: 2px solid #E5EDF2; float: left;"></div>

	<!-- 报表div -->

</body>
</html>