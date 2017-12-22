function getData(){
	var sfc = $("#sfc_input").val();
	var cb = $("#contains");
	cb.html(CommnUtil.loadingImg());
	cb.load(rootPath + "/qc/getNcHandleSFCData.shtml?sfc="+sfc);
	$('#contains').show();
}

