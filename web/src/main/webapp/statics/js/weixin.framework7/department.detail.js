function department_detail_update() {
	myApp.showIndicator();

	var form = window.document.forms['department/detail/update'];
	form.action = appUrl + "/weixin/department.framework7.htm";
	form.target = "hideFrame";
	form.submit();
}

function department_detail_delete() {
	myApp.confirm('确定删除部门？', '', function() {
				myApp.showIndicator();

				var form = window.document.forms['department/detail/delete'];
				form.action = appUrl + "/weixin/department.framework7.htm";
				form.target = "hideFrame";
				form.submit();
			});
}
