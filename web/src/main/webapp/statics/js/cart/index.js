myApp.onPageInit('cart.index', function(page) {
			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '信息', function() {
									view4.router.refreshPage()
								});
					});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});
		});

function cart_index_minus(cartId) {
	$$.get(appUrl + '/cart/minus.htm', {
				cartId : cartId
			}, function(data) {
				$$('#cart/index/quantity').val(data);
				$$('#cart/index/quantity/edited/' + cartId).htm(data);
			});
}

function cart_index_plus(cartId) {
	$$.get(appUrl + '/cart/plus.htm', {
				cartId : cartId
			}, function(data) {
				$$('#cart/index/quantity').val(data);
				$$('#cart/index/quantity/edited/' + cartId).htm(data);
			});
}

function cart_index_remove() {
	myApp.showIndicator();

	$$('#cart/index/form').attr("action", appUrl + "/cart/remove.htm");

	$$('#cart/index/form').trigger("submit");
}

function cart_index_check() {
	if ($$('#cart/index/check').prop('checked')) {
		$$('input[name="cartId"]').prop('checked', false);
	} else {
		$$('input[name="cartId"]').prop('checked', true);
	}
}

function cart_index_edit() {
	$$('#cart/index/edit').hide();
	$$('#cart/index/edited').show();

	$$('div[id^="cart/index/quantity/edited"]').hide();
	$$('div[id="cart/index/quantity/edit"]').show();

	$$('#cart/index/select').removeClass("checked");
	$$('#cart/index/select').addClass("delete");

	$$('#cart/index/price').hide();

	$$('#cart/index/btn/create').hide();
	$$('#cart/index/btn/delete').show();

	$$('input[type="checkbox"]').prop('checked', false);
}

function cart_index_edited() {
	$$('#cart/index/edited').hide();
	$$('#cart/index/edit').show();

	$$('div[id="cart/index/quantity/edit"]').hide();
	$$('div[id^="cart/index/quantity/edited"]').show();

	$$('#cart/index/select').removeClass("delete");
	$$('#cart/index/select').addClass("checked");

	$$('#cart/index/price').show();

	$$('#cart/index/btn/delete').hide();
	$$('#cart/index/btn/create').show();

	$$('input[type="checkbox"]').prop('checked', true);
}