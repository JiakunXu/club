function department_detail_update() {
	myApp.showIndicator();

	$$('#department/detail/update').trigger("submit");
}

function department_detail_delete() {
	myApp.confirm('确定删除部门？', '部门管理', function() {
				myApp.showIndicator();

				$$('#department/detail/delete').trigger("submit");
			});
}

myApp.onPageInit('department.detail', function(page) {
	$$('form.ajax-submit').on('beforeSubmit', function(e) {
			});

	$$('form.ajax-submit').on('submitted', function(e) {
		myApp.hideIndicator();
		var xhr = e.detail.xhr;
		myApp.alert(xhr.responseText, '信息', function() {
					mainView.router.back({
								url : appUrl
										+ '/weixin/department.framework7.htm?op=list',
								force : true,
								ignoreCache : true
							})
				});
	});

	$$('form.ajax-submit').on('submitError', function(e) {
				myApp.hideIndicator();
				var xhr = e.detail.xhr;
				myApp.alert(xhr.responseText, '错误');
			});
});
