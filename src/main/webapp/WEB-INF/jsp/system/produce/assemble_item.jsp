<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/production_process/pod_panel/assemble_item.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>POD</title>
</head>
<body>
	<ul class="nav nav-pills nav-stacked">
		<li class="active"><a href="#">装配组件</a></li>
	</ul>
	<div class="panel panel-default">	
		<div class="contentPanel">
			<form class="form-horizontal" role="form" id="assemble_item_form" name="assemble_item_form">
				<input type="hidden" id="site" name="sfcAssemblyFormMap.site" value="${sessionScope.site }">
			  <div class="form-group">
			    <label class="col-sm-4 control-label">当前车间作业控制</label>
			    <div class="col-sm-4">
			    	<input type="text" class="form-control" id="sfc" name="sfcAssemblyFormMap.sfc" readonly="readonly" value="${res.wc_sfc}">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword" class="col-sm-4 control-label">当前操作</label>
			    <div class="col-sm-3">
			      <input type="text" class="form-control" id="operation" name="sfcAssemblyFormMap.operation" readonly="readonly" value="${res.operation_no}">
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label class="col-sm-2 control-label">组件详细信息</label>
			    <dir class="col-sm-7"/>
			    <label class="col-sm-1 control-label">装配信息</label>
			  </div>
			  
			  <div class="form-group">
			    <label class="col-sm-1 control-label">组件</label>
			    <div class="col-sm-2">
			      <input type="text" class="form-control" readonly="readonly" id="item_no" name="sfcAssemblyFormMap.item" value="${res.item_no}">
			    </div>
			    <dir class="col-sm-5"/>
			    
			    <label class="col-sm-1 control-label">装配SFC</label>
				<div class="col-sm-2">
					<div class="input-group">
						<input type="text" class="form-control" id="supplier" name="sfcAssemblyFormMap.item_sfc" > <span
							class="input-group-btn">
							<button class="btn btn-info" id="operation" type="button" style="height: 34px;" 
							onclick="getSfcByAssemble('supplier','item_sfc');">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
				</div>
			    
			  </div>
			  
			  <div class="form-group">
			    <label class="col-sm-1 control-label">组件名称</label>
			    <div class="col-sm-2">
			      	<input type="text" class="form-control" readonly="readonly" id="item_list_name" name="sfcAssemblyFormMap.item_list_name" value="${res.item_name}">
			    </div>
			    <dir class="col-sm-5"/>
			    <label class="col-sm-1 control-label">供应商批次</label>
			    <div class="col-sm-2">
			      <input type="text" class="form-control" readonly="readonly" value="${res.batch}" id="supplier_batch" name="sfcAssemblyFormMap.batch">
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label class="col-sm-1 control-label">组件描述</label>
			    <div class="col-sm-2">
			    	<textarea class="form-control" rows="3" readonly="readonly" id="item_desc" name="sfcAssemblyFormMap.item_desc">${res.item_desc}</textarea>
			    </div>
			    <dir class="col-sm-5"/>
			    <label class="col-sm-1 control-label">装配数量</label>
			    <div class="col-sm-2">
			      <input type="number" class="form-control" id="assemble_num" name="sfcAssemblyFormMap.use_num" value="1">
			    </div>
			  </div>
			  
			<div align="center">
				<button type="button" class="btn btn-primary" onclick="fit_out();">装配</button>
				<button type="button" class="btn btn-primary" id="assembling_canner();" onclick="close_task_div();">关闭</button>
			</div>
			</form>
		</div>	
	</div>
</body>
</html>