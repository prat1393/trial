package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_ATT {
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	//@FindBy(xpath="//img[contains(@src,'btnLinkMyAccount')]")
	@FindBy(xpath="//a[contains(text(),'Link your accounts')]")
	public WebElement imgLinkMyAccount;
	
	@FindBy(xpath="//a[@id='enbSubmitBtn']/img[@alt='Next']")
	public WebElement imgNext;
	
	@FindBy(xpath="//a[@name='contBtn']")
	public WebElement  imgContinue;
	
	@FindBy(xpath="//img[@alt='Yes']")
	public WebElement imgYes;
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	@FindBy(xpath="//dd[@class='radio4px botMar20']")
	public WebElement radIwantTo;
	
	@FindBy(xpath="//dd[@class='botMar20']//*[contains(text(),'I want to change my AT&T Access ID')]")
	public WebElement radChangeATT;
	
	@FindBy(xpath="//div[@class='MarLeft20']//*[contains(text(),'Go to myAT&T to manage my accounts')]")
	public WebElement radGoTo;
	
	@FindBy(xpath="//div[@class='MarLeft20']//*[contains(text(),'Link more accounts to this AT&T Access ID and password')]")
	public WebElement radLinkMore;
	
	@FindBy(xpath="//input[@id='radioavailable']")
	public WebElement radSelect;
	
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
//	@FindBy(xpath="//select[contains(@id,'installmentPaymentForm')]")
	@FindBy(xpath = "//select[contains(@id,'nstallmentPayment')]")
	public WebElement lstPaymentMethod;
	
	@FindBy(xpath="//select[contains(@id,'xpirationMonth')]")
	public WebElement lstExpirationMonth;
	
	@FindBy(xpath="//select[contains(@id,'xpirationMonth')]")
	public WebElement lstExpirationYear;
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	
	
	/*---------------------------
	 * button objects add below
	 *---------------------------*/
	@FindBy(xpath="//input[@type='image']")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//img[@src='/olam/English/brand30/bt/btnContinue.gif']")
	public WebElement btnContinue;
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	@FindBy(xpath="//input[@id='customerCode']")
	public WebElement edtCustomerCode;
	
	@FindBy(id="fname")
	public WebElement edtFname;
	
	@FindBy(id="lname")
	public WebElement edtLname;
	
	@FindBy(xpath="//input[contains(@id,'CustomerName')]")
	public WebElement edtCustomerName;
	
	@FindBy(xpath="//input[contains(@id,'cardNumber')]")
	public WebElement edtCardNumber;
	
	@FindBy(xpath="//input[contains(@id,'zip')]")
	public WebElement edtZipCode;
	
	@FindBy(xpath="//input[contains(@id,'VerificationNumber')]")
	public WebElement edtSecurityCode;
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//p[contains(text(),'Your request for access')]")
	public WebElement txtRequestForAccess;

	@FindBy(xpath="//h1[contains(text(),'Pay Off Installment')]")
	public WebElement txtPayOffInstallment;
	
	@FindBy(xpath="//h2[contains(text(),'Payment Profiles')]")
	public WebElement txtPaymentProfile;
	
	@FindBy(xpath="//h1[contains(text(),'Quick Account Registration')]")
	public WebElement txtQuickAccRegistration;
	
	@FindBy(xpath="//h2[contains(text(),'Your AT&T Access ID')]")
	public WebElement txtATTaccessID;
	
	@FindBy(xpath="//h2[contains(text(),'User Information')]")
	public WebElement txtUserInfo;
	
	@FindBy(xpath="//h1[contains(text(),'Register for AT&T Online Access')]")
	public WebElement txtRegisterForATT;
	
	@FindBy(xpath="//h2[contains(text(),'Your Account')]")
	public WebElement txtRegistrationComplete;
	
	@FindBy(xpath="//h1[contains(text(),'AT&T Access ID')]")
	public WebElement txtATTAccess;
	

	@FindBy(xpath="//h2[contains(text(),'Account Details')]")
	public WebElement txtAccountDetails;
	
	@FindBy(xpath="	//h2[contains(text(),'Account Overview')]")
	public WebElement txtAccountOverview;
	
	@FindBy(xpath="//h1[text()='Account Overview']")
	public WebElement txtSMBaccountOverview;
	
	@FindBy(xpath="//h2[contains(text(),'U-verse')]")
	public WebElement txtUverse;
	
	@FindBy(xpath="//h2[contains(text(),'Change Billing Address')]")
	public WebElement txtChangeBillingAddress;
	
	@FindBy(xpath="//span[contains(@class,'account')]")
	public WebElement txtAccountNumberOnPayOffInstallment;
	
	@FindBy(xpath="//span[contains(@class,'account')]//parent::h2")
	public WebElement txtNickNameOnPayOffInstallment;

	@FindBy(xpath="//*[contains(@class,'user-info')]")
	public WebElement txtPlanNameCTN;
	
	@FindBy(xpath="//*[contains(text(),'Installment plan ID')]")
	public WebElement txtInstallmentID;
	
	@FindBy(xpath="//h2[contains(text(),'Stored Payment')]")
	public WebElement txtStoredPaymentMethod;
	
	@FindBy(xpath="//p[contains(text(),'Add, edit, or delete your stored payment methods to make paying bills faster and easier')]")
	public WebElement txtStoredPaymentMethodExplanation;
	
	@FindBy(xpath="//h1[contains(text(),'Delete')]")
	public WebElement txtDeleteATTAccessID;
	
	@FindBy(xpath="//p[contains(text(),'delete')]")
	public WebElement txtDeleteFrameVerbiage;
	
	@FindBy(xpath="//span[@id='notEnroll' or contains(text(),'Not Enrolled')]")
	public WebElement txtNotEnrolledAutopay;
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[@onclick='noChanges()'] | //a[contains(text(),'Cancel')]")
	public WebElement lnkCancel;
	
	@FindBy(xpath="//*[contains(@src,'slidCancelPopup.jsp')]")
	public WebElement frmCancelRequest;
	
	@FindBy(xpath="//a[@id='Account+ending+in+7890']/parent::*/a[@title='Edit']")
	public WebElement lnkEdit;
	
	@FindBy(id="addNewPaymentLink")
	public WebElement lnkAddNewPayment;
	
	@FindBy(xpath="//a[contains(text(),'Billing & Payments')]")
	public WebElement lnkBillingAndPayments;
	
	@FindBy(xpath="//a[contains(text(),'Print')]")
	public WebElement lnkPrint;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	public WebElement lnkSMBLogout;
	
	@FindBy(id="accountInfo")
	public WebElement lnkAccountInfo;
	
	@FindBy(xpath="//img[@alt='Back']")
	public WebElement lnkBack;
	
	@FindBy(xpath="//a[contains(text(),'Show installment plan details')]")
	public WebElement lnkShowInstallmentPlanDetailsOnPayOff;
	
	@FindBy(xpath="//div[contains(@class,'row-seamless')]/child::*//label[contains(text(),'Account ending')]/parent::*//a[contains(text(),'Edit')]|//form/child::*//label[contains(text(),'saving')]/parent::*/parent::*//a[contains(text(),'Edit')] | //label[contains(text(),'abc')]/parent::div/parent::div//a[contains(text(),'Edit')]")
	public WebElement lnkEdit_For_AccountEnding_StoredPaymentMethod;	

	@FindBy(xpath="//a[contains(text(),'Continue')]")
	public WebElement lnkContinue;
	
	@FindBy(xpath="//*[contains(@alt,'access ID profile')]/parent::a")
	public WebElement lnkManageMyATTAcessID;
	
	@FindBy(xpath="//a[contains(text(),'Delete your AT&T Access ID')]")
	public WebElement lnkDeleteATTAccessID;
	
	@FindBy(xpath="//span[@id='notEnroll' or contains(text(),'Not Enrolled')]//a[contains(text(),'Enroll in AutoPay')]")
	public WebElement lnkEnrollInAutopay;
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	@FindBy(xpath="//iframe[contains(@src,'addProfilePopUp')]")
	public WebElement frmAddNewPayment;
	
	@FindBy(xpath="//iframe[contains(@src,'Add Credit/Debit Card')]")
	public WebElement frmNewDebitCreditCardPaymentMethod; 
	
	@FindBy(xpath="//iframe[contains(@src,'delete')]")
	public WebElement frmDeleteProfile;
	//Elements in Add new payment frame
	@FindBy(xpath="//p")
	public WebElement txtSelectPaymentMethod;
	
	@FindBy(xpath="//div[@class='radio']")
	public WebElement radRadio;
	
	@FindBy(xpath="//input[@class='radiohead-1']")
	public WebElement radChecking;
	
	@FindBy(xpath="//input[@class='radiohead-2']")
	public WebElement radCredit;
	
	@FindBy(xpath="//a[contains(@class,'closeModal')]")
	public WebElement lnkClose;
	
	@FindBy(xpath="//img")
	public WebElement imgCreditCards;
	
	//End of Elements in Add new payment frame
	
	
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_ATT(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}

