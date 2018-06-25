package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_SMBMyServices {
	
	/*---------------------------  
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[contains(@id,'Secondary')]//a[text()='My Services']")
	public WebElement lnkMyService;
	 
	@FindBy(xpath="//a[text()='AT&T U-verse Voice home']")
	public  WebElement lnkATTUverseVoiceHome;
	
	@FindBy(xpath="//a[text()='Voicemail Setup']")
	public WebElement lnkVoicemailSetup;
	
	@FindBy(xpath="//a[text()='Greeting Setup']")
	public WebElement lnkGreetingSetup;
	
	@FindBy(xpath="//a[text()='User Permissions']")
	public WebElement lnkUserPermissions;
	
	@FindBy(xpath="//a[contains(text(),'View My Profile')]")
	public WebElement lnkViewMyProfile;
	
	@FindBy(xpath="//a[contains(text(),'Terms of Service')]")
	public WebElement lnkTermsOfService;
	
	@FindBy(xpath="//a[contains(text(),'Check Order Status')]")
	public WebElement lnkCheckOrderStatus;
	
	@FindBy(xpath="//a[contains(text(),'Compare Plans')]")
	public WebElement lnkComparePlans;
	
	@FindBy(xpath="//a[contains(text(),'Manage U-verse Sub Accounts')]")
	public WebElement lnkManageUverseSubAccounts;
	
	@FindBy(xpath="//a[contains(text(),'Voicemail Setup Wizard')]")
	public WebElement lnkVoicemailSetupWizard;
	
	@FindBy(xpath="//a[contains(text(),'Update voicemail settings')]")
	public WebElement lnkUpdateVoicemailSettings;
	 /*---------------------------
		 * Text objects add below
		 *---------------------------*/ 
	@FindBy(xpath="//h1[contains(text(),'My Services')]")
	public WebElement txtMyServices;
	
	@FindBy(xpath="//*[contains(text(),'AT&T U-verse Voice Details')]")
	public WebElement txtATTUverseVoiceDetailsTitle;
	
	@FindBy(xpath="//*[contains(text(),'Account Owner:')]")
	public WebElement txtAccountOwner;
	
	@FindBy(xpath="//*[contains(text(),'Account Number:')]")
	public WebElement txtAccountNumber;
	 
	@FindBy(xpath="//*[contains(text(),'AT&T U-verse Voice Unlimited')]")
	public WebElement txtATTUverseVoiceUnlimited;
	
	@FindBy(xpath="//*[text()='Features']")
	public WebElement txtFeatures;
	
	@FindBy(xpath="//*[text()='Voice Options']")
	public WebElement txtVoiceOptions; 
	
	@FindBy(xpath="//*[text()='Voicemail Account Information']")
	public WebElement txtVoicemailAccountInformation;
	
	@FindBy(xpath="//*[text()='Features']/parent::*/parent::*//*[text()='Incoming Calls']")
	public WebElement txtIncomingCalls;
	
	@FindBy(xpath="//*[text()='Features']/parent::*/parent::*//*[text()='Voice Mail']")
	public WebElement txtVoiceMail;
	
	@FindBy(xpath="//*[text()='Features']/parent::*/parent::*//*[text()='Outgoing Calls']")
	public WebElement txtOutgoingCalls;
	
	@FindBy(xpath="//*[text()='Features']/parent::*/parent::*//*[text()='Call Forwarding']")
	public WebElement txtCallForwarding;
	
	@FindBy(xpath="//*[text()='Features']/parent::*/parent::*//*[text()='Call Filtering']")
	public WebElement txtCallFiltering;

	@FindBy(xpath="//*[text()='Features']/parent::*/parent::*//*[text()='Feature Controls']")
	public WebElement txtFeatureControls;
	 /*---------------------------
		 * Button objects add below
		 *---------------------------*/
	 
	
	 
	 /*---------------------------
		 * Dropdown objects add below
		 *---------------------------*/
	
	 
	 /*---------------------------
		 * WebDriver objects add below
		 *---------------------------*/
		WebDriver driver;

		
		
		
		public OR_SMBMyServices(WebDriver driver){
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
}
