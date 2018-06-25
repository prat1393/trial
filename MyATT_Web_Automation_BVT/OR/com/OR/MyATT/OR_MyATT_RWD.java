package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_MyATT_RWD 
{
	/*---------------------------
	 * Card objects add below
	 *---------------------------*/
	 @FindBy(xpath="//h2[contains(text(),'My U-verse TV')]")
	 public WebElement crdMyUverseTV;
	 
	 @FindBy(xpath="//h2[contains(text(),'My Internet')]")
	 public WebElement crdMyInternet;
	
	WebDriver driver;

	public OR_MyATT_RWD(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
