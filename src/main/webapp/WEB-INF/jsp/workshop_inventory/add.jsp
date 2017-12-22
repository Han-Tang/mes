<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/workshop_inventory/add.js">
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
	<div class="panel panel-primary">
	    <div class="panel-heading">
	        <h3 class="panel-title">库存接收面板</h3>
	    </div>
	    <div class="panel-body">
	        <form id="form" name="form" class="form-horizontal" method="post"
				action="${ctx}/workshop_inventory/workshop_inventory_addEntity.shtml">
				<section class="panel panel-default">
				<div class="panel-body">
					<div class="form-group">
						<label class="col-sm-2 control-label">所属站点</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入所属站点"
								name="workshopInventoryFormMap.site" id="site" value="${sessionScope.site }" readonly>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label">接收物料</label>
						<div class="col-sm-8">
						<div class="input-group">
							<input type="text" readonly="readonly" class="form-control" placeholder="请选择物料"
								name="workshopInventoryFormMap.item" id="item">
							<span class="input-group-btn">
								<button class="btn btn-info" id="workorder" type="button"
									style="height: 34px;"
									onclick="getItemChild('item','item_no');">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
							</div>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label">物料批次</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" 
								name="workshopInventoryFormMap.batch" id="batch" value="${batch}" readonly>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label">接收SFC</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入物料的SFC编号"
								name="workshopInventoryFormMap.itemSfc" id="item_sfc">
							<input type="hidden" id="item_sfcs" name="workshopInventoryFormMap.item_sfcs">
							<input type="hidden" id="item_nos" name="workshopInventoryFormMap.item_nos">
						</div>
					</div>
				</div>
				</section>
			</form>
	    </div>
	    <!-- 表格 -->
	    <div class="panel-body">
	    	<section class="panel panel-default">
				<footer class="panel-footer text-left bg-light left">
					<button type="button" disabled="disabled" id="canner_btn" class="btn btn-success btn-s-xs">取消接收</button>
					<button type="button" disabled="disabled" id="finish_btn" class="btn btn-success btn-s-xs">完成接收</button>
				</footer>
			    <div class="table-responsive">
					<div id="item_inventory_paging" class="pagclass"></div>
				</div>
			</section>
		</div>
		<!-- 表格 -->
	</div>
</body>
</html>