$(function() 
{
	$("form").validate(
	{
				submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
					ly.ajaxSubmit(form, {// 验证新增是否成功
						type : "post",
						dataType : "json",
						success : function(data) {
							var rs = JSON.parse(data);
							if (rs.result == "success") {
								if (CommnUtil.notNull(rs.score)) {
									layer.alert('恭喜你，添加成功，本次操作得分：' + rs.score
											+ "，是否关闭窗口？", {
										skin : 'layui-layer-molv',
										closeBtn : 1,
										anim : 1,
										btn : [ '关闭', '取消' ],
										icon : 6,
										yes : function() {
											parent.grid.loadData();
											parent.layer.close(parent.pageii);
											return false;
										},
										btn2 : function() {

										}
									});
								} else {
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
					"operationResourceFormMap.operationResource_no" : {
						required : true,
						remote : { // 异步验证是否存在
							type : "POST",
							url : 'operation_resource_isExist.shtml',
							data : {
								name : function() {
									return $("#operationResource_no").val();
								}
							}
						}
					},"operationResourceFormMap.operationResource_desc" : {
						required : true
					},"operationResourceFormMap.operation" : {
						required : true
					}
				},
				messages : {
					"operationResourceFormMap.operationResource_no" : {
						required : "请输入资源编号",
						remote : "该资源编号已经存在"
					},"operationResourceFormMap.operationResource_desc" : {
						required : "请输入资源描述"
					},"operationResourceFormMap.operation" : {
						required : "请选择默认操作"
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