myApp.onPageInit('trade.list', function(page) {
			// 下拉刷新页面
			var ptrContent = $$('.pull-to-refresh-content');

			// 添加'refresh'监听器
			ptrContent.on('refresh', function(e) {
						// 模拟1s的加载过程
						setTimeout(function() {
									// 列表元素的HTML字符串
									var itemHTML = '';
									// 前插新列表元素
									// ptrContent.find('.more').prepend(itemHTML);
									// 加载完毕需要重置
									myApp.pullToRefreshDone();
								}, 1000);
					});

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
