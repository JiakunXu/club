$(document).ready(function() {
			$('#hideFrame').on('load', promgtMsg);
		})

function save(op) {
	if (op == "delete") {
		$('#dialogConfirm').show();
		return;
	}

	showLoadingToast();

	var form = window.document.forms[0];
	form.action = appUrl + "/weixin/user.htm?op=user/" + op;
	form.target = "hideFrame";
	form.submit();
}

function hideDialogConfirm() {
	$('#dialogConfirm').hide();
}

function deleteUser() {
	hideDialogConfirm();
	showLoadingToast();

	var form = window.document.forms[1];
	form.action = appUrl + "/weixin/user.htm?op=user/delete";
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