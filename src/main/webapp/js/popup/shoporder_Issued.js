//回调方法
function un_finished_shoporder_robackFun(data){
	$('#numbers').val(data.data.shoporder_numbers);
	$('#shoporder_item').val(data.data.shoporder_item);
	$('#process_route').val(data.data.process_route);
	$('#startDate').val(data.data.shoporder_start_date);
	$('#endDate').val(data.data.shoporder_end_date);
	$('#shoporder_status').val(data.data.shoporder_status);
	$('#shoporder_issued_id').val(data.data.id);
	$('#issued_numbers').val(data.data.shoporder_numbers);
}

$(function() {
	$("form").validate({
		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
			if (!CommnUtil.notNull($('#workcenter').val())) {
				$(".l_err").css('display', 'block');
				$(".l_err").html("车间不能为空");
				layer.msg("车间不能为空");
				return;
			}
			if (!CommnUtil.notNull($('#workcenter_input').val())) {
				$(".l_err").css('display', 'block');
				$(".l_err").html("产线不能为空");
				layer.msg("产线不能为空");
				return;
			}
			$(".l_err").css('display', 'none');
			
			var shoporderNo = $('#workorder_input').val();
			var isLssued = true;
			$.ajax({
				type : "post",
				url : rootPath + "/plan/isLssued.shtml?shoporderNo="+shoporderNo,
				cache : false,
				async : false,
				dataType : "json",
				success : function(obj) {
					isLssued = obj;
				}
			});
			if (isLssued) {
				layer.msg("工单已下达，无需重复下达！！！", {
					icon : 5,
					shift : 6
				});
				return;
			}
			
			ly.ajaxSubmit(form, {// 验证新增是否成功
				type : "post",
				dataType : "json",
				success : function(data) {
					var rs = JSON.parse(data);
					if (rs.status == true) {
						if (CommnUtil.notNull(rs.score)) {
							layer.alert('恭喜你，添加成功，本次操作得分：'+rs.score+"，是否关闭窗口？", {
							    skin: 'layui-layer-molv' //样式类名  自定义样式
							    ,closeBtn: 1    // 是否显示关闭按钮
							    ,anim: 1 //动画类型
							    ,btn: ['关闭'] //按钮
							    ,icon: 6    // icon
							    ,yes:function(){
							    	parent.grid.loadData();
									parent.layer.close(parent.pageii);
									return false;
							    }});
							
						}else {
							layer.msg('下达成功', {
								icon : 1
							});
						}
						$("#form")[0].reset();
					} else {
						layer.msg("下达失败", {
							icon : 5,
							shift : 6
						});
					}
				}
			});
		},
		errorPlacement : function(error, element) {// 自定义提示错误位置
			$(".l_err").css('display', 'block');
			$(".l_err").html(error.html());
			layer.msg(error.html());
		},
		success : function(label) {// 验证通过后
			$(".l_err").css('display', 'none');
		}
	});
})
