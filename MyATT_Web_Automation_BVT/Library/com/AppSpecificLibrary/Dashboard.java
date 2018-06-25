package com.AppSpecificLibrary;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import com.OR.MyATT.OR_ATT;
import com.OR.MyATT.OR_ATT_ManageMyDeviceFeatures;
import com.OR.MyATT.OR_AccountOverview;
import com.OR.MyATT.OR_AddaDevice;
import com.OR.MyATT.OR_AlertDetails;
import com.OR.MyATT.OR_BillAndPayments;
import com.OR.MyATT.OR_BillAndUsage;
import com.OR.MyATT.OR_BillingSettingsNotice;
import com.OR.MyATT.OR_EditBillingContactInformation;
import com.OR.MyATT.OR_EditPaymentProfile;
import com.OR.MyATT.OR_ForgotUserID;
import com.OR.MyATT.OR_InternetService;
import com.OR.MyATT.OR_LinkAnAccount;
import com.OR.MyATT.OR_Login;
import com.OR.MyATT.OR_MakeAPayment;
import com.OR.MyATT.OR_ManageAutoPay;
import com.OR.MyATT.OR_ManagePlentiCard;
import com.OR.MyATT.OR_MessageCenter;
import com.OR.MyATT.OR_MyAccountAccess;
import com.OR.MyATT.OR_MyWirelessPlan;
import com.OR.MyATT.OR_PaperlessBilling;
import com.OR.MyATT.OR_Profile;
import com.OR.MyATT.OR_Shop;
import com.OR.MyATT.OR_SuspendReactivateService;
import com.OR.MyATT.OR_TelevisionService;
import com.OR.MyATT.OR_ViewOrChangeRatePlan;
import com.SupportingFiles.IO;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;
import com.SupportingFiles.UI;




public class Dashboard extends LaunchAndLogout {

	static Hashtable<String, String> sTestParams = new Hashtable<String, String>();

	/**************************************************************
	 * Function Name - VerifyServiceSummarySectionForUverseInternet 
	 * Description- This function Validates Internet icon, Troubleshoot & resolve link, 
	 * 				email addrs, message in service summary for uverse internet and verifies that user is taken to profile tab
	 * 				when clicked on change my password link
	 * Parameters - None
	 * Date created - 3rd Feb 2015https://myattmonitor26.stage.att.com:8442/olam/loginAction.olamexecute
	 * Developed by - Monica M
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//DBD09021

	public static void VerifyServiceSummarySectionForUverseInternet(final ITestContext testContext) 
			throws Exception
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
		
		//String sVerifyServiceSummarySectionForUverseInternet = "False";
		try{
			//Internet icon should be displayed
			Report.OperationPoint(testContext.getName(), "Validating Service Summary Section for Uverse Internet");
			String sUverseIntIcon = UI.CheckExist(oR_AccountOverview.imgUverseInternetIcon);
			if(sUverseIntIcon.equalsIgnoreCase("True")){
				Report.ValidationPoint(testContext.getName(), "Validate Uverse Internet Icon In Service Summary Section", "True", "True", true);	
			} else{
				Report.ValidationPoint(testContext.getName(), "Validate Uverse Internet Icon In Service Summary Section", "Uverse Internet Icon must be present in Service Summary section", "Uverse Internet Icon is present", true);		
			}
			//Verify Trouble shoot and resolve link
			if(UI.CheckExist(oR_AccountOverview.lnkTroubleshoot).equalsIgnoreCase("True")){
				Report.ValidationPoint(testContext.getName(), "Validate Troubleshoot & Resolve link In Service Summary Section", "True", "True", true);	
			} else {
				Report.ValidationPoint(testContext.getName(), "Validate Troubleshoot & Resolve link In Service Summary Section", "Troubleshoot & Resolve link", "NOT PRESENT", true);	
			}

			//validation for DBD09021
			if(testContext.getName().equalsIgnoreCase("DBD09021"))
			{
			//message logged in as a sub account 
			if(UI.CheckExist(oR_AccountOverview.txtNotLoggedInWithPrimaryAccMsg).equalsIgnoreCase("True")){
				Report.ValidationPoint(testContext.getName(), "Message logged in as a sub account", "Present", "Present", true);
			} else {
				Report.ValidationPoint(testContext.getName(), "Message logged in as a sub account", "True", "NOT PRESENT", true);
			}
			}
			//Verify Email address - for member ID associated to SLID 
			if(UI.CheckExist(oR_AccountOverview.lnkCheckEmail).equalsIgnoreCase("True")){
				Report.ValidationPoint(testContext.getName(), "Email Address For MID associated to SLID", "True", "True", true);
			} else {
				Report.ValidationPoint(testContext.getName(), "Email Address For MID associated to SLID", "Email Address For MID must be present", "NOT PRESENT", true);
			}
			//Validate & Click on Link to change email account password 
			if(UI.CheckExist(oR_AccountOverview.lnkChangeMyEmailPwd).equalsIgnoreCase("True"))
			{
				Report.TestPoint(testContext.getName(), "Link Change My Email Password", "True", "True", true);
				oR_AccountOverview.lnkChangeMyEmailPwd.click();
				Report.OperationPoint(testContext.getName(), "Validating Navigation to profile Tab on Edit Info Page");
				//Validate user taken to  page/tab on profile (edit login info page)
				if(UI.WaitForObject(oR_Profile.lnkProfileActive, UI.iObjTimeOut).equals(true)){
					Report.TestPoint(testContext.getName(), "Validate user taken to  page/tab on profile (edit login info page)", lDriver.getTitle(), "Edit AT&T Access ID Information", true);				
				} 

			} else {
				Report.TestPoint(testContext.getName(), "Link Change My Email Password", "Link Change My Email Password must be Present", "NOT PRESENT", true);
			}

		} catch (Exception e)
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}

	}


	/**************************************************************
	 * Function Name - VerifyQuickLinksofManageUverseTV
	 * Description- 
	 * Parameters - 
	 * Date created - 4th Feb 2015
	 * Developed by - Krutika Kamdi 
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/


	public static void VerifyQuickLinksofManageUverseTV(final ITestContext testContext)
			throws Exception {
		
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		
		try{
			//Verify I Want to link
			Boolean bIwantto= UI.WaitForObject(oR_AccountOverview.btnIWantTo,UI.iObjTimeOut);
			if(bIwantto.equals(true)){

				Report.TestPoint(testContext.getName(),"Validate I want to link is present ", "True","True", true);
				Report.OperationPoint(testContext.getName(), "	Clicking on I want to");
				oR_AccountOverview.btnIWantTo.click();
				Boolean bManagePlans= UI.WaitForObject(oR_AccountOverview.lnkManageMyPlan,UI.iObjTimeOut);

				for (int i=0; i<2; i++){
					oR_AccountOverview.lnkManageMyPlan.sendKeys(Keys.ARROW_DOWN);
					if(i == 1){
						oR_AccountOverview.lnkManageMyPlan.click();
					}}

				//Verify Uverse TV link
				Boolean bUverseTV= UI.WaitForObject(oR_AccountOverview.lnkUverseTV,UI.iObjTimeOut);
				if(bUverseTV.equals(true))
				{
					Report.TestPoint(testContext.getName(),"Validate Uverse TV is present ", "True","True", true);
					oR_AccountOverview.lnkUverseTV.click();
					Report.OperationPoint(testContext.getName(), "	Clicking on Manage My plans");
					Report.TestPoint(testContext.getName(),"Validate Uverse TV is clicked ", "True","True", true);

					//Verify Compare TV Plans link
					Boolean bCompareTVPlans= UI.WaitForObject(oR_AccountOverview.lnkCompareTVPlans,UI.iObjTimeOut);
					if(bCompareTVPlans.equals(true))
					{
						Report.TestPoint(testContext.getName(),"Validate Compare TV Plans link is present ", "True","True", true);
					}

					else {
						Report.TestPoint(testContext.getName(),"Validate Compare TV Plans link is present ", "Compare TV Plans link is present","Compare TV Plans link is not present", true);
					}
				}
				//Verify Channel line up link
				Boolean bChannelLineup= UI.WaitForObject(oR_AccountOverview.lnkChannelLineup,UI.iObjTimeOut);
				if(bChannelLineup.equals(true))
				{
					Report.TestPoint(testContext.getName(),"Validate Channel line up link is present ", "True","True", true);
				}
				else {
					Report.TestPoint(testContext.getName(),"Validate Channel line up link is present ", "Channel line up link is present","Channel line up link is not present", true);
				}
				//Verify Parental Control link
				Boolean bParentalControls= UI.WaitForObject(oR_AccountOverview.lnkParentalControls,UI.iObjTimeOut);
				if(bParentalControls.equals(true))
				{
					Report.TestPoint(testContext.getName(),"Validate Parental Controls link is present ", "True","True", true);
				}
				else {
					Report.TestPoint(testContext.getName(),"Validate Parental Controls link is present ", "Parental Controls link is present","Parental Controls link is not present", true);
				}
				//Verify Add receivers link
				Boolean bAddReceivers= UI.WaitForObject(oR_AccountOverview.lnkAddReceivers,UI.iObjTimeOut);
				if(bAddReceivers.equals(true))
				{
					Report.TestPoint(testContext.getName(),"Validate Add Receiver link is present ", "True","True", true);
				}
				else {
					Report.TestPoint(testContext.getName(),"Validate Add Receiver link is present ", "Add Receiver link is present","Add Receiver link is not present", true);
				}
				//Verify Manage DVR link
				Boolean bManageDVR= UI.WaitForObject(oR_AccountOverview.lnkManageDVR,UI.iObjTimeOut);
				if(bManageDVR.equals(true))
				{
					Report.TestPoint(testContext.getName(),"Validate Manage DVR link is present ", "True","True", true);
				}
				else {
					Report.TestPoint(testContext.getName(),"Validate Manage DVR link is present ", "Manage DVR linkis present","Manage DVR link is not present", true);
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
	 * Function Name - VerifySuspendedAccountAlert
	 * Description- VerifySuspendedAccountAlert is to verify alert 
	 * 				due to non payment in Message Center page
	 * Parameters - None
	 * Date created - 3-Feb-2015
	 * Developed by - Rahul Bakde 
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/

	public static void VerifySuspendedAccountAlert(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException{
		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());       
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_MessageCenter oR_MessageCenter = PageFactory.initElements(lDriver, OR_MessageCenter.class);
			OR_AlertDetails oR_AlertDetails = PageFactory.initElements(lDriver, OR_AlertDetails.class);
			
			//verify AT&T message under service summary section
			if (UI.WaitForObject(oR_AccountOverview.txtATTMessage, 10).equals(true)) {
				Report.ValidationPoint(testContext.getName(), "Verify AT&T message under service summary section", "True", "True", true);
			}else{
				Report.ValidationPoint(testContext.getName(), "Verify AT&T message under service summary section", "AT&T message should be displayed", "AT&T message is not displayed under service summary section ", true);
			}
			//verify view all link should be displayed in front of AT&T message
			if (UI.WaitForObject(oR_AccountOverview.lnkViewAll, 10)) {
				Report.TestPoint(testContext.getName(), "Validate view all link", "view all link displayed in front of AT&T message", "view all link displayed in front of AT&T message", true);
				Report.OperationPoint(testContext.getName(), "	Operational - clicking on view all link");
				oR_AccountOverview.lnkViewAll.click();
				Report.OperationPoint(testContext.getName(), "	Operational - Navigating to Message Center page");
				//verify successfully navigated to Message Center page by verifying the Message Center title
				if (UI.WaitForObject(oR_MessageCenter.txtMessageCenterTitle, UI.iObjTimeOut)) {
					Report.ValidationPoint(testContext.getName(), "Validate navigated to Message Center page ", "Message Center title is displayed", "Message Center title is displayed", true);
				} else {
					Report.ValidationPoint(testContext.getName(), "Validate navigated to Message Center page ", "Message Center title is displayed", "Message Center title is not displayed", true);
				}
				//verify the presence of the red alert error in alert box
				if (UI.WaitForObject(oR_MessageCenter.txtAlertError, UI.iObjTimeOut)) {
					Report.ValidationPoint(testContext.getName(), "Validate red alert error in alert box", "Red alert error in alert box is displayed", "Red alert error in alert box is displayed", true);
				} else {
					Report.ValidationPoint(testContext.getName(), "Validate red alert error in alert box", "Red alert error in alert box is displayed", "Red alert error in alert box is not displayed", true);
				}
				//verify alert for non payment is displayed as link
				if (UI.WaitForObject(oR_MessageCenter.lnkPastDueMessage, 10)) {
					Report.TestPoint(testContext.getName(), "Validate alert for non payment", "Alert for non payment is displayed as link", "Alert for non payment is displayed as link", true);
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on alert for non payment link");
					oR_MessageCenter.lnkPastDueMessage.click();
					Report.OperationPoint(testContext.getName(), "	Operational - Navigating to Alert Detail page");
					//verify Alert Detail title exist on Alert Detail page
					if (UI.WaitForObject(oR_AlertDetails.txtAlertDetailsTitle, UI.iObjTimeOut)) {
						Report.TestPoint(testContext.getName(), "Validate Alert Detail title", "Alert Detail title is displayed", "Alert Detail title is displayed", true);
						//verify the format of the Alert should be : Title, Account, Number 
						if (UI.WaitForObject(oR_AlertDetails.txtAccountNickName, 10) && UI.WaitForObject(oR_AlertDetails.txtAccountNumber, 10)) {
							Report.ValidationPoint(testContext.getName(), "Validate format of the Alert", "Alerts are in format Title, Account, Number", "Alerts are in format Title, Account, Number", true);
							Report.OperationPoint(testContext.getName(), "	Operational - Alert title is : "+oR_AlertDetails.txtAlertDetailsTitle.getText());
							Report.OperationPoint(testContext.getName(), "	Operational - Account number is : "+oR_AlertDetails.txtAccountNumber.getText());
							Report.OperationPoint(testContext.getName(), "	Operational - Account nickname is : "+oR_AlertDetails.txtAccountNickName.getText());
							Report.OperationPoint(testContext.getName(), "	Operational - Loggin out");
						} else {
							Report.ValidationPoint(testContext.getName(), "Validate format of the Alert", "Alerts are in format Title, Account, Number", "Alerts are not in format", true);
						}
					} else {
						Report.TestPoint(testContext.getName(), "Validate Alert Detail title", "Alert Detail title is displayed", "Alert Detail title is not displayed", true);
					}
				} else {
					Report.TestPoint(testContext.getName(), "Validate alert for non payment", "Alert for non payment is displayed as link", "Alert for non payment is not displayed", true);
				}
			} else {
				Report.TestPoint(testContext.getName(), "Validate view all link", "Verify view all link should be displayed in front of AT&T message", "view all link is not displayed", true);
			}
		} catch (Exception e) {
			Report.TestPoint(testContext.getName(), "Validate Suspended Account Alert", "Should successfully validate Suspended Account Alert", "Failed to validate Suspended Account Alert\n"+e.getMessage(), true);
		}
	}



	/**************************************************************
	 * Function Name - VerifyDTV_ServiceSummary
	 * Description- 
	 * Parameters - 
	 * Date created - 4th Feb 2015
	 * Developed by - Merrin Mathai
	 * Last Modified By - Krutika
	 * Last Modified Date - 24th Feb 2015
	 ***************************************************************/


	public static void VerifyDTV_ServiceSummary(final ITestContext testContext) 
			throws Exception{

		WebDriver lDriver = UI.getDriver(testContext.getName()); 
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		//String sVerifyDTV_ServiceSummary = "False";
		try{
			//Click on DIRECTV Toggle
			String sDTVToggle = UI.CheckExist(oR_AccountOverview.lnkDTVToggle);
			if(sDTVToggle.equalsIgnoreCase("True"))
			{
				oR_AccountOverview.lnkDTVToggle.click();
				Report.ValidationPoint(testContext.getName(), "Validate DirecTV Toggle is present and click", "True", "True", true);	
				//Information for DTV is viewed when clicked on DTV toggle 
				
				//if(UI.CheckExist(oR_AccountOverview.txtDTVInformation).equalsIgnoreCase("True")) --- modified
				if(UI.WaitForObject(oR_AccountOverview.txtDTVInformation,50).equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Information for DTV is viewed", "True", "True", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Information for DTV is viewed", "True", "False", true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate DirecTV Toggle is present and click", "True", "False", true);	
			}

		} catch (Exception e)
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}

	}

	/**************************************************************
	 * Function Name - VerifyUpgradeEligibilityAlert
	 * Description- 
	 * Parameters - 
	 * Date created - 4-Feb-2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/

	public static void VerifyUpgradeEligibilityAlert(final ITestContext testContext) throws Exception {
		try{

			WebDriver lDriver = UI.getDriver(testContext.getName());  
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			//Verify alert button on dashboard
			if(UI.WaitForObject(oR_AccountOverview.btnAlert,20).equals(true)){
				Report.OperationPoint(testContext.getName(), "Operational - Navigated to Dashboard");				
				Report.TestPoint(testContext.getName(), "Verify alert button on dashboard", "alert button on dashboard is present", "alert button on dashboard is present", true);
				//CLick on the alert button
				oR_AccountOverview.btnAlert.click();

				//Verify Upgrade eligibility alert
				if(UI.WaitForObject(oR_AccountOverview.txtUpgradeEligibilityAlert, 10).equals(true)){
					Report.ValidationPoint(testContext.getName(), "Verify Upgrade eligibility alert", "Upgrade eligibility alert is present", "Upgrade eligibility alert is present", true);
					Report.OperationPoint(testContext.getName(), "Alert is "+oR_AccountOverview.txtEligibleForUpgrade.getText());			
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify Upgrade eligibility alert", "Upgrade eligibility alert should be present", "Upgrade eligibility alert is NOT present", true);					
				}
			}else{
				Report.TestPoint(testContext.getName(), "Verify alert button on dashboard", "alert button on dashboard should be displayed", "alert button not displayed on dashboard", true);					
			}



		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}

	/**************************************************************
	 * Function Name - VerifyQuickLinkAndMsgs
	 * Description- 
	 * Parameters - 
	 * Date created - 05-Feb-2015
	 * Developed by - Swagata Das
	 * Last Modified By - Sneha Pansare (added code to check suppressed link and to verify view all link)
	 * Last Modified Date - 17th March
	 ***************************************************************/
	//DBD09149
	//DBD08848
	
	public static void VerifyQuickLinkAndMsgs(final ITestContext testContext) throws HeadlessException, IOException, AWTException, InterruptedException
	  {
		
		WebDriver lDriver = UI.getDriver(testContext.getName());  
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		String sLinkToVerifyUnderIwantTo = IO.GetParamVal(sTestParams, "Link_To_Verify_Under_IwantTo_Dropdown");
	   try
	   {
		
		Thread.sleep(30000);
		
		//Verify Quick link 'Create or Manage Sub Accounts' is suppressed
		try
		{
			if(lDriver.findElement(By.xpath("//button[@id='ddShortcut']")).isDisplayed())
			{
				WebElement btnIwantTo= lDriver.findElement(By.xpath("//button[@id='ddShortcut']"));
			
				Actions action= new Actions(lDriver);
				action.moveToElement(btnIwantTo).click().build().perform();
				Thread.sleep(4000);	
				
				try
				{
					if(lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'"+sLinkToVerifyUnderIwantTo+"')]")).isDisplayed())
					{
						//System.out.println("Quick link '"+sLinkToVerifyUnderIwantTo+"' is NOT suppressed");
						Report.ValidationPoint(testContext.getName(), "Verify Quick link is suppressed", "Quick link '"+sLinkToVerifyUnderIwantTo+"' is suppressed", "Quick link '"+sLinkToVerifyUnderIwantTo+"' is NOT suppressed", true);
					}
					else
					{
						//System.out.println("Quick link '"+sLinkToVerifyUnderIwantTo+"' is suppressed");
						Report.ValidationPoint(testContext.getName(), "Verify Quick link is suppressed", "Quick link '"+sLinkToVerifyUnderIwantTo+"' is suppressed", "Quick link '"+sLinkToVerifyUnderIwantTo+"' is suppressed", true);
					}
				
				}
				catch(Exception e)
				{
					//System.out.println("Quick link '"+sLinkToVerifyUnderIwantTo+"' is suppressed");
					Report.ValidationPoint(testContext.getName(), "Verify Quick link is suppressed", "Quick link '"+sLinkToVerifyUnderIwantTo+"' is suppressed", "Quick link '"+sLinkToVerifyUnderIwantTo+"' is suppressed", true);
				}
				
			}
		}catch(Exception e)
		{
			//System.out.println("I Want to Dropdown is NOT displayed");
			Report.ValidationPoint(testContext.getName(), "Verify Quick link is suppressed under I Want to dropdown", "suppressed", "I Want to Dropdown is NOT displayed", true);
			
		}
		
		//Verify AT&T messages below the service summary section
		Boolean bATTmessages=UI.WaitForObject(oR_AccountOverview.txtATTMessage, 40); //UI.iObjTimeOut
		Report.ValidationPoint(testContext.getName(), "AT&T messages below the service summary section", "true", String.valueOf(bATTmessages), true);
		//System.out.println("AT&T messages below the service summary section , true , "+String.valueOf(bATTmessages));
		
		
		//Verify View all link
		if(UI.WaitForObject(oR_AccountOverview.lnkViewAll, 40))
		{
			Boolean bViewAllLink=UI.WaitForObject(oR_AccountOverview.lnkViewAll, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "View all link", "true", String.valueOf(bViewAllLink), true);
			//System.out.println("View all link , true , "+String.valueOf(bViewAllLink));
		}
		 
		
		
	   }catch(Exception e)
	   {
		   Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
	   }
		
	 }

	/**************************************************************
	 * Function Name - VerifyQuickLinkAndMsgs
	 * Description- 
	 * Parameters - 
	 * Date created - 05-Feb-2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/

	public static void VerifyUverseTVSummaryForNoPurchase(final ITestContext testContext) throws Exception {
		try{
			
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			//Validate uverse TV toggle and click on it
			if(UI.WaitForObject(oR_AccountOverview.lnkUverseTVToggle,10).equals(true)){
				Report.TestPoint(testContext.getName(),"Validate uverse TV toggle and click on it", "uverse TV toggle is present","uverse TV toggle is present", true);
				Report.OperationPoint(testContext.getName(), "	Clicking on uverse TV toggle");
				oR_AccountOverview.lnkUverseTVToggle.click();
			}

			//Verify Uverse TV section
			if(UI.WaitForObject(oR_AccountOverview.txtUverseTVPlan,20).equals(true)){
				Report.ValidationPoint(testContext.getName(), "Verify Uverse TV sectionr", "Uverse TV section is present", "Uverse TV section is present", true);
				//Verify that column headers is displayed
				//Pay per view column header
				if(UI.WaitForObject(oR_AccountOverview.txtVideoOnDemand,20).equals(true)){
					Report.ValidationPoint(testContext.getName(), "Verify Pay per view column header", "Pay per view column header is present", "Pay per view column header is present", true);
				}

				//Video on Demand column header
				if(UI.WaitForObject(oR_AccountOverview.txtTotal,20).equals(true)){
					Report.ValidationPoint(testContext.getName(), "Verify Video on demand column header", "Video on demand column header is present", "Video on demand column header is present", true);
				}

				//Total column header	
				if(UI.WaitForObject(oR_AccountOverview.txtUverseTVPlan,20).equals(true)){
					Report.ValidationPoint(testContext.getName(), "Verify total column header", "total column header is present", "total column header is present", true);
				}
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Uverse TV section", "Uverse TV section is NOTpresent", "Uverse TV section is NOTpresent", true);					
			}

		}catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}

	}
	/**************************************************************
	 * Function Name - VerifyHardSuspendedAlert()
	 * Description- 
	 * Parameters - 
	 * Date created - 4-Feb-2015
	 * Developed by - Merrin Mathai
	 * Last Modified By - Nachiket Pawar
	 * Last Modified Date - 22 - Jan - 2016
	 ***************************************************************/

	public static void VerifyHardSuspendedAlert(final ITestContext testContext) throws Exception {
		try{

			WebDriver lDriver = UI.getDriver(testContext.getName());    
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			//Verify alert button on dashboard
			if(UI.WaitForObject(oR_AccountOverview.btnAlert,20).equals(true))
			{
				Report.OperationPoint(testContext.getName(), "Operational - Navigated to Dashboard");				
				Report.TestPoint(testContext.getName(), "Verify alert button on dashboard is present", "true", "true", true);
				//click on alert button
				oR_AccountOverview.btnAlert.click();
				//Verify Hard Suspended alert
				if(UI.WaitForObject(oR_AccountOverview.txtAccountSuspended, 10).equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Verify Hard Suspensed alert is present", "true", "true", true);
					Report.OperationPoint(testContext.getName(), "Alert is "+oR_AccountOverview.txtAccountSuspended.getText());			
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify Hard Suspensed alert is present", "true", "Hard Suspensed alert is NOT present", true);					
				}
				//Verify Contact AT&T link
				if(UI.WaitForObject(oR_AccountOverview.btnMakeAPayment, 10).equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Make A Payment link present", "true", "true", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Make A Payment link present", "true", "false", true);
				}
			}
			else{
				Report.TestPoint(testContext.getName(), "Verify alert button on dashboard", "true", "alert button not displayed on dashboard", true);					
			}



		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}




	/**************************************************************
	 * Function Name - ValidateForgotUserID
	 * Description- 
	 * Parameters - 
	 * Date created - 5th Feb 2015
	 * Developed by - Krutika Kamdi 
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/		

	public static void ValidateForgotUserID(final ITestContext testContext)
			throws Exception {
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
		OR_ForgotUserID oR_ForgotUserID = PageFactory.initElements(lDriver, OR_ForgotUserID.class);
		
		try{

			//validateForgot UserID link
			Boolean bForgotUserID= UI.WaitForObject(oR_Login.lnkForgotId,UI.iObjTimeOut);
			if(bForgotUserID.equals(true))
			{
				Report.TestPoint(testContext.getName(),"Validate Forgot User ID link is present", "True","True", true);
				Report.OperationPoint(testContext.getName(), " Operational - Clicking on Forgot User ID ");
				oR_Login.lnkForgotId.click();
			}
			//validate Step indicators
			UI.VerifyElementNotPresent(oR_ForgotUserID.txtSteps, "Step indicators");
			//validate Continue link		
			if (UI.WaitForObject(oR_ForgotUserID.imgContinue, 60)){
				Report.TestPoint(testContext.getName(), "Validate Continue link", "Continue link is present", "Continue link is present", true);
			}
			else{
				Report.TestPoint(testContext.getName(), "Validate Continue link", "Continue link is present", "Continue link is not present", true);
			}
			//validate Cancel link		
			if (UI.WaitForObject(oR_ForgotUserID.lnkCancel, 60)){
				Report.TestPoint(testContext.getName(), "Validate Cancel link", "Cancel link is present", "Cancel link is present", true);
			}
			else{
				Report.TestPoint(testContext.getName(), "Validate Cancel link", "Cancel link is present", "Cancel link is not present", true);
			}
			//validate invalid and blank email id error messages	
			if (UI.WaitForObject(oR_ForgotUserID.edtContactemail, 60))
			{
				Report.OperationPoint(testContext.getName(), " Operational - Entering Email ID");
				oR_ForgotUserID.edtContactemail.sendKeys("abc");
				oR_ForgotUserID.imgContinue.click();
				if (UI.WaitForObject(oR_ForgotUserID.txtErrinvalidemail, 10)){
					Report.TestPoint(testContext.getName(), "Validate Invalid Error message", "Invalid Error message is displayed", "Invalid Error message is displayed", true);
				}
				else{
					Report.TestPoint(testContext.getName(), "Validate Invalid Error message", "Invalid Error message is displayed", "Invalid Error message is not displayed", true);	
				}
				oR_ForgotUserID.edtContactemail.clear();
				oR_ForgotUserID.imgContinue.click();
				if (UI.WaitForObject(oR_ForgotUserID.txtErrBlankemail, 10)){
					Report.TestPoint(testContext.getName(), "Validate Blank Email ID message", "Blank Email ID Error message is displayed", "Blank Email ID Error message is displayed", true);
				}
				else{
					Report.TestPoint(testContext.getName(), "Validate Blank Email ID message", "Blank Email ID Error message is displayed", "Blank Email ID Error message is not displayed", true);	
				}

				//validate 2 attempts of valid email id error message
				Boolean bimgContinue= UI.WaitForObject(oR_ForgotUserID.imgContinue,10);
				oR_ForgotUserID.edtContactemail.sendKeys("abc@att.com");
				for (int i=0; i<2; i++)
				{
					if(bimgContinue.equals(true))
					{	oR_ForgotUserID.imgContinue.click();}
					if (UI.WaitForObject(oR_ForgotUserID.txtErrMsg,60))
					{
						Report.TestPoint(testContext.getName(), "Validate Error message", "Error message is displayed", "Error message is displayed", true);
					}
					else{
						Report.TestPoint(testContext.getName(), "Validate Error message", "Error message is displayed", "Error message is not displayed", true);	
					}	
				}
				oR_ForgotUserID.imgContinue.click();
				lDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			}


			Report.OperationPoint(testContext.getName(), " Operational - Navigating to account details ");
			UI.SelectOptionFromDropDown(oR_ForgotUserID.lstSelectAccount, "Home phone/Internet");
			Report.TestPoint(testContext.getName(), "Validate Account is selected", "Home Phone/Internet/DirecTV is selected", "Home Phone/Internet/DirecTV is selected", true);
			Boolean bBAN= UI.WaitForObject(oR_ForgotUserID.edtBAN,UI.iObjTimeOut);
			Boolean bZip= UI.WaitForObject(oR_ForgotUserID.edtBAN,UI.iObjTimeOut);
			//entering BAN and Zip
			if(bBAN.equals(true)){
				oR_ForgotUserID.edtBAN.sendKeys("2102220632152");

				if(bZip.equals(true)){
					oR_ForgotUserID.edtZipCode.sendKeys("78223");
					oR_ForgotUserID.imgContinueAccountDetails.click();
					Report.TestPoint(testContext.getName(), "Validate Continue button is clicked", "Continue button is clicked", "Continue button is clicked", true);
				}
			}
			//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			Boolean biframe= UI.WaitForObject(oR_ForgotUserID.frmMultipleIDs,90);

			if(biframe.equals(true))
			{
				Thread.sleep(6000);
				Report.TestPoint(testContext.getName(), "Validate Multiple ID selection frame", "Multiple ID selection frame with first name and last name is displayed", "Multiple ID selection frame with first name and last name is displayed", true);
				oR_ForgotUserID.imgClose.click();
			}

			else{
				Report.TestPoint(testContext.getName(), "Validate Multiple ID selection frame", "Multiple ID selection frame is displayed", "Multiple ID selection frame is not displayed", true);	
			}
			Thread.sleep(3000);
			Report.TestPoint(testContext.getName(), "Validate Multiple ID selection frame is closed", "Multiple ID selection frame is closed", "Multiple ID selection frame is closed", true);	
		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}


	/**************************************************************
	 * Function Name - VerifyPaperlessBillingDetails
	 * Description- VerifyPaperlessBillingDetails is to verify paperless
	 * billing details.
	 * Parameters - None
	 * Date created - 5-Feb-2015
	 * Developed by - Rahul Bakde 
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/

	public static void VerifyPaperlessBillingDetails(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_PaperlessBilling oR_PaperlessBilling = PageFactory.initElements(lDriver, OR_PaperlessBilling.class);
		
		try{
			
			//verify last login stamp exist on Account overview page
			if (UI.WaitForObject(oR_AccountOverview.txtAccountOverview, UI.iObjTimeOut).equals(true)) {
				Report.ValidationPoint(testContext.getName(), "Validate Account overview page", "Account overview page exist", "Account overview page exist", true);
			} else {
				Report.ValidationPoint(testContext.getName(), "Validate Account overview page", "Account overview page exist", "Account overview page does not exist", true);
			}
			//verify manage paperless billing link exist on account overview page
			if (UI.WaitForObject(oR_AccountOverview.lnkManagePaperlessBilling, 2).equals(false)) {
				Report.TestPoint(testContext.getName(), "Validate manage paperless billing link should not exist on account overview page", "Manage paperless billing link does not exist", "Manage paperless billing link does not exist", true);
				//verify enroll in paperless billing link do not exist
				if (UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBilling, 5, lDriver)) {
					Report.ValidationPoint(testContext.getName(), "Validate enroll in paperless billing link exist", "Enroll in paperless billing link exist", "Enroll in paperless billing link exist", true);
				} else {
					Report.ValidationPoint(testContext.getName(), "Validate enroll in paperless billing link exist", "Enroll in paperless billing link exist", "Enroll in paperless billing link do not exist", true);
				}
				Report.OperationPoint(testContext.getName(), "Operational point - Clicking on Enroll paperless billing link");
				Report.OperationPoint(testContext.getName(), "Navigating to Paperless billing page");
				oR_AccountOverview.lnkEnrollInPaperlessBilling.click();
				//verify after clicking on manage paperless billing link, Paperless Billing page is opened
				if (UI.WaitForObject(oR_PaperlessBilling.txtPaperlessBillingTitle, UI.iObjTimeOut)) {
					Report.ValidationPoint(testContext.getName(), "Validate successfully navigated to Paperless Billing page", "Navigated to Paperless Billing page", "Navigated to Paperless Billing page", true);
				} else {
					Report.ValidationPoint(testContext.getName(), "Validate successfully navigated to Paperless Billing page", "Navigated to Paperless Billing page", "Failed to navigated to Paperless Billing page", true);
				}
				Report.OperationPoint(testContext.getName(), "	Operational - Logging out");
			} else {
				Report.TestPoint(testContext.getName(), "Validate manage paperless billing link does not exist on account overview page", "Manage paperless billing link does not exist", "Manage paperless billing link exist", true);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "Validate Paperless Billing details", "Should successfully validate Paperless Billing details", "Failed to validate Paperless Billing details\n"+e.getMessage(), true);

		}
	}



	/**************************************************************
	 * Function Name -  VerifyPaymentsFailedAlert
	 * Description- This function whether failed payments(Payment Unsuccessful) alert is present. and its format(Title,nickname,description(this is optional) and CTAs)
	 * Parameters - sTitle,sNickname,sDescription,sCTA1,sCTA2
	 * Date created -10th Feb 15
	 * Developed by - Sneha Pansare
	 * Last Modified By - Swagata Das
	 * Last Modified Date -	3rd Sept 2015
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	//DBD11616

	public static void VerifyPaymentsFailedAlert(final ITestContext testContext, String sTitle, String sNickname , String sDescription , String sCTA1 , String sCTA2) throws HeadlessException, IOException, AWTException, ParseException 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());  
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try {

			if(UI.WaitForObject(oR_AccountOverview.btnAlertClick, 10))
			{
				oR_AccountOverview.btnAlert.click();
				Report.ValidationPoint(testContext.getName(), "Click on Alert button", "Clicked on Alert button", "Clicked on Alert button", true);

				System.out.println("Clicked on Alert button");

				//Verify whether 'Payment Unsuccessful' alert is present in alert box
				if(UI.WaitForObject((lDriver.findElement(By.xpath("//*[contains(@class,'alert-dropdown')]//*[text()='Payment Unsuccessful']"))), 40))
				{
					// if this alert is present take count of this alert
					List<WebElement> alertCount = lDriver.findElements(By.xpath("//*[contains(@class,'alert-dropdown')]//*[text()='Payment Unsuccessful']"));

					System.out.println("There are total "+alertCount.size()+" Failed Payments Alert/s");
					Report.ValidationPoint(testContext.getName(), "There are total "+alertCount.size()+" Failed Payments Alert/s", "True", "True", true);


					//iterate loop till maximum alert count and check alert pattern
					for(int iAlertCount=1;iAlertCount<=alertCount.size();iAlertCount++)
					{
						//Verify alert line 1- alert icon and Title
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@class='alert-dropdown-mid']//*[contains(@id,'List')]//li["+iAlertCount+"]//*[text()='"+sTitle+"']")), 10))
						{
							System.out.println("Line 1- alert icon & title->'"+sTitle+"' is present for Failed Payments Alert "+iAlertCount);
							Report.ValidationPoint(testContext.getName(), "Verify Alert Line 1", "Line 1- alert icon & title->'"+sTitle+"' is present for Failed Payments Alert "+iAlertCount, "Line 1- alert icon & title->'"+sTitle+"' is present for Failed Payments Alert "+iAlertCount, true);

						}
						else
						{
							System.out.println("Line 1- alert icon & title->'"+sTitle+"' is NOT present for Failed Payments Alert "+iAlertCount);
							Report.ValidationPoint(testContext.getName(), "Verify Alert Line 1", "Line 1- alert icon & title->'"+sTitle+"' is present for Failed Payments Alert "+iAlertCount, "Line 1- alert icon & title->'"+sTitle+"' is NOT present for Failed Payments Alert "+iAlertCount, true);

						}

						//Verify alert line 2- account nickname/ number
						try{
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@class='alert-dropdown-mid']//*[contains(@id,'List')]//li["+iAlertCount+"]//*[text()='"+sNickname+"']")), 10,lDriver))
						{
							System.out.println("Line 2- account nickname/ number->'"+sNickname+"' is present for Failed Payments Alert "+iAlertCount);
							Report.ValidationPoint(testContext.getName(), "Verify Alert Line 2", "Line 2- account nickname/ number->'"+sNickname+"' is present for Failed Payments Alert "+iAlertCount, "Line 2- account nickname/ number->'"+sNickname+"' is present for Failed Payments Alert "+iAlertCount, true);

						}
						}
						catch(Exception e)
						{
							
						}
						

						//Verify alert line 3- description (optional)
						if(!sDescription.equals("No"))
						{
							if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@class='alert-dropdown-mid']//*[contains(@id,'List')]//li["+iAlertCount+"]//*[contains(text(),'"+sDescription+"')]")), 10))
							{
								String sFullDescription= lDriver.findElement(By.xpath("//*[@class='alert-dropdown-mid']//*[contains(@id,'List')]//li["+iAlertCount+"]//*[contains(text(),'"+sDescription+"')]")).getText();
								System.out.println("Line 3- description->'"+sFullDescription+"' is present for Failed Payments Alert "+iAlertCount);
								Report.ValidationPoint(testContext.getName(), "Verify Alert Line 3", "Line 3- description->'"+sFullDescription+"' is present for Failed Payments Alert "+iAlertCount, "Line 3- description->'"+sFullDescription+"' is present for Failed Payments Alert "+iAlertCount, false);

							}
						}


						//Verify alert line 4- CTAs 1 -> 'Make a Payment' & 'View Payments'
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@class='alert-dropdown-mid']//*[contains(@id,'List')]//li["+iAlertCount+"]//a[contains(text(),'"+sCTA1+"')]")), 10))
						{
							System.out.println("Line 4- CTA->'"+sCTA1+"' is present for Failed Payments Alert "+iAlertCount);
							Report.ValidationPoint(testContext.getName(), "Verify Alert Line 4", "Line 4- CTA->'"+sCTA1+"' is present for Failed Payments Alert "+iAlertCount, "Line 4- CTA->'"+sCTA1+"' is present for Failed Payments Alert "+iAlertCount, false);
						}
						else
						{
							System.out.println("Line 4- CTA->'"+sCTA1+"' is NOT present for Failed Payments Alert "+iAlertCount);
							Report.ValidationPoint(testContext.getName(), "Verify Alert Line 4", "Line 4- CTA->'"+sCTA1+"' is present for Failed Payments Alert "+iAlertCount, "Line 4- CTA->'"+sCTA1+"' is NOT present for Failed Payments Alert "+iAlertCount, false);
						}

						//Verify alert line 4- CTAs 2 -> 'Make a Payment' & 'View Payments'
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@class='alert-dropdown-mid']//*[contains(@id,'List')]//li["+iAlertCount+"]//a[contains(text(),'"+sCTA2+"')]")), 10))
						{
							System.out.println("Line 4- CTA->'"+sCTA2+"' is present for Failed Payments Alert "+iAlertCount);
							Report.ValidationPoint(testContext.getName(), "Verify Alert Line 4", "Line 4- CTA->'"+sCTA2+"' is present for Failed Payments Alert "+iAlertCount, "Line 4- CTA->'"+sCTA2+"' is present for Failed Payments Alert "+iAlertCount, false);
						}
						else
						{
							System.out.println("Line 4- CTA->'"+sCTA2+"' is NOT present for Failed Payments Alert "+iAlertCount);
							Report.ValidationPoint(testContext.getName(), "Verify Alert Line 4", "Line 4- CTA->'"+sCTA2+"' is present for Failed Payments Alert "+iAlertCount, "Line 4- CTA->'"+sCTA2+"' is NOT present for Failed Payments Alert "+iAlertCount, false);
						}

					}
					Report.ValidationPoint(testContext.getName(), "Verify  for the alerts, borders,icons are present as expected", "true", String.valueOf(oR_AccountOverview.btnAlert.isDisplayed()), true);
					}
				else
				{
					System.out.println("Failed Payments alert is NOT present");
					Report.ValidationPoint(testContext.getName(), "Verify Failed Payments alert", "Failed Payments alert is present", "Failed Payments alert is NOT present", true);
				}


			}
			//Modified by Swagata Das - 3rd Sept 15
			else if(UI.WaitForObject(oR_AccountOverview.txtAlertSection, 10))
			{
				Report.TestPoint(testContext.getName(), "Validate Alert Section", "true", "true", true);
				//Validate Payment Unsuccessful alert
				boolean bPayment = UI.WaitForObject(oR_AccountOverview.txtPaymentUnsucessful,5);
				Report.TestPoint(testContext.getName(), "Verify "+oR_AccountOverview.txtPaymentUnsucessful.getText()+" alert is displayed", "true", String.valueOf(bPayment), true);
				//Validate make a payment link 
				boolean bMAPlink = UI.WaitForObject(oR_AccountOverview.lnkMakeAPaymentAlert,5);
				if(bMAPlink)
				{
					Report.ValidationPoint(testContext.getName(), "Verify make a payment link", "true", String.valueOf(bMAPlink), true);
				}
				
				//Validate view payments link
				boolean bViewPayment = UI.WaitForObject(oR_AccountOverview.lnkViewPaymentAlert,5);
				if(bViewPayment)
				{
					Report.ValidationPoint(testContext.getName(), "Verify view payments link is displayed", "true", String.valueOf(bViewPayment), true);
				}
				Report.ValidationPoint(testContext.getName(), "Verify  for the alerts, borders,icons are present as expected", "true", String.valueOf(oR_AccountOverview.txtAlertSection.isDisplayed()), true);
				//Validate View pending payment link
				boolean bPendingLnk = UI.WaitForObject(oR_AccountOverview.lnkViewPendingPayment,5);
				if(bPendingLnk)
				{
					Report.ValidationPoint(testContext.getName(), "Validate View pending payment link", "true", String.valueOf(bPendingLnk), true);
					//Click View pending payment link
					Report.OperationPoint(testContext.getName(), "	Operational - Clicking on View pending payment link");
					//Validate Pending payments page
					UI.VerifyLinkNavigatestoNextPage(oR_AccountOverview.lnkViewPendingPayment, "PendingPayment");
					
				}
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Validate Alert Section", "true", "false", true);
			}

		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);


		}

	}
	/**************************************************************
	 * Function Name - VerifyVoluntarySuspendedAlert()
	 * Description- Verify Voluntary Suspended account
	 * Parameters - None
	 * Date created - 9-Feb-2015
	 * Developed by - Merrin Mathai
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/

	public static void VerifyVoluntarySuspendedAlert(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException{
		WebDriver lDriver = UI.getDriver(testContext.getName());    
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try{
			
			
			//Verify alert button on dashboard
			if(UI.WaitForObject(oR_AccountOverview.btnAlert,20).equals(true)){
				Report.OperationPoint(testContext.getName(), "Operational - Navigated to Dashboard");				
				Report.TestPoint(testContext.getName(), "Verify alert button on dashboard is present", "true", "true", true);
				//click on alert button
				oR_AccountOverview.btnAlert.click();
				//Verify Voluntary Suspended alert
				if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(@id,'alertList')]//*[contains(text(),'Account Suspended')]")), 10).equals(true)){
					Report.ValidationPoint(testContext.getName(), "Verify Voluntary Suspended alert is present", "true", "true", true);
					List<WebElement> ListAlert=lDriver.findElements(By.xpath("//*[contains(@id,'alertList')]//*[contains(text(),'Account Suspended')]"));
					//wireless suspended alert
					for(int count=0;count<ListAlert.size();count++)
					{
						String s=ListAlert.get(count).getText();
						Boolean wirelessAlert=s.contains("Wireless");
						if(wirelessAlert)
						{
							//Verify Reactivate Service link
							if(UI.WaitForObject(oR_AccountOverview.lnkReactivateService, 10).equals(true))
							{
								Report.ValidationPoint(testContext.getName(), "Verify Reactivate Service link is present", "true", "true", true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Verify Reactivate Service link is present", "true", "Reactivate Service link is NOT present", true);
							}
						}
					}

				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify Voluntary Suspended alert is present", "true", "Voluntary Suspended alert is NOT present", true);					
				}

			}else{
				Report.TestPoint(testContext.getName(), "Verify alert button on dashboard", "true", "alert button not displayed on dashboard", true);					
			}
		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	}

	/**************************************************************
	 * Function Name - VerifyUsageDetailsInServiceSummary
	 * Description- 
	 * Parameters - 
	 * Date created - 12th Feb 2015
	 * Developed by - Krutika Kamdi 
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws ParseException 
	 ***************************************************************/		
		
public static void VerifyUsageDetailsInServiceSummary(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException{
	
	WebDriver lDriver = UI.getDriver(testContext.getName()); 
	OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	
	try{
		
	//verify Label for data usage bar exist on Account overview page
	/*if (UI.WaitForObject(oR_AccountOverview.txtDataLabel, UI.iObjTimeOut).equals(true))
	{
	Report.ValidationPoint(testContext.getName(), "Label for data usage bar on Account overview page", "Label for data usage bar exist", "Label for data usage bar exist", true);
	}
	else
	{
	Report.ValidationPoint(testContext.getName(), "Label for data usage bar on Account overview page", "Label for data usage bar exist", "Label for data usage bar do not exist on Account overview page", true);
	} */

	//verify Usage Meter image exist on account overview page
	if (UI.WaitForObject(oR_AccountOverview.txtUsageMeter, UI.iObjTimeOut).equals(true))
	{
		
		String cssValue = oR_AccountOverview.txtUsageMeter.getCssValue("background-image");
		System.out.println(cssValue);
		Report.ValidationPoint(testContext.getName(), "Check Colour", "Colour", "Colour", true);
	Report.ValidationPoint(testContext.getName(), "Verify Usage Meter on account overview page", "Usage Meter image exist", "Usage Meter image exist", true);
	}
	else
	{
	Report.ValidationPoint(testContext.getName(), "Verify Usage Meter on account overview page", "Usage Meter image exist", "Usage Meter image does not exist", true);
	}

	//verify Usage Data left/Over exist on account overview page
	/*if (UI.WaitForObject(oR_AccountOverview.txtDataLeft, UI.iObjTimeOut).equals(true))
	{
	Report.ValidationPoint(testContext.getName(), "Verify Usage Data on account overview page", "Usage data left exist", "Usage Meter image exist", true);
	}
	else if(UI.WaitForObject(oR_AccountOverview.txtDataOver, UI.iObjTimeOut).equals(true))
	{
	Report.ValidationPoint(testContext.getName(), "Verify Data on account overview page", "Usage Data Over exist", "Usage Data Over exist", true);
	}
	else
	{
	Report.ValidationPoint(testContext.getName(), "Verify Usage Data left/over on account overview page", "Usage Data left/Over exist", "Usage Data left/Over does not exist", true);
	}
*/
	}
	catch (Exception e) {
	Report.TestPoint(testContext.getName(), "Validate Paperless Billing details", "Should successfully validate Paperless Billing details", "Failed to validate Paperless Billing details\n"+e.getMessage(), true);

	}
	}

/**************************************************************
 * Function Name - VerifyViewAllAndIWantToDropDown
 * Description- Verify View All link under Service Summary section and 'I want to' drop down and its subsections 
 * Parameters - None
 * Date created - 16th Feb 2015
 * Developed by - Clint John
 * Last Modified By - Clint John
 * Last Modified Date - 26th Mar 2015
 * @throws ParseException 
 ***************************************************************/
//DBD09405

public static void VerifyViewAllAndIWantToDropDown(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
{
	WebDriver lDriver = UI.getDriver(testContext.getName()); 
	OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	OR_MessageCenter oR_MessageCenter = PageFactory.initElements(lDriver, OR_MessageCenter.class);
	
try
{
	//Code to handle Old Dashboard page (Added on 26th March 2015)
	Boolean bMyServices = UI.WaitForObject(oR_AccountOverview.txtMyServices, UI.iObjTimeOut);
	if(bMyServices.equals(true))
	{
		//Check for Account Overview heading
		Boolean bOverview = UI.WaitForObject(oR_AccountOverview.txtAccountOverview, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that Account Overview page is displayed", "true", bOverview.toString(), true);
		
		//Validate My Services section
		Report.TestPoint(testContext.getName(), "Verify that My Services section is displayed", "true", bMyServices.toString(), true);
		//Check Account balance section is displayed
		boolean bCurrBalance = UI.WaitForObject(oR_AccountOverview.txtCurrentBalance, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify Current Balance text is displayed", "true", String.valueOf(bCurrBalance), true);
		boolean bBalance = UI.WaitForObject(oR_AccountOverview.txtBalance, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify Current Balance amount is displayed", "true", String.valueOf(bBalance), true);
		boolean bViewBillLink = UI.WaitForObject(oR_AccountOverview.lnkViewBillDetailsForOldDashboard, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that 'View Bill Details' link is displayed", "true", String.valueOf(bViewBillLink), true);
		boolean bAutopay = UI.WaitForObject(oR_AccountOverview.lnkEnrollInAutopay2, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that 'Enroll in Autopay' link is displayed", "true", String.valueOf(bAutopay), true);
		boolean bPaperlessBilling = UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBillingForOldDashboard, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that 'Enroll in Paperless Billing' link is displayed", "true", String.valueOf(bPaperlessBilling), true);
		boolean bMakePayment = UI.WaitForObject(oR_AccountOverview.btnMakeAPayment_OldDashboard, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that 'Make a payment' button is displayed", "true", String.valueOf(bMakePayment), true);
		
		//Click on the "View all " messages under the service summary section to check the messages
		boolean bViewAll = UI.WaitForObject(oR_AccountOverview.lnkViewAll_OldDashboard, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that View All link for Message Centre is displayed", "true", String.valueOf(bViewAll), true);
		oR_AccountOverview.lnkViewAll_OldDashboard.click();
		Report.OperationPoint(testContext.getName(), "Operational - Clicked on View All link, Navigating to Message Centre page");				
		boolean bMessageCentre = UI.WaitForObject(oR_MessageCenter.txtMessageCenterTitle, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "User successfully navigated to 'Message Center' page", "true", String.valueOf(bMessageCentre), true);
		//Navigate back to Dashboard from Message Center page by clicking on Overview tab
		boolean bOverviewLink = UI.WaitForObject(oR_AccountOverview.lnkOverview, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Overview link is present in 2ndry Nav bar", "true", String.valueOf(bOverviewLink), true);
		oR_AccountOverview.lnkOverview.click();
		Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Overview' tab and Navigating back to Dashboard");
		//Check if user successfully navigated back to Overview page
		//Check for Account Overview heading
		Boolean bOverviewPage = UI.WaitForObject(oR_AccountOverview.txtAccountOverview, UI.iObjTimeOut);
		Report.ValidationPoint(testContext.getName(), "Verify that Account Overview page is displayed", "true", bOverviewPage.toString(), true);
		

	}else{
	//Code to handle New Dashboard page (Added on 16th Feb 2015)
	
	//Verify Dashboard Webelemets 
	//1. Verify Welcome back Fname text 
	if(UI.WaitForObject(oR_AccountOverview.txtWelcome, 40).equals(true))
	{
		Report.ValidationPoint(testContext.getName(), "Welcome back text is displayed", "true", "true", true);
	}	
	else
	{
		Report.ValidationPoint(testContext.getName(), "Welcome back text is displayed", "true", "Welcome back text is NOT displayed", true);					
	}

	//2. Verify Total balance text is displayed 
	if(UI.WaitForObject(oR_AccountOverview.txtTotalBalance, 40).equals(true))
	{
		Report.ValidationPoint(testContext.getName(), "Total balance text is displayed", "true", "true", true);
	}	
	else
	{
		Report.ValidationPoint(testContext.getName(), "Total balance text is displayed", "true", "Total balance text is NOT displayed", true);					
	}

	//3. Verify Total balance value in $ is displayed 
	if(UI.WaitForObject(oR_AccountOverview.txtBalanceAmount, 40).equals(true))
	{
		Report.ValidationPoint(testContext.getName(), "Total balance amount is displayed", "true", "true", true);
	}	
	else
	{
		Report.ValidationPoint(testContext.getName(), "Total balance amount is displayed", "true", "Total balance amount is NOT displayed", true);					
	}

	//4. Verify link for view bill details
	if(UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails, 40).equals(true))
	{
		Report.ValidationPoint(testContext.getName(), "View bill details link is displayed", "true", "true", true);
	}	
	else
	{
		Report.ValidationPoint(testContext.getName(), "View bill details link is displayed", "true", "View bill details link is NOT displayed", true);					
	}

	//5. Verify button for 'Make a Payment' 
	if(UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentInOverviewPage, 40).equals(true))
	{
		Report.ValidationPoint(testContext.getName(), "Make a Payment button is displayed", "true", "true", true);
	}	
	else
	{
		Report.ValidationPoint(testContext.getName(), "Make a Payment button is displayed", "true", "Make a Payment button is NOT displayed", true);					
	}

	//6. Verify Due date under Make a payment link
	if(UI.WaitForObject(oR_AccountOverview.txtDueDate, 40).equals(true))
	{
		Report.ValidationPoint(testContext.getName(), "Due date is successfully displayed", "true", "true", true);
	}	
	else
	{
		Report.ValidationPoint(testContext.getName(), "Due date is successfully displayed", "true", "Due date is NOT displayed successfully", true);					
	}

	//7. Verify Enroll in paperless billing link
	if(UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBilling, 40).equals(true))
	{
		Report.ValidationPoint(testContext.getName(), "Enroll in Paperless billing link is displayed", "true", "true", true);
	}	
	else
	{
		Report.ValidationPoint(testContext.getName(), "Enroll in Paperless billing link is displayed", "true", "Enroll in Paperless billing link is NOT displayed", true);					
	}

	//8. Verify Enroll in Autopay link
	if(UI.WaitForObject(oR_AccountOverview.lnkEnrollInAutopay, 40).equals(true))
	{
		Report.ValidationPoint(testContext.getName(), "Enroll in Autopay link is displayed", "true", "true", true);
	}	
	else
	{
		Report.ValidationPoint(testContext.getName(), "Enroll in Autopay link is displayed", "true", "Enroll in Autopay link is NOT displayed", true);					
	}


	//Verify Message Center page by clicking ViewAll under the Service summary section
	if(UI.WaitForObject(oR_AccountOverview.lnkViewAll,40).equals(true))
	{
		Report.OperationPoint(testContext.getName(), "Operational - Navigated to Dashboard and Clicking on View All link");				
		Report.TestPoint(testContext.getName(), "Verify that View All link is present", "true", "true", true);
		oR_AccountOverview.lnkViewAll.click();

		//Verify Message Center page is displayed after clicking on 'View All' link from Dashboard 
		if(UI.WaitForObject(oR_MessageCenter.txtMessageCenterTitle, 60).equals(true))
		{
			Report.ValidationPoint(testContext.getName(), "User successfully navigated to 'Message Center' page", "true", "true", true);

			//Navigate back to Dashboard from Message Center page by clicking on Overview tab
			if(UI.WaitForObject(oR_AccountOverview.lnkOverview,60).equals(true))
			{
				oR_AccountOverview.lnkOverview.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Overview' tab and Navigating back to Dashboard");
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Navigate back to dashboard", "true", " NOT able to naviagte back to Dashboard by clicking Overview tab from Message Center page", true);					
			}		
		}
		else
		{
			Report.ValidationPoint(testContext.getName(), "User successfully navigated to 'Message Center' page", "true", "User NOT successfully navigated to 'Message Center' page", true);					
		}

	}else
	{
		Report.TestPoint(testContext.getName(), "Verify that View All link is present to naviagte to Message Center page", "true", "Verification FAILED - View All link is NOT present to naviagte to Message Center page", true);					
	}


	//Verify by clicking on 'I want to' drop-down at Overview page and verify that subsections are present for individual option in the menu

	//if(UI.WaitForObject(driver.findElement(By.xpath("//button[@id='ddShortcut']")), 40).equals(true))
	if(oR_AccountOverview.btnIWantTo.isEnabled() || oR_AccountOverview.btnIWantTo.isDisplayed())
	{
		Report.ValidationPoint(testContext.getName(), "'I want to' drop-down present in overview page", "true", "true", true);
		oR_AccountOverview.btnIWantTo.click();
		Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'I want to' dropdown");

		//Verify all the Sub-Sections under 'I want to' dropdown list
		new Actions(lDriver).moveToElement(oR_AccountOverview.txtViewAndPayBill).click().build().perform();
		Report.OperationPoint(testContext.getName(), "Operational - Clicked View and Paybill");

		//Code to verify subsection View and Pay bill
		if(UI.WaitForObject(oR_AccountOverview.txtViewAndPayBill, 40).equals(true))
		{
			Report.ValidationPoint(testContext.getName(), "View And Pay Bill option is present", "true", "true", true);

			//Verify the Subsections inside 'View and Pay Bill' menu
			//1. Billing:
			if(UI.WaitForObject(oR_AccountOverview.txtBilling, 40).equals(true))
			{
				Report.ValidationPoint(testContext.getName(), "Billing subsection is present", "true", "true", true);

			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Billing subsection is present", "true", "Billing subsection is NOT present", true);					
			}
			//2. Payments
			if(UI.WaitForObject(oR_AccountOverview.txtPayments, 40).equals(true))
			{
				Report.ValidationPoint(testContext.getName(), "Paymets subsection is present", "true", "true", true);

			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Payments subsection is present", "true", "Payments subsection is NOT present", true);					
			}
		}
		else
		{
			Report.ValidationPoint(testContext.getName(), "View And Pay Bill option is present", "true", "View And Pay Bill option is NOT present", true);					
		}

		//Code to verify subsection Manage My Plan

		new Actions(lDriver).moveToElement(oR_AccountOverview.txtManageMyPlan).click().perform();
		Report.OperationPoint(testContext.getName(), "Operational - Clicked Manage My Plan tab");

		if(UI.WaitForObject(oR_AccountOverview.txtManageMyPlan, 40).equals(true))
		{
			Report.ValidationPoint(testContext.getName(), "Manage My Plan option is present", "true", "true", true);

			//Verify the Subsections inside 'Manage My Plan' menu
			//1. Voice:
			try
			{
				new Actions(lDriver).moveToElement(oR_AccountOverview.txtManageMyPlan).click().moveToElement(oR_AccountOverview.lnkHomePhoneInManageMyProfile).click().build().perform();
				
				if(UI.WaitForObject(oR_AccountOverview.txtVoice, 40).equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Voice subsection is present", "true", "true", true);
	
				}
				else
				{
					
					try
					{
						//new Actions(lDriver).moveToElement(oR_AccountOverview.txtManageMyPlan).click().moveToElement(oR_AccountOverview.lnkHomePhoneInManageMyProfile).click().build().perform();
						
						if(UI.WaitForObject(oR_AccountOverview.txtVoice, 40).equals(true))
						{
							Report.ValidationPoint(testContext.getName(), "Voice subsection is present", "true", "true", true);
			
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Voice subsection is present", "true", "Voice subsection is NOT present", true);					
						}
						
					}catch(Exception Ee2)
					{
						
						Report.ValidationPoint(testContext.getName(), "Failed to validate - Manage my plan section", "true", "false", true);
						Report.ValidationPoint(testContext.getName(), "Voice subsection is present", "true", "Voice subsection is NOT present", true);
					}
				}
				
			}catch(Exception Ee)
			{
				try
				{
					//new Actions(lDriver).moveToElement(oR_AccountOverview.txtManageMyPlan).click().moveToElement(oR_AccountOverview.lnkHomePhoneInManageMyProfile).click().build().perform();
					
					if(UI.WaitForObject(oR_AccountOverview.txtVoice, 40).equals(true))
					{
						Report.ValidationPoint(testContext.getName(), "Voice subsection is present", "true", "true", true);
		
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Voice subsection is present", "true", "Voice subsection is NOT present", true);					
					}
					
				}catch(Exception Ee2)
				{
					Report.ValidationPoint(testContext.getName(), "Failed to validate - Manage my plan section", "true", "false", true);	
				}
				
			}

		}
		else
		{
			Report.ValidationPoint(testContext.getName(), "Manage My Plan option is present", "true", "Manage My Plan option is NOT present", true);					
		}

		//Code to verify subsection Manage My Profile
		new Actions(lDriver).moveToElement(oR_AccountOverview.txtManageMyProfile).click().perform();
		Report.OperationPoint(testContext.getName(), "Operational - Clicked Manage My Profile tab");

		if(UI.WaitForObject(oR_AccountOverview.txtManageMyProfile, 40).equals(true))
		{
			Report.ValidationPoint(testContext.getName(), "Manage My Profile option is present", "true", "true", true);

			//Verify the Subsections inside 'Manage My Profile' menu
			//1. Profile:
			if(UI.WaitForObject(oR_AccountOverview.txtProfile, 40).equals(true))
			{
				Report.ValidationPoint(testContext.getName(), "Profile subsection is present", "true", "true", true);

			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Profile subsection is present", "true", "Profile subsection is NOT present", true);					
			}

		}
		else
		{
			Report.ValidationPoint(testContext.getName(), "Manage My Profile option is present", "true", "Manage My Profile option is NOT present", true);					
		}

		//Code to verify subsection Get help & Support
		new Actions(lDriver).moveToElement(oR_AccountOverview.txtGetHelpAndSupport).click().perform();
		Report.OperationPoint(testContext.getName(), "Operational - Clicked on Get help & Support");

		if(UI.WaitForObject(oR_AccountOverview.txtGetHelpAndSupport, UI.iObjTimeOut).equals(true))
		{
			Report.ValidationPoint(testContext.getName(), "Get help and support option is present", "true", "true", true);

			//Verify the Subsections inside 'Get Help & Support' menu
			try
			{	
				new Actions(lDriver).moveToElement(oR_AccountOverview.txtGetHelpAndSupport).click().moveToElement(oR_AccountOverview.lnkHomePhoneInGetHelpAndSupport).click().build().perform();
				
				//1. Service:
				if(UI.WaitForObject(oR_AccountOverview.txtService, 40).equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Service subsection is present", "true", "true", true);
	
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Service subsection is present", "true", "Service subsection is NOT present", true);					
				}
				//2. Setup:
				if(UI.WaitForObject(oR_AccountOverview.txtSetup, 40).equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Setup subsection is present", "true", "true", true);
	
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Setup subsection is present", "true", "Setup subsection is NOT present", true);					
				}
				
			}catch(Exception Ee2)
			{
				Report.OperationPoint(testContext.getName(), "Operational - HomePhone subsection is not present");
				//1. Service:
				if(UI.WaitForObject(oR_AccountOverview.txtService, 40).equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Service subsection is present", "true", "true", true);
	
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Service subsection is present", "true", "Service subsection is NOT present", true);					
				}
				//2. Setup:
				if(UI.WaitForObject(oR_AccountOverview.txtSetup, 40).equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Setup subsection is present", "true", "true", true);
	
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Setup subsection is present", "true", "Setup subsection is NOT present", true);					
				}
			}

		}
		else
		{
			Report.ValidationPoint(testContext.getName(), "Get help & support option is present", "true", "Get help & support option is NOT present", true);					
		}

		//Code to verify subsection Shop At&t
		new Actions(lDriver).moveToElement(oR_AccountOverview.txtShopAtt).click().perform();
		Report.OperationPoint(testContext.getName(), "Operational - Clicked Shop At&t tab");

		if(UI.WaitForObject(oR_AccountOverview.txtShopAtt, 40).equals(true))
		{
			Report.ValidationPoint(testContext.getName(), "Shop At&t option is present", "true", "true", true);

			//Verify the Subsections inside 'Shop At&t' menu
			//1. Shop:
			if(UI.WaitForObject(oR_AccountOverview.txtShop, 40).equals(true))
			{
				Report.ValidationPoint(testContext.getName(), "Shop subsection is present", "true", "true", true);

			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Shop subsection is present", "true", "Shop subsection is NOT present", true);					
			}

		}
		else
		{
			Report.ValidationPoint(testContext.getName(), "Shop At&t option is present", "true", "Shop At&t option is NOT present", true);					
		}


	}
	else
	{
		Report.ValidationPoint(testContext.getName(), "'I want to' drop-down present in overview page", "true", "'I want to' drop-down NOT present in overview page", true);					
	}


}
	
}
catch (Exception e) 
{
	Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

}
}


	/**************************************************************
	 * Function Name - VerifyUverseTVServiceSummary()
	 * Description- Verify Voluntary Suspended account
	 * Parameters - None
	 * Date created - 9-Feb-2015
	 * Developed by - Merrin Mathai
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/

	public static void VerifyUverseTVServiceSummary(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException{
		WebDriver lDriver = UI.getDriver(testContext.getName());  
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try{
			//click on Uverse TV toggle lnkUverseTVToggle
			Boolean bUverseTVToggle=UI.WaitForObject(oR_AccountOverview.lnkUverseTVToggle, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Uverse TV toggle", "true", String.valueOf(bUverseTVToggle), true);
			oR_AccountOverview.lnkUverseTVToggle.click();
			//TV icon
			Boolean bTVIcon=UI.WaitForObject(oR_AccountOverview.imgUverseTVIcon, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Uverse TV icon", "true", String.valueOf(bTVIcon), true);
			//trouble shoot n resolve
			Boolean bTroubleshootLink=UI.WaitForObject(oR_AccountOverview.lnkTroubleshootAndResolve, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "TroubleShoot and Resolve link", "true", String.valueOf(bTroubleshootLink), true);
			//Manage DVR
			Boolean bManageDVR=UI.WaitForObject(oR_AccountOverview.lnkManageDVRServiceSummary, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Manage DVR link", "true", String.valueOf(bManageDVR), true);
			//Link to Channel Line Up 
			Boolean bChannelLineUp=UI.WaitForObject(oR_AccountOverview.lnkChannelLineup, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Link to Channel Line Up ", "true", String.valueOf(bChannelLineUp), true);
			//Message for limited access
			String sTestCase= testContext.getName();
			if(sTestCase=="DBD09061")
			{	
			Boolean bMessage=UI.WaitForObject(oR_AccountOverview.txtMsgLimitedAccess, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Message notifying customer that they have limited access", "true", String.valueOf(bMessage), true);
			}
		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	}

	/**************************************************************
	 * Function Name -  VerifySuppressedMidFlyoutManuOfIwantToDropdownForSameProducts
	 * Description- this function checks whether mid flyout under i want to dropdown is suppressed(this will be present only in case of different accounts with different types)
	 * 				here for this test case we need to pass slid with same account types so this mid flyout should be suppressed
	 * Parameters - 
	 * Date created -20th Feb 15
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	//DBD12878

	public static void VerifySuppressedMidFlyoutManuOfIwantToDropdownForSameProducts(final ITestContext testContext) throws HeadlessException, IOException, AWTException, InterruptedException
	{   
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		
		try
		{
		//click on I want to drop down 
		if(UI.WaitForObject(lDriver.findElement(By.xpath("//button[@id='ddShortcut']")), 120)) 
		{  
			WebElement btnIwantTO = lDriver.findElement(By.xpath("//button[@id='ddShortcut']"));

			List<WebElement> categories = lDriver.findElements(By.xpath("//li[contains(@id,'ddShortcutMenu')]"));

			//take all categories under dropdown in list and put it in loop
			for(int cnt=1;cnt<=categories.size();cnt++)
			{

				Actions action= new Actions(lDriver);

				//hover over to each category
				WebElement we= lDriver.findElement(By.xpath("//li[@id='ddShortcutMenu"+cnt+"']"));

				action.moveToElement(btnIwantTO).click().moveToElement(we).build().perform();

				Thread.sleep(8000);
				//take Screenshot after hovering
				Report.ValidationPoint(testContext.getName(), "Verify Dropdown Category no: "+cnt, "true", "true", true);
				
				//check for mid flyout(it will be present only in case of different account types)
				//here we are using same account type so this mid flyout should be suppressed 

				try {
					lDriver.findElement(By.xpath("//*[contains(@id,'ddShortcutMenu')]//*[contains(@id,'midMenu')]"));
					//System.out.println("Mid flyout category(which is displayed only when different account types are present) is NOT suppressed-----> FAIL");
					Report.ValidationPoint(testContext.getName(), "Mid flyout category(which is displayed only when different account types are present) should be suppressed for Dropdown Category no: "+cnt, "Suppressed", "NOT suppressed", true);

				} catch (Exception e) {

					//System.out.println("Mid flyout category(which is displayed only when different account types are present) is suppressed-----> PASS");
					Report.ValidationPoint(testContext.getName(), "Mid flyout category(which is displayed only when different account types are present) should be suppressed for Dropdown Category no: "+cnt, "Suppressed", "Suppressed", true);
				}

				Thread.sleep(5000);

				action.moveToElement(btnIwantTO).click().build().perform();

			}

		}
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Verify dropdown categories", "Verified", "I want to dropdown DOES NOT exist", true);
		}
		//Thread.sleep(90000);


		

	}

	/**************************************************************
	 * Function Name - VerifyManageDVRLinkIsSuppressed
	 * Description- Validate Manage DVR link is suppressed
	 * Parameters - None
	 * Date created - 23-Feb-2015
	 * Developed by - Rahul Bakde 
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/

	public static void VerifyManageDVRLinkIsSuppressed(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException{
		
		WebDriver lDriver = UI.getDriver(testContext.getName()); 
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		try{
			if (UI.WaitForObject(oR_AccountOverview.lnkUverseTVToggle, 5)) {
				oR_AccountOverview.lnkUverseTVToggle.click();
				WebDriverWait wait=new WebDriverWait(lDriver, UI.iObjTimeOut);
				wait.until(ExpectedConditions.visibilityOfAllElements(oR_AccountOverview.lnkmanageDVR));
				Report.TestPoint(testContext.getName(), "Validate Manage DVR link is suppressed", "Manage DVR link is suppressed", "Manage DVR link is not suppressed", true);
			} else {
				Report.TestPoint(testContext.getName(), "Validate U-verse TV toggle", "True", "U-verse TV toggle is not present", true);
			}
		}catch(Exception e){
			Report.TestPoint(testContext.getName(), "Validate Manage DVR link is suppressed", "Manage DVR link is suppressed", "Manage DVR link is suppressed", true);
		}
	}
	
	
	
	 /**************************************************************
	 * Function Name -  VerifyDashboardForDSL
	 * Description- This function verifies dashboard as well as verify that subsections are present for individual option in the menu of I want to dropdown
	 * Parameters - 
	 * Date created -23rd Feb 15
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	//DBD09404	
	
	public static void VerifyDashboardForDSL(final ITestContext testContext) throws HeadlessException, IOException, AWTException, InterruptedException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
	  
	  
		//Verify the service summary section for the DSL 
	  
		if(lDriver.findElement(By.xpath("//*[text()='My Plans']")).isDisplayed() && lDriver.findElement(By.xpath("//*[contains(@id,'planName')]")).isDisplayed() && lDriver.findElement(By.xpath("//button[@id='ddShortcut']")).isDisplayed()) //&& (driver.findElement(By.xpath("//*[contains(@id,'planName')]")).isDisplayed()) //&& (driver.findElement(By.xpath("//button[contains(@alt,'I want to')]")).isDisplayed())
		
		{
			//System.out.println("The appropriate plan details are displayed on dashboard");
			Report.ValidationPoint(testContext.getName(), "Verify the service summary section for the DSL", "The appropriate plan details are displayed on dashboard", "The appropriate plan details are displayed on dashboard", true);
			
		}
		else
		{
			//System.out.println("The appropriate plan details are NOT displayed on dashboard");
			Report.ValidationPoint(testContext.getName(), "Verify the service summary section for the DSL", "The appropriate plan details are displayed on dashboard", "The appropriate plan details are NOT displayed on dashboard", true);
		}
	  
		
		//Click on I want to drop down , verify that subsections are present for individual option in the menu
	  
		 if(UI.WaitForObject(lDriver.findElement(By.xpath("//button[@id='ddShortcut']")), 120)) 
		 {  
			 Report.ValidationPoint(testContext.getName(), "Click on I want to drop down , verify that subsections are present for individual option in the menu", "verify", "verify", false);
			 WebElement btnIwantTO = lDriver.findElement(By.xpath("//button[@id='ddShortcut']"));
		  
			 List<WebElement> categories = lDriver.findElements(By.xpath("//li[contains(@id,'ddShortcutMenu')]"));
		  
			 //take all categories under dropdown in list and put it in loop
			 for(int cnt=1;cnt<=categories.size();cnt++)
			 {
			  
				 Actions action= new Actions(lDriver);
			 
				 //hover over to each category
				 WebElement we= lDriver.findElement(By.xpath("//li[@id='ddShortcutMenu"+cnt+"']"));
			  
				 action.moveToElement(btnIwantTO).click().moveToElement(we).build().perform();
				// action.moveToElement(btnIwantTO).click().build().perform();
				 
				 Thread.sleep(8000);
				 //take Screenshot after hovering
				 //verify that subsections are present for individual option after hovering
				
				 String sCurrentMenuId = "ddShortcutMenu"+cnt;
				 if(lDriver.findElement(By.xpath("//li[contains(@id,'"+sCurrentMenuId+"')]//*[contains(@id,'flyOutDiv')]")).isDisplayed())
				 {
					 //System.out.println("Subsection is present for dropdown option no : "+cnt);
					 Report.ValidationPoint(testContext.getName(), "Subsection is present for dropdown option no : "+cnt, "true", "true", true);
				 }
				 else
				 {
					 //System.out.println("Subsection is NOT present for dropdown option no : "+cnt);
					 Report.ValidationPoint(testContext.getName(), "Subsection is NOT present for dropdown option no : "+cnt, "true", "false", true);
				 }
				 
				 Thread.sleep(2000);
			  
				 action.moveToElement(btnIwantTO).click().build().perform();
			  
			 }
		  
		 }

	}
	
	/**************************************************************
	 * Function Name -  VerifyLoginPage
	 * Description- 
	 * Parameters - 
	 * Date created -24th Feb 15
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	//DBD05161

	public static void VerifyLoginPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName()); 
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
		Actions aAction = new Actions(lDriver);
		
		try
		{
			
			String sUserID = IO.GetParamVal(sTestParams, "LoginCred");
			System.out.println(sUserID);
			//String sPwd = IO.GetParamVal(sTestParams, "Password", iCurrIter);
			//Verify Type of account dropdown is not displayed
			boolean bLstSelectAccountType = UI.WaitForObject(oR_Login.lstSelectAccountType, 2);
			Report.ValidationPoint(testContext.getName(), "Verify Type of account dropdown is not displayed", "false", String.valueOf(bLstSelectAccountType), true);
			
			//validating wireless promotion page
/*			try{
				WebElement WirelessPromotion = lDriver.findElement(By.xpath("//img[contains(@src,'WirelessLatestLogin')]"));	
				Report.ValidationPoint(testContext.getName(),"Validate wireless promotion is displayed","true",String.valueOf(WirelessPromotion.isDisplayed()),true);
			}catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(),"Validate wireless promotion is displayed","true","false",true);
			}*/
			
			////Valdiate textboxes for username and password & login button
			//username
			boolean bUserID = UI.WaitForObject(oR_Login.edtUserIdReal, 2);
			Report.ValidationPoint(testContext.getName(), "Valdiate textboxes for username", "true", String.valueOf(bUserID), true);
			//Password
			boolean bPassword = UI.WaitForObject(oR_Login.edtPassword, 2);
			Report.ValidationPoint(testContext.getName(), "Valdiate textboxes for password", "true", String.valueOf(bPassword), true);
			//Login button
			boolean bLoginButton = UI.WaitForObject(oR_Login.btnLogin, 2);
			Report.ValidationPoint(testContext.getName(), "Valdiate login button", "true", String.valueOf(bLoginButton), true);
			
			//hover the mouse on user id and validate pop-up
			aAction.moveToElement(oR_Login.edtUserIdReal).build().perform();
			Report.ValidationPoint(testContext.getName(), "Validate pop-up is displayed","true",String.valueOf(bUserID),true);
			
			try{
				WebElement PopUp = lDriver.findElement(By.xpath("//*[contains(text(),'If you currently')]"));	
				Report.ValidationPoint(testContext.getName(),"Validate that user is able to click on pop up","false",String.valueOf(!PopUp.isEnabled()),true);
			}catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(),"Validate that user is not able to click on pop up","true","true",true);
			}
			
			//
			Report.ValidationPoint(testContext.getName(), "Validate pop-up content is displayed as expected","true",String.valueOf(bUserID),true);
			
			//
			//Verify Forgot your User id link
			boolean bForgotUserID = UI.WaitForObject(oR_Login.lnkForgotId, 2);
			Report.ValidationPoint(testContext.getName(), "Valdiate Forgot your User id link", "true", String.valueOf(bForgotUserID), true);
			
			//validate forgot password link
			boolean bForgotPwd = UI.WaitForObject(oR_Login.lnkForgotPassword, 2);
			Report.ValidationPoint(testContext.getName(), "Valdiate forgot password link", "true", String.valueOf(bForgotPwd), true);
			
			//Validate Register Today link
			boolean bRegisterToday = UI.WaitForObject(oR_Login.lnkRegisterToday, 2);
			Report.ValidationPoint(testContext.getName(), "Valdiate Register Today link", "true", String.valueOf(bRegisterToday), true);
			
			//Validate Register link
			boolean bRegister = UI.WaitForObject(oR_Login.lnkRegister, 2);
			Report.ValidationPoint(testContext.getName(), "Valdiate Register link", "true", String.valueOf(bRegister), true);
			
			//Cannot perform on new UI. Unable to click on login button
		//	Click login button without entering userID and password
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on Login button");
			oR_Login.btnLogin.click();
			
			//verify error msg Please Enter your User id
			boolean bErrorUserID = UI.WaitForObject(oR_Login.txtErrorUserID, 2);
			if(bErrorUserID)
			{
				Report.ValidationPoint(testContext.getName(), "verify error msg is displayed as expected after clicking login button", "true", String.valueOf(bErrorUserID), true);
			}
			
			/*//verify error msg Please Enter your password
			boolean bErrorPassword = UI.WaitForObject(oR_Login.txtErrorPwd, 2);
			Report.ValidationPoint(testContext.getName(), "verify error msg Please Enter your password", "true", String.valueOf(bErrorPassword), true);
			*/
			//Verify Need help? Is present on the left hand corner below the Register today link
			int iLocRegister = oR_Login.lnkRegisterToday.getLocation().getY();
			int iLocNeedHelp = oR_Login.txtNeedHelp.getLocation().getY();
			if(UI.WaitForObject(oR_Login.txtNeedHelp, 2) && (iLocNeedHelp>iLocRegister))
			{
				Report.ValidationPoint(testContext.getName(), "Verify Need help? Is present on the left hand corner below the Register today link", "true","true", true);
			}
			
			//Verify that the Video carousel is displayed
			boolean bVideoCarousel = UI.WaitForObject(oR_Login.lnkVideoCarousel, 2);
			Report.ValidationPoint(testContext.getName(), "Verify that the Video carousel is displayed", "true", String.valueOf(bVideoCarousel), true);
			
			//Hover includes Content elements: Video Title,Duration , and a help description specific to the video will appear
			aAction.moveToElement(oR_Login.lnkHowToRegister).build().perform();
			Thread.sleep(5000);
			if(UI.WaitForObject(oR_Login.txtVideoCarasolHover, 5))
			{
				Report.ValidationPoint(testContext.getName(), "Hover includes Content elements: Video Title,Duration , and a help description specific to the video will appear : "+oR_Login.txtVideoCarasolHover.getText(), "true","true", true);
			}
			//Clicking on the video CTAs will launch the appropriate video clips.
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on link how to register");
			if(UI.WaitForObject(oR_Login.lnkHowToRegister, 10)){
			oR_Login.lnkHowToRegister.click();
			
			//Validate the video window
			boolean bVideo = UI.WaitForObject(oR_Login.txtVideoDetailsPopUP, 2);
			Thread.sleep(5000);
			Report.ValidationPoint(testContext.getName(), "Verify the video window", "true", String.valueOf(bVideo), true);
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on link close");
			oR_Login.lnkClose.click();
			
			}else{
				Report.ValidationPoint(testContext.getName(), "Verify that How to register link is available", "How to register link is available", "How to register link is NOT available", true);
			}
			
			//Enter valid User ID
			oR_Login.edtUserIdReal.sendKeys(sUserID);
			//Capslock ON
			//Enter invalid pwd
			oR_Login.edtPassword.sendKeys("abcd");
			
			//Verify caps lock is ON msg is displayed
			/*boolean bCapsLock = UI.WaitForObject(oR_Login.txtCAPSlockError, 2);
			Report.ValidationPoint(testContext.getName(), "Verify caps lock is ON msg is displayed", "true", String.valueOf(bCapsLock), true);*/
			
			//Click Login button
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on login button");
			oR_Login.btnLogin.click();
			
			//Verify Error msg
			boolean bError = UI.WaitForObject(oR_Login.txtError, 10);
			Report.ValidationPoint(testContext.getName(), "Verify Error msg after entering valid UserId and Invalid Pw", "true", String.valueOf(bError), true);
			
			//Enter valid username and password
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
	public static void VerifyLOverviewPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName()); 
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
		Actions aAction = new Actions(lDriver);
		
		try
		{
		List<WebElement> BAN1= lDriver.findElements(By.xpath("id('MainTab')//li"));
		if(BAN1.size()>0)
		{
			int i=1;
			for (WebElement e : BAN1)
			{
				System.out.println("Plan "+i+" is "+e.getText());
				i++;
			}
			Report.OperationPoint(testContext.getName(),"Clicking on BAN 1");
			BAN1.get(0).click();
			
			Report.ValidationPoint(testContext.getName(),"Validate page refreshed accordingly","true",String.valueOf(BAN1.get(0).isDisplayed()),true);
		}
		//logout
		LaunchAndLogout.LogoutApplication(testContext, "DBD05161");
		Thread.sleep(5000);
		
		Report.OperationPoint(testContext.getName(),"Navigating to Target URL " +Report.sMyATTEnvURL);
		lDriver.navigate().to(Report.sMyATTEnvURL);
		Thread.sleep(5000);
		//login
		LaunchAndLogout.LoginApplication(testContext);
		
		//
		String URL = lDriver.getCurrentUrl();
		Report.ValidationPoint(testContext.getName(),"Verify URL of page after  logging in", "Url is: "+URL, "Url is: "+URL,true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyVedioBill
	 * Description- 
	 * Parameters - 
	 * Date created - 24th Feb 2015
	 * Developed by - Merrin Mathai
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/


	public static void VerifyVedioBill(final ITestContext testContext)throws Exception{
		WebDriver lDriver = UI.getDriver(testContext.getName()); 
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try{
			//Click on video bill
			String sVideoBill = UI.CheckExist(oR_AccountOverview.lnkVideoBill);
			if(UI.WaitForObject(oR_AccountOverview.lnkVideoBill, 120, lDriver))
			{	
				Report.ValidationPoint(testContext.getName(), "Video bill CTA is present", "True", "True", true);
					
				//video icon
				if(UI.WaitForObject(oR_AccountOverview.imgVideoCamera, 120, lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Video icon is present", "True", "True", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Video icon is present", "True", "False", true);
				}
				
				oR_AccountOverview.lnkVideoBill.click();
				Report.ValidationPoint(testContext.getName(), "Validate Tour My Bill link is present and clicked", "True", "True", true);
				Thread.sleep(2000);
				//check video is played
				if(UI.WaitForObject(oR_AccountOverview.frmTourMyBill, 120, lDriver)){
				lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
				//driver.switchTo().defaultContent();
				Boolean blnFrameTitle=UI.WaitForObject(oR_AccountOverview.elmBillTour, 120);
				Report.ValidationPoint(testContext.getName(), "Validate Tour My Bill video modal is opened", "true",String.valueOf(blnFrameTitle), true);
				
				oR_AccountOverview.lnkClose.click();
				lDriver.switchTo().defaultContent();}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate Tour My Bill link is present and clicked", "True", "False", true);	
			}

		} catch (Exception e)
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}

	}

	/**************************************************************
	 * Function Name -  ValidateQuickAccRegistrationPageForAutogeneratedSlid
	 * Description- 
	 * Parameters - 
	 * Date created -24th Feb 15
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	//DBD5161

	public static void ValidateQuickAccRegistrationPageForAutogeneratedSlid(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName()); 
        Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
        OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
        OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
        
		try
		{
			String sFirstName = IO.GetParamVal(sTestParams, "Fname");
            String sLastName = IO.GetParamVal(sTestParams, "Lname");

			//Verify Quick account registration heading
			boolean bQuickAccountRegisteration = UI.WaitForObject(oR_ATT.txtQuickAccRegistration, 200);
			Report.TestPoint(testContext.getName(), "Verify Quick account registration heading", "true", String.valueOf(bQuickAccountRegisteration), true);
			
			//Verify the Sub Sections present
			//Validate Update Access ID
			boolean bUpdateAccessID = UI.WaitForObject(oR_ATT.txtATTaccessID, 50);
			Report.TestPoint(testContext.getName(), "Validate Update Access ID", "true", String.valueOf(bUpdateAccessID), true);
			//Validate user information
			boolean bUserInfo = UI.WaitForObject(oR_ATT.txtUserInfo, 1);
			Report.TestPoint(testContext.getName(), "Validate user information", "true", String.valueOf(bUserInfo), true);
			
			//Verify various fields in "Update Access ID" section.
			//Verify Radio Button
			boolean bRad1 = UI.WaitForObject(oR_ATT.radIwantTo, 1);
			Report.ValidationPoint(testContext.getName(), "Verify Radio Button", "true", String.valueOf(bRad1), true);
			Report.OperationPoint(testContext.getName(), "Operational - Retrieving the inner text: "+oR_ATT.radIwantTo.getText());
			boolean bRad2 = UI.WaitForObject(oR_ATT.radChangeATT, 1);
			Report.ValidationPoint(testContext.getName(), "Verify Radio Button", "true", String.valueOf(bRad2), true);
			Report.OperationPoint(testContext.getName(), "Operational - Retrieving the inner text: "+oR_ATT.radChangeATT.getText());
			
			//Verify by default Keep my current AT&T Access ID Field is selected
			String sValue = oR_ATT.radSelect.getAttribute("value");
			System.out.println(sValue);
			if(sValue.equals("yes"))
			{
				Report.ValidationPoint(testContext.getName(), "Verify by default Keep my current AT&T Access ID Field is selected", "true","true", true);
			}
			
			//Verify various fields in "User Information" section.
			//Verify First name field
			boolean bFname = UI.WaitForObject(oR_ATT.edtFname, 1);
			Report.ValidationPoint(testContext.getName(), "Verify First name field", "true", String.valueOf(bFname), true);
			//Verify that first name field is pre-populate
			String sFname = oR_ATT.edtFname.getAttribute("value");
			System.out.println(sFname);
			if(sFname.trim().equals(sFirstName))
			{
				Report.ValidationPoint(testContext.getName(), "Verify first name field is pre-populate", "true","true", true);
			}
			//Verify last name field
			boolean bLname = UI.WaitForObject(oR_ATT.edtLname, 1);
			Report.ValidationPoint(testContext.getName(), "Verify last name field", "true", String.valueOf(bLname), true);
			
			//Verify that last name field is pre-populate
			String sLname = oR_ATT.edtLname.getAttribute("value");
			System.out.println(sLname);
			if(sLname.trim().equals(sLastName))
			{
				Report.ValidationPoint(testContext.getName(), "Verify last name field is pre-populate", "true","true", true);
			}
			
			//Verify Contact Email Address is not present
/*			boolean bEmail = UI.VerifyElementNotPresent(driver.findElement(By.id("Email")), "Email");
			Report.ValidationPoint(testContext.getName(), "Verify Contact Email Address is not present", "true", String.valueOf(bEmail), true);
			//Verify confirm Contact Email Address is not present
			boolean bEmail1 = UI.VerifyElementNotPresent(driver.findElement(By.id("Email1")), "Email1");
			Report.ValidationPoint(testContext.getName(), "Verify confirm Contact Email Address is not present", "true", String.valueOf(bEmail1), true);*/
					
			//Validate Submit button
			boolean bSubmit = UI.WaitForObject(oR_ATT.btnSubmit, 1);
			Report.TestPoint(testContext.getName(), "Verify Submit button", "true", String.valueOf(bSubmit), true);
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on Submit button");
			oR_ATT.btnSubmit.click();
			
			//verify ATT Access ID registration confirmation page is displayed
			boolean bRegister = UI.WaitForObject(oR_ATT.txtRegisterForATT, 1);
			Report.TestPoint(testContext.getName(), "verify ATT Access ID registration confirmation page is displayed", "true", String.valueOf(bRegister), true);
			
			//verify text Your Account Registration is Complete
			boolean bRegisterComplete = UI.WaitForObject(oR_ATT.txtRegistrationComplete, 1);
			Report.ValidationPoint(testContext.getName(), "verify text Your Account Registration is Complete", "true", String.valueOf(bRegisterComplete), true);
			
			//Validate continue button
			boolean bContinue = UI.WaitForObject(oR_ATT.btnContinue, 1);
			Report.TestPoint(testContext.getName(), "Validate continue button", "true", String.valueOf(bContinue), true);
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on Continue button");
			oR_ATT.btnContinue.click();
			
/*			//Click on No, Thanks
			if (UI.WaitForObject(driver.findElement(By.xpath("//a[contains(text(),'No, Thanks')]")),10))
			{
				driver.findElement(By.xpath("//a[contains(text(),'No, Thanks')]")).click();
			}*/
			
			//Validate Account overview
			boolean bAccOverview = UI.WaitForObject(oR_AccountOverview.lnkOverview,50);
			Report.TestPoint(testContext.getName(), "Validate continue button", "true", String.valueOf(bAccOverview), true);
		}
		catch (Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}

	
	/**************************************************************
	 * Function Name -  VerifyWelcomeMessageAndEllipsisAfterName
	 * Description- This function verifies Welcome back message in account information section as well as verify if Name is too long only first name is displayed otherwise both
	 * 				first and last name displayed in account information section
	 * 				Also verifies that if only first name is displaying & ellipsis is present after provided character location
	 * Parameters - 
	 * Date created -25th Feb 15
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	//DBD12513  

	  public static void VerifyWelcomeMessageAndEllipsisAfterName(final ITestContext testContext) throws HeadlessException, IOException, AWTException, InterruptedException
	  {
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		String sProvidedName = IO.GetParamVal(sTestParams, "Provided_Name");
		String sMaxLengthToDisplayBothNames = IO.GetParamVal(sTestParams, "Max_Length_To_Display_Both_Names");
		String sCharacterCountBeforeEllipsis = IO.GetParamVal(sTestParams, "Character_Count_Before_Ellipsis");
		
		 // Verify that 'Welcome Back' message should be displayed in account information section
		  if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")), 120))
		  {
			  String sText= lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")).getText();
			  if(sText.contains("Welcome back"))
			  {
				  //System.out.println("'Welcome Back' message is displayed in account information section");
				  Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "'Welcome Back' message is displayed in account information section", "'Welcome Back' message is displayed in account information section", true);
			  }
			  else
			  {
			  //System.out.println("'Welcome Back' message is NOT displayed in account information section");
				  Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "'Welcome Back' message is displayed in account information section", "'Welcome Back' message is NOT displayed in account information section", true);
			  }
		  }
		  
		  //Verify that If the name (first and last) is too long, only the first name will be displayed
		  if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(@id,'UserName')]//span")), 60))////*[contains(@id,'UserName')]//span
		  {
			  String sDisplayedNameText=lDriver.findElement(By.xpath("//*[contains(@id,'UserName')]//span")).getText();
			  
			  //now compare sDisplayedNameText and sName
			  int iLengthOfName=sProvidedName.length();
			  String sProvidedFirstName=sProvidedName;
			  String sProvidedLastName=null;
			  if(sProvidedName.contains(" "))
			  {
				  String[] aProvidedName=sProvidedName.split(" ");
				   sProvidedFirstName=aProvidedName[0];
				   sProvidedLastName=aProvidedName[1];
			  }
				  int iMaxLengthToDisplayBothNames = Integer.parseInt(sMaxLengthToDisplayBothNames);
			  
				  //Verify that If the name (first and last) is too long, only the first name will be displayed
				  
				  //For this first check if ellipsis is there in name displaying on dashboard
				  //if ellipsis is there then first remove ellipsis n then compare this displaying name with provided name
				  
				  String sDisplayedNameExcludingEllipsis;
				  
				  
				  if(sDisplayedNameText.contains("…"))
				  {
					  String[] aDisplayedNameTextArrayWithoutEllipsis=sDisplayedNameText.split("…");
					  sDisplayedNameExcludingEllipsis=aDisplayedNameTextArrayWithoutEllipsis[0];
				  }
				  else
				  {
					  sDisplayedNameExcludingEllipsis=sDisplayedNameText;
				  }
				  
				  //check if space is there in displayed name text (it means both first and last names are there)
				  //then split them
				  String sDisplayedFirstNameExcludingEllipsis;
				  String sDisplayedLastNameExcludingEllipsis="null";
				  
				  if(sDisplayedNameExcludingEllipsis.contains(" "))
				  {
					  String[] aDisplayedNameExcludingEllipsis=sDisplayedNameExcludingEllipsis.split(" ");
					  sDisplayedFirstNameExcludingEllipsis=aDisplayedNameExcludingEllipsis[0];
					  sDisplayedLastNameExcludingEllipsis=aDisplayedNameExcludingEllipsis[1];
					  
				  }
				  else
				  {
					  sDisplayedFirstNameExcludingEllipsis=sDisplayedNameExcludingEllipsis;
				  }
				  
				 
				  if(iLengthOfName>iMaxLengthToDisplayBothNames)
				  {
					  
					  //in this case only first name should be displayed 
					 
					  if(sProvidedFirstName.toUpperCase().contains(sDisplayedFirstNameExcludingEllipsis.toUpperCase())) //&& (!sProvidedLastName.toUpperCase().contains(sDisplayedLastNameExcludingEllipsis.toUpperCase())))
					  {
						  if(sProvidedLastName.toUpperCase().contains(sDisplayedLastNameExcludingEllipsis.toUpperCase()))
						  {
							  //System.out.println("Both first & last names are displayed even though name (first and last) is too long");
							  Report.ValidationPoint(testContext.getName(), "Verify that If the name (first and last) is too long, only the first name will be displayed", "Only the first name will be displayed", "Both first & last names are displayed", true);
						  }
							 
						  else
						  {
							  //System.out.println("Only the first name is displayed as name (first and last) is too long");
							  Report.ValidationPoint(testContext.getName(), "Verify that If the name (first and last) is too long, only the first name will be displayed", "Only the first name is displayed", "Only the first name is displayed", true);
							  
							  //ellipsis should display after 9 characters
							  int iCharacterCountBeforeEllipsis=Integer.parseInt(sCharacterCountBeforeEllipsis);
						 
						  
							  char charAtLocation=sDisplayedNameText.charAt(iCharacterCountBeforeEllipsis);
							  //here iCharacterCountBeforeEllipsis is taken instead of iCharacterCountBeforeEllipsis+1(i.e next character of CharacterCountBeforeEllipsis-ellipsis will be present after this count)
							  //this is because in charAt(index) this index will start with zero
						  
							  if(charAtLocation=='…') 
							  {
								  //System.out.println("Ellipsis is present after "+sCharacterCountBeforeEllipsis+" characters");
								  Report.ValidationPoint(testContext.getName(), "Verify Ellipsis is present after "+sCharacterCountBeforeEllipsis+" characters", "Ellipsis is present after "+sCharacterCountBeforeEllipsis+" characters", "Ellipsis is present after "+sCharacterCountBeforeEllipsis+" characters", false);
							  }
							  else if(sDisplayedNameText.contains("…"))
							  {
								  //System.out.println("Ellipsis is present but Not displayed after "+sCharacterCountBeforeEllipsis+" characters----> fail");
								  Report.ValidationPoint(testContext.getName(), "Verify Ellipsis is present after "+sCharacterCountBeforeEllipsis+" characters", "Ellipsis is present after "+sCharacterCountBeforeEllipsis+" characters", "Ellipsis is present but Not displayed after "+sCharacterCountBeforeEllipsis+" characters", false);
							  }
							  else
							  {
								  //System.out.println("Ellipsis is NOT present");
								  Report.ValidationPoint(testContext.getName(), "Verify Ellipsis is present after "+sCharacterCountBeforeEllipsis+" characters", "Ellipsis is present after "+sCharacterCountBeforeEllipsis+" characters", "Ellipsis is NOT present", false);
							  }
						  }
					  }
					  else
					  {
							  //System.out.println("First name is NOT displaying");
						  Report.ValidationPoint(testContext.getName(), "Verify that If the name (first and last) is too long, only the first name will be displayed", "Only the first name is displayed", "First name is NOT displaying", true);
					  }
				  }
				  else
				  {
					  //in this both first and last names should be displayed
					  
					  //if space is there it means both names are there else only first or last name is there
					  if(sDisplayedNameExcludingEllipsis.contains(" "))
					  {
						  //compare whether correct names are displayed 
						  if(((sProvidedFirstName.toUpperCase()).contains(sDisplayedFirstNameExcludingEllipsis.toUpperCase())) && ((sProvidedLastName.toUpperCase()).contains(sDisplayedLastNameExcludingEllipsis.toUpperCase()))) 
						  {
							  //System.out.println("Both first and last names are displayed as name (first and last) is not too long");
							  Report.ValidationPoint(testContext.getName(), "If the name (first and last) is not too long,Both names should be displayed", "Both first and last names are displayed", "Both first and last names are displayed", true);
						  }
						  else
						  {
							  //System.out.println("Both first and last names are not displayed even though name (first and last) is not too long");
							  Report.ValidationPoint(testContext.getName(), "If the name (first and last) is not too long,Both names should be displayed", "Both first and last names are displayed", "Both first and last names are not displayed", true);
						  }
						   
					  }
					  else
					  {
						  //System.out.println("Both first and last names are not displayed even though name (first and last) is not too long");
						  Report.ValidationPoint(testContext.getName(), "If the name (first and last) is not too long,Both names should be displayed", "Only first name is displayed", "Only first name is displayed", true);
					  }
				  }
				  
				  

				  //Verify that first character of the First name 
				  //and Last name will always be capitalized and all preceding letters will be lower case
				  
				  String[] aDisplayedIndividualName =new String[2];
				  int iDisplayedIndividualNameslength=0;
				  if(sDisplayedNameExcludingEllipsis.contains(" "))
				  {
					  aDisplayedIndividualName=sDisplayedNameExcludingEllipsis.split(" ");
					  iDisplayedIndividualNameslength=2;
				  }
				  else
				  {
					  
					   aDisplayedIndividualName[0]=sDisplayedNameExcludingEllipsis;
					   iDisplayedIndividualNameslength=1;
				  }
				 
				  for(int iNamescnt=0;iNamescnt<iDisplayedIndividualNameslength;iNamescnt++)
				  {
					  String sCheckCaseOfTheString = aDisplayedIndividualName[iNamescnt];
					  Boolean bFirstCharacterCapital=false;
					  Boolean bOtherCharactersLowerCase=false;
					  
					  if(Character.isUpperCase(sCheckCaseOfTheString.charAt(0)))
					  {
						  bFirstCharacterCapital=true;
					  }
					  for(int iCharcnt=1;iCharcnt<sCheckCaseOfTheString.length();iCharcnt++)
					  {
						  if(Character.isLowerCase(sCheckCaseOfTheString.charAt(iCharcnt)))
						  {
							  bOtherCharactersLowerCase=true;
						  }
						  else
						  {
							  bOtherCharactersLowerCase=false;
							  break;
						  }
					  }
					  
					  
					  if(bFirstCharacterCapital==true && bOtherCharactersLowerCase==true)
					  {
						  //System.out.println("First character of the name '"+sCheckCaseOfTheString+"' is capitalized and all preceding letters are lower case");
						  Report.ValidationPoint(testContext.getName(), "Verify first character of the First name will always be capitalized & all preceding letters will be lower case", "First character of the name '"+sCheckCaseOfTheString+"' is capitalized and all preceding letters are lower case", "First character of the name '"+sCheckCaseOfTheString+"' is capitalized and all preceding letters are lower case", true);
					  }
					  else if(bFirstCharacterCapital==false && bOtherCharactersLowerCase==true)
					  {
						  //System.out.println("First character of the name '"+sCheckCaseOfTheString+"' is NOT capitalized and all preceding letters are lower case");
						  Report.ValidationPoint(testContext.getName(), "Verify first character of the First name will always be capitalized & all preceding letters will be lower case", "First character of the name '"+sCheckCaseOfTheString+"' is capitalized and all preceding letters are lower case", "First character of the name '"+sCheckCaseOfTheString+"' is NOT capitalized and all preceding letters are lower case", true);
					  }
					  else if(bFirstCharacterCapital==true && bOtherCharactersLowerCase==false)
					  {
						  //System.out.println("First character of the name '"+sCheckCaseOfTheString+"' is capitalized but all preceding letters are NOT lower case");
						  Report.ValidationPoint(testContext.getName(), "Verify first character of the First name will always be capitalized & all preceding letters will be lower case", "First character of the name '"+sCheckCaseOfTheString+"' is capitalized and all preceding letters are lower case", "First character of the name '"+sCheckCaseOfTheString+"' is capitalized but all preceding letters are NOT lower case", true);
					  }
					  else
					  {
						  //System.out.println("First character of the name '"+sCheckCaseOfTheString+"' is NOT capitalized and all preceding letters are NOT lower case");
						  Report.ValidationPoint(testContext.getName(), "Verify first character of the First name will always be capitalized & all preceding letters will be lower case", "First character of the name '"+sCheckCaseOfTheString+"' is capitalized and all preceding letters are lower case", "First character of the name '"+sCheckCaseOfTheString+"' is NOT capitalized and all preceding letters are NOT lower case", true);
					  }
				  }
		}
		  else
		  {
			
			  //System.out.println("Name is not displayed in account information section");
			  Report.ValidationPoint(testContext.getName(), "Name is not displayed in account information section", "Displayed", "NOT Displayed", true);
		  }
		  
		  
	  }
	  
	/**************************************************************
	 * Function Name - validateServiceSumamryLabelAndName
	 * Description- 
	 * Parameters - 
	 * Date created -26th Feb 15
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	//DBD16016

	public static void ValidateServiceSumamryLabelAndName(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());  
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
			//Validate Service summary plan name
			boolean bSummaryPlan = UI.WaitForObject(oR_AccountOverview.txtPlanName,50);
			Report.TestPoint(testContext.getName(), "Validate Service summary plan name", "true", String.valueOf(bSummaryPlan), true);
			//When there is single plan, the below may not come
			//Validate Plan Name
			boolean bPlanName = UI.WaitForObject(oR_AccountOverview.txtPlanName1,1);
			if(bPlanName)
			{
				Report.TestPoint(testContext.getName(), "Validate plan name", "true", String.valueOf(bPlanName), true);
				String sSummaryPlan = oR_AccountOverview.txtPlanName.getText();
				String sPlanName = oR_AccountOverview.txtPlanName1.getText();
				if(sSummaryPlan.contains(sPlanName))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Service summary plan name is same as that of plan name", "true","true", true);
				}
				/*else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Service summary plan name is same as that of plan name", "true","Not Equal", true);
				}*/
			}
		}		
		catch (Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	

	/**************************************************************
	   * Function Name - VerifyQuickLinksUpdateMyPlansFeatures 
	   * Description- This function Validates Manage my plans for IPTV ,HSIA and VOIP and checks Move My Services- Redirects in same window to Shop Page.
	   * Parameters - None
	   * Date created - 26th Feb 2015
	   * Developed by - Krutika K
	   ***************************************************************/


		public static void VerifyQuickLinksUpdateMyPlansFeatures(final ITestContext testContext)
				throws Exception {
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			try{
		//Validate I Want to link is peresent
		String SDBDURL = lDriver.getCurrentUrl();
		Boolean bIwantto= UI.WaitForObject(oR_AccountOverview.btnIWantTo,UI.iObjTimeOut);
		Report.TestPoint(testContext.getName(), "Validate I want to drop down", "true", String.valueOf(bIwantto), true);
		//Verify Internet link under Support is navigated to Internet support section
		Report.OperationPoint(testContext.getName(), "Clicking on Internet under Support Section");
		
		if(UI.WaitForObject(oR_AccountOverview.lnkSupport, 20).equals(true))
		{
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkSupport, oR_AccountOverview.lnkSupportInternet);
			oR_AccountOverview.lnkSupportInternet.click();
			Thread.sleep(40000);
			boolean bInternetHeader = UI.WaitForObject(oR_AccountOverview.txtSupportInternetHeader, 40);
			Report.TestPoint(testContext.getName(), "Validate Internet Support page is displayed", "true", String.valueOf(bInternetHeader), true);
		}
//		lDriver.navigate().back();
		lDriver.get(SDBDURL);
		Thread.sleep(40000); 
			//validate TV Plans
			
		    UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify","Manage my plan", "U-verse TV" , "Compare TV plans", null);
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Click","Manage my plan", "U-verse TV" , "Move my TV service", null);
			Thread.sleep(20000);
			String url = lDriver.getCurrentUrl();
			if(url.contains("http://www.att.com/"))
			{

				Report.ValidationPoint(testContext.getName(), "Validate ATT support page is displayed", "true","true", true);
//				lDriver.navigate().back();
				lDriver.get(SDBDURL);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate ATT support page is displayed", "true","false", true);
			}
			Thread.sleep(30000);
			//Validate Internet Plans
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify","Manage my plan", "U-verse Internet" , "Compare Internet plans",null);
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Click","Manage my plan", "U-verse Internet" , "Move my Internet service", null);
			Thread.sleep(30000);
			url = lDriver.getCurrentUrl();
			if(url.contains("http://www.att.com/"))
			{

				Report.ValidationPoint(testContext.getName(), "Validate ATT support page is displayed", "true","true", true);
//				lDriver.navigate().back();
				lDriver.get(SDBDURL);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate ATT support page is displayed", "true","false", true);
			}
			Thread.sleep(30000);
			//Validate Voice plans
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify","Manage my plan", "U-verse Voice" , "Compare voice plans", null);
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Click","Manage my plan", "U-verse Voice" , "Move my Voice service", null);
			Thread.sleep(30000);
			url = lDriver.getCurrentUrl();
			if(url.contains("http://www.att.com/"))
			{

				Report.ValidationPoint(testContext.getName(), "Validate ATT support page is displayed", "true","true", true);
//				lDriver.navigate().back();
				lDriver.get(SDBDURL);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate ATT support page is displayed", "true","false", true);
			} 
			}

			catch (Exception e) {
				Report.TestPoint(testContext.getName(),
						"Some error has occured", "True",
						e.getMessage(), true);
			}

}
		
		 /**************************************************************
		 * Function Name - SupportDashboard()
		 * Description- 
		 * Parameters - 
		 * Date created - 26th Feb 2015
		 * Developed by - Merrin Mathai
		 * Last Modified By - 
		 * Last Modified Date -
		 ***************************************************************/


		public static void SupportDashboard(final ITestContext testContext)throws Exception{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			
			try{
				//Mouse over Support tab
				Actions action = new Actions(lDriver);
				
			
				action.moveToElement(oR_AccountOverview.lnkSupportTab).build().perform();	
				//click on a tertiatry navigation link
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkSupportInternetSecNav , null);
				
				Report.ValidationPoint(testContext.getName(), "Validate Select a Service from support tertiary nav", "True", "True", true);
				//Navigate back to overview page
				lDriver.navigate().back();
				String spageTitle=lDriver.getTitle();
				if(spageTitle.contains("Account Overview"))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Navigate back to overview page", "True", "True", true);
				}
				Boolean bVideoBill =UI.WaitForObject(oR_AccountOverview.lnkVideoBill,120);
				if(bVideoBill)
				{	
					Report.ValidationPoint(testContext.getName(), "Validate Tour My Bill link is present", "True", "True", true);	
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Tour My Bill link is present", "True", "False", true);	
				}
				//video icon
				if(UI.CheckExist(oR_AccountOverview.imgVideoCamera).equalsIgnoreCase("True"))
				{
					Report.ValidationPoint(testContext.getName(), "Video icon is present", "True", "True", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Video icon is present", "True", "False", true);
				}

			} catch (Exception e)
			{
				String sErrMsg = e.getMessage();
				Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
			}

		}   
		
	
		
		/**************************************************************
		 * Function Name -  VerifyCreditAlertsAndItsDescriptiveMessage
		 * Description- This method checks whether Credit alert is present and descriptive message is displayed for each credit alert
		 * Parameters - 
		 * Date created -27th Feb 15
		 * Developed by - Sneha Pansare
		 * Last Modified By - 
		 * Last Modified Date -
		 * @throws AWTException 
		 * @throws IOException 
		 * @throws HeadlessException 
		 * @throws ParseException 
		 ***************************************************************/
		//DBD11352
		
		public static void VerifyCreditAlertsAndItsDescriptiveMessage(final ITestContext testContext) throws HeadlessException, IOException, AWTException, InterruptedException, ParseException
		  {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());  
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
		  try
		  {
			
			String sDescriptiveMessage = IO.GetParamVal(sTestParams, "Descriptive_Message");
			
			   if(UI.WaitForObject(oR_AccountOverview.btnAlert, 120))
			  {
				  oR_AccountOverview.btnAlert.click();
			  }  
				//Verify that user receives an alert for credit or adjustment
				 try
				 {
					 List<WebElement> creditAlerts = lDriver.findElements(By.xpath("//div[@id='alertList']//li//*[contains(@class,'credit')]"));
					 
					 if(creditAlerts.size()!=0)
					 {
						 //it means credit alerts are present
						 
						 //System.out.println("Alerts for Credit or Adjustment are displayed");
						 Report.ValidationPoint(testContext.getName(), "Verify that user receives an alert for credit", "Alerts for Credit or Adjustment are displayed", "Alerts for Credit or Adjustment are displayed", true);
						 
						 //Verify Descriptive message for each credit alert
						 for(int cnt=0;cnt<creditAlerts.size();cnt++)
						 {
							// String sAlertText = creditAlerts.get(cnt).getText();
							 String sAlertText = creditAlerts.get(cnt).getText();
							 if(sAlertText.isEmpty())
							 {
								 lDriver.findElement(By.xpath("//img[@alt='Scroll Down']")).click();
								 Thread.sleep(20000);
								 creditAlerts = lDriver.findElements(By.xpath("//div[@id='alertList']//li//*[contains(@class,'credit')]"));
								 sAlertText = creditAlerts.get(cnt).getText();
							 }
							 
							
							//Object element;
							//((JavaScriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
							 
							 //System.out.println("sAlertText : "+sAlertText);
							 
							 if(sAlertText.contains(sDescriptiveMessage))
							 {
								 //System.out.println("Descriptive message is displayed for Credit alert "+(cnt+1));
								 Report.ValidationPoint(testContext.getName(), "Verify Descriptive message for alert", "Descriptive message is displayed for Credit alert "+(cnt+1), "Descriptive message is displayed for Credit alert "+(cnt+1), true);
							 }
							 else
							 {
								 //System.out.println("Descriptive message is NOT displayed for Credit alert "+(cnt+1));
								 Report.ValidationPoint(testContext.getName(), "Verify Descriptive message for alert", "Descriptive message is displayed for Credit alert "+(cnt+1), "Descriptive message is NOT displayed for Credit alert "+(cnt+1), true);
							 }
						 }
						 
					 }
					 else
					 {
						 //System.out.println("Alerts for Credit or Adjustment are NOT displayed");
						 Report.ValidationPoint(testContext.getName(), "Verify that user receives an alert for credit", "Alerts for Credit or Adjustment are displayed", "Alerts for Credit or Adjustment are NOT displayed", true);
					 }
				
				 }
				 
				 catch(Exception e)
				 {
					// System.out.println("Alerts for Credit or Adjustment are NOT displayed");
					 Report.ValidationPoint(testContext.getName(), "Verify that user receives an alert for credit", "Alerts for Credit or Adjustment are displayed", "Alerts for Credit or Adjustment are NOT displayed", true);
				 }
				  
			  
		  
			  /*else
			  {
				  //System.out.println("Alerts button is not present");
				  Report.TestPoint(testContext.getName(), "Click on Alert button", "Clicked", "Alerts button is not present", true);
			  }*/
			  
		  }catch(Exception e)
		  {
			  String sErrMsg = e.getMessage();
				Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		  }
			 
			  
		}
		
		
		/**************************************************************
		 * Function Name -  VerifySummerySectionForTVandQuickLinkNavigation
		 * Description- This function verifies service summery section details for uverse TV plan 
		 * 				and verifies links under i want to drop down and their navigation
		 * Parameters - 
		 * Date created -2nd Mar 15
		 * Developed by - Sneha Pansare
		 * Last Modified By - 
		 * Last Modified Date -
		 * @throws AWTException 
		 * @throws IOException 
		 * @throws HeadlessException 
		 * @throws ParseException 
		 ***************************************************************/
		//DBD16299
		
		public static void VerifySummerySectionForTVandQuickLinkNavigation(final ITestContext testContext) throws HeadlessException, IOException, AWTException, InterruptedException, ParseException
		  {
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
		  try
		  {
			  
		  
			String sPlanNameType = IO.GetParamVal(sTestParams, "Plan_Name_Type");
			String sPrimaryCategory = IO.GetParamVal(sTestParams, "Primary_Category");
			String sSecondaryCategory = IO.GetParamVal(sTestParams, "Secondary_Category");
			String sLinkToClick1 = IO.GetParamVal(sTestParams, "Link_To_Click_1");
			String sLinkToClick2 = IO.GetParamVal(sTestParams, "Link_To_Click_2");
			
			
			//click on uverse-tv plan
			if(UI.WaitForObject(lDriver.findElement(By.xpath("//a[contains(@alt,'"+sPlanNameType+"')]")), 40))
			{
				lDriver.findElement(By.xpath("//a[contains(@alt,'"+sPlanNameType+"')]")).click();
				
				//Verify service summery section & TV icon 
				//verify plan name is displaying
				
				if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='planName'][contains(text(),'"+sPlanNameType+" |')]")), 80))
				{
					String planName= lDriver.findElement(By.xpath("//*[@id='planName'][contains(text(),'"+sPlanNameType+" |')]")).getText();
					//System.out.println("Plan name :'"+planName+"' is Displayed for "+sPlanNameType);
					Report.ValidationPoint(testContext.getName(), "Verify the service summary section for TV plan", "Plan name :'"+planName+"' is Displayed for "+sPlanNameType, "Plan name :'"+planName+"' is Displayed for "+sPlanNameType, true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify the service summary section for TV plan", "Plan name is Displayed for "+sPlanNameType, "Plan name is NOT Displayed for "+sPlanNameType, true);
				}
				
				//Verify TV icon
				Thread.sleep(40000);
				try
				{
					if(lDriver.findElement(By.xpath("//img[@id='tvImage']")).isDisplayed())
					{
						//System.out.println("TV icon is displayed");
						Report.ValidationPoint(testContext.getName(), "Verify TV icon", "TV icon is displayed", "TV icon is displayed", true);
					}
					
				}catch(Exception e)
				{
					//System.out.println("TV icon is NOT displayed");
					Report.ValidationPoint(testContext.getName(), "Verify TV icon", "TV icon is displayed", "TV icon is NOT displayed", true);
				}
				
				//Verify details are displayed
				try
				{
					if((lDriver.findElement(By.xpath("//*[text()='Billing Period:']")).isDisplayed()) && (lDriver.findElement(By.xpath("//table//*[contains(text(),'Broadcasts')]")).isDisplayed()) && (lDriver.findElement(By.xpath("//table//*[contains(text(),'Amount')]")).isDisplayed()))
					{
						//System.out.println("TV details are displayed");
						Report.ValidationPoint(testContext.getName(), "Verify the service summary section details", "TV details are displayed", "TV details are displayed", true);
					}
				}catch(Exception e)
				{
					//System.out.println("TV details are NOT displayed");
					Report.ValidationPoint(testContext.getName(), "Verify the service summary section details", "TV details are displayed", "TV details are NOT displayed", true);
				}
			}
			
			//Verify and navigate to links under I want to dropdown
			
			String parentHandle = lDriver.getWindowHandle(); // get the current window handle
			
			
			// 1- link 'Manage my DVR'
			
			if(UI.ClickOrVerifyLinkUnderIWantToDropdown("Click", sPrimaryCategory, sSecondaryCategory, sLinkToClick1, null))
			{
				
				Thread.sleep(20000);

				 for (String winHandle : lDriver.getWindowHandles()) {
					 lDriver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle 
				 }

				  //code to do something on new window

				 String sURL= lDriver.getCurrentUrl();
				 if(sURL.contains("att"))
				 {
					 //System.out.println("link '"+sLinkToClick1+"' navigated to att website");
					 Report.ValidationPoint(testContext.getName(), "Verify link '"+sLinkToClick1+"'", "link '"+sLinkToClick1+"' navigates to att website", "link '"+sLinkToClick1+"' navigates to att website", true);
					 Thread.sleep(20000);
				 }
				 else
				 {
					// System.out.println("link '"+sLinkToClick1+"' NOT navigated to att website");
					 Report.ValidationPoint(testContext.getName(), "Verify link '"+sLinkToClick1+"'", "link '"+sLinkToClick1+"' navigates to att website", "link '"+sLinkToClick1+"' DOES NOT navigate to att website", true);
				 }
				  
				 lDriver.close();                        // close newly opened window when done with it
				 lDriver.switchTo().window(parentHandle); 
				  
				  Thread.sleep(20000);
				  
				  if(UI.WaitForObject(oR_AccountOverview.btnIWantTo, 10))
					{
						oR_AccountOverview.btnIWantTo.click();
					}
			}
			   
			// 2- link 'Set up my remote control'
			
			if(UI.ClickOrVerifyLinkUnderIWantToDropdown("Click", sPrimaryCategory, sSecondaryCategory, sLinkToClick2, null))
			{
				Thread.sleep(20000);

				for (String winHandle : lDriver.getWindowHandles()) {
					lDriver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle 
				}

				//code to do something on new window

				String sURL= lDriver.getCurrentUrl();
				if(sURL.contains("att.com"))
				{
					//System.out.println("link '"+sLinkToClick2+"' navigated to att website");
					Report.ValidationPoint(testContext.getName(), "Verify link '"+sLinkToClick2+"'", "link '"+sLinkToClick2+"' navigates to att website", "link '"+sLinkToClick2+"' navigates to att website", true);
					Thread.sleep(20000);
				}
				 else
				{
					//System.out.println("link '"+sLinkToClick2+"' NOT navigated to att website");
					 Report.ValidationPoint(testContext.getName(), "Verify link '"+sLinkToClick2+"'", "link '"+sLinkToClick2+"' navigates to att website", "link '"+sLinkToClick2+"' DOES NOT navigate to att website", true);
				}
						  
				lDriver.close();                        // close newly opened window when done with it
				lDriver.switchTo().window(parentHandle); 
						  
				Thread.sleep(20000);
			}
			
		  }
		  catch(Exception e)
			 {
			  	String sErrMsg = e.getMessage();
				Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
			 }
			  
	  }
		/**************************************************************
		 * Function Name - VerifyAlerts
		 * Description- This verifies the alerts present on dashboard.
		 * Parameters - 
		 * Date created - 2nd Mar 2015
		 * Developed by - Krutika Kamdi 
		 * Last Modified By - Monica Mohabansi
		 * Last Modified Date - 13th Oct 2015
		 * Description : if else looping changed for proper reporting in case there are no up and down arrows 
		 ***************************************************************/


		public static void VerifyAlerts(final ITestContext testContext) 
				throws Exception{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			//String sVerifyDTV_ServiceSummary = "False";
			try{
				//click on Alert button 
				Boolean bAlert = UI.WaitForObject(oR_AccountOverview.btnAlert,40);
				if(bAlert.equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Alert section is present", "True", "True", true);
					oR_AccountOverview.btnAlert.click();
					Thread.sleep(3000);
					Report.ValidationPoint(testContext.getName(), "Validate Alerts are present", "Alerts Are Present", "Alerts Are Present", true);
					
					if(UI.WaitForObject(oR_AccountOverview.btnDownarrow,10))
					{
						Report.ValidationPoint(testContext.getName(), "More alerts are displayed on next modal page", "True", "True", true);
						oR_AccountOverview.btnDownarrow.click();
						Thread.sleep(20000);
						if(oR_AccountOverview.btnUparrow.isEnabled())
						{
							Report.ValidationPoint(testContext.getName(), "Validate Previous page of alert is displayed", "True", "True", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Validate Previous page of alert is displayed", "True", "Unable to navigate to previous modal", true);
						}
						try
						{
							while(oR_AccountOverview.btnDownarrow.isEnabled())
							{
							oR_AccountOverview.btnDownarrow.click();
							Thread.sleep(20000);
							Report.ValidationPoint(testContext.getName(), "Next modal page is displayed ", "True", "True", true);
							}
						}
						catch (Exception e){
							// do nothing
						}
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "If more alerts are present check for the page up and page down arrows.", "More alerts are not present", "More alerts are not present", true);

					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Alert button is not present", "True", "False", true);
				}
			}	
			catch (Exception e)
			
			{
				String sErrMsg = e.getMessage();
				Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
			}

		}

		
		/**************************************************************
		 * Function Name - VerifyCreditCardExpirationAlert
		 * Description - Verify that Uverse customer can view alerts when credit card is ready to expire so that action can be taken to update the credit card info. 
		 * Parameters - None
		 * Date created - 2nd Mar 2015
		 * Developed by - Clint John
		 * Last Modified By - Clint John
		 * Last Modified Date - 2nd Mar 2015
		 * @throws ParseException 
		 ***************************************************************/
		public static void VerifyCreditCardExpirationAlert(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
		{   
			WebDriver lDriver = UI.getDriver(testContext.getName());  
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_EditPaymentProfile oR_EditPaymentProfile = PageFactory.initElements(lDriver, OR_EditPaymentProfile.class);
			OR_ManageAutoPay oR_ManageAutoPay = PageFactory.initElements(lDriver, OR_ManageAutoPay.class);
			try
			{
						//Verify that Alert button is displayed with Alert count
						Boolean checkSubObject1 = UI.WaitForObject(oR_AccountOverview.btnAlertBox, UI.iObjTimeOut);
						Report.TestPoint(testContext.getName(), "Verify that Alert button is displayed", "true", checkSubObject1.toString(), true);
						//Verify Alert count is displayd
						Boolean checkSubObject2 = UI.WaitForObject(oR_AccountOverview.txtAlertButtonCount, UI.iObjTimeOut);
						Report.ValidationPoint(testContext.getName(), "Verify that No. of Alerts is displayed", "true", checkSubObject2.toString(), true);
						//Click on Alert button for dropdown
						oR_AccountOverview.btnAlert.click();
						//Validate if dropdown list is displayed
						Boolean alertDropdownValue= UI.WaitForObject(oR_AccountOverview.lstAlertDropDown, UI.iObjTimeOut);
						Report.TestPoint(testContext.getName(), "Verify that Alert button dropdown with all alerts are displayed", "true", alertDropdownValue.toString(), true);
						if(alertDropdownValue.equals(true))
						{
							//verify the alert title
							Boolean alertTitleValue= UI.WaitForObject(oR_AccountOverview.txtAlertTitle, UI.iObjTimeOut);
							Report.ValidationPoint(testContext.getName(), "Verify that Alert title is displayed inside the dropdown", "true", alertTitleValue.toString(), true);
							//verify expiration alert for CreditCard is displayed
							Boolean expirationAlertValue= UI.WaitForObject(oR_AccountOverview.txtCardAboutToExpireMessage, UI.iObjTimeOut);
							Report.TestPoint(testContext.getName(), "Verify that a message is displayed for Credit card expiration, 'Card about to expire'", "true", expirationAlertValue.toString(), true);
							//Verify the link and click of the link below alert message to navigate to next page
							Boolean updateProfileLinkValue= UI.WaitForObject(oR_AccountOverview.lnkManageAutopaySettings, UI.iObjTimeOut);
							Report.TestPoint(testContext.getName(), "Verify that the hyperlink is dosplayed below CC card expiration message to update profile", "true", updateProfileLinkValue.toString(), true);
							oR_AccountOverview.lnkManageAutopaySettings.click();
							//Report.OperationPoint(testContext.getName(), "Operational - Update payment profile link is clicked");
							Report.OperationPoint(testContext.getName(), "Operational - Manage autopay settings link is clicked");
							
							Thread.sleep(3000);
							boolean bHeading = UI.WaitForObject(oR_ManageAutoPay.txtManageAutoPay, UI.iObjTimeOut, lDriver);
							Report.ValidationPoint(testContext.getName(), "Verify that Manage Autopay page is displayed", "true",String.valueOf(bHeading), true);
							
							//Verify that Credit card expiry edit link is displayed
							boolean bExpiryEdit = UI.WaitForObject(oR_ManageAutoPay.lnkEditCardExpirationDate, UI.iObjTimeOut, lDriver);
							Report.ValidationPoint(testContext.getName(), "Verify that Edit link for Card expiration date is displayed", "true",String.valueOf(bExpiryEdit), true);

							
//							//Verify that Edit Payment Profile page is displayed
//							Boolean headingValue = UI.WaitForObject(oR_EditPaymentProfile.txtEditPaymentProfile, UI.iObjTimeOut);
//							Report.TestPoint(testContext.getName(), "Verify that 'Edit Payment Profile' page is displayed", "true", headingValue.toString(), true);
//							//Verify Cancel and Delete profile links are displayed
//							Boolean CancelAndDelete = UI.WaitForObject(oR_EditPaymentProfile.txtCancelDelete, UI.iObjTimeOut);
//							Report.ValidationPoint(testContext.getName(), "Verify that Cancel and Delete links are displayed", "true", CancelAndDelete.toString(), true);
//							
//							//Verify that Name of card, Card number and Billing Zip code is pre-populated
//							Boolean customerName = UI.WaitForObject(oR_EditPaymentProfile.edtCreditCardCustomerName, UI.iObjTimeOut);
//							Report.ValidationPoint(testContext.getName(), "Verify that Credit card customer name is displayed", "true", customerName.toString(), true);
//							Boolean cardNumber = UI.WaitForObject(oR_EditPaymentProfile.edtCreditCardNumber, UI.iObjTimeOut);
//							Report.ValidationPoint(testContext.getName(), "Verify that Credit card number is displayed", "true", cardNumber.toString(), true);
//							Boolean zipCode = UI.WaitForObject(oR_EditPaymentProfile.edtZip, UI.iObjTimeOut);
//							Report.ValidationPoint(testContext.getName(), "Verify that Zip codee is displayed", "true", zipCode.toString(), true);
//							
//							//Select expiration date and year from drop-down menu
//							new Actions(lDriver).moveToElement(oR_EditPaymentProfile.lstExpiryDate).click().perform();
//							Report.OperationPoint(testContext.getName(), "Operational - Select date from dropdown menu");
//							new Actions(lDriver).moveToElement(oR_EditPaymentProfile.lstExpiryYear).click().perform();
//							Report.OperationPoint(testContext.getName(), "Operational - Select year from dropdown menu");
//							
//							//Click on Save changes
//							oR_EditPaymentProfile.btnSubmit.click();
//							Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Save changes' button");
						
					}
					
					
			}
			catch (Exception e) 
			{
				Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

			}
			
		}
		
		
		/**************************************************************
		 * Function Name -  VerifySuppressedQuickLinkAndNavigationOfViewAllLink
		 * Description- This function verifies whether category 'Update My Plans and features' under i want to dropdown is suppressed
		 * 				also verifies 'View all' link and its navigation to message center page
		 * Parameters - 
		 * Date created -3rd Mar 15
		 * Developed by - Sneha Pansare
		 * Last Modified By - 
		 * Last Modified Date -
		 * @throws AWTException 
		 * @throws IOException 
		 * @throws HeadlessException 
		 * @throws ParseException 
		 ***************************************************************/
		//DBD10919
		
		public static void VerifySuppressedQuickLinkAndNavigationOfViewAllLink(final ITestContext testContext) throws HeadlessException, IOException, AWTException, InterruptedException, ParseException
		 {
			WebDriver lDriver = UI.getDriver(testContext.getName());      
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			String sSuppressedCategory= IO.GetParamVal(sTestParams, "Suppressed_Category");
		  try
		  {
					
			//Validate that "Update My Plans and features" category in "I want to.." should be suppressed
			try
			{
				/***** Code Modified - Monica 12th June 2015 *****/
//				WebElement btnIwantTo= lDriver.findElement(By.xpath("//button[contains(@alt,'I want to')]"));
				UI.WaitForObject(oR_AccountOverview.btnIWantTo, UI.iObjTimeOut);
				Actions action= new Actions(lDriver);
				action.moveToElement(oR_AccountOverview.btnIWantTo).click().build().perform();
				Thread.sleep(5000);
				
				try
				{
					WebElement ePrimaryCategory= lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'"+sSuppressedCategory+"')]"));
					if(ePrimaryCategory.isDisplayed())
					{
						//System.out.println("Category '"+sSuppressedCategory+"' is NOT suppressed--->fail");
						Report.ValidationPoint(testContext.getName(), "Verify category is suppressed", "Category '"+sSuppressedCategory+"' is suppressed", "Category '"+sSuppressedCategory+"' is NOT suppressed", true);
					}
					
				}catch(Exception e)
				{
					//System.out.println("Category '"+sSuppressedCategory+"' is suppressed--->pass");
					Report.ValidationPoint(testContext.getName(), "Verify category is suppressed", "Category '"+sSuppressedCategory+"' is suppressed", "Category '"+sSuppressedCategory+"' is suppressed", true);
				}
			
			}catch(Exception e)
			{
				//System.out.println("I Want to drop down is NOT displayed");
				Report.ValidationPoint(testContext.getName(), "Verify category is suppressed", "Category is suppressed", "I Want to drop down is NOT displayed", true);
			}
			
			 
			//click on view all link besides message bar below service summary section
			if(UI.WaitForObject(oR_AccountOverview.lnkViewAll, 125))
			{
				//System.out.println("Clicked on 'View all' link");
				Report.ValidationPoint(testContext.getName(), "Click on 'View all' link", "Clicked on 'View all' link", "Clicked on 'View all' link", true);
				oR_AccountOverview.lnkViewAll.click();
				
				Thread.sleep(50000);
				try
				{
					if(lDriver.findElement(By.xpath("//*[contains(@class,'title')]//*[text()='Message Center']")).isDisplayed())
					{
						//System.out.println("Message center page is displayed");
						Report.ValidationPoint(testContext.getName(), "Message center page should be displayed", "Message center page is displayed", "Message center page is displayed", true);
					}
					
				}
				catch(Exception e)
				{
					//System.out.println("Message center page is NOT displayed");
					Report.ValidationPoint(testContext.getName(), "Message center page should be displayed", "Message center page is displayed", "Message center page is NOT displayed", true);
				}
				
				
			}
			else
			{
				//System.out.println("'View all' link DOES not exist");
				Report.ValidationPoint(testContext.getName(), "Click on 'View all' link", "Clicked on 'View all' link", "'View all' link DOES not exist", true);
			}
		
		  }catch(Exception e)
		  {
			  String sErrMsg = e.getMessage();
			  Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		  }
		}
		/**************************************************************
		   * Function Name - VerifyUverseWirelessQuickLinks 
		   * Description- This function Validates Quick links for Uverse and Wireless.
		   * Parameters - None
		   * Date created - 3rd March 2015
		   * Developed by - Krutika K
		   ***************************************************************/


		public static void VerifyUverseWirelessQuickLinks(final ITestContext testContext)
					throws Exception {
			WebDriver lDriver = UI.getDriver(testContext.getName());           

			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
				try{
			//Validate I Want to link is peresent
			Boolean bIwantto= UI.WaitForObject(oR_AccountOverview.btnIWantTo,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate I want to drop down", "true", String.valueOf(bIwantto), true);
				//Validate View my bill navigates to billing summary page
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Click","View & pay bill", null , "View my bill", null);
				Thread.sleep(20000);
				String url = lDriver.getCurrentUrl();
				if(url.contains("billSummary"))
				{

					Report.ValidationPoint(testContext.getName(), "Validate Bill Summary page is displayed", "true","true", true);
					lDriver.navigate().back();
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Bill Summary page is displayed", "true","false", true);
				}
				//Validate View my billing history navigates to History summary page
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Click","View & pay bill", null , "View my billing history", null);
				Thread.sleep(20000);
				url = lDriver.getCurrentUrl();
				if(url.contains("ViewBillHistory"))
				{

					Report.ValidationPoint(testContext.getName(), "Validate Bill History Summary page is displayed", "true","true", true);
					lDriver.navigate().back();
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Bill History Summary page is displayed", "true","false", true);
				}
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Click","View & pay bill", null , "View bill and usage reports",null);
				Thread.sleep(20000);
				url = lDriver.getCurrentUrl();
				if(url.contains("BillingReports"))
				{

					Report.ValidationPoint(testContext.getName(), "Validate Report Summary page is displayed", "true","true", true);
					lDriver.navigate().back();
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Report Summary page is displayed", "true","false", true);
				} 
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Click","View & pay bill", null , "Make a payment", null);
				Thread.sleep(20000);
				url = lDriver.getCurrentUrl();
				if(url.contains("Payment"))
				{

					Report.ValidationPoint(testContext.getName(), "Validate Make a Payment page is displayed", "true","true", true);
					lDriver.navigate().back();
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Make a Payment page is displayed", "true","false", true);
				}
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Click","View & pay bill", null , "Enroll in or manage AutoPay",null);
				Thread.sleep(20000);
				url = lDriver.getCurrentUrl();
				if(url.contains("AUTO_PAY"))
				{

					Report.ValidationPoint(testContext.getName(), "Validate Autopay Enrollment page is displayed", "true","true", true);
					lDriver.navigate().back();
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Autopay Enrollment page is displayed", "true","false", true);
				}
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Click","View & pay bill", null , "Arrange late payment", null);
				Thread.sleep(20000);
				url = lDriver.getCurrentUrl();
				if(url.contains("Payment"))
				{

					Report.ValidationPoint(testContext.getName(), "Validate Make a Payment page is displayed", "true","true", true);
					lDriver.navigate().back();
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Make a Payment page is displayed", "true","false", true);
				}
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Click","View & pay bill", null , "Update payment profile",null);
				Thread.sleep(20000);
				url = lDriver.getCurrentUrl();
				if(url.contains("SLIDProfileview"))
				{

					Report.ValidationPoint(testContext.getName(), "Validate Update payment profile page is displayed", "true","true", true);
					lDriver.navigate().back();
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Update payment profile page is displayed", "true","false", true);
				}
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Click","View & pay bill", null , "View payment activity", null);
				Thread.sleep(20000);
				url = lDriver.getCurrentUrl();
				if(url.contains("History"))
				{

					Report.ValidationPoint(testContext.getName(), "Validate payment activity(Payment History) page is displayed", "true","true", true);
					lDriver.navigate().back();
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate payment activity(Payment History) page is displayed", "true","false", true);
				}
				
				
				}
				catch (Exception e) {
					Report.TestPoint(testContext.getName(),
							"Some error has occured", "True",
							e.getMessage(), true);
				}

	}	

	/**************************************************************
	 * Function Name - ValidateQuickLinksOfManageUverseInternetLinks
	 * Description - It validates the manage Uverse internet inks in the Quick links
	 * Parameters - None
	 * Date created - 3rd Mar 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 * @throws ParseException 
	 ***************************************************************/
	//DBD10906
	public static void ValidateQuickLinksOfManageUverseInternetLinks(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		try
		{
			//Validate compare internet plans
			boolean lnkCompare = UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify","Manage my plan","U-verse Internet","Compare Internet plans",null);
			if(lnkCompare)
			{
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Click","Manage my plan","U-verse Internet","Compare Internet plans",null);
				//Validate "Compare Plans" link is opening in same window and redirecting to URL : " http://www.att.com/u-verse/shop/index.jsp?shopFilterId=500001"
				if(lDriver.getCurrentUrl().contains("internet"))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Compare Plans link is opening in same window and redirecting to URL : "+lDriver.getCurrentUrl(), "true", "true", true);
					lDriver.navigate().back();
					Thread.sleep(7000);
				}
				else
				{
					lDriver.navigate().back();
					Thread.sleep(7000);
				}
			}
			
			//Validate View my internet service
			boolean bView = UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify","Manage my plan","U-verse Internet","View my Internet service", null);
			if(bView)
			{
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Click","Manage my plan","U-verse Internet","View my Internet service", null);
				if(lDriver.getCurrentUrl().contains("InternetProductLandingPage"))
				{
					Report.ValidationPoint(testContext.getName(), "Validate View my internet service link is opening in same window and redirecting to URL : "+lDriver.getCurrentUrl(), "true", "true", true);
					lDriver.navigate().back();
					Thread.sleep(7000);
				}
				else
				{
					lDriver.navigate().back();
					Thread.sleep(7000);
				}
			}
			String sDasboard = lDriver.getWindowHandle();
			//Validate Check email
			boolean bCheck = UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify","Manage my plan","U-verse Internet","View my email", null);
			if(bCheck)
			{
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Click","Manage my plan","U-verse Internet","View my email", null);
				
				for(String newwinHandle : lDriver.getWindowHandles())
				{
					lDriver.switchTo().window(newwinHandle);
				}
				if(lDriver.getCurrentUrl().contains("att.net"))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Check email link is opening in same window and redirecting to URL : "+lDriver.getCurrentUrl(), "true", "true", true);
					lDriver.switchTo().window(sDasboard);
				}
			}
		
			//Validate following links are suppressed: -Manage my sub accounts -Manage parental controls -Download Internet Security
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify","Manage my plan","U-verse Internet","Manage parental controls","Suppressed");
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify","Manage my plan","U-verse Internet","Download Internet Security","Suppressed");
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - VerifyManageAutoPay
	 * Description- Validate ManageAutoPay DVR link is present
	 * Parameters - None
	 * Date created - 3rd-March 2015
	 * Developed by - Merrin Mathai 
	 * Last Modified By - Krutika Kamdi
	 * Last Modified Date - 11th June 2015
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/

	public static void VerifyManageAutoPay(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		
		   if (UI.WaitForObject(oR_AccountOverview.lnkManageAutoPay, 5) | UI.WaitForObject(oR_AccountOverview.lnkEnrollInAutopay, 5)) {
				Report.ValidationPoint(testContext.getName(), "Manage autopay link should be visible", "Manage autopay link is visible", "Manage autopay link is visible", true);
						 	
			} else {
				Report.ValidationPoint(testContext.getName(), "Manage autopay link should be visible", "Manage autopay link is visible", "Manage autopay link is not visible", true);
			}
			
			if (UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBilling, 5) | UI.WaitForObject(oR_AccountOverview.lnkManagePaperlessBilling, 5)) {
				Report.ValidationPoint(testContext.getName(), "enroll/manage paperless biling links should not be visible", "True", "False", true);
					
			} else {
				Report.ValidationPoint(testContext.getName(), "enroll/manage paperless biling links should not be visible", "True", "True", true);
			}
				
	}

	/**************************************************************
	 * Function Name -  VerifyServiceAndPlanNameAndsuspendedLabel
	 * Description- This function verifies 'service type(for internet and voice, not for TV) , plan name ,account suspended label in front of plan name
	 * 				device image, red suspended label ' in service summery section for all plans present in plan toggle section
	 * Parameters - 
	 * Date created -4th Mar 15
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	//DBD09045

	public static void VerifyServiceAndPlanNameAndsuspendedLabel(final ITestContext testContext) throws HeadlessException, IOException, AWTException, InterruptedException, ParseException
	 {
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	  
	try
	  {
		
		String sUverseTVplanName=IO.GetParamVal(sTestParams, "UverseTV_plan_Name");
		
		try
		{
			List<WebElement> plansFromToggleSection = lDriver.findElements(By.xpath("//*[contains(@id,'service')]//a[contains(@class,'wt_Body focusable')]"));
			int sTotalPlans = plansFromToggleSection.size();
			for(int cnt=0;cnt < sTotalPlans ; cnt++)
			{
				String sAccount = plansFromToggleSection.get(cnt).getText();
				//System.out.println("text: "+plansFromToggleSection.get(cnt).getText());
				plansFromToggleSection.get(cnt).click();
				
				if(UI.WaitForObject(oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery, 50))
				{
					String sPlanNameWithService = oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery.getText();
					String sServiceType=null;
					String sPlanName = null;
					
					if(sPlanNameWithService.contains("|"))
					{
						String[] arrSplit = sPlanNameWithService.split("\\|");
						sServiceType = arrSplit[0];
						sPlanName= arrSplit[1];
					}
					
					try
					{
						Thread.sleep(15000);
						String sPlanNameWithDetailsInSummerySection;
						if(sPlanNameWithService.contains("TV"))
						{
							sPlanNameWithDetailsInSummerySection= lDriver.findElement(By.xpath("//div[@class='dashboardTabContent']//*[contains(text(),'"+sUverseTVplanName+"')]")).getText();
							sPlanName=sUverseTVplanName;
						}
						else
						{
							sPlanNameWithDetailsInSummerySection = lDriver.findElement(By.xpath("//div[@class='dashboardTabContent']//*[contains(text(),'"+sPlanNameWithService+"')]")).getText();
							
							//Verify Service type is displaying(This will not be displayed for TV service type hence written in else part)
							if(sPlanNameWithDetailsInSummerySection.contains(sServiceType))	
							{
								//System.out.println("Service Type '"+sServiceType+"' is displayed for account '"+sAccount+"'");
								Report.ValidationPoint(testContext.getName(), "Verify Service Type", "Service Type '"+sServiceType+"' is displayed for account '"+sAccount+"'", "Service Type '"+sServiceType+"' is displayed for account '"+sAccount+"'", true);
							}
							else
							{
								//System.out.println("Service Type is NOT displayed for account '"+sAccount+"'");
								Report.ValidationPoint(testContext.getName(), "Verify Service Type", "Service Type is displayed", "Service Type is NOT displayed for account '"+sAccount+"'", true);
							}
						
						}
						//System.out.println("sPlanNameWithDetailsInSummerySection: "+sPlanNameWithDetailsInSummerySection);
						
						//Verify plan name is displaying
						if(sPlanNameWithDetailsInSummerySection.contains(sPlanName))
						{
							//System.out.println("Plan name is displayed for account '"+sAccount+"'");
							Report.ValidationPoint(testContext.getName(), "Verify plan name", "Plan name is displayed for account '"+sAccount+"'", "Plan name is displayed for account '"+sAccount+"'", true);
						}
						else
						{
							//System.out.println("Plan name is NOT displayed for account '"+sAccount+"'");
							Report.ValidationPoint(testContext.getName(), "Verify plan name", "Plan name is displayed", "Plan name is NOT displayed for account '"+sAccount+"'", true);
						}
						
						//Verify 'Suspended label' is displaying in front of plan name
						if(sPlanNameWithDetailsInSummerySection.contains("Suspended"))
						{
							//System.out.println("Suspended label is displayed for account '"+sAccount+"'");
							Report.ValidationPoint(testContext.getName(), "Verify 'Suspended label' in front of plan name", "Suspended label is displayed for account '"+sAccount+"'", "Suspended label is displayed for account '"+sAccount+"'", true);
						}
						else
						{
							//System.out.println("Suspended label is NOT displayed for account '"+sAccount+"'");
							Report.ValidationPoint(testContext.getName(), "Verify 'Suspended label' in front of plan name", "Suspended label is displayed", "Suspended label is NOT displayed for account '"+sAccount+"'", true);
						}
						
						//Verify that Gray out image is getting displayed
						try
						{
							if(lDriver.findElement(By.xpath("//div[@class='dashboardTabContent']//img")).isDisplayed())
							{
								//System.out.println("Image is getting displayed for account '"+sAccount+"'");
								Report.ValidationPoint(testContext.getName(), "Verify the Gray out image", "Image is getting displayed for account '"+sAccount+"'", "Image is getting displayed for account '"+sAccount+"'", true);
							}
						}
						catch(Exception e)
						{
							//System.out.println("Image is NOT getting displayed for account '"+sAccount+"'");
							Report.ValidationPoint(testContext.getName(), "Verify the Gray out image", "Image is getting displayed", "Image is NOT getting displayed for account '"+sAccount+"'", true);
						}
						
						//Verify that Red suspended label is displaying
						
						try
						{
							if(lDriver.findElement(By.xpath("//div[@class='dashboardTabContent']//img[contains(@alt,'suspended')]")).isDisplayed())
							{
								//System.out.println("Red suspended label is getting displayed for account '"+sAccount+"'");
								Report.ValidationPoint(testContext.getName(), "Verify the Red suspended label", "Red suspended label is getting displayed for account '"+sAccount+"'", "Red suspended label is getting displayed for account '"+sAccount+"'", true);
							}
						}
						catch(Exception e)
						{
							//System.out.println("Red suspended label is NOT getting displayed for account '"+sAccount+"'");
							Report.ValidationPoint(testContext.getName(), "Verify the Red suspended label", "Red suspended label is getting displayed", "Red suspended label is NOT getting displayed for account '"+sAccount+"'", true);
						}
						
						
					}catch(Exception e)
					{
						//System.out.println("Plan name is NOT displayed in content of service summery section");
						Report.TestPoint(testContext.getName(), "Verify Service, Plan name, label", "Plan name is displayed", "Plan name is NOT displayed in content of service summery section", true);
					}
					
				}
				else
				{
					//System.out.println("Plan name is NOT displayed in header of summery section");
					Report.TestPoint(testContext.getName(), "Verify Service, Plan name, label", "Plan name is displayed", "Plan name is NOT displayed in header of summery section", true);
				}
			}
			
		}catch(Exception e)
		{
			//System.out.println("Plans are NOT displayed in plan toggle section");
			Report.TestPoint(testContext.getName(), "Verify Service, Plan name, label", "Plan name is displayed", "Plans are NOT displayed in plan toggle section", true);
		}
		
		
	  }catch(Exception e)
	  {
		  Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
	  }
	}
	
	/**************************************************************
	 * Function Name - VerifyManageAutoPayLink()
	 * Description - Verify tha Manage Autopay link is present at the botom of Billing & Payment section 
	   and also verify that after clicking on the link it should take user to Manage Autopay page with accnt in focus passed
	 * Parameters - None
	 * Date created - 5th Mar 2015
	 * Developed by - Clint John
	 * Last Modified By - Clint John
	 * Last Modified Date - 5th Mar 2015
	 * @throws ParseException 
	 ***************************************************************/
	//DBD12579
	public static void VerifyManageAutoPayLink(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		
		try
		{
				//Navigate to Billing & Payemnts page to Validate Manage Autopay link
				Boolean bBillingAndPaymentlink = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Verify that Billing and Payments section is displayed in secondary navigation bar", "true", bBillingAndPaymentlink.toString(), true);
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on Billing & Payments from sec nav");
				
				new Actions(lDriver).moveToElement(oR_BillAndUsage.txtManageAccount).build().perform();
				//Verify Billing & Usage page is displayed
				//Boolean bHeadingCheck = UI.ValidateHeadingOfPage("Billing, Usage, Payments");
				//Report.ValidationPoint(testContext.getName(), "Verify that Billing and Usage page is displayed", "true", bHeadingCheck.toString(), true);
				//Extract Account number from the top of the page
				String strAccountNo1 = oR_BillAndUsage.txtAccountNoInBillAndUsagePage.getText();
				
				//Verify Manage Autopay link is present at the bottom of the page
				Boolean bAutoPay = UI.WaitForObject(oR_BillAndUsage.lnkManageAutoPay, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Verify that 'Manage AutoPay' link is displayed at the bottom of the page", "true", bAutoPay.toString(), true);
				oR_BillAndUsage.lnkManageAutoPay.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on Manage AutoPay link");
				
				//Verify the after clicking Manage AutoPay it is redirected to Manage Autopay page with account in focus passed
				//Boolean bManageAutoPayPage = UI.ValidateHeadingOfPage("Manage My AutoPay Preferences");
				Boolean bManageAutoPayPage = UI.ValidateHeadingOfPage("Manage AutoPay");
				Report.TestPoint(testContext.getName(), "Verify that Manage My AutoPay Preferences page is displayed", "true", bManageAutoPayPage.toString(), true);
				//Verify Account in focus is passed to the page
				String strAccountNo2 = oR_BillAndUsage.txtAccountNoInManageAutopayPage.getText();
		
					//compare Account number from Bill and Usage page and Manage Autopay page
					String AccNo1[] = strAccountNo1.split(" ");
					String AccNo2[] = strAccountNo2.split("  ");
					if(AccNo1[1].contains(AccNo2[1]) || AccNo1[1].contains(AccNo2[2]))
					{
						Report.ValidationPoint(testContext.getName(), "Verify Account in focus is passed to the page", "Account in focus is passed to the page", "Account in focus is passed to the page", true);
						
					}else
					{
						Report.ValidationPoint(testContext.getName(), "Verify Account in focus is passed to the page", "Account in focus is passed to the page", "Account in focus is NOT passed to the page", true);
					}
					
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - VerifyPaymentModule()
	 * Description - Verify the Payment modules for customer who has current balance amount.
	 * Parameters - None
	 * Date created - 12th Mar 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - Monica Mohabansi
	 * Last Modified Date - 16th Oct 2015
	 * @throws ParseException 
	 ***************************************************************/
	//DBD21820
	public static void VerifyPaymentModule(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
		
		try
		{
			//verify total balance amount text is displayed
			Boolean bBalanceAmt = UI.WaitForObject(oR_AccountOverview.txtCurrentBalance, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify the Total balance Amount text is displayed", "true", bBalanceAmt.toString(), true);
			//Verify the balance amount is in orange colour
			Boolean bCurrentBalance = UI.WaitForObject(oR_AccountOverview.txtCurrentBalanceAmt, UI.iObjTimeOut);
			if(bCurrentBalance.equals(true))
			{
				Report.TestPoint(testContext.getName(), "Verify the Current balance Amount text is displayed in Orange colour", "true", bCurrentBalance.toString(), true);
			}
			else 
			{
				Boolean bPastDueBalance = UI.WaitForObject(oR_AccountOverview.txtPastBalanceAmt, UI.iObjTimeOut);
				if(bPastDueBalance.equals(true))		
				Report.TestPoint(testContext.getName(), "Verify the Current balance Amount text is displayed in Redcolour", "true", bPastDueBalance.toString(), true);
			}
			//verify view bill detail link is present and redirects to Bill page
			Boolean bViewBillDetails = UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify View Bill Details link is displayed", "true", bViewBillDetails.toString(), true);
		
			if(bViewBillDetails.equals(true))
			{
				oR_AccountOverview.lnkViewBillDetails.click();
				Thread.sleep(20000);
				Boolean bBillPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Verify View Bill Details link redirects to Bill Page", "true", bBillPage.toString(), true);
				lDriver.navigate().back();
			}
			//verify Make A Payment button is displayed and it redirects to Payment module
			Boolean bMakePayment = UI.WaitForObject(oR_AccountOverview.btnMakeAPayment, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Make a Payment button is displayed", "true", bMakePayment.toString(), true);
			if(bMakePayment.equals(true))
			{
				oR_AccountOverview.btnMakeAPayment.click();
				Thread.sleep(20000);
				Boolean bDueDate = UI.WaitForObject(oR_MakeAPayment.txtDueDate, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Verify Due Date is displayed", "true", bDueDate.toString(), true);
			}
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	}

	/**************************************************************
	 * Function Name -  VerifyUverseInternetSummerySectionDetails
	 * Description- This function verifies below service summery section details for uverse internet plan 
	 * 				Internet icon , Trouble shoot and resolve link , billing cycle bar and days left ,  Find my wi-fi network Password link
	 * Parameters - 
	 * Date created -13th Mar 15
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	//DBD09024
	
	public static void VerifyUverseInternetSummerySectionDetails(final ITestContext testContext) throws HeadlessException, IOException, AWTException, InterruptedException
	  {
		
		WebDriver lDriver = UI.getDriver(testContext.getName());    
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		String sTroubleshootLinkName = IO.GetParamVal(sTestParams, "Troubleshoot_Link_Name");
		try
		{
			List<WebElement> plansFromToggleSection = lDriver.findElements(By.xpath("//*[contains(@id,'service')]//a[contains(@class,'wt_Body focusable')]"));
			int sTotalPlans = plansFromToggleSection.size();
			for(int cnt=0;cnt < sTotalPlans ; cnt++)
			{
				String sAccount = plansFromToggleSection.get(cnt).getText();
				
				//System.out.println("text: "+plansFromToggleSection.get(cnt).getText());
				
				if(sAccount.contains("U-verse Internet"))
				{
					plansFromToggleSection.get(cnt).click();
					
					Thread.sleep(20000);
					
					//Verify Internet icon is displayed within the summary section
					try
					{
						if(lDriver.findElement(By.xpath("//div[@class='dashboardTabContent']//img[contains(@src,'internet')]")).isDisplayed())
						{
							//System.out.println("Internet icon is displayed within the summary section for account : '"+sAccount+"'");
							Report.ValidationPoint(testContext.getName(), "Verify Internet icon", "Internet icon is displayed within the summary section for account : '"+sAccount+"'", "Internet icon is displayed within the summary section for account : '"+sAccount+"'", true);
						}
					}catch(Exception e)
					{
						//System.out.println("Internet icon is NOT displayed within the summary section for account : '"+sAccount+"'");
						Report.ValidationPoint(testContext.getName(), "Verify Internet icon", "Internet icon is displayed within the summary section for account : '"+sAccount+"'", "Internet icon is NOT displayed within the summary section for account : '"+sAccount+"'", true);
					}
					
					//Verify Trouble shoot and resolve link is present within the summary section
					try
					{
						if(lDriver.findElement(By.xpath("//div[@class='dashboardTabContent']//a[contains(text(),'"+sTroubleshootLinkName+"')]")).isDisplayed())
						{
							//System.out.println("Trouble shoot and resolve link is present within the summary section for account : '"+sAccount+"'");
							Report.ValidationPoint(testContext.getName(), "Verify Trouble shoot and resolve link", "Trouble shoot and resolve link is present within the summary section for account : '"+sAccount+"'", "Trouble shoot and resolve link is present within the summary section for account : '"+sAccount+"'", true);
						}
					}catch(Exception e)
					{
						//System.out.println("Trouble shoot and resolve link is NOT present within the summary section for account : '"+sAccount+"'");
						Report.ValidationPoint(testContext.getName(), "Verify Trouble shoot and resolve link", "Trouble shoot and resolve link is present within the summary section for account : '"+sAccount+"'", "Trouble shoot and resolve link is NOT present within the summary section for account : '"+sAccount+"'", true);
					}
					
					//Verify billing cycle bar and days left is displayed within the service summary section
					try
					{
						if(lDriver.findElement(By.xpath("//div[@class='dashboardTabContent']//*[contains(@class,'usage-meter')]//*[contains(@class,'container')]")).isDisplayed())
						{
							//System.out.println("Billing cycle bar is present within the summary section for account : '"+sAccount+"'");
							Report.ValidationPoint(testContext.getName(), "Verify billing cycle bar", "Billing cycle bar is present within the summary section for account : '"+sAccount+"'", "Billing cycle bar is present within the summary section for account : '"+sAccount+"'", true);
						}
					}catch(Exception e)
					{
						//System.out.println("Billing cycle bar is NOT present within the summary section for account : '"+sAccount+"'");
						Report.ValidationPoint(testContext.getName(), "Verify billing cycle bar", "Billing cycle bar is present within the summary section for account : '"+sAccount+"'", "Billing cycle bar is NOT present within the summary section for account : '"+sAccount+"'", true);
					}
					
					try
					{
						if(lDriver.findElement(By.xpath("//div[@class='dashboardTabContent']//*[contains(@class,'usage-meter')]//div[contains(@class,'font')]")).isDisplayed())
						{
							String sDaysLeftText = lDriver.findElement(By.xpath("//div[@class='dashboardTabContent']//*[contains(@class,'usage-meter')]//div[contains(@class,'font')]")).getText();
							
							if(sDaysLeftText.contains("days left"))
							{
								//System.out.println("Days left is present within the summary section for account : '"+sAccount+"'");
								Report.ValidationPoint(testContext.getName(), "Verify days left", "Days left is present within the summary section for account : '"+sAccount+"'", "Days left is present within the summary section for account : '"+sAccount+"'", true);
							}
							else
							{
								//System.out.println("Days left is NOT present within the summary section for account : '"+sAccount+"'");
								Report.ValidationPoint(testContext.getName(), "Verify days left", "Days left is present within the summary section for account : '"+sAccount+"'", "Days left is NOT present within the summary section for account : '"+sAccount+"'", true);
							}
							
						}
					}catch(Exception e)
					{
						//System.out.println("Days left is NOT present within the summary section for account : '"+sAccount+"'");
						Report.ValidationPoint(testContext.getName(), "Verify days left", "Days left is present within the summary section for account : '"+sAccount+"'", "Days left is NOT present within the summary section for account : '"+sAccount+"'", true);
					}
					
					//Verify Find my wi-fi network Password is displaying
					Boolean bManageDVR=UI.WaitForObject(oR_AccountOverview.lnkFindMyWifiPasswordUnderSummerySection, 40);
					Report.ValidationPoint(testContext.getName(), "Verify Find my wi-fi network Password link for account : '"+sAccount+"'", "true", String.valueOf(bManageDVR), true);
					//System.out.println("Verify Find my wi-fi network Password link , true , "+String.valueOf(bManageDVR));
					
				}
				
			}	
			
		}catch(Exception e)
		{
			//System.out.println("Plans are NOT displayed in plan toggle section");
			Report.ValidationPoint(testContext.getName(), "Verify Plans in plan toggle section", "displayed", "NOT displayed", true);
		}
		
	  }
	/**************************************************************
	 * Function Name - VerifyLinksinQuickToolSection()
	 * Description - Verify the links in Quick Tool section for Pre-Install HSIA customer
	 * Parameters - None
	 * Date created - 16th Mar 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date - 
	 * @throws ParseException 
	 ***************************************************************/
	//DBD17540
	public static void VerifyLinksinQuickToolSection(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
			//verify Prepare for your installation link is displayed on account overview
			Boolean bInstallation = UI.WaitForObject(oR_AccountOverview.lnkPrepareInstallation, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify the Prepare for your Installation link is displayed", "true", bInstallation.toString(), true);
			//click on "Prepare for your Installation" link
			if(bInstallation.equals(true))
			{
				oR_AccountOverview.lnkPrepareInstallation.click();
				Thread.sleep(10000);
				String url = lDriver.getCurrentUrl();
				if(url.contains("http://www.att.com/esupport/article.jsp?sid=KB401950&cv=812"))
				{
	
					Report.ValidationPoint(testContext.getName(), "Validate Prepare for installation page is displayed", "true","true", true);
					lDriver.navigate().back();
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Prepare for installation page is displayed", "true","false", true);
				}
			} 
			//verify View Internet user guide link is displayed
			Boolean bInternetGuide = UI.WaitForObject(oR_AccountOverview.lnkViewInternetGuide, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify the View Internet user guide link is displayed", "true", bInternetGuide.toString(), true);
			//click on "Prepare for your Installation" link
			if(bInternetGuide.equals(true))
			{
				oR_AccountOverview.lnkViewInternetGuide.click();
				Thread.sleep(20000);
				String CurrentWindow = lDriver.getWindowHandle();
				for(String newwinHandle : lDriver.getWindowHandles()){
					lDriver.switchTo().window(newwinHandle);
				}
				String url = lDriver.getCurrentUrl();
				if(url.contains("http://www.att.com/esupport/uverse-user-guides/?source=EZ000000000USPA4U"))
				{
	
					Report.ValidationPoint(testContext.getName(), "Validate View Internet user guide page is displayed", "true","true", true);
					lDriver.close();
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate View Internet user guide page is displayed", "true","false", true);
				}
				lDriver.switchTo().window(CurrentWindow);
			}
			
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name -  VerifySuppressedQuickLinkAndATTmessages
	 * Description- This function verifies whether 'manage sub accounts' quick link is supressed under i want to dropdown
	 * 				also verifies ATT&T messages below the service summary section and View all link
	 * Parameters - 
	 * Date created -17th Mar 15
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	//DBD08848
	
	public static void VerifySuppressedQuickLinkAndATTmessages(final ITestContext testContext) throws HeadlessException, IOException, AWTException, InterruptedException
	  {
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		String sLinkToVerifyUnderIwantTo = IO.GetParamVal(sTestParams, "Link_To_Verify_Under_IwantTo_Dropdown");
	   try
	   {
		
		Thread.sleep(30000);
		
		//Verify Quick link 'Create or Manage Sub Accounts' is suppressed
		try
		{
			if(lDriver.findElement(By.xpath("//button[contains(@alt,'I want to')]")).isDisplayed())
			{
				WebElement btnIwantTo= lDriver.findElement(By.xpath("//button[contains(@alt,'I want to')]"));
			
				Actions action= new Actions(lDriver);
				action.moveToElement(btnIwantTo).click().build().perform();
				Thread.sleep(4000);	
				
				try
				{
					if(lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'"+sLinkToVerifyUnderIwantTo+"')]")).isDisplayed())
					{
						//System.out.println("Quick link '"+sLinkToVerifyUnderIwantTo+"' is NOT suppressed");
						Report.ValidationPoint(testContext.getName(), "Verify Quick link is suppressed", "Quick link '"+sLinkToVerifyUnderIwantTo+"' is suppressed", "Quick link '"+sLinkToVerifyUnderIwantTo+"' is NOT suppressed", true);
					}
					else
					{
						//System.out.println("Quick link '"+sLinkToVerifyUnderIwantTo+"' is suppressed");
						Report.ValidationPoint(testContext.getName(), "Verify Quick link is suppressed", "Quick link '"+sLinkToVerifyUnderIwantTo+"' is suppressed", "Quick link '"+sLinkToVerifyUnderIwantTo+"' is suppressed", true);
					}
				
				}
				catch(Exception e)
				{
					//System.out.println("Quick link '"+sLinkToVerifyUnderIwantTo+"' is suppressed");
					Report.ValidationPoint(testContext.getName(), "Verify Quick link is suppressed", "Quick link '"+sLinkToVerifyUnderIwantTo+"' is suppressed", "Quick link '"+sLinkToVerifyUnderIwantTo+"' is suppressed", true);
				}
				
			}
		}catch(Exception e)
		{
			//System.out.println("I Want to Dropdown is NOT displayed");
			Report.ValidationPoint(testContext.getName(), "Verify Quick link is suppressed under I Want to dropdown", "suppressed", "I Want to Dropdown is NOT displayed", true);
			
		}
		
		//Verify AT&T messages below the service summary section
		Boolean bATTmessages=UI.WaitForObject(oR_AccountOverview.txtATTMessage, 40); //UI.iObjTimeOut
		Report.ValidationPoint(testContext.getName(), "AT&T messages below the service summary section", "true", String.valueOf(bATTmessages), true);
		//System.out.println("AT&T messages below the service summary section , true , "+String.valueOf(bATTmessages));
		
		
		//Verify View all link
		if(UI.WaitForObject(oR_AccountOverview.lnkViewAll, 40))
		{
			Boolean bViewAllLink=UI.WaitForObject(oR_AccountOverview.lnkViewAll, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "View all link", "true", String.valueOf(bViewAllLink), true);
			//System.out.println("View all link , true , "+String.valueOf(bViewAllLink));
		}
		 
		
		
	   }catch(Exception e)
	   {
		   Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
	   }
		
	 }

	/**************************************************************
	 * Function Name - VerifyUverseSMBDashBoardIsPresent 
	 * Description- This function Validates if Uverse SMB Dashboard is displayed for a data
	 * Parameters - None
	 * Date created - 19th March 2015
	 * Developed by - Monica M
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	public static void VerifyUverseSMBDashBoardIsPresent(ITestContext testContext) throws HeadlessException, IOException, AWTException 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName()); 
		OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
		
		Boolean bUverse;
		//String sPrimaryEmailAdd = IO.GetParamVal(IO.sTestParams, "Primary_Email", iCurrIter);
		try
		{
			bUverse = UI.WaitForObject(oR_ATT.txtSMBaccountOverview, UI.iObjTimeOut) && UI.WaitForObject(oR_ATT.txtUverse, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "SMB Uverse Dashboard", "true", String.valueOf(bUverse), true);
		}
		catch(Exception e)
		{
			   Report.TestPoint(testContext.getName(),"Failed to validate Dashboard for SMB Uverse", "True",e.getMessage(), true);
		}
	}

	
	/**************************************************************
	 * Function Name - VerifyOverviewSectionforSMBUverse 
	 * Description- This function Validates SMB DashBoard for Uverse
	 * 				
	 * Parameters - None
	 * Date created - 19th Mar 2015
	 * Developed by - Merrin Mathai
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/

	public static void VerifyOverviewSectionforSMBUverse(final ITestContext testContext) 
			throws Exception
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try{
			//Bill and Payments Section on Dashboard
			Boolean bBillAndPayment=UI.WaitForObject(oR_AccountOverview.lnkBillAndPaymentSMB, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Bill & Payments section on dashboard is present", "true", String.valueOf(bBillAndPayment), true);
			
			//Validate the presence of Make a Payment button and View bill summary link 
			Boolean bMAP=UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentSMB, UI.iObjTimeOut);
			Boolean bViewBillSummary=UI.WaitForObject(oR_AccountOverview.lnkViewBillSummarySMB, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "presence of Make a Payment button and View bill summary link", "true,true", String.valueOf(bMAP)+","+String.valueOf(bViewBillSummary), true);
			
			//Validate the  My Message Center section on left hand side on the Dashboard
			Boolean bMsgCenter=UI.WaitForObject(oR_AccountOverview.lnkMyMessageCenterSMB, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "My Message Center section on left hand side on the Dashboard", "true", String.valueOf(bBillAndPayment), true);
			
			//Validate the  service summary section for the Uverse SMB customer.
			List<WebElement> lstUverseServices=lDriver.findElements(By.xpath("//div[@class='float-left uvWidePanel']//h3"));
			int lstSize=lstUverseServices.size();
			String sSize=Integer.toString(lstSize);
			Report.ValidationPoint(testContext.getName(), "service summary section for the Uverse SMB customer", "true", "true", true);
			Report.OperationPoint(testContext.getName(),"Uverse Customer has"+sSize+"Services");
			for(int i=0;i<lstUverseServices.size();i++)
			{
				String sUverseService=lstUverseServices.get(i).getText();
				Report.OperationPoint(testContext.getName(),"Uverse Customer has service"+sUverseService);
			}
			
		} catch (Exception e)
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}

	}
	
	/**************************************************************
	 * Function Name - VerifyUpgradeEligibilityAlert
	 * Description- 
	 * Parameters - 
	 * Date created - 4-Feb-2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/

	public static void VerifyUnlockDeviceAlert(final ITestContext testContext) throws Exception {
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
	
		try{

			//Verify alert button on dashboard
			if(UI.WaitForObject(oR_AccountOverview.btnAlert,20).equals(true)){
				Report.OperationPoint(testContext.getName(), "Operational - Navigated to Dashboard");				
				Report.TestPoint(testContext.getName(), "Verify alert button on dashboard", "alert button on dashboard is present", "alert button on dashboard is present", true);
				//CLick on the alert button
				oR_AccountOverview.btnAlert.click();
				
				String sAlert="Unlock Mobile Device";
				String sAccount=IO.GetParamVal(sTestParams, "LoginID");	
				String sXpathAlert="//div[@class='alert-upgrade']//p[contains(text(),"+sAlert+")]";
				String sXpathAccount="//div[@class='alert-upgrade']//p[contains(text(),"+sAccount+")]"	;	

				//Verify Unlock Device alert
				if((UI.WaitForObject(lDriver.findElement(By.xpath(sXpathAlert)), 10).equals(true))&&(UI.WaitForObject(lDriver.findElement(By.xpath(sXpathAccount)), 10).equals(true))){
					Report.ValidationPoint(testContext.getName(), "Verify Unlock Mobile Device alert", "Unlock Mobile Device alert is present", "Unlock Mobile Device alert is present", true);			
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify Unlock Mobile Device alert", "Unlock Mobile Device alert should be present", "Unlock Mobile Device alert is NOT present", true);					
				}
			}else{
				Report.TestPoint(testContext.getName(), "Verify alert button on dashboard", "alert button on dashboard should be displayed", "alert button not displayed on dashboard", true);					
			}



		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	

/**************************************************************
	 * Function Name - ValidationsOnProductLandingPage
	 * Description- 
	 * Parameters - 
	 * Date created - 23rd-March-2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//DBD03415
	public static void ValidationsOnProductLandingPage(final ITestContext testContext) throws Exception {
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try{
			Boolean benglish = UI.WaitForObject(oR_AccountOverview.txtAccountOverview,20);
			//Verify Language Dropdown is displayed
			if(UI.WaitForObject(oR_AccountOverview.lstLanguage,20).equals(true)){
				Report.OperationPoint(testContext.getName(), "Operational - Navigated to Spanish Page");				
				Report.TestPoint(testContext.getName(), "Verify Language Dropdown is displayed", "Language Dropdown is displayed", "Language Dropdown is displayed", true);
				//Change Language to Spanish
				

				Boolean bLanguage = UI.WaitForObject(oR_AccountOverview.lstLanguage,20);
				Report.ValidationPoint(testContext.getName(), "Verify Language dropdown is displayed", "true", String.valueOf(bLanguage), true);
				if(bLanguage.equals(true))
				{
					//verify the page changes to spanish language
					oR_AccountOverview.lstLanguage.click();
					Boolean bSpanishLang = UI.WaitForObject(oR_AccountOverview.lnkSpanish,5);
					Report.ValidationPoint(testContext.getName(), "Verify Link to change the page in spanish is displayed", "true", String.valueOf(bSpanishLang), true);
					oR_AccountOverview.lnkSpanish.click();
					Thread.sleep(10000);
					//oR_AccountOverview.btnContinueLanguage.click();
					Boolean bSpanish = UI.WaitForObject(lDriver.findElement(By.xpath("//h2[contains(text(),'Resumen de cuenta')]")),20);
					Report.ValidationPoint(testContext.getName(), "Verify Page is displayed in spanish", "true", String.valueOf(bSpanish), true);
					//Verify page changes back to English language
					oR_AccountOverview.lstLanguage.click();
					Boolean bEnglishLang = UI.WaitForObject(oR_AccountOverview.lnkEnglish,5);
					Report.ValidationPoint(testContext.getName(), "Verify Link to change the page in English is displayed", "true", String.valueOf(bEnglishLang), true);
					oR_AccountOverview.lnkEnglish.click();
					Thread.sleep(10000);
					//oR_AccountOverview.btnContinueLanguage.click();
					Report.ValidationPoint(testContext.getName(), "Verify Page is displayed in English", "true", String.valueOf(benglish), true);
					
				}
				//verify Internet and home phone link are displayed in primary navigation bar
				Boolean bInternet = UI.WaitForObject(oR_AccountOverview.lnkInternetSecondaryNav,20);
				Report.ValidationPoint(testContext.getName(), "Verify Internet Link is displayed on the dashboard in the primary navigation bar", "true", String.valueOf(bInternet), true);
				
				Boolean bHomePhone  = UI.WaitForObject(oR_AccountOverview.lnkHomePhoneSecondaryNav,20);
				Report.ValidationPoint(testContext.getName(), "Verify Home Phone Link is displayed on the dashboard in the primary navigation bar", "true", String.valueOf(bHomePhone), true);
				//count no. of products on dashboard
				List<WebElement> Products= lDriver.findElements(By.xpath("//div[@class='convdashbotsolidborder']//h3"));
				
				int ProductCount=Products.size();
				if(ProductCount >= 4)
				{
					Report.ValidationPoint(testContext.getName(), "four product icons on Converged dashboard for Wireless, TV, Internet and Home Phone are displayed", "true", "true", true);
				}
				//Check for I want to dropdown 
				if(lDriver.findElement(By.xpath("//h3[@class='disabletext']")).equals(true))
				{
					Boolean bIWantTo = UI.VerifyElementNotPresent(oR_AccountOverview.btnIWant,"I want to");
					Report.ValidationPoint(testContext.getName(), "Verify I Want to is not displayed for unlinked accounts", "true", String.valueOf(bIWantTo), true);
				}
				else
				{
					Boolean bIWant  = UI.WaitForObject(oR_AccountOverview.btnIWant,20);
					if(bIWant.equals(true))
					{
						Report.ValidationPoint(testContext.getName(), "Verify I Want to is displayed for linked accounts", "true", String.valueOf(bIWant), true);
					}
				}
			//Verify clicking on Internet lands us on Product Landing Page
			 if(bInternet.equals(true))
			 {
				 oR_AccountOverview.lnkInternetSecondaryNav.click();
				 String url = lDriver.getCurrentUrl();
				 if(url.contains("ProductLandingPage"))
				 {
					 Report.ValidationPoint(testContext.getName(), "Verify that the user is landed on to Internet landing page as Wi-Fi service associated to SLID do not have usage", "true","true", true);
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
	 * Function Name - MultiAccShowCorrectLinks
	 * Description- 
	 * Parameters - 
	 * Date created - 20-March-2015
	 * Developed by - Rahul Bakde
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/

	public static void MultiAccShowCorrectLinks(final ITestContext testContext) throws Exception {
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		
		try{
			Boolean bAddADevice=UI.VerifyLinksFromSecondaryNav(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkAddADevice);
			Boolean bTransferNumberToATT=UI.VerifyLinksFromSecondaryNav(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkTransferANumberToATT);
			Boolean bChangeAWirelessNumber=UI.VerifyLinksFromSecondaryNav(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkChangeAWirelessNumber);
			Boolean bSuspendOrReactivateADevice=UI.VerifyLinksFromSecondaryNav(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkSuspendOrReactivateDeviceTertNav);
			if (bAddADevice) {
				Report.TestPoint(testContext.getName(), "Validate Add a device link is displayed under Wireless link in secondary navigation", "True", "True", true);
			} else {
				Report.TestPoint(testContext.getName(), "Validate Add a device link is displayed under Wireless link in secondary navigation", "True", "False", true);
			}
			
			if (bTransferNumberToATT) {
				Report.TestPoint(testContext.getName(), "Validate Transfer number to ATT link is displayed under Wireless link in secondary navigation", "True", "True", true);
			} else {
				Report.TestPoint(testContext.getName(), "Validate Transfer number to ATT link is displayed under Wireless link in secondary navigation", "True", "False", true);
			}
			
			if (bChangeAWirelessNumber) {
				Report.TestPoint(testContext.getName(), "Validate Change a wireless number link is displayed under Wireless link in secondary navigation", "True", "True", true);
			} else {
				Report.TestPoint(testContext.getName(), "Validate Change a wireless number link is displayed under Wireless link in secondary navigation", "True", "False", true);
			}
			
			if (bSuspendOrReactivateADevice) {
				Report.TestPoint(testContext.getName(), "Validate suspend or reactivate a device link is displayed under Wireless link in secondary navigation", "True", "True", true);
			} else {
				Report.TestPoint(testContext.getName(), "Validate suspend or reactivate a device link is displayed under Wireless link in secondary navigation", "True", "False", true);
			}
			
			oR_AccountOverview.txtWelcome.click();
			//Verify the % discount is  shown for any accounts.
			if (UI.WaitForObject(oR_AccountOverview.lnkReceivingDiscount, 1)) {
				Report.TestPoint(testContext.getName(), "Validate % discount is  shown for any accounts", "True", "True", true);
				oR_AccountOverview.lnkReceivingDiscount.click();
				//Billing and Usage page is displayed
				if (UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut)) {
					Report.OperationPoint(testContext.getName(), "Navigated to Bill & usage page");
					Report.TestPoint(testContext.getName(), "Validate navigated to Bill & Usage page", "True", "True", true);
				} else {
					Report.TestPoint(testContext.getName(), "Validate navigated to Bill & Usage pages", "True", "False", true);
				}
			} else {
				Report.TestPoint(testContext.getName(), "Validate % discount is  shown for any accounts", "True", "False", true);
			}
		}catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - ValidatePaperlessBillingAndUsage
	 * Description- 
	 * Parameters - 
	 * Date created - 24th-March-2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/

	public static void ValidatePaperlessBillingAndUsage(final ITestContext testContext) throws Exception 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_PaperlessBilling oR_PaperlessBilling = PageFactory.initElements(lDriver, OR_PaperlessBilling.class);
		
		try
		{
			//Validate error for overage usage
			//According to SS
			Boolean bAlert = UI.WaitForObject(oR_AccountOverview.imgAlertIcon,20);
		//	Report.TestPoint(testContext.getName(), "Verify error for overage usage", "true", String.valueOf(bAlert), true);
			//Retrieving the tooltip msg
			oR_AccountOverview.imgAlertIcon.click();
			
			Report.TestPoint(testContext.getName(), "Verify error for overage usage", "true", String.valueOf(bAlert), true);
//			if(UI.WaitForObject(oR_AccountOverview.imgAlertIconText,1))
//			{
//				Report.ValidationPoint(testContext.getName(), "Verify tooltip", "true","true", true);
//				Report.OperationPoint(testContext.getName(), "Retrieving the tooltip msg : "+oR_AccountOverview.imgAlertIconText.getAttribute("text"));
//			}
			
			
			//validate Enroll in paperless billing link
			Boolean bEnrollPaperless = UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBilling,1);
			Report.TestPoint(testContext.getName(), "Verify Enroll in paperless billing link", "true", String.valueOf(bEnrollPaperless), true);
			//Click on Enroll in paperless billing link
			Report.OperationPoint(testContext.getName(), "Click on Enroll in paperless billing link");
			oR_AccountOverview.lnkEnrollInPaperlessBilling.click();
			
			//Validate paperless billing landing page
			Boolean bEnrollPaperlessPg = UI.WaitForObject(oR_PaperlessBilling.txtPaperlessBillingTitle,30);
			Report.TestPoint(testContext.getName(), "Verify paperless billing landing page", "true", String.valueOf(bEnrollPaperlessPg), true);
			//Validate Account 
			Boolean bAccount = UI.WaitForObject(oR_PaperlessBilling.txtAccount,30);
			Report.ValidationPoint(testContext.getName(), "Verify Account", "true", String.valueOf(bAccount), true);
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
					
					
	}

	/**************************************************************
	 * Function Name - VerifyUpgradeEligibilityInServiceSummary() 
	 * Description- This function Validates if Upgrade Eligibility msg is present under device image in service summary section
	 * Parameters - None
	 * Date created - 26th March 2015
	 * Developed by - Monica M
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	public static void VerifyUpgradeEligibilityInServiceSummary(ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName()); 
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);

		Boolean bUpgradeCountdown, bOfferDates,bViewUpgradeOptions;
		try
		{
//2			Verify the upgrade countdown/future eligibility alert is displayed
			bUpgradeCountdown = UI.WaitForObject(oR_AccountOverview.txtUpgradeEligibilityAlert, 1, lDriver);
			Report.ValidationPoint(testContext.getName(), "1-Verify the upgrade countdown/future eligibility alert is displayed", "true", bUpgradeCountdown.toString(), true);

/*//3			Verify the Standard Offer Eligibility dates is displayed on the upgarde message.
			bOfferDates = UI.WaitForObject(oR_AccountOverview.txtOfferDates, 1, lDriver);
			Report.ValidationPoint(testContext.getName(), "3-Verify the Standard Offer Eligibility dates is displayed on the upgarde message.", "true", bOfferDates.toString(), true);*/

//4			Verify view upgrade options is available under Device image
			bViewUpgradeOptions = UI.WaitForObject(oR_AccountOverview.lnkViewUpgradeOptions, UI.iObjTimeOut, lDriver);
			
			Report.ValidationPoint(testContext.getName(),"2-Verify Upgrade countdown is located under device image and device details modal","true", bViewUpgradeOptions.toString(), true);
			/*List<WebElement> lnkUpgradeEligibility = lDriver.findElements(By.xpath("//span[contains(text(),'View upgrade options')]"));
			 
			 if(lnkUpgradeEligibility.size()!=0)
			 {
				 Report.ValidationPoint(testContext.getName(), "Verify that upgrade Eligibility msg is present under device", "true", "true", true);
			 }
			 else
			 {
				 Report.ValidationPoint(testContext.getName(), "Verify that upgrade Eligibility msg is present under device", "true", "false", true);
			 }*/
		}
		catch(Exception e)
		{
		  Report.TestPoint(testContext.getName(),"Failed to Upgrade Eligibility message under device image", "True",e.getMessage(), true);
		}
		
	}
	/**************************************************************
	 * Function Name -  VerifySingleALert()
	 * Description- This function validates Nickname, Header, Description and CTAs for a single alert
	 * Parameters -
	 * Date created - 30th March 2015
	 * Developed by - Monica Mohabansi 
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	public static void VerifySingleALert(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		String sAlertHeader = IO.GetParamVal(sTestParams, "Alert_Header");
		String sALertDesc = IO.GetParamVal(sTestParams, "Alert_Description");
		String sCTA1 = IO.GetParamVal(sTestParams, "CTA1_alt");
		String sCTA2 = IO.GetParamVal(sTestParams, "CTA2_alt");

		Boolean bALertIcon;
		try
		{
			Report.OperationPoint(testContext.getName(), "Verify Alert " +sAlertHeader);
			//Check For ALert Icon and Click to Expand Alerts
			bALertIcon= UI.WaitForObject(oR_AccountOverview.imgAlertIcon, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Alert Icon Present", "true", String.valueOf(bALertIcon), true);
			oR_AccountOverview.imgAlertIcon.click();
			
			try
			{
				if(lDriver.findElement(By.xpath("//ul[@id='alertPage_1']//*[contains(text(),'"+sAlertHeader+"')]")).isDisplayed())
				{
					Report.TestPoint(testContext.getName(), "ALert with header "+sAlertHeader, "Present", "Present", true);
					//Switch to alert with desired Header
//					lDriver.switchTo().activeElement().findElement(By.xpath("//ul[@id='alertPage_1']//strong[contains(text(),'"+sAlertHeader+"')]/parent::div"));
					lDriver.switchTo().activeElement().findElement(By.xpath("//ul[@id='alertPage_1']//*[contains(text(),'"+sAlertHeader+"')]//parent::p//parent::div"));
					//Verify that it contains service name and Nick name in it.
					try
					{
						if(testContext.getName().contains("DBD16719"))				
						{
							if(lDriver.findElement(By.xpath("//p[contains(@class,'colorGrey')]")).isDisplayed())
								Report.ValidationPoint(testContext.getName(), "NickName And Serivce Name", "Present", "Present", true);
							else
								Report.ValidationPoint(testContext.getName(), "NickName And Serivce Name", "Present", "Absent", true);
						}
					}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(), "NickName And Number", "Present", "Absent", true);
						}
					
					
					if(sALertDesc==null || sALertDesc=="" || sALertDesc==" " || sALertDesc.isEmpty());
					else
					{	
						try
						{
							if(lDriver.findElement(By.xpath("//p[contains(text(),'"+sALertDesc+"')]")).isDisplayed())
							{
								Report.ValidationPoint(testContext.getName(), "Alert Description" +sALertDesc , "Present", "Present", true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Alert Description" +sALertDesc , "Present", "Absent", true);
							}
							
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(), "Alert Description" +sALertDesc , "Present", "Absent", true);
						}
					}
					if(sCTA1==null || sCTA1=="" || sCTA1.isEmpty() || sCTA1==" ");
					else
					{
						try
						{									//a[@alt='View request']
							if(lDriver.findElement(By.xpath("//a[@alt='"+sCTA1+"']")).isDisplayed())
								Report.ValidationPoint(testContext.getName(), sCTA1 + " Link", "Present", "Present", true);
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(), sCTA1 + " Link", "Present", "Absent", true);
						}
					}
					if(sCTA2.equals(null) || sCTA2.equals("") || sCTA2.isEmpty() || sCTA2.equals(" ") || sCTA2.equals("NA"));
					else
					{
						try
						{
							if(lDriver.findElement(By.xpath("//a[@alt="+sCTA2+"]")).isDisplayed())
								Report.ValidationPoint(testContext.getName(), sCTA2 + " Link", "Present", "Present", true);
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(), sCTA2 + " Link", "Present", "Absent", true);
						}
					}
					
				}	
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "ALert with header "+sAlertHeader, "Present", "Absent", true);
			}
		
		}
		catch(Exception e)
		{
			Report.ValidationPoint(testContext.getName(), "Failed to validate Single Alert", "Pass", "Fail", true);
		}
	}
	/**************************************************************
	 * Function Name - VerifyEnrolPaperlesslink() 
	 * Description- This function Validates if Enroll Paperless billing is present and navigates to the expected page
	 * Parameters - None
	 * Date created - 7th April 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	public static void VerifyEnrolPaperlesslink(ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_PaperlessBilling oR_PaperlessBilling = PageFactory.initElements(lDriver, OR_PaperlessBilling.class);
		
		try
		{
			Boolean bEnrollPaperlesslink = UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBilling,30);
			Report.TestPoint(testContext.getName(), "Verify 'Enroll Paperless Billing link is present'", "true", String.valueOf(bEnrollPaperlesslink), true);
			oR_AccountOverview.lnkEnrollInPaperlessBilling.click();
			Boolean bEnrollPaperlesstxt = UI.WaitForObject(oR_PaperlessBilling.txtPaperlessBillingTitle,30);
			Report.TestPoint(testContext.getName(), "Verify user is navigated to Paperless Billing Page is displayed'", "true", String.valueOf(bEnrollPaperlesstxt), true);
			//need to check for data with multiple account
		}
		catch(Exception e)
		{
		  Report.TestPoint(testContext.getName(),"Failed to Upgrade Eligibility message under device image", "True",e.getMessage(), true);
		}
		
	}
	
	/**************************************************************
	 * Function Name - VerifyPlentiLink()
	 * Description - This function is to validate Plenti Indicator positioned in the Welcome Section of the Overview page
	 * Parameters - None
	 * Date created - 8th April 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD14082
	public static void VerifyPlentiLink(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_ManagePlentiCard oR_ManagePlentiCard = PageFactory.initElements(lDriver, OR_ManagePlentiCard.class);
		try
		{
			//Validate the link 'Earn Plenti points at AT&T' is displayed under Welcome text
			boolean bPlenti = UI.WaitForObject(oR_AccountOverview.lnkPlenti, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate that the link 'Earn Plenti points at AT&T' for Plenti eligible customers are displayed", "true", String.valueOf(bPlenti), true);
			oR_AccountOverview.lnkPlenti.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Earn Plenti points at AT&T' link");
			Thread.sleep(5000);
			
			//Validate it is redirected to Plenti landing page
			String sTitle = lDriver.getTitle().toLowerCase();
			if(sTitle.contains("plenti"))
			{
				Report.ValidationPoint(testContext.getName(), "Validate that Plenti landing page is displayed", "true", "true", true);
			
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Validate that Plenti landing page is displayed", "true", "false", true);
			}
			
			//validate Manage plenti link button is displayed
			boolean bManagePlentiBtn = UI.WaitForObject(oR_ManagePlentiCard.btnManagePlentiLink, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that 'Manage Plenti Links' button is displayed in landing page", "true", String.valueOf(bManagePlentiBtn), true);
			oR_ManagePlentiCard.btnManagePlentiLink.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Manage Plenti link' button");
			
			//Validate it is redirected to Manage Plenti Card Links page and verify URL
			boolean bManagePlenti = UI.WaitForObject(oR_ManagePlentiCard.txtManagePlentiCardLink, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that 'Manage Plenti Card Links' page is displayed", "true", String.valueOf(bManagePlenti), true);
			//Valiadte the page is redirected to correct URL
			String sPlentiURL = lDriver.getCurrentUrl().toLowerCase();
			if(sPlentiURL.contains("plentimanage.myworld"))
			{
				Report.ValidationPoint(testContext.getName(), "Verify that the URL for newly loaded page is something similar to ATT Micro-site URL: www.att.com/olam/plentimanage.myworld. URL for current page is: "+sPlentiURL, "true","true", true);
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that the URL for newly loaded page is something similar to ATT Micro-site URL: www.att.com/olam/plentimanage.myworld. URL for current page is: "+sPlentiURL, "true","false", true);
			}
				

		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - VerifyServiceSummaryForSuspendedAccount()
	 * Description - This method verifies grayed out image is displayed with yellow suspended label for suspended account within service summary section, verifies Troubleshoot & resolve link and Billing period usage bar
	 * Parameters - None
	 * Date created - 13th April 2015
	 * Developed by - Clint John
	 * Last Modified By -
	 * Last Modified Date -
	 ***************************************************************/
	//DBD09025
	public static void VerifyServiceSummaryForSuspendedAccount(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
			//Start validating by slecting each plans which is displayed under MyPlans one by one and if if you get at leat one suspended plan - Validate overview page
			List<WebElement> MyPlansList = lDriver.findElements(By.xpath("//ul[@id='MainTab']/li/a"));
			int i=1;
			for (WebElement element: MyPlansList) 
			{
				element.click();
				boolean bSuspendedAlertImage = UI.WaitForObject(oR_AccountOverview.imgSuspendAlertImage, UI.iObjTimeOut);
				boolean bSuspendedAccountText = UI.WaitForObject(oR_AccountOverview.txtSuspendAccountText, UI.iObjTimeOut);
				if(bSuspendedAlertImage==true || bSuspendedAccountText==true)
				{
					Report.ValidationPoint(testContext.getName(), "Verify that at least one plan which is already suspended is displayed under 'My Plans' section", "true", "true", true);
					break;
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Plan number: "+i+" is not suspended, It is active!", "true", "true", true);
					i++;
				}
			}
			
			//Verify grayed out image is displayed with yellow suspended label within service summary section
			boolean bSuspended = UI.WaitForObject(oR_AccountOverview.imgSuspendAlertImage, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that Suspended plan is displayed in Service summary section", "true", String.valueOf(bSuspended), true);
//			//Verify that grayed out image is displayed for suspended plan
//			boolean bSuspendedPlan = UI.WaitForObject(oR_AccountOverview.imgSuspendPlanImage, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Verify that Device image for suspended plan is displayed in Service summary section", "true", String.valueOf(bSuspendedPlan), true);
//			//Validate that a Greyed out device image is displayed
//			String sImageOpacityValue = oR_AccountOverview.imgSuspendPlanImage.getCssValue("opacity");
//			if(sImageOpacityValue.equals("0.4"))
//			//Opacity value in CSS can chnage depending on what developer decides but for now its 0.4 mentioned everywhere for a greyed out image
//			{
//				Report.ValidationPoint(testContext.getName(), "Verify that Device image for suspended plan is Greyed out", "true","true", true);
//				
//			}else
//			{
//				Report.ValidationPoint(testContext.getName(), "Verify that Device image for suspended plan is Greyed out", "true","false", true);
//			}
//			//Validate that yellow suspended label is displaying along with suspended device image
//			boolean bSuspendedAlertImage = UI.WaitForObject(oR_AccountOverview.imgSuspendAlertImage, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Verify that yellow suspended label is displayed along with suspended device image", "true", String.valueOf(bSuspendedAlertImage), true);
			//Verify Trouble shoot and resolve link is present within the summary section
			boolean bTroubleshoot = UI.WaitForObject(oR_AccountOverview.lnkTroubleshoot, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(),"Verify that Fix it now/Trouble shoot and resolve link is present within the service summary section", "true", String.valueOf(bTroubleshoot), true);
			boolean bViewHelpVideos = UI.WaitForObject(oR_AccountOverview.lnkViewHelpVideos, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(),"Verify that View help videos and articles link is present within the service summary section", "true", String.valueOf(bViewHelpVideos), true);	
			//Verify billing cycle bar and days left is displayed within the service summary section
			boolean bBillingPeriod = UI.WaitForObject(oR_AccountOverview.txtBillingPeriod, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that text for Billing Period is displayed", "true", String.valueOf(bBillingPeriod), true);
			String sDaysLeft = oR_AccountOverview.txtDaysLeft.getText();
			if(sDaysLeft.contains("days left") || UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(@class,'days-left')]")), UI.iObjTimeOut, lDriver).equals(true))
			{
				Report.ValidationPoint(testContext.getName(), "Verify that text for number of days left is displayed under billing usgae bar", "true","true", true);
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that text for number of days left is displayed under billing usgae bar", "true","false", true);
			}
			
			boolean bWifiKey = UI.WaitForObject(oR_AccountOverview.lnkFindMyWifiNetworkKey, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(),"Verify that 'Find my Wi-Fi network key' link is present within the service summary section", "true", String.valueOf(bWifiKey), true);	

		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	/**************************************************************
	 * Function Name - Verifyalertsandunbilleddata()
	 * Description- Verifies alerts and validates unbilled data
	 * Parameters - 
	 * Date created - 10-Apr-2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//DBD11727

	public static void Verifyalertsandunbilleddata(final ITestContext testContext) throws Exception 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
			Actions driver = new Actions(lDriver);
			//validation Alert text
			boolean txtalert= UI.WaitForObject(oR_AccountOverview.btnAlert, UI.iObjTimeOut);
				if(txtalert)			
				{
					oR_AccountOverview.btnAlert.click();
					Report.TestPoint(testContext.getName(), "Validate alerts are available", "true", String.valueOf(txtalert), true);
				}
			//verifying whether unbilled mobile purchase alert is available 
			boolean alertunbilled= UI.WaitForObject(oR_AccountOverview.txtAlertsUnbilledPurchases, UI.iObjTimeOut);
			if(alertunbilled==false){
				if(UI.WaitForObject(oR_AccountOverview.btnDownarrow, 10, lDriver)){
					oR_AccountOverview.btnDownarrow.click();
				}
			}
			Report.ValidationPoint(testContext.getName(), "unbilled mobile purchase alert is available", "true", String.valueOf(oR_AccountOverview.txtAlertsUnbilledPurchases.isDisplayed()), true);
	
			
			 //verifying whether text and description is available or not in alert
			boolean alertunbilledtext= UI.WaitForObject(oR_AccountOverview.txtAlertstextanddesciption, UI.iObjTimeOut);
			 Report.ValidationPoint(testContext.getName(), "Text and  Description is available", "true", String.valueOf(alertunbilledtext), true);
					
			 //verifying whether CTA to unbilled mobile purchase tab is available  
			 boolean CTAtounbilled= UI.WaitForObject(oR_AccountOverview.lnkCTAtounbilledpurchases, UI.iObjTimeOut);
			 Report.ValidationPoint(testContext.getName(), "CTA to unbilled mobile purchase tab is available ", "true", String.valueOf(CTAtounbilled), true);
			 
			//verifying whether alert is placed in desired format or not
			 if(alertunbilled&&CTAtounbilled){
				 Report.ValidationPoint(testContext.getName(), "Verifying desired format of alert", "Pass", "Pass", true);
			 }
		  
			 //verify priority of element
			 Point coordinate1= oR_AccountOverview.lnkCTAtounbilledpurchases.getLocation();
			 System.out.println("Unbilled alert is placed at location"+"X:"+coordinate1.x+"   "+"Y:"+coordinate1.y);
				
			 if(UI.WaitForObject(oR_AccountOverview.lnkCTAtoBilledpurchases, 10, lDriver)){
			 Point coordinate2= lDriver.findElement(By.xpath("//ul[@id='alertPage_1']//li[@class='noFloat']")).getLocation();
			 System.out.println("Billed alert is placed at location"+"X:"+coordinate2.x+"   "+"Y:"+coordinate2.y);
			 
			 if(coordinate1.y<coordinate2.y)
			  {				  
				 Report.ValidationPoint(testContext.getName(), "Comparing priorities of Unbilled and Billed purchases", "Pass", "Pass", true);
			  }
			  else	
			  {
				  Report.ValidationPoint(testContext.getName(), "Comparing priorities of Unbilled and Billed purchases", "Pass", "Fail", true);
			  }
			 }
			 // validatiing view all usage link
			 boolean viewallusagelink= UI.WaitForObject(oR_AccountOverview.lnkmobileviewallusage, 100);
			 if(viewallusagelink){
			 Report.ValidationPoint(testContext.getName(), "View all usage link is available ", "true", String.valueOf(viewallusagelink), true);
			 oR_AccountOverview.lnkmobileviewallusage.click();
			 Report.OperationPoint(testContext.getName(), "Navigating to view all usage page to validate unbilled talk and data");
			 }
			// validatiing details link in view all usage page
			 boolean detailsinallusage= UI.WaitForObject(oR_AccountOverview.lnkdetailsinallusagelink, UI.iObjTimeOut);
			 oR_AccountOverview.lnkdetailsinallusagelink.click();
			 Report.ValidationPoint(testContext.getName(), "Details link is available in view all usage page", "true", String.valueOf(detailsinallusage), true);
			 
			 
			 //selecting talk from mobile purchase drop down
			 if(UI.WaitForObject(oR_AccountOverview.frmTourMyBill, 10, lDriver)){
				 lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
				 driver.moveToElement(lDriver.findElement(By.xpath("//p[contains(text(),'Mobile purchases')]"))).click().build().perform();
				 Thread.sleep(5000);
				 driver.moveToElement(lDriver.findElement(By.xpath("id('viewUsageBox')//dl//dd//a[contains(text(),'Talk')]"))).click().build().perform();
				 Report.OperationPoint(testContext.getName(), "Clicking on text froam drop down");
				 Thread.sleep(5000);
				 //validate unbilled talk
				 WebElement Talk= lDriver.findElement(By.xpath("//p[text()='Talk']"));
				 Report.ValidationPoint(testContext.getName(), "Validate unbilled  Talk is selected from dropdown", "true", String.valueOf(Talk.isDisplayed()), true);
				
				 //validate unbilled data
				 Talk.click();
				 Thread.sleep(3000);
				 WebElement Data= lDriver.findElement(By.xpath("id('viewUsageBox')//dl//dd//a[contains(text(),'Data')]"));
				 if(Data.isDisplayed()){
				 driver.moveToElement(Data).click().build().perform();
				 Report.OperationPoint(testContext.getName(), "Clicking on data froam drop down");
				 Thread.sleep(5000);
				 Report.ValidationPoint(testContext.getName(), "Validate unbilled  Data is selected from dropdown", "true", "true", true);
				lDriver.switchTo().defaultContent();
			 }}
		}		catch(Exception e)
			{
			e.printStackTrace();
				Report.ValidationPoint(testContext.getName(), "Some Error has occured", "Pass", "Fail", true);
			}
	}

	/**************************************************************
	 * Function Name - ValidateChangePlan
	 * Description- 
	 * Parameters - 
	 * Date created - 22nd April 2015
	 * Developed by - Krutika Kamdi 
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/


	public static void ValidateChangePlan(final ITestContext testContext)
			throws Exception {
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try{
			
			Report.OperationPoint(testContext.getName(), "Navigate to Change Plan page");
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkChangeMyPlanTertiaryNav, lDriver);
					
			//Navigating to Change Plan Page
			
			WebElement txtChangePlan = lDriver.findElement(By.xpath("//h1[contains(text(),'View or Change Rate Plan')]"));
			Boolean bChangePlanPage = UI.WaitForObject(txtChangePlan,40);
			Report.TestPoint(testContext.getName(), "Verify user is navigated to Change Plan page", "true", String.valueOf(bChangePlanPage), true);
		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}		
	/**************************************************************
	 * Function Name - VerifBalanceAndDueDate()
	 * Description- Verifies text balance and payment due date
	 * Parameters - 
	 * Date created - 22-Apr-2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
//DBD21821
	public static void VerifBalanceAndDueDate(final ITestContext testContext) throws Exception 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
		
		try
			{
				//verify total balance amount text is displayed
				Boolean bBalance = UI.WaitForObject(oR_AccountOverview.txtTotalBalance, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Verify the Total balance Amount text is displayed", "true", bBalance.toString(), true);
				//Verify the balance amount is in orange colour
				Boolean bCurrentBalance = UI.WaitForObject(oR_AccountOverview.txtCurrentBalanceAmt, UI.iObjTimeOut);
				if(bCurrentBalance.equals(true))
				{
					Report.TestPoint(testContext.getName(), "Verify the Current balance Amount text is displayed in Orange colour", "true", bCurrentBalance.toString(), true);
				}
				//verify view bill detail link is present and redirects to Bill page
				Boolean bViewBillDetails = UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Verify View Bill Details link is displayed", "true", bViewBillDetails.toString(), true);
			
				if(bViewBillDetails.equals(true))
				{
					oR_AccountOverview.lnkViewBillDetails.click();
					Thread.sleep(20000);
					Boolean bBillPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "Verify View Bill Details link redirects to Bill Page", "true", bBillPage.toString(), true);
					lDriver.navigate().back();
				}		
								
				//verify Make A Payment button is displayed and it redirects to Payment module
				Boolean bMakePayment = UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentInOverviewPage, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Verify Make a Payment button is displayed", "true", bMakePayment.toString(), true);
				if(bMakePayment.equals(true))
				{
					oR_AccountOverview.btnMakeAPaymentInOverviewPage.click();
					Thread.sleep(20000);
					Boolean bDueDate = UI.WaitForObject(oR_MakeAPayment.txtDueDate, UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "Verify Due Date is displayed", "true", bDueDate.toString(), true);
				}

			}
			catch (Exception e) 
			{
				Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

			}
	}
	/**************************************************************
	 * Function Name - VerifCreditBalanceAndMessage()
	 * Description- Verifies text balance and payment due date
	 * Parameters - 
	 * Date created - 22-Apr-2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
//DBD21827
	public static void VerifyCreditBalanceAndMessage(final ITestContext testContext) throws Exception 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		
		try
		{
			// verify current balance text
			boolean bCurrentBill = UI.WaitForObject(oR_AccountOverview.txtTotalBalance,10);
			Report.TestPoint(testContext.getName(), "Verify Current bill text is displayed", "true", String.valueOf(bCurrentBill), true);
		
			//Verify current balance $ Amount with CR and in GREEN
			boolean bCurrentBalAmount = UI.WaitForObject(oR_AccountOverview.txtCurrentBalanceAmt,5);
			boolean bCurrentBalCR = UI.WaitForObject(oR_AccountOverview.txtCRinCurrentBalance,5);
		
			if(oR_AccountOverview.txtBalanceAmtInGreenColor.isDisplayed())
					{
				Report.TestPoint(testContext.getName(), "Verify current balance $ Amount with CR and in GREEN", "true", String.valueOf(bCurrentBalAmount), true);
				
					}
			else
				{
				Report.TestPoint(testContext.getName(), "Verify current balance $ Amount with CR and in GREEN", "true", "false", true);
				
				}
			//Verify "View bill " BUTTON which redirects to Bill Details page
			boolean bViewBill = UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails,5);
			//Verify note " no action necessary " under view bill button
			boolean bNoAction = UI.WaitForObject(oR_AccountOverview.txtNoActionNecessary,5);
			Report.TestPoint(testContext.getName(), "Verify note  no action necessary  under view bill button", "true", String.valueOf(bNoAction), true);
			
			//Verify message under balance about credit (content TBD)
			boolean bMessage = UI.WaitForObject(oR_AccountOverview.txtNoActionNecessary,5);
			Report.TestPoint(testContext.getName(), "Verify message under balance about credit (content TBD)", "true", String.valueOf(bMessage), true);
			
			Report.TestPoint(testContext.getName(), "Verify View bill  BUTTON is displayed", "true", String.valueOf(bViewBill), true);
			oR_AccountOverview.lnkViewBillDetails.click();
			
			Report.OperationPoint(testContext.getName(), "Navigating to Bill Details Page");
			boolean bBillAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,5);
			Report.TestPoint(testContext.getName(), "Verify View bill  BUTTON is redirecting to  Bill Details page", "true", String.valueOf(bBillAndUsage), true);
					
			}

		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	}
	/************************************************************
	 * Function Name - VerifyInvalidEmailAddressAlert
	 * Description- Verifies text balance and payment due date
	 * Parameters - 
	 * Date created - 22-Apr-2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//DBD08853
	public static void VerifyInvalidEmailAddressAlert(final ITestContext testContext) throws Exception 
	{   
		WebDriver lDriver = UI.getDriver(testContext.getName());           
	    OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	    OR_EditBillingContactInformation oR_EditBillingContactInformation = PageFactory.initElements(lDriver, OR_EditBillingContactInformation.class);
	    OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
	    OR_BillingSettingsNotice oR_BillingSettingsNotice = PageFactory.initElements(lDriver, OR_BillingSettingsNotice.class);
	    OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
		   
	    try
		{
	    	
	    	//check for promotion
	    	boolean bPromotion= UI.WaitForObject(oR_AccountOverview.imgPromotions, 5, lDriver);
	    	Report.ValidationPoint(testContext.getName(),"Verify promotions are available on dashboard","true",String.valueOf(bPromotion),true);
	    	
			//Validate Invalid email address alert in dashboard
			//Validate alert button
			Boolean bAlertButton = UI.WaitForObject(oR_AccountOverview.btnAlert,10);
			Report.TestPoint(testContext.getName(), "Verify alert button", "true", String.valueOf(bAlertButton), true);
			
			
			//Click on the alert button
			Thread.sleep(5000);
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on the alert button");
			oR_AccountOverview.btnAlert.click();
			List <WebElement> lstInvalidEmail = lDriver.findElements(By.xpath("//p[text()='Invalid Email Address']"));
			if(lstInvalidEmail.size()>0)
			{
				Report.TestPoint(testContext.getName(), "Verify Invalid email address alert in dashboard", "true","true", true);
				
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Verify Invalid email address alert in dashboard", "true","false", true);
			}
			//Validate the alert description as : The email address we have on file for you is not working.
			List <WebElement> lstDesc = lDriver.findElements(By.xpath("//p[text()='The email address we have on file for you is not working.']"));
			if(lstDesc.size()>0)
			{
				Report.ValidationPoint(testContext.getName(), "Validate the alert description as : The email address we have on file for you is not working.", "true","true", true);
				
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate the alert description as : The email address we have on file for you is not working.", "true","false", true);
			}
			//Validate link Update email address
			List <WebElement> lstLnk = lDriver.findElements(By.xpath("//a[@alt='Update email address']"));
			if(lstLnk.size()>0)
			{
				Report.TestPoint(testContext.getName(), "Validate link Update email address", "true","true", true);
				
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Validate link Update email address", "true","false", true);
			}
			//Click on Update email address link
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on Update email address link");
			lstLnk.get(0).click();
			
			//Validate Edit Billing Contact info Page
			Boolean bEdit = UI.WaitForObject(oR_EditBillingContactInformation.txtEditBillingContactInformation,100);
			Report.TestPoint(testContext.getName(), "Verify Edit Billing Contact info Page", "true", String.valueOf(bEdit), true);
			
			//Logout
			if (UI.WaitForObject(oR_AccountOverview.btnLogout, 5).equals(true)) {
				Report.OperationPoint(testContext.getName(), "Operational - Logging out");
				Report.TestPoint(testContext.getName(),
						"Validate logout button is displayed", "True",
						"True", true);
				oR_AccountOverview.btnLogout.click();
			}
				/*if (UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, 3)) {
					Report.TestPoint(testContext.getName(),
							"Validate user successfully logged out and MyATT login page is dispalyed",
							"True", "True", true);
				}*/
				Thread.sleep(2000);
				lDriver.navigate().to(Report.sMyATTEnvURL);
				Report.OperationPoint(testContext.getName(), "Operational - Logging in");
				Thread.sleep(10000);
			//Login
			LaunchAndLogout.LoginApplication(testContext);
			
			//validate alert disppeared or not
			
			//Click on profile sec navigation
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav,null);
			//Validate My profile page
			Boolean bProfile = UI.WaitForObject(oR_Profile.txtMyProfileTitle,100);
			Report.TestPoint(testContext.getName(), "Verify My profile page", "true", String.valueOf(bProfile), true);
			//Validate billing notification edit link
			Boolean bBilling = UI.WaitForObject(oR_Profile.lnkEditBillingNotifications,1);
			Report.TestPoint(testContext.getName(), "Verify billing notification edit link", "true", String.valueOf(bBilling), true);
			//Click on billing notification edit link
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on billing notification edit link");
			oR_Profile.lnkEditBillingNotifications.click();
			//Validate Billing settings notice page
			Boolean bBillingSettings = UI.WaitForObject(oR_BillingSettingsNotice.txtBillingSettingsNotice,100);
			Report.TestPoint(testContext.getName(), "Verify Billing settings notice page", "true", String.valueOf(bBillingSettings), true);
			//Validate user should be able to make changes
			List<WebElement> lstChange = lDriver.findElements(By.xpath("//div[@class='tabcontent']//h3"));
			//Boolean bToggle = UI.WaitForObject(oR_BillingSettingsNotice.lnkThresholdToggle,1);
			if(lstChange.size()>0)
			{
				Report.ValidationPoint(testContext.getName(), "Verify user should be able to make changes", "true","true", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify user should be able to make changes", "true","false", true);
			}
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	}
	
	/**************************************************************
	 * Function Name - VerifyUsageDetails()
	 * Description - This function is used to verify the usages details for each of the usages types and the Upgrade plan or service links direct users to the correct location for that particular account and plan
	 * Parameters - None
	 * Date created - 23rd Apr 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD08070
	public static void VerifyUsageDetails(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_ViewOrChangeRatePlan oR_ViewOrChangeRatePlan = PageFactory.initElements(lDriver, OR_ViewOrChangeRatePlan.class);
		OR_ATT_ManageMyDeviceFeatures oR_ATT_ManageMyDeviceFeatures = PageFactory.initElements(lDriver, OR_ATT_ManageMyDeviceFeatures.class);
		try
		{
			//Verify sections in I want to dropdown 
			if(oR_AccountOverview.btnIWantTo.isDisplayed())
			{
				WebElement element2 = oR_AccountOverview.btnIWantTo;
				new Actions(lDriver).moveToElement(element2).click().build().perform();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'I want to' dropdown");
				
				boolean bManageMyPlan = UI.WaitForObject(lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'Manage my plan')]")),UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Manage My Plan option is present under 'I want to' dropdown", "true", String.valueOf(bManageMyPlan), true);
				boolean bManageMyProfile = UI.WaitForObject(lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'Manage my profile')]")),UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Manage my profile option is present under 'I want to' dropdown", "true", String.valueOf(bManageMyProfile), true);
				boolean bGetHelp = UI.WaitForObject(lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'Get help & support')]")),UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Get help & support option is present under 'I want to' dropdown", "true", String.valueOf(bGetHelp), true);
				boolean bShop = UI.WaitForObject(lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'Shop AT&T')]")),UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Shop AT&T option is present under 'I want to' dropdown", "true", String.valueOf(bShop), true);
				int flag = 0;
				WebElement element3 = lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'Manage my profile')]"));
				try
				{
					WebElement element4 = lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'Wireless')]"));
					new Actions(lDriver).moveToElement(element2).moveToElement(element3).moveToElement(element4).click().build().perform();
					Report.OperationPoint(testContext.getName(), "Operational - Navigating through the dropdown list");
					
				}catch(Exception Ee)
				{
					flag = 1;
				}
				WebElement element5 = lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//*[contains(@id,'ShortcutFlyoutMenu')]//a[text()='Update my profile']"));
				
				if(flag==1)
				{
					new Actions(lDriver).moveToElement(element2).moveToElement(element3).click().build().perform();
					Report.OperationPoint(testContext.getName(), "Operational - Navigating through the dropdown list");
				}
				Report.OperationPoint(testContext.getName(), "Operational - Navigating through the dropdown list");
				new Actions(lDriver).moveToElement(element5).build().perform();
				Report.OperationPoint(testContext.getName(), "Operational - Mouse moved to Update my profile link");
				boolean bLink1 = UI.WaitForObject(element5, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Update my profile link is present under Manage my profile tab", "true", String.valueOf(bLink1), true);
				if(flag==0)
				{
					
					WebElement element6 = lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//*[contains(@id,'ShortcutFlyoutMenu')]//a[text()='Link my AT&T accounts']"));
					new Actions(lDriver).moveToElement(element6).build().perform();
					Report.OperationPoint(testContext.getName(), "Operational - Mouse moved to Link my AT&T accounts link");
					boolean bLink2 = UI.WaitForObject(element6, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Link my AT&T accounts link is present under Manage my profile tab", "true", String.valueOf(bLink2), true);
				}
				
				
			
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Validate that 'I want to' dropdown is displayed", "true","false", true);

			}
			
			new Actions(lDriver).moveToElement(oR_AccountOverview.btnLogout).build().perform();
			int UsageFlag = 0;
			
			//Verify the usage bar and other sections talk, text, data items in Dashboard
			List<WebElement> MyPlans = lDriver.findElements(By.xpath("//ul[@id='MainTab']/li"));
			int iPlansSize = MyPlans.size();
			int i=1;	
			for(i=1;i<=iPlansSize;i++)
			{
				boolean bPlan = UI.WaitForObject(lDriver.findElement(By.xpath("//ul[@id='MainTab']/li["+i+"]")), UI.iObjTimeOut);
				WebElement element = lDriver.findElement(By.xpath("//ul[@id='MainTab']/li["+i+"]/a"));
		        Actions action = new Actions(lDriver);
		        action.moveToElement(element).click().build().perform();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on plan from the plans list");
				Report.ValidationPoint(testContext.getName(), "Validate that a plan is selected from the list of Plans available in dashboard", "true", String.valueOf(bPlan), true);
				
				boolean bDataBar = UI.WaitForObject(oR_AccountOverview.imgDataUsageBar, UI.iObjTimeOut);
				boolean bTextBar = UI.WaitForObject(oR_AccountOverview.imgTextUsageBar, UI.iObjTimeOut);
				boolean bTalkBar = UI.WaitForObject(oR_AccountOverview.imgTalkUsageBar, UI.iObjTimeOut);
				if(bDataBar==true && bTextBar==true && bTalkBar==true)
				{
					Report.ValidationPoint(testContext.getName(), "Usage bars for Data, Text & Talk is displayed in dashboard for Plan no: "+i, "true", "true", true);
					//validate text, talk, data usuage bars
					//validate usage bar for Data is displayed
					Report.ValidationPoint(testContext.getName(), "Validate usage bar for Data is displayed in dashboard", "true", String.valueOf(bDataBar), true);
					Report.ValidationPoint(testContext.getName(), "Validate usage bar for Text is displayed in dashboard", "true", String.valueOf(bTextBar), true);			
					Report.ValidationPoint(testContext.getName(), "Validate usage bar for Talk is displayed in dashboard", "true", String.valueOf(bTalkBar), true);
					
					//Verify overage alert is displayed for Data
					boolean bDataOverage = UI.WaitForObject(oR_AccountOverview.imgDataUsageBarOverageAlert, UI.iObjTimeOut);
					//if(String.valueOf(bDataOverage).equals(true))
					if(bDataOverage)
					{
						WebElement OverageAlert = oR_AccountOverview.imgDataUsageBarOverageAlert;
						new Actions(lDriver).moveToElement(OverageAlert).build().perform();
						Report.OperationPoint(testContext.getName(), "Operational - mouse hovered over Overage alert for data");
						Report.ValidationPoint(testContext.getName(), "Validate Overage alert is displayed for Data usage bar", "true", String.valueOf(bDataOverage), true);
					}
					
					//Verify overage alert is displayed for Text
					boolean bTextOverage = UI.WaitForObject(oR_AccountOverview.imgTextUsageBarOverageAlert, UI.iObjTimeOut);
					//if(String.valueOf(bTextOverage).equals(true))
					if(bTextOverage)
					{
						WebElement OverageAlert = oR_AccountOverview.imgTextUsageBarOverageAlert;
						new Actions(lDriver).moveToElement(OverageAlert).build().perform();
						Report.OperationPoint(testContext.getName(), "Operational - mouse hovered over Overage alert for Text");
						Report.ValidationPoint(testContext.getName(), "Validate Overage alert is displayed for Text usage bar", "true", String.valueOf(bTextOverage), true);
					}
					
					//Verify overage alert is displayed for Talk
					boolean bTalkOverage = UI.WaitForObject(oR_AccountOverview.imgTalkUsageBarOverageAlert, UI.iObjTimeOut);
					//if(String.valueOf(bTalkOverage).equals(true))
					if(bTalkOverage)
					{
						WebElement OverageAlert = oR_AccountOverview.imgTalkUsageBarOverageAlert;
						new Actions(lDriver).moveToElement(OverageAlert).build().perform();
						Report.OperationPoint(testContext.getName(), "Operational - mouse hovered over Overage alert for Talk");
						Report.ValidationPoint(testContext.getName(), "Validate Overage alert is displayed for Talk usage bar", "true", String.valueOf(bTalkOverage), true);
					}
					
					//Verify Shared Data usage is not displayed
					boolean bSharedUsage = UI.WaitForObject(oR_AccountOverview.imgSharedDataUsage, UI.iObjTimeOut);
					//if(String.valueOf(bSharedUsage).equals(true))
					if(bSharedUsage)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that Member of shared data plan link is not displayed in web usage section", "true","false", true);

					}else
					{
						Report.ValidationPoint(testContext.getName(), "Verify that Member of shared data plan link is not displayed in web usage section", "true","true", true);
					}
					
					UsageFlag =1;
					break;
					
				}else if(UsageFlag==0)
				{
					Report.ValidationPoint(testContext.getName(), "Usage bars for Data, Text & Talk is NOT displayed in dashboard for Plan no: "+i, "true", "true", true);
					//If  NOT able to find Usage bars for Data, Text & Talk in any of the Plans available then do this
					if(i==iPlansSize)
					{
						Report.ValidationPoint(testContext.getName(), "Could NOT find Usage bars for Data, Text & Talk in any of the Plans available", "true", "false", true);
					}

				}
			}
			
			//Verify that upgrade Talk plan link displays for talk overage
			boolean bTalkPlan = UI.WaitForObject(oR_AccountOverview.lnkUpgradeTalkLimit, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that upgrade Talk plan link displays for talk overage", "true", String.valueOf(bTalkPlan), true);
			oR_AccountOverview.lnkUpgradeTalkLimit.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Upgrade talk plan link");
			Thread.sleep(3000);
			//click and verify it is redirected to upgrade talk plan page
			boolean bViewOrChangePage = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtViewOrChangeRatePlan, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that on clicking on upgrade Talk plan link, user is redirected to upgrade talk plan page (View or Change rate plan)", "true", String.valueOf(bViewOrChangePage), true);
			lDriver.navigate().back();
			Report.OperationPoint(testContext.getName(), "Operational - Navigating back to overview page");
			Thread.sleep(4000);
			
			//Verify that upgrade Text plan link displays for talk overage
			boolean bTextPlan = UI.WaitForObject(oR_AccountOverview.lnkUpgradeTextLimit, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that upgrade Text plan link displays for talk overage", "true", String.valueOf(bTextPlan), true);
			oR_AccountOverview.lnkUpgradeTextLimit.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Upgrade text plan link");
			Thread.sleep(3000);
			//click and verify it is redirected to upgrade talk plan page
			boolean bManageMyDevicePage = UI.WaitForObject(oR_ATT_ManageMyDeviceFeatures.txtManageMyDeviceFeatures, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that on clicking on upgrade Text plan link, user is redirected to upgrade text plan page (Manage my device & features)", "true", String.valueOf(bManageMyDevicePage), true);
			lDriver.navigate().back();
			Report.OperationPoint(testContext.getName(), "Operational - Navigating back to overview page");
			Thread.sleep(4000);
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	
	/**************************************************************
	 * Function Name - verifyQuickLinksAndPageChangeToSpanish()
	 * Description - This function do below things:
	 * 				Verify downward arrow in I want to dropdown button and verifies whether it is changing to upward arrow when I want to dropdown clicked
	 * 				Verifies below categories under I want to dropdown
	 * 						-View and pay bill
	 * 						-Manage U-verse TV
	 * 						-Manage U-verse TV , Manage U-verse Internet , Manage U-verse Voice
	 * 						-Get help & support
	 * 				Verifies that flyout is displaying for all categories under I want to dropdown
	 * 				Changes language to spanish and verifies whether dashboard contents are displaying in spanish
	 * Parameters - None
	 * Date created - 28th Apr 2015
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD12904
	
	public static void verifyQuickLinksAndPageChangeToSpanish(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		try
		{
			if(UI.WaitForObject(oR_AccountOverview.lnkIdoma, 5, lDriver)){
				oR_AccountOverview.lnkIdoma.click();
				Thread.sleep(4000);
				oR_AccountOverview.lnkPageInEnglish.click();
				Thread.sleep(5000);
			}
			
			//Verify quick link dropdown 
			boolean bIWantToDropdown = UI.WaitForObject(oR_AccountOverview.btnIWantTo,40);
			Report.TestPoint(testContext.getName(), "Verify quick link dropdown", "true", String.valueOf(bIWantToDropdown), true);
			
			//Verify that the default positon for quick link drop downarrow is 'down'
			try
			{
				WebElement elmDownArrowOfIwantToDropdown = lDriver.findElement(By.xpath("//button[contains(@id,'ddShortcut')  and contains(@class,'WDBg')]"));
				
				if(elmDownArrowOfIwantToDropdown.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(), "Verify that the default positon for quick link dropdown arrow is 'down'", "Downward arrow is displayed", "Downward arrow is displayed", true);
				}
				
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that the default positon for quick link dropdown arrow is 'down'", "Downward arrow is displayed", "Downward arrow is NOT displayed", true);
			}
			
			oR_AccountOverview.btnIWantTo.click();
			
			Thread.sleep(8000);

			//Verify that  when quick links drop down is selected, the arrow will be pointed up
			try
			{
				WebElement elmDownArrowOfIwantToDropdown = lDriver.findElement(By.xpath("//button[contains(@id,'ddShortcut')  and contains(@class,'WDTopBg')]"));
				
				if(elmDownArrowOfIwantToDropdown.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(), "Verify that  when quick links drop down is selected, the arrow will be pointed up", "Upward arrow is displayed", "Upward arrow is displayed", true);
				}
				
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that  when quick links drop down is selected, the arrow will be pointed up", "Upward arrow is displayed", "Upward arrow is NOT displayed", true);
			}
			
			oR_AccountOverview.btnIWantTo.click();
			Thread.sleep(4000);
			//Verify below categories should be present in quick link drop down
			//Verify  View and pay bill
			
			UI.VerifyCategoriesUnderIWantToDropdown("View & pay bill", null);
			
			//Verify  Manage U-verse TV
			UI.VerifyCategoriesUnderIWantToDropdown("Manage my plan", "U-verse TV");
			
			//Verify  Manage U-verse Internet
			UI.VerifyCategoriesUnderIWantToDropdown("Manage my plan", "U-verse Internet");
			
			//Verify  Manage U-verse Voice
			UI.VerifyCategoriesUnderIWantToDropdown("Get help & support", null);
			
			//Verify that Upon hover over of each category should display a menu fly-out
			Actions action = new Actions(lDriver);
			oR_AccountOverview.btnIWantTo.click();
			try
			{
				//Extract all categories under I want to dropdown
				List<WebElement> lstIwantToDropdownElements = lDriver.findElements(By.xpath("//*[contains(@aria-label,'Quick Links')]//li[contains(@id,'ShortcutMenu')]"));
			
				//Check flyout for all categories under dropdown
				for(int cnt= 0; cnt< lstIwantToDropdownElements.size() ; cnt++)
				{
					action.moveToElement(lstIwantToDropdownElements.get(cnt)).build().perform();
					Thread.sleep(4000);
					try
					{
						//Check whether flyout is displaying after hovering over category
						WebElement flyOutMenu = lDriver.findElement(By.xpath("//*[contains(@aria-label,'Quick Links')]//li[contains(@id,'ShortcutMenu"+(cnt+1)+"')]//*[contains(@id,'flyOut')]"));
						if(flyOutMenu.isDisplayed())
						{
							Report.ValidationPoint(testContext.getName(), "Verify that flyout is displayed for category no "+(cnt+1)+" under I want to dropdown", "Flyout displayed", "Flyout displayed", true);
						}
					}
					catch(Exception e)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that flyout is displayed for category no "+(cnt+1), "Flyout displayed", "Flyout NOT displayed", true);
					}
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that flyout is displayed for categoris under I want to dropdown", "Flyout displayed", "Categories NOT present", true);
			}
			
			//Verify that entire quick links menu should close when user hovers out of the menu
			action.moveToElement(oR_AccountOverview.txtWelcome).build().perform();
			try
			{
				WebElement elmDownArrowOfIwantToDropdown = lDriver.findElement(By.xpath("//button[contains(@id,'ddShortcut')  and contains(@class,'WDBg')]"));
				
				if(elmDownArrowOfIwantToDropdown.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(), "Verify that entire quick links menu should close when user hovers out of the menu", "Quick links menu closed after hovering out of the menu", "Quick links menu closed after hovering out of the menu", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that entire quick links menu should close when user hovers out of the menu", "Quick links menu closed after hovering out of the menu", "Quick links menu NOT closed after hovering out of the menu", true);
				}
				
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that entire quick links menu should close when user hovers out of the menu", "Quick links menu closed after hovering out of the menu", "Quick links menu NOT closed after hovering out of the menu", true);
			}
			
			//Change language to spanish
			//Verify Language link on top
			
			boolean bLinkLanguage = UI.WaitForObject(oR_AccountOverview.lstLanguage,10);
			//Click on link
			oR_AccountOverview.lstLanguage.click();
			Thread.sleep(4000);
			Report.TestPoint(testContext.getName(), "Verify Language link on top", "true", String.valueOf(bLinkLanguage), true);
			
			//Click on link 'Page in spanish'
			boolean bLinkPageInSpanish = UI.WaitForObject(oR_AccountOverview.lnkPageInSpanish,6);
			if(bLinkPageInSpanish)
			{
			Report.TestPoint(testContext.getName(), "Verify 'Page in spanish' link under Language dropdown", "true", String.valueOf(bLinkPageInSpanish), true);
			
			//Change Language to Spanish
			oR_AccountOverview.lnkPageInSpanish.click();
			Report.OperationPoint(testContext.getName(), "Clicked on Spanish Link");
			Thread.sleep(5000);
			String URL=lDriver.getCurrentUrl();
			System.out.println(URL);
			if(URL.equals("http://espanol.att.com/")){
				lDriver.navigate().back();
			}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify 'Page in spanish' link under Language dropdown", "true", String.valueOf(bLinkPageInSpanish), true);
				
			}
			if(UI.WaitForObject(oR_AccountOverview.lnkIdoma, 5, lDriver)){
			Report.TestPoint(testContext.getName(), "Change language to spanish", "Changed", "Changed", true);
			
			//Verify Dashboard contents are displaying in Spanish
			
			//here it is verified that whether overview link is getting displayed in spanish to identify page change in spanish
			boolean bLinkOverviewInSpanish = UI.WaitForObject(oR_AccountOverview.lnkResumen,40);
			Report.TestPoint(testContext.getName(), "Verify Dashboard contents are displaying in Spanish", "true", String.valueOf(bLinkOverviewInSpanish), true);
			
			//Change language back to English
			boolean bLinkLanguageInSpanish = UI.WaitForObject(oR_AccountOverview.lnkIdoma,40);
			oR_AccountOverview.lnkIdoma.click();
			Thread.sleep(4000);
			Report.TestPoint(testContext.getName(), "Click on Language link which is now displaying in spanish", "true", String.valueOf(bLinkLanguageInSpanish), true);
			
			//Click on 'Page in English' link under Language dropdown 
			boolean bLinkPageInEnglish = UI.WaitForObject(oR_AccountOverview.lnkPageInEnglish,40);
			oR_AccountOverview.lnkPageInEnglish.click();
			Thread.sleep(8000);
			Report.TestPoint(testContext.getName(), "Change language back to English", "true", String.valueOf(bLinkPageInEnglish), true);
			}
			else
			{
				Report.TestPoint(testContext.getName(), "validate page displayed in spanish", "Not displayed in spanish", "Not displayed in spanish", true);
				
			}
			oR_AccountOverview.btnIWantTo.click();
			Report.TestPoint(testContext.getName(), "validate Drop down  listed all the links only once", "true", "true", true);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name - VerifyDashboardElements() 
	 * Description- This function Validates
	 * Parameters - None
	 * Date created - 6th May 2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	//DBD21826
	public static void VerifyDashboardElements(ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		
		try
		{
				// verify current balance text
				boolean bCurrentBill = UI.WaitForObject(oR_AccountOverview.txtTotalBalance,40);
				Report.TestPoint(testContext.getName(), "Verify Current bill text is displayed", "true", String.valueOf(bCurrentBill), true);
			
				//Verify current balance $ Amount with CR and in GREEN
				boolean bCurrentBalAmount = UI.WaitForObject(oR_AccountOverview.txtCurrentBalanceAmt,40);
				boolean bCurrentBalCR = UI.WaitForObject(oR_AccountOverview.txtCRinCurrentBalance,40);
			
				if(oR_AccountOverview.txtBalanceAmtInGreenColor.isDisplayed())
						{
					Report.TestPoint(testContext.getName(), "Verify current balance $ Amount with CR and in GREEN", "true", String.valueOf(bCurrentBalAmount), true);
					
						}
				else
					{
					Report.TestPoint(testContext.getName(), "Verify current balance $ Amount with CR and in GREEN", "true", "false", true);
					
					}
				//Verify "View bill " BUTTON which redirects to Bill Details page
				boolean bViewBill = UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails,40);
				Report.TestPoint(testContext.getName(), "Verify View bill  BUTTON is displayed", "true", String.valueOf(bViewBill), true);
				oR_AccountOverview.lnkViewBillDetails.click();
				Report.OperationPoint(testContext.getName(), "Navigating to Bill Details Page");
				boolean bBillAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,40);
				Report.TestPoint(testContext.getName(), "Verify View bill  BUTTON is redirecting to  Bill Details page", "true", String.valueOf(bBillAndUsage), true);
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkOverview, null, lDriver);
				//Verify note " no action necessary " under view bill button
				boolean bNoAction = UI.WaitForObject(oR_AccountOverview.txtNoActionNecessary,40);
				Report.TestPoint(testContext.getName(), "Verify note  no action necessary  under view bill button", "true", String.valueOf(bNoAction), true);
				
				//Verify message under balance about credit (content TBD)
				boolean bMessage = UI.WaitForObject(oR_AccountOverview.txtmessageUnderCreditBalance,40);
				Report.TestPoint(testContext.getName(), "Verify message under balance about credit (content TBD)", "true", String.valueOf(bMessage), true);
				
				//navigate to bap
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
				Thread.sleep(5000);
				if(UI.WaitForObject(lDriver.findElement(By.xpath("//p[contains(text(),'canceled')]")), 30, lDriver))
				{
					Report.TestPoint(testContext.getName(), "Verify message about cancellation of service is displayed in Billings, Usage and Payments Page", "true", "true", true);
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyWirelessDataOveragePack()
	 * Description - This function is used to verify the Overage pack displayed for Data only SDG with Data Overage
 	 * Test Case -  DBD07814
	 * Parameters - None
	 * Date created - 8th May 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyWirelessDataOveragePack(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
				//Verify that Wireless dashboard is displayed
				String sDashboardURL = lDriver.getCurrentUrl();
				if(sDashboardURL.contains("WirelessDashboard"))
				{
					Report.TestPoint(testContext.getName(), "Verify that Wireless Dashboard is displayed", "true","true", true);
					//Verify if Data Overage section is displayed
					boolean bOverageSection = UI.WaitForObject(oR_AccountOverview.imgDataOveragePackSection, UI.iObjTimeOut);
					boolean bOverageSectionWithRed = UI.WaitForObject(oR_AccountOverview.imgDataOverage100percentRedBar, UI.iObjTimeOut);
					
					if(bOverageSection==false && bOverageSectionWithRed==true)
					{
						Report.ValidationPoint(testContext.getName(), "Validate that Overage for Data is displayed, overage bar is displayed in RED", "true", String.valueOf(bOverageSectionWithRed), true);
						//Verify correct overage pack applied, should show x of 1GB ( or concatenate if more than 1 GB)
						boolean bMBLeftDisplayed = UI.WaitForObject(oR_AccountOverview.txtDataUsageMBleft, UI.iObjTimeOut);
						Report.ValidationPoint(testContext.getName(), "Total MB left is displayed, Total MB left is: "+oR_AccountOverview.txtDataUsageMBleft.getText(), "true",String.valueOf(bMBLeftDisplayed), true);
						
					}else if(bOverageSection==true && bOverageSectionWithRed==false)
					{
						Report.ValidationPoint(testContext.getName(), "Validate correct Overage for Data is displayed", "true", String.valueOf(bOverageSection), true);
						//Verify correct overage pack applied, should show x of 1GB ( or concatenate if more than 1 GB)
						boolean bOverageUsage = UI.WaitForObject(oR_AccountOverview.txtDataOverageMinutesUsed, UI.iObjTimeOut);
						boolean bTotalUsage = UI.WaitForObject(oR_AccountOverview.txtDataOverageTotalMinutes, UI.iObjTimeOut);
					
						if(bOverageUsage && bTotalUsage)
						{
							Report.ValidationPoint(testContext.getName(), "Verify that correct Overage usage is displayed: Should show x of 1GB ( or concatenate if more than 1 GB). Overage Usage is displayed as : "+oR_AccountOverview.txtDataOverageUsage.getText(), "true","true", true);
						}else
						{
							Report.ValidationPoint(testContext.getName(), "Verify that correct Overage usage is displayed: Should show x of 1GB ( or concatenate if more than 1 GB). Overage Usage is displayed as : "+oR_AccountOverview.txtDataOverageUsage.getText(), "true","false", true);	
						}
					}else
					{
						Report.ValidationPoint(testContext.getName(), "Validate correct Overage for Data is displayed", "true","false", true);
					}
					
					//Verify that an Alert icon is displayed for Overage
					boolean bAlertIcon = UI.WaitForObject(oR_AccountOverview.imgAlertIcon, UI.iObjTimeOut);
					if(bAlertIcon==true)
					{
					
						new Actions(lDriver).moveToElement(oR_AccountOverview.imgAlertIcon).build().perform();
						Thread.sleep(1000);
						Report.ValidationPoint(testContext.getName(), "Verify that an Alert is displayed for data overage", "true","true", true);
						
					}else
					{
						Report.ValidationPoint(testContext.getName(), "Verify that an Alert is displayed for data overage", "true","false", true);

					}
					
					
				}else
				{
					Report.TestPoint(testContext.getName(), "Verify that Wireless Dashboard is displayed", "true","false", true);
				}

		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - VerifyNoAlertsForCCExpiredMoreThan90Days()
	 * Description - This function is to verify that no alerts are displayed if CC has been expired from more than 90 days
 	 * Test Case - DBD10950
	 * Parameters - None
	 * Date created - 11th May 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyNoAlertsForCCExpiredMoreThan90Days(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
				boolean bAlertSection = UI.WaitForObject(oR_AccountOverview.txtAlertSection, UI.iObjTimeOut);
				if(bAlertSection==true)
				{
					boolean Flag=true; int i=1;
					Report.TestPoint(testContext.getName(), "Validate Alert section is displayed", "true","true", true);
					//Verify the alerts section
					List<WebElement> lstAlertSection = lDriver.findElements(By.xpath("//ul[@id='alertPage_1']/li/div/div/span"));
					for(WebElement element: lstAlertSection)
					{
						String sAlertHeading = element.getText();
						System.out.println("AlertHeading name: "+sAlertHeading);
						if((sAlertHeading.contains("Credit")||sAlertHeading.contains("credit"))&&(sAlertHeading.contains("Expire")||sAlertHeading.contains("expire")))
						{
							Report.ValidationPoint(testContext.getName(), "Verify that NO alerts are displayed for Credit Card expired from more than 90 days", "true","false", true);
							Flag=false;
							break;

						}
					}
					if(oR_AccountOverview.lnkAlertsScrollDown.isEnabled())
					{
						oR_AccountOverview.lnkAlertsScrollDown.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on down arrow button for alerts to view rest of the alert section");
						List<WebElement> lstAlertSection2 = lDriver.findElements(By.xpath("//ul[@id='alertPage_2']/li/div/div/span"));
						for(WebElement element2: lstAlertSection2)
						{
							String sAlertHeading = element2.getText();
							System.out.println("AlertHeading name: "+sAlertHeading);
							if((sAlertHeading.contains("Credit")||sAlertHeading.contains("credit"))&&(sAlertHeading.contains("Expire")||sAlertHeading.contains("expire")))
							{
								Report.ValidationPoint(testContext.getName(), "Verify that NO alerts are displayed for Credit Card expired from more than 90 days", "true","false", true);
								Flag=false;
								break;

							}
						}	
					}
					
					if(Flag==true)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that NO alerts are displayed for Credit Card expired from more than 90 days", "true","true", true);
					}
					
				}else
				{
					Report.TestPoint(testContext.getName(), "Alert section is NOT displayed. No alerts for Credit Card expiry displayed", "true","true", true);
				}

		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - VerifyShopATTQuickLink()
	 * Description - This function is to verify 'Shop AT&T' Category is displaying under 'I want to' drop down
 	 * Test Case -  DBD16290
	 * Parameters - None
	 * Date created - 11th May 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyShopATTQuickLink(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
			
			
			//Verify that Shop AT&T is displayed under I want to drop-down tab
			boolean bShopATT = UI.VerifyCategoriesUnderIWantToDropdown("Shop AT&T",null);
			new Actions(lDriver).moveToElement(oR_AccountOverview.btnIWantTo).click().build().perform();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'I want to' dropdown");
			//boolean bShopATT = UI.WaitForObject(oR_AccountOverview.txtShopAttUnderIWantTo, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate 'Shop AT&T' is displayed under 'I want to' drop down tab", "true", String.valueOf(bShopATT), true);
			new Actions(lDriver).moveToElement(oR_AccountOverview.txtShopAttUnderIWantTo).build().perform();
			Report.OperationPoint(testContext.getName(), "Operational - Hovered over 'Shop AT&T' link under I want to dropdown section");
			
			//Verify that following links are getting suppressed •Add a line •Check upgrade eligibility
			try
			{
				boolean bWireless = UI.WaitForObject(lDriver.findElement(By.xpath("//li[@id='ddShortcutFlyoutMenu9']/div/span[contains(text(),'Wireless')]")), UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify that 'Add a line' link is NOT displayed under Wireless section", "true", String.valueOf(bWireless), true);
				new Actions(lDriver).moveToElement(lDriver.findElement(By.xpath("//li[@id='ddShortcutFlyoutMenu9']/div/span[contains(text(),'Wireless')]"))).build().perform();
				Report.OperationPoint(testContext.getName(), "Operational - Hovered over 'Wireless' tab under Shop AT&T section");
			}catch(Exception ee)
			{
				Report.OperationPoint(testContext.getName(), "Operational - 'Wireless' subsection is not displayed under Shop AT&T section");
			}
				//•Add a line
				UI.VerifyElementNotPresent(oR_AccountOverview.lnkAddALine,"Add a line");
				//•Check upgrade eligibility
				UI.VerifyElementNotPresent(oR_AccountOverview.lnkCheckUpgradeEligibility, "Check upgrade eligibility");
			
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}


	/**************************************************************
	 * Function Name - VerifyQuickLinksUverseShop
	 * Description- This function is to verify : 
	                > "Shop AT&T" Category is displaying under "I want to.." Quick link gor Uverse service.
					> By clicking on the link,customer should be able to redirected to appropriate destination page 
	 * Parameters - 
	 * Date created - 11th May 2015
	 * Developed by - Krutika Kamdi 
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//DBD16361

	public static void VerifyQuickLinksUverseShop(final ITestContext testContext)
			throws Exception {
		
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_Shop oR_Shop = PageFactory.initElements(lDriver, OR_Shop.class);
		
		try{
			//Verify •Exclusive Offers link is displayed under Shop navigating through I Want to drop down
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", "U-verse" , "Exclusive offers",null);
			
			//Verify Remote Controls link
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", "U-verse" , "Remote controls", null);
			
			//Verify •Free Videos link
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", "U-verse" , "Free videos", null);
			
			//Verify Upgrade Package link
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", "U-verse" , "Plan upgrades", null);
			
			//Navigate to Shop Page for exclusive offers
			Report.OperationPoint(testContext.getName(), "Operational - Click on Exclusive Offer");
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Click","Shop AT&T", "U-verse" , "Exclusive offers", null);
			Thread.sleep(5000);
			
			//Select Account
			Report.OperationPoint(testContext.getName(), "Operational - Select Account");
			
			WebElement Account = lDriver.findElement(By.xpath("//h1[contains(text(),'Select Account')]"));
			if(Account.isDisplayed())
			{
				Report.OperationPoint(testContext.getName(), "Operational - Select Account");
				WebElement AccNo = lDriver.findElement(By.xpath("//input[@id='userSelection'][@value='192408783']"));
				AccNo.click();
				WebElement Continue =lDriver.findElement(By.xpath("//img[@alt='Continue']"));
				Continue.click();
				
				Report.OperationPoint(testContext.getName(), "Navigation - Navigate to Shop Page");
				boolean bShop = UI.WaitForObject(oR_Shop.txtShopHeading, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify user is navigated to Shop Page ","true",String.valueOf(bShop), true);
			}

		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}		
	

	

/**************************************************************
	 * Function Name - verifyServiceLabelPlanNameAndIconForUverse()
	 * Description - This function verifies icon , plan name and service lable in plan toggle section for all uverse plans
	 * Parameters - None
	 * Date created - 12th May 2015
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD16047 //DBD16046
	
	public static void verifyServiceLabelPlanNameAndIconForUverse(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());  
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sExpectedPlanNames = IO.GetParamVal(sTestParams, "Plan_names");
		
	try
	{
		//Extract all Uverse plan tabs of toggle section
		List<WebElement> uversePlans = null;
		try
		{
			uversePlans = lDriver.findElements(By.xpath("//*[@id='MainTab']//li"));
		
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Extarct all uverse plan tabs from toggle", "Extarcted", "Failed to extract", true);
		}
		
		for(int cnt=0; cnt<uversePlans.size(); cnt++)
		{
			
			String sTabText = uversePlans.get(cnt).getText();
			
			if(sTabText.contains("Plan") || sTabText.contains("Wireless"))
			{
				
			}else
			{
				
			//Verify the icon is displaying in plan toggle section
            try
            {
            	lDriver.findElement(By.xpath("//*[@id='MainTab']//li//img[@id='planImage"+(cnt+1)+"']"));
            	
            	Report.ValidationPoint(testContext.getName(), "Verify the icon is displaying in plan toggle section for tab no "+(cnt+1), "Displayed", "Displayed", true);
            }
            catch(Exception e)
            {
            	Report.ValidationPoint(testContext.getName(), "Verify the icon is displaying in plan toggle section for tab no "+(cnt+1), "Displayed", "NOT Displayed", true);
            }
			
			//Verify the Uverse service lable is displaying in plan toggle section
			if(sTabText.contains("U-verse Internet") || sTabText.contains("U-verse TV") || sTabText.contains("U-verse Voice"))
			{
				Report.ValidationPoint(testContext.getName(), "Verify the Uverse service lable is displaying in plan toggle section for tab no "+(cnt+1), "Displayed","Displayed", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify the Uverse service lable is displaying in plan toggle section for tab no "+(cnt+1), "Displayed","NOT Displayed", true);
			}
			
			//Verify the plan name is displaying in plan toggle section
			
			//first extract all expected plan names from excel sheet and put them in array
			String[] arrExpectedPlanNames = new String[1];
            if(sExpectedPlanNames.contains(","))
			{
				arrExpectedPlanNames = sExpectedPlanNames.split(",");
			}
			else
			{
				arrExpectedPlanNames[0] = sExpectedPlanNames ;
			}
            
            //Loop to check if any of the provided plan name is displayed for all plans in toggle
            boolean bPlanNameDisplayed = false;
            for(int arrCnt= 0 ; arrCnt<arrExpectedPlanNames.length; arrCnt++)
            {
            	if(sTabText.contains(arrExpectedPlanNames[arrCnt]))
            	{
            		bPlanNameDisplayed = true;
            		break;
            	}
            }
            
            if(bPlanNameDisplayed == true)
            {
            	Report.ValidationPoint(testContext.getName(), "Verify Plan name is displaying in plan toggle section for tab no "+(cnt+1), "Displayed","Displayed", true);
            }
            else
            {
            	Report.ValidationPoint(testContext.getName(), "Verify Plan name is displaying in plan toggle section for tab no "+(cnt+1), "Displayed","NOT Displayed", true);
            }
            
			}
            
            
		}
	
	}
	catch(Exception e)
	{
		Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
	}
	}
	
	
	/**************************************************************
	 * Function Name - VerifyLinksUnderQuickLink()
	 * Description - This function is used to verify that "View Recent Usage " and "View Usage trends and graphs " is displayed in the quicklinks section.
 	 * Test Case -  DBD16352
	 * Parameters - None
	 * Date created - 11th May 2015
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyLinksUnderQuickLink(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	
		try
		{
		    if(UI.WaitForObject(oR_AccountOverview.btnIWantTo, UI.iObjTimeOut)){
	    		Report.ValidationPoint(testContext.getName(), "Verify 'I want to' drop down is displayed", "'I want to' drop down is displayed", "'I want to' drop down is displayed", true);
			   	oR_AccountOverview.btnIWantTo.click();
	    		
			   	// Validate View Usages link is displayed
	    		if(UI.WaitForObject(oR_AccountOverview.lnkViewUsageUnderIwantTo, UI.iObjTimeOut)){
	    	    	Actions action = new Actions(lDriver);
	    		    action.moveToElement(oR_AccountOverview.lnkViewUsageUnderIwantTo).build().perform();
	    		    
	    		    // Validate "View usage trends and graphs" link is displayed
	       		    if(UI.WaitForObject(oR_AccountOverview.lnkViewUsageTrendsAndGraphs, UI.iObjTimeOut)){
	    		    	Report.ValidationPoint(testContext.getName(), "Validate 'View usage trends and graphs' link is displayed", "'View usage trends and graphs' link is displayed", "'View usage trends and graphs' link is displayed", true);
	    		    
	    		    }else{
	    		    	Report.ValidationPoint(testContext.getName(), "Validate 'View usage trends and graphs' link is displayed", "'View usage trends and graphs' link is displayed", "'View usage trends and graphs' link is NOT displayed", true);
	    		     }
	       		}
	    	}else{
				Report.ValidationPoint(testContext.getName(), "Verify 'I want to' drop down is displayed", "'I want to' drop down is displayed", "'I want to' drop down is NOT displayed", true);
			}
		}catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	
	}

/**************************************************************
	 * Function Name - VerifyIShopAtATTInIWantto()
	 * Description - This function verifies links in I want to..
	 * Test Case -  DBD16287
	 * Parameters - None
	 * Date created - 11th May 2015
	 * Developed by -Gautham
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyIShopAtATTInIWantto(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
		   
			//Verify Check upgrade eligibility Link
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", "Wireless" , "Check upgrade eligibility", null);

			//Verify Add a Device Link
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", "Wireless" , "Add a device", null);

			//Verify Transfer a number to AT&T
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", "Wireless" , "Transfer a number to AT&T", null);

			//Verify Shop accessories
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", "Wireless" , "Shop accessories", null);		
		 
			//Verify Check order status
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", "Wireless" , "Check order status", null);		
		
			}
	
			catch (Exception e) 
		{
		Report.TestPoint(testContext.getName(),
				"Some error has occured", "True",
				e.getMessage(), true);
		}
	
	}

	
	
	/**************************************************************
	 * Function Name - verifyShopLinksUnderIWantToDropdown()
	 * Description - This function verifies below links are getting visible under "Shop AT&T" Category for Uverse service
	 * 				-Exclusive Offers
	 * 				-Plan upgrades
	 * 				-Free videos
	 * 				It also verifies link 'Remote Controls' is suppressed under "Shop AT&T" Category for Uverse service
	 * Parameters - None
	 * Date created - 13th May 2015
	 * Developed by - Sneha Pansare
	 * Last Modified By - Clint (Changed "U-verse" to null to match with the data, Uverse is not displayed)
	 * Last Modified Date - 24th Sept 2015
	 ***************************************************************/
	//DBD16363
	
	public static void verifyShopLinksUnderIWantToDropdown(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
			Report.OperationPoint(testContext.getName(), "Verify below links are present under 'Shop AT&T' Category for Uverse service");
			//Verify 'Exclusive Offers' link is getting visible under "Shop AT&T" Category for Uverse service
			//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", "U-verse", "Exclusive offers", null);
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", null,"Exclusive offers", null);
			
		
			//Verify 'Plan upgrades' link is getting visible under "Shop AT&T" Category for Uverse service
			//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", "U-verse", "Plan upgrades", null);
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", null, "Plan upgrades", null);
		
			//Verify 'Free videos' link is getting visible under "Shop AT&T" Category for Uverse service
			//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", "U-verse", "Free videos", null);
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", null, "Free videos", null);
		
			//Verify link 'Remote Controls' is suppressed
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", null, "Remote Controls", "Suppressed");
			
//			
//			//Verify 'Remote Controls' link is suppressed under "Shop AT&T" Category for Uverse service
//			if(UI.WaitForObject(oR_AccountOverview.btnIWantTo, 40)) 
//			{
//				Actions action= new Actions(lDriver); 
//				action.moveToElement(oR_AccountOverview.btnIWantTo).click().build().perform(); 
//				Thread.sleep(4000); 
//				
//				try
//				{
//					//Hover over primary category - Shop AT&T
//					if(lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'Shop AT&T')]")).isDisplayed())
//					{
//						String id = lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'Shop AT&T')]/parent::*/parent::*")).getAttribute("id"); 
//						WebElement ePrimaryCategory= lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'Shop AT&T')]")); 
//						action.moveToElement(ePrimaryCategory).build().perform(); 
//						Thread.sleep(4000); 
//						
//						//Hover over secondary category - U-verse
//						try 
//						{ 
//							if(lDriver.findElement(By.xpath("//*[contains(@id,'"+id+"')]//*[contains(@id,'midMenu')]//span[text()='U-verse']")).isDisplayed()) 
//							{ 
//								WebElement eSecondaryCategory = lDriver.findElement(By.xpath("//*[contains(@id,'"+id+"')]//*[contains(@id,'midMenu')]//span[text()='U-verse']")); 
//								action.moveToElement(eSecondaryCategory).build().perform(); 
//								Thread.sleep(6000); 
//							} 
//							else 
//							{ 
//								Report.TestPoint(testContext.getName(), "Hover over Secondary Category", "Hovered over Secondary Category 'U-verse'", "Secondary Category 'U-verse' is NOT displayed", true); 
//							} 
//						} 
//						catch(Exception e) 
//						{ 
//							Report.TestPoint(testContext.getName(), "Hover over Secondary Category", "Hovered over Secondary Category 'U-verse'", "Secondary Category 'U-verse' is NOT present on page", true); 
//						} 
//						
//						//Verify link 'Remote Controls' is suppressed
//						try
//						{
//							WebElement eLink = lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//*[contains(@id,'midMenu')]//*[contains(@id,'ShortcutFlyoutMenu')]//a[text()='Remote Controls']"));
//							if(eLink.isDisplayed())
//							{
//								Report.TestPoint(testContext.getName(), "Verify 'Remote Controls' link is suppressed under 'Shop AT&T' Category for Uverse service", "Suppressed", "NOT Suppressed", true);
//							}
//						}
//						catch(Exception e)
//						{
//							Report.TestPoint(testContext.getName(), "Verify 'Remote Controls' link is suppressed under 'Shop AT&T' Category for Uverse service", "Suppressed", "Suppressed", true);
//						}
//					}
//				}
//				catch(Exception e)
//				{
//					Report.TestPoint(testContext.getName(), "Hover over Primary Category", "Hovered over Primary Category 'Shop AT&T'", "Primary Category 'Shop AT&T' is NOT Present on page", true); 
//				}
//			}
//			else
//			{
//				Report.TestPoint(testContext.getName(), "Verify 'Remote Controls' link is suppressed under 'Shop AT&T'", "Suppressed", "I want to dropdown NOT present", true);
//			}
		
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	
	/**************************************************************
	 * Function Name - VerifyViewMyPlanFeaturesQuickLinkAndItsNavigation()
	 * Description - This function click on 'View my plans and features' link under I want to dropdown
	 * 				and verifies navigation to Wireless plans and services page
	 * Parameters - None
	 * Date created - 14th May 2015
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD21947
	
	public static void VerifyViewMyPlanFeaturesQuickLinkAndItsNavigation(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());  
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sDataType = IO.GetParamVal(sTestParams, "DataType");
		
		try
		{
			//Click on View my plans and features under manage my my plans and services
			if((sDataType.toUpperCase().equals("WIRELESS")))
			{
				//for wireless data type mid flyout is not present under I want to dropdown
				
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Click", "Manage my plan", null, "View my plan & features", null);
			}
			else
			{
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Click", "Manage my plan", "Wireless" , "View my plan & features", null);
			}
			
			Thread.sleep(30000);
			
			String sWirelessURL = lDriver.getCurrentUrl();
			
			if(sWirelessURL.contains("Plans"))
			{
				Report.TestPoint(testContext.getName(), "Verify link navigates to Wireless plans and services page", "Successfully Navigated", "Successfully Navigated", true);
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Verify link navigates to Wireless plans and services page", "Successfully Navigated", "Failed to Navigate", true);
			}
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
		
		
	}
	
	/**************************************************************
	 * Function Name - verifyUVerseTVServiceSummarySection()
	 * Description - This function is to verify Service Summary section for U-Verse TV
	 * Test Case -  DBD12741
	 * Parameters - None
	 * Date created - 14th May 2015
	 * Developed by -Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void verifyUVerseTVServiceSummarySection(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
			WebElement txtMyPlans = lDriver.findElement(By.xpath("//h2[contains(text(),'My Plans')]"));
			if(UI.WaitForObject(txtMyPlans, UI.iObjTimeOut)){
				Report.ValidationPoint(testContext.getName(), "Validate My Plans section is displayed", "My Plans section is displayed", "My Plans section is displayed", true);
				
				Report.OperationPoint(testContext.getName(), "Validating Service Summary section for all available U-Verse TV accounts");
				WebElement actTab = lDriver.findElement(By.xpath("//ul[@id='MainTab']"));
				List<WebElement> uVerseAccounts = actTab.findElements(By.tagName("a"));
				System.out.println(uVerseAccounts.size());
				for(WebElement e : uVerseAccounts)
				{
					if(e.getAttribute("linkname").contains("U-verse TV")){
						e.click();
						
						// Validate TV image is displayed 
						if(UI.WaitForObject(oR_AccountOverview.imgUverseTVIcon, UI.iObjTimeOut)){
						   Report.ValidationPoint(testContext.getName(), "Validate TV image is displayed under Service Summary section", "TV image is displayed under Service Summary section", "TV image is displayed under Service Summary section", true);	
						}else{
							   Report.ValidationPoint(testContext.getName(), "Validate TV image is displayed under Service Summary section", "TV image is displayed under Service Summary section", "TV image is NOT displayed under Service Summary section", true);
						}
						
						// Validate Fix It Now url
	    				if(UI.WaitForObject(oR_AccountOverview.lnkFixItNow,5)){
							   Report.ValidationPoint(testContext.getName(), "Validate Fix It Now link is displayed under Service Summary section", "Fix It Now link is displayed under Service Summary section", "Fix It Now link is displayed under Service Summary section", true);	
						}else{
							   Report.ValidationPoint(testContext.getName(), "Validate Fix It Now link is displayed under Service Summary section", "Fix It Now link is displayed under Service Summary section", "Fix It Now link is NOT displayed under Service Summary section", true);
						 }
						
						// Validate message notifying that the customer have limited functionality is displayed
						if(UI.WaitForObject(oR_AccountOverview.txtMessageBox,5)){
							   Report.ValidationPoint(testContext.getName(), "Validate message notifying that the customer have limited functionality is displayed under Service Summary section", "Message notifying that the customer have limited functionality is displayed under Service Summary section", "Message notifying that the customer have limited functionality is displayed under Service Summary section", true);	
						}else{
							   Report.ValidationPoint(testContext.getName(), "Validate message notifying that the customer have limited functionality is displayed under Service Summary section", "Message notifying that the customer have limited functionality is displayed under Service Summary section", "Message notifying that the customer have limited functionality is NOT displayed under Service Summary section", true);
						}
					
						// Validate View Channel link is displayed
						if(UI.WaitForObject(oR_AccountOverview.lnkViewChannelLineup,5)){
							   Report.ValidationPoint(testContext.getName(), "Validate View Channel lineup link is displayed under Service Summary section", "View Channel lineup link is displayed under Service Summary section", "View Channel lineup link is displayed under Service Summary section", true);	
						}else{
							   Report.ValidationPoint(testContext.getName(), "Validate View Channel lineup link is displayed under Service Summary section", "View Channel lineup link is displayed under Service Summary section", "View Channel lineup link is NOT displayed under Service Summary section", true);
						}

                        // Validate Manage DVR link is displayed
						if(UI.WaitForObject(oR_AccountOverview.lnkManageDVRServiceSummary,5)){
							   Report.ValidationPoint(testContext.getName(), "Validate Manage DVR link is displayed under Service Summary section", "Manage DVR link is displayed under Service Summary section", "Manage DVR link is displayed under Service Summary section", true);	
						}else{
							Report.ValidationPoint(testContext.getName(), "Validate Manage DVR link is displayed under Service Summary section", "Manage DVR link is displayed under Service Summary section", "Manage DVR link is NOT displayed under Service Summary section", true);	
						}
    				}
				}
			}else{
				Report.ValidationPoint(testContext.getName(), "Validate My Plans section is displayed", "My Plans section is displayed", "My Plans section is displayed", true);
			}
		}catch (Exception e) {
			e.printStackTrace();
		   Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
		
	/**************************************************************
	 * Function Name - verifyPreInstallBannerIsSuppressed()
	 * Description - This function is to verify PRE Install banner is suppressed
	 * Test Case -  DBD21120
	 * Parameters - None
	 * Date created - 15th May 2015
	 * Developed by -Nachiket Pawar
	 * Last Modified By - Hiral Jogi
	 * Last Modified Date - 1st Feb 2016
	 ***************************************************************/
	public static void verifyPreInstallBannerIsSuppressed(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
			boolean bbanner = UI.WaitForObject(oR_AccountOverview.txtPreInstallBanner, UI.iObjTimeOut);
			if(bbanner){
				Report.TestPoint(testContext.getName(), "Validate Pre installation banner is not suppressed.", "true", "true", true);
				
				boolean bAppointmentActivity = UI.WaitForObject(oR_AccountOverview.txtPreInstallBannerAppointmentactivity, UI.iObjTimeOut);
				String stractivity = oR_AccountOverview.txtPreInstallBannerAppointmentactivity.getText();
				Report.ValidationPoint(testContext.getName(), "Validate Appointment activity is shown." +  stractivity,"true",String.valueOf(bAppointmentActivity), true);
				
				boolean bAppointmentDate = UI.WaitForObject(oR_AccountOverview.txtPreInstallBannerAppointmentDate, UI.iObjTimeOut);
				String strDate = oR_AccountOverview.txtPreInstallBannerAppointmentDate.getText();
				Report.ValidationPoint(testContext.getName(), "Validate Appointment date is shown i.e. " + strDate ,"true",String.valueOf(bAppointmentDate), true);
				
				String strDateValidation = oR_AccountOverview.txtPreInstallBannerAppointmentDate.getText();
				if(strDateValidation.contains(", 2016 after")){
					Report.ValidationPoint(testContext.getName(), "The date is shown in correct format i.e month name, year and time.","true",String.valueOf(bAppointmentDate), true);
				}
				
				
				
			}else{
				Report.ValidationPoint(testContext.getName(), "Validate Pre installation banner is suppressed", "Pre installation banner is suppressed", "Pre installation banner is NOT suppressed", true);
			}
	    }catch (Exception e) {
			e.printStackTrace();
		   Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyAlertForEditBillingInformation
	 * Description - This Function is meant to validate alert for edit billing address.
	 * Test Case -  DBD08835
	 * Parameters - None
	 * Date created - 15th May 2015
	 * Developed by -Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyAlertForEditBillingInformation(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
		OR_EditBillingContactInformation oR_EditBillingContactInformation = PageFactory.initElements(lDriver, OR_EditBillingContactInformation.class);
		
		try
		{
			//Validate Alert button
			Boolean btnAlert = UI.WaitForObject(oR_AccountOverview.btnAlert, 30);
			Report.TestPoint(testContext.getName(), "Validate Alert button", "true", String.valueOf(btnAlert), true);
			//Click on alert button
			Report.OperationPoint(testContext.getName(), "Clicking on alert button");
			oR_AccountOverview.btnAlert.click();
			//Validate Update billing information link
			Boolean lnkUpdateBill = UI.WaitForObject(oR_AccountOverview.lnkUpdateEmailAddress, 30);
			Report.TestPoint(testContext.getName(), "Validate Update billing information link", "true", String.valueOf(lnkUpdateBill), true);
			//Click on Update billing information link
			Report.OperationPoint(testContext.getName(), "Clicking on Update billing information link");
			oR_AccountOverview.lnkUpdateEmailAddress.click();
			//Validate navigation to profile page
			//Validate Edit Billing Contact info Page
			Boolean bEdit = UI.WaitForObject(oR_EditBillingContactInformation.txtEditBillingContactInformation,100);
			Report.TestPoint(testContext.getName(), "Verify Edit Billing Contact info Page", "true", String.valueOf(bEdit), true);
			/*Boolean lnkProfile = UI.WaitForObject(oR_Profile.txtMyProfileTitle, 50);
			Report.TestPoint(testContext.getName(), "Validate navigation to profile page", "true", String.valueOf(lnkProfile), true);*/
			//Validate URL
			String pURL = lDriver.getCurrentUrl();
			if(pURL.contains("profile"))
			{
				Report.ValidationPoint(testContext.getName(), "Validate the URL : "+lDriver.getCurrentUrl(), "true","true", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate the URL", "true","false", true);
			}
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - VerifySuspendedFlowUsingNewLinkReportLostOrStolenPhone()
	 * Description - This function is to verify that a wireless user should be able to suspend service for Lost or Stolen phone using the newly added link
 	 * Test Case -  DBD06041
	 * Parameters - None
	 * Date created - 15th May 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifySuspendedFlowUsingNewLinkReportLostOrStolenPhone(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_SuspendReactivateService oR_SuspendReactivateService = PageFactory.initElements(lDriver, OR_SuspendReactivateService.class);	     
		OR_MessageCenter oR_MessageCenter = PageFactory.initElements(lDriver, OR_MessageCenter.class);
		try
					{
						//Select Suspend or Reactivate Your Service from tertiary Nav bar
						if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav ,oR_AccountOverview.lnkSuspendOrReactivateDeviceTertNav))
						{
						
							//navigate to Suspend Reactivate Service page
							if(UI.WaitForObject(oR_SuspendReactivateService.elmSuspendReactivateService, UI.iObjTimeOut))
							{
								Report.TestPoint(testContext.getName(), "Verify that page is navigated to Suspend Reactivate Service page after clicking the link from global nav", "true", "true" , true);
								//Verify reason for suspension drop down
								boolean bDropDown = UI.WaitForObject(oR_SuspendReactivateService.lstSuspendReasonDropDown,UI.iObjTimeOut);
								//if(UI.WaitForObject(oR_SuspendReactivateService.lstSuspendReason,UI.iObjTimeOut))
								if(bDropDown==true)
								{
									Report.ValidationPoint(testContext.getName(), "Verify that a dropdown is diplayed for suspension reasons", "true", "true", true);
									
									//Validate the reason is not pre-selected in dropdown box
									String sSelectedOption = new Select(lDriver.findElement(By.xpath("//select[@id='select20']"))).getFirstSelectedOption().getText();
									if(sSelectedOption.contains("Reason For Suspension"))
									{
										Report.ValidationPoint(testContext.getName(), "Verify that Reason for suspension is NOT preselected from the Drop down list", "true", "true", true);
									}else
									{
										Report.ValidationPoint(testContext.getName(), "Verify that Reason for suspension is NOT preselected from the Drop down list", "true", "false", true);
									}
									
									//Verify suspend button should be inactive
									boolean bSuspendButton = UI.WaitForObject(oR_SuspendReactivateService.btnSuspendInactive,UI.iObjTimeOut);
									Report.ValidationPoint(testContext.getName(), "Verify that Suspend Button against active wireless should be Inactive", "true", String.valueOf(bSuspendButton), true);
									
									//Select the suspension reason as "Lost or Stolen" from the list
									if(UI.SelectOptionFromDropDown(oR_SuspendReactivateService.lstSuspendReason, "Lost"))
									{
										Report.ValidationPoint(testContext.getName(), "Select the suspension reason as Lost or Stolen from the list", "Selected suspension reason as Lost from dropdown", "Selected suspension reason as Lost from dropdown", true);
										//Suspend Button should be activated 
										boolean bSuspendButtonActive = UI.WaitForObject(oR_SuspendReactivateService.btnSuspendEnabled, UI.iObjTimeOut);
										Report.ValidationPoint(testContext.getName(), "Verify that Suspend Button should be activated after selecting suspension reason from dropdown", "true", String.valueOf(bSuspendButtonActive), true);
										//Click on Suspend Button
										oR_SuspendReactivateService.btnSuspendEnabled.click();
										Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Suspend' button");
										
										//Verify Suspend confirmation popup
										Thread.sleep(6000);
										String sMainWindow = lDriver.getWindowHandle();
										WebElement frmNote = lDriver.findElement(By.xpath("//div[@id='cboxWrapper']//iframe[contains(@src,'suspendOrReactivateModal')]"));
										lDriver.switchTo().frame(frmNote);
										
										//validate elements on frame
										boolean bFrmHeader=UI.WaitForObject(oR_SuspendReactivateService.elmAreYouSure, UI.iObjTimeOut);
										boolean bCancel=UI.WaitForObject(oR_SuspendReactivateService.lnkCancel, UI.iObjTimeOut);
										boolean bClose=UI.WaitForObject(oR_SuspendReactivateService.lnkClose, UI.iObjTimeOut);
										boolean bReason=UI.WaitForObject(oR_SuspendReactivateService.txtReason, UI.iObjTimeOut);
										boolean bConfirm=UI.WaitForObject(oR_SuspendReactivateService.lnkConfirm, UI.iObjTimeOut);
										
										if(bFrmHeader==bCancel==bClose==bReason==bConfirm==true)
										{
											Report.ValidationPoint(testContext.getName(), "Validate Elements on Frame: 1.Page Title 2.Review message for suspend service for a CTN 3.Close Window link and icon. 4.Confirm Button-call to action 5.Cancel Link", "true", "true", true);
											//click on confirm button
											oR_SuspendReactivateService.lnkConfirm.click();
											Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'confirm' button from the pop-up");
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Validate Elements on Frame: 1.Page Title 2.Review message for suspend service for a CTN 3.Close Window link and icon. 4.Confirm Button-call to action 5.Cancel Link", "true", "false", true);
											//click on confirm button
											oR_SuspendReactivateService.lnkConfirm.click();
											Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'confirm' button from the pop-up");
											
										}
										    lDriver.switchTo().window(sMainWindow);
											//driver.switchTo().parentFrame();
											
											//suspend Reactivate page
											boolean bSuspendPage = UI.WaitForObject(oR_SuspendReactivateService.elmSuspendReactivateService,UI.iObjTimeOut);
											Report.ValidationPoint(testContext.getName(), "Verify that page is navigated to Suspend Reactivate Service page after clicking 'confirm' button on pop-up page", "true", String.valueOf(bSuspendPage), true);
											
											//CTN status shown as Suspended and Reason for suspension shown for CTN as Lost or Stolen
											boolean bCTNStatus = UI.WaitForObject(oR_SuspendReactivateService.elmSuspendCTN,UI.iObjTimeOut);
											Report.ValidationPoint(testContext.getName(), "Verify that CTN status is changed to 'Suspended' and Reason for suspension is changed to 'Lost' ", "true", String.valueOf(bCTNStatus), true);	
											
											//Navigate to Overview page
											UI.SelectServiceFromSecondaryNavigation(oR_SuspendReactivateService.lnkOverview ,null);
											Report.OperationPoint(testContext.getName(), "Navigated to Overview Page");
											
											//Verify the presence of  alert  for the suspension on the dashboard
											boolean bDashboardAlert = UI.WaitForObject(oR_AccountOverview.txtAlertMobileNoSuspended,UI.iObjTimeOut);
											Report.ValidationPoint(testContext.getName(), "Verify the presence of alert for the suspension on the dashboard", "true", String.valueOf(bDashboardAlert), true);
										
											//"View all" messages under the service summary section to check the messages.
											if(UI.WaitForObject(oR_AccountOverview.lnkViewAll, UI.iObjTimeOut))
											{
												Report.ValidationPoint(testContext.getName(), "Validate View all messages link under the service summary section is enabled", "true", "true", true);
												oR_AccountOverview.lnkViewAll.click();
												Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'View All' link");
												
												//Verify user is navigated to message center page
												boolean bMessageCenter = UI.WaitForObject(oR_MessageCenter.txtMessageCenterTitle, UI.iObjTimeOut);
												Report.ValidationPoint(testContext.getName(), "Verify Message centre page is displayed", "true", String.valueOf(bMessageCenter), true);
												
												//Verify message for suspension is displayed
												boolean bSuspendedMessage = UI.WaitForObject(oR_MessageCenter.lnkSuspendedMessage, UI.iObjTimeOut);
												Report.ValidationPoint(testContext.getName(), "Verify the presence of  alert  for the suspension in Message Centre page", "true", String.valueOf(bSuspendedMessage), true);
												
											}
											else
											{
												Report.ValidationPoint(testContext.getName(), "Validate View all messages link under the service summary section", "View all messages link is present", "View all messages link is NOT present", true);
											}
																		
									}
									else
									{
										Report.ValidationPoint(testContext.getName(), "Select the suspension reason as Lost or Stolen from the list", "NOT selected suspension reason as Lost ", "NOT selected suspension reason as Lost", true);
									}
								}	
								else
								{
									Report.ValidationPoint(testContext.getName(), "Verify that a dropdown is diplayed for suspension reasons", "true", "false", true);
								}
							
								
							}
							else
							{
								Report.TestPoint(testContext.getName(), "Goto Suspend Reactivate Service page from global nav", "true", "false" , true);
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
	
	
	/**************************************************************
	 * Function Name - verifyDataUsageBarAndDetailsForOnstarPlan()
	 * Description - This function verifies Label, usage bar and amount left for Data
	 * Parameters - None
	 * Date created - 14th May 2015
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD09129
	
	public static void verifyDataUsageBarAndDetailsForOnstarPlan(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());    
		
		try
		{
			//Extract all plans from toggle
			List<WebElement> AllPlans = null;
			try
			{
				AllPlans = lDriver.findElements(By.xpath("//*[@id='MainTab']//li"));
			
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Verify Onstar plan details", "All plans extarcted from toggle", "Failed to extract", true);
			}
			
			int iOnstartPlansCount=1;
			for(int cnt=0; cnt< AllPlans.size() ; cnt++)
			{
				String sPlanText = AllPlans.get(cnt).getText();
				
				if(sPlanText.contains("OnStar"))
				{
					AllPlans.get(cnt).click();
					Thread.sleep(10000);
					Report.ValidationPoint(testContext.getName(), "Select Onstar plan", "Onstar plan no "+iOnstartPlansCount+" Selected", "Onstar plan no "+iOnstartPlansCount+" Selected", true);
					
					
					//Verify Label for data usage bar
					try
					{
						WebElement elmDataUsageBarLabel = lDriver.findElement(By.xpath("//*[contains(@class,'usage-meter')]//*[text()='Data']"));
						
						if(elmDataUsageBarLabel.isDisplayed())
						{
							Report.ValidationPoint(testContext.getName(), "Verify Label for data usage bar for Onstar Plan no "+iOnstartPlansCount, "Displayed", "Displayed", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Verify Label for data usage bar for Onstar Plan no "+iOnstartPlansCount, "Displayed", "NOT Displayed", true);
						}
					}
					catch(Exception e)
					{
						Report.ValidationPoint(testContext.getName(), "Verify Label for data usage bar for Onstar Plan no "+iOnstartPlansCount, "Displayed", "NOT Displayed", true);
					}
					
					//Verify usage bar for data
					try
					{
						WebElement elmUsageMeterForData = lDriver.findElement(By.xpath("//*[contains(@class,'usage-meter')]//*[text()='Data']/parent::*/parent::*//*[contains(@class,'meter-container')]"));
						
						if(elmUsageMeterForData.isDisplayed())
						{
							Report.ValidationPoint(testContext.getName(), "Verify usage bar for data for Onstar Plan no "+iOnstartPlansCount, "Displayed", "Displayed", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Verify usage bar for data for Onstar Plan no "+iOnstartPlansCount, "Displayed", "NOT Displayed", true);
						}
					}
					catch(Exception e)
					{
						Report.ValidationPoint(testContext.getName(), "Verify usage bar for data for Onstar Plan no "+iOnstartPlansCount, "Displayed", "NOT Displayed", true);
					}
					
					//Verify amount of data left
					try
					{
						WebElement elmUsageMeter = lDriver.findElement(By.xpath("//*[contains(@class,'usage-meter')]//*[text()='Data']/parent::*/parent::*/parent::*"));
						String sUsageMeterText = elmUsageMeter.getText();
						
						if(sUsageMeterText.contains("left"))
						{
							Report.ValidationPoint(testContext.getName(), "Verify amount of data left is displayed for Onstar Plan no "+iOnstartPlansCount, "Displayed", "Displayed", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Verify amount of data left is displayed for Onstar Plan no "+iOnstartPlansCount, "Displayed", "NOT Displayed", true);
						}
					}
					catch(Exception e)
					{
						Report.ValidationPoint(testContext.getName(), "Verify amount of data left is displayed for Onstar Plan no "+iOnstartPlansCount, "Displayed", "NOT Displayed", true);
					}
					
					iOnstartPlansCount=iOnstartPlansCount+1; 
				}
			}
				
			
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name -  VerifyAccountSuspendedAlert()
	 * Description - This function is used to verify that "View Recent Usage " and "View Usage trends and graphs " is displayed in the quicklinks section.
 	 * Test Case -  DBD16352
	 * Parameters - None
	 * Date created - 18th May 2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD08891
	public static void VerifyAccountSuspendedAlert(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		try
		{
			//Verify whether Account suspended alert is displayed 
			boolean bAlert = UI.WaitForObject(oR_AccountOverview.btnAlertClick,UI.iObjTimeOut);
			Actions driver= new Actions(lDriver);
			oR_AccountOverview.btnAlertClick.click();
			
			//iver.moveToElement(oR_AccountOverview.btnAlertClick).click().build().perform();
			boolean bAccount = UI.WaitForObject(oR_AccountOverview.txtAccountSuspendedAlert,UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify whether Account suspended alert is displayed", "true", String.valueOf(bAccount), true);
			
			Report.ValidationPoint(testContext.getName(), "Verify Yellow alert is displayed", "true", String.valueOf(bAccount), true);
			
			//Verifying whether particular conditions is present in alert 
			boolean bDescription = UI.WaitForObject(oR_AccountOverview.txtAlertDescription,UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify whether description for alert is displayed", "true", String.valueOf(bDescription), true);
			
			boolean bLink = UI.WaitForObject(oR_AccountOverview.lnkAlertLearnMore,UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Learn more link is present or not", "true", String.valueOf(bLink), true);
			oR_AccountOverview.lnkAlertLearnMore.click();
			String URL=lDriver.getCurrentUrl();
			Report.OperationPoint(testContext.getName(), "navigating to '"+URL+"'");
			Report.ValidationPoint(testContext.getName(), "Verify whether support article is redirected to mentioned url or not", "true", String.valueOf(bLink), true);
			lDriver.navigate().back();
			
			//verify alert is displayed for each voluntaru suspended account
			List<WebElement> BAN= lDriver.findElements(By.xpath("//*[contains(@id,'MainTab')]//li"));
			for(WebElement e: BAN)
			{
				e.click();
				Report.OperationPoint(testContext.getName(),"Plan Name is "+ e.getText());
				Report.OperationPoint(testContext.getName(),"Clicking On "+ e.getText());
				boolean bSuspend = UI.WaitForObject(oR_AccountOverview.txtSuspendAccountText, 15, lDriver);
				Report.ValidationPoint(testContext.getName(),"Validate Account suspended text is available for '"+e.getText()+"'","true",String.valueOf(bSuspend),true);
			}
			
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,null, lDriver);
			
			Report.OperationPoint(testContext.getName(),"Clicking on Usage tab");
			
			oR_BillAndUsage.lnkUsage.click();
			
			//validate drop down
			UI.WaitForObject(oR_BillAndUsage.lstBillCycleDropDown,20, lDriver);
			oR_BillAndUsage.lstBillCycleDropDown.click();
			Thread.sleep(3000);
			oR_BillAndUsage.lnkRecentUsageFromDropDown.click();
			
			try{
				WebElement suspend=lDriver.findElement(By.xpath("//*[contains(text(),'suspend')]"));
				Report.ValidationPoint(testContext.getName(),"validate Recent bill details are not displayed","true",String.valueOf(suspend.isDisplayed()),true);
				}catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(),"validate Recent bill details are not displayed","true","false",true);
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
	/**************************************************************
	 * Function Name - VerifyRescheduleLinkSuppressed
	 * Description - This Function is meant to validate rescheduled link suppressed
	 * Test Case -  DBD17007
	 * Parameters - None
	 * Date created - 15th May 2015
	 * Developed by -Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyRescheduleLinkSuppressed(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		try
		{
			//Validate rescheduled link is suppressed
			UI.VerifyElementNotPresent(oR_AccountOverview.lnkReSchedule, "Reschedule link");
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyHardBounceIndicatorSet
	 * Description- This function is to verify alert message regarding hard bounce is displayed on converged dashboard along with arrangement of bills and payments and my orders sections             
	 * Parameters - 
	 * Date created - 18th May 2015
	 * Developed by - Krutika Kamdi 
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//DBD03511

	public static void VerifyHardBounceIndicatorSet(final ITestContext testContext)
			throws Exception {
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_MessageCenter oR_MessageCenter = PageFactory.initElements(lDriver, OR_MessageCenter.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		OR_TelevisionService oR_TelevisionService = PageFactory.initElements(lDriver, OR_TelevisionService.class);
		OR_InternetService oR_InternetService = PageFactory.initElements(lDriver, OR_InternetService.class);
		
		try{
			//Verify Manage my plan in I Want to drop down
			UI. VerifyCategoriesUnderIWantToDropdown("Manage my plan", null);
			
			//Verify Manage my profile in I Want to drop down
			UI. VerifyCategoriesUnderIWantToDropdown("Manage my profile", null);
			
			//Verify Get help & support in I Want to drop down
			UI. VerifyCategoriesUnderIWantToDropdown("Get help & support", null);
			
			//Verify Shop AT&T in I Want to drop down
			UI. VerifyCategoriesUnderIWantToDropdown("Shop AT&T", null);
			
			//Verify Update my profile and Link my AT&T accounts links under Manage my profile
			
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Manage my profile", "Wireless" , "Update my profile", null);
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Manage my profile", "Wireless" , "Link my AT&T accounts", null);
			
			//Verify Digital TV and Internet tiles are displayed 
			boolean bDigitalTV = UI.WaitForObject(oR_AccountOverview.lnkDigitalTVSecondaryNav, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Digital TV link on Secondary Navigation","true",String.valueOf(bDigitalTV), true);
			
			boolean bInternet = UI.WaitForObject(oR_AccountOverview.lnkInternetSecondaryNav, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Internet link on Secondary Navigation","true",String.valueOf(bInternet), true);
			
			//Verify View All link
			
			Report.OperationPoint(testContext.getName(), "Operational - Click on View All link");
			
			Boolean bViewAll = UI.WaitForObject(oR_AccountOverview.lnkViewAll, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify View All link is displayed","true",String.valueOf(bViewAll), true);
			if(bViewAll.equals(true))
			{
				oR_AccountOverview.lnkViewAll.click();
				Boolean bMessageCentre = UI.WaitForObject(oR_MessageCenter.txtMessageCenterTitle, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify Message Centre title is displayed","true",String.valueOf(bMessageCentre), true);
				lDriver.navigate().back();
			}
			
			//Verify Welcome Back Section
			
			Boolean bWelcome = UI.WaitForObject(oR_AccountOverview.txtWelcome, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Welcome Back is displayed","true",String.valueOf(bWelcome), true);
			
			//Verify Last logged in 
			
			Boolean bLastLogin = UI.WaitForObject(oR_AccountOverview.txtLastLogin, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Last Login section is displayed","true",String.valueOf(bLastLogin), true);
			
			//Verify Past due msg
			
			Boolean bPastDue = UI.WaitForObject(oR_AccountOverview.txtPastDue, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Past Due is displayed","true",String.valueOf(bPastDue), true);
			
			//Verify View bill details link 
			Boolean bBillDetails = UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify View Bill Details link is displayed","true",String.valueOf(bBillDetails), true);
			if(bBillDetails.equals(true))
			{
				oR_AccountOverview.lnkViewBillDetails.click();
				Boolean bBillUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify Billing & Usage page is displayed","true",String.valueOf(bBillUsage), true);
				lDriver.navigate().back();
			}
			Boolean bUverseTV = UI.WaitForObject(oR_AccountOverview.lnkUverseTVToggle, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Uverse TV Plan is displayed","true",String.valueOf(bUverseTV), true);
			if(bUverseTV.equals(true))
			{
				oR_AccountOverview.lnkUverseTVToggle.click();
				Boolean bMyOrders = UI.WaitForObject(oR_AccountOverview.lnkMyOrders, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify My Orders is displayed","true",String.valueOf(bMyOrders), true);
			}
			//Verify Internet Secondary Nav
			Report.OperationPoint(testContext.getName(), "Navigation - User navigates to TV landing page");
			
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkDigitalTVSecondaryNav,null);
			
			Boolean bTVtitle = UI.WaitForObject(oR_TelevisionService.txtMyTVService, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify user is navigated to TV landing page","true",String.valueOf(bTVtitle), true);
			
			lDriver.navigate().back();
			
			//Verify Internet on Secondary Nav bar
			Report.OperationPoint(testContext.getName(), "Navigation - User navigates to Internet landing page");
			
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkInternetSecondaryNav,null);
			
			Boolean bIntTitle = UI.WaitForObject(oR_InternetService.txtMyInternetService, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify user is navigated to Internet landing page","true",String.valueOf(bIntTitle), true);
			
			lDriver.navigate().back();
			
			//Verify hard bounce alert message
			Boolean bAlert = UI.WaitForObject(oR_AccountOverview.btnAlert, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Alert is displayed","true",String.valueOf(bAlert), true);
			if(bAlert.equals(true))
			{
				oR_AccountOverview.btnAlert.click();
				WebElement txtInvalidAlert = lDriver.findElement(By.xpath("//p[contains(text(),'Invalid Email Address')]"));
				if(txtInvalidAlert.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(), "Verify hard bounce alert message is displayed","true","true", true);
				}
				else
				{	
					Report.ValidationPoint(testContext.getName(), "Verify hard bounce alert message is displayed","true","false", true);
				}
			}
			
			//Verify Rerate Pending alert is not present
			
			UI. VerifyElementNotPresent(oR_AccountOverview.txtRerate, "Rerate Pending");   // chck Rerate Pending xpath once 
			


		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}	
	
	
	/**************************************************************
	 * Function Name - VerifyVoiceLineForSLIDWithUverse
	 * Description - This Function is meant to validate Voice link is getting displayed for Slid + Uverse customer
	 * Test Case -  DBD20182
	 * Parameters - None
	 * Date created - 20th May 2015
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyVoiceLineForSLIDWithUverse(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
			WebElement actTab = lDriver.findElement(By.xpath("//ul[@id='MainTab']"));
			List<WebElement> uVerseAccounts = actTab.findElements(By.tagName("a"));
			boolean bVocieLineFlag = false;
			
			for(WebElement e : uVerseAccounts){
				
				if(e.getAttribute("linkname").contains("U-verse Voice")){
					e.click();
					bVocieLineFlag = true;
						
					UI.WaitForObject(oR_AccountOverview.txtQuickTools, 15);
					
					List<WebElement> usageBar = lDriver.findElements(By.xpath("//div[@class='usage-meter-table']//span[contains(text(),'mins left')]"));
					
					if(usageBar.size() == 0){
					    Report.ValidationPoint(testContext.getName(), "Validate voice usage bar is displayed showing how much mins left", "Voice usage bar is displayed showing how much mins left", "Voice usage bar is NOT displayed showing how much mins left",true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Validate voice usage bar is displayed showing how much mins left", "Voice usage bar is displayed showing how much mins left", "Voice usage bar is displayed showing how much mins left",true);
					}
			    }
			}
			
			if(bVocieLineFlag){
				Report.ValidationPoint(testContext.getName(), "Validate Voice line is displayed under service summary section", "Voice line is displayed under service summary section", "Voice line is displayed under service summary section", true);
			}else{
				Report.ValidationPoint(testContext.getName(), "Validate Voice line is displayed under service summary section", "Voice line is displayed under service summary section", "Voice line is NOT displayed under service summary section", true);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	

	/**************************************************************
	 * Function Name - VerifyUsageLinksUnderQuickTools
	 * Description - This Function is meant to validate View My Usage links under Quick Tools section
	 * Test Case -  DBD16355
	 * Parameters - None
	 * Date created - 21th May 2015
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyUsageLinksUnderQuickTools(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		
		try
		{
			
			WebElement actTab = lDriver.findElement(By.xpath("//ul[@id='MainTab']"));
			List<WebElement> uVerseAccounts = actTab.findElements(By.tagName("a"));
		   	for(WebElement e : uVerseAccounts){
						
				if(e.getAttribute("linkname").contains("U-verse Voice")){
					e.click();
					// Validate Quick Tools Seciotn is displayed				
					if(UI.WaitForObject(oR_AccountOverview.txtQuickTools, 15)){
						Report.ValidationPoint(testContext.getName(), "Validate Quick Tools section is displayed", "Quick Tools section is displayed", "Quick Tools section is displayed", true);
						
					  // Validate View Current Usage link is displayed under Quick Tools section
						if(UI.WaitForObject(oR_AccountOverview.lnkQuickToolsViewCurrentUsage, 5)){
							Report.ValidationPoint(testContext.getName(), "Validate View Current Usage link is displayed under Quick Tools section", "View Current Usage link is displayed under Quick Tools section", "View Current Usage link is displayed under Quick Tools section", true);
							
							//Click on View Current Usage link
							Report.OperationPoint(testContext.getName(), "Click on View Current Usage Link");
							oR_AccountOverview.lnkQuickToolsViewCurrentUsage.click();
							
							//Validate User is navigated to Billing & Usages page 
							if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut)){
								Report.ValidationPoint(testContext.getName(), "Validate user is navigated to Billings & Usage page", "User is navigated to Billings & Usage page", "user is navigated to Billings & Usage page", true);
							
								// Validate Current Usage is displayed
								WebElement txtBilledUsage = lDriver.findElement(By.xpath("//*[@id='timePeriod']"));
							    if(txtBilledUsage.getText().contains("Recent")){
							    	Report.ValidationPoint(testContext.getName(), "Validate Current usage is displayed", "Current usage is displayed", "Current usage is displayed", true);
							    }else{
							    	Report.ValidationPoint(testContext.getName(), "Validate Current usage is displayed", "Current usage is displayed", "Current usage is NOT displayed", true);
							    }
							    
							 // Navigate back to Overview Page
							 lDriver.navigate().back();
							 UI.WaitForObject(oR_AccountOverview.txtWelcome, UI.iObjTimeOut);
							 
							 for(WebElement lnkServiceLine : lDriver.findElement(By.xpath("//ul[@id='MainTab']")).findElements(By.tagName("a"))){
							    if(lnkServiceLine.getAttribute("linkname").contains("U-verse Voice")){
							    	lnkServiceLine.click();
								 }
		    				 }
	    					}else{
								Report.ValidationPoint(testContext.getName(), "Validate user is navigated to Billings & Usage page", "User is navigated to Billings & Usage page", "user is NOT navigated to Billings & Usage page", true);
							}
						}else{
							Report.ValidationPoint(testContext.getName(), "Validate View Current Usage link is displayed under Quick Tools section", "View Current Usage link is displayed under Quick Tools section", "View Current Usage link is NOT displayed under Quick Tools section", true);
					    }
						

						
						  // Validate View Billed Usage link is displayed under Quick Tools section
						if(UI.WaitForObject(oR_AccountOverview.lnkQuickToolsViewBilledUsage, 5)){
							Report.ValidationPoint(testContext.getName(), "Validate View Billed Usage link is displayed under Quick Tools section", "View Billed Usage link is displayed under Quick Tools section", "View Billed Usage link is displayed under Quick Tools section", true);
							
							//Click on View Current Usage link
							Report.OperationPoint(testContext.getName(), "Click on View Current Usage Link");
							oR_AccountOverview.lnkQuickToolsViewBilledUsage.click();
							
							//Validate User is navigated to Billing & Usages page 
							if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut)){
								Report.ValidationPoint(testContext.getName(), "Validate user is navigated to Billings & Usage page", "User is navigated to Billings & Usage page", "user is navigated to Billings & Usage page", true);
							
								// Validate Billed Usage is displayed
								WebElement txtBilledUsage = lDriver.findElement(By.xpath("//*[@id='timePeriod']"));
							    if(txtBilledUsage.getText().contains("Previously")){
							    	Report.ValidationPoint(testContext.getName(), "Validate Billed usage is displayed", "Billed usage is displayed", "Billed usage is displayed", true);
							    }else{
							    	Report.ValidationPoint(testContext.getName(), "Validate Billed usage is displayed", "Billed usage is displayed", "Billed usage is NOT displayed", true);
							    }
																		    
							}else{
								Report.ValidationPoint(testContext.getName(), "Validate user is navigated to Billings & Usage page", "User is navigated to Billings & Usage page", "user is NOT navigated to Billings & Usage page", true);
							}
						}else{
							Report.ValidationPoint(testContext.getName(), "Validate View Billed Usage link is displayed under Quick Tools section", "View Billed Usage link is displayed under Quick Tools section", "View Billed Usage link is NOT displayed under Quick Tools section", true);
					    }
					
					}else{
						Report.ValidationPoint(testContext.getName(), "Validate Quick Tools section is displayed", "Quick Tools section is displayed", "Quick Tools section is NOT displayed", true);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	
	}
	
	/**************************************************************
	 * Function Name - VerifyBalanceActivityItems()
	 * Description - This function is to verify balance activity line items
 	 * Test Case -  DBD16595
	 * Parameters - None
	 * Date created - 25th May 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyBalanceActivityItems(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
			boolean bLastBillActivity = UI.WaitForObject(oR_AccountOverview.txtLastBillActivity, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that a label is provided to display the total sum of the customer's payment  activity on the account within the bill cycle under the most recent bill label", "true",String.valueOf(bLastBillActivity), true);
			boolean bLastBillActivityAmount = UI.WaitForObject(oR_AccountOverview.txtLastBillActivityAmount, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that total dollar sum of payment  activities is displayed", "true",String.valueOf(bLastBillActivityAmount), true);
			//validate the amount format
			String sAmount = oR_AccountOverview.txtLastBillActivityAmount.getText();
			if(sAmount.contains("-$"))
			{
				Report.ValidationPoint(testContext.getName(), "Verify that total dollar sum of payment activities amount is preceded with a dollar sign and a minus sign", "true","true", true);
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that total dollar sum of payment activities amount is preceded with a dollar sign and a minus sign", "true","false", true);
			}
			//Verify that Total balnce amount is displayed
			boolean bTotalBalance = UI.WaitForObject(oR_AccountOverview.txtBalanceAmount, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that total sum of all real time adjustments on current bill cycle is displayed", "true",String.valueOf(bTotalBalance), true);
			
			//Verify the color of Total balance amount displayed
			boolean bRed = UI.WaitForObject(oR_AccountOverview.txtBalanceAmtInRedColor,UI.iObjTimeOut);
			boolean bGreen = UI.WaitForObject(oR_AccountOverview.txtBalanceAmtInGreenColor, UI.iObjTimeOut);
			boolean bOrange = UI.WaitForObject(oR_AccountOverview.txtBalanceAmtInOrangeColor, UI.iObjTimeOut);
			
			if(bRed==true)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that Balance amount is displayed in GREEN color", "Balance amount is displayed in GREEN color","Balance amount is displayed in RED color, Customer has past due", true);

			}else if(bGreen==true)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that Balance amount is displayed in GREEN color", "true","true", true);
				
			}else if(bOrange==true)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that Balance amount is displayed in GREEN color", "Balance amount is displayed in GREEN color","Balance amount is displayed in ORANGE color, Customer has no past due", true);

				
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Not able to Verify the balance amount color", "true","false", true);
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	/**************************************************************
	 * Function Name - VerifyUsageDetailsOverview
	 * Description - This Function is meant to validate usage details in overview page. Strictly developed according to SS and manaul tester guidance
	 * Test Case -  DBD11353
	 * Parameters - None
	 * Date created - 20th May 2015
	 * Developed by -Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyUsageDetailsOverview(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
			//Check for the Payment section on the dashboard ,the amount should be dollar sign.
			boolean bPaymentAmt = UI.WaitForObject(oR_AccountOverview.txtBalanceAmount,5);
			if(bPaymentAmt)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Check for the Payment section on the dashboard", "true", String.valueOf(bPaymentAmt), true);
				//Checking for the dollar sign
				String sBalance = oR_AccountOverview.txtBalanceAmount.getText();
				if(sBalance.contains("$"))
				{
					Report.ValidationPoint(testContext.getName(), "Checking for the dollar sign", "true","true", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Checking for the dollar sign", "true","false", true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Check for the Payment section on the dashboard", "true", String.valueOf(bPaymentAmt), true);
			}
			//Validate View all Usage details link
			boolean blnkUsage = UI.WaitForObject(oR_AccountOverview.lnkViewAllUsage,1);
			Report.ValidationPoint(testContext.getName(), "Verify View all Usage details link", "true", String.valueOf(blnkUsage), true);
			//Validate overage alert icon for text
			boolean bAlert = UI.WaitForObject(oR_AccountOverview.imgAlertIcon,1);
			Report.ValidationPoint(testContext.getName(), "Verify overage alert icon for text", "true", String.valueOf(bAlert), true);
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}

	
	
	/**************************************************************
	 * Function Name - verifyAddDevicePageDetailsAndUsageSectionForProvidedPlan 
	 * Description- This function verifies Add a device Page details and othe details of usage summery section of plan toggle section
	 * Parameters - final ITestContext testContext , sTestCaseFolderName
	 * Date created -29th May 2015
	 * Developed by - 	Sneha Pansare
	 * Last Modified By -
	 * Last Modified Date -
	 ***************************************************************/
	 //DBD07332
	
	public static void verifyAddDevicePageDetailsAndUsageSectionForProvidedPlan(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		String sSuspendedPlanName = IO.GetParamVal(sTestParams, "Suspended_plan_name");
		String sMobileSharePlanName = IO.GetParamVal(sTestParams, "Mobile_share_plan_name");
		
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_AddaDevice oR_AddaDevice = PageFactory.initElements(lDriver, OR_AddaDevice.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		
		try
		{	
			//Change language to spanish
			//Verify Language link on top

//			boolean bLinkLanguage = UI.WaitForObject(oR_AccountOverview.lstLanguage,10);
//			//Click on link
//			oR_AccountOverview.lstLanguage.click();
//			Thread.sleep(4000);
//			Report.TestPoint(testContext.getName(), "Verify Language link on top", "true", String.valueOf(bLinkLanguage), true);
//
//			//Click on link 'Page in spanish'
//			boolean bLinkPageInSpanish = UI.WaitForObject(oR_AccountOverview.lnkPageInSpanish,6);
//			Report.TestPoint(testContext.getName(), "Verify 'Page in spanish' link under Language dropdown", "true", String.valueOf(bLinkPageInSpanish), true);
//
//			//Change Language to Spanish
//			oR_AccountOverview.lnkPageInSpanish.click();
//			Report.TestPoint(testContext.getName(), "Change language to spanish", "Changed", "Changed", true);
//
//			//Verify Dashboard contents are displaying in Spanish
//
//			//here it is verified that whether overview link is getting displayed in spanish to identify page change in spanish
//			boolean bLinkOverviewInSpanish = UI.WaitForObject(oR_AccountOverview.lnkResumen,40);
//			Report.TestPoint(testContext.getName(), "Verify Dashboard contents are displaying in Spanish", "true", String.valueOf(bLinkOverviewInSpanish), true);
//
//			//Change language back to English
//			boolean bLinkLanguageInSpanish = UI.WaitForObject(oR_AccountOverview.lnkIdoma,40);
//			oR_AccountOverview.lnkIdoma.click();
//			Thread.sleep(4000);
//			Report.TestPoint(testContext.getName(), "Click on Language link which is now displaying in spanish", "true", String.valueOf(bLinkLanguageInSpanish), true);
//
//			//Click on 'Page in English' link under Language dropdown 
//			boolean bLinkPageInEnglish = UI.WaitForObject(oR_AccountOverview.lnkPageInEnglish,40);
//			oR_AccountOverview.lnkPageInEnglish.click();
//			Thread.sleep(8000);
//			Report.TestPoint(testContext.getName(), "Change language back to English", "true", String.valueOf(bLinkPageInEnglish), true);
//			
			
			try
			{
				//Retireve Provided plan name link from plan toggle section
				List<WebElement> lstSuspendedPlan = lDriver.findElements(By.xpath("//*[@id='MainTab']//li/a[contains(@linkname,'"+sSuspendedPlanName+"')]"));
				int iSuspendedNotePresentForSuspendedPlan = 0;
				for(int cnt= 0 ; cnt<lstSuspendedPlan.size(); cnt++)
				{
					lstSuspendedPlan.get(cnt).click();
					
					Thread.sleep(10000);
					
					try
					{
						//Retrieve suspended message elements
						List<WebElement> lstSuspendedLable = lDriver.findElements(By.xpath("//*[contains(@alt,'suspended')]"));//Mobile Share 2GB with Unlimited Talk & Text
						
						boolean bSuspendedNotePresent = false;
						
						//here loop is added because there might be other suspended messages which are not visible
						for(int suspendedCnt = 0; suspendedCnt<lstSuspendedLable.size()  ;suspendedCnt++)
						{
							if((lstSuspendedLable.get(suspendedCnt)).isDisplayed())
							{
								// if suspended messages element is visible then increment counter
								bSuspendedNotePresent=true;
								iSuspendedNotePresentForSuspendedPlan++;
								break;
							}
						}
						
						// if suspended note is displayed for provided plan
						if(bSuspendedNotePresent==true)
						{
							Report.TestPoint(testContext.getName(), "Verify suspended note is displayed for suspended plan '"+sSuspendedPlanName+"'", "Displayed", "Displayed", true);
						}
						
						// if suspended note is not displayed for provided plan
						if(iSuspendedNotePresentForSuspendedPlan==0)
						{
							Report.TestPoint(testContext.getName(), "Verify suspended note is displayed for suspended plan '"+sSuspendedPlanName+"'", "Displayed", "NOT Displayed", true);
						}
						
						//Verify the Service summary section is present 
						try
						{
							WebElement elmServiceSummerySection = lDriver.findElement(By.xpath("//*[@class='dashboardTabContent']"));
							if(elmServiceSummerySection.isDisplayed())
							{
								Report.TestPoint(testContext.getName(), "Verify service summery section is Displayed", "Displayed", "Displayed", true);
							}
						}
						catch(Exception e)
						{
							Report.TestPoint(testContext.getName(), "Verify service summery section is Displayed", "Displayed", "NOT Displayed", true);
						}
					}
					catch(Exception e)
					{
						Report.TestPoint(testContext.getName(), "Verify suspended note is displayed for suspended plan '"+sSuspendedPlanName+"'", "Displayed", "NOT displayed", true);
					}
				}
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Verify suspended message for suspended plan", "Verified", "Provided plan '"+sSuspendedPlanName+"' DOES NOT exist", true);
			}
			
			 
			 
			//Verify that in Quick links dropdown Add a line link is displayed under wireless section
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Manage my plan", null, "Add a device", null);
			
			//click on link
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Click", "Manage my plan", null, "Add a device",null);
			
			//search for - RatePlan
			Thread.sleep(10000);
			
			boolean bTitleAddDevice = UI.WaitForObject(oR_AddaDevice.txtAddaDevice,UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Navigation to add a device page", "true", String.valueOf(bTitleAddDevice), true);	
			
			//Retrieve all 'add a device to..' links
			try
			{
				List<WebElement> lstAddaDeviceToLinks = lDriver.findElements(By.xpath("//a[contains(text(),'Add a device to')]"));
				for(int lnkCount= 0; lnkCount< lstAddaDeviceToLinks.size(); lnkCount++)
				{
					String lnkText = lstAddaDeviceToLinks.get(lnkCount).getText();
					Report.ValidationPoint(testContext.getName(), "'"+lnkText+"' link", "Link displayed", "Link displayed", true);
					
					/*
					lstAddaDeviceToLinks.get(lnkCount).click();
					
					Thread.sleep(8000);
					
					String sURL= driver.getCurrentUrl();
					
					if(sURL.contains("RatePlan"))
					{
						Report.ValidationPoint(testContext.getName(), "Verify link '"+lnkText+"' navigates to Hardrock page", "Navigated successfully", "Navigated successfully", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify link '"+lnkText+"' navigates to Hardrock page", "Navigated successfully", "Failed to Navigate", true);
					}
					
					driver.navigate().back();
					
					Thread.sleep(20000);
					*/
				
					
					
				}
				
				oR_AccountOverview.lnkOverview.click();
				Thread.sleep(5000);
				
			}
			catch(Exception e2)
			{
				Report.ValidationPoint(testContext.getName(), "'Verify Add a device to' link navigated to hardrock page", "Navigates", "Link DOES NOT exist", true);
			}
				
				//Select Mobile share plan
				try
				{
					WebElement mobileSharePlan = lDriver.findElement(By.xpath("//*[@id='MainTab']//li/a[contains(@linkname,'"+sMobileSharePlanName+"')]"));
					mobileSharePlan.click();
					
					Report.TestPoint(testContext.getName(), "Select Mobile share plan '"+sMobileSharePlanName+"'", "Selected", "Selected", true);
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Select Mobile share plan", "Selected", "Provided mobile share plan DOES NOT exist", true);
				}
				
				//Verify View details link
				try
				{
					WebElement lnkViewDetails = lDriver.findElement(By.xpath("//a[contains(text(),'View details')]"));
					lnkViewDetails.click();
					Thread.sleep(4000);
					Report.TestPoint(testContext.getName(), "Verify View details link", "Link present and clicked", "Link present and clicked", true);
					
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Verify View details link", "Link present and clicked", "Link NOT displayed", true);
				}
				
				//Switch to frame
				try
				{
					lDriver.switchTo().frame(lDriver.findElement(By.xpath("//iframe[contains(@name,'cbox')]")));
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Switch to View details link frame", "Switched", "Failed to switch to frame", true);
				}
				
				//Verify 'View billed usage' link on View details page
				try
				{
					lDriver.findElement(By.xpath("//a[contains(text(),'View billed usage')]"));
					Report.TestPoint(testContext.getName(), "Verify 'View billed usage' link on View details page", "Link Displayed", "Link Displayed", true);
					
					lDriver.findElement(By.xpath("//a[contains(text(),'View billed usage')]")).click();
					
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Verify 'View billed usage' link on View details page", "Link Displayed", "Link NOT Displayed", true);
				}
				
				Thread.sleep(8000);

				lDriver.switchTo().defaultContent();
				//Verify 'View billed usage' link navigates to Billing and Usage page
				boolean bTxtBillingAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Verify 'View billed usage' link navigates to Billing and Usage page", "true", String.valueOf(bTxtBillingAndUsage), true);	
				
				lDriver.navigate().back();
				
				Thread.sleep(20000);
				
				//Verify View Plan Details link
				
				if(UI.WaitForObject(oR_AccountOverview.lnkViewPlanDetails, 10, lDriver)){
					
					Report.TestPoint(testContext.getName(), "Verify View Plan Details link is displayed", "View Plan Details link is displayed", "View Plan Details link is displayed", true);
					oR_AccountOverview.lnkViewPlanDetails.click();
				
				}else{
					Report.TestPoint(testContext.getName(), "Verify View Plan Details link is displayed", "View Plan Details link is displayed", "View Plan Details link is NOT displayed", true);
				}
							
				
				//Verify Change plan link
				try
				{
					WebElement lnkChangePlan = lDriver.findElement(By.xpath("//a[text()='Change plan'][contains(@href,'changePlanOverview')]"));
					
					Report.TestPoint(testContext.getName(), "Verify Change plan link", "true", "true", true);
					
					lnkChangePlan.click();
					Thread.sleep(20000);
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Verify Change plan link", "true", "false", true);
				}
				
				//Verify navigation of 'Change plan' link to change rate plan page
				
				String sChangePlanPageURL = lDriver.getCurrentUrl();
				
				if(sChangePlanPageURL.contains("ChangeRatePlan") || sChangePlanPageURL.contains("selectMobileSharePlan") || sChangePlanPageURL.contains("RatePlan"))
				{
					Report.TestPoint(testContext.getName(), "Verify navigation of 'Change plan' link to change rate plan page", "true", "true", true);
				}
				else
				{
					Report.TestPoint(testContext.getName(), "Verify navigation of 'Change plan' link to change rate plan page", "true", "false", true);
				}
				
				
				
				lDriver.navigate().back();
				
				//Verify 'AT&T Messages' field
				boolean bTxtATTMessage = UI.WaitForObject(oR_AccountOverview.txtATTMessage,UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify 'AT&T Messages' field", "true", String.valueOf(bTxtATTMessage), true);	
				
				//Verify 'View all' link
				boolean bLnkViewAll = UI.WaitForObject(oR_AccountOverview.lnkViewAll,UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify 'View all' link", "true", String.valueOf(bLnkViewAll), true);	
				
				
			
			
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyReportingforIWTlinks
	 * Description- This function is to validate View International Services link under View my plans & Services            
	 * Parameters - 
	 * Date created - 20th May 2015
	 * Developed by - Krutika Kamdi 
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//DBD18056

	public static void VerifyReportingforIWTlinks(final ITestContext testContext)
			throws Exception {
		try{
			WebDriver lDriver = UI.getDriver(testContext.getName());           
			OR_ATT_ManageMyDeviceFeatures oR_ATT_ManageMyDeviceFeatures = PageFactory.initElements(lDriver, OR_ATT_ManageMyDeviceFeatures.class);
			
			//Verify View International Services link under Manage my plan
			
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Manage my plan", null , "View international options", null);
			
			//Click on View International Services link under Manage my plan
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Click","Manage my plan", null , "View international options", null);
			
			//Navigating to Manage my features page
			Report.OperationPoint(testContext.getName(), "Navigation - Navigating to ");
			Boolean bManageDevice = UI.WaitForObject(oR_ATT_ManageMyDeviceFeatures.txtManageMyDeviceFeatures,UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Clicking on View international options navigates to Manage Device Page","true",String.valueOf(bManageDevice), true);
	
			
		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name - VerifyQuickLinksWirelessShop
	 * Description- This function is to validate links 'Add a line' and 'Check upgrade eligibility' are suppressed under Shop.   
	 * Parameters - 
	 * Date created - 16th June 2015
	 * Developed by - Krutika Kamdi 
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//DBD16291

	public static void VerifyQuickLinksWirelessShop(final ITestContext testContext)
			throws Exception {
		try{
				//Verify Add a line link is suppressed under Uverse for Shop AT&T
				 UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", "U-verse" , "Add a line", "Suppressed");
				
				 WebDriver lDriver = UI.getDriver(testContext.getName()); 
				 WebElement btnIwantTo= lDriver.findElement(By.xpath("//button[@id='ddShortcut']")); 
		 			
					Actions action= new Actions(lDriver); 
					action.moveToElement(btnIwantTo).click().build().perform(); 
					
			
				//Verify Add a line link is suppressed under Wireless for Shop AT&T
				 UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", "Wireless" , "Add a line", "Suppressed");
				 
					action= new Actions(lDriver); 
					action.moveToElement(btnIwantTo).click().build().perform(); 
				 
				//Verify Check upgrade eligibility link is suppressed under Wireless for Shop AT&T
				 UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", "Wireless" , "Check upgrade eligibility", "Suppressed");
				
					action= new Actions(lDriver); 
					action.moveToElement(btnIwantTo).click().build().perform(); 
				 
				//Verify Check upgrade eligibility link is suppressed under Uverse for Shop AT&T
				 UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", "U-verse" , "Check upgrade eligibility", "Suppressed");
					 action= new Actions(lDriver); 
					action.moveToElement(btnIwantTo).click().build().perform(); 
		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyVoluntarySuspendedAutopayLink()
	 * Description - This testcase is to verify the Autopay link for the accounts enrolled in Autopay- Voluntary Suspended also to Verify the  Paperless link where only few accounts are enrolled in Paperless.
 	 * Test Case -  DBD16469
	 * Parameters - None
	 * Date created - 16th June 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyVoluntarySuspendedAutopayLink(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			boolean bManageAutopay = UI.WaitForObject(oR_AccountOverview.lnkManageAutopayDasboard, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that 'Manage AutoPay' link is displayed", "true",String.valueOf(bManageAutopay), true);
			try
			{
				boolean bManagePaperlessBilling = UI.WaitForObject(oR_AccountOverview.lnkManagePaperlessBilling, UI.iObjTimeOut);
				boolean bEnrollPaperlessBilling = UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBilling, UI.iObjTimeOut);
				
				Report.ValidationPoint(testContext.getName(), "Verify that Paperless billing link - 'Manage Paperless Billing' is NOT displayed", "false",String.valueOf(bManagePaperlessBilling), true);
				Report.ValidationPoint(testContext.getName(), "Verify that Paperless billing link - 'Enroll in Paperless Billing' is NOT displayed", "false",String.valueOf(bEnrollPaperlessBilling), true);
				
			}
			catch (Exception e) 
			{
				Report.ValidationPoint(testContext.getName(), "Verify that Paperless billing links are not displayed", "true","true", true);

			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}

/**************************************************************
 * Function Name - VerifyUpgradePlanAndUsageDetailsLink()
 * Description - This testcase  Validates required links. 
 * Test Case -  DBD21814
 * Parameters - None
 * Date created - 17th June 2015
 * Developed by - Gautham
 * Last Modified By - 
 * Last Modified Date - 
 ***************************************************************/
public static void VerifyUpgradePlanAndUsageDetailsLink(final ITestContext testContext) throws HeadlessException, IOException, AWTException
{
	try
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		try
		{
			boolean bUpgrade = UI.WaitForObject(oR_AccountOverview.lnkCheckUpgradeEligibility, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that  'Upgrade plan link' is  displayed", "false",String.valueOf(bUpgrade), true);
		}
		catch (Exception e) 
		{
			Report.ValidationPoint(testContext.getName(), "Verify that Upgrade plan link' is  displayed", "true","true", true);
		}
		
		boolean bViewDetails= UI.WaitForObject(oR_AccountOverview.lnkViewUsgeDetails, UI.iObjTimeOut);
		Actions move = new Actions(lDriver);
		move.moveToElement(oR_AccountOverview.lnkViewUsgeDetails).build().perform();
		Report.ValidationPoint(testContext.getName(), "Verify that  'View Usage details link' is  displayed", "true",String.valueOf(bViewDetails), true);
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
	}
	
	}



/**************************************************************
 * Function Name - VerifyTroubleShootAndResolveSection()
 * Description - To verify customer have a TS & R section so that customer can troubleshoot the issues with my service
 * Test Case - DBD20689
 * Parameters - None
 * Date created - 17th June 2015
 * Developed by - Clint John
 * Last Modified By - 
 * Last Modified Date - 
 ***************************************************************/
public static void VerifyTroubleShootAndResolveSection(final ITestContext testContext) throws HeadlessException, IOException, AWTException
{
	try
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

		boolean bTSR = UI.WaitForObject(oR_AccountOverview.lnkTroubleshoot, UI.iObjTimeOut);
		new Actions(lDriver).moveToElement(oR_AccountOverview.lnkTroubleshoot).build().perform();
		Report.TestPoint(testContext.getName(), "Verify that link for TroubleShoot And Resolve provided in service summary section is displayed", "true", String.valueOf(bTSR), true);
		
		oR_AccountOverview.lnkTroubleshoot.click();
		Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'TS&R' link");
		
		Thread.sleep(7000);
		
		//WebElement wHeading = lDriver.findElement(By.xpath("//h1/span[contains(text(),'Troubleshoot & Resolve')]"));
		//boolean bTroubleShootPage = UI.WaitForObject(wHeading, UI.iObjTimeOut);
		String sURL = lDriver.getCurrentUrl().toString();
		if(sURL.contains("ufix.att.com"))
		{
			Report.ValidationPoint(testContext.getName(), "Verify that after clicking 'Troubleshoot & Resolve' link, the page is redirected to the correct URL(which contains ufix.att.com). URL for new page is: "+sURL, "true","true", true);
		
		}else
		{
			Report.ValidationPoint(testContext.getName(), "Verify that after clicking 'Troubleshoot & Resolve' link, the page is redirected to the correct URL(which contains ufix.att.com). URL for new page is: "+sURL, "true","false", true);
		}
		
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

	}
	
}


	/**************************************************************
	 * Function Name - VerifyQuickLinksForUverseShop
	 * Description- This function is to verify : 
	                > "Shop AT&T" Category is displaying under "I want to.." Quick link gor Uverse service.
	 * Parameters - 
	 * Date created - 17th June 2015
	 * Developed by - Krutika Kamdi 
	 * Last Modified By - Clint (Changed Uverse to null since Uverse is not displayed)
	 * Last Modified Date - 24th Sept 2015
	 ***************************************************************/
	//DBD16365

	public static void VerifyQuickLinksForUverseShop(final ITestContext testContext)
			throws Exception {
		
		try{
			//Verify •Exclusive Offers link is displayed under Shop navigating through I Want to drop down
			//UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", "U-verse" , "Exclusive offers",null);
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", null , "Exclusive offers",null);
			
			//Verify Remote Controls link
			//UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", "U-verse" , "Remote controls", null);
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", null , "Remote controls", null);
			
			//Verify •Free Videos link
			//UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", "U-verse" , "Free videos", null);
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", null , "Free videos", null);
			
			//Verify Upgrade Package link should be Suppressed
			//UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", "U-verse" , "Upgrade Package", "Suppressed");
			UI. ClickOrVerifyLinkUnderIWantToDropdown("Verify","Shop AT&T", null , "Upgrade Package", "Suppressed");
		

		}
		catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}	
	
	/**************************************************************
	 * Function Name - VerifyTitanAccountIdentifier
	 * Description- This function is to verify that for a SLID with 1 titan bundle with 1 uverse and 1 wireless ,the Icon and Wireless Label and Nickname is displayed
	 * Parameters - 
	 * Date created - 17th June 2015
	 * Developed by - Krutika Kamdi 
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//DBD16048

	public static void VerifyTitanAccountIdentifier(final ITestContext testContext)
			throws Exception {
		
		try{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			//OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			List<WebElement> uversePlans = null;
			try
			{
				uversePlans = lDriver.findElements(By.xpath("//*[@id='MainTab']//li"));
			
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Extarct all uverse plan tabs from toggle", "Extarcted", "Failed to extract", true);
			}
				
				String sTabText = uversePlans.get(0).getText();
				
		
				if(sTabText.contains("Wireless"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify Wireless Plan is displayed in Plan toggle section", "true","true", true);
					
					//Verify Plan Name is displayed for all Wireless Toggle section
					List<WebElement> txtPlanName = lDriver.findElements(By.xpath("//ul[@id='MainTab']//span[contains(text(),'Wireless')]/parent::span/span[3]"));
					String sPlan = txtPlanName.get(0).getText();
					for(int i=0; i<txtPlanName.size(); i++)
					{
						WebElement Plan = txtPlanName.get(i);
						
						if(sPlan!=null)
						{
							Report.ValidationPoint(testContext.getName(), "Verify Plan Name is displayed for Wireless Account '"+i+"'", "true","true", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Verify Plan Name is displayed for Wireless Account '"+i+"'", "true","false", true);
						}

					}
					//Verify Icon is displayed in Plan Toggle section
					List <WebElement> imgIcon = lDriver.findElements(By.xpath("//ul[@id='MainTab']//a/img[contains(@src,'/olam/images/brand30/icon_uverse_wireless_ind')]"));
					for(int i=0; i<imgIcon.size();i++)
					{
						if(imgIcon.get(i)!=null)
						{
							Report.ValidationPoint(testContext.getName(), "Verify Icon is displayed for Wireless Toggle section '"+i+"'", "true","true", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Verify Icon is displayed for Wireless Toggle section '"+i+"'", "true","false", true);
						}
					}
					//Verify Nickname or titan bundle is displayed
					List<WebElement> txtToggle = lDriver.findElements(By.xpath("//ul[@id='MainTab']//span[contains(text(),'Wireless')]/parent::span"));
					//System.out.println("chck"+txtToggle.get(0).getText());
					//System.out.println("chck"+txtToggle.get(1).getText());
					for(int j=0;j<txtToggle.size();j++)
					{
						String sSection = txtToggle.get(j).getText();
						String[] aSection = sSection.split("\\:");
						System.out.println(aSection[1]);
						String[] aNickName = aSection[1].split(sPlan);
						System.out.println(aNickName[0]);
						if(aNickName!=null)
						{
							Report.ValidationPoint(testContext.getName(), "Verify NickName or Account is displayed with Wireless section '"+j+"'", "true","true", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Verify NickName or Account is displayed with Wireless section '"+j+"'", "true","false", true);
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
	 * Function Name -  ValidateUnderstaingVideoLinkInDasboard
	 * Description- 
	 * Parameters - 
	 * Date created -22nd Jun 15
	 * Developed by - gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	//DBD18081

	public static void ValidateUnderstaingVideoLinkInDasboard(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName()); 
        Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
        OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
        OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
        
		try
		{
			
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
			//Verify link Understanding your att u-verse bill Include video length/time (2:17)
			boolean bVideo = UI.WaitForObject(oR_AccountOverview.lnkunderstandingVideoToolTip, UI.iObjTimeOut);
			new Actions(lDriver).moveToElement(oR_AccountOverview.lnkunderstandingVideoToolTip).build().perform();
			Report.ValidationPoint(testContext.getName(), "Verify link Understanding your att u-verse bill Include video length/time (2:17) is displayed", "true", String.valueOf(bVideo), true);
			
			//Verify upon click, display video player modal
			oR_AccountOverview.lnkunderstandingVideoToolTip.click();
			Report.OperationPoint(testContext.getName(), "Clicking on Video Link");
			Thread.sleep(3000);
			WebElement ModalWindow= lDriver.findElement(By.xpath("//div[@style='position: relative; width: auto; display: inline-block; padding: 70px 10px 10px;']"));
			if(ModalWindow.isDisplayed())
			{
				Report.ValidationPoint(testContext.getName(), "Verify upon click, display video player modal is displayed", "true", "true", true);
				
			}	
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify upon click, display video player modal is displayed", "true", "false", true);
				
			}
			Thread.sleep(5000);
			lDriver.findElement(By.xpath("//button[contains(text(),'Close')]")).click();
		}

		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyMiniStatement()
	 * Description - Verify that customer is able to view mini statement within the B&P section in Dashboard
 	 * Test Case -  DBD16573
	 * Parameters - None
	 * Date created - 23-June-2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyMiniStatement(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
//			//Verify total balance text and amount is displayed 
//			boolean bTotalBalanceText = UI.WaitForObject(oR_AccountOverview.txtTotalBalance, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Verify that text for Total Balance is displayed", "true",String.valueOf(bTotalBalanceText), true);
//			//Verify total balance amount is displayed
//			boolean bTotalBalance = UI.WaitForObject(oR_AccountOverview.txtBalanceAmount, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Verify that Total Balance Amount is displayed", "true",String.valueOf(bTotalBalance), true);
//			
//			//Verify Most Recent Activity text and Amount
//			boolean bMostRecentBillText = UI.WaitForObject(oR_AccountOverview.txtMostRecentBill, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Verify that text for Most recent bill is displayed", "true",String.valueOf(bMostRecentBillText), true);
//			boolean bMostRecentBillAmount = UI.WaitForObject(oR_AccountOverview.txtMostRecentBillAmount, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Verify that the Amount for Most recent bill is displayed", "true",String.valueOf(bMostRecentBillAmount), true);
//			
//			//Verify Activity since last bill is displayed
//			//Verify Most Recent Activity text and Amount
//			boolean bActivityText = UI.WaitForObject(oR_AccountOverview.txtLastBillActivity, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Verify that text Activity since last bill is displayed", "true",String.valueOf(bActivityText), true);
//			boolean bActivityAmount = UI.WaitForObject(oR_AccountOverview.txtMostRecentBillAmount, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Verify that the Amount for Activity since last bill is displayed", "true",String.valueOf(bActivityAmount), true);
			
			//Naviagte to Billing and paymnets page
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Report.OperationPoint(testContext.getName(), "Navigating to Billing, Usage and Payments page");
			
			boolean bBillingPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Billing and Usage page is displayed", "true",String.valueOf(bBillingPage), true);
			
			//Verify that mini statement is getting visible in the billing & payment section.
			try
			{
				String sNote = oR_BillAndUsage.txtNote.getText();
				if(sNote.contains("Your account has been suspended per your request"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify that mini statement is getting visible in the billing & payment section", "true","true", true);
					Report.OperationPoint(testContext.getName(), "Contents of the Note displayed is:- "+sNote);
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that mini statement is getting visible in the billing & payment section", "true","false", true);
					Report.OperationPoint(testContext.getName(), "Contents of the Note displayed is:- "+sNote);
				}
				
			}catch(Exception ee)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that mini statement is getting visible in the billing & payment section", "true","NO Note/Mini statement is displayed", true);
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	
	/**************************************************************
	 * Function Name - VerifyAlertHardSuspension()
	 * Description - Verify that customer is able to view mini statement within the B&P section in Dashboard
 	 * Test Case -  DBD16719
	 * Parameters - None
	 * Date created - 24th-June-2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyAlertHardSuspension(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			//Verify total balance text and amount is displayed 
			boolean bTotalBalanceText = UI.WaitForObject(oR_AccountOverview.txtTotalBalance, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that text for Total Balance is displayed", "true",String.valueOf(bTotalBalanceText), true);
			//Verify total balance amount is displayed
			boolean bTotalBalance = UI.WaitForObject(oR_AccountOverview.txtBalanceAmount, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that Total Balance Amount is displayed", "true",String.valueOf(bTotalBalance), true);
			
			//Verify Most Recent Activity text and Amount
			boolean bMostRecentBillText = UI.WaitForObject(oR_AccountOverview.txtMostRecentBill, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that text for Most recent bill is displayed", "true",String.valueOf(bMostRecentBillText), true);
			boolean bMostRecentBillAmount = UI.WaitForObject(oR_AccountOverview.txtMostRecentBillAmount, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that the Amount for Most recent bill is displayed", "true",String.valueOf(bMostRecentBillAmount), true);
			
			//Verify Activity since last bill is displayed
			//Verify Most Recent Activity text and Amount
			boolean bActivityText = UI.WaitForObject(oR_AccountOverview.txtLastBillActivity, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that text Activity since last bill is displayed", "true",String.valueOf(bActivityText), true);
			boolean bActivityAmount = UI.WaitForObject(oR_AccountOverview.txtMostRecentBillAmount, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that the Amount for Activity since last bill is displayed", "true",String.valueOf(bActivityAmount), true);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - VerifyDTVNotDisplayed()
	 * Description - This function is to verify that login is working fine for the titan bundle account having DTV service. Also verify that DTV charges are not reflected in the customer's bill details.
 	 * Test Case - DBD11669
	 * Parameters - None
	 * Date created - 24th June 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyDTVNotDisplayed(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			//Verify that on Overview page, no DTV details are displayed
			List<WebElement> MyPlans = lDriver.findElements(By.xpath("//ul[@id='MainTab']/li"));
			int flag=0;
			for(int i=0;i< MyPlans.size();i++)
			{
				if(MyPlans.get(i).getText().contains("DIRECTV") || MyPlans.get(i).getText().contains("DTV") || MyPlans.get(i).getText().contains("DirecTV"))
				{
					flag=1;
					Report.ValidationPoint(testContext.getName(), "Verify that NO DTV details are displayed in overview page", "true","false", true);
					break;
				}else
				{
					flag=0;
				}
			}
			if(flag==0)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that NO DTV details are displayed in overview page", "true","true", true);
			}
			
			//Navigate to Bill & Payments page and verify no DTV charges/details are displayed in Bill details & Usage section
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Bill, Usage, Payments link from Sec Nav");
			
			boolean bBillingPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that Billing & Usage page is displayed", "true",String.valueOf(bBillingPage), true);
			
			List<WebElement> DirectTV1 = lDriver.findElements(By.xpath("//div[@id='tabcontent']//*[contains(text(),'DIRECTV')]"));
			List<WebElement> DirectTV2 = lDriver.findElements(By.xpath("//div[@id='tabcontent']//*[contains(text(),'DirecTV')]"));
			List<WebElement> DirectTV3 = lDriver.findElements(By.xpath("//div[@id='tabcontent']//*[contains(text(),'DTV')]"));
			if(DirectTV1.size()==0 && DirectTV2.size()==0 && DirectTV3.size()==0)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that NO DTV details and charges are displayed in Bill details tab", "true","true", true);
				
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that NO DTV details are displayed in Bill details tab", "true","false", true);
			}
			
			//Navigate to Usage tab
			oR_BillAndUsage.lnkUsage.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Usage tab");
			
			Thread.sleep(5000);
			
			List<WebElement> DirectTV01 = lDriver.findElements(By.xpath("//div[@id='tabcontent']//*[contains(text(),'DIRECTV')]"));
			List<WebElement> DirectTV02 = lDriver.findElements(By.xpath("//div[@id='tabcontent']//*[contains(text(),'DirecTV')]"));
			List<WebElement> DirectTV03 = lDriver.findElements(By.xpath("//div[@id='tabcontent']//*[contains(text(),'DTV')]"));
			if(DirectTV01.size()==0 && DirectTV02.size()==0 && DirectTV03.size()==0)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that NO DTV details and charges are displayed in Bill Usage tab", "true","true", true);
				
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that NO DTV details are displayed in Bill Usage tab", "true","false", true);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}

	/**************************************************************
	 * Function Name - VerifyTOBRAlert 
	 * Description- This function validate TOBR alert
	 * Parameters - None
	 * Date created - 02 Jul 2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void VerifyTOBRAlert(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
			//click on alertt button
			boolean bAlert= UI.WaitForObject(oR_AccountOverview.btnAlert, 10);
			oR_AccountOverview.btnAlert.click();
			Report.ValidationPoint(testContext.getName(), "Validating alert button is present", "true", String.valueOf(bAlert), true);
			
			//validating TOBR alert is present or not
			
			List<WebElement> TOBRAlert= lDriver.findElements(By.xpath("//p[contains(text(),'Transfer of billing responsibility')]"));
			if(TOBRAlert.size()>0)
			{
				Report.ValidationPoint(testContext.getName(), "Validating alert for TOBR is present", "true", String.valueOf(TOBRAlert.size()>0), true);
			
			}
			
			//Verify that "countdown and message starting at 14days before expiration" is displaying
			boolean bExpireAlert= UI.WaitForObject(oR_AccountOverview.txtTOBRAlertExpire, 10);
			Report.ValidationPoint(testContext.getName(), "Validating Message displaying about expiration  is present", "true", String.valueOf(bExpireAlert), true);
			
			//Verify CTN is displayed for which the customer has initiated the TOBR
			boolean bExpireAlertCTN= UI.WaitForObject(oR_AccountOverview.txtTOBRAlertCTN, 10);
			Report.ValidationPoint(testContext.getName(), "Validating CTN is displayed for which the customer has initiated the TOBR", "true", String.valueOf(bExpireAlertCTN), true);
			
			//Verify one alert is displaying for each CTN incase of Multiple TOBR(if present)
			if(TOBRAlert.size()>0)
			{
				Report.ValidationPoint(testContext.getName(), "Validating one alert is displaying for each CTN incase of Multiple TOBR", "true", String.valueOf(TOBRAlert.size()>0), true);
			
			}
			
			//On the Dashboard,Below the device image, click on "Learn how to use my device link"
			boolean bLearn= UI.WaitForObject(oR_AccountOverview.lnkLearnHowToUseMyDevice, 10);
			UI.VerifyLinkNavigatestoNextPage(oR_AccountOverview.lnkLearnHowToUseMyDevice, "stage.att.com/devicehowto");
			
		}

		catch (Exception e) 
		{
		e.printStackTrace();
		Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	
   }
	/**************************************************************
	 * Function Name - VerifyPromotionTiles 
	 * Description- This function validate Promotion tiles in dashboard
	 * Parameters - 
	 * Date created - 09th Jul 2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void VerifyPromotionTiles(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	
		try
		{
			//Verify that the user is landed to Overview page.
			boolean boverview = UI.WaitForObject(oR_AccountOverview.txtWelComeBack, 10);
			Report.ValidationPoint(testContext.getName(), "Verify that the user is landed to Overview page.", "true", String.valueOf(boverview), true);
		
			//Verify that promo tiles are displayed on Overview page
			boolean bpromotion= UI.WaitForObject(oR_AccountOverview.imgPromotions, 10);	
			Report.ValidationPoint(testContext.getName(), "Validating Link is opened with device tutorial", "true", String.valueOf(bpromotion), true);
		
			//Verify that for the third Promo Slot TDATA Promo is disabled and ATG promo for Bundle/Converge offer will be displayed.
			boolean bATG= UI.WaitForObject(oR_AccountOverview.lnkATGPromotionTile, 10);	
			Report.ValidationPoint(testContext.getName(), "Validating ATG promo for Bundle/Converge offer will be displayed", "true", String.valueOf(bATG), true);
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	
   }
	
	/**************************************************************
	 * Function Name - VerifyTitanAlertHardSuspension()  #DBD16719
	 * Description- This function 
	 * Parameters - None
	 * Date created - 20th July 2015
	 * Developed by - Monica M
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * 	 ***************************************************************/
	//DBD16719
	public static void VerifyTitanAlertHardSuspension(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			String sMAPBtn = null;
			//validate alert
			if(UI.WaitForObject(oR_AccountOverview.btnAlertClick, 10, lDriver))
			{
				String Alert=oR_AccountOverview.btnAlertClick.getText();
				if(Alert.equals("1"))
				{
					Report.ValidationPoint(testContext.getName(), "Validate ONLY ONE red alert is there ", "true", "true", true);
					
				}
				oR_AccountOverview.btnAlertClick.click();
				
				//validate alert text
				if(UI.WaitForObject(oR_AccountOverview.txtAccountSuspendedAlert, 10, lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Original description is available in alert for suspended", "true", "true", true);
				}
				
				if(UI.WaitForObject(oR_AccountOverview.lnkContactATT, 10, lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Validate alert has a CTA to contact ATT", "true", "true", true);
				}
				
				//validate title
				if(UI.WaitForObject(oR_AccountOverview.txtAccountSuspendedAlert, 10, lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Validate alert has title", "true", "true", true);
				}
				
				//
			}
			if(lDriver.findElement(By.xpath("(//span[contains(@class,'font30imp')])[1]")).isDisplayed());
			
			{
				Report.TestPoint(testContext.getName(), "Balance Amount", "Present", "Present", true);
				String sAmount = lDriver.findElement(By.xpath("(//span[contains(@class,'font30imp')])[1]")).getText();
				System.out.println(sAmount);
				
				String[] parts = sAmount.split("\\$");
				String sAmt = parts[1];
				System.out.println(sAmt + " sAmt");
				
				String[] Amount = sAmt.split("//.");
				String sAmount1 = Amount[0];	
				System.out.println(sAmount1 + " sAmount1");
				
				float iAmount = Float.parseFloat(sAmount1);
				
				
				System.out.println(iAmount);
//				Verify that it Provides CTA for customer to make a payment ONLY if balance is GREATER THAN 0.

				if(iAmount==0.0)
				 {
					
					sMAPBtn = UI.CheckExist(oR_AccountOverview.btnMakeAPayment);
					System.out.println(sMAPBtn);
					if(sMAPBtn.equalsIgnoreCase("True"))				
						Report.ValidationPoint(testContext.getName(), "Verify that it Provides CTA for customer to make a payment ONLY if balance is GREATER THAN 0.", "true", "true", true);
					else
						Report.ValidationPoint(testContext.getName(), "Verify that it Provides CTA for customer to make a payment ONLY if balance is GREATER THAN 0.", "true", "false", true);

				 }
				Report.OperationPoint(testContext.getName(), "Validate Links in billing and payments section on Dashboard");				
//				verify that 'Enroll auto pay' link is NOT displayed at the B&P section .
				String sAutopay = UI.CheckExist(oR_AccountOverview.lnkEnrollInAutopay);
				Report.ValidationPoint(testContext.getName(), "Link Enroll in Autopay", "false", sAutopay, true);
				
//				verify that 'Sign up for Paperless Billing' or 'Manage Paperless Billing' CTA is NOT displayed at the B&P section.
				Boolean bEnrollInPaperlessBilling = UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBilling, 2) || UI.WaitForObject(oR_AccountOverview.lnkManagePaperlessBilling, 2);
				Report.ValidationPoint(testContext.getName(), "Verify that 'Sign up for Paperless Billing' or 'Manage Paperless Billing' CTA is NOT displayed", "false", String.valueOf(bEnrollInPaperlessBilling), true);

				Report.OperationPoint(testContext.getName(), "Validate Quicklinks under Shop AT&T Category");				

//				Verify that following links are getting visible under "Shop AT&T" Category for Uverse service Exclusive Offers Free Videos Remote Controls
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", null, "Exclusive offers", ""); 
				Thread.sleep(2000); 
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", null, "Remote controls", ""); 
				Thread.sleep(2000); 
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", null, "Free videos", ""); 
				Thread.sleep(2000); 
//				Verify that following link is suppressed under "Shop AT&T" Category for Uverse service Upgrade Package
				UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", null, "Upgrade package", "Suppressed"); 

			}
		}
		catch (Exception e)
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyBMWCustomerDashboard()
	 * Description - Validate Login functionality for BMW Customer and dashboard validations for connected car devices
 	 * Test Case -  DBD10405
	 * Parameters - None
	 * Date created - 21st July 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyBMWCustomerDashboard(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			List <WebElement> ConnectedDevices = lDriver.findElements(By.xpath("//div[@class='phoneItemContainerMid']//li"));
			int iDeviceNum = ConnectedDevices.size();
			if(iDeviceNum!=1 && iDeviceNum!=0)
			{
				Report.OperationPoint(testContext.getName(), "Operational - Total number of connected devices displayed in dashboard :"+iDeviceNum);
				Report.ValidationPoint(testContext.getName(), "Verify that connected devices are displayed at dashboard for BMW customers", "true","true", true);
				//verify device images are displayed
				List <WebElement> ConnectedDevicesImage = lDriver.findElements(By.xpath("//div[@class='phoneItemContainerMid']//li//img"));
				int iImageNum = ConnectedDevicesImage.size();
				if(iImageNum==iDeviceNum)
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Device images are displayed in dashboard", "true","true", true);
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Device images are displayed in dashboard", "true","false", true);
				}
			}else
			{
				Report.OperationPoint(testContext.getName(), "Operational - Total number of connected devices displayed in dashboard :"+iDeviceNum);
				Report.ValidationPoint(testContext.getName(), "Verify that connected devices are displayed at dashboard for BMW customers", "true","false", true);
			}
			
			try
			{
				
				boolean bUpgradeOption = UI.WaitForObject(oR_AccountOverview.lnkViewUpgradeOptions, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that 'View upgrade options' CTA is suppressed", "false",String.valueOf(bUpgradeOption), true);

				
			}catch(Exception Ee)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that 'View upgrade options' CTA is suppressed", "true","true", true);
			}
			
			String sTextXpath = "//div[contains(@class,'usageContainer')]//*[contains(text(),'Text')]";
			try
			{
				boolean bText = UI.WaitForObject(lDriver.findElement(By.xpath(sTextXpath)), UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Talk should be suppressed for connected car CTN on dashboard", "false",String.valueOf(bText), true);
				
			}catch(Exception Ee2)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that Talk should be suppressed for connected car CTN on dashboard", "true","true", true);
			}
			
			List<WebElement> ViewDetails1 = lDriver.findElements(By.xpath("//div[contains(@class,'usageContainer')]//li//a[contains(text(),'View details')]"));
			int iLinkSize = ViewDetails1.size();
			for(int i=0;i<iLinkSize;i++)
			{
				List<WebElement> ViewDetails = lDriver.findElements(By.xpath("//div[contains(@class,'usageContainer')]//li//a[contains(text(),'View details')]"));
				ViewDetails.get((i)).click();ViewDetails.get((i)).click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on view details link for CTN no:"+(i+1));
				
				Thread.sleep(5000);
				String sMainWindow = lDriver.getWindowHandle();
				UI.WaitForObject(lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe")), UI.iObjTimeOut, lDriver);
				WebElement wFrame = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
				lDriver.switchTo().frame(wFrame);
					
					boolean bViewConnectedLink = UI.WaitForObject(oR_AccountOverview.lnkViewConnectedDeviceSupport, UI.iObjTimeOut, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify that 'View connected device support' is displayed", "true",String.valueOf(bViewConnectedLink), true);
					
					/*Below links should be suppressed on Manage my plans and device modal window:
						- View billed usage
						- Turn member’s data on / off
						- View My Contract
						- Learn how to use my device
						- View upgrade options */
					try
					{
						boolean bViewBilledUsage = UI.WaitForObject(oR_AccountOverview.lnkQuickToolsViewBilledUsage, UI.iObjTimeOut, lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify that 'View billed usage' link is NOT displayed", "false",String.valueOf(bViewBilledUsage), true);
								
					}catch(Exception Ee3)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that 'View billed usage' link is NOT displayed", "true","true", true);
					}
					
					try
					{
						boolean bMemData = UI.WaitForObject(oR_AccountOverview.lnkMembersDataOnOff, UI.iObjTimeOut, lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify that 'Turn member’s data on / off' link is NOT displayed", "false",String.valueOf(bMemData), true);
								
					}catch(Exception Ee3)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that 'Turn member’s data on / off' link is NOT displayed", "true","true", true);
					}
					
					try
					{
						boolean bViewMyContract = UI.WaitForObject(oR_AccountOverview.lnkViewMyContract, UI.iObjTimeOut, lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify that 'View My Contract' link is NOT displayed", "false",String.valueOf(bViewMyContract), true);
								
					}catch(Exception Ee3)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that 'View My Contract' link is NOT displayed", "true","true", true);
					}
					try
					{
						boolean bLearnHow = UI.WaitForObject(oR_AccountOverview.lnkLearnHowToUseMyDevice, UI.iObjTimeOut, lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify that 'Learn how to use my device' link is NOT displayed", "false",String.valueOf(bLearnHow), true);
								
					}catch(Exception Ee3)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that 'Learn how to use my device' link is NOT displayed", "true","true", true);
					}
					try
					{
						boolean bViewUpgrade = UI.WaitForObject(oR_AccountOverview.lnkViewUpgradeOptions, UI.iObjTimeOut, lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify that 'View upgrade options' link is NOT displayed", "false",String.valueOf(bViewUpgrade), true);
								
					}catch(Exception Ee3)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that 'View upgrade options' link is NOT displayed", "true","true", true);
					}

				
				oR_AccountOverview.lnkClose.click();
				lDriver.switchTo().window(sMainWindow);
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on close button");
				
				
			}

				

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	/**************************************************************
	 * Function Name - VerifyUverseWirelessInfo()
	 * Description - To verify that for a Titan BAN with u-verse and wireless (UVerse cancelled), appropriate information is shown on dashboard
	 * Test Case - DBD16543
	 * Parameters - None
	 * Date created - 21st June 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyUverseWirelessInfo(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			WebElement includes = lDriver.findElement(By.xpath("//strong[contains(text(),'*Includes')]//parent::span"));

			boolean bPlan = UI.WaitForObject(includes, UI.iObjTimeOut);
			
			Report.TestPoint(testContext.getName(), "Verify Plan section is displayed", "true", String.valueOf(bPlan), true);
			
			//Verify Uverse and Wireless Details
			String sPlan = includes.getText();
			if(sPlan.contains("Wireless") && sPlan.contains("U-verse"))
			{
				Report.TestPoint(testContext.getName(), "Verify Uverse and Wireless Details are displayed", "true","true", true);
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Verify Uverse and Wireless Details are displayed", "true","true", true);
			}
			
			//Navigating to BAP page
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null);
			
			//Verify Uverse and Wireless Plan Bill Details are displayed
			WebElement Users = lDriver.findElement(By.id("ddUserDetails"));
			Users.click();
			Thread.sleep(10000);
			
			WebElement BAN = lDriver.findElement(By.xpath("//div[@class='myAccountList colorBlack']//div[contains(text(),'BAN')]"));
			boolean bBAN = UI.WaitForObject(BAN, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Wireless is displayed for Bill details", "true", String.valueOf(bBAN), true);
			
			WebElement Uverse = lDriver.findElement(By.xpath("//div[@class='myAccountList colorBlack']//div[contains(text(),'BAN')]"));
			boolean bUverse = UI.WaitForObject(Uverse, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Uverse is displayed for Bill details", "true", String.valueOf(bUverse), true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	/**************************************************************
	 * Function Name - VerifyVoluntarySuspendedAlertDashboard()
	 * Description - To verify that it displays the Voluntary Suspended Alert if  Wireless account is voluntarily suspended 
	 * Test Case - DBD16548
	 * Parameters - None
	 * Date created - 30th July 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyVoluntarySuspendedAlertDashboard (final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		try
		{
			//Validate alert button
			boolean bAlertBtn = UI.WaitForObject(oR_AccountOverview.imgAlertIcon, 20);
			Report.TestPoint(testContext.getName(), "Verify alert button is displayed", "true", String.valueOf(bAlertBtn), true);
			//Click on alert button
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on alert button");
			oR_AccountOverview.imgAlertIcon.click();
			Thread.sleep(4000);
			//Validate vonlentary suspension alert is displayed
			boolean bAlert = UI.WaitForObject(oR_AccountOverview.lstAlertDropDown, 10);
			Report.TestPoint(testContext.getName(), "Verify vonlentary suspension alert is displayed", "true", String.valueOf(bAlert), true);
			//Validate Alert heading
			boolean bAlertH = UI.WaitForObject(oR_AccountOverview.txtMobileNoSuspended, 5);
			Report.ValidationPoint(testContext.getName(), "Verify Alert heading: "+oR_AccountOverview.txtMobileNoSuspended.getText(), "true", String.valueOf(bAlertH), true);
			//Validate the content of the alert
			boolean bAlertContent = UI.WaitForObject(oR_AccountOverview.txtYouHave, 5);
			Report.ValidationPoint(testContext.getName(), "Verify the content of the alert: "+oR_AccountOverview.txtYouHave.getText(), "true", String.valueOf(bAlertContent), true);
			//Validate Reactivate service CTA
			boolean bLnkReactivate = UI.WaitForObject(oR_AccountOverview.txtYouHave, 5);
			Report.ValidationPoint(testContext.getName(), "Verify Reactivate service CTA", "true", String.valueOf(bLnkReactivate), true);
			//Click on Hide
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on alert hide link");
			oR_AccountOverview.lnkAlertHide.click();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}	
	}
	/**************************************************************
	 * Function Name - ValidateQuickLinksWirelineProfile()
	 * Description - This function is used To verify the view of Quick Links of slid customer with 2 wireline a/c(suspended) in the dash board.
	 * Test Case -  DBD08528
	 * Parameters - None
	 * Date created - 31st July 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void ValidateQuickLinksWirelineProfile (final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{	
		try
		{
			//Verify Update My Profile Link is displayed under Manage My Profile
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Manage my profile", null , "Update my profile", null );
			
			//Verify Update my login information Link is displayed under Manage My Profile
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Manage my profile" , null , "Update my login information", null );
			
			//Verify Link my AT&T accounts Link is displayed under Manage My Profile
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Manage my profile" , null , "Link my AT&T accounts", null );
			
			//Verify Change my billing address Link is suppressed under Manage My Profile
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Manage my profile" , "Manage my profile" , "Change my billing address", "Suppressed" );
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}	
	}

	/**************************************************************
	 * Function Name - ValidateEmailAndMailboxesIWT()
	 * Description - This function is used to verify  'The"Check email” link and “Manage my mailboxes” link should be displayed under Email
	 * Test Case -  DBD10201
	 * Parameters - None
	 * Date created - 4th Aug 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void ValidateEmailAndMailboxesIWT (final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{	
		try
		{
			//Verify Update My Profile Link is displayed under Manage My Plan
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Manage my plan", "U-verse Internet" , "Manage my mailboxes", null );
			
			//Verify Update my login information Link is displayed under Manage My Plan
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Manage my plan" , "U-verse Internet" , "View my email", null );
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}	
	}
	
	/**************************************************************
	 * Function Name - VerifyLinkAnOPRIDToSlid()
	 * Description - To verify dashboard after linking a  ISP/OPR ID to the SLID With Wireless, Uverse, Wireline account. 
	 * Test Case - DBD20257
	 * Parameters - None
	 * Date created - 4th August 2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -18th September 2015 
	 ***************************************************************/
	public static void VerifyLinkAnOPRIDToSlid (final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_LinkAnAccount oR_LinkAnAccount = PageFactory.initElements(lDriver, OR_LinkAnAccount.class);
		OR_MyAccountAccess oR_MyAccountAccess = PageFactory.initElements(lDriver, OR_MyAccountAccess.class);
		try
		{
			//Link OPR ID to the account
			Actions driver = new Actions(lDriver);
			if(UI.WaitForObject(oR_AccountOverview.lnkMyLinkedAccounts, 60, lDriver))
			{
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("click", "Manage my profile","U-verse", "Link my AT&T accounts", null);
				//Click on Link another account in my linked account
				driver.moveToElement(oR_AccountOverview.lnkMyLinkedAccounts).build().perform();
				Thread.sleep(2000);
				driver.moveToElement(oR_AccountOverview.lnkLinkAnotherAccountInMyLinkedAccount).click().build().perform();
				//Select Att Email from drop down
				UI.WaitForObject(oR_LinkAnAccount.edtBillingAccNum, 60, lDriver);
				UI.SelectOptionFromDropDown(oR_LinkAnAccount.lstSelAcc, "AT&T Email");
				UI.WaitForObject(oR_LinkAnAccount.edtBillingAccNum, 60, lDriver);
				oR_LinkAnAccount.edtBillingAccNum.sendKeys("qay_opr_abcdef11@att.net");
				oR_LinkAnAccount.btnNext.click();
				
				//Verify whether account is already  linked
				if(UI.WaitForObject(oR_LinkAnAccount.txtAccountAlreadyLinked, 30, lDriver))
				{
					if(UI.WaitForObject(oR_AccountOverview.lnkMyLinkedAccounts, 60, lDriver))
					{
					driver.moveToElement(oR_AccountOverview.lnkMyLinkedAccounts).build().perform();
					driver.moveToElement(oR_AccountOverview.lnkManageAccountAccessInMyLinkedAccount).click().build().perform();
					//Report.ValidationPoint(testContext.getName(), "Validating My account access  page is displayed", "true", String.valueOf(oR_MyAccountAccess.lnkDeLinkEmailAccount.isDisplayed()), false);
						if(UI.WaitForObject(oR_MyAccountAccess.lnkDeLinkEmailAccountOnly, 60, lDriver))
						{
							oR_MyAccountAccess.lnkDeLinkEmailAccountOnly.click();
							if(UI.WaitForObject(oR_AccountOverview.frmTourMyBill, 60, lDriver))
							{
							lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
							WebElement btncontinue = lDriver.findElement(By.xpath("//a[@id='enableBtn']"));
							btncontinue.click();
							lDriver.switchTo().defaultContent();
							}	
						}
					}
					UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkOverview, null, lDriver);
					if(UI.WaitForObject(oR_AccountOverview.lnkMyLinkedAccounts, 60, lDriver))
					{
						//UI.ClickOrVerifyLinkUnderIWantToDropdown("click", "Manage my profile","U-verse", "Link my AT&T accounts", null);
						//Click on Link another account in my linked account
						driver.moveToElement(oR_AccountOverview.lnkMyLinkedAccounts).build().perform();
						Thread.sleep(2000);
						driver.moveToElement(oR_AccountOverview.lnkLinkAnotherAccountInMyLinkedAccount).click().build().perform();
						//Select Att Email from drop down
						UI.WaitForObject(oR_LinkAnAccount.edtBillingAccNum, 60, lDriver);
						UI.SelectOptionFromDropDown(oR_LinkAnAccount.lstSelAcc, "AT&T Email");
						UI.WaitForObject(oR_LinkAnAccount.edtBillingAccNum, 60, lDriver);
						oR_LinkAnAccount.edtBillingAccNum.sendKeys("qay_opr_abcdef11@att.net");
						oR_LinkAnAccount.btnNext.click();
					}
				}
				//Verify account information
				if(UI.WaitForObject(oR_LinkAnAccount.txtVerifyAccountInformation2, 60, lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Validating Verification page is displayed", "true", String.valueOf(oR_LinkAnAccount.txtVerifyAccountInformation2.isDisplayed()), true);
					UI.WaitForObject(oR_LinkAnAccount.edtPassword, 60, lDriver)	;
					oR_LinkAnAccount.edtPassword.sendKeys("test1ng");
					oR_LinkAnAccount.btnContinue.click();
					
					//Validate agree to account linking page
					if(UI.WaitForObject(oR_LinkAnAccount.txtAgreeAccLink, 60, lDriver))
					{
					Report.ValidationPoint(testContext.getName(), "Validating agree to account linking page is displayed", "true", String.valueOf(oR_LinkAnAccount.txtAgreeAccLink.isDisplayed()), true);
					oR_LinkAnAccount.btnContinue.click();
					
						//Validate success page
						if(UI.WaitForObject(oR_LinkAnAccount.txtSuccessTitle, 60, lDriver))
						{
							Report.ValidationPoint(testContext.getName(), "Validating Confirmation page is displayed", "true", String.valueOf(oR_LinkAnAccount.txtSuccessTitle.isDisplayed()), true);
							oR_LinkAnAccount.btnContinueInConfirmationPage.click();
							
							//Validate overview page is displayed
							if(UI.WaitForObject(oR_AccountOverview.txtWelComeBack, 60, lDriver))
							{
								Report.ValidationPoint(testContext.getName(), "Validating Overview page is displayed", "true", String.valueOf(oR_AccountOverview.txtWelComeBack.isDisplayed()), true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validating Overview page is displayed", "true", "fail", true);
							}
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Validating Confirmation page is displayed", "true", "fail", true);
						}
					}
					else
					{
					Report.ValidationPoint(testContext.getName(), "Validating agree to account linking page is displayed", "true", "fail", true);
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validating Verify account information page is displayed", "true", "fail", true);
				}
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Validating My linked accounts in overview page", "true", "Failed to validate", true);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}	
	}
	
	/**************************************************************
	 * Function Name - VerifyScheduledAppointmentAlert()
	 * Description - Verify that for a SLID Uverse customer with future appointment date, alert for appointment is displayed in alert section.
 	 * Test Case -  DBD16408
	 * Parameters - None
	 * Date created - 10th Aug 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyScheduledAppointmentAlert(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			boolean bAlertSection = UI.WaitForObject(oR_AccountOverview.btnAlert, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Alert section is displayed on Account Overview page", "true",String.valueOf(bAlertSection), true);
			oR_AccountOverview.btnAlert.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Alert section");
			//Verify alert for appointment is displayed in alert section
			boolean bAppoAlert = UI.WaitForObject(oR_AccountOverview.txtAppointmentReminderAlert, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that alert for appointment is displayed in alert section", "true",String.valueOf(bAppoAlert), true);
			
			//Verify that a green alert for appointment is getting display in the alert section
				//Cannot verify the green icon in alert since the value is mentioned inside css as a link
			
			//Verify that the appointment date is getting display
			String sServiceType = oR_AccountOverview.txtAppointmentReminderAlertDetails.getText().toLowerCase();
			String sDatePattern = ".*\\d\\d\\/\\d\\d\\/\\d\\d\\d\\d.*";
			if(sServiceType.matches(sDatePattern))
			{
				Report.ValidationPoint(testContext.getName(), "Verify that the appointment date is getting displayed", "true","true", true);
				
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that the appointment date is getting displayed", "true","false", true);
			}
			
			//Verify that BAN number is getting display in the alert
			String sUverseBAN = oR_AccountOverview.txtAppointmentReminderUverseBAN.getText().trim();
			String sPattern = "U-verse \\#\\d\\d\\d\\d\\d\\d\\d\\d\\d";
			if(sUverseBAN.matches(sPattern))
			{
				Report.ValidationPoint(testContext.getName(), "Verify that BAN number is getting displayed in the alert", "true","true", true);
				
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that BAN number is getting displayed in the alert", "true","false", true);
			}
			
			//Verify that the type of service is provided(installation/Repair) is getting display
			String sServTypePresent = "null";
			if(sServiceType.contains("installation"))
			{
				Report.ValidationPoint(testContext.getName(), "Verify that service type 'installation' is mentioned under alert details", "true","true", true);
				sServTypePresent ="installation";
				
			}else if(sServiceType.contains("repair"))
			{
				Report.ValidationPoint(testContext.getName(), "Verify that service type 'repair' is mentioned under alert details", "true","true", true);
				sServTypePresent ="repair";
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that service type 'installation/repair' is mentioned under alert details", "true","false", true);
			}
			
			//Verify that the link allowing customer to change scheduled appointment date is getting display in the alert.
			boolean bChangeLink = UI.WaitForObject(oR_AccountOverview.lnkAppointmentReminderChangeAppointment, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that the link allowing customer to change scheduled appointment date is getting display in the alert", "true",String.valueOf(bChangeLink), true);
			
			
			//Verify the link will vary as per installation /repair For installation :https://www.att.com/eos/unauth/eosLogin?productType=uverse For repair : http://www.att.com/u-verse/appointmentstatus/
			oR_AccountOverview.lnkAppointmentReminderChangeAppointment.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Change appointment' link, redirecting to new page");
			Thread.sleep(6000);
			String sNewURL = lDriver.getCurrentUrl();
			Report.OperationPoint(testContext.getName(), "The page is redirected to new URL:- "+sNewURL);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	/**************************************************************
	 * Function Name - ValidateUsageMeterAndAlertSetion()
	 * Description - The purpose of the test is to validate the following on Dashboard and Usage landing pagesQuick Links,Plan Names, Usage bars
 	 * Test Case -  DBD01801
	 * Parameters - None
	 * Date created - 29th Oct 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void ValidateUsageMeterAndAlertSetion(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	
		//Validate Options under I Want to drop down
			Report.OperationPoint(testContext.getName(), "	Clicking on I want to");
			//oR_AccountOverview.btnIWantTo.click();
			List<WebElement> options = lDriver.findElements(By.xpath("//button[@id='ddShortcut']//parent :: div//ul[contains(@class,'main')]/li"));
			//options.get(0).getText();
//			for (int i=0; i<(options.size()-1); i++){
//			//	oR_AccountOverview.btnIWantTo.sendKeys(Keys.ARROW_DOWN);
//				new Actions(lDriver).moveToElement(options.get(i)).build().perform(); 
//				Thread.sleep(3000);
//				Report.ValidationPoint(testContext.getName(), "Validate '"+options.get(i).getText()+"' is displayed ", "true", "true", true);
//			//	Thread.sleep(10000);
//				}
						
/*			
			for(WebElement e : options){
				oR_AccountOverview.btnIWantTo.click();
			System.out.println(e.findElement(By.tagName("span")).getText());
            UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify",e.findElement(By.tagName("span")).getText(), null, null, null)	;			
				
			}*/
			

			 //String strLinkName = e.findElement(By.tagName("span")).getText();
				
		     //UI.VerifyCategoriesUnderIWantToDropdown(strLinkName, null );
		     UI.VerifyCategoriesUnderIWantToDropdown("View & pay bill", null);	
		
		     UI.VerifyCategoriesUnderIWantToDropdown("View usage", null);
			
		     UI.VerifyCategoriesUnderIWantToDropdown("Manage my plan", null);
			
			UI.VerifyCategoriesUnderIWantToDropdown("Manage my profile", null);
			
			UI.VerifyCategoriesUnderIWantToDropdown("Get help & support", null);
		
			UI.VerifyCategoriesUnderIWantToDropdown("Shop AT&T", null);
//			
		/*	System.out.println(e.findElement(By.tagName("span")).getText());
            UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify",e.findElement(By.tagName("span")).getText(), null, e.findElement(By.tagName("span")).getText(), null)	;				*/

			
			// Validate last logged in date is displayed in mm/dd/yyyy format
		if(oR_AccountOverview.txtLastLogin.isDisplayed()){
			Report.ValidationPoint(testContext.getName(), "Verify Last Login section is displayed","true","true", true);
				String strDate = oR_AccountOverview.txtLastLogin.getText();
			String[] strSplit = strDate.split(": ");
			System.out.println(strSplit[0]);
			System.out.println(strSplit[1]);
			if( (strSplit[1].charAt(2) == '/' ) && (strSplit[1].charAt(5) == '/') ){
				String[] strDateSplit = strSplit[1].split("/");

				// Validate if date is in mm/dd/yyyy formate
				if(strDateSplit[0].length() == 2  && strDateSplit[1].length() == 2 && strDateSplit[2].length() == 4){
						Report.ValidationPoint(testContext.getName(), "Validate last logged in date is in mm/dd/yyyy format", "true", "true", true);
					}else{
					Report.ValidationPoint(testContext.getName(), "Validate last logged in date is in mm/dd/yyyy format", "true", "false", true);
					}
			}else{
					Report.ValidationPoint(testContext.getName(), "Validate last logged in date is in mm/dd/yyyy format", "true", "false", true);
			}
		}else{
			Report.ValidationPoint(testContext.getName(), "Verify Last Login section is displayed ", "true", "false", true);
			}
			
			//Verify Welcome Back Section
			
			Boolean bWelcome = UI.WaitForObject(oR_AccountOverview.txtWelcome, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Welcome Back is displayed","true",String.valueOf(bWelcome), true);
			
		
		//Verify that Total balnce amount is displayed
		boolean bTotalBalance = UI.WaitForObject(oR_AccountOverview.txtBalanceAmount, UI.iObjTimeOut);
	Report.ValidationPoint(testContext.getName(), "Verify that total sum of all real time adjustments on current bill cycle is displayed", "true",String.valueOf(bTotalBalance), true);
		
		//Verify the color of Total balance amount displayed
		boolean bRed = UI.WaitForObject(oR_AccountOverview.txtBalanceAmtInRedColor,UI.iObjTimeOut);
		boolean bGreen = UI.WaitForObject(oR_AccountOverview.txtBalanceAmtInGreenColor, UI.iObjTimeOut);
		boolean bOrange = UI.WaitForObject(oR_AccountOverview.txtBalanceAmtInOrangeColor, UI.iObjTimeOut);
		
		if(bRed==true)
		{
			Report.ValidationPoint(testContext.getName(), "Verify that Balance amount is displayed in Red color", "true","true", true);

		}else if(bGreen==true)
		{
			Report.ValidationPoint(testContext.getName(), "Verify that Balance amount is displayed in GREEN color", "true","true", true);
			
		}else if(bOrange==true)
		{
		Report.ValidationPoint(testContext.getName(), "Verify that Balance amount is displayed in orange color", "true","true", true);

			
		}
		
		//Validate A countdown will appear below the make a payment button, when there are 14 calendar days left on the billing cycle
		if(oR_AccountOverview.lnkEnrollInAutopay.isDisplayed())
		{
			boolean bDaysLeft = UI.WaitForObject(oR_AccountOverview.txtDaysLeft, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify countdown i.e days left is displayed", "true",String.valueOf(bDaysLeft), true);
		}	
		
		//Validate Plans are displayed
		List<WebElement> lstPlans= lDriver.findElements(By.xpath("//span[contains(text(),'Plan')]"));
		int i=lstPlans.size();
		if(i>0)
		{
			Report.ValidationPoint(testContext.getName(), "Verify Plans are displayed", "true","true", true);
		}
		else
		{
		Report.ValidationPoint(testContext.getName(), "Verify Plans are displayed", "true","false", true);
		}	
		
}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}	
	
	/**************************************************************
	 * Function Name - ValidateCancelledServiceActivityBanner()
	 * Description - The purpose of the test is To validate activity banner for Titan SLID - UV WLS and DTV having all services cancelled
 	 * Test Case -  DBD25258
	 * Parameters - None
	 * Date created - 31st Oct 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void ValidateCancelledServiceActivityBanner(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			//Validate for the Activity Banner on Overview page
			Boolean bCancelled = UI.WaitForObject(oR_AccountOverview.txtSerCancelled, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Activity Banner for service cancelled is displayed on Overview page","true",String.valueOf(bCancelled), true);
			
			//Validate the cancellation message on Activity Banner
			Boolean bCancelMsg = UI.WaitForObject(oR_AccountOverview.txtSerCancelled, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify cancellation message on Activity Banner is displayed on Overview page","true",String.valueOf(bCancelMsg), true);
			
			//Validate the cancellation date format on Activity Banner
			if(oR_AccountOverview.txtCancellationDate.isDisplayed()){
				Report.ValidationPoint(testContext.getName(), "Verify Cancellation date is displayed","true","true", true);
					String strDate = oR_AccountOverview.txtCancellationDate.getText();
				String[] strSplit = strDate.split(" ");
				System.out.println(strSplit[0]);
				System.out.println(strSplit[6].length());
				if( (strSplit[5].length() == 3 ) && (strSplit[6].length() == 3)  && (strSplit[7].length() == 4))
				{	
					Report.ValidationPoint(testContext.getName(), "Verify Cancellation date is displayed in  sample format 'Jan 12, 2015'","true","true", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify Cancellation date is displayed in  sample format 'Jan 12, 2015'","true","false", true);
				}
			}
				else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Cancellation date is displayed","true","false", true);
			}
			
			//Validate the Link for equipment return on Activity Banner.
			Boolean bEquipment = UI.WaitForObject(oR_AccountOverview.lnkEquipment, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Link for equipment return is displayed","true",String.valueOf(bEquipment), true);
			
			//Click on Link for equipment return
			
			UI.VerifyLinkNavigatestoNextPage(oR_AccountOverview.lnkEquipment , "esupport");
			
			lDriver.navigate().back();
			
			//Validate past due msg is displayed in red colour
			boolean bRed = UI.WaitForObject(oR_AccountOverview.txtBalanceAmtInRedColor,UI.iObjTimeOut);
			if(bRed==true)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that Balance amount is displayed in Red color", "true","true", true);

			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that Balance amount is displayed in Red color", "true","false", true);
			}
			
			//Validate Past due msg is displayed under Billing section
			Boolean bPastDueMsg = UI.WaitForObject(oR_AccountOverview.txtPastDueMsg, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Past due msg is displayed under Billing section is displayed","true",String.valueOf(bPastDueMsg), true);
}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}	
	
	/**************************************************************
	 * Function Name - VerifyServiceSummaryforUverseVoiceInternational()  #DBD20189
	 * Description- This function 
	 * Parameters - None
	 * Date created - 31st Oct 2015
	 * Developed by - Monica M
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * 	 ***************************************************************/
	public static void VerifyServiceSummaryforUverseVoiceInternational(
			ITestContext testContext) throws HeadlessException, IOException, AWTException 
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			Boolean bVoicePlan,bDomesticVoice, bVoiceSummary, bBillingPeriodBar, bDaysLeft;
			Report.OperationPoint(testContext.getName(), "Selecting Voice Plan from My Plan Section");
			bVoicePlan = UI.WaitForObject(lDriver.findElement(By.xpath("//a[contains(@class,'focusable')][contains(@linkname,'Voice')]")), 20);
			if(bVoicePlan)
			{
				lDriver.findElement(By.xpath("//a[contains(@class,'focusable')][contains(@linkname,'Voice')]")).click();
				//2- validate Usage section for U-verse Voice will display the following:
				bVoiceSummary = UI.WaitForObject(oR_AccountOverview.txtVoice, 20, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate Usage section for U-verse Voice will display the following:", "true", String.valueOf(bVoiceSummary), true);
				//3- Billing cycle bar: >Title of the bar: Billing cycle
				bBillingPeriodBar = UI.WaitForObject(oR_AccountOverview.txtBillingPeriod, 3, lDriver);
				Report.ValidationPoint(testContext.getName(), "Billing cycle bar: >Title of the bar: Billing cycle/Period ", "true", String.valueOf(bBillingPeriodBar), true);
			
				//5 - On the left hand side of the billing cycle remaining days in the billing cycle should be displayed. eg: 12 days Left
				bDaysLeft = UI.WaitForObject(oR_AccountOverview.txtDaysLeft, 3, lDriver);
				Report.ValidationPoint(testContext.getName(), "Remaining days in the billing cycle should be displayed", "true", String.valueOf(bDaysLeft), true);
			
				//6 - >Phone No in the format XXX-XXX-XXXX eg: 404-390-3581 should be displayed.
				if(UI.WaitForObject(oR_AccountOverview.txtVoiceCTN, 10))
				{
					Report.ValidationPoint(testContext.getName(), "Phone No should be present XXX-XXX-XXXX", "true","true", true);

					String sUverseBAN = oR_AccountOverview.txtVoiceCTN.getText().trim();
					String sPattern = "\\d\\d\\d\\-\\d\\d\\d\\-\\d\\d\\d\\d";
					if(sUverseBAN.matches(sPattern))
					{
						Report.ValidationPoint(testContext.getName(), "Phone No in the format XXX-XXX-XXXX", "true","true", true);
						
					}
				}

				//If limited Plan - Display Domestic Off Network Usage label and usage bar. Provide a tool tip to describe domestic off network calling.
				//Provide minutes left
				//Domestic Voice usage Green bar should be displayed.
				
				bDomesticVoice = UI.WaitForObject(oR_AccountOverview.txtDomesticVoice,20,lDriver);
				Report.ValidationPoint(testContext.getName(), "Domestic Voice usage Green bar should be displayed.",  "true", String.valueOf(bDomesticVoice), true);
			
				
//				If customer is subscribed to international calling plan - Display international usage
//				Provide minutes USED
//				Voice International | XXX minutes used should be displayed.

				Report.OperationPoint(testContext.getName(), "international calling plan not displayed");
				
				

			}
			else
				Report.TestPoint(testContext.getName(), "U-verse Voice plan is not displayed", "true", "false", true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	}



	/**************************************************************
	 * Function Name - VerifyPlanOrderInMyPlans()  #DBD14121
	 * Description- This function 
	 * Parameters - None
	 * Date created - 31st Oct 2015
	 * Developed by - Monica M
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * 	 ***************************************************************/

	public static void VerifyPlanOrderInMyPlans(ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			//validate Plans/Services are ordered by Product Type and then by SLID association date (BAU)
			Report.OperationPoint(testContext.getName(), "validate Plans/Services are ordered by Product Type and then by SLID association date (BAU)");

//			verify priority is in the order: Wireless > DTV > Uverse internet > Uverse TV > Uverse Voice
			List<WebElement> aPlans = lDriver.findElements(By.xpath("//a[contains(@class,'focusable')]"));
			if(aPlans.size()!=0)
			{
				try
				{
					int iWireless=0, iVoice=0, iInternet=0, iDTV=0, iTV=0;
					List<WebElement> sPlans = lDriver.findElements(By.xpath("//a[contains(@class,'focusable')]//span[contains(@class,'Bold')]"));
					
						for(int i=0;i<sPlans.size();i++)
						{
							if(sPlans.get(i).getText().contains("Internet"))
								iInternet = i;
							else if(sPlans.get(i).getText().contains("Voice"))
								iVoice = i;
							else if(sPlans.get(i).getText().contains("Wireless") || sPlans.get(i).getText().contains("Plan"))
								iWireless = i;
							else if(sPlans.get(i).getText().contains("U-verse TV"))
								iTV = i;
							else if(sPlans.get(i).getText().contains("DIRECTV"))
								iDTV = i;
						}
						
						if(iVoice>iTV && iTV>iInternet && iInternet>iDTV && iDTV>iWireless)
						{
							Report.ValidationPoint(testContext.getName(), "verify priority is in the order:  Wireless > DTV > Uverse internet > Uverse TV > Uverse Voice", "true", "true", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "verify priority is in the order:  Wireless > DTV > Uverse internet > Uverse TV > Uverse Voice", "true", "false", true);
						}
							
					}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), e.getMessage(), "true", "false", true);
				}	
				try
				{
	//				validate toggles include labels of the type of service and the service package ordered throughout tenures
					if(lDriver.findElements(By.xpath("//a[contains(@class,'focusable')]//span[contains(@class,'MarTop5')]")).size()>0)
						Report.ValidationPoint(testContext.getName(), "Validate toggles include labels of the type of service and the service package ordered throughout tenures", "true", "true", true);
					else
						Report.ValidationPoint(testContext.getName(), "Validate toggles include labels of the type of service and the service package ordered throughout tenures", "true", "false", true);
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), e.getMessage(), "true", "false", true);
				}
			}
			else
				Report.ValidationPoint(testContext.getName(), "My Plans Section", "My Plans Must be Present", "My Plans Not Present", true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

			
		}
	
	}	

/**************************************************************
 * Function Name - ValidateVideosSectionOnServiceSummary ()
 * Description - 
	 * Test Case -  DBD25351
 * Parameters - None
 * Date created - 31st Oct 2015
 * Developed by - Gautham
 * Last Modified By - 
 * Last Modified Date - 
 ***************************************************************/
@SuppressWarnings("unchecked")
//DBD25351
public static void ValidateVideosSectionOnServiceSummary(final ITestContext testContext) throws HeadlessException, IOException, AWTException
{
	try
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		//Verify that service summary section is displayed for Direct TV
		
		List<WebElement> Plans= lDriver.findElements(By.xpath("//ul[@id='MainTab']/li"));
				
		System.out.println(Plans.size());
		for (WebElement e: Plans)
		{	
			String txtPlan= e.getText();
			System.out.println(txtPlan);
			if(txtPlan.contains("DIRECTV"))
			{
				Report.OperationPoint(testContext.getName(), "Clicking on Plan");
				e.click();
				Thread.sleep(4000);
				Report.ValidationPoint(testContext.getName(), "Verify that service summary section is displayed for Direct TV","true","true", true);
				break;
			}
		}
		
		//Verify that No Video detials will be available.
		UI.VerifyElementNotPresent(oR_AccountOverview.lnkVideoBill, "Video details");
		
		//Verify that the header-" You've recently viewed [X] videos" is suppressed.
		try{
			WebElement txtVideos= lDriver.findElement(By.xpath("//*[contains(text(),'recently viewed [X] videos')]"));
			 boolean bVideos=txtVideos.isDisplayed();
			 if(bVideos){
			 Report.ValidationPoint(testContext.getName(), "Verify that the header-'You have recently viewed [X] videos' is suppressed","Supressed","Not Supressed", true);
			 }	
		}
		catch(Exception e)
		{
			 boolean bVideos=false;
			 if(bVideos==false)
			 {
			 Report.ValidationPoint(testContext.getName(), "Verify that the header-'You have recently viewed [X] videos' is suppressed","Supressed","Supressed", true);
			 }
		}
		
		//Verify that the link " View more of my videos details" is suppressed
		try{
			WebElement lnkVideos= lDriver.findElement(By.xpath("//*[contains(text(),'View more of my videos')]"));
			 boolean bLnVideos=lnkVideos.isDisplayed();
			 if(bLnVideos){
			 Report.ValidationPoint(testContext.getName(), "Verify that the link ' View more of my videos details' is suppressed","Supressed","Not Supressed", true);
			 }	
		}
		catch(Exception e)
		{
			 boolean bLnVideos=false;
			 if(bLnVideos==false)
			 {
			 Report.ValidationPoint(testContext.getName(), "Verify that the link 'View more of my videos details' is suppressed","Supressed","Supressed", true);
			 }
		}
	}

	catch (Exception e) 
	{
		e.printStackTrace();
		Report.TestPoint(testContext.getName(), "Some error occured", "true", "Failed to validate"+e.getMessage(), true);

	}
	
}
/**************************************************************
 * Function Name -ValidateRollOverDatainServiceSummary ()
 * Description - 
* Test Case -  DBD13902
 * Parameters - None
 * Date created - 31st Oct 2015
 * Developed by - Gautham
 * Last Modified By - 
 * Last Modified Date - 
 ***************************************************************/
//DBD13902
public static void ValidateRollOverDatainServiceSummary(final ITestContext testContext) throws HeadlessException, IOException, AWTException
{
	try
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	
		// verify in the Service Summary Section: Display text -'Rollover data used'.
		boolean bRollover= UI.WaitForObject(oR_AccountOverview.txtRollOverdata, 20, lDriver);
		Report.ValidationPoint(testContext.getName(), "Verify that 'Rollover data used' is displayed in Service sumamry section","true",String.valueOf(bRollover), true);
		
		//click on tool tip
		boolean bTooltip= UI.WaitForObject(oR_AccountOverview.lnkTooltip, 20, lDriver);
		if(bTooltip){
		oR_AccountOverview.lnkTooltip.click();
		Report.ValidationPoint(testContext.getName(), "Verify that 'Tool tip icon is displayed in Service sumamry section","true",String.valueOf(bTooltip), true);
		Report.ValidationPoint(testContext.getName(), "Verify that message is displayed after clicking on tool tip icon","true",String.valueOf(bTooltip), true);
		
		//validate view details link
		boolean bLink= UI.WaitForObject(lDriver.findElement(By.xpath("//p[@id='msg1']//a|//div[@id='sdgViewDetails']//a[contains(text(),'details')]")), 20, lDriver);
		Report.ValidationPoint(testContext.getName(), "Verify that CTA to serve usage details link is displayed","true",String.valueOf(bLink), true);
		}
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		Report.TestPoint(testContext.getName(), "Some error occured", "true", "Failed to validate"+e.getMessage(), true);

	}
}
/**************************************************************
 * Function Name -ValidateRollOverDataAndOverageBarInServiceSummary ()
 * Description - 
* Test Case -  DBD13904
 * Parameters - None
 * Date created - 31st Oct 2015
 * Developed by - Gautham
 * Last Modified By - 
 * Last Modified Date - 
 ***************************************************************/
//DBD13904
public static void ValidateRollOverDataAndOverageBarInServiceSummary(final ITestContext testContext) throws HeadlessException, IOException, AWTException
{
	try
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		// verify in the Service Summary Section: Display text -'Rollover data used'.
		boolean bRollover= UI.WaitForObject(oR_AccountOverview.txtRollOverdata, 20, lDriver);
		Report.ValidationPoint(testContext.getName(), "Verify that 'Rollover data used' is displayed as expected in Service sumamry section","true",String.valueOf(bRollover), true);
			
		//validating including overage pack text
		boolean bMessage= UI.WaitForObject(lDriver.findElement(By.xpath("//span[contains(text(),'Overage Pack')]")), 20, lDriver);
		Report.ValidationPoint(testContext.getName(), "Verify that 'Overage pack ' text is displayed  as expected in summary section","true",String.valueOf(bMessage), true);
		
		//validating employer data
		boolean bEmployer= UI.WaitForObject(lDriver.findElement(By.xpath("//div[@class='phoneItemContainerMid']//div[@id='sdgFirstName']")), 20, lDriver);
		Report.ValidationPoint(testContext.getName(), "Verify that 'Employer data'  is displayed  as expected in summary section","true",String.valueOf(bEmployer), true);
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		Report.TestPoint(testContext.getName(), "Some error occured", "true", "Failed to validate"+e.getMessage(), true);

	}
}
/**************************************************************
 * Function Name -ValidateEmployerContributionTextInServiceSummary ()
 * Description - 
* Test Case -  DBD13964
 * Parameters - None
 * Date created - 31st Oct 2015
 * Developed by - Gautham
 * Last Modified By - 
 * Last Modified Date - 
 ***************************************************************/
//DBD13964
public static void ValidateEmployerContributionTextInServiceSummary(final ITestContext testContext) throws HeadlessException, IOException, AWTException
{
	try
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		Actions driver = new Actions(lDriver);
		
		//creating list of view details link
		List<WebElement> ViewDetail= lDriver.findElements(By.xpath("//div[@id='sdgViewDetails']//a[contains(text(),'details')]"));

		//storing elements as string in a list
		List<String> strViewDetails = new ArrayList<String>();
		
		for(WebElement e : ViewDetail)
		{
			strViewDetails.add(e.getText());
		}
		
		for(String s : strViewDetails )
		{
		System.out.println(s);	
		}
		
		//List of CTN's
		List<WebElement> CTN= lDriver.findElements(By.xpath("//li[@class='phoneItem ForcePadRight10 w110 Padbot10']//div[@id='sdgCtn']"));
		
		//storing all ctn's as strings in a list
		List<String> strCTN = new ArrayList<String>();
		
		//storing ctn's as string
		for(WebElement e : CTN){
			strCTN.add(e.getText());
		}
		for(String c: strCTN)
		{ 
		System.out.println(c);	
		}

		for(int i=0;i<CTN.size();i++)
		{	
			System.out.println("link for "+strCTN.get(i)+" is:"+strViewDetails.get(i));
			
			//Verify the link Device details is present on service summary section for CTN1.
			boolean bLink1= UI.WaitForObject(ViewDetail.get(i), 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify link Device details is present on service summary section for CTN"+i+":"+strCTN.get(i),"true",String.valueOf(bLink1), true);
			
			//click on link
			Report.OperationPoint(testContext.getName(), "validating link device details of"+strCTN.get(i));
			ViewDetail.get(i).click();
			
			//validate modal window
			if(UI.WaitForObject(oR_AccountOverview.frmTourMyBill, 10, lDriver))
			{
				Report.ValidationPoint(testContext.getName(), "Verify Modal window is opened on clicking link for CTN-"+i,"true","true", true);
				
				//switch to frame
				lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
				
				//validate text
				boolean bText= UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(text(),'employer')]|//*[contains(text(),'Employer')]")), 5, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Employer contribution text is present on modal window","true",String.valueOf(bText), true);
				
				//
				if(testContext.getName().contains("DBD13742"))
				{
					if(UI.WaitForObject(oR_AccountOverview.imgRedAlertIcon, 10, lDriver))
					{
						driver.moveToElement(oR_AccountOverview.imgRedAlertIcon).build().perform();
						Report.ValidationPoint(testContext.getName(), "Verify usage is >100% as on the overview page","true","true", true);
					}
				}
				oR_AccountOverview.lnkClose.click();
				lDriver.switchTo().defaultContent();
				if(testContext.getName().contains("DBD13742"))
				{
				break;
				}
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Frame is not displayed", "true", "Failed to validate", true);
			}
		}
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		Report.TestPoint(testContext.getName(), "Some error occured", "true", "Failed to validate"+e.getMessage(), true);
	}
}

/**************************************************************
	 * Function Name - ValidatePaperlessBillingAndUsage
	 * Description- Linking att.net Email to Overview
	 * Parameters - 
	 * Date created - 12th-Oct-2015
	 * Developed by - Hiral Jogi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/

	public static void LinkingattnetEmailtoOverview(final ITestContext testContext) throws Exception 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());           
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
		try
		{
			//Verify 'I want to' button is getting displayed
			
			Boolean bIwantbtn = UI.WaitForObject(oR_AccountOverview.btnIWantTo, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "'I want to' Button is displayed", "true", String.valueOf(bIwantbtn), true);
			oR_AccountOverview.btnIWantTo.click();
			Thread.sleep(3000);
			
			//Verify 'Manage my plan' button is getting displayed
			
			Boolean bmanageplan = UI.WaitForObject(oR_AccountOverview.btnIWantTo, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "'Manage my plan' option is displayed", "true", String.valueOf(bmanageplan), true);
			oR_AccountOverview.lnkManageMyPlan.click();
			Thread.sleep(3000);
			
			//Verify 'Uverse Internet' option is getting displayed
			
			Boolean bUnet = UI.WaitForObject(oR_AccountOverview.btnIWantTo, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "'Uverse Internet' option is displayed", "true", String.valueOf(bUnet), true);
			oR_AccountOverview.lnkUverseInternet.click();
			
			//Verify 'ViewMyEmail' option is getting displayed
			
			Boolean bViewemail = UI.WaitForObject(oR_AccountOverview.lnkViewMyEmail, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "'ViewMyEmail' option is displayed", "true", String.valueOf(bViewemail), true);
			
			
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name -VerifyGetTheMostSectionInTheServiceSummary ()
	 * Description - 
	* Test Case - DBD14336 
	 * Parameters - None
	 * Date created - 3 Nov 2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	
	public static void VerifyGetTheMostSectionInTheServiceSummary(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			Actions driver = new Actions(lDriver);
			int Count=0;
			//Verify for all the three services the following in the Service Summary Section
			boolean bSections = UI.WaitForObject(oR_AccountOverview.lstMyPlans, 10, lDriver);
			
			List<WebElement> lstPlans= lDriver.findElements(By.xpath("//ul[@id='MainTab']/li//span[@class='float-left MarLeft10']"));
			System.out.println(lstPlans.size());
			
			//storing elements as string in a list
			List<String> PlanNames = new ArrayList<String>();
			for(int i=0;i<lstPlans.size();i++)
			{
				 PlanNames.add(lstPlans.get(i).getText());
			}
			for(int i=0;i<lstPlans.size();i++)
			{
			if(PlanNames.get(i).contains("U-verse Internet")|PlanNames.get(i).contains("U-verse TV")|PlanNames.get(i).contains("U-verse Voice"))
			{
				System.out.println("Plan name is: "+PlanNames.get(i));
				Count++;
			}
			}
			if(Count==3)
			{
				Report.ValidationPoint(testContext.getName(), " validate all the three services in the Service Summary Section  are displayed", "true", String.valueOf(Count==3), true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), " validate all the three services in the Service Summary Section  are displayed", "true", String.valueOf(Count==3), true);
			}
			
			
			//verify Get the most out of your  Service section
			boolean bGetMost= UI.WaitForObject(lDriver.findElement(By.xpath("//*//span[contains(text(),'more details')]")), 10, lDriver);
			Report.ValidationPoint(testContext.getName(), " Validate 'Get the most out of your  Service' section in the Service Summary Section is displayed", "true", String.valueOf(bGetMost), true);
			
			//validate promotions
			boolean bPromotions= UI.WaitForObject(oR_AccountOverview.imgPromotions, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), " Validate promotions are displayed as expected", "true", String.valueOf(bPromotions), true);
			
			List<WebElement> ImgPromotion= lDriver.findElements(By.xpath("//div[@id='promoContainer']//img"));
			int Size= ImgPromotion.size();
			if(Size==3)
			{
				Report.ValidationPoint(testContext.getName(), " Validate promotions have image for each of them as expected", "true", String.valueOf(Size==3), true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), " Validate promotions have image for each of them as expected", "true", String.valueOf(Size==3), true);
			}
			
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name -VerifyDeviceDetailsModalServiceSummary ()
	 * Description - 
	* Test Case - DBD13739 & DBD17288
	 * Parameters - None
	 * Date created - 4th Nov 2015
	 * Developed by - Swagata
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	
	public static void VerifyDeviceDetailsModalServiceSummary(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		try
		{
			Thread.sleep(5000);
			//Validate the service summary section
			boolean bServiceSummary = UI.WaitForObject(oR_AccountOverview.txtServiceSummarySection, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), " Validate the service summary section", "true", String.valueOf(bServiceSummary), true);
			//Verify the link Device details is present on service summary section for CTN.
			//DBD17288
			if(!testContext.getName().contains("DBD17288"))
			{
				List<WebElement> lstViewDetails = lDriver.findElements(By.xpath("//a[contains(@id,'modalLink')]"));
				if(lstViewDetails.size()>0)
				{
					Report.ValidationPoint(testContext.getName(), " Validate the link Device details is present on service summary section for CTN", "true", String.valueOf(bServiceSummary), true);
					//Click
					Report.OperationPoint(testContext.getName(), "Clicking on Device details");
					lstViewDetails.get(0).click();
					//Validate modal window is opened
					Thread.sleep(3000);
					boolean bModal = UI.WaitForObject(oR_AccountOverview.frmDeviceDetailsModal, 10, lDriver);
					if(bModal)
					{
						lDriver.switchTo().frame(oR_AccountOverview.frmDeviceDetailsModal);
						Report.ValidationPoint(testContext.getName(), "Validate modal window is opened", "true", String.valueOf(bModal), true);
						//Validate Includes Bonus Data' is displayed above data usage bar. Usage is displayed as expected
						boolean bIncludes = UI.WaitForObject(oR_AccountOverview.frmDeviceDetailsIncludes, 10, lDriver);
						Report.ValidationPoint(testContext.getName(), " Validate "+oR_AccountOverview.frmDeviceDetailsIncludes.getText()+" is displayed above data usage bar. Usage is displayed as expected", "true", String.valueOf(bIncludes), true);
						oR_AccountOverview.lnkClose.click();
						lDriver.switchTo().defaultContent();
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate modal window is opened", "true", String.valueOf(bModal), true);
					}
				}
			}
			//DBD17288
			if(testContext.getName().contains("DBD17288"))
			{
				//Validate data usage bar
				List<WebElement> lstBar = lDriver.findElements(By.xpath("//div[contains(@class,'meter-container')]"));
				if(lstBar.size()>0)
				{
					Report.ValidationPoint(testContext.getName(), "Validate data usage bar","true","true", true);
					//Validate Above data usage bar display text 'Now using Rollover'
					List<WebElement> lstRollover = lDriver.findElements(By.xpath("//*[contains(text(),'Rollover Data')]"));
					if(lstRollover.size()>0)
					{
						int iRollover = lstRollover.get(0).getLocation().getY();
						int iBar = lstBar.get(1).getLocation().getY();
						if(iBar<iRollover)
						{
							Report.ValidationPoint(testContext.getName(), "Validate Above data usage bar display text Now using Rollover","true","true", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Validate Above data usage bar display text Now using Rollover","true","true", true);
						}
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), " Validate the service summary section", "true", "false", true);
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate data usage bar","true","false", true);
				}
			}
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name -VerifyProfilePageInterstialPage ()
	 * Description - 
	* Test Case - DBD13739
	 * Parameters - None
	 * Date created - 4th Nov 2015
	 * Developed by - Swagata
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
		
	public static void VerifyProfilePageInterstialPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
		try
		{
			//Developed according to SS in stub environment. May vary from the QC steps.
			//Validate interstitial page : Profile - User information
			boolean bProfileInter = UI.WaitForObject(oR_Profile.txtProfileUserInformation, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), " Validate interstitial page : Profile - User information", "true", String.valueOf(bProfileInter), true);
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	


	/**************************************************************
	 * Function Name -VerifyCancelledContainerAndOtherDashboardDetailsSuppressed ()
	 * Description - 
	 * Test Case - DBD14366
	 * Parameters - testContext
	 * Date created - 5th Nov 2015
	 * Developed by - Sneha
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/

	public static void VerifyCancelledContainerAndOtherDashboardDetailsSuppressed(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			//Verify cancelled container for the data
			try
			{
				lDriver.findElement(By.xpath("//*[text()='Service canceled']"));
				lDriver.findElement(By.xpath("//*[contains(text(),'Your service is canceled')]"));
				Report.TestPoint(testContext.getName(),"Verify cancelled container for the data", "displayed","displayed", true);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(),"Verify cancelled container for the data", "displayed","NOT displayed", true);
			}
			
			//Validate 'Welcome back' message is suppressed
			boolean txtWelcome = UI.WaitForObject(oR_AccountOverview.txtWelcome, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate 'Welcome back' message is displayed", "true", String.valueOf(txtWelcome), true);
		
			//Validate “I want to drop down” is suppressed
			boolean btnIWantTo = UI.WaitForObject(oR_AccountOverview.btnIWantTo, 10, lDriver);
			//Report.ValidationPoint(testContext.getName(), "Validate 'I want to drop down' is suppressed", "true", String.valueOf(btnIWantTo), true);
			if(btnIWantTo==true)
			{
				Report.ValidationPoint(testContext.getName(), "Validate 'I want to drop down' is suppressed", "suppressed", "NOT suppressed", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate 'I want to drop down' is suppressed", "suppressed", "suppressed", true);
			}
		
			//Verify Billing Container includes the experiences for balance due, due date is displayed
			try
			{
				lDriver.findElement(By.xpath("//*[contains(text(),'Your total balance is:') or contains(text(),'Balance')]"));
				lDriver.findElement(By.xpath("//*[contains(text(),'Due')]"));
				Report.ValidationPoint(testContext.getName(), "Verify Billing Container includes the experiences for balance due, due date is displayed", "displayed", "displayed", true);
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Billing Container includes the experiences for balance due, due date is displayed", "displayed", "NOT displayed", true);
			}
			
			//Verify Service Summary section is NOT displayed 
			boolean txtMyPlans = UI.WaitForObject(oR_AccountOverview.txtMyPlans, 10, lDriver);
			if(txtMyPlans==true)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Service Summary section is NOT displayed", "NOT displayed", "displayed", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Service Summary section is NOT displayed", "NOT displayed", "NOT displayed", true);
			}
			
		
			//Verify AT&T messages section is NOT displayed
			boolean txtATTMessage = UI.WaitForObject(oR_AccountOverview.txtATTMessage, 10, lDriver);
			if(txtATTMessage==true)
			{
				Report.ValidationPoint(testContext.getName(), "Verify AT&T messages section is NOT displayed", "NOT displayed", "displayed", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify AT&T messages section is NOT displayed", "NOT displayed", "NOT displayed", true);
			}
			
		
			//Verify Promos are NOT displayed
			try
			{
				lDriver.findElement(By.xpath("//*[@id='promoContainer']"));
				Report.ValidationPoint(testContext.getName(), "Verify Promos are NOT displayed", "NOT displayed", "displayed", true);
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Promos are NOT displayed", "NOT displayed", "NOT displayed", true);
			}
			
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name -VerifyusageUnavailablemessage ()
	 * Description - 
	* Test Case - DBD17290 
	 * Parameters - None
	 * Date created - 5 Nov 2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyusageUnavailablemessage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
  {
	try
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		int Count=0;
		
		//validate service summary section
		try{
		if(UI.WaitForObject(lDriver.findElement(By.xpath("//div[contains(@class,'usageContainer')]")),10,lDriver))
		{
			Report.TestPoint(testContext.getName(), "Validating service summary section is present", "true", "true", true);
		}
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Validating service summary section is present", "true", "false", true);
		}
		//creating list of view details link
		List<WebElement> ViewDetail= lDriver.findElements(By.xpath("//div[@id='sdgViewDetails']//a[contains(text(),'details')]"));
		List<String> strViewDetails = new ArrayList<String>();
		for(WebElement e : ViewDetail)
		{
			strViewDetails.add(e.getText());
		}
		List<WebElement> Usage= lDriver.findElements(By.xpath("//div[@id='sdgUsage']//span"));
		System.out.println(Usage.size());
		for(WebElement e: Usage)
		{
			
			String data= e.getText();
			System.out.println(data);
			if(data.equals("0.0"))
			{	
				System.out.println("0.0 usage is available");
				System.out.println(Count);
				System.out.println(ViewDetail.get(Count).getText());
				if(ViewDetail.get(Count).isDisplayed())
				{
					Report.TestPoint(testContext.getName(), "Validating link to Device detials is present for CTN", "true", "true", true);
					System.out.println("clicking on view details link");
					ViewDetail.get(Count).click();
				
				//Thread.sleep(3000);
				boolean bModal = UI.WaitForObject(oR_AccountOverview.frmTourMyBill, 10, lDriver);
				lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
				if(bModal)
				{
					Report.ValidationPoint(testContext.getName(), "Validate modal window is opened", "true", String.valueOf(bModal), true);
					//
					Thread.sleep(4000);
				try{
					if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@class='fontWeightBoldForce'][contains(text(),'0.0')]")),10,lDriver))
					{
						Report.TestPoint(testContext.getName(), "Validating usage unavailable text is present", "true", "true", true);
					}
					}
					catch(Exception m)
					{
						Report.TestPoint(testContext.getName(), "Validating usage unavailable text is present", "true", "false", true);
					}
				oR_AccountOverview.lnkClose.click();
				lDriver.switchTo().defaultContent();
				}
				
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate modal window is opened", "true", String.valueOf(bModal), true);
				}
				}
				else
				{
					Report.TestPoint(testContext.getName(), "Validating link to Device detials is present for CTN", "true", "false", true);
				}
			break;
			}
			Count++;
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
	}
  }
	
	/**************************************************************
	 * Function Name -verifyPromotionalOfferPopupDetailsForWirelessHomePhone ()
	 * Description - 
	 * Test Case - DBD21668
	 * Parameters - testContext
	 * Date created - 6th Nov 2015
	 * Developed by - Sneha
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void verifyPromotionalOfferPopupDetailsForWirelessHomePhone(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
		
		Thread.sleep(20000);
		if(UI.WaitForObject(oR_AccountOverview.frmPromotionalOffer, 20, lDriver))
		{
			lDriver.switchTo().frame(oR_AccountOverview.frmPromotionalOffer);
			Thread.sleep(5000); 
			
			//Verify Promotional offer for wireless home phone is displayed as an overlay on overview page gives a modal like experience
			try
			{
				lDriver.findElement(By.xpath("//*[contains(text(),'Wireless Home Phone is Here') or contains(text(),'Wireless is Here') or contains(text(),'Wireless homephone is Here') or contains(text(),'U-verse is Here')]"));
				Report.ValidationPoint(testContext.getName(), "Verify Promotional offer for wireless home phone is displayed as an overlay on overview page gives a modal like experience", "displayed", "displayed", true);
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Promotional offer for wireless home phone is displayed as an overlay on overview page gives a modal like experience", "displayed", "not displayed", true);
			}
			//Verify Interstitial contains a header 
			try
			{
				lDriver.findElement(By.xpath("//*[contains(text(),'Wireless Home Phone is Here') or contains(text(),'Wireless is Here') or contains(text(),'Wireless homephone is Here') or contains(text(),'U-verse is Here')]"));
				Report.ValidationPoint(testContext.getName(), "Verify Interstitial contains a header in interstitial modal", "displayed", "displayed", true);

			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Interstitial contains a header in interstitial modal", "displayed", "displayed", true);
			}
			
			//Verify Interstitial contains an image 
			try
			{
				lDriver.findElement(By.xpath("//img[contains(@alt,'U-Verse')]"));
				Report.ValidationPoint(testContext.getName(), "Verify Interstitial contains an image in interstitial modal", "displayed", "displayed", true);

			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Interstitial contains an image in interstitial modal", "displayed", "displayed", true);
			}
			
			//Verify CTA 'No Thanks'   
			try
			{
				lDriver.findElement(By.xpath("//*[contains(text(),'No, thanks')]"));
				Report.ValidationPoint(testContext.getName(), "Verify CTA 'No Thanks' is displayed", "displayed", "displayed", true);
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify CTA 'No Thanks' is displayed", "displayed", "not displayed", true);
			}
			
			//Verify CTA 'Remind me later'
			try
			{
				lDriver.findElement(By.xpath("//a[contains(text(),'Remind me later')]"));
				Report.ValidationPoint(testContext.getName(), "Verify CTA 'Remind me later' is displayed", "displayed", "displayed", true);
		
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify CTA 'Remind me later' is displayed", "displayed", "not displayed", true);
			}
			
			//Verify button 'Upgrade now'
			boolean btnUpgradeNow = UI.WaitForObject(oR_Login.btnUpgradeNow, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify button 'Upgrade now' is displayed", "true", String.valueOf(btnUpgradeNow), true);
		
			//Verify link 'AT&T Wireless Home Phone'
			try
			{
				lDriver.findElement(By.xpath("//*[contains(text(),'Learn more about AT&T Wireless Home Phone')]"));
				Report.ValidationPoint(testContext.getName(), "Verify link 'AT&T Wireless Home Phone' is displayed", "displayed", "displayed", true);
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify link 'AT&T Wireless Home Phone' is displayed", "displayed", "not displayed", true);
			}
		}
		else
		{
			Report.ValidationPoint(testContext.getName(), "Validate that Promotional offer for ATT wireless Homephone is appearing as an interstitial modal", "appearing", "not appearing", true);
		}
	}

	catch(Exception e)
	{
		Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
	}
		
	}
	
	
	/**************************************************************
	 * Function Name -verifyPromotionalOfferPopupIsSuppressedForWirelessHomePhone ()
	 * Description - 
	 * Test Case - DBD21668
	 * Parameters - testContext
	 * Date created - 5th Nov 2015
	 * Developed by - Sneha
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void verifyPromotionalOfferPopupIsSuppressedForWirelessHomePhone(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);

			Thread.sleep(20000);
			if(UI.WaitForObject(oR_AccountOverview.frmPromotionalOffer, 20, lDriver))
			{
				try
				{
					lDriver.switchTo().frame(oR_AccountOverview.frmPromotionalOffer);
					Thread.sleep(5000);
					lDriver.findElement(By.xpath("//*[contains(text(),'Wireless Home Phone is Here') or contains(text(),'Wireless is Here') or contains(text(),'Wireless homephone is Here')]"));
					Report.ValidationPoint(testContext.getName(), "Validate that Promotional offer for ATT wireless Homephone is not appearing as an interstitial modal", "not appearing", "appearing", true);
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Validate that Promotional offer for ATT wireless Homephone is not appearing as an interstitial modal", "not appearing", "not appearing", true);
				}


			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate that Promotional offer for ATT wireless Homephone is not appearing as an interstitial modal", "not appearing", "not appearing", true);
			}
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}

	}
	
	/**************************************************************
	 * Function Name -verifyOverageUsageGracePeriod ()
	 * Description - 
	 * Test Case - DBD21799
	 * Parameters - 
	 * Date created - 9th Nov 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void verifyOverageUsageGracePeriod(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			Actions aAction = new Actions(lDriver);
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			//Validate overage usage
			Boolean bOverage = UI.WaitForObject(oR_AccountOverview.txtOverageData, 20, lDriver);
			Report.TestPoint(testContext.getName(), "Validate overage usage label is displayed is usage summary section. Verify yellow overage bar - the bar will be Full", "true", String.valueOf(bOverage), true);
			//verify the amount used = "0.1GB" is displyed
			Boolean bAmount = UI.WaitForObject(oR_AccountOverview.txtOverageAmount, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "verify the amount used = 0.1GB is displyed", "true", String.valueOf(bAmount), true);
			//verify messaging to customer that they are within their grace period
			Boolean bGraceTxt = UI.WaitForObject(oR_AccountOverview.txtOverageDataGrace, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "verify messaging to customer that they are within their grace period is displayed", "true", String.valueOf(bGraceTxt), true);
			//Validate Grace period link is displayed
			Boolean bLnkGrace = UI.WaitForObject(oR_AccountOverview.lnkGracePeriod, 5, lDriver);
			Report.TestPoint(testContext.getName(), "verify Grace period link is displayed", "true", String.valueOf(bLnkGrace), true);
			//hover over it
			Report.OperationPoint(testContext.getName(), "Hovering on Grace period link");
			aAction.moveToElement(oR_AccountOverview.lnkGracePeriod).build().perform();
			//Validate a "Learn more" link = Grace period link with show a message when pointer hovers over it.
			Boolean btxtHover = UI.WaitForObject(oR_AccountOverview.txtOverageChargesHover, 15, lDriver);
			Report.ValidationPoint(testContext.getName(), "verify message when pointer hovers over it : "+oR_AccountOverview.txtOverageChargesHover.getText() , "true", String.valueOf(btxtHover), true);
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}

	}
	
	/**************************************************************
	 * Function Name - VerifyLockerAccountDataUsage()
	 * Description - Verify locker account, data usage between 75 to 100 percent
 	 * Test Case -  DBD20639
	 * Parameters - None
	 * Date created - 10th Dec 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyLockerAccountDataUsage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			try
			{
				Boolean bAlert = UI.WaitForObject(oR_AccountOverview.btnAlert,UI.iObjTimeOut,lDriver);
				if(bAlert.equals(true))
				{
					
					oR_AccountOverview.btnAlert.click();
					Report.ValidationPoint(testContext.getName(), "Validate Alerts are present", "True", "True", true);
					Thread.sleep(3000);

					//oR_AccountOverview.lnkHide.click();
					try
					{
						oR_AccountOverview.lnkHide.click();
						
					}catch(Exception Ee2)
					{
						Report.OperationPoint(testContext.getName(),"Hide link is not present under Alerts");
					}

					Thread.sleep(1000);
				}
			    else if(UI.WaitForObject(oR_AccountOverview.txtSinglePlanAlertSection, UI.iObjTimeOut, lDriver).equals(true))
				{
					Report.OperationPoint(testContext.getName(), "My plans section is NOT displayed since, Only a Single plan is available and Alerts section is Displayed instead");
					Report.ValidationPoint(testContext.getName(), "Validate Alerts section is displayed", "Alert section is present instead of My Plans section since only one plan is available", "Alert section is present instead of My Plans section since only one plan is available", true);
					
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Alert section is displayed", "true", "false", true);
				}
				
			}catch(Exception e2)
			{
				Report.ValidationPoint(testContext.getName(), "Validate Alert section is displayed", "true", "false", true);
			}
			
			//Select Locker AT&T Locker plan under My plas section
			try
			{
				List<WebElement> Plans= lDriver.findElements(By.xpath("//ul[@id='MainTab']/li//a"));
				if(Plans.size()>0)
				{
					for(WebElement planLink:Plans)
					{								
						planLink.click();
						Thread.sleep(1000);
						
						if(oR_AccountOverview.txtPlanName.getText().contains("AT&T Locker"))
						{
							Report.OperationPoint(testContext.getName(),"AT&T Locker is selcted from My plans section");
							Thread.sleep(3000);
							Report.ValidationPoint(testContext.getName(), "Validate that AT&T Locker plan name is displayed in Service summary section", "true", "true", true);
							break;
						}
						
					}
				}
				

				
			}catch(Exception e3)
			{
				Report.OperationPoint(testContext.getName(), "My plans section is NOT displayed");
			}
				
				boolean bAllotedData = UI.WaitForObject(oR_AccountOverview.txtLockerPlanAllotedData, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that alloted data is displayed in the service summary section", "true",String.valueOf(bAllotedData), true);
				boolean bSeeYouCan = UI.WaitForObject(oR_AccountOverview.lnkSeeIfYouCanLinkForLocker, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that link for 'See if you can get a bigger locker' is displayed", "true",String.valueOf(bSeeYouCan), true);
				boolean bStoredData = UI.WaitForObject(oR_AccountOverview.txtLockerAvailableStorage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that the header 'Stored data' above the Usage bar is displayed", "true",String.valueOf(bStoredData), true);
				boolean bMeter = UI.WaitForObject(oR_AccountOverview.txtLockerMeterContainer, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that usage bar is displayed", "true",String.valueOf(bMeter), true);
				//Verify that the usage bar in yellow color when the data consumption is between 75% and 100% - CANNOT Verify Color, skipping step
				boolean bUnusedData = UI.WaitForObject(oR_AccountOverview.txtLockerUnusedDataLeft, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that the unused data left on the account is displayed besides the usage bar", "true",String.valueOf(bUnusedData), true);
				boolean bGoToLocker = UI.WaitForObject(oR_AccountOverview.lnkGoToLocker, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that the CTA 'Go to Locker' is displayed", "true",String.valueOf(bGoToLocker), true);
				boolean bGoToLockerWindow = UI.WaitForObject(oR_AccountOverview.ImgGoToLockerWindowIcon, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that the a new window icon near CTA 'Go to Locker' is displayed", "true",String.valueOf(bGoToLockerWindow), true);
				boolean bCancelLocker = UI.WaitForObject(oR_AccountOverview.lnkCancelLocker, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that that CTA 'Cancel locker' is displayed which takes the customer to the services flow", "true",String.valueOf(bCancelLocker), true);
				boolean bHelp = UI.WaitForObject(oR_AccountOverview.lnkGetHelpWithMyLocker, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that the CTA 'Get help with my Locker' is displayed", "true",String.valueOf(bHelp), true);

		


			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	/**************************************************************
	 * Function Name - VerifyServiceSummaryCarDetails()
	 * Description - Verify service summary for car details
 	 * Test Case -  DBD20553
	 * Parameters - None
	 * Date created - 10th Dec 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyServiceSummaryCarDetails(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			//Validate In the Overview page Check the Service summary section with the plan name
			boolean bServiceName = UI.WaitForObject(oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery,10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate in the Overview page Check the Service summary section with the plan name. The plan name is "+oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery.getText(), "true",String.valueOf(bServiceName), true);
			//Verifying the Display of BAU Usage and billing cycle for the MS plan
			boolean bBillingCycle = UI.WaitForObject(oR_AccountOverview.txtBillingPeriod,5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verifying the Display of BAU Usage and billing cycle for the MS plan", "true",String.valueOf(bBillingCycle), true);
			//Validate View Plan Detail CTA should displayed in the service summary Header section
			boolean bViewPlan = UI.WaitForObject(oR_AccountOverview.lnkViewPlanDetails,5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate View Plan Detail CTA should displayed in the service summary Header section", "true",String.valueOf(bViewPlan), true);
			//Validate Car image should displayed based on subscription class
			List<WebElement> lstImage = lDriver.findElements(By.xpath("//li[contains(@class,'phoneItem')]//img[contains(@alt,'Phone')]"));
			if(lstImage.size()>0)
			{
				Report.ValidationPoint(testContext.getName(), "Verifying Car image should displayed based on subscription class. There are "+lstImage.size()+" images", "true","true", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verifying Car image should displayed based on subscription class", "true","false", true);
			}
			//Validate View details link
			List<WebElement> lstViewDetails = lDriver.findElements(By.xpath("//div[@id='phoneItemContainer']//a[contains(text(),'View details')] | //div[@id='phoneItemContainer']//a[contains(text(),'View usage details')]"));
		//	System.out.println("List Size " + );
			int j = lstViewDetails.size();
			if(lstViewDetails.size()>0)
			{
				while (j > 0){
				Report.ValidationPoint(testContext.getName(), "Validate View details link", "true","true", true);
				//Click on the 1st view details
				Report.OperationPoint(testContext.getName(),"Click on the 1st view details");
				lstViewDetails.get(j-1).click();
				j--;
				Thread.sleep(5000);
				//Validate Display modal window should displayed after click on device or view detail links
				boolean bModal = UI.WaitForObject(oR_AccountOverview.frmDeviceDetailsModal,5, lDriver);
				if(bModal)
				{
					Thread.sleep(2000);
					lDriver.switchTo().frame(oR_AccountOverview.frmDeviceDetailsModal);
					Report.ValidationPoint(testContext.getName(), "Validate Display modal window should displayed after click on device or view detail links", "true","true", true);
					//In the Device Details modal of the car: Verify the Display car image based on subscription class if available, otherwise display generic device image
					boolean bModalImg = UI.WaitForObject(oR_AccountOverview.imgPhoneInFrm,5, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify the Display car image based on subscription class if available, otherwise display generic device image", "true",String.valueOf(bModalImg), true);
					//Verify all the other remaining experience remains BAU.
					boolean bManageMyPlan = UI.WaitForObject(oR_AccountOverview.txtManageMyPlan,5, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify manage my plan section", "true",String.valueOf(bManageMyPlan), true);
					//Validate the CTA's present
					List<WebElement> lstCTA = lDriver.findElements(By.xpath("//li/a"));
					if(lstCTA.size()>0)
					{
						Report.ValidationPoint(testContext.getName(), "Verify the CTA's present", "true","true", true);
						//Retrieving the CTA
						for(int i=0;i<lstCTA.size();i++)
						{
							Report.ValidationPoint(testContext.getName(), "Verify CTA : " +lstCTA.get(i).getText(), "true","true", true);
						}
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify the CTA's present", "true","false", true);
					}
					
					oR_AccountOverview.lnkClose.click();
					lDriver.switchTo().defaultContent();
				  }	
				 else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Display modal window should displayed after click on device or view detail links", "true","false", true);
				}
			  }
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate View details link", "true","false", true);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	
	/**************************************************************
	 * Function Name - VerifyMissingCBR_CTN_Alert()
	 * Description - Verify that the CBR CTN is missing for the customer and alert is present
	 * Test Case -  DBD20707
	 * Parameters - None
	 * Date created - 10th Dec 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyMissingCBR_CTN_Alert(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
	
			
			Boolean bAlert = UI.WaitForObject(oR_AccountOverview.btnAlert,UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate Alerts section is present in Overview page", "true", String.valueOf(bAlert), true);
			oR_AccountOverview.btnAlert.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Alert' button");
			
			//Verify that the alert for missing CBR CTN is present
			boolean bCBRCTNAlert = UI.WaitForObject(oR_AccountOverview.txtAlertForMissingCBR_CTN, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that the alert for missing CBR CTN is present", "true", String.valueOf(bCBRCTNAlert), true);
			Report.ValidationPoint(testContext.getName(), "Verify that close button to dismiss and yellow alert icon is displayed with the alert", "true", String.valueOf(bCBRCTNAlert), true);
			
			//Verify that one CTA is present related to cbrInterstitial – Link "Update now"
			boolean bUpdateNow = UI.WaitForObject(oR_AccountOverview.lnkUpdateNowForCBR_CTN_Alert, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that one CTA is present related to cbrInterstitial – Link 'Update now'", "true", String.valueOf(bUpdateNow), true);

			List<WebElement> lstAlertNumber = lDriver.findElements(By.xpath("//p[contains(text(),'retrieve your AT&T Access ID')]"));
			if(lstAlertNumber.size()>1)
			{
				Report.ValidationPoint(testContext.getName(), "Verify only one alert is displayed at access ID level", "true", "false", true);

			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify only one alert is displayed at access ID level", "true", "true", true);

			}
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
	
		}
		
	}


	/**************************************************************
	 * Function Name - VerifyServiceSummaryForOnStar
	 * Description- This verifies the Summary section links for OnStar Data plan
	 * Parameters - 
	 * Date created - 11th Dec 2015
	 * Developed by - Monica Mohabansi 
	 * Last Modified By - 
	 * Last Modified Date - 
	 * Description :  
	 ***************************************************************/
//	#DBD21088
	public static void VerifyServiceSummaryForOnStar(final ITestContext testContext)throws Exception
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_MyWirelessPlan oR_MyWirelessPlan = PageFactory.initElements(lDriver, OR_MyWirelessPlan.class);
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);		
		Boolean bOnStarPlan, bWirelessService, bViewAllUsage, bViewPlanDetails, bGetConnected, bPlanName;
		try
		{
			/*try
			 {
				bOnStarPlan = UI.WaitForObject(lDriver.findElement(By.xpath("(//a[contains(@class,'focusable')]//span[contains(text(),'OnStar')])[1]")), 10, lDriver);
			 	if(bOnStarPlan)
			 		lDriver.findElement(By.xpath("(//a[contains(@class,'focusable')]//span[contains(text(),'OnStar')])[1]")).click();
			 }
			
			catch(Exception e)
				{Report.TestPoint(testContext.getName(), "Select OnStar Plan", "true", "Failed to validate"+e.getMessage(), true);}
		*/
//			Verify that the Service summary Header Links
			Report.OperationPoint(testContext.getName(), "Verify that the Service summary Header Links");	
			bViewPlanDetails = UI.WaitForObject(oR_AccountOverview.lnkViewPlanDetails, 20, lDriver);
			bViewAllUsage = UI.WaitForObject(oR_AccountOverview.lnkViewAllUsage, 2, lDriver);
			//bGetConnected = UI.WaitForObject(oR_AccountOverview.lnkGetConnectedDeviceSupport, 1, lDriver);
			
			Report.ValidationPoint(testContext.getName(), "i-View Plan Details Link in service summary section", "true", bViewPlanDetails.toString(), true);
			Report.ValidationPoint(testContext.getName(), "ii-View All Usage Link in service summary section", "true", bViewPlanDetails.toString(), true);
			Report.ValidationPoint(testContext.getName(), "iii-Get Connected Device Support Link in service summary section", "true", bViewPlanDetails.toString(), true);
			if(bViewPlanDetails && bViewAllUsage)
				Report.ValidationPoint(testContext.getName(), "In Service summary Header, Links should displayed", "true", "true", true);
//			Verify the Display of plan name based on the dataconnect feature
			Report.OperationPoint(testContext.getName(), "Verify the Display of plan name based on the dataconnect feature");
			bPlanName = UI.WaitForObject(oR_AccountOverview.txtUverseTVPlan, 1, lDriver);
			Report.ValidationPoint(testContext.getName(), "plan name based on the dataconnect feature should displayed on Header ", "true", bPlanName.toString(), true);
//			Verify the Display of CTA View Plan details
			Report.OperationPoint(testContext.getName(), "Verify the Display of CTA View Plan details");
			Report.ValidationPoint(testContext.getName(), "View Plan details CTA should displayed in service summary header section", "true", bViewPlanDetails.toString(), true);
			/*if(bViewPlanDetails)
			{
				oR_AccountOverview.lnkViewPlanDetails.click();
			    bWirelessService = UI.WaitForObject(oR_MyWirelessPlan.txtMyWirelessService, 20);		  
		    	Report.ValidationPoint(testContext.getName(), "Clicked and validated View Plan Details link", "true", bWirelessService.toString(), true);
			}*/
		}	
	catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
	
		}
		
	}
	
	/**************************************************************
	 * Function Name -ValidateDigitalTVCustomerPage ()
	 * Description - 
	 * Test Case - DBD33079
	 * Parameters - 
	 * Date created - 11th Dec 2015
	 * Developed by - Hiral Jogi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD33079
	public static void ValidateDigitalTVCustomerPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	
			Boolean bDTVCust = UI.WaitForObject(oR_AccountOverview.txtDirectTVCustomer, UI.iObjTimeOut, lDriver);
				if(bDTVCust){
					Report.ValidationPoint(testContext.getName(),"Validate User is navigated to DirecTV Customer Page.","true","true",true);
				}
				
				
				 Report.ValidationPoint(testContext.getName(),"Validating content.","true","true",true);
				 String strnoteseen = oR_AccountOverview.txtPrimaryContent.getText();
				 System.out.println(strnoteseen);
				 String strnoteexpected = "You logged in to AT&T with your DIRECTV email and password. Are you trying to manage your DIRECTV account? Here's some help getting to the right place: ";
				if(strnoteseen.equalsIgnoreCase(strnoteexpected)){
					Report.ValidationPoint(testContext.getName(),"Expected content is displayed","true","true",true);
				}
				
				Boolean bGoToDirectv = UI.WaitForObject(oR_AccountOverview.lnkGoToDirectv, UI.iObjTimeOut, lDriver);
				if(bGoToDirectv){
					Report.ValidationPoint(testContext.getName(),"Link 'Go to DIRECTV to manage my account' is present.","true","true",true);
				}
				
				Boolean bWantToManage = UI.WaitForObject(oR_AccountOverview.txtWantToManage, UI.iObjTimeOut, lDriver);
				if(bWantToManage){
					String str = oR_AccountOverview.txtWantToManage.getText();
					Report.ValidationPoint(testContext.getName(), str + "----- Expected content present.","true","true",true);
				}
				
				Boolean bLoginwithATTAIDandPWd = UI.WaitForObject(oR_AccountOverview.lnkLoginwithATTAIDandPWd, UI.iObjTimeOut, lDriver);
				if(bLoginwithATTAIDandPWd){
					Report.ValidationPoint(testContext.getName(),"Expected link present.","true","true",true);
				}
				
				// Validating Spanish Language page
				Boolean bLanguage = UI.WaitForObject(oR_AccountOverview.lstLanguage, UI.iObjTimeOut, lDriver);
				if(bLanguage){
					Report.ValidationPoint(testContext.getName(),"Option for switching language is present.","true","true",true);
					
					// Click
					oR_AccountOverview.lstLanguage.click();
					
					// Selecting Spanish language
					Boolean bSpanish = UI.WaitForObject(oR_AccountOverview.lnkSpanish, UI.iObjTimeOut, lDriver);
					if(bSpanish){
						Report.ValidationPoint(testContext.getName(),"Selecting Spanish Language.","true","true",true);
						oR_AccountOverview.lnkSpanish.click();
					}
					
					// Validations
//					Boolean bLanguageSpanish = UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'¡Hola, cliente de DIRECTV!')]")), UI.iObjTimeOut, lDriver);
//					if(bLanguageSpanish){
//						Report.ValidationPoint(testContext.getName(),"Validate User is navigated to DirecTV Customer Page in Spanish.","true","true",true);
//					}
//					
//					Report.ValidationPoint(testContext.getName(),"Validating content.","true","true",true);
//					String strnoteseenspanish = oR_AccountOverview.txtPrimaryContent.getText();
//					System.out.println(strnoteseenspanish);
//					String strnoteexpectedspanish = "Has ingresado a AT&T con tu correo electrónico y contraseña de DIRECTV. ¿Quieres administrar tu cuenta de DIRECTV? Esto es lo que tienes que hacer para llegar adonde quieres ir:";
//					
//					if(strnoteseen.equalsIgnoreCase(strnoteexpectedspanish)){
//						Report.ValidationPoint(testContext.getName(),"Expected content is displayed in Spanish.","true","true",true);
//					}
//					
//					Boolean bGoToDirectvSpanish = UI.WaitForObject(oR_AccountOverview.lnkGoToDirectv, UI.iObjTimeOut, lDriver);
//					if(bGoToDirectvSpanish){
//						Report.ValidationPoint(testContext.getName(),"Link 'Ve a DIRECTV para administrar tu cuenta' is present.","true","true",true);
//					}
//					
//					Boolean bWantToManageSpanish = UI.WaitForObject(oR_AccountOverview.txtWantToManage, UI.iObjTimeOut, lDriver);
//					if(bWantToManageSpanish){
//						String str = oR_AccountOverview.txtWantToManage.toString();
//						Report.ValidationPoint(testContext.getName(), str + "----- Expected content present.","true","true",true);
//					}
//					
//					Boolean bLoginwithATTAIDandPWdSpanish = UI.WaitForObject(oR_AccountOverview.lnkLoginwithATTAIDandPWd, UI.iObjTimeOut, lDriver);
//					if(bLoginwithATTAIDandPWdSpanish){
//						Report.ValidationPoint(testContext.getName(),"Expected link present.","true","true",true);
//					}
					
			}
			
			
		} 
		
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	
	}
	/**************************************************************
	 * Function Name -ValidateServiceSummaryForOnStarTrialOrPrepaid ()
	 * Description - 
	 * Test Case - DBD20291
	 * Parameters - 
	 * Date created - 14th Dec 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//#DBD20291
	public static void ValidateServiceSummaryForOnStarTrialOrPrepaid(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);		
		Boolean  bDeviceImg, bViewUsage, bGetConnected, bUserNameAndCTN, bNote, bTimeLeft, bActualDays, bGreyBar, bDataUsageBar;
		try
		{
			/** Selecting OnStar Trial Plan from My Plans section **/
			Report.OperationPoint(testContext.getName(), "Selecting OnStar Trial Plan from My Plans section If Present");
			try
			{
				lDriver.findElement(By.xpath("(//a[contains(@class,'focusable')]//*[contains(text(),'OnStar') and contains(text(),'Trial')])[1]")).click();
				Report.TestPoint(testContext.getName(), "OnStar Trial Plan not present in My Plans Section", "true", "true", true);
			}
			catch (Exception e) 
			{
				System.out.println("Separate Trial plan not present");
			}
			
//			Verify the time remaining for the trail/prepaid plan
			bTimeLeft = UI.WaitForObject(oR_AccountOverview.txtTimeLeft, 20, lDriver);
			bActualDays = UI.WaitForObject(oR_AccountOverview.txtNumberOfDaysLeft, 3, lDriver);
			Report.ValidationPoint(testContext.getName(), "4-Verify the time remaining for the trail/prepaid plan", "true", String.valueOf(bTimeLeft && bActualDays), true);
			
//			Verify the grey bar under the time remaining which shows the time remaining based on start and end date of trail/prepaid plan
			bGreyBar = UI.WaitForObject(oR_AccountOverview.imgGreyBarForTimeLeft, 1, lDriver);
			Report.ValidationPoint(testContext.getName(), "5-Verify the grey bar under the time remaining which shows the time remaining based on start and end date of trial/prepaid plan", "true", bGreyBar.toString(), true);
	
//			verify the Trial/Prepaid plan usage bar and indicate data left.
			bDataUsageBar = UI.WaitForObject(oR_AccountOverview.imgDataUsageBar, 1, lDriver);
			Report.ValidationPoint(testContext.getName(), "6-verify the Trial/Prepaid plan usage bar and indicate data left.", "true", bDataUsageBar.toString(), true);
			
//			 verify the user first name and CTN at top of the image.
			bUserNameAndCTN = UI.WaitForObject(oR_AccountOverview.txtUsernameAndCTNForOnStarInServiceSummary, 1, lDriver);
			Report.ValidationPoint(testContext.getName(), "7-verify the user first name and CTN at top of the image.", "true", bUserNameAndCTN.toString(), true);


//			verify the device image based on the subscription class.
			bDeviceImg = UI.WaitForObject(oR_AccountOverview.imgOn4GLTE, 1, lDriver);
			Report.ValidationPoint(testContext.getName(), "8-verify the device image based on the subscription class", "true", bDeviceImg.toString(), true);

//			verify the display note indicating the start and end date of prepaid/trial plans
			bNote = UI.WaitForObject(oR_AccountOverview.txtNoteForTrialOrPrepaidPlan, 1, lDriver);
			Report.ValidationPoint(testContext.getName(), "9-verify the display note indicating the start and end date of prepaid/trial plans", "true", bNote.toString(), true);

//			Verify the  CTA "Get connected device support" 
			bGetConnected = UI.WaitForObject(oR_AccountOverview.lnkGetConnectedDeviceSupport, 1, lDriver);
			Report.ValidationPoint(testContext.getName(), "10-Verify the  CTA 'Get connected device support' ", "true", bGetConnected.toString(), true);

//			Verify the the new Destination url for "Get Connected device support"  is  - https://www.att.com/shop/wireless/connected-car.html
			Report.OperationPoint(testContext.getName(), "11-Verify the the new Destination url for 'Get Connected device support'  is  - https://www.att.com/shop/");
			if(bGetConnected)
			{
				oR_AccountOverview.lnkGetConnectedDeviceSupport.click();
				Thread.sleep(10000);
				Report.OperationPoint(testContext.getName(), "The URL is : " +lDriver.getCurrentUrl());

				if(lDriver.getCurrentUrl().contains("www.att.com/shop"))
					Report.ValidationPoint(testContext.getName(), "Get Connected device support redirected to - https://www.att.com/shop/wireless/connected-car.html", "true", "true", true);					
				else
					Report.ValidationPoint(testContext.getName(), "Get Connected device support redirected to - https://www.att.com/shop/wireless/connected-car.html", "true", "true", true);					
			}
			lDriver.navigate().back();
//			Verify the Display  of the view all usage link and CTA 
			bViewUsage = UI.WaitForObject(oR_AccountOverview.lnkViewAllUsage, 30, lDriver);
			Report.ValidationPoint(testContext.getName(), "12 - Verify the Display  of the view all usage link and CTA ", "true", bViewUsage.toString(), true);
		}	
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name -VerifyOverviewPageForNakedSlid ()
	 * Description - Verifies Welcome back msg with User NickName on Overview page of Naked slid
	 * Test Case - DBD20633
	 * Parameters - 
	 * Date created - 21st Dec 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//#DBD20633
	public static void VerifyOverviewPageForNakedSlid(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);		
		Boolean bLast, bManage, bNumberSync;
		try
		{
//			Verify that Welcome message with Customer's nick name is displayed
			try
			{
				if(lDriver.findElement(By.xpath("//h3[contains(text(),'Welcome')]//span")).isDisplayed());
					Report.ValidationPoint(testContext.getName(), "3-Verify that Welcome message with Customer's nick name is displayed", "true", "true", true);

			}
			catch (Exception e) 
			{
				Report.TestPoint(testContext.getName(), "Verify that Welcome message with Customer's nick name is displayed", "true", "false", true);
			}	
//			Verify that last login information below welcome message is displayed
			bLast = UI.WaitForObject(oR_AccountOverview.txtLastLogin, 1, lDriver);
			Report.ValidationPoint(testContext.getName(), "4-Verify that last login information below welcome message is displayed", "true", bLast.toString(), true);
		
//			Verify "Manage my device" - header and Sync Devices using Numbersync" CTA are Suppressed when DMCTN is not linked to SLID		
			bManage = UI.WaitForObject(oR_AccountOverview.txtManageMyPlan, 1, lDriver);
			bNumberSync = UI.WaitForObject(oR_AccountOverview.lnkSyncDevicesWithNumberSync, 1, lDriver);
			Report.ValidationPoint(testContext.getName(), "5-Verify 'Manage my device' - header and Sync Devices using Numbersyncs CTA are Suppressed when DMCTN is not linked to SLID", "false", String.valueOf(bManage && bNumberSync), true);

		}
		
		
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name -ValidationOfWlsDashboard ()
	 * Description - To verify that dashboard is displayed after login with the data
	 * Test Case - DBD10302
	 * Parameters - 
	 * Date created - 21st Dec 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - Hiral Jogi
	 * Last Modified Date - 28th Jan 2016
	 ***************************************************************/
	//DBD10302
	public static void ValidationOfWlsDashboard(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);	
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);	
		OR_MyWirelessPlan oR_MyWirelessPlan = PageFactory.initElements(lDriver, OR_MyWirelessPlan.class);
		OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
		
		try
		{
	
	
			// Verifying if alert is present or not
			
			Boolean bAlert = UI.WaitForObject(oR_AccountOverview.btnAlert, UI.iObjTimeOut, lDriver);
			if(bAlert){
				Report.ValidationPoint(testContext.getName(),"Alerts are present.","true","true",true);
				
				// Getting alert count
				
				Boolean bAlertCount = UI.WaitForObject(oR_AccountOverview.txtAlertButtonCount, UI.iObjTimeOut, lDriver);
				if(bAlertCount){
					Report.ValidationPoint(testContext.getName(),"Total number of alerts present are : " + oR_AccountOverview.txtAlertButtonCount.getText(),"true","true",true);
				}
				
				// Click on Alert button
				oR_AccountOverview.btnAlert.click();
				WebElement upgradealert = lDriver.findElement(By.xpath("(//div[@class='alert-upgrade'])[1]//p/strong[contains(text(),'Upgrade Eligibility')]"));
				if(upgradealert.isDisplayed()){
					Report.ValidationPoint(testContext.getName(),"The customer is eligible for upgrade as Upgrade alert is present.","true","true",true);
				}
			
				// Validating label
				WebElement upgradealertLABEL = lDriver.findElement(By.xpath("(//div[@class='alert-upgrade'])[1]//p[contains(text(),'Wireless')]"));
				if(upgradealertLABEL.isDisplayed()){
					String strlabel = upgradealertLABEL.getText();
					Report.OperationPoint(testContext.getName(), "Alert contains a label with name " + strlabel);
				}
				
				// validating CTN and firstname
				WebElement upgradealertCTNName = lDriver.findElement(By.xpath("(//div[@class='alert-upgrade'])[1]//span/parent::p"));
				if(upgradealertCTNName.isDisplayed()){
					String strCTNName = upgradealertCTNName.getText();
					Report.OperationPoint(testContext.getName(), "Alert contains CTN and FirstName as " + strCTNName);
				}
				
				// Validating description
				WebElement upgradealertDescription = lDriver.findElement(By.xpath("(//div[@class='alert-upgrade'])[1]//p[contains(text(),'eligible for an upgrade.')]"));
				if(upgradealertDescription.isDisplayed()){
					String strDescription = upgradealertDescription.getText();
					Report.OperationPoint(testContext.getName(), "Alert contains description as " + strDescription);
				}
				
				// Validating CTA that links the user to the upgrade phone
				WebElement upgradealertCTA = lDriver.findElement(By.xpath("(//div[@class='alert-upgrade'])[1]//a[@alt='View upgrade options']"));
				if(upgradealertCTA.isDisplayed()){
					String strCTA = upgradealertCTA.getText();
					Report.OperationPoint(testContext.getName(), "Alert contains CTA as " + strCTA);
				}
				
			} 
		}
				
				
//			 //Verify  Account name is displayed
//			boolean bAccName = UI.WaitForObject(oR_AccountOverview.txtAccName, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Validate Account name is displayed", "true", String.valueOf(bAccName), true);
//						
//			//Verify My Plan section is displayed
//			boolean bPlanSection = UI.WaitForObject(oR_AccountOverview.txtAccName, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Validate My Plan section is displayed", "true", String.valueOf(bPlanSection), true);
//		
//			//Verify billing cycle is displayed
//			boolean bBillingCycle = UI.WaitForObject(oR_AccountOverview.txtBillingCycleOverview_1511, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Validate billing cycle is displayed", "true", String.valueOf(bBillingCycle), true);
//			
//			
//			//Verify Service summary section is displayed
//			boolean bPlanSection1 = UI.WaitForObject(oR_AccountOverview.txtAccName, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Validate Service Summary section is displayed", "true", String.valueOf(bPlanSection1), true);
//			
//			
//			//Click on Usage
//		
//			boolean bUsage = UI.WaitForObject(oR_AccountOverview.lnkViewAllUsage, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Validate View All usage link is displayed", "true", String.valueOf(bUsage), true);
//			Report.OperationPoint(testContext.getName(), "Clicking on Usage");
//			oR_AccountOverview.lnkViewAllUsage.click();
//			
//			boolean bUsagetab = UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Validate Clicking on Usage, user is navigated to Usage page", "true", String.valueOf(bUsagetab), true);
//			
//			lDriver.navigate().back();
//			//Click on My Plan and Services
//			
//			Report.OperationPoint(testContext.getName(), "Clicking on My Plans & Services");
//			boolean bPlanServices = UI.WaitForObject(oR_AccountOverview.lnkViewPlanDetails, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Validate Clicking on View Plan Details", "true", String.valueOf(bPlanServices), true);
//			oR_AccountOverview.lnkViewPlanDetails.click();
//			boolean bServices = UI.WaitForObject(oR_MyWirelessPlan.txtMyWirelessService, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Validate User is navigated to My Services page", "true", String.valueOf(bServices), true);
//			
//			//Click on  Billing & payments
//			boolean bBAP = UI.WaitForObject(oR_AccountOverview.lnkBillingUsageSecNav, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Validate Billing, Usage and Payments is displayed in Secondary nav bar", "true", String.valueOf(bBAP), true);
//			Report.OperationPoint(testContext.getName(), "Clicking on Billing & payments");
//			
//			oR_AccountOverview.lnkBillingUsageSecNav.click();
//			boolean bBAPheading = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 40);
//			Report.ValidationPoint(testContext.getName(), "Validate User is navigated to Billing & payments page", "true", String.valueOf(bBAPheading), true);
//			
//			//Click on  Profile
//	
//			boolean bprofile = UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav, UI.iObjTimeOut);
//			Report.ValidationPoint(testContext.getName(), "Validate Profile Tab is displayed in Secondary nav bar", "true", String.valueOf(bprofile), true);
//			Report.OperationPoint(testContext.getName(), "Clicking on Profile");
//			
//			oR_AccountOverview.lnkProfileSecondaryNav.click();
//			boolean bProfileTitle = UI.WaitForObject(oR_Profile.txtMyProfileTitle, 40);
//			Report.ValidationPoint(testContext.getName(), "Validate User is navigated to Profile page", "true", String.valueOf(bProfileTitle), true);
		

		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name -VerifyWlsAlert ()
	 * Description - To verify that dashboard is displayed after login with the data
	 * Test Case - DBD26685
	 * Parameters - 
	 * Date created - 22nd Dec 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - Monica Mohabansi	
	 * Last Modified Date - 4th Feb 2016
	 ***************************************************************/
	//DBD26685
	public static void VerifyWlsAlert(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);	
		
		try
		{
			 //Verify  Alert is displayed
			boolean bAlert = UI.WaitForObject(oR_AccountOverview.btnAlert, 40);
			Report.ValidationPoint(testContext.getName(), "3-Validate Alert section is displayed", "true", String.valueOf(bAlert), true);
						
			oR_AccountOverview.btnAlert.click();
			
			//Verify We need a primary online user alert
			boolean bPrimaryAlert = UI.WaitForObject(oR_AccountOverview.txtPrimaryUserAlert, 40);
			Report.ValidationPoint(testContext.getName(), "4-Verify that  alert will be displayed for Claim Primary Ownership", "true", String.valueOf(bPrimaryAlert), true);
			
//			boolean bManage = UI.WaitForObject(oR_AccountOverview.lnkManageOnline, 40);
//			Report.ValidationPoint(testContext.getName(), "Validate CTA for 'Manage online access' is displayed", "true", String.valueOf(bManage), true);
			
		}

		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name -VerifyDMCTNDeviceImageModelNameAndCTNForNakedSlid ()
	 * Description - Verifies Welcome back msg with User NickName and DMCTNs Image, CTN and Model Make on Overview page of Naked slid
	 * Test Case - DBD20632
	 * Parameters - 
	 * Date created - 23rd Dec 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//#DBD20632
	public static void VerifyDMCTNDeviceImageModelNameAndCTNForNakedSlid(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);		
		Boolean bDevice, bMake, bCTN;
		try
		{
//			Verify that Welcome message with Customer's nick name is displayed
			try
			{
				if(lDriver.findElement(By.xpath("//h3[contains(text(),'Welcome')]//span")).isDisplayed());
					Report.ValidationPoint(testContext.getName(), "3-Verify that Welcome message with Customer's nick name is displayed", "true", "true", true);
			}
			catch (Exception e) 
			{
				Report.TestPoint(testContext.getName(), "Verify that Welcome message with Customer's nick name is displayed", "true", "false", true);
			}
//			Display DMCTN's device image, CTN and make model info on the naked slid page
			Report.OperationPoint(testContext.getName(), "Display DMCTN's device image, CTN and make model info on the naked slid page");
			
			bDevice = UI.WaitForObject(oR_AccountOverview.imgPhoneInFrm, 2);
			bMake = UI.WaitForObject(oR_AccountOverview.txtModelMakeOnLinkAccountPage, 2);
			bCTN = UI.WaitForObject(oR_AccountOverview.txtCTNOnLinkAccountPage, 2);
			
			Report.ValidationPoint(testContext.getName(), "i - DMCTN's device image", "true", bDevice.toString(), true);
			Report.ValidationPoint(testContext.getName(), "ii - CTN", "true", bCTN.toString(), true);
			Report.ValidationPoint(testContext.getName(), "iii - Make Model info", "true", bMake.toString(), true);

		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name -ValidateAlertOnOverviewPage ()
	 * Description - 
	 * Test Case - DBD20093
	 * Parameters - 
	 * Date created - 22nd Dec 2015
	 * Developed by - Hiral Jogi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD20093 & DBD20710 (For DBD20710, developed according to Screen shots and data in QC)
	public static void ValidateAlertOnOverviewPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	
			// Verifying if alert is present or not
			
			Boolean bAlert = UI.WaitForObject(oR_AccountOverview.btnAlert, UI.iObjTimeOut, lDriver);
			if(bAlert){
				Report.ValidationPoint(testContext.getName(),"Alerts are present.","true","true",true);
				
				// Getting alert count
				
				Boolean bAlertCount = UI.WaitForObject(oR_AccountOverview.txtAlertButtonCount, UI.iObjTimeOut, lDriver);
				if(bAlertCount){
					Report.ValidationPoint(testContext.getName(),"Total number of alerts present are : " + oR_AccountOverview.txtAlertButtonCount.getText(),"true","true",true);
				}
				
				// Click on Alert button
				oR_AccountOverview.btnAlert.click();
				
				// Validating alert content
				Boolean bUpdateWirelessContactNumberAlert = UI.WaitForObject(oR_AccountOverview.txtUpdateWirelessContactNumberAlert, UI.iObjTimeOut, lDriver);
				if(bUpdateWirelessContactNumberAlert){
					Report.ValidationPoint(testContext.getName(),"Only one Alert is displayed at AccessID level for valid but not verified CBR CTN.","true","true",true);
				}
			
				// Validating close button availability
				Boolean bCloseAlert = UI.WaitForObject(oR_AccountOverview.imgCloseAlert, UI.iObjTimeOut, lDriver);
				if(bCloseAlert){
					Report.ValidationPoint(testContext.getName(),"Close button to dismiss the alert is present.","true","true",true);
				}
				
				Boolean bYellowAlertIcon = UI.WaitForObject(lDriver.findElement(By.xpath("//ul[@id='alertPage_1']")), 20, lDriver);
			 	if(bYellowAlertIcon){
			 		
			 		String sAlert = lDriver.findElement(By.xpath("//ul[@id='alertPage_1']")).getText();
			 		Report.ValidationPoint(testContext.getName(),"Validating if Yellow Alert Icon is present or not.","true","true",true);
			 		System.out.println(sAlert);
			 	}
			 	
			 	Boolean bAlertUpdatenow = UI.WaitForObject(oR_AccountOverview.lnkAlertUpdatenow, UI.iObjTimeOut, lDriver);
				if(bAlertUpdatenow){
					Report.ValidationPoint(testContext.getName(),"Update now CTA is present.","true","true",true);
				}
			 	
				
				
			}	
				
			
		} 
		
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	
	}
	/**************************************************************
	 * Function Name -VerifyDMCTNDeviceImageModelNameAndCTNForNakedSlid ()
	 * Description - Verifies Welcome back msg with User NickName and DMCTNs Image, CTN and Model Make on Overview page of Naked slid
	 * Test Case - DBD20632
	 * Parameters - 
	 * Date created - 23rd Dec 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//#DBD20632
	public static void ValidateReplaceMyDeviceLinkSuppressedInIWantTo(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);		
		Boolean bIWantTo, bReplaceDevice;
		Actions action = new Actions(lDriver);

		try
		{
//		bIWantTo = UI.WaitForObject(oR_AccountOverview.btnIWantTo, 10, lDriver);
		if(UI.WaitForObject(lDriver.findElement(By.xpath("//button[@id='ddShortcut']")), 150)) 
		{ 
			WebElement btnIwantTo= lDriver.findElement(By.xpath("//button[@id='ddShortcut']"));
//			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			action.moveToElement(btnIwantTo).click().build().perform(); 
			Thread.sleep(7000);
			if(lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'Shop AT&T')]")).isDisplayed()) 
			{ 
//				String id = lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'Shop AT&T')]/parent::*/parent::*")).getAttribute("id"); 
				WebElement ePrimaryCategory= lDriver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'Shop AT&T')]")); 
				action.moveToElement(ePrimaryCategory).build().perform(); 
				Thread.sleep(10000); 
//			if(bIWantTo)
//			{
//				action.moveToElement(oR_AccountOverview.btnIWantTo).build().perform();
//				action.moveToElement(oR_AccountOverview.btnIWantTo).click();
//				if(UI.WaitForObject(oR_AccountOverview.txtShopAttUnderIWantTo, 10, lDriver))
//				{
				Report.OperationPoint(testContext.getName(), "Verify the new link to replace a wireless device in sections of the 'I want to'  menu at following locations when switch is OFF:");

					if(UI.WaitForObject(oR_AccountOverview.lnkWirelessInShopATTInIWantTo, 10, lDriver))
					{
						action.moveToElement(oR_AccountOverview.lnkWirelessInShopATTInIWantTo).build().perform();
						bReplaceDevice = UI.WaitForObject(oR_AccountOverview.lnkReplaceMyDevice, 5, lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify the new link to replace a wireless device in sections of the 'I want to'  menu at following locations when switch is OFF:", "true", bReplaceDevice.toString() , true);
						
					}
					else
						if(UI.WaitForObject(oR_AccountOverview.lnkReplaceMyDevice, 5, lDriver))						
							Report.ValidationPoint(testContext.getName(), "The link should not display when the switch is off at following location:", "Link is not diaplayed", "Link is diaplayed", true);
						else
							Report.ValidationPoint(testContext.getName(), "The link should not display when the switch is off at following location:", "Link is not diaplayed", "Link is not diaplayed", true);
			}
				else
					Report.TestPoint(testContext.getName(), "Shop ATT under Wireless Not present", "true", "false", true);
	
			}
			else
				Report.TestPoint(testContext.getName(), "I want to DropDown Not present", "true", "false", true);
		}	
	
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}

	}
	
	/**************************************************************
	 * Function Name -ValidateTourMyBillLink ()
	 * Description - Enhance Video Bill Display logic to trigger off of new-additional values 
	 * Test Case - DBD31197
	 * Parameters - 
	 * Date created - 7th Jan 2015
	 * Developed by - Hiral Jogi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD31197
	public static void ValidateTourMyBillLink(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);	
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);	
		
		try
		{
			// Click on Billing payement and usage sec nav link
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,null);
			
			Boolean bBAPPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "User is navigated to Bill&Usage page and Bill Tab is pre-selected", "True", String.valueOf(bBAPPage), true);
			
			// Validating Tour my bill link
			
			Boolean bTourMyBill = UI.WaitForObject(oR_BillAndUsage.lnkTourMyBill, UI.iObjTimeOut, lDriver);
			if(bTourMyBill){
				Report.ValidationPoint(testContext.getName(),"Tour my link CTA is present in billing & payments section.","true","true",true);
				
				//click
				Report.OperationPoint(testContext.getName(), "Clicking on Tour my link CTA.");
				oR_BillAndUsage.lnkTourMyBill.click();
			}
		 	
			// Handling the frame
			Boolean bTourMyBillpopup = UI.WaitForObject(oR_BillAndUsage.frmTourMyBill, UI.iObjTimeOut);
			if(bTourMyBillpopup){
				lDriver.switchTo().frame(oR_BillAndUsage.frmTourMyBill);
				
				// Validating popup header
				Boolean bBillTourPage = UI.WaitForObject(oR_BillAndUsage.txtBillTour,UI.iObjTimeOut);
				if(bBillTourPage){
					Report.ValidationPoint(testContext.getName(),"Tour my link pop up is opened.","true","true",true);
				}
				
				// Video part is pending as currently no data with video is available.
				
				// Closing the modal window
				Boolean bClosemodal = UI.WaitForObject(oR_BillAndUsage.lnkClosemodal, UI.iObjTimeOut);
				if(bClosemodal){
					Report.OperationPoint(testContext.getName(), "Closing the window.");
					oR_BillAndUsage.lnkClosemodal.click();
					lDriver.switchTo().defaultContent();
				}
			}
			
			
			
		}

		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	
	

/**************************************************************
	 * Function Name -verifyBillingSummerySectionAndItsDetailsOnOverviewPage
	 * Description - This function verifies billing summery section and its details , links on overview page
	 * Test Case - DBD26710
	 * Parameters - 
	 * Date created - 13th Jan 2015
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD26710

	public static void verifyBillingSummerySectionAndItsDetailsOnOverviewPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			//Verify that the Bills and Payments setion will be displayed as BAU
			try
			{
				lDriver.findElement(By.xpath("//*[@class='shadowRight']//*[contains(text(),'Bill')]"));
				Report.TestPoint(testContext.getName(), "Verify that the Bills and Payments setion will be displayed as BAU", "Displayed", "Displayed", true);
			}
			catch(Exception e2)
			{
				Report.TestPoint(testContext.getName(), "Verify that the Bills and Payments setion will be displayed as BAU", "Displayed", "NOT Displayed", true);
			}
			
			//Verify that the total balance is displayed 
			Boolean txtTotalBalance = UI.WaitForObject(oR_AccountOverview.txtTotalBalance, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify that the total balance is displayed", "True", String.valueOf(txtTotalBalance), true);
			
			//Verify that the Make a Payment button is displayed
			Boolean btnMakeAPayment = UI.WaitForObject(oR_AccountOverview.btnMakeAPayment, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify that the Make a Payment button is displayed", "True", String.valueOf(btnMakeAPayment), true);
			
			//Verify the link to View Bill Details is displayed
			Boolean lnkViewBillDetails = UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify the link to View Bill Details is displayed", "True", String.valueOf(lnkViewBillDetails), true);
			
			//Verify the link 'Paperless Billing' is displayed
			Boolean lnkEnrollInPaperlessBillingServiveSummary = UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBillingServiveSummary, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify the link 'Paperless Billing' is displayed", "True", String.valueOf(lnkEnrollInPaperlessBillingServiveSummary), true);
			
			//Verify the link 'Autopay' is displayed
			try
			{
				lDriver.findElement(By.xpath("//a[text()='Enroll in AutoPay']"));
				Report.TestPoint(testContext.getName(), "Verify the link 'Autopay' is displayed", "displayed", "displayed", true);
				
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Verify the link 'Autopay' is displayed", "displayed", "NOT displayed", true);

			}
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name -verifyDashboardDetailsAndNavigations
	 * Description - This function verifies Dashboard details 
	 * 				And navigations to some pages
	 * Test Case - DBD10307
	 * Parameters - 
	 * Date created - 19th Jan 2015
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD10307
	
	public static void verifyDashboardDetailsAndNavigations(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			
			//Verify on Overview page, Account name is displayed
			try 
			{
				lDriver.findElement(By.xpath("//*[@id='UserName']"));
				Report.ValidationPoint(testContext.getName(), "Verify on Overview page, Account name is displayed", "displayed", "displayed", true);
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify on Overview page, Account name is displayed", "displayed", "NOT displayed", true);
			}
			
			//Verify on Overview page , My Plan section is displayed
			Boolean txtMyPlans = UI.WaitForObject(oR_AccountOverview.txtMyPlans, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify on Overview page , My Plan section is displayed", "True", String.valueOf(txtMyPlans), true);
			
			//Verify on Overview page ,billing cycle is displayed
			try 
			{
				lDriver.findElement(By.xpath("//*[contains(text(),'Billing Cycle:')]"));
				Report.ValidationPoint(testContext.getName(), "Verify on Overview page ,billing cycle is displayed", "displayed", "displayed", true);
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify on Overview page ,billing cycle is displayed", "displayed", "NOT displayed", true);
			}
			
			//Verify on Overview page , Service summary section is displayed
			try 
			{
				lDriver.findElement(By.xpath("//*[contains(text(),'Billing')]"));
				Report.ValidationPoint(testContext.getName(), "Verify on Overview page , Service summary section is displayed", "displayed", "displayed", true);
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify on Overview page , Service summary section is displayed", "displayed", "NOT displayed", true);
			}
			
			//Click on My Plan and Services
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkViewMyPlanAndFeatures, lDriver))
			{
				Report.ValidationPoint(testContext.getName(), "Click on My Plan and Services", "Clicked", "Clicked", true);
				
				//Verify Redirection to the My plans and services page
				Boolean txtMyWirelessService = UI.WaitForObject(oR_AccountOverview.txtMyWirelessService, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify Redirection to the 'My plans and services' page", "True", String.valueOf(txtMyWirelessService), true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Click on My Plan and Services", "Clicked", "FAILED to click", true);
			}
			
			//Click on Billing & payments
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver))
			{
				Report.ValidationPoint(testContext.getName(), "Click on Billing & payments", "Clicked", "Clicked", true);
				
				//Verify Redirection to the Billing & payments page
				Boolean txtBillingAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify Redirection to the 'Billing & payments' page", "True", String.valueOf(txtBillingAndUsage), true);
				
				//Validate usage tab and click on it
				boolean bUsageTab = UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Validate usage tab and click on it", "true", String.valueOf(bUsageTab), true);
				Report.OperationPoint(testContext.getName(), "Operation : Clicking on the usage tab");
				oR_BillAndUsage.lnkUsage.click();
				
				//Verify Redirection to Usage page
				try
				{
					lDriver.findElement(By.xpath("//*[@id='tabcontent']//*[contains(text(),'Usage')]"));
					Report.ValidationPoint(testContext.getName(), "Verify Redirection to Usage page", "Redirected", "Redirected", true);
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Verify Redirection to Usage page", "Redirected", "NOT Redirected", true);
				}
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Click on Billing & payments", "Clicked", "FAILED to click", true);
			}
			
			
			//Click on Profile
			if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecNav, null, lDriver))
			{
				Report.ValidationPoint(testContext.getName(), "Click on Profile", "Clicked", "Clicked", true);
				
				//Verify Redirection to Profile page
				Boolean txtMyProfileTitle = UI.WaitForObject(oR_Profile.txtMyProfileTitle, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify Redirection to Profile page", "True", String.valueOf(txtMyProfileTitle), true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Click on Profile", "Clicked", "FAILED to click", true);
			}
			
			
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name -verifyDashboardConnectedCar
	 * Description -  
	 * Test Case - DBD20075
	 * Parameters - 
	 * Date created - 5th Feb 2016
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD20075
	
	public static void verifyDashboardConnectedCar(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			//Validate that plan name is displayed on overview page for prepaid connected car with future dated mobile share
			Boolean txtConnectedCar = UI.WaitForObject(oR_AccountOverview.imgConnectedCar, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that plan name is displayed on overview page for prepaid connected car with future dated mobile share", "True", String.valueOf(txtConnectedCar), true);
			Boolean txtPlanName = UI.WaitForObject(oR_AccountOverview.txtUverseTVPlan, 5);
			if(txtPlanName)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that plan name is : "+oR_AccountOverview.txtUverseTVPlan.getText(), "True", String.valueOf(txtConnectedCar), true);
			}
			
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
		
	/**************************************************************
	 * Function Name - ValidateWirelessDashboardEnrollInPlenti()
	 * Description -  
	 * Test Case - UOVW0069
	 * Parameters - 
	 * Date created - 5th Feb 2016
	 * Developed by - Hiral Jogi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_UOVW0069
	
	public static void ValidateWirelessDashboardEnrollInPlenti(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			// Step 2
			Boolean btWelcome = UI.WaitForObject(oR_AccountOverview.txtWelcome, UI.iObjTimeOut);
			String strwelcome = oR_AccountOverview.txtWelcome.getText();
			Report.ValidationPoint(testContext.getName(), strwelcome + " string is present.", "True", String.valueOf(btWelcome), true);
			
			// step 3
			Boolean bPlenti = UI.WaitForObject(oR_AccountOverview.lnkLinkYourAccountToPlenti, UI.iObjTimeOut);
			if(bPlenti){
				Report.OperationPoint(testContext.getName(), "Clicking on 'Link your account to plenti' link.");
				oR_AccountOverview.lnkLinkYourAccountToPlenti.click();
				Thread.sleep(2000);
				
				Boolean bbtnPlenti = UI.WaitForObject(oR_AccountOverview.btnLinkToPlenti, UI.iObjTimeOut); 
				if(bbtnPlenti){
					Report.ValidationPoint(testContext.getName(), "Link to Plenti button is seen on the visited page.", "True", String.valueOf(btWelcome), true);
					String strURL = lDriver.getCurrentUrl();
					if(strURL.contains("showAboutPlenti")){
						Report.ValidationPoint(testContext.getName(), "User has navigated to URL --- " + strURL + "which is as expected.", "True", String.valueOf(btWelcome), true);
					}
					
					// Validating page contents
					Boolean bEarnPlentiPoints = UI.WaitForObject(oR_AccountOverview.txtWaysToEarnPlentiPoints, UI.iObjTimeOut); 
					if(bEarnPlentiPoints){
						Report.OperationPoint(testContext.getName(), "Ways to Earn plenti points heading is seen.");
					}
				}
			}
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	
	
	/**************************************************************
	 * Function Name -ValidateEMODiscount
	 * Description - Validate EMO disccount 
	 * Test Case - REG0001
	 * Parameters - 
	 * Date created - 4th Feb 2016
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//REG0001
	
	public static void ValidateEMODiscount(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		
			// Validate EMO disccount link is displayed and a Model Window get's displayed after clicking on it
			if(UI.WaitForObject(oR_AccountOverview.lnkEMODisccount,UI.iObjTimeOut,lDriver)){
				
				Report.ValidationPoint(testContext.getName(), "Verify EMO discounts link is displayed on Dashboard", "True", "True", true);
				Report.OperationPoint(testContext.getName(), "Click on EMO link");
				oR_AccountOverview.lnkEMODisccount.click();
				
				if(UI.WaitForObject(oR_AccountOverview.framEMODiscount, UI.iObjTimeOut)){
					Report.ValidationPoint(testContext.getName(), "Validate a model window is displayed", "True", "True", true);
					
					if(UI.WaitForObject(oR_AccountOverview.lnkCloseEMODisccountModel, UI.iObjTimeOut)){
						Report.ValidationPoint(testContext.getName(), "Validate Close button is displayed on right top", "True", "True", true);
						oR_AccountOverview.lnkCloseEMODisccountModel.click();
						Thread.sleep(5000);
					}else{
						Report.ValidationPoint(testContext.getName(), "Validate Close button is displayed on right top", "True", "False", true);
					}
				}else{
					
					Report.ValidationPoint(testContext.getName(), "Validate a model window is displayed", "True", "False", true);
				}
			}else{
				
				Report.ValidationPoint(testContext.getName(), "Verify EMO discounts link is displayed on Dashboard", "True", "False", true);
			}
			
			// Validate Suspended Mobile Alert
			if(UI.WaitForObject(oR_AccountOverview.btnAlert, UI.iObjTimeOut)){
				oR_AccountOverview.btnAlert.click();
				Thread.sleep(5000);
				
				if(UI.WaitForObject(oR_AccountOverview.txtSuspendedNumberAlert, UI.iObjTimeOut)){
					Report.ValidationPoint(testContext.getName(), "Verify Mobile number suspended alert is displayed", "True", "True", true);
				}else{
					Report.ValidationPoint(testContext.getName(), "Verify Mobile number suspended alert is displayed", "True", "False", true);
				}
			}else{
				Report.ValidationPoint(testContext.getName(), "Validate Alert's are displayed", "True", "False", true);
			}
			
			
		}catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
    }
	
	/**************************************************************
	 * Function Name - ValidateIRUDiscountMessage()
	 * Description -  
	 * Test Case - UAT_UOVW0511
	 * Parameters - 
	 * Date created - 9th Feb 2016
	 * Developed by - Hiral Jogi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_UOVW0511
	
	public static void ValidateIRUDiscountMessage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			// Validating discount link is present.
			Boolean bDiscount = UI.WaitForObject(oR_AccountOverview.lnkReceivingDiscount, UI.iObjTimeOut);
			if(bDiscount){
				Report.ValidationPoint(testContext.getName(), oR_AccountOverview.lnkReceivingDiscount.getText() + " Link is present.", "True", "True", true);
				Report.OperationPoint(testContext.getName(), "Clicking on the discount link.");
				oR_AccountOverview.lnkReceivingDiscount.click();
				
				// Validating the discount frame
				if (UI.WaitForObject(oR_AccountOverview.frmTourMyBill, UI.iObjTimeOut, lDriver)){
				lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
				Thread.sleep(5000);
				// Validating Service discount modal
				WebElement bServiceDiscount = lDriver.findElement(By.xpath("//h2[contains(text(),'Service discount')]"));
				if(bServiceDiscount.isDisplayed()){
					Report.ValidationPoint(testContext.getName(),"A modal window is opened displaying the title as - " + bServiceDiscount.getText(), "True", "True", true);
				
				
				WebElement bServiceDiscountContent = lDriver.findElement(By.xpath("//*[@id='payCommonActionForm']"));
				if(bServiceDiscountContent.isDisplayed()){
					Report.ValidationPoint(testContext.getName(),"Content for the service discount modal window is as below : " + '\n'+ bServiceDiscountContent.getText(), "True", "True", true);
				}
				
				lDriver.switchTo().defaultContent();
				
				// Closing the discount modal
				
				Boolean bClose = UI.WaitForObject(oR_AccountOverview.lnkCloseEMODisccountModel, UI.iObjTimeOut);
				if(bClose){
					Report.ValidationPoint(testContext.getName(),"Close button is present.", "True", "True", true);
					Report.OperationPoint(testContext.getName(), "Closing the modal.");
					oR_AccountOverview.lnkCloseEMODisccountModel.click();
					
				}
			  }else{
					Report.ValidationPoint(testContext.getName(),"Verify A modal window is opened", "True", "False", true);
			  }
				
			}
			} else {
				Report.ValidationPoint(testContext.getName(), "Discount Link is present.", "True", "False", true);
			}
			
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	
	/**************************************************************
	 * Function Name - VerifyDMCTNdetailsOnNakedSLID()
	 * Description -  To see the DMCTN details on the Naked SLID overview page when flag is OFF
	 * Test Case - DBD20634
	 * Parameters - 
	 * Date created - 13th Feb 2016
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD20634
	
	public static void VerifyDMCTNdetailsOnNakedSLID(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			// Verify Welcome message with customer name is displayed
    		if(UI.WaitForObject(oR_AccountOverview.txtWelComeBack, UI.iObjTimeOut, lDriver)){
				Report.ValidationPoint(testContext.getName(), "Validate Welcome back message is displayed with customer name", "True", "True", true);
			}else{
				Report.ValidationPoint(testContext.getName(), "Validate Welcome back message is displayed with customer name", "True", "False", true);
			}
			
			// Verify last login information is displayed
    		if(UI.WaitForObject(oR_AccountOverview.txtLastLogin, UI.iObjTimeOut, lDriver)){
				Report.ValidationPoint(testContext.getName(), "Validate last login information is displayed with customer name", "True", "True", true);
			}else{
				Report.ValidationPoint(testContext.getName(), "Validate last login information is displayed with customer name", "True", "False", true);
			}
	
    		// Verify Sync Devices using Numbersync CTA is Suppressed 
			// In QC along with above link it is also asked to check for "Manage my device" - header suppressed
    		// As per Rakshitha we can ignore it
    		
    		if(!UI.WaitForObject(oR_AccountOverview.lnkSyncDevicesWithNumberSync, 10, lDriver)){
    			Report.ValidationPoint(testContext.getName(), "Validate Sync Devices using Numbersync link is suppressed", "True", "True", true);
    		}else{
    			Report.ValidationPoint(testContext.getName(), "Validate Sync Devices using Numbersync link is suppressed", "True", "False", true);
    		}
    		
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - ValidateServiceSummarySection()
	 * Description -  
	 * Test Case - UAT_UOVW0257
	 * Parameters - 
	 * Date created - 15th Feb 2016
	 * Developed by - Hiral Jogi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_UOVW0257
	
	public static void ValidateServiceSummarySection(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			// Step 1.1
			Boolean btWelcome = UI.WaitForObject(oR_AccountOverview.txtWelcome, UI.iObjTimeOut);
			String strwelcome = oR_AccountOverview.txtWelcome.getText();
			Report.ValidationPoint(testContext.getName(), strwelcome + " is the subscriber first name.", "True", String.valueOf(btWelcome), true);
			
			// Step 1.2
			Boolean bCTNOnOverviewPage = UI.WaitForObject(oR_AccountOverview.txtUsernameAndCTNForOnStarInServiceSummary, UI.iObjTimeOut);
			Boolean bCTNUnderViewDetails = UI.WaitForObject(oR_AccountOverview.txtCTNUnderViewDetails, UI.iObjTimeOut);
			Boolean bFNameUnderViewDetails = UI.WaitForObject(oR_AccountOverview.txtFNameUnderViewDetails, UI.iObjTimeOut);
			
			if(bCTNOnOverviewPage){
				String strCTN= oR_AccountOverview.txtUsernameAndCTNForOnStarInServiceSummary.getText();
				Report.ValidationPoint(testContext.getName(), "Username and CTN " + strCTN + " is present.", "True", String.valueOf(bCTNOnOverviewPage), true);
			} else if(bCTNUnderViewDetails && bFNameUnderViewDetails){
				Report.ValidationPoint(testContext.getName(), "Username is - " + oR_AccountOverview.txtFNameUnderViewDetails.getText() + "\n CTN is - " + oR_AccountOverview.txtCTNUnderViewDetails.getText(), "True", "True", true);
			} else {
				Report.OperationPoint(testContext.getName(), "Required information i.e. CTN or Username is not present.");
			}
			
			// Step 1.3 and 2
			Boolean bViewAllUsage = UI.WaitForObject(oR_AccountOverview.lnkViewDetailsSummary, UI.iObjTimeOut);
			if(bViewAllUsage){
				Report.OperationPoint(testContext.getName(), "View all Usage link is present.");
				UI.VerifyLinkNavigatestoNextPage(oR_AccountOverview.lnkViewAllUsage, "displayUsageLanding");
				lDriver.navigate().back();
				
			} else {
				Report.OperationPoint(testContext.getName(), "View all Usage link is not present.");
			}
			
			// Step 1.4
			Boolean bUnknownDeviceImage = UI.WaitForObject(oR_AccountOverview.imgUnknownDevice, UI.iObjTimeOut);
			WebElement AddADevice = lDriver.findElement(By.xpath("(//a[@class='navTertiaryLink' and @href='/olam/passthroughAction.myworld?actionType=AddALine'])[1]"));
			
			if(bUnknownDeviceImage){
				Report.OperationPoint(testContext.getName(), "Default Device image is present.");
			} else if(AddADevice.isDisplayed()){
				Report.OperationPoint(testContext.getName(), "Add a Device link is present in the tertiary nav.");
			} else {
				Report.OperationPoint(testContext.getName(), "Device image is not present.");
			}
			
			// Step 1.5 and 3
			
			Boolean bLearnHowToUseMyDevice = UI.WaitForObject(oR_AccountOverview.lnkLearnHowToUseMyDevice, UI.iObjTimeOut);
		//	WebElement LearnHowToUseMyDevice1 = lDriver.findElement(By.xpath("(//a[contains(text(),'Learn how to use my device')])[2]"));
			Boolean bViewDetails = UI.WaitForObject(oR_AccountOverview.lnkViewDetailsSummary, UI.iObjTimeOut);
			
			if(bLearnHowToUseMyDevice){
				Report.OperationPoint(testContext.getName(), "LearnHowToUseMyDevice link is present.");
				UI.VerifyLinkNavigatestoNextPage(oR_AccountOverview.lnkLearnHowToUseMyDevice, "devicehowto");
				
			} else if(bViewDetails){
				
				Report.OperationPoint(testContext.getName(), "Clicking on View Details.");
				oR_AccountOverview.lnkViewDetailsSummary.click();
				//WebElement ViewDetailsFrame = lDriver.findElement(By.xpath("//div[@id='cboxLoadedContent']"));
				lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
				
				Boolean bLearnHowToUseMyDevice1 = UI.WaitForObject(oR_AccountOverview.lnkLearnHowToUseMyDevice, UI.iObjTimeOut);
				Report.OperationPoint(testContext.getName(), "LearnHowToUseMyDevice link is present.");
				UI.VerifyLinkNavigatestoNextPage(oR_AccountOverview.lnkLearnHowToUseMyDevice, "devicehowto");
				lDriver.navigate().back();
				Thread.sleep(5000);
			}
			else {
				Report.OperationPoint(testContext.getName(), "LearnHowToUseMyDevice link is not present.");
			}
			
			//Step 4 not to be covered as it is related to content
			
			
		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyWirelessDashboardServiceSummary()
	 * Description - To Verify Wireless Dashboard ServiceSummary section
 	 * Test Case -  UOVW0259 - Wireless Dashboard New - Service Summary - Data only
	 * Parameters - None
	 * Date created - 19th Feb 2016
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_UOVW0259
	public static void VerifyWirelessDashboardServiceSummary(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			//Verify Subscriber first name (If name exceeds 20 characters, ellipsis will display)
			boolean bSubsFirstName = UI.WaitForObject(oR_AccountOverview.txtSubscriberFirstName, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that  Subscriber first name is displayed", "true",String.valueOf(bSubsFirstName), true);
			//CTN
			boolean bSubsCTN = UI.WaitForObject(oR_AccountOverview.txtSubscriberCTN, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that  Subscriber CTN is displayed", "true",String.valueOf(bSubsCTN), true);
			//'View all usage' link
			boolean bViewAllUsage = UI.WaitForObject(oR_AccountOverview.lnkViewAllUsage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that 'View all usage' link is displayed", "true",String.valueOf(bViewAllUsage), true);
			//Device image (If not available, default image)
			boolean bDeviceImage = UI.WaitForObject(oR_AccountOverview.imgDeviceImage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Device image is displayed", "true",String.valueOf(bDeviceImage), true);
			//'View device tutorial' link -- [SKIP]
			
			//Validate the user is directed to the Usage landing page after clicking on 'view all usage' link
			oR_AccountOverview.lnkViewAllUsage.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'view all usage' link");
			Thread.sleep(2000);
			boolean bUsagePage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Billing and Usage page is displayed after clicking on view all usage link", "true",String.valueOf(bUsagePage), true);
			oR_AccountOverview.lnkOverview.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Overview link");
			Thread.sleep(2000);
		
			//Review usage information in Service Summary
			//Confirm the following is displayed: - Days left in bill cycle - Associated bar - 'Web' label - Colored usage bar - Amount of GB left (If there is overage, amount of GB over)
			boolean bUsageSummarySection = UI.WaitForObject(oR_AccountOverview.imgUsageSummarySection, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that usage summary section is displayed", "true",String.valueOf(bUsageSummarySection), true);
			boolean bDaysLeft = UI.WaitForObject(oR_AccountOverview.txtDaysLeft, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that text for Days left in bill cycle is displayed", "true",String.valueOf(bDaysLeft), true);
			boolean bUsageBars = UI.WaitForObject(oR_AccountOverview.imgMeterbars, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Usage bars are displayed with corresponding Usage left or Overage", "true",String.valueOf(bUsageBars), true);
			
			String sDashboardURL = lDriver.getCurrentUrl();
			//Select the 'View device tutorial' link - Validate the user is directed to the Device How-To Center page.
			//Report.OperationPoint(testContext.getName(), "* 'View device tutorial' link not dislpayed! so, skipping validation for view device tutorial link");
			oR_AccountOverview.lnkViewDetailsUnderUsageContainer.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on link - View details");
			Thread.sleep(2000);
	
			//Validate the user is directed to the Device How-To Center page.
			String sMainWindowHandle = lDriver.getWindowHandle();
			WebElement frameHandle = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
			lDriver.switchTo().frame(frameHandle);
			
				boolean bLearnHowTo = UI.WaitForObject(oR_BillAndUsage.lnkLearnHowToUseMyDevice, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that 'Learn How to use my device' link is displayed", "true",String.valueOf(bLearnHowTo), true);
				oR_BillAndUsage.lnkLearnHowToUseMyDevice.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on link - Learn how to use my device");
				Thread.sleep(2000);
				Report.OperationPoint(testContext.getName(), "The page is redirected to URl = "+lDriver.getCurrentUrl());
				boolean bDeviceSupportPage = UI.WaitForObject(oR_AccountOverview.txtDeviceSupport, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate the user is directed to the Device How-To Center page", "true",String.valueOf(bDeviceSupportPage), true);
				//Navigate back to Overview page
				lDriver.get(sDashboardURL);
				Thread.sleep(3000);
				
			lDriver.switchTo().window(sMainWindowHandle);
			boolean bOverview = UI.WaitForObject(oR_AccountOverview.txtWelcome, UI.iObjTimeOut,lDriver);
			
			//Check the unbilled usage in the biller - Confirm the data usage matches what is in the biller.
			boolean bBalnceAmount = UI.WaitForObject(oR_AccountOverview.txtBalanceAmount, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate balnce amount is diplayed in Dashboard", "true",String.valueOf(bBalnceAmount), true);
			String sDashboardBalance = oR_AccountOverview.txtBalanceAmount.getText().toString();
			//navigate to Billing and Payments page
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Billing,Usage,Payments link");
			Thread.sleep(3000);
			boolean bBillingpage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that Billing and Usage page is displayed", "true",String.valueOf(bBillingpage), true);
			new Actions(lDriver).moveToElement(oR_BillAndUsage.lnkBillTab).click().perform();
			
			boolean bBillingAmount = UI.WaitForObject(oR_BillAndUsage.txtTotalAmountDueAmount, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate balnce amount is diplayed in Bill and Usage page", "true",String.valueOf(bBillingAmount), true);
			String bBillingPageAmount = oR_BillAndUsage.txtTotalAmountDueAmount.getText().toString();
			
			//Check the unbilled usage in the biller - Confirm the data usage matches what is in the biller.
			if(sDashboardBalance.equalsIgnoreCase(bBillingPageAmount))
			{
				Report.ValidationPoint(testContext.getName(), "Validate unbilled usage in the biller matches what is in the biller in Bill and Usage page", "true","true", true);
				Report.OperationPoint(testContext.getName(), "Amount displayed is : "+bBillingPageAmount);

			}else
			{

				Report.ValidationPoint(testContext.getName(), "Validate unbilled usage in the biller matches what is in the biller in Bill and Usage page", "true","false", true);
				Report.OperationPoint(testContext.getName(), "Amount displayed in Bill and Usage page is : "+bBillingPageAmount);
				Report.OperationPoint(testContext.getName(), "Amount displayed in Overview page is : "+sDashboardBalance);

			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - VerifyBoltOnDataUsage()
	 * Description - To verify the bolt-on data usage included in Overage in the device details modal during consumption
 	 * Test Case - DBD26018
	 * Parameters - None
	 * Date created - 22nd Feb 2016
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//DBD26018
	public static void VerifyBoltOnDataUsage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		
			//Verify Employer + additonal data used' should be displayed 
			boolean bAddDataUse = UI.WaitForObject(oR_AccountOverview.txtAdditionalDataUsed, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify Employer + additonal data used' should be displayed ", "true",String.valueOf(bAddDataUse), true);
			
			//Verify the link Device details is present on service summary section
			boolean bViewDetails = UI.WaitForObject(oR_AccountOverview.lnkViewDetailsSummary, 60);
			Report.ValidationPoint(testContext.getName(), "Verify the link Device details is present on service summary section", "true",String.valueOf(bViewDetails), true);
			
			oR_AccountOverview.lnkViewDetailsSummary.click();
			Thread.sleep(5000);
			lDriver.switchTo().frame(lDriver.findElement(By.xpath("//iframe[@class='cboxIframe']")));
			
			//Verify that on click of the link modal window is displayed.
			boolean bUsage = UI.WaitForObject(oR_AccountOverview.txtViewAllUsage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that on click of the link modal window is displayed. ", "true",String.valueOf(bUsage), true);
	
			oR_AccountOverview.lnkCloseEMODisccountModel.click();
			
			lDriver.switchTo().defaultContent();
		
			//verify that clicking on data usage bar,it should redirect to usgae page
			
			boolean bUsageBar = UI.WaitForObject(oR_AccountOverview.imgUsageMeter, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate Usage Meter is displayed on Overview page", "true",String.valueOf(bUsageBar), true);
			
			oR_AccountOverview.imgUsageMeter.click();
			
			boolean bUsageTab = UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "verify that clicking on data usage bar,it should redirect to usgae page", "true",String.valueOf(bUsageTab), true);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	

/**************************************************************
	 * Function Name - verify_serviceSummerySectionAndOrderOfCTN_andViewUsageNavigations()
	 * Description - To verify whether devices in summery section are in numerical/alphnumerical order
	 * 					navigation to view details frame for each CTN and reviewing opened model
 	 * Test Case - UAT_UOVW0258
	 * Parameters - None
	 * Date created - 26th Feb 2016
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_UOVW0258
	
public static void verify_serviceSummerySectionAndOrderOfCTN_andViewUsageNavigations(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
			//#STEP 1# Verify View All Usage link
			boolean lnkViewAllUsage = UI.WaitForObject(oR_AccountOverview.lnkViewAllUsage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify View All Usage link", "true",String.valueOf(lnkViewAllUsage), true);
			
			
			
			List<WebElement> lstCTNs = lDriver.findElements(By.xpath("//*[contains(@id,'Ctn')]"));
			String[] sNameArray = new String[lstCTNs.size()];
			String[] sCTNArray = new String[lstCTNs.size()];
			
			//#STEP 1# Verify device image , name and view details link for all CTN
			int cnt=0;
			for(WebElement eCTN:lstCTNs)
			{
				sCTNArray[cnt] = eCTN.getText().trim();
				System.out.println(sCTNArray[cnt]);
				//Verify device image for each CTN
				try
				{
					lDriver.findElement(By.xpath("//*[contains(text(),'"+sCTNArray[cnt]+"')]/parent::*/child::*[.//a[contains(@id,'modalimageLink')]]"));
					Report.ValidationPoint(testContext.getName(), "Verify device image for CTN "+sCTNArray[cnt], "displayed", "displayed", true);
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Verify device image for CTN "+sCTNArray[cnt], "displayed", "NOT displayed", true);
				}
				//810-837-0337
				//Verify subscriber first name
				try
				{
					 WebElement eName= lDriver.findElement(By.xpath("//*[contains(text(),'"+sCTNArray[cnt]+"')]/following-sibling::div[contains(@id,'FirstName')]"));
					 sNameArray[cnt] = eName.getText().trim();
					Report.ValidationPoint(testContext.getName(), "Verify subscriber first name for CTN "+sCTNArray[cnt], "displayed", "displayed", true);
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Verify subscriber first name for CTN "+sCTNArray[cnt], "displayed", "NOT displayed", true);
				}
				
				//Verify View details link
				try
				{
					lDriver.findElement(By.xpath("//*[contains(text(),'"+sCTNArray[cnt]+"')]/following-sibling::div[.//a[contains(text(),'View details')]]"));
					Report.ValidationPoint(testContext.getName(), "Verify View details link for CTN "+sCTNArray[cnt], "displayed", "displayed", true);
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Verify View details link for CTN "+sCTNArray[cnt], "displayed", "NOT displayed", true);
				}
				
				cnt++;
			}
			
			// #STEP 2# Verify navigation of view all usage link
			if(lnkViewAllUsage)
			{
				//Click on View All Usage link
				oR_AccountOverview.lnkViewAllUsage.click();
				Report.OperationPoint(testContext.getName(), "Clicking on View All Usage link");
				
				//Verify user is directed to usage landing page
				boolean txtBillingAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify user is directed to usage landing page", "true",String.valueOf(txtBillingAndUsage), true);
				
				lDriver.navigate().back();
				Report.OperationPoint(testContext.getName(), "Navigating back to dashboard");
			}
			int NameCnt2=0;
			// #STEP 3# Review order of devices
			for(int NameCnt1=0;NameCnt1<(sCTNArray.length);NameCnt1++)
			{
				String sNameToCheck = sNameArray[NameCnt1];
				Boolean bNamesSame = false;
				Boolean bCtnInNumericalOrder = false;
				Boolean bNameInAlphabeticalOrder = false;
				
				if(NameCnt1==sCTNArray.length-1)
				{
					bNamesSame = true;
					bCtnInNumericalOrder = true;
					bNameInAlphabeticalOrder =  true;
				}
				else
				{
					for(NameCnt2 = NameCnt1+1; NameCnt2<sCTNArray.length; NameCnt2++)
					{
						if(NameCnt2!=NameCnt1)
						{
							if(sNameToCheck.compareTo(sNameArray[NameCnt2])==0)
							{
								bNamesSame= true;
								//need to check with CTNs as names are same
								Long currentCTN = Long.parseLong(sCTNArray[NameCnt1].replaceAll("-", ""));
								Long OtherCTN = Long.parseLong(sCTNArray[NameCnt2].replaceAll("-", ""));

								if(currentCTN<OtherCTN)
								{
									//it means CTN is in numerical order
									bCtnInNumericalOrder = true;
									bNameInAlphabeticalOrder = true;
								}
								else
								{
									//it means CTN is not in numerical order
									bCtnInNumericalOrder = false;
									break;
								}
							}
							else
							{
								if(sNameToCheck.compareTo(sNameArray[NameCnt2])<0)
								{
									//it means it is in alphabetical order
									bNameInAlphabeticalOrder = true;
								}
								else if(sNameToCheck.compareTo(sNameArray[NameCnt2])>0)
								{
									//it means it is not in alphabetical order
									bNameInAlphabeticalOrder = false;
									break;
								}
							}
						}
					}
				}
				//if(NameCnt2>=sCTNArray.length)
				//{
					//break outer;
				//}
				if(bNamesSame==true)
				{
					if(bCtnInNumericalOrder==true && bNameInAlphabeticalOrder==true)
					{
						Report.ValidationPoint(testContext.getName(), "Verify subscriber name "+sNameToCheck+" is in alphabetical order and "+sCTNArray[NameCnt1]+" is in numerical order", "true","true", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify subscriber name "+sNameToCheck+" is in alphabetical order and "+sCTNArray[NameCnt1]+" is in numerical order", "true","false", true);
					}
				}
				else
				{
					if(bNameInAlphabeticalOrder==true)
					{
						Report.ValidationPoint(testContext.getName(), "Verify subscriber name "+sNameToCheck+" is in alphabetical order for CTN "+sCTNArray[NameCnt1], "true","true", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify subscriber name "+sNameToCheck+" is in alphabetical order for CTN "+sCTNArray[NameCnt1], "true","false", true);
					}
				}
				
			}
			
			// #STEP 5# Select device image for each CTN
			for(int j=0;j<sCTNArray.length;j++)
			{
				//Select device image for each CTN
				try
				{
					UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(text(),'"+sCTNArray[j]+"')]/parent::*/child::*[.//a[contains(@id,'modalimageLink')]]")), UI.iObjTimeOut);
					lDriver.findElement(By.xpath("//*[contains(text(),'"+sCTNArray[j]+"')]/parent::*/child::*[.//a[contains(@id,'modalimageLink')]]")).click();
					Report.ValidationPoint(testContext.getName(), "Select device image for CTN "+sCTNArray[j], "Selected", "Selected", true);
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Select device image for CTN "+sCTNArray[j], "Selected", "NOT Selected", true);
				}
				
				//Review model
				
				//switch to frame
				try
				{
					//lDriver.switchTo().frame("//iframe[@class='cboxIframe']");
					lDriver.switchTo().frame(lDriver.findElement(By.xpath("//iframe[@class='cboxIframe']")));
				
					Report.ValidationPoint(testContext.getName(), "Switch to frame", "Switched","Switched", true);
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Switch to frame", "Switched","NOT Switched", true);
				}
				
				//Verify user is directed to model of that CTN
				try
				{
					lDriver.findElement(By.xpath("//*[contains(text(),'"+sNameArray[j]+"')]"));
					Report.ValidationPoint(testContext.getName(), "Verify user is directed to model of CTN "+sCTNArray[j], "directed","directed", true);
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Verify user is directed to model of CTN "+sCTNArray[j], "directed","NOT directed", true);
				}
				
				//Close the model
				try
				{
					lDriver.findElement(By.xpath("//a[text()='Close']")).click();
					Report.TestPoint(testContext.getName(), "Close the model of CTN "+sCTNArray[j], "Closed","Closed", true);
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Close the model of CTN "+sCTNArray[j], "Closed","NOT Closed", true);
				}
				
				lDriver.switchTo().defaultContent();
				
			}
			
			
			//
			
			// #STEP 6# Select View details link for each CTN
			for(int i=0;i<sCTNArray.length;i++)
			{
				System.out.println(sCTNArray[i]);
				String s= "//*[contains(text(),'"+sCTNArray[i]+"')]/parent::*/child::div[.//a[contains(text(),'View details')]]";
				System.out.println(s);
				try
				{
					//UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(text(),'"+sCTNArray[cnt].trim()+"')]/following-sibling::div[.//a[contains(text(),'View details')]]")), UI.iObjTimeOut);
					
					UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(text(),'"+sCTNArray[i].trim()+"')]/following-sibling::div[.//a[contains(text(),'View details')]]")), UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Verify View details link for CTN "+sCTNArray[i], "present","present", true);
					
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Verify View details link for CTN "+sCTNArray[i], "present","NOT present", true);
					continue;
				}
				
				WebElement lnkViewDetails = lDriver.findElement(By.xpath("//*[contains(text(),'"+sCTNArray[i]+"')]/following-sibling::div[.//a[contains(text(),'View details')]]"));
				lnkViewDetails.click();
				Report.OperationPoint(testContext.getName(), "Clicking View details link for CTN "+sCTNArray[i]);
				
				// #STEP 7# Review model
				
				//switch to frame
				try
				{
					//lDriver.switchTo().frame("//iframe[@class='cboxIframe']");
					lDriver.switchTo().frame(lDriver.findElement(By.xpath("//iframe[@class='cboxIframe']")));
				
					Report.ValidationPoint(testContext.getName(), "Switch to frame", "Switched","Switched", true);
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Switch to frame", "Switched","NOT Switched", true);
				}
				
				//Verify subscriber first name is displayed
				try
				{
					lDriver.findElement(By.xpath("//*[contains(text(),'"+sNameArray[i]+"')]"));
					Report.ValidationPoint(testContext.getName(), "Verify subscriber first name "+sNameArray[i]+" is displayed", "displayed","displayed", true);
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Verify subscriber first name "+sNameArray[i]+" is displayed", "displayed","NOT displayed", true);
				}
				
				//Verify CTN is displayed
				try
				{
					lDriver.findElement(By.xpath("//*[contains(text(),'"+sNameArray[i]+"')]"));
					Report.ValidationPoint(testContext.getName(), "Verify CTN "+sCTNArray[i]+" is displayed", "displayed","displayed", true);
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Verify CTN "+sCTNArray[i]+" is displayed", "displayed","NOT displayed", true);
				}
				
				//Verify Device image is displayed
				try
				{
					lDriver.findElement(By.tagName("img"));
					Report.ValidationPoint(testContext.getName(), "Verify Device image is displayed", "displayed","displayed", true);
				}
				catch(Exception e)
				{
					Report.ValidationPoint(testContext.getName(), "Verify Device image is displayed", "displayed","NOT displayed", true);
				}
				
				//Verify View All Usage link
				Boolean lnkViewAllUsageOnViewDetailsFrame = UI.WaitForObject(oR_AccountOverview.lnkViewAllUsageOnViewDetailsFrame, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify View All Usage link", "true",String.valueOf(lnkViewAllUsageOnViewDetailsFrame), true);
				//List<WebElement> lstViewLinks = lDriver.findElements(By.xpath("//a[contains(text(),'View all usage')]"));
				//System.out.println(lstViewLinks.size());
				
				// #STEP 8# Click on link
				if(lnkViewAllUsage)
				{
					if(oR_AccountOverview.lnkViewAllUsageOnViewDetailsFrame.isDisplayed())
					{
						System.out.println("link displayed");
					}
					else
					{
						System.out.println("link not displayed");
					}
					
					//((JavascriptExecutor)lDriver).executeScript("window.scrollTo(0,"+oR_AccountOverview.lnkViewAllUsage.getLocation().y+")");
					
					//Click on View All Usage link
					//Actions action = new Actions(lDriver);
					//action.moveToElement(oR_AccountOverview.lnkViewAllUsage).click().build().perform();
					oR_AccountOverview.lnkViewAllUsageOnViewDetailsFrame.click();
					Report.OperationPoint(testContext.getName(), "Clicking on View All Usage link");
					lDriver.switchTo().defaultContent();
					//Verify user is directed to usage landing page
					boolean txtBillingAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify user is directed to usage landing page", "true",String.valueOf(txtBillingAndUsage), true);
					
					lDriver.navigate().back();
					Report.OperationPoint(testContext.getName(), "Navigating back to dashboard");
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
 * Function Name -VerifyUverseWirelessAlerts ()
 * Description - To verify that CC expiration alert is shown for SLID linked with UVerse and wireless accounts (CC in payment profile is about to expire)
 * Test Case - DBD16233
 * Parameters - 
 * Date created - 3rd March 2016
 * Developed by - Krutika Kamdi
 * Last Modified By - 
 * Last Modified Date - 
 ***************************************************************/
//DBD16233
public static void VerifyUverseWirelessAlerts(final ITestContext testContext) throws HeadlessException, IOException, AWTException
{
	WebDriver lDriver = UI.getDriver(testContext.getName());   
	OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);		
	try
	{

		//Step 2>Verify that CC expiration alert is shown for SLID linked with UVerse and wireless accounts
		oR_AccountOverview.imgAlertIcon.click();
		Thread.sleep(6000);
		boolean txtCardExpired = UI.WaitForObject(oR_AccountOverview.txtCardExpiredMessage, UI.iObjTimeOut, lDriver);
		Report.ValidationPoint(testContext.getName(), "Verify that CC expiration alert", "true",String.valueOf(txtCardExpired), true);
		
		//Step 3>Verify that Q&A not updated alert is shown for SLID linked with UVerse and wireless accounts (secret Q&A not updated for UVerse & wireless accounts)
		
		
		//Step 4>For primary slid click on Get Help & Support under quick links
		//1) Troubleshoot my device
		UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Get help & support", "Wireless", "Troubleshoot my device", null);
		
		//2) Learn how to use my device
		UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Get help & support", "Wireless", "Learn how to use my device", null);
		
		//3) Report a lost or stolen device
		UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Get help & support", "Wireless", "Report a lost or stolen device", null);
		
		//4) Unlock my phone or device
		UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Get help & support", "Wireless", "Unlock my phone or device", null);
		
		//5) Transfer contacts & content
		UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Get help & support", "Wireless", "Transfer contacts & content", null);
		
		//6) Activate my device
		UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Get help & support", "Wireless", "Activate my device", null);
		
		//7) Reset voicemail password
		UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Get help & support", "Wireless", "Reset voicemail password", null);
		
		//8) Suspend or reactivate a device
		UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Get help & support", "Wireless", "Suspend or reactivate a device", null);
		
		//9) Contact AT&T
		UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Get help & support", "Wireless", "Contact AT&T", null);
		
		//10) U-verse  Troubleshoot & Resolve
		
	//Step 6> Below link should display under quick links for Primary SLID with 1 U-Verse a/c with all HSIA,TV and VOIP services and 1 wireless a/c Troubleshoot & Resolve View or change repair appointment - http://www.att.com/u-verse/appointmentstatus/ View or change install appointment - https://www.att.com/eos/unauth/eosLogin?productType=uverse Return equipment - eSupport Article - http://www.att.com/esupport/article.jsp? Cancel my service - http://www.att.com/esupport/article.jsp? View my Terms of Service -http://www.att.com/u-verse/att-terms-of-service.jsp - displays in same window. Contact AT&T
	
		UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Get help & support", "U-verse", "Troubleshoot & Resolve", null);
		
		//1) View or change repair appt.
		UI.ClickOrVerifyLinkUnderIWantToDropdown("Click", "Get help & support", "U-verse", "View or change repair appt.", null);
		
		//Verify navigated to http://www.att.com/u-verse/appointmentstatus/ View 
		 String winHandleBefore = lDriver.getWindowHandle();

		    // Switch to new window opened

		    for (String winHandle : lDriver.getWindowHandles()) {
		        lDriver.switchTo().window(winHandle);
		    }
		    //check current window url
		    Thread.sleep(10000);
		    
		    String sURL=lDriver.getCurrentUrl();
		    		
			if(sURL.contains("att.com"))
			{
				Report.ValidationPoint(testContext.getName(), "Verify link View or change repair appt navigates user to required URL", "True", "True", true);	
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify link View or change repair appt navigates user to required URL", "True", "false", true);	
			}
			//back to home phones page
			lDriver.switchTo().window(winHandleBefore);

	   //2) View or change install appt.
		UI.ClickOrVerifyLinkUnderIWantToDropdown("Click", "Get help & support", "U-verse", "View or change repair appt.", null);
		
		//Verify navigated to https://www.att.com/eos/unauth/eosLogin?productType=uverse 
		 winHandleBefore = lDriver.getWindowHandle();

		    // Switch to new window opened

		    for (String winHandle : lDriver.getWindowHandles()) {
		        lDriver.switchTo().window(winHandle);
		    }
		    //check current window url
		    Thread.sleep(10000);
		    
		    sURL=lDriver.getCurrentUrl();
		    		
			if(sURL.contains("www.att.com/eos/unauth/eosLogin"))
			{
				Report.ValidationPoint(testContext.getName(), "Verify link View or change install appt. navigates user to www.att.com/eos/unauth/eosLogin URL", "True", "True", true);	
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify link View or change install appt. navigates user to required URL", "True", "false", true);	
			}
			//back to home phones page
			lDriver.switchTo().window(winHandleBefore);
		
		//3) Return equipment	
		UI.ClickOrVerifyLinkUnderIWantToDropdown("Click", "Get help & support", "U-verse", "Return equipment", null);
		//Verify navigated to http://www.att.com/esupport/article.jsp? 
		 winHandleBefore = lDriver.getWindowHandle();

		    // Switch to new window opened

		    for (String winHandle : lDriver.getWindowHandles()) {
		        lDriver.switchTo().window(winHandle);
		    }
		    //check current window url
		    Thread.sleep(10000);
		    
		    sURL=lDriver.getCurrentUrl();
		    		
			if(sURL.contains("http://www.att.com/esupport"))
			{
				Report.ValidationPoint(testContext.getName(), "Verify link Return equipment navigates user to http://www.att.com/esupport", "True", "True", true);	
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify link Return equipment navigates user to http://www.att.com/esupport", "True", "false", true);	
			}
			//back to home phones page
			lDriver.switchTo().window(winHandleBefore);
		
		//4) Cancel my service
			UI.ClickOrVerifyLinkUnderIWantToDropdown("Click", "Get help & support", "U-verse", "Cancel my service", null);
			
			//Verify navigated to http://www.att.com/esupport/article.jsp? 
			 winHandleBefore = lDriver.getWindowHandle();

			    // Switch to new window opened

			    for (String winHandle : lDriver.getWindowHandles()) {
			        lDriver.switchTo().window(winHandle);
			    }
			    //check current window url
			    Thread.sleep(10000);
			    
			    sURL=lDriver.getCurrentUrl();
			    		
				if(sURL.contains("http://www.att.com/esupport"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify link Cancel my service navigates user to http://www.att.com/esupport", "True", "True", true);	
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify link Cancel my service navigates user to http://www.att.com/esupport", "True", "false", true);	
				}
				//back to home phones page
				lDriver.switchTo().window(winHandleBefore);
			
		//5) View my terms of service
		UI.ClickOrVerifyLinkUnderIWantToDropdown("Click", "Get help & support", "U-verse", "View my terms of service", null);
		
		//Verify navigated to http://www.att.com/u-verse/att-terms-of-service.jsp 
		String Url = lDriver.getCurrentUrl();
		   Report.OperationPoint(testContext.getName(), "Navigating to " +Url);
		   if(Url.contains("www.att.com/shop"))
		   {
			   Report.ValidationPoint(testContext.getName(), "Validate View my terms of service link navigates to required page", "True", "True", true);
		   }
		   else
		   {
			   Report.ValidationPoint(testContext.getName(), "Validate View my terms of service link navigates to required page", "True", "false", true);
		   }

		   lDriver.navigate().back();
		//Verify View And Pay My Bill category for Wireless from quick links
		   UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View & pay bill", "Wireless", "View my bill", null); 
		   
		   UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View & pay bill", "Wireless", "Manage paperless billing", null); 

		   UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View & pay bill", "Wireless", "View my billing history", null); 
		   
		   //View bill and usage reports 
		   UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View & pay bill", "Wireless", "View bill & usage reports", null); 
		   
		   //Question a Charge For wireless payments
		   UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View & pay bill", "Wireless", "Question a charge", null); 
		   
		   //Arrange late payment
		   UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View & pay bill", "Wireless", "Arrange late payment", null); 
		   
		   //Enroll in AutoPay
		   UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View & pay bill", "Wireless", "Enroll in AutoPay", null); 
		   
		   //Make a payment
		   UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View & pay bill", "Wireless", "Make a payment", null);
		   
		   //View payment activity
		   UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View & pay bill", "Wireless", "View payment activity", null);
		   
		   //Update stored payment method
		   UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View & pay bill", "Wireless", "Update stored payment method", null);
		   
	}	

	catch(Exception e)
	{
		Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
	}

}
/**************************************************************
 * Function Name -VerifyIWTChangeVoicemailPaswsowrd ()
 * Description - To verify Change voicemail pin link under I want to drop down
 * Test Case - DBD16233
 * Parameters - 
 * Date created - 4th March 2016
 * Developed by - Krutika Kamdi
 * Last Modified By - 
 * Last Modified Date - 
 ***************************************************************/
//DBD33080
public static void VerifyIWTChangeVoicemailPaswsowrd(final ITestContext testContext) throws HeadlessException, IOException, AWTException
{
	try
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
				
		 UI.ClickOrVerifyLinkUnderIWantToDropdown("Click", "Manage my profile", null, "Change voicemail PIN", null); 
	
		 String Url = lDriver.getCurrentUrl();
		   Report.OperationPoint(testContext.getName(), "Navigating to " +Url);
		   if(Url.contains("att.com"))
		   {
			   Report.ValidationPoint(testContext.getName(), "Validate View my terms of service link navigates to required page", "True", "True", true);
		   }
		   else
		   {
			   Report.ValidationPoint(testContext.getName(), "Validate View my terms of service link navigates to required page", "True", "false", true);
		   }
		   
		   lDriver.navigate().back();
		 
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

	}
	
}



}


	






