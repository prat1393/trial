package com.AppSpecificLibrary;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.xpath.operations.Equals;
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
import com.OR.MyATT.OR_AccountOverview;


import com.OR.MyATT.OR_ForgotUserID;
import com.OR.MyATT.OR_InternetService;
import com.OR.MyATT.OR_Login;
import com.OR.MyATT.OR_Profile;
import com.SupportingFiles.IO;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;
import com.SupportingFiles.UI;




public class Dashboard_Sanity extends LaunchAndLogout {

	static Hashtable<String, String> sTestParams = new Hashtable<String, String>();

	/****************************************************************
	 * Function Name - VerifyDashboardPage() 
	 * Description-    This function use to verify the Overview page  
	 * Parameters -    None
	 * Date created -  20th July 2015
	 * Developed by -  Deepak Kapdi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/

	public static void VerifyDashboardPage(final ITestContext testContext) 
			throws Exception
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);

		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

		/*--- Need to retrieve DataType for Overview page verification */
		String sLoginType= IO.GetParamVal(sTestParams, "DataType");


		try{

			/*---Switch to desired data type as per data sheet ---*/
			switch (sLoginType) {

			case "SLID":

				/************ Developed by - Deepak on 07/29/2015 *****************/

				// Verify Welcome back text adjacent to Logout button is displayed
				boolean bWelcomeTxt_sld = UI.WaitForObject(oR_AccountOverview.txtWelComeBack, UI.iObjTimeOut,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Welcome back text is displayed adjacent to Logout button on Overview page", "True",String.valueOf(bWelcomeTxt_sld), true);

				/* Verify Logout button is displayed on Overview page*/
				boolean bLogout_sld = UI.WaitForObject(oR_AccountOverview.btnLogout, 5, lDriver).equals(true);
				Report.TestPoint(testContext.getName(), "Verify Logout button is displayed on Overview page", "True",String.valueOf(bLogout_sld), true);

				/*--- Verify Group 1 elements on Dashboard - Primary navigations links ---*/

				/* --- Verify Overview link is displayed on Secondary navigation on Overview page --- */					
				boolean bOverviewLink_sld = UI.WaitForObject(oR_AccountOverview.lnkOverview, 2, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Overview link is displayed on Secondary navigation on Overview page", "true", String.valueOf(bOverviewLink_sld), true);

				/*--- Verify Billing, Usage, Payments link is displayed on Secondary navigation on Overview page---*/					
				boolean bBillingLnk_sld = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Billing, Usage, Payments link is displayed on Secondary navigation on Overview page", "True",String.valueOf(bBillingLnk_sld), false);

				/*---- Verifying Wireless link --- */					
				if(UI.WaitForObject(oR_AccountOverview.lnkWirelessSecNav, 1, lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Verify Digital Wireless link in 2ndry Nav bar", "True", "True", false);
				}

				/*---- Verifying Digital TV link --- */					
				if(UI.WaitForObject(oR_AccountOverview.lnkDigitalTVSecondaryNav, 1, lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Verify Digital TV link in 2ndry Nav bar", "True", "True", false);
				}	

				/*---- Verifying Internet link --- */					
				if(UI.WaitForObject(oR_AccountOverview.lnkInternetSecondaryNav, 1, lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Verify Internet link in 2ndry Nav bar", "True", "True", false);
				}			

				/*---- Verifying Home Phone link --- */					
				if(UI.WaitForObject(oR_AccountOverview.lnkHomePhoneSecondaryNav, 1, lDriver).equals(true)){
					Report.ValidationPoint(testContext.getName(), "Verify Home Phone Link is displayed on the dashboard in the primary navigation bar", "True", "True", false);
				}

				/*--- Verifying Profile link ---*/					
				boolean bProfileLnk_sld = UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Profile link in 2ndry Nav bar", "True", String.valueOf(bProfileLnk_sld), false);


				/* --- Verifying My Orders link ---*/	
				if(UI.WaitForObject(oR_AccountOverview.lnkMyOrdersSecNav, 1, lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Verify Verify My Orders link in 2ndry Nav bar in 2ndry Nav bar", "True", "True", false);
				}	


				/*--- Verifying Digital Life link --- */				
				boolean bDigitalLifeLnk_sld=UI.WaitForObject(oR_AccountOverview.lnkDigitalLife, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Digital Life link in 2ndry Nav bar", "True", String.valueOf(bDigitalLifeLnk_sld), false);

				/*--- Verifying My Services text for Combined bill type account --- */

				if(UI.WaitForObject(oR_AccountOverview.txtMyServices, 2, lDriver).equals(false)){

					/*-- Verify Group 2 elements on Dashboard - Welcome Back with Last Login -- */

					/*-- Verify that 'Welcome Back' message should be displayed in account information section adjacent to Last login element ---- */
					if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")), 120,lDriver)) {
						String sText= lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")).getText();
						if(sText.contains("Welcome back")) {
							Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "True", "True", true);
						}
						else {
							Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "'Welcome Back' message is displayed in account information section", "'Welcome Back' message is NOT displayed in account information section", true);
						}
					}


					/*1. ------------- Verify Last login in ----------- */			
					if (UI.WaitForObject(oR_AccountOverview.txtLastLogin, UI.iObjTimeOut,lDriver).equals(true)) {
						String sText=oR_AccountOverview.txtLastLogin.getText();
						if(!sText.trim().isEmpty()){
							String aSplit[]=sText.split(":");
							if(aSplit.length>1){
								String sLastLoginDate=aSplit[1].trim();				
								if(!sLastLoginDate.isEmpty()){									
									Report.ValidationPoint(testContext.getName(), "Validate last login date stamp exist on Account overview page", "Last login date stamp exist", "Last login date stamp exist", true);
									Report.OperationPoint(testContext.getName(), "Information - Last login date displayed as-"+sLastLoginDate);
								}else{
									Report.ValidationPoint(testContext.getName(), "Validate last login date stamp exist on Account overview page", "Last login date stamp exist", "Last login date not displayed on Account overview page", true);
								}

							}else{
								Report.ValidationPoint(testContext.getName(), "Validate last login date stamp exist on Account overview page", "Last login date stamp exist", "Last login date not displayed on Account overview page", true);
							}			

						}else{
							Report.ValidationPoint(testContext.getName(), "Validate last login date stamp exist on Account overview page", "Last login date stamp exist", "Last login date not displayed on Account overview page", true);
						}											

					}
					else {
						Report.ValidationPoint(testContext.getName(), "Validate last login stamp exist on Account overview page", "Last login stamp exist", "Last login stamp do not exist on Account overview page", true);
					}

					//2. Verify Total balance text is displayed 
					boolean btotalBalance_sld = UI.WaitForObject(oR_AccountOverview.txtTotalBalance, 5, lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Total balance text is displayed", "true", "true", true);

					//3. Verify balance amount and $ is displayed on Overview page

					List <WebElement> lstBalAmt_sld = lDriver.findElements(By.xpath("//span[contains(@class,'Orange') or contains(@class,'Green') or contains(@class,'Red') and contains(text(),'$')]"));
					if(lstBalAmt_sld.size() > 0){
						for(int i=0; i<lstBalAmt_sld.size(); i++){
							Report.ValidationPoint(testContext.getName(), "Verify balance amount is displayed on Overview page", "True", "True", true);
							Report.ValidationPoint(testContext.getName(), "Verify balance amount is preceded with $ sign", "True", "True", true);
							String txtColorOfBalAmt = lstBalAmt_sld.get(i).getAttribute("class");
							switch (txtColorOfBalAmt) {

							case "Orange":
								Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Orange color");
								break;

							case "Green":
								Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Green color");
								break;

							case "Red":
								Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Red color");	
								break;

							default:
								Report.OperationPoint(testContext.getName(), "Balance amount is displayed");
								break;
							}
							Report.OperationPoint(testContext.getName(), "Balance amount is: "+lstBalAmt_sld.get(i).getAttribute("text"));
						}
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify balance amount and $ sign is displayed on Overview page", "True", "False", true);
					}

					//4. Verify link for view bill details
					if(UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails, 5, lDriver).equals(true)) {
						Report.ValidationPoint(testContext.getName(), "View bill details link is displayed", "true", "true", true);
					}	
					else {
						Report.ValidationPoint(testContext.getName(), "View bill details link is displayed", "true", "View bill details link is NOT displayed", true);					
					}

					//5. Verify button for 'Make a Payment' 
					if(UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentInOverviewPage, 10, lDriver).equals(true)) {
						Report.ValidationPoint(testContext.getName(), "Make a Payment button is displayed", "true", "true", true);
					}	
					else {
						Report.ValidationPoint(testContext.getName(), "Make a Payment button is displayed", "true", "Make a Payment button is NOT displayed", true);					
					}

					//6. Verify Due date under Make a payment link
					/*if(UI.WaitForObject(oR_AccountOverview.txtDueDate, 40).equals(true)) {
							Report.ValidationPoint(testContext.getName(), "Due date is successfully displayed", "true", "true", true);
						}	
						else {
							Report.ValidationPoint(testContext.getName(), "Due date is successfully displayed", "true", "Due date is NOT displayed successfully", true);					
						}*/

					/*--- Verify Group 3 elements on Dashboard - I want to drop down - links --- */

					//Dashboard.VerifyLinksUnderIWantTo(testContext);

					if(oR_AccountOverview.btnIWantTo.isEnabled()) {

						Report.ValidationPoint(testContext.getName(), "'I want to' drop-down present in overview page", "true", "true", true);
						oR_AccountOverview.btnIWantTo.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'I want to' dropdown");

						//Verify all the Sub-Sections under 'I want to' drop down list
						new Actions(lDriver).moveToElement(oR_AccountOverview.txtViewAndPayBill).click().perform();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked View and Paybill");
						Thread.sleep(2000);

						oR_AccountOverview.btnIWantTo.click();
					}


					/* call method to check View and pay bill*/
					//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View & pay bill", null , null , null);

					/* call method to check View usage */
					//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View usage", null , null, null);

					/* call method to check Manage my plan */					
					//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Mange my plan", null , null, null);

					/* call method to check Manage my profile */ 
					//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Manage my profile", null , null, null);

					/* call method to check Get Help & Support */
					//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Get help & support", null , null, null);

					/* call method to check Shop AT&T */
					//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", null , null, null); 

					/*---- Verify Group 4 elements on Dashboard - My Plans and Alerts --- */

					//Dashboard.VerifyAlerts(testContext);

					Boolean bAlert_sld = UI.WaitForObject(oR_AccountOverview.btnAlert,40,lDriver);
					if(bAlert_sld.equals(true))
					{
						Report.ValidationPoint(testContext.getName(), "Validate Alerts are present", "True", "True", true);
						
						try
						{
							oR_AccountOverview.btnAlert.click();
							Report.OperationPoint(testContext.getName(),"Clicked on Alert button");
							Thread.sleep(3000);
							
							oR_AccountOverview.lnkHide.click();
							
						}catch(Exception Ee2)
						{
							Report.OperationPoint(testContext.getName(),"Hide link is not present under Alerts");
						}
						
						Thread.sleep(1000);
					}

					else {
						Report.ValidationPoint(testContext.getName(), "Validate Alerts are present", "True", "False", true);
					}

					/*---- Verify Group 5 elements on Dashboard - Plan Name , details  --- */

					try{
						//Verify available plans displayed....//
						//lstMyPlans
						List<WebElement> Plans= lDriver.findElements(By.xpath("//ul[@id='MainTab']/li//a"));
						if(Plans.size()>0){
							//ul[@id='MainTab']/li[1]//span/span[starts-with(@class,'Mar')]
							for(WebElement planLink:Plans){								
								planLink.click();  //Selecting plan..
								//String sSelectedPlanTabName=lDriver.findElement(By.xpath("//ul[@id='MainTab']/li["+i+"]//span/span[starts-with(@class,'Mar')]")).getText();
								Thread.sleep(3000);
								//verify plan name on right side .//							
								if(UI.WaitForObject(oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery, UI.iObjTimeOut, lDriver).equals(true)){
									String planNameInServiceSummary=oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery.getText().trim();
									if(!planNameInServiceSummary.isEmpty()){
										Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name displayed on service summary screen header", true);
										Report.OperationPoint(testContext.getName(), "Information - Plan name displayed on Service summary section -"+planNameInServiceSummary);
									}else{
										Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name not displayed on service summary header", true);
									}

								}else{
									Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name not displayed on service summary header", true);
								}

								//Verify phone image displayed on service summary section
								if(UI.WaitForObject(oR_AccountOverview.imgPhoneServiceSumaryOverview_1511, UI.iObjTimeOut, lDriver).equals(true)){
									Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image displayed under service summary section", true);						
								}else{
									Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image not displayed under service summary section", true);
								}

							}
						}else{
							if(UI.WaitForObject(oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery, UI.iObjTimeOut, lDriver).equals(true)){
								String planNameInServiceSummary=oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery.getText().trim();
								if(!planNameInServiceSummary.isEmpty()){
									Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name displayed on service summary screen header", true);
									Report.OperationPoint(testContext.getName(), "Information - Plan name displayed on Service summary section -"+planNameInServiceSummary);
								}else{
									Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name not displayed on service summary header", true);
								}
								//Verify phone image displayed on service summary section
								if(UI.WaitForObject(oR_AccountOverview.imgPhoneServiceSumaryOverview_1511, UI.iObjTimeOut, lDriver).equals(true)){
									Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image displayed under service summary section", true);						
								}else{
									Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image not displayed under service summary section", true);
								}
							}


						}					

					}catch(Exception e){
						String sErrMsg = e.getMessage();
						Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);						
					}



					/*---- Verify Group 6 elements on Dashboard - AT& Messages  --- */
					//verify AT&T message under service summary section
					if (UI.WaitForObject(oR_AccountOverview.txtATTMessage, 10, lDriver).equals(true)) {
						Report.ValidationPoint(testContext.getName(), "Verify AT&T message under service summary section", "true", "true", true);

						if(UI.WaitForObject(oR_AccountOverview.lstDisplayMessage,5, lDriver)){
							Report.ValidationPoint(testContext.getName(), "AT&T Message list box", "true", "true", true);	
						}
						else {
							Report.ValidationPoint(testContext.getName(), "AT&T Message list box", "AT&T Message list box should be displayed", "AT&T Message list box not displayed", true);
						}

					}
					else {
						Report.ValidationPoint(testContext.getName(), "Verify AT&T message under service summary section", "AT&T message should be displayed", "AT&T message is not displayed under service summary section", true);
					}

					//verify view all link should be displayed in front of AT&T message
					if (UI.WaitForObject(oR_AccountOverview.lnkViewAll, 10, lDriver)) {
						Report.TestPoint(testContext.getName(), "Validate view all link", "view all link displayed in front of AT&T message", "view all link displayed in front of AT&T message", true);
						Report.ValidationPoint(testContext.getName(), "Verify View all link", "True", "True", true);
					}	

				} else {

					//verify Last Logged in on date displayed 
					if (UI.WaitForObject(oR_AccountOverview.txtLastLoggedInOn, 1, lDriver)) {
						Report.TestPoint(testContext.getName(), "Verify Last Logged in on date", "True", "True", true);
						Report.ValidationPoint(testContext.getName(), "Verify Last Logged in on date", "True", "True", true);
					}	

					/*--- Verify Group 3 elements on Dashboard - I want to drop down - links --- */

					if(oR_AccountOverview.lnkIWantTo.isEnabled()) {

						Report.ValidationPoint(testContext.getName(), "I want to' drop-down present in overview page", "True", "True", true);
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'I want to' dropdown");

						//oR_AccountOverview.lnkIWantTo.click();
					}

					//verify Service Summary section displayed 
					if (UI.WaitForObject(oR_AccountOverview.txtServiceSummary, 1, lDriver)) {
						Report.TestPoint(testContext.getName(), "Verify Service Summary section displayed", "True", "True", true);
						Report.ValidationPoint(testContext.getName(), "Verify Service Summary section displayed", "True", "True", true);
					}	



				}

				break;


				/************* SLID case - End here ********************/


			case "Wireless":


				/*if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav ,oR_AccountOverview.lnkChangeMyPlanTertiaryNav)){
					System.out.println("True");
				}*/


				// Welcome back text adjacent to Logout button
				if(UI.WaitForObject(oR_AccountOverview.txtWelcome, 40,lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Verify Welcome back text is displayed on Overview page", "true", "true", true);
				}	
				else {
					Report.ValidationPoint(testContext.getName(), "Verify Welcome back text is displayed on Overview page", "true", "Welcome back text is NOT displayed", true);					
				}

				/* Logout button displayed */
				if (UI.WaitForObject(oR_AccountOverview.btnLogout, 5,lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Verify Logout button displayed on overview page", "true", "true", true);
					//Report.OperationPoint(testContext.getName(), "Operational - Logging out");
					//Report.TestPoint(testContext.getName(), "Validate logout button is displayed", "True","True", true);
				}else{
					Report.ValidationPoint(testContext.getName(), "Verify Logout button displayed on overview page", "true", "Logout button not displayed on overview page", true);
				}
				/*--- Verify Group 1 elements on Dashboard - Primary navigations links ---*/
				/* ---1. Verifying Overview link --- */					
				if(UI.CheckExist(oR_AccountOverview.lnkOverview).equalsIgnoreCase("True")) {
					Report.ValidationPoint(testContext.getName(), "Verify Overview link in 2ndry Nav bar", "True", "True", true);
				}
				else {
					Report.ValidationPoint(testContext.getName(), "Verify Overview link in 2ndry Nav bar", "Overview link should be present on 2ndry navigation", "Overview link is NOT present on 2ndry navigation", true);
				}


				/*--- 3. Verifying Billing, Usage, Payments link ---*/					
				if(UI.CheckExist(oR_AccountOverview.lnkBillingUsagePaymentsSecNav).equalsIgnoreCase("True")) {
					Report.ValidationPoint(testContext.getName(), "Verify Billing, Usage, Payments link in 2ndry Nav bar", "True","True", false);
				}
				else {
					Report.ValidationPoint(testContext.getName(), "Verify Billing, Usage, Payments link in 2ndry Nav bar", "Billing, Usage, Payments link should be present on 2ndry navigation", "Billing, Usage, Payments link is NOT present on 2ndry navigation", true);
				}

				/*---- 4. Verifying Wireless link --- */					
				if(UI.CheckExist(oR_AccountOverview.lnkWirelessSecNav).equalsIgnoreCase("True")) {
					Report.ValidationPoint(testContext.getName(), "Verify Wireless link in 2ndry Nav bar", "True", "True", false);
				}
				else {
					Report.ValidationPoint(testContext.getName(), "Verify Wireless link in 2ndry Nav bar", "Wireless link should be present on 2ndry navigation", "Wireless link is NOT present on 2ndry navigation", true);
				}


				/*--- 5. Verifying Profile link ---*/					
				if(UI.CheckExist(oR_AccountOverview.lnkProfileSecondaryNav).equalsIgnoreCase("True")) {
					Report.ValidationPoint(testContext.getName(), "Verify Profile link in 2ndry Nav bar", "True", "True", false);
				}
				else {
					Report.ValidationPoint(testContext.getName(), "Verify Profile link in 2ndry Nav bar", "Profile link should be present on 2ndry navigation", "Profile link is NOT present on 2ndry navigation", true);
				}


				/* --- 6. Verifying My Orders link ---*/					
				if(UI.CheckExist(oR_AccountOverview.lnkMyOrders_1511).equalsIgnoreCase("True")) {
					Report.ValidationPoint(testContext.getName(), "Verify My Orders link in 2ndry Nav bar", "True", "True", false);
				}
				else {
					Report.ValidationPoint(testContext.getName(), "Verify My Orders link in 2ndry Nav bar", "My Orders link should be present on 2ndry navigation", "My Orders link is NOT present on 2ndry navigation", true);
				}

				/* --- 7. Verifying Digital Life link ---*/					
				if(UI.CheckExist(oR_AccountOverview.lnkDigitalLife).equalsIgnoreCase("True")) {
					Report.ValidationPoint(testContext.getName(), "Verify Digital Life link in 2ndry Nav bar", "True", "True", false);
				}
				else {
					Report.ValidationPoint(testContext.getName(), "Verify Digital Life link in 2ndry Nav bar", "Digital Life link should be present on 2ndry navigation", "Digital Life link is NOT present on 2ndry navigation", true);
				}

				/*-- Verify that 'Welcome Back' message should be displayed in account information section adjacent to Last login element ---- */
				if(UI.WaitForObject(oR_AccountOverview.txtWelcomeBackWithLastLogin_1511, 120,lDriver).equals(true)) {
					String sText= lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")).getText();

					if(sText.contains("Welcome back")) {
						Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "'Welcome Back' message is displayed in account information section", "'Welcome Back' message is displayed in account information section", true);
					}
					else {
						Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "'Welcome Back' message is displayed in account information section", "'Welcome Back' message is NOT displayed in account information section", true);
					}
				}

				/*1. ------------- Verify Last login in date----------- */
				if (UI.WaitForObject(oR_AccountOverview.txtLastLogin, UI.iObjTimeOut,lDriver).equals(true)) {
					String sText=oR_AccountOverview.txtLastLogin.getText();
					if(!sText.trim().isEmpty()){
						String aSplit[]=sText.split(":");
						if(aSplit.length>1){
							String sLastLoginDate=aSplit[1].trim();				
							if(!sLastLoginDate.isEmpty()){									
								Report.ValidationPoint(testContext.getName(), "Validate last login date stamp exist on Account overview page", "Last login date stamp exist", "Last login date stamp exist", true);
								Report.OperationPoint(testContext.getName(), "Information - Last login date displayed as-"+sLastLoginDate);
							}else{
								Report.ValidationPoint(testContext.getName(), "Validate last login date stamp exist on Account overview page", "Last login date stamp exist", "Last login date not displayed on Account overview page", true);
							}

						}else{
							Report.ValidationPoint(testContext.getName(), "Validate last login date stamp exist on Account overview page", "Last login date stamp exist", "Last login date not displayed on Account overview page", true);
						}			

					}else{
						Report.ValidationPoint(testContext.getName(), "Validate last login date stamp exist on Account overview page", "Last login date stamp exist", "Last login date not displayed on Account overview page", true);
					}											

				}
				else {
					Report.ValidationPoint(testContext.getName(), "Validate last login stamp exist on Account overview page", "Last login stamp exist", "Last login stamp do not exist on Account overview page", true);
				}

				//2. Verify Total balance text is displayed 
				if(UI.WaitForObject(oR_AccountOverview.txtTotalBalance, 40,lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Total balance text is displayed", "true", "true", true);
				}	
				else {
					Report.ValidationPoint(testContext.getName(), "Total balance text is displayed", "true", "Total balance text is NOT displayed", true);					
				}

				//2. Verify Total balance as of date is displayed 
				if(UI.WaitForObject(oR_AccountOverview.txtTotalBalAsOfDate_1511, 40,lDriver).equals(true)) {
					String sText=oR_AccountOverview.txtTotalBalAsOfDate_1511.getText();
					String aSplit[]=sText.split("as of");
					if(aSplit.length>1){
						String sAsOfDate=aSplit[1].trim();				
						if(!sAsOfDate.isEmpty()){									
							Report.ValidationPoint(testContext.getName(), "Verify Total Balance as of <today's date> exist on Account overview page", "Total balance as of date displayed", "Total balance as of date displayed", true);
							Report.OperationPoint(testContext.getName(), "Information - as of date displayed as-"+sAsOfDate);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify Total Balance as of <today's date> exist on Account overview page", "Total balance as of date displayed", "Today's date not displayed with total balance text", true);
						}

					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Total Balance as of <today's date> exist on Account overview page", "Total balance as of date displayed", "Today's date not displayed with total balance text", true);
					}				


				}	
				else {
					Report.ValidationPoint(testContext.getName(), "Total balance text is displayed", "true", "Total balance text is NOT displayed", true);					
				}

				//3. Verify Total balance value in $ is displayed 
				if(UI.WaitForObject(oR_AccountOverview.txtBalanceAmount, 40,lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Total balance amount is displayed", "true", "true", true);
				}	
				else {
					Report.ValidationPoint(testContext.getName(), "Total balance amount is displayed", "true", "Total balance amount is NOT displayed", true);					
				}

				//4. Verify link for view bill details
				if(UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails, 40,lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "View bill details link is displayed", "true", "true", true);
				}	
				else {
					Report.ValidationPoint(testContext.getName(), "View bill details link is displayed", "true", "View bill details link is NOT displayed", true);					
				}

				//5. Verify button for 'Make a Payment' 
				if(UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentInOverviewPage, 40,lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Make a Payment button is displayed", "true", "true", true);
				}	
				else {
					Report.ValidationPoint(testContext.getName(), "Make a Payment button is displayed", "true", "Make a Payment button is NOT displayed", true);					
				}

				//6. Verify Past due amount under Make a payment link(if any)
				if(UI.WaitForObject(oR_AccountOverview.txtPastDue, 40,lDriver).equals(true)) {
					if(UI.WaitForObject(oR_AccountOverview.txtPastDueAmount_1511, 30,lDriver).equals(true)) {
						Report.ValidationPoint(testContext.getName(), "Verify Past Due text with amount displayed", "true", "true", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Past Due text with amount displayed", "Past due text with amount displayed", "Past due amount not displayed with past due text", true);
					}						
				}



				/*--- Verify Group 3 elements on Dashboard - I want to drop down - links --- */

				/* call method to check View and pay bill */
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "View & pay bill", " " , " " , "Present");

				/* call method to check View usage */
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "View usage", " " , " ", "Present");

				/* call method to check Manage my plan */					
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "Mange my plan", " " , " ", "Present");

				/* call method to check Manage my profile */ 
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "Manage my profile", " " , " ", "Present");

				/* call method to check Get Help & Support */
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "Get help & support", " " , " ", "Present");

				/* call method to check Shop AT&T */
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "Shop AT&T", " " , " ", "Present");


