package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_InternationalFeatures {
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
		
	@FindBy(xpath="//h1[text()='International Features']")
	public WebElement txtInternationalFeatures;
	
	@FindBy(xpath="//a[@name='CTN'] | //*[@class='normalNumber']")
	public WebElement txtCTN;
	
	@FindBy(xpath="//div[contains(@class,'equalPad')]//p[2] | //*[@class='normalNumber']//parent::p//parent::div//p[2]")
	public WebElement txtDeviceMakeAndModel;
	
	@FindBy(xpath="//p[contains(text(),'How will you use your device?')]")
	public WebElement txtHowWillYouUseYourDevice;
	
	@FindBy(xpath="//*[contains(text(),'My current international features')]")
	public WebElement txtMyCurrentInternationalFeatures;
	
	@FindBy(xpath="//p[contains(text(),'Where are you going?')]")
	public WebElement txtWhereAreYouGoing;
	
	@FindBy(xpath="//label[contains(@for,'addons')]")
	public WebElement txtAddOnFeature;
	
	@FindBy(xpath="//strong[contains(text(),'$') and contains(text(),'.')]")
	public WebElement txtCost;
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[@id='ChooseAnotherDevice']")
	public WebElement lnkChooseAnotherDevice;

	@FindBy(xpath="//a[@id='CoveredCountries']")
	public WebElement lnkSeeGlobalCoverage;
	
	@FindBy(xpath="//div[contains(@class,'myAccount')]//a[@id='ManageCurrentFeature']")
	public WebElement lnkViewOrChange;
	
	@FindBy(xpath="//a[contains(text(),'Return to Manage my device & features')]")
	public WebElement lnkReturnToManageDevices;
	
	@FindBy(xpath="//a[@id='StartOver']")
	public WebElement lnkStartOver;

	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//button[contains(text(),'Travel outside the U.S.')]")
	public WebElement btnTravelOutsideUS;

	@FindBy(xpath="//button[contains(text(),'Go on a cruise')]")
	public WebElement btnGoOnACruise;
	
	@FindBy(xpath="//button[contains(text(),'Call / text another country from U.S.')]")
	public WebElement btnCallOrTextAnotherCountry;
	
	@FindBy(xpath="//button[contains(text(),'Pay-per-use')]")
	public WebElement btnPayPerUse;
	
	@FindBy(xpath="//button[contains(text(),'Mexico')] | //button[contains(text(),'Canada')]")
	public WebElement btnMexico;
	
	@FindBy(xpath="//button[contains(text(),'Another Country')]")
	public WebElement btnAnotherCountry;

	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;




	public OR_InternationalFeatures(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}

