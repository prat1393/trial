package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_EditPaymentProfile {
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/


	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/


	/*---------------------------
	 * List objects add below
	 *---------------------------*/

	@FindBy(xpath="//option[@value='01']")
	public WebElement lstExpiryDate;
	
	
	@FindBy(xpath="//option[@value='2016']")
	public WebElement lstExpiryYear;
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/


	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	@FindBy(id="bankCustomerName") 
	public WebElement edtNameOnBankAccount;
	
	@FindBy(id="routingNumber") 
	public WebElement edtRoutingNo;
	
	@FindBy(id="bankAccountNumber") 
	public WebElement edtBankAccountNo;
	
	@FindBy(id="bankAccountNumberConfirm")
	public WebElement edtReBankAccountNo;
	
	@FindBy(xpath="//input[@id='creditCardCustomerName']")
	public WebElement edtCreditCardCustomerName;
	
	@FindBy(xpath="//input[@id='cardNumber']")
	public WebElement edtCreditCardNumber;
	
	@FindBy(xpath="//input[@id='zip']")
	public WebElement edtZip;
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
		
	@FindBy(xpath="//h1[contains(text(),'Edit Payment Profile')] | //h1[contains(text(),'Edit Stored Payment Method')]") 
	public WebElement txtEditPaymentProfile;
	
	@FindBy(xpath="//div[@class='btnLt inline-links MarTop7']") 
	public WebElement txtCancelDelete;
	
	@FindBy(id="bankCustomerNameErrorMsg") 
	public WebElement txtBankCustomerNameErrorMsg;
	
	@FindBy(id="RoutingNumberErrorMsg") 
	public WebElement txtRoutingNumberErrorMsg;
	
	@FindBy(id="bankAccountNumberErrorMsg") 
	public WebElement txtbankAccountNumberErrorMsg;
	
	@FindBy(id="bankReAccountNumberErrorMsg") 
	public WebElement txtbankReAccountNumberErrorMsg;
	
	@FindBy(id="bankCustomerNameErrorMsg") 
	public WebElement txtbankReAccountNumberErrorMsg1;
	
	@FindBy(id="bankReAccountNumberErrorMsg") 
	public WebElement txtbankReAccountNumberErrorMsg2;
	
	@FindBy(id="bankReAccountNumberErrorMsg") 
	public WebElement txtbankReAccountNumberErrorMsg3;
	
	@FindBy(id="bankReAccountNumberErrorMsg") 
	public WebElement txtbankReAccountNumberErrorMsg4;
	
	@FindBy(xpath="//p[@id='divExpireAlert']") 
	public WebElement txtExpiryDate;
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	



	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(id="submitImage") 
	public WebElement btnSubmit;


	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;




	public OR_EditPaymentProfile(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
