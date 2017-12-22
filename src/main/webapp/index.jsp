<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html lang="en" class="app">
<head>
<style type="text/css">
.contentPanel {
	margin: 20px 20px;
}

.line-down-5h {
	margin-bottom: 10px;
}

.div_center {
	position: fixed; /*or前面的是absolute就可以用*/
	width: 100%;
	text-align: center;
	bottom: 0px;
}
</style>
<%@include file="/common/common.jspf"%>
<script type="text/javascript">
	$(function() {
		var winwidth = $("body").width();
		if (winwidth < 770) {
			$("#nav ul.lt li").click(function() {
				$("#nav").removeClass("nav-off-screen");
			});
		}
		//---------修改人赵雷完毕----------
		var tb = $("#loadhtml");
		tb.html(CommnUtil.loadingImg());
		tb.load(rootPath + "/welcome.jsp");
		$("[nav-n]").each(
				function() {
					$(this).bind(
							"click",
							function() {
								var nav = $(this).attr("nav-n");
								var sn = nav.split(",");
								var html = '<li><i class="fa fa-home"></i>';
								html += '<a href="index.shtml">Home</a></li>';
								for (var i = 0; i < 2; i++) {
									html += '<li><a href="javascript:void(0)">'
											+ sn[i] + '</a></li>';
								}
								$("#topli").html(html);
								var tb = $("#loadhtml");
								tb.html(CommnUtil.loadingImg());
								tb.load(rootPath + sn[2]);
							});
				});
		setInterval("getScoreInfo()", 5000);
	});

	function getScoreInfo() {
		$.ajax({
					type : 'POST',
					data : "",
					url : '/IE-MES/produce/getScoreInfo.shtml',
					success : function(e) {
						var data = JSON.parse(e);
						if (data.result) {
							var rs = data.data;
							$('#add_score').remove();
							var start_time = rs.exam_start_time;
							var end_time = rs.exam_end_time;
							var surplus = rs.surplus;
							var score = rs.score;
							$('#score_ul').append(
											"<li titile='当前时间为考试时间，请学生抓紧时间完成考试，考试评分只计算考试时间内的操作，非考试时间段内的操作不计分'"+
							" id='add_score'><a"+
						"href='#' class='navbar-brand' style='color: #1dff00'><font style='font-size: 14px;'> 考试模式 考试时间："
													+ start_time
													+ "——"
													+ end_time
													+ ", 剩余时间："
													+ surplus
													+ "分钟	总分：<span id='score'>"
													+ score
													+ "分</span></font></a></li>");
						} else {
							$('#add_score').remove();
						}
					},
					error : function(xhr, e) {

					}
				});
	}

	//点击学生信息导入openLayer
	function openLayer() {
		// 点击学生学生信息导入时 弹出框的内容 
		var indexx = layer.open({
			type : 2,
			title : "模板上传",
			area : [ '500px', '300px' ], //宽高
			content : rootPath + '/message/upload.shtml'
		});
	}

	//点击帮助openHelpLayer
	function openHelpLayer() {
		// 点击帮助时 弹出框的内容 
		var indexx = layer.open({
			type : 2,
			title : "帮助文档",
			area : [ '50%', '80%' ], //宽高
			content : rootPath + '/help/open.shtml'
		});

	}
	
	// 点击工厂主数据导入 弹出框
	 function openFactoryImport(){
		var indexx = layer.open({
			type : 2,
			title : "工厂主数据导入",
			area : [ '500px', '300px' ], //宽高
			content : rootPath + '/message/mainUpload.shtml'
		});
	 }
