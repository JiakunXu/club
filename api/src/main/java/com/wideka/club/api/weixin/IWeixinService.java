package com.wideka.club.api.weixin;

import java.util.List;

import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.weixin.api.agent.bo.Agent;
import com.wideka.weixin.api.department.bo.Department;
import com.wideka.weixin.api.material.bo.MaterialList;
import com.wideka.weixin.api.menu.bo.Menu;
import com.wideka.weixin.api.message.bo.Image;
import com.wideka.weixin.api.message.bo.Text;
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
