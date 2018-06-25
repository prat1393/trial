
package com.OR;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_SystemX {
	WebDriver driver;


	/*---------------------------
	 * Text objects add below
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
	@FindBy(name="userid") 
	public WebElement Login_txtATTUID;
	
	@FindBy(name="password") 
	public WebElement Login_txtPassword;
	
	/*---------------------------
	 *  Text objects add below
	 *---------------------------*/

	@FindBy(id="srv_TitleMsg") 
	public WebElement LogOn_lblSuccessMessage;
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/

	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/

	@FindBy(name="btnSubmit") 
	public WebElement Login_btnOK;
	
	@FindBy(name="successOK") 
	public WebElement LogOn_btnOK; 
	
	@FindBy(xpath="//img[contains(@alt,'Logout')]") 
	public WebElement btnLogout;
	
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	
	
	
	
	public OR_SystemX(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
