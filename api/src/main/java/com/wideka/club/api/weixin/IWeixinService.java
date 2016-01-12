package com.wideka.club.api.weixin;

import java.util.List;

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

	// >>>>>>>>>>以下是 Department<<<<<<<<<<

	/**
	 * 
	 * @param id
	 * @return
	 */
	List<Department> getDepartmentList(String id);

	// >>>>>>>>>>以下是 User<<<<<<<<<<

	/**
	 * 
	 * @param departmentId
	 * @param fetchChild
	 * @param status
	 * @return
	 */
	List<User> getSimpleUserList(String departmentId, String fetchChild, String status);

	// >>>>>>>>>>以下是 Tag<<<<<<<<<<

	/**
	 * 
	 * @return
	 */
	List<Tag> getTagList();

}
