function user_detail_update() {
	myApp.showIndicator();

	$$('#user/detail/update').trigger("submit");
}

function user_detail_delete() {
	myApp.confirm('确定删除成员？', '成员管理', function() {
				myApp.showIndicator();

				$$('#user/detail/delete').trigger("submit");
			});
}

myApp.onPageInit('user.detail', function(page) {
	$$('form.ajax-submit').on('beforeSubmit', function(e) {
			});

	$$('form.ajax-submit').on('submitted', function(e) {
		myApp.hideIndicator();
		var xhr = e.detail.xhr;
		myApp.alert(xhr.responseText, '信息', function() {
					mainView.router.back({
								url : appUrl
										+ '/weixin/user.framework7.htm?op=simplelist',
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
