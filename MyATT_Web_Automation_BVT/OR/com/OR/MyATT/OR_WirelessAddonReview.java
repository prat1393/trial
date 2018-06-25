package com.OR.MyATT;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_WirelessAddonReview {

	
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	
	
	
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/

	@FindBy(xpath="//input[@id='presentdate']//parent::span//parent::div")
	public WebElement rdoPresentDate;
	
	@FindBy(xpath="//input[@id='backdate']//parent::span//parent::div")
	public WebElement rdoBackDate;
	
	@FindBy(xpath="//input[@id='futuredate']//parent::span//parent::div")
	public WebElement rdoFutureDate;
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//h1[text()='Review Changes']")
	public WebElement txtReviewChanges;
	
	@FindBy(xpath="//p/strong[contains(text(),'re Adding:')]")
	public WebElement txtYouAreAdding;
	
	@FindBy(xpath="//p/strong[contains(text(),'re Removing:')]")
	public WebElement txtYouAreRemoving;
	
	@FindBy(xpath="//span[@class='normalNumber']")
	public WebElement txtCTN;
	
	@FindBy(xpath="//div[@id='newAddOns']")
	public WebElement txtNewAddonName;
	
	@FindBy(xpath="//div[@id='newAddOns']/child::div[1]")
	public WebElement txtNewAddonCost;
	
	@FindBy(xpath="//div[@id='emoveAddOns']")
	public WebElement txtRemoveAddonName;
	
	//@FindBy(xpath="//div[@id='groupRemoveAddOns']")
	@FindBy(xpath="//div[contains(@id,'emoveAddOns')]")
	public WebElement txtRemoveAddonName2;
	
	@FindBy(xpath="//div[contains(@id,'emoveAddOns')]//child::div[1]")
	public WebElement txtRemoveAddonCost;
	
	@FindBy(xpath="//div[contains(@id,'emoveAddOns')]//child::p[2]")
	public WebElement txtRemoveAddonCost2;
	
	@FindBy(xpath="//p[@class='font16 botMar0']/strong")
	public WebElement txtCTNName;
	
	@FindBy(xpath="//p[contains(text(),'This feature also will be removed from the following device(s).')]")
	public WebElement txtRemoveFromSharedDeviceContent;
	
	@FindBy(xpath="//p[contains(text(),'Removal Date')]")
	public WebElement txtRemovalDate;
	
	@FindBy(xpath="//p[contains(text(),'taxes & additional fees')]")
	public WebElement txtPricesAdditionalFees;
	
	@FindBy(xpath="//span[@id='dateSelected']")
	public WebElement txtDate;
	
	@FindBy(xpath="//p[contains(text(),'Effective:')]")
	public WebElement txtEffectiveDate;
	
	@FindBy(xpath="//h2[contains(text(),'Select Effective Date')]")
	public WebElement txtSelectEffectiveDateHeading;
	
	
	
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

	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[@linkname='Edit_Effective Date']")
	public WebElement lnkEdit;
	
	@FindBy(xpath="//a[@id='cancel']")
	public WebElement lnkCancel;
	
	@FindBy(xpath="//a[@id='back']")
	public WebElement lnkBack;
		
	/*---------------------------
	 * Table objects add below
	 *---------------------------*/
	

	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[@id='submit']")
	public WebElement btnSubmit;
	

	@FindBy(xpath="//a[@title='Remove']")
	public WebElement btnRemove;

	@FindBy(xpath="//a[@title='Select a date']")
	public WebElement btnSelectADate;
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	
	public OR_WirelessAddonReview(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
			
		}
}
