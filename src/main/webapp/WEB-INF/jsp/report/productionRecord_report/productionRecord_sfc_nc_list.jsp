<!-- 请复制整个jsp，修改下面第三行引用的js即可 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/common/common.jspf"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/report/productionRecord_report/productionRecord_sfc_nc_list.js"></script>

<div class="report_title">生产记录报表_不良详细信息</div>
<!-- 列表展示div -->
<div class="panel panel-default">
	<div class="panel-body-ext">
		<div class="table-responsive">
			<div id="paging" class="pagclass"></div>
		</div>
		<input type="hidden" value="${sfc}" id="SFC_input">
		<input type="hidden" value="${operation}" id="operation">