package com.wideka.club.weixin.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.api.weixin.bo.Ticket;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.weixin.api.agent.bo.Agent;
import com.wideka.weixin.api.message.bo.File;
import com.wideka.weixin.api.message.bo.Image;
import com.wideka.weixin.api.message.bo.Text;
import com.wideka.weixin.api.message.bo.Video;
import com.wideka.weixin.api.message.bo.Voice;

/**
 * 
 * @author JiakunXu
 * 
 */
public class MessageAction extends BaseAction {

	private static final long serialVersionUID = -783512905307414411L;

	private IWeixinService weixinService;

	/**
	 * 操作.
	 */
	private String op;

	private List<Agent> agentList;

	private Ticket ticket;

	private String toUser;

	private String toParty;

	private String toTag;

	private String agentId;

	private String safe;

	private Text text;

	private Image image;

	private Voice voice;

	private Video video;

	private File file;

	public String message() {
		if ("send/text".equals(op)) {
			BooleanResult result = weixinService.send(toUser, toParty, toTag, Integer.parseInt(agentId), text, safe);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		if ("send/image".equals(op)) {
			BooleanResult result = weixinService.send(toUser, toParty, toTag, Integer.parseInt(agentId), image, safe);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		if ("send/voice".equals(op)) {
			BooleanResult result = weixinService.send(toUser, toParty, toTag, Integer.parseInt(agentId), voice, safe);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		if ("send/video".equals(op)) {
			BooleanResult result = weixinService.send(toUser, toParty, toTag, Integer.parseInt(agentId), video, safe);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		if ("send/file".equals(op)) {
			BooleanResult result = weixinService.send(toUser, toParty, toTag, Integer.parseInt(agentId), file, safe);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		if (StringUtils.isNotBlank(op)) {
			agentList = weixinService.getAgentList();

			ticket = weixinService.getTicket(env.getProperty("appUrl") + "/weixin/message.htm?op=" + op.trim());

			return op.trim();
		}

		return SUCCESS;
	}

	public IWeixinService getWeixinService() {
		return weixinService;
	}

	public void setWeixinService(IWeixinService weixinService) {
		this.weixinService = weixinService;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public List<Agent> getAgentList() {
		return agentList;
	}

	public void setAgentList(List<Agent> agentList) {
		this.agentList = agentList;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getToParty() {
		return toParty;
	}

	public void setToParty(String toParty) {
		this.toParty = toParty;
	}

	public String getToTag() {
		return toTag;
	}

	public void setToTag(String toTag) {
		this.toTag = toTag;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getSafe() {
		return safe;
	}

	public void setSafe(String safe) {
		this.safe = safe;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
