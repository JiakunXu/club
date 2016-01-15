$(document).ready(function() {
			$('#chooseImage').click(function() {
						wx.chooseImage({
									count : 1, // 默认9
									sizeType : ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
									sourceType : ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
									success : function(res) {
										var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片

										wx.uploadImage({
													localId : localIds[0], // 需要上传的图片的本地ID，由chooseImage接口获得
													isShowProgressTips : 1,// 默认为1，显示进度提示
													success : function(res) {
														var serverId = res.serverId; // 返回图片的服务器端ID
														$("#mediaId")
																.val(serverId);
													}
												});
									}
								});
					});

			$('#hideFrame').on('load', promgtMsg);
		})

function send() {
	showLoadingToast();

	var form = window.document.forms[0];
	form.action = appUrl + "/weixin/message.htm?op=send/image";
	form.target = "hideFrame";
	form.submit();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;

	hideLoadingToast();

	if (failResult != undefined && failResult != "") {
		alert(failResult);
	} else if (successResult != undefined) {
		showToast();
	}
}

function showActionSheet() {
	var mask = $('#mask');
	var weuiActionsheet = $('#weui_actionsheet');
	weuiActionsheet.addClass('weui_actionsheet_toggle');
	mask.show().addClass('weui_fade_toggle').click(function() {
				hideActionSheet(weuiActionsheet, mask);
			});
	$('#actionsheet_cancel').click(function() {
				hideActionSheet(weuiActionsheet, mask);
			});
	weuiActionsheet.unbind('transitionend').unbind('webkitTransitionEnd');

	function hideActionSheet(weuiActionsheet, mask) {
		weuiActionsheet.removeClass('weui_actionsheet_toggle');
		mask.removeClass('weui_fade_toggle');
		weuiActionsheet.on('transitionend', function() {
					mask.hide();
				}).on('webkitTransitionEnd', function() {
					mask.hide();
				})
	}
}

function showToast() {
	var $toast = $('#toast');
	if ($toast.css('display') != 'none') {
		return;
	}

	$toast.show();
	setTimeout(function() {
				$toast.hide();
			}, 2000);
}

function showLoadingToast() {
	var $loadingToast = $('#loadingToast');
	if ($loadingToast.css('display') != 'none') {
		return;
	}

	$loadingToast.show();
}

function hideLoadingToast() {
	var $loadingToast = $('#loadingToast');
	if ($loadingToast.css('display') == 'none') {
		return;
	}

	$loadingToast.hide();
}