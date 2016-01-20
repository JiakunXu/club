// Initialize your app
var myApp = new Framework7({
			swipePanel : 'left'
		});

// Export selectors engine
var $$ = Dom7;

// Add view
var mainView = myApp.addView('.view-main', {
			// Because we use fixed-through navbar we can enable dynamic navbar
			dynamicNavbar : true
		});

$(document).ready(function() {
			$('#hideFrame').on('load', promgtMsg);
		})

function promgtMsg() {
	myApp.hideIndicator();

	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;

	if (failResult != undefined && failResult != "") {
		myApp.alert(failResult, '信息');
	} else if (successResult != undefined) {
		myApp.alert(successResult, '信息');
	}
}