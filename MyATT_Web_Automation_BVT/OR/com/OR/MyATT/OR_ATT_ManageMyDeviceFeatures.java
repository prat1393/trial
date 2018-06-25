package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_ATT_ManageMyDeviceFeatures 
{
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	@FindBy(xpath="//img[@alt='Use the calendar to set an end date.']") 
	public WebElement imgCalenderIcon;
	
	@FindBy(xpath="//a[contains(@class,'closeModal')]//img[@alt='Close']") 
	public WebElement imgCloseModal;
	
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	@FindBy(xpath="//input[@id='addonsNOTHANKS']") 
	public WebElement radRemoveAddon;
	
	@FindBy(xpath="//input[@id='addonsIRPASPAVM']") 
	public WebElement radAddonPassport;
	
	@FindBy(xpath="//div[@id='uniform-addonsIRPASPAVM']") 
	public WebElement radAddonPassportElm;
	
	
	@FindBy(xpath="//div[@id='uniform-addonsNOTHANKS']") 
	public WebElement radRemoveAddonElm;
	
	@FindBy(xpath="(//input[contains(@id,'addons')])[1]") 
	public WebElement radAddonsAN32;
	
	@FindBy(id="startEndDate") 
	public WebElement radStartEndDate;
	
	@FindBy(id="presentdate") 
	public WebElement radPresentDate;
	
	@FindBy(id="level3") 
	public WebElement radLevel3;
	
	@FindBy(id="addonsNOTHANKS") 
	public WebElement radRemoveFeatureAddOn;
	
	@FindBy(id="level1") 
	public WebElement elmRad;
	
	@FindBy(id="addonsMSGM2AMUN") 
	public WebElement radSecondAddOn;
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[contains(@class,'usm_click')]") 
	public WebElement elmSelectCTNDropdown ;
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/

	
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	

	
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[text()='Manage My Device & Features']") 
	public WebElement txtManageMyDeviceFeatures;

	@FindBy(xpath="//span[text()='One-time Charge']")
	public WebElement txtOneTimeCharge;
	
	@FindBy(xpath="//span/strong[contains(text(),'One-time Charge')]")
	public WebElement txtOneTimeChargeFeatures;
	
	@FindBy(xpath="//p[contains(text(),'Manage My Current Features')]")
	public WebElement txtManageMyCurrentFeatures;
	
	@FindBy(xpath="//*[contains(text(),'There aren') and contains(text(),'t any features available for this device')]")
	public WebElement txtNoFeaturesAvailable;
	
	@FindBy(xpath="//*[contains(text(),'You don') and contains(text(),'t have any features for this device yet')]")
	public WebElement txtInternationalSectionAbsent;
	
	@FindBy(xpath="//h1[contains(text(),'Confirmation')]")
	public WebElement txtConfirmation;
	
	@FindBy(xpath="//h2[text()='My current features']")
	public WebElement txtMyCurrentFeatures;
	
	@FindBy(xpath="//h2[text()='More features for my device']")
	public WebElement txtMoreFeatures;
	
	@FindBy(xpath="//h2[text()='View All Features']")
	public WebElement txtViewAllFeatures;
	
	@FindBy(xpath="//p[contains(text(),'Manage device')]")
	public WebElement txtManageDevice;
	
	//review change page
	@FindBy(xpath="//h1[text()='Review Changes']")
	public WebElement txtReviewChange;
	
	@FindBy(xpath="//h1[text()='Select an Account']")
	public WebElement txtSelectAccount;
	
	@FindBy(xpath="//h4[text()='Upgrade Your Device']")
	public WebElement txtUpgradeDevice;
	
	@FindBy(xpath="//h1[contains(text(),'NumberSync')]")
	public WebElement txtNumberSync;
	
	@FindBy(xpath="//p[contains(text(),'effective immediately')]")
	public WebElement txtEffImmediately;
	
	@FindBy(xpath="//p[contains(text(),'partial')]")
	public WebElement txtPartial;
	
	@FindBy(xpath="//strong[contains(text(),'Adding')]")
	public WebElement txtYouAdding;
	
	@FindBy(xpath="//strong[contains(text(),'Removing')]")
	public WebElement txtYouRemoving;
	
	@FindBy(xpath="//p[contains(text(),'Effective')]")
	public WebElement txtEffectiveDate;
	
	@FindBy(xpath="//h2[contains(text(),'Terms & Conditions')]")
	public WebElement txtTermsAndCondition;
	
	@FindBy(xpath="//strong[contains(text(),'Remove This Feature')]")
	public WebElement txtRemoveThisFeature;
	
	@FindBy(xpath="//strong[contains(text(),'You Added')]")
	public WebElement txtYouAdded;
	
	@FindBy(xpath="//strong[contains(text(),'You Removed')]")
	public WebElement txtYouRemoved;
	
	@FindBy(xpath="//div[@class='myAccount']")
	public WebElement txtAccountInfo;
	
	@FindBy(xpath="//p[text()='Manage My Account']")
	public WebElement txtManageAccount;
	
	@FindBy(xpath="//p[text()='My Devices & Features']")
	public WebElement txtDeviceFeatures;
	
	@FindBy(xpath="//p[text()='Get Help & Support']")
	public WebElement txtHelpSupport;
	
	@FindBy(xpath="//h1[text()='International Features']")
	public WebElement txtInternationalFeatures;
	
	@FindBy(xpath="//span[contains(text(),'Customer is on prepaid plan')]")
	public WebElement txtErrorMsg;
	
	@FindBy(xpath="//*[contains(text(),'have an annual service commitment' ) and contains(text(),'doesn')]")
	public WebElement txtThisDeviceDoesntHaveAnnualServiceCommitment;
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[@title='Passport']")
	public WebElement lnkPassport;
		
	
	
	//a[normalize-space(@linkname)='View all features']
	@FindBy(xpath="//a[contains(text(),'View all features')]")
	public WebElement lnkViewAllFeatures;
	
	@FindBy(xpath="//a[@linkname='View all features'] | //a[contains(text(),'View all features')]")
	public WebElement lnkViewAllFeatures1;
		
	@FindBy(xpath="//a/*[contains(text(),'972-839-4957')]")
	public WebElement lnkCTN;
	
//	@FindBy(xpath="//div[@id='usmModule']")
	@FindBy(xpath="//a[@class='usm_click']/span")
	public WebElement lnkUSM;
	
	@FindBy(xpath="//a//strong[contains(text(),'Messaging')]")
	public WebElement lnkMessaging;
	
	@FindBy(xpath="//a[contains(text(),'Device protection')]")
	public WebElement lnkDeviceProtection;
	
	@FindBy(id="SeeMoreDeviceOptions")
	public WebElement lnkSeeMoreDeviceOptions;
	
	@FindBy(xpath="//img[@title='Reactivate This Device']")
	public WebElement lnkSuspendOrReactivates;
	
	@FindBy(xpath="//a[@linkname='Data & text']")
	public WebElement lnkDataText;
	
	@FindBy(xpath="//a[@name='Edit']")
	public WebElement lnkEdit;
	
	@FindBy(id="CheckUpgradeEligibility")
	public WebElement lnkCheckUpgradeEligibility;
	
	@FindBy(id="SuspendService")
	public WebElement lnkSuspendService;
	
	@FindBy(id="ResetVoicemailPassword")
	public WebElement lnkResetVoicemailPassword;
	
	@FindBy(id="WirelessNumberChange")
	public WebElement lnkWirelessNumberChange;
	
	@FindBy(id="NumberSyncWearablesInitiate")
	public WebElement lnkNumberSyncWearablesInitiate;
	
	@FindBy(id="ViewAccessories")
	public WebElement lnkViewAccessories;
	
	@FindBy(id="UnlockSIMUsingPIN")
	public WebElement lnkUnlockSIMUsingPIN;
	
	@FindBy(xpath="//div[@id='usmModule']//a/span[contains(text(),'214-458-8404')] | //span[contains(text(),'214-458-8404')]")
	public WebElement lnkUSMCTN;
	
	@FindBy(xpath="//a[@title='Show/Hide Prior Acivity']")
	public WebElement lnkCollapseExpand;
	
	@FindBy(id="CheckUpgradeEligibility")
	public WebElement lnkUpgrade;
		
	@FindBy(id="SuspendService")
	public WebElement lnkSuspend;
	
	@FindBy(id="ResetVoicemailPassword")
	public WebElement lnkResetPassowrd;
	
	@FindBy(id="WirelessNumberChange")
	public WebElement lnkChangeWirelessNumber;
	
	@FindBy(id="NumberSyncWearablesInitiate")
	public WebElement lnkNumberSyncInitiate;
	
	@FindBy(id="ViewAccessories")
	public WebElement lnkSeeAccessories;
	
	@FindBy(id="UnlockSIMUsingPIN")
	public WebElement lnkUnlockSim;
	
	@FindBy(id="ReturnToMyDeviceAddons")
	public WebElement lnkReturnToMyDevicesAddOns;
	
	@FindBy(xpath="//*[@id='DeviceAndServices' or @id='ManageCurrentFeature']")
	public WebElement lnkViewChange;
	
	@FindBy(id="ReturnToMyDeviceAndFeatures")
	public WebElement lnkReturnToMyDeviceAndFeatures;
	
	@FindBy(id="ReportLostAndStolenDevice")
	public WebElement lnkReportLostAndStolenDevice;
	
	@FindBy(xpath="//a[contains(text(),'Change my wireless number')]")
	public WebElement lnkChangeMyWirelessNumber;
	
	@FindBy(id="TroubleshootMyDevice")
	public WebElement lnkTroubleshootMyDevice;
	
	@FindBy(xpath="//a[contains(text(),'global coverage')]")
	public WebElement lnkGlobalCoverage;
	
	@FindBy(id="DeviceUnlockSupportOnModal")
	public WebElement lnkDeviceUnlockSupportOnModal;
	
	@FindBy(id="GetHelpForPlanAndAddOns")
	public WebElement lnkGetHelpForPlanAndAddOns;
	
	@FindBy(id="GetWirelessSupport")
	public WebElement lnkGetWirelessSupport;
	
	@FindBy(id="ReturnToOverview")
	public WebElement lnkReturnToOverview;
	
	@FindBy(xpath="//a[contains(text(),'Get your PIN unlock key')]")
	public WebElement lnkGetYourPINUnlockKey;
	
	@FindBy(xpath="//a[@href='#toggle700']")
	public WebElement lnkRecommendedFeatures;
	
	@FindBy(id="TransferOfBillingResponsibility")
	public WebElement lnkTransferOfBillingResponsibility;
	
	@FindBy(id="TurnOffSponsoredData")
	public WebElement lnkTurnOffSponsoredData;
	
	@FindBy(xpath="//a[contains(text(),'Turn data usage')]")
	public WebElement lnkTurnDataUsageOff;
	
	@FindBy(id="ResetMyVoicemailPassword")
	public WebElement lnkResetMyVoicemailPassword;
	
	@FindBy(id="AboutTravelAndInternational")
	public WebElement lnkAboutTravelAndInternational;
	
	@FindBy(xpath="//h2[contains(text(),'My current features')]//parent::div//a[1][contains(text(),'View or change')]")
	public WebElement lnkViewOrChangeUnderMyCurrentFeatures;
	
	@FindBy(xpath="//a[contains(text(),'Texting')]")
	public WebElement lnkTexting;
	
	@FindBy(xpath="//a[@linkname='International']")
	public WebElement lnkInternational;
	
	@FindBy(id="UpgradeDevice")
	public WebElement lnkUpgradeDevice;
	
	@FindBy(id="modalLink")
	public WebElement lnkGetHelpUnlockingDevice ;
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//div[@id='btnNext']//button[contains(text(),'Select & Review')]") 
	public WebElement btnSelectReview;
	
	@FindBy(xpath="//button[text()='Continue']") 
	public WebElement btnContinue;
	
	@FindBy(xpath="//input[@id='acctSelContinue']") 
	public WebElement btnContinue1;
	
	@FindBy(xpath="//button[text()='Close']") 
	public WebElement btnClose;
	
	@FindBy(id="submit") 
	public WebElement btnSubmit;
	
	@FindBy(id="selectmodal_focus") 
	public WebElement btnSelect;
	
	@FindBy(id="cancel") 
	public WebElement btnCancel;
	
	@FindBy(id="Cancel") 
	public WebElement btnCancel1;
	
	@FindBy(id="back") 
	public WebElement btnBack;
	
	@FindBy(xpath="//a[@title='Select a date']") 
	public WebElement btnSelectAdate;
	
	@FindBy(id="btnNextDisabled") 
	public WebElement btnNextDisabled;
	
	@FindBy(xpath="//button[contains(text(),'text another country')]") 
	public WebElement btnCallORTextAnotherCountryFromUS;
	
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	@FindBy(xpath="//iframe[contains(@src,'conflictModelFeatures')]") 
	public WebElement frmConflict;
	
	@FindBy(xpath="//iframe[contains(@src,'viewAllAddonsModalFeatures')]") 
	public WebElement frmViewFeatures;
	
	@FindBy(xpath="//iframe[contains(@src,'effDatePopUpFeatures')]") 
	public WebElement frmeffDatePopUpFeatures;
	
	@FindBy(xpath="//iframe[contains(@src,'unlockSimFeatures')]") 
	public WebElement frmUnlockSimFeatures;
	
	@FindBy(xpath="//iframe[contains(@src,'sponsoredDataFeatures')]") 
	public WebElement frmManageSponsoredData;
	
	@FindBy(xpath="//iframe[contains(@src,'DeviceUnlock')]") 
	public WebElement frmDeviceUnlock;
	
	@FindBy(xpath="//iframe[contains(@src,'effDateForIPPopUpFeatures')]") 
	public WebElement frmEffDate;
	
	@FindBy(xpath="//input[@id='strtdate']") 
	public WebElement frmEffDateEdt;
	
	@FindBy(xpath="//div[@id='ui-datepicker-div']") 
	public WebElement frmDatePickDiv;
	
	@FindBy(xpath="//strong[contains(text(),'Ends:')]") 
	public WebElement frmEndsText;
	
	@FindBy(xpath="//a[@id='Cancel']") 
	public WebElement frmLnkCancel;
	
	
	
	
	

@FindBy(id="futuredate") 
	public WebElement frmeFutureDateRadioBtn;
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;
	public OR_ATT_ManageMyDeviceFeatures(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
