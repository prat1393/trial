package com.OR.MyATT;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SupportingFiles.UI;

public class OR_ViewOrChangeRatePlan {

	
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[contains(@class,'closeModal')]")
	public WebElement imgCloseModal;
	
	
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//input[@id='dateChoice2']")
	public WebElement radEffdate;
	
	@FindBy(xpath="//*[@for='CURRENTDATE'] | //*[contains(text(),'today')]")
	public WebElement radCurrentDate;
	
	@FindBy(xpath="//*[@for='FUTUREDATE']")
	public WebElement radFutureDate;
	
	
	
	/*---------------------------
	 * List objects add below
	
	
	
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	
	
	
	/*---------------------------
	 * Frame box objects add below
	 *---------------------------*/
	@FindBy(xpath="//iframe[contains(@src,'Usage')]")
	public WebElement frmViewUsageRecommenderTool;
	
	@FindBy(xpath="//iframe[contains(@src,'ValuePlanDetailSwitchMobileShare')]")
	public WebElement frmLearnMore;
	
	@FindBy(xpath="//iframe[contains(@src,'AllPlans')]")
	public WebElement frmViewAllPlans;
	
	@FindBy(xpath="//iframe[contains(@src,'show')]")
	public WebElement frmShow;
	
	@FindBy(xpath="//iframe[contains(@src,'AddlBenefits')]")
	public WebElement frmViewDetails;
	
	@FindBy(xpath="//iframe[contains(@src,'Plan')]")
	public WebElement frmPriceDetails;
	
	@FindBy(xpath="//iframe[contains(@src,'MobileShare')]")
	public WebElement frmeffDatePopUpSwitchMobileShare;
	
	@FindBy(xpath="//iframe[contains(@src,'PricingDetailMobileSharePlan')]")
	public WebElement frmPricingDetailMobileSharePlan;
	
	
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//h1[contains(text(),'View or Change')]")
	public WebElement txtViewOrChangeRatePlan;

	@FindBy(xpath="//h2[contains(text(),'Current Rate Plan')]")
	public WebElement txtCurrentRatePlan;
	
	@FindBy(xpath="//h2[contains(text(),'Nation Plan')]")
	public WebElement txtNationPlans;
	
	@FindBy(xpath="//h2[contains(text(),'Nation with Canada Plan')]")
	public WebElement txtNationWithCanadaPlans;
	
	@FindBy(xpath="//ol[@class='step-list']")
	public WebElement txtStepIndicator;
	
	@FindBy(id="StepIndicator")
	public WebElement txtStepIndicator1;
	
	@FindBy(xpath="//h1[contains(text(),'Select Effective Date')]")
	public WebElement txtSelectEffectiveDate;
	
	@FindBy(xpath="//h1")
	public WebElement txtReviewAndConfirm;
	
	@FindBy(xpath="//h1")
	public WebElement txtConfirmation;

	@FindBy(xpath="//p[contains(text(),'Usage includes bonus data')]")
	public WebElement txtBonusData;
	
	@FindBy(xpath="//h2[contains(text(),'Account')]")
	public WebElement txtAccount;
	
	@FindBy(xpath="//*[contains(text(),'Plan Effective')]")
	public WebElement txtEffective;
	
	@FindBy(xpath="//*[contains(text(),'successfully')]")
	public WebElement txtConfirmationSuccess;
	
	@FindBy(xpath="//h1[contains(text(),'Review')] | //*[contains(text(),'Review My Order')]")
	public WebElement txtReviewMyChanges;
	
	@FindBy(xpath="//p[@class='botMar0 font14']/strong[contains(text(),'GB')]")
	public WebElement txtGBshareValue;
	
	@FindBy(id="planData")
	public WebElement txtPlanData;
	
	@FindBy(id="planAmount")
	public WebElement txtPlanAmt;
	
	@FindBy(xpath="//h2[contains(text(),'BAN')]")
	public WebElement txtBAN;
	
	@FindBy(xpath="//strong[text()='Review My Order']")
	public WebElement txtReviewMyOrder;