</script>
</head>
<body class="" style="">
	<section class="vbox">
		<header class="bg-dark dk header navbar navbar-fixed-top-xs">
			<div class="navbar-header aside-md">
				<a class="btn btn-link visible-xs"
					data-toggle="class:nav-off-screen,open" data-target="#nav,html">
					<i class="fa fa-bars"></i>
				</a> <a href="index.shtml#" class="navbar-brand"
					data-toggle="fullscreen">IE-MES<font style="font-size: 12px;">制造执行系统</font></a>
				<a class="btn btn-link visible-xs" data-toggle="dropdown"
					data-target=".nav-user"> <i class="fa fa-cog"></i>
				</a>
			</div>
			<span class="glyphicon icon-checkmark" style="margin: 0px 4px; float: right ">
				<ul id="score_ul" class="nav navbar-nav hidden-xs">
					<!-- 
					<li><a href="index.shtml#" class="navbar-brand"
						onclick="openLayer()"><font style="font-size: 12px;"><span
								class="glyphicon glyphicon-open" style="margin: 0px 4px"></span>学生信息导入</font></a></li>
					<li><a href="index.shtml#" class="navbar-brand"
						onclick="openHelpLayer()"><font style="font-size: 12px;"><span
								class="glyphicon glyphicon-info-sign" style="margin: 0px 4px"></span>帮助</font></a></li>
					<li><a href="index.shtml#" class="navbar-brand"
						onclick="openFactoryImport()"><font style="font-size: 12px;"><span
								class="glyphicon glyphicon-open" style="margin: 0px 4px"></span>工厂主数据导入</font></a></li>
					 -->			
					<!-- 			<a href="index.shtml#" class="navbar-brand"><font style="font-size: 12px;">生产进度报表</font></a>
				<a href="index.shtml#" class="navbar-brand"><font style="font-size: 12px;">SPC分析</font></a>
				<a href="index.shtml#" class="navbar-brand"><font style="font-size: 12px;">品质报表</font></a>
				<a href="index.shtml#" class="navbar-brand"><font style="font-size: 12px;">产线稼动率分析</font></a>
				<a href="index.shtml#" class="navbar-brand"><font style="font-size: 12px;">产品反向追溯</font></a>
				<a href="index.shtml#" class="navbar-brand"><font style="font-size: 12px;">库存报警</font></a> -->
				</ul>
				<ul class="nav navbar-nav navbar-right m-n hidden-xs nav-user">
					<li class="dropdown"><a href="index.html#"
						style="font-family: -webkit-pictograph;" class="dropdown-toggle"
						data-toggle="dropdown"> <span
							class="thumb-sm avatar pull-left"> <img
								src="${ctx}/notebook/notebook_files/avatar.jpg">
						</span> site:${sessionScope.siteMap.siteName } ,
							${userFormMap.accountName} <b class="caret"></b>
					</a>
						<ul class="dropdown-menu animated fadeInRight">
							<span class="arrow top"></span>
							<li><a href="logout.shtml" style="color: gray;"><span
									class="glyphicon glyphicon-transfer"></span> 切换站点</a></li>
							<li><a href="logout.shtml" style="color: gray;"><span
									class="glyphicon glyphicon-log-in"></span> 注销</a></li>
						</ul></li>
				</ul>
		</header>
		<section>
			<section class="hbox stretch">
				<!-- .aside -->
				<aside class="bg-dark lter aside-md hidden-print hidden-xs" id="nav">
					<section class="vbox">
						<!-- <header class="header bg-primary lter text-center clearfix">
							<div class="btn-group">
							系统菜单
							</div>
						</header> -->
						<section class="w-f scrollable">
							<div class="slim-scroll" data-height="auto"
								data-disable-fade-out="true" data-distance="0" data-size="5px"
								data-color="#333333">
								<!-- nav -->
								<nav class="nav-primary hidden-xs">
									<ul class="nav">
										<c:forEach var="key" items="${list}" varStatus="s">
											<!-- <li class="active"> 某一项展开-->
											<li><a href="javascript:void(0)"> <c:if
														test="${s.index==0}">
														<i class="fa fa-dashboard icon"> <b class="bg-danger"></b>
														</i>
													</c:if> <c:if test="${s.index==1}">
														<i class="fa fa-pencil-square icon"> <b
															class="bg-warning"></b>
														</i>
													</c:if> <c:if test="${s.index==2}">
														<i class="fa fa-columns icon"> <b class="bg-primary"></b>
														</i>
													</c:if> <c:if test="${s.index==3}">
														<i class="fa fa-book icon"> <b class="bg-info"></b>
														</i>
													</c:if> <c:if test="${s.index==4}">
														<i class="fa fa-th-list icon"> <b class="bg-success"></b>
														</i>
													</c:if> <c:if test="${s.index==5}">
														<i class="fa fa-dashboard icon"> <b class="bg-danger"></b>
														</i>
													</c:if> <c:if test="${s.index==6}">
														<i class="fa fa-pencil-square icon"> <b
															class="bg-warning"></b>
														</i>
													</c:if> <c:if test="${s.index==7}">
														<i class="fa fa-columns icon"> <b class="bg-primary"></b>
														</i>
													</c:if> <c:if test="${s.index==8}">
														<i class="fa fa-book icon"> <b class="bg-info"></b>
														</i>
													</c:if> <c:if test="${s.index==9}">
														<i class="fa fa-th-list icon"> <b class="bg-success"></b>
														</i>
													</c:if> <c:if test="${s.index==10}">
														<i class="fa fa-dashboard icon"> <b class="bg-danger"></b>
														</i>
													</c:if> <c:if test="${s.index==11}">
														<i class="fa fa-pencil-square icon"> <b
															class="bg-warning"></b>
														</i>
													</c:if> <c:if test="${s.index==12}">
														<i class="fa fa-columns icon"> <b class="bg-primary"></b>
														</i>
													</c:if> <c:if test="${s.index==13}">
														<i class="fa fa-book icon"> <b class="bg-info"></b>
														</i>
													</c:if> <c:if test="${s.index==14}">
														<i class="fa fa-th-list icon"> <b class="bg-success"></b>
														</i>
													</c:if> <span class="pull-right"> <i
														class="fa fa-angle-down text"></i> <i
														class="fa fa-angle-up text-active"></i>
												</span> <span>${key.name}</span>
											</a>

												<ul class="nav lt">
													<c:forEach var="kc" items="${key.children}">
														<li class="active"><a href="javascript:void(0)"
															class="active"
															nav-n="${key.name},${kc.name},${kc.resUrl}?id=${kc.id}">
																<i class="fa fa-angle-right"></i> <span>${kc.name}</span>
														</a></li>
													</c:forEach>
												</ul></li>
										</c:forEach>
									</ul>
								</nav>
								<!-- / nav -->
							</div>
						</section>
						<footer class="footer lt hidden-xs b-t b-dark"> </footer>
					</section>
				</aside>
				<!-- /.aside -->

				<section id="content">
					<section id="id_vbox" class="vbox">
						<ul class="breadcrumb no-border no-radius b-b b-light" id="topli">
						</ul>
						<section class="scrollable" style="margin-top: 35px;">
							<div id="loadhtml"></div>
						</section>
					</section>
				</section>
			</section>
		</section>
		<!-- 
		<div class="div_center bg-dark nav">
			<p style="color: white">Copyright © 2016 - 2017 深圳市迈鼎盛信息技术有限公司</p>
		</div>
		 -->
	</section>
	<!-- Bootstrap -->
	<div id="flotTip" style="display: none; position: absolute;"></div>
</body>
</html>