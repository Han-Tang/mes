<!-- 请复制整个jsp，修改下面第三行引用的js即可 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/workshopdata/operation_resource/operation_resource_list.js"></script>
<body>
<div class="contentPanel">
<div class="m-b-md">
	<form class="form-inline" role="form" id="searchForm" name="searchForm">
		<div>
			<input style="width: 100%;" type="text" class="form-control" id="name" name="operationResourceFormMap.operationResource_no" placeholder="请输入资源编号检索">
		</div>
	</form>
</div>

<!-- 
	这个是新增、删除、修改等菜单，从菜单管理功能进行配置
	-----------------菜单读取开始---------------------
 -->
<header>
	<div class="doc-buttons">
		<c:forEach items="${res}" var="key">
		${key.description}
		</c:forEach>
	</div>
</header>
<!-- -----------------菜单读取结束--------------------- -->

 <!-- 列表展示div -->
<div class="table-responsive">
	<div id="paging" class="pagclass"></div>
</div>
</div>
</body>