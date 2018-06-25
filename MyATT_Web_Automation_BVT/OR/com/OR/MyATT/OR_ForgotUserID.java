package com.OR.MyATT;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OR_ForgotUserID {
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	
	@FindBy(id="customerEmailAddress") 
	public WebElement edtContactemail;
	
	@FindBy(xpath="//input[@id='userId']") 
	public WebElement edtUserID;
	
	@FindBy(xpath="//input[@id='LastName']") 
	public WebElement edtLastName;
	
	/*---------------------------
	 * Frames add below
	 *---------------------------*/
	
	@FindBy(xpath="//div[@id='cboxContent']")
	public WebElement frmMultipleIDs;
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[@id='cancelId']")
	public WebElement lnkCancel;
	
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[@class='closeModal']")
	public WebElement imgClose;
	
	
	
	@FindBy(xpath="//div/button[contains(text(),'Continue')]")
	public WebElement imgContinue;
	
	@FindBy(xpath="//img[@src='/olam/English/brand30/bt/btnContinue.gif']")
	public WebElement imgContinueAccountDetails;
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//p[contains(text(),'Please enter an email')]")
	public WebElement txtErrBlankemail;

	@FindBy(xpath="//p[contains(text(),'Please enter a valid email format')]")
	public WebElement txtErrinvalidemail;

	@FindBy(xpath="//p[@class='botMar0']")
	public WebElement txtErrMsg;
	
	@FindBy(id="accountEntered") 
	public WebElement edtBAN;

	@FindBy(id="userZip") 
	public WebElement edtZipCode;
	
	@FindBy(id="loginMethodSelectType")
	public WebElement lstSelectAccount;
	
	@FindBy(xpath="//div[@id='secondary-content']/div[@class='MarTop4']")
	public WebElement txtSteps;

	@FindBy(xpath="//div[@id='StepIndicator']")
	public WebElement txtStepIndicators;
	
	@FindBy(xpath="//div[@class='row-seam past top-round']")
	public WebElement txtSelectCredentialStep;
	
	@FindBy(xpath="//div[@class='row-seam current']")
	public WebElement txtSelectRetrievalStep;
	
	@FindBy(xpath="//div[@class='row-seam ']")
	public WebElement txtResetPasswordStep;
	
	@FindBy(xpath="//h1[contains(text(),'Forgot Password')]")
	public WebElement txtForgotPasswordHeading;
	
	@FindBy(xpath="//h1[contains(text(),'Forgot User ID / Password')]")
	public WebElement txtForgotUidAndPasswordHeading;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}