<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="${ctx}/IE-MES/js/qc/nc_handle_info.js"></script>
<title>nc_handle</title>
</head>
<c:if test="${exist==true }">
	<body id="report_test_body" style="height: 100%; margin: 0">
		<form id="product_form" name="product_form" class="form-horizontal">
			<!--产品信息  -->
			<div class="panel panel-default"
				style="height: 50%; width: 50%; float: left;">
				<div class="panel-heading">
					<h3 class="panel-title">产品信息</h3>
				</div>
				<div class="panel-body">
					<div class="form-group"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label">产品名称:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入产品名称"
								id="product_name_input" name="formMap.product_name"
								value="${ncInfo[0].item_name }" readonly>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">产品描述:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入产品描述"
								id="product_desc_input" name="formMap.product_desc"
								value="${ncInfo[0].item_desc }" readonly>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">所属工单:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入所属工单"
								id="shoporder_no_input" name="formMap.shoporder_no"
								value="${ncInfo[0].shoporderId }" readonly>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">异常工位:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入产品名称"
								id="operation_no_input" name="formMap.operation_no"
								value="${ncInfo[0].operation }" readonly>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">操作人:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入产品名称"
								id="by_user_input" name="formMap.by_user"
								value="${ncInfo[0].by_user }" readonly>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">操作时间:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入操作时间"
								id="create_time_input" name="formMap.create_time"
								value="${ncInfo[0].create_time }" readonly>
						</div>
					</div>
				</div>
			</div>


			<!--不合格信息  -->
			<div class="panel panel-default"
				style="height: 17.5%; width: 50%; float: right;">
				<div class="panel-heading">
					<h3 class="panel-title">不合格信息</h3>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<label class="col-sm-2 control-label">不合格组:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入不合格组"
								id="nc_group_input" name="formMap.nc_group"
								value="${ncInfo[0].nc_code_group_desc }" readonly>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">不合格代码:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="请输入不合格代码"
								id="nc_code_input" name="formMap.nc_code"
								value="${ncInfo[0].nc_code_desc }" readonly>
						</div>
					</div>
				</div>
			</div>
		</form>

		<!--处置方式  -->
		<form id="handle_form" name="handle_form" class="form-horizontal">
			<div class="panel panel-default"
				style="height: 30%; width: 50%; float: right;">
				<div class="panel-heading">
					<h3 class="panel-title">处置方式</h3>
				</div>
				<div class="panel-body">
					<div class="form-group"></div>
					<div class="form-group">
						<label class="radio-inline" style="margin-left: 19.3%"> <input
							type="radio" id="radio" name="radio" value="repair"
							checked="checked"> 维修&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</label> <label class="radio-inline" style="margin-left: 35%"> <input
							type="radio" id="radio" name="radio" value="scrap"> 报废
						</label>
					</div>
					<div class="form-group"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label">指定产品跳至哪个操作</label>
						<div class="col-sm-3">
							<select class="form-control" id="goto_operation">
								<c:forEach items="${operationList}" var="operation">
									<option value="${operation.operation}">${operation.operation}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">备注:</label>
						<div class="col-sm-8">
							<textarea class="form-control" rows="3" id="remarks_input"
								name="formMap.remarks"></textarea>
						</div>
					</div>

					<div class="form-group" align="center">
						<button type="button" class="btn btn-success btn-s-xs"
							onclick="handle()">处置</button>
					</div>
				</div>
			</div>
		</form>
	</body>

</c:if>
</html>