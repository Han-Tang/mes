$(function() {
	$("form").validate({
		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
			if (!CommnUtil.notNull($('#startDate').val)) {
				$(".l_err").css('display', 'block');
				layer.msg("计划开始时间不能为空");
				$(".l_err").html(error.html());
				return;
			}
			if (!CommnUtil.notNull($('#endDate').val)) {
				$(".l_err").css('display', 'block');
				layer.msg("计划结束时间不能为空");
				$(".l_err").html(error.html());
				return;
			}
			$(".l_err").css('display', 'none');
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
			"shopOrderFormMap.shoporder_no" : {
				required : true,
				remote : { // 异步验证是否存在
					type : "POST",
					url : 'isExist.shtml',
					data : {
						name : function() {
							return $("#shoporderId").val();
						}
					}
				}
			}
		},
		messages : {
			"shopOrderFormMap.shoporder_no" : {
				required : "请选择工单号",
				remote : "该工单已存在！"
			}
		},
		errorPlacement : function(error, element) {// 自定义提示错误位置
			$(".l_err").css('display', 'block');
			$(".l_err").html(error.html());
		},
		success : function(label) {// 验证通过后
			$(".l_err").css('display', 'none');
		}
	});

	$("#startDate").daterangepicker({
		singleDatePicker : true,
		startDate : moment().subtract(6, 'days'),
		timePicker : true, //是否显示小时和分钟  
		timePickerIncrement : 60, //时间的增量，单位为分钟  
		timePicker12Hour : false,
		format : 'YYYY-MM-DD HH:mm:ss',
	}, function(start, end, label) {//格式化日期显示框  
		$('#startDate span').append('<input type="text" class="form-control" name="shopOrderFormMap.shoporder_start_date" value="'+start.format('YYYY-MM-DD HH:mm:ss')+'">');
	});

	$("#endDate").daterangepicker({
		singleDatePicker : true,
		startDate : moment(),
		timePicker : true, //是否显示小时和分钟  
		timePickerIncrement : 60, //时间的增量，单位为分钟  
		timePicker12Hour : false,
		format : 'YYYY-MM-DD HH:mm:ss',
	}, function(start, end, label) {//格式化日期显示框  
		$('#endDate span').append('<input type="text" class="form-control" name="shopOrderFormMap.shoporder_end_date" value="'+start.format('YYYY-MM-DD HH:mm:ss')+'">');
	});
	
});
