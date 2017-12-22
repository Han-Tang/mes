<!-- 请复制整个jsp，修改下面第三行引用的js即可 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/common/common.jspf" %>
<script type="text/javascript">
var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
		}, {
			colkey : "item_sfc",
			name : "sfc",
		}, {
			colkey : "item_no",
			name : "物料编号",
		}, {
			colkey : "item_name",
			name : "物料名称",
		}, {
			colkey : "item_desc",
			name : "物料描述",
		}, {
			colkey : "item_type",
			name : "物料类型",
		},{
			colkey : "site",
			name : "挂靠站点"
		}, {
			colkey : "by_user",
			name : "创建人"
		}, {
			colkey : "create_time",
			name : "创建时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}],
		jsonUrl : rootPath + '/report/getSfcInfoData.shtml',
		dymCol:true,
		checkbox : true,
		serNumber : false
	});
	
});
</script>

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