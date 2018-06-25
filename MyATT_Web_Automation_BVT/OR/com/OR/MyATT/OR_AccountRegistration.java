package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_AccountRegistration {
	

	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	
	/*---------------------------
	 * Table objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//div[@id='emptyfooter']")
	public WebElement tblEmptyFooter;
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//input[@id='availableorc']")
	public WebElement rdoAvailableORC;
	
	@FindBy(xpath="//input[@id='unavailableorc']")
	public WebElement rdoNeedORC;

	@FindBy(xpath="id('createMemberId')|//input[@id='createMemberId']")
	public WebElement rdoCreateAccessID;
	
	@FindBy(xpath="//input[@id='requestSecondaryAccess_true']")
	public WebElement rdoNameNotOnTheBill;
	
	@FindBy(xpath="//input[@id='gainAccessRadios_BAN']")
	public WebElement rdoBAN;
	
	@FindBy(xpath="//input[@id='email']")
	public WebElement rdoORCSendByEmail;
	
	@FindBy(xpath="//input[@id='text_message_by_sms']")
	public WebElement rdoORCSendBySMS;
	
	@FindBy(xpath="//input[@id='mail']")
	public WebElement rdoORCSendByUSMail;

	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//select[@id='selectedAccountType']/option")
	public WebElement lstSelectAccDropdownOptions;
	
	@FindBy(xpath="//select[@id='securityQuestion1Id']")
	public WebElement lstSelectSecurityQues1Options;
	
	@FindBy(xpath="//select[@id='securityQuestion2Id']")
	public WebElement lstSelectSecurityQues2Options;

	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//input[@id='acceptTerms']")
	public WebElement chkTermsAndConditions;


	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//input[@id='selectedAccountId']")
	public WebElement edtWirelessNumber;
	
	@FindBy(xpath="//input[@id='zipCode']")
	public WebElement edtZipCode;
	
	@FindBy(xpath="//input[@id='orc']")
	public WebElement edtEnterORC;
	
	@FindBy(xpath="//input[@id='slid']")
	public WebElement edtPreferredAccessID;
	
	@FindBy(xpath="//input[@id='emailAddress']")
	public WebElement edtEmailAddress;
	
	@FindBy(xpath="//input[@id='emailAddressVerify']")
	public WebElement edtConfirmEmailAddress;
	
	@FindBy(xpath="//input[@id='password']")
	public WebElement edtPassword;
	
	@FindBy(xpath="//input[@id='passwordVerify']")
	public WebElement edtConfirmPassword;
	
	@FindBy(xpath="//input[@id='firstName']")
	public WebElement edtFirstName;
	
	@FindBy(xpath="//input[@id='lastName']")
	public WebElement edtLastName;
	
	@FindBy(xpath="//input[@id='securityAnswer1']")
	public WebElement edtSecurityAns1;
	
	@FindBy(xpath="//input[@id='securityAnswer2']")
	public WebElement edtSecurityAns2;
	
	@FindBy(xpath="//input[@id='BAN']")
	public WebElement edtBAN;
	
	@FindBy(xpath="//input[@id='f_orc']")
	public WebElement edtEnterORCReceived;
	
	@FindBy(xpath="//input[@id='f_passcode']")
	public WebElement edtPassocde;

	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	

	@FindBy(xpath="//*[text()='Select an Account']")
	public WebElement txtSelectAnAccount;
	
	@FindBy(xpath="//h1[contains(text(),'Select the Account You')]")
	public WebElement txtSelectAccountHeading;
	
	@FindBy(xpath="//h2[contains(text(),'Provide Account Information')]")
	public WebElement txtAccInfoSubHeading;
	
	@FindBy(xpath="//select[@id='selectedAccountType']")
	public WebElement txtSelectAccDropdownTab;
	
	@FindBy(xpath="//select[@id='selectedAccountType']//option[text()='Wireless']")
	public WebElement txtWireless;
	
	@FindBy(xpath="//h1[contains(text(),'Your AT&T Access ID request')]")
	public WebElement txtATTAccessIDRequest;
	
	@FindBy(xpath="//h2[contains(text(),'Receive Online Registration Code')]")
	public WebElement txtOnlineRegistrationCode;
	
	@FindBy(xpath="//h1[contains(text(),'Create an AT&T Access ID')]")
	public WebElement txtCreateATTAccessID;
	
	@FindBy(xpath="//strong[contains(text(),'The email address you entered is not valid.')]")
	public WebElement txtEmailAlert;
	
	@FindBy(xpath="//div[@class='errorMsg box']//p[contains(text(),'Check to be sure you entered the correct Online Registration Code')]")
	public WebElement txtORCErrorMsg;
	
	@FindBy(xpath="//div[@id='SMSDIV']")
	public WebElement txtOCRSMSOption;
	
	@FindBy(xpath="//div[@id='EMAILDIV']")
	public WebElement txtOCREmailOption;
	
	@FindBy(xpath="//div[@id='PSOTDIV']")
	public WebElement txtOCRUSPostOption;
	
	@FindBy(xpath="//h1[contains(text(),'Proceed With Online Account Management Registration')]")
	public WebElement txtProceedWithOnlineAccReg;
	
	@FindBy(xpath="//h1[contains(text(),'Account Verification')]")
	public WebElement txtAccountVerification;
	
	@FindBy(xpath="//p[contains(text(),'Your Online Registration Code has been sent via US Mail')]")
	public WebElement txtUSMailMessage;
	
	@FindBy(xpath="//div[@id='EnterORCText']")
	public WebElement txtEnterORCSection;
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[contains(text(),'More options')]")
	public WebElement lnkMoreOptions;
	
	@FindBy(xpath="//input[@alt='submit']")
	public WebElement lnkContinue;
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[@alt='Next']|//a[@id='showNext']")
	public WebElement btnNext;
	
	//@FindBy(xpath="//input[@id='enbNextBtn']")
	@FindBy(xpath="//*[@id='enbNextBtn']")
	public WebElement btnNext2;
	
	//@FindBy(xpath="//input[@alt='Next']")
	@FindBy(xpath="//*[@alt='Next']")
	public WebElement btnNext3;
	
	@FindBy(xpath="//input[contains(@src,'btnNext.png')]")
	public WebElement btnNext4;
	
	@FindBy(xpath="//a[contains(@id,'NextBtn')]")
	public WebElement btnNextEnabledOrDisabled;
	
	@FindBy(xpath="//*[@alt='NextbtnDisabled']")
	public WebElement btnNextButtonDisabled;
	
	@FindBy(xpath="//img[@alt='Back']")
	public WebElement btnBack;
	
	
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_AccountRegistration(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
