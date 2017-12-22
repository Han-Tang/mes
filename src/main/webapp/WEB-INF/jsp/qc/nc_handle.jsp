<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="${ctx}/IE-MES/js/qc/nc_handle.js"></script>
<title>nc_handle</title>
</head>
<body id="report_test_body" style="height: 100%; margin: 0">

	<!-- 查询条件div -->
	<div style="border: 2px solid #E5EDF2;">
		<form id="nc_handle_form" name="nc_handle_form"
			class="form-horizontal">
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-5 control-label">产品SFC:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" placeholder="请输入SFC"
							id="sfc_input" name="formMap.sfc">
					</div>
				</div>

				<div class="form-group" align="center">
					<button type="button" class="btn btn-success btn-s-xs"
						onclick="getData()">查询</button>
				</div>
			</div>
		</form>
	</div>

	<div id="contains" style="display:none;"></div>
</body>
</html>