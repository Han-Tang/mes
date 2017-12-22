<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf"%>
<script type="text/javascript"
	src="${ctx}/js/production_process/pod_button/pod_button_add.js">
	
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
		action="${ctx}/produce/pod_button_addEntity.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">所属站点</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" placeholder="请输入所属站点"
						name="podButtonFormMap.site" id="site"
						value="${sessionScope.site }" readonly>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">pod按钮编号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkabh"
						placeholder="请输入pod按钮编号" name="podButtonFormMap.pod_button_no"
						id="pod_button_no">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">pod按钮名称</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkName"
						placeholder="请输入pod按钮名称" name="podButtonFormMap.pod_button_name"
						id="pod_button_name">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">PDD按钮触发器</label>
				<div class="col-sm-9">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="请输入POD按钮触发器"
							name="podButtonFormMap.pod_function" id="pod_function"> <span
							class="input-group-btn">
							<button class="btn btn-info" id="pod_function_no" type="button"
								style="height: 34px;"
								onclick="getPodFunction('pod_function','pod_function_no');">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">POD按钮样式</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" placeholder="请输入POD按钮样式"
						name="podButtonFormMap.pod_icon" id="pod_icon" readonly>
				</div>
			</div>
			<div class="form-group" id="divbut" >
				<label class="col-sm-3 control-label"></label>
				<div class="col-sm-9">
					<div id="but" class="doc-buttons"></div>
				</div>
			</div>

			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">创建人</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" placeholder="请输入创建人"
						name="podButtonFormMap.by_user" id="by_user"
						value="${podButtonFormMap.by_user }" readonly>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">创建时间</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" placeholder="请输入创建时间"
						name="podButtonFormMap.create_time" id="create_time"
						value="${podButtonFormMap.create_time }" readonly>
				</div>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
		<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> </section>
	</form>
</body>
</html>