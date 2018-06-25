package com.att.automation.configuration;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author pd056d This class will register the Interceptor used to restrict the
 *         access of URL's
 */
@Configuration
public class E2EAutomationWebConfig extends WebMvcConfigurerAdapter {

	HandlerInterceptorAdapter automationInterceptorAdaptor;

	@Inject
	public E2EAutomationWebConfig(HandlerInterceptorAdapter automationInterceptorAdaptor) {
		super();
		this.automationInterceptorAdaptor = automationInterceptorAdaptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(automationInterceptorAdaptor);
	}

}
