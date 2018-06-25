package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_AlertDetails {
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
		
	@FindBy(xpath="//h1[contains(text(),'Alert')]") 
	public WebElement txtAlertDetailsTitle;
	
	@FindBy(xpath="//div[@id='profile-details']//dt") 
	public WebElement txtAccountNickName;
	
	@FindBy(xpath="//div[@id='profile-details']//dd") 
	public WebElement txtAccountNumber;
	
	
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




	public OR_AlertDetails(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
