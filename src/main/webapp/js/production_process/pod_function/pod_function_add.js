jQuery.validator.addMethod("checkName", function(value, element) {
	return this.optional(element)
			|| ((value.length <= 50) && (value.length >= 3));
}, "pod功能名称由3至50位字符组合构成");
jQuery.validator.addMethod("checkabh", function(value, element) {
	return this.optional(element)
			|| ((value.length <= 50) && (value.length >= 3));
}, "pod功能编号由3至50位字符组合构成");
$(function() {
	$("form").validate({
		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
			ly.ajaxSubmit(form, {// 验证新增是否成功
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data == "success") {
						layer.confirm('添加成功!是否关闭窗口?', function(index) {
							parent.grid.loadData();
							parent.layer.close(parent.pageii);
							return false;
						});
						$("#form")[0].reset();
					} else {
						layer.alert('添加失败！', 3);
					}
				}
			});
		},
		rules : {
			"podFunctionFormMap.pod_function_no" : {
				required : true,
			},
			"podFunctionFormMap.pod_function_name" : {
				required : true,
			}
		},
		messages : {
			"podFunctionFormMap.pod_function_no" : {
				required : "请输入pod功能编号",
			},
			"podFunctionFormMap.pod_function_name" : {
				required : true,
			}
		},
		errorPlacement : function(error, element) {// 自定义提示错误位置
			$(".l_err").css('display', 'block');
			// element.css('border','3px solid #FFCCCC');
			$(".l_err").html(error.html());
		},
		success : function(label) {// 验证通过后
			$(".l_err").css('display', 'none');
		}
	});
});
