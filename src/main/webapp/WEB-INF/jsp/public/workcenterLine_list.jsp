<!-- 请复制整个jsp，修改下面第三行引用的js即可 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/workshopdata/workcenter/workcenter_line_pop.js"></script>

<!-- 列表展示div -->
<div class="panel panel-default">
	<div class="panel-body">
<div class="table-responsive">
	<div id="paging" class="pagclass"></div>
</div>
<div align="right">
	<button type="button" class="btn btn-primary" id="op_ok">确定</button>
	<button type="button" class="btn btn-primary" id="op_canner">取消</button>
</div>