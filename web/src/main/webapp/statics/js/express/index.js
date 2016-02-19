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

myApp.onPageInit('store', function(page) {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://api.map.baidu.com/api?v=2.0&ak=57GsXSutGfXcANaX3GfW1FqZ&callback=init";
	document.body.appendChild(script);
});

function init() {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js";
	document.body.appendChild(script);

	// 百度地图API功能
	var map = new BMap.Map("allmap");
	map.centerAndZoom('上海', 11);

	// 添加带有定位的导航控件
	var navigationControl = new BMap.NavigationControl({
				// 靠左上角位置
				anchor : BMAP_ANCHOR_TOP_LEFT,
				// LARGE类型
				type : BMAP_NAVIGATION_CONTROL_LARGE,
				// 启用显示定位
				enableGeolocation : true
			});
	map.addControl(navigationControl);

	// 添加定位控件
	var geolocationControl = new BMap.GeolocationControl();
	geolocationControl.addEventListener("locationError", function(e) {
				// 定位失败事件
				alert(e.message);
			});
	map.addControl(geolocationControl);
}