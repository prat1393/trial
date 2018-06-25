package com.att.automation.configuration;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

import com.att.automation.utility.AutomationUtilityHelper;

/**
 * @author pd056d This class will be used to set "prod" profile to use
 *         Production Database.During development H2 Database will be used
 */
public class E2EAutomationWebApplicationInitializer implements WebApplicationInitializer {

	private AutomationUtilityHelper automationUtilityHelper;

	@Inject
	public E2EAutomationWebApplicationInitializer(AutomationUtilityHelper automationUtilityHelper) {
		super();
		this.automationUtilityHelper = automationUtilityHelper;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		if (!automationUtilityHelper.isWindows()) {
			servletContext.setInitParameter("spring.profiles.active", "Prod");
		}

	}

}
