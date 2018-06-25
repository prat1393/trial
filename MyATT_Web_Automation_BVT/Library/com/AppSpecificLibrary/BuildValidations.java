package com.AppSpecificLibrary;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import com.OR.MyATT.OR_ATT;
import com.OR.MyATT.OR_ATTSupportCenter;
import com.OR.MyATT.OR_AccountOverview;
import com.OR.MyATT.OR_BillAndUsage;
import com.OR.MyATT.OR_BillingSettingsNotice;
import com.OR.MyATT.OR_HomePhoneService;
import com.OR.MyATT.OR_InternetService;
import com.OR.MyATT.OR_Login;
import com.OR.MyATT.OR_MakeAPayment;
import com.OR.MyATT.OR_MyWirelessPlan;
import com.OR.MyATT.OR_PaymentConfirmation;
import com.OR.MyATT.OR_Profile;
import com.OR.MyATT.OR_ReviewPaymentDetails;
import com.OR.MyATT.OR_Shop;
import com.OR.MyATT.OR_TelevisionService;
import com.OR.MyATT.OR_ViewOrChangeRatePlan;
import com.OR.MyATTZone.OR_Logon;
import com.SupportingFiles.IO;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;
import com.SupportingFiles.UI;

public class BuildValidations extends LaunchAndLogout 
{
		
	//Wireline SA/SL Uvese SA/SL
	//BVT-SA_01_Login. Same for BVT-SL_01_Login
	public static void ValidateWirelineLogin(final ITestContext testContext) throws Exception 
	{
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sUrlDomain = IO.GetParamVal(sTestParams, "DOMAIN");
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
		
		try
		{
							
			Report.OperationPoint(testContext.getName(), "***************************************** VALIDATING LOGIN FLOW *****************************************");
			//Verify primary Navigation consists of  Shop|My AT&T|Support links
			//Shop
			Boolean bShop = UI.WaitForObject(oR_AccountOverview.lnkShopPrimaryNav, 5,lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify primary Navigation consists of Shop link", "true", bShop.toString(), true);
			//My AT&T
			Boolean bMyATT = UI.WaitForObject(oR_AccountOverview.lnkMyATTPrimaryNav, 5,lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify primary Navigation consists of My AT&T link", "true", bMyATT.toString(), true);
			//Support
			Boolean bSupport = UI.WaitForObject(oR_AccountOverview.lnkSupportPrimaryNav, 5,lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify primary Navigation consists of Support link", "true", bSupport.toString(), true);
			
			//Verify secondary navigation is present
			Boolean bSec = UI.WaitForObject(oR_AccountOverview.lnkSecNav, 1,lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify secondary navigation is present", "true", bSec.toString(), true);
			
			//Verify the domain of the page
			Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);

			// Check for any error  message
			Boolean bErrorMsg = UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver);
			if(bErrorMsg){
			    Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);
			}else{
				Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
			}
		 }
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	
	}

/*****************************************************************************************************************************************/
	//Wireline SA/SL Uvese SA/SL
	//BVT_SA_02_Account_Level_Info. Same for BVT_SL_02_Account_Level_Info
	public static void ValidateWirelineAccount(final ITestContext testContext) throws Exception 
	{
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		Report.OperationPoint(testContext.getName(), "***************************************** VALIDATING PROFILE AND ACCOUNT FLOW *****************************************");
		String sUrlDomain = IO.GetParamVal(sTestParams, "DOMAIN");
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
		OR_Login oR_Login = PageFactory.initElements(lDriver,OR_Login.class);
		
		try
		{
								
			//Click Profile Link
			Boolean bProfile = UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav,60,lDriver);

			if(bProfile.equals(true))
			{
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on link profile");
				//UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav, null, lDriver);
				oR_AccountOverview.lnkProfileSecondaryNav.click();
				
				Thread.sleep(13000);
				new Actions(lDriver).moveByOffset(0, 40).build().perform();
				
				if(lDriver.getCurrentUrl().contains("Profile") && lDriver.getCurrentUrl().contains("myattmonitor"))
				{
					//aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
					Report.ValidationPoint(testContext.getName(), "Validate navigation to My profile page", "true", "true", true);
					//Verify the domain of the page
					Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
				//	Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl() + "  " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
					Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);	
					// Check for any error  message
					if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
					}else{
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
					}
				
				}
				else if(UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, 10, lDriver))
				{
					Report.OperationPoint(testContext.getName(), "User is navigated to login page. Clicking on Back button");
					lDriver.navigate().back();
					Thread.sleep(5000);
				//	System.out.println("Session ID after clicking back " + ((RemoteWebDriver)lDriver).getSessionId());
					if(UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav, UI.iObjTimeOut, lDriver)){
				//	UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav, null, lDriver);
					oR_AccountOverview.lnkProfileSecondaryNav.click();
					
					Thread.sleep(3000);
					new Actions(lDriver).moveByOffset(0, 40).build().perform();
					
					if(lDriver.getCurrentUrl().contains("Profile") && lDriver.getCurrentUrl().contains("myattmonitor"))
					{
						Report.ValidationPoint(testContext.getName(), "Validate navigation to My profile page", "true", "true", true);
						//Verify the domain of the page
						Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl() + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
											
						// Check for any error  message
						if(UI.WaitForObject(oR_AccountOverview.errorMsg, 2, lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
						}else{
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
						}		
				
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate navigation to My profile page", "true", "false", true);
					}
				 }else{
					 Report.ValidationPoint(testContext.getName(), "Verify Profile Link", "Verify Profile Link","Profile Link not present", true);
				 }
			  }
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate navigation to My profile page", "true", "false", true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Profile Link", "Verify Profile Link","Profile Link not present", true);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	
	}
	
/*****************************************************************************************************************************************/
	//Wireline SA/SL
	//BVT_SA_03_HomePhone. Same for BVT_SL_03_HomePhone
	
	public static void ValidateWirelineHomePhone(final ITestContext testContext) throws Exception 
	{
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		Report.OperationPoint(testContext.getName(), "***************************************** VALIDATING HOME PHONE FLOW *****************************************");
		String sUrlDomain = IO.GetParamVal(sTestParams, "DOMAIN");
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
		OR_Login oR_Login = PageFactory.initElements(lDriver,OR_Login.class);
			
		try
		{
							
			//Click home phone Link
			Boolean bHomePhone = UI.WaitForObject(oR_AccountOverview.lnkHomePhoneSecondaryNav, 20,lDriver);
			if(bHomePhone.equals(true))
			{
				//System.out.println("Session ID before back " + ((RemoteWebDriver)lDriver).getSessionId());
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on home phone Link");
			//	UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkHomePhoneSecondaryNav, null, lDriver);
				oR_AccountOverview.lnkHomePhoneSecondaryNav.click();
				
				Thread.sleep(13000);
				new Actions(lDriver).moveByOffset(0, 40).build().perform();
				
				if((lDriver.getCurrentUrl().contains("HomePhone") || lDriver.getCurrentUrl().contains("TelcoLocalService")) && lDriver.getCurrentUrl().contains("myattmonitor"))
				{
					////aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
					Report.ValidationPoint(testContext.getName(), "Verify that HomePhone landing page is displayed", "true","true", true);
					//Verify the domain of the page
					Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
							
					// Check for any error  message
					if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
					}else{
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
					}
				
				}
				else if(UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, 10, lDriver))
				{
					Report.OperationPoint(testContext.getName(), "User is navigated to login page. Clicking on Back button");
					lDriver.navigate().back();
					Thread.sleep(5000);
				//	System.out.println("Session ID after back " + ((RemoteWebDriver)lDriver).getSessionId());
					if(UI.WaitForObject(oR_AccountOverview.lnkHomePhoneSecondaryNav, UI.iObjTimeOut, lDriver)){
				//	UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkHomePhoneSecondaryNav, null, lDriver);
					oR_AccountOverview.lnkHomePhoneSecondaryNav.click();
					
					Thread.sleep(6000);
					new Actions(lDriver).moveByOffset(0, 40).build().perform();
					////aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
					if((lDriver.getCurrentUrl().contains("HomePhone") || lDriver.getCurrentUrl().contains("TelcoLocalService")) && lDriver.getCurrentUrl().contains("myattmonitor"))
					{
						Report.ValidationPoint(testContext.getName(), "Verify that HomePhone landing page is displayed", "true","true", true);
						//Verify the domain of the page
						Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
					
						// Check for any error  message
						if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
						}else{
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
						}
						
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify that HomePhone landing page is displayed", "true","false", true);
					}
				 }else{
					 Report.ValidationPoint(testContext.getName(), "Verify that HomePhone sec nav link is displayed", "true","false", true);
				  }
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that HomePhone landing page is displayed", "true","true", true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that HomePhone sec nav link is displayed", "true","false", true);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
		
/*****************************************************************************************************************************************/
	//Wireline SL
	//BVT_SL_07_DigitalTV.
	public static void ValidateWirelineDigitalTV(final ITestContext testContext) throws Exception 
	{
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		Report.OperationPoint(testContext.getName(), "***************************************** VALIDATING DIGITAL TV FLOW *****************************************");
		String sUrlDomain = IO.GetParamVal(sTestParams, "DOMAIN");
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
		OR_Login oR_Login = PageFactory.initElements(lDriver,OR_Login.class);
			
		try
		{
				
			//Click digital TV Link
			Boolean bDigiTV = UI.WaitForObject(oR_AccountOverview.lnkDigitalTVSecondaryNav,30,lDriver);
			if(bDigiTV.equals(true))
			{
			//	System.out.println("Session ID before back " + ((RemoteWebDriver)lDriver).getSessionId());
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on digital TV Link");
				//UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkDigitalTVSecondaryNav, null, lDriver);
				oR_AccountOverview.lnkDigitalTVSecondaryNav.click();
				Thread.sleep(5000);
				new Actions(lDriver).moveByOffset(0, 40).build().perform();
				////aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
				
				//Verify that digital TV landing page is displayed
				if(lDriver.getCurrentUrl().contains("TvProductLandingPage") && lDriver.getCurrentUrl().contains("myattmonitor"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify that digital TV landing page is displayed", "true", "true", true);
					//Verify the domain of the page
					Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
				
					// Check for any error  message
					if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
					}else{
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
					}
				    
				}
				else if(UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, 10, lDriver))
				{
					Report.OperationPoint(testContext.getName(), "User is navigated to login page. Clicking on Back button");
					lDriver.navigate().back();
					Thread.sleep(5000);
				//	 System.out.println("Session ID after back " + ((RemoteWebDriver)lDriver).getSessionId());
					if(UI.WaitForObject(oR_AccountOverview.lnkDigitalTVSecondaryNav, UI.iObjTimeOut, lDriver)){
					
				//	UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkDigitalTVSecondaryNav, null, lDriver);
					 oR_AccountOverview.lnkDigitalTVSecondaryNav.click();
					 
					 Thread.sleep(5000);
					 new Actions(lDriver).moveByOffset(0, 40).build().perform();
					////aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
					
					//Verify that digital TV landing page is displayed
					if(lDriver.getCurrentUrl().contains("TvProductLandingPage") && lDriver.getCurrentUrl().contains("myattmonitor"))
					{
						Report.ValidationPoint(testContext.getName(), "Verify that digital TV landing page is displayed", "true", "true", true);
						//Verify the domain of the page
						Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
					
						// Check for any error  message
						if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
						}else{
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
						}			    
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify that digital TV landing page is displayed", "true","false", true);
					}
					
				 }else{
					 Report.ValidationPoint(testContext.getName(), "Verify digital TV Link", "Verify digital TV Link","digital TV Link not present", true);
				 }
					
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that digital TV landing page is displayed", "true","false", true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify digital TV Link", "Verify digital TV Link","digital TV Link not present", true);
			}
		}
		catch(Exception e)
		{   e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}

