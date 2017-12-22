<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <script type="text/javascript" src="${ctx}/IE-MES/js/jquery/jquery-1.8.3.js"></script> -->
<script type="text/javascript" src="${ctx}/IE-MES/js/popup/shoporder_Issued.js"></script>
<style type="text/css">
.col-sm-8 {
	width: 70%;
	float: left;
}

label[class^="btn btn-default"] {
	margin-top: -4px;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/IE-MES/plan/shoporderIssued.shtml">
		<div class="panel-body">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title" >检索条件</h3>
				</div>
				<div class="panel-body">
						
					<!--  -->
					<div class="form-group">
						<input type="hidden" name="shopOrderFormMap.id" id="shoporder_issued_id">
						<label class="col-sm-2 control-label">所属站点</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入所属站点"
								name="shopOrderFormMap.site" id="site"
								value="${sessionScope.site }" readonly>
						</div>
					</div>
					<!--  -->

					<div class="line line-dashed line-lg pull-in"></div>

					<!--  -->
					<div class="form-group">
						<label class="col-sm-2 control-label">工单编号</label>
						<div class="col-sm-8">
							<div class="input-group">
								<input type="text" class="form-control" readonly="readonly"
									name="shopOrderFormMap.workorderNo" id="workorder_input"> <span
									class="input-group-btn">
									<button class="btn btn-info" id="workorder" type="button"
										style="height: 34px;"
										onclick="getUnFinishedWorkOrder('workorder_input','shoporder_no','test11');">
										<span class="glyphicon glyphicon-search"></span>
									</button>
								</span>
							</div>
						</div>
					</div>
					<!--  -->

				</div>
			</div>




			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title" >工单信息</h3>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<label class="col-sm-2 control-label">工单数量</label>
						<div class="col-sm-8">
							<input type="text" class="form-control checkName"
								placeholder="请输入工单数量" name="shopOrderFormMap.shoporder_numbers"
								id="numbers">
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label">物料</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入物料"
								name="shopOrderFormMap.shoporder_item" id="shoporder_item">
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label">工艺路线</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入工艺路线"
								name="shopOrderFormMap.process_route" id="process_route">
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label">计划开始时间</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入计划开始时间"
							name="shopOrderFormMap.shoporder_start_date" id="startDate">
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label">计划结束时间</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入结束时间"
							name="shopOrderFormMap.shoporder_end_date" id="endDate">
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label">是否可用</label>
						<div class="col-sm-8">
							<select class="form-control"
								name="shopOrderFormMap.shoporder_status" id="shoporder_status">
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ds_host">车间</label>
						<div class="col-sm-8">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="请输入车间编号"
									name="shopOrderFormMap.workcenter" id="workcenter"> <span
									class="input-group-btn">
									<button class="btn btn-info" id="workorder" type="button"
										style="height: 34px;"
										onclick="getfWorkCenter('workcenter','workcenter_no');">
										<span class="glyphicon glyphicon-search"></span>
									</button>
								</span>
							</div>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label">产线</label>
						<div class="col-sm-8">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="请输入产线编号"
									name="shopOrderFormMap.workline" id="workcenter_input"> <span
									class="input-group-btn">
									<button class="btn btn-info" id="workorder2" type="button"
										style="height: 34px;"
										onclick="getfWorkCenterChild('workcenter_input','workcenter_no');">
										<span class="glyphicon glyphicon-search"></span>
									</button>
								</span>
							</div>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label">下达数量</label>
						<div class="col-sm-8">
							<input type="text" class="form-control checkName" readonly="readonly"
								placeholder="请输入工单数量" name="shopOrderFormMap.shoporder_numbers"
								id="issued_numbers">
						</div>
					</div>
				</div>
			</div>

			<div class="panel-footer" align="right">
				<button type="submit" class="btn btn-success btn-s-xs">工单下达</button>
			</div>
		</div>
	</form>
</body>
</html>