	@FindBy(xpath="//h2")
	public WebElement txtMOdalWidowTitle;
	
//	@FindBy(xpath="//span[contains(text(),'We Suggest')]/parent::p/parent::div/parent::div//strong[contains(text(),'GB')]")
	@FindBy(xpath="//tbody[not(contains(@style,'display:none'))]//div[@class='csToggles']//parent::div//parent::td//parent::tr//parent::tbody//td//strong[contains(text(),'GB')]")
	public WebElement txtBestPricingInRecommenderTool;
	
	@FindBy(xpath="//a[contains(text(),'How rate plan changes affect your bill')]")
	public WebElement txtNote;
	
	@FindBy(xpath="//p[contains(text(),'Primary Line')]")
	public WebElement txtPrimaryLine;
	
	@FindBy(xpath="//td[contains(text(),'ED RATE PLAN')]")
	public WebElement txtSelectedPlan;
	
	@FindBy(xpath="//td[contains(text(),'Effective Date:')]")
	public WebElement txtEffectiveDate;
	
	@FindBy(xpath="//td[contains(text(),'Monthly Cost:')]")
	public WebElement txtMonthlyCost;
	
	@FindBy(xpath="//strong[contains(text(),'PLAN DETAILS')]")
	public WebElement txtPlanDetails;
	
	@FindBy(xpath="//h2[contains(text(),'Terms & Conditions')]")
	public WebElement txtTerms;
	
	@FindBy(xpath="//strong[contains(text(),'RETAINED FEATURES')]")
	public WebElement txtRetainedFeatures;
	
	@FindBy(xpath="//p[contains(text(),'SUMMARY')]")
	public WebElement txtSummary;
	
	@FindBy(xpath="//p[contains(text(),'Additional Fees')]")
	public WebElement txtAdditionalFees;
	
	@FindBy(xpath="//*[text()='About AT&T Mobile Share® Value plan']")
	public WebElement txtAboutATTMobileShareValuePlanOnViewMoreDetailFrame;
	
	@FindBy(xpath="//h1[contains(text(),'Switch to Mobile Share Value')]")
	public WebElement txtSwitchMobileShareValue;
	
	@FindBy(xpath="//h3//strong[text()='Select Devices']")
	public WebElement txtSelectDevices;
	
	@FindBy(xpath="//*[text()='Terms & Conditions']")
	public WebElement txtTermsAndConditions;
	
	@FindBy(xpath="//*[contains(text(),'successfully')]")
	public WebElement txtSuccessfully;
	
	@FindBy(xpath="//span[contains(text(),'A change to your')]")
	public WebElement txtChangeRatePlanError;
	
	@FindBy(xpath="//tbody[not(contains(@style,'display:none'))]//div[@class='csToggles']//strong | //tbody[(contains(@style,'table-row-group'))]//div[@class='csToggles']//strong")
	public WebElement txtPlanInGB;
	
	@FindBy(xpath="//*[contains(text(),'My Current Mobile Share Plan')]//ancestor::div//div[@class='plansComparison']")
	public WebElement txtMobileShare;
	
	@FindBy(xpath="//div[@class='planMod']")
	public WebElement txtListOfPlansSection;
	
	@FindBy(xpath="//*[contains(text(),'Connected device -')]")
	public WebElement txtConnectedDevice;
	
	@FindBy(xpath="//*[contains(text(),'Connected device -')]//parent::td//*[contains(text(),'.')]")
	public WebElement txtConnectedDeviceNumber;
	
	@FindBy(xpath="//*[contains(text(),'Are you sure?')]")
	public WebElement txtAreYouSureAlert;
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//tbody[not(contains(@style,'display:none'))]//img[@alt='expand']")
	public WebElement lnkPlus;
	
	@FindBy(id="SelectMSPlan")
	public WebElement lnkEdit;
	
	@FindBy(xpath="//a[contains(text(),'Learn more about Mobile Share Value.')]")
	public WebElement lnkLearnMore;
	
	@FindBy(xpath="//tbody[@id='msrt_4']//a[text()='View usage']")
	public WebElement lnkViewUsage;
	
	@FindBy(xpath="//tbody[not(contains(@style,'display:none'))]//a[contains(text(),'View usage')]")
	public WebElement lnkViewUsage1;
	
	@FindBy(xpath="//a[@href='/olam/cancelSelectRatePlan.myworld']|//a[@id='Cancel']")
	public WebElement lnkCancel;
	
