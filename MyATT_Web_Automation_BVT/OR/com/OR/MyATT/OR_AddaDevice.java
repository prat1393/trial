package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_AddaDevice {
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/

@FindBy(xpath="//a[@class='arrow | wt_Body'][text()='Add a device to a Mobile Share group ']")
	public WebElement lnkadddevicetogroup;
	
@FindBy(xpath="//a[@class='arrow | wt_Body'][text()='Bring your own device']")
	public WebElement lnkbringyourdevice;

@FindBy(xpath="//a[@title='Add a device to a Mobile Share group']")
public WebElement lnkAddADeviceToMobileShareGroup;

@FindBy(xpath="//a[contains(text(),'View Profile')]")
public WebElement lnkViewProfile;

@FindBy(xpath="//a[text()='Cancel']")
public WebElement lnkCancel;

@FindBy(xpath="//div[@id='WirelessPhoneAddALineToMyAccount']//a[contains(text(),'Bring your own device')]")
public WebElement lnkBringYourDeviceInMobileShareGroup;

@FindBy(xpath="//div[@id='WirelessPhoneAddALineToFT']//a[contains(text(),'Bring your own device')]")
public WebElement lnkBringYourDeviceInFamilyTalkPlan;



/*---------------------------
 * Text objects add below
 *---------------------------*/
	@FindBy(xpath="//h1[contains(text(),'Add a Device')]|//h2[contains(text(),'Device')]")
	public WebElement txtAddaDevice;

	@FindBy(xpath="//p[contains(text(),'Add a device to a Mobile Share plan')]")
	public WebElement txtAddADeviceToMobileShareGroup;
	
	@FindBy(xpath="//p[contains(text(),'Add a device to a Mobile Share plan')]")
	public WebElement txtContentInFTPlanAndMSGroup;
	
	@FindBy(xpath="//*[@id='profile-details']//*[contains(text(),'Owner')]")
	public WebElement txtOwnerName;
	
	@FindBy(xpath="//*[@id='profile-details']//*[contains(text(),'Account Number')]")
	public WebElement txtAccNumber;
	
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

		
	
	public OR_AddaDevice(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
