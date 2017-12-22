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
		src="${ctx}/js/report/sfc_assembly_info_trace/sfc_assembly_info_query.js"></script>
</head>
<body id="report_test_body" style="height: 100%; margin: 0">

	<!-- 查询条件div -->
	<div style="border: 2px solid #E5EDF2;">
		<form id="report_test_form" name="report_form" id="report_form"
			class="form-horizontal">
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-5 control-label" for="ds_name"><font color="red">*</font>SFC</label>
					<div class="col-sm-2">
						<input class="form-control" id="sfc" name="sfc" type="text" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-5 control-label" for="ds_host">操作</label>
					<div class="col-sm-2">
						<input class="form-control" id="operation" name="operation" type="text" />
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