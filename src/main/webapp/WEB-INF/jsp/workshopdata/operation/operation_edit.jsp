<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf"%>
<script type="text/javascript"
	src="${ctx}/js/workshopdata/operation/operation_edit.js">
	
</script>
<style type="text/css">
.col-sm-3 {
	width: 15%;
	float: left;
}

.col-sm-9 {
	width: 85%;
	float: left;
}

label[class^="btn btn-default"] {
	margin-top: -4px;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/workshop/operation_editEntity.shtml">
		<input type="hidden" class="form-control checkacc"
			value="${operation.id}" name="operationFormMap.id" id="id">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">所属站点</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" placeholder="请输入所属站点"
						name="operationFormMap.site" id="site"
						value="${sessionScope.site }" readonly>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">操作编号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkabh"
						placeholder="请输入操作编号" name="operationFormMap.operation_no"
						id="operation_no" value="${operation.operation_no}" readonly />
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">操作描述</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkName"
						placeholder="请输入站点名称"
						name="operationFormMap.operation_description"
						id="operation_description"
						value="${operation.operation_description}">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">操作类型</label>
				<div class="col-sm-9">
					<select class="form-control" name="operationFormMap.operation_type">
						<option value="common"
							<c:if test="${operation.operation_type eq 'common'}"> SELECTED</c:if>>正常</option>
						<option value="repair"
							<c:if test="${operation.operation_type eq 'repair'}"> SELECTED</c:if>>维修</option>
						<option value="test"
							<c:if test="${operation.operation_type eq 'test'}"> SELECTED</c:if>>测试</option>
					</select>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">资源类型</label>
				<div class="col-sm-9">
					<select class="form-control" name="operationFormMap.resoucre_type">
						<option value="default">默认</option>
					</select>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">默认资源</label>
				<div class="col-sm-9">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="请输入默认资源"
							name="operationFormMap.default_resource" id="default_resource"
							value="${operation.default_resource}"> <span
							class="input-group-btn">
							<button class="btn btn-info" id="workorder" type="button"
								style="height: 34px;"
								onclick="getResource('default_resource','operationResource_no');">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">所属工作中心</label>
				<div class="col-sm-9">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="请输入工作中心"
							name="operationFormMap.workcenter" id="workcenter"
							value="${operation.workcenter}"> <span
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
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<div id="pod_button_list"
					data-url="/produce/selButton.shtml?podButtonFormMap.operation_no=${operation.id}"></div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">是否可用</label>
					<div class="col-sm-9">
						<select class="form-control" name="operationFormMap.state">
							<option value="1"
								<c:if test="${operation.state eq 1}"> SELECTED</c:if>>是</option>
							<option value="0"
								<c:if test="${operation.state eq 0}"> SELECTED</c:if>>否</option>
						</select>
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
			</div>
			<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交1</button>
			</footer>
		</div>
		</section>
	</form>
	<script type="text/javascript">
		onloadurl();
	</script>
</body>
</html>