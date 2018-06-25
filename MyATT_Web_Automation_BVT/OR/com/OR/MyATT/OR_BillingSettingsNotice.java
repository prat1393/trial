package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_BillingSettingsNotice 
{
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
	 * Check box objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/

	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//h1[contains(text(),'Billing Notice Settings')]")
	public WebElement txtBillingSettingsNotice;
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(id="billThresholdToggleLabel")
	public WebElement lnkThresholdToggle;
	
	
	
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/

	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/

	
	
	
	
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_BillingSettingsNotice(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
