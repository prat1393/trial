package com.att.automation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.WebApplicationInitializer;

@Configuration
@EnableJpaRepositories(basePackages = "com.att.user.dao")
@EntityScan("com.att.user.dao")

@SpringBootApplication(scanBasePackages = "com.att")
public class E2eAutomationWebApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	private final static Logger logger = LoggerFactory.getLogger(E2eAutomationWebApplication.class);

	public static void main(String[] args) {
		logger.info("This is an info message in main class");
		SpringApplication.run(E2eAutomationWebApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(E2eAutomationWebApplication.class);
	}
}
