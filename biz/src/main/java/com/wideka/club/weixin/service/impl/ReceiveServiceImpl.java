package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IEventBatchJobService;
import com.wideka.club.api.weixin.IEventClickService;
import com.wideka.club.api.weixin.IEventEnterAgentService;
import com.wideka.club.api.weixin.IEventLocationSelectService;
import com.wideka.club.api.weixin.IEventLocationService;
import com.wideka.club.api.weixin.IEventPicService;
import com.wideka.club.api.weixin.IEventScanCodeService;
import com.wideka.club.api.weixin.IEventSubscribeService;
import com.wideka.club.api.weixin.IEventViewService;
import com.wideka.club.api.weixin.IMsgImageService;
import com.wideka.club.api.weixin.IMsgLocationService;
import com.wideka.club.api.weixin.IMsgTextService;
import com.wideka.club.api.weixin.IMsgVideoService;
import com.wideka.club.api.weixin.IMsgVoiceService;
import com.wideka.club.api.weixin.IReceiveService;
import com.wideka.club.api.weixin.ITokenService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.weixin.api.callback.ICallbackService;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class ReceiveServiceImpl implements IReceiveService {

	private ICallbackService callbackService;

	private IMsgTextService msgTextService;

	private IMsgImageService msgImageService;

	private IMsgVoiceService msgVoiceService;

	private IMsgVideoService msgVideoService;

	private IMsgLocationService msgLocationService;

	private IEventSubscribeService eventSubscribeService;

	private IEventLocationService eventLocationService;

	private IEventClickService eventClickService;

	private IEventViewService eventViewService;

	private IEventScanCodeService eventScanCodeService;

	private IEventPicService eventPicService;

	private IEventLocationSelectService eventLocationSelectService;

	private IEventEnterAgentService eventEnterAgentService;

	private IEventBatchJobService eventBatchJobService;

	private ITokenService tokenService;

	private String token;

	private String encodingAesKey;

	private String corpId;

	private String corpSecret;

	@Override
	public BooleanResult verify(String signature, String timestamp, String nonce, String echostr) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			result.setCode(callbackService.verify(token, encodingAesKey, corpId, signature, timestamp, nonce, echostr));
			result.setResult(true);
		} catch (RuntimeException e) {
			result.setCode(e.getMessage());
		}

		return result;
	}

	@Override
	public BooleanResult callback(String signature, String timestamp, String nonce, String data) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			Content content =
				callbackService.callback(token, encodingAesKey, corpId, signature, timestamp, nonce, data);

			System.out.println("==============================");
			System.out.println(LogUtil.parserBean(content));
			System.out.println("==============================");

			String msgType = content.getMsgType();

			if ("text".equals(msgType)) {
				result = msgTextService.createMsgText(content);
			} else if ("image".equals(msgType)) {
				result = msgImageService.createMsgImage(content);
			} else if ("voice".equals(msgType)) {
				result = msgVoiceService.createMsgVoice(content);
			} else if ("video".equals(msgType)) {
				result = msgVideoService.createMsgVideo(content);
			} else if ("shortvideo".equals(msgType)) {
				result = msgVideoService.createMsgVideo(content);
			} else if ("location".equals(msgType)) {
				result = msgLocationService.createMsgLocation(content);
			} else if ("event".equals(msgType)) {

				String event = content.getEvent();

				if ("subscribe".equals(event)) {
					result = eventSubscribeService.createEventSubscribe(content);
				} else if ("LOCATION".equals(event)) {
					result = eventLocationService.createEventLocation(content);
				} else if ("CLICK".equals(event)) {
					result = eventClickService.createEventClick(content);
				} else if ("VIEW".equals(event)) {
					result = eventViewService.createEventView(content);
				} else if ("scancode_push".equals(event)) {
					result = eventScanCodeService.createEventScanCode(content);
				} else if ("scancode_waitmsg".equals(event)) {
					result = eventScanCodeService.createEventScanCode(content);
				} else if ("pic_sysphoto".equals(event)) {
					result = eventPicService.createEventPic(content);
				} else if ("pic_photo_or_album".equals(event)) {
					result = eventPicService.createEventPic(content);
				} else if ("pic_weixin".equals(event)) {
					result = eventPicService.createEventPic(content);
				} else if ("location_select".equals(event)) {
					result = eventLocationSelectService.createEventLocationSelect(content);
				} else if ("enter_agent".equals(event)) {
					result = eventEnterAgentService.createEventEnterAgent(content);
				} else if ("batch_job_result".equals(event)) {
					result = eventBatchJobService.createEventBatchJob(content);
				}
			}
		} catch (RuntimeException e) {
			result.setCode(e.getMessage());
		}

		return result;
	}

	@Override
	public void getCallbackIP() {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (result.getResult()) {
			try {
				System.out.println(LogUtil.parserBean(callbackService.getCallbackIP(result.getCode())));
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

	public ICallbackService getCallbackService() {
		return callbackService;
	}

	public void setCallbackService(ICallbackService callbackService) {
		this.callbackService = callbackService;
	}

	public IMsgTextService getMsgTextService() {
		return msgTextService;
	}

	public void setMsgTextService(IMsgTextService msgTextService) {
		this.msgTextService = msgTextService;
	}

	public IMsgImageService getMsgImageService() {
		return msgImageService;
	}

	public void setMsgImageService(IMsgImageService msgImageService) {
		this.msgImageService = msgImageService;
	}

	public IMsgVoiceService getMsgVoiceService() {
		return msgVoiceService;
	}

	public void setMsgVoiceService(IMsgVoiceService msgVoiceService) {
		this.msgVoiceService = msgVoiceService;
	}

	public IMsgVideoService getMsgVideoService() {
		return msgVideoService;
	}

	public void setMsgVideoService(IMsgVideoService msgVideoService) {
		this.msgVideoService = msgVideoService;
	}

	public IMsgLocationService getMsgLocationService() {
		return msgLocationService;
	}

	public void setMsgLocationService(IMsgLocationService msgLocationService) {
		this.msgLocationService = msgLocationService;
	}

	public IEventSubscribeService getEventSubscribeService() {
		return eventSubscribeService;
	}

	public void setEventSubscribeService(IEventSubscribeService eventSubscribeService) {
		this.eventSubscribeService = eventSubscribeService;
	}

	public IEventLocationService getEventLocationService() {
		return eventLocationService;
	}

	public void setEventLocationService(IEventLocationService eventLocationService) {
		this.eventLocationService = eventLocationService;
	}

	public IEventClickService getEventClickService() {
		return eventClickService;
	}

	public void setEventClickService(IEventClickService eventClickService) {
		this.eventClickService = eventClickService;
	}

	public IEventViewService getEventViewService() {
		return eventViewService;
	}

	public void setEventViewService(IEventViewService eventViewService) {
		this.eventViewService = eventViewService;
	}

	public IEventScanCodeService getEventScanCodeService() {
		return eventScanCodeService;
	}

	public void setEventScanCodeService(IEventScanCodeService eventScanCodeService) {
		this.eventScanCodeService = eventScanCodeService;
	}

	public IEventPicService getEventPicService() {
		return eventPicService;
	}

	public void setEventPicService(IEventPicService eventPicService) {
		this.eventPicService = eventPicService;
	}

	public IEventLocationSelectService getEventLocationSelectService() {
		return eventLocationSelectService;
	}

	public void setEventLocationSelectService(IEventLocationSelectService eventLocationSelectService) {
		this.eventLocationSelectService = eventLocationSelectService;
	}

	public IEventEnterAgentService getEventEnterAgentService() {
		return eventEnterAgentService;
	}

	public void setEventEnterAgentService(IEventEnterAgentService eventEnterAgentService) {
		this.eventEnterAgentService = eventEnterAgentService;
	}

	public IEventBatchJobService getEventBatchJobService() {
		return eventBatchJobService;
	}

	public void setEventBatchJobService(IEventBatchJobService eventBatchJobService) {
		this.eventBatchJobService = eventBatchJobService;
	}

	public ITokenService getTokenService() {
		return tokenService;
	}

	public void setTokenService(ITokenService tokenService) {
		this.tokenService = tokenService;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEncodingAesKey() {
		return encodingAesKey;
	}

	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getCorpSecret() {
		return corpSecret;
	}

	public void setCorpSecret(String corpSecret) {
		this.corpSecret = corpSecret;
	}

}
