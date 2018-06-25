package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class OR_PhoneAndDevices {
	
	
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	@FindBy(id="disableNext")
	public WebElement btnDisableNext;
	
	@FindBy(id="enableNext")
	public WebElement btnEnableNext;
	
	@FindBy(xpath="//img[@alt='Close']")
	public WebElement imgClose;
	
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
//	@FindBy(xpath="//div[@id='uniform-zip_code']//input[@type='radio' and @id='zip_code']")
//	public WebElement radZipcode;
	
	@FindBy(xpath="//label[contains(text(),'ZIP Code')]")
    public WebElement radZipcode;
	
	@FindBy(id="uniform-citystate")
	public WebElement radCityState;
	
	@FindBy(id="uniform-areacode")
	public WebElement radAreaCode;
	
	
	
	
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	
	
	
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	@FindBy(id="zipCode")
	public WebElement edtZipcode;

	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//h1[contains(text(),'Select a New Number')]")
	public WebElement txtSelectNewNo;
	
	@FindBy(id="StepIndicator")
	public WebElement txtStepIndicator;
	
	@FindBy(xpath="//h4[contains(text(),'Change Mobile Number')]")
	public WebElement txtStep1;
	
	@FindBy(xpath="//h1[text()='My Phone/Device']")
	public WebElement txtMyPhoneAndDevice;	
	
	@FindBy(xpath="//h1[contains(text(),'Select Your Phone')]")
	public WebElement txtSelectPhone;	
	
	@FindBy(xpath="//li/em[text()='ACCESSORIES']")
	public WebElement txtAccessories;
	
	@FindBy(xpath="//h1[text()='Manage My Device & Features']")
	public WebElement txtManageDevices;
	
	@FindBy(xpath="//li/em[text()='RINGTONES & DOWNLOADS']")
	public WebElement txtRingtonesDownloads;
	
	@FindBy(xpath="//h1[contains(text(),'Consumer Device Unlock Portal')]")
	public WebElement txtUnlockDevicePg;
	
	@FindBy(xpath="//h2[text()='Resend device unlock instructions']")
	public WebElement txtResendPopup;
	
	@FindBy(xpath="//h4[text()='Support']")
	public WebElement txtSupport;
	
	@FindBy(xpath="//h1[text()='Unlock SIM Card Using PIN Unlock Key']|//h2[contains(text(),'Unlock SIM using PIN unlock key (PUK)')]")
	public WebElement txtUnlockSIMPg;
	
	@FindBy(xpath="//h2[contains(text(),'Wireless Support')]|//h1[contains(text(),'Wireless Support')]")
	public WebElement txtWirelessSupport;
	
	@FindBy(xpath="//h1[contains(text(),'Learn about device unlock eligibility')]")
	public WebElement txtLearnUnlockEligibility;
	
	@FindBy(xpath="//h1[contains(text(),'Replace broken phone')]")  // added by Hiral Jogi
	public WebElement txtReplacebrokenphone;
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[contains(text(),'Wireless number change')]")
	public WebElement lnkWirelessNumChange;
	
	@FindBy(xpath="//a[@class='usm_click']")
	public WebElement lnkDropdown;
	
	@FindBy(xpath="//ul[@class='usm_menu']//li[contains(@class,'Padbot10')]/a")
	public WebElement lnkNewNo;
	
	@FindBy(xpath="//a[text()='Suspend or reactivate service']")
	public WebElement lnkReactivate;
	
	@FindBy(xpath="//a[contains(text(),'Upgrade device')]")
	public WebElement lnkUpgradePhone;
	
	@FindBy(xpath="//a[@id='ResetVoicemailPassword']")
	public WebElement lnkResetVM;
	
	@FindBy(xpath="//div[@class='menu_container']")
	public WebElement lnkUserSelect;
	
	@FindBy(id="profile-change")
	public WebElement lnkProfChange;
	
	@FindBy(xpath="//a[@id='UnlockSIMUsingPIN']")
	public WebElement lnkUnlockSIM;
	
	@FindBy(xpath="//a[text()='FAQ']")
	public WebElement lnkFAQ;
	
	@FindBy(xpath="//a[@id='GetHelpForPlanAndAddOns']")
	public WebElement lnkDeviceTutorial;
	
	@FindBy(xpath="//a[@id='TroubleshootMyDevice']")
	public WebElement lnkTroubleShoot;
	
	@FindBy(xpath="//a[contains(text(),'Troubleshoot my device')]")
	public WebElement lnkTroubleShootMyDevice;
	
	@FindBy(xpath="//a[text()='Resend device unlock instructions']")
	public WebElement lnkResendDevice;
	
	@FindBy(xpath="//a[text()='Device unlock support']")
	public WebElement lnkDeviceUnlockSupport;
	
	@FindBy(xpath="//a[text()='Add or change services']")
	public WebElement lnkAddOrChangeService;
	
	@FindBy(xpath="//a[contains(text(),'Learn about device unlock eligibility')]")
	public WebElement lnkLearnDeviceUnlockEligibility;
	
	@FindBy(xpath="//a[contains(text(),' Upgrade device')]")
	public WebElement lnkUpgradeDevice;
	
	@FindBy(xpath="//div[@id='topicTree']//a[contains(text(),'Troubleshoot devices')]")  // added by Hiral Jogi
	public WebElement lnkTroubleShoot1;
	
	@FindBy(xpath="//a[contains(text(),'Replace broken phone or other mobile device')]")  // added by Hiral Jogi
	public WebElement lnkReplacebrokenPhone;
	
	@FindBy(xpath="//a[@class='solutionLink' and contains(text(),'Upgrade your phone')]")  // added by Hiral Jogi
	public WebElement lnkUpgradeYourPhone;
	
	
	/*---------------------------
	 * frame objects add below
	 *---------------------------*/
	@FindBy(xpath="//iframe[contains(@src,'mobileNumberChangeInfoPopup')]")
	public WebElement frmMobileNumberChangePopUp;
	
	@FindBy(xpath="//img[@alt='continue']")
	public WebElement imgContinue;
	
	@FindBy(xpath="//p")
	public WebElement txtAfterYouChange;
	
	@FindBy(xpath="//a[contains(text(),'Unlock this device')]")
	public WebElement txtUnlockDevice;

	@FindBy(xpath="//img[@alt='Device']")
	public WebElement imgDevice;
	
	@FindBy(xpath="//iframe[contains(@src,'unlockModal')]")
	public WebElement frmUnlockModel;
	
	@FindBy(xpath="//img[@alt='continue']")
	public WebElement imgContinueFrm;
	
/*------- obejcts added by osnhore - Starts here ------------ */
	
	
	@FindBy(xpath="//h1[contains(text(),'Manage My Device & Features')]")   // Added by Ravinder
	public WebElement txtPgHrManageMyDeviceAndFeature_1511;
	
	@FindBy(xpath="//img[@alt='Device']")   // Added by Ravinder
	public WebElement imgDevice_1511;
	
	@FindBy(xpath="//div[@class='addOnsMod']//Strong")          // Added by Ravinder
	public WebElement txtCustomerName_1511;
	
	@FindBy(xpath="//div[@class='myAccount']//*[contains(text(),'.')]")          // Added by Ravinder
	public WebElement txtCustomerNumber_1511;
	
	@FindBy(xpath="//div[@class='myAddOns']//p[contains(text(),'Manage device')]")        // Added by Ravinder
	public WebElement txtHeaderManageDevice_1511;
	
	@FindBy(xpath="//div[@class='myAddOns']//a")          // Added by Ravinder
	public WebElement lnkUnderManageDevice_1511;
	
	@FindBy(xpath="//*[contains(@class,'myAccount')]//h2[contains(text(),'My current features')]")        // Added by Ravinder
	public WebElement txtHeaderMyCurrentFeatures_1511;
	
	@FindBy(xpath="//*[starts-with(@class,'toggleCheck ')]/h2[contains(text(),'More features for my device')]")        // Added by Ravinder
	public WebElement txtHeaderMoreFeaturesForMyDevice_1511;
	
	@FindBy(xpath="//*[@class='padSubfooter']//p[contains(text(),'Manage My Account')]")        // Added by Ravinder
	public WebElement txtFooterManageMyAccount_1511;
	
	@FindBy(xpath="//*[@class='padSubfooter']//p[contains(text(),'My Devices & Features')]")        // Added by Ravinder
	public WebElement txtFooterMyDeviceFeatures_1511;
	
	@FindBy(xpath="//*[@class='padSubfooter']//p[contains(text(),'Get Help & Support')]")        // Added by Ravinder
	public WebElement txtFooterGetHelpSupport_1511;

	
	
	
	/*------- obejcts added by osnhore - Ends here ------------ */
	
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	
	WebDriver driver;
	
	
	
	
	public OR_PhoneAndDevices(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}


