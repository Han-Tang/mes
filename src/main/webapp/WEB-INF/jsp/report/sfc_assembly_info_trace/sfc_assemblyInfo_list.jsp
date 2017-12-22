<!-- 请复制整个jsp，修改下面第三行引用的js即可 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/report/sfc_assembly_info_trace/sfc_assembly_info_list.js"></script>
<div class="panel panel-default">
	<div class="panel-body-ext">
		<div class="table-responsive">
			<div id="paging" class="pagclass"></div>
		</div>
		<input type="hidden" value="${sfc}" id="sfc">
		<input type="hidden" value="${operation}" id="operation">