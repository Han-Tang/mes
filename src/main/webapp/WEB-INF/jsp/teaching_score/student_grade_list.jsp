<!-- 请复制整个jsp，修改下面第三行引用的js即可 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/teaching_test/student_grade_list.js"></script>

<body>
	<div class="contentPanel">
		<div class="m-b-md" style="margin: 10px">
			<form id="form" name="form" class="form-horizontal" method="post"
				action="${ctx}/IE-MES/plan/shoporderIssued.shtml">
				<div class="panel-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">检索条件</h3>
						</div>
						<div class="panel-body">

							<!--  -->
							<div class="form-group">
								<input type="hidden" name="shopOrderFormMap.id"
									id="shoporder_issued_id"> <label
									class="col-sm-2 control-label">考试编号</label>
								<div class="col-sm-8">
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
							<!--  -->

							<div class="line line-dashed line-lg pull-in"></div>

							<!--  -->
							<div class="form-group">
								<label class="col-sm-2 control-label">考试名称</label>
								<div class="col-sm-8">
									<input type="text" class="form-control checkName" placeholder="请输入考试名称" 
									name="shopOrderFormMap.shoporder_numbers"
										id="issued_numbers">
								</div>
							</div>
							<!--  -->
						</div>
					</div>
				</div>
			</form>
		</div>

		<!-- 列表展示div -->

		<div class="table-responsive">
			<div id="paging" class="pagclass"></div>
		</div>

	</div>
</body>