	@FindBy(xpath="//*[@id='btnCancel']/a")
	public WebElement lnkCancelSwitch;
	
	@FindBy(xpath="//a[@class='usm_click']")
	public WebElement lnkSelectCTNFromList;
	
	@FindBy(xpath="//tbody[not(contains(@style,'display:none'))]//a[contains(text(),'View all plans')]")
	public WebElement lnkSeeAllPlans;
	
	@FindBy(xpath="(//div[@class='btmBdrdot MarLeft20 MarRight20 aligmentTd1'])[2]//a[text()='View usage']")
	public WebElement lnkViewUsageRecommenderTool;
	
	@FindBy(xpath="//tbody[not(contains(@style,'display:none'))]//a[contains(text(),'View usage')]")
	public WebElement lnkViewUsageRecommenderTool1;
	
	@FindBy(xpath="//a[text()='Switch to Mobile Share Value']")
	public WebElement lnkSwitchToMobileSharePlan;
	
	@FindBy(xpath="//a[contains(text(),'Add or Change Services')]")
	public WebElement lnkAddService;
	
	@FindBy(xpath="//a[contains(text(),'Print')]")
	public WebElement lnkPrint;
	
	@FindBy(xpath="//tbody[@id='msrt_2']//a[@title='View all plans']")
	public WebElement lnkViewAllPlans;
	
	@FindBy(xpath="//table//a[@title='View all plans']")
	public WebElement lnkViewAllPlans1;
	
	@FindBy(xpath="//tbody[@id='msrt_2']//a[@name='View details']")
	public WebElement lnkViewDetails;
	
	@FindBy(id="newPlanViewDetails")
	public WebElement lnkViewDetails1;
	
	@FindBy(xpath="(//tbody[not(contains(@style,'display:none'))]//a[contains(text(),'View price details')])[1]")
	public WebElement lnkViewPriceDetails;
	
	@FindBy(xpath="//*[@id='EditEffectiveDate']")
	public WebElement lnkEditMSEffectiveDate;
	
	@FindBy(id="EditEffectiveDate")
	public WebElement lnkEditMSEffectiveDate1;
	
	@FindBy(xpath="//a[@linkname='Edit (Joining Plan)']")
	public WebElement lnkMobileShareEdit;
	
	@FindBy(xpath="//a[@linkname='Edit (Group Members)']")
	public WebElement lnkDeviceEdit;
	
	@FindBy(xpath="//a[@title='Learn more about Mobile Share Value pricing and Rollover Data']")
	public WebElement lnkLearnMore1;
	
	@FindBy(xpath="(//tbody[not(contains(@style,'display:none'))]//*[contains(text(),'Show')])[1]")
	public WebElement lnkShow;
	
	@FindBy(xpath="//*[contains(@id,'videoDiv_Video_Banner')]")
	public WebElement lnkVideo;
	
	@FindBy(xpath="(//li[contains(@class,'cancelled usmTitle')]//a)[1]")
	public WebElement lnkSuspendedCTN;
	
	@FindBy(xpath="//li[contains(@class,'cancelled usmTitle')][1]//a")
	public WebElement lnkSuspendedCTNMobileShare;
	
	@FindBy(xpath="//li[contains(@class,'cancelled usmTitle')][2]//a")
	public WebElement lnkSuspendedCTNFamilyTalk;
	
