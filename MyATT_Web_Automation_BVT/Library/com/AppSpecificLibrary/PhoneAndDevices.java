package com.AppSpecificLibrary;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;

import com.OR.MyATT.OR_ATT;
import com.OR.MyATT.OR_AccountOverview;
import com.OR.MyATT.OR_AddaDevice;
import com.OR.MyATT.OR_MyWirelessPlan;
import com.OR.MyATT.OR_PhoneAndDevices;
import com.OR.MyATT.OR_SuspendReactivateService;
import com.SupportingFiles.IO;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;
import com.SupportingFiles.UI;


public class PhoneAndDevices extends LaunchAndLogout {
	static Hashtable<String, String> sTestParams = new Hashtable<String, String>();
	String sFirstName = IO.GetParamVal(sTestParams, "Fname");
    String sLastName = IO.GetParamVal(sTestParams, "Lname");

	
	/**************************************************************
	 * Function Name - ValidateStepsForWirelessNumberChange
	 * Description- 
	 * Parameters - 
	 * Date created - 27th-Feb-2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void ValidateStepsForWirelessNumberChange(final ITestContext testContext) throws Exception 
	{
		 WebDriver lDriver = UI.getDriver(testContext.getName()); 
	        Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
	        OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
	        OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	        OR_PhoneAndDevices oR_PhoneAndDevices= PageFactory.initElements(lDriver, OR_PhoneAndDevices.class);
	
		try
		{
			//Validate secondary navigation Wireless
			boolean blnkWireless = UI.WaitForObject(oR_AccountOverview.lnkWirelessSecNav, 50);
			Report.TestPoint(testContext.getName(), "Validate secondary navigation Wireless", "true", String.valueOf(blnkWireless), true);
			//Click on my phone/devices tertiary link
			boolean blnkMyPAD = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkTransferANumberToATT);
			Report.TestPoint(testContext.getName(), "validate successful Click on my phone/devices tertiery link", "true", String.valueOf(blnkMyPAD), true);
			//Validate wireless number change
			boolean blnWirelessNoChange = UI.WaitForObject(oR_PhoneAndDevices.lnkWirelessNumChange, 50);
			Report.TestPoint(testContext.getName(), "validate wireless number change", "true", String.valueOf(blnWirelessNoChange), true);
			//Click on the link wireless number change
			Report.OperationPoint(testContext.getName(), "Operation : Clicking on the link wireless number change");
			oR_PhoneAndDevices.lnkWirelessNumChange.click();
			
			//Validate please note pop-up
			boolean blnPopUp = UI.WaitForObject(oR_PhoneAndDevices.frmMobileNumberChangePopUp,700);
			//Report.ValidationPoint(testContext.getName(), "validate wireless number change", "true", String.valueOf(blnWirelessNoChange), true);
			if(blnPopUp)
			{
				lDriver.switchTo().frame(oR_PhoneAndDevices.frmMobileNumberChangePopUp);
				//Validate the message : After you change your number
				boolean blnNote = UI.WaitForObject(oR_PhoneAndDevices.txtAfterYouChange, 2);
				Report.ValidationPoint(testContext.getName(), "Validate please note pop-up", "true", String.valueOf(blnNote), true);
				
				//Click on continue
				Report.OperationPoint(testContext.getName(), "Operation : Clicking on continue");
				oR_PhoneAndDevices.imgContinue.click();
				lDriver.switchTo().defaultContent();
			}
			
			//Validate Select a new number page
			boolean bSelectNewNo = UI.WaitForObject(oR_PhoneAndDevices.txtSelectNewNo, 5);
			Report.ValidationPoint(testContext.getName(), "validate Select a new number page", "true", String.valueOf(bSelectNewNo), true);
			//Validate the radio buttons
			//radio button zipcode
			boolean bRadZipcode = UI.WaitForObject(oR_PhoneAndDevices.radZipcode, 5);
			Report.ValidationPoint(testContext.getName(), "validate radio button zipcode", "true", String.valueOf(bRadZipcode), true);
			//radio button city code
			boolean bRadCitycode = UI.WaitForObject(oR_PhoneAndDevices.radCityState, 5);
			Report.ValidationPoint(testContext.getName(), "validate radio button city code", "true", String.valueOf(bRadCitycode), true);
			//radio button area code
			boolean bRadAreacode = UI.WaitForObject(oR_PhoneAndDevices.radAreaCode, 5);
			Report.ValidationPoint(testContext.getName(), "validate radio button area code", "true", String.valueOf(bRadAreacode), true);
			
			//Validate Step indicator
			boolean bStepIndi = UI.WaitForObject(oR_PhoneAndDevices.txtStepIndicator, 5);
			Report.TestPoint(testContext.getName(), "validate Step indicator", "true", String.valueOf(bStepIndi), true);
			//Validate Change mobile number as 1st step
			String sStep1 = oR_PhoneAndDevices.txtStep1.getAttribute("class");
			if(sStep1.equals("step1"))
			{
				Report.ValidationPoint(testContext.getName(), "Change mobile number as 1st step", "true","true", true);
			}
			//Validate that Number selection dropdown is displayed
			boolean bDropDown = UI.WaitForObject(oR_PhoneAndDevices.lnkDropdown, 5);
			Report.ValidationPoint(testContext.getName(), "validate that number selection dropdown is displayed", "true", String.valueOf(bDropDown), true);
			//Click on number selection dropdown
			Report.OperationPoint(testContext.getName(), "Operation : Clicking on number selection dropdown");
			oR_PhoneAndDevices.lnkDropdown.click();
			//Validate list of phone numbers
			List<WebElement> lstNewNo = lDriver.findElements(By.xpath("//ul[@class='usm_menu']//li[contains(@class,'Padbot10')]/a"));
			if(lstNewNo.size()>0)
			{
				Report.ValidationPoint(testContext.getName(), "validate that number selection dropdown is displayed", "true","true", true);
			}
			Report.OperationPoint(testContext.getName(), "Operation : Retrieving count of new numbers: "+lstNewNo.size());
			for(int i=0; i<lstNewNo.size(); i++)
			{
				Report.OperationPoint(testContext.getName(), "Operation : Retrieving the new numbers: "+(i+1)+") "+lstNewNo.get(i).getText());
			}
			
			//Validate button next is disabled
			boolean bDisNext = UI.WaitForObject(oR_PhoneAndDevices.btnDisableNext, 5);
			String sTextDis = oR_PhoneAndDevices.btnDisableNext.getAttribute("id");
			if(sTextDis.contains("disable"))
			{
				Report.ValidationPoint(testContext.getName(), "Validate button next is disabled", "true", String.valueOf(bDisNext), true);
			}
			
			//Click on radio button zipcode
			Report.OperationPoint(testContext.getName(), "Operation : Clicking on radio button zipcode ");
			oR_PhoneAndDevices.radZipcode.click();
			
			//Validate edit box zipcode
			boolean bEdtZipcode = UI.WaitForObject(oR_PhoneAndDevices.edtZipcode, 5);
			Report.ValidationPoint(testContext.getName(), "validate edit box zipcode", "true", String.valueOf(bEdtZipcode), true);
			
			//Enter zipcode
			oR_PhoneAndDevices.edtZipcode.sendKeys("75081");
			
			//Validate button next is enabled
			boolean bEnNext = UI.WaitForObject(oR_PhoneAndDevices.btnEnableNext, 5);
			String sTextEn = oR_PhoneAndDevices.btnEnableNext.getAttribute("id");
			if(sTextEn.contains("enable"))
			{
				Report.ValidationPoint(testContext.getName(), "Validate button next is enabled", "true", String.valueOf(bEnNext), true);
			}
		
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name -  VerifyReactivateSuspendedAccount
	 * Description- 
	 * Parameters - 
	 * Date created -24th Feb 15
	 * Developed by - Merrin Mathai
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void VerifyReactivateSuspendedAccount(final ITestContext testContext)throws Exception
	{
	 {
		 WebDriver lDriver = UI.getDriver(testContext.getName()); 
	        Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
	        OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
	        OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	        OR_PhoneAndDevices oR_PhoneAndDevices= PageFactory.initElements(lDriver, OR_PhoneAndDevices.class);
	        OR_SuspendReactivateService oR_SuspendReactivateService = PageFactory.initElements(lDriver,OR_SuspendReactivateService.class);
		try
		{
			//boolean result;
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav ,oR_AccountOverview.lnkSuspendOrReactivateDeviceTertNav))
			{
			
				//navigate to Suspend Reactivate Service page
				if(UI.WaitForObject(oR_SuspendReactivateService.elmSuspendReactivateService, 120))
				{
					
					
					Report.TestPoint(testContext.getName(), "Goto Suspend Reactivate Service page from global nav", "Suspend Reactivate Service page is displayed", "Suspend Reactivate Service page is displayed" , true);
					//select reason drop down
					
						
							//Reason for Suspension 
							boolean bReason=UI.WaitForObject(oR_SuspendReactivateService.txtSuspended, 120);
							Report.ValidationPoint(testContext.getName(), "Reason for suspension is displayed", "true", "true", true);
						//	Report.OperationPoint(testContext.getName(),"Reason for suspension is "+oR_SuspendReactivateService.txtSuspended.getText());
							//Click on Reactivate Button
							oR_SuspendReactivateService.btnReactivate.click();
							Thread.sleep(5000);
							Report.ValidationPoint(testContext.getName(), "Click on Reactivate Button", "true","true" , true);
							
							//Reactivate confirmation popup
							lDriver.switchTo().frame(oR_SuspendReactivateService.frmSuspendOrReactivateModal);
							//driver.switchTo().defaultContent();
							//validate elements on frame
							boolean bFrmHeader=UI.WaitForObject(oR_SuspendReactivateService.elmAreYouSure, 120);
							Report.OperationPoint(testContext.getName(),"Rea"+oR_SuspendReactivateService.elmAreYouSure.getText());
							boolean bCancle=UI.WaitForObject(oR_SuspendReactivateService.lnkCancel, 120);
							Report.OperationPoint(testContext.getName(),"Rea"+oR_SuspendReactivateService.lnkCancel.getText());
							boolean bClose=UI.WaitForObject(oR_SuspendReactivateService.lnkClose, 120);
							Report.OperationPoint(testContext.getName(),"Rea"+oR_SuspendReactivateService.lnkClose.getText());
							
							boolean bConfirm=UI.WaitForObject(oR_SuspendReactivateService.btnConfirm, 120);
							Report.OperationPoint(testContext.getName(),"Rea"+oR_SuspendReactivateService.btnConfirm.getText());
							
							if(bFrmHeader==bCancle==bClose==bConfirm)
							{
								Report.ValidationPoint(testContext.getName(), "Validate Elements on Frame", "Validated Elements on Frame", "Validated Elements on Frame", true);
								//click on confirm
								oR_SuspendReactivateService.btnConfirm.click();
								
								lDriver.switchTo().parentFrame();
								//suspend Reactivate page
								UI.WaitForObject(oR_SuspendReactivateService.elmSuspendReactivateService, 120);
								Report.OperationPoint(testContext.getName(), "Suspend Reactivate Service page");
								
								//Confirmation Note
								boolean bNote=UI.WaitForObject(oR_SuspendReactivateService.txtReactivatNote, 120);
								Report.ValidationPoint(testContext.getName(), "Reactivate confirmation note", "true","true" , true);
								Report.OperationPoint(testContext.getName(),"confirmation note is"+oR_SuspendReactivateService.txtReactivatNote.getText());
								
								//Verify that the device is in Active status.
								if(UI.WaitForObject(oR_SuspendReactivateService.txtActive, 120))
								{
									Report.ValidationPoint(testContext.getName(), "Verify that the device is in Active status", "true", "true", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Verify that the device is in Active status", "true", "false", true);
								}
								
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate Elements on Frame", "Validated Elements on Frame", " Elements on Frame not present ", true);
							}
				}				
					
				
				else
				{
					Report.TestPoint(testContext.getName(), "Goto Suspend Reactivate Service page from global nav", "Suspend Reactivate Service page is displayed", "Suspend Reactivate Service page is displayed" , true);
				}
				
			}
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	 }
		
			
		}

	/**************************************************************
	 * Function Name -  ValidateSiteRedesigninPhoneDevice
	 * Description- 
	 * Parameters - 
	 * Date created -8th April 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//PDS00038
	@SuppressWarnings("deprecation")
	public static void ValidateSiteRedesigninPhoneDevice(final ITestContext testContext)throws Exception
	{
	 {
		 WebDriver lDriver = UI.getDriver(testContext.getName()); 
	        Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
	        OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
	        OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	        OR_PhoneAndDevices oR_PhoneAndDevices= PageFactory.initElements(lDriver, OR_PhoneAndDevices.class);
	        OR_SuspendReactivateService oR_SuspendReactivateService = PageFactory.initElements(lDriver,OR_SuspendReactivateService.class);
			
		 try
		{
			 //Navigate to My Phones & Device Page
			boolean blnkMyPAD = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkPhoneDevicesTertNav);
			Report.TestPoint(testContext.getName(), "validate successful Click on my phone/devices tertiery link", "true", String.valueOf(blnkMyPAD), true);
			
			Boolean bSelectionModule = UI.WaitForObject(oR_PhoneAndDevices.lnkUserSelect, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "validate User Selection Module is displayed", "true", String.valueOf(bSelectionModule), true);
			 
			/*
			//Validate device image is present and click on the image to change the device
			Boolean bDevice = UI.WaitForObject(oR_PhoneAndDevices.imgDevice, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Phone device is displayed", "true", bDevice.toString(), true);
			oR_PhoneAndDevices.imgDevice.click();
			
			Boolean bSelectPhone = UI.WaitForObject(oR_PhoneAndDevices.txtSelectPhone, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify user is navigated to Selection of Phone page", "true", bSelectPhone.toString(), true);
			
			//Verifying the presence of manufacturer and model options on select Phone page
			Boolean bManuf = UI.WaitForObject(lDriver.findElement(By.id("makeAT&T")), UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify select manufacturer option is displayed on Select Phone page", "true", bManuf.toString(), true);
			
			Boolean bModel = UI.WaitForObject(lDriver.findElement(By.id("modelAT&TAT&T")), UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify select model option is displayed on Select Phone page", "true", bModel.toString(), true);
			//Navigate back to My Phones & Device page
			lDriver.navigate().back();
			
			//Verify Upgrade Phone link navigates user to Upgrade Phone Page
			Boolean bUpgrade = UI.WaitForObject(oR_PhoneAndDevices.lnkUpgradePhone, 60);
			Report.ValidationPoint(testContext.getName(), "Verify select link Upgrade is displayed", "true", bUpgrade.toString(), true);
			oR_PhoneAndDevices.lnkUpgradePhone.click();
			
			Boolean bUpgradePage = UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'Upgrade Phone')]")), 60);
			Report.ValidationPoint(testContext.getName(), "Verify user is navigated to Upgrade Phone page is displayed", "true", bUpgradePage.toString(), true);
			lDriver.navigate().back();
			
			Boolean bMoreAccess = UI.WaitForObject(lDriver.findElement(By.xpath("//a[@href='/olam/gotoShop.olamexecute?event=displayAccessoryPromotion']")), UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify More Accessories link is displayed", "true", bMoreAccess.toString(), true);
			driver.findElementByXPath("//a[@href='/olam/gotoShop.olamexecute?event=displayAccessoryPromotion']").click();
			
			//Navigate to Shop for Accessories page
			Boolean bMoreAccessPage = UI.WaitForObject(driver.findElementByXPath("//div[contains(text(),' Shop for Accessories')]"), 120);
			Report.TestPoint(testContext.getName(), "Verify Shop for Accessories page is displayed", "true", bMoreAccessPage.toString(), true);
			driver.navigate().back(); 
			//Navigating to Apple Site
			Boolean bApple = UI.WaitForObject(lDriver.findElement(By.xpath("//a[contains(text(),'APPLE')]")), 120);
			Report.ValidationPoint(testContext.getName(), "Verify Apple's Site is displayed", "true", bApple.toString(), true);
			lDriver.findElement(By.xpath("//a[contains(text(),'APPLE')]")).click();
			Thread.sleep(20000);
			Boolean bAppleSupport = UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'iPhone Support')]")), 200);
			Report.ValidationPoint(testContext.getName(), "Verify Apple's Site is displayed", "true", bAppleSupport.toString(), true);
			lDriver.navigate().back();
			
			//Verify Add or Change Service
			Boolean bAddService = UI.WaitForObject(lDriver.findElement(By.xpath("//a[contains(text(),'Add or change services')]")), 200);
			Report.ValidationPoint(testContext.getName(), "Verify Add or Change Service CTA is displayed", "true", bAddService.toString(), true);
			lDriver.findElement(By.xpath("//a[contains(text(),'Add or change services')]")).click();
			
			Boolean bAddServicePage = UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'Manage My Device & Features')]")), 200);
			Report.ValidationPoint(testContext.getName(), "Verify Manage Features page is displayed", "true", bAddServicePage.toString(), true);*/
			//Not Validating promo tiles checked with Manual team 
			
			
			
