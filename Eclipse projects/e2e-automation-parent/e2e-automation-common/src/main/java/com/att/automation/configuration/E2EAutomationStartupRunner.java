package com.att.automation.configuration;

import javax.inject.Inject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.att.automation.utility.AutomationUtilityHelper;
import com.att.user.dao.UserRepository;
import com.att.user.dao.model.User;

/**
 * @author pd056d This class is used to insert dummy Data during
 *         development,this class will not insert data in production
 *
 */
@Component
public class E2EAutomationStartupRunner implements CommandLineRunner {

	private UserRepository userRepository;
	private AutomationUtilityHelper automationUtilityHelper;

	@Inject
	public E2EAutomationStartupRunner(UserRepository userRepository, AutomationUtilityHelper automationUtilityHelper) {
		super();
		this.userRepository = userRepository;
		this.automationUtilityHelper = automationUtilityHelper;
	}

	@Override
	public void run(String... arg0) throws Exception {
		if (automationUtilityHelper.isWindows()) {

			User user = new User("pd056d", "Partho", "Dey", "ADMIN", "30-Apr-2018");
			User user1 = new User("rt580s", "Rohit", "Tripathi", "ADMIN", "30-Apr-2018");
			User user2 = new User("rr998s", "Raghavendra", "Rao", "DATA ANALYST", "30-Apr-2018");

			userRepository.save(user);
			userRepository.save(user1);
			userRepository.save(user2);

		}

	}

}
