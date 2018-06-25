package com.OR.MyATT;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_WirelessAddonConfirmation {

	
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
	
	@FindBy(xpath="//h1[text()='Confirmation']")
	public WebElement txtConfirmation;
	
	
	@FindBy(xpath="//p/strong[contains(text(),'You Added:')]")
	public WebElement txtYouAdded;
	
	@FindBy(xpath="//p/strong[contains(text(),'You Removed:')]")
	public WebElement txtYouRemoved;
	
	@FindBy(xpath="//span[@class='normalNumber']")
	public WebElement txtCTN;
	
	@FindBy(xpath="//div[@id='newAddOns']")
	public WebElement txtNewAddonName;
	
	@FindBy(xpath="//div[@id='newAddOns']/child::div[1]")
	public WebElement txtNewAddonCost;
	
	@FindBy(xpath="//div[@id='removeAddOns']")
	public WebElement txtRemoveAddonName;
	
	@FindBy(xpath="//div[@id='groupRemoveAddOns'] | //div[contains(@id,'emoveAddOns')]")
	public WebElement txtRemoveAddonName2;
	
	//@FindBy(xpath="div[contains(@id,'emoveAddOns')]//parent::div//span[contains(text(),'$')]")
	@FindBy(xpath="//div[contains(@id,'emoveAddOns')]//child::div[1]")
	public WebElement txtRemoveAddonCost;
	
	@FindBy(xpath="//div[contains(@id,'emoveAddOns')]//child::p[2]")
	public WebElement txtRemoveAddonCost2;
	
	@FindBy(xpath="//p[@class='font16 botMar0']/strong")
	public WebElement txtCTNName;
	
	@FindBy(xpath="//p[contains(text(),'This feature was removed from the following device(s).')]")
	public WebElement txtRemoveFromSharedDeviceContent;
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[contains(text(),'Go to My Wireless Service')]")
	public WebElement lnkGoToMyWirelessService;
		
	/*---------------------------
	 * Table objects add below
	 *---------------------------*/
	

	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/

	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	
	public OR_WirelessAddonConfirmation(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
			
		}
}
