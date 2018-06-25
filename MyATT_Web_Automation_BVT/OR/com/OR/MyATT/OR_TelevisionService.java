package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_TelevisionService {

	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	@FindBy(xpath="//img[@alt='Change Plan']")
	public WebElement imgChangePlanOnTVService;
	
	@FindBy(xpath="//img[@title='Home DVR']")
	public WebElement imgTotalHomeDVR;
	
	@FindBy(xpath="//img[@title='U-verse TV']")
	public WebElement imgUverseTV;
	
	@FindBy(xpath="//img[@title='Parental Controls']")
	public WebElement imgParentalControls;
	
	@FindBy(xpath="//img[@title='MultiView']")
	public WebElement imgMultiView;

	@FindBy(xpath="//dd/a[@id='TroubleshootResolveTool']//parent::dd/span[@class='Wrench']")
	public WebElement imgWrenchMyEqui;

	@FindBy(xpath="//dd/a[@id='TroubleshootResolveTool']//parent::dd/span[@class='Wrench']")
	public WebElement imgWrenchSup;
	
	@FindBy(xpath="//a[@id='ViewChannelLineUp']/span[@class='newWindow']")
	public WebElement imgNewWinViewChann;
	
	@FindBy(xpath="//a[@id='OrderRemoteControl']/span[@class='newWindow']")
	public WebElement imgNewWinOrderRemote;
	
	@FindBy(xpath="//a[@id='DownloadUverseRemoteApp']/span[@class='newWindow']")
	public WebElement imgNewWindowLearnRemote;
	
	@FindBy(xpath="//a[@id='ManageDVR']//parent::p/span[@class='newWindow']")
	public WebElement imgNewWindowDVR;
	
	@FindBy(xpath="//a[@id='UverseOnline']//parent::p/span[@class='newWindow']")
	public WebElement imgNewWindowGoToUverse;
	
	@FindBy(xpath="//a[@id='TVParentalControls']//parent::p/span[@class='newWindow']")
	public WebElement imgNewWindowLearn;
	
	@FindBy(xpath="//a[@id='UverseAppForSmartPhone']//parent::dd/span[@class='newWindow']")
	public WebElement imgNewWindowDwnApp;
	
	@FindBy(xpath="//a[@id='PayPerViewEvents']//parent::dd/span[@class='newWindow']")
	public WebElement imgNewWindowGoToPay;
	
	@FindBy(xpath="//a[@id='UverseMovieCoupons']//parent::dd/span[@class='newWindow']")
	public WebElement imgNewWindowGetMovie;
	
	@FindBy(xpath="//img[@alt='Close Summary']")
	public WebElement imgCloseSummary;
	
	@FindBy(xpath="//a[contains(text(),'Move my service')]/parent::*/*[contains(@class,'newWindow')]")
	public WebElement imgNewWindowIconForMovemyservice;
	
	
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/

	
	/*---------------------------
	 *  button objects add below
	 *---------------------------*/
	@FindBy(id="ChangePlan")
	public WebElement btnChangePlan;
	
	@FindBy(id="saveButton")
	public WebElement btnContinue;
	
	@FindBy(xpath="//button[contains(text(),'Watch TV now')]")
	public WebElement btnWatchTVNow;
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	@FindBy(id="attServiceName")
	public WebElement lstATTservice;
	
	
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	
	
	
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/

	@FindBy(xpath="//div[@id='primary-content']/h2")
	public WebElement txtMyPlanHeader;
	
	@FindBy(xpath="//h1[contains(text(),'My TV Service')]")
	public WebElement txtMyTVService;
	
	@FindBy(xpath="//*[contains(text(),'My Video Orders:')]")
	public WebElement txtMyVideoOrders;
	
	@FindBy(xpath="//*[contains(text(),'Included Services & Features')]")
	public WebElement txtIncludedServicesAndFeatures;

	@FindBy(xpath="//p[contains(text(),'Total Home DVR')]")
	public WebElement txtTotalHomeDVR;

	@FindBy(xpath="//p[contains(text(),'U-verse.com')]")
	public WebElement txtUverseCom;
	
	@FindBy(xpath="//p[contains(text(),'Parental Controls')]")
	public WebElement txtParentalControls;
	
	@FindBy(xpath="//h2[contains(text(),'Plan Details')]")
	public WebElement txtPlanDetails;
	
	@FindBy(xpath="//h2[text()='My programming']")
	public WebElement txtMyProgramming;
	
	@FindBy(xpath="//div[@id='modalHdr']")
	public WebElement txtPersonalise;
	
	@FindBy(xpath="//h1[contains(text(),'Cancel or suspend AT&T U-verse service')]")
	public WebElement txtCancelSuspend;
	
	@FindBy(xpath="//div[@id='AtgAccordion']")
	public WebElement txtAccordin;
	
	@FindBy(xpath="//div[contains(@class,'mod-service-landing')]")
	public WebElement txtAccountSection;
	
	@FindBy(xpath="//h1[text()='Check Order Status']")
	public WebElement txtChkOrderStatus;
	
	@FindBy(xpath="//h1[text()='U-verse TV']")
	public WebElement txtUverseDetails;
	
	@FindBy(xpath="	//h2[contains(text(),'U-verse')  and contains(@class,'font22')]")
	public WebElement txtPlanName;
	
	@FindBy(xpath="//h4[text()='AT&T U-verse Voice and TV Terms of Service']")
	public WebElement txtUverseVoiceTermsOf;
	
	@FindBy(xpath="//p[text()='user is not eligible to change or add service in HardRock because of in-flight order (order not provisioned) HRCKUM02']")
	public WebElement txtErrorHardRock;
	
	@FindBy(xpath="//p[contains(text(),'Your account is in collections')]")
	public WebElement txtNoteColour;
	
	@FindBy(xpath="//*[contains(text(),'next bill')]")
	public WebElement txtTaxesCreditsAdjustments;
	
	@FindBy(xpath="//p[contains(text(),'Promo')]")
	public WebElement txtPromoDummy;
	
	@FindBy(xpath="//strong[contains(text(),'Note')] | //p[contains(text(),'Note')]")
	public WebElement txtNote;
	
	@FindBy(xpath="//p[contains(text(),'You have an order pending')]")
	public WebElement txtOrderPendingMessage;
	
	@FindBy(xpath="//strong[contains(text(),'My U-verse receivers')]")
	public WebElement txtMyUverseReceivers;
	
	@FindBy(xpath="//li[contains(text(),'U-verse TV receivers with DVR')]")
	public WebElement txtUverseReceiverWithDVR;
	
	@FindBy(xpath="//li[contains(text(),'U-verse TV receivers:')]")
	public WebElement txtUverseTVReceivers;
	
	@FindBy(xpath="//li[contains(text(),'U-verse TV receivers - wireless')]")
	public WebElement txtUverseTVReceiversWireless;
	
	@FindBy(xpath="//h2[contains(text(),'U-verse TV Support')]")
	public WebElement txtUverseTVSupport;
	
	@FindBy(xpath="//div/p[contains(text(),'Tune in to Channel 401/1401 to explore the latest TV features.')]")
	public WebElement txtTune;
	
	@FindBy(xpath="//div/p[contains(text(),'U-verse.com')]")
	public WebElement txtUversecom;
	
	@FindBy(xpath="//div/p[contains(text(),'Multi-View')]")
	public WebElement txtMultiView;
	
	@FindBy(xpath="//h2[text()='My Equipment'] | //h2[contains(text(),'My equipment & features')]")
	public WebElement txtMyEquipment;
	
	@FindBy(xpath="//p/strong[@class and contains(text(),'monthly TV cost')]")
	public WebElement txtMonthlyCost;
	
	@FindBy(xpath="//div[@class='msPODshadowRightAddonHub']//p[contains(text(),'*')]")
	public WebElement txtDisclaimer;
	
	@FindBy(xpath="//*[contains(text(),'My remote controls')]")
	public WebElement txtMyRemoteControls;
	
	@FindBy(xpath="//div[@class='myChannels']")
	public WebElement txtProgrammingSect;
	
	@FindBy(id="LearnToProgramRemote")
	public WebElement txtProgRemote;
	
	@FindBy(id="DownloadUverseRemoteApp")
	public WebElement txtRemoteApp;
	
	@FindBy(xpath="//h1[contains(text(),'remote control')]")
	public WebElement txtRemoteControls;
	
	@FindBy(xpath="//p[contains(text(),'You have')]")
	public WebElement txtYouHaveApplied;
	
	@FindBy(xpath="//div[@class='float-left']/h1[contains(text(),'Equipment')]")
	public WebElement txtEquipment;
	
	@FindBy(xpath="//div[@id='linkrailset2']/h2[text()='Explore DIRECTV options']") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement txtexploreDirectTvoptions;
	
	@FindBy(xpath="//div[@id='linkrailset3']/h2[text()='Get help & support']") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement txtGethelpandsupport ;
	
	@FindBy(xpath="//h2[contains(text(),'Support')]") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement txtSupport ;
	
	@FindBy(xpath="//div[@id='linkrailset3']/h2[text()='Support']") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement txtDisconnect ;
	
	@FindBy(xpath="//h2[contains(text(),'Get the most from')]") //Added by Clint - 13-Oct-2015
	public WebElement txtGetTheMostFrom;
	
	@FindBy(xpath="//h1[contains(text(),'AT&T U-verse Channel Lineup')]")  //Added by Hiral
	public WebElement txtAtTChannelLineup;

	@FindBy(xpath="//h1[contains(text(),'Log In to AT&T')]")  //Added by Hiral
	public WebElement txtManageDVRLogin;
	
	@FindBy(xpath="//p/strong[contains(text(),'My applied discounts')]")
	public WebElement txtAppliedDiscounts;
	
	@FindBy(xpath="//p/strong[contains(text(),'My applied discounts')]/p[@aria-expanded]")
	public WebElement txtExpandedDiscounts;
	
	@FindBy(xpath="//p[contains(text(),'applied discounts')]")
	public WebElement txtAllDiscounts;
	
	@FindBy(xpath="//p/strong[contains(text(),'My applied discounts')]/p[contains(text(),'days left')]")
	public WebElement txtDiscountsCounter ;
	
	/*---------------------------	
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//*[contains(text(),'My Video Orders:')]/a[contains(text(),'Recent')]")
	public WebElement lnkRecent;
	
	@FindBy(xpath="//*[contains(text(),'My Video Orders:')]/a[contains(text(),'Billed')]")
	public WebElement lnkBilled;
	
	@FindBy(xpath="//a[contains(text(),'Access my DVR')]")
	public WebElement lnkAccessMyDVR;
	
	@FindBy(xpath="//a[contains(text(),'Go to U-verse.com')]")
	public WebElement lnkGoToUVerseCom;
	
	@FindBy(xpath="//a[contains(@id,'TroubleshootResolveTool')]")
	public WebElement lnkTroubleShootAndResolve;
	
	@FindBy(xpath="//a[contains(text(),'Learn more')]")
	public WebElement lnkLearnMore;

	@FindBy(xpath="//*[contains(@id,'additional')]//a[contains(text(),'Troubleshoot & Resolve')][contains(@href,'TV_Landing')]")
	public WebElement lnkTroubleshootAndResolveBottomSide;
	
	@FindBy(xpath="//a[contains(text(),'Move my service')] | //a[contains(text(),'Move Service')]")
	public WebElement lnkMoveMyService;
	
	@FindBy(xpath="//a[@id='LearnToReturnEquipment']")
	public WebElement lnkReturnEquipment;
	
	@FindBy(xpath="//a[@id='ManageEquipment']")
	public WebElement lnkOrderReceiver;
	
	@FindBy(id="FacebookOnTV")
	public WebElement lnkAccessFBOnTV;
	
	@FindBy(xpath="//a[@id='ViewBill']")
	public WebElement lnkViewAndPay; 
	
	@FindBy(id="VoluntarySuspend")
	public WebElement lnkSuspend;
	
	@FindBy(id="TermsOfService")
	public WebElement lnkTerms;
	
	@FindBy(id="ManageDVR")
	public WebElement lnkManageDVR;
	
	@FindBy(id="UverseTVDetail")
	public WebElement lnkUverseTVDetail;
	
	@FindBy(id="MyEquipment")
	public WebElement lnkOrderReceivers;
	
	@FindBy(xpath="//a[contains(text(),'View All')]")
	public WebElement lnkViewAll;
	
	@FindBy(xpath="//dd[@class='botMar7']//a[contains(text(),'Terms of Service')] | //a[@id='DTVTermsOfService']")
	public WebElement lnkTermsOfService;
	
	@FindBy(id="CheckAppointmentStatus")
	public WebElement lnkCheckAppointnmentStatus;
	
	@FindBy(id="MultiViewLearnMore")
	public WebElement lnkMultiViewLearn;
	
	@FindBy(id="TVParentalControls")
	public WebElement lnkParentalLearn;
	
	@FindBy(xpath="//div[contains(@class,'myAccountList')]")
	public WebElement lnkASM;
	
	@FindBy(id="ViewChannelLineUp")
	public WebElement lnkViewChannel;
	
	@FindBy(id="OrderRemoteControl")
	public WebElement lnkOrderRemote;
	
	@FindBy(id="DownloadUverseRemoteApp")
	public WebElement lnkLearnremote;
	
	@FindBy(id="UverseOnline")
	public WebElement lnkGoToUverse;
	
	@FindBy(id="TVCommunityForum")
	public WebElement lnkGoToTVCommunnity;
	
	@FindBy(id="TVParentalControls")
	public WebElement lnkLearn;
	
	@FindBy(id="UverseAppForSmartPhone")
	public WebElement lnkDwnApp;
	
	@FindBy(id="PayPerViewEvents")
	public WebElement lnkToPay;
	
	@FindBy(id="UverseMovieCoupons")
	public WebElement lnkMovieCoupon;
	
	@FindBy(id="summary")
	public WebElement lnkSummary;
	
	@FindBy(id="UverseMovieCoupons")
	public WebElement lnkUverseMovieCoupons;
	
	@FindBy(id="UverseTVDetail")
	public WebElement lnkVideoOrder;
	
	@FindBy(xpath="//a[contains(text(),'Order receivers')]")
	public WebElement lnkOrderRec;
	
	@FindBy(xpath="//a[contains(text(),'U-verse_Easy_Remote_app_DBPD')]")
	public WebElement lnkEasyApp;
	
	@FindBy(id="summary")
	public WebElement lnkViewCostSummary;
	
	@FindBy(id="UverseAppForSmartPhone")
	public WebElement lnkDownloadUverseApp;
	
	@FindBy(xpath="//a[@name='Add channels or packages']")
	public WebElement lnkAddChannelPackage;
	
	@FindBy(xpath="//a[contains(text(),'Find out how')]") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement lnkFindoutHow ;
	
	@FindBy(xpath="//a[contains(text(),'Compare TV Packages')] | //a[@id='CompareDTVPackages']") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement lnkCompareTVPackages ;
	
	@FindBy(xpath="//a[contains(text(),'Watch TV now')] | //a[contains(text(),'DTV on Demand')]") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement lnkWatchTVnow ;
	
	@FindBy(xpath="//a[contains(text(),'Parental')]") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement lnkParental ;
	
	@FindBy(xpath="//a[contains(text(),'Learn how to refer friends')] | //a[contains(text(),'Refer A Friend')]") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement lnkLearnhowtoreferfriends ;
	
	@FindBy(xpath="//a[@alt='View details']") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement lnkViewDetails ;
	
	@FindBy(xpath="//a[contains(text(),'View programming extras')] | //a[contains(text(),'Channel Line up')]") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement lnkViewProgrammingExtras ;
	
	@FindBy(xpath="//a[contains(text(),'Troubleshoot my Equipment or Service')] | //a[@id='TroubleshootResolveTool']") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement lnkTroubleshootEquipment ;
	
	@FindBy(xpath="//a[contains(text(),'Check or reschedule appointment')] | //a[contains(text(),'Check or change my appointment')]") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement lnkRescheduleappointment ;
	
	@FindBy(xpath="//a[contains(text(),'Check order status')]") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement lnkcheckorderstatus ;
	
	@FindBy(xpath="//a[contains(text(),'Go to DIRECTV community forum')] | //a[contains(text(),'Go to TV community forum')]") 			// Added by Hiral Jogi - 10/08/2015
	public WebElement lnkDirectvforum ;
	
	@FindBy(xpath="//div[@class='myChannels']//a[@id='ViewChannelLineUp']") //Added by Clint 13-Oct-2015
	public WebElement lnkViewChannelLineUp;
	
	@FindBy(xpath="//div[@class='myChannels']//a[@id='UverseTVDetail'][contains(text(),'View recent video orders')] | //a[contains(text(),'View recent video orders')]") //Added by Clint 13-Oct-2015
	public WebElement lnkViewRecentVideoOrders;
	
	@FindBy(xpath="//strong[contains(text(),'My remote controls')]//parent::p//parent::div//a[@id='LearnToProgramRemote']") //Added by Clint 13-Oct-2015
	public WebElement lnkLearnToProgramRemote;
	
	@FindBy(xpath="//strong[contains(text(),'My remote controls')]//parent::p//parent::div//a[@id='OrderRemoteControl']") //Added by Clint 13-Oct-2015
	public WebElement lnkOrderNewRemote;
	
	@FindBy(xpath="//strong[contains(text(),'My remote controls')]//parent::p//parent::div//a[@id='DownloadUverseRemoteApp']") //Added by Clint 13-Oct-2015
	public WebElement lnkUseMyMobileDeviceAsUverseRemote;
	
	@FindBy(xpath="//*[contains(text(),'My programming')]//parent::div//a") // Added by Hiral Jogi 
	public WebElement lnkMyProgrammingcontent;
	
	@FindBy(xpath="//p[contains(text(),'Get the AT&T U-verse app')] | //p[contains(text(),'U-verse App')]")
	public WebElement lnkUverseApp;
	
	@FindBy(xpath="//p[contains(text(),'AT&T') and contains(text(),'app')]")
	public WebElement lnkGetTheMyATTApp;
	
	@FindBy(xpath="//a//p[contains(text(),'U-verse') and contains(text(),'app')]")
	public WebElement lnkParentalControl;
	
	@FindBy(xpath="//p[contains(text(),'Get the easy remote app')]")
	public WebElement lnkEasyRemoteApp;
	
	@FindBy(xpath="//a[contains(text(),'TV support')]")
	public WebElement lnkGetUverseTVSupport;
	
	@FindBy(xpath="//p[contains(text(),'Get the AT&T U-verse app')]")
	public WebElement lnkGetUverseApp;
		
	
	/*------- obejcts added by osnhore - Starts here ------------ */	
	
	@FindBy(xpath="//p[contains(@class,'MarBot')]/*[text()='Total monthly TV cost']")   // Added by Ravinder
	public WebElement txtHrTotalMonthlyTVCost_1511;
	
	@FindBy(xpath="//p[contains(@class,'MarBot')]/*[contains(text(),'$')]")   // Added by Ravinder
	public WebElement txtTotalMonthlyTVCost_1511;
	
	@FindBy(xpath="//a[@name='View cost summary']")   // Added by Ravinder
	public WebElement lnkViewCostSummary_1511;
	
	@FindBy(xpath=".//*[@id='myDiscountsOffers']")   // Added by Ravinder
	public WebElement txtMyDiscountOffersSection_1511;
	
	@FindBy(xpath=".//*[@class='channnelsList']//strong")   // Added by Ravinder
	public WebElement txtPackageName_1511;
	
	@FindBy(xpath=".//img[@id='premiumChannels'] | //*[contains(text(),'My additional channels')]")   // Added by Ravinder
	public WebElement imgChannels_1511;	
	
	@FindBy(xpath="//*[@id='myChannels']//strong[contains(text(),'My additional channels')]")   // Added by Ravinder
	public WebElement txtMyAdditionalChannels_1511;
	
	@FindBy(xpath="//button[text()='Watch TV now']")   // Added by Ravinder
	public WebElement btnWatchTVNow_1511;
	
	@FindBy(id="ManageDVR")
	public WebElement lnkManageDVR_1511;
	
	@FindBy(xpath="//a[@id='UverseTVDetail' and text()='View recent video orders']")
	public WebElement lnkViewRecentVideoOrders_1511;	
	
	@FindBy(xpath="//h2[contains(text(),'Get the most from my U-verse TV')]")
	public WebElement txtGetTheMostFromMyTV;
	
	@FindBy(xpath="//*[contains(text(),'Manage My Account')]")
	public WebElement txtManageMyAccount_1511;
	
	@FindBy(xpath=".//*[@id='ajaxDiv1 Fatfooter']//h2[text()='TV Extras']")
	public WebElement txtTVExtras_1511;	
	

	@FindBy(xpath="//h2[contains(text(),'My discounts & offers')]")
	public WebElement txtMyAccountAndDisccount;	
	

	/*------- obejcts added by osnhore - Ends here ------------*/

	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	
	WebDriver driver;
	
	
	
	
	public OR_TelevisionService(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	}

