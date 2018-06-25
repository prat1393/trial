package com.AppSpecificLibrary;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import com.OR.MyATTZone.*;
import com.SupportingFiles.IO;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.ReadEnvPropFile;
import com.SupportingFiles.Report;
import com.SupportingFiles.UI;

public class myATT_Zone {
	
	public static void Validate_AccessCustomerAccounts(final ITestContext testContext)
			throws Exception {
		
		String sTestName = testContext.getName();
		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());	
			OR_MyATTZone OR_MyATTZone = PageFactory.initElements(lDriver, OR_MyATTZone.class);
			
			UI.WaitForObject(OR_MyATTZone.AccessCustomerAccounts_lblSearchMenu, UI.iObjTimeOut);
			Report.TestPoint(sTestName, "Validate the myAT&T Zone heading on Access Customer Accounts page", "True", 
				  	UI.CheckExist(OR_MyATTZone.AccessCustomerAccounts_imgMyATTZone), true);
		
			// Validate all the search menu options
			Report.ValidationPoint(sTestName, "Validate - ATT Access ID", "True", UI.CheckExist(OR_MyATTZone.AccessCustomerAccounts_lnkATTAccessID),	true);
			Report.ValidationPoint(sTestName, "Validate - Wireless", "True", UI.CheckExist(OR_MyATTZone.AccessCustomerAccounts_lnkWireless), true);
			Report.ValidationPoint(sTestName, "Validate - Uverse", "True", UI.CheckExist(OR_MyATTZone.AccessCustomerAccounts_lnkUverse), true);
			Report.ValidationPoint(sTestName, "Validate - Wireline", "True", UI.CheckExist(OR_MyATTZone.AccessCustomerAccounts_lnkWireline), true);
			Report.ValidationPoint(sTestName, "Validate - DSL", "True", UI.CheckExist(OR_MyATTZone.AccessCustomerAccounts_lnkDSL), true);
			Report.ValidationPoint(sTestName, "Validate - FAN", "True", UI.CheckExist(OR_MyATTZone.AccessCustomerAccounts_lnkFAN), true);
			Report.ValidationPoint(sTestName, "Validate - Free Email", "True", UI.CheckExist(OR_MyATTZone.AccessCustomerAccounts_lnkFreeEmail), true);
		
		} catch (Exception e) {
			Report.TestPoint(sTestName, "Error in Execution", "True", e.getMessage(), true);
		}
			
	}
	
	public static void Perform_AccessSubscriberAccounts(final ITestContext testContext, String srchVal) throws Exception {
		
		String sTestName = testContext.getName();
		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());	
			OR_MyATTZone OR_MyATTZone = PageFactory.initElements(lDriver, OR_MyATTZone.class);
			
			if (UI.CheckExist(OR_MyATTZone.Option_SelectLoginID_rdoID) == "True") {
				srchVal = OR_MyATTZone.Option_SelectLoginID_rdoID.getAttribute("value");
				OR_MyATTZone.Option_SelectLoginID_rdoID.click();
				OR_MyATTZone.Option_SelectLoginID_btnContinue.click();
			}
	  
			UI.WaitForObject(OR_MyATTZone.AccessCustomerAccounts_lblSearchMenu, UI.iObjTimeOut);
			Report.TestPoint(sTestName, "Validate the title of Search Menu option window", "Online Account Management Tool : Access Subscriber Accounts", lDriver.getTitle(), true);
	
			// Validate the search details displayed
			//Report.ValidationPoint(sTestName, "Validate - Search Access_ID details", "AT&T Login ID " + srchVal,
					 // OR_MyATTZone.AccessATTID_lblSearchHeader.getText(), true);
			
			Report.ValidationPoint(sTestName, "Validate - Web Impersonate button", "True", UI.CheckExist(OR_MyATTZone.Wireless_Details_btnWebImpersonate), true);
			if(!testContext.getName().contains("CSR03601"))
			{
				Report.ValidationPoint(sTestName, "Validate - Mobile Impersonate button", "True", UI.CheckExist(OR_MyATTZone.Wireless_Details_btnMobileImpersonate), true);
			}
			Report.ValidationPoint(sTestName, "Validate - Subscriber Management button", "True", UI.CheckExist(OR_MyATTZone.Wireless_Details_btnSubscriberManagement), true);
			Report.ValidationPoint(sTestName, "Validate - Customer Activity button", "True", UI.CheckExist(OR_MyATTZone.Wireless_Details_btnCustomerActivity), true);
		} catch (Exception e) {
			Report.TestPoint(sTestName, "Error in Execution", "True", e.getMessage(), true);
		}
	}
	
	public static void Validate_WebImpersonateButton(final ITestContext testContext) throws Exception {
		
		String sTestName = testContext.getName();
		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());	
			OR_MyATTZone OR_MyATTZone = PageFactory.initElements(lDriver, OR_MyATTZone.class);
			
			// Click on Impersonate button.
			Report.OperationPoint(sTestName, "Click on Mirror myATT Web Impersonate button");
			OR_MyATTZone.Wireless_Details_btnWebImpersonate.click();
			UI.acceptAlert(lDriver); Thread.sleep(10000L);
			Report.OperationPoint(sTestName, "Accept Alert");
			Thread.sleep(20000);
			lDriver.switchTo().frame(1);
			UI.WaitForObject(OR_MyATTZone.WebImpersonate_lnkShop, UI.iObjTimeOut);
			  
			// Verify that user lands in Account Dash board page for the entered ATT Access ID
			Report.TestPoint(sTestName, "Verify that user lands in Account Dash board page", 
					  "True", UI.CheckExist(OR_MyATTZone.WebImpersonate_lnkShop), true);
		} catch (Exception e) {
			Report.TestPoint(sTestName, "Error in Execution", "True", e.getMessage(), true);
		}
	}
	
	public static void Perform_SubscriberMgmt(final ITestContext testContext, String srchVal) throws Exception {
		
		String sTestName = testContext.getName();
		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());	
			OR_MyATTZone OR_MyATTZone = PageFactory.initElements(lDriver, OR_MyATTZone.class);
			
			Report.TestPoint(sTestName, "Validate the Subscriber Mgmt button on Access Customer Accounts page", "True",
													UI.CheckExist(OR_MyATTZone.Wireless_Details_btnSubscriberManagement), true);
			
			Report.OperationPoint(sTestName, "Click on the subscriber management button");
			OR_MyATTZone.Wireless_Details_btnSubscriberManagement.click();
			
			Thread.sleep(10000L);
			Report.TestPoint(sTestName, "Validate the Subscriber Mgmt page", 
					"Online Account Management Tool : Access Subscriber Accounts", lDriver.getTitle(), true);
		} catch (Exception e) {
			Report.TestPoint(sTestName, "Error in Execution", "True", e.getMessage(), true);
		}
	}
	
	public static void Perform_AgentActivity(final ITestContext testContext, String srchVal) throws Exception {
		
		String sTestName = testContext.getName();
		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName());	
			OR_MyATTZone OR_MyATTZone = PageFactory.initElements(lDriver, OR_MyATTZone.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			Report.OperationPoint(sTestName, "Click on Agent Activity tab");
			UI.ForceClick(lDriver, OR_MyATTZone.AccessCustomerAccounts_lnkAgentActivity);
		  
			UI.WaitForObject(OR_MyATTZone.AgentActivity_txtUID, UI.iObjTimeOut);
			OR_MyATTZone.AgentActivity_txtUID.sendKeys(IO.GetParamVal(sTestParams, "ATTUID"));
			// Fill the From & End Dates
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Calendar cal = java.util.Calendar.getInstance(); cal.add(java.util.Calendar.DATE, -1);
			String ystrDate = dateFormat.format(cal.getTime());
			java.util.Date date1 = new java.util.Date(); String currDate = dateFormat.format(date1);
	       
			org.openqa.selenium.JavascriptExecutor executor = (org.openqa.selenium.JavascriptExecutor) lDriver;
			executor.executeScript("arguments[0].value='"+ ystrDate +"';", OR_MyATTZone.AgentActivity_txtFromDate);
			executor.executeScript("arguments[0].value='"+ currDate +"';", OR_MyATTZone.AgentActivity_txtEndDate);
			
			OR_MyATTZone.AgentActivity_btnSearch.click();
			
			UI.WaitForObject(OR_MyATTZone.AgentActivity_tblActivityDetails, UI.iObjTimeOut);
			Report.TestPoint(sTestName, "Validate - " + srchVal + " value in Activity Details", "True", 
					UI.VerifyInTable(srchVal, OR_MyATTZone.AgentActivity_tblActivityDetails, 0, 4), true);

		} catch (Exception e) {
			Report.TestPoint(sTestName, "Error in Execution", "True", e.getMessage(), true);
		}
	}
	
	
