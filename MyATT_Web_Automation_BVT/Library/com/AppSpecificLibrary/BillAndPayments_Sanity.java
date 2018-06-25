/******************************************************************************************************
   Library - BillAndPayments_Sanity 
   Description-   Includes all functions/methods related to Bill and payments page objects verification
   Date created - 28-Jul-15
   Developed by - Deepak
   Last Modified By -
   Last Modified Date -
 ******************************************************************************************************/

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

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;

import com.OR.MyATT.OR_ATT;
import com.OR.MyATT.OR_AccountOverview;
import com.OR.MyATT.OR_AddaDevice;
import com.OR.MyATT.OR_AutoPayEnrollment;
import com.OR.MyATT.OR_BillAndUsage;
import com.OR.MyATT.OR_EditPaymentInformation;
import com.OR.MyATT.OR_EditPaymentProfile;
import com.OR.MyATT.OR_Login;
import com.OR.MyATT.OR_MakeAPayment;
import com.OR.MyATT.OR_ManageAutoPay;
import com.OR.MyATT.OR_MyWirelessPlan;
import com.OR.MyATT.OR_PaperlessBilling;
import com.OR.MyATT.OR_PayOffInstallmentPlan;
import com.OR.MyATT.OR_PaymentConfirmation;
import com.OR.MyATT.OR_PrintBillHistoryTab;
import com.OR.MyATT.OR_Profile;
import com.OR.MyATT.OR_ReviewPaymentDetails;
import com.OR.MyATT.OR_SMBBillDetails;
import com.OR.MyATT.OR_UpgradePhone;
import com.OR.MyATT.OR_ViewOrChangeRatePlan;
import com.SupportingFiles.IO;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;
import com.SupportingFiles.UI;
//import com.OR.AccountOverview.OR_AccountOverview;
//import com.OR.BillAndPayments.*;
//import com.OR.MyATT.*;

public class BillAndPayments_Sanity extends LaunchAndLogout {
	static Hashtable<String, String> sTestParams = new Hashtable<String, String>();
	String sBalance, sAccountNumber, sDueDate,sPastDuePleasePay, sBalanceDue; 
	


	/**************************************************************
	 * Function Name - VerifyMakeAPaymentPage 
	 * Description- 
	 * Parameters - 
	 * Date created -
	 * Developed by - 
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void VerifyMakeAPaymentPage(final ITestContext testContext)
			throws Exception {
		
		try {
				WebDriver lDriver = UI.getDriver(testContext.getName());
				OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
				OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
				OR_MakeAPayment oR_MakeAPayment = PageFactory.initElements(lDriver, OR_MakeAPayment.class);
		
				String sAccBalance, sDueDate , sPastDue ;
				Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
				
				
				/*--- Need to retrieve DataType for Overview page verification */
				String sLoginType= IO.GetParamVal(sTestParams, "DataType");
				
				
				/*---Switch to desired data type as per data sheet ---*/
				switch (sLoginType) {
				
					case "SLID":
						
						if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,20,lDriver).equals(true)){
							//UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingAndPaymentSecNav, oR_AccountOverview.lnkBillDetailsTertNav);
							Report.TestPoint(testContext.getName(), "Validate Billing & Payment link is present in secondary nav", "True", "True", true);
							//validate Bill and Usage page is displayed
							oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
						}
						
						/**************** Group 1 Elements **********************/
						UI.WaitForObject(oR_BillAndUsage.lnkBillTab, 120, lDriver);
						new Actions(lDriver).moveToElement(oR_BillAndUsage.lnkBillTab).click().build().perform();
						
						//Verify Billing & Usage page is displayed
						boolean bBillingPage_sld = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
						Report.TestPoint(testContext.getName(), "Validate that Billing And Usage page is displayed", "true", String.valueOf(bBillingPage_sld), true);

						boolean bTotalAmtDueby_sld = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 5, lDriver);					
						Report.TestPoint(testContext.getName(), "Verify Total Amount due By is displayed", "true", String.valueOf(bTotalAmtDueby_sld), true);
				 
						List <WebElement> lstBalAmt_sld = lDriver.findElements(By.xpath("//span[contains(@class,'Orange') or contains(@class,'Green') or contains(@class,'Red') and contains(text(),'$')]"));
						if(lstBalAmt_sld.size() > 0){
							for(int i=0; i<lstBalAmt_sld.size(); i++) {
								Report.ValidationPoint(testContext.getName(), "Verify balance amount is displayed on Overview page", "True", "True", true);
								Report.ValidationPoint(testContext.getName(), "Verify balance amount is preceded with $ sign", "True", "True", true);
							}	
						}
						
						
						if(UI.WaitForObject(oR_BillAndUsage.btnMakePaymentInBillingPage, 5, lDriver)) {
							
							Report.ValidationPoint(testContext.getName(), "Verify Make A Payment button is displayed on B&P page", "True", "True", true);
						} else {
							
							Report.ValidationPoint(testContext.getName(), "Verify Make A Payment button is displayed on B&P page", "True", "False", true);
						}
						
						//Verifying presence of User account details
						if(UI.WaitForObject(oR_BillAndUsage.txtUserDetails, 5, lDriver)) {
							String sText_sld = oR_BillAndUsage.txtUserDetails.getText();
							if(sText_sld.contains("U-verse") || sText_sld.contains("Wireless") || sText_sld.contains("BAN") || sText_sld.contains("CTN")) {
							Report.ValidationPoint(testContext.getName(), "Verify User account details is displayed", sText_sld, sText_sld, true);
							}
						} else {
							
							Report.ValidationPoint(testContext.getName(), "Verify User account details is displayed", "True", "False", true);
						}
						
						/**************** Group 2 Elements  - Bill, Usage , History, Reports tabs **********************/
						
						
						//Verifying presence of Bill Tab
						if(UI.WaitForObject(oR_BillAndUsage.lnkBillTab, 5, lDriver)) {
							
							Report.ValidationPoint(testContext.getName(), "Verify Bill tab is displayed on B&P page", "True", "True", true);
						} else {
							
							Report.ValidationPoint(testContext.getName(), "Verify Bill tab is displayed on B&P page", "True", "False", true);
						}
						
						//Verifying presence of Usage Tab
						if(UI.WaitForObject(oR_BillAndUsage.lnkUsage, 5, lDriver)) {
							
							Report.ValidationPoint(testContext.getName(), "Verify Usage tab is displayed on B&P page", "True", "True", true);
						} else {
							
							Report.ValidationPoint(testContext.getName(), "Verify Usage tab is displayed on B&P page", "True", "False", true);
						}
						
						//Verifying presence of History Tab
						if(UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab, 5, lDriver)) {
							
							Report.ValidationPoint(testContext.getName(), "Verify History tab is displayed on B&P page", "True", "True", true);
						} else {
							
							Report.ValidationPoint(testContext.getName(), "Verify History tab is displayed on B&P page", "True", "False", true);
						}
						
						//Verifying presence of Reports Tab
						if(UI.WaitForObject(oR_BillAndUsage.lnkReportTab, 5, lDriver)) {
							
							Report.ValidationPoint(testContext.getName(), "Verify Report tab is displayed on B&P page", "True", "True", true);
						} else {
							
							Report.ValidationPoint(testContext.getName(), "Verify Report tab is displayed on B&P page", "True", "False", true);
						}
						
						//Validate Account Details text
						Boolean bAccDet_sld = UI.WaitForObject(oR_BillAndUsage.txtAccountDetails,10,lDriver);
						Report.TestPoint(testContext.getName(), "Validate Account Details text", "True", String.valueOf(bAccDet_sld), true);
						
						//Validate Print link
						Boolean bPrintLnk_sld = UI.WaitForObject(oR_BillAndUsage.lnkPrintBill,2,lDriver);
						Report.TestPoint(testContext.getName(), "Validate Print link", "True", String.valueOf(bPrintLnk_sld), true);
						
						//Verify current bill details
						Boolean bCurrent_sld = UI.WaitForObject(oR_BillAndUsage.txtCurrentBill, 2, lDriver );
						Report.ValidationPoint(testContext.getName(), "Validate bill for", "True", String.valueOf(bCurrent_sld), true);
						
						//Validate Collapse/Expand functionality is Not present
						Boolean bCollapse_sld = UI.VerifyElementNotPresent(oR_BillAndUsage.lnkCollapseDummy, "Collapse/Expand functionality");
						Report.ValidationPoint(testContext.getName(), "Validate Collapse/Expand functionality is present", "True", String.valueOf(bCollapse_sld), true);
						
						/************************* Group 3 Elements - Bill Alerts, Compare bills ****************************/
						
						//Validate bill alerts section
						Boolean bAlert_sld = UI.WaitForObject(oR_BillAndUsage.txtBillAlerts,20,lDriver);
						Report.TestPoint(testContext.getName(), "Validate bill alerts section" , "true", String.valueOf(bAlert_sld),  true);
						Report.ValidationPoint(testContext.getName(), "Validate bill alerts section is present", "True", String.valueOf(bAlert_sld), true);
						
						//Validate compare bills
						if(UI.WaitForObject(oR_BillAndUsage.txtCompareBills, 1,lDriver))
						{
							Report.ValidationPoint(testContext.getName(), "Verify compare bills is present", "true", "true", true);
						}
						
						/************************* Group 4 Elements - Manage account,Change billing options & Get Help ****************************/
						
						//Verify secondary link rails section contains Manage Account 
						boolean bManageAccount_sld = UI.WaitForObject(oR_BillAndUsage.txtManageAccountNew, UI.iObjTimeOut,lDriver);
						Report.ValidationPoint(testContext.getName(), "Validate that 'Manage Account' section is displayed in Sec link rails section", "true", String.valueOf(bManageAccount_sld), true);
						
						//Verify secondary link rails section contains Change Billing options
						boolean bChangeBilling_sld = UI.WaitForObject(oR_BillAndUsage.txtManageBillingAndPaymentOptions, UI.iObjTimeOut,lDriver);
						Report.ValidationPoint(testContext.getName(), "Validate that 'Change Billing Options' section is displayed in Sec link rails", "true", String.valueOf(bChangeBilling_sld), true);
						
						//Verify secondary link rails section contains Get Help
						boolean bGetHelp_sld = UI.WaitForObject(oR_BillAndUsage.txtGetHelp, UI.iObjTimeOut,lDriver);
						Report.ValidationPoint(testContext.getName(), "Validate that 'Get Help' section is displayed in Sec link rails", "true", String.valueOf(bGetHelp_sld), true);
						
						break;
				
						
			case "Wireless":
						       
				String sBalanceDue_wls = null;
				
				Report.OperationPoint(testContext.getName(), "Selecting Make A Payment form Tertiary Navigation");
				boolean bBillingAndPaymentlnk_wls;
				bBillingAndPaymentlnk_wls = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut,lDriver);
				
				Report.TestPoint(testContext.getName(), "Secondary Link Billing and Payments", "true", String.valueOf(bBillingAndPaymentlnk_wls).toLowerCase(), true);
				
				new Actions(lDriver).moveToElement(oR_AccountOverview.lnkBillingUsagePaymentsSecNav).moveToElement(oR_AccountOverview.lnkMakeAPaymentTertNav).click().build().perform();
				Thread.sleep(2000);
				boolean bMAPPage_wls = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 60,lDriver);
				Report.ValidationPoint(testContext.getName(), "Make A Payment Page", "true", String.valueOf(bMAPPage_wls).toLowerCase(), true);
				Thread.sleep(1000);
				
