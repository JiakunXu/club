// trg.list.vm
function tag_list_goto(op, tagId, tagName) {
	mainView.router.loadPage(appUrl + "/weixin/tag.framework7.htm?op=" + op
			+ "&tagId=" + tagId + "&tagName=" + encodeURIComponent(tagName));
}

function tag_list_delete(tagId) {
	myApp.confirm('确定删除标签？', '标签管理', function() {
				myApp.showIndicator();

				$$('#tag_list_tagId').val(tagId);
				$$('#tag/list/delete').trigger("submit");
			});
}

myApp.onPageInit('tag.list', function(page) {
			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '信息', function() {
									mainView.router.refreshPage();
								});
					});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});
		});
