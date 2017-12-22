<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/qc/nc/nc_edit.js">
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
label[class^="btn btn-default"]{
	margin-top: -4px;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/qc/nc_editEntity.shtml">
		<input type="hidden" class="form-control checkacc" value="${ncCode.id}"
			name="ncCodeFormMap.id" id="id">
		<section class="panel panel-default">
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-3 control-label">所属站点</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入所属站点"
							name="ncCodeFormMap.site" id="site" value="${sessionScope.site }" readonly>
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">不良代码编号</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkabh"
							placeholder="请输入不良代码编号" name="ncCodeFormMap.nc_code" id="nc_code"
							value="${ncCode.nc_code }" readonly="readonly">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">不良代码描述</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkName"
							placeholder="请输入不良代码描述" name="ncCodeFormMap.nc_code_desc" id="nc_code_desc"
							value="${ncCode.nc_code_desc }">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">所属不良代码组</label>
					<div class="col-sm-9">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="请输入不良代码组"
							name="ncCodeFormMap.nc_code_group_no" id="nc_code_group_no"
							value="${ncCode.nc_code_group_no }" readonly="readonly">
						<span class="input-group-btn">
							<button class="btn btn-info" id="workorder" type="button"
								style="height: 34px;"
								onclick="getNcCodeGroup('nc_code_group_no','nc_code_group_no');">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
						</div>
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">是否可用</label>
					<div class="col-sm-9">
						<select class="form-control" name="ncCodeFormMap.state">
							<option value="1" <c:if test="${ncCode.state eq 1}"> SELECTED</c:if>>是</option>
							<option value="0" <c:if test="${ncCode.state eq 0}"> SELECTED</c:if>>否</option>
						</select>
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
			</div>
			<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
	</section>
	</form>
</body>
</html>