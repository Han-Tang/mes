//显示装配列表
function assemblingUI(){
	if (!valadata()) {
		return;
	}
	$("#center_contpanel2").empty();
	var cb = $("#center_contpanel2");
	cb.html(CommnUtil.loadingImg());
	cb.load(rootPath + "/produce/assemble_item_list.shtml");
}

//显示装配组件信息
function assembling(){
	if (grid.selectRow().length>1) {
		layer.msg("一次只能选择一个组件进行组装",{time:5000});
		return;
	}
	if (grid.selectRow()[0].surplus_number==0) {
		layer.msg("该组件已经装配过了，不能重复组装！",{time:5000});
		return;
	}

	var item_no = grid.selectRow()[0].item_no;
	var item_name = grid.selectRow()[0].item_name;
	var item_desc = grid.selectRow()[0].item_desc;
	var operation_no = grid.selectRow()[0].operation_no;
	var wc_sfc = $('#wc_sfc').val();

	var sb = 'item_no='+item_no + 
	'&item_name='+item_name+
	'&item_desc='+item_desc+
	'&operation_no='+operation_no+
	'&wc_sfc='+wc_sfc;
	var db = $("#pod_task_div");
	db.html(CommnUtil.loadingImg());
	db.load(rootPath + "/produce/assemble_item.shtml?"+sb);
}

//开始
function assemblingStart(){
	var data = $("#process_condition_form").serialize();
	if (!valadata()) {
		return;
	}
	
	//判断当前数据是否存在
	$.ajax({ 
		type: 'POST', 
		data: data, 
		url: '/IE-MES/produce/assemblingStartValadata.shtml',
		success: function (e) {
			var data= JSON.parse(e);
			data = eval('(' + data + ')');
			if (data.status) {
				var data = $("#process_condition_form").serialize();
				var db = $("#center_contpanel");
				db.html(CommnUtil.loadingImg());
				$("#center_contpanel").empty();
				db.load(rootPath + "/produce/process_start.shtml?"+data);
			}else {
				layer.msg(data.message, {
					icon : 5,
					shift : 6,
					time : 5000
				});
			}
		},
		error: function (xhr,e) {
			layer.alert("哎呀，好像出错了！",{time:3000});
		} 
	});
}

//完成
function assemblingFinish(){
	if (!valadata()) {
		return;
	}
	//1、调用完成后台方法
	var data = $("#process_condition_form").serialize();
	$.ajax({ 
		type: 'POST', 
		data: data, 
		url: '/IE-MES/produce/process_finish.shtml',
		success: function (e) {
			var data= JSON.parse(e);
			data = eval('(' + data + ')');
			
			if (data.status) {
				layer.msg('操作成功', {
					icon : 1
				});
				item_lsit_grid.loadData();
				$('#center_contpanel2').empty();
				$('#pod_task_div').empty();
			}else {
				layer.msg(data.message, {
					icon : 5,
					shift : 6,
					time : 5000
				});
			}
		},
		error: function (xhr,e,errorThrown) {
			layer.msg("哎呀，好像出错了", {
				icon : 5,
				shift : 6,
				time : 5000
			});
		} 
	});
}

function valadata(){
	if ($('#workorder_input').val()=='') {
		layer.msg('工单不能为空',{time:3000});
		return false;
	}
	if ($('#operation_input').val()=='') {
		layer.msg('操作不能为空',{time:3000});
		return false;
	}
	if ($('#resource_input').val()=='') {
		layer.msg('资源不能为空',{time:3000});
		return false;
	}
	if ($('#wc_sfc').val()=='') {
		layer.msg('车间作业不能为空',{time:3000});
		return false;
	}
	return true;
}

function assembling_canner(){
	$('#center_contpanel2').empty();
}

//记录不良
function recode_nccode_ui(){
	if (!valadata()) {
		return;
	}
	
	var sfc = $('#wc_sfc').val();
	var operation = $('#operation_input').val();
	var workorder = $('#workorder_input').val();
	
	//同步加载
	var url = '/IE-MES/produce/isInProduction.shtml';
	var s = CommnUtil.ajax(url,{
		'sfc':sfc,
		'operation':operation,
		'shoporder':workorder
	},"json");
	
	var jsonData = s.data;
	if(s.result == true){
		var cb = $("#pod_task_div");
		cb.html(CommnUtil.loadingImg());
		cb.load(rootPath + "/produce/recode_nccode_ui.shtml");	
	}else{
		layer.msg(s.message,{time:5000});
	}
	
	
	
}

//动态加载pod_button
function findButton(operation_no){
	$('#button_active').empty();

	//同步加载
	var url = '/IE-MES/produce/operation_input_changed.shtml';
	var s = CommnUtil.ajax(url,{
		'operationNo':operation_no
	},"json");
	var jsonData = s.data;
	if(s.message == 'success'){
		var buttonList = jsonData.buttonList;
		var functionList = jsonData.functionList;
		for(var i=0 ;i<buttonList.length;i++){
			var button = '        <button type="button" class="'+buttonList[i].pod_icon+'" onclick="'+functionList[i].pod_function_url+'()">'+buttonList[i].pod_button_name+'</button>       ';
			$("#button_active").append(button);
		}	
	}else{
		layer.msg("哎呀，好像出错了！");
	}

	/*	//异步加载
	  $.ajax({ 
		type: 'POST',
		data: {'operationNo':operation_no}, 
		url: '/IE-MES/produce/operation_input_changed.shtml',
		success: function (e) {
			var data= JSON.parse(e);
			var jsonData = data.data;
			if(jsonData != null){
				var buttonList = jsonData.buttonList;
				var functionList = jsonData.functionList;
				for(var i=0 ;i<buttonList.length;i++){
					var button = '        <button type="button" class="'+buttonList[i].pod_icon+'" onclick="'+functionList[i].pod_function_url+'()">'+buttonList[i].pod_button_name+'</button>       ';
					$("#button_active").append(button);
				}		
			}
		},
		error: function (xhr,e) {
			alert(e);
		}
	});*/
}
