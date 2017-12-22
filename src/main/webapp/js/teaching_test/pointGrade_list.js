var pageii = null;
var grid = null;
$(function() {

	grid = lyGrid({
		pagId : 'paging',
		l_column : [
				{
					colkey : "id",
					name : "id"
				},
				{
					colkey : "function",
					name : "功能"
				},
				{
					colkey : "descirption",
					name : "描述",
				/*	renderData : function(rowindex, data, rowdata, column) {
						return "<textarea rows='0' readonly style='background:transparent;border-style:none;border:0; width:100%; height:100%; resize:none;overflow:hidden;'>"
								+ data + "</textarea>";
					},*/
					height : "auto",
					width  : '45%',
					align  : 'left'
				},
				{
					colkey : "enable",
					name : "是否启用",
					renderData : function(rowindex, data, rowdata, column) {
						if (rowdata.id == 1) {
							return '必选';
						}
						switch (data) {
						case true:
							return '<input id="chechInfo" type="checkbox" checked="checked" id = "'
									+ rowdata.id + 'chk" onblur="checkBoxMouseLeave(this,'+rowindex+')"/>';
						case false:
							return '<input type="checkbox" id = "'
									+ rowdata.id + 'chk" onblur="checkBoxMouseLeave(this,'+rowindex+')"/>';
						default:
							return '必选';
						}

					},
				},
				{
					colkey : "point",
					name : "分值",
					renderData : function(rowindex, data, rowdata, column) {
						if (rowdata.id == 1) {
							return '<div><input disabled="disabled" style="width: 100%;float:left" type="text" class="form-control" value="'
									+ data
									+ '"id = "'
									+ rowdata.id
									+ 'inp" onblur="inputMouseLeave(this,'+rowindex+')"></div>';
						}
						return '<div><input style="width: 100%;float:left" type="text" class="form-control" value="'
								+ data + '"id = "' + rowdata.id + 'inp" onblur="inputMouseLeave(this,'+rowindex+')"></div>';
					}
				} ],
		jsonUrl : rootPath + '/point_grade/point_grade_findByPage.shtml?exam_no='+$('#exam_no').val(),
		usePage : false,// 是否分页
		serNumber : false,// 是否显示序号
		pageSize :25,
	});
	$("#confirm").click("click", function() {
		var data = grid.getCurrentData();
		var score_data = JSON.stringify(data);
		var saveParams = $("#pointGrade_list_condition_form").serializeJson();
		var saveParams2 = JSON.stringify(saveParams);
		var enabled = $('#isUse')[0].checked;
		if (enabled==true || enabled=="true") {
			enabled = "1";
		}else if (enabled==false || enabled=="false"){
			enabled = "0";
		}
		
		var parames_data = "score_data=" + score_data + "&exam_data="+saveParams2+"&enabled="+$('#isUse')[0].checked;
		$.ajax({
			type : 'POST',
			data : parames_data,
			url : '/IE-MES/point_grade/point_grade_update_all.shtml',
			success : function(e) {
				var data = $.parseJSON(e);
				if (data.result) {
					layer.msg(data.message);
					//同步加载
					var url = '/IE-MES/point_grade/putScore.shtml';
					var s = CommnUtil.ajax(url,{
						'exam_no':v
					},"json");
					var jsonData = s.data;
					if(s.message == 'success'){
						
					}else{
						
					}
					grid.loadData();
				}else {
					layer.msg(data.message);
				}
			},
	        error: function (xhr,e) {
	        	layer.alert('请求失败,请联系管理员');
	        } 
		}); 
	});
});
function inputMouseLeave(t,index){
	var data = grid.getCurrentData();
	data[index-1].point = t.value;
}
function checkBoxMouseLeave(t,index){
	var data = grid.getCurrentData();
	data[index-1].enable = t.checked;
}

function exam_confirm(exam_no,exam_name,exam_start_time,exam_end_time,enabled,v)
{
	exam_start_time = new Date(exam_start_time).format("yyyy-MM-dd hh:mm:ss");
	exam_end_time = new Date(exam_end_time).format("yyyy-MM-dd hh:mm:ss");
	
	$("#exam_name").val(exam_name);
	$("#exam_start_date").val(exam_start_time);
	$("#exam_end_date").val(exam_end_time);
	if(enabled == 1 || enabled == true || enabled == "true")
	{
		//$("[name = enabled]:checkbox").attr("checked", true);
		$("[name = enabled]:checkbox")[0].checked = true;
	}else {
		//$("[name = enabled]:checkbox").attr("checked", false);
		$("[name = enabled]:checkbox")[0].checked = false;
	}
	
	
	//同步加载
	var url = '/IE-MES/point_grade/putScore.shtml';
	var s = CommnUtil.ajax(url,{
		'exam_no':v
	},"json");
	var jsonData = s.data;
	if(s.message == 'success'){
		
	}else{
		
	}
	grid.loadData();
}