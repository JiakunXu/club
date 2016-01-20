// department.list.vm
function department_list_goto(op, id, name) {
	mainView.router.loadPage(appUrl + "/weixin/department.framework7.htm?op="
			+ op + "&id=" + id + "&name=" + encodeURIComponent(name));
}