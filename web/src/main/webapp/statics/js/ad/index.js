// Initialize your app
var myApp = new Framework7();

// Export selectors engine
var $$ = Dom7;

// Init slider and store its instance in mySwiper variable
var swiper_0 = myApp.swiper('.swiper-0', {
			pagination : '.swiper-0 .swiper-pagination'
		});

var swiper_1 = myApp.swiper('.swiper-1', {
			pagination : '.swiper-1 .swiper-pagination',
			direction : 'vertical'
		});
