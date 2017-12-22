<!-- 请复制整个jsp，修改下面第三行引用的js即可 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />	
<script type="text/javascript"
	src="${ctx}/js/report/productionRecord_report/productionRecord_list.js"></script>

<!-- 列表展示div -->
<div class="panel panel-default">
	<div class="panel-body">
		<div class="table-responsive">
			<div id="paging" class="pagclass"></div>
		</div>
		<input type="hidden" value="${query_form}" id="query_form"
			name="query_form">