package com.AppSpecificLibrary.MyATTZone;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;

import com.OR.MyATTZone.OR_AgentActivity;
import com.OR.MyATTZone.OR_Dashboard;
import com.OR.MyATTZone.OR_MobileShareComparisionTool;
import com.OR.MyATTZone.OR_MyATTZoneDashboard;
import com.SupportingFiles.IO;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;
import com.SupportingFiles.UI;


public class MyATTZoneDashboard extends LaunchAndLogout {

	static Hashtable<String, String> sTestParams = new Hashtable<String, String>();

	/**************************************************************
	 * Function Name - ValidateErrorMsgMobileShareRecommender 
	 * Description- This is to validate the error on input search screen/ MSP landing screen for IRU Wireless Customer having more than 10 CTNs
	 * Parameters - final ITestContext testContext
	 * Date created - 24-March-2015
	 * Developed by - Rahul Bakde
	 * Last Modified By -
	 * Last Modified Date -
	 ***************************************************************/
	public static void ValidateErrorMsgMobileShareRecommender(final ITestContext testContext)
			throws Exception {
		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
			OR_MobileShareComparisionTool oR_MobileShareComparisionTool = PageFactory.initElements(lDriver, OR_MobileShareComparisionTool.class);	
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			String sWirelessBAN;
			//retrieving WirelessBAN parameter value from Test Data excel sheet
			//			sTestParams = IO.Read_Iter_Data(sEnv, sCurrTCName, iCurrIter);
			sWirelessBAN = IO.GetParamVal(sTestParams, "WirelessBAN");
			
			//hover over wireless
			Actions builder=new Actions(UI.driver);
			if (UI.WaitForObject(oR_Dashboard.txtWireless, 1)) {
				//wirless exist
				Report.TestPoint(testContext.getName(), "Validate wireless tab exist", "True", "True", true);
				builder.moveToElement(oR_Dashboard.txtWireless).build().perform();
				//check if BAN tab exist
				if (UI.WaitForObject(oR_Dashboard.txtWirelessBAN, 1)) {
					Report.TestPoint(testContext.getName(), "Validate BAN tab exist", "True", "True", true);
					oR_Dashboard.txtWirelessBAN.click();
					//verify BAN edit box exist
					if (UI.WaitForObject(oR_Dashboard.edtWirelessBAN, UI.iObjTimeOut)) {
						Report.TestPoint(testContext.getName(), "Validate BAN edit box exist", "True", "True", true);
						oR_Dashboard.edtWirelessBAN.sendKeys(sWirelessBAN);
						//verify if search button exist
						if (UI.WaitForObject(oR_Dashboard.btnSearch, 1)) {
							Report.TestPoint(testContext.getName(), "Validate search button exist", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Clicking on search button");
							oR_Dashboard.btnSearch.click();
							//verify select slid radio button exist
							List <WebElement> btnRadio= lDriver.findElements(By.xpath("//*[@id='selectedSlid']"));
							if (btnRadio.size()>0) {
								Report.TestPoint(testContext.getName(), "Validate select slid radio button exist", "True", "True", true);
								btnRadio.get(1).click();
								if (UI.WaitForObject(oR_Dashboard.btnContinue, 10)) {
									Report.TestPoint(testContext.getName(), "Validate continue button exist", "True", "True", true);
									oR_Dashboard.btnContinue.click();
									//check if Mobile Share Comparison button exist
									if (UI.WaitForObject(oR_Dashboard.btnMobileShareComparison, UI.iObjTimeOut)) {
										Report.TestPoint(testContext.getName(), "Validate Mobile Share Comparison button exist", "True", "True", true);
										//click on Mobile Share Comparison button
										oR_Dashboard.btnMobileShareComparison.click();
										//validate navigated to Mobile Share Comparison Tool page
										String parentWindow=lDriver.getWindowHandle();
										for (String windowHandle : lDriver.getWindowHandles()) {
											lDriver.switchTo().window(windowHandle);
										}										
										if (UI.WaitForObject(oR_MobileShareComparisionTool.txtMobileShareHeading, UI.iObjTimeOut)) {
											Report.TestPoint(testContext.getName(), "Validate navigated to Mobile Share Comparison Tool page", "True", "True", true);
											//check if click here button exist
											if (UI.WaitForObject(oR_MobileShareComparisionTool.btnClickHere, 1)) {
												Report.TestPoint(testContext.getName(), "Validate click here button exist", "True", "True", true);
												oR_MobileShareComparisionTool.btnClickHere.click();
												//wait for the edit box
												if (UI.WaitForObject(oR_MobileShareComparisionTool.edtCTN, UI.iObjTimeOut)) {
													Report.TestPoint(testContext.getName(), "Validate edit box exist", "True", "True", true);
													oR_MobileShareComparisionTool.edtCTN.sendKeys(sWirelessBAN);
													if (UI.WaitForObject(oR_MobileShareComparisionTool.btnSubmit, 1)) {
														Report.TestPoint(testContext.getName(), "Validate Submit button exist", "True", "True", true);
														oR_MobileShareComparisionTool.btnSubmit.click();
														//validte Mobile share comparision tool page exist
														if (UI.WaitForObject(oR_MobileShareComparisionTool.txtMobileShareHeading, UI.iObjTimeOut)) {
															Report.TestPoint(testContext.getName(), "Validate navigated to Mobile Share Comparison Tool page", "True", "True", true);
														}else{
															Report.TestPoint(testContext.getName(), "Validate navigated to Mobile Share Comparison Tool page", "True", "False", true);
														}
													} else {
														Report.TestPoint(testContext.getName(), "Validate Submit button exist", "True", "False", true);
													}
												} else {
													Report.TestPoint(testContext.getName(), "Validate edit box exist", "True", "False", true);
												}
											} else {
												Report.TestPoint(testContext.getName(), "Validate click here button exist", "True", "False", true);
											}
										} else {
											Report.TestPoint(testContext.getName(), "Validate navigated to Mobile Share Comparison Tool page", "True", "False", true);
										}
									} else {
										Report.TestPoint(testContext.getName(), "Validate Mobile Share Comparision button exist", "True", "False", true);
									}
								} else {
									Report.TestPoint(testContext.getName(), "Validate continue button exist", "True", "False", true);
								}
							} else {
								Report.TestPoint(testContext.getName(), "Validate select slid radio button exist", "True", "False", true);
							}
						} else {
							Report.TestPoint(testContext.getName(), "Validate search button exist", "True", "False", true);
						}
					} else {
						Report.TestPoint(testContext.getName(), "Validate BAN edit box exist", "True", "False", true);
					}
				} else {
					Report.TestPoint(testContext.getName(), "Validate BAN tab exist", "True", "False", true);
				}
			} else {
				Report.TestPoint(testContext.getName(), "Validate wireless tab exist", "True", "False", true);
			}
		}catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	

	/**************************************************************
	 * Function Name - ValidateDataInputForFAN 
	 * Description- This is to validate the Data Input for FAN
	 * Parameters - final ITestContext testContext
	 * Date created - 3rd April 2015
	 * Developed by - 	Krutika Kamdi
	 * Last Modified By -
	 * Last Modified Date -
	 ***************************************************************/
	public static void ValidateDataInputForFAN(final ITestContext testContext)
			throws Exception {
		try{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			//verify AT&T Access ID option is present
			Boolean bATTAcessID = UI.WaitForObject(oR_Dashboard.elm_ATTAccessID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "verify AT&T Access ID is present", "true", bATTAcessID.toString(), true);
			//Verify Wireless option is present
			Boolean bWireless = UI.WaitForObject(oR_Dashboard.txtWireless, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Wireless option is present", "true", bWireless.toString(), true);
			//Verify Uverse option is present
			Boolean bUverse = UI.WaitForObject(oR_Dashboard.elm_Uverse, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Uverse option is present", "true", bUverse.toString(), true);
			//Verify Wireline option is present
			Boolean bWireline = UI.WaitForObject(oR_Dashboard.elm_Wireline, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Wireline option is present", "true", bWireline.toString(), true);
			//Verify DSL option is present
			Boolean bDSL = UI.WaitForObject(oR_Dashboard.elm_DSL, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify DSL option is present", "true", bDSL.toString(), true);
			//Verify FAN option is present
			Boolean bFAN = UI.WaitForObject(oR_Dashboard.elm_Wireline, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify FAN option is present", "true", bFAN.toString(), true);
			//Verifying the details of Input rules
			if(bFAN.equals(true))
			{
				oR_Dashboard.elm_Wireline.click();
				Thread.sleep(10000);
				Boolean bFANdetails = UI.WaitForObject(oR_Dashboard.txtFAN, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Verify FAN details is present", "true", bFANdetails.toString(), true);
				
					Boolean bInputRules = UI.WaitForObject(oR_Dashboard.lnkInputRules, UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "Verify FAN Data Input Rules link is present", "true", bInputRules.toString(), true);
				    oR_Dashboard.lnkInputRules.click();
				
				Thread.sleep(10000);
				//Report.ValidationPoint(testContext.getName(), "clicked", "true", "true", true);
				//Thread.sleep(10000);
				//Report.OperationPoint(testContext.getName(), "success");
				//Boolean bRules = UI.WaitForObject(lDriver.findElement(By.xpath("//a[@onclick='javascript:alert('Data input rules:\nMax eight numeric digits, leading zeros should not be removed  (e.g. 00111111)')']"), UI.iObjTimeOut);
				//Report.TestPoint(testContext.getName(), "Verify FAN Data Input Rules link is present", "true", bInputRules.toString(), true);
				//String sRules = lDriver.switchTo().alert().getText();
				 //Report.ValidationPoint(testContext.getName(),"Alert Screenshot : ", "true", "true", true);
				
				//String spath = System.getProperty("user.dir");
				
				  BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			       //ImageIO.write(image, "png", new File("E:\\Krutika\\Automation\\Backup2.jpg"));
			       
			       ImageIO.write(image, "png", new File("D:\\OSD_Test_Automation\\MyATT_Web_Selenium\\IO\\Reports\\CSR04633.jpg"));
				
				Alert alert = lDriver.switchTo().alert();
				//String sRules = alert.getText();
				
					//File screenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//org.apache.commons.io.FileUtils.copyFile(screenshot, new File("E:\\Krutika\\Automation\\Backup.jpg"));
			
				//File scrFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		       //FileUtils.copyFile(scrFile, new File("E:\\Krutika\\Automation\\Backup.jpg"));

		       String sRules = alert.getText();
		       lDriver.switchTo().alert().accept();
				System.out.println("Alert Text = "+sRules);
				if(sRules!=null)
				{
					System.out.println("Alert Text checked"+sRules);
					Report.ValidationPoint(testContext.getName(),"Alert Screenshot validation point done successfully", "true", "true", true);
				}
			
			}
		}catch (Exception e) {
			 e.printStackTrace();
	Report.TestPoint(testContext.getName(),
			"Some error has occured", "True",
			e.getMessage(), true);
	 
}
}
	

	/**************************************************************
	 * Function Name - ValidateAllOptionsInSearchMenu 
	 * Description- This Function verifies whether all options are present in search menu on dashboard
	 * Parameters - final ITestContext testContext
	 * Date created - 6th April 2015
	 * Developed by - 	Sneha Pansare
	 * Last Modified By -
	 * Last Modified Date -
	 ***************************************************************/
	
	public static void ValidateAllOptionsInSearchMenu(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			//verify AT&T Access ID option is present
			Boolean bATTAcessID = UI.WaitForObject(oR_Dashboard.elm_ATTAccessID, 40);
			Report.TestPoint(testContext.getName(), "verify AT&T Access ID is present", "true", bATTAcessID.toString(), true);
			
			//Verify Wireless option is present
			Boolean bWireless = UI.WaitForObject(oR_Dashboard.txtWireless, 10);
			Report.TestPoint(testContext.getName(), "Verify Wireless option is present", "true", bWireless.toString(), true);
			
			//Verify Uverse option is present
			Boolean bUverse = UI.WaitForObject(oR_Dashboard.elm_Uverse, 10);
			Report.TestPoint(testContext.getName(), "Verify Uverse option is present", "true", bUverse.toString(), true);
			
			//Verify Wireline option is present
			Boolean bWireline = UI.WaitForObject(oR_Dashboard.elm_Wireline, 10);
			Report.TestPoint(testContext.getName(), "Verify Wireline option is present", "true", bWireline.toString(), true);
			
			//Verify DSL option is present
			Boolean bDSL = UI.WaitForObject(oR_Dashboard.elm_DSL, 10);
			Report.TestPoint(testContext.getName(), "Verify DSL option is present", "true", bDSL.toString(), true);
			
			//Verify FAN option is present
			Boolean bFAN = UI.WaitForObject(oR_Dashboard.elm_Wireline, 10);
			Report.TestPoint(testContext.getName(), "Verify FAN option is present", "true", bFAN.toString(), true);
			
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
	
	/**************************************************************
	 * Function Name - VerifyErrorMessageForWrongFAN 
	 * Description- This Function verifies whether all options are present in search menu on dashboard
	 * 				Also Enters wrong FAN in FAN input box and checks for invalid FAN error message
	 * Parameters - final ITestContext testContext
	 * Date created - 6th April 2015
	 * Developed by - 	Sneha Pansare
	 * Last Modified By -
	 * Last Modified Date -
	 ***************************************************************/
	 //CSR04636 
	
	public static void VerifyErrorMessageForWrongFAN(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		String sWrongFAN = IO.GetParamVal(sTestParams, "Wrong_FAN");
		
		try
		{
			//Verify whether all options are present  in the search menu
			ValidateAllOptionsInSearchMenu(testContext);
			
			if(UI.WaitForObject(oR_Dashboard.elm_Wireline, 40))
			{
				oR_Dashboard.elm_Wireline.click();
				
				//Verify FAN section is displayed
				Boolean bFANinputBox = UI.WaitForObject(oR_Dashboard.edtFAN, 30);
				Report.TestPoint(testContext.getName(), "Verify FAN section is displayed", "true", bFANinputBox.toString(), true);
				
				//Enter wrong FAN
				oR_Dashboard.edtFAN.sendKeys(sWrongFAN);
				Report.ValidationPoint(testContext.getName(), "Enter wrong FAN", "true", "true", true);
				
				//Click on Search button
				Boolean bSearchButton = UI.WaitForObject(oR_Dashboard.btnSearch, 30);
				Report.TestPoint(testContext.getName(), "Click on search Button", "true", bSearchButton.toString(), true);
				oR_Dashboard.btnSearch.click();
				
				try
				{
					WebElement elmErrorMessage = lDriver.findElement(By.xpath("//*[@class='errorMsg' and contains(text(),'The FAN # entered does not exist')]"));
					if(elmErrorMessage.isDisplayed())
					{
						Report.TestPoint(testContext.getName(), "Verify Error message - 'The FAN # entered does not exist'", "Message displayed", "Message displayed", true);
					}
					else
					{
						Report.TestPoint(testContext.getName(), "Verify Error message - 'The FAN # entered does not exist'", "Message displayed", "Message NOT displayed", true);
					}
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Verify Error message - 'The FAN # entered does not exist'", "Message displayed", "Message NOT displayed", true);
				}
				
			}
			else
			{
				Report.TestPoint(testContext.getName(),"Click FAN option in search options", "Clicked","FAN option is NOT present", true);
			}
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - ValidateFANHardrockPage 
	 * Description- This is to validate the Data Input for FAN
	 * Parameters - final ITestContext testContext
	 * Date created - 6th April 2015
	 * Developed by - 	Krutika Kamdi
	 * Last Modified By -
	 * Last Modified Date -
	 ***************************************************************/
	public static void ValidateFANHardrockPage(final ITestContext testContext)
			throws Exception {
		try{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			String sFAN = IO.GetParamVal(sTestParams, "FAN");
			
			//Verify whether all options are present  in the search menu
			ValidateAllOptionsInSearchMenu(testContext);
			 String winHandleBefore = lDriver.getWindowHandle();
			//clciking on FAN 
			Boolean bFAN = UI.WaitForObject(oR_Dashboard.elm_Wireline, UI.iObjTimeOut);
			if(bFAN.equals(true))
			{
				oR_Dashboard.elm_Wireline.click();
				Thread.sleep(10000);
				Boolean bFANdetails = UI.WaitForObject(oR_Dashboard.txtFAN, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Verify FAN search is displayed", "true", bFANdetails.toString(), true);
				//Entering FAN detail to search
				oR_Dashboard.edtFAN.sendKeys(sFAN);
				oR_Dashboard.btnSearch.click();
				Thread.sleep(5000);
				Boolean bPopUp = UI.WaitForObject(oR_Dashboard.imgPopUp, UI.iObjTimeOut);
				//validating the details of FAN such as Company name , Valid message
				if(bPopUp.equals(true))
				{
					Report.TestPoint(testContext.getName(), "Verify FAN Model is displayed", "true", "true", true);
					Boolean bCompany = UI.WaitForObject(lDriver.findElement(By.xpath("//div[@id='fandetails']//span[contains(text(),'Company Name   : ')]")), UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "Verify Company name is present", "true", bCompany.toString(), true);
					Boolean bCompanyName = UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(text(),'Company Name')]/parent::*/child::span[2]")), UI.iObjTimeOut);
					if(bCompanyName)
					{
						String sCompanyName = lDriver.findElement(By.xpath("//*[contains(text(),'Company Name')]/parent::*/child::span[2]")).getText();
						if(!(sCompanyName.isEmpty()))
						{
							Report.TestPoint(testContext.getName(), "Verify Company name is displayed", "Company name "+sCompanyName+" is displayed", "Company name "+sCompanyName+" is displayed", true);
						}		
						else
						{
							Report.TestPoint(testContext.getName(), "Verify Company name is displayed", "Company name "+sCompanyName+" is displayed", "Company name "+sCompanyName+" is NOT displayed", true);
						}
					}
					else
					{
						Report.TestPoint(testContext.getName(), "Verify Company name is displayed", "Company name is displayed", "Company name is NOT displayed", true);
					}
					Boolean bValidMsg = UI.WaitForObject(lDriver.findElement(By.xpath("//div[@id='fandetails']//span[contains(text(),'The FAN is valid. Would you like to continue in the ordering flow?')]")), UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "Verify Valid FAN msg is displayed", "true", bValidMsg.toString(), true);
					Boolean bNobtn = UI.WaitForObject(lDriver.findElement(By.xpath("//input[@src='_assets/images/bt_No.png']")), UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "Verify No button is displayed", "true", bNobtn.toString(), true);
					Boolean bYesbtn = UI.WaitForObject(lDriver.findElement(By.xpath("//input[@src='_assets/images/bt_yes.png']")), UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "Verify Yes button is displayed", "true", bYesbtn.toString(), true);
					//Clicking on Yes button
					if(bYesbtn.equals(true))
					{
						Report.OperationPoint(testContext.getName(), "Navigate to att shop Clicking on Yes");
						lDriver.findElement(By.xpath("//input[@src='_assets/images/bt_yes.png']")).click();
						//Handling the Hardrock page (shop page)
					    
					     lDriver.switchTo().window(winHandleBefore);
							for (String windowHandle : lDriver.getWindowHandles()) {
								lDriver.switchTo().window(windowHandle);
								String url = lDriver.getCurrentUrl();
								if(url.contains("att.com"))
								{
									
									Report.TestPoint(testContext.getName(), "Hardrock page is displayed", "true", "true", true);
								}
							}	
						//Closing the new window opened after clicking on Yes
						lDriver.close();
						lDriver.switchTo().window(winHandleBefore);
					};
					//Verifying the main CSR admin tool page is displayed
					Boolean bWelcome = UI.WaitForObject(oR_Dashboard.txtWelcome, UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "Verify User is landed on CSR Admin Tool in parent frame ", "true", bWelcome.toString(), true);
					//For clicking on No
					oR_Dashboard.elm_Wireline.click();
					Thread.sleep(10000);
					Report.TestPoint(testContext.getName(), "Verify FAN details is present", "true", bFANdetails.toString(), true);
					oR_Dashboard.edtFAN.sendKeys(sFAN);
					oR_Dashboard.btnSearch.click();
					Thread.sleep(5000);
					lDriver.findElement(By.xpath("//input[@src='_assets/images/bt_No.png']")).click();
					//Verifying the main CSR admin tool page is displayed
					Report.TestPoint(testContext.getName(), "Verify User is landed on CSR Admin Tool in parent frame, ", "true", bWelcome.toString(), true);
				}

			}

		}catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
		}
	
	/**************************************************************
	 * Function Name - ValidateWPHDeviceUser
	 * Description- Validate for WHP device user is not allowed change the usage through Slider.
	 * Parameters - final ITestContext testContext
	 * Date created - 7th April 2015
	 * Developed by - 	Merrin Mathai
	 * Last Modified By -
	 * Last Modified Date -
	 ***************************************************************/

	/**************************************************************
	 * Function Name - ValidateWPHDeviceUser
	 * Description- Validate for WHP device user is not allowed change the usage through Slider.
	 * Parameters - final ITestContext testContext
	 * Date created - 7th April 2015
	 * Developed by - 	Merrin Mathai
	 * Last Modified By -
	 * Last Modified Date -
	 ***************************************************************/
	public static void ValidateWPHDeviceUserChangeUsage(final ITestContext testContext)
			throws Exception {
		try{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
			OR_MobileShareComparisionTool oR_MobileShareComparisionTool = PageFactory.initElements(lDriver, OR_MobileShareComparisionTool.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			String sBAN=IO.GetParamVal(sTestParams, "sloginID");
			//Click on the 'Mobile Share Recommender' tab. On CSR Landing Page
            Boolean bMobileShareComparisonTab = UI.WaitForObject(oR_Dashboard.btnMobileShareComparison, 30);
            Report.TestPoint(testContext.getName(), "Click on the 'Mobile Share Recommender tab", "true", bMobileShareComparisonTab.toString(), true);
            oR_Dashboard.btnMobileShareComparison.click();
            
            //switch to new opened window
            
              String parentHandle = lDriver.getWindowHandle(); // get the current window handle
            
              Thread.sleep(5000);
              for (String winHandle : lDriver.getWindowHandles()) {
                  lDriver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle 
              }
              
            //Verify Redirected to Mobile Share Recommender Tool Search Page
            Boolean bMobileShareHeading = UI.WaitForObject(oR_MobileShareComparisionTool.txtMobileShareHeading, 30);
            Report.TestPoint(testContext.getName(), "Verify Redirection to Mobile Share Recommender Tool Search Page", "true", bMobileShareHeading.toString(), true);
            
            //Enter wireless BAN/CTN on Search Page
            Boolean bEnterCTN = UI.WaitForObject(oR_MobileShareComparisionTool.edtCTN, 30);
            Report.TestPoint(testContext.getName(), "Enter wireless BAN/CTN", "true", bEnterCTN.toString(), true);
            oR_MobileShareComparisionTool.edtCTN.sendKeys(sBAN);
            
            //Click submit
            Boolean bSubmit = UI.WaitForObject(oR_MobileShareComparisionTool.btnSubmit, 30);
            Report.TestPoint(testContext.getName(), "Click submit", "true", bSubmit.toString(), true);
            oR_MobileShareComparisionTool.btnSubmit.click();
            
            //Verify Device Navigation Bar is displayed besides Left Hand Section
            Boolean bDeviceNavigationBar = UI.WaitForObject(oR_MobileShareComparisionTool.lstDeviceNavigationBar, 30);
            Report.TestPoint(testContext.getName(), "Verify Device Navigation Bar is displayed besides Left Hand Section", "true", bDeviceNavigationBar.toString(), true);
            
            //Verify that Device Navigation Bar should have - first name , image type , CTN , model of device and possibly informational message
            
            try
            {
                  
                  List<WebElement> elmDevicesTab = lDriver.findElements(By.xpath("//*[contains(@id,'Slider')]//li"));
                  
                  //Verify Up and down arrows (viewable but disabled if under 7 devices in list)
                   if(UI.WaitForObject(oR_MobileShareComparisionTool.btnSlideDownOfDeviceNavigationBar, 20))
                  {
                         //In this case arrow should be displayed but disabled
                         if(elmDevicesTab.size()<7)
                         {
                                if(oR_MobileShareComparisionTool.btnSlideDownOfDeviceNavigationBar.isDisplayed())
                                {
                                       Report.TestPoint(testContext.getName(), "Verify down arrow is Viewable", "Viewable", "Viewable", true);
                                }
                                else
                                {
                                       Report.TestPoint(testContext.getName(), "Verify down arrow is Viewable", "Viewable", "NOT Viewable", true);
                                }
                         }
                         else
                         {
                                //In this case arrow should be enabled
                                if(oR_MobileShareComparisionTool.btnSlideDownOfDeviceNavigationBar.isEnabled())
                                {
                                       Report.TestPoint(testContext.getName(), "Verify down arrow is Enabled", "Enabled", "Enabled", true);
                                }
                                else
                                {
                                       Report.TestPoint(testContext.getName(), "Verify down arrow is Enabled", "Enabled", "NOT Enabled", true);
                                }
                         }
                         
                  }
                  else
                  {
                         Report.TestPoint(testContext.getName(), "Verify down arrow is Displayed", "Displayed", "NOT Displayed", true);
                  }
                 //Verify if modify usage link is available on RH side of MSRT   
                   Boolean bModifyUsage=UI.WaitForObject(oR_MobileShareComparisionTool.lnkModifyUsage, 20);   
                   Report.ValidationPoint(testContext.getName(), "Verify modify usage link is available", "true", String.valueOf(bModifyUsage), true);
                   oR_MobileShareComparisionTool.lnkModifyUsage.click();
                   
                 //User Modify the Usage data for WHP device
                    List<WebElement> lstUsageBar=lDriver.findElements(By.xpath("//span[contains(@class,'ui-slider')]"));
                   if(lstUsageBar.size()>0){
                	   Report.ValidationPoint(testContext.getName(), "User should not be able to change the usage value using the Slider", "true","true", true);  
                   }
                   else
                   {
                	   Report.ValidationPoint(testContext.getName(), "User should not be able to change the usage value using the Slider", "true","false", true);    
                   } 
                   
                   //Click on close button of frame 
                   List<WebElement> btnClose = null;
                   try
                   {
                	    btnClose = lDriver.findElements(By.xpath("//*[@class='lightbox']//img[@class='close2']"));
                	   
                	    btnClose.get(0).click();
                	   Report.TestPoint(testContext.getName(), "Click on close button of Modify usage pop up", "Clicking", "Clicking", true);
                	  
                   }
                   catch(Exception e)
                   {
                	   try
                	   {
                		   btnClose.get(1).click();
                		   Report.TestPoint(testContext.getName(), "Click on close button of Modify usage pop up", "Clicking", "Clicking", true);
                	   }
                	   catch(Exception e2)
                	   {
                		   Report.TestPoint(testContext.getName(), "Click on close button of Modify usage pop up", "Clicking", "Close button not found", true);  
                	   }
                	   
                   }
                   
                   
                   //Click on Plan Details button of mobile share section
                   Boolean bPlanDetailsInHeaderMobileSharePlanSection = UI.WaitForObject(oR_MobileShareComparisionTool.txtPlanDetailsInHeaderMobileSharePlanSection, 30);
                   Report.TestPoint(testContext.getName(), "Click on Plan Details button of mobile share section", "true", bPlanDetailsInHeaderMobileSharePlanSection.toString(), true);
                   oR_MobileShareComparisionTool.txtPlanDetailsInHeaderMobileSharePlanSection.click();
                   
                   //Search for wireless home phone plan
                   List<WebElement> lstWHP = lDriver.findElements(By.xpath("//*[contains(text(),'Added Wireless Home Phone')]/parent::*/parent::*"));
                   if(lstWHP.size()==0)
                   {
                	   Report.TestPoint(testContext.getName(), "Verify wireless home phone plan is displayed on plan details page", "displayed", "NOT displayed", true);
                   }
                   else
                   {
                	   Report.TestPoint(testContext.getName(), "Verify wireless home phone plan is displayed on plan details page", "displayed", "displayed", true);
                   }
                   
                   //Verify WHP plan is displyed
                   boolean blnWHPdisplayedWithCost = false;
                   for(int i= 0; i< lstWHP.size() ; i++)
                   {
                	   String sPlanDetailsText = lstWHP.get(i).getText();
                	   if(sPlanDetailsText.contains("$20.00"))
                	   {
                		   blnWHPdisplayedWithCost = true;
                		   break;
                	   }
                   }
                   
                   if(blnWHPdisplayedWithCost==true)
                   {
                	   Report.TestPoint(testContext.getName(), "Verify WHP device cost is displayed as $20", "displayed", "displayed", true);
                   }
                   else
                   {
                	   Report.TestPoint(testContext.getName(), "Verify WHP device cost is displayed as $20", "displayed", "NOT displayed", true);
                   }
            }
                  
             
            catch(Exception e)
            {
                  Report.TestPoint(testContext.getName(), "Verify Device Navigation Bar for Individual Device", "Displayed", "NOT Displayed", true);
            } 

		}catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	
	
	
	
	
	/**************************************************************
	 * Function Name - ValidatePlanDetailPageAfterAddingDeletingMultipleDevices 
	 * Description- This Function verifies All the elements on mobile share recoomender page
	 * 				adds and delete device plans . verifies whether all CTNs are displayed in each section along with navigation bar
	 * 				verifies all elements on plan details popup. 
	 * 				changes value of discount in input box and verifies whether it gets reflected in popup header in green color
	 * Parameters - final ITestContext testContext
	 * Date created - 9th April 2015
	 * Developed by - 	Sneha Pansare
	 * Last Modified By -
	 * Last Modified Date -
	 ***************************************************************/
	 //CSR04561 	
	
	public static void ValidatePlanDetailPageAfterAddingDeletingMultipleDevices(final ITestContext testContext) throws HeadlessException, IOException, AWTException, InterruptedException
	{
		try
		{	
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
			OR_MobileShareComparisionTool oR_MobileShareComparisionTool = PageFactory.initElements(lDriver, OR_MobileShareComparisionTool.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			String sBAN = IO.GetParamVal(sTestParams, "BAN");
			String sDiscountAmountToEnterInInputBox = IO.GetParamVal(sTestParams, "Discount_Amount_To_Enter_In_InputBox");
			
			//Click on the 'Mobile Share Recommender' tab. On CSR Landing Page
			Boolean bMobileShareComparisonTab = UI.WaitForObject(oR_Dashboard.btnMobileShareComparison, 30);
			Report.TestPoint(testContext.getName(), "Click on the 'Mobile Share Recommender tab", "true", bMobileShareComparisonTab.toString(), true);
			oR_Dashboard.btnMobileShareComparison.click();
			
			//switch to new opened window
			 
			  //String parentHandle = lDriver.getWindowHandle(); // get the current window handle
			
			  Thread.sleep(5000);
			  for (String winHandle : lDriver.getWindowHandles()) {
			      lDriver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle 
			  }
			  
			//Verify Redirected to Mobile Share Recommender Tool Search Page
			Boolean bMobileShareHeading = UI.WaitForObject(oR_MobileShareComparisionTool.txtMobileShareHeading, 30);
			Report.TestPoint(testContext.getName(), "Verify Redirection to Mobile Share Recommender Tool Search Page", "true", bMobileShareHeading.toString(), true);
			
			//Enter wireless BAN/CTN on Search Page
			Boolean bEnterCTN = UI.WaitForObject(oR_MobileShareComparisionTool.edtCTN, 30);
			Report.TestPoint(testContext.getName(), "Enter wireless BAN/CTN", "true", bEnterCTN.toString(), true);
			oR_MobileShareComparisionTool.edtCTN.sendKeys(sBAN);
			
			//Click submit
			Boolean bSubmit = UI.WaitForObject(oR_MobileShareComparisionTool.btnSubmit, 30);
			Report.TestPoint(testContext.getName(), "Click submit", "true", bSubmit.toString(), true);
			oR_MobileShareComparisionTool.btnSubmit.click();
			
			//Verify Device Navigation Bar is displayed besides Left Hand Section
			Boolean bDeviceNavigationBar = UI.WaitForObject(oR_MobileShareComparisionTool.lstDeviceNavigationBar, 30);
			Report.ValidationPoint(testContext.getName(), "Verify Device Navigation Bar is displayed besides Left Hand Section", "true", bDeviceNavigationBar.toString(), true);
			
			//Verify that Device Navigation Bar should have - first name , image type , CTN , model of device and possibly informational message
			String[] arrCTN=new String[2];
			try
			{
				
				List<WebElement> elmDevicesTab = lDriver.findElements(By.xpath("//*[contains(@id,'Slider')]//li"));
				
				//Verify Up and down arrows (viewable but disabled if under 7 devices in list)
				if(UI.WaitForObject(oR_MobileShareComparisionTool.btnSlideDownOfDeviceNavigationBar, 20))
				{
					//In this case arrow should be displayed but disabled
					if(elmDevicesTab.size()<7)
					{
						if(oR_MobileShareComparisionTool.btnSlideDownOfDeviceNavigationBar.isDisplayed())
						{
							Report.ValidationPoint(testContext.getName(), "Verify down arrow is Viewable", "Viewable", "Viewable", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Verify down arrow is Viewable", "Viewable", "NOT Viewable", true);
						}
					}
					else
					{
						//In this case arrow should be enabled
						if(oR_MobileShareComparisionTool.btnSlideDownOfDeviceNavigationBar.isEnabled())
						{
							Report.ValidationPoint(testContext.getName(), "Verify down arrow is Enabled", "Enabled", "Enabled", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Verify down arrow is Enabled", "Enabled", "NOT Enabled", true);
						}
					}
					
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify down arrow is Displayed", "Displayed", "NOT Displayed", true);
				}
				
				
				//Store CTNs which we will be extracting in this for loop for future use
				arrCTN= new String[elmDevicesTab.size()];
				
				for(int cnt=1; cnt<=elmDevicesTab.size(); cnt++)
				{
					String sCTN="";
					
					//Verify CTN
					WebElement elmCTN;
					try
					{
						elmCTN = lDriver.findElement(By.xpath("//*[contains(@id,'Slider')]//li["+cnt+"]//*[contains(@class,'Details')]//span[contains(text(),'-')]"));
						
						sCTN = elmCTN.getText();
						
						if(sCTN.equals("") && elmDevicesTab.size()>=7)
						{
							//it means there is down arrow and some CTNs will be visible when we press down arrow
							oR_MobileShareComparisionTool.btnSlideDownOfDeviceNavigationBar.click();
							//Now again extract element
							elmCTN = lDriver.findElement(By.xpath("//*[contains(@id,'Slider')]//li["+cnt+"]//*[contains(@class,'Details')]//span[contains(text(),'-')]"));
							sCTN = elmCTN.getText();
						}
						
						
						//Store CTN in array
						arrCTN[cnt-1] = sCTN ;
						
						//String sExpectedPattern = "\\d\\d\\d"+"-"+"\\d\\d\\d"+"-"+"\\d\\d\\d\\d"; 
						String sExpectedPattern = "\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d";
							
						if(sCTN.matches(sExpectedPattern))
						{
							Report.ValidationPoint(testContext.getName(), "Verify CTN '"+sCTN+"' is displayed for Device Navigation bar No: "+cnt, "Displayed", "Displayed", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Verify CTN '"+sCTN+"' is displayed for Device Navigation bar No: "+cnt, "Displayed", "NOT Displayed", true);
						}
						
					}
					catch(Exception e)
					{
						Report.ValidationPoint(testContext.getName(), "Verify CTN '"+sCTN+"' is displayed for Device Navigation bar No: "+cnt, "Displayed", "NOT Displayed", true);
					}
					
					//Verify device Image
					try
					{
						WebElement elmImage = lDriver.findElement(By.xpath("//*[contains(@id,'Slider')]//li["+cnt+"]//img"));
						
						if(elmImage.isDisplayed())
						{
							Report.ValidationPoint(testContext.getName(), "Verify device Image is displayed for CTN '"+sCTN+"' on Device Navigation bar No: "+cnt, "Displayed", "Displayed", true);
						}
					}
					catch(Exception e)
					{
						Report.ValidationPoint(testContext.getName(), "Verify device Image is displayed for CTN '"+sCTN+"' on Device Navigation bar No: "+cnt, "Displayed", "NOT Displayed", true);
					}
					
					
					//Verify first name
					try
					{
						WebElement elmFirstName = lDriver.findElement(By.xpath("//*[contains(@id,'Slider')]//li["+cnt+"]//*[contains(@class,'Details')]/strong"));
						
						if(elmFirstName.isDisplayed())
						{
							Report.ValidationPoint(testContext.getName(), "Verify first name is displayed for CTN '"+sCTN+"' on Device Navigation bar No: "+cnt, "Displayed", "Displayed", true);
						}
					}
					catch(Exception e)
					{
						Report.ValidationPoint(testContext.getName(), "Verify first name is displayed for CTN '"+sCTN+"' on Device Navigation bar No: "+cnt, "Displayed", "NOT Displayed", true);
					}
					
					
					//*[contains(@id,'Slider')]//li[1]//*[contains(@class,'Details')]//span[contains(text(),'-')]
					//lDriver.findElement(By.xpath("//*[contains(@id,'Slider')]//li[1]//*[contains(@class,'Details')]//span[regexp:test(text()='[0-1000]+-+[0-1000]+-[0-10000]')]"));
					
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Device Navigation Bar for Individual Device", "Displayed", "NOT Displayed", true);
			}
			
			
			//Validate header should show 'Current Plan' text along with 'Plan Details' label
			Boolean bTxtCurrentPlanInHeader = UI.WaitForObject(oR_MobileShareComparisionTool.txtCurrentPlanInHeader, 30);
			Report.ValidationPoint(testContext.getName(), "Validate header should show 'Current Plan' text", "true", bTxtCurrentPlanInHeader.toString(), true);
			
			Boolean bTxtPlanDetailsInHeader = UI.WaitForObject(oR_MobileShareComparisionTool.txtPlanDetailsInHeaderCurrentPlanSection, 30);
			Report.ValidationPoint(testContext.getName(), "Validate header should show 'Plan Details' label", "true", bTxtPlanDetailsInHeader.toString(), true);
			
			
			//Validate sub heading as 'Combined Average Usage(past 3 months)
			try
			{
				WebElement elmUsage = lDriver.findElement(By.xpath("//*[@id='cpcSection']//*[contains(text(),'Usage')]"));
				WebElement elmThreeMonthAverage = lDriver.findElement(By.xpath("//*[@id='cpcSection']//*[contains(text(),'Three-Month Average:')]"));
				
				if(elmUsage.isDisplayed() && elmThreeMonthAverage.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(), "Validate sub heading as 'Combined Average Usage(past 3 months)", "Displayed", "Displayed", true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Validate sub heading as 'Combined Average Usage(past 3 months)", "Displayed", "NOT Displayed", true);
			}
			
			
			//Verify In each section all CTN should be display
			try
			{
				//Three sections header are extracted
				List<WebElement> lstAllSections = lDriver.findElements(By.xpath("//*[@id='cpcSection']//*[contains(@onclick,'Content')]"));
				
				for(int cnt=0 ; cnt<lstAllSections.size();cnt++)
				{
					//Class attribute is taken to check whether that section is expanded or not
					String sClassName = lstAllSections.get(cnt).getAttribute("class");
					
					if(sClassName.contains("active"))
					{
						//it means section is already expanded so do nothing
					}
					else
					{
						//expand it
						lstAllSections.get(cnt).click();
						Thread.sleep(5000);
					}
					
					try
					{
						String sCurrentSectionText = lDriver.findElement(By.xpath("//*[@id='cpcSection']//*[contains(@class,'active')]")).getText();
						if(sCurrentSectionText.contains("TEXT"))
						{
							// if section is 'Text section' then dont check for CTNs only check for Talk and Data
						}
						else
						{
							int sCTNcountUnderExpandedSection=0;
							System.out.println("arrCTN.length: "+arrCTN.length);
							for(int cnt2=0 ; cnt2< arrCTN.length ; cnt2++)
							{
								try
								{
									System.out.println("CTN "+(cnt2+1)+": "+arrCTN[cnt2]);
									WebElement elmCTN = lDriver.findElement(By.xpath("//*[@id='cpcSection']//div[@class='content' and contains(@style,'block')]//span[text()='"+arrCTN[cnt2]+"']"));
									if(elmCTN.isDisplayed())
									{
										sCTNcountUnderExpandedSection = sCTNcountUnderExpandedSection+1;
									}
								}
								catch(Exception e)
								{
									
								}
							}
							
							if(sCTNcountUnderExpandedSection==arrCTN.length)
							{
								Report.ValidationPoint(testContext.getName(), "Validate all CTNs are displayed in current expanded section", "Displayed", "Displayed", true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate all CTNs are displayed in current expanded section", "Displayed", "NOT Displayed", true);
							}
						}
					}
					catch(Exception e)
					{
						Report.TestPoint(testContext.getName(), "Verify In each section all CTN should be display", "Displayed", "Expanded section NOT displayed", true);
					}
					
					Thread.sleep(5000);
					//Collapse Expanded section
					lstAllSections.get(cnt).click();
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify In each section all CTN should be display", "Displayed", "Sections are NOT displayed", true);
			}
			
			//Add Device button should be displayed 
			Boolean bAddDeviceButton = UI.WaitForObject(oR_MobileShareComparisionTool.btnAddDevice, 30);
			Report.TestPoint(testContext.getName(), "Verify Add Device button", "true", bAddDeviceButton.toString(), true);
			
			//Click on add Device button.
			oR_MobileShareComparisionTool.btnAddDevice.click();
			
			//Verify A new dialog box should be open to add Devices
			if(oR_MobileShareComparisionTool.txtAddDeviceOnDialogBox.isDisplayed())
			{
				Report.ValidationPoint(testContext.getName(), "Verify new dialog box has been opened", "Opened", "Opened", true);
				
				//Verify Devices are available on Device selection dropdown
				//Select Smartphone - contract with 3 GB Usage 
				
				Thread.sleep(5000);
				
				//Select Smartphone - contract with 3 GB Usage
				Boolean bUsageInputBox = UI.WaitForObject(oR_MobileShareComparisionTool.edtInputBox, 30);
				oR_MobileShareComparisionTool.edtInputBox.clear();
				oR_MobileShareComparisionTool.edtInputBox.sendKeys("3");
				Report.TestPoint(testContext.getName(), "Select Smartphone - contract with 3 GB Usage", "true", bUsageInputBox.toString(), true);
				
				
				//click on Add button
				Boolean bAdd = UI.WaitForObject(oR_MobileShareComparisionTool.btnAddOnDialogBox, 30);
				Report.TestPoint(testContext.getName(), "Click Add button", "true", bAdd.toString(), true);
				oR_MobileShareComparisionTool.btnAddOnDialogBox.click();
				Thread.sleep(5000);
				
				//Verify Message - 'Device(s) added since last mobile share recommendation= (1) devices added - Smartphone - contract 3GB '
				try
				{
					WebElement elmMessage = lDriver.findElement(By.xpath("//*[contains(text(),'- Smartphone-Contract 3GB')]"));
					if(elmMessage.isDisplayed())
					{
						Report.ValidationPoint(testContext.getName(), "Verify Message - 'devices added - Smartphone - contract 3GB '", "Message displayed", "Message displayed", true);
					}
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Verify Message - 'devices added - Smartphone - contract 3GB '", "Message displayed", "Message NOT displayed", true);
				}
				
				//Click on add another device button 
				Boolean bAddAnotherDevice = UI.WaitForObject(oR_MobileShareComparisionTool.btnAddAnotherDeviceOnDialogBox, 30);
				Report.TestPoint(testContext.getName(), "Click Add another device button", "true", bAddAnotherDevice.toString(), true);
				oR_MobileShareComparisionTool.btnAddAnotherDeviceOnDialogBox.click();
				
				//Select Smartphone -contract with 1 GB Usage
				Boolean bUsageInputBox2 = UI.WaitForObject(oR_MobileShareComparisionTool.edtInputBox, 30);
				oR_MobileShareComparisionTool.edtInputBox.clear();
				oR_MobileShareComparisionTool.edtInputBox.sendKeys("1");
				Report.TestPoint(testContext.getName(), "Select Smartphone - contract with 1 GB Usage", "true", bUsageInputBox2.toString(), true);
				
				
				//click on Add button
				Boolean bAdd2 = UI.WaitForObject(oR_MobileShareComparisionTool.btnAddOnDialogBox, 30);
				Report.TestPoint(testContext.getName(), "Click Add button", "true", bAdd2.toString(), true);
				oR_MobileShareComparisionTool.btnAddOnDialogBox.click();
				Thread.sleep(4000);
				
				//Verify Message - 'Device(s) added since last mobile share comparison= (2) devices added- Smartphone-Contract 3GB	- Smartphone-Contract 1GB'
				try
				{
					WebElement elmMessage1 = lDriver.findElement(By.xpath("//*[contains(text(),'- Smartphone-Contract 3GB')]"));
					WebElement elmMessage2 = lDriver.findElement(By.xpath("//*[contains(text(),'- Smartphone-Contract 1GB')]"));
					if(elmMessage1.isDisplayed() && elmMessage2.isDisplayed())
					{
						Report.ValidationPoint(testContext.getName(), "Verify Message - 'devices added - Smartphone - contract 3GB - Smartphone-Contract 1GB'", "Message displayed", "Message displayed", true);
					}
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Verify Message - 'devices added - Smartphone - contract 3GB - Smartphone-Contract 1GB'", "Message displayed", "Message NOT displayed", true);
				}
				
				
				//Click on show recommendation button
				Boolean bBtnShowNewComparisonOnDialogBox = UI.WaitForObject(oR_MobileShareComparisionTool.btnShowNewComparisonOnDialogBox, 30);
				Report.TestPoint(testContext.getName(), "Click on show recommendation button", "true", bBtnShowNewComparisonOnDialogBox.toString(), true);
				oR_MobileShareComparisionTool.btnShowNewComparisonOnDialogBox.click();
				
				Thread.sleep(10000);
				
				//Verify Warning Message 'Added Devices not included in Current Plan' when new devices have been added
				try
				{
					WebElement elmAlertMessage = lDriver.findElement(By.xpath("//*[contains(text(),'Added Devices not included in Current Plan')]"));
					String sAlertMessage = elmAlertMessage.getText();
					
					Report.ValidationPoint(testContext.getName(), "Verify Warning Message '"+sAlertMessage+"'", "Alert displayed", "Alert displayed", true);
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Verify Warning Message", "Alert displayed", "Alert NOT displayed", true);
				}
				
				//User should be able to delete added devices
				try
				{
					List<WebElement> elmCloseButtons;
					elmCloseButtons= lDriver.findElements(By.xpath("//*[contains(@id,'Legends')]//img[@class='close']"));
					
					for(int elmCount=0 ; elmCount<elmCloseButtons.size() ; elmCount++)
					{
						//here page gets refreshed after deleting first device so need to extract close image elements once again
						elmCloseButtons= lDriver.findElements(By.xpath("//*[contains(@id,'Legends')]//img[@class='close']"));
						elmCloseButtons.get(0).click();
						Thread.sleep(4000);
						Report.ValidationPoint(testContext.getName(), "Verify User is able to delete added device no "+(elmCount+1), "Deleted", "Deleted", true);
					}
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Verify User is able to delete added devices", "Deleted", "NOT Deleted", true);
				}
				
				
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify new dialog box has been opened", "Opened", "NOT Opened", true);
			}
			
			
			//Click on Plan Details in RS Section
			Boolean bTxtPlanDetailsInHeaderMobileSharePlanSection = UI.WaitForObject(oR_MobileShareComparisionTool.txtPlanDetailsInHeaderMobileSharePlanSection, 30);
			Report.TestPoint(testContext.getName(), "Click on Plan Details button in Mobile share plan section", "true", bTxtPlanDetailsInHeaderMobileSharePlanSection.toString(), true);
			oR_MobileShareComparisionTool.txtPlanDetailsInHeaderMobileSharePlanSection.click();
			
			
			//Verify Plan Detail Page is Displayed
			Boolean bTxtMobileShareDetails = UI.WaitForObject(oR_MobileShareComparisionTool.txtMobileShareDetails, 30);
			Report.TestPoint(testContext.getName(), "Verify Plan Detail Page is Displayed", "true", bTxtMobileShareDetails.toString(), true);
			
			//Verify Cost column is Displayed
			Boolean bTxtCost = UI.WaitForObject(oR_MobileShareComparisionTool.txtCost, 30);
			Report.ValidationPoint(testContext.getName(), "Verify Cost column is Displayed", "true", bTxtCost.toString(), true);
			
			//Verify PLAN pre discount text
			Boolean bTxtPlanPreDiscount = UI.WaitForObject(oR_MobileShareComparisionTool.txtPlanPreDiscount, 30);
			Report.ValidationPoint(testContext.getName(), "Verify PLAN pre discount text is Displayed", "true", bTxtPlanPreDiscount.toString(), true);
			
			//Verify FAN discount in Green color
			Boolean bTxtFANdiscount = UI.WaitForObject(oR_MobileShareComparisionTool.txtFANdiscount, 30);
			Report.ValidationPoint(testContext.getName(), "Verify FAN discount is Displayed", "true", bTxtFANdiscount.toString(), true);
			
			//Verify FAN discount in is Green color
			try
			{
				WebElement elmFanDiscount = lDriver.findElement(By.xpath("//*[text()='FAN Discount']/parent::*"));
				
				//extract attribute 'style' to check for green color
				String sTextStyle = elmFanDiscount.getAttribute("style");
				
				if(sTextStyle.contains("color:green"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify FAN discount is Displayed in green color", "Displayed in green color", "Displayed in green color", true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify FAN discount is Displayed in green color", "Displayed in green color", "NOT Displayed in green color", true);
			}
			
			//Verify Dollar amount In green color and ‘( )’  included around the dollar amount
			
			try
			{
				lDriver.findElement(By.xpath("//*[contains(@style,'color:green')][contains(text(),'($')]"));
				
				Report.ValidationPoint(testContext.getName(), "Verify Dollar amount In green color and ‘( )’  included around the dollar amount", "Displayed", "Displayed", true);
				
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Dollar amount In green color and ‘( )’  included around the dollar amount", "Displayed", "Displayed", true);
			}
			
			//Verify PLAN w/discount text
			Boolean bTxtwDiscount = UI.WaitForObject(oR_MobileShareComparisionTool.txtwDiscount, 30);
			Report.ValidationPoint(testContext.getName(), "Verify PLAN w/discount text", "true", bTxtwDiscount.toString(), true);
			
			//Verify line item format in content section
			try
			{
				List<WebElement> elmNameWithCTN = lDriver.findElements(By.xpath("//*[contains(@class,'content')]//*[@class='bottomline']"));
				
				for(int count=0; count< elmNameWithCTN.size() ; count++)
				{
					String sText = elmNameWithCTN.get(count).getText();
					
					if(elmNameWithCTN.get(count).isDisplayed())
					{
						if(!(sText.equals("")))
						{
							//Verify CTN Name and number
							String sExpectedPattern = ".*-.*\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d.*" ;
						
							if(sText.matches(sExpectedPattern))
							{
								Report.ValidationPoint(testContext.getName(), "Verify CTN Name and number for plan no "+count, "CTN Name and number displayed", "CTN Name and number displayed", true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Verify CTN Name and number for plan no "+count, "CTN Name and number displayed", "CTN Name and number NOT displayed", true);
							}
							
							//Verify Cost in $ amount
							if(sText.contains("$"))
							{
								Report.ValidationPoint(testContext.getName(), "Verify Cost in $ amount for plan no "+count, "Displayed", "Displayed", true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Verify Cost in $ amount for plan no "+count, "Displayed", "NOT displayed", true);
							}
						}
					}
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify line item format in content section", "Displayed", "NOT displayed", true);
			}
			
			//Verify Input text box on bottom of the plan
			Boolean bUserDiscountrs = UI.WaitForObject(oR_MobileShareComparisionTool.edtUserDiscountrs, 30);
			Report.ValidationPoint(testContext.getName(), "Verify Input text box on bottom of the plan", "true", bUserDiscountrs.toString(), true);
			
			//Verify value of FAN discount will changed based on the % value entered in Input Text box
			oR_MobileShareComparisionTool.edtUserDiscountrs.clear();
			oR_MobileShareComparisionTool.edtUserDiscountrs.sendKeys(sDiscountAmountToEnterInInputBox);
			Report.ValidationPoint(testContext.getName(), "Enter discount amount "+sDiscountAmountToEnterInInputBox+" in input box", "Entered", "Entered", true);
			Thread.sleep(4000);
			
			//Extract amount of input box
			String sInputBoxValue = oR_MobileShareComparisionTool.edtUserDiscountrs.getAttribute("value");
			
			//Extract discount amount displaying in green
			try
			{
				String sDiscountAmount = lDriver.findElement(By.xpath("//*[@id='greendiscount']")).getText();
				
				if(sDiscountAmount.equals(sInputBoxValue))
				{
					Report.ValidationPoint(testContext.getName(), "Verify value of FAN discount will changed based on the % value entered in Input Text box", "Discount changed", "Discount changed", true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify value of FAN discount will changed based on the % value entered in Input Text box", "Discount changed", "Discount NOT changed", true);
			}
			
			//End of code to //Verify value of FAN discount will changed based on the % value entered in Input Text box
			
			//Close plan details popup
			Boolean bBtnCloseOnPlanDetailsPopup = UI.WaitForObject(oR_MobileShareComparisionTool.btnCloseOnPlanDetailsPopup, 30);
			Report.ValidationPoint(testContext.getName(), "Close plan details popup", "true", bBtnCloseOnPlanDetailsPopup.toString(), true);
			oR_MobileShareComparisionTool.btnCloseOnPlanDetailsPopup.click();
			
			
			}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
	
	/**************************************************************
	 * Function Name - VerifyEventIRU_Discount_HyperlinkOnAgentActivity 
	 * Description- This function clicks on apply discounts link on Mobile Share Recommender Tool page
	 * 				then navigates to agent activity page and verifies whether 'MSRT_LS_IRU_DISCOUNT_HYPERLINK' event
	 * 				is displayed in activity list.  If it is found that means the event of click on Apply Details hyperlink is logged in Database
	 * Parameters - final ITestContext testContext
	 * Date created -14th April 2015
	 * Developed by - 	Sneha Pansare
	 * Last Modified By -
	 * Last Modified Date -
	 ***************************************************************/
	 //CSR04578 	
	
	public static void VerifyEventIRU_Discount_HyperlinkOnAgentActivity(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
		OR_MobileShareComparisionTool oR_MobileShareComparisionTool = PageFactory.initElements(lDriver, OR_MobileShareComparisionTool.class);
		OR_AgentActivity oR_AgentActivity = PageFactory.initElements(lDriver, OR_AgentActivity.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
        String sFromDate = IO.GetParamVal(sTestParams, "From_Date"); 
        String sToDate = IO.GetParamVal(sTestParams, "To_Date");
        
		try
		{
			String sBAN = IO.GetParamVal(sTestParams, "BAN");
			
			//Click on the 'Mobile Share Recommender' tab. On CSR Landing Page
			Boolean bMobileShareComparisonTab = UI.WaitForObject(oR_Dashboard.btnMobileShareComparison, 30);
			Report.TestPoint(testContext.getName(), "Click on the 'Mobile Share Recommender tab", "true", bMobileShareComparisonTab.toString(), true);
			oR_Dashboard.btnMobileShareComparison.click();
			
			//switch to new opened window
			 
			  String parentHandle = lDriver.getWindowHandle(); // get the current window handle
			
			  Thread.sleep(5000);
			  for (String winHandle : lDriver.getWindowHandles()) {
			      lDriver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle 
			  }
			  
			//Verify Redirected to Mobile Share Recommender Tool Search Page
			Boolean bMobileShareHeading = UI.WaitForObject(oR_MobileShareComparisionTool.txtMobileShareHeading, 30);
			Report.TestPoint(testContext.getName(), "Verify Redirection to Mobile Share Recommender Tool Search Page", "true", bMobileShareHeading.toString(), true);
			
			//Enter wireless BAN/CTN on Search Page
			Boolean bEnterCTN = UI.WaitForObject(oR_MobileShareComparisionTool.edtCTN, 30);
			Report.TestPoint(testContext.getName(), "Enter wireless BAN/CTN", "true", bEnterCTN.toString(), true);
			oR_MobileShareComparisionTool.edtCTN.sendKeys(sBAN);
			
			//Click submit
			Boolean bSubmit = UI.WaitForObject(oR_MobileShareComparisionTool.btnSubmit, 30);
			Report.TestPoint(testContext.getName(), "Click submit", "true", bSubmit.toString(), true);
			oR_MobileShareComparisionTool.btnSubmit.click();
			
			//Verify Navigation to to Mobile Share Recommender Tool Landing Page
			Boolean bTxtMobileShareHeading = UI.WaitForObject(oR_MobileShareComparisionTool.txtMobileShareHeading, 30);
			Report.TestPoint(testContext.getName(), "Verify Navigation to to Mobile Share Recommender Tool Landing Page", "true", bTxtMobileShareHeading.toString(), true);
			
			//Click on Apply discounts
			Boolean bLnkApplyDiscount = UI.WaitForObject(oR_MobileShareComparisionTool.lnkApplyDiscount, 30);
			Report.TestPoint(testContext.getName(), "Click on Apply discounts link", "true", bLnkApplyDiscount.toString(), true);
			oR_MobileShareComparisionTool.lnkApplyDiscount.click();
			
			//Verify Plan Detail page after clicking on link
			Boolean bTxtCurrentPlanDetails = UI.WaitForObject(oR_MobileShareComparisionTool.txtCurrentPlanDetails, 30);
			Report.TestPoint(testContext.getName(), "Verify Plan Detail page after clicking on link", "true", bTxtCurrentPlanDetails.toString(), true);
			
			//Close Plan Detail popup
			Boolean bBtnCloseOnPlanDetailsPopup = UI.WaitForObject(oR_MobileShareComparisionTool.btnCloseOnPlanDetailsPopup, 30);
			Report.TestPoint(testContext.getName(), "Close Plan Detail popup", "true", bBtnCloseOnPlanDetailsPopup.toString(), true);
			oR_MobileShareComparisionTool.btnCloseOnPlanDetailsPopup.click();
			
			//close current window of mobile share tool
			lDriver.close();
			
			//go back to CSR admin tool landing page
			lDriver.switchTo().window(parentHandle);
			
			//click on Agent Activity
			Boolean bBtnAgentActivity = UI.WaitForObject(oR_Dashboard.btnAgentActivity, 30);
			Report.TestPoint(testContext.getName(), "click on Agent Activity", "true", bBtnAgentActivity.toString(), true);
			oR_Dashboard.btnAgentActivity.click(); 
			 
			
			//Enter attUID
			Boolean bEdtATT_UID = UI.WaitForObject(oR_AgentActivity.edtATT_UID, 30);
			Report.TestPoint(testContext.getName(), "Enter attUID", "true", bEdtATT_UID.toString(), true);
			oR_AgentActivity.edtATT_UID.clear();
			oR_AgentActivity.edtATT_UID.sendKeys(oReadEnvProp.AttuidMyATTZone());
			
			//Select start date
			Boolean bEdtStartDate = UI.WaitForObject(oR_AgentActivity.edtStartDate, 30);
			Report.TestPoint(testContext.getName(), "Verify start date input box", "true", bEdtStartDate.toString(), true);
			oR_AgentActivity.edtStartDate.click();
			Thread.sleep(4000);
			//this will open calender 
			try
			{
				WebElement eStartDateLink = lDriver.findElement(By.xpath("//*[contains(@class,'calendar_container')]//*[contains(@class,'dates')]//li[contains(text(),'"+sFromDate+"')]"));
				Report.TestPoint(testContext.getName(), "Select start date", "Selected", "Selected", true);
				eStartDateLink.click();
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Select start date", "Selected", "Provided start date DOES NOT exist", true);
			}
				
			
			//Select end date
			Boolean bEdtEndDate = UI.WaitForObject(oR_AgentActivity.edtEndDate, 30);
			Report.TestPoint(testContext.getName(), "Verify end date input box", "true", bEdtEndDate.toString(), true);
			oR_AgentActivity.edtEndDate.click();
			Thread.sleep(4000);
			//this will open calender 
			try
			{
				WebElement eEndDateLink = lDriver.findElement(By.xpath("//*[contains(@class,'calendar_container')]//*[contains(@class,'dates')]//li[contains(text(),'"+sToDate+"')]"));
					
				Report.TestPoint(testContext.getName(), "Select end date", "Selected", "Selected", true);
				eEndDateLink.click();
					
					
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Select end date", "Selected", "Provided end date DOES NOT exist", true);
			}
			
			
			Thread.sleep(20000);
			//Click on search button
			Boolean bBtnSearch = UI.WaitForObject(oR_AgentActivity.btnSearch, 30);
			Report.TestPoint(testContext.getName(), "Click on search button", "true", bBtnSearch.toString(), true);
			oR_AgentActivity.btnSearch.click();
			Thread.sleep(15000);
			
			//Verify MSRT_LS_IRU_DISCOUNT_HYPERLINK event. it means click on link 'apply discounts' is logged in Database
			try
			{
				WebElement eEvent = lDriver.findElement(By.xpath("//*[contains(text(),'04/"+sToDate+"/2015')]/parent::*/child::*[contains(text(),'MSRT_LS_IRU_DISCOUNT_HYPERLINK')]"));
				if(eEvent.isDisplayed())
				{
					Report.TestPoint(testContext.getName(), "Verify MSRT_LS_IRU_DISCOUNT_HYPERLINK event", "Event is present in activity list.click on link 'apply discounts' is logged in Database", "Event is present in activity list.click on link 'apply discounts' is logged in Database", true);
				}
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Verify MSRT_LS_IRU_DISCOUNT_HYPERLINK event", "Event is present in activity list.click on link 'apply discounts' is logged in Database", "Event NOT present in activity list.click on link 'apply discounts' is NOT logged in Database", true);
			}
			
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
	
	/**************************************************************
	 * Function Name - VerifySendSMSButtonForCBR()
	 * Description - This testcase is to validate that 'Send SMS' button is NOT greyed out if CBR is in pending validation status(empty) and 'Send SMS' button is greyed out if CBR is NOT in pending validation status
	 * Parameters - DSL_Memeber_ID
	 * Date created - 15th April 2015
	 * Developed by - Clint John
	 * Last Modified By -
	 * Last Modified Date - 
	 ***************************************************************/
	//CSR04668
	public static void VerifySendSMSButtonForCBR(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			//Parameter declaration
			String sDSLMemberID = IO.GetParamVal(sTestParams, "DSL_Memeber_ID");
			
			//Verify the user lands on new page with header 'MyAT&T Zone'
			boolean bHeader = UI.WaitForObject(oR_Dashboard.imgMyATTZoneHeader, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate that user lands on new page with header 'MyAT&T Zone'", "true", String.valueOf(bHeader), true);
			//Verify that user is able to view and access all the below options in the search menu: ATT Access ID, Wireless, Uverse, Wireline, DSL
			boolean bAccessID = UI.WaitForObject(oR_Dashboard.elm_ATTAccessID, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that 'ATT Access ID' option is available in Search menu", "true", String.valueOf(bAccessID), true);
			boolean bWireless = UI.WaitForObject(oR_Dashboard.txtWireless, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that 'Wireless' option is available in Search menu", "true", String.valueOf(bWireless), true);
			boolean bUverse = UI.WaitForObject(oR_Dashboard.elm_Uverse, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that 'Uverse' option is available in Search menu", "true", String.valueOf(bUverse), true);
			boolean bWireline = UI.WaitForObject(oR_Dashboard.elm_Wireline, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that 'Wireline' option is available in Search menu", "true", String.valueOf(bWireline), true);
			boolean bDSL = UI.WaitForObject(oR_Dashboard.elm_DSL, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that 'DSL' option is available in Search menu", "true", String.valueOf(bDSL), true);
			//Select Member ID option from DSL
			oR_Dashboard.elm_DSL.click();
			boolean bMemberID = UI.WaitForObject(oR_Dashboard.elm_MemberID, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that 'Member ID' option is available under DSL option in Search menu", "true", String.valueOf(bMemberID), true);
			oR_Dashboard.elm_MemberID.click();
			//Verify that DSL Member ID Search box is displayed
			boolean bEnterDSLMemberID = UI.WaitForObject(oR_Dashboard.edtDSLMemberID, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that edit boc to enter DSL Member ID is displayed", "true", String.valueOf(bEnterDSLMemberID), true);
			//insert DSL Member ID
			oR_Dashboard.edtDSLMemberID.sendKeys(sDSLMemberID);
			Report.OperationPoint(testContext.getName(), "Operational - Entered value to DSL Member ID Search box. DSL Member ID entered is : "+sDSLMemberID);
			//click on Search button
			oR_Dashboard.btnSearchDSLMemberID.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Search' button ");
			
			//Verify a pop-up is displayed to select SLID ID		
			boolean bSelectLoginIDForm = UI.WaitForObject(oR_Dashboard.elm_SelectLoginIDForm, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate a Pop-up/Form to select login ID is displayed", "true", String.valueOf(bSelectLoginIDForm), true);
			boolean bSelectLoginID = UI.WaitForObject(oR_Dashboard.txtSelectLoginIDHeading, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Confirm the form is for Select Login ID", "true", String.valueOf(bSelectLoginID), true);
			//Select any of the radio-button
			oR_Dashboard.radSelectSlid.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on radio button, Login ID selected");
			oR_Dashboard.btnContinue.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Continue' button");
			//Verify mirror myatt mobile app, mirror myatt web app ,subscriber management ,customer activity buttons are present
			boolean bMobileApp= UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(),"Validate that button 'Mirror MyAT&T Mobile App' is displayed", "true", String.valueOf(bMobileApp), true);
			boolean bMyATTWeb= UI.WaitForObject(oR_Dashboard.btnMirrorMyATTWeb, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(),"Validate that button 'Mirror MyAT&T Web' is displayed", "true", String.valueOf(bMyATTWeb), true);
			boolean bSubManagement= UI.WaitForObject(oR_Dashboard.btnSubscriberManagement, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(),"Validate that button 'Subscriber Management' is displayed", "true", String.valueOf(bSubManagement), true);
			boolean bCustomerAct= UI.WaitForObject(oR_Dashboard.btnCustomerActivity, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(),"Validate that button 'Customer Activity' is displayed", "true", String.valueOf(bCustomerAct), true);
			oR_Dashboard.btnSubscriberManagement.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Subscriber Management button");
			
			//Verify view details link for CBR is displayed
			boolean bViewDetails= UI.WaitForObject(oR_Dashboard.lnkViewDetails, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(),"Validate that 'View Details' link is displayed for PMID", "true", String.valueOf(bViewDetails), true);
			oR_Dashboard.lnkViewDetails.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on View Details link");
			//Verify if PopUp for CBR is displayed 
			boolean bCBRPopUp= UI.WaitForObject(oR_Dashboard.elm_CBRPopUp, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(),"Validate that a Pop-Up/ modal window is opened after clicking View Details link", "true", String.valueOf(bCBRPopUp), true);
			//Verify CBR validation status and Send SMS button
			//If CBR validation is not done (CheckBox unchecked) then validate Send SMS button is enable and verify the message
			if(oR_Dashboard.imgDeviceValidatedUnchecked.isEnabled())
			{
				Report.ValidationPoint(testContext.getName(),"Validate that Device validation checkbox is unchecked", "true", "true", true);
				//validate that Send SMS button is Enabled
				if(oR_Dashboard.btnSendSMSEnabled.isEnabled())
				{
					Report.ValidationPoint(testContext.getName(),"Validate that Send SMS button is Enabled", "true", "true", true);
					oR_Dashboard.btnSendSMSEnabled.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Send SMS' button");
					//Verify new modal window with a warning about text message rates, with cancel and ok button will get opened
					//boolean bCBRSMSPopUp= UI.WaitForObject(oR_Dashboard.elm_CBRSMSPopUp, UI.iObjTimeOut);
					if(oR_Dashboard.elm_CBRSMSPopUp.isEnabled())
					{
						//Report.ValidationPoint(testContext.getName(),"Validate that new modal window with a warning about text message rates is opened", "true", String.valueOf(bCBRSMSPopUp), true);
						Report.ValidationPoint(testContext.getName(),"Validate that new modal window with a warning about text message rates is opened", "true", "true", true);

						//Check if Cancel and Ok button is displayed
						boolean bCancel = UI.WaitForObject(oR_Dashboard.btnCancelSMSPopup, UI.iObjTimeOut);
						Report.ValidationPoint(testContext.getName(),"Validate Cancel button is present for the new modal window", "true", String.valueOf(bCancel), true);
						boolean bOK = UI.WaitForObject(oR_Dashboard.btnOKSMSPopup, UI.iObjTimeOut);
						Report.ValidationPoint(testContext.getName(),"Validate OK button is present for the new modal window", "true", String.valueOf(bOK), true);
						oR_Dashboard.btnOKSMSPopup.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on OK button");
					}else
					{
						Report.ValidationPoint(testContext.getName(),"New modal window with a warning about text message rates is NOT opened", "true","false", true);
					}
					
				}else
				{
					Report.ValidationPoint(testContext.getName(),"Validate that 'Send SMS' should not be greyed out if Device validation checkbox is unchecked", "true", "false", true);
				}

			}else if(oR_Dashboard.imgDeviceValidatedChecked.isEnabled())
			{
				Report.ValidationPoint(testContext.getName(),"Validate that Device validation checkbox is checked (NOT blank)", "true", "true", true);
				//Verify Send SMS button is disabled/Greyed out
				if(oR_Dashboard.btnSendSMSDisabled.isEnabled())
				{
					Report.ValidationPoint(testContext.getName(),"Validate that Send SMS button is Disabled (greyed out)", "true", "true", true);
				}else
				{
					Report.ValidationPoint(testContext.getName(),"Validate that 'Send SMS' button is Disabled (greyed out)", "true", "false", true);
				}
			}else
			{
				Report.ValidationPoint(testContext.getName(),"Error in validating checkbox for device validation option", "true", "false", true);
			}

		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}		
	}

}
		
//*[contains(text(),'04/13/2015')]/parent::*/child::*[contains(text(),'MSRT_LS_IRU_DISCOUNT_HYPERLINK')]
