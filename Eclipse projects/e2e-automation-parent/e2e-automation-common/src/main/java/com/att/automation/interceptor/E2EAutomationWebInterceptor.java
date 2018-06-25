package com.att.automation.interceptor;

import static com.att.dbvalidation.properties.DataValidationEnum.REDIRECT_URL_DEV;
import static com.att.dbvalidation.properties.DataValidationEnum.REDIRECT_URL_PROD;
import static com.att.dbvalidation.properties.DataValidationEnum.VERSION;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.att.automation.utility.AutomationUtilityHelper;

/**
 * @author pd056d This interceptor will restrict the access of Url's without
 *         Logging in
 */
@Component
public class E2EAutomationWebInterceptor extends HandlerInterceptorAdapter {

	@Inject
	private AutomationUtilityHelper utilityHelper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// System.out.println("Inside interceptor request url " +
		// request.getRequestURI());
		HttpSession session = request.getSession(false);
		String redirectUrl;
		if (request.getRequestURI().endsWith("/e2e/login")) {
			return true;
		}
		if (null == session || null == session.getAttribute("cookie")) {

			if (utilityHelper.isWindows()) {

				redirectUrl = REDIRECT_URL_DEV.getValue() + "/e2e/login";
			} else

			{
				redirectUrl = REDIRECT_URL_PROD.getValue() + "/" + VERSION.getValue() + "/e2e/login";
			}

			response.sendRedirect(redirectUrl);
			return false;

		}
		return true;

	}

}
