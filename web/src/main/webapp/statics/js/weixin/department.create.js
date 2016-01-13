$(document).ready(function() {
			$('#hideFrame').on('load', promgtMsg);
		})

function save() {
	showLoadingToast();

	var form = window.document.forms[0];
	form.action = appUrl + "/weixin/department.htm?op=department/create";
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