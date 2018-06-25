package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_ATTSupportCenter {

	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/

	
	/*---------------------------
	 * Frames add below
	 *---------------------------*/
	
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/

	
	/*---------------------------
	 * Image objects add below
	 * *---------------------------*/

	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//h1[text()='AT&T Support Center']")
	public WebElement txtATTSupportCenter;
	
	@FindBy(xpath="//h2[text()='Wireless']")
	public WebElement txtWireless;
	
	@FindBy(xpath="//h2[text()='Digital TV']")
	public WebElement txtDTV;
	
	@FindBy(xpath="//h2[text()='Internet & Email']")
	public WebElement txtInternet;
	
	@FindBy(xpath="//h2[text()='Home Phone']")
	public WebElement txtHomePhone;
	
	@FindBy(xpath="//h2[text()='Home Security']")
	public WebElement txtHomeSecurity;
	
	/*---------------------------
	 * Dropdown add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_ATTSupportCenter(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}