/*****************************************************************************************************************************************/
	//Uverse SL
	//BVT_Slid_04_Support
	public static void ValidateUverseSupport(final ITestContext testContext) throws Exception 
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
			OR_Login oR_Login = PageFactory.initElements(lDriver,OR_Login.class);
			Report.OperationPoint(testContext.getName(), "***************************************** VALIDATING SUPPORT FLOW *****************************************");
							
			//Click on primary navigation Support link
			Boolean bSupport = UI.WaitForObject(oR_AccountOverview.lnkSupportPrimaryNav, 30,lDriver);
			if(bSupport.equals(true))
			{
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on primary navigation Support Link");
				oR_AccountOverview.lnkSupportPrimaryNav.click();
				Thread.sleep(3000);
				new Actions(lDriver).moveByOffset(0, 40).build().perform();
				Boolean bLogin = UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, 10,lDriver);
				if(bLogin)
				{
					Thread.sleep(5000);
					oR_AccountOverview.lnkSupportPrimaryNav.click();
					new Actions(lDriver).moveByOffset(0, 40).build().perform();
				}
				
				//Verify AT&T Support center page
				if(lDriver.getCurrentUrl().contains("support"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify AT&T Support center page is displayed", "true", "true", true);
				    
					// Check for any error  message
					if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
					}else{
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
					}
					
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify AT&T Support center page is displayed", "true", "false", true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify primary navigation Support Link", "Verify primary navigation Support Link","primary navigation Support not present", true);
			}
		}
		catch(Exception e)
		{   
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
/*****************************************************************************************************************************************/
	//Uverse SA
	//BVT-Uverse_08_Validate_Inernet_Landing Page having Internet Usage CTA
	public static void ValidateInternet(final ITestContext testContext) throws Exception 
	{
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sUrlDomain = IO.GetParamVal(sTestParams, "DOMAIN");
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
	//	OR_InternetService oR_InternetService = PageFactory.initElements(lDriver,OR_InternetService.class);
		OR_Login oR_Login = PageFactory.initElements(lDriver,OR_Login.class);
	//	Actions aAction = new Actions(lDriver);
		
		try
		{
			
			Report.OperationPoint(testContext.getName(), "***************************************** VALIDATING INTERNET FLOW *****************************************");
			
			//Click Internet secondary navigation Link
			Boolean bInternet = UI.WaitForObject(oR_AccountOverview.lnkInternetSecondaryNav, 50,lDriver);
			if(bInternet.equals(true))
			{
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Internet secondary navigation Link");
			//	UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkInternetSecondaryNav, null, lDriver);
				oR_AccountOverview.lnkInternetSecondaryNav.click();
				
				Thread.sleep(10000);
				new Actions(lDriver).moveByOffset(0, 40).build().perform();
				//aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
				// System.out.println("Session ID before back " + ((RemoteWebDriver)lDriver).getSessionId());
				//Verify that Internet landing page is displayed
				if(lDriver.getCurrentUrl().contains("InternetProductLandingPage") && lDriver.getCurrentUrl().contains("myattmonitor"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify that internet landing page is displayed", "true", "true", true);
					//Verify the domain of the page
					Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
				
					
					// Check for any error  message
					if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
					}else{
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
					}
				}
				else if(UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, 10, lDriver))
				{
					Report.OperationPoint(testContext.getName(), "User is navigated to login page. Clicking on Back button");
					lDriver.navigate().back();
					Thread.sleep(5000);
								
					// System.out.println("Session ID after back " + ((RemoteWebDriver)lDriver).getSessionId());
					if( UI.WaitForObject(oR_AccountOverview.lnkInternetSecondaryNav, UI.iObjTimeOut, lDriver)){
					
					//UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkInternetSecondaryNav, null, lDriver);
					 oR_AccountOverview.lnkInternetSecondaryNav.click();
					 
					 Thread.sleep(5000);
					 new Actions(lDriver).moveByOffset(0, 40).build().perform();
					//aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
					
					//Verify that Internet landing page is displayed
					if(lDriver.getCurrentUrl().contains("InternetProductLandingPage") && lDriver.getCurrentUrl().contains("myattmonitor"))
					{
						Report.ValidationPoint(testContext.getName(), "Verify that internet landing page is displayed", "true", "true", true);
						//Verify the domain of the page
						Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
							
						// Check for any error  message
						if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
						}else{
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
						}
					
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify that internet landing page is displayed", "true", "false", true);
					}
				  }else{
					  Report.ValidationPoint(testContext.getName(), "Verify Internet secondary navigation Link", "Verify Internet secondary navigation Link","Internet secondary navigation Link NOT present", true);
				  }
					
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that internet landing page is displayed", "true", "false", true);
				}
				/** According to revised TC, following steps are commented */
				/*//Verify Plan details section is displayed
				Boolean bPlanD = UI.WaitForObject(oR_InternetService.txtPlanDetailsSection, 5);
				Report.TestPoint(testContext.getName(), "Verify Plan details section is displayed", "true", bPlanD.toString(), true);
				
				//Validate View my current internet usage cta
				Boolean bViewIntUsg = UI.WaitForObject(oR_InternetService.lnkInternetUsage, 5);
				Report.ValidationPoint(testContext.getName(), "Verify View my current internet usage cta", "true", bViewIntUsg.toString(), true);
				
				//Click on the CTA "View my current internet usage"
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on View my current internet usage Link");
				oR_InternetService.lnkInternetUsage.click();
				
				//Verify The link will navigate to :olam/hsiaRedirectToUsage.myworld?reportActionEvent=A_UMD_UVERSE_CURRENT_USAGE
				String sURL = driver.getCurrentUrl();
				System.out.println(sURL);
				if(sURL.contains("A_UMD_UVERSE_CURRENT_USAGE"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify The link will navigate to :olam/hsiaRedirectToUsage.myworld?reportActionEvent=A_UMD_UVERSE_CURRENT_USAGE", 
							"true",bViewIntUsg.toString(), true);
				}*/
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Internet secondary navigation Link", "Verify Internet secondary navigation Link","Internet secondary navigation Link NOT present", true);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
/*****************************************************************************************************************************************/
	//Uverse SA
	//BVT-Uverse_06_Validate_HomePhone_page
	public static void ValidateUverseHomePhone(final ITestContext testContext) throws Exception 
	{
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sUrlDomain = IO.GetParamVal(sTestParams, "DOMAIN");
		
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
//		OR_HomePhoneService oR_HomePhoneService = PageFactory.initElements(lDriver,OR_HomePhoneService.class);
	//	OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver,OR_MakeAPayment.class);
		OR_Login oR_Login = PageFactory.initElements(lDriver,OR_Login.class);
//		Actions aAction = new Actions(lDriver);
		
		try
		{
			
			Report.OperationPoint(testContext.getName(), "***************************************** VALIDATING UVERSE HOMEPHONE FLOW *****************************************");
			
			//Click HomePhone Link
			Boolean bDigiTV = UI.WaitForObject(oR_AccountOverview.lnkHomePhoneSecondaryNav, 30,lDriver);
			if(bDigiTV.equals(true))
			{
			//	System.out.println("Session ID before back " + ((RemoteWebDriver)lDriver).getSessionId());
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on HomePhone Link");
			//	UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkHomePhoneSecondaryNav, null, lDriver);
				oR_AccountOverview.lnkHomePhoneSecondaryNav.click();
				Thread.sleep(15000);
				new Actions(lDriver).moveByOffset(0, 40).build().perform();
				//aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
				
				//Verify that HomePhone landing page is displayed
				if(lDriver.getCurrentUrl().contains("myattmonitor"))
				{
					if(lDriver.getCurrentUrl().contains("HomePhoneProductLandingPage") || lDriver.getCurrentUrl().contains("LocalServiceDetails"))
					{
						Report.ValidationPoint(testContext.getName(), "Verify that HomePhone landing page is displayed", "true", "true", true);
						//Verify the domain of the page
						Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
					
						// Check for any error  message
						if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
						}else{
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
						}
					}
				}
				else if(UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, 10, lDriver))
				{
					Report.OperationPoint(testContext.getName(), "User is navigated to login page. Clicking on Back button");
					lDriver.navigate().back();
					Thread.sleep(5000);
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on HomePhone Link");
				
				if(UI.WaitForObject(oR_AccountOverview.lnkHomePhoneSecondaryNav, UI.iObjTimeOut, lDriver)){
				//	UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkHomePhoneSecondaryNav, null, lDriver);
				    oR_AccountOverview.lnkHomePhoneSecondaryNav.click();
				    Thread.sleep(10000);
				    new Actions(lDriver).moveByOffset(0, 40).build().perform();
				    //aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
					
					if(lDriver.getCurrentUrl().contains("HomePhoneProductLandingPage") && lDriver.getCurrentUrl().contains("myattmonitor"))
					{
						Report.ValidationPoint(testContext.getName(), "Verify that HomePhone landing page is displayed", "true", "true", true);
						//Verify the domain of the page
						Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
					
						// Check for any error  message
						if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
						}else{
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
						}		    
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify that HomePhone landing page is displayed", "true", "false", true);
					}
					
				 }else{
					 Report.ValidationPoint(testContext.getName(), "Verify HomePhone Link", "true", bDigiTV.toString(), true);
				 }
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that HomePhone landing page is displayed", "true", "false", true);
				}

				//Verify following sections links in each in footer section of Internet page:
				//1.Manage My Account
				//2.HomePhone Extras
				//3. HomePhone Support
				List <WebElement> elmFooter = lDriver.findElements(By.xpath("//div[@class='table tableTextWrap']//div/h2"));
				if(elmFooter.size()>0)
				{
					String [] sText = new String[elmFooter.size()];
					if(elmFooter.size()==3)
					{
						Report.ValidationPoint(testContext.getName(), "Verify 3 sections links in each in footer section of Internet page", "true","true", true);
					}
					//Retrieving Text
					for(int i=0;i<elmFooter.size();i++)
					{
						sText[i] = elmFooter.get(i).getText();
					}
					Report.OperationPoint(testContext.getName(), "	Operational - retrieving the Footer names ");
					for(int i=0;i<elmFooter.size();i++)
					{
						Report.OperationPoint(testContext.getName(),sText[i]);
					}
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify HomePhone Link", "true", bDigiTV.toString(), true);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
/*****************************************************************************************************************************************/		
	//Wireless SA
	//BVT-Wireless_06_Change_Rate_Plan
	public static void ValidateWirelessChangeRatePlan(final ITestContext testContext) throws Exception 
	{
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sUrlDomain = IO.GetParamVal(sTestParams, "DOMAIN");
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
		OR_MyWirelessPlan oR_MyWirelessPlan = PageFactory.initElements(lDriver,OR_MyWirelessPlan.class);
		OR_ViewOrChangeRatePlan oR_ViewOrChangeRatePlan = PageFactory.initElements(lDriver,OR_ViewOrChangeRatePlan.class);
		OR_Login oR_Login = PageFactory.initElements(lDriver,OR_Login.class);
	//	Actions aAction = new Actions(lDriver);
		
		try
		{
			
			Report.OperationPoint(testContext.getName(), "***************************************** VALIDATING CHANGE RATE PLAN FLOW *****************************************");
			
			//Click Wireless Link
			Boolean bWireless = UI.WaitForObject(oR_AccountOverview.lnkWirelessSecNav,30,lDriver);
			if(bWireless.equals(true))
			{
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Wireless Link");
			//	UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav, null, lDriver);
				oR_AccountOverview.lnkWirelessSecNav.click();
				Thread.sleep(13000);
				new Actions(lDriver).moveByOffset(0, 40).build().perform();
				//aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
				
				//Validate Wireless landing page is displayed
				//Boolean bWirelessPg = UI.WaitForObject(oR_MyWirelessPlan.txtMyWirelessService,20,lDriver);
				if(lDriver.getCurrentUrl().contains("PlansFeatures") && lDriver.getCurrentUrl().contains("myattmonitor"))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Wireless landing page is displayed", "true", "true", true);
					//Verify the domain of the page
					Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
					//Validate Manage link
					Boolean bManage = UI.WaitForObject(oR_MyWirelessPlan.lnkManage,5,lDriver);
					Report.ValidationPoint(testContext.getName(), "Validate Manage link is displayed Wireless landing page", "true", bManage.toString(), true);
					//Validate Add a device link
					Boolean bAddDevice = UI.WaitForObject(oR_MyWirelessPlan.lnkAddADevice,5,lDriver);
					Report.ValidationPoint(testContext.getName(), "Validate Add a device link is displayed Wireless landing page", "true", bAddDevice.toString(), true);
					//Validate Change plan Button
					Boolean bChangePlan = UI.WaitForObject(oR_MyWirelessPlan.btnChangePlan,5,lDriver);
				
					// Check for any error  message
					if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
					}else{
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
					}
					
					if(bChangePlan)
					{
						Report.ValidationPoint(testContext.getName(), "Validate Change plan Button is displayed Wireless landing page", "true","true", true);
						//Click on Change plan button
						Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Change plan button");
						oR_MyWirelessPlan.btnChangePlan.click();
						
						//Validate View or change rate plan page
						Boolean bViewChange = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtViewOrChangeRatePlan,120,lDriver);
						Report.ValidationPoint(testContext.getName(), "Validate View or change rate plan page", "true", bViewChange.toString(), true);
						//Verify the domain of the page
						Boolean bDomain1 = UI.VerifyURLofPage(sUrlDomain,lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain1.toString(), true);
						
						// Check for any error  message
						if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
						}else{
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
						}
						//Validate step indicator
						//1 Select a Plan
						//2 Select Effective Date
						//3 Plan Review
						//4 Confirmation
						Boolean bStepIndi = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtStepIndicator,30,lDriver);
						if(bStepIndi)
						{
							Report.ValidationPoint(testContext.getName(), "Validate step indicator", "true", bStepIndi.toString(), true);
						}
						/*else
						{
							Report.ValidationPoint(testContext.getName(), "Validate step indicator", "true", bStepIndi.toString(), true);
						}*/
					}
				}
				else if(UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, 10, lDriver))
				{
					Report.OperationPoint(testContext.getName(), "User is navigated to login page. Clicking on Back button");
					lDriver.navigate().back();
					Thread.sleep(5000);
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Wireless Link");
					//System.out.println("Session ID after back" + ((RemoteWebDriver)lDriver).getSessionId());
					UI.WaitForObject(oR_AccountOverview.lnkWirelessSecNav, UI.iObjTimeOut, lDriver);
				//	UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav, null, lDriver);
					oR_AccountOverview.lnkWirelessSecNav.click();
					
					new Actions(lDriver).moveByOffset(0, 40).build().perform();
					//aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
					//Validate Wireless landing page is displayed
					//Boolean bWirelessPg1 = UI.WaitForObject(oR_MyWirelessPlan.txtMyWirelessService,120,lDriver);
					if(lDriver.getCurrentUrl().contains("PlansFeatures") && lDriver.getCurrentUrl().contains("myattmonitor"))
					{
						Report.ValidationPoint(testContext.getName(), "Validate Wireless landing page is displayed", "true", "true", true);
						//Verify the domain of the page
						Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
						//Validate Manage link
						Boolean bManage = UI.WaitForObject(oR_MyWirelessPlan.lnkManage,5,lDriver);
						Report.ValidationPoint(testContext.getName(), "Validate Manage link is displayed Wireless landing page", "true", bManage.toString(), true);
						//Validate Add a device link
						Boolean bAddDevice = UI.WaitForObject(oR_MyWirelessPlan.lnkAddADevice,5,lDriver);
						Report.ValidationPoint(testContext.getName(), "Validate Add a device link is displayed Wireless landing page", "true", bAddDevice.toString(), true);
						//Validate Change plan Button
						Boolean bChangePlan = UI.WaitForObject(oR_MyWirelessPlan.btnChangePlan,5,lDriver);
					
						// Check for any error  message
						if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
						}else{
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
						}
					    
						if(bChangePlan)
						{
							Report.ValidationPoint(testContext.getName(), "Validate Change plan Button is displayed Wireless landing page", "true","true", true);
							//Click on Change plan button
							Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Change plan button");
							oR_MyWirelessPlan.btnChangePlan.click();
							
							//Validate View or change rate plan page
							Boolean bViewChange = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtViewOrChangeRatePlan,120,lDriver);
							Report.ValidationPoint(testContext.getName(), "Validate View or change rate plan page", "true", bViewChange.toString(), true);
							//Verify the domain of the page
							Boolean bDomain1 = UI.VerifyURLofPage(sUrlDomain,lDriver);
							Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain1.toString(), true);
							
											    
							// Check for any error  message
							if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
								 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
							}else{
								 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
							}
							//Validate step indicator
							//1 Select a Plan
							//2 Select Effective Date
							//3 Plan Review
							//4 Confirmation
							Boolean bStepIndi = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtStepIndicator,30,lDriver);
							if(bStepIndi)
							{
								Report.ValidationPoint(testContext.getName(), "Validate step indicator", "true", bStepIndi.toString(), true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate step indicator", "true", bStepIndi.toString(), true);
							}
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Validate Change plan Button is displayed Wireless landing page", "true","false", true);
						}
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify wireless Landing page", "true", bWireless.toString(), true);
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify wireless Landing page", "true", bWireless.toString(), true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Wireless link", "true", bWireless.toString(), true);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}