	@FindBy(xpath="//a[contains(text(),'Change plan')]")
	public WebElement lnkChangePlan;
	
	
	/*---------------------------
	 * Table objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//table[@id='sortable_table']//th")
	public WebElement tblNationPlans;
	
	@FindBy(xpath="//table[@id='nationcanada_sortable_table']//th")
	public WebElement tblNationWithCanada;
	
	@FindBy(xpath="//table//*[contains(text(),'Compare to')]")
	public WebElement tblRecommenderTool;
	
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[contains(@id,'Nation')]/img[@alt='select plan']")
	public WebElement btnSelectPlan;
	
	@FindBy(xpath="//span[contains(text(),'Select a Plan')]")
	public WebElement btnSelectAPlan;
	
	@FindBy(xpath="//*[@alt='Add Devices to this Plan']")
	public WebElement btnAddDeviceToThisPlan;
	
	@FindBy(xpath="//a[@id='Nation 900 w/Rollover® Minutes ']/img[@alt='select plan']")
	public WebElement btnSelectPlan1;
	
	@FindBy(xpath="//*[@id='Nation with Canada 900']/img")
	public WebElement btnSelect2;
	
	@FindBy(xpath="//input[@alt='Next']")
	public WebElement btnNext;
	
	@FindBy(xpath="//img[@alt='Next']")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//*[@alt='Select']")
	public WebElement btnSelect;
	
	@FindBy(xpath="//a[@title='Select this Plan'][@role='button']")
	public WebElement btnSelect1;

	@FindBy(xpath="//a[@id='idBtnAnchor'] | (//a[contains(text(),'Select this')])[1]")
	public WebElement btnSelectThisXXGBPlan;
	
	@FindBy(xpath="(//tbody[not(contains(@style,'display:none'))]//a[contains(@name,'SelectPlan')])[1]")
	public WebElement btnSelectThisPlan;
	
	@FindBy(xpath="//a[@title='Submit']")
	public WebElement btnSubmit1;
	
	@FindBy(xpath="//a[@class='cancelLink2 wt_Body']")
	public WebElement btnClose;
	
	@FindBy(xpath="//a[@class='closeModal alignIE focus_parent']//img[@alt='Close']")
	public WebElement btnCloseUsageFrm;
	
	@FindBy(xpath="(//img[@alt='Close'])[1]")
	public WebElement imgCloseFrame;
	
	@FindBy(xpath="//div[@class='csToggles']//a[@class='msrtPlusButton_2']")
	public WebElement btnPlus;
	
	@FindBy(xpath="//a[@id='plus']")
	public WebElement btnPlus1;
	
	@FindBy(xpath="(//tbody[not(contains(@style,'display:none'))]//a[@id='minus'])[2]")
	public WebElement btnMinus;
	
	@FindBy(xpath="//a[@id='minus']")
	public WebElement btnMinus1;
	
	@FindBy(xpath="//img[@title='Next']")
	public WebElement btnContinue;
	
	@FindBy(id="Back" )
	public WebElement btnBack;
	
	@FindBy(xpath="//*[@id='btnBack']/button")
	public WebElement btnBack3;
	
	@FindBy(xpath="//*[@id='btnSub']/button | //*[@id='Submit']/button")
	public WebElement btnSubmit3;
	
	@FindBy(xpath="//*[@id='btnSub']/button | //*[@id='Submit']/button")
	public WebElement btnSubmit2;
	
	@FindBy(xpath="//a[contains(@class,'closeModal')]//img[@alt='Close']")
	public WebElement btnCloseModal;
	
	
	/*------- obejcts added by osnhore - Starts here ------------ */
	
	
	@FindBy(xpath="//h1[contains(text(),'View or Change My Plan')]")   // Added by Ravinder
	public WebElement txtViewOrChangeRatePlan_1511;
	
	@FindBy(xpath="//*[@class='account-number']")   // Added by Ravinder
	public WebElement txtAccountNumber_1511;
	
	@FindBy(xpath=".//*[@id='usmModule']//a[@class='usm_click']")   // Added by Ravinder
	public WebElement lnkUSM_1511;
	
	@FindBy(xpath="//h2//*[contains(text(),'Choose a New Rate Plan')]")   // Added by Ravinder
	public WebElement txtChooseNewRatePlan_1511;
	
	@FindBy(xpath="//button[starts-with(@class,'small primary') and contains(text(),'Select this')] | //*[starts-with(@class,'small primary') and contains(@title,'Select this')]")
	public WebElement btnSelectThisPlan_1511;
	
	@FindBy(xpath="//h1[contains(text(),'Change my plan')] | //h1[contains(text(),'View or Change Rate Plan')]")   // Added by Monica
	public WebElement txtChangeMyPlanRWD;
	
	@FindBy(xpath="//p/a[contains(text(),'See plan details')]")   // Added by Monica
	public WebElement lnkSeePlanDetails;
	
		
	/*------- obejcts added by osnhore - End here ------------*/
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	
	public OR_ViewOrChangeRatePlan(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
			
		}
}
