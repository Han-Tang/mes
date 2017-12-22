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
			colkey : "exam_no",
			name : "考试编号",
		}, {
			colkey : "exam_name",
			name : "考试名称",
		}, {
			colkey : "enabled",
			name : "状态",
			renderData : function(rowindex,data, rowdata, column) {
				if (data=="1") {
					return "已启用";
				}else if (data=="0") {
					return "未启用";
				}
			}
		}, {
			colkey : "exam_start_time",
			name : "开始时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "exam_end_time",
			name : "结束时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}],
		jsonUrl : rootPath + '/point_grade/exam_findBySite.shtml',
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
	<button type="button" class="btn btn-primary" id="confirm" onclick="op_ok()">确定</button>
	<button type="button" class="btn btn-primary" id="op_canner">取消</button>
</div>