var operationWin = null;
var operation_resourceWin = null;
var work_orderWin = null;
var input_id, data_no, robackFun = null;

function getOperation(inputId, no){
	input_id = inputId;
	data_no = no;
	operationWin = layer.open({
		title : "操作列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getOperation.shtml'
	});
}

function getOperation2(inputId, no){
	input_id = inputId;
	data_no = no;
	operationWin = layer.open({
		title : "操作列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getOperation2.shtml'
	});
}

function getResource(inputId, no){
	input_id = inputId;
	data_no = no;
	operation_resourceWin = layer.open({
		title : "资源列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getResource.shtml'
	});
}


function getPodFunction(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "按钮维护列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getPodFunction.shtml'
	});
}

function getWorkOrder(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "工单列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getWorkOrder.shtml'
	});
}

//扩展--获取未完成订单
function getUnFinishedWorkOrder(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "未完成工单列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getUnFinishedWorkOrder.shtml'
	});
}

//确认方法
function unfinish_workshop_ok(){
	var data = grid.selectRow()[0];
	window.parent.call_input(data);
	//查询相关信息
	$.ajax({
		type: 'POST', 
		data: data, 
		url: '/IE-MES/plan/getShoporderInfoByNo.shtml',
		success: function (e) {
			var data= JSON.parse(e);
			if (data.result) {
				parent.un_finished_shoporder_robackFun(data);
			}else {
				layer.msg("加载工单信息失败："+data.message);
			}
			//关闭弹出框
			var index = parent.layer.getFrameIndex(window.name);//获取窗口索引  
			parent.layer.close(index);
		},
		error: function (xhr,e) {
			layer.alert("哎呀，好像出错了！");
			//关闭弹出框
			var index = parent.layer.getFrameIndex(window.name);//获取窗口索引  
			parent.layer.close(index);
		} 
	});
}

function getSfcInfo(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "工作中心列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/report/get_sfcinfo.shtml'
	});
}


//查询所有车间弹出框
function getfWorkCenter(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "工作中心列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getfWorkCenter.shtml'
	});
}

//查询所有产线弹出框
function getfWorkCenterLine(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "工作中心列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getfWorkCenterLine.shtml'
	});
}

//根据车间查询产线
function getfWorkCenterChild(inputId, no){
	input_id = inputId;
	data_no = no;
	var workcenter = $('#workcenter').val();
	if (!CommnUtil.notNull(workcenter)) {
		layer.msg("请先选择车间，再选择产线");
		return;
	}
	work_orderWin = layer.open({
		title : "工作中心列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getfWorkCenterChild.shtml?workcenter='+workcenter
	});
}

function getItemBom(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "物料BOM列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getItemBom.shtml'
	});
}

//获取所有物料弹出框
function getItem(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "物料列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getItem.shtml'
	});
}


//获取所有原物料弹出框
function getItemChild(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "物料列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getItemChild.shtml'
	});
}

//获取所有自生产物料弹出框
function getItemMain(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "物料列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getItemMain.shtml'
	});
}


function getRoute(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "工艺路线列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getRoute.shtml'
	});
}

function getNcCode(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "不合代码列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getNcCode.shtml'
	});
}

function getNcCodeGroup(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "不合格组列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getNcCodeGroup.shtml'
	});
}

function getSite(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "站点维护列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getSite.shtml'
	});
}

function getSfc(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "产品SFC维护列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getSfc.shtml'
	});
}

//考试编号弹出框（评分项维护私有）
function getExam(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "考试信息列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/point_grade/point_exam_list.shtml'
	});
}


//考试编号弹出框（公共）
function getExam2(inputId, no){
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "考试信息列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getExamInfoList.shtml'
	});
}

//装配SFC，显示已接收但未被使用的SFC列表弹出框
//考试编号弹出框（公共）
function getSfcByAssemble(inputId, no){
	var item_no = $('#item_no').val();
	input_id = inputId;
	data_no = no;
	work_orderWin = layer.open({
		title : "SFC列表",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/base/getSfcByAssemble.shtml?itemNo='+item_no
	});
}

function call_input2(data){
	$('#'+input_id).val(data[data_no]);
	exam_confirm(data.exam_no,data.exam_name,data.exam_start_time,data.exam_end_time,data.enabled,data[data_no]);
}

function call_input(data){
	$('#'+input_id).val(data[data_no]);
}