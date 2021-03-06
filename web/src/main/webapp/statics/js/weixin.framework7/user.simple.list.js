// user.simple.list.vm
function user_simple_list_goto(op, userId, name) {
	mainView.router.loadPage(appUrl + "/weixin/user.framework7.htm?op=" + op
			+ "&userId=" + userId + "&name=" + encodeURIComponent(name));
}

function user_simple_list_delete(userId) {
	myApp.confirm('确定删除成员？', '成员管理', function() {
				myApp.showIndicator();

				$$('#user_simple_list_userId').val(userId);
				$$('#user/simple/list/delete').trigger("submit");
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
