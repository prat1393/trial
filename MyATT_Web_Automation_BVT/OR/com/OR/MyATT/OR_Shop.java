package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_Shop {
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	

	
	/*---------------------------
	 * Frames add below
	 *---------------------------*/
	
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(id="minicart-link")
	public WebElement lnkWirelessCart;
	
	@FindBy(xpath="//div[@id='deviceLayout']//div[contains(@id,'device_')]//a[contains(text(),'View')]")
	public WebElement lnkLGview;
	
	@FindBy(xpath="//a[contains(text(),'Add to Cart')]")
	public WebElement lnkAddToCart;
	
	@FindBy(xpath="//span[@id='minicart-count']")
	public WebElement lnkCartCount;
	
	@FindBy(xpath="//div[@class='cart-actions']/a[text()='Save cart']")
	public WebElement lnkSaveCart;
	
	@FindBy(id="save-cart-btn")
	public WebElement lnkSaveCartDiv;
	
	@FindBy(xpath="//form[@id='saveForm']//a[@id='cancel']")
	public WebElement lnkCancelDiv;
	
	@FindBy(xpath="//div[@class='linkFarmCol']//a[text()='Accessories']")
	public WebElement lnkAccessories;
	
	@FindBy(xpath="//div[@class='linkFarmCol']//a[text()='Ringtones & Apps']")
	public WebElement lnkRingtonesAndApp;
	
	@FindBy(xpath="//li/a[text()='TV']")
	public WebElement lnkTVTerNav;
	
	@FindBy(xpath="//li/a[text()='TV']//parent::li//div//li/a[text()='Add Receivers']")
	public WebElement lnkAddReceivers;
	
	@FindBy(xpath="//div[@class='pricesTileHeadline']/a[contains(text(),'Mobile Share Value Plans')]") // added by Hiral Jogi
	public WebElement lnkMobileShareValuePlans;

	
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[@role='button']")
	public WebElement lnkButtonCont;
	
	@FindBy(xpath="//a[@role='button' and text()='Save & Add Another Device']")
	public WebElement btnSaveAndAdd;
	
	
	
	
	/*---------------------------
	 * Image objects add below
	 * *---------------------------*/
	


	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//div[@class='breadcrumblink']")
	public WebElement txtShop;
	
	@FindBy(xpath="//h1[contains(text(),'Change Your Service')]")
	public WebElement txtChangeYourService;
	
	@FindBy(xpath="//h1[text()='Shop']")
	public WebElement txtShopHeading;
	
	@FindBy(xpath="//h1[contains(text(),'LG')]")
	public WebElement txtLG;
	
	@FindBy(xpath="//h1[contains(text(),'Add Devices to Your Existing Plan ')]")
	public WebElement txtAddDevicesToPlan;
	
	@FindBy(xpath="//h1[text()='Shopping Cart']")
	public WebElement txtShoppingCart;
	
	@FindBy(xpath="//h1[text()='Save Cart']")
	public WebElement txtSaveCartDiv;
	
	@FindBy(xpath="//p[contains(text(),'save the items')]")
	public WebElement txtSaveCartDivMsg;
	
	@FindBy(xpath="//h1[contains(text(),'Select an Account')]")
	public WebElement txtSelectAnAccount;
	
	@FindBy(xpath="//input[@id='insuranceX']")
	public WebElement txtDeclineInsurance;
	
	@FindBy(xpath="//h1[contains(text(),'Shop AT&T')]")  // added by Hiral Jogi
	public WebElement txtShopAtt;

	@FindBy(xpath="//h3[contains(text(),'Shop AT&T Plans')]") // Added By Hiral Jogi
	public WebElement txtShopATTPlans;


	@FindBy(xpath="//h3[contains(text(),'Latest Deals')]") // Added By Hiral Jogi
	public WebElement txtLatestDeals;

	@FindBy(xpath="//h3[contains(text(),'Services & More')]") // Added By Hiral Jogi
	public WebElement txtServicesandMore;
	
	@FindBy(xpath="//p[contains(text(),'What you get with Mobile Share Value')]") // Added By Hiral Jogi
	public WebElement txtMobileShareValue;
	
	@FindBy(xpath="//p[contains(text(),'Choose your data')]") // Added By Hiral Jogi
	public WebElement txtChooseyourdata;
	
	@FindBy(xpath="//p[contains(text(),'Add phones')]") // Added By Hiral Jogi
	public WebElement txtAddPhones;
	
	@FindBy(xpath="//p[contains(text(),'Add more devices')]") // Added By Hiral Jogi
	public WebElement txtAddmoredevices;
	
	@FindBy(xpath="//p[contains(text(),'Families ')]") // Added By Hiral Jogi
	public WebElement txtFamilies;
	
	@FindBy(xpath="//p[contains(text(),'Individual')]") // Added By Hiral Jogi
	public WebElement txtIndividual;
	
	
	/*---------------------------
	 * Dropdown add below
	 *---------------------------*/

	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_Shop(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

}

	
	
	
	
	

