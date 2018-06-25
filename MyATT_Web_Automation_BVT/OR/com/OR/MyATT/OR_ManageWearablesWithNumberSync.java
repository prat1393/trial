package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_ManageWearablesWithNumberSync {
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//img[@alt='pda']")
	public WebElement imgDeviceWireless;


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
	
	@FindBy(xpath="//input[@id='nickName']")
	public WebElement edtAddOrEditNickName;


	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
		
	@FindBy(xpath="//h1[contains(text(),'NumberSync')]")
	public WebElement txtNumberSync;
	
	@FindBy(xpath="//strong[contains(text(),'.')]")
	public WebElement txtCTN;
	
	@FindBy(xpath="//strong[contains(text(),'.')]//parent::p//parent::div//p[2][contains(text(),' ')]")
	public WebElement txtDeviceName;
	
	@FindBy(xpath="//*[contains(text(),'Add or edit nickname')]")
	public WebElement txtAddOrEditNickName;
	
	@FindBy(xpath="//div[contains(@class,'errorMsg')]//*[contains(text(),'Error')]")
	public WebElement txtErrorMessage;
	
	@FindBy(xpath="//div[contains(@class,'innerModal')]")
	public WebElement txtRemoveModal;
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[contains(@href,'nickNameModal') or contains(text(),'Add nick')]")
	public WebElement lnkNickName;
	
	@FindBy(xpath="//a[@id='Cancel']")
	public WebElement lnkCancel;

	@FindBy(xpath="//a[@id='save']")
	public WebElement lnkSave;

	@FindBy(xpath="//a[contains(text(),'Remove')]")
	public WebElement lnkRemove;
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[@id='remove']")
	public WebElement btnRemoveUnderRemoveModal;


	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;




	public OR_ManageWearablesWithNumberSync(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}

