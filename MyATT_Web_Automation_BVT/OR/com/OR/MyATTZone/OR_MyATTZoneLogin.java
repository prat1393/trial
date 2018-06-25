package com.OR.MyATTZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_MyATTZoneLogin {
	WebDriver driver;
	
	@FindBy(xpath="//*[@name='userid']") 
	public WebElement attuid;
	
	@FindBy(xpath="//*[@name='password']") 
	public WebElement password;
	
	@FindBy(xpath="//*[@type='submit']") 
	public WebElement ok;
		
	public OR_MyATTZoneLogin(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
