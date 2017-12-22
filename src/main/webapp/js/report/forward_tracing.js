//正向追溯结果列表
function getData(){
	var cb = $("#container1");
	cb.html(CommnUtil.loadingImg());
	cb.load(rootPath + "/capacity_report/forward_tracing_list.shtml");
}