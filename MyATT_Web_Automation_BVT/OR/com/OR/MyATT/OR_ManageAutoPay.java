package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_ManageAutoPay 
{
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/

	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * Check box objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * Edit box objects add below
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy (xpath="//span[@class=' top2px']")
	 public WebElement txtAccount;
	
	@FindBy (xpath="//h1[contains(text(),'Discontinue AutoPay')]")
	 public WebElement txtDiscontinueAutopayHeading;
	
	@FindBy (xpath="//h1[contains(text(),'Discontinue AutoPay Confirmation')]")
	 public WebElement txtDiscontinueAutopayConfirmationHeading;
	
	//Manage my Autopay settings page
	@FindBy (xpath="//h1[text()='Manage My AutoPay Settings']")
	 public WebElement txtManageMyAutopaySettings;
	
	@FindBy(xpath="//h1[contains(text(),'Manage AutoPay')]")
	public WebElement txtManageAutoPay;
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy (xpath="//a[contains(text(),'Enroll my other accounts in AutoPay')]")
	 public WebElement lnkEnrollMyOtherAccountsInAutopay;
	
	
	@FindBy (xpath="//a[contains(text(),'Discontinue AutoPay')]")
	 public WebElement lnkDiscontinueAutoPay;
	
	@FindBy (xpath="//a[contains(text(),'Go to account overview')]")
	 public WebElement lnkGoToAccOverview;

	@FindBy (xpath="//a[text()='Update card expiration date']")
	 public WebElement lnkUpdateCardExpDate;
	
	@FindBy (xpath="//a[@linkname='Edit card expiration date'] | //a[@id='editCardExp']")
	 public WebElement lnkEditCardExpirationDate;
	
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	@FindBy (xpath="//iframe[contains(@src,'EXPIRED_CARD&editMethodName')]")
	 public WebElement frmCardInfo;
	//objs in frame
	@FindBy (xpath="//h1[text()='Enter your card information']")
	 public WebElement txtCardInfo;
	@FindBy (xpath="//label[text()='Card Expiration Date']")
	 public WebElement txtCardExpDate;
	@FindBy (id="expirationMonth")
	 public WebElement lstCardExpMonth;
	@FindBy (id="expirationYear")
	 public WebElement lstCardExpYear;
	@FindBy (xpath="//a[text()='Continue']")
	 public WebElement lnkContinue;
	@FindBy (xpath="//a[text()='Cancel']")
	 public WebElement lnkCancel;
	@FindBy (xpath="//a[text()='CVV']")
	 public WebElement lnkCVV;
	@FindBy (xpath="//a[text()='Close']")
	 public WebElement lnkClose;
	
	
	
	
	
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/

	@FindBy (xpath="//a[contains(text(),'Submit')]")
	 public WebElement btnSubmit;
	
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_ManageAutoPay(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
