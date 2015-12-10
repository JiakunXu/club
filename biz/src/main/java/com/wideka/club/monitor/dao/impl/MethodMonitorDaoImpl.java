package com.wideka.club.monitor.dao.impl;

import java.util.List;

import com.wideka.club.api.monitor.bo.MethodMonitor;
import com.wideka.club.framework.dao.impl.BaseDaoImpl;
import com.wideka.club.monitor.dao.IMethodMonitorDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class MethodMonitorDaoImpl extends BaseDaoImpl implements IMethodMonitorDao {

	@Override
	public int getMethodMonitorCount(MethodMonitor methodMonitor) {
		return (Integer) getSqlMapClientTemplate()
			.queryForObject("monitor.method.getMethodMonitorCount", methodMonitor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MethodMonitor> getMethodMonitorList(MethodMonitor methodMonitor) {
		return (List<MethodMonitor>) getSqlMapClientTemplate().queryForList("monitor.method.getMethodMonitorList",
			methodMonitor);
	}

	@Override
	public String createMethodMonitor(MethodMonitor methodMonitor) {
		return (String) getSqlMapClientTemplate().insert("monitor.method.createMethodMonitor", methodMonitor);
	}

}
