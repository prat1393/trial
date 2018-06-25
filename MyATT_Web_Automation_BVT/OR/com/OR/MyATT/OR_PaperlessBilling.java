package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_PaperlessBilling {
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/

	@FindBy(xpath="//img[contains(@src,'Main_computer')]")
	public WebElement imgPaperlessBillingMonitor;

	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/

	/*---------------------------
	 * iFrame objects add below
	 *---------------------------*/
	@FindBy(xpath="//iframe[contains(@src,'paperless_Billing_Disclosure')]") 
	public WebElement frmPaperLessBillingDisclosure;
	
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
	@FindBy(xpath="//h1[contains(text(),'Paperless Billing')]") 
	public WebElement txtPaperlessBillingTitle;
	
	@FindBy(xpath="//strong[text()='Account']") 
	public WebElement txtAccount;
	
	@FindBy(xpath="//p[contains(text(),'Manage your account almost anywhere, anytime')]") 
	public WebElement txtHTMLMsg;
	
	@FindBy(xpath="//h1[contains(text(),'Paperless Billing Disclosure')]") 
	public WebElement txtPaperlessBillingDisclosure;
		
	@FindBy(xpath="//strong[contains(text(),'Paperless Bill and Bill Notice Disclosures')]") 
	public WebElement txtPaperlessBillNoticeDisclosure;
	
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[contains(text(),'No, thanks')]") 
	public WebElement lnkNoThanks;
	
	@FindBy(xpath="//a[contains(text(),'paperless billing disclosure')]") 
	public WebElement lnkPaperlessBillingDisclosure;

	@FindBy(xpath="//a[contains(text(),'Close')]") 
	public WebElement lnkClose;
	
	

	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	



	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;




	public OR_PaperlessBilling(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
