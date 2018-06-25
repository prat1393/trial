package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_LinkAnAccount 
{
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
		@FindBy(xpath="//a[@id='enbNextBtn']")
		public WebElement imgNext;
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
		@FindBy(xpath="//input[@type='radio' and @value='LinkAnotherAccount']	")
		public WebElement rdoLinkAnotherAcc;
		
		@FindBy(xpath="//input[@type='radio' and @value='ManageAccountNow']")
		public WebElement rdoManageAccNow;
		
		@FindBy(xpath="//form[@id='LinkAnotherORmanageAcctForm']")
		public WebElement rdoRadioOptionForm;
		
		@FindBy(xpath="//div[contains(@id,'gotoAccess')]")
		public WebElement rdoGoToAttToManageAcc;
		
		@FindBy(id="uniform-alreadyHaveOrc")
		public WebElement radAlreadyHaveOrc;
		
		@FindBy(id="uniform-unavailableorc")
		public WebElement radNeedORC;
		
		@FindBy(id="unavailableorc")
		public WebElement radORCclick;
		
		@FindBy(id="uniform-email")
		public WebElement radEmail;
		
		@FindBy(id="uniform-telephone")
		public WebElement radTelephone;
		
		@FindBy(id="uniform-mail")
		public WebElement radMail;
		
		@FindBy(id="uniform-requestSecondaryAccess")
		public WebElement radRequestSecondaryAccesss;
		
		@FindBy(xpath="//label[@for = 'requestSecondaryAccess']")
		public WebElement radReqForSecAccess;
		
		
		

		
		
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	
		@FindBy(xpath="//select[contains(@id,'acctSelectType')]") 
		public WebElement lstSelAcc;
		
		@FindBy(id="uniform-acctSelectType") 
		public WebElement lstSelAccType;
		
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
		@FindBy(xpath="//input[@id='acctSelectVal']")
		public WebElement edtBillingAccNum;
		
		@FindBy(xpath="//input[@id='acctBillingZip']")
		public WebElement edtZipCode;
		
		@FindBy(xpath="//input[@id='password']")
		public WebElement edtPassword;
		
		@FindBy(xpath="//input[@id='userName']")
		public WebElement edtUserName;
	 
		@FindBy(id="memberId")
		public WebElement edtMemberId;
		
		@FindBy(xpath="//input[@id='last4ssn']")
		public WebElement edtLast4SSN;
		
		@FindBy(id="customerCode")
		public WebElement edtCustomerCode;
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
		@FindBy(xpath="//h2[@class='accessID']")
		public WebElement txtLinkAnAccount;
		
		@FindBy(xpath="//div[@class='msg box']/p")
		public WebElement txtMessageUnderLinkAnAccount;
		
		@FindBy(xpath="//h1[contains(text(),'Account Overview')]")
		public WebElement txtAccountOverview;
		
		@FindBy(xpath="//h1[contains(text(),'Link an Account - Select Account Type')]")
		public WebElement txtSelectAccountType;
		
		@FindBy(xpath="//h2[contains(text(),'Provide Account Information')]")
		public WebElement txtProvideAccountInformation;
		
		@FindBy(xpath="//h1[text()='Convert to an AT&T Access ID']")
		public WebElement txtConverttoAnATTAccessID;
		
		@FindBy(xpath="//div/span[contains(text(),'Link Accounts')]")
		public WebElement txtLinkAccounts;
		
		@FindBy(xpath="//div/span[contains(text(),'Confirmation')]")
		public WebElement txtConfirmation;
		
		@FindBy(xpath="//label[@for='acctSelectType']")
		public WebElement txtAcctype;
		
		@FindBy(xpath="//option[@value='attemail']")
		public WebElement txtAttmail;
		
		@FindBy(xpath="//label[@for='email']")
		public WebElement txtAttEmailAddress;
		
		@FindBy(xpath="//h1[contains(text(),'AT&T Access ID - Verify Account Information')]")
		public WebElement txtVerifyAccountInformation;
		
		@FindBy(xpath="//*[contains(@class,'title')]//h1[contains(text(),'Verify Account Information')]")
		public WebElement txtVerifyAccountInformation2;
		
		@FindBy(xpath="//h2[contains(text(),'Link Account')]")
		public WebElement txtLinkAccount;
		
		@FindBy(xpath="//span[@class='statictxt top6px top5pxIE']")
		public WebElement txtMemberIDfeild;
		
		@FindBy(xpath="//label[@for='lname'][contains(text(),'Member ID')]")
		public WebElement txtMemberIDHeading;
		
		@FindBy(xpath="//label[@for='lname'][contains(text(),'Password')]")
		public WebElement txtPasswordHeading;
		
		@FindBy(xpath="//h1[contains(text(),'Agree to Account Linking')]")
		public WebElement txtAgreeAccLink;
		
		//h2[contains(text(),'AT&T Access ID Linking')]
		@FindBy(xpath="//h2[contains(text(),'AT&T Access ID Linking')]")
		public WebElement txtAttAccessSubHeading;

		@FindBy(xpath="//span[@class='colorOrange font16']")
		public WebElement txtlinkedUserid;
		
		@FindBy(xpath="//p[contains(text(),'You successfully linked your AT&T Email account to your new AT&T Access ID and password.')] ")
		public WebElement txtSuccessfulMsg;
		
		@FindBy(xpath="//h1[contains(text(),'Success')]")
		public WebElement txtSuccessTitle;
		
		@FindBy(xpath="//h2[contains(text(),'Confirmation')]")
		public WebElement txtConfirmationTitle;
		
		@FindBy(xpath="//*[contains(@class,'title')]//*[contains(text(),'Confirmation')]")
		public WebElement txtConfirmationTitle2;
		
		@FindBy(xpath="//h1[contains(text(),'Select the Account You'd Like To Manage')]")
		public WebElement txtSelectAccountHeader;
		
		@FindBy(xpath="//h1[contains(text(),'AT&T Access ID – Receive Online Registration Code')]")
		public WebElement txtORCPg;
		
		@FindBy(xpath="//div[@class='box steps']")
		public WebElement txtStepIndicator;
		
		@FindBy(xpath="//label[contains(text(),'@att.com')]")
		public WebElement txtEmailText;
		
		@FindBy(xpath="//b[contains(text(),'account is already linked')]")
		public WebElement txtAccountAlreadyLinked;
		
		@FindBy(xpath="//label[contains(text(),'Wireless Number')]")
		public WebElement txtWirelessNumber;
		
		@FindBy(xpath="//label[contains(text(),'Account Type')]")
		public WebElement txtAccountType;
		
		@FindBy(xpath="//label[contains(text(),'Billing ZIP Code')]")
		public WebElement txtBillingZIPCode;
		
		@FindBy(xpath="//*[contains(text(),'For your security')]")
		public WebElement txtSecurityWarning;
		
		@FindBy(xpath="//div[contains(@id,'acctSelectType')]")
		public WebElement txtSelectAccount;
		
		
		
		
		
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
		@FindBy(xpath="//a/img[@src='/olam/English/brand30/bt/btnLinkMyAccounts.png']")
		public WebElement lnkLinkMyAccount;
	
		@FindBy(xpath="//a[@href='/olam/SLIDProfileview.myworld']")
		public WebElement lnkAttAccessIDProfile;
		
		@FindBy(xpath="//a[@href='/olam/SLIDProfilechangeSLIDView.myworld']")
		public WebElement lnkChangeAttAccessID;
		
		@FindBy(xpath="//a[@title='Cancel']")
		public WebElement lnkCancel;
		
		@FindBy(xpath="//a[@id='TnC']")
		public WebElement lnkTnC;
		
		@FindBy(xpath="//a[contains(text(),'Forgot your password?')]")
		public WebElement lnkForgotYourPassword;
		
		@FindBy(xpath="//a[contains(text(),'Cancel')][@href='#']")
		public WebElement lnkCancel2;
		
		@FindBy(xpath="//a[text()='More options']")
		public WebElement lnkMoreOptions;
		
		@FindBy(xpath="//a[@title='Link your accounts']")
		public WebElement lnkLinkMyAccounts;
		
		@FindBy(xpath="//a[contains(@class,'arrowRt') and contains(text(),'Link another account')]")
		public WebElement lnkLinkAnotherAccount;
		
		
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
		@FindBy(xpath="//a[@id='enbNextBtn']")
		public WebElement btnNext;
		
		@FindBy(xpath="//img[@alt='Back']")
		public WebElement btnback;
		
		@FindBy(xpath="//a[@id='enbSubmitBtn']")
		public WebElement btnContinue;
		
		@FindBy(xpath="//a[@name='contBtn']")
		public WebElement btnContinue2;
		
		@FindBy(xpath="//img[@alt='Continue']")
		public WebElement btnContinue3;
		
	//	@FindBy(xpath="//a[contains(text(),'Link your accounts')]")
		@FindBy(xpath="//a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'Link your accounts')] | //a[contains(text(),'Link your accounts')]")
		public WebElement btnLinkMyAccounts;
		
		@FindBy(xpath="//a[@id='enbSubmitBtn']")
		public WebElement btnNext1;
		
		@FindBy(xpath="//img[@alt='Back']")
		public WebElement btnBack;
		
		@FindBy(xpath="//input[@alt='Next']")
		public WebElement btnNext2;
		
		@FindBy(xpath="//img[@alt='Next']")
		public WebElement btnNext3;
		
		@FindBy(xpath="//input[@type='image']")
		public WebElement btnShowNext;
		
		@FindBy(xpath="//a[@href='#'][@name='contBtn']")
		public WebElement btnContinueInConfirmationPage;
		
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_LinkAnAccount(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}


