<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/workshopdata/site/site_edit.js">
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
		action="${ctx}/workshop/site_editEntity.shtml">
		<input type="hidden" class="form-control checkacc" value="${site.id}"
			name="siteFormMap.id" id="id">
		<section class="panel panel-default">
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-3 control-label">站点编号</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkabh" readonly="readonly"
							placeholder="请输入站点编号" name="siteFormMap.site" id="site" value="${site.site}"/>
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">站点名称</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkName"
							placeholder="请输入站点名称" name="siteFormMap.siteName" id="siteName" value="${site.siteName}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">描述</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入站点描述"
							name="siteFormMap.siteDescription" id="siteDescription" value="${site.siteDescription }">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">是否可用</label>
					<div class="col-sm-9">
						<select class="form-control" name="siteFormMap.state">
							<option value="1" <c:if test="${site.state eq 1}"> checked="checked"</c:if>>是</option>
							<option value="0" <c:if test="${site.state eq 0}"> checked="checked"</c:if>>否</option>
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