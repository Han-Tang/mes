
$(function() {
	/*	var cb = $("#pod_button_list");
	var oprationId = "${operation.id}";
	var url = "/produce/selButton.shtml?podButtonFormMap.operationId="+oprationId+"";
	alert(oprationId);
	alert(url);
	cb.html(CommnUtil.loadingImg());
	cb.load(rootPath + "/produce/selButton.shtml?podButtonFormMap.operationId="+oprationId+"");
	 */
	$("form").validate({
		submitHandler : function(form) {//必须写在验证前面，否则无法ajax提交
			
			if (!CommnUtil.notNull($('#operation_description').val())) {
				$(".l_err").css('display', 'block');
				$(".l_err").html("操作描述不能为空");
				layer.msg("操作描述不能为空");
				return;
			}
			
			ly.ajaxSubmit(form,{//验证新增是否成功
				type : "post",
				dataType:"json",
				success : function(data) {
					if (data=="success") {
						layer.confirm('更新成功!是否关闭窗口?', function(index) {
							parent.grid.loadData();
							parent.layer.close(parent.pageii);
							return false;
						});
					} else {
						layer.alert('添加失败！', 3);
					}
				}
			});
		},
		errorPlacement : function(error, element) {//自定义提示错误位置
			$(".l_err").css('display','block');
			//element.css('border','3px solid #FFCCCC');
			$(".l_err").html(error.html());
		},
		success: function(label) {//验证通过后
			$(".l_err").css('display','none');
		}
	});
});

