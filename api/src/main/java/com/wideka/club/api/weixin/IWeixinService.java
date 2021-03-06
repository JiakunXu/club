package com.wideka.club.api.weixin;

import java.util.List;

import com.wideka.club.api.weixin.bo.Ticket;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.weixin.api.agent.bo.Agent;
import com.wideka.weixin.api.department.bo.Department;
import com.wideka.weixin.api.material.bo.MaterialCount;
import com.wideka.weixin.api.material.bo.MaterialList;
import com.wideka.weixin.api.menu.bo.Menu;
import com.wideka.weixin.api.message.bo.File;
import com.wideka.weixin.api.message.bo.Image;
import com.wideka.weixin.api.message.bo.MPNews;
import com.wideka.weixin.api.message.bo.News;
import com.wideka.weixin.api.message.bo.Text;
import com.wideka.weixin.api.message.bo.Video;
import com.wideka.weixin.api.message.bo.Voice;
import com.wideka.weixin.api.tag.bo.Tag;
import com.wideka.weixin.api.tag.bo.TagResult;
import com.wideka.weixin.api.user.bo.User;

/**
 * demo 测试.
 * 
 * @author JiakunXu
 * 
 */
public interface IWeixinService {

	/**
	 * 
	 * @param url
	 * @return
	 */
	Ticket getTicket(String url);

	/**
	 * 
	 * @param url
	 * @return
	 */
	Ticket getTicket4Corp(String url);

	// >>>>>>>>>>以下是Department<<<<<<<<<<

	/**
	 * 
	 * @param department
	 * @return
	 */
	BooleanResult createDepartment(Department department);

	/**
	 * 
	 * @param department
	 * @return
	 */
	BooleanResult updateDepartment(Department department);

	/**
	 * 
	 * @param id
	 * @return
	 */
	BooleanResult deleteDepartment(String id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	List<Department> getDepartmentList(String id);

	// >>>>>>>>>>以下是User<<<<<<<<<<

	/**
	 * 
	 * @param user
	 * @return
	 */
	BooleanResult createUser(User user);

	/**
	 * 
	 * @param user
	 * @return
	 */
	BooleanResult updateUser(User user);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	BooleanResult deleteUser(String userId);

	/**
	 * 
	 * @param departmentId
	 * @param fetchChild
	 * @param status
	 * @return
	 */
	List<User> getSimpleUserList(String departmentId, String fetchChild, String status);

	/**
	 * 
	 * @param departmentId
	 * @param fetchChild
	 * @param status
	 * @return
	 */
	List<User> getUserList(String departmentId, String fetchChild, String status);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	BooleanResult inviteUser(String userId);

	// >>>>>>>>>>以下是Tag<<<<<<<<<<

	/**
	 * 
	 * @param tag
	 * @return
	 */
	BooleanResult createTag(Tag tag);

	/**
	 * 
	 * @param tag
	 * @return
	 */
	BooleanResult updateTag(Tag tag);

	/**
	 * 
	 * @param tagId
	 * @return
	 */
	BooleanResult deleteTag(String tagId);

	/**
	 * 
	 * @param tagId
	 * @return
	 */
	TagResult getTag(String tagId);

	/**
	 * 
	 * @return
	 */
	List<Tag> getTagList();

	// >>>>>>>>>>以下是Media<<<<<<<<<<

	/**
	 * 
	 * @param agentId
	 * @return
	 */
	MaterialCount getCount(String agentId);

	/**
	 * 
	 * @param type
	 * @param agentId
	 * @param offset
	 * @param count
	 * @return
	 */
	MaterialList batchGet(String type, int agentId, int offset, int count);

	// >>>>>>>>>>以下是Agent<<<<<<<<<<

	/**
	 * 
	 * @param agentId
	 * @return
	 */
	Agent getAgent(String agentId);

	/**
	 * 
	 * @return
	 */
	List<Agent> getAgentList();

	// >>>>>>>>>>以下是Message<<<<<<<<<<

	/**
	 * 
	 * @param toUser
	 * @param toParty
	 * @param toTag
	 * @param agentId
	 * @param text
	 * @param safe
	 * @return
	 */
	BooleanResult send(String toUser, String toParty, String toTag, int agentId, Text text, String safe);

	/**
	 * 
	 * @param toUser
	 * @param toParty
	 * @param toTag
	 * @param agentId
	 * @param image
	 * @param safe
	 * @return
	 */
	BooleanResult send(String toUser, String toParty, String toTag, int agentId, Image image, String safe);

	/**
	 * 
	 * @param toUser
	 * @param toParty
	 * @param toTag
	 * @param agentId
	 * @param voice
	 * @param safe
	 * @return
	 */
	BooleanResult send(String toUser, String toParty, String toTag, int agentId, Voice voice, String safe);

	/**
	 * 
	 * @param toUser
	 * @param toParty
	 * @param toTag
	 * @param agentId
	 * @param video
	 * @param safe
	 * @return
	 */
	BooleanResult send(String toUser, String toParty, String toTag, int agentId, Video video, String safe);

	/**
	 * 
	 * @param toUser
	 * @param toParty
	 * @param toTag
	 * @param agentId
	 * @param file
	 * @param safe
	 * @return
	 */
	BooleanResult send(String toUser, String toParty, String toTag, int agentId, File file, String safe);

	/**
	 * 
	 * @param toUser
	 * @param toParty
	 * @param toTag
	 * @param agentId
	 * @param news
	 * @return
	 */
	BooleanResult send(String toUser, String toParty, String toTag, int agentId, News news);

	/**
	 * 
	 * @param toUser
	 * @param toParty
	 * @param toTag
	 * @param agentId
	 * @param mpNews
	 * @param safe
	 * @return
	 */
	BooleanResult send(String toUser, String toParty, String toTag, int agentId, MPNews mpNews, String safe);

	// >>>>>>>>>>以下是Menu<<<<<<<<<<

	/**
	 * 
	 * @return
	 */
	Menu getMenu(String agentId);

	// >>>>>>>>>>以下是Auth<<<<<<<<<<

	/**
	 * 
	 * @param userId
	 * @param agentId
	 * @return
	 */
	BooleanResult convertToOpenId(String userId, Integer agentId);

	/**
	 * 
	 * @param openId
	 * @return
	 */
	BooleanResult convertToUserId(String openId);

}
