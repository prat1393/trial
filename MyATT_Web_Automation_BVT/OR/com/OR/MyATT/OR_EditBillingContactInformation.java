package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_EditBillingContactInformation {
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	

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
	@FindBy(xpath="//h1[text()='Edit Billing Contact Information']")
	public WebElement txtEditBillingContactInformation;
	

	
	
	
	/*---------------------------	
	 * Link objects add below
	 *---------------------------*/

	

	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	public OR_EditBillingContactInformation(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
