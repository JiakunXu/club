$(document).ready(function() {
			$('#hideFrame').on('load', promgtMsg);
		})

function save(op) {
	showLoadingToast();

	var form = (op == 'convertToOpenId')
			? window.document.forms[0]
			: window.document.forms[1];
	form.action = appUrl + "/weixin/auth.htm?op=auth/" + op;
	form.target = "hideFrame";
	form.submit();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;

	hideLoadingToast();

	if (failResult != undefined && failResult != "") {
		dialogAlert(failResult);
	} else if (successResult != undefined) {
		dialogAlert(successResult);
	}
}

function dialogAlert(content) {
	$('#content').html(content);
	$('#dialogAlert').show();
}

function hideDialogAlert() {
	$('#dialogAlert').hide();
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