package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_RequestAccessConfirmation {
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
		
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	@FindBy(xpath="//div[@id='uniform-gotoAccess']")
	public WebElement radGoToMyATT;
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	
	
		
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//h1[contains(text(),'Confirmation')]")
	public WebElement txtConfirmationHeading;
		
	@FindBy(xpath="//p[contains(text(),'You will receive an email')]")
	public WebElement txtMsg1;
	
	@FindBy(xpath="//p[contains(text(),'request for access to the account associated')]")
	public WebElement txtMsg2;
		
		

	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
		
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[@id='enableButton']/a/img[@alt='Continue']")
	public WebElement btnContinue;
		
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_RequestAccessConfirmation(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
