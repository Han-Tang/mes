<!-- 请复制整个jsp，修改下面第三行引用的js即可 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/welcome.js"></script>
<style type="text/css">
.broken-line {
	width: 100%;
	height: 2px;
	font-size: 0;
	overflow: hidden;
	background-color: transparent;
	border-width: 0;
	border-top: 2px solid #e8e8e8;
	margin-bottom: 15px;
	border-style: dashed;
	background: transparent;
	margin-left: -15px;
	margin-right: -15px;
}

.vertical-broken-line {
	height: 340px;
	width: 2px;
	margin: 10px 0;
	float: left;
	font-size: 0;
	overflow: hidden;
	background-color: transparent;
	border-width: 0;
	margin-top: -15px;
	margin-bottom: -15px;
	margin-left: 15px;
	margin-right: 15px;
	border-right: 2px solid #e8e8e8;
	border-style: dashed;
}
</style>

<body>
	<div class="broken-line"></div>
	<div id="tableDiv"
		style="width: 48%; height: 300px; float: left; margin-left: 10px">
		<div class="panel panel-default">
			<div class="panel-body">
				<div id="main1" style="height: 300px;"></div>
			</div>
		</div>
	</div>
	<div class="vertical-broken-line"></div>
	<div id="rightDiv" style="width: 48%; height: 300px; float: left">
		<div class="panel panel-default">
			<div class="panel-body">
				<div id="main2" style="height: 300px;"></div>
			</div>
		</div>
	</div>
	<div class="broken-line" style="margin-top: 350px"></div>

	<div id="tableDiv"
		style="width: 48%; height: 300px; float: left; margin-left: 10px">
		<div class="panel panel-default">
			<div class="panel-body">
				<div id="main3" style="height: 300px;  margin: 20px auto;"></div>
			</div>
		</div>
	</div>
	<div class="vertical-broken-line" style="height:380px"></div>
	<div id="rightDiv" style="width: 48%; height: 300px; float: left">
		<div class="panel panel-default">
			<div class="panel-body" style="height: 300px;margin: 35px auto;">
				<h5>工单生产预览</h5>
				<div id="paging" class="pagclass"></div>
			</div>
		</div>
	</div>
</body>
