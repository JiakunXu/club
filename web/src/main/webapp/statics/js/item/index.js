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

myApp.onPageInit('ship', function(page) {
			var e = {
				provinceId : "province",
				cityId : "city",
				countyId : "area",
				dfCode : '310'
			};
			$.fn.cityTools(e);
		});
