// user.list.vm
function user_list_goto(op, userId, name) {
	mainView.router.loadPage(appUrl + "/weixin/user.framework7.htm?op=" + op
			+ "&userId=" + userId + "&name=" + encodeURIComponent(name));
}

function user_list_delete(userId) {
	myApp.confirm('确定删除成员？', '成员管理', function() {
				myApp.showIndicator();

				$$('#user_list_id').val(userId);
				$$('#user/list/delete').trigger("submit");
			});
}

myApp.onPageInit('user.list', function(page) {
			// Pull to refresh content
			var ptrContent = $$('.pull-to-refresh-content');

			// Add 'refresh' listener on it
			ptrContent.on('refresh', function(e) {
						setTimeout(function() {
									// List item html
									var itemHTML = '<li class="item-content">'
											+ '<div class="item-inner">'
											+ '<div class="item-title">我测试啊我测试</div>'
											+ '</div>' + '</div>' + '</li>';
									// Prepend new list element
									ptrContent.find('ul').prepend(itemHTML);
									// When loading done, we need to reset it
									myApp.pullToRefreshDone();
								}, 1000);
					});

			// Loading flag
			var loading = false;

			// Attach 'infinite' event handler
			$$('.infinite-scroll').on('infinite', function() {

				// Exit, if loading in progress
				if (loading)
					return;

				// Set loading flag
				loading = true;

				// Emulate 1s loading
				setTimeout(function() {
							// Reset loading flag
							loading = false;

							// Generate new items HTML
							var html = '<li class="item-content">'
									+ '<div class="item-inner">'
									+ '<div class="item-title">我测试啊我测试</div>'
									+ '</div>' + '</div>' + '</li>';

							// Append new items
							$$('.list-block ul').append(html);

							// Update last loaded index
							lastIndex = $$('.list-block li').length;
						}, 1000);
			});

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
