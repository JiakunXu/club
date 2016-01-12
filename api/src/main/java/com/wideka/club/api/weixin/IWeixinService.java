package com.wideka.club.api.weixin;

import java.util.List;

import com.wideka.weixin.api.department.bo.Department;
import com.wideka.weixin.api.tag.bo.Tag;

/**
 * demo 测试.
 * 
 * @author JiakunXu
 * 
 */
public interface IWeixinService {

	// >>>>>>>>>>以下是<<<<<<<<<<

	/**
	 * 
	 * @param id
	 * @return
	 */
	List<Department> getDepartmentList(String id);

	// >>>>>>>>>>以下是<<<<<<<<<<

	// >>>>>>>>>>以下是<<<<<<<<<<

	/**
	 * 
	 * @return
	 */
	List<Tag> getTagList();

}
