package com.wideka.club.framework.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * base bo.
 * 
 * @author xujiakun
 * 
 */
public class BaseBo implements Serializable {

	private static final long serialVersionUID = -3829312471141500962L;

	private Date gmtCreated;

	private Date gmtModified;

	private String creator;

	private String modifier;

	public Date getGmtCreated() {
		return gmtCreated != null ? (Date) gmtCreated.clone() : null;
	}

	public void setGmtCreated(Date gmtCreated) {
		if (gmtCreated != null) {
			this.gmtCreated = (Date) gmtCreated.clone();
		}
	}

	public Date getGmtModified() {
		return gmtModified != null ? (Date) gmtModified.clone() : null;
	}

	public void setGmtModified(Date gmtModified) {
		if (gmtModified != null) {
			this.gmtModified = (Date) gmtModified.clone();
		}
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

}
