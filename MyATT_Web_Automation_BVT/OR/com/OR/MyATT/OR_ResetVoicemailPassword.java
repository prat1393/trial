package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class OR_ResetVoicemailPassword {
	
	
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/

	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/

	
	/*--------------------------
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
	@FindBy(xpath="//*[contains(text(),'can not perform this task')]")
	public WebElement elmNote;
		
	@FindBy(xpath="//p[contains(text(),'Need more help')]")
	public WebElement elmNeedSomeHelp;
	
	@FindBy(xpath="//div[@id='content-container']//h1[contains(text(),'Reset Voicemail Password')]")
	public WebElement txtResetVoicemailPasswordPg;
	
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * button objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[@src='/olam/English/images/bt/bt_submit.gif']")
	public WebElement btnSubmit;
	
	/*---------------------------
	 * frame objects add below
	 *---------------------------*/

	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	
	WebDriver driver;
	
	
	
	
	public OR_ResetVoicemailPassword(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}


