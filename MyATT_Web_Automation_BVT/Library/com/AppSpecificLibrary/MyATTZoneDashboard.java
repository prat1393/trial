package com.AppSpecificLibrary; 

import java.awt.AWTException; 
import java.awt.HeadlessException; 
import java.awt.Rectangle; 
import java.awt.Robot; 
import java.awt.Toolkit; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import java.text.DecimalFormat; 
import java.text.SimpleDateFormat; 
import java.util.Calendar; 
import java.util.Date; 
import java.util.Hashtable; 
import java.util.List; 
import java.util.concurrent.TimeUnit; 

import javax.imageio.ImageIO; 
import org.openqa.selenium.Alert; 
import org.openqa.selenium.By; 


import org.openqa.selenium.JavascriptExecutor; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.interactions.Action; 
import org.openqa.selenium.interactions.Actions; 
import org.openqa.selenium.support.PageFactory; 
import org.openqa.selenium.support.ui.ExpectedConditions; 
import org.openqa.selenium.support.ui.Select; 
import org.openqa.selenium.support.ui.WebDriverWait; 
import org.testng.ITestContext; 

import com.OR.MyATTZone.OR_Dashboard; 
import com.OR.MyATTZone.OR_MobileShareComparisionTool; 
import com.OR.MyATTZone.OR_MyATTZone; 
import com.OR.MyATTZone.OR_Profile_zone; 
import com.SupportingFiles.IO; 
import com.SupportingFiles.LaunchAndLogout; 
import com.SupportingFiles.ReadEnvPropFile; 
import com.SupportingFiles.Report; 
import com.SupportingFiles.UI; 
import com.SupportingFiles.LaunchAndLogout;

public class MyATTZoneDashboard {

	/**************************************************************
	 * Function Name - checkDifferentAccountTypesAndVerifyDetails 
	 * Description- This function Verifies and clicks -
	 * 				- AT&T access ID and verifies all details of AT&T access ID
	 * 				- either wireless CTN or wireless BAN, and verifies all details of selected option
	 * 				- either uverse member id or uverse BAN, and verifies all details of selected option 
	 * 				- Wireline BTN, and  verifies all details of Wireline BTN
	 * Parameters - final ITestContext testContext
	 * Date created -11th July 2017
	 * Developed by - 	Monica Mohabansi
	 * Last Modified By -
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	//CSR2549,CSR3671,CSR2496,CSR2600,CSR2494,CSR2495

	public static void checkDifferentAccountTypesAndVerifyDetails(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{

		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);

			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			String sAccessID = IO.GetParamVal(sTestParams, "AccessID");
			String sWirelessCTN = IO.GetParamVal(sTestParams, "Wireless_CTN");
//			String sWirelessBAN = IO.GetParamVal(sTestParams, "Wireless_BAN");
			String sUverseMemberID = IO.GetParamVal(sTestParams, "Uverse_Member_ID");
//			String sUverseBAN = IO.GetParamVal(sTestParams, "Uverse_BAN");
			String sWirelineBTN = IO.GetParamVal(sTestParams, "WirelineBTN");

			myATT_Zone.Validate_AccessCustomerAccounts(testContext);
			//select AT&T access id from search menu
			boolean bElmATTAccessID = UI.WaitForObject(oR_Dashboard.elm_ATTAccessID, 10,lDriver);
			Report.TestPoint(testContext.getName(),"Verify AT&T access id from search menu", "true", String.valueOf(bElmATTAccessID), true);
		
			/**********************************************************ACCCESS ID************************************************************************************************/

			//Hover over Access Id 

