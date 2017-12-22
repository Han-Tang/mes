<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.table-striped tbody tr.clickTr:nth-child(odd) td {
  background-color: rgb(34, 153, 34);
}
.clickTr{
background: rgb(34, 153, 34);
}

</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/production_process/pod_panel/assemble_pod.js"></script>
<title>POD</title>
</head>
<body>
	<div class="contentPanel">
		<!-- 条件层 -->
		<form class="form-horizontal" role="form" id="process_condition_form">
			<fieldset>
				<div class="form-group">
					<label class="col-sm-1 control-label" for="ds_host">工单</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" class="form-control" name="formMap.workorderNo" id="workorder_input"> <span
								class="input-group-btn">
								<button class="btn btn-info" id="workorder" type="button"
									style="height: 34px;"
									onclick="getWorkOrder('workorder_input','shoporder_no');">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</div>
					<label class="col-sm-2 control-label" for="ds_host">操作</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" class="form-control" id="operation_input" name="formMap.operation" > <span
								class="input-group-btn">
								<button class="btn btn-info" id="operation" type="button" style="height: 34px;" 
								onclick="getOperation2('operation_input','operation_no');">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label" for="ds_name">资源</label>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="text" class="form-control" id="resource_input" name="formMap.resource"> <span
									class="input-group-btn">
									<button class="btn btn-info" id="operation_resource" type="button" style="height: 34px;" 
									onclick="getResource('resource_input','operationResource_no');">
										<span class="glyphicon glyphicon-search"></span>
									</button>
								</span>
							</div>
						</div>
					<label class="col-sm-2 control-label" for="ds_username">车间作业控制</label>
					<div class="col-sm-4">
						<input class="form-control" id="wc_sfc" type="text" name="formMap.wc_sfc"/>
					</div>
				</div>
			</fieldset>

			<ul class="nav nav-list">
				<li class="divider"></li>
			</ul>
		</form>

		<!-- 按钮层 -->
		<div align="center" id="button_active">
			 <!-- <button type="button" class="btn btn-default" onclick="assemblingPass();">通过</button>
			<button type="button" class="btn btn-primary" onclick="assemblingStart();">开始</button>
			<button type="button" class="btn btn-success" onclick="assemblingFinish();">完成</button>
			<button type="button" class="btn btn-info" onclick="assemblingUI();">装配</button>
			<button type="button" class="btn btn-warning" onclick="recode_nccode_ui()">记录不良</button>  -->
		</div> 
	</div>
	
	<!-- 列表展示层 -->
	<div id="center_contpanel"></div>
	
	<!-- 列表展示层2 -->
	<div id="center_contpanel2"></div>
	
	<!-- 操作层 -->
	<div id="pod_task_div"></div>	
</body>
</html>