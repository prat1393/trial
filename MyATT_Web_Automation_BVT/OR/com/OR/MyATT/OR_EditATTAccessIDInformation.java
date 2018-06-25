package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_EditATTAccessIDInformation {
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	

	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	

	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/


	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//select[@id='onQ1' or @title='Select Question 1']")
	public WebElement lstSelectSecurityQues1;
	
	@FindBy(xpath="//select[@id='onQ2' or @title='Select Question 2']")
	public WebElement lstSelectSecurityQues2;

	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	@FindBy(xpath="//input[@id='currPassword' or @id='current_password']")
	public WebElement edtCurrentPassword;
	
	@FindBy(xpath="//input[@id='newPassword' or @id='new_password']")
	public WebElement edtNewPassword;
	
	@FindBy(xpath="//input[@id='confirmPassword' or @id='confirm_password']")
	public WebElement edtConfirmNewPassword;
	
	@FindBy(xpath="//input[@id='in1' or @id='answer1']")
	public WebElement edtSecurityAns1;
	
	@FindBy(xpath="//input[@id='in2' or @id='answer2']")
	public WebElement edtSecurityAns2;


	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//p[text()='Change Password']")
	public WebElement txtChangePassword;
	
	@FindBy(xpath="//h1[contains(text(),'Edit AT&T Access ID Information')]")
	public WebElement txtATTAccessIDInfo;
	
	@FindBy(xpath="//p[contains(text(),'Security Questions and Answers')]")
	public WebElement txtSecurityQuesAndAns;
	
	@FindBy(xpath="//h1[contains(text(),'Confirmation')]")
	public WebElement txtConfirmationPage;
	
	/*---------------------------	
	 * Link objects add below
	 *---------------------------*/

	@FindBy(xpath="//a[@title='Return to smart Limits']")
	public WebElement lnkReturnToSmartLimits;

	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//img[@src='/olam/English/images/bt/bt_saveChanges.gif']")
	public WebElement btnSaveChanges;
	
	
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	public OR_EditATTAccessIDInformation(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
