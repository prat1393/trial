package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_AutoPayEnrollment 
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
	@FindBy(id="autoPayForm_paymentMethodSelected")
	public WebElement lstSelectPaymentMethod;
	
	@FindBy(id="paymentMethodForm.expirationMonth")
	public WebElement lstMonth;
	
	@FindBy(id="paymentMethodForm.expirationYear")
	public WebElement lstYear;
	
	
	/*---------------------------
	 * Check box objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	@FindBy(id="paymentMethodForm.newCreditCardCustomerName")
	public WebElement edtNameOnCard;
	
	@FindBy(id="paymentMethodForm.cardNumber")
	public WebElement edtcardNo;
	
	@FindBy(id="paymentMethodForm.cardVerificationNumber")
	public WebElement edtSecurityCode;
	
	@FindBy(id="paymentMethodForm.zip")
	public WebElement edtZipCode;
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//h1[contains(text(),'AutoPay Enrollment')] | //h1[contains(text(),'Manage AutoPay')]")
	public WebElement txtAutoPayEnrollment;
	
	@FindBy(xpath="//h1[contains(text(),'Enroll in AutoPay')]")
	public WebElement txtEnrollInAutopay;
	
	@FindBy(xpath="//span[@class='step3']")
	public WebElement txtStepConfirmation;
	
	@FindBy(xpath="//h1[contains(text(),'AutoPay Confirmation')] | //h2[contains(text(),'AutoPay status')]")
	public WebElement txtAutopayConfirmation;
	
	@FindBy(xpath="//*[contains(text(),'AutoPay information review')]")
	public WebElement txtAutopayReview;
	
	@FindBy(xpath="//*[contains(text(),'NOTE')]")
	public WebElement txtNoteBank;
	
	@FindBy(xpath="//span[contains(text(),'Select Payment Method')] | //label[contains(text(),'Payment method')]")
	public WebElement txtSelectPaymentMethod;
	
	@FindBy(xpath="//option[@value='trueprofile,1878881365']")
	public WebElement txtPaymentMethodDropDownOption2;
	
	@FindBy(xpath="//div[text()='Video Details']")
	public WebElement txtVideoDetails;
	
	@FindBy(xpath="//p[contains(text(),'You have successfully enrolled in AutoPay.')] | //p[contains(text(),'Your accounts are now successfully enrolled in AutoPay.')]")
	public WebElement txtSuccess;
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(id="ge5p_z2_s3001")
	public WebElement lnkOverview;
	
	@FindBy(xpath="//a[contains(text(),'Go to account overview')]")
	public WebElement lnkGoToAccountOverview;
	
	@FindBy(xpath="//a[contains(@linkname,'AutoPay Video')]")
	public WebElement lnkAutopayVideo;
	
	@FindBy(xpath="//button[text()='Close']")
	public WebElement lnkClose;
	
	@FindBy(xpath="//a[@linkname='Change Payment Method']")
	public WebElement lnkChangeAutoPayPayment;
	
	
	
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	@FindBy(xpath="//iframe[contains(@src,'Add Credit/Debit Card')]")
	public WebElement frmNewDebitCreditCard;
	
	@FindBy(xpath="//iframe[contains(@src,'Add Checking/Savings Account')]")
	public WebElement frmCheckingPayment;
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[contains(text(),'Continue')]")
	public WebElement btnContinue;
	
	@FindBy(xpath="//a[contains(text(),'Continue')] | //a[@id='idBtnNext']")
	public WebElement btnNext;
	
	@FindBy(xpath="//a[contains(text(),'Submit')]")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//a[contains(text(),'Back')]")
	public WebElement btnBack;
	
	@FindBy(xpath="//a[contains(text(),'Discontinue AutoPay')]")
	public WebElement btnDisContinue;
	
	@FindBy(xpath="//h1[contains(text(),'Discontinue AutoPay')]")
	public WebElement txtDiscontinuePage;
	
	@FindBy(xpath="//h1[contains(text(),'Discontinue AutoPay Confirmation')]")
	public WebElement txtDiscontinueConfirmation;
	
	
	
	
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_AutoPayEnrollment(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
