package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_PayOffInstallmentPlan {
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
	@FindBy(tagName="h1")
	public WebElement txtPayOffInstallmentHeading;
	
	@FindBy(xpath="//h2[contains(text(),'Account')]")
	public WebElement txtAccountNumber;
	
	@FindBy(className="poip-user-info")
	public WebElement txtPlanName;
	
	@FindBy(className="poip-plan-info")
	public WebElement txtPlanID;
	
	
	/*---------------------------	
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[@name='showDetails']")
	public WebElement lnkShowDetails;
	

	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;





	public OR_PayOffInstallmentPlan(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
