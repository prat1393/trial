package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_UpgradePhone {

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

	@FindBy(xpath="//*[contains(@class,'title')]//*[text()='Upgrade your device'] | //h1[contains(text(),'Upgrade Phone')]")
	public WebElement txtUpgradeYourDevice;
	
	@FindBy(xpath="//h1[contains(text(),'Upgrade Phone')]")
	public WebElement txtUpgradePhone;
	
	@FindBy(xpath="//*[contains(text(),'Emerald')]")
	public WebElement txtEmerald;
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[contains(text(),'Continue to myAT&T account management')]")
	public WebElement lnkContinuetoMyATTAccMgt;
	



	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[text()='Upgrade']")
	public WebElement btnUpgrade;

	@FindBy(xpath="//img[@alt='Check Now']")
	public WebElement btnCheckNow;
	
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;




	public OR_UpgradePhone(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
