package com.OR.MyATTZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_Profile_zone {

	WebDriver driver;
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	
	
	
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	
	
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	@FindBy(xpath="//select[@name='role']")
	public WebElement lstRole;
	
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	@FindBy(xpath="//input[@name='csrId']")
	public WebElement edtAttUID;
	
	@FindBy(xpath="//input[@name='firstName']")
	public WebElement edtFirstName;
	
	@FindBy(xpath="//input[@name='lastName']")
	public WebElement edtLastName; 
	
	
	/*---------------------------
	 *  Text objects add below
	 *---------------------------*/
	
	
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[text()='Edit']")
	public WebElement lnkEdit;
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//img[@name='searchCSR']") 
	public WebElement btnSearchCSR;
	
	@FindBy(xpath="//img[@name='addCSR']") 
	public WebElement btnAddCSR;
	
	@FindBy(xpath="//input[@type='image'][contains(@src,'search')]")
	public WebElement btnSearch; 
	
	@FindBy(xpath="//input[contains(@title,'Submit')]") 
	public WebElement btnSubmit;
	
	public OR_Profile_zone(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
}
