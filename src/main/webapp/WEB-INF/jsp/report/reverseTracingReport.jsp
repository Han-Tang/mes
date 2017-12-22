
<!-- 请复制整个jsp，修改下面第三行引用的js即可 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
var pageii = null;
var grid = null;
$(function getData() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [{
			colkey : "shoporder_item",
			name : "物料号"
		}, {
			colkey : "item_name",
			name : "物料名称"
		}, {
			colkey : "item_type",
			name : "物料类别"
		}, {
			colkey : "create_time",
			name : "装配时间",
				renderData : function(rowindex,data, rowdata, column) {
					return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		},{
			colkey : "use_num",
			name : "装配数量"
		}, {
			colkey : "workcenter",
			name : "车间"
		}, {
			colkey : "workcenter",
			name : "产线"
		}, {
			colkey : "default_resource",
			name : "资源"
		}, {
			colkey : "byUser",
			name : "操作人"
		},],
		jsonUrl : rootPath + '/report/getReverseTracingData.shtml',
		dymCol:true,
		checkbox : true,
		serNumber : true,
        usePage : false,// 是否分页
	});
	
 	$("#search").click("click", function() {
		var searchParams = $("#report_reverse_form").serializeJson();
		grid.setOptions({
			data : searchParams
		});
		document.getElementById("div").style.display="";
	}); 
});


function reset(){
	$('#report_reverse_form').reset();
}
</script>

<body>
<div class="contentPanel">
	<div class="m-b-md">
		<!-- 查询条件div -->
		<div style="border: 2px solid #E5EDF2;">
			<form id="report_reverse_form" name="report_reverse_form" class="form-horizontal">
				<div class="panel-body">
					<div class="form-group">
						<label class="col-sm-5 control-label">装配SFC</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="请选择SFC" 
									id="reverse_input" name="formMap.reverse"> <span
									class="input-group-btn">
									<button class="btn btn-info" id="reverse" type="button" style="height: 34px;" 
									onclick="getSfcInfo('reverse_input','item_sfc');">
										<span class="glyphicon glyphicon-search"></span>
									</button>
								</span>
							</div>
						</div>
					</div>
					<div class="form-group" align="center">
						<button type="button" class="btn btn-success btn-s-xs" id="search">查询</button>
						<button type="button" class="btn btn-success btn-s-xs" onclick="reset()">清空</button>
					</div>
				</div>
			</form>
		</div>
		<!-- 查询条件div -->

		<!-- 列表展示div -->
		<div id="div" style="border: 2px solid #E5EDF2; display:none">
				<div class="table-responsive" style="margin:25px">
				<div id="paging" class="pagclass"></div>
			</div>
		</div>
	</div>
</body>