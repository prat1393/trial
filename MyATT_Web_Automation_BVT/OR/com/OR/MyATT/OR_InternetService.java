package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_InternetService {
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[contains(@class,'emailFeatures')]//a[contains(text(),'Create or manage email account')] | //a[contains(text(),'Create/Manage Sub-Accounts')]") 
	public WebElement lnkCreateOrManageEmailAccount;
	
	@FindBy(xpath="//*[contains(@class,'emailFeatures')]//a[contains(text(),'Change password')]") 
	public WebElement lnkChangePassword;
	
	@FindBy(xpath="//a[text()='Digital TV' and contains(@href,'TvProductLandingPage')]") 
	public WebElement lnkDigitalTVsecondaryNav;
	
	@FindBy(xpath="//a[contains(text(),'View my current Internet usage')]") 
	public WebElement lnkInternetUsage;
	
	@FindBy(xpath="//a[contains(text(),'Help me find')]") 
	public WebElement lnkHelpMeFind;
	
	@FindBy(xpath="//a[contains(text(),'Troubleshoot my network device')]") 
	public WebElement lnkTroubleshoot;
	
	@FindBy(id="VoluntarySuspend") 
	public WebElement lnkSuspendCancelService;
	
	@FindBy(xpath="//button[text()='Close']") 
	public WebElement lnkClose;

	@FindBy(xpath="//a[@id='ViewInternetDataUsage']") 
	public WebElement lnkViewInternetDataUsage;
	
	@FindBy(xpath = "//a[@id='InternetSecurity' and text()='Download now']")
	public WebElement lnkDownloadNowBelowMcAfee;
	
	@FindBy(xpath="//a[@id='InternetSecurityLearnMore' and text()='Learn more']")
	public WebElement lnkLearnMoreBelowMcAfee;
	
	@FindBy(xpath="//a[@id='WiFiHotSpot' and text()='View Wi-Fi hotspot map']")
	public WebElement lnkViewWiFiHotspotMap;
	
	@FindBy(xpath = "//a[@id='ParentalControlLearnMore' and text()='Learn more']")
	public WebElement lnkLearnMoreBelowParentalControls;
	
	@FindBy(xpath ="//a[@id='TroubleshootResolveTool' and contains(text(),'Troubleshoot & Resolve')]")
	public WebElement lnkTroubleshootAndResolve;
	
	@FindBy(xpath ="//a[@id='TroubleshootResolveTool' and contains(text(),'Troubleshoot my network device')]")
	public WebElement lnkTroubleshootNetwork;
	
	@FindBy(xpath="//a[contains(text(),'Compare plans')]")
	public WebElement lnkCompare;
			
	@FindBy(xpath="//a[contains(text(),'Reset DSL Network Password')]")
	public WebElement lnkReset;
	
	@FindBy(xpath="//a[contains(text(),'Upgrade plan')]")
	public WebElement lnkUpgrade;

	@FindBy(xpath="//a[contains(text(),'Test your Internet speed')]")
	public WebElement lnkInternetSpeed;
	
	@FindBy(xpath="//a[contains(text(),'AT&T Internet Security Suite')]")
	public WebElement lnkIntSecurity;
	
	@FindBy(xpath="//dd/a[contains(text(),'Parental Controls')]")
	public WebElement lnkParental;
	
	@FindBy(xpath="//a[contains(text(),'att.net Homepage')]")
	public WebElement lnkAttHomepage;
	
	@FindBy(xpath="//a[contains(text(),'att.net Toolbar')]")
	public WebElement lnkAttToolbar;
	
	@FindBy(xpath="//a[contains(text(),'Yahoo! Messenger')]")
	public WebElement lnkYahooMsg;
	
	@FindBy(xpath="//a[contains(text(),'Flickr')]")
	public WebElement lnkFlickr;
	
	@FindBy(xpath="//a[contains(text(),'Wi-Fi Hotspot List')]")
	public WebElement lnkHotspotp;

	@FindBy(xpath="//a[contains(text(),'How to troubleshoot slow speeds')]")
	public WebElement lnktroubleshoot;

	@FindBy(xpath="//a[contains(text(),'Download the Troubleshoot & Resolve Tool')]")
	public WebElement lnkDownload;
	
	@FindBy(xpath="//a[contains(text(),'Connect to a Wi-Fi home network')]")
	public WebElement lnkConnectWifi;
	
	@FindBy(xpath="//a[contains(text(),'Browse internet service support topics')]")
	public WebElement lnkServSupport;
	
	@FindBy(id="GoToUverseSupport")
	public WebElement lnkGoToUverseSupportInFooterSection;
	
	@FindBy(xpath="//a[contains(text(),'Connect to my home network')]")
	public WebElement lnkConnectHomeNetworkInFooterSection;
	
	@FindBy(id="CheckMyOrder")
	public WebElement lnkCheckMyOrderInFooterSection;
	
	@FindBy(id="UverseEmailSupport")
	public WebElement lnkGetHelpWithEmail;
	
	@FindBy(id="WiFiHotSpot")
	public WebElement lnkFindWiFiHotspot;
	
	@FindBy(id="CommunityForums")
	public WebElement lnkInternetCommunity;
	
	@FindBy(xpath="//a[contains(text(),'Go to my att.net homepage ')]")
	public WebElement lnkAttHomepageNew;
	
	@FindBy(xpath="//a[contains(text(),'Get att.net toolbar')]")
	public WebElement lnkAttToolbarNew;
	
	@FindBy(id="PortForwardingTool")
	public WebElement lnkPortForwarding;
	
	@FindBy(id="TermsOfService")
	public WebElement lnkViewTermsOfService;
	
	@FindBy(id="MoveUverseService")
	public WebElement lnkMoveMyService;
	
	@FindBy(id="ViewBill")
	public WebElement lnkViewAndPayMyBill;
	
	@FindBy(id="VoluntarySuspend")
	public WebElement lnkSuspendService;
	
	@FindBy(id="CheckEmail")
	public WebElement lnkDCheckEmail;
	
	@FindBy(xpath="//a[contains(text(),'Find my Wi-fi hotspot')]")
	public WebElement lnkFindWifi;
	
	@FindBy(xpath="//p[contains(text(),'Get McAfee')]")
	public WebElement lnkMcAfee;
	
	@FindBy(xpath="//a[contains(text(),'Set up parental controls')] | //p[contains(text(),'Set up parental controls')]")
	public WebElement lnkParentalControl;
	
	@FindBy(xpath="//a[@name='See all discounts']")
	public WebElement lnkSeeAllDiscounts;
	
	@FindBy(xpath="//a[@id='ViewWiFiNameandpwd']")
	public WebElement lnkViewMyWifiNameAndPassword;
	
	@FindBy(xpath="//a[@id='DownloadInternetSecurityInfo']")
	public WebElement lnkDownloadInternetSecurity;
	
	@FindBy(xpath="//a[contains(@href,'name=internetsecurity.feature.att.downloadNow')]//img[contains(@src,'img/addons/Promo_ISS') and @alt='AT&T Internet Security Suite powered by McAfee']")
	public WebElement lnkDownloadATTInternetSecuritySuite;
	
	@FindBy(xpath="//a[contains(text(),'Manage email settings')]")
	public WebElement lnkManageEmailSettings;
	
	@FindBy(xpath="//a[contains(text(),'Check email')]")
	public WebElement lnkCheckEmail;
	
	@FindBy(xpath="//a[contains(text(),'Create/Manage Sub-Accounts')]")
	public WebElement lnkCreateManageSubAccounts;


	
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	@FindBy(xpath="//img[@alt='Click here for more information on data usage.']") 
	public WebElement imgToolTip;
	
	@FindBy(xpath="//div[@class='devImg']") 
	public WebElement imgKey;
	
	@FindBy(xpath = "//img[contains(@title,'McAfee® Security Software')]")
	public WebElement imgMcAfeeSecurity;
	
	@FindBy(xpath="//img[contains(@title,'Largest Wi-Fi Network')]")
	public WebElement imgWiFi;
	
	@FindBy(xpath="//img[@title='Parental Controls']")
	public WebElement imgParentalControls;
	
	@FindBy(xpath="//img[@alt='Change Plan']")
	public WebElement imgChangePlan;
	
	@FindBy(xpath="//a[@title='CloseSummary'] | //a[@id='closeSummary']")
	public WebElement imgCloseSummary;
	
	@FindBy(xpath="//a[contains(text(),'Move my service')]/parent::*/*[contains(@class,'newWindow')]")
	public WebElement imgNewWindowIconForMovemyservice;
	
	@FindBy(xpath="//a[contains(text(),'Check email')]/parent::*/*[contains(@class,'newWindow')] | //a[contains(text(),'Check email')]")
	public WebElement imgNewWindowIconForCheckEmail;
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	
	/*---------------------------
	 *button objects add below
	 *---------------------------*/
	@FindBy(id="ChangePlan") 
	public WebElement btnChangePlanInInternetServices;
	
	@FindBy(xpath="//a[1][contains(@class,'closeModal')]//img[@alt='Close']")
	public WebElement imgClose;
	
	
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	@FindBy(id="ddUserDetails")
	public WebElement lstUserDetailsDropDown;
	
	
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	
	
	
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//table//th[contains(text(),'Email Address')] | //table[contains(@summary,'List of email accounts')]//th[text()='Name']") 
	public WebElement txtName_emailSection;
	
	@FindBy(xpath="//h3[text(),'OTT']") 
	public WebElement txtOTTDummy;
	
	@FindBy(xpath="//table[contains(@summary,'List of email accounts')]//th[text()='Email Address']") 
	public WebElement txtEmailAddress_emailSection;
	
	@FindBy(xpath="//*[contains(@class,'emailFeatures')]//*[contains(text(),'My Email Addresses')]")
	public WebElement txtMyEmailAddresses;
	
	@FindBy(xpath="//h1[contains(text(),'My Internet Service')]")
	public WebElement txtMyInternetService;
	
	@FindBy(xpath="//h2[contains(text(),'Plan Details')]")
	public WebElement txtPlanDetailsSection;
	
	@FindBy(xpath="//span[contains(text(),'null monthly data allowance')]")
	public WebElement txtMonthlyData;
	
	@FindBy(id="pagetip2")
	public WebElement txtToolTipText;	
	
	@FindBy(xpath="//*[contains(text(), 'Get the most from my U-verse Internet')]")
	public WebElement txtGetTheMost;
	
	@FindBy(xpath="//p[contains(text(),'Because you have a special plan for AT&T employees')]|//p[contains(text(),'process changes')]")
	public WebElement txtIneligibleCustomerNoteEmployee;
	
	@FindBy(xpath="//td[@class='eAddress']//em")
	public WebElement txtPrimary;
	
	@FindBy(xpath="//div[@class='devTxt']")
	public WebElement txtNetworkSection;
	
	@FindBy(xpath="//div[@class='devTxt']/p")
	public WebElement txtQuickKey;

	@FindBy(xpath="//div[contains(@data-tab-label,'Learn how to find your Wi-Fi network name and password ')]")
	public WebElement txtShortDescDiv;
	
	@FindBy(xpath="//h3[text()='Voluntary Suspend']")
	public WebElement txtVolenteerSuspend;
	
	@FindBy(xpath="//div[@id='primary-content']//p[contains(text(),'ve temporarily suspended your service.')]")
	public WebElement txtSuspendedMessage;
	
	@FindBy(xpath="//div[@id='primary-content']//p[contains(text(),'t able to make changes to your service online right now.')]")
	public WebElement txtSuspendedMessage2;
	
	@FindBy(xpath="//div[@id='primary-content']//p[contains(text(),'t able to make changes to your service online right now.')]")
	public WebElement txtIncludedServicesAndFeaturesSection;
	
	@FindBy(xpath = "//h2[text()='Included Services & Features']")
	public WebElement txtIncludedServicesHeader;
	
	@FindBy(xpath = "//p[contains(text(),'McAfee® Security Software:')]")
	public WebElement txtMcAfeeSecuritySoftware;
	
	@FindBy(xpath = "//p[contains(text(),'Largest Wi-Fi Network')]")
	public WebElement txtAccessToNationsLargestNetwork;
	
	@FindBy(xpath="//p[contains(text(),'Parental Controls')]")
	public WebElement txtParentalControls;

	@FindBy(xpath="//h2[contains(text(),'Additional Resources')]")
	public WebElement txtAddResources;

	@FindBy(xpath="//h2[contains(text(),'Email Accounts')]")
	public WebElement txtEmailAcc;

	@FindBy(xpath = "//p[contains(text(),'user is not eligible to change or add service in HardRock')]")
	public WebElement txtHardRockError;
	
	@FindBy(xpath = "//h2[contains(text(),'Manage My Account')]")
	public WebElement txtManageAccountInFoterSection;
	
	@FindBy(xpath = "//h2[contains(text(),'Internet Extras')]")
	public WebElement txtInternetExtrasInFooterSection;
	
	@FindBy(xpath = "//h2[contains(text(),'U-verse Internet Support')]")
	public WebElement txtUverseInternetSupportInFooterSection;
	
	@FindBy(xpath = "//p[contains(text(),'Residential gateway router')]")
	public WebElement txtResidentialGatewayRouter;
	
	//@FindBy(xpath = "//p[contains(text(),'user is not eligible to change or add service in HardRock')]")
	//public WebElement txtHardRockError;
	
	@FindBy(xpath="//h2[contains(text(),'equipment')]")
	public WebElement txtEquip;
	
	@FindBy(id="ViewWiFiNameandpwd")
	public WebElement txtWiFiName;
	
	@FindBy(xpath="//h1[contains(text(),'Internet Details')]")
	public WebElement txtInternetDetails;
	
	@FindBy(xpath="//p[contains(text(),'AT&T U-verse Internet')]")
	public WebElement txtCostSummary_ATTuverseInternet;
	
	@FindBy(xpath="//p[contains(text(),'AMD ST Over The Top Services')]")
	public WebElement txtCostSummary_OTT;
	
	@FindBy(xpath="//p[contains(text(),'Total Video Streaming Services Taxes')]")
	public WebElement txtCostSummary_VideoStreaming;
	
	@FindBy(xpath="//h2[contains(text(),'inactive')]")
	public WebElement txtInactive;
	
	@FindBy(xpath="//h2[contains(text(),'My equipment')]")
	public WebElement txtMyEquipment;
	
	/*---------------------------
	 * Frame objects
	 *---------------------------*/
	@FindBy(xpath="//iframe[contains(@src,'showAllPromoModalInternetLanding.')]")
	public WebElement frmDiscountAndOffers;
	
	
	/*------- obejcts added by osnhore - Starts here ------------ */	
	
	@FindBy(xpath=".//*[@id='primary-content']/h2")   // Added by Ravinder
	public WebElement txtPlanNameHeader_1511;
	
	@FindBy(xpath="//p[contains(@class,'MarBot')]/strong[contains(text(),'Total monthly')]")   // Added by Ravinder
	public WebElement txtHrTotalMonthlyInternetCost_1511;
	
	@FindBy(xpath="//p[contains(@class,'MarBot')]/strong[contains(text(),'*')]")   // Added by Ravinder
	public WebElement txtTotalMonthlyInternetCost_1511;
	
	@FindBy(xpath=".//*[@linkname='View cost summary']")   // Added by Ravinder
	public WebElement lnkViewCostSummary_1511;
	
	@FindBy(xpath=".//*[@id='myDiscountsOffers']//strong[text()='My discounts & offers']")   // Added by Ravinder
	public WebElement txtMyDiscountOffersSection_1511;
	
	@FindBy(xpath=".//*[@id='primary-content']//h2[text()='Plan Details']")   // Added by Ravinder
	public WebElement txtHrPlanDetails_1511;
	
	@FindBy(xpath="//*[contains(@class,'myChannels')]//strong[contains(text(),'U-verse')]")   // Added by Ravinder
	public WebElement txtPackageName_1511;
	
	@FindBy(xpath=".//*[@id='primary-content']//h2[text()='My equipment']")   // Added by Ravinder
	public WebElement txtHrMyEquipment_1511;
	
	@FindBy(xpath="//*[contains(@class,'devTxt')]//strong[contains(text(),'Help & support')]")   // Added by Ravinder
	public WebElement txtHrHelpnSupport_1511;
	
	@FindBy(xpath="//h2[text()='Get the most from my U-verse Internet']")   // Added by Ravinder
	public WebElement txtHrGetMostFromMyUverseInternet_1511;
	
	@FindBy(xpath="//*[contains(text(),'My equipment')]/parent::*//*[contains(text(),'Help & support')]")   
	public WebElement txtHelpAndSupportForEquipment;
	
	
	/*------- obejcts added by osnhore - Ends here ------------*/
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_InternetService(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
