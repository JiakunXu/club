package com.wideka.club.api.weixin;

import java.util.List;

import com.wideka.weixin.api.department.bo.Department;

/**
 * demo 测试.
 * 
 * @author JiakunXu
 * 
 */
public interface IWeixinService {

	List<Department> getDepartmentList(String id);

}
