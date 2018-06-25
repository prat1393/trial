package com.OR.MyATT; 
 
import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy; 
import org.openqa.selenium.support.PageFactory; 
 
public class OR_Login { 
 
 
	/*--------------------------- 
	 * Image objects add below 
	 *---------------------------*/ 
 
 
 
	/*--------------------------- 
	 * Radio button objects add below 
	 *---------------------------*/ 
	@FindBy(id="desgMainCTN_Y") 
	public WebElement radYes;
	
	@FindBy(xpath="//div[contains(@id,'uniform-desgMainCTN_Y')]") 
	public WebElement radBtnYes;
	
	@FindBy(xpath="//div[contains(@id,'uniform-desgMainCTN_N')]") 
	public WebElement radBtnNo;
 
	/*@FindBy(xpath="//input[@class='radiohead-11'][@id='desgMainCTN_N']") 
	public WebElement radNo; */ 
 
	@FindBy(xpath="//label[@for='desgMainCTN_N']") 
	public WebElement radNo; 
 
	@FindBy(xpath="//div[@class='radio' and contains(@id,'Userid')]") 
	public WebElement radUserID; 
	
	@FindBy(xpath="//div[@class='radio' and contains(@id,'forgotPassword')]") 
	public WebElement radPassword;
 
	@FindBy(xpath="//div[@class='radio' and contains(@id,'uniform-forgotUserid2')]") 
	public WebElement radUserIDPwd; 
	/*--------------------------- 
	 * List objects add below 
	 *---------------------------*/ 
	@FindBy(id="SelectAccountType") 
	public WebElement lstSelectAccountType; 
	
	@FindBy(id="selectedAccountType") 
	public WebElement lstSelectAccountTypeDropDown; 
 
	@FindBy(id="accountType") 
	public WebElement lstSelectSMBAccountType; 
	
	@FindBy(xpath="//select[contains(@id,'loginMethodSelectType')]") 
	public WebElement lstAccountDropDown;
 
	@FindBy(id="question1") 
	public WebElement lstSecurityQ1; 
 
	@FindBy(id="question2") 
	public WebElement lstSecurityQ2; 
	
	
 
 
	/*--------------------------- 
	 * Checkbox objects add below 
	 *---------------------------*/ 
 
	@FindBy(id="dontKnow0") 
	public WebElement chkManageThisAccountLater; 
 
 
 
 
	/*--------------------------- 
	 * Edit box objects add below 
	 *---------------------------*/ 
//	@FindBy(id="userName")           //userID wireless_num 
//	@FindBy(xpath="//input[@id='userName' or @id='userID' or 'wireless_num']") 
//	@FindBy(id="wireless_num") 
	@FindBy(xpath="//input[@id='wireless_num'] | //*[@id='userName']")
	public WebElement edtUserId; 
 
	@FindBy(id="password" ) 
	public WebElement edtPassword;
	
	@FindBy(xpath="//input[@class='loginInput lgwgPassword']" ) 
	public WebElement edtRePassword;
 
	@FindBy(name="wireless_num") 
	public WebElement edtUserIDSMB; 
 
	@FindBy(id="primaryEmailAddress") 
	public WebElement edtEmailAddress; 
 
	@FindBy(id="confirmPrimaryEmailAddress")
	public WebElement edtConfirmEmailAddress; 
 
	@FindBy(id="firstName") 
	public WebElement edtFirstName; 
 
	@FindBy(id="lastName") 
	public WebElement edtLastName; 
 
	@FindBy(xpath="//input[@id='userID' or @id='username' or @id='wireless_num' or @id='userName']") 
	public WebElement edtUserIdReal; 
 
	@FindBy(xpath="//input[@title='Username']") 
	public WebElement edtSMBuserId ; 
 
	@FindBy(id="primaryEmailAddress") 
	public WebElement edtPrimaryEmailAdd ; 
 
	@FindBy(xpath="//input[@title='Password' and @name='pass']") 
	public WebElement edtSMBpassword ; 
 
	@FindBy(xpath="//input[contains(@id,'passcode')]") 
	public WebElement edtPassocde ; 
 
	@FindBy(id="answer1") 
	public WebElement edtSecurityA1; 
 
	@FindBy(id="answer2") 
	public WebElement edtSecurityA2; 
 
	@FindBy(id="pwd") 
	public WebElement edtPasswordInReminderPage; 
	
