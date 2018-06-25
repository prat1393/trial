package com.OR.MyATTZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_AgentActivity {

	WebDriver driver;
	
	
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[contains(text(),'Agent Activity')]") 
	public WebElement txtAgentActivity;
	
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
	@FindBy(xpath="//input[@name='attUID']") 
	public WebElement edtATT_UID;
	
	@FindBy(xpath="//input[@name='startDate']") 
	public WebElement edtStartDate;
	
	@FindBy(xpath="//input[@name='endDate']") 
	public WebElement edtEndDate;
	
	
	/*---------------------------
	 *  objects add below
	 *---------------------------*/
	
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//input[contains(@src,'search')]") 
	public WebElement btnSearch;
	
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	
	
	
	


	public OR_AgentActivity(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
