<!-- 请复制整个jsp，修改下面第三行引用的js即可 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/common/common.jspf"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>upload</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<body>
	<div class="body-content">
		<form method="post" class="form-horizontal" id="fileForm"
			enctype="multipart/form-data">
			<div class="panel-body">
				<div class="form-group">
					<div class="col-sm-12">
						<div class="input-group">
							<input type="file" name="file" style="display: none">
							<input id="filein" class="input-large" type="text"
								style="height: 33px; width: 340px;">
							<button class="btn btn-info" type="button" id="fileb">
								<span class="glyphicon glyphicon-folder-open"></span>&nbsp;&nbsp;选择文件
							</button>
						</div>
					</div>
				</div>
				<br>
				<div class="form-group" align="center">
					<button type="submit" class="btn btn-success btn-s-xs" ><span class="glyphicon glyphicon-open"></span> 上传文件</button>
					<button type="button" class="btn btn-success btn-s-xs" onclick="downDoadFile()"><span class="glyphicon glyphicon-save"></span> 下载模板</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function downDoadFile() {
			var form=$("<form>");//定义一个form表单
			form.attr("style","display:none");
			form.attr("target","");
			form.attr("method","post");
			form.attr("action",rootPath +"/message/userInfoDownLoad.shtml");
			var input1=$("<input>");
			input1.attr("type","hidden");
			input1.attr("name","exportData");
			input1.attr("value",(new Date()).getMilliseconds());
			$("body").append(form);//将表单放置在web中
			form.append(input1);

			form.submit();//表单提交 
		}
		
		$(function(){
            $("#fileb").click(function(){
                $("input[type='file']").trigger('click');
            });
            $("#filein").click(function(){
                $("input[type='file']").trigger('click');
            });
            $("input[type='file']").change(function(){
                $("#filein").val($(this).val());
            });
        });
		
		$("#fileForm").validate({
			//省略验证   
			submitHandler : function(form) {//验证成功，就提交表单
				$("#fileForm").ajaxSubmit({
					type : 'post',
					url : rootPath +'/message/studentInfoUpload.shtml', //变量
					error : function() {//请求失败处理函数  
						layer.msg('哎呀，好像出错了');
					},
					success : function(data) { //请求成功后处理函数。
						layer.msg('上传完毕');
						/* var index = parent.layer.getFrameIndex(window.name); //获取窗口索引  
						parent.layer.close(index); */
					}
				});
			}

		})
	</script>
</body>
</html>