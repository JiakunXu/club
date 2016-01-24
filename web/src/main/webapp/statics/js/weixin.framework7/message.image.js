function message_image() {
	myApp.showIndicator();

	var isChecked = $$('#message/image/checkbox').prop('checked');
	if (isChecked) {
		$$('#message/image/safe').val('1');
	}

	$$('#message/image').trigger("submit");
}

function message_image_showActionSheet() {
	var buttons1 = [{
		text : '本地选图或拍照',
		onClick : function() {
			wx.chooseImage({
						count : 1, // 默认9
						sizeType : ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
						sourceType : ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
						success : function(res) {
							var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片

							wx.uploadImage({
										localId : localIds[0], // 需要上传的图片的本地ID，由chooseImage接口获得
										isShowProgressTips : 1,// 默认为1，显示进度提示
										success : function(res) {
											var serverId = res.serverId; // 返回图片的服务器端ID
											$$("#message/image/mediaId")
													.val(serverId);
										}
									});
						}
					});
		}
	}];
	var buttons2 = [{
				text : 'Cancel',
				color : 'red'
			}];
	var groups = [buttons1, buttons2];
	myApp.actions(groups);
}

myApp.onPageInit('message.image', function(page) {
			wx.config({
						debug : false,
						appId : $$('#message/image/appId').val(),
						timestamp : $$('#message/image/timestamp').val(),
						nonceStr : $$('#message/image/nonceStr').val(),
						signature : $$('#message/image/signature').val(),
						jsApiList : ['chooseImage', 'uploadImage']
					});

			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '信息', function() {
									// mainView.router.back();
								});
					});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});
		});