$(function() {
	$('#supplier').focus();
})

$('#supplier').keypress(function (e){
	if (e.which == 13) {
		if (!CommnUtil.notNull($('#supplier').val())) {
			layer.msg("装配的物料SFC不能为空");
			return;
		}
		$('#assemble_num').focus();
		$("#assemble_num").select();
	}
});

$('#assemble_num').keypress(function (e){
	if (e.which == 13) {
		if (!CommnUtil.notNull($('#assemble_num').val())) {
			layer.msg("装配的数量不能为空");
			return;
		}
		fit_out();
	}
});

function fit_out(){
	if ($('#assemble_num')==null || $('#assemble_num')=="") {
		layer.msg("装配数量不能为空", {
			icon : 5,
			shift : 6
		});
		return;
	}
	//判断剩余装配数量是否小于当前装配数量，如果是，则提示装配数量不能大于剩余装配数量
	var surplus_number = grid.selectRow()[0].surplus_number;
	if ($('#assemble_num').val()>surplus_number) {
		layer.msg("装配数量不能大于剩余装配数量", {
			icon : 5,
			shift : 6
		});
		return;
	}
	//将Form表单转换为js对象
	var data = $("#assemble_item_form").serialize();
	$.ajax({ 
		type: 'POST', 
		data: data, 
		url: '/IE-MES/produce/fit_out.shtml',
        success: function (e) {
        	
        	var data= JSON.parse(e);
			data = eval('(' + data + ')');
			if (data.status) {
				layer.msg("操作成功");
        		$('#pod_task_div').empty();
        		assemblingUI();
			}else {
				layer.msg(data.message, {
					icon : 5,
					shift : 6,
					time : 5000
				});
			}
        },
        error: function (xhr,e) {
        	layer.msg("哎呀，好像出错了！", {
    			icon : 5,
    			shift : 6
    		});
        } 
    });
}

function close_task_div(){
	$('#pod_task_div').empty();
}