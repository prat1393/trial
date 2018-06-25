/**************************************************************
   Library 				- LaunchAndLogout 
   Description 			- Includes all functions/methods related to Launch, login and logout
   Date created 		- 18-Dec-14
   Developed by			- Rahul/Deepak
   Last Modified By 	-
   Last Modified Date	-
 ***************************************************************/

package com.SupportingFiles;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;

import com.SupportingFiles.ReadEnvPropFile;
import com.SupportingFiles.UI;
import com.AppSpecificLibrary.Dashboard;
import com.AppSpecificLibrary.Profile;
import com.OR.MyATT.*;
import com.OR.MyATTZone.OR_AgentActivity;
import com.OR.MyATTZone.OR_Dashboard;
import com.OR.MyATTZone.OR_Logon;
import com.OR.MyATTZone.OR_MobileShareComparisionTool;
import com.OR.MyATTZone.OR_MyATTZone;
import com.OR.MyATTZone.OR_ZoneLogin;

public class LaunchAndLogout {

	// Global variable declaration
	public static String sloginTypeValue = "LoginType";
	public static String sloginID = "LoginID";
	public static String sPassword = "Password";
	public static String sURL, sNode;
//	public static String sCurrTCName;
	public static String sToBeExeOn;
//	public static String sUserID, sPwd,sLoginType;

//	public static Integer iCurrIter = 0;

	public static Report report;
	public static UI ui;
	
	public static boolean blnWantURLInSS;
	
	//static Hashtable<String, String> sTestParams = new Hashtable<String, String>();

	/*
	public static com.OR.MyATT.OR_AccountOverview oR_AccountOverview;
	public static com.OR.MyATT.OR_ATT oR_ATT;
	public static com.OR.MyATT.OR_BillAndUsage oR_BillAndUsage;
	public static com.OR.MyATT.OR_Login oR_Login;
	public static com.OR.MyATT.OR_Profile oR_Profile;
	public static com.OR.MyATT.OR_MakeAPayment oR_MakeAPayment;
	public static com.OR.MyATT.OR_MessageCenter oR_MessageCenter;
	public static com.OR.MyATT.OR_AlertDetails oR_AlertDetails;
	public static com.OR.MyATT.OR_HomePhoneService oR_HomePhoneService;
	public static com.OR.MyATT.OR_ForgotUserID oR_ForgotUserID;
	public static com.OR.MyATT.OR_PaperlessBilling oR_PaperlessBilling;
	public static com.OR.MyATT.OR_TelevisionService oR_TelevisionService;
	public static com.OR.MyATT.OR_InternetService oR_InternetService;
	public static com.OR.MyATT.OR_LinkAnAccount oR_LinkAnAccount;
	public static com.OR.MyATT.OR_SuspendReactivateService oR_SuspendReactivateService;	
	public static com.OR.MyATT.OR_ViewOrChangeRatePlan oR_ViewOrChangeRatePlan;
	public static com.OR.MyATT.OR_AutoPayEnrollment oR_AutoPayEnrollment;
	public static com.OR.MyATT.OR_PaymentConfirmation oR_PaymentConfirmation;
	public static com.OR.MyATT.OR_ReviewPaymentDetails oR_ReviewPaymentDetails;
	public static com.OR.MyATT.OR_AccountInformation oR_AccountInformation;
	public static com.OR.MyATT.OR_EditPaymentProfile oR_EditPaymentProfile;
	public static com.OR.MyATT.OR_ChatWindow oR_ChatWindow;
	public static com.OR.MyATT.OR_PhoneAndDevices oR_PhoneAndDevices;
	public static com.OR.MyATT.OR_MyWirelessPlan oR_MyWirelessPlan;
	public static com.OR.MyATT.OR_ATTSupportCenter oR_ATTSupportCenter;
	public static com.OR.MyATT.OR_AccountRegistration oR_AccountRegistration;
	public static com.OR.MyATT.OR_PayOffInstallmentPlan oR_PayOffInstallmentPlan;
	public static com.OR.MyATT.OR_ATTAccessIDVerifyAccInfo oR_ATTAccessIDVerifyAccInfo;
	public static com.OR.MyATT.OR_RequestAccessConfirmation oR_RequestAccessConfirmation;
	public static com.OR.MyATT.OR_MyAccountAccess oR_MyAccountAccess;
	public static com.OR.MyATT.OR_Shop oR_Shop;
	public static com.OR.MyATT.OR_ManageAutoPay oR_ManageAutoPay;
	public static com.OR.MyATT.OR_UpgradePhone oR_UpgradePhone;
	public static com.OR.MyATT.OR_ATTFeatureDetails oR_ATTFeatureDetails;
	public static com.OR.MyATT.OR_WirelessAddonReview oR_WirelessAddonReview;
	public static com.OR.MyATT.OR_WirelessAddonConfirmation oR_WirelessAddonConfirmation;
	public static com.OR.MyATT.OR_ManagePlentiCard oR_ManagePlentiCard;
	public static com.OR.MyATT.OR_ATT_ManageMyDeviceFeatures oR_ATT_ManageMyDeviceFeatures;
	public static com.OR.MyATT.OR_ATT_AccessID_Profile oR_ATT_AccessID_Profile;
	public static com.OR.MyATT.OR_AddaDevice oR_AddaDevice;
	public static com.OR.MyATT.OR_ResetVoicemailPassword oR_ResetVoicemailPassword;
	public static com.OR.MyATT.OR_EditATTAccessIDInformation oR_EditATTAccessIDInformation;
	public static com.OR.MyATT.OR_EditBillingContactInformation oR_EditBillingContactInformation;
	public static com.OR.MyATT.OR_BillingSettingsNotice oR_BillingSettingsNotice;
	public static com.OR.MyATT.OR_EditPaymentInformation oR_EditPaymentInformation;
	public static com.OR.MyATT.OR_PlentiLogin oR_PlentiLogin;
	
	
	// Objects declaration of MyATTZone
	public static com.OR.MyATTZone.OR_ZoneLogin oR_ZoneLogin;
	public static com.OR.MyATTZone.OR_Logon oR_Logon;
	public static com.OR.MyATTZone.OR_Dashboard oR_Dashboard;
	public static com.OR.MyATTZone.OR_MobileShareComparisionTool oR_MobileShareComparisionTool;
	public static com.OR.MyATTZone.OR_AgentActivity oR_AgentActivity;
	
	public static com.OR.MyATT.OR_SMBBillDetails oR_SMBBillDetails;
	*/

	public static ReadEnvPropFile oReadEnvProp;
	
