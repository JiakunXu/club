$(document).ready(function() {
			$('#hideFrame').bind('load', promgtMsg);
		});

function like() {
	$('#btn1').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/trade/like.htm";
	form.target = "hideFrame";
	form.submit();
}

function unlike() {
	$('#btn2').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/trade/unlike.htm";
	form.target = "hideFrame";
	form.submit();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != undefined && failResult != "") {
		window.location.reload();
	} else if (successResult != undefined) {
		window.location.reload();
	}
}