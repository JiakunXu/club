// Initialize your app
var myApp = new Framework7({
			swipePanel : 'left',
			// Hide and show indicator during ajax requests
			onAjaxStart : function(xhr) {
				myApp.showIndicator();
			},
			onAjaxComplete : function(xhr) {
				myApp.hideIndicator();
			}
		});

// Export selectors engine
var $$ = Dom7;

// Add view
var mainView = myApp.addView('.view-main', {
			// Because we use fixed-through navbar we can enable dynamic navbar
			dynamicNavbar : true
		});

var view2 = myApp.addView('#view-2', {
			dynamicNavbar : true
		});

$$('#href-2').on('click', function() {
			view2.router.load({
						url : appUrl + "/cart/index.htm",
						ignoreCache : true,
						reload : true
					});
		});

var view3 = myApp.addView('#view-3', {
			dynamicNavbar : true
		});

function index_goto(itemId) {
	mainView.router.loadPage(appUrl + "/item/detail.htm?itemId=" + itemId);
}

function detail_goto(type) {
	mainView.router.loadPage({
				url : appUrl + "/trade/create.htm?type=" + type,
				ignoreCache : true
			});
}

myApp.onPageInit('pay.index', function(page) {
			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						getBrandWCPayRequest(xhr.responseText);
					});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});
		});

function pay() {
	myApp.showIndicator();

	$$('#pay/wxpay').trigger("submit");
}

function getBrandWCPayRequest(data) {
	data = data.replace(/&quot;/g, "\"");
	var obj = JSON.parse(data);

	try {
		WeixinJSBridge.invoke('getBrandWCPayRequest', {
					"appId" : obj.appId,
					"timeStamp" : obj.timeStamp,
					"nonceStr" : obj.nonceStr,
					"package" : obj.packageValue,
					"signType" : obj.signType,
					"paySign" : obj.paySign
				}, function(res) {
					WeixinJSBridge.log(res.err_msg);

					if (res.err_msg == 'get_brand_wcpay_request:ok') {
						mainView.router.back();
					} else if (res.err_msg == 'get_brand_wcpay_request:fail') {
						myApp.alert(res.err_code + res.err_desc + res.err_msg,
								'错误');
					}
				});
	} catch (e) {
		alert(e);
	}
}