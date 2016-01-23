function message_text() {
	myApp.showIndicator();

	var isChecked = $$('#message/text/checkbox').prop('checked');
	if (isChecked) {
		$$('#message/text/safe').val('1');
	}

	$$('#message/text').trigger("submit");
}

function message_text_showActionSheet() {
	var buttons1 = [{
				text : '开始录音',
				onClick : function() {
					wx.startRecord();
				}
			}, {
				text : '识别音频并返回识别结果',
				onClick : function() {
					wx.stopRecord({
								success : function(res) {
									var localId = res.localId;

									wx.translateVoice({
												localId : localId, // 需要识别的音频的本地Id，由录音相关接口获得
												isShowProgressTips : 1, // 默认为1，显示进度提示
												success : function(res) {
													// 语音识别的结果
													$$("#message/text/content")
															.val(res.translateResult);
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

myApp.onPageInit('message.text', function(page) {
			wx.config({
						debug : false,
						appId : $$('#message/text/appId').val(),
						timestamp : $$('#message/text/timestamp').val(),
						nonceStr : $$('#message/text/nonceStr').val(),
						signature : $$('#message/text/signature').val(),
						jsApiList : ['startRecord', 'stopRecord',
								'translateVoice']
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