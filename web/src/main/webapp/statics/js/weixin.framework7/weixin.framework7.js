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

$$('.panel-close').on('click', function(e) {
			myApp.closePanel();
		});

myApp.addNotification({
			title : 'Device',
			message : myApp.device.os + " " + myApp.device.osVersion,
			media : '<i class="icon icon-f7"></i>'
		});

function department_create() {
	myApp.showIndicator();

	$$('#department/create').trigger("submit");
}

myApp.onPageInit('department.create', function(page) {
			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '信息', function() {
									mainView.router.back();
								});
					});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});
		});

// department.list.vm
function department_list_goto(op, id, name) {
	mainView.router.loadPage(appUrl + "/weixin/department.framework7.htm?op="
			+ op + "&id=" + id + "&name=" + encodeURIComponent(name));
}

function department_list_delete(id) {
	myApp.confirm('确定删除部门？', '部门管理', function() {
				myApp.showIndicator();

				$$('#department_list_id').val(id);
				$$('#department/list/delete').trigger("submit");
			});
}

myApp.onPageInit('department.list', function(page) {
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

function department_detail_update() {
	myApp.showIndicator();

	$$('#department/detail/update').trigger("submit");
}

function department_detail_delete() {
	myApp.confirm('确定删除部门？', '部门管理', function() {
				myApp.showIndicator();

				$$('#department/detail/delete').trigger("submit");
			});
}

myApp.onPageInit('department.detail', function(page) {
	$$('form.ajax-submit').on('beforeSubmit', function(e) {
			});

	$$('form.ajax-submit').on('submitted', function(e) {
		myApp.hideIndicator();
		var xhr = e.detail.xhr;
		myApp.alert(xhr.responseText, '信息', function() {
					mainView.router.back({
								url : appUrl
										+ '/weixin/department.framework7.htm?op=list',
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

function user_create() {
	myApp.showIndicator();

	$$('#user/create').trigger("submit");
}

myApp.onPageInit('user.create', function(page) {
			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '信息', function() {
									mainView.router.back();
								});
					});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});
		});

// user.simple.list.vm
function user_simple_list_goto(op, userId, name) {
	mainView.router.loadPage(appUrl + "/weixin/user.framework7.htm?op=" + op
			+ "&userId=" + userId + "&name=" + encodeURIComponent(name));
}

function user_simple_list_delete(userId) {
	myApp.confirm('确定删除成员？', '成员管理', function() {
				myApp.showIndicator();

				$$('#user_simple_list_userId').val(userId);
				$$('#user/simple/list/delete').trigger("submit");
			});
}

myApp.onPageInit('user.simple.list', function(page) {
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

// user.list.vm
function user_list_goto(op, userId, name) {
	mainView.router.loadPage(appUrl + "/weixin/user.framework7.htm?op=" + op
			+ "&userId=" + userId + "&name=" + encodeURIComponent(name));
}

function user_list_delete(userId) {
	myApp.confirm('确定删除成员？', '成员管理', function() {
				myApp.showIndicator();

				$$('#user_list_id').val(userId);
				$$('#user/list/delete').trigger("submit");
			});
}

myApp.onPageInit('user.list', function(page) {
			// Pull to refresh content
			var ptrContent = $$('.pull-to-refresh-content');

			// Add 'refresh' listener on it
			ptrContent.on('refresh', function(e) {
						setTimeout(function() {
									// List item html
									var itemHTML = '<li class="item-content">'
											+ '<div class="item-inner">'
											+ '<div class="item-title">我测试啊我测试</div>'
											+ '</div>' + '</div>' + '</li>';
									// Prepend new list element
									ptrContent.find('ul').prepend(itemHTML);
									// When loading done, we need to reset it
									myApp.pullToRefreshDone();
								}, 1000);
					});

			// Loading flag
			var loading = false;

			// Attach 'infinite' event handler
			$$('.infinite-scroll').on('infinite', function() {

				// Exit, if loading in progress
				if (loading)
					return;

				// Set loading flag
				loading = true;

				// Emulate 1s loading
				setTimeout(function() {
							// Reset loading flag
							loading = false;

							// Generate new items HTML
							var html = '<li class="item-content">'
									+ '<div class="item-inner">'
									+ '<div class="item-title">我测试啊我测试</div>'
									+ '</div>' + '</div>' + '</li>';

							// Append new items
							$$('.list-block ul').append(html);

							// Update last loaded index
							lastIndex = $$('.list-block li').length;
						}, 1000);
			});

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

function user_detail_update() {
	myApp.showIndicator();

	$$('#user/detail/update').trigger("submit");
}

function user_detail_delete() {
	myApp.confirm('确定删除成员？', '成员管理', function() {
				myApp.showIndicator();

				$$('#user/detail/delete').trigger("submit");
			});
}

myApp.onPageInit('user.detail', function(page) {
	$$('form.ajax-submit').on('beforeSubmit', function(e) {
			});

	$$('form.ajax-submit').on('submitted', function(e) {
		myApp.hideIndicator();
		var xhr = e.detail.xhr;
		myApp.alert(xhr.responseText, '信息', function() {
					mainView.router.back({
								url : appUrl
										+ '/weixin/user.framework7.htm?op=simplelist',
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

function user_invite() {
	myApp.showIndicator();

	$$('#user/invite').trigger("submit");
}

myApp.onPageInit('user.invite', function(page) {
			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '信息', function() {
									mainView.router.back();
								});
					});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});
		});

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

function message_video() {
	myApp.showIndicator();

	$$('#message/video').trigger("submit");
}

myApp.onPageInit('message.video', function(page) {
			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '信息', function() {
									mainView.router.back();
								});
					});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});
		});

function message_file() {
	myApp.showIndicator();

	$$('#message/file').trigger("submit");
}

myApp.onPageInit('message.file', function(page) {
			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '信息', function() {
									mainView.router.back();
								});
					});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});
		});

function auth_convert_convertToOpenId() {
	myApp.showIndicator();

	$$('#auth/convert/convertToOpenId').trigger("submit");
}

function auth_convert_convertToUserId() {
	myApp.showIndicator();

	$$('#auth/convert/convertToUserId').trigger("submit");
}

myApp.onPageInit('auth.convert', function(page) {
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

function tag_create() {
	myApp.showIndicator();

	$$('#tag/create').trigger("submit");
}

myApp.onPageInit('tag.create', function(page) {
			$$('form.ajax-submit').on('beforeSubmit', function(e) {
					});

			$$('form.ajax-submit').on('submitted', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '信息', function() {
									mainView.router.back();
								});
					});

			$$('form.ajax-submit').on('submitError', function(e) {
						myApp.hideIndicator();
						var xhr = e.detail.xhr;
						myApp.alert(xhr.responseText, '错误');
					});
		});

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
