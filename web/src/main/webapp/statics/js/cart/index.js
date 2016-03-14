function cart_index_edit() {
	$$('#cart/index/edit').hide();
	$$('#cart/index/edited').show();

	$$('#cart/index/select').removeClass("checked");
	$$('#cart/index/select').addClass("delete");

	$$('#cart/index/price').hide();

	$$('#cart/index/btn/create').hide();
	$$('#cart/index/btn/delete').show();
}

function cart_index_edited() {
	$$('#cart/index/edited').hide();
	$$('#cart/index/edit').show();

	$$('#cart/index/select').removeClass("delete");
	$$('#cart/index/select').addClass("checked");

	$$('#cart/index/price').show();

	$$('#cart/index/btn/delete').hide();
	$$('#cart/index/btn/create').show();
}