package com.wideka.club.framework.action;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wideka.club.framework.annotation.Decode;
import com.wideka.club.framework.bo.SearchInfo;

/**
 * BaseAction.
 * 
 * @author xujiakun
 * 
 */
public class BaseAction extends ActionSupport {

	public static final String LOGIN = "login";

	public static final String LOGOUT = "logout";

	public static final String CREATE = "create";
	public static final String UPDATE = "update";
	public static final String CREATE_PREPARE = "createPrepare";
	public static final String UPDATE_PREPARE = "updatePrepare";
	public static final String DETAIL = "detail";
	public static final String DELETE = "delete";
	public static final String LIST = "list";
	public static final String JSON_RESULT = "jsonresult";
	public static final String RESOURCE_RESULT = "resourceResult";
	public static final String RESULT_MESSAGE = "resultMessage";

	private static final long serialVersionUID = 7674615559114195895L;

	protected String actionName;

	protected String gmtStart;

	protected String gmtEnd;

	protected Map<String, Boolean> permission;

	protected Properties env = new Properties();

	protected String token;

	@Decode
	private String name;

	private String code;

	@Decode
	private String search;

	private String resourceResult;

	private String failMessage;

	private String successMessage;

	private int limit;

	private int offset;

	private String sort;

	private String order;

	private int start;

	protected HttpSession getSession() {
		return getServletRequest().getSession();
	}

	/**
	 * 取得HttpServletRequest对象.
	 * 
	 * @return HttpServletRequest对象.
	 */
	protected HttpServletRequest getServletRequest() {
		ActionContext ctx = ActionContext.getContext();
		return (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
	}

	/**
	 * 取得HttpServletResponse对象.
	 * 
	 * @return HttpServletResponse对象.
	 */
	protected HttpServletResponse getServletResponse() {
		ActionContext ctx = ActionContext.getContext();
		return (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
	}

	public <T extends SearchInfo> T getSearchInfo(T info) {
		info.setSort(sort);
		info.setOrder(order);
		info.setLimit(limit);
		info.setOffset(offset);
		info.setName(name);
		info.setCode(code);
		info.setGmtStart(gmtStart);
		info.setGmtEnd(gmtEnd);
		info.setSearch(search);
		return info;
	}

	public SearchInfo getSearchInfo() {
		return this.getSearchInfo(new SearchInfo());
	}

	/**
	 * getOpenId.
	 * 
	 * @return
	 */
	public String getOpenId() {
		return (String) getSession().getAttribute("ACEGI_SECURITY_LAST_OPEN_ID");
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
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getResourceResult() {
		return resourceResult;
	}

	public void setResourceResult(String resourceResult) {
		this.resourceResult = resourceResult;
	}

	public String getFailMessage() {
		return failMessage;
	}

	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

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

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Boolean> getPermission() {
		return (Map<String, Boolean>) getSession().getAttribute("PERMISSION");
	}

	public void setPermission(Map<String, Boolean> permission) {
		this.permission = permission;
	}

	public Properties getEnv() {
		return env;
	}

	public void setEnv(Properties env) {
		this.env = env;
	}

	public String getEnv(String key) {
		return env.getProperty(key);
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
