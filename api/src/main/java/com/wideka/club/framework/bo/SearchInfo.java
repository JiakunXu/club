package com.wideka.club.framework.bo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang.StringUtils;

import com.wideka.club.framework.annotation.Decode;

/**
 * SearchInfo.
 * 
 * @author xujiakun
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
public class SearchInfo implements Serializable {

	public static final String DIR_DESC = "DESC";

	public static final String DIR_ASC = "ASC";

	private static final long serialVersionUID = 235499309845516885L;

	private static final int LIMIT = 10;

	private String gmtStart;

	private String gmtEnd;

	private String name;

	private String code;

	private String[] codes;

	@Decode
	private String search;

	private int limit = LIMIT;

	private int offset;

	private String sort;

	private String order;

	private String filter;

	private long totalRows;

	@SuppressWarnings("rawtypes")
	private List resultList;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	@SuppressWarnings("rawtypes")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getGmtStart() {
		return gmtStart;
	}

	public void setGmtStart(String gmtStart) {
		this.gmtStart = gmtStart;
	}

	public String getGmtEnd() {
		return gmtEnd;
	}

	public void setGmtEnd(String gmtEnd) {
		this.gmtEnd = gmtEnd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtils.deleteWhitespace(name);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = StringUtils.deleteWhitespace(code);
	}

	public String[] getCodes() {
		return codes != null ? Arrays.copyOf(codes, codes.length) : null;
	}

	public void setCodes(String[] codes) {
		if (codes != null) {
			this.codes = Arrays.copyOf(codes, codes.length);
		}
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = StringUtils.deleteWhitespace(search);
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}
