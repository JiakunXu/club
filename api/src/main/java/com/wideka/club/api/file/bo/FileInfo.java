package com.wideka.club.api.file.bo;

import com.wideka.club.framework.bo.SearchInfo;

/**
 * 文件信息.
 * 
 * @author JiakunXu
 * 
 */
public class FileInfo extends SearchInfo {

	private static final long serialVersionUID = 6240425668306639391L;

	private Long fileId;

	private Long shopId;

	private String fileName;

	/**
	 * dfs or hdfs.
	 */
	private String fileType;

	/**
	 * txt jpg ...
	 */
	private String suffix;

	/**
	 * hdfs path.
	 */
	private String filePath;

	/**
	 * U or D.
	 */
	private String state;

	private String modifyUser;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

}
