<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/production_process/pod_panel/assemble_nc_code.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>POD</title>
</head>
<body>
	<ul class="nav nav-pills nav-stacked">
		<li class="active"><a href="#">记录不良</a></li>
	</ul>
	<div class="panel panel-default">	
		<div class="contentPanel">
			<form class="form-horizontal" role="form" id="recode_nccode_form" name="recode_nccode_form">
				<input type="hidden" id="site" name="formMap.site" value="${sessionScope.site }">
			  <div class="form-group">
			    <label class="col-sm-3">不合格客户端：记录不合格客户端</label>
			    <label class="col-sm-3 control-label">车间作业控制&nbsp;&nbsp;已选择</label>
			    <label class="col-sm-3 control-label">车间作业控制：${res.ds_username}</label>
			    <label class="col-sm-3 control-label">1/1</label>
			  </div>

				<div class="form-group">
					<label class="col-sm-2 control-label">不良代码组</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请选择不良代码组"
								name="formMap.nc_code_group_no" id="nc_code_group_no">
							<span class="input-group-btn">
								<button class="btn btn-info" id="workorder" type="button"
									style="height: 34px;"
									onclick="getNcCodeGroup('nc_code_group_no','nc_code_group_no');">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</div>
					<div class="col-sm-2"/>
					<label class="col-sm-2 control-label">不良代码</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请选择不良代码"
								name="formMap.nc_code_no" id="nc_code_no">
							<span class="input-group-btn">
								<button class="btn btn-info" id="nc_code_bt" type="button"
									style="height: 34px;"
									onclick="getNcCode('nc_code_no','nc_code');">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</div>
				</div>

				<div align="center">
				<button type="button" class="btn btn-primary" onclick="recode();">记录不合格</button>
				<button type="button" class="btn btn-primary" id="assembling_canner();" onclick="close_task_div();">关闭</button>
			</div>
			</form>
		</div>	
	</div>
</body>
</html>