package com.OR.MyATTZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_MobileShareComparisionTool {
	
	WebDriver driver;
	
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	
	
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	@FindBy(xpath="//img[@id='btn_SDGExit']")
	public WebElement imgExit ;
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//*[contains(@id,'Slider')]")
	public WebElement lstDeviceNavigationBar ;
	
	
	@FindBy(xpath="//div[contains(@class,'select')]//span")
	public WebElement lstSelectDeviceOnAddDevice ;
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	
	
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	@FindBy(xpath="//input[@id='ctn']")
	public WebElement edtCTN;
	
	@FindBy(xpath="//*[@id='addDevicePopup']//div[@id='Device_id0']//input")
	public WebElement edtInputBox;
	
	@FindBy(xpath="//*[@id='addDevicePopup']/div//div[@id='Device_id2']//input")
	public WebElement edtInputBoxForTabletDevice;
	
	@FindBy(xpath="//input[@id='userdiscountrs']")
	public WebElement edtUserDiscountrs;
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//u[contains(text(),'Mobile Share')]")
	public WebElement txtMobileShareHeading ;
	
	@FindBy(xpath="//*[@class='sectionHead']//*[text()='Current Plan']")
	public WebElement txtCurrentPlanInHeader ;
	
	//@FindBy(xpath="//*[contains(@class,'currentPlanWrap')]//*[@class='sectionHead']//*[contains(@id,'PlanDetails') or @class='currentPlanDetails']")
	@FindBy(xpath="//img[@class='currentPlanDetails']")
	public WebElement txtPlanDetailsInHeaderCurrentPlanSection ;
	
	@FindBy(xpath="//*[contains(text(),'Add Device') or contains(@class,'addDevice')]")
	public WebElement txtAddDeviceOnDialogBox;
	
	@FindBy(xpath="//*[contains(@class,'mobileShareWrap')]//*[@class='sectionHead']//img[contains(@class,'mobileshareDetails')]")
	public WebElement txtPlanDetailsInHeaderMobileSharePlanSection ; 
	
	@FindBy(xpath="//*[text()='Mobile Share Details']")
	public WebElement txtMobileShareDetails;
	
	@FindBy(xpath="//*[contains(@class,'top')]//*[text()='COST']")
	public WebElement txtCost;
	
	@FindBy(xpath="//*[contains(@class,'heading ')]//*[text()='PLAN pre Discount']")
	public WebElement txtPlanPreDiscount;
	
	@FindBy(xpath="//*[text()='FAN Discount']")
	public WebElement txtFANdiscount;
	
	@FindBy(xpath="//*[contains(@class,'heading ')]//*[text()='PLAN w/discount']")
	public WebElement txtwDiscount;
	
	@FindBy(xpath="//*[text()='Current Plan Details']")
	public WebElement txtCurrentPlanDetails; 
	
	@FindBy(xpath="//div[contains(text(),'We see')]")
	public WebElement txtDeviceAdded;
	
	@FindBy(xpath="//span[@class='adddevicename']/parent::li//img[@src='msrt/Sdg/images/close-btn-2.png']")
	public WebElement txtDeviceAddedSuccess;
	
	@FindBy(xpath="//*[contains(text(),'We see you have added a device')]")
	public WebElement txtWeSeeYouAddedADevice;
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//b[contains(text(),'Modify Usage')]")
	public WebElement lnkModifyUsage;
	
	@FindBy(xpath="//a[contains(@onclick,'logEvent')]//*[contains(text(),'Apply discount')]")
	public WebElement lnkApplyDiscount;

	@FindBy(xpath="//a//*[text()='Data input rules']")
	public WebElement lnkDataInputRules; 
	
	@FindBy(xpath="//*[@id='mobileShareSection']//*[contains(text(),'Compare Plans')]")
	public WebElement lnkComparePlans;
	
	@FindBy(xpath="//a//b[contains(text(),'Benefits')]")
	public WebElement lnkATTNextBenefits; 
	
	@FindBy(xpath="(//a/u[contains(text(),'Details')])[1]")
	public WebElement lnkDetails ;
	
	@FindBy(xpath="//a//*[contains(text(),'Comparison based on Modified Usage')]")
	public WebElement lnkComparisonBasedOnModifiedUsage;
	
	@FindBy(xpath="//*[contains(@class,'Toggle')]//*[contains(text(),'Total Usage')]/parent::*")
	public WebElement lnkTotalUsage;
	
	@FindBy(xpath="//*[contains(@class,'Toggle')]//*[contains(text(),'Total Available')]/parent::*")
	public WebElement lnkTotalAvailable;
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[@target='_self']") 
	public WebElement btnClickHere;
	
	@FindBy(xpath="//input[@type='submit' or @type='button' or @value='SUBMIT']")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//*[@class='slideDown']")
	public WebElement btnSlideDownOfDeviceNavigationBar;
	
	@FindBy(xpath="//img[@class='addDeviceBTN']")
	public WebElement btnAddDevice;
	
	@FindBy(xpath="//div[@id='Device_id0']//img[@class='addbutton']")
	public WebElement btnAddOnDialogBox;
	
	@FindBy(xpath="//div[@id='Device_id2']//img[@class='addbutton']")
	public WebElement btnAddForTabletOnDialogBox;
	
	@FindBy(xpath="//div[@id='Device_id1']//img[@class='addbutton']")
	public WebElement btnAddOnDialogBoxForSmartphoneNoContract;
	
	@FindBy(xpath="//img[@id='btn_addAnotherDevice']")
	public WebElement btnAddAnotherDeviceOnDialogBox;
	
	@FindBy(xpath="//img[@id='recommend']")           // or @id='btn_recommend']") //img[@id='btn_recommend'] |  
	public WebElement btnShowNewComparisonOnModifyUsageDialogBox;
	
	@FindBy(xpath="//img[@id='btn_recommend']")  // | //img[@id='btn_recommend']")           // or @id='btn_recommend']") //img[@id='btn_recommend'] |  
	public WebElement btnShowNewComparisonOnAddDeviceDialogBox;
	
	@FindBy(xpath="//*[@id='currentplanLB']//img[@class='close' or //img[@class='close']]") 
	public WebElement btnCloseOnPlanDetailsPopup;
	
	@FindBy(xpath="//div[@id='currentplanMS']//img[@class='close']")
	public WebElement btnCloseOnPlanDetailsPopupForMobileShare; 
	
	@FindBy(xpath="//img[contains(@src,'search_again')]")
	public WebElement btnSearchAgain_ReturnToSearch;
	
	@FindBy(xpath="//img[@id='btn_recommend']")
	public WebElement btnShowNewComparisonOnDialogBox;
	
	@FindBy(xpath="//*[@id='pieChart']")
	public WebElement imgPieChart;
	
	@FindBy(id="pieerror")
	public WebElement imgPieError;
	
	@FindBy(xpath="//div[@id='addDevicePopup']//select")
	public WebElement btnDeviceSelect;

	@FindBy(xpath="//div[@id='Device_id0']//img[@class='exitBtn']")
	public WebElement btnDeviceExit;
	
	@FindBy(xpath="//a//*[contains(@src,'yes')]")
	public WebElement btnYes;
	
	@FindBy(xpath="//a//*[contains(@src,'No')]")
	public WebElement btnNo;
	
	@FindBy(xpath="//a//*[contains(@src,'exit')]")
	public WebElement btnExit;
	
	@FindBy(xpath="//img[@id='btn_changeusage' and contains(@src,'exit-button')]")
	public WebElement btnExitOnModifiedCurrentUsageDialogBox;
	
	/*---------------------------
	 * PopUp objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[contains(@id,'SuggestedPlan')]")
	public WebElement tblPopUpPlans;
	
	@FindBy(xpath="//*[contains(text(),'Customers must select from one of the current Mobile Share Value')]")
	public WebElement txtAlertWarning;
	
	@FindBy(xpath="//b[contains(text(),'You have modified your current')]")
	public WebElement txtYouHaveModifiedYourCurrentDeviceData;
	
	
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/

	
	
	
	public OR_MobileShareComparisionTool(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
