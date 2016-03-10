myApp.onPageInit('user.address', function(page) {
			$.fn.cityTools({
						provinceId : "user_address_province",
						cityId : "user_address_city",
						countyId : "user_address_area",
						dfCode : $$('#user_address_backCode').val(),
						backCodeId : "user_address_backCode"
					});

			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '信息', function() {
									myApp.getCurrentView().router.back();
								});
					});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});

		});

function user_address_create() {
	myApp.showIndicator();

	$$('#user/address/create').trigger("submit");
}