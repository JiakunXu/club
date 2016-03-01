package com.wideka.club.auth.action;

import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.wideka.club.api.auth.IAuthService;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;

/**
 * 
 * @author JiakunXu
 * 
 */
public class OAuth2Action extends BaseAction {

	private static final long serialVersionUID = 6386474612475679175L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(OAuth2Action.class);

	private IAuthService authService;

	private String redirectUrl;

	public String authorize() {
		BooleanResult result = null;

		try {
			result =
				authService.authorize(URLEncoder.encode(env.getProperty("appUrl") + "/auth/redirect.htm", "UTF-8"));
		} catch (Exception e) {
			logger.error(e);
			return ERROR;
		}

		if (result.getResult()) {
			redirectUrl = result.getCode();

			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String redirect() {
		// 用户唯一标识
		String openId = authService.getOpenId(this.getCode());

		if (StringUtils.isEmpty(openId)) {
			return ERROR;
		}

		HttpSession session = this.getSession();
		session.setAttribute("ACEGI_SECURITY_LAST_OPEN_ID", openId);

		return SUCCESS;
	}

	public IAuthService getAuthService() {
		return authService;
	}

	public void setAuthService(IAuthService authService) {
		this.authService = authService;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}
