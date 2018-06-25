package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_MessageCenter {
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
		
	@FindBy(xpath="//p[@class='error']") 
	public WebElement txtAlertError;
	
	@FindBy(xpath="//h1[contains(text(),'Message Center')]") 
	public WebElement txtMessageCenterTitle;
	
	@FindBy(xpath="//h2[contains(text(),'Account')]") 
	public WebElement txtAccount;
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//p[@class='error']/a[contains(text(),'past due')]") 
	public WebElement lnkPastDueMessage;


	@FindBy(xpath="//a[@class='unread'][contains(@href,'Suspended')]")
	public WebElement lnkSuspendedMessage;
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	



	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;




	public OR_MessageCenter(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
