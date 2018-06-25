package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_ATTLocker {
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//img[contains(@src,'attLockerSignUp')]") 
	public WebElement imgLockerSignupImage;


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
		
	@FindBy(xpath="//h1[contains(text(),'AT&T Locker')]") 
	public WebElement txtATTLocker;
	
	@FindBy(xpath="//p[contains(text(),'Easily save photos from your')]") 
	public WebElement txtPageDescription;
	
	@FindBy(xpath="//*[contains(text(),'ll get:')]//parent::*//parent::*//*[contains(text(),'of cloud storage')]//*[contains(text(),'$')]") 
	public WebElement txtYouWillGetLabel;
	
	@FindBy(xpath="//h2[contains(text(),'Terms & Conditions')]") 
	public WebElement txtTermsAndConditions;
	
	@FindBy(xpath="//*[contains(@class,'modalContent')]//*[contains(text(),'Terms & Conditions')]") 
	public WebElement txtTermsAndConditionsModalContent;
	
	@FindBy(xpath="//p[@class='agreCond']") 
	public WebElement txtAgreeTermsAndConditions;
	
	@FindBy(xpath="//h1[contains(text(),'Confirmation')]") 
	public WebElement txtConfirmation;
	
	@FindBy(xpath="//*[contains(text(),'Success! Your AT&T Locker™ is set up and ready to go')]") 
	public WebElement txtSuccessMessage;
	
	@FindBy(xpath="//*[contains(text(),'re getting:')]//parent::*//parent::*//*[contains(text(),'of cloud storage')]") 
	public WebElement txtYouAreGettingLabel;
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[contains(text(),'Print Terms & Conditions')]") 
	public WebElement lnkPrintTermsAndConditions;

	@FindBy(xpath="//a[contains(text(),'Go to Locker')]") 
	public WebElement lnkGoToLocker;
	
	@FindBy(xpath="//a[contains(text(),'Go to myAT&T overview')]") 
	public WebElement lnkGoToMyATTOverview;

	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[@name='Back']") 
	public WebElement btnBack;

	@FindBy(xpath="//a[@id='submit']") 
	public WebElement btnSubmit;

	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	
	
	
	WebDriver driver;
	public OR_ATTLocker(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}

