var grid_ = null;

$(function() {
	$('#item_sfc').keypress(function (event){
		if (event.which == 13 ) {
			if (!CommnUtil.notNull($('#item').val())){
				layer.msg("请先选择接收物料");
				return;
			}
			if (!CommnUtil.notNull($('#item_sfc').val())){
				layer.msg("接收SFC不能为空，请扫描SFC后再试");
				return;
			}
			$('#canner_btn').removeAttr('disabled');
			$('#finish_btn').removeAttr('disabled');
			tableAddLine();
	   }
	});
	
	$("form").validate({
		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
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
			"workCenterFormMap.workcenter_no" : {
				required : true,
				remote : { // 异步验证是否存在
					type : "POST",
					url : 'isExist.shtml',
					data : {
						name : function() {
							return $("#workcenter_no").val();
						}
					}
				}
			}
		},
		messages : {
			"workCenterFormMap.workcenter_no" : {
				required : "请输入工作中心编号",
				remote : "该工作中心已经存在"
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
	
	$('#canner_btn').click(function () {
		for (var i=1;i<$('table tr').length-1;i++) {
			var input = $("table tr").eq(i).find("td").find("input[type=checkbox]");
			if (input[0].checked) {
				input.parent().parent().remove();
	        }
		}
	});
	
	$('#finish_btn').click(function () {
		sfcs = "";
		item_nos = "";
		for (var i=1;i<$('table tr').length-1;i++) {
			sfcs = sfcs +$("table tr").eq(i).find("td").eq(2).text();
			item_nos = item_nos +$("table tr").eq(i).find("td").eq(3).text();
			if (i<$('table tr').length-2) {
				sfcs = sfcs + ",";
				item_nos = item_nos + ",";
			}
		}
		$('#item_nos').val(item_nos);
		$('#item_sfcs').val(sfcs);
		$("form").submit();
	});
	
});



function tableAddLine(){
	//判断是否存在，存在则提示已接收
	if (CommnUtil.notNull(grid_)) {
		var isexist = false;
		for (var i=0;i<$('table tr').length;i++) {
			if ($("table tr").eq(i).find("td").eq(2).text()==$('#item_sfc').val() &&
					$("table tr").eq(i).find("td").eq(3).text()==$('#item').val()) {
				layer.msg("物料已接收，不允许重复接收");
				isexist = true;
			}
		}
		if (isexist){
			return;
		}
	}
	
	var parames = "sfc="+$('#item_sfc').val();
	parames = parames + '&item='+$('#item').val();
	parames = parames + '&site='+$('#site').val();
	if (CommnUtil.notNull(grid_)) {
		var $sfc = $('#item_sfc').val();
		var $item_no;
		var $item_name;
		var $item_desc;
		var $site;
		var $by_user;
		
		//同步加载
		var url = '/IE-MES/workshop_inventory/workshop_inventory_getItemInfo2.shtml';
		var s = CommnUtil.ajax(url,{
			'item':$('#item').val(),
			'site':$('#site').val()
		},"json");
		var jsonData = s.data;
		if(s.result == true){
			$item_no = jsonData.item_no;
			$item_name = jsonData.item_name;
			$item_desc = jsonData.item_desc;
			$site = jsonData.site;
			$by_user = jsonData.by_user;
		}else{
			
		}
		
		var $tr=$("<tr align='center'><td style='text-align:center;width: 15px;display: none;'>"+$("table tr").length+"</td><td><input type='checkbox'></td><td>"+$sfc+"</td><td>"+$item_no+"</td><td>"+$item_name+"</td><td>"+$item_desc+
				"</td><td>"+$site+"</td><td>"+$by_user+"</td></tr>"); 
		$('#mytable').append($tr);
		return;
	}
	
	grid_ = lyGrid({
		pagId : 'item_inventory_paging',
		l_column : [ {
			colkey : "sfc",
			name : "sfc",
		}, {
			colkey : "item_no",
			name : "物料编号",
		}, {
			colkey : "item_name",
			name : "物料名称",
		}, {
			colkey : "item_desc",
			name : "物料描述",
		}, {
			colkey : "site",
			name : "挂靠站点"
		}, {
			colkey : "by_user",
			name : "创建人"
		}],
		jsonUrl : rootPath + '/workshop_inventory/workshop_inventory_getItemInfo.shtml?'+parames,
		dymCol:true,
		checkbox : true,
		serNumber : false
	});
}