			//PDS00038_01
			String stestcase = testContext.getName();
			if(stestcase.contains("PDS00038_01"))
			{
				//Verifying User Selection Module displays only the current user
				Thread.sleep(20000);
				String sLogin = IO.GetParamVal(sTestParams, "LoginID");
				String sUser = oR_PhoneAndDevices.lnkDropdown.getText(); 
				if(sLogin.equals(sUser))
				{
					Report.TestPoint(testContext.getName(), "Verify User Selection Module displays only the current user", "true", "true", true);
				}
				else
				{
					Report.TestPoint(testContext.getName(), "Verify User Selection Module displays only the current user", "true", "false", true);
				} 
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkPhoneDevicesTertNav);
				Thread.sleep(50000);
				oR_PhoneAndDevices.imgDevice.click();
				Thread.sleep(30000);
				//CHANGE this to frame elements
				lDriver.switchTo().frame(lDriver.findElement(By.xpath("//iframe[@id='usm']")));
				Boolean bProfileChange = UI.WaitForObject(lDriver.findElement(By.xpath("//a[@name='profileIcon']")), 250);
				Report.ValidationPoint(testContext.getName(), "Verify Profile Change link is displayed", "true", bProfileChange.toString(), true);
				//WebDriver driver=new FirefoxDriver();
			     //String baseURL="https://myattmonitor5.stage.att.com:8442/olam/loginAction.olamexecute";
			      String url = lDriver.getCurrentUrl();
					
				
				//Issue while clicking on 'change profile' link present inside frame
				//Navigating to Profile Info page
		      /*  Actions action = new Actions(driver);
		        
		       Action actn = action.moveToElement(oR_PhoneAndDevices.lnkProfChange).build();
		       actn.perform(); */
		       
		     
				//Locatable clickItem = (Locatable)oR_PhoneAndDevices.lnkProfChange.getLocation();
				//clickItem.getCoordinates();
		           //  action.moveByOffset(oR_PhoneAndDevices.lnkProfChange.getLocation().getX(), oR_PhoneAndDevices.lnkProfChange.getLocation().getY()).doubleClick().build().perform();
		             //action.build().perform();
		        //action.click().build().perform();
				//driver.findElementById("profile-change").click();
				//Report.TestPoint(testContext.getName(), "Verify Profile Change link is clicked", "true", "true", true);
				
