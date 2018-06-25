package com.att.automation.aop;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ControllerAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * @Before("execution(* com.att.automation.controller.admin.*.*(..))")
	 * public void before() { // Advice logger.info(
	 * " Check for user access !!!!!!!!!!!!!!!!!!!!1"); // logger.info(
	 * " Allowed execution for {}", joinPoint); }
	 */
}
