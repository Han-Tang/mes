<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>forwardTracingReport</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/report/forward_tracing.js"></script>
</head>
<body id="report_test_body" style="height: 100%; margin: 0">

	<!-- 查询条件div -->
	<div style="border: 2px solid #E5EDF2;">
		<form id="report_test_form" name="report_test_form"
			class="form-horizontal">
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-5 control-label">产品SFC</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入产品SFC"
								name="formMap.SFC" id="SFC_input"> <span
								class="input-group-btn">
								<button class="btn btn-info" id="sfc" type="button"
									style="height: 34px;"
									onclick="getSfc('SFC_input','sfc');">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
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

	<!-- 报表div -->
	<div id="container1"
		style="height: 60%; width: 100%; border: 2px solid #E5EDF2; float: left;"></div>

	<!-- 报表div -->

</body>
</html>