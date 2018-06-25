package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_ReviewPaymentDetails {
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[contains(@class,'button')][text()='Submit']") 
	public WebElement btnSubmit;
	
	@FindBy(xpath="//a[text()='Back']") 
	public WebElement btnBack;
	
	@FindBy(xpath="//a[@id='idSubmit']") 
	public WebElement btnSubmitFrm;
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//div[contains(@class,'title')]/*[text()='Review Payment Details']") 
	public WebElement txtReviewPaymentDetailsTitle;
	
	@FindBy(xpath="//p[contains(text(),'Account')] | //ul[contains(@class,'map-account-info')]//*[contains(text(),'Account')]") 
	public WebElement txtAccount;
	
	@FindBy(xpath="//strong[text()='Terms & Conditions']") 
	public WebElement txtTC;
	
	@FindBy(xpath="//strong[text()='Current Payments']") 
	public WebElement txtCurrentPayment;
	
	@FindBy(xpath="//span[contains(text(),'Name on Card')]") 
	public WebElement txtNameOnCard;
	
	@FindBy(xpath="//span[contains(text(),'Card Number')]") 
	public WebElement txtCardNo;
	
	@FindBy(xpath="//span[contains(text(),'Expiration Date')]") 
	public WebElement txtExpirationDate;
	
	@FindBy(xpath="//span[contains(text(),'Billing ZIP Code')]") 
	public WebElement txtBillingZipcode;
	
	@FindBy(xpath="//strong[contains(text(),'Terms')]") 
	public WebElement txtTermsAndConditions;
	
	@FindBy(xpath="//label[text()='Payment method'] | //*[contains(text(),'Payment method')]") 
	public WebElement txtPaymentMethod;
	
	@FindBy(xpath="//label[text()='Amount to pay'] | //*[contains(text(),'Amount to pay')]") 
	public WebElement txtAmountToPay;
	
	@FindBy(xpath="//label[text()='Pay on date'] | //*[contains(text(),'Pay on date')]") 
	public WebElement txtPayOnDate;
	
	@FindBy(xpath="//h2[text()='Review Payment']") 
	public WebElement txtReviewPaymentFrm;
	
	@FindBy(xpath="//span[contains(text(),'Enrolling in AutoPay')]") 
	public WebElement txtEnrollingInAutoPay;
	
	@FindBy(xpath="//strong[text()='Agreement']") 
	public WebElement txtAgreement;
	
	@FindBy(xpath="//p[contains(text(),'By clicking ')]") 
	public WebElement txtByClicking;
	
	@FindBy(xpath="//p[contains(text(),'You have not')]") 
	public WebElement txtIncompleteDivMsg;
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[text()='Edit payment information']") 
	public WebElement lnkEditPaymentInformation;
	
	@FindBy(xpath="//a[contains(text(),'Print Terms & Conditions')]") 
	public WebElement lnkPrintTC;
	
	@FindBy(id="cancel") 
	public WebElement lnkCancel;
	
	@FindBy(xpath="//a[text()='Yes,Continue']") 
	public WebElement lnkYes;
	
	@FindBy(id="Cancel") 
	public WebElement lnkCancelDiv;
	
	@FindBy(xpath="//a[text()='Cancel']") 
	public WebElement lnkCancel1;
	
	@FindBy(xpath="//div[contains(@class,'btnRt')]//a[contains(text(),'Make another payment')]") 
	public WebElement lnkMakeAnotherPayment;
	
	@FindBy(xpath="//div[contains(@class,'btnRt')]//a[contains(text(),'Go to Account Overview')]") 
	public WebElement lnkGoToAccountOverview;
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	
	public OR_ReviewPaymentDetails(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