/*****************************************************************************************************************************************/
	//Uverse SA
	//BVT-Uverse_07_Validate_TV_page, extra channels and TV Receivers
	public static void ValidateUverseTVextra(final ITestContext testContext) throws Exception 
	{
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sUrlDomain = IO.GetParamVal(sTestParams, "DOMAIN");
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
		OR_TelevisionService oR_TelevisionService = PageFactory.initElements(lDriver,OR_TelevisionService.class);
		OR_Login oR_Login = PageFactory.initElements(lDriver,OR_Login.class);
//		Actions aAction = new Actions(lDriver);

		try
		{
			Report.OperationPoint(testContext.getName(), "***************************************** VALIDATING UVERSE TV, EXTRA CHANNEL AND TV RECEIVERS FLOW *****************************************");
			
			//Check for secondary navigation
//			if(!UI.checkForScecondaryNavigation(oR_AccountOverview.lnkDigitalTVSecondaryNav, oR_AccountOverview.lnkMyATTPrimaryNav, lDriver)){
//				Report.ValidationPoint(testContext.getName(), "Verify Digital TV link is available under secondary menu","true" , "false", true);
//			    return;
//			}
			
			//Click on Digital TV Link from Secondary navigation
			Boolean bDTV = UI.WaitForObject(oR_AccountOverview.lnkDigitalTVSecondaryNav,30,lDriver);
			if(bDTV.equals(true))
			{
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Digital TV Link from Secondary navigation");
				//UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkDigitalTVSecondaryNav, null, lDriver);
				oR_AccountOverview.lnkDigitalTVSecondaryNav.click();
				
				Thread.sleep(10000);
				new Actions(lDriver).moveByOffset(0, 40).build().perform();
				//aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
				//Validate my TV landing page
				if(lDriver.getCurrentUrl().contains("TvProductLandingPage") && lDriver.getCurrentUrl().contains("myattmonitor"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify my TV landing page", "true","true", true);
					//Verify the domain of the page
					Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
							 	    
					
					// Check for any error  message
					if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
					}else{
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
					}
					
					//Validate My programming section
					Boolean bProgramming = UI.WaitForObject(oR_TelevisionService.txtMyProgramming,5,lDriver);
					if(bProgramming)
					{
						Report.ValidationPoint(testContext.getName(), "Verify My programming section is being displayed", "true", bProgramming.toString(), true);
					}
					
					List <WebElement> elmFooter = lDriver.findElements(By.xpath("//div[@class='table tableTextWrap']//div/h2"));
					String [] sText = new String[elmFooter.size()];
					if(elmFooter.size()==3)
					{
						Report.ValidationPoint(testContext.getName(), "Verify 3 sections links in each in footer section of Internet page", "true","true", true);
					}
					//Retrieving Text
					for(int i=0;i<elmFooter.size();i++)
					{
						sText[i] = elmFooter.get(i).getText();
					}
					Report.OperationPoint(testContext.getName(), "	Operational - retrieving the Footer names ");
					for(int i=0;i<elmFooter.size();i++)
					{
						Report.OperationPoint(testContext.getName(),sText[i]);
					}
				}
				else if(UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, 10, lDriver))
				{
					Report.OperationPoint(testContext.getName(), "User is navigated to login page. Clicking on Back button");
					lDriver.navigate().back();
					Thread.sleep(5000);
				//	System.out.println("Session ID after " + ((RemoteWebDriver)lDriver).getSessionId());
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Digital TV Link from Secondary navigation");
					if(UI.WaitForObject(oR_AccountOverview.lnkDigitalTVSecondaryNav, UI.iObjTimeOut, lDriver)){
					
				//	UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkDigitalTVSecondaryNav, null, lDriver);
					oR_AccountOverview.lnkDigitalTVSecondaryNav.click();
					
					Thread.sleep(10000);
					new Actions(lDriver).moveByOffset(0, 40).build().perform();
					//aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
					if(lDriver.getCurrentUrl().contains("TvProductLandingPage") && lDriver.getCurrentUrl().contains("myattmonitor"))
					{
						Report.ValidationPoint(testContext.getName(), "Verify my TV landing page", "true","true", true);
						//Verify the domain of the page
						Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
											    						
						// Check for any error  message
						if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
						}else{
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
						}
					    
						//Validate My programming section
						Boolean bProgramming = UI.WaitForObject(oR_TelevisionService.txtMyProgramming,5,lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify My programming section is being displayed", "true", bProgramming.toString(), true);
						
						List <WebElement> elmFooter = lDriver.findElements(By.xpath("//div[@class='table tableTextWrap']//div/h2"));
						String [] sText = new String[elmFooter.size()];
						if(elmFooter.size()==3)
						{
							Report.ValidationPoint(testContext.getName(), "Verify 3 sections links in each in footer section of Internet page", "true","true", true);
						}
						//Retrieving Text
						for(int i=0;i<elmFooter.size();i++)
						{
							sText[i] = elmFooter.get(i).getText();
						}
						Report.OperationPoint(testContext.getName(), "	Operational - retrieving the Footer names ");
						for(int i=0;i<elmFooter.size();i++)
						{
							Report.OperationPoint(testContext.getName(),sText[i]);
						}
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify my TV landing page", "true","false", true);
					}
				 }else{
					 Report.ValidationPoint(testContext.getName(), "Verify Digital TV Link from Secondary navigation", "true", bDTV.toString(), true);
				 }
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify my TV landing page", "true","false", true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Digital TV Link from Secondary navigation", "true", bDTV.toString(), true);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
/*****************************************************************************************************************************************/
	//Wireless SA
	//BVT_wireless_SA_05_Shop
	public static void ValidateWirelessShop(final ITestContext testContext) throws Exception 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
		OR_Shop oR_Shop = PageFactory.initElements(lDriver,OR_Shop.class);
	//	OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver,OR_MakeAPayment.class);
		
		try
		{	
			
			/*oR_AccountOverview.lnkOverview.click();
			Thread.sleep(5000);
			//Validate Incomplete payment popup
			Boolean bIncomplete = UI.WaitForObject(oR_MakeAPayment.txtIncomplete, 5,lDriver);
			if(bIncomplete)
			{
				//Click on Yes continue
				oR_MakeAPayment.btnYesCont.click();
			}*/
			Report.OperationPoint(testContext.getName(), "***************************************** VALIDATING SHOP FLOW *****************************************");
			//Click on link shop on primary navigation
			Boolean bShop = UI.WaitForObject(oR_AccountOverview.lnkShopPrimaryNav,40,lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify link shop on primary navigation", "true", bShop.toString(), true);
			Report.OperationPoint(testContext.getName(),"Operational - Click on link shop on primary navigation");
			if(UI.WaitForObject(oR_AccountOverview.lnkShopPrimaryNav,UI.iObjTimeOut,lDriver)){
				oR_AccountOverview.lnkShopPrimaryNav.click();
			}
			
			//Validate User is directed to Shop landing page
			Boolean bShopPg = UI.WaitForObject(oR_Shop.txtShopHeading,20,lDriver);
			Boolean bShopPg1 = UI.WaitForObject(oR_Shop.txtShop, 15,lDriver);
			if(bShopPg.equals(true))
			{
				Report.ValidationPoint(testContext.getName(), "Verify User is directed to Shop landing page", "true", bShopPg.toString(), true);
			}
			else if(bShopPg1.equals(true))
			{
				Report.ValidationPoint(testContext.getName(), "Verify User is directed to Shop landing page", "true",bShopPg1.toString(), true);
			}
			else if(!bShopPg.equals(true) && !bShopPg1.equals(true))
			{
				if(UI.WaitForObject(oR_AccountOverview.lnkShopPrimaryNav, 3,lDriver)){
				oR_AccountOverview.lnkShopPrimaryNav.click();
				Thread.sleep(3000);
				//Validate User is directed to Shop landing page
				if(bShopPg.equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Verify User is directed to Shop landing page", "true", bShopPg.toString(), true);
				
					// Check for any error  message
					if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
					}else{
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
					}
				}
				else if(bShopPg1.equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Verify User is directed to Shop landing page", "true",bShopPg1.toString(), true);
				  
					// Check for any error  message
					if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
					}else{
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
					}
				
				}
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Verify Shop link is displayed", "true", "false", true);
			  }
			}
			else 
			{
				Report.ValidationPoint(testContext.getName(), "Verify User is directed to Shop landing page", "true","false", true);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
		
	/*****************************************************************************************************************************************/
		//Wireline SA 07
		//BVT_SA_07_DigitalTV
		//No particular method required.
		
/*****************************************************************************************************************************************/
	//Wireless SA
	//BVT_wireless_SA_03_Payment
	public static void ValidateWirelessPayment(final ITestContext testContext) throws Exception 
	{
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sUrlDomain = IO.GetParamVal(sTestParams, "DOMAIN");
		String sPaymentAmount = IO.GetParamVal(sTestParams, "PAYMENT_AMOUNT");
		String sNameOnCard = IO.GetParamVal(sTestParams, "NAME_ON_CARD");
		String sRoutingNo	= IO.GetParamVal(sTestParams, "ROUTING_NO");
		String sBankAccNo = IO.GetParamVal(sTestParams, "BANK_ACC_NO");
		String sReBankAccNo = IO.GetParamVal(sTestParams, "RE_BANK_ACC_NO");
	
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver,OR_BillAndUsage.class);
		OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver,OR_MakeAPayment.class);
		OR_ReviewPaymentDetails oR_ReviewPaymentDetails = PageFactory.initElements(lDriver,OR_ReviewPaymentDetails.class);
		OR_PaymentConfirmation oR_PaymentConfirmation = PageFactory.initElements(lDriver,OR_PaymentConfirmation.class);
		OR_Login oR_Login = PageFactory.initElements(lDriver,OR_Login.class);
//		Actions aAction = new Actions(lDriver);

		try
		{
					Report.OperationPoint(testContext.getName(), "***************************************** VALIDATING BILLING AND PAYMENT FLOW *****************************************");
		
			Thread.sleep(5000);
			
			//Click on Billing & Usage Link from Secondary navigation
			Boolean bBPU = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,120,lDriver);
			if(bBPU.equals(true))
			{
				//UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
				Thread.sleep(12000);
				new Actions(lDriver).moveByOffset(0, 40).build().perform();
				////aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
				//Validate billing and usage landing page
				if(lDriver.getCurrentUrl().contains("Bill") && lDriver.getCurrentUrl().contains("myattmonitor"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify billing and usage landing page", "true","true", true);
					
					//Verify the domain of the page
					Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
											
					// Check for any error  message
					if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
					}else{
						 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
					}
					
					//aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
					Thread.sleep(2000);
					if(UI.WaitForObject(oR_BillAndUsage.lnkBillTab, 5, lDriver))
					{
						oR_BillAndUsage.lnkBillTab.click();
					}
					//Validate Make a Payment button
					Boolean bMakePayment = UI.WaitForObject(oR_BillAndUsage.btnMakePaymentInBillingPage,20,lDriver);
					if(bMakePayment)
					{
						Report.ValidationPoint(testContext.getName(), "Verify Make a Payment button", "true","true", true);
						//Click on Make a payment button
						Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Make a payment button");
						oR_BillAndUsage.btnMakePaymentInBillingPage.click();
						
						Boolean bMakePaymentPg = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment,120,lDriver);
						//Validate Make a payment landing page
						if(bMakePaymentPg)
						{
							Report.ValidationPoint(testContext.getName(), "Verify Make a Payment landing page", "true", bMakePaymentPg.toString(), true);
							
							//Verify the domain of the page
							Boolean bDomain1 = UI.VerifyURLofPage(sUrlDomain,lDriver);
							Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain1.toString(), true);
									
						  
							// Check for any error  message
							if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
								 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
							}else{
								 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
							}
							
							//Verify Steps 1- Step 3 are displayed on Right side and Step 1 is highlighted.
							List<WebElement> Steps = lDriver.findElements(By.xpath("//div[@class='step-indicator-hor box']//li"));
							if(Steps.size()>0)
							{
								if(Steps.size()==3)
								{
									Report.ValidationPoint(testContext.getName(), "Verify Steps 1- Step 3 are displayed on Right side", "true","true", true);
									//Retrieving the steps
									Report.OperationPoint(testContext.getName(), "	Operational - Retrieving the steps");
									/*String [] sSteps = new String [Steps.size()];
									
									for(int i=0;i<Steps.size();i++)
									{
										sSteps[i]=Steps.get(i).getText();
									}
									for(int i=0;i<Steps.size();i++)
									{
										Report.OperationPoint(testContext.getName(),sSteps[i]);
									}*/
									
									//Validate Step 1 is highlighted
									String sClass = Steps.get(0).getAttribute("class");
									if(sClass.contains("current"))
									{
										Report.ValidationPoint(testContext.getName(), "Verify Step 1 is highlighted", "true","true", true);
									}
									else
									{
										Report.ValidationPoint(testContext.getName(), "Verify Step 1 is highlighted", "true","false", true);
									}
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Verify Steps 1- Step 3 are displayed on Right side", "true","false", true);
								}
							}
							
							//Validate 'Select Account to Pay' text.
							//If this is present, it means that there are multiple accounts present. Hence below code for more than 1 accounts.
							if(UI.WaitForObject(oR_MakeAPayment.txtSelectAccountToPay, 1,lDriver))
							{
								List <WebElement> lstAccounts = lDriver.findElements(By.xpath("//div[@class='map-multi-account styled_forms']//ul"));
								if(lstAccounts.size()>0)
								{
									Report.OperationPoint(testContext.getName(), "There are "+lstAccounts.size()+" number of accounts");
									//Validate Due date for the accounts
									List <WebElement> lstElm = lDriver.findElements(By.xpath("//span[@class='label'][text()='Due Date:']"));
									for(int i=0;i<lstElm.size();i++)
									{
										if(lstElm.get(i).getText().contains("Date"))
										{
											Report.ValidationPoint(testContext.getName(), "Validate due date", "true","true", true);
										}
									}
									//Validate Split the payment link
									List <WebElement> lstSplit = lDriver.findElements(By.xpath("//a[contains(@id,'addpmtlink')]"));
									if(lstSplit.size()==lstAccounts.size())
									{
										Report.ValidationPoint(testContext.getName(), "Validate Split the payment link is present for "+lstAccounts.size()+" number of accounts", "true","true", true);
									}
									// Verify Amount, Date text boxes are given and Dropdown to select Payment Method is present.
									Boolean bAmount = UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1,5,lDriver);
									if(bAmount)
									{
										
										//Enter the amount to be paid
										oR_MakeAPayment.edtPaymentAmount1.clear();
										oR_MakeAPayment.edtPaymentAmount1.sendKeys(sPaymentAmount);
										
									}
									else
									{
										Report.ValidationPoint(testContext.getName(), "Verify payment amount editbox", "true", bAmount.toString(), true);
										return;
									}
									//Validate Date text boxes
									Boolean bDateEdit = UI.WaitForObject(oR_MakeAPayment.edtDate1,5,lDriver);
									Boolean bDateTxt = UI.WaitForObject(oR_MakeAPayment.txtDate,5,lDriver);
									if(bDateEdit)
									{
										Report.ValidationPoint(testContext.getName(), "Verify Date text boxes", "true", bDateEdit.toString(), true);
									}
									else if(bDateTxt)
									{
										Report.ValidationPoint(testContext.getName(), "Verify Date text boxes", "true", bDateTxt.toString(), true);
									}
									
									//validate Payment method
									Boolean bPaymentMethod = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethod,5,lDriver);
									if(!bPaymentMethod)
									{
										Report.ValidationPoint(testContext.getName(), "Verify Payment method", "true", bPaymentMethod.toString(), true);
										return;
									}
								}
							}
							else
							{
								//Validate Account number
								Boolean bAccNo = UI.WaitForObject(oR_MakeAPayment.txtAccount,1,lDriver);
								Boolean bBAN = UI.WaitForObject(oR_MakeAPayment.txtBAN,1,lDriver);
								if(bAccNo.equals(true))
								{
									Report.ValidationPoint(testContext.getName(), "Verify Account number", "true", bAccNo.toString(), true);
								}
								else if(bBAN.equals(true))
								{
									Report.ValidationPoint(testContext.getName(), "Verify BAN number", "true", bBAN.toString(), true);
								}
								/*else
								{
									Report.ValidationPoint(testContext.getName(), "Verify BAN number", "true", bBAN.toString(), true);
								}*/
								//Validate due date
								Boolean bDueDate = UI.WaitForObject(oR_MakeAPayment.txtDueDate,5,lDriver);
								Report.ValidationPoint(testContext.getName(), "Verify due date ", "true", bDueDate.toString(), true);
								//Validate checkbox Enroll in autopay
								Boolean bEnrollInAutpoayCheckbox = UI.WaitForObject(oR_MakeAPayment.chkEnrollInAutopayCheckbox,5);
								if(bEnrollInAutpoayCheckbox.equals(true))
								{
									Report.ValidationPoint(testContext.getName(), "Verify checkbox Enroll in autopay", "true", bEnrollInAutpoayCheckbox.toString(), true);
								}
								//Validate Split the payment link
								Boolean bSplitPayment = UI.WaitForObject(oR_MakeAPayment.lnkSplitThisPayment,5,lDriver);
								if(bSplitPayment.equals(true))
								{
									Report.ValidationPoint(testContext.getName(), "Verify Split the payment link", "true", bSplitPayment.toString(), true);
								}
								
								// Verify Amount, Date text boxes are given and Dropdown to select Payment Method is present.
								Boolean bAmount = UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1,5,lDriver);
								if(bAmount)
								{
									
									//Enter the amount to be paid
									oR_MakeAPayment.edtPaymentAmount.clear();
									oR_MakeAPayment.edtPaymentAmount.sendKeys(sPaymentAmount);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Verify payment amount editbox", "true", bAmount.toString(), true);
									return;
								}
								//Validate Date text boxes
								Boolean bDateEdit = UI.WaitForObject(oR_MakeAPayment.edtDate1,5,lDriver);
								Boolean bDateTxt = UI.WaitForObject(oR_MakeAPayment.txtDate,5,lDriver);
								if(bDateEdit)
								{
									Report.ValidationPoint(testContext.getName(), "Verify Date text boxes", "true", bDateEdit.toString(), true);
								}
								else if(bDateTxt)
								{
									Report.ValidationPoint(testContext.getName(), "Verify Date text boxes", "true", bDateTxt.toString(), true);
								}
								
								//validate Payment method
								Boolean bPaymentMethod = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethod,5,lDriver);
								if(!bPaymentMethod)
								{
									Report.ValidationPoint(testContext.getName(), "Verify Payment method", "true", bPaymentMethod.toString(), true);
									return;
								}
							}
	
							//Select Payment method as New Checking/Saving Account
							Boolean bSelect = UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod,"New checking / savings account",testContext.getName());
							if(bSelect.equals(true))
							{
								//Validate  payment method details popup
								Boolean bPaymentPopup = UI.WaitForObject(oR_MakeAPayment.frmNewCheckingSavingPaymentMethod,50,lDriver);
								//Switch to the popup
								lDriver.switchTo().frame(oR_MakeAPayment.frmNewCheckingSavingPaymentMethod);
								//Enter payment bank accounts details
								{
									Thread.sleep(15000);
									Report.ValidationPoint(testContext.getName(), "Verify payment method details popup", "true", bPaymentPopup.toString(), true);
									Report.OperationPoint(testContext.getName(),"Operational - Name on bank account: "+sNameOnCard);
									oR_MakeAPayment.edtNameOnBankAcc.sendKeys(sNameOnCard);
									Report.OperationPoint(testContext.getName(),"Operational - Routing number: "+sRoutingNo);
									oR_MakeAPayment.edtRoutingNumber.sendKeys(sRoutingNo);
									Report.OperationPoint(testContext.getName(),"Operational - Bank account number: "+sBankAccNo);
									oR_MakeAPayment.edtBankAccountNumber.sendKeys(sBankAccNo);
									oR_MakeAPayment.edtReenterAccNum.sendKeys(sReBankAccNo);
									/*if(UI.WaitForObject(oR_MakeAPayment.chkPaymentFrameSaveMyPaymentInformationCheckbox, 1).equals(true))
									{
										oR_MakeAPayment.chkPaymentFrameSaveMyPaymentInformationCheckbox.click();
									}*/					
									//Click on continue button
									oR_MakeAPayment.btnPaymentFrameContinue.click();
									Thread.sleep(10000);
									lDriver.switchTo().defaultContent();
								}
							
								//click on next button
								if(UI.WaitForObject(oR_MakeAPayment.btnNext, UI.iObjTimeOut,lDriver)){
								     oR_MakeAPayment.btnNext.click();
								}
								Thread.sleep(5000);
								
	//							//Validate popup when Balance is 0.00
	//							if(UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert, 20))
	//							{
	//								oR_MakeAPayment.lnkContinue.click();
	//							}
								//Validate Are you sure? popup
								if(UI.WaitForObject(oR_MakeAPayment.txtAreYouSure, 50,lDriver))
								{
									Thread.sleep(10000);
									Report.ValidationPoint(testContext.getName(), "Verify Are you sure?", "true","true", true);
									oR_MakeAPayment.lnkNo.click();
								}else if(UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert, 20,lDriver)) {
									oR_MakeAPayment.lnkContinue.click();
								}
								
								//Validate Payment Review page
								Boolean bPaymentReview = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,120,lDriver);
								if(bPaymentReview)
								{
									Report.ValidationPoint(testContext.getName(), "Verify Payment Review page", "true", bPaymentReview.toString(), true);
									//Verify the domain of the page
									Boolean bDomain2 = UI.VerifyURLofPage(sUrlDomain,lDriver);
									Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain2.toString(), true);
									
									//Validate Payment information
									//div[@class='PadLeft12']/div
									List<WebElement> lstPaymentInfo = lDriver.findElements(By.xpath("//div[@class='PadLeft12']/div"));
									String sInfo [] = new String[lstPaymentInfo.size()];
									for(int i=0;i<lstPaymentInfo.size();i++)
									{
										sInfo[i] = lstPaymentInfo.get(i).getText();
									}
									//Retrieving the payment info
									Report.OperationPoint(testContext.getName(),"Operational - Retrieving the payment info");
									for(int i=0;i<lstPaymentInfo.size();i++)
									{
										if(!sInfo[i].isEmpty())
										{
											Report.OperationPoint(testContext.getName(),sInfo[i]);
										}
									}
									//Validate Step 2 is highlighted
									List<WebElement> Steps2 = lDriver.findElements(By.xpath("//div[@class='step-indicator-hor box']//li"));
									if(Steps2.size()>0)
									{
										String sClass2 = Steps2.get(1).getAttribute("class");
										if(sClass2.contains("current"))
										{
											Report.ValidationPoint(testContext.getName(), "Verify Step 2 is highlighted", "true","true", true);
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Verify Step 2 is highlighted", "true","false", true);
										}
									}
									
									//Click on submit button
									oR_ReviewPaymentDetails.btnSubmit.click();
									
									//Validate Payment Confirmation page is displayed
									Boolean bPaymentConf = UI.WaitForObject(oR_PaymentConfirmation.txtPaymentConfirmationTitle,80,lDriver);
									if(bPaymentConf)
									{
										Report.ValidationPoint(testContext.getName(), "Verify Payment Confirmation page is displayed", "true", bPaymentConf.toString(), true);
										//Verify the domain of the page
										Boolean bDomain3 = UI.VerifyURLofPage(sUrlDomain,lDriver);
										Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain3.toString(), true);
										
										//Validate Step 3 is highlighted
										List<WebElement> Steps3 = lDriver.findElements(By.xpath("//div[@class='step-indicator-hor box']//li"));
										if(Steps3.size()>0)
										{
											String sClass3 = Steps3.get(2).getAttribute("class");
											if(sClass3.contains("last"))
											{
												Report.ValidationPoint(testContext.getName(), "Verify Step 3 is highlighted", "true","true", true);
											}
											else
											{
												Report.ValidationPoint(testContext.getName(), "Verify Step 3 is highlighted", "true","false", true);
											}
										}
									}
									else
									{
										Report.ValidationPoint(testContext.getName(), "Verify Payment Confirmation page is displayed", "true", bPaymentConf.toString(), true);
									}
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Verify Payment Review page", "true", bPaymentReview.toString(), true);
								}
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Select Payment method as New Checking/Saving Account", "true","Not present", true);
							}
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Verify Make a Payment landing page", "true", bMakePaymentPg.toString(), true);
						}
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify Make a Payment button", "true", bMakePayment.toString(), true);
					}
				}
				else if(UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, 10, lDriver))
				{
					Report.OperationPoint(testContext.getName(), "User is navigated to login page. Clicking on Back button");
					lDriver.navigate().back();
					Thread.sleep(2000);
					
				//	System.out.println("Session ID before Back" + ((RemoteWebDriver)lDriver).getSessionId());
					 UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut, lDriver);
				//	UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
					 oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
					 Thread.sleep(5000);
					 new Actions(lDriver).moveByOffset(0, 40).build().perform();
					//aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
					if(lDriver.getCurrentUrl().contains("Bill") && lDriver.getCurrentUrl().contains("myattmonitor"))
					{
						Report.ValidationPoint(testContext.getName(), "Verify billing and usage landing page", "true", "true", true);
						
						//Verify the domain of the page
						Boolean bDomain = UI.VerifyURLofPage(sUrlDomain,lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain.toString(), true);
																 
						// Check for any error  message
						if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);					
						}else{
							 Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
						}
						
						//aAction.moveToElement(oR_AccountOverview.btnLogout).build().perform();
						Thread.sleep(2000);
						if(UI.WaitForObject(oR_BillAndUsage.lnkBillTab, 5, lDriver))
						{
							oR_BillAndUsage.lnkBillTab.click();
						}
						//Validate Make a Payment button
						Boolean bMakePayment = UI.WaitForObject(oR_BillAndUsage.btnMakePaymentInBillingPage,20,lDriver);
						//Boolean bMakePaymentWirelineSA = UI.WaitForObject(oR_AccountOverview.btnMakeAPayment_OldDashboard,10,lDriver);
						if(bMakePayment)
						{
							Report.ValidationPoint(testContext.getName(), "Verify Make a Payment button", "true","true", true);
							
							if(bMakePayment)
							{
								//Click on Make a payment button
								Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Make a payment button");
								oR_BillAndUsage.btnMakePaymentInBillingPage.click();
							}
							/*else if(bMakePaymentWirelineSA)
							{
								//Click on Make a payment button
								Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Make a payment button");
								oR_AccountOverview.btnMakeAPayment_OldDashboard.click();
							}*/
							//Payment in frame.
							//Boolean bPaymentFrm = UI.WaitForObject(oR_BillAndUsage.frmQuickPayment, 30);
							Boolean bMakePaymentPg = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment,120,lDriver);
							/*if(bPaymentFrm)
							{
								Thread.sleep(30000);
								lDriver.switchTo().frame(oR_BillAndUsage.frmQuickPayment);
								oR_BillAndUsage.edtEnteredAmt.clear();
								oR_BillAndUsage.edtEnteredAmt.sendKeys(sPaymentAmount);
								Report.OperationPoint(testContext.getName(),"Clicking on Next button");
								oR_BillAndUsage.btnNext.click();
								
								//Validate review payment page
								Boolean bReviewPaymentFrm = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentFrm,30);
								Report.ValidationPoint(testContext.getName(), "Verify the review payment frame", "true", bReviewPaymentFrm.toString(), true);
								Thread.sleep(5000);
								//Click on submit button
								Report.OperationPoint(testContext.getName(),"Clicking on submit button");
								oR_ReviewPaymentDetails.btnSubmitFrm.click();
								
								//Validate Payment confirmation frame
								Boolean bPaymentConf = UI.WaitForObject(oR_PaymentConfirmation.txtPaymentConfFrm,10);
								Boolean bPaymentFailure = UI.WaitForObject(oR_PaymentConfirmation.txtPaymentFailure,30);
								if(bPaymentConf || bPaymentFailure)
								{
									Report.ValidationPoint(testContext.getName(), "Verify the Payment confirmation frame", "true","true", true);
								}
								Thread.sleep(5000);
								//Click on Close link
								Report.OperationPoint(testContext.getName(),"Clicking on Close link");
								oR_BillAndUsage.lnkClose.click();
								
								lDriver.switchTo().defaultContent();*/
							//}
							//Validate Make a payment landing page
							
							if(bMakePaymentPg)
							{
								Report.ValidationPoint(testContext.getName(), "Verify Make a Payment landing page", "true", bMakePaymentPg.toString(), true);
								
								//Verify the domain of the page
								Boolean bDomain1 = UI.VerifyURLofPage(sUrlDomain,lDriver);
								Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain1.toString(), true);
								
							    
							  //Check for error message
								if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
								    Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);
								}else{
									Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
								}
							    
								//Verify Steps 1- Step 3 are displayed on Right side and Step 1 is highlighted.
								List<WebElement> Steps = lDriver.findElements(By.xpath("//div[@class='step-indicator-hor box']//li"));
								if(Steps.size()>0)
								{
									if(Steps.size()==3)
									{
										Report.ValidationPoint(testContext.getName(), "Verify Steps 1- Step 3 are displayed on Right side", "true","true", true);
										//Retrieving the steps
										Report.OperationPoint(testContext.getName(), "	Operational - Retrieving the steps");
										/*String [] sSteps = new String [Steps.size()];
										
										for(int i=0;i<Steps.size();i++)
										{
											sSteps[i]=Steps.get(i).getText();
										}
										for(int i=0;i<Steps.size();i++)
										{
											Report.OperationPoint(testContext.getName(),sSteps[i]);
										}*/
										
										//Validate Step 1 is highlighted
										String sClass = Steps.get(0).getAttribute("class");
										if(sClass.contains("current"))
										{
											Report.ValidationPoint(testContext.getName(), "Verify Step 1 is highlighted", "true","true", true);
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Verify Step 1 is highlighted", "true","false", true);
										}
									}
									else
									{
										Report.ValidationPoint(testContext.getName(), "Verify Steps 1- Step 3 are displayed on Right side", "true","false", true);
									}
								}
								
								//Validate 'Select Account to Pay' text.
								//If this is present, it means that there are multiple accounts present. Hence below code for more than 1 accounts.
								if(UI.WaitForObject(oR_MakeAPayment.txtSelectAccountToPay, 1,lDriver))
								{
									List <WebElement> lstAccounts = lDriver.findElements(By.xpath("//div[@class='map-multi-account styled_forms']//ul"));
									if(lstAccounts.size()>0)
									{
										Report.OperationPoint(testContext.getName(), "There are "+lstAccounts.size()+" number of accounts");
										//Validate Due date for the accounts
										List <WebElement> lstElm = lDriver.findElements(By.xpath("//span[@class='label'][text()='Due Date:']"));
										for(int i=0;i<lstElm.size();i++)
										{
											if(lstElm.get(i).getText().contains("Date"))
											{
												Report.ValidationPoint(testContext.getName(), "Validate due date", "true","true", true);
											}
										}
										//Validate Split the payment link
										List <WebElement> lstSplit = lDriver.findElements(By.xpath("//a[contains(@id,'addpmtlink')]"));
										if(lstSplit.size()==lstAccounts.size())
										{
											Report.ValidationPoint(testContext.getName(), "Validate Split the payment link is present for "+lstAccounts.size()+" number of accounts", "true","true", true);
										}
										// Verify Amount, Date text boxes are given and Dropdown to select Payment Method is present.
										Boolean bAmount = UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1,5,lDriver);
										if(bAmount)
										{
											
											//Enter the amount to be paid
											oR_MakeAPayment.edtPaymentAmount1.clear();
											oR_MakeAPayment.edtPaymentAmount1.sendKeys(sPaymentAmount);
											
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Verify payment amount editbox", "true", bAmount.toString(), true);
											return;
										}
										//Validate Date text boxes
										Boolean bDateEdit = UI.WaitForObject(oR_MakeAPayment.edtDate1,5,lDriver);
										Boolean bDateTxt = UI.WaitForObject(oR_MakeAPayment.txtDate,5,lDriver);
										if(bDateEdit)
										{
											Report.ValidationPoint(testContext.getName(), "Verify Date text boxes", "true", bDateEdit.toString(), true);
										}
										else if(bDateTxt)
										{
											Report.ValidationPoint(testContext.getName(), "Verify Date text boxes", "true", bDateTxt.toString(), true);
										}
										
										//validate Payment method
										Boolean bPaymentMethod = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethod,5,lDriver);
										if(!bPaymentMethod)
										{
											Report.ValidationPoint(testContext.getName(), "Verify Payment method", "true", bPaymentMethod.toString(), true);
											return;
										}
									}
								}
								else
								{
									//Validate Account number
									Boolean bAccNo = UI.WaitForObject(oR_MakeAPayment.txtAccount,1,lDriver);
									Boolean bBAN = UI.WaitForObject(oR_MakeAPayment.txtBAN,1,lDriver);
									if(bAccNo.equals(true))
									{
										Report.ValidationPoint(testContext.getName(), "Verify Account number", "true", bAccNo.toString(), true);
									}
									else if(bBAN.equals(true))
									{
										Report.ValidationPoint(testContext.getName(), "Verify BAN number", "true", bBAN.toString(), true);
									}
									/*else
									{
										Report.ValidationPoint(testContext.getName(), "Verify BAN number", "true", bBAN.toString(), true);
									}*/
									//Validate due date
									Boolean bDueDate = UI.WaitForObject(oR_MakeAPayment.txtDueDate,5,lDriver);
									Report.ValidationPoint(testContext.getName(), "Verify due date ", "true", bDueDate.toString(), true);
									//Validate checkbox Enroll in autopay
									Boolean bEnrollInAutpoayCheckbox = UI.WaitForObject(oR_MakeAPayment.chkEnrollInAutopayCheckbox,5);
									if(bEnrollInAutpoayCheckbox.equals(true))
									{
										Report.ValidationPoint(testContext.getName(), "Verify checkbox Enroll in autopay", "true", bEnrollInAutpoayCheckbox.toString(), true);
									}
									//Validate Split the payment link
									Boolean bSplitPayment = UI.WaitForObject(oR_MakeAPayment.lnkSplitThisPayment,5,lDriver);
									if(bSplitPayment.equals(true))
									{
										Report.ValidationPoint(testContext.getName(), "Verify Split the payment link", "true", bSplitPayment.toString(), true);
									}
									
									// Verify Amount, Date text boxes are given and Dropdown to select Payment Method is present.
									Boolean bAmount = UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1,5,lDriver);
									if(bAmount)
									{
										
										//Enter the amount to be paid
										oR_MakeAPayment.edtPaymentAmount.clear();
										oR_MakeAPayment.edtPaymentAmount.sendKeys(sPaymentAmount);
									}
									else
									{
										Report.ValidationPoint(testContext.getName(), "Verify payment amount editbox", "true", bAmount.toString(), true);
										return;
									}
									//Validate Date text boxes
									Boolean bDateEdit = UI.WaitForObject(oR_MakeAPayment.edtDate1,5,lDriver);
									Boolean bDateTxt = UI.WaitForObject(oR_MakeAPayment.txtDate,5,lDriver);
									if(bDateEdit)
									{
										Report.ValidationPoint(testContext.getName(), "Verify Date text boxes", "true", bDateEdit.toString(), true);
									}
									else if(bDateTxt)
									{
										Report.ValidationPoint(testContext.getName(), "Verify Date text boxes", "true", bDateTxt.toString(), true);
									}
									
									//validate Payment method
									Boolean bPaymentMethod = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethod,5,lDriver);
									if(!bPaymentMethod)
									{
										Report.ValidationPoint(testContext.getName(), "Verify Payment method", "true", bPaymentMethod.toString(), true);
										return;
									}
								}
	
								//Select Payment method as New Checking/Saving Account
								Boolean bSelect = UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod,"New checking / savings account",testContext.getName());
								if(bSelect.equals(true))
								{
									//Validate  payment method details popup
									Boolean bPaymentPopup = UI.WaitForObject(oR_MakeAPayment.frmNewCheckingSavingPaymentMethod,50,lDriver);
									//Switch to the popup
									lDriver.switchTo().frame(oR_MakeAPayment.frmNewCheckingSavingPaymentMethod);
									//Enter payment bank accounts details
									{
										Thread.sleep(15000);
										Report.ValidationPoint(testContext.getName(), "Verify payment method details popup", "true", bPaymentPopup.toString(), true);
										Report.OperationPoint(testContext.getName(),"Operational - Name on bank account: "+sNameOnCard);
										oR_MakeAPayment.edtNameOnBankAcc.sendKeys(sNameOnCard);
										Report.OperationPoint(testContext.getName(),"Operational - Routing number: "+sRoutingNo);
										oR_MakeAPayment.edtRoutingNumber.sendKeys(sRoutingNo);
										Report.OperationPoint(testContext.getName(),"Operational - Bank account number: "+sBankAccNo);
										oR_MakeAPayment.edtBankAccountNumber.sendKeys(sBankAccNo);
										oR_MakeAPayment.edtReenterAccNum.sendKeys(sReBankAccNo);
										/*if(UI.WaitForObject(oR_MakeAPayment.chkPaymentFrameSaveMyPaymentInformationCheckbox, 1).equals(true))
										{
											oR_MakeAPayment.chkPaymentFrameSaveMyPaymentInformationCheckbox.click();
										}*/					
										//Click on continue button
										oR_MakeAPayment.btnPaymentFrameContinue.click();
										Thread.sleep(10000);
										lDriver.switchTo().defaultContent();
									}
								
									//click on next button
									if(UI.WaitForObject(oR_MakeAPayment.btnNext, UI.iObjTimeOut,lDriver)){
									     oR_MakeAPayment.btnNext.click();
									}
									Thread.sleep(5000);
									
	//								//Validate popup when Balance is 0.00
	//								if(UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert, 20))
	//								{
	//									oR_MakeAPayment.lnkContinue.click();
	//								}
									//Validate Are you sure? popup
									if(UI.WaitForObject(oR_MakeAPayment.txtAreYouSure, 50,lDriver))
									{
										Thread.sleep(10000);
										Report.ValidationPoint(testContext.getName(), "Verify Are you sure?", "true","true", true);
										oR_MakeAPayment.lnkNo.click();
									}else if(UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert, 20,lDriver)) {
										oR_MakeAPayment.lnkContinue.click();
									}
									
									//Validate Payment Review page
									Boolean bPaymentReview = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,120,lDriver);
									if(bPaymentReview)
									{
										Report.ValidationPoint(testContext.getName(), "Verify Payment Review page", "true", bPaymentReview.toString(), true);
										//Verify the domain of the page
										Boolean bDomain2 = UI.VerifyURLofPage(sUrlDomain,lDriver);
										Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain2.toString(), true);
											
									    	
									  //Check for error message
										if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
										    Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);
										}else{
											Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
										}
										
										//Validate Payment information
										//div[@class='PadLeft12']/div
										List<WebElement> lstPaymentInfo = lDriver.findElements(By.xpath("//div[@class='PadLeft12']/div"));
										String sInfo [] = new String[lstPaymentInfo.size()];
										for(int i=0;i<lstPaymentInfo.size();i++)
										{
											sInfo[i] = lstPaymentInfo.get(i).getText();
										}
										//Retrieving the payment info
										Report.OperationPoint(testContext.getName(),"Operational - Retrieving the payment info");
										for(int i=0;i<lstPaymentInfo.size();i++)
										{
											if(!sInfo[i].isEmpty())
											{
												Report.OperationPoint(testContext.getName(),sInfo[i]);
											}
										}
										//Validate Step 2 is highlighted
										List<WebElement> Steps2 = lDriver.findElements(By.xpath("//div[@class='step-indicator-hor box']//li"));
										if(Steps2.size()>0)
										{
											String sClass2 = Steps2.get(1).getAttribute("class");
											if(sClass2.contains("current"))
											{
												Report.ValidationPoint(testContext.getName(), "Verify Step 2 is highlighted", "true","true", true);
											}
											else
											{
												Report.ValidationPoint(testContext.getName(), "Verify Step 2 is highlighted", "true","false", true);
											}
										}
										
										
										//Click on submit button
										oR_ReviewPaymentDetails.btnSubmit.click();
										
										//Validate Payment Confirmation page is displayed
										Boolean bPaymentConf = UI.WaitForObject(oR_PaymentConfirmation.txtPaymentConfirmationTitle,80,lDriver);
										if(bPaymentConf)
										{
											Report.ValidationPoint(testContext.getName(), "Verify Payment Confirmation page is displayed", "true", bPaymentConf.toString(), true);
											//Verify the domain of the page
											Boolean bDomain3 = UI.VerifyURLofPage(sUrlDomain,lDriver);
											Report.ValidationPoint(testContext.getName(), "Verify the domain of the page : " + lDriver.getCurrentUrl(), "true", bDomain3.toString(), true);
										
																			    
										  //Check for error message
											if(UI.WaitForObject(oR_AccountOverview.errorMsg, 1, lDriver)){
											    Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "false", true);
											}else{
												Report.ValidationPoint(testContext.getName(), "Verify no error message is displayed" , "true", "true", true);
											}
											//Validate Step 3 is highlighted
											List<WebElement> Steps3 = lDriver.findElements(By.xpath("//div[@class='step-indicator-hor box']//li"));
											if(Steps3.size()>0)
											{
												String sClass3 = Steps3.get(2).getAttribute("class");
												if(sClass3.contains("last"))
												{
													Report.ValidationPoint(testContext.getName(), "Verify Step 3 is highlighted", "true","true", true);
												}
												else
												{
													Report.ValidationPoint(testContext.getName(), "Verify Step 3 is highlighted", "true","false", true);
												}
											}
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Verify Payment Confirmation page is displayed", "true", bPaymentConf.toString(), true);
										}
									}
									else
									{
										Report.ValidationPoint(testContext.getName(), "Verify Payment Review page", "true", bPaymentReview.toString(), true);
									}
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Select Payment method as New Checking/Saving Account", "true","Not present", true);
								}
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Verify Make a Payment landing page", "true", bMakePaymentPg.toString(), true);
							}
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Verify Make a Payment button", "true", bMakePayment.toString(), true);
						}
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify make a payment page", "true", "false", true);
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify billing and usage landing page", "true","false", true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Billing & Usage Link from Secondary navigation", "true","false", true);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}

