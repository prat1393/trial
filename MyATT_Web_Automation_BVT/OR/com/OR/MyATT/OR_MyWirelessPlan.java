package com.OR.MyATT;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_MyWirelessPlan {

	
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[@class='closeModal alignIE'][1]")
	public WebElement imgCloseMessageForNotEligble;
	
	@FindBy(xpath="//img[@src='/olam/images/brand30/alert_icon_y_suspended.png'] | //img[contains(@src,'suspended.png')]")
	public WebElement imgSuspendedAlert;
	
	@FindBy(xpath="//div[@id='viewFeaturesPopup'] | //strong[text()='Included Features']")
	public WebElement imgViewAllIncludedFeaturesPopup;
	
	@FindBy(xpath="//img[contains(@alt,'calendar')]")
	public WebElement imgCalendar;
	
	@FindBy(xpath="//a[@title='CloseSummary'] | //a[@id='closeSummary']")
	public WebElement imgCloseSummary;
	
	@FindBy(xpath="//img[@title='viewFeatures' or @title='Close']")
	public WebElement imgCloseSummaryIncluded;
	
	@FindBy(xpath="//img[@alt='Cancel']")
	public WebElement imgCancel;
	
	
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	

	
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//div[@class='padRight20 accRow']//p[@class='botMar0']//strong")
	public WebElement lstTalkTextData;
	
	@FindBy(xpath="//div[@id='ddUserDetailsBox1']//dl/dd")
	public WebElement lstMyPlanDropDown;
	
	@FindBy(xpath="//div[@id='ddUserDetailsBox1']//dl/dd//a")
	public WebElement lstMyPlanDropDownLinks;
	
	@FindBy(xpath="//div[@id='ddUserDetailsBox1']//dl/dd[4]/div/div/a")
	public WebElement lstMyPlanDropDownIndividualPlans;

	@FindBy(xpath="//div[@class='addOnsMod']/div/div")
	public WebElement lstDevicesUnderAPlan;
	
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	
	
	
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
		//@FindBy(xpath="//title[contains(text(),'Feature Details')]")
		@FindBy(xpath="//div[@id='ParaSecQSBNDLMRC']/div[1]/h3[contains(text(),'More About This Feature')]")
		public WebElement txtFeatureDetails;
	
		@FindBy(xpath="//p/span[contains(@class,'Number')]")
		public WebElement txtCTNInFocus;
		
		@FindBy(xpath="//h1[text()='My Wireless Plan']")
		public WebElement txtMyWirelessPlan;
		
		@FindBy(xpath="//h1[contains(text(),'My Wireless Service')]")
		public WebElement txtMyWirelessService;
		
		@FindBy(xpath="//h3[contains(text(),'Manage My Devices & Features')]")
		public WebElement txtManageMyDevicesAndFeatures;
		
		@FindBy(xpath="//div[@class='left-30 MarRight28Force']")
		public WebElement txtPlanSummarySection;
		
		//@FindBy(xpath="//span[@class='MarTop15 ']")
		@FindBy(xpath="//div[contains(@class,'sub-group-title')]//span")
		public WebElement txtPlanSummarySectionHeader;
				
		@FindBy(xpath="//strong[@class='font14'][contains(text(),'Monthly Cost')]")
		public WebElement txtMonthlyCostSubHeader;

		//@FindBy(xpath="//span[@class='cs2_colorDarkGray font14']//strong")
		@FindBy(xpath="//strong[@class='font14'][contains(text(),'Monthly Cost')]//parent::p//parent::div//*[contains(text(),'$')]")
		public WebElement txtMonthlyCost;
	
		@FindBy(xpath="//strong[contains(text(),'Plan and Feature Cost Summary')] | //span[contains(text(),'FamilyTalk Nation 450 with Rollover')]")
		public WebElement txtPlanAndFeatureCostSummary;
		
		@FindBy(xpath="//div[@class='padRight20 accRow']//p/strong[contains(text(),'Data')]")
		public WebElement txtDataSubHead;
		
		@FindBy(xpath="//div[contains(@class,'PadBot5')]")
		public WebElement txtSummaryOverlay;
		
		@FindBy(xpath="//strong[contains(text(),'Total Monthly Cost')] | //span[contains(text(),'Total Monthly Cost')]")
		public WebElement txtTotalMonthlyCost;
		
		@FindBy(xpath="//span[@class='colorGrey']//strong")
		public WebElement txtTotalMonthlyCostValue;
		
		@FindBy(xpath="//strong[text()='Talk'] | //span[text()='Talk']")
		public WebElement txtTalk;
		
		@FindBy(xpath="//strong[text()='Data'] | //span[text()='Data']")
		public WebElement txtData;
		
		@FindBy(xpath="//strong[text()='Text'] | //span[text()='Text']")
		public WebElement txtText;
		
		@FindBy(xpath="//strong[text()='Sub-total for Talk, Text & Data'] | //span[text()='Sub-total for Talk, Text & Data']")
		public WebElement txtSubTotal;
		
		@FindBy(xpath="//p[@class='botMar0']//strong[contains(text(),'$')]")
		public WebElement txtSubTotalAmount;
		
		@FindBy(xpath="//strong[contains(text(),'corporate')] | //span[contains(text(),'corporate')]")
		public WebElement txtCorporateDiscount;
		
		@FindBy(xpath="//p[contains(text(),'excludes taxes & additional fees')] | //span[contains(text(),'excludes taxes & additional fees')]")
		public WebElement txtExcludesTaxAndAdditionalFees;
		
		@FindBy(xpath="//p/strong[contains(text(),'Plan Details')]")
		public WebElement txtPlanDetails;
		
		@FindBy(xpath="//p[contains(text(),'Due to one of your lines being suspended we are unable to change your rate plan online')] | //p[contains(text(),'Because this wireless number is suspended, we can not process your request to change your rate plan')] | //*[contains(text(),'Due to one of your lines being suspended')]")
		public WebElement txtChangePlanErrorMessage;
		
		@FindBy(xpath="//p[contains(text(),'Connected Vehicle')]")
		public WebElement txtConnectedVehicle;
		
		@FindBy(xpath="//h1//div[contains(text(),'Mobile Devices')]")
		public WebElement txtMobileDevices;
		
		@FindBy(xpath="//h3[text()='Manage My Devices & Features']")
		public WebElement txtManageDevicesFeatures;
		
		@FindBy(xpath="//div[@id='profile-details']")
		public WebElement txtProfileDetails;
		
			/*---------------------------
	 * Link objects add below
	 *---------------------------*/
		@FindBy(xpath="//a[@name='Manage']")
		public WebElement lnkManage;
	
		@FindBy(name="AT&T Mobile Protection Pack")
		public WebElement lnkATTMobileProtectionPack;
	
		@FindBy(xpath="//a[@name='Add a new device']")
		public WebElement lnkAddADevice;
		
		@FindBy(xpath="//a[@name='viewIncludedFeatures'] | //a[@id='planDetails']")
		public WebElement lnkViewAllFeatures;
		
		@FindBy(xpath="//a[@name='viewCostSummary'] | //a[@id='costSummary']")
		public WebElement lnkViewCostSummary;
		
		@FindBy(xpath="//div[@class='myAddOns']//child::p[2]/a[1]")
		public WebElement lnkFirstAddon;
		
		@FindBy(xpath="//a[@id='ddUserDetails1']")
		public WebElement lnkMyPlanDropdown;
		
		@FindBy(xpath="//a[text()='Convert to family ']")
		public WebElement lnkConvertToFamily;
		
		@FindBy(xpath="//table/tbody/tr/td[@class='vmid pad0 center']//a[@class='msrtMinusButton_2']")
		public WebElement lnkMinusSigntoPlan;

		@FindBy(xpath="//a[@class='wt_Body openModalIneligibledevice']")
		public WebElement lnkNotEligibleInMobileShareValue;
		
		@FindBy(xpath="//a[@name='Edit']")
		public WebElement lnkEdit;
		
		@FindBy(xpath="//a//span[text()='Mobile Share 1']")
		public WebElement lnkMobileShare;
		
		@FindBy(xpath="//a[contains(text(),'Check upgrade eligibility')]")
		public WebElement lnkCheckUpgradeEligibility;
		
		@FindBy(xpath="//a[contains(text(),'Suspend service ')]")
		public WebElement lnkSuspendService;
		
		@FindBy(xpath="//a[contains(text(),'Reset voicemail password ')]")
		public WebElement lnkResetVoicemailPassword;
		
		@FindBy(xpath="//a[contains(text(),'Change wireless number ')]")
		public WebElement lnkChangeWirelessNumber;
		
		@FindBy(xpath="//a[@id='NumberSyncWearablesInitiate']")
		public WebElement lnkNumberSyncForWearables;
		
		@FindBy(xpath="//a[contains(text(),'See accessories')]")
		public WebElement lnkSeeAccessories;
		
		@FindBy(xpath="//a[contains(text(),'Unlock SIM using PIN unlock key (PUK)')]")
		public WebElement lnkUnlockSIMUsingPINUnlockKey;
		
		@FindBy(xpath="//a[contains(text(),'Get help unlocking device')]")
		public WebElement lnkGetHelpUnlockingDevice;
		
		@FindBy(xpath="//a[@name='Go to myAT&T overview']")
		public WebElement lnkGoToOverview;
		
		@FindBy(id="TransferOfBillingResponsibility")
		public WebElement lnkTransferOfBillingResponsibility;
		
		@FindBy(id="ViewAndPayMyBill")
		public WebElement lnkViewAndPayMyBill;
		
		@FindBy(id="ViewWirelessUsage")
		public WebElement lnkViewWirelessUsage;
		
		@FindBy(id="EarlyTerminationFee")
		public WebElement lnkEarlyTerminationFee;
		
		@FindBy(id="AboutTravelAndInternational")
		public WebElement lnkAboutTravelAndInternational;
		
		@FindBy(id="CancelMySerivce")
		public WebElement lnkCancelMySerivce;
		
		@FindBy(id="GetWirelessSupport")
		public WebElement lnkGetWirelessSupport;
		
	  @FindBy(xpath="//a[contains(text(),'View Profile')]")
	  public WebElement lnkViewProfile;
	  
	  @FindBy(xpath="//a[contains(text(),'Plan details')] | //a[contains(text(),'Plan Details')] | //a[contains(text(),'plan details')]")
	  public WebElement lnkPlanDetailsCTA;
			
	/*---------------------------
	 * Table objects add below
	 *---------------------------*/
	

	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
		@FindBy(xpath="//button[contains(text(),'Select & Review')]")
		public WebElement btnSelectAndReview;
		
		@FindBy(xpath="//a[@name='Change Plan']")
		public WebElement btnChangePlan;
		
		@FindBy(xpath="//table/tbody[@id='msrt_1']/tr/td//a[@class='btnActiveBckgx176 wt_Body tooltipsDisabled']")
		public WebElement btnSelect300MBPlan;
 
		@FindBy(xpath="//a[@class='usm_click']")
		public WebElement btnSelectInMobilShareValue;
		
		@FindBy(xpath="//div[@id='ddUserDetailsBox1']//dl/dd[2]")
		public WebElement btnMobilShareFromDropDown;
		
		@FindBy(xpath="//div[@id='ddUserDetailsBox1']//dl/dd[3]")
		public WebElement btnFamilyTalkFromDropDown;
		
		@FindBy(xpath="//img[@alt='Back']")
		public WebElement btnBack;
		
		@FindBy(id="presentdate")
		public WebElement btneffdate;
		
		@FindBy(id="startEndDate")
		public WebElement btnstrtEnddate;
		
		@FindBy(id="selectmodal_focus")
		public WebElement btnSelect;
		
		
		
			
		/*------- obejcts added by osnhore - Starts here ------------ */		
		
		
		@FindBy(xpath="//span[contains(@class,'MarTop15')]")          // Added by Ravinder
		public WebElement txtPlanSummarySectionHeader_1511;
		
		@FindBy(xpath="//h3[contains(text(),'Manage My Devices & Features')]")          // Added by Ravinder
		public WebElement txtManageDeviceAndFeatures_1511;
		
		@FindBy(xpath="//img[@alt='Device']")          // Added by Ravinder
		public WebElement imgDevice_1511;
		
		@FindBy(xpath="//div[@class='addOnsMod']//Strong")          // Added by Ravinder
		public WebElement txtCustomerName_1511;
		
		@FindBy(xpath="//div[@class='addOnsMod']//*[contains(text(),'.')]")          // Added by Ravinder
		public WebElement txtCustomerNumber_1511;
		
		@FindBy(xpath="//*[@id='Fatfooter']//strong[contains(text(),'Manage My Account')]")          // Added by Ravinder
		public WebElement txtFooterManageMyAccount_1511;
		
		@FindBy(xpath="//*[@id='Fatfooter']//strong[contains(text(),'My Wireless Service')]")          // Added by Ravinder
		public WebElement txtFooterMyWirelessService_1511;
		
		@FindBy(xpath="//*[@id='Fatfooter']//strong[contains(text(),'Get Help & Support')]")          // Added by Ravinder
		public WebElement txtFooterGetHelpSupport_1511;
		
		
		
		
		/*------- obejcts added by osnhore - Ends here ------------ */
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	
	public OR_MyWirelessPlan(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
			
		}
}
