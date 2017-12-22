<!-- 请复制整个jsp，修改下面第三行引用的js即可 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/system/kanban/material_consumption_list.js"></script>
<body >
	<div id="">
		<label id="item_label"
			style="height: 5%; width: 45%; float: left; text-align: right; font-size:28px">物料消耗看板</label>
		<label id="kanban_date"
			style="height: 5%; width: 50%; float: right; text-align: left;font-size:28px"></label>
	</div>
	<!-- 报表div -->
	<div id="material_table_bg"
		style="height: 80%; width: 100%; border: 2px solid #E5EDF2; float: left;">
		<div class="table-responsive">
			<div id="material_table" class="pagclass"></div>
		</div>
	</div>
	<!-- 报表div -->
</body>
