myApp.onPageInit('trade.list', function(page) {
			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '信息', function() {
									member_index_stats();
									myApp.getCurrentView().router.back();
								});
					});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});
		});

function trade_list_cancel(tradeNo) {
	myApp.confirm('确定取消订单？', '订单管理', function() {
				myApp.showIndicator();

				$$('#trade_list_cancel_tradeNo').val(tradeNo);
				$$('#trade/list/cancel').trigger("submit");
			});
}

function trade_list_sign(tradeNo) {
	myApp.confirm('确定收货？', '订单管理', function() {
				myApp.showIndicator();

				$$('#trade_list_sign_tradeNo').val(tradeNo);
				$$('#trade/list/sign').trigger("submit");
			});
}
