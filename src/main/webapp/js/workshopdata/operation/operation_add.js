$(function() {
	var cb = $("#pod_button_list");
	cb.html(CommnUtil.loadingImg());
	cb.load(rootPath + "/produce/selButton.shtml");
	
	$("form").validate({
		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
			
			if (!CommnUtil.notNull($('#workcenter').val())) {
				$(".l_err").css('display', 'block');
				$(".l_err").html("工作中心不能为空");
				layer.msg("工作中心不能为空");
				return;
			}
			
			if (!CommnUtil.notNull($('#operation_no').val())) {
				$(".l_err").css('display', 'block');
				$(".l_err").html("操作编号不能为空");
				layer.msg("操作编号不能为空");
				return;
			}
			
			if (!CommnUtil.notNull($('#operation_description').val())) {
				$(".l_err").css('display', 'block');
				$(".l_err").html("操作描述不能为空");
				layer.msg("操作描述不能为空");
				return;
			}
			
			ly.ajaxSubmit(form, {// 验证新增是否成功
				type : "post",
				dataType : "json",
				success : function(data) {
					var rs = JSON.parse(data);
					if (rs.result == "success") {
						if (CommnUtil.notNull(rs.score)) {
							layer.alert('恭喜你，添加成功，本次操作得分：'+rs.score+"，是否关闭窗口？", {
							    skin: 'layui-layer-molv' //样式类名  自定义样式
							    ,closeBtn: 1    // 是否显示关闭按钮
							    ,anim: 1 //动画类型
							    ,btn: ['关闭','取消'] //按钮
							    ,icon: 6    // icon
							    ,yes:function(){
							    	parent.grid.loadData();
									parent.layer.close(parent.pageii);
									return false;
							    }
							    ,btn2:function(){
							    	
						    }});
							
						}else {
							layer.confirm('添加成功!是否关闭窗口?', function(index) {
								parent.grid.loadData();
								parent.layer.close(parent.pageii);
								return false;
							});
						}
						$("#form")[0].reset();
					} else {
						layer.alert('添加失败！', 3);
					}
				}
			});
		},
		rules : {
			"operationFormMap.operation_no" : {
				required : true,
				remote : { // 异步验证是否存在
					type : "POST",
					url : 'operation_isExist.shtml',
					data : {
						name : function() {
							return $("#operation_no").val();
						}
					}
				}
			}
		},
		messages : {
			"operationFormMap.operation_no" : {
				required : "请输入工艺路线编号",
				remote : "该工艺路线编号已经存在"
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