			Actions action = new Actions(lDriver);
			try
			{
				action.moveToElement(oR_Dashboard.elm_ATTAccessID).build().perform();

				Thread.sleep(1000);
				Report.TestPoint(testContext.getName(),"Hover over Access Id", "Hovered", "Hovered", true);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(),"Hover over Access Id", "Hovered", "Failed to hover", true);
			}

			//Move to Access Id

			try
			{
				Thread.sleep(2000);
				if(oR_Dashboard.elm_AccessID.isDisplayed())
				{
					try
					{
						action.moveToElement(oR_Dashboard.elm_AccessID).build().perform();

						Thread.sleep(4000);
						Report.TestPoint(testContext.getName(),"Hover over submenu Access Id", "Hovered", "Hovered", true);
						oR_Dashboard.elm_AccessID.click();
						Thread.sleep(4000);
						Report.TestPoint(testContext.getName(),"Click on submenu Access Id", "Clicked", "Clicked", true);
					}
					catch(Exception e)
					{
						Report.TestPoint(testContext.getName(),"Hover over Access Id", "Hovered", "Failed to hover", true);
					}
				}
				else
				{
					oR_Dashboard.elm_AccessID.click();
					Thread.sleep(5000);
					Report.TestPoint(testContext.getName(),"Click AT&T access id from search menu", "Clicked", "Clicked", true);


				}
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(),"Click AT&T access id from search menu", "Clicked", "FAILED to click", true);
			}

			//Verify AT&T access id field is displayed
			boolean bTxtATTaccessId = UI.WaitForObject(oR_Dashboard.txtATTaccessId, 10,lDriver);
			Report.TestPoint(testContext.getName(),"Verify AT&T access id field is displayed", "true", String.valueOf(bTxtATTaccessId), true);

			//Verify AT&T access id input box is displayed
			boolean bEdtATTaccessIdSearchField = UI.WaitForObject(oR_Dashboard.elm_ATTAccessIDTxtField, 10,lDriver);
			Report.TestPoint(testContext.getName(),"Verify AT&T access id input box is displayed", "true", String.valueOf(bEdtATTaccessIdSearchField), true);

			//Verify search button
			boolean bBtnSearch = UI.WaitForObject(oR_Dashboard.btnSearch, 10,lDriver);
			Report.TestPoint(testContext.getName(),"Verify search button", "true", String.valueOf(bBtnSearch), true);

			//Enter the Access Id and press Search button
			oR_Dashboard.elm_ATTAccessIDTxtField.sendKeys(sAccessID);
			Report.TestPoint(testContext.getName(),"Enter the Access Id", "Entered", "Entered", true);

			oR_Dashboard.btnSearch.click();
			Thread.sleep(4000);

			//Select CTN/access id from popup
			try
			{
//				lDriver.findElement(By.xpath("//input[@type='radio']")).click();
				oR_Dashboard.radSelectSlid.click();

				Thread.sleep(2000);
				Report.TestPoint(testContext.getName(),"Select CTN/access id with radio button from popup", "Selected", "Selected", true);
				//Click on continue button
				oR_Dashboard.btnContinue.click();
				Thread.sleep(4000);
			}
			catch(Exception e)
			{

			}

			//Verify all the details about the access Id are displayed properly
			try
			{
				lDriver.findElement(By.xpath("//*[contains(text(),'AT&T Login ID')]"));
				lDriver.findElement(By.xpath("//*[contains(text(),'Name')]"));
				
				if(oR_Dashboard.btnSubscriberManagement.isDisplayed())
				{
					JavascriptExecutor jse = (JavascriptExecutor)lDriver;
					jse.executeScript("window.scrollBy(0,250)", "");
					
					Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile", UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30, lDriver).toString(), "true", true);
					Report.ValidationPoint(testContext.getName(), "Button Subscriber Management", UI.WaitForObject(oR_Dashboard.btnSubscriberManagement, 2, lDriver).toString(), "true", true);
					Report.ValidationPoint(testContext.getName(), "Button Customer Activity", UI.WaitForObject(oR_Dashboard.btnCustomerActivity, 2, lDriver).toString(), "true", true);

					Report.ValidationPoint(testContext.getName(),"Verify all the details about the access Id are displayed properly", "Displayed", "Displayed", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(),"Verify all the details about the access Id are displayed properly", "Displayed", "NOT Displayed", true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(),"Verify all the details about the access Id are displayed properly", "Displayed", "NOT Displayed", true);
			}
			/************************************ACCESS ID ENDS **************************************************************************/
			/*//Click on account tab again
			boolean bLnkAccounts = UI.WaitForObject(oR_Dashboard.lnkAccounts, 10,lDriver);
			oR_Dashboard.lnkAccounts.click();
			Thread.sleep(9000);
			Report.TestPoint(testContext.getName(),"Click on account tab", "true", String.valueOf(bLnkAccounts), true);
*/
			/**********************************************************WIRELESS************************************************************************************************/

			//Hover over Wireless 
			boolean bTxtWireless = UI.WaitForObject(oR_Dashboard.txtWireless, 10, lDriver);
			Report.TestPoint(testContext.getName(),"Verify Wireless tab", "true", String.valueOf(bTxtWireless), true);

			action = new Actions(lDriver);
			try
			{
				action.moveToElement(oR_Dashboard.txtWireless).build().perform();

				Thread.sleep(4000);
				Report.TestPoint(testContext.getName(),"Hover over Wireless", "Hovered", "Hovered", true);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(),"Hover over Wireless", "Hovered", "Failed to hover", true);
			}

			//Select Wireless BAN/CTN
			if(!(sWirelessCTN.equals("NA")))
			{
				System.out.println("CTN entered "+sWirelessCTN);


				//Verify CTN will be displayed in dropdown
				if(oR_Dashboard.elm_WirelessCTN.isDisplayed())
				{
					Report.TestPoint(testContext.getName(),"Verify CTN displayed in dropdown", "Displayed", "Displayed", true);
				}
				else
				{
					Report.TestPoint(testContext.getName(),"Verify CTN displayed in dropdown", "Displayed", "NOT Displayed", true);
				}

				//Select CTN from the Wireless drop-down menu         txtMobileNumber  edtWirelessCTN
				oR_Dashboard.elm_WirelessCTN.click();
				Thread.sleep(5000);
				Report.TestPoint(testContext.getName(),"Select CTN from the Wireless drop-down menu", "Selected", "Selected", true);

				//Verify Mobile Number field is displayed
				boolean bTxtMobileNumber = UI.WaitForObject(oR_Dashboard.txtMobileNumber, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify Mobile Number field is displayed", "true", String.valueOf(bTxtMobileNumber), true);

				//Verify Mobile Number input box is displayed
				boolean bEdtWirelessCTN = UI.WaitForObject(oR_Dashboard.edtWirelessCTN, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify Mobile Number input box is displayed", "true", String.valueOf(bEdtWirelessCTN), true);

				//Verify search button
				boolean bBtnSearch1 = UI.WaitForObject(oR_Dashboard.btnSearch, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify search button", "true", String.valueOf(bBtnSearch1), true);

				//Enter mobile number in input box
				oR_Dashboard.edtWirelessCTN.sendKeys(sWirelessCTN);
				Thread.sleep(2000);
				Report.TestPoint(testContext.getName(),"Enter mobile number in input box", "Entered", "Entered", true);

				oR_Dashboard.btnSearch.click();
				Thread.sleep(4000);

				//Select CTN/access id from popup
				try
				{
//					lDriver.findElement(By.xpath("//input[@type='radio'][contains(@onclick,'CTN')]")).click();
					oR_Dashboard.radSelectSlid.click();
					Thread.sleep(2000);
					Report.TestPoint(testContext.getName(),"Select CTN/access id with radio button from popup", "Selected", "Selected", true);
					//Click on continue button
					oR_Dashboard.btnContinue.click();
					Thread.sleep(4000);
				}
				catch(Exception e)
				{

				}



				//Verify all the details about the Wireless CTN are displayed properly
				try
				{
					WebElement elmMobileNumber = lDriver.findElement(By.xpath("//*[text()='Mobile Number']"));
					WebElement elmMobileName = lDriver.findElement(By.xpath("//*[text()='Mobile Name']"));
					
					if(elmMobileNumber.isDisplayed() && elmMobileName.isDisplayed() && oR_Dashboard.btnSubscriberManagement.isDisplayed())
					{
						JavascriptExecutor jse = (JavascriptExecutor)lDriver;
						jse.executeScript("window.scrollBy(0,250)", "");
						
						Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile", UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30, lDriver).toString(), "true", true);
						Report.ValidationPoint(testContext.getName(), "Button Subscriber Management", UI.WaitForObject(oR_Dashboard.btnSubscriberManagement, 2, lDriver).toString(), "true", true);
						Report.ValidationPoint(testContext.getName(), "Button Customer Activity", UI.WaitForObject(oR_Dashboard.btnCustomerActivity, 2, lDriver).toString(), "true", true);

						Report.TestPoint(testContext.getName(),"Verify all the details about the Wireless CTN are displayed properly", "Displayed", "Displayed", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(),"Verify all the details about the Wireless CTN are displayed properly", "Displayed", "NOT Displayed", true);
					}
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(),"Verify all the details about the Wireless CTN are displayed properly", "Displayed", "NOT Displayed", true);
				}

			}
			/*else if(!(sWirelessBAN.equals("NA")))
			{
				System.out.println("BAN entered "+sWirelessBAN);

				//Verify BAN will be displayed in dropdown
				if(oR_Dashboard.elm_WirelessBAN.isDisplayed())
				{
					Report.TestPoint(testContext.getName(),"Verify BAN displayed in dropdown", "Displayed", "Displayed", true);
				}
				else
				{
					Report.TestPoint(testContext.getName(),"Verify BAN displayed in dropdown", "Displayed", "NOT Displayed", true);
				}

				//Select BAN from the Wireless drop-down menu         txtMobileNumber  edtWirelessCTN
				oR_Dashboard.elm_WirelessBAN.click();
				Thread.sleep(5000);
				Report.TestPoint(testContext.getName(),"Select BAN from the Wireless drop-down menu", "Selected", "Selected", true);

				//Verify Wireless account Number field is displayed
				boolean bElm_WirelessOrUnifiedAccountNumber = UI.WaitForObject(oR_Dashboard.elm_WirelessOrUnifiedAccountNumber, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify Wireless account Number field is displayed", "true", String.valueOf(bElm_WirelessOrUnifiedAccountNumber), true);

				//Verify Wireless account Number input box is displayed
				boolean bEdtWirelessBAN = UI.WaitForObject(oR_Dashboard.edtWirelessBAN, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify Wireless account Number input box is displayed", "true", String.valueOf(bEdtWirelessBAN), true);

				//Verify search button
				boolean bBtnSearch1 = UI.WaitForObject(oR_Dashboard.btnSearch, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify search button", "true", String.valueOf(bBtnSearch1), true);

				//Enter Wireless BAN in input box
				oR_Dashboard.edtWirelessBAN.sendKeys(sWirelessBAN);
				Thread.sleep(2000);
				Report.TestPoint(testContext.getName(),"Enter Wireless BAN in input box", "Entered", "Entered", true);

				oR_Dashboard.btnSearch.click();
				Thread.sleep(4000);

				//Select CTN/access id from popup
				try
				{
					lDriver.findElement(By.xpath("//input[@type='radio']")).click();
					Thread.sleep(2000);
					Report.TestPoint(testContext.getName(),"Select CTN/access id with radio button from popup", "Selected", "Selected", true);
					//Click on continue button
					oR_Dashboard.btnContinue.click();
					Thread.sleep(4000);
				}
				catch(Exception e)
				{

				}



				//Verify all the details about the Wireless BAN are displayed properly
				try
				{
					WebElement elmAccountNumber = lDriver.findElement(By.xpath("//*[contains(text(),'Account Number')]"));
					WebElement elmMobileNumber = lDriver.findElement(By.xpath("//*[text()='Mobile Number']"));

					if(elmAccountNumber.isDisplayed() && elmMobileNumber.isDisplayed() && oR_Dashboard.btnSubscriberManagement.isDisplayed())
					{
						Report.ValidationPoint(testContext.getName(),"Verify all the details about the Wireless BAN are displayed properly", "Displayed", "Displayed", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(),"Verify all the details about the Wireless BAN are displayed properly", "Displayed", "NOT Displayed", true);
					}
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(),"Verify all the details about the Wireless BAN are displayed properly", "Displayed", "NOT Displayed", true);
				}
			}*/
			else
			{
				Report.TestPoint(testContext.getName(),"Either provide Wireless BAN or CTN", "Provided", "NOT provided", true);
			}
			
		/*	*//** UVERSE **//*
			ATTAccessID = UI.WaitForObject(oR_Dashboard.elm_DSL, 10);
			if(ATTAccessID)
			{
				Report.ValidationPoint(testContext.getName(), "Uverse in Search Menu", "true", "true", true);
				oR_Dashboard.elm_DSL.click();
				if(UI.WaitForObject(oR_Dashboard.elm_MemberID	, 2))
				{
					Report.ValidationPoint(testContext.getName(), "Member ID in Menu Uverse", "true", "true", true);
					oR_Dashboard.elm_MemberID.click();
					
					Report.ValidationPoint(testContext.getName(), "U-verse Member ID: Text", UI.WaitForObject(oR_Dashboard.elm_UverseMemberId, 2).toString(), "true", true);
					if(UI.WaitForObject(oR_Dashboard.edtUverseMID, 2))
					{
						Report.ValidationPoint(testContext.getName(), "U-verse Member ID Edit box", UI.WaitForObject(oR_Dashboard.edtUverseMID, 2).toString(), "true", true);
						oR_Dashboard.edtUverseMID.clear();
						oR_Dashboard.edtUverseMID.sendKeys(sUverseMemberID);
						oR_Dashboard.btnSearch.click();
						if(UI.WaitForObject(oR_Dashboard.radSelectSlid, 8))
						{
							oR_Dashboard.radSelectSlid.click();
							Report.ValidationPoint(testContext.getName(), "Select Login ID", "true" , "true", true);
							oR_Dashboard.btnContinue.click();
						}
							Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile", UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30).toString(), "true", true);
							Report.ValidationPoint(testContext.getName(), "Button Subscriber Management", UI.WaitForObject(oR_Dashboard.btnSubscriberManagement, 2).toString(), "true", true);
							Report.ValidationPoint(testContext.getName(), "Button Customer Activity", UI.WaitForObject(oR_Dashboard.btnCustomerActivity, 2).toString(), "true", true);
						
					}
				}
				else 	 Report.ValidationPoint(testContext.getName(), "Access ID in Menu ATT Access ID", "true", "false", true);

				
			}
			else Report.ValidationPoint(testContext.getName(), "ATT Access ID", "true", "false", true);
			*//** UVERSE ENDS**//*
			*/
			

			
			/**********************************************************UVERSE************************************************************************************************/

			//Hover over Uverse 
			boolean bElm_Uverse = UI.WaitForObject(oR_Dashboard.elm_DSL, 10,lDriver);
			Report.TestPoint(testContext.getName(),"Verify Uverse tab", "true", String.valueOf(bElm_Uverse), true);

			Actions action2 = new Actions(lDriver);
			try
			{
				action2.moveToElement(oR_Dashboard.elm_DSL).build().perform();

				Thread.sleep(4000);
				Report.TestPoint(testContext.getName(),"Hover over Uverse", "Hovered", "Hovered", true);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(),"Hover over Uverse", "Hovered", "Failed to hover", true);
			}

			//Select Uverse Member ID/BAN
			if(!(sUverseMemberID.equals("NA")))
			{
				//Verify Uverse Member ID will be displayed in dropdown
				if(oR_Dashboard.elm_MemberID.isDisplayed())
				{
					Report.TestPoint(testContext.getName(),"Verify Uverse Member ID displayed in dropdown", "Displayed", "Displayed", true);
				}
				else
				{
					Report.TestPoint(testContext.getName(),"Verify Uverse Member ID displayed in dropdown", "Displayed", "NOT Displayed", true);
				}

				//Select Member ID from the Uverse drop-down menu         
				oR_Dashboard.elm_MemberID.click();
				Thread.sleep(2000);
				Report.TestPoint(testContext.getName(),"Select Member ID from the Uverse drop-down menu", "Selected", "Selected", true);

				//Verify Uverse Member ID field is displayed
				boolean bElm_UverseMemberId = UI.WaitForObject(oR_Dashboard.elm_UverseMemberId, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify Uverse Member ID field is displayed", "true", String.valueOf(bElm_UverseMemberId), true);

				//Verify Uverse MID input box is displayed
				boolean bEdtUverseMID = UI.WaitForObject(oR_Dashboard.edtUverseMID, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify Uverse MID input box is displayed", "true", String.valueOf(bEdtUverseMID), true);

				//Verify search button
				boolean bBtnSearch1 = UI.WaitForObject(oR_Dashboard.btnSearch, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify search button", "true", String.valueOf(bBtnSearch1), true);

				//Enter Uverse MID in input box
				oR_Dashboard.edtUverseMID.sendKeys(sUverseMemberID);
				Thread.sleep(2000);
				Report.TestPoint(testContext.getName(),"Enter Uverse MID in input box", "Entered", "Entered", true);

				oR_Dashboard.btnSearch.click();
				Thread.sleep(4000);

				//Select CTN/access id from popup
				try
				{
					oR_Dashboard.radSelectSlid.click();
					Thread.sleep(2000);
					Report.TestPoint(testContext.getName(),"Select CTN/access id with radio button from popup", "Selected", "Selected", true);
					//Click on continue button
					oR_Dashboard.btnContinue.click();
					Thread.sleep(4000);
				}
				catch(Exception e)
				{

				}

				//Verify all the details about the Uverse Member Id are displayed properly
				try
				{
					lDriver.findElement(By.xpath("//*[text()='Member ID Details'] | //*[contains(text(),'Member ID:')] | //*[contains(text(),'Name:')] | //*[contains(text(),'Name')]"));

					//WebElement elmMemberIDdetails = lDriver.findElement(By.xpath("//*[text()='Member ID Details']"));
					//WebElement elmMemberId = lDriver.findElement(By.xpath("//*[contains(text(),'Member ID:')]"));
					//WebElement elmName = lDriver.findElement(By.xpath("//*[contains(text(),'Name:')]"));
					JavascriptExecutor jse = (JavascriptExecutor)lDriver;
					jse.executeScript("window.scrollBy(0,250)", "");
					Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile", UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30, lDriver).toString(), "true", true);
					Report.ValidationPoint(testContext.getName(), "Button Subscriber Management", UI.WaitForObject(oR_Dashboard.btnSubscriberManagement, 2, lDriver).toString(), "true", true);
					Report.ValidationPoint(testContext.getName(), "Button Customer Activity", UI.WaitForObject(oR_Dashboard.btnCustomerActivity, 2, lDriver).toString(), "true", true);

					Report.ValidationPoint(testContext.getName(),"Verify all the details about the Uverse Member Id are displayed properly", "Displayed", "Displayed", true);

				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(),"Verify all the details about the Uverse Member Id are displayed properly", "Displayed", "NOT Displayed", true);
				}
			}
/*			else if(!sUverseBAN.equals("NA"))
			{
				// Uverse BAN

				//Verify Uverse BAN will be displayed in dropdown
				if(oR_Dashboard.elmUverseBAN.isDisplayed())
				{
					Report.TestPoint(testContext.getName(),"Verify Uverse BAN displayed in dropdown", "Displayed", "Displayed", true);
				}
				else
				{
					Report.TestPoint(testContext.getName(),"Verify Uverse BAN displayed in dropdown", "Displayed", "NOT Displayed", true);
				}

				//Select BAN from the Uverse drop-down menu         
				oR_Dashboard.elmUverseBAN.click();
				Thread.sleep(5000);
				Report.TestPoint(testContext.getName(),"Select BAN from the Uverse drop-down menu", "Selected", "Selected", true);

				//Verify Uverse BAN field is displayed
				boolean bElm_UverseBAN = UI.WaitForObject(oR_Dashboard.elm_UverseOrUnifiedAccountNumber, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify Uverse BAN field is displayed", "true", String.valueOf(bElm_UverseBAN), true);

				//Verify Uverse BAN input box is displayed
				boolean bEdtUverseBAN = UI.WaitForObject(oR_Dashboard.edtUverseBAN, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify Uverse BAN input box is displayed", "true", String.valueOf(bEdtUverseBAN), true);

				//Verify search button
				boolean bBtnSearch1 = UI.WaitForObject(oR_Dashboard.btnSearch, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify search button", "true", String.valueOf(bBtnSearch1), true);

				//Enter Uverse BAN in input box
				oR_Dashboard.edtUverseBAN.sendKeys(sUverseBAN);
				Thread.sleep(2000);
				Report.TestPoint(testContext.getName(),"Enter Uverse BAN in input box", "Entered", "Entered", true);

				oR_Dashboard.btnSearch.click();
				Thread.sleep(4000);

				//Select CTN/access id from popup
				try
				{
					lDriver.findElement(By.xpath("//input[@type='radio']")).click();
					Thread.sleep(2000);
					Report.TestPoint(testContext.getName(),"Select CTN/access id with radio button from popup", "Selected", "Selected", true);
					//Click on continue button
					oR_Dashboard.btnContinue.click();
					Thread.sleep(4000);
				}
				catch(Exception e)
				{

				}

				//Verify all the details about the Uverse BAN are displayed properly
				try
				{
					WebElement elmATTLoginId = lDriver.findElement(By.xpath("//*[text()='AT&T Login ID']"));
					WebElement elmName = lDriver.findElement(By.xpath("//*[text()='Name']"));

					if(elmATTLoginId.isDisplayed() && elmName.isDisplayed() && oR_Dashboard.btnSubscriberManagement.isDisplayed())
					{
						Report.ValidationPoint(testContext.getName(),"Verify all the details about the Uverse BAN are displayed properly", "Displayed", "Displayed", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(),"Verify all the details about the Uverse BAN are displayed properly", "Displayed", "NOT Displayed", true);
					}
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(),"Verify all the details about the Uverse BAN are displayed properly", "Displayed", "NOT Displayed", true);
				}

			}*/
			else
			{
				Report.ValidationPoint(testContext.getName(),"Either provide Uverse Member ID or BAN", "Provided", "NOT provided", true);
			}

			/** WIRELINE **//*
			ATTAccessID = UI.WaitForObject(oR_Dashboard.elmWireline, 10);
			if(ATTAccessID)
			{
				Report.ValidationPoint(testContext.getName(), "Uverse in Search Menu", "true", "true", true);
				oR_Dashboard.elmWireline.click();
				if(UI.WaitForObject(oR_Dashboard.elm_WirelineBTN	, 2))
				{
					Report.ValidationPoint(testContext.getName(), "BTN in Menu Wireline", "true", "true", true);
					oR_Dashboard.elm_WirelineBTN.click();
					
					Report.ValidationPoint(testContext.getName(), "Wireline BTN: Text", UI.WaitForObject(oR_Dashboard.elmWirelineBTN, 2).toString(), "true", true);
					if(UI.WaitForObject(oR_Dashboard.edtWirelineBTN, 2))
					{
						Report.ValidationPoint(testContext.getName(), "U-verse Member ID Edit box", UI.WaitForObject(oR_Dashboard.edtWirelessCTN, 2).toString(), "true", true);
						oR_Dashboard.edtWirelineBTN.clear();
						oR_Dashboard.edtWirelineBTN.sendKeys(sUverseMemberID);
						oR_Dashboard.btnSearch.click();
						if(UI.WaitForObject(oR_Dashboard.radSelectSlid, 8))
						{
							oR_Dashboard.radSelectSlid.click();
							Report.ValidationPoint(testContext.getName(), "Select Login ID", "true" , "true", true);
							oR_Dashboard.btnContinue.click();
						}
							Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile", UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30).toString(), "true", true);
							Report.ValidationPoint(testContext.getName(), "Button Subscriber Management", UI.WaitForObject(oR_Dashboard.btnSubscriberManagement, 2).toString(), "true", true);
							Report.ValidationPoint(testContext.getName(), "Button Customer Activity", UI.WaitForObject(oR_Dashboard.btnCustomerActivity, 2).toString(), "true", true);
						
					}
				}
				else 	 Report.ValidationPoint(testContext.getName(), "Access ID in Menu ATT Access ID", "true", "false", true);

				
			}
			else Report.ValidationPoint(testContext.getName(), "ATT Access ID", "true", "false", true);
			*//** WIRELINE ENDS**/
			/**********************************************************WIRELINE************************************************************************************************/
				if(!(sWirelineBTN.equals("NA")))
				{
					System.out.println("sWirelineBTN "+sWirelineBTN);
					//Hover over Wireline 
					boolean bElm_Wireline = UI.WaitForObject(oR_Dashboard.elmWireline, 10,lDriver);
					Report.TestPoint(testContext.getName(),"Verify Wireline tab", "true", String.valueOf(bElm_Wireline), true);

					Actions action3 = new Actions(lDriver);
					try
					{
						action3.moveToElement(oR_Dashboard.elmWireline).build().perform();

						Thread.sleep(4000);
						Report.TestPoint(testContext.getName(),"Hover over Wireline", "Hovered", "Hovered", true);
					}
					catch(Exception e4)
					{
						Report.TestPoint(testContext.getName(),"Hover over Wireline", "Hovered", "Failed to hover", true);
					}


					//Verify Wireline BTN will be displayed in dropdown
					if(oR_Dashboard.elm_WirelineBTN.isDisplayed())
					{
						Report.TestPoint(testContext.getName(),"Verify Wireline BTN displayed in dropdown", "Displayed", "Displayed", true);
					}
					else
					{
						Report.TestPoint(testContext.getName(),"Verify Wireline BTN displayed in dropdown", "Displayed", "NOT Displayed", true);
					}

					//Select BTN from the Wireline drop-down menu         
					oR_Dashboard.elm_WirelineBTN.click();
					Thread.sleep(5000);
					Report.TestPoint(testContext.getName(),"Select BTN from the Wireline drop-down menu", "Selected", "Selected", true);

					//Verify Wireline BTN field is displayed
					boolean bElmWirelineBTN = UI.WaitForObject(oR_Dashboard.txtWirelineBTN, 10,lDriver);
					Report.TestPoint(testContext.getName(),"Verify Wireline BTN field is displayed", "true", String.valueOf(bElmWirelineBTN), true);

					//Verify Wireline BTN input box is displayed
					boolean bEDTWirelineBTN = UI.WaitForObject(oR_Dashboard.edtWirelineBTN, 10,lDriver);
					Report.TestPoint(testContext.getName(),"Verify Wireline BTN input box is displayed", "true", String.valueOf(bEDTWirelineBTN), true);

					//Verify search button
					boolean bBtnSearch2 = UI.WaitForObject(oR_Dashboard.btnSearch, 10,lDriver);
					Report.TestPoint(testContext.getName(),"Verify search button", "true", String.valueOf(bBtnSearch2), true);

					//Enter Wireline BTN in input box
					oR_Dashboard.edtWirelineBTN.sendKeys(sWirelineBTN);
					Thread.sleep(2000);
					Report.TestPoint(testContext.getName(),"Enter Wireline BTN in input box", "Entered", "Entered", true);

					oR_Dashboard.btnSearch.click();
					Thread.sleep(4000);

					//Select CTN/access id from popup
					try
					{
						lDriver.findElement(By.xpath("//input[@type='radio']")).click();
						Thread.sleep(2000);
						Report.TestPoint(testContext.getName(),"Select CTN/access id with radio button from popup", "Selected", "Selected", true);
						//Click on continue button
						oR_Dashboard.btnContinue.click();
						Thread.sleep(4000);
					}
					catch(Exception e3)
					{

					}

					//Verify all the details about the Wireline BTN are displayed properly
					try
					{
						WebElement elmATTLoginId = lDriver.findElement(By.xpath("//*[text()='AT&T Login ID']"));
						WebElement elmName = lDriver.findElement(By.xpath("//*[contains(text(),'Name:')]"));
						
						JavascriptExecutor jse = (JavascriptExecutor)lDriver;
						jse.executeScript("window.scrollBy(0,250)", "");
						Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile", UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30, lDriver).toString(), "true", true);
						Report.ValidationPoint(testContext.getName(), "Button Subscriber Management", UI.WaitForObject(oR_Dashboard.btnSubscriberManagement, 2, lDriver).toString(), "true", true);
						Report.ValidationPoint(testContext.getName(), "Button Customer Activity", UI.WaitForObject(oR_Dashboard.btnCustomerActivity, 2, lDriver).toString(), "true", true);

						if(elmATTLoginId.isDisplayed() && elmName.isDisplayed() && oR_Dashboard.btnSubscriberManagement.isDisplayed())
						{
							Report.ValidationPoint(testContext.getName(),"Verify all the details about the Wireline BTN are displayed properly", "Displayed", "Displayed", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(),"Verify all the details about the Wireline BTN are displayed properly", "Displayed", "NOT Displayed", true);
						}
					}
					catch(Exception e2)
					{
						Report.ValidationPoint(testContext.getName(),"Verify all the details about the Wireline BTN are displayed properly", "Displayed", "NOT Displayed", true);
					}
				}	
				else
				{
					Report.ValidationPoint(testContext.getName(),"Provide Wireline BTN", "Provided", "NOT provided", true);
				}

			
		}
		catch(Exception e)
		{          
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}




	/**************************************************************
	 * Function Name - verifyTopMenuAndNavigations 
	 * Description- This function is used in BVT. It verifies options of top search menu and their navigations to 
	 * 				respective pages
	 * 			
	 * Parameters - final ITestContext testContext
	 * Date created -
	 * Date modified -
	 * Developed by - 	Sneha Pansare
	 * Last Modified By - 8th Jan 16
	 * Last Modified Date -
	 ***************************************************************/

	public static void verifyTopMenuAndNavigations(final ITestContext testContext) throws HeadlessException, IOException, AWTException 
	{ 
		try 
		{ 
			WebDriver lDriver = UI.getDriver(testContext.getName());	// Test Specific Operations 
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class); 
			OR_MyATTZone oR_MyATTZone = PageFactory.initElements(lDriver, OR_MyATTZone.class); 
			OR_Profile_zone oR_Profile_zone = PageFactory.initElements(lDriver, OR_Profile_zone.class); 
			OR_MobileShareComparisionTool oR_MobileShareComparisionTool = PageFactory.initElements(lDriver, OR_MobileShareComparisionTool.class); 

			myATT_Zone.verifySearchOptionsOfTopMenu(testContext); 

			//Click on Errors tab 
			UI.WaitForObject(oR_Dashboard.lnkErrors, UI.iObjTimeOut, lDriver); 
			oR_Dashboard.lnkErrors.click(); 
			Report.OperationPoint(testContext.getName(), "Clicking on Errors tab"); 

			//Verify Navigation to Errors page 
			try 
			{ 
				Boolean tabSearchErrorMessages = UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(@name,'error')]")), 10, lDriver); 
				Boolean edtErrorCode = UI.WaitForObject(lDriver.findElement(By.xpath("//*[text()='Error Code:']")), 10, lDriver); 

				if(tabSearchErrorMessages==true && edtErrorCode==true) 
				{ 
					Report.ValidationPoint(testContext.getName(), "Verify Navigation to Errors page", "Navigated", "Navigated", true); 
					oR_Dashboard.lnkAccounts.click(); 
				} 
				else 
				{ 
					Report.ValidationPoint(testContext.getName(), "Verify Navigation to Profile page", "Navigated", "NOT Navigated", true); 
				} 


			} 
			catch(Exception e) 
			{ 
				Report.ValidationPoint(testContext.getName(), "Verify Navigation to Errors page", "Navigated", "NOT Navigated", true); 
			} 

			oR_Dashboard.lnkAccounts.click(); 

			if(testContext.getName().contains("BVT"))
			{
				//Click on Profile tab 
				UI.WaitForObject(oR_Dashboard.btnProfile, UI.iObjTimeOut, lDriver); 
				oR_Dashboard.btnProfile.click(); 
				Report.OperationPoint(testContext.getName(), "Clicking on Profile tab"); 

				//Verify Navigation to Profile page 

				try 
				{ 
					Boolean btnSearchCSR = UI.WaitForObject(oR_Profile_zone.btnSearchCSR, 10, lDriver); 
					Boolean btnAddCSR = UI.WaitForObject(oR_Profile_zone.btnAddCSR, 10, lDriver); 
					Boolean elmATTUID = UI.WaitForObject(lDriver.findElement(By.xpath("//*[text()='ATTUID']")), 10, lDriver); 

					if(btnSearchCSR==true && btnAddCSR==true && elmATTUID==true) 
					{ 
						Report.ValidationPoint(testContext.getName(), "Verify Navigation to Profile page", "Navigated", "Navigated", true); 
					} 
					else 
					{ 
						Report.ValidationPoint(testContext.getName(), "Verify Navigation to Profile page", "Navigated", "NOT Navigated", true); 
					} 
				} 
				catch(Exception e) 
				{ 
					Report.ValidationPoint(testContext.getName(), "Verify Navigation to Profile page", "Navigated", "NOT Navigated", true); 
				} 

				oR_Dashboard.lnkAccounts.click(); 

				//Click on Agent Activity tab 
				UI.WaitForObject(oR_Dashboard.btnAgentActivity, UI.iObjTimeOut, lDriver); 
				oR_Dashboard.btnAgentActivity.click(); 
				Report.OperationPoint(testContext.getName(), "Clicking on Agent Activity tab"); 

				//Verify Navigation to Agent Activity page 
				try 
				{ 
					Boolean btnAgentActivity = UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(@src,'agent')]")), UI.iObjTimeOut, lDriver); 
					Boolean txtATTUID = UI.WaitForObject(lDriver.findElement(By.xpath("//*[text()='ATTUID:']")), UI.iObjTimeOut, lDriver); 

					if(btnAgentActivity==true && txtATTUID==true) 
					{ 
						Report.ValidationPoint(testContext.getName(), "Verify Navigation to Agent Activity page", "Navigated", "Navigated", true); 
					} 
					else 
					{ 
						Report.ValidationPoint(testContext.getName(), "Verify Navigation to Agent Activity page", "Navigated", "NOT Navigated", true); 
					} 


				} 
				catch(Exception e) 
				{ 
					Report.ValidationPoint(testContext.getName(), "Verify Navigation to Agent Activity page", "Navigated", "NOT Navigated", true); 
				} 

				oR_Dashboard.lnkAccounts.click(); 
			}
			//////////////////////////// 

			//Click on MSCT tab 
			UI.WaitForObject(oR_Dashboard.btnMobileShareComparison, UI.iObjTimeOut, lDriver); 
			oR_Dashboard.btnMobileShareComparison.click(); 
			Report.OperationPoint(testContext.getName(), "Clicking on MSCT tab"); 
			Thread.sleep(2000); 

			//switch to new opened window 

			String parentHandle = lDriver.getWindowHandle(); // get the current window handle 

			Thread.sleep(5000); 
			for (String winHandle : lDriver.getWindowHandles()) { 
				lDriver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle 
			} 

			//Verify Navigation to MSCT page 
			try 
			{ 
				Boolean txtMobileShareHeading = UI.WaitForObject(oR_MobileShareComparisionTool.txtMobileShareHeading, UI.iObjTimeOut, lDriver); 
				//System.out.println("txtMobileShareHeading : "+txtMobileShareHeading); 
				if(txtMobileShareHeading==true) 
				{ 
					Report.ValidationPoint(testContext.getName(), "Verify Navigation to MSCT page", "Navigated", "Navigated", true); 
				} 
				else 
				{ 
					Report.ValidationPoint(testContext.getName(), "Verify Navigation to MSCT page", "Navigated", "NOT Navigated", true); 
				} 
			} 
			catch(Exception e) 
			{ 
				Report.ValidationPoint(testContext.getName(), "Verify Navigation to MSCT page", "Navigated", "NOT Navigated", true); 
			} 
			lDriver.close(); 
			lDriver.switchTo().window(parentHandle); 

			/////////////////////////// 

			//Click on Sales Support tab 
			UI.WaitForObject(oR_Dashboard.lnkSalesSupport, UI.iObjTimeOut, lDriver); 
			oR_Dashboard.lnkSalesSupport.click(); 
			Report.OperationPoint(testContext.getName(), "Clicking on Sales Support tab"); 

			//Verify Navigation to Sales Support page 
			try 
			{ 
				Boolean tabSalesSupport = UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(@src,'sales')]")), UI.iObjTimeOut, lDriver); 
				Boolean lnkOrderStatus = UI.WaitForObject(oR_MyATTZone.lnkOrderStatus, UI.iObjTimeOut, lDriver); 


				if(tabSalesSupport==true && lnkOrderStatus==true) 
				{ 
					Report.ValidationPoint(testContext.getName(), "Verify Navigation to Sales Support page", "Navigated", "Navigated", true); 

				} 
				else 
				{ 
					Report.ValidationPoint(testContext.getName(), "Verify Navigation to Sales Support page", "Navigated", "NOT Navigated", true); 
				} 

			} 
			catch(Exception e) 
			{ 
				Report.ValidationPoint(testContext.getName(), "Verify Navigation to Sales Support page", "Navigated", "NOT Navigated", true); 
			} 
			oR_Dashboard.lnkAccounts.click(); 


			//Click on Registration tab 
			UI.WaitForObject(oR_Dashboard.lnkRegistration, UI.iObjTimeOut, lDriver); 
			oR_Dashboard.lnkRegistration.click(); 
			Report.OperationPoint(testContext.getName(), "Clicking on Registration tab"); 

			//switch to new opened window 

			//String parentHandle = lDriver.getWindowHandle(); // get the current window handle 

			Thread.sleep(5000); 
			for (String winHandle : lDriver.getWindowHandles()) { 
				lDriver.switchTo().window(winHandle); // switch focus of WeblDriver to the next found window handle 
			} 

			//Verify Navigation to Registration page 
			try 
			{ 
				//Boolean txtProvideAccountInfo = UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(text(),'Provide Account Information')]")), UI.iObjTimeOut); 
				Boolean txtProvideAccountInfo = UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'Register')]")), UI.iObjTimeOut, lDriver);

				if(txtProvideAccountInfo==true) 
				{ 
					Report.ValidationPoint(testContext.getName(), "Verify Navigation to Registration page", "Navigated", "Navigated", true); 

				} 
				else 
				{ 
					Report.ValidationPoint(testContext.getName(), "Verify Navigation to Registration page", "Navigated", "NOT Navigated", true); 
				} 



			} 
			catch(Exception e) 
			{ 
				Report.ValidationPoint(testContext.getName(), "Verify Navigation to Registration page", "Navigated", "NOT Navigated", true); 
			} 
			lDriver.close(); 
			lDriver.switchTo().window(parentHandle); 

			//Click on Fraud tab 
			UI.WaitForObject(oR_Dashboard.lnkFraud, UI.iObjTimeOut, lDriver); 
			oR_Dashboard.lnkFraud.click(); 
			Report.OperationPoint(testContext.getName(), "Clicking on Fraud tab"); 

			//Verify Navigation to Fraud page 
			try 
			{ 
				Boolean txtFraud = UI.WaitForObject(lDriver.findElement(By.xpath("//*[text()='Fraud'][@class='fraudTitle']")), UI.iObjTimeOut, lDriver); 

				if(txtFraud==true) 
				{ 
					Report.ValidationPoint(testContext.getName(), "Verify Navigation to Fraud page", "Navigated", "Navigated", true); 

				} 
				else 
				{ 
					Report.ValidationPoint(testContext.getName(), "Verify Navigation to Fraud page", "Navigated", "NOT Navigated", true); 
				} 



			} 
			catch(Exception e) 
			{ 
				Report.ValidationPoint(testContext.getName(), "Verify Navigation to Fraud page", "Navigated", "NOT Navigated", true); 
			} 

			oR_Dashboard.lnkAccounts.click(); 


		} catch (Exception e){ 

			String[] errMsg = e.getMessage().split("\\r?\\n"); UI.printMsg(errMsg[0]); 
			Report.TestPoint(testContext.getName(), errMsg[0], "True", "False", true); 
		} 

	}	

	/**************************************************************
	 * Function Name - productionEnvCheckWithWirelessAndAccessID 
	 * Description- This function is used in PVT. It verifies Navigations of Access Id and wireless CTN from left menu
	 * 			
	 * Parameters - final ITestContext testContext
	 * Date created -
	 * Date modified -
	 * Developed by - 	Sneha Pansare
	 * Last Modified By - 3rd March 16
	 * Last Modified Date -
	 ***************************************************************/

	public static void productionEnvCheckWithWirelessAndAccessID(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{

		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);

		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sAccessID = IO.GetParamVal(sTestParams, "AccessID");
		String sWirelessCTN = IO.GetParamVal(sTestParams, "Wireless_CTN");
		
		//select AT&T access id from search menu
		boolean bElmATTAccessID = UI.WaitForObject(oR_Dashboard.elm_ATTAccessID, 10, lDriver);
		Report.TestPoint(testContext.getName(),"Verify AT&T access id from search menu", "true", String.valueOf(bElmATTAccessID), true);


		//Hover over Access Id 

		Actions action = new Actions(lDriver);
		try
		{
			action.moveToElement(oR_Dashboard.elm_ATTAccessID).build().perform();

			Thread.sleep(4000);
			Report.TestPoint(testContext.getName(),"Hover over Access Id", "Hovered", "Hovered", true);
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Hover over Access Id", "Hovered", "Failed to hover", true);
		}

		//Move to Access Id

		try
		{
			Thread.sleep(2000);
			if(oR_Dashboard.elm_AccessID.isDisplayed())
			{
				try
				{
					action.moveToElement(oR_Dashboard.elm_AccessID).build().perform();

					Thread.sleep(4000);
					Report.TestPoint(testContext.getName(),"Hover over submenu Access Id", "Hovered", "Hovered", true);
					oR_Dashboard.elm_AccessID.click();
					Thread.sleep(4000);
					Report.TestPoint(testContext.getName(),"Click on submenu Access Id", "Clicked", "Clicked", true);
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(),"Hover over Access Id", "Hovered", "Failed to hover", true);
				}
			}
			else
			{
				oR_Dashboard.elm_ATTAccessID.click();
				Thread.sleep(5000);
				Report.TestPoint(testContext.getName(),"Click AT&T access id from search menu", "Clicked", "Clicked", true);


			}
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Click AT&T access id from search menu", "Clicked", "FAILED to click", true);
		}

		//Verify AT&T access id field is displayed
		boolean bTxtATTaccessId = UI.WaitForObject(oR_Dashboard.txtATTaccessId, 10, lDriver);
		Report.TestPoint(testContext.getName(),"Verify AT&T access id field is displayed", "true", String.valueOf(bTxtATTaccessId), true);

		//Verify AT&T access id input box is displayed
		boolean bEdtATTaccessIdSearchField = UI.WaitForObject(oR_Dashboard.elm_ATTAccessIDTxtField, 10, lDriver);
		Report.TestPoint(testContext.getName(),"Verify AT&T access id input box is displayed", "true", String.valueOf(bEdtATTaccessIdSearchField), true);

		//Verify search button
		boolean bBtnSearch = UI.WaitForObject(oR_Dashboard.btnSearch, 10, lDriver);
		Report.TestPoint(testContext.getName(),"Verify search button", "true", String.valueOf(bBtnSearch), true);
		
		//Enter the Access Id and press Search button
		oR_Dashboard.elm_ATTAccessIDTxtField.sendKeys(sAccessID);
		Report.TestPoint(testContext.getName(),"Enter the Access Id", "Entered", "Entered", true);

		oR_Dashboard.btnSearch.click();
		Thread.sleep(4000);

		//Select CTN/access id from popup
		try
		{
			lDriver.findElement(By.xpath("//input[@type='radio']")).click();
			Thread.sleep(2000);
			Report.TestPoint(testContext.getName(),"Select CTN/access id with radio button from popup", "Selected", "Selected", true);
			//Click on continue button
			oR_Dashboard.btnContinue.click();
			Thread.sleep(4000);
		}
		catch(Exception e)
		{
			
		}
		
		//Verify all the details about the access Id are displayed properly
		try
		{
			lDriver.findElement(By.xpath("//*[contains(text(),'AT&T Login ID:')]"));
			lDriver.findElement(By.xpath("//*[contains(text(),'Name:')]"));

			if(oR_Dashboard.btnSubscriberManagement.isDisplayed())
			{
				Report.ValidationPoint(testContext.getName(),"Verify all the details about the access Id are displayed properly", "Displayed", "Displayed", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(),"Verify all the details about the access Id are displayed properly", "Displayed", "NOT Displayed", true);
			}
		}
		catch(Exception e)
		{
			Report.ValidationPoint(testContext.getName(),"Verify all the details about the access Id are displayed properly", "Displayed", "NOT Displayed", true);
		}

		//Click on account tab again
		
		boolean bLnkAccounts = UI.WaitForObject(oR_Dashboard.lnkAccounts, 10, lDriver);
		oR_Dashboard.lnkAccounts.click();
		Thread.sleep(9000);
		Report.TestPoint(testContext.getName(),"Click on account tab", "true", String.valueOf(bLnkAccounts), true);

		//Hover over Wireless 
		boolean bTxtWireless = UI.WaitForObject(oR_Dashboard.txtWireless, 10, lDriver);
		Report.TestPoint(testContext.getName(),"Verify Wireless tab", "true", String.valueOf(bTxtWireless), true);

	
		try
		{
			action.moveToElement(oR_Dashboard.txtWireless).build().perform();

			Thread.sleep(4000);
			Report.TestPoint(testContext.getName(),"Hover over Wireless", "Hovered", "Hovered", true);
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Hover over Wireless", "Hovered", "Failed to hover", true);
		}
		
		//Verify CTN will be displayed in dropdown
			List<WebElement> lstCTNs = lDriver.findElements(By.xpath("//*[text()='CTN']"));
			WebElement elmCTN = null;
			boolean bCTNfound = false;
			for(int i=0;i<lstCTNs.size();i++)
			{
				if(lstCTNs.get(i).isDisplayed())
				{
					elmCTN = lstCTNs.get(i);
					bCTNfound = true;
					break;
					
				}
			}
			
			if(bCTNfound==true)
			{
				action.moveToElement(elmCTN).build().perform();
				Report.TestPoint(testContext.getName(),"Verify CTN will be displayed in dropdown", "Displayed", "Displayed", true);
			}
			else
			{
				Report.TestPoint(testContext.getName(),"Verify CTN will be displayed in dropdown", "Displayed", "NOT Displayed", true);
			}

			//Select CTN from the Wireless drop-down menu
			elmCTN.click();
			Report.TestPoint(testContext.getName(),"Select CTN from the Wireless drop-down menu", "Selected", "Selected", true);

			//Verify Mobile Number field is displayed
			boolean bTxtMobileNumber = UI.WaitForObject(oR_Dashboard.txtMobileNumber, 10, lDriver);
			Report.TestPoint(testContext.getName(),"Verify Mobile Number field is displayed", "true", String.valueOf(bTxtMobileNumber), true);

			//Verify Mobile Number input box is displayed
			boolean bEdtWirelessCTN = UI.WaitForObject(oR_Dashboard.edtWirelessCTN, 10, lDriver);
			Report.TestPoint(testContext.getName(),"Verify Mobile Number input box is displayed", "true", String.valueOf(bEdtWirelessCTN), true);

			//Verify search button
			boolean bBtnSearch1 = UI.WaitForObject(oR_Dashboard.btnSearch, 10, lDriver);
			Report.TestPoint(testContext.getName(),"Verify search button", "true", String.valueOf(bBtnSearch1), true);

			//Enter mobile number in input box
			oR_Dashboard.edtWirelessCTN.sendKeys(sWirelessCTN);
			Thread.sleep(2000);
			Report.TestPoint(testContext.getName(),"Enter mobile number in input box", "Entered", "Entered", true);

			oR_Dashboard.btnSearch.click();
			Thread.sleep(4000);

			//Select CTN/access id from popup
			try
			{
				lDriver.findElement(By.xpath("//input[@type='radio'][contains(@onclick,'Access ID')]")).click();
				Thread.sleep(2000);
				Report.TestPoint(testContext.getName(),"Select access id with radio button from popup", "Selected", "Selected", true);
				//Click on continue button
				oR_Dashboard.btnContinue.click();
				Thread.sleep(4000);
			}
			catch(Exception e)
			{
				
			}



			//Verify all the details about the Wireless CTN are displayed properly
			try
			{
				lDriver.findElement(By.xpath("//*[contains(text(),'AT&T Login ID:')]"));
				lDriver.findElement(By.xpath("//*[contains(text(),'Name:')]"));

				if(oR_Dashboard.btnSubscriberManagement.isDisplayed())
				{
					Report.TestPoint(testContext.getName(),"Verify all the details about the Wireless CTN are displayed properly", "Displayed", "Displayed", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(),"Verify all the details about the Wireless CTN are displayed properly", "Displayed", "NOT Displayed", true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(),"Verify all the details about the Wireless CTN are displayed properly", "Displayed", "NOT Displayed", true);
			}
		}
		catch(Exception e)
		{
		String[] errMsg = e.getMessage().split("\\r?\\n"); UI.printMsg(errMsg[0]);
		Report.TestPoint(testContext.getName(), errMsg[0], "True", "False", true);
		}
	}
	

	/**************************************************************
	 * Function Name - verifyMobileShareComparisonToolPageDetailsAndAddDeviceFlow 
	 * Description- This function is used in BVT. It verifies all details on mobile share tool page
	 * 				It also performs add device flow
	 * Parameters - final ITestContext testContext
	 * Date created -
	 * Date modified -
	 * Developed by - 	Sneha Pansare
	 * Last Modified By - 8th Jan 16
	 * Last Modified Date -
	 ***************************************************************/

	public static void verifyMobileShareComparisonToolPageDetailsAndAddDeviceFlow(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{

			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
			OR_MyATTZone oR_MyATTZone = PageFactory.initElements(lDriver, OR_MyATTZone.class);
			OR_MobileShareComparisionTool oR_MobileShareComparisionTool = PageFactory.initElements(lDriver, OR_MobileShareComparisionTool.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			String sBAN = IO.GetParamVal(sTestParams, "Wireless_CTN");
			//String sDiscountAmountToEnterInInputBox = IO.GetParamVal(sTestParams, "Discount_Amount_To_Enter_In_InputBox");

			//Click on the 'Mobile Share Recommender' tab. On CSR Landing Page
			Boolean bMobileShareComparisonTab = UI.WaitForObject(oR_Dashboard.btnMobileShareComparison, 30,lDriver);
			Report.TestPoint(testContext.getName(), "Click on the 'Mobile Share Recommender tab", "true", bMobileShareComparisonTab.toString(), true);
			oR_Dashboard.btnMobileShareComparison.click();

			//switch to new opened window

			//String parentHandle = lDriver.getWindowHandle(); // get the current window handle

			Thread.sleep(5000);
			for (String winHandle : lDriver.getWindowHandles()) {
				lDriver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle 
			}

			//Verify Redirected to Mobile Share Recommender Tool Search Page
			Boolean bMobileShareHeading = UI.WaitForObject(oR_MobileShareComparisionTool.txtMobileShareHeading, 30,lDriver);
			Report.TestPoint(testContext.getName(), "Verify Redirection to Mobile Share Recommender Tool Search Page", "true", bMobileShareHeading.toString(), true);

			//Enter wireless BAN/CTN on Search Page
			Boolean bEnterCTN = UI.WaitForObject(oR_MobileShareComparisionTool.edtCTN, 30,lDriver);
			Report.TestPoint(testContext.getName(), "Enter wireless BAN/CTN", "true", bEnterCTN.toString(), true);
			oR_MobileShareComparisionTool.edtCTN.sendKeys(sBAN);

			//Click submit
			Boolean bSubmit = UI.WaitForObject(oR_MobileShareComparisionTool.btnSubmit, 30,lDriver);
			Report.TestPoint(testContext.getName(), "Click submit", "true", bSubmit.toString(), true);
			oR_MobileShareComparisionTool.btnSubmit.click();

			String sPageTitle = lDriver.getTitle();
			if(sPageTitle.contains("AT&T Mobile Share Data Planner"))
			{
				Report.TestPoint(testContext.getName(), "Verify navigation Mobile share plan details page", "Navigated", "FAILED to navigate to Mobile share plan details page", true);
			}
			
			
			//Verify Device Navigation Bar is displayed besides Left Hand Section
			Boolean bDeviceNavigationBar = UI.WaitForObject(oR_MobileShareComparisionTool.lstDeviceNavigationBar, 30,lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify Device Navigation Bar is displayed besides Left Hand Section", "true", bDeviceNavigationBar.toString(), true);

			//Verify Mobile share details are displaying
			try
			{
				lDriver.findElement(By.xpath("//*[contains(text(),'Mobile Share Comparisons are currently unavailable')]"));
				Report.TestPoint(testContext.getName(), "Verify Mobile share details are displaying", "displaying", "NOT displaying", true);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Verify Mobile share details are displaying", "displaying", "displaying", true);
			}

			//Verify that Device Navigation Bar should have - first name , image type , CTN , model of device and possibly informational message
			String[] arrCTN=new String[2];
			try
			{

				List<WebElement> elmDevicesTab = lDriver.findElements(By.xpath("//*[contains(@id,'Slider')]//li"));

				//Verify Up and down arrows (viewable but disabled if under 7 devices in list)
				if(UI.WaitForObject(oR_MobileShareComparisionTool.btnSlideDownOfDeviceNavigationBar, 20,lDriver))
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
			Boolean bTxtCurrentPlanInHeader = UI.WaitForObject(oR_MobileShareComparisionTool.txtCurrentPlanInHeader, 30,lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate header should show 'Current Plan' text", "true", bTxtCurrentPlanInHeader.toString(), true);

			Boolean bTxtPlanDetailsInHeader = UI.WaitForObject(oR_MobileShareComparisionTool.txtPlanDetailsInHeaderCurrentPlanSection, 30,lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate header should show 'Plan Details' label", "true", bTxtPlanDetailsInHeader.toString(), true);

			//Click on current plan Details button
			oR_MobileShareComparisionTool.txtPlanDetailsInHeaderCurrentPlanSection.click();
			Report.OperationPoint(testContext.getName(), "Clicking on current plan Details button");

			//Verify Plan details are displayed on Current plan details popup
			Boolean txtCurrentPlanDetails = UI.WaitForObject(oR_MobileShareComparisionTool.txtCurrentPlanDetails, 30,lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify Plan details are displayed on Current plan details popup", "true", txtCurrentPlanDetails.toString(), true);

			oR_MobileShareComparisionTool.btnCloseOnPlanDetailsPopup.click();
			Report.OperationPoint(testContext.getName(), "Closing current plan Details popup");

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
							Report.ValidationPoint(testContext.getName(), "Validate Text section is displayed", "Displayed", "Displayed", true);
						}
						else
						{
							String sCurrentSection= null;
							if(sCurrentSectionText.contains("TALK"))
							{
								sCurrentSection = "Talk";
							}
							else
							{
								sCurrentSection = "Data";
							}
							int sCTNcountUnderExpandedSection=0;
							System.out.println("arrCTN.length: "+arrCTN.length);


							if(sCurrentSection.equals("Data"))
							{
								//Verify Total usage and Total available part in Data Summary Section
								Boolean lnkTotalUsage = UI.WaitForObject(oR_MobileShareComparisionTool.lnkTotalUsage, 30,lDriver);
								Report.TestPoint(testContext.getName(), "Verify Total usage is displayed in Data Summary Section", "true", lnkTotalUsage.toString(), true);

								Boolean lnkTotalAvailable = UI.WaitForObject(oR_MobileShareComparisionTool.lnkTotalAvailable, 30,lDriver);
								Report.TestPoint(testContext.getName(), "Verify Total available is displayed in Data Summary Section", "true", lnkTotalAvailable.toString(), true);

								//Validate '+' button for Total usage section
								String sTotalUsageText = oR_MobileShareComparisionTool.lnkTotalUsage.getText();
								if(sTotalUsageText.contains("+"))
								{
									Report.TestPoint(testContext.getName(), "Validate '+' button for Total usage section", "displayed", "displayed", true);
								}

								//Expand '+' button for Total usage section
								oR_MobileShareComparisionTool.lnkTotalUsage.click();
								Report.TestPoint(testContext.getName(), "Expand '+' button for Total usage section", "Expanded", "Expanded", true);

								//Verify subsection 'Promo Used' is displayed under Total usage section
								try
								{
									lDriver.findElement(By.xpath("//*[text()='Promo Used']"));
									Report.TestPoint(testContext.getName(), "Verify subsection 'Promo Used' is displayed under Total usage section", "displayed", "displayed", true);
								}
								catch(Exception e)
								{
									Report.TestPoint(testContext.getName(), "Verify subsection 'Promo Used' is displayed under Total usage section", "displayed", "NOT displayed", true);
								}

								//Verify subsection 'Plan Used' is displayed under Total usage section
								try
								{
									lDriver.findElement(By.xpath("//*[text()='Plan Used']"));
									Report.TestPoint(testContext.getName(), "Verify subsection 'Plan Used' is displayed under Total usage section", "displayed", "displayed", true);
								}
								catch(Exception e)
								{
									Report.TestPoint(testContext.getName(), "Verify subsection 'Plan Used' is displayed under Total usage section", "displayed", "NOT displayed", true);
								}

								//Verify subsection 'MSV Rollover Used' is displayed under Total usage section
								try
								{
									lDriver.findElement(By.xpath("//*[text()='MSV Rollover Used']"));
									Report.TestPoint(testContext.getName(), "Verify subsection 'MSV Rollover Used' is displayed under Total usage section", "displayed", "displayed", true);
								}
								catch(Exception e)
								{
									Report.TestPoint(testContext.getName(), "Verify subsection 'MSV Rollover Used' is displayed under Total usage section", "displayed", "NOT displayed", true);
								}

								//Validate '+' button for Total Available section
								String sTotalAvailableText = oR_MobileShareComparisionTool.lnkTotalAvailable.getText();
								if(sTotalAvailableText.contains("+"))
								{
									Report.TestPoint(testContext.getName(), "Validate '+' button for Total Available section", "displayed", "displayed", true);
								}

								//Expand '+' button for Total Available section
								oR_MobileShareComparisionTool.lnkTotalAvailable.click();
								Report.TestPoint(testContext.getName(), "Expand '+' button for Total Available section", "Expanded", "Expanded", true);

								//Verify subsection 'Promo Avail.' is displayed under Total Available section
								try
								{
									lDriver.findElement(By.xpath("//*[text()='Promo Avail.']"));
									Report.TestPoint(testContext.getName(), "Verify subsection 'Promo Avail.' is displayed under Total Available section", "displayed", "displayed", true);
								}
								catch(Exception e)
								{
									Report.TestPoint(testContext.getName(), "Verify subsection 'Promo Avail.' is displayed under Total Available section", "displayed", "NOT displayed", true);
								}

								//Verify subsection 'Plan Avail.' is displayed under Total Available section
								try
								{
									lDriver.findElement(By.xpath("//*[text()='Plan Avail.']"));
									Report.TestPoint(testContext.getName(), "Verify subsection 'Plan Avail.' is displayed under Total Available section", "displayed", "displayed", true);
								}
								catch(Exception e)
								{
									Report.TestPoint(testContext.getName(), "Verify subsection 'Plan Avail.' is displayed under Total Available section", "displayed", "NOT displayed", true);
								}

								//Verify subsection 'MSV Rollover Avail.' is displayed under Total Available section
								try
								{
									lDriver.findElement(By.xpath("//*[text()='MSV Rollover Avail.']"));
									Report.TestPoint(testContext.getName(), "Verify subsection 'MSV Rollover Avail.' is displayed under Total Available section", "displayed", "displayed", true);
								}
								catch(Exception e)
								{
									Report.TestPoint(testContext.getName(), "Verify subsection 'MSV Rollover Avail.' is displayed under Total Available section", "displayed", "NOT displayed", true);
								}
							}

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
								Report.ValidationPoint(testContext.getName(), "Validate all CTNs are displayed in expanded "+sCurrentSection+" section", "Displayed", "Displayed", true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate all CTNs are displayed in current expanded "+sCurrentSection+" section", "Displayed", "NOT Displayed", true);
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
			Boolean bAddDeviceButton = UI.WaitForObject(oR_MobileShareComparisionTool.btnAddDevice, 30,lDriver);
			Report.TestPoint(testContext.getName(), "Verify Add Device button", "true", bAddDeviceButton.toString(), true);

			//Click on add Device button.
			oR_MobileShareComparisionTool.btnAddDevice.click();

			//Verify A new dialog box should be open to add Devices
			if(oR_MobileShareComparisionTool.txtAddDeviceOnDialogBox.isDisplayed())
			{
				Report.ValidationPoint(testContext.getName(), "Verify new dialog box has been opened", "Opened", "Opened", true);

				//Verify Devices are available on Device selection dropdown
				// Validate the options displayed in Device list
				String strRes = "True";
				//String strDevOptions = " Smartphone - Next/BYOD | Tablet | Basic Phone | Laptop | Mobile Hotspot Device | Gaming Device | Wireless Home Phone ";	
				String strDevOptions = " Smartphone-Contract-(If Available) | Smartphone-No Contract | Tablet | Basic Phone | Laptop | Mobile Hotspot Device | Gaming Device | Wireless Home Phone ";
				Select devOptions = new Select(oR_MyATTZone.AddDevice_lstOptions);  

				List<WebElement> opt = devOptions.getOptions();

				for(WebElement option : opt){
					if(!strDevOptions.contains(option.getText())) {
						strRes = "False";  
					}
				}
				oR_MyATTZone.AddDevice_lstOptions.click();
				Thread.sleep(2000);
				Report.TestPoint(testContext.getName(), "Validate the options displayed in Device list", "True", strRes, true);
				oR_MyATTZone.AddDevice_lstOptions.click();

				//Select Tablet with 3 GB Usage 

				Thread.sleep(5000);

				Select sel = new Select(oR_MyATTZone.AddDevice_lstOptions);
				sel.selectByValue("Device_id2");
				Boolean edtInputBoxForTabletDevice = UI.WaitForObject(oR_MobileShareComparisionTool.edtInputBoxForTabletDevice, 30,lDriver);
				oR_MobileShareComparisionTool.edtInputBoxForTabletDevice.clear();
				oR_MobileShareComparisionTool.edtInputBoxForTabletDevice.sendKeys("3");
				Report.TestPoint(testContext.getName(), "Select Tablet with 3 GB Usage", "true", edtInputBoxForTabletDevice.toString(), true);


				//click on Add button
				Boolean bAdd = UI.WaitForObject(oR_MobileShareComparisionTool.btnAddForTabletOnDialogBox, 30,lDriver);
				Report.TestPoint(testContext.getName(), "Click Add button", "true", bAdd.toString(), true);
				oR_MobileShareComparisionTool.btnAddForTabletOnDialogBox.click();
				Thread.sleep(5000);

				//Verify Message - 'Device(s) added since last mobile share recommendation= (1) devices added  - Tablet 3GB'
				try
				{
					WebElement elmMessage = lDriver.findElement(By.xpath("//*[contains(text(),'- Tablet 3GB')]"));
					if(elmMessage.isDisplayed())
					{
						Report.ValidationPoint(testContext.getName(), "Verify Message - 'devices added  - Tablet 3GB'", "Message displayed", "Message displayed", true);
					}
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Verify Message - 'devices added  - Tablet 3GB'", "Message displayed", "Message NOT displayed", true);
				}

				//Click on add another device button 
				Boolean bAddAnotherDevice = UI.WaitForObject(oR_MobileShareComparisionTool.btnAddAnotherDeviceOnDialogBox, 30,lDriver);
				Report.TestPoint(testContext.getName(), "Click Add another device button", "true", bAddAnotherDevice.toString(), true);
				oR_MobileShareComparisionTool.btnAddAnotherDeviceOnDialogBox.click();

				//Select Tablet with 1 GB Usage
				sel.selectByValue("Device_id2");

				Boolean edtInputBoxForTabletDevice2 = UI.WaitForObject(oR_MobileShareComparisionTool.edtInputBoxForTabletDevice, 30,lDriver);
				oR_MobileShareComparisionTool.edtInputBoxForTabletDevice.clear();
				oR_MobileShareComparisionTool.edtInputBoxForTabletDevice.sendKeys("1");
				Report.TestPoint(testContext.getName(), "Select Tablet with 1 GB Usage", "true", edtInputBoxForTabletDevice2.toString(), true);


				//click on Add button
				Boolean bAdd2 = UI.WaitForObject(oR_MobileShareComparisionTool.btnAddForTabletOnDialogBox, 30,lDriver);
				Report.TestPoint(testContext.getName(), "Click Add button", "true", bAdd2.toString(), true);
				oR_MobileShareComparisionTool.btnAddForTabletOnDialogBox.click();
				Thread.sleep(4000);

				//Verify Message - 'Device(s) added since last mobile share comparison= (2) devices added- Tablet 3GB	- Tablet 1GB'
				try
				{
					WebElement elmMessage1 = lDriver.findElement(By.xpath("//*[contains(text(),'- Tablet 3GB')]"));
					WebElement elmMessage2 = lDriver.findElement(By.xpath("//*[contains(text(),'- Tablet 1GB')]"));
					if(elmMessage1.isDisplayed() && elmMessage2.isDisplayed())
					{
						Report.ValidationPoint(testContext.getName(), "Verify Message - 'devices added - Tablet 3GB - Tablet 1GB'", "Message displayed", "Message displayed", true);
					}
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Verify Message - 'devices added - Tablet 3GB - Tablet 1GB'", "Message displayed", "Message NOT displayed", true);
				}


				//Click on show recommendation button
				Boolean bBtnShowNewComparisonOnDialogBox = UI.WaitForObject(oR_MobileShareComparisionTool.btnShowNewComparisonOnDialogBox, 30,lDriver);
				Report.TestPoint(testContext.getName(), "Click on show recommendation button", "true", bBtnShowNewComparisonOnDialogBox.toString(), true);
				oR_MobileShareComparisionTool.btnShowNewComparisonOnDialogBox.click();

				Thread.sleep(10000);

				//Verify RHS Section is displayed as per the new selected device changes
				try
				{
					lDriver.findElement(By.xpath("//*[contains(text(),'Added')]"));
					Report.TestPoint(testContext.getName(),"Verify Added devices are displayed with respective data usage in recommendation pie chart", "Displayed", "Displayed", true);
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(),"Verify Added devices are displayed with respective data usage in recommendation pie chart", "Displayed", "NOT Displayed", true);
				}

				//Verify Warning Message 'Added Devices not included in Current Plan' when new devices have been added
				try
				{
					WebElement elmAlertMessage = lDriver.findElement(By.xpath("//*[contains(text(),'Added Devices not included in Current Plan')]"));
					UI.WaitForObject(elmAlertMessage, UI.iObjTimeOut, lDriver);
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
			Boolean bTxtPlanDetailsInHeaderMobileSharePlanSection = UI.WaitForObject(oR_MobileShareComparisionTool.txtPlanDetailsInHeaderMobileSharePlanSection, 30,lDriver);
			Report.TestPoint(testContext.getName(), "Click on Plan Details button in Mobile share plan section", "true", bTxtPlanDetailsInHeaderMobileSharePlanSection.toString(), true);
			oR_MobileShareComparisionTool.txtPlanDetailsInHeaderMobileSharePlanSection.click();


			//Verify Plan Detail Page is Displayed
			Boolean bTxtMobileShareDetails = UI.WaitForObject(oR_MobileShareComparisionTool.txtMobileShareDetails, 30,lDriver);
			Report.TestPoint(testContext.getName(), "Verify Plan Detail Page is Displayed", "true", bTxtMobileShareDetails.toString(), true);

			oR_MobileShareComparisionTool.btnCloseOnPlanDetailsPopupForMobileShare.click();
			Report.OperationPoint(testContext.getName(), "Closing Mobile share Plan Detail popup");

			//Verify Compare plans link
			try
			{
				lDriver.findElement(By.xpath("//*[text()='Compare Plans']"));
				Report.TestPoint(testContext.getName(),"Verify 'Compare plans' link is displayed", "Displayed", "Displayed", true);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(),"Verify 'Compare plans' link is displayed", "Displayed", "NOT Displayed", true);
			} 

			//Verify Modify Usage link
			boolean blnkModifyUsage = UI.WaitForObject(oR_MobileShareComparisionTool.lnkModifyUsage, 10,lDriver);
			Report.TestPoint(testContext.getName(),"Verify 'Modify Usage' link", "true", String.valueOf(blnkModifyUsage), true);

			//Verify 'AT&T Next Benefits' link
			try
			{
				lDriver.findElement(By.xpath("//*[text()='AT&T Next']"));
				Report.TestPoint(testContext.getName(),"Verify 'AT&T Next Benefits' link is displayed", "Displayed", "Displayed", true);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(),"Verify 'AT&T Next Benefits' link is displayed", "Displayed", "NOT Displayed", true);
			} 

			//Verify debugger hyperlink 
			boolean blnkDebugger = UI.WaitForObject(oR_Dashboard.lnkDebugger, UI.iObjTimeOut,lDriver);
			Report.TestPoint(testContext.getName(),"Verify debugger hyperlink", "true", String.valueOf(blnkDebugger), true);


		} catch (Exception e){

			String[] errMsg = e.getMessage().split("\\r?\\n"); UI.printMsg(errMsg[0]);
			Report.TestPoint(testContext.getName(), errMsg[0], "True", "False", true);
		}
	}




	public static void validateAllDataTypes(ITestContext testContext) throws HeadlessException, IOException, AWTException 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
		OR_MyATTZone oR_MyATTZone = PageFactory.initElements(lDriver, OR_MyATTZone.class);
		OR_MobileShareComparisionTool oR_MobileShareComparisionTool = PageFactory.initElements(lDriver, OR_MobileShareComparisionTool.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		String sAccessID = IO.GetParamVal(sTestParams, "AccessID");
		String sWirelessCTN = IO.GetParamVal(sTestParams, "Wireless_CTN");
		String sUverseMemberID = IO.GetParamVal(sTestParams, "Uverse_Member_ID");
		String sBAN = IO.GetParamVal(sTestParams, "WirelineBTN");
		Boolean ATTAccessID;
		
		try
		{	
			/** ATT ACCESS ID **/
			ATTAccessID = UI.WaitForObject(oR_Dashboard.elm_ATTAccessID, 10, lDriver);
			if(ATTAccessID)
			{
				Report.ValidationPoint(testContext.getName(), "ATT Access ID", "true", "true", true);
//				oR_Dashboard.elm_ATTAccessID.click();
				Actions action = new Actions(lDriver);
				try
				{
					action.moveToElement(oR_Dashboard.elm_ATTAccessID).build().perform();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					Report.TestPoint(testContext.getName(),"Hover over Access Id", "Hovered", "Failed to hover", true);
				}
				
				
				if(UI.WaitForObject(oR_Dashboard.elm_AccessID, 2, lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Access ID in Menu ATT Access ID", "true", "true", true);
//					oR_Dashboard.elm_AccessID.click();
					try
					{
						action.moveToElement(oR_Dashboard.elm_AccessID).build().perform();

						Thread.sleep(4000);
						Report.TestPoint(testContext.getName(),"Hover over submenu Access Id", "Hovered", "Hovered", true);
						oR_Dashboard.elm_AccessID.click();
					}catch(Exception e)
					{
						Report.TestPoint(testContext.getName(),"Hover over Access Id", "Hovered", "Failed to hover", true);
					}
					
					
					Report.ValidationPoint(testContext.getName(), "AT&T Access ID : Text", UI.WaitForObject(oR_Dashboard.txtATTaccessId, 2, lDriver).toString(), "true", true);
					if(UI.WaitForObject(oR_Dashboard.elm_ATTAccessIDTxtField, 2, lDriver))
					{
						Report.ValidationPoint(testContext.getName(), "AT&T Access ID Edit box", UI.WaitForObject(oR_Dashboard.elm_ATTAccessIDTxtField, 2, lDriver).toString(), "true", true);
						oR_Dashboard.elm_ATTAccessIDTxtField.clear();
						oR_Dashboard.elm_ATTAccessIDTxtField.sendKeys(sAccessID);
						oR_Dashboard.btnSearch.click();
						JavascriptExecutor jse = (JavascriptExecutor)lDriver;
						jse.executeScript("window.scrollBy(0,250)", "");
						Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile", UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30, lDriver).toString(), "true", true);
						Report.ValidationPoint(testContext.getName(), "Button Subscriber Management", UI.WaitForObject(oR_Dashboard.btnSubscriberManagement, 2, lDriver).toString(), "true", true);
						Report.ValidationPoint(testContext.getName(), "Button Customer Activity", UI.WaitForObject(oR_Dashboard.btnCustomerActivity, 2, lDriver).toString(), "true", true);
					}
				}
				else 	 Report.ValidationPoint(testContext.getName(), "Access ID in Menu ATT Access ID", "true", "false", true);

				
			}
			else Report.ValidationPoint(testContext.getName(), "ATT Access ID", "true", "false", true);
			/** ATT ACCESS ID  ENDS**/
			
			/** WIRELESS **/
			ATTAccessID = UI.WaitForObject(oR_Dashboard.txtWireless, 10, lDriver);
			if(ATTAccessID)
			{
				Report.ValidationPoint(testContext.getName(), "Wireless in Search Menu", "true", "true", true);
				oR_Dashboard.txtWireless.click();
				if(UI.WaitForObject(oR_Dashboard.elm_WirelessCTN, 2, lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "CTN in Menu Wireless", "true", "true", true);
					oR_Dashboard.elm_WirelessCTN.click();
					
					Report.ValidationPoint(testContext.getName(), "Mobile Number : Text", UI.WaitForObject(oR_Dashboard.txtMobileNumber, 2, lDriver).toString(), "true", true);
					if(UI.WaitForObject(oR_Dashboard.edtWirelessCTN, 2, lDriver))
					{
						Report.ValidationPoint(testContext.getName(), "Mobile Number Edit box", UI.WaitForObject(oR_Dashboard.edtWirelessCTN, 2, lDriver).toString(), "true", true);
						oR_Dashboard.edtWirelessCTN.clear();
						oR_Dashboard.edtWirelessCTN.sendKeys(sWirelessCTN);
						oR_Dashboard.btnSearch.click();
						if(UI.WaitForObject(oR_Dashboard.radSelectSlid, 8, lDriver))
						{
							oR_Dashboard.radSelectSlid.click();
							Report.ValidationPoint(testContext.getName(), "Select Login ID", "true" , "true", true);
							oR_Dashboard.btnContinue.click();
						}
							Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile", UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30, lDriver).toString(), "true", true);
							Report.ValidationPoint(testContext.getName(), "Button Subscriber Management", UI.WaitForObject(oR_Dashboard.btnSubscriberManagement, 2, lDriver).toString(), "true", true);
							Report.ValidationPoint(testContext.getName(), "Button Customer Activity", UI.WaitForObject(oR_Dashboard.btnCustomerActivity, 2, lDriver).toString(), "true", true);
						
					}
				}
				else 	 Report.ValidationPoint(testContext.getName(), "Access ID in Menu ATT Access ID", "true", "false", true);

				
			}
			else Report.ValidationPoint(testContext.getName(), "ATT Access ID", "true", "false", true);
			/** WIRELESS ENDS**/
			
			/** UVERSE **/
			ATTAccessID = UI.WaitForObject(oR_Dashboard.elm_DSL, 10, lDriver);
			if(ATTAccessID)
			{
				Report.ValidationPoint(testContext.getName(), "Uverse in Search Menu", "true", "true", true);
				oR_Dashboard.elm_DSL.click();
				if(UI.WaitForObject(oR_Dashboard.elm_MemberID, 2, lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Member ID in Menu Uverse", "true", "true", true);
					oR_Dashboard.elm_MemberID.click();
					
					Report.ValidationPoint(testContext.getName(), "U-verse Member ID: Text", UI.WaitForObject(oR_Dashboard.elm_UverseMemberId, 2, lDriver).toString(), "true", true);
					if(UI.WaitForObject(oR_Dashboard.edtUverseMID, 2, lDriver))
					{
						Report.ValidationPoint(testContext.getName(), "U-verse Member ID Edit box", UI.WaitForObject(oR_Dashboard.edtUverseMID, 2, lDriver).toString(), "true", true);
						oR_Dashboard.edtUverseMID.clear();
						oR_Dashboard.edtUverseMID.sendKeys(sUverseMemberID);
						oR_Dashboard.btnSearch.click();
						if(UI.WaitForObject(oR_Dashboard.radSelectSlid, 8, lDriver))
						{
							oR_Dashboard.radSelectSlid.click();
							Report.ValidationPoint(testContext.getName(), "Select Login ID", "true" , "true", true);
							oR_Dashboard.btnContinue.click();
						}
							Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile", UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30, lDriver).toString(), "true", true);
							Report.ValidationPoint(testContext.getName(), "Button Subscriber Management", UI.WaitForObject(oR_Dashboard.btnSubscriberManagement, 2, lDriver).toString(), "true", true);
							Report.ValidationPoint(testContext.getName(), "Button Customer Activity", UI.WaitForObject(oR_Dashboard.btnCustomerActivity, 2, lDriver).toString(), "true", true);
						
					}
				}
				else 	 Report.ValidationPoint(testContext.getName(), "Access ID in Menu ATT Access ID", "true", "false", true);

				
			}
			else Report.ValidationPoint(testContext.getName(), "ATT Access ID", "true", "false", true);
			/** UVERSE ENDS**/
			
			

			/** WIRELINE **/
			ATTAccessID = UI.WaitForObject(oR_Dashboard.elmWireline, 10, lDriver);
			if(ATTAccessID)
			{
				Report.ValidationPoint(testContext.getName(), "Uverse in Search Menu", "true", "true", true);
				oR_Dashboard.elmWireline.click();
				if(UI.WaitForObject(oR_Dashboard.elm_WirelineBTN, 2, lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "BTN in Menu Wireline", "true", "true", true);
					oR_Dashboard.elm_WirelineBTN.click();
					
					Report.ValidationPoint(testContext.getName(), "Wireline BTN: Text", UI.WaitForObject(oR_Dashboard.txtWirelineBTN, 2, lDriver).toString(), "true", true);
					if(UI.WaitForObject(oR_Dashboard.edtWirelineBTN, 2, lDriver))
					{
						Report.ValidationPoint(testContext.getName(), "U-verse Member ID Edit box", UI.WaitForObject(oR_Dashboard.edtWirelessCTN, 2, lDriver).toString(), "true", true);
						oR_Dashboard.edtWirelineBTN.clear();
						oR_Dashboard.edtWirelineBTN.sendKeys(sUverseMemberID);
						oR_Dashboard.btnSearch.click();
						if(UI.WaitForObject(oR_Dashboard.radSelectSlid, 8, lDriver))
						{
							oR_Dashboard.radSelectSlid.click();
							Report.ValidationPoint(testContext.getName(), "Select Login ID", "true" , "true", true);
							oR_Dashboard.btnContinue.click();
						}
							Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile", UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30, lDriver).toString(), "true", true);
							Report.ValidationPoint(testContext.getName(), "Button Subscriber Management", UI.WaitForObject(oR_Dashboard.btnSubscriberManagement, 2, lDriver).toString(), "true", true);
							Report.ValidationPoint(testContext.getName(), "Button Customer Activity", UI.WaitForObject(oR_Dashboard.btnCustomerActivity, 2, lDriver).toString(), "true", true);
						
					}
				}
				else 	 Report.ValidationPoint(testContext.getName(), "Access ID in Menu ATT Access ID", "true", "false", true);

				
			}
			else Report.ValidationPoint(testContext.getName(), "ATT Access ID", "true", "false", true);
			/** WIRELINE ENDS**/
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Validate all Data types", "true", "False", true);
		}
	}




	public static void ImpersonateMobileAndWeb(ITestContext testContext) throws HeadlessException, IOException, AWTException 
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
			OR_MyATTZone oR_MyATTZone = PageFactory.initElements(lDriver, OR_MyATTZone.class);

			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			String sAccessID = IO.GetParamVal(sTestParams, "AccessID");
			String sWirelessCTN = IO.GetParamVal(sTestParams, "Wireless_CTN");
//			String sWirelessBAN = IO.GetParamVal(sTestParams, "Wireless_BAN");
			String sUverseMemberID = IO.GetParamVal(sTestParams, "Uverse_Member_ID");
//			String sUverseBAN = IO.GetParamVal(sTestParams, "Uverse_BAN");
			String sWirelineBTN = IO.GetParamVal(sTestParams, "WirelineBTN");

			myATT_Zone.Validate_AccessCustomerAccounts(testContext);
			
			
			/********************************* IMPERSONATE ACCESSID ACCOUNT *******************************************************/
			Report.OperationPoint(testContext.getName(), "IMPERSONATE ACCESSID ACCOUNT" );
			

			//Hover over Access Id 

			Actions action = new Actions(lDriver);
			try
			{
				action.moveToElement(oR_Dashboard.elm_ATTAccessID).build().perform();

				Thread.sleep(1000);
				Report.TestPoint(testContext.getName(),"Hover over Access Id", "Hovered", "Hovered", true);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(),"Hover over Access Id", "Hovered", "Failed to hover", true);
			}

			//Move to Access Id

			try
			{
				Thread.sleep(2000);
				if(oR_Dashboard.elm_AccessID.isDisplayed())
				{
					try
					{
						action.moveToElement(oR_Dashboard.elm_AccessID).build().perform();

						Thread.sleep(4000);
						Report.TestPoint(testContext.getName(),"Hover over submenu Access Id", "Hovered", "Hovered", true);
						oR_Dashboard.elm_AccessID.click();
						Thread.sleep(4000);
						Report.TestPoint(testContext.getName(),"Click on submenu Access Id", "Clicked", "Clicked", true);
					}
					catch(Exception e)
					{
						Report.TestPoint(testContext.getName(),"Hover over Access Id", "Hovered", "Failed to hover", true);
					}
				}
				else
				{
					oR_Dashboard.elm_ATTAccessID.click();
					Thread.sleep(5000);
					Report.TestPoint(testContext.getName(),"Click AT&T access id from search menu", "Clicked", "Clicked", true);


				}
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(),"Click AT&T access id from search menu", "Clicked", "FAILED to click", true);
			}

			//Verify AT&T access id field is displayed
			boolean bTxtATTaccessId = UI.WaitForObject(oR_Dashboard.txtATTaccessId, 10,lDriver);
			Report.TestPoint(testContext.getName(),"Verify AT&T access id field is displayed", "true", String.valueOf(bTxtATTaccessId), true);

			//Verify AT&T access id input box is displayed
			boolean bEdtATTaccessIdSearchField = UI.WaitForObject(oR_Dashboard.elm_ATTAccessIDTxtField, 10,lDriver);
			Report.TestPoint(testContext.getName(),"Verify AT&T access id input box is displayed", "true", String.valueOf(bEdtATTaccessIdSearchField), true);

			//Verify search button
			boolean bBtnSearch = UI.WaitForObject(oR_Dashboard.btnSearch, 10,lDriver);
			Report.TestPoint(testContext.getName(),"Verify search button", "true", String.valueOf(bBtnSearch), true);

			//Enter the Access Id and press Search button
			
			Report.OperationPoint(testContext.getName(), "Enter " +sAccessID+ " in Text Box");

			oR_Dashboard.elm_ATTAccessIDTxtField.sendKeys(sAccessID);
			Report.TestPoint(testContext.getName(),"Enter the Access Id", "Entered", "Entered", true);

			oR_Dashboard.btnSearch.click();
			Thread.sleep(4000);

			//Select CTN/access id from popup
			try
			{
//				lDriver.findElement(By.xpath("//input[@type='radio']")).click();
				oR_Dashboard.radSelectSlid.click();

				Thread.sleep(2000);
				Report.TestPoint(testContext.getName(),"Select CTN/access id with radio button from popup", "Selected", "Selected", true);
				//Click on continue button
				oR_Dashboard.btnContinue.click();
				Thread.sleep(4000);
			}
			catch(Exception e)
			{

			}

			//Verify all the details about the access Id are displayed properly
			try
			{
				lDriver.findElement(By.xpath("//*[contains(text(),'AT&T Login ID')]"));
				lDriver.findElement(By.xpath("//*[contains(text(),'Name')]"));
				
				if(oR_Dashboard.btnMirrorMyATTMobileApp.isDisplayed())
				{
					JavascriptExecutor jse = (JavascriptExecutor)lDriver;
					jse.executeScript("window.scrollBy(0,250)", "");
					if(UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30, lDriver))	
					{
						Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile","true" , "true", true);
						Impersonate(testContext);
					}
					
				}
				else
				{
					Report.ValidationPoint(testContext.getName(),"Button Mirror MyATT Mobile", "Displayed", "NOT Displayed", true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(),"Verify all the details about the access Id are displayed properly", "Displayed", "NOT Displayed", true);
			}
			
			/********************************* IMPERSONATE ACCESSID ACCOUNT ENDS *******************************************************/
		

			//Click on account tab 
			lDriver.switchTo().frame(oR_MyATTZone.frmTabs);
			boolean bLnkAccounts = UI.WaitForObject(oR_Dashboard.lnkAccounts, 10, lDriver);
			oR_Dashboard.lnkAccounts.click();
			Thread.sleep(9000);
			Report.TestPoint(testContext.getName(),"Click on account tab", "true", String.valueOf(bLnkAccounts), true);
			lDriver.switchTo().defaultContent();
			
			/********************************* IMPERSONATE WIRELESS ACCOUNT *******************************************************/
			Report.OperationPoint(testContext.getName(), "IMPERSONATE WIRELESS ACCOUNT" );
			
			boolean bTxtWireless = UI.WaitForObject(oR_Dashboard.txtWireless, 10, lDriver);
			Report.TestPoint(testContext.getName(),"Verify Wireless tab", "true", String.valueOf(bTxtWireless), true);

			action = new Actions(lDriver);
			try
			{
				action.moveToElement(oR_Dashboard.txtWireless).build().perform();

				Thread.sleep(4000);
				Report.TestPoint(testContext.getName(),"Hover over Wireless", "Hovered", "Hovered", true);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(),"Hover over Wireless", "Hovered", "Failed to hover", true);
			}
			
			//Select Wireless BAN/CTN
			if(!(sWirelessCTN.equals("NA")))
			{
				System.out.println("CTN entered "+sWirelessCTN);


				//Verify CTN will be displayed in dropdown
				if(oR_Dashboard.elm_WirelessCTN.isDisplayed())
				{
					Report.TestPoint(testContext.getName(),"Verify CTN displayed in dropdown", "Displayed", "Displayed", true);
				}
				else
				{
					Report.TestPoint(testContext.getName(),"Verify CTN displayed in dropdown", "Displayed", "NOT Displayed", true);
				}

				//Select CTN from the Wireless drop-down menu         txtMobileNumber  edtWirelessCTN
				oR_Dashboard.elm_WirelessCTN.click();
				Thread.sleep(5000);
				Report.TestPoint(testContext.getName(),"Select CTN from the Wireless drop-down menu", "Selected", "Selected", true);

				//Verify Mobile Number field is displayed
				boolean bTxtMobileNumber = UI.WaitForObject(oR_Dashboard.txtMobileNumber, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify Mobile Number field is displayed", "true", String.valueOf(bTxtMobileNumber), true);

				//Verify Mobile Number input box is displayed
				boolean bEdtWirelessCTN = UI.WaitForObject(oR_Dashboard.edtWirelessCTN, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify Mobile Number input box is displayed", "true", String.valueOf(bEdtWirelessCTN), true);

				//Verify search button
				boolean bBtnSearch1 = UI.WaitForObject(oR_Dashboard.btnSearch, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify search button", "true", String.valueOf(bBtnSearch1), true);

				//Enter mobile number in input box
				Report.OperationPoint(testContext.getName(), "Enter " +sWirelessCTN+ " in Text Box");

				oR_Dashboard.edtWirelessCTN.sendKeys(sWirelessCTN);
				Thread.sleep(2000);
				Report.TestPoint(testContext.getName(),"Enter mobile number in input box", "Entered", "Entered", true);

				oR_Dashboard.btnSearch.click();
				Thread.sleep(4000);

				//Select CTN/access id from popup
				try
				{
//					lDriver.findElement(By.xpath("//input[@type='radio'][contains(@onclick,'CTN')]")).click();
					oR_Dashboard.radSelectSlid.click();
					Thread.sleep(2000);
					Report.TestPoint(testContext.getName(),"Select CTN/access id with radio button from popup", "Selected", "Selected", true);
					//Click on continue button
					oR_Dashboard.btnContinue.click();
					Thread.sleep(4000);
				}
				catch(Exception e)
				{

				}



				//Verify all the details about the Wireless CTN are displayed properly
				try
				{
					WebElement elmMobileNumber = lDriver.findElement(By.xpath("//*[text()='Mobile Number']"));
					WebElement elmMobileName = lDriver.findElement(By.xpath("//*[text()='Mobile Name']"));
					
					if(oR_Dashboard.btnMirrorMyATTMobileApp.isDisplayed())
					{
						JavascriptExecutor jse = (JavascriptExecutor)lDriver;
						jse.executeScript("window.scrollBy(0,250)", "");
						if(UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30, lDriver))	
						{
							Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile","true" , "true", true);
							Impersonate(testContext);
						}
						
					}
					else
					{
						Report.ValidationPoint(testContext.getName(),"Button Mirror MyATT Mobile", "Displayed", "NOT Displayed", true);
					}
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(),"Verify all the details about the Wireless CTN are displayed properly", "Displayed", "NOT Displayed", true);
				}

			}
			
			/********************************* IMPERSONATE WIRELESS ACCOUNT ENDS *******************************************************/
			//Click on account tab again
			lDriver.switchTo().frame(oR_MyATTZone.frmTabs);
			bLnkAccounts = UI.WaitForObject(oR_Dashboard.lnkAccounts, 10, lDriver);
			oR_Dashboard.lnkAccounts.click();
			Thread.sleep(9000);
			Report.TestPoint(testContext.getName(),"Click on account tab", "true", String.valueOf(bLnkAccounts), true);
			lDriver.switchTo().defaultContent();
			/********************************* IMPERSONATE UVERSE ACCOUNT *******************************************************/
			Report.OperationPoint(testContext.getName(), "IMPERSONATE UVERSE ACCOUNT" );
			//Hover over Uverse 
			boolean bElm_Uverse = UI.WaitForObject(oR_Dashboard.elm_DSL, 10,lDriver);
			Report.TestPoint(testContext.getName(),"Verify Uverse tab", "true", String.valueOf(bElm_Uverse), true);

			Actions action2 = new Actions(lDriver);
			try
			{
				action2.moveToElement(oR_Dashboard.elm_DSL).build().perform();

				Thread.sleep(4000);
				Report.TestPoint(testContext.getName(),"Hover over Uverse", "Hovered", "Hovered", true);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(),"Hover over Uverse", "Hovered", "Failed to hover", true);
			}

			//Select Uverse Member ID/BAN
			if(!(sUverseMemberID.equals("NA")))
			{
				//Verify Uverse Member ID will be displayed in dropdown
				if(oR_Dashboard.elm_MemberID.isDisplayed())
				{
					Report.TestPoint(testContext.getName(),"Verify Uverse Member ID displayed in dropdown", "Displayed", "Displayed", true);
				}
				else
				{
					Report.TestPoint(testContext.getName(),"Verify Uverse Member ID displayed in dropdown", "Displayed", "NOT Displayed", true);
				}

				//Select Member ID from the Uverse drop-down menu         
				oR_Dashboard.elm_MemberID.click();
				Thread.sleep(2000);
				Report.TestPoint(testContext.getName(),"Select Member ID from the Uverse drop-down menu", "Selected", "Selected", true);

				//Verify Uverse Member ID field is displayed
				boolean bElm_UverseMemberId = UI.WaitForObject(oR_Dashboard.elm_UverseMemberId, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify Uverse Member ID field is displayed", "true", String.valueOf(bElm_UverseMemberId), true);

				//Verify Uverse MID input box is displayed
				boolean bEdtUverseMID = UI.WaitForObject(oR_Dashboard.edtUverseMID, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify Uverse MID input box is displayed", "true", String.valueOf(bEdtUverseMID), true);

				//Verify search button
				boolean bBtnSearch1 = UI.WaitForObject(oR_Dashboard.btnSearch, 10,lDriver);
				Report.TestPoint(testContext.getName(),"Verify search button", "true", String.valueOf(bBtnSearch1), true);

				//Enter Uverse MID in input box
				Report.OperationPoint(testContext.getName(), "Enter " +sUverseMemberID+ " in Text Box");

				oR_Dashboard.edtUverseMID.sendKeys(sUverseMemberID);
				Thread.sleep(2000);
				Report.TestPoint(testContext.getName(),"Enter Uverse MID in input box", "Entered", "Entered", true);

				oR_Dashboard.btnSearch.click();
				Thread.sleep(4000);

				//Select CTN/access id from popup
				try
				{
					oR_Dashboard.radSelectSlid.click();
					Thread.sleep(2000);
					Report.TestPoint(testContext.getName(),"Select CTN/access id with radio button from popup", "Selected", "Selected", true);
					//Click on continue button
					oR_Dashboard.btnContinue.click();
					Thread.sleep(4000);
				}
				catch(Exception e)
				{

				}

				//Verify all the details about the Uverse Member Id are displayed properly
				try
				{
					lDriver.findElement(By.xpath("//*[text()='Member ID Details'] | //*[contains(text(),'Member ID:')] | //*[contains(text(),'Name:')] | //*[contains(text(),'Name')]"));

					//WebElement elmMemberIDdetails = lDriver.findElement(By.xpath("//*[text()='Member ID Details']"));
					//WebElement elmMemberId = lDriver.findElement(By.xpath("//*[contains(text(),'Member ID:')]"));
					//WebElement elmName = lDriver.findElement(By.xpath("//*[contains(text(),'Name:')]"));
					JavascriptExecutor jse = (JavascriptExecutor)lDriver;
					jse.executeScript("window.scrollBy(0,250)", "");
					Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile", UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30, lDriver).toString(), "true", true);
					if(UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30, lDriver))	
					{
						Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile","true" , "true", true);
						Impersonate(testContext);
					}

				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(),"Verify all the details about the Uverse Member Id are displayed properly", "Displayed", "NOT Displayed", true);
				}
			}
			/******************************************************** IMPERSONATE UVERSE ACCOUNT ENDS *******************************************************/
			//Click on account tab again
			lDriver.switchTo().frame(oR_MyATTZone.frmTabs);
			bLnkAccounts = UI.WaitForObject(oR_Dashboard.lnkAccounts, 10, lDriver);
			oR_Dashboard.lnkAccounts.click();
			Thread.sleep(9000);
			Report.TestPoint(testContext.getName(),"Click on account tab", "true", String.valueOf(bLnkAccounts), true);
			lDriver.switchTo().defaultContent();
			
			/********************************* IMPERSONATE WIRELINE ACCOUNT *******************************************************/
			Report.OperationPoint(testContext.getName(), "IMPERSONATE WIRELINE ACCOUNT" );
			Boolean wireline;
			wireline = UI.WaitForObject(oR_Dashboard.elmWireline, 10, lDriver);
			if(wireline)
			{
				Report.ValidationPoint(testContext.getName(), "Uverse in Search Menu", "true", "true", true);
				oR_Dashboard.elmWireline.click();
				if(UI.WaitForObject(oR_Dashboard.elm_WirelineBTN, 2, lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "BTN in Menu Wireline", "true", "true", true);
					oR_Dashboard.elm_WirelineBTN.click();
					
					Report.ValidationPoint(testContext.getName(), "Wireline BTN: Text", UI.WaitForObject(oR_Dashboard.txtWirelineBTN, 2, lDriver).toString(), "true", true);
					if(UI.WaitForObject(oR_Dashboard.edtWirelineBTN, 2, lDriver))
					{
						Report.ValidationPoint(testContext.getName(), "Wireline BTN ID Edit box", UI.WaitForObject(oR_Dashboard.edtWirelineBTN, 2, lDriver).toString(), "true", true);
						oR_Dashboard.edtWirelineBTN.clear();
						Report.OperationPoint(testContext.getName(), "Enter " +sWirelineBTN+ " in Text Box");
	
						oR_Dashboard.edtWirelineBTN.sendKeys(sWirelineBTN);
						oR_Dashboard.btnSearch.click();
						if(UI.WaitForObject(oR_Dashboard.radSelectSlid, 8, lDriver))
						{
							oR_Dashboard.radSelectSlid.click();
							Report.ValidationPoint(testContext.getName(), "Select Login ID", "true" , "true", true);
							oR_Dashboard.btnContinue.click();
						}
							Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile", UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30, lDriver).toString(), "true", true);
							if(UI.WaitForObject(oR_Dashboard.btnMirrorMyATTMobileApp, 30, lDriver))	
							{
								Report.ValidationPoint(testContext.getName(), "Button Mirror My AT&T Mobile","true" , "true", true);
								Impersonate(testContext);
							}
					}
				}
				else 	 Report.ValidationPoint(testContext.getName(), "Access ID in Menu ATT Access ID", "true", "false", true);

				
			}
			else Report.ValidationPoint(testContext.getName(), "ATT Access ID", "true", "false", true);
			
			
			/********************************* IMPERSONATE WIRELINE ACCOUNT ENDS*******************************************************/

		}	
		catch(Exception e)
		{          
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}




	private static void Impersonate(ITestContext testContext) throws HeadlessException, IOException, AWTException 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
		OR_MyATTZone oR_MyATTZone = PageFactory.initElements(lDriver, OR_MyATTZone.class);
		
		oR_Dashboard.btnMirrorMyATTMobileApp.click();
		try
		{
			lDriver.switchTo().frame(oR_MyATTZone.frmMobileInpersonate);
			if(UI.WaitForObject(oR_MyATTZone.txtWelcome, 120,lDriver))
				Report.ValidationPoint(testContext.getName(), "Mobile Impersonation", "true", "true", true);
			else
				Report.ValidationPoint(testContext.getName(), "Mobile Impersonation", "true", "false", true);
			
			lDriver.switchTo().defaultContent();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.ValidationPoint(testContext.getName(), "Mobile Impersonation", "true", e.getMessage().toString(), true);

		}
		try
		{
			lDriver.switchTo().frame(oR_MyATTZone.frmTabs);
			if(UI.WaitForObject(oR_Dashboard.btnMirrorMyATTWeb, 10,lDriver))
			{
				Report.ValidationPoint(testContext.getName(), "Check for link Mirror MyATT Web", "true", "true", true);

				oR_Dashboard.btnMirrorMyATTWeb.click();
				lDriver.switchTo().defaultContent();
			}
			else 	Report.ValidationPoint(testContext.getName(), "Check for link Mirror MyATT Web", "true", "false", true);

			
			lDriver.switchTo().frame(oR_MyATTZone.frmWebImpersonate);
			
			if(UI.WaitForObject(oR_MyATTZone.txtWelcome, 120, lDriver))
				Report.ValidationPoint(testContext.getName(), "Web Impersonation", "true", "true", true);
			else
				Report.ValidationPoint(testContext.getName(), "Web Impersonation", "true", "false", true);	
			
			lDriver.switchTo().defaultContent();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.ValidationPoint(testContext.getName(), "Web Impersonation", "true", e.getMessage().toString(), true);
		}
		
	}
	
	

}