public static void Uverse(ITestContext testContext) throws HeadlessException, IOException, AWTException 
{
	Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
	


	WebDriver lDriver = UI.getDriver(testContext.getName());
	OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
	OR_InternetService oR_InternetService = PageFactory.initElements(lDriver,OR_InternetService.class);

	OR_TelevisionService oR_TelevisionService = PageFactory.initElements(lDriver,OR_TelevisionService.class);
	OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver,OR_BillAndUsage.class);
	OR_HomePhoneService oR_HomePhoneService = PageFactory.initElements(lDriver,OR_HomePhoneService.class);
	OR_Profile oR_Profile = PageFactory.initElements(lDriver,OR_Profile.class);
	JavascriptExecutor jse = (JavascriptExecutor)lDriver;

	try
	{
		//Check for Cards on Dashboard

		Report.ValidationPoint(testContext.getName(), "Card My Uverse TV", "true",UI.WaitForObject(oR_AccountOverview.crdMyUverseTV, 50,lDriver).toString(), true);
		Report.ValidationPoint(testContext.getName(), "Card My Internet", "true",UI.WaitForObject(oR_AccountOverview.crdMyInternet, 50,lDriver).toString(), true);
		Report.ValidationPoint(testContext.getName(), "Card My Digital HomePhone", "true",UI.WaitForObject(oR_AccountOverview.crdMyDigitalHomephone, 50,lDriver).toString(), true);
		//Validate My Uverse TV Card
		Report.OperationPoint(testContext.getName(), "Validate My Uverse TV Card");
		if(UI.WaitForObject(oR_AccountOverview.crdMyUverseTV, 50,lDriver))
		{
			oR_AccountOverview.crdMyUverseTV.click();	
			//Validate cards under My Uverse TV
			jse.executeScript("window.scrollBy(0,250)", "");

			Report.ValidationPoint(testContext.getName(), "Card Watch TV within My Uverse TV", "true",UI.WaitForObject(oR_AccountOverview.crdWatchTV, 50,lDriver).toString(), true);
			Report.ValidationPoint(testContext.getName(), "Card My Plan within My Uverse TV", "true",UI.WaitForObject(oR_AccountOverview.crdMyPlan, 50,lDriver).toString(), true);
			Report.ValidationPoint(testContext.getName(), "Link Start Watching TV Now", "true",UI.WaitForObject(oR_AccountOverview.lnkStartWatchingNow, 50,lDriver).toString(), true);
			Report.ValidationPoint(testContext.getName(), "Link Manage My Plan under My Uverse TV", "true",UI.WaitForObject(oR_AccountOverview.lnkManageMyPlan, 50,lDriver).toString(), true);
			jse.executeScript("window.scrollBy(0,250)", "");
			if(UI.WaitForObject(oR_AccountOverview.lnkManageMyPlan, 50,lDriver))
				{
					//CLick on Manage My Plan Link
					oR_AccountOverview.lnkManageMyPlan.click();
					Report.OperationPoint(testContext.getName(), "Validate My TV Service Page");
					Report.ValidationPoint(testContext.getName(), "Validate User lands on My TV Service page", "true",UI.WaitForObject(oR_TelevisionService.txtMyTVService, 50,lDriver).toString(), true);
					jse.executeScript("window.scrollBy(0,250)", "");
					Report.ValidationPoint(testContext.getName(), "Validate My Programming Section", "true",UI.WaitForObject(oR_TelevisionService.txtMyProgramming, 50,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate My Equipment Section", "true",UI.WaitForObject(oR_TelevisionService.txtMyEquipment, 50,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate Watch TV now button in My Programming Section", "true",UI.WaitForObject(oR_TelevisionService.btnWatchTVNow, 50,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate View recent video orders link in My Programming Section", "true",UI.WaitForObject(oR_TelevisionService.lnkViewRecentVideoOrders, 50,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate My remote controls section in My Equipment Section", "true",UI.WaitForObject(oR_TelevisionService.txtMyRemoteControls, 50,lDriver).toString(), true);
					
					//Navigate To Account Overview 
					UI.NavigateToAccountOverview();
				}
			else Report.ValidationPoint(testContext.getName(), "Validate Manage My Plan Link", "Manage My Plan Link Absent", "False", true);
		
			

		}	
		else Report.ValidationPoint(testContext.getName(), "Validate My Uverse TV Card", "My Uverse TV Card Absent", "False", true);
	
		//Validate My Internet Card
		if(UI.WaitForObject(oR_AccountOverview.crdMyInternet, 50,lDriver))
		{

			oR_AccountOverview.crdMyInternet.click();	
			jse.executeScript("window.scrollBy(0,250)", "");

			//Validate cards under My Internet Card
			Report.ValidationPoint(testContext.getName(), "Card My Usage", "true",UI.WaitForObject(oR_AccountOverview.crdMyUsage, 50,lDriver).toString(), true);
			Report.ValidationPoint(testContext.getName(), "Card My Plan within My Internet", "true",UI.WaitForObject(oR_AccountOverview.crdMyPlan, 50,lDriver).toString(), true);
				if(UI.WaitForObject(oR_AccountOverview.lnkManageMyPlan, 50,lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Link Manage My Plan under My Internet", "true","true", true);
					oR_AccountOverview.lnkManageMyPlan.click();
					//Validate My Internet Service Page
					Report.ValidationPoint(testContext.getName(), "Validate User lands on My Internet Service page", "true",UI.WaitForObject(oR_InternetService.txtMyInternetService, 50,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate Plan Details Section on My Internet Service page", "true",UI.WaitForObject(oR_InternetService.txtPlanDetailsSection, 50,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate My Equipment on My Internet Service page", "true",UI.WaitForObject(oR_InternetService.txtMyEquipment, 50,lDriver).toString(), true);
					//Navigate To Account Overview 
					UI.NavigateToAccountOverview();
				}
				else Report.ValidationPoint(testContext.getName(), "Link Manage My Plan under My Internet", "true","false", true);
				
				//Click on My Internet card and link See All my Usage
				oR_AccountOverview.crdMyInternet.click();	
				if(UI.WaitForObject(oR_AccountOverview.lnkSeeAllMyUsage, 10,lDriver))
				{
					jse.executeScript("window.scrollBy(0,250)", "");

					Report.ValidationPoint(testContext.getName(), "Link See All My Usage under My Internet", "true","true", true);
					oR_AccountOverview.lnkSeeAllMyUsage.click();

					//Validate Billing & Usage Page
					Report.ValidationPoint(testContext.getName(), "Validate User lands on Billing & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 50,lDriver).toString(), true);
					if(UI.WaitForObject(oR_BillAndUsage.tabUsagePreselected, 10))
					{
						Report.ValidationPoint(testContext.getName(), "Validate Usage Tab is preselected", "true","true",true);
					}
					Report.ValidationPoint(testContext.getName(), "Validate Showing on Biiling & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.lstShowing, 20,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate Recent Usage header on Biiling & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.lnkRecentUsageDropdownOption, 5,lDriver).toString(), true);
					if(UI.WaitForObject(oR_BillAndUsage.txtViewUsageForText, 10,lDriver))
					{
						Report.ValidationPoint(testContext.getName(), "Validate View usage for: on Biiling & Usage page", "true","true".toString(), true);
					}
					
					//Navigate To Account Overview 
					UI.NavigateToAccountOverview(); 
					
				}	
				else{			
					jse.executeScript("window.scrollBy(0,250)", "");
					Report.ValidationPoint(testContext.getName(), "Link See All My Usage under My Internet", "true","false", true);}
		}
		else Report.ValidationPoint(testContext.getName(), "Validate My Internet Card", "My Internet Card Absent", "False", true);
		
			
			
		//Validate My Digital Home Phone Card
		if(UI.WaitForObject(oR_AccountOverview.crdMyDigitalHomephone, 10,lDriver))
		{
			oR_AccountOverview.crdMyDigitalHomephone.click();
			jse.executeScript("window.scrollBy(0,250)", "");

			//Validate cards under My Internet Card
			Report.ValidationPoint(testContext.getName(), "Card My Usage under My Digital Home Phone Card", "true",UI.WaitForObject(oR_AccountOverview.crdMyUsage, 10,lDriver).toString(), true);
			Report.ValidationPoint(testContext.getName(), "Card My Plan within My Digital Home Phone Card", "true",UI.WaitForObject(oR_AccountOverview.crdMyPlan, 10,lDriver).toString(), true);
			if(UI.WaitForObject(oR_AccountOverview.lnkManageMyPlan, 10,lDriver))
			{
				Report.ValidationPoint(testContext.getName(), "Link Manage My Plan under My Digital Home Phone", "true","true", true);
				oR_AccountOverview.lnkManageMyPlan.click();
				//Validate My Home Phone Service Page
				Report.ValidationPoint(testContext.getName(), "Validate User lands on My Home Phone Service page", "true",UI.WaitForObject(oR_HomePhoneService.txtHomePhoneHeader, 50,lDriver).toString(), true);
				jse.executeScript("window.scrollBy(0,250)", "");
				Report.ValidationPoint(testContext.getName(), "Validate Plan Details Section on My Home Phone Service page", "true",UI.WaitForObject(oR_HomePhoneService.txtHrPlanDetails, 10,lDriver).toString(), true);
				Report.ValidationPoint(testContext.getName(), "Validate Features section on My Home Phone Service page", "true",UI.WaitForObject(oR_HomePhoneService.txtFeaturesDetails, 10,lDriver).toString(), true);
				Report.ValidationPoint(testContext.getName(), "Validate My Voicemail on My Home Phone Service page", "true",UI.WaitForObject(oR_HomePhoneService.txtMyVoicemail, 10,lDriver).toString(), true);

				//Navigate To Account Overview 
				UI.NavigateToAccountOverview();
			}
			else Report.ValidationPoint(testContext.getName(), "Link Manage My Plan under My Digital Home Phone", "true","false", true);
		
			oR_AccountOverview.crdMyDigitalHomephone.click();	
			if(UI.WaitForObject(oR_AccountOverview.lnkSeeAllMyUsage, 10,lDriver))
			{
				Report.ValidationPoint(testContext.getName(), "Link See All My Usage under My Digital Home Phone", "true","true", true);
				oR_AccountOverview.lnkSeeAllMyUsage.click();
				//Validate Billing & Usage Page
				Report.ValidationPoint(testContext.getName(), "Validate User lands on Billing & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 50,lDriver).toString(), true);
				if(UI.WaitForObject(oR_BillAndUsage.tabUsagePreselected, 10))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Usage Tab is preselected", "true","true",true);
				}
				Report.ValidationPoint(testContext.getName(), "Validate Showing on Biiling & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.lstShowing, 10,lDriver).toString(), true);
				Report.ValidationPoint(testContext.getName(), "Validate Recent Usage header on Biiling & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.lnkRecentUsageDropdownOption, 10,lDriver).toString(), true);
				if(UI.WaitForObject(oR_BillAndUsage.txtViewUsageForText, 10,lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Validate View usage for: on Biiling & Usage page", "true","true".toString(), true);
				}						
			}	
			else
			{
				jse.executeScript("window.scrollBy(0,250)", "");

				Report.ValidationPoint(testContext.getName(), "Link See All My Usage under My Internet", "true","false", true);
			}

		}
		else Report.ValidationPoint(testContext.getName(), "Validate My Digital Home Phone Card", "My Digital Home Phone Absent", "False", true);
			
		//Profile Page Validations
		ValidateProfilePage(testContext);
		//Make A Payment
		MakeAPayment(testContext);

		

		
	}
	catch(Exception e)
	{
		
	}
}

public static void Wireless(ITestContext testContext) throws HeadlessException, IOException, AWTException
{
	WebDriver lDriver = UI.getDriver(testContext.getName());
	OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
	OR_ViewOrChangeRatePlan oR_ViewOrChangeRatePlan = PageFactory.initElements(lDriver,OR_ViewOrChangeRatePlan.class);
	OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver,OR_BillAndUsage.class);
	JavascriptExecutor jse = (JavascriptExecutor)lDriver;

	try
	{
		//Check for Cards on Dashboard
		if(UI.WaitForObject(oR_AccountOverview.crdMyWireless, 30, lDriver))
		{
			Report.ValidationPoint(testContext.getName(), "Card My Wireless", "true","true".toString(), true);
			oR_AccountOverview.crdMyWireless.click();
			//Validate My Wireless Card


			Report.OperationPoint(testContext.getName(), "Validate My Wireless Card");
			jse.executeScript("window.scrollBy(0,250)", "");

			Report.ValidationPoint(testContext.getName(), "Card My Plan within My Wireless Card", "true",UI.WaitForObject(oR_AccountOverview.crdMyPlan, 10,lDriver).toString(), true);
			Report.ValidationPoint(testContext.getName(), "Card My Devices and Features", "true",UI.WaitForObject(oR_AccountOverview.crdMyDevicesAndFeatures, 10,lDriver).toString(), true);
			if(UI.WaitForObject(oR_AccountOverview.lnkManageMyPlan, 10,lDriver))
			{
				Report.ValidationPoint(testContext.getName(), "Link Manage My Plan under My Wireless Card", "true","true", true);
				oR_AccountOverview.lnkManageMyPlan.click();
				
				Report.OperationPoint(testContext.getName(), "Validate Change My Plan Page");
				Report.ValidationPoint(testContext.getName(), "Validate User lands on Change My Plan Page", "true",UI.WaitForObject(oR_ViewOrChangeRatePlan.txtChangeMyPlanRWD, 50,lDriver).toString(), true);
				Report.ValidationPoint(testContext.getName(), "Validate Link See Plan Details", "true",UI.WaitForObject(oR_ViewOrChangeRatePlan.lnkSeePlanDetails, 10,lDriver).toString(), true);
				UI.NavigateToAccountOverview();
			}
			else Report.ValidationPoint(testContext.getName(), "Link Manage My Plan under My Wireless Card", "true","false", true);
			
			//Navigate To Usage Page
			Report.OperationPoint(testContext.getName(), "Navigate to Usage Page");
			try
			{
				/*oR_AccountOverview.img3BarMenu.click();
				oR_AccountOverview.lnkMyWirelessSecNav.click();
				oR_AccountOverview.lnkCheckUsage.click();*/
				
				oR_AccountOverview.crdMyWireless.click();	
				jse.executeScript("window.scrollBy(0,250)", "");

				if(UI.WaitForObject(oR_AccountOverview.lnkSeeAllMyUsage, 10,lDriver))
				{
				Report.ValidationPoint(testContext.getName(), "Link See All My Usage under My Wireless", "true","true", true);
					oR_AccountOverview.lnkSeeAllMyUsage.click();
					//Validate Billing & Usage Page
					Report.ValidationPoint(testContext.getName(), "Validate User lands on Billing & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 50,lDriver).toString(), true);
					if(UI.WaitForObject(oR_BillAndUsage.tabUsagePreselected, 10))
					{
						Report.ValidationPoint(testContext.getName(), "Validate Usage Tab is preselected", "true","true",true);
					}
					Report.ValidationPoint(testContext.getName(), "Validate Showing on Biiling & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.lstShowing, 10,lDriver).toString(), true);
//					Report.ValidationPoint(testContext.getName(), "Validate Select A Plan Biiling & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.txtSelectAPlan, 50,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate Usage Options Biiling & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.txtUsageOptions, 5,lDriver).toString(), true);

					if(UI.WaitForObject(oR_BillAndUsage.btnRecentUse, 5,lDriver))
					{
						Report.ValidationPoint(testContext.getName(), "Validate Recent use on Biiling & Usage page", "true","true".toString(), true);
						oR_BillAndUsage.btnRecentUse.click();
						
						Report.ValidationPoint(testContext.getName(), "Validate Current bill in Showing Dropdown on Biiling & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.lnkCurrentBill, 50,lDriver).toString(), true);
							
					}
					//Navigate To Account Overview 
					UI.NavigateToAccountOverview(); 
					
				}	
				else {
					jse.executeScript("window.scrollBy(0,250)", "");
					Report.ValidationPoint(testContext.getName(), "Link See All My Usage under My Internet", "true","false", true);
				}
				
			}
			catch(Exception e)
			{	
				Report.ValidationPoint(testContext.getName(), "Navigate to Billing And Usage Page", "Failed", "Failed", true);
			}
			
		}
		else Report.ValidationPoint(testContext.getName(), "Card My Wireless", "true","false", true);

		//Profile Page Validations
		ValidateProfilePage(testContext);
		//Make A Payment
		MakeAPayment(testContext);
	}
	catch(Exception e)
	{
		Report.ValidationPoint(testContext.getName(), "Uverse SA Flow", "Failed", e.getMessage().toString(), true);
		e.printStackTrace();
	}
}
	public static void ValidateProfilePage(ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		Report.OperationPoint(testContext.getName(), "Validating PROFILE PAGE");
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
		OR_Profile oR_Profile = PageFactory.initElements(lDriver,OR_Profile.class);

	//Navigate to Profile Page
			if(UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav, 10))
			{
				oR_AccountOverview.lnkProfileSecondaryNav.click();
				if(UI.WaitForObject(oR_AccountOverview.lnkViewProfile, 10))
				{
					Report.ValidationPoint(testContext.getName(), "Link View Profile under Profile in Global Navigation", "true","true", true);
					oR_AccountOverview.lnkViewProfile.click();
					Report.ValidationPoint(testContext.getName(), "Validate User is taken to Profile Page", "true",UI.WaitForObject(oR_Profile.txtMyProfileTitle, 50,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate Link Popular Tasks on Profile Page", "true",UI.WaitForObject(oR_Profile.lnkPopularTasks, 10,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate Link Account users on Profile Page", "true",UI.WaitForObject(oR_Profile.lnkAccountUsers, 5,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate Link Contact Info on Profile Page", "true",UI.WaitForObject(oR_Profile.lnkContactInfo, 5,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate Link Login Info on Profile Page", "true",UI.WaitForObject(oR_Profile.lnkLoginInfo, 5,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate Link Billing & Payments option on Profile Page", "true",UI.WaitForObject(oR_Profile.lnkBillingAndPaymentOptions, 5,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate Link Communication Preferences on Profile Page", "true",UI.WaitForObject(oR_Profile.lnkCommunicationPreferences, 5,lDriver).toString(), true);
				}
				else Report.ValidationPoint(testContext.getName(), "Link View Profile under Profile in Global Navigation", "true","false", true);
					
			}
			else Report.ValidationPoint(testContext.getName(), "Link Profile in Global Navigation", "true","false", true);	
	}
	
	public static void MakeAPayment(ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		Report.OperationPoint(testContext.getName(), "Validating MAKE A PAYMENT flow");
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
		OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver,OR_MakeAPayment.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sAmount = IO.GetParamVal(sTestParams, "PAYMENT_AMOUNT");
		String sRoutingNum = IO.GetParamVal(sTestParams, "ROUTING_NO");
		String sBankAccNum = IO.GetParamVal(sTestParams, "BANK_ACC_NO");
		
		//Navigate to Make A Payment Page
		try
		{
			oR_AccountOverview.img3BarMenu.click();
			oR_AccountOverview.lnkBillingAndPaymentSecNav.click();
			oR_AccountOverview.lnkMakeAPaymentTertNav.click();
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Failed to Navigate to Make A Payment Page", "true","false", true);

		}
		//Make A Payment
		if(UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 60))
		{
			Report.ValidationPoint(testContext.getName(), "Validate User is taken to Make A Payment Page", "true","true", true);
			Report.ValidationPoint(testContext.getName(), "Validate Step bar 1. Enter Payment Information on Make a Payment Page", "true",UI.WaitForObject(oR_MakeAPayment.tabEnterPaymentInformation, 20,lDriver).toString(), true);
			Report.ValidationPoint(testContext.getName(), "Validate Step bar 2. Review Payment on Make a Payment Page", "true",UI.WaitForObject(oR_MakeAPayment.tabReviewPayment, 5,lDriver).toString(), true);
			Report.ValidationPoint(testContext.getName(), "Validate Step bar 3. Payment status Page", "true",UI.WaitForObject(oR_MakeAPayment.tabPaymentStatus, 5,lDriver).toString(), true);
			//Enter Payment Details
			Report.OperationPoint(testContext.getName(), "Enter Payment Information");
			try
			{
				oR_MakeAPayment.edtPaymentAmount.clear();
				oR_MakeAPayment.edtPaymentAmount.sendKeys(sAmount);
				if(UI.WaitForObject(oR_MakeAPayment.edtDate, 10))
				{
					oR_MakeAPayment.edtDate.click();
					oR_MakeAPayment.txtCurrentDatecalender.click();
				}
				oR_MakeAPayment.lstPaymentMethodRWD.click();
				oR_MakeAPayment.txtCheckingSaving.click();
				oR_MakeAPayment.edtRoutingNumber.sendKeys(sRoutingNum);
				oR_MakeAPayment.edtBankAccountNumber.sendKeys(sBankAccNum);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Failed to enter Payment info", "true", "false", true);
			}
			
			//Payment Review
			try{
				oR_MakeAPayment.btnContinue.click();
				if(UI.WaitForObject(oR_MakeAPayment.btnNo,3))
						oR_MakeAPayment.btnNo.click();
				if(UI.WaitForObject(oR_MakeAPayment.btnOk, 3))
					oR_MakeAPayment.btnOk.click();
				Report.TestPoint(testContext.getName(), "Validate Step 2.Review Payment is current step on Make a Payment Page", "true",UI.WaitForObject(oR_MakeAPayment.txtReviewPaymentCurrentStep, 50,lDriver).toString(), true);
				oR_MakeAPayment.btnSubmit.click();
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Failed to continue with Payment Review", "true", "false", true);
			}
			
			//Payment Confirmation
			Report.TestPoint(testContext.getName(), "Validate Payment Confirmation Page", "true",UI.WaitForObject(oR_MakeAPayment.txtPaymentConfirmation, 50,lDriver).toString(), true);

			Report.TestPoint(testContext.getName(), "Validate Payment is done Successfully", "true",UI.WaitForObject(oR_MakeAPayment.txtPaymentStatusCompleted, 50,lDriver).toString(), true);

			
		}
		else
		{
			Report.ValidationPoint(testContext.getName(), "Failed to open Make A Payment page", "true", "false", true);

		}
	}

	public static void Wireline(ITestContext testContext) throws HeadlessException, IOException, AWTException 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver,OR_AccountOverview.class);
		OR_ViewOrChangeRatePlan oR_ViewOrChangeRatePlan = PageFactory.initElements(lDriver,OR_ViewOrChangeRatePlan.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver,OR_BillAndUsage.class);
		OR_HomePhoneService oR_HomePhoneService = PageFactory.initElements(lDriver,OR_HomePhoneService.class);
		try
		{
			//Check for Cards on Dashboard
			if(UI.WaitForObject(oR_AccountOverview.crdMyHomePhone, 30))
			{
				Report.ValidationPoint(testContext.getName(), "Card My HomePhone", "true","true".toString(), true);
				oR_AccountOverview.crdMyHomePhone.click();
				
				//Validate My HomePhone Card
				Report.OperationPoint(testContext.getName(), "Validate My HomePhone Card");
				Report.ValidationPoint(testContext.getName(), "Card My Plan within My HomePhone Card", "true",UI.WaitForObject(oR_AccountOverview.crdMyPlan, 10,lDriver).toString(), true);
				if(UI.WaitForObject(oR_AccountOverview.lnkManageMyPlan, 10,lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Link Manage My Plan under My HomePhone Card", "true","true", true);
					oR_AccountOverview.lnkManageMyPlan.click();
					
					Report.OperationPoint(testContext.getName(), "Validate Change My Plan Page");
					Report.ValidationPoint(testContext.getName(), "Validate User lands on Change My Plan Page", "true",UI.WaitForObject(oR_ViewOrChangeRatePlan.txtChangeMyPlanRWD, 50,lDriver).toString(), true);
					Report.ValidationPoint(testContext.getName(), "Validate BTN is Displayed", "true",UI.WaitForObject(oR_HomePhoneService.txtBTN, 20,lDriver).toString(), true);
					if(UI.WaitForObject(oR_HomePhoneService.txtPhoneNumber, 10,lDriver))
					Report.ValidationPoint(testContext.getName(), "Validate Phone Number is Displayed", "true","true", true);
					if(UI.WaitForObject(oR_HomePhoneService.txtHomePhoneDetails_1511, 20,lDriver))
					Report.ValidationPoint(testContext.getName(), "Validate Home Phone Details section", "true","true", true);
					
				}
				else Report.ValidationPoint(testContext.getName(), "Link Manage My Plan under My HomePhone Card", "true","false", true);
			}
			else  Report.ValidationPoint(testContext.getName(), "Card My Home Phone", "true","false", true);
			
			//Usage Page
			Report.OperationPoint(testContext.getName(), "Navigate to Usage Page");
			try
			{	
				try{
					oR_AccountOverview.img3BarMenu.click();
					oR_AccountOverview.lnkBillingAndPaymentSecNav.click();
					oR_AccountOverview.lnkViewMyBill.click();
					Report.OperationPoint(testContext.getName(), "Validate Usage Page");
				 }
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Failed to Navigate to Usage page", "true","false", true);

				}
				
				//Validate Billing & Usage Page
				Report.ValidationPoint(testContext.getName(), "Validate User lands on Billing & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 50,lDriver).toString(), true);
				//Validate Billing & Usage Page
				Report.ValidationPoint(testContext.getName(), "Validate User lands on Billing & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 50,lDriver).toString(), true);
				if(UI.WaitForObject(oR_BillAndUsage.lnkUsage,10))
				{
					Report.ValidationPoint(testContext.getName(), "Click on Usage tab", "true","true", true);
					oR_BillAndUsage.lnkUsage.click();
				}
				else 				
					Report.ValidationPoint(testContext.getName(), "Click on Usage tab", "true","false", true);
				Thread.sleep(2000);
				if(UI.WaitForObject(oR_BillAndUsage.tabUsagePreselected, 10))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Usage Tab is preselected", "true","true",true);
				}
				Report.ValidationPoint(testContext.getName(), "Validate Showing on Biiling & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.lstShowing, 10,lDriver).toString(), true);
				if(UI.WaitForObject(oR_BillAndUsage.txtDefaultUsageBillSelection, 20))
				{
					oR_BillAndUsage.txtDefaultUsageBillSelection.click();
					Report.ValidationPoint(testContext.getName(), "Validate Recent Usage  on Biiling & Usage page", "true",UI.WaitForObject(oR_BillAndUsage.lnkRecentUsageDropdownOption, 50,lDriver).toString(), true);
				}
				if(UI.WaitForObject(oR_BillAndUsage.txtViewUsageForText, 10,lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Validate View usage for: on Biiling & Usage page", "true","true".toString(), true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Navigate to Usage Page", "Failed", "False", true);
			}
			
			//Profile Page Validations
			ValidateProfilePage(testContext);
			Thread.sleep(8000);
			//Make A Payment
			MakeAPayment(testContext);
		
	}catch(Exception e)
	{
		Report.ValidationPoint(testContext.getName(), "Wireline Flow", "Failed", e.getMessage().toString(), true);
		e.printStackTrace();
	}
	}
}
