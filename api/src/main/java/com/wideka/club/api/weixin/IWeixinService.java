package com.wideka.club.api.weixin;

import java.util.List;

import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.weixin.api.agent.bo.Agent;
import com.wideka.weixin.api.department.bo.Department;
import com.wideka.weixin.api.tag.bo.Tag;
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
	 * @param id
	 * @return
	 */
	List<Department> getDepartmentList(String id);

	// >>>>>>>>>>以下是User<<<<<<<<<<

	/**
	 * 
	 * @param departmentId
	 * @param fetchChild
	 * @param status
	 * @return
	 */
	List<User> getSimpleUserList(String departmentId, String fetchChild, String status);

	// >>>>>>>>>>以下是Tag<<<<<<<<<<

	/**
	 * 
	 * @return
	 */
	List<Tag> getTagList();

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
	 * @param content
	 * @param safe
	 * @return
	 */
	BooleanResult send(String toUser, String toParty, String toTag, String agentId, String content, String safe);

}
