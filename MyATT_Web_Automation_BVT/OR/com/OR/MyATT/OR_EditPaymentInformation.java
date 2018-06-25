package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_EditPaymentInformation {
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
	 * Checkbox objects add below
	 *---------------------------*/


	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//h1[contains(text(),'Edit payment information')]")
	public WebElement txtEditPaymentInformation;
	
	@FindBy(xpath="//td[contains(text(),'Debit / credit card')]")
	public WebElement txtPaymentMethodCreditDebit;
	
	@FindBy(xpath="//p[contains(text(),'Using your bank account as a payment')]")
	public WebElement txtNOTEEditPaymentInfoPage;
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[@id='imgNext']")
	public WebElement lnkNextEditPaymentInfoPage;
	



	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	


	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;




	public OR_EditPaymentInformation(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
