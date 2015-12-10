package com.wideka.club.framework.bo;

import java.io.Serializable;

/**
 * 
 * @author
 * 
 */
public class ThreadPool implements Serializable {

	private static final long serialVersionUID = 4776405616552648893L;
	private int corePoolSize;
	private int maximumPoolSize;
	private int poolSize;
	private int largestPoolSize;
	private long completedTaskCount;
	private int activeCount;
	private long taskCount;
	private int queueCount;

	public int getQueueCount() {
		return queueCount;
	}

	public void setQueueCount(int queueCount) {
		this.queueCount = queueCount;
	}

	public int getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public int getLargestPoolSize() {
		return largestPoolSize;
	}

	public void setLargestPoolSize(int largestPoolSize) {
		this.largestPoolSize = largestPoolSize;
	}

	public long getCompletedTaskCount() {
		return completedTaskCount;
	}

	public void setCompletedTaskCount(long completedTaskCount) {
		this.completedTaskCount = completedTaskCount;
	}

	public int getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(int activeCount) {
		this.activeCount = activeCount;
	}

	public long getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(long taskCount) {
		this.taskCount = taskCount;
	}
}
