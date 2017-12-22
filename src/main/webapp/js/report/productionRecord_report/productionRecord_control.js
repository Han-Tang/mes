//正向追溯结果列表
function getData(){
	var data = $("form").serialize();
	console.log(data);
	var cb = $("#container1");
	cb.html(CommnUtil.loadingImg());
	cb.load(rootPath + "/report/get_productionRecord_list.shtml?"+data);
}
