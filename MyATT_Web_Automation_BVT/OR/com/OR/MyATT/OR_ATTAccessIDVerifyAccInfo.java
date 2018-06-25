package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_ATTAccessIDVerifyAccInfo {
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
		
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	
	
		
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	@FindBy(id="memberId")
	public WebElement edtMemberID;
	
	@FindBy(id="password")
	public WebElement edtPassword;
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//h1[contains(text(),'Verify Account Information')]")
	public WebElement txtVerifyAccInfoHeading;
	
		
		
		

	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(id="enbSubmitBtn")
	public WebElement btnNext;
		
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
		
		
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_ATTAccessIDVerifyAccInfo(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