//////////////////////////////////////////////Generic Functions ////////////////////////////////////////////////////////		
	
	
	

	/**************************************************************
	 * Function Name - VerifyReviewPageOfMakePaymentUsingCreditDebitCardMethod 
	 * Description- This function performs Make a Payment flow 
	 * Parameters - final ITestContext testContext
	 * Date created -4th Aug 2015
	 * Developed by - 	Sneha Pansare
	 * Last Modified By -
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/

	//CSR2590,CSR2591,CSR2592  

	public static void VerifyReviewPageOfMakePaymentUsingCreditDebitCardMethod(final ITestContext testContext,String sMakePayment,String sDate) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());

			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
			OR_MyATTZone oR_MyATTZone = PageFactory.initElements(lDriver, OR_MyATTZone.class);

			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			//Parameter declaration
			String sNameOnCard = IO.GetParamVal(sTestParams, "Name on card");
			String sCardNumber = IO.GetParamVal(sTestParams, "Card number");
			String sCardExpirationMonthYear = IO.GetParamVal(sTestParams, "Card expiration month year");
			String sSecurityCode = IO.GetParamVal(sTestParams, "Security code");
			String sPaymentProfileName = IO.GetParamVal(sTestParams, "Payment profile name");
			String sZipCode = IO.GetParamVal(sTestParams, "Zip code"); 
			String sPaymentAmount = IO.GetParamVal(sTestParams, "Payment Amount");

			if(!testContext.getName().contains("CSR2544")){
			//Verify Billing, usage, payments link from tertiary navigation  
			boolean bBtnBillingUsagePayments = UI.WaitForObject(oR_MyATTZone.WebImpersonate_lnkBillingUsage, 10);

			if(bBtnBillingUsagePayments==true)
			{
				Report.TestPoint(testContext.getName(),"Verify Billing, usage, payments link from tertiary navigation", "true", String.valueOf(bBtnBillingUsagePayments), true);

				Actions action = new Actions(lDriver);

				action.moveToElement(oR_MyATTZone.WebImpersonate_lnkBillingUsage).build().perform(); 

				//Verify Make a payment link from tertiary navigation  
				boolean bBtnMakePaymentTerNav = UI.WaitForObject(oR_MyATTZone.WebImpersonate_myATT_MakePayment_TerNavigation, 10);
				Report.TestPoint(testContext.getName(),"Verify Make a payment link from tertiary navigation", "true", String.valueOf(bBtnMakePaymentTerNav), true);

				action.moveToElement(oR_MyATTZone.WebImpersonate_myATT_MakePayment_TerNavigation).click().build().perform();
			}
			else
			{
				try
				{
					oR_Dashboard.btnMakeApayment.click();
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(),"Verify Navigation to Make a payment page", "true", "Make a payment button NOT present on dashboard", true);
				}
			}
			//Verify Navigation to Make a payment page  
			boolean bTxtMakePayment = UI.WaitForObject(oR_Dashboard.txtMakePaymentHeading, 10);
			Report.TestPoint(testContext.getName(),"Verify Navigation to Make a payment page", "true", String.valueOf(bTxtMakePayment), true);
			}
			//////////////////////////////
			//Verify secured and unsecured payment methodsfor test case CSR4342
			if(testContext.getName().contains("CSR4342") || !testContext.getName().contains("CSR2544"))
			{
				WebElement dropdown = null;
				try
				{
					dropdown = lDriver.findElement(By.xpath("//select[@title='Payment Method']"));
					dropdown.click();
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(),"Click on Select payment method dropdown", "Clicked", "Dropdown DOES NOT exist", true);
				}

				//Verify Secured payment method 'New checking / savings account'
				try
				{
					lDriver.findElement(By.xpath("//option[text()='New checking / savings account']"));
					Report.TestPoint(testContext.getName(),"Verify Secured payment method 'New checking / savings account'", "Present", "Present", true);
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(),"Verify Secured payment method 'New checking / savings account'", "Present", "NOT Present", true);
				}

				//Verify Secured payment method 'New debit / credit card'
				try
				{
					lDriver.findElement(By.xpath("//option[text()='New debit / credit card']"));
					Report.TestPoint(testContext.getName(),"Verify Secured payment method 'New debit / credit card'", "Present", "Present", true);
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(),"Verify Secured payment method 'New debit / credit card'", "Present", "NOT Present", true);
				}

				//Verify UnSecured payment method 'Mail payment'
				try
				{
					lDriver.findElement(By.xpath("//option[text()='Mail payment']"));
					Report.TestPoint(testContext.getName(),"Verify UnSecured payment method 'Mail payment'", "Present", "Present", true);
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(),"Verify UnSecured payment method 'Mail payment'", "Present", "NOT Present", true);
				}

				//Verify UnSecured payment method 'Authorized location'
				try
				{
					lDriver.findElement(By.xpath("//option[text()='Authorized location']"));
					Report.TestPoint(testContext.getName(),"Verify UnSecured payment method 'Authorized location'", "Present", "Present", true);
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(),"Verify UnSecured payment method 'Authorized location'", "Present", "NOT Present", true);
				}

				//Verify UnSecured payment method 'Other (web/phone)'
				try
				{
					lDriver.findElement(By.xpath("//option[text()='Other (web/phone)']"));
					Report.TestPoint(testContext.getName(),"Verify UnSecured payment method 'Other (web/phone)'", "Present", "Present", true);
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(),"Verify UnSecured payment method 'Other (web/phone)'", "Present", "NOT Present", true);
				}
				dropdown.click();
			}

			//Select Credit card option from dropdown
			//Boolean bDropdown = UI.WaitForObject(oR_MyATTZone.lstPaymentMethod, UI.iObjTimeOut);	
			boolean bSelectCreditCard = false;
			try
			{
				Select select = new Select(lDriver.findElement(By.xpath("//select[@title='Payment Method']|//select[contains(@id,'installmentPaymentForm')]")));
				select.selectByVisibleText("New debit / credit card");
				//lDriver.findElement(By.xpath("//select[@title='Payment Method']"));
				//lDriver.findElement(By.xpath("//select[@title='Payment Method']")).click();
				Thread.sleep(2000);
				//bSelectCreditCard = UI.SelectOptionFromDropDown(oR_MyATTZone.lstPaymentMethod, "New debit / credit card");
				//action.moveToElement(lDriver.findElement(By.xpath("//option[text()='New debit / credit card']"))).click().build().perform();
				Report.TestPoint(testContext.getName(), "Select Payment method from dropdown", "Selected", "Selected", true);
				bSelectCreditCard =  true;
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Select Payment method from dropdown", "Selected", "FAILED to Select", true);
			}
			//Report.TestPoint(testContext.getName(), "Verify Select payment method dropdown", "true", String.valueOf(bDropdown),  true);

			if(bSelectCreditCard)
			{
				Thread.sleep(8000);
				Boolean bDebitCreditCardPaymentFrame = UI.WaitForObject(oR_MyATTZone.frmNewDebitCreditCardPaymentMethod, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Frame Credit/Debit card Payment Method", "true", String.valueOf(bDebitCreditCardPaymentFrame), true);    

				lDriver.switchTo().frame(oR_MyATTZone.frmNewDebitCreditCardPaymentMethod); 	
				Report.OperationPoint(testContext.getName(), "Entering valid Credit/Debit card Details");

				//Enter name on card
				Boolean bEdtNameOnCard = UI.WaitForObject(oR_MyATTZone.edtPaymentFrameNameOnCard, UI.iObjTimeOut);	
				Report.TestPoint(testContext.getName(), "Verify Name on card input box", "true", String.valueOf(bEdtNameOnCard),  true);

				oR_MyATTZone.edtPaymentFrameNameOnCard.sendKeys(sNameOnCard);
				Report.TestPoint(testContext.getName(), "Enter Name on card in input box", "Entered", "Entered",  true);

				//Enter card number
				Boolean bEdtCardNumber = UI.WaitForObject(oR_MyATTZone.edtPaymentFrameCardNumber, UI.iObjTimeOut);	
				Report.TestPoint(testContext.getName(), "Verify card number input box", "true", String.valueOf(bEdtCardNumber),  true);

				oR_MyATTZone.edtPaymentFrameCardNumber.sendKeys(sCardNumber);
				Report.TestPoint(testContext.getName(), "Enter  card number in input box", "Entered", "Entered",  true);

				//Extract month and year
				String[] arrMonthYear= null;
				try
				{
					arrMonthYear = sCardExpirationMonthYear.split("/");
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Enter card expiration month and year", "Entered", "Failed to extract date and month",  true);
				}
				System.out.println("arrMonthYear[0]"+arrMonthYear[0]);
				System.out.println("arrMonthYear[1]"+arrMonthYear[1]);
				//Select Card expiration month
				try
				{
					Select select = new Select(lDriver.findElement(By.xpath("//select[contains(@id,'expirationMonth')]")));
					select.selectByVisibleText(arrMonthYear[0]);
					//lDriver.findElement(By.xpath("//select[contains(@id,'expirationMonth')]")).click();
					Thread.sleep(2000);
					//WebElement eMonth = lDriver.findElement(By.xpath("//select[contains(@id,'expirationMonth')]//option[text()='12']"));
					//action.moveToElement(eMonth).click().build().perform();
					Report.TestPoint(testContext.getName(), "Select Card expiration month", "Selected", "Selected",  true);
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Select Card expiration month", "Selected", "FAILED to select",  true);
				}


				//Select Card expiration year
				try
				{
					Select select = new Select(lDriver.findElement(By.xpath("//select[contains(@id,'expirationYear')]")));
					select.selectByVisibleText("20"+arrMonthYear[1]);
					//lDriver.findElement(By.xpath("//select[contains(@id,'expirationYear')]")).click();
					Thread.sleep(2000);
					//action.moveToElement(lDriver.findElement(By.xpath("//select[contains(@id,'expirationYear')]//option[text()='2015']"))).click().build().perform();
					Report.TestPoint(testContext.getName(), "Select Card expiration year", "Selected", "Selected",  true);
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Select Card expiration year", "Selected", "FAILED to select",  true);
				}


				//Enter security code
				Boolean bEdtPaymentFrameSecurityNumber = UI.WaitForObject(oR_MyATTZone.edtPaymentFrameSecurityNumber, UI.iObjTimeOut);	
				Report.TestPoint(testContext.getName(), "Verify security code input box", "true", String.valueOf(bEdtPaymentFrameSecurityNumber),  true);

				oR_MyATTZone.edtPaymentFrameSecurityNumber.sendKeys(sSecurityCode);
				Report.TestPoint(testContext.getName(), "Enter security code in input box", "Entered", "Entered",  true);

				//Enter Card billing zip code
				Boolean bEdtPaymentFrameZipCode = UI.WaitForObject(oR_MyATTZone.edtPaymentFrameZipCode, UI.iObjTimeOut);	
				Report.TestPoint(testContext.getName(), "Verify Card billing zip code input box", "true", String.valueOf(bEdtPaymentFrameZipCode),  true);

				oR_MyATTZone.edtPaymentFrameZipCode.sendKeys(sZipCode);
				Report.TestPoint(testContext.getName(), "Enter Card billing zip code in input box", "Entered", "Entered",  true);

				//Check the save my payment information checkbox
				boolean blnCheckBoxExist = false;
				try
				{
					lDriver.findElement(By.xpath("//input[contains(@id,'paymentMethodForm.storePaymentProfileInd')]")).click();
					Report.TestPoint(testContext.getName(), "Check the save my payment information checkbox", "Selected", "Selected",  true);
					blnCheckBoxExist=true;
				}
				catch(Exception e)
				{
					//Report.TestPoint(testContext.getName(), "Check the save my payment information checkbox", "Selected", "FAILED to Select",  true);
				}

				Thread.sleep(2000);
				//Enter payment profile name
				if(blnCheckBoxExist==true)
				{
					Boolean bEdtPaymentFrameProfileName = UI.WaitForObject(oR_MyATTZone.edtPaymentFrameProfileName, UI.iObjTimeOut);	
					Report.TestPoint(testContext.getName(), "Verify payment profile name input box", "true", String.valueOf(bEdtPaymentFrameProfileName),  true);

					//String sPaymentProfileName1 = sPaymentProfileName+"1";
					oR_MyATTZone.edtPaymentFrameProfileName.sendKeys(sPaymentProfileName);
					Report.TestPoint(testContext.getName(), "Enter payment profile name in input box", "Entered", "Entered",  true);
				}

				//Click on continue button
				Boolean bBtnPaymentFrameContinue = UI.WaitForObject(oR_MyATTZone.btnPaymentFrameContinue, UI.iObjTimeOut);	
				Report.TestPoint(testContext.getName(), "Verify continue button of frame", "true", String.valueOf(bBtnPaymentFrameContinue),  true);

				oR_MyATTZone.btnPaymentFrameContinue.click();
				Report.TestPoint(testContext.getName(), "Click on continue button of frame", "Clicked", "Clicked",  true);

				Thread.sleep(5000);

				lDriver.switchTo().defaultContent();
				lDriver.switchTo().frame(1);
			if(!testContext.getName().contains("CSR2544")){
				if(sMakePayment.equalsIgnoreCase("yes"))
				{
					//enter payment amount
					//Enter payment amount  
					boolean bEdtPaymentAmount = UI.WaitForObject(oR_MyATTZone.edtPaymentAmount1,20);
					Report.TestPoint(testContext.getName(), "Verify payment amount input box" , "true", String.valueOf(bEdtPaymentAmount),  true);

					oR_MyATTZone.edtPaymentAmount1.clear();
					oR_MyATTZone.edtPaymentAmount1.sendKeys(sPaymentAmount);
					Thread.sleep(2000);

					Report.TestPoint(testContext.getName(), "Enter payment amount ", "Entered", "Entered", true);

					//Select date from calender

					if(sDate!=null)
					{
						Boolean bCalendar = UI.WaitForObject(oR_MyATTZone.imgCalender1, UI.iObjTimeOut);
						Report.TestPoint(testContext.getName(), "Verify Calendar image", "true", String.valueOf(bCalendar),  true);

						oR_MyATTZone.imgCalender1.click();

						Boolean bCalendarFrame = UI.WaitForObject(oR_MyATTZone.frmCalender, UI.iObjTimeOut);
						if(bCalendarFrame)
						{
							lDriver.switchTo().frame(oR_MyATTZone.frmCalender);
							Report.TestPoint(testContext.getName(), "Switch to calender frame", "Switched", "Switched", true);
						}
						else
						{
							Report.TestPoint(testContext.getName(), "Switch to calender frame", "Switched", "Failed to switch", true);
						}


						try
						{
							//Select date
							lDriver.findElement(By.xpath("//a[contains(@onclick,'"+sDate+"')]")).click();
							Thread.sleep(8000);
							Report.TestPoint(testContext.getName(), "Select date", "Selected", "Selected", true);
						}
						catch(Exception e)
						{
							Report.TestPoint(testContext.getName(), "Select date", "Selected", "Provided date DOES NOT exist", true);
						}

						lDriver.switchTo().defaultContent();
						lDriver.switchTo().frame(1);
					}
				}
			}
				//Click on continue button
				Boolean bBtnNext = UI.WaitForObject(oR_MyATTZone.btnNext, 10);	
				if(bBtnNext)
				{
					Report.TestPoint(testContext.getName(), "Verify next button", "true", String.valueOf(bBtnNext),  true);

					oR_MyATTZone.btnNext.click();
					Report.TestPoint(testContext.getName(), "Click on next button", "Clicked", "Clicked",  true);
					Thread.sleep(10000);
				}
				else
				{

					Boolean bBtnContinue = UI.WaitForObject(oR_MyATTZone.btnContinue, 5);	
					Report.TestPoint(testContext.getName(), "Verify continue button", "true", String.valueOf(bBtnContinue),  true);

					oR_MyATTZone.btnContinue.click();
					Report.TestPoint(testContext.getName(), "Click on continue button", "Clicked", "Clicked",  true);
					
				}
				//lDriver.switchTo().defaultContent();
				//lDriver.switchTo().frame(1);
				//Validate alert pop up if comes
				try
				{
					Thread.sleep(6000);
					lDriver.findElement(By.xpath("//*[text()='Payment Alert']/parent::*//*[contains(text(),'Continue')]")).click();
				}
				catch(Exception e)
				{

				}
				//Verify Payment Review Page is Displayed
				WebDriverWait wait = new WebDriverWait(lDriver, 60);
				try
				{

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'title')]//*[text()='Review Payment Details']|//*[contains(text(),'Review payment information')][2]")));
					Report.TestPoint(testContext.getName(), "Verify Payment Review Page is Displayed", "Displayed", "Displayed",  true);
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Verify Payment Review Page is Displayed", "Displayed", "NOT Displayed",  true);
				}

				if(sMakePayment.equalsIgnoreCase("yes"))
				{
					//Click on submit button on review page
					Boolean bBtnSubmit = UI.WaitForObject(oR_MyATTZone.btnSubmit, 10);	
					Report.TestPoint(testContext.getName(), "Verify submit button of review page", "true", String.valueOf(bBtnSubmit),  true);

					oR_MyATTZone.btnSubmit.click();
					Report.TestPoint(testContext.getName(), "Click on submit button on review page", "Clicked", "Clicked",  true);

					//Verify Payment Confirmation Message 
					Boolean bPaymentConfirmation = UI.WaitForObject(oR_MyATTZone.txtPaymentConfirmation, 40);	
					Report.TestPoint(testContext.getName(), "Verify Payment Confirmation Message", "true", String.valueOf(bPaymentConfirmation),  true);

					//Verify batch number which will be available in the confirmation number as MYW3x for WEB 
					WebDriverWait wait1 = new WebDriverWait(lDriver, 60); 

					try
					{
						wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'MYW')]")));
						Report.TestPoint(testContext.getName(), "Verify batch number which will be available in the confirmation number as MYW3x for WEB", "Displayed", "Displayed",  true);
					}
					catch(Exception e)
					{
						Report.TestPoint(testContext.getName(), "Verify batch number which will be available in the confirmation number as MYW3x for WEB", "Displayed", "NOT Displayed",  true);
					}
				}
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Select Payment method", "Selected", "Failed to select", true);
			}
			/*
//Verify payment confirmation page  
WebDriverWait wait = new WebDriverWait(lDriver, 60);
try
{

wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Confirmation')]")));
Report.TestPoint(testContext.getName(), "Verify Payment Confirmation Page is Displayed", "Displayed", "Displayed",  true);
}
catch(Exception e)
{
Report.TestPoint(testContext.getName(), "Verify Payment Confirmation Page is Displayed", "Displayed", "NOT Displayed",  true);
}
			 */

		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}

	/**************************************************************
	 * Function Name - verifyActivity 
	 * Description- This function Validates Agent activity events for provided product type
	 * Parameters - final ITestContext testContext
	 * Date created -29th July 2015
	 * Developed by - 	Sneha Pansare
	 * Last Modified By -
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/


	public static void verifyActivity(final ITestContext testContext,String sActivity1 , String sActivity2, String sProductValue) throws HeadlessException, IOException, AWTException
	{
		try
		{
			ReadEnvPropFile oReadEnvProp1 = LaunchAndLogout.oReadEnvProp;

			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);

			//Click on agent activity button 
			boolean bBtnAgentActivity = UI.WaitForObject(oR_Dashboard.btnAgentActivity, 10);
			Report.TestPoint(testContext.getName(),"Verify 'Agent Activity' button is displayed", "true", String.valueOf(bBtnAgentActivity), true);

			oR_Dashboard.btnAgentActivity.click();
			Report.TestPoint(testContext.getName(),"Clicked 'Agent Activity' button", "true", "true", true);

			//Enter attUID
			Boolean bEdtATT_UID = UI.WaitForObject(oR_Dashboard.edtATT_UID, 30);
			Report.TestPoint(testContext.getName(), "Enter attUID", "true", bEdtATT_UID.toString(), true);
			oR_Dashboard.edtATT_UID.clear();
			oR_Dashboard.edtATT_UID.sendKeys(oReadEnvProp1.AttuidMyATTZone());

			//Select start date
			Boolean bEdtStartDate = UI.WaitForObject(oR_Dashboard.edtStartDate, 30);
			Report.TestPoint(testContext.getName(), "Verify start date input box", "true", bEdtStartDate.toString(), true);
			oR_Dashboard.edtStartDate.click();
			Thread.sleep(4000);

			//get today's date


			Date date = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("d");
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
			System.out.println(sdf.format(date));



			String sFromDate = sdf.format(date);


			//Select from date as today date -1
			int iFromDate = Integer.parseInt(sFromDate);
			iFromDate = iFromDate -1 ;
			sFromDate = iFromDate+"";

			//sFromDate = DateUtil.addDays(sFromDate, 1);

			//Select to date as todays date
			String sToDate =  sdf.format(date);
			String sDateForActivitySearch = sdf2.format(date);

			//this will open calender 
			try
			{
				WebElement eStartDateLink = lDriver.findElement(By.xpath("//*[contains(@class,'calendar_container')]//*[contains(@class,'dates')]//li[contains(text(),'"+sFromDate+"')][contains(@class,'month')]"));
				Report.TestPoint(testContext.getName(), "Select start date", "Selected", "Selected", true);
				eStartDateLink.click();
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Select start date", "Selected", "Provided start date DOES NOT exist", true);
			}


			//Select end date
			Boolean bEdtEndDate = UI.WaitForObject(oR_Dashboard.edtEndDate, 30);
			Report.TestPoint(testContext.getName(), "Verify end date input box", "true", bEdtEndDate.toString(), true);
			oR_Dashboard.edtEndDate.click();
			Thread.sleep(4000);
			//this will open calender 
			try
			{
				WebElement eEndDateLink = lDriver.findElement(By.xpath("//*[contains(@class,'calendar_container')]//*[contains(@class,'dates')]//li[contains(text(),'"+sToDate+"')][contains(@class,'month')]"));

				Report.TestPoint(testContext.getName(), "Select end date", "Selected", "Selected", true);
				eEndDateLink.click();


			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Select end date", "Selected", "Provided end date DOES NOT exist", true);
			}


			Thread.sleep(20000);
			//Click on search button
			Boolean bBtnSearch = UI.WaitForObject(oR_Dashboard.btnSearchOnAgentActivitySection, 30);
			Report.TestPoint(testContext.getName(), "Click on search button", "true", bBtnSearch.toString(), true);
			oR_Dashboard.btnSearchOnAgentActivitySection.click();
			Thread.sleep(15000);

			//Verify Activity1 event. 
			int iActivityFound = 0;
			try
			{
				WebElement eEvent = lDriver.findElement(By.xpath("//*[contains(text(),'"+sDateForActivitySearch+"')]/parent::*/child::*[contains(text(),'"+sActivity1+"')]"));
				//WebElement eEvent = lDriver.findElement(By.xpath("//*[contains(text(),'"+sActivity1+"')]"));
				if(eEvent.isDisplayed())
				{
					Report.TestPoint(testContext.getName(), "Verify "+sActivity1+" event", "Event is present in activity list.", "Event is present in activity list.", true);
					iActivityFound = iActivityFound + 1;
				}
			}
			catch(Exception e)
			{
				try
				{
					for(int i = 0; i<=5 ; i++)
					{
						lDriver.findElement(By.xpath("//*[@class='next']")).click();

						List<WebElement> eEvent = lDriver.findElements(By.xpath("//*[contains(text(),'"+sDateForActivitySearch+"')]/parent::*/child::*[contains(text(),'"+sActivity1+"')]"));
						if(eEvent.size()>=1)
						{
							Report.TestPoint(testContext.getName(), "Verify "+sActivity1+" event", "Event is present in activity list.", "Event is present in activity list.", true);
							iActivityFound = iActivityFound + 1;
							break;
						}

					}	
				}
				catch(Exception e2)
				{
					Report.TestPoint(testContext.getName(), "Click on next search button to search activity", "true", "Failed to click", true);
				}
			}

			if(iActivityFound==0)
			{
				Report.TestPoint(testContext.getName(), "Verify "+sActivity1+" event", "Event is present in activity list.", "Event NOT present in activity list.", true);
			}

			//Verify Activity2 search event. 
			if(!(sActivity2==null))
			{
				try
				{
					WebElement eEventWithRespectToDate = lDriver.findElement(By.xpath("//*[contains(text(),'"+sDateForActivitySearch+"')]/parent::*/child::*[contains(text(),'"+sActivity2+"')]"));
					//WebElement eEventWithRespectToDate = lDriver.findElement(By.xpath("//*[contains(text(),'"+sActivity2+"')]"));
					WebElement eEventWithRespectToProductValue = lDriver.findElement(By.xpath("//*[contains(text(),'"+sProductValue+"')]/parent::*/child::*[contains(text(),'"+sActivity2+"')]"));
					if(eEventWithRespectToDate.isDisplayed() && eEventWithRespectToProductValue.isDisplayed())
					{
						Report.TestPoint(testContext.getName(), "Verify '"+sActivity2+"' event", "Event is present in activity list.", "Event is present in activity list.", true);
					}

				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Verify '"+sActivity2+"' event", "Event is present in activity list.", "Event NOT present in activity list.", true);
				}
			}

		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
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

			WebDriver llDriver = UI.getDriver(testContext.getName());
			OR_Dashboard oR_Dashboard = PageFactory.initElements(llDriver, OR_Dashboard.class);
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

			

		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}


	public static void verifySearchOptionsOfTopMenu(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver llDriver = UI.getDriver(testContext.getName());
			OR_Dashboard oR_Dashboard = PageFactory.initElements(llDriver, OR_Dashboard.class);

			//Verify Account option is present
			Boolean bAccounts = UI.WaitForObject(oR_Dashboard.lnkAccounts, 10);
			Report.TestPoint(testContext.getName(), "Verify Account option is present", "true", bAccounts.toString(), true);

			//Verify Errors option is present
			Boolean bErrors = UI.WaitForObject(oR_Dashboard.lnkErrors, 10);
			Report.TestPoint(testContext.getName(), "Verify Errors option is present", "true", bErrors.toString(), true);

			if(testContext.getName().contains("BVT"))
			{
				//Verify Profile option is present
				Boolean bProfile = UI.WaitForObject(oR_Dashboard.btnProfile, 10);
				Report.TestPoint(testContext.getName(), "Verify Profile option is present", "true", bProfile.toString(), true);

				//Verify Agent Activity option is present
				Boolean bAgentActivity = UI.WaitForObject(oR_Dashboard.btnAgentActivity, 10);
				Report.TestPoint(testContext.getName(), "Verify Agent Activity option is present", "true", bAgentActivity.toString(), true);
			}
			
			//Verify MSCT option is present
			Boolean bMobileShareComparison = UI.WaitForObject(oR_Dashboard.btnMobileShareComparison, 10);
			Report.TestPoint(testContext.getName(), "Verify MSCT option is present", "true", bMobileShareComparison.toString(), true);

			//Verify Sales Support option is present
			Boolean bSalesSupport = UI.WaitForObject(oR_Dashboard.lnkSalesSupport, 10);
			Report.TestPoint(testContext.getName(), "Verify Sales Support option is present", "true", bSalesSupport.toString(), true);

			//Verify Registration option is present
			Boolean bRegistration = UI.WaitForObject(oR_Dashboard.lnkRegistration, 10);
			Report.TestPoint(testContext.getName(), "Verify Registration option is present", "true", bRegistration.toString(), true);

			/*
			//Verify About Us option is present
			Boolean bAboutUs = UI.WaitForObject(oR_Dashboard.lnkAboutUs, 10);
			Report.TestPoint(testContext.getName(), "Verify About Us option is present", "true", bAboutUs.toString(), true);
			 */

			//Verify Fraud option is present
			Boolean blnkFraud = UI.WaitForObject(oR_Dashboard.lnkFraud, 10);
			Report.TestPoint(testContext.getName(), "Verify Fraud option is present", "true", blnkFraud.toString(), true);


		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}

	}


	public static void changeRole(final ITestContext testContext,String sNewRole) throws HeadlessException, IOException, AWTException
	{
		try
		{

			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_Profile_zone oR_Profile_Zone = PageFactory.initElements(lDriver, OR_Profile_zone.class);
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);

			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			String sATTUIDForRoleChange = IO.GetParamVal(sTestParams, "Role Change attUID");


			//Verify Profile tab
			boolean bBtnProfile = UI.WaitForObject(oR_Dashboard.btnProfile, 10);
			Report.TestPoint(testContext.getName(),"Verify Profile tab", "true", String.valueOf(bBtnProfile), true);

			//Click on Profile tab
			oR_Dashboard.btnProfile.click();
			Report.TestPoint(testContext.getName(),"Click on Profile tab", "true", "true", true);

			//Verify Search CSR sub tab
			boolean bBtnSearchCSR = UI.WaitForObject(oR_Profile_Zone.btnSearchCSR, 10);
			Report.TestPoint(testContext.getName(),"Verify Search CSR sub tab", "true", String.valueOf(bBtnSearchCSR), true);


			//Click on Search CSR sub tab
			oR_Profile_Zone.btnSearchCSR.click();

			Thread.sleep(4000);

			//Verify ATTUID input box
			boolean bEdtAttUID = UI.WaitForObject(oR_Profile_Zone.edtAttUID, 10);
			Report.TestPoint(testContext.getName(),"Verify ATTUID input box", "true", String.valueOf(bEdtAttUID), true);


			//Verify Search button
			boolean bBtnSearch = UI.WaitForObject(oR_Profile_Zone.btnSearch, 10);
			Report.TestPoint(testContext.getName(),"Verify Search button", "true", String.valueOf(bBtnSearch), true);

			//Enter ATTUID in 'ATTUID' Field

			oR_Profile_Zone.edtAttUID.sendKeys(sATTUIDForRoleChange);
			Thread.sleep(2000);

			Report.TestPoint(testContext.getName(),"Enter ATTUID in 'ATTUID' Field", "Entered", "Entered", true);

			//Click on search button
			oR_Profile_Zone.btnSearch.click();

			Thread.sleep(5000);

			//Verify Edit CTA
			boolean bLnkEdit = UI.WaitForObject(oR_Profile_Zone.lnkEdit, 10);
			Report.TestPoint(testContext.getName(),"Verify Edit CTA", "true", String.valueOf(bLnkEdit), true);

			//Click on Edit CTA
			oR_Profile_Zone.lnkEdit.click();
			//Thread.sleep(6000);
			Report.TestPoint(testContext.getName(),"Click on Edit CTA", "Clicked", "Clicked", true);


			//Verify Title - 'Please edit CSR profile information'

			try
			{
				WebElement txtMessage = (new WebDriverWait(lDriver, 70)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Please edit CSR profile information')]")));

				if(txtMessage.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(),"Verify Title - 'Please edit CSR profile information'", "Title displayed", "Title displayed", true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(),"Verify Title - 'Please edit CSR profile information'", "Title displayed", "Title NOT displayed", true);
			}


			//Change role from Role dropdown
			Select select = new Select(oR_Profile_Zone.lstRole);
			try
			{
				select.selectByVisibleText(sNewRole);
				Thread.sleep(2000);
				Report.TestPoint(testContext.getName(),"Change role to '"+sNewRole+"' from Role dropdown", "Changed", "Changed", true);

			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(),"Change role to '"+sNewRole+"' from Role dropdown", "Changed", "FAILED to Change", true);
			}

			//Verify Submit button
			boolean bBtnSubmit = UI.WaitForObject(oR_Profile_Zone.btnSubmit, 10);
			Report.TestPoint(testContext.getName(),"Verify Submit button", "true", String.valueOf(bBtnSubmit), true);

			oR_Profile_Zone.btnSubmit.click(); 

			//Verify success message
			try
			{
				WebElement txtSuccessMessage = (new WebDriverWait(lDriver, 70)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'successfully')]")));

				if(txtSuccessMessage.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(),"Verify Role changed", "Role changed successfully", "Role changed successfully", true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(),"Verify Role changed", "Role changed successfully", "FAILED to change Role", true);
			}

			//LaunchAndLogout.LogoutApplication(testContext);

			//lDriver.manage().deleteAllCookies();
			//Thread.sleep(1000);
			//System.out.println("Deleted all cookies");

		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}


	/**************************************************************
	 * Function Name - VerifyRoleHeadingOnDashboard 
	 * Description- This function varifies whether role with which user is logged is getting displayed on dashboard
	 * Parameters - final ITestContext testContext
	 * Date created -
	 * Date modified -
	 * Developed by - 	Sneha Pansare
	 * Last Modified By - 26th Nov 15
	 * Last Modified Date -
	 ***************************************************************/
	//CSR2599
	
	public static void VerifyRoleHeadingOnDashboard(final ITestContext testContext, String sRole) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			try
			{
				lDriver.findElement(By.xpath("//*[text()='"+sRole+"']"));
				Report.TestPoint(testContext.getName(),"Verify role name "+sRole+" is getting displayed on Account search page", "displayed","displayed", true);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(),"Verify role name "+sRole+" is getting displayed on Account search page", "displayed","NOT displayed", true);
			}
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}

	
	
	/**************************************************************
	 * Function Name - VerifyEventOnCustomerActivityPage 
	 * Description- This function varifies whether provided event is present in event list of customer activity page
	 * Parameters - final ITestContext testContext
	 * Date created -
	 * Date modified -
	 * Developed by - 	Sneha Pansare
	 * Last Modified By - 28th Dec 15
	 * Last Modified Date -
	 ***************************************************************/
	
	public static void VerifyEventOnCustomerActivityPage(final ITestContext testContext, String sEvent) throws HeadlessException, IOException, AWTException
	{
		String sTestName = testContext.getName();
	
		try
		{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());	// Test Specific Operations
			OR_MyATTZone OR_MyATTZone = PageFactory.initElements(lDriver, OR_MyATTZone.class);
			OR_Dashboard oR_Dashboard = PageFactory.initElements(lDriver, OR_Dashboard.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
	// Click on Customer Activity button
				OR_MyATTZone.Wireless_Details_btnCustomerActivity.click();
				Report.ValidationPoint(sTestName, "Click on Customer Activity button", "Clicked", 
						"Clicked", true);
				UI.WaitForObject(OR_MyATTZone.CustomerActivity_ddnSelect, UI.iObjTimeOut);
				
				

				//Select start date
				Boolean bEdtStartDate = UI.WaitForObject(oR_Dashboard.edtStartDate, 30);
				Report.TestPoint(testContext.getName(), "Verify start date input box", "true", bEdtStartDate.toString(), true);
				oR_Dashboard.edtStartDate.click();
				Thread.sleep(4000);

				//get today's date


				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("d");
				SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
				System.out.println(sdf.format(date));




				String sFromDate = sdf.format(date);
				//Select from date as today date -1
				//int iFromDate = Integer.parseInt(sFromDate);
				//iFromDate = iFromDate -1 ;
				//sFromDate = iFromDate+"";

				//Select to date as todays date
				String sToDate =  sdf.format(date);
				String sDateForActivitySearch = sdf2.format(date);

				//this will open calender 
				try
				{
					WebElement eStartDateLink = lDriver.findElement(By.xpath("//*[contains(@class,'calendar_container')]//*[contains(@class,'dates')]//li[contains(text(),'"+sFromDate+"')][contains(@class,'month')]"));
					Report.TestPoint(testContext.getName(), "Select start date", "Selected", "Selected", true);
					eStartDateLink.click();
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Select start date", "Selected", "Provided start date DOES NOT exist", true);
				}


				//Select end date
				Boolean bEdtEndDate = UI.WaitForObject(oR_Dashboard.edtEndDate, 30);
				Report.TestPoint(testContext.getName(), "Verify end date input box", "true", bEdtEndDate.toString(), true);
				oR_Dashboard.edtEndDate.click();
				Thread.sleep(4000);
				//this will open calender 
				try
				{
					WebElement eEndDateLink = lDriver.findElement(By.xpath("//*[contains(@class,'calendar_container')]//*[contains(@class,'dates')]//li[contains(text(),'"+sToDate+"')][contains(@class,'month')]"));

					Report.TestPoint(testContext.getName(), "Select end date", "Selected", "Selected", true);
					eEndDateLink.click();


				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Select end date", "Selected", "Provided end date DOES NOT exist", true);
				}


				Thread.sleep(20000);
				//Click on submit button
				Boolean bBtnSubmit = UI.WaitForObject(oR_Dashboard.btnSubmitOnCustomerActivity, 30);
				Report.TestPoint(testContext.getName(), "Click on submit button", "true", bBtnSubmit.toString(), true);
				oR_Dashboard.btnSubmitOnCustomerActivity.click();
				Thread.sleep(15000);

				// Validate the Customer Activity information	event 'Mirror Residential Customer'  	
				try
				{
					WebElement eEvent = lDriver.findElement(By.xpath("//*[contains(text(),'"+sDateForActivitySearch+"')]/parent::*/child::*[contains(text(),'"+sEvent+"')]"));
					if(eEvent.isDisplayed())
					{
						Report.TestPoint(testContext.getName(), "Verify "+sEvent+" event", "Event is present in activity list.", "Event is present in activity list.", true);

					}
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Verify "+sEvent+" event", "Event is present in activity list.", "Event is NOT present in activity list.", true);
				}
			} catch (Exception e){

				String[] errMsg = e.getMessage().split("\\r?\\n"); UI.printMsg(errMsg[0]);
				Report.TestPoint(sTestName, errMsg[0], "True", "False", true);
			}	
	}	
}