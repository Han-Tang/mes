<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf"%>
<script type="text/javascript"
	src="${ctx}/js/item_resource/bom/bom_edit.js">
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

label[class^="btn btn-default"] {
	margin-top: -4px;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/item_resource/bom_editEntity.shtml">
		<input type="hidden" class="form-control checkacc" value="${bom.id}"
			name="itemFormMap.id" id="id">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">站点</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkabh"
						placeholder="请输入站点" name="bomFormMap.site" id="site"
						value="${sessionScope.site }" readonly="readonly">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">物料BOM编号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkabh"
						placeholder="请输入物料BOM编号" name="bomFormMap.item_bom_no"
						id="item_bom_no" value="${bom.item_bom_no }" readonly="readonly">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">物料BOM名称</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkName"
						placeholder="请输入物料BOM名称" name="bomFormMap.item_bom_name"
						id="item_bom_name" value="${bom.item_bom_name }">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">物料BOM描述</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" placeholder="请输入物料BOM描述"
						name="bomFormMap.item_bom_desc" id="item_bom_desc"
						value="${bom.item_bom_desc }">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否可用</label>
				<div class="col-sm-9">
					<select class="form-control" name="bomFormMap.state">
						<option value="1" <c:if test="${bom.state eq 1}"> SELECTED</c:if>>是</option>
						<option value="0" <c:if test="${bom.state eq 0}"> SELECTED</c:if>>否</option>
					</select>
				</div>
				<input type="hidden" name="itemSize" value="${item_bom.size()}">
			</div>
			<div class="line line-dashed line-lg pull-in"></div>

			<!-- 开始处理清单 -->
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">物料清单子项</h3>
				</div>
				<div class="panel-body">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th style="text-align: center;width:176px;">物料编号</th>
								<th style="text-align: center;width:135px;">使用数量</th>
								<th style="text-align: center;width:136px;">结存上限</th>
								<th style="text-align: center;width:136px;">结存下限</th>
								<th style="text-align: center;"><input type="button"
									class="btn btn-primary" onclick="insert_row(this)" value="增加"></th>
								<th style="text-align: center;"><input type="button"
									class="btn btn-danger" onclick="deleteRow(this)" value="删除"></th>
							</tr>
						</thead>
						<tbody id="tbl">
							<c:forEach items="${item_bom}" var="item_bom">
								<tr>
									<td>
										<div class="input-group" style="width: 100px;">
											<input type="text" class="form-control" style="width: 120px;" name="item_no_1" id="item_no_1"
												value="${item_bom.item_no}"> <span
												class="input-group-btn">
												<button class="btn btn-info" id="workorder" type="button"
													style="height: 34px;"
													onclick="getItem('item_no_1','item_no');">
													<span class="glyphicon glyphicon-search"></span>
												</button>
											</span>
										</div>
									</td>
									<td>
										<div class="input-group">
											<input type="text" class="form-control"
												name="item_use_number_1" value="${item_bom.use_number}">
										</div>
									</td>
									<td>
										<div class="input-group">
											<input type="text" class="form-control"
												name="item_balance_up_1" value="${item_bom.balance_up}">
										</div>
									</td>
									<td>
										<div class="input-group">
											<input type="text" class="form-control"
												name="item_balance_down_1" value="${item_bom.balance_down}">
										</div>
									</td>
									<td><input type="button" class="btn btn-primary"
										onclick="insert_row(this)" value="增加"></td>
									<td><input type="button" class="btn btn-danger"
										onclick="deleteRow(this)" value="删除"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</div>
		<footer class="panel-footer text-right bg-light lter">
		<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> </section>
	</form>

	<script type="text/javascript">
		var i = 0
		function insert_row(obj) {
			i++;
			R = tbl.insertRow(tbl.rows.length);
			C = R.insertCell(0);
			C.innerHTML = '<div class="input-group" style="width: 100px;"><input type="text" style="width: 120px;" class="form-control" name="item_no_1"id="item_no_1_'+i+'">'
					+ '<span class="input-group-btn">'
					+ '<button class="btn btn-info" id="workorder" type="button"style="height: 34px;" '
					+ 'onclick="getItem(\'item_no_1_'+i+'\',\'item_no\');">'
					+ '<span class="glyphicon glyphicon-search"></span></button></span></div>';
			C = R.insertCell(1);
			C.innerHTML = '<div class="input-group"><input type="text" class="form-control" name="item_use_number_1" id="item_use_number_1"></div>';
			C = R.insertCell(2);
			C.innerHTML = '<div class="input-group"><input type="text" class="form-control" name="item_balance_up_1" id="item_balance_up_1"></div>';
			C = R.insertCell(3);
			C.innerHTML = '<div class="input-group"><input type="text" class="form-control" name="item_balance_down_1" id="item_balance_down_1"></div>';
			C = R.insertCell(4);
			C.innerHTML = '<input type="button" class="btn btn-primary" onclick ="insert_row(this)" value = "增加">';
			C = R.insertCell(5);
			C.innerHTML = '<input type="button" class="btn btn-danger" onclick ="deleteRow(this)" value ="删除">';
		}
		function deleteRow(obj) {
			var i = obj.parentNode.parentNode.rowIndex;
			if (i > 0) {
				tbl.deleteRow(i-1);
			}
		}
	</script>
</body>
</html>