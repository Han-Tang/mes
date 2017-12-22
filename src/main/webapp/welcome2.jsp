<!-- 请复制整个jsp，修改下面第三行引用的js即可 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">

var pageii = null;
var grid = null;
var grid2 = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging3',
		l_column : [{
			colkey : "sfc",
			name : "SFC"
		}, {
			colkey : "shoporderid",
			name : "物料"
		}, {
			colkey : "item_type",
			name : "物料类别"
		}, {
			colkey : "nc_code_group_desc",
			name : "不合格代码组",
		},{
			colkey : "nc_code_desc",
			name : "不合格代码"
		}, {
			colkey : "workcenter_no",
			name : "车间"
		}, {
			colkey : "workcenter",
			name : "产线"
		}, {
			colkey : "default_resource",
			name : "资源"
		}],
		jsonUrl : rootPath + '/report/getUnqualifiedData.shtml',
		dymCol:true,
		checkbox : false,
		serNumber : true,
        usePage : true,// 是否分页
        pageSize : 5, // 每页显示多少条数据
	});	
	
	grid2 = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
		}, {
			colkey : "shoporder_no",
			name : "工单编号",
		}, {
			colkey : "shoporder_numbers",
			name : "订单数量",
		}, {
			colkey : "shoporder_item",
			name : "物料编号",
		}, {
			colkey : "process_route",
			name : "工艺路线",
		}, {
			colkey : "shoporder_status",
			name : "工单状态",
			renderData: function(rowindex,data, rowdata, column) {
				switch(data)
				{
				case "0":
					return "未开始";
					break;
				case "1":
					return "正在进行";
					break;
				case "2":
					return "已完成";
					break;
				}
			}
		},{
			colkey : "shoporder_start_date",
			name : "订单开始时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
		}}, {
			colkey : "shoporder_end_date",
			name : "订单完成时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
		}}, {
			colkey : "site",
			name : "挂靠站点"
		}, {
			colkey : "by_user",
			name : "创建人"
		}, {
			colkey : "create_time",
			name : "创建时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}],
		jsonUrl : rootPath + '/product_plan/shoporder_maintenance_findByPage.shtml',
		dymCol:true,
		checkbox : false,
		serNumber : true,
        usePage : true,// 是否分页
        pageSize : 5, // 每页显示多少条数据
	});

	 var data = 'formMap.reportMethod=getWelcomeData&'+data
	$.ajax({
		type: 'POST', 
		data: data, 
		url: '/IE-MES/report/getReportData.shtml',
        success: function (e) {
        	 var data= JSON.parse(e);
        	 if (data.result) {
        		 
        		 var materiel = data.data;
        		 try{
        			 var p = document.getElementById("materiel1");
        			 p.innerHTML ="物料" + materiel[0].name + "剩余：" + materiel[0].value;
        		 }
        		 catch(error){
        			 var p = document.getElementById("materiel1");
        			 p.innerHTML = "暂无数据";
        		 }
        		 try{
        			 var p = document.getElementById("materiel2");
        			 p.innerHTML ="物料" + materiel[1].name + "剩余：" + materiel[1].value;
        		 }
        		 catch(error){
        			 var p = document.getElementById("materiel2");
        			 p.innerHTML = "暂无数据";
        		 }
        		 try{
        			 var p = document.getElementById("materiel3");
        			 p.innerHTML ="物料" + materiel[2].name + "剩余：" + materiel[2].value;
        		 }
        		 catch(error){
        			 var p = document.getElementById("materiel3");
        			 p.innerHTML = "暂无数据";
        		 }
        		 try{
        			 var p = document.getElementById("materiel4");
        			 p.innerHTML = "物料" + materiel[3].name + "剩余：" + materiel[3].value;
        		 }
        		 catch(error){
        			 var p = document.getElementById("materiel4");
        			 p.innerHTML = "暂无数据";
        		 }
        		 try{
        			 var p = document.getElementById("materiel5");
        			 p.innerHTML ="物料" + materiel[4].name + "剩余：" + materiel[5].value;
        		 }
        		 catch(error){
        			 var p = document.getElementById("materiel5");
        			 p.innerHTML = "暂无数据";
        		 }
	        		 
        	 }else {
         		layer.msg("执行失败："+data.message);
         	}
         },
         error: function (xhr,e) {
         	layer.alert('请求失败,请联系管理员');
         } 
     });
});
</script>
<style type="text/css">
.cercleDiv
	{
		text-align:center;
		padding:10px 20px; 
		margin-top:35px;
		margin-left:2.5%;
		margin-right:2.5%;
		height:50%;
		width:15%;
		float:left;
		border-radius:15px;
		-moz-border-radius:25px; /* 老的 Firefox */
		text-align:center;  
  		vertical-align:middle;  
	}
.celclep{
	color:#FFF;
	font-size:16px;
	margin-top:0; padding:0 0;
}
</style>
<body>
	<div style="border: 2px solid #E5EDF2; height:37.5%; width:98%; position:relative; margin:10px;">
		<h4 style=" background:#FFF; display:block; position:absolute; left:10px; top:-20px; text-align:center">工单生产预览</h4>
		<div style="height:100%;overflow:auto;">
		<div style="display:block; margin-top:25px; text-align:center;">
			<div id="paging" class="pagclass" ></div>
		</div>
		</div>
	</div>
	<div style="border: 2px solid #E5EDF2; height:10%; width:98%; position:relative; margin:10px;"">
		<h4 style=" background:#FFF; display:block; position:absolute; left:10px; top:-20px; text-align:center">物料剩余预览</h4>
		<div class="table-responsive">
		<div class="cercleDiv" style="background:#4682B4">
			<p class="celclep" id="materiel1"></p>
		</div>
		<div class="cercleDiv" style="background:#8B475D">
			<p class="celclep" id="materiel2"></p>
		</div>		
		<div class="cercleDiv" style="background:#668B8B">
			<p class="celclep" id="materiel3"></p>
		</div>		
		<div class="cercleDiv" style="background:#6666CC">
			<p class="celclep" id="materiel4"></p>
		</div>		
		<div class="cercleDiv" style="background:#996699">
			<p class="celclep" id="materiel5"></p>
		</div>
		</div>
	</div>
	<div style="border: 2px solid #E5EDF2; height:37.5%; width:98%; position:relative; margin:10px;">		
		<h4 style=" background:#FFF; display:block; position:absolute; left:10px; top:-20px; text-align:center">不良产品预览</h4>
		<div style="overflow:auto;height:100%; ">
		<div class="table-responsive" style="display:block; margin-top:25px; text-align:center;">
			<div id="paging3" class="pagclass"></div>
		</div>
		</div>
	</div>
</body>
