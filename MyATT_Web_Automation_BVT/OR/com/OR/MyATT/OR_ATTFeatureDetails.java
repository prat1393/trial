package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_ATTFeatureDetails 
{
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//img[@alt='calendarIcon']")
	public WebElement imgCalenderIconOnSelectDateFrame;	
	
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	@FindBy(xpath="//div[@id='uniform-addon1']")
	public WebElement radMessaging;
	
	@FindBy(xpath="//div[@id='uniform-addon1']//input")
	public WebElement radMessagingInput;
	
	
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	@FindBy(xpath="//strong[contains(text(),'Remove This Feature')]")
	public WebElement radRemoveThisFeature;
	

	
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[contains(@class,'title')]//*[contains(text(),'AT&T Passport')]") 
	public WebElement txtAttPassportTitle ;
	
	@FindBy(xpath="//*[contains(@class,'title')]//*[text()='Review Changes']") 
	public WebElement txtReviewChangesTitle ;

	@FindBy(xpath="//*[text()='Select Effective Date']") 
	public WebElement txtSelectEffectiveDateOnEffectiveDateFrame ;
	
	@FindBy(xpath="//*[contains(@class,'title')]//*[text()='Confirmation']") 
	public WebElement txtConfirmation ;
	
	@FindBy(xpath="//p[text()='Current']/parent::div//label[contains(@id,'Passport')]") 
	public WebElement txtCurrentPlan ;
	
	@FindBy(xpath="//p[contains(text(),'Pending')]/parent::div//label[contains(@id,'Passport')]") 
	public WebElement txtPendingPlan ;
	
	@FindBy(xpath="//h1[contains(text(),'Texting')]") 
	public WebElement txtTexting ;
	
	@FindBy(xpath="//div[contains(@class,'btnChecked ')]//label|//div[contains(@class,'btnChecked Padbot15')]//label")
	public WebElement txtDefaultSelectedFeature;
	
	@FindBy(xpath="//div[@class,'btnChecked')]//label")
	public WebElement txtNewlySelectedFeature;
	
	@FindBy(xpath="//div[@class='btnChecked']//label/strong[contains(text(),'Messaging')]")
	public WebElement txtNewlySelectedFeatureMessaging;
	
	@FindBy(xpath="//*[contains(text(),'Tell us')]")
	public WebElement txtTellUs;
	
	@FindBy(xpath="//div[contains(text(),'Effective')]")
	public WebElement txtEffective;
	
	@FindBy(xpath="//*[contains(text(),'Ends')]")
	public WebElement txtEnds;
	
	
	/*---------------------------
	 * EditBox objects add below
	 *---------------------------*/
	@FindBy(xpath="//input[@title='Start Date']")
	public WebElement edtStartDate;
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[contains(@id,'newAddOn')]//a[text()='Edit']") 
	public WebElement lnkEdit ;
		
	@FindBy(xpath="//a[contains(text(),'Edit')]") 
	public WebElement lnkEditDate ;
	
	@FindBy(xpath="(//*[contains(text(),'Messaging Unlimited')]//a)[1] | //a[@href='/olam/detailFeatures.myworld?selectedFeaturesCat=grp10189308-olam&socCode=MSGM2AMUN&reportActionEvent=A_FTRS_FEATURE_DETAILS_SUB']") 
	public WebElement lnkManageOrViewOrChange;
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//button[contains(text(),'Select & Review') and contains(@onclick,'disableNextAnchor')]")
	public WebElement btnSelectAndReview;
	
	@FindBy(xpath="//button[contains(text(),'Continue')]")
	public WebElement btnContinueOnConflictModelFeaturesFrame;
	
	@FindBy(xpath="//a[@name='Select']")
	public WebElement btnSelectOnSelectEffectiveDateFrame;
	
	@FindBy(xpath="//a[@id='submit'][contains(text(),'Submit')]")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//div[@id='btnNext']//button[contains(text(),'Select & Review')]")
	public WebElement btnSelectAndReviewEnabled;
	
	@FindBy(xpath="//button[contains(text(),'Continue')]")
	public WebElement btnContinueInModalWindow;
	
	@FindBy(xpath="//*[contains(@alt,'calendar')]")
	public WebElement btnCalendar;	
	
	@FindBy(xpath="//a[@name = 'Next']")
	public WebElement btnNextMonth;		
	
	@FindBy(xpath="//a[@name = 'Prev']")
	public WebElement btnPrevMonth;		
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
		
	@FindBy(xpath="//iframe[contains(@src,'conflictModelFeatures')]")
	public WebElement frmConflictModelFeatures;
	
	@FindBy(xpath="//iframe[contains(@src,'effDate')]")
	public WebElement frmSelectEffectiveDate;
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;
	public OR_ATTFeatureDetails(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
