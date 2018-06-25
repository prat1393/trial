package com.att.automation.controller.login;

import static com.att.dbvalidation.properties.DataValidationEnum.ATTID;
import static com.att.dbvalidation.properties.DataValidationEnum.FIRST_NAME;
import static com.att.dbvalidation.properties.DataValidationEnum.LAST_NAME;
import static com.att.dbvalidation.properties.DataValidationEnum.REDIRECT_URL_PROD;
import static com.att.dbvalidation.properties.DataValidationEnum.VERSION;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.att.automation.utility.AutomationUtilityHelper;
import com.att.login.model.CSPAuthData;
import com.att.login.services.CSPAuthenticator;
import com.att.login.services.UserAccessService;
import com.att.user.dao.model.User;

@Controller
@RequestMapping(value = "/e2e")
public class E2eAutomationLoginController {
	/**
	 * 
	 */

	private UserAccessService userService;
	private AutomationUtilityHelper utilityHelper;

	@Inject
	public E2eAutomationLoginController(UserAccessService userService, AutomationUtilityHelper utilityHelper) {
		super();
		this.userService = userService;
		this.utilityHelper = utilityHelper;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void welcome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute("cookie", Math.random());

		String redirectUrl;
		if (utilityHelper.isWindows()) {
			System.out.println("Inside windows ");
			/* redirectUrl = REDIRECT_URL_DEV.getValue() + "/e2e/index"; */
			redirectUrl = "/e2e/index";
		} else

		{
			redirectUrl = "https://www.e-access.att.com/empsvcs/hrpinmgt/pagLogin/?retURL="
					+ REDIRECT_URL_PROD.getValue() + "/" + VERSION.getValue() + "/e2e/index";
		}

		response.sendRedirect(redirectUrl);

	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside index");

		CSPAuthData cspAuthData = null;
		String attid;
		String forwardPage = "error";
		HttpSession session = request.getSession();
		try {
			if (!utilityHelper.isWindows()) {
				cspAuthData = new CSPAuthenticator().validate(request);
				attid = cspAuthData.getAttuid();
			} else {
				cspAuthData = new CSPAuthData();
				cspAuthData.setHrFirstName(FIRST_NAME.getValue());
				cspAuthData.setHrLastName(LAST_NAME.getValue());
				attid = ATTID.getValue();
			}

			User user = userService.getUserByAttId(attid);
			if (null != user)

			{
				String accessPrivilege = user.getUserAccess();
				// logger.error(" accessPrivilege " + accessPrivilege);
				cspAuthData.setAccessPrivilege(accessPrivilege);

				forwardPage = "blank";
			} else {

				forwardPage = "accessdenied";
			}
			// Forward to to the JSP file.
			// request.getRequestDispatcher("blank.jsp").forward(request,
			// response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("cspAuthData", cspAuthData);

		return forwardPage;
	}

}
