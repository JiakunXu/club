var $table = $('#table');

function initTable() {
	$table.bootstrapTable({
		height : getHeight(),
		columns : [[{
							title : '序号',
							field : 'tradeId',
							rowspan : 2,
							align : 'center',
							valign : 'middle',
							sortable : true
						}, {
							title : '商品信息',
							colspan : 2,
							align : 'center'
						}, {
							title : '交易信息',
							colspan : 3,
							align : 'center'
						}, {
							title : '买家信息',
							colspan : 2,
							align : 'center'
						}], [{
							field : 'itemName',
							title : '商品信息',
							align : 'center'
						}, {
							field : 'sellerName',
							title : '卖家信息',
							align : 'center'
						}, {
							field : 'tradeCode',
							title : '二维码',
							align : 'center',
							width : 1
						}, {
							field : 'tradeNo',
							title : '交易单号',
							sortable : true,
							align : 'center',
							formatter : function(value, row, index) {
								if (value == undefined) {
									return "";
								} else {
									return value;
								}
							},
							editable : {
								type : 'text',
								title : '交易单号',
								validate : function(value) {
									return '';
								},
								url : function(params) {
									var d = new $.Deferred;
									$.ajax({
												type : "post",
												url : appUrl
														+ "/trade/tradeNo.htm",
												data : {
													tradeId : params.pk,
													tradeNo : params.value,
													dateTime : new Date()
															.getTime()
												},
												success : function(data) {
													d.resolve();
												},
												error : function(data) {
													d.reject(data);
												}
											});
									return d.promise();
								}
							}
						}, {
							field : 'tradeDate',
							title : '交易时间',
							sortable : true,
							align : 'center',
							formatter : function(value, row, index) {
								if (value == undefined) {
									return "";
								} else {
									return value;
								}
							},
							editable : {
								type : 'text',
								title : '交易时间',
								defaultValue : deta,
								validate : function(value) {
									return '';
								},
								url : function(params) {
									var d = new $.Deferred;
									$.ajax({
												type : "post",
												url : appUrl
														+ "/trade/tradeDate.htm",
												data : {
													tradeId : params.pk,
													tradeDate : params.value,
													dateTime : new Date()
															.getTime()
												},
												success : function(data) {
													d.resolve();
												},
												error : function(data) {
													d.reject(data);
												}
											});
									return d.promise();
								}
							}
						}, {
							field : 'like',
							title : '评价',
							align : 'center',
							formatter : function(value, row, index) {
								if (value == 'Y') {
									return "赞";
								} else if (value == 'N') {
									return "差评";
								} else {
									return "";
								}
							}
						}, {
							field : 'likeDate',
							title : '评价时间',
							align : 'center'
						}]],
		queryParams : function(params) {
			if (params.search != undefined) {
				params.search = encodeURIComponent(params.search);
			}
			return params;
		}
	});

	// sometimes footer render error.
	setTimeout(function() {
				$table.bootstrapTable('resetView');
			}, 200);

	$(window).resize(function() {
				$table.bootstrapTable('resetView', {
							height : getHeight()
						});
			});
}

function responseHandler(res) {
	return res;
}

function detailFormatter(index, row) {
}

function getHeight() {
	return $(window).height() - $('h1').outerHeight(true) - 70;
}

$(function() {
			initTable();

			$('#hideFrame').bind('load', promgtMsg);
		});

function create() {
	$('#btn').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/trade/create.htm";
	form.target = "hideFrame";
	form.submit();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != undefined && failResult != "") {
		alert(failResult);
	} else if (successResult != undefined) {
		$('#table').bootstrapTable('refresh');
	}

	$('#btn').button('reset');
}