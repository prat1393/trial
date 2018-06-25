package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_MyAccountAccess {
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
	@FindBy(xpath="//h1[contains(text(),'My Account Access')]")
	public WebElement txtMyAccountAccessHeading;
		
	@FindBy(xpath="//*[@class='box extend3o']//td[contains(text(),'Pending acceptance')]")
	public WebElement txtPendingAcceptance;

	@FindBy(xpath="//h2[contains(text(),'Primary online access')]")
	public WebElement txtPrimaryOnlineAccess;
	
	@FindBy(xpath="//h2[contains(text(),'Secondary online access')]")
	public WebElement txSecondaryOnlineAccess;
	
	@FindBy(xpath="//h1[contains(text(),'Manage Sub-Accounts')]")
	public WebElement txtManageSubAccounts;
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[text()='Overview']")
	public WebElement lnkOverview;
	
	@FindBy(xpath="//a[contains(text(),'De-Link email account')]|//a[contains(text(),'De-Link Account')]")
	public WebElement lnkDeLinkEmailAccount;
	
	@FindBy(xpath="//a[contains(text(),'Offer Others Account Access')]")
	public WebElement lnkOffersOthersAccountAccess;
		
	@FindBy(xpath="//a[contains(text(),'De-Link email account')]") // Dont merge this xpath with any other xpath
	public WebElement lnkDeLinkEmailAccountOnly;
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	
		
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_MyAccountAccess(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