				/*---- Verify Group 4 elements on Dashboard - My Plans and Alerts --- */

				//Dashboard.VerifyAlerts(testContext);
				Boolean bAlert = UI.WaitForObject(oR_AccountOverview.btnAlert,40,lDriver);
				if(bAlert.equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Alerts are present", "True", "True", true);
					
					//oR_AccountOverview.lnkHide.click();
					try
					{
						oR_AccountOverview.btnAlert.click();
						Report.OperationPoint(testContext.getName(),"Clicked on Alert button");
						Thread.sleep(3000);
						
						oR_AccountOverview.lnkHide.click();
						
					}catch(Exception Ee2)
					{
						Report.OperationPoint(testContext.getName(),"Hide link is not present under Alerts");
					}

					Thread.sleep(1000);
				}

				else {
					Report.ValidationPoint(testContext.getName(), "Validate Alerts are present", "True", "False", true);
				}

				//Group 5 right pane:- <Plan Name>  Phone image/Name 'Learn how to use my device' link Billing cycle, Data, Text, Talk usage (graphical representation)  

				//Verify My Plans header displayed....//
				if(UI.WaitForObject(oR_AccountOverview.txtHeaderMyPlans_1511, UI.iObjTimeOut, lDriver).equals(true)){
					Report.ValidationPoint(testContext.getName(), "Validate My Plans section is displayed", "My Plans section is displayed", "My Plans section is displayed", true);
				}else{
					
					//If there is only 1 plan, MyPlans sections wont display instead Alerts section will display there
					//Last modified by Clint, 25th Nov 2015
					try
					{
						
						if(UI.WaitForObject(oR_AccountOverview.txtSinglePlanAlertSection, UI.iObjTimeOut, lDriver).equals(true))
						{
							Report.OperationPoint(testContext.getName(), "My plans section is NOT displayed since, Only a Single plan is available and Alerts section is Displayed instead");
							Report.ValidationPoint(testContext.getName(), "Validate My Plans section is displayed", "Alert section is present instead of My Plans section since only one plan is available", "Alert section is present instead of My Plans section since only one plan is available", true);
							
						}else
						{
							Report.ValidationPoint(testContext.getName(), "Validate My Plans section is displayed", "My Plans section is displayed", "My Plans section header not displayed", true);
						}
						
					}catch(Exception e2)
					{
						Report.ValidationPoint(testContext.getName(), "Validate My Plans section is displayed", "My Plans section is displayed", "My Plans section header not displayed", true);
					}
					
				}


