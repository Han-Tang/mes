<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/production_process/pod_panel/assemble_pod_list.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>POD</title>
</head>
<body>
	<ul class="nav nav-pills nav-stacked">
		<li class="active"><a href="#">组件列表</a></li>
	</ul>
	<div class="panel-body">
		<div class="table-responsive">
			<div id="paging2" class="pagclass"></div>
		</div>
	</div>
	<div align="center">
		<button type="button" class="btn btn-primary" onclick="assembling();">装配</button>
		<button type="button" class="btn btn-primary" id="assembling_canner" onclick="assembling_canner()">关闭</button>
	</div>
	<div class="line-down-5h"></div>
</body>
</html>