	@FindBy(xpath="//input[contains(@id,'customerEmailAddress')]") 
	public WebElement edtContactEmailAddress ;
	
	@FindBy(xpath="//input[contains(@id,'accountEntered')]") 
	public WebElement edtWirelessNumber ;
		
	@FindBy(xpath="//input[contains(@id,'Zip')]") 
	public WebElement edtBillingZipCode ;
	
	/*--------------------------- 
	 * Text objects add below 
	 *---------------------------*/ 
	@FindBy(xpath = "//h1[contains(text(),'Log in to manage your account')] | //*[contains(text(),'Log in to your account')]") 
	public WebElement txtLogInToManageUrAcc; 
	
	@FindBy(xpath="//span[contains(text(),'Select account')]")
	public WebElement txtSelectAccountType;
 
	@FindBy(xpath="//*[text()='No'][@class='radio font12 MarLeft10 lineHeight18']") 
	public WebElement txtNo; 
 
	@FindBy(id="userIDErrMsg") 
	public WebElement txtErrorUserID; 
 
	@FindBy(id="passwordErrMsg") 
	public WebElement txtErrorPwd; 
 
	@FindBy(xpath="//h3[contains(text(),'Need help using myAT&T? Play videos below:')]") 
	public WebElement txtNeedHelp; 
 
	@FindBy(id="gvpModalInjection") 
	public WebElement txtLearnHowToRegisterPopup;
 
	@FindBy(xpath="//*[contains(text(),'Caps Lock')]") 
	public WebElement txtCAPSlockError; 
 
	@FindBy(xpath="//div[@class='errorMsg box']//p//a[contains(text(),'I forgot my User ID')] | //div[@class='errorheading']") 
	public WebElement txtError; 
 
	@FindBy(xpath="//h1[contains(text(),'Account Manager')]") 
	public WebElement txtSMBAccountManager; 
 
	@FindBy(xpath="//h1[contains(text(),'Confirm Your')]")
	public WebElement txtConfirmYourPasscode; 
 
	@FindBy(xpath="//p[contains(text(),'Security Questions')]") 
	public WebElement txtSecurityQ; 
 
	@FindBy(xpath="//label[contains(text(),'know the passcode')]") 
	public WebElement txtIDontKnowThePasscode; 
 
	@FindBy(xpath="//h1[contains(text(),'Reminder: You created a new password')]") 
	public WebElement txtReminder; 
 
	@FindBy(xpath="//h1[contains(text(),'Important Information')]") 
	public WebElement txtImportantInformation; 
 
	@FindBy(xpath="//h1[contains(text(),'Introducing Premier')]") 
	public WebElement txtIntroducingPremier;
	
	@FindBy(xpath="//p[contains(text(),'Learn how to solve')]") 
	public WebElement txtVideoCarasolHover;
	
	@FindBy(xpath="//h1[contains(text(),'Make password resets')]") 
	public WebElement txtMakeAPasswordReset;
 
	@FindBy(xpath="//h1[contains(text(),'Is your myAT&T profile up-to-date?')]") 
	public WebElement txtIsMYATTProfileUpToDate;
	
	@FindBy(xpath="//p[contains(text(),'Welcome Back,')]") 
	public WebElement txtWelcomeBackFirstNameLastName;
	
	@FindBy(xpath="//p[contains(text(),'We want to confirm you have the most current information in your myAT&T profile')]") 
	public WebElement txtWelcomeBackDescription;
	
	@FindBy(xpath="//p[contains(text(),'Would you like to provide your wireless number')]") 
	public WebElement txtMyWirelessNumberDesc;
	
	@FindBy(xpath="//*[contains(text(),'Select the Account')]") 
	public WebElement txtSelectAccount;
	
	@FindBy(xpath="//h1[contains(text(),'Forgot User ID')]") 
	public WebElement txtForgotIDPasswordHeader;
	
	@FindBy(xpath="//*[contains(text(),'User ID and Password')]") 
	public WebElement txtUserIDAndPassword;
	
	@FindBy(xpath="//*[contains(text(),'Video Details')]") 
	public WebElement txtVideoDetailsPopUP; 
	
	@FindBy(xpath="//h1[contains(text(),'Forgot User ID')]") 
	public WebElement txtForgotID; 
	
