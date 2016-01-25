function message_voice() {
	myApp.showIndicator();

	var isChecked = $$('#message/voice/checkbox').prop('checked');
	if (isChecked) {
		$$('#message/voice/safe').val('1');
	}

	$$('#message/voice').trigger("submit");
}

function message_voice_showActionSheet() {
	var buttons1 = [{
				text : '开始录音',
				onClick : function() {
					wx.startRecord();
				}
			}, {
				text : '监听录音自动停止',
				onClick : function() {
					wx.stopRecord({
								success : function(res) {
									var localId = res.localId;

									wx.uploadVoice({
												localId : localId, // 需要上传的音频的本地ID，由stopRecord接口获得
												isShowProgressTips : 1,// 默认为1，显示进度提示
												success : function(res) {
													var serverId = res.serverId; // 返回音频的服务器端ID
													$$("#message/voice/mediaId")
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

myApp.onPageInit('message.voice', function(page) {
			wx.config({
						debug : false,
						appId : $$('#message/voice/appId').val(),
						timestamp : $$('#message/voice/timestamp').val(),
						nonceStr : $$('#message/voice/nonceStr').val(),
						signature : $$('#message/voice/signature').val(),
						jsApiList : ['startRecord', 'stopRecord', 'uploadVoice']
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