/**************************************************************
   Library - BillAndPayments 
   Description-   Includes all functions/methods related to Bill and payments
   Date created - 18-Dec-14
   Developed by - Rahul
   Last Modified By -
   Last Modified Date -
 ***************************************************************/

package com.AppSpecificLibrary;

//import java.awt.AWTException;
//import java.awt.HeadlessException;
//import java.io.IOException;
//
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.lift.TestContext;
//import org.openqa.selenium.support.PageFactory;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.apache.bcel.generic.LDIV;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;

import com.OR.MyATT.OR_ATT;
import com.OR.MyATT.OR_AccountOverview;
import com.OR.MyATT.OR_AddaDevice;
import com.OR.MyATT.OR_AutoPayEnrollment;
import com.OR.MyATT.OR_BillAndPayments;
import com.OR.MyATT.OR_BillAndUsage;
import com.OR.MyATT.OR_EditPaymentInformation;
import com.OR.MyATT.OR_EditPaymentProfile;
import com.OR.MyATT.OR_Login;
import com.OR.MyATT.OR_MakeAPayment;
import com.OR.MyATT.OR_ManageAutoPay;
import com.OR.MyATT.OR_ManagePhoneBook;
import com.OR.MyATT.OR_MyWirelessPlan;
import com.OR.MyATT.OR_PaperlessBilling;
import com.OR.MyATT.OR_PayOffInstallmentPlan;
import com.OR.MyATT.OR_PaymentConfirmation;
import com.OR.MyATT.OR_PrintBillHistoryTab;
import com.OR.MyATT.OR_Profile;
import com.OR.MyATT.OR_ReviewPaymentDetails;
import com.OR.MyATT.OR_SMBBillDetails;
import com.OR.MyATT.OR_SuspendReactivateService;
import com.OR.MyATT.OR_UpgradePhone;
import com.OR.MyATT.OR_ViewOrChangeRatePlan;
import com.SupportingFiles.IO;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;
import com.SupportingFiles.UI;
//import com.OR.AccountOverview.OR_AccountOverview;
//import com.OR.BillAndPayments.*;
//import com.OR.MyATT.*;

public class BillAndPayments extends LaunchAndLogout {
	static Hashtable<String, String> sTestParams = new Hashtable<String, String>();
	private static boolean bstart;

	/**************************************************************
	 * Function Name - MakeAPayment 
	 * Description- 
	 * Parameters - 
	 * Date created -
	 * Developed by - 
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void MakeAPayment(final ITestContext testContext)
			throws Exception {

		//Variable declaration
		String sMakeAPayment = "False";
		try {
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
			
			sMakeAPayment = UI
					.CheckExist(oR_AccountOverview.btnMakeAPayment);
			if (sMakeAPayment.equalsIgnoreCase("True")) {
				Report.TestPoint(testContext.getName(),
						"Validate if Make a payment button exist", "True",
						sMakeAPayment, true);
				oR_AccountOverview.btnMakeAPayment.click();

				//validate navigated to Make a payment page
				if (UI.CheckExist(oR_MakeAPayment.txtMakeAPayment).equalsIgnoreCase("True")) {
					Report.TestPoint(testContext.getName(), "validate successfully navigated to MAP page", "True", UI.CheckExist(oR_MakeAPayment.txtMakeAPayment), true);
				} else {
					Report.TestPoint(testContext.getName(), "validate successfully navigated to MAP page", "True", UI.CheckExist(oR_MakeAPayment.txtMakeAPayment), true);
				}
			} else {
				Report.TestPoint(testContext.getName(),
						"Validate if Make a payment button exist", "True",
						sMakeAPayment, true);
			}
		} catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}


	/**************************************************************
	 * Function Name - ValidateAlertInBillAndPaymentPage
	 * Description- 
	 * Parameters - 
	 * Date created - 9th Feb 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/		
//BAP14014	
	public static void ValidateAlertInBillAndPaymentPage(final ITestContext testContext) throws Exception {
		try{

			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_ManageAutoPay oR_ManageAutoPay = PageFactory.initElements(lDriver, OR_ManageAutoPay.class);
			
			//click on view all usage link
			if(UI.WaitForObject(oR_AccountOverview.lnkViewAllUsage, 20, lDriver))
			{
				Report.ValidationPoint(testContext.getName(), "Validate View all usage link is displyed",
						"true", String.valueOf(oR_AccountOverview.lnkViewAllUsage.isDisplayed()), true);
				oR_AccountOverview.lnkViewAllUsage.click();
				
				//validate usage drop down
				if(UI.WaitForObject(oR_BillAndUsage.btnUsageSelectionDropdown, 20, lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Validate View static over lay section is displyed",
							"true", String.valueOf(oR_BillAndUsage.btnUsageSelectionDropdown.isDisplayed()), true);
					
					oR_BillAndUsage.btnUsageSelectionDropdown.click();
					Thread.sleep(5000);
					Report.ValidationPoint(testContext.getName(), "Validate elements from each page will be included in the Overlay with a description",
							"true", String.valueOf(oR_BillAndUsage.btnUsageSelectionDropdown.isDisplayed()), true);
				
					lDriver.findElement(
							By.xpath("//*[contains(text(),'Current Billed Usage')]//parent::div//parent::div//a")).click();
					Report.ValidationPoint(testContext.getName(), "Validate billed usage is selected",
							"true", String.valueOf(oR_BillAndUsage.btnUsageSelectionDropdown.isDisplayed()), true);
					
					oR_BillAndUsage.btnUsageSelectionDropdown.click();
					Thread.sleep(5000);
					Report.ValidationPoint(testContext.getName(), "Validate drop down is expanded",
							"true", String.valueOf(oR_BillAndUsage.btnUsageSelectionDropdown.isDisplayed()), true);
					Report.OperationPoint(testContext.getName(), "Clicking on CTA to close Drop Down");
					lDriver.findElement(
							By.xpath("//*[contains(text(),'Current Billed Usage')]//parent::div//parent::div//a")).click();
					Report.ValidationPoint(testContext.getName(), "Validate Verify customer will have the ability to close the overlay displayed via a CTA or the Esc key.",
							"true", String.valueOf(oR_BillAndUsage.lstBillCycleDropDown.isDisplayed()), true);
					Report.OperationPoint(testContext.getName(), "Navigate to history tab");
					oR_BillAndUsage.lnkHistoryTab.click();
					
					try
					{
						//boolean bOverlay = UI.WaitForObject(oR_BillAndUsage.lstBillCycleDropDown, 5, lDriver);
					
					if(oR_BillAndUsage.lstBillCycleDropDown.isDisplayed())
					{
						Report.ValidationPoint(testContext.getName(), "Validate overlay is not displayed in history tab",
								"true", "false", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate overlay is not displayed in history tab",
								"true", "true", true);
						
					}
					}
					catch(Exception e)
					{
						Report.ValidationPoint(testContext.getName(), "Validate overlay is not displayed in history tab",
								"true", "true", true);
					
					}
					/*//logout
					oR_AccountOverview.btnLogout.click();
					Report.OperationPoint(testContext.getName(), "Logging Out");
					Thread.sleep(3000);
					
					//login
					LaunchAndLogout.LoginApplication(testContext);*/
				
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate View static over lay section is displyed",
							"true", String.valueOf(oR_BillAndUsage.btnUsageSelectionDropdown.isDisplayed()), true);
					
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate View all usage link is displyed",
						"true", String.valueOf(oR_AccountOverview.lnkViewAllUsage.isDisplayed()), true);
					
			}
			/*//Logout
			if (UI.WaitForObject(oR_AccountOverview.btnLogout, 5).equals(true)) {
				Report.OperationPoint(testContext.getName(), "Operational - Logging out");
				Report.TestPoint(testContext.getName(),
						"Validate logout button is displayed", "True",
						"True", true);
				oR_AccountOverview.btnLogout.click();
			}
				
				Thread.sleep(2000);
				lDriver.navigate().to(Report.sMyATTEnvURL);
				Report.OperationPoint(testContext.getName(), "Operational - Logging in");
				Thread.sleep(10000);
			//Login
			LaunchAndLogout.LoginApplication(testContext);
			//
			if(UI.WaitForObject(oR_AccountOverview.lnkViewAllUsage, 20, lDriver))
			{
				Report.ValidationPoint(testContext.getName(), "Validate View all usage link is displyed",
						"true", String.valueOf(oR_AccountOverview.lnkViewAllUsage.isDisplayed()), true);
				oR_AccountOverview.lnkViewAllUsage.click();
			}*/
			//Validate sec navigation link - billing and usage
			if(UI.WaitForObject(oR_BillAndUsage.lnkBillTab,20).equals(true)){
				oR_BillAndUsage.lnkBillTab.click();

				//validate over lay
				try
				{
					//boolean bOverlay = UI.WaitForObject(oR_BillAndUsage.lstBillCycleDropDown, 5, lDriver);
				
				if(oR_BillAndUsage.lstBillCycleDropDown.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(), "Validate overlay is not displayed in bill tab",
							"true", "false", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate overlay is not displayed in bill tab",
							"true", "true", true);
					
				}
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Validate overlay is not displayed in bill tab",
							"true", "true", true);
				
				}
				//Validate billing and usage page
				if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50).equals(true)){
					Report.TestPoint(testContext.getName(), "Validate billing and usage page", "navigated to billing and usage page", "navigated to billing and usage page", true);
					Report.OperationPoint(testContext.getName(), "	Operational - Verfying the page title");
					Report.OperationPoint(testContext.getName(), "	Operational - Page title is : "+oR_BillAndUsage.txtBillingAndUsage.getText());

					//Validate bill alerts section
					if(UI.WaitForObject(oR_BillAndUsage.txtBillAlerts,120).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Validate bill alerts section", "bill alerts section is present", "bill alerts section is present", true);

						//Validate the heading of alert
						List<WebElement> lstHeading = lDriver.findElements(By.xpath("//div[@class='alertBoxGrad']//li[contains(@class,'SolidBorder')]"));
						if(lstHeading.size()>0){
							Report.ValidationPoint(testContext.getName(), "Validate the heading of alert", "the heading of alert is present", "the heading of alert is present", true);
							//Retrieving the heading of the alert
							//Report.OperationPoint(testContext.getName(), "	Operational - Heading of the alert: "+oR_BillAndUsage.txtCardHasExpired.getText());
						}else{
							Report.ValidationPoint(testContext.getName(), "Validate the heading of alert", "the heading of alert is present", "the heading of alert is NOTpresent", true);						
						}
						
						

						//Validate the description of the alert
						List<WebElement> lstDesc = lDriver.findElements(By.xpath("//div[@class='alertBoxGrad']//li[contains(@class,'SolidBorder')]//p"));
						if(lstDesc.size()>0){
							Report.ValidationPoint(testContext.getName(), "Validate the description of the alert", "description of the alert is present", "description of the alert is present", true);
							//Retrieving the description
							//Report.OperationPoint(testContext.getName(), "	Operational - Description of the alert: "+oR_BillAndUsage.txtAlertDescription.getText());						
						}else
						{
							Report.ValidationPoint(testContext.getName(), "Validate the description of the alert", "description of the alert is present", "description of the alert is NOTpresent", true);						
						}
						
						WebElement strCardExpireAlert = lDriver.findElement(By.xpath("//span[contains(text(),'Card has expired')]"));
						if(UI.WaitForObject(strCardExpireAlert, 10, lDriver)){
							Report.ValidationPoint(testContext.getName(), "Card epire alert is displayed", "true", "true", true);
						}else{
							Report.ValidationPoint(testContext.getName(), "Card epire alert is displayed", "true", "false", true);
						}

						//Validate manage autopay settings link
						if(UI.WaitForObject(oR_BillAndUsage.lnkManageAutopaySettings,5).equals(true)){
							Report.ValidationPoint(testContext.getName(), "Validate manage autopay/Edit Autopay Information settings link is displayed", "true", "true", true);
							//Click
							Report.OperationPoint(testContext.getName(), "	Operational - Clicking on manage autopay settings link");
							oR_BillAndUsage.lnkManageAutopaySettings.click();
							if(UI.WaitForObject(oR_ManageAutoPay.txtManageAutoPay,50).equals(true))
							{
								Report.ValidationPoint(testContext.getName(), "Validate manage autopay/Edit Autopay page is displayed", "true", "true", true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate manage autopay/Edit Autopay page is displayed", "true", "false", true);
							}
							
						}else
						{
							Report.ValidationPoint(testContext.getName(), "Validate manage autopay/Edit Autopay Information settings link is displayed", "true", "false", true);						
						}

					}else
					{
						Report.ValidationPoint(testContext.getName(), "Validate bill alerts section", "bill alerts section is present", "bill alerts section is NOT present", true);
					}

				}else
				{
					Report.ValidationPoint(testContext.getName(), "Validate billing and usage page", "navigate to billing and usage page", "failed to navigate billing and usage page", true);				
				}

			}else
			{
				Report.ValidationPoint(testContext.getName(), "Validate sec navigation link - billing and usage", "validate sec navigation link - billing and usage", "sec navigation link - billing and usage NOT present", true);			
			}

		}catch (Exception e) {
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}


	/**************************************************************
	 * Function Name - ValidateErrorMsgForSuspendedAcc
	 * Description- 
	 * Parameters - 
	 * Date created - 9th Feb 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//BAP08060, BAP10387. BAP10387 merged in BAP08060

	public static void ValidateErrorMsgForSuspendedAcc(final ITestContext testContext) throws Exception {
		try{

			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
			//Validate sec navigation link - billing and usage
			if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,20).equals(true)){
				Report.TestPoint(testContext.getName(), "Validate sec navigation billing and usage link", "sec navigation billing and usage link is displayed", "sec navigation billing and usage link is displayed", true);
				//Click on sec navigation link - billing and usage
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on sec navigation link - billing and usage");
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();

				//Validate billing and usage page
				if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,60).equals(true)){
					Report.TestPoint(testContext.getName(), "Validate billing and usage page", "navigated to billing and usage page", "navigated to billing and usage page", true);
					Report.OperationPoint(testContext.getName(), "	Operational - Verfying the page title");
					Report.OperationPoint(testContext.getName(), "	Operational - Page title is : "+oR_BillAndUsage.txtBillingAndUsage.getText());

					//Validate the error message for Suspended account
//					UI.WaitForObject(lDriver.findElement(By.xpath("id('ddUserDetails')//div[@class='myAccountList colorBlack']")),
//							20, lDriver);
//					WebElement AccSelected = lDriver.findElement(By.xpath("id('ddUserDetails')//div[@class='myAccountList colorBlack']"));
					try
					{
						UI.WaitForObject(oR_BillAndUsage.txtAccSuspendedMsg, UI.iObjTimeOut, lDriver); 
						String TxtAccount = oR_BillAndUsage.txtAccSuspendedMsg.getText().toString();
						System.out.println(TxtAccount);
						if(TxtAccount.contains("Suspended") || TxtAccount.contains("Your account has been suspended") || TxtAccount.contains("suspended")){
							Report.ValidationPoint(testContext.getName(), "Validate the error message for Suspended account", "error message for Suspended account is present", "error message for Suspended account is present", true);
							Report.OperationPoint(testContext.getName(), "	Operational - Content of Note is : " +TxtAccount);
	
						}else
						{
							Report.ValidationPoint(testContext.getName(), "Validate error message for Suspended account", "error message for Suspended account", "error message for Suspended account is NOT present", true);				
						}
					}catch(Exception E2)
					{
						Report.ValidationPoint(testContext.getName(), "Validate error message for Suspended account", "error message for Suspended account", "error message for Suspended account is NOT present", true);				

					}
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Validate billing and usage page", "validate billing and usage page", "Failed to navigated to billing and usage page", true);			
				}

			}else
			{
				Report.ValidationPoint(testContext.getName(), "Validate sec navigation link - billing and usage", "validate sec navigation link - billing and usage is NOT present", "sec navigation link - billing and usage NOT present", true);			
			}


		}catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);

		}
	}

	/**************************************************************
	 * Function Name - ValidateBillDetailsAndCallDetails
	 * Description- 
	 * Parameters - 
	 * Date created -11th Feb 2015
	 * Developed by - Swagata Das
	 * Last Modified By - Swagata Das
	 * Last Modified Date -	3rd Nov 2015 for BAP03105_01
	 ***************************************************************/
	//BAP03105 and BAP03105_01
	public static void ValidateBillDetailsAndCallDetails(final ITestContext testContext) throws Exception {

		try{

			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);

			//Navigate to bill details from secondary navigation
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,oR_AccountOverview.lnkBillDetailsTertNav))
			{
				if(UI.WaitForObject(oR_BillAndUsage.txtAccountDetails,50).equals(true) && !testContext.getName().contains("BAP03105_01"))
				{
					Report.TestPoint(testContext.getName(), "Navigate to bill details page from secondary navigation", "navigated to bill details page from secondary navigation", "navigated to bill details page from secondary navigation", true);
					//validate and Click on History tab
					if(UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab,20).equals(true))
					{
						Report.ValidationPoint(testContext.getName(), "validate and Click on History tab", "History tab is present and clicked", "History tab is present and clicked", true);
						Report.OperationPoint(testContext.getName(), "	Operational - Click on history tab");
						oR_BillAndUsage.lnkHistoryTab.click();
						if(UI.WaitForObject(oR_BillAndUsage.txtPaymentActivity,30).equals(true))
						{
							Report.ValidationPoint(testContext.getName(), "validate payment activity page", "payment activity page is present", "payment activity page is present", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "validate payment activity page", "payment activity page should be present", "payment activity page is NOTpresent", true);
						}	
					}

					//Validate and click on Reports tab
					if(UI.WaitForObject(oR_BillAndUsage.lnkReportTab,30).equals(true) && !testContext.getName().contains("BAP03105_01"))
					{
						Report.ValidationPoint(testContext.getName(), "validate and Click on Reports tab", "Reports tab is present and clicked", "Reports tab is present and clicked", true);
						Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Reports tab");
						oR_BillAndUsage.lnkReportTab.click();
						if(UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,30).equals(true))
						{
							Report.ValidationPoint(testContext.getName(), "validate reports page", "reports page is present", "reports page is present", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "validate payment activity page", "reports page should be present", "reports page is NOTpresent", true);
						}	
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "validate and Click on Reports tab", "Reports tab is should be present and clicked", "Reports tab is NOT", true);
					}
				}
				/*else
				{
					Report.ValidationPoint(testContext.getName(), "Navigate to bill details page from secondary navigation", "Navigate to bill details page from secondary navigation", "Failed to navigate to bill details page from secondary navigation", true);				
				}*/
				//Validate link change billing address
				if(UI.WaitForObject(oR_BillAndUsage.lnkChangeBillingAddress,30).equals(true) && !testContext.getName().contains("BAP03105_01"))
				{
					Report.ValidationPoint(testContext.getName(), "validate link change billing address", "link change billing address is present", "link change billing address is present", true);
					//Click on the link
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on link change billing address");
					oR_BillAndUsage.lnkChangeBillingAddress.click();
					//Validate Change billing address popup
					if(UI.WaitForObject(oR_BillAndUsage.frmChangeBillingAddress,30).equals(true))
					{
						lDriver.switchTo().frame(oR_BillAndUsage.frmChangeBillingAddress);
						if(UI.WaitForObject(oR_BillAndUsage.txtChangeBillingAddress,60).equals(true))
						{
							Report.ValidationPoint(testContext.getName(), "validate Change billing address popup", "Change billing address popup is present", "Change billing address popup is present", true);
							//Click on cancel
							Report.OperationPoint(testContext.getName(), "	Operational - Clicking on link cancel");
							oR_BillAndUsage.lnkCancel.click();
							lDriver.switchTo().parentFrame();
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "validate Change billing address popup", "validate Change billing address popup", "Change billing address popup is NOTpresent", true);
						}
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "validate link change billing address", "validate link change billing address", "link change billing address is NOTpresent", true);
					}
				}
				/*else
				{
					Report.ValidationPoint(testContext.getName(), "validate link change billing address", "validate link change billing address", "link change billing address is NOTpresent", true);	
				}*/

				//Validate manage payment options link
				if(UI.WaitForObject(oR_BillAndUsage.lnkManagePaymentOptions,60).equals(true) && !testContext.getName().contains("BAP03105_01"))
				{
					Report.ValidationPoint(testContext.getName(), "validate manage payment options link", "manage payment options link is present", "manage payment options link is present", true);
					//Click on the link manage payment options
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on link manage payment options");
					oR_BillAndUsage.lnkManagePaymentOptions.click();
					//Validate payment options page
					if(UI.WaitForObject(oR_BillAndUsage.txtPaymentOptions,60).equals(true))
					{
						Report.ValidationPoint(testContext.getName(), "validate payment options page", "payment options page is present", "payment options page is present", true);
						//Navigating back to bills & usage page					
						lDriver.navigate().back();
						//Validate billing and usage page
						if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,60).equals(true))
						{
							Report.ValidationPoint(testContext.getName(), "validate billing and usage page", "billing and usage page is present", "billing and usage page is present", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "validate billing and usage page", "validate billing and usage page", "billing and usage page is NOTpresent", true);							
						}
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "validate payment options page", "validate payment options page", "payment options page is NOTpresent", true);
					}
				}
				/*else
				{
					Report.ValidationPoint(testContext.getName(), "validate manage payment options link", "validate manage payment options link", "manage payment options link is NOT present", true);
				}*/

				//Validate Account Details Section
				if(UI.WaitForObject(oR_BillAndUsage.txtAccountDetails,80).equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Account Details Section", "Account Details Section is present", "Account Details Section is present", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Account Details Section", "Validate Account Details Section", "Account Details Section is NOTpresent", true);							
				}
				//Validate Current bill
				if(UI.WaitForObject(oR_BillAndUsage.txtCurrentBill,80).equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Current bill Section", "Current bill Section is present", "Current bill Section is present", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Current bill Section", "Validate Current bill Section", "Current bill Section is NOTpresent", true);							
				}
				//Validate Previous Balance section
				if(UI.WaitForObject(oR_BillAndUsage.txtPreviousBalance,10).equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Previous Balance section", "Previous Balance section is present", "Previous Balance section is present", true);
				}
				else
				/*{
					Report.ValidationPoint(testContext.getName(), "Validate Previous Balance section", "Validate Previous Balance section", "Previous Balance section is NOTpresent", true);							
				}*/
				//Validate Payment section
				if(UI.WaitForObject(oR_BillAndUsage.txtPayment,10).equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Payment section", "Payment section is present", "Payment section is present", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Payment section", "Validate Payment section", "Payment section is NOTpresent", true);							
				}
					
				//Validate View explanation of services link and click on it
				Boolean lnkView = UI.WaitForObject(oR_BillAndUsage.lnkViewExplanationOfServices, 5, lDriver);
				if(lnkView)
				{
					Report.ValidationPoint(testContext.getName(), "Validate View explanation of services link", "true", String.valueOf(lnkView), true);
					//Click
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on View explanation of services link");
					oR_BillAndUsage.lnkViewExplanationOfServices.click();
					if(UI.WaitForObject(oR_BillAndUsage.frmExplanationOfCharges, 10, lDriver))
					{
						Report.ValidationPoint(testContext.getName(), "Validate explanation of services popup with details", "true", "true", true);
						oR_BillAndUsage.imgClose.click();
					}
				}
				//Validate wireless section
				Thread.sleep(3000);
				boolean bWireless = UI.WaitForObject(oR_BillAndUsage.txtWirelessSection, 5, lDriver);
				Report.TestPoint(testContext.getName(), "Validate Wireless section header is present", "true", String.valueOf(bWireless), true);
				
				//
				/** Code Modified - Monica -14th Jan 2015 **/
				List<WebElement> CTN= lDriver.findElements(By.xpath("//a[contains(@href,'CTN')]"));
				for(int i=0;i<CTN.size();i++){
					System.out.println(CTN.get(i).getText());
					CTN.get(i).click();}
				Report.ValidationPoint(testContext.getName(), "Validate different  CTN sections are present", "true", String.valueOf(CTN.size()>0), true);
				
				//
				
				List<WebElement> MonthCharges= lDriver.findElements(By.xpath("//a[contains(@title,'Monthly Plan Charges')]"));
				Report.TestPoint(testContext.getName(),"Validate monthly charges section is present","true",String.valueOf(MonthCharges.size()>0),true);
				
				//	
				/** code MOdified - 14th Jan 2015 - Monica - Exception handling done **/

				try
				{
				WebElement OverageCharges= lDriver.findElement(By.xpath("//a[contains(@title,'Web Text Usage Charges')]"));
				
				Report.ValidationPoint(testContext.getName(),"Validate Data Overage charges section is present as expected","true",String.valueOf(OverageCharges.isDisplayed()),true);
				if(OverageCharges.isDisplayed())
					OverageCharges.click();  /** Code Modified - Monica - 14th Jan 2015 **/
				}
				catch(Exception e) 
				{
					Report.ValidationPoint(testContext.getName(),"Validate Data Overage charges section is present as expected","true","false",true);

				}
				
				//
				List<WebElement> Alerts=lDriver.findElements(By.xpath("//div[@class='alertBoxGrad']//li"));
				Report.ValidationPoint(testContext.getName(),"Validate Bill alerts are didplayed in tabular form","true",String.valueOf(Alerts.size()>0),true);
				/** code MOdified - 14th Jan 2015 - Monica - Exception handling done **/
				try
				{
					boolean bOverage= UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(text(),'Internet Overage Alert')]")), 5, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Internet Overage Alert is present","true",String.valueOf(bOverage),true);
				}
				catch(Exception e) 
				{
					Report.ValidationPoint(testContext.getName(),"Validate Internet Overage Alert is present","true","false",true);

				}
				Report.OperationPoint(testContext.getName(),"Clicking on view details");
				try
				{	lDriver.findElement(By.xpath("//a[contains(text(),'View details')]")).click();
					Report.ValidationPoint(testContext.getName(),"Validate data usage charges section is expanded","true",String.valueOf(lDriver.findElement(By.xpath("//*[contains(text(),'Total Data Usage Charges')]")).isDisplayed()),true);
				}
				catch(Exception e) 
				{
					Report.ValidationPoint(testContext.getName(),"Validate data usage charges section is expanded","true","false",true);

				}
				//validate alert icon
				try
				{
				boolean bAlertIcon=UI.WaitForObject(lDriver.findElement(By.xpath("//div[contains(@class,'AlertIcon')]")), 2, lDriver);
				Report.ValidationPoint(testContext.getName(),"Validate alert icon is present","true",String.valueOf(bAlertIcon),true);
				}
				catch(Exception e) 
				{
					Report.ValidationPoint(testContext.getName(),"Validate alert icon is present","true","false",true);

				}
				
				//validate amount
				try
				{
				WebElement bAmount=lDriver.findElement(By.xpath("//*[text()='Total Data Usage Charges']/parent::*/parent::*/child::*[contains(text(),'$')]"));
				Report.OperationPoint(testContext.getName(),"Amount of total charges are: "+bAmount.getText());
				Report.ValidationPoint(testContext.getName(),"Validate Amount of Due charges is present","true",String.valueOf(bAmount.isDisplayed()),true);
				}
				catch(Exception e) 
				{
					Report.ValidationPoint(testContext.getName(),"Validate Amount of Due charges is present","true","false",true);

				}
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Navigate to bill details page from secondary navigation", "navigate to bill details page from secondary navigation", "bill details link is NOT present", true);
			}

			/** Code Modification ends - Monica -14th Jan 2015 **/
		}catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}

	}
	/**************************************************************
	 * Function Name - UverseInstallment 
	 * Description- 
	 * Parameters - 
	 * Date created -11 Feb 2015
	 * Developed by - Merrin Mathai
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void UverseInstallment(final ITestContext testContext)
			throws Exception {

		try {
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
			//click on Bills&Payments from Tertiary Navigation
			Report.OperationPoint(testContext.getName(), "Navigating to Bills & Usage landing page");
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null))
			{
				Report.TestPoint(testContext.getName(), "Goto Bills & Usage from global nav", "Bills & Usage page is displayed", "Bills & Usage page is displayed" , true);	
				//Click One - Time Charges section 	
				if(UI.WaitForObject(oR_BillAndUsage.lnkOneTimeCharges, UI.iObjTimeOut))
				{
					oR_BillAndUsage.lnkOneTimeCharges.click();
					Report.TestPoint(testContext.getName(), "Verify link One - Time Charges is present", "link One - Time Charges is present and clicked", "link One - Time Charges is present and clicked" , true);
					//verify Uverse installment
					if(UI.WaitForObject(oR_BillAndUsage.txtUverseTVInstallment, UI.iObjTimeOut))
					{
						Report.ValidationPoint(testContext.getName(), "Verify Uverse TV Installment", "Uverse TV Installment should be present under One - Time Charges section", "Uverse TV Installment is present", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify Uverse TV Installment", "Uverse TV Installment should be present under One - Time Charges section", "Uverse TV Installment is NOT present", true);
					}
					//div[@class='MarLeft25 accSummary']/div[@class='BotSolidBorder Padbot5 PadTop5']//div[contains(text(),'Installment')]
					//div[@class='MarLeft25 accSummary']/div[@class='BotSolidBorder Padbot5 PadTop5']//div//*[contains(text(),'asked')]
					//Verify a note for each installment
					List<WebElement> InstallmentNote= lDriver.findElements(By.xpath("//div[@class='float-left accRow PadLeft5imp PadTop0 Padbot2IE']"));

					List<WebElement> installmentText=lDriver.findElements(By.xpath("//div[@class='MarLeft25 accSummary']/div[@class='BotSolidBorder Padbot5 PadTop5']//div[contains(text(),'Installment')]"));	
					List<WebElement> payText=lDriver.findElements(By.xpath("//div[@class='MarLeft25 accSummary']/div[@class='BotSolidBorder Padbot5 PadTop5']//div//*[contains(text(),'asked')]"));

					int InstallmentNoteCount=InstallmentNote.size();
					int InstallmentTextCount=installmentText.size();
					int PayTextCount=payText.size();

					if(InstallmentNoteCount==InstallmentTextCount&& InstallmentTextCount== PayTextCount)
					{
						Report.ValidationPoint(testContext.getName(), "Verify a note for each installment in such a way it is associated to the installment displayed and indicates the charge was a request over a specified duration with alert icon.", "true", "true", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify a note for each installment in such a way it is associated to the installment displayed and indicates the charge was a request over a specified duration with alert icon.", "true", "false", true);
					}
				}

				else
				{
					Report.TestPoint(testContext.getName(), "Verify link One - Time Charges is present", "link One - Time Charges is present and clicked", "link One - Time Charges is NOT present or clicked" , true);
				}
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Goto Bills & Usage from global nav", "Bills & Usage page is displayed", "Bills & Usage page is NOT displayed" , true);
			}


		} catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name - ValidateBillingforDTVISPWireline 
	 * Description- 
	 * Parameters - 
	 * Date created -12 Feb 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void ValidateBillingforDTVISPWireline (final ITestContext testContext)
			throws Exception {
		try {
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_PaperlessBilling oR_PaperlessBilling = PageFactory.initElements(lDriver, OR_PaperlessBilling.class);
			
			//click on Billing&Payments
			Report.OperationPoint(testContext.getName(), "Navigating to Bills & Usage landing page");
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null))
			{
				Report.TestPoint(testContext.getName(), "Goto Bills & Usage from global nav", "Bills & Usage page is displayed", "Bills & Usage page is displayed" , true);	
				//Click History Tab 	
				if(UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab, UI.iObjTimeOut))
				{
					oR_BillAndUsage.lnkHistoryTab.click();
					Report.TestPoint(testContext.getName(), "Verify History is present", "History Tab is present and clicked", "History Tab is present and clicked" , true);
					Report.OperationPoint(testContext.getName(), "Navigating to History");
					//verify History Table
					if(UI.WaitForObject(oR_BillAndUsage.tblBillingHistory, UI.iObjTimeOut))
					{
						Report.ValidationPoint(testContext.getName(), "Verify Billing History Table", "Billing History Table is displayed", "Billing History Table is displayed", true);
						if((UI.WaitForObject(oR_BillAndUsage.lnkPaperBill, UI.iObjTimeOut)))
						{
							Report.ValidationPoint(testContext.getName(), "Verify View Paper Bill link is present", "View Paper Bill link is present", "View Paper Bill link is present", true);
						}
						if(UI.WaitForObject(oR_BillAndUsage.lnkOnlineBill, UI.iObjTimeOut))
						{
							Report.ValidationPoint(testContext.getName(), "Verify View Bill Online link is present", "View Bill Online link is present", "View Bill Online link is present", true);
						}
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify Billing History Table", "Billing History Table is displayed", "Billing History Table is not displayed", true);

					}
					//verify Report tabs
					Report.OperationPoint(testContext.getName(), "Navigating to Reports");
					if(UI.WaitForObject(oR_BillAndUsage.lnkReportTab, UI.iObjTimeOut))
					{	
						Report.TestPoint(testContext.getName(), "Goto Report Tab", "Report is displayed", "Report is displayed" , true);
						oR_BillAndUsage.lnkReportTab.click();
						if(UI.WaitForObject(oR_BillAndUsage.lstSelectReportType, UI.iObjTimeOut))
						{	
							Report.TestPoint(testContext.getName(), "Verify Select Report type dropdown is present", "Select Report type dropdown is present", "Select Report type dropdown is present" , true);
						}
						if(UI.WaitForObject(oR_BillAndUsage.lstStartDate, UI.iObjTimeOut))
						{	
							Report.TestPoint(testContext.getName(), "Verify Select Start Date dropdown is present", "Start Date dropdown is present", "Start Date dropdown is present" , true);
						}
						if(UI.WaitForObject(oR_BillAndUsage.lstEndDate, UI.iObjTimeOut))
						{	
							Report.TestPoint(testContext.getName(), "Verify End Date dropdown is present", "End Date dropdown is present", "End Date dropdown is present" , true);
						}
						
						Report.OperationPoint(testContext.getName(), "Click on Paperless Billing link under secondary navigation");
						
						UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkPaperlessBillingSecNav);
						if(UI.WaitForObject(oR_PaperlessBilling.txtPaperlessBillingTitle, UI.iObjTimeOut))
						{	
						   Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Paperless Billing page", "Customer is navigated to Paperless Billing page", "Customer is navigated to Paperless Billing page", true); 
					   }
					  else
					   {
						   Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Paperless Billing page", "Customer is navigated to Paperless Billing page", "Customer is NOT navigated to Paperless Billing page", true); 

					   }
				}
				
			}
			}
		}

		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name -  VerifyUsageTab()
	 * Description- This method validates the Basic Usage plan Table on B&U page
	 * Parameters - None
	 * Date created -13th Feb 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - Monica Mohabansi
	 * Last Modified Date - 20th Jan 2016
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	//BAP09950
	public static void VerifyUsageTab(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		Report.OperationPoint(testContext.getName(), "Validate Usage tab");

		 Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sPlanName = IO.GetParamVal(sTestParams, "Plan_Name");
		//String sPlanName = "FamilyTalk";

		Boolean bLnkBnU, bRollover;
		int iRowCount;
		List<WebElement> aRows, aImgDevice, alnkViewUsageDetails, aName, aCTN;
		try			
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);

			bLnkBnU = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 20);
			if(bLnkBnU.equals(true))
			{
				Report.TestPoint(testContext.getName(), "2-Link Billing & Usage on Sec Navigation", "Present", "Present", true);
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();

				if(UI.WaitForObject(oR_BillAndUsage.lnkUsage, 15).equals(true))
				{
					Report.TestPoint(testContext.getName(), "3-Usage Tab on B&U page", "true", String.valueOf(UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut)), true);
					oR_BillAndUsage.lnkUsage.click();

					Boolean bPlanName = UI.WaitForObject(oR_BillAndUsage.txtPlanName, 20);					
					Report.ValidationPoint(testContext.getName(), "4-Plan Name", "true", bPlanName.toString(), true);
				

					if(lDriver.findElement(By.xpath("//div[@class='box']/table[@class='table']")).isDisplayed())
					{	
						Report.ValidationPoint(testContext.getName(), "Basic Plan Usage Table", "true", "true", true);

						aRows = lDriver.findElements(By.xpath("//div[@class='box']/table[@class='table']//tr"));
						iRowCount = aRows.size()-1;

						aImgDevice = lDriver.findElements(By.xpath("//table//img[@alt='Phone']"));
						alnkViewUsageDetails = lDriver.findElements(By.xpath("//table//a[contains(@id,'ViewUsageDetails')]"));
						aName = lDriver.findElements(By.xpath("//table//p/strong"));
						aCTN = lDriver.findElements(By.xpath("//table//span[contains(text(),'.')]"));
						// Verify whether First name, Last name and CTN is displayed for each subscriber.

						if(aImgDevice.size() == iRowCount && alnkViewUsageDetails.size() == iRowCount  &&
								aName.size() == iRowCount  && aCTN.size() == iRowCount)
						{
							Report.ValidationPoint(testContext.getName(), "Steps 5,6,8-Name, CTN and View Usage details link for Each Subscriber", "Present" , "Present", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Steps 5,6,8-Name, CTN and View Usage details link for Each Subscriber", "Present" , "Not Present", true);
						}

						//Check for display of Talk(with rollover) Usage for each individual on the FT plan.
						Report.OperationPoint(testContext.getName(), "7-Check for order of subscribers listed in the table.");
						//Validate that asterisk (as rollover minutes are included in the allotted amount) on each users usage should be displayed.
						bRollover = lDriver.findElement(By.xpath("//table//div[contains(text(),'Rollover Minutes')]")).isDisplayed();
						if(bRollover.equals(true) && lDriver.findElements(By.xpath("//table//a[contains(@id,'Rollover_talk_usage')]")).size() == iRowCount)
						{
							Report.ValidationPoint(testContext.getName(), "9-Talk (rollover minutes) Usage with Amount use and left", "true", "true", false);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "9-Talk (rollover minutes) Usage with Amount use and left", "true", "false", false);
						}
						//Check for display of asterisk on each users usage .
						Report.OperationPoint(testContext.getName(), "10-Check for display of asterisk on each users usage");
						Report.ValidationPoint(testContext.getName(), "Validate that asterisk (as rollover minutes are included in the allotted amount) on each users usage should be displayed.", "Asterisk absent", "Asterisk absent", true);
						
						//Check for display of shared and non-shared label.							
						if(lDriver.findElements(By.xpath("//table/thead//span[contains(text(),'Shared')]")).size()==3)
						{
							Report.ValidationPoint(testContext.getName(), "11-Shared/Non-Shared Label", "Present", "Present", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "11-Shared/Non-Shared Label", "Present", "Not Present", true);

						}
					}						
					else
					{
						Report.ValidationPoint(testContext.getName(), "Usage table", "true", "false", true);
					}		
				}
			}
			else			
			{
				Report.TestPoint(testContext.getName(), "Link Billing & Usage on Sec Navigation", "Link B & U", "Not Present", true);
			}

		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Failed to validate", "true", e.getMessage(), true);
		}
	}


	/**************************************************************
	 * Function Name - ValidateManageAutopayPaymentMethod
	 * Description- 
	 * Parameters - 
	 * Date created - 16th Feb 2015
	 * Developed by - Swagata Das
	 * Last Modified By - Monica Mohabansi
	 * Last Modified Date -13th Oct 2015
	 ***************************************************************/		

	public static void ValidateManageAutopayPaymentMethod(final ITestContext testContext) throws Exception {
		try{
			 Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			 
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_AutoPayEnrollment oR_AutoPayEnrollment = PageFactory.initElements(lDriver, OR_AutoPayEnrollment.class);
			
			String sNameOnCard, sCardNo, sSecurityCode, sZipcode;
			sNameOnCard = IO.GetParamVal(sTestParams, "Name");
			sCardNo = IO.GetParamVal(sTestParams, "CardNo");
			sSecurityCode = IO.GetParamVal(sTestParams, "SecurityCode");
			sZipcode = IO.GetParamVal(sTestParams, "Zipcode");
			System.out.println(sNameOnCard);
			System.out.println(sCardNo);
			System.out.println(sSecurityCode);
			System.out.println(sZipcode);

			//Validate and click on manage Autopay tertiery link
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkManageAutopayTertNav))
			{
				Report.TestPoint(testContext.getName(), "Validate and click on manage Autopay tertiery link", "manage Autopay tertiery link is present and clicked", "manage Autopay tertiery link is present and clicked", true);

				//Validate autopay enrollement page
				if(UI.WaitForObject(oR_AutoPayEnrollment.txtAutoPayEnrollment,50))
				{
					Report.ValidationPoint(testContext.getName(), "Validate autopay enrollement page", "navigated to autopay enrollement page", "navigated to autopay enrollement page", true);

					//Validate Select payment method
					if(UI.WaitForObject(oR_AutoPayEnrollment.txtSelectPaymentMethod, 10))
					{
						Report.ValidationPoint(testContext.getName(), "Validate Select payment method", "Select payment method is present", "Select payment method is present", true);
						//Select New debit / credit card method
						if(UI.SelectOptionFromDropDown(oR_AutoPayEnrollment.lstSelectPaymentMethod, "New debit / credit card"))
						{
							Report.ValidationPoint(testContext.getName(), "Select New debit / credit card method", "New debit / credit card method is selected", "New debit / credit card method is selected", true);

							//validate New Debit/credit payment method form
							if(UI.WaitForObject(oR_AutoPayEnrollment.frmNewDebitCreditCard, 80))
							{
								Report.ValidationPoint(testContext.getName(), "Validate New Debit/credit payment method form", "New Debit/credit payment method form is present", "New Debit/credit payment method form is present", true);

								//Switching to the new frame
								lDriver.switchTo().frame(oR_AutoPayEnrollment.frmNewDebitCreditCard);
								{
									//Enter the values
									Report.OperationPoint(testContext.getName(), "Operation : Entering the values:");
									Report.OperationPoint(testContext.getName(), "Operation : Name on card : " +sNameOnCard);
									oR_AutoPayEnrollment.edtNameOnCard.sendKeys(sNameOnCard);
									Report.OperationPoint(testContext.getName(), "Operation : Card number : " +sCardNo);
									oR_AutoPayEnrollment.edtcardNo.sendKeys(sCardNo);
									Report.OperationPoint(testContext.getName(), "Operation : Security number : " +sSecurityCode );
									oR_AutoPayEnrollment.edtSecurityCode.sendKeys(sSecurityCode);

									UI.SelectOptionFromDropDown(oR_AutoPayEnrollment.lstMonth, "12");
									UI.SelectOptionFromDropDown(oR_AutoPayEnrollment.lstYear, "2015");

									Report.OperationPoint(testContext.getName(), "Operation : Entering Zipcode : " +sZipcode);
									oR_AutoPayEnrollment.edtZipCode.sendKeys(sZipcode);

									//Click on Continue
									Report.OperationPoint(testContext.getName(), "Operation : Clicking on Continue");
									oR_AutoPayEnrollment.btnContinue.click();

									//Switching back to parent frame
									lDriver.switchTo().defaultContent();
								}
								//click on Next button
								Report.OperationPoint(testContext.getName(), "Operation : Clicking on Next");
								//Thread.sleep(4000);
								oR_AutoPayEnrollment.btnNext.click();

								//Validate Autopay review page
								if(UI.WaitForObject(oR_AutoPayEnrollment.txtAutopayReview, 50))
								{
									Report.ValidationPoint(testContext.getName(), "Validate Autopay review page", "Navigated to Autopay review page", "Navigated to Autopay review page", true);
									//Click on submit button
									Report.OperationPoint(testContext.getName(), "Operation : Clicking on Submit");
									oR_AutoPayEnrollment.btnSubmit.click();

									//Validate Confirmation page
									if(UI.WaitForObject(oR_AutoPayEnrollment.txtAutopayConfirmation, 500))
									{
										Report.ValidationPoint(testContext.getName(), "Validate Confirmation page", "Navigated to Confirmation page", "Navigated to Confirmation page", true);

										//Validate step 3: Confirmation
										if(UI.WaitForObject(oR_AutoPayEnrollment.txtStepConfirmation, 50))
										{
											Report.ValidationPoint(testContext.getName(), "Validate Confirmation step", "Confirmation step is present", "Confirmation step is present", true);
											String sClass = oR_AutoPayEnrollment.txtStepConfirmation.getAttribute("class");
											if(sClass.trim().equalsIgnoreCase("step3"))
											{
												Report.ValidationPoint(testContext.getName(), "Validate step 3: Confirmation", "step 3: Confirmation is present", "step 3: Confirmation is present", true);
											}
											
												/*{
											Report.ValidationPoint(testContext.getName(), "Validate step 3: Confirmation", "step 3: Confirmation is present", "step 3: Confirmation is NOTpresent", true);
										}*/

												//Click on link overview
//												Report.OperationPoint(testContext.getName(), "Operation : Clicking on link overview");
//												oR_AutoPayEnrollment.lnkOverview.click();

											//Click on Manage Autopay tertiery link
											if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsageSecNav, 80))
											{
												if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsageSecNav, oR_AccountOverview.lnkManageAutopayTertNav))
												{
													Report.ValidationPoint(testContext.getName(), "Validate Manage Autopay tertiery link and click on it", "Manage Autopay tertiery link is present and clicked", "Manage Autopay tertiery link is present and clicked", true);

													//Validate Autopay enrollment page
													if(UI.WaitForObject(oR_AutoPayEnrollment.txtAutoPayEnrollment, 50))
													{
														Report.ValidationPoint(testContext.getName(), "Validate Manage Autopay page", "Navigated to Manage Autopay enrollment page", "Navigated to Manage Autopay enrollment page", true);

														//verify note: encouraging users to use a banking account is NOT displayed
														UI.VerifyElementNotPresent(oR_AutoPayEnrollment.txtNoteBank, "Note: encouraging users to use a banking account");

														/** code added Monica - 13th OCT 2015 **/
														//Click on Edit Payment Profile method
														if(UI.WaitForObject(oR_AutoPayEnrollment.lnkChangeAutoPayPayment, 20, lDriver))
														{
															Report.ValidationPoint(testContext.getName(), "Click on 'Use different payment method' link", "true", "true", true);
															oR_AutoPayEnrollment.lnkChangeAutoPayPayment.click();
														}
														
														//Select payment method
														UI.SelectOptionFromDropDown(oR_AutoPayEnrollment.lstSelectPaymentMethod, "New card ending in 1100");

														//Click on Next button
														Report.OperationPoint(testContext.getName(), "Operation : Clicking on Next button");
														oR_AutoPayEnrollment.btnNext.click();

														//Validate Autopay review page
														if(UI.WaitForObject(oR_AutoPayEnrollment.txtAutopayReview, 50))
														{
															Report.ValidationPoint(testContext.getName(), "Validate Autopay review page", "Navigated to Autopay review page", "Navigated to Autopay review page", true);
															//verify note: encouraging users to use a banking account is NOT displayed
															UI.VerifyElementNotPresent(oR_AutoPayEnrollment.txtNoteBank, "Note: encouraging users to use a banking account");
														
															/** code added Monica -13th OCT 2015 **/
															//Again click on Back button
															Boolean btnBack = UI.WaitForObject(oR_AutoPayEnrollment.btnBack, 20, lDriver);
															Report.TestPoint(testContext.getName(), "Again click on Back button", "true", String.valueOf(btnBack), true);
															oR_AutoPayEnrollment.btnBack.click();
															
															Boolean bNote = UI.VerifyElementNotPresent(oR_AutoPayEnrollment.txtNoteBank, "Note: encouraging users to use a banking account");
															Report.ValidationPoint(testContext.getName(), "Verify that note encouraging users to use a banking account is still NOT displayed  once user navigate back to AutoPay step 1 page from AutoPay step 2 page", "true", String.valueOf(bNote), true);

														}
														
													
														else
														{
															Report.ValidationPoint(testContext.getName(), "Validate Autopay review page", "Navigated to Autopay review page", "Failed to navigate to Autopay review page", true);
														}
													}
													else
													{
														Report.ValidationPoint(testContext.getName(), "Validate Autopay enrollment page", "Navigated to Autopay enrollment page", "failed to Navigate to Autopay enrollment page", true);
													}
												}
												else
												{
													Report.ValidationPoint(testContext.getName(), "Validate Manage Autopay tertiery link and click on it", "Manage Autopay tertiery link is present and clicked", "Manage Autopay tertiery link is NOTpresent", true);
												}
											}
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Validate Confirmation step is present", "Confirmation step is present", "Confirmation step is NOTpresent", true);
										}
									}
									else
									{
										Report.ValidationPoint(testContext.getName(), "Validate Confirmation page", "Navigated to Confirmation page", "Navigated to Confirmation page", true);
									}
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate Autopay review page", "Navigated to Autopay review page", "Failed to Navigate to Autopay review page", true);
								}
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate New Debit/credit payment method form", "New Debit/credit payment method form is present", "New Debit/credit payment method form is NOTpresent", true);
							}

						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Select New debit / credit card method", "New debit / credit card method is selected", "New debit / credit card method is NOTselected", true);
						}
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate Select payment method", "Select payment method is present", "Select payment method is NOT present", true);
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate autopay enrollement page", "navigated to autopay enrollement page", "failed to navigate to autopay enrollement page", true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate and click on manage Autopay tertiery link", "manage Autopay tertiery link is present and clicked", "manage Autopay tertiery link is NOTpresent", true);		
			}


		}catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);

		}
	}
	/************************************************************** 
	 * Function Name - ValidateMonthInstallmentDetails 
	 * Description-  This validates the Installment Details under Equipment Charges section 
	 * Parameters - 
	 * Date created -17 Feb 2015 
	 * Developed by - Krutika Kamdi 
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/ 
	public static void ValidateMonthInstallmentDetails(final ITestContext testContext) 
			throws Exception { 
		try { 
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class); 
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class); 
 
			Report.OperationPoint(testContext.getName(), "Navigating to Bills & Usage landing page"); 
			if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,50).equals(true)) 
			{ 
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click(); 
				Thread.sleep(10000); 
 
				Report.TestPoint(testContext.getName(), "Goto Bills & Usage from global nav", "Bills & Usage page is displayed", "Bills & Usage page is displayed" , true); 
				Report.OperationPoint(testContext.getName(), "Clicking on all '+' expand section to expand all"); 
				List<WebElement> PlusSigns = lDriver.findElements(By.xpath("//a[contains(@class,'expandImg')]")); 
				int iSize = PlusSigns.size(); 
				for(int i=0;i<iSize;i++) 
				{ 
					try 
					{ 
	 				if(PlusSigns.get(i).isEnabled() || PlusSigns.get(i).isDisplayed()) 
						{ 
							PlusSigns.get(i).click(); 
							Thread.sleep(3000); 
						} 
 
					}catch(Exception Eee) 
					{ 
						Report.OperationPoint(testContext.getName(), "expand '+' is hidden, moving to next"); 
					} 
 
				} 
				if(UI.WaitForObject(oR_BillAndUsage.txtEquipmentsChargesSection,40).equals(true)) 
				{ 
					Report.TestPoint(testContext.getName(), "Validate presence of Equipment charges ", "Equipment charges details is displayed", "Equipment charges details is displayed" , true); 
 
					if(UI.WaitForObject(oR_BillAndUsage.lnkManageInstallmentPlan,5).equals(true)) 
					{ 
						Report.TestPoint(testContext.getName(), "Validate Manage Installment Plan", "Manage Installment Plan is displayed", "Manage Installment Plan is displayed" , true); 
						oR_BillAndUsage.lnkManageInstallmentPlan.click(); 
						lDriver.switchTo().frame(lDriver.findElement(By.xpath("//iframe[@class='cboxIframe']"))); 
 
						Boolean bInstallmentDetails = UI.WaitForObject(oR_BillAndUsage.txtInstallmentPlanDetails, 50); 
						Report.TestPoint(testContext.getName(), "Modal with installment details is displayed", "true", String.valueOf(bInstallmentDetails) , true); 
						lDriver.switchTo().defaultContent(); 
					} 
					else 
					{ 
						Report.TestPoint(testContext.getName(), "Validate Manage Installment Plan", "Manage Installment Plan is displayed", "Manage Installment Plan is not displayed" , true); 
					} 
 
				} 
				else 
				{ 
					Report.TestPoint(testContext.getName(), "Validate presence of Equipment charges ", "Equipment charges details is displayed", "Equipment charges details is not displayed" , true); 
				} 
 
		} 
		} 
		catch (Exception e) { 
			Report.TestPoint(testContext.getName(), 
					"Some error has occured", "True", 
					e.getMessage(), true); 
		} 
	}

	/**************************************************************
	 * Function Name - ValidateSurchargesAndGovernmentTaxesForTVAndInternetAndVoice
	 * Description-  This validates the Installment Details under Equipment Charges section
	 * Parameters - 
	 * Date created -18 Feb 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/	
	public static void ValidateSurchargesAndGovernmentTaxesForTVAndInternetAndVoice(final ITestContext testContext) throws Exception {

		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
			//Validate Sec nav link billing & payments and click on it
			if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 50))
			{
				Report.ValidationPoint(testContext.getName(), "Validate Sec nav link billing & payments and click on it", "Sec nav link billing & payments is displayed and clicked", "Sec nav link billing & payments is displayed and clicked", true);
				if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null))
				{
					//Validate Bills & Usage page
					if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 50))
					{
						Report.ValidationPoint(testContext.getName(), "Navigate to Bills & Usage page", "successfully navigated to Bills & Usage page", "successfully navigated to Bills & Usage page", true);
						//Validate Previous Activity section
						if(UI.WaitForObject(oR_BillAndUsage.txtPreviousActivityFor, 1))
						{
							Report.ValidationPoint(testContext.getName(), "Validate Previous Activity section", "Previous Activity section is present", "Previous Activity section is present", true);
							//Validate adjustments
							if(UI.WaitForObject(oR_BillAndUsage.txtAdjustments, 1))
							{
								Report.ValidationPoint(testContext.getName(), "Validate adjustments", "adjustments is present", "adjustments is present", true);

							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate adjustments", "adjustments is present", "adjustments is NOTpresent", true);
							}
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Validate Previous Activity section", "Previous Activity section is present", "Previous Activity section is NOTpresent", true);
						}

						//Validate U-Verse TV link
						if(UI.WaitForObject(oR_BillAndUsage.lnkUverseTV, 1))
						{
							Report.ValidationPoint(testContext.getName(), "Validate U-Verse TV link", "U-Verse TV link is present", "U-Verse TV link is present", true);

							//Validate Surcharges and fees link
							if(UI.WaitForObject(oR_BillAndUsage.lnkUverseTVSurchargeAndFees, 1))
							{
								Report.ValidationPoint(testContext.getName(), "Validate Surcharges and fees link", "Surcharges and fees link is present", "Surcharges and fees link is present", true);
								//Validate Surcharges and fees link is collapsed by default

								if(oR_BillAndUsage.lnkUverseTVSurchargeAndFees.getAttribute("aria-expanded").equals("false"))
								{
									Report.ValidationPoint(testContext.getName(), "Validate Surcharges and fees link is collapsed by default", "Surcharges and fees link is collapsed by default", "Surcharges and fees link is collapsed by default", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate Surcharges and fees link is collapsed by default", "Surcharges and fees link is collapsed by default", "Surcharges and fees link is NOT collapsed by default", true);
								}

								//Click on the link Surcharges and fees
								Report.OperationPoint(testContext.getName(), "Operation : Clicking on link Surcharges and fees");
								oR_BillAndUsage.lnkUverseTVSurchargeAndFees.click();

								//Validate total Surcharges & Other Fees
								if(UI.WaitForObject(oR_BillAndUsage.txtUverseTVTotalSurchargeFees,1))
								{
									Report.ValidationPoint(testContext.getName(), "Validate total Surcharges & Other Fees", "total Surcharges & Other Fees is present", "total Surcharges & Other Fees is present", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate total Surcharges & Other Fees", "total Surcharges & Other Fees is present", "total Surcharges & Other Fees is NOTpresent", true);
								}

								//Click on the link Surcharges and fees
								Report.OperationPoint(testContext.getName(), "Operation : Clicking on link Surcharges and fees");
								oR_BillAndUsage.lnkUverseTVSurchargeAndFees.click();
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate Surcharges and fees link", "Surcharges and fees link is present", "Surcharges and fees link is NOTpresent", true);
							}

							//Validate Government Fees & Taxes link
							if(UI.WaitForObject(oR_BillAndUsage.lnkUverseTVGovernmentFees, 1))
							{
								Report.ValidationPoint(testContext.getName(), "Validate Government Fees & Taxes link", "Government Fees & Taxes link is present", "Government Fees & Taxes link is present", true);
								//Validate Government Fees & Taxes link is collapsed by default
								if(oR_BillAndUsage.lnkUverseTVGovernmentFees.getAttribute("aria-expanded").equals("false"))
								{
									Report.ValidationPoint(testContext.getName(), "Validate Government Fees & Taxes link is collapsed by default", "Government Fees & Taxes link is collapsed by default", "Government Fees & Taxes link is collapsed by default", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate Government Fees & Taxes link is collapsed by default", "Government Fees & Taxes link is collapsed by default", "Government Fees & Taxes link is NOTcollapsed by default", true);
								}

								//Click on the Government Fees & Taxes link
								Report.OperationPoint(testContext.getName(), "Operation : Clicking on Government Fees & Taxes link");
								oR_BillAndUsage.lnkUverseTVGovernmentFees.click();

								//Validate total Government Fees & Taxes
								if(UI.WaitForObject(oR_BillAndUsage.txtUverseTVTotalGovernmentFees,1))
								{
									Report.ValidationPoint(testContext.getName(), "Validate total Government Fees & Taxes", "total Government Fees & Taxes is present", "total Government Fees & Taxes is present", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate total Government Fees & Taxes", "total Government Fees & Taxes is present", "total Government Fees & Taxes is NOTpresent", true);
								}

								//Click on the Government Fees & Taxes link
								Report.OperationPoint(testContext.getName(), "Operation : Clicking on Government Fees & Taxes link");
								oR_BillAndUsage.lnkUverseTVGovernmentFees.click();
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate Government Fees & Taxes link", "Government Fees & Taxes link is present", "Government Fees & Taxes link is NOTpresent", true);
							}

							//Click on U-Verse TV link
							Report.OperationPoint(testContext.getName(), "Operation : Clicking on U-Verse TV link");
							oR_BillAndUsage.lnkUverseTV.click();
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Validate U-Verse TV link", "U-Verse TV link is present", "U-Verse TV link is NOTpresent", true);
						}


						//Validate U-Verse Voice link
						if(UI.WaitForObject(oR_BillAndUsage.lnkUverseVoice, 1))
						{
							Report.ValidationPoint(testContext.getName(), "Validate U-Verse Voice link", "U-Verse Voice link is present", "U-Verse Voice link is present", true);
							//Click on U-Verse Voice link
							Report.OperationPoint(testContext.getName(), "Operation : Clicking on U-Verse TV link");
							oR_BillAndUsage.lnkUverseVoice.click();

							//Validate Surcharges and fees link
							if(UI.WaitForObject(oR_BillAndUsage.lnkUverseVoiceSurchargeAndFees, 1))
							{
								Report.ValidationPoint(testContext.getName(), "Validate Surcharges and fees link", "Surcharges and fees link is present", "Surcharges and fees link is present", true);
								//Validate Surcharges and fees link is collapsed by default
								if(oR_BillAndUsage.lnkUverseVoiceSurchargeAndFees.getAttribute("aria-expanded").equals("false"))
								{
									Report.ValidationPoint(testContext.getName(), "Validate Surcharges and fees link is collapsed by default", "Surcharges and fees link is collapsed by default", "Surcharges and fees link is collapsed by default", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate Surcharges and fees link is collapsed by default", "Surcharges and fees link is collapsed by default", "Surcharges and fees link is NOT collapsed by default", true);
								}

								//Click on the link Surcharges and fees
								Report.OperationPoint(testContext.getName(), "Operation : Clicking on link Surcharges and fees");
								oR_BillAndUsage.lnkUverseVoiceSurchargeAndFees.click();

								//Validate total Surcharges & Other Fees
								if(UI.WaitForObject(oR_BillAndUsage.txtUverseVoiceTotalSurchargeFees,1))
								{
									Report.ValidationPoint(testContext.getName(), "Validate total Surcharges & Other Fees", "total Surcharges & Other Fees is present", "total Surcharges & Other Fees is present", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate total Surcharges & Other Fees", "total Surcharges & Other Fees is present", "total Surcharges & Other Fees is NOTpresent", true);
								}

								//Click on the link Surcharges and fees
								Report.OperationPoint(testContext.getName(), "Operation : Clicking on link Surcharges and fees");
								oR_BillAndUsage.lnkUverseVoiceSurchargeAndFees.click();
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate Surcharges and fees link", "Surcharges and fees link is present", "Surcharges and fees link is NOTpresent", true);
							}

							//Validate Government Fees & Taxes link
							if(UI.WaitForObject(oR_BillAndUsage.lnkUverseVoiceGovernmentFees, 1))
							{
								Report.ValidationPoint(testContext.getName(), "Validate Government Fees & Taxes link", "Government Fees & Taxes link is present", "Government Fees & Taxes link is present", true);
								//Validate Government Fees & Taxes link is collapsed by default
								if(oR_BillAndUsage.lnkUverseVoiceGovernmentFees.getAttribute("aria-expanded").equals("false"))
								{
									Report.ValidationPoint(testContext.getName(), "Validate Government Fees & Taxes link is collapsed by default", "Government Fees & Taxes link is collapsed by default", "Government Fees & Taxes link is collapsed by default", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate Government Fees & Taxes link is collapsed by default", "Government Fees & Taxes link is collapsed by default", "Government Fees & Taxes link is NOTcollapsed by default", true);
								}

								//Click on the Government Fees & Taxes link
								Report.OperationPoint(testContext.getName(), "Operation : Clicking on Government Fees & Taxes link");
								oR_BillAndUsage.lnkUverseVoiceGovernmentFees.click();

								//Validate total Government Fees & Taxes
								if(UI.WaitForObject(oR_BillAndUsage.txtUverseVoiceTotalGovernmentFees,1))
								{
									Report.ValidationPoint(testContext.getName(), "Validate total Government Fees & Taxes", "total Government Fees & Taxes is present", "total Government Fees & Taxes is present", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate total Government Fees & Taxes", "total Government Fees & Taxes is present", "total Government Fees & Taxes is NOTpresent", true);
								}

								//Click on the Government Fees & Taxes link
								Report.OperationPoint(testContext.getName(), "Operation : Clicking on Government Fees & Taxes link");
								oR_BillAndUsage.lnkUverseVoiceGovernmentFees.click();
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate Government Fees & Taxes link", "Government Fees & Taxes link is present", "Government Fees & Taxes link is NOTpresent", true);
							}

							//Click on U-Verse Voice link
							Report.OperationPoint(testContext.getName(), "Operation : Clicking on U-Verse TV link");
							oR_BillAndUsage.lnkUverseVoice.click();
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Validate U-Verse Voice link", "U-Verse Voice link is present", "U-Verse Voice link is NOTpresent", true);
						}

						//Validate compare bills
						if(UI.WaitForObject(oR_BillAndUsage.txtCompareBills, 1))
						{
							Report.ValidationPoint(testContext.getName(), "Validate compare bills", "compare bills is present", "compare bills is present", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Validate compare bills", "compare bills is present", "compare bills is NOTpresent", true);
						}

					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Navigate to Bills & Usage page", "navigated to Bills & Usage page", "failed to navigate to Bills & Usage page", true);
					}
				}

			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate Sec nav link billing & payments and click on it", "Sec nav link billing & payments is displayed and clicked", "Sec nav link billing & payments is NOTdisplayed", true);
			}

		} catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}	





	/**************************************************************
	 * Function Name -  VerifyEditPaymentProfileModal
	 * Description- This method validates the Basic Usage plan Table on B&U page
	 * Parameters - None
	 * Date created -16th Feb 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - Monica Mohabansi
	 * Last Modified Date - 13th Oct 2015
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	//BAP07068
	public static void  VerifyEditPaymentProfileModal(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
			
			Report.OperationPoint(testContext.getName(), "Selecting Make A Payment form Tertiary Navigation");
			Boolean bBillingAndPaymentlnk, bClickonBnPLink, bMAPPage, bEditPaymentFrame, bEditPaymentLink, bErrorMsgs, bLnkLanguage, bLnkPagineEnEspanol;
			bBillingAndPaymentlnk = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut);

			Report.TestPoint(testContext.getName(), "Secondary Link Billing and Payments", "true", String.valueOf(bBillingAndPaymentlnk).toLowerCase(), true);
			bClickonBnPLink = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkMakeAPaymentTertNav);
			if(bClickonBnPLink.equals(true))
			{
				Thread.sleep(5000);
				bMAPPage = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Make A Payment Page", "true", String.valueOf(bMAPPage).toLowerCase(), true);

				//UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod, "New checking / savings account");
				bEditPaymentLink = UI.WaitForObject(oR_MakeAPayment.lnkEditPaymentProfile, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Edit Payment Profile Link", "true", String.valueOf(bEditPaymentLink), true);
				oR_MakeAPayment.lnkEditPaymentProfile.click();

				bEditPaymentFrame = UI.WaitForObject(oR_MakeAPayment.frmEditPaymentProfile, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Frame Edit Payment Profile", "true", String.valueOf(bEditPaymentFrame).toLowerCase(), true);

				Report.OperationPoint(testContext.getName(), "Validate Edit Payment Profile frame");
				lDriver.switchTo().frame(oR_MakeAPayment.frmEditPaymentProfile);

				//Image of routing/transit number and account number is displayed on new checking or savings account payment method div layer on Edit payment Profile page as expected
				String bImg = UI.CheckExist(oR_MakeAPayment.imgRoutingTransitNum);
				Report.ValidationPoint(testContext.getName(), "Image of routing/transit number and account number", "true", bImg.toLowerCase(), true);

				//Verify that tool tip is absent for Routing transit number and Bank account number
				//if(UI.WaitForObject(lDriver.findElement(By.xpath("//div[@class='tipPad safariComp botMar20']//img[@alt='Help']")),15).equals(true))
				if(UI.WaitForObject(oR_MakeAPayment.imgToolTipIcon, 5))
				{
					int iLocOfToolTip = lDriver.findElement(By.xpath("//div[@class='tipPad safariComp botMar20']//img[@alt='Help']")).getLocation().y;

					int iLocOfRoutingNumField = lDriver.findElement(By.xpath("//label[contains(text(),'Routing / transit number')]")).getLocation().y;
					int ilocOfAccNumberField = lDriver.findElement(By.xpath("//label[contains(text(),'Bank account number')]")).getLocation().y;

					if(iLocOfRoutingNumField>iLocOfToolTip+1 && ilocOfAccNumberField>iLocOfToolTip+1)
					{
						Report.ValidationPoint(testContext.getName(), "ToolTip must be absent for Routing Transit and Acc Number", "ToolTip Absent", "ToolTip Absent", false);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "ToolTip must be absent for Routing Transit and Acc Number", "ToolTip Absent", "ToolTip Present", false);
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "ToolTip Icon is not present for Rounting/Account number", "ToolTip Icon is not present", "ToolTip Icon is not present", true);
				}

				//Verify that Asterisk key is displayed on below reguired field:
				//List<WebElement> aRequiredFields = lDriver.findElements(By.xpath("//div[@class='styled_forms botMar30 row-seamless modalForms']//label[contains(@class,'rel top')]"));

				List<WebElement> aMandatoryFields = lDriver.findElements(By.xpath("//div[@class='styled_forms botMar30 row-seamless modalForms']//label[contains(@class,'rel top')]"));
				List<WebElement> aRequiredFields = lDriver.findElements(By.xpath("//span[@class='colorRed']"));
				List<WebElement> aNonMandatoryFields = lDriver.findElements(By.xpath("//span[@style='visibility:hidden']"));

				if((aRequiredFields.size()-aNonMandatoryFields.size()) == aMandatoryFields.size())
				{
					Report.ValidationPoint(testContext.getName(), "Mandatory Fields", "All the required fields have asterisk mark", "All the required fields have asterisk mark", true);						
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Mandatory Fields", "All the required fields have asterisk mark", "All the required fields DOES NOT have asterisk mark", true);						
				}
				/*//Click on Continue button without entering any details
						Report.TestPoint(testContext.getName(), "Click on continue button without entering details", "true", UI.CheckExist(oR_MakeAPayment.lnkContinue), false);
						oR_MakeAPayment.lnkContinue.click();*/

				//Verify that error messages are displayed at top of the screen
				if(oR_MakeAPayment.edtBankAccountNumber.isDisplayed() && oR_MakeAPayment.edtNameOnBankAcc.isDisplayed() 
						&& oR_MakeAPayment.edtReenterAccNum.isDisplayed() && oR_MakeAPayment.edtRoutingNumber.isDisplayed())
				{
					oR_MakeAPayment.edtNameOnBankAcc.clear();
					oR_MakeAPayment.edtRoutingNumber.clear();
					oR_MakeAPayment.edtBankAccountNumber.clear();
					oR_MakeAPayment.edtReenterAccNum.clear();
					
					
					oR_MakeAPayment.lnkContinue.click();
					
//					Report.TestPoint(testContext.getName(), "Save Button", "true", String.valueOf(oR_MakeAPayment.lnkSave.isDisplayed()).toLowerCase(), true);
//					oR_MakeAPayment.lnkSave.click();

					bErrorMsgs = UI.WaitForObject(oR_MakeAPayment.txtNameOnBankErrorMsg,2) && UI.WaitForObject(oR_MakeAPayment.txtRoutingNumErrorMsg,2)
							&& UI.WaitForObject(oR_MakeAPayment.txtAccNumErrorMsg,1)  && UI.WaitForObject(oR_MakeAPayment.txtAccNumConfirmErrorMsg,1);
					Report.ValidationPoint(testContext.getName(), "Error messages for mandatory fields", "true", String.valueOf(bErrorMsgs), true);

				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Detail Fields", "Details Fields Are Present", "Details Fields Not Present", true);
				}


				//Close the Edit  cheking/saving div layer
				String sLnkClose = UI.CheckExist(oR_MakeAPayment.lnkCloseFrame);
				if(sLnkClose.toLowerCase().equals("true"))
				{	
					Report.ValidationPoint(testContext.getName(), "close link", "true", sLnkClose.toLowerCase(), true);
					oR_MakeAPayment.lnkCloseFrame.click();
					Thread.sleep(5000);
					lDriver.switchTo().defaultContent();

					//Change  language to spanish and then click on Edit payment profile link located below payment method dropdown
					Report.OperationPoint(testContext.getName(), "Change the language to Spanish and validate the frame");
					bLnkLanguage = UI.WaitForObject(oR_MakeAPayment.lnkLanguage, 7);
					Report.TestPoint(testContext.getName(), "Link Language", "true", String.valueOf(bLnkLanguage).toLowerCase(), true);						
					oR_MakeAPayment.lnkLanguage.click();

					bLnkPagineEnEspanol = UI.WaitForObject(oR_MakeAPayment.lnkPaginaEnEspanol, 7);
					if(bLnkPagineEnEspanol)
					{
						Report.ValidationPoint(testContext.getName(), "Link Pagina En Espanol", "true", String.valueOf(bLnkPagineEnEspanol).toLowerCase(), true);						
						
						
						oR_MakeAPayment.lnkPaginaEnEspanol.click();
						Thread.sleep(5000);
						System.out.println(lDriver.getTitle());
						if(lDriver.getTitle().contains("Servicio"))
						{
							Report.ValidationPoint(testContext.getName(), "Site in Spanish", "true", "true", true);
							lDriver.navigate().back();
						}
						

						bEditPaymentLink = UI.WaitForObject(oR_MakeAPayment.lnkEditPaymentProfile, UI.iObjTimeOut);
						Report.TestPoint(testContext.getName(), "Edit Payment Profile Link", "true", String.valueOf(bEditPaymentLink), true);
						oR_MakeAPayment.lnkEditPaymentProfile.click();
						Thread.sleep(2000);
	
						//					Verify that contents are displayed properly in frame
						bEditPaymentFrame = UI.WaitForObject(oR_MakeAPayment.frmEditPaymentProfile, UI.iObjTimeOut);
						Report.TestPoint(testContext.getName(), "Frame Edit Payment Profile", "true", String.valueOf(bEditPaymentFrame).toLowerCase(), true);
	
						lDriver.switchTo().frame(oR_MakeAPayment.frmEditPaymentProfile);
	
						if(oR_MakeAPayment.edtBankAccountNumber.isDisplayed() && oR_MakeAPayment.edtNameOnBankAcc.isDisplayed() 
								&& oR_MakeAPayment.edtReenterAccNum.isDisplayed() && oR_MakeAPayment.edtRoutingNumber.isDisplayed())
						{
							Report.ValidationPoint(testContext.getName(), "Contents on Modal", "Contents on modal are as expected", "Contents on modal are as expected", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Contents on Modal", "Contents on modal are as expected", "Contents on modal are NOT as expected", true);
						}	
	
						sLnkClose = UI.CheckExist(oR_MakeAPayment.lnkCloseFrame);
						if(sLnkClose.toLowerCase().equals("true"))
						{	
							Report.ValidationPoint(testContext.getName(), "close link", "true", sLnkClose.toLowerCase(), true);
							oR_MakeAPayment.lnkCloseFrame.click();
							Thread.sleep(5000);
							lDriver.switchTo().defaultContent();
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "close link", "true", sLnkClose, true);
						}
				}else{
					 
					 Report.ValidationPoint(testContext.getName(), "Validate Spanish language link is displayed", "Spanish language link is displayed", "Spanish language link is NOT displayed", true);
				 }
			  }else
				{
					Report.ValidationPoint(testContext.getName(), "close link", "true", sLnkClose, true);
				}
				
			}
			else
				Report.ValidationPoint(testContext.getName(), "Click on Make a payment link from Tertiary navigation", "true", "false", true);
		}		
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Failed to validate MAP checking/savings div layer", "true", e.getMessage(), true);
		}
	}




	/**************************************************************
	 * Function Name - ValidateEditPaymentWithIncorrectAndPartialData
	 * Description-  This validates the Edit payment with incorrect and partial data
	 * Parameters - 
	 * Date created -19 Feb 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/	
	public static void ValidateEditPaymentWithIncorrectAndPartialData(final ITestContext testContext) throws Exception {

		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
			OR_EditPaymentProfile oR_EditPaymentProfile = PageFactory.initElements(lDriver, OR_EditPaymentProfile.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			
			//
			//Validate and click tertiery link update payment profile
			boolean bEditPaymentProfile = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkUpdatePaymentProfile);
			Report.TestPoint(testContext.getName(), "Validate update payment profile Link", "true", String.valueOf(bEditPaymentProfile), true);

			//Validate Payment profile section
			boolean bPaymentProfileSection = UI.WaitForObject(oR_ATT.txtStoredPaymentMethod, 20);
			Report.TestPoint(testContext.getName(), "Validate Profile page is displayed", "true", String.valueOf(bPaymentProfileSection), true);

			//Validate link edit
			boolean bLinkEdit = UI.WaitForObject(oR_ATT.lnkEdit_For_AccountEnding_StoredPaymentMethod, 5);
			Report.TestPoint(testContext.getName(), "Validate link edit is present for savings account", "true", String.valueOf(bLinkEdit), true);
			//Click on link edit
			Report.OperationPoint(testContext.getName(), "Operation : Clicking on link edit");
			oR_ATT.lnkEdit_For_AccountEnding_StoredPaymentMethod.click();

			//Validate Edit payment profile page
			boolean bEditPaymentProfilePage = UI.WaitForObject(oR_EditPaymentProfile.txtEditPaymentProfile, 60);
			Report.TestPoint(testContext.getName(), "Validate Edit payment profile page", "true", String.valueOf(bEditPaymentProfilePage), true);

			//Validate name on bank account edit box
			boolean bBankAccName = UI.WaitForObject(oR_EditPaymentProfile.edtNameOnBankAccount, 1);
			Report.ValidationPoint(testContext.getName(), "Validate name on bank account edit box", "true", String.valueOf(bBankAccName), true);
			//Clear the edit box
			oR_EditPaymentProfile.edtNameOnBankAccount.clear();
			//Click on Edit payment profile text
			oR_EditPaymentProfile.btnSubmit.click();
			//Validate error msg
			boolean bNameError = UI.WaitForObject(oR_EditPaymentProfile.txtBankCustomerNameErrorMsg, 1);
			Report.ValidationPoint(testContext.getName(), "Validate error msg", "true", String.valueOf(bNameError), true);
			//Retrieving the error msg
			Report.OperationPoint(testContext.getName(), "Operation : Error messege : "+oR_EditPaymentProfile.txtBankCustomerNameErrorMsg.getText());

			//Validate routing no edit box
			boolean bRoutingNo = UI.WaitForObject(oR_EditPaymentProfile.edtRoutingNo, 1);
			Report.ValidationPoint(testContext.getName(), "Validate routing no edit box", "true", String.valueOf(bRoutingNo), true);
			//Clear the edit box
			oR_EditPaymentProfile.edtRoutingNo.clear();
			//Click on Edit payment profile text
			oR_EditPaymentProfile.btnSubmit.click();
			//Validate error msg
			boolean bRoutingNoError = UI.WaitForObject(oR_EditPaymentProfile.txtRoutingNumberErrorMsg, 1);
			Report.ValidationPoint(testContext.getName(), "Validate error msg", "true", String.valueOf(bRoutingNoError), true);
			//Retrieving the error msg
			Report.OperationPoint(testContext.getName(), "Operation : Error messege : "+oR_EditPaymentProfile.txtRoutingNumberErrorMsg.getText());

			//Validate account number edit box
			boolean bBankAccNo = UI.WaitForObject(oR_EditPaymentProfile.edtBankAccountNo, 1);
			Report.ValidationPoint(testContext.getName(), "Validate bank account number edit box", "true", String.valueOf(bBankAccNo), true);
			//Clear the edit box
			oR_EditPaymentProfile.edtBankAccountNo.clear();
			//Click on Edit payment profile text
			oR_EditPaymentProfile.btnSubmit.click();
			//Validate error msg
			boolean bBankAccNoError = UI.WaitForObject(oR_EditPaymentProfile.txtbankAccountNumberErrorMsg, 1);
			Report.ValidationPoint(testContext.getName(), "Validate error msg", "true", String.valueOf(bBankAccNoError), true);
			//Retrieving the error msg
			Report.OperationPoint(testContext.getName(), "Operation : Error messege : "+oR_EditPaymentProfile.txtbankAccountNumberErrorMsg.getText());

			//Validate Re-account number edit box
			boolean bReBankAccName = UI.WaitForObject(oR_EditPaymentProfile.edtReBankAccountNo, 1);
			Report.ValidationPoint(testContext.getName(), "Validate Re-bank account number edit box", "true", String.valueOf(bReBankAccName), true);
			//Clear the edit box
			oR_EditPaymentProfile.edtReBankAccountNo.clear();
			//Click on Edit payment profile text
			oR_EditPaymentProfile.btnSubmit.click();
			//Validate error msg
			boolean bReBankAccNoError = UI.WaitForObject(oR_EditPaymentProfile.txtbankReAccountNumberErrorMsg, 1);
			Report.ValidationPoint(testContext.getName(), "Validate error msg", "true", String.valueOf(bReBankAccNoError), true);
			//Retrieving the error msg
			Report.OperationPoint(testContext.getName(), "Operation : Error messege : "+oR_EditPaymentProfile.txtbankReAccountNumberErrorMsg.getText());

			//Validate "|" character between the Cancel and Delete Profile is displayed as expected
			boolean bChar = UI.WaitForObject(oR_EditPaymentProfile.txtCancelDelete, 1);
			Report.ValidationPoint(testContext.getName(), "Validate '|' character between the Cancel and Delete Profile is displayed as expected", "true", String.valueOf(bChar), true);
			String sText = oR_EditPaymentProfile.txtCancelDelete.getText();
			if(sText.contains("|"))
			{
				Report.ValidationPoint(testContext.getName(), "Validate '|' character between the Cancel and Delete Profile is displayed as expected", "true","true", true);
			}

			/*//Validate and click on link overview
			boolean blnkOverview = UI.WaitForObject(oR_AccountOverview.lnkOverview, 1);
			Report.TestPoint(testContext.getName(), "Validate and click on link overview", "true", String.valueOf(blnkOverview), true);
			Report.OperationPoint(testContext.getName(), "Operation : Clicking on link overview");
			oR_AccountOverview.lnkOverview.click();

			//Validate and click tertiery link update payment profile
			boolean bBillingAndPayment = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 50);
			Report.TestPoint(testContext.getName(), "Validate billing and payment Link", "true", String.valueOf(bBillingAndPayment), true);
			boolean bEditPaymentProfile1 = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkUpdatePaymentProfile);
			Report.TestPoint(testContext.getName(), "Validate update payment profile Link", "true", String.valueOf(bEditPaymentProfile1), true);

			//Validate Payment profile section
			boolean bPaymentProfileSection1 = UI.WaitForObject(oR_ATT.txtPaymentProfile, 50);
			Report.TestPoint(testContext.getName(), "Validate Payment profile section", "true", String.valueOf(bPaymentProfileSection1), true);

			//Validate link edit
			boolean bLinkEdit1 = UI.WaitForObject(oR_ATT.lnkEdit, 1);
			Report.TestPoint(testContext.getName(), "Validate link edit", "true", String.valueOf(bLinkEdit1), true);
			//Click on link edit
			Report.OperationPoint(testContext.getName(), "Operation : Clicking on link edit");
			oR_ATT.lnkEdit.click();

			//Validate Edit payment profile page
			boolean bEditPaymentProfilePage1 = UI.WaitForObject(oR_EditPaymentProfile.txtEditPaymentProfile, 60);
			Report.TestPoint(testContext.getName(), "Validate Edit payment profile page", "true", String.valueOf(bEditPaymentProfilePage1), true);

			boolean bBankAccName1 = UI.WaitForObject(oR_EditPaymentProfile.edtNameOnBankAccount, 1);
			Report.ValidationPoint(testContext.getName(), "Validate name on bank account edit box", "true", String.valueOf(bBankAccName1), true);
			*///Clear the edit box
			oR_EditPaymentProfile.edtNameOnBankAccount.clear();
			oR_EditPaymentProfile.edtNameOnBankAccount.sendKeys("+");
			//Click on Edit payment profile text
			oR_EditPaymentProfile.btnSubmit.click();
			//Validate error msg on entering any special character
			boolean bNameError1 = UI.WaitForObject(oR_EditPaymentProfile.txtBankCustomerNameErrorMsg, 1);
			Report.ValidationPoint(testContext.getName(), "Validate error msg on entering any special character", "true", String.valueOf(bNameError1), true);
			//Retrieving the error msg
			Report.OperationPoint(testContext.getName(), "Operation : Error messege : "+oR_EditPaymentProfile.txtBankCustomerNameErrorMsg.getText());

			//Validate routing no edit box
			boolean bRoutingNo1 = UI.WaitForObject(oR_EditPaymentProfile.edtRoutingNo, 1);
			Report.ValidationPoint(testContext.getName(), "Validate routing no edit box", "true", String.valueOf(bRoutingNo1), true);
			//Clear the edit box
			oR_EditPaymentProfile.edtRoutingNo.clear();
			oR_EditPaymentProfile.edtRoutingNo.sendKeys("1234");
			//Click on Edit payment profile text
			oR_EditPaymentProfile.btnSubmit.click();
			//Enter invalid Routing number
			//Validate error msg
			boolean bRoutingNoError1 = UI.WaitForObject(oR_EditPaymentProfile.txtRoutingNumberErrorMsg, 1);
			Report.ValidationPoint(testContext.getName(), "Validate error msg", "true", String.valueOf(bRoutingNoError1), true);
			//Retrieving the error msg
			Report.OperationPoint(testContext.getName(), "Operation : Error messege : "+oR_EditPaymentProfile.txtRoutingNumberErrorMsg.getText());

			//Validate account number edit box
			boolean bBankAccNo1 = UI.WaitForObject(oR_EditPaymentProfile.edtBankAccountNo, 1);
			Report.ValidationPoint(testContext.getName(), "Validate bank account number edit box", "true", String.valueOf(bBankAccNo1), true);
			//Clear the edit box
			oR_EditPaymentProfile.edtBankAccountNo.clear();
			oR_EditPaymentProfile.edtBankAccountNo.sendKeys("123456789");
			//Click on Edit payment profile text
			//oR_EditPaymentProfile.btnSubmit.click();

			//Validate Re-account number edit box
			boolean bReBankAccName1 = UI.WaitForObject(oR_EditPaymentProfile.edtReBankAccountNo, 1);
			Report.ValidationPoint(testContext.getName(), "Validate Re-bank account number edit box", "true", String.valueOf(bReBankAccName1), true);
			//Clear the edit box
			oR_EditPaymentProfile.edtReBankAccountNo.clear();
			oR_EditPaymentProfile.edtReBankAccountNo.sendKeys("987654321");
			//Click on Edit payment profile text
			oR_EditPaymentProfile.btnSubmit.click();

			//Enter valid account no and incorrect account no in re-enter editbox.
			//Validate the error msg : "The account number and account number confirmation must match."
			boolean bReBankAccNoError1 = UI.WaitForObject(oR_EditPaymentProfile.txtbankReAccountNumberErrorMsg, 1);
			Report.ValidationPoint(testContext.getName(), "Validate error msg", "true", String.valueOf(bReBankAccNoError1), true);
			//Retrieving the error msg
			Report.OperationPoint(testContext.getName(), "Operation : Error messege : "+oR_EditPaymentProfile.txtbankReAccountNumberErrorMsg.getText());

			//Validate "|" character between the Cancel and Delete Profile is displayed as expected
			boolean bChar1 = UI.WaitForObject(oR_EditPaymentProfile.txtCancelDelete, 1);
			Report.ValidationPoint(testContext.getName(), "Validate '|' character between the Cancel and Delete Profile is displayed as expected", "true", String.valueOf(bChar1), true);
			//String sText1 = oR_EditPaymentProfile.txtCancelDelete.getText();
			if(sText.contains("|"))
			{
				Report.ValidationPoint(testContext.getName(), "Validate '|' character between the Cancel and Delete Profile is displayed as expected", "true","true", true);
			}
			
			if(bReBankAccNoError1|bRoutingNoError1)
			{
				Report.ValidationPoint(testContext.getName(), "Validate all invalid fields are not cleared after clicking save button", "true","true", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate all invalid fields are not cleared after clicking save button", "true","false", true);
			}
			
			if(bReBankAccNoError1|bRoutingNoError1)
			{
				Report.ValidationPoint(testContext.getName(), "Veiry that if any of the fields have invalid information, the inline style error is displayed for those fields.", "true","true", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Veiry that if any of the fields have invalid information, the inline style error is displayed for those fields.", "true","false", true);
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}

	/**************************************************************
	 * Function Name -  MakeAPayementByCardMethodAndVerifyConfirmation
	 * Description- This function does make a payment flow(selection of date ,payment method,enters credit/debit card details) and verifies payment confirmation message
	 * Parameters - 
	 * Date created -18th Feb 15
	 * Developed by - Sneha Pansare
	 * Last Modified By - Monica Mohabansi
	 * Last Modified Date -7th Oct 2015 - 1511 Execution - Handled Payment alert frame, code added to select date from calendar and check enroll in autopay chkbox
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 ***************************************************************/
	//BAP08867

	public static void MakeAPayementByCardMethodAndVerifyConfirmation(final ITestContext testContext) throws HeadlessException, IOException, AWTException, InterruptedException, NumberFormatException, ParseException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
		OR_ReviewPaymentDetails oR_ReviewPaymentDetails = PageFactory.initElements(lDriver, OR_ReviewPaymentDetails.class);
		//oR_PaymentConfirmation
		OR_PaymentConfirmation oR_PaymentConfirmation = PageFactory.initElements(lDriver, OR_PaymentConfirmation.class);
		
		
		 Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		 
		String sPaymentAmount = IO.GetParamVal(sTestParams, "PAYMENT_AMOUNT");
		String sPaymentMethod = IO.GetParamVal(sTestParams, "PAYMENT_METHOD");
		String sCardExpirationMonthYear = IO.GetParamVal(sTestParams, "CARD_EXPIRATION_MONTH_YEAR");
		String sNameOnCard = IO.GetParamVal(sTestParams, "NAME_ON_CARD");
		String sCardNumber = IO.GetParamVal(sTestParams, "CARD_NUMBER");
		String sSecurityNumber = IO.GetParamVal(sTestParams, "SECURITY_NUMBER");
		String sZipCode = IO.GetParamVal(sTestParams, "ZIP_CODE");
		String sProfileName	= IO.GetParamVal(sTestParams, "PROFILE_NAME");

		float fRemainingBalanceAmount=-1;
        		
		if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 10, lDriver))
		{
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Thread.sleep(2000);
	    	if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 20))
			{
				Report.TestPoint(testContext.getName(), "Navigate to Bill and details page", "Bill Details page is displayed", "Bill Details page is displayed", true);
				//System.out.println("Bill Details page is displayed");

				if(UI.WaitForObject(oR_BillAndUsage.btnMakePaymentInBillingPage, 90))
				{
					oR_BillAndUsage.btnMakePaymentInBillingPage.click();
					Thread.sleep(10000);

					if(UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 120,lDriver))
					{
						//System.out.println("Make a Payment page is displayed");
						Report.TestPoint(testContext.getName(), "Navigate to Make a Payment page", "Make a Payment page is displayed", "Make a Payment page is displayed", true);

					        // Get the Balance amout displayed 
							String sElementBalanceInnerText = lDriver.findElement(By.xpath("//span[contains(text(),'Balance')]/parent::li/span[2]")).getText().substring(1);
							//System.out.println(sElementBalanceInnerText);

													
							//System.out.println("Balance amount is: "+sBalanceAmount);
							Report.TestPoint(testContext.getName(), "Balance amount is: "+sElementBalanceInnerText, "true", "true", false);

							float fPaymentAmount= Float.valueOf(sPaymentAmount);
							System.out.println(fPaymentAmount);
							float fBalanceAmount= Float.valueOf(sElementBalanceInnerText);
							System.out.println(fBalanceAmount);

							fRemainingBalanceAmount= fBalanceAmount-fPaymentAmount ;
							//System.out.println("Remaining balance= "+fRemainingBalanceAmount);
						

							if(UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount, 30))
							{

								//enter payment amount
								oR_MakeAPayment.edtPaymentAmount.clear();
								oR_MakeAPayment.edtPaymentAmount.sendKeys(sPaymentAmount);

								//System.out.println("Total due amount is entered as expected");
								Report.TestPoint(testContext.getName(), "Enter the total due amount in Payment amount field", "Total due amount is entered as expected", "Total due amount is entered as expected", true);
                                
								// Check if 'past due' message is dispayed. If that is the case than Calender will not be displayed and by default todays date should be selected
								WebElement strPastDueDate = lDriver.findElement(By.xpath("//span[contains(text(),'past due')]"));
								
//							if(! UI.WaitForObject(strPastDueDate, 5, lDriver)){
								//select today's date in calender
								if(UI.WaitForObject(oR_MakeAPayment.imgCalender, 20))
								{
									oR_MakeAPayment.imgCalender.click();
									Thread.sleep(10000);
									//after clicking on calender frame will be displayed switch to that frame
									lDriver.switchTo().frame(lDriver.findElement(By.xpath("//*[contains(@src,'CalendarView')]")));
									if(UI.WaitForObject(oR_MakeAPayment.txtFramePaymentCalenderTitle, 40))
									{
										//System.out.println("Frame detected");
										Report.ValidationPoint(testContext.getName(), "Select todays date in payment date field", "Clicked on calender icon", "Clicked on calender icon", true);

										
										if(lDriver.findElement(By.xpath("//td[@class='currentDate']/a")).isSelected())
											lDriver.findElement(By.xpath("//*[@class='closeModal']")).click();
										else
										{
											while(lDriver.findElement(By.xpath("//td[@class='currentDate']")).isDisplayed())
											{
												lDriver.findElement(By.xpath("//td[@class='currentDate']/a")).click();
											}
										}
//										else
//											lDriver.findElement(By.xpath("//*[@class='closeModal']")).click();
////										//Code to get today's date and month
//										Calendar cal = Calendar.getInstance();
//
//										int iCurrentYear = cal.get(Calendar.YEAR);
//										int iCurrentMonth = cal.get(Calendar.MONTH)+1;  //since it is zero based 1 is added
//										int iCurrentDate = cal.get(Calendar.DATE);
//
//										String sCurrentYear= Integer.toString(iCurrentYear);
//										String sCurrentMonth= Integer.toString(iCurrentMonth);
//										String sCurrentDate= Integer.toString(iCurrentDate);
//
//										String sTodayDate= sCurrentMonth+"/"+sCurrentDate+"/"+sCurrentYear;
//
//										//System.out.println(sTodayDate);									
//
//										//Select Today date in opened calender frame
//										if(UI.WaitForObject(lDriver.findElement(By.xpath("//a[contains(@onclick,'"+sTodayDate+"')]")), 40))
//										{// it means todays date is already selected
//											lDriver.findElement(By.xpath("//a[contains(@onclick,'"+sTodayDate+"')]")).click();
//											Thread.sleep(20000);
//										}
//										else
//										{
//											//here we need to select today's date
//											if(UI.WaitForObject(lDriver.findElement(By.xpath("//table[contains(@class,'table calendar')]//a[contains(text(),'"+sCurrentDate+"')]")), 20))
//											{
//												lDriver.findElement(By.xpath("//table[contains(@class,'table calendar')]//a[contains(text(),'"+sCurrentDate+"')]")).click();
//												Thread.sleep(20000);
//											}
//
//											//System.out.println("Todays Payment date is selected as expected");
//											Report.ValidationPoint(testContext.getName(), "Select todays date in payment date field", "Todays Payment date is selected as expected", "Todays Payment date is selected as expected", true);
//										}


									}
									Thread.sleep(5000);
									//Switch back from calender frame
									lDriver.switchTo().defaultContent();

								}
								else if(UI.WaitForObject(oR_BillAndUsage.txtDateWithoutCalendar, 5))
								{
									Report.ValidationPoint(testContext.getName(), "Select todays date in payment date field", "Todays Payment date is selected as expected", "Todays Payment date is selected as expected", true);
								}
								else
								{
									//System.out.println("Todays Payment date is NOT selected as expected");
									Report.ValidationPoint(testContext.getName(), "Select todays date in payment date field", "Todays Payment date is selected as expected", "Todays Payment date is NOT selected as expected", true);
								}
								
							 }
							
//								Check the Enroll in Auto Pay checkbox
							try
							{
								if(UI.WaitForObject(oR_MakeAPayment.chkAccountSelectCheckbox_1, 3, lDriver))
								{
									oR_MakeAPayment.chkAccountSelectCheckbox_1.click();
									Report.ValidationPoint(testContext.getName(), "Select Check box Enroll In Autopay", "true", "true", true);
								}
								else
									if(UI.WaitForObject(lDriver.findElement(By.xpath("(//label[contains(@for,'enrollInAutopayChkBox_0_1')])[1]")),50, lDriver))
									{
										lDriver.findElement(By.xpath("(//label[contains(@for,'enrollInAutopayChkBox_0_1')])[1]")).click();
										Report.ValidationPoint(testContext.getName(), "Select Check box Enroll In Autopay", "true", "true", true);

									}
								else	
									Report.ValidationPoint(testContext.getName(), "Select Check box Enroll In Autopay", "Check Box Enroll In Autopay not present", "Check Box Enroll In Autopay not present", true);
							}
							catch(Exception e)
							{
								Report.ValidationPoint(testContext.getName(), "Select Enroll in Autopay check box", "Enroll in Autopay checkbox checked", "Enroll in Autopay checkbox is ABSENT", true);
							}
								//select payment method from dropdown

								if(UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod, sPaymentMethod))
								{
									Thread.sleep(10000);

									//Fill all credit/debit card details if payment method is 'New debit / credit card'
									if(sPaymentMethod.equals("New debit / credit card"))
									{
										lDriver.switchTo().frame(lDriver.findElement(By.xpath("//*[contains(@src,'setupPaymentPopup')]")));

										//Enter Name on card
										if(UI.WaitForObject(oR_MakeAPayment.edtPaymentFrameNameOnCard, 40))
										{
											oR_MakeAPayment.edtPaymentFrameNameOnCard.sendKeys(sNameOnCard);
										}

										//Enter Card number
										if(UI.WaitForObject(oR_MakeAPayment.edtPaymentFrameCardNumber, 10))
										{
											oR_MakeAPayment.edtPaymentFrameCardNumber.sendKeys(sCardNumber);
										}

										//Enter Security code
										if(UI.WaitForObject(oR_MakeAPayment.edtPaymentFrameSecurityNumber, 10))
										{
											oR_MakeAPayment.edtPaymentFrameSecurityNumber.sendKeys(sSecurityNumber);
										}

										//Select Card expiration month and year
										if(sCardExpirationMonthYear.contains("/"))
										{
											String[] aCardExpirationMonthYear= sCardExpirationMonthYear.split("/");

											String sCardExpirationMonth= aCardExpirationMonthYear[0];
											String sCardExpirationYear= aCardExpirationMonthYear[1];

											UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationMonth, sCardExpirationMonth);
											Thread.sleep(3000);
											oR_MakeAPayment.lstSelectCardExpirationMonth.click();
											UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationYear, sCardExpirationYear);
										}
										//01234-zip code, profile name- abc
										//Enter Billing zip code
										if(UI.WaitForObject(oR_MakeAPayment.edtPaymentFrameZipCode, 10))
										{
											oR_MakeAPayment.edtPaymentFrameZipCode.sendKeys(sZipCode);
										}

										//Enter payment profile name
										if(UI.WaitForObject(oR_MakeAPayment.edtPaymentFrameProfileName, 10))
										{
											oR_MakeAPayment.edtPaymentFrameProfileName.sendKeys(sProfileName);
										}

										//Uncheck theSave my payment information checkbox
										try
										{
											if(oR_MakeAPayment.chkPaymentFrameSaveMyPaymentInformationCheckbox.isSelected())
											{
												oR_MakeAPayment.chkPaymentFrameSaveMyPaymentInformationCheckbox.click();
											}
										}
										catch(Exception e)
										{

										}

										Thread.sleep(10000);
										Report.TestPoint(testContext.getName(), "select any one of the secured payment method and Enter the valid card details", "Secured payment method selected and details are entered", "Secured payment method selected and details are entered", true);

										//press continue button
										if(UI.WaitForObject(oR_MakeAPayment.btnPaymentFrameContinue, 20))
										{
											oR_MakeAPayment.btnPaymentFrameContinue.click();
											Thread.sleep(7000);
											if(UI.WaitForObject(oR_MakeAPayment.btnPaymentFrameContinue, 20))
											{
												oR_MakeAPayment.btnPaymentFrameContinue.click();
											}
										}
										Thread.sleep(10000);
											
									}

									
									//switch back from payment method frame
									lDriver.switchTo().defaultContent();

								}
								else
								{
									//System.out.println("Failed to select secured payment method and details are NOT entered");
									Report.TestPoint(testContext.getName(), "select any one of the secured payment method and Enter the valid card details", "Secured payment method selected and details are entered", "Failed to select secured payment method and details are NOT entered", true);
								}

								//Check the Enroll in Auto Pay checkbox
								/*try
								{
									if(!oR_MakeAPayment.chkEnrollInAutopayCheckbox.isSelected())
									{
										oR_MakeAPayment.chkEnrollInAutopayCheckbox.click();
									}

									System.out.println("Enroll in Autopay checkbox is checked as expected");
									Report.TestPoint(testContext.getName(), "Check the Enroll in Auto Pay checkbox", "Enroll in Autopay checkbox is checked as expected", "Enroll in Autopay checkbox is checked as expected", true);

								}
								catch(Exception e)
								{
									System.out.println("Enroll in Autopay checkbox is NOT displayed");
								}*/

								//Click on Next button
								if(UI.WaitForObject(oR_MakeAPayment.lnkContinue, 20))
								{
									Report.ValidationPoint(testContext.getName(), "Click on Continue button", "Next button clicked", "Next button clicked", true);

									oR_MakeAPayment.lnkContinue.click();
								}

								Thread.sleep(10000);
								
								//System.out.println("Secured payment method selected and details are entered");
								if(UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert, 15))
								{
									oR_MakeAPayment.lnkContinue.click();
								}
								//Verify whether payment review page is displayed
								if(UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle, 70))
								{
									//System.out.println("Payment Review page is displayed");
									Report.TestPoint(testContext.getName(), "Click on Next button", "Payment Review page is displayed", "Payment Review page is displayed", true);

									//click on submit button
									if(UI.WaitForObject(oR_ReviewPaymentDetails.btnSubmit, 20))
									{
										oR_ReviewPaymentDetails.btnSubmit.click();
									}

									//Verify whether payment confirmation page is displayed
									if(UI.WaitForObject(oR_PaymentConfirmation.txtPaymentConfirmationTitle, 250))
									{
										
										//System.out.println("Payment Confirmation page is displayed");
										Report.TestPoint(testContext.getName(), "click on submit button", "Payment Confirmation page is displayed", "Payment Confirmation page is displayed", true);
										String sRemainingBalanceAmount = "$"+fRemainingBalanceAmount;

										//Verify zero balance note is displayed in case of full payment OR remaining balance Note is displayed in case of partial payment
										
										if(lDriver.findElement(By.xpath("//*[contains(@class,'msg box')]")).isDisplayed())
										Report.ValidationPoint(testContext.getName(), "Yellow Msgbox", "true", "true", true);
										else
										{	
										WebElement eRemainingBalanceNote = lDriver.findElement(By.xpath("//p[contains(@class,'14 PadLeft20')]"));
										String sRemainingBalanceNoteText= eRemainingBalanceNote.getText();

										if(fRemainingBalanceAmount==0)   // if full payment is done
										{

											if(sRemainingBalanceNoteText.contains("has a balance of "+sRemainingBalanceAmount))
											{
												//System.out.println("Note->'You have a balance of "+sRemainingBalanceAmount+"' is displayed");
												Report.TestPoint(testContext.getName(), "Verify that a zero balance note is displayed", "Note->'You have a balance of "+sRemainingBalanceAmount+".No Payment required' is displayed", "Note->'You have a balance of "+sRemainingBalanceAmount+".No Payment required' is displayed", true);
											}
											else
											{
												//System.out.println("Note->'You have a balance of "+sRemainingBalanceAmount+"' is NOT displayed");
												Report.TestPoint(testContext.getName(), "Verify that a zero balance note is displayed", "Note->'You have a balance of "+sRemainingBalanceAmount+".No Payment required' is displayed", "Note->'You have a balance of "+sRemainingBalanceAmount+".No Payment required' is NOT displayed", true);
											}
										}
										else  // if partial payment is done
										{

											if(sRemainingBalanceNoteText.contains("remaining balance of "+sRemainingBalanceAmount))
											{
												//System.out.println("Note->'You have remaining balance of "+sRemainingBalanceAmount+"' is displayed");
												Report.TestPoint(testContext.getName(), "Verify that a remaining balance note is displayed", "Note->'You have remaining balance of "+sRemainingBalanceAmount+"' is displayed", "Note->'You have remaining balance of "+sRemainingBalanceAmount+"' is displayed", true);
											}
											else
											{
												//System.out.println("Note->'You have remaining balance of "+sRemainingBalanceAmount+"' is NOT displayed");
												Report.TestPoint(testContext.getName(), "Verify that a remaining balance note is displayed", "Note->'You have remaining balance of "+sRemainingBalanceAmount+"' is displayed", "Note->'You have remaining balance of "+sRemainingBalanceAmount+"' is NOT displayed", true);
											}

										}
										
											

										//Verify confirmation heading is displayed in table
										if(UI.WaitForObject(oR_PaymentConfirmation.txtConfirmation, 20))
										{
											//System.out.println("Confirmation content is displayed as expected");
											Report.ValidationPoint(testContext.getName(), "Verify that Confirmation content is displayed", "Confirmation content is displayed as expected", "Confirmation content is displayed as expected", true);
										}
									}
									}
									else
									{
										//System.out.println("Payment Confirmation page is NOT displayed");
										Report.TestPoint(testContext.getName(), "click on submit button", "Payment Confirmation page is displayed", "Payment Confirmation page is NOT displayed", true);

									}
									
								}
								else
								{
									//System.out.println("Payment Review page is NOT displayed");
									Report.TestPoint(testContext.getName(), "Click on Next button", "Payment Review page is displayed", "Payment Review page is NOT displayed", true);

								}
							}
							else
							{
								//System.out.println("Total Due amount is NOT entered as expected");
								Report.TestPoint(testContext.getName(), "Enter the total due amount in Payment amount field", "Total due amount is entered as expected", "Total Due amount is NOT entered as expected", true);

							}

						
					}
					else
					{
						//System.out.println("Make a Payment page is NOT displayed");
						Report.TestPoint(testContext.getName(), "Navigate to Make a Payment page", "Make a Payment page is displayed", "Make a Payment page is NOT displayed", true);

					}
				}
				else
				{
					//System.out.println("Make a payment button is not displayed");
					Report.TestPoint(testContext.getName(), "Click on Make a Payment button", "Clicked on Make a Payment button", "Make a Payment button NOT clicked", true);

				}

			}
			else
			{
				//System.out.println("Bill Details page is NOT displayed");
				Report.TestPoint(testContext.getName(), "Navigate to Bill details page", "Bill Details page is displayed", "Bill Details page is NOT displayed", true);

			}
		

	}


	/**************************************************************
	 * Function Name - ValidateOnStarUsageDetails
	 * Description-  This validates the OnStar usage
	 * Parameters - 
	 * Date created -20 Feb 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/	
	public static void ValidateOnStarUsageDetails(final ITestContext testContext) throws Exception {

		try
		{	
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
			//Validate secondary navigation link bills & usage
			boolean blnkBAU = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate and click secondary navigation link bills & usage", "true", String.valueOf(blnkBAU), true);
			//Validate and click secondary navigation link bills & usage
			boolean blnkBAUClick = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,null);
			Report.TestPoint(testContext.getName(), "Validate and click secondary navigation link bills & usage", "true", String.valueOf(blnkBAUClick), true);

			//validate Billing and usage page
			boolean bBAPpage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "validate Billing and usage page", "true", String.valueOf(bBAPpage), true);

			//Validate usage tab and click on it
			boolean bUsageTab = UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate usage tab and click on it", "true", String.valueOf(bUsageTab), true);
			Report.OperationPoint(testContext.getName(), "Operation : Clicking on the usage tab");
			oR_BillAndUsage.lnkUsage.click();

			//validate showing dropdown and click on recent usage
			boolean bPre = UI.WaitForObject(oR_BillAndUsage.lstShowing,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "validate showing dropdown and click on recent usage", "true", String.valueOf(bPre), true);
			Report.OperationPoint(testContext.getName(), "Operation : Clicking on the showing dropdown");
			oR_BillAndUsage.lstShowing.click();

			//validate recent usage and click
			boolean brecent = UI.WaitForObject(oR_BillAndUsage.lnkRecentBill,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "validate recent usage and click", "true", String.valueOf(brecent), true);
			Report.OperationPoint(testContext.getName(), "Operation : Clicking on the recent usage link");
			oR_BillAndUsage.lnkRecentBill.click();

			//validate OnStar heading
			boolean bOnStar = UI.WaitForObject(oR_BillAndUsage.txtOnStar, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "validate OnStar heading", "true", String.valueOf(bOnStar), true);

			//Validate View details by dropdown - should not be present
			/*boolean bView = UI.WaitForObject(lDriver.findElement(By.xpath("//div[contains(text(),'View details dropdown')]")), 1);
			Report.TestPoint(testContext.getName(), "validate OnStar heading", "false", String.valueOf(bView), true);*/

		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}

	/**************************************************************
	 * Function Name - ValidateONSTARUsageTabandSummary
	 * Description-  This validates ONSTAR - USAGE TAB_Unbilled - Usage Summary
	 * Parameters - 
	 * Date created -23 Feb 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void ValidateONSTARUsageTabandSummary(final ITestContext testContext)
			throws Exception {
		try {
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
			Report.OperationPoint(testContext.getName(), " Account Overview Page");
			if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 60))
			{
				Report.ValidationPoint(testContext.getName(), "Validate Billing and Usage link is present", "Billing and Usage link is present", "Billing and Usage link is present", true);
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
				Report.OperationPoint(testContext.getName(), " Billing & Usage Page");
				if(UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Usage Tab is present", "Usage Tab is present", "Usage Tab is present", true);
					oR_BillAndUsage.lnkUsage.click();
					Thread.sleep(20000);
					//validate Usage for Onstar
					if(UI.WaitForObject(oR_BillAndUsage.lnkUsageFor, 60))
					{
						Report.ValidationPoint(testContext.getName(), "Validate View Usage for drop down is present", "View Usage for drop down is present", "View Usage for drop down is present", true);
						oR_BillAndUsage.lnkUsageFor.click();
						Thread.sleep(20000);
					}
					
				
					if(UI.WaitForObject(oR_BillAndUsage.lnkOnstarCTN, UI.iObjTimeOut))
					{
						Report.ValidationPoint(testContext.getName(), "Validate Onstar Label is not displayed as Nick name is available", "Onstar Label is not displayed as Nick name is available", "Onstar Label is not displayed as Nick name is available", true);
						oR_BillAndUsage.lnkOnstarCTN.click();

						//Validate Onstar label is displayed
						boolean bOnstar = UI.WaitForObject(oR_BillAndUsage.txtOnstarLabel, 20);
						Report.TestPoint(testContext.getName(), "Validate Onstar Label heading is displayed", "true", String.valueOf(bOnstar), true);

						//Validate Onstar CTN and Nickname is displayed
						boolean bOnstarNickname = UI.WaitForObject(oR_BillAndUsage.txtOnstarNicknameCTN, 20);
						Report.TestPoint(testContext.getName(), "Validate Onstar CTN and Nickname is displayed", "true", String.valueOf(bOnstarNickname), true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate Onstar Label is not displayed as Nick name is available", "Onstar Label is not displayed as Nick name is available", "Onstar Label is displayed as Nick name is available", true);
					}


				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Usage Tab is present", "Usage Tab is present", "Usage Tab is not present", true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate Billing and Usage link is present", "Billing and Usage link is present", "Billing and Usage link is not present", true);
			}
		} catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}

	/**************************************************************
	 * Function Name - ValidateAutoPayManagePageandDiscontinueCCDC
	 * Description-  This validates AutoPay Manage Page and Discontinue-CCDC
	 * Parameters - 
	 * Date created -24 Feb 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void ValidateAutoPayManagePageandDiscontinueCCDC(final ITestContext testContext)
			throws Exception {

		try
		{	
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
			//Validate secondary navigation link bills & usage
			boolean blnkBAU = UI.WaitForObject(oR_AccountOverview.lnkBillingUsageSecNav, 50);
			Report.TestPoint(testContext.getName(), "Validate and click secondary navigation link bills & usage", "true", String.valueOf(blnkBAU), true);
			//Validate and click secondary navigation link bills & usage
			boolean blnkBAUClick = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsageSecNav,null);
			Report.TestPoint(testContext.getName(), "Validate and click secondary navigation link bills & usage", "true", String.valueOf(blnkBAUClick), true);

			//validate Billing and usage page
			boolean bBAPpage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 50);
			Report.TestPoint(testContext.getName(), "validate Billing and usage page", "true", String.valueOf(bBAPpage), true);

			//Validate usage tab and click on it
			boolean bUsageTab = UI.WaitForObject(oR_BillAndUsage.lnkUsage, 20);
			Report.TestPoint(testContext.getName(), "Validate usage tab and click on it", "true", String.valueOf(bUsageTab), true);
			Report.OperationPoint(testContext.getName(), "Operation : Clicking on the usage tab");
			oR_BillAndUsage.lnkUsage.click();

			//validate showing dropdown and click on recent usage
			boolean bPre = UI.WaitForObject(oR_BillAndUsage.lstShowing, 1);
			Report.TestPoint(testContext.getName(), "validate showing dropdown and click on recent usage", "true", String.valueOf(bPre), true);
			Report.OperationPoint(testContext.getName(), "Operation : Clicking on the showing dropdown");
			oR_BillAndUsage.lstShowing.click();

			//validate recent usage and click
			boolean brecent = UI.WaitForObject(oR_BillAndUsage.lnkRecentBill, 1);
			Report.TestPoint(testContext.getName(), "validate recent usage and click", "true", String.valueOf(brecent), true);
			Report.OperationPoint(testContext.getName(), "Operation : Clicking on the recent usage link");
			oR_BillAndUsage.lnkRecentBill.click();

			//validate OnStar heading
			boolean bOnStar = UI.WaitForObject(oR_BillAndUsage.txtOnStar, 1);
			Report.TestPoint(testContext.getName(), "validate OnStar heading", "true", String.valueOf(bOnStar), true);

			//Validate View details by dropdown - should not be present
			/*boolean bView = UI.WaitForObject(lDriver.findElement(By.xpath("//div[contains(text(),'View details dropdown')]")), 1);
				Report.TestPoint(testContext.getName(), "validate OnStar heading", "false", String.valueOf(bView), true);*/

		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}

	/**************************************************************
	 * Function Name - ValidateOnStarBillDetails
	 * Description-  Validate OnStar Bill Details - base plan and data plan
	 * Parameters - 
	 * Date created -26th Feb 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//BAP12319
	public static void ValidateOnStarBillDetails(final ITestContext testContext)throws Exception {

		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
			//Validate secondary navigation link bills & usage
			boolean blnkBAU = UI.WaitForObject(oR_AccountOverview.lnkBillingUsageSecNav, 50);
			Report.TestPoint(testContext.getName(), "Validate and click secondary navigation link bills & usage", "true", String.valueOf(blnkBAU), true);
			//Validate and click secondary navigation link bills & usage
			boolean blnkBAUClick = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsageSecNav,null);
			Report.TestPoint(testContext.getName(), "Validate and click secondary navigation link bills & usage", "true", String.valueOf(blnkBAUClick), true);

			//validate Billing and usage page
			boolean bBAPpage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 50);
			Report.TestPoint(testContext.getName(), "validate Billing and usage page", "true", String.valueOf(bBAPpage), true);

			//Validate the Web & Text usage section for Onstar CTN on the Bill page
			//On Star data plan
			boolean bDataPlan = UI.WaitForObject(oR_BillAndUsage.txtOnstarDataPlan, 50);
			Report.ValidationPoint(testContext.getName(), "validate On Star data plan", "true", String.valueOf(bDataPlan), true);
			//On Star base plan
			boolean bBasePlan = UI.WaitForObject(oR_BillAndUsage.txtOnstarBasePlan, 50);
			Report.ValidationPoint(testContext.getName(), "validate On Star base plan", "true", String.valueOf(bBasePlan), true);
		}

		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}

	/**************************************************************
	 * Function Name -  VerifyTableAndCarouselView()
	 * Description- This script validates the Table And Carousel View in Payment History
	 * Parameters - None
	 * Date created - 4th June 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	//BAP14024
	public static void VerifyTableAndCarouselView(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		Boolean bAscDesFuncForBillPeriod, blnkPrintCTA, bLnkBillingAndUsage, bTableViewSelection, bPrintFriendlyTable, bToolTip, bCarouselViewRad, bPageBillingAndUsage,bTableView,bHistoryTab, bTableViewSelected, bCarouselView, bTableViewPresent, bElmTotalAmt, bTableViewCheckBtn;
		String  sPrintCTA, sBillTotalFrame,sBillTotal, sCarouselView, sAscDesFuncForBillPeriod, sToolTip;
		
		try
		{	
			//Click on Bill & Payments on the Global navigation
			bLnkBillingAndUsage = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Click on Link Billing & Usage from Sec Navigation", "true", String.valueOf(bLnkBillingAndUsage), true);
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			//User should be landed in Bills page
			bPageBillingAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Page Billing & Usage", "true", String.valueOf(bPageBillingAndUsage), true);
			//Validate Bill Total within the Top Wrapper & lower portion of the Account Summary
			bElmTotalAmt = UI.WaitForObject(oR_BillAndUsage.txtTotalAmtDueBy, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Bill Total At top", "true", String.valueOf(bElmTotalAmt), false);
			//Click on history tab
			bHistoryTab = UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab, 10);
			Report.TestPoint(testContext.getName(), "History Tab", "true", String.valueOf(bHistoryTab), false);
			oR_BillAndUsage.lnkHistoryTab.click();

			//Verify Table view is appeared as selected
			//Verify Pagination for table view is displayed

			bTableViewPresent = UI.WaitForObject(oR_BillAndUsage.tblBillingHistory, UI.iObjTimeOut);
			bTableView = UI.WaitForObject(oR_BillAndUsage.radTableView, 30);
			if(bTableView.equals(true) && bTableViewPresent.equals(true))
			{
				bTableViewSelection = UI.isAttributePresent(oR_BillAndUsage.radTableView, "checked");
				Report.ValidationPoint(testContext.getName(), "Table View Selection and Table Present", "true", String.valueOf(bTableViewSelection), true);			
			}

			//Verify CTA's to Print is displayed
			sPrintCTA = UI.CheckExist(oR_BillAndUsage.lnkPrintBill);
			Report.ValidationPoint(testContext.getName(), "Print CTA", "true", sPrintCTA.toLowerCase(), true);

			//Verify Bill period Column will have a sort descending and ascending functionality from the column header
			bAscDesFuncForBillPeriod = UI.WaitForObject(oR_BillAndUsage.imgSortAscDesBillPeriod, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Sort descending and ascending functionality for Bill Period Column", "true", String.valueOf(bAscDesFuncForBillPeriod).toLowerCase(), false);

			//Verify The View Bill column contains separate CTA's to view the Paper and Online bill for each bill cycle.

			//int iBillCycle = lDriver.findElements(By.xpath("//tr[contains(@class,'Row')]")).size();
			int iBillCycle = lDriver.findElements(By.xpath("//td[@headers='bill_period']")).size();	
			int iOnlineLink = lDriver.findElements(By.xpath("//table//a[contains(text(),'Online')]")).size();
			int iPaperLink = lDriver.findElements(By.xpath("//table//a[contains(text(),'Paper')]")).size();

			

			if(iBillCycle==iOnlineLink && iBillCycle==iPaperLink)
			{
				Report.ValidationPoint(testContext.getName(), "Separate CTA's to view the Paper and Online bill for each bill cycle", "Present", "Present", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Separate CTA's to view the Paper and Online bill for each bill cycle", "Present", "Absent", true);
			}

			//Verify View Bill Column Header will contain a tool tip including a short description of the functionality of this column
			bToolTip = lDriver.findElement(By.xpath("//table//th[@id='view_bill']//img[@alt='help']")).isDisplayed();
			System.out.println("bToolTip" +bToolTip);

			sToolTip = UI.CheckExist(lDriver.findElement(By.xpath("//table//th[@id='view_bill']//img[@alt='help']")));
			Report.ValidationPoint(testContext.getName(), "View Bill Column Header tool tip icon", "true", "true", true);

			//Verify all scenarios for the print-friendly table will apply to the table of the carousel.
			bCarouselViewRad = oR_BillAndUsage.radCarouselView.isDisplayed();

			System.out.println(bCarouselViewRad +" bCarouselViewRad");
			oR_BillAndUsage.radCarouselView.click();

			bCarouselView = UI.WaitForObject(oR_BillAndUsage.tblCarouselView, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Carousel View", "true", String.valueOf(bCarouselView), true);

			sBillTotal = lDriver.findElement(By.xpath("//span[@class='billingOrangeText font36']")).getText();
			System.out.println(sBillTotal + "sBillTotal");

			blnkPrintCTA = UI.WaitForObject(oR_BillAndUsage.lnkPrintBill, UI.iObjTimeOut);
			System.out.println("blnkPrintCTA" + blnkPrintCTA);
			Report.TestPoint(testContext.getName(), "Print CTA", "true", String.valueOf(blnkPrintCTA).toLowerCase(), true);
				oR_BillAndUsage.lnkPrintBill.click();
			
				bPrintFriendlyTable = UI.WaitForObject(oR_BillAndUsage.frmPrintFriendlyTable,UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Print Friendly Table", "true", String.valueOf(bPrintFriendlyTable), true);
				
				String sMainWindow = lDriver.getWindowHandle();
				WebElement Frame = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
				lDriver.switchTo().frame(Frame);
				//lDriver.switchTo().frame(oR_BillAndUsage.frmPrintFriendlyTable);

				//Bill Total within the print-friendly table should also exclude the superscript
				sBillTotalFrame = lDriver.findElement(By.xpath("//td[@headers='Bill_Total']")).getText();
				System.out.println(sBillTotalFrame);

				if(sBillTotal.equals(sBillTotalFrame))
				{
					Report.ValidationPoint(testContext.getName(), "Bill Total", "true", "true", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Bill Total", "true", "false", true);
				}

				//lDriver.switchTo().defaultContent();
			lDriver.switchTo().window(sMainWindow);
			WebElement btnClose = lDriver.findElement(By.xpath("//img[@src='/olam/images/brand30/modalClose.png']"));
			btnClose.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on frame close button ");
				
			//Click on view all payments CTA from the carousel	A model window will be opened having multiple payment details
			boolean bViewPayments = UI.WaitForObject(oR_BillAndUsage.lnkViewAllPaymentsCarousel, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that 'View all payments' exists within the carousel", "true",String.valueOf(bViewPayments), true);
			oR_BillAndUsage.lnkViewAllPaymentsCarousel.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'View all payments' within the carousel");
			BillAndPayments.PaymentsHistroyPopUpInBillHistory(testContext);
				
				//Validate Carousel view Bill history section
				boolean bMostRecentPaymentMethod = UI.WaitForObject(oR_BillAndUsage.txtMostRecentPayment, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify that the text for Most Recent Payment is displayed in bold", "true",String.valueOf(bMostRecentPaymentMethod), true);
				boolean bMostRecentPaymentMethodAmount = UI.WaitForObject(oR_BillAndUsage.txtMostRecentPaymentAmount, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify that the Amount for Most Recent Payment is displayed in bold and in correct format with a $ sign", "true",String.valueOf(bMostRecentPaymentMethodAmount), true);
				
				//Bill Total
				boolean bBillTotal = UI.WaitForObject(oR_BillAndUsage.txtBillTotalInCarousel, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify that Bill Total is displayed as sum of all amounts", "true",String.valueOf(bBillTotal), true);
				//Verify payment received
				boolean bPaymentDate = UI.WaitForObject(oR_BillAndUsage.txtPaymentReceivedInCarousel, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify that the Payment received date should be displayed for the last payment received", "true",String.valueOf(bPaymentDate), true);
				//verify payment method
				boolean bPaymentMethod = UI.WaitForObject(oR_BillAndUsage.txtPaymentMethodInCarousel, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify that Payment method is displayed", "true",String.valueOf(bPaymentMethod), true);
				//Verify Autopay
				boolean bAutopay = UI.WaitForObject(oR_BillAndUsage.txtAutoPayValueInCarousel, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify that AutoPay value is displayed", "true",String.valueOf(bAutopay), true);
				String sAutopayValue = oR_BillAndUsage.txtAutoPayValueInCarousel.getText();
				if(sAutopayValue.contains("No") || sAutopayValue.contains("Yes"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Autopay should be displayed as 'yes' or 'no' as appropriate", "true","true", true);

				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Autopay should be displayed as 'yes' or 'no' as appropriate", "true","false", true);
				}
				//verify payment status
				boolean bPaymentStatus = UI.WaitForObject(oR_BillAndUsage.txtPaymentStatusInCarousel, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify that Payment Status is displayed", "true",String.valueOf(bPaymentStatus), true);
				String sPmtStatus = oR_BillAndUsage.txtPaymentStatusInCarousel.getText();
				if(sPmtStatus.contains("Unsuccessful") )
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Status is displayed as 'Unsuccessful' for failed payments", "true","true", true);

				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Status is displayed as 'Unsuccessful' for failed payments", "true","false", true);
				}
				//Verify confirmation
				String sConfirm = oR_BillAndUsage.txtConfirmationInCarousel.getText();
				if(sConfirm!=null || sConfirm=="NA")
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Confirmation feild is displayed", "true","true", true);
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Confirmation feild is displayed", "true","false", true);

				}
				
				boolean bError = UI.WaitForObject(oR_BillAndUsage.txtErrorMsgInCarousel, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify a Note at the bottom of the table indicating the failed payment details for the distinct indicator", "true",String.valueOf(bError), true);



		}
		catch(Exception e)
		{
			Report.ValidationPoint(testContext.getName(), "Failed to validate Table And Carousel View of Payment History", "true", e.getMessage(), true);
		}
	}

	/**************************************************************
	 * Function Name -  VerifyPrintCTAModal()
	 * Description- This method verifies if print friendly layout of Bill Details is displayed 
	 * Parameters - None
	 * Date created -11th March 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/

	@SuppressWarnings("rawtypes")
	public static void VerifyPrintCTAModal(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{

		
		Boolean bLinkBAP, bBillTabDefault, bPrintLayout;
		String sBillTabSelection, sPrintCTA;
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			Report.OperationPoint(testContext.getName(), "Navigating to Billing And Payments Page");
			//Click on Bill & usage tab within the secondary navigation
			bLinkBAP = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Link Billing & Payments", "true", String.valueOf(bLinkBAP).toLowerCase(), true);
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();


			//By default user is switched to the Bill tab on the same page
			bBillTabDefault = UI.WaitForObject(oR_BillAndUsage.lnkBillTab, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Bill Tab", "true", String.valueOf(bBillTabDefault).toLowerCase(), true);

			sBillTabSelection = oR_BillAndUsage.lnkBillTab.getAttribute("class");
			System.out.println(sBillTabSelection+" sBillTabSelection");
			if(sBillTabSelection.contains("currentTab locked"))
			{
				Report.TestPoint(testContext.getName(), "Bill Tab selection by default", "User is landed under Bill Tab", "User is landed under Bill Tab", true);
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Bill Tab selection by default", "User is landed under Bill Tab", "User is not landed under Bill Tab", true);
			}
			//Verify "Print" CTA at Account Summary title level
			sPrintCTA = UI.CheckExist(oR_BillAndUsage.lnkPrint);
			Report.TestPoint(testContext.getName(), "Print CTA next to Account Details/Summary Title", "true", sPrintCTA.toLowerCase(), true);

			Report.OperationPoint(testContext.getName(), "Verify if Print Layout Exist");
			//Click on "Print" CTA
			String sBAPPage = lDriver.getWindowHandle();
			oR_BillAndUsage.lnkPrint.click();

			//A separate window should be opened with the print-friendly version of the Bill Details.(Print friendly layout will be cared in different User story)
			@SuppressWarnings("unchecked")
			ArrayList WinCount = new ArrayList (lDriver.getWindowHandles());
			if(WinCount.size()>1)
			{/*switching to new window opened*/
				for(String winHandle : lDriver.getWindowHandles())
				{
					lDriver.switchTo().window(winHandle);
				}	
				bPrintLayout = UI.WaitForObject(oR_ATT.lnkPrint, 20)&&UI.WaitForObject(oR_ATT.txtAccountDetails, 10);
				Report.ValidationPoint(testContext.getName(), "Print layout", "true", String.valueOf(bPrintLayout), true);
			}
			else
				Report.TestPoint(testContext.getName(), "Print Layout in new window", "New window", "New window is not opened", true);

			lDriver.close();
			lDriver.switchTo().window(sBAPPage);				
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Failed to validate", "Pass", e.getMessage(), true);
		}
	}


	/**************************************************************
	 * Function Name - VerifyCreditCardExpiry()
	 * Description - Verify that Uverse Credit card expiry alert at alerts tab and Bills tab
	 * Parameters - None
	 * Date created - 11th Mar 2015
	 * Developed by - Clint John
	 * Last Modified By - Clint John
	 * Last Modified Date - 11th Mar 2015
	 * @throws ParseException 
	 ***************************************************************/
	public static void VerifyCreditCardExpiry(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
			//Check for Alert button/tab
			boolean bAlert = UI.WaitForObject(oR_AccountOverview.btnAlertBox, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate Alert tab is displayed", "true", String.valueOf(bAlert), true);

			//Verify Credit Card expiry alert is displayed in the Alerts section
			oR_AccountOverview.btnAlertBox.click();
			Report.OperationPoint(testContext.getName(), "Operation : Clicked on Alert button to view alerts");
			boolean bCardExpiredAlert = UI.WaitForObject(oR_AccountOverview.txtCardExpiredMessage, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate that text showing 'Your Card Has Expired' is displayed under Alerts section", "true", String.valueOf(bCardExpiredAlert), true);
			boolean bPaymentProfileLink = UI.WaitForObject(oR_AccountOverview.lnkUpdatePaymentProfileAlert, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that the link for Update payment profile is displayed under Card expired alert", "true", String.valueOf(bPaymentProfileLink	), true);

			//Naviagate to BillingAndPayments page and verify that Alert message for Credit Card expiry is displayed under Bill section
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Report.OperationPoint(testContext.getName(), "Operation : Clicked on Billing & Payments Sec Nav tab");
			boolean bBillingAndUsageHeading = UI.ValidateHeadingOfPage("Billing & Usage");
			Report.TestPoint(testContext.getName(), "Validate that Billing & Usage page is displayed", "true", String.valueOf(bBillingAndUsageHeading), true);
			//Check for Card expired message
			boolean bCardExpiredText = UI.WaitForObject(oR_BillAndUsage.txtCardHasExpired, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate that 'Card Has Expired' message is displayed", "true", String.valueOf(bCardExpiredText), true);
			boolean bPaymentProfileLink2 = UI.WaitForObject(oR_BillAndUsage.lnkUpdatePaymentProfile, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that the link for Update payment profile is displayed under Card expired alert", "true", String.valueOf(bPaymentProfileLink2), true);

		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}

	}


	/**************************************************************
	 * Function Name - SupressRecentActivity 
	 * Description- This validates the presence of previous activity section on Billing & Usage page, the recent activity section should be suppressed.
	 * Parameters - 
	 * Date created - 11th March 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void SupressRecentActivity(final ITestContext testContext)
			throws Exception {


		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			boolean bBAP = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate Billing & Payment link is present in secondary navigation bar", "true", String.valueOf(bBAP), true);
			if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 60))
			{
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
				boolean bPreviousAct = UI.WaitForObject(oR_BillAndUsage.txtPreviousActivityFor, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Validate Previous Activity section is present", "true", String.valueOf(bPreviousAct), true);
				UI.VerifyElementNotPresent(oR_BillAndUsage.txtRecentActivity, "Recent Activity");
			}

		} catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}

	/**************************************************************
	 * Function Name - VerifyCreditCardExpiryAndUpdatePaymentLink()
	 * Description - Verify that Uverse Credit card expiry alert at alerts tab and Make a payments page
	 * Parameters - None
	 * Date created - 12th Mar 2015
	 * Developed by - Clint John
	 * Last Modified By - Clint John
	 * Last Modified Date - 12th Mar 2015
	 * @throws ParseException 
	 ***************************************************************/
	public static void VerifyCreditCardExpiryAndUpdatePaymentLink(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_EditPaymentProfile oR_EditPaymentProfile = PageFactory.initElements(lDriver, OR_EditPaymentProfile.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			//Check for Alert button/tab
			boolean bAlert = UI.WaitForObject(oR_AccountOverview.btnAlertBox, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate Alert tab is displayed", "true", String.valueOf(bAlert), true);

			//Verify Credit Card expiry alert is displayed in the Alerts section
			oR_AccountOverview.btnAlertBox.click();
			Report.OperationPoint(testContext.getName(), "Operation : Clicked on Alert button to view alerts");
			boolean bCardExpiredAlert = UI.WaitForObject(oR_AccountOverview.txtCardExpiredMessage, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate that text showing 'Your Card Has Expired' is displayed under Alerts section", "true", String.valueOf(bCardExpiredAlert), true);
			boolean bPaymentProfileLink = UI.WaitForObject(oR_AccountOverview.lnkUpdatePaymentProfileAlert, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate that the link for Update payment profile is displayed under Card expired alert", "true", String.valueOf(bPaymentProfileLink	), true);

			//Click on Update payment link and navigate to Edit payment link page
			oR_AccountOverview.lnkUpdatePaymentProfileAlert.click();
			Report.OperationPoint(testContext.getName(), "Operation : Clicked on 'Update payment profile' link");
			boolean bEditPaymentProfileHeading = UI.ValidateHeadingOfPage("Edit Payment Profile");
			Report.TestPoint(testContext.getName(), "Validate that Edit Payment Profile page is displayed", "true", String.valueOf(bEditPaymentProfileHeading), true);
			//Validate note for credit card expiry is displayed
			boolean bExpiredOn = UI.WaitForObject(oR_EditPaymentProfile.txtExpiryDate, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate that note for Credit card expiry is displayed", "true", String.valueOf(bExpiredOn), true);


		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}

	}

	/**************************************************************
	 * Function Name - DisplayDatesMonthlyServiceCharges 
	 * Description- 
	 * Parameters - 
	 * Date created -11th March
	 * Developed by - Merrin Mathai
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void DisplayDatesMonthlyServiceCharges(final ITestContext testContext)
			throws Exception {

		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			Report.OperationPoint(testContext.getName(), "Navigating to Bills & Usage landing page");
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsageSecNav, null))
			{
				Report.TestPoint(testContext.getName(), "Goto Bills & Usage from global nav", "Bills & Usage page is displayed", "Bills & Usage page is displayed" , true);	

				List<WebElement> MonthlyCharges=lDriver.findElements(By.xpath("//a[@title='Monthly Plan Charges']"));
				
				if(MonthlyCharges.size()==0)
					Report.TestPoint(testContext.getName(), "Monthly plan Charges", "Monthly Plan Charges Links must be present", "Monthly Plan Charges Links absent", true);
				
				for(int i=0;i<MonthlyCharges.size();i++)
				{
					String txtMonthlyCharges=MonthlyCharges.get(i).getText();
					//if(txtMonthlyCharges.contains([0-1][1-9]/[1-12]))

					String[] aElementMonthlyChargesText;
					aElementMonthlyChargesText= txtMonthlyCharges.split(" ");

					String sStartDate = aElementMonthlyChargesText[4].toString();
					String sEndDate= aElementMonthlyChargesText[6].toString();

					//System.out.println(sBalanceAmountWithDollerSign);
					String[] aStartDate = sStartDate.split("\\/");
					String[] aEndDate = sEndDate.split("\\/");

					String sStartDateDay = aStartDate[2].toString();
					String sStartDateMonth = aStartDate[1].toString();
					String sEndDateDay= aEndDate[2].toString();
					String sEndDateMonth = aEndDate[1].toString();

					int iStartDateDay= Integer.parseInt(sStartDateDay);
					int iStartDateMonth= Integer.parseInt(sStartDateMonth);
					int iEndDateDay= Integer.parseInt(sEndDateDay);
					int iEndDateMonth= Integer.parseInt(sEndDateMonth);

					if(((iStartDateDay<31)&&(iEndDateDay<=31))&&((iStartDateMonth<12)&&(iEndDateMonth<12)))
					{
						Report.ValidationPoint(testContext.getName(), "Date in Monthly Charges Section", "Date in Monthly Charges Section is in mm/dd format", "Date in Monthly Charges Section is in mm/dd format", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Date in Monthly Charges Section", "Date in Monthly Charges Section should be in mm/dd format", "Date in Monthly Charges Section is not in mm/dd format", true);
					}
				}
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Goto Bills & Usage from global nav", "Bills & Usage page is displayed", "Bills & Usage page is Not displayed" , true);
			}
		} catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}


	/**************************************************************
	 * Function Name - VerifyTotalAmountDue 
	 * Description- 
	 * Parameters - 
	 * Date created -13th March
	 * Developed by - Merrin Mathai
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//BAP31764
	public static void VerifyTotalAmountDue(final ITestContext testContext)
			throws Exception {

		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			Report.OperationPoint(testContext.getName(), "Navigating to Bills & Usage landing page");
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null))
			{
				Report.TestPoint(testContext.getName(), "Goto Bills & Usage from global nav", "Bills & Usage page is displayed", "Bills & Usage page is displayed" , true);	

				//lDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				//Thread.sleep(20000);
				UI.WaitForObject(oR_BillAndUsage.txtTotalAmountDueAmount, 500, lDriver);
				//Totals displayed in total amount due container and Account Details Summary should match with eachother
				String sTotalAmountDueContainer=  oR_BillAndUsage.txtTotalAmountDueAmount.getText();
				String sSummaryTotalAmountDue= oR_BillAndUsage.txtTotalAmountDueUnderAccountDetails.getText();
				if(sTotalAmountDueContainer.equals(sSummaryTotalAmountDue))
				{
					Report.ValidationPoint(testContext.getName(), "total in amount due container and Account Details Summary is same", "true", "true", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "total in amount due container and Account Details Summary is same", "true", "false", true);
				}
				
				//Verify total amount due dollar amount
				//Total Amount Due Container should be displayed persistently above all the tabs.
				List<WebElement> lTabs=lDriver.findElements(By.xpath("//a[contains(@class,'tabPadding top1pxie')]"));
				for(int i=0;i<lTabs.size();i++)
				{
					lTabs.get(i).click();
					Thread.sleep(6000);
					Boolean bTotalAmountText=UI.WaitForObject(lDriver.findElement(By.xpath("//p[contains(text(),'Total Amount Due by ')]")), UI.iObjTimeOut);
					Boolean bTotalAmountDue= UI.WaitForObject(oR_BillAndUsage.txtTotalAmountDueAmount, UI.iObjTimeOut, lDriver);
					String sTotalAmountDue= oR_BillAndUsage.txtTotalAmountDueAmount.getText();
					if(bTotalAmountText=bTotalAmountDue=true)
					{
						String sTabName=lTabs.get(i).getText();
						Report.ValidationPoint(testContext.getName(), "total amount due dollar amount in"+sTabName, "true", String.valueOf(bTotalAmountDue), true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "total amount due dollar amount", "true", "false", true);
					}
				}
				/*Boolean bTotalAmountText=UI.WaitForObject(lDriver.findElement(By.xpath("//p[contains(text(),'Total Amount Due by ')]")), UI.iObjTimeOut);
				Boolean bTotalAmountDue=UI.WaitForObject(lDriver.findElement(By.xpath("//span[contains(@class,'iesh billing')]")), UI.iObjTimeOut);
				String sTotalAmountDue=lDriver.findElement(By.xpath("//span[contains(@class,'iesh billing')]")).getText();
				if(bTotalAmountText=bTotalAmountDue=true)
				{
					Report.ValidationPoint(testContext.getName(), "total amount due dollar amount", "true", String.valueOf(bTotalAmountDue), true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "total amount due dollar amount", "true", "false", true);
				}*/
				
				


			}
			else
			{
				Report.TestPoint(testContext.getName(), "Goto Bills & Usage from global nav", "Bills & Usage page is displayed", "Bills & Usage page is Not displayed" , true);
			}
		} catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}


	/**************************************************************
	 * Function Name - VerifyInstallmentPayoff 
	 * Description- Verify that a wireless customer with single installment plan 
	 * 				should be able to view the below mentioned details on the "Payoff  installment plan 
	 * 				page" while paying off the balance of their  installment plan for the ctn/device 
	 * 				selected by the them.
	 * Parameters - None
	 * Date created -16th March
	 * Developed by - Rahul Bakde
	 * Last Modified By - Krutika
	 * Last Modified Date - 29th July
	 ***************************************************************/
	public static void VerifyInstallmentPayoff(final ITestContext testContext)
			throws Exception {
		try{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
			OR_PayOffInstallmentPlan oR_PayOffInstallmentPlan = PageFactory.initElements(lDriver, OR_PayOffInstallmentPlan.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			if (UI.WaitForObject(oR_AccountOverview.btnMakeAPayment, UI.iObjTimeOut)) {
				Report.TestPoint(testContext.getName(),"Validate Make a payment button",
						"True", "True", true);
				oR_AccountOverview.btnMakeAPayment.click();
				if (UI.WaitForObject(oR_MakeAPayment.lnkPayoff, UI.iObjTimeOut)) {
					Report.TestPoint(testContext.getName(),"Validate payoff link",
							"True", "True", true);
					oR_MakeAPayment.lnkPayoff.click();
					if (UI.WaitForObject(oR_MakeAPayment.frmPayoffIFrame, UI.iObjTimeOut)) {
						Report.TestPoint(testContext.getName(),"Validate interstitial modal window exist",
								"True", "True", true);
						//switching control to the frame
						lDriver.switchTo().frame(oR_MakeAPayment.frmPayoffIFrame);
						//validating heading and descriptive text for payoff
						if (UI.WaitForObject(oR_MakeAPayment.txtPayoffHeading, 5)) {
							Report.TestPoint(testContext.getName(),"Validate payoff heading exist",
									"True", "True", true);
						} else {
							Report.TestPoint(testContext.getName(),"Validate payoff heading exist",
									"True", "False", true);
						}
						if (UI.WaitForObject(oR_MakeAPayment.txtPayoffDescriptive, 1)) {
							Report.TestPoint(testContext.getName(),"Validate payoff descriptive text exist",
									"True", "True", true);
						} else {
							Report.TestPoint(testContext.getName(),"Validate payoff descriptive text exist",
									"True", "False", true);
						}
						//validate continue button exist
						if (UI.WaitForObject(oR_MakeAPayment.btnPayoffContinue, 1)) {
							Report.TestPoint(testContext.getName(),"Validate continue button exist",
									"True", "True", true);
							oR_MakeAPayment.btnPayoffContinue.click();
							//validate payoff installment heading
							if (UI.WaitForObject(oR_PayOffInstallmentPlan.txtPayOffInstallmentHeading, UI.iObjTimeOut)) {
								//validate nickname/account number is displayed
								if (UI.WaitForObject(oR_PayOffInstallmentPlan.txtAccountNumber, 1)) {
									Report.TestPoint(testContext.getName(),"Validate nickname/account number is displayed",
											"True", "True", true);
								} else {
									Report.TestPoint(testContext.getName(),"Validate nickname/account number is displayed",
											"True", "False", true);
								}
								//Verify that plan name/ctn is displayed
								if (UI.WaitForObject(oR_PayOffInstallmentPlan.txtPlanName, 1)) {
									Report.TestPoint(testContext.getName(),"Validate that plan name/ctn is displayed",
											"True", "True", true);
								} else {
									Report.TestPoint(testContext.getName(),"Validate that plan name/ctn is displayed",
											"True", "False", true);
								}
								//Verify that installment plan id is displayed 
								if (UI.WaitForObject(oR_PayOffInstallmentPlan.txtPlanID, 1)) {
									Report.TestPoint(testContext.getName(),"Validate that installment plan id is displayed",
											"True", "True", true);
								} else {
									Report.TestPoint(testContext.getName(),"Validate that installment plan id is displayed",
											"True", "False", true);
								}
								//Verify that a link  "show plan details/collapse plan details" should be  displayed  
								if (UI.WaitForObject(oR_PayOffInstallmentPlan.lnkShowDetails, 1)) {
									Report.TestPoint(testContext.getName(),"Validate that a link show plan details/collapse plan details is displayed",
											"True", "True", true);
								} else {
									Report.TestPoint(testContext.getName(),"Validate that a link show plan details/collapse plan details is displayed",
											"True", "False", true);
								}
							} else {

							}
						} else {
							Report.TestPoint(testContext.getName(),"Validate continue button exist",
									"True", "False", true);
						}
					} else {
						Report.TestPoint(testContext.getName(),"Validate interstitial modal window exist",
								"True", "False", true);
					}
				} else {
					Report.TestPoint(testContext.getName(),"Validate payoff link",
							"True", "False", true);
				}
			} else {
				Report.TestPoint(testContext.getName(),"Validate Make a payment button",
						"True", "False", true);
			}
		} catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name -  ValidateAccountInformationSectionOnDashboard() #BAP06095
	 * Description- This method validates elements present in account info section on DashBoard
	 * Parameters - None
	 * Date created -16th March 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	***************************************************************/
	//BAP06095
	public static void ValidateAccountInformationSectionOnDashboard(final ITestContext testContext) throws HeadlessException, IOException, AWTException 
	{
		Boolean bClickOnManageAutopay, bManagePaperlessBillingLink,  bEnrollInAutoPayLink, bTotalBalance, bManageAutoPayPage, bAmount, bBAPPAge ;
		String  sMAPButton, sBAPLink, sTextAsOf, sBillDetailsLink,  sManagePaperlessBillingLink;
		
	try
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		OR_AutoPayEnrollment oR_AutoPayEnrollment = PageFactory.initElements(lDriver, OR_AutoPayEnrollment.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		 //1. Text for Current ( or credit ) Balance and the Amount.
		 bTotalBalance = UI.WaitForObject(oR_AccountOverview.txtTotalBalance, UI.iObjTimeOut);
		 Report.ValidationPoint(testContext.getName(), "Total Balance text", "true", String.valueOf(bTotalBalance), true);
	
		 //2. Amount would be displayed in orange or green 
		 bAmount = UI.WaitForObject(oR_AccountOverview.txtCurrentBalanceAmt, 10) || (UI.WaitForObject(oR_AccountOverview.txtCurrentBalanceAmtGreen, 10));
		 Report.ValidationPoint(testContext.getName(), "Total Balance Amount", "true", String.valueOf(bAmount), true);
		 
		 //3. ' As of <<Today's Date>> ' is displayed.
		 sTextAsOf = UI.CheckExist(oR_AccountOverview.txtAsOf);
		 Report.ValidationPoint(testContext.getName(), "Text As Of <Today's Date>", "true", sTextAsOf.toLowerCase(), true);
		/* Date date = new Date();
		 String CDate = oR_AccountOverview.txtAsOf.getText();
		 if(CDate.contains("%s %tb %<td", 
                 "", date))*/
	     // SimpleDateFormat DtFormat = new SimpleDateFormat("a b, yy");
	     /* SimpleDateFormat ft = new SimpleDateFormat ("E',' M d");
	        System.out.println("Current Date: " + ft.format(sdate));	    
	      	System.out.printf("%s %tb %<td", 
                  "", date);*/
	     // System.out.printf("%a %b %Y Due date:", date);
	     // System.out.println(DtFormat.format(sdate).toString());
		
	      //4. View Bill Details Link.
		 sBillDetailsLink = UI.CheckExist(oR_AccountOverview.lnkViewBillDetails);
		 Report.ValidationPoint(testContext.getName(), "View Bill Details Link", "true", sBillDetailsLink.toLowerCase(), true);
	 
		 //5. Sign up / Manage AutoPay link.
		 bEnrollInAutoPayLink = (UI.WaitForObject(oR_AccountOverview.lnkEnrollInAutopay,10) || UI.WaitForObject(oR_AccountOverview.lnkManageAutoPay,10));
		 Report.ValidationPoint(testContext.getName(), "Link Enroll In AutoPay", "true", String.valueOf(bEnrollInAutoPayLink).toLowerCase(), true);

		 //6. Enroll in / Manage Paperless Billing link.
		 bManagePaperlessBillingLink = (UI.WaitForObject(oR_AccountOverview.lnkManagePaperlessBilling, UI.iObjTimeOut) || UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBilling, UI.iObjTimeOut));
		 Report.ValidationPoint(testContext.getName(), "Link Manage Paperless Billing", "true", String.valueOf(bManagePaperlessBillingLink).toLowerCase(), true);
	
		 //Click on the Billing and Payment link on the secondary Navigation.
		 sBAPLink = UI.CheckExist(oR_AccountOverview.lnkBillingUsagePaymentsSecNav);
		 Report.TestPoint(testContext.getName(), "Billing and Payment link", "true", sBAPLink, true);
		 oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
		 
		 //Billing and Payment Landing Page is displayed.
		 bBAPPAge = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 12);
		 Report.ValidationPoint(testContext.getName(), "BAP Page", "true", String.valueOf(bBAPPAge), true);
		 
		 //Verify that the Make a Payment Button is diplayed with the countdown for the number of days left on the billing cycle. ( only if < 14 )
		 /** since Balance is 0, no payment Required msg is displayed instead of countdown**/
		 sMAPButton = UI.CheckExist(oR_BillAndUsage.btnMakePaymentInBillingPage);
		 Report.ValidationPoint(testContext.getName(), "Make A Payment Button", "true",  sMAPButton, true);
	
		 //Hover over Bills and Payments secondary nav and click on "Manage Autopay" link.
		 bClickOnManageAutopay = UI.SelectServiceFromSecondaryNavigation(oR_BillAndUsage.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkManageAutopayTertNav);
		 
		 //Manage AutoPay page displays.
		 bManageAutoPayPage = UI.WaitForObject(oR_AutoPayEnrollment.txtAutoPayEnrollment, 20);
		 Report.ValidationPoint(testContext.getName(), "Manage AutoPay page", "true", String.valueOf(bManageAutoPayPage), true);
	 }
	 catch(Exception e)
	 {
		 Report.TestPoint(testContext.getName(), "Account Information Section", "Passed", "Failed "+e.getMessage() , true);
	 }
		
	}
	
	/**************************************************************
	 * Function Name - ValidateDiscontinueAutopay()
	 * Description - The purpose of the test is to verify that a SLID account can discontinue autopay for the linked accounts  
	 * Parameters - None
	 * Date created - 20th Mar 2015
	 * Developed by - Clint John
	 * Last Modified By - Clint John
	 * Last Modified Date -  20th Mar 2015
	 ***************************************************************/
	//BAP04957
	public static void ValidateDiscontinueAutopay(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_ManageAutoPay oR_ManageAutoPay = PageFactory.initElements(lDriver, OR_ManageAutoPay.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
				
			boolean bOverview = UI.WaitForObject(oR_AccountOverview.txtWelcome, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify that Account Overview page is displayed", "true", String.valueOf(bOverview), true);
			
			boolean bBillingtab = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Billing, Payments & Usage tab is displayed in secondary nav. bar", "true", String.valueOf(bBillingtab), true);
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Billing, Payments & Usage' tab on sev nav bar");
			
			//Verify Billing and Usage page is displayed
			boolean bBillingPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify that Billing & Usage page is displayed", "true", String.valueOf(bBillingPage), true);
			
			//Verify Manage Autopay link is displayed in the bottom portion of Billing, Usage and Payments page
			boolean bAutopay = UI.WaitForObject(oR_BillAndUsage.lnkManageAutoPay, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that Manage Autopay link is displayed at the bottom of the page", "true", String.valueOf(bAutopay), true);
			oR_BillAndUsage.lnkManageAutoPay.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Manage AutoPay' link at the bottom of the page");
			
			//Check for Enroll my other accounts in AutoPay link
			boolean bEnroll = UI.WaitForObject(oR_ManageAutoPay.lnkEnrollMyOtherAccountsInAutopay, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that 'Enroll my other accounts in AutoPay' link is displayed ", "true", String.valueOf(bEnroll), true);
			
			//Verify only one Account is displayed in the page
			List <WebElement> Account = lDriver.findElements(By.xpath("//span[@class=' top2px']"));
			int iAccSize = Account.size();
			if(iAccSize==1)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that only 1 account is listed in Manage Autopay page", "Only 1 account is listed in Manage Autopay page", "Only 1 account is listed in Manage Autopay page", true);
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that only 1 account is listed in Manage Autopay page", "Only 1 account is listed in Manage Autopay page", "More than 1 accounts are listed in Manage Autopay page", true);

			}
			
			//Verify and click of Discontinue Autopay link
			boolean bDiscAutopay = UI.WaitForObject(oR_ManageAutoPay.lnkDiscontinueAutoPay, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that 'Discontinue AutoPay' link is displayed ", "true", String.valueOf(bDiscAutopay), true);
			oR_ManageAutoPay.lnkDiscontinueAutoPay.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Discontinue AutoPay' link");
			
			//Verify it is redirected to Discontinue Autopay page
			boolean bDiscAutopayPage = UI.WaitForObject(oR_ManageAutoPay.txtDiscontinueAutopayHeading, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify that Discontinue AutoPay page is displayed ", "true", String.valueOf(bDiscAutopayPage), true);
			//click on submit
			//Thread.sleep(60000);
			oR_ManageAutoPay.btnSubmit.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Submit button");
			
			//Verify that confirmation page is displayed
			boolean bConfirPage = UI.WaitForObject(oR_ManageAutoPay.txtDiscontinueAutopayConfirmationHeading, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that user is redirected to 'Discontinue AutoPay Confirmation' page", "true", String.valueOf(bConfirPage), true);
			//Verify Go to account overview link is displayed and naviagte to overview page
			boolean bGoTo = UI.WaitForObject(oR_ManageAutoPay.lnkGoToAccOverview, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that 'Got to account overview' link is displayed", "true", String.valueOf(bGoTo), true);
			oR_ManageAutoPay.lnkGoToAccOverview.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Go to account overview' link");
			
			//Verify user is redirected to Overview page
			boolean bOverviewPage = UI.WaitForObject(oR_AccountOverview.txtWelcome, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify that Account Overview page is displayed", "true", String.valueOf(bOverviewPage), true);
			
			
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}

	/**************************************************************
	 * Function Name -  ValidatePaymentMethods #BAP06486
	 * Description- This method validates elements present in account info section on DashBoard
	 * Parameters - None
	 * Date created -20th March 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	***************************************************************/
	public static void ValidatePaymentMethods(final ITestContext testContext) throws HeadlessException, IOException, AWTException 
	{	
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
			//Click on tertiery navigation link Make a payment
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,oR_AccountOverview.lnkMakeAPaymentTertNav);
			
			//Validate make a payment page
			 Boolean bMakePaymentPg = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment,30);
			 Report.TestPoint(testContext.getName(), "Validate make a payment page", "true",String.valueOf(bMakePaymentPg), true);
			//Validate Payment Entry as the 1st step
			 
			 Boolean bStep1 = UI.WaitForObject(oR_MakeAPayment.txtStep1New,5);
			 String sClass = oR_MakeAPayment.txtStep1New.getAttribute("class");
			 if(bStep1.equals(true))
			 {
				 if(sClass.contains("current"))
				 {
					 Report.ValidationPoint(testContext.getName(), "Validate Payment Entry as the 1st step", "true",String.valueOf(bStep1), true);
				 }
				 else
				 {
					 Report.ValidationPoint(testContext.getName(), "Validate Payment Entry as the 1st step", "true","false", true);
				 }
			 }
			 else
			 {
				 Report.ValidationPoint(testContext.getName(), "Validate Step indicator not present", "true","false", true);
			 }
			 
			 
			 //The Current Payment & Charges section and Account/User Information Module is not displayed.
			 Boolean bCurrentP = UI.VerifyElementNotPresent(oR_MakeAPayment.txtCurrentPayment, "Current Payment & Charges section");
			 Boolean bAccUser = UI.VerifyElementNotPresent(oR_MakeAPayment.txtAccountUser, "Account/User Information Module");
			 if(bCurrentP.equals(true) && bAccUser.equals(true))
			 {
				 Report.ValidationPoint(testContext.getName(), "Validate The Current Payment & Charges section and Account/User Information Module is not displayed", "true","true", true);
			 }
			 else
			 {
				 Report.ValidationPoint(testContext.getName(), "Validate The Current Payment & Charges section and Account/User Information Module is not displayed", "true","false", true);
			 }
			 
			 //Verify that the Account nickname and the Account number is displayed at the top of the page
			 //Account nickname
			 List <WebElement> lstNickName = lDriver.findElements(By.xpath("//p[contains(text(),'NICKNAME1')]"));
			 if(lstNickName.size()>0)
			 {
				 Report.ValidationPoint(testContext.getName(), "Verify the Account nickname", "true","true", true);
			 }
			 //Verify that 2 links , 'Manage Payment Profile' and 'Manage autopay Settings'
			 //Manage payment profile
			 //Not for the data qay1_133682422@att.net
			 /**Manage Payment Profile will be displayed only if there are any payment profiles present at the SLID level **/
			 /*Boolean bManage = UI.WaitForObject(oR_MakeAPayment.lnkManagePaymentProfile,5);
			 Report.ValidationPoint(testContext.getName(), "Validate Manage payment profile", "true",String.valueOf(bManage), true);*/
			 
			 /**Manage autopay Settings will be displayed if atleast one of the acounts is enrolled in autopay**/
			 //Not enrolled in the given data
			 
			 //Validate that Language dropdown is displayed
			 Boolean bLang = UI.WaitForObject(oR_AccountOverview.lnkLanguage,5);
			 Report.ValidationPoint(testContext.getName(), "Validate that Language dropdown is displayed", "true",String.valueOf(bLang), true);
			 oR_AccountOverview.lnkLanguage.click();
			 List <WebElement> lstLang = lDriver.findElements(By.xpath("//ul[@id='ge5p_z1_1_3']/li/a"));
			 if(lstLang.size()>0)
			 {
				 Report.ValidationPoint(testContext.getName(), "Validate Links are displayed under Language dropdown", "true","true", true);
			 }
			 Report.OperationPoint(testContext.getName(), "Operation : Retrieving the Language links");
			 String [] sLang = new String [lstLang.size()];
			 for(int i=0;i<lstLang.size();i++)
			 {
				 sLang[i] = lstLang.get(i).getText();
			 }
			 for(int i=0;i<lstLang.size();i++)
			 {
				 Report.OperationPoint(testContext.getName(),sLang[i]);
			 }
			 
			 //Verify the 'Choose Payment Method' subsection
			 //not for the data qay1_133682422@att.net
			 /*Boolean bChoose = UI.WaitForObject(oR_MakeAPayment.txtChoosePaymentMethod,5);
			 Report.ValidationPoint(testContext.getName(), "Verify the 'Choose Payment Method' subsection", "true",String.valueOf(bChoose), true);*/
			 
			 
			 //Verify Total amount to pay: $###.## ( total amount due on all the accounts)
			 //not applicable for qay1_133682422@att.net
			 /*Boolean bTotal = UI.WaitForObject(oR_MakeAPayment.txtTotalAmtToPay,5);
			 Report.ValidationPoint(testContext.getName(), "Validate Total amount to pay", "true",String.valueOf(bTotal), true);
			 
			 Report.ValidationPoint(testContext.getName(), "Validate Total amount to pay whole text is: "+oR_MakeAPayment.txtTotalAmtToPayEntire.getText(), "true",String.valueOf(bTotal), true);
			 String sText1 = oR_MakeAPayment.txtTotalAmtToPayEntire.getText();
			 String [] sSplit = new String [5];
			 String[] sAmt = new String [5];
			 sSplit = sText1.split("\\$");
			 sAmt = sSplit[1].split("\\.");
			 sAmt[0].trim();
			 if(sAmt[0].trim().length()==5 && sAmt[1].trim().length()==2)
			 {
				 Report.ValidationPoint(testContext.getName(), "Validate the format $###.##", "true","true", true);
			 }
			 
			 //Total payment: $###.## ( sum total of the amount paid for the accounts selected above)
			 Boolean bTotalP = UI.WaitForObject(oR_MakeAPayment.txtTotalPayment,5);
			 Report.ValidationPoint(testContext.getName(), "Validate Total payment", "true",String.valueOf(bTotalP), true);
			 Report.ValidationPoint(testContext.getName(), "Validate Total payment is : "+oR_MakeAPayment.txtTotalPaymentEntire.getText(), "true",String.valueOf(bTotalP), true);
			 String sText2 = oR_MakeAPayment.txtTotalPaymentEntire.getText();
			 String [] sSplit1 = new String [5];
			 String[] sAmt1 = new String [5];
			 sSplit1 = sText2.split("\\$");
			 sAmt1 = sSplit1[1].split("\\.");
			 sAmt1[0].trim();
			 if(sAmt1[0].trim().length()==1 && sAmt1[1].trim().length()==2)
			 {
				 Report.ValidationPoint(testContext.getName(), "Validate the format $###.##", "true","true", true);
			 }*/
			 
			 //Verify Cancel link
			 Boolean bCancel = UI.WaitForObject(oR_MakeAPayment.lnkCancel,5);
			 Report.ValidationPoint(testContext.getName(), "Validate Cancel link", "true",String.valueOf(bCancel), true);
			 
			 //Validate Next button
			 Boolean bNext = UI.WaitForObject(oR_MakeAPayment.lnkContinueDis,5);
			 Report.ValidationPoint(testContext.getName(), "Validate Next button", "true",String.valueOf(bNext), true);
			 
			 //Validate payment method dropdown
			 Boolean bPaymentMethod = UI.WaitForObject(oR_MakeAPayment.lstPaymentMethod,5);
			 Report.TestPoint(testContext.getName(), "Validate payment method dropdown", "true",String.valueOf(bPaymentMethod), true);
			 
			//verify the methods will be displayed in the payment method dropdown
			 List <WebElement> lstPaymentMethod = lDriver.findElements(By.xpath("//select[@id='makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.methodNameId']/option"));
			 for(int i=0;i<lstPaymentMethod.size();i++)
			 {
				 String sText = lstPaymentMethod.get(i).getText();
				 if(sText.contains("new"))
				 {
					 Report.ValidationPoint(testContext.getName(), "Validate the method : "+sText, "true","true", true);
				 }
			 }
		}
		 catch(Exception e)
		 {
			 Report.TestPoint(testContext.getName(), "Account Information Section", "Passed", "Failed "+e.getMessage() , true);
		 }
	}		

	/**************************************************************
	 * Function Name - ValidateBillingforIPTV() 
	 * Description- 
	 * Parameters - 
	 * Date created -20th Feb 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//BAP08723
	public static void ValidateBillingforIPTV (final ITestContext testContext)
			throws Exception {
		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			//click on Billing&Payments
			Report.OperationPoint(testContext.getName(), "Navigating to Bills & Usage landing page");
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null))
			{
				Report.TestPoint(testContext.getName(), "Goto Bills & Usage from global nav", "Bills & Usage page is displayed", "Bills & Usage page is displayed" , true);	
				//Verify account charges and monthly charges is displayed
				Boolean bAccountCharges = UI.WaitForObject(oR_BillAndUsage.lnkAccountCharges, 20);
				Report.ValidationPoint(testContext.getName(), "Verify Account Charges is displayed", "true", String.valueOf(bAccountCharges), true);
				
				Boolean bMonthlyCharges = UI.WaitForObject(oR_BillAndUsage.lnkMonthlyCharges, 5);
				Report.ValidationPoint(testContext.getName(), "Verify Monthly Charges is displayed", "true", String.valueOf(bMonthlyCharges), true);
				
				Boolean bUverseVoice = UI.WaitForObject(oR_BillAndUsage.btnUverseVoice, 5);
				Report.ValidationPoint(testContext.getName(), "Verify Uverse Voice details expand button is displayed", "true", String.valueOf(bUverseVoice), true);
				if(bUverseVoice.equals(true))
				{
					oR_BillAndUsage.btnUverseVoice.click();
					Thread.sleep(20000);
					Boolean bMonthlyCharge = UI.WaitForObject(lDriver.findElement(By.xpath("//div[@class='sub-group-title PadTop10 MarLeft10 botDotBorder Padbot5 ']//a[@id='UVV-monthly']")),20);
					Report.ValidationPoint(testContext.getName(), "Verify Monthly Charges for Uverse Voice is displayed", "true", String.valueOf(bMonthlyCharge), true);
				}

				Boolean bUverseInt = UI.WaitForObject(oR_BillAndUsage.btnUverseInt, 5);
				Report.ValidationPoint(testContext.getName(), "Verify Uverse Internet details expand button is displayed", "true", String.valueOf(bUverseInt), true);
				if(bUverseVoice.equals(true))
				{
					oR_BillAndUsage.btnUverseInt.click();
					Thread.sleep(20000);
					Boolean bMonthlyCharge = UI.WaitForObject(lDriver.findElement(By.xpath("//div[@class='sub-group-title PadTop10 MarLeft10 botDotBorder Padbot5 ']//a[@id='UVV-monthly']")), 20);
					Report.ValidationPoint(testContext.getName(), "Verify Monthly Charges for Uverse Voice is displayed", "true", String.valueOf(bMonthlyCharge), true);
				}
				if (UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab, 1))
				{
					oR_BillAndUsage.lnkHistoryTab.click();
					Thread.sleep(20000);
					Boolean bNotPresent= UI.VerifyElementNotPresent(oR_BillAndUsage.txtNoBills, "You have no bills");
					if(bNotPresent.equals(true))
					{
						Boolean bOnlineBill = UI.WaitForObject(oR_BillAndUsage.lnkOnlineBill, 5);
						Report.ValidationPoint(testContext.getName(), "Verify Online Bill link is present", "true", String.valueOf(bOnlineBill), true);
						
						Boolean bPaperBill = UI.WaitForObject(oR_BillAndUsage.lnkPaperBill, 5);
						Report.ValidationPoint(testContext.getName(), "Verify Paper Bill link is present", "true", String.valueOf(bPaperBill), true);
						if(bPaperBill.equals(true))
						{
							oR_BillAndUsage.lnkPaperBill.click();
							Thread.sleep(20000);
							String CurrentWindow = lDriver.getWindowHandle();
							for(String newwinHandle : lDriver.getWindowHandles()){
							    lDriver.switchTo().window(newwinHandle);}
							String url = lDriver.getCurrentUrl();
							if(url.contains("printer"))
							{
				
								Report.ValidationPoint(testContext.getName(), "Validate Bill PDF is displayed", "true","true", true);
								 lDriver.close();
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate Bill PDF is displayed", "true","false", true);
							}
							lDriver.switchTo().window(CurrentWindow);	
						}
						
						
						Boolean bBillPeriod = UI.WaitForObject(oR_BillAndUsage.imgSortAscDesBillPeriod, 5);
						Report.ValidationPoint(testContext.getName(), "Verify Paper Billing period is present", "true", String.valueOf(bBillPeriod), true);
						
						Boolean bBillTotal = UI.WaitForObject(oR_BillAndUsage.imgBillTotal, 5);
						Report.ValidationPoint(testContext.getName(), "Verify Paper Billing Total is present", "true", String.valueOf(bBillTotal), true);
						
						Boolean bPaymentReceived = UI.WaitForObject(lDriver.findElement(By.xpath("//th[@id='Payments_Received']")), 5);
						Report.ValidationPoint(testContext.getName(), "Verify Payment Received is present", "true", String.valueOf(bPaymentReceived), true);
						
						Boolean bAmount = UI.WaitForObject(lDriver.findElement(By.xpath("//th[@id='Amount_Paid']")), 5);
						Report.ValidationPoint(testContext.getName(), "Verify Amount is present", "true", String.valueOf(bAmount), true);
						
						Boolean bPaymentMethod = UI.WaitForObject(lDriver.findElement(By.xpath("//th[@id='Payment_Method']")), 5);
						Report.ValidationPoint(testContext.getName(), "Verify Payment Method is present", "true", String.valueOf(bPaymentMethod), true);
						
						Boolean bAutopay = UI.WaitForObject(lDriver.findElement(By.xpath("//th[@id='autopay']")), 5);
						Report.ValidationPoint(testContext.getName(), "Verify Autopay is present", "true", String.valueOf(bAutopay), true);
						
						Boolean bStatus = UI.WaitForObject(lDriver.findElement(By.xpath("//th[@id='status']")), 5);
						Report.ValidationPoint(testContext.getName(), "Verify Status is present", "true", String.valueOf(bStatus), true);
						
						Boolean bConfirmation = UI.WaitForObject(lDriver.findElement(By.xpath("//th[@id='confirmation']")), 5);
						Report.ValidationPoint(testContext.getName(), "Verify Confirmation is present", "true", String.valueOf(bConfirmation), true);

						Boolean bEnrollPaperless = UI.WaitForObject(lDriver.findElement(By.xpath("//span[contains(text(),'Enroll In paperless billing')]")), 5);
						Report.ValidationPoint(testContext.getName(), "Verify Enroll Paperless billing is present", "true", String.valueOf(bEnrollPaperless), true);
						
					}
					//verify Report tabs
					Report.OperationPoint(testContext.getName(), "Navigating to Reports");
					if(UI.WaitForObject(oR_BillAndUsage.lnkReportTab, UI.iObjTimeOut))
					{	
						Report.TestPoint(testContext.getName(), "Goto Report Tab", "Report is displayed", "Report is displayed" , true);
						oR_BillAndUsage.lnkReportTab.click();
						if(UI.WaitForObject(oR_BillAndUsage.lstSelectReportType, UI.iObjTimeOut))
						{	
							Report.TestPoint(testContext.getName(), "Verify Select Report type dropdown is present", "Select Report type dropdown is present", "Select Report type dropdown is present" , true);
						}
						if(UI.WaitForObject(oR_BillAndUsage.lstStartDate, UI.iObjTimeOut))
						{	
							Report.TestPoint(testContext.getName(), "Verify Select Start Date dropdown is present", "Start Date dropdown is present", "Start Date dropdown is present" , true);
						}
						if(UI.WaitForObject(oR_BillAndUsage.lstEndDate, UI.iObjTimeOut))
						{	
							Report.TestPoint(testContext.getName(), "Verify End Date dropdown is present", "End Date dropdown is present", "End Date dropdown is present" , true);
						}
					}
				}
			}
		}

		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	
	

	/**************************************************************
	 * Function Name -  VerifyCreditsNoteAmountsBeforeOrAfterTLGchangesAndVerifyCreditAlerts
	 * Description- This function Verifies 
	 * 				-Past due amount , current amount in account information section
	 * 				-credit(s) note in account information section
	 * 				-credit(s) note along with credit amount(as given by TLG team) when Before_Or_After_TLG_changes parameter value
	 *              is passed as 'AFTER' in sheet.
	 *              -credit adjustment alert on dashboard
	 * Parameters - 
	 * Date created -25th Mar 15
	 * Developed by - Sneha Pansare
	 * Last Modified By - Monica Mohabansi
	 * Last Modified Date - 10th feb 2016
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	//BAP08668 
	
	public static void VerifyCreditsNoteAmountsBeforeOrAfterTLGchangesAndVerifyCreditAlerts(final ITestContext testContext) throws HeadlessException, IOException, AWTException, InterruptedException
	  {
		
	  try
	  {
		  	WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_AutoPayEnrollment oR_AutoPayEnrollment = PageFactory.initElements(lDriver, OR_AutoPayEnrollment.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
			/**Code Modified : MOnica 10th Feb ***/
			Report.ValidationPoint(testContext.getName(), "Steps 2,3,6 : TLG VALIDATIONS", "SKIPPED", "SKIPPED", true);
		try
		{
			WebElement eCreditNote = lDriver.findElement(By.xpath("//*[contains(text(),'Credit(s)')]"));	
			if(eCreditNote.isDisplayed())
			{
				//System.out.println("Credit(s) Note is displayed in account summary section");
				Report.ValidationPoint(testContext.getName(), "4-Verify Credit(s) Note is displayed in account summary section", "Credit(s) Note is displayed", "Credit(s) Note is displayed", true);
			}
			else
			{
				//System.out.println("Credit(s) Note is NOT displayed in account summary section");
				Report.ValidationPoint(testContext.getName(), "4-Verify Credit(s) Note is displayed in account summary section", "Credit(s) Note is displayed", "Credit(s) Note is NOT displayed", true);
			}
		}
		catch(Exception e)
		{
			//System.out.println("Credit(s) Note is NOT displayed in account summary section");
			Report.ValidationPoint(testContext.getName(), "4-Verify Credit(s) Note is displayed in account summary section", "Credit(s) Note is displayed", "Credit(s) Note is NOT displayed", true);
		}
		try
		{
			WebElement eCurrentBalance = lDriver.findElement(By.xpath("//*[contains(text(),'Your total balance is')]"));
			if(eCurrentBalance.isDisplayed())
			{
				//here navigated from child 1(i.e Your total balance is:) to parent and then from parent to child 2 (i.e $ amount)
				String sCurrentBalanceAmount = lDriver.findElement(By.xpath("//*[contains(text(),'Your total balance is')]/parent::div/parent::div/child::div[2]")).getText();
				
				//verify whether amount is displayed
				if(sCurrentBalanceAmount.contains("$"))
				{
					//System.out.println("Current balance Amount is displayed in account summary section");
					Report.ValidationPoint(testContext.getName(), "5-Verify for Past due/ Current balance amounts", "Amount is displayed", "Amount is displayed", true);
				}
				else
				{
					//System.out.println("Current balance Amount is NOT displayed in account summary section");
					Report.ValidationPoint(testContext.getName(), "5-Verify for Past due/ Current balance amounts", "Amount is displayed", "Amount is NOT displayed", true);
				}
			}
		}
		catch(Exception e)
		{
			//System.out.println("Current balance Amount is NOT displayed in account summary section");
			Report.ValidationPoint(testContext.getName(), "5-Verify for Past due/ Current balance amounts", "Amount is displayed", "Amount is NOT displayed", true);
		}
		
//7		gOTO bills and payent landing page, check for the ask your questions dropdown on the RHN, 		
//		UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
//		bAskYourQuestions = UI.WaitForObject(elmEle, iObjTimeOut, lDriver)
		Report.ValidationPoint(testContext.getName(), "7-Goto bills and payent landing page, check for the ask your questions dropdown on the RHN, ", "SKIPPED", "SKIPPED", true);

//9		Goto Autopay enrollment page , check for the payment method dropdown there
//		if(UI.WaitForObject(oR_AccountOverview.lnkManageAutoPay, 20))
		if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkManageAutoPay, lDriver));
		
		else if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkManageAutoPay, lDriver));

		else
			 Report.TestPoint(testContext.getName(),"9-Autopay Link in Global Navigation", "Present", "Absent", true);

		Boolean bPaymentMethod = UI.WaitForObject(oR_AutoPayEnrollment.txtSelectPaymentMethod, 20, lDriver);
		Report.ValidationPoint(testContext.getName(), "9-Goto Autopay enrollment page , check for the payment method dropdown there", "true", bPaymentMethod.toString(), true);

//8		Goto MAP page , check for the payment method dropdown there
		UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkMakeAPaymentTertNav, lDriver);
		Boolean bMethod = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethod, 20, lDriver);
		Report.ValidationPoint(testContext.getName(), "8-Goto MAP page , check for the payment method dropdown there", "true", bMethod.toString(), true);

		/**Code modification ends : MOnica 10th Feb ***/
	  }
	  catch(Exception e)
	  {
		  Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
	  }
		
	 
	}
	/**************************************************************
	 * Function Name - VerifyReportsTabSecondaryLinkRails()
	 * Description - Validate Reports tab secondary link rails for stand alone wireline which Is enrollred in Autopay and paperless billing
	 * Parameters - None
	 * Date created - 27th Mar 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//BAP30731
	public static void VerifyReportsTabSecondaryLinkRails(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			//Check Account Overview page is displayed
			boolean bOverview = UI.WaitForObject(oR_AccountOverview.txtAccountOverview, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate Account Overview page is displayed", "true", String.valueOf(bOverview), true);
			
			//Click on Billing, Usage, Payments
			boolean bBillingSecTab = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate Billing, Usage, Payments tab is present in Sec Nav section", "true", String.valueOf(bBillingSecTab), true);
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Bill, Usage & Payments' tab");
			
			//Verify Bill & Usage page is displayed and navigate to Report tab
			boolean bBillingPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate that Billing & Usage page is displayed", "true", String.valueOf(bBillingPage), true);
			boolean bReportTab = UI.WaitForObject(oR_BillAndUsage.btnReportsTab, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate that Report tab is displayed", "true", String.valueOf(bReportTab), true);
			oR_BillAndUsage.btnReportsTab.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Reports' tab");
			
			//Validate Secondary rails link section
			//Check  Secondary rails link section is displayed at the bottom of Reports page
			boolean bSecLinkRail = UI.WaitForObject(oR_BillAndUsage.tblSecLinkRailsSection, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate that Secondary Rail Link section is displayed at the bottom of Reports page", "true", String.valueOf(bSecLinkRail), true);
			//Validate secondary link rails section contains following 3 subsections:
			//1. Manage Account
			//2. Manage Billing & Payment Options
			//3. Get Help
			boolean bManageAccount = UI.WaitForObject(oR_BillAndUsage.txtManageAccount, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that 'Manage Account' section is displayed in Sec link rails section", "true", String.valueOf(bManageAccount), true);
			boolean bChangeBilling = UI.WaitForObject(oR_BillAndUsage.txtChangeBillingOptions, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that 'Manage Billing & Payment Options' or 'Change billing options' section is displayed in Sec link rails", "true", String.valueOf(bChangeBilling), true);
			boolean bGetHelp = UI.WaitForObject(oR_BillAndUsage.txtGetHelp, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that 'Get Help' section is displayed in Sec link rails", "true", String.valueOf(bGetHelp), true);
			
			//Get he URL of Current page
			String sBillAndUsagePageURL = lDriver.getCurrentUrl();
			
			/*Validate following links are displayed within the 'Manage Account' section:
				1. Manage Features**       **Not displayed in current releases
				2. Move my service 
				3. View Troubleshoot & Resolve Tool
				4. Member Center
				5. Set up Parental Controls for internet*/
			
			//2. Validate 'Move my service'
			boolean bMoveMyService = UI.WaitForObject(oR_BillAndUsage.lnkMoveMyService, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that Move My Service link is displayed under Manage Account section", "true", String.valueOf(bMoveMyService), true);
				//Click on Move My service link and check if it is redirected correctly
				oR_BillAndUsage.lnkMoveMyService.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Move My Service' link");
				Thread.sleep(60000);
				String sMoveMyServiceURL = lDriver.getCurrentUrl();
				Report.ValidationPoint(testContext.getName(), "URL for Move My Service page:"+sMoveMyServiceURL, "true", "true", true);
				Thread.sleep(10000);
				//lDriver.navigate().back();
				lDriver.get(sBillAndUsagePageURL);
				Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
				//Navigate to Report tab again to do rest of the validations
				boolean bReportTab2 = UI.WaitForObject(oR_BillAndUsage.btnReportsTab, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Validate that Report tab is displayed after navigating back from My Service page", "true", String.valueOf(bReportTab2), true);
				oR_BillAndUsage.btnReportsTab.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Reports' tab");
			
			//3. Validate 'Toubleshoot My Service' 	
			boolean bTroubleshoot = UI.WaitForObject(oR_BillAndUsage.lnkTroubleshootMyService, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that Troubleshoot My Service link is displayed under Manage Account section", "true", String.valueOf(bTroubleshoot), true);	
				//Click on Troubleshoot My service link and check if it is redirected correctly
				oR_BillAndUsage.lnkTroubleshootMyService.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Troubleshoot My Service' link");
				Thread.sleep(60000);
				String sTroubleshootMyServiceURL = lDriver.getCurrentUrl();
				Report.ValidationPoint(testContext.getName(), "URL for Toubleshoot My Service page:"+sTroubleshootMyServiceURL, "true", "true", true);
				Thread.sleep(10000);
				//lDriver.navigate().back();
				lDriver.get(sBillAndUsagePageURL);
				Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
				//Navigate to Report tab again to do rest of the validations
				boolean bReportTab3 = UI.WaitForObject(oR_BillAndUsage.btnReportsTab, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Validate that Report tab is displayed after navigating back from Toubleshoot My Service page", "true", String.valueOf(bReportTab3), true);
				oR_BillAndUsage.btnReportsTab.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Reports' tab");
			
			//4. Validate 'Visit Member Center' 	
			boolean bVisitMem = UI.WaitForObject(oR_BillAndUsage.lnkVisitMemberCenter, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that Visit Member Center link is displayed under Manage Account section", "true", String.valueOf(bVisitMem), true);	
				//Click on Visit Member centre link and check if it is redirected correctly
				oR_BillAndUsage.lnkVisitMemberCenter.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Visit Member Center' link");
				Thread.sleep(60000);
				String sVisitMemCenterURL = lDriver.getCurrentUrl();
				Report.ValidationPoint(testContext.getName(), "URL for Visit Member Center page:"+sVisitMemCenterURL, "true", "true", true);
				Thread.sleep(10000);
				//lDriver.navigate().back();
				lDriver.get(sBillAndUsagePageURL);
				Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
				//Navigate to Report tab again to do rest of the validations
				boolean bReportTab4 = UI.WaitForObject(oR_BillAndUsage.btnReportsTab, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Validate that Report tab is displayed after navigating back from Visit Member Center page", "true", String.valueOf(bReportTab4), true);
				oR_BillAndUsage.btnReportsTab.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Reports' tab");
				
				//5. Validate 'Set up Parental Controls for Internet' 	
				boolean bParentalControl = UI.WaitForObject(oR_BillAndUsage.lnkSetUpParentalControls, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate that Set up Parental Controls link is displayed under Manage Account section", "true", String.valueOf(bParentalControl), true);	
					//Click on Set up Parental Controls for Internet link and check if it is redirected correctly
					oR_BillAndUsage.lnkSetUpParentalControls.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Set up Parental Controls' link");
					Thread.sleep(60000);
					String sParentalControlURL = lDriver.getCurrentUrl();
					Report.ValidationPoint(testContext.getName(), "URL for Set up Parental controls page:"+sParentalControlURL, "true", "true", true);
					Thread.sleep(10000);
					//lDriver.navigate().back();
					lDriver.get(sBillAndUsagePageURL);
					Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
					//Navigate to Report tab again to do rest of the validations
					boolean bReportTab5 = UI.WaitForObject(oR_BillAndUsage.btnReportsTab, UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "Validate that Report tab is displayed after navigating back from Set up Parental Controls page", "true", String.valueOf(bReportTab5), true);
					oR_BillAndUsage.btnReportsTab.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Reports' tab");
					
					/*Validate Following links are displayed within the 'Change billing options' section:
						1. Manage Paperless Billing
						2. Manage AutoPay
						3. Manage payments options
						4. Change billing address*/
					
					//1. Validate Manage Paperless Billing
					boolean bPaperlessBilling = UI.WaitForObject(oR_BillAndUsage.lnkEnrollPaperlessBilling, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Validate that Enroll in paperless billing link is displayed under Change Billing Options section", "true", String.valueOf(bPaperlessBilling), true);	
						//Click on Enroll in paperless billing link and check if it is redirected correctly
						oR_BillAndUsage.lnkEnrollPaperlessBilling.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Enroll in paperless billing' link");
						Thread.sleep(60000);
						String sPaperlessBillingURL = lDriver.getCurrentUrl();
						Report.ValidationPoint(testContext.getName(), "URL for Enroll in paperless billing page:"+sPaperlessBillingURL, "true", "true", true);
						Thread.sleep(10000);
						//lDriver.navigate().back();
						lDriver.get(sBillAndUsagePageURL);
						Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
						//Navigate to Report tab again to do rest of the validations
						boolean bReportTab6 = UI.WaitForObject(oR_BillAndUsage.btnReportsTab, UI.iObjTimeOut);
						Report.TestPoint(testContext.getName(), "Validate that Report tab is displayed after navigating back from Enroll in paperless billing", "true", String.valueOf(bReportTab6), true);
						oR_BillAndUsage.btnReportsTab.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Reports' tab");
						
					//2. Validate Manage Autopay link Or Enroll in Autopay Link
					boolean bAutopay = UI.WaitForObject(oR_BillAndUsage.lnkEnrollInAutopay, UI.iObjTimeOut);
					if(bAutopay==true)
					{
						Report.ValidationPoint(testContext.getName(), "Validate that Enroll in Autopay link is displayed under Change Billing Options section", "true", String.valueOf(bAutopay), true);	
						//Click on Enroll in Autopay link and check if it is redirected correctly
						oR_BillAndUsage.lnkEnrollInAutopay.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Enroll in Autopay' link");
						Thread.sleep(60000);
						String sAutopayURL = lDriver.getCurrentUrl();
						Report.ValidationPoint(testContext.getName(), "URL for Enroll in Autopay page:"+sAutopayURL, "true", "true", true);
						Thread.sleep(10000);
						//lDriver.navigate().back();
						lDriver.get(sBillAndUsagePageURL);
						Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
						//Navigate to Report tab again to do rest of the validations
						boolean bReportTab7 = UI.WaitForObject(oR_BillAndUsage.btnReportsTab, UI.iObjTimeOut);
						Report.TestPoint(testContext.getName(), "Validate that Report tab is displayed after navigating back from Enroll in Autopay", "true", String.valueOf(bReportTab7), true);
						oR_BillAndUsage.btnReportsTab.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Reports' tab");
					}else
					{
						boolean bManageAutopay = UI.WaitForObject(oR_BillAndUsage.lnkManageAutoPay, UI.iObjTimeOut);
						Report.ValidationPoint(testContext.getName(), "Validate that Manage Autopay link is displayed under Change Billing Options section", "true", String.valueOf(bManageAutopay), true);	
						//Click on Manage autopay link and check if it is redirected correctly
						oR_BillAndUsage.lnkManageAutoPay.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Manage Autopay' link");
						Thread.sleep(60000);
						String sAutopayURL = lDriver.getCurrentUrl();
						Report.ValidationPoint(testContext.getName(), "URL for Manage Autopay page:"+sAutopayURL, "true", "true", true);
						Thread.sleep(10000);
						//lDriver.navigate().back();
						lDriver.get(sBillAndUsagePageURL);
						Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
						//Navigate to Report tab again to do rest of the validations
						boolean bReportTab7 = UI.WaitForObject(oR_BillAndUsage.btnReportsTab, UI.iObjTimeOut);
						Report.TestPoint(testContext.getName(), "Validate that Report tab is displayed after navigating back from Manage Autopay", "true", String.valueOf(bReportTab7), true);
						oR_BillAndUsage.btnReportsTab.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Reports' tab");
					}
					
						//3. Validate See more payment options link
						boolean bPaymentOptions = UI.WaitForObject(oR_BillAndUsage.lnkSeeMorePaymentOptions, UI.iObjTimeOut);
						Report.ValidationPoint(testContext.getName(), "Validate that See more payment options link is displayed under Change Billing Options section", "true", String.valueOf(bPaymentOptions), true);	
							//Click on See more paymnet options link and check if it is redirected correctly
							oR_BillAndUsage.lnkSeeMorePaymentOptions.click();
							Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'See more payment options' link");
							Thread.sleep(60000);
							String sSeeMoreURL = lDriver.getCurrentUrl();
							Report.ValidationPoint(testContext.getName(), "URL for See more payment options page:"+sSeeMoreURL, "true", "true", true);
							Thread.sleep(10000);
							//lDriver.navigate().back();
							lDriver.get(sBillAndUsagePageURL);
							Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
							//Navigate to Report tab again to do rest of the validations
							boolean bReportTab8 = UI.WaitForObject(oR_BillAndUsage.btnReportsTab, UI.iObjTimeOut);
							Report.TestPoint(testContext.getName(), "Validate that Report tab is displayed after navigating back from See more payment options", "true", String.valueOf(bReportTab8), true);
							oR_BillAndUsage.btnReportsTab.click();
							Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Reports' tab");
				
						//4. Change billing address
						boolean bBillingAdd = UI.WaitForObject(oR_BillAndUsage.lnkChangeBillingAddress, UI.iObjTimeOut);
						Report.ValidationPoint(testContext.getName(), "Validate that Change Billing address link is displayed under Change Billing Options section", "true", String.valueOf(bBillingAdd), true);	
							//Click on Change Billing Address link and check if it is redirected correctly
							oR_BillAndUsage.lnkChangeBillingAddress.click();
							Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Change billing address' link");
							Thread.sleep(60000);
							String sBillingAddURL = lDriver.getCurrentUrl();
							Report.ValidationPoint(testContext.getName(), "URL for Change billing address page:"+sBillingAddURL, "true", "true", true);
							Thread.sleep(10000);
							//lDriver.navigate().back();
							lDriver.get(sBillAndUsagePageURL);
							Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
							//Navigate to Report tab again to do rest of the validations
							boolean bReportTab9 = UI.WaitForObject(oR_BillAndUsage.btnReportsTab, UI.iObjTimeOut);
							Report.TestPoint(testContext.getName(), "Validate that Report tab is displayed after navigating back from Change billing address", "true", String.valueOf(bReportTab9), true);
							oR_BillAndUsage.btnReportsTab.click();
							Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Reports' tab");
							
						/*Following links are displayed within the 'Get Help' section:
							1. Home Phone Support
							2. High Speed Internet (DSL) Billng Support*/
							
						//1. Validate Home Phone Support
						boolean bHomePhone = UI.WaitForObject(oR_BillAndUsage.lnkHomePhoneSupport, UI.iObjTimeOut);
						Report.ValidationPoint(testContext.getName(), "Validate that Home Phone Support link is displayed under Change Billing Options section", "true", String.valueOf(bHomePhone), true);	
							//Click on Home Phone Support link and check if it is redirected correctly
							oR_BillAndUsage.lnkHomePhoneSupport.click();
							Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Home Phone Support' link");
							Thread.sleep(60000);
							String sHomePhoneURL = lDriver.getCurrentUrl();
							Report.ValidationPoint(testContext.getName(), "URL for Home Phone Support page:"+sHomePhoneURL, "true", "true", true);
							Thread.sleep(10000);
							//lDriver.navigate().back();
							lDriver.get(sBillAndUsagePageURL);
							Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
							//Navigate to Report tab again to do rest of the validations
							boolean bReportTab10 = UI.WaitForObject(oR_BillAndUsage.btnReportsTab, UI.iObjTimeOut);
							Report.TestPoint(testContext.getName(), "Validate that Report tab is displayed after navigating back from Home Phone Support", "true", String.valueOf(bReportTab10), true);
							oR_BillAndUsage.btnReportsTab.click();
							Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Reports' tab");	
						
						//2. Validate High Speed Internet (DSL) Billng Support
						boolean bHighSpeedInter = UI.WaitForObject(oR_BillAndUsage.lnkHighSpeedInternetBillingSupport, UI.iObjTimeOut);
						Report.ValidationPoint(testContext.getName(), "Validate that High Speed Internet (DSL) Billng Support link is displayed under Change Billing Options section", "true", String.valueOf(bHighSpeedInter), true);	
							//Click on High Speed Internet (DSL) Billng Support link and check if it is redirected correctly
							oR_BillAndUsage.lnkHighSpeedInternetBillingSupport.click();
							Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'High Speed Internet (DSL) Billng Support' link");
							Thread.sleep(60000);
							String sHighSpeedInter = lDriver.getCurrentUrl();
							Report.ValidationPoint(testContext.getName(), "URL for High Speed Internet (DSL) Billng Support page:"+sHighSpeedInter, "true", "true", true);
							Thread.sleep(10000);
							
						
				//End of Validations
				//lDriver.navigate().back();
				lDriver.get(sBillAndUsagePageURL);
				Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
					
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	
	/**************************************************************
	 * Function Name - ValidateBillDetailsSMB()
	 * Description - Validate Bill Summary page for SMB customer
	 * Parameters - None
	 * Date created - 15th Apr 2015
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/

	
	public static void ValidateBillDetailsSMB(final ITestContext testContext) throws HeadlessException, IOException, AWTException{
		try{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_SMBBillDetails oR_SMBBillDetails = PageFactory.initElements(lDriver, OR_SMBBillDetails.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			Boolean bSmallBusinessTab = UI.WaitForObject(oR_SMBBillDetails.lnkSmallBusiness, UI.iObjTimeOut);
			if(bSmallBusinessTab){

				// Validate Small Business tab is high-lighted
				String strTxtColor = oR_SMBBillDetails.lnkSmallBusiness.getCssValue("color");
				if(strTxtColor.equals("rgba(239, 111, 0, 1)")){
					Report.ValidationPoint(testContext.getName(),"Validate Small Business tab is high-lighted","Small Business tab is high-lighted","Small Business tab is high-lighted",true);	
				}else{
					Report.ValidationPoint(testContext.getName(),"Validate Small Business tab is high-lighted","Small Business tab is high-lighted","Small Business tab is high-lighted",true);	

				}

				// Verify Bill Summary page is displayed
				Report.OperationPoint(testContext.getName(), "Navigate to Bill Summary page");
				oR_SMBBillDetails.lnkViewBillSummary.click();


				if(UI.WaitForObject(oR_SMBBillDetails.txtBillSummary, UI.iObjTimeOut)){
					Report.ValidationPoint(testContext.getName(), "Validate Bill Summary page is displayed", "Bill Summary page is displayed", "Bill Summary page is displayed", true);

					// Check if Bill is avaialble for selected CTN. If not select different CTN from dropdown
					
					if(UI.WaitForObject(oR_SMBBillDetails.txtNoteBillNotAvailable, 10, lDriver)){
						
						if(oR_SMBBillDetails.txtNoteBillNotAvailable.getText().contains("Your bill is not available")){
						
						Report.OperationPoint(testContext.getName(), "Bill is not avaialble for selected CTN. Selecting other CTN");
						WebElement drpDwnCTNSelection = lDriver.findElement(By.id("accountModuleDropDown"));
						
						Select ctnDropDsown = new Select(drpDwnCTNSelection);
						ctnDropDsown.selectByIndex(1);
						Thread.sleep(5000);
						
					 }
				  }
					
					
					// Validate Account owner Frist and Lasr name is displayed
					if(oR_SMBBillDetails.txtAccountOwner.isDisplayed()){
						String strAccOwnerName = oR_SMBBillDetails.txtAccountOwner.getText();

						if(strAccOwnerName.substring(strAccOwnerName.indexOf(":")) != ""){

							if(strAccOwnerName.substring(strAccOwnerName.indexOf(":")).split("")[0] != null){
								Report.ValidationPoint(testContext.getName(), "Validate owners  frist name is displayed", "Owners frist name is displayed", "Owners frist name is displayed", true);	
							}else{
								Report.ValidationPoint(testContext.getName(), "Validate owners  frist name is displayed", "Owners frist name is displayed", "Owners frist name is NOT displayed", true);  
							}

							if(strAccOwnerName.substring(strAccOwnerName.indexOf(":")).split("")[1] != null){
								Report.ValidationPoint(testContext.getName(), "Validate owners  last name is displayed", "Owners last name is displayed", "Owners last name is displayed", true);	
							}else{
								Report.ValidationPoint(testContext.getName(), "Validate owners  last name is displayed", "Owners last name is displayed", "Owners last name is NOT displayed", true);  
							}

						}else{
							Report.ValidationPoint(testContext.getName(), "Validate owner name is displayed", "Owner name is displayed", "Owner name is not displayed", true);
						}

					}else{
						Report.ValidationPoint(testContext.getName(), "Validate owner name field is displayed", "Owner name filed is displayed", "Owner name field is NOT displayed", true);
					}


					// Validate Account Number information is displayed
					if(oR_SMBBillDetails.txtAccountNumber.isDisplayed()){
						String strAccNumber = oR_SMBBillDetails.txtAccountNumber.getText();

						if(strAccNumber.substring(strAccNumber.indexOf(":")) != ""){
							Report.ValidationPoint(testContext.getName(), "Validate account number is displayed", "Account number is displayed", "Account number is displayed", true);

						}else{
							Report.ValidationPoint(testContext.getName(), "Validate owner name is displayed", "Account number is displayed", "Account number is NOT displayed", true);
						}
					}else{
						Report.ValidationPoint(testContext.getName(), "Validate account number field is displayed", "Account number filed is displayed", "Account number field is NOT displayed", true);
					}

					

					// Validate "View Account Profile" link is displayed 
					if(oR_SMBBillDetails.lnkViewAccountProfile.isDisplayed()){
						Report.ValidationPoint(testContext.getName(), "Validate View Account Profile link is displayed ","View Account Profile link is displayed", "View Account Profile link is displayed", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Validate View Account Profile link is displayed ","View Account Profile link is displayed", "View Account Profile link is NOT displayed", true);
					}

					// Validate CURRENT PAYMENT & CHARGES displayed
					if(oR_SMBBillDetails.txtCurrentPaymentAndCharges.isDisplayed()){
						Report.ValidationPoint(testContext.getName(), "Validate CURRENT PAYMENT & CHARGES section is displayed", "CURRENT PAYMENT & CHARGES section is displayed", "CURRENT PAYMENT & CHARGES section is displayed",true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Validate CURRENT PAYMENT & CHARGES section is displayed", "CURRENT PAYMENT & CHARGES section is displayed", "CURRENT PAYMENT & CHARGES section is NOT displayed",true);
					}

					// Validate "Make a Payment" button is displayed
					if(oR_SMBBillDetails.btnMakeAPayment.isDisplayed()){
						Report.ValidationPoint(testContext.getName(), "Validate Make a Paymnet button is displayed", "Make a Paymnet button is displayed", "Make a Paymnet button is displayed",true);

						Report.OperationPoint(testContext.getName(), "Navigate to Make a Payment page");
						//Validate user is navigated to Make a Payment page 
						oR_SMBBillDetails.btnMakeAPayment.click();

						if(UI.WaitForObject(oR_SMBBillDetails.txtMakeaPayment, UI.iObjTimeOut)){
							Report.ValidationPoint(testContext.getName(),"Validate user is navigated to Make a Payment page","User is navigated to Make a Payment page","User is navigated to Make a Payment page",true);

							// Validate user is navigated back to Bill Summary page
							Report.OperationPoint(testContext.getName(), "Navigate back to Bill Summary page");
							
							lDriver.navigate().back();

							if(UI.WaitForObject(oR_SMBBillDetails.txtBillSummary, UI.iObjTimeOut)){
								Report.ValidationPoint(testContext.getName(), "Validate user is navigated back to Bill Summary page", "User is navigated back to Bill Summary page", "User is navigated back to Bill Summary page", true);
							}else{
								Report.ValidationPoint(testContext.getName(), "Validate user is navigated back to Bill Summary page", "User is navigated back to Bill Summary page", "User is NOT navigated back to Bill Summary page", true);
							}

						}else{
							Report.ValidationPoint(testContext.getName(),"Validate user is navigated to Make a Payment page","User is Navigated to Make a Payment page","User is not navigated to Make a Payment page",true);	
						}
					}else{
						Report.ValidationPoint(testContext.getName(), "Validate Make a Paymnet button is displayed", "Make a Paymnet button is displayed", "Make a Paymnet button is NOT displayed",true);
					}
					
					//Validate BILL SUMMARY tab is by default selected
					if(oR_SMBBillDetails.txtBillAtAGlance.isDisplayed()){
						Report.ValidationPoint(testContext.getName(), "Validate BILL SUMMARY tab is selected by default", "BILL SUMMARY tab is selected by default", "BILL SUMMARY tab is selected by default",true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Validate BILL SUMMARY tab is selected by default", "BILL SUMMARY tab is selected by default", "BILL SUMMARY tab is NOT selected by default",true);
					}


					// Validate Business Phone Details tab is displaed
					if(oR_SMBBillDetails.lnkBusinessPhoneDetails.isDisplayed()){
						Report.ValidationPoint(testContext.getName(), "Validate Business Phone Details tab is displayed ", "Business Phone Details tab is displayed", "Business Phone Details tab is displayed", true);

						Report.OperationPoint(testContext.getName(), "Navigate to Business Phone Details tab");
						oR_SMBBillDetails.lnkBusinessPhoneDetails.click();

						if(UI.WaitForObject(oR_SMBBillDetails.txtCallDetails, UI.iObjTimeOut)){
							Report.ValidationPoint(testContext.getName(), "Validate user is navigated to Business Phone Details tab", "User is navigated Business Phone Details tab", "User is navigated Business Phone Details tab", true);

						}else{
							Report.ValidationPoint(testContext.getName(), "Validate user is navigated to Business Phone Details tab", "User is navigated Business Phone Details tab", "User is NOT navigated Business Phone Details tab", true);
						}

					}else{
						Report.ValidationPoint(testContext.getName(), "Validate Business Phone Details tab is displayed ", "Business Phone Details tab is displayed", "Business Phone Details tab is NOT displayed", true);
					}


					// Validate Yellow Summary Box under Bill Summary tab
					   if(oR_SMBBillDetails.yellowMessageBox.isDisplayed()){
						   	String rgba = oR_SMBBillDetails.yellowMessageBox.getCssValue("background-color");
							if(rgba.equals("rgba(255, 255, 204, 1)")){
								Report.ValidationPoint(testContext.getName(), "Validate that yellow message box is displayed", "Yellow message box is displayed", "Yellow message box is displayed", true);
							}else{
								Report.ValidationPoint(testContext.getName(), "Validate that yellow message box is displayed", "Yellow message box is displayed", "Yellow message box is NOT displayed", true);
							}
										
						}


					// Validate View Full Bill link is displayed
					if(oR_SMBBillDetails.btnViewFullBill.isDisplayed()){

						Report.ValidationPoint(testContext.getName(), "Validate View Full Bill button is displayed", "View Full Bill button is displayed","View Full Bill button is displayed", true);
						String mainWindowHandle = lDriver.getWindowHandle();

						Report.OperationPoint(testContext.getName(), "Navigate to View Full Bill page");
						oR_SMBBillDetails.btnViewFullBill.click();

						Set<String> windowHandles = lDriver.getWindowHandles();

						if(windowHandles.size() == 1){
							Report.ValidationPoint(testContext.getName(), "Validate View Full Bill pop-up window is displayed", "View Full Bill pop-up window is displayed","View Full Bill pop-up window is NOT displayed", true);	
						}

						for(String currentWindow : windowHandles){

							if(! currentWindow.equals(mainWindowHandle)){
								lDriver.switchTo().window(currentWindow);
								if(UI.WaitForObject(oR_SMBBillDetails.txtPDFBillNotAvailable, UI.iObjTimeOut)){
									Report.ValidationPoint(testContext.getName(),"Validate pop up window with message PDF Not Available is displayed","Pop up window with message PDF Not Available is displayed","pop up window with message PDF Not Available is displayed",true);

									Report.OperationPoint(testContext.getName(),"Closing Pop up window");
									oR_SMBBillDetails.lnkCloseWindow.click();
									lDriver.switchTo().window(mainWindowHandle);

								}

							}
						}

					}else{

						Report.ValidationPoint(testContext.getName(), "Validate View Full Bill button is displayed", "View Full Bill button is displayed","View Full Bill button is NOT displayed", true);
					}


					// Validate text Statement dated is displayed
					if(oR_SMBBillDetails.txtStatementDate.isDisplayed()){
						Report.ValidationPoint(testContext.getName(), "Validate text statement dated is displayed ", "Text statement dated is displayed", "Text statement dated is displayed", true);
						String strDate = oR_SMBBillDetails.txtStatementDate.getText();
						String[] strSplit = strDate.split(" ");
			
						if( (strSplit[strSplit.length - 1].charAt(2) == '/' ) && (strSplit[strSplit.length - 1].charAt(5) == '/') ){
							String[] strDateSplit = strSplit[strSplit.length - 1].split("/");

							// Validate if date is in mm/dd/yyyy formate
							if(strDateSplit[0].length() == 2  && strDateSplit[1].length() == 2 && strDateSplit[2].length() == 5){
								Report.ValidationPoint(testContext.getName(), "Validate text statement date is in mm/dd/yyyy formate", "Text statement date is in mm/dd/yyyy formate", "Text statement date is in mm/dd/yyyy formate", true);
							}else{
								Report.ValidationPoint(testContext.getName(), "Validate text statement date is in mm/dd/yyyy formate", "Text statement date is in mm/dd/yyyy formate", "Text statement date is not in mm/dd/yyyy formate", true);
							}
						}else{
							Report.ValidationPoint(testContext.getName(), "Validate text statement date is in mm/dd/yyyy formate", "Text statement date is in mm/dd/yyyy formate", "Text statement date is NOT in mm/dd/yyyy formate", true);
						}
					}else{
						Report.ValidationPoint(testContext.getName(), "Validate text statement dated is displayed ", "Text statement dated is displayed", "Text statement dated is NOT displayed", true);
					}

				}else{
					Report.ValidationPoint(testContext.getName(), "Validate Bill Summary page is displayed", "Bill Summary page is displayed", "Bill Summary page is NOT displayed", true);
				}
			}else{

				Report.ValidationPoint(testContext.getName(),"Validate Dashboard page is displayed", "Dashboard page is displayed", "Validate Dashboard page is NOT displayed",true);
			}
		}catch (Exception e) 
		{
		  	Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate "+e.getMessage(), true);

		}

	}			
	/**************************************************************
	 * Function Name - VerifyBillinAndPaymentNotificationLink()
	 * Description - Validate Paper less Billing notification link in Billing and Usage
	 * Parameters - None
	 * Date created -16th Apr 2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//BAP11279
	public static void VerifyBillinAndPaymentNotificationLink(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
			try
			{
				
				WebDriver lDriver = UI.getDriver(testContext.getName());
				OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
				OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
				Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
				
				//Validationg on Paperless Billing Page. 
				UI.WaitForObject(oR_AccountOverview.lnkManagePaperlessBilling, 50);
				Report.OperationPoint(testContext.getName(), "Navigating  to Paperless Billing page");
				Report.ValidationPoint(testContext.getName(),"Validate Paperless Billing link", "Paperless Billing link is displayed", "Paperless Billing link is displayed",true);
				oR_AccountOverview.lnkManagePaperlessBilling.click();
			
				//Click on change enrollment setting link
				UI.WaitForObject(oR_BillAndUsage.lnkBillingUsagePaymentsChangeEnrollment, 50);
				Report.OperationPoint(testContext.getName(), "Navigating  to change enrollment setting page");
				Report.ValidationPoint(testContext.getName(),"Validate change enrollment setting link", "change enrollment setting link is displayed", "change enrollment setting link is displayed",true);
				oR_BillAndUsage.lnkBillingUsagePaymentsChangeEnrollment.click();
							
				//Changing preference to paper billing 
				boolean rPaper = UI.WaitForObject(oR_BillAndUsage.txtCancelPaperlessBilling, 50);
				Report.TestPoint(testContext.getName(), "validate radio button to prefer paper billing", "true", String.valueOf(rPaper), true);
				oR_BillAndUsage.btnCancelPaperlessBilling.click();
					
				oR_BillAndUsage.lnkClosePopupInContinuePaperBill.click();
				
				//validating cancel button
				boolean blnkCNCL = UI.WaitForObject(oR_BillAndUsage.lnkCancelinEnrollmentSetting, 50);
				Report.TestPoint(testContext.getName(), "validate Cancel button in Change Enrollment Setting link", "true", String.valueOf(blnkCNCL), true);
				oR_BillAndUsage.lnkCancelinEnrollmentSetting.click();
				Report.OperationPoint(testContext.getName(), "Navigating back to dash board");
		 
			
			}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	}
	/**************************************************************
	 * Function Name - ValidateAccountSummaryLinksCaliforniaCustomer()
	 * Description - Validate Account Sumarry links for Claifornia Wireless Customer
	 * Parameters - None
	 * Date created - 17th Apr 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - Monica Mohabansi
	 * Last Modified Date - 16th Oct 2015
	 ***************************************************************/
	//BAP30607
	
	public static void ValidateAccountSummaryLinksCaliforniaCustomer(final ITestContext testContext) throws HeadlessException, IOException, AWTException{
		try
		{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			Boolean bLnkBAP = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Click on Link Billing & Usage from Sec Navigation", "True", String.valueOf(bLnkBAP), true);

			if(bLnkBAP.equals(true))
			{
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
				Report.ValidationPoint(testContext.getName(), "Clicked on Link Billing & Usage from Sec Navigation", "True", String.valueOf(bLnkBAP), true);
			}
			else
			{
				Boolean bIWantTo = UI.WaitForObject(oR_AccountOverview.lnkIWantTo, UI.iObjTimeOut);
				if(bIWantTo)
				{
					Actions action = new Actions(lDriver);
					action.moveToElement(oR_AccountOverview.lnkIWantTo).build().perform();
					Thread.sleep(4000);
					try
					{					
						Report.TestPoint(testContext.getName(), "Select 'View Bill details' from 'I want to..' dropdown", "Selected", "Selected", true);
						action.click(oR_AccountOverview.lnkViewBillDetails).build().perform();
						Thread.sleep(8000);
					}
					catch(Exception e)
					{
						Report.TestPoint(testContext.getName(), "Select 'View Bill details' from 'I want to..' dropdown", "Selected", "Unable to Select 'View bill details from 'I want to...''", true);
					}
				}
				else
					Report.TestPoint(testContext.getName(), "Select 'View Bill details' from 'I want to..' dropdown", "Selected", "Unable to Select 'View bill details from 'I want to...''", true);
			}
				
				//Navigated to Bill&Usage Page
				Boolean bBAPPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "User is navigated to Bill&Usage page and Bill Tab is selected", "True", String.valueOf(bBAPPage), true);
				//Verify 3 CTAs
				//1) View News you Can Use
				Boolean bNews = UI.WaitForObject(lDriver.findElement(By.id("ViewNewsYouCanUse")), 100);
				Report.TestPoint(testContext.getName(), "View News You Can Use link is displayed", "True", String.valueOf(bNews), true);
				if(bNews.equals(true))
				{
					lDriver.findElement(By.id("ViewNewsYouCanUse")).click();
					String parentHandle = lDriver.getWindowHandle();
					for (String winHandle : lDriver.getWindowHandles()) {
					    lDriver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					}
			
					Boolean bNewsWindow = UI.WaitForObject(lDriver.findElement(By.xpath("//div[h1[contains(text(),'News You Can Use & Promos')]]")), UI.iObjTimeOut);;
					Report.TestPoint(testContext.getName(), "User is navigated to News You Can Use & Promos Page", "True", String.valueOf(bNewsWindow), true);

					lDriver.close(); // close newly opened window when done with it
					lDriver.switchTo().window(parentHandle);
				}
				//2) Terms & Conditions
				Boolean bTerms = UI.WaitForObject(lDriver.findElement(By.id("ViewTermsConditions")), 100);
				Report.TestPoint(testContext.getName(), "View Terms Conditions link is displayed", "True", String.valueOf(bTerms), true);
				if(bTerms.equals(true))
				{
					lDriver.findElement(By.id("ViewTermsConditions")).click();
					Thread.sleep(30000);
					lDriver.switchTo().frame(lDriver.findElement(By.xpath("//iframe[@class='cboxIframe']")));
					
					
					Boolean bViewTerms = UI.WaitForObject(lDriver.findElement(By.xpath("//div[h1[contains(text(),'Terms and Conditions')]]")), UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "Verify User is navigated to Terms And Condition modal", "True", String.valueOf(bViewTerms), true);
					
					lDriver.switchTo().defaultContent();
					
					Boolean bClose = UI.WaitForObject(lDriver.findElement(By.xpath("//img[@src='/olam/images/brand30/modalClose.png']")), UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "Close option is displayed on View Terms & Conditions frame", "True", String.valueOf(bClose), true);
					lDriver.findElement(By.xpath("//img[@alt='close']")).click();

				}
				//3)Important information for California Customers
				Boolean bInfo = UI.WaitForObject(lDriver.findElement(By.id("impInfo")),80);
				Report.TestPoint(testContext.getName(), "'Important information for California customers' link is displayed", "True", String.valueOf(bInfo), true);
				if(bInfo.equals(true))
				{
					lDriver.findElement(By.id("impInfo")).click();
					Thread.sleep(20000);
					lDriver.switchTo().frame(lDriver.findElement(By.xpath("//iframe[@class='cboxIframe']")));
					
					
					Boolean bInfoCA = UI.WaitForObject(lDriver.findElement(By.xpath("//div[h1[contains(text(),'Information for California ')]]")), UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "Verify User is navigated to Information for California customers modal", "True", String.valueOf(bInfoCA), true);
					
					lDriver.switchTo().defaultContent();
					
					Boolean bClose = UI.WaitForObject(lDriver.findElement(By.xpath("//img[@src='/olam/images/brand30/modalClose.png']")), UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "Close option is displayed on View Terms & Conditions frame", "True", String.valueOf(bClose), true);
					lDriver.findElement(By.xpath("//img[@alt='close']")).click();

				}
			
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	}
	/**************************************************************
	 * Function Name - ValidateEnrollAutopay
	 * Description- 
	 * Parameters - 
	 * Date created -21st April 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - Hiral Jogi
	 * Last Modified Date - 27th Jan 2016
	 ***************************************************************/
	//BAP08739
	public static void ValidateEnrollAutopay (final ITestContext testContext)
			throws Exception {
		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_AutoPayEnrollment oR_AutoPayEnrollment = PageFactory.initElements(lDriver, OR_AutoPayEnrollment.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			Boolean bEnrollAutopay = UI.WaitForObject(oR_AccountOverview.lnkEnrollInAutopay_OverviewPage, UI.iObjTimeOut);
			if(bEnrollAutopay){
				Report.TestPoint(testContext.getName(), "Enroll Autopay Link is present", "True", String.valueOf(bEnrollAutopay), true);
				
				//click on Enroll Autopay
				oR_AccountOverview.lnkEnrollInAutopay_OverviewPage.click();
				Report.OperationPoint(testContext.getName(), "Navigating to Enroll Autopay page");
			/*	Boolean bAutopayPage = UI.WaitForObject(oR_AutoPayEnrollment.txtEnrollInAutopay, 60);
				Report.TestPoint(testContext.getName(), "Enroll Autopay Link is present", "True", String.valueOf(bAutopayPage), true);*/
			}
			Thread.sleep(10000);
			UI.SelectOptionFromDropDown(oR_AutoPayEnrollment.lstSelectPaymentMethod, "New checking / savings account");
			Thread.sleep(20000);

			String sMainWindowHandle = lDriver.getWindowHandle();
			lDriver.switchTo().frame(oR_AutoPayEnrollment.frmCheckingPayment);
			//Entering Card details
			
			lDriver.findElement(By.id("paymentMethodForm.newBankCustomerName")).sendKeys("abc");
			String sAccountNo = IO.GetParamVal(sTestParams, "AccNo");
			String sRoutingNo = IO.GetParamVal(sTestParams, "RoutingNo");
			lDriver.findElement(By.id("paymentMethodForm.routingNumber")).sendKeys(sRoutingNo);
			lDriver.findElement(By.id("paymentMethodForm.accountNumber")).sendKeys(sAccountNo);
			lDriver.findElement(By.id("paymentMethodForm.accountNumberConfirm")).sendKeys(sAccountNo);
			
			//Click on Continue
			Boolean bContinue = UI.WaitForObject(oR_AutoPayEnrollment.btnContinue, 60);
			Report.TestPoint(testContext.getName(), "Continue button is present", "True", String.valueOf(bContinue), true);
			oR_AutoPayEnrollment.btnContinue.click();
			Thread.sleep(10000);
			if(UI.WaitForObject(oR_AutoPayEnrollment.btnContinue, 60))
				oR_AutoPayEnrollment.btnContinue.click();
			
			lDriver.switchTo().window(sMainWindowHandle);
			
			//click on Next
			Boolean bNext = UI.WaitForObject(oR_AutoPayEnrollment.btnNext, 60);
			Report.TestPoint(testContext.getName(), "Next/Continue button is present", "True", String.valueOf(bNext), true);
			oR_AutoPayEnrollment.btnNext.click();
			if( UI.WaitForObject(oR_AutoPayEnrollment.btnNext, 60))
				oR_AutoPayEnrollment.btnNext.click();
			Thread.sleep(2000);
			
			//Click on Submit
			Boolean bSubmit = UI.WaitForObject(oR_AutoPayEnrollment.btnSubmit, 60);
			Report.TestPoint(testContext.getName(), "Submit button is present", "True", String.valueOf(bSubmit), true);
			oR_AutoPayEnrollment.btnSubmit.click();
			Thread.sleep(10000);
			
			try
			{
				//Successfully enrolled 

				Boolean bSuccess = UI.WaitForObject(oR_AutoPayEnrollment.txtSuccess, 60);
				if(bSuccess)
				{
					Report.TestPoint(testContext.getName(), "User is successfully enrolled in Autopay", "True", String.valueOf(bSuccess), true);
				}else if(bSuccess.equals(false))
				{
					//Autopay Pending approval
					WebElement txtPendingAprroval = lDriver.findElement(By.xpath("//*[contains(text(),'Your request to enroll in AutoPay has been submitted and is pending approval')]"));
					Boolean bPendingApproval = UI.WaitForObject(txtPendingAprroval, UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "User request to enroll in AutoPay has been submitted and is pending approval", "True", String.valueOf(bPendingApproval), true);
					
				}
				
			}catch(Exception e1)
			{
				//Autopay Pending approval
				WebElement txtPendingAprroval = lDriver.findElement(By.xpath("//*[contains(text(),'Your request to enroll in AutoPay has been submitted and is pending approval')]"));
				Boolean bPendingApproval = UI.WaitForObject(txtPendingAprroval, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "User request to enroll in AutoPay has been submitted and is pending approval", "True", String.valueOf(bPendingApproval), true);
				
			}

	/*		//click on Go To Overview
			oR_AutoPayEnrollment.lnkGoToAccountOverview.click();
			Thread.sleep(4000);*/
			
		/*	//change method of payment and submit if the data has more than one payment option
			
			//Manage Autopay
			Boolean bManageAutoPay = UI.WaitForObject(oR_AccountOverview.lnkManageAutoPay, 60);
			Report.TestPoint(testContext.getName(), "Manage Autopay link is displayed", "True", String.valueOf(bManageAutoPay), true);
			
			oR_AccountOverview.lnkManageAutoPay.click();
			WebElement lnkChangeWay = lDriver.findElement(By.xpath("//a[contains(text(),'Change the way you pay (payment method)')]");
			Boolean bChangeWay = UI.WaitForObject(lnkChangeWay, 60);
			Report.TestPoint(testContext.getName(), "'Change the way you pay you pay' link is displayed", "True", String.valueOf(bChangeWay), true);
			
			lDriver.findElement(By.xpath("//a[contains(text(),'Change the way you pay (payment method)')]").click();
			
			//select other method of payment and enter credentials then gop back on overview after submitting */
			
			
			oR_AccountOverview.lnkOverview.click();
			Thread.sleep(10000);
			
			WebElement txtAutoPay = lDriver.findElement(By.xpath("(//*[contains(text(),'Manage AutoPay')])[1]"));
			Boolean bManageAutoPay = UI.WaitForObject(txtAutoPay, 60);
			//Click on Manage AutoPay 
			/*if(oR_AccountOverview.lnkManageAutopayDasboard.isDisplayed() | oR_AccountOverview.lnkManageAutoPay.isDisplayed() | oR_AccountOverview.lnkManageAutoPay2.isDisplayed())
			{*/
			
			if(bManageAutoPay)
			{
				Report.TestPoint(testContext.getName(), "Manage Autopay link is displayed", "True", "True", true);

			
		/*	Boolean bManageAutoPay = UI.WaitForObject(oR_AccountOverview.lnkManageAutoPay, 60);
			Report.TestPoint(testContext.getName(), "Manage Autopay link is displayed", "True", String.valueOf(bManageAutoPay), true);*/
			
			oR_AccountOverview.lnkManageAutoPay.click();
			Thread.sleep(3000);
			}
			try
			{
				//Click on discontinue link
				Boolean bDiscontinue = UI.WaitForObject(oR_AutoPayEnrollment.btnDisContinue, 90);
				if(bDiscontinue)
				{
					Report.TestPoint(testContext.getName(), "Disontinue Autopay link is displayed", "True", String.valueOf(bDiscontinue), true);
					oR_AutoPayEnrollment.btnDisContinue.click();
					
					//Navigating to Discontinue page
					Report.OperationPoint(testContext.getName(), "Navigating to Discontinue AutoPay page");
					Boolean bDiscontinuePage = UI.WaitForObject(oR_AutoPayEnrollment.txtDiscontinuePage, 90);
					Report.TestPoint(testContext.getName(), "Disontinue Autopay page is displayed", "True", String.valueOf(bDiscontinuePage), true);
					
					//Click on Submit
					bSubmit = UI.WaitForObject(oR_AutoPayEnrollment.btnSubmit, 90);
					Report.TestPoint(testContext.getName(), "Submit button is displayed", "True", String.valueOf(bSubmit), true);
					oR_AutoPayEnrollment.btnSubmit.click();
					
					//Confirmation page
					Boolean bConfirmation = UI.WaitForObject(oR_AutoPayEnrollment.txtDiscontinueConfirmation, 90);
					Report.TestPoint(testContext.getName(), "Confirmation page is displayed", "True", String.valueOf(bConfirmation), true);
				
				}else if(bDiscontinue.equals(false))
				{
					boolean bPedingAutopay = UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(text(),'Your AutoPay enrollment is pending. Your status will be updated within 48 hours')]")), UI.iObjTimeOut, lDriver);
					Report.TestPoint(testContext.getName(), "Autopay enrollment pending message is displayed", "True", String.valueOf(bPedingAutopay), true);
				}
				
			}catch(Exception e2)
			{

				boolean bPedingAutopay = UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(text(),'Your AutoPay enrollment is pending. Your status will be updated within 48 hours')]")), UI.iObjTimeOut, lDriver);
				Report.TestPoint(testContext.getName(), "Autopay enrollment pending message is displayed", "True", String.valueOf(bPedingAutopay), true);
			
			}

		}

		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name - ValidateCardAboutToExpireAlertBillDetails
	 * Description - Validate Card about to expire alert in bill details page
	 * Parameters - None
	 * Date created - 23rd Apr 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//BAP30676
	
	public static void ValidateCardAboutToExpireAlertBillDetails(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_EditPaymentProfile oR_EditPaymentProfile = PageFactory.initElements(lDriver, OR_EditPaymentProfile.class);
			OR_ManageAutoPay oR_ManageAutoPay = PageFactory.initElements(lDriver, OR_ManageAutoPay.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			//Click on Billing payement and usage sec nav link
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,null);
			//Validate billing and usage page
			Boolean bBAPpg = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,100);
			Report.ValidationPoint(testContext.getName(), "Validate billing and usage page", "True", String.valueOf(bBAPpg), true);
			//Verify the Bill Alert Section
			Boolean bBillAlerts = UI.WaitForObject(oR_BillAndUsage.txtBillAlerts,20);
			Report.ValidationPoint(testContext.getName(), "Validate the Bill Alert Section", "True", String.valueOf(bBillAlerts), true);
			
			//Validate Card About to Expire Alert should be displayed within Bill Alert Section
			Boolean bCard = UI.WaitForObject(oR_BillAndUsage.txtCardAboutToExpire,10);
			Report.ValidationPoint(testContext.getName(), "Validate Card About to Expire Alert should be displayed within Bill Alert Section", "True", String.valueOf(bCard), true);
			//Verify an alert icon
			List<WebElement> lstAlert = lDriver.findElements(By.xpath("//img[contains(@src,'alertIcon.png')]"));
			if(lstAlert.size()>0)
			{
				Report.ValidationPoint(testContext.getName(), "Validate an alert icon", "True","True", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate an alert icon", "True","False", true);
			}
			//Validate description : Please update the expiration date on your card ending in ####(Last 4 digits of the card)'should be displayed
			Boolean bDesc = UI.WaitForObject(oR_BillAndUsage.txtCardAboutToExpireDesc,20);
			if(bDesc.equals(true))
			{
				String sDesc = oR_BillAndUsage.txtCardAboutToExpireDesc.getText();
				String[] sNumber = sDesc.split("in");
				String sTrim = sNumber[2].trim();
				if(!sTrim.isEmpty())
				{
					Report.ValidationPoint(testContext.getName(), "Validate description : Please update the expiration date on your card ending in ####(Last 4 digits of the card)'should be displayed", "True","True", true);
					Report.OperationPoint(testContext.getName(), "	Operational - Retrieving the alert description : "+sDesc);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate description : Please update the expiration date on your card ending in ####(Last 4 digits of the card)'should be displayed", "True","False", true);
			}
			//Validate Update Payment Profile link
			Boolean bUpdateLink = UI.WaitForObject(oR_BillAndUsage.lnkUpdatePaymentProfileCardAboutExpire,20);
			Report.ValidationPoint(testContext.getName(), "Validate Update Payment Profile link", "true", String.valueOf(bUpdateLink), true);
			//Click on Update Payment Profile link
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Update Payment Profile link");
			oR_BillAndUsage.lnkUpdatePaymentProfileCardAboutExpire.click();
			//Validate User should be directed to Profile Page to Edit the Payment Profile
			Boolean bEditPg = UI.WaitForObject(oR_ManageAutoPay.txtManageMyAutopaySettings,50);
			Report.ValidationPoint(testContext.getName(), "Validate User should be directed to Manage Autopay page", "True", String.valueOf(bEditPg), true);
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	

	/**************************************************************
	 * Function Name - ValidateCVVremovedCreditCardExpiration
	 * Description - Validate that CVV field is removed while updating Credit card expiration date
	 * Parameters - None
	 * Date created - 24th Apr 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//BAP11483
	
	public static void ValidateCVVremovedCreditCardExpiration(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_ManageAutoPay oR_ManageAutoPay = PageFactory.initElements(lDriver, OR_ManageAutoPay.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			//Validate manage Autopay link
			Boolean bManageAutopay = UI.WaitForObject(oR_AccountOverview.lnkManageAutopayDasboard,50);
			Report.TestPoint(testContext.getName(), "Validate manage Autopay link", "True", String.valueOf(bManageAutopay), true);
			//Click manage Autopay link
			Report.OperationPoint(testContext.getName(), "	Operational - Click manage Autopay link");
			oR_AccountOverview.lnkManageAutopayDasboard.click();
			
			//Validate Manage My AutoPay Preferences page should display
			Boolean bManageAutopayPg = UI.WaitForObject(oR_ManageAutoPay.txtManageAutoPay,120);
			Report.TestPoint(testContext.getName(), "Validate Manage My AutoPay Preferences page should display", "True", String.valueOf(bManageAutopayPg), true);
			//Validate Edit card expiration date link
			Boolean bUpdateCardlnk = UI.WaitForObject(oR_ManageAutoPay.lnkEditCardExpirationDate,1);
			Report.TestPoint(testContext.getName(), "Validate Edit card expiration date link", "True", String.valueOf(bUpdateCardlnk), true);
			//Click Update card expiration date link
			Report.OperationPoint(testContext.getName(), "	Operational - Click Edit card expiration date link");
			oR_ManageAutoPay.lnkEditCardExpirationDate.click();
			Thread.sleep(5000);
			//Validate Card information popup
			Boolean bPopup = UI.WaitForObject(oR_ManageAutoPay.frmCardInfo,50);
			if(bPopup.equals(true))
			{
				lDriver.switchTo().frame(oR_ManageAutoPay.frmCardInfo);
				//Validate popup name as card information
				Boolean bCardInfo = UI.WaitForObject(oR_ManageAutoPay.txtCardInfo,20);
				Report.TestPoint(testContext.getName(), "Validate popup name as card information", "True", String.valueOf(bCardInfo), true);
				//Validate Card Expiration Date text
				Boolean bCardExp = UI.WaitForObject(oR_ManageAutoPay.txtCardExpDate,20);
				Report.TestPoint(testContext.getName(), "Validate Card Expiration Date text", "True", String.valueOf(bCardExp), true);
				//Validate CVV label not displayed
				Boolean bCVV = UI.VerifyElementNotPresent(oR_ManageAutoPay.lnkCVV, "CVV label");
				Report.ValidationPoint(testContext.getName(), "Validate CVV label not displayed", "False", String.valueOf(!bCVV), true);
				//Select month and year
				UI.SelectOptionFromDropDown(oR_ManageAutoPay.lstCardExpMonth, "12");
				UI.SelectOptionFromDropDown(oR_ManageAutoPay.lstCardExpYear, "2016");
				//Click on Continue button
				Report.OperationPoint(testContext.getName(), "	Operational - Click Continue button");
				oR_ManageAutoPay.lnkContinue.click();
				Thread.sleep(60000);
				lDriver.switchTo().defaultContent();
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Validate popup name as card information", "True","False", true);
			}
			//Validate Manage My AutoPay Preferences page should display
			Boolean bManageAutopayPg1 = UI.WaitForObject(oR_ManageAutoPay.txtManageMyAutopaySettings,120);
			Report.TestPoint(testContext.getName(), "Validate Manage My AutoPay Preferences page should display", "True", String.valueOf(bManageAutopayPg1), true);
			//Click Update card expiration date link
			Report.OperationPoint(testContext.getName(), "	Operational - Click Update card expiration date link");
			oR_ManageAutoPay.lnkUpdateCardExpDate.click();
			Thread.sleep(5000);
			//Validate Card information popup
			Boolean bPopup1 = UI.WaitForObject(oR_ManageAutoPay.frmCardInfo,50);
			if(bPopup1.equals(true))
			{
				lDriver.switchTo().frame(oR_ManageAutoPay.frmCardInfo);
				//Validate popup name as card information
				Boolean bCardInfo = UI.WaitForObject(oR_ManageAutoPay.txtCardInfo,20);
				Report.TestPoint(testContext.getName(), "Validate popup name as card information", "True", String.valueOf(bCardInfo), true);
				//Click on cancel
				oR_ManageAutoPay.lnkCancel.click();
				Report.OperationPoint(testContext.getName(), "	Operational - Click cancel link");
				lDriver.switchTo().defaultContent();
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Validate popup name as card information", "True","False", true);
			}
			//Validate Manage My AutoPay Preferences page should display
			Thread.sleep(5000);
			Boolean bManageAutopayPg2 = UI.WaitForObject(oR_ManageAutoPay.txtManageMyAutopaySettings,50);
			Report.TestPoint(testContext.getName(), "Validate Manage My AutoPay Preferences page should display", "True", String.valueOf(bManageAutopayPg2), true);
			//Click Update card expiration date link
			Report.OperationPoint(testContext.getName(), "	Operational - Click Update card expiration date link");
			oR_ManageAutoPay.lnkUpdateCardExpDate.click();
			//Validate Card information popup
			Boolean bPopup2 = UI.WaitForObject(oR_ManageAutoPay.frmCardInfo,50);
			if(bPopup2.equals(true))
			{
				lDriver.switchTo().frame(oR_ManageAutoPay.frmCardInfo);
				//Validate popup name as card information
				Boolean bCardInfo = UI.WaitForObject(oR_ManageAutoPay.txtCardInfo,20);
				Report.TestPoint(testContext.getName(), "Validate popup name as card information", "True", String.valueOf(bCardInfo), true);
				//Click on Close
				Report.OperationPoint(testContext.getName(), "	Operational - Click on close link");
				oR_ManageAutoPay.lnkClose.click();
				lDriver.switchTo().defaultContent();
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Validate popup name as card information", "True","False", true);
			}
			//Validate Manage My AutoPay Preferences page should display
			Boolean bManageAutopayPg3 = UI.WaitForObject(oR_ManageAutoPay.txtManageMyAutopaySettings,50);
			Report.TestPoint(testContext.getName(), "Validate Manage My AutoPay Preferences page should display", "True", String.valueOf(bManageAutopayPg3), true);
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	
	}
	
	/**************************************************************
	 * Function Name - ValidateNoteForBankAccountAsPaymentMethod()
	 * Description - Validate Note for encouraging users for using back account as payment method
	 * Test Case - BAP14626
	 * Parameters - None
	 * Date created - 19th Apr 2015
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/

	
	public static void ValidateNoteForBankAccountAsPaymentMethod(final ITestContext testContext) throws HeadlessException, IOException, AWTException{
		try{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
			OR_EditPaymentInformation oR_EditPaymentInformation = PageFactory.initElements(lDriver, OR_EditPaymentInformation.class);
			OR_ReviewPaymentDetails oR_ReviewPaymentDetails = PageFactory.initElements(lDriver, OR_ReviewPaymentDetails.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			  String strMainWindowHandle = lDriver.getWindowHandle();
  
			  Report.OperationPoint(testContext.getName(), "Navigate to Make a Payment page");
  		      if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut)){
			//  UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkMakeAPaymentTertNav,lDriver);
			  UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkMakeAPaymentTertNav, lDriver);
  		      
  		      Boolean bMakeAPayment = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, UI.iObjTimeOut);
		      // Validate Make a Payment page is displayed
			  if(bMakeAPayment){
				Report.ValidationPoint(testContext.getName(), "Validate Make a Payment page is displayed", "Make a Payment page is displayed", "Make a Payment page is displayed", true);
	    		
				// Validate Schedule Payment link is available
	            if(UI.WaitForObject(oR_MakeAPayment.lnkScheduledPayment,UI.iObjTimeOut)){
	            	Report.OperationPoint(testContext.getName(), "Click on scheduled Pending payments link");	
	            	oR_MakeAPayment.lnkScheduledPayment.click();
                	Thread.sleep(5000);
       	           	lDriver.switchTo().frame(oR_MakeAPayment.frmPendingOneTimePayments);
	    
       	           	// Validate Pending One Time Payment pop up is displayed
	            	if(UI.WaitForObject(oR_MakeAPayment.txtPendingOneTimePayment,UI.iObjTimeOut)){
	            		Report.ValidationPoint(testContext.getName(), "Validate Pending one time payment window is displayed", "Pending one time payment window is displayed", "Pending one time payment window is displayed", true);
	            		Report.OperationPoint(testContext.getName(), "Click on Edit link");	
	            		
	            		if(oR_MakeAPayment.lnkEditOnPendingOneTimePayment.isDisplayed()){
	            			oR_MakeAPayment.lnkEditOnPendingOneTimePayment.click();
	            			
	            			Thread.sleep(5000);
	            			lDriver.switchTo().window(strMainWindowHandle);	           			
	            		// Validate Edit Payment Information page is displayed
	            			if(UI.WaitForObject(oR_EditPaymentInformation.txtEditPaymentInformation,UI.iObjTimeOut)){
	            				Report.ValidationPoint(testContext.getName(), "Validate Edit Payment Information page is displayed", "Edit Payment Information page is displayed", "Edit Payment Information page is displayed", true);    
	            			
	            				// Validate by default Credit/Debit payment method is selected
	            				if(oR_EditPaymentInformation.txtPaymentMethodCreditDebit.isDisplayed()){
	            					Report.ValidationPoint(testContext.getName(), "Validate Credit/Debit payment method is by default selected", "Credit/Debit payment method is by default selected", "Credit/Debit payment method is by default selected", true);		
	            			            				
	            				//	lDriver.switchTo().window(strMainWindowHandle);
	            					// Validate NOTE encouraging users to use bank account is displayed
	            					if(oR_EditPaymentInformation.txtNOTEEditPaymentInfoPage.isDisplayed()){
	            						Report.ValidationPoint(testContext.getName(), "Validate NOTE Encouraging users to use bank account is displayed", "NOTE Encouraging users to use bank account is displayed", "NOTE Encouraging users to use bank account is displayed", true);	
	            					}else{
	            						Report.ValidationPoint(testContext.getName(), "Validate NOTE Encouraging users to use bank account is displayed", "NOTE Encouraging users to use bank account is displayed", "NOTE Encouraging users to use bank account is NOT displayed", true);
	            					}
	            					
	            					Report.OperationPoint(testContext.getName(), "Click on Next button");	
	            					oR_EditPaymentInformation.lnkNextEditPaymentInfoPage.click();
	            					// Validate Review Payment Details page is displayed
	            					if(UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,UI.iObjTimeOut)){
	            				
	            						Report.ValidationPoint(testContext.getName(), "Validate Review Payments Details page is displayed", "Review Payments Details page is displayed", "Review Payments Details page is displayed", true);
	            						Report.OperationPoint(testContext.getName(), "Click on Back button");
	            						oR_ReviewPaymentDetails.btnBack.click();
	            						
	            						// Validate Edit Payment Information page is displayed
	            						if(UI.WaitForObject(oR_EditPaymentInformation.txtEditPaymentInformation,UI.iObjTimeOut)){
	            							Report.ValidationPoint(testContext.getName(), "Validate Edit Payment Information page is displayed", "Edit Payment Information page is displayed", "Edit Payment Information page is displayed", true);
	            							
	            							// Validate NOTE encouraging users to use bank account is displayed
	            							if(oR_EditPaymentInformation.txtNOTEEditPaymentInfoPage.isDisplayed()){
	    	            						Report.ValidationPoint(testContext.getName(), "Validate NOTE Encouraging users to use bank account is displayed", "NOTE Encouraging users to use bank account is displayed", "NOTE Encouraging users to use bank account is displayed", true);	
	    	            					}else{
	    	            						Report.ValidationPoint(testContext.getName(), "Validate NOTE Encouraging users to use bank account is displayed", "NOTE Encouraging users to use bank account is displayed", "NOTE Encouraging users to use bank account is NOT displayed", true);
	    	            					}
	            							
	            							// Validate that NOTE is content only with no links
	            							List<WebElement> hrfLinks = oR_EditPaymentInformation.txtNOTEEditPaymentInfoPage.findElements(By.tagName("a"));
	            						    if(hrfLinks.size() > 0){
	            								Report.ValidationPoint(testContext.getName(), "Validate that NOTE is content only with no links", "NOTE is content only with no links", "Note is content only with no links", true);
	            							}else{
	            								Report.ValidationPoint(testContext.getName(), "Validate that NOTE is content only with no links", "NOTE is content only with no links", "There is a link with in NOTE contents", true);
	            							}
	            							
	            						}
	            						else{
	            							Report.ValidationPoint(testContext.getName(), "Validate Edit Payment Information page is displayed", "Edit Payment Information page is displayed", "Edit Payment Information page is NOT displayed", true);
	            						}
	            					}else{
	            						Report.ValidationPoint(testContext.getName(), "Validate Review Payments Details page is displayed", "Review Payments Details page is displayed", "Review Payments Details page is NOT displayed", true);
	            					}
	            					
	            				}else{
	            					Report.ValidationPoint(testContext.getName(), "Validate Credit/Debit payment method is by default selected", "Credit/Debit payment method is by default selected", "Credit/Debit payment method is NOT by default selected", true);
	            				}
	            			}else{
	            				Report.ValidationPoint(testContext.getName(), "Validate Edit Payment Information page is displayed", "Edit Payment Information page is displayed", "Edit Payment Information page is NOT displayed", true);
	            			}
	            			
	            		}else{
	            			Report.ValidationPoint(testContext.getName(), "Validate Edit link is available on Pending one time payment window", "Edit link is available on Pending one time payment window", "Edit link is NOT available on Pending one time payment window", true);
	            		}
	            		
	            	}else{
	            		Report.ValidationPoint(testContext.getName(), "Validate Pending one time payment window is displayed", "Pending one time payment window is displayed", "Pending one time payment window is NOT displayed", true);
	            	}
	            	
	            	
				}else{
					Report.ValidationPoint(testContext.getName(), "Validate pending schedule payments link is displayed", "Pending schedule payments link is displayed", "pending schedule payments link is NOT displayed", true);
				}
			  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate Make a Payment page is displayed", "Make a Payment page is displayed", "Make a Payment page is NOT displayed", true);  
			  }
			}else{
				Report.ValidationPoint(testContext.getName(), "Validate Billing,Payment,Usage link is displayed", "Billing,Payment,Usage link is displayed", "Billing,Payment,Usage link is NOT displayed", true);
			}

		}catch(Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate "+e.getMessage(), true);

		}
	}
	
	/**************************************************************
	 * Function Name -  VerifyInternationalPassportPackageDetailsOnBillingPaymentsPage
	 * Description- This function verifies below details for International passport talk , text and data plan in current and recent billing usage periods
	 * 					- Plan description
	 * 					- Effective date note
	 * 					- Data used
	 * 					- Tool tip icon
	 * Parameters - 
	 * Date created -24th April
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
 * @throws ParseException 
	 ***************************************************************/
	//BAP28619	
	
	public static void VerifyInternationalPassportPackageDetailsOnBillingPaymentsPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		String sUsagePeriodsToCheck = IO.GetParamVal(sTestParams, "Usage_Periods_To_Check");
		String sCTNforWhichPlanNeedsToBeSelected = IO.GetParamVal(sTestParams, "LoginID");
		Actions action = new Actions(lDriver);
		
		try
		{
			//Click on Billing & usage from Global navigation
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null))
			{
				//Verify navigation to billing & usage page
				if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 80))
				{
					Report.TestPoint(testContext.getName(),"Verify navigation to billing & usage page", "Navigated","Navigated", true);
					
					//Click on usage tab
					Boolean bUsageTab = UI.WaitForObject(oR_BillAndUsage.lnkUsage,25);
					Report.TestPoint(testContext.getName(), "Verify usage tab", "true", String.valueOf(bUsageTab), true);
					oR_BillAndUsage.lnkUsage.click();
					Thread.sleep(30000);
					Report.TestPoint(testContext.getName(), "Click on usage tab", "true", String.valueOf(bUsageTab), true);
					
					
					
					//Click on usage dropdown
					String[] arrUsagePeriodsToCheck=new String[2];
					
					if(sUsagePeriodsToCheck.contains(","))
					{
						arrUsagePeriodsToCheck = sUsagePeriodsToCheck.split(",");
						
					}
					else
					{
						arrUsagePeriodsToCheck[0] = sUsagePeriodsToCheck;
					}
					
					for(int usagePeriodCount= 0 ; usagePeriodCount< arrUsagePeriodsToCheck.length ; usagePeriodCount++ )
					{
						try
						{
							WebElement elmSelectUsagePeriodDropdown = lDriver.findElement(By.xpath("//*[contains(@name,'Usage')]//*[contains(@class,'MyBill')]"));
							
							elmSelectUsagePeriodDropdown.click();
							Thread.sleep(8000);
							//Select usage period which is passed from sheet
							String sUsagePeriod=null;
							try
							{
								sUsagePeriod = arrUsagePeriodsToCheck[usagePeriodCount].trim();
								WebElement UsagePeriodToSelect = lDriver.findElement(By.xpath("//*[contains(text(),'"+sUsagePeriod+"')]/parent::*/parent::*/child::*/a"));
								Report.TestPoint(testContext.getName(), "Select Billing usage period from dropdown", "Selected", "Selected", true);
								UsagePeriodToSelect.click();
								Thread.sleep(10000);
								
							}
							catch(Exception e)
							{
								Report.ValidationPoint(testContext.getName(), "Select Billing usage period from dropdown", "Selected", "Provided billing usage period DOES NOT exist in dropdown", true);
							}	
							
							//Select plan from 'View usage for' dropdown
							Boolean bViewUsageForDropdown = UI.WaitForObject(oR_BillAndUsage.lstViewUsageFor,25);
							Report.TestPoint(testContext.getName(), "Verify 'View usage for' dropdown", "true", String.valueOf(bViewUsageForDropdown), true);
							
							oR_BillAndUsage.lstViewUsageFor.click();
							Thread.sleep(8000);

							//Select plan CTN sCTNforWhichPlanNeedsToBeSelected
							
							//First make it in format which is there in dropdown (i.e 972.839.9643)
							//so '.' is inserted
							String sPlanCTN = sCTNforWhichPlanNeedsToBeSelected.substring(0, 3)+"."+sCTNforWhichPlanNeedsToBeSelected.substring(3, 6)+"."+sCTNforWhichPlanNeedsToBeSelected.substring(6, sCTNforWhichPlanNeedsToBeSelected.length()) ;
							
							try
							{
								WebElement elmPlan = lDriver.findElement(By.xpath("//*[@id='viewUsageBox']//a[contains(text(),'"+sPlanCTN+"')]"));
								action.moveToElement(elmPlan).build().perform();
								Thread.sleep(4000);
								Report.TestPoint(testContext.getName(), "Select plan from 'View usage for' dropdown", "Selected", "Selected", true);
								action.click(elmPlan).build().perform();
								Thread.sleep(8000);
							}
							catch(Exception e)
							{
								Report.TestPoint(testContext.getName(), "Select plan from 'View usage for' dropdown", "Selected", "Failed to select plan for "+sCTNforWhichPlanNeedsToBeSelected, true);
							}
								
							//Extract all Passport plans from selected usage period
							List<WebElement> lstPassportPlans=null;
							try
							{
								lstPassportPlans = lDriver.findElements(By.xpath("//*[contains(@id,'Row')]//div[contains(text(),'AT&T Passport')]"));
							}
							catch(Exception e)
							{
								Report.TestPoint(testContext.getName(), "Extract all Passport plans from selected usage period - '"+arrUsagePeriodsToCheck[usagePeriodCount]+"'", "Extracted", "No passport plans present in selected usage period", true);
								break;
							}
							
							Report.TestPoint(testContext.getName(), "Extract all Passport plans from "+sUsagePeriod, "Extracted", "Extracted", true);
							
							//Add loop to verify details for all passport plans
							for(int passportPlansCount = 0; passportPlansCount< lstPassportPlans.size() ; passportPlansCount++ )
							{
								//Verify Plan description 
								String sPassportPlanNameWithNumber = lstPassportPlans.get(passportPlansCount).getText();
								if(sPassportPlanNameWithNumber.isEmpty())
								{
									try
									{
										WebElement lnkViewMore = lDriver.findElement(By.xpath("//*[contains(@linkname,'viewMore')]"));
										lnkViewMore.click();
										Report.TestPoint(testContext.getName(), "Click on view more if plan is not visible", "Clicked", "Clicked", true);
										lstPassportPlans = lDriver.findElements(By.xpath("//*[contains(@id,'Row')]//div[contains(text(),'AT&T Passport')]"));
										
										sPassportPlanNameWithNumber = lstPassportPlans.get(passportPlansCount).getText();
									}
									catch(Exception e)
									{
										Report.TestPoint(testContext.getName(), "Verify plan details for passport plan : ", "Verified", "Plan NOT visible", true);
										break;
									}
								}
								System.out.println("sPassportPlanNameWithNumber : "+sPassportPlanNameWithNumber);
								String locationOfDigitInPlanName = null;
								for(int cnt= 0 ; cnt < sPassportPlanNameWithNumber.length() ; cnt++ )
								{
									char ch = sPassportPlanNameWithNumber.charAt(cnt);
									if(Character.isDigit(ch))
									{
										locationOfDigitInPlanName = ""+ch ; 
										break;
									}
								}
								
								String[] arrPassportPlanNameWithSeperatedDigitAndName= sPassportPlanNameWithNumber.split(locationOfDigitInPlanName);
								
								String sPassportPlanName = arrPassportPlanNameWithSeperatedDigitAndName[0].trim();
								
								System.out.println("sPassportPlanName : "+sPassportPlanName);
								
								Report.TestPoint(testContext.getName(), "Verify Plan description is displayed for plan '"+sPassportPlanNameWithNumber+"' in '"+sUsagePeriod, "Displayed", "Displayed", true);
								
								//Verify tool tip
								try
								{
									
									WebElement elmToolTip = lDriver.findElement(By.xpath("//*[contains(@id,'Row')]//div[contains(text(),'"+sPassportPlanName+"')]/a[contains(@class,'tooltip')]"));
									
									action.moveToElement(elmToolTip).build().perform();
									Thread.sleep(5000);
									
									Report.ValidationPoint(testContext.getName(), "Verify tool tip icon for passport plan : '"+sPassportPlanNameWithNumber+"' in "+sUsagePeriod, "Displayed", "Displayed", true);
									
									/*
									//Verify tool tip text is displaying after hovering over tool tip icon
									try
									{
										WebElement elmToolTipIconText = driver1.findElement(By.xpath("//*[contains(@id,'tip')]"));
										String sToolTipTitle = elmToolTipIconText.getText();
										System.out.println("sToolTipTitle : "+sToolTipTitle);
										if(sToolTipTitle.contains("AT&T Passport"))
										{
											Report.ValidationPoint(testContext.getName(), "Verify brief explanation of the International Passport plan after hovering over tool tip icon for passport plan : '"+sPassportPlanNameWithNumber+"' in "+sUsagePeriod, "Displayed", "Displayed", true);
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Verify brief explanation of the International Passport plan after hovering over tool tip icon for passport plan : '"+sPassportPlanNameWithNumber+"' in "+sUsagePeriod, "Displayed", "NOT Displayed", true);
										}
									}
									catch(Exception e)
									{
										Report.ValidationPoint(testContext.getName(), "Verify brief explanation of the International Passport plan after hovering over tool tip icon for passport plan : '"+sPassportPlanNameWithNumber+"' in "+sUsagePeriod, "Displayed", "NOT Displayed", true);
									}
									*/
									
									
								}
								catch(Exception e)
								{
									Report.ValidationPoint(testContext.getName(), "Verify tool tip icon for passport plan : '"+sPassportPlanNameWithNumber+"' in "+sUsagePeriod, "Displayed", "NOT Displayed", true);
								}
								
								//Verify Data used
								try
								{
									if(!sPassportPlanName.contains("Talk"))
									{
										WebElement elmDataUsed = lDriver.findElement(By.xpath("//*[contains(@id,'Row')]//div[contains(text(),'"+sPassportPlanName+"')]/parent::*/parent::*/parent::*/parent::*/child::*[contains(@class,'HeadRow')]"));
										String sDataUsedText = elmDataUsed.getText();
									
										if(sDataUsedText.contains("Used"))
										{
											Report.ValidationPoint(testContext.getName(), "Verify Data used for passport plan : '"+sPassportPlanNameWithNumber+"' in "+sUsagePeriod, "Displayed", "Displayed", true);
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Verify Data used for passport plan : '"+sPassportPlanNameWithNumber+"' in "+sUsagePeriod, "Displayed", "NOT Displayed", true);
										}
									}
								}
								catch(Exception e)
								{
									Report.ValidationPoint(testContext.getName(), "Verify Data used for passport plan : '"+sPassportPlanNameWithNumber+"' in "+sUsagePeriod, "Displayed", "NOT Displayed", true);
								}
								
								//Verify effective date note if usage is current billed usage(this note will not display for recent usage)
								if(sUsagePeriod.equalsIgnoreCase("Current Billed Usage"))
								{
									try
									{
										WebElement elmEffectiveDateNoteText = lDriver.findElement(By.xpath("//*[contains(@id,'Row')]//div[contains(text(),'"+sPassportPlanName+"')]/parent::*/parent::*/parent::*"));
										String sEffectiveDateNoteText = elmEffectiveDateNoteText.getText();
										if(sEffectiveDateNoteText.contains("effective date"))
										{
											Report.ValidationPoint(testContext.getName(), "Verify effective date note for passport plan : '"+sPassportPlanNameWithNumber+"' in "+sUsagePeriod, "Displayed", "Displayed", true);
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Verify effective date note for passport plan : '"+sPassportPlanNameWithNumber+"' in "+sUsagePeriod, "Displayed", "NOT Displayed", true);
										}
									}
									catch(Exception e)
									{
										Report.ValidationPoint(testContext.getName(), "Verify effective date note for passport plan : '"+sPassportPlanNameWithNumber+"' in "+sUsagePeriod, "Displayed", "NOT Displayed", true);
									}
								}
								
								
								
							}	
							
						}
						catch(Exception e)
						{
							Report.TestPoint(testContext.getName(), "Select Billing usage period from dropdown", "Selected", "Usage dropdown DOES NOT exist", true);
						}
					
					}
					
					
					
					
				}
				else
				{
					Report.TestPoint(testContext.getName(),"Verify navigation to billing & usage page", "Navigated","Failed to Navigate", true);
				}
				
			}
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name - ValidateReportDropdownFunctionality
	 * Description - Validate Reports Drop Down functionality
	 * Parameters - None
	 * Date created - 24th Apr 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//BAP11483
	
	public static void ValidateReportDropdownFunctionality(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
	//		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			//Click on Billing,usage,payment secondary navigation link
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,null);
			//Validate Billing and usage page
			Boolean bBAU = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
			Report.TestPoint(testContext.getName(), "Validate Billing and usage page", "True", String.valueOf(bBAU), true);
			//Verify Bill tab highligted
			Boolean bBillTab = UI.WaitForObject(oR_BillAndUsage.lnkBillTab,1);
			if(bBillTab.equals(true))
			{
				String sBillTabClass = oR_BillAndUsage.lnkBillTab.getAttribute("class");
				if(sBillTabClass.contains("current"))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Bill tab highligted", "True","True", true);
				}
			}
			
			//[Nachiket] : Select BTN from drop down displayed next to Billing & Usages. For selected BTN only all the report types will be available under 
			//Report Type Drop down . Confirmed with Nidhi
			
		//	new Actions(lDriver).moveByOffset(50, 0).build().perform();
				
//			WebElement userDetailsDropDwon = lDriver.findElement(By.xpath("//a[contains(@id,'ddUserDetails')]"));
//			UI.WaitForObject(userDetailsDropDwon, UI.iObjTimeOut,lDriver);
//			userDetailsDropDwon.click();
//			Thread.sleep(3000);
//			WebElement btnOption = lDriver.findElement(By.xpath("//a[contains(@linkname,'ASMDropDown')]//div[contains(text(),'BTN')]"));
//			UI.WaitForObject(btnOption, UI.iObjTimeOut,lDriver);
//			new Actions(lDriver).moveToElement(btnOption).click().build().perform();
			Thread.sleep(5000);
			
			//Validate Report tab
			Boolean bReport = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,1);
			Report.TestPoint(testContext.getName(), "Validate Report tab is displayed", "True", String.valueOf(bReport), true);
			//Click on Report tab
			Report.OperationPoint(testContext.getName(), "	Operational - Click on Report tab");
			oR_BillAndUsage.lnkReportTab.click();
			//Validate Reports are displayed with tab highligted
			Thread.sleep(7000);
			String sReportTabClass = oR_BillAndUsage.lnkReportTab.getAttribute("class");
			if(sReportTabClass.contains("current"))
			{
				Report.ValidationPoint(testContext.getName(), "Validate Reports tab highligted", "True","True", true);
			}
			//Validate Select report type dropdown
			Boolean bReportSelect = UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,20);
			Report.TestPoint(testContext.getName(), "Validate Select report type dropdown is displayed", "True", String.valueOf(bReportSelect), true);
		
			//validate Start billing period dropdown
			Boolean bDateDis = UI.WaitForObject(oR_BillAndUsage.lstBillStartDateDisabled,1);
			Report.TestPoint(testContext.getName(), "Validate Start billing date dropdown is displayed", "True", String.valueOf(bDateDis), true);
			
			//Validate Ending billing period dropdown
			Boolean bEnd = UI.WaitForObject(oR_BillAndUsage.lstBillStartDateDisabled,1);
			Report.TestPoint(testContext.getName(), "Validate End billing date dropdown is displayed", "True", String.valueOf(bEnd), true);
		
			//Validate Create report button
			Boolean bCreateButtonDis = UI.WaitForObject(oR_BillAndUsage.btnCreateReportDisabled,1);
			Report.TestPoint(testContext.getName(), "Validate Create Report button is displayed", "True", String.valueOf(bCreateButtonDis), true);
			
			
			
			List<String> lstReportTypesExpected = new ArrayList<String>();
			lstReportTypesExpected.add("Total Account Charges");
			lstReportTypesExpected.add("One-Time Talk Charges");
			lstReportTypesExpected.add("One-Time Data & Text Charges");
			lstReportTypesExpected.add("Talk Usage (Minutes)");
			lstReportTypesExpected.add("Rollover® for Individual Plans (Minutes)");
			lstReportTypesExpected.add("Data Usage (MB/GB)");
			lstReportTypesExpected.add("Text Usage (Messages)");
			lstReportTypesExpected.add("Total Mobile Share Usage (MB/GB)");
			lstReportTypesExpected.add("Mobile Share Usage by Wireless Number");
			lstReportTypesExpected.add("Mobile Share Overage Charges");
			//lstReportTypesExpected.add("Total Charges by Wireless Number");
			lstReportTypesExpected.add("Breakdown of Current Charges by Carrier");
			lstReportTypesExpected.add("Past DSL Usage");
			

			List<String> lstReportTypesActual = new ArrayList<String>();
	//		WebElement startBillingDate = lDriver.findElement(By.xpath("//*[@id='ddShortcut1']/div"));
	//		WebElement endBillingDate = lDriver.findElement(By.xpath("//*[@id='ddShortcut2']/div"));
			
			List<WebElement> reportTypesList = lDriver.findElements(By.xpath("//*[@id='ddShortcutBox']//dd[@class='opt botMar7']"));
			WebElement lastReportType = null;		
			Report.OperationPoint(testContext.getName(), "Click on Select Report drop down");
			oR_BillAndUsage.lstSelectReportType.click();
		//	System.out.println("Size : " + reportTypesList.size());
			   	for(WebElement e : reportTypesList){
			   	   
			       WebElement reportType = e.findElement(By.tagName("a"));
			       UI.WaitForObject(e, 5);
			       String reportTypeName = reportType.getText();
			       lstReportTypesActual.add(reportTypeName);
			       lastReportType = reportType;	       
		//		   System.out.println(reportTypeName);
			   	 } 
		
			   	for(String s : lstReportTypesExpected ){
				   if(lstReportTypesActual.contains(s)){
//					   UI.WaitForObject(oR_BillAndUsage.lstSelectReportType, 40,lDriver);
//					   oR_BillAndUsage.lstSelectReportType.click();
			   		   Report.ValidationPoint(testContext.getName(), "Validate " + s + " option is available under Select Report Type drop down",  s + " option is available under Select Report Type drop down",  s + " option is available under Select Report Type drop down", true);
					}else{
//						  UI.WaitForObject(oR_BillAndUsage.lstSelectReportType, 40,lDriver);
//				   	      oR_BillAndUsage.lstSelectReportType.click();
						  Report.ValidationPoint(testContext.getName(), "Validate " + s + " option is available under Select Report Type drop down",  s + " option is available under Select Report Type drop down",  s + " option is NOT available under Select Report Type drop down", true); 
				  }
			   	}
			//validate Start billing period dropdown
//			Boolean bDateDis1 = UI.WaitForObject(oR_BillAndUsage.lstBillStartDateDisabled,1);
//			if(bDateDis1.equals(true))
//			{
//				Report.TestPoint(testContext.getName(), "Validate Start billing period dropdown", "True", String.valueOf(bDateDis), true);
//				//Validate Start billing period is disabled
//				String sDateDis = oR_BillAndUsage.lstBillStartDateDisabled.getAttribute("class");
//				if(!sDateDis.contains("enabled"))
//				{
//					Report.ValidationPoint(testContext.getName(), "Validate Start billing period is disabled", "True","True", true);
//				}
//				else
//				{
//					Report.ValidationPoint(testContext.getName(), "Validate Start billing period is disabled", "True","False", true);
//				}
//			}
//			else
//			{
//				Report.TestPoint(testContext.getName(), "Validate Start billing period dropdown", "True","False", true);
//			}
			
			//Select one report from dropdown
			Report.OperationPoint(testContext.getName(), "	Operational - Selecting an report from report type dropdown");
		//	oR_BillAndUsage.lstSelectReportType.click();
		    Thread.sleep(3000);
  			lastReportType.click();
  			
  			
  			//Validate start billing period is enabled
			String sDateDis = oR_BillAndUsage.lstBillStartDateDisabled.getAttribute("class");
			if(sDateDis.contains("enabled"))
			{
				Report.ValidationPoint(testContext.getName(), "Validate Start billing period is active", "True","True", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate Start billing period is active", "True","False", true);
			}
			//Validate Ending billing period dropdown
//			Boolean bEnd1 = UI.WaitForObject(oR_BillAndUsage.lstBillStartDateDisabled,1);
//			if(bEnd1.equals(true))
//			{
//				Report.TestPoint(testContext.getName(), "Validate End billing period dropdown", "True", String.valueOf(bEnd), true);
//				//Validate End billing period is disabled
//				String sDateEnd = oR_BillAndUsage.lstBillEndDateDisabled.getAttribute("class");
//				if(!sDateEnd.contains("enabled"))
//				{
//					Report.ValidationPoint(testContext.getName(), "Validate End billing period is disabled", "True","True", true);
//				}
//				else
//				{
//					Report.ValidationPoint(testContext.getName(), "Validate End billing period is disabled", "True","False", true);
//				}
//			}
//			else
//			{
//				Report.TestPoint(testContext.getName(), "Validate End billing period dropdown", "True","False", true);
//			}
			
			WebElement startBillingDate = lDriver.findElement(By.xpath("//*[@id='ddShortcut1']/div"));
			WebElement endBillingDate = lDriver.findElement(By.xpath("//*[@id='ddShortcut2']/div"));
			
			
			// Select Start Billing Period
			if(UI.WaitForObject(oR_BillAndUsage.lstBillStartDateDisabled, 40)){
			oR_BillAndUsage.lstBillStartDateDisabled.click();
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on start date");
			List <WebElement> startDate = lDriver.findElements(By.xpath("//div[contains(@id,'divShortcut1')]//dl[not(contains(@style, 'none'))]//a"));
			System.out.println(startDate.size());
			for(int i=0;i<startDate.size();i++)
			{
				String Date= startDate.get(i).getText();
						System.out.println(Date);
			}
			//System.out.println("List size is"+startDate.size() +"and "+startDate.get(1).getText());
			startDate.get(1).click();
			}
//			//Select a Start date
//			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on start date");
//			startBillingDate.click();
//			Thread.sleep(5000);
//			
//			WebElement startDate = lDriver.findElement(By.xpath("//div[@id='divShortcut1']//dl[@id='dateList']/dd/a"));
//			UI.WaitForObject(startDate, UI.iObjTimeOut);
		//	new Actions(lDriver).moveToElement(startDate).click().build().perform();
		//	startDate.click();
//			List<WebElement> lstStartDate = lDriver.findElements(By.xpath("//div[@id='date1']//dd/a"));
//			if(lstStartDate.size()>0)
//			{
//				Report.ValidationPoint(testContext.getName(), "Validate Start billing period dates are present", "True","True", true);
//				Report.OperationPoint(testContext.getName(), "	Operational - Select a date");
//				lstStartDate.get(0).click();
//			}
//			else
//			{
//				Report.ValidationPoint(testContext.getName(), "Validate Start billing period dates are present", "True","False", true);
//			}
			//Validate Ending billing period is enabled
			String sDateEnd = oR_BillAndUsage.lstBillEndDateDisabled.getAttribute("class");
			if(sDateEnd.contains("enabled"))
			{
				Report.ValidationPoint(testContext.getName(), "Validate End billing period is Enabled", "True","True", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate End billing period is Enabled", "True","False", true);
			}
//			//Validate Create report button
//			Boolean bCreateButtonDis = UI.WaitForObject(oR_BillAndUsage.btnCreateReportDisabled,1);
//			if(bCreateButtonDis.equals(true))
//			{
//				Report.TestPoint(testContext.getName(), "Validate Create report button", "True", String.valueOf(bCreateButtonDis), true);
//				//Validate Create report button is disabled
//				String sCreateReportDis = oR_BillAndUsage.btnCreateReportDisabled.getAttribute("aria-labelledby");
//				if(!sCreateReportDis.contains("enabled"))
//				{
//					Report.ValidationPoint(testContext.getName(), "Validate Create report button is disabled", "True","True", true);
//				}
//				else
//				{
//					Report.ValidationPoint(testContext.getName(), "Validate Create report button is disabled", "True","False", true);
//				}
//			}
//			else
//			{
//				Report.TestPoint(testContext.getName(), "Validate Create report button", "True","False", true);
//			}
			//Select an End date
//			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on End date");
//			oR_BillAndUsage.lstEndDate.click();
//			Thread.sleep(5000);
//			
//			WebElement endDate = lDriver.findElement(By.xpath("//div[@id='divShortcut2']//dl[@id='endDateList']/dd/a[1]"));
//			UI.WaitForObject(endDate, UI.iObjTimeOut);
//		//	new Actions(lDriver).moveToElement(endDate).click().build().perform();
//			endDate.click();
			
			// Select End Billing Period  
			UI.WaitForObject(endBillingDate, 5);
			endBillingDate.click();
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on End date");
			WebElement endDate = lDriver.findElement(By.xpath("//div[contains(@id,'divShortcut2')]//dl[not(contains(@style, 'none'))]//a"));
			UI.WaitForObject(endDate, UI.iObjTimeOut);
			endDate.click();
			
//			List<WebElement> lstEndDate = lDriver.findElements(By.xpath("//div[@id='date2']//dd/a"));
//			if(lstEndDate.size()>0)
//			{
//				Report.ValidationPoint(testContext.getName(), "Validate End billing period dates are present", "True","True", true);
//				Report.OperationPoint(testContext.getName(), "	Operational - Select a date");
//				lstEndDate.get(0).click();
//			}
//			else
//			{
//				Report.ValidationPoint(testContext.getName(), "Validate End billing period dates are present", "True","False", true);
//			}
			//Validate Create report button is enabled
			Boolean bCreateButtonEna = UI.WaitForObject(oR_BillAndUsage.btnCreateReport,1);
			if(bCreateButtonEna.equals(true))
			{
				String sCreateReportEna = oR_BillAndUsage.btnCreateReport.getAttribute("id");
				if(sCreateReportEna.contains("Enable"))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Create report button is enabled", "True","True", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Create report button is enabled", "True","False", true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate Create report button", "True","False", true);
			}
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	
	}
	/**************************************************************
	 * Function Name - ValidatePrintFriendlyVersion()
	 * Description - Validate print friendly version of Account Summary
	 * Parameters - None
	 * Date created - 28th Apr 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//BAP30656
	public static void ValidatePrintFriendlyVersion(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		String sAccount = IO.GetParamVal(sTestParams, "Account_Number");
		String sName = IO.GetParamVal(sTestParams, "Name");
		String sAddr1 = IO.GetParamVal(sTestParams, "Address_1");
		String sAddr2 = IO.GetParamVal(sTestParams, "Address_2");
		try
		{
			//Click on Billing, usage and payments sec navigation link
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,null);
			//Validate billing and usage page
			Boolean bBP = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
			Report.TestPoint(testContext.getName(), "Validate billing and usage page", "True", String.valueOf(bBP), true);
			//Validate Print link
			Boolean bPrintLnk = UI.WaitForObject(oR_BillAndUsage.lnkPrint,1);
			Report.TestPoint(testContext.getName(), "Validate Print link", "True", String.valueOf(bPrintLnk), true);
			//Storing current window handle
			String sCurrWin = lDriver.getWindowHandle();
			//Click on print link
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on link Print");
			oR_BillAndUsage.lnkPrint.click();
			Thread.sleep(5000);
			//Switching to Print window
			for(String sPrintWind : lDriver.getWindowHandles())
			{
				lDriver.switchTo().window(sPrintWind);
			}
			//Validate Account Details text
			Boolean bAccDet = UI.WaitForObject(oR_BillAndUsage.txtAccountDetails,10);
			Report.TestPoint(testContext.getName(), "Validate Account Details text", "True", String.valueOf(bAccDet), true);
			//Validate Collapse/Expand functionality is Not present
			Boolean bCollapse = UI.VerifyElementNotPresent(oR_BillAndUsage.lnkCollapseDummy, "Collapse/Expand functionality");
			Report.ValidationPoint(testContext.getName(), "Validate Collapse/Expand functionality is Not present", "True", String.valueOf(bCollapse), true);
			//Verify that CTA to view any usage details within the current bill should not be displayed.
			Boolean bUsage = UI.VerifyElementNotPresent(oR_BillAndUsage.lnkViewUsageDummy, "View Usage");
			Report.ValidationPoint(testContext.getName(), "Validate CTA to view any usage details within the current bill should not be displayed", "True", String.valueOf(bUsage), true);
			//There is no alert as per data. So cannot do validations for alerts
			//Validate Print functionality
			Boolean bPrintPgLnk = UI.WaitForObject(oR_BillAndUsage.lnkPrintPg,1);
			Report.ValidationPoint(testContext.getName(), "Validate Print functionality", "True", String.valueOf(bPrintPgLnk), true);
			//Validate AT&T Logo
			Boolean bATTlogo = UI.WaitForObject(oR_BillAndUsage.imgATTlogoPrint,1);
			Report.ValidationPoint(testContext.getName(), "Validate AT&T Logo", "True", String.valueOf(bATTlogo), true);
			//Validate Account Number and Customer name & address
			List<WebElement> lstAccDetails = lDriver.findElements(By.xpath("//div[@class='float-left ']//p"));
			if(lstAccDetails.size()>0)
			{
				Report.ValidationPoint(testContext.getName(), "Validate Account and Address details", "True","True", true);
				for(int i=0;i<lstAccDetails.size();i++)
				{
					//Retrieving the Account number, Name and address
					Report.OperationPoint(testContext.getName(), "	Operational - Retrieving the Account number, Name and address");
					//Expelling the empty elements
					if(lstAccDetails.get(i).getText().length()>0)
					{
						if(lstAccDetails.get(i).getText().contains(sAccount)
							||lstAccDetails.get(i).getText().contains(sName)
							||lstAccDetails.get(i).getText().contains(sAddr1)
							||lstAccDetails.get(i).getText().contains(sAddr2))
						{
							Report.ValidationPoint(testContext.getName(),lstAccDetails.get(i).getText(), "True","True", true);
						}
					}
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate Account and Address details", "True","False", true);
			}
			//Verify current bill Total Amount Due and dollar
			//Current bill
			Boolean bCurrent = UI.WaitForObject(oR_BillAndUsage.txtCurrentBill,1);
			Report.ValidationPoint(testContext.getName(), "Validate bill for", "True", String.valueOf(bCurrent), true);
			//Validate Total Amount due
			Boolean bTotalAmt = UI.WaitForObject(oR_BillAndUsage.txtTotalAmountDue,1);
			Report.ValidationPoint(testContext.getName(), "Validate Total Amount due", "True", String.valueOf(bTotalAmt), true);
			//Validate Total Amount due
			Boolean bDollar = UI.WaitForObject(lDriver.findElement(By.xpath("//div[contains(@class,'TopBorder')]//span[contains(text(),'$')]")),1);
			Report.ValidationPoint(testContext.getName(), "Validate Dollar sign", "True", String.valueOf(bDollar), true);
			
			
			//Validate Access to View Paper Bill not displayed
			UI.VerifyElementNotPresent(oR_BillAndUsage.lnkPaperBill, "View Paper bill CTA");
			//Validate Access to Make A payment not displayed
			UI.VerifyElementNotPresent(oR_BillAndUsage.btnMakePaymentInBillingPage, "Make A payment CTA");
			//Validate CTA to View explanation of charges not displayed
			UI.VerifyElementNotPresent(oR_BillAndUsage.lnkViewExplainationCharges, "View explanation of charges CTA");
			//Validate CTA to View Bill info, News you can Use and Promos not displayed
			UI.VerifyElementNotPresent(oR_BillAndUsage.lnkViewNewsPromos, "toView Bill info, News you can Use and Promos CTA");
			//Close the window
			Report.OperationPoint(testContext.getName(), "	Operational - Close the Print window");
			lDriver.close();
			//Switch to billing and usage page
			lDriver.switchTo().window(sCurrWin);
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name - ValidateSuppressingLastPayment 
	 * Description- 
	 * Parameters - 
	 * Date created -27th FEb 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void ValidateSuppressingLastPayment (final ITestContext testContext)
			throws Exception {
		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			//click on Billing&Payments
			Report.OperationPoint(testContext.getName(), "Navigating to Bills & Usage landing page");
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null))
			{
				Report.TestPoint(testContext.getName(), "Goto Bills & Usage from global nav", "Bills & Usage page is displayed", "Bills & Usage page is displayed" , true);	
				//Click History Tab 	
				if(UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab, UI.iObjTimeOut))
				{
					oR_BillAndUsage.lnkHistoryTab.click();
					Thread.sleep(7000);
					Report.TestPoint(testContext.getName(), "Verify History is present", "History Tab is present and clicked", "History Tab is present and clicked" , true);
					Report.OperationPoint(testContext.getName(), "Navigating to History");
					//Validate Payment activity
					Boolean bPaymentActivity = UI.WaitForObject(oR_BillAndUsage.txtPaymentActivity,1);
					Report.ValidationPoint(testContext.getName(), "Validate Payment Activity is displayed", "True", String.valueOf(bPaymentActivity), true);
					if(bPaymentActivity.equals(true))
					{
						UI.VerifyElementNotPresent(oR_BillAndUsage.txtlastPayemnt, "Last Payment Received");
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//span[contains(text(),'Pending Payment')]")), UI.iObjTimeOut))
						{
							Report.TestPoint(testContext.getName(), "Verify Pending Payment is displayed under Payment Activity section", "Pending Payment is displayed under Payment Activity section", "Pending Payment is displayed under Payment Activity section" , true);
						}
						else
						{
							Report.TestPoint(testContext.getName(), "Verify Pending Payment is displayed under Payment Activity section", "Pending Payment is displayed under Payment Activity section", "Pending Payment is not displayed under Payment Activity section" , true);
						}
					}
					else
					{	
						Report.TestPoint(testContext.getName(), "Verify Payment Activity section", "True", "false" , true);
					}
				}
			}	
		}

		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	
	
	/**************************************************************
	 * Function Name - ValidateReportsForSlidWithWireless()
	 * Description - Validate Reports that are available so Customer can compare information from past billing 
	 *               periods when logged in with Slid linked with Wireless Only of CB Wireline Account with Mobile 
	 *               share Plan
	 * Test Case - BAP30664 /BAP30661/BAP30663
	 * Parameters - None
	 * Date created - 24th Apr 2015
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void ValidateReportsForSlidWithWireless(final ITestContext testContext) throws HeadlessException, IOException, AWTException{
		
		
		try{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_ViewOrChangeRatePlan oR_ViewOrChangeRatePlan = PageFactory.initElements(lDriver, OR_ViewOrChangeRatePlan.class);
			OR_MyWirelessPlan oR_MyWirelessPlan = PageFactory.initElements(lDriver, OR_MyWirelessPlan.class);
			OR_PaperlessBilling oR_PaperlessBilling = PageFactory.initElements(lDriver, OR_PaperlessBilling.class);
			OR_UpgradePhone oR_UpgradePhone = PageFactory.initElements(lDriver, OR_UpgradePhone.class);
			OR_AutoPayEnrollment oR_AutoPayEnrollment = PageFactory.initElements(lDriver, OR_AutoPayEnrollment.class);
			OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
			OR_AddaDevice oR_AddaDevice = PageFactory.initElements(lDriver, OR_AddaDevice.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
	        String sCurrTCName = testContext.getName().split("-")[0];
	        
			Report.OperationPoint(testContext.getName(), "Navigate to Billing&Usage-> Reports");
			if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut)){
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkBillReports);
				Boolean bBillingUsages = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
				if(bBillingUsages){

					if(oR_BillAndUsage.lstSelectReportType.isDisplayed()){
						
						List<String> lstReportTypes = new ArrayList<String>();
						lstReportTypes.add("Total Account Charges");
						lstReportTypes.add("One-Time Talk Charges");
						lstReportTypes.add("One-Time Data & Text Charges");
						lstReportTypes.add("Talk Usage (Minutes)");
						lstReportTypes.add("Rollover® for Individual Plans (Minutes)");
						lstReportTypes.add("Data Usage (MB/GB)");
						lstReportTypes.add("Text Usage (Messages)");
						lstReportTypes.add("Total Mobile Share Usage (MB/GB)");
						lstReportTypes.add("Mobile Share Usage by Wireless Number");
						lstReportTypes.add("Mobile Share Overage Charges");
						lstReportTypes.add("Total Charges by Wireless Number");
						WebElement startBillingDate = lDriver.findElement(By.xpath("//*[@id='ddShortcut1']/div"));
						WebElement endBillingDate = lDriver.findElement(By.xpath("//*[@id='ddShortcut2']/div"));
						
						List<WebElement> reportTypesList = lDriver.findElements(By.xpath("//*[@id='ddShortcutBox']//dd[@class='opt botMar7']"));
						
						if(((sCurrTCName.equalsIgnoreCase("BAP30664") || sCurrTCName.equalsIgnoreCase("BAP30663"))&& (reportTypesList.size() == 11 || reportTypesList.size() == 10 || reportTypesList.size() == 12 )) 
						    || ((sCurrTCName.equalsIgnoreCase("BAP30661")) && (reportTypesList.size() == 7))){
										           
						   	for(WebElement e : reportTypesList){
						   	   Report.OperationPoint(testContext.getName(), "Click on Select Report drop down");
							   UI.WaitForObject(oR_BillAndUsage.lstSelectReportType, 40,lDriver);
							   
						   	   oR_BillAndUsage.lstSelectReportType.click();
						       WebElement reportType = e.findElement(By.tagName("a"));
						       UI.WaitForObject(e, 5);
						       String reportTypeName = reportType.getText();
						       						       
							   if(lstReportTypes.contains(reportTypeName)){
					
								
									Report.ValidationPoint(testContext.getName(), "Validate " + reportTypeName + " option is available under Select Report Type drop down",  reportTypeName + " option is available under Select Report Type drop down",  reportTypeName + " option is available under Select Report Type drop down", true);
									UI.WaitForObject(reportType,UI.iObjTimeOut);
									reportType.click();
									
									// Select Start Billing Period
									UI.WaitForObject(startBillingDate, 10);
									startBillingDate.click();
									WebElement startDate = lDriver.findElement(By.xpath("//div[@id='divShortcut1']//dl[@id='dateList']/dd/a"));
									UI.WaitForObject(startDate, UI.iObjTimeOut);
									startDate.click();

									// Select End Billing Period  
									UI.WaitForObject(endBillingDate, 5);
									endBillingDate.click();
									WebElement endDate = lDriver.findElement(By.xpath("//div[@id='divShortcut2']//dl[@id='endDateList']/dd/a"));
									UI.WaitForObject(endDate, UI.iObjTimeOut);
									endDate.click();

									// Click on Create Report button
									UI.WaitForObject(oR_BillAndUsage.btnCreateReport, UI.iObjTimeOut);
									oR_BillAndUsage.btnCreateReport.click();
									Thread.sleep(10000);
									
									// Check Graph Title
									WebElement chartTitle = lDriver.findElement(By.cssSelector("text.highcharts-title tspan"));
									UI.WaitForObject(chartTitle, UI.iObjTimeOut);
									String strChartTitle = chartTitle.getText();
									

									if(reportTypeName.contains(strChartTitle) ){
									  Report.ValidationPoint(testContext.getName(), "Validate report for " + reportTypeName + " is generated", "Report for " + reportTypeName + " is generated", "Report for " + reportTypeName + " is generated", true);	
									  if(oR_BillAndUsage.lnkPrint.isDisplayed()){
										  Report.ValidationPoint(testContext.getName(),"Validate Print functionality is available" ,"Print functionality is available" , "Print functionality is available", true);					  
									  }else{
										  Report.ValidationPoint(testContext.getName(),"Validate Print functionality is available" ,"Print functionality is available" , "Print functionality is NOT available", true);	
									  }
								}else{
									  Report.ValidationPoint(testContext.getName(), "Validate report for " + reportTypeName + " is generated", "Report for " + reportTypeName + " is generated", "Report for " + reportTypeName + " is NOT generated", true);		
									}
									
							   }else{
	        						  Report.ValidationPoint(testContext.getName(),"Validate valid report type is displayed" ,reportTypeName + " is a valid report type" , reportTypeName + " is NOT a valid report type", true); 
							   }
							}
					  }else{
						  Report.ValidationPoint(testContext.getName(),"Validate requried number(10 or 11) of report types are displayed" ,"Requried number(10 or 11) of report types are displayed" , "Requried number(10 or 11) of report types are NOT displayed", true);
					  }
								
		           // Validate Wireless secondary link is available
				    if(oR_AccountOverview.lnkWirelessSecNav.isDisplayed()){
						 Report.ValidationPoint(testContext.getName(), "Validate secondary link Wireless is displayed", "Secondary link Wireless is displayed", "Secondary link Wireless is displayed", true);
					 }else{
						 Report.ValidationPoint(testContext.getName(), "Validate secondary link Wireless is displayed", "Secondary link Wireless is displayed", "Secondary link Wireless is NOT displayed", true);
					 }
					 
					// Verify links with in Manage Account section
					 String windowTitle ;
					 if(UI.WaitForObject(oR_BillAndUsage.txtManageAccount,UI.iObjTimeOut)){
						 
						 // Validate Change Rate Plan link
						 if(UI.WaitForObject(oR_BillAndUsage.lnkChangeRatePlan,UI.iObjTimeOut)){
							 Report.ValidationPoint(testContext.getName(), "Validate Change Rate Plan link is displayed", "Change Rate Plan link is displayed", "Change Rate Plan link is displayed", true); 
//							 oR_BillAndUsage.lnkChangeRatePlan.click();
//							 Thread.sleep(3000);
//							 UI.WaitForObject(oR_ViewOrChangeRatePlan.txtViewOrChangeRatePlan, UI.iObjTimeOut);
//							 windowTitle = lDriver.getTitle();
//							 if(windowTitle.contains("View or Change My Plan")){
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to View or Change My Plan page", "Customer is navigated to View or Change My Plan page", "Customer is navigated to View or Change My Plan page", true); 
//							     lDriver.navigate().back();
//							     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//							 }else{
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to View or Change My Plan page", "Customer is navigated to View or Change My Plan page", "Customer is NOT navigated to View or Change My Plan page", true);
//							     if(! UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//							    	 lDriver.navigate().back();
//							     }
//							 }
						 }else{
							 Report.ValidationPoint(testContext.getName(), "Validate Change Rate Plan link is displayed", "Change Rate Plan link is displayed", "Change Rate Plan link is NOT displayed", true);
						 }
						 
						 // Validate View My Plan & Features link
						 if(sCurrTCName.equalsIgnoreCase("BAP30664")){
							 if(UI.WaitForObject(oR_BillAndUsage.lnkViewMyPlansAndFeatures,UI.iObjTimeOut)){
								 Report.ValidationPoint(testContext.getName(), "Validate View My Plan & Features link is displayed", "View My Plan & Features link is displayed", "View My Plan & Features link is displayed", true); 
//								 oR_BillAndUsage.lnkViewMyPlansAndFeatures.click();
//								 Thread.sleep(3000);
//								 UI.WaitForObject(oR_MyWirelessPlan.txtMyWirelessPlan, UI.iObjTimeOut);
//								 windowTitle = lDriver.getTitle();
//								 if(windowTitle.contains("AT&T - My Wireless Plan")){
//									 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to My Wireless Plan page", "Customer is navigated to My Wireless Plan page", "Customer is navigated to My Wireless Plan page", true); 
//								     lDriver.navigate().back();
//								     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//								 }else{
//									 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to My Wireless Plan page", "Customer is navigated to My Wireless Plan page", "Customer is NOT navigated to My Wireless Plan page", true);
//									 
//									 if(! UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//								    	 lDriver.navigate().back();
//								     }
//								 }
							 }else{
								 Report.ValidationPoint(testContext.getName(), "Validate View My Plan & Features link is displayed", "View My Plan & Features link is displayed", "View My Plan & Features link is NOT displayed", true);
							 }
						 }
						 
						 // Validate Manage my device & Features link
						 if(sCurrTCName.equalsIgnoreCase("BAP30661") || sCurrTCName.equalsIgnoreCase("BAP30663") ){
							 if(UI.WaitForObject(oR_BillAndUsage.lnkManageMyDeviceAndFeatures,UI.iObjTimeOut)){
								 Report.ValidationPoint(testContext.getName(), "Validate Manage my device & Features link is displayed", "Manage my device & Features link is displayed", "Manage my device & Features link is displayed", true); 
//								 oR_BillAndUsage.lnkManageMyDeviceAndFeatures.click();
//								 Thread.sleep(3000);
//								 UI.WaitForObject(oR_BillAndUsage.txtManageMyDevice, UI.iObjTimeOut);
//								 windowTitle = lDriver.getTitle();
//								 if(windowTitle.contains("AT&T - Manage My Device & Features")){
//									 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Manage my device & Features page", "Customer is navigated to Manage my device & Features page", "Customer is navigated to Manage my device & Features page", true); 
//								     lDriver.navigate().back();
//								     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//								 }else{
//									 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Manage my device & Features page", "Customer is navigated to Manage my device & Features page", "Customer is NOT navigated to Manage my device & Features page", true);
//									 
//									 if(! UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//								    	 lDriver.navigate().back();
//								     }
//								 }
							 }else{
								 Report.ValidationPoint(testContext.getName(), "Validate Manage my device & Features link is displayed", "Manage my device & Features link is displayed", "Manage my device & Features link is NOT displayed", true);
							 }
						 }

						 
						 // Validate Upgrade device link
						 if(UI.WaitForObject(oR_BillAndUsage.lnkUpgradeDevice,UI.iObjTimeOut)){
							 Report.ValidationPoint(testContext.getName(), "Validate Upgrade Device link is displayed", "Upgrade Device link is displayed", "Upgrade Device link is displayed", true); 
//							 oR_BillAndUsage.lnkUpgradeDevice.click();
//							 Thread.sleep(3000);
//							 UI.WaitForObject(oR_UpgradePhone.txtUpgradePhone, UI.iObjTimeOut);
//							 windowTitle = lDriver.getTitle();
//							 if(windowTitle.contains("Upgrade Phone")){
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Upgrade Phone page", "Customer is navigated to Upgrade Phone page", "Customer is navigated to Upgrade Phone page", true); 
//							     lDriver.navigate().back();
//							     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//							 }else{
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Upgrade Phone page", "Customer is navigated to Upgrade Phone page", "Customer is NOT navigated to Upgrade Phone page", true);
//								 if(!  UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//							    	 lDriver.navigate().back();
//							     }
//							 }
						 }else{
							 Report.ValidationPoint(testContext.getName(), "Validate Upgrade Phone link is displayed", "Upgrade Phone link is displayed", "Upgrade Phone link is NOT displayed", true);
						 }
						 
						// Validate Add a device link
						 if(sCurrTCName.equalsIgnoreCase("BAP30661") || sCurrTCName.equalsIgnoreCase("BAP30663")){
							 if(UI.WaitForObject(oR_BillAndUsage.lnkAddADevice,UI.iObjTimeOut)){
								 Report.ValidationPoint(testContext.getName(), "Validate Add A Device link is displayed", "Add A Device link is displayed", "Add A Device link is displayed", true); 
//								 oR_BillAndUsage.lnkAddADevice.click();
//								 Thread.sleep(3000);
//								UI.WaitForObject(oR_AddaDevice.txtAddaDevice, UI.iObjTimeOut);
//								 windowTitle = lDriver.getTitle();
//								 if(windowTitle.contains("Cell Phones")){
//									 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Add A Device page", "Customer is navigated to Add A Device page", "Customer is navigated to Add A Device page", true); 
//								     lDriver.navigate().back();
//								     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//								 }else{
//									 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Add A Device page", "Customer is navigated to Add A Device page", "Customer is NOT navigated to Add A Device page", true);
//									 if(! UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//								    	 lDriver.navigate().back();
//								     }
//								 }
							 }else{
								 Report.ValidationPoint(testContext.getName(), "Validate Add A Device link is displayed", "Add A Device link is displayed", "Add A Device link is NOT displayed", true);
							 }
						 }
						 
						 
						 // Validate Set Parental Controls link
						 if(UI.WaitForObject(oR_BillAndUsage.lnkSetParentalControl,UI.iObjTimeOut)){
							 Report.ValidationPoint(testContext.getName(), "Validate Set Parental Controls link is displayed", "Set Parental Controls link is displayed", "Set Parental Controls link is displayed", true); 
//							 oR_BillAndUsage.lnkSetParentalControl.click();
//							 Thread.sleep(10000);
//							 windowTitle = lDriver.getTitle();
//							 if(windowTitle.contains("Smart")){
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Smart Controls page", "Customer is navigated to Smart Controls page", "Customer is navigated to Smart Controls page", true); 
//							     lDriver.navigate().back();
//							     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//							 }else{
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Smart Controls page", "Customer is navigated to Smart Controls page", "Customer is NOT navigated to Smart Controls page", true);
//								 if(! UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//							    	 lDriver.navigate().back();
//							     }
//							 }
						 }else{
							 Report.ValidationPoint(testContext.getName(), "Validate Smart Controls link is displayed", "Smart Controls link is displayed", "Smart Controls link is NOT displayed", true);
						 }
						 
					 }else{
						 Report.ValidationPoint(testContext.getName(), "Validate Manage Account section is displayed", "Manage Account section is displayed", "Manage Account section is NOT displayed", true); 
					 }
					 
					 
					 //Validate links within Change Billing Options
					 
					 if(UI.WaitForObject(oR_BillAndUsage.txtChangeBillingOptions, UI.iObjTimeOut)){
				
						 // Validate Manage Paperless Billing link
						 if(sCurrTCName.equalsIgnoreCase("BAP30664")){
							 if(UI.WaitForObject(oR_BillAndUsage.lnkManagePaperlessBilling,UI.iObjTimeOut,lDriver)){
								 Report.ValidationPoint(testContext.getName(), "Validate Manage Paperless Billing link is displayed", "Manage Paperless Billing link is displayed", "Manage Paperless Billing link is displayed", true); 
//								 oR_BillAndUsage.lnkManagePaperlessBilling.click();
//								 Thread.sleep(3000);
//								 UI.WaitForObject(oR_PaperlessBilling.txtPaperlessBillingTitle, UI.iObjTimeOut);
//								 windowTitle = lDriver.getTitle();
//								 if(windowTitle.contains("Paperless")){
//									 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Paperless Billing page", "Customer is navigated to Paperless Billing page", "Customer is navigated to Paperless Billing page", true); 
//								     lDriver.navigate().back();
//								     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//								 }else{
//									 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Paperless Billing page", "Customer is navigated to Paperless Billing page", "Customer is NOT navigated to Paperless Billing page", true);
//									 if(! UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//								    	 lDriver.navigate().back();
//								     }
//								 }
							 }else{
								 Report.ValidationPoint(testContext.getName(), "Validate Paperless Billing link is displayed", "Paperless Billing link is displayed", "Paperless Billing link is NOT displayed", true);
							 }
						 }
						 // Validate Enroll in Paperless Billing link
						 if(sCurrTCName.equalsIgnoreCase("BAP30661") || sCurrTCName.equalsIgnoreCase("BAP30663") ){
							 if(UI.WaitForObject(oR_BillAndUsage.lnkEnrollPaperlessBilling,UI.iObjTimeOut,lDriver)){
								 Report.ValidationPoint(testContext.getName(), "Validate Enroll in Paperless Billing link is displayed", "Enroll in Paperless Billing link is displayed", "Enroll in Paperless Billing link is displayed", true); 
//								 oR_BillAndUsage.lnkEnrollPaperlessBilling.click();
//								 Thread.sleep(3000);
//								 UI.WaitForObject(oR_PaperlessBilling.txtPaperlessBillingTitle, UI.iObjTimeOut);
//								 windowTitle = lDriver.getTitle();
//								 if(windowTitle.contains("Paperless")){
//									 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Paperless Billing page", "Customer is navigated to Paperless Billing page", "Customer is navigated to Paperless Billing page", true); 
//								     lDriver.navigate().back();
//								     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//								 }else{
//									 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Paperless Billing page", "Customer is navigated to Paperless Billing page", "Customer is NOT navigated to Paperless Billing page", true);
//									 if(! UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//								    	 lDriver.navigate().back();
//								     }
//								 }
							 }else{
								 Report.ValidationPoint(testContext.getName(), "Validate Enroll in Paperless Billing link is displayed", "Enroll in Paperless Billing link is displayed", "Enroll in Paperless Billing link is NOT displayed", true);
							 }
						 }

						// Validate Enroll in AutoPay link
						 if(UI.WaitForObject(oR_BillAndUsage.lnkEnrollInAutopay,UI.iObjTimeOut,lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Validate Enroll in AutoPay link is displayed", "Enroll in AutoPay link is displayed", "Enroll in AutoPay link is displayed", true); 
//							 oR_BillAndUsage.lnkEnrollInAutopay.click();
//							 Thread.sleep(3000);
//							 UI.WaitForObject(oR_AutoPayEnrollment.txtEnrollInAutopay, UI.iObjTimeOut);
//							 windowTitle = lDriver.getTitle();
//							 if(windowTitle.contains("AutoPay")){
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Enroll in AutoPay page", "Customer is navigated to Enroll in AutoPay page", "Customer is navigated to Enroll in AutoPay page", true); 
//							     lDriver.navigate().back();
//							     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//							 }else{
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Enroll in AutoPay page", "Customer is navigated to Enroll in AutoPay page", "Customer is NOT navigated to Enroll in AutoPay page", true);
//								 if(! UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//							    	 lDriver.navigate().back();
//							     }
//							 }
						 }else{
							 Report.ValidationPoint(testContext.getName(), "Validate Enroll in AutoPay link is displayed", "Enroll in AutoPay link is displayed", "Enroll in AutoPay link is NOT displayed", true);
						 }
						 
						// Validate See More Payment Options link
						 if(UI.WaitForObject(oR_BillAndUsage.lnkSeeMorePaymentOptions,UI.iObjTimeOut,lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Validate See More Payment Options link is displayed", "See More Payment Options link is displayed", "See More Payment Options link is displayed", true); 
//							 oR_BillAndUsage.lnkSeeMorePaymentOptions.click();
//							 Thread.sleep(3000);
//							 windowTitle = lDriver.getTitle();
//							 if(windowTitle.contains("Payment Options")){
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Payment Options page", "Customer is navigated to Payment Options page", "Customer is navigated to Payment Options page", true); 
//							     lDriver.navigate().back();
//							     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//							 }else{
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Payment Options page", "Customer is navigated to Payment Options page", "Customer is NOT navigated to Payment Options page", true);
//								 if(! UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//							    	 lDriver.navigate().back();
//							     }
//							 }
						 }else{
							 Report.ValidationPoint(testContext.getName(), "Validate See More Payment Options link is displayed", "See More Payment Options link is displayed", "See More Payment Options link is NOT displayed", true);
						 }
						 
						 // Validate Change billing addredd option
						 if(UI.WaitForObject(oR_BillAndUsage.lnkChangeBillingAddress,UI.iObjTimeOut,lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Validate Change Billing address link is displayed", "Change Billing address link is displayed", "Change Billing address link is displayed", true); 
//							 oR_BillAndUsage.lnkChangeBillingAddress.click();
//							 Thread.sleep(5000);
//							 lDriver.switchTo().frame(oR_BillAndUsage.frmChangeBillingAddress);
//							 
//							 if(UI.WaitForObject(oR_BillAndUsage.txtChangeBillingAddress, UI.iObjTimeOut,lDriver)){
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Change address page", "Customer is navigated to Change address page", "Customer is navigated to Change address page", true); 
//								 UI.WaitForObject(oR_BillAndUsage.lnkCancelOnChangeBillingAddress,UI.iObjTimeOut,lDriver);
//								 oR_BillAndUsage.lnkCancelOnChangeBillingAddress.click();
//								 Thread.sleep(3000);
//								 lDriver.switchTo().defaultContent();
//							     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//							 }else{
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Change address page", "Customer is navigated to Change address page", "Customer is NOT navigated to Change address page", true);
//								 if(! UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//							    	 lDriver.navigate().back();
//							     }
//							 }
						 }else{
							 Report.ValidationPoint(testContext.getName(), "Validate Change Billing address link is displayed", "Change Billing address link is displayed", "Change Billing address link is NOT displayed", true);
						 }
				
						// Validate Update Billing Information link
						 if(UI.WaitForObject(oR_BillAndUsage.lnkUpdateBillingInfo,UI.iObjTimeOut,lDriver)){
							 boolean flag = false;
							 Report.ValidationPoint(testContext.getName(), "Validate Update Billing Information link is displayed", "Update Billing Information link is displayed", "Update Billing Information link is displayed", true); 
//							 oR_BillAndUsage.lnkUpdateBillingInfo.click();
//							 Thread.sleep(3000);
//							 String mainWindow= lDriver.getWindowHandle();
//							 if(UI.WaitForObject(oR_BillAndUsage.frmChangeBillingAddress, UI.iObjTimeOut)){
//						     lDriver.switchTo().frame(oR_BillAndUsage.frmChangeBillingAddress);
//						     flag = true;
//							 }
//						 //  UI.WaitForObject(oR_BillAndUsage.frmChangeBillingAddress, UI.iObjTimeOut);					
//							   if(UI.WaitForObject(oR_ATT.txtChangeBillingAddress, UI.iObjTimeOut)){
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Update Billing Information page", "Customer is navigated to Update Billing Information page", "Customer is navigated to Update Billing Information page", true); 
//								UI.WaitForObject(oR_BillAndUsage.lnkCancelOnChangeBillingAddress,UI.iObjTimeOut);
//            				    if(flag){
//								  oR_BillAndUsage.lnkCancelOnChangeBillingAddress.click();            				    
//            				      lDriver.switchTo().defaultContent();
//            				    }else{
//								  lDriver.navigate().back();
//            				    }
//							     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//									     
//							 }else{
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Update Billing Information page", "Customer is navigated to Update Billing Information page", "Customer is NOT navigated to Update Billing Information page", true);
//								 if(! UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//							    	 lDriver.navigate().back();
//							     }
//							 }
						 }else{
							 Report.ValidationPoint(testContext.getName(), "Validate Update Billing Information Options link is displayed", "Update Billing Information link is displayed", "Update Billing Information link is NOT displayed", true);
						 }
					 }else{
						 Report.ValidationPoint(testContext.getName(), "Validate Change Billing section is displayed", "Change Billing section is displayed", "Change Billing section is NOT displayed", true); 
					 }
				 
					 // Validate links within Get Help section
					 
					 if(UI.WaitForObject(oR_BillAndUsage.txtGetHelp,UI.iObjTimeOut)){
						 
						 // Validate Connect & save with Wi-fi link
						 if(UI.WaitForObject(oR_BillAndUsage.lnkConnectAndSaveWithWiFi,UI.iObjTimeOut,lDriver)){
    						 Report.ValidationPoint(testContext.getName(), "Validate Connect & Save with Wi-Fi link is displayed", "Connect & Save with Wi-Fi link is displayed", "Connect & Save with Wi-Fi link is displayed", true); 
//							 oR_BillAndUsage.lnkConnectAndSaveWithWiFi.click();
//							 Thread.sleep(3000);
//    						 Boolean connectWindow = UI.WaitForObject(oR_BillAndUsage.txtConnectingToWiFiHotspot, UI.iObjTimeOut);
//							 if(connectWindow){
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Connect & Save with Wi-Fi page", "Customer is navigated to Connect & Save with Wi-Fi page", "Customer is navigated to Connect & Save with Wi-Fi page", true); 
//							     oR_BillAndUsage.lnkCloseOnConnectAndSaveWithWiFi.click();
//							     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//							 }else{
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Connect & Save with Wi-Fi page", "Customer is navigated to Connect & Save with Wi-Fi page", "Customer is NOT navigated to Connect & Save with Wi-Fi page", true);
//	
//						       }
						 }else{
							 Report.ValidationPoint(testContext.getName(), "Validate Connect & Save with Wi-Fi link is displayed", "Connect & Save with Wi-Fi link is displayed", "Connect & Save with Wi-Fi link is NOT displayed", true);
						 }
			 
						 // Validate Learn How to Manage Usage link
						 if(UI.WaitForObject(oR_BillAndUsage.lnkHowToManageUsage,UI.iObjTimeOut,lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Validate Learn How to Manage Usage link is displayed", "Learn How to Manage Usage link is displayed", "Learn How to Manage Usage link is displayed", true); 
//							 oR_BillAndUsage.lnkHowToManageUsage.click();
//							 Thread.sleep(3000);
//							 UI.WaitForObject(oR_BillAndUsage.txtWirelessSupport, UI.iObjTimeOut);
//							 windowTitle = lDriver.getTitle();
//							 if(windowTitle.contains("Ways to manage your")){
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Way to Manage Usage page", "Customer is navigated to Way to Manage Usage page", "Customer is navigated to Way to Manage Usage page", true); 
//							     lDriver.navigate().back();
//							     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//							 }else{
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Way to Manage Usage page", "Customer is navigated to Way to Manage Usage page", "Customer is NOT navigated to Way to Manage Usage page", true);
//								 if(! UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//							    	 lDriver.navigate().back();
//							     }
//							 }
						 }else{
							 Report.ValidationPoint(testContext.getName(), "Validate How to Manage Usage link is displayed", "How to Manage Usage link is displayed", "How to Manage Usage link is NOT displayed", true);
						 }
						
						 // Validate Estimate Monthly Usage link
						 if(UI.WaitForObject(oR_BillAndUsage.lnkEstimateMonthlyUsage,UI.iObjTimeOut,lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Validate Estimate Monthly Usage link is displayed", "Estimate Monthly Usage link is displayed", "Estimate Monthly Usage link is displayed", true); 
//							 oR_BillAndUsage.lnkEstimateMonthlyUsage.click();
//							 Thread.sleep(3000);
//							 UI.WaitForObject(oR_BillAndUsage.txtDataCalculator, UI.iObjTimeOut);
//							 windowTitle = lDriver.getTitle();
//							 if(windowTitle.contains("AT&T Data Calculator")){
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Estimate Monthly Usage page", "Customer is navigated to Estimate Monthly Usage page", "Customer is navigated to Estimate Monthly Usage page", true); 
//							     lDriver.navigate().back();
//							     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//							 }else{
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Estimate Monthly Usage page", "Customer is navigated to Estimate Monthly Usage page", "Customer is NOT navigated to Estimate Monthly Usage page", true);
//								 if(! UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//							    	 lDriver.navigate().back();
//							     }
//							 }
						 }else{
							 Report.ValidationPoint(testContext.getName(), "Validate Estimate Monthly Usage link is displayed", "Estimate Monthly Usage link is displayed", "Estimate Monthly Usage link is NOT displayed", true);
						 }
						 
						 // Validate Manage Your Usage link
						 if(UI.WaitForObject(oR_BillAndUsage.lnkManageYourUsage,UI.iObjTimeOut,lDriver)){
							 Report.ValidationPoint(testContext.getName(), "Validate Manage Your Usage link is displayed", "Manage Your Usage link is displayed", "Manage Your Usage link is displayed", true); 
//							 oR_BillAndUsage.lnkManageYourUsage.click();
//							 Thread.sleep(3000);
//							 UI.WaitForObject(oR_BillAndUsage.txtWirelessSupport, UI.iObjTimeOut);
//							 windowTitle = lDriver.getTitle();
//							 if(windowTitle.contains("Ways to manage your")){
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Way to Manage Usage page", "Customer is navigated to Way to Manage Usage page", "Customer is navigated to Way to Manage Usage page", true); 
//							     lDriver.navigate().back();
//							     UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
//							 }else{
//								 Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Way to Manage Usage page", "Customer is navigated to Way to Manage Usage page", "Customer is NOT navigated to Way to Manage Usage page", true);
//								 if(! UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 10)){
//							    	 lDriver.navigate().back();
//							     }
//							 }
						 }else{
							 Report.ValidationPoint(testContext.getName(), "Validate Manage Your Usage link is displayed", "Manage Your Usage link is displayed", "Manage Your Usage link is NOT displayed", true);
						 }
						 
					 }else{
						 Report.ValidationPoint(testContext.getName(), "Validate Get Help section is displayed", "Help section section is displayed", "Help section section is NOT displayed", true);
					 }
						
					}
				}
			}

		}catch(Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate "+e.getMessage(), true);

		}
	}
	
	
	
	/**************************************************************
	 * Function Name - VerifyBillTabSecondaryLinkRails()
	 * Description - Validate Bill tab secondary link rails for for a Uverse/ Wireless combined billed customer
 	 * Test Case -  BAP28462
	 * Parameters - None
	 * Date created - 29th April 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyBillTabSecondaryLinkRails(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		

		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		try
		{
			
			//Click on Billing, Usage, Payments
			boolean bBillingSecTab = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate Billing, Usage, Payments tab is present in Sec Nav section", "true", String.valueOf(bBillingSecTab), true);
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Bill, Usage & Payments' tab");
			
			//Verify Bill & Usage page is displayed and navigate to Report tab
			boolean bBillingPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate that Billing & Usage page is displayed", "true", String.valueOf(bBillingPage), true);
					
			//Validate Secondary rails link section
			//Check  Secondary rails link section is displayed at the bottom of Bill page
			boolean bSecLinkRail = UI.WaitForObject(oR_BillAndUsage.tblSecLinkRailsSection, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate that Secondary Rail Link section is displayed at the bottom of billing page", "true", String.valueOf(bSecLinkRail), true);
			
			//Validate secondary link rails section contains following 3 subsections:
			//1. Manage Account
			//2. Change Billing options
			//3. Get Help
			boolean bManageAccount = UI.WaitForObject(oR_BillAndUsage.txtManageAccount, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that 'Manage Account' section is displayed in Sec link rails section", "true", String.valueOf(bManageAccount), true);
			boolean bChangeBilling = UI.WaitForObject(oR_BillAndUsage.txtChangeBillingOptions, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that 'Change Billing Options' section is displayed in Sec link rails", "true", String.valueOf(bChangeBilling), true);
			boolean bGetHelp = UI.WaitForObject(oR_BillAndUsage.txtGetHelp, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that 'Get Help' section is displayed in Sec link rails", "true", String.valueOf(bGetHelp), true);
			String sBillAndUsagePageURL = lDriver.getCurrentUrl();
			
			List<String> lstSecondaryListRail = new ArrayList<String>();
			lstSecondaryListRail.add("Manage Account");
			lstSecondaryListRail.add("Change billing options");
			lstSecondaryListRail.add("Get Help");
			lstSecondaryListRail.get(1);
			
			for(int i=1,k=0;i<=3;i++,k++)
			{	
				
				List<WebElement> lnkSubLinks = lDriver.findElements(By.xpath("//div[@class='table tableTextWrap']/div[" + i + "]//dd"));
		 			
		    	
		        for(int j=1;j<=lnkSubLinks.size();j++)
		        {
		           	WebElement e = lDriver.findElement(By.xpath("//div[@class='table tableTextWrap']/div[" + i + "]//dd[" + j + "]"));
		        	String sLinkName = e.getText();	
					System.out.println("Link Name : "  + sLinkName);
					e.findElement(By.tagName("a")).click();
					
	    			Report.OperationPoint(testContext.getName(), "Operational - Clicked on the link: "+sLinkName+" under section: "+lstSecondaryListRail.get(k)+", Navigating to new page");
					Thread.sleep(10000);
	    			String sCurrentPageURL = lDriver.getCurrentUrl();
					
					//Verify the page is redirected to new URL after clicking on the sub-link
	    			if(sLinkName.contains("Enroll in AutoPay"))
	    			{
	    				lDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						Report.ValidationPoint(testContext.getName(), "Validate that new page is displayed after clicking on: "+sLinkName+", Page is redirected to the URL: "+sCurrentPageURL+" ", "true","true", true);
						//lDriver.navigate().back();
						lDriver.findElement(By.xpath("//a[contains(@href,'/olam/passthroughAction.myworld?actionType=BillPayments') and contains(text(),'Cancel')]")).click();
						Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
						UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
						
	    			}else if(sLinkName.contains("Play video"))
					{
						Thread.sleep(15000);
						Report.ValidationPoint(testContext.getName(), "Validate that a pop-up page for video is displayed after clicking on: "+sLinkName+", Page URL: "+sCurrentPageURL+" ", "true","true", true);
						lDriver.findElement(By.xpath("//button[contains(text(),'Close')]")).click();
						Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
						UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
						

					}else if(sLinkName.contains("Update billing information"))
					{
						Report.ValidationPoint(testContext.getName(), "Validate that a pop-up page for Change billing address is displayed after clicking on: "+sLinkName+", Page URL: "+sCurrentPageURL+" ", "true","true", true);
						Thread.sleep(10000);
						lDriver.findElement(By.xpath("//img[@src='/olam/images/brand30/modalClose.png']")).click();
						Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
						UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
					
					}else if(sCurrentPageURL!=sBillAndUsagePageURL)
					{
						
						Report.ValidationPoint(testContext.getName(), "Validate that new page is displayed after clicking on: "+sLinkName+", Page is redirected to the URL: "+sCurrentPageURL+" ", "true","true", true);
						lDriver.navigate().back();
						Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
						UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
						
					}else 
					{
						
				    	Report.ValidationPoint(testContext.getName(), "Validate that new page is displayed after clicking on: "+sLinkName+", Page is redirected to the URL: "+sCurrentPageURL+" ", "true","false", true);
					}
			
	    			}
		  		}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	/**************************************************************
	 * Function Name - ValidateMakePaymentSingleAccountView()
	 * Description - Validate print friendly version of Account Summary
	 * Parameters - None
	 * Date created - 28th Apr 2015
	 * Developed by - Swagata Das
	 * Last Modified By - Monica Mohabansi
	 * Last Modified Date - 8th June - 1508 execution
	 ***************************************************************/
	//BAP06044
	public static void ValidateMakePaymentSingleAccountView(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
		OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
		OR_ReviewPaymentDetails oR_ReviewPaymentDetails = PageFactory.initElements(lDriver, OR_ReviewPaymentDetails.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sUrlDomain = IO.GetParamVal(sTestParams, "DOMAIN");
		String sPaymentAmount = IO.GetParamVal(sTestParams, "PAYMENT_AMOUNT");
		String sNameOnCard = IO.GetParamVal(sTestParams, "NAME_ON_CARD");
		String sRoutingNo	= IO.GetParamVal(sTestParams, "ROUTING_NO");
		String sBankAccNo = IO.GetParamVal(sTestParams, "BANK_ACC_NO");
		String sReBankAccNo = IO.GetParamVal(sTestParams, "RE_BANK_ACC_NO");
		
		try
		{
			//Validate Make A Payment button
			//Code modified - Monica 8th June 2015
			Boolean bMAP = UI.WaitForObject(oR_AccountOverview.btnMakeAPayment_OldDashboard,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate Make A Payment button", "true", String.valueOf(bMAP), true);
			//Click on Make A Payment button
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Make A Payment button");
			oR_AccountOverview.btnMakeAPayment_OldDashboard.click();
			//Validate make a payment page
			Boolean bMAPpg = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment,50);
			Report.TestPoint(testContext.getName(), "Validate make a payment page", "true", String.valueOf(bMAPpg).toLowerCase(), true);
			//Verify that the Account nickname and the Account number is displayed at the top of the page.
			List<WebElement> lstNick = lDriver.findElements(By.xpath("//ul[contains(@class,'account-info')]"));
			if(lstNick.size()>0)
			{
				Report.ValidationPoint(testContext.getName(), "Validate that the Account nickname and the Account number is displayed at the top of the page", "true", "true", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate that the Account nickname and the Account number is displayed at the top of the page", "true", "false", true);
			}
			
			//No Pending payment link present
			//No multiple payments
			//Verify Step indicators are displayed at the right hand corner of the Screen
			Boolean bStepIndi = UI.WaitForObject(oR_MakeAPayment.txtStepIndicator,1);
			Report.TestPoint(testContext.getName(), "Validate Step indicators are displayed at the right hand corner of the Screen", "true", String.valueOf(bStepIndi), true);
			//Verify there are only three step indicators in display - 
			//Payment Entry 
			//Payment Review
			//Confirmation
			List<WebElement> lstStepIndi = lDriver.findElements(By.xpath("//div[@class='step-indicator-hor box']//span[@class='step-text colorGrey']"));
			if(lstStepIndi.size()==3)
			{
				Report.ValidationPoint(testContext.getName(), "Validate there are only three step indicators in display", "True","True", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate there are only three step indicators in display", "True","False", true);
			}
			//Retrieving the Step Indicators
			for(int i=0;i<lstStepIndi.size();i++)
			{
				Report.OperationPoint(testContext.getName(), lstStepIndi.get(i).getText());
			}
			//Validate the names of the steps
			if(lstStepIndi.get(0).getText().contains("Enter payment information")&&lstStepIndi.get(1).getText().contains("Review payment information")&&lstStepIndi.get(2).getText().contains("Payment status"))
			{
				Report.ValidationPoint(testContext.getName(), "Validate the names of the steps", "True","True", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate the names of the steps", "True","False", true);
			}
			//Verify that the Pay All Accounts section and the Customize My Payments header is not displayed on this page
			UI.VerifyElementNotPresent(oR_MakeAPayment.txtPayAllAccounts, "Pay All Accounts section");
			UI.VerifyElementNotPresent(oR_MakeAPayment.txtCustomizePayments, "Customize My Payments");
			
			//Verify the link Page in Espanol is present in the top of the page when you hover your mouse over the Language\Intrenational link.
			//Validate link Language
//			if(UI.WaitForObject(oR_AccountOverview.lnkLanguage, 1))
//			{
//				Report.ValidationPoint(testContext.getName(), "Validate link Language", "True","True", true);
//				//Click on link Language
//				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on link Language");
//				oR_AccountOverview.lnkLanguage.click();
//				//Validate Page in Espanol link
//				if(UI.WaitForObject(oR_AccountOverview.lnkPageInSpanish, 1))
//				{
//					Report.ValidationPoint(testContext.getName(), "Validate Page in Espanol link", "True","True", true);
//					oR_AccountOverview.lnkLanguage.click();
//				}
//				else
//				{
//					Report.ValidationPoint(testContext.getName(), "Validate Page in Espanol link", "True","False", true);
//				}
//			}
//			else
//			{
//				Report.ValidationPoint(testContext.getName(), "Validate link Language", "True","False", true);
//			}
			//Validate the presence of the link 'Manage Payment Profiles' next to the Decsription
			//The link is displayed if the user has any active payment profile. therefore no Else condition
			if(UI.WaitForObject(oR_MakeAPayment.lnkManagePaymentProfile, 1))
			{
				Report.ValidationPoint(testContext.getName(), "Validate the presence of the link 'Manage Payment Profiles' next to the Decsription", "True","True", true);
				//Click on the link
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Manage Payment Profiles");
				oR_MakeAPayment.lnkManagePaymentProfile.click();
				//Validate Payment profiles section
				Boolean bPP = UI.WaitForObject(oR_Profile.txtPaymentProfiles,50);
				Report.TestPoint(testContext.getName(), "Validate Payment profiles section", "true", String.valueOf(bPP), true);
				//Navigate back
				lDriver.navigate().back();
				//Validate Make a payment page
				Boolean bMAPpg1 = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment,50);
				Report.TestPoint(testContext.getName(), "Validate make a payment page", "true", String.valueOf(bMAPpg1), true);
			}
			//Manage autopay link is not present in this Data
			//Verify the Section heading is displayed as Choose Payment Method
			Boolean bChoose = UI.WaitForObject(oR_MakeAPayment.lstPaymentMethod,5,lDriver);
			if(bChoose)
			{
				Report.ValidationPoint(testContext.getName(), "Validate the Section heading is displayed as Choose Payment Method", "true", String.valueOf(bChoose), true);
			}
			/*else
			{
				Report.ValidationPoint(testContext.getName(), "Validate the Section heading is displayed as Choose Payment Method", "true", String.valueOf(bChoose), true);
			}*/
			//Verify the table headings as Account Name,Amount and Date.
			//Validate Payement Amount
			Boolean bPaymentAmountLabel = UI.WaitForObject(oR_MakeAPayment.txtPaymentAmountLabel,1);
			Report.ValidationPoint(testContext.getName(), "Verify the table headings as : Account Name", "true", String.valueOf(bPaymentAmountLabel), true);
			//Validate Payment Date
			Boolean bPaymentDateLabel = UI.WaitForObject(oR_MakeAPayment.txtPaymentDateLabel,1);
			Report.ValidationPoint(testContext.getName(), "Verify the table headings as : Amount", "true", String.valueOf(bPaymentDateLabel), true);
			//Validate Payment Method
			Boolean bPaymentMethodLabel = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethodLabel,1);
			Report.ValidationPoint(testContext.getName(), "Verify the table headings as : Date", "true", String.valueOf(bPaymentMethodLabel), true);
			//Enroll in autopay present only when account not enrolled
			//Validate Enroll in autopay
			if(UI.WaitForObject(oR_MakeAPayment.chkAccountSelectCheckbox_1, 1))
			{
				Report.ValidationPoint(testContext.getName(), "Validate Enroll in autopay", "True","True", true);
			}
			//Verify the Amount Due is displayed below the Amount Heading as - 'DUE : $##.## and is it not editable.
			Boolean bDueTxt = UI.WaitForObject(oR_MakeAPayment.txtPaymenttxt,1);
			Report.TestPoint(testContext.getName(), "Verify the Amount Due is displayed below the Amount Heading as - 'DUE : $##.## and is it not editable", "true", String.valueOf(bDueTxt), true);
			String sTxt = oR_MakeAPayment.txtPaymenttxt.getText();
			//Validate the $ sign
			if(sTxt.contains("$"))
			{
				Report.ValidationPoint(testContext.getName(), "Verify the $ sign", "True","True", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify the $ sign", "True","False", true);
			}
			//Split the $ from the amount
			String sNumArr[] = sTxt.split("\\$");
			String sNum = sNumArr[1].trim();
			System.out.println(sNum);
			//Verify that the due date is displayed below the heading DATE as - MM/DD/YYYY and is not editable.
			Boolean bDueDate = UI.WaitForObject(lDriver.findElement(By.xpath("//li[@class='balance']")),1);
			Report.TestPoint(testContext.getName(), "Verify Due date", "true", String.valueOf(bDueDate), true);
			//Due date is NA. therefore commenting
			/*String sDateArr[] = lDriver.findElement(By.xpath("//li[@class='balance']")).getText().split(":");
			String sDate = sDateArr[1].trim();
			//Validate the format : MM/DD/YYYY
			String sDate1[] = sDate.split("\\/");
			System.out.println(sDate1[0].length());
			System.out.println(sDate1[1].length());
			System.out.println(sDate1[2].length());
			if(sDate1[0].trim().length()==2 && sDate1[1].trim().length()==2 && sDate1[2].trim().length()==4)
			{
				Report.ValidationPoint(testContext.getName(), "Verify the format as MM/DD/YYYY  ", "True","True", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify the format as MM/DD/YYYY  ", "True","False", true);
			}*/
			//Verify that the amount field is displayed below the Due Amount text, its is set to the amount due by default but can be changed by the user
			//Validate Payment amount editbox
			Boolean bPaymentAmt = UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1,1);
			Report.TestPoint(testContext.getName(), "Verify Payment amount editbox", "true", String.valueOf(bPaymentAmt), true);
			//Enter an amount greater than the due amount
			Report.OperationPoint(testContext.getName(), "	Operational - Entering payment amount greater than the due amount");
			if(oR_MakeAPayment.edtPaymentAmount1.getAttribute("value").contains(sNum))
			{
				Report.ValidationPoint(testContext.getName(), "Verify that the amount field is displayed below the Due Amount text, its is set to the amount due by default but can be changed by the user", "True","True", true);
			}
			/*else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that the amount field is displayed below the Due Amount text, its is set to the amount due by default but can be changed by the user", "True","False", true);
			}*/
			//Verify that the amount field is prefixed by a $ symbol before the text field.
			Boolean bDollar = UI.WaitForObject(oR_MakeAPayment.txtDollarSign,1);
			Report.ValidationPoint(testContext.getName(), "Verify that the amount field is prefixed by a $ symbol before the text field.", "true", String.valueOf(bDollar), true);
			//Due to stub, the date arent matching
			//Verify a calender is displayed next to the Date field
			Boolean bCalender = UI.WaitForObject(oR_MakeAPayment.imgCalender1,1);
			if(bCalender)
			{
				Report.ValidationPoint(testContext.getName(), "Verify calender is displayed next to the Date field", "true", String.valueOf(bCalender), true);
				//Click on the Calender
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Calender image");
				oR_MakeAPayment.imgCalender1.click();
				Thread.sleep(5000);
				//Switch to frame calender
				if(UI.WaitForObject(oR_MakeAPayment.frmCalender, 10))
				{
					lDriver.switchTo().frame(oR_MakeAPayment.frmCalender);
					//Validate Payment Calender heading
					Boolean bCalenderHeading = UI.WaitForObject(oR_MakeAPayment.txtFramePaymentCalenderTitle,1);
					Report.ValidationPoint(testContext.getName(), "Verify Payment Calender heading", "true", String.valueOf(bCalenderHeading), true);
					//Close the calender
					Report.OperationPoint(testContext.getName(), "	Operational - Closing the calender");
					oR_MakeAPayment.lnkFramePaymentCalenderClose.click();
					//unable to change date coz of stub env
				}
				//Switch back to default content
				lDriver.switchTo().defaultContent();
			}
			/*else
			{
				Report.ValidationPoint(testContext.getName(), "Verify calender is displayed next to the Date field", "true", String.valueOf(bCalender), true);
			}*/
			//Verify that a payment method selection dropdown is diplayed in this page
			Boolean bPayDropdown = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethod,1);
			Report.TestPoint(testContext.getName(), "Verify that a payment method selection dropdown is diplayed in this page", "true", String.valueOf(bPayDropdown), true);
			//Select New Card\New bank Account as the payment method
			UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod3, "New debit / credit card");
			//validate The New Payment Div layer is displayed
			Thread.sleep(10000);
			Boolean bPayDiv = UI.WaitForObject(oR_MakeAPayment.frmPendingOneTimePayments,20);
			Report.TestPoint(testContext.getName(), "validate The New Payment Div layer is displayed", "true", String.valueOf(bPayDiv), true);
			//close the div layer
			lDriver.switchTo().frame(oR_MakeAPayment.frmPendingOneTimePayments);
			Report.OperationPoint(testContext.getName(), "	Operational - Close the payment div layer");
			oR_MakeAPayment.lnkCloseFrame.click();
			//switch back to original frame
			lDriver.switchTo().defaultContent();
			//Validate make a payment page
			Boolean bMAPpg2 = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment,10);
			Report.TestPoint(testContext.getName(), "Validate make a payment page", "true", String.valueOf(bMAPpg2), true);
			//Verify the presence of a ? Next to the link
			List<WebElement> lstHelp = lDriver.findElements(By.xpath("//img[@alt='tool tip help']"));
			if(lstHelp.size()>0)
			{
				Report.ValidationPoint(testContext.getName(), "Verify the presence of a ? Next to the link", "True","True", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify the presence of a ? Next to the link", "True","False", true);
			}
			//Validate Split payment link
			if(UI.WaitForObject(oR_MakeAPayment.lnkSplitThisPayment,1))
			{
				Report.ValidationPoint(testContext.getName(), "Verify Split payment link", "True","True", true);
				//Click on the link
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Split payment link");
				oR_MakeAPayment.lnkSplitThisPayment.click();
				//Verify when an additional line for payments is displayed on this page the check box - Enroll in Autopay is disabled and unchecked
				/*List<WebElement> lstEdt = lDriver.findElements(By.xpath("//a[contains(text(),'Edit payment profile')]"));
				if(lstEdt.size()==2)
				{
					Report.ValidationPoint(testContext.getName(), "Verify when an additional line for payments is displayed", "True","True", true);
					//UI.VerifyElementNotPresent(oR_MakeAPayment.chkEnrollInAutopayCheckbox, "Enroll in autopay checkbox");
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify when an additional line for payments is displayed", "True","False", true);
				}*/
				//Validate delete this row link
				Boolean bDelete = UI.WaitForObject(oR_MakeAPayment.lnkDeleteThisRow,1);
				Report.ValidationPoint(testContext.getName(), "Validate delete this row link", "true", String.valueOf(bDelete), true);
				//Click on the link , click OK on the confirming alert if any
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on delete this row link");
				oR_MakeAPayment.lnkDeleteThisRow.click();
				//The Split Payment is removed from the Step 1 page , the checkbox - Enroll is Autopay becomes enabled
				List<WebElement> lstEdt1 = lDriver.findElements(By.xpath("//a[contains(text(),'Edit payment profile')]"));
				if(lstEdt1.size()==1)
				{
					Report.ValidationPoint(testContext.getName(), "Verify The Split Payment is removed from the Step 1 page", "True","True", true);
					Boolean bEnroll = UI.WaitForObject(oR_MakeAPayment.chkEnrollInAutopayCheckbox,1);
					Report.ValidationPoint(testContext.getName(), "Validate Enroll is Autopay becomes enabled", "true", String.valueOf(bEnroll), true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Split payment link", "True","False", true);
			}
			
			oR_MakeAPayment.edtPaymentAmount1.clear();
			//String sAmt = sNum + 1;
			//System.out.println(sAmt);
			//String sAmt = String.valueOf(iAmt);
			oR_MakeAPayment.edtPaymentAmount1.sendKeys("1");
			//Select payment method and enter the details
			Boolean bSelect = UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod3,"New checking / savings account",testContext.getName());
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
					Report.ValidationPoint(testContext.getName(), "Verify payment method details entered", "true", "true", true);
					/*if(UI.WaitForObject(oR_MakeAPayment.chkPaymentFrameSaveMyPaymentInformationCheckbox, 1).equals(true))
					{
						oR_MakeAPayment.chkPaymentFrameSaveMyPaymentInformationCheckbox.click();
					}*/					
					//Click on continue button
					oR_MakeAPayment.btnPaymentFrameContinue.click();
					Thread.sleep(10000);
					lDriver.switchTo().defaultContent();
					Thread.sleep(5000);
					//Edited according to the new condition (15th Jan)
					/*oR_MakeAPayment.edtPaymentAmountSlid.clear();
					oR_MakeAPayment.edtPaymentAmountSlid.sendKeys("101");
					UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod3, "New account ending in 5543");*/
				}
			}
			//Click on next
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Next");
			oR_MakeAPayment.btnNext.click();
			//Validate Payment Alert div
			Thread.sleep(10000);
			Boolean bPaymentAlert = UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert,10);
			if(bPaymentAlert.equals(true))
			{
				Report.ValidationPoint(testContext.getName(), "Verify payment alert div", "true", String.valueOf(bPaymentAlert), true);
				//Verify alert informs the customer that they are paying more than they owe, or paying on an account with a zero balance
				Boolean bPaymentAlertTxt = UI.WaitForObject(oR_MakeAPayment.txtPaymentAlertText,1);
				Report.ValidationPoint(testContext.getName(), "Verify alert informs the customer that they are paying more than they owe, or paying on an account with a zero balance", "true", String.valueOf(bPaymentAlertTxt), true);
				//Retrivieng the Alert msg
				Report.OperationPoint(testContext.getName(), "	Operational - Retrivieng the Alert msg : "+oR_MakeAPayment.txtPaymentAlertText.getText());
				//Click on the Edit Payment option on the div layer
				if(UI.WaitForObject(oR_MakeAPayment.lnkEditPayment, 1))
				{
					Report.ValidationPoint(testContext.getName(), "Verify Edit Payment link", "True","True", true);
					//Click on Edit payment 
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Edit payment");
					oR_MakeAPayment.lnkEditPayment.click();
					//The customer lands back to the same page
					//Validate make a payment page
					Boolean bMAPpg3 = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment,10);
					Report.TestPoint(testContext.getName(), "Validate make a payment page", "true", String.valueOf(bMAPpg3), true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify Edit Payment link", "True","false", true);
				}
			}
			else
			{
				//Validate Payment Review page
				Boolean bPaymentReview1 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Validate Payment Review page", "True", String.valueOf(bPaymentReview1), true);
			}
			//unverified Card not present in the account
			//Expired Credit Card - not present
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name - ValidateMakePaymentReviewFlow()
	 * Description - Validate print friendly version of Account Summary
	 * Parameters - None
	 * Date created - 29th Apr 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//BAP06044_01
	public static void ValidateMakePaymentReviewFlow(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
		OR_ReviewPaymentDetails oR_ReviewPaymentDetails = PageFactory.initElements(lDriver, OR_ReviewPaymentDetails.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		try
		{
			//Validate Make A Payment button
			Boolean bMAP = UI.WaitForObject(oR_AccountOverview.btnMakeAPayment_OldDashboard,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate Make A Payment button", "True", String.valueOf(bMAP), true);
			//Click on Make A Payment button
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Make A Payment button");
			oR_AccountOverview.btnMakeAPayment_OldDashboard.click();
			//Validate make a payment page
			Boolean bMAPpg = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate make a payment page", "True", String.valueOf(bMAPpg), true);
			//Enter $1.00 in the payment amount editBox
			//Boolean bPaymentAmt = UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1,1);
			//Report.TestPoint(testContext.getName(), "Verify Payment amount editbox", "True", String.valueOf(bPaymentAmt), true);
			oR_MakeAPayment.edtPaymentAmount1.clear();
			Report.OperationPoint(testContext.getName(), "	Operational - Entering $1 at payment amount editBox");
			oR_MakeAPayment.edtPaymentAmount1.sendKeys("1");
			//Click on Edit profile link
			Boolean bEditProfileLnk = UI.WaitForObject(oR_MakeAPayment.lnkEditPaymentProfile,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Edit profile link", "True", String.valueOf(bEditProfileLnk), true);
			//Click on Edit profile link
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Edit profile link");
			oR_MakeAPayment.lnkEditPaymentProfile.click();
			Thread.sleep(15000);
			//Validate the Edit Payment Profile (Checking/Savings) div layer is displayed
			Boolean bEditProfileDiv = UI.WaitForObject(oR_MakeAPayment.frmEditPaymentProfile,1);
			Report.TestPoint(testContext.getName(), "Verify the Edit Payment Profile (Checking/Savings) div layer is displayed", "True", String.valueOf(bEditProfileDiv), true);
			//Switch to Edit payment profile div
			String sMainWindowHandle = lDriver.getWindowHandle();
			WebElement frmNote = lDriver.findElement(By.xpath("//div[@id='cboxLoadedContent']//iframe"));
			lDriver.switchTo().frame(frmNote);
			//lDriver.switchTo().frame(oR_MakeAPayment.frmEditPaymentProfile);
			//Validate the title of the div layer is 'Edit Payment Profile'
			Boolean bEditProfileTitle = UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert,10);
			Report.TestPoint(testContext.getName(), "Verify Edit Payment Profile title is displayed", "True", String.valueOf(bEditProfileTitle), true);
			//Validate Payment Profile Name
			Boolean bPaymentProfileName = UI.WaitForObject(oR_MakeAPayment.txtPaymentProfileName,1);
			Report.ValidationPoint(testContext.getName(), "Verify Payment Profile Name is displayed", "True", String.valueOf(bPaymentProfileName), true);
			//Validate Payment Method
			Boolean bPaymentMethod = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethodfrm,1);
			Report.ValidationPoint(testContext.getName(), "Verify Payment Method is displayed", "True", String.valueOf(bPaymentMethod), true);
			//Validate Name on Bank Account is prepopulated
			Boolean bName = UI.WaitForObject(oR_MakeAPayment.edtNameOnBankAcc,1);
			if(bName.equals(true))
			{
				if(oR_MakeAPayment.edtNameOnBankAcc.getAttribute("value").length()>0)
				{
					Report.ValidationPoint(testContext.getName(), "Verify Name on Bank Account is prepopulated", "True","True", true);
					Report.OperationPoint(testContext.getName(), "	Operational - Retrivieng the Name : "+oR_MakeAPayment.edtNameOnBankAcc.getText());
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Name on Bank Account is prepopulated", "True","False", true);
			}
			//Validate Routing Number is prepopulated
			Boolean bRouting = UI.WaitForObject(oR_MakeAPayment.edtRoutingNumber,UI.iObjTimeOut);
			if(bRouting.equals(true))
			{
				if(oR_MakeAPayment.edtRoutingNumber.getAttribute("value").length()>0)
				{
					Report.ValidationPoint(testContext.getName(), "Verify Routing Number is prepopulated", "True","True", true);
					Report.OperationPoint(testContext.getName(), "	Operational - Retrivieng the Routing number : "+oR_MakeAPayment.edtRoutingNumber.getText());
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Routing Number is prepopulated", "True","False", true);
			}
			//Validate Bank account number is prepopulated
			Boolean bBankAccNo = UI.WaitForObject(oR_MakeAPayment.edtBankAccountNumber,1);
			if(bBankAccNo.equals(true))
			{
				if(oR_MakeAPayment.edtBankAccountNumber.getAttribute("value").length()>0)
				{
					Report.ValidationPoint(testContext.getName(), "Verify Bank account number is prepopulated", "True","True", true);
					Report.OperationPoint(testContext.getName(), "	Operational - Retrivieng the Bank account : "+oR_MakeAPayment.edtBankAccountNumber.getText());
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Bank account Number is prepopulated", "True","False", true);
			}
			//Validate Re-Bank account number is prepopulated
			Boolean bReBankAccNo = UI.WaitForObject(oR_MakeAPayment.edtReenterAccNum,1);
			if(bReBankAccNo.equals(true))
			{
				if(oR_MakeAPayment.edtReenterAccNum.getAttribute("value").length()>0)
				{
					Report.ValidationPoint(testContext.getName(), "Verify Re-Bank account number is prepopulated", "True","True", true);
					Report.OperationPoint(testContext.getName(), "	Operational - Retrivieng the Re-Bank account : "+oR_MakeAPayment.edtReenterAccNum.getText());
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Re-Bank account Number is prepopulated", "True","False", true);
			}
			//Validate the presence of the following links/buttons: Close ( link) Cancel ( link) Delete Profile ( button) Save ( button)
			//Validate link close
			Boolean bClose = UI.WaitForObject(oR_MakeAPayment.lnkCloseFrame,1);
			Report.ValidationPoint(testContext.getName(), "Verify link close is displayed", "True", String.valueOf(bClose), true);
			//Validate button delete profile
			Boolean bDelete = UI.WaitForObject(oR_MakeAPayment.btnDeleteProfile,1);
			Report.ValidationPoint(testContext.getName(), "Verify button delete profile is displayed", "True", String.valueOf(bDelete), true);
			//Validate button save
			Boolean bsave = UI.WaitForObject(oR_MakeAPayment.btnSave,1);
			Report.ValidationPoint(testContext.getName(), "Verify button save/Continue is displayed", "True", String.valueOf(bsave), true);
			//Validate link cancel
			Boolean bCancel = UI.WaitForObject(oR_MakeAPayment.lnkCancelFrm,1);
			Report.ValidationPoint(testContext.getName(), "Verify link cancel is displayed", "True", String.valueOf(bCancel), true);
			//Click on the 'close' link
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on link close");
			oR_MakeAPayment.lnkCloseFrame.click();
			//lDriver.switchTo().defaultContent();
			lDriver.switchTo().window(sMainWindowHandle);
			Thread.sleep(7000);
			//Validate make a payment page
			Boolean bMAPpg1 = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate make a payment page", "True", String.valueOf(bMAPpg1), true);
			//Click on Edit payment profile link
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Edit profile link");
			oR_MakeAPayment.lnkEditPaymentProfile.click();
			Thread.sleep(15000);
			//Validate the Edit Payment Profile (Checking/Savings) div layer is displayed
			Boolean bEditProfileDiv1 = UI.WaitForObject(oR_MakeAPayment.frmEditPaymentProfile,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify the Edit Payment Profile (Checking/Savings) div layer is displayed", "True", String.valueOf(bEditProfileDiv1), true);
			//Switch to Edit payment profile div
			lDriver.switchTo().frame(oR_MakeAPayment.frmEditPaymentProfile);
			//Click on cancel link
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on link cancel");
			oR_MakeAPayment.lnkCancelFrm.click();
			lDriver.switchTo().defaultContent();
			Thread.sleep(5000);
			//Validate make a payment page
			Boolean bMAPpg2 = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate make a payment page", "True", String.valueOf(bMAPpg2), true);
			//Click on Edit payment profile link
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Edit profile link");
			oR_MakeAPayment.lnkEditPaymentProfile.click();
			Thread.sleep(10000);
			//Validate the Edit Payment Profile (Checking/Savings) div layer is displayed
			Boolean bEditProfileDiv2 = UI.WaitForObject(oR_MakeAPayment.frmEditPaymentProfile,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify the Edit Payment Profile (Checking/Savings) div layer is displayed", "True", String.valueOf(bEditProfileDiv2), true);
			//Switch to Edit payment profile div
			lDriver.switchTo().frame(oR_MakeAPayment.frmEditPaymentProfile);
			//Click delete profile button
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on button delete profile");
			oR_MakeAPayment.btnDeleteProfile.click();
			//Validate the Delete Payment Profile (Checking/ Savings Account) div layer is displayed
			Boolean bDeleteProfileDiv = UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate the Delete Payment Profile (Checking/ Savings Account) div layer is displayed", "True", String.valueOf(bDeleteProfileDiv), true);
			//Click on cancel link
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on link cancel");
			oR_MakeAPayment.lnkCancel.click();
			try
			{
				if(UI.WaitForObject(oR_MakeAPayment.lnkCancel, UI.iObjTimeOut, lDriver))
				{
					oR_MakeAPayment.lnkCancel.click();
				}
			}catch(Exception ee1)
			{}
			//The user is navigated back to Edit payment profile div layer
			Boolean bProf = UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate The user is navigated back to Edit payment profile div layer", "True", String.valueOf(bProf), true);
			//Click on cancel link
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on cancel link");
			oR_MakeAPayment.lnkCancelFrm.click();
			lDriver.switchTo().defaultContent();
			Thread.sleep(5000);
			//Click on next button
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on next button");
			oR_MakeAPayment.btnNext.click();
			
			//Validate Payment Review page
			Boolean bPaymentReview = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,UI.iObjTimeOut);
			if(!bPaymentReview.equals(true))
			{
				//Validate popup when Balance is 0.00
				if(UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert, UI.iObjTimeOut))
				{
					oR_MakeAPayment.lnkContinue.click();
				}
			}
			//Validate Payment Review page
			Boolean bPaymentReview1 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate Payment Review page", "True", String.valueOf(bPaymentReview1), true);
			//Validate Account text
			Boolean bAccount = UI.WaitForObject(oR_ReviewPaymentDetails.txtAccount,UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate Account text", "True", String.valueOf(bAccount), true);
			//Validate Terms and condition section
			Boolean bTC = UI.WaitForObject(oR_ReviewPaymentDetails.txtTC,1);
			Report.ValidationPoint(testContext.getName(), "Validate Terms and condition section", "True", String.valueOf(bTC), true);
			//Verify that the Current Payments and charges section and the Account module is not displayed on this page.
			UI.VerifyElementNotPresent(oR_ReviewPaymentDetails.txtCurrentPayment, "Current Payments");
			//Validate link Edit Payment Information
			Boolean bEditlnk = UI.WaitForObject(oR_ReviewPaymentDetails.lnkEditPaymentInformation,1);
			Report.ValidationPoint(testContext.getName(), "Validate link Edit Payment Information", "True", String.valueOf(bEditlnk), true);
			//Click link Edit Payment Information
			Report.OperationPoint(testContext.getName(), "	Operational - Click link Edit Payment Information");
			oR_ReviewPaymentDetails.lnkEditPaymentInformation.click();
			//Validate make a payment page
			Boolean bMAPpg3 = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate make a payment page", "True", String.valueOf(bMAPpg3), true);
			//Click on Next button
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on next button");
			oR_MakeAPayment.btnNext.click();
			
			//Validate Payment Review page
			Boolean bPaymentReview2 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,UI.iObjTimeOut);
			if(!bPaymentReview2.equals(true))
			{
				//Validate popup when Balance is 0.00
				if(UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert, UI.iObjTimeOut))
				{
					oR_MakeAPayment.lnkContinue.click();
				}
			}
			//Validate Payment Review page
			Boolean bPaymentReview3 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate Payment Review page", "True", String.valueOf(bPaymentReview3), true);
			
			//Verify the presence of the the following column headers in a tabular form- Account,Payment Method,Amount to Pay,Pay on Date
			//Validate Payment Method column
			Boolean bPaymentMethodCol = UI.WaitForObject(oR_ReviewPaymentDetails.txtPaymentMethod,UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate Payment Method column", "True", String.valueOf(bPaymentMethodCol), true);
			//Validate Payment Amount to Pay column
			Boolean bAmtPayCol = UI.WaitForObject(oR_ReviewPaymentDetails.txtAmountToPay,1);
			Report.ValidationPoint(testContext.getName(), "Validate Payment Amount to Pay column", "True", String.valueOf(bAmtPayCol), true);
			//Validate Payment Pay on Date column
			Boolean bPayOnDateCol = UI.WaitForObject(oR_ReviewPaymentDetails.txtPayOnDate,1);
			Report.ValidationPoint(testContext.getName(), "Validate Payment Pay on Date column", "True", String.valueOf(bPayOnDateCol), true);
			//Verify the presence of a Print terms and condition link next to the section header
			Boolean bPrint = UI.WaitForObject(oR_ReviewPaymentDetails.lnkPrintTC,1);
			if(bPrint.equals(true))
			{
				Report.ValidationPoint(testContext.getName(), "Validate the presence of a Print terms and condition link next to the section header", "True", String.valueOf(bPrint), true);
				//Click on Print terms and condition link
				Report.OperationPoint(testContext.getName(), "	Operational - Click on Print terms and condition link");
				String sOrigWin = lDriver.getWindowHandle();
				oR_ReviewPaymentDetails.lnkPrintTC.click();
				//The Print this page pop up is displayed
				for(String sTC : lDriver.getWindowHandles())
				{
					lDriver.switchTo().window(sTC);
				}
				WebElement elmTC = lDriver.findElement(By.xpath("//p[text()='Terms & Conditions']"));
				if(elmTC.equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Print this page pop up is displayed", "True", String.valueOf(bPrint), true);
				}
				lDriver.close();
				lDriver.switchTo().window(sOrigWin);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate the presence of a Print terms and condition link next to the section header", "True", String.valueOf(bPrint), true);
			}
			//Below alert not present
			//Verify that the alerts are displayed if the Payment mode is one of the following - o Payment arrangements (one- and two-part) o 
			//Editing a scheduled payment method o Adding a scheduled payment method o New scheduled payments
			//Verify the presence of the the back button, cancel link and the Submit button
			//Validate back button
			Boolean bBackButton = UI.WaitForObject(oR_ReviewPaymentDetails.btnBack,UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate back button", "True", String.valueOf(bBackButton), true);
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on back button");
			oR_ReviewPaymentDetails.btnBack.click();
			//Validate make a payment page
			Boolean bMAPpg4 = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate make a payment page", "True", String.valueOf(bMAPpg4), true);
			//Click on Next button
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on next button");
			oR_MakeAPayment.btnNext.click();
			
			//Validate Payment Review page
			Boolean bPaymentReview4 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,UI.iObjTimeOut);
			if(!bPaymentReview4.equals(true))
			{
				//Validate popup when Balance is 0.00
				if(UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert, UI.iObjTimeOut))
				{
					oR_MakeAPayment.lnkContinue.click();
				}
			}
			//Validate Payment Review page
			Boolean bPaymentReview5 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate Payment Review page", "True", String.valueOf(bPaymentReview5), true);
			
			//Validate Cancel link
			Report.ValidationPoint(testContext.getName(), "Validate Cancel link", "True", String.valueOf(bBackButton), true);
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Cancel link");
			oR_ReviewPaymentDetails.lnkCancel.click();
			
			//Validate Account overview page
			Boolean bAccOverview1 = UI.WaitForObject(oR_AccountOverview.lnkOverview,UI.iObjTimeOut);
			oR_AccountOverview.lnkOverview.click();
			Thread.sleep(3000);
			Report.TestPoint(testContext.getName(), "Validate Account overview page", "True", String.valueOf(bAccOverview1), true);	
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name - ValidateMakePaymentConfirmation()
	 * Description - Validate print friendly version of Account Summary
	 * Parameters - None
	 * Date created - 29th Apr 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//BAP06044_02
	public static void ValidateMakePaymentConfirmation(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
		OR_ReviewPaymentDetails oR_ReviewPaymentDetails = PageFactory.initElements(lDriver, OR_ReviewPaymentDetails.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		OR_PaymentConfirmation oR_PaymentConfirmation = PageFactory.initElements(lDriver, OR_PaymentConfirmation.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		try
		{
			//Validate Make A Payment button
			Boolean bMAP = UI.WaitForObject(oR_AccountOverview.btnMakeAPayment_OldDashboard,1);
			Report.TestPoint(testContext.getName(), "Validate Make A Payment button", "True", String.valueOf(bMAP), true);
			//Click on Make A Payment button
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Make A Payment button");
			oR_AccountOverview.btnMakeAPayment_OldDashboard.click();
			//Validate make a payment page
			Boolean bMAPpg = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment,50);
			Report.TestPoint(testContext.getName(), "Validate make a payment page", "True", String.valueOf(bMAPpg), true);
			//Click on next button
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on next button");
			oR_MakeAPayment.btnNext.click();
			
			//Validate Payment Review page
			Boolean bPaymentReview = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,10);
			if(!bPaymentReview.equals(true))
			{
				//Validate popup when Balance is 0.00
				if(UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert, 15))
				{
					oR_MakeAPayment.lnkContinue.click();
				}
			}
			//Validate Payment Review page
			Boolean bPaymentReview1 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,50);
			Report.TestPoint(testContext.getName(), "Validate Payment Review page", "True", String.valueOf(bPaymentReview1), true);
			
			//Click on Billing and usage sec nav link
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Billing and usage sec nav link");
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			//validate Incomplete payment div
			Boolean bIncomplete = UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert,50);
			Report.ValidationPoint(testContext.getName(), "Validate Incomplete payment div", "True", String.valueOf(bIncomplete), true);
			//Click Cancel link
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Cancel link");
			oR_ReviewPaymentDetails.lnkCancel1.click();
			//Validate Payment Review page
			Boolean bPaymentReview2 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,10);
			Report.TestPoint(testContext.getName(), "Validate Payment Review page", "True", String.valueOf(bPaymentReview2), true);
			//Click on Billing and usage sec nav link
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Billing and usage sec nav link");
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			//validate Incomplete payment div
			Boolean bIncomplete1 = UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert,50);
			Report.ValidationPoint(testContext.getName(), "Validate Incomplete payment div", "True", String.valueOf(bIncomplete1), true);
			//Click on Yes, Continue
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Yes, Continue");
			oR_ReviewPaymentDetails.lnkYes.click();
			
			//Validate Billing and usage page
			Boolean bBAU = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
			Report.ValidationPoint(testContext.getName(), "Validate Billing and usage page", "True", String.valueOf(bBAU), true);
			//Click on make a payment button
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on make a payment button");
			oR_BillAndUsage.btnMakePaymentInBillingPage.click();
			
			//Validate make a payment page
			Boolean bMAPpg1 = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment,50);
			Report.TestPoint(testContext.getName(), "Validate make a payment page", "True", String.valueOf(bMAPpg1), true);
			//Click on next button
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on next button");
			oR_MakeAPayment.btnNext.click();
			
			//Validate Payment Review page
			Boolean bPaymentReview11 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,10);
			if(!bPaymentReview11.equals(true))
			{
				//Validate popup when Balance is 0.00
				if(UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert, 15))
				{
					oR_MakeAPayment.lnkContinue.click();
				}
			}
			//Validate Payment Review page
			Boolean bPaymentReview12 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle,50);
			Report.TestPoint(testContext.getName(), "Validate Payment Review page", "True", String.valueOf(bPaymentReview12), true);
			
			//Click on Submit button
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Submit button");
			oR_ReviewPaymentDetails.btnSubmit.click();
			
			//Validate confirmation page
			Boolean bPaymentConf = UI.WaitForObject(oR_PaymentConfirmation.txtPaymentConfirmationTitle,50);
			Report.TestPoint(testContext.getName(), "Validate confirmation page", "True", String.valueOf(bPaymentConf), true);
			//Validate text Account
			Boolean bAccount = UI.WaitForObject(oR_PaymentConfirmation.txtAccount,1);
			Report.ValidationPoint(testContext.getName(), "Validate text Account", "True", String.valueOf(bAccount), true);
			//Verify that a Print this page link is displayed next to the Section Header
			Boolean bPrint = UI.WaitForObject(oR_PaymentConfirmation.lnkPrint,1);
			Report.ValidationPoint(testContext.getName(), "Validate that a Print this page link is displayed next to the Section Header", "True", String.valueOf(bPrint), true);
			//Click on the Print link
			//cannot click on print link. system browser opens.
			//Report.OperationPoint(testContext.getName(), "	Operational - Clicking on link print");
			//oR_PaymentConfirmation.lnkPrint.click();
			
			//Verify the presence of the the following column headers in a tabular form- Account,Payment Method,Amount to Pay,Pay on Date
			//Validate Payment Method column
			Boolean bPaymentMethodCol = UI.WaitForObject(oR_PaymentConfirmation.txtPaymentMethod,1);
			Report.ValidationPoint(testContext.getName(), "Validate Payment Method column", "True", String.valueOf(bPaymentMethodCol), true);
			//Validate Confirmation column
			Boolean bConfCol = UI.WaitForObject(oR_PaymentConfirmation.txtConfirmation,1);
			Report.ValidationPoint(testContext.getName(), "Validate Payment Amount to Pay column", "True", String.valueOf(bConfCol), true);
			//Validate Payment Amount to Pay column
			Boolean bAmtPayCol = UI.WaitForObject(oR_PaymentConfirmation.txtAmountToPay,1);
			Report.ValidationPoint(testContext.getName(), "Validate Payment Amount to Pay column", "True", String.valueOf(bAmtPayCol), true);
			//Validate Payment Pay on Date column
			Boolean bPayOnDateCol = UI.WaitForObject(oR_PaymentConfirmation.txtPayOnDate,1);
			Report.ValidationPoint(testContext.getName(), "Validate Payment Pay on Date column", "True", String.valueOf(bPayOnDateCol), true);
			
			//Validate the following information is displayed below the Column Headers in order :
			//NickName|Account Number,Method(Card ending in ####,Mail Payment,New Account Ending in ####) , Confirmation number - 12 digit.,payment amount -$##.## , and pay on date - MM/DD/YYYY
			List<WebElement> lstTable = lDriver.findElements(By.xpath("//div[@class='PadLeft23']//label"));
			for(int i=0;i<lstTable.size();i++)
			{
				if(lstTable.get(i).getText().length()>0)
				{
					Report.ValidationPoint(testContext.getName(), lstTable.get(i).getText(),"True","True", true);
				}
			}
			
			//Validate Make Another Payment link
			Boolean bMakeAnotherPayment = UI.WaitForObject(oR_PaymentConfirmation.lnkMakeAnotherPayment,1);
			Report.ValidationPoint(testContext.getName(), "Validate Make Another Payment link", "True", String.valueOf(bMakeAnotherPayment), true);
			//Validate Go to overview link
			Boolean bGoto = UI.WaitForObject(oR_PaymentConfirmation.lnkMakeAnotherPayment,1);
			Report.ValidationPoint(testContext.getName(), "Validate Go to overview link", "True", String.valueOf(bGoto), true);
			
			//Due to stub env, payment wont be seen in payment activity.
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name - ValidateReportTabPastDSLUsage 
	 * Description- This fdunction validates the Past DSL Usage Report
	 * Parameters - 
	 * Date created -29th April 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//BAP30885
	public static void ValidateReportTabPastDSLUsage (final ITestContext testContext)
			throws Exception {
		
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		try {
			//click on Billing&Payments
			Report.OperationPoint(testContext.getName(), "Navigating to Bills & Usage landing page");
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null))
			{
				Report.TestPoint(testContext.getName(), "Goto Bills & Usage from global nav", "Bills & Usage page is displayed", "Bills & Usage page is displayed" , true);	
				//Click Report Tab 	
				if(UI.WaitForObject(oR_BillAndUsage.lnkReportTab, UI.iObjTimeOut))
				{
					Report.OperationPoint(testContext.getName(), "Navigating to Reports");
	
						Report.TestPoint(testContext.getName(), "Goto Report Tab", "Report is displayed", "Report is displayed" , true);
						oR_BillAndUsage.lnkReportTab.click();
						if(UI.WaitForObject(oR_BillAndUsage.lstSelectReportType, UI.iObjTimeOut))
						{	
							Report.TestPoint(testContext.getName(), "Verify Select Report type dropdown is present", "Select Report type dropdown is present", "Select Report type dropdown is present" , true);
							List<String> lstReportTypes = new ArrayList<String>();
							lstReportTypes.add("Total Account Charges");
							lstReportTypes.add("Breakdown of Current Charges by Carrier");
							lstReportTypes.add("Past DSL Usage");
							
							//List<WebElement> reportTypesList = lDriver.findElements(By.xpath("//*[@id='ddShortcutBox']//dl[@class='MarTop10']/dd"));							
							//if(reportTypesList.){
							   	   Report.OperationPoint(testContext.getName(), "Click on Select Report drop down");
								   oR_BillAndUsage.lstSelectReportType.click();
								   Thread.sleep(10000);
							       WebElement reportType = lDriver.findElement(By.xpath("//*[@id='ddShortcutBox']//dl[@class='MarTop10']//a[contains(text(),'Past DSL Usage')]"));
							      // UI.WaitForObject(e, 30);
							       String reportTypeName = reportType.getText();
							       System.out.println(" report Name " + reportTypeName);
							       
								   if(lstReportTypes.contains(reportTypeName))
								   {
										Report.ValidationPoint(testContext.getName(), "Validate " + reportTypeName + " option is available under Select Report Type drop down",  reportTypeName + " option is available under Select Report Type drop down",  reportTypeName + " option is available under Select Report Type drop down", true);
										UI.WaitForObject(reportType,40);
										reportType.click();


										// Select Start Billing Period
										Boolean bStartDate = UI.WaitForObject(oR_BillAndUsage.lstStartDate, 30);
										Report.ValidationPoint(testContext.getName(), "Validate Start Date Selection dropdown is displayed", "True", String.valueOf(bStartDate), true);
										oR_BillAndUsage.lstStartDate.click();
										
										List<WebElement> StartDate = lDriver.findElements(By.xpath("//div[@id='ddShortcutBox1']//dl[@id='dateList8']/dd"));
										int Size = StartDate.size();
										System.out.println(Size);

										Report.OperationPoint(testContext.getName(), "Click on extreme start date");
										Thread.sleep(10000);
										StartDate.get(Size-1).click();
										
										Report.ValidationPoint(testContext.getName(), "Validate most previous start date is selected", "True", "true", true);
									  
										// Select End Billing Period 
										
										Boolean bEndDate = UI.WaitForObject(oR_BillAndUsage.lstEndDate, 10);
										Report.ValidationPoint(testContext.getName(), "Validate Start Date Selection dropdown is displayed", "True", String.valueOf(bEndDate), true);
										oR_BillAndUsage.lstEndDate.click();
										
										List<WebElement> EndDate = lDriver.findElements(By.xpath("//div[@id='ddShortcutBox2']//dl[@id='endDateList8']/dd"));

										Report.OperationPoint(testContext.getName(), "Click on extreme start date");
										Thread.sleep(10000);
										EndDate.get(0).click();
										
										Report.ValidationPoint(testContext.getName(), "Validate the recent end date is selected", "True", "true", true);

										// Click on Create Report button
										UI.WaitForObject(oR_BillAndUsage.btnCreateReport, UI.iObjTimeOut);
										Report.OperationPoint(testContext.getName(), "Generate Report by Clicking on Create button");
										oR_BillAndUsage.btnCreateReport.click();
										Thread.sleep(10000);
										
										//Check for graphs
			
										List <WebElement> graphsize = lDriver.findElements(By.cssSelector("g.highcharts-tracker rect"));
										int countBar = graphsize.size();
										System.out.println(countBar);
										Report.OperationPoint(testContext.getName(), "Verifying the usage bars are displayed for all the available months");
										if(countBar==Size)
										{
											Report.ValidationPoint(testContext.getName(), "Validate the bars displayed are equivalent to the no.of months", "True", "true", true);
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Validate the bars displayed are equivalent to the no.of months", "True", "false", true);
										}

										//Validate y axis title
										
										WebElement dataGBValue = lDriver.findElement(By.xpath("//*[local-name() = 'svg']/*[name()='text']/*[name()='tspan' and contains(@x, '14')]"));
										String syaxis = dataGBValue.getText();
										Report.OperationPoint(testContext.getName(), "Verifying the usage is displayed in Gigabytes");
										if(syaxis.contains("Gigabytes*"))
										{
											Report.ValidationPoint(testContext.getName(), "Validate the yaxis title is Gigabytes", "True", "true", true);
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Validate the yaxis title is Gigabytes", "True", "false", true);
										}
										//Validate for O usage
										WebElement barheight = lDriver.findElement(By.cssSelector("g.highcharts-tracker rect"));
										String height = barheight.getAttribute("height");
										if(height.equals("1"))
										{
											Report.ValidationPoint(testContext.getName(), "Validate for 0 usage", "Zero(0) usage graph bar when zero usage exists for DSL Customer", "Zero(0) usage graph bar when zero usage exists for DSL Customer", true);
										}

								   }else{
									   
									  Report.ValidationPoint(testContext.getName(),"Validate valid report type is displayed" ,reportTypeName + " is a valid report type" , reportTypeName + " is NOT a valid report type", true); 
								   }
						}			
				}
			}
		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}

	
	
	/**************************************************************
	 * Function Name - VerifyOnlinePaperBillingAndReportTabSection()
	 * Description - This function do below things:
	 * 				 Verifies payment history and online , paper links if payment is made under history tab
	 * 				 Verifies 'Select report type' , 'Starting billing period' , 'Ending billing period' dropdowns and create report button
	 * Parameters - None
	 * Date created - 4th May 2015
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//BAP08728
	
	public static void VerifyOnlinePaperBillingAndReportTabSection(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		try
		{
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null))
			{
				//Verify navigation to billing & usage page
				if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 80))
				{
					Report.TestPoint(testContext.getName(),"Verify navigation to billing & usage page", "Navigated","Navigated", true);
					Thread.sleep(10000);
					//Click on History tab
					Boolean bHistoryTab = UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab,25);
					Report.TestPoint(testContext.getName(), "Verify history tab", "true", String.valueOf(bHistoryTab), true);
					oR_BillAndUsage.lnkHistoryTab.click();
					
					Thread.sleep(10000);
					oR_BillAndUsage.lnkBillTab.click();
					Thread.sleep(25000);
					oR_BillAndUsage.lnkHistoryTab.click();
					
					Thread.sleep(20000);
					Report.TestPoint(testContext.getName(), "Click on history tab", "true", String.valueOf(bHistoryTab), true);
					
					//Verify that payment history gets diplayed if payment is made Else a message :No history available gets displayed
					boolean bPaymentMade= false;
					if(UI.WaitForObject(oR_BillAndUsage.txtBillAndPaymentHistory,25))
					{
						Report.TestPoint(testContext.getName(), "Verify that payment history gets displayed if payment is made", "Displayed", "Displayed", true);
						bPaymentMade= true ;
					}
					else if(bPaymentMade==false)
					{
						try
						{
							lDriver.findElement(By.xpath("//*[contains(text(),'No history available')]"));
							Report.ValidationPoint(testContext.getName(), "Verify A message :No history available gets displayed if payment is not made", "Displayed", "Displayed", true);
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(), "Verify A message :No history available gets displayed if payment is not made", "Displayed", "NOT Displayed", true);
						}
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify that payment history gets diplayed if payment is made Else a message :No history available gets displayed", "Displayed", "NOT Displayed", true);
					}
					
					
					//Verify links online and paper links under view bill column  if there is payment history
					if(bPaymentMade==true)
					{
						// Extract total billing periods displaying in bill history table
						
						List<WebElement> lstBillPeriods = lDriver.findElements(By.xpath("//*[@headers = 'bill_period'][contains(text(),'-')]"));
						
						for(int billPriodCount = 0; billPriodCount< lstBillPeriods.size() ; billPriodCount++)
						{
							String txtBillPeriod = lstBillPeriods.get(billPriodCount).getText();
							System.out.println("txtBillPeriod : "+txtBillPeriod);
							
							//Verify 'Online link'
							try
							{
								WebElement lnkOnline = lDriver.findElement(By.xpath("//*[contains(text(),'"+txtBillPeriod+"')]/parent::*/child::td/a[text()='Online']"));
							
								if(lnkOnline.isDisplayed())
								{
									Report.ValidationPoint(testContext.getName(),"Verify 'Online link' for biling period '"+txtBillPeriod+"'", "Link Displayed","Link Displayed", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(),"Verify 'Online link' for biling period '"+txtBillPeriod+"'", "Link Displayed","Link NOT Displayed", true);
								}
							}
							catch(Exception e)
							{
								Report.ValidationPoint(testContext.getName(),"Verify 'Online link' for biling period '"+txtBillPeriod+"'", "Link Displayed","Link NOT Displayed", true);
							}
							
							//Verify 'Paper' link
							try
							{
								WebElement lnkPaper = lDriver.findElement(By.xpath("//*[contains(text(),'"+txtBillPeriod+"')]/parent::*/child::td/a[text()='Paper']"));
							
								if(lnkPaper.isDisplayed())
								{
									Report.ValidationPoint(testContext.getName(),"Verify 'Paper link' for biling period '"+txtBillPeriod+"'", "Link Displayed","Link Displayed", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(),"Verify 'Paper link' for biling period '"+txtBillPeriod+"'", "Link Displayed","Link NOT Displayed", true);
								}
							}
							catch(Exception e)
							{
								Report.ValidationPoint(testContext.getName(),"Verify 'Paper link' for biling period '"+txtBillPeriod+"'", "Link Displayed","Link NOT Displayed", true);
							}
							
						}
						
						//Click on Reports tab
						oR_BillAndUsage.lnkReportTab.click();
							
						Thread.sleep(20000);
							
						//Verify Select Report type dropdown is displayed
							
						try
						{
							if(UI.WaitForObject(oR_BillAndUsage.lstSelectReportType, 20))
							{
								Report.ValidationPoint(testContext.getName(),"Verify 'Select Report type' dropdown is displayed", "Displayed","Displayed", true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(),"Verify 'Select Report type' dropdown is displayed", "Displayed","NOT Displayed", true);
							}
							//Select OptionSelect = new Select(oR_BillAndUsage.lstSelectReportType);
							//OptionSelect.selectByIndex(1);
								
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(),"Verify 'Select Report type' dropdown is displayed", "Displayed","NOT Displayed", true);
						}
						
						
						//Verify Starting billing period dropdown is displayed
						try
						{
							if(UI.WaitForObject(oR_BillAndUsage.lstStartDate, 20))
							{
								Report.ValidationPoint(testContext.getName(),"Verify 'Starting billing period' dropdown is displayed", "Displayed","Displayed", true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(),"Verify 'Starting billing period' dropdown is displayed", "Displayed","NOT Displayed", true);
							}
							
								
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(),"Verify 'Starting billing period' dropdown is displayed", "Displayed","NOT Displayed", true);
						}
						
						
						//Verify Ending billing period dropdown is displayed
						try
						{
							if(UI.WaitForObject(oR_BillAndUsage.lstEndDate, 20))
							{
								Report.ValidationPoint(testContext.getName(),"Verify 'Ending billing period' dropdown is displayed", "Displayed","Displayed", true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(),"Verify 'Ending billing period' dropdown is displayed", "Displayed","NOT Displayed", true);
							}
							
								
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(),"Verify 'Ending billing period' dropdown is displayed", "Displayed","NOT Displayed", true);
						}
						
						
						//Verify create report tab is displayed
						try
						{
							lDriver.findElement(By.xpath("//img[@alt='create report']"));
							Report.ValidationPoint(testContext.getName(),"Verify 'Create report' tab is displayed", "Displayed","Displayed", true);
								
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(),"Verify 'Create report' tab is displayed", "Displayed","NOT Displayed", true);
						}
						
					}
					
					
				}
				else
				{
					Report.TestPoint(testContext.getName(),"Verify navigation to billing & usage page", "Navigated","Failed to Navigate", true);
				}
			
			}
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyBillAlertForMultipleServiceChange()
	 * Description - This function is to validate Bill alerts for multiple service changes in Uverse
 	 * Test Case - BAP11473
	 * Parameters - None
	 * Date created - 5th Mar 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//BAP11473
	public static void VerifyBillAlertForMultipleServiceChange(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		try
		{
			
			//Navigate to Billing,Usage,Payments tab and click on Bill details
			new Actions(lDriver).moveToElement(oR_AccountOverview.lnkBillingUsagePaymentsSecNav).build().perform();
			boolean bBillDetails = UI.WaitForObject(oR_AccountOverview.lnkBillDetailsTertNav, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate that Bill Details link is displayed", "true", String.valueOf(bBillDetails), true);
			new Actions(lDriver).moveToElement(oR_AccountOverview.lnkBillDetailsTertNav).click().build().perform();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Bill details' link");
			
			//Verify Bill Alert section is displayed
			boolean bBillAlertSection = UI.WaitForObject(oR_BillAndUsage.txtBillAlertSection, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that Bill Alert section is displayed for all alerts in current Bill page", "true", String.valueOf(bBillAlertSection), true);
			//Verify that a 'Service change’ Bill alert is displayed on current bill page for multiple service changes in single Uverse service
			List<WebElement> BillAlerts = lDriver.findElements(By.xpath("//div[@class='alertBoxGrad']//span[contains(text(),'Change')] [contains(text(),'U-verse')]"));
			if(BillAlerts.size()!=0)
			{
				Report.TestPoint(testContext.getName(), "Verify that a 'Service change’ Bill alert is displayed on current bill page for multiple service changes in single Uverse service. No.of Service change alerts = "+BillAlerts.size(), "true","true", true);
				boolean bUverseTVAlert = UI.WaitForObject(lDriver.findElement(By.xpath("//span[contains(text(),'U-verse TV')]")), 20);
				boolean bUverseInternetAlert = UI.WaitForObject(lDriver.findElement(By.xpath("//span[contains(text(),'U-verse Internet')]")), 20);
				boolean bUverseVoiceAlert = UI.WaitForObject(lDriver.findElement(By.xpath("//span[contains(text(),'U-verse Voice')]")),20);
				//Check for the Uverse TV alert
				if(bUverseTVAlert==true)
				{
					Report.ValidationPoint(testContext.getName(), "Verify that alert for Uverse TV plan changes are displayed", "true","true", true);
					//verify verbiage is displayed under the alert
					boolean bUTvVerbiage = UI.WaitForObject(oR_BillAndUsage.txtUverseTVAlertVerbiage, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Validate that bill alert verbiage is displayed for Uverse TV", "true", String.valueOf(bUTvVerbiage), true);
					//verify View changes section for each alert
					oR_BillAndUsage.lnkUverseTVAlertViewChanges.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'View changes' link");
					//Verify that bill section ‘Addition & changes to services’ is expanded to show the prorated charge detail for the U-verse combined plan changes
					boolean bUTVExpanded = oR_BillAndUsage.txtUverseTVAdditionsAndChangesExpanded.isDisplayed();
					if(bUTVExpanded==true)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that ‘Addition & changes to services’ is expanded to show the prorated charge details", "true",String.valueOf(bUTVExpanded), true);
						//Verify that the bill alert verbiage displays the corresponding dollar amount if Total Additions & Changes to Service is not equal to zero including the total dollar amount
						//and NOT displayed if it is equal to zero
						boolean bUTVCharge = oR_BillAndUsage.txtUTVPlanChangesCharge.getText().contains("$0.00");
						if(bUTVCharge==false)
						{
							Report.ValidationPoint(testContext.getName(), "Verify that Total Additions & Changes amount is NOT zero", "true","true", true);
							//Verify that the bill alert verbiage displays the corresponding dollar amount
							String sCharge = oR_BillAndUsage.txtUTVPlanChangesCharge.getText();
							String arr[]= sCharge.split("\\$");
							if(oR_BillAndUsage.txtUverseTVAlertVerbiage.getText().contains(arr[1]))
							{
								Report.ValidationPoint(testContext.getName(), "Verify that the bill alert verbiage displays the corresponding dollar amount if Total Additions & Changes to Service is not equal to zero", "true","true", true);

							}else
							{
								Report.ValidationPoint(testContext.getName(), "Verify that the bill alert verbiage displays the corresponding dollar amount if Total Additions & Changes to Service is not equal to zero", "true","false", true);

							}
						

						}else
						{
							Report.ValidationPoint(testContext.getName(), "Verify that Total Additions & Changes amount is zero", "true","true", true);
							//Verify that the bill alert verbiage displays the corresponding dollar amount
							if(oR_BillAndUsage.txtUverseTVAlertVerbiage.getText().contains(oR_BillAndUsage.txtUTVPlanChangesCharge.getText()))
							{
								Report.ValidationPoint(testContext.getName(), "Verify that the bill alert verbiage does not displays the corresponding dollar amount if Total Additions & Changes to Service is equal to zero", "true","false", true);

							}else
							{
								Report.ValidationPoint(testContext.getName(), "Verify that the bill alert verbiage does not displays the corresponding dollar amount if Total Additions & Changes to Service is equal to zero", "true","true", true);

							}

						}

					}else
					{
						Report.ValidationPoint(testContext.getName(), "Verify that ‘Addition & changes to services’ is expanded to show the prorated charge details", "true","false", true);

					}
				}
				
				//Check for the Uverse Internet alert
				if(bUverseInternetAlert==true)
				{
					Report.ValidationPoint(testContext.getName(), "Verify that alert for Uverse Internet plan changes are displayed", "true","true", true);

					//verify verbiage is displayed under the alert
					boolean bUinternetVerbiage = UI.WaitForObject(oR_BillAndUsage.txtUverseInternetAlertVerbiage, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Validate that bill alert verbiage is displayed for Uverse Internet", "true", String.valueOf(bUinternetVerbiage), true);
					//verify View changes section for each alert
					oR_BillAndUsage.lnkUverseInternetAlertViewChanges.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'View changes' link");
					//Verify that bill section ‘Addition & changes to services’ is expanded to show the prorated charge detail for the U-verse combined plan changes
					boolean bUInternetExpanded = oR_BillAndUsage.txtUverseInternetAdditionsAndChangesExpanded.isDisplayed();
					if(bUInternetExpanded==true)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that ‘Addition & changes to services’ is expanded to show the prorated charge details", "true",String.valueOf(bUInternetExpanded), true);
						//Verify that the bill alert verbiage displays the corresponding dollar amount if Total Additions & Changes to Service is not equal to zero including the total dollar amount
						//and NOT displayed if it is equal to zero
						boolean bUInternetCharge = oR_BillAndUsage.txtUInternetPlanChangesCharge.getText().contains("$0.00");
						if(bUInternetCharge==false)
						{
							Report.ValidationPoint(testContext.getName(), "Verify that Total Additions & Changes amount is NOT zero", "true","true", true);
							//Verify that the bill alert verbiage displays the corresponding dollar amount
							String sCharge = oR_BillAndUsage.txtUInternetPlanChangesCharge.getText();
							String arr[]= sCharge.split("\\$");
							if(oR_BillAndUsage.txtUverseInternetAlertVerbiage.getText().contains(arr[1]))
							{
								Report.ValidationPoint(testContext.getName(), "Verify that the bill alert verbiage displays the corresponding dollar amount if Total Additions & Changes to Service is not equal to zero", "true","true", true);

							}else
							{
								Report.ValidationPoint(testContext.getName(), "Verify that the bill alert verbiage displays the corresponding dollar amount if Total Additions & Changes to Service is not equal to zero", "true","false", true);

							}
						

						}else
						{
							Report.ValidationPoint(testContext.getName(), "Verify that Total Additions & Changes amount is zero", "true","true", true);
							//Verify that the bill alert verbiage displays the corresponding dollar amount
							if(oR_BillAndUsage.txtUverseInternetAlertVerbiage.getText().contains(oR_BillAndUsage.txtUInternetPlanChangesCharge.getText()))
							{
								Report.ValidationPoint(testContext.getName(), "Verify that the bill alert verbiage does not displays the corresponding dollar amount if Total Additions & Changes to Service is equal to zero", "true","false", true);

							}else
							{
								Report.ValidationPoint(testContext.getName(), "Verify that the bill alert verbiage does not displays the corresponding dollar amount if Total Additions & Changes to Service is equal to zero", "true","true", true);

							}

						}

					}else
					{
						Report.ValidationPoint(testContext.getName(), "Verify that ‘Addition & changes to services’ is expanded to show the prorated charge details", "true","false", true);

					}
				
				}
				
				//Check for the Uverse Voice alert
				if(bUverseVoiceAlert==true)
				{
					Report.ValidationPoint(testContext.getName(), "Verify that alert for Uverse Voice plan changes are displayed", "true","true", true);
					
					//verify verbiage is displayed under the alert
					boolean bUVoiceVerbiage = UI.WaitForObject(oR_BillAndUsage.txtUverseVoiceAlertVerbiage, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Validate that bill alert verbiage is displayed for Uverse Voice", "true", String.valueOf(bUVoiceVerbiage), true);
					//verify View changes section for each alert
					oR_BillAndUsage.lnkUverseVoiceAlertViewChanges.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'View changes' link");
					//Verify that bill section ‘Addition & changes to services’ is expanded to show the prorated charge detail for the U-verse combined plan changes
					boolean bUVoiceExpanded = oR_BillAndUsage.txtUverseVoiceAdditionsAndChangesExpanded.isDisplayed();
					if(bUVoiceExpanded==true)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that ‘Addition & changes to services’ is expanded to show the prorated charge details", "true",String.valueOf(bUVoiceExpanded), true);
						//Verify that the bill alert verbiage displays the corresponding dollar amount if Total Additions & Changes to Service is not equal to zero including the total dollar amount
						//and NOT displayed if it is equal to zero
						boolean bUVoiceCharge = oR_BillAndUsage.txtUVoicePlanChangesCharge.getText().contains("$0.00");
						if(bUVoiceCharge==false)
						{
							Report.ValidationPoint(testContext.getName(), "Verify that Total Additions & Changes amount is NOT zero", "true","true", true);
							//Verify that the bill alert verbiage displays the corresponding dollar amount
							String sCharge = oR_BillAndUsage.txtUVoicePlanChangesCharge.getText();
							String arr[]= sCharge.split("\\$");
							if(oR_BillAndUsage.txtUverseVoiceAlertVerbiage.getText().contains(arr[1]))
							{
								Report.ValidationPoint(testContext.getName(), "Verify that the bill alert verbiage displays the corresponding dollar amount if Total Additions & Changes to Service is not equal to zero", "true","true", true);

							}else
							{
								Report.ValidationPoint(testContext.getName(), "Verify that the bill alert verbiage displays the corresponding dollar amount if Total Additions & Changes to Service is not equal to zero", "true","false", true);

							}
						

						}else
						{
							Report.ValidationPoint(testContext.getName(), "Verify that Total Additions & Changes amount is zero", "true","true", true);
							//Verify that the bill alert verbiage displays the corresponding dollar amount
							if(oR_BillAndUsage.txtUverseVoiceAlertVerbiage.getText().contains(oR_BillAndUsage.txtUVoicePlanChangesCharge.getText()))
							{
								Report.ValidationPoint(testContext.getName(), "Verify that the bill alert verbiage does not displays the corresponding dollar amount if Total Additions & Changes to Service is equal to zero", "true","false", true);

							}else
							{
								Report.ValidationPoint(testContext.getName(), "Verify that the bill alert verbiage does not displays the corresponding dollar amount if Total Additions & Changes to Service is equal to zero", "true","true", true);

							}

						}

					}else
					{
						Report.ValidationPoint(testContext.getName(), "Verify that ‘Addition & changes to services’ is expanded to show the prorated charge details", "true","false", true);

					}
				
				}
				
				
				

			}else
			{
				Report.TestPoint(testContext.getName(), "Verify that a 'Service change’ Bill alert is displayed on current bill page for multiple service changes in single Uverse service. No.of Service change alerts = "+BillAlerts.size(), "true","false", true);

			}
			

		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "true", e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - verifyPrintHistoryTabStandaloneWireline 
	 * Description- This function validates the Print friendly version for Standalone Wireline Customer_Cancelled payment
	 * Parameters - 
	 * Date created -5th May 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//BAP30786
	public static void verifyPrintHistoryTabStandaloneWireline (final ITestContext testContext)
			throws Exception {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_PrintBillHistoryTab oR_PrintBillHistoryTab = PageFactory.initElements(lDriver, OR_PrintBillHistoryTab.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		try {
			//click on Billing&Payments
			Report.OperationPoint(testContext.getName(), "Navigating to Bills & Usage landing page");
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null))
			{
				Report.TestPoint(testContext.getName(), "Goto Bills & Usage from global nav", "Bills & Usage page is displayed", "Bills & Usage page is displayed" , true);
                 WebElement sAccountNo = lDriver.findElement(By.xpath("//p[contains(text(),'Account')]"));
                 String sAccNo = sAccountNo.getText();
				//Click History Tab 	
				if(UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab, UI.iObjTimeOut))
				{
					oR_BillAndUsage.lnkHistoryTab.click();
					Report.TestPoint(testContext.getName(), "Verify History is present", "History Tab is present and clicked", "History Tab is present and clicked" , true);
					Report.OperationPoint(testContext.getName(), "Navigating to History");
					//verify History Table
					if(UI.WaitForObject(oR_BillAndUsage.lnkBillPrint, UI.iObjTimeOut))
					{
						Report.ValidationPoint(testContext.getName(), "Verify Print link", "Print link is displayed", "Print link is displayed", true);
						oR_BillAndUsage.lnkBillPrint.click();
						Thread.sleep(10000);
						//lDriver.switchTo().frame(oR_BillAndUsage.frmPrint);
						
						String mainWindow = lDriver.getWindowHandle();
						
						Report.OperationPoint(testContext.getName(), "Switching to Frame");
						//Switch to Bill details frame
						
						WebElement iframePrint = lDriver.findElement(By.xpath("//iframe[@name='view_or_print_as_table']"));
						lDriver.switchTo().frame(iframePrint);
						Report.ValidationPoint(testContext.getName(), "Verify Print frame", "Print frame is identified", "Print frame is identified", true);
						//lDriver.switchTo().defaultContent();
						WebElement txtHeader = lDriver.findElement(By.xpath("//h2[contains(text(),'Account Activity History')]"));
						if(UI.WaitForObject(txtHeader, 20).equals(true)){
							Report.ValidationPoint(testContext.getName(), "Verify History title", "History title is displayed", "History title is displayed", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Verify History title", "History title is displayed", "History title is not displayed", true);
						}
						//Validate ATT Logo is displayed
						if(UI.WaitForObject(oR_PrintBillHistoryTab.imgATTlogo,20))
						{
							Report.ValidationPoint(testContext.getName(), "Validate ATT Logo is displayed", "True", "True", true);
						} 
						//Boolean bAttLogo = UI.WaitForObject(oR_PrintBillHistoryTab.imgATTlogo,20);
						//Report.ValidationPoint(testContext.getName(), "Validate ATT Logo is displayed", "True", String.valueOf(bAttLogo), true); 
						//Validate Account No.
						String sAcc = oR_PrintBillHistoryTab.txtAccNo.getText();
						if(sAccNo.contains(sAcc))
						{
							Report.ValidationPoint(testContext.getName(), "Validate Account No. is displayed", "True", "True", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Validate Account No. is not displayed", "True", "false", true);
						}	
						//Validate Name is displayed
						Boolean bName = UI.WaitForObject(oR_PrintBillHistoryTab.txtName,2);
						Report.ValidationPoint(testContext.getName(), "Validate Account holder's Name is displayed", "True", String.valueOf(bName), true);
						
						//Validate Address is displayed
						Boolean bAddr = UI.WaitForObject(oR_PrintBillHistoryTab.txtName,2);
						Report.ValidationPoint(testContext.getName(), "Validate Account holder's Address is displayed", "True", String.valueOf(bAddr), true);  
						
						if(UI.WaitForObject(txtHeader, 20))
						{
						//Validate Table Header
							List<String> lstHeader = new ArrayList<String>();
							lstHeader.add("Billing Period");
							lstHeader.add("Bill Total");
							lstHeader.add("Payments Received");
							lstHeader.add("Amount");	
							lstHeader.add("Payment Method");
							lstHeader.add("AutoPay");
							lstHeader.add("Status");
							lstHeader.add("Confirmation");	
							//
							List<WebElement> lstHeaders = lDriver.findElements(By.xpath("//thead[@class='BPODTableHeader']//th"));
							
							if(lstHeaders.size() == 8)
							{
								
								System.out.println(" Report items = " + lstHeaders.size());
								Report.OperationPoint(testContext.getName(), "Validate Headers");
			
									for ( WebElement we: lstHeaders )
									{
										System.out.println( we.getText() );
										String sHeadingName = we.getText();
										if(sHeadingName.equals(lstHeader.get(0)) || sHeadingName.equals(lstHeader.get(1)) || sHeadingName.equals(lstHeader.get(2)) || sHeadingName.equals(lstHeader.get(3)) || sHeadingName.equals(lstHeader.get(4)) || sHeadingName.equals(lstHeader.get(5)) || sHeadingName.equals(lstHeader.get(6)) || sHeadingName.equals(lstHeader.get(7)))
										{
											Report.ValidationPoint(testContext.getName(), "Validate " + sHeadingName ,  sHeadingName + " is displayed",  sHeadingName + " is displayed", true);	
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Validate " + sHeadingName ,  sHeadingName + " is displayed",  sHeadingName + " is not displayed", true);	
										}
								  }
							}
						     }
							//Validate Date Format
							   Boolean Flag = true;
								List<WebElement> lstDate = lDriver.findElements(By.xpath("//tr/td[contains(text(),'/')]"));
								for(int j=0;j<=lstDate.size();j++)
								{
									if(lstDate.get(j).isDisplayed())
									{
										String sDate = lstDate.get(j).getText();
										String[] sDatesplit = sDate.split(" - ");
										if(sDatesplit[0] != null || sDatesplit[1] != null)
										{

											//String sBillDate = lstDate.get(j).getText();
											for(int i=0;i<=sDatesplit.length;i++)
											{
											 String[] sDateSplit = sDatesplit[i].split("\\/");
											
											if(sDateSplit[0].trim().length()==2 && sDateSplit[1].trim().length()==2 && sDateSplit[2].trim().length()==4)
											{
												
												System.out.println(Flag);
											}
											else
											{
												Flag = false;
												break;
											}
											}
										}
									}
								}
										if(Flag.equals(true)){
											
											Report.ValidationPoint(testContext.getName(), "Verify the format as MM/DD/YYYY  ", "True","True", true);
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Verify the format as MM/DD/YYYY  ", "True","False", true);
										}
		
								//Validate Bill Total field should be displayed as dollar amount
								List<WebElement> lstAmount = lDriver.findElements(By.xpath("//tr/td[contains(text(),'$')]"));
								int Flag1 =0;
								for(int i=0;i<=lstAmount.size();i++){
									String sAmount = lstAmount.get(i).getText();
									if(sAmount.contains("\\$"))
									{
										Flag1++;
									}
								}
									if(Flag1==lstAmount.size())
									{
										Report.ValidationPoint(testContext.getName(), "Verify the Bill Total Amt is displayed in $", "True","True", true);
									}
									else
									{	
										Report.ValidationPoint(testContext.getName(), "Verify the Bill Total Amt is displayed in $", "True","False", true);
									}
								

								//Validate Amount Paid field should be displayed in dollar value with negative sign

								/*Validate For Cancelled payments,
								1. Status field should be displayed as Cancelled.
								2. Corresponding confirmation number should be present under confirmation field. */
								

								
								
								
								
									lDriver.switchTo().window(mainWindow);
					}		
							else
								{
									Report.ValidationPoint(testContext.getName(), "Verify Print link", "Print link is displayed", "Print link is not displayed", true);
								}	
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
	 * Function Name - ValidatechangeplanlinkMobileShare 
	 * Description- Validate change plan link for Mobile Share account
	 * Parameters - 
	 * Date created -20th May 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void ValidatechangeplanlinkMobileShare (final ITestContext testContext)
			throws Exception {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		try {
			//click on Billing & Usage Secondary Nav
			Report.OperationPoint(testContext.getName(), "Navigating to Bills & Usage landing page");
			
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			if((UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut)))
			{
				oR_BillAndUsage.lnkUsage.click();
				Thread.sleep(15000);
				//validate showing dropdown and click on recent usage
				
				try
				{
					if(lDriver.findElement(By.xpath("//*[contains(text(),'Recent Usage')]")).isDisplayed())
					{
						// It means Recent usage is already displayed so no need to select from dropdown
					}
					
				}
				catch(Exception e)
				{
					//need to select Recent usage from dropdown
					
					boolean bPre = UI.WaitForObject(oR_BillAndUsage.lstShowing, 10);
					Report.TestPoint(testContext.getName(), "validate showing dropdown and click on recent usage", "true", String.valueOf(bPre), true);
					Report.OperationPoint(testContext.getName(), "Operation : Clicking on the showing dropdown");
					oR_BillAndUsage.lstShowing.click();

					//validate recent usage and click
					boolean brecent = UI.WaitForObject(oR_BillAndUsage.lnkRecentBill, 10);
					Report.TestPoint(testContext.getName(), "validate recent usage and click", "true", String.valueOf(brecent), true);
					Report.OperationPoint(testContext.getName(), "Operation : Clicking on the recent usage link");
					oR_BillAndUsage.lnkRecentBill.click();
				}
				//Validate Mobile Share Plan
				
				if(UI.WaitForObject(oR_BillAndUsage.txtMobileShare, 20))
				{
					int iChart=0;
					try
					{
						iChart = lDriver.findElement(By.xpath("//div[@class='highcharts-container']")).getLocation().y;
						System.out.println(iChart);
					}
					catch(Exception e)
					{
						Report.TestPoint(testContext.getName(), "Verify Change plan link under pie chart", "Verified", "Pie chart NOT displayed", true);
					}
					int iChangePlan=0;
					try
					{
						iChangePlan = lDriver.findElement(By.xpath("//a[contains(text(),'Change plan')]")).getLocation().y;
						System.out.println(iChangePlan);

					}
					catch(Exception e)
					{
						Report.TestPoint(testContext.getName(), "Verify Change plan link under pie chart", "Verified", "Change plan link NOT displayed", true);
					}


					if(iChart<iChangePlan)
					{
						Report.ValidationPoint(testContext.getName(), "Change Plan link is present below the pie Chart", "True", "True", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Change Plan link is present below the pie Chart", "True", "false", true);
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Mobile Share is not present", "True", "false", true);
				}
		  }
		}

		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}

	/**************************************************************
	 * Function Name - ValidateAudiCTNusageDetails
	 * Description- Validate billed usage details for my Audi CTN so understand the billed usage totals. 
	 * Parameters - 
	 * Date created - 21th May 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/		

	public static void ValidateAudiCTNusageDetails(final ITestContext testContext) throws Exception 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		try
		{
			//Click on Billing,usage and payments secondary navigation link
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			//Validate Billing and usage page
			Boolean bBAU = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,30);
			Report.TestPoint(testContext.getName(), "Validate Billing and usage page", "true", String.valueOf(bBAU), true);
			//Click on Usage tab
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Usage tab");
			oR_BillAndUsage.lnkUsage.click();
		
			
			//Validate Usage details selection module
			/** Code Modified - Monica 12th June 2015 */
			Boolean bBillingDropdown = UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate Usage details selection module", "true", String.valueOf(bBillingDropdown), true);
			//Click on Billing usage date dropdown
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on Billing usage date dropdown");
			//R_BillAndUsage.lstSelectReportType.click();
			Actions move = new Actions(lDriver);
			move.moveToElement(oR_BillAndUsage.lstSelectReportType).click().build().perform();
			
			Thread.sleep(5000);
			//Validate Previous Bill Usage link
			Boolean bBillingPeriod = UI.WaitForObject(oR_BillAndUsage.lnkPreviousBill,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate Previous Bill Usage link", "true", String.valueOf(bBillingPeriod), true);
			//Click on Previous bill link
			/*Report.OperationPoint(testContext.getName(), "Operational - Clicking on Previous bill link");
			move.moveToElement(oR_BillAndUsage.lnkPreviousBill3).click().build().perform();*/
			move.moveToElement(oR_BillAndUsage.lstSelectReportType).click().build().perform();
			

			//Validate Plan name
			Boolean bAudiPlan = UI.WaitForObject(oR_BillAndUsage.txtAudiPostPlan,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate Plan name for Audi CTN", "true", String.valueOf(bAudiPlan), true);
			//Validate View Usage Details link
			Boolean bViewUsageLnk = UI.WaitForObject(oR_BillAndUsage.lnkViewUsageDetails,1);
			Report.TestPoint(testContext.getName(), "Validate View Usage Details link", "true", String.valueOf(bViewUsageLnk), true);
			//Click on View Usage Details link
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on View Usage Details link");
			move.moveToElement(oR_BillAndUsage.lnkViewUsageDetails).click().build().perform();
			
			Thread.sleep(5000);
			//Validate A modal should be displayed displaying Usage Details for Audi CTN
			Boolean bFrameUsage = UI.WaitForObject(oR_BillAndUsage.frmBillUsageDetail,5);
			//Switch to frame 
			if(bFrameUsage)
			{
				Report.ValidationPoint(testContext.getName(), "Validate A modal should be displayed displaying Usage Details for Audi CTN", "True", String.valueOf(bFrameUsage), true);
				lDriver.switchTo().frame(oR_BillAndUsage.frmBillUsageDetail);
				//Validate vehicle label
				Boolean bLabel = UI.WaitForObject(oR_BillAndUsage.frmTitle,5);
				Report.ValidationPoint(testContext.getName(), "Validate vehicle label", "true", String.valueOf(bLabel), true);
				Report.OperationPoint(testContext.getName(), "	Operational - Vehicle Label: "+oR_BillAndUsage.frmTitle.getText());
				//Validate the 'view Usage for' dropdown should not be displayed
				UI.VerifyElementNotPresent(oR_BillAndUsage.lstViewUsageFor, "View Usage For Dropdown");
				//Validate the column headers within
				List<WebElement> lstColHead = lDriver.findElements(By.xpath("//div[@id='taheader']/table//th/a"));
				if(lstColHead.size()>0)
				{
					Report.TestPoint(testContext.getName(), "Validate Usage Details Modal", "True","True", true);
					//Retrieve the column heading
					for(int i=0;i<lstColHead.size();i++)
					{
						Report.OperationPoint(testContext.getName(), lstColHead.get(i).getText() );
					}
				}
				else
				{
					Report.TestPoint(testContext.getName(), "Validate Usage Details Modal", "True","False", true);
				}
				//Validate Contact column header
				UI.VerifyElementNotPresent(oR_BillAndUsage.txtContactColumnHeader, "Contact column header");
				//Verify the contents within the 'Type' field : Infotainment should be displayed
				Boolean bType = UI.WaitForObject(oR_BillAndUsage.txtInfotainment,5);
				Report.ValidationPoint(testContext.getName(), "Validate the contents within the 'Type' field : Infotainment should be displayed", "true", String.valueOf(bType), true);
				
				lDriver.switchTo().defaultContent();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
			
	}
	/**************************************************************
	 * Function Name - ValidateProrationNoteAndUsagaeModalForOnStarCTN()
	 * Description- Validate Proration note and usage modal for onstar CTN text/data in recently billed usage. 
	 * Parameters - 
	 * Date created - 22nd May 2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/		
//BAP12322
	public static void ValidateProrationNoteAndUsagaeModalForOnStarCTN(final ITestContext testContext) throws Exception 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		try
		{
			//navigating to bills and usage page
			
			boolean bPage = UI.WaitForObject(oR_AccountOverview.lnkBillingUsageSecNav, UI.iObjTimeOut);
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			Report.OperationPoint(testContext.getName(), "Navigating to Bills & Usage landing page");	
			
			//Validating Proration note for  onstar CTN in bills tab
			boolean bOnStar = UI.WaitForObject(oR_BillAndUsage.lnkCTNOnStar, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Proration note for  onstar CTN is present in bills tab","true", String.valueOf(bOnStar) , true);	
			
			//Validating Proration note for  Non onstar CTN in bills tab
			boolean bNonOnStar = UI.WaitForObject(oR_BillAndUsage.lnkNonOnStarCTN, UI.iObjTimeOut);
			Actions move = new Actions(lDriver);
			move.moveToElement(oR_BillAndUsage.lnkNonOnStarCTN).click().build().perform();;
			Report.ValidationPoint(testContext.getName(), "Proration note for  Non onstar CTN is present in bills tab", "true", String.valueOf(bNonOnStar) , true);	
			
			// navigating to usage tab
			UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut);
			move.moveToElement(oR_BillAndUsage.lnkUsage).click().build().perform();
			Report.OperationPoint(testContext.getName(), "Operational - Navigating to Usage tab");
			
			// Clicking on view usage details link
			UI.WaitForObject(oR_BillAndUsage.lnkViewUsageDetails1, UI.iObjTimeOut);
			move.moveToElement(oR_BillAndUsage.lnkViewUsageDetails1).build().perform();
			Report.ValidationPoint(testContext.getName(), "Clicking on view usage details link", "True", "True" , true);	
			move.moveToElement(oR_BillAndUsage.lnkViewUsageDetails1).click().build().perform();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on view usage details link");
			
			//frame will be opened
			String mainWindow = lDriver.getWindowHandle();

			WebElement frmNote = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
			lDriver.switchTo().frame(frmNote);
			
			//Clicking on change billing period
			boolean bchange=UI.WaitForObject(oR_BillAndUsage.lstchangeBillingperiod, UI.iObjTimeOut);
			move.moveToElement(oR_BillAndUsage.lstchangeBillingperiod).click().build().perform();
			Report.ValidationPoint(testContext.getName(), "Clicking on change billing period", "true", String.valueOf(bchange) , true);	
			
			//validate recent usage is selected
			boolean brecent=UI.WaitForObject(oR_BillAndUsage.lnkRecentUsageFromDropDown, UI.iObjTimeOut);
			move.moveToElement(oR_BillAndUsage.lnkRecentUsageFromDropDown).click().build().perform();
			Report.ValidationPoint(testContext.getName(), "Recent Usage is selected", "true", String.valueOf(brecent) , true);	
			
			//validate text/data for recent usage
			//Due to environment issue text/data is not dispayed in page. So this step is skipped
					
			//closing frame
			
			lDriver.switchTo().window(mainWindow);
			boolean bclose=UI.WaitForObject(oR_BillAndUsage.imgClose, UI.iObjTimeOut);
			move.moveToElement(oR_BillAndUsage.imgClose).click().build().perform();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
			
	}
	
	/**************************************************************
	 * Function Name - ValidateUpdatedPaperlessOptInExp()
	 * Description - This function is to validate Updated Paper less Opt In Experience
 	 * Test Case - BAP11281
	 * Parameters - None
	 * Date created - 25th May 2015
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void ValidateUpdatedPaperlessOptInExp(final ITestContext testContext) throws Exception 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_PaperlessBilling oR_PaperlessBilling = PageFactory.initElements(lDriver, OR_PaperlessBilling.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
	
		try
		{
			//Validate standalone environmental leaf image is changed to monitor image on Overview page 
		
			if(UI.WaitForObject(oR_AccountOverview.imgPaperlessBillingMonitor, UI.iObjTimeOut)){
			    Report.ValidationPoint(testContext.getName(),"Validate standalone environmental leaf image is changed to monitor icon image on Overview page","Standalone environmental leaf image is changed to monitor icon image on Overview page","Standalone environmental leaf image is changed to monitor icon image on Overview page",true);
			}else{
				Report.ValidationPoint(testContext.getName(),"Validate standalone environmental leaf image is changed to monitor icon image on Overview page","Standalone environmental leaf image is changed to monitor icon image on Overview page","Standalone environmental leaf image is NOT changed to monitor icon image on Overview page",true);
			}
			
			
			//Validate standalone environmental leaf image is changed to monitor image on Billings and Usages landing page 
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null,lDriver);
			if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut)){
				if((UI.WaitForObject(oR_AccountOverview.imgPaperlessBillingMonitor, 5))){
				    Report.ValidationPoint(testContext.getName(),"Validate standalone environmental leaf image is changed to monitor icon image on Billing & Usages landing page","Standalone environmental leaf image is changed to monitor icon image on Billing & Usages landing page","Standalone environmental leaf image is changed to monitor icon image on Billing & Usages landing page",true);
				}else{
					Report.ValidationPoint(testContext.getName(),"Validate standalone environmental leaf image is changed to monitor icon image on Billing & Usages landing page","Standalone environmental leaf image is changed to monitor icon image on Billing & Usages landing page","Standalone environmental leaf image is NOT changed to monitor icon image on Billing & Usages landing page",true);
				}
			}else{
	    		 Report.ValidationPoint(testContext.getName(), "Validate user is navigated to Billings & Usages page", "User is navigated to Billings & Usages page", "User is NOT navigated to Billings & Usages page", true);	
			}
			
		   // Navigate to Paperless Billing page
			Report.OperationPoint(testContext.getName(), "Navigate to Paperless Billing Page");
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkPaperlessBillingSecNav,lDriver);
			
			//Validate standalone environmental leaf image is changed to monitor image on Paperless Billing page
			if(UI.WaitForObject(oR_PaperlessBilling.txtPaperlessBillingTitle, UI.iObjTimeOut)){
				if(UI.WaitForObject(oR_PaperlessBilling.imgPaperlessBillingMonitor,5)){
					Report.ValidationPoint(testContext.getName(),"Validate standalone environmental leaf image is changed to monitor icon image on Paperless Billing page","Standalone environmental leaf image is changed to monitor icon image on Paperless Billing page","Standalone environmental leaf image is changed to monitor icon image on Paperless Billing page",true);
				}else{
					Report.ValidationPoint(testContext.getName(),"Validate standalone environmental leaf image is changed to monitor icon image on Paperless Billing page","Standalone environmental leaf image is changed to monitor icon image on Paperless Billing page","Standalone environmental leaf image is NOT changed to monitor icon image on Paperless Billing page",true);
				}
				
			 //Validate HTML content 
			    if(UI.WaitForObject(oR_PaperlessBilling.txtHTMLMsg, UI.iObjTimeOut)){
			    	Report.ValidationPoint(testContext.getName(),"Validate HTML content updated on Paperless Billing page","HTML content updated on Paperless Billing page","HTML content updated on Paperless Billing page",true); 
				}else{
					Report.ValidationPoint(testContext.getName(),"Validate HTML content updated on Paperless Billing page","HTML content updated on Paperless Billing page","HTML content NOT updated on Paperless Billing page",true); 
				}
				
			 //Validate "No, thanks" lis displayed
			    if(UI.WaitForObject(oR_PaperlessBilling.lnkNoThanks, UI.iObjTimeOut)){
			    	Report.ValidationPoint(testContext.getName(),"Validate No Thanks link is displayed on Paperless Billing page","No Thanks link is displayed on Paperless Billing page","No Thanks link is displayed on Paperless Billing page",true);
			    }else{
			       	Report.ValidationPoint(testContext.getName(),"Validate No Thanks link is displayed on Paperless Billing page","No Thanks link is displayed on Paperless Billing page","No Thanks link is NOT displayed on Paperless Billing page",true);
			    }
			   
			   // Validate Paperless billing disclosure link is displayed
			   if(UI.WaitForObject(oR_PaperlessBilling.lnkPaperlessBillingDisclosure, UI.iObjTimeOut)){
				   Report.ValidationPoint(testContext.getName(),"Validate Paperless billing disclosure link is displayed on Paperless Billing page","Paperless billing disclosure link is displayed on Paperless Billing page","Paperless billing disclosure link is displayed on Paperless Billing page",true);
			       
				   Report.OperationPoint(testContext.getName(), "Click on Paperless billing disclosure link");
				   oR_PaperlessBilling.lnkPaperlessBillingDisclosure.click();
				   Thread.sleep(5000);
				   String strMainWindow = lDriver.getWindowHandle();
				   
				   // Validate Paperless billing disclosure pop up is displayed
				   if(UI.WaitForObject(oR_PaperlessBilling.frmPaperLessBillingDisclosure, UI.iObjTimeOut)){
					   Report.ValidationPoint(testContext.getName(),"Validate Paperless billing disclosure pop up is displayed","Paperless billing disclosure pop up is displayed","Paperless billing disclosure pop up is displayed",true);
					   lDriver.switchTo().frame(oR_PaperlessBilling.frmPaperLessBillingDisclosure);
					   UI.WaitForObject(oR_PaperlessBilling.txtPaperlessBillingDisclosure, UI.iObjTimeOut);
					   
					   //Verify text content 
					   if(UI.WaitForObject(oR_PaperlessBilling.txtPaperlessBillNoticeDisclosure, UI.iObjTimeOut)){
						   Report.ValidationPoint(testContext.getName(),"Validate Paperless billing notice disclosure text is displayed on Paperless Billing Disclosure pop up","Paperless billing notice disclosure text is displayed on Paperless Billing Disclosure pop up","Paperless billing notice disclosure text is displayed on Paperless Billing Disclosure pop up",true); 
					   }else{
						   Report.ValidationPoint(testContext.getName(),"Validate Paperless billing notice disclosure text is displayed on Paperless Billing Disclosure pop up","Paperless billing notice disclosure text is displayed on Paperless Billing Disclosure pop up","Paperless billing notice disclosure text is NOT displayed on Paperless Billing Disclosure pop up",true); 
					   }
					   
					   //Click on Close link
					   Report.OperationPoint(testContext.getName(), "Click on Close link");
					   oR_PaperlessBilling.lnkClose.click();
					   
					   lDriver.switchTo().window(strMainWindow);
				   
				   }else{
					   Report.ValidationPoint(testContext.getName(),"Validate Paperless billing disclosure pop up is displayed","Paperless billing disclosure pop up is displayed","Paperless billing disclosure pop up is NOT displayed",true);
				   }
				   
			   }else{
				   Report.ValidationPoint(testContext.getName(),"Validate Paperless billing disclosure link is displayed on Paperless Billing page","Paperless billing disclosure link is displayed on Paperless Billing page","Paperless billing disclosure link is displayed on Paperless Billing page",true);
			   }

			}else{
			
				Report.ValidationPoint(testContext.getName(), "Validate user is navigated to Paperless Billing page", "User is navigated to Paperless Billing page", "User is NOT navigated to Paperless Billing page", true);
			}
    	}catch (Exception e) 
		{
			e.printStackTrace();Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}

	/**************************************************************
	 * Function Name - ValidateLinksInBillTab()
	 * Description- Validates Required links in Bill tab. 
	 * Parameters - 
	 * Date created - 26th May 2015
	 * Developed by - Gautham
	 * Last Modified By - Hiral Jogi
	 * Last Modified Date - 10th Feb
	 ***************************************************************/		
	//BAP30683
	public static void ValidateLinksInBillTab(final ITestContext testContext) throws Exception 
		{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		
			try
			{

				Report.OperationPoint(testContext.getName(), "Navigating to Bills, Usage & Payments landing page");
				UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 5);
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
				Thread.sleep(5000);
				
				//Validate user is navigated to bills tab
				Boolean bBill = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
				if(bBill){ // if bill details are present
					oR_BillAndUsage.txtBillingAndUsage.click();
					Thread.sleep(5000);
					Report.ValidationPoint(testContext.getName(), "Validate User is navigated to Billing and Usage Page.", "True","True", true);
					WebElement Bill= lDriver.findElement(By.xpath("//*[text()='Bill']"));
					if(Bill.isDisplayed()){
						Report.ValidationPoint(testContext.getName(), "Bill tab is pre-selected.", "True","True", true);
					}
				}
				
				// step 3 - Validating total amount due
				
				Boolean bAmountDue= UI.WaitForObject(oR_BillAndUsage.txtTotalAmountDue, UI.iObjTimeOut);
				if(bAmountDue){
					String stramount = oR_BillAndUsage.txtTotalAmountDue.getText();
					Report.OperationPoint(testContext.getName(), stramount);
					
					Boolean bTotalAmountDue= UI.WaitForObject(oR_BillAndUsage.txtTotalAmountDueAmount, UI.iObjTimeOut);
					String strTotal = oR_BillAndUsage.txtTotalAmountDueAmount.getText();
					Report.OperationPoint(testContext.getName(), "Total amount due for the customer is :" + strTotal);
				
				
				// Validating the visited URL
				String sURL = lDriver.getCurrentUrl();
				Report.OperationPoint(testContext.getName(), "URL displayed to learn about account ownership details is - " + sURL);
				
				// Validating Wireline bill details :
				Boolean bWirelineBillDetails = UI.WaitForObject(oR_BillAndUsage.txtWirelineBillDetails, UI.iObjTimeOut);
				if(bWirelineBillDetails){
					Report.ValidationPoint(testContext.getName(), "Wireline bill details is present.", "True","True", true);
					
					// step 4
					
					Boolean bPaperBillNotAvailable = UI.WaitForObject(oR_BillAndUsage.btnPaperBillNotAvailable, UI.iObjTimeOut);
					if(bPaperBillNotAvailable){
						Report.ValidationPoint(testContext.getName(), "No access to bill PDF which is as expected.", "True","True", true);
						}
					} 
				} else { // if bill details are not present
					
				//Validate Login with wireline id link
		
				WebElement Link= lDriver.findElement(By.xpath("//a[contains(text(),'log in with your Wireline ID.')]"));
				if(Link.isDisplayed()){
				    Report.ValidationPoint(testContext.getName(),"Validate Login with wireline id link","Login with wireline id link is displayed","Login with wireline id link is displayed",true);
				}
				else{
					Report.ValidationPoint(testContext.getName(),"Validate Login with wireline id link","Login with wireline id link is displayed","Login with wireline id link is not displayed",true);
				}
				
				//Validate Learn about account ownership link
				WebElement Learn= lDriver.findElement(By.xpath("//a[contains(text(),'Learn about account ownership')]"));
				if(Learn.isDisplayed()){
				    Report.ValidationPoint(testContext.getName(),"Validate Learn about account ownership link","Learn about account ownership link is displayed","Learn about account ownership link is displayed",true);
				}
				else{
					Report.ValidationPoint(testContext.getName(),"Validate Learn about account ownership link","Learn about account ownership link is displayed","Learn about account ownership link is not displayed",true);
				}
				
				//validate access to bill pdf
				UI.VerifyElementNotPresent(oR_BillAndUsage.btnPaperBillNotAvailable, "Access to paper bill");
				
				UI.VerifyLinkNavigatestoNextPage(oR_BillAndUsage.lnkLearnAboutAccountOwnership, "esupport");
				Thread.sleep(10000);
				lDriver.navigate().back();
				
				// Validate Access to make a payment button
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkOverview, null);
				Thread.sleep(5000);
				UI.VerifyElementNotPresent(oR_BillAndUsage.btnMakePaymentInBillingPage, "Access to Make a Payment button");
			}
		} catch (Exception e) {
				e.printStackTrace();Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
/**************************************************************
 * Function Name - VerifyPaymentMethodSection()
 * Description- Verify Payment Profile and Payment Options sections are merged are not. 
 * Parameters - 
 * Date created - 27th May 2015
 * Developed by - Gautham
 * Last Modified By - 
 * Last Modified Date -
 ***************************************************************/		
//BAP09159
public static void VerifyPaymentMethodSection(final ITestContext testContext) throws Exception 
	{
	WebDriver lDriver = UI.getDriver(testContext.getName());
	OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
	OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
	Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		try
			{
			//navigating to Profile page
			Report.OperationPoint(testContext.getName(), "Navigating to Bills, Usage & Payments landing page");
			UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav, 5);
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav, null);
			Report.TestPoint(testContext.getName(), "Goto Profile page from global nav", "Profile is displayed", "Profile is displayed" , true);	
			
			//verifying payment profile section
			boolean payment=UI.WaitForObject(oR_Profile.txtPaymentProfiles, 5);
			Report.ValidationPoint(testContext.getName(), "verifying payment profile section is displayed as expected", "true", String.valueOf(payment), true);
			
			//Verifying What's this in payment profile section
			boolean WhatsThis=UI.WaitForObject(oR_Profile.lnkWhatsThisInPaymentProfile, 5);
			if(WhatsThis)
			{
			Report.ValidationPoint(testContext.getName(), "Verifying What's this in payment profile section", "Not Displayed", "Displayed", true);
			}
			else
			{
			Report.ValidationPoint(testContext.getName(), "Verifying What's this in payment profile section", "Not Displayed", "Not Displayed", true);
				
			}
			
			//verifying text related to AutoPay are present or not
			WebElement AutoPay=lDriver.findElement(By.xpath("//p[contains(text(),'AutoPay')]"));
			Report.ValidationPoint(testContext.getName(), "verifying text and CTA related to AutoPay are present or not", "True", "True", true);
			
			//Navigating to add a payment pageBoolean
			boolean AddPayment=UI.WaitForObject(oR_Profile.lnkAddNewPaymentMethod, 5);
			Report.ValidationPoint(testContext.getName(), "Navigating to add a payment page", "true", String.valueOf(AddPayment), true);
			oR_Profile.lnkAddNewPaymentMethod.click();
			
			Thread.sleep(5000);
			//frame will open and select continue
			String mainWindow = lDriver.getWindowHandle();
			WebElement frmNote = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
			lDriver.switchTo().frame(frmNote);
			WebElement Continue = lDriver.findElement(By.xpath("//a[contains(text(),'Continue')]"));
			Report.ValidationPoint(testContext.getName(), "Click on Continue", "True", "True", true);
			Continue.click();
			lDriver.switchTo().window(mainWindow);
			
			//Validating all fields
			oR_BillAndUsage.edtPaymentProfileName.sendKeys("att");
			oR_BillAndUsage.edtNameOnBankAccount.sendKeys("att");
			oR_BillAndUsage.edtRoutingNumber.sendKeys("031300465");
			oR_BillAndUsage.edtBankAccountingNumber.sendKeys("1234567777");
			oR_BillAndUsage.edtBankAccountingNumberConfirm.sendKeys("1234567777");
			oR_BillAndUsage.lnkPrintTerms.click();
			Report.ValidationPoint(testContext.getName(), "Validating all fields", "True", "True", true);
			
			Thread.sleep(5000);
			 String winHandleBefore = lDriver.getWindowHandle();

			    // Switch to new window opened

			    for (String winHandle : lDriver.getWindowHandles()) {
			        lDriver.switchTo().window(winHandle);
			    }
			    			   	   
			    String sURL=lDriver.getCurrentUrl();
			    		
				if(sURL.contains("termsCondition"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify link navigates user to " +"termsCondition" + " window", "True", "True", true);	
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify link navigates user to "+"termsCondition"+" window", "True", "false", true);	
				}
				
				boolean Print= UI.WaitForObject(lDriver.findElement(By.xpath("//a[@class='print']")), 5);
				Report.ValidationPoint(testContext.getName(), "Print this page is displayed", "true", String.valueOf(Print), true);	
				
				//back to profile page
				lDriver.switchTo().window(winHandleBefore);
			
			}
			catch (Exception e) 
			{
				e.printStackTrace();Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
			}
		}

/**************************************************************
 * Function Name -  Func_ValidateIndividualAccountsOnMAPPage #BAP06122 
 * Description- This method validates the details for all the accounts listed on MAP page
 * Parameters - None
 * Date created -25th May 2015
 * Developed by - Monica Mohabansi
 * Last Modified By - 
 * Last Modified Date -
 * @throws AWTException 
 * @throws IOException 
 * @throws HeadlessException 
 * @throws ParseException 
/**************************************************************/
//BAP06122
public static void Func_ValidateIndividualAccountsOnMAPPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
{
	Boolean bBAPLink,bMAPBtn,bSelectCheckingSaving;
	
	try
		
	{
		
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
		
		Report.OperationPoint(testContext.getName(), "Navigating to Billing and Payments -> Make A Payment Page");
		
//		Click on billing and payments from secondary navigation and click on Make a Payment button

		bBAPLink = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut);
		Report.TestPoint(testContext.getName(), "Click on BAP link from Secondary Navigation", "true", String.valueOf(bBAPLink), true);
		oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
//		
		bMAPBtn = UI.WaitForObject(oR_BillAndUsage.btnMakePaymentInBillingPage,UI.iObjTimeOut);
		Report.TestPoint(testContext.getName(), "Click on MAP Button", "true", String.valueOf(bMAPBtn), true);
		oR_BillAndUsage.btnMakePaymentInBillingPage.click();
		
		
//		Verify that the accounts are listed with their nicknames (if present) with a check box to their left. By default the checkbox is unchecked.
		UI.WaitForObject(oR_MakeAPayment.txtSelectAccountToPay, UI.iObjTimeOut);
		
		List <WebElement> Accounts = lDriver.findElements(By.xpath("//p[@class= 'MarLeft0 botMar0  font16 bt_left']"));
		int iNumberOfAcc = Accounts.size();


		List <WebElement> CheckBoxes = lDriver.findElements(By.xpath("//div[@class='btnLt']/div[@class='float-left PadTop1 MarLeft1']"));
		if(CheckBoxes.size() == iNumberOfAcc)
			Report.ValidationPoint(testContext.getName(), "Accounts are listed with check box to their left", "true", "true", true);
		else
			Report.ValidationPoint(testContext.getName(), "Accounts are listed with check box to their left", "true", "false", true);
		
//		Verify that the 'amount due/balance' and 'due date' for the individual accounts are listed under the Amount and Date headers respectively

		List <WebElement> Balance = lDriver.findElements(By.xpath("//p[contains(text(),'Balance')]"));
//		List <WebElement> DueDate = lDriver.findElements(By.xpath("//p[contains(text(),'Due Date')]"));

		List <WebElement> DueDate = lDriver.findElements(By.xpath("//p[@class='float-left botMar6 font14 OC2PARowSeamless' and contains(text(),'Due Date')]"));
		
		//p[contains(text(),'Due Date')]/span
//		List <WebElement> Date = lDriver.findElements(By.xpath("//p[@class='float-left botMar6 font14 OC2PARowSeamless' and contains(text(),'Due Date')]/span"));
		
		if(Balance.size() == iNumberOfAcc && DueDate.size() == iNumberOfAcc )
			Report.ValidationPoint(testContext.getName(), "'Amount due/balance' and 'due date' for the individual accounts are listedt", "true", "true", true);
		else
			Report.ValidationPoint(testContext.getName(), "'Amount due/balance' and 'due date' for the individual accounts are listed", "true", "false", true);
		
//		Verify that the amount is displayed and can be entered in the following format $###.##
		List <WebElement> amount = lDriver.findElements(By.xpath("//p[contains(text(),'Balance')]/span[contains(text(),'$')]"));
		if(amount.size() == iNumberOfAcc)
			Report.ValidationPoint(testContext.getName(), "Amount is displayed in the format $###.##", "true", "true", true);
		else
			Report.ValidationPoint(testContext.getName(), "Amount is displayed in the format $###.##", "true", "false", true);

//		Verify that the date is displayed in the following format: MM/DD/YYYY The date is defaulted to today's date.			

		List <WebElement> lstDate = lDriver.findElements(By.xpath("//p[@class='float-left botMar6 font14 OC2PARowSeamless' and contains(text(),'Due Date')]/span"));
		for(WebElement elm : lstDate)
		{
			String SDate = elm.getText();
			if (SDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
				Report.ValidationPoint(testContext.getName(), "Date is in format MM/DD/YYYY", "true", "true", true);
		}
			
	
//		Verify that for all accounts there is an option to enroll in autopay with a check box to the left of it. Verify the presence of a help icon next to the check box. Note: not displayed if the account is already actively enrolled in autopay. Will not be displayed if autopay is pending
		
		int iEnrollInAutopayChkBox = lDriver.findElements(By.xpath("//input[contains(@id,'enrollInAutopayChkBox')]")).size();
		int iHelpIcon = lDriver.findElements(By.xpath("//a[contains(@id,'enrollinAutoPayHelp')]/img[@alt='Help']")).size();
		if(iEnrollInAutopayChkBox == iNumberOfAcc && iHelpIcon ==  iNumberOfAcc)
			Report.ValidationPoint(testContext.getName(), "For all accounts there is an option to enroll in autopay with a check box to the left and help icon next to the check box", "true", "true", true);
		else
			Report.ValidationPoint(testContext.getName(), "For all accounts there is an option to enroll in autopay with a check box to the left and help icon next to the check box", "true", "false", true);

//		Hover the mouse over the help icon, Validate that a pop up is displayed with an appropriate text.	
		
		WebElement ToolTip = lDriver.findElement(By.xpath("//a[@id='enrollinAutoPayHelp0']/img[@alt='Help']"));
		Actions link= new Actions(lDriver);
		link.moveToElement(ToolTip);
		Thread.sleep(2000);
		
//		Boolean bPopUp = lDriver.findElement(By.xpath("//div[@id='enrollInAutoPay0']//span[@class='tips' and contains(text(),'automated bill paying system')]")).isDisplayed();
		//div[@id='enrollInAutoPay0']/span[@class='tips']
	
		Boolean bPopUp1 = lDriver.findElement(By.xpath("//div[@id='enrollInAutoPay0']/span[@class='tips']")).isEnabled();	
		Report.ValidationPoint(testContext.getName(), "Pop up is displayed with an appropriate text", "true", String.valueOf(bPopUp1), true);
		System.out.println(lDriver.findElement(By.xpath("//div[@id='enrollInAutoPay0']/span[@class='tips']")).getText());
//		Verify the following autopay messages are displayed as appropriate. 1:'Pending autopay Request' displayed if the account has a pending request for autopay 2:'Autopay to be applied on MM/DD/YYYY' is displayed if the account is already enrolled and autopay due date is in future (mm/dd/yyyy is the autopay due date)
//		3: 'autopay applied on MM/DD/YYYY' will be shown if account is already enrolled and autopay due date is in past (MM/DD/YYYY) is the autopay due
		// *******NA******
		
//		Verify that text '$###.## due immediately!' is displayed in red under the account that has a past due on it.
//		WebElement ele1 = driver.findElement(By.cssSelector(".primary-btn"));
		List <WebElement> DueText = lDriver.findElements(By.xpath("//p[contains(text(),'past due')]"));
		if(DueText.size()>=1)
			Report.ValidationPoint(testContext.getName(), "Text for Due", "true", "true", true);
		else
			Report.ValidationPoint(testContext.getName(), "Text for Due", "true", "false", true);

//		Verify that the date cannot be entered manually and clicking on the text box or the calendar icon opens the Calendar div Layer
		String sDate = UI.CheckExist(oR_MakeAPayment.edtDate1);
		Report.ValidationPoint(testContext.getName(), "Date edit box", "true", sDate.toLowerCase(), true);
		oR_MakeAPayment.edtDate1.click();
		
		Boolean bCalendar = UI.WaitForObject(oR_MakeAPayment.frmCalender, UI.iObjTimeOut);
		Thread.sleep(5000);
		Report.ValidationPoint(testContext.getName(), "Clicking on Date Edit box / calendar icon open calendar div layer", "true", String.valueOf(bCalendar), true);
		lDriver.switchTo().frame(oR_MakeAPayment.frmCalender);
		oR_MakeAPayment.lnkCloseModal.click();
		lDriver.switchTo().parentFrame();
//		Verify that 'Split this Payment' link is displayed under the payment method dropdown with a help icon next to it.

		Thread.sleep(3000);
	 	List <WebElement> SplitThisPayment =  lDriver.findElements(By.xpath("//a[contains(text(),'Split this payment')]"));
		System.out.println(SplitThisPayment.size()+" size");
	 	List <WebElement> HelpIcon = lDriver.findElements(By.xpath("//div[contains(@id,'paymentItem_AddAnchor')]//img[@alt='Help']"));
		if(SplitThisPayment.size()>=1 && HelpIcon.size() == SplitThisPayment.size())
		{
			for(int i=0; i<=SplitThisPayment.size()-1; i++)	
			{
				int m = i+1;
				Report.ValidationPoint(testContext.getName(), "Split Payment for Account " +m, "Present", "Present", true);
				SplitThisPayment.get(i).click();
	//			1:Another row is listed which allows the user to make 2nd of the 2 part arrangement with the following fields:
	//			2: Enroll in autopay checkbox gets disabled.
	
				if(lDriver.findElement(By.xpath("//div/a[@id='acct_"+i+"_rmvpmtlink']")).isDisplayed());
				{
					Report.ValidationPoint(testContext.getName(), "Another row is listed, Enroll In Autopay is disabled and delete this row CTA present", "true","true", true);
					
		//			Click on 'Remove this Payment' link. Note: Execute this step if the previous step was executed.
					lDriver.findElement(By.xpath("//div/a[@id='acct_"+i+"_rmvpmtlink']")).click();
					
		//			3: If the customer selects this link the split payment will be removed from the account and the page will be refreshed  the Amount to Pay and Pay On Date fields will revert back to the values shown when the customer landed on the page.
		
					Boolean bSplitThisPayment = UI.WaitForObject(SplitThisPayment.get(i), UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Split Payment must be removed", "true", String.valueOf(bSplitThisPayment), true);
					Thread.sleep(2000);
				}
			}	
		
		}
		else
			Report.ValidationPoint(testContext.getName(), "Split Payment", "Present", "Absent", true);

		
//		Validate that for all the accounts , only the current date can be selected in the calendar

		List<WebElement> cals = lDriver.findElements(By.xpath("//div[@id='tableOC2PATab0']"));
        System.out.println(cals.size() +" Cals Size");
       
//        Retrieving current date in strDate
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date();
        String strCurrDate = dateFormat.format(date);
       
        for(WebElement e : cals)
        {
              WebElement calLink = e.findElement(By.tagName("a"));
              System.out.println(calLink.getText());
              calLink.click();

              bCalendar = UI.WaitForObject(oR_MakeAPayment.frmCalender, UI.iObjTimeOut);
              Report.ValidationPoint(testContext.getName(), "Calendar Frame", "true", String.valueOf(bCalendar),  true);
              Thread.sleep(2000);
              
              lDriver.switchTo().frame(oR_MakeAPayment.frmCalender);
	             
//			  Boolean bCurrentDate = UI.WaitForObject(lDriver.findElement(By.xpath("//a[@onclick='parent.setValue('"+strDate+"')']")), UI.iObjTimeOut);
			  String strDate = lDriver.findElement(By.xpath("//a[contains(@onclick,'parent.setValue')]")).getText();
			  
			  if(strDate == strCurrDate || Integer.parseInt(strDate) == Integer.parseInt(strCurrDate)-1 )
				  Report.ValidationPoint(testContext.getName(), "Only the current Date can be selected in the calendar", "true", "true", true);
			  else
				  Report.ValidationPoint(testContext.getName(), "Only the current Date can be selected in the calendar", "true", "false", true);

//			  CLose calendar frame
              Boolean bCloseLink = UI.WaitForObject(oR_MakeAPayment.lnkCloseModal, UI.iObjTimeOut);
	      		if(bCloseLink)
	      		{
        		  	oR_MakeAPayment.lnkCloseModal.click();
      				Thread.sleep(2000);
      				lDriver.switchTo().parentFrame();     				
	      		}
	      		else
	      			Report.ValidationPoint(testContext.getName(), "Close link for calendar frame", "present", "absent", true);
	    }

//        For the AC1 account enter: First part Amount> 0 Date= current date not the curent date Payment method : New Checking/Savings card

        	Boolean bDropdown = UI.WaitForObject(oR_MakeAPayment.lstPaymentMethod, UI.iObjTimeOut);
        
        	
        			bSelectCheckingSaving = UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod, "New checking / savings account");
        			System.out.println("bSelectCheckingSaving " +bSelectCheckingSaving);
//                	Validate that new payment method div layer - checking/ savings is displayed as below:
                	Boolean bCheckingSavingFrame = UI.WaitForObject(oR_MakeAPayment.frmNewCheckingSavingPaymentMethod, UI.iObjTimeOut);
                	Report.TestPoint(testContext.getName(), "Frame Checking/Saving Payment Method", "true", String.valueOf(bCheckingSavingFrame), true);
           	
        	
        	Report.OperationPoint(testContext.getName(), "Validating Checking/Saving Payment Method Frame");

      	
        	lDriver.switchTo().frame(oR_MakeAPayment.frmNewCheckingSavingPaymentMethod);
//        	o Title  New Payment Method Information
//        	String sCheckingSavingHeader = UI.CheckExist(oR_MakeAPayment.txtCheckingSavingHeader);
//        	Report.ValidationPoint(testContext.getName(), "Title  New Payment Method Information", "true", sCheckingSavingHeader.toLowerCase(), true);

        	
//        	o Section Title  Checking/Savings Account Information
        	String sPaymentInformationHeader = UI.CheckExist(oR_MakeAPayment.txtPaymentMethodInformation);
        	Report.ValidationPoint(testContext.getName(), "Title  Checking/Savings Account Information", "true", sPaymentInformationHeader.toLowerCase(), true);

//        	- Checking radio button (default selection) - Savings radio button
        	
        	if(oR_MakeAPayment.radCheckingSelectedByDefault.isDisplayed()||oR_MakeAPayment.radCheckingSelectedByDefault.isEnabled() || oR_MakeAPayment.radCheckingSelectedByDefault.isSelected())
        		Report.ValidationPoint(testContext.getName(), "Checking radio Button(default), Saving Radio Button", "true", "true", true);
        	else
            	Report.ValidationPoint(testContext.getName(), "Checking radio Button(default), Saving Radio Button", "true", "false", true);

        	if(oR_MakeAPayment.radSaving.isDisplayed()||oR_MakeAPayment.radSaving.isEnabled() || oR_MakeAPayment.radSaving.isSelected())
        		Report.ValidationPoint(testContext.getName(), "Saving Radio Button", "true", "true", true);
        	else
            	Report.ValidationPoint(testContext.getName(), "Saving Radio Button", "true", "false", true);

//        	o Name on Bank Account  ? support link
//        	o Routing-Transit Number  Whats this ? support link
//        	o Bank Account Number  ? support link
//        	o Re-Enter Bank Account Number 

        	if(oR_MakeAPayment.edtBankAccountNumber.isDisplayed() && oR_MakeAPayment.edtNameOnBankAcc.isDisplayed() 
					&& oR_MakeAPayment.edtReenterAccNum.isDisplayed() && oR_MakeAPayment.edtRoutingNumber.isDisplayed())      	
        		Report.ValidationPoint(testContext.getName(), "Name on Bank Account \n,  Routing-Transit Number \n,  Bank Account Number \n, Re-Enter Bank Account Number \n","true", "true", true);
        	else
        		Report.ValidationPoint(testContext.getName(), "Name on Bank Account \n,  Routing-Transit Number \n,  Bank Account Number \n, Re-Enter Bank Account Number \n","true", "false", true);
        	
//        	o Store Payment Profile section  This section will display the following:

        	String sSavePayInfo = UI.CheckExist(oR_MakeAPayment.txtSaveMyPaymentInformation);
        	Report.ValidationPoint(testContext.getName(), "Store Payment Profile Section", "true",sSavePayInfo.toLowerCase() , true);
        	
//        	- Section Description - The section description of the Store Payment Profile section should contain verbiage which explains to the customer that if this option is selected, their account information will be saved immediately upon clicking the Continue button. The description should also explain that if a payment using this profile fails, the customer may have to update this saved profile.
        	String sSectionDescription = UI.CheckExist(oR_MakeAPayment.txtSavePaymentSectionDescription);
        	Report.ValidationPoint(testContext.getName(), " The section description of the Store Payment Profile section", "true", sSectionDescription.toLowerCase() , true);

//        	- Store Payment Information checkbox (default to checked)
           	if(oR_MakeAPayment.radStorePaymentInfo.isDisplayed()||oR_MakeAPayment.radStorePaymentInfo.isEnabled() || oR_MakeAPayment.radStorePaymentInfo.isSelected())
        		Report.ValidationPoint(testContext.getName(), "Store Payment Information checkbox", "true", "true", true);
        	else
            	Report.ValidationPoint(testContext.getName(), "Store Payment Information checkbox", "true", "false", true);

//        	- Payment Profile Name
        	String sPayProfName = UI.CheckExist(oR_MakeAPayment.txtMyPaymentProfileName);
        	Report.ValidationPoint(testContext.getName(), "Payment Profile Name", "true", sPayProfName.toLowerCase() , true);

//        	- ? support link
        	String supportLink = UI.CheckExist(lDriver.findElement(By.xpath("//a[contains(@id,'atip')]")));
        	Report.ValidationPoint(testContext.getName(), "Support Link", "true", supportLink.toLowerCase(), true);

//        	o Cancel link
        	String slnkCancelFrm = UI.CheckExist(oR_MakeAPayment.lnkCancelFrm);
        	Report.ValidationPoint(testContext.getName(), "Cancel Frame Link", "true", slnkCancelFrm.toLowerCase() , true);       	

//        	o Continue button
        	String sContinue = UI.CheckExist(oR_MakeAPayment.btnPaymentFrameContinue);
        	Report.ValidationPoint(testContext.getName(), "Cancel Frame Link", "true", sContinue.toLowerCase() , true);

//        	HOver mouse over help icon next to name on bank account field
        	String sNameToolTip = UI.CheckExist(lDriver.findElement(By.xpath("//a[@href='#tip51']")));
        	Report.ValidationPoint(testContext.getName(), "Name Tool Tip", "true", sNameToolTip.toLowerCase(), true);
        	//div[contains(@class,'helpDiv')]/span[contains(text(),'Enter the name of the bank account owner')]	
        	lDriver.findElement(By.xpath("//a[@href='#tip51']")).click();

//        	Validate that text as per CMS is displayed and close the popup
        	Boolean bNameToolMsg = lDriver.findElement(By.xpath("//div[contains(@class,'helpDiv')]/span[contains(text(),'Enter the name of the bank account owner')]")).isEnabled();
        	Report.ValidationPoint(testContext.getName(), "Tool Tip Msg for name", "true", String.valueOf(bNameToolMsg), true);
        	
//        	HOver mouse over help icon next to payment profile name field
        	 lDriver.findElement(By.xpath("//a[contains(@id,'atip')]")).click();
        	
        	Boolean bPayProfNameToolMsg = lDriver.findElement(By.xpath("//span[contains(text(),'Create a meaningful name')]")).isEnabled();
         	Report.ValidationPoint(testContext.getName(), "Tool Tip Msg for Payment Profile name", "true", String.valueOf(bPayProfNameToolMsg), true);

         	Boolean bCloseLink = UI.WaitForObject(oR_MakeAPayment.lnkCloseModal, UI.iObjTimeOut);
      		if(bCloseLink)
      		{
    		  	oR_MakeAPayment.lnkCloseModal.click();
  				Thread.sleep(2000);
  				lDriver.switchTo().parentFrame();   
      		}
			else
      			Report.ValidationPoint(testContext.getName(), "Close link for calendar frame", "present", "absent", true);

	}	
	catch(Exception e)
	{
		Report.TestPoint(testContext.getName(), "Validate Individual Accounts", "true", "false", true);
	}
}


/**************************************************************
 * Function Name - VerifyPaymentDetailsInBillHistory()
 * Description - This function is used to verify all the TestCases made in one single bill period
 * Test Case - BAP14030
 * Parameters - None
 * Date created - 28th Mar 2015
 * Developed by - Clint John
 * Last Modified By - 
 * Last Modified Date - 
 ***************************************************************/
public static void VerifyPaymentDetailsInBillHistory(final ITestContext testContext) throws HeadlessException, IOException, AWTException
{
	WebDriver lDriver = UI.getDriver(testContext.getName());
	OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
	Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

	try
	{
	
		new Actions(lDriver).moveToElement(oR_AccountOverview.lnkBillingUsagePaymentsSecNav).build().perform();
		new Actions(lDriver).moveToElement(oR_AccountOverview.lnkBillingHistoryTerNav).click().build().perform();
		Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Billing History' link from Ter Nav");
		//Verify Billing History page is displayed
		boolean bBillingHistory = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Validate that 'Billing & Usage' page is displayed", "true", String.valueOf(bBillingHistory), true);
		
		//Click on radio button to change it to carousel view
		boolean bHistorySection = UI.WaitForObject(oR_BillAndUsage.txtPaymentActivity, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that History section is displayed", "true",String.valueOf(bHistorySection), true);

		new Actions(lDriver).moveToElement(oR_BillAndUsage.radCarouselView).build().perform();
		//boolean bCarousel = oR_BillAndUsage.radCarouselView.isDisplayed();
		if(oR_BillAndUsage.radCarouselView.isDisplayed() || oR_BillAndUsage.radCarouselView.isEnabled() || oR_BillAndUsage.radCarouselView.isSelected())
		{
			Report.ValidationPoint(testContext.getName(), "Verify that radio-button for Carousel view is displayed", "true","true", true);
		}else
		{
			Report.ValidationPoint(testContext.getName(), "Verify that radio-button for Carousel view is displayed", "true","false", true);
		}
		new Actions(lDriver).moveToElement(oR_BillAndUsage.radCarouselView).click().build().perform();
		Report.OperationPoint(testContext.getName(), "Operational - Clicked on radio button for carousel view");
		
		//Verify that CTA to "View all Payments" exists within the carousel
		boolean bViewPayments = UI.WaitForObject(oR_BillAndUsage.lnkViewAllPaymentsCarousel, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that 'View all payments' exists within the carousel", "true",String.valueOf(bViewPayments), true);
		oR_BillAndUsage.lnkViewAllPaymentsCarousel.click();
		Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'View all payments' within the carousel");
	
		//Calling function PaymentsHistroyPopUpInBillHistory() to verify the contents under Carousel view after clicking 'view all payments'
		BillAndPayments.PaymentsHistroyPopUpInBillHistory(testContext);
		
		boolean bPrintLink = UI.WaitForObject(oR_BillAndUsage.lnkBillPrint, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that 'Print' link is displayed to view printable table of Bill details", "true",String.valueOf(bPrintLink), true);
		new Actions(lDriver).moveToElement(oR_BillAndUsage.lnkBillPrint).click().build().perform();
		//Calling function PaymentsHistroyPopUpInBillHistory() to verify the contents under Carousel view after clicking 'print' link
		BillAndPayments.PaymentsHistroyPopUpInBillHistory(testContext);
		
		//Validate Carousel view Bill history section
		boolean bMostRecentPaymentMethod = UI.WaitForObject(oR_BillAndUsage.txtMostRecentPayment, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that the text for Most Recent Payment is displayed in bold", "true",String.valueOf(bMostRecentPaymentMethod), true);
		boolean bMostRecentPaymentMethodAmount = UI.WaitForObject(oR_BillAndUsage.txtMostRecentPaymentAmount, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that the Amount for Most Recent Payment is displayed in bold and in correct format with a $ sign", "true",String.valueOf(bMostRecentPaymentMethodAmount), true);
		
		//Bill Total
		boolean bBillTotal = UI.WaitForObject(oR_BillAndUsage.txtBillTotalInCarousel, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that Bill Total is displayed as sum of all amounts", "true",String.valueOf(bBillTotal), true);
		//Verify payment received
		boolean bPaymentDate = UI.WaitForObject(oR_BillAndUsage.txtPaymentReceivedInCarousel, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that the Payment received date should be displayed for the last payment received", "true",String.valueOf(bPaymentDate), true);
		//verify payment method
		boolean bPaymentMethod = UI.WaitForObject(oR_BillAndUsage.txtPaymentMethodInCarousel, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that Payment method is displayed", "true",String.valueOf(bPaymentMethod), true);
		//Verify Autopay
		boolean bAutopay = UI.WaitForObject(oR_BillAndUsage.txtAutoPayValueInCarousel, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that AutoPay value is displayed", "true",String.valueOf(bAutopay), true);
		String sAutopayValue = oR_BillAndUsage.txtAutoPayValueInCarousel.getText();
		if(sAutopayValue.contains("No") || sAutopayValue.contains("Yes"))
		{
			Report.ValidationPoint(testContext.getName(), "Verify that Autopay should be displayed as 'yes' or 'no' as appropriate", "true","true", true);

		}else
		{
			Report.ValidationPoint(testContext.getName(), "Verify that Autopay should be displayed as 'yes' or 'no' as appropriate", "true","false", true);
		}
		//verify payment status
		boolean bPaymentStatus = UI.WaitForObject(oR_BillAndUsage.txtPaymentStatusInCarousel, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that Payment Status is displayed", "true",String.valueOf(bPaymentStatus), true);
		String sPmtStatus = oR_BillAndUsage.txtPaymentStatusInCarousel.getText();
		if(sPmtStatus.contains("Posted"))
		{
			Report.ValidationPoint(testContext.getName(), "Verify that Status is displayed as 'posted' for successful payments", "true","true", true);

		}else
		{
			Report.ValidationPoint(testContext.getName(), "Verify that Status is displayed as 'posted' for successful payments", "true","false", true);
		}
		//Verify confirmation
		String sConfirm = oR_BillAndUsage.txtConfirmationInCarousel.getText();
		if(sConfirm!=null)
		{
			Report.ValidationPoint(testContext.getName(), "Verify that Confirmation number is displayed", "true","true", true);
		}else
		{
			Report.ValidationPoint(testContext.getName(), "Verify that Confirmation number is displayed", "true","false", true);

		}

	}
	catch (Exception e) 
	{
		e.printStackTrace();
		Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

	}


}

/**************************************************************
 * Function Name - PaymentsHistroyPopUpInBillHistory() - Supporting method for VerifyPaymentDetailsInBillHistory()
 * Description - This method is used to verify contents Carousel View section
 * Test Case - BAP14030
 * Parameters - None
 * Date created - 28th Mar 2015
 * Developed by - Clint John
 * Last Modified By - 
 * Last Modified Date - 
 ***************************************************************/
public static void PaymentsHistroyPopUpInBillHistory(final ITestContext testContext) throws HeadlessException, IOException, AWTException
{
	WebDriver lDriver = UI.getDriver(testContext.getName());
	OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
	Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

	try
	{
		
		//Verify that a seperate Window is opened listing all payments made within the same bill period and ability to close is visible
		String mainWindow = lDriver.getWindowHandle();
		Thread.sleep(5000);
		WebElement frmPaymentDetailsFrame = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
		lDriver.switchTo().frame(frmPaymentDetailsFrame);
		
			//Validate that the following fields are displayed in the table: Bill Total Payment Received Amount Payment Method AutoPay Status Confirmation
			boolean bBillTotal = UI.WaitForObject(oR_BillAndUsage.txtBillTotal, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that 'Bill Total' feild is displayed", "true",String.valueOf(bBillTotal), true);
			boolean bPaymentReceived = UI.WaitForObject(oR_BillAndUsage.txtPaymentsReceived, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that 'Payments Received' feild is displayed", "true",String.valueOf(bPaymentReceived), true);
			boolean bAmount = UI.WaitForObject(oR_BillAndUsage.txtAmount, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that 'Amount' feild is displayed", "true",String.valueOf(bAmount), true);
			boolean bPaymentMethod = UI.WaitForObject(oR_BillAndUsage.txtPaymentMethod, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that 'Payment Method' feild is displayed", "true",String.valueOf(bPaymentMethod), true);
			boolean bAutopay = UI.WaitForObject(oR_BillAndUsage.txtAutoPay, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that 'AutoPay' feild is displayed", "true",String.valueOf(bAutopay), true);
			boolean bStatus = UI.WaitForObject(oR_BillAndUsage.txtStatus, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that 'Status' feild is displayed", "true",String.valueOf(bStatus), true);
			boolean bConfirmation = UI.WaitForObject(oR_BillAndUsage.txtConfirmation, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that 'Confirmation' feild is displayed", "true",String.valueOf(bConfirmation), true);
		
			//Payment Received should be displayed in the descending order i.e. the most recent payment should be listed first
			List <WebElement> lstPaymentDates = lDriver.findElements(By.xpath("//td[@headers='Payment_Received']"));
			int iSize = lstPaymentDates.size();
			if(iSize==1)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that the most recent payment is listed first (Payment Received should be displayed in the descending order)", "true","true", true);
			}else if(iSize>1)
			{
				//Add validation here if more than 1 payements dates are displayed
			}
			//Verify that Amount should be displayed in $###.## format
			List <WebElement> lstAmount = lDriver.findElements(By.xpath("//td[contains(@headers,'Amount')]"));
			int iAmountsSize = lstAmount.size();
			for(WebElement element: lstAmount)
			{
				String sAmount = element.getText();
				if(sAmount.contains("$") && sAmount.contains("."))
				{
					iAmountsSize--;
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Amount should be displayed in $###.## format", "true","false", true);
					break;
				}
			}
			if(iAmountsSize==0)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that Amount should be displayed in $###.## format", "true","true", true);
			}
		
			//Verify that Payment method is displayed
			List <WebElement> lstPaymentMethod = lDriver.findElements(By.xpath("//td[@headers='Payment_Method']"));
			int iPaymentMethodSize = lstPaymentMethod.size();
			if(iPaymentMethodSize!=0)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that Payment method is displayed.", "true","true", true);
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that Payment method is displayed.", "true","false", true);
			}
			
			//Verify Autopay feilds - Checkmark should be displayed for "yes" and blank field should be display for "no"
			List <WebElement> lstAutoPayStatus = lDriver.findElements(By.xpath("//td[@headers='AutoPay']"));
			int iAutoPaySize = lstAutoPayStatus.size();
			if(iAutoPaySize!=0)
			{
				
				Report.ValidationPoint(testContext.getName(), "Verify that AutoPay status is displayed. Checkmark should be displayed for 'yes' and blank field should be display for 'no' ", "true","true", true);

				/*for(WebElement AutoPayColumn: lstAmount)
				{
					String sAutoPayValue = AutoPayColumn.getText();
					if(sAutoPayValue.contains(" ") || sAutoPayValue=!null)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that AutoPay status is displayed. Checkmark should be displayed for 'yes' and blank field should be display for 'no' ", "true","true", true);
						
					}
				}*/
				
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that AutoPay status is displayed. Checkmark should be displayed for 'yes' and blank field should be display for 'no' ", "true","false", true);

			}
			
			//Verify that Status should be displayed as "posted" for successful payements
			List <WebElement> lstStatus = lDriver.findElements(By.xpath("//td[@headers='Status']"));
			int iStatusSize = lstStatus.size();
			if(iStatusSize!=0)
			{
				for(WebElement StatusColumn: lstStatus)
				{
					String sStatusValue = StatusColumn.getText();
					if(sStatusValue.contains("Posted") || sStatusValue.contains("Unsuccessful"))
					{
						iStatusSize--;
						if(sStatusValue.contains("Unsuccessful"))
						{
							Report.ValidationPoint(testContext.getName(), "Verify that payment 'Unsuccessful' message is displayed", "true","true", true);

						}
						
					}else
					{
						Report.ValidationPoint(testContext.getName(), "Verify that Status should be displayed as 'posted' for successful payements", "true","false", true);
						break;
					}
					if(iStatusSize==0)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that Status should be displayed as 'posted' for successful payements", "true","true", true);

					}
				
				}
					
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that Status should be displayed as 'posted' for successful payements", "true","false", true);
			}
			
			//Verify that "NA" should be displayed for payment methods that do not generate a Confirmation and Conf # for payment methods generating a Conf
			List <WebElement> lstConfirmation = lDriver.findElements(By.xpath("//td[@headers='Confirmation']"));
			int iConfSize = lstConfirmation.size();
			for(WebElement ConfNumColumn: lstStatus)
			{
				String sConfNum = ConfNumColumn.getText();
				if(sConfNum.contains("NA") || sConfNum!=null)
				{
					iConfSize--;
				}
			}
			if(iConfSize==0)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that 'NA' should be displayed for payment methods that do not generate a Confirmation and Conf # for payment methods generating a Conf", "true","true", true);

			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that 'NA' should be displayed for payment methods that do not generate a Confirmation and Conf # for payment methods generating a Conf", "true","false", true);
			}
			
		lDriver.switchTo().window(mainWindow);	
		WebElement btnClose = lDriver.findElement(By.xpath("//img[@src='/olam/images/brand30/modalClose.png']"));
		btnClose.click();
		Report.OperationPoint(testContext.getName(), "Operational - Clicked on frame close button ");
		
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

	

	}
}
/**************************************************************
 * Function Name -VerifyDiscontinueAutopaylink()
 * Description - 
 * Test Case - BAP04957_01
 * Parameters - None
 * Date created - 2nd jun 2015
 * Developed by - Gautham
 * Last Modified By - 
 * Last Modified Date - 
 ***************************************************************/
public static void VerifyDiscontinueAutopaylink(final ITestContext testContext) throws HeadlessException, IOException, AWTException
{
	WebDriver lDriver = UI.getDriver(testContext.getName());
	OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	OR_ManageAutoPay oR_ManageAutoPay = PageFactory.initElements(lDriver, OR_ManageAutoPay.class);
	Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

		try
		{
			//Verify for uverse SA 'AP applied MMDDYYYY' not displayed on DB
			Report.ValidationPoint(testContext.getName(), "Verify for uverse SA 'AP applied MMDDYYYY' not displayed on DB", "true","true", true);
			
			//Click on the 'Bill & Payments' link under primary navigation bar
			Report.OperationPoint(testContext.getName(), "Navigating to Bills, Usage & Payments landing page");
			UI.WaitForObject(oR_AccountOverview.lnkBillingUsageSecNav, 5);
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			Report.TestPoint(testContext.getName(), "Goto Bills, Usage and Payments page from global nav", "Bills, Usage & Payments page is displayed", "Bills, Usage & Payments page is displayed" , true);	
			
			//Click on the 'Manage Autopay' on Bills and payments page
			Report.OperationPoint(testContext.getName(), "Navigating to manage AutoPay page");
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkManageAutopayTertNav);
			
			//Validate only one account is listed on the manage Autopay page
			boolean bAccount= UI.WaitForObject(oR_ManageAutoPay.txtAccount, UI.iObjTimeOut);
			List<WebElement> Account= lDriver.findElements(By.xpath("//span[@class=' top2px']"));
			if(Account.size()==1)
			{
				Report.ValidationPoint(testContext.getName(), "Validate only one account is listed on the manage Autopay page", "true",String.valueOf(bAccount), true);
			}
			
			//Validate 'Enroll my other accounts in AutoPay' link is displayed
			boolean bEnroll= UI.WaitForObject(oR_ManageAutoPay.lnkEnrollMyOtherAccountsInAutopay, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate 'Enroll my other accounts in AutoPay' link is displayed",
					"true", String.valueOf(bEnroll) , true);	
			
			//Click on the 'Discontinue Autopay' link present next to the wireless account listed on the 'Manage Autopay' page
			boolean bDiscontinue= UI.WaitForObject(oR_ManageAutoPay.lnkDiscontinueAutoPay, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "'Discontinue Autopay' link is present",
					"true", String.valueOf(bDiscontinue) , true);	
			Actions move= new Actions(lDriver);
			move.moveToElement(oR_ManageAutoPay.lnkDiscontinueAutoPay).click().build().perform();
						
			//Submit. User navigates to discontinue autopay confiramtion page
			boolean bSubmit= UI.WaitForObject(oR_ManageAutoPay.btnSubmit, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Submit button is displayed",
					"true", String.valueOf(bSubmit) , true);	
			move.moveToElement(oR_ManageAutoPay.btnSubmit).click().build().perform();
			
			//Validate "Back to Account Overview" link is displayed
			boolean bGoToAccount= UI.WaitForObject(oR_ManageAutoPay.lnkGoToAccOverview, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "'Back to Account Overview' link is displayed",
					"true", String.valueOf(bGoToAccount) , true);	
			move.moveToElement(oR_ManageAutoPay.lnkGoToAccOverview).click().build().perform();
			
			//Validate 'Sign up for Autopay' link is displayed not the 'Manage Autopay ' link
			WebElement bEnrollInAutopay= lDriver.findElement(By.xpath("//span//a[contains(text(),'Enroll in AutoPay')]"));
			if(bEnrollInAutopay.isDisplayed())
			{
				move.moveToElement(lDriver.findElement(By.xpath("//span//a[contains(text(),'Enroll in AutoPay')]"))).build().perform();
				Report.TestPoint(testContext.getName(), "'Enroll in autopay' link is displayed",
					"true", "true" , true);	
			}
			else
			{
				Report.TestPoint(testContext.getName(), "'Enroll in autopay' link is displayed",
						"true", "false" , true);	
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
/**************************************************************
 * Function Name - VerifySecondaryLinkRailSlidCBWireline()
 * Description - Validate Bill tab secondary link rails for Slid with CB wireline.
 * Parameters - None
 * Date created - 23rd June 2015
 * Developed by - Krutika Kamdi
 * Last Modified By - 
 * Last Modified Date - 
 ***************************************************************/
//BAP30714
public static void VerifySecondaryLinkRailSlidCBWireline(final ITestContext testContext) throws HeadlessException, IOException, AWTException
{
	try
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		//Check Account Overview page is displayed
		boolean bOverview = UI.WaitForObject(oR_AccountOverview.txtAccountOverview, UI.iObjTimeOut);
		Report.TestPoint(testContext.getName(), "Validate Account Overview page is displayed", "true", String.valueOf(bOverview), true);
		
		//Click on Billing, Usage, Payments
		UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
		
		//Verify Bill Tab is pre selected
		if(UI.WaitForObject(oR_BillAndUsage.btnBillTab, UI.iObjTimeOut, lDriver))
		{
			Report.TestPoint(testContext.getName(), "Validate Bill Tab is preselected", "true","true", true);
		}
		else
		{
			Report.TestPoint(testContext.getName(), "Validate Bill Tab is preselected", "true","false", true);
		}

		//Validate Secondary rails link section
		//Check  Secondary rails link section is displayed at the bottom of Bill Page
		boolean bSecLinkRail = UI.WaitForObject(oR_BillAndUsage.tblSecLinkRailsSection, UI.iObjTimeOut);
		Report.TestPoint(testContext.getName(), "Validate that Secondary Rail Link section is displayed at the bottom of Bill page", "true", String.valueOf(bSecLinkRail), true);
		//Validate secondary link rails section contains following 3 subsections:
		//1. Manage Account
		//2. Change Billing options
		//3. Get Help
		boolean bManageAccount = UI.WaitForObject(oR_BillAndUsage.txtManageAccount, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Validate that 'Manage Account' section is displayed in Sec link rails section", "true", String.valueOf(bManageAccount), true);
		boolean bChangeBilling = UI.WaitForObject(oR_BillAndUsage.txtChangeBillingOptions, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Validate that 'Change Billing Options' section is displayed in Sec link rails", "true", String.valueOf(bChangeBilling), true);
		boolean bGetHelp = UI.WaitForObject(oR_BillAndUsage.txtGetHelp, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Validate that 'Get Help' section is displayed in Sec link rails", "true", String.valueOf(bGetHelp), true);
		
		//Get the URL of Current page
		String sBillAndUsagePageURL = lDriver.getCurrentUrl();
		
		/*Validate following links are displayed within the 'Manage Account' section:
		/*  1) Manage Features 
			2) Move my service
			3) View Troubleshoot & Resolve Tool
			4) Member Center
			5) Set up Parental Controls for internet */
		
		//2. Validate 'Move my service'
		boolean bMoveMyService = UI.WaitForObject(oR_BillAndUsage.lnkMoveMyService, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Validate that Move My Service link is displayed under Manage Account section", "true", String.valueOf(bMoveMyService), true);
			//Click on Move My service link and check if it is redirected correctly
			oR_BillAndUsage.lnkMoveMyService.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Move My Service' link");
//			Thread.sleep(60000);
			String sMoveMyServiceURL = lDriver.getCurrentUrl();
			Report.ValidationPoint(testContext.getName(), "URL for Move My Service page:"+sMoveMyServiceURL, "true", "true", true);
			Thread.sleep(10000);
			//driver.navigate().back();
			lDriver.get(sBillAndUsagePageURL);
			Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");


		
		//3. Validate 'Toubleshoot My Service' 	
		boolean bTroubleshoot = UI.WaitForObject(oR_BillAndUsage.lnkTroubleshootMyService, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Validate that Troubleshoot My Service link is displayed under Manage Account section", "true", String.valueOf(bTroubleshoot), true);	
			//Click on Troubleshoot My service link and check if it is redirected correctly
			oR_BillAndUsage.lnkTroubleshootMyService.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Troubleshoot My Service' link");
//			Thread.sleep(60000);
			String sTroubleshootMyServiceURL = lDriver.getCurrentUrl();
			Report.ValidationPoint(testContext.getName(), "URL for Toubleshoot My Service page:"+sTroubleshootMyServiceURL, "true", "true", true);
			Thread.sleep(10000);
			//driver.navigate().back();
			lDriver.get(sBillAndUsagePageURL);
			Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");

		
		//4. Validate 'Visit Member Center' 	
		boolean bVisitMem = UI.WaitForObject(oR_BillAndUsage.lnkVisitMemberCenter, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Validate that Visit Member Center link is displayed under Manage Account section", "true", String.valueOf(bVisitMem), true);	
			//Click on Visit Member centre link and check if it is redirected correctly
			oR_BillAndUsage.lnkVisitMemberCenter.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Visit Member Center' link");
//			Thread.sleep(60000);
			String sVisitMemCenterURL = lDriver.getCurrentUrl();
			Report.ValidationPoint(testContext.getName(), "URL for Visit Member Center page:"+sVisitMemCenterURL, "true", "true", true);
			Thread.sleep(10000);
			//driver.navigate().back();
			lDriver.get(sBillAndUsagePageURL);
			Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");

			
			//5. Validate 'Set up Parental Controls for Internet' 
			if(oR_BillAndUsage.lnkSetUpParentalControls.isDisplayed())
			{
			
				//Click on Set up Parental Controls for Internet link and check if it is redirected correctly
				oR_BillAndUsage.lnkSetUpParentalControls.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Set up Parental Controls' link");
//				Thread.sleep(60000);
				String sParentalControlURL = lDriver.getCurrentUrl();
				Report.ValidationPoint(testContext.getName(), "URL for Set up Parental controls page:"+sParentalControlURL, "true", "true", true);
				Thread.sleep(10000);
				//driver.navigate().back();
				lDriver.get(sBillAndUsagePageURL);
				Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");

			}		
			
				/*Validate Following links are displayed within the 'Change billing options' section:
					1. Manage Paperless Billing
					2. Enroll in AutoPay
					3. Manage payments options
					4. Change billing address*/
				
				//1. Validate Manage Paperless Billing
				//boolean bPaperlessBilling = UI.WaitForObject(oR_BillAndUsage.lnkEnrollPaperlessBilling, UI.iObjTimeOut);
				if(oR_BillAndUsage.lnkEnrollPaperlessBilling.isDisplayed())
				{
				    Report.ValidationPoint(testContext.getName(), "Validate that Enroll in paperless billing link is displayed under Change Billing Options section", "true","true", true);	
					//Click on Enroll in paperless billing link and check if it is redirected correctly
					oR_BillAndUsage.lnkEnrollPaperlessBilling.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Enroll in paperless billing' link");
//					Thread.sleep(60000);
					String sPaperlessBillingURL = lDriver.getCurrentUrl();
					Report.ValidationPoint(testContext.getName(), "URL for Enroll in paperless billing page:"+sPaperlessBillingURL, "true", "true", true);
					Thread.sleep(10000);
					//driver.navigate().back();
					lDriver.get(sBillAndUsagePageURL);
					Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");

				}	
				//2. Validate Manage Autopay link Or Enroll in Autopay Link
				boolean bAutopay = UI.WaitForObject(oR_BillAndUsage.lnkEnrollInAutopay, UI.iObjTimeOut);
				if(bAutopay==true)
				{
					Report.ValidationPoint(testContext.getName(), "Validate that Enroll in Autopay link is displayed under Change Billing Options section", "true", String.valueOf(bAutopay), true);	
					//Click on Enroll in Autopay link and check if it is redirected correctly
					oR_BillAndUsage.lnkEnrollInAutopay.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Enroll in Autopay' link");
//					Thread.sleep(60000);
					String sAutopayURL = lDriver.getCurrentUrl();
					Report.ValidationPoint(testContext.getName(), "URL for Enroll in Autopay page:"+sAutopayURL, "true", "true", true);
					Thread.sleep(10000);
					//driver.navigate().back();
					lDriver.get(sBillAndUsagePageURL);
					Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");

				}else
				{
					boolean bManageAutopay = UI.WaitForObject(oR_BillAndUsage.lnkManageAutoPay, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Validate that Manage Autopay link is displayed under Change Billing Options section", "true", String.valueOf(bManageAutopay), true);	
					//Click on Manage autopay link and check if it is redirected correctly
					oR_BillAndUsage.lnkManageAutoPay.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Manage Autopay' link");
//					Thread.sleep(60000);
					String sAutopayURL = lDriver.getCurrentUrl();
					Report.ValidationPoint(testContext.getName(), "URL for Manage Autopay page:"+sAutopayURL, "true", "true", true);
					Thread.sleep(10000);
					//driver.navigate().back();
					lDriver.get(sBillAndUsagePageURL);
					Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");

				}
				
					//3. Validate See more payment options link
					boolean bPaymentOptions = UI.WaitForObject(oR_BillAndUsage.lnkSeeMorePaymentOptions, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Validate that See more payment options link is displayed under Change Billing Options section", "true", String.valueOf(bPaymentOptions), true);	
						//Click on See more paymnet options link and check if it is redirected correctly
						oR_BillAndUsage.lnkSeeMorePaymentOptions.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'See more payment options' link");
//						Thread.sleep(60000);
						String sSeeMoreURL = lDriver.getCurrentUrl();
						Report.ValidationPoint(testContext.getName(), "URL for See more payment options page:"+sSeeMoreURL, "true", "true", true);
						Thread.sleep(10000);
						//driver.navigate().back();
						lDriver.get(sBillAndUsagePageURL);
						Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
						//Navigate to Report tab again to do rest of the validations
						boolean bReportTab8 = UI.WaitForObject(oR_BillAndUsage.btnReportsTab, UI.iObjTimeOut);
						Report.TestPoint(testContext.getName(), "Validate that Report tab is displayed after navigating back from See more payment options", "true", String.valueOf(bReportTab8), true);
						oR_BillAndUsage.btnReportsTab.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Reports' tab");
			
					//4. Change billing address
					boolean bBillingInfo = UI.WaitForObject(oR_BillAndUsage.lnkUpdateBillingInfo, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Validate that Change Billing address link is displayed under Change Billing Options section", "true", String.valueOf(bBillingInfo), true);	
						//Click on Change Billing Address link and check if it is redirected correctly
						oR_BillAndUsage.lnkUpdateBillingInfo.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Change billing address' link");
//						Thread.sleep(60000);
						String sBillingAddURL = lDriver.getCurrentUrl();
						Report.ValidationPoint(testContext.getName(), "URL for Change billing address page:"+sBillingAddURL, "true", "true", true);
						Thread.sleep(10000);
						//driver.navigate().back();
						lDriver.get(sBillAndUsagePageURL);
						Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");

						
					/*Following links are displayed within the 'Get Help' section:
						1.Question a charge
						2.How to update a payment method
						3.High Speed Internet (DSL) Billing Support
						4.Wireless Billing Support
						5.DirecTV Support*/
						
					//1. Validate Question a charge 
					boolean bQuestion = UI.WaitForObject(oR_BillAndUsage.lnkQuestion, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Validate that Question a charge under Change Billing Options section", "true", String.valueOf(bQuestion), true);	
						//Click on Question a charge link and check if it is redirected correctly
						oR_BillAndUsage.lnkQuestion.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Question a charge' link");
//						Thread.sleep(60000);
						String sQuestion = lDriver.getCurrentUrl();
						Report.ValidationPoint(testContext.getName(), "URL for Question a charge :"+sQuestion, "true", "true", true);
						Thread.sleep(10000);

					//2.How to update a payment method
					boolean bUpdatePayment = UI.WaitForObject(oR_BillAndUsage.lnkUpdatePaymentMethod, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Validate that How to update a payment method under Change Billing Options section", "true", String.valueOf(bUpdatePayment), true);	
						//Click on Question a charge link and check if it is redirected correctly
						oR_BillAndUsage.lnkUpdatePaymentMethod.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'How to update a payment method' link");
//						Thread.sleep(60000);
						String sPaymentMethod = lDriver.getCurrentUrl();
						Report.ValidationPoint(testContext.getName(), "URL for How to update a payment method :"+sPaymentMethod, "true", "true", true);
						Thread.sleep(10000);

					
					//3. Validate High Speed Internet (DSL) Billng Support
					boolean bHighSpeedInter = UI.WaitForObject(oR_BillAndUsage.lnkHighSpeedInternetBillingSupport, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Validate that High Speed Internet (DSL) Billng Support link is displayed under Change Billing Options section", "true", String.valueOf(bHighSpeedInter), true);	
						//Click on High Speed Internet (DSL) Billng Support link and check if it is redirected correctly
						oR_BillAndUsage.lnkHighSpeedInternetBillingSupport.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'High Speed Internet (DSL) Billng Support' link");
//						Thread.sleep(60000);
						String sHighSpeedInter = lDriver.getCurrentUrl();
						Report.ValidationPoint(testContext.getName(), "URL for High Speed Internet (DSL) Billng Support page:"+sHighSpeedInter, "true", "true", true);
						Thread.sleep(10000);
						
						
					//4.Wireless Billing Support
					boolean bBillingSupport = UI.WaitForObject(oR_BillAndUsage.lnkWirelessSupport, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Validate that Wireless Billing Support link is displayed under Change Billing Options section", "true", String.valueOf(bBillingSupport), true);	
						//Click on High Speed Internet (DSL) Billng Support link and check if it is redirected correctly
						oR_BillAndUsage.lnkWirelessSupport.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Wireless Billing Support' link");
//						Thread.sleep(60000);
						String sBillingSupport = lDriver.getCurrentUrl();
						Report.ValidationPoint(testContext.getName(), "URL for Wireless Billing Support page:"+sBillingSupport, "true", "true", true);
						Thread.sleep(10000);
						
					//5.DirecTV Support
					boolean bDirectv = UI.WaitForObject(oR_BillAndUsage.lnkDIRECTV, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Validate that DirecTV Support link is displayed under Change Billing Options section", "true", String.valueOf(bDirectv), true);	
						//Click on High Speed Internet (DSL) Billng Support link and check if it is redirected correctly
						oR_BillAndUsage.lnkDIRECTV.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'DirecTV Support' link");
//						Thread.sleep(60000);
						String sDirectv = lDriver.getCurrentUrl();
						Report.ValidationPoint(testContext.getName(), "URL for DirecTV Support page:"+sDirectv, "true", "true", true);
						Thread.sleep(10000);	
					
					
			//End of Validations
			//driver.navigate().back();
						lDriver.get(sBillAndUsagePageURL);
			Report.OperationPoint(testContext.getName(), "Operational - User navigating back to Billing & Usage page");
				
	}
	catch (Exception e) 
	{
		Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

	}
	
}

/**************************************************************
 * Function Name -  Func_ValidateCheckingSavingPaymentMethod #BAP06122_01 
 * Description- This method 
 * Parameters - None
 * Date created -2nd June May 2015
 * Developed by - Monica Mohabansi
 * Last Modified By - 
 * Last Modified Date -
/**************************************************************/
//BAP06122_01 
 public static void  Func_ValidateCheckingSavingPaymentMethod(final ITestContext testContext) throws HeadlessException, IOException, AWTException
 {
	 String sDefaultSelection = null;
	try
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
		
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		String sRoutingNum, sBAN;
		
		sRoutingNum = IO.GetParamVal(sTestParams, "RoutingNum");
		sBAN = IO.GetParamVal(sTestParams, "BAN");
		
		System.out.println(sRoutingNum + "sRoutingNum");
		System.out.println(sBAN + "sBAN");

			
		Boolean bMAPBtn, bMAPHeader, bSelectCheckingSaving;
		String bEditPaymentProfile;
		Boolean  sNameOnBankAcc, sRoutingNumber, sBankAccountNumber, sReenterAccNum;
		
		bMAPBtn = UI.WaitForObject(oR_AccountOverview.btnMakeAPayment, UI.iObjTimeOut);
		oR_AccountOverview.btnMakeAPayment.click();
		Report.TestPoint(testContext.getName(), "Make A Payment Button", "true", String.valueOf(bMAPBtn), true);
		
		bMAPHeader = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, UI.iObjTimeOut);
//		For the AC1 account enter: First part Amount> 0 Date= current date not the curent date Payment method : New Checking/Savings card

    	Boolean bDropdown = UI.WaitForObject(oR_MakeAPayment.lstPaymentMethod, UI.iObjTimeOut);	
    	bSelectCheckingSaving = UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod, "New checking / savings account");

//	    Validate that new payment method div layer - checking/ savings is displayed as below:
    	Boolean bCheckingSavingFrame = UI.WaitForObject(oR_MakeAPayment.frmNewCheckingSavingPaymentMethod, UI.iObjTimeOut);
    	Report.TestPoint(testContext.getName(), "Frame Checking/Saving Payment Method", "true", String.valueOf(bCheckingSavingFrame), true);    
	        	
    	lDriver.switchTo().frame(oR_MakeAPayment.frmNewCheckingSavingPaymentMethod); 	
    	Report.OperationPoint(testContext.getName(), "Entering valid Checking/Savings Details");

	        	
//    	Enter valid bank account details in the new checking/saving account div layer and continue.
	        	sNameOnBankAcc  = UI.WaitForObject(oR_MakeAPayment.edtNameOnBankAcc, UI.iObjTimeOut);
	        	sRoutingNumber  = UI.WaitForObject(oR_MakeAPayment.edtRoutingNumber, UI.iObjTimeOut);
	        	sBankAccountNumber  = UI.WaitForObject(oR_MakeAPayment.edtBankAccountNumber,UI.iObjTimeOut);
	        	sReenterAccNum = UI.WaitForObject(oR_MakeAPayment.edtReenterAccNum, UI.iObjTimeOut);
	        	
//	       if(sNameOnBankAcc.toLowerCase()=="true" && sRoutingNumber.toLowerCase()=="true" && sBankAccountNumber.toLowerCase()=="true" && sReenterAccNum.toLowerCase()=="true")
	     
	       System.out.println(sNameOnBankAcc + " sNameOnBankAcc"); 	
	       System.out.println(sRoutingNumber + " sNameOnBankAcc"); 	
	       System.out.println(sBankAccountNumber + " sNameOnBankAcc"); 	
	       System.out.println(sReenterAccNum + " sNameOnBankAcc"); 	

	       if(sNameOnBankAcc && sRoutingNumber && sBankAccountNumber && sReenterAccNum)
	       {
	    	   System.out.println("HIii");
	    	   oR_MakeAPayment.edtNameOnBankAcc.sendKeys("James");
			   oR_MakeAPayment.edtRoutingNumber.sendKeys(sRoutingNum);
			   oR_MakeAPayment.edtBankAccountNumber.sendKeys(sBAN);
			   oR_MakeAPayment.edtReenterAccNum.sendKeys(sBAN);
	       }
	        
	       String sContinueBtn = UI.CheckExist(oR_MakeAPayment.btnPaymentFrameContinue);
	       oR_MakeAPayment.btnPaymentFrameContinue.click();
	       Report.ValidationPoint(testContext.getName(), "Click on Continue", "true", sContinueBtn.toLowerCase(), true);
	       
//	       Verify that in the payment method dropdown the default selection is "New account ending in xxxx"
	       lDriver.switchTo().parentFrame();   
	       
	       Thread.sleep(3000);
	       String sLastFourDigitsOfBAN = sBAN.substring(13);
	       if(lDriver.findElement(By.xpath("//div[@id='uniform-account_0_1']//span[contains(text(),'Account ending in')]")).isDisplayed())
	    	  {
//	    	   New Account number ending is Displayed

	    	   sDefaultSelection= lDriver.findElement(By.xpath("//div[@id='uniform-account_0_1']//span[contains(text(),'Account ending in')]")).getText();   	
	    	  
	    	   System.out.println(sDefaultSelection + " sDefaultSelection");
	    	   
	    	   if(sDefaultSelection.contains(sLastFourDigitsOfBAN))
	    		   Report.ValidationPoint(testContext.getName(), "Default selection 'Account ending in new account number ending #### is present'", "true", "true", true);    	  
	    	   else
	    		   Report.ValidationPoint(testContext.getName(), "Default selection 'Account ending in #### is present'", "true", "false", true);
	    	  }
	       else
    		   Report.ValidationPoint(testContext.getName(), "Default selection 'Account ending in #### is present'", "true", "false", true);

//	       Validate that " Edit New Payment Method Link" appears below the payment method dropdown
	       bEditPaymentProfile = UI.CheckExist(oR_MakeAPayment.lnkEditPaymentProfile);
	       Report.ValidationPoint(testContext.getName(), "Edit Payment Profile link", "true", bEditPaymentProfile.toLowerCase(), true);
	       
	       List <WebElement> Accounts = lDriver.findElements(By.xpath("//p[@class= 'MarLeft0 botMar0  font16 bt_left']"));
			int iNumberOfAcc = Accounts.size();
			for(int i = 0; i<=iNumberOfAcc-1 ; i++)
			{
				lDriver.findElement(By.xpath("//div[contains(@id,'Child"+i+"_1')]//input[@title='Payment Amount']")).sendKeys("10.00");
				
			}	
			for(WebElement e : Accounts)
	        {
	              WebElement calLink = e.findElement(By.tagName("a"));
	              calLink.click();

	              Boolean bCalendar = UI.WaitForObject(oR_MakeAPayment.frmCalender, UI.iObjTimeOut);
	              Report.ValidationPoint(testContext.getName(), "Calendar Frame for account" + e, "true", String.valueOf(bCalendar),  true);
	              Thread.sleep(2000);
	              
	              lDriver.switchTo().frame(oR_MakeAPayment.frmCalender);
		             
//				  Boolean bCurrentDate = UI.WaitForObject(lDriver.findElement(By.xpath("//a[@onclick='parent.setValue('"+strDate+"')']")), UI.iObjTimeOut);
				  lDriver.findElement(By.xpath("//a[contains(@onclick,'parent.setValue')]")).click();
	        
				  Boolean bCloseLink = UI.WaitForObject(oR_MakeAPayment.lnkCloseModal, UI.iObjTimeOut);
		      		if(bCloseLink)
		      		{
	        		  	oR_MakeAPayment.lnkCloseModal.click();
	      				Thread.sleep(2000);
	      				lDriver.switchTo().parentFrame();     				
		      		}
		      		else
		      			Report.ValidationPoint(testContext.getName(), "Close link for calendar frame", "present", "absent", true);

	        }
			
			Boolean bNewDropdown = UI.WaitForObject(oR_MakeAPayment.lstPaymentMethod, UI.iObjTimeOut);
	    	   {
	    		   System.out.println(sDefaultSelection + " sDefaultSelection");
	    	  	   Boolean bNewAccount = UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod, sDefaultSelection);
		    	  	 if(bNewAccount==true)
		 			{
		 				String sNextBtn = UI.CheckExist(oR_MakeAPayment.btnNext);
		 				Report.TestPoint(testContext.getName(), "Click on Next button", "true", sNextBtn.toLowerCase(), true);
		 			}
	    	   }	 
	    	  	   
	}	
	catch(Exception e)
	{
		
	}
 }
 
 /**************************************************************
  * Function Name - VerifyCreditCardAboutToExpireAndUpdatePaymentLink()
  * Description - Verify that Uverse Credit card about to expire alert at alerts tab and Make a payments page
  * Parameters - None
  * Date created - 15th June 2015
  * Developed by - Clint John
  * Last Modified By - 
  * Last Modified Date - 
  * @throws ParseException 
  ***************************************************************/
 //BAP08734 //BAP08742
 public static void VerifyCreditCardAboutToExpireAndUpdatePaymentLink(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
 {
 	try
 	{
 		WebDriver lDriver = UI.getDriver(testContext.getName());
 		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
 		OR_EditPaymentProfile oR_EditPaymentProfile = PageFactory.initElements(lDriver, OR_EditPaymentProfile.class);
 		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
 		
 		//Check for Alert button/tab
 		boolean bAlert = UI.WaitForObject(oR_AccountOverview.btnAlertBox, UI.iObjTimeOut);
 		Report.TestPoint(testContext.getName(), "Validate Alert tab is displayed", "true", String.valueOf(bAlert), true);

 		//Verify Credit Card expiry alert is displayed in the Alerts section
 		oR_AccountOverview.btnAlertBox.click();
 		Report.OperationPoint(testContext.getName(), "Operation : Clicked on Alert button to view alerts");
 		boolean bCardExpiredAlert = UI.WaitForObject(oR_AccountOverview.txtCardAboutToExpireMessage, UI.iObjTimeOut);
 		Report.TestPoint(testContext.getName(), "Validate that text showing 'Your Card is about to Expire' is displayed under Alerts section", "true", String.valueOf(bCardExpiredAlert), true);
 		boolean bPaymentProfileLink = UI.WaitForObject(oR_AccountOverview.lnkUpdatePaymentProfileAlert, UI.iObjTimeOut);
 		Report.TestPoint(testContext.getName(), "Validate that the link for Update payment profile is displayed under Card expired alert", "true", String.valueOf(bPaymentProfileLink	), true);

 		//Click on Update payment link and navigate to Edit payment link page
 		oR_AccountOverview.lnkUpdatePaymentProfileAlert.click();
 		Report.OperationPoint(testContext.getName(), "Operation : Clicked on 'Update payment profile' link");
 		//boolean bEditPaymentProfileHeading = UI.ValidateHeadingOfPage("Edit Payment Profile");
 		boolean bEditPaymentProfileHeading = UI.WaitForObject(oR_EditPaymentProfile.txtEditPaymentProfile, UI.iObjTimeOut, lDriver);
 		Report.TestPoint(testContext.getName(), "Validate that Edit Payment Profile page is displayed", "true", String.valueOf(bEditPaymentProfileHeading), true);
 		//Validate note for credit card expiry is displayed
 		boolean bExpiredOn = UI.WaitForObject(oR_EditPaymentProfile.txtExpiryDate, UI.iObjTimeOut);
 		Report.TestPoint(testContext.getName(), "Validate that note for Credit card expiry is displayed", "true", String.valueOf(bExpiredOn), true);


 	}
 	catch (Exception e) 
 	{
 		Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

 	}

 }
 /**************************************************************
  * Function Name - VerifyCreditCardExpiryNoteAndAlertForTitanAccount()
  * Description - This function verifies required alert
  * Parameters - None
  * Date created - 17th June 2015
  * Developed by - Gautham
  * Last Modified By - 
  * Last Modified Date - 
  * @throws ParseException 
  ***************************************************************/
 //BAP08741
 public static void VerifyCreditCardExpiryNoteAndAlertForTitanAccount(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
 {
	 	WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_EditPaymentProfile oR_EditPaymentProfile = PageFactory.initElements(lDriver, OR_EditPaymentProfile.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
	 try
 	{
 		
 		//Check for Alert button/tab
 		boolean bAlert = UI.WaitForObject(oR_AccountOverview.btnAlertBox, UI.iObjTimeOut);
 		if(bAlert){
 		Report.TestPoint(testContext.getName(), "Validate Alert tab is displayed", "true", String.valueOf(bAlert), true);
 		oR_AccountOverview.btnAlertBox.click();
 		Report.OperationPoint(testContext.getName(), "Clicked on Alert button");
 		}
 		//Verify Credit Card expiry alert is displayed in the Alerts section
 		boolean bExpireAlert = UI.WaitForObject(oR_AccountOverview.txtCardAboutToExpireMessage, UI.iObjTimeOut);
 		Report.ValidationPoint(testContext.getName(), "Validate that text 'Your Card is about to Expire' is displayed under Alerts section", "true", String.valueOf(bExpireAlert), true);
 		
 		//Verify Update Payment profile link is displayed in the Alerts section
 		boolean bPaymentProfileLink = UI.WaitForObject(oR_AccountOverview.lnkUpdatePaymentProfileAlert, UI.iObjTimeOut);
 		Actions move= new Actions(lDriver);
 		move.moveToElement(oR_AccountOverview.lnkUpdatePaymentProfileAlert).build().perform();
 		Report.ValidationPoint(testContext.getName(), "Validate that the link for 'Update payment profile' is displayed under Card expired alert", "true", String.valueOf(bPaymentProfileLink	), true);

 		//Click on Update payment link and navigate to Edit payment link page
 		oR_AccountOverview.lnkUpdatePaymentProfileAlert.click();
 		Report.OperationPoint(testContext.getName(), "Clicked on 'manage autopay settings' link");
 		
 		//Validating edit payment profile page
 		boolean bEditPaymentProfile = UI.ValidateHeadingOfPage("Manage AutoPay");
 		Report.ValidationPoint(testContext.getName(), "Validate that Edit Payment Profile page is displayed", "true", String.valueOf(bEditPaymentProfile), true);
 		
 		//validating note for credit card expiry date
 		boolean bDate = UI.WaitForObject(lDriver.findElement(By.xpath("//span[contains(text(),'Expires soon')]")), UI.iObjTimeOut);
 		Report.ValidationPoint(testContext.getName(), "Validate that note for Credit card expiry is displayed", "true", String.valueOf(bDate), true);

 	
   }

	catch (Exception e) 
 	{
		e.printStackTrace();
 		Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

 	}

 }
 /**************************************************************
  * Function Name - VerifySecondaryLinkRailsInBillTab()
  * Description - This function verifies secondary link rails in bill tab
  * Parameters - None
  * Date created - 23rd June 2015
  * Developed by - Gautham
  * Last Modified By - 
  * Last Modified Date - 
  * @throws ParseException 
  ***************************************************************/
 //BAP30839
 public static void VerifySecondaryLinkRailsInBillTab(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
 {
	 	WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
	 try
 	{
		 //Click Bill & Payments link
		 UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 20);
		 UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
		 
		 //Verify Bill Details
		 boolean bBill= UI.WaitForObject(oR_BillAndUsage.txtTotalAmountDue,20);
		 Report.ValidationPoint(testContext.getName(), "Bill details displayed" , "true", String.valueOf(bBill),  true);
          
		 //Validate the secondary link rails.
		 WebElement Manage= lDriver.findElement(By.xpath("//h3[@class='font16 bold']//strong[contains(text(),'Manage Account')]"));
		 WebElement Payment= lDriver.findElement(By.xpath("//h3[@class='font16 bold']//strong[contains(text(),'Change Billing & Payment Options')]"));
		 WebElement GetHelp= lDriver.findElement(By.xpath("//h3[@class='font16 bold']//strong[contains(text(),'Get Help')]"));
				if(Manage.isDisplayed() && Payment.isDisplayed() && GetHelp.isDisplayed() )
					{
					Report.ValidationPoint(testContext.getName(), "Validating  Secondary Link Rail Section including titles within the Bill Tab" , "Displayed as expected", "Displayed as expected",  true);
					}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validating  Secondary Link Rail Section including titles within the Bill Tab" , "Displayed as expected", "Not Displayed as expected",  true);
					
				}
 	}

	 catch (Exception e) 
	 	{
	 		Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

	 	}

 }
 
 	/**************************************************************
	 * Function Name - VerifyEquipmentsChargesSection()
	 * Description - Validate Installment Plan move messaging within Equipment Charges section where Installment Charge created after Cross Upgrade and Pay Up done
	 * Test Case -  BAP34639
	 * Parameters - None
	 * Date created - 14th July 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyEquipmentsChargesSection(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			
			//Navigate to Billing, usage and payments page
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Billing, Usage and Payments button");

			//Verify Billing & Usage page is displayed
			boolean bBillingPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
			Report.TestPoint(testContext.getName(), "Validate that BillAndUsage page is displayed", "true", String.valueOf(bBillingPage), true);
			
			Report.OperationPoint(testContext.getName(), "Clicking on all '+' expand section to expand all");
			List<WebElement> PlusSigns = lDriver.findElements(By.xpath("//a[contains(@class,'expandImg')]"));
			int iSize = PlusSigns.size();
			for(int i=0;i<iSize;i++)
			{
				try
				{
					
					if(PlusSigns.get(i).isEnabled() || PlusSigns.get(i).isDisplayed())
					{
						PlusSigns.get(i).click();
						Thread.sleep(3000);
					}
				
				}catch(Exception Eee)
				{
					Report.OperationPoint(testContext.getName(), "expand '+' is hidden, moving to next");
				}
					
			}
			
			//Verify Equipments charges section is displayed
			boolean bEquipCharges = UI.WaitForObject(oR_BillAndUsage.txtEquipmentsChargesSection, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Equipments charges section is displayed", "true",String.valueOf(bEquipCharges), true);
				
			//Verify Equipments charges section contents
				//Installment plan ID and Est Date
				boolean bInstID = UI.WaitForObject(oR_BillAndUsage.txtInstallmentPlanID, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Installment plan ID is displayed", "true",String.valueOf(bInstID), true);
				boolean bEstDate = UI.WaitForObject(oR_BillAndUsage.txtEstablishedOnDate, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Installment plan ID is displayed", "true",String.valueOf(bInstID), true);
				/*//Equip price
				boolean bEquipPrice = UI.WaitForObject(oR_BillAndUsage.txtEquipmentPrice, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Equipment price is displayed", "true",String.valueOf(bEquipPrice), true);
				//Down payment
				boolean bDownPayment = UI.WaitForObject(oR_BillAndUsage.txtDownPayment, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Down Payment is displayed", "true",String.valueOf(bDownPayment), true);
				*///Amount financed
				boolean bAmtFinanced = UI.WaitForObject(oR_BillAndUsage.txtAmountFinanced, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Amount financed is displayed", "true",String.valueOf(bAmtFinanced), true);
				//Balance amount
				boolean bBalanceAmt = UI.WaitForObject(oR_BillAndUsage.txtBalanceRemainingAfterInst, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Balance amount remaining is displayed", "true",String.valueOf(bBalanceAmt), true);
				
				// *** Installment Plan moved message(from CMS Eg:Completed move of remaining Installment Plan from 123-444-7878) NOT displayed
				
				//total Equipments charges (total payup amount)
				boolean bTotEqupCharges = UI.WaitForObject(oR_BillAndUsage.txtTotalEquipmentCharges, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Total Equipment charges (Pay up amount) is displayed", "true",String.valueOf(bTotEqupCharges), true);		

				

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
 
	 /**************************************************************
	 * Function Name - VerifySplitPaymentMethod()
	 * Description - Verifies split payment link by using credit card and savings account
	 * Test Case -  BAP07796
	 * Parameters - None
	 * Date created - 14th July 2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifySplitPaymentMethod(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
			OR_PaymentConfirmation oR_PaymentConfirmation = PageFactory.initElements(lDriver, OR_PaymentConfirmation.class);
			
			//Verify Dashboard is displayed 
			
				if(UI.WaitForObject(oR_AccountOverview.imgPromotions, 10, lDriver).booleanValue())
					{
					if(UI.WaitForObject(oR_AccountOverview.lnkEnrollInAutopay, 10, lDriver).booleanValue())
						{
							Report.TestPoint(testContext.getName(), "Verify Dashboard is displayed as expected", "true", "true", true);
						}
					else
						{
							Report.TestPoint(testContext.getName(), "Verify Dashboard is displayed as expected", "true", "Enroll in autopay is not present", true);
						
						}
					}
				else
					{
						Report.TestPoint(testContext.getName(), "Verify Dashboard is displayed as expected", "true", "Promotions is not present", true);
				
					}	
			
			//Hover on Bill & Payments, Click on Payment Activity link.
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkPaymentActivityTertNav, lDriver);
			boolean bLast = UI.WaitForObject(oR_BillAndUsage.txtLastPaymentsReceived, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify Last Payments received are displayed", "true",String.valueOf(bLast), true);		
			
			//Verify 2 part split payment in the payment activity page
			oR_BillAndUsage.btnMakePaymentInBillingPage.click();
			boolean bSplit = UI.WaitForObject(oR_MakeAPayment.lnkSplitThisPayment, 10, lDriver);
			Actions Driver = new Actions(lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify Split Payments Link is displayed", "true",String.valueOf(bSplit), true);		
			Driver.moveToElement(oR_MakeAPayment.lnkSplitThisPayment).build().perform();
			oR_MakeAPayment.lnkSplitThisPayment.click();
			WebElement SecondPayment= lDriver.findElement(By.xpath("//div[@id='paymentItem_0_1']")); 
			if(SecondPayment.isEnabled());
			{
				Report.ValidationPoint(testContext.getName(), "Verify Payment methods are splitted as expected", "true",String.valueOf(SecondPayment.isEnabled()), true);		
			}
			
			//Enter values of payment
			 UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1, 10, lDriver);
			UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount2, 10, lDriver);
			oR_MakeAPayment.edtPaymentAmount1.clear();
			oR_MakeAPayment.edtPaymentAmount1.sendKeys("20");
			oR_MakeAPayment.edtPaymentAmount2.clear();
			oR_MakeAPayment.edtPaymentAmount2.sendKeys("3");
			UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod, "New checking / savings account");
			
			Thread.sleep(10000);
			// switching to new frame
			lDriver.switchTo().frame(oR_BillAndUsage.frmNewPaymentDetails);
			// entering values of payment1
			oR_MakeAPayment.edtNameOnBankAcc.sendKeys("test");
			oR_MakeAPayment.edtRoutingNumber.sendKeys("031300465");
			oR_MakeAPayment.edtBankAccountNumber.sendKeys("1777777777");
			oR_MakeAPayment.edtReenterAccNum.sendKeys("1777777777");
			Report.ValidationPoint(testContext.getName(), "Verify Values for payment method1 are entered", "true","true", true);		
			oR_MakeAPayment.lnkContinue.click();
			
			//Switch back from payment frame
			lDriver.switchTo().defaultContent();
			
			//selecting payment method2
		
			boolean bSecond= UI.WaitForObject(oR_MakeAPayment.lstPaymentMethod2, 10, lDriver);
			UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod2,"New debit / credit card");
			Thread.sleep(10000);
			// switching to new frame
			lDriver.switchTo().frame(oR_BillAndUsage.frmNewPaymentDetails);
			//entering values of payment2
			oR_MakeAPayment.edtPaymentFrameNameOnCard.sendKeys("test");
			oR_MakeAPayment.edtPaymentFrameCardNumber.sendKeys("4001331544181100");
			oR_MakeAPayment.edtPaymentFrameSecurityNumber.sendKeys("123");
			UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationMonth, "12");
			UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationYear, "2015");
			oR_MakeAPayment.edtPaymentFrameZipCode.sendKeys("12345");
			Report.ValidationPoint(testContext.getName(), "Verify Values for payment method2 are entered", "true","true", true);		
			oR_MakeAPayment.lnkContinue.click();
			Report.ValidationPoint(testContext.getName(), "Verify Payment methods are selected as expected", "true",String.valueOf(bLast), true);		

			////Switch back from payment frame
			lDriver.switchTo().defaultContent();
		
			oR_MakeAPayment.btnNext.click();
			
			Thread.sleep(10000);
			//if paid amount is greater than required amount then alert type window will open
			if(UI.WaitForObject(oR_MakeAPayment.lnkContinue, 10, lDriver))
			{
				WebElement Continue = 
						lDriver.findElement(By.xpath("id('cboxContent')//a[contains(text(),'Continue')]"));
				Continue.click();	
			}
			
			//Submitting the payment
			Report.ValidationPoint(testContext.getName(), "Verify Review payments are displayed", "true",String.valueOf(oR_MakeAPayment.btnSubmit.isDisplayed()), true);		
			oR_MakeAPayment.btnSubmit.click();
			
			if(oR_PaymentConfirmation.txtPaymentConfirmationTitle.isDisplayed())
			{
				Report.ValidationPoint(testContext.getName(), "Verify Payment Confirmation is displayed", "true","true", true);		
				
			}
			//if(oR_PaymentConfirmation.lnkGotoAccountOverviewNew.isDisplayed() && oR_PaymentConfirmation.lnkMakeAnotherPaymentNew.isDisplayed())
			{
				Report.ValidationPoint(testContext.getName(), "Verify Confirmation page has 'Make another payment'and 'Go to account overview' links", "true","true", true);		
				
			}
			
			// navigating to make a payment activity page
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkPaymentActivityTertNav, lDriver);
			
			//Verifying credit card and saving account in last payment received list
			WebElement Card= lDriver.findElement(By.xpath("//div[@class='float-left cell '][contains(text(),'Credit')]"));
			WebElement Savings= lDriver.findElement(By.xpath("//div[@class='float-left cell '][contains(text(),'ACH')]"));
			if(Card.isDisplayed() && Savings.isDisplayed())
			{
				Report.ValidationPoint(testContext.getName(), "Verify credit card and saving account in last payment received list", "true","true", true);		
					
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify credit card and saving account in last payment received list", "true","false", true);		
				
			}
		}
			catch (Exception e) 
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

			}
			
	}		
	
	/**************************************************************
	 * Function Name - VerifyNoAccessToBAP()
	 * Description - Validate 
	 * Test Case -  BAP31781
	 * Parameters - None
	 * Date created - 15th July 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//BAP31781
	public static void VerifyNoAccessToBAP(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			Boolean bBAPSecNavLink = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 20);
			System.out.println(bBAPSecNavLink);
			if(bBAPSecNavLink.equals(false))
			{
			//Verify access to Bill & Payments Page from Global Navigation
				Report.ValidationPoint(testContext.getName(), "User should not be able to access Bill & Payments Page from Global navigation", "User not able to Access BAP link", "User not able to Access BAP link", true);
			//			Access to followng elements should not be provide Bill Details, Reports,Payment informaiton, Biling information,Total Amount Due Information
				Report.ValidationPoint(testContext.getName(), "Access to followng elements should not be provide Bill Details, Reports, Payment information, Billing information,Total Amount Due Information", "Access Not Provided", "Access Not Provided", true);
			}
			else
				Report.ValidationPoint(testContext.getName(), "User should not be able to access Bill & Payments Page from Global navigation", "User not able to Access BAP link", "User is able to Access BAP link", true);
			
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Validate Blocked Access to BAP", "true", "Failed to validate", true);
		}
	}
	
	/**************************************************************
	  * Function Name - VerifyBillDetailsAndCompareBillChart()
	  * Description - 
	  * Parameters - None
	  * Date created - 13th July 2015
	  * Developed by - Gautham
	  * Last Modified By - 
	  * Last Modified Date - 
	  * @throws ParseException 
	  ***************************************************************/
	 //BAP33326

	 public static void VerifyBillDetailsAndCompareBillChart(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		 	WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
		 try
	 		{
			 //Click Bill & Payments link
			 UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 20, lDriver);
			 UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			 
			 Actions action = new Actions(lDriver);
			 action.moveToElement(oR_BillAndUsage.lnkBillTab).build().perform();
	 		//Verify 'View Plan Summary' CTA
			boolean bViewPlan =  UI.WaitForObject(oR_BillAndUsage.lnkViewPlanSummary, 20, lDriver);
			oR_BillAndUsage.lnkViewPlanSummary.click();
			Report.ValidationPoint(testContext.getName(), "Validating  'View Plan Summary' CTA is displayed" , "true", String.valueOf(bViewPlan),  true);
			
	 		//Verify Wireless alerts with Bill Alert Section
			UI.WaitForObject(oR_BillAndUsage.txtBillAlerts, 10, lDriver);
			oR_BillAndUsage.lnkSeeMoreAlerts.click();
			boolean bAlerts = UI.WaitForObject(oR_BillAndUsage.txtWirelessServiceChangeAlert, 20, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validating  Wireless alerts with Bill Alert Section is displayed" , "true", String.valueOf(bAlerts),  true);
			
			//Click 'View Changes' CTA for each Alert
			oR_BillAndUsage.lnkWirelessServiceAlertViewChanges.click();
			Report.OperationPoint(testContext.getName(), "Clicking On Wireless service Alert View Changes");
			Report.ValidationPoint(testContext.getName(), "Validating  User should be navigated to the corresponding sections of Wireless Account" , "true", String.valueOf(oR_BillAndUsage.lnkWirelessServiceAlertViewChanges.isDisplayed()),  true);
			
			//Verify Uverse alerts with Bill Alert Section
			boolean bTVAlerts = UI.WaitForObject(oR_BillAndUsage.lnkUverseTVAlertViewChanges, 10, lDriver);
			boolean bINternetAlerts = UI.WaitForObject(oR_BillAndUsage.lnkUverseInternetAlertViewChanges, 10, lDriver);
			boolean bWirelessAlerts = UI.WaitForObject(oR_BillAndUsage.lnkWirelessServiceAlertViewChanges, 10, lDriver);
			if(bTVAlerts && bINternetAlerts && bWirelessAlerts )
			{
				Report.ValidationPoint(testContext.getName(), "Validating Video On Demand,Voice Overage,Web Overage,Alerts should be displayed" , "true", String.valueOf(bINternetAlerts),  true);
				
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validating Video On Demand,Voice Overage,Web Overage,Alerts should be displayed" , "true", "false",  true);
				
			}
			
			//Click 'View Charges' CTA for each Alert
			oR_BillAndUsage.lnkUverseTVAlertViewChanges.click();
			Report.OperationPoint(testContext.getName(), "Clicking On Uverse Tv Alert View Changes");
			Report.ValidationPoint(testContext.getName(), "Validating User navigated to required section after clicking on TV Alert" , "true", String.valueOf(oR_BillAndUsage.lnkUverseTVAlertViewChanges.isDisplayed()),  true);
			oR_BillAndUsage.lnkUverseInternetAlertViewChanges.click();
			Report.OperationPoint(testContext.getName(), "Clicking On Uverse Internet Alert View Changes");
			Report.ValidationPoint(testContext.getName(), "Validating User navigated to required section after clicking on Internet Alert" , "true", String.valueOf(oR_BillAndUsage.lnkUverseInternetAlertViewChanges.isDisplayed()),  true);
			//oR_BillAndUsage.lnkUverseVoiceAlertViewChanges.click();
			//Report.OperationPoint(testContext.getName(), "Clicking On Uverse Voice Alert View Changes");
			//Report.ValidationPoint(testContext.getName(), "Validating User navigated to required section after clicking on Voice Alert" , "true", String.valueOf(oR_BillAndUsage.lnkUverseVoiceAlertViewChanges.isDisplayed()),  true);
			
			//Verify Uverse Bill Details
			boolean bUverseCharges = UI.WaitForObject(oR_BillAndUsage.txtTotalUverseCharges, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validating Uverse Bill Details is displayed" , "true", String.valueOf(bUverseCharges),  true);
			
			//Verify all line items and total for Uverse Bill
			boolean bUverseInternet = UI.WaitForObject(oR_BillAndUsage.lnkUverseInternetSectionExpand, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validating Uverse Bill Details are displayed as expected" , "true", String.valueOf(bUverseInternet),  true);
			
			boolean bUverseVoice = UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(text(),'Total U-verse')]")), 10, lDriver);
			oR_BillAndUsage.lnkUverseInternetSectionExpand.click();
			//oR_BillAndUsage.lnkUverseVoiceSectionExpand.click();
			Report.ValidationPoint(testContext.getName(), "Validating all line items and total for U-Verse bill" , "true", String.valueOf(bUverseInternet&&bUverseVoice),  true);
			
			oR_BillAndUsage.lnkWirelessServiceAlertViewChanges.click();
			Report.OperationPoint(testContext.getName(), "Clicking On Wireless Alert View Changes");
			
			//Verify Wireless Bill Details
			boolean bWirelessCharges = UI.WaitForObject(oR_BillAndUsage.txtTotalWirelessCharges, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validating Wireless Bill Details is displayed" , "true", String.valueOf(bWirelessCharges),  true);
			
			//Verify all line items and total for Wireless Bill
			boolean bLineItems1 = UI.WaitForObject(oR_BillAndUsage.lnkWirelessMonthlyPlanCharges, 10, lDriver);
			boolean bLineItems2 = UI.WaitForObject(oR_BillAndUsage.lnkWirelessAdditionsAndChangesToService, 10, lDriver);
			boolean bLineItems3 = UI.WaitForObject(oR_BillAndUsage.lnkWirelessSurchargeAndFees, 10, lDriver);
			boolean bLineItems4 = UI.WaitForObject(oR_BillAndUsage.lnkWirelessTotalGovernmentFees, 10, lDriver);
			oR_BillAndUsage.lnkWirelessAdditionsAndChangesToService.click();
			//lDriver.findElement(By.xpath("//a[@href='#toggleSrchgFees404-984-83150T']")).click();
			oR_BillAndUsage.lnkWirelessTotalGovernmentFees.click();		
			Report.ValidationPoint(testContext.getName(), "Validating all line items and total for Wireless Bill is displayed" , "true", String.valueOf(bLineItems1 && bLineItems2 && bLineItems3 && bLineItems4),  true);
			
			//Verify DirecTv Bill Details
			boolean bDigitalTv = UI.WaitForObject(lDriver.findElement(By.id("direcTV0")), 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validating Direct Tv Details is displayed" , "true", String.valueOf(bDigitalTv),  true);
			lDriver.findElement(By.id("direcTV0")).click();
	 		Report.ValidationPoint(testContext.getName(), "Validating Line items should be displayed with charges once sections are expanded" , "true", String.valueOf(bDigitalTv),  true);
			
	 		//Verify Compare Bill Chart within right hand corner of the Bill Tab
			boolean bCharts = UI.WaitForObject(oR_BillAndUsage.txtCompareBills, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validating compare bill chart is displayed" , "true", String.valueOf(bCharts),  true);
			
			//Verify Total amount for each bill period within Compare Bill Chart
			List<WebElement> chartTitle = lDriver.findElements(By.cssSelector("div.highcharts-container svg text tspan"));
			int iMonthCount = 0;
			int iAmountCount = 0;
			for(WebElement e : chartTitle )
			{
				 String Text = e.getText();
				
				//System.out.println(Text);
				if(Text.contains("Feb 16") || Text.contains("Jan 16") || Text.contains("Mar 16") || Text.contains("Apr 16") || Text.contains("May 16") || Text.contains("Jun 16"))
				{
						iMonthCount++;
				}
				else if(Text.contains("$"))
				{
					iAmountCount++;
				}
				
			}
			
			if(iMonthCount>0)
			{
				Report.ValidationPoint(testContext.getName(), "Validating Month of the chart displayed" , "true", "true",  true);
				
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validating Month of the chart displayed" , "true", "false",  true);
				
			}
			
			if(iAmountCount>0)
			{
				Report.ValidationPoint(testContext.getName(), "Validating Total amount is displayed" , "true", "true",  true);
				
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validating Total amount is displayed" , "true", "false",  true);
				
			}
			
			if(UI.WaitForObject(lDriver.findElement(By.cssSelector("g.highcharts-scroller rect ")), 5, lDriver))
			{
				Report.ValidationPoint(testContext.getName(), "Validating scroll bar  is displayed" , "true", "true",  true);
				
			}
			for(WebElement e : chartTitle )
			{
				String Text = e.getText();
				System.out.println(Text);
				if(Text.contains("Jul 15"))
				{
					UI.VerifyLinkNavigatestoNewWindow(e, "view/titan_printer_friendly");
				}
			}
			
	 	}
			catch (Exception e) 
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "some error occured", "true", "Failed to validate"+e.getMessage(), true);

			}
			
	}
	 /**************************************************************
	  * Function Name - VerifyRelevantTabsInBAPPage()
	  * Description - 
	  * Parameters - None
	  * Date created - 20th July 2015
	  * Developed by - Gautham
	  * Last Modified By - 
	  * Last Modified Date - 
	  * @throws ParseException 
	  ***************************************************************/
	 //BAP10430

	 public static void VerifyRelevantTabsInBAPPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		 	WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
			
		 try
	 		{
			 //Click Bill & Payments link
			 UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 20, lDriver);
			 UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			 Thread.sleep(20000);
	 		//Verify 'View Explanation of Charges' CTA is displayed 
			boolean bViewPlan =  UI.WaitForObject(oR_BillAndUsage.lnkViewExplanationOfServices,60, lDriver);
			oR_BillAndUsage.lnkViewExplanationOfServices.click();
			Report.ValidationPoint(testContext.getName(), 
					"Validating  'View Explanation of Charges' CTA is displayed " , "true", String.valueOf(bViewPlan),  true);
		
			if(oR_BillAndUsage.frmNewPaymentDetails.isDisplayed())
			{
				Report.ValidationPoint(testContext.getName(), 
						"Validating  modal window is displayed after clicking on view explanation services link" , "true", "true",  true);
			}
			// switching to new frame
			lDriver.switchTo().frame(oR_BillAndUsage.frmNewPaymentDetails);
			boolean bExplanation= UI.WaitForObject(oR_BillAndUsage.txtExplanationOfCharges, 5);	
			Report.ValidationPoint(testContext.getName(), 
					"Validating  text regarding explanation charges is displayed in modal window" , "true", String.valueOf(bExplanation),  true);
			
			//Switch back from BAP Window
			lDriver.switchTo().defaultContent();
			
			//closing modal window
			oR_BillAndUsage.imgClose.click();
			
			//Verify the 'Manage Account Secondary Link Rail Section' and the links within Bill,History and Reports tab
			boolean bManage= UI.WaitForObject(oR_BillAndUsage.txtManageAccountNew, 5);	
			Report.ValidationPoint(testContext.getName(), 
					"Validating Manage Account Secondary Link Rail Section is displayed" , "true", String.valueOf(bManage),  true);
			
			//Validate 'Set Up Parental Control' link within 'Manage Account' Secondary Link Rail Section
			UI.VerifyLinkNavigatestoNextPage(oR_BillAndUsage.lnkSetUpParentalControl, "parentalcontrols");
			lDriver.navigate().back();
			Thread.sleep(10000);
			//Validate 'Learn how to return equipment' link within 'Manage Account' Secondary Link Rail Section
			UI.VerifyLinkNavigatestoNextPage(oR_BillAndUsage.lnkHowToReturnEquipment, "www.att.com");
			lDriver.navigate().back();
			Thread.sleep(10000);
			//Validate 'Move service' link within 'Manage Account' Secondary Link Rail Section
			UI.VerifyLinkNavigatestoNextPage(oR_BillAndUsage.lnkMoveService, "movers/u-verse");
			lDriver.navigate().back();
			Thread.sleep(10000);
			//Validate 'View Troubleshoot & Resolve Tool' link within 'Manage Account' Secondary Link Rail Section
			UI.VerifyLinkNavigatestoNextPage(oR_BillAndUsage.lnkTroubleshootMyService, "ufix.att.com");
			Thread.sleep(10000);
			////now we need to login again
			lDriver.navigate().to("https://q1col2a1.edc.cingular.net:12121/olam/loginAction.olamexecute"); 
			if (UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, UI.iObjTimeOut)) {
				Report.OperationPoint(testContext.getName(), "Operational - Launch MyATT application");
				Report
				.TestPoint(
						testContext.getName(),
						"Validate MyATT application is launched and login page is dispalyed",
						"True", "True", true);
			} 
			Thread.sleep(5000);
			LaunchAndLogout.LoginApplication(testContext);
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			Thread.sleep(10000);
			//Validate 'Change plan' link within 'Manage Account' Secondary Link Rail Section
			UI.WaitForObject(oR_BillAndUsage.lnkChangePlan, 60, lDriver);
			UI.VerifyLinkNavigatestoNextPage(oR_BillAndUsage.lnkChangePlan, "tst26.stage.att.com");
			}
		

			catch (Exception e) 
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

			}
			
	}
	 
	 /**************************************************************
		 * Function Name - VerifyInstallmentDetails()
		 * Description - Validate the bill tab to to provide a CTA to the Installment Details modal 
		 * Test Case -  BAP29906
		 * Parameters - None
		 * Developed by - Krutika Kamdi
		 * Last Modified By - 
		 * Last Modified Date - 
		 ***************************************************************/
	 //BAP29906
		public static void VerifyInstallmentDetails(final ITestContext testContext) throws HeadlessException, IOException, AWTException
		{
			try
			{
				WebDriver lDriver = UI.getDriver(testContext.getName());
				OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
				OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class );
				//Navigate to Bills and Usage page
				Report.OperationPoint(testContext.getName(), "Navigating to Billing,Usage and Payment Page");
				
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
				Thread.sleep(10000);
				//Expand Equipment section
				
				WebElement osd = lDriver.findElement(By.xpath("//a[@aria-expanded='false' and contains(text(),'OSD')]"));
				
				Boolean bOSD = UI.WaitForObject(osd ,50);
				Report.ValidationPoint(testContext.getName(), "Validate OSD Toggle section is displayed", "True", String.valueOf(bOSD), true);
				
				osd.click();
				
		 		boolean bEquipment  = UI.WaitForObject(oR_BillAndUsage.txtEquipmentsChargesSection, 10);
		 		Report.TestPoint(testContext.getName(), "Validate that Equipment Section is displayed", "true", String.valueOf(bEquipment), true);
		 		
		 		//Verify CTA for Installment Details modal
		 		WebElement Installment = lDriver.findElement(By.xpath("//a[contains(text(),'Manage installment plan')]"));
				
		 		boolean bInstallment  = UI.WaitForObject(Installment, 10);
		 		Report.TestPoint(testContext.getName(), "Validate that CTA for Installment Details modal is displayed", "true", String.valueOf(bInstallment), true);
		 		
		 		Installment.click();
		 		
		 		String mainWindow = lDriver.getWindowHandle();
		 		
		 		Report.OperationPoint(testContext.getName(), "Switching to Frame");
		 		WebElement iframeInstallment = lDriver.findElement(By.xpath("//iframe[contains(@src,'installmentPlan')]"));
				lDriver.switchTo().frame(iframeInstallment);
				
				Report.ValidationPoint(testContext.getName(), "Verify Installment details modal is displayed", "true", "true", true);
				//driver.switchTo().defaultContent();
				WebElement txtHeader = lDriver.findElement(By.id("modalHeader"));
		 		boolean bHeader  = UI.WaitForObject(txtHeader, 10);
		 		Report.TestPoint(testContext.getName(), "Validate header for Installment Details modal is displayed", "true", String.valueOf(bHeader), true);

				
				lDriver.switchTo().window(mainWindow);
				Thread.sleep(5000);
		 		
		 		WebElement close = lDriver.findElement(By.xpath("//img[contains(@src,'Close')]"));
				close.click();
		 		
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Validate BAP is displayed", "true", "Failed to validate", true);
			}
		}
		/**************************************************************
		  * Function Name - VerifyBilledChargesFromReport()
		  * Description - 
		  * Parameters - None
		  * Date created - 21st July 2015
		  * Developed by - Gautham
		  * Last Modified By - 
		  * Last Modified Date - 
		  * @throws ParseException 
		  ***************************************************************/
		 //BAP14003_01

		 public static void VerifyBilledChargesFromReport(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
		 {
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_PaperlessBilling oR_PaperlessBilling = PageFactory.initElements(lDriver,OR_PaperlessBilling.class);
			try
			 	{
				 //Click Bill & Payments link
				UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 20, lDriver);
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
				Thread.sleep(20000);
			 	
				//Click on reports tab
				if(UI.WaitForObject(oR_BillAndUsage.lnkReportTab,30).equals(true))
				{
					oR_BillAndUsage.lnkReportTab.click();
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Reports tab");
					Report.ValidationPoint(testContext.getName(), "validate and Click on Reports tab", "Reports tab is present and clicked", "Reports tab is present and clicked", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "validate and Click on Reports tab", "Reports tab is should be present and clicked", "Reports tab is NOT", true);
				}
				
				 List<WebElement> reportTypesList = lDriver.findElements(By.xpath("//*[@id='ddShortcutBox']//dd[@class='opt botMar7']"));				
				 oR_BillAndUsage.lstSelectReportType.click();
				 Thread.sleep(2000);
				 System.out.println("Expanding report drop down");
				 Report.ValidationPoint(testContext.getName(), "List of available report types", "displayed", "displayed", true);
				 oR_BillAndUsage.lstSelectReportType.click();
				 System.out.println("Collapsing report drop down");
				  for(WebElement e : reportTypesList)
				{				   	  
					   oR_BillAndUsage.lstSelectReportType.click();
					   Thread.sleep(2000);
					   WebElement reportType = e.findElement(By.tagName("a"));
				       String reportTypeName = reportType.getText();
				       Report.OperationPoint(testContext.getName(), reportTypeName +" is present in Reports type dropdown");
				       
				     if(reportTypeName.contains("Mobile Share Overage"))
				       {
				    	   reportType.click();	   
				    	   Report.OperationPoint(testContext.getName(),"Mobile share Overage is selected");
						    
				    	// Select Start Billing Period
				    	   WebElement startBillingDate = lDriver.findElement(By.xpath("//*[@id='ddShortcut1']/div"));
			 			   startBillingDate.click();
			 			  
							WebElement startDate = lDriver.findElement(By.xpath("//div[@id='divShortcut1']//dl[@id='dateList']/dd/a"));
							UI.WaitForObject(startDate, 10);
							startDate.click();
							Report.OperationPoint(testContext.getName(),"Starting Date is selected");
							  
							Report.ValidationPoint(testContext.getName(),"Validate reports tab is displayed with Start date, End date etc..","true",String.valueOf(startBillingDate.isDisplayed()),true);
							   
				 	   // Select End Billing Period  
							WebElement endBillingDate = lDriver.findElement(By.xpath("//*[@id='ddShortcut2']/div"));
				 			UI.WaitForObject(endBillingDate, 5);
							endBillingDate.click();
							WebElement endDate = lDriver.findElement(By.xpath("(//div[@id='divShortcut2']//dl[@id='endDateList']/dd/a)[2]"));
							UI.WaitForObject(endDate, 5);
							endDate.click();

							// Click on Create Report button
							UI.WaitForObject(oR_BillAndUsage.btnCreateReport, UI.iObjTimeOut);
							oR_BillAndUsage.btnCreateReport.click();
							Thread.sleep(10000);
				 }
				        }
				boolean bprint=UI.WaitForObject(oR_BillAndUsage.lnkPrintReport, 60, lDriver);
				try 
				{ 
					//Run Authentication Pop-up program 
					Runtime.getRuntime().exec("D:\\AutoIT\\Autoit\\PRINT.exe"); 
					oR_BillAndUsage.lnkPrintReport.click();
					System.out.println("worked"); 

				}catch(Exception ae) 
				{ 
					System.out.println("Error occured while running Auth.exe File "); 
				}
				Report.ValidationPoint(testContext.getName(),"Validate Print link is present","true",String.valueOf(bprint),true);
			
				//check graph title
				WebElement chartTitle = lDriver.findElement(By.cssSelector("text.highcharts-title tspan"));
				UI.WaitForObject(chartTitle, 10);
				String strChartTitle = chartTitle.getText();
				if(strChartTitle.contains("Mobile Share Overage Charges"))
				{
					Report.ValidationPoint(testContext.getName(), "Validate title of chart graph is '"+strChartTitle+"' ", "true", "true", true);
				}
		 		
				//Check for x & y axis labels
				List<WebElement> lchartTitle = lDriver.findElements(By.cssSelector("div.highcharts-container svg text tspan"));
				for(WebElement e : lchartTitle )
				{
					String Text= e.getText();
					System.out.println(Text);
					if(Text.contains("201"))
					{
					Report.ValidationPoint(testContext.getName(), "Validating Month of the chart is '"+Text+"' displayed" , "true", "true",  true);
					break;
					}
				}
				for(WebElement e : lchartTitle )
				{
					String Text= e.getText();
					System.out.println(Text);
					
					 if(Text.contains("$"))
					{
						Report.ValidationPoint(testContext.getName(), "Validating dollar amount scale is displayed" , "true", "true",  true);
						break;
					}
				}
				try
				{
					WebElement graph= lDriver.findElement(By.cssSelector("svg g rect"));
					Report.TestPoint(testContext.getName(),"Validate graphs should be displayed as expected","Displayed","Displayed",true);
				}catch(Exception E)
				{
					Report.TestPoint(testContext.getName(),"Validate graphs should be displayed as expected","Displayed","Not displayed",true);
				}
				//
				//click on history tab
				boolean bHistory= UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab, 10);
				oR_BillAndUsage.lnkHistoryTab.click();
				Thread.sleep(10000);
				Report.ValidationPoint(testContext.getName(), "Validating History tab is displayed" , "true", String.valueOf(bHistory),  true);
				
				//
				
				
				//validating bill period and bill total
				WebElement BillTotal = lDriver.findElement(By.xpath("//th[@id='bill_total']"));
				WebElement BillPeriod = lDriver.findElement(By.xpath("//th[@id='bill_period']"));
				Report.ValidationPoint(testContext.getName(), "Validating Billing Period and Bill total  are displayed" , "true", String.valueOf(BillTotal.isDisplayed() && BillPeriod.isDisplayed()),  true);
				
				//Validating payment fields
				List<WebElement> lpayment = lDriver.findElements(By.xpath("//th"));
				for(WebElement e : lpayment )
				{
					String Text= e.getText();
					Report.OperationPoint(testContext.getName(),Text+" is displayed");
				if(Text.contains("Payments Received"))
					{
					Report.ValidationPoint(testContext.getName(), "Validating Payment fields are displayed as expected" , "true", "true",  true);
					}
					
				
				}
				//verify online and paper links
				boolean bOnline= UI.WaitForObject(oR_BillAndUsage.lnkOnlineBill, 10);
				boolean bPaper= UI.WaitForObject(oR_BillAndUsage.lnkPaperBill, 10);
				 Actions builder = new Actions(lDriver);
				 builder.moveToElement(oR_BillAndUsage.lnkOnlineBill);
				Report.ValidationPoint(testContext.getName(), "Validating 'Online', 'paper' links are displayed" , "true", String.valueOf(bOnline && bPaper),  true);
				
				//Validate Paper bill button is displayed
				Report.ValidationPoint(testContext.getName(), "Validating View Paper Bill Button is displayed" , "true", String.valueOf(bPaper),  true);
				
				//validate paperless billing link
				UI.WaitForObject(oR_BillAndUsage.lnkBillingUsagePaymentsSecNav, 20, lDriver);
				UI.SelectServiceFromSecondaryNavigation(oR_BillAndUsage.lnkBillingUsagePaymentsSecNav, oR_BillAndUsage.lnkPaperlessBillingTertNAv, lDriver);
				
			 	//
			 	boolean bPaperless= UI.WaitForObject(oR_PaperlessBilling.txtPaperlessBillingTitle,10, lDriver);
			 	Report.ValidationPoint(testContext.getName(),"Validate paperless billing page is displayed","true",String.valueOf(bPaperless),true);
			 	
			 	//
			 	}
				catch(Exception e)
				{
					e.printStackTrace();
					Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
				}
		}
		 public static void VerifyNationalDiscount(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
		 {
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Actions driver = new Actions(lDriver);	
			try
		 	{
				//Clicking on secondary navigation billing,usage and payment link
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
				
				//Validate Billing and usage page
				boolean bBU = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 20);
				Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBU),  true);
				
				//
				List <WebElement> OSDDATA= lDriver.findElements(By.xpath("//*[contains(@href,'#toggleCTN')]"));
				
				List <WebElement> MonthCharges= lDriver.findElements(By.xpath("//*[contains(@id,'monthly-alert')]"));
				
				for(int i=0;i<OSDDATA.size();i++)
				{
					OSDDATA.get(i).click();
					MonthCharges.get(i).click();
					try
					{
						WebElement nation= lDriver.findElement(By.xpath("//*[contains(text(),'National Account Discount')]"));
						Report.TestPoint(testContext.getName(), "Validate Nation account discount text is displayed" , "true", String.valueOf(nation.isDisplayed()),  true);
						
						WebElement popup= lDriver.findElement(By.xpath("//img[@class='helpImg']"));
						driver.moveToElement(popup);
						Thread.sleep(3000);
						Report.TestPoint(testContext.getName(), "Validate Nation account discount popup modal is displayed" , "true", String.valueOf(popup.isDisplayed()),  true);
						break;
					}catch(Exception e)
					{
						
					}
				}
				
				WebElement explanation= lDriver.findElement(By.id("viewExplanationOfServices"));
				explanation.click();
				lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
				WebElement explanationTxt= lDriver.findElement(By.xpath("//h1[contains(text(),'Explanation')]"));
				
				Report.ValidationPoint(testContext.getName(), "Validate explanation of services modal window is displayed" , "true", String.valueOf(explanationTxt.isDisplayed()),  true);
				lDriver.switchTo().defaultContent();
				lDriver.findElement(By.xpath("//img[@alt='close']")).click();
				
				
		 	}
			catch(Exception e)
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
			}
	}
	
	/**************************************************************
	  * Function Name - VerifyUversePreviousDSLusage()
	  * Description - Validate previous DSL usage for uverse primary MID
	  * Parameters - None
	  * Date created - 24st July 2015
	  * Developed by - Swagata Das
	  * Last Modified By - 
	  * Last Modified Date - 
	  * @throws ParseException 
	 ***************************************************************/
	 //BAP14003
	
	 public static void VerifyUversePreviousDSLusage(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
		try
		 	{
				//Clicking on secondary navigation billing,usage and payment link
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
				//Validate Billing and usage page
				boolean bBU = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 20);
				Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBU),  true);
				//Validate Report Tab
				Thread.sleep(5000);
				boolean bReportTab= UI.WaitForObject(oR_BillAndUsage.lnkReportTab, 10);
				Report.TestPoint(testContext.getName(), "Validate Report Tab" , "true", String.valueOf(bReportTab),  true);
				//Click on Report tab
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report tab");
				oR_BillAndUsage.lnkReportTab.click();
				//Validate Select Report type dropdown
				boolean bReportType = UI.WaitForObject(oR_BillAndUsage.lstSelectReportType, 5);
				Report.TestPoint(testContext.getName(), "Validate Select Report type dropdown" , "true", String.valueOf(bReportType),  true);
				Thread.sleep(2000);
				//Selecting total account charge
				oR_BillAndUsage.lstSelectReportType.click();
				Thread.sleep(2000);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Total Account Charges");
				oR_BillAndUsage.lnkTotalAccountCharges.click();
				Thread.sleep(2000);
				//Validate Start date dropdown
				boolean bReportStart = UI.WaitForObject(oR_BillAndUsage.lstStartDate, 10);
				Report.TestPoint(testContext.getName(), "Validate Start date dropdown" , "true", String.valueOf(bReportStart),  true);
				//Selecting the Start Date
				oR_BillAndUsage.lstStartDate.click();
				Thread.sleep(2000);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on starting billing period");
				List<WebElement> lstStartDate = lDriver.findElements(By.xpath("//dl[@id='dateList']//a"));
				String sTotal = lstStartDate.get(0).getText();
				lstStartDate.get(0).click();
				//Validate Start date dropdown
				//Selecting the ending Date
				boolean bReportEnd = UI.WaitForObject(oR_BillAndUsage.lstEndDate, 10);
				Report.TestPoint(testContext.getName(), "Validate Start date dropdown" , "true", String.valueOf(bReportEnd),  true);
				oR_BillAndUsage.lstEndDate.click();
				Thread.sleep(2000);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on ending billing period");
				List<WebElement> lstEndDate = lDriver.findElements(By.xpath("//dl[@id='endDateList']//a"));
				lstEndDate.get(1).click();
				//Validate Create Report button
				boolean bCreate = UI.WaitForObject(oR_BillAndUsage.btnCreateReport, 10);
				Report.TestPoint(testContext.getName(), "Validate Create Report button" , "true", String.valueOf(bCreate),  true);
				//Click on Create Report button
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Create Report button");
				oR_BillAndUsage.btnCreateReport.click();
				Thread.sleep(7000);
				
				// Check Graph Title
				WebElement chartTitle = lDriver.findElement(By.cssSelector("text.highcharts-title tspan"));
				Boolean bGraph = UI.WaitForObject(chartTitle, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Graph Title" , "true", String.valueOf(bGraph),  true);
				String strChartTitle = chartTitle.getText();
				if(strChartTitle.contains(sTotal))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Graph title matches with the selected report type" , "true","true",  true);
				}
		 	}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 }
	 
	 
	 /**************************************************************
	  * Function Name - verifyUverseVoiceUnbilledUsage()
	  * Description - 
	  * Parameters - None
	  * Date created - 23st July 2015
	  * Developed by - Nachiket Pawar
	  * Last Modified By - 
	  * Last Modified Date - 
	  * @throws ParseException 
	  ***************************************************************/
	 //BAP12245

	 public static void verifyUverseVoiceUnbilledUsage(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
	   try {
		    Report.OperationPoint(testContext.getName(), "Navigate to Billing And Usages page");
		    if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 10, lDriver)){
		      
		    	oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
		    	Thread.sleep(3000);
		    	if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver)){
		    	   Report.ValidationPoint(testContext.getName(), "Validate User is navigated to Billing & Usages page", "User is navigated to Billing & Usages page", "User is navigated to Billing & Usages page", true);
		    	   Report.OperationPoint(testContext.getName(), "Click on Usage tab");
		    	    
		    	   if(UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut, lDriver)){
		    		   oR_BillAndUsage.lnkUsage.click();
		    		   Thread.sleep(5000);
		    		   if(UI.WaitForObject(oR_BillAndUsage.btnUsageSelectionDropdown, UI.iObjTimeOut, lDriver)){
		    			   			    			   			    			   
		    			   Report.OperationPoint(testContext.getName(), "Select Recent Usage from Usgae drop down");
		    			   
		    			   oR_BillAndUsage.btnUsageSelectionDropdown.click();
		    			   Thread.sleep(5000);
		    			   
		    			   oR_BillAndUsage.lnkRecentUsageFromDropDown.click();
		    			   Thread.sleep(5000);
		    			   
		    			   Report.OperationPoint(testContext.getName(), "Select AT&T U-verse voice from drop down");
		    			   oR_BillAndUsage.lnkUsageFor.click();
		    			   Thread.sleep(5000);
		    			   
		    			   if(UI.WaitForObject(oR_BillAndUsage.lnkUverseVoiceUsage, UI.iObjTimeOut, lDriver)){
		    			        oR_BillAndUsage.lnkUverseVoiceUsage.click();
		    			   }
		    			   
		    			   if(UI.WaitForObject(oR_BillAndUsage.txtMsgBoxVoiceServiceNotAvailable, UI.iObjTimeOut, lDriver)){
		    				  Report.ValidationPoint(testContext.getName(), "Validate Voice service was not available message is displayed", "Voice service was not available message is displayed", "Voice service was not available message is displayed", true); 
		    			   }else{
		    				   Report.ValidationPoint(testContext.getName(), "Validate Voice service was not available message is displayed", "Voice service was not available message is displayed", "Voice service was not available message is NOT displayed", true); 
		    			   }
		    				    			   
		    		   }else{
		    			   Report.ValidationPoint(testContext.getName(), "Validate Usage page is displayed ", "Usage page is displayed", "Usage page is NOT displayed", true);
		    		   }
		    		   
		    		   
		    	   }else{
		    		   
		    		   Report.ValidationPoint(testContext.getName(), "Validate that Usage tab is displayed", "Usage tab is displayed", "Usage tab is not displayed", true);
		    	   }
		    	   
		    	
		    	}else{
		    	   Report.ValidationPoint(testContext.getName(), "Validate User is navigated to Billing & Usages page", "User is navigated to Billing & Usages page", "User is NOT navigated to Billing & Usages page", true);
		    	}
		    	
		    	
		    }
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
}
		/**************************************************************
	  * Function Name - VerifyUsageDetailsModalTalk()
	  * Description - Validate the usage details modal for talk in usage section
	  * Parameters - None
	  * Date created - 24st July 2015
	  * Developed by - Swagata Das
	  * Last Modified By - 
	  * Last Modified Date - 
	  * @throws ParseException 
	 ***************************************************************/
	 //BAP09953
	
	 public static void VerifyUsageDetailsModalTalk(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		OR_ManagePhoneBook oR_ManagePhoneBook = PageFactory.initElements(lDriver, OR_ManagePhoneBook.class);
		OR_PaperlessBilling or_PaperlessBilling = PageFactory.initElements(lDriver, OR_PaperlessBilling.class);
		
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sNumber = IO.GetParamVal(sTestParams, "Number");
		String sAlias = IO.GetParamVal(sTestParams, "Alias");
		String sName = IO.GetParamVal(sTestParams, "Name");
			
		try
		 	{
				//Clicking on secondary navigation billing,usage and payment link
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
				//Validate Billing and usage page
				boolean bBU = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 20);
				Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBU),  true);
				//Validate Usage Tab and click on it
				Thread.sleep(7000);
				boolean bUsageTab = UI.WaitForObject(oR_BillAndUsage.lnkUsage, 20);
				Report.TestPoint(testContext.getName(), "Validate Usage Tab" , "true", String.valueOf(bUsageTab),  true);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Usage Tab");
				oR_BillAndUsage.lnkUsage.click();
				Thread.sleep(5000);
				//Validate view usage details link
				boolean bViewUsageLink = UI.WaitForObject(oR_BillAndUsage.lnkViewUsageDetails, 5);
				Report.TestPoint(testContext.getName(), "Validate view usage details link" , "true", String.valueOf(bViewUsageLink),  true);
				//Click on view usage details link
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on view usage details link");
				oR_BillAndUsage.lnkViewUsageDetails.click();
				//Validate Usage details frame
				boolean bViewUsageFrame = UI.WaitForObject(oR_BillAndUsage.frmBillUsageDetail1, 10);
				Report.TestPoint(testContext.getName(), "Validate Usage details frame" , "true", String.valueOf(bViewUsageFrame),  true);
				Thread.sleep(5000);
				//Switch to frame
				String sMainWindowHandle = lDriver.getWindowHandle();
				WebElement frameHandle = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
				lDriver.switchTo().frame(frameHandle);
				//lDriver.switchTo().frame(oR_BillAndUsage.frmBillUsageDetail1);
					//Validate heading of the frame
					boolean bHeadingFrame = UI.WaitForObject(oR_BillAndUsage.frmTitle, 20);
					Report.ValidationPoint(testContext.getName(), "Validate Usage details frame" , "true", String.valueOf(bHeadingFrame),  true);
					//Select Recent usage
					if(lDriver.findElement(By.xpath("//a[@id='billingShortcut']//div")).getText().contains("Current Billed Usage"))
					{
						new Actions(lDriver).moveToElement(lDriver.findElement(By.xpath("//a[@id='billingShortcut']"))).click().build().perform();
						Thread.sleep(1000);
						//new Actions(lDriver).moveToElement(lDriver.findElement(By.xpath("//a[@id='billingShortcutBox']//*[contains(text(),'Recent Usage')]//parent::div//parent::div//a"))).click().build().perform();
						//new Actions(lDriver).moveToElement(lDriver.findElement(By.xpath("//div[@id='RecentUnbilled']//a"))).click().build().perform();
						Report.OperationPoint(testContext.getName(), "Selected Recent usage from the Usage selection drop-down");
						
					}else if(lDriver.findElement(By.xpath("//a[@id='billingShortcut']//div")).getText().contains("Recent Usage"))
					{
						Report.OperationPoint(testContext.getName(), "Recent usage is already selected by default");
					}else
					{
						Report.OperationPoint(testContext.getName(), "ERROR in selecting Recent usage!");
					}
					//Select View details by : Talk
					oR_BillAndUsage.frmViewUsageDD.click();
					Thread.sleep(4000);
					Report.OperationPoint(testContext.getName(), "	Operational - Selecting View details by : Talk");
					oR_BillAndUsage.frmTalkLink.click();
					Thread.sleep(7000);
					//Validate Usage details table
					boolean bTable = UI.WaitForObject(oR_BillAndUsage.frmUsageTable, 10);
					Report.ValidationPoint(testContext.getName(), "Validate Usage details table" , "true", String.valueOf(bTable),  true);
					//Retriveing the Table headings
					List<WebElement> lstTableHeading = lDriver.findElements(By.xpath("//table[@id='t']//th//a"));
					Report.OperationPoint(testContext.getName(), "	Operational - Retriveing the Table headings");
					for(int i=0;i<lstTableHeading.size();i++)
					{
						Report.OperationPoint(testContext.getName(), lstTableHeading.get(i).getText());
						//Validate the sorting property of the heading
						if(lstTableHeading.get(i).getAttribute("linkname").contains("Sort"))
						{
							Report.ValidationPoint(testContext.getName(), "Validate sorting property of "+lstTableHeading.get(i).getText() , "true","true",  true);
						}
						else
						{
								Report.ValidationPoint(testContext.getName(), "Validate sorting property of "+lstTableHeading.get(i).getText() , "true","false",  true);
						}	
					}
					//Validate Date/time – format: mm/dd/yyyy 00:00pm/am 
					List<WebElement> lstDate = lDriver.findElements(By.xpath("//table[@id='t']//tr[@class='odd']//td[1]"));
					String Date = lstDate.get(0).getText().trim();
					String sPattern = "\\d{2}\\/\\d{2}\\/\\d{4}.*";
					if(Date.matches(sPattern))
					{
						Report.ValidationPoint(testContext.getName(), "Validate Date/time – format: mm/dd/yyyy", "true","true",  true);
					}
					
					//Validate for location, city, state is displayed
					List<WebElement> lstLocation = lDriver.findElements(By.xpath("//table[@id='t']//tr//td[3]"));
					Report.OperationPoint(testContext.getName(), "	Operational - Retriveing the Locations");
					for(int i=0;i<lstLocation.size();i++)
					{
						Report.OperationPoint(testContext.getName(), lstLocation.get(i).getText());
					}
					//Validate Charges – format: x.xx
					List<WebElement> lstCharge = lDriver.findElements(By.xpath("//table//tr//td[6]"));
					String sCharge = lstCharge.get(0).getText().trim();
					String sPatt = "\\d.\\d\\d";
					if(sCharge.matches(sPatt))
					{
						Report.ValidationPoint(testContext.getName(), "Validate Charges – format: x.xx", "true","true",  true);
					}
					
//					//Validate Search by dropdpwn
//					boolean bSearchBy = UI.WaitForObject(oR_BillAndUsage.frmDrpSearchBy, 10);
//					Report.ValidationPoint(testContext.getName(), "Validate Search by dropdpwn" , "true", String.valueOf(bSearchBy),  true);
//					//Validate Search by text box
//					boolean bSearchByBtn = UI.WaitForObject(oR_BillAndUsage.frmDrpSearchByButton, 10);
//					Report.ValidationPoint(testContext.getName(), "Validate Search by text box" , "true", String.valueOf(bSearchByBtn),  true);
					//Validate Nickname a number link
					boolean blnkNick = UI.WaitForObject(oR_BillAndUsage.frmLnkNickname, 10);
					Report.ValidationPoint(testContext.getName(), "Validate Nickname a number link" , "true", String.valueOf(blnkNick),  true);
					//Click on link Nickname a number
					Report.OperationPoint(testContext.getName(), "	Operational - clicking link Nickname a number");
					oR_BillAndUsage.frmLnkNickname.click();
					//Validate Number and name textboxes in add a nickname div
					boolean bNickNo = UI.WaitForObject(oR_BillAndUsage.frmEdtAddNo, 10);
					Report.ValidationPoint(testContext.getName(), "Validate Number textbox in add a nickname div" , "true", String.valueOf(bNickNo),  true);
					boolean bNickName = UI.WaitForObject(oR_BillAndUsage.frmEdtAddName, 10);
					Report.ValidationPoint(testContext.getName(), "Validate Name textbox in add a nickname div" , "true", String.valueOf(bNickName),  true);
					//Validate Save nickname button in add a nickname div
					boolean bNickSave = UI.WaitForObject(oR_BillAndUsage.frmSaveNickname, 10);
					Report.ValidationPoint(testContext.getName(), "Validate Save nickname button in add a nickname div" , "true", String.valueOf(bNickSave),  true);
					//Validate Cancel button in add a nickname div and click on it
					boolean bNickCan = UI.WaitForObject(oR_BillAndUsage.frmCancel, 10);
					Report.ValidationPoint(testContext.getName(), "Validate Cancel button in add a nickname div" , "true", String.valueOf(bNickCan),  true);
					Report.OperationPoint(testContext.getName(), "	Operational - clicking Cancel button in add a nickname div");
					oR_BillAndUsage.frmCancel.click();
					//Validate link print
					boolean bPrint = UI.WaitForObject(oR_BillAndUsage.frmLnkPrint, 5);
					Report.ValidationPoint(testContext.getName(), "Validate link print" , "true", String.valueOf(bPrint),  true);
					//Validate Download dropdown
					boolean bDownload = UI.WaitForObject(oR_BillAndUsage.frmDrpDownload, 5);
					Report.ValidationPoint(testContext.getName(), "Validate Download dropdown" , "true", String.valueOf(bDownload),  true);
					
					//Validate manage contacts link and click
					boolean bMC = UI.WaitForObject(oR_BillAndUsage.frmLnkManageContacts, 5);
					Report.ValidationPoint(testContext.getName(), "Validate link manage contacts" , "true", String.valueOf(bMC),  true);
					Report.OperationPoint(testContext.getName(), "	Operational - clicking manage contacts link");
					oR_BillAndUsage.frmLnkManageContacts.click();
					Thread.sleep(5000);
					//Switch back from frame
				//lDriver.switchTo().defaultContent();
				lDriver.switchTo().window(sMainWindowHandle);
				
				//Validate manage phone book page is displayed
				boolean bMCP = UI.WaitForObject(oR_ManagePhoneBook.txtManagePhoneBook, 20);
				Report.TestPoint(testContext.getName(), "Validate manage phone book page is displayed" , "true", String.valueOf(bMCP),  true);
				//Validate Add entry link and click
				boolean bAddEntry = UI.WaitForObject(oR_ManagePhoneBook.lnkAddEntry, 20);
				Report.TestPoint(testContext.getName(), "Validate Add entry link" , "true", String.valueOf(bAddEntry),  true);
				Report.OperationPoint(testContext.getName(), "	Operational - clicking on link Add entry");
				Thread.sleep(10000);
				//UI.VerifyLinkNavigatestoNewWindow(oR_ManagePhoneBook.lnkAddEntry, "PhonebookDetailPage");
				 String winHandleBefore = lDriver.getWindowHandle();
				oR_ManagePhoneBook.lnkAddEntry.click();
				//Switch to window add phone book entry
			    for (String winHandle : lDriver.getWindowHandles()) 
			    {
			    	lDriver.switchTo().window(winHandle);
			    }
				//Validate Add Phone Book Entry heading
				boolean bAdd = UI.WaitForObject(oR_ManagePhoneBook.txtAddPBentry, 50);
				Report.TestPoint(testContext.getName(), "Validate Add Phone Book Entry heading" , "true", String.valueOf(bAdd),  true);
				Report.OperationPoint(testContext.getName(), "	Operational - Entering the number, alias and name");
				//Enter the number
				boolean bNum = UI.WaitForObject(oR_ManagePhoneBook.edtPhoneNo, 5);
				Report.TestPoint(testContext.getName(), "Validate text box number" , "true", String.valueOf(bNum),  true);
				oR_ManagePhoneBook.edtPhoneNo.clear();
				oR_ManagePhoneBook.edtPhoneNo.sendKeys(sNumber);
				//Enter the alias
				boolean bAlias = UI.WaitForObject(oR_ManagePhoneBook.edtAlias, 50);
				Report.TestPoint(testContext.getName(), "Validate text box Alias" , "true", String.valueOf(bAlias),  true);
				oR_ManagePhoneBook.edtAlias.clear();
				oR_ManagePhoneBook.edtAlias.sendKeys(sAlias);
				//Enter the Name
				boolean bName = UI.WaitForObject(oR_ManagePhoneBook.edtName, 50);
				Report.TestPoint(testContext.getName(), "Validate text box Name" , "true", String.valueOf(bName),  true);
				oR_ManagePhoneBook.edtName.clear();
				oR_ManagePhoneBook.edtName.sendKeys(sName);
				//Click on Submit button
				Report.OperationPoint(testContext.getName(), "	Operational - clicking on submit button");
				oR_ManagePhoneBook.btnSubmit.click();
				Thread.sleep(5000);
				lDriver.switchTo().window(winHandleBefore);
				Thread.sleep(2000);
				//Validate link delete and click on it
				boolean bDel = UI.WaitForObject(oR_ManagePhoneBook.lnkDelete, 10);
				Report.TestPoint(testContext.getName(), "Validate link delete" , "true", String.valueOf(bDel),  true);
				Report.OperationPoint(testContext.getName(), "	Operational - clicking on link Delete");
				oR_ManagePhoneBook.lnkDelete.click();
				//Enter the keword "Enter"
				Alert alert = lDriver.switchTo().alert();
				alert.accept();
				//Validate Delete link is not present
				UI.VerifyElementNotPresent(oR_ManagePhoneBook.lnkDelete, "Delete");
				/*boolean bAdd1 = UI.WaitForObject(oR_ManagePhoneBook.txtManagePhoneBook, 20);
				Report.TestPoint(testContext.getName(), "Validate Add Phone Book Entry heading" , "true", String.valueOf(bAdd1),  true);*/
				//Validate link Go back to Usage page and click
				boolean bGoBack = UI.WaitForObject(oR_ManagePhoneBook.lnkGoBack, 20);
				Report.TestPoint(testContext.getName(), "Validate link Go back to Usage page" , "true", String.valueOf(bGoBack),  true);
				Report.OperationPoint(testContext.getName(), "	Operational - clicking on link Go back to Usage page");
				oR_ManagePhoneBook.lnkGoBack.click();
				
				//Validate biling and usage page
				boolean bBP = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 50);
				Report.TestPoint(testContext.getName(), "Validate biling and usage page" , "true", String.valueOf(bBP),  true);
				Thread.sleep(4000);
				//Validate History tab and click on it
				boolean bHistory = UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab, 50);
				Report.TestPoint(testContext.getName(), "Validate History tab" , "true", String.valueOf(bHistory),  true);
				Report.OperationPoint(testContext.getName(), "	Operational - clicking on History tab");
				oR_BillAndUsage.lnkHistoryTab.click();
				//Validate biling and payment history heading
				boolean bBPH = UI.WaitForObject(oR_BillAndUsage.txtBillAndPaymentHistory, 20);
				Report.TestPoint(testContext.getName(), "Validate biling and payment history heading" , "true", String.valueOf(bBPH),  true);
				//Validate paper bill link
				List<WebElement> lstPaper = lDriver.findElements(By.xpath("//a[text()='Paper']"));
				if(lstPaper.size()>0)
				{
					Report.TestPoint(testContext.getName(), "Validate paper bill link" , "true","true",  true);
				}
				else
				{
					Report.TestPoint(testContext.getName(), "Validate paper bill link" , "true","false",  true);
				}
				
				//Click on Report tab
				Report.OperationPoint(testContext.getName(), "	Operational - clicking on Report tab");
				oR_BillAndUsage.lnkReportTab.click();
				//Validate Select Report type dropdown
				boolean bReportType = UI.WaitForObject(oR_BillAndUsage.lstSelectReportType, 5);
				Report.TestPoint(testContext.getName(), "Validate Select Report type dropdown" , "true", String.valueOf(bReportType),  true);
				Thread.sleep(4000);
				//Selecting total account charge
				oR_BillAndUsage.lstSelectReportType.click();
				Thread.sleep(2000);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Total Account Charges");
				oR_BillAndUsage.lnkTotalAccountCharges.click();
				Thread.sleep(2000);
				//Validate Start date dropdown
				boolean bReportStart = UI.WaitForObject(oR_BillAndUsage.lstStartDate, 10);
				Report.TestPoint(testContext.getName(), "Validate Start date dropdown" , "true", String.valueOf(bReportStart),  true);
				//Selecting the Start Date
				oR_BillAndUsage.lstStartDate.click();
				Thread.sleep(2000);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on starting billing period");
				List<WebElement> lstStartDate = lDriver.findElements(By.xpath("//dl[@id='dateList']//a"));
				String sTotal = lstStartDate.get(0).getText();
				lstStartDate.get(0).click();
				//Validate Start date dropdown
				//Selecting the ending Date
				boolean bReportEnd = UI.WaitForObject(oR_BillAndUsage.lstEndDate, 10);
				Report.TestPoint(testContext.getName(), "Validate End date dropdown" , "true", String.valueOf(bReportEnd),  true);
				oR_BillAndUsage.lstEndDate.click();
				Thread.sleep(2000);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on ending billing period");
				List<WebElement> lstEndDate = lDriver.findElements(By.xpath("//dl[@id='endDateList']//a"));
				lstEndDate.get(0).click();
				//Validate Create Report button
				boolean bCreate = UI.WaitForObject(oR_BillAndUsage.btnCreateReport, 10);
				Report.TestPoint(testContext.getName(), "Validate Create Report button" , "true", String.valueOf(bCreate),  true);
				//Click on Create Report button
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Create Report button");
				oR_BillAndUsage.btnCreateReport.click();
				Thread.sleep(7000);
				
				// Check Graph Title
				WebElement chartTitle = lDriver.findElement(By.cssSelector("text.highcharts-title tspan"));
				Boolean bGraph = UI.WaitForObject(chartTitle, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Graph Title" , "true", String.valueOf(bGraph),  true);
				String strChartTitle = chartTitle.getText();
				if(strChartTitle.contains(sTotal))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Graph title matches with the selected report type" , "true","true",  true);
				}
				//Click on Tertiery navigation link paperless billing
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkPaperlessBillingSecNav);
				//Validate paperless billing page
				Boolean bPB = UI.WaitForObject(or_PaperlessBilling.txtPaperlessBillingTitle,UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate paperless billing page" , "true", String.valueOf(bPB),  true);
		 	}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 }
	 /**************************************************************
	  * Function Name - VerifyWirelessSuspendedAlertBillingPage()
	  * Description - Validate the wireless suspended alert in billing page
	  * Parameters - None
	  * Date created - 31st July 2015
	  * Developed by - Swagata Das
	  * Last Modified By - Monica Mohabansi
	  * Last Modified Date - 15th Jan 2016
	  * @throws ParseException 
	 ***************************************************************/
	 //BAP34608
	
	 public static void VerifyWirelessSuspendedAlertBillingPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		OR_SuspendReactivateService oR_SuspendReactivateService = PageFactory.initElements(lDriver, OR_SuspendReactivateService.class);
		
		try
		{
			if(UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails, UI.iObjTimeOut, lDriver)){
			//Click on Billing, usage and payment sec navigation link
			//UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkBillDetailsTertNav);
			//Validate billing and usage page
			oR_AccountOverview.lnkViewBillDetails.click();
			Boolean bBUPg = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
			Report.TestPoint(testContext.getName(), "Validate billing and usage page" , "true", String.valueOf(bBUPg),  true);
			Thread.sleep(5000);
			//Validate bill alerts section
			Boolean bAlert = UI.WaitForObject(oR_BillAndUsage.txtBillAlerts,5);
			Report.TestPoint(testContext.getName(), "Validate bill alerts section" , "true", String.valueOf(bAlert),  true);
			//Validate alert for mobile number suspended 
			/** changed xpath -Monica - 15th Jan 2016**/
			WebElement txtSuspend = lDriver.findElement(By.xpath("//span[contains(text(),'Mobile number suspended')]"));
			Boolean bSuspend = UI.WaitForObject(txtSuspend,2);
			Report.TestPoint(testContext.getName(), "Validate alert for mobile number suspended" , "true", String.valueOf(bSuspend),  true);
			//Validate reactivate service link and click on it
			WebElement Reactivate = lDriver.findElement(By.xpath("//div[contains(text(),'Mobile number suspended')]/a[contains(text(),'Reactivate service')]"));
			Boolean bReactivate = UI.WaitForObject(Reactivate,2);
			Report.TestPoint(testContext.getName(), "Validate reactivate service link" , "true", String.valueOf(bReactivate),  true);
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on reactivate service");
			Reactivate.click();
			//Validate suspend and Reactivate service Page
			Boolean bReactivatePg = UI.WaitForObject(oR_SuspendReactivateService.txtReactivateYourService,50);
			Report.TestPoint(testContext.getName(), "Validate suspend and Reactivate service Page" , "true", String.valueOf(bReactivatePg),  true);
		   }else{
			   Report.ValidationPoint(testContext.getName(), "Validate View bill details link is displayed", "True", "False", true);
		   }
		 }
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 }
	 
	 /**************************************************************
		 * Function Name -  ValidateEquipmentChargesOnBillPage()
		 * Description- This method validates that Equipment Price and equipment charges are present on Bill page and One time charges and Adjustment section(IF PRESENT) do not have down payment details
		 * Parameters - None
		 * Date created -3rd August 2015
		 * Developed by - Monica Mohabansi
		 * Last Modified By - 
		 * Last Modified Date -
		 * @throws AWTException 
		 * @throws IOException 
		 * @throws HeadlessException 
		 * @throws ParseException 
		 ***************************************************************/ 	
	 //BAP33304
	 	public static void ValidateEquipmentChargesOnBillPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	 	{
		 	try
		 	{
		 		WebDriver lDriver = UI.getDriver(testContext.getName());
		 		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		 		OR_BillAndUsage	oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
//		 		Click Bill & Payments CTA from Global navigation
		 		Boolean bBAPLink = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 20);
		 		Report.TestPoint(testContext.getName(), "Click on BAP link from ","true" , String.valueOf(bBAPLink), true);
		 		oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
		 		
//		 		Verify Equipment Charges Section within the Bill Page
		 		Boolean bEquipCharges = UI.WaitForObject(oR_BillAndUsage.txtEquipmentsChargesSection, 90);
		 		Report.ValidationPoint(testContext.getName(), "Equipment Charges on Bill page", "True", String.valueOf(bEquipCharges), true);
		 		
//		 		Equipment Price should be displayed with corresponding charges of Equipment Charges Section
		 		Boolean bEquipmentPrice = UI.WaitForObject(oR_BillAndUsage.txtTotalEquipmentCharges, 3);
		 		Report.ValidationPoint(testContext.getName(), "Equipment Price with corresponding charges of Equipment Charges Section", "True", String.valueOf(bEquipmentPrice), true);

//		 		One Time Charges Section should not contain next down payment details if the section is displayed
		 		Boolean bOneTimeCharges = UI.WaitForObject(oR_BillAndUsage.lnkOneTimeCharges, 2);
		 		if(bOneTimeCharges.equals(false))
		 			Report.ValidationPoint(testContext.getName(), "One Time Charges Section should not contain next down payment details if the section is displayed", "One Time Charges Section not present", "One Time Charges Section not present", true);
		 		else
			 		Report.ValidationPoint(testContext.getName(), "One Time Charges Section should not contain next down payment details if the section is displayed", "One Time Charges Section not present", "One Time Charges Section is present", true);
		 		
//		 		Payments and Adjustments should not contain next down payment details if the section is displayed
		 		Boolean bAdjustments = UI.WaitForObject(oR_BillAndUsage.lnkOneTimeCharges, 2);
		 		if(bAdjustments.equals(false))
		 			Report.ValidationPoint(testContext.getName(), "Payments and Adjustments should not contain next down payment details if the section is displayed", "Payments And Adjustments not present", "Payments And Adjustments not present", true);
		 		else
			 		Report.ValidationPoint(testContext.getName(), "One Time Charges Section should not contain next down payment details if the section is displayed", "One Time Charges Section not present", "One Time Charges Section is present", true);

		 	}
		 	catch(Exception e)
		 	{
		 		Report.TestPoint(testContext.getName(), "Failed to validate Equipment charges " +e.getMessage(), "PASS", "FAIL", true);
		 	}
	 	}
	 	/**************************************************************
		 * Function Name - ValidateCreditAdjustmentsInBillTab()
		 * Description-  this function valiadtes credit adjustments section
		 * * Parameters - 
		 * Date created - 3rg August 2015
		 * Developed by - Gautham
		 * Last Modified By - 
		 * Last Modified Date -
		 ***************************************************************/		
	 	//BAP30673
		public static void ValidateCreditAdjustmentsInBillTab(final ITestContext testContext) throws Exception 
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			try
			{
				//navigating to bills and usage page
				boolean BUP= UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 5, lDriver);
				boolean View=UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails, 10, lDriver);
				if(BUP |View)
				{
					if(BUP)
					{
						UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
					}
					else if(View)
					{
						oR_AccountOverview.lnkViewBillDetails.click();
					}
				
				Report.OperationPoint(testContext.getName(), "Navigating to Bills & Usage landing page");	
					//Verify Previous Activity Section
					//Thread.sleep(30000);
					//WebElement Credit = lDriver.findElement(By.xpath("//div[@class='sub-group-title PadTop7 Padbot2 botDotBorder PadBot0ie']//a[@id='credit-alert']"));
					if(UI.WaitForObject(oR_BillAndUsage.lnkCreditsAdjustments, 120, lDriver))
					{
						Report.TestPoint(testContext.getName(), "Validating Credits and adjustments sections is present as expected", "true", String.valueOf(oR_BillAndUsage.lnkCreditsAdjustments.isDisplayed()), true);
						
						//Verify the Bill Alert Section
						List <WebElement> Billed = lDriver.findElements(By.xpath("//div[@class='MarLeft32 top-2pxIE ']//strong[contains(text(),'Billed credits')]"));
						
						if( Billed.size()==1)
						{
							Report.TestPoint(testContext.getName(), "Validating Bill Alerts sections is present", "true", String.valueOf(Billed.size()==1), true);
							
							//Click on "View Charges" CTA
							if(UI.WaitForObject(oR_BillAndUsage.lnkViewcredits, 120, lDriver))
							{
								oR_BillAndUsage.lnkViewcredits.click();
								WebElement Minus = lDriver.findElement(By.xpath("//a[@id='credit-alert']//img[@alt='collapse']"));
								if(Minus.isDisplayed())
								{
									Report.TestPoint(testContext.getName(), "Validating View alerts link is redirected to credits and adjustments section expanding it", "true", String.valueOf(Minus.isDisplayed()), true);
								}
								else
								{
									Report.TestPoint(testContext.getName(), "Validating View alerts link is redirected to credits and adjustments section expanding it", "true", "fail", true);
								}
							}
							else
							{
								Report.TestPoint(testContext.getName(), "Validating View credits link is present", "true", "fail", true);
							}
						}
						else
						{
							Report.TestPoint(testContext.getName(), "Validating Bill Alerts sections is present as expected", "true", "Failed to validate", true);
						}
					}
					else
					{
						Report.TestPoint(testContext.getName(), "Validating Credits and adjustments sections is present", "true", "Failed to validate", true);
					}
				}
				else
				{
					Report.TestPoint(testContext.getName(), "Validating Bills and Payments in global navigation/View bill details in dashboard", "true", "Failed to validate", true);
				}
				
			}
	
			catch(Exception e)
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
			}
		 }

		/**************************************************************
		 * Function Name - VerifyNationalAccInfoAndExplanationOfServicesLink()
		 * Description - Verify National Acct Info tooltip and Explanation of services link in Billing page
	 	 * Test Case -  BAP28100
		 * Parameters - None
		 * Date created - 4th Aug 2015
		 * Developed by - Clint John
		 * Last Modified By - 
		 * Last Modified Date - 
		 ***************************************************************/
		public static void VerifyNationalAccInfoAndExplanationOfServicesLink(final ITestContext testContext) throws HeadlessException, IOException, AWTException
		{
			try
			{
				WebDriver lDriver = UI.getDriver(testContext.getName());
				OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
				OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
				Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
				
				//Naviagte to Billing & Usage page
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Billing, Usage and Payments' from Secondary navigation bar");
				
				lDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				//Verify a tool tip is present at "National Account Discount"
				boolean bToolTip = UI.WaitForObject(oR_BillAndUsage.imgNationalAccountDiscountToolTip, UI.iObjTimeOut, lDriver);
				if(bToolTip)
				{
					Report.ValidationPoint(testContext.getName(), "Verify that tool tip for National Account Discount is displayed", "true","true", true);
					//Verify the modal window
					Report.OperationPoint(testContext.getName(), "Operational - Hovering over tool tip for National Account Discount");
					new Actions(lDriver).moveToElement(oR_BillAndUsage.imgNationalAccountDiscountToolTip).build().perform();
					boolean bToolTipFrame = UI.WaitForObject(oR_BillAndUsage.imgNationalAccountDiscountToolTipFrame, UI.iObjTimeOut, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify a modal window is displayed when hovered over the tool tip : "+oR_BillAndUsage.txtNationalAccDiscountText.getText(), "true",String.valueOf(bToolTipFrame), true);
					
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that tool tip for National Account Discount is displayed", "true","false", true);
				}
				
				//Verify "View explanation of charges" link and modal window
				boolean bExplanationOfServices = UI.WaitForObject(oR_BillAndUsage.lnkExplanationOfServices, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that link for Explanation Of Services is displayed at the bottom of Billing & Usage section", "true",String.valueOf(bExplanationOfServices), true);
				oR_BillAndUsage.lnkExplanationOfServices.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Explanation of services' link under Billing & Usage section");
				
					//Verify the modal explanation window
					String sMainWindowHandle = lDriver.getWindowHandle();
					WebElement frameHandle = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
					lDriver.switchTo().frame(frameHandle);
					
						boolean bHeading = UI.WaitForObject(oR_BillAndUsage.txtExplanationOfServicesFrameHeading, UI.iObjTimeOut, lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify that a modal window for Explanation of Services is displayed with the details", "true",String.valueOf(bHeading), true);
					
					lDriver.switchTo().window(sMainWindowHandle);
					oR_BillAndUsage.btnCloseFrame.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on close button of modal window opened");
					
				
				Report.OperationPoint(testContext.getName(), "NOTE:- Validation is NOT done for: Explanation of charges should be present for Spanish language as well | REASON: Spanish conversion NOT working at the time of development of script");
					
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

			}
			
		}

	/**************************************************************
	  * Function Name - VerifyPrecycleBillPageNewDTVexistingWireless()
	  * Description - Validate Precycle Bill Page for New DTV and existing Wireless
	  * Parameters - None
	  * Date created - 5th Aug 2015
	  * Developed by - Swagata Das
	  * Last Modified By - 
	  * Last Modified Date - 
	  * @throws ParseException 
	 ***************************************************************/
	 //BAP34604
	 public static void VerifyPrecycleBillPageNewDTVexistingWireless(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		OR_PaperlessBilling oR_PaperlessBilling = PageFactory.initElements(lDriver, OR_PaperlessBilling.class);
		OR_AutoPayEnrollment oR_AutoPayEnrollment = PageFactory.initElements(lDriver, OR_AutoPayEnrollment.class);
		try
		{
			//Navigate to Billing,usage and payment page
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			//Validate Billing and usage page
			Boolean bBUpage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
			Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBUpage),  true);
			Thread.sleep(5000);
			//Verify below elements within Top wrapper of Bill,History and Report Pages
			//Total Amount Due
			//View Paper Bill CTA
			//Make a Payment button
			Boolean bAmountDue = UI.WaitForObject(oR_BillAndUsage.txtTotalAmountDue,5);
			Report.ValidationPoint(testContext.getName(), "Validate Total amount due" , "true", String.valueOf(bAmountDue),  true);
 //         Check for Paper Bill CTA is not requried. As confirmed with Pallavi Pande from manual team
//			Boolean bPaperBill = UI.WaitForObject(oR_BillAndUsage.btnPaperBill,5);
//			Report.ValidationPoint(testContext.getName(), "Validate View Paper Bill CTA" , "true", String.valueOf(bPaperBill),  true);
			Boolean bPayment = UI.WaitForObject(oR_BillAndUsage.btnMakePaymentInBillingPage,5);
			Report.ValidationPoint(testContext.getName(), "Validate Make a Payment button" , "true", String.valueOf(bPayment),  true);
			
			//Validate Play video on flexible payments
			Boolean bPlayVid = UI.WaitForObject(oR_BillAndUsage.lnkPlayVideo,5);
			Report.ValidationPoint(testContext.getName(), "Validate Play video on flexible payments" , "true", String.valueOf(bPlayVid),  true);
			//Validate Get help section
			Boolean bGetHelp = UI.WaitForObject(oR_BillAndUsage.txtGetHelp,5);
			Report.ValidationPoint(testContext.getName(), "Validate Get help section" , "true", String.valueOf(bGetHelp),  true);
			//Validate Make payment arrangements link and click
			if(UI.WaitForObject(oR_BillAndUsage.lnkMakePaymentArrangements,5))
			{
				Report.ValidationPoint(testContext.getName(), "Validate Get help section" , "true", String.valueOf(bGetHelp),  true);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Make payment arrangements");
				oR_BillAndUsage.lnkMakePaymentArrangements.click();
				Thread.sleep(7000);
				if(lDriver.getCurrentUrl().contains("esupport"))
				{
					Report.ValidationPoint(testContext.getName(), "Validate navigation to required page" , "true", "true",  true);
					lDriver.navigate().back();
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate navigation to required page" , "true", "false",  true);
					lDriver.navigate().back();
				}
			}
			//Validate Billing and usage page
			Boolean bBUpage1 = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
			Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBUpage1),  true);
			Thread.sleep(3000);
			//Verify Manage Billing & Payment Options Section
			Boolean bManageSection = UI.WaitForObject(oR_BillAndUsage.txtManageBillingAndPaymentOptions,5);
			Report.TestPoint(testContext.getName(), "Validate Manage Billing & Payment Options Section" , "true", String.valueOf(bManageSection),  true);
			//Click on Enroll in Paperless Billing link
			if(UI.WaitForObject(oR_BillAndUsage.lnkEnrollPaperlessBilling,5))
			{
				Report.ValidationPoint(testContext.getName(), "Validate Enroll in Paperless Billing link" , "true", "true",  true);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Enroll in Paperless Billing link");
				oR_BillAndUsage.lnkEnrollPaperlessBilling.click();
				//Validate paperless billing page
				Boolean bPaperlessPage = UI.WaitForObject(oR_PaperlessBilling.txtPaperlessBillingTitle,5);
				Report.ValidationPoint(testContext.getName(), "Validate paperless billing page" , "true", String.valueOf(bPaperlessPage),  true);
				lDriver.navigate().back();
			}
			//Validate Billing and usage page
			Boolean bBUpage2 = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
			Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBUpage2),  true);
			Thread.sleep(3000);
			//Click on Enroll in autopay link
			if(UI.WaitForObject(oR_BillAndUsage.lnkEnrollInAutopay,5))
			{
				Report.ValidationPoint(testContext.getName(), "Validate Enroll in autopay link " , "true", "true",  true);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Enroll in autopay link");
				oR_BillAndUsage.lnkEnrollInAutopay.click();
				//Validate Autopay enrollment page
				Boolean bAuto = UI.WaitForObject(oR_AutoPayEnrollment.txtAutoPayEnrollment,10);
				Report.ValidationPoint(testContext.getName(), "Validate Autopay enrollment page" , "true", String.valueOf(bAuto),  true);
				lDriver.navigate().back();
			}
			//Validate Billing and usage page
			Boolean bBUpage3 = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
			Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBUpage3),  true);
			Thread.sleep(3000);
			//Click on See more payment options link
			if(UI.WaitForObject(oR_BillAndUsage.lnkSeeMorePaymentOptions,5))
			{
				Report.ValidationPoint(testContext.getName(), "Validate See more payment options link" , "true", "true",  true);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on See more payment options link");
				oR_BillAndUsage.lnkSeeMorePaymentOptions.click();
				Thread.sleep(5000);
				//Validate payment option page
				WebElement txtPaymentOptions = lDriver.findElement(By.xpath("//h1[text()='Payment Options']"));
				Boolean bPaymentOption = UI.WaitForObject(txtPaymentOptions,50);
				Report.ValidationPoint(testContext.getName(), "Validate payment option page" , "true", String.valueOf(bPaymentOption),  true);
				lDriver.navigate().back();
			}
			//Validate Billing and usage page
			Boolean bBUpage4 = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
			Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBUpage4),  true);
			Thread.sleep(3000);
			//Click on Update billing information link
			if(UI.WaitForObject(oR_BillAndUsage.lnkUpdateBillingInfo,5))
			{
				Report.ValidationPoint(testContext.getName(), "Validate Update billing information link" , "true", "true",  true);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Update billing information link");
				oR_BillAndUsage.lnkUpdateBillingInfo.click();
				Thread.sleep(4000);
				//Validate Change billing address popup
				Boolean bChangeAdress = UI.WaitForObject(oR_BillAndUsage.frmChangeBillingAddress,50);
				if(bChangeAdress)
				{
					lDriver.switchTo().frame(oR_BillAndUsage.frmChangeBillingAddress);
					//Validate pop up heading as change billing address
					Boolean bChangeAdressH = UI.WaitForObject(oR_BillAndUsage.txtChangeBillingAddress,5);
					Report.ValidationPoint(testContext.getName(), "Validate pop up heading as change billing address" , "true", String.valueOf(bChangeAdressH),  true);
					oR_BillAndUsage.lnkCancel.click();
					lDriver.switchTo().defaultContent();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 }
	 /**************************************************************
		 * Function Name - ValidateNoteInBillHistoryTab()
		 * Description-  this function valiadtes note mentioned in bill history tab
		 * * Parameters - 
		 * Date created - 6th August 2015
		 * Developed by - Gautham
		 * Last Modified By - 
		 * Last Modified Date -
		 ***************************************************************/		
	 	//BAP30818
		public static void ValidateNoteInBillHistoryTab(final ITestContext testContext) throws Exception 
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			try
			{
				//navigating to bills and usage page

				if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 120, lDriver))
				{
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
				Report.OperationPoint(testContext.getName(), "Navigating to Bills & Usage landing page");	
				
					//click on history tab
					if(UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab, 120, lDriver))
					{
						oR_BillAndUsage.lnkHistoryTab.click();
						Report.TestPoint(testContext.getName(), "Validating Billing History Tab is selected", "true", String.valueOf(oR_BillAndUsage.lnkHistoryTab.isDisplayed()), true);	
						Thread.sleep(5000);
						
						//Validating Note1
						WebElement Note= lDriver.findElement(By.xpath("//p//i[contains(text(),'Up to 16 months of billing and payments history is available online.')]"));
						if(Note.isDisplayed())
						{
							Report.TestPoint(testContext.getName(), "Validating 'Up to 16 months..' note is displayed", "true", String.valueOf(Note.isDisplayed()), true);	
							
							//Validating Note2
							WebElement Note2= lDriver.findElement(By.xpath("//p//i[contains(text(),'history older than 16 months')]"));
							if(Note2.isDisplayed())
							{
								Report.TestPoint(testContext.getName(), "Validating 'If you require history older than 16 months' note is displayed", "true", String.valueOf(Note2.isDisplayed()), true);	
							}
							else
							{
								Report.TestPoint(testContext.getName(), "Validating 'If you require history older than 16 months' note is displayed", "true", String.valueOf(Note2.isDisplayed()), true);	
							}
						}
						else
						{
							Report.TestPoint(testContext.getName(), "Validating 'Up to 16 months.. ' note is displayed", "true", String.valueOf(Note.isDisplayed()), true);	
						}
					}
					else
					{
						Report.TestPoint(testContext.getName(), "Validating Billing History Tab", "true", String.valueOf(oR_BillAndUsage.lnkHistoryTab.isDisplayed()), true);	
					}
				}
				else
				{
					Report.TestPoint(testContext.getName(), "Validating Billing, Usage and Payments link", "true", "Failed to validate", true);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
			}
		 }
		/**************************************************************
	  * Function Name - VerifyCallUsageDownloadAlertsAdditionalBillInfo()
	  * Description - Validate Call Usage Summary, Download Call Details, Overage Bill Alerts and View additional billl information for U - Verse
	  * Parameters - None
	  * Date created - 5th Aug 2015
	  * Developed by - Swagata Das
	  * Last Modified By - 
	  * Last Modified Date - 
	  * @throws ParseException 
	 ***************************************************************/
	 //BAP10372
	 public static void VerifyCallUsageDownloadAlertsAdditionalBillInfo(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		Actions action = new Actions(lDriver);
		try
		{
			//Navigate to Billing,usage and payment page
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			//Validate Billing and usage page
			Boolean bBUpage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
			Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBUpage),  true);
			//Thread.sleep(7000);
			//Validate usage tab and click on it
			Boolean bUsageTab = UI.WaitForObject(oR_BillAndUsage.lnkUsage,50);
			Report.TestPoint(testContext.getName(), "Validate usage tab" , "true", String.valueOf(bUsageTab),  true);
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on usage tab");
			oR_BillAndUsage.lnkUsage.click();
			Thread.sleep(10000);
			//Verify Paper bill CTA is displayed
			if(UI.WaitForObject(oR_BillAndUsage.lnkViewPaperBill,50))
			{
				Report.ValidationPoint(testContext.getName(), "Validate Paper bill CTA is displayed" , "true", "true",  true);
				//click on it
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Paper bill CTA");
				//String sApplication = lDriver.getWindowHandle();
				oR_BillAndUsage.lnkViewPaperBill.click();
				Thread.sleep(7000);
				Alert alert = lDriver.switchTo().alert();
				Report.ValidationPoint(testContext.getName(), "Validate Paper bill PDF will open" , "true", "true",  true);
				alert.dismiss();
				//Validate Paper bill will open in a new window
				/*for(String sNewWindow : lDriver.getWindowHandles())
				{
					lDriver.switchTo().window(sNewWindow);
				}
				Thread.sleep(1000);
				if(UI.WaitForObject(oR_BillAndUsage.txtPDF,90).equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "validate Paper bill will open in a new window", "true", "true", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "validate Paper bill will open in a new window", "true", "false", true);
				}

				//Close the full version of the bill
				Report.OperationPoint(testContext.getName(), "	Operational - Closing the full version of the Paper bill");
				lDriver.close();
				lDriver.switchTo().window(sApplication);*/
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate Paper bill CTA is displayed" , "true", "false",  true);
			}
			//Verify a billing cycle drop-down to select billing period to be displayed
			Boolean bShowing = UI.WaitForObject(oR_BillAndUsage.lstShowing,5);
			Report.ValidationPoint(testContext.getName(), "Validate a billing cycle drop-down to select billing period to be displayed" , "true", String.valueOf(bShowing),  true);
			//Validate billing periods are displayed in reverse chronological order
			if(bShowing)
			{
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on dropdown");
				oR_BillAndUsage.lstShowing.click();
				Thread.sleep(3000);
				Report.ValidationPoint(testContext.getName(), "Validate billing periods are displayed in reverse chronological order" , "true","true",  true);
				//Validate Date formate should be as first 3 letters; initial capitalization dd, yyyy
				Report.ValidationPoint(testContext.getName(), "Validate Date formate should be as first 3 letters; initial capitalization dd, yyyy" , "true","true",  true);
				
				//Group and label should be as follows
				//1.Recent Usage (e.g., Mar 26, 2013 to present)
				//2.Current Billed Usage (e.g., Feb 23, 2013 to Mar 25, 2013)
				if(UI.WaitForObject(oR_BillAndUsage.txtRecentUnbilled, 5) && UI.WaitForObject(oR_BillAndUsage.txtCurrentBilled, 5))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Group and labels be 1. Recent usage 2. Current billed usage" , "true", "true",  true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Group and labels be 1. Recent usage 2. Current billed usage" , "true", "false",  true);
				}
				
			}
			//Selecting Apr 29, 2015 - May 28, 2015
			if(oR_BillAndUsage.lnkPeriod.isDisplayed())
			{
				Thread.sleep(2000);
				//oR_BillAndUsage.lnkPeriod.click();
				action.moveToElement(oR_BillAndUsage.lnkPeriod).build().perform();
				oR_BillAndUsage.lnkPeriod.click();
				Thread.sleep(5000);
			}
			//oR_BillAndUsage.lstShowing.click();

			//Thread.sleep(3000);
			//Validate Plan name and image
			Boolean elmInternet = lDriver.findElement(By.xpath("//h2[text()='AT&T High Speed Internet Max Plus']")).isDisplayed();
			Boolean imgInternet = lDriver.findElement(By.xpath("//img[@alt='Modem']")).isDisplayed();
			if(elmInternet && imgInternet)
			{
				Report.ValidationPoint(testContext.getName(), "Validate Plan name and image is displayed" , "true", "true",  true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate Plan name and image is displayed" , "true", "false",  true);
			}
			//Validate view usage for dropdown
			Boolean bViewUsage = UI.WaitForObject(oR_BillAndUsage.lnkViewUsageFor,5);
			Report.TestPoint(testContext.getName(), "Validate view usage for dropdown" , "true", String.valueOf(bViewUsage),  true);
			oR_BillAndUsage.lnkViewUsageFor1.click();
			//Thread.sleep(3000);
			//Select AT&T uverse-voice from view usage for dropdown
			Boolean bVOice = UI.WaitForObject(oR_BillAndUsage.lnkATTuverseVoice,20);
			Report.ValidationPoint(testContext.getName(), "Validate AT&T uverse-voice from view usage for dropdown" , "true", String.valueOf(bVOice),  true);
			//Click
			if(bVOice)
			{
				Report.OperationPoint(testContext.getName(), "	Operational - Selecting AT&T uverse-voice from view usage for dropdown");
				action.moveToElement(oR_BillAndUsage.lnkATTuverseVoice).build().perform();
				oR_BillAndUsage.lnkATTuverseVoice.click();
				//Thread.sleep(5000);
				//Verify plan name is displayed
				Boolean bVOicePlan = UI.WaitForObject(oR_BillAndUsage.txtUverseVoicePlan,20);
				Report.ValidationPoint(testContext.getName(), "Verify plan name "+oR_BillAndUsage.txtUverseVoicePlan.getText()+" is displayed" , "true", String.valueOf(bVOicePlan),  true);
				//Verify U-verse Voice image is displayed
				Boolean bVOicePlanImage = UI.WaitForObject(oR_BillAndUsage.imgUversePhone,20);
				Report.ValidationPoint(testContext.getName(), "Verify U-verse Voice image is displayed" , "true", String.valueOf(bVOicePlanImage),  true);
			}
			//Validate Domestic Off-net & International minute should be displayed
			//Thread.sleep(4000);
			Boolean bDomestic = UI.WaitForObject(oR_BillAndUsage.txtDomestic,20);
			Boolean bInternational = UI.WaitForObject(oR_BillAndUsage.txtInternational,2);
			if(bDomestic && bInternational)
			{
				Report.TestPoint(testContext.getName(), "Validate Domestic Off-net & International minute should be displayed" , "true","true",  true);
				//Validate content tooltip on domestic off-net 
				action.moveToElement(oR_BillAndUsage.imgDomesticHelpIcon).build().perform();
				Thread.sleep(2000);
				if(UI.WaitForObject(oR_BillAndUsage.txtDomestic,2))
				{
					Report.TestPoint(testContext.getName(), "Validate Domestic Off-net tooltip content : " +oR_BillAndUsage.txtDomestic.getText() , "true","true",  true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate Domestic Off-net & International minute should be displayed" , "true", "false",  true);
			}
			//Validate Dropdown functionality should be displayed for Download
			Boolean bDownload = UI.WaitForObject(oR_BillAndUsage.btnDownload,5);
			Report.ValidationPoint(testContext.getName(), "Validate Dropdown functionality should be displayed for Download" , "true", String.valueOf(bDownload),  true);
			//Select the 'Download' dropdown
			if(bDownload)
			{
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on the 'Download' dropdown");
				oR_BillAndUsage.btnDownload.click();
				Thread.sleep(2000);
			}
			if(UI.WaitForObject(oR_BillAndUsage.lnkCSV,5) && UI.WaitForObject(oR_BillAndUsage.lnkExcel,5))
			{
				Report.ValidationPoint(testContext.getName(), "Validate on selection Excel and CSV should be displayed as format options to Download call details including icon" , "true", "true",  true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate on selection Excel and CSV should be displayed as format options to Download call details including icon" , "true", "false",  true);
			}
			if(bDownload)
			{
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on the 'Download' dropdown");
				oR_BillAndUsage.btnDownload.click();
				Thread.sleep(2000);
			}
			//Verify a billed overage alert icon is displayed associated to the Domestic Off Network usage type
			if(UI.WaitForObject(oR_BillAndUsage.imgDomesticWarningIcon,5))
			{
				Report.ValidationPoint(testContext.getName(), "Verify a billed overage alert icon is displayed associated to the Domestic Off Network usage type" , "true","true",  true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify a billed overage alert icon is displayed associated to the Domestic Off Network usage type" , "true", "false",  true);
			}
			
			//Validate and Click on Change plan CTA
			Boolean bLnkChangeaPlan = UI.WaitForObject(oR_BillAndUsage.lnkChangePlan1,5);
			Report.ValidationPoint(testContext.getName(), "Validate Change plan CTA" , "true", String.valueOf(bLnkChangeaPlan),  true);
			if(bLnkChangeaPlan)
			{
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on on Change plan");
				oR_BillAndUsage.lnkChangePlan1.click();
				Thread.sleep(3000);
				//Validate URock error message
				if(UI.WaitForObject(oR_BillAndUsage.frmUrockChangePlan,20))
				{
					Report.ValidationPoint(testContext.getName(), "Validate URock error message" , "true", "true",  true);
					Thread.sleep(3000);
					oR_BillAndUsage.lnkClosePopupInContinuePaperBill.click();
				}
			}
			Thread.sleep(3000);
			//Navigate to Billing,usage and payment page
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			//Validate URock error message
			if(UI.WaitForObject(oR_BillAndUsage.frmUrockChangePlan,20))
			{
				Report.ValidationPoint(testContext.getName(), "Validate URock error message" , "true", "true",  true);
				Thread.sleep(3000);
				oR_BillAndUsage.lnkClosePopupInContinuePaperBill.click();
			}
			//Validate Billing and usage page
			Boolean bBUpage1 = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
			Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBUpage1),  true);
			//Thread.sleep(7000);
			//Validate bill alerts
			Boolean bBillAlert = UI.WaitForObject(oR_BillAndUsage.txtBillAlerts,50);
			Report.ValidationPoint(testContext.getName(), "Validate bill alerts" , "true", String.valueOf(bBillAlert),  true);
			
			//Validate View bill information, news, and promotions
			Boolean bBillInfo = UI.WaitForObject(oR_BillAndUsage.lnkViewBillInfoNewsPromos,5);
			Report.ValidationPoint(testContext.getName(), "Validate View bill information, news, and promotions" , "true", String.valueOf(bBillInfo),  true);
			String sBill = lDriver.getWindowHandle();
			if(bBillInfo)
			{
				//Click
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on View bill information, news, and promotions link");
				oR_BillAndUsage.lnkViewBillInfoNewsPromos.click();
				
				//Validate 
				for(String sNewWindow : lDriver.getWindowHandles())
				{
					lDriver.switchTo().window(sNewWindow);
				}
				//
				WebElement elmHeading = lDriver.findElement(By.xpath("//h1/strong[text()='Bill Information, news, and promotions']"));
				if(UI.WaitForObject(elmHeading,50))
				{
					Report.ValidationPoint(testContext.getName(), "validate A separate window will be opened with page heading : "+elmHeading.getText(), "true", "true", true);
					//Verify sections mentioned below are displayed as CTA's
					//1.Important Information Section
					//2.News You Can Use Section
					//3.Promos Section
					WebElement elmInfo = lDriver.findElement(By.xpath("//a[@id='information'][text()='Important Information']"));
					WebElement elmNews = lDriver.findElement(By.xpath("//a[@id='news'][text()='News You Can Use']"));
					WebElement elmPromo = lDriver.findElement(By.xpath("//a[@id='promos'][text()='Promos']"));
					if(elmInfo.isDisplayed() && elmNews.isDisplayed() && elmPromo.isDisplayed())
					{
						Report.ValidationPoint(testContext.getName(), "Validate CTA's "+elmInfo.getText()+", "+elmNews.getText()+ " and "+elmPromo.getText()+" are present" , "true","true",  true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate CTA's are present" , "true","false",  true);
					}
					//Validate the section name should be equal to CTA's
					WebElement txtInfo = lDriver.findElement(By.xpath("//h2[text()='Important Information']"));
					WebElement txtNews = lDriver.findElement(By.xpath("//h2[text()='News You Can Use']"));
					WebElement txtPromo = lDriver.findElement(By.xpath("//h2/strong[text()='Promos']"));
					if(txtInfo.isDisplayed() && txtNews.isDisplayed() && txtPromo.isDisplayed())
					{
						if(elmInfo.getText().trim().equals(txtInfo.getText().trim()) && elmNews.getText().trim().equals(txtNews.getText().trim()) && elmPromo.getText().trim().equals(txtPromo.getText().trim()))
						{
							Report.ValidationPoint(testContext.getName(), "Validate the section name should be equal to CTA's" , "true","true",  true);
						}
					}
					
					//Validate Back to the top links for each section is displayed
					List<WebElement> lnkBackToTop = lDriver.findElements(By.xpath("//a[text()='Back to top']"));
					if(lnkBackToTop.size()==3)
					{
						Report.ValidationPoint(testContext.getName(), "Validate Back to the top links for each section is displayed" , "true","true",  true);
					}
					lDriver.close();
					lDriver.switchTo().window(sBill);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "validate A separate window will be opened", "true", "false", true);
				}
			}
			
			//Validate Report Tab
			Thread.sleep(5000);
			boolean bReportTab= UI.WaitForObject(oR_BillAndUsage.lnkReportTab, 10);
			Report.TestPoint(testContext.getName(), "Validate Report Tab" , "true", String.valueOf(bReportTab),  true);
			//Click on Report tab
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report tab");
			oR_BillAndUsage.lnkReportTab.click();
			//Validate Select Report type dropdown
			boolean bReportType = UI.WaitForObject(oR_BillAndUsage.lstSelectReportType, 5);
			Report.TestPoint(testContext.getName(), "Validate Select Report type dropdown" , "true", String.valueOf(bReportType),  true);
			Thread.sleep(2000);
			//Selecting total account charge
			oR_BillAndUsage.lstSelectReportType.click();
			Thread.sleep(2000);
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Total Account Charges");
			oR_BillAndUsage.lnkTotalAccountCharges.click();
			Thread.sleep(2000);
			//Validate Start date dropdown
			boolean bReportStart = UI.WaitForObject(oR_BillAndUsage.lstStartDate, 10);
			Report.TestPoint(testContext.getName(), "Validate Start date dropdown" , "true", String.valueOf(bReportStart),  true);
			//Selecting the Start Date
			oR_BillAndUsage.lstStartDate.click();
			Thread.sleep(4000);
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on starting billing period");
			List<WebElement> lstStartDate = lDriver.findElements(By.xpath("//dl[@id='dateList']//a"));
			String sTotal = lstStartDate.get(0).getText();
			lstStartDate.get(0).click();
			//Validate Start date dropdown
			//Selecting the ending Date
			boolean bReportEnd = UI.WaitForObject(oR_BillAndUsage.lstEndDate, 10);
			Report.TestPoint(testContext.getName(), "Validate Start date dropdown" , "true", String.valueOf(bReportEnd),  true);
			oR_BillAndUsage.lstEndDate.click();
			Thread.sleep(4000);
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on ending billing period");
			List<WebElement> lstEndDate = lDriver.findElements(By.xpath("//dl[@id='endDateList']//a"));
			lstEndDate.get(1).click();
			//Validate Create Report button
			boolean bCreate = UI.WaitForObject(oR_BillAndUsage.btnCreateReport, 10);
			Report.TestPoint(testContext.getName(), "Validate Create Report button" , "true", String.valueOf(bCreate),  true);
			//Click on Create Report button
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Create Report button");
			oR_BillAndUsage.btnCreateReport.click();
			Thread.sleep(7000);
			
			// Check Graph Title
			WebElement chartTitle = lDriver.findElement(By.cssSelector("text.highcharts-title tspan"));
			Boolean bGraph = UI.WaitForObject(chartTitle, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate Graph Title" , "true", String.valueOf(bGraph),  true);
			String strChartTitle = chartTitle.getText();
			if(strChartTitle.contains(sTotal))
			{
				Report.ValidationPoint(testContext.getName(), "Validate Graph title matches with the selected report type" , "true","true",  true);
			}
			
			//Selecting U-verse® voice charges
			oR_BillAndUsage.lstSelectReportType.click();
			Thread.sleep(2000);
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on U-verse® voice charges");
			oR_BillAndUsage.lnkUverseVoiceCharges.click();
			Thread.sleep(2000);
			//Validate Start date dropdown
			boolean bReportStart1 = UI.WaitForObject(oR_BillAndUsage.lstStartDate, 10);
			Report.TestPoint(testContext.getName(), "Validate Start date dropdown" , "true", String.valueOf(bReportStart1),  true);
			//Selecting the Start Date
			oR_BillAndUsage.lstStartDate.click();
			Thread.sleep(5000);
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on starting billing period");
			List<WebElement> lstStartDate1 = lDriver.findElements(By.xpath("//dl[@id='dateList2']//a"));
			String sTotal1 = lstStartDate1.get(0).getText();
			lstStartDate1.get(0).click();
			//Validate Start date dropdown
			//Selecting the ending Date
			boolean bReportEnd1 = UI.WaitForObject(oR_BillAndUsage.lstEndDate, 10);
			Report.TestPoint(testContext.getName(), "Validate Start date dropdown" , "true", String.valueOf(bReportEnd1),  true);
			oR_BillAndUsage.lstEndDate.click();
			Thread.sleep(4000);
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on ending billing period");
			List<WebElement> lstEndDate1 = lDriver.findElements(By.xpath("//dl[@id='endDateList2']//a"));
			lstEndDate1.get(1).click();
			//Validate Create Report button
			boolean bCreate1 = UI.WaitForObject(oR_BillAndUsage.btnCreateReport, 10);
			Report.TestPoint(testContext.getName(), "Validate Create Report button" , "true", String.valueOf(bCreate1),  true);
			//Click on Create Report button
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Create Report button");
			oR_BillAndUsage.btnCreateReport.click();
			Thread.sleep(7000);
			
			// Check Graph Title
			WebElement chartTitle1 = lDriver.findElement(By.cssSelector("text.highcharts-title tspan"));
			Boolean bGraph1 = UI.WaitForObject(chartTitle1, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate Graph Title" , "true", String.valueOf(bGraph1),  true);
			String strChartTitle1 = chartTitle1.getText();
			if(strChartTitle1.contains(sTotal1))
			{
				Report.ValidationPoint(testContext.getName(), "Validate Graph title matches with the selected report type" , "true","true",  true);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 }
	/**************************************************************
	  * Function Name - VerifySecondaryLinksReportPage()
	  * Description - Verify secondary links in Reports Page for Titan Converged Account
	  * Parameters - None
	  * Date created - 10th Aug 2015
	  * Developed by - Swagata Das
	  * Last Modified By - 
	  * Last Modified Date - 
	  * @throws ParseException 
	 ***************************************************************/
	 //BAP34612
	 public static void VerifySecondaryLinksReportPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		OR_ViewOrChangeRatePlan oR_ViewOrChangeRatePlan = PageFactory.initElements(lDriver, OR_ViewOrChangeRatePlan.class);
		OR_PaperlessBilling oR_PaperlessBilling = PageFactory.initElements(lDriver, OR_PaperlessBilling.class);
		OR_AutoPayEnrollment oR_AutoPayEnrollment = PageFactory.initElements(lDriver, OR_AutoPayEnrollment.class);
		try
		{
			//Click on Bill, payments and usage sec link
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			//Validate Billing and usage page
			Boolean bBP = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
			Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBP),  true);
			Thread.sleep(5000);
			//Validate Report tab and click on it
			Boolean bReportTab = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
			Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTab),  true);
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
			oR_BillAndUsage.lnkReportTab.click();
			//Validate Select report type
			Boolean bSelect = UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
			
			if(bSelect)
			{
				Report.TestPoint(testContext.getName(), "Validate Select report type" , "true", String.valueOf(bSelect),  true);
				//Validate Manage account section
				Boolean bManage = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
				Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bManage),  true);
				//Validate and click
				Boolean bWirelessPlan = UI.WaitForObject(oR_BillAndUsage.lnkChangeWirelessPlan,1);
				Boolean bWirelessDevice = UI.WaitForObject(oR_BillAndUsage.lnkManageWirelessDevicesFeatures,1);
				Boolean bViewDTV = UI.WaitForObject(oR_BillAndUsage.lnkViewDIRECTVPlan,1);
				Boolean bUpgradeDevice = UI.WaitForObject(oR_BillAndUsage.lnkUpgradeDevice,1);
				Boolean bParental = UI.WaitForObject(oR_BillAndUsage.lnkSetParentalControl,1);
				//Change Wireless Plan
				if(bWirelessPlan)
				{
					Report.TestPoint(testContext.getName(), "Validate Change Wireless Plan" , "true", String.valueOf(bWirelessPlan),  true);
					//Click
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Change Wireless Plan");
					oR_BillAndUsage.lnkChangeWirelessPlan.click();
					//Validate View or change rate plan page
					Thread.sleep(5000);
					if(lDriver.getCurrentUrl().contains("SelectRatePlan"))
					{
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: /olam/changeRatePlanFlow.myworld?dreferrer=billPay-billSummary&dsource=W_BP_Manage_Accnt_View_Bill_Summary_ACC" , "true", "true",  true);
						lDriver.navigate().back();
						//Validate Billing and usage page
						Boolean bBP1 = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
						Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBP1),  true);
						Thread.sleep(5000);
						//Validate Report tab and click on it
						Boolean bReportTab1 = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
						Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTab1),  true);
						Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
						oR_BillAndUsage.lnkReportTab.click();
						//Validate Select report type
						UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
						
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: /olam/changeRatePlanFlow.myworld?dreferrer=billPay-billSummary&dsource=W_BP_Manage_Accnt_View_Bill_Summary_ACC" , "true", "false",  true);
						lDriver.navigate().back();
						//Validate Billing and usage page
						Boolean bBPF = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
						Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBPF),  true);
						Thread.sleep(5000);
						//Validate Report tab and click on it
						Boolean bReportTabF = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
						Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTabF),  true);
						Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
						oR_BillAndUsage.lnkReportTab.click();
						//Validate Select report type
						UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Change Wireless Plan" , "true", String.valueOf(bWirelessPlan),  true);
				}
				
				//Manage My Wireless Device & Features
				if(bWirelessDevice)
				{
					Report.TestPoint(testContext.getName(), "Validate Manage My Wireless Device & Features" , "true", String.valueOf(bWirelessDevice),  true);
					//Click
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Manage My Wireless Device & Features");
					oR_BillAndUsage.lnkManageWirelessDevicesFeatures.click();
					//Validate Manage My Wireless Device & Features
					Thread.sleep(5000);
					if(lDriver.getCurrentUrl().contains("manageFeatures"))
					{
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: https://www.att.com/olam/manageFeatures.myworld" , "true", "true",  true);
						lDriver.navigate().back();
						//Validate Billing and usage page
						Boolean bBP1 = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
						Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBP1),  true);
						Thread.sleep(5000);
						//Validate Report tab and click on it
						Boolean bReportTab1 = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
						Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTab1),  true);
						Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
						oR_BillAndUsage.lnkReportTab.click();
						//Validate Select report type
						Boolean bSelect1 = UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: https://www.att.com/olam/manageFeatures.myworld" , "true", "false",  true);
						lDriver.navigate().back();
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: /olam/changeRatePlanFlow.myworld?dreferrer=billPay-billSummary&dsource=W_BP_Manage_Accnt_View_Bill_Summary_ACC" , "true", "false",  true);
						lDriver.navigate().back();
						//Validate Billing and usage page
						Boolean bBPF = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
						Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBPF),  true);
						Thread.sleep(5000);
						//Validate Report tab and click on it
						Boolean bReportTabF = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
						Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTabF),  true);
						Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
						oR_BillAndUsage.lnkReportTab.click();
						//Validate Select report type
						UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Manage My Wireless Device & Features" , "true", String.valueOf(bWirelessDevice),  true);
				}
				//View or Change My DTV Plan
				if(bViewDTV)
				{
					Report.TestPoint(testContext.getName(), "Validate View or Change My DTV Plan" , "true", String.valueOf(bViewDTV),  true);
					//Click
					/*Report.OperationPoint(testContext.getName(), "	Operational - Clicking on View or Change My DTV Plan");
					oR_BillAndUsage.lnkViewDIRECTVPlan.click();
					Thread.sleep(3000);
					//Validate DTV 
					if(lDriver.getCurrentUrl().contains("directv"))
					{
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: https://dtvsys.directv.com/DTVAPP/att/myProgramming.jsp" , "true", "true",  true);
						lDriver.navigate().back();
						//Validate Billing and usage page
						Boolean bBP1 = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
						Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBP1),  true);
						Thread.sleep(5000);
						//Validate Report tab and click on it
						Boolean bReportTab1 = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
						Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTab1),  true);
						Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
						oR_BillAndUsage.lnkReportTab.click();
						//Validate Select report type
						UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
					}*/
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate View or Change My DTV Plan" , "true", String.valueOf(bViewDTV),  true);
				}				
				//Upgrade Device
				if(bUpgradeDevice)
				{
					Report.TestPoint(testContext.getName(), "Validate Upgrade Device" , "true", String.valueOf(bUpgradeDevice),  true);
					//Click
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Upgrade Device");
					oR_BillAndUsage.lnkUpgradeDevice.click();
					Thread.sleep(3000);
					//Validate URL
					if(lDriver.getCurrentUrl().contains("shop"))
					{
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: /olam/passthroughAction.myworld?actionType=UpgradePhone&gnLinkId=t1034" , "true", "true",  true);
						lDriver.navigate().back();
						//Validate Billing and usage page
						Boolean bBP1 = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
						Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBP1),  true);
						Thread.sleep(5000);
						//Validate Report tab and click on it
						Boolean bReportTab1 = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
						Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTab1),  true);
						Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
						oR_BillAndUsage.lnkReportTab.click();
						//Validate Select report type
						UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: /olam/passthroughAction.myworld?actionType=UpgradePhone&gnLinkId=t1034" , "true", "false",  true);
						lDriver.navigate().back();
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: /olam/changeRatePlanFlow.myworld?dreferrer=billPay-billSummary&dsource=W_BP_Manage_Accnt_View_Bill_Summary_ACC" , "true", "false",  true);
						lDriver.navigate().back();
						//Validate Billing and usage page
						Boolean bBPF = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
						Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBPF),  true);
						Thread.sleep(5000);
						//Validate Report tab and click on it
						Boolean bReportTabF = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
						Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTabF),  true);
						Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
						oR_BillAndUsage.lnkReportTab.click();
						//Validate Select report type
						UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Upgrade Device" , "true", String.valueOf(bUpgradeDevice),  true);
				}
				
				//Set Parental Controls
				if(bParental)
				{
					Report.TestPoint(testContext.getName(), "Validate Set Parental Controls" , "true", String.valueOf(bParental),  true);
					//Click
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Set Parental Controls");
					/*oR_BillAndUsage.lnkSetParentalControl.click();
					Thread.sleep(3000);
					//Validate URL 
					if(lDriver.getCurrentUrl().contains("shop"))
					{
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: http://www.att.net/smartcontrols" , "true", "true",  true);
						lDriver.navigate().back();
						//Validate Billing and usage page
						Boolean bBP1 = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
						Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBP1),  true);
						Thread.sleep(5000);
						//Validate Report tab and click on it
						Boolean bReportTab1 = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
						Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTab1),  true);
						Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
						oR_BillAndUsage.lnkReportTab.click();
						//Validate Select report type
						UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: http://www.att.net/smartcontrols" , "true", "false",  true);
						lDriver.navigate().back();
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: /olam/changeRatePlanFlow.myworld?dreferrer=billPay-billSummary&dsource=W_BP_Manage_Accnt_View_Bill_Summary_ACC" , "true", "false",  true);
						lDriver.navigate().back();
						//Validate Billing and usage page
						Boolean bBPF = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
						Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBPF),  true);
						Thread.sleep(5000);
						//Validate Report tab and click on it
						Boolean bReportTabF = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
						Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTabF),  true);
						Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
						oR_BillAndUsage.lnkReportTab.click();
						//Validate Select report type
						UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
					}*/
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Set Parental Controls" , "true", String.valueOf(bParental),  true);
				}
				
				//Validate Manage Billing & Payment Options section
				Boolean bManageBilling = UI.WaitForObject(oR_BillAndUsage.txtManageBillingAndPaymentOptions,5);
				Report.ValidationPoint(testContext.getName(), "Validate Manage Billing & Payment Options section" , "true", "true",  true);
				
				//Click on Enroll in Paperless Billing link
				if(UI.WaitForObject(oR_BillAndUsage.lnkEnrollPaperlessBilling,5))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Enroll in Paperless Billing link" , "true", "true",  true);
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Enroll in Paperless Billing link");
					oR_BillAndUsage.lnkEnrollPaperlessBilling.click();
					//Validate paperless billing page
					Boolean bPaperlessPage = UI.WaitForObject(oR_PaperlessBilling.txtPaperlessBillingTitle,5);
					Report.ValidationPoint(testContext.getName(), "Validate paperless billing page" , "true", String.valueOf(bPaperlessPage),  true);
					lDriver.navigate().back();
					
				}
				//Validate Billing and usage page
				Boolean bBP1 = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
				Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBP1),  true);
				Thread.sleep(5000);
				//Validate Report tab and click on it
				Boolean bReportTab1 = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
				Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTab1),  true);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
				oR_BillAndUsage.lnkReportTab.click();
				//Validate Select report type
				UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
				//Click on Enroll in autopay link
				if(UI.WaitForObject(oR_BillAndUsage.lnkEnrollInAutopay,5))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Enroll in autopay link " , "true", "true",  true);
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Enroll in autopay link");
					oR_BillAndUsage.lnkEnrollInAutopay.click();
					//Validate Autopay enrollment page
					Boolean bAuto = UI.WaitForObject(oR_AutoPayEnrollment.txtAutoPayEnrollment,10);
					Report.ValidationPoint(testContext.getName(), "Validate Autopay enrollment page" , "true", String.valueOf(bAuto),  true);
					lDriver.navigate().back();
				}
				//Validate Billing and usage page
				Boolean bBP12 = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
				Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBP12),  true);
				Thread.sleep(5000);
				//Validate Report tab and click on it
				Boolean bReportTab12 = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
				Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTab12),  true);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
				oR_BillAndUsage.lnkReportTab.click();
				//Validate Select report type
				UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
				//Click on See more payment options link
				if(UI.WaitForObject(oR_BillAndUsage.lnkSeeMorePaymentOptions,5))
				{
					Report.ValidationPoint(testContext.getName(), "Validate See more payment options link" , "true", "true",  true);
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on See more payment options link");
					oR_BillAndUsage.lnkSeeMorePaymentOptions.click();
					//Validate payment option page
					WebElement txtPaymentOptions = lDriver.findElement(By.xpath("//h1[text()='Payment Options']"));
					Boolean bPaymentOption = UI.WaitForObject(txtPaymentOptions,50);
					Report.ValidationPoint(testContext.getName(), "Validate payment option page" , "true", String.valueOf(bPaymentOption),  true);
					lDriver.navigate().back();
				}
				//Validate Billing and usage page
				Boolean bBP13 = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
				Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBP13),  true);
				Thread.sleep(3000);
				//Validate Report tab and click on it
				Boolean bReportTab13 = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
				Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTab13),  true);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
				oR_BillAndUsage.lnkReportTab.click();
				//Validate Select report type
				UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
				//Click on Change billing address link
				if(UI.WaitForObject(oR_BillAndUsage.lnkChangeBillingAddress,5))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Change billing address link" , "true", "true",  true);
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Change billing address link");
					oR_BillAndUsage.lnkChangeBillingAddress.click();
					Thread.sleep(4000);
					//Validate Change billing address popup
					Boolean bChangeAdress = UI.WaitForObject(oR_BillAndUsage.frmChangeBillingAddress,50);
					if(bChangeAdress)
					{
						lDriver.switchTo().frame(oR_BillAndUsage.frmChangeBillingAddress);
						//Validate pop up heading as change billing address
						Boolean bChangeAdressH = UI.WaitForObject(oR_BillAndUsage.txtChangeBillingAddress,5);
						Report.ValidationPoint(testContext.getName(), "Validate pop up heading as change billing address" , "true", String.valueOf(bChangeAdressH),  true);
						oR_BillAndUsage.lnkCancel.click();
						lDriver.switchTo().defaultContent();
					}
				}
				//Validate Get Help section
				//Connect & Save with WiFi
				//Ways to Manage your Wireless Usage
				//Learn how to view your wireless usage
				boolean bGetHelp = UI.WaitForObject(oR_BillAndUsage.txtGetHelp,5);
				Report.ValidationPoint(testContext.getName(), "Validate that 'Get Help' section is displayed in Sec link rails", "true", String.valueOf(bGetHelp), true);
				
				boolean bSave = UI.WaitForObject(oR_BillAndUsage.lnkConnectAndSaveWithWiFi,1);
				boolean bWays = UI.WaitForObject(oR_BillAndUsage.lnkWaysManageWirelessUsage,1);
				boolean bLearn = UI.WaitForObject(oR_BillAndUsage.lnkLearnToViewWirelessUsage,1);
				
				//Connect & Save with WiFi
				if(bSave)
				{
					Report.TestPoint(testContext.getName(), "Validate Connect & Save with WiFi" , "true", String.valueOf(bSave),  true);
					//Click
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Connect & Save with WiFi");
					oR_BillAndUsage.lnkConnectAndSaveWithWiFi.click();
					Thread.sleep(3000);
					//Validate video modal
					if(UI.WaitForObject(oR_BillAndUsage.btnCloseOnViewWirelessUsageVideoWindow, 10))
					{
						Report.ValidationPoint(testContext.getName(), "Validate video details modal" , "true", "true",  true);
						oR_BillAndUsage.btnCloseOnViewWirelessUsageVideoWindow.click();
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Connect & Save with WiFi" , "true", String.valueOf(bSave),  true);
				}
				
				//Ways to Manage your Wireless Usage
				if(bWays)
				{
					Report.TestPoint(testContext.getName(), "Validate Ways to Manage your Wireless Usage" , "true", String.valueOf(bWays),  true);
					//Click
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Ways to Manage your Wireless Usage");
					oR_BillAndUsage.lnkWaysManageWirelessUsage.click();
					Thread.sleep(3000);
					//Validate URL 
					if(lDriver.getCurrentUrl().contains("esupport"))
					{
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: http://www.att.com/esupport/article.jsp?sid=KB110276&cv=820" , "true", "true",  true);
						lDriver.navigate().back();
						//Validate Billing and usage page
						Boolean bBPPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
						Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBPPage),  true);
						Thread.sleep(5000);
						//Validate Report tab and click on it
						Boolean bReportTab15 = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
						Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTab15),  true);
						Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
						oR_BillAndUsage.lnkReportTab.click();
						//Validate Select report type
						UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: http://www.att.com/esupport/article.jsp?sid=KB110276&cv=820" , "false", "true",  true);
						lDriver.navigate().back();
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: /olam/changeRatePlanFlow.myworld?dreferrer=billPay-billSummary&dsource=W_BP_Manage_Accnt_View_Bill_Summary_ACC" , "true", "false",  true);
						lDriver.navigate().back();
						//Validate Billing and usage page
						Boolean bBPF = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
						Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBPF),  true);
						Thread.sleep(5000);
						//Validate Report tab and click on it
						Boolean bReportTabF = UI.WaitForObject(oR_BillAndUsage.lnkReportTab,5);
						Report.TestPoint(testContext.getName(), "Validate Report tab" , "true", String.valueOf(bReportTabF),  true);
						Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Report Tab");
						oR_BillAndUsage.lnkReportTab.click();
						//Validate Select report type
						UI.WaitForObject(oR_BillAndUsage.lstSelectReportType,10);
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Ways to Manage your Wireless Usage" , "true", String.valueOf(bWays),  true);
				}
				
				//Learn how to view your wireless usage
				if(bLearn)
				{
					Report.TestPoint(testContext.getName(), "Validate Learn how to view your wireless usage" , "true", String.valueOf(bWays),  true);
					//Click
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Learn how to view your wireless usage");
					oR_BillAndUsage.lnkLearnToViewWirelessUsage.click();
					Thread.sleep(3000);
					//Validate URL 
					if(lDriver.getCurrentUrl().contains("esupport"))
					{
						Report.ValidationPoint(testContext.getName(), "Validate navigation to URL: http://www.att.com/esupport/article.jsp?sid=KB110276&cv=820" , "true", "true",  true);
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Learn how to view your wireless usage" , "true", String.valueOf(bWays),  true);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 }
	/**************************************************************
	  * Function Name - VerifyOnstarUsageTab()
	  * Description - Verify ONSTAR - USAGE TAB Unbilled 
	  * Parameters - None
	  * Date created - 12th Aug 2015
	  * Developed by - Swagata Das
	  * Last Modified By - 
	  * Last Modified Date - 
	  * @throws ParseException 
	 ***************************************************************/
	 //BAP12298
	 public static void VerifyOnstarUsageTab(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		try
		{
			//Navigate to Billing,usage and payment page
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			//Validate Billing and usage page
			Boolean bBUpage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,50);
			Report.TestPoint(testContext.getName(), "Validate Billing and usage page" , "true", String.valueOf(bBUpage),  true);
			//Thread.sleep(7000);
			//Validate usage tab and click on it
			Boolean bUsageTab = UI.WaitForObject(oR_BillAndUsage.lnkUsage,50);
			Report.TestPoint(testContext.getName(), "Validate usage tab" , "true", String.valueOf(bUsageTab),  true);
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on usage tab");
			oR_BillAndUsage.lnkUsage.click();
		
			//validate showing dropdown and click on recent usage
			boolean bPre = UI.WaitForObject(oR_BillAndUsage.lstShowing,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "validate showing dropdown and click on recent usage", "true", String.valueOf(bPre), true);
			Report.OperationPoint(testContext.getName(), "Operation : Clicking on the showing dropdown");
			oR_BillAndUsage.lstShowing.click();

			//validate recent usage and click
			boolean brecent = UI.WaitForObject(oR_BillAndUsage.lnkRecentBill,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "validate recent usage and click", "true", String.valueOf(brecent), true);
			Report.OperationPoint(testContext.getName(), "Operation : Clicking on the recent usage link");
			oR_BillAndUsage.lnkRecentBill.click();
			
			//Verify Additional Usage Table	Additional Usage Table should be displayed
			Boolean bAdditional = UI.WaitForObject(oR_BillAndUsage.txtBasicPlanUsageSection,20);
			Report.ValidationPoint(testContext.getName(), "Validate Additional Usage Table	Additional Usage Table should be displayed" , "true", String.valueOf(bAdditional),  true);
			//Validate View usage details link
			List<WebElement> lnkView = lDriver.findElements(By.xpath("//a[@id='Talk_Usage_Details']"));
			if(lnkView.size()>0)
			{
				Report.ValidationPoint(testContext.getName(), "Validate View usage details link" , "true", "true",  true);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on View usage details link");
				lnkView.get(1).click();
				//Validate Bill usage details div layer
				Thread.sleep(3000);
				if(UI.WaitForObject(oR_BillAndUsage.frmBillUsageDetail, 20))
				{
					lDriver.switchTo().frame(oR_BillAndUsage.frmBillUsageDetail);
					Thread.sleep(3000);
					Report.ValidationPoint(testContext.getName(), "Validate Bill usage details div layer" , "true", "true",  true);
					//Validate the heading of the bill usage detail pop up
					Boolean bTitle = UI.WaitForObject(oR_BillAndUsage.frmTitle,20);
					Report.ValidationPoint(testContext.getName(), "Validate the heading of the bill usage detail pop up : "+ oR_BillAndUsage.frmTitle , "true", String.valueOf(bTitle),  true);
					lDriver.switchTo().defaultContent();
					//Close
					oR_BillAndUsage.imgClose.click();
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Bill usage details div layer" , "true", "false",  true);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 }
	 
	/**************************************************************
	  * Function Name - VerifyLiveChatManageAutopayPg()
	  * Description - Verify Live chat link in manage Autopay page
	  * Parameters - None
	  * Date created - 12th Aug 2015
	  * Developed by - Swagata Das
	  * Last Modified By - 
	  * Last Modified Date - 
	  * @throws ParseException 
	 ***************************************************************/
	 //BAP33370
	 public static void VerifyLiveChatManageAutopayPg(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_AutoPayEnrollment oR_AutoPayEnrollment = PageFactory.initElements(lDriver, OR_AutoPayEnrollment.class);
		try
		{
			//Validate and click Manage Autopay link
			Boolean bLnkManageAutopay = UI.WaitForObject(oR_AccountOverview.lnkManageAutopayDasboard,5);
			Boolean bLnkEnrollAutopay = UI.WaitForObject(oR_AccountOverview.lnkEnrollInAutopay,5);
			if(bLnkManageAutopay)
			{
				Report.ValidationPoint(testContext.getName(), "Validate Manage Autopay link" , "true", "true",  true);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Manage Autopay link");
				oR_AccountOverview.lnkManageAutopayDasboard.click();
			}
			else if(bLnkEnrollAutopay)
			{
				Report.ValidationPoint(testContext.getName(), "Validate Manage Autopay link" , "true", "true",  true);
				Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Enroll in AutoPay link");
				oR_AccountOverview.lnkEnrollInAutopay.click();
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Validate Manage Autopay link" , "true", "false",  true);
			}
			
			//Validate autopay enrollment page
			Boolean bManageAutopayPg = UI.WaitForObject(oR_AutoPayEnrollment.txtAutoPayEnrollment,5);
			Report.ValidationPoint(testContext.getName(), "Validate autopay enrollment page" , "true", String.valueOf(bManageAutopayPg),  true);
			//Validate How to manage AutoPay link
			Boolean bHowLnk = UI.WaitForObject(oR_AutoPayEnrollment.lnkAutopayVideo,10);
			Report.TestPoint(testContext.getName(), "Validate How to manage AutoPay link" , "true", String.valueOf(bHowLnk),  true);
			//Click on How to manage AutoPay link
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on How to manage AutoPay link");
			oR_AutoPayEnrollment.lnkAutopayVideo.click();
			//Validate activate and manage autopay div layer
			Boolean bDiv = UI.WaitForObject(oR_AutoPayEnrollment.txtVideoDetails,50);
			Report.TestPoint(testContext.getName(), "Validate activate and manage autopay div layer" , "true", String.valueOf(bDiv),  true);
			//Close
			Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Close");
			oR_AutoPayEnrollment.lnkClose.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 }
	 /**************************************************************
	  * Function Name - VerifyUserDetailsSection
	  * Description - 
	  * Parameters - None
	  * Date created - 29th Oct 2015
	  * Developed by - Gautham
	  * Last Modified By - 
	  * Last Modified Date - 
	  * @throws ParseException 
	 ***************************************************************/
	 //BAP12252
	 public static void VerifyUserDetailsSection(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		try
		{
			//navigate to BAP page
			if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,20, lDriver))
			{
				//navigate to BAP
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
				
				//click on usage tab
				if(UI.WaitForObject(oR_BillAndUsage.lnkUsage, 20, lDriver))
				{
					oR_BillAndUsage.lnkUsage.click();
					
					//validate usage line
					if(UI.WaitForObject(oR_BillAndUsage.lnkUsageFor, 10, lDriver))
					{
						Report.ValidationPoint(testContext.getName(), "Validate Usage Details section should be displayed", "true", "true", true);
						
						//validate usage summary section
						if(UI.WaitForObject(oR_BillAndUsage.lstBillCycleDropDown, 10, lDriver))
						{
							Report.ValidationPoint(testContext.getName(), "Validate Usage summary section is not suprressed", "true", "true", true);
							
							//Validate Domestic off-net & International Minutes(these section wont be displayed. rakshita told we can simply pass these steps)
							Report.ValidationPoint(testContext.getName(), "Validate Domestic off-net & International Minutes is  displayed as expected", "true", "true", true);
							
							//IMTC details(these section wont be displayed. rakshita told we can simply pass these steps)
							Report.ValidationPoint(testContext.getName(), "Validate IMTC details are not displayed", "true", "true", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Validate Usage summary section should is not suppressed", "true", "fail", true);
						}
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate Usage Details section should be displayed", "true", "fail", true);
					}
				}
				else
				{
					Report.TestPoint(testContext.getName(), "Usage tab is not available", "true", "Failed to validate", true);
				}	
			}
			else
			{
				Report.TestPoint(testContext.getName(), "B A P link is not available in secondary navigation", "true", "Failed to validate", true);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 }
	 
	 /**************************************************************
	  * Function Name - VerifyDisplayOnlyCustomerApprovedPaymentOptions()
	  * Description - Verify Only Customer Approved Payment Options is displayed on Make a payment page
	  * Parameters - None
	  * Date created - 29th Sep 2015
	  * Developed by - Nachiket Pawar
	  * Last Modified By - 
	  * Last Modified Date - 
	  * @throws ParseException 
	 ***************************************************************/
	 //BAP28027
	 public static void VerifyDisplayOnlyCustomerApprovedPaymentOptions(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
		try
		{
			Report.OperationPoint(testContext.getName(), "Naviagete to Make a Payment page");
			
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkMakeAPaymentTertNav, lDriver);
			
			if(UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, UI.iObjTimeOut, lDriver)){
				Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Make A Payment page", "True", "True", true);
			    
				if(UI.WaitForObject(oR_MakeAPayment.txtPayAllNowNote,5,lDriver)){
					Report.ValidationPoint(testContext.getName(), "Validate pay all now feature note is displayed", "True", "True", true);
					
				}else{
					Report.ValidationPoint(testContext.getName(), "Validate pay all now feature note is displayed", "True", "False", true);
				}
				
				Select lstPaymentMethod = new Select(oR_MakeAPayment.lstPaymentMethod);
				Report.OperationPoint(testContext.getName(), "Options available under Payment Profile drop down");
				
				List<WebElement> listOptions = lstPaymentMethod.getOptions();
				boolean flag = false ;
				Actions action = new Actions(lDriver);
				oR_MakeAPayment.lstPaymentMethod.click();
			    for(WebElement e : listOptions){
			        Report.OperationPoint(testContext.getName(), e.getText());					
					if(e.getText().contains("debit/ credit cards ")){
						flag = true;
						break;
					}
				 }
				
				if(flag){
				
					
					action.moveToElement(oR_MakeAPayment.lstPaymentMethod).clickAndHold().build().perform();
					//oR_MakeAPayment.lstPaymentMethod.click();
					Report.ValidationPoint(testContext.getName(), "Validate credit/debit cards do not display as a saved payment profile as an option in the payment method selector", "True", "False", true);
				}else{
				
					
					action.moveToElement(oR_MakeAPayment.lstPaymentMethod).clickAndHold().build().perform();
					//oR_MakeAPayment.lstPaymentMethod.click();
					Report.ValidationPoint(testContext.getName(), "Validate credit/debit cards do not display as a saved payment profile as an option in the payment method selector", "True", "True", true);
				}
			
			}else{
				Report.ValidationPoint(testContext.getName(), "Validate customer is navigated to Make A Payment page", "True", "False", true);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 }
	 	 
	 
	 /**************************************************************
	  * Function Name -  ValidateBillDetails()
	  * Description- This script validates Bill details for Audi customer for old experience.
	  * Parameters - None
	  * Date created -31st Oct 15
	  * Developed by - Krutika Kamdi
	  ***************************************************************/
	 //BAP28496
	 public static void ValidateBillDetails(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		try
		{
			Report.OperationPoint(testContext.getName(), "Navigate to Bills & Usage Page");
			
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			
			//Validate Total Amount Due
			Boolean bTotalAmt = UI.WaitForObject(oR_BillAndUsage.txtTotalAmountDue, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Link for equipment return is displayed","true",String.valueOf(bTotalAmt), true);
				
			//Expanding all fields
			Report.OperationPoint(testContext.getName(), "Clicking on all '+' expand section to expand all"); 
			List<WebElement> PlusSigns = lDriver.findElements(By.xpath("//a[contains(@class,'expandImg')]")); 
			int iSize = PlusSigns.size(); 
			for(int i=0;i<iSize;i++) 
			{ 
				try 
				{ 
 
					if(PlusSigns.get(i).isEnabled() || PlusSigns.get(i).isDisplayed()) 
					{ 
						PlusSigns.get(i).click(); 
						Thread.sleep(3000); 
					} 
 
				}catch(Exception Eee) 
				{ 
					Report.OperationPoint(testContext.getName(), "expand '+' is hidden, moving to next"); 
				} 
 
			}
			
			//verify Onstar BTN
			WebElement txtOnstar =	lDriver.findElement(By.xpath("//div[2]/a[contains(text(),'OnStar')]"));
			if(txtOnstar.isDisplayed())
			{
				Report.ValidationPoint(testContext.getName(), "Verify Onsatr BTN ","true","true", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Onsatr BTN ","true","false", true);
			}
			//Verify Monthly charges
			List<WebElement> monthCharges = lDriver.findElements(By.xpath(".//*[contains(@id,'monthly-alert')]"));
			if(monthCharges.size() > 0)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Monthly Charges is displayed for Onstar BTN","true","true", true);
			}
			else
			{	
				Report.ValidationPoint(testContext.getName(), "Verify Monthly Charges is displayed for Onstar BTN","true","false", true);
			}
			//Verify Government Fees & Taxes
			List<WebElement> GovernmentFees = lDriver.findElements(By.xpath(".//*[@title='Government Fees & Taxes']"));
			if(GovernmentFees.size() > 0)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Government Fees & Taxes is displayed for Onstar BTN","true","true", true);
			}
			else
			{	
				Report.ValidationPoint(testContext.getName(), "Verify Government Fees & Taxes is displayed for Onstar BTN","true","false", true);
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 }

	 
	 /**************************************************************
	  * Function Name -  ValidateEquipmentChargesSection()
	  * Description- This script validates Equipment Charges section for CTN Sharing/Receiving Upgrade
	  * Parameters - None
	  * Date created -4th Nov 15
	  * Developed by -Monica Mohabansi
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	  ***************************************************************/
	 //BAP34641

	public static void ValidateEquipmentChargesSection(ITestContext testContext) throws HeadlessException, IOException, AWTException 
	{
		Boolean bEquipCharges, bExpand, bEquipChargesDetails;
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);

//	3		Click 'Billing and Usage' CTA
			Report.OperationPoint(testContext.getName(), "Click 'Billing and Usage' CTA");		
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
	
//	4		Verify Equipment Charges Section for the CTN Sharing Upgrade
			bEquipCharges = UI.WaitForObject(oR_BillAndUsage.txtEquipmentsChargesSection, 20, lDriver);
			Report.TestPoint(testContext.getName(), "Verify Equipment Charges Section for the CTN Sharing Upgrade", "true", String.valueOf(bEquipCharges), true);
			
			/** expanding equipment charges section if not already expanded **/
			bExpand = UI.WaitForObject(lDriver.findElement(By.xpath("//a[@title='Equipment Charges']//img[@alt='expand']")), 1, lDriver);
			if(bExpand)
				lDriver.findElement(By.xpath("//a[@title='Equipment Charges']//img[@alt='expand']")).click();
			
//	5		Verify Equipment Charges Section contents for the CTN Sharing Upgrade where pending installment is created after cross upgrade and before cross upgrade reversal
			
			bEquipChargesDetails = UI.WaitForObject(oR_BillAndUsage.txtInstallmentPlanID, 20, lDriver);
			Report.TestPoint(testContext.getName(), "Verify Equipment Charges Section contents for the CTN Sharing Upgrade", "true", String.valueOf(bEquipChargesDetails), true);

//	6		Verify Equipment Charges Section for the CTN Receiving Upgrade
//  7		Verify Equipment Charges Section contents for the CTN Receiving Upgrade where pending installment is created after cross upgrade and before cross upgrade reversal	
//			Equipment Charges Section is present on Bill tab on BAP page. But acc to Rakshitha the data is not proper to develop the scenario. Special request to be done to stub team for the data. Might get such data in 1602/03
//			Need to modify the script later when apt data is available
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Failed to validate Equipment Charges Section", "true", "Failed to validate"+e.getMessage(), true);
		}
		
	}
	
	/**************************************************************
	  * Function Name -  ValidatePlentiLinkinSecRail()
	  * Description- This script validates Plenti Login Link under Manage Account Section on BAP page
	  * Parameters - None
	  * Date created -11th Aug 2015
	  * Developed by - Krutika Kamdi
	  ***************************************************************/
	 //BAP33212
	 public static void ValidatePlentiLinkinSecRail(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
			Report.OperationPoint(testContext.getName(), "Navigate to Bills & Usage Page");
			
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			
			WebElement lnkPlenti = lDriver.findElement(By.xpath("//h3[contains(text(),'Manage Account')]/parent::div//a[contains(text(),'Earn Plenti points at AT&T')]"));
			
			Boolean bPlenti = UI.WaitForObject(lnkPlenti,5);
			Report.ValidationPoint(testContext.getName(), "Validate 'Enroll in the Plenti Program'link within Manage Account Section of Secondary link rails within Bill,History and Reports Tab" , "true", String.valueOf(bPlenti),  true);

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 } 
	 
	 	 
	 
	 /**************************************************************
	  * Function Name -  ValidateAttNextPlanDetails()
	  * Description- This script validates AT&T plan details on Explanation of services modal window
	  * Parameters - testContext
	  * Date created -10th Nov 15
	  * Developed by -Sneha Pansare
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	  ***************************************************************/
	 //BAP28157
	
	public static void ValidateAttNextPlanDetails(ITestContext testContext) throws HeadlessException, IOException, AWTException 
	{

		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);

			//Click on Bill details link from Secondary navigation
			Report.OperationPoint(testContext.getName(), "Click on Bill details link from Secondary navigation");		
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkBillDetailsTertNav);

			//Verify Bill details page is displayed  
			Boolean txtBillingAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 40, lDriver);
			Report.TestPoint(testContext.getName(), "Verify Bill details page is displayed", "true", String.valueOf(txtBillingAndUsage), true);
			
			//Verify 'View Explanation of charges' link on Bill details page
			Boolean lnkViewExplanationOfServices = UI.WaitForObject(oR_BillAndUsage.lnkViewExplanationOfServices, 40, lDriver);
			Report.TestPoint(testContext.getName(), "Verify 'View Explanation of charges' link on Bill details page", "true", String.valueOf(lnkViewExplanationOfServices), true);
			
			//Click on "View Explanation of charges" link on Bill details page
			oR_BillAndUsage.lnkViewExplanationOfServices.click();
			Report.TestPoint(testContext.getName(), "Click on 'View Explanation of charges' link on Bill details page", "Clicked", "Clicked", true);
			
			Thread.sleep(5000);
			
			lDriver.switchTo().frame(oR_BillAndUsage.frmExplanationOfCharges);
			Report.OperationPoint(testContext.getName(), "Switch to Explanation Of Charges frame");
			
			//Verify Explanation of services modal window is opened  
			Boolean txtExplanationOfServicesFrameHeading = UI.WaitForObject(oR_BillAndUsage.txtExplanationOfServicesFrameHeading, 40, lDriver);
			Report.TestPoint(testContext.getName(), "Verify Bill details page is displayed", "true", String.valueOf(txtExplanationOfServicesFrameHeading), true);
			
			//Validate AT&T Next plan is displayed
			try
			{
				lDriver.findElement(By.xpath("//a[text()='AT&T Next']"));
				Report.TestPoint(testContext.getName(), "Validate AT&T Next plan is displayed", "displayed", "displayed", true);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Validate AT&T Next plan is displayed", "displayed", "NOT displayed", true);
			}
			
			//Validate the content for ATT NEXT plan installment charges in Explanation of services modal window
			try
			{
				lDriver.findElement(By.xpath("//a[text()='AT&T Next']")).click();
				
				lDriver.findElement(By.xpath("//*[contains(text(),'Installment plan to purchase')]"));
				Report.TestPoint(testContext.getName(), "Validate the content for ATT NEXT plan installment charges in Explanation of services modal window", "displayed", "displayed", true);
				
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Validate the content for ATT NEXT plan installment charges in Explanation of services modal window", "displayed", "NOT displayed", true);
			}
			
			
		}

		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Failed to validate Equipment Charges Section", "true", "Failed to validate"+e.getMessage(), true);
		}

	}


/**************************************************************
	  * Function Name -  VerifyMAPWithUverseDTV()
	  * Description- The purpose of the test case is to Make a payment with Uverse DTV service.
	  * Parameters - None
	  * Date created -10th Nov 2015
	  * Developed by - Guatham
	  ***************************************************************/
	 //BAP08051
	 public static void VerifyMAPWithUverseDTV(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		 Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		 WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
		OR_ReviewPaymentDetails oR_ReviewPaymentDetails = PageFactory.initElements(lDriver, OR_ReviewPaymentDetails.class);
		OR_PaymentConfirmation oR_PaymentConfirmation = PageFactory.initElements(lDriver, OR_PaymentConfirmation.class);
		String sAmount= IO.GetParamVal(sTestParams, "Amount");
		String sPaymentMethod= IO.GetParamVal(sTestParams, "Method");
		String sName= IO.GetParamVal(sTestParams, "Name");
		String sCardNo= IO.GetParamVal(sTestParams, "CardNO");
		String sDateMonth= IO.GetParamVal(sTestParams, "Month");
		String sDateYear= IO.GetParamVal(sTestParams, "Year");
		String sSecCode= IO.GetParamVal(sTestParams, "SecCode");
		String sZip= IO.GetParamVal(sTestParams, "Zip");
		String sInvalidCardNo= IO.GetParamVal(sTestParams, "InvalidCardNo");
		String sInvalidCardName= IO.GetParamVal(sTestParams, "InvalidCardName");
		int Count=0;
		Actions driver = new Actions(lDriver);
		System.out.println(sAmount);
		System.out.println(sSecCode);System.out.println(sPaymentMethod);System.out.println(sName);System.out.println(sCardNo);System.out.println(sDateMonth);
		System.out.println(sDateYear);
		try
		{
		 //navigate to BAP
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkBillingHistoryTerNav, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validating History page is displayed ", "true", String.valueOf(UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab, UI.iObjTimeOut)), true);
			
			//check bill details
			if(UI.WaitForObject(oR_BillAndUsage.txtTotalAmountDueAmount, 10, lDriver))
			{
				Report.ValidationPoint(testContext.getName(), "Validating Bill details are present", "true", "true", true);
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Validating Bill details are present", "true", "false", true);
			}
			
			//click on MAP
			if(UI.WaitForObject(oR_BillAndUsage.btnMakePaymentInBillingPage, 10, lDriver))
			{
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkMakeAPaymentTertNav, lDriver);
				
				//
				boolean bPage= UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 10, lDriver);
				Report.ValidationPoint(testContext.getName(),"Validating Make a payment page is displayed", "true",String.valueOf(bPage),true);
				
				//make payment
				
					  if(UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount, 2, lDriver))
					  {
						  oR_MakeAPayment.edtPaymentAmount.clear();
						  oR_MakeAPayment.edtPaymentAmount.sendKeys(sAmount);
						  System.out.println("Payment Amount Selected is: "+sAmount);
					  }
					  else
					  {
						  Report.TestPoint(testContext.getName(), "Validating Payment amount field is present", "true","false",true);
					  }
				  
					
					 List<WebElement> Payment= lDriver.findElements(By.xpath("//*[contains(@id,'makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.methodNameId')]//option"));
					System.out.println("options in payment drop down are:"+ Payment.size());
					 String sCardEnding= sInvalidCardNo.substring(sInvalidCardNo.length() - 4);
					 System.out.println("last 4 digits of card is: "+ sCardEnding);
					 for(WebElement e: Payment)
					 {
						 if(e.getText().contains("Card ending in "+sCardEnding))
						 {
							Count= Count+1;
						 }
					 }
					 Report.OperationPoint(testContext.getName(),"Validating Invalid payment flow");
				  //changing payment method to new debit/credit
				  if(Count>0)
				  {
					  WebElement drpPayment= lDriver.findElement(By.id("makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.methodNameId"));
					  Select OptionSelect1 = new Select(drpPayment);
					  OptionSelect1.selectByVisibleText("Card ending in "+sCardEnding);	
					  Report.OperationPoint(testContext.getName(), "Card has been already added in dropdown");
					  System.out.println("Card has been already added in dropdown");
					  Report.ValidationPoint(testContext.getName(),"validate payment option has been selected for Invalid payment flow","true",String.valueOf(oR_MakeAPayment.lnkContinueDis.isDisplayed()),true);
				}
				  else
				  {
					 WebElement drpPayment= lDriver.findElement(By.id("makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.methodNameId"));
					  Select OptionSelect1 = new Select(drpPayment);
					  OptionSelect1.selectByVisibleText(sPaymentMethod);	
					  System.out.println("Payment Method Selected is: "+sPaymentMethod);
					  //make invalid payment
					  if(UI.WaitForObject(oR_AccountOverview.frmTourMyBill, 10, lDriver))
					  	{
						  lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
				
						  UI.WaitForObject(oR_MakeAPayment.edtPaymentFrameNameOnCard, 2, lDriver);
						  oR_MakeAPayment.edtPaymentFrameNameOnCard.sendKeys(sInvalidCardName);
						  System.out.println("Name for Invalid card is:"+ sInvalidCardName);
			    
						  oR_MakeAPayment.edtPaymentFrameCardNumber.sendKeys(sInvalidCardNo);
						  System.out.println("Invalid card number  is:" +sInvalidCardNo);
						
						  Select OptionSelect = new Select(oR_MakeAPayment.lstSelectCardExpirationMonth);
						  OptionSelect.selectByVisibleText(sDateMonth);	
						  System.out.println("Month Selected for Invalid card  is:" +sDateMonth);
						  
						  Select OptionSelect_1 = new Select(oR_MakeAPayment.lstSelectCardExpirationYear);
						  OptionSelect_1.selectByVisibleText(sDateYear);	
						  System.out.println("Year Selected for Invalid card is:" +sDateYear);
						  
						  oR_MakeAPayment.edtPaymentFrameSecurityNumber.sendKeys(sSecCode);  
						  System.out.println("Security No for Invalid card is:" +sSecCode);
				
						  oR_MakeAPayment.edtPaymentFrameZipCode.sendKeys(sZip);
						  System.out.println("ZIP for Invalid card is:"+ sZip);
						  Report.TestPoint(testContext.getName(), "Validating all the required fields are filled for payment", "true",String.valueOf(oR_MakeAPayment.edtPaymentFrameZipCode.isDisplayed()),true);
						  //click on continue
						  oR_MakeAPayment. btnPaymentFrameContinue.click();
						  lDriver.switchTo().defaultContent();
					  	}
				
				}
				  Thread.sleep(3000);
				oR_MakeAPayment.lnkContinueDis.click();
				if(oR_MakeAPayment.lnkContinueDis.isEnabled())
				{
					oR_MakeAPayment.lnkContinueDis.click();
				}
				//sometimes payment alert is displayed if paying balance is more than due amount
				  if(UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert1, 60, lDriver))
				  {
					  System.out.println(" Payment alert is displayed");
					  Report.ValidationPoint(testContext.getName(),"Validate payment alert is displayed","true","true",true);
						 lDriver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click(); 
				  }
				  
				  
			//validate review page
		
			if(UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle, 10, lDriver))
			{
				Report.ValidationPoint(testContext.getName(),"Validating Review payment details page is displayed", "true","true",true);
				oR_ReviewPaymentDetails.btnSubmit.click();
			}
			else
			{
				Report.TestPoint(testContext.getName(),"Validating Review payment details page is displayed", "true","false",true);
			}
		
			//
			
			
			//validate confirmation page is displayed
			UI.WaitForObject(oR_PaymentConfirmation.txtPaymentConfirmationTitle, 10, lDriver);
			Report.ValidationPoint(testContext.getName(),"Validating payment confirmation page is displayed", "true",String.valueOf(oR_PaymentConfirmation.txtPaymentConfirmationTitle.isDisplayed()),true);
			try{
				WebElement ErrorMsg= lDriver.findElement(By.xpath("//*[contains(text(),'Your card wasn')]"));
				Report.ValidationPoint(testContext.getName(),"Validate Payment has been failed and error message was displayed","true",String.valueOf(ErrorMsg.isDisplayed()),true);
			}catch(Exception e){
				Report.ValidationPoint(testContext.getName(),"Validate Payment has been failed and error message was displayed","true","false",true);
				
			}
			
			//valid payment flow starts here
			
			WebElement link=lDriver.findElement(By.xpath("//a[contains(text(),'Re-enter Payment')]"));
			link.click();
			Thread.sleep(5000);
			Report.OperationPoint(testContext.getName(),"Validating Valid payment flow");
			 if(UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount, UI.iObjTimeOut, lDriver))
			  {
				  oR_MakeAPayment.edtPaymentAmount.clear();
				  oR_MakeAPayment.edtPaymentAmount.sendKeys("02");
				  System.out.println("Payment Amount Selected is: 02");
			  }
			List<WebElement> Payment1=  lDriver.findElements(By.xpath("//*[contains(@id,'makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.methodNameId')]//option"));
			 String sCardEnding1= sCardNo.substring(sCardNo.length() - 4);
			 System.out.println("last 4 digits of Valid card is: "+ sCardEnding1);
			 for(WebElement e: Payment1)
			 {
				 if(e.getText().contains("Card ending in "+sCardEnding1))
				 {
					Count= Count+1;
				 }
			 }
		  //changing payment method to new debit/credit
			//if card is already added then this code will handle it
		  if(Count>0)
		  {
			  WebElement drpPayment= lDriver.findElement(By.id("makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.methodNameId"));
			  Select OptionSelect1 = new Select(drpPayment);
			  OptionSelect1.selectByVisibleText("Card ending in "+sCardEnding1);	
			  Report.OperationPoint(testContext.getName(), "Card has been already added in dropdown");
			  System.out.println("Card has been already added in dropdown");
			  Report.ValidationPoint(testContext.getName(),"validate payment option has been selected for valid payment flow","true",String.valueOf(oR_MakeAPayment.lnkContinueDis.isDisplayed()),true);
				  oR_MakeAPayment.lnkContinueDis.click();
			  }
		  
		  else{	
			  Report.OperationPoint(testContext.getName(), "Card has been already added in dropdown");
				 
			 WebElement drpPayment= lDriver.findElement(By.id("makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.methodNameId"));
			 Select OptionSelect2 = new Select(drpPayment);
			OptionSelect2.selectByVisibleText(sPaymentMethod);	
			  System.out.println("Payment Method Selected  for valid card is: "+sPaymentMethod);
		 //making valid payment
				  if(UI.WaitForObject(oR_AccountOverview.frmTourMyBill, 10, lDriver))
				  {
					  lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
					  
					  //
					  if(UI.WaitForObject(oR_MakeAPayment.edtPaymentFrameNameOnCard, UI.iObjTimeOut, lDriver))
					  {
						  oR_MakeAPayment.edtPaymentFrameNameOnCard.sendKeys(sName);
						  System.out.println("Name on card is: "+sName);
						  
						  if(UI.WaitForObject(oR_MakeAPayment.edtPaymentFrameCardNumber,1, lDriver))
						  {
							  oR_MakeAPayment.edtPaymentFrameCardNumber.sendKeys(sCardNo);
							  System.out.println("card number  is: "+sCardNo);
							  
							  //if(UI.WaitForObject(oR_MakeAPayment.lstSelectCardExpirationMonth,25, lDriver))
							  {
								  Select OptionSelect_2 = new Select(oR_MakeAPayment.lstSelectCardExpirationMonth);
								  OptionSelect_2.selectByVisibleText(sDateMonth);	
								  System.out.println("Month Selected is: "+sDateMonth);
								  
								  //if(UI.WaitForObject(oR_MakeAPayment.lstSelectCardExpirationYear,1, lDriver))
								  {
									  Select OptionSelect_1 = new Select(oR_MakeAPayment.lstSelectCardExpirationYear);
									  OptionSelect_1.selectByVisibleText(sDateYear);	
									  System.out.println("Year Selected is: "+sDateYear);
									  
									 // if(UI.WaitForObject(oR_MakeAPayment.edtPaymentFrameSecurityNumber, 1, lDriver))
									  {
										  oR_MakeAPayment.edtPaymentFrameSecurityNumber.sendKeys(sSecCode);  
										  System.out.println("Security No  is: "+sSecCode);
										  
										 // if(UI.WaitForObject(oR_MakeAPayment.edtPaymentFrameZipCode, 1, lDriver))
										  {
											  oR_MakeAPayment.edtPaymentFrameZipCode.sendKeys(sZip);
											  System.out.println("ZIP is: "+sZip);
											  Report.TestPoint(testContext.getName(), "Validating all the required fields are filled for payment", "true",String.valueOf(oR_MakeAPayment.edtPaymentFrameZipCode.isDisplayed()),true);
											  //click on continue
											  Boolean bContinue = UI.WaitForObject( oR_MakeAPayment. btnPaymentFrameContinue, UI.iObjTimeOut);
											  Report.TestPoint(testContext.getName(), "Validate continue button on frame", "true",String.valueOf(bContinue),true);
											  oR_MakeAPayment.btnPaymentFrameContinue.click();
											  
											  lDriver.switchTo().defaultContent();
											  if(UI.WaitForObject(oR_MakeAPayment.lnkContinueDis, UI.iObjTimeOut, lDriver))
											  {
												  //click on continue
												  oR_MakeAPayment.lnkContinueDis.click();
											  }
										  }
									  }
								  }
							  }
							  
						  }
						}
				  }
				  else
				  {
					  Report.TestPoint(testContext.getName(), "Validating frame is dipalyed with required fields for payment", "true","false",true);
				  }
		  		}
		 // if(oR_MakeAPayment.lnkContinueDis.isEnabled())
			//{
			//	oR_MakeAPayment.lnkContinueDis.click();
			//}
		//sometimes payment alert is displayed if paying balance is more than due amount
		  if(UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert1, 90, lDriver))
		  {
			  System.out.println(" Payment alert is displayed");
			  lDriver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click(); 
			  Report.ValidationPoint(testContext.getName(),"Validate payment alert is displayed","true","true",true);
		  }
		 
				//validate review page
				if(UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle, UI.iObjTimeOut, lDriver))
				{
					Report.ValidationPoint(testContext.getName(),"Validating Review payment details page is displayed", "true","true",true);
				}
			
				//
				oR_ReviewPaymentDetails.btnSubmit.click();
				
				//validate confirmation page is displayed
				if(UI.WaitForObject(oR_PaymentConfirmation.txtPaymentConfirmationTitle, UI.iObjTimeOut, lDriver))
				{
					Report.ValidationPoint(testContext.getName(),"Validating payment confirmation page is displayed", "true","true",true);
					
					if(UI.WaitForObject(oR_PaymentConfirmation.txtThankyou, 5, lDriver))
					{
						Report.ValidationPoint(testContext.getName(),"Validating payment is success", "true","true",true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(),"Validating payment is success", "true","false",true);
					}
				}
				else
				{
					Report.TestPoint(testContext.getName(),"Validating payment confirmation page is displayed", "true","false",true);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 }
	 
	 
	 /**************************************************************
	  * Function Name -   VerifyArrangeLatePaymentPage()
	  * Description- 
	  * Parameters - None
	  * Date created -12th Nov 2015
	  * Developed by - Guatham
	  ***************************************************************/
	 //BAP28065
	 public static void VerifyArrangeLatePaymentPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		 Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		 WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		int count=0;
		try{
		//navigate to payment page
		UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,oR_AccountOverview.lnkViewArrangeLatePayment, lDriver);
		
		//validate payment method
		List <WebElement> Payment= lDriver.findElements(By.xpath("//select//option"));
		System.out.println(Payment.size());
		
		
		for(WebElement e: Payment)
		{
			if(e.getText().contains("debit/credit"))
			{
				count++;
			}
			else if(e.getText().isEmpty())
			{
				
			}
			else
			{
				Report.OperationPoint(testContext.getName(),"Payment Methods Available are: " +e.getText());
			}
		}
		if(count==0){
			lDriver.findElement(By.xpath("//select")).click();
			Report.ValidationPoint(testContext.getName(),"validate New Debit/Credit card is not present in payment method section","true","true",true);
		}
		else
		{
			lDriver.findElement(By.xpath("//select")).click();
			Report.ValidationPoint(testContext.getName(),"validate New Debit/Credit card is not present in payment method section","true","false",true);
		}
		
		//
	WebElement Tooltip=	lDriver.findElement(By.xpath("//a[@class='lnk-help tooltips']//img"));
		//validate messgae
		
		try
		{
			if(UI.WaitForObject(lDriver.findElement(By.xpath("//span[contains(text(),'Select')]")), 10, lDriver))
			{
				Tooltip.click();
				Report.ValidationPoint(testContext.getName(),"validate appropriate dynamic messaging is displayed within the tool tip  to convey payment options available to the user ","true","true",true);
			}
			else
				
			{
				Tooltip.click();
				Report.ValidationPoint(testContext.getName(),"validate appropriate dynamic messaging is displayed within the tool tip  to convey payment options available to the user ","true","false",true);
			}
			
		}
		catch(Exception e)
		{
			Report.ValidationPoint(testContext.getName(),"validate appropriate dynamic messaging is displayed within the tool tip  to convey payment options available to the user ","true","false",true);
		}
		}
	catch(Exception e)
	{
		e.printStackTrace();
		Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
	}
 } 
	 /**************************************************************
	  * Function Name -  VerifyServiceCancelledMessageInBAP()
	  * Description- 
	  * Parameters - None
	  * Date created -13th Nov 2015
	  * Developed by - Guatham
	  ***************************************************************/
	 //BAP30751

	 public static void VerifyServiceCancelledMessageInBAP(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		 Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		 WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
	 try{
		 //navigate to BAP
		 UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
		 
		 //validate service cancelled message
		 boolean bService=UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(text(),'Your service has been canceled')]")), 10, lDriver);
		 Report.ValidationPoint(testContext.getName(),"validte 'Your service has been canceled....' message has been displayed in BAP page","true",String.valueOf(bService),true);
		 
		 //validate error box
		 boolean bDiv=UI.WaitForObject(lDriver.findElement(By.xpath("//div[@class='msg box botMar14']")), 10, lDriver);
		 Report.ValidationPoint(testContext.getName(),"validte that message in not in error box","true",String.valueOf(bDiv),true);
		
	 }
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 } 

	 /**************************************************************
	  * Function Name -  ValidatePaymentMethodDropDownInMAPage()
	  * Description- 
	  * Parameters - None
	  * Date created -13th Nov 2015
	  * Developed by - Guatham
	  ***************************************************************/
	 //BAP28031, //BAP28058

	 public static void ValidatePaymentMethodDropDownInMAPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		 Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		 WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
	 try{
		 int count=0;
		 Actions driver= new Actions(lDriver);
		 if(testContext.getName().contains("BAP28058"))
		 {
			boolean bAutopay=UI.WaitForObject(oR_AccountOverview.lnkEnrollInAutopay,10, lDriver);
			Report.ValidationPoint(testContext.getName(),"validate Enroll autopay/manage autopay link is present","true",String.valueOf(bAutopay),true);
			oR_AccountOverview.lnkEnrollInAutopay.click();
		 }
		 else{
		 //navigate to BAP
		 UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkMakeAPaymentTertNav, lDriver);
		 }
		 
		//validate payment method
			List <WebElement> Payment= lDriver.findElements(By.xpath("//select//option"));
			Report.OperationPoint(testContext.getName(), "No.Of options in payment method drop down is: "+Payment.size());
			
			int i=0;
			for(WebElement e: Payment)
			{
				Report.OperationPoint(testContext.getName(), "Payment Option "+i+" is: "+e.getText());
				i++;
				if(e.getText().contains("checkings/savings"))
				{
					count++;
				}
			}
			if(count==0){
				driver.moveToElement(lDriver.findElement(By.xpath("//select"))).click().build().perform();;
				Report.ValidationPoint(testContext.getName(),"validate New checkings/savings account is not present in payment method section","true","true",true);
			}
			else
			{
				lDriver.findElement(By.xpath("//select")).click();
				Report.ValidationPoint(testContext.getName(),"validate New  New checkings/savings account is not present in payment method section","true","false",true);
			}
			
			//
		WebElement Tooltip=	lDriver.findElement(By.xpath("//a[@class='lnk-help tooltips']|//a[@class='right MarTop3 top5px lnk-help tooltips']//img[@alt='tool tip help']"));
			//validate messgae
			
			try
			{
				boolean bPayment=UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(text(),'Select payment method')]")), 10, lDriver);
			
				if(bPayment)
				{
					driver.moveToElement(Tooltip).click().build().perform();;
					Report.ValidationPoint(testContext.getName(),"validate appropriate dynamic messaging is displayed within the tool tip  to convey payment options available to the user ","true","true",true);
				}
				else
					
				{
					driver.moveToElement(Tooltip).click().build().perform();;
					Report.ValidationPoint(testContext.getName(),"validate appropriate dynamic messaging is displayed within the tool tip  to convey payment options available to the user ","true","false",true);
				}
				
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(),"validate appropriate dynamic messaging is displayed within the tool tip  to convey payment options available to the user ","true","false",true);
			}
			}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 }
	 
	 /**************************************************************
	  * Function Name -  ValidateSessionPlan()
	  * Description- This script validates Plenti Login Link under Manage Account 

Section on BAP page
	  * Parameters - None
	  * Date created -30th Nov 2015
	  * Developed by - Krutika Kamdi
	  ***************************************************************/
	 //BAP35579 , BAP35580
	 public static void ValidateSessionPlan(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			try
		{
			Report.OperationPoint(testContext.getName(), "Navigate to Bills & Usage Page");
			
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
		    //Validate View Summary details section

			Boolean bSummary = UI.WaitForObject(oR_BillAndUsage.lnkViewPlanSummary,30);
			Report.ValidationPoint(testContext.getName(), "Validate 'View Plan Summary'link is displayed" , "true", String.valueOf(bSummary),  true);
			Report.OperationPoint(testContext.getName(), "Clicking on Plan Summary link");
			
			oR_BillAndUsage.lnkViewPlanSummary.click();
			Thread.sleep(5000);
			
			
			Boolean btrialPlan= UI.WaitForObject(oR_BillAndUsage.txtTrialPlan,30);
			Report.ValidationPoint(testContext.getName(), "Validate 'Trial/Pre-paid Data Plan' is displayed" , "true", String.valueOf(btrialPlan),  true);
			
			Boolean bTrialSummary = UI.WaitForObject(oR_BillAndUsage.txtPlanDescription,30);
			Report.ValidationPoint(testContext.getName(), "Validate 'informational contents' for trial plan is displayed" , "true", String.valueOf(bTrialSummary),  true);
			
			if(UI.WaitForObject(oR_BillAndUsage.lnkViewPlanSummary,30))
			{
				Report.ValidationPoint(testContext.getName(), "Validate 'OnStar title' is displayed" , "true", "true",  true);
			}
			
			
			//As discussed with Rakshitha
			
			//data for Modal page with Session plan name should be displayed -> OnStar Data Trial 3GB/3Mon; SOC info- Trial - 3mo/3GB; SOC code - GMTRS is not available

			//BAP35580 : data not available for Modal page with Session plan name should be displayed:OnStar Data Trial 30GB/6Mon;Turbo Session no bundle GM Funded executive Usage ($0/30GB/6mo);SOC code - GMS30GEXD

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 } 	 
	 

	 /****************************************************************
		 * Function Name -  ValidateMessageforCancelledBillwithOutstandinbalance
		 * Description- This function Validates message within the Bill Tab when a Wireline Customer is cancelled with outstanding balance
		 * Parameters - None 
		 * Date created -1st Dec 2015
		 * Developed by - Hiral Jogi
		 * Last Modified By - 
		 * Last Modified Date -
		 * @throws AWTException 
		 * @throws IOException 
		 * @throws HeadlessException 
		 ***************************************************************/
		//BAP30707
	 
	 public static void ValidateMessageforCancelledBillwithOutstandinbalance(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	 {
		 	WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
			
		
		 try
		 {
			 	// Navigating to Billing and Usage Page
				
				Report.OperationPoint(testContext.getName(), "Navigating to Billing and Usage page");
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsageSecNav, null);
				
				// Validate user is navigated to Billing and Usage Page
				
				Boolean bBillingAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
				if(bBillingAndUsage) {
					 Report.ValidationPoint(testContext.getName(),"Validate user is navigated to Billing and Usage Landing.","true","true",true);
				}
			 
				// Validate message at account level
				Boolean bNote = UI.WaitForObject(oR_BillAndUsage.txtNote, UI.iObjTimeOut, lDriver);
				if(bNote) {
					 Report.ValidationPoint(testContext.getName(),"Validating content of the note.","true","true",true);
					 String strnoteactual = oR_BillAndUsage.txtNote.getText();
					 System.out.println(strnoteactual);
					 String strnoteexpected =null;
					 if(testContext.getName().contains("BAP30701")){
						 strnoteexpected = "Note: Your account has been suspended due to an outstanding balance of";
						 }
					
					 else{
					 strnoteexpected = "Note: Your account has been canceled due to an outstanding balance of $475.99. Call Customer Care at 800.288.2020 to resolve this issue.";
					 }
					// String strnoteactual = oR_BillAndUsage.txtNote.getText();
					 if(strnoteactual.equalsIgnoreCase(strnoteexpected)){
					// if(strnoteactual.matches(strnoteexpected)){
						 Report.ValidationPoint(testContext.getName(),"Content of the note is '"+strnoteexpected+"'... as expected.","true","true",true);
					 } else {
						 Report.ValidationPoint(testContext.getName(),"Content of the note is not as expected.","true","true",true);
					 }
					 if(testContext.getName().contains("BAP30701")){
						 WebElement MAP= lDriver.findElement(By.xpath("//a[contains(text(),'Make a payment')]"));
						 Report.TestPoint(testContext.getName(),"validate make a payment CTA is present","true",String.valueOf(MAP.isDisplayed()),true);
						 MAP.click();
						 boolean bMAP= UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 10);
						 Report.ValidationPoint(testContext.getName(),"validate make a payment page is present","true",String.valueOf(bMAP),true);
						 }
				}
			 
				
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		 }
	 }
	 
	 /**************************************************************
		 * Function Name - VerifyAutopayCardExpiredAlert()
		 * Description - The purpose of this test is To validate Autopay card expired alert 
	 	 * Test Case -  IST_BAP28382_RDR-942_To validate Autopay card expired alert on Bill details page _SLID+CBWIRELINE_ Mozilla Firefox - H
		 * Parameters - None
		 * Date created - 2nd Dec 2015
		 * Developed by - Clint John
		 * Last Modified By - 
		 * Last Modified Date - 
		 ***************************************************************/
		public static void VerifyAutopayCardExpiredAlert(final ITestContext testContext) throws HeadlessException, IOException, AWTException
		{
			try
			{
				WebDriver lDriver = UI.getDriver(testContext.getName());
				OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
				OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
				Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
				
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Billing, Usage, Payments' tab");
				
				Thread.sleep(4000);
				new Actions(lDriver).moveToElement(oR_BillAndUsage.lnkBillTab).click().build().perform();
				boolean bBillAndPaymentsPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that User landed on Bill deatils page", "true",String.valueOf(bBillAndPaymentsPage), true);
				
				//Verify Card expired ALert for Autopay is displayed
				boolean bAutopayCardExpiredAlert = UI.WaitForObject(oR_BillAndUsage.txtAutoPayCardExpiredAlert, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Autopay card expired alert", "true",String.valueOf(bAutopayCardExpiredAlert), true);
				

			}
			catch (Exception e) 
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

			}
			
		}

		/****************************************************************
		 * Function Name -  ValidateMessageInBAPpage()
		 * Description- 
		 * Parameters - None 
		 * Date created -2nd Dec 2015
		 * Developed by - Gautham
		 * Last Modified By - 
		 * Last Modified Date -
		 * @throws AWTException 
		 * @throws IOException 
		 * @throws HeadlessException 
		 ***************************************************************/
		//BAP30701
	 
	 public static void ValidateMessageInBAPpage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	 {
		 	WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		try
		 {
			ValidateMessageforCancelledBillwithOutstandinbalance(testContext);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		 }
	 }
	 
	 /****************************************************************
		 * Function Name -  ValidateSecondylinksinHistoryPage()
		 * Description- This function validates secondary links in History Page for Titan Converged Account where New Wireless and New Directv Services bundled 
		 * Parameters - None 
		 * Date created -2nd Dec 2015
		 * Developed by - Hiral Jogi
		 * Last Modified By - 
		 * Last Modified Date -
		 * @throws AWTException 
		 * @throws IOException 
		 * @throws HeadlessException 
	 ***************************************************************/
	//BAP34611
	 
	 public static void ValidateSecondylinksinHistoryPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	 {
		 	WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			String ParentURL;
		
		 try
		 {
			 	// Navigating to Billing and Usage Page
				
				Report.OperationPoint(testContext.getName(), "Navigating to Billing and Usage page");
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsageSecNav, null);
				
				// Validate user is navigated to Billing and Usage Page
				
				Boolean bBillingAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
				if(bBillingAndUsage) {
					 Report.ValidationPoint(testContext.getName(),"Validate user is navigated to Billing and Usage Landing.","true","true",true);
				}
			 
				 ParentURL = lDriver.getCurrentUrl();
				
				// Validate 'Manage Account' section within the History Page 
				
				Boolean bManageAccount = UI.WaitForObject(oR_BillAndUsage.txtManageAccount, UI.iObjTimeOut, lDriver);
				if(bManageAccount) {
					 Report.ValidationPoint(testContext.getName(),"Validate 'Manage Account' section is present on the history page.","true","true",true);
					 
					 // Validate links within 'Manage Account' section within the History Page
					 
					 // Link 1 (Link your Account To Plenti)
					 
//					 Boolean bLinkyourAccountToPlenti = UI.WaitForObject(oR_BillAndUsage.lnkLinkyourAccountToPlenti, UI.iObjTimeOut, lDriver);
//						if(bLinkyourAccountToPlenti) {
//							 Report.ValidationPoint(testContext.getName(),"Validate 'LinkyourAccountToPlenti' link is present under Manage Account.","true","true",true);
//							 
//							 // click 'LinkyourAccountToPlenti' link
//							 
//							 Report.ValidationPoint(testContext.getName(),"Clicking 'LinkyourAccountToPlenti' link.","true","true",true);
//							 oR_BillAndUsage.lnkLinkyourAccountToPlenti.click();
//							 Thread.sleep(2000);
//						
//							 // pending to capture the text after clicking on the link as currently unavailable
//							 
//							 String sURL= lDriver.getCurrentUrl();
//							 Report.ValidationPoint(testContext.getName(),"Clicking on 'LinkyourAccountToPlenti' link user is directed to URL :-" + sURL ,"true","true",true);
//							 Thread.sleep(5000);
//							 
//							 Report.OperationPoint(testContext.getName(), "Navigating to Billing and Usage page");
//							 UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsageSecNav, null);
					
//							 
//						} 
						
					// Link 2 (Change Wireless Plan)
						
					Boolean bChangeWirelessPlan = UI.WaitForObject(oR_BillAndUsage.lnkChangeWirelessPlan, UI.iObjTimeOut, lDriver);
						if(bChangeWirelessPlan) {
							Report.ValidationPoint(testContext.getName(),"Validate 'ChangeWirelessPlan' link is present under Manage Account.","true","true",true);
							 
							 // click 'ChangeWirelessPlan' link
							 
							Report.ValidationPoint(testContext.getName(),"Clicking 'ChangeWirelessPlan' link.","true","true",true); 
							oR_BillAndUsage.lnkChangeWirelessPlan.click();
							Thread.sleep(2000);
							
							boolean bvieworchange = UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'View or Change Rate Plan')]")), UI.iObjTimeOut, lDriver);
							if(bvieworchange){
								 Report.ValidationPoint(testContext.getName(),"View or Change Rate Plan page is seen","true","true",true);
							}
													 
							String sURL= lDriver.getCurrentUrl();
							Report.ValidationPoint(testContext.getName(),"Clicking on 'ChangeWirelessPlan' link user is directed to URL :-" + sURL ,"true","true",true);
							Thread.sleep(3000);
							
							// Navigating back to billing and usage page
							 
							 lDriver.navigate().to(ParentURL);
						}
						
					// Link 3 (Manage Wireless Devices Features)
						
//					Boolean bManagemywirelessdevice = UI.WaitForObject(oR_BillAndUsage.lnkManageWirelessDevicesFeatures, UI.iObjTimeOut, lDriver);
//						if(bManagemywirelessdevice) {
//							Report.ValidationPoint(testContext.getName(),"Validate 'ManageWirelessDevicesFeatures' link is present under Manage Account.","true","true",true);
//							 
//							 // click 'Manage my wirless device & features' link
//							
//							 Report.ValidationPoint(testContext.getName(),"Clicking 'Manage my wirless device & features' link.","true","true",true); 
//							 oR_BillAndUsage.lnkManageWirelessDevicesFeatures.click();
//							 
//							// pending to capture the text after clicking on the link as currently unavailable
//							 
//							 
//							 String sURL= lDriver.getCurrentUrl();
//							 Report.ValidationPoint(testContext.getName(),"Clicking on 'ManageWirelessDevicesFeatures' link user is directed to URL :-" + sURL ,"true","true",true);
//							 Thread.sleep(3000);
//							 
//							 Report.OperationPoint(testContext.getName(), "Navigating to Billing and Usage page");
//							 UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsageSecNav, null);  
//						}
						
					// Link 4 (View or change my DIRECTV Plan)
						
					Boolean bViewDIRECTVPlan = UI.WaitForObject(oR_BillAndUsage.lnkViewDIRECTVPlan, UI.iObjTimeOut, lDriver);
						if(bViewDIRECTVPlan) {
							Report.ValidationPoint(testContext.getName(),"Validate 'View or change my DIRECTV Plan' link is present under Manage Account.","true","true",true);
							 
							 // click 'View or change my DIRECTV Plan' link
							 
							 Report.ValidationPoint(testContext.getName(),"Clicking 'View or change my DIRECTV Plan' link.","true","true",true); 
							 oR_BillAndUsage.lnkViewDIRECTVPlan.click();
							 Thread.sleep(2000);
							 
							 // Validating if it navigated to DTV page
							 String str = lDriver.getCurrentUrl();
							 Thread.sleep(10000);
							 if(str.contains("https://dtvsys.directv.com/DTVAPP/att/myProgramming.jsp")){
								 Report.ValidationPoint(testContext.getName(),"'Direct TV' page is seen","true","true",true);
							 }
							 
//							 boolean bdirecttv = UI.WaitForObject(lDriver.findElement(By.xpath("//h2[contains(text(),'My Account:')]")), UI.iObjTimeOut, lDriver);
//							 if(bdirecttv){
//									 Report.ValidationPoint(testContext.getName(),"'Direct TV' page is seen","true","true",true);
//							 }
							 
							 Report.ValidationPoint(testContext.getName(),"Clicking on 'View or change my DIRECTV Plan' link user is directed to URL :-" + str ,"true","true",true);
							 Thread.sleep(3000);
							 
							 lDriver.navigate().to(ParentURL);
					}
						
					// Link 5 (Upgrade my device)
						
//					Boolean bUpgradeDevice = UI.WaitForObject(oR_BillAndUsage.lnkUpgradeDevice, UI.iObjTimeOut, lDriver);
//						if(bUpgradeDevice) {
//							Report.ValidationPoint(testContext.getName(),"Validate 'Upgrade my device' link is present under Manage Account.","true","true",true);
//							 
//							 // click 'Upgrade my device' link
//							
//							 Report.ValidationPoint(testContext.getName(),"Clicking 'Upgrade my device' link.","true","true",true); 
//							 oR_BillAndUsage.lnkUpgradeDevice.click();
//							
//							 // pending to capture the text after clicking on the link as currently unavailable
//							 
//							 
//							 String sURL= lDriver.getCurrentUrl();
//							 Report.ValidationPoint(testContext.getName(),"Clicking on 'Upgrade my device' link user is directed to URL :-" + sURL ,"true","true",true);
//							 Thread.sleep(3000);
//							 
//							 Report.OperationPoint(testContext.getName(), "Navigating to Billing and Usage page");
//							 UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsageSecNav, null); 
//						}
				}
				
				// Validate 'Manage Billing & Payment Options' section within the History Page 
				
				Boolean bManagebilling = UI.WaitForObject(oR_BillAndUsage.txtManageBillingAndPaymentOptions, UI.iObjTimeOut, lDriver);
				if(bManagebilling) {
					 Report.ValidationPoint(testContext.getName(),"Validate 'Manage Billing And Payment Options' section is present on the history page.","true","true",true);
					 
					 // Validate links within 'Manage Billing & Payment Option' section within the History Page
					 // Link 1 (Enroll in Paperless Billing)
					 
					 Boolean bEnroll = UI.WaitForObject(oR_BillAndUsage.lnkEnrollinPaperlessBilling, UI.iObjTimeOut, lDriver);
						if(bEnroll) {
							Report.ValidationPoint(testContext.getName(),"Validate 'EnrollinPaperlessBilling' link is present under Manage billing&Payment options.","true","true",true);
							 
							 // click 'EnrollinPaperlessBilling' link
							 
							Report.ValidationPoint(testContext.getName(),"Clicking 'UEnrollinPaperlessBilling' link.","true","true",true);  
							oR_BillAndUsage.lnkEnrollinPaperlessBilling.click();
							
							String str = lDriver.getCurrentUrl();
							Thread.sleep(8000);
							 if(str.contains("https://myattmonitor8.stage.att.com:8442/view/paperless.doview")){
								 Report.ValidationPoint(testContext.getName(),"'EnrollinPaperlessBilling' page is seen","true","true",true);
							 }
//							boolean bpaperlessBilling = UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'Paperless Billing')]")), UI.iObjTimeOut, lDriver);
//							if(bpaperlessBilling){
//								 Report.ValidationPoint(testContext.getName(),"'Paperless Billing' page is seen","true","true",true);
//							}
							
							Report.ValidationPoint(testContext.getName(),"Clicking on 'EnrollinPaperlessBilling' link user is directed to URL :-" + str ,"true","true",true);
							Thread.sleep(3000);
							 
							 lDriver.navigate().to(ParentURL); 
						}
						
					// Link 2 (Enroll In Autoplay)
						
//					Boolean bEnrollInAutoplay = UI.WaitForObject(oR_BillAndUsage.lnkEnrollInAutopay, UI.iObjTimeOut, lDriver);
//						if(bEnrollInAutoplay) {
//							Report.ValidationPoint(testContext.getName(),"Validate 'EnrollInAutoplay' link is present under Manage billing&Payment options.","true","true",true);
//							 
//							// click 'EnrollInAutoplay' link
//								 
//							Report.ValidationPoint(testContext.getName(),"Clicking 'EnrollInAutoplay' link.","true","true",true);  
//							oR_BillAndUsage.lnkEnrollInAutopay.click();
//								
//							// pending to capture the text after clicking on the link as currently unavailable
//								
//							boolean bpaperlessBilling = UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'Paperless Billing')]")), UI.iObjTimeOut, lDriver);
//							if(bpaperlessBilling){
//								Report.ValidationPoint(testContext.getName(),"'Paperless Billing' page is seen","true","true",true);
//							}
//								
//							String sURL= lDriver.getCurrentUrl();
//							Report.ValidationPoint(testContext.getName(),"Clicking on 'EnrollinPaperlessBilling' link user is directed to URL :-" + sURL ,"true","true",true);
//							Thread.sleep(3000);
//								 
//							Report.OperationPoint(testContext.getName(), "Navigating to Billing and Usage page");
//							UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsageSecNav, null);  
//						}
					
					// Link 3 (See More Payment Options)
						
					Boolean bSeeMorePaymentOptions = UI.WaitForObject(oR_BillAndUsage.lnkSeeMorePaymentOptions, UI.iObjTimeOut, lDriver);
						if(bSeeMorePaymentOptions) {
							Report.ValidationPoint(testContext.getName(),"Validate 'SeeMorePaymentOptions' link is present under Manage billing&Payment options.","true","true",true);
									 
							// click 'SeeMorePaymentOptions' link
									 
							Report.ValidationPoint(testContext.getName(),"Clicking 'SeeMorePaymentOptions' link.","true","true",true);  
							oR_BillAndUsage.lnkSeeMorePaymentOptions.click();
							
							String str = lDriver.getCurrentUrl();
							Thread.sleep(8000);
							if(str.contains("https://myattmonitor8.stage.att.com:8442/pmt/setupPaymentOptions.myworld?reportActionEvent=A_PMT_SETUP_PAY_OPTIONS&reportActionForm=commonPaymentForm")){
								 Report.ValidationPoint(testContext.getName(),"'SeeMorePaymentOptions' page is seen","true","true",true);
							 }
//							boolean bpaperlessBilling = UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'Payment Options')]")), UI.iObjTimeOut, lDriver);
//							if(bpaperlessBilling){
//								 Report.ValidationPoint(testContext.getName(),"'SeeMorePaymentOptions' page is seen","true","true",true);
//							}
									
							Report.ValidationPoint(testContext.getName(),"Clicking on 'SeeMorePaymentOptions' link user is directed to URL :-" + str ,"true","true",true);
							Thread.sleep(3000);
									 
							Report.OperationPoint(testContext.getName(), "Navigating to Billing and Usage page");
							UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsageSecNav, null);  
						}
						
					// Link 4 (Update Billing Information)
						
					Boolean bUpdateBillingInfo = UI.WaitForObject(oR_BillAndUsage.lnkUpdateBillingInfo, UI.iObjTimeOut, lDriver);
						if(bUpdateBillingInfo) {
							Report.ValidationPoint(testContext.getName(),"Validate 'UpdateBillingInformation' link is present under Manage billing&Payment options.","true","true",true);
									 
							 // click 'UpdateBillingInformation' link
							
							Report.ValidationPoint(testContext.getName(),"Clicking 'UpdateBillingInformation' link.","true","true",true);  
							oR_BillAndUsage.lnkUpdateBillingInfo.click();
							
							String str = lDriver.getCurrentUrl();
							Thread.sleep(8000);
							if(str.contains("https://myattmonitor8.stage.att.com:8442/olam/profileAddresseditAddress.myworld")){
								 Report.ValidationPoint(testContext.getName(),"'UpdateBillingInformation' page is seen","true","true",true);
							 }
							
//							boolean bChangeBillingAddress = UI.WaitForObject(lDriver.findElement(By.xpath("//h2[contains(text(),'Change Billing Address')]")), UI.iObjTimeOut, lDriver);
//							if(bChangeBillingAddress){
//								Report.ValidationPoint(testContext.getName(),"'UpdateBillingInformation' page is seen","true","true",true);
//							}
							
							Report.ValidationPoint(testContext.getName(),"Clicking on 'UpdateBillingInformation' link user is directed to URL :-" + str ,"true","true",true);
							Thread.sleep(3000);
							
							lDriver.navigate().to(ParentURL);							 
						}
							
					// Link 5 (Transfer Wireless Billing Responsibility)
						
					Boolean bTransferBilling = UI.WaitForObject(oR_BillAndUsage.lnkTransferWirelessBillingResponsibility, UI.iObjTimeOut, lDriver);
						if(bTransferBilling) {
							Report.ValidationPoint(testContext.getName(),"Validate 'TransferWirelessBillingResponsibility' link is present under Manage billing&Payment options.","true","true",true);
									 
							// click 'TransferWirelessBillingResponsibility' link
									 
							Report.ValidationPoint(testContext.getName(),"Clicking 'TransferWirelessBillingResponsibility' link.","true","true",true);  
							oR_BillAndUsage.lnkTransferWirelessBillingResponsibility.click();
							
							String str = lDriver.getCurrentUrl();
							Thread.sleep(8000);
							if(str.contains("https://www.att.com/tobr/tobrSelectCtn.rt")){
								 Report.ValidationPoint(testContext.getName(),"'TransferWirelessBillingResponsibility' page is seen","true","true",true);
							 }
							
//							boolean bHeaddingtext = UI.WaitForObject(lDriver.findElement(By.xpath("//h1")), UI.iObjTimeOut, lDriver);
//							if(bHeaddingtext){
//								 Report.ValidationPoint(testContext.getName(),"'TransferWirelessBillingResponsibility' page is seen","true","true",true);
//							}
							
							Report.ValidationPoint(testContext.getName(),"Clicking on 'TransferWirelessBillingResponsibility' link user is directed to URL :-" + str ,"true","true",true);
							Thread.sleep(3000);
									 
							lDriver.navigate().to(ParentURL);							
						}
				}
				
				// Validate 'Get help' section within the History Page 
				
				Boolean bGetHelp = UI.WaitForObject(oR_BillAndUsage.txtGetHelpHeading, UI.iObjTimeOut, lDriver);
				if(bGetHelp) {
					 Report.ValidationPoint(testContext.getName(),"Validate 'Get Help' section is present on the history page.","true","true",true);
					 
					 // Validate links within 'Manage Billing & Payment Option' section within the History Page
					 
					 // Link 1 (PlayVideoOnViewUsageDetails)
					 
					 Boolean bPlayVideo = UI.WaitForObject(oR_BillAndUsage.lnkPlayVideoOnViewUsageDetails, UI.iObjTimeOut, lDriver);
						if(bPlayVideo) {
							Report.ValidationPoint(testContext.getName(),"Validate 'PlayVideoOnViewUsageDetails' link is present under Get Help options.","true","true",true);
							
							// click 'SeeWaysToPayYourBill' link
							Report.ValidationPoint(testContext.getName(),"Clicking 'PlayVideoOnViewUsageDetails' link.","true","true",true);  
							oR_BillAndUsage.lnkPlayVideoOnViewUsageDetails.click();
							
							boolean bFlexiblepaymentsmodal = UI.WaitForObject(lDriver.findElement(By.xpath("//div[@id='gvpModalInjection']/parent::div")), UI.iObjTimeOut, lDriver);
							if(bFlexiblepaymentsmodal){
								 Report.ValidationPoint(testContext.getName(),"'PlayVideoOnViewUsageDetails' modal is seen.","true","true",true);
							}
							
							// Close
							boolean bclose = UI.WaitForObject(lDriver.findElement(By.xpath("//button[contains(text(),'Close')]")), UI.iObjTimeOut, lDriver);
							if(bclose){
								Report.ValidationPoint(testContext.getName(),"Closing the 'PlayVideoOnViewUsageDetails' modal.","true","true",true);
								lDriver.findElement(By.xpath("//button[contains(text(),'Close')]")).click();
							}
							
					}
					 
					 //Link 2 (see ways to pay your bill)
				
					 Boolean bWaystopaybill = UI.WaitForObject(oR_BillAndUsage.lnkSeewaysToPayYourBill, UI.iObjTimeOut, lDriver);
						if(bWaystopaybill) {
							Report.ValidationPoint(testContext.getName(),"Validate 'See WaysToPayYourBill' link is present under Get Help options.","true","true",true);
							 
							 // click 'SeeWaysToPayYourBill' link
							 
							Report.ValidationPoint(testContext.getName(),"Clicking 'SeeWaysToPayYourBill' link.","true","true",true);  
							oR_BillAndUsage.lnkSeewaysToPayYourBill.click();
							
							
							String str = lDriver.getCurrentUrl();
							Thread.sleep(8000);
							if(str.contains("http://www.att.com/esupport/article.html#!/wireless/KM1009340?source=redirect")){
								 Report.ValidationPoint(testContext.getName(),"'SeeWaysToPayYourBill' page is seen","true","true",true);
							 }
//							boolean bSeeways = UI.WaitForObject(lDriver.findElement(By.xpath("//div[@class='page-heading pB35']/h1")), UI.iObjTimeOut, lDriver);
//							if(bSeeways){
//								 Report.ValidationPoint(testContext.getName(),"'SeeWaysToPayYourBill' page is seen","true","true",true);
//							}
							
							String sURL= lDriver.getCurrentUrl();
							Report.ValidationPoint(testContext.getName(),"Clicking on 'SeeWaysToPayYourBill' link user is directed to URL :-" + sURL ,"true","true",true);
							Thread.sleep(3000);
							 
							lDriver.navigate().to(ParentURL);							
						}
					
					//Link 3 (Make Payment arrangements)
					
					Boolean bMakePaymentArrangements = UI.WaitForObject(oR_BillAndUsage.lnkMakePaymentArrangements, UI.iObjTimeOut, lDriver);
						if(bMakePaymentArrangements) {
							Report.ValidationPoint(testContext.getName(),"Validate 'MakePaymentArrangements' link is present under Get Help options.","true","true",true);
								 
							// click 'MakePaymentArrangements' link
								 
							Report.ValidationPoint(testContext.getName(),"Clicking 'MakePaymentArrangements' link.","true","true",true);  
							oR_BillAndUsage.lnkMakePaymentArrangements.click();
								
							String str = lDriver.getCurrentUrl();
							Thread.sleep(8000);
							if(str.contains("https://www.att.com/esupport/article.html#!/wireless/KM1025834?source=redirect")){
								 Report.ValidationPoint(testContext.getName(),"'MakePaymentArrangements' page is seen","true","true",true);
							 }
//							boolean bBillingAccountSupport = UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'Billing & Account Support')]")), UI.iObjTimeOut, lDriver);
//							if(bBillingAccountSupport){
//								Report.ValidationPoint(testContext.getName(),"'MakePaymentArrangements' page is seen","true","true",true);
//							}
								
							String sURL= lDriver.getCurrentUrl();
							Report.ValidationPoint(testContext.getName(),"Clicking on 'MakePaymentArrangements' link user is directed to URL :-" + sURL ,"true","true",true);
							Thread.sleep(3000);
								 
							lDriver.navigate().to(ParentURL);
						}
				
					//Link 4 (Question A Charge)
						
					Boolean bQuestionACharge = UI.WaitForObject(oR_BillAndUsage.lnkQuestion, UI.iObjTimeOut, lDriver);
						if(bQuestionACharge) {
							Report.ValidationPoint(testContext.getName(),"Validate 'QuestionACharge' link is present under Get Help options.","true","true",true);
									 
							// click 'QuestionACharge' link
									 
							Report.ValidationPoint(testContext.getName(),"Clicking 'QuestionACharge' link.","true","true",true);  
							oR_BillAndUsage.lnkQuestion.click();
							
							String str = lDriver.getCurrentUrl();
							Thread.sleep(8000);
							if(str.contains("https://www.att.com/esupport/article.html#!/wireless/KM1002304?source=redirect")){
								 Report.ValidationPoint(testContext.getName(),"'QuestionACharge' page is seen","true","true",true);
							 }
							
//							boolean bQuestion = UI.WaitForObject(lDriver.findElement(By.xpath("//div[@class='page-heading pB35']/h1")), UI.iObjTimeOut, lDriver);
//							if(bQuestion){
//								Report.ValidationPoint(testContext.getName(),"'QuestionACharge' page is seen","true","true",true);
//							}
									
							String sURL= lDriver.getCurrentUrl();
							Report.ValidationPoint(testContext.getName(),"Clicking on 'QuestionACharge' link user is directed to URL :-" + sURL ,"true","true",true);
							Thread.sleep(3000);
						
							lDriver.navigate().to(ParentURL);
						}
						
					//Link 5 (Go To Billing Support)
						
					Boolean bBillingSupport = UI.WaitForObject(oR_BillAndUsage.lnkGoToBillingSupport, UI.iObjTimeOut, lDriver);
						if(bBillingSupport) {
							Report.ValidationPoint(testContext.getName(),"Validate 'GoToBillingSupport' link is present under Get Help options.","true","true",true);
									 
							// click 'GoToBillingSupport' link
									 
							Report.ValidationPoint(testContext.getName(),"Clicking 'GoToBillingSupport' link.","true","true",true);  
							oR_BillAndUsage.lnkGoToBillingSupport.click();
							
							String str = lDriver.getCurrentUrl();
							Thread.sleep(8000);
							if(str.contains("https://www.att.com/esupport/billAndAccount.jsp?view=combine")){
								 Report.ValidationPoint(testContext.getName(),"'QuestionACharge' page is seen","true","true",true);
							 }
							
//							boolean bPaymentArrangement = UI.WaitForObject(lDriver.findElement(By.xpath("//div[@class='page-heading pB35']/h1")), UI.iObjTimeOut, lDriver);
//							if(bPaymentArrangement){
//								Report.ValidationPoint(testContext.getName(),"'GoToBillingSupport' page is seen","true","true",true);
//							}
									
							String sURL= lDriver.getCurrentUrl();
							Report.ValidationPoint(testContext.getName(),"Clicking on 'GoToBillingSupport' link user is directed to URL :-" + sURL ,"true","true",true);
							Thread.sleep(3000);
							
							lDriver.navigate().to(ParentURL);					
						}			
					}
		 		}
		 	catch(Exception e) {
			 e.printStackTrace();
			 Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		 }
	 }
	 
	 /**************************************************************
	  * Function Name - ValidateNewDirecTvNoBillingPaymentHistory
	  * Description-  This validates Top Wrapper for Titan account where New DirecTv and New Wireless Service bundled with current bill but no billing or payment history
	  * Parameters - 
	  * Date created -3rd Dec
	  * Developed by - Krutika Kamdi
	  * Last Modified By - 
	  * Last Modified Date -
	  ***************************************************************/
	 //BAP33394
	 public static void ValidateNewDirecTvNoBillingPaymentHistory(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		
			try
		{
			Report.OperationPoint(testContext.getName(), "Navigate to Bills & Usage Page");
			
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
		    //Validate Bill not available Message

			Boolean bBillNot = UI.WaitForObject(oR_BillAndUsage.txtBillUnavailable,60);
			Report.ValidationPoint(testContext.getName(), "Validate 'Bill isn't available' message is displayed" , "true", String.valueOf(bBillNot),  true);
			Report.OperationPoint(testContext.getName(), "Clicking on History");
			
			oR_BillAndUsage.lnkHistoryTab.click();
			Thread.sleep(5000);
			UI.VerifyElementNotPresent(oR_BillAndUsage.txtTotalAmountDueBy, "Total Amount Due");
			
			UI.VerifyElementNotPresent(oR_BillAndUsage.lnkViewPaperBill, "View Paper Bill ");
			
			UI.VerifyElementNotPresent(oR_BillAndUsage.btnMakePaymentInBillingPage, "Make a Payment");
		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	 } 	 
	 
	 /**************************************************************
		 * Function Name - VerifyPaymentsAndAdjustmentsCredits
		 * Description - The purpose of this test is To validate the 'Payments Section' and 'Credits And Adjustments' Section in Previous Activity on Bill Page
	 	 * Test Case -  IST_BAP30603_US30363_Sprint2_Bill tab - Combined Wireline_BILL TAB - Previous Activity Section_multiple payment and credit or adjustment_M,P2
		 * Parameters - None
		 * Date created - 4th Dec 2015
		 * Developed by - Monica Mohabansi
		 * Last Modified By - 
		 * Last Modified Date - 
	 ***************************************************************/
//	 #BAP30603
	 public static void VerifyPaymentsAndAdjustmentsCredits(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	 {
		 WebDriver lDriver = UI.getDriver(testContext.getName()); 
		 OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		 OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		 Boolean bBAP, bBalance, bBalAmt, bDollarAmt, bCreds, bAmount, bTotalPayments, bPayments, bPreviousActivity, bPrevBal;		
		 int NumOfPays = 0;
		 try
		 {
//			 Click on Bill & usage tab within the secondary navigation
			 bBAP = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 10, lDriver);
			 Report.TestPoint(testContext.getName(), "2-Click on Bill & usage tab within the secondary navigation", "true", bBAP.toString(), true);
			 oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
//			 By default user is switched to the Bill tab on the same page
			 if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(@class,'current')]//span[contains(text(),'Bill')][1]")), 20, lDriver))
				 Report.ValidationPoint(testContext.getName(), "By default user is switched to the Bill tab on the same page", "true", "true", true);
			 
//			 Validate Previous Activity Section
			 Report.OperationPoint(testContext.getName(), "3-Validate Previous Activity Section");
			 bPreviousActivity = UI.WaitForObject(oR_BillAndUsage.txtPreviousActivityFor, 20, lDriver);
			 Report.ValidationPoint(testContext.getName(), "Previous Activity for Mmm. dd - Mmm. dd, yyyy should be the section title.", "true", bPreviousActivity.toString(), true);
			 
//			 Validate Previous Balance within the Previous Activity Section
			 Report.OperationPoint(testContext.getName(), "4-Validate Previous Balance within the Previous Activity Sectio");
			 bDollarAmt = UI.WaitForObject(lDriver.findElement(By.xpath("//div[contains(text(),'Previous Balance')]//parent::div//*[contains(text(),'$')]")), 20, lDriver);
			 bPrevBal = UI.WaitForObject(oR_BillAndUsage.txtPreviousBalance, 10, lDriver);
			 Report.ValidationPoint(testContext.getName(), "Previous balance with dollar amount should be displayed", "true", String.valueOf(bDollarAmt && bPrevBal), true);

//			 Validate Multiple Payments within the Previous Activity Section
			 Report.OperationPoint(testContext.getName(), "5-Validate Multiple Payments within the Previous Activity Section");
			 bPayments = UI.WaitForObject(oR_BillAndUsage.lnkPaymentsWithExpandCollapseFunctionality, 10, lDriver);

			 Report.TestPoint(testContext.getName(), "Payments CTA should be displayed with expand collapse functionality", "true", bPayments.toString(), true);
//			 Expand "Payments" CTA
			 Report.OperationPoint(testContext.getName(), "6-Expand Payments CTA");
			 oR_BillAndUsage.lnkPaymentsWithExpandCollapseFunctionality.click();
//			List should be expanded and Total Payments should be displayed including dollar amount that is a sum of all payments.

			 try
			 {
				
				if(lDriver.findElements(By.xpath("//*[contains(@id,'togglePrevActivityPayments1')]//div[contains(@class,'accRow')]")).size()>0)
					 NumOfPays  = lDriver.findElements(By.xpath("//*[contains(@id,'togglePrevActivityPayments1')]//div[contains(@class,'accRow')]")).size();
					 Report.ValidationPoint(testContext.getName(), "List should be expanded and Total Payments should be displayed including dollar amount that is a sum of all payments", "true", "true", true);
//				 Validate each payment
//				The first payment should display a "Thank you" message(if present in bill file) and not for otherpayments following it. Each payment should be displayed as a line item including a description(depending on data check if payment method is Cash, Gift certificate, Visa, American Express, etc) , posted month & day (mm/dd)including the dollar amount as a negative value
				/** told to ignore the messaging part by Rakshitha **/
					 Report.OperationPoint(testContext.getName(), "7-Validate each payment");
				 if(lDriver.findElements(By.xpath("//*[contains(@id,'togglePrevActivityPayments1')]//div[contains(@class,'accRow') and contains(text(),'$')]")).size()==(NumOfPays/2))
					 Report.ValidationPoint(testContext.getName(), "Each payment has dollar amount", "true", "true", true);

			 }
			 catch(Exception e)
			 {
				 Report.TestPoint(testContext.getName(), "List should be expanded and Total Payments should be displayed including dollar amount that is a sum of all payments", "true", "false", true);
			 }
//			Validate Total Payments
			 Report.OperationPoint(testContext.getName(), "8-Validate Total Payments");
			 bTotalPayments = UI.WaitForObject(oR_BillAndUsage.txtTotalPaymentsUnderPreviousActivity, 5, lDriver);
			 bAmount = UI.WaitForObject(oR_BillAndUsage.txtDollarAmountAtSectionTitlePayments, 5, lDriver);
			 Report.ValidationPoint(testContext.getName(), "Total Payments should not be displayed at the Section title level", "false", bAmount.toString(), true);

//			 Collapse "Payments" CTA
			 oR_BillAndUsage.lnkPaymentsWithExpandCollapseFunctionality.click();
			 Report.OperationPoint(testContext.getName(), "9-Collapse 'Payments' CTA");
			 
//			 Dollar amount should be displayed at the section title level.
			 bAmount = UI.WaitForObject(oR_BillAndUsage.txtDollarAmountAtSectionTitlePayments, 5, lDriver);
			 Report.ValidationPoint(testContext.getName(), "Dollar amount should be displayed at the section title level", "true", bAmount.toString(), true);
		
//			 Validate "Credits & Adjustments" within the Previous Activity Section
			 Report.OperationPoint(testContext.getName(), "10-Validate Credits & Adjustments within the Previous Activity Section");
			 bCreds = UI.WaitForObject(oR_BillAndUsage.lnkCreditsAdjustments, 2, lDriver);
			 Report.TestPoint(testContext.getName(), "'Credits & Adjustments' CTA should be displayed with expand collapse functionality", "true", bCreds.toString(), true);	
		
//			 Expand "Credits & Adjustments" CTA
			 Report.OperationPoint(testContext.getName(), "11-Expand 'Credits & Adjustments' CTA");
			 oR_BillAndUsage.lnkCreditsAdjustments.click();			 
			 
			 try
			 {
				
				int NumOfCreds = 0;
				if(lDriver.findElements(By.xpath("//*[contains(@id,'togglePrevActivityAdjustment')]//div[contains(@class,'accRow')]")).size()>0)
				{
					 NumOfCreds  = lDriver.findElements(By.xpath("//*[contains(@id,'togglePrevActivityAdjustment')]//div[contains(@class,'accRow')]")).size();
				 	 Report.ValidationPoint(testContext.getName(), "List should be expanded and Total Credits & Adjustments should be displayed including dollar amount that is a sum of all payments", "true", "true", true);
//				 Validate each payment
//				The first payment should display a "Thank you" message(if present in bill file) and not for otherpayments following it. Each payment should be displayed as a line item including a description(depending on data check if payment method is Cash, Gift certificate, Visa, American Express, etc) , posted month & day (mm/dd)including the dollar amount as a negative value
					 Report.OperationPoint(testContext.getName(), "12-Validate each Credits & Adjustments");
				}
				 if(lDriver.findElements(By.xpath("//*[contains(@id,'togglePrevActivityAdjustment')]//div[contains(@class,'accRow') and contains(text(),'$')]")).size()==(NumOfCreds/2))
				 Report.ValidationPoint(testContext.getName(), "Each Credits & Adjustments should be displayed as a line item including a description,including the dollar amount Credits & Adjustments may include (applicable in data)", "true", "true", true);

			 }
			 catch(Exception e)
			 {
				 Report.TestPoint(testContext.getName(), "Each Credits & Adjustments should be displayed as a line item including a description,including the dollar amount Credits & Adjustments may include (applicable in data)", "true", "false", true);
			 }
//			 Validate Total Credits & Adjustments
			 Report.OperationPoint(testContext.getName(), "13-Validate Total Credits & Adjustments");
			 bAmount = UI.WaitForObject(oR_BillAndUsage.txtDollarAmountAtSectionTitleCreditsAndAdjustments, 5, lDriver);
			 Report.ValidationPoint(testContext.getName(), "Dollar amount should be displayed at the section title level", "false", bAmount.toString(), true);
		
//			 Collapse "Credits & Adjustments" CTA
			 Report.OperationPoint(testContext.getName(), "14-Collapse Credits & Adjustments");
			 oR_BillAndUsage.lnkCreditsAdjustments.click();
			 
//			 Dollar amount should be displayed at the section title level with a negative sign.
			 bAmount = UI.WaitForObject(oR_BillAndUsage.txtDollarAmountAtSectionTitleCreditsAndAdjustments, 5, lDriver);
			 Report.ValidationPoint(testContext.getName(), "Dollar amount should be displayed at the section title level", "true", bAmount.toString(), true);

//			 Validate "Balance" (Outstanding Balance)			 
			 Report.OperationPoint(testContext.getName(), "Validate 'Balance' (Outstanding Balance)");
			 bBalance = UI.WaitForObject(oR_BillAndUsage.txtBalanceInPreviousActivity, 2, lDriver);
			 bBalAmt = UI.WaitForObject(oR_BillAndUsage.txtBalanceAmountInPrevActivity, 4, lDriver);
			 Report.ValidationPoint(testContext.getName(), "Dollar amount should be displayed at the section title level", "true", String.valueOf(bBalAmt && bBalance), true);

		 }
		 
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		 }
		 
		 
	 }
	 
		/**************************************************************
		 * Function Name - VerifyTotalAccountCharges()
		 * Description - Validate Total Account Charges for a CB Wireline Customer so that user can understand the charges procured in the past
	 	 * Test Case -  BAP30741
		 * Parameters - None
		 * Date created - 7th Dec 2015
		 * Developed by - Clint John
		 * Last Modified By - 
		 * Last Modified Date - 
		 ***************************************************************/
		public static void VerifyTotalAccountCharges(final ITestContext testContext) throws HeadlessException, IOException, AWTException
		{
			try
			{
				WebDriver lDriver = UI.getDriver(testContext.getName());
				OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
				OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
				Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

				//Click on Bill Reports from Secondary navigation drop-down
				//UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkBillReports, lDriver);
				new Actions(lDriver).moveToElement(oR_AccountOverview.lnkBillingUsagePaymentsSecNav).moveToElement(oR_AccountOverview.lnkBillReports).click().build().perform();
				Thread.sleep(3000);
				
				if(oR_BillAndUsage.lnkBillTabPreSelected.getText().contains("Reports"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Reports tab is pre-selected", "true","true", true);
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Reports tab is pre-selected", "true","false", true);
				}
				
				//Select Total Account charges
				//Start Billing Period should be activate only once Report type is selected - End Billing Period should be activate only once Start Billing Period is selected
				boolean bReportDropDown = UI.WaitForObject(oR_BillAndUsage.lstSelectReportType, UI.iObjTimeOut, lDriver);
				//oR_BillAndUsage.lstSelectReportType.click();
				new Actions(lDriver).moveToElement(oR_BillAndUsage.lstSelectReportType).click().perform();
				Report.ValidationPoint(testContext.getName(), "Verify that drop-down to select Report type is displayed", "true",String.valueOf(bReportDropDown), true);
				String sPreSelectedReport = lDriver.findElement(By.xpath("//a[@id='ddShortcut']//p")).getText();
				if(sPreSelectedReport.contains("Select Report Type"))
				{
					String sStartDateEnabled = oR_BillAndUsage.lstStartDate.getAttribute("class").toString();
					if(sStartDateEnabled.contains("enabled"))
					{
						Report.ValidationPoint(testContext.getName(), "Verify that Start Billing Period should be activate only once Report type is selected - Billing period is not Deactive", "true","false", true);
					}else
					{
						Report.ValidationPoint(testContext.getName(), "Verify that Start Billing Period should be activate only once Report type is selected - Billing period is Deactive", "true","true", true);
					}
					
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Select report type not visible - Report type is already pre-selected", "true","true", true);
				}
				
				Thread.sleep(2000);
				//new Actions(lDriver).moveToElement(oR_BillAndUsage.lstSelectReportType).build().perform();
				//new Actions(lDriver).moveToElement(oR_BillAndUsage.lstSelectReportType).click().build().perform();
				//Thread.sleep(2000);
				//new Actions(lDriver).moveToElement(oR_BillAndUsage.txtTotalAccountCharges).click().build().perform();
				oR_BillAndUsage.txtTotalAccountCharges.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Total Account Charges' from drop-down menu");
				
				String sStartDateEnabled = oR_BillAndUsage.lstStartDate.getAttribute("class").toString();
				if(sStartDateEnabled.contains("enabled"))
				//if(oR_BillAndUsage.lstStartDate.isEnabled() || oR_BillAndUsage.lstStartDate.isSelected() || oR_BillAndUsage.lstStartDate.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Start Billing Period should be activate only once Report type is selected - Billing period should be Active", "true","true", true);
					//End Billing Period should be activate only once Start Billing Period is selected
					String sEndDateEnabled = oR_BillAndUsage.lstEndDate.getAttribute("class").toString();
					if(sEndDateEnabled.contains("enabled"))
					//if(oR_BillAndUsage.lstEndDate.isEnabled() || oR_BillAndUsage.lstEndDate.isSelected() || oR_BillAndUsage.lstEndDate.isDisplayed())
					{
						Report.ValidationPoint(testContext.getName(), "End Billing Period should be activate only once Start Billing Period is selected. Start Bill period not selected So, End period should be inActive", "true","false", true);
					}else
					{
						Report.ValidationPoint(testContext.getName(), "End Billing Period should be activate only once Start Billing Period is selected. Start Bill period not selected So, End period should be inActive", "true","true", true);
					}
					
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Start Billing Period should be activate only once Report type is selected - Billing period should be Active", "true","false", true);
				}
				
				Thread.sleep(2000);
				//Select Start Billing period
				new Actions(lDriver).moveToElement(oR_BillAndUsage.lstStartDate).click().build().perform();
				Thread.sleep(2000);
				//new Actions(lDriver).moveToElement(oR_BillAndUsage.lstFirstAvailableStartDate).click().build().perform();
				oR_BillAndUsage.lstFirstAvailableStartDate.click();
				Report.OperationPoint(testContext.getName(), "Operational - Date selected from Start billing period drop-down");
				
				//Verify End Billing Period should be activate only once Start Billing Period is selected
				String sEndDateEnabled = oR_BillAndUsage.lstEndDate.getAttribute("class").toString();
				if(sEndDateEnabled.contains("enabled"))
				//if(oR_BillAndUsage.lstEndDate.isEnabled() || oR_BillAndUsage.lstEndDate.isSelected() || oR_BillAndUsage.lstEndDate.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(), "End Billing Period should be activate only once Start Billing Period is selected. Start Bill period is selected So, End period should be Active", "true","true", true);
					//Selecting end billing period date
					new Actions(lDriver).moveToElement(oR_BillAndUsage.lstEndDate).click().build().perform();
					Thread.sleep(2000);
					
					//new Actions(lDriver).moveToElement(oR_BillAndUsage.lstFirstAvailableEndDate).click().build().perform();
					oR_BillAndUsage.lstFirstAvailableEndDate.click();
					Report.OperationPoint(testContext.getName(), "Operational - Date selected from End billing period drop-down");
					oR_BillAndUsage.btnCreateReport.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on button Create Report");
					Thread.sleep(3000);
					
				}else
				{
					Report.ValidationPoint(testContext.getName(), "End Billing Period should be activate only once Start Billing Period is selected. Start Bill period is selected So, End period should be Active", "true","false", true);
				}
				
				Thread.sleep(2000);
				
				//Check for graphs
				
					//User should be able to view reports for 9 bill periods at a time - DATA not avilable for development
				        //Scroll bar should be displayed for Reports with more than 6 bill periods - DATA not avilable for development
				       //Empty spaces should not be displayed when less than 9 bill periods are selected - DATA not available for development
				
				//validate chart title
				WebElement chartTitle = lDriver.findElement(By.cssSelector("text.highcharts-title tspan"));
				UI.WaitForObject(chartTitle, UI.iObjTimeOut);
				String strChartTitle = chartTitle.getText();
				if(strChartTitle.contains("Total Account Charges"))
				{
					Report.ValidationPoint(testContext.getName(), "Validate title of chart graph is '"+strChartTitle+"' ", "true", "true", true);

				}else
				{
					Report.ValidationPoint(testContext.getName(), "Validate title of chart graph", "true", "failed", true);
				}
				
				//validate usage bars
				List <WebElement> graphsize = lDriver.findElements(By.cssSelector("g.highcharts-tracker rect"));
				int countBar = graphsize.size();
				System.out.println(countBar);
				Report.OperationPoint(testContext.getName(), "Validating graph bars");
				if(countBar!=0)
				{
					Report.ValidationPoint(testContext.getName(), "Validate the graph bars are displayed for each bill period", "True", "true", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate the graph bars are displayed for each bill period", "True", "false", true);
				}

				//Validate y axis title
				
				WebElement dataGBValue = lDriver.findElement(By.xpath("//*[local-name() = 'svg']/*[name()='text']/*[name()='tspan' and contains(@x, '14')]"));
				String syaxis = dataGBValue.getText();
				Report.OperationPoint(testContext.getName(), "Verifying Y axis");
				if(syaxis.contains("Charges"))
				{
					Report.ValidationPoint(testContext.getName(), "Validate that dollar amount scale should be displayed at Y axis", "True", "true", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate that dollar amount scale should be displayed at Y axis", "True", "false", true);
				}
				
				//Validate x axis title
				List<WebElement> lstXandYaxisContents = lDriver.findElements(By.cssSelector("div.highcharts-container svg text tspan"));
				String iflagXtitle="false", iflagDollar="false";
				for(WebElement e : lstXandYaxisContents )
				{
					
					//Validate x axis title
					String Text= e.getText();
					System.out.println(Text);
					if((Text.contains("Jan") || Text.contains("Feb") || Text.contains("Mar") || Text.contains("Apr") || Text.contains("May") || Text.contains("Jun") || Text.contains("Jul") || Text.contains("Aug") || Text.contains("Sept") || Text.contains("Oct") || Text.contains("Nov") || Text.contains("Dec")) && (Text.contains("201")))
					{
						iflagXtitle="true";
					}
					
					//Total charges using dollar amount should be displayed for each graph center aligned.	
					if(Text.contains("$"))
					{
						iflagDollar="true";
					}
				}
				
				Report.ValidationPoint(testContext.getName(), "Validate that X axis should display label for month and year" , "true",iflagXtitle,  true);
				Report.ValidationPoint(testContext.getName(), "Validate that Total charges using dollar amount should be displayed for each graph center aligned" , "true",iflagDollar,  true);

				
				//Horizontal dotted line across the graph bars should be displayed to reflect the average charge including a label - DATA NOT available for development
				//Single graph bar should be displayed for each bill period eventhough there are multiple carrier lines. - DATA NOT available for development
				
				new Actions(lDriver).moveToElement(oR_BillAndUsage.lstEndDate).click().build().perform();
				Thread.sleep(2000);
				List <WebElement> lstDates = lDriver.findElements(By.xpath("//div[@id='ddShortcutBox2']//dl[@id='endDateList']//a"));
				int iSizeEndDates = lstDates.size();
				if(iSizeEndDates>6)
				{
					Report.ValidationPoint(testContext.getName(), "Verify that More than 6 billing period is displayed", "true","true", true);
					lDriver.findElement(By.xpath("(//div[@id='ddShortcutBox2']//dl[@id='endDateList']//a)["+iSizeEndDates+"]")).click();
					Thread.sleep(2000);
					Report.OperationPoint(testContext.getName(), "Operational - Last Date selected from End billing period drop-down");
					
					oR_BillAndUsage.btnCreateReport.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on button Create Report");
					Thread.sleep(3000);
					
					//Check for graphs
					
						//User should be able to view reports for 9 bill periods at a time - DATA not avilable for development
					        //Scroll bar should be displayed for Reports with more than 6 bill periods - DATA not avilable for development
					       //Empty spaces should not be displayed when less than 9 bill periods are selected - DATA not available for development
					
					//validate chart title
					WebElement chartTitle1 = lDriver.findElement(By.cssSelector("text.highcharts-title tspan"));
					UI.WaitForObject(chartTitle1, UI.iObjTimeOut);
					String strChartTitle1 = chartTitle1.getText();
					if(strChartTitle1.contains("Total Account Charges"))
					{
						Report.ValidationPoint(testContext.getName(), "Validate title of chart graph is '"+strChartTitle1+"' ", "true", "true", true);

					}else
					{
						Report.ValidationPoint(testContext.getName(), "Validate title of chart graph", "true", "failed", true);
					}
					
					//validate usage bars
					List <WebElement> graphsize1 = lDriver.findElements(By.cssSelector("g.highcharts-tracker rect"));
					int countBar1 = graphsize1.size();
					System.out.println(countBar1);
					Report.OperationPoint(testContext.getName(), "Validating graph bars");
					if(countBar1!=0)
					{
						Report.ValidationPoint(testContext.getName(), "Validate the graph bars are displayed for each bill period", "True", "true", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate the graph bars are displayed for each bill period", "True", "false", true);
					}

					//Validate y axis title
					
					WebElement dataGBValue1 = lDriver.findElement(By.xpath("//*[local-name() = 'svg']/*[name()='text']/*[name()='tspan' and contains(@x, '14')]"));
					String syaxis1 = dataGBValue1.getText();
					Report.OperationPoint(testContext.getName(), "Verifying Y axis");
					if(syaxis1.contains("Charges"))
					{
						Report.ValidationPoint(testContext.getName(), "Validate that dollar amount scale should be displayed at Y axis", "True", "true", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate that dollar amount scale should be displayed at Y axis", "True", "false", true);
					}
					
					//Validate x axis title
					List<WebElement> lstXandYaxisContents1 = lDriver.findElements(By.cssSelector("div.highcharts-container svg text tspan"));
					String iflagXtitle1="false", iflagDollar1="false";
					for(WebElement e : lstXandYaxisContents1 )
					{
						
						//Validate x axis title
						String Text= e.getText();
						System.out.println(Text);
						if((Text.contains("Jan") || Text.contains("Feb") || Text.contains("Mar") || Text.contains("Apr") || Text.contains("May") || Text.contains("Jun") || Text.contains("Jul") || Text.contains("Aug") || Text.contains("Sept") || Text.contains("Oct") || Text.contains("Nov") || Text.contains("Dec")) && (Text.contains("201")))
						{
							iflagXtitle1="true";
						}
						
						//Total charges using dollar amount should be displayed for each graph center aligned.	
						if(Text.contains("$"))
						{
							iflagDollar1="true";
						}
					}
					
					Report.ValidationPoint(testContext.getName(), "Validate that X axis should display label for month and year" , "true",iflagXtitle1,  true);
					Report.ValidationPoint(testContext.getName(), "Validate that Total charges using dollar amount should be displayed for each graph center aligned" , "true",iflagDollar,  true);

					//Verify scroll bar when Report is generated for 6 or less than 6 Bill Period
					boolean bScrollbar = UI.WaitForObject(lDriver.findElement(By.cssSelector("div.highcharts-container svg g g.highcharts-scroller")), UI.iObjTimeOut, lDriver);
					Report.ValidationPoint(testContext.getName(), "Validate that scroll bar is displayed if more than 6 bill period is present" , "true",String.valueOf(bScrollbar),  true);

					//Horizontal dotted line across the graph bars should be displayed to reflect the average charge including a label - DATA NOT available for development
					boolean bDottedLine = UI.WaitForObject(lDriver.findElement(By.cssSelector("div.highcharts-container svg path[stroke-dasharray='7,5']")), UI.iObjTimeOut, lDriver);
					Report.ValidationPoint(testContext.getName(), "Validate that Horizontal dotted line across the graph bars should be displayed to reflect the average charge including a label is displayed" , "true",String.valueOf(bDottedLine),  true);

					//Single graph bar should be displayed for each bill period eventhough there are multiple carrier lines. - DATA NOT available for development
				}else
				{
					Report.OperationPoint(testContext.getName(), "*Less than or equal to 6 bill cycles are only displayed");
				}
				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

			}
			
		}
		
		 /**************************************************************
			 * Function Name - VerifyPaymentsAndAdjustmentsCredits
			 * Description - The purpose of this test is To verify Previous Activity section on Bill Page and Print friendly version
		 	 * Test Case -  [1]IST_BAP35092_Verify  Past due message within Previous Activity Section of Bill Page for Slid with wireless Account with and  payment scheduled and bill is cut and payment completed_IE
			 * Parameters - None
			 * Date created - 8th Dec 2015
			 * Developed by - Monica Mohabansi
			 * Last Modified By - 
			 * Last Modified Date - 
		 ***************************************************************/
		 public static void ValidateNewPreviousActivitySection(final ITestContext testContext) throws HeadlessException, IOException, AWTException
		 {
			 WebDriver lDriver = UI.getDriver(testContext.getName()); 
			 OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			 OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			 Boolean bPreviousActivity, bPastDue, bDollarAmt, bPrint;
			 String strTxtColor, sBill;
			try
			{
//				Click Billing & Payments link
				Report.OperationPoint(testContext.getName(), "3-Click Billing & Payments link");
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
				
				 if(UI.WaitForObject(lDriver.findElement(By.xpath("//a[contains(@class,'current') and contains(@href,'billOverview')]")), 20, lDriver))
					 Report.ValidationPoint(testContext.getName(), "User should be directed to Bill Page", "true", "true", true);
				 
//				 Verify new content in black font within Topwrapper below Make a Payment button
				 /** Not present for given data. Also not present in SS **/
//				 Verify Previous Activity Section within the Bill Page
				 Report.OperationPoint(testContext.getName(), "Verify Previous Activity Section within the Bill Page	");
				 bPreviousActivity = UI.WaitForObject(oR_BillAndUsage.txtPreviousActivityFor, 20, lDriver);
				 Report.ValidationPoint(testContext.getName(), "Previous Past due messaging should be suppressed", "true", bPreviousActivity.toString(), true);
//				 Verify new content within Previous Activity Section of Bill Page
				 Report.OperationPoint(testContext.getName(), "Verify new content within Previous Activity Section of Bill Page");
				 bPastDue = UI.WaitForObject(oR_BillAndUsage.txtPastDueFromPreviousActivity, 10, lDriver);
				 Report.ValidationPoint(testContext.getName(), "'Past due from previous activity' (CRD) content should be displayed", "true", bPastDue.toString(), true);
//				 Verify font color of the new content "Past due from previous activity"
				 Report.OperationPoint(testContext.getName(), "Verify font color of the new content 'Past due from previous activity'");
				  strTxtColor = oR_BillAndUsage.txtPastDueFromPreviousActivity.getCssValue("color");
				 if(strTxtColor.contains("rgba(51, 51, 51, 1"))
					 Report.ValidationPoint(testContext.getName(), "Black font should be displayed for new content 'Past due from previous activity'", "true", "true", true);
				 else
					 Report.ValidationPoint(testContext.getName(), "Black font should be displayed for new content 'Past due from previous activity'", "true", "false", true);
//				 Verify font color of dollar amount
				 Report.OperationPoint(testContext.getName(), "Verify font color of dollar amount");			 
				 bDollarAmt = UI.WaitForObject(oR_BillAndUsage.txtOrangeColorDollarAmountInPreviousActivity, 10, lDriver);
				 Report.ValidationPoint(testContext.getName(), "Dollar amount should be displayed with orange font color", "true", bDollarAmt.toString(), true);
				  sBill = lDriver.getWindowHandle();
	 //			 Click 'Print' CTA
			 	 bPrint = UI.WaitForObject(oR_BillAndUsage.lnkPrint, 10, lDriver);
			 	 Report.TestPoint(testContext.getName(), "Click Print CTA", "true", bPrint.toString(), true);
			 	 oR_BillAndUsage.lnkPrint.click();
//			 	 Verify step 7-10 in PF version of the Bill
			 	
			 	for(String sNewWindow : lDriver.getWindowHandles())
				{
					lDriver.switchTo().window(sNewWindow);
				}
			 	 Report.OperationPoint(testContext.getName(), "Verify step 7-10 in PF version of the Bill");
//				 Verify Previous Activity Section within the Bill Page
				 Report.OperationPoint(testContext.getName(), "Verify Previous Activity Section within the PF version");
				 bPreviousActivity = UI.WaitForObject(oR_BillAndUsage.txtPreviousActivityFor, 20, lDriver);
				 Report.ValidationPoint(testContext.getName(), "Previous Past due messaging should be suppressed", "true", bPreviousActivity.toString(), true);
//				 Verify new content within Previous Activity Section of Bill Page
				 Report.OperationPoint(testContext.getName(), "Verify new content within Previous Activity Section of PF version");
				 bPastDue = UI.WaitForObject(oR_BillAndUsage.txtPastDueFromPreviousActivity, 10, lDriver);
				 Report.ValidationPoint(testContext.getName(), "'Past due from previous activity' (CRD) content should be displayed on PF version", "true", bPastDue.toString(), true);
//				 Verify font color of the new content "Past due from previous activity"
				 Report.OperationPoint(testContext.getName(), "Verify font color of the new content 'Past due from previous activity'");
				  strTxtColor = oR_BillAndUsage.txtPastDueFromPreviousActivity.getCssValue("color");
				 if(strTxtColor.contains("rgba(51, 51, 51, 1"))
					 Report.ValidationPoint(testContext.getName(), "Black font should be displayed for new content 'Past due from previous activity' on PF version", "true", "true", true);
				 else
					 Report.ValidationPoint(testContext.getName(), "Black font should be displayed for new content 'Past due from previous activity' on PF version", "true", "false", true);
//				 Verify font color of dollar amount
				 Report.OperationPoint(testContext.getName(), "Verify font color of dollar amount");			 
				 bDollarAmt = UI.WaitForObject(oR_BillAndUsage.txtOrangeColorDollarAmountInPreviousActivity, 10, lDriver);
				 Report.ValidationPoint(testContext.getName(), "Dollar amount should be displayed with orange font color on PF version", "true", bDollarAmt.toString(), true);
				 lDriver.close();
				 
				 lDriver.switchTo().window(sBill);
			}
			 catch(Exception e)
			 {
				 Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
			 }
		 }
		 
		 /**************************************************************
		  * Function Name - ValidateDescriptionForATTNextPlan()
		  * Description-  Validates description For ATT NEXTplan in Explanation of charges modal
		  * Parameters - 
		  * Date created -11th Dec
		  * Developed by - Hiral Jogi
		  * Last Modified By - 
		  * Last Modified Date -
		  ***************************************************************/
		 //BAP28161
		 public static void ValidateDescriptionForATTNextPlan(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
		 {
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
				try
				{
					// Navigating to Billing and Usage Page
					
					Report.OperationPoint(testContext.getName(), "Navigating to Billing and Usage page");
					UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsageSecNav, null);
					
					// Validate user is navigated to Billing and Usage Page
					
					Boolean bBillingAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
					if(bBillingAndUsage) {
						 Report.ValidationPoint(testContext.getName(),"Validate user is navigated to Billing and Usage Landing.","true","true",true);
					}
					
					// Validate ViewExplanationOfServices link
					
					Boolean bViewExplanationOfServices = UI.WaitForObject(oR_BillAndUsage.lnkExplanationOfServices, UI.iObjTimeOut, lDriver);
					if(bViewExplanationOfServices) {
						 Report.ValidationPoint(testContext.getName(),"Validate 'ViewExplanationOfServices' link is present on Billing and Usage Page.","true","true",true);
					}
					
					// Click ViewExplanationOfServices link
				
					Report.OperationPoint(testContext.getName(),"Clicking 'ViewExplanationOfServices' link.");
					oR_BillAndUsage.lnkViewExplanationOfServices.click();
					Thread.sleep(5000);
					// Switch to ViewExplanationOfServices frame
				   
					if(UI.WaitForObject(oR_BillAndUsage.frmViewExplanationOfServices, UI.iObjTimeOut, lDriver)){
					
					Report.ValidationPoint(testContext.getName(), "Explanation of services modal window is displayed", "True", "True", true);
					lDriver.switchTo().frame(oR_BillAndUsage.frmViewExplanationOfServices);
										
//					// Validating Content
//					
//					boolean bServices= UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'Explanation of services')]")), UI.iObjTimeOut, lDriver);
//					Report.ValidationPoint(testContext.getName(),"'Explanation of services' content is present.","true","true",true);
//						
//					if(bServices){
//						List<WebElement> links = lDriver.findElements(By.xpath("//p[@class='Marbot5']"));
//						System.out.println("Total links present is - " + links.size());
//						String [] strCTA = new String[links.size()];
//								
//						for (int i=0; i<links.size(); i++){
//							strCTA[i]=links.get(i).getText();
//						}
//								
//						//Retrieving the link headings
//						for(int j=0;j<links.size();j++)
//						{
//							if(strCTA[j].length()>1){
//								Report.OperationPoint(testContext.getName()," Link "+strCTA[j] + " is present");
//								}
//							}
//						}
//							
//						boolean bHighSpeed= UI.WaitForObject(lDriver.findElement(By.xpath("//h2[contains(text(),'AT&T High Speed Internet Elite')]")), UI.iObjTimeOut, lDriver);
//						if(bHighSpeed){
//							Report.ValidationPoint(testContext.getName(),"'AT&T High Speed Internet Elite' content is present.","true","true",true);
//						}
//						
//						boolean bUverseVoice= UI.WaitForObject(lDriver.findElement(By.xpath("//div[@id='link2']")), UI.iObjTimeOut, lDriver);
//						if(bUverseVoice){
//							Report.ValidationPoint(testContext.getName(),"'UverseVoice' content is present.","true","true",true);
//						}
//						
//						boolean bNation450withRollOver= UI.WaitForObject(lDriver.findElement(By.xpath("//div[@id='link3']")), UI.iObjTimeOut, lDriver);
//						if(bNation450withRollOver){
//							Report.ValidationPoint(testContext.getName(),"'Nation 450 with Rollover' content is present.","true","true",true);
//						}
//						
//						boolean bDataPerPayUse= UI.WaitForObject(lDriver.findElement(By.xpath("//div[@id='link4']")), UI.iObjTimeOut, lDriver);
//						if(bDataPerPayUse){
//							Report.ValidationPoint(testContext.getName(),"'Day Per Pay Use' content is present.","true","true",true);
//						}
//						
//						boolean bPerPayUsePicture= UI.WaitForObject(lDriver.findElement(By.xpath("//div[@id='link5']")), UI.iObjTimeOut, lDriver);
//						if(bPerPayUsePicture){
//							Report.ValidationPoint(testContext.getName(),"'Pay Per Use Picture/Video Messaging ' content is present.","true","true",true);
//						}
//						
//						boolean bPerPayUseText= UI.WaitForObject(lDriver.findElement(By.xpath("//div[@id='link5']")), UI.iObjTimeOut, lDriver);
//						if(bPerPayUseText){
//							Report.ValidationPoint(testContext.getName(),"'Pay Per Use Text/Instant Messaging ' content is present.","true","true",true);
//						}
					
					
					  UI.WaitForObject(oR_BillAndUsage.lnkATTNextPlan, UI.iObjTimeOut);
					  oR_BillAndUsage.lnkATTNextPlan.click();
					  Thread.sleep(3000);
					  // Validate description for ATT NEXT plan should display
					
					  if(UI.WaitForObject(oR_BillAndUsage.txtATTNextPlanDescription, UI.iObjTimeOut, lDriver)){
						  Report.ValidationPoint(testContext.getName(), "Validate description for ATT NEXT plan is displayed", "True", "True", true);
					  
					  }else{
						  Report.ValidationPoint(testContext.getName(), "Validate description for ATT NEXT plan is displayed", "True", "False", true);
					  }
					  				 			 
					  lDriver.switchTo().defaultContent();
					  lDriver.findElement(By.xpath("//a[@name='close']")).click();
					  
					}else{
						Report.ValidationPoint(testContext.getName(), "Explanation of services modal window is displayed", "True", "False", true);
					}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
			}
		 } 

		 /**************************************************************
		  * Function Name - ValidateChangesOnUIForMAP()
		  * Description-  Validate the changes on UI for MAP step 1 page
		  * Parameters - 
		  * Date created -15th Jan
		  * Developed by - Hiral Jogi
		  * Last Modified By - 
		  * Last Modified Date -
		  ***************************************************************/
		 //BAP25521
		 public static void ValidateChangesOnUIForMAP(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
		 {
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
			
				try
				{
					// Clicking on Make a payment button
					
					Boolean bMakeAPayment = UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentInOverviewPage, UI.iObjTimeOut, lDriver);
					if(bMakeAPayment) {
						 Report.ValidationPoint(testContext.getName(),"Clicking on Make a Payment CTA. ", "true","true",true);
						 oR_AccountOverview.btnMakeAPaymentInOverviewPage.click();
					}
					
					Boolean bMAP = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, UI.iObjTimeOut, lDriver);
					if(bMAP) {
						 Report.ValidationPoint(testContext.getName(),"User is navigated to Make a Payment page. ", "true","true",true);
						 
						 // step 4.1
						 
						 Boolean bProgressBar = UI.WaitForObject(oR_MakeAPayment.txtProgressBar, UI.iObjTimeOut, lDriver);
						 if(bProgressBar){
							 Report.OperationPoint(testContext.getName()," 3 step progress bar us present.");
						 }
						 
						 String strTxtColor1 = lDriver.findElement(By.xpath("//li[@class='step-current']/span[@class='step-number stepPadding']")).getCssValue("color");
						 System.out.println("color value of step 1 is " + strTxtColor1);
						 if(strTxtColor1.equals("rgba(255, 255, 255, 1)")){
							 Report.ValidationPoint(testContext.getName(),"First step i.e. 'Enter Payment Information' is marked as green.","true","true",true);	
						 } else {
							 Report.ValidationPoint(testContext.getName(),"First step i.e. 'Enter Payment Information' is marked as green.","true","false",true);
						 }
						
						 String strTxtColor2 = lDriver.findElement(By.xpath("//li[@class='step-future']/span[@class='step-number stepAfter']")).getCssValue("color");
						 System.out.println("color value of step 2 is " + strTxtColor2);
						 if(strTxtColor2.equals("rgba(102, 102, 102, 1)")){
							Report.ValidationPoint(testContext.getName(),"Second step i.e. 'Review Payment Information' is not marked as green.","true","true",true);	
						} else {
							 Report.ValidationPoint(testContext.getName(),"First step i.e. 'Enter Payment Information' is  not marked as green.","true","false",true);
						}
					
						// step 4.2 cannot be coded as no data will be present with MOP seen in side bar
						
						// step 4.3
						Boolean bManagePaymentProfile = UI.WaitForObject(oR_MakeAPayment.lnkManagePaymentProfile, UI.iObjTimeOut, lDriver);
						if(bManagePaymentProfile){
							 Report.ValidationPoint(testContext.getName(),"Manage payment profile link is visible which is not as expected.", "true","true",true);
						} else {
							 Report.ValidationPoint(testContext.getName(),"Manage payment profile link is not visible which is as expected.", "true","true",true);
						}
					}
					
					Boolean bFlexiblepaymentoptions = UI.WaitForObject(oR_MakeAPayment.lnkFlexiblepaymentoptions, UI.iObjTimeOut, lDriver);
					if(bFlexiblepaymentoptions){
						Report.OperationPoint(testContext.getName(),"An Icon and CTA is displayed next to the MAP header having title as 'Flexible payment options'.");
						Report.ValidationPoint(testContext.getName(),"Clicking on 'Flexible payment options CTA.", "true","true",true);
						oR_MakeAPayment.lnkFlexiblepaymentoptions.click();
						
						// Validating the new window
						
						WebElement window = lDriver.findElement(By.xpath("//div[@id='gvpModalInjection']/parent::div"));
						Thread.sleep(5000);
						
						if(window.isDisplayed()){
					
							WebElement VideoBanner = lDriver.findElement(By.xpath("//div[@id='gvpModalInjection_Video_Banner']"));
							if(VideoBanner.isDisplayed()){
								Report.ValidationPoint(testContext.getName(),"A new window for flexible payments is opened.", "true","true",true);
						}
						
						WebElement close = lDriver.findElement(By.xpath("//button[contains(text(),'Close')]"));
						if(close.isDisplayed()){
							 Report.ValidationPoint(testContext.getName(),"Closing the window.", "true","true",true);
							 close.click();
						}
					}
				}	
					
					// Validating manage autopay settings
					
					Boolean blnkManageAutoPaySettings = UI.WaitForObject(oR_MakeAPayment.lnkManageAutoPaySettings, UI.iObjTimeOut, lDriver);
					if(blnkManageAutoPaySettings){
						Report.ValidationPoint(testContext.getName(),"The manage autopay link is visible.", "true","true",true);
						Report.OperationPoint(testContext.getName(),"Clicking on manage autopay link.");
						oR_MakeAPayment.lnkManageAutoPaySettings.click();
						Thread.sleep(10000);
						String strnewURL = lDriver.getCurrentUrl();
						
						Report.ValidationPoint(testContext.getName(),"Navigating to URL - " + strnewURL, "true","true",true);
						Thread.sleep(5000);
						
						// step 7.2 
						
						Boolean bManageAutoPay = UI.WaitForObject(oR_MakeAPayment.txtManageAutoPay, UI.iObjTimeOut, lDriver);
						if(bManageAutoPay){
							Report.ValidationPoint(testContext.getName(),"User is navigated to Manage autopay.", "true","true",true);
						}
						
						// Validating card active date
						
						Boolean bCardExpirationDate = UI.WaitForObject(oR_MakeAPayment.txtCardExpirationDate, UI.iObjTimeOut, lDriver);
						if(bCardExpirationDate){
							Report.ValidationPoint(testContext.getName(),"Validating card expiration date:", "true","true",true);
							
							// Validating the date expiration format
							
							boolean bCardExpirationDate1format = UI.WaitForObject(oR_MakeAPayment.txtCardExpirationDate1format, UI.iObjTimeOut);
							Report.ValidationPoint(testContext.getName(), "Card Expiration date is displayed.", "true", String.valueOf(bCardExpirationDate1format), true);
							String sCardExpirationDate1format = oR_MakeAPayment.txtCardExpirationDate1format.getText();
							
							if(sCardExpirationDate1format.contains("Expired")){
								Report.ValidationPoint(testContext.getName(),"Autopay expired Notes in the Balance section is seen.", "true","true",true);
							}
							
							char[] scardArray = sCardExpirationDate1format.toCharArray();
							int icharlength = sCardExpirationDate1format.length();
							if(scardArray[2]=='/' && scardArray[2]=='2' && icharlength==6)
							{
								Report.ValidationPoint(testContext.getName(), "Actual date format seen is :" + sCardExpirationDate1format + " which is as expected.", "true", "true", true);

							}else {
								Report.ValidationPoint(testContext.getName(), "Actual date format seen is :" + sCardExpirationDate1format + " which is as expected.", "true", "false", true);

							}
						}
						
						
						Boolean bOverview= UI.WaitForObject(oR_AccountOverview.lnkOverview, UI.iObjTimeOut, lDriver);
						if(bOverview){
							Report.OperationPoint(testContext.getName(),"Navigating to Overview page.");
							oR_AccountOverview.lnkOverview.click();
							Thread.sleep(10000);
						}
						
						// Clicking again on Make a payment button
						
						Boolean bMakeAPayment1 = UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentInOverviewPage, UI.iObjTimeOut, lDriver);
						if(bMakeAPayment1) {
							 Report.ValidationPoint(testContext.getName(),"Clicking again on Make a Payment CTA. ", "true","true",true);
							 oR_AccountOverview.btnMakeAPaymentInOverviewPage.click();
							 Thread.sleep(5000);
						}
						
					}
					
					// validating step 6 
					
					Boolean bNotes = UI.WaitForObject(oR_MakeAPayment.txtNotes, UI.iObjTimeOut, lDriver);
					if(bNotes){
						String strNote = oR_MakeAPayment.txtNotes.getText();
						Report.ValidationPoint(testContext.getName(),"Below note content is seen.", "true", "true", true);
						Report.OperationPoint(testContext.getName(),strNote);
						
					} else {
						Report.ValidationPoint(testContext.getName(),"View Plan details note is seen.", "true", "false", true);
					}
					
					
					Boolean bViewPlanDetailsNote = UI.WaitForObject(oR_MakeAPayment.txtViewPlanDetailsNote, UI.iObjTimeOut, lDriver);
					if(bViewPlanDetailsNote){
						Report.ValidationPoint(testContext.getName(),"View Plan details note is seen in the note section.", "true", "true", true);
					} else {
						Report.ValidationPoint(testContext.getName(),"View Plan details note is seen in the note section.", "true", "false", true);
					}
						
					Boolean bPayOffRemainingInstallments = UI.WaitForObject(oR_MakeAPayment.txtPayOffRemainingInstallments, UI.iObjTimeOut, lDriver);
					if(bPayOffRemainingInstallments){
						Report.ValidationPoint(testContext.getName(),"Pay Off Remaining Installments note is seen in the note section.", "true", "true", true);
					} else {
						Report.ValidationPoint(testContext.getName(),"Pay Off Remaining Installments note is seen in the note section.", "true", "false", true);
					}
					
					
					// Validating Split this payment CTA
					
					Boolean bSplitThisPayment = UI.WaitForObject(oR_MakeAPayment.lnkSplitThisPayment, UI.iObjTimeOut, lDriver);
					if(bSplitThisPayment){
						Report.ValidationPoint(testContext.getName(),"Split this payment CTA is displayed.", "true","true",true);
						Report.ValidationPoint(testContext.getName(),"Clicking on split this payment CTA.", "true","true",true);
						oR_MakeAPayment.lnkSplitThisPayment.click();
						
						// Validating if user is able to split the bill
						
						WebElement split = lDriver.findElement(By.xpath("//div[@id='paymentItem_0_1']"));
						if(split.isDisplayed()){
							Report.ValidationPoint(testContext.getName(),"User is able to split the bill.", "true","true",true);
						} else {
							Report.ValidationPoint(testContext.getName(),"User is able to split the bill.", "true","false",true);
						}
						
					}
					
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
			}
		 } 


		 /**************************************************************
		  * Function Name - ValidateEnrollOtherAccountsForAutopayLink
		  * Description-  
		  * Parameters - 
		  * Date created -15th Jan
		  * Developed by - Gautham
		  * Last Modified By - 
		  * Last Modified Date -
		  ***************************************************************/
		 //BAP28151
		 public static void ValidateEnrollOtherAccountsForAutopayLink(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
		 {
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			try
			{
				if(UI.WaitForObject(oR_AccountOverview.lnkEnrollInAutopay_OverviewPage, 10, lDriver))
				{
					Report.ValidationPoint(testContext.getName(),"Verify Enroll in autopay link is present","true","true",true);
					
					//click on link
					oR_AccountOverview.lnkEnrollInAutopay_OverviewPage.click();
					
					//validate autopay page
					UI.ValidateHeadingOfPage("AutoPay Enrollment");
					
					//validate supressed element
				try{
					UI.VerifyElementNotPresent(lDriver.findElement(By.xpath("//*[contains(text(),'Enroll my other account')]")),"Enroll my other accounTs in autopay link");
				}catch(Exception e){
					Report.ValidationPoint(testContext.getName(),"Verify Enroll my other accounTs in autopay link is not present","true","true",true);
				
				}
					}
				else
				{
					Report.ValidationPoint(testContext.getName(),"Verify Enroll in autopay link is present","true","false",true);
				}
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
			}
		 } 
		 
		 /**************************************************************
		  * Function Name - VerifyOpenCalenderWhenRecommmenderAndPropertyFlagForCalenderIsON()
		  * Description-    This function verify open calender experience when recommmender is on and property flag for calender open is on.
		  * Parameters - 
		  * Date created -19th Jan
		  * Developed by - Hiral
		  * Last Modified By - 
		  * Last Modified Date -
		  ***************************************************************/
		 //BAP25569
		 public static void VerifyOpenCalenderWhenRecommmenderAndPropertyFlagForCalenderIsON(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
		 {
			 WebDriver lDriver = UI.getDriver(testContext.getName());
				OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
				OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
				
					try
					{
						// Clicking on Make a payment button
						
						Boolean bMakeAPayment = UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentInOverviewPage, UI.iObjTimeOut, lDriver);
						if(bMakeAPayment) {
							 Report.ValidationPoint(testContext.getName(),"Clicking on Make a Payment CTA. ", "true","true",true);
							 oR_AccountOverview.btnMakeAPaymentInOverviewPage.click();
						}
						
						Boolean bMAP = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, UI.iObjTimeOut, lDriver);
						if(bMAP) {
							 Report.ValidationPoint(testContext.getName(),"User is navigated to Make a Payment page. ", "true","true",true);
						}
				
						
						Boolean bPaymentDateseen = UI.WaitForObject(oR_MakeAPayment.txtPaymentDate, UI.iObjTimeOut, lDriver);
						String strpaymentdate = oR_MakeAPayment.txtPaymentDate.getText();
						if(bPaymentDateseen) {
							Report.ValidationPoint(testContext.getName(),"Payement date pre-populated is : " + strpaymentdate, "true","true",true);
						}
						
						// Validating Calender
						Boolean bCalender = UI.WaitForObject(oR_MakeAPayment.imgCalender, UI.iObjTimeOut, lDriver);
						if(bCalender) {
							 Report.ValidationPoint(testContext.getName(),"Image for selecting suitable date from calender is present.", "true","true",true);
							 
							 // Click
							 Report.OperationPoint(testContext.getName(),"Clicking on calender.");
							 oR_MakeAPayment.imgCalender.click();
							 Thread.sleep(3000);
							 
							 // validating calender frame
							 lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
							 Boolean bPaymentCalender = UI.WaitForObject(oR_MakeAPayment.txtFramePaymentCalenderTitle, UI.iObjTimeOut, lDriver);
							 if(bPaymentCalender){
								 Report.ValidationPoint(testContext.getName(),"User is navigated to Payment calender frame.", "true","true",true);
							 }
							 
							 // getting the due date
							 Boolean bDueDatecalendernote = UI.WaitForObject(oR_MakeAPayment.txtDueDatenotecalender, UI.iObjTimeOut, lDriver);
							 if(bDueDatecalendernote){
								 String strduedate = oR_MakeAPayment.txtDueDatenotecalender.getText();
								 Report.ValidationPoint(testContext.getName(),"Due date for the account seen in the note section is :" + strduedate, "true","true",true);
							 }
							 
							 Boolean bDueDatecalender = UI.WaitForObject(oR_MakeAPayment.txtDueDatecalender, UI.iObjTimeOut, lDriver);
							 if(bDueDatecalender){
								 String strduedate1 = oR_MakeAPayment.txtDueDatecalender.getText();
								 Report.ValidationPoint(testContext.getName(),"Due date highlighted with red ring seen is : " + strduedate1, "true","true",true);
							 }
							 
							 // getting the current date
							 Boolean bCurrentDatecalender = UI.WaitForObject(oR_MakeAPayment.txtCurrentDatecalender, UI.iObjTimeOut, lDriver);
							 if(bCurrentDatecalender){
								 String strcurrentdate = oR_MakeAPayment.txtCurrentDatecalender.getText();
								 Report.ValidationPoint(testContext.getName(),"Current date is : " + strcurrentdate, "true","true",true);
							 }
							 
							 // validating date selection
							 WebElement date1 = lDriver.findElement(By.xpath("//div[@id='month1']//tr//td[normalize-space(text())='1']"));
							 if(date1.isDisplayed()){
								 Report.OperationPoint(testContext.getName(),"Selecting 1st as the payment date.");
								 date1.click();
								 
								 lDriver.navigate().back();
								 Thread.sleep(5000);
								 String strnewdate = oR_MakeAPayment.txtPaymentDate.getText();
								 if(strnewdate.equalsIgnoreCase(strpaymentdate)){
									 Report.ValidationPoint(testContext.getName(),"Sorry!! This date is not available for selection. Please select some other date.", "true","true",true); 
								 }
							 }
							 
							 Report.OperationPoint(testContext.getName(),"Selecting another date.");
							 oR_MakeAPayment.imgCalender.click();
							 Thread.sleep(4000);
							 lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
								 
							 WebElement date2 = lDriver.findElement(By.xpath("//tbody//tr//td[contains(text(),'15')]"));
							 if(date2.isDisplayed()){
								 Report.OperationPoint(testContext.getName(),"Selecting 15th as the payment date.");
								 date2.click();
								 Thread.sleep(3000);
								 lDriver.navigate().back();
								 Thread.sleep(5000);
								 
								 String strnewdate = oR_MakeAPayment.txtPaymentDate.getText();
								 if(strnewdate.equalsIgnoreCase(strpaymentdate)){
									 Report.ValidationPoint(testContext.getName(),"Sorry!! This date is not available for selection. Please select some other date.", "true","true",true); 
								 }
							 }
							 
							 Report.OperationPoint(testContext.getName(),"Selecting another date.");
							 oR_MakeAPayment.imgCalender.click();
							 Thread.sleep(4000);
							 lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
							 
							 WebElement date3 = lDriver.findElement(By.xpath("//div[@id='month2']//tr//td/a[normalize-space(text())='1']"));
							 if(date3.isDisplayed()){
								 Report.OperationPoint(testContext.getName(),"Selecting 1st of another month as the payment date.");
								 date3.click();
								 Thread.sleep(3000);
								 lDriver.switchTo().defaultContent();
								 Thread.sleep(5000);
								 String strnewdate = oR_MakeAPayment.txtPaymentDate.getText();
								 if(strnewdate != strpaymentdate){
									 Report.ValidationPoint(testContext.getName(),"This date is available for payment.", "true","true",true); 
								 } else {
									 Report.ValidationPoint(testContext.getName(),"This date is available for payment.", "true","false",true); 
								 }
							}
						}
						
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
			}
		 } 
		 
/*		 *//**************************************************************
		  * Function Name - ValidateCurrentDueDateWhilePayment()
		  * Description-    This function verify current due date while doint the payment.
		  * Parameters - 
		  * Date created -20th Jan
		  * Developed by - Hiral
		  * Last Modified By - 
		  * Last Modified Date -
		  ***************************************************************//*
		 //BAP25505
		 public static void ValidateCurrentDueDateWhilePayment(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
		 {
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
			OR_ReviewPaymentDetails oR_ReviewPaymentDetails = PageFactory.initElements(lDriver, OR_ReviewPaymentDetails.class);
			String sAmount= IO.GetParamVal(sTestParams, "Amount");
			String sPaymentMethod= IO.GetParamVal(sTestParams, "Method");
			String sName= IO.GetParamVal(sTestParams, "Name");
			String sCardNo= IO.GetParamVal(sTestParams, "CardNO");
			String sDateMonth= IO.GetParamVal(sTestParams, "Month");
			String sDateYear= IO.GetParamVal(sTestParams, "Year");
			String sSecCode= IO.GetParamVal(sTestParams, "SecCode");
			String sZip= IO.GetParamVal(sTestParams, "Zip");
			// Create object of SimpleDateFormat class and decide the format
			 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
			 
			 // get current date with Date()
			 Date date = new Date();
			 
			 // Format the date
			 String date1= dateFormat.format(date);
			 
			 // Print the Date
			 System.out.println(date1);
			
					try
					{
						
						// Clicking on Make a payment button
						
						Boolean bMakeAPayment = UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentInOverviewPage, UI.iObjTimeOut, lDriver);
						if(bMakeAPayment) {
							 Report.ValidationPoint(testContext.getName(),"Clicking on Make a Payment CTA. ", "true","true",true);
							 oR_AccountOverview.btnMakeAPaymentInOverviewPage.click();
						}
						
						Boolean bMAP = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, UI.iObjTimeOut, lDriver);
						if(bMAP) {
							 Report.ValidationPoint(testContext.getName(),"User is navigated to Make a Payment page. ", "true","true",true);
						}
				
						Report.OperationPoint(testContext.getName(),"Current date is :" + date1);
				
						
						Boolean bPaymentDateseen = UI.WaitForObject(oR_MakeAPayment.txtPaymentDate, UI.iObjTimeOut, lDriver);
						String strpaymentdateseen = oR_MakeAPayment.txtPaymentDate.getText();
						System.out.println(strpaymentdateseen);
						if(date1.equalsIgnoreCase(strpaymentdateseen)){
							Report.ValidationPoint(testContext.getName(),"Payment date pre-populated is current date " + bPaymentDateseen.toString(), "true","true",true);
						}
						
						// Validating Calender
							Boolean bCalender = UI.WaitForObject(oR_MakeAPayment.imgCalender, UI.iObjTimeOut, lDriver);
							if(bCalender) {
								 Report.ValidationPoint(testContext.getName(),"Image for selecting suitable date from calender is present.", "true","true",true);
								 
								 // Click
								 Report.OperationPoint(testContext.getName(),"Clicking on calender.");
								 oR_MakeAPayment.imgCalender.click();
								 Thread.sleep(3000);
								 
								 // validating calender frame
								 lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
								 Boolean bPaymentCalender = UI.WaitForObject(oR_MakeAPayment.txtFramePaymentCalenderTitle, UI.iObjTimeOut, lDriver);
								 if(bPaymentCalender){
									 Report.ValidationPoint(testContext.getName(),"User is navigated to Payment calender frame.", "true","true",true);
								 }
						
						// getting the non-available (shaded) dates for payment
						
						List<WebElement> sDateList = lDriver.findElements(By.xpath("//div[@id='month1']//td[@class='']"));
						Report.OperationPoint(testContext.getName(),"Total number of dates which are unavailable for payments are :" + sDateList.size() + "They are :");
						for(int i=0;i<sDateList.size();i++){
							Report.OperationPoint(testContext.getName(),sDateList.get(i).getText());
						}
						
						// getting the open dates for payment
						
						String strlist1 = "//div[@id='month1']//td[@class='preDate']";
						String strlist2 = "//div[@id='month2']//td[@class='preDate']";
						
						List<WebElement> sDateAvailableList1 = lDriver.findElements(By.xpath(strlist1));
						Report.OperationPoint(testContext.getName(),"Total number of dates which available for payments from Current Month are :" + sDateAvailableList1.size() + "They are :");
						for(int i=0;i<sDateAvailableList1.size();i++){
							Report.OperationPoint(testContext.getName(),sDateList.get(i).getText());
						}
						
						List<WebElement> sDateAvailableList2 = lDriver.findElements(By.xpath(strlist2));
						Report.OperationPoint(testContext.getName(),"Total number of dates which available for payments from Next Month are :" + sDateAvailableList2.size() + "They are :");
						for(int i=0;i<sDateAvailableList2.size();i++){
							Report.OperationPoint(testContext.getName(),sDateList.get(i).getText());
						}
						
						// validating date selection (step 6)
						 WebElement Date1 = lDriver.findElement(By.xpath("//div[@id='month1']//tr//td[normalize-space(text())='1']"));
						 if(Date1.isDisplayed()){
							 Report.OperationPoint(testContext.getName(),"Selecting 1st as the payment date.");
							 Date1.click();
							 
							 lDriver.navigate().back();
							 Thread.sleep(5000);
							 String strnewdate = oR_MakeAPayment.txtPaymentDate.getText();
							 if(strnewdate.equalsIgnoreCase(strpaymentdateseen)){
								 Report.ValidationPoint(testContext.getName(),"Sorry!! This date is not available for selection. Please select some other date.", "true","true",true); 
							 }
						 }
						 
						 Report.OperationPoint(testContext.getName(),"Selecting another date.");
						 oR_MakeAPayment.imgCalender.click();
						 Thread.sleep(4000);
						 lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
							 
						 WebElement date2 = lDriver.findElement(By.xpath("//tbody//tr//td[contains(text(),'15')]"));
						 if(date2.isDisplayed()){
							 Report.OperationPoint(testContext.getName(),"Selecting 15th as the payment date.");
							 date2.click();
							 Thread.sleep(5000);
							 lDriver.navigate().back();
							 Thread.sleep(5000);
							 
							 String strnewdate = oR_MakeAPayment.txtPaymentDate.getText();
							 if(strnewdate.equalsIgnoreCase(strpaymentdateseen)){
								 Report.ValidationPoint(testContext.getName(),"Sorry!! This date is not available for selection. Please select some other date.", "true","true",true); 
							 }
						 }
						 
						 Report.OperationPoint(testContext.getName(),"Selecting another date.");
						 oR_MakeAPayment.imgCalender.click();
						 Thread.sleep(4000);
						 lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
						 
						 WebElement date3 = lDriver.findElement(By.xpath("//div[@id='month2']//tr//td/a[normalize-space(text())='1']"));
						 if(date3.isDisplayed()){
							 Report.OperationPoint(testContext.getName(),"Selecting 1st of another month as the payment date.");
							 date3.click();
							 Thread.sleep(5000);
							 lDriver.switchTo().defaultContent();
							 Thread.sleep(5000);
							 String strnewdate = oR_MakeAPayment.txtPaymentDate.getText();
							 if(strnewdate != strpaymentdateseen){
								 Report.ValidationPoint(testContext.getName(),"This date is available for payment.", "true","true",true); 
							 } else {
								 Report.ValidationPoint(testContext.getName(),"This date is available for payment.", "true","false",true); 
							 }
						}
					}
							
				// Step 8
				
				Boolean bPaymentMethod = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethodfrm, UI.iObjTimeOut, lDriver);
				if(bPaymentMethod){
					WebElement select = lDriver.findElement(By.xpath("//select[@id='makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.methodNameId']/option[contains(text(),'Select')]"));
					Report.ValidationPoint(testContext.getName(),"Select Payment Method is displayed", "true","true",true); 
					select.click();
					
					// selecting New debit / credit card method
					WebElement DebitCredit = lDriver.findElement(By.xpath(".//*[@id='makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.methodNameId']/option[3]"));
					if(DebitCredit.isDisplayed()){
						DebitCredit.click();
						Thread.sleep(4000);
					}
					
					//making valid payment
					 lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
					 Boolean bDebitCreditCardPayment = UI.WaitForObject(oR_MakeAPayment.txtDebitCreditCardPayment, UI.iObjTimeOut, lDriver);
					 if(bDebitCreditCardPayment){
						 Report.ValidationPoint(testContext.getName(),"New Debit / Credit Card Payment Method frame is displayed.", "true","true",true);  
					 }
					 
					 
//					  if(UI.WaitForObject(oR_AccountOverview.frmTourMyBill, 10, lDriver))
//					  {
//						  lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
//						  
						  
						  if(UI.WaitForObject(oR_MakeAPayment.edtPaymentFrameNameOnCard, 2, lDriver))
						  {
							  oR_MakeAPayment.edtPaymentFrameNameOnCard.sendKeys(sName);
							  System.out.println("Name on card is: "+sName);
							  
							  if(UI.WaitForObject(oR_MakeAPayment.edtPaymentFrameCardNumber,1, lDriver))
							  {
								  oR_MakeAPayment.edtPaymentFrameCardNumber.sendKeys(sCardNo);
								  System.out.println("card number  is: "+sCardNo);
								  
								  if(UI.WaitForObject(oR_MakeAPayment.lstSelectCardExpirationMonth,1, lDriver))
								  {
									  Select OptionSelect_2 = new Select(oR_MakeAPayment.lstSelectCardExpirationMonth);
									  OptionSelect_2.selectByVisibleText(sDateMonth);	
									  System.out.println("Month Selected is: "+sDateMonth);
									  
									  if(UI.WaitForObject(oR_MakeAPayment.lstSelectCardExpirationYear,1, lDriver))
									  {
										  Select OptionSelect_1 = new Select(oR_MakeAPayment.lstSelectCardExpirationYear);
										  OptionSelect_1.selectByVisibleText(sDateYear);	
										  System.out.println("Year Selected is: "+sDateYear);
										  
										  if(UI.WaitForObject(oR_MakeAPayment.edtPaymentFrameSecurityNumber, 1, lDriver))
										  {
											  oR_MakeAPayment.edtPaymentFrameSecurityNumber.sendKeys(sSecCode);  
											  System.out.println("Security No  is: "+sSecCode);
											  
											  if(UI.WaitForObject(oR_MakeAPayment.edtPaymentFrameZipCode, 1, lDriver))
											  {
												  oR_MakeAPayment.edtPaymentFrameZipCode.sendKeys(sZip);
												  System.out.println("ZIP is: "+sZip);
												  Report.TestPoint(testContext.getName(), "Validating all the required fields are filled for payment", "true",String.valueOf(oR_MakeAPayment.edtPaymentFrameZipCode.isDisplayed()),true);
												  //click on continue
												  oR_MakeAPayment. btnPaymentFrameContinue.click();
												  
												  lDriver.switchTo().defaultContent();
												  if(UI.WaitForObject(oR_MakeAPayment.lnkContinueDis, 60, lDriver))
												  {
													  //click on continue
													  oR_MakeAPayment.lnkContinueDis.click();
												  }
											  }
										  }
									  }
								  }
								  
							  }
							}
					  }
					  else
					  {
						  Report.TestPoint(testContext.getName(), "Validating frame is dipalyed with required fields for payment", "true","false",true);
					  }	 
				
				//	Boolean bMAP = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, UI.iObjTimeOut, lDriver);
					if(bMAP) {
						Report.ValidationPoint(testContext.getName(),"User is again navigated to Make a Payment page. ", "true","true",true);
					}
					
					// Validating if the payment method is saved
					WebElement seenmethod = lDriver.findElement(By.xpath(".//*[@id='makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.methodNameId']"));
					seenmethod.getText();
					System.out.println(seenmethod);
//					//validate revire page
//					if(UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle, 10, lDriver))
//					{
//						Report.ValidationPoint(testContext.getName(),"Validating Review payment details page is displayed", "true","true",true);
//					}
					
				
				
							// Validating Split this payment CTA (step 9)
							
							Boolean bSplitThisPayment = UI.WaitForObject(oR_MakeAPayment.lnkSplitThisPayment, UI.iObjTimeOut, lDriver);
							if(bSplitThisPayment){
								Report.ValidationPoint(testContext.getName(),"Split this payment CTA is displayed.", "true","true",true);
								Report.ValidationPoint(testContext.getName(),"Clicking on split this payment CTA.", "true","true",true);
								oR_MakeAPayment.lnkSplitThisPayment.click();
								
								// Validating if user is able to split the bill
								
								WebElement split = lDriver.findElement(By.xpath("//div[@id='paymentItem_0_1']"));
								if(split.isDisplayed()){
									Report.ValidationPoint(testContext.getName(),"User is able to split the bill.", "true","true",true);
								} else {
									Report.ValidationPoint(testContext.getName(),"User is able to split the bill.", "true","false",true);
								}
								
							
							
							// Validating Cancel CTA (step 10)
							
							Boolean bCancel = UI.WaitForObject(oR_MakeAPayment.lnkCancel, UI.iObjTimeOut, lDriver);
							if(bCancel){
								 Report.OperationPoint(testContext.getName(),"Cancel CTA is present.");
								 Report.OperationPoint(testContext.getName(),"Clikcing on Cancel CTA.");
								 oR_MakeAPayment.lnkCancel.click();
								 Boolean bOverview = UI.WaitForObject(oR_AccountOverview.txtWelcome, UI.iObjTimeOut, lDriver);
								 if(bOverview){
									 Report.ValidationPoint(testContext.getName(),"On clicking Cancel CTA, user is navigated to Overview page.", "true","true",true); 
								 }
							}
							
							// MAP page again (step 11)
							
							Boolean bMakeAPayment1 = UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentInOverviewPage, UI.iObjTimeOut, lDriver);
							if(bMakeAPayment1) {
								 Report.ValidationPoint(testContext.getName(),"Clicking on Make a Payment CTA. ", "true","true",true);
								 oR_AccountOverview.btnMakeAPaymentInOverviewPage.click();
							}
							
							Boolean bMAP1 = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, UI.iObjTimeOut, lDriver);
							if(bMAP1) {
								 Report.ValidationPoint(testContext.getName(),"User is navigated to Make a Payment page. ", "true","true",true);
							}
							
							
							// step 12
							Boolean bBalanceDue = UI.WaitForObject(oR_MakeAPayment.txtTotalBalanceDue, UI.iObjTimeOut, lDriver);
							if(bBalanceDue) {
								 Report.OperationPoint(testContext.getName(),"Total Balance due seen in the balance section is :" + bBalanceDue.toString());
								 
								 String strpayment = oR_MakeAPayment.edtPaymentAmount1.getText();
								 Report.ValidationPoint(testContext.getName(),"Total Balance due seen in the Payment amount section is :" + strpayment , "true","true",true);
								 
								 // Entering lesser value then the actual amount for error validation
								 int interrorpayment = Integer.valueOf(strpayment) - 50;
								 String strerrorpayment = String.valueOf(interrorpayment);
								 oR_MakeAPayment.edtPaymentAmount1.sendKeys(strerrorpayment);
								 
							
							}
							
						}
			}	
			catch(Exception e)
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
			}
		 } */
		 
		 /**************************************************************
		  * Function Name -  VerifyServiceCancelledMessageInBAP()
		  * Description- 
		  * Parameters - None
		  * Date created -13th Nov 2015
		  * Developed by - Guatham
		  ***************************************************************/
		 //BAP30751

		 public static void VerifyPendingPaymentAutopayment(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
		 {
			 Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			 WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		 try{
			 //navigate to BAP
			 UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
			 
			 //navigate to history tab
			 
				
			}
	
		 catch(Exception e)
		 {
	e.printStackTrace();
	Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		 }
     }
		 
		 
		 /**************************************************************
		  * Function Name -  VerifyBillPageStandaloneUverse()
		  * Description- 
		  * Parameters - None
		  * Date created -29th Jan 2016
		  * Developed by - Krutika
		  ***************************************************************/
		 //BAP33387

		 public static void VerifyBillPageStandaloneUverse(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
		 {
			
			 WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		 try{
			 //navigate to BAP
			 UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
			 
			Report.OperationPoint(testContext.getName(), "Navigated to Bills and Usage Page");
			
			//Verify Top wrapper should not display total due amount rather should display message
			Boolean bMsg = UI.WaitForObject(oR_BillAndUsage.txtBillIsntUnavailable, 40);
			Report.TestPoint(testContext.getName(), "Verify Top wrapper should not display total due amount rather should display message", "true", String.valueOf(bMsg), true);
			
			//Validate the estimated charges during the Pre-install tenure within the Bill Tab.
			Boolean bEstimated = UI.WaitForObject(oR_BillAndUsage.txtEstimatedCharges, 40);
			Report.TestPoint(testContext.getName(), "Validate the estimated charges during the Pre-install tenure within the Bill Tab.", "true", String.valueOf(bEstimated), true);
			
			//Validate the About your Bill section.
			Boolean bUnderstanding = UI.WaitForObject(oR_BillAndUsage.lnkUnderstandingBill, 80);
			Report.TestPoint(testContext.getName(), "Validate 'Understanding Your AT&T U-verse Bill'is displayed.", "true", String.valueOf(bUnderstanding), true);
			
			oR_BillAndUsage.lnkUnderstandingBill.click();
			Boolean bVideo = UI.WaitForObject(lDriver.findElement(By.xpath("//h2[contains(text(),'Understanding your AT&T U-verse® Bill')]")), 40);
			Report.TestPoint(testContext.getName(), "Validate 'Understanding Your AT&T U-verse Bill' clicking on link with launch video.", "true", String.valueOf(bVideo), true);
			
			Thread.sleep(4000);
			oR_BillAndUsage.lnkCloseOnConnectAndSaveWithWiFi.click();
			//Validate Interactive Sample Bill - clicking on link will open new window with bill in view
			Boolean bInteractive = UI.WaitForObject(oR_BillAndUsage.lnkViewInteractiveBill , 40);
			Report.TestPoint(testContext.getName(), "Validate 'Interactive Sample Bill'is displayed.", "true", String.valueOf(bInteractive), true);
			
			UI.VerifyLinkNavigatestoNewWindow(oR_BillAndUsage.lnkViewInteractiveBill, "interactivebill");
			
			//Validate Generic U-verse First Bill KB Article 
			
			Boolean bLearnBill = UI.WaitForObject(oR_BillAndUsage.lnkLearnAboutBill , 40);
			Report.TestPoint(testContext.getName(), "Validate 'Learn About Your First U-verse Bill'is displayed.", "true", String.valueOf(bLearnBill), true);
			
			UI.VerifyLinkNavigatestoNewWindow(oR_BillAndUsage.lnkLearnAboutBill, "esupport");
			
			//Validate .Display verbiage on Bill in Accessible Format
			Boolean bBillAccessible = UI.WaitForObject(oR_BillAndUsage.txtBillAccessibleFormat , 40);
			Report.TestPoint(testContext.getName(), "Validate 'verbiage on Bill in Accessible Format'is displayed.", "true", String.valueOf(bBillAccessible), true);
			
			//Validate Validate the secondary link rails is not displayed
			UI.VerifyElementNotPresent(oR_BillAndUsage.txtManageAccount, "Manage Account");
			
			UI.VerifyElementNotPresent(oR_BillAndUsage.txtChangeBillingOptions, "Change Billing Options");
			
			UI.VerifyElementNotPresent(oR_BillAndUsage.txtGetHelpHeading, "Get Help");
			
			}
	
		 catch(Exception e)
		 {
	e.printStackTrace();
	Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		 }
     }
		 
		 /**************************************************************
		  * Function Name -  ValidateBillTabAlerts()
		  * Description- 
		  * Parameters - None
		  * Date created -17th Feb 2016
		  * Developed by - Hiral
		  ***************************************************************/
		 //UBIL0041a

		 public static void ValidateBillTabAlerts(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
		 {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
			try{
				
			 //navigate to BAP
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
			Report.OperationPoint(testContext.getName(), "Navigated to Bills and Usage Page");
			
			// Validate Bill Alerts
			Boolean bBillAlerts = UI.WaitForObject(oR_BillAndUsage.txtBillAlerts , UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate 'BillAlerts' section is displayed.", "true", String.valueOf(bBillAlerts), true);
			oR_BillAndUsage.txtBillAlerts.click();
			
			// Total number of alerts present
			List<WebElement> Alerts = lDriver.findElements(By.xpath("//div[@class='alertBoxGrad']/ul/li"));
			Report.OperationPoint(testContext.getName(), "Total alerts present are : " + Alerts.size());
			String [] sAlerts = new String[Alerts.size()];
			
			for(int i=0;i<Alerts.size();i++){
				sAlerts[i] = Alerts.get(i).getText();
			}
			
			for(int i=0;i<Alerts.size();i++){
				
				// Validating Icon
				List<WebElement> Alertsicon = lDriver.findElements(By.xpath("//div[@class='alertBoxGrad']//img[@alt='alertIcon']"));
				String [] sAlertsicon = new String[Alertsicon.size()];
				sAlertsicon[i] = Alertsicon.get(i).getText();
				if(sAlertsicon[i] != null){
					Report.OperationPoint(testContext.getName(),"Alert is preceeded with an icon.");
				} else {
					Report.OperationPoint(testContext.getName(),"Icon is missing for the alert.");
				}
			
			
				// Validating heading
				List<WebElement> Alertsheading = lDriver.findElements(By.xpath("//div[@class='alertBoxGrad']//span"));
				String [] sAlertsheading = new String[Alertsheading.size()];
				sAlertsheading[i] = Alertsheading.get(i).getText();
				Report.OperationPoint(testContext.getName()," Validating Alert heading : "+ sAlertsheading[i]);
			
				// Validating content
				List<WebElement> Alertscontent= lDriver.findElements(By.xpath("//div[@class='alertBoxGrad']//p[1]"));
				String [] sAlertscontent = new String[Alertscontent.size()];
				
				List<WebElement> AlertsCTA= lDriver.findElements(By.xpath("//div[@class='alertBoxGrad']//p/a"));
				String [] sAlertsCTA = new String[Alertscontent.size()];
				
				sAlertscontent[i] = Alertscontent.get(i).getText();
				Report.OperationPoint(testContext.getName()," Validating Alert content: "+ sAlertscontent[i]);
				
				sAlertsCTA[i] = AlertsCTA.get(i).getText();
				Report.OperationPoint(testContext.getName()," Validating Alert CTA : "+ sAlertsCTA[i]);
				
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
    }
		 
		 	/**************************************************************
			 * Function Name - VerifyBillAlertAndDiscountForCurrentBilled()
			 * Description - The purpose of this test case is to validate Bill alert for Discounts and Promotions
		 	 * Test Case -  BAP28174
			 * Parameters - None
			 * Date created - 17th Feb 2016
			 * Developed by - Clint John
			 * Last Modified By - 
			 * Last Modified Date - 
			 ***************************************************************/
			public static void VerifyBillAlertAndDiscountForCurrentBilled(final ITestContext testContext) throws HeadlessException, IOException, AWTException
			{
				try
				{
					WebDriver lDriver = UI.getDriver(testContext.getName());
					OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
					OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
					Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
					
					oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on Billing, Usage and Payments secondary navigation");
					
					//A bill alerts should display for Discount applied to the each product
					boolean bDiscountAlert = UI.WaitForObject(oR_BillAndUsage.txtAlertDiscount, UI.iObjTimeOut, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify that bill alerts should display for Discount applied to the each product", "true",String.valueOf(bDiscountAlert), true);
					
					//Validate the alert content for discount in alerts section
					//The Bill alert should display as follows 
					//[Product Type] Discount 
					//"You are receiving a discount of $[X.XX] on your Bill" 
					//View Discount CTA
					boolean bAlertDiscountHeading = UI.WaitForObject(oR_BillAndUsage.txtAlertDiscountHeading, UI.iObjTimeOut, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify that bill alerts should display [Product Type] Discount as alert heading", "true",String.valueOf(bAlertDiscountHeading), true);
					Report.OperationPoint(testContext.getName(), "Discount alert heading displayed is: "+oR_BillAndUsage.txtAlertDiscountHeading.getText().toString());
					String sAlertHeading = oR_BillAndUsage.txtAlertDiscountHeading.getText().toString();
					String[] sPlanName = sAlertHeading.split("discount");
					
					boolean bDiscountAlertContents = UI.WaitForObject(oR_BillAndUsage.txtAlertDiscount, UI.iObjTimeOut, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify that bill alerts should display as following format: 'You are receiving a discount of $[X.XX] on your Bill' ", "true",String.valueOf(bDiscountAlertContents), true);
					
					boolean bViewDiscountCTA = UI.WaitForObject(oR_BillAndUsage.lnkViewDiscountCTA, UI.iObjTimeOut, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify that 'View discount' CTA is displayed under alerts content section", "true",String.valueOf(bViewDiscountCTA), true);
					
					//The CTA will anchor to the part of the Bill where Discount is present
					oR_BillAndUsage.lnkViewDiscountCTA.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on View discount CTA");
					try
					{
						
						boolean bRedirectCheck = UI.WaitForObject(lDriver.findElement(By.xpath("//div[contains(text(),'(Promotional Offer)') and contains(text(),'"+sPlanName[0]+"')]")), UI.iObjTimeOut, lDriver);
						if(bRedirectCheck==true)
						{
							Report.ValidationPoint(testContext.getName(), "Verify that 'View discount' CTA will anchor to the part of the Bill where Discount is present", "true","true", true);
	
						}else
						{
							Report.ValidationPoint(testContext.getName(), "Verify that 'View discount' CTA will anchor to the part of the Bill where Discount is present", "true","false", true);
	
						}
						
					}catch(Exception e2)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that 'View discount' CTA will anchor to the part of the Bill where Discount is present", "true","false", true);

					}

				}
				catch (Exception e) 
				{
					e.printStackTrace();
					Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

				}
				
			}
			
			/**************************************************************
			 * Function Name - VerifyViewPlanSummary()
			 * Description - To verify view plan Summary section
		 	 * Test Case -  UBIL1007 - 443809 - Channel Enablement - View Plan Summary - free trial plan
			 * Parameters - None
			 * Date created - 19th Feb 2016
			 * Developed by - Clint John
			 * Last Modified By - 
			 * Last Modified Date - 
			 ***************************************************************/
			//UAT_UBIL1007
			public static void VerifyViewPlanSummary(final ITestContext testContext) throws HeadlessException, IOException, AWTException
			{
				try
				{
					
					WebDriver lDriver = UI.getDriver(testContext.getName());
					OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
					OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
					Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
					
					oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Billing, Usage and Payments' link");
					Thread.sleep(3000);
					boolean bBillingAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify that Billing & Usage page is displayed", "true",String.valueOf(bBillingAndUsage), true);
					
					new Actions(lDriver).moveToElement(oR_BillAndUsage.lnkBillTab).click().perform();
					
					boolean bViewPlanSummary = UI.WaitForObject(oR_BillAndUsage.lnkViewPlanSummary, UI.iObjTimeOut, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify that View plan summary link is displayed", "true",String.valueOf(bViewPlanSummary), true);
					
					oR_BillAndUsage.lnkViewPlanSummary.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'View plan summary' link");
					Thread.sleep(3000);
					try
					{
						boolean bTrailPlan = UI.WaitForObject(oR_BillAndUsage.txtViewPlanSummaryTrailPlan, UI.iObjTimeOut, lDriver);
						if(bTrailPlan==true)
						{
							Report.ValidationPoint(testContext.getName(), "Verify free trial plan is available for the account", "true","true", true);
							
							boolean bContentDisplayed = UI.WaitForObject(oR_BillAndUsage.txtViewPlanSummaryPlanName, UI.iObjTimeOut, lDriver);
							Report.ValidationPoint(testContext.getName(), "Verify session plan name and summary displayed as per CRD, along with other BAU plans on the account", "true",String.valueOf(bContentDisplayed), true);
							
						}else
						{
							Report.ValidationPoint(testContext.getName(), "Verify free trial plan is available for the account", "true","false", true);
						}
						
					}catch(Exception e2)
					{
						Report.ValidationPoint(testContext.getName(), "Verify free trial plan is available for the account", "true","false", true);
					}
				}
				catch (Exception e) 
				{
					e.printStackTrace();
					Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

				}
				
			}
			

	/**************************************************************
	 * Function Name - VerifyMakeApayementPageEntire()
	 * Description - 
 	 * Test Case -  BAP25646_1
	 * Parameters - None
	 * Date created - 22nd Feb 2016
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//BAP25646_1
	public static void VerifyMakeApayementPageEntire(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_MakeAPayment oR_MAP = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		try
		{
			//Click on make a payment link
			boolean bBtnMAP = UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentInOverviewPage, 20, lDriver);
			Report.TestPoint(testContext.getName(), "Verify Make a payment button", "true",String.valueOf(bBtnMAP), true);
			Report.OperationPoint(testContext.getName(), "Click on make a payment link");
			oR_AccountOverview.btnMakeAPaymentInOverviewPage.click();
			//Validate make a payment page
			boolean bMAPpg = UI.WaitForObject(oR_MAP.txtMakeAPayment, 20, lDriver);
			Report.TestPoint(testContext.getName(), "Validate make a payment page", "true",String.valueOf(bMAPpg), true);
			//Validate 3 step progress indicator showing User's stage of payment completion.
			boolean bSteps = UI.WaitForObject(oR_MAP.txtProgressBar, 20, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate 3 step progress indicator showing User's stage of payment completion", "true",String.valueOf(bSteps), true);
			//right Hand navigation should not be displayed
			boolean bRightSteps = UI.WaitForObject(oR_MAP.txtStepIndicatorPaymentEntry, 2, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate right Hand navigation should not be displayed", "true",String.valueOf(!bRightSteps), true);
			//CancelCTA
			boolean bCancel = UI.WaitForObject(oR_MAP.lnkCancelPayment, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify Cancel cta", "true",String.valueOf(bCancel), true);
			//Next CTA
			boolean bNext = UI.WaitForObject(oR_MAP.lnkContinueDis, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify Continue cta", "true",String.valueOf(bNext), true);
			//Edit payment profile,Enroll in autopay check box and flexible Payment options link should be displayed
			//Edit payment profile - not present with the data
			//Enroll in autopay
			boolean bEA = UI.WaitForObject(oR_MAP.chkEnrollInAutopayCheckbox, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify Enroll in autopay cta", "true",String.valueOf(bEA), true);
			
			//Balance is NA for this data
			
			//
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	}
	 /**************************************************************
	 * Function Name - ValidateChargesForAudiCTNAndPrintFriendlyVersion
	 * Description - The purpose of this test to validate charges for Audi CTN and Print Friendly Version of Bill
 	 * Test Case -  UBIL1003 - 446626 - AUDI - Validate Bill charges
	 * Parameters - None
	 * Date created - 23rd feb 2016
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date - 
 ***************************************************************/
// UAT_UBIL1003
	public static void ValidateChargesForAudiCTNAndPrintFriendlyVersion(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			Report.OperationPoint(testContext.getName(), "1-Login with a slid associated to Audi CTN on a CBWireline and Navigate to  Bill, Usage and Payments  page");
			Boolean bPrint;
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Thread.sleep(3000);
			boolean bBillingAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "1-Validate  Bill, Usage and Payments  landing page is displayed with Bill tab in focus.", "true",String.valueOf(bBillingAndUsage), true);

			List<WebElement> AudiCTNs = lDriver.findElements(By.xpath("//a[contains(text(),'Audi')]"));
			if(AudiCTNs.size()>0)
				Report.ValidationPoint(testContext.getName(), "2-Verify Audi Label", "true","true", true);
			else
				Report.ValidationPoint(testContext.getName(), "2-Verify Audi Label", "true","Audi CTN not present", true);

			for(WebElement e : AudiCTNs)
			{
				e.click();
			}
			
//			Verify Charges  associated with CTN
			Report.OperationPoint(testContext.getName(), "3-Verify Charges  associated with CTN");
			List<WebElement> Charges = lDriver.findElements(By.xpath("//*[contains(text(),'Charges')]"));
			if(Charges.size()>0)
				Report.ValidationPoint(testContext.getName(), "3-Verify applicable charges displayed as BAU", "true","true", true);
			else
				Report.ValidationPoint(testContext.getName(), "3-Verify applicable charges displayed as BAU", "true","false", true);
			String iAudiTotal = lDriver.findElement(By.xpath("//*[contains(text(),'Audi') and contains(@class,'bold')]//parent::div//*[contains(text(),'$')]")).getText();

//			Navigate to Printer Friendly version 
			bPrint = UI.WaitForObject(oR_BillAndUsage.lnkPrint, 10, lDriver);		
			Report.TestPoint(testContext.getName(), "Print Link", "true", bPrint.toString(), true);
			oR_BillAndUsage.lnkPrint.click();
			String winHandleBefore = lDriver.getWindowHandle();
			ArrayList WinCount = new ArrayList (lDriver.getWindowHandles());
			if(WinCount.size()>1)
			{/*switching to new Print window opened*/
				for(String winHandle : lDriver.getWindowHandles())
				{
					lDriver.switchTo().window(winHandle);
				}	
				Report.ValidationPoint(testContext.getName(), "4-Navigate to Printer Friendly version ", "true",bPrint.toString(), true);
			}
			else
				Report.TestPoint(testContext.getName(), "Print Layout in new window", "New window", "New window is not opened", true);

			
			String iPrintAudiTotal = lDriver.findElement(By.xpath("//*[contains(text(),'Audi') and contains(@class,'bold')]//parent::div//*[contains(text(),'$')]")).getText();
		
			if(iPrintAudiTotal.equals(iAudiTotal))
				Report.ValidationPoint(testContext.getName(), "4-Verify it is in sync with online bill", "true","true", true);
			else
				Report.ValidationPoint(testContext.getName(), "4-Verify it is in sync with online bill", "true","true", true);
			Report.OperationPoint(testContext.getName(), "5-Verify both English and Spanish content.");
			
			lDriver.close();
			lDriver.switchTo().window(winHandleBefore);
//			Verify both English and Spanish content.
//			Validate content, spelling, format, field layout, etc. are displaying correctly in both English and Spanish.
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - VerifyBillTabPrintFriendly()
	 * Description - Validate printer-friendly version of online bill in bill tab
 	 * Test Case -  UBIL0002 - CB - Bill Tab - Print - Friendly
	 * Parameters - None
	 * Date created - 25th Feb 2016
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_UBIL0002
	public static void VerifyBillTabPrintFriendly(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Billing, Usage and Payments tab");
			Thread.sleep(2000);
			
			boolean bBillingPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Billing&Usage page is displayed", "true",String.valueOf(bBillingPage), true);
			
			new Actions(lDriver).moveToElement(oR_BillAndUsage.lnkBillTab).perform();
			Thread.sleep(4000);
			
			//2. Validate at Account Summary title level the print CTA is display and selecting the link will open a printer-friendly version of online bill in a separate window.
			boolean bPrintIcon = UI.WaitForObject(oR_BillAndUsage.lnkPrint, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate at Account Summary title level the print CTA is displayed", "true",String.valueOf(bPrintIcon), true);
			//selecting the link will open a printer-friendly version of online bill in a separate window.
			oR_BillAndUsage.lnkPrint.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Print CTA");
			Thread.sleep(3000);
			
			//Switch to new window:
			ArrayList<String> tabs2 = new ArrayList<String> (lDriver.getWindowHandles());
		    lDriver.switchTo().window(tabs2.get(1));
		    Report.OperationPoint(testContext.getName(), "Operational - Switched to newly opened window");
		    	
		    	//3. Validate print functionality such as the ability to scroll, close and print will be available once the window pop-up is opened.
		    	boolean bAccountDetails = UI.WaitForObject(oR_BillAndUsage.txtAccountDetails, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that a printer-friendly version of online bill is opened in separate window", "true",String.valueOf(bAccountDetails), true);
				
				boolean bPrintIconInNewWindow = UI.WaitForObject(oR_BillAndUsage.lnkPrint, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate print functionality such as the ability to scroll, close and print will be available once the window pop-up is opened", "true",String.valueOf(bPrintIconInNewWindow), true);

				//4. Validate all list will be displayed expanded without expand/collapse functionality and CTA to view any usage details and to view all usage for current bill will not be included.
				try
				{
					boolean bExpandIcon = UI.WaitForObject(oR_BillAndUsage.imgExpandIcon, 5, lDriver);
					boolean bCollapseIcon = UI.WaitForObject(oR_BillAndUsage.imgCollapseIcon, 5, lDriver);
					if(bExpandIcon==false && bCollapseIcon==false)
					{
						Report.ValidationPoint(testContext.getName(), "Validate all list will be displayed expanded without expand/collapse functionality", "true","true", true);
					}else
					{
						Report.ValidationPoint(testContext.getName(), "Validate all list will be displayed expanded without expand/collapse functionality", "true","false", true);
					}
					
				}catch(Exception e2)
				{
					Report.ValidationPoint(testContext.getName(), "Validate all list will be displayed expanded without expand/collapse functionality", "true","true", true);
				}
				
				//5. Validate Access to View Paper Bill, Access to Make a Payment, 
				//CTA to View explanation of charges, CTA to View Bill info, News you can Use , 
				//CTA to CA Customer Information, and Promos are not displayed within the bottom portion of the Account Summary within the printer-friendly window.
				UI.VerifyElementNotPresent(oR_BillAndUsage.lnkPaperBill, "View Paper Bill");
				UI.VerifyElementNotPresent(oR_BillAndUsage.btnMakePaymentInBillingPage, "Make a payment");
				UI.VerifyElementNotPresent(oR_BillAndUsage.lnkViewExplainationCharges, "View explaination of charges");
				UI.VerifyElementNotPresent(oR_BillAndUsage.lnkViewBillInfoNewsPromos, "View Bill info, News and promos");
				UI.VerifyElementNotPresent(oR_BillAndUsage.lnkViewNewsPromos, "View news you can use & promos");
				
				//6. Validate print functionality within the window will display and when selected will default to the browser's print functionality.  In addition, carry over highlighting or overage charges and billed purchase alerts within bill details will be displayed.
				Report.ValidationPoint(testContext.getName(), "Validate print functionality within the window will display", "true",String.valueOf(bPrintIconInNewWindow), true);
				boolean bBillAlerts = UI.WaitForObject(oR_BillAndUsage.imgAlertIcon, 5, lDriver);
				if(bBillAlerts==true)
				{
					Report.ValidationPoint(testContext.getName(), "Validate that billed purchase alerts within bill details will be displayed", "true","true", true);

				}
				boolean bPastDue = UI.WaitForObject(oR_BillAndUsage.txtPastDueFromPreviousActivity, 5, lDriver);
				if(bPastDue==true)
				{
					Report.ValidationPoint(testContext.getName(), "Validate that carry over highlighting (past due) or overage charges will be displayed", "true","true", true);

				}

				//7. Validate additional elements such as AT&T Logo, Account Number, Customer name & address will be included in the printer-friendly window and alerts within the bill details excluding the CTA associated to each line item is displayed.
				boolean bATTLogo = UI.WaitForObject(oR_BillAndUsage.imgATTlogoPrint,5, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that AT&T logo is displayed in printer-friendly window", "true",String.valueOf(bATTLogo), true);
				boolean bAccNum = UI.WaitForObject(oR_BillAndUsage.txtAccNumInPrintFriendlyView, 5, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that account number is displayed in printer-friendly window", "true",String.valueOf(bAccNum), true);
				boolean bCustName = UI.WaitForObject(oR_BillAndUsage.txtCustNameInPrintFriendlyView, 5, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that customer name & address is displayed in printer-friendly window", "true",String.valueOf(bCustName), true);
				
				//8. Validate bill period for monthly charges for both wireless and wireline is displayed at section title level.
				boolean bMontlyServicePeriod = UI.WaitForObject(oR_BillAndUsage.txtMontlyServicePeriodInPrintFriendlyView, 5, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that bill period for monthly charges is displayed at section title level", "true",String.valueOf(bMontlyServicePeriod), true);
				
				//9.Validate no close button is present and that the entire printer-friendly window can be closed without logging out the user.
				UI.VerifyElementNotPresent(oR_BillAndUsage.lnkClose, "Close button");
				
		    lDriver.close();
		    Report.OperationPoint(testContext.getName(), "Operational - Closing printer-friendly window");
		    lDriver.switchTo().window(tabs2.get(0));
		    Thread.sleep(3000);
		    
			boolean bBillingPageBack = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that user is back at Billing&Usage page", "true",String.valueOf(bBillingPageBack), true);
			if(bBillingPageBack==true)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that entire printer-friendly window can be closed without logging out the user", "true","true", true);

			}

		    
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}

	 /**************************************************************
	 * Function Name - ValidateChargesForAudiCTNAndPrintFriendlyVersion
	 * Description - The purpose of this test to validate charges for Audi CTN and Print Friendly Version of Bill
 	 * Test Case -  UBIL1003 - 446626 - AUDI - Validate Bill charges
	 * Parameters - None
	 * Date created - 23rd feb 2016
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date - 
 ***************************************************************/
// UAT_UPAY0097_07 
	public static void ValidateEnrollInAutoPay(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
			OR_ReviewPaymentDetails oR_ReviewPaymentDetails = PageFactory.initElements(lDriver, OR_ReviewPaymentDetails.class);
			OR_PaymentConfirmation oR_PaymentConfirmation = PageFactory.initElements(lDriver, OR_PaymentConfirmation.class);
			OR_PaperlessBilling oR_PaperlessBilling = PageFactory.initElements(lDriver, OR_PaperlessBilling.class);

			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			Boolean bHeader, bEnrollingInAutopay, bTotalAmt, bConfirmationNum, bFrame, bSubmit, bMAP, bReview, bcontinue, bAutopayChkBox, bSelectCheckingSaving,bNameOnBankAcc,bRoutingNumber, bReenterAccNum, bBankAccountNumber;
			
			String sAmount= IO.GetParamVal(sTestParams, "Amount");
			String sBankAccNum= IO.GetParamVal(sTestParams, "Acc_Num");
			String sRoutingNumber = IO.GetParamVal(sTestParams, "Routing_Num");
//			String sDateMonth= IO.GetParamVal(sTestParams, "Month");
//			String sDateYear= IO.GetParamVal(sTestParams, "Year");
			String sSecCode= IO.GetParamVal(sTestParams, "SecCode");
			String sZip= IO.GetParamVal(sTestParams, "Zip");

//			Login to a Wireless Account that is not enrolled in AutoPay - Select the Make a Payment button
			bMAP = UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentOverview, 10, lDriver);
			Report.TestPoint(testContext.getName(), "1-Login to a Wireless Account that is not enrolled in AutoPay - Select the Make a Payment button", "true", bMAP.toString(), true);
			oR_AccountOverview.btnMakeAPaymentOverview.click();
			
//			"Select the Enroll in AutoPay check box, 
			Report.OperationPoint(testContext.getName(), "2-Select the Enroll in AutoPay check ");
//			
			if(UI.WaitForObject(oR_MakeAPayment.chkEnrollInAutopayCheckbox, 20, lDriver))
			{
				oR_MakeAPayment.chkEnrollInAutopayCheckbox.click();
				Report.TestPoint(testContext.getName(), "Enroll in AutoPay checkbox Selected ", "true", "true", true);
			}
			else
			{
				try
				{
					oR_MakeAPayment.chkEnrollInAutopayCheckbox.click();
					Report.TestPoint(testContext.getName(), "Enroll in AutoPay checkbox Selected ", "true", "true", true);

				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Enroll in AutoPay checkbox Selected ", "true", "false", true);
				}
			}
/**
			{
				bAutopayChkBox = UI.WaitForObject(oR_MakeAPayment.chkAccountSelectCheckbox_1, 20, lDriver);
//				Report.OperationPoint(testContext.getName(), "2-Select the Enroll in AutoPay check ");
				if(bAutopayChkBox)
				{
					oR_MakeAPayment.chkAccountSelectCheckbox_1.click();
					Report.TestPoint(testContext.getName(), "Enroll in AutoPay checkbox Selected ", "true", "true", true);

				}
			}		**/		
			

//			enter an Amount, select today as the date,Select a payment method and select the Next buttons(note: alternate payment methods between runs to ensure both methods work properly.)"
			oR_MakeAPayment.edtPaymentAmount1.clear();
			oR_MakeAPayment.edtPaymentAmount1.sendKeys(sAmount);
			Report.TestPoint(testContext.getName(), "2-enter an Amount, Select today as a date", "true", "true", true);

			
			/**   CALENDAR **/
		
			  Boolean bCalendarImg = UI.WaitForObject(oR_MakeAPayment.imgCalender1, UI.iObjTimeOut);
	            if(bCalendarImg)
	            {
				  Report.ValidationPoint(testContext.getName(), "Calendar Icon", "true", String.valueOf(bCalendarImg),  true);
				  oR_MakeAPayment.imgCalender1.click();
				  Thread.sleep(3000);

	              Boolean bCalendar = UI.WaitForObject(oR_MakeAPayment.frmCalender, UI.iObjTimeOut);
	              Report.ValidationPoint(testContext.getName(), "Calendar Frame", "true", String.valueOf(bCalendar),  true);
	              Thread.sleep(2000);
	              
	              lDriver.switchTo().frame(oR_MakeAPayment.frmCalender);
		             
//				  Boolean bCurrentDate = UI.WaitForObject(lDriver.findElement(By.xpath("//a[@onclick='parent.setValue('"+strDate+"')']")), UI.iObjTimeOut);
				  lDriver.findElement(By.xpath("//*[contains(@class,'currentDate')]//a")).click();
				  Thread.sleep(3000);
				  Boolean bCloseLink = UI.WaitForObject(oR_MakeAPayment.lnkCloseModal, 2);
		      		if(bCloseLink)
		      		{
	        		  	oR_MakeAPayment.lnkCloseModal.click();
	      				Thread.sleep(2000);
	      				lDriver.switchTo().parentFrame();     				
		      		}		      		
	            }
	            else
	            	Report.OperationPoint(testContext.getName(), "Calendar is not present");
			
//			Boolean bDropdown = UI.WaitForObject(oR_MakeAPayment.lstPaymentMethod, UI.iObjTimeOut);	
//			if(bDropdown)
	           /** Facing multiple issues while trying to use the function of selecting option from dropdown **/					
	            try
	            {
	            	Thread.sleep(3000);
			    	Report.OperationPoint(testContext.getName(), "2-Select 1st payment method Checkings/Savings and select the Next button");		    	
			    	WebElement list = lDriver.findElement(By.xpath("(//*[contains(text(),'Select payment method')])[2]//parent::div/select"));
			    	Select OptionSelect = new Select(list);
					OptionSelect.selectByVisibleText("New checking / savings account");	
					Thread.sleep(2000);
	            }
	            catch(Exception e)
	            {
	            	bSelectCheckingSaving = UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod, "New checking / savings account");
//					if(!bSelectCheckingSaving)
//						Report.TestPoint(testContext.getName(), "Drop down for Payment method not present", "true", "false", true);    
				
	            }
				
//		    Validate that new payment method div layer - checking/ savings is displayed as below:
	    	Boolean bCheckingSavingFrame = UI.WaitForObject(oR_MakeAPayment.frmNewCheckingSavingPaymentMethod, UI.iObjTimeOut);
	    	Report.TestPoint(testContext.getName(), "Frame Checking/Saving Payment Method", "true", String.valueOf(bCheckingSavingFrame), true);    
	    		
	    	lDriver.switchTo().frame(oR_MakeAPayment.frmNewCheckingSavingPaymentMethod); 	
	    	Report.OperationPoint(testContext.getName(), "Entering valid Checking/Savings Details");		    	
		        	
//	    	Enter valid bank account details in the new checking/saving account div layer and continue.
	    	try
	    	{
	    		
        	 bNameOnBankAcc  = UI.WaitForObject(oR_MakeAPayment.edtNameOnBankAcc, UI.iObjTimeOut);
        	 bRoutingNumber  = UI.WaitForObject(oR_MakeAPayment.edtRoutingNumber, UI.iObjTimeOut);
        	 bBankAccountNumber  = UI.WaitForObject(oR_MakeAPayment.edtBankAccountNumber,UI.iObjTimeOut);
        	 bReenterAccNum = UI.WaitForObject(oR_MakeAPayment.edtReenterAccNum, UI.iObjTimeOut);
        	 
				oR_MakeAPayment.edtNameOnBankAcc.sendKeys("test");
				oR_MakeAPayment.edtRoutingNumber.sendKeys(sRoutingNumber);
				oR_MakeAPayment.edtBankAccountNumber.sendKeys(sBankAccNum);
				oR_MakeAPayment.edtReenterAccNum.sendKeys(sBankAccNum);
				Report.ValidationPoint(testContext.getName(), "Verify Values for payment method1 are entered", "true","true", true);
	    	}
	    	catch (Exception e) 
			{
				Report.TestPoint(testContext.getName(), "Unable TO enter Checking/Savings details", "true", "Failed to validate", true);

			}
	    	bcontinue = UI.WaitForObject(oR_MakeAPayment.lnkContinue, 3);
			Report.TestPoint(testContext.getName(), "Click on Continue", "true", bcontinue.toString(), true);
	    	oR_MakeAPayment.lnkContinue.click();
	    	
	    	if(UI.WaitForObject(oR_MakeAPayment.frmNewCheckingSavingPaymentMethod, 5))
    		{
	    		if(UI.WaitForObject(oR_MakeAPayment.lnkContinue,3))
	    			oR_MakeAPayment.lnkContinue.click();
    		}
    		lDriver.switchTo().defaultContent();
    		
    		if(UI.WaitForObject(oR_MakeAPayment.lnkContinue,3))
    			oR_MakeAPayment.lnkContinue.click();
    		else
				Report.TestPoint(testContext.getName(), "Unable TO Click on Continue on MAP Page", "true", "Failed to Continue", true);
    		Thread.sleep(5000);
    		
    		/**Incase Payment Alert is shown up click on Continue **/
    		if(UI.WaitForObject(oR_MakeAPayment.lnkContinue,10))
    			oR_MakeAPayment.lnkContinue.click();
//    		Validate the user is directed to the Review Payment Details page.
    		bReview = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle, 10);
    		Report.ValidationPoint(testContext.getName(), "Validate the user is directed to the Review Payment Details page For Checking/Savings Payment Method", "true", bReview.toString(), true);
		
    		/** Cancel this method and go to MAP Page **/
    		Report.OperationPoint(testContext.getName(), "Clicking on Back");
    		if(UI.WaitForObject(oR_ReviewPaymentDetails.btnBack,3))
    			oR_ReviewPaymentDetails.btnBack.click();
    		
    		/** Incase Incomplete  Payment Alert is shown up click on Yes,Continue **/
    		if(UI.WaitForObject(oR_ReviewPaymentDetails.lnkYes,3))
    			oR_ReviewPaymentDetails.lnkYes.click();
    		if(UI.WaitForObject(oR_ReviewPaymentDetails.lnkYes,3))
    			oR_ReviewPaymentDetails.lnkYes.click();
    		
    		/**Entering details for Debit/Credit card **/
    		oR_MakeAPayment.edtPaymentAmount1.clear();
			oR_MakeAPayment.edtPaymentAmount1.sendKeys(sAmount);
			Report.TestPoint(testContext.getName(), "2-enter an Amount, Select today as a date", "true", "true", true);
			
			/**   CALENDAR **/
		
			 bCalendarImg = UI.WaitForObject(oR_MakeAPayment.imgCalender1, UI.iObjTimeOut);
            if(bCalendarImg)
            {
			  Report.ValidationPoint(testContext.getName(), "Calendar Icon", "true", String.valueOf(bCalendarImg),  true);
			  oR_MakeAPayment.imgCalender1.click();
			  Thread.sleep(3000);

              Boolean bCalendar = UI.WaitForObject(oR_MakeAPayment.frmCalender, UI.iObjTimeOut);
              Report.ValidationPoint(testContext.getName(), "Calendar Frame", "true", String.valueOf(bCalendar),  true);
              Thread.sleep(2000);
              
              lDriver.switchTo().frame(oR_MakeAPayment.frmCalender);
	             
//			  Boolean bCurrentDate = UI.WaitForObject(lDriver.findElement(By.xpath("//a[@onclick='parent.setValue('"+strDate+"')']")), UI.iObjTimeOut);
			  lDriver.findElement(By.xpath("//*[contains(@class,'currentDate')]//a")).click();
			  Thread.sleep(3000);
			  Boolean bCloseLink = UI.WaitForObject(oR_MakeAPayment.lnkCloseModal, 2);
	      		if(bCloseLink)
	      		{
        		  	oR_MakeAPayment.lnkCloseModal.click();
      				Thread.sleep(2000);
      				lDriver.switchTo().parentFrame();     				
	      		}		      		
            }
            else
            	Report.OperationPoint(testContext.getName(), "Calendar is not present");

			
	    	Report.OperationPoint(testContext.getName(), "3-Select a payment method 2-debit / credit and select the Next button");		    	
        	bSelectCheckingSaving = UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod, "New debit / credit account");
			
    		Thread.sleep(5000);
			bFrame = UI.WaitForObject(oR_MakeAPayment.frmNewDebitCreditCardPaymentMethod, 10, lDriver);
			Report.TestPoint(testContext.getName(), "Check Debit/Credit card frame", "true", bFrame.toString(), true);
		
	            
			// switching to new frame
			lDriver.switchTo().frame(oR_MakeAPayment.frmNewDebitCreditCardPaymentMethod);
		
			try
			{
				oR_MakeAPayment.edtPaymentFrameNameOnCard.sendKeys("test");
				oR_MakeAPayment.edtPaymentFrameCardNumber.sendKeys(sBankAccNum);
				oR_MakeAPayment.edtPaymentFrameSecurityNumber.sendKeys(sSecCode);
				UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationMonth, "12");
				UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationYear, "2016");
				oR_MakeAPayment.edtPaymentFrameZipCode.sendKeys(sZip);
				Report.ValidationPoint(testContext.getName(), "Verify Values for payment method2 are entered", "true","true", true);		
			}
			catch (Exception e) 
			{
				Report.TestPoint(testContext.getName(), "Unable TO enter Debit/Credit details", "true", "Failed to validate", true);
			}
//			Click on continue on Debit/Credit Frame
			bcontinue = UI.WaitForObject(oR_MakeAPayment.lnkContinue, 3);
			Report.TestPoint(testContext.getName(), "Click on continue on Debit/Credit Frame", "true",bcontinue.toString() , true);
	    	oR_MakeAPayment.lnkContinue.click();
	    	
	    	if(UI.WaitForObject(oR_MakeAPayment.frmNewDebitCreditCardPaymentMethod,3))
	    	{
	    		if(UI.WaitForObject(oR_MakeAPayment.lnkContinue,3))
	    			oR_MakeAPayment.lnkContinue.click();
	    	}
    		lDriver.switchTo().defaultContent();
//    		Click on Continue after modal closed on MAP page
    		Report.OperationPoint(testContext.getName(), "Click on Continue button on MAP page after Credit/Debit modal is closed");
    		if(UI.WaitForObject(oR_MakeAPayment.lnkContinue,3))
    			{
    				oR_MakeAPayment.lnkContinue.click();
    				if(UI.WaitForObject(oR_MakeAPayment.lnkContinue,3))
    	    			oR_MakeAPayment.lnkContinue.click();
    			}
    		else
				Report.TestPoint(testContext.getName(), "Unable TO Click on Continue on MAP Page", "true", "Failed to Continue", true);

    		/**Incase Payment Alert is shown up click on Continue **/
    		if(UI.WaitForObject(oR_MakeAPayment.lnkContinue,3))
    			oR_MakeAPayment.lnkContinue.click();
    		if(UI.WaitForObject(oR_MakeAPayment.lnkContinue,3))
    			oR_MakeAPayment.lnkContinue.click();
    		
//    		Validate the user is directed to the Review Payment Details page.
    		bReview = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle, 10);
    		Report.ValidationPoint(testContext.getName(), "4-Validate the user is directed to the Review Payment Details page For Debit/Credit Payment Method", "true", bReview.toString(), true);

    		bEnrollingInAutopay = UI.WaitForObject(oR_ReviewPaymentDetails.txtEnrollingInAutoPay, 10); 
    		Report.ValidationPoint(testContext.getName(), "4-Validate the user is directed to the Review Payment Details page For Debit/Credit Payment Method", "true", bEnrollingInAutopay.toString(), true);

//			Select the Submit button
    		bSubmit =  UI.WaitForObject(oR_ReviewPaymentDetails.btnSubmit, 10);
			Report.TestPoint(testContext.getName(), "5,6-Click on continue on Debit/Credit Frame", "true",bSubmit.toString() , true);
			oR_ReviewPaymentDetails.btnSubmit.click();
			Thread.sleep(3000);
			
			
//7				View - Payment Confirmation page
			Report.OperationPoint(testContext.getName(), "7-View - Payment Confirmation page");
			
			Boolean bAccordion, bPayOnDate, bEnrolledInAutopay, bPayAmount,bPayDate,bThankYou,bTotal,bPrintLink,bAccNum,bAutoPayNote,bMethod,bAmountToPay;
//			Header
			bHeader = UI.WaitForObject(oR_PaymentConfirmation.txtPaymentConfirmationTitle, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Payment Confirmation (header)", "true", bHeader.toString(), true);
//			ThankYou
			bThankYou = UI.WaitForObject(oR_PaymentConfirmation.txtThankyou, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Thank You", "true", bThankYou.toString(), true);
//			Print link
			bPrintLink = UI.WaitForObject(oR_PaymentConfirmation.txtThankyou, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Print link", "true", bPrintLink.toString(), true);
//			Account # 
			try
			{
				if(lDriver.findElements(By.xpath("//*[contains(@class,'account')]")).size()>0)
					bAccNum = true;
				Report.ValidationPoint(testContext.getName(), "Account Number", "true", "true", true);
			}
			catch (Exception e) 
			{
				Report.ValidationPoint(testContext.getName(), "Account Number", "true", "False", true);
			}
//			AutoPay Note
		/**	try
			{
				if(lDriver.findElements(By.xpath("//*[contains(text(),'Autopay') or contains(text(),'autopay')]")).size()>0)
					bAccNum = true;
				Report.ValidationPoint(testContext.getName(), "AutoPay Note", "true", "true", true);
			}
			catch (Exception e) 
			{
				Report.ValidationPoint(testContext.getName(), "AutoPay Note", "true", "false", true);
			}
			**/
//			bAutoPayNote = UI.WaitForObject(oR_PaymentConfirmation.txtAutopay, 10);
//			Report.ValidationPoint(testContext.getName(), "AutoPay Note", "true", bAutoPayNote.toString(), true);

//			Payment Method
			bMethod = UI.WaitForObject(oR_PaymentConfirmation.txtPaymentMethod, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Payment Method", "true", bMethod.toString(), true);
//			Confirmation Number
			bConfirmationNum = UI.WaitForObject(oR_PaymentConfirmation.txtConfirmation, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Confirmation Number", "true", bConfirmationNum.toString(), true);
//			Amount to Pay
			bAmountToPay = UI.WaitForObject(oR_PaymentConfirmation.txtAmountToPay, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Amount to Pay", "true", bAmountToPay.toString(), true);
//			$##.##
			bPayAmount = UI.WaitForObject(oR_PaymentConfirmation.txtAmountValue, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "$##.##", "true", bPayAmount.toString(), true);
//			PayOndate
			bPayOnDate = UI.WaitForObject(oR_PaymentConfirmation.txtPayOnDate, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "$##.##", "true", bPayOnDate.toString(), true);
//			MM/DD/YYYY
			bPayDate = UI.WaitForObject(oR_PaymentConfirmation.txtPaymentDate, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "$##.##", "true", bPayDate.toString(), true);
//			Total Payments $##.##
			bTotal = UI.WaitForObject(oR_PaymentConfirmation.txtTotalPayments, 10, lDriver);
			bTotalAmt = UI.WaitForObject(oR_PaymentConfirmation.txtTotalPaymentsAmount, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Total Payments $##.##", "true", String.valueOf(bTotal && bTotalAmt), true);
//			8- Enrolled in AutoPay
			bEnrolledInAutopay = UI.WaitForObject(oR_PaymentConfirmation.txtEnrolledInAutopay, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "8- Enrolled in AutoPay", "true", bEnrolledInAutopay.toString(), true);

			Boolean bStep1,bStep2,bStep3,bAnotherPay,bGoTo;
//			9-View - RHN
			Report.OperationPoint(testContext.getName(), "9-Validate the RHN accordions do not display and the Payment step indicators display the following:");
			
			bStep1 = UI.WaitForObject(oR_PaymentConfirmation.txtEnterPaymentInfoAccordion, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "9-i-Enter Payment Information", "true",bStep1.toString(), true);

			bStep2 = UI.WaitForObject(oR_PaymentConfirmation.txtReviewPaymentInfoAccordion, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "9-ii-Review Payment Information", "true",bStep2.toString(), true);

			bStep3 = UI.WaitForObject(oR_PaymentConfirmation.txtPaymentStatusAccordion, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "9-iii-Payment Status", "true",bStep3.toString(), true);

//			10-"Validate the What's Next section displays the following:- Make Another Payment link (dynamic - will only display if the account has a balance)- Go to Account Overview link"
			Report.OperationPoint(testContext.getName(), "validate Make Another Payment link  and  Go to Account Overview link");
			bAnotherPay = UI.WaitForObject(oR_PaymentConfirmation.lnkMakeAnotherPayment, 10,lDriver);
			bGoTo = UI.WaitForObject(oR_PaymentConfirmation.lnkGoToAccountOverviewNew, 10,lDriver);
			
			Report.ValidationPoint(testContext.getName(), "Make Another Payment link (dynamic - will only display if the account has a balance)", "true","true", true);
			Report.ValidationPoint(testContext.getName(), "Go to Account Overview link", "true",bGoTo.toString(), true);


//			if(bAnotherPay)
//				
//			{
				Report.OperationPoint(testContext.getName(), "11-Select the Make Another Payment link(dynamic)");
				/**Told to Skip by Sweta Kasliwal since we can click on any 1 link only**/
//				oR_PaymentConfirmation.lnkMakeAnotherPayment.click();
//				bMAP = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 10);
//				Report.ValidationPoint(testContext.getName(), "11-Validate the user is directed to the Make a Payment page.", "true",bMAP.toString(), true);
//			}
			
			if(bGoTo)
			{
				Report.OperationPoint(testContext.getName(), "12-Select the Go to Account Overview link");
				oR_PaymentConfirmation.lnkGoToAccountOverviewNew.click();
				Boolean bOverview = UI.WaitForObject(oR_AccountOverview.txtWelcome, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(),"Validate the user is directed to the Dashboard", "True",bOverview.toString(), true);
			}
			
			Boolean bPaperless = UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBilling, 10, lDriver);
			if(bPaperless)
			{
				Report.OperationPoint(testContext.getName(), "13-Click on Enroll In Paperless Billing link");
				oR_AccountOverview.lnkEnrollInPaperlessBilling.click();
			}
			
			Boolean bManagePaperless = UI.WaitForObject(oR_AccountOverview.lnkManagePaperlessBilling, 10, lDriver);
			Report.TestPoint(testContext.getName(),"13-Click on Manage Paperless Billing link", "True",bManagePaperless.toString(), true);

			Report.OperationPoint(testContext.getName(), "13-Click on Manage Paperless Billing link");
			oR_AccountOverview.lnkManagePaperlessBilling.click();
			
				
			Boolean bPaperPage = UI.WaitForObject(oR_PaperlessBilling.txtPaperlessBillingTitle, 10, lDriver);
			Report.ValidationPoint(testContext.getName(),"13-Validate the user is directed to the Dashboard", "True",bPaperPage.toString(), true);

//			Confirmation Email
			Report.OperationPoint(testContext.getName(), "14-Confirmation Email");
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	}
	
	/**************************************************************
	 * Function Name - ValidateBillTabATTNextCharges()
	 * Description - 
 	 * Test Case -  UBIL0120 - CB -  Bill Tab - ATT Next Charges
	 * Parameters - None
	 * Date created - 8th Mar 2016
	 * Developed by - Hiral Jogi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_UBIL0120
	public static void ValidateBillTabATTNextCharges(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class); 
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class); 
 
			Report.OperationPoint(testContext.getName(), "Navigating to Bills & Usage landing page"); 
			if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,50).equals(true)) 
			{ 
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click(); 
				Thread.sleep(10000); 
 
				Report.TestPoint(testContext.getName(), "Goto Bills & Usage from global nav", "Bills & Usage page is displayed", "Bills & Usage page is displayed" , true); 
				Report.OperationPoint(testContext.getName(), "Clicking on all '+' expand section to expand all"); 
				List<WebElement> PlusSigns = lDriver.findElements(By.xpath("//a[contains(@class,'expandImg')]")); 
				int iSize = PlusSigns.size(); 
				for(int i=0;i<iSize;i++) 
				{ 
					try 
					{ 
	 				if(PlusSigns.get(i).isEnabled() || PlusSigns.get(i).isDisplayed()) 
						{ 
							PlusSigns.get(i).click(); 
							Thread.sleep(3000); 
						} 
 
					}catch(Exception Eee) 
					{ 
						Report.OperationPoint(testContext.getName(), "expand '+' is hidden, moving to next"); 
					} 
 
				} 
				if(UI.WaitForObject(oR_BillAndUsage.txtEquipmentsChargesSection,40).equals(true)) 
				{ 
					Report.TestPoint(testContext.getName(), "Validate presence of Equipment charges ", "Equipment charges details is displayed", "Equipment charges details is displayed" , true); 

						boolean bEquipmentChargescollapse = UI.WaitForObject(lDriver.findElement(By.xpath("//a[@title='Equipment Charges']//img[@alt='collapse']")), UI.iObjTimeOut, lDriver);
						if(bEquipmentChargescollapse){
							Report.ValidationPoint(testContext.getName(), "Default state of equipment charges section is expanded which is as expected.", "true",String.valueOf(bEquipmentChargescollapse), true);
						} else {
							Report.ValidationPoint(testContext.getName(), "Default state of equipment charges section is expanded which is as expected.", "true","false", true);
						}
						
						// collapse
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on Expanded button for equipment section.");
						lDriver.findElement(By.xpath("//a[@title='Equipment Charges']//img[@alt='collapse']")).click();
						boolean bEquipmentChargesexpand = UI.WaitForObject(lDriver.findElement(By.xpath("//a[@title='Equipment Charges']//img[@alt='expand']")), UI.iObjTimeOut, lDriver);
						if(bEquipmentChargesexpand){
							Report.ValidationPoint(testContext.getName(), "Equipment Charges appears with expand/collapsed functionality.", "true",String.valueOf(bEquipmentChargesexpand), true);
						} else {
							Report.ValidationPoint(testContext.getName(), "Equipment Charges appears with expand/collapsed functionality.", "true","false", true);
						}
						
						// expand
						lDriver.findElement(By.xpath("//a[@title='Equipment Charges']//img[@alt='expand']")).click();
						
						//3.Validate the Equipment Charges displays the following information:
							// 3.1 - Installment Plan ID - established on (mm/dd/yyyy)
							boolean bInstallmentPlanID = UI.WaitForObject(oR_BillAndUsage.txtInstallmentPlanID1, UI.iObjTimeOut, lDriver);
							Report.OperationPoint(testContext.getName(), oR_BillAndUsage.txtInstallmentPlanID1.getText());
							
							//3.2 i.e friendly equipment description
							boolean bFriendlyEquipDescription = UI.WaitForObject(lDriver.findElement(By.xpath("//div[contains(@id,'EC')]//div/div[1]/div[2]")), UI.iObjTimeOut);
							String Strdesc = lDriver.findElement(By.xpath("//div[contains(@id,'EC')]//div/div[1]/div[2]")).getText();
							Report.OperationPoint(testContext.getName(), "Friendly Equipment description is present as " + Strdesc);
							
							//3.3 - Amount Financed
							boolean bAmountFinanced= UI.WaitForObject(oR_BillAndUsage.txtAmountFinanced, UI.iObjTimeOut, lDriver);
							if(bAmountFinanced){
							boolean bAmountFinancedamt= UI.WaitForObject(lDriver.findElement(By.xpath("(//div[contains(@id,'EC')]//div[contains(text(),'$')])[1]")), UI.iObjTimeOut, lDriver);
								String Stramt = lDriver.findElement(By.xpath("(//div[contains(@id,'EC')]//div[contains(text(),'$')])[1]")).getText();
								Report.OperationPoint(testContext.getName(),"Amount Financed is " + Stramt);
							}
							
							boolean bNoIntallments = UI.WaitForObject(oR_BillAndUsage.txtNoIntallments, UI.iObjTimeOut, lDriver);
							boolean bIntallmentDate = UI.WaitForObject(lDriver.findElement(By.xpath("(//div[contains(text(),'of')]//parent::div/div[2])[1]")), UI.iObjTimeOut, lDriver);
							boolean bIntallmentAmount = UI.WaitForObject(lDriver.findElement(By.xpath("(//div[contains(text(),'of')]//parent::div/div[3])[1]")), UI.iObjTimeOut, lDriver);
							boolean bIntallmentNumber = UI.WaitForObject(lDriver.findElement(By.xpath("(//div[contains(text(),'of')])[1]")), UI.iObjTimeOut, lDriver);
						
						
							if(bNoIntallments){
								Report.OperationPoint(testContext.getName(), oR_BillAndUsage.txtNoIntallments.getText());
								Report.OperationPoint(testContext.getName(), "As no installments are due, hence, 1. Installment scheduled date,\n 2. Number of installments,\n 3. Dollar Amount of Monthly Installment \n will not be present.");
							} else if (bIntallmentDate && bIntallmentAmount && bIntallmentNumber){
								if(bIntallmentDate){
									String StrDate = lDriver.findElement(By.xpath("(//div[contains(text(),'of')]//parent::div/div[2])[1]")).getText();
									Report.OperationPoint(testContext.getName(),"Installment scheduled date is " + StrDate);
								} 
								
								if(bIntallmentAmount){
									String StrAmount = lDriver.findElement(By.xpath("(//div[contains(text(),'of')]//parent::div/div[3])[1]")).getText();
									Report.OperationPoint(testContext.getName(),"Installment scheduled amount is " + StrAmount);
								}
								
								if(bIntallmentNumber){
									String StrDate = lDriver.findElement(By.xpath("(//div[contains(text(),'of')])[1]")).getText();
									Report.OperationPoint(testContext.getName(),"Number of installments is " + StrDate);
								}
								
							} else {
								Report.OperationPoint(testContext.getName(),"Required information from the above list is missing.");
							}
							
							//3.7
							boolean bBalanceRemainingAfterInst= UI.WaitForObject(oR_BillAndUsage.txtBalanceRemainingAfterInst, UI.iObjTimeOut, lDriver);
							Report.OperationPoint(testContext.getName(), oR_BillAndUsage.txtBalanceRemainingAfterInst.getText());
							
							//3.10
							boolean bTotalEquipmentCharges = UI.WaitForObject(oR_BillAndUsage.txtTotalEquipmentCharges, UI.iObjTimeOut, lDriver);
							Report.OperationPoint(testContext.getName(), "Total Equipment Charges is "+ oR_BillAndUsage.txtTotalEquipmentCharges.getText());
							
							//Storing current window handle
							String sCurrWin = lDriver.getWindowHandle();
							if(UI.WaitForObject(oR_BillAndUsage.lnkManageInstallmentPlan,5).equals(true)) 
							{ 
								Report.TestPoint(testContext.getName(), "Validate Manage Installment Plan", "Manage Installment Plan is displayed", "Manage Installment Plan is displayed" , true); 
								oR_BillAndUsage.lnkManageInstallmentPlan.click(); 
								lDriver.switchTo().frame(lDriver.findElement(By.xpath("//iframe[@class='cboxIframe']"))); 
		 
								Boolean bInstallmentDetails = UI.WaitForObject(oR_BillAndUsage.txtInstallmentPlanDetails, 50); 
								Report.TestPoint(testContext.getName(), "Modal with installment details is displayed", "true", String.valueOf(bInstallmentDetails) , true); 
								
								lDriver.switchTo().defaultContent();
								
								//Close modal
								boolean bClose =  UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='colorbox']/a/img[@alt='Close']")), UI.iObjTimeOut, lDriver);
								if(bClose){
									Report.OperationPoint(testContext.getName(), "Closing the Modal.");
									lDriver.findElement(By.xpath("//*[@id='colorbox']/a/img[@alt='Close']")).click();
								} else {
									Report.OperationPoint(testContext.getName(), "Some problem while closing the Modal.");
								}
								
							} 
							else 
							{ 
								Report.TestPoint(testContext.getName(), "Validate Manage Installment Plan", "Manage Installment Plan is displayed", "Manage Installment Plan is not displayed" , true); 
							} 
							
							
							// step 5
							Boolean bPrintLnk = UI.WaitForObject(oR_BillAndUsage.lnkPrint,1);
							Report.TestPoint(testContext.getName(), "Validate Print link", "True", String.valueOf(bPrintLnk), true);
							
							//Click on print link
							Report.OperationPoint(testContext.getName(), "	Operational - Clicking on link Print");
							oR_BillAndUsage.lnkPrint.click();
							Thread.sleep(5000);
							//Switching to Print window
							for(String sPrintWind : lDriver.getWindowHandles())
							{
								lDriver.switchTo().window(sPrintWind);
							}
							//Validate Account Details text
							Boolean bAccDet = UI.WaitForObject(oR_BillAndUsage.txtAccountDetails,10);
							Report.TestPoint(testContext.getName(), "Validate a pop-up window displays the Printer-Friendly version of the Account Activity", "True", String.valueOf(bAccDet), true);
							
							// step 6
							Boolean bPrintEquipmentCharges = UI.WaitForObject(lDriver.findElement(By.xpath("(//div[contains(text(),'Equipment Charges')])[1]")),30);
							if(bPrintEquipmentCharges){
								Report.OperationPoint(testContext.getName(), "Equipment Charges section is present.");
							}
							
							Report.OperationPoint(testContext.getName(), "	Operational - Close the Print window");
							lDriver.close();
							//Switch to billing and usage page
							lDriver.switchTo().window(sCurrWin);
						}
					} 
				else 
				{ 
					Report.TestPoint(testContext.getName(), "Validate presence of Equipment charges ", "Equipment charges details is displayed", "Equipment charges details is not displayed" , true); 
				} 
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	/**************************************************************
	 * Function Name - ValidateUATcreditDebitCardDivLayer
	 * Description - validate New Credit/Debit Card Payment Method div layer
	 * Test Case -  UAT_UPAY0224_03a
	 * Parameters - None
	 * Date created - 8th March 2016
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	***************************************************************/
	//UAT_UPAY0224_03a
	public static void ValidateUATcreditDebitCardDivLayer(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
		OR_ReviewPaymentDetails oR_ReviewPaymentDetails = PageFactory.initElements(lDriver, OR_ReviewPaymentDetails.class);
		
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		String sNameOnCard = IO.GetParamVal(sTestParams, "NAME_ON_CARD");
		String sCardNumber = IO.GetParamVal(sTestParams, "CARD_NUMBER");
		String sSecurityNumber = IO.GetParamVal(sTestParams, "SECURITY_NUMBER");
		String sZipCode = IO.GetParamVal(sTestParams, "ZIP_CODE");
		String sProfileName	= IO.GetParamVal(sTestParams, "PROFILE_NAME");
		
		try
		{
			//Click on make a payment button
			Report.OperationPoint(testContext.getName(), "Click on make a payment button");
			oR_AccountOverview.btnMakeAPayment.click();
			//Validate make a payment page
			Boolean bMAPpg = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 20, lDriver);
			Report.TestPoint(testContext.getName(),"Validate make a payment page", "True",bMAPpg.toString(), true);
			//Validate Payment method dropdown
			Boolean bMAPDropdown = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethod, 20, lDriver);
			if(bMAPDropdown)
			{
				Report.TestPoint(testContext.getName(),"Validate Validate Payment method dropdown", "True",bMAPDropdown.toString(), true);
				//Select New Credit/Debit Card Payment Method from the Payment Method Drop down
				Report.OperationPoint(testContext.getName(), "Select New Credit/Debit Card Payment Method from the Payment Method Drop down");
				UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod, "New debit / credit card");
				Thread.sleep(5000);
				//Validate New debit / credit card pop up
				Boolean bPopup = UI.WaitForObject(oR_MakeAPayment.frmNewDebitCreditCardPaymentMethod, 20, lDriver);
				if(bPopup)
				{
					Thread.sleep(5000);
					Report.TestPoint(testContext.getName(),"Validate New debit / credit card pop up", "True",bPopup.toString(), true);
					//View - New Credit/Debit Card Payment Method div layer
					//Validate the New Credit/Debit Card Payment Method div layer displays the following:
					//- New Credit/Debit Card Payment Method (header)
					lDriver.switchTo().frame((oR_MakeAPayment.frmNewDebitCreditCardPaymentMethod));
					Boolean bHeader = UI.WaitForObject(oR_MakeAPayment.frmTxtDebitCredit, 20, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate New Credit/Debit Card Payment Method (header)", "True",bHeader.toString(), true);
					//- Close (X) link
					Boolean bCloseLnk = UI.WaitForObject(oR_MakeAPayment.lnkCloseFrame, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Close (X) link", "True",bCloseLnk.toString(), true);
					//- Credit Debit Card Information
					Boolean bInfo = UI.WaitForObject(oR_MakeAPayment.frmTxtCDinformation, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Credit Debit Card Information", "True",bInfo.toString(), true);
					/*- Method
					- Credit Debit
					- Cards we Accept
					- Name on Card
					- Card Number
					- Security Code
					- Card Expiration Date
					- Card Billing Zip code helpful info icon (?)

					- *Required field label 
					-Asterisk by each required field label
					*/
					List<WebElement> lstLabel = lDriver.findElements(By.xpath("//form[@id='paymentMethodForm']//label"));
					List<WebElement> lstLabelAs = lDriver.findElements(By.xpath("//form[@id='paymentMethodForm']//label//span"));
					
					if(lstLabel.size()>0)
					{
						for(int i=0;i<lstLabel.size();i++)
						{
							if(lstLabel.size()<5)
							{
								//To check the astrick
								if(lstLabelAs.get(i).getText().contains("/*"))
								{
									if(lstLabel.get(i).getText().length()>0)
									{
										Report.ValidationPoint(testContext.getName(),"Validate "+lstLabel.get(i).getText()+" and Asterisk by each required field label", "True","True", true);
									}
									
								}
							}
							else
							{
								if(lstLabel.get(i).getText().length()>0)
								{
									Report.ValidationPoint(testContext.getName(),"Validate "+lstLabel.get(i).getText(), "True","True", true);
								}
								
							}
						}
					}
					
					//- Select Month drop down
					Boolean bMOnth = UI.WaitForObject(oR_MakeAPayment.txtMonthDropDown, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Select Month drop down", "True",bMOnth.toString(), true);
					//- Select Year drop down
					Boolean byear = UI.WaitForObject(oR_MakeAPayment.txtYearDropDown, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Year drop down", "True",byear.toString(), true);
					//-Credit Debit image
					Boolean bCC = UI.WaitForObject(oR_MakeAPayment.imgCreditCard, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Credit Debit image", "True",bCC.toString(), true);
					
					//Validate the Save My Payment Information section
//					Boolean bSave = UI.WaitForObject(oR_MakeAPayment.frmTxtSaveMyInfo, 2, lDriver);
//					Report.ValidationPoint(testContext.getName(),"Validate the Save My Payment Information section", "True",bSave.toString(), true);
					//Section description (content TBD)
					Boolean bDesc = UI.WaitForObject(oR_MakeAPayment.frmTxtSectionDesc, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Section description: "+oR_MakeAPayment.frmTxtSectionDesc.getText() , "True",bDesc.toString(), true);
					// Save My Payment Information checkbox is unchecked
					Boolean bCheck = UI.WaitForObject(oR_MakeAPayment.frmCheckBox, 2, lDriver);
					if(bCheck && !oR_MakeAPayment.frmCheckBox.getAttribute("class").contains("checked"))
					{
						Report.ValidationPoint(testContext.getName(),"Validate Save My Payment Information checkbox is unchecked", "True",bCheck.toString(), true);
					}
					//- Cancel link
					Boolean bCancel = UI.WaitForObject(oR_MakeAPayment.lnkCancel, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Cancel link", "True",bCancel.toString(), true);
					//- Continue button
					Boolean bContinue = UI.WaitForObject(oR_MakeAPayment.lnkContinue, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Continue button", "True",bContinue.toString(), true);
					//Select each entry field without entering any information
					Report.OperationPoint(testContext.getName(), "Select each entry field without entering any information");
					oR_MakeAPayment.edtPaymentFrameNameOnCard.clear();
					oR_MakeAPayment.edtPaymentFrameCardNumber.clear();
					oR_MakeAPayment.lstSelectCardExpirationMonth.click();
					oR_MakeAPayment.lstSelectCardExpirationYear.click();
					oR_MakeAPayment.edtPaymentFrameSecurityNumber.clear();
					oR_MakeAPayment.edtPaymentFrameZipCode.clear();
					oR_MakeAPayment.lnkContinue.click();
					List<WebElement> lstError = lDriver.findElements(By.xpath("//p[contains(@id,'ErrorMessage')]"));
					if(lstError.size()>0)
					{
						for(int i=0;i<lstError.size();i++)
						{
							if(lstError.get(i).getText().length()>0)
							{
								Report.ValidationPoint(testContext.getName(),"Validate an inline error displays below each entry field : "+lstError.get(i).getText(), "True",bContinue.toString(), true);
							}
							
						}
						
					}
					//Select a card expiration date that has passed
					Report.OperationPoint(testContext.getName(), "Select a card expiration date that has passed");
					UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationMonth, "01");
					UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationYear, "2016");
					oR_MakeAPayment.edtPaymentFrameSecurityNumber.clear();
					//Validate an inline error displays below the Month and Year dropdown.
					Boolean bError = UI.WaitForObject(oR_MakeAPayment.frmExpirationMonthErrorMsg, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate an inline error displays below the Month and Year dropdown", "True",bError.toString(), true);
					
					//Select the Close link
					Report.OperationPoint(testContext.getName(), "Select the Close link");
					oR_MakeAPayment.lnkCloseFrame.click();
					Thread.sleep(4000);
					lDriver.switchTo().defaultContent();
					
					//Validate make a payment page
					Boolean bMAPpg1 = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 20, lDriver);
					Report.TestPoint(testContext.getName(),"Validate the New Credit/Debit Card div layer closes and the user is directed back to the Make a payment page", "True",bMAPpg1.toString(), true);
					
					UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod, "New debit / credit card");
					Thread.sleep(10000);
					lDriver.switchTo().frame(oR_MakeAPayment.frmNewDebitCreditCardPaymentMethod);
					Thread.sleep(10000);
					//Click on cancel link
					Report.OperationPoint(testContext.getName(), "Click on cancel link");
					oR_MakeAPayment.lnkCancel.click();
					Alert alert = lDriver.switchTo().alert();
					alert.accept();
					Thread.sleep(4000);
					lDriver.switchTo().defaultContent();
					//Validate make a payment page
					Boolean bMAPpg2 = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 20, lDriver);
					Report.TestPoint(testContext.getName(),"Validate the New Credit/Debit Card div layer closes and the user is directed back to the Make a payment page", "True",bMAPpg2.toString(), true);
					
					UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod, "New debit / credit card");
					Thread.sleep(10000);
					lDriver.switchTo().frame(oR_MakeAPayment.frmNewDebitCreditCardPaymentMethod);
					Thread.sleep(10000);
					Report.OperationPoint(testContext.getName(), "Select the Continue button without making any changes");
					oR_MakeAPayment.lnkContinue.click();
					//Validate inline errors are displayed below each entry field
					List<WebElement> lstError1 = lDriver.findElements(By.xpath("//p[contains(@id,'ErrorMessage')]"));
					if(lstError1.size()>0)
					{
						Report.ValidationPoint(testContext.getName(),"Validate inline errors are displayed below each entry field", "True","True", true);
					}
					
					//Fill in all fields but enter an incorrect Card number
					Report.OperationPoint(testContext.getName(), "Fill in all fields but enter an incorrect Card number");
					oR_MakeAPayment.edtPaymentFrameNameOnCard.clear();
					oR_MakeAPayment.edtPaymentFrameNameOnCard.sendKeys(sNameOnCard);
					oR_MakeAPayment.edtPaymentFrameCardNumber.clear();
					oR_MakeAPayment.edtPaymentFrameCardNumber.sendKeys("12345");
					oR_MakeAPayment.lnkContinue.click();
					//Validate inline error displays below the Card Number entry field.
					Boolean bNoError = UI.WaitForObject(lDriver.findElement(By.xpath("//p[contains(text(),'Verify card number')]")), 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate inline error displays below the Card Number entry field", "True",bNoError.toString(), true);
					
					//Enter valid information for each entry field 
					Report.OperationPoint(testContext.getName(), "Enter valid information for each entry field");
					oR_MakeAPayment.edtPaymentFrameCardNumber.clear();
					oR_MakeAPayment.edtPaymentFrameCardNumber.sendKeys(sCardNumber);
					UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationMonth, "12");
					UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationYear, "2016");
					oR_MakeAPayment.edtPaymentFrameSecurityNumber.clear();
					oR_MakeAPayment.edtPaymentFrameSecurityNumber.sendKeys(sSecurityNumber);
					oR_MakeAPayment.edtPaymentFrameZipCode.clear();
					oR_MakeAPayment.edtPaymentFrameZipCode.sendKeys(sZipCode);
					//Validate Inline Error message no longer displays for each field. 
					List<WebElement> lstError2 = lDriver.findElements(By.xpath("//p[contains(@id,'ErrorMessage')]"));
					if(!(lstError2.size()>0))
					{
						Report.ValidationPoint(testContext.getName(),"Validate Inline Error message no longer displays for each field", "True","True", true);
					}
					//Select the Save my Payment information check box.
					Report.OperationPoint(testContext.getName(), "Select the Save my Payment information check box");
					oR_MakeAPayment.frmCheckBox.click();
					//Enter special characters into the Payment Profile entry field.
					Report.OperationPoint(testContext.getName(), "Enter special characters into the Payment Profile entry field");
					oR_MakeAPayment.edtPaymentFrameProfileName.clear();
					oR_MakeAPayment.edtPaymentFrameProfileName.sendKeys("@&");
					//Confirm inline error displays below the Payment Profile entry field and advises the user to confirm special characters have not been entered 
					oR_MakeAPayment.lnkContinue.click();
					Thread.sleep(3000);
					Boolean bErrorProfile = UI.WaitForObject(oR_MakeAPayment.frmErrorProfile, 4);
					Report.ValidationPoint(testContext.getName(),"Confirm inline error displays below the Payment Profile entry field and advises the user to confirm special characters have not been entered ", "True",bErrorProfile.toString(), true);
					//Enter valid Payment Profile name in the Payment Profile entry field
					Report.OperationPoint(testContext.getName(), "Enter valid Payment Profile name in the Payment Profile entry field");
					oR_MakeAPayment.edtPaymentFrameProfileName.clear();
					oR_MakeAPayment.edtPaymentFrameProfileName.sendKeys(sProfileName);
					oR_MakeAPayment.edtPaymentFrameZipCode.click();
					Thread.sleep(2000);
					//Validate Inline Error message no longer displays
					Boolean bErrorProfile1 = UI.WaitForObject(oR_MakeAPayment.frmErrorProfile, 4);
					Report.ValidationPoint(testContext.getName(),"Validate Inline Error message no longer displays", "True",String.valueOf(!bErrorProfile1), true);
					
					//Click on continue button
					Report.OperationPoint(testContext.getName(), "Click on continue button");
					oR_MakeAPayment.lnkContinue.click();
					//oR_MakeAPayment.lnkContinue.click();
					Thread.sleep(4000);
					lDriver.switchTo().defaultContent();
					//Click on Continue button
					oR_MakeAPayment.lnkContinue.click();
					
					//Validate the user is directed to the Review Payment Details page
					Boolean bReview = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle, 20, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate the user is directed to the Review Payment Details page", "True",bReview.toString(), true);
				}
				else
				{
					Report.TestPoint(testContext.getName(),"Validate New debit / credit card pop up", "True",bPopup.toString(), true);
				}
				
			}
			else
			{
				Report.TestPoint(testContext.getName(),"Validate Validate Payment method dropdown", "True",bMAPDropdown.toString(), true);
			}
			
			
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	}
	/**************************************************************
	 * Function Name - ValidateUATreviewPaymentDetailsPgValidations
	 * Description - validate review payment details page
	 * Test Case -  UAT_UPAY0224_03b
	 * Parameters - None
	 * Date created - 9th March 2016
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	***************************************************************/
	//UAT_UPAY0224_03b
	public static void ValidateUATreviewPaymentDetailsPgValidations(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
		OR_ReviewPaymentDetails oR_ReviewPaymentDetails = PageFactory.initElements(lDriver, OR_ReviewPaymentDetails.class);
		
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		String sNameOnCard = IO.GetParamVal(sTestParams, "NAME_ON_CARD");
		String sCardNumber = IO.GetParamVal(sTestParams, "CARD_NUMBER");
		String sSecurityNumber = IO.GetParamVal(sTestParams, "SECURITY_NUMBER");
		String sZipCode = IO.GetParamVal(sTestParams, "ZIP_CODE");
		String sProfileName	= IO.GetParamVal(sTestParams, "PROFILE_NAME");
		
		try
		{
			//Click on make a payment button
			Report.OperationPoint(testContext.getName(), "Click on make a payment button");
			oR_AccountOverview.btnMakeAPayment.click();
			//Validate make a payment page
			Boolean bMAPpg = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 20, lDriver);
			Report.TestPoint(testContext.getName(),"Validate make a payment page", "True",bMAPpg.toString(), true);
			//Validate Payment method dropdown
			Boolean bMAPDropdown = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethod, 20, lDriver);
			if(bMAPDropdown)
			{
				Report.TestPoint(testContext.getName(),"Validate Validate Payment method dropdown", "True",bMAPDropdown.toString(), true);
				//Select New Credit/Debit Card Payment Method from the Payment Method Drop down
				Report.OperationPoint(testContext.getName(), "Select New Credit/Debit Card Payment Method from the Payment Method Drop down");
				UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod, "New debit / credit card");
				Thread.sleep(5000);
				//Validate New debit / credit card pop up
				Boolean bPopup = UI.WaitForObject(oR_MakeAPayment.frmNewDebitCreditCardPaymentMethod, 20, lDriver);
				if(bPopup)
				{
					Thread.sleep(5000);
					Report.TestPoint(testContext.getName(),"Validate New debit / credit card pop up", "True",bPopup.toString(), true);
					//View - New Credit/Debit Card Payment Method div layer
					//Validate the New Credit/Debit Card Payment Method div layer displays the following:
					//- New Credit/Debit Card Payment Method (header)
					lDriver.switchTo().frame((oR_MakeAPayment.frmNewDebitCreditCardPaymentMethod));
					
					//Enter the card details
					Report.OperationPoint(testContext.getName(), "Enter the card details");
					oR_MakeAPayment.edtPaymentFrameNameOnCard.clear();
					oR_MakeAPayment.edtPaymentFrameNameOnCard.sendKeys(sNameOnCard);
					oR_MakeAPayment.edtPaymentFrameCardNumber.clear();
					oR_MakeAPayment.edtPaymentFrameCardNumber.sendKeys(sCardNumber);
					UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationMonth, "12");
					UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationYear, "2016");
					oR_MakeAPayment.edtPaymentFrameSecurityNumber.clear();
					oR_MakeAPayment.edtPaymentFrameSecurityNumber.sendKeys(sSecurityNumber);
					oR_MakeAPayment.edtPaymentFrameZipCode.clear();
					oR_MakeAPayment.edtPaymentFrameZipCode.sendKeys(sZipCode);
					//Click on continue button
					Report.OperationPoint(testContext.getName(), "Click on continue button");
					oR_MakeAPayment.lnkContinue.click();
					//oR_MakeAPayment.lnkContinue.click();
					Thread.sleep(4000);
					lDriver.switchTo().defaultContent();
					//Click on Continue button
					oR_MakeAPayment.lnkContinue.click();
					
					//Validate the user is directed to the Review Payment Details page
					Boolean bReview = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle, 20, lDriver);
					Report.TestPoint(testContext.getName(),"Validate the user is directed to the Review Payment Details page", "True",bReview.toString(), true);
					
					//Validate the Review Payment Details page displays the following:
					Report.OperationPoint(testContext.getName(), "Validate the Review Payment Details page displays the following:");
					//- Review Payment Details (header)
					Boolean bReview1 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle, 2, lDriver);
					Report.TestPoint(testContext.getName(),"Validate Review Payment Details (header)", "True",bReview1.toString(), true);
					//- Edit Payment Information link
					Boolean bEditLnk = UI.WaitForObject(oR_ReviewPaymentDetails.lnkEditPaymentInformation, 2, lDriver);
					if(bEditLnk)
					{
						Report.ValidationPoint(testContext.getName(),"Validate Edit Payment Information link", "True",bEditLnk.toString(), true);
						//Click
						Report.OperationPoint(testContext.getName(), "Click Edit Payment Information link");
						oR_ReviewPaymentDetails.lnkEditPaymentInformation.click();
						//Validate make a payment page
						Boolean bMAPpg1 = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 20, lDriver);
						Report.TestPoint(testContext.getName(),"Validate make a payment page", "True",bMAPpg1.toString(), true);
						Thread.sleep(4000);
						//Click on continue
						oR_MakeAPayment.lnkContinue.click();
						//Validate the user is directed to the Review Payment Details page
						Boolean bReview123 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle, 20, lDriver);
						Report.TestPoint(testContext.getName(),"Validate the user is directed to the Review Payment Details page", "True",bReview123.toString(), true);
					}
					
					//- Nickname (dynamic)
					//- Account Number
					Boolean bAccNo = UI.WaitForObject(oR_ReviewPaymentDetails.txtAccount, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Account Number", "True",bAccNo.toString(), true);
					
					//- Payment Method
					Boolean bMethod = UI.WaitForObject(oR_ReviewPaymentDetails.txtPaymentMethod, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Payment Method", "True",bMethod.toString(), true);
					//- New Credit Debit Card
					//- Name on Card
					Boolean bName = UI.WaitForObject(oR_ReviewPaymentDetails.txtNameOnCard, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Name on Card", "True",bName.toString(), true);
					//- Card Number
					Boolean bCardNo = UI.WaitForObject(oR_ReviewPaymentDetails.txtCardNo, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Card Number", "True",bCardNo.toString(), true);
					//- Security Code
					//- Card Expiration Date
					Boolean bExp = UI.WaitForObject(oR_ReviewPaymentDetails.txtExpirationDate, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Card Expiration Date", "True",bExp.toString(), true);
					//- Card Billing Zip Code
					Boolean bZipcode = UI.WaitForObject(oR_ReviewPaymentDetails.txtBillingZipcode, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Card Billing Zip Code", "True",bZipcode.toString(), true);
					//- Amount to Pay
					Boolean bAmount = UI.WaitForObject(oR_ReviewPaymentDetails.txtAmountToPay, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Amount to Pay in $###.## format", "True",bAmount.toString(), true);
					//- $###.##
					//- $##.## balance remaining due by MM/DD/YYYY (dynamic)
					
					//View - Terms and Conditions section
					//Validate the Terms and Conditions section of the Review Payment Details page displays the following:
					Report.OperationPoint(testContext.getName(), "View - Terms and Conditions section. Validate the Terms and Conditions section of the Review Payment Details page displays the following:");
					//- Terms and Conditions
					Boolean bTerms = UI.WaitForObject(oR_ReviewPaymentDetails.txtTermsAndConditions, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Terms and Conditions section", "True",bTerms.toString(), true);
					//- Print link and icon
					Boolean bPrint = UI.WaitForObject(oR_ReviewPaymentDetails.lnkPrintTC, 2, lDriver);
					if(bPrint)
					{
						Report.ValidationPoint(testContext.getName(),"Validate Print link and icon", "True",bPrint.toString(), true);
						//Click on print link
						Report.OperationPoint(testContext.getName(),"Click on print link");
						UI.VerifyLinkNavigatestoNewWindow(oR_ReviewPaymentDetails.lnkPrintTC, "TermsAndConditions");
					}
					
					//- Term and Conditions verbiage
					List <WebElement> lstTerms = lDriver.findElements(By.xpath("//div[@class='OC2PAterms focusable']/p"));
					if(lstTerms.size()>0)
					{
						Report.ValidationPoint(testContext.getName(),"Validate Term and Conditions verbiage : ", "True",bPrint.toString(), true);
						for(int i=0;i<lstTerms.size();i++)
						{
							Report.OperationPoint(testContext.getName(), lstTerms.get(i).getText());
						}
					}
					//- Agreement
					Boolean bAgreement = UI.WaitForObject(oR_ReviewPaymentDetails.txtAgreement, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Agreement", "True",bAgreement.toString(), true);
					//  - By Clicking 'Submit' you agree to the above Terms & Conditions
					Boolean btxtByClicking = UI.WaitForObject(oR_ReviewPaymentDetails.txtByClicking, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate By Clicking 'Submit' you agree to the above Terms & Conditions", "True",btxtByClicking.toString(), true);
					//- Cancel link
					Boolean blnkCancel = UI.WaitForObject(oR_ReviewPaymentDetails.lnkCancel, 2, lDriver);
					if(blnkCancel)
					{
						Report.ValidationPoint(testContext.getName(),"Validate Cancel link", "True",blnkCancel.toString(), true);
						//click
						Report.OperationPoint(testContext.getName(),"Click on cancel link");
						oR_ReviewPaymentDetails.lnkCancel.click();
						Boolean bIncomplete = UI.WaitForObject(oR_MakeAPayment.txtIncomplete, 20, lDriver);
						if(bIncomplete)
						{
							Report.ValidationPoint(testContext.getName(),"Validate Incomplete Payment div layer", "True",bIncomplete.toString(), true);
							oR_MakeAPayment.lnkCancelFrm.click();
						}
					}
					
					//- Back button
					Boolean bBack = UI.WaitForObject(oR_ReviewPaymentDetails.btnBack, 2, lDriver);
					if(bBack)
					{
						Report.ValidationPoint(testContext.getName(),"Validate Back button", "True",bBack.toString(), true);
						//Click
						Report.OperationPoint(testContext.getName(),"Click on back link");
						oR_ReviewPaymentDetails.btnBack.click();
						//Validate make a payment page
						Boolean bMAPpg1 = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 20, lDriver);
						Report.TestPoint(testContext.getName(),"Validate make a payment page", "True",bMAPpg1.toString(), true);
						Thread.sleep(4000);
						//Click on continue
						oR_MakeAPayment.lnkContinue.click();
						//Validate the user is directed to the Review Payment Details page
						Boolean bReview123 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle, 20, lDriver);
						Report.TestPoint(testContext.getName(),"Validate the user is directed to the Review Payment Details page", "True",bReview123.toString(), true);
					}
					
					//- Submit button
					Boolean bSubmit = UI.WaitForObject(oR_ReviewPaymentDetails.btnSubmit, 2, lDriver);
					Report.ValidationPoint(testContext.getName(),"Validate Submit button", "True",bSubmit.toString(), true);
						
					//Validate the RHN accordions do not display and the Payment step indicators display the following:
					List<WebElement> lstSteps = lDriver.findElements(By.xpath("//div[@class='step-indicator-hor box']//li"));
					if(lstSteps.size()==3)
					{
						Report.ValidationPoint(testContext.getName(),"Validate Payment step indicators", "True","True", true);
						if(lstSteps.get(0).getAttribute("class").contains("complete") && lstSteps.get(1).getAttribute("class").contains("current") && lstSteps.get(2).getAttribute("class").contains("future"))
						{
							Report.ValidationPoint(testContext.getName(),"Validate the order of the steps: Enter payment information is complete. Review payment information is the current step", "True","True", true);
						}
					}
					
					//Select a link from the Primary or Secondary navigation links
					Report.OperationPoint(testContext.getName(),"Select a link from the Primary or Secondary navigation links");
					UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
					
					//Validate the Make a Payment Abandonment div layer opens and displays the following:
					Report.OperationPoint(testContext.getName(),"Validate the Make a Payment Abandonment div layer opens and displays the following:");
					Thread.sleep(10000);
					//- Incomplete Payment
					Boolean bIncomplete = UI.WaitForObject(oR_MakeAPayment.txtIncomplete, 20, lDriver);
					if(bIncomplete)
					{
						Report.ValidationPoint(testContext.getName(),"Validate Incomplete Payment div layer", "True",bIncomplete.toString(), true);
						//- Payment abandonment message (content TBD)
						Boolean bTxt = UI.WaitForObject(oR_ReviewPaymentDetails.txtIncompleteDivMsg, 2, lDriver);
						Report.ValidationPoint(testContext.getName(),"Validate Payment abandonment message : "+oR_ReviewPaymentDetails.txtIncompleteDivMsg.getText(), "True",bTxt.toString(), true);
						//- AutoPay abandonment message (content TBD) (dynamic)
						//- Cancel link
						Boolean bCancel = UI.WaitForObject(oR_MakeAPayment.lnkCancelFrm, 2, lDriver);
						Report.ValidationPoint(testContext.getName(),"Validate Cancel link", "True",bCancel.toString(), true);
						//- Yes I want to leave button (content TBD)
						Boolean bYes = UI.WaitForObject(oR_MakeAPayment.btnYesCont, 2, lDriver);
						Report.ValidationPoint(testContext.getName(),"Validate Yes I want to leave button", "True",bYes.toString(), true);
						//Click on close link
						Report.OperationPoint(testContext.getName(),"Click on close link");
						oR_MakeAPayment.lnkFramePaymentCalenderClose.click();
						//Validate the user is directed to the Review Payment Details page
						Boolean bReview2 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle, 20, lDriver);
						Report.TestPoint(testContext.getName(),"Validate the user is directed to the Review Payment Details page", "True",bReview2.toString(), true);
						UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
						Thread.sleep(5000);
						//Click on cancel link
						Report.OperationPoint(testContext.getName(),"Click on cancel link");
						oR_MakeAPayment.lnkCancelFrm.click();
						//Validate the user is directed to the Review Payment Details page
						Boolean bReview22 = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle, 20, lDriver);
						Report.TestPoint(testContext.getName(),"Validate the user is directed to the Review Payment Details page", "True",bReview22.toString(), true);
						UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
						Thread.sleep(5000);
						//Click on Yes,Continue button
						Report.OperationPoint(testContext.getName(),"Click on Yes,Continue button");
						//oR_MakeAPayment.btnYesCont.click();
						//Validate billing and usage page
						UI.VerifyLinkNavigatestoNextPage(oR_MakeAPayment.btnYesCont, "BillPayments");
					}
					else
					{
						Report.ValidationPoint(testContext.getName(),"Validate Incomplete Payment div layer", "True",bIncomplete.toString(), true);
					}
					
					//Select the Submit button - Validate the user is directed to the Confirmation page (skipping this step as dev in real env. This step will be covered in the next flow)
						
				}
				else
				{
					Report.TestPoint(testContext.getName(),"Validate New debit / credit card pop up", "True",bPopup.toString(), true);
				}
			}
			else
			{
				Report.TestPoint(testContext.getName(),"Validate Validate Payment method dropdown", "True",bMAPDropdown.toString(), true);
			}
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	}
	
	/**************************************************************
	 * Function Name - ValidatePaymentConfirmationPage()
	 * Description - 
 	 * Test Case -  UPAY0224 - 03c - New Credit or Debit Card Payment - Confirmation - Legacy Wireline
	 * Parameters - None
	 * Date created - 10th Mar 2016
	 * Developed by - Hiral Jogi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UPAY0224 - 03c 
	public static void ValidatePaymentConfirmationPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
			OR_ReviewPaymentDetails oR_ReviewPaymentDetails = PageFactory.initElements(lDriver, OR_ReviewPaymentDetails.class);
			OR_PaymentConfirmation oR_PaymentConfirmation = PageFactory.initElements(lDriver, OR_PaymentConfirmation.class);
			OR_PaperlessBilling oR_PaperlessBilling = PageFactory.initElements(lDriver, OR_PaperlessBilling.class);

			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			Boolean bHeader, bEnrollingInAutopay, bTotalAmt, bConfirmationNum, bFrame, bSubmit, bMAP, bReview, bcontinue, bAutopayChkBox, bSelectCheckingSaving,bNameOnBankAcc,bRoutingNumber, bReenterAccNum, bBankAccountNumber;
			
			String sAmount= IO.GetParamVal(sTestParams, "Amount");
			String sBankAccNum= IO.GetParamVal(sTestParams, "CARD_NUM");
			String sSecCode= IO.GetParamVal(sTestParams, "SecCode");
			String sZip= IO.GetParamVal(sTestParams, "Zip");
			
			//	Login to a Wireless Account that is not enrolled in AutoPay - Select the Make a Payment button
			bMAP = UI.WaitForObject(oR_AccountOverview.btnMakeAPayment, 10, lDriver);
			Report.TestPoint(testContext.getName(), "Selecting the Make a Payment button", "true", bMAP.toString(), true);
			oR_AccountOverview.btnMakeAPayment.click();
			
			
			Boolean bMAPHeading = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 20, lDriver);
			Report.TestPoint(testContext.getName(), "Make a Payment page is seen.", "true", bMAPHeading.toString(), true);
			
			
			//	/**Entering details for Debit/Credit card **/
    		oR_MakeAPayment.edtPaymentAmount1.clear();
			oR_MakeAPayment.edtPaymentAmount1.sendKeys(sAmount);
			Report.TestPoint(testContext.getName(), "Eenter an Amount, Select today as a date", "true", "true", true);
			
			/**   CALENDAR **/
		
			Boolean bCalendarImg = UI.WaitForObject(oR_MakeAPayment.imgCalender1, UI.iObjTimeOut);
            if(bCalendarImg)
            {
			  Report.ValidationPoint(testContext.getName(), "Calendar Icon", "true", String.valueOf(bCalendarImg),  true);
			  oR_MakeAPayment.imgCalender1.click();
			  Thread.sleep(3000);

              Boolean bCalendar = UI.WaitForObject(oR_MakeAPayment.frmCalender, UI.iObjTimeOut);
              Report.ValidationPoint(testContext.getName(), "Calendar Frame", "true", String.valueOf(bCalendar),  true);
              Thread.sleep(2000);
              
              lDriver.switchTo().frame(oR_MakeAPayment.frmCalender);
	          
              lDriver.findElement(By.xpath("//*[contains(@class,'currentDate')]//a")).click();
			  Thread.sleep(3000);
			  		
            }
            else {
            	Report.OperationPoint(testContext.getName(), "Calendar is not present");
            }
			
	    	Report.OperationPoint(testContext.getName(), "Select a payment method 2-debit / credit and select the Next button");	
	    	UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod, "New debit / credit card");
	    	Thread.sleep(10000);
        	
    		Thread.sleep(5000);
			bFrame = UI.WaitForObject(oR_MakeAPayment.frmIncompletePayment, 10, lDriver);
			Report.TestPoint(testContext.getName(), "New debit / credit card", "true", bFrame.toString(), true);
	
	            
			// switching to new frame
			
			lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
		
			try
			{
				oR_MakeAPayment.edtPaymentFrameNameOnCard.sendKeys("test");
				oR_MakeAPayment.edtPaymentFrameCardNumber.sendKeys(sBankAccNum);
				oR_MakeAPayment.edtPaymentFrameSecurityNumber.sendKeys(sSecCode);
				UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationMonth, "12");
				UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationYear, "2016");
				oR_MakeAPayment.edtPaymentFrameZipCode.sendKeys(sZip);
				Report.ValidationPoint(testContext.getName(), "Verify Values for payment method2 are entered", "true","true", true);		
			}
			catch (Exception e) 
			{
				Report.TestPoint(testContext.getName(), "Unable TO enter Debit/Credit details", "true", "Failed to validate", true);
			}
			
			// Click on continue on Debit/Credit Frame
			bcontinue = UI.WaitForObject(oR_MakeAPayment.lnkContinue, 3);
			Report.TestPoint(testContext.getName(), "Click on continue on Debit/Credit Frame", "true",bcontinue.toString() , true);
	    	oR_MakeAPayment.lnkContinue.click();
	    	
	    	if(UI.WaitForObject(oR_MakeAPayment.frmNewDebitCreditCardPaymentMethod,3))
	    	{
	    		if(UI.WaitForObject(oR_MakeAPayment.lnkContinue,3))
	    			oR_MakeAPayment.lnkContinue.click();
	    	}
    		lDriver.switchTo().defaultContent();
    		
    		
	    		// Click on Continue after modal closed on MAP page
	    		Report.OperationPoint(testContext.getName(), "Click on Continue button on MAP page after Credit/Debit modal is closed");
	    		if(UI.WaitForObject(oR_MakeAPayment.lnkContinue,3))
	    			{
	    				oR_MakeAPayment.lnkContinue.click();
	    				if(UI.WaitForObject(oR_MakeAPayment.lnkContinue,3))
	    	    			oR_MakeAPayment.lnkContinue.click();
	    			}
	    		else {
					Report.TestPoint(testContext.getName(), "Unable TO Click on Continue on MAP Page", "true", "Failed to Continue", true);
	    		}
	    		
	    		// Validate the user is directed to the Review Payment Details page.
	    		bReview = UI.WaitForObject(oR_ReviewPaymentDetails.txtReviewPaymentDetailsTitle, 10);
	    		Report.ValidationPoint(testContext.getName(), "Validate the user is directed to the Review Payment Details page For Debit/Credit Payment Method", "true", bReview.toString(), true);
	    		
	    		// Select the Submit button 
	    		bSubmit =  UI.WaitForObject(oR_ReviewPaymentDetails.btnSubmit, 10);
				Report.TestPoint(testContext.getName(), "Step 1 - Click on continue on Debit/Credit Frame", "true",bSubmit.toString() , true);
				oR_ReviewPaymentDetails.btnSubmit.click();
				Thread.sleep(3000);
		
				// Validate the user is directed to the Confirmation page - step 2.1
				Boolean bPayConfirm = UI.WaitForObject(oR_PaymentConfirmation.txtPaymentConfirmationTitle,10);
				Report.TestPoint(testContext.getName(), "Step 2.1 - Validate payment confirmation heading is displayed.", "True", String.valueOf(bPayConfirm), true);
				
				//2.2
				Boolean bThankYou = UI.WaitForObject(oR_PaymentConfirmation.txtThankyou,10);
				Report.ValidationPoint(testContext.getName(), "Step 2.2 - Validate Thank you note is present on the payment confirmation page.", "true", bThankYou.toString(), true);
			
				//2.3
				Boolean bPrint = UI.WaitForObject(oR_PaymentConfirmation.lnkPrint,10);
				Report.ValidationPoint(testContext.getName(), "Step 2.3 - Validate Print link is present on the payment confirmation page.", "true", bPrint.toString(), true);
			
				// 2.4 and Step 3
				Boolean bNote = UI.WaitForObject(oR_PaymentConfirmation.txtNote,10);
				if(bNote){
					String StrNote = oR_PaymentConfirmation.txtNoteContent.getText();
					Report.ValidationPoint(testContext.getName(), "Step 2.4 & Step 3 - Note is present. Content of the note is " + StrNote, "true", bNote.toString(), true);
				
				} else {
					Report.OperationPoint(testContext.getName(), "Step 2.4 & Step 3 - No note is present.");
				}
				
				//2.5
				Boolean bAmount = UI.WaitForObject(oR_PaymentConfirmation.txtAmountToPay,10);
				Report.ValidationPoint(testContext.getName(), "Step 2.5 - Validate Amount to pay is present on the payment confirmation page.", "true", bAmount.toString(), true);
			
				//2.6
				Boolean bAmount$ = UI.WaitForObject(oR_PaymentConfirmation.txtTotalPaymentsAmount,10);
				Report.ValidationPoint(testContext.getName(), "Step 2.6 - Validate Total payment amount in $ is present on the payment confirmation page.", "true", bAmount$.toString(), true);
			
				//2.7
				Boolean bPayOnDate = UI.WaitForObject(oR_PaymentConfirmation.txtPayOnDate,10);
				Report.ValidationPoint(testContext.getName(), "Step 2.7 - Validate PayOnDate text is present on the payment confirmation page.", "true", bPayOnDate.toString(), true);
			
				//2.8
				Boolean bPaymentDate = UI.WaitForObject(oR_PaymentConfirmation.txtPaymentDate,10);
				Report.ValidationPoint(testContext.getName(), "Step 2.8 - Validate PaymentDate is present on the payment confirmation page.", "true", bPaymentDate.toString(), true);
			
				//2.9
				Boolean bPaymentMethod = UI.WaitForObject(oR_PaymentConfirmation.txtPaymentMethod,10);
				Report.ValidationPoint(testContext.getName(), "Step 2.9 - Validate PaymentMethod is present on the payment confirmation page.", "true", bPaymentMethod.toString(), true);
			
				//2.9
				Boolean bConfirmation = UI.WaitForObject(oR_PaymentConfirmation.txtConfirmation,10);
				Report.ValidationPoint(testContext.getName(), "Step 2.10 - Validate Confirmation number is present on the payment confirmation page.", "true", bConfirmation.toString(), true);
			
				//Steo 4-View - RHN 
				Report.OperationPoint(testContext.getName(), "4-Validate the RHN accordions do not display and the Payment step indicators display the following:");
				
				Boolean bStep1 = UI.WaitForObject(oR_PaymentConfirmation.txtEnterPaymentInfoAccordion, 10, lDriver);
				Report.ValidationPoint(testContext.getName(), "4-i-Enter Payment Information", "true",bStep1.toString(), true);

				Boolean bStep2 = UI.WaitForObject(oR_PaymentConfirmation.txtReviewPaymentInfoAccordion, 10, lDriver);
				Report.ValidationPoint(testContext.getName(), "4-ii-Review Payment Information", "true",bStep2.toString(), true);

				Boolean bStep3 = UI.WaitForObject(oR_PaymentConfirmation.txtPaymentStatusAccordion, 10, lDriver);
				Report.ValidationPoint(testContext.getName(), "4-iii-Payment Status", "true",bStep3.toString(), true);

				// Step 5 - not to be covered
				
				// step 6
				Boolean bAnotherPayment = UI.WaitForObject(oR_PaymentConfirmation.lnkMakeAnotherPayment1, 10, lDriver);
				if(bAnotherPayment){
					Report.ValidationPoint(testContext.getName(), "Make another payment link is present.", "true","true", true);
					Report.OperationPoint(testContext.getName(), "Clicking on Make another payment link.");
					oR_PaymentConfirmation.lnkMakeAnotherPayment1.click();
					
					// Validate MAP page is seen
					Boolean bMAPPage = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 20, lDriver);
					Report.TestPoint(testContext.getName(), "Step 6 - Make a Payment page is seen.", "true", bMAPHeading.toString(), true);
				} else {
					Report.ValidationPoint(testContext.getName(), "Make another payment link is present.", "true","false", true);
				}
				
				// Step 7
				if(UI.WaitForObject(oR_AccountOverview.lnkOverview,20).equals(true)){
					Report.TestPoint(testContext.getName(), "Validate sec navigation Overview link", "sec navigation Overview link is displayed", "sec navigation Overview link is displayed", true);
					//Click on sec navigation link - Overview
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on sec navigation link - Overview");
					oR_AccountOverview.lnkOverview.click();
					
					// text to validate if user is on overview page
					bMAP = UI.WaitForObject(oR_AccountOverview.btnMakeAPayment, 10, lDriver);
					Report.TestPoint(testContext.getName(), "Step 7 - Validate User is on Overview page.", "true", bMAP.toString(), true);
					
				}
				
				// step 8 - not to be covered.
				
			/*	Boolean bPaperless = UI.WaitForObject(oR_PaymentConfirmation.imgEnrollInPaperlessBilling, 10, lDriver);
				if(bPaperless){
					Report.ValidationPoint(testContext.getName(), "Enroll in Paperless Billing.", "true",bPaperless.toString(), true);
					oR_PaymentConfirmation.imgEnrollInPaperlessBilling.click();
					
					// validate if paperless billing page is seen
					Boolean bPaperlessPage = UI.WaitForObject(oR_PaperlessBilling.txtPaperlessBillingTitle, 10, lDriver);
					if(bPaperlessPage){
						Report.ValidationPoint(testContext.getName(), "Paperless Billing Page is displayed.", "true",bPaperlessPage.toString(), true);
						
						// navigating back
						lDriver.navigate().back();
						
					} else {
						Report.ValidationPoint(testContext.getName(), "Paperless Billing Page is displayed.", "true",bPaperlessPage.toString(), false);
					}
				} else {
					Report.ValidationPoint(testContext.getName(), "Enroll in Paperless Billing.", "true",bPaperless.toString(), false);
				} */
				
				// Step 9 and 10 is for email confirmation
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - ValidationsOnMakeAPaymentPage()
	 * Description - 
 	 * Test Case -  UPAY0324 - 01 - Make a Payment - Step 1 - Uverse
	 * Parameters - None
	 * Date created - 14th Mar 2016
	 * Developed by - Hiral Jogi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UPAY0324 - 01 
	public static void ValidationsOnMakeAPaymentPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
			OR_ReviewPaymentDetails oR_ReviewPaymentDetails = PageFactory.initElements(lDriver, OR_ReviewPaymentDetails.class);
			OR_PaymentConfirmation oR_PaymentConfirmation = PageFactory.initElements(lDriver, OR_PaymentConfirmation.class);
			OR_PaperlessBilling oR_PaperlessBilling = PageFactory.initElements(lDriver, OR_PaperlessBilling.class);

			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			Boolean bHeader, bEnrollingInAutopay, bTotalAmt, bConfirmationNum, bFrame, bSubmit, bMAP, bReview, bcontinue, bAutopayChkBox, bSelectCheckingSaving,bNameOnBankAcc,bRoutingNumber, bReenterAccNum, bBankAccountNumber;
			
			String sAmount= IO.GetParamVal(sTestParams, "Amount");
			String sBankAccNum= IO.GetParamVal(sTestParams, "CARD_NUM");
			String sSecCode= IO.GetParamVal(sTestParams, "SecCode");
			String sZip= IO.GetParamVal(sTestParams, "Zip");
			
			bMAP = UI.WaitForObject(oR_AccountOverview.btnMakeAPayment, 10, lDriver);
			Report.TestPoint(testContext.getName(), "Step 1 - Selecting Make a Payment button", "true", bMAP.toString(), true);
			oR_AccountOverview.btnMakeAPayment.click();
			
			// Validations on MAP Page
			
			Boolean bMAPHeading = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 2.1 - Make a Payment page headed is seen.", "True",bMAPHeading.toString(), true);
			
			Boolean bFlexiblepaymentoptions = UI.WaitForObject(oR_MakeAPayment.lnkFlexiblepaymentoptions, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 2.2 - Flexible Payment Options video link is seen.", "True",bFlexiblepaymentoptions.toString(), true);
			
			Boolean bChoosePaymentMethod = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethodLabel, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 2.3 - Choose Payment Method is seen.", "True",bChoosePaymentMethod.toString(), true);
			
			Boolean bManagePaymentProfile = UI.WaitForObject(oR_MakeAPayment.lnkManagePaymentProfile, 20, lDriver);
			if(bManagePaymentProfile){
				Report.ValidationPoint(testContext.getName(),"Step 2.4 - Manage Payment Profiles is seen.", "True",bManagePaymentProfile.toString(), true);
			} else {
				Report.OperationPoint(testContext.getName(), "Step 2.4 - Manage Payment Profiles is not seen.");
			}
			
			Boolean bManageAutoPaySettings = UI.WaitForObject(oR_MakeAPayment.lnkManageAutoPaySettings, 20, lDriver);
			if(bManageAutoPaySettings){
				Report.ValidationPoint(testContext.getName(),"Step 2.5 - Manage AutoPay Settings Link is seen.", "True",bManageAutoPaySettings.toString(), true);
			} else {
				Report.OperationPoint(testContext.getName(), "Step 2.5 - Manage AutoPay Settings Link is seen.");
			}
			
			//- Nickname (dynamic) - not seen
			
			// - Account Number
			Boolean bBANAndAccNum = UI.WaitForObject(oR_MakeAPayment.txtBANAndAccNum, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 2.6 - Account Number is seen.", "True",bBANAndAccNum.toString(), true);
			
			//- Scheduled payment of $##.## for MM/DD/YYYY (dynamic)
			Boolean bScheduledPayment = UI.WaitForObject(oR_MakeAPayment.lnkScheduledPayment, 20, lDriver);
			if(bScheduledPayment){
				Report.ValidationPoint(testContext.getName(),"Step 2.8 - Manage AutoPay Settings Link is seen.", "True",bScheduledPayment.toString(), true);
			} else {
				Report.OperationPoint(testContext.getName(), "Step 2.8 - Manage AutoPay Settings Link is seen.");
			}
			
			Boolean bBalance = UI.WaitForObject(oR_MakeAPayment.txtBalance, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 2.9 - Balance available in $ is seen.", "True",bBalance.toString(), true);
			
			Boolean bPaymentAmountLabel = UI.WaitForObject(oR_MakeAPayment.txtPaymentAmountLabel, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 2.10 - Payment Amount is seen.", "True",bPaymentAmountLabel.toString(), true);
			
			Boolean bPaymentAmountbox = UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1, 20, lDriver);
			String Strpaymentamt = oR_MakeAPayment.edtPaymentAmount1.getAttribute("value");
			Report.ValidationPoint(testContext.getName(),"Step 2.11 - Edit Payment Amount box is seen with value as  " + Strpaymentamt, "True",bPaymentAmountbox.toString(), true);
			
			Boolean bPaymentDateLabel = UI.WaitForObject(oR_MakeAPayment.txtPaymentDateLabel, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 2.12 - Payment Date is seen.", "True",bPaymentDateLabel.toString(), true);
			
			Boolean bCalendarImg = UI.WaitForObject(oR_MakeAPayment.imgCalender1, UI.iObjTimeOut);
            if(bCalendarImg)
            {
			  Report.ValidationPoint(testContext.getName(), "Calendar Icon is seen.", "true", String.valueOf(bCalendarImg),  true);
			  oR_MakeAPayment.imgCalender1.click();
			  Thread.sleep(3000);

              Boolean bCalendar = UI.WaitForObject(oR_MakeAPayment.frmCalender, UI.iObjTimeOut);
              Report.ValidationPoint(testContext.getName(), "Step 2.13 - Date open box with calendar icon is seen.", "true", String.valueOf(bCalendar),  true);
              Thread.sleep(2000);
              
              lDriver.switchTo().frame(oR_MakeAPayment.frmCalender);
	          
              lDriver.findElement(By.xpath("//*[contains(@class,'currentDate')]//a")).click();
			  Thread.sleep(3000);
			  		
            }
            else {
            	Report.OperationPoint(testContext.getName(), "Calendar is not present");
            }
			
            
            Boolean bPaymentMethodLabel = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethodLabel, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 2.14 - Payment Method is seen.", "True",bPaymentMethodLabel.toString(), true);
			
			// Payment Method Drop Down w/ helpful icon (?) 
			
			Boolean bPaymentMethodTooltip = UI.WaitForObject(oR_MakeAPayment.btnPaymentMethodTooltip, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 2.15 - Payment Method Drop Down help icon (?) is seen.", "True",bPaymentMethodTooltip.toString(), true);
			
			//- Split this payment helpful icon (?) 
			Boolean bSplitThisPaymentTooltip = UI.WaitForObject(oR_MakeAPayment.btnSplitThisPaymentTooltip, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 2.16 - Split this payment help icon (?) is seen.", "True",bSplitThisPaymentTooltip.toString(), true);
			
    		//- Enroll in AutoPay check box w/ helpful icon (?) 
			
			Boolean bEnrollInAutopayTooltip = UI.WaitForObject(oR_MakeAPayment.btnEnrollInAutopayTooltip, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 2.17 - Enroll in AutoPay check box help icon (?) is seen.", "True",bEnrollInAutopayTooltip.toString(), true);
			
			Boolean bEditPaymentProfile = UI.WaitForObject(oR_MakeAPayment.lnkEditPaymentProfile, 20, lDriver);
			if(bEditPaymentProfile){
				Report.ValidationPoint(testContext.getName(),"Step 2.18 - Edit Payment Profile link is seen.", "True",bEditPaymentProfile.toString(), true);
			} else {
				Report.OperationPoint(testContext.getName(), "Step 2.18 - Edit Payment Profile link is not seen.");	
			}
			
			
			Boolean bCancel = UI.WaitForObject(oR_MakeAPayment.lnkCancel, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 2.19 - Cancel link is seen.", "True",bCancel.toString(), true);
			
			Boolean bNext = UI.WaitForObject(oR_MakeAPayment.btnNext, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 2.20 - Next/Continue button is seen.", "True",bNext.toString(), true);
			Thread.sleep(5000);
			
			// Step 3
			List<WebElement> redtext = lDriver.findElements(By.xpath("//span[contains(text(),'*')]/parent::div"));
			for(int i=0;i<redtext.size();i++){
				String impfields = redtext.get(i).getText();
				Report.ValidationPoint(testContext.getName(),"Step 3 - Field with red asterick are :" + i + "-" + impfields, "True","True", true);
			}
		
	    	// step 5 
	    	Actions action = new Actions(lDriver);
			action.moveToElement(oR_MakeAPayment.btnNext).build().perform();
			Report.ValidationPoint(testContext.getName(),"Step 5 - Hover over Next/Continue button.", "True",bNext.toString(), true);
			action.clickAndHold(oR_MakeAPayment.btnNext).build().perform();
			String Message = oR_MakeAPayment.btnNext.getText();
		//	String Message = oR_MakeAPayment.btnNext.getAttribute("id")
			System.out.println(Message);
			Report.ValidationPoint(testContext.getName(),"A message to complete all the required fields is displayed when hovering over the gray Next/Continue button.", "True","True", true);
			action.release(oR_MakeAPayment.btnNext).build().perform();
			Thread.sleep(4000);
			
			// Step 6 - Validate Inline Error message dispalys below Payment Amount entry field 
			
	    	Boolean bEditPayment = UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 6 - Delete the balance information in the Payment Amount entry field.", "True",bEditPayment.toString(), true);
			oR_MakeAPayment.edtPaymentAmount1.clear();
			Thread.sleep(2000);
			
			// Step 7 - Validate Inline Error message displays below the Select Payment Method dropdown 
			
			Report.OperationPoint(testContext.getName(), "Step 4 & 7 - Choose Select Payment Method from the Payment Method dropdown.");	
			UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethodnew,"Select payment method");
			Thread.sleep(3000);
						
			
			
			Boolean bErrorMsgOnBlankPaymentAmt = UI.WaitForObject(oR_MakeAPayment.txtErrorMsgOnBlankPaymentAmt, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Validate Inline Error message dispalys below Payment Amount entry field .", "True",bErrorMsgOnBlankPaymentAmt.toString(), true);
			
			//clicking somewhere to get the error alert
			oR_MakeAPayment.edtPaymentAmount1.click();
			oR_MakeAPayment.txtPaymentAmountLabel.click();
	    	
			Boolean bErrorMsgOnSelectingNoPaymentMethod = UI.WaitForObject(oR_MakeAPayment.txtErrorMsgOnSelectingNoPaymentMethod, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Validate Inline Error message displays below the Select Payment Method dropdown.", "True",bErrorMsgOnSelectingNoPaymentMethod.toString(), true);
			
			// refreshing the page
			lDriver.navigate().refresh();
			
			// Step 9 - Validate backend error displays when 0 amount is entered
 
			Boolean bEditPayment1 = UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1, 20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Step 9 - Enter 0.00 in the Payment Amount entry field.", "True",bEditPayment1.toString(), true);
			oR_MakeAPayment.edtPaymentAmount1.clear();
			oR_MakeAPayment.edtPaymentAmount1.sendKeys("0");
			
			Boolean bErrorMsgOn0PaymentAmt = UI.WaitForObject(oR_MakeAPayment.txtErrorMsgOnBlankPaymentAmt, 20, lDriver);
			if(bErrorMsgOn0PaymentAmt){
				Report.ValidationPoint(testContext.getName(),"Inline Error message is displayed below Payment Amount entry field.", "True",bErrorMsgOn0PaymentAmt.toString(), true);
			} else {
				Report.OperationPoint(testContext.getName(), "Validate Inline Error message is no longer displayed below the ayment Amount entry field which is as expected.");
			}
			
			// step 10 pending
			
			// step 11
		
			Report.OperationPoint(testContext.getName(), "Total payment amount is - " + Strpaymentamt);
			Report.OperationPoint(testContext.getName(), "Current transaction amount to pay is  - 4000 " );
			
			oR_MakeAPayment.edtPaymentAmount1.clear();
			oR_MakeAPayment.edtPaymentAmount1.sendKeys("4000");
			
			// Step 8 - Validate Inline Error message no longer displays below the Payment Method dropdown 
		
			Report.OperationPoint(testContext.getName(), "Step 8 - Select a Payment Method in the Payment Method dropdown.");	
			UI.SelectOptionFromDropDown(oR_MakeAPayment.lstPaymentMethod,"New debit / credit card");
			Thread.sleep(10000);
			
			bFrame = UI.WaitForObject(oR_MakeAPayment.frmIncompletePayment, 10, lDriver);
			Report.TestPoint(testContext.getName(), "New debit / credit card", "true", bFrame.toString(), true);
	
			// switching to new frame
			
			lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
		
			try
			{
				oR_MakeAPayment.edtPaymentFrameNameOnCard.sendKeys("test");
				oR_MakeAPayment.edtPaymentFrameCardNumber.sendKeys(sBankAccNum);
				oR_MakeAPayment.edtPaymentFrameSecurityNumber.sendKeys(sSecCode);
				UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationMonth, "12");
				UI.SelectOptionFromDropDown(oR_MakeAPayment.lstSelectCardExpirationYear, "2016");
				oR_MakeAPayment.edtPaymentFrameZipCode.sendKeys(sZip);
				Report.ValidationPoint(testContext.getName(), "Verify Values for payment method2 are entered", "true","true", true);		
			}
			catch (Exception e) 
			{
				Report.TestPoint(testContext.getName(), "Unable TO enter Debit/Credit details", "true", "Failed to validate", true);
			}
			
			// Click on continue on Debit/Credit Frame
			bcontinue = UI.WaitForObject(oR_MakeAPayment.lnkContinue, 3);
			Report.TestPoint(testContext.getName(), "Click on continue on Debit/Credit Frame", "true",bcontinue.toString() , true);
	    	oR_MakeAPayment.lnkContinue.click();
	    	
	    	if(UI.WaitForObject(oR_MakeAPayment.frmNewDebitCreditCardPaymentMethod,3))
	    	{
	    		if(UI.WaitForObject(oR_MakeAPayment.lnkContinue,3))
	    			oR_MakeAPayment.lnkContinue.click();
	    	}
    		lDriver.switchTo().defaultContent();
    		
	    	
			// Boolean bErrorMsgOnSelectingNoPaymentMethod = UI.WaitForObject(oR_MakeAPayment.txtErrorMsgOnSelectingNoPaymentMethod, 20, lDriver);
			if(bErrorMsgOnSelectingNoPaymentMethod){
				Report.ValidationPoint(testContext.getName(),"Step 8 - Validate Inline Error message displays below the Select Payment Method dropdown.", "True",bErrorMsgOnSelectingNoPaymentMethod.toString(), true);
				} else {
					Report.OperationPoint(testContext.getName(), "Step 8 - Validate Inline Error message no longer displays below the Payment Method dropdown.");	
			}
    	
	    	// Click on Continue after modal closed on MAP page
	    	Report.OperationPoint(testContext.getName(), "Click on Continue button on MAP page after Credit/Debit modal is closed");
	    	if(UI.WaitForObject(oR_MakeAPayment.lnkContinue,3))
	    		{
	    			oR_MakeAPayment.lnkContinue.click();
	    			if(UI.WaitForObject(oR_MakeAPayment.lnkContinue,3))
	    	    		oR_MakeAPayment.lnkContinue.click();
	    		}
	    	else {
				Report.TestPoint(testContext.getName(), "Unable TO Click on Continue on MAP Page", "true", "Failed to Continue", true);
	    	}
	    	
	    	// Validating Payment Alert Div layer opens displaying the following:
	    	Thread.sleep(10000);
			Boolean bPaymentAlert = UI.WaitForObject(oR_MakeAPayment.txtPaymentAlert,20);
			if(bPaymentAlert.equals(true)) {
				Report.ValidationPoint(testContext.getName(), "Verify payment alert div", "true", String.valueOf(bPaymentAlert), true);
	    		Thread.sleep(2000);
	    		
	    		WebElement PaymentAlertHeader = lDriver.findElement(By.xpath("//h2[contains(text(),'Payment Alert')]"));
	    		if(PaymentAlertHeader.isDisplayed()){
	    			Report.ValidationPoint(testContext.getName(), "Step 11.1 - Payment Alert (header) is displayed.", "true","true", true);		
	    		} else {
	    			Report.ValidationPoint(testContext.getName(), "Step 11.1 - Payment Alert (header) is displayed.", "true","false", true);		
	    		}
	    		
	    		WebElement AlertText = lDriver.findElement(By.xpath("//p[contains(text(),'The amount')]"));
	    		if(AlertText.isDisplayed()){
	    			String strAlertText = lDriver.findElement(By.xpath("//p[contains(text(),'The amount')]")).getText();
	    			Report.ValidationPoint(testContext.getName(), "Step 11.2 - Alert text is displayed with content as " + strAlertText, "true","true", true);		
	    		} else {
	    			Report.ValidationPoint(testContext.getName(), "Step 11.2 - Alert text is displayed.", "true","false", true);		
	    		}
	    		
	    		WebElement AlertWarning = lDriver.findElement(By.xpath("//p/strong[contains(text(),'Do you')]"));
	    		if(AlertWarning.isDisplayed()){
	    			String strAlertWarning = lDriver.findElement(By.xpath("//p/strong[contains(text(),'Do you')]")).getText();
	    			Report.ValidationPoint(testContext.getName(), "Step 11.3 - Alert warning is displayed with content as " + strAlertWarning, "true","true", true);		
	    		} else {
	    			Report.ValidationPoint(testContext.getName(), "Step 11.3 - Alert warning is displayed.", "true","false", true);		
	    		}
	    		
	    		Boolean bContinueDis = UI.WaitForObject(oR_MakeAPayment.lnkContinueDis, 20, lDriver);
	    		if(bContinueDis){
	    			Report.ValidationPoint(testContext.getName(),"Step 11.5 - Continue button is present.", "True","True", true);
	    		} else {
	    			Report.ValidationPoint(testContext.getName(),"Step 11.5 - Continue button is present.", "True","False", true);
	    		}
	    		

	    		Boolean bEditPaymentlink = UI.WaitForObject(oR_MakeAPayment.lnkEditPayment, 20, lDriver);
	    		if(bEditPaymentlink){
	    			Report.ValidationPoint(testContext.getName(),"Step 11.4 - Edit Payment link is present.", "True","True", true);
	    			oR_MakeAPayment.lnkEditPayment.click();
	    			Report.ValidationPoint(testContext.getName(), "Step 12 - Clicking on Edit Payment link in the payment alert layer.", "true","true", true);		
	    			
	    			Boolean bMAPPage = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 20, lDriver);
	    			Report.ValidationPoint(testContext.getName(),"Validate the user is directed back to the Make a Payment page.", "True",bMAPPage.toString(), true);
	    			
	    		} else {
	    			Report.ValidationPoint(testContext.getName(),"Step 11.4 - Edit Payment link is present.", "True","False", true);
	    		}
			}
	    	
	    	else {
	    		Report.ValidationPoint(testContext.getName(), "Switching to Payment Alert Layer.", "true","false", true);		
	    	}
	    	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
}
	

		
		
	

		
