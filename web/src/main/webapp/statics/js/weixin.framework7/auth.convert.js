function auth_convert_convertToOpenId() {
	myApp.showIndicator();

	$$('#auth/convert/convertToOpenId').trigger("submit");
}

function auth_convert_convertToUserId() {
	myApp.showIndicator();

	$$('#auth/convert/convertToUserId').trigger("submit");
}

myApp.onPageInit('auth.convert', function(page) {
			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '信息', function() {
									// mainView.router.back();
								});
					});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});
		});