<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/workshopdata/routing/routing_add.js">
</script>
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
		action="${ctx}/workshop/routing_addEntity.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">工艺路线</label>
				<div class="col-sm-9">
					<input type="text" class="form-control check"
						placeholder="请输入工艺路线" name="routingFormMap.process_route" id="process_route">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">工艺路线类型</label>
				<div class="col-sm-9">
					<input type="text" class="form-control"
						placeholder="请输入工艺路线类型" name="routingFormMap.process_route_type" id="process_route_type">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">描述</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" placeholder="请输入工艺路线描述"
						name="routingFormMap.process_route_desc" id="process_route_desc">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">状态</label>
				<div class="col-sm-9">
					<div class="btn-group m-r">
						<button data-toggle="dropdown"
							class="btn btn-sm btn-default dropdown-toggle">
							<span class="dropdown-label">可用</span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu dropdown-select">
							<li class=""><a href="#"><input type="radio"
									name="routingFormMap.state" value="1" checked="checked">可用</a></li>
							<li class="active"><a href="#"><input type="radio"
									name="routingFormMap.state" value="0">不可用</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否允许跳过</label>
				<div class="col-sm-9">
					<div class="btn-group m-r">
						<button data-toggle="dropdown"
							class="btn btn-sm btn-default dropdown-toggle">
							<span class="dropdown-label">是</span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu dropdown-select">
							<li class=""><a href="#"><input type="radio"
									name="routingFormMap.allowpass" value="1" checked="checked">是</a></li>
							<li class="active"><a href="#"><input type="radio"
									name="routingFormMap.allowpass" value="0">否</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label for="inputCreateTime" class="col-sm-3 control-label">创建时间</label>
				<div class="col-sm-9">
					<label style="text-align: left; cursor: default;" id="createTime"><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())%></label>
				</div>
			</div>
			<div class="form-group">
				<label for="inputCreateTime" class="col-sm-3 control-label">编辑工艺路线</label>
				<button type="button" id="editRouting" class="btn btn-success btn-s-xs">编辑工艺路线</button>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">新增</button>
		</footer> 
		</section>
	</form>
</body>
</html>