//				boolean bClickonBnPLink_wls = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkMakeAPaymentTertNav);
//				Thread.sleep(2000);
//				if(bClickonBnPLink_wls==true)
//				{
//					boolean bMAPPage_wls = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 60);
//					Report.ValidationPoint(testContext.getName(), "Make A Payment Page", "true", String.valueOf(bMAPPage_wls).toLowerCase(), true);
//					Thread.sleep(1000);
//				}
					   				    
				// Verify Flexible Payment Options link is displayed
				boolean bFlexPaymentOptions_wls = UI.WaitForObject(oR_MakeAPayment.lnkFlexiblePaymentOptions, UI.iObjTimeOut,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Flexible Payment Options link with Icon is displayed on Make A Payment page", "True",String.valueOf(bFlexPaymentOptions_wls), true);
				
				 						
				/*---------- Group 1:- Choose payment method (1. Payment entry) -------------*/
						
				List <WebElement> CheckBoxes_wls = lDriver.findElements(By.xpath("//input[@title = 'checkbox']"));
				if(CheckBoxes_wls.size() > 1) {
					
					//Verify that the accounts are listed with their nicknames (if present) with a check box to their left. By default the checkbox is unchecked.
					boolean bSelectAccToPay_wls = UI.WaitForObject(oR_MakeAPayment.txtSelectAccountToPay, UI.iObjTimeOut,lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify Select Account to Pay text is displayed on Make A Payment page", "True",String.valueOf(bSelectAccToPay_wls), true);
					
					for(int i=1; i<=CheckBoxes_wls.size(); i++)	{
						
						
						
						/* Verify BAN - Account Number is displayed*/
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[1]")), 5,lDriver)) {
						    String sText_wls= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[1]")).getText();
						    if(sText_wls.contains("BAN")) {
								  Report.ValidationPoint(testContext.getName(), "Verify Account details is displayed ", "True", "True", true);
							}
							else {
							  	  Report.ValidationPoint(testContext.getName(), "Verify Account details", "Account details should be displayed", "'Account details are not displayed", true);
							}
						 }
						
						Thread.sleep(1000);
						
						/* Verify Balance Amount is displayed*/
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[2]")), 5,lDriver)) {
						    String sText_wls= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[2]")).getText();
						    if(sText_wls.contains("Balance")) {
								  Report.ValidationPoint(testContext.getName(), "Verify Account details is displayed ", sText_wls, sText_wls, true);
							  }
							  else {
							  	  Report.ValidationPoint(testContext.getName(), "Verify Account details", "Account details should be displayed", "'Account details are not displayed", true);
							  }
						 }
						
						/* Verify Due Date is displayed*/
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[3]")), 5,lDriver)) {
						    String sText_wls= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[3]")).getText();
						    if(sText_wls.contains("Due Date")) {
								  Report.ValidationPoint(testContext.getName(), "Verify Due Date is displayed ", sText_wls, sText_wls, false);
							  }
							  else {
							  	  Report.ValidationPoint(testContext.getName(), "Verify Due Date displayed","Due Date should be displayed", "Due date not displayed", true);
							  }
						 }
						
						/* Verify Past Due , Please pay immediately is displayed*/
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[4]")), 5,lDriver)) {
						    String sText_wls= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[4]")).getText();
						    if(sText_wls.contains("past due")) {
								  Report.ValidationPoint(testContext.getName(), "Verify past Due is displayed ", sText_wls, sText_wls, false);
							  }
							  else {
							  	  Report.ValidationPoint(testContext.getName(), "Verify past Due displayed","Past Due Amount be displayed", "Past Due Amount is not displayed", true);
							  }
						 }
						Thread.sleep(1000);
						
						break;
						
					}	
				} else if(CheckBoxes_wls.size() == 0) {
				
					/* Verify BAN - Account Number is displayed*/
					if(UI.WaitForObject(lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[1]")), 5,lDriver)) {
					    String sText_wls= lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[1]")).getText();
					    if(sText_wls.contains("Account")) {
							  Report.ValidationPoint(testContext.getName(), "Verify Account details is displayed ", "True", "True", true);
						}
						else {
						  	  Report.ValidationPoint(testContext.getName(), "Verify Account details", "Account details should be displayed", "'Account details are not displayed", true);
						}
					 }
					
					Thread.sleep(1000);
					
					/* Verify Balance Amount is displayed*/
					if(UI.WaitForObject(lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[2]")), 5,lDriver)) {
					    String sText_wls= lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[2]")).getText();
					    if(sText_wls.contains("Balance")) {
					    	
					    	  sBalanceDue_wls = sText_wls;
							  Report.ValidationPoint(testContext.getName(), "Verify Balance Amount is displayed ", sText_wls, sText_wls, true);
						  }
						  else {
						  	  Report.ValidationPoint(testContext.getName(), "Verify Balance Amount is displayed", "Balance Amount should be displayed", "Balance Amount is not displayed", true);
						  }
					 }
					
					/* Verify Due Date is displayed*/
					if(UI.WaitForObject(lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[3]")), 5,lDriver)) {
					    String sText_wls= lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[3]")).getText();
					    if(sText_wls.contains("Due Date:")) {
							  Report.ValidationPoint(testContext.getName(), "Verify Due Date is displayed ", sText_wls, sText_wls, false);
						  }
						  else {
						  	  Report.ValidationPoint(testContext.getName(), "Verify Due Date displayed","Due Date should be displayed", "Due date not displayed", true);
						  }
					 }
					
					/* Verify Past Due , Please pay immediately is displayed*/
					if (!sBalanceDue_wls.contains("$0.00")) {
						/* Verify Past Due , Please pay immediately is displayed*/
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[4]")), 5,lDriver)) {
						    String sText_wls= lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[4]")).getText();
						    if(sText_wls.contains("past due")) {
								  Report.ValidationPoint(testContext.getName(), "Verify past Due, Please pay immediately tage is displayed ", sText_wls, sText_wls, false);
							}
						    else {
							  	  Report.ValidationPoint(testContext.getName(), "Verify past Due, Please pay immediately tage is displayed displayed","Past Due Amount be displayed", "Past Due Amount is not displayed", true);
							}
						 }
				      }
						Thread.sleep(1000);
					
				} else {
					
					Report.ValidationPoint(testContext.getName(), "Accounts are listed with check box to their left", "true", "false", true);
				}
	
				
				Thread.sleep(1000);
				
				//List <WebElement> Accounts_uv= lDriver.findElements(By.xpath("//p[@class= 'MarLeft0 botMar0  font16 bt_left']"));
				List <WebElement> Accounts_wls= lDriver.findElements(By.xpath("//div[@class= 'map-info MarLeft28 MarTopNeg5']"));
				int iNumberOfAcc_wls = Accounts_wls.size();
				Report.ValidationPoint(testContext.getName(), iNumberOfAcc_wls + " Accounts are listed for payment", "true", "true", true);


							       
				/*---------- Group 2:- Choose payment method (1. Payment entry) -------------*/
				
				Thread.sleep(1000);
				
				Boolean bPaymentMethod_wls = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethodfrm,1,lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Payment Method text is displayed", "True", String.valueOf(bPaymentMethod_wls), true);
				
				// Payment Method edit box is displayed
				if (UI.CheckExist(oR_MakeAPayment.lstPaymentMethod).equalsIgnoreCase("True"))
				{
					Thread.sleep(2000);
					Report.ValidationPoint(testContext.getName(), "Verify Payment Method list is displayed", "True", "True", true);
				}	
				
				//Verify today's date selection
				if (UI.CheckExist(oR_MakeAPayment.txtPaymentDateLabel).equalsIgnoreCase("True")) {
					
					Report.ValidationPoint(testContext.getName(), "Verify Payment Date label is displayed", "true", "true", true);
				}		
				
				//Verify calender is displayed for today's date selection
				if(UI.WaitForObject(oR_MakeAPayment.imgCalender, 20,lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Verify Image calender is displayed", "True", "True", true);
					Thread.sleep(1000);
				}	
						
				
				
				// Payment Amount edit box is displayed
				if(UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1, 5,lDriver)) {

					//Verify payment amount in Payment amount edit field
					String sPaymentAmount_wls=oR_MakeAPayment.edtPaymentAmount1.getText();

					Report.ValidationPoint(testContext.getName(), "Verify Payment Amount is displayed", String.valueOf(sPaymentAmount_wls), String.valueOf(sPaymentAmount_wls), false);
					Thread.sleep(1000);
				}
				
				
				//Verify that for all accounts there is an option to enroll in autopay with a check box to the left of it. Verify the presence of a help icon next to the check box. Note: not displayed if the account is already actively enrolled in autopay. Will not be displayed if autopay is pending
				//int iEnrollInAutopayChkBox_lwl = lDriver.findElements(By.xpath("//input[contains(@id,'enrollInAutopayChkBox')]")).size();
				int iEnrollInAutopayChkBox_wls = lDriver.findElements(By.xpath("//div[contains(@id,'enrollInAutopayChkBox')]")).size();
				int iHelpIcon_wls = lDriver.findElements(By.xpath("//a[contains(@id,'enrollinAutoPayHelp')]")).size();
				if(iEnrollInAutopayChkBox_wls ==  iHelpIcon_wls)
					Report.ValidationPoint(testContext.getName(), "For all accounts there is an option to enroll in autopay with a check box to the left and help icon next to the check box", "true", "true", true);
				else
					Report.ValidationPoint(testContext.getName(), "For all accounts there is an option to enroll in autopay with a check box to the left and help icon next to the check box", "true", "false", true);
				
				//Verifying number of Split this payment links present
				List <WebElement> SplitThisPayment_wls =  lDriver.findElements(By.xpath("//a[contains(text(),'Split this payment')]"));
				System.out.println(SplitThisPayment_wls.size()+" size");
			 	List <WebElement> HelpIcon_wls = lDriver.findElements(By.xpath("//div[contains(@id,'paymentItem_AddAnchor')]"));
				if(SplitThisPayment_wls.size()>=1 && HelpIcon_wls.size() == SplitThisPayment_wls.size())
				{
					Report.ValidationPoint(testContext.getName(), "Verify Split this payment link exists", "true","true", true);
					
					for(int i=0; i<=SplitThisPayment_wls.size()-1; i++)	
					{
						int m = i+1;
						Report.ValidationPoint(testContext.getName(), "Split Payment for Account " +m, "Present", "Present", true);
						//SplitThisPayment_uv.get(i).click();
						//1:Another row is listed which allows the user to make 2nd of the 2 part arrangement with the following fields:
						//2: Enroll in autopay checkbox gets disabled.
			
						if(lDriver.findElement(By.xpath("//div/a[@id='acct_"+i+"_rmvpmtlink']")).isDisplayed());
						{
							Report.ValidationPoint(testContext.getName(), "Another row is listed, Enroll In Autopay is disabled and delete this row CTA present", "true","true", true);
							
							//Click on 'Remove this Payment' link. Note: Execute this step if the previous step was executed.
							//lDriver.findElement(By.xpath("//div/a[@id='acct_"+i+"_rmvpmtlink']")).click();
							
							//3: If the customer selects this link the split payment will be removed from the account and the page will be refreshed  the Amount to Pay and Pay On Date fields will revert back to the values shown when the customer landed on the page.
				
							Boolean bSplitThisPayment_lwl = UI.WaitForObject(SplitThisPayment_wls.get(i), UI.iObjTimeOut,lDriver);
							Report.ValidationPoint(testContext.getName(), "Split Payment must be removed", "true", String.valueOf(bSplitThisPayment_lwl), true);
							Thread.sleep(2000);
						}
					}	
				
				}
				else {
					Report.ValidationPoint(testContext.getName(), "Split Payment", "Present", "Absent", true);
				}
							
				
				
				/*---------- Group 3:- Cancel and Continue buttons -------------*/
				
				//Verify Continue link
				if(UI.WaitForObject(oR_MakeAPayment.lnkContinue, 10, lDriver))
				{
					
					Report.ValidationPoint(testContext.getName(), "Verify the link 'Next' displayed  ", "true", "true", true);	
				} else {
					
					Report.ValidationPoint(testContext.getName(), "Verify the link 'Next' displayed  ", "true", "false", true);
				}
				
				//Verify Cancel link
				 Boolean bCancel_wls = UI.WaitForObject(oR_MakeAPayment.lnkCancel,5,lDriver);
				 Report.ValidationPoint(testContext.getName(), "Verify Cancel link displayed", "true",String.valueOf(bCancel_wls), true);
	           					 
		 
				 break; 
				
					
			case "Wireline":
				
					
				if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,20,lDriver).equals(true)){
					//UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingAndPaymentSecNav, oR_AccountOverview.lnkBillDetailsTertNav);
					Report.TestPoint(testContext.getName(), "Validate Billing & Payment link is present in secondary nav", "True", "True", true);
					//validate Bill and Usage page is displayed
					oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
				}
				
				/**************** Group 1 Elements **********************/
				
				//Verify Billing & Usage page is displayed
				boolean bBillingPage_wln = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
				Report.TestPoint(testContext.getName(), "Validate that Billing And Usage page is displayed", "true", String.valueOf(bBillingPage_wln), true);

				boolean bTotalAmtDueby_wln = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 5, lDriver);					
				Report.TestPoint(testContext.getName(), "Verify Total Amount due By is displayed", "true", String.valueOf(bTotalAmtDueby_wln), true);
		 
				List <WebElement> lstBalAmt_wln = lDriver.findElements(By.xpath("//span[contains(@class,'Orange') or contains(@class,'Green') or contains(@class,'Red') and contains(text(),'$')]"));
				if(lstBalAmt_wln.size() > 0){
					for(int i=0; i<lstBalAmt_wln.size(); i++) {
						Report.ValidationPoint(testContext.getName(), "Verify balance amount is displayed on Overview page", "True", "True", true);
						Report.ValidationPoint(testContext.getName(), "Verify balance amount is preceded with $ sign", "True", "True", true);
					}	
				}
				
				
				if(UI.WaitForObject(oR_BillAndUsage.btnMakePaymentInBillingPage, 5, lDriver)) {
					
					Report.ValidationPoint(testContext.getName(), "Verify Make A Payment button is displayed on B&P page", "True", "True", true);
				} else {
					
					Report.ValidationPoint(testContext.getName(), "Verify Make A Payment button is displayed on B&P page", "True", "False", true);
				}
				
				//Verifying presence of User account details
				if(UI.WaitForObject(oR_BillAndUsage.txtUserDetails, 5, lDriver)) {
					String sText_wln = oR_BillAndUsage.txtUserDetails.getText();
					if(sText_wln.contains("U-verse") || sText_wln.contains("Wireless") || sText_wln.contains("Wireline")) {
					Report.ValidationPoint(testContext.getName(), "Verify User account details is displayed", sText_wln, sText_wln, true);
					}
				} else {
					
					Report.ValidationPoint(testContext.getName(), "Verify User account details is displayed", "True", "False", true);
				}
				
				/**************** Group 2 Elements  - Bill, Usage , History, Reports tabs **********************/
				
				
				//Verifying presence of Bill Tab
				if(UI.WaitForObject(oR_BillAndUsage.lnkBillTab, 5, lDriver)) {
					
					Report.ValidationPoint(testContext.getName(), "Verify Bill tab is displayed on B&P page", "True", "True", true);
				} else {
					
					Report.ValidationPoint(testContext.getName(), "Verify Bill tab is displayed on B&P page", "True", "False", true);
				}
				
				//Verifying presence of Usage Tab
				if(UI.WaitForObject(oR_BillAndUsage.lnkUsage, 5, lDriver)) {
					
					Report.ValidationPoint(testContext.getName(), "Verify Usage tab is displayed on B&P page", "True", "True", true);
				} else {
					
					Report.ValidationPoint(testContext.getName(), "Verify Usage tab is displayed on B&P page", "True", "False", true);
				}
				
				//Verifying presence of History Tab
				if(UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab, 5, lDriver)) {
					
					Report.ValidationPoint(testContext.getName(), "Verify History tab is displayed on B&P page", "True", "True", true);
				} else {
					
					Report.ValidationPoint(testContext.getName(), "Verify History tab is displayed on B&P page", "True", "False", true);
				}
				
				//Verifying presence of Reports Tab
				if(UI.WaitForObject(oR_BillAndUsage.lnkReportTab, 5, lDriver)) {
					
					Report.ValidationPoint(testContext.getName(), "Verify Report tab is displayed on B&P page", "True", "True", true);
				} else {
					
					Report.ValidationPoint(testContext.getName(), "Verify Report tab is displayed on B&P page", "True", "False", true);
				}
				
				//Validate Account Details text
				Boolean bAccDet_wln = UI.WaitForObject(oR_BillAndUsage.txtAccountDetails,10,lDriver);
				Report.TestPoint(testContext.getName(), "Validate Account Details text", "True", String.valueOf(bAccDet_wln), true);
				
				//Validate Print link
				Boolean bPrintLnk_wln = UI.WaitForObject(oR_BillAndUsage.lnkPrintBill,2,lDriver);
				Report.TestPoint(testContext.getName(), "Validate Print link", "True", String.valueOf(bPrintLnk_wln), true);
				
				//Verify current bill details
				Boolean bCurrent_wln = UI.WaitForObject(oR_BillAndUsage.txtCurrentBill, 2, lDriver );
				Report.ValidationPoint(testContext.getName(), "Validate bill for", "True", String.valueOf(bCurrent_wln), true);
				
				//Validate Collapse/Expand functionality is Not present
				Boolean bCollapse_wln = UI.VerifyElementNotPresent(oR_BillAndUsage.lnkCollapseDummy, "Collapse/Expand functionality");
				Report.ValidationPoint(testContext.getName(), "Validate Collapse/Expand functionality is present", "True", String.valueOf(bCollapse_wln), true);
				
				/************************* Group 3 Elements - Bill Alerts, Compare bills ****************************/
				
				//Validate bill alerts section
				Boolean bAlert_wln = UI.WaitForObject(oR_BillAndUsage.txtBillAlerts,20,lDriver);
				Report.TestPoint(testContext.getName(), "Validate bill alerts section" , "true", String.valueOf(bAlert_wln),  true);
				Report.ValidationPoint(testContext.getName(), "Validate bill alerts section is present", "True", String.valueOf(bAlert_wln), true);
				
				//Validate compare bills
				if(UI.WaitForObject(oR_BillAndUsage.txtCompareBills, 1,lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Verify compare bills is present", "true", "true", true);
				}
				
				/************************* Group 4 Elements - Manage account,Change billing options & Get Help ****************************/
				
				//Verify secondary link rails section contains Manage Account 
				boolean bManageAccount_wln = UI.WaitForObject(oR_BillAndUsage.txtManageAccountNew, UI.iObjTimeOut,lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that 'Manage Account' section is displayed in Sec link rails section", "true", String.valueOf(bManageAccount_wln), true);
				
				//Verify secondary link rails section contains Change Billing options
				boolean bChangeBilling_wln = UI.WaitForObject(oR_BillAndUsage.txtManageBillingAndPaymentOptions, UI.iObjTimeOut,lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that 'Change Billing Options' section is displayed in Sec link rails", "true", String.valueOf(bChangeBilling_wln), true);
				
				//Verify secondary link rails section contains Get Help
				boolean bGetHelp_wln = UI.WaitForObject(oR_BillAndUsage.txtGetHelp, UI.iObjTimeOut,lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that 'Get Help' section is displayed in Sec link rails", "true", String.valueOf(bGetHelp_wln), true);
	       
				break;
				              
						        
				    
			case "Legacy Wireline":
					       
				String sBalanceDue_lwl = null;
				
				Report.OperationPoint(testContext.getName(), "Selecting Make A Payment form Tertiary Navigation");
				boolean bBillingAndPaymentlnk_lwl;
				bBillingAndPaymentlnk_lwl = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut,lDriver);
				
				Report.TestPoint(testContext.getName(), "Secondary Link Billing and Payments", "true", String.valueOf(bBillingAndPaymentlnk_lwl).toLowerCase(), true);
				
				new Actions(lDriver).moveToElement(oR_AccountOverview.lnkBillingUsagePaymentsSecNav).moveToElement(oR_AccountOverview.lnkMakeAPaymentTertNav).click().build().perform();
				Thread.sleep(2000);
				boolean bMAPPage_lwl = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 60,lDriver);
				Report.ValidationPoint(testContext.getName(), "Make A Payment Page", "true", String.valueOf(bMAPPage_lwl).toLowerCase(), true);
				Thread.sleep(1000);
				
//				boolean bClickonBnPLink_lwl = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkMakeAPaymentTertNav);
//				Thread.sleep(2000);
//				if(bClickonBnPLink_lwl==true)
//				{
//					boolean bMAPPage_lwl = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 60);
//					Report.ValidationPoint(testContext.getName(), "Make A Payment Page", "true", String.valueOf(bMAPPage_lwl).toLowerCase(), true);
//					Thread.sleep(1000);
//				}
					   				    
				// Verify Flexible Payment Options link is displayed
				boolean bFlexPaymentOptions_lwl = UI.WaitForObject(oR_MakeAPayment.lnkFlexiblePaymentOptions, UI.iObjTimeOut,lDriver).equals(true);
				Report.ValidationPoint(testContext.getName(), "Verify Flexible Payment Options link with Icon is displayed on Make A Payment page", "True",String.valueOf(bFlexPaymentOptions_lwl), true);
				
				 						
				/*---------- Group 1:- Choose payment method (1. Payment entry) -------------*/
						
				List <WebElement> CheckBoxes_lwl = lDriver.findElements(By.xpath("//input[@title = 'checkbox']"));
				if(CheckBoxes_lwl.size() > 1) {
					
					//Verify that the accounts are listed with their nicknames (if present) with a check box to their left. By default the checkbox is unchecked.
					boolean bSelectAccToPay_lwl = UI.WaitForObject(oR_MakeAPayment.txtSelectAccountToPay, UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Verify Select Account to Pay text is displayed on Make A Payment page", "True",String.valueOf(bSelectAccToPay_lwl), true);
					
					for(int i=1; i<=CheckBoxes_lwl.size(); i++)	{
						
						
						
						/* Verify BAN - Account Number is displayed*/
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[1]")), 5,lDriver)) {
						    String sText_lwl= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[1]")).getText();
						    if(sText_lwl.contains("BAN")) {
								  Report.ValidationPoint(testContext.getName(), "Verify Account details is displayed ", "True", "True", true);
							}
							else {
							  	  Report.ValidationPoint(testContext.getName(), "Verify Account details", "Account details should be displayed", "'Account details are not displayed", true);
							}
						 }
						
						Thread.sleep(1000);
						
						/* Verify Balance Amount is displayed*/
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[2]")), 5,lDriver)) {
						    String sText_lwl= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[2]")).getText();
						    if(sText_lwl.contains("Balance")) {
								  Report.ValidationPoint(testContext.getName(), "Verify Account details is displayed ", sText_lwl, sText_lwl, true);
							  }
							  else {
							  	  Report.ValidationPoint(testContext.getName(), "Verify Account details", "Account details should be displayed", "'Account details are not displayed", true);
							  }
						 }
						
						/* Verify Due Date is displayed*/
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[3]")), 5,lDriver)) {
						    String sText_lwl= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[3]")).getText();
						    if(sText_lwl.contains("Due Date")) {
								  Report.ValidationPoint(testContext.getName(), "Verify Due Date is displayed ", sText_lwl, sText_lwl, false);
							  }
							  else {
							  	  Report.ValidationPoint(testContext.getName(), "Verify Due Date displayed","Due Date should be displayed", "Due date not displayed", true);
							  }
						 }
						
						/* Verify Past Due , Please pay immediately is displayed*/
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[4]")), 5,lDriver)) {
						    String sText_lwl= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[4]")).getText();
						    if(sText_lwl.contains("past due")) {
								  Report.ValidationPoint(testContext.getName(), "Verify past Due is displayed ", sText_lwl, sText_lwl, false);
							  }
							  else {
							  	  Report.ValidationPoint(testContext.getName(), "Verify past Due displayed","Past Due Amount be displayed", "Past Due Amount is not displayed", true);
							  }
						 }
						Thread.sleep(1000);
						
						break;
						
					}	
				} else if(CheckBoxes_lwl.size() == 0) {
				
					/* Verify BAN - Account Number is displayed*/
					if(UI.WaitForObject(lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[1]")), 5,lDriver)) {
					    String sText_lwl= lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[1]")).getText();
					    if(sText_lwl.contains("Account")) {
							  Report.ValidationPoint(testContext.getName(), "Verify Account details is displayed ", "True", "True", true);
						}
						else {
						  	  Report.ValidationPoint(testContext.getName(), "Verify Account details", "Account details should be displayed", "'Account details are not displayed", true);
						}
					 }
					
					Thread.sleep(1000);
					
					/* Verify Balance Amount is displayed*/
					if(UI.WaitForObject(lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[2]")), 5,lDriver)) {
					    String sText_lwl= lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[2]")).getText();
					    if(sText_lwl.contains("Balance")) {
					    	
					    	  sBalanceDue_lwl = sText_lwl;
							  Report.ValidationPoint(testContext.getName(), "Verify Balance Amount is displayed ", sText_lwl, sText_lwl, true);
						  }
						  else {
						  	  Report.ValidationPoint(testContext.getName(), "Verify Balance Amount is displayed", "Balance Amount should be displayed", "Balance Amount is not displayed", true);
						  }
					 }
					
					/* Verify Due Date is displayed*/
					if(UI.WaitForObject(lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[3]")), 5,lDriver)) {
					    String sText_lwl= lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[3]")).getText();
					    if(sText_lwl.contains("Due Date:")) {
							  Report.ValidationPoint(testContext.getName(), "Verify Due Date is displayed ", sText_lwl, sText_lwl, false);
						  }
						  else {
						  	  Report.ValidationPoint(testContext.getName(), "Verify Due Date displayed","Due Date should be displayed", "Due date not displayed", true);
						  }
					 }
					
					
					if (!sBalanceDue_lwl.contains("$0.00")) {
						/* Verify Past Due , Please pay immediately is displayed*/
						if(UI.WaitForObject(lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[4]")), 5,lDriver)) {
						    String sText_lwl= lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[4]")).getText();
						    if(sText_lwl.contains("past due")) {
								  Report.ValidationPoint(testContext.getName(), "Verify past Due, Please pay immediately tage is displayed ", sText_lwl, sText_lwl, false);
							}
						    else {
							  	  Report.ValidationPoint(testContext.getName(), "Verify past Due, Please pay immediately tage is displayed displayed","Past Due Amount be displayed", "Past Due Amount is not displayed", true);
							}
						 }
				      }
						Thread.sleep(1000);
					
				} else {
					
					Report.ValidationPoint(testContext.getName(), "Accounts are listed with check box to their left", "true", "false", true);
				}
	
				
				Thread.sleep(1000);
				
				//List <WebElement> Accounts_uv= lDriver.findElements(By.xpath("//p[@class= 'MarLeft0 botMar0  font16 bt_left']"));
				List <WebElement> Accounts_lwl= lDriver.findElements(By.xpath("//div[@class= 'map-info MarLeft28 MarTopNeg5']"));
				int iNumberOfAcc_lwl = Accounts_lwl.size();
				Report.ValidationPoint(testContext.getName(), iNumberOfAcc_lwl + " Accounts are listed for payment", "true", "true", true);


							       
				/*---------- Group 2:- Choose payment method (1. Payment entry) -------------*/
				
				Thread.sleep(1000);
				
				Boolean bPaymentMethod_lwl = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethodfrm,1,lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Payment Method text is displayed", "True", String.valueOf(bPaymentMethod_lwl), true);
				
				// Payment Method edit box is displayed
				if (UI.CheckExist(oR_MakeAPayment.lstPaymentMethod).equalsIgnoreCase("True"))
				{
					Thread.sleep(2000);
					Report.ValidationPoint(testContext.getName(), "Verify Payment Method list is displayed", "True", "True", true);
				}	
				
				//Verify today's date selection
				if (UI.CheckExist(oR_MakeAPayment.txtPaymentDateLabel).equalsIgnoreCase("True")) {
					
					Report.ValidationPoint(testContext.getName(), "Verify Payment Date label is displayed", "true", "true", true);
				}		
				
				//Verify calender is displayed for today's date selection
				if(UI.WaitForObject(oR_MakeAPayment.imgCalender, 20,lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Verify Image calender is displayed", "True", "True", true);
					Thread.sleep(1000);
				}	
				
				
				// Payment Amount edit box is displayed
				if(UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1, 5,lDriver)) {

					//Verify payment amount in Payment amount edit field
					String sPaymentAmount_lwl=oR_MakeAPayment.edtPaymentAmount1.getText();

					Report.ValidationPoint(testContext.getName(), "Verify Payment Amount is displayed", String.valueOf(sPaymentAmount_lwl), String.valueOf(sPaymentAmount_lwl), false);
					Thread.sleep(1000);
				}
				
				
				//Verify that for all accounts there is an option to enroll in autopay with a check box to the left of it. Verify the presence of a help icon next to the check box. Note: not displayed if the account is already actively enrolled in autopay. Will not be displayed if autopay is pending
				//int iEnrollInAutopayChkBox_lwl = lDriver.findElements(By.xpath("//input[contains(@id,'enrollInAutopayChkBox')]")).size();
				int iEnrollInAutopayChkBox_lwl = lDriver.findElements(By.xpath("//div[contains(@id,'enrollInAutopayChkBox')]")).size();
				int iHelpIcon_lwl = lDriver.findElements(By.xpath("//a[contains(@id,'enrollinAutoPayHelp')]")).size();
				if(iEnrollInAutopayChkBox_lwl ==  iHelpIcon_lwl)
					Report.ValidationPoint(testContext.getName(), "For all accounts there is an option to enroll in autopay with a check box to the left and help icon next to the check box", "true", "true", true);
				else
					Report.ValidationPoint(testContext.getName(), "For all accounts there is an option to enroll in autopay with a check box to the left and help icon next to the check box", "true", "false", true);
				
				//Verifying number of Split this payment links present
				List <WebElement> SplitThisPayment_lwl =  lDriver.findElements(By.xpath("//a[contains(text(),'Split this payment')]"));
				System.out.println(SplitThisPayment_lwl.size()+" size");
			 	List <WebElement> HelpIcon_lwl = lDriver.findElements(By.xpath("//div[contains(@id,'paymentItem_AddAnchor')]"));
				if(SplitThisPayment_lwl.size()>=1 && HelpIcon_lwl.size() == SplitThisPayment_lwl.size())
				{
					Report.ValidationPoint(testContext.getName(), "Verify Split this payment link exists", "true","true", true);
					
					for(int i=0; i<=SplitThisPayment_lwl.size()-1; i++)	
					{
						int m = i+1;
						Report.ValidationPoint(testContext.getName(), "Split Payment for Account " +m, "Present", "Present", true);
						//SplitThisPayment_uv.get(i).click();
						//1:Another row is listed which allows the user to make 2nd of the 2 part arrangement with the following fields:
						//2: Enroll in autopay checkbox gets disabled.
			
						if(lDriver.findElement(By.xpath("//div/a[@id='acct_"+i+"_rmvpmtlink']")).isDisplayed());
						{
							Report.ValidationPoint(testContext.getName(), "Another row is listed, Enroll In Autopay is disabled and delete this row CTA present", "true","true", true);
							
							//Click on 'Remove this Payment' link. Note: Execute this step if the previous step was executed.
							//lDriver.findElement(By.xpath("//div/a[@id='acct_"+i+"_rmvpmtlink']")).click();
							
							//3: If the customer selects this link the split payment will be removed from the account and the page will be refreshed  the Amount to Pay and Pay On Date fields will revert back to the values shown when the customer landed on the page.
				
							Boolean bSplitThisPayment_lwl = UI.WaitForObject(SplitThisPayment_lwl.get(i), UI.iObjTimeOut,lDriver);
							Report.ValidationPoint(testContext.getName(), "Split Payment must be removed", "true", String.valueOf(bSplitThisPayment_lwl), true);
							Thread.sleep(2000);
						}
					}	
				
				}
				else {
					Report.ValidationPoint(testContext.getName(), "Split Payment", "Present", "Absent", true);
				}
							
				
				
				/*---------- Group 3:- Cancel and Continue buttons -------------*/
				
				//Verify Continue link
				if(UI.WaitForObject(oR_MakeAPayment.lnkContinue, 10, lDriver))
				{
					
					Report.ValidationPoint(testContext.getName(), "Verify the link 'Next' displayed  ", "true", "true", true);	
				} else {
					
					Report.ValidationPoint(testContext.getName(), "Verify the link 'Next' displayed  ", "true", "false", true);
				}
				
				//Verify Cancel link
				 Boolean bCancel_lwl = UI.WaitForObject(oR_MakeAPayment.lnkCancel,5,lDriver);
				 Report.ValidationPoint(testContext.getName(), "Verify Cancel link displayed", "true",String.valueOf(bCancel_lwl), true);
	           					 
		 
				 break; 
				                 
				                 
			case "uverse":   
						
						
						Report.OperationPoint(testContext.getName(), "Selecting Make A Payment form Tertiary Navigation");
						boolean bBillingAndPaymentlnk_uv = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut,lDriver);
						
						Report.TestPoint(testContext.getName(), "Secondary Link Billing and Payments", "true", String.valueOf(bBillingAndPaymentlnk_uv).toLowerCase(), true);
						
						new Actions(lDriver).moveToElement(oR_AccountOverview.lnkBillingUsagePaymentsSecNav).moveToElement(oR_AccountOverview.lnkMakeAPaymentTertNav).click().build().perform();
						Thread.sleep(2000);
						boolean bMAPPage_uv = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 60,lDriver);
						Report.ValidationPoint(testContext.getName(), "Make A Payment Page", "true", String.valueOf(bMAPPage_uv).toLowerCase(), true);
						Thread.sleep(1000);
						
//						boolean bClickonBnPLink_uv = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkMakeAPaymentTertNav);
//						Thread.sleep(2000);
//						if(bClickonBnPLink_uv==true){
//							
//							boolean bMAPPage_uv = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 60);
//							Report.ValidationPoint(testContext.getName(), "Make A Payment Page", "true", String.valueOf(bMAPPage_uv).toLowerCase(), true);
//							Thread.sleep(1000);
//						}
					    				    
						// Verify Flexible Payment Options link is displayed
						boolean bFlexPaymentOptions_uv = UI.WaitForObject(oR_MakeAPayment.lnkFlexiblePaymentOptions, UI.iObjTimeOut,lDriver).equals(true);
						Report.ValidationPoint(testContext.getName(), "Verify Flexible Payment Options link with Icon is displayed on Make A Payment page", "True",String.valueOf(bFlexPaymentOptions_uv), true);
						
						 						
						/*---------- Group 1:- Choose payment method (1. Payment entry) -------------*/
						
						//Verify that the accounts are listed with their nicknames (if present) with a check box to their left. By default the checkbox is unchecked.
						boolean bSelectAccToPay_uv = UI.WaitForObject(oR_MakeAPayment.txtSelectAccountToPay, UI.iObjTimeOut,lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify Select Account to Pay text is displayed on Make A Payment page", "True",String.valueOf(bSelectAccToPay_uv), true);
						
						List <WebElement> CheckBoxes_uv = lDriver.findElements(By.xpath("//input[@title = 'checkbox']"));
						if(CheckBoxes_uv.size() > 1) {
							for(int i=1; i<=CheckBoxes_uv.size(); i++)	{
								
								/* Verify BAN - Account Number is displayed*/
								if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[1]")), 5,lDriver)) {
								    String sText= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[1]")).getText();
								    if(sText.contains("BAN")) {
										  Report.ValidationPoint(testContext.getName(), "Verify Account details is displayed ", "True", "True", true);
									}
									else {
									  	  Report.ValidationPoint(testContext.getName(), "Verify Account details", "Account details should be displayed", "'Account details are not displayed", true);
									}
								 }
								
								Thread.sleep(1000);
								
								/* Verify Balance Amount is displayed*/
								if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[2]")), 5,lDriver)) {
								    String sText= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[2]")).getText();
								    if(sText.contains("Balance")) {
										  Report.ValidationPoint(testContext.getName(), "Verify Account details is displayed ", sText, sText, true);
									  }
									  else {
									  	  Report.ValidationPoint(testContext.getName(), "Verify Account details", "Account details should be displayed", "'Account details are not displayed", true);
									  }
								 }
								
								/* Verify Due Date is displayed*/
								if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[3]")), 5,lDriver)) {
								    String sText= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[3]")).getText();
								    if(sText.contains("Due Date")) {
										  Report.ValidationPoint(testContext.getName(), "Verify Due Date is displayed ", sText, sText, false);
									  }
									  else {
									  	  Report.ValidationPoint(testContext.getName(), "Verify Due Date displayed","Due Date should be displayed", "Due date not displayed", true);
									  }
								 }
								
								/* Verify Past Due , Please pay immediately is displayed*/
								if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[4]")), 5,lDriver)) {
								    String sText= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[4]")).getText();
								    if(sText.contains("past due")) {
										  Report.ValidationPoint(testContext.getName(), "Verify past Due is displayed ", sText, sText, false);
									  }
									  else {
									  	  Report.ValidationPoint(testContext.getName(), "Verify past Due displayed","Past Due Amount be displayed", "Past Due Amount is not displayed", true);
									  }
								 }
								Thread.sleep(1000);
								
								break;
								
							}	
						}
						else {
							Report.ValidationPoint(testContext.getName(), "Accounts are listed with check box to their left", "true", "false", true);
						}

						
						/*Verifying text past due and please pay immediately 
						String sPastDuePayImmediately_uv = oR_MakeAPayment.txtPleasePayImmediately.getText();
						if(sPastDuePayImmediately_uv.contains("past due") && sPastDuePayImmediately_uv.contains("Please pay immediately") )
						{
							Report.ValidationPoint(testContext.getName(), "Verify the text 'Please pay Immediately' displayed  ", "true", String.valueOf(sPastDuePayImmediately_uv), false);
						} else {
							
							Report.ValidationPoint(testContext.getName(), "Verify the text 'Please pay Immediately' displayed  ", "true", "false" , true);
						} */
						
						
						Thread.sleep(1000);
						
						//List <WebElement> Accounts_uv= lDriver.findElements(By.xpath("//p[@class= 'MarLeft0 botMar0  font16 bt_left']"));
						List <WebElement> Accounts_uv= lDriver.findElements(By.xpath("//div[@class= 'map-info MarLeft28 MarTopNeg5']"));
						int iNumberOfAcc_uv = Accounts_uv.size();
						Report.ValidationPoint(testContext.getName(), iNumberOfAcc_uv + " Accounts are listed for payment", "true", "true", true);


									       
						/*---------- Group 2:- Choose payment method (1. Payment entry) -------------*/
						
						Thread.sleep(1000);
						
						Boolean bPaymentMethod_uv = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethodfrm,1,lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify Payment Method text is displayed", "True", String.valueOf(bPaymentMethod_uv), true);
						
						// Payment Method edit box is displayed
						if (UI.CheckExist(oR_MakeAPayment.lstPaymentMethod).equalsIgnoreCase("True"))
						{
							Thread.sleep(2000);
							Report.ValidationPoint(testContext.getName(), "Verify Payment Method list is displayed", "True", "True", true);
						}	
						
						//Verify calender is displayed for today's date selection
						if(UI.WaitForObject(oR_MakeAPayment.imgCalender, 20,lDriver))
						{
							Report.ValidationPoint(testContext.getName(), "Verify Image calender is displayed", "True", "True", true);
							Thread.sleep(1000);
						}	
								
						
						
						// Payment Amount edit box is displayed
						if(UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1, 5,lDriver)) {

							//Verify payment amount in Payment amount edit field
							String sPaymentAmount_uv=oR_MakeAPayment.edtPaymentAmount1.getText();

							Report.ValidationPoint(testContext.getName(), "Verify Payment Amount is displayed", "True", String.valueOf(sPaymentAmount_uv), false);
							Thread.sleep(1000);
						}
						
						//Verify that for all accounts there is an option to enroll in autopay with a check box to the left of it. Verify the presence of a help icon next to the check box. Note: not displayed if the account is already actively enrolled in autopay. Will not be displayed if autopay is pending
						//int iEnrollInAutopayChkBox_lwl = lDriver.findElements(By.xpath("//input[contains(@id,'enrollInAutopayChkBox')]")).size();
						int iEnrollInAutopayChkBox_uv = lDriver.findElements(By.xpath("//div[contains(@id,'enrollInAutopayChkBox')]")).size();
						int iHelpIcon_uv = lDriver.findElements(By.xpath("//a[contains(@id,'enrollinAutoPayHelp')]")).size();
						if(iEnrollInAutopayChkBox_uv ==  iHelpIcon_uv)
							Report.ValidationPoint(testContext.getName(), "For all accounts there is an option to enroll in autopay with a check box to the left and help icon next to the check box", "true", "true", true);
						else
							Report.ValidationPoint(testContext.getName(), "For all accounts there is an option to enroll in autopay with a check box to the left and help icon next to the check box", "true", "false", true);
						
						//Verifying number of Split this payment links present
						List <WebElement> SplitThisPayment_uv =  lDriver.findElements(By.xpath("//a[contains(text(),'Split this payment')]"));
						System.out.println(SplitThisPayment_uv.size()+" size");
					 	List <WebElement> HelpIcon_uv = lDriver.findElements(By.xpath("//div[contains(@id,'paymentItem_AddAnchor')]"));
						if(SplitThisPayment_uv.size()>=1 && HelpIcon_uv.size() == SplitThisPayment_uv.size())
						{
							Report.ValidationPoint(testContext.getName(), "Verify Split this payment link exists", "true","true", true);
							
							for(int i=0; i<=SplitThisPayment_uv.size()-1; i++)	
							{
								int m = i+1;
								Report.ValidationPoint(testContext.getName(), "Split Payment for Account " +m, "Present", "Present", true);
								//SplitThisPayment_uv.get(i).click();
								//1:Another row is listed which allows the user to make 2nd of the 2 part arrangement with the following fields:
								//2: Enroll in autopay checkbox gets disabled.
					
								if(lDriver.findElement(By.xpath("//div/a[@id='acct_"+i+"_rmvpmtlink']")).isDisplayed());
								{
									Report.ValidationPoint(testContext.getName(), "Another row is listed, Enroll In Autopay is disabled and delete this row CTA present", "true","true", true);
									
									//Click on 'Remove this Payment' link. Note: Execute this step if the previous step was executed.
									//lDriver.findElement(By.xpath("//div/a[@id='acct_"+i+"_rmvpmtlink']")).click();
									
									//3: If the customer selects this link the split payment will be removed from the account and the page will be refreshed  the Amount to Pay and Pay On Date fields will revert back to the values shown when the customer landed on the page.
						
									Boolean bSplitThisPayment_lwl = UI.WaitForObject(SplitThisPayment_uv.get(i), UI.iObjTimeOut,lDriver);
									Report.ValidationPoint(testContext.getName(), "Split Payment must be removed", "true", String.valueOf(bSplitThisPayment_lwl), true);
									Thread.sleep(2000);
								}
							}	
						
						}
						else {
							Report.ValidationPoint(testContext.getName(), "Split Payment", "Present", "Absent", true);
						}
									
						
						
						/*---------- Group 3:- Cancel and Continue buttons -------------*/
						
						//Verify Continue link
						if(UI.WaitForObject(oR_MakeAPayment.btnNext, 10, lDriver))
						{
							
							Report.ValidationPoint(testContext.getName(), "Verify the link 'Next' displayed  ", "true", "true", true);	
						} else {
							
							Report.ValidationPoint(testContext.getName(), "Verify the link 'Next' displayed  ", "true", "false", true);
						}
						
						//Verify Cancel link
						 Boolean bCancel = UI.WaitForObject(oR_MakeAPayment.lnkCancel,5,lDriver);
						 Report.ValidationPoint(testContext.getName(), "Verify Cancel link displayed", "true",String.valueOf(bCancel), true);
			           					 
						 
								            
						//Validate Split payment link
						/*if(UI.WaitForObject(oR_MakeAPayment.lnkSplitThisPayment,1))
						{
							Report.ValidationPoint(testContext.getName(), "Verify Split payment link", "True","True", true);
							//Click on the link
							Report.OperationPoint(testContext.getName(), "	Operational - Clicking on Split payment link");
							
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Verify Split payment link", "True","False", true);
						} */
				 
					 break; 
						
						
						
					case "CB-Uverse":
						
						String sBalanceDue_cbu = null;
						
						Report.OperationPoint(testContext.getName(), "Selecting Make A Payment form Tertiary Navigation");
						boolean bBillingAndPaymentlnk_cbu = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut,lDriver);
						
						Report.TestPoint(testContext.getName(), "Secondary Link Billing and Payments", "true", String.valueOf(bBillingAndPaymentlnk_cbu).toLowerCase(), true);
						
						new Actions(lDriver).moveToElement(oR_AccountOverview.lnkBillingUsagePaymentsSecNav).moveToElement(oR_AccountOverview.lnkMakeAPaymentTertNav).click().build().perform();
						Thread.sleep(2000);
						boolean bMAPPage_cbu = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 60,lDriver);
						Report.ValidationPoint(testContext.getName(), "Make A Payment Page", "true", String.valueOf(bMAPPage_cbu).toLowerCase(), true);
						Thread.sleep(1000);
						
						/*boolean bClickonBnPLink_cbu = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkMakeAPaymentTertNav);
						Thread.sleep(2000);
						if(bClickonBnPLink_cbu==true)
						{
							boolean bMAPPage_cbu = UI.WaitForObject(oR_MakeAPayment.txtMakeAPayment, 60);
							Report.ValidationPoint(testContext.getName(), "Make A Payment Page", "true", String.valueOf(bMAPPage_cbu).toLowerCase(), true);
							Thread.sleep(1000);
						}*/
							   				    
						// Verify Flexible Payment Options link is displayed
						boolean bFlexPaymentOptions_cbu = UI.WaitForObject(oR_MakeAPayment.lnkFlexiblePaymentOptions, UI.iObjTimeOut,lDriver).equals(true);
						Report.ValidationPoint(testContext.getName(), "Verify Flexible Payment Options link with Icon is displayed on Make A Payment page", "True",String.valueOf(bFlexPaymentOptions_cbu), true);
						
						 						
						/*---------- Group 1:- Choose payment method (1. Payment entry) -------------*/
						
						//Verify that the accounts are listed with their nicknames (if present) with a check box to their left. By default the checkbox is unchecked.
						try
						{
							boolean bSelectAccToPay_cbu = UI.WaitForObject(oR_MakeAPayment.txtSelectAccountToPay, UI.iObjTimeOut,lDriver);
							if(bSelectAccToPay_cbu)
							{
								Report.ValidationPoint(testContext.getName(), "Verify Select Account to Pay text is displayed on Make A Payment page", "True",String.valueOf(bSelectAccToPay_cbu), true);
							}
						}catch(Exception Ee)
						{
							Report.OperationPoint(testContext.getName(),"No accounts are listed with their nicknames with a check box to their left");
						}
						
						List <WebElement> CheckBoxes_cbu = lDriver.findElements(By.xpath("//input[@title = 'checkbox']"));
						if(CheckBoxes_cbu.size() > 1) {
							for(int i=1; i<=CheckBoxes_cbu.size(); i++)	{
								
								/* Verify BAN - Account Number is displayed*/
								if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[1]")), 5,lDriver)) {
								    String sText_cbu= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[1]")).getText();
								    if(sText_cbu.contains("BAN") || sText_cbu.contains("BTN") || sText_cbu.contains("Account")) {
										  Report.ValidationPoint(testContext.getName(), "Verify Account details is displayed ", "True", "True", true);
									}
									else {
									  	  Report.ValidationPoint(testContext.getName(), "Verify Account details", "Account details should be displayed", "'Account details are not displayed", true);
									}
								 }
								
								Thread.sleep(1000);
								
								/* Verify Balance Amount is displayed*/
								if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[2]")), 5,lDriver)) {
								    String sText_cbu= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[2]")).getText();
								    if(sText_cbu.contains("Balance")) {
										  Report.ValidationPoint(testContext.getName(), "Verify Account details is displayed ", sText_cbu, sText_cbu, true);
									  }
									  else {
									  	  Report.ValidationPoint(testContext.getName(), "Verify Account details", "Account details should be displayed", "'Account details are not displayed", true);
									  }
								 }
								
								/* Verify Due Date is displayed*/
								if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[3]")), 5,lDriver)) {
								    String sText_cbu= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[3]")).getText();
								    if(sText_cbu.contains("Due Date")) {
										  Report.ValidationPoint(testContext.getName(), "Verify Due Date is displayed ", sText_cbu, sText_cbu, false);
									  }
									  else {
									  	  Report.ValidationPoint(testContext.getName(), "Verify Due Date displayed","Due Date should be displayed", "Due date not displayed", true);
									  }
								 }
								
								/* Verify Past Due , Please pay immediately is displayed*/
								if(UI.WaitForObject(lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[4]")), 5,lDriver)) {
								    String sText_cbu= lDriver.findElement(By.xpath("//*[@id='ulAccount0']/li[4]")).getText();
								    if(sText_cbu.contains("past due")) {
										  Report.ValidationPoint(testContext.getName(), "Verify past Due is displayed ", sText_cbu, sText_cbu, false);
									  }
									  else {
									  	  Report.ValidationPoint(testContext.getName(), "Verify past Due displayed","Past Due Amount be displayed", "Past Due Amount is not displayed", true);
									  }
								 }
								Thread.sleep(1000);
								
								break;
								
							}	
						} else if(CheckBoxes_cbu.size() == 0) {
						
							/* Verify BAN - Account Number is displayed*/
							if(UI.WaitForObject(lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[1]")), 5,lDriver)) {
							    String sText_cbu= lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[1]")).getText();
							    if(sText_cbu.contains("Account")|| sText_cbu.contains("BAN")|| sText_cbu.contains("BTN")) {
									  Report.ValidationPoint(testContext.getName(), "Verify Account details is displayed ", "True", "True", true);
								}
								else {
								  	  Report.ValidationPoint(testContext.getName(), "Verify Account details", "Account details should be displayed", "'Account details are not displayed", true);
								}
							 }
							
							Thread.sleep(1000);
							
							/* Verify Balance Amount is displayed*/
							if(UI.WaitForObject(lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[2]")), 5,lDriver)) {
							    String sText_cbu= lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[2]")).getText();
							    if(sText_cbu.contains("Balance")) {
							    	  sBalanceDue_cbu = sText_cbu;
									  Report.ValidationPoint(testContext.getName(), "Verify Balance Amount is displayed ", sText_cbu, sText_cbu, true);
								  }
								  else {
								  	  Report.ValidationPoint(testContext.getName(), "Verify Balance Amount is displayed", "Balance Amount should be displayed", "Balance Amount is not displayed", true);
								  }
							 }
							
							/* Verify Due Date is displayed*/
							if(UI.WaitForObject(lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[3]")), 5,lDriver)) {
							    String sText_cbu= lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[3]")).getText();
							    if(sText_cbu.contains("Due Date:")) {
									  Report.ValidationPoint(testContext.getName(), "Verify Due Date is displayed ", sText_cbu, sText_cbu, false);
								  }
								  else {
								  	  Report.ValidationPoint(testContext.getName(), "Verify Due Date displayed","Due Date should be displayed", "Due date not displayed", true);
								  }
							 }
							
							/* Verify Past Due , Please pay immediately is displayed*/
							if (!sBalanceDue_cbu.contains("$0.00")) {
								/* Verify Past Due , Please pay immediately is displayed*/
								if(UI.WaitForObject(lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[4]")), 5,lDriver)) {
								    String sText_cbu= lDriver.findElement(By.xpath("//ul[@class='map-account-info botMar25 topMar25']/li[4]")).getText();
								    if(sText_cbu.contains("past due")) {
										  Report.ValidationPoint(testContext.getName(), "Verify past Due, Please pay immediately tage is displayed ", sText_cbu, sText_cbu, false);
									}
								    else {
									  	  Report.ValidationPoint(testContext.getName(), "Verify past Due, Please pay immediately tage is displayed displayed","Past Due Amount be displayed", "Past Due Amount is not displayed", true);
									}
								 }
						      }
								Thread.sleep(1000);
							
						} else {
							
							Report.ValidationPoint(testContext.getName(), "Accounts are listed with check box to their left", "true", "false", true);
						}
			
						
						Thread.sleep(1000);
						
						List <WebElement> Accounts_cbu= lDriver.findElements(By.xpath("//div[@class= 'map-info MarLeft28 MarTopNeg5']"));
						int iNumberOfAcc_cbu = Accounts_cbu.size();
						Report.ValidationPoint(testContext.getName(), iNumberOfAcc_cbu + " Accounts are listed for payment", "true", "true", true);


									       
						/*---------- Group 2:- Choose payment method (1. Payment entry) -------------*/
						
						Thread.sleep(1000);
						
						Boolean bPaymentMethod_cbu = UI.WaitForObject(oR_MakeAPayment.txtPaymentMethodfrm,1,lDriver);
						Report.ValidationPoint(testContext.getName(), "Verify Payment Method text is displayed", "True", String.valueOf(bPaymentMethod_cbu), true);
						
						// Payment Method edit box is displayed
						if (UI.CheckExist(oR_MakeAPayment.lstPaymentMethod).equalsIgnoreCase("True"))
						{
							Thread.sleep(2000);
							Report.ValidationPoint(testContext.getName(), "Verify Payment Method list is displayed", "True", "True", true);
						}	
						
						//Verify today's date selection
						if (UI.CheckExist(oR_MakeAPayment.txtPaymentDateLabel).equalsIgnoreCase("True")) {
							
							Report.ValidationPoint(testContext.getName(), "Verify Payment Date label is displayed", "true", "true", true);
						}
						
						//Verify calender is displayed for today's date selection
						if(UI.WaitForObject(oR_MakeAPayment.imgCalender, 20,lDriver))
						{
							Report.ValidationPoint(testContext.getName(), "Verify Image calender is displayed", "True", "True", true);
							Thread.sleep(1000);
						}	
								
						
						
						// Payment Amount edit box is displayed
						if(UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1, 5,lDriver)) {
							
							boolean bPaymentAmount = UI.WaitForObject(oR_MakeAPayment.edtPaymentAmount1, UI.iObjTimeOut, lDriver);
							//Verify payment amount in Payment amount edit field
							String sPaymentAmount_cbu=oR_MakeAPayment.edtPaymentAmount1.getText();

							Report.ValidationPoint(testContext.getName(), "Verify Payment Amount is displayed", "True", String.valueOf(bPaymentAmount), false);
							Thread.sleep(1000);
						}
						
						

						//Verify that for all accounts there is an option to enroll in autopay with a check box to the left of it. Verify the presence of a help icon next to the check box. Note: not displayed if the account is already actively enrolled in autopay. Will not be displayed if autopay is pending
						//int iEnrollInAutopayChkBox_lwl = lDriver.findElements(By.xpath("//input[contains(@id,'enrollInAutopayChkBox')]")).size();
						int iEnrollInAutopayChkBox_cbu = lDriver.findElements(By.xpath("//div[contains(@id,'enrollInAutopayChkBox')]")).size();
						int iHelpIcon_cbu = lDriver.findElements(By.xpath("//a[contains(@id,'enrollinAutoPayHelp')]")).size();
						if(iEnrollInAutopayChkBox_cbu ==  iHelpIcon_cbu)
							Report.ValidationPoint(testContext.getName(), "For all accounts there is an option to enroll in autopay with a check box to the left and help icon next to the check box", "true", "true", true);
						else
							Report.ValidationPoint(testContext.getName(), "For all accounts there is an option to enroll in autopay with a check box to the left and help icon next to the check box", "true", "false", true);
						
						//Verifying number of Split this payment links present
						List <WebElement> SplitThisPayment_cbu =  lDriver.findElements(By.xpath("//a[contains(text(),'Split this payment')]"));
						System.out.println(SplitThisPayment_cbu.size()+" size");
					 	List <WebElement> HelpIcon_cbu = lDriver.findElements(By.xpath("//div[contains(@id,'paymentItem_AddAnchor')]"));
						if(SplitThisPayment_cbu.size()>=1 && HelpIcon_cbu.size() == SplitThisPayment_cbu.size())
						{
							Report.ValidationPoint(testContext.getName(), "Verify Split this payment link exists", "true","true", true);
							
							for(int i=0; i<=SplitThisPayment_cbu.size()-1; i++)	
							{
								int m = i+1;
								Report.ValidationPoint(testContext.getName(), "Split Payment for Account " +m, "Present", "Present", true);
								//SplitThisPayment_uv.get(i).click();
								//1:Another row is listed which allows the user to make 2nd of the 2 part arrangement with the following fields:
								//2: Enroll in autopay checkbox gets disabled.
					
								if(lDriver.findElement(By.xpath("//div/a[@id='acct_"+i+"_rmvpmtlink']")).isDisplayed());
								{
									Report.ValidationPoint(testContext.getName(), "Another row is listed, Enroll In Autopay is disabled and delete this row CTA present", "true","true", true);
									
									//Click on 'Remove this Payment' link. Note: Execute this step if the previous step was executed.
									//lDriver.findElement(By.xpath("//div/a[@id='acct_"+i+"_rmvpmtlink']")).click();
									
									//3: If the customer selects this link the split payment will be removed from the account and the page will be refreshed  the Amount to Pay and Pay On Date fields will revert back to the values shown when the customer landed on the page.
						
									Boolean bSplitThisPayment_lwl = UI.WaitForObject(SplitThisPayment_cbu.get(i), UI.iObjTimeOut);
									Report.ValidationPoint(testContext.getName(), "Split Payment must be removed", "true", String.valueOf(bSplitThisPayment_lwl), true);
									Thread.sleep(2000);
								}
							}	
						
						}
						else {
							Report.ValidationPoint(testContext.getName(), "Split Payment", "Present", "Absent", true);
						}
									
						
						
						/*---------- Group 3:- Cancel and Continue buttons -------------*/
						
						//Verify Continue link
						if(UI.WaitForObject(oR_MakeAPayment.btnNext, 10, lDriver))
						{
							
							Report.ValidationPoint(testContext.getName(), "Verify the link 'Next' displayed  ", "true", "true", true);	
						} else {
							
							Report.ValidationPoint(testContext.getName(), "Verify the link 'Next' displayed  ", "true", "false", true);
						}
						
						//Verify Cancel link
						 Boolean bCancel_cbu = UI.WaitForObject(oR_MakeAPayment.lnkCancel,5,lDriver);
						 Report.ValidationPoint(testContext.getName(), "Verify Cancel link displayed", "true",String.valueOf(bCancel_cbu), true);
			           					 
				 
					 break; 
				    }
			} catch (Exception e)
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}
		
				
	}
	
}