	/**************************************************************
	 * Method Name - LaunchMyATTZoneApplication 
	 * Description - LaunchMyATTZoneApplication is used to launch and login to MyATT Zone application
	 * Date created - 24-Dec-2014
	 * Developed by - Rahul Bakde 
	 * Last Modified By - Rahul Bakde
	 * Last Modified Date - 2-Feb-2015
	 ***************************************************************/
	@Parameters({"Environment"})
	public static void LaunchMyATTZoneApplication(String sEnv, String sBrowser, String sIter,
			final ITestContext testContext, String sTcName) throws Exception {

		//Local variable declaration
				String sMyATTEnvURL, sCurrTCName;
				WebDriver driver = null;
				Hashtable<String, String> sTestParams = null;
	
		//		String sMyATTZoneEnvURL;

		
		ui=new UI(testContext);

		try {
			sCurrTCName = sTcName;
			//			sTcName1 = sTcName;

			// creating object of ReadEnvProFile
			oReadEnvProp = new ReadEnvPropFile();

			UI.iObjTimeOut = Integer.parseInt(oReadEnvProp.ObjTimeOut());

			// retrieving test data file path from env.properties file
			IO.sFolder = oReadEnvProp.TestDataFile(); 

			IO.Setup();

			// retrieving executed by
			Report.exeBy = oReadEnvProp.ExecutedBy(); 															

			sMyATTEnvURL = oReadEnvProp.MyATTEnvURL();
			//			sMyATTZoneEnvURL = oReadEnvProp.MyATTZoneEnvURL();

			/***************************************
			 * 
			 *  Start - Environment Settings Area
			 *  
			 ***************************************/

			Integer iCurrIter = Integer.valueOf(sIter);
			String sTestName = testContext.getName();
			if (sBrowser.equalsIgnoreCase("IE")) {
//				DesiredCapabilities oCap = DesiredCapabilities.chrome();
//				oCap.setBrowserName("ie");
//				driver = new RemoteWebDriver(new URL(
//						"http://localhost:5558/wd/hub"), oCap);
				
				
				DesiredCapabilities oCap = DesiredCapabilities.internetExplorer(); 
				//System.setProperty("webdriver.ie.driver", "C:\\OSD_Test_Automation\\MyATT_Data_SystemX_Automation\\IO\\Utilities\\IEDriverServer.exe"); 
				oCap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true); 
				//oCap.setBrowserName("ie"); 
				driver = new RemoteWebDriver(new URL( 
						"http://localhost:5558/wd/hub"), oCap);
			} else if (sBrowser.equalsIgnoreCase("Firefox")) {
				DesiredCapabilities oCap = DesiredCapabilities.firefox();
				oCap.setBrowserName("firefox");
				driver = new RemoteWebDriver(new URL(
						"http://localhost:5555/wd/hub"), oCap);
			} else if (sBrowser.equalsIgnoreCase("Chrome")) {
				DesiredCapabilities oCap = DesiredCapabilities.chrome();
				oCap.setBrowserName("chrome");
				driver = new RemoteWebDriver(new URL(
						"http://localhost:5559/wd/hub"), oCap);
			}
			else if (sBrowser.equalsIgnoreCase("Safari")) {
				DesiredCapabilities oCap = DesiredCapabilities.safari();
				oCap.setBrowserName("safari");
				driver = new RemoteWebDriver(new URL(
						"http://localhost:5556/wd/hub"), oCap);
			}
			UI.driver=driver;

			//Maximizing the browser window
			driver.manage().window().maximize();
			sTestParams = IO.Read_Iter_Data(sEnv, sCurrTCName, iCurrIter);
			IO.putTestData(sTestName, sTestParams);
			
		//	Report.Start(sCurrTCName);

			
			driver.get(sMyATTEnvURL);

			UI.driver = driver;
			UI.putDriver(sTestName, driver);
			
			
			
			//OR declaration			
			OR_ZoneLogin oR_ZoneLogin = PageFactory.initElements(driver,
					OR_ZoneLogin.class);
			OR_Logon oR_Logon = PageFactory.initElements(driver,
					OR_Logon.class);
			OR_Dashboard oR_Dashboard = PageFactory.initElements(driver,
					OR_Dashboard.class);
			OR_MobileShareComparisionTool oR_MobileShareComparisionTool = PageFactory.initElements(driver,
					OR_MobileShareComparisionTool.class);
			OR_AgentActivity oR_AgentActivity = PageFactory.initElements(driver,
					OR_AgentActivity.class);


			

			Report.PrintIteration(sTestName, sBrowser, iCurrIter.toString());
			//Declaring local variable
			Boolean bAttUid, bPassword, bOk, bOkLogon, bATTAccessID, bATTAccessIDTxtField, bMyATTWeb;
			String  sDataType ;

			// validating MyATTZone login page
			bAttUid = UI.WaitForObject(oR_ZoneLogin.attuid,UI.iObjTimeOut,driver);
			bPassword = UI.WaitForObject(oR_ZoneLogin.password, 1, driver);
			bOk = UI.WaitForObject(oR_ZoneLogin.ok, 1, driver);

			if (bAttUid && bPassword && bOk) {
				oR_ZoneLogin.attuid.sendKeys(oReadEnvProp.AttuidMyATTZone());
				oR_ZoneLogin.password.sendKeys(oReadEnvProp.PasswordMyATTZone());
				Thread.sleep(1000);
				Report.TestPoint(testContext.getName(), "Validate MyATTZone login page", "True", "True", true);
				oR_ZoneLogin.ok.click();
			} else {
				Report.TestPoint(testContext.getName(),
						"Validate MyATTZone login page", "True", "False",
						true);
			}

			// validating zone logon page
			bOkLogon = UI.WaitForObject(oR_Logon.ok, UI.iObjTimeOut, driver);
			if (bOkLogon) {
				Report.TestPoint(testContext.getName(), "Validate MyATTZone logon page", "True", "True", true);
				oR_Logon.ok.click();
			} else {
				Report.TestPoint(testContext.getName(), "Validate MyATTZone logon page", "True", "False", true);
			}

			// Validating zone dashboard page
			bATTAccessID = UI.WaitForObject(oR_Dashboard.elm_ATTAccessID, UI.iObjTimeOut, driver);
			if (bATTAccessID) {
				Report.TestPoint(testContext.getName(), "Validate zone dashboard page", "True", bATTAccessID.toString(), true);

				//			//Retrieving data type parameter from Test Data excel sheet
				//			sDataType = IO.GetParamVal(sTestParams, "DataType", UI.iCurrIter);
				//			
				//			if (sDataType.equalsIgnoreCase("slid")) {
				//
				//				// click on AT&TAccessID tab
				//				oR_MyATTZoneDashboard.elm_ATTAccessID.click();
				//
				//				bATTAccessIDTxtField = UI.WaitForObject(oR_MyATTZoneDashboard.elm_ATTAccessIDTxtField, UI.iObjTimeOut);
				//				if (bATTAccessIDTxtField) {
				//					Report.TestPoint(testContext.getName(),	"Validate ATT Access ID text field is displayed on MyATTZone dashboard page", "True",
				//							bATTAccessIDTxtField.toString(), true);
				//					oR_MyATTZoneDashboard.elm_ATTAccessIDTxtField.sendKeys(sUserID);
				//					oR_MyATTZoneDashboard.btn_Search.click();
				//
				//					bMyATTWeb = UI.WaitForObject(oR_MyATTZoneDashboard.myATTWeb, UI.iObjTimeOut);
				//					if (bMyATTWeb) {
				//						Report.TestPoint(testContext.getName(),
				//								"Validate Mirror MyATTWeb is displayed on MyATTZone dashboard page",
				//								"True", bMyATTWeb.toString(),
				//								true);
				//						oR_MyATTZoneDashboard.myATTWeb.click();
				//
				//						// code to handle security pop up
				//						Alert oAlert1 = driver.switchTo().alert();
				//						oAlert1.accept();
				//						driver.switchTo().defaultContent();
				//					} else {
				//						Report
				//						.TestPoint(
				//								testContext.getName(),
				//								"Validate Mirror MyATTWeb is displayed on MyATTZone dashboard page",
				//								"True", bMyATTWeb.toString(),
				//								true);
				//					}
				//				} else {
				//					Report.TestPoint(
				//							testContext.getName(),
				//							"Validate ATT Access ID text field is displayed on MyATTZone dashboard page",
				//							"True", bATTAccessIDTxtField.toString(), true);
				//				}
				//
				//			} else if (sDataType.equalsIgnoreCase("wireless")) {
				//
				//			} else if (sDataType.equalsIgnoreCase("uverse")) {
				//
				//			} else if (sDataType.equalsIgnoreCase("wireline")) {
				//
				//			}
			} else {
				Report.TestPoint(testContext.getName(),
						"Validate zone dashboard page", "True", "False",
						true);
			}
		}catch (Exception e) {
			String sErrorMsg = e.getMessage();
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Validate MyATT application is launched", "MyATT application should launched successfully", "Failed to launch MyATT application", true);
		}
	}
	
	
	@Parameters({"Environment"})
	public static Hashtable<String, String> LaunchApplicationZone(String sEnv, String sBrowser, String sIter,
			final ITestContext testContext, String sTcName) throws Exception {

		//Local variable declaration
		String sMyATTZoneEnvURL, sCurrTCName;
		WebDriver driver = null;
		Hashtable<String, String> sTestParams = null;
		Report.testContext_Map.put(testContext.getName(), testContext);
		
		ui=new UI(testContext);

		sCurrTCName = sTcName;

		// creating object of ReadEnvProFile
		oReadEnvProp = new ReadEnvPropFile();
		UI.iObjTimeOut = Integer.parseInt(oReadEnvProp.ObjTimeOut());
		// retrieving test data file path from env.properties file
		IO.sFolder = oReadEnvProp.TestDataFile(); 

		IO.Setup();
		// retrieving executed by
		Report.exeBy = oReadEnvProp.ExecutedBy(); 	
		
		sMyATTZoneEnvURL = oReadEnvProp.MyATTZoneEnvURL();
		Integer iCurrIter = Integer.valueOf(sIter);
		String sTestName = testContext.getName();

		/***************************************
		 * 
		 *  Start - Environment Settings Area
		 *  
		 ***************************************/
		try {
			if (sBrowser.equalsIgnoreCase("IE")) {
				DesiredCapabilities oCap = DesiredCapabilities.internetExplorer();
				oCap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				oCap.setBrowserName("ie");
				driver = new RemoteWebDriver(new URL(
						"http://localhost:5558/wd/hub"), oCap);
			} else if (sBrowser.equalsIgnoreCase("Firefox")) {
				DesiredCapabilities oCap = DesiredCapabilities.firefox();
				oCap.setBrowserName("firefox");
				driver = new RemoteWebDriver(new URL(
						"http://localhost:5555/wd/hub"), oCap);
			} else if (sBrowser.equalsIgnoreCase("Chrome")) {
				DesiredCapabilities oCap = DesiredCapabilities.chrome();
				oCap.setBrowserName("chrome");
				driver = new RemoteWebDriver(new URL(
						"http://localhost:5559/wd/hub"), oCap);
			}
			else if (sBrowser.equalsIgnoreCase("Safari")) {
				DesiredCapabilities oCap = DesiredCapabilities.safari();
				oCap.setBrowserName("safari");
				driver = new RemoteWebDriver(new URL(
						"http://localhost:5556/wd/hub"), oCap);
			}
			UI.driver=driver;
			driver.manage().timeouts().implicitlyWait(UI.iObjTimeOut, TimeUnit.SECONDS);
			
			//Maximizing the browser window
			driver.manage().window().maximize();
			driver.get(sMyATTZoneEnvURL);

			sTestParams = IO.Read_Iter_Data(sEnv, sCurrTCName, iCurrIter);
			IO.putTestData(sTestName, sTestParams);
			
			Report.Start(sCurrTCName);
			System.out.println(sMyATTZoneEnvURL);  
			
			UI.driver = driver;
			UI.putDriver(sTestName, driver);

			/***************************************
			 * 
			 *  End - Environment Settings Area
			 *  
			 ***************************************/
			Report.PrintIteration(sTestName, sBrowser, iCurrIter.toString());
			
		

		} catch (Exception e) {
			e.printStackTrace();
			Report.TestPoint(sTestName, "Validate myATT-Zone application is launched", "True", "False", true);
		}
		return sTestParams;
	}

	public static void LoginApplicationZone(final ITestContext testContext) throws HeadlessException,
	IOException, AWTException, InterruptedException, ParseException {
		
		String sTestName = testContext.getName();
		try {
			
			String sAttUIDMyATTZone = oReadEnvProp.AttuidMyATTZone();
			String sPwdMyATTZone = oReadEnvProp.PasswordMyATTZone();
			
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			WebDriver lDriver = UI.getDriver(testContext.getName());
			
			OR_MyATTZone OR_MyATTZone = PageFactory.initElements(lDriver, OR_MyATTZone.class);
			
			OR_MyATTZone.Login_txtATTUID.sendKeys(sAttUIDMyATTZone);
			OR_MyATTZone.Login_txtPassword.sendKeys(sPwdMyATTZone);
			OR_MyATTZone.Login_btnOK.click();

			UI.WaitForObject(OR_MyATTZone.LogOn_lblSuccessMessage, UI.iObjTimeOut, lDriver);
			Report.TestPoint(sTestName, "Validate the Log On Successful page", "AT&T - Log On Successful", lDriver.getTitle(), true);
			OR_MyATTZone.LogOn_btnOK.click();
			Report.OperationPoint(sTestName, "Click on OK button");
			//lDriver.get(oReadEnvProp.MyATTZoneEnvURL());
			//System.out.println("reenetered url");
			Thread.sleep(10000);
			
		} catch (Exception e) {
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Validate user successfully logged in to MyATT", "User should be able to login to MyATT", "User failed to login to MyATT application", true);
		}
	}
	

	/**************************************************************
	 * Method Name - LaunchApplication 
	 * Description - LaunchApplication method has the logic to 
	 * 				 decide which browser needs to be open and 
	 * 				 then it navigates to the URL specified in 
	 * 				 the property file. 
	 * Date created - 24-Dec-2014
	 * Developed by - Rahul Bakde 
	 * Last Modified By - Rahul Bakde
	 * Last Modified Date - 2-Feb-2015
	 ***************************************************************/
	@Parameters({"Environment"})
	public static Hashtable<String, String> LaunchApplication(String sEnv, String sBrowser, String sIter,
			final ITestContext testContext, String sTcName) throws Exception {

		//Local variable declaration
		String sMyATTEnvURL, sCurrTCName;
		WebDriver driver = null;
		Hashtable<String, String> sTestParams = null;
		Report.testContext_Map.put(testContext.getName(), testContext);
		ui=new UI(testContext);

		try {
			sCurrTCName = sTcName;

			// creating object of ReadEnvProFile
			oReadEnvProp = new ReadEnvPropFile();

			UI.iObjTimeOut = Integer.parseInt(oReadEnvProp.ObjTimeOut());

			// retrieving test data file path from env.properties file
			IO.sFolder = oReadEnvProp.TestDataFile(); 

			IO.Setup();

			// retrieving executed by
			Report.exeBy = oReadEnvProp.ExecutedBy(); 															

			Report.sMyATTEnvURL = oReadEnvProp.MyATTEnvURL();

			/***************************************
			 * 
			 *  Start - Environment Settings Area
			 *  
			 ***************************************/

			Integer iCurrIter = Integer.valueOf(sIter);
			String sTestName = testContext.getName();
			if (sBrowser.equalsIgnoreCase("IE")) {
				DesiredCapabilities oCap = DesiredCapabilities.internetExplorer();
				oCap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				oCap.setBrowserName("ie");
				driver = new RemoteWebDriver(new URL("http://localhost:5558/wd/hub"), oCap);
			} else if (sBrowser.equalsIgnoreCase("Firefox")) {
				DesiredCapabilities oCap = DesiredCapabilities.firefox();
				oCap.setBrowserName("firefox");
				System.setProperty("webdriver.gecko.driver", "C:\\OSD_Test_Automation\\MyATT_Web_Automation_BVT\\IO\\Utilities\\geckodriver.exe");
				driver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), oCap);
			} else if (sBrowser.equalsIgnoreCase("Chrome")) {
				DesiredCapabilities oCap = DesiredCapabilities.chrome();
				oCap.setBrowserName("chrome");
				System.setProperty("webdriver.chrome.driver", "C:\\OSD_Test_Automation\\MyATT_Web_Automation_BVT\\IO\\Utilities\\chromedriver.exe");
				driver = new RemoteWebDriver(new URL(
						"http://localhost:5559/wd/hub"), oCap);
			}
			else if (sBrowser.equalsIgnoreCase("Safari")) {
				DesiredCapabilities oCap = DesiredCapabilities.safari();
				oCap.setBrowserName("safari");
				driver = new RemoteWebDriver(new URL(
						"http://localhost:5556/wd/hub"), oCap);
			}
			UI.driver=driver;

			//Maximizing the browser window
			driver.manage().window().maximize();

			
			sTestParams = IO.Read_Iter_Data(sEnv, sCurrTCName, iCurrIter);
			IO.putTestData(sTestName, sTestParams);
			
			Report.Start(sCurrTCName);
					
			driver.get(Report.sMyATTEnvURL);

			UI.driver = driver;
			UI.putDriver(sTestName, driver);
			OR_Login oR_Login = PageFactory.initElements(driver, OR_Login.class);
			OR_PlentiLogin oR_PlentiLogin = PageFactory.initElements(driver, OR_PlentiLogin.class);

			/***************************************
			 * 
			 *  End - Environment Settings Area
			 *  
			 ***************************************/
			Report.PrintIteration(sTestName, sBrowser, iCurrIter.toString());
			if (UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, UI.iObjTimeOut, driver)) {
				Report.OperationPoint(testContext.getName(), "Operational - Launch MyATT application");
				Report.OperationPoint(testContext.getName(), "Launch URL : " + Report.sMyATTEnvURL);
				Report
				.TestPoint(
						testContext.getName(),
						"Validate MyATT application is launched and login page is dispalyed",
						"True", "True", true);
			} 
			else if (UI.WaitForObject(oR_Login.txtSMBAccountManager, UI.iObjTimeOut, driver))
			{
				Report.OperationPoint(testContext.getName(), "Operational - Launch MyATT application");
				Report.OperationPoint(testContext.getName(), "Launch URL : " + Report.sMyATTEnvURL);
				Report
				.TestPoint(
						testContext.getName(),
						"Validate MyATT application is launched and login page is dispalyed",
						"True", "True", true);
			}
			else if(UI.WaitForObject(oR_PlentiLogin.txtLoginToBegin, UI.iObjTimeOut, driver))
			{
				Report.OperationPoint(testContext.getName(), "Operational - Launch MyATT application");
				Report.OperationPoint(testContext.getName(), "Launch URL : " + Report.sMyATTEnvURL);
				Report
				.TestPoint(
						testContext.getName(),
						"Validate MyATT application is launched and Plenti login page is dispalyed",
						"True", "True", true);
			}
			else {
				Report
				.TestPoint(
						testContext.getName(),
						"Validate MyATT application is launched and login page is dispalyed",
						"True", "Failed to launch MyATT application", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Validate MyATT application is launched", "MyATT application should launched successfully", "Failed to launch MyATT application", true);
		}
		return sTestParams;
	}

	/**************************************************************
	 * Method Name - LoginApplication() 
	 * Description - This method is used to login into the myATT 
	 * 				 application. 
	 * Date created - 18-Dec-2014 
	 * Developed by - Rahul Bakde 
	 * Last Modified By - Rahul Bakde 
	 * Last Modified Date - 7-Jan-2015
	 * 
	 * @throws AWTException
	 * @throws IOException
	 * @throws HeadlessException
	 * @throws InterruptedException
	 * @throws ParseException 
	 ***************************************************************/
	public static void LoginApplication(final ITestContext testContext) throws HeadlessException,
	IOException, AWTException, InterruptedException, ParseException {
		WebDriver lDriver = UI.getDriver(testContext.getName());	

		try {
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
//			WebDriver lDriver = UI.getDriver(testContext.getName());	
			OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
			OR_LinkAnAccount oR_LinkAnAccount = PageFactory.initElements(lDriver, OR_LinkAnAccount.class);
			
			
			
			String sUserID = IO.GetParamVal(sTestParams, sloginID);
			String sPwd = IO.GetParamVal(sTestParams, sPassword);
			//System.out.println(sUserID);
			//UI.printMsg(testContext.getName().split("-")[0] + "::" + testContext.getName().split("-")[2] + "::" + "User Name - " + sUserID);
			
			
			
			
			// Validating whether userid input field exist
			Thread.sleep(6000);
			String sUID = UI.CheckExist(oR_Login.edtUserId);
			String SUIDReal = UI.CheckExist(oR_Login.edtUserIdReal);
			if (sUID.equalsIgnoreCase("true")) {
				Report.TestPoint(testContext.getName(),
						"Validate loginID field is present", "True",
						sUID, true);

				oR_Login.edtUserId.clear();
				Thread.sleep(2000);
				oR_Login.edtUserId.sendKeys(sUserID);
			} 
			else if(SUIDReal.equalsIgnoreCase("true"))
			{
				Report.TestPoint(testContext.getName(),
						"Validate loginID field is present", "True",
						SUIDReal, true);
				oR_Login.edtUserIdReal.clear();
				Thread.sleep(2000);
				oR_Login.edtUserIdReal.sendKeys(sUserID);
				Thread.sleep(2000);
			}

			// Validating whether password input field exist
			String sPassword = UI.CheckExist(oR_Login.edtPassword);
			String sPassword1 = UI.CheckExist(oR_Login.edtRePassword);
			
			if (sPassword.equalsIgnoreCase("true")) {
				Thread.sleep(2000);
				oR_Login.edtPassword.sendKeys(sPwd);
				Report.TestPoint(testContext.getName(),
						"Validate password input field exist ", "True",
						sPassword, true);
				Thread.sleep(3000);
			} else if(sPassword1.equalsIgnoreCase("true")) {
				Thread.sleep(2000);
				oR_Login.edtRePassword.sendKeys(sPwd);
				Report.TestPoint(testContext.getName(),
						"Validate password input field exist ", "True",
						sPassword1, true);
				Thread.sleep(3000);
			}
			else
			{
				Report.TestPoint(testContext.getName(),
						"Validate password input field exist ", "True",
						sPassword1, true);
				Thread.sleep(3000);
			}

			// Validating whether login input field exist
			String sLogin = UI.CheckExist(oR_Login.btnLogin);
			if (sLogin.equalsIgnoreCase("true")) {
				
				Report.OperationPoint(testContext.getName(), "Logging in with user " + sUserID );
				oR_Login.btnLogin.click();
				Thread.sleep(60000);
			} else {
				Report.TestPoint(testContext.getName(),
						"Validate login button exist", "True",
						sLogin, false);
				Thread.sleep(5000);
			}
			
			//Added by Clint(26th Nov 2015) : To view the new URL after redirecting from Login
			String sRedirectURL = lDriver.getCurrentUrl();	
			Report.OperationPoint(testContext.getName(), "Launch URL after entering UserID and Password is redirected to new URL : "+sRedirectURL);
			
			
			/****************************************
				Code for Validation of login process frame - saWireline
				Developed By - Swagata Das
				Date Created - 11th Mar 2015
			 ****************************************/
			/*if(testContext.getName().equals("BVTwirelineSA"))
			{
				if(UI.WaitForObject(oR_Login.frmLogInProgress, 10))
				{
					lDriver.switchTo().frame(oR_Login.frmLogInProgress);
					//Validate Login in progress image
					Boolean bATT = UI.WaitForObject(oR_Login.imgATTLogo, 5);
					Report.ValidationPoint(testContext.getName(), "Validate Login in progress image", "true", bATT.toString(), true);

					lDriver.switchTo().defaultContent();
				}
			}*/

	
			
			/*************************************
			 * Check for login Failure 
			 *************************************/
			
			if(!UI.WaitForObject(oR_Login.btnLogin, 10, lDriver)){
				
				/*************************************
				 * Validates Reminder: Page which appears immediately after Login page
				 *************************************/
				if(UI.CheckExist(oR_Login.txtMakeAPasswordReset).equalsIgnoreCase("True")){
					Report.ValidationPoint(testContext.getName(), 
							"Validate Make password reset easier page is displayed", "true", String.valueOf(oR_Login.txtMakeAPasswordReset.isDisplayed()), true);
					
					Report.OperationPoint(testContext.getName(), "Click on Remind me later link");
					oR_Login.lnkRemindMeLater.click();
				}
				
				if(UI.CheckExist(oR_Login.txtReminder).equalsIgnoreCase("True"))
				{
					if(UI.WaitForObject(oR_Login.edtPasswordInReminderPage, 15, lDriver))
					{
						oR_Login.edtPasswordInReminderPage.sendKeys("test1ng");
						Report.ValidationPoint(testContext.getName(), 
						"Validate 'Reminder: You created a new password' page is displayed", "true", String.valueOf(oR_Login.edtPasswordInReminderPage.isDisplayed()), true);
						
						//clicking on login button
						oR_Login.btnLoginOnReminderPage.click();
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), 
								"Validate 'Reminder: You created a new password' page is displayed", "true", String.valueOf(oR_Login.edtPasswordInReminderPage.isDisplayed()), true);
					}
				}
				
				/*************************************
				 * Validates Confirm Pass code page and enters the value of pass code 
				 * Modified By: Gautham
				 * Date: 30th June 2015
				 *************************************/
				if(UI.CheckExist(oR_Login.txtConfirmYourPasscode).equalsIgnoreCase("True"))
				{
					Report.ValidationPoint(testContext.getName(),
							"Validate Confirm Passcode page is displayed", "true",
							String.valueOf(oR_Login.txtConfirmYourPasscode.isDisplayed()), true);
					String sPasscode = IO.GetParamVal(sTestParams, "Passcode"); 
					boolean bDontKnowPasscode = UI.WaitForObject(oR_Login.txtIDontKnowThePasscode, 60, lDriver);
					if(oR_Login.edtPassocde.isDisplayed())
							{
								if(testContext.getName().contains("BVTwirelessSLflow"))
								{
									oR_Login.chkManageThisAccountLater.click();
								}
								else if(bDontKnowPasscode==false)
								{
									if(sPasscode != null)
									{
										oR_Login.edtPassocde.sendKeys(sPasscode);
									}
									else
									{
										oR_Login.edtPassocde.sendKeys("1234");
									}
								}
								else if(bDontKnowPasscode)
								{
									oR_Login.chkManageThisAccountLater.click();	
								}
								
							}
						else
						{
							Report.TestPoint(testContext.getName(),
								"Validate edit box for passcode exist", "true",
								String.valueOf(oR_Login.edtPassocde.isDisplayed()), true);
						}
						if(UI.WaitForObject(oR_Login.btnContinueOnPasscodePage, 2, lDriver))
						{
							oR_Login.btnContinueOnPasscodePage.click();
						}
						else
						{
							oR_Login.btnNextOnPasscodePage.click();
						}
						Report.OperationPoint(testContext.getName(), "Confirming Passcode");
					
				}

			/****************************************
			 * 
			 *  Start - validating interstitial pages
			 *  
			 ****************************************/
				
			/*************************************
			* Code for Test case USG05121
			* Developed By - Hiral Jogi
			* Date Created - 15th Dec 2015 
		    **************************************//*
				if(testContext.getName().contains("USG05121")){
				//if(String.valueOf(testContext.getName()).equals("USG05121")){
					OR_UpgradePhone oR_UpgradePhone = PageFactory.initElements(lDriver, OR_UpgradePhone.class);	
					Report.ValidationPoint(testContext.getName(),"Validate interstitial page.", "True","True", true);
					boolean bCheckNow = UI.WaitForObject(oR_UpgradePhone.btnCheckNow, 60, lDriver);
					if(bCheckNow){
						Report.ValidationPoint(testContext.getName(),"Clicking on 'Check Now' button for upgrade.", "True","True", true);
						oR_UpgradePhone.btnCheckNow.click();
					}
					
					// Validating if user is navigated to Upgrade Phone page
					boolean bUpgradeYourDevice = UI.WaitForObject(oR_UpgradePhone.txtUpgradeYourDevice, 60, lDriver);
					if(bUpgradeYourDevice){
						Report.ValidationPoint(testContext.getName(),"User has navigated to upgrade phone page sucessfully..", "True","True", true);
					}
					
					// navigating back to Overview Page
					boolean bContinuetoMyATTAccMgt = UI.WaitForObject(oR_UpgradePhone.lnkContinuetoMyATTAccMgt, 60, lDriver);
					if(bContinuetoMyATTAccMgt){
						Report.ValidationPoint(testContext.getName(),"Navigating back to Overview Page..", "True","True", true);
						oR_UpgradePhone.lnkContinuetoMyATTAccMgt.click();
					}
					
				}*/
				
			//Validate No Thanks, Continue....
			if(UI.WaitForObject(oR_Login.lnkNoThanksContinue, UI.iObjTimeOut, lDriver).equals(true)){
				Report.ValidationPoint(testContext.getName(),
						"Validate interstitial page exist", "True",
						"True", true);
			
				
/*				*//*************************************
				 * Code for Test case SER06554_02
				 * Developed By - Nachiket Pawar
				 * Date Created - 26th June 2015 
		    	 **************************************//*
				if(String.valueOf(testContext.getName()).equals("SER06554_02")){
					OR_UpgradePhone oR_UpgradePhone = PageFactory.initElements(lDriver, OR_UpgradePhone.class);	
					oR_Login.btnCheckNowOnUpgradeEligibility.click();
					if(UI.WaitForObject(oR_UpgradePhone.txtUpgradeYourDevice, UI.iObjTimeOut))
					{
						if(!UI.WaitForObject(oR_UpgradePhone.txtEmerald,UI.iObjTimeOut)){
							Report.ValidationPoint(testContext.getName(), "Validate Emerald eligible message is not displayed", "Emerald eligible message is not displayed", "Emerald eligible message is not displayed", true);
						}else{
							Report.ValidationPoint(testContext.getName(), "Validate Emerald eligible message is not displayed", "Emerald eligible message is not displayed", "Emerald eligible message is displayed", true);
						}
					}
				}
*/				oR_Login.lnkNoThanksContinue.click();
			}
			
/*			*//*************************************
			 * Code for Test case PRF11624
			 * Developed By - Clint John
			 * Date Created - 4th Nov 2015 
	    	 **************************************//*
			if(testContext.getName().contains("PRF11624"))
			{
				try
				{
					 Call to application specific function 
					Profile.VerifyPRF11624(testContext);
				}
				catch (Exception e) 
				{
					Report.TestPoint(testContext.getName(),
							"Some error has occured for TC: PRF11624", "True",
							e.getMessage(), true);
				}
			}*/

		
			//validating 'No Thanks' button on 'One Time AT&T U-verse Account Validation' page
			if(UI.WaitForObject(oR_Login.btnNoThanksOnAccountValidationPage, 2, lDriver))
			{
				
				oR_Login.btnNoThanksOnAccountValidationPage.click();
			}

			//validatate interstitial page for create your ATT access ID
			Boolean lnkContinue = UI.WaitForObject(oR_Login.lnkContinue,1,lDriver);
			if(lnkContinue.equals(true))
			{
				oR_Login.lnkContinue.click();
			}

			// validating interstitial pages for email verification

			//validating Email adddress
			Boolean bPriEmail= UI.WaitForObject(oR_Login.edtEmailAddress,1, lDriver);

			if(bPriEmail.equals(true)){
			if(oR_Login.edtEmailAddress.getAttribute("value").isEmpty()){
				   oR_Login.edtEmailAddress.sendKeys("myatt.testing@att.com");
				}
			else
			{
				String email = oR_Login.edtEmailAddress.getAttribute("value");
				oR_Login.edtConfirmEmailAddress.sendKeys(email);
			}
			}

			//validating confirm email adddress
			Boolean bConfrmEmail= UI.WaitForObject(oR_Login.edtConfirmEmailAddress,1, lDriver);
			
			if(bConfrmEmail.equals(true)){
				if(oR_Login.edtConfirmEmailAddress.getAttribute("value").isEmpty()){
				   oR_Login.edtConfirmEmailAddress.sendKeys("myatt.testing@att.com");
				}
			}
			

			//validating firstname
			if (UI.WaitForObject(oR_Login.edtFirstName, 1, lDriver)) {
				if(oR_Login.edtFirstName.getAttribute("value").isEmpty()){
				   oR_Login.edtFirstName.sendKeys("Rambo");
				}
				
			}

			//validating Lastname
			if (UI.WaitForObject(oR_Login.edtLastName, 1, lDriver)) {
				if(oR_Login.edtLastName.getAttribute("value").isEmpty()){
				   oR_Login.edtLastName.sendKeys("Singh");
				}
			}

			//validating No radio button
			Boolean bWirelessNum= UI.WaitForObject(oR_Login.radNo,1, lDriver);
			if (bWirelessNum.equals(true)) {
				oR_Login.radNo.click();
			}
			
			//Validating Security ques
			Boolean bSecurity = UI.WaitForObject(oR_Login.txtSecurityQ, 1, lDriver);
			if(bSecurity.equals(true))
			{
				//for security q/a 1
				UI.SelectOptionFromDropDown(oR_Login.lstSecurityQ1, "What is your favorite film?");
				oR_Login.edtSecurityA1.clear();
				oR_Login.edtSecurityA1.sendKeys("Titanic");
				//for security q/a 2
				UI.SelectOptionFromDropDown(oR_Login.lstSecurityQ2, "What country would you like to visit?");
				oR_Login.edtSecurityA2.clear();
				oR_Login.edtSecurityA2.sendKeys("India");
			}
	
			//validating continue button
			if (UI.WaitForObject(oR_Login.btnContinueToAccount, 1, lDriver)) {
				oR_Login.btnContinueToAccount.click();
			}

			//Validating Remind me later
			if(UI.WaitForObject(oR_Login.lnkRemindMeLater, 10, lDriver))
			{
				oR_Login.lnkRemindMeLater.click();
			}

			if (UI.WaitForObject(oR_Login.btnNoThanks, 1, lDriver).equals(true)) {
				Report.ValidationPoint(testContext.getName(),
						"Validate interstitial page exist", "True",
						"True", true);
				oR_Login.btnNoThanks.click();
			}
			/*if(!testContext.getName().contains("DBD08060"))
			{
			if (UI.WaitForObject(oR_Login.lnkNoThanks, 1).equals(true)) {
				Report.ValidationPoint(testContext.getName(),
						"Validate interstitial page exist", "True",
						"True", true);
				oR_Login.lnkNoThanks.click();
			}
			
			//Validating Remind me later - Getting 3 interstitial pages for PRF17322 - Monica 17th Dec 2015
			if(UI.WaitForObject(oR_Login.lnkRemindMeLater, 1))
			{
				oR_Login.lnkRemindMeLater.click();
			}
			}*/
			/*************************************
			 * Code for validating Important information page
			 * Developed By - Clint John
			 * Date Created - 21st September 2015
	    	 **************************************/
			if (UI.WaitForObject(oR_Login.txtImportantInformation, 1,lDriver).equals(true)) {
				Report.ValidationPoint(testContext.getName(),
						"Validate interstitial page exist", "True",
						"True", true);
				oR_Login.btnCancel.click();
			}
			
			/*************************************
			 * Code for validating Introducing Premier page
			 * Developed By - Clint John
			 * Date Created - 21st September 2015
	    	 **************************************/
			if (UI.WaitForObject(oR_Login.txtIntroducingPremier, 1, lDriver).equals(true)) {
				Report.ValidationPoint(testContext.getName(),
						"Validate interstitial page exist", "True",
						"True", true);
				oR_Login.btnContinueToAccount.click();
			}
			
			//validating continue button
			if (UI.WaitForObject(oR_Login.btnContinueToAccount, 1,lDriver)) {
				oR_Login.btnContinueToAccount.click();
			}

			/****************************************
			 * 
			 *  End - validating interstitial pages
			 *  
			 ****************************************/
			
			/****************************************
			 * code for Validating the Promotional offer popup for DBD13719 and DBD13720
			 * Created by: Sneha Pansare
			 * Date: 05-Nov-2015
			 ****************************************/
			/*if(testContext.getName().contains("DBD13719") || testContext.getName().contains("DBD13720"))
			{
				Dashboard.verifyPromotionalOfferPopupIsSuppressedForWirelessHomePhone(testContext);
				return;
			}
			if(testContext.getName().contains("DBD21668"))
			{
				Dashboard.verifyPromotionalOfferPopupDetailsForWirelessHomePhone(testContext);
				return;
			}
			
			if(testContext.getName().contains("DBD05161"))
				
			{
				try{
				lDriver.findElement(By.xpath("//a[contains(text(),'Close')]")).click();
				}catch(Exception e)
				{
					System.out.println("Close link is not present after logging in into Target URL");
				}
			}*/
			
			//Code Modified for Dashboard whose title is ATT instead of Account Overview - Monica 11th Mar 2015
			if (UI.WaitForObject(oR_AccountOverview.txtWelcome, UI.iObjTimeOut,lDriver)
					.equals(true)|| UI.WaitForObject(oR_ATT.txtAccountOverview, 1, lDriver).equals(true)) {
				Report.OperationPoint(testContext.getName(), "Operational - Navigating to Account Overview page");
				Report.TestPoint(testContext.getName(),
						"Validate Overview page is displayed", "True",
						"True", true);

				/****************************************
				 * code for Handling the paperless billing pop-up
				 * Created by: Swagata Das
				 * Date: 10-Feb-2015
				 ****************************************/
				if(testContext.getName().contains("DBD08060")){
					if(UI.WaitForObject(oR_AccountOverview.frmPaperlessBilling, 20, lDriver))
					{
						lDriver.switchTo().frame(oR_AccountOverview.frmPaperlessBilling);
						Report.ValidationPoint(testContext.getName(), "Validate paperless billing frame exists", "true", "true", true);
						
						//valuidate text on interstitial page
						boolean b10= UI.WaitForObject(lDriver.findElement(By.xpath("//strong[contains(text(),'$10')]")), 10, lDriver);
						if(b10)
						{
							Report.ValidationPoint(testContext.getName(), "Validate $10 Visa Reward Card offer present on  Paperless Billing Offer Interstitial page", "true", String.valueOf(b10), true);
						}
						
						//
						boolean bterms= UI.WaitForObject(lDriver.findElement(By.xpath("//strong[contains(text(),'Terms and Conditions')]")), 10, lDriver);
						if(b10)
						{
							Report.ValidationPoint(testContext.getName(), "Validate terms and conditions are present on  Paperless Billing Offer Interstitial page", "true", String.valueOf(bterms), true);
						}
						
						//
						boolean bNo= UI.WaitForObject(lDriver.findElement(By.id("rejectPBO")), 10, lDriver);
						boolean bRemind= UI.WaitForObject(lDriver.findElement(By.id("remindMeLater")), 10, lDriver);
						boolean bEnroll= UI.WaitForObject(lDriver.findElement(By.id("selectPBO")), 10, lDriver);
						if(bNo && bRemind && bEnroll)
						{
							Report.ValidationPoint(testContext.getName(), "Validate required links are present on  Paperless Billing Offer Interstitial page", "true", String.valueOf(bNo && bRemind && bEnroll), true);
						}
						lDriver.findElement(By.id("remindMeLater")).click();
						lDriver.switchTo().defaultContent();
						
					}
				}
				else{
				if (UI.WaitForObject(oR_AccountOverview.frmPaperlessBilling,20,lDriver).equals(true))
				{

					lDriver.switchTo().frame(oR_AccountOverview.frmPaperlessBilling);
					Report.OperationPoint(testContext.getName(), "Operational - Clicking No, Thanks for paperless billing popup");
					lDriver.findElement(By.id("rejectPBO")).click();
					//					Code modified to switch back from frame  to Dashboard - Monica, 13th Feb 2015
					lDriver.switchTo().defaultContent(); 
				}
			} 
			}
			else if(UI.WaitForObject(oR_LinkAnAccount.txtAccountOverview, 1, lDriver))
			{
				//Code modified to handle Account overview page for a customer linking his account - Clint, 20th Feb 2015
				Report.TestPoint(testContext.getName(),"Validate Overview page is displayed","true","true", true);
			
			}else if(UI.WaitForObject(oR_AccountOverview.txtWelComeBack,UI.iObjTimeOut, lDriver)){
				Report.TestPoint(testContext.getName(),"Validate Overview page is displayed","true","true", true);
			}
			else
			{
				Report.TestPoint(testContext.getName(),
						"Validate Overview page is displayed", "Account overview page should displayed",
						"Account overview page is not displayed", true);
			}

		 }else{
		     throw new Exception();
	  	 }
		
		//Added by Clint(23rd Nov 2015) : To view the new URL after redirecting from Login
		String sDashboardURL = lDriver.getCurrentUrl();	
		Report.OperationPoint(testContext.getName(), "URL on navigating to Dashboard : "+sDashboardURL);
		
		 }catch (Exception e) {
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Validate user successfully logged in to MyATT", "User should be able to login to MyATT", "User failed to login to MyATT application", true);
			//Added by Clint(23rd Nov 2015) : To view the new URL after redirecting from Login
			String sDashboardURL = lDriver.getCurrentUrl();	
			Report.OperationPoint(testContext.getName(), "URL on navigating to Dashboard : "+sDashboardURL);
		}
	}

	/**************************************************************
	 * Method Name - LogoutApplication() 
	 * Description - This method is used to logout from the myATT 
	 * 				 application 
	 * Date created- 18-Dec-2014 
	 * Developed by- Rahul Bakde 
	 * Last Modified By - Rahul Bakde  
	 * 					Modified by Sneha Pansare - added new logout link for SMB env
	 * Last Modified Date - 8-Jan-2015
	 * Last Modified By - Krutika Kamdi -Incomplete Payment modal handled
	 * Last Modified Date - 29th JUly
	 ***************************************************************/
	public static void LogoutApplication(final ITestContext testContext,
			String sTcName) throws Exception {

		// verify logout button
		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());	
			OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);

			if (UI.WaitForObject(oR_AccountOverview.btnLogout, 5, lDriver).equals(true)) {
				Report.OperationPoint(testContext.getName(), "Operational - Logging out");
				Report.TestPoint(testContext.getName(),
						"Validate logout button is displayed", "True",
						"True", true);
				oR_AccountOverview.btnLogout.click();
				//incomplete payment
				if (UI.WaitForObject(oR_MakeAPayment.frmIncompPay, 3, lDriver)) {
					Report.TestPoint(testContext.getName(),"Validate interstitial modal window exist",
							"True", "True", true);
					//switching control to the frame
					lDriver.switchTo().frame(oR_MakeAPayment.frmIncompPay);
					WebElement btnCont = lDriver.findElement(By.xpath("//a[contains(text(),'Continue')]"));
					btnCont.click();
				}	
					
				if (UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, 3, lDriver)) {
					Report.TestPoint(testContext.getName(),
							"Validate user successfully logged out and MyATT login page is dispalyed",
							"True", "True", true);
				}
			}
			else if(UI.WaitForObject(oR_ATT.lnkSMBLogout, 5, lDriver).equals(true))
			{

				Report.OperationPoint(testContext.getName(), "Operational - Logging out");
				Report.TestPoint(testContext.getName(),
						"Validate logout button is displayed", "True",
						"True", true);
				oR_ATT.lnkSMBLogout.click();
				if (UI.WaitForObject(oR_Login.txtSMBAccountManager, UI.iObjTimeOut, lDriver)) {
					Report
					.TestPoint(
							testContext.getName(),
							"Validate user successfully logged out and MyATT login page is dispalyed",
							"True", "True", true);


				}
				else
				{
					Report
					.TestPoint(
							testContext.getName(),
							"Validate user successfully logged out and MyATT login page is dispalyed",
							"User should be able to logout and MyATT login page should be displayed", "User is not logged of from MyATT application", true);
				}

			}
			else 
			{
				Report.TestPoint(testContext.getName(),
						"Validate logout button is displayed", "Logout button should be displayed",
						"Logout button is not displayed", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), "Validate successfully logged out",
					"True", sErrMsg, true);
		}
	}

	/**************************************************************
	 * Method Name - closeApplication() 
	 * Description - This method is used to close myATT application
	 * Date created - 18-Dec-2014 
	 * Developed by - Rahul Bakde
	 * Last Modified By - Rahul Bakde
	 * Last Modified Date - 7-Jan-2015 
	 * 
	 * @throws AWTException
	 * @throws IOException
	 * @throws HeadlessException
	 * @throws InterruptedException
	 ***************************************************************/
	public static void CloseApplication(final ITestContext testContext) throws HeadlessException,
	IOException, AWTException, InterruptedException {
		WebDriver lDriver = UI.getDriver(testContext.getName()); 
		try {
			Report.OperationPoint(testContext.getName(), "Operational - Closing browser");
			Report.CloseIteration(testContext.getName());
			Report.Consolidation(testContext.getName());
			lDriver.quit();
			 
		} catch (Exception e) {
			e.printStackTrace();
			String sErrMsg = e.getMessage();
			Report.OperationPoint(testContext.getName(), "Operational - Browser is not closed \n"+sErrMsg);
		} 
	 
	}

	/**************************************************************
	 * Method Name - closeApplication() (Overloaded)
	 * Description - This method is used to close myATT application
	 * Date created - 18-Dec-2014 
	 * Developed by - Rahul Bakde
	 * Last Modified By - Nachiket Pawar
	 * Last Modified Date - 15-Jun-2015 
	 * 
	 * @throws AWTException
	 * @throws IOException
	 * @throws HeadlessException
	 * @throws InterruptedException
	 ***************************************************************/
	
	public static void CloseApplication(String testCaseName) throws HeadlessException,
	IOException, AWTException, InterruptedException {
		WebDriver lDriver = UI.getDriver(testCaseName);

			try {
				Report.OperationPoint(testCaseName, "Operational - Closing browser");
				Report.CloseIteration(testCaseName);
				Report.Consolidation(testCaseName);
		    	  if(((RemoteWebDriver)lDriver).getSessionId() != null)
					{
			     			lDriver.quit();
					}
				} catch (Exception e) {
				e.printStackTrace();
				String sErrMsg = e.getMessage();
				Report.OperationPoint(testCaseName, "Operational - Browser is not closed \n"+sErrMsg);
			} 
	}
	/**************************************************************
	 * Method Name - UpdateTestResultInQC() 
	 * Description - This method is used to updated test case 
	 * 				 status in Quality Center 
	 * Date created - 27-Jan-2015 
	 * Developed by - Rahul Bakde
	 * Last Modified By - Rahul Bakde
	 * Last Modified Date - 7-Jan-2015 
	 * 
	 * @throws AWTException
	 * @throws IOException
	 * @throws HeadlessException
	 * @throws InterruptedException
	 * @throws ParseException 
	 ***************************************************************/
	public static void UpdateTestResultInQC(final ITestContext testContext) throws HeadlessException,
	IOException, AWTException, InterruptedException, ParseException {
		try {
			String sQcFlag = oReadEnvProp.UpdateTCStatusInQC();

			// condition to check whether qcFlag is true or false
			if (sQcFlag.equalsIgnoreCase("True")) {
				// method call to update test status in QC
			}
		} catch (Exception e) {
			Report.TestPoint(testContext.getName(), "Error message", "True", e.getMessage(),
					false);
		}
	}


	/**************************************************************
	 * Method Name - LoginApplicationSMB()
	 * Description - This method is used to login into the SMB real
	 * application.
	 * Date created - 12-Mar-2015
	 * Developed by - Rahul Bakde
	 * Last Modified By - Sneha Pansare /Merrin Mathai
	 * Last Modified Date - 12-Mar-2015/19-Mar-2015
	 *
	 * @throws AWTException
	 * @throws IOException
	 * @throws HeadlessException
	 * @throws InterruptedException
	 * @throws ParseException
	 ***************************************************************/
	public static void LoginApplicationSMB(final ITestContext testContext) throws HeadlessException,
	IOException, AWTException, InterruptedException, ParseException {
		try {

			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
			OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);

			String sLoginType = IO.GetParamVal(sTestParams, sloginTypeValue);
			String sUserID = IO.GetParamVal(sTestParams, sloginID);
			String sPwd = IO.GetParamVal(sTestParams, sPassword);
			String sAccountType = IO.GetParamVal(sTestParams, "Account_Type");

			//Validating Login Type Select field is present
			String sLType=UI.CheckExist(oR_Login.lstSelectSMBAccountType);
			if (sLType.equalsIgnoreCase("true")) {
				Report.TestPoint(testContext.getName(),
						"Validate select Account Type field is present", "True",
						sLType, true);

				Boolean bSelect=UI.SelectOptionFromDropDown(oR_Login.lstSelectSMBAccountType,sAccountType);
				Report.TestPoint(testContext.getName(),
						"Validate select Account Type field is selected", "true",
						String.valueOf(bSelect), true);
			}
			else
			{
				Report.TestPoint(testContext.getName(),
						"Validate select Account Type field is present", "True",
						sLType, true);

			}



			// Validating whether userid input field exist
			String sUID = UI.CheckExist(oR_Login.edtSMBuserId);
			if (sUID.equalsIgnoreCase("true")) {
				Report.TestPoint(testContext.getName(),
						"Validate loginID field is present", "True",
						sUID, true);

				oR_Login.edtSMBuserId.sendKeys(sUserID);
			}
			else
			{
				Report.TestPoint(testContext.getName(),
						"Validate loginID field is present", "True",
						sUID, true);

			}


			// Validating whether password input field exist
			String sPassword = UI.CheckExist(oR_Login.edtSMBpassword);
			if (sPassword.equalsIgnoreCase("true")) {
				oR_Login.edtSMBpassword.sendKeys(sPwd);
				Report.TestPoint(testContext.getName(),
						"Validate password input field exist ", "True",
						sPassword, true);
			} else {
				Report.TestPoint(testContext.getName(),
						"Validate password input field exist ", "True",
						sPassword, true);

			}

			// Validating whether login input field exist
			String sLogin = UI.CheckExist(oR_Login.btnSMBlogin);

			if (sLogin.equalsIgnoreCase("true")) {
				
				Report.OperationPoint(testContext.getName(), "Logging in with user " + sUserID );
				oR_Login.btnSMBlogin.click();
			} else {
				Report.TestPoint(testContext.getName(),
						"Validate login button exist", "True",
						sLogin, false);
			}

			Boolean bPrimaryEmailId = UI.WaitForObject(oR_Login.edtPrimaryEmailAdd, 10,lDriver);

			if(bPrimaryEmailId.equals(true))
			{
				String sPrimaryEmailAdd = IO.GetParamVal(sTestParams, "Primary_Email");

				oR_Login.edtPrimaryEmailAdd.sendKeys(sPrimaryEmailAdd);
				Report.ValidationPoint(testContext.getName(), "Primary Email Address field", "true", String.valueOf(bPrimaryEmailId), true);
				oR_Login.btnContinueToAccount.click();
			}


			/****************************************
			 *
			 * Start - validating interstitial pages
			 *
			 *********************/



			/*********************
			 *
	End - validating interstitial pages

			 ****************************************/

			//Validating whether SMB Dashboard is displayed
			if(UI.WaitForObject(oR_ATT.txtSMBaccountOverview, 10, lDriver))
			{
				//Code modified to handle Account overview page for a customer linking his account - Clint, 20th Feb 2015
				Report.TestPoint(testContext.getName(),"Validate Overview page is displayed","true","true", true);
			}
			else
			{
				Report.TestPoint(testContext.getName(),
						"Validate Overview page is displayed", "Account overview page should displayed",
						"Account overview page is not displayed", true);
			}





		} catch (Exception e) {
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Validate user successfully logged in to MyATT", "User should be able to login to MyATT", "User failed to login to MyATT application", true);
		}
		
	}
	
	public static void LogoutApplicationZone(final ITestContext testContext) throws HeadlessException,
	IOException, AWTException, InterruptedException {
		try {
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
			Boolean bbtnLogout = UI.WaitForObject(oR_Dashboard.btnLogout, UI.iObjTimeOut, lDriver);
			Report.TestPoint(testContext.getName(), "Verify Logout button", "true", String.valueOf(bbtnLogout), true);
			oR_Dashboard.btnLogout.click();
			Report.TestPoint(testContext.getName(), "Logged out", "true", "true", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			String sErrMsg = e.getMessage();
			Report.OperationPoint(testContext.getName(), "Operational - Not able to logout \n"+sErrMsg);
		} 
	}
	
	/**************************************************************
	 * Method Name - LogoutApplication() 
	 * Description - This method is used to logout from the myATT RWD application 
	 * Date created- 27th June 2019
	 * Developed by- Monica Mohabansi
	 * Last Modified By -
	 * Last Modified Date -
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void Logout(final ITestContext testContext,
			String sTcName) throws Exception {

		// verify logout button
		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());	
			OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			

			if (UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav, 5, lDriver).equals(true)) 
			{
				Report.OperationPoint(testContext.getName(), "Operational - Logging out");
				Report.TestPoint(testContext.getName(),"Validate profile link is displayed", "True","True", true);
				oR_AccountOverview.lnkProfileSecondaryNav.click();
				
				if (UI.WaitForObject(oR_AccountOverview.btnLogoutRWD, 5, lDriver).equals(true)) 
				{
					Report.TestPoint(testContext.getName(),"Validate logout button is displayed", "True","True", true);
					oR_AccountOverview.btnLogoutRWD.click();
					Thread.sleep(6000);
					Report.ValidationPoint(testContext.getName(), "Logout from Application", "Logout Successful", "Logout Successful", true);

				}
			}
		}catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Logout from Application", "Failed to Logout", "false", true);
		}
	}
}
