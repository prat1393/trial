package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_PaymentConfirmation {
	

	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	
	 @FindBy(xpath="//img[@alt='Paperless_BillConfirm_1508_PSLOT']")
	 public WebElement imgEnrollInPaperlessBilling;
	
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//h1[contains(text(),'Confirmation')]") 
	public WebElement txtPaymentConfirmationTitle;
	
	@FindBy(xpath="//span[text()='Account']") 
	public WebElement txtAccount;
	
	@FindBy(xpath="//span[contains(text(),'Payment Method')]") 
	public WebElement txtPaymentMethod;
	
	@FindBy(xpath="//*[contains(text(),'Amount to Pay')]") 
	public WebElement txtAmountToPay;
	
	@FindBy(xpath="//strong[contains(text(),'Confirmation')]") 
	public WebElement txtConfirmation;
	
	@FindBy(xpath="//*[contains(text(),'Pay on Date')]") 
	public WebElement txtPayOnDate;
	
	@FindBy(xpath="//*[contains(text(),'Total Payments')]") 
	public WebElement txtTotalPayments;
	

	@FindBy(xpath="//*[contains(text(),'$') and contains(@class,'orange font')]") 
	public WebElement txtTotalPaymentsAmount;
	
	
	@FindBy(xpath="//*[contains(text(),'$') and contains(@class,'float-right orange')]") 
	public WebElement txtAmountValue;
	
	@FindBy(xpath="//*[contains(text(),'/') and contains(@class,'normal')]") 
	public WebElement txtPaymentDate;
	
	
	@FindBy(xpath="//strong[text()='Payment Confirmation']") 
	public WebElement txtPaymentConfFrm;
	
	@FindBy(xpath="//*[contains(text(),'Enrolled in AutoPay')]") 
	public WebElement txtEnrolledInAutopay;
	
	@FindBy(xpath="//div[contains(@class,'indicator')]//*[contains(text(),'Enter payment information')]") 
	public WebElement txtEnterPaymentInfoAccordion;
	
	@FindBy(xpath="//div[contains(@class,'indicator')]//*[contains(text(),'Review payment information')]") 
	public WebElement txtReviewPaymentInfoAccordion;
	
	@FindBy(xpath="//div[contains(@class,'indicator')]//span[contains(text(),'Payment status')]") 
	public WebElement txtPaymentStatusAccordion;
	
	
	@FindBy(xpath="//strong[text()='Payment Failure']") 
	public WebElement txtPaymentFailure;
	
	@FindBy(xpath="//*[contains(text(),'Thank you')]") 
	public WebElement txtThankyou;
	
	@FindBy(xpath="//*[contains(text(),'Autopay') or contains(text(),'autopay')]") 
	public WebElement txtAutopay;
	
	@FindBy(xpath="//*[@id='primary-content']//strong[contains(text(),'Note')]") 
	public WebElement txtNote;

	@FindBy(xpath="//a[@id='Make another payment']") 
	public WebElement lnkMakeAnotherPayment1;

	@FindBy(xpath="//*[@id='primary-content']//ul/li/p") 
	public WebElement txtNoteContent;
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[contains(text(),'Print')]") 
	public WebElement lnkPrint;
	
	@FindBy(id="Make another payment") 
	public WebElement lnkMakeAnotherPayment;
	
	@FindBy(id="Go to account overview") 
	public WebElement lnkGotoAccountOverview;
	
	@FindBy(xpath="//a[contains(text(),'Overview')]") 
	public WebElement lnkGotoAccountOverviewFrm;
	
	@FindBy(xpath="//div[contains(@class,'btnRt')]//a[contains(text(),'Make another payment')]") 
	public WebElement lnkMakeAnotherPaymentNew;
	
	@FindBy(xpath="//div[contains(@class,'btnRt')]//a[contains(text(),'Go to Account Overview')]") 
	public WebElement lnkGoToAccountOverviewNew;
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	
	public OR_PaymentConfirmation(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
