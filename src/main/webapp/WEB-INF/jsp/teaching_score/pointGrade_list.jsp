<!-- 请复制整个jsp，修改下面第三行引用的js即可 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/teaching_test/pointGrade_list.js">
	
</script>
<body>
	<div class="contentPanel">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">评分信息维护</h3>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form"
					id="pointGrade_list_condition_form">
					<div class="form-group">
						<div class="col-sm-2"></div>
						<label class="col-sm-1 control-label">考试编号</label>
						<div class="col-sm-7">
							<div class="input-group">
								<input type="text" class="form-control"
									placeholder="请输入本次的考试编号" name="exam_no"
									id="exam_no"> <span class="input-group-btn">
									<button class="btn btn-info" id="reverse" type="button"
										style="height: 34px;" onclick="getExam('exam_no','exam_no');">
										<span class="glyphicon glyphicon-search"></span>
									</button>
								</span>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-1"></div>
						<label class="col-sm-2 control-label">考试名称</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" placeholder="请输入本次的考试的名称"
								name="exam_name" id="exam_name">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-1"></div>
						<label class="col-sm-2 control-label">考试开始时间</label>
						<div class="col-sm-7">
							<input name="exam_start_time"
								id="exam_start_date" class="form-control" placeholder="选择考试开始时间"
								onclick="layui.laydate({elem: this, festival: true, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-1"></div>
						<label class="col-sm-2 control-label">考试结束时间</label>
						<div class="col-sm-7">
							<input name="exam_end_time" id="exam_end_date"
								class="form-control" placeholder="选择考试结束时间"
								onclick="layui.laydate({elem: this, festival: true, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-1"></div>
						<label class="col-sm-2 control-label">启用</label>
						<div class="col-sm-7">
							<div class="checkbox">
								<input type="checkbox" id="isUse" name="enabled">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">评分项</h3>
			</div>
			<form id="point_grade_form">
				<div class="table-responsive"
					style="overflow-y: scroll; height: 100%">
					<div id="paging" class="pagclass"></div>
				</div>
			</form>
		</div>

		<div>
			<button class="btn btn-success btn-s-xs" id="confirm"
				style="float: right;">确定</button>
		</div>
	</div>
</body>