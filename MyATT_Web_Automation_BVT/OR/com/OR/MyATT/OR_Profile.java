package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_Profile {
	
		
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	@FindBy(xpath="//li[@class='editLeft']//img") 
	public WebElement imgEditIcon;
	
	
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	@FindBy(xpath="//input[@name='acctProfileInfo.billLang'][2]") 
	public WebElement radEspanol;
	
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	
	
	
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	@FindBy(id="checkbox_6") 
	public WebElement edtEmailNotification;
	
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	@FindBy(id="accountNickname") 
	public WebElement edtAccNickName;
	
	@FindBy(xpath="//input[@id='subHandle']") 
	public WebElement edtMemberEmail;
	
	@FindBy(xpath="//input[@id='addsubSave_currentClearPassword']") 
	public WebElement edtPassword;
	
	@FindBy(xpath="//input[@id='addsubSave_confirmPassword']") 
	public WebElement edtConfirmPassword;
	
	@FindBy(xpath="//input[@id='addsubSave_firstName']") 
	public WebElement edtFirstName;
		
	@FindBy(xpath="//input[@id='addsubSave_lastName']") 
	public WebElement edtLastName;
	
	@FindBy(xpath="//input[@id='addsubSave_nickName']") 
	public WebElement edtNickName;
	
	@FindBy(xpath="//input[@id='contactEmail']") 
	public WebElement edtAlternateEmail;

	
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//h1[contains(text(),'My Profile') or contains(text(),'Access ID')] | //a[contains(@id,'profile')]/i[contains(@aria-label,'Profile')]") 
	public WebElement txtMyProfileTitle;
	
	@FindBy(xpath="//p[contains(text(),'Note: Your changes')]") 
	public WebElement txtNoteBilling;
	
	@FindBy(xpath="//li[@class='top-round']/a[@id='accountInfo']") 
	public WebElement txtAccountInformationDefault;
	
	@FindBy(xpath="//li[@class='top-round']/a[@id='userInfo']") 
	public WebElement txtUserInformation;
	
	@FindBy(xpath="//li[@class='top-round']/a[@id='DNS']") 
	public WebElement txtMarketingPreference;
	
	@FindBy(css="div.group-title > h2") 
	public WebElement txtAccountNames;
	
	@FindBy(xpath="//div[@class='group-title']/h2[contains(text(),'Billing Contact Information')]") 
	public WebElement txtBillingContactInfo;
	
	@FindBy(xpath="//h2[contains(text(),'Billing Notifications')]") 
	public WebElement txtBillingNotifications;
	
	@FindBy(xpath="//h3[contains(text(),'Payment received')]|//h2[contains(text(),'Payment Options')]|//h2[contains(text(),'Payment Profiles')]") 
	public WebElement txtPaymentOptions;
	
	@FindBy(xpath="//h2[contains(text(),'Bill Options')]") 
	public WebElement txtBillOptions;
	
	@FindBy(xpath="//label[contains(text(),'Billing Name')]") 
	public WebElement txtBillName;
	
	@FindBy(xpath="//label[contains(text(),'Attention')]") 
	public WebElement txtAttention;
	
	@FindBy(xpath="//strong[contains(text(),'Street Address')]") 
	public WebElement txtStreetAddr;
	
	@FindBy(xpath="//strong[contains(text(),'Apartment / Suite Number')]") 
	public WebElement txtAppt;
	
	@FindBy(xpath="//label[contains(text(),'City')]") 
	public WebElement txtCity;
	
	@FindBy(xpath="//label[contains(text(),'State')]") 
	public WebElement txtState;
	
	@FindBy(xpath="//label[contains(text(),'ZIP Code')]") 
	public WebElement txtZipCode;
	
	@FindBy(xpath="//label[contains(text(),'Home Phone')]") 
	public WebElement txtHomePhone;
	
	@FindBy(xpath="//label[contains(text(),'Work Phone')]") 
	public WebElement txtWorkPhone;
	
	@FindBy(xpath="//label[contains(text(),'Work Extension')]") 
	public WebElement txtWorkExt;
	
	@FindBy(xpath="//label[contains(text(),'Billing Email Address')]") 
	public WebElement txtBillingEmailAddr;
	
	@FindBy(xpath="//label[contains(text(),'Bill Language')]") 
	public WebElement txtBillLang;
	
	@FindBy(xpath="//div[@class='USM-section-title']") 
	public WebElement txtAccountModule;
	
	@FindBy(xpath="//h2[contains(text(),'Authorized Users')]") 
	public WebElement txtAuthorizedUsers;
	
	@FindBy(xpath="//h1[contains(text(),'Edit Account Information')]") 
	public WebElement txtProfileEditAccInfoTitle;
	
	@FindBy(xpath="//h1[contains(text(),'Confirmation')]") 
	public WebElement txtConfirmationTitle;
	
	@FindBy(xpath="//div[2][@class='row-seamless']//div[1][contains(@class,'tipPad safariComp')]//label[@class='statictxt normalFont']") 
	public WebElement txtAccNickNameValue;
	
	@FindBy(xpath="//h2[contains(text(),'Billing Contact Information')]") 
	public WebElement txtBillingContactInformationTitle;
	
	@FindBy(xpath="//h1[contains(text(),'Billing Notifications')]") 
	public WebElement txtBillingNotificationTitle;
	
	@FindBy(xpath="//h2[contains(text(),'Account Information')]") 
	public WebElement txtAccInfo;
	
	@FindBy(xpath="//*[contains(text(),' Your AT&T wireless service is part of a bundle with another provider.')]") 
	public WebElement txtNote;
	
	@FindBy(xpath="//p[contains(text(),'Privacy Policy and Terms & Conditions')]") 
	public WebElement txtTermsAndConditions;
	
	@FindBy(xpath="//li[@class='account-number']") 
	public WebElement txtAccountNumber;
	
	
	@FindBy(xpath="//h1[contains(text(),'Update Your Contact Email Address')]") 
	public WebElement txtUpdateContactDetails;
	
	@FindBy(xpath="//div[contains(text(),'Alternate Email')]") 
	public WebElement txtAlternateEmail;
	
	@FindBy(xpath="//h2[text()='Payment Profiles']") 
	public WebElement txtPaymentProfiles;
	
	@FindBy(xpath="//div[contains(text(),'A continuación se detallan tus cuentas secundarias.  Puedes tener un máximo de 20 cuentas secundarias, pero solo 10 pueden estar activas a la vez.')]") 
	public WebElement txtSpanish;

	@FindBy(xpath="//p[contains(text(),'Your member ID is the same as your AT&T email address and AT&T Yahoo! ID (example: jonsmith@att.net).')]") 
	public WebElement txtEmailDescription;
	
	@FindBy(xpath="//p[contains(text(),'An alternate email address allows you to recover the log-in information for this member if you forget the ID or password.')]") 
	public WebElement txtAlternateEmailDescription;

	@FindBy(xpath="//div[contains(text(),'@att.net')]") 
	public WebElement txtDomainATTNetAfterEmail;
	
	@FindBy(xpath="//label[contains(text(),'Paper or Paperless?')]") 
	public WebElement txtPaperPaperless;
	
	
	@FindBy(xpath="//table/tbody/tr/td[@class='BottomDotBorder'][contains(text(),'Pending')]") 
	public WebElement txtPendingAcceptance;
	
	@FindBy(xpath="//label[text()='Account Nickname']") 
	public WebElement txtAccountNickname;
	
	@FindBy(xpath="//label[text()='Account Nickname']//parent::div/span") 
	public WebElement txtAccountNicknameValue;
	
	@FindBy(xpath="//p//strong[contains(text(),'Bill alert notifications')]") 
	public WebElement txtBillAlertNotifications;
	
	@FindBy(xpath="//p//strong[contains(text(),'Bill threshold notification')]") 
	public WebElement txtBillThresholdNotifications;
	
	@FindBy(xpath="//p//strong[contains(text(),'Bill ready notification')]") 
	public WebElement txtBillReadyNotifications;
	
	@FindBy(xpath="//div[@class='section-divider']//h2[contains(text(),'Billing Contact Information')]") 
	public WebElement txtBillingContactInfoNew;
	
	@FindBy(xpath="//h3[contains(text(),'Payment received')]") 
	public WebElement txtPaymentReceived;
	
	
	@FindBy(xpath="//div[h3[contains(text(),'Bill ready notifications')]]/parent::div//label") 
	public WebElement txtBillReadyStatus;
	
	@FindBy(xpath="//div[h3[contains(text(),'Payment received')]]/parent::div//label") 
	public WebElement txtPaymentStatus;
	
	@FindBy(xpath="//div[h2[contains(text(),'Edit Bill Notifications')]]") 
	public WebElement txtEditBilling;
	
	@FindBy(xpath="//div/h3[contains(text(),'Bill ready notifications')]") 
	public WebElement txtBillReadyEdit;
	
	@FindBy(xpath="//p[contains(text(),'Contract Information')]") 
	public WebElement txtContractInfo;
	
	@FindBy(xpath="//p[contains(text(),'Primary User')]|//h2[contains(text(),'Primary User')]") 
	public WebElement txtPrimaryUser;
	
	@FindBy(xpath="//p[contains(text(),'Customer Communities Registration')]|//h2[contains(text(),'Customer Communities Registration')]") 
	public WebElement txtCustReg;
	
	@FindBy(xpath="//a[@title='Marketing Preferences']") 
	public WebElement txtMarketPref;
	
	@FindBy(xpath="//a[contains(text(),'View Account Level Preferences')]") 
	public WebElement txtViewPref;
	
	@FindBy(xpath="//p[contains(text(),'General Marketing ')]") 
	public WebElement txtGeneMarketing;
	
	@FindBy(xpath="//div[@class='groupHeader']/p[contains(text(),'Marketing Subscription Settings')]") 
	public WebElement txtSubscription;
	
	@FindBy(xpath="//p[contains(text(),'Access ID:')]") 
	public WebElement txtATTAccessID;
	
	@FindBy(xpath="//*contains(text(),'Status')") 
	public WebElement txtNoteStatus;
	
	@FindBy(xpath="//h1[contains(text(),'Profile – User Information')]") 
	public WebElement txtProfileUserInformation;
	
	@FindBy(xpath="//label[contains(text(),'Paper or Paperless?')]") 
	public WebElement txtPaper;
	
	@FindBy(xpath="//p[contains(text(),'Use these')]") 
	public WebElement txtNoteAutho;
	
	@FindBy(xpath="//h1[contains(text(),'Change Security Passcode')]") 
	public WebElement txtChangeSecurityPasscode;
	
	@FindBy(xpath="//h1[contains(text(),'Profile')]") 
	public WebElement txtProfileHeader;
	

	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//h1[contains(text(),'Edit AT&T Access ID1')] | //div[contains(text(),'Current Password')]")
	public WebElement lnkProfileActive;
	
	@FindBy(xpath="//label[contains(text(),'English')]//a[text()='Change setting']") 
	public WebElement lnkChangeSettingLang;
	
	@FindBy(xpath="//a[@title='Edit Account Bill']")
	public WebElement lnkChangeSettings;
	
	@FindBy(xpath="//a[@name='accountGroup']")
	public WebElement lnkEditAccName;
		
	@FindBy(xpath="//a[@name='accountBillContact' and contains(@title,'Edit Bill Contact')]")
	public WebElement lnkEditBillingContactInformation;
	
	@FindBy(xpath="//a[@title='Edit Bill Notify']")
	public WebElement lnkEditBillingNotifications;
	
	@FindBy(xpath="//a[@title='Change Passcode']")
	public WebElement lnkChangePasscode;
	
	@FindBy(xpath="//a[@class='wt_Body posStatic' or contains(@linkname,'Learn about updating payment')]")
	public WebElement lnkLearnAboutPaymentOptions;
	
	@FindBy(xpath="//a[@class='posStatic' and contains(@title,'Paper Bill')]")
	public WebElement lnkChangeSetting;
	
	@FindBy(xpath="//a[@class='posStatic' and contains(@title,'Add new user')]")
	public WebElement lnkAddNewUser;
	
	@FindBy(xpath="//a[@class='arrow' or contains(text(),'ack to')]")
	public WebElement lnkBackToAccountInfo;
	
	@FindBy(xpath="//div[@id='myImg']//a[contains(text(),'Cancel')]")
	public WebElement lnkCancelBillContInfo;
	
	@FindBy (xpath="//a[contains(text(),'Add new payment method')]")
	public WebElement lnkAddNewPaymentMethod;
	
	@FindBy (xpath="//a[@class='usm_click']")
	public WebElement lnkUSM;
	
	@FindBy (xpath="//*[contains(text(),'177038047308')]")
	public WebElement lnkCTN;
	
	@FindBy (xpath="//a[contains(text(),'Additional information')]")
	public WebElement lnkAdditionalInfo;
	
	@FindBy (xpath="//a[@href='addsubStart.myworld']")
	public WebElement lnkAddASubAccount;
	
	@FindBy (xpath="//span//a[@class='']")
	public WebElement lnkNoKeepMyCurrentEmail;
	
	@FindBy (xpath="//a[@href='SLIDMyProfileview.myworld']")
	public WebElement lnkAttAccessIdProfile;
	
	@FindBy (xpath="//a[@id='acctLink']")
	public WebElement lnkManageAccountAccess;
	
	@FindBy (xpath="//div[@id='paymentProfiles']//a")
	public WebElement lnkWhatsThisInPaymentProfile;
	
	@FindBy(xpath="//a[@title='Edit Billing Notifications']")
	public WebElement lnkEditBillingNotificationsNew;
	
	@FindBy(xpath="//a[contains(text(),'Manage Plenti')]")
	public WebElement lnkManagePlenti;

	@FindBy(xpath="//img[@alt='AT&T access ID profile']")
	public WebElement lnkAccessID;

	@FindBy(xpath="//a[contains(text(),'Customer Service Summary')]")
	public WebElement lnkCustomerServiceSummary;
	
	@FindBy(xpath="//a[contains(text(),'Remove Address')]")
	public WebElement lnkRemoveAddress;
	
	@FindBy(xpath="//a[contains(text(),'Add New Address')]")
	public WebElement lnkAddNewAddress;
	
	@FindBy(xpath="//a[@title='Paper Bill']")
	public WebElement lnkEditPaperBillOptions;
	
	@FindBy(xpath="//a[contains(text(),'Popular tasks')]")
	public WebElement lnkPopularTasks;
	
	@FindBy(xpath="//a[contains(text(),'Account users')]")
	public WebElement lnkAccountUsers;
	
	@FindBy(xpath="//a[contains(text(),'Contact info')]")
	public WebElement lnkContactInfo;
	
	@FindBy(xpath="//a[contains(text(),'Login Info')]")
	public WebElement lnkLoginInfo;
	
	@FindBy(xpath="//a[contains(text(),'Billing & payment options')]")
	public WebElement lnkBillingAndPaymentOptions;
	
	@FindBy(xpath="//a[contains(text(),'Communication preferences')]")
	public WebElement lnkCommunicationPreferences;
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//div[@id='myImg']/a") 
	public WebElement btnSaveChanges;
	
	@FindBy(xpath="//div[@id='myImg']//a[@name='Save Changes']") 
	public WebElement btnSaveChangesBillContInfo;
	
	@FindBy(id="save") 
	public WebElement btnSaveChangesBillNotification;
	
	@FindBy(xpath="//div[@class='tabcontent']//div[4]//span[contains(@class,'btnS btnS-slider-off')]") 
	public WebElement btnOff;
	
	@FindBy(id="ok")
	public WebElement btnOk;
	
	@FindBy(id="enbSubmitBtn") 
	public WebElement btnSaveChanges1;
	
	@FindBy(xpath="//a[@id='DNS']") 
	public WebElement btnManageSubAccountsTab;
	
	@FindBy(xpath="//input[@id='addsubAccept_0']") 
	public WebElement btnAccept;
	
	@FindBy(xpath="//a[@id='yesBtn']") 
	public WebElement btnYes;
	
	@FindBy(xpath="//a[@id='saveDis']/img") 
	public WebElement btnSaveBillingNotificationsDisable;
	
	@FindBy(xpath="//a[@id='save']/img") 
	public WebElement btnSaveBillingNotificationsActive;
	
	@FindBy(xpath="//img[@alt='Continue']") 
	public WebElement btnContinue;
	
	/*---------------------------
	 * frame objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//iframe[@class='cboxIframe']")
	public WebElement frmAddProfile;
	
//	@FindBy(xpath="//div[@class='radio']//input[@checked='checked'][@title='Checking / savings account']")
	@FindBy(xpath="//div[@class='radio']//*[@checked='checked' and contains(@title,'Checking / savings account')]")
	public WebElement rbtnCheckingSaving;
	
	@FindBy (xpath="//input[@title='Debit / credit card']")
	public WebElement rbtnDebitCredit;
	
	@FindBy (xpath="//img[contains(@src,'creditCards')][contains(@alt,'Acceptable debit / credit cards')]")
	public WebElement imgCreditCards;
	
	@FindBy (xpath="//a[@class='closeModal']")
	public WebElement lnkClose;
	
	
	/*--------------Objects added by onshore - Starts here ------------  */
	
	@FindBy(xpath="//li[@class='top-round']/a[@title='Account Information']") 							  //Added by Deepak 08/11/15
	public WebElement txtAccountInformation;
	
	@FindBy(xpath="//div[@class='group-title']/h2[contains (text(),'Account Information')]")              //Added by Deepak 08/11/15
	public WebElement txtAccountInformationHdr;
	
	@FindBy(xpath="//h2[contains(text(),'BTN') or contains(text(),'Wireline')]")
	public WebElement txtAccWirelineOrBTN;
	
	@FindBy(xpath="//li[@class='top-round']/a[@title='User Information']") 
	public WebElement txtUserInformation_1511;
	
	@FindBy(xpath="//a[@id='editLink']")										//Added by Deepak 08/12/15
	public WebElement lnkEditAccInfo;
	
	@FindBy(xpath="//a[@title='Edit Account Group']")							//Added by Deepak 08/12/15
	public WebElement lnkEditAccNames;
	
	
	@FindBy(xpath="//a[@id='editBillLink']")									//Added by Deepak 08/12/15
	public WebElement lnkEditBillingContactInfo;
	
	@FindBy(xpath="//a[@title='Edit Bill Notify']")								//Added by Deepak 08/12/15
	public WebElement lnkEditBillingNotification;
	
	@FindBy(xpath="//a[@title='Edit Billing Notifications']")
	public WebElement lnkEditBillingNotifications_wls;
	
	@FindBy(xpath="//h2[contains(text(),'Billing Notice Settings')]")			//Added by Deepak 08/114/15
	public WebElement txtBillingNoticeSettings;
	
	@FindBy(xpath="//a[@title='Bill Notify']")
	public WebElement lnkEditBillingNoticeSettings_wls;
	
	@FindBy(xpath="//label[contains(text(),'Bill Ready Notice Setting')]")    //Added by Deepak 08/14/15
	public WebElement txtBillReadyNoticeSettings;
	
	@FindBy(xpath="//label[contains(text(),'Bill Ready Delivery')]")    //Added by Deepak 08/14/15
	public WebElement txtBillReadyDelivery;
	
	@FindBy(xpath="//label[contains(text(),'Pay Bill Reminder Notice Setting')]")    //Added by Deepak 08/14/15
	public WebElement txtPayBillReminderNoticeSettings;
	
	@FindBy(xpath="//label[contains(text(),'Pay Bill Reminder Notice Delivery')]")    //Added by Deepak 08/14/15
	public WebElement txtPayBillReminderNoticeDelivery;
	
	@FindBy(xpath="//label[contains(text(),'Bill Threshold Amount Notice Setting')]")    //Added by Deepak 08/14/15
	public WebElement txtBillThresholdAmtNoticeSettings;
	
	@FindBy(xpath="//label[contains(text(),'Bill Threshold Amount Notice Delivery')]")    //Added by Deepak 08/14/15
	public WebElement txtBillThresholdAmtNoticeDelivery;
	
	
	@FindBy(xpath="//div[h3[contains(text(),'Bill threshold notification')]]")    //Added by Deepak 08/12/15
	public WebElement txtBillThresholdNotifications_1511;
	
	@FindBy(xpath="//p//strong[contains(text(),'Bill threshold notification')]")    //Added by Deepak 08/12/15
	public WebElement txtBillThresholdNotifications_wls;
	
	@FindBy(xpath="//p//strong[contains(text(),'Bill ready notification')]]") 
	public WebElement txtBillReadyNotifications_1511;
	
	@FindBy(xpath="//p//strong[contains(text(),'Bill ready notification by text message')]") 
	public WebElement txtBillReadyNotificationsByTextMsg;
	
	@FindBy(xpath="//div[@class='group-title']/h2[contains(text(),'Paper Bill Options')]")    //Added by Deepak 08/12/15
	public WebElement txtPaperBillOptions;
	
	@FindBy(xpath="//div[@class='group-title']/h2[contains(text(),'Service Address')]")      //Added by Deepak 08/12/15
	public WebElement txtServiceAddress;
	
	@FindBy(xpath="//div[@class='float-left w450']/h3[contains (text(),'Pay Bill Reminder Notification')]")      //Added by Deepak 08/12/15
	public WebElement txtPayBillReminderNotification;
	
	//@FindBy(xpath="//p[contains(text(),'@att.com')]")      //Added by Deepak 08/12/15
	@FindBy(xpath="//p[contains(text(),'Bill Ready and Payment Notifications')]//parent::div//parent::div//p[contains(text(),'@att.com')] | //p[contains(text(),'@att.com')] | //p[contains(text(),'Bill Ready and Payment Notifications')]//parent::div//parent::div//*[contains(text(),'@att.com')] | //p[contains(text(),'Bill Ready and Payment Notifications')]//parent::div//parent::div//p[contains(text(),'.com')] | //p[contains(text(),'Bill Ready and Payment Notifications')]//parent::div//parent::div//p[contains(text(),'@')]") //Edited by Clint (5th Jan-2016)
	public WebElement txtEmailIDInBillReady;
	
	@FindBy(xpath="//p[contains(text(),'Bill Ready and Payment Notifications')]//parent::div//parent::div//p[contains(text(),'@att.com')] | //p[contains(text(),'@att.com')] | //p[contains(text(),'Bill Ready and Payment Notifications')]//parent::div//parent::div//*[contains(text(),'@att.com')]")      //Added by Deepak 08/12/15
	public WebElement txtEmailIDInPaymentNotifications;
	
	/*--------------Objects added by onshore - Starts here ------------  */
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_Profile(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
