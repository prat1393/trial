package com.OR.MyATT;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OR_AccountInformation {
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	
	@FindBy(id="selectedAccountId")
	public WebElement edtBillingAccNum;
	
	@FindBy(id="zipCode")
	public WebElement edtZipCode;
	
	/*---------------------------
	 * Frames add below
	 *---------------------------*/
	
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	
	@FindBy(id="enbNextBtn")
	public WebElement btnNext;
	
	
	
	
	/*---------------------------
	 * Image objects add below
	 * *---------------------------*/
	
	@FindBy(xpath="//img[@alt='Back']")
	public WebElement imgBack;

	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	

	@FindBy(xpath="//div[@class='page-title ie7botMar0']/h1[contains(text(),'Select')]")
	public WebElement txtSelectTitle;
	
	@FindBy(xpath="//div[@class='page-title ie7botMar0']/h1[contains(text(),'Access')]")
	public WebElement txtAccessCode;
	
	@FindBy(xpath="//h2[contains(text(),'Account Names')]")
	public WebElement txtAccountNames;
	
	@FindBy(xpath="//p[contains(text(),'Change Passcode:')]")
	public WebElement txtChangePasscode;
	
	@FindBy(xpath="//h2[contains(text(),'Billing Contact Information')]")
	public WebElement txtBillingContInfo;
	
	@FindBy(xpath="//h2[contains(text(),'Billing Notifications')]")
	public WebElement txtBillingNotif;
	
	@FindBy(xpath="//h2[contains(text(),'Payment Options')]")
	public WebElement txtPaymentOption;
	
	@FindBy(xpath="//h2[contains(text(),'Authorized Users')]")
	public WebElement txtAuthoUsers;
	/*---------------------------
	 * Dropdown add below
	 *---------------------------*/
	
	@FindBy(id="selectedAccountType")
	public WebElement lstSelectAccount;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}











