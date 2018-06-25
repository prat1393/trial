package com.att.automation.utility;

import org.springframework.stereotype.Component;

@Component
public class AutomationUtilityHelper {
	public boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

}
