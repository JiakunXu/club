myApp.onPageInit('cart.index', function(page) {
			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
				myApp.hideIndicator();
				var xhr = e.detail.xhr;

				if (cart_index_flag == "create") {
					view4.router.load({
								url : appUrl + "/pay/index.htm?tradeNo="
										+ xhr.responseText
							});
				} else if (cart_index_flag == "remove") {
					myApp.alert(xhr.responseText, '信息', function() {
								view4.router.refreshPage()
							});
				}
			});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});

			cart_index_check();
			$$('#cart/index/check').prop('checked', true)

			$$("input[name='cartIds']").on('change', function(e) {
						cart_index_stats();
					});
		});

function cart_index_stats() {
	var total = 0;
	var count = 0;

	$$("input[name='cartIds']").each(function(e) {
		if (this.checked) {
			total = dcmAdd(total, dcmMul($$("#cart/index/price/" + this.value)
									.val(), $$("#cart/index/quantity/"
									+ this.value).val()));
			count++;
		}
	});

	if (count == 0) {
		$$(".js-total-price").html(0);
		$$(".btn.btn-orange-dark").html("结算");
	} else {
		$$(".js-total-price").html(total);
		$$(".btn.btn-orange-dark").html("结算(" + count + ")");
	}
}

function cart_index_minus(cartId) {
	var q = $$('#cart/index/quantity/' + cartId).val();

	if (q == 1) {
		return;
	}

	cart_index_num(cartId, dcmSub(q, 1));
}

function cart_index_plus(cartId) {
	var q = $$('#cart/index/quantity/' + cartId).val();
	cart_index_num(cartId, dcmAdd(q, 1));
}

function cart_index_num(cartId, quantity) {
	$$.get(appUrl + '/cart/num.htm', {
				cartId : cartId,
				quantity : quantity
			}, function(data) {
				$$('#cart/index/quantity/' + cartId).val(data);
				$$('#cart/index/quantity/edited/' + cartId).html('×' + data);
			});
}

var cart_index_flag;

function cart_index_create() {
	cart_index_flag = "create";

	myApp.showIndicator();

	$$('#cart/index/form').attr("action", appUrl + "/trade/create.htm");

	$$('#cart/index/form').trigger("submit");
}

function cart_index_remove() {
	cart_index_flag = "remove";

	myApp.showIndicator();

	$$('#cart/index/form').attr("action", appUrl + "/cart/remove.htm");

	$$('#cart/index/form').trigger("submit");
}

function cart_index_check() {
	if ($$('#cart/index/check').prop('checked')) {
		$$('input[name="cartIds"]').prop('checked', false);

		$$(".js-total-price").html(0);
		$$(".btn.btn-orange-dark").html("结算");
	} else {
		$$('input[name="cartIds"]').prop('checked', true);

		cart_index_stats();
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