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
		action="${ctx}/test2/sukey_editEntity.shtml">
		<input type="hidden" class="form-control checkacc" value="${sukey.id}"
			name="sukeyFormMap.id" id="id">
		<section class="panel panel-default">
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-3 control-label">sukey姓名</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkabh"
							placeholder="请输入sukey姓名" name="sukeyFormMap.name" id="name" value="${sukey.name}"/>
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">sukey名</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkName"
							placeholder="请输入sukey名" name="sukeyFormMap.firstName" id="firstName" value="${sukey.firstName}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">sukey姓</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入sukey姓"
							name="sukeyFormMap.lastName" id="lastName" value="${sukey.lastName }">
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