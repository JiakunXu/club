// Initialize your app
var myApp = new Framework7();

// Export selectors engine
var $$ = Dom7;

// Init slider and store its instance in mySwiper variable
var mySwiper = myApp.swiper('.swiper-container', {
			pagination : '.swiper-pagination',
			direction : 'vertical'
		});

$$('img.lazy').trigger('lazy');