				try{
					//Verify available plans displayed....//
					//lstMyPlans
					List<WebElement> Plans= lDriver.findElements(By.xpath("//ul[@id='MainTab']/li//a"));
					if(Plans.size()>0){
						//ul[@id='MainTab']/li[1]//span/span[starts-with(@class,'Mar')]
						for(WebElement planLink:Plans){								
							planLink.click();  //Selecting plan..
							//String sSelectedPlanTabName=lDriver.findElement(By.xpath("//ul[@id='MainTab']/li["+i+"]//span/span[starts-with(@class,'Mar')]")).getText();
							Thread.sleep(3000);
							//verify plan name on right side .//							
							if(UI.WaitForObject(oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery, UI.iObjTimeOut, lDriver).equals(true)){
								String planNameInServiceSummary=oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery.getText().trim();
								if(!planNameInServiceSummary.isEmpty()){
									Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name displayed on service summary screen header", true);
									Report.OperationPoint(testContext.getName(), "Information - Plan name displayed on Service summary section -"+planNameInServiceSummary);
								}else{
									Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name not displayed on service summary header", true);
								}

							}else{
								Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name not displayed on service summary header", true);
							}

							//Verify phone image displayed on service summary section
							if(UI.WaitForObject(oR_AccountOverview.imgPhoneServiceSumaryOverview_1511, UI.iObjTimeOut, lDriver).equals(true)){
								Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image displayed under service summary section", true);						
							}else{
								Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image not displayed under service summary section", true);
							}

						}
					}else{
						if(UI.WaitForObject(oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery, UI.iObjTimeOut, lDriver).equals(true)){
							String planNameInServiceSummary=oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery.getText().trim();
							if(!planNameInServiceSummary.isEmpty()){
								Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name displayed on service summary screen header", true);
								Report.OperationPoint(testContext.getName(), "Information - Plan name displayed on Service summary section -"+planNameInServiceSummary);
							}else{
								Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name not displayed on service summary header", true);
							}
							//Verify phone image displayed on service summary section
							if(UI.WaitForObject(oR_AccountOverview.imgPhoneServiceSumaryOverview_1511, UI.iObjTimeOut, lDriver).equals(true)){
								Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image displayed under service summary section", true);						
							}else{
								Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image not displayed under service summary section", true);
							}
						}


					}					

				}catch(Exception e){
					String sErrMsg = e.getMessage();
					Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);						
				}


				//Verify billing cycle header displayed under service summary	
				if(UI.WaitForObject(oR_AccountOverview.txtBillingCycleOverview_1511, UI.iObjTimeOut, lDriver).equals(true)){
					Report.ValidationPoint(testContext.getName(), "Verify Billing cycle text displayed under service summary section on overview page", "Billing Cycle text displayed under service summary section", "Billing Cycle text displayed under service summary section", true);						
				}else{
					Report.ValidationPoint(testContext.getName(), "Verify Billing cycle text displayed under service summary section on overview page", "Billing Cycle text displayed under service summary section", "Billing Cycle text not displayed under service summary section", true);
				}

				//verify AT&T message box displayed
				if (UI.WaitForObject(oR_AccountOverview.txtATTMessageCenterbox_1511, 10,lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Verify AT&T message box displayed", "true", "true", true);
				}
				else {
					Report.ValidationPoint(testContext.getName(), "Verify AT&T message box displayed", "AT&T message box should be displayed", "AT&T message box not displayed", true);
				}

				//verify view all link should be displayed in front of AT&T message
				if (UI.WaitForObject(oR_AccountOverview.lnkViewAll, 10,lDriver)) {
					//Report.TestPoint(testContext.getName(), "Validate view all link", "view all link displayed in front of AT&T message", "view all link displayed in front of AT&T message", true);
					Report.ValidationPoint(testContext.getName(), "Validate view all link", "view all link displayed in front of AT&T message", "view all link displayed in front of AT&T message", true);
				}else{
					Report.OperationPoint(testContext.getName(), "Information - There is no message displayed on AT&T message box");
				}
				//String sText1= lDriver.findElement(By.xpath("//div[@class='font14 PadBot5 colorBlack']")).getText();
				//System.out.println("Billig cycle days left="+sText1);

				break;


			case "uverse":

				/******************* Developed by - Deepak Kapdi ****************************************/

				// Verify Welcome back text adjacent to Logout button is displayed
				boolean bWelcomeTxt_uv = UI.WaitForObject(oR_AccountOverview.txtWelComeBack, UI.iObjTimeOut,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Welcome back text is displayed adjacent to Logout button on Overview page", "True",String.valueOf(bWelcomeTxt_uv), true);

				/* Verify Logout button is displayed on Overview page*/
				boolean bLogout_uv = UI.WaitForObject(oR_AccountOverview.btnLogout, 5, lDriver).equals(true);
				Report.TestPoint(testContext.getName(), "Verify Logout button is displayed on Overview page", "True",String.valueOf(bLogout_uv), true);

				/*--- Verify Group 1 elements on Dashboard - Primary navigations links ---*/

				/* --- Verify Overview link is displayed on Secondary navigation on Overview page --- */					
				boolean bOverviewLink_uv = UI.WaitForObject(oR_AccountOverview.lnkOverview, 10, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Overview link is displayed on Secondary navigation on Overview page", "true", String.valueOf(bOverviewLink_uv), true);

				/*--- Verify Billing, Usage, Payments link is displayed on Secondary navigation on Overview page---*/					
				boolean bBillingLnk_uv = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Billing, Usage, Payments link is displayed on Secondary navigation on Overview page", "True",String.valueOf(bBillingLnk_uv), false);

				/*---- Verifying Digital TV link --- */					
				boolean bDigitalTVLnk_uv = UI.WaitForObject(oR_AccountOverview.lnkDigitalTVSecondaryNav, 5, lDriver ).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Digital TV link in 2ndry Nav bar", "True", String.valueOf(bDigitalTVLnk_uv), false);


				/*---- Verifying Internet link --- */					
				boolean bInternetLnk_uv = UI.WaitForObject(oR_AccountOverview.lnkInternetSecondaryNav, 5, lDriver ).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Internet link in 2ndry Nav bar", "True", String.valueOf(bInternetLnk_uv), false);


				/*---- Verifying Home Phone link --- */					
				boolean bHomePhoneLnk_uv = UI.WaitForObject(oR_AccountOverview.lnkHomePhoneSecondaryNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Home Phone Link is displayed on the dashboard in the primary navigation bar", "true", String.valueOf(bHomePhoneLnk_uv), false);


				/*--- Verifying Profile link ---*/					
				boolean bProfileLnk_uv = UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Profile link in 2ndry Nav bar", "True", String.valueOf(bProfileLnk_uv), false);


				/* --- Verifying My Orders link ---*/					
				boolean bMyOrdersLnk_uv = UI.WaitForObject(oR_AccountOverview.lnkMyOrdersSecNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify My Orders link in 2ndry Nav bar", "True", String.valueOf(bMyOrdersLnk_uv), false);


				/*--- Verifying Digital Life link --- */				
				boolean bDigitalLifeLnk_uv=UI.WaitForObject(oR_AccountOverview.lnkDigitalLife, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Digital Life link in 2ndry Nav bar", "True", String.valueOf(bDigitalLifeLnk_uv), false);


				/*-- Verify Group 2 elements on Dashboard - Welcome Back with Last Login -- */

				/*-- Verify that 'Welcome Back' message should be displayed in account information section adjacent to Last login element ---- */
				if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")), 120,lDriver)) {
					String sText= lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")).getText();
					if(sText.contains("Welcome back")) {
						Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "True", "True", true);
					}
					else {
						Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "'Welcome Back' message is displayed in account information section", "'Welcome Back' message is NOT displayed in account information section", true);
					}
				}


				/*1. ------------- Verify Last login in ----------- */
				if (UI.WaitForObject(oR_AccountOverview.txtLastLogin, 5, lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Validate last login stamp exist on Account overview page", "Last login stamp exist", "Last login stamp exist", true);
				}
				else {
					Report.ValidationPoint(testContext.getName(), "Validate last login stamp exist on Account overview page", "Last login stamp exist", "Last login stamp do not exist on Account overview page", true);
				}

				//2. Verify Total balance text is displayed 
				boolean btotalBalance_uv = UI.WaitForObject(oR_AccountOverview.txtTotalBalance, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Total balance text is displayed", "true", String.valueOf(btotalBalance_uv), true);

				//3. Verify Total balance value in $ is displayed 
				/*if(UI.WaitForObject(oR_AccountOverview.txtBalanceAmount, 5, lDriver).equals(true)) {
						Report.ValidationPoint(testContext.getName(), "Total balance amount is displayed", "true", "true", true);
					}	
					else {
						Report.ValidationPoint(testContext.getName(), "Total balance amount is displayed", "true", "Total balance amount is NOT displayed", true);					
					}*/

				// --- Verify balance amount and $ is displayed on Overview page
				List <WebElement> lstBalAmt_uv = lDriver.findElements(By.xpath("//span[contains(@class,'Orange') or contains(@class,'Green') or contains(@class,'Red') and contains(text(),'$')]"));
				if(lstBalAmt_uv.size() > 0){
					for(int i=0; i<lstBalAmt_uv.size(); i++){
						Report.ValidationPoint(testContext.getName(), "Verify balance amount is displayed on Overview page", "True", "True", true);
						Report.ValidationPoint(testContext.getName(), "Verify balance amount is preceded with $ sign", "True", "True", true);
						String txtColorOfBalAmt = lstBalAmt_uv.get(i).getAttribute("class");
						switch (txtColorOfBalAmt) {

						case "Orange":
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Orange color");
							break;

						case "Green":
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Green color");
							break;

						case "Red":
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Red color");	
							break;

						default:
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed");
							break;
						}
						Report.OperationPoint(testContext.getName(), "Balance amount is: "+lstBalAmt_uv.get(i).getAttribute("text"));
					}
				}else{
					Report.ValidationPoint(testContext.getName(), "Verify balance amount and $ sign is displayed on Overview page", "True", "False", true);
				}

				//4. Verify link for view bill details
				if(UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails, 5, lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "View bill details link is displayed", "true", "true", true);
				}	
				else {
					Report.ValidationPoint(testContext.getName(), "View bill details link is displayed", "true", "View bill details link is NOT displayed", true);					
				}

				//5. Verify button for 'Make a Payment' 
				if(UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentInOverviewPage, 10, lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Make a Payment button is displayed", "true", "true", true);
				}	
				else {
					Report.ValidationPoint(testContext.getName(), "Make a Payment button is displayed", "true", "Make a Payment button is NOT displayed", true);					
				}

				//6. Verify Due date under Make a payment link
				/*if(UI.WaitForObject(oR_AccountOverview.txtDueDate, 40).equals(true)) {
						Report.ValidationPoint(testContext.getName(), "Due date is successfully displayed", "true", "true", true);
					}	
					else {
						Report.ValidationPoint(testContext.getName(), "Due date is successfully displayed", "true", "Due date is NOT displayed successfully", true);					
					}*/

				/*--- Verify Group 3 elements on Dashboard - I want to drop down - links --- */

				//Dashboard.VerifyLinksUnderIWantTo(testContext);

				if(oR_AccountOverview.btnIWantTo.isEnabled()) {

					Report.ValidationPoint(testContext.getName(), "'I want to' drop-down present in overview page", "true", "true", true);
					oR_AccountOverview.btnIWantTo.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'I want to' dropdown");

					//Verify all the Sub-Sections under 'I want to' drop down list
					new Actions(lDriver).moveToElement(oR_AccountOverview.txtViewAndPayBill).click().perform();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked View and Paybill");
					Thread.sleep(2000);

					oR_AccountOverview.btnIWantTo.click();
				}


				/* call method to check View and pay bill*/
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View & pay bill", null , null , null);

				/* call method to check View usage */
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View usage", null , null, null);

				/* call method to check Manage my plan */					
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Mange my plan", null , null, null);

				/* call method to check Manage my profile */ 
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Manage my profile", null , null, null);

				/* call method to check Get Help & Support */
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Get help & support", null , null, null);

				/* call method to check Shop AT&T */
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", null , null, null); 


				/*---- Verify Group 4 elements on Dashboard - My Plans and Alerts --- */
				//Dashboard.VerifyAlerts(testContext);

				Boolean bAlert_uv = UI.WaitForObject(oR_AccountOverview.btnAlert,40,lDriver);
				if(bAlert_uv.equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Alerts are present", "True", "True", true);
					
					//oR_AccountOverview.lnkHide.click();
					try
					{
						oR_AccountOverview.btnAlert.click();
						Report.OperationPoint(testContext.getName(),"Clicked on Alert button");
						Thread.sleep(3000);
						
						oR_AccountOverview.lnkHide.click();
						
					}catch(Exception Ee2)
					{
						Report.OperationPoint(testContext.getName(),"Hide link is not present under Alerts");
					}
					
					Thread.sleep(2000);
				}

				else {
					Report.ValidationPoint(testContext.getName(), "Validate Alerts are present", "True", "False", true);
				}
				

				/*---- Verify Group 5 elements on Dashboard - Plan Name , details  --- */

				//Verify My Plans header displayed....//
				if(UI.WaitForObject(oR_AccountOverview.txtHeaderMyPlans_1511, UI.iObjTimeOut, lDriver).equals(true)){
					Report.ValidationPoint(testContext.getName(), "Validate My Plans section is displayed", "My Plans section is displayed", "My Plans section is displayed", true);
				}else{
					
					//If there is only 1 plan, MyPlans sections wont display instead Alerts section will display there
					//Last modified by Clint, 25th Nov 2015
					try
					{
						
						if(UI.WaitForObject(oR_AccountOverview.txtSinglePlanAlertSection, UI.iObjTimeOut, lDriver).equals(true))
						{
							Report.OperationPoint(testContext.getName(), "My plans section is NOT displayed since, Only a Single plan is available and Alerts section is Displayed instead");
							Report.ValidationPoint(testContext.getName(), "Validate My Plans section is displayed", "Alert section is present instead of My Plans section since only one plan is available", "Alert section is present instead of My Plans section since only one plan is available", true);
							
						}else
						{
							Report.ValidationPoint(testContext.getName(), "Validate My Plans section is displayed", "My Plans section is displayed", "My Plans section header not displayed", true);
						}
						
					}catch(Exception e2)
					{
						Report.ValidationPoint(testContext.getName(), "Validate My Plans section is displayed", "My Plans section is displayed", "My Plans section header not displayed", true);
					}
					
				}


				try{
					//Verify available plans displayed....//
					//lstMyPlans
					List<WebElement> Plans= lDriver.findElements(By.xpath("//ul[@id='MainTab']/li//a"));
					if(Plans.size()>0){
						//ul[@id='MainTab']/li[1]//span/span[starts-with(@class,'Mar')]
						for(WebElement planLink:Plans){								
							planLink.click();  //Selecting plan..
							//String sSelectedPlanTabName=lDriver.findElement(By.xpath("//ul[@id='MainTab']/li["+i+"]//span/span[starts-with(@class,'Mar')]")).getText();
							Thread.sleep(3000);
							//verify plan name on right side .//							
							if(UI.WaitForObject(oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery, UI.iObjTimeOut, lDriver).equals(true)){
								String planNameInServiceSummary=oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery.getText().trim();
								if(!planNameInServiceSummary.isEmpty()){
									Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name displayed on service summary screen header", true);
									Report.OperationPoint(testContext.getName(), "Information - Plan name displayed on Service summary section -"+planNameInServiceSummary);
								}else{
									Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name not displayed on service summary header", true);
								}

							}else{
								Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name not displayed on service summary header", true);
							}

							//Verify phone image displayed on service summary section
							//Update from Yuvashree on 23rd Nov 2015: Phone image will not be displayed For Uverse or CBuverse customer types. Need Script changes for the same.
							//Code updated by Clint (23rd Nov 2015)
							
//							if(UI.WaitForObject(oR_AccountOverview.imgPhoneServiceSumaryOverview_1511, UI.iObjTimeOut, lDriver).equals(true)){
//								Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image displayed under service summary section", true);						
//							}else{
//								Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image not displayed under service summary section", true);
//							}

						}
					}else{
						if(UI.WaitForObject(oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery, UI.iObjTimeOut, lDriver).equals(true)){
							String planNameInServiceSummary=oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery.getText().trim();
							if(!planNameInServiceSummary.isEmpty()){
								Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name displayed on service summary screen header", true);
								Report.OperationPoint(testContext.getName(), "Information - Plan name displayed on Service summary section -"+planNameInServiceSummary);
							}else{
								Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name not displayed on service summary header", true);
							}
							
							//Verify phone image displayed on service summary section
							//Update from Yuvashree on 23rd Nov 2015: Phone image will not be displayed For Uverse or CBuverse customer types. Need Script changes for the same.
							//Code updated by Clint (23rd Nov 2015)
//							if(UI.WaitForObject(oR_AccountOverview.imgPhoneServiceSumaryOverview_1511, UI.iObjTimeOut, lDriver).equals(true)){
//								Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image displayed under service summary section", true);						
//							}else{
//								Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image not displayed under service summary section", true);
//							}
						}


					}					

				}catch(Exception e){
					String sErrMsg = e.getMessage();
					Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);						
				}



				/*---- Verify Group 6 elements on Dashboard - AT& Messages  --- */
				//verify AT&T message under service summary section
				if (UI.WaitForObject(oR_AccountOverview.txtATTMessage, 10, lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Verify AT&T message under service summary section", "true", "true", true);

					if(UI.WaitForObject(oR_AccountOverview.lstDisplayMessage,5, lDriver)){
						Report.ValidationPoint(testContext.getName(), "AT&T Message list box", "true", "true", true);	
					}
					else {
						Report.ValidationPoint(testContext.getName(), "AT&T Message list box", "AT&T Message list box should be displayed", "AT&T Message list box not displayed", true);
					}

				}
				else {
					Report.ValidationPoint(testContext.getName(), "Verify AT&T message under service summary section", "AT&T message should be displayed", "AT&T message is not displayed under service summary section", true);
				}

				//verify view all link should be displayed in front of AT&T message
				if (UI.WaitForObject(oR_AccountOverview.lnkViewAll, 10, lDriver)) {
					Report.TestPoint(testContext.getName(), "Validate view all link", "view all link displayed in front of AT&T message", "view all link displayed in front of AT&T message", true);
					Report.ValidationPoint(testContext.getName(), "Verify View all link", "True", "True", true);
				}	

				break;

				/******************* Uverse case - End here ********************************/

			case "CB-Uverse":

				/******************* Developed by - Deepak Kapdi  on 07/21/2015 ************/

				// Verify Welcome back text adjacent to Logout button is displayed
				boolean bWelcomeTxt_cbu = UI.WaitForObject(oR_AccountOverview.txtWelComeBack, UI.iObjTimeOut,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Welcome back text is displayed adjacent to Logout button on Overview page", "True",String.valueOf(bWelcomeTxt_cbu), true);

				/*--- Verify Logout button is displayed on Overview page ---*/
				boolean bLogout_cbu = UI.WaitForObject(oR_AccountOverview.btnLogout, 5, lDriver).equals(true);
				Report.TestPoint(testContext.getName(), "Verify Logout button is displayed on Overview page", "True",String.valueOf(bLogout_cbu), true);

				/*--- Verify Group 1 elements on Dashboard - Primary navigations links ---*/

				/* --- Verify Overview link is displayed on Secondary navigation on Overview page --- */					
				boolean bOverviewLink_cbu = UI.WaitForObject(oR_AccountOverview.lnkOverview, 10, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Overview link is displayed on Secondary navigation on Overview page", "true", String.valueOf(bOverviewLink_cbu), true);

				/*--- Verify Billing, Usage, Payments link is displayed on Secondary navigation on Overview page---*/					
				boolean bBillingLnk_cbu = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Billing, Usage, Payments link is displayed on Secondary navigation on Overview page", "True",String.valueOf(bBillingLnk_cbu), false);

				/*---- Verifying Digital TV link --- */					
				boolean bDigitalTVLnk_cbu = UI.WaitForObject(oR_AccountOverview.lnkDigitalTVSecondaryNav, 5, lDriver ).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Digital TV link in 2ndry Nav bar", "True", String.valueOf(bDigitalTVLnk_cbu), false);


				/*---- Verifying Internet link --- */					
				boolean bInternetLnk_cbu = UI.WaitForObject(oR_AccountOverview.lnkInternetSecondaryNav, 5, lDriver ).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Internet link in 2ndry Nav bar", "True", String.valueOf(bInternetLnk_cbu), false);


				/*---- Verifying Home Phone link --- */					
				boolean bHomePhoneLnk_cbu = UI.WaitForObject(oR_AccountOverview.lnkHomePhoneSecondaryNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Home Phone Link is displayed on the dashboard in the primary navigation bar", "true", String.valueOf(bHomePhoneLnk_cbu), false);


				/*--- Verifying Profile link ---*/					
				boolean bProfileLnk_cbu = UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Profile link in 2ndry Nav bar", "True", String.valueOf(bProfileLnk_cbu), false);


				/* --- Verifying My Orders link ---*/					
				boolean bMyOrdersLnk_cbu = UI.WaitForObject(oR_AccountOverview.lnkMyOrdersSecNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify My Orders link in 2ndry Nav bar", "True", String.valueOf(bMyOrdersLnk_cbu), false);


				/*--- Verifying Digital Life link --- */				
				boolean bDigitalLifeLnk_cbu=UI.WaitForObject(oR_AccountOverview.lnkDigitalLife, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Digital Life link in 2ndry Nav bar", "True", String.valueOf(bDigitalLifeLnk_cbu), false);


				/*-- Verify Group 2 elements on Dashboard - Welcome Back with Last Login -- */

				/*-- Verify that 'Welcome Back' message should be displayed in account information section adjacent to Last login element ---- */
				if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")), 120,lDriver)) {
					String sText= lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")).getText();
					if(sText.contains("Welcome back")) {
						Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "True", "True", true);
					}
					else {
						Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "'Welcome Back' message is displayed in account information section", "'Welcome Back' message is NOT displayed in account information section", true);
					}
				}


				/*1. ------------- Verify Last login in ----------- */
				if (UI.WaitForObject(oR_AccountOverview.txtLastLogin, 5, lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Validate last login stamp exist on Account overview page", "Last login stamp exist", "Last login stamp exist", true);
				}
				else {
					Report.ValidationPoint(testContext.getName(), "Validate last login stamp exist on Account overview page", "Last login stamp exist", "Last login stamp do not exist on Account overview page", true);
				}

				//2. Verify Total balance text is displayed 
				boolean btotalBalance_cbu = UI.WaitForObject(oR_AccountOverview.txtTotalBalance, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Total balance text is displayed", "true", "true", true);

				//3. Verify Total balance value in $ is displayed 
				/*if(UI.WaitForObject(oR_AccountOverview.txtBalanceAmount, 5, lDriver).equals(true)) {
						Report.ValidationPoint(testContext.getName(), "Total balance amount is displayed", "true", "true", true);
					}	
					else {
						Report.ValidationPoint(testContext.getName(), "Total balance amount is displayed", "true", "Total balance amount is NOT displayed", true);					
					}*/

				// --- Verify balance amount and $ is displayed on Overview page
				List <WebElement> lstBalAmt_cbu = lDriver.findElements(By.xpath("//span[contains(@class,'Orange') or contains(@class,'Green') or contains(@class,'Red') and contains(text(),'$')]"));
				if(lstBalAmt_cbu.size() > 0){
					for(int i=0; i<lstBalAmt_cbu.size(); i++){
						Report.ValidationPoint(testContext.getName(), "Verify balance amount is displayed on Overview page", "True", "True", true);
						Report.ValidationPoint(testContext.getName(), "Verify balance amount is preceded with $ sign", "True", "True", true);
						String txtColorOfBalAmt = lstBalAmt_cbu.get(i).getAttribute("class");
						switch (txtColorOfBalAmt) {

						case "Orange":
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Orange color");
							break;

						case "Green":
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Green color");
							break;

						case "Red":
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Red color");	
							break;

						default:
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed");
							break;
						}
						Report.OperationPoint(testContext.getName(), "Balance amount is: "+lstBalAmt_cbu.get(i).getAttribute("text"));
					}
				}else{
					Report.ValidationPoint(testContext.getName(), "Verify balance amount and $ sign is displayed on Overview page", "True", "False", true);
				}

				//4. Verify link for view bill details
				if(UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails, 5, lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "View bill details link is displayed", "true", "true", true);
				}	
				else {
					Report.ValidationPoint(testContext.getName(), "View bill details link is displayed", "true", "View bill details link is NOT displayed", true);					
				}

				//5. Verify button for 'Make a Payment' 
				if(UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentInOverviewPage, 10, lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Make a Payment button is displayed", "true", "true", true);
				}	
				else {
					Report.ValidationPoint(testContext.getName(), "Make a Payment button is displayed", "true", "Make a Payment button is NOT displayed", true);					
				}

				//6. Verify Due date under Make a payment link
				/*if(UI.WaitForObject(oR_AccountOverview.txtDueDate, 40).equals(true)) {
						Report.ValidationPoint(testContext.getName(), "Due date is successfully displayed", "true", "true", true);
					}	
					else {
						Report.ValidationPoint(testContext.getName(), "Due date is successfully displayed", "true", "Due date is NOT displayed successfully", true);					
					}*/

				/*--- Verify Group 3 elements on Dashboard - I want to drop down - links --- */

				//Dashboard.VerifyLinksUnderIWantTo(testContext);

				if(oR_AccountOverview.btnIWantTo.isEnabled()) {

					Report.ValidationPoint(testContext.getName(), "'I want to' drop-down present in overview page", "true", "true", true);
					oR_AccountOverview.btnIWantTo.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'I want to' dropdown");

					//Verify all the Sub-Sections under 'I want to' drop down list
					new Actions(lDriver).moveToElement(oR_AccountOverview.txtViewAndPayBill).click().perform();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked View and Paybill");
					Thread.sleep(2000);

					oR_AccountOverview.btnIWantTo.click();
				}


				/* call method to check View and pay bill*/
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View & pay bill", " " , " " , null);

				/* call method to check View usage */
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View usage", " " , " ", null);

				/* call method to check Manage my plan */					
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Mange my plan", " " , " ", null);

				/* call method to check Manage my profile */ 
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Manage my profile", " " , " ", null);

				/* call method to check Get Help & Support */
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Get help & support", " " , " ", null);

				/* call method to check Shop AT&T */
				//UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", " " , " ", null); 

				/*---- Verify Group 4 elements on Dashboard - My Plans and Alerts --- */

				//Dashboard.VerifyAlerts(testContext);

				Boolean bAlert_cbu = UI.WaitForObject(oR_AccountOverview.btnAlert,40,lDriver);
				if(bAlert_cbu.equals(true))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Alerts are present", "True", "True", true);
					
					try
					{
						oR_AccountOverview.btnAlert.click();
						Report.OperationPoint(testContext.getName(),"Clicked on Alert button");
						Thread.sleep(3000);
						
						oR_AccountOverview.lnkHide.click();
						
					}catch(Exception Ee2)
					{
						Report.OperationPoint(testContext.getName(),"Hide link is not present under Alerts");
					}

					Thread.sleep(1000);
				}

				else {
					Report.ValidationPoint(testContext.getName(), "Validate Alerts are present", "True", "False", true);
				}

				/*---- Verify Group 5 elements on Dashboard - Plan Name , details  --- */

				//Verify My Plans header displayed....//
				if(UI.WaitForObject(oR_AccountOverview.txtHeaderMyPlans_1511, UI.iObjTimeOut, lDriver).equals(true)){
					Report.ValidationPoint(testContext.getName(), "Validate My Plans section is displayed", "My Plans section is displayed", "My Plans section is displayed", true);
				}else{
					
					//If there is only 1 plan, MyPlans sections wont display instead Alerts section will display there
					//Last modified by Clint, 25th Nov 2015
					try
					{
						
						if(UI.WaitForObject(oR_AccountOverview.txtSinglePlanAlertSection, UI.iObjTimeOut, lDriver).equals(true))
						{
							Report.OperationPoint(testContext.getName(), "My plans section is NOT displayed since, Only a Single plan is available and Alerts section is Displayed instead");
							Report.ValidationPoint(testContext.getName(), "Validate My Plans section is displayed", "Alert section is present instead of My Plans section since only one plan is available", "Alert section is present instead of My Plans section since only one plan is available", true);
							
						}else
						{
							Report.ValidationPoint(testContext.getName(), "Validate My Plans section is displayed", "My Plans section is displayed", "My Plans section header not displayed", true);
						}
						
					}catch(Exception e2)
					{
						Report.ValidationPoint(testContext.getName(), "Validate My Plans section is displayed", "My Plans section is displayed", "My Plans section header not displayed", true);
					}
					
				}


				try{
					//Verify available plans displayed....//
					//lstMyPlans
					List<WebElement> Plans= lDriver.findElements(By.xpath("//ul[@id='MainTab']/li//a"));
					if(Plans.size()>0){
						//ul[@id='MainTab']/li[1]//span/span[starts-with(@class,'Mar')]
						for(WebElement planLink:Plans){								
							planLink.click();  //Selecting plan..
							//String sSelectedPlanTabName=lDriver.findElement(By.xpath("//ul[@id='MainTab']/li["+i+"]//span/span[starts-with(@class,'Mar')]")).getText();
							Thread.sleep(3000);
							//verify plan name on right side .//							
							if(UI.WaitForObject(oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery, UI.iObjTimeOut, lDriver).equals(true)){
								String planNameInServiceSummary=oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery.getText().trim();
								if(!planNameInServiceSummary.isEmpty()){
									Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name displayed on service summary screen header", true);
									Report.OperationPoint(testContext.getName(), "Information - Plan name displayed on Service summary section -"+planNameInServiceSummary);
								}else{
									Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name not displayed on service summary header", true);
								}

							}else{
								Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name not displayed on service summary header", true);
							}

							//Verify phone image displayed on service summary section
							//Update from Yuvashree on 23rd Nov 2015: Phone image will not be displayed For Uverse or CBuverse customer types. Need Script changes for the same.
							//Code updated by Clint (23rd Nov 2015)
//							if(UI.WaitForObject(oR_AccountOverview.imgPhoneServiceSumaryOverview_1511, UI.iObjTimeOut, lDriver).equals(true)){
//								Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image displayed under service summary section", true);						
//							}else{
//								Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image not displayed under service summary section", true);
//							}

						}
					}else{
						if(UI.WaitForObject(oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery, UI.iObjTimeOut, lDriver).equals(true)){
							String planNameInServiceSummary=oR_AccountOverview.txtPlanNameInHeaderOfServiceSummery.getText().trim();
							if(!planNameInServiceSummary.isEmpty()){
								Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name displayed on service summary screen header", true);
								Report.OperationPoint(testContext.getName(), "Information - Plan name displayed on Service summary section -"+planNameInServiceSummary);
							}else{
								Report.ValidationPoint(testContext.getName(), "Verify Plan name displayed on Service summary screen header", "Plan name displayed on service summary screen header", "Plan name not displayed on service summary header", true);
							}
							//Verify phone image displayed on service summary section
							//Update from Yuvashree on 23rd Nov 2015: Phone image will not be displayed For Uverse or CBuverse customer types. Need Script changes for the same.
							//Code updated by Clint (23rd Nov 2015)
//							if(UI.WaitForObject(oR_AccountOverview.imgPhoneServiceSumaryOverview_1511, UI.iObjTimeOut, lDriver).equals(true)){
//								Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image displayed under service summary section", true);						
//							}else{
//								Report.ValidationPoint(testContext.getName(), "Verify Phone image displayed on service summary section on overview page", "Phone image displayed under service summary section", "Phone image not displayed under service summary section", true);
//							}
							
						}


					}					

				}catch(Exception e){
					String sErrMsg = e.getMessage();
					Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);						
				}



				/*---- Verify Group 6 elements on Dashboard - AT& Messages  --- */
				//verify AT&T message under service summary section
				if (UI.WaitForObject(oR_AccountOverview.txtATTMessage, 10, lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Verify AT&T message under service summary section", "true", "true", true);

					if(UI.WaitForObject(oR_AccountOverview.lstDisplayMessage,5, lDriver)){
						Report.ValidationPoint(testContext.getName(), "AT&T Message list box", "true", "true", true);	
					}
					else {
						Report.ValidationPoint(testContext.getName(), "AT&T Message list box", "AT&T Message list box should be displayed", "AT&T Message list box not displayed", true);
					}

				}
				else {
					Report.ValidationPoint(testContext.getName(), "Verify AT&T message under service summary section", "AT&T message should be displayed", "AT&T message is not displayed under service summary section", true);
				}

				//verify view all link should be displayed in front of AT&T message
				if (UI.WaitForObject(oR_AccountOverview.lnkViewAll, 10, lDriver)) {
					Report.TestPoint(testContext.getName(), "Validate view all link", "view all link displayed in front of AT&T message", "view all link displayed in front of AT&T message", true);
					Report.ValidationPoint(testContext.getName(), "Verify View all link", "True", "True", true);
				}	

				break;

				/******************* CB_Uverse case - End here **************************/

			case "Wireline" : case "Legacy Wireline":

				/* 
				 * Developed by Rahul Bakde
				 * on 7/23/2015
				 */


				// Verify Welcome back text adjacent to Logout button is displayed
				boolean bWelcomeTxt = UI.WaitForObject(oR_AccountOverview.txtWelComeBack, UI.iObjTimeOut,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Welcome back text is displayed adjacent to Logout button on Overview page", "True",String.valueOf(bWelcomeTxt), true);

				/* Verify Logout button is displayed on Overview page*/
				boolean bLogout = UI.WaitForObject(oR_AccountOverview.btnLogout, 5,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Logout button is displayed on Overview page", "True",String.valueOf(bLogout), true);

				/*--- Verify Secondary navigations links ---*/

				/* --- Verify Overview link is displayed on Secondary navigation on Overview page --- */					
				boolean bOverviewLink = UI.WaitForObject(oR_AccountOverview.lnkOverview, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Overview link is displayed on Secondary navigation on Overview page", "True", String.valueOf(bOverviewLink), true);

				/*--- Verify Billing, Usage, Payments link is displayed on Secondary navigation on Overview page---*/					
				boolean bBillingLnk = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Billing, Usage, Payments link is displayed on Secondary navigation on Overview page", "True",String.valueOf(bBillingLnk), false);

				/*---- Verify Home Phone link is displayed on Secondary navigation on Overview page --- */					
				boolean bHomePhonelnk = UI.WaitForObject(oR_AccountOverview.lnkHomePhoneSecondaryNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Home Phone link is displayed on Secondary navigation on Overview page", "True", Boolean.toString(bHomePhonelnk), false);

				/*--- Verify Profile link is displayed on Secondary navigation on Overview page ---*/					
				boolean ProfileLnk = UI.WaitForObject(oR_AccountOverview.lnkProfileSecNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Profile link is displayed on Secondary navigation on Overview page", "True", String.valueOf(ProfileLnk), false);

				/* --- Verify My Orders link is displayed on Secondary navigation on Overview page ---*/					
				boolean MyOrdersLnk = UI.WaitForObject(oR_AccountOverview.lnkMyOrdersSecNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify My Orders link is displayed on Secondary navigation on Overview page", "True", String.valueOf(MyOrdersLnk), false);

				/*--- Verifying Digital Life link is displayed on Secondary navigation on Overview page --- */				
				boolean DigitalLifeLnk = UI.WaitForObject(oR_AccountOverview.lnkDigitalLife, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Digital Life link is displayed on Secondary navigation on Overview page", "True", String.valueOf(DigitalLifeLnk), false);

				//					/*-- Welcome Back with Last Login -- */
				//
				//					/*-- Verify that 'Welcome Back' message should be displayed in account information section adjacent to Last login element ---- */
				//					UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")), 120);
				//					String sText= lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")).getText();
				//					if(sText.contains("Welcome back")) {
				//						Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "'Welcome Back' message is displayed in account information section", "'Welcome Back' message is displayed in account information section", true);
				//					}
				//					else {
				//						Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "'Welcome Back' message is displayed in account information section", "'Welcome Back' message is NOT displayed in account information section", true);
				//					}

				/* ------------- Verify Last logged text is displayed on Overview page----------- */
				boolean bLastLoggedInTxt = UI.WaitForObject(oR_AccountOverview.txtLastLoggedIn, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Last logged text is displayed on Overview page", "True", Boolean.toString(bLastLoggedInTxt), true);
				//					Report.OperationPoint(testContext.getName(), oR_AccountOverview.txtLastLoggedIn.getAttribute("text"));

				//Verify date is displayed in front of last logged in text
				if (bLastLoggedInTxt == true) {
					String data [] = new String[2];
					data = oR_AccountOverview.txtLastLoggedIn.getText().split(":");
					String date [] = data[1].split(" ");
					if (date[1].isEmpty() != true) {
						Report.ValidationPoint(testContext.getName(), "Verify date is displayed in front of last logged in text", "True", "True", true);
						Report.OperationPoint(testContext.getName(), "Date is : "+date[1]);
					}
				}

				//Verify My Services text is displayed on Overview page
				boolean bMyServicesTxt = UI.WaitForObject(oR_AccountOverview.txtMyServices, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify My Services text is displayed on Overview page", "True", Boolean.toString(bMyServicesTxt), true);

				//Verify Home Phone image is displayed on Overview page
				boolean bHomePhoneImg = UI.WaitForObject(oR_AccountOverview.imgHomePhone, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Home Phone image is displayed on Overview page", "True", Boolean.toString(bHomePhoneImg), true);

				//Verify Home Phone link is displayed on Overview page
				boolean bHomePhoneLink = UI.WaitForObject(oR_AccountOverview.lnkHomePhone, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Home Phone link is displayed on Overview page", "True", Boolean.toString(bHomePhoneLink), true);

				//Verify Other Services text is displayed on Overview page
				boolean bOtherServicesTxt = UI.WaitForObject(oR_AccountOverview.txtOtherServices, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Other Services text is displayed on Overview page", "True", Boolean.toString(bOtherServicesTxt), true);

				//Verify add Wireless device image is displayed on Overview page
				boolean bAddWirelessImg = UI.WaitForObject(oR_AccountOverview.imgAddWireless, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify add Wireless device image is displayed on Overview page", "True", Boolean.toString(bAddWirelessImg), true);

				//Verify Wireless text is displayed on Overview page for disabled wireless service
				boolean bWirelessTxtDisabledService = UI.WaitForObject(oR_AccountOverview.txtDisabledWireless, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Wireless text is displayed on Overview page for disabled wireless service", "True", Boolean.toString(bWirelessTxtDisabledService), true);

				//Verify Shop wireless link is displayed on Overview page
				boolean bShopWirelessLink = UI.WaitForObject(oR_AccountOverview.lnkShopWireless, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Shop wireless link is displayed on Overview page", "True", Boolean.toString(bShopWirelessLink), true);

				//Verify add Digital TV image is displayed on Overview page
				boolean bAddDigitalTVImg = UI.WaitForObject(oR_AccountOverview.imgAddDigitalTV, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify add Digital TV image is displayed on Overview page", "True", Boolean.toString(bAddDigitalTVImg), true);

				//Verify Digital TV text is displayed on Overview page for disabled Digital TV service
				boolean bDigitalTVTxtDisabledService = UI.WaitForObject(oR_AccountOverview.txtDisabledDigitalTV, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Digital TV text is displayed on Overview page for disabled Digital TV service", "True", Boolean.toString(bDigitalTVTxtDisabledService), true);

				//Verify Uverse TV only link is displayed on Overview page
				//boolean bUverseTVOnlyLink = UI.WaitForObject(oR_AccountOverview.lnkUverseTVOnly, 5, lDriver).equals(true);
				if (UI.WaitForObject(oR_AccountOverview.lnkUverseTVOnly, 5, lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Verify Uverse TV only link is displayed on Overview page", "True", "True", true);
				}
				
				//Verify add Internet image is displayed on Overview page
				boolean bAddInternetImg = UI.WaitForObject(oR_AccountOverview.imgAddUverseInternet, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify add Internet image is displayed on Overview page", "True", Boolean.toString(bAddInternetImg), true);

				//Verify Internet text is displayed on Overview page for disabled Uverse Internet service
				boolean bInternetTxtDisabledService = UI.WaitForObject(oR_AccountOverview.txtDisabledInternet, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Internet text is displayed on Overview page for disabled Uverse Internet service", "True", Boolean.toString(bInternetTxtDisabledService), true);

				//Verify U-verse Internet only link is displayed on Overview page
				boolean bUverseInternetOnlyLink = UI.WaitForObject(oR_AccountOverview.lnkUverseInternetOnly, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify U-verse Internet only link is displayed on Overview page", "True", Boolean.toString(bUverseInternetOnlyLink), true);

				// --- Verify Current balance text is displayed on Overview page
				boolean bCurrentBalTxt = UI.WaitForObject(oR_AccountOverview.txtTotalBalance, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Current balance text is displayed on Overview page", "True", Boolean.toString(bCurrentBalTxt), true);

				// --- Verify balance amount and $ is displayed on Overview page
				//					List <WebElement> lstBalAmt_wline = lDriver.findElements(By.xpath("//span[contains(@class,'Orange') or contains(@class,'Green') or contains(@class,'Red') and contains(text(),'$')]"));
				List <WebElement> lstBalAmt_wline = lDriver.findElements(By.xpath("//span[contains(@class,'Orange') or contains(@class,'Green') or contains(@class,'Red') and contains(text(),'$')]/parent::div"));
				if(lstBalAmt_wline.size() > 0){
					for(int i=0; i<lstBalAmt_wline.size(); i++){
						Report.ValidationPoint(testContext.getName(), "Verify balance amount is displayed on Overview page", "True", "True", true);
						Report.ValidationPoint(testContext.getName(), "Verify balance amount is preceded with $ sign", "True", "True", true);
						String txtColorOfBalAmt = lstBalAmt_wline.get(i).getAttribute("class");

						if (txtColorOfBalAmt.contains("Orange")) {
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Orange color");
						} else if(txtColorOfBalAmt.contains("Green")) {
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Green color");
						} else if(txtColorOfBalAmt.contains("Red")){
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Red color");
						} else{
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed");
						}
						Report.OperationPoint(testContext.getName(), "Balance amount is: "+lstBalAmt_wline.get(i).getText().trim());
					}
				}else{
					Report.ValidationPoint(testContext.getName(), "Verify balance amount and $ sign is displayed on Overview page", "True", "False", true);
				}

				// --- Verify view bill details link is displayed on Overview page
				Boolean bviewBillDetails = UI.WaitForObject(oR_AccountOverview.lnkviewBillDetails, 5,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "View bill details link is displayed on Overview page", "True", Boolean.toString(bviewBillDetails), true);

				// --- Verify Enroll in paperless billing link is displayed on Overview page
				Boolean bEnrollPaperlessBill = UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBillingForOldDashboard, 4,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "View Enroll in paperless billing is displayed on Overview page", "True", Boolean.toString(bEnrollPaperlessBill), true);

				// --- Verify 'Make a Payment' is displayed on Overview page
				Boolean bMap = UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentOverview, 5,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Make a Payment button is displayed", "True", String.valueOf(bMap), true);	

				// --- Verify Home Phone Options link is displayed on Overview page
				Boolean bHomePhoneOptions = UI.WaitForObject(oR_AccountOverview.lnkHomePhoneOptions, 5,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Home Phone Options link is displayed on Overview page", "True", String.valueOf(bHomePhoneOptions), true);	


				//					/*--- Verify I want to drop down - links ---
				//					/*
				//					/* call method to check View and pay bill */
				//					
				//					UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "View & pay bill", " " , " " , "Present");
				//
				//					/* call method to check View usage */
				//					UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "View usage", " " , " ", "Present");
				//
				//					/* call method to check Manage my plan */					
				//					UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "Mange my plan", " " , " ", "Present");
				//
				//					/* call method to check Manage my profile */ 
				//					UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "Manage my profile", " " , " ", "Present");
				//
				//					/* call method to check Get Help & Support */
				//					UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "Get help & support", " " , " ", "Present");
				//
				//					/* call method to check Shop AT&T */
				//					UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "Shop AT&T", " " , " ", "Present");

				// --- Verify Service Summary text is displayed on Overview page
				Boolean bServiceSummaryTxt = UI.WaitForObject(oR_AccountOverview.lnkServiceSummary, 5,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Service Summary text is displayed on Overview page", "True", String.valueOf(bHomePhoneOptions), true);	

				// --- Verify Home Phone link is displayed under Service Summary on Overview page
				Boolean bHomePhone = UI.WaitForObject(oR_AccountOverview.lnkHomePhoneUnderServiceSummary, 5,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Home Phone link is displayed under Service Summary on Overview page", "True", String.valueOf(bHomePhone), true);	

				break;					

			case "CB Wireline":
				// Verify Welcome back text adjacent to Logout button is displayed
				boolean bWelcomeTxt_cbWire = UI.WaitForObject(oR_AccountOverview.txtWelComeBack, UI.iObjTimeOut,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Welcome back text is displayed adjacent to Logout button on Overview page", "True",String.valueOf(bWelcomeTxt_cbWire), true);

				/* Verify Logout button is displayed on Overview page*/
				boolean bLogout_cbWire = UI.WaitForObject(oR_AccountOverview.btnLogout, 5,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Logout button is displayed on Overview page", "True",String.valueOf(bLogout_cbWire), true);

				/*--- Verify Secondary navigations links ---*/

				/* --- Verify Overview link is displayed on Secondary navigation on Overview page --- */					
				boolean bOverviewLink_cbWire = UI.WaitForObject(oR_AccountOverview.lnkOverview, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Overview link is displayed on Secondary navigation on Overview page", "True", String.valueOf(bOverviewLink_cbWire), true);

				/*--- Verify Billing, Usage, Payments link is displayed on Secondary navigation on Overview page---*/					
				boolean bBillingLnk_cbWire = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Billing, Usage, Payments link is displayed on Secondary navigation on Overview page", "True",String.valueOf(bBillingLnk_cbWire), false);

				/*---- Verify Home Phone link is displayed on Secondary navigation on Overview page --- */					
				boolean bHomePhonelnk_cbWire = UI.WaitForObject(oR_AccountOverview.lnkHomePhoneSecondaryNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Home Phone link is displayed on Secondary navigation on Overview page", "True", Boolean.toString(bHomePhonelnk_cbWire), false);

				/*--- Verify Profile link is displayed on Secondary navigation on Overview page ---*/					
				boolean ProfileLnk_cbWire = UI.WaitForObject(oR_AccountOverview.lnkProfileSecNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Profile link is displayed on Secondary navigation on Overview page", "True", String.valueOf(ProfileLnk_cbWire), false);

				/* --- Verify My Orders link is displayed on Secondary navigation on Overview page ---*/					
				boolean MyOrdersLnk_cbWire = UI.WaitForObject(oR_AccountOverview.lnkMyOrdersSecNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify My Orders link is displayed on Secondary navigation on Overview page", "True", String.valueOf(MyOrdersLnk_cbWire), false);

				/*--- Verifying Digital Life link is displayed on Secondary navigation on Overview page --- */				
				boolean DigitalLifeLnk_cbWire = UI.WaitForObject(oR_AccountOverview.lnkDigitalLife, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Digital Life link is displayed on Secondary navigation on Overview page", "True", String.valueOf(DigitalLifeLnk_cbWire), false);

				//					/*-- Welcome Back with Last Login -- */
				//
				//					/*-- Verify that 'Welcome Back' message should be displayed in account information section adjacent to Last login element ---- */
				//					UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")), 120);
				//					String sText= lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")).getText();
				//					if(sText.contains("Welcome back")) {
				//						Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "'Welcome Back' message is displayed in account information section", "'Welcome Back' message is displayed in account information section", true);
				//					}
				//					else {
				//						Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "'Welcome Back' message is displayed in account information section", "'Welcome Back' message is NOT displayed in account information section", true);
				//					}

				/* ------------- Verify Last logged text is displayed on Overview page----------- */
				boolean bLastLoggedInTxt_cbWire = UI.WaitForObject(oR_AccountOverview.txtLastLoggedIn, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Last logged text is displayed on Overview page", "True", Boolean.toString(bLastLoggedInTxt_cbWire), true);
				//					Report.OperationPoint(testContext.getName(), oR_AccountOverview.txtLastLoggedIn.getAttribute("text"));

				//Verify date is displayed in front of last logged in text
				if (bLastLoggedInTxt_cbWire == true) {
					String data [] = new String[2];
					data = oR_AccountOverview.txtLastLoggedIn.getText().split(":");
					String date [] = data[1].split(" ");
					if (date[1].isEmpty() != true) {
						Report.ValidationPoint(testContext.getName(), "Verify date is displayed in front of last logged in text", "True", "True", true);
						Report.OperationPoint(testContext.getName(), "Date is : "+date[1]);
					}
				}

				//Verify My Services text is displayed on Overview page
				boolean bMyServicesTxt_cbWire = UI.WaitForObject(oR_AccountOverview.txtMyServices, 5, lDriver).equals(true);
				if (bMyServicesTxt_cbWire) {
					Report.ValidationPoint(testContext.getName(), "Verify My Services text is displayed on Overview page", "True", Boolean.toString(bMyServicesTxt_cbWire), true);

					//Verify Home Phone image is displayed on Overview page
					boolean bHomePhoneImg_cbWire = UI.WaitForObject(oR_AccountOverview.imgHomePhone, 5, lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Verify Home Phone image is displayed on Overview page", "True", Boolean.toString(bHomePhoneImg_cbWire), true);

					//Verify Home Phone link is displayed on Overview page
					boolean bHomePhoneLink_cbWire = UI.WaitForObject(oR_AccountOverview.lnkHomePhone, 5, lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Verify Home Phone link is displayed on Overview page", "True", Boolean.toString(bHomePhoneLink_cbWire), true);

					//Verify Other Services text is displayed on Overview page
					boolean bOtherServicesTxt_cbWire = UI.WaitForObject(oR_AccountOverview.txtOtherServices, 5, lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Verify Other Services text is displayed on Overview page", "True", Boolean.toString(bOtherServicesTxt_cbWire), true);

					//Verify add Wireless device image is displayed on Overview page
					boolean bAddWirelessImg_cbWire = UI.WaitForObject(oR_AccountOverview.imgAddWireless, 5, lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Verify add Wireless device image is displayed on Overview page", "True", Boolean.toString(bAddWirelessImg_cbWire), true);

					//Verify Wireless text is displayed on Overview page for disabled wireless service
					boolean bWirelessTxtDisabledService_cbWire = UI.WaitForObject(oR_AccountOverview.txtDisabledWireless, 5, lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Verify Wireless text is displayed on Overview page for disabled wireless service", "True", Boolean.toString(bWirelessTxtDisabledService_cbWire), true);

					//Verify Shop wireless link is displayed on Overview page
					boolean bShopWirelessLink_cbWire = UI.WaitForObject(oR_AccountOverview.lnkShopWireless, 5, lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Verify Shop wireless link is displayed on Overview page", "True", Boolean.toString(bShopWirelessLink_cbWire), true);

					//Verify add Digital TV image is displayed on Overview page
					boolean bAddDigitalTVImg_cbWire = UI.WaitForObject(oR_AccountOverview.imgAddDigitalTV, 5, lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Verify add Digital TV image is displayed on Overview page", "True", Boolean.toString(bAddDigitalTVImg_cbWire), true);

					//Verify Digital TV text is displayed on Overview page for disabled Digital TV service
					boolean bDigitalTVTxtDisabledService_cbWire = UI.WaitForObject(oR_AccountOverview.txtDisabledDigitalTV, 5, lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Verify Digital TV text is displayed on Overview page for disabled Digital TV service", "True", Boolean.toString(bDigitalTVTxtDisabledService_cbWire), true);

					//Verify Uverse TV only link is displayed on Overview page
					boolean bUverseTVOnlyLink_cbWire = UI.WaitForObject(oR_AccountOverview.lnkUverseTVOnly, 5, lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Verify Uverse TV only link is displayed on Overview page", "True", Boolean.toString(bUverseTVOnlyLink_cbWire), true);

					//Verify add Internet image is displayed on Overview page
					boolean bAddInternetImg_cbWire = UI.WaitForObject(oR_AccountOverview.imgAddUverseInternet, 5, lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Verify add Internet image is displayed on Overview page", "True", Boolean.toString(bAddInternetImg_cbWire), true);

					//Verify Internet text is displayed on Overview page for disabled Uverse Internet service
					boolean bInternetTxtDisabledService_cbWire = UI.WaitForObject(oR_AccountOverview.txtDisabledInternet, 5, lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Verify Internet text is displayed on Overview page for disabled Uverse Internet service", "True", Boolean.toString(bInternetTxtDisabledService_cbWire), true);

					//Verify U-verse Internet only link is displayed on Overview page
					boolean bUverseInternetOnlyLink_cbWire = UI.WaitForObject(oR_AccountOverview.lnkUverseInternetOnly, 5, lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Verify U-verse Internet only link is displayed on Overview page", "True", Boolean.toString(bUverseInternetOnlyLink_cbWire), true);
				}else{
					// --- Verify Current balance text is displayed on Overview page
					boolean bCurrentBalTxt_cbWire = UI.WaitForObject(oR_AccountOverview.txtTotalBalance, 5, lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Verify Current balance text is displayed on Overview page", "True", Boolean.toString(bCurrentBalTxt_cbWire), true);

					// --- Verify balance amount and $ is displayed on Overview page
					//					List <WebElement> lstBalAmt_wline = lDriver.findElements(By.xpath("//span[contains(@class,'Orange') or contains(@class,'Green') or contains(@class,'Red') and contains(text(),'$')]"));
					List <WebElement> lstBalAmt_cbWire = lDriver.findElements(By.xpath("//span[contains(@class,'Orange') or contains(@class,'Green') or contains(@class,'Red') and contains(text(),'$')]/parent::div"));
					if(lstBalAmt_cbWire.size() > 0){
						for(int i=0; i<lstBalAmt_cbWire.size(); i++){
							Report.ValidationPoint(testContext.getName(), "Verify balance amount is displayed on Overview page", "True", "True", true);
							Report.ValidationPoint(testContext.getName(), "Verify balance amount is preceded with $ sign", "True", "True", true);
							String txtColorOfBalAmt = lstBalAmt_cbWire.get(i).getAttribute("class");

							if (txtColorOfBalAmt.contains("Orange")) {
								Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Orange color");
							} else if(txtColorOfBalAmt.contains("Green")) {
								Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Green color");
							} else if(txtColorOfBalAmt.contains("Red")){
								Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Red color");
							} else{
								Report.OperationPoint(testContext.getName(), "Balance amount is displayed");
							}
							Report.OperationPoint(testContext.getName(), "Balance amount is: "+lstBalAmt_cbWire.get(i).getText().trim());
						}
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify balance amount and $ sign is displayed on Overview page", "True", "False", true);
					}
					
					// --- Verify Alert button is displayed on Overview page ---
//					boolean bAlertButton_tb = UI.WaitForObject(oR_AccountOverview.btnAlertBtn, 5);
//					Report.ValidationPoint(testContext.getName(), "Verify Alert btn is displayed on Overview page", "True", String.valueOf(bAlertButton_tb), true);

					
					// --- Verify view bill details link is displayed on Overview page
					Boolean bviewBillDetails_cbWire = UI.WaitForObject(oR_AccountOverview.lnkviewBillDetails, 5,lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "View bill details link is displayed on Overview page", "True", Boolean.toString(bviewBillDetails_cbWire), true);

					//						// --- Verify Enroll in paperless billing link is displayed on Overview page
					//						Boolean bEnrollPaperlessBill_cbWire = UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBillingForOldDashboard, 4).equals(true);
					//						Report.ValidationPoint(testContext.getName(), "View Enroll in paperless billing is displayed on Overview page", "True", Boolean.toString(bEnrollPaperlessBill_cbWire), true);

					// --- Verify 'Make a Payment' is displayed on Overview page
					Boolean bMap_cbWire = UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentOverview, 5,lDriver).equals(true);
					Report.ValidationPoint(testContext.getName(), "Make a Payment button is displayed", "True", String.valueOf(bMap_cbWire), true);	

					//						// --- Verify Home Phone Options link is displayed on Overview page
					//						Boolean bHomePhoneOptions_cbWire = UI.WaitForObject(oR_AccountOverview.lnkHomePhoneOptions, 5).equals(true);
					//						Report.ValidationPoint(testContext.getName(), "Verify Home Phone Options link is displayed on Overview page", "True", String.valueOf(bHomePhoneOptions_cbWire), true);	


					//					/*--- Verify I want to drop down - links ---
					//					/*
					//					/* call method to check View and pay bill */
					//					
					//					UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "View & pay bill", " " , " " , "Present");
					//
					//					/* call method to check View usage */
					//					UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "View usage", " " , " ", "Present");
					//
					//					/* call method to check Manage my plan */					
					//					UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "Mange my plan", " " , " ", "Present");
					//
					//					/* call method to check Manage my profile */ 
					//					UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "Manage my profile", " " , " ", "Present");
					//
					//					/* call method to check Get Help & Support */
					//					UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "Get help & support", " " , " ", "Present");
					//
					//					/* call method to check Shop AT&T */
					//					UI.ClickOrVerifyLinkUnderIWantToDropdown("VERIFY", "Shop AT&T", " " , " ", "Present");

					// --- Verify Service Summary text is displayed on Overview page
					//						Boolean bServiceSummaryTxt_cbWire = UI.WaitForObject(oR_AccountOverview.lnkServiceSummary, 5).equals(true);
					//						Report.ValidationPoint(testContext.getName(), "Verify Service Summary text is displayed on Overview page", "True", String.valueOf(bServiceSummaryTxt_cbWire), true);	

					// --- Verify Home Phone link is displayed under Service Summary on Overview page
					//						Boolean bHomePhone_cbWire = UI.WaitForObject(oR_AccountOverview.lnkHomePhoneUnderServiceSummary, 5).equals(true);
					//						Report.ValidationPoint(testContext.getName(), "Verify Home Phone link is displayed under Service Summary on Overview page", "True", String.valueOf(bHomePhone_cbWire), true);	

				}
				break;					


			case "Titan Bundle":

				/* 
				 * Developed by Rahul Bakde
				 * on 7/28/2015
				 */

				// Verify Welcome back text adjacent to Logout button is displayed
				boolean bWelcomeTxt_tb = UI.WaitForObject(oR_AccountOverview.txtWelComeBack, UI.iObjTimeOut,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Welcome back text is displayed adjacent to Logout button on Overview page", "True",String.valueOf(bWelcomeTxt_tb), true);

				// Verify First Name link is displayed adjacent to Welcome back text on Overview page
				boolean bFirstName_tb = UI.WaitForObject(oR_AccountOverview.lnkFirstName, UI.iObjTimeOut,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify First Name link is displayed adjacent to Welcome back text on Overview page", "True",String.valueOf(bFirstName_tb), true);

				/* Verify Logout button is displayed on Overview page*/
				boolean bLogout_tb = UI.WaitForObject(oR_AccountOverview.btnLogout, 5,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Logout button is displayed on Overview page", "True",String.valueOf(bLogout_tb), true);

				/*--- Verify Secondary navigations links ---*/

				/* --- Verify Overview link is displayed on Secondary navigation on Overview page --- */					
				boolean bOverviewLink_tb = UI.WaitForObject(oR_AccountOverview.lnkOverview, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Overview link is displayed on Secondary navigation on Overview page", "True", String.valueOf(bOverviewLink_tb), true);

				/*--- Verify Billing, Usage, Payments link is displayed on Secondary navigation on Overview page---*/					
				boolean bBillingLnk_tb = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Billing, Usage, Payments link is displayed on Secondary navigation on Overview page", "True",String.valueOf(bBillingLnk_tb), false);

				/*---- Verifying Wireless link is displayed on Secondary navigation on Overview page --- */					
				if(UI.WaitForObject(oR_AccountOverview.lnkWirelessSecNav, 1, lDriver).equals(true)) {
					Report.ValidationPoint(testContext.getName(), "Verify Digital Wireless link in 2ndry Nav bar", "True", "True", false);
				}

				/*--- Verify Profile link is displayed on Secondary navigation on Overview page ---*/					
				boolean ProfileSecLnk_tb = UI.WaitForObject(oR_AccountOverview.lnkProfileSecNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Profile link is displayed on Secondary navigation on Overview page", "True", String.valueOf(ProfileSecLnk_tb), false);

				/* --- Verify My Orders link is displayed on Secondary navigation on Overview page ---*/					
				boolean MyOrdersSecLnk_tb = UI.WaitForObject(oR_AccountOverview.lnkMyOrdersSecNav, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify My Orders link is displayed on Secondary navigation on Overview page", "True", String.valueOf(MyOrdersSecLnk_tb), false);

				/*--- Verifying Digital Life link is displayed on Secondary navigation on Overview page --- */				
				boolean DigitalLifeSecLnk_tb = UI.WaitForObject(oR_AccountOverview.lnkDigitalLife, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Digital Life link is displayed on Secondary navigation on Overview page", "True", String.valueOf(DigitalLifeSecLnk_tb), false);

				//					/*-- Welcome Back with Last Login -- */
				//
				//					/*-- Verify that 'Welcome Back' message should be displayed in account information section adjacent to Last login element ---- */
				UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")), 120,lDriver);
				String sText_tb = lDriver.findElement(By.xpath("//*[contains(@id,'User')]//h2")).getText();
				boolean bWel_tb = sText_tb.contains("Welcome back");
				Report.ValidationPoint(testContext.getName(), "Verify 'Welcome Back' message in account information section", "True", String.valueOf(bWel_tb), true);

				/* ------------- Verify Last logged text is displayed on Overview page----------- */
				boolean bLastLoggedInTxt_tb = UI.WaitForObject(oR_AccountOverview.txtLastLoggedIn, 5, lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Last logged text is displayed on Overview page", "True", Boolean.toString(bLastLoggedInTxt_tb), true);
				//					Report.OperationPoint(testContext.getName(), "Last login date is : "+oR_AccountOverview.txtLastLoggedIn.getText());

				//Verify date is displayed in front of last logged in text
				if (bLastLoggedInTxt_tb == true) {
					String data_tb [] = new String[2];
					data_tb = oR_AccountOverview.txtLastLoggedIn.getText().split(":");
					String date_tb [] = data_tb[1].split(" ");
					if (date_tb[1].isEmpty() != true) {
						Report.ValidationPoint(testContext.getName(), "Verify date is displayed in front of last logged in text", "True", "True", true);
						Report.OperationPoint(testContext.getName(), "Date is : "+date_tb[1]);
					} else { 
						Report.ValidationPoint(testContext.getName(), "Verify date is displayed in front of last logged in text", "True", "False", true);
					}
				}

				//--- Verify Total balance text is displayed on Overview page --- 
				boolean bTotalBal_tb = UI.WaitForObject(oR_AccountOverview.txtTotalBalance, 4,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify total balance text is displayed on Overview page", "True", String.valueOf(bTotalBal_tb), true);

				//					// --- Verify Total balance amount in $ is displayed on Overview page ---
				//					boolean bTotalBalAmt_tb = UI.WaitForObject(oR_AccountOverview.txtBalanceAmount, 4).equals(true);
				//					if (bTotalBalAmt_tb == true) {
				//						String sBalAmt_tb [] = oR_AccountOverview.txtBalanceAmount.getText().split("$");
				//						if (sBalAmt_tb.length>0) {
				//							Report.ValidationPoint(testContext.getName(), "Verify Total balance amount in $ is displayed on Overview page", "True", String.valueOf(bTotalBalAmt_tb), true);
				//							Report.OperationPoint(testContext.getName(), "Total balance amount is : "+sBalAmt_tb[1]);
				//						} else {
				//							Report.ValidationPoint(testContext.getName(), "Verify $ is displayed in balance amount on Overview page", "True", String.valueOf(bTotalBalAmt_tb), true);
				//						}				
				//					} else {
				//						Report.ValidationPoint(testContext.getName(), "Verify $ is displayed in balance amount on Overview page", "True", String.valueOf(bTotalBalAmt_tb), true);
				//					}

				// --- Verify balance amount and $ is displayed on Overview page
				//					List <WebElement> lstBalAmt = lDriver.findElements(By.xpath("//span[contains(@class,'Orange') or contains(@class,'Green') or contains(@class,'Red') and contains(text(),'$')]"));
				List <WebElement> lstBalAmt_tb = lDriver.findElements(By.xpath("//span[contains(@class,'Orange') or contains(@class,'Green') or contains(@class,'Red') and contains(text(),'$')]/parent::div/span"));
				if(lstBalAmt_tb.size() > 0){
					for(int i=0; i<lstBalAmt_tb.size(); i++){
						Report.ValidationPoint(testContext.getName(), "Verify balance amount is displayed on Overview page", "True", "True", true);
						Report.ValidationPoint(testContext.getName(), "Verify balance amount is preceded with $ sign", "True", "True", true);
						String txtColorOfBalAmt = lstBalAmt_tb.get(i).getAttribute("class");
						boolean bOrange = txtColorOfBalAmt.contains("Orange");
						boolean bGreen = txtColorOfBalAmt.contains("Green");
						boolean bRed = txtColorOfBalAmt.contains("Red");

						if (bOrange == true) {
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Orange color");
						} else if(bGreen == true) {
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Green color");
						} else if(bRed == true){
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed in Red color");
						} else{
							Report.OperationPoint(testContext.getName(), "Balance amount is displayed");
						}
						Report.OperationPoint(testContext.getName(), "Balance amount is: "+lstBalAmt_tb.get(i).getText().trim());
					}
				}else{
					Report.ValidationPoint(testContext.getName(), "Verify balance amount and $ sign is displayed on Overview page", "True", "False", true);
				}

				// --- Verify link for view bill details ---
				boolean bViewBillLnk_tb = UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails, 4,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify view bill details link is displayed on Overview page", "True", String.valueOf(bViewBillLnk_tb), true);

				// --- Verify 'Make a Payment' is displayed on Overview page
				Boolean bMap_tb = UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentOverview, 5,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Make a Payment button is displayed on Overview page", "True", String.valueOf(bMap_tb), true);	

				// --- Verify past due text is displayed on Overview page
				Boolean bPastDue_tb = UI.WaitForObject(oR_AccountOverview.txtPastDueTxt, 5,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify past due text is displayed on Overview page", "True", String.valueOf(bPastDue_tb), true);	

				// --- Verify links under I want to dropdown 
				//last edited by Clint on 20th Nov | Reason: ClickOrVerifyLinkUnderIWantToDropdown() not working
				
				new Actions(lDriver).moveToElement(oR_AccountOverview.btnIWantTo).click().perform();
				//boolean bViewPayBill_tb = UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View & pay bill", null, null, null);
				boolean bViewPayBill_tb = UI.WaitForObject(oR_AccountOverview.txtViewAndPayBill, UI.iObjTimeOut, lDriver);
				//					Thread.sleep(1000);
				//boolean bViewUsage_tb = UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "View usage", null, null, null);
				boolean bViewUsage_tb = UI.WaitForObject(oR_AccountOverview.lnkViewUsageUnderIwantTo, UI.iObjTimeOut, lDriver);
				//					Thread.sleep(1000);
				//boolean bManageMyPlan_tb = UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Manage my plan", null, null, null); 
				boolean bManageMyPlan_tb = UI.WaitForObject(oR_AccountOverview.txtManageMyPlan, UI.iObjTimeOut, lDriver);
				//					Thread.sleep(3000);
				//boolean bManageMyProfile_tb = UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Manage my profile", null, null, null);
				boolean bManageMyProfile_tb = UI.WaitForObject(oR_AccountOverview.txtManageMyProfile, UI.iObjTimeOut, lDriver);
				//					Thread.sleep(3000);
				//boolean bGetHelpSupport_tb = UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Get help & support", null, null, null);
				boolean bGetHelpSupport_tb = UI.WaitForObject(oR_AccountOverview.txtGetHelpAndSupport, UI.iObjTimeOut, lDriver);
				//					Thread.sleep(3000);
				//boolean bShopATT_tb = UI.ClickOrVerifyLinkUnderIWantToDropdown("Verify", "Shop AT&T", null, null, null);
				boolean bShopATT_tb = UI.WaitForObject(oR_AccountOverview.txtShopAtt, UI.iObjTimeOut, lDriver);
				//					Thread.sleep(3000);
				// --- Verify link View & Pay bill should be displayed under I want to dropdown
				Report.ValidationPoint(testContext.getName(), "Verify link View & Pay bill should be displayed under I want to dropdown", "True", String.valueOf(bViewPayBill_tb), true);	

				// --- Verify link View usage should be displayed under I want to dropdown
				Report.ValidationPoint(testContext.getName(), "Verify link View usage should be displayed under I want to dropdown", "True", String.valueOf(bViewUsage_tb), true);	

				// --- Verify link Manage my plan should be displayed under I want to dropdown
				Report.ValidationPoint(testContext.getName(), "Verify link Manage my plan should be displayed under I want to dropdown", "True", String.valueOf(bManageMyPlan_tb), true);	

				// --- Verify link Manage my profile should be displayed under I want to dropdown
				Report.ValidationPoint(testContext.getName(), "Verify link Manage my profile should be displayed under I want to dropdown", "True", String.valueOf(bManageMyProfile_tb), true);	

				// --- Verify link Get help & support should be displayed under I want to dropdown
				Report.ValidationPoint(testContext.getName(), "Verify link Get help & support should be displayed under I want to dropdown", "True", String.valueOf(bGetHelpSupport_tb), true);	

				// --- Verify link Shop AT&T should be displayed under I want to dropdown
				Report.ValidationPoint(testContext.getName(), "Verify link Shop AT&T should be displayed under I want to dropdown", "True", String.valueOf(bShopATT_tb), true);	

				// --- Verify Alert button is displayed on Overview page ---
				boolean bAlertButton_tb = UI.WaitForObject(oR_AccountOverview.btnAlertBtn, 5,lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Alert btn is displayed on Overview page", "True", String.valueOf(bAlertButton_tb), true);

				// --- Verify AT&T message text is displayed under service summary section on Overview page ---
				boolean bATTMsg_tb = UI.WaitForObject(oR_AccountOverview.txtATTMessage, 5,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify AT&T message text is displayed under service summary section on Overview page", "True", String.valueOf(bATTMsg_tb), true);

				// --- Verify AT&T message box is displayed under service summary section on Overview page ---
				boolean bATTMsgBox_tb = UI.WaitForObject(oR_AccountOverview.txtATTMsgBox, 5,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify AT&T message box is displayed under service summary section on Overview page", "True", String.valueOf(bATTMsgBox_tb), true);				

				// --- Verify view all link is displayed in front of AT&T message ---
				boolean bViewAll_tb = UI.WaitForObject(oR_AccountOverview.lnkViewAll, 5,lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify view all link is displayed in front of AT&T message", "True", String.valueOf(bViewAll_tb), true);

				// --- Verify Uverse TV product tile is displayed on Overview page ---
				boolean bUverseTVProdTile_tb = UI.WaitForObject(oR_AccountOverview.lnkUverseTVProductTile, 5,lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Uverse TV product tile is displayed on Overview page", "True", String.valueOf(bUverseTVProdTile_tb), true);

				//click on Uverse TV product tile
				if (bUverseTVProdTile_tb) {
					oR_AccountOverview.lnkUverseTVProductTile.click();

					// --- Verify Uverse TV plan name is displayed under service summary section on Overview page ---\
					boolean bUverseTVPlanName_tb = UI.WaitForObject(oR_AccountOverview.txtUverseTVPlan, 5,lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify Uverse TV plan name is displayed under service summary section on Overview page", "True", String.valueOf(bUverseTVPlanName_tb), true);

					// --- Verify plan details link is displayed under service summary section on Overview page ---\
					boolean bPlanDetails_tb = UI.WaitForObject(oR_AccountOverview.lnkPlanDetails, 5,lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify plan details link is displayed under service summary section on Overview page", "True", String.valueOf(bPlanDetails_tb), true);

					// --- Verify Fix it now, Support tool link is displayed under service summary section on Overview page ---\
					boolean bFixItNow_tb = UI.WaitForObject(oR_AccountOverview.lnkTroubleshoot, 5,lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify Fix it now, Support tool link is displayed under service summary section on Overview page", "True", String.valueOf(bFixItNow_tb), true);

					// --- Verify Billing period text is displayed under service summary section on Overview page ---\
					boolean bBillingPeriod_tb = UI.WaitForObject(oR_AccountOverview.txtDaysLeft, 5,lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify Billing period text is displayed under service summary section on Overview page", "True", String.valueOf(bBillingPeriod_tb), true);

					// --- Verify Recent orders text is displayed under service summary section on Overview page ---\
					boolean bRecentOrders_tb = UI.WaitForObject(oR_AccountOverview.txtRecentOrders, 5,lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify Recent orders text is displayed under service summary section on Overview page", "True", String.valueOf(bRecentOrders_tb), true);

					// --- Verify Charges text is displayed under service summary section on Overview page ---\
					boolean bCharges_tb = UI.WaitForObject(oR_AccountOverview.txtCharges, 5,lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify Charges text is displayed under service summary section on Overview page", "True", String.valueOf(bCharges_tb), true);
				}

				break;
			}	
		} catch (Exception e)
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}

	}


}