				//driver.switchTo().defaultContent();
				
				Boolean bProfileInfo = UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'Profile Information - Upload Images')]")), 100);
				Report.ValidationPoint(testContext.getName(), "Verify Profile Change link is displayed", "true", bProfileInfo.toString(), true);
				//Verifying the Profile Information is displayed of current user
				
				String sBAN = IO.GetParamVal(sTestParams, "BAN");
				String sUserBAN = lDriver.findElement(By.xpath("id('profile-details')//dd")).getText();
				if(sUserBAN.contains(sBAN))
				{
					Report.ValidationPoint(testContext.getName(), "Verify the Profile Information is displayed of current user", "true", "true", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify the Profile Information is displayed of current user", "true", "false", true);
				}
				//Navigating back to Phone & Device page
				blnkMyPAD = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkPhoneDevicesTertNav);
				Report.ValidationPoint(testContext.getName(), "validate successful Click on my phone/devices tertiery link", "true", String.valueOf(blnkMyPAD), true);
				
				//Verifying Reset Voicemail Link
				Boolean bResetVM = UI.WaitForObject(oR_PhoneAndDevices.lnkResetVM, 60);
				Report.ValidationPoint(testContext.getName(), "Verify Reset my Voicemail Password link is displayed", "true", bResetVM.toString(), true);
				oR_PhoneAndDevices.lnkResetVM.click();
				
				Boolean bResetVMPage = UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'Reset Voicemail Password')]")), 60);
				Report.ValidationPoint(testContext.getName(), "Verify User is navigated to Reset Voicemail Password", "true", bResetVMPage.toString(), true);
				lDriver.navigate().back();
				
				Boolean bUserSelect = UI.WaitForObject(oR_PhoneAndDevices.lnkUserSelect, 60);
				Report.ValidationPoint(testContext.getName(), "Verify User selection dropdown is displayed", "true", bUserSelect.toString(), true);
				
				
				//Verify Suspend or Reactivate feature
				Boolean bSuspend = UI.WaitForObject(oR_PhoneAndDevices.lnkReactivate, 60);
				Report.ValidationPoint(testContext.getName(), "Verify Suspend Or Reactivate link is displayed", "true", bSuspend.toString(), true);
				oR_PhoneAndDevices.lnkReactivate.click();
				
				Boolean bSuspendPage = UI.WaitForObject(oR_SuspendReactivateService.elmSuspendReactivateService, 60);
				Report.ValidationPoint(testContext.getName(), "Verify Suspend Or Reactivate Page is displayed", "true", bSuspendPage.toString(), true);
				
				//Validate Name,Number, suspend link
				
				UI.VerifyElementNotPresent(oR_PhoneAndDevices.lnkUserSelect, "Select");
				
				Boolean bAccNo = UI.WaitForObject(lDriver.findElement(By.xpath("//li[@class='account-number']")), 80);
				Report.ValidationPoint(testContext.getName(), "Verify Account Number is displayed", "true", bAccNo.toString(), true);
				
				Boolean bAccName = UI.WaitForObject(lDriver.findElement(By.xpath("//div[@class='section-title']/h2")), 80);
				Report.ValidationPoint(testContext.getName(), "Verify Account Name is displayed", "true", bAccName.toString(), true);
				
				Boolean bSuspendlnk = UI.WaitForObject(lDriver.findElement(By.xpath("//img[@alt='suspend']")), 80);
				Report.ValidationPoint(testContext.getName(), "Verify Suspend link is displayed", "true", bSuspendlnk.toString(), true);
					
				
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
			
		}
	 }
		
	}
	/**************************************************************
	 * Function Name -  Validatewirelessaddadevice
	 * Description- 
	 * Parameters - 
	 * Date created -14th April 2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/

public static void Validatewirelessaddadevice(final ITestContext testContext)throws Exception
  {
	{
		WebDriver lDriver = UI.getDriver(testContext.getName()); 
        Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
        OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
        OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
        OR_AddaDevice oR_AddaDevice= PageFactory.initElements(lDriver, OR_AddaDevice.class);
        OR_MyWirelessPlan oR_MyWirelessPlan= PageFactory.initElements(lDriver, OR_MyWirelessPlan.class);
        
		try
			{
			//Validate secondary navigation Wireless
			 UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav, null, lDriver);
			
			//validate add a new device link
			boolean bAdd = UI.WaitForObject(oR_MyWirelessPlan.lnkAddADevice, 50);
			Report.ValidationPoint(testContext.getName(), "Validate Add a new device link is displyed", "true", String.valueOf(bAdd), true);
			
			UI.VerifyLinkNavigatestoNextPage(oR_MyWirelessPlan.lnkAddADevice, "stage.att.com/shop");
			lDriver.navigate().back();
			if(oR_MyWirelessPlan.lnkAddADevice.isDisplayed())
			{
				Report.ValidationPoint(testContext.getName(), "Validate wireless page is displayed after navigating back from add a device page", "true", String.valueOf(oR_MyWirelessPlan.lnkAddADevice.isDisplayed()), true);
			}
			
			
			}
		catch (Exception e) 
			{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
			}
	}
	
  }
			/**************************************************************
			 * Function Name -  Validatewirelessaddadevice
			 * Description- This function checks for Wireless with Featurephone damage feature
			 * Parameters - 
			 * Date created - 30th Oct 2015
			 * Developed by - Hiral
			 * Last Modified By - 
			 * Last Modified Date -
			 ***************************************************************/
			public static void ValidateWirelessFeaturephone_DamageIndicator(final ITestContext testContext)throws Exception
			{
			{
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_PhoneAndDevices oR_PhoneAndDevices = PageFactory.initElements(lDriver, OR_PhoneAndDevices.class);
			try
			{
			//Validate secondary navigation Wireless
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkPhoneDevicesTertNav);
			Report.OperationPoint(testContext.getName(), "Clicking on My Phone/Device link under Wireless section.");
			
			//validate add a new device link
			boolean bManage = UI.WaitForObject(oR_PhoneAndDevices.txtManageDevices, 5);
			Report.ValidationPoint(testContext.getName(), "Validate Manage My Device & Features page is displyed.", "true", String.valueOf(bManage), true);
			
			//click on TroubleShoot my device link
			Report.OperationPoint(testContext.getName(), "clicking on TroubleShoot my device link.");
			oR_PhoneAndDevices.lnkTroubleShoot.click();
			
			Thread.sleep(2000);
			
			
			 // Get the window handle of the new browser window opened.  
			String childWindow = (String) lDriver.getWindowHandles().toArray()[1];  
			lDriver.switchTo().window(childWindow);  
			
			Report.OperationPoint(testContext.getName(), "Validate a modal window is opened.");
			Thread.sleep(5000);
			
			
			//click on Wireless support link
			
			Report.OperationPoint(testContext.getName(), "clicking on Wireless support link.");
			Thread.sleep(2000);
			WebElement bsupport = lDriver.findElement(By.xpath("//div/p/a[text()='Wireless Support']"));
			bsupport.click();
			Thread.sleep(5000);
			
			 // Get the window handle of the new browser window opened.  
			String sURL = (String) lDriver.getWindowHandles().toArray()[1];  
			lDriver.switchTo().window(sURL); 
			
			
			
			//validate Troubleshoot link is displayed
			boolean btroubleshoot1 = UI.WaitForObject(oR_PhoneAndDevices.lnkTroubleShoot1, 5);
			Report.ValidationPoint(testContext.getName(), "Clicking on Troubleshoot devices link.", "true", String.valueOf(btroubleshoot1), true);
			oR_PhoneAndDevices.lnkTroubleShoot1.click();
			Report.ValidationPoint(testContext.getName(), "Validate the page with solutions for troubleshoot devices is seen.", "true", String.valueOf(btroubleshoot1), true);
			
			boolean bReplace = UI.WaitForObject(oR_PhoneAndDevices.lnkReplacebrokenPhone, 5);
			Report.ValidationPoint(testContext.getName(), "Clicking on Replace broken phone or other mobile devices link.", "true", String.valueOf(bReplace), true);
			oR_PhoneAndDevices.lnkReplacebrokenPhone.click();
			Report.ValidationPoint(testContext.getName(), "Validate the page is seen with Replace broken phone or other mobile devices content info.", "true", String.valueOf(btroubleshoot1), true);
			
			//Validate Replace broken phone or other mobile device content
			boolean bReplacetext = UI.WaitForObject(oR_PhoneAndDevices.txtReplacebrokenphone, 5);
			Report.ValidationPoint(testContext.getName(), "Validate Replace broken phone or other mobile devices content is displayed.", "true", String.valueOf(bReplacetext), true);
			
			//Validate Replace broken phone or other mobile device content
			boolean bUpgradeyourphone = UI.WaitForObject(oR_PhoneAndDevices.lnkUpgradeYourPhone, 5);
			Report.ValidationPoint(testContext.getName(), "Validate Upgrade your device link is displayed.", "true", String.valueOf(bUpgradeyourphone), true);
			
			
			
			
			}
			catch (Exception e) 
			{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
			}
			}
			
			
			
			
			}

}