package com.OR.MyATTZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_Dashboard {
	WebDriver driver;

	@FindBy(id="STM0XX0YY1ZZMTD") 
	public WebElement elm_ATTAccessID;
	
	@FindBy(id="id_slidSearch") 
	public WebElement elm_ATTAccessIDTxtField;
	
	@FindBy(id="STM0XX1YY0ZZMTD")
	//@FindBy(xpath="//*[text()='Access ID']")
	public WebElement elm_AccessID;
	
	//@FindBy(id="STM0XX1YY0ZZMTD")
	@FindBy(xpath="//*[@id='STM0XX2YY0ZZTX' and contains(text(),'CTN')]") 
	public WebElement elm_WirelessCTN ;
	
	
	
	@FindBy(id="STM0XX0YY3ZZMTD") 
	public WebElement elm_Uverse;
	
	@FindBy(id="STM0XX2YY0ZZMTD") 
	public WebElement elm_UverseMID;
	
	@FindBy(id="STM0XX2YY1ZZMTD") 
	public WebElement elm_UverseBAN;
	
	@FindBy(id="STM0XX0YY4ZZMTD") 
	public WebElement elm_Wireline;
	
	@FindBy(id="STM0XX3YY0ZZMTD") 
	public WebElement elm_WirelineLoginID;
	
	//@FindBy(id="STM0XX3YY1ZZMTD")
	@FindBy(xpath="//*[text()='BTN']")
	public WebElement elm_WirelineBTN;
	
	@FindBy(id="STM0XX0YY5ZZMTD") 
	public WebElement elm_DSL;
	
	@FindBy(id="STM0XX4YY0ZZMTD") 
	public WebElement elm_DSLMID;
	
	@FindBy(id="STM0XX4YY1ZZMTD") 
	public WebElement elm_DSLWTN;
	
	@FindBy(id="STM0XX0YY6ZZMTD") 
	public WebElement elmWireline;
	
	@FindBy(id="STM0XX0YY7ZZMTD") 
	public WebElement elm_FreeEmailID;
	
	@FindBy(id="STM0XX5YY0ZZMTD") 
	public WebElement elm_MemberID;
		
	@FindBy(xpath="//a[@class='cingularLink']/img[contains(@src,'bt_web_impersonate')]") 
	public WebElement myATTWeb;
	
	@FindBy(xpath="//td[contains(text(),'FAN (Foundation Account Number)')]")
	public WebElement txtFAN;
	
	@FindBy(xpath="//form[@name='SlidIdSelect']") 
	public WebElement elm_SelectLoginIDForm;
	
	@FindBy(xpath="//div[@id='cbrMainPopup']") 
	public WebElement elm_CBRPopUp;
	
	@FindBy(xpath="//div[@id='cbrSmsPopup']") 
	public WebElement elm_CBRSMSPopUp;

	//@FindBy(id="STM0XX1YY1ZZTX")
	@FindBy(xpath="//*[text()='BAN']")
	public WebElement elm_WirelessBAN ;
	
	@FindBy(xpath="//*[text()='Wireless or Unified Account Number:']")
	public WebElement elm_WirelessOrUnifiedAccountNumber ;

	@FindBy(xpath="//*[contains(text(),'U-verse Member ID:')]")
	public WebElement elm_UverseMemberId ;

	@FindBy(id="STM0XX2YY1ZZTX")
	public WebElement elmUverseBAN ;

	@FindBy(xpath="//*[text()='U-verse or Unified Account Number:']")
	public WebElement elm_UverseOrUnifiedAccountNumber ; 

	@FindBy(xpath="//*[text()='Wireline BTN:']")
	public WebElement txtWirelineBTN ;
	
	@FindBy(id="STM0XX3YY0ZZMTD")
	public WebElement elmMemberId ;
	
	@FindBy(xpath="//td//font[contains(text(),'Locker')]")
	public WebElement elmLockerOption ;
	
	@FindBy(xpath="//td//font[(@id='STM0XX5YY2ZZTX') and text()='Email']")
	public WebElement elmEmailOptionUnderLocker ;
	
	@FindBy(xpath="//td//font[(@id='STM0XX5YY1ZZTX') and text()='CTN']")
	public WebElement elmCTNOptionUnderLocker ;
	
	@FindBy(xpath="//td//font[(@id='STM0XX5YY0ZZTX') and text()='Service ID']")
	public WebElement elmServiceIDOptionUnderLocker;
	
	@FindBy(xpath="//td//font[(@id='STM0XX5YY3ZZTX') and text()='Access ID']")
	public WebElement elmAccessIDOptionUnderLocker;
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[contains(text(),'successfully logged out')]")
	public WebElement txtMessageAfterLogout;
	
	@FindBy(xpath="//*[contains(text(),'Select Login ID')]")
	public WebElement txtSelectLoginIDHeading;
	
	@FindBy(xpath="//*[contains(@class,'title')]//*[text()='Make a Payment']")
	public WebElement txtMakePaymentHeading;
	
	@FindBy(xpath="//*[contains(text(),'Due Date')]")
	public WebElement txtDueDate;
	
	@FindBy(xpath="//*[text()='Payment Amount']")
	public WebElement txtPaymentAmount;
	
	@FindBy(xpath="//*[text()='Payment Date']")
	public WebElement txtPaymentDate;
	
	@FindBy(xpath="//*[text()='Payment Method']")
	public WebElement txtPaymentMethod;

	@FindBy(xpath="//*[contains(text(),'Mobile Number:')]")
	public WebElement txtMobileNumber;
	
	@FindBy(xpath="//strong[contains(text(),'Name:')]")
	public WebElement txtName;
	
	@FindBy(xpath="//strong[contains(text(),'AT&T ACCESS ID SUBSCRIBER MANAGEMENT')]")
	public WebElement txtSubscriberMgtPage;
	
	@FindBy(xpath="//td/font[text()='API Transaction Details']")
	public WebElement txtAPITransactionDetails;
	
	@FindBy(xpath="//b/font[text()='FILE: OLAM_ENV.properties']")
	public WebElement txtFileOLAMEnvPrperties;
	
	@FindBy(xpath="//td/b[text()='OEM Enabled:']")
	public WebElement txtOEMEnabled;
	

	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	@FindBy(xpath="id('fandetails')")
	public WebElement imgPopUp;
	
	@FindBy(xpath="//img[@src='/tbmcsr/_assets/images/myAT&T_header.png']")
	public WebElement imgMyATTZoneHeader;
	
	@FindBy(xpath="//div[@id='CbrValidUnchecked']")
	public WebElement imgDeviceValidatedUnchecked;
	
	@FindBy(xpath="//div[@id='CbrValidChecked']")
	public WebElement imgDeviceValidatedChecked;
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
//	@FindBys(value = { @FindBy(xpath="//*[@id='selectedSlid']") })
//	public WebElement radSelectSlid;
	
	@FindBy(xpath="(//input[@type='radio'])[1]")
	public WebElement radSelectSlid;

	
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	
	
	
	
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	
	
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	@FindBy(id="id_accountNumber")
	public WebElement edtWirelessBAN;
	
	@FindBy(xpath="//*[@name='inputFan']")
	public WebElement edtFAN;
	
	@FindBy(xpath="//input[@id='dslMemberIDSearch']")
	public WebElement edtDSLMemberID;
	
	@FindBy(id="id_mobileNumber")
	public WebElement edtWirelessCTN;
	
	@FindBy(xpath="//input[@id='id_uvMemberID']")
	public WebElement edtUverseMID;

	@FindBy(xpath="//input[@id='id_uvAccountNumber']")
	public WebElement edtUverseBAN;

	@FindBy(xpath="//input[@id='id_wlBTN']")
	public WebElement edtWirelineBTN;
	
	@FindBy(xpath="//input[@name='attUID']") 
	public WebElement edtATT_UID;
	
	@FindBy(xpath="//input[@name='startDate']") 
	public WebElement edtStartDate;
	
	@FindBy(xpath="//input[@name='endDate']") 
	public WebElement edtEndDate;
	
	@FindBy(xpath="//input[@name='lockerEmail']") 
	public WebElement edtLockerEmail;
	
	@FindBy(xpath="//input[contains(@id,'slidSearch')]") 
	public WebElement edtSlidSearch;

	
	/*---------------------------
	 *  Text objects add below
	 *---------------------------*/
	@FindBy(id="STM0XX0YY2ZZMTD")
	public WebElement txtWireless ;
	
	@FindBy(id="STM0XX1YY1ZZMTD")
	public WebElement txtWirelessBAN ;
	
	@FindBy(xpath="//td[contains(text(),'Welcome Todd')]")
	public WebElement txtWelcome;
	
	@FindBy(xpath="//*[text()='AT&T Access ID:']")
	public WebElement txtATTaccessId;
	
	@FindBy(xpath="//h1[contains(text(),'myAT&T Overview')]")
	public WebElement txtAccountOverview_Mobile;
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[id('tool_tip') and contains(text(),'Data input rules')]")
	public WebElement lnkInputRules;

	@FindBy(xpath="//a[contains(text(),'View Details')]")
	public WebElement lnkViewDetails;
	
	@FindBy(xpath="//a[contains(@id,'Accounts')]")
	public WebElement lnkAccounts;
	
	@FindBy(xpath="//a[text()='Error Messages']")
	public WebElement lnkErrors;  
	
	@FindBy(xpath="//a[text()='Sales Support']")
	public WebElement lnkSalesSupport; 
	
	@FindBy(xpath="//a[contains(text(),'Registration')]")
	public WebElement lnkRegistration; 
	
	@FindBy(xpath="//a[text()='About Us']")
	public WebElement lnkAboutUs;
	
	@FindBy(xpath="//a[text()='debugger']")
	public WebElement lnkDebugger;
	
	@FindBy(xpath="//a[text()='Fraud']")
	public WebElement lnkFraud;
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//td/input[@type='image']") 
	public WebElement btnSearch;
	
	@FindBy(id="continue") 
	public WebElement btnContinue;
	
	@FindBy(xpath="//a[@id='PN-MSRT']") 
	public WebElement btnMobileShareComparison;
	
	@FindBy(xpath="//img[contains(@alt,'Logout')]") 
	public WebElement btnLogout;
	
	@FindBy(xpath="//*[@target='_top'][contains(@href,'searchrep')]") 
	public WebElement btnAgentActivity;
	
	@FindBy(xpath="//input[@src='/tbmcsr/_assets/images/bt_search.png']") 
	public WebElement btnSearchDSLMemberID;
	
	@FindBy(xpath="//img[@src='/tbmcsr/_assets/images/bt_mobile_impersonate_large.png']") 
	public WebElement btnMirrorMyATTMobileApp;
	
	@FindBy(xpath="//img[@src='/tbmcsr/_assets/images/mirror_full_web_ul.png']") 
	public WebElement btnMirrorMyATTWeb;
	
	@FindBy(xpath="//img[@src='/tbmcsr/_assets/images/bt_sub_mgmt.png']") 
	public WebElement btnSubscriberManagement;
	
	@FindBy(xpath="//img[@src='/tbmcsr/_assets/images/bt_cust_act.png']") 
	public WebElement btnCustomerActivity;
	
	@FindBy(xpath="//div[@id='SmsMobileBlue']//img[@src='_assets/images/bt_send_sms.png']") 
	public WebElement btnSendSMSEnabled;
	
	@FindBy(xpath="//div[@id='SmsMobileGrey']//img[@src='_assets/images/bt_send_sms_grey.png']") 
	public WebElement btnSendSMSDisabled;
	
	@FindBy(xpath="//div[@id='cbrSmsPopup']//img[@src='_assets/images/bt_cancel.png']") 
	public WebElement btnCancelSMSPopup;
	
	@FindBy(xpath="//div[@id='cbrSmsPopup']//img[@src='_assets/images/bt_ok.png']") 
	public WebElement btnOKSMSPopup;
	
	@FindBy(xpath="//a[@linkname='Make a Payment']") 
	public WebElement btnMakeApayment;
	
	@FindBy(xpath="//a[contains(@id,'Profile')]") 
	public WebElement btnProfile;
	
	@FindBy(xpath="//input[contains(@src,'search')]") 
	public WebElement btnSearchOnAgentActivitySection; 
	
	@FindBy(xpath="//input[@title='Cancel']") 
	public WebElement btnCancel;  
	
	@FindBy(xpath="//img[@name='customerSearch']") 
	public WebElement btnReturnToCustomerSearch;  
	
	@FindBy(xpath="//input[contains(@src,'submit')]") 
	public WebElement btnSubmitOnCustomerActivity;
	
	@FindBy(xpath="//button[@id='iconMenu']") 
	public WebElement btnGlobalNavMenu_Mobile; 
	
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	
	
	
	
	public OR_Dashboard(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
