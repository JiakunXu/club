function tag_detail_update() {
	myApp.showIndicator();

	$$('#tag/detail/update').trigger("submit");
}

function tag_detail_delete() {
	var buttons1 = [{
				text : '确定删除标签？',
				label : true
			}, {
				text : '确定',
				onClick : function() {
					myApp.showIndicator();

					$$('#tag/detail/delete').trigger("submit");

				}
			}];
	var buttons2 = [{
				text : 'Cancel',
				color : 'red'
			}];
	var groups = [buttons1, buttons2];
	myApp.actions(groups);
}

myApp.onPageInit('tag.detail', function(page) {
			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
				myApp.hideIndicator();
				var xhr = e.detail.xhr;
				myApp.alert(xhr.responseText, '信息', function() {
							mainView.router.back({
										url : appUrl
												+ '/weixin/tag.framework7.htm?op=list',
										force : true,
										ignoreCache : true
									})
						});
			});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});
		});