	@FindBy(xpath="//div[@class='errorMsg box']//p//a[contains(text(),'register your account')]")
	public WebElement txtErrorMsg;
	
	@FindBy(xpath="//div[@class='errorheading']/parent::div/p[contains(text(),'LU100')] | //div[@class='errorMsg box']//p[contains(normalize-space(),'LU100')]")
	public WebElement txtErrorLU100;
	
	@FindBy(xpath="//div[@class='errorMsg box']//strong[contains(normalize-space(),'LU200')]")
	public WebElement txtErrorLU200;
	
	@FindBy(xpath="//div[@class='warning'][contains(normalize-space(),'LU002')]")
	public WebElement txtErrorLU002;
	
	@FindBy(xpath="//div[@class='errorMsg box']//p[contains(normalize-space(),'LGN0183')]")
	public WebElement txtErrLGN0183;
	
	@FindBy(xpath="//div[@class='errorMsg box']//strong[contains(normalize-space(),'LU150')] | //div[@class='errorheading']/parent::div/p[contains(text(),'LU150')]")
	public WebElement txtErrorLU150;
	
	@FindBy(xpath="//*[contains(text(),'I forgot')]") 
	public WebElement txtIForgotMy;
	
	@FindBy(xpath="//*[contains(text(),'Please enter an email') and contains(@class,'err')]") 
	public WebElement txtPleaseEnterEmailError;
	
	@FindBy(xpath="//*[contains(text(),'7 character') and contains(@class,'err')]") 
	public WebElement txtEmailMustBeMoreThan7CharactersError;
	
	@FindBy(xpath="//*[contains(text(),'80 character') and contains(@class,'err')]") 
	public WebElement txtEmailMustBeLessThan80CharactersError;
	
	@FindBy(xpath="//*[contains(text(),'//*[contains(text(),'valid email format') and contains(@class,'err')]') and contains(@class,'err')]") 
	public WebElement txtEnterValidEmailFormatErrors;
	
	@FindBy(xpath="//*[contains(text(),'find a match for')]") 
	public WebElement txtCantFindAMatchFor;
	
	@FindBy(xpath="//h1[contains(text(),'User ID Sent')]") 
	public WebElement txtUserIDSent;
	
	@FindBy(xpath="//*[contains(text(),'As requested')]") 
	public WebElement txtAsRequestedWeHaveSent;
	/*--------------------------- 
	 * Image objects add below 
	 *---------------------------*/ 
	@FindBy(xpath="//img[contains(@alt,'checkmark')]") 
	public WebElement imgCheckMark;
	/*--------------------------- 
	 * Link objects add below 
	 *---------------------------*/ 
	@FindBy(xpath="//a[@href='/olam/enterEmailForgotId.myworld']") 
	public WebElement lnkForgotId; 
 
	@FindBy(xpath="//img[@alt='Remind Me Later']|//a[contains(text(),'Remind me later ')]|//a[@id='remindLater']") 
	public WebElement lnkRemindMeLater; 
 
	@FindBy(xpath="//a[@id='ge5p_z2-zipcode-register']") 
	public WebElement lnkRegister; 
 
	@FindBy(xpath="//a[@href='/olam/forgotPasswordAction.olamexecute?forgotPasswordActionEvent=forgotPasswordStep1']") 
	public WebElement lnkForgotPassword; 
 
	@FindBy(xpath="//a[contains(@href,'Registration')] | //a[contains(text(),'Create an ID')]") 
	public WebElement lnkRegisterToday;
	
	@FindBy(xpath="//a[contains(text(),'Create an ID')]") 
	public WebElement lnkCreateID; 
	
	@FindBy(xpath="//a[text()='Cancel']|//a[contains(@name,'cancel')]") 
	public WebElement lnkCancel; 
	
	@FindBy(xpath="//a[contains(text(),'Need help with your password')]") 
	public WebElement lnkNeedHelpWithYourPassword; 
	
	@FindBy(id="mycarousel") 
	public WebElement lnkVideoCarousel; 
 
	@FindBy(xpath="//a[contains(text(),'Help With Logging In')]") 
	public WebElement lnkHowToRegister; 
 
	@FindBy(xpath="//button[contains(text(),'Close')]") 
	public WebElement lnkClose; 
 
	@FindBy(xpath="//*[contains(text(),'Thanks')]") 
	public WebElement lnkNoThanks; 
 
