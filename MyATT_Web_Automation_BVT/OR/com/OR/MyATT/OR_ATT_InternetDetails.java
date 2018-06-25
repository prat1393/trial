package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class OR_ATT_InternetDetails 
{
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//h1[text()='Internet Details']")
	public WebElement txtInternetDetails;
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[contains(text(),'Compare plans')]")
	public WebElement lnkComparePlans;
	
	@FindBy(xpath="//a[contains(text(),'Change Plan')]")
	public WebElement lnkChangePlan;
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_ATT_InternetDetails(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}

