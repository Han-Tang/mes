function handle(){
	var remarks_input = $("#remarks_input").val();
	var radio = $('input:radio:checked').val();
	var sfc = $("#sfc_input").val();
	var operation = $('#goto_operation option:selected') .val();
	
	$.ajax({ 
		type: 'POST', 
		data: {'sfc':sfc,'radio':radio,'remarks':remarks_input,'operation':operation}, 
		url: '/IE-MES/qc/addNcHandleMsg.shtml',
		success: function (e) {
			var data= JSON.parse(e);
			data = eval('(' + data + ')');
			if (data.status) {
				layer.msg('操作成功', {
					icon : 1
				});
				$("#remarks_input").val('');
				$("#sfc_input").val('');
				$("#contains").hide();
			}else {
				layer.msg(data.message, {
					icon : 5,
					shift : 6,
					time : 5000
				});
			}
		},
		error: function (xhr,e) {
			layer.alert("哎呀，好像出错了！");
		} 
	});
}
