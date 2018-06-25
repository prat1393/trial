package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_ManagePlentiCard {
	
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

	@FindBy(xpath="//a[contains(@class,'button primary small')][contains(text(),'Manage Plenti link')]")
	public WebElement btnManagePlentiLink;
	
	
	
	/*---------------------------
	 * Image objects add below
	 * *---------------------------*/
	


	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//h1[contains(text(),'Manage Plenti card link(s)')]")
	public WebElement txtManagePlentiCardLink;

	
	/*---------------------------
	 * Dropdown add below
	 *---------------------------*/

	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_ManagePlentiCard(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

}

	
	
	
	
	

