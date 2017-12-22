<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/date/daterangepicker.js"></script>
<script type="text/javascript" src="${ctx}/js/product_plan/shoporder_maintenance/shoporder_maintenance_add.js"></script>
<script type="text/javascript" src="${ctx}/bootstrap-daterangepicker-master/moment.js"></script>
<script type="text/javascript" src="${ctx}/bootstrap-daterangepicker-master/daterangepicker.js"></script>

<link rel="stylesheet" type="text/css" media="all" href="${ctx}/bootstrap-daterangepicker-master/daterangepicker.css" />
<style type="text/css">
.col-sm-3 {
	width: 15%;
	float: left;
}

.col-sm-9 {
	width: 85%;
	float: left;
}
label[class^="btn btn-default"]{
	margin-top: -4px;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/product_plan/shoporder_maintenance_addEntity.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">所属站点</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" placeholder="请输入所属站点"
						name="shopOrderFormMap.site" id="site" value="${sessionScope.site }" readonly>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">工单编号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkabh"
						placeholder="请输入工单编号" name="shopOrderFormMap.shoporder_no" id="shoporderId">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">工单数量</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkName"
						placeholder="请输入工单数量" name="shopOrderFormMap.shoporder_numbers" id="numbers">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">物料</label>
				<div class="col-sm-9">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="请输入物料"
						name="shopOrderFormMap.shoporder_item" id="shoporder_item">
					<span class="input-group-btn">
						<button class="btn btn-info" id="workorder" type="button"
							style="height: 34px;"
							onclick="getItemMain('shoporder_item','item_no');">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</span>
				</div>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">工艺路线</label>
				<div class="col-sm-9">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="请输入工艺路线"
						name="shopOrderFormMap.process_route" id="process_route">
					<span class="input-group-btn">
						<button class="btn btn-info" id="workorder" type="button"
							style="height: 34px;"
							onclick="getRoute('process_route','process_route');">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</span>
				</div>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">计划开始时间</label>
				<div class="col-sm-9">
					<!-- <input type="text" class="form-control" placeholder="请输入计划开始时间"
						name="shopOrderFormMap.shoporder_start_date" id="startDate"> -->
					<div class="controls">  
	                    <div id="startDate" class="pull-left dateRange">
	                        <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>  
	                        <span id="searchDateRange"></span>
	                        <b class="caret"></b>
	                    </div>
	                </div>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">计划结束时间</label>
				<div class="col-sm-9">
					<!-- <input type="text" class="form-control" placeholder="请输入结束时间"
						name="shopOrderFormMap.shoporder_end_date" id="endDate">-->
					<div class="controls">  
	                    <div id="endDate" class="pull-left dateRange">
	                        <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>  
	                        <span id="searchDateRange"></span>
	                        <b class="caret"></b>
	                    </div>
	                </div>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否可用</label>
				<div class="col-sm-9">
					<select class="form-control" name="shopOrderFormMap.shoporder_status">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</div>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
		</section>
	</form>
</body>
</html>