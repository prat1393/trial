package com.OR.MyATT;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class OR_SuspendReactivateService {

	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	
	
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	@FindBy(id="select20")
	public WebElement lstSuspendReason;
	
	@FindBy(id="select21")
	public WebElement lstSuspendReason1;
	
	@FindBy(xpath="//select[contains(@id,'select')]")
	public WebElement lstSuspend_Reason;
	
	@FindBy(xpath="//div[@id='uniform-select20']")
	public WebElement lstSuspendReasonDropDown;
	
	@FindBy(xpath="//select[@title='Reason']//parent::div//span")
	public WebElement lstDefaultReasonforSuspension;
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//h1[contains(text(),'Suspend or Reactivate Your Service')]")
	public WebElement elmSuspendReactivateService;
	
	@FindBy(xpath="//select[@title='Reason']/option[@value='LOST']")
	public WebElement txtLost;
	
	@FindBy(xpath="//div[@class='w280 float-left MarTop5']/p[contains(text(),'Suspended')][contains(text(),'Lost')]")
	public WebElement elmSuspendCTN;
	
	@FindBy(xpath="//p[contains(text(),'Suspended')]")
	public WebElement txtSuspended;
	
	@FindBy(xpath="//p[contains(text(),'Active')]")
	public WebElement txtActive;
	
	@FindBy(xpath="//p[contains(text(),'Note: You've successfully reactivated service for this device')]")
	public WebElement txtReactivatNote;
	
	@FindBy(xpath="//strong[text()='Lost or Stolen Phone']")
	public WebElement txtLostOrStolenPhone;

	@FindBy(xpath="//p[contains(text(),'A misplaced')]")
	public WebElement txtLostOrStolenMessage;
	
	@FindBy(xpath="//p[contains(text(),'Check')]")
	public WebElement txtEmail;
	
	@FindBy(xpath="//h2//strong[contains(text(),'Reactivate Your Service')]")
	public WebElement txtReactivateYourService;
	
	@FindBy(xpath="//h1[contains(text(),'Are you sure')]")
	public WebElement txtAreYouSurePopup;
	
	@FindBy(xpath="//p[contains(text(),'ve successfully suspended service for this device')]")
	public WebElement txtConfirmationMessage;
	
	@FindBy(xpath="//p[contains(text(),'s no charge to suspend or reactivate your service. Remember, monthly charges still apply while service is suspended')]")
	public WebElement txtAreYouSurePopupContents;
	
	@FindBy(xpath="//strong[contains(text(),'Error')]")
	public WebElement txtError;
	
	@FindBy(xpath="//p[contains(text(),'successfully reactivated')]")
	public WebElement txtSuccessfulReactivation;
	
		/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[contains(text(),'Overview')]")
	public WebElement lnkOverview;
	
	@FindBy(xpath="//a[contains(text(),'File an insurance claim')]")
	public WebElement lnkFileAnInsurance;
	
	@FindBy(xpath="//a[contains(text(),'Replace this device')]")
	public WebElement lnkReplaceThisDevice;
	
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//img[@alt='suspend'][contains(@src,'suspend_disabled')]")
	public WebElement btnSuspendInactive;
	
	@FindBy(xpath="//span[@id='enbSubmitBtn0']//img")
	public WebElement btnSuspendInactiveButton1;
	
	@FindBy(xpath="//span[@id='dsbSubmitBtn0']//input")
	public WebElement btnSuspendActiveButton1;
	
	@FindBy(xpath="//input[contains(@id,'suspendEnableImg')]")
	public WebElement btnSuspendEnabled;
	
	@FindBy(xpath="//input[contains(@id,'suspendEnableImg1')]")
	public WebElement btnSuspendEnabled1;
	
	@FindBy(xpath="//input[@alt='Reactivate']")
	public WebElement btnReactivate;
	
	@FindBy(xpath="//img[@id='confirmImg']")
	public WebElement btnConfirm;
	
	
	
	
	
	/*---------------------------
	 * Table objects add below
	 *---------------------------*/
	@FindBy(xpath="//table//*[text()='Reason']")
	public WebElement tblLostStolenCustRequest;

	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	@FindBy(xpath="//iframe[contains(@src,'suspendOrReactivateModal')]")
	public WebElement frmSuspendOrReactivateModal;
	
	@FindBy(xpath="//h1[contains(text(),'Are you sure')]")
	public WebElement elmAreYouSure;
	
	@FindBy(xpath="//p[contains(text(),'Reason for Suspension')]")
	public WebElement txtReason;
	
	@FindBy(xpath="//a[contains(text(),'Cancel')]")
	public WebElement lnkCancel;
	
	@FindBy(xpath="//a[@id='Confirmenabled']")
	public WebElement lnkConfirm;
	
	
	@FindBy(xpath="//a[contains(text(),'Close')]")
	public WebElement lnkClose;
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;
	
	public OR_SuspendReactivateService(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
