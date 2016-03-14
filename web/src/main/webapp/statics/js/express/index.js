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

myApp.addNotification({
			title : '来自好社惠的消息',
			subtitle : '',
			message : '好社惠快递收发平台即将上线，敬请期待。',
			media : '<img width="33" height="33" style="border-radius:100%" src="'
					+ imgUrl + '/image/portal/logo.jpg">'
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

myApp.onPageInit('rate', function(page) {
			$.fn.cityTools({
						provinceId : "province_0",
						cityId : "city_0",
						countyId : "area_0",
						dfCode : '310'
					});

			$.fn.cityTools({
						provinceId : "province_1",
						cityId : "city_1",
						countyId : "area_1",
						dfCode : '3301'
					});
		});

myApp.onPageInit('time', function(page) {
			$.fn.cityTools({
						provinceId : "province_0",
						cityId : "city_0",
						countyId : "area_0",
						dfCode : '310'
					});

			$.fn.cityTools({
						provinceId : "province_1",
						cityId : "city_1",
						countyId : "area_1",
						dfCode : '3301'
					});
		});

myApp.onPageInit('store', function(page) {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://api.map.baidu.com/api?v=2.0&ak=57GsXSutGfXcANaX3GfW1FqZ&callback=init";
	document.body.appendChild(script);
});

myApp.onPageInit('range', function(page) {
			var e = {
				provinceId : "province",
				cityId : "city",
				countyId : "area",
				dfCode : '310'
			};
			$.fn.cityTools(e);
		});

function init() {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js";
	document.body.appendChild(script);

	var data_info = JSON
			.parse('[{"lon":121.48,"lat":31.22},{"lon":121.485,"lat":31.226},{"lon":121.482,"lat":31.229},{"lon":121.482,"lat":31.223},{"lon":121.488,"lat":31.223}]');

	// 百度地图API功能
	var map = new BMap.Map("allmap");

	map.centerAndZoom(new BMap.Point(data_info[0].lon, data_info[0].lat), 15);

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

	for (var i = 0; i < data_info.length; i++) {
		var marker = new BMap.Marker(new BMap.Point(data_info[i].lon,
				data_info[i].lat)); // 创建标注
		var content = "<div><strong>好社惠便民亭子[淡水小区店]</strong><div style='margin: 3px 0 3px 0;'>地址：淡水路100号</div><div>联系电话：13333333333</div></div>";
		map.addOverlay(marker); // 将标注添加到地图中
		addClickHandler(data_info[i].parkName, content, marker);
	}

	function addClickHandler(title, content, marker) {
		marker.addEventListener("click", function(e) {
					openInfo(title, content, marker, e)
				});
	}

	function openInfo(title, content, marker, e) {
		// 创建检索信息窗口对象
		var searchInfoWindow = null;
		searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
					title : title, // 标题
					enableAutoPan : true, // 自动平移
					searchTypes : []
				});

		searchInfoWindow.open(marker);
	}
}