	@FindBy(id="remindMe") 
	public WebElement lnkContinue; 
	
	@FindBy(id="loginBtn") 
	public WebElement lnkLoginBtn; 
 
	@FindBy(xpath="//a[contains(text(),'Continue to myAT&T account')]") 
	public WebElement lnkNoThanksContinue; 
	
	@FindBy(xpath="//*[@id='contactEmailAddBlock']//parent::div//a[contains(@id,'dontKnowEmail')]") 
	public WebElement lnkDontKnowContactEmailAddressForUserIDAndPassword; 
	/*--------------------------- 
	 * Button objects add below 
	 *---------------------------*/ 
//	@FindBy(xpath="//input[@title='Login'][@alt='Login']") 
	@FindBy(xpath="//button[@id='loginButton'] |//input[@title='Login'][@alt='Login']|//button[contains(text(),'Log in')]") 
	public WebElement btnLogin; 
 
	@FindBy(xpath="//button[contains(text(),'Continue') and contains(@id,'btnPrimarySmall')]") 
	public WebElement btnContinue; 
	
	@FindBy(xpath="//input[@type='image'][@alt='Continue'] | //img[@alt='Continue'] | //a[contains(text(),'Continue to my account')]") 
	public WebElement btnContinueToAccount; 
 
	@FindBy(xpath="//*[contains(text(),'I Understand the Risks')]") 
	public WebElement btnIUnderstand; 
 
	@FindBy(xpath="//*[@id='exceptionDialogButton']") 
	public WebElement btnAddException; 
 
	@FindBy(xpath="//a[contains(text(),'No, Thanks')]") 
	public WebElement btnNoThanks ; 
 
	@FindBy(xpath="//input[@type='image' and @alt='Login']") 
	public WebElement btnSMBlogin ; 
 
	@FindBy(xpath="//a[contains(@onclick,'NoThanks')]") 
	public WebElement btnNoThanksOnAccountValidationPage; 
 
	@FindBy(xpath="//img[@alt='Check Now']") 
	public WebElement btnCheckNowOnUpgradeEligibility; 
 
	@FindBy(xpath="//input[@id='bt_continue']  | //a[contains(text(),'Continue')]" ) 
	public WebElement btnContinueOnPasscodePage; 
 
	@FindBy(xpath="//img[contains(@src,'btnNext')] | //span[@id='enableBtnId']/a[contains(text(),'Next')]") 
	public WebElement btnNextOnPasscodePage; 
 
	@FindBy(id="loginButton") 
	public WebElement btnLoginOnReminderPage; 
 
	@FindBy(xpath="//img[contains(@src,'btnCancel')]") 
	public WebElement btnCancel; 
	
	@FindBy(xpath="//div[@id='uniform-question1']") 
	public WebElement btnSecurityQ1; 
 
	@FindBy(xpath="//div[@id='uniform-question2']") 
	public WebElement btnSecurityQ2; 
	
	@FindBy(xpath="//a[@name='Upgrade now']") 
	public WebElement btnUpgradeNow; 
	
	@FindBy(xpath="//*[contains(@class,'btnLt')]") 
	public WebElement btnNo; 
	
	@FindBy(xpath="//*[contains(@class,'btnRt')]") 
	public WebElement btnYes; 
	
	@FindBy(xpath="//a[contains(@id,'dsbContBtn')]") 
	public WebElement btnDisableContinue; 
	
	@FindBy(xpath="//a[contains(@id,'enbContBtn')]")
	public WebElement btnEnableContinue;
	
	@FindBy(xpath="//div/a[contains(text(),'Enter a different ID')]")
	public WebElement lnkEnterADifferentID;
 
	/*--------------------------- 
	 * Frame objects add below 
	 *---------------------------*/ 
	@FindBy(xpath="//iframe[@src='login/inProgress']") 
	public WebElement frmLogInProgress; 
 
	@FindBy(xpath="//ifraime[@src='images/attLoader']") 
	public WebElement imgATTLogo; 
 
	@FindBy(xpath="//iframe[contains(@src,'Cancel')]") 
	public WebElement frmCancelRegistration; 
	
	/*--------------------------- 
	 * WebDriver objects add below 
	 *---------------------------*/ 
	WebDriver driver; 
 
 
 
 
	public OR_Login(WebDriver driver){ 
		this.driver = driver; 
		PageFactory.initElements(driver, this); 
	} 
}