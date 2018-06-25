package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_HomePhoneService {

	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[text()='Overview']") 
	public WebElement lnkOverviewSecondaryNav;
	
	@FindBy(xpath="//a[contains(text(),'Move my service')] | //a[contains(text(),'Move Services')]")
	public WebElement lnkMoveMyService;
	
	@FindBy(xpath="//a[contains(text(),'Change Plan')]")
	public WebElement lnkChangePlan;
	
	@FindBy(xpath="//a[contains(text(),'Add/replace features')]")
	public WebElement lnkAddReplaceFeatures;
	
	@FindBy(xpath="//a[contains(text(),'Remove features')]")
	public WebElement lnkRemoveFeatures;
	
	@FindBy(xpath="//a[contains(text(),'Add additional line')][contains(@class,'inActiveLinkColor')")
	public WebElement lnkAddAdditionalLine;
	
	@FindBy(xpath="//a[contains(text(),'Disconnect service')][contains(@class,'inActiveLinkColor')]")
	public WebElement lnkDiscontinueService;
	
	@FindBy(xpath="//a[contains(text(),'View provider information')]")
	public WebElement lnkViewProvideInfo;
	
	@FindBy(xpath="//a[contains(text(),'Remove long distance')][contains(@class,'inActiveLinkColor')]")
	public WebElement lnkRemoveLongDistance;
	
	@FindBy(xpath="//a[contains(text(),'Phone feature support')]")
	public WebElement PhoneFeatureSupport;
	
	@FindBy(xpath="//a[contains(text(),'AT&T Real Yellow Pages')]")
	public WebElement lnkYellowPages;
	
	@FindBy(xpath="//a[contains(text(),'Go to U-verse Community Forum')]")
	public WebElement lnkGotoUverseCommunityForum;
	
	@FindBy(id="ViewBill") 
	public WebElement lnkViewBill;
	
	@FindBy(id="VoluntarySuspend") 
	public WebElement lnkVoluntarySuspend;
	
	@FindBy(id="TermsOfService") 
	public WebElement lnkTermsOfService;
	
	@FindBy(id="TroubleshootResolveTool") 
	public WebElement lnkTroubleshootAndResolve;
	
	@FindBy(id="ddUserDetails") 
	public WebElement lnkUserASMDropDown;
	
	@FindBy(xpath="//a[@title='View Video']") 
	public WebElement lnkTourBill;
	
	@FindBy(xpath="//a[contains(text(),'View and manage all U-verse Voice features')]") 
	public WebElement lnkManageFeatures;
	
	@FindBy(id="ViewVoiceUsageDetail") 
	public WebElement lnkRecentUsage;
	
	@FindBy(id="ViewVoiceBilledUsage") 
	public WebElement lnkViewBillUsage;
	
	@FindBy(id="CreateManageSubAccount") 
	public WebElement lnkCreateAcc;
	
	@FindBy(id="VoiceMailboxSetup") 
	public WebElement lnkSetUpVoicemail;
	
	@FindBy(id="ChangePin") 
	public WebElement lnkChangeMyPin;
	
	@FindBy(id="ManageVoiceMailboxSettings") 
	public WebElement lnkManageVoicemail;
	
	@FindBy(id="ViewVoicemail") 
	public WebElement lnkViewVoicemail;
	
	@FindBy(xpath="//*[contains(text(),'Manage my voicemail')]") 
	public WebElement lnkManageMyVoicemail;
	
	@FindBy(xpath="//*[contains(text(),'See caller ID on my TV')]") 
	public WebElement lnkSeeCallerID;
	
	@FindBy(xpath="//*[contains(text(),'Block unwanted callers')]") 
	public WebElement lnkBlockCallers;
	
	@FindBy(id="PhoneFeaturesStarCodes") 
	public WebElement lnkPhonefeaturesStarCodes;
	
	@FindBy(id="InternationalCallingRates") 
	public WebElement lnkInternationalCallingRates;
	
	@FindBy(id="ATTVoicemailViewer") 
	public WebElement lnkATTVoicemailViewer;
	
	@FindBy(id="UverseBatteryBackup") 
	public WebElement lnkUverseBatteryBackup;
	
	@FindBy(id="AddChangeUverseService") 
	public WebElement lnkAddChangeUverseService;
	
	@FindBy(id="CheckMyOrder") 
	public WebElement lnkCheckMyOrder;
	
	@FindBy(id="GoToUverseSupport") 
	public WebElement lnkGoToUverseSupport;
	
	@FindBy(xpath="//div[@class='myChannels']//a[@id='ViewChannelLineUp']") 
	public WebElement lnkViewChannelLineUp;
	
	
	/*---------------------------
	 * Element objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[text()='My Home Phone Service'] | //h1[contains(text(),'Local Service Details')] | //h1[contains(text(),'My Home Phone Service')]")
	public WebElement elmMyHomePhoneService;
	
	@FindBy(xpath="//strong[contains(text(),'Local Service')]")
	public WebElement elmLocalService;
	
	@FindBy(xpath="//strong[contains(text(),'Other Resources')]")
	public WebElement elmOtherResources;
	
	@FindBy(xpath="//h1[contains(text(),'Local Service Details')]")
	public WebElement elmLocalServiceDetails;
	
	@FindBy(xpath="//h2[contains(text(),'Plan Details')]")
	public WebElement elmPlanDetails;
	
	@FindBy(xpath="//h2[contains(text(),'Troubleshoot My Phone')]")
	public WebElement elmTroubleshoot;
	
	@FindBy(xpath="//h2[contains(text(),'Included Services & Features')]")
	public WebElement elmIncludeServiceFeature;
	
	@FindBy(xpath="//h1[text()='My Home Phone Service']")
	public WebElement elmHomePhoneService;
	
	@FindBy(xpath="//p[contains(text(),'We are sorry')]")
	public WebElement elmUROCK;
	
	@FindBy(xpath="//div/h2[contains(text(),'U-verse')]//sup")
	public WebElement txtUversePlanName;
	
	@FindBy(xpath="//p[contains(text(),'must first request access from the primary account holder')]|//p//strong[contains(text(),'Re')]")
	public WebElement txtVoicemailAccessFromPrimaryAccount;
	
	@FindBy(xpath="//h2[text()='Features']")
	public WebElement txtFeaturesDetails;
	
	@FindBy(xpath="//div[contains(@class,'footer')]")
	public WebElement txtFooter;
	
	@FindBy(xpath="//h1[text()='AT&T U-verse voluntary suspension of service']")
	public WebElement txtVoluntarySuspend;
	
	@FindBy(xpath="//h4[text()='AT&T U-verse Voice and TV Terms of Service']")
	public WebElement txtTermsOfService;

	@FindBy(xpath="//h2[contains(text(),'My voicemail')]")
	public WebElement txtMyVoicemail;
	
	@FindBy(xpath="//h2[contains(text(),'My Plan')]")
	public WebElement txtMyPlan;
	
	@FindBy(xpath="//*[contains(text(),'Video Details')]")
	public WebElement txtVideoDetails;
	
	@FindBy(xpath="(//p[contains(text(),'*')])[1]")
	public WebElement txtStaticTextUnderMRC;
	
	@FindBy(xpath="(//p[contains(text(),'*')])[2]")
	public WebElement txtStaticTextUnderMRCPopup;
	
	@FindBy(xpath="//div[@id='viewDetailPopupInner']") 
	public WebElement elmViewCostSummaryPopup;
	
	@FindBy(xpath="//div[@id='viewDetailPopupInner']//strong[contains(text(),'Total monthly voice cost')]") 
	public WebElement txtTotalMonthlyCostTextUnderPopup;
	
	@FindBy(xpath="//div[@id='viewDetailPopupInner']//strong[contains(text(),'$')]") 
	public WebElement txtTotalMonthlyCostAmountUnderPopup;
	
	@FindBy(xpath="//h1[contains(text(),'Home Phone')]") 
	public WebElement txtHomePhoneHeader;	
	
	@FindBy(xpath="//ul[@class='plDet']/li[text()='Call forwarding']") 
	public WebElement txtCallForwarding;
	
	@FindBy(xpath="//ul[@class='plDet']/li[text()='Call waiting & caller ID']") 
	public WebElement txtCallWaiting;
	
	@FindBy(xpath="//h1[contains(text(),'Log In to AT&T')]") 
	public WebElement txtUvPLogin;
	
	@FindBy(xpath="//ul[@class='plDet']/li[text()='Call filtering & blocking']") 
	public WebElement txtCallFilteringandBlocking;
	
	@FindBy(xpath="//a[contains(text(),'Move my service')]/parent::*/*[contains(@class,'newWindow')]")
	public WebElement imgNewWindowIconForMovemyservice;
	
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[contains(text(),'Change plan')]")
	public WebElement btnchangePlan;
	
	@FindBy(xpath="//button[text()='Close']")
	public WebElement btnClose;

	@FindBy(xpath="//img[@alt='Close Summary']")
	public WebElement btnCloseSummaryPopup;	
	
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[@id='VoicemailViewerApp']/parent::p/span[@class='newWindow']")
	public WebElement imgNewWindowIconUnderVoiceMail;
	
	@FindBy(xpath="//a[contains(text(),'Go to U-verse Community Forum')]/parent::dd/span[@class='newWindow']")
	public WebElement imgNewWindowIconForUverseCommunityForumLink;
	
	@FindBy(xpath="//img[contains(@alt,'Shop, Service, Support')]")
	public WebElement imgAttfooterLogo;
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	
	
	
	/*------- obejcts added by osnhore - Starts here ------------ */	
	
	@FindBy(xpath=".//*[@id='primary-content']/h2")   // Added by Ravinder
	public WebElement txtPlanNameHeader_1511;
	
	@FindBy(xpath="//p[contains(@class,'MarBot')]/strong[text()='Total monthly voice cost']")   // Added by Ravinder
	public WebElement txtHrTotalMonthlyVoiceCost_1511;	
	
	@FindBy(xpath="//p[contains(@class,'MarBot')]/strong[contains(text(),'$')]")   // Added by Ravinder
	public WebElement txtTotalMonthlyVoiceCost_1511;	
	
	@FindBy(xpath="//a[@id='summary' and @name='View cost summary']")   // Added by Ravinder
	public WebElement lnkViewCostSummary_1511;
	
	@FindBy(xpath="//a[@id='ChangePlan' and text()='Change plan']")    // Added by Ravinder
	public WebElement lnkChangePlan_1511;
	
	@FindBy(xpath="//p[contains(text(),'U-verse App')]")    // Added by Monica
	public WebElement lnkUverseApp;
	
	@FindBy(xpath="//p[contains(text(),'Get the myAT&T app')]")    // Added by Monica
	public WebElement lnkGetMyATTApp;
	
	
	@FindBy(xpath=".//*[@id='myDiscountsOffers']//strong[text()='My discounts & offers']")    // Added by Ravinder
	public WebElement txtMyDiscountOffersSection_1511;
	
	@FindBy(id="ViewVoiceUsageDetail")    // Added by Ravinder
	public WebElement lnkViewRecentUsage_1511;
	
	@FindBy(id="ViewVoiceBilledUsage")    // Added by Ravinder
	public WebElement lnkViewBilledUsage_1511;
	
	@FindBy(xpath="//a[text()='Tour my features']")    // Added by Ravinder
	public WebElement lnkTourMyFeatures_1511;
	
	@FindBy(id="UverseVoiceManageFeatures")    // Added by Ravinder
	public WebElement lnkViewManageAllVoiceFeatures_1511;
	
	@FindBy(xpath="//h2[text()='My voicemail']")    // Added by Ravinder
	public WebElement txtHrMyVoiceMail_1511;
	
	@FindBy(xpath="//a[text()='Create or manage voicemail accounts']")    // Added by Ravinder
	public WebElement lnkCreateManageVoiceMailAcc_1511;
	
	@FindBy(xpath="//a[text()='Set up voicemail']| id('SetupMyVoicemail')")    // Added by Ravinder
	public WebElement lnkSetupVoiceMail_1511;	
	
	@FindBy(id="SetupMyVoicemail")    // Added by Ravinder
	public WebElement lnkSetupVoiceMail_1604;
	
	@FindBy(xpath="//a[text()='Change my PIN']")    // Added by Ravinder
	public WebElement lnkChangeMyPin_1511;
	
	@FindBy(xpath="//a[text()='Manage voicemail settings']")    // Added by Ravinder
	public WebElement lnkManageVoicemailSettings_1511;
		
	@FindBy(xpath="//h2[text()='Get the most from my U-verse Voice']")    // Added by Ravinder
	public WebElement txtHrGetMostFromMyUverseVoice_1511;
	
	@FindBy(xpath=".//*[contains(@id,'Fatfooter')]//h2[text()='Manage My Account']")    // Added by Ravinder
	public WebElement txtManageMyAccount_1511;
		
	@FindBy(xpath=".//*[contains(@id,'Fatfooter')]//h2[text()='Home Phone Extras']")    // Added by Ravinder
	public WebElement txtHomePhoneExtras_1511;
	
	@FindBy(xpath=".//*[contains(@id,'Fatfooter')]//h2[text()='Home Phone Support']")    // Added by Ravinder
	public WebElement txtHomePhoneSupport_1511;
	
	@FindBy(xpath="//*[@class='section-title']/h2")    // Added by Ravinder
	public WebElement txtAccountName_1511;
	
	@FindBy(xpath=".//*[@id='primary-content']//li[@class='account-number']")    // Added by Ravinder
	public WebElement txtAccountNumber_1511;
	
	@FindBy(xpath=".//*[@id='usmModule']//a[@class='usm_click']")    // Added by Ravinder
	//@FindBy(xpath=".//*[@id='usmModule']//a[@class='usm_click'] | //div[@class='section-title']//li[@class='account-number']") //added by Clint
	public WebElement lnkUSM_1511;
	
	//@FindBy(xpath=".//*[@id='usmModule']//a[@class='usm_click']")    // Added by Ravinder
	@FindBy(xpath="//div[@class='section-title']//li[@class='account-number']") //added by Clint
	public WebElement lnkUSM_for_SingleAccount;
	
	@FindBy(xpath=".//*[@id='ie7noPad']//*[@class='group-title']//h2")    // Added by Ravinder
	public WebElement txtPlanNameWireline_1511;
	
	@FindBy(xpath="//*[@class='group-title']//h2[contains(text(),'Home Phone Details')]")    // Added by Ravinder
	public WebElement txtHomePhoneDetails_1511;
	
	@FindBy(xpath="//h3[text()='Included Features']")
	public WebElement txtIncludeFeature_1511;
	
	@FindBy(xpath="//h2[contains(text(),'Plan Details')]")   
	public WebElement txtHrPlanDetails;
	
	@FindBy(xpath="//h2[contains(text(),'Plan Details')]//span[contains(text(),'1') or contains(text(),'6') ][1]")   
	public WebElement txtPhoneNumberInPlanDetailsSection;
	
	
	@FindBy(xpath="//th[contains(text(),'Name')]")   
	public WebElement txtName;
	
	@FindBy(xpath="//th[contains(text(),'Member ID')]")   
	public WebElement txtMID;
	
	@FindBy(xpath="//h3[text()='Manage Services']")
	public WebElement txtManageServices_1511;
	
	@FindBy(xpath="//*[@class='group-title']//h2[contains(text(),'Additional Resources')] | //*[contains(text(),'Additional Resources')]")
	public WebElement txtAdditionalResources_1511;
	
	@FindBy(xpath="//a[contains(text(),'Call Forwarding')]")
	public WebElement lnkCallForwarding_1511;	
	
	@FindBy(xpath="//a[contains(text(),'Call Block/Call Screening')]")
	public WebElement lnkCallBlockScreening_1511;
	
	@FindBy(xpath="//a[contains(text(),'Stopping annoying or harassing calls')]")
	public WebElement lnkStopCalls_1511;	
	
	@FindBy(xpath="//h2[contains(text(),'BTN')]")
	public WebElement txtBTN;
	
	@FindBy(xpath="//*[contains(text(),'Phone Number')]")
	public WebElement txtPhoneNumber;
	
	/*------- obejcts added by osnhore - Ends here ------------*/
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_HomePhoneService(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
}
