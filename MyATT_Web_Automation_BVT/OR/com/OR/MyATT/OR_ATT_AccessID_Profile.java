package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_ATT_AccessID_Profile {
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	@FindBy(xpath="//img[contains(@class,'helpImg')]")
	public WebElement imgHelp;
		
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
	
	@FindBy(xpath="//*[contains(@class,'title')]//*[text()='My AT&T Access ID Profile']")
	public WebElement txtMyATTAccessIDProfile;
	
	
	@FindBy(xpath="//*[contains(text(),'Name:')]")
	public WebElement txtName; 
	
	@FindBy(xpath="//*[contains(text(),'Contact Email:')]")
	public WebElement txtEmailID; 	
		
	@FindBy(xpath="//*[contains(text(),'AT&T Access ID:')]")
	public WebElement txtATTAccessID; 	

	@FindBy(xpath="//*[contains(@class,'title')]//*[contains(text(),'My Linked Accounts')]")
	public WebElement txtMyLinkedAccountsTitle;
	
	@FindBy(xpath="//p[contains(text(),'My Wireless Number')]") 
	public WebElement txtMyWirelessNo;
	
	@FindBy(xpath="//h2[contains(text(),'AT&T Access ID Information')]") 
	public WebElement txtAttAccessIdInformation;
	
	@FindBy(xpath ="//h2[contains(text(),'AutoPay Status')]")
	public WebElement txtAutoPayStatus;
	
	@FindBy(xpath ="//h1[contains(text(),'AT&T Access ID')]")
	public WebElement txtATTAccessIDHeader;
	
	@FindBy(xpath ="//h1[contains(text(),'Delete AT&T Access ID')]")
	public WebElement txtDeleteATTAccessIDHeading;
	
	@FindBy(xpath ="//p[contains(text(),'re-register accounts to another profile')]")
	public WebElement txtReRegisterAccountVerbiage;
	
	@FindBy(xpath ="//h1[contains(text(),'De-Link Account')]")
	public WebElement txtDeLinkAccountHeading;
	
	@FindBy(xpath ="//p[contains(text(),'You are about to de-link (remove access to) account')]")
	public WebElement txtDelinkModalVerbiage;
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[text()='AT&T Access ID Profile']")
	public WebElement lnkATTAccessIDProfile;

	@FindBy(xpath="//*[@class='profileLinks']//a[text()='Manage account access']")
	public WebElement lnkManageAccountAccess;
	
	@FindBy(xpath="//a[text()='Change AT&T Access ID']")
	public WebElement lnkChangeATTaccessID;
	
	@FindBy(xpath="//a[@class='arrowRt'][text()='Link another account']")
	public WebElement lnkLinkAnotherAccount;
	
	@FindBy(id="designate")
	public WebElement lnkUpdate;
	
	@FindBy(xpath ="//a[contains(text(),'De-Link Account')]")
	public WebElement lnkDeLink;
	
	@FindBy(xpath ="//a[contains(text(),'Sign up for AutoPay')]")
	public WebElement lnkSignUpForAutoPay;
	
	@FindBy(xpath ="//a[contains(text(),'Delete your AT&T Access ID')]")
	public WebElement lnkDeleteAccessID;
	
	@FindBy(xpath ="//a[contains(text(),'Close')]")
	public WebElement lnkClose;
	
	@FindBy(xpath ="//a[contains(text(),'Cancel')]")
	public WebElement lnkCancel;
	
	@FindBy(xpath ="//img[@alt='Continue']")
	public WebElement lnkContinue;
		
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
		
		
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_ATT_AccessID_Profile(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
}
