<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf"%>
<script type="text/javascript"
	src="${ctx}/js/production_process/pod_function/pod_function_edit.js">
	
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
		action="${ctx}/produce/pod_function_editEntity.shtml">
		<input type="hidden" class="form-control checkacc"
			name="podFunctionFormMap.id" id="id" value="${pod_function.id}">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">所属站点</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" placeholder="请输入所属站点"
						name="podFunctionFormMap.site" id="site"
						value="${sessionScope.site }" readonly>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>

			<div class="form-group">
				<label class="col-sm-3 control-label">pod功能编号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkabh"
						value="${pod_function.pod_function_no}" placeholder="请输入pod功能编号"
						name="podFunctionFormMap.pod_function_no" id="pod_function_no">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">POD功能名称</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkName"
						value="${pod_function.pod_function_name}" placeholder="请输入POD功能名称"
						name="podFunctionFormMap.pod_function_name" id="pod_function_name">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">PDD资源路径</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" placeholder="请输入PDD资源路径"
						value="${pod_function.pod_function_url}"
						name="podFunctionFormMap.pod_function_url" id="pod_function_url">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>

			<div class="form-group">
				<label class="col-sm-3 control-label">创建人</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" placeholder="请输入创建人"
						name="podFunctionFormMap.by_user" id="by_user"
						value="${pod_function.by_user }" readonly>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">创建时间</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" placeholder="请输入创建时间"
						name="podFunctionFormMap.create_time" id="create_time"
						value="<fmt:formatDate value="${pod_function.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						readonly>
				</div>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
		<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> </section>
	</form>
</body>
</html>