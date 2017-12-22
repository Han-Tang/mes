
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
			"podButtonFormMap.pod_button_no" : {
				required : true,
			},
			"podButtonFormMap.pod_button_name" : {
				required : true,
			}
		},
		messages : {
			"podButtonFormMap.pod_button_no" : {
				required : "请输入pod按钮编号",
			},
			"podButtonFormMap.pod_button_name" : {
				required : "请输入pod按钮编号",
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

	//加载按钮
	showBut("按钮");
	$("#pod_button_name").change(function(){
		showBut($("#pod_button_name").val());
		if($("#pod_button_name").val() == ''){
			showBut("按钮");
		}
	});
});

function toBut(b){
	$("#pod_icon").val($("#"+b.id).attr('class'));
	
}

function showBut(value){
	var bb = $("#but");
	bb.html('');
	bb.append('   <button type="button"  onclick="toBut(this)" id = "startFun" class="btn btn-primary marR10">'+value+'</button></span>');
	bb.append('   <button type="button"  onclick="toBut(this)" id = "passFun" class="btn btn-default">'+value+'</button>');
	bb.append('   <button type="button"  onclick="toBut(this)" id = "successFun" class="btn btn-success">'+value+'</button>');
	bb.append('   <button type="button"  onclick="toBut(this)" id = "assemblingFun" class="btn btn-info">'+value+'</button>');
	bb.append('   <button type="button"  onclick="toBut(this)" id = "badRecordFun" class="btn btn-warning">'+value+'</button>');
	bb.append('   <button type="button"  onclick="toBut(this)" id = "unfinishedFun" class="btn btn btn-grey marR10">'+value+'</button>');
	bb.append('   <button type="button"  onclick="toBut(this)" id = "overFun" class="btn btn-default">'+value+'</button>');
}
