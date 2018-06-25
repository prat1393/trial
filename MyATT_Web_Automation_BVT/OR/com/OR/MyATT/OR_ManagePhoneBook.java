package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_ManagePhoneBook {

	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	@FindBy(xpath="//input[@name='phoneNumber']")
	public WebElement edtPhoneNo;
	
	@FindBy(xpath="//input[@name='name']")
	public WebElement edtName;
	
	@FindBy(xpath="//input[@name='alias']")
	public WebElement edtAlias;
	
	
	/*---------------------------
	 * Frames add below
	 *---------------------------*/
	
	
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[text()='Add entry']")
	public WebElement lnkAddEntry;
	
	@FindBy(xpath="//a[text()='Delete']")
	public WebElement lnkDelete;
	
	@FindBy(xpath="//a[text()='Go back to Usage page']")
	public WebElement lnkGoBack;
	
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//input[@alt='Submit']")
	public WebElement btnSubmit;
	
	
	/*---------------------------
	 * Image objects add below
	 * *---------------------------*/

	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//h1[text()='Manage Phone Book']")
	public WebElement txtManagePhoneBook;
	
	@FindBy(xpath="//h1[text()='Add Phone Book Entry']")
	public WebElement txtAddPBentry;
	
	
	/*---------------------------
	 * Dropdown add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	
	
	public OR_ManagePhoneBook(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}