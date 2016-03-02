package com.wideka.club.api.item.bo;

import com.wideka.club.api.file.bo.FileInfo;

/**
 * 商品文件.
 * 
 * @author JiakunXu
 * 
 */
public class ItemFile extends FileInfo {

	private static final long serialVersionUID = -2252136499749108075L;

	private Long id;

	/**
	 * 商品id.
	 */
	private Long itemId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

}
