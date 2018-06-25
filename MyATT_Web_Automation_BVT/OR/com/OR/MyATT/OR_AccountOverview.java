package com.OR.MyATT;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_AccountOverview {

	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	 @FindBy(xpath="//img[contains(@src,'uverse_internet')]")
	 public WebElement imgUverseInternetIcon;
	 
	 @FindBy(xpath="//img[@id='tvImage'] | //img[contains(@src,'icon_uverse_tv')]")
	 public WebElement imgUverseTVIcon;
	 
	 @FindBy (xpath="//img[contains(@src,'video-camera-icon')]")
	 public WebElement imgVideoCamera;

	 @FindBy (xpath="//div[@id='promoContainer']")
	 public WebElement imgPromotions;
	 
	@FindBy(xpath="//span[@id='alertButtonLabel']")
	public WebElement imgAlertIcon;
	
	@FindBy(xpath="//span[@id='webTip1']")
	public WebElement imgAlertIconText;
	
	@FindBy(xpath="//div[@class='dashboardTabContent']//*[contains(text(),'Account Suspended')]")
	public WebElement txtSuspendAccountText;
	
	@FindBy(xpath="//div[@class='dashboardTabContent']//child::img[@src='/olam/images/brand30/alert_icon_y_suspended.png']/parent::div/img[1]")
	public WebElement imgSuspendPlanImage;
	
	@FindBy(xpath="//div[@class='dashboardTabContent']//child::img[@src='/olam/images/brand30/alert_icon_y_suspended.png'] | //div[@class='dashboardTabContent']//*[contains(text(),'Account Suspended')]")
	public WebElement imgSuspendAlertImage;
	
	@FindBy(xpath="//img[contains(@alt,'suspended')]")
	public WebElement imgSuspendImage;
	
	@FindBy(xpath="//div[@class='meter-container-5']")
	public WebElement imgBillingCycleBar;
	
	@FindBy(xpath="//div[contains(@class,'meter-container')]")
	public WebElement imgMeterbars;
	
	@FindBy(xpath="//div[contains(@class,'usageDiv')]")
	public WebElement imgUsageSummarySection;
	
	@FindBy(xpath="//span[normalize-space(text())='Data']//parent::div/parent::div | //a[contains(@class,'usageBar')]/div")
	public WebElement imgDataUsageBar;
	
	@FindBy(xpath="//span[normalize-space(text())='Data']//parent::div/parent::div//a/img[@src='/olam/images/brand30/iconError.png'] | //span[contains(text(),'Data')]//parent::div//parent::div/div[2]//img")
	public WebElement imgDataUsageBarOverageAlert;
	
	@FindBy(xpath="//span[contains(text(),'Text')]//parent::div/parent::div//a/img[@src='/olam/images/brand30/iconError.png'] | //span[contains(text(),'Text')]//parent::div//parent::div/div[2]//img")
	public WebElement imgTextUsageBarOverageAlert;
	
	@FindBy(xpath="//span[normalize-space(text())='Talk']//parent::div/parent::div//a/img[@src='/olam/images/brand30/iconError.png']")
	public WebElement imgTalkUsageBarOverageAlert;
	
	@FindBy(xpath="//span[normalize-space(text())='Talk']//parent::div/parent::div")
	public WebElement imgTalkUsageBar;
	
	@FindBy(xpath="//span[contains(text(),'Text')]//parent::div/parent::div")
	public WebElement imgTextUsageBar;
	
	@FindBy(xpath="//span/strong[contains(text(),'Shared Data Usage')]//parent::span//parent::div/parent::div")
	public WebElement imgSharedDataUsage;
	
	@FindBy(xpath="//div[@class='overagePack625']")
	public WebElement imgDataOveragePackSection;
	
	@FindBy(xpath="//div[@class='usage-meter-table']//span[contains(text(),'Data')]//parent::div//parent::div//div[contains(@class,'meter-container')]//div[@style='width:100%']")
	public WebElement imgDataOverage100percentRedBar;
	
	@FindBy(xpath="//img[contains(@src,'smallcomp')]")
	public WebElement imgPaperlessBillingMonitor;
	
	@FindBy(xpath="//img[@alt='Voluntarily suspended alert']")
	public WebElement imgSuspendAlert;
	
	@FindBy(xpath="//img[@alt='alert Icon']")
	public WebElement imgRedAlertIcon;
	
	@FindBy(xpath="//a[contains(text(),'Go to Locker')]//parent::div//span[contains(@class,'newWindow')]") // added by Clint
	public WebElement ImgGoToLockerWindowIcon;
	
	@FindBy(xpath="//img[contains(@alt,'Phone')]")
	public WebElement imgPhoneInFrm;
	
	@FindBy(xpath="//img[contains(@alt,'Phone')]")
	public WebElement imgOn4GLTE;
	
	@FindBy(xpath="//*[contains(@class,'meter') and contains(@class,'grey')]")
	public WebElement imgGreyBarForTimeLeft;
	
	@FindBy(xpath="//img[@alt='close']")
	public WebElement imgCloseAlert;
	
	@FindBy(xpath="//img[contains(@src,'jaguar')]")
	public WebElement imgConnectedCar;
	
	
	@FindBy(xpath="//img[@alt='Phone 1']")
	public WebElement imgUnknownDevice;
	
	@FindBy(xpath="//div[@id='phoneItemContainer']//a[contains(@id,'modalimage')]//img | //div[@id='phoneItemContainer']//img | //div[@id='phoneItemContainer']//img[@alt!='Add Phone']")
	public WebElement imgDeviceImage;
	
	@FindBy(xpath="//div[@id='phoneItemContainer']//img[@alt!='Add Phone']")
	public WebElement imgValidDeviceImage;
	
	@FindBy(xpath="//div[@id='phoneItemContainer']//img[@alt='Add Phone']")
	public WebElement imgDeviceImageAddPhone;
	
    @FindBy(xpath="//div[@class='meter-value warning']")
    public WebElement imgUsageMeter;
    
	@FindBy(xpath="//div[contains(@class,'modalContent')]//img[@alt!='Add Phone']")
	public WebElement imgDeviceImageUnderViewDetailsModal;
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/


	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	 
	 @FindBy(xpath="//div[@class='alert-dropdown-mid']") 
	 public WebElement lstAlertDropDown;
	 
	@FindBy(id="ge5p_z1-language-drop-down-link")
	public WebElement lstLanguage;
	
	@FindBy(xpath="//ul[@id='MainTab']/li")
	public WebElement lstMyPlans;
	
	@FindBy(xpath="//div[@id='viewUsage']")
	public WebElement lstmobilepurchasetalkdata;

	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/


	/*---------------------------
	 * Card objects add below - added by Monica - 26th June 2017
	 *---------------------------*/
	@FindBy(xpath = "//h2[contains(text(),'My U-verse TV')]")
	public WebElement crdMyUverseTV;

	@FindBy(xpath = "//h2[contains(text(),'My Internet')]")
	public WebElement crdMyInternet;

	@FindBy(xpath = "//h2[contains(text(),'My digital home phone')]")
	public WebElement crdMyDigitalHomephone;

	@FindBy(xpath = "//h3[contains(text(),'My plan')]")
	public WebElement crdMyPlan;

	@FindBy(xpath = "//h3[contains(text(),' Watch TV ')]")
	public WebElement crdWatchTV;

	@FindBy(xpath = "(//h3[contains(text(),'Get the most from your TV')])[1]")
	public WebElement crdGetTheMostFromYourTV;

	@FindBy(xpath = "(//h3[contains(text(),'Support')])[1]")
	public WebElement crdSupport;
	
	@FindBy(xpath = "//h3[contains(text(),'My usage')]")
	public WebElement crdMyUsage;

	@FindBy(xpath = "(//h2[contains(text(),'My wireless')])[1]")
	public WebElement crdMyWireless;

	@FindBy(xpath = "//h3[contains(text(),'My devices')]")
	public WebElement crdMyDevicesAndFeatures;

	@FindBy(xpath = "//h2[contains(text(),'My home phone')]")
	public WebElement crdMyHomePhone;
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//h2[contains(text(),'Welcome')]") 
	public WebElement txtWelcome;
		
	@FindBy(xpath="//*[contains(text(),'AT&T Message')]") 
	public WebElement txtATTMessage;
	
	@FindBy(xpath="//div[contains(@class,'msg box')] | span[contains(text(),'login with primary')]")
	public WebElement txtNotLoggedInWithPrimaryAccMsg;

	@FindBy(xpath="//*[contains(text(),'UpgradeCountdown')]")
	public WebElement txtUpgradeCountdown;
	
	@FindBy(xpath="//*[contains(text(),'OfferEligibility')]")
	public WebElement txtOfferDates;
	
	@FindBy(xpath="//span[@class='colorBlack PadRight10 font12' and contains(text(),'qayls')]")
	public WebElement txtEmailIDLinkedToSlid;
	
	@FindBy (xpath="//h2[contains(text(),'DIRECTV | XTRA')]")
	public WebElement txtDTVInformation ;

	@FindBy(xpath="//strong[contains(text(),'Upgrade Eligibility')]")
	public WebElement txtUpgradeEligibilityAlert;
	
	@FindBy(xpath="//p[3][@class='botMar0']")
	public WebElement txtEligibleForUpgrade;
	
	@FindBy(xpath="//h2[@id='planName']")
	public WebElement txtUverseTVPlan;
	
	@FindBy(xpath="	//p[@class='font12  colorBlack botMar15'][contains(text(),'Pay per view')]")
	public WebElement txtPayPerView;

	@FindBy(xpath="	//p[@class='font12  colorBlack botMar15'][contains(text(),'Video on Demand')]")
	public WebElement txtVideoOnDemand;
	
	@FindBy(xpath="	//p[@class='font12  colorBlack botMar15'][contains(text(),'Total')]")
	public WebElement txtTotal;
	
	@FindBy(xpath="//strong[contains(text(),'Account Suspended')]")
	public WebElement txtAccountSuspended;
	
	@FindBy(xpath="//span[@class='font11 colorGrey' and contains(text(),'Last')]")
	public WebElement txtLastLogin;

	@FindBy(xpath="//span[@class='font14 plan fontWeightBoldForce' and contains(text(),'Data')]")
	public WebElement txtDataLabel;

	@FindBy(xpath="//span[@class='meter-value-10 normal']")
	public WebElement txtUsageMeter;
	
	@FindBy(xpath="//span[@class='float-left MarTop18 now-rap font12 MarLeftNeg10 colorBlack' and contains(text(),'left')]")
	public WebElement txtDataLeft;

	@FindBy(xpath="//span[@class='float-left MarTop18 now-rap font12 MarLeftNeg10 colorBlack' and contains(text(),'over')]")
	public WebElement txtDataOver;
	
	@FindBy(xpath="//div[@id='alertList']//div[@class='alert-warning']//span[contains(text(),'Mobile Number Suspended')]")
	public WebElement txtAlertMobileNoSuspended;
	
	@FindBy(xpath="//li[@id='ddShortcutMenu1']")
	public WebElement txtViewAndPayBill;
	
	@FindBy(xpath="//span[contains(text(),'Manage my plan')] | //strong[contains(text(),'Manage My Plan & Device')]")
	public WebElement txtManageMyPlan;
	
	@FindBy(xpath="//li[@id='ddShortcutFlyoutMenu4']/div/span[contains(text(),'U-verse Internet')]")
	public WebElement txtUverseInternet;
		
	@FindBy(xpath="//li[@id='ddShortcutMenu4']//span[contains(text(),'Manage my profile')]")
	public WebElement txtManageMyProfile;
	
	@FindBy(xpath="//span[text()='Overage data']")
	public WebElement txtOverageData;
	
	@FindBy(xpath="//strong[contains(text(),'0.1')]")
	public WebElement txtOverageAmount;
	
	@FindBy(xpath="//div[contains(text(),'Overage data')]")
	public WebElement txtOverageDataGrace;

	@FindBy(xpath="//li[@id='ddShortcutMenu5']//span[contains(text(),'Get help & support')]")
	public WebElement txtGetHelpAndSupport;
	
	@FindBy(xpath="//li[@id='ddShortcutMenu6']//span[contains(text(),'Shop AT&T')]")
	public WebElement txtShopAtt;
	
	@FindBy(xpath="//li[@id='ddShortcutMenu6']/div/span[contains(text(),'Shop AT&T')]")
	public WebElement txtShopAttUnderIWantTo;
	
	@FindBy(xpath="//h4[@id='menu-head-Billing']")
	public WebElement txtBilling;
	
	@FindBy(xpath = "//h2[contains(text(),'OnStar')]")
	public WebElement txtOnStarPlanHeaderInServiceSummary;
	
	@FindBy(xpath="//h4[@id='menu-head-Payments']")
	public WebElement txtPayments;
	
	@FindBy(xpath="//h4[@id='menu-head-Voice'] | //h2[contains(text(),'Voice')]")
	public WebElement txtVoice;
	
	@FindBy(xpath="//h4[@id='menu-head-Profile']")
	public WebElement txtProfile;
	
	@FindBy(xpath="//h4[@id='menu-head-Service']")
	public WebElement txtService;
	
	@FindBy(xpath="//h4[@id='menu-head-Setup']")
	public WebElement txtSetup;
	
	@FindBy(xpath="//h4[@id='menu-head-Shop']")
	public WebElement txtShop;
	
	@FindBy(xpath="//*[contains(text(),'Current Balance')] | //*[contains(text(),'total balance')]")
	public WebElement txtTotalBalance;
	
	@FindBy(xpath="//div[@class='lineHeight30 right float-left w195']/span")
	public WebElement txtBalanceAmount;
	
	@FindBy(xpath="//div[@class='Marbot5 ']")
	public WebElement txtDueDate;
	
	@FindBy(xpath="//div[@class='msg box vmid PadTop18 botMar0 focusable']/p[contains(text(),'to see all the features')]")
	public WebElement txtMsgLimitedAccess;
	
	@FindBy(id="planName")
	public WebElement txtPlanName;
	
	@FindBy(xpath="//a[contains(@class,'current')]//span[contains(@class,'BoldForce')]")
	public WebElement txtPlanName1;

	@FindBy(xpath="//div[@id='search-section']/h1['AT&T Internet Support']")
	public WebElement txtSupportInternetHeader;
	
	@FindBy(xpath="//span[@id='alertButtonCount']")
	public WebElement txtAlertButtonCount;

	@FindBy(xpath="//div[@class='alert-title']")
	public WebElement txtAlertTitle;

	@FindBy(xpath="//p[contains(text(),'Your Card Has Expired')]")
	public WebElement txtCardExpiredMessage;
	
	@FindBy(xpath="//p[contains(text(),'Your Card Is About to Expire')]|//span[contains(text(),'Your Card Is About to Expire')] | //*[contains(text(),'Your Card Is About to Expire')]")
	public WebElement txtCardAboutToExpireMessage;
	
	@FindBy(xpath="//*[@class='section-title']//*[@id='planName']")
	public WebElement txtPlanNameInHeaderOfServiceSummery;
	
	@FindBy(xpath="//h2[text()='Account Overview']")
	public WebElement txtAccountOverview;
	
	@FindBy(xpath="//h1[contains(text(),'Account Overview')]")
	public WebElement txtAccountOverviewHeading;
	
	@FindBy(xpath="//span[contains(@class,'font30imp fontWeightBoldForce') and contains(text(),'$')]")
	public WebElement txtCurrentBalanceAmt;
	
	@FindBy(xpath="//span[contains(@class,'newOrange') and contains(text(),'$')]")
	public WebElement txtBalanceAmtInOrangeColor;
	
	@FindBy(xpath="//span[contains(@class,'newRed') and contains(text(),'$')]")
	public WebElement txtBalanceAmtInRedColor;
	
	@FindBy(xpath="//span[contains(@class,'newGreen') and contains(text(),'$')]|//span[contains(@class,'Green') and contains(text(),'$')]")
	public WebElement txtBalanceAmtInGreenColor;
	
	@FindBy(xpath="//span[@class='newRed font30imp fontWeightBoldForce']")
	public WebElement txtPastBalanceAmt;
	
	@FindBy(xpath="//span[contains(@class,'newGreen')]")
	public WebElement txtCurrentBalanceAmtGreen;
	
	@FindBy(xpath="//span[@class='font14 newGrey' and contains(text(),'as of')]")
	public WebElement txtAsOf;
	
	@FindBy(xpath="//p[contains(text(),'is pending approval by the account owner')]")
	public WebElement txtYourAccessPending;
	
	@FindBy(xpath="//h2[contains(text(),'My Services')]")
	public WebElement txtMyServices;
	
	@FindBy(xpath="//h2[contains(text(),'total balance')] | //span[contains(text(),'total balance')]")
	public WebElement txtCurrentBalance;
	
	@FindBy(xpath="//span[@class='colorGreen font24 PadTop5']")
	public WebElement txtBalance;
	
	@FindBy(xpath="//div[@id='myBillSectionDataDiv']")
	public WebElement txtMyBillSection;
	
	@FindBy(xpath="//strong[contains(text(),'Billing Period:')] | //strong[contains(text(),'Billing Cycle:')]")
	public WebElement txtBillingPeriod;
	
	@FindBy(xpath="//strong[contains(text(),'Billing Period:')]/parent::div/span[contains(@class,'left')] | //div[contains(text(),'day(s) left')] | //strong[contains(text(),'Billing Period:')] | //strong[contains(text(),'Billing Cycle:')]")
	public WebElement txtDaysLeft;
	
	@FindBy(xpath="//ul[@id='alertPage_1']//li[@class='noFloat BotSolidBorder']//span[text()='Recent Mobile ']|//a[@alt='View unbilled mobile purchases']")
	public WebElement txtAlertsUnbilledPurchases;
	
	@FindBy(xpath="//li[@class='noFloat BotSolidBorder']//div[@class='alert-upgrade']//p[@class='botMar0'][2]")
	public WebElement txtAlertstextanddesciption;
	
	@FindBy(xpath="//h2[@class='font18Force']")
	public WebElement txtAlerts;
	
	@FindBy(xpath="//h3[text()='My Profile']")
	public WebElement txtMyProfileTerNav;
	
	@FindBy(xpath="//h3[text()='Passwords']")
	public WebElement txtPasswordsTerNav;
	
	@FindBy(xpath="//h3[text()='Linked Accounts']")
	public WebElement txtLinkedAccountsTerNav;
	
	@FindBy(xpath="//h2[contains(text(),'Your total credit balance')]")
	public WebElement txtCurrentBalanceNew;
	
	@FindBy(xpath="//span[contains(@class,'newGreen')][contains(text(),'CR')]|//span[contains(@class,'Green')]")
	public WebElement txtCRinCurrentBalance;
	
	@FindBy(xpath="//*[contains(text(),'Domestic')]")
	public WebElement txtDomesticVoice;
	
	
	@FindBy(xpath="//div[@class='colorBlack font12 fontWeightBoldForce Marbot5'][contains(text(),'No Action Necessary')]|//div[@class='colorBlack font12 fontWeightBoldForce Marbot5'][contains(text(),'No')]")
	public WebElement txtNoActionNecessary;
	
	@FindBy(xpath="//p[@class='PadLeft25 w400']")
	public WebElement txtmessageUnderCreditBalance;
	
	@FindBy(xpath="//span[@class='minutes-used']")
	public WebElement txtDataOverageMinutesUsed;
	
	@FindBy(xpath="//span[@class='total-minutes']")
	public WebElement txtDataOverageTotalMinutes;
	
	@FindBy(xpath="//span[@class='total-minutes']//parent::div")
	public WebElement txtDataOverageUsage;
	
	@FindBy(xpath="//div[@class='usage-meter-table']//span[contains(text(),'Data')]//parent::div//parent::div//parent::div//div[2]/span")
	public WebElement txtDataUsageMBleft;
	
	@FindBy(xpath="//div[@id='alertSection']")
	public WebElement txtAlertSection;

	@FindBy(xpath="//div[contains(@class,'msg box vmid')]")
	public WebElement txtMessageBox;
		
	@FindBy(xpath="//*[contains(text(),'New Service Order')] | //div[@class='msg box']/p[contains(text(),'U-verse')]")
	public WebElement txtPreInstallBanner;
	
	@FindBy(xpath="//*[@id='main-container']//div[contains(text(),'activation date and time')]")
	public WebElement txtPreInstallBannerAppointmentactivity;

	@FindBy(xpath="//div/strong[contains(text(),'service will activate on')]")
	public WebElement txtPreInstallBannerAppointmentDate;
	
	@FindBy(xpath="//span[contains(text(),'past due')]")
	public WebElement txtPastDue;
	
	@FindBy(xpath="//p[@class='colorBlack botMar5 MarTop5 fontWeightBoldForce font13']//strong[contains(text(),'Account Suspended')]")
	public WebElement txtAccountSuspendedAlert;
	
	@FindBy(xpath="//p[@class='botMar0'][contains(text(),'You chose to suspend service for this account.')]")
	public WebElement txtAlertDescription;
	
	@FindBy(xpath="//p[contains(text(),'Rerate Pending')]")
	public WebElement txtRerate;
	
	@FindBy(xpath="//span[contains(text(),'Quick tools')]")
	public WebElement txtQuickTools;
	
	@FindBy(xpath="//span[contains(text(),'Activity since last bill')]")
	public WebElement txtLastBillActivity;
	
	@FindBy(xpath="//span[contains(text(),'Activity since last bill')]//parent::div//parent::div/div[2]")
	public WebElement txtLastBillActivityAmount;
	
	@FindBy(xpath="//span[contains(text(),'welcome back')] | //h1[contains(text(),'Welcome to')]")
	public WebElement txtWelComeBack;
	
	@FindBy(xpath="//span[contains(text(),'Most recent bill')]")
	public WebElement txtMostRecentBill;
	
	@FindBy(xpath="//span[contains(text(),'Most recent bill')]//parent::div//parent::div/div/span[contains(text(),'$')]")
	public WebElement txtMostRecentBillAmount;
	
	@FindBy(xpath="//div[contains(@class,'float-left MarTop24 now-rap font16')] | //div[contains(@class,'meterwrapper PadBot0 padTop15')]/div[2]")
	public WebElement txtLeftDataUsageValue;
	
	@FindBy(xpath="//div[contains(text(),'including employer contribution')]")
	public WebElement txtEmployerContributionNote;
		
	@FindBy(xpath="//p[contains(text(),'transfer billing responsibility will expire in')]")
	public WebElement txtTOBRAlertExpire;
		
	@FindBy(xpath="//div[@class='MarLeft10 noBorderFocus']//p[contains(text(),'77')]|//div[@class='MarLeft10']//p[contains(text(),'-')]")
	public WebElement txtTOBRAlertCTN;
	
	@FindBy(xpath="//p[text()='Mobile Number Suspended']")
	public WebElement txtMobileNoSuspended;
	
	@FindBy(xpath="//p[contains(text(),'suspend your mobile service')]")
	public WebElement txtYouHave;
	
	@FindBy(xpath="//p[contains(text(),'Service canceled')]")
	public WebElement txtSerCancelled;
	
	@FindBy(xpath="//div[@id='alertList']//*[contains(text(),'Appointment Reminder')]")
	public WebElement txtAppointmentReminderAlert;
	
	@FindBy(xpath="//div[@id='alertList']//*[contains(text(),'Appointment Reminder')]//parent::p//parent::div//*[contains(text(),'U-verse #')]")
	public WebElement txtAppointmentReminderUverseBAN;
	
	@FindBy(xpath="//div[@id='alertList']//*[contains(text(),'Appointment Reminder')]//parent::p//parent::div//p[contains(text(),'scheduled for')]")
	public WebElement txtAppointmentReminderAlertDetails;
	
	@FindBy(xpath="//div[contains(text(),'Service changed on') or contains(text(),'activated on on')]")
	public WebElement txtMidCycleMessage;
	
	@FindBy(xpath="//span[text()='Payment Unsuccessful'] | //span[contains(text(),'Payment Pending')]")
	public WebElement txtPaymentUnsucessful;
	
	@FindBy(xpath="//div[contains(text(),'Please pay your balance')]")
	public WebElement txtCancellationMsg;

	@FindBy(xpath="//div[contains(text(),'Your service is canceled')]")
	public WebElement txtCancellationDate;
	
	@FindBy(xpath="//strong[contains(text(),'Billing Period:')]//ancestor::div/following-sibling::div//div[contains(@class,'Grey')]")
	public WebElement txtVoiceCTN;

	@FindBy(xpath="//p[contains(text(),'past due')]")
	public WebElement txtPastDueMsg;
	
	@FindBy(xpath="//*[contains(text(),'Rollover Data')]") 
	public WebElement txtRollOverdata;

	@FindBy(xpath="//p[@id='msg1'][contains(text(),'shared data allowance includes')]")
	public WebElement txtToolTipMsg;
	
	@FindBy(xpath="//div[contains(@class,'usageContainer')]")
	public WebElement txtServiceSummarySection;
	
	@FindBy(xpath="//div[@class='tip-white']//p[contains(text(),'Overage charges')]")
	public WebElement txtOverageChargesHover;
	
	@FindBy(xpath="//span[@id='lockerPlan']")
	public WebElement txtLockerPlanAllotedData;
	
	@FindBy(xpath="//strong[contains(text(),'Available storage')] | //strong[contains(text(),'Stored Data')]")
	public WebElement txtLockerAvailableStorage;
	
	@FindBy(xpath="//div[contains(@class,'meter-container')]")
	public WebElement txtLockerMeterContainer;
	
	@FindBy(xpath="//div[contains(@class,'meter-container')]//parent::div//strong[contains(text(),'GB') or contains(text(),'MB') or contains(text(),'KB')]")
	public WebElement txtLockerUnusedDataLeft;
	
	@FindBy(xpath="//a[contains(@dtmreportingattrs,'CBRCTN') and @alt='Update now']//parent::p//parent::div")
	public WebElement txtAlertForMissingCBR_CTN;
	
	@FindBy(xpath="//h1[contains(text(),'Hey there, DIRECTV customer')]")
	public WebElement txtDirectTVCustomer;

	@FindBy(xpath="//div[@id='primary-content']/p[1]")
	public WebElement txtPrimaryContent;

	@FindBy(xpath="//p/strong[contains(text(),'Want to manage your AT&T account(s)?')]")
	public WebElement txtWantToManage;
	
	@FindBy(xpath="//strong[contains(text(),'left')]")
	public WebElement txtTimeLeft;

	@FindBy(xpath="//strong[contains(text(),'left')]//parent::strong//parent::div//span[@class='time-left']")
	public WebElement txtNumberOfDaysLeft;

	@FindBy(xpath="//a[contains(text(),'View all usage')]//parent::div//parent::div//h4")
	public WebElement txtUsernameAndCTNForOnStarInServiceSummary;
	
	@FindBy(xpath="//span[contains(text(),'This trial / prepaid data plan')]")
	public WebElement txtNoteForTrialOrPrepaidPlan;

	@FindBy(xpath="//strong[contains(text(),'Plan Change')]")
	public WebElement txtPlanChangeAlert;
	
	@FindBy(xpath="//h1[contains(text(),'My Wireless Service')]")
	public WebElement txtMyWirelessService;
	
	@FindBy(xpath="//h2[contains(text(),'Welcome')]/span")
	public WebElement txtAccName;
	
	@FindBy(xpath="//div[contains(@class,'alert')]//p[contains(text(),'primary') and contains(text(),'access')]")
	public WebElement txtPrimaryUserAlert;
	
	@FindBy(xpath="//img[contains(@alt,'Phone')]//ancestor::div//div[contains(text(),'Phone')]")
	public WebElement txtModelMakeOnLinkAccountPage;
	
	@FindBy(xpath="//div[contains(@class,'Bold')]")
	public WebElement txtCTNOnLinkAccountPage;
	
	@FindBy(xpath="//p[contains(text(),'Update wireless contact number')]")
	public WebElement txtUpdateWirelessContactNumberAlert;
	
	@FindBy(xpath="//h2[contains(text(),'Ways to earn Plenti points at AT&T')]")
	public WebElement txtWaysToEarnPlentiPoints;
	
	@FindBy(xpath="//p[contains(text(),'Number Suspended')]")
	public WebElement txtSuspendedNumberAlert;
	
	@FindBy(xpath="//div[@id='ftCtn']")
	public WebElement txtCTNOnOverviewPage;
	
	@FindBy(xpath="//div[@id='phoneItemContainer']//div[contains(@id,'FirstName')]")
	public WebElement txtSubscriberFirstName;
	
	@FindBy(xpath="//div[@id='phoneItemContainer']//div[contains(@id,'Ctn')]")
	public WebElement txtSubscriberCTN;
	
	@FindBy(xpath="//h1[contains(text(),'Device Support')]")
	public WebElement txtDeviceSupport;
	
	@FindBy(xpath="//h4[contains(text(),'-')]")
	public WebElement txtFirstNameAndCTNUnderViewDetailsModalWindow;
	
	@FindBy(xpath="//*[@id='sdgCtn']")
	public WebElement txtCTNUnderViewDetails;
	
	@FindBy(xpath="//*[@id='sdgFirstName']")
	public WebElement txtFNameUnderViewDetails;

	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//span/a[contains(text(),'Manage paperless billing')]")
	public WebElement lnkManagePaperlessBilling;
	
	//@FindBy(xpath="//a[@title='Make a Payment']")
	@FindBy(xpath="//a[@linkname='Make a Payment']") 
	public WebElement btnMakeAPayment;
	
	@FindBy(xpath="//a[contains(text(),'Sync devices with NumberSync')]") 
	public WebElement lnkSyncDevicesWithNumberSync;
	
	@FindBy(xpath="//a[contains(@id,'profile-open')]")
	public WebElement lnkProfileSecondaryNav;
	
	@FindBy(xpath="//li[@id='ddShortcutMenu3'] | //a[contains(text(),'Manage my plan')]")
	public WebElement lnkManageMyPlan;

	@FindBy(xpath="//span[@class='colorBlue'][contains(text(),'U-verse TV')]")
	public WebElement lnkUverseTV;

	@FindBy(xpath="//li[1]/a[contains(@class,'wt_BodyQuickLinks')][contains(text(),'Compare TV plans')]")
	public WebElement lnkCompareTVPlans;

	@FindBy(xpath="//li[5]/a[contains(@class,'wt_BodyQuickLinks')][contains(text(),'View my channel lineup')]")
	public WebElement lnkChannelLineup;

	@FindBy(xpath="//li[6]/a[contains(@class,'wt_BodyQuickLinks')][contains(text(),'Set & manage parental controls')]")
	public WebElement lnkParentalControls;

	@FindBy(xpath="//li[7]/a[contains(@class,'wt_BodyQuickLinks')][contains(text(),'Add receivers')]")
	public WebElement lnkAddReceivers;

	@FindBy(xpath="//li[4]/a[contains(@class,'wt_BodyQuickLinks')][contains(text(),'Manage my DVR')]")
	public WebElement lnkManageDVR;
	
	@FindBy(xpath="//div[@id='dashboardMessageCenterDiv']//a[contains(text(),'View all')] | //a[@alt='View all']")
	public WebElement lnkViewAll;
	
	@FindBy(xpath="//a[text()='Download Internet Security']")
	public WebElement lnkDownloadInternetSecurity;
	
	@FindBy(xpath="//a[contains(@href,'TroubleshootAndResolve')]")
	public WebElement lnkTroubleshootAndResolve;

	@FindBy(xpath="//a[@alt='Change my password' or text()='Change my password']")
	public WebElement lnkChangeMyEmailPwd;
	
	@FindBy(xpath="//a[@linkname='DIRECTV']")
	public WebElement lnkDTVToggle;
	
	@FindBy(xpath="//a[contains(@href,'HomePhoneProductLandingPage')][text()='Home Phone']")
	public WebElement lnkHomePhoneSecondaryNav;
	
	@FindBy(xpath="//a[@alt='U-verse TV U300']")
	public WebElement lnkUverseTVToggle;

	@FindBy(xpath="//a[@class='font12 wt_BodyAlerts'][@alt='Contact AT&T']")
	public WebElement lnkContactATT;

	//@FindBy(xpath="//a[contains(text(),'Digital TV') and contains(@href,'TvProductLandingPage')]")
	//public WebElement lnkDigitalTVSecondaryNav;
	
	@FindBy(xpath="//a[contains(text(),'TV') and contains(@href,'TvProductLandingPage')]")
	public WebElement lnkDigitalTVSecondaryNav;
	
	@FindBy(xpath="//li//a[text()='Internet' and contains(@href,'InternetProductLandingPage')]")
	public WebElement lnkInternetSecondaryNav;
	
	@FindBy(xpath="//a[contains(text(),'Billing & Usage')] | //a[contains(text(),'Billing, Usage, Payments')]")
	public WebElement lnkBillingUsageSecNav;
	
	@FindBy(xpath="//div[@id='ftUpgradeEligible']")
	public WebElement lnkViewUpgrade;
	
	@FindBy(xpath="//*[contains(text(),'View upgrade options')]")
	public WebElement lnkViewUpgradeOptions;
	
	@FindBy(xpath="//a[contains(text(),'Billing & Payments')] | //span[contains(text(),'Billing & payments')]/parent::a")
	public WebElement lnkBillingAndPaymentSecNav;
	
	@FindBy(xpath="//a[@class='navTertiaryLink'][contains(text(),'Manage AutoPay')]")
	public WebElement lnkManageAutopayTertNav;
	
	@FindBy(xpath="//a[@class='navTertiaryLink'][contains(text(),'Bill Details')]")
	public WebElement lnkBillDetailsTertNav;
	
	@FindBy(xpath="//a[@alt='Change plan']")
	public WebElement lnkChangePlanDasboard;
	
	@FindBy(xpath="//a[@name='Change Plan']")
	public WebElement lnkChangePlanWirelessPage;
	
	@FindBy(xpath="//a[@id='cplink']")
	public WebElement lnkChangePlanServiceSummary;
	
    @FindBy(xpath="//a[contains(text(),'Wireless') and contains(@href,'WirelessProductLandingPage')]")
    public WebElement lnkWirelessSecNav;
	
	@FindBy(xpath="//a[@class='navTertiaryLink'][contains(text(),'Suspend or Reactivate a Device')]")
	public WebElement lnkSuspendOrReactivateDeviceTertNav;
	
	@FindBy(xpath="//a[@class='navTertiaryLink' and @href='/olam/passthroughAction.myworld?actionType=AddALine']")
	public WebElement lnkAddADevice;
	
	@FindBy(id="ge5p_z2_t2024")
	public WebElement lnkAddADeviceStub;
	
	@FindBy(xpath="//a[@class='navTertiaryLink' and @id='ge5p_z2_t3025']")
	public WebElement lnkTransferANumberToATT;
	
	@FindBy(xpath="//a[@class='navTertiaryLink' and @id='ge5p_z2_t3026']|//a[@id='ge5p_z2_t2026']")
	public WebElement lnkChangeAWirelessNumber;
	
	@FindBy(xpath="//a[@alt='Receiving Discount']")
	public WebElement lnkReceivingDiscount;
	
	@FindBy(xpath="//a[@class='navTertiaryLink' and contains(text(),'Make A Payment')] | //a[contains(@href,'MakePayment')] | (//span[contains(text(),'Pay my bill')]/parent::a)[1] | (//span[contains(text(),'Pay my bill')]/parent::a)[1] | (//span[contains(text(),'Make a payment')]/parent::a)[1]")
	public WebElement lnkMakeAPaymentTertNav;
	
	@FindBy(xpath="//li[contains(@role,'presentation')]//a[contains(text(),'Overview')] | //a[@id='ge5p_z2_s2001' and contains(text(),Overview)]")
	public WebElement lnkOverview;
	
	@FindBy(xpath="//a[contains(text(),'View bill details')]")
	public WebElement lnkViewBillDetails;
	
	@FindBy(xpath="//a[contains(@alt,'Enroll in paperless billing')]|//a[contains(text(),'Enroll in Paperless Billing')]")
	public WebElement lnkEnrollInPaperlessBilling;
	
	@FindBy(xpath="//a[@alt='Enroll in paperless billing'] | //a[@alt='Enroll in Paperless Billing']")
	public WebElement lnkEnrollInPaperlessBillingServiveSummary;
	
	@FindBy(xpath="//a[@alt='Enroll in AutoPay'] | //a[contains(text(),'Enroll in AutoPay')]")
	public WebElement lnkEnrollInAutopay;
	
	@FindBy(xpath="//a[@alt='Enroll in AutoPay']")
	public WebElement lnkEnrollInAutopay_OverviewPage;
	
	@FindBy(xpath="//a[contains(text(),'Update Stored Payment Method') and contains(@href,'paymentProfiles')]")
	public WebElement lnkUpdatePaymentProfile;
	
	@FindBy(xpath="//a[contains(text(),'Manage DVR')]")
	public List<WebElement> lnkmanageDVR ;
	
	@FindBy(xpath="//a[@linkname='Tour my bill']")
	public WebElement lnkVideoBill;
	
	@FindBy(xpath="']//span[contains(text(),'U-verse TV')]")
	public WebElement lnkUverseTVSubCategory;
	
	@FindBy(xpath="//a[@id='ge5p_z2_p4001']")
	public WebElement lnkSupport;
	
	@FindBy(xpath="//a[contains(text(),'check the status')]")
	public WebElement lnkCheckStatus;
	
	@FindBy(xpath="//a[@id='ge5p_z2_s4003' and contains(text(),'Internet')]")
	public WebElement lnkSupportInternet;

	@FindBy(xpath="//li[8]/a[contains(@class,'wt_BodyQuickLinks')][contains(text(),'Move my TV service')]")
	public WebElement lnkMovetoMyyTVService;
	
	@FindBy(xpath="//li[5]/a[contains(@class,'wt_BodyQuickLinks')][contains(text(),'Move my Internet service')]")
	public WebElement lnkMovetoMyInternetService;

	@FindBy(xpath="//li[4]/a[contains(@class,'wt_BodyQuickLinks')][contains(text(),'Move my Voice service')]")
	public WebElement lnkMovetoMyVoiceService;
	
	@FindBy(xpath="//span[@class='colorBlue'][contains(text(),'U-verse Internet')]")
	public WebElement lnkUverseInternet;
	
	@FindBy(xpath="//span[@class='colorBlue'][contains(text(),'U-verse Voice')]")
	public WebElement lnkUverseVoice;

	@FindBy(xpath="//li[1]/a[contains(@class,'wt_BodyQuickLinks')][contains(text(),'Compare Internet plans')]")
	public WebElement lnkCompareInternetplans;

	@FindBy(xpath="//li[1]/a[contains(@class,'wt_BodyQuickLinks')][contains(text(),'Compare voice')]")
	public WebElement lnkCompareVoice;
	
	@FindBy(xpath="//a[contains(text(),'My Phone / Device')]")
	public WebElement lnkPhoneDevicesTertNav;
	
	@FindBy(xpath="//a[@alt='Update payment profile']")
	public WebElement lnkUpdatePaymentProfileAlert;
	
	@FindBy(xpath="//a[@alt='Manage AutoPay settings']")
	public WebElement lnkManageAutopaySettings;
	
	@FindBy(xpath="//span[@class='colorBlue' and contains(text(),'View & pay bill')]")
	public WebElement lnkViewPayBill;

	@FindBy(xpath="//a[@linkname='View & pay bill_View my bill'] | //a[contains(@href,'ViewBillDetails')]")
	public WebElement lnkViewMyBill;
	
	@FindBy(xpath="//a[@linkname='View & pay bill_View my billing history']")
	public WebElement lnkViewMyBillingHistory;
	
	@FindBy(xpath="//a[@linkname='View & pay bill_View bill and usage reports']")
	public WebElement lnkViewBillandUsageReports;
	
	@FindBy(xpath="//a[@linkname='View & pay bill_Make a payment']")
	public WebElement lnkViewMakePayment;
	
	@FindBy(xpath="//a[@linkname='View & pay bill_Arrange late payment']|//a[contains(text(),'Arrange Late Payment')]")
	public WebElement lnkViewArrangeLatePayment;
	
	@FindBy(xpath="//a[@linkname='View & pay bill_Update payment profile']")
	public WebElement lnkViewUpdatePaymentProfile;
	
	@FindBy(xpath="//a[@linkname='View & pay bill_View payment activity']")
	public WebElement lnkViewPaymentActivity;
	
	@FindBy(xpath="//a[contains(text(),'Manage AutoPay')] | //dd//a[contains(text(),'Manage AutoPay')]")
	public WebElement lnkManageAutoPay;
	
	@FindBy(xpath="//a[text()='Shop'] | //a[@href='http://www.att.com/shop']")
	public WebElement lnkShopPrimaryNav;
	
	@FindBy(xpath="//a[text()='myAT&T']")
	public WebElement lnkMyATTPrimaryNav;
	
	@FindBy(xpath="//a[@class='ge5p_z2-primary-nav-el'][text()='Support']")
	public WebElement lnkSupportPrimaryNav;
	
	@FindBy(xpath="//ul[@id='ge5p_z2_1_2']")
	public WebElement lnkSecNav;
	
	@FindBy(xpath="//a[contains(text(),'Enroll in AutoPay')]")
	public WebElement lnkEnrollInAutopay2;
	
	@FindBy(xpath="//a[@class='wt_Body' and contains(text(),'Manage AutoPay')]")
	public WebElement lnkManageAutoPay2;
	
	@FindBy(xpath="//div[@class='dashboardTabContent']//a[contains(text(),'Find my wifi network key')]")
	public WebElement lnkFindMyWifiPasswordUnderSummerySection;
	
	@FindBy(xpath="//a[text()='Update My Profile']")
	public WebElement lnkUpdateMyProfileTertNav;
	
	@FindBy(xpath="//a[@name='Prepare for your installation']")
	public WebElement lnkPrepareInstallation;
	
	@FindBy(xpath="//a[@name='View Internet user guide']")
	public WebElement lnkViewInternetGuide;
	
	@FindBy(xpath="//a[text()='Change My Plan'][contains(@href,'ChangeRatePlan')]")
	public WebElement lnkChangeMyPlanTertiaryNav;
	
	@FindBy(xpath="//a[text()='Billing, Usage, Payments']")
	public WebElement lnkBillingUsagePaymentsSecNav;
	
	@FindBy(xpath="//a[text()='Usage Since Last Bill']")
	public WebElement lnkUsageSinceLastBillTertNav;
	
	@FindBy(id="ge5p_z1-language-drop-down-link")
	public WebElement lnkLanguage;
	
	@FindBy(xpath="//a[contains(text(),'My Phone / Device') and contains(@href,'PhoneDevice')]")
	public WebElement lnkMyPhoneAndDeviceTertiarynav;
	
	@FindBy(xpath="//a[contains(text(),'en Español')]")
	public WebElement lnkSpanish;
	
	@FindBy(xpath="//a[contains(text(),'In English')]")
	public WebElement lnkEnglish;
	
	@FindBy(xpath="//a[@class=' closing overlay-close']")
	public WebElement lnkCloseOverlay;

	@FindBy(xpath="//a[contains(text(),'In English')]")
	public WebElement lnkBillingUsagePaymentsSec;
	
	@FindBy(xpath="//a[@alt='Reactivate service']")
	public WebElement lnkReactivateService;
	
	@FindBy(xpath="//a[@alt='Manage DVR']")
	public WebElement lnkManageDVRServiceSummary;
	
	@FindBy(xpath="//a[@alt='View channel lineup']")
	public WebElement lnkViewChannelLineup;
	
	@FindBy(xpath="//a[@class='ge5p_z2-primary-nav-el'][@aria-label='Support']")
	public WebElement lnkSupportTab;
	
	@FindBy(xpath="//a[@id='ge5p_z2_s4003'][contains(text(),'Internet')]")
	public WebElement lnkSupportInternetSecNav;
	
	@FindBy(xpath="//a[@id='ge5p_z2_t4002'][@class='navTertiaryLink'][contains(text(),'Billing & Account')]")
	public WebElement lnkSupportBillingAccountsTerNav;
	
	@FindBy(xpath="//a[@id='ge5p_z2_t3034']|//a[@id='ge5p_z2_t3022']")
	public WebElement lnkMyPhonesDevicestertnav;
	
	@FindBy(xpath="//div[@class='inner-border']//a[contains(text(),'BILL & PAYMENTS')]")
	public WebElement lnkBillAndPaymentSMB;
	
	@FindBy(xpath="//div[@class='inner-border']//a[contains(text(),'View Bill Summary')]")
	public WebElement lnkViewBillSummarySMB;
	
	@FindBy(xpath="//div[@class='left-col-content']//a[contains(text(),'My Message Center')]")
	public WebElement lnkMyMessageCenterSMB;
	
	@FindBy(xpath="//div[@class='PadTop5 Padbot15']//a[contains(text(),'View bill details')]")
	public WebElement lnkViewBillDetailsForOldDashboard;
	
	@FindBy(xpath="//a[contains(text(),'Enroll in Paperless Billing ')]")
	public WebElement lnkEnrollInPaperlessBillingForOldDashboard;
	
	@FindBy(xpath="//a[@href='/olam/showAllMessageCenter.myworld?']")
	public WebElement lnkViewAll_OldDashboard;
	
	@FindBy(xpath="//a[@alt='View request']")
	public WebElement lnkViewRequest;
	
	@FindBy(xpath="//a[contains(text(),'View My Plan & Features')]")
	public WebElement lnkViewMyPlanAndFeatures;
	
	@FindBy(xpath="//a[contains(text(),'Earn Plenti points at AT&T')]")
	public WebElement lnkPlenti;

	@FindBy(xpath="//a[contains(text(),'View International Options')]")
	public WebElement lnkViewInternationalOptionsTertiaryNav;
	
	@FindBy(xpath="//*[contains(@class,'submenu')]//a[text()='My Linked Accounts']")
	public WebElement lnkMyLinkedAccounts;

	@FindBy(xpath="//*[contains(@class,'mylinked-drop-down')]//*[contains(@class,'menuitem')]//a[text()='Go to My Profile']")
	public WebElement lnkGoToMyProfile;
	
	@FindBy(xpath="//a[contains(text(),'Fix it now! Support tools')]")
	public WebElement lnkTroubleshoot;
	
	@FindBy(xpath="//a[contains(text(),'View help videos and articles')]")
	public WebElement lnkViewHelpVideos;
	
	@FindBy(xpath="//a[contains(text(),'Find my Wi-Fi network key')]")
	public WebElement lnkFindMyWifiNetworkKey;
	
	@FindBy(xpath="//a[@class='wt_BodyAlerts'][@linkname='ScrollDown_2']")
	public WebElement lnkalertsscrolldown;
	
	@FindBy(xpath="//img[@id='alertScrollDown']")
	public WebElement lnkAlertsScrollDown;
	
	@FindBy(xpath="//a[@class='font12 wt_BodyAlerts'][text()=' View unbilled mobile purchases']")
	public WebElement lnkCTAtounbilledpurchases;

	@FindBy(xpath="//a[contains(text(),'View billed mobile purchases')]")
	public WebElement lnkCTAtoBilledpurchases;
	
	@FindBy(xpath="//a[@class='wt_Body'][text()='View all usage']|//a[contains(@class,'wt_Body')][text()='View all usage']")
	public WebElement lnkmobileviewallusage;

	@FindBy(xpath="//a[@id='mobilepurchasedetails']")
	public WebElement lnkdetailsinallusagelink;
	
	@FindBy(xpath="//a[text()='myAT&T Login Password']")
	public WebElement lnkMyATTLoginPasswordTerNav;
	
	@FindBy(xpath="//a[text()='Wireless Voicemail Password']")
	public WebElement lnkWirelessVoicemailPasswordTerNav;
	
	@FindBy(xpath="//a[text()='Home Phone Voicemail Password']")
	public WebElement lnkHomePhoneVoicemailPasswordTerNav;
	
	@FindBy(xpath="//li[@id='ulddShortcutFlyoutMenu40liddShortcutFlyoutMenu43']/a[contains(text(),'View my email')]")
	public WebElement lnkViewMyEmail;

	@FindBy(xpath="//a[contains(text(),'Página en Español')]|//a[contains(text(),'Español')]")
	public WebElement lnkPageInSpanish;

	@FindBy(xpath="//a[text()='Resumen']")
	public WebElement lnkResumen;
	
	@FindBy(xpath="//a[text()='Idioma']")
	public WebElement lnkIdoma;
	
	@FindBy(xpath="//a[text()='Page In English']")
	public WebElement lnkPageInEnglish;
	
	@FindBy(xpath="//a[contains(@href,'A_PMT_AUTO_PAY_SETUP')]")
	public WebElement lnkManageAutopayDasboard;
	
	@FindBy(xpath="//a[@id='cplink']")
	public WebElement lnkChangePlanDasboardNew;

	@FindBy(xpath="//li[@class='noFloat FTP_G21071097  last-visible']//a[@class='wt_Body focusable'][@linkname='Wireless FamilyTalk Nation 550 with Rollover']")
	public WebElement lnkMyPlansnew;
	
	@FindBy(xpath="//a[text()='grace period']")
	public WebElement lnkGracePeriod;
	
	@FindBy(xpath="//a[contains(text(),'Bill Reports')]")
	public WebElement lnkBillReports;
	
	@FindBy(xpath="//a[@linkname='Manage my profile_Link my AT&T accounts']")
	public WebElement lnklinkMyATTAccountsInIWantTo;
	
	@FindBy(xpath="//a[@id='ge5p_z2_t3102']")
	public WebElement lnkManageSubAccountTertNav;

	@FindBy(xpath="//li[@id='ddShortcutFlyoutMenu9']//a[contains(text(),'Add a line')]")
	public WebElement lnkAddALine;

//	@FindBy(xpath="//a[contains(@title,'Check upgrade eligibility')]|//a[contains(text(),'Check Upgrade Eligibility')]")
	@FindBy(xpath="//a[contains(@title,'Check upgrade eligibility')] | //a[@id='ge5p_z2_t3018']")
	public WebElement lnkCheckUpgradeEligibility;
	
	@FindBy(xpath="(//a[contains(text(),'Check Upgrade Eligibility')])[2] ")
	public WebElement lnkCheckUpgradeEligibility1;
	
	@FindBy(xpath="//a[contains(@class,'focusable')]//*[contains(text(),'FamilyTalk')][1]")
	public WebElement lnkFamilyTalkPlanInMyPlans;
	
	@FindBy(xpath="(//a[contains(@class,'focusable')]//*[contains(text(),'Rollover')])[1]")
	public WebElement lnkNationPlanInMyPlans;

	@FindBy(xpath="//a[@href='/olam/acctSelectTYPEstart.myworld'][contains(text(),'Link another account')]")
	public WebElement lnkLinkAnotherAccountInMyLinkedAccount;

	@FindBy(xpath="//a[@href='/olam/passthroughAction.myworld?actionType=SLIDAcctMgr'][contains(text(),'Manage account access')]")
	public WebElement lnkManageAccountAccessInMyLinkedAccount;

  @FindBy(xpath="//*[@id='divShortcut']//div/span[contains(text(),'View usage')]")
  public WebElement lnkViewUsageUnderIwantTo;

  @FindBy(xpath="//a[contains(text(),'View usage trends and graphs')]")
   public WebElement lnkViewUsageTrendsAndGraphs;
  
  @FindBy(xpath="//a[contains(text(),'Fix it now')]")
  public WebElement lnkFixItNow;
  
  @FindBy(xpath="//a[@alt='Update billing information']")
  public WebElement lnkUpdateBillingInformation;
  
  @FindBy(xpath="//a[@alt='Update email address']")
  public WebElement lnkUpdateEmailAddress;
  
  @FindBy(xpath="//a[text()='Reschedule']")
  public WebElement lnkReSchedule;
  
  @FindBy(xpath="//a[contains(text(),'orders')]")
  public WebElement lnkMyOrders;
  
  @FindBy(xpath="//a[@href='http://www.att.com/esupport/article.jsp?sid=KB408261#fbid=UMhLpU9n6ay']")
  public WebElement lnkAlertLearnMore;
  
  @FindBy(xpath="//a[contains(text(),'View current usage')]")
  public WebElement lnkQuickToolsViewCurrentUsage;
  
  @FindBy(xpath="//a[contains(text(),'View billed usage')]")
  public WebElement lnkQuickToolsViewBilledUsage;

  @FindBy(xpath="//a[contains(text(),'Paperless Billing')]")
  public WebElement lnkPaperlessBillingSecNav;
  
  @FindBy(xpath="//a[@class='navTertiaryLink'][contains(text(),'Billing History')]")
	public WebElement lnkBillingHistoryTerNav;
 
  @FindBy(xpath="//a[contains(text(),'myAT&T')]")
	public WebElement lnkMyATT;
  
  @FindBy(xpath="//a[@title='link your accounts']")
 public WebElement lnkLinkYourAccounts;
 
  @FindBy(xpath="//a[contains(text(),'View details')]|//a[contains(text(),'View usage details')]")
 public WebElement lnkViewUsgeDetails;
  
  @FindBy(xpath="//a[@href='#tipVideo4300207']|//a[contains(text(),'Understanding')]")
  public WebElement lnkunderstandingVideoToolTip;
  
  @FindBy(xpath="//div[@class='MarTop6 w110']//a[contains(text(),'Learn how to use my device')] | //a[contains(text(),'Learn how to use my device')]")
  public WebElement lnkLearnHowToUseMyDevice;
  
  @FindBy(xpath="//div[@id='WirelessDashboardDashboardPromotion3']//a[@class='atg-promotion-link']")
  public WebElement lnkATGPromotionTile;
  
  @FindBy(id="ge5p_z2_t3009")
  public WebElement lnkPaymentActivityTertNav;
  
  @FindBy(xpath="//a[text()='Add & Remove Features']")
  public WebElement lnkAddRemove;
  
  @FindBy(xpath="//a[contains(text(),'View connected device support')]")
  public WebElement lnkViewConnectedDeviceSupport;
  
  @FindBy(xpath="//a[contains(text(),'s data on')]")
  public WebElement lnkMembersDataOnOff;
  
  @FindBy(xpath="//a[contains(text(),'View My Contract')]")
  public WebElement lnkViewMyContract;
  
  @FindBy(xpath="//a[text()='Hide']")
  public WebElement lnkAlertHide;
  
  @FindBy(xpath="//div[@id='alertList']//*[contains(text(),'Appointment Reminder')]//parent::p//parent::div//a[contains(text(),'Change appointment')]")
  public WebElement lnkAppointmentReminderChangeAppointment;
  
  @FindBy(xpath="//a[contains(text(),'View all usage')]")
  public WebElement lnkViewAllUsage;
  
  @FindBy(xpath="//*[contains(@class,'modalContent')]//a[contains(text(),'View all usage')]")
  public WebElement lnkViewAllUsageOnViewDetailsFrame;

  @FindBy(xpath="//a[@title='Make a Payment'] | //img[@alt='Make a Payment']")
  public WebElement lnkMAP;
  
  @FindBy(xpath="//a[contains(text(),'Manage Plan & Services')]")
  public WebElement lnkManagePlans;
  
  @FindBy(xpath="//a[contains(text(),'View plan details')]")
  public WebElement lnkViewPlanDetails;
  
  @FindBy(xpath="//a[@alt='Make a payment']")
  public WebElement lnkMakeAPaymentAlert;
  
  @FindBy(xpath="//a[@alt='View pending payment']")
  public WebElement lnkViewPendingPayment;
  
  @FindBy(xpath="//a[@alt='View payments']")
  public WebElement lnkViewPaymentAlert;
  
  @FindBy(xpath="//li//span[contains(text(),'Manage my plan')]//parent::div//parent::li//li//span[contains(text(),'Home Phone')]")
  public WebElement lnkHomePhoneInManageMyProfile;
  
  @FindBy(xpath="//li//span[contains(text(),'Get help & support')]//parent::div//parent::li//li//span[contains(text(),'Home Phone')]")
  public WebElement lnkHomePhoneInGetHelpAndSupport;
  
  @FindBy(xpath="//a[contains(text(),'Check my email')]")
  public WebElement lnkCheckEmail;
  
  @FindBy(xpath="//a[contains(text(),'Learn more about returning equipment')]")
  public WebElement lnkEquipment;
  
  @FindBy(xpath="//a//img[contains(@alt,'tooltip help')]")
  public WebElement lnkTooltip;

  @FindBy(xpath="//a[@id='ge5p_z2_s1001']") // added by Hiral Jogi
  public WebElement lnkShopwireless;
  
  @FindBy(xpath="//a[@id='ge5p_z2_t1026']") // added by Hiral Jogi
  public WebElement lnkViewAllCellPhonesandDevices;
  
  @FindBy(xpath="//a[contains(text(),'See if you can get a bigger Locker')]") // added by Clint
  public WebElement lnkSeeIfYouCanLinkForLocker;
  
  @FindBy(xpath="//a[contains(text(),'Go to Locker')]") // added by Clint
  public WebElement lnkGoToLocker;
  
  @FindBy(xpath="//a[contains(text(),'Cancel Locker')]") // added by Clint
  public WebElement lnkCancelLocker;
  
  @FindBy(xpath="//a[contains(text(),'Get help with my Locker')]") // added by Clint
  public WebElement lnkGetHelpWithMyLocker;
  
  @FindBy(xpath="//a[contains(@dtmreportingattrs,'CBRCTN') and @alt='Update now']")
  public WebElement lnkUpdateNowForCBR_CTN_Alert;

  @FindBy(xpath="//a[contains(text(),'Get connected device support')]") // added by Monica
  public WebElement lnkGetConnectedDeviceSupport;
  
  @FindBy(xpath="//a[contains(text(),'Go to DIRECTV to manage my account')]")
  public WebElement lnkGoToDirectv;

  @FindBy(xpath="//a[contains(text(),'Shop at AT&T')]")
  public WebElement lnkShopAtATT;
  
  @FindBy(xpath="//a[contains(text(),'Log in with your AT&T Access ID and password.')]")
  public WebElement lnkLoginwithATTAIDandPWd;
  
  @FindBy(xpath="//a[@alt='View details']")
  public WebElement lnkViewDetailsUnderPlanChangeAlert;
  
  @FindBy(xpath="//div[contains(@class,'usageContainer') or contains(@id,'ItemContainer')]//a[contains(text(),'View details')]")
  public WebElement lnkViewDetailsUnderUsageContainer;
  
  @FindBy(xpath="//div//a[contains(text(),' Manage online access')]")
  public WebElement lnkManageOnline;

  @FindBy(xpath="//a[contains(text(),'Update now')]")
  public WebElement lnkAlertUpdatenow;
  
  @FindBy(xpath="//li[@id='ddShortcutMenu6']/div/span[contains(text(),'Shop AT&T')]//parent::*//parent::li//span[contains(text(),'Wireless')]")
  public WebElement lnkWirelessInShopATTInIWantTo;
  
  @FindBy(xpath="//a[contains(@class,'QuickLinks') and contains(text(),'Replace my device')]")
  public WebElement lnkReplaceMyDevice;
  
  @FindBy(xpath="//a[@id='enrollLoco']")
  public WebElement lnkLinkYourAccountToPlenti;
    
  @FindBy(xpath="//a[contains(text(),'Employee Mobility Offer')]")
  public WebElement lnkEMODisccount;
  
  @FindBy(xpath="//a/img[@alt='Close'] | //*[@id='colorbox']/a/img[@alt='Close_Modal'] | //*[@id='colorbox']/a/img[@alt='Close'] | //a[@class='closeModal wt_BodyCloseModal']")
  public WebElement lnkCloseEMODisccountModel;
  
  @FindBy(xpath="//a[@linkname='View details'] ")
  public WebElement lnkViewDetailsSummary;
  
  @FindBy(xpath="//a[contains(text(),'Upgrade Talk limit')]")
  public WebElement lnkUpgradeTalkLimit;

  @FindBy(xpath="//a[contains(text(),'Upgrade Text limit')]")
  public WebElement lnkUpgradeTextLimit;
  
  @FindBy(xpath = "//a[contains(text(),'Start watching now')]")
  public WebElement lnkStartWatchingNow;
  
@FindBy(xpath = "//a[contains(text(),'See all my usage')]")
public WebElement lnkSeeAllMyUsage;

@FindBy(xpath = "(//a[contains(@href,'PROFILEOVERVIEW')])[1]")
public WebElement lnkViewProfile;

@FindBy(xpath = "//a/span[contains(text(),'My wireless')]")
public WebElement lnkMyWirelessSecNav;

@FindBy(xpath = "(//span[contains(text(),'Check usage')]/parent::a)[1]")
public WebElement lnkCheckUsage;

	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[contains(@alt,'Out')] | //*[contains(text(),'Log out')]") 
	public WebElement btnLogout;
	
	@FindBy(xpath="//button[@id='ddShortcut']") 
	public WebElement btnIWantTo;
	
	@FindBy(xpath="//ul[contains(@id,'alertButton')]//*[contains(text(),'Alerts')] | //span[@id='alertButtonLabel'] | //ul[contains(@id,'alertButton')]//li//span[contains(text(),'Alerts')] | //h2[contains(text(),'Alerts')]") //edited by Clint on 19th Nov for Alerts contains 0 alert count
	public WebElement btnAlert;
	
	@FindBy(xpath="//li[@class='font18 rel center cursorPointer wt_BodyAlerts']")
	public WebElement btnAlertClick;
	
	

	//@FindBy(xpath="//a[@id='planScrollDown']")
	//public WebElement btnDownarrow;
	
	//@FindBy(xpath="//a[@id='planScrollUp']")
	//public WebElement btnUparrow;
	
	@FindBy(xpath="//img[@alt='Scroll Down']")
	public WebElement btnDownarrow;
	
	@FindBy(xpath="//img[@alt='Scroll Up']")
	public WebElement btnUparrow;

	@FindBy(xpath="//ul[@id='alertButtonOn']")
	public WebElement btnAlertBox;
	
	//@FindBy(xpath="//div[@class='btnLt']/a")
	//@FindBy(xpath="//a[contains(text(),'link your accounts')]")
	@FindBy(xpath="//a[contains(text(),'Link your accounts')]")
	public WebElement btnLinkMyAccounts;
	

	@FindBy(xpath="//div[@class='inner-border']//img[@alt='Make a Payment']")
	public WebElement btnMakeAPaymentSMB;

	@FindBy(xpath="//a[@id='mp_cnti' and contains(text(),'Si, Continuar')]")
	public WebElement btnContinueLanguage;
	
	//For i want to on product tile
	@FindBy(xpath="//div[@class='MyBillDropDown']")
	public WebElement btnIWant;
	
	@FindBy(xpath="//img[@alt='Make a Payment'] | //a[contains(@linkname,'Make a Payment') and contains(text(),'Make a Payment')] | //a[@linkname='Make a Payment']")
	public WebElement btnMakeAPayment_OldDashboard;
	
	@FindBy(xpath="//a[contains(@linkname,'Make a Payment') and //*[contains(text(),'Make a Payment')]]")
	public WebElement btnMakeAPaymentInOverviewPage;
	
	@FindBy(xpath="//ul[@id='MainTab']/li[5]")
	public WebElement btnPlan5;
	
	@FindBy(xpath="//input[@value='Continue']")
	public WebElement btnTroubleShootAndResolveContinue;
	
	@FindBy(xpath="(//a[contains(text(),'Link to Plenti')])[1]")
	public WebElement btnLinkToPlenti;
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	@FindBy(xpath="//iframe[contains(@src,'dashboardPBOPromoModal.jsp')]")
	public WebElement frmPaperlessBilling;
	
	@FindBy(xpath="//iframe[@class='cboxIframe']")
	public WebElement frmTourMyBill;
	
	@FindBy(xpath="//h1[contains(text(),'Your Personalized Bill Tour is Ready')]")
	public WebElement elmBillTour;
	
	@FindBy(xpath="//a[@class='closeModal']|//a[contains(@class,'closeModal')]")
	public WebElement lnkClose;
	
	@FindBy(xpath="//iframe[contains(@src,'Modal')] | //iframe[contains(@src,'openMSModalWirelessDashboard')]")
	public WebElement frmDeviceDetailsModal;
	
	@FindBy(xpath="//span[text()='Includes employer data']")
	public WebElement frmDeviceDetailsIncludes;
	
	@FindBy(xpath="//iframe[contains(@src,'Promo')]")
	public WebElement frmPromotionalOffer;
	
	@FindBy(xpath="//div[contains(@class,'errorMsg')]/p")
	public WebElement errorMsg;
	
	@FindBy(xpath="//iframe[contains(@src,'IRUDiscount')]")
	public WebElement framEMODiscount;
	
		/* --------- Objects added by onshore - Start here -------------- */
	
	
	@FindBy(xpath = "")
	public WebElement txtWelcomeTxt;
	
	@FindBy(xpath="//a[text()='Profile' and contains(@class,'secondary')]")				// Added by Rahul Bakde - 7/23/2015
	public WebElement lnkProfileSecNav;

	@FindBy(xpath="//a[contains(text(),'My Orders')]")				// Added by Rahul Bakde - 7/23/2015
	public WebElement lnkMyOrdersSecNav;
	
	@FindBy(xpath="//span[contains(text(),'ast log')]")				// Added by Rahul Bakde - 7/23/2015
	public WebElement txtLastLoggedIn;
	
	@FindBy(xpath="//img[contains(@src,'HomePhone')]")				// Added by Rahul Bakde - 7/24/2015
	public WebElement imgHomePhone;
	
	@FindBy(xpath="//h3/a[contains(text(),'Home Phone')]")				// Added by Rahul Bakde - 7/24/2015
	public WebElement lnkHomePhone;
	
	@FindBy(xpath="//*[contains(text(),'Other Services')]")				// Added by Rahul Bakde - 7/24/2015
	public WebElement txtOtherServices;

	@FindBy(xpath="//img[contains(@src,'add_wireless')]")				// Added by Rahul Bakde - 7/24/2015
	public WebElement imgAddWireless;
	
	@FindBy(xpath="//a[contains(text(),'Shop Wireless')]")				// Added by Rahul Bakde - 7/24/2015
	public WebElement lnkShopWireless;
	
	@FindBy(xpath="//img[contains(@src,'add_TV')]")				// Added by Rahul Bakde - 7/24/2015
	public WebElement imgAddDigitalTV;
	
	@FindBy(xpath="//h3[contains(text(),'Wireless') and @class='disabletext']")				// Added by Rahul Bakde - 7/24/2015
	public WebElement txtDisabledWireless;
	
	@FindBy(xpath="//h3[contains(text(),'Digital TV') and @class='disabletext']")				// Added by Rahul Bakde - 7/24/2015
	public WebElement txtDisabledDigitalTV;
	
	@FindBy(xpath="//a[contains(text(),'U-verse TV only')]")				// Added by Rahul Bakde - 7/24/2015
	public WebElement lnkUverseTVOnly;
	
	@FindBy(xpath="//img[contains(@src,'AddUverseInternet')]")				// Added by Rahul Bakde - 7/24/2015
	public WebElement imgAddUverseInternet;
	
	@FindBy(xpath="//h3[contains(text(),'Internet') and @class='disabletext']")				// Added by Rahul Bakde - 7/24/2015
	public WebElement txtDisabledInternet;
	
	@FindBy(xpath="//a[contains(text(),'U-verse Internet now')]")				// Added by Rahul Bakde - 7/24/2015
	public WebElement lnkUverseInternetOnly;
	
	@FindBy(xpath="//span[contains(@class,'Orange') or contains(@class,'Green') or contains(@class,'Red') and contains(text(),'$')]")				// Added by Rahul Bakde - 7/24/2015
	public WebElement txtBalanceAmtAnyColor;
	
	@FindBy(xpath="//a[contains(text(),'Home  Options')]")				// Added by Rahul Bakde - 7/24/2015
	public WebElement lnkHomePhoneOptions;

	@FindBy(xpath="//*[contains(text(),'Service Summary')]")				// Added by Rahul Bakde - 7/24/2015
	public WebElement lnkServiceSummary;

	@FindBy(xpath="//div[@class='row-seamless']//a[contains(text(),'Home Phone')]")				// Added by Rahul Bakde - 7/24/2015
	public WebElement lnkHomePhoneUnderServiceSummary;
	
	@FindBy(xpath="//div[@id='myBillSectionDataDiv']//a[contains(text(),'View bill details')]")			// Added by Rahul Bakde - 7/27/2015
	public WebElement lnkviewBillDetails;

//	@FindBy(xpath="//img[contains(@title,'Make')]/parent::a")	
	@FindBy(xpath="//*[contains(@title,'Make') or contains(text(),'Make')]/parent::a")			// Added by Rahul Bakde - 7/27/2015
	public WebElement btnMakeAPaymentOverview;
	
	@FindBy(xpath="//a[contains(@id,'firstname')]")			// Added by Rahul Bakde - 7/28/2015
	public WebElement lnkFirstName;

	@FindBy(xpath="//span[contains(text(),'ast due')]")		// Added by Rahul Bakde - 7/29/2015
	public WebElement txtPastDueTxt;

	@FindBy(xpath="//ul[contains(@id,'alertButton') and contains(@style, 'block')]")		// Added by Rahul Bakde - 7/29/2015
	public WebElement btnAlertBtn;
	
	@FindBy(xpath="//h2[text()='My Plans']")		// Added by Rahul Bakde - 7/29/2015
	public WebElement txtMyPlans;
	
	@FindBy(xpath="//p[@id='displayMessage']")		// Added by Rahul Bakde - 7/29/2015
	public WebElement txtATTMsgBox;
	
	@FindBy(xpath="//li[contains(@class,'IPTV')]/a[contains(@class,'focusable') and contains(text(),'')]")		// Added by Rahul Bakde - 7/29/2015
	public WebElement lnkUverseTVProductTile;

	@FindBy(xpath="//a[text()='Plan details']")		// Added by Rahul Bakde - 7/29/2015
	public WebElement lnkPlanDetails;
	
	@FindBy(xpath="//*[contains(text(),'Recent Orders')]")		// Added by Rahul Bakde - 7/29/2015
	public WebElement txtRecentOrders;
	
	@FindBy(xpath="//p[text()='Charges']")		// Added by Rahul Bakde - 7/29/2015
	public WebElement txtCharges;
		
	//@FindBy(xpath="//*[@id='ge5p_z2_s3011' and contains(text(),'Digital Life')]")     // Added by Deepak Kapdi - 7/2/2015
	@FindBy(xpath="//a[contains(text(),'myAT&T')]//parent::li//li[contains(@role,'presentation')]//a[contains(@href,'my-digitallife')] | //*[@id='ge5p_z2_s2011' and contains(@aria-label,'Digital Life')]") //Added by Clint on 17th Dec 2015 
	public WebElement lnkDigitalLife;
	
	@FindBy(xpath="//*[@id='displayMessage']")                                // Added by Deepak Kapdi - 7/2/2015
	public WebElement lstDisplayMessage;
	
	@FindBy(xpath="//span[contains(text(),'Last logged in on')]")			 // Added by Deepak Kapdi - 8/25/2015
	public WebElement txtLastLoggedInOn;
	
	@FindBy(xpath="//div[@class='MyBillDropDown']")                          // Added by Deepak Kapdi - 8/25/2015
	public WebElement lnkIWantTo;
	
	
	@FindBy(xpath="//h2[contains(text(),'Service Summary')]")
	public WebElement txtServiceSummary;
		
	@FindBy(xpath="//a[contains(text(),'My Orders')]")   // Added by Ravinder
	public WebElement lnkMyOrders_1511;

	@FindBy(xpath="//span[contains(text(),'$') and contains(text(),'past due')]")    // Added by Ravinder
	public WebElement txtPastDueAmount_1511;
	
	@FindBy(xpath="//div[starts-with(@id,'dashboardMessageCenter')]//*[@id='displayMessage']")    // Added by Ravinder
	public WebElement txtATTMessageCenterbox_1511;
		
	//@FindBy(xpath="//img[starts-with(@alt,'Home Phone')] or img[starts-with(@alt,'Internet') or img[starts-with(@alt,'Phone')]")   // Added by Ravinder
	@FindBy(xpath="//img[starts-with(@alt,'Internet') or starts-with(@alt,'Home Phone') or starts-with(@alt,'Phone')]")     //Updated by Deepak - 09-17 for 1511
	public WebElement imgPhoneServiceSumaryOverview_1511;
	
	@FindBy(xpath=".//*[@id='servicecontent']//*[starts-with(text(),'Billing Cycle')]")   // Added by Ravinder
	public WebElement txtBillingCycleOverview_1511;
	
	@FindBy(xpath="//a[@id='ge5p_z2_t3019']")
	public WebElement lnkChangeMyPlanTertiaryNav_1511;
	
	@FindBy(xpath="//*[@id='alertSection']/div[2]/div[1]/ul/li/a") // Added by Deepak
	public WebElement lnkHide;
	
	@FindBy(xpath="//*[contains(@id,'UserName')]//h2")   // Added by Ravinder
	public WebElement txtWelcomeBackWithLastLogin_1511;
	
	@FindBy(xpath="//span[contains(text(),'as of')]")     // Added by Ravinder
	public WebElement txtTotalBalAsOfDate_1511;
	
	@FindBy(xpath="//h2[contains(text(),'My Plans')]")   // Added by Ravinder
	public WebElement txtHeaderMyPlans_1511;
	
	@FindBy(xpath="//div[@id='tabDiv']//div[@id='alertSection']")   // Added by Clint
	public WebElement txtSinglePlanAlertSection;
		
	@FindBy(xpath="//a[@class='navTertiaryLink' and contains(text(),'My Phone / Device')]")   // Added by Ravinder
	public WebElement lnkPhoneDevicesTertNav_1511;
	
	@FindBy(xpath=".//*[@id='servicecontent']")
	public WebElement txtServiceSection;
	
	@FindBy(xpath="//span[contains(text(),'Includes bonus')]")
	public WebElement txtAdditionalDataUsed;
	
	@FindBy(xpath="//a[contains(text(),'View all usage')]")
	public WebElement txtViewAllUsage;
	
	@FindBy(xpath = "//*[@class='icon-hamburger show-unauth']")
	public WebElement img3BarMenu;
	
	@FindBy(xpath = "//a[@role='menuitem' and @href='/olam/passthroughAction.myworld?actionType=Manage']")
	public WebElement lnkAccountAndServices;
	
	@FindBy(xpath = "//*[contains(@href,'logout')]")
	public WebElement btnLogoutRWD;
	
	//@FindBy(xpath="//a[@class='navTertiaryLink' and contains(text(),'Change My Plan')]")   // Added by Ravinder
	//public WebElement lnkChangeMyPlanTertiaryNav_1511;
		
	
	/* --------- Objects added by onshore - End here -------------- */
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_AccountOverview(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
