function recode(){
	if ($('#nc_code_group_no')==null || $('#nc_code_group_no')=="") {
		layer.msg("不良代码组不能为空！");
		return;
	}
	if ($('#nc_code_no')==null || $('#nc_code_no')=="") {
		layer.msg("不良代码不能为空！");
		return;
	}
	
	//将Form表单转换为js对象
	var data = $("#recode_nccode_form").serialize();
	var data2 = $("#process_condition_form").serialize();
	$.ajax({ 
		type: 'POST', 
		data: data+'&'+data2, 
		url: '/IE-MES/produce/recode_nccode_methods.shtml',
        success: function (e) {
        	var data= JSON.parse(e);
			data = eval('(' + data + ')');
			
			if (data.status) {
				layer.msg('操作成功', {
					icon : 1
				});
				$('#pod_task_div').empty();
			}else {
				layer.msg(data.message, {
					icon : 5,
					shift : 6,
					time : 5000
				});
			}
        },
        error: function (xhr,e) {
        	layer.msg("哎呀，好像出错了", {
				icon : 5,
				shift : 6,
				time : 5000
			});
        	$('#center_contpanel').empty();
        } 
    });
}

function close_task_div(){
	$('#pod_task_div').empty();
}