<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>forwardTracingReport</title>
<script type="text/javascript"
		src="${ctx}/js/report/productionRecord_report/productionRecord_control.js"></script>
</head>
<body id="report_test_body" style="height: 100%; margin: 0">

	<!-- 查询条件div -->
	<div style="border: 2px solid #E5EDF2;">
		<form id="report_test_form" name="report_form" id="report_form"
			class="form-horizontal">
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-2 control-label" for="ds_host">工单</label>
					<div class="col-sm-2">
						<input class="form-control" id="shoporder" name="shoporder" type="text" />
					</div>
					<label class="col-sm-1 control-label" for="ds_name">工单状态</label>
					<div class="col-sm-2">
						<select id="disabledSelect" class="form-control" id="status" name="status">
							<option value="">请选择工单状态</option>
							<option value="0">创建</option>
							<option value="1">已下达</option>
							<option value="2">生产中</option>
							<option value="3">已完成</option>
							<option value="4">关闭</option>
							<option value="5">挂起</option>
							<option value="6">已删除</option>
						</select>
					</div>
					<label class="col-sm-1 control-label" for="ds_name">SFC</label>
					<div class="col-sm-2">
						<input class="form-control" id="SFC" name="SFC" type="text" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="ds_host">工单创建时间从</label>
					<div class="col-sm-2">
						<input class="form-control" id="start_time" name="start_time" type="text" />
					</div>
					<label class="col-sm-1 control-label" for="ds_name">至</label>
					<div class="col-sm-2">
						<input class="form-control" id="end_time" name="end_time" type="text" />
					</div>
					<label class="col-sm-1 control-label" for="ds_name">物料批次</label>
					<div class="col-sm-2">
						<input class="form-control" id="batch" name="batch" type="text" />
					</div>
				</div>
				<div class="form-group" align="center">
					<button type="button" class="btn btn-success btn-s-xs"
						onclick="getData()">查询</button>
					<button type="button" class="btn btn-success btn-s-xs"
						onclick="reset()">清空</button>
				</div>
			</div>
		</form>
	</div>
	<!-- 查询条件div -->

	<div id="container1"
		style="height: 60%; width: 100%; border: 2px solid #E5EDF2; float: left;"></div>
</body>
</html>