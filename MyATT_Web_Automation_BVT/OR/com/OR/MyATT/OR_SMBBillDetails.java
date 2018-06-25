package com.OR.MyATT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OR_SMBBillDetails {
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	 @FindBy(partialLinkText="Bill Summary")
	 public WebElement lnkViewBillSummary;
	 
	 @FindBy(linkText="View Account Profile")
	 public WebElement lnkViewAccountProfile;
	 
	 @FindBy(linkText="Business Phone Details")
	 public WebElement lnkBusinessPhoneDetails;
	 
	 @FindBy(linkText="Small Business")
	 public WebElement lnkSmallBusiness;
	 
	 @FindBy(linkText="Close window")
	 public WebElement lnkCloseWindow;
	 
	 @FindBy(linkText="BILL SUMMARY")
	 public WebElement lnkBillSummaryTab;
	 
	 
	 /*---------------------------
		 * Text objects add below
		 *---------------------------*/
	 @FindBy(xpath="//h1[contains(text(),'Bill Summary')] | //h1[contains(text(),'Bill & Payments')]")
	 public WebElement txtBillSummary;
	 
	 
	 @FindBy(xpath="//dt[contains(text(),'Account Owner')]")
	 public WebElement txtAccountOwner;
	 
	 
	 @FindBy(xpath="//dd[contains(text(),'Account Number')]")
	 public WebElement txtAccountNumber;
	 
	 @FindBy(xpath="//h2[contains(text(),'CURRENT PAYMENT & CHARGES')]")
	 public WebElement txtCurrentPaymentAndCharges;
	 
	 @FindBy(xpath="//strong[contains(text(),'Total Amount Due')]")
	 public WebElement txtTotalAmountDue;
	 
	 @FindBy(xpath="//strong[contains(text(),'$')]")
	 public WebElement txtTotalAmountDueValue;
	 
	 @FindBy(xpath="//p[contains(text(),'Information from statement dated')]")
	 public WebElement txtStatementDate;
	 
	 @FindBy(xpath="//p[contains(text(),'Bill-At-A-Glance')]")
	 public WebElement txtBillAtAGlance;

	 @FindBy(xpath="//strong[contains(text(),'Call Details')]")
	 public WebElement txtCallDetails;
	 
	 @FindBy(xpath="//h1[contains(text(),'Make a Payment')]")
	 public WebElement txtMakeaPayment;
	 
	 @FindBy(xpath="//h1[contains(text(),'PDF Not Available')]")
	 public WebElement txtPDFBillNotAvailable;
	 
	 
	 @FindBy(xpath="//div[@class='message']")
	 public WebElement txtNoteBillNotAvailable;
		
	 
	 /*---------------------------
		 * Button objects add below
		 *---------------------------*/
	 
	 @FindBy(xpath="//img[@alt='Make a Payment']")
	 public WebElement btnMakeAPayment;
	 
	 
	 @FindBy(xpath="//img[contains(@src,'viewFullBill')]")
	 public WebElement btnViewFullBill;
	 
	 /*---------------------------
		 * Dropdown objects add below
		 *---------------------------*/
	 @FindBy(xpath="//*[@id='serviceType']")
	 public WebElement drpDownSelectServiceProvider;
	 
	 @FindBy(xpath="//div[@class='message']")
	 public WebElement yellowMessageBox;
}
