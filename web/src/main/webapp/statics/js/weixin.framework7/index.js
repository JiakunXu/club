// Initialize your app
var myApp = new Framework7({
			swipePanel : 'left',
			// Hide and show indicator during ajax requests
			onAjaxStart : function(xhr) {
				myApp.showIndicator();
			},
			onAjaxComplete : function(xhr) {
				myApp.hideIndicator();
			}
		});

// Export selectors engine
var $$ = Dom7;

// Add view
var mainView = myApp.addView('.view-main', {
			// Because we use fixed-through navbar we can enable dynamic navbar
			dynamicNavbar : true
		});

$$('.panel-close').on('click', function(e) {
			myApp.closePanel();
		});

myApp.addNotification({
			title : 'Device',
			message : myApp.device.os + " " + myApp.device.osVersion,
			media : '<i class="icon icon-f7"></i>'
		});
