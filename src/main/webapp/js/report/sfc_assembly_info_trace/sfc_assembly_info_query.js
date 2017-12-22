//正向追溯结果列表
function getData(){
	var sfc = $('#sfc').val(); 
	if (sfc==null || sfc=="") {
		layer.msg("查询条件sfc不能为空", {
			icon : 5,
			shift : 6
		});
		return;
	}
	
	var data = $("form").serialize();
	console.log(data);
	var cb = $("#container1");
	cb.html(CommnUtil.loadingImg());
	cb.load(rootPath + "/report/get_sfc_assemblyInfo_list.shtml?"+data);
}
