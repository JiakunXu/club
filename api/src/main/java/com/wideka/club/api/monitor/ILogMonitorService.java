package com.wideka.club.api.monitor;

import java.util.List;

import com.wideka.club.api.monitor.bo.LogMonitor;

/**
 * logMonitor.
 * 
 * @author xujiakun
 * 
 */
public interface ILogMonitorService {

	String ERROR_MESSAGE = "操作失败！";

	/**
	 * 
	 * @param logMonitor
	 * @return
	 */
	int getLogMonitorCount(LogMonitor logMonitor);

	/**
	 * 
	 * @param logMonitor
	 * @return
	 */
	List<LogMonitor> getLogMonitorList(LogMonitor logMonitor);

	/**
	 * createLogMonitor.
	 * 
	 * @param logMonitorList
	 * @return
	 */
	boolean createLogMonitor(List<LogMonitor> logMonitorList);

	/**
	 * getLogMonitorList4SendEmail.
	 * 
	 * @return
	 */
	List<LogMonitor> getLogMonitorList4SendEmail();

}
