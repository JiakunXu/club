// user.simple.list.vm
function user_simple_list_goto(op, userId, name) {
	mainView.router.loadPage(appUrl + "/weixin/user.framework7.htm?op=" + op
			+ "&userId=" + userId + "&name=" + encodeURIComponent(name));
}

function user_simple_list_delete(id) {
	myApp.confirm('确定删除部门？', '部门管理', function() {
				myApp.showIndicator();

				$$('#department_list_id').val(id);
				$$('#department/list/delete').trigger("submit");
			});
}

myApp.onPageInit('user.simple.list', function(page) {
			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '信息', function() {
									mainView.router.refreshPage();
								});
					});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});
		});
