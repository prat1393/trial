package com.OR.MyATTZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_Logon {
	WebDriver driver;

	@FindBy(xpath="//*[@type='button']") 
	public WebElement ok;

	public OR_Logon(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
