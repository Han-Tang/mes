<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [{
			colkey : "sfc",
			name : "SFC"
		}, {
			colkey : "shoporderid",
			name : "物料"
		}, {
			colkey : "item_type",
			name : "物料类别"
		}, {
			colkey : "nc_code_group_desc",
			name : "不合格代码组",
		},{
			colkey : "nc_code_desc",
			name : "不合格代码"
		}, {
			colkey : "workcenter_no",
			name : "车间"
		}, {
			colkey : "workcenter",
			name : "产线"
		}, {
			colkey : "default_resource",
			name : "资源"
		}],
		jsonUrl : rootPath + '/report/getUnqualifiedData.shtml',
		dymCol:true,
		checkbox : true,
		serNumber : true,
		usePage : false,// 是否分页
	});
 	$("#search").click("click", function() {
		if (!CommnUtil.notNull($('#scheduler_input').val())) {
			layer.msg("工单不能为空");
			return;
		}
		if (!CommnUtil.notNull($('#workcenter').val())) {
			layer.msg("车间不能为空");
			return;
		}
		var searchParams = $("#report_unqualified_form").serializeJson();
		grid.setOptions({
			data : searchParams
		});
		getData();
		document.getElementById("paging").style.display="";
	}); 
});

function getData(){
	$("#container").show();
	var dom = document.getElementById("container");
	var myChart = echarts.init(dom);
	myChart.showLoading();
	var app = {};
	option = null;

	option = {
			title:{
				text:"不合格工单占比"
			},
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    series : [
		        {
		            name: '工单',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	myChart.hideLoading();
	
	if (option && typeof option === "object") {
		myChart.setOption(option, true);
	}
	myChart.showLoading();

	var data = $("#report_unqualified_form").serialize();
	data = 'formMap.reportMethod=getPercentData&'+data
	$.ajax({
		type: 'POST', 
		data: data, 
		url: '/IE-MES/report/getReportData.shtml',
        success: function (e) {
        	 var data= JSON.parse(e);
        	 if (data.result) {
        		layer.msg("操作成功");
        		var newoption = myChart.getOption();
        		var jsondata = [       
    		               		{value:data.data[0].nc_numbers, name:'不合格'},
    		               		{value:(parseInt(data.data[0].pass_numbers)-data.data[0].nc_numbers), name:'合格'},
    		                ]; 
    			
        		newoption.series[0].data=jsondata;
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
	$('#report_unqualified_form').reset();
}
</script>

<body>
<div class="contentPanel">
	<div class="m-b-md">
		<!-- 查询条件div -->
		<div style="border: 2px solid #E5EDF2;">
			<form id="report_unqualified_form" name="report_unqualified_form"
			class="form-horizontal">
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-1 control-label">工单 *</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请选择工单" 
									id="scheduler_input" name="formMap.scheduler"> <span
									class="input-group-btn">
									<button class="btn btn-info" id="scheduler" type="button" style="height: 34px;" 
									onclick="getWorkOrder('scheduler_input','shoporder_no');">
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
				</div>
				<div class="form-group" align="center">
					<button type="button" class="btn btn-success btn-s-xs" id = "search">查询</button>
					<button type="button" class="btn btn-success btn-s-xs"
						onclick="reset()">清空</button>
				</div>
			</div>
		</form>
		</div>
		<!-- 查询条件div -->

		<!-- 列表展示div -->
		<div>
			<div class="table-responsive" style="margin:5px">
				<div id="container" style="height: 18%; width: 15%; border: 2px solid #E5EDF2;float:left"></div>

				<div id="paging" class="pagclass" style="display:none"></div>
			</div>
		</div>
	</div>
</body>