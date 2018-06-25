package com.AppSpecificLibrary;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;

import com.OR.MyATT.OR_AccountOverview;
import com.OR.MyATT.OR_BillAndUsage;
import com.OR.MyATT.OR_ViewOrChangeRatePlan;
import com.SupportingFiles.IO;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;
import com.SupportingFiles.UI;

public class Usage extends LaunchAndLogout {

	static Hashtable<String, String> sTestParams = new Hashtable<String, String>();

	/**************************************************************
	 * Function Name - ValidateUsagePageForSuspendedWirelineAcc 
	 * Description- This method validates the usage page is displayed as per design for a DSL customer whose Wireline account has been suspended
	 * Parameters - ITestContext
	 * Date created - 13 March,2015
	 * Developed by - Rahul Bakde
	 * Last Modified By - 
	 * Last Modified Date -
	 * 
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	public static void ValidateUsagePageForSuspendedWirelineAcc(final ITestContext testContext) throws HeadlessException, IOException, AWTException{
		try {
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
			Boolean bSecoNav=UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingAndPaymentSecNav, null);
			if (bSecoNav) {
				Report.TestPoint(testContext.getName(), "Validate Billing & Payment link is present in secondary nav", "True", "True", true);
				//validate Bill and Usage page is displayed
				if (UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut)) {
					Report.TestPoint(testContext.getName(), "Validate Billing & Usage page is displayed", "True", "True", true);
					//validate the account suspended message is displayed
					Boolean bAccSuspendedMsg=UI.WaitForObject(oR_BillAndUsage.txtAccSuspendedMsg, 1);
					Report.TestPoint(testContext.getName(), "Validate account suspended message is displayed", "True", bAccSuspendedMsg.toString(), true);
				} else {
					Report.TestPoint(testContext.getName(), "Validate Billing & Usage page is displayed", "True", "False", true);
				}
			} else {
				Report.TestPoint(testContext.getName(), "Validate Billing & Payment link is present in secondary nav", "True", "False", true);
			}
		} catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name - ValidateUsageUnbilledBonusRolloverModal 
	 * Description- This method validates the Bonus & Rollover Modal for unbilled Usage.
	 * Parameters - ITestContext
	 * Date created - 22nd April 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 * 
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	//USG11480
	public static void ValidateUsageUnbilledBonusRolloverModal(final ITestContext testContext) throws HeadlessException, IOException, AWTException{
		try {
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
			
			Boolean bBAPSecNav = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Billing, Usage Payment link is displayed on Sec Navigation bar", "true", bBAPSecNav.toString(), true);
			//Navigate to Bills & Usage page
			
			Report.OperationPoint(testContext.getName(), "Operation : Navigating to Bills & Usage page");
			if(bBAPSecNav.equals(true))
			{
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
				Boolean bUsage = UI.WaitForObject(oR_BillAndUsage.lnkUsage, 80);
				Report.TestPoint(testContext.getName(), "Verify Usage tab is displayed", "true", bUsage.toString(), true);
				if(bUsage.equals(true))
				{
					Report.OperationPoint(testContext.getName(), "Operation : Navigating to Usage tab");
					oR_BillAndUsage.lnkUsage.click();

					//validate showing dropdown and click on recent usage
					boolean bPre = UI.WaitForObject(oR_BillAndUsage.lstShowing, 40);
					Report.TestPoint(testContext.getName(), "validate showing dropdown and click on recent usage", "true", String.valueOf(bPre), true);
					Report.OperationPoint(testContext.getName(), "Operation : Clicking on the showing dropdown");
					oR_BillAndUsage.lstShowing.click();

					//validate recent usage and click
					boolean brecent = UI.WaitForObject(oR_BillAndUsage.lnkRecentBill, 10);
					Report.TestPoint(testContext.getName(), "validate recent usage and click", "true", String.valueOf(brecent), true);
					Report.OperationPoint(testContext.getName(), "Operation : Clicking on the recent usage link");
					oR_BillAndUsage.lnkRecentBill.click();

					//Validate Bonus + Rollover details CTA
					boolean bRollover = UI.WaitForObject(oR_BillAndUsage.lnkRolloverdetails, 70);
					Report.TestPoint(testContext.getName(), "validate Bonus + Rollover detail click", "true", String.valueOf(bRollover), true);
					oR_BillAndUsage.lnkRolloverdetails.click();
					
					//Handle ScrollBar
					WebElement lnkLearnMore = lDriver.findElement(By.xpath("//a[contains(text(),'Learn more about Rollover Data')]"));
					((JavascriptExecutor) lDriver).executeScript("arguments[0].scrollIntoView(true);", lnkLearnMore);
					//Thread.sleep(500);
					
					//Handle new Window
					String parentHandle = lDriver.getWindowHandle(); // get the current window handle
					lnkLearnMore.click();
					Report.OperationPoint(testContext.getName(), "Operation : Navigating to 'About Rollover Data' window ");

					for (String winHandle : lDriver.getWindowHandles()) {
						lDriver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					}

					WebElement txtLearnMore = lDriver.findElement(By.xpath("//h1[contains(text(),'About Rollover Data')]"));
					boolean bLearn = UI.WaitForObject(txtLearnMore, 20);
					Report.TestPoint(testContext.getName(), "validate User is Navigated to Learn more Rollover data page", "true", String.valueOf(bLearn), true);
					lDriver.close(); // close newly opened window when done with it
					lDriver.switchTo().window(parentHandle); // switch back to the original window
					
					//Close the Modal
					WebElement btnClose = lDriver.findElement(By.xpath("//a[@class='closePopupRllover float-right left2pxIE btnimg']"));
					boolean bClose = UI.WaitForObject(btnClose, 60);
					Report.TestPoint(testContext.getName(), "validate Close button is displayed on Bonus + Rollover details Modal", "true", "true", true);
					btnClose.click();
					Report.TestPoint(testContext.getName(), "validate 'Bonus + Rollover details Modal' is closed", "true", "true", true);
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
	 * Function Name - ValidateUsageReportInGBMBs 
	 * Description- Verify Customer’s usage report is displayed in MBs/GBs.
	 * Parameters - ITestContext
	 * Date created - 22 April 2015
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date -
	 * Test Case - USG06271
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	public static void ValidateUsageReportInGBMBs(final ITestContext testContext) throws HeadlessException, IOException, AWTException{
		try {
			WebDriver lDriver = UI.getDriver(testContext.getName());           
			
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		
			Report.OperationPoint(testContext.getName(), "Navigate to Billing&Usage-> Reports");
			if(UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut)){
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkBillReports);
				Boolean bBillingUsages = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut);
				if(bBillingUsages){

					if(oR_BillAndUsage.lstSelectReportType.isDisplayed()){
						Report.OperationPoint(testContext.getName(), "Click on Select Report drop down");
						oR_BillAndUsage.lstSelectReportType.click();
						List<WebElement> dataUsageOption = lDriver.findElements(By.xpath("//*[@id='dataUsageTrend']/a"));
						List<WebElement> mobileShareUsageOption = lDriver.findElements(By.xpath("//*[@id='sharedDataUsage']/a"));
						
						WebElement startBillingDate = lDriver.findElement(By.xpath("//*[@id='ddShortcut1']/div"));
						WebElement endBillingDate = lDriver.findElement(By.xpath("//*[@id='ddShortcut2']/div"));

						for(int i=1;i<=2;i++){	  
							if((i==1 && dataUsageOption.size() == 1) || (i == 2 && mobileShareUsageOption.size() == 1) ){
							
								if(i==1){
									// Validate Data Usage option is available under Select Report Type drop down
									Report.OperationPoint(testContext.getName(), "Validating for Data Usage");  
									UI.WaitForObject(dataUsageOption.get(0),UI.iObjTimeOut);
									dataUsageOption.get(0).click();
									Report.ValidationPoint(testContext.getName(), "Validate Data Usage option is available under Select Report Type drop down", "Data Usage option is available under Select Report Type drop down", "Data Usage option is available under Select Report Type drop down", true); 
								}else{
									// Validate Total Mobile Share Usage option is available under Select Report Type drop down
									Report.OperationPoint(testContext.getName(), "Validating for Total Mobile Share Usage");  
									oR_BillAndUsage.lstSelectReportType.click();
									UI.WaitForObject(mobileShareUsageOption.get(0),UI.iObjTimeOut);
									mobileShareUsageOption.get(0).click();
									Report.ValidationPoint(testContext.getName(), "Validate Total Mobile Share Usage option is available under Select Report Type drop down", "Total Mobile Share Usage option is available under Select Report Type drop down", "Total Mobile Share Usage option is available under Select Report Type drop down", true);  
								}
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

								// Check Graph Title
								Thread.sleep(20000);
								WebElement chartTitle = lDriver.findElement(By.cssSelector("text.highcharts-title tspan"));
								UI.WaitForObject(chartTitle, UI.iObjTimeOut);
								String strChartTitle = chartTitle.getText();
								if((i == 1 && strChartTitle.contains("Data Usage")) || (i == 2 && strChartTitle.contains("Total Mobile"))){
									List<WebElement> dataElements = lDriver.findElements(By.cssSelector("g.highcharts-data-labels text"));
									for(WebElement e : dataElements){
										String usageValue = e.findElement(By.tagName("tspan")).getText();
										Report.OperationPoint(testContext.getName(), " Data Usage = " + usageValue);
                                        // Validate usage value is in 0.XX format 
										if( usageValue.contains(".")  && usageValue.substring(usageValue.indexOf(".") + 1).length() == 2){
											Report.ValidationPoint(testContext.getName(),"Validate usage value is in 0.XX format" , "Value usage is in 0.XX format", "Value usage is in 0.XX format", true);
										}else{
											Report.ValidationPoint(testContext.getName(),"Validate usage value is in 0.XX format" , "Value usage is in 0.XX format", "Value usage is NOT in 0.XX format", true); 
										}
									}
								}else{
									if(i == 1){
										Report.ValidationPoint(testContext.getName(), "Validate Data Usage graph is displayed", "Data Usage graph is displayed", "Data Usage graph is NOT displayed",true);
									}else{
										Report.ValidationPoint(testContext.getName(), "Validate Total Mobile Share Usage graph is displayed", "Total Mobile Share Usage graph is displayed", "Total Mobile Share Usage graph is NOT displayed",true);
									}
								}
							}else{
								if(i == 1){
									Report.ValidationPoint(testContext.getName(), "Validate Data Usage (MB/GB) option is available under Select Report Type drop down", "Data Usage option is available under Select Report Type drop down", "Data Usage option is NOT available under Select Report Type drop down", true); 
								}else{
									Report.ValidationPoint(testContext.getName(), "Validate Total Mobile Share Usage (MB/GB) option is available under Select Report Type drop down", "Total Mobile Share Usage (MB/GB) option is available under Select Report Type drop down", "Total Mobile Share Usage (MB/GB) option is NOT available under Select Report Type drop down", true); 
								}
							}
						}
					}else{
						Report.ValidationPoint(testContext.getName(), "Validate Reports tab is by default selected", "Reports tab is by default selected", "Reports tab is by default NOT selected", true);
					}

				}else{
					Report.ValidationPoint(testContext.getName(), "Validate Billing & Payments page is displayed", "Billing & Payments page is displayed", "Billing & Payments page is NOT displayed", true);
				}
			}else{
				Report.ValidationPoint(testContext.getName(), "Validate Billing,Payment,Usage link is displayed", "Billing,Payment,Usage link is displayed", "Billing,Payment,Usage link is NOT displayed", true);
			}

		} catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
		
	
	/**************************************************************
	 * Function Name - ValidateUsageLandingPageForIRU 
	 * Description- Verify usage landing page for IRU
	 * Parameters - ITestContext
	 * Date created - 
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date -
	 * Test Case - USG0704
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	public static void ValidateUsageLandingPageForIRU(final ITestContext testContext) throws HeadlessException, IOException, AWTException{
		
	try {
            WebDriver lDriver = UI.getDriver(testContext.getName());           
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);  
			
			// Validate Usage bar is displayed
			if(UI.WaitForObject(oR_AccountOverview.imgDataUsageBar, UI.iObjTimeOut)){
				Report.ValidationPoint(testContext.getName(), "Verify Data Usage bar is displayed", "Data Usage bar is displayed", "Data Usage bar is displayed", true);
			}else{
				Report.ValidationPoint(testContext.getName(), "Verify Data Usage bar is displayed", "Data Usage bar is displayed", "Data Usage bar is NOT displayed", true);
			}
			
			// Validate Data usage is displayed
			if(UI.WaitForObject(oR_AccountOverview.txtLeftDataUsageValue, UI.iObjTimeOut)){
				String lfetDataUsage = oR_AccountOverview.txtLeftDataUsageValue.getText();
				System.out.println("Text " + lfetDataUsage);
				if(lfetDataUsage.contains("MB") || lfetDataUsage.contains("GB")){
				   Report.ValidationPoint(testContext.getName(), "Verify left data usage values is displayed in MB or GB", "Left data usage value is displayed in MB or GB", "Left data usage value is displayed in MB or GB", true);
				}else{
				   Report.ValidationPoint(testContext.getName(), "Verify left data usage values is displayed in MB or GB", "Left data usage value is displayed in MB or GB", "Left data usage value is NOT displayed in MB or GB", true); 	
				}
			}else{
				Report.ValidationPoint(testContext.getName(), "Verify left data usage values is displayed", "Left data usage values is displayed", "Left data usage values is NOT displayed", true);
			}
			
			// Validate employer's contribution is displayed
//			if(UI.WaitForObject(oR_AccountOverview.txtEmployerContributionNote, UI.iObjTimeOut)){
//				Report.ValidationPoint(testContext.getName(), "Verify Including Employer's Contribution note is displayed", "Including Employer's Contribution note is displayed", "Including Employer's Contribution note is displayed", true);
//			}else{
//				Report.ValidationPoint(testContext.getName(), "Verify Including Employer's Contribution note is displayed", "Including Employer's Contribution note is displayed", "Including Employer's Contribution note is NOT displayed", true);
//			}
			
			//Validate Mid cycle message is displayed
			if(UI.WaitForObject(oR_AccountOverview.txtMidCycleMessage, UI.iObjTimeOut)){
				Report.ValidationPoint(testContext.getName(), "Verify mid cycle message is displayed", "Mid cycle message is displayed", "Mid cycle message is displayed", true);
			}else{
				Report.ValidationPoint(testContext.getName(), "Verify mid cycle message is displayed", "Mid cycle message is displayed", "Mid cycle message is NOT displayed", true);
			}
							
			// Navigate to Billing and Usage section
			Report.OperationPoint(testContext.getName(), "Navigate to Billing & Usage page");
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkBillDetailsTertNav);
			if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut)){
				Report.ValidationPoint(testContext.getName(), "Validate Billing and Usage page is displayed", "Billing and Usage page is displayed", "Billing and Usage page is displayed", true);
	        	Report.OperationPoint(testContext.getName(), "Navigate to Usages tab");
	        	oR_BillAndUsage.lnkUsage.click();
	        	
	        	UI.WaitForObject(oR_BillAndUsage.txtShowListDefaultSelectedUsage, 30, lDriver);
	        	
	        	if(!oR_BillAndUsage.txtShowListDefaultSelectedUsage.getText().contains("Recent")){
	        		oR_BillAndUsage.lnkShowDropDown.click();
	        		
	        		UI.WaitForObject(oR_BillAndUsage.lnkRecentUsageFromDropDown, 20,lDriver);
	        		oR_BillAndUsage.lnkRecentUsageFromDropDown.click();
	        	}
	        	
	        
	        	
	        	if(UI.WaitForObject(oR_BillAndUsage.imgUsageUnavailable, UI.iObjTimeOut,lDriver)){
	        	   Report.ValidationPoint(testContext.getName(), "Validate Usage is displayed", "Usage is displayed", "Usage is NOT displayed", true);
	        		
	             }else{
	            	 Report.ValidationPoint(testContext.getName(), "Validate Usage is displayed", "Usage is displayed", "Usage is displayed", true);
	             }
	        	
	        	// Added following check after discuss with Sonali.
	        	if(UI.WaitForObject(oR_BillAndUsage.txtEmployerData, UI.iObjTimeOut,lDriver)){
	        		 Report.ValidationPoint(testContext.getName(), "Validate Data Employer contribution is displayed", "Data Employer contribution is displayed", "Data Employer contribution is displayed", true);
	        	}else{
	       		     Report.ValidationPoint(testContext.getName(), "Validate Data Employer contribution is displayed", "Data Employer contribution is displayed ", "Data Employer contribution is NOT displayed", true);
	        	}
	        	
	        	// Validate play video on usages details link is displayed
	           	if(UI.WaitForObject(oR_BillAndUsage.lnkPlayVideoOnViewUsageDetails, UI.iObjTimeOut,lDriver)){
					Report.ValidationPoint(testContext.getName(), "Validate play video on usages details link is displayed", "Play video on usages details link is displayed", "Play video on usages details link is displayed", true);
	       			Report.OperationPoint(testContext.getName(), "Play video on usages details");
					oR_BillAndUsage.lnkPlayVideoOnViewUsageDetails.click();
					Thread.sleep(5000);
					
					if(UI.WaitForObject(oR_BillAndUsage.btnCloseOnViewWirelessUsageVideoWindow, UI.iObjTimeOut)){
						Report.ValidationPoint(testContext.getName(), "Validate video on usages details is displayed", "Video on usages details is displayed", "Video on usages details is displayed", true);
						oR_BillAndUsage.btnCloseOnViewWirelessUsageVideoWindow.click();
						Thread.sleep(5000);
					}else{
						Report.ValidationPoint(testContext.getName(), "Validate video on usages details is displayed", "Video on usages details is displayed", "Video on usages details is NOT displayed", true);
					}
					
	        	}else{
	        		Report.ValidationPoint(testContext.getName(), "Validate play video on usages details link is displayed", "Play video on usages details link is displayed", "Play video on usages details link is NOT displayed", true);
	        	}
	 				
			}else{
				Report.TestPoint(testContext.getName(), "Validate Billing and Usage page is displayed", "Billing and Usage page is displayed", "Billing and Usage page NOT is displayed", true);
			}
			
		} catch (Exception e) {
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - ValidateDefaultViewBillingPeriodSelectorDropDown()
	 * Description - This function is to validate default view of the billing period selector drop-down on usage tab.
	 * Test Case - USG08931  
	 * Parameters - None
	 * Date created - 3rd June 2015
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void ValidateDefaultViewBillingPeriodSelectorDropDown(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{  
		WebDriver lDriver = UI.getDriver(testContext.getName());
	    OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	    OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
	    
		try
		{
			
			Report.OperationPoint(testContext.getName(),"Click on Billing,Usage,Payments link from secondary navigation");
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
			
			if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut)){
				Report.ValidationPoint(testContext.getName(), "Validate Billing & Usages page is displayed", "Billing & Usages page is displayed", "Billing & Usages page is displayed", true);
			    
				Report.OperationPoint(testContext.getName(), "Click on Usage tab");
				oR_BillAndUsage.lnkUsage.click();
				
				if(UI.WaitForObject(oR_BillAndUsage.txtCurrentBilledUsage, UI.iObjTimeOut)){
				   Report.ValidationPoint(testContext.getName(), "Validate that Current Billing Usage option is selected by default as billing period", "Current Billing Usage option is selected by default as billing period", "Current Billing Usage option is selected by default as billing period", true);	
				}else{
		    		Report.ValidationPoint(testContext.getName(), "Validate that Current Billing Usage option is selected by default as billing period", "Current Billing Usage option is selected by default as billing period", "Current Billing Usage option is NOT selected by default as billing period", true);
				}
			}else{
				
				Report.ValidationPoint(testContext.getName(), "Validate Billing & Usages page is displayed", "Billing & Usages page is displayed", "Billing & Usages page is NOT displayed", true);
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
    }
	
	
	
	
	/**************************************************************
	 * Function Name - VerifyUnbilledUsage()
	 * Description - Verify unbilled usage on dashboard and usage landing page 
 	 * Test Case - USG06308
	 * Parameters - None
	 * Date created - 16th July 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyUnbilledUsage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			//Verify whether Data usage meter is present on Overview (Service Summary) page
			try
			{
				boolean bUsageBar = UI.WaitForObject(oR_AccountOverview.imgDataUsageBar, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Data Usage meter is NOT displayed", "false",String.valueOf(bUsageBar), true);
			}catch(Exception ee)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that Data Usage meter is NOT displayed", "true","true", true);
			}
			
			//Navigate to Bill & Usage page
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Billing usage and payments link from Sec nav");
			
			//Verify Billing & Usage page is displayed
			boolean bBillingPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
			Report.TestPoint(testContext.getName(), "Validate that BillAndUsage page is displayed", "true", String.valueOf(bBillingPage), true);
			
			oR_BillAndUsage.lnkUsage.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Usage tab");
			Thread.sleep(5000);
			
			//Select Recent usage from dropdown
			String sDefaultUsageSelection = null;
			try
			{
				sDefaultUsageSelection = oR_BillAndUsage.txtDefaultUsageBillSelection.getText();
				
			}catch(Exception Ee)
			{
				sDefaultUsageSelection = lDriver.findElement(By.xpath("//div[@id='ddShortcut']//p")).getText();
			}
			
			if(sDefaultUsageSelection.contains("Recent Usage"))
			{
				Report.OperationPoint(testContext.getName(), "Default Usage bill selection is:"+sDefaultUsageSelection);	
				
			}else
			{	
				Report.OperationPoint(testContext.getName(), "Default Usage bill selection is:"+sDefaultUsageSelection);
				//UI.SelectOptionFromDropDown(oR_BillAndUsage.btnUsageSelectionDropdown, "Recent Usage");
				new Actions(lDriver).moveToElement(oR_BillAndUsage.txtDefaultUsageBillSelection).click().moveToElement(oR_BillAndUsage.lnkRecentUsageDropdownOption).click().build().perform();
				Report.OperationPoint(testContext.getName(), "Recent Usage bill details is selected");
				Thread.sleep(5000);
			}
			
			String sUsageDetailsFor = oR_BillAndUsage.txtViewUsageForText.getText();
			if(sUsageDetailsFor.contains("MobileShare") || sUsageDetailsFor.contains("Family") || sUsageDetailsFor.contains("Mobile Share"))
			{
				//Validations are not written for Mobile share and FamilyTalk since data at the time of development didn't contain Mobile share and Family talk plans
				Report.OperationPoint(testContext.getName(), "Validations are not written for MobileShare and Family talk, Please contact Automation developer");
				
			}
			
			// Validate Additional usage section
			boolean bMobilePurchases = UI.WaitForObject(oR_BillAndUsage.txtMobilePurchases, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Mobile purchases section and Details are displayed", "true",String.valueOf(bMobilePurchases), true);
			
			//Verify Basic plan usage section
			boolean bBasicPlanUsageSection= UI.WaitForObject(oR_BillAndUsage.txtBasicPlanUsageSection, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Basic plan usage section is displayed", "true",String.valueOf(bBasicPlanUsageSection), true);
				
				//Verify Device image, Plan name, useful links
				WebElement wDeviceImage = lDriver.findElement(By.xpath("//a[@id='View_Usage_Details']//parent::span//parent::div//parent::div/div/img"));
				boolean bDeviceImage = UI.WaitForObject(wDeviceImage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Device image is displayed", "true",String.valueOf(bDeviceImage), true);
				
				WebElement wCustDetails = lDriver.findElement(By.xpath("//a[@id='View_Usage_Details']//parent::span//parent::div//parent::div/div/p"));
				boolean bCustDetails  = UI.WaitForObject(wCustDetails, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Customer details are displayed", "true",String.valueOf(bCustDetails), true);
				
				//Validation for alert is not done since Data does not contain any alerts
				
				
			//Verify view usage details link is displayed and verify the contents after clicking View details link
			boolean bUsageDetails = UI.WaitForObject(oR_BillAndUsage.lnkViewUsageDetails, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that view usage details link is displayed", "true",String.valueOf(bUsageDetails), true);
			oR_BillAndUsage.lnkViewUsageDetails.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'view usage details' page");
			
			String sMainWindow = lDriver.getWindowHandle();
			WebElement wFrame = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
			lDriver.switchTo().frame(wFrame);
				
				//Verify the elements inside the frame
				boolean bPrint = UI.WaitForObject(oR_BillAndUsage.btnPrintUsageBill, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Print icon is displayed", "true",String.valueOf(bPrint), true);
				boolean bDownload = UI.WaitForObject(oR_BillAndUsage.btnDownload, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Download icon is displayed", "true",String.valueOf(bDownload), true);
				
				boolean bUsageDetailsTitle = UI.WaitForObject(oR_BillAndUsage.txtUsageDetailsHeading, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Usage details title is displayed", "true",String.valueOf(bUsageDetailsTitle), true);
				
				//boolean bCurBillingPeriod = UI.WaitForObject(oR_BillAndUsage.btnChangeBillingPeriodDropdown, UI.iObjTimeOut, lDriver);
				//Report.ValidationPoint(testContext.getName(), "Verify that drop-down for Current billing period is displayed", "true",String.valueOf(bCurBillingPeriod), true);
				
				boolean bViewUsageFor = UI.WaitForObject(oR_BillAndUsage.btnViewUsageFor, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that drop-down for View usage for is displayed", "true",String.valueOf(bViewUsageFor), true);
				
				boolean bNickname = UI.WaitForObject(oR_BillAndUsage.radNickname, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Nickname is displayed as radio option", "true",String.valueOf(bNickname), true);
				boolean bShowNumbers = UI.WaitForObject(oR_BillAndUsage.radShowNumbers, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Numbers is displayed as radio option", "true",String.valueOf(bShowNumbers), true);
				
				//Verify Depending  Fields : Date/Time, Contact, Usage Type, Usage, Charges, Call time, Minutes and Location 
				boolean bDate = UI.WaitForObject(oR_BillAndUsage.txtDateAndTime, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Date/Time feild is displayed", "true",String.valueOf(bDate), true);
				boolean bContact = UI.WaitForObject(oR_BillAndUsage.txtContact, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Contact feild is displayed", "true",String.valueOf(bContact), true);
				boolean bLocation = UI.WaitForObject(oR_BillAndUsage.txtLocation, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Location feild is displayed", "true",String.valueOf(bLocation), true);
				boolean bCallType = UI.WaitForObject(oR_BillAndUsage.txtCallType, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Call Type feild is displayed", "true",String.valueOf(bCallType), true);
				boolean bMinutes = UI.WaitForObject(oR_BillAndUsage.txtMinutes, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Minutes feild is displayed", "true",String.valueOf(bMinutes), true);
				boolean bCharges = UI.WaitForObject(oR_BillAndUsage.txtCharges, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Charges feild is displayed", "true",String.valueOf(bCharges), true);
				
			lDriver.switchTo().window(sMainWindow);
			oR_BillAndUsage.imgClose.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on close button");
				
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - VerifyEmployerContributionNote()
	 * Description - Verify that Employer contribution Note is NOT displayed for Talk and Web usage
 	 * Test Case - USG07501
	 * Parameters - None
	 * Date created - 17th July 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyEmployerContributionNote(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			int flag=0;
			List<WebElement> MyPlansList = lDriver.findElements(By.xpath("//ul[@id='MainTab']/li/a"));
			int i=0;
			for (WebElement element: MyPlansList) 
			{
				element.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on Plan No: "+(i+1));
				
				//Verify under each plan that Employer contribution Note is NOT displayed
				try
				{
					boolean bEmpContriNote = UI.WaitForObject(oR_AccountOverview.txtEmployerContributionNote, UI.iObjTimeOut, lDriver);
					if(bEmpContriNote==false)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that Employer contribution note is NOT displayed for Talk and Web, For Plan No: "+(i+1), "true","true", true);
					
					}else if(bEmpContriNote==true)
					{
						Report.ValidationPoint(testContext.getName(), "Verify that Employer contribution note is NOT displayed for Talk and Web, For Plan No: "+(i+1), "true","false", true);
						flag=1; break;
					}
					
				}catch(Exception Ee)
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Employer contribution note is NOT displayed for Talk and Web Plan No: "+(i+1), "true","true", true);
				}
				i++;
			}
			
			if(flag==1)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that Employer contribution note is NOT displayed for Talk and Web", "true","false", true);
			}
			
			//Navigate to Bill & Usage page
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Billing usage and payments link from Sec nav");
			
			//Verify Billing & Usage page is displayed
			boolean bBillingPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
			Report.TestPoint(testContext.getName(), "Validate that BillAndUsage page is displayed", "true", String.valueOf(bBillingPage), true);
			
			oR_BillAndUsage.lnkUsage.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Usage tab");
			
			//Verify Employer contribution Note is NOT displayed
			try
			{
				
				boolean bEmpContriNoteInUsage = UI.WaitForObject(oR_BillAndUsage.txtEmployerContributionNote, UI.iObjTimeOut, lDriver);
				if(bEmpContriNoteInUsage==false)
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Employer contribution note is NOT displayed for Talk and Web", "true","true", true);
				
				}else if(bEmpContriNoteInUsage==true)
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Employer contribution note is NOT displayed for Talk and Web", "true","false", true);
				}
				
			}catch(Exception Ee)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that Employer contribution note is NOT displayed for Talk and Web", "true","true", true);
			}
			

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - VerifyUsageDownloadSection()
	 * Description - verify that the user is able to download usage detail records file in the CSV format
 	 * Test Case -  USG8972
	 * Parameters - None
	 * Date created - 20th July 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyUsageDownloadSection(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			//Navigate to Bill & Usage page
			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Billing usage and payments link from Sec nav");
			Thread.sleep(10000);
			
			//Verify Billing & Usage page is displayed
			boolean bBillingPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
			Report.TestPoint(testContext.getName(), "Validate that BillAndUsage page is displayed", "true", String.valueOf(bBillingPage), true);
			
			oR_BillAndUsage.lnkUsage.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Usage tab");
			Thread.sleep(5000);
			
			lDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Select Recent usage from dropdown
			String sDefaultUsageSelection = oR_BillAndUsage.txtDefaultUsageBillSelection.getText();
			if(sDefaultUsageSelection!="Recent Usage")
			{
				Report.OperationPoint(testContext.getName(), "Default Usage bill selection is:"+sDefaultUsageSelection);
				//UI.SelectOptionFromDropDown(oR_BillAndUsage.btnUsageSelectionDropdown, "Recent Usage");
				
				new Actions(lDriver).moveToElement(oR_BillAndUsage.txtDefaultUsageBillSelection).click().build().perform();
				Thread.sleep(1000);
				oR_BillAndUsage.lnkRecentUsageDropdownOption.click();
				Report.OperationPoint(testContext.getName(), "Operational - Selected Recent usage from dropdown");

				//boolean bRecent = UI.WaitForObject(oR_BillAndUsage.txtDefaultUsageBillSelection, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Recent Usage bill details is selected", "true","true", true);
				Report.OperationPoint(testContext.getName(), "Recent Usage bill details is selected");
			
				Thread.sleep(5000);
				
			}else if(sDefaultUsageSelection=="Recent Usage")
			{
				Report.OperationPoint(testContext.getName(), "Default Usage bill selection is:"+sDefaultUsageSelection);
			}
			
			//Verify Recent usage details are displayed
			boolean bRecent = UI.WaitForObject(oR_BillAndUsage.txtRecentUsageDetailsCheck, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Recent Usage details are displayed", "true",String.valueOf(bRecent), true);
			
			//Click on view details link
			List<WebElement> ViewUsageDetailsLink = lDriver.findElements(By.xpath("//a[contains(text(),'View usage details')]"));
			if(ViewUsageDetailsLink.size()!=0)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that View usage details link is displayed", "true","true", true);
				//click
				ViewUsageDetailsLink.get(0).click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on View usage details link");
				
				String sMainWindow = lDriver.getWindowHandle();
				WebElement wFrame = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
				lDriver.switchTo().frame(wFrame);
					
					//Verify Download dropdown
					boolean bDownload = UI.WaitForObject(oR_BillAndUsage.btnDownload, UI.iObjTimeOut, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify that Download drop-down is displayed", "true",String.valueOf(bDownload), true);
					//Verify that the user is able to see the following format options to download details: •CSV •Excel
					new Actions(lDriver).moveToElement(oR_BillAndUsage.btnDownload).click().build().perform();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on Download drop-down");
					new Actions(lDriver).moveToElement(oR_BillAndUsage.lnkCSV).build().perform();
					boolean bCSV = UI.WaitForObject(oR_BillAndUsage.lnkCSV, UI.iObjTimeOut, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify that user is able to see format option for CSV in download drop-down", "true",String.valueOf(bCSV), true);
					new Actions(lDriver).moveToElement(oR_BillAndUsage.lnkExcel).build().perform();
					boolean bExcel = UI.WaitForObject(oR_BillAndUsage.lnkExcel, UI.iObjTimeOut, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify that user is able to see format option for Excel in download drop-down", "true",String.valueOf(bExcel), true);
					
					
				lDriver.switchTo().window(sMainWindow);
				oR_BillAndUsage.imgClose.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on close button");
				
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that View usage details link is displayed", "true","false", true);
			}

				

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	

	/**************************************************************
	 * Function Name - verifyHomePhoneDetailsUnderUsageTab()
	 * Description - This function validates home phone details under usage tab
	 * Test Case - USG05745  
	 * Parameters - None
	 * Date created - 16th July 2015
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	
	public static void verifyHomePhoneDetailsUnderUsageTab(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
	    OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	    OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
	    
	    Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

		//Parameter declaration
		String sBillingPeriod = IO.GetParamVal(sTestParams, "Billing_Period");
		
		try

		{
			
			Report.OperationPoint(testContext.getName(),"Click on Billing,Usage,Payments link from secondary navigation");
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
			
			if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut)){
				Report.ValidationPoint(testContext.getName(), "Validate Billing & Usages page is displayed", "Billing & Usages page is displayed", "Billing & Usages page is displayed", true);

				Report.OperationPoint(testContext.getName(), "Click on Usage tab");
				oR_BillAndUsage.lnkUsage.click();

				try
				{
					WebElement lstBillingPeriodDropdown = (new WebDriverWait(lDriver, 240)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@name,'Usage')]")));

					if(lstBillingPeriodDropdown.isDisplayed())
					{
						Report.TestPoint(testContext.getName(), "Verify billing period dropdown is displayed", "Displayed", "Displayed", true);
						lstBillingPeriodDropdown.click();
						
						//Select billing period from dropdown
						Thread.sleep(5000);
						try
						{
							//Report.TestPoint(testContext.getName(), "Select billing period from dropdown", "Selecting", "Selecting", true);
							
							WebElement lnkBillingPeriod = lDriver.findElement(By.xpath("//a[contains(@title,'"+sBillingPeriod+"')]"));
							
							Actions action = new Actions(lDriver);
							action.moveToElement(lnkBillingPeriod).build().perform();
							Report.TestPoint(testContext.getName(), "Select billing period from dropdown", "Selected", "Selected", true);
							lnkBillingPeriod.click();
						}
						catch(Exception e)
						{
							Report.TestPoint(testContext.getName(), "Select billing period from dropdown", "Selected", "Provided billing period DOES NOT exist", true);
						}
						
						Thread.sleep(8000);
						//Validate Home phone text 
						try
						{
							lDriver.findElement(By.xpath("//*[text()='AT&T Home Phone']"));
							
							Report.ValidationPoint(testContext.getName(), "Validate Home phone text", "Displayed", "Displayed", true);
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(), "Validate Home phone text", "Displayed", "NOT Displayed", true);
						}
						
						//Validate details are displaying
						try 
						{
							if(lDriver.findElement(By.xpath("//*[text()='Details']")).isDisplayed() && lDriver.findElement(By.xpath("//*[contains(text(),'Date')]")).isDisplayed() && lDriver.findElement(By.xpath("//*[text()='Location']")).isDisplayed())
							{
								Report.ValidationPoint(testContext.getName(), "Validate Home phone details are displayed", "Displayed", "Displayed", true);
							}
							
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(), "Validate Home phone details are displayed", "Displayed", "NOT Displayed", true);
						}
						
						//Validate search field is displaying
						try 
						{
							if(lDriver.findElement(By.xpath("//*[contains(text(),'Search by:')]")).isDisplayed() && lDriver.findElement(By.xpath("//img[contains(@alt,'search')]")).isDisplayed())
							{
								Report.ValidationPoint(testContext.getName(), "Validate Search details and table for usage are displayed", "Displayed", "Displayed", true);
							}
							
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(), "Validate Search details and table for usage are displayed", "Displayed", "NOT Displayed", true);
						}
						
						
					}
					else
					{
						Report.TestPoint(testContext.getName(), "Verify billing period dropdown is displayed", "Displayed", "NOT Displayed", true);
					}
				}
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Verify billing period dropdown is displayed", "Displayed", "NOT Displayed", true);
				}

			}
			else
			{

				Report.ValidationPoint(testContext.getName(), "Validate Billing & Usages page is displayed", "Billing & Usages page is displayed", "Billing & Usages page is NOT displayed", true);
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
	}
	
	/**************************************************************
	 * Function Name - verifyUsageTabPieChart()
	 * Description - This function validates pie chart of usages in usage tab
	 * Test Case - USG08970 
	 * Parameters - None
	 * Date created - 3rd nov 15
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	
	public static void verifyUsageTabPieChart(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
	    OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	    OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
	    Actions aClick = new Actions(lDriver);
	    try
	    {
	    	//Redirect to billing and usage page
	    	UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
	    	//Validate Billing and usage page
	    	Boolean bBill = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, 20, lDriver);
	    	Report.TestPoint(testContext.getName(), "Verify Billing and usage page is displayed", "true", String.valueOf(bBill), true);
	    	//Click on usage tab
	    	Boolean bUSage = UI.WaitForObject(oR_BillAndUsage.lnkUsage, 20, lDriver);
	    	Report.TestPoint(testContext.getName(), "Verify usage tab is displayed", "true", String.valueOf(bUSage), true);
	    	Report.OperationPoint(testContext.getName(), "Click on Usage tab");
			oR_BillAndUsage.lnkUsage.click();
			Thread.sleep(10000);	
			//Validate view usage for:
			Boolean bViewUsageFor = UI.WaitForObject(oR_BillAndUsage.lstViewUsageFor, 15, lDriver);
			if(bViewUsageFor)
			{
				Report.OperationPoint(testContext.getName(), "Click on View usage for drop down");
				oR_BillAndUsage.lstViewUsageFor.click();
				Thread.sleep(3000);
				List<WebElement> lstShare = lDriver.findElements(By.xpath("//a[contains(text(),'Mobile Share 10GB')]"));
				//Boolean bShare = UI.WaitForObject(oR_BillAndUsage.lnkMobileShare., 5, lDriver);
				if(!lstShare.isEmpty())
				{
					WebElement lstShare1 = lstShare.get(0);
					aClick.moveToElement(lstShare1).build().perform();
					String sSharePlan = lstShare1.getText();
					Report.OperationPoint(testContext.getName(), "Click on : "+lstShare1.getText());
					lstShare1.click();
					
					//Validate Mobile share data plan section
					Boolean bMobileData = UI.WaitForObject(oR_BillAndUsage.txtMobileShare, 5, lDriver);
			    	Report.ValidationPoint(testContext.getName(), "Verify Mobile share data plan section is displayed", "true", String.valueOf(bMobileData), true);
			    	//Validate the selected plan is displayed in the data plan section
			    	if(sSharePlan.equals(oR_BillAndUsage.txtMobileShare.getText()))
			    	{
			    		Report.ValidationPoint(testContext.getName(), "Verify the selected plan is displayed in the data plan section", "true", "true", true);
			    	}
			    	else
			    	{
			    		Report.ValidationPoint(testContext.getName(), "Verify the selected plan is displayed in the data plan section", "true", "false", true);
			    	}
					//Validate Pie chart present in Mobile share data plan section
			    	Boolean bPieChart = UI.WaitForObject(oR_BillAndUsage.txtPieChart, 5, lDriver);
			    	Report.ValidationPoint(testContext.getName(), "Verify Pie chart present in Mobile share data plan section", "true", String.valueOf(bPieChart), true);
			    	//Validate that total amount of data usage in decimal is displayed for the CTN when rolled over a section of pie
			    	Boolean bDecimal = UI.WaitForObject(oR_BillAndUsage.txtDecimalUsage, 5, lDriver);
			    	Report.ValidationPoint(testContext.getName(), "Verify that total amount of data usage in decimal is displayed for the CTN when rolled over a section of pie", "true", String.valueOf(bDecimal), true);
			    	//Verify that total amount of data usage percentage is displayed for the CTN when rolled over a section of pie
			    	List<WebElement> lstPercentage = lDriver.findElements(By.xpath("//*[contains(text(),'%')]"));
			    	if(lstPercentage.size()>0)
			    	{
			    		Report.ValidationPoint(testContext.getName(), "Verify that total amount of data usage percentage is displayed for the CTN when rolled over a section of pie", "true", "true", true);
			    	}
			    	//Verify that total amount of data usage in decimal and the total data amount is displayed beneath the pie chart for the plan for which pie chart is displayed
			    	Boolean bMobileDataTable = UI.WaitForObject(oR_BillAndUsage.tblBasicUsagePlan2, 5, lDriver);
			    	Report.ValidationPoint(testContext.getName(), "Verify that total amount of data usage in decimal and the total data amount is displayed beneath the pie chart for the plan for which pie chart is displayed", "true", String.valueOf(bMobileDataTable), true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify Mobile Share 1GB with Unlimited Talk & Text link is displayed", "true", "false", true);
				}
			}
			else
			{
				//Validate Mobile share data plan section
				Boolean bMobileData = UI.WaitForObject(oR_BillAndUsage.txtMobileShare, 5, lDriver);
		    	Report.ValidationPoint(testContext.getName(), "Verify Mobile share data plan section is displayed", "true", String.valueOf(bMobileData), true);
				//Validate Pie chart present in Mobile share data plan section
		    	Boolean bPieChart = UI.WaitForObject(oR_BillAndUsage.txtPieChart, 5, lDriver);
		    	Report.ValidationPoint(testContext.getName(), "Verify Pie chart present in Mobile share data plan section", "true", String.valueOf(bPieChart), true);
		    	//Validate that total amount of data usage in decimal is displayed for the CTN when rolled over a section of pie
		    	Boolean bDecimal = UI.WaitForObject(oR_BillAndUsage.txtDecimalUsage, 5, lDriver);
		    	Report.ValidationPoint(testContext.getName(), "Verify that total amount of data usage in decimal is displayed for the CTN when rolled over a section of pie", "true", String.valueOf(bDecimal), true);
		    	//Verify that total amount of data usage percentage is displayed for the CTN when rolled over a section of pie
		    	List<WebElement> lstPercentage = lDriver.findElements(By.xpath("//*[contains(text(),'%')]"));
		    	if(lstPercentage.size()>0)
		    	{
		    		Report.ValidationPoint(testContext.getName(), "Verify that total amount of data usage percentage is displayed for the CTN when rolled over a section of pie", "true", "true", true);
		    	}
		    	//Verify that total amount of data usage in decimal and the total data amount is displayed beneath the pie chart for the plan for which pie chart is displayed
		    	Boolean bMobileDataTable = UI.WaitForObject(oR_BillAndUsage.tblBasicUsagePlan2, 5, lDriver);
		    	Report.ValidationPoint(testContext.getName(), "Verify that total amount of data usage in decimal and the total data amount is displayed beneath the pie chart for the plan for which pie chart is displayed", "true", String.valueOf(bMobileDataTable), true);
			}
	    	
	    	// Validate play video on usages details link is displayed
           	if(UI.WaitForObject(oR_BillAndUsage.lnkPlayVideoOnViewUsageDetails, UI.iObjTimeOut)){
				Report.ValidationPoint(testContext.getName(), "Validate play video on usages details link is displayed", "Play video on usages details link is displayed", "Play video on usages details link is displayed", true);
       			Report.OperationPoint(testContext.getName(), "Play video on usages details");
				oR_BillAndUsage.lnkPlayVideoOnViewUsageDetails.click();
				Thread.sleep(5000);
				
				if(UI.WaitForObject(oR_BillAndUsage.btnCloseOnViewWirelessUsageVideoWindow, UI.iObjTimeOut)){
					Report.ValidationPoint(testContext.getName(), "Validate video on usages details is displayed", "Video on usages details is displayed", "Video on usages details is displayed", true);
					oR_BillAndUsage.btnCloseOnViewWirelessUsageVideoWindow.click();
					Thread.sleep(5000);
				}else{
					Report.ValidationPoint(testContext.getName(), "Validate video on usages details is displayed", "Video on usages details is displayed", "Video on usages details is NOT displayed", true);
				}
				
        	}else{
        		Report.ValidationPoint(testContext.getName(), "Validate play video on usages details link is displayed", "Play video on usages details link is displayed", "Play video on usages details link is NOT displayed", true);
        	}
	    }
	    catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}

	/**************************************************************
	 * Function Name - verifyMessageForPassportAddOn()
	 * Description - This function validates message for passport add on after hovering over help icons
	 * Test Case - USG23003 
	 * Parameters - None
	 * Date created - 20th nov 15
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/

	public static void verifyMessageForPassportAddOn(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
		    OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		    OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		    
		    Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			//Parameter declaration
			String sBillingPeriod = IO.GetParamVal(sTestParams, "Billing_Period");

		    UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkUsageSinceLastBillTertNav, lDriver);
			Report.OperationPoint(testContext.getName(), "Operational - Navigating to Usage Since Last Bill page");
			
			//Verify navigation to 'Billing and Usage' page
			Boolean txtBillingAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
	    	Report.TestPoint(testContext.getName(), "Verify navigation to 'Billing and Usage' page", "true", String.valueOf(txtBillingAndUsage), true);
	    	
			//Select Recent usage from dropdown
			Boolean lstShowing = UI.WaitForObject(oR_BillAndUsage.lstShowing, 10, lDriver);
	    	Report.TestPoint(testContext.getName(), "Verify usage dropdown is displayed", "true", String.valueOf(lstShowing), true);
	    	
	    	oR_BillAndUsage.lstShowing.click();
	    	Actions action = new Actions(lDriver);
	    	
	    	try
	    	{
	    		action.moveToElement(oR_BillAndUsage.lnkRecentBill).build().perform();
	    		Report.OperationPoint(testContext.getName(), "Operational - Selecting recent usage from usage dropdown");
	    		oR_BillAndUsage.lnkRecentBill.click();
	    		
	    		Report.TestPoint(testContext.getName(), "Select Recent usage from dropdown", "Selected", "Selected", true);
	    	}
	    	catch(Exception e)
	    	{
	    		Report.TestPoint(testContext.getName(), "Select Recent usage from dropdown", "Selected", "FAILED to Select", true);
	    	}
	   
	    	//Verify 'AT&T Passport Data' and 'AT&T Passport Text' details
	    	try
	    	{
	    		lDriver.findElement(By.xpath("//*[contains(@class,'ColLeft')]//*[text()='AT&T Passport Text']"));
	    		lDriver.findElement(By.xpath("//*[contains(@class,'ColLeft')]//*[text()='AT&T Passport Data']"));
	    		Report.TestPoint(testContext.getName(), "Verify 'AT&T Passport Data' and 'AT&T Passport Text' details", "Displayed", "Displayed", true);
	    	}
	    	catch(Exception e)
	    	{
	    		Report.TestPoint(testContext.getName(), "Verify 'AT&T Passport Data' and 'AT&T Passport Text' details", "Displayed", "NOT Displayed", true);
	    	}
	    	
	    	//Expand all 'View More' buttons
	    	List<WebElement> lstViewMore = lDriver.findElements(By.xpath("//img[@alt='Show more']"));
	    	try
	    	{
	    		for(int i=0;i<lstViewMore.size();i++)
	    		{
	    			lstViewMore.get(i).click();
	    		}
	    		Report.TestPoint(testContext.getName(), "Expand all 'View More' buttons", "Expanded", "Expanded", true);
	    	}
	    	catch(Exception e)
	    	{
	    		Report.TestPoint(testContext.getName(), "Expand all 'View More' buttons", "Expanded", "NOT Expanded", true);
	    	}
	    	
	    	//Verify tool tip help icons in front of AT&T passport plans
	    	List<WebElement> lstHelpIcons = lDriver.findElements(By.xpath("//*[contains(text(),'AT&T Passport')]//img[@alt='help']"));
	    	
	    	//Verify AT&T Passport text is displayed after hovering over help icon
	    	for(int i=0;i<lstHelpIcons.size();i++)
	    	{
	    		action.moveToElement(lstHelpIcons.get(i)).build().perform();
	    		Thread.sleep(5000);
	    		
	    		try
	    		{
	    			if(lDriver.findElement(By.xpath("//p[contains(text(),'The AT&T Passport provides discounted rates')]")).isDisplayed())
	    			{
	    				Report.TestPoint(testContext.getName(), "Verify AT&T Passport text is displayed after hovering over help icon "+(i+1), "displayed", "displayed", true);
	    			}
	    			else
	    			{
	    				Report.TestPoint(testContext.getName(), "Verify AT&T Passport text is displayed after hovering over help icon "+(i+1), "displayed", "displayed", true);
	    			}
	    		}
	    		catch(Exception e)
	    		{
	    			Report.TestPoint(testContext.getName(), "Verify AT&T Passport text is displayed after hovering over help icon"+(i+1), "displayed", "NOT displayed", true);
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
	 * Function Name - ValidateUsageTableForTurnDataOnOFFSwitch()
	 * Description - Validate Usage Table on Usage landing page for "Turn data on/off" switch.
 	 * Test Case - USG05121
	 * Parameters - None
	 * Date created -15th Dec 2015
	 * Developed by - Hiral Jogi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//USG05121
	public static void ValidateUsageTableForTurnDataOnOFFSwitch(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		

			Boolean bViewAllUsage = UI.WaitForObject(oR_AccountOverview.lnkViewAllUsage, UI.iObjTimeOut, lDriver);
	    	Report.TestPoint(testContext.getName(), "Verify 'View all Usage' link is seen.", "true", String.valueOf(bViewAllUsage), true);
	    	if(bViewAllUsage){
	    		Report.TestPoint(testContext.getName(), "Clicking on 'View all Usage' link.", "true", String.valueOf(bViewAllUsage), true);
	    		oR_AccountOverview.lnkViewAllUsage.click();
	    	}
	    	
	    	// Validations on Billing and Usage page
	    	Boolean bBillingAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
	    	Report.TestPoint(testContext.getName(), "Verify navigation to Billing & Usage Page.", "true", String.valueOf(bBillingAndUsage), true);
			
	    	// Validating usage table
	    	Boolean bBasicPlanUsage = UI.WaitForObject(oR_BillAndUsage.txtBasicPlanUsage, UI.iObjTimeOut, lDriver);
	    	if(bBasicPlanUsage){
	    		Report.TestPoint(testContext.getName(), "Basic Plan Usage table is seen.", "true", String.valueOf(bViewAllUsage), true);
	    	}
	    	// Validating "Turn data on/off" switch under Data. 
	    	Boolean bSwitch = UI.WaitForObject(oR_BillAndUsage.lnkSwitchTurnOn, UI.iObjTimeOut, lDriver);
	    	if(bSwitch){
	    		Report.TestPoint(testContext.getName(), "Clicking on 'Turn data on/off' switch under Data.", "true", String.valueOf(bSwitch), true);
	    		oR_BillAndUsage.lnkSwitchTurnOn.click();
	    	}
	    	
	    	// Validating Turn Usage On/Off page
  
	    		Boolean bSubmit = UI.WaitForObject(oR_BillAndUsage.btnSubmitDisabled, UI.iObjTimeOut, lDriver);
	    		if(bSubmit){
	    			 Report.TestPoint(testContext.getName(), "The data usage switch is in 'ON' State.", "true", String.valueOf(bSubmit), true);
	    		}
	    		
	    		// turning it OFF
	    		Boolean bSwitchOFF = UI.WaitForObject(lDriver.findElement(By.xpath("//input[@title='ardio2']")), UI.iObjTimeOut, lDriver);
		    	if(bSwitchOFF){
		    		Report.TestPoint(testContext.getName(), "Turning the data usage switch OFF", "true", String.valueOf(bSwitchOFF), true);
		    		lDriver.findElement(By.xpath("//input[@title='ardio2']")).click();
		    	}
	    		
		    	// Validating if the Submit button is activated or not
		    	Boolean bSubmit1 = UI.WaitForObject(oR_BillAndUsage.btnSubmit, UI.iObjTimeOut, lDriver);
	    		if(bSubmit1){
	    			 Report.TestPoint(testContext.getName(), "The Submit button is now enabled.", "true", String.valueOf(bSubmit1), true);
	    		}
	    	
	    		
	    	
	    	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - ValidateSuppressDataOnOFFForDataOptOutSlid()
	 * Description - Validate Suppress Turn data on-off link and data usage for Data Opt Out.
 	 * Test Case - USG23024
	 * Parameters - None
	 * Date created -16th Dec 2015
	 * Developed by - Hiral Jogi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//USG23024 data : (214) 240-8996
	public static void ValidateSuppressDataOnOFFForDataOptOutSlid(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Actions builder = new Actions(lDriver);
			
			// Navigating to billing&Usage page
			Report.OperationPoint(testContext.getName(),"Click on Billing,Usage,Payments link from secondary navigation");
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, null, lDriver);
			
			Boolean bBlillingAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
    		if(bBlillingAndUsage){
    			 Report.TestPoint(testContext.getName(), "User is navigated to Billing and Usage page.", "true", String.valueOf(bBlillingAndUsage), true);
    		}
			
	    	// Clicking on Usage tab
    		Boolean bUsage = UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut, lDriver);
    		if(bUsage){
    			 Report.TestPoint(testContext.getName(), "Clicking on Usage tab under Billing and Usage page.", "true", String.valueOf(bUsage), true);
    			 oR_BillAndUsage.lnkUsage.click();
    		}
    		
    		// Checking for basic plan data availability
    		Boolean btblBasicPlanData = UI.WaitForObject(oR_BillAndUsage.tblBasicPlanData, UI.iObjTimeOut, lDriver);
    		if(btblBasicPlanData){
    			 Report.TestPoint(testContext.getName(), "Basic plan data is seen as below.", "true", String.valueOf(btblBasicPlanData), true);
    			 String str = oR_BillAndUsage.tblBasicPlanData.getText();
    			 System.out.println(str);
    		}
    		
    		// checking for data blank section
    		Boolean bData = UI.WaitForObject(lDriver.findElement(By.xpath("//span[@class='font18 colorGreyCCO']")), UI.iObjTimeOut, lDriver);
	    	if(bData){
	    		Report.TestPoint(testContext.getName(), "Validating data section", "true", String.valueOf(bData), true);
	    		String str = lDriver.findElement(By.xpath("//span[@class='font18 colorGreyCCO']")).getText();
	    		if(str.contains("--")){
	    			Report.TestPoint(testContext.getName(), "Data Usage is greyed out .", "true", String.valueOf(bBlillingAndUsage), true);
	    		}
	    	}
	    	
	    	// Validating ToolTip1
	    	WebElement Tooltip1 = lDriver.findElement(By.xpath("//div[contains(text(),'Nights and Weekend Minutes')]//img[@alt='help']"));
	    	builder.moveToElement(Tooltip1).build().perform();
	    //	String sToolTipText1 = lDriver.findElement(By.xpath("//div[@id='tipTalkNightsWeekends0']")).getText(); // Get the value of Tooltip by getAttribute command
	    	Report.TestPoint(testContext.getName(), "Nights and Weekend Minutes Tooltip is seen with required content. ", "true", "true", true);
	   
	    	// Validating ToolTip1
	    	WebElement Tooltip2 = lDriver.findElement(By.xpath("//div[contains(text(),'Mobile to Mobile Minutes')]//img[@alt='help']"));
	    	builder.moveToElement(Tooltip2).build().perform();
	    //	String sToolTipText2 = lDriver.findElement(By.xpath("//div[@id='tipTalkMobileToMobile1']")).getText(); // Get the value of Tooltip by getAttribute command
	    	Report.TestPoint(testContext.getName(), "Mobile to Mobile Minutes Tooltip is seen with required content. ", "true", "true", true);
	   	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	/**************************************************************
	 * Function Name - ValidateCancelledMEssage()
	 * Description - 
 	 * Test Case -USG08911
	 * Parameters - None
	 * Date created -28th Jan 2016
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//USG08911
	public static void ValidateCancelledMEssage(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Actions builder = new Actions(lDriver);
			
			//navigate to BAP page
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,null,lDriver);
			
			//
			boolean bBilling=UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Validate Billing page is displayed","true",String.valueOf(bBilling),true);
			
			//navigate to usage tab
			boolean bUsage=UI.WaitForObject(oR_BillAndUsage.lnkUsage,20, lDriver);
			oR_BillAndUsage.lnkUsage.click();
			Report.ValidationPoint(testContext.getName(),"Validate Usage page is displayed","true",String.valueOf(bBilling),true);
			
			//
		try
			{
				WebElement Note= lDriver.findElement(By.xpath("//*[contains(@class,'msg box')]"));
				Report.OperationPoint(testContext.getName(), "Note displayed on usage tab is: "+ Note.getText());
				Report.ValidationPoint(testContext.getName(),"Validate usage is displayed if available","Appropriate content is available","Appropriate content is available",true);
			}
		catch(Exception e)
		{
			Report.ValidationPoint(testContext.getName(),"Validate usage is displayed if available","Appropriate content is available","Appropriate content is not available",true);
		}
			
		//
		try
		{
			WebElement Error= lDriver.findElement(By.xpath("//*[contains(@class,'ErrorMsg')]"));
			Report.OperationPoint(testContext.getName(), "Error displayed on usage tab is: "+ Error.getText());
			Report.ValidationPoint(testContext.getName(),"Validate message with appropriate support is displayed in usage tab","Appropriate content is available","Appropriate content is available",true);
		}
	catch(Exception e)
	{
		Report.ValidationPoint(testContext.getName(),"Validate message with appropriate support is displayed in usage tab","Appropriate content is available","Appropriate content is not available",true);
	}
			
	//verify outstanding balance
		try
		{
			WebElement Balance= lDriver.findElement(By.xpath("//*[contains(@class,'POD-title')]//span"));
			Report.OperationPoint(testContext.getName(), "Balance displayed on usage tab is: "+ Balance.getText());
			if(Balance.getText().contains("$0.00"))
			Report.ValidationPoint(testContext.getName(),"Validate account does not include balance amount","true","true",true);
		}
	catch(Exception e)
	{
		Report.ValidationPoint(testContext.getName(),"Validate account does not include balance amount","true","false",true);
	}
			
	}
catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
		
	}	
	
	/**************************************************************
	 * Function Name - ValidateSwitchOrderofTalk_Text_Data()
	 * Description -  Validate Switch Order of Talk_Text_Data for wireless on new dashboard
 	 * Test Case - UAT_UUSG0324
	 * Parameters - None
	 * Date created -5th Jan 2016
	 * Developed by - Hiral Jogi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_UUSG0324
	public static void ValidateSwitchOrderofTalk_Text_Data(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			// Step 2
			Boolean btWelcome = UI.WaitForObject(oR_AccountOverview.txtWelcome, UI.iObjTimeOut);
			String strwelcome = oR_AccountOverview.txtWelcome.getText();
			Report.ValidationPoint(testContext.getName(), strwelcome + " string is present.", "True", String.valueOf(btWelcome), true);
			
			// Validating Data
			Boolean bDataUsageBar = UI.WaitForObject(oR_AccountOverview.imgDataUsageBar, UI.iObjTimeOut);
			if(bDataUsageBar){
				Report.ValidationPoint(testContext.getName(),"Data Summary section is seen.", "True", String.valueOf(bDataUsageBar), true);
				String strData = oR_AccountOverview.imgDataUsageBar.getText();
				Report.OperationPoint(testContext.getName(), "Data summary is seen as ----- " + strData);
			}	
				int xData = oR_AccountOverview.imgDataUsageBar.getLocation().getX();
				System.out.println("x co -ordinate is --- " + xData);
				int yData = oR_AccountOverview.imgDataUsageBar.getLocation().getY();
				System.out.println("y co -ordinate is --- " + yData);
			
			
			// Validating Text
			Boolean bTextUsageBar = UI.WaitForObject(oR_AccountOverview.imgTextUsageBar, UI.iObjTimeOut);
			if(bTextUsageBar){
				Report.ValidationPoint(testContext.getName(),"Text Summary section is seen.", "True", String.valueOf(bTextUsageBar), true);
				String strText = oR_AccountOverview.imgTextUsageBar.getText();
				Report.OperationPoint(testContext.getName(), "Text summary is seen as ----- " + strText);
			}
				int xText = oR_AccountOverview.imgTextUsageBar.getLocation().getX();
				System.out.println("x co -ordinate is --- " + xText);
				int yText = oR_AccountOverview.imgTextUsageBar.getLocation().getY();
				System.out.println("y co -ordinate is --- " + yText);
			
			
			// Validating Text
			Boolean bTalkUsageBar = UI.WaitForObject(oR_AccountOverview.imgTalkUsageBar, UI.iObjTimeOut);
			if(bTalkUsageBar){
				Report.ValidationPoint(testContext.getName(),"Talk Summary section is seen.", "True", String.valueOf(bTalkUsageBar), true);
				String strTalk = oR_AccountOverview.imgTalkUsageBar.getText();
				Report.OperationPoint(testContext.getName(), "Talk summary is seen as ----- " + strTalk);
			}
				int xTalk = oR_AccountOverview.imgTalkUsageBar.getLocation().getX();
				System.out.println("x co -ordinate is --- " + xTalk);
				int yTalk = oR_AccountOverview.imgTalkUsageBar.getLocation().getY();
				System.out.println("y co -ordinate is --- " + yTalk);
			
			
			if(xData < xText){
				Report.ValidationPoint(testContext.getName(),"Data is seen prior to Text which is the expected order.", "True", "True" , true);
			} else {
				Report.ValidationPoint(testContext.getName(),"Data is seen prior to Text which is the expected order.", "True", "False" , true);
			}
			
			if(yText < xTalk){
				Report.ValidationPoint(testContext.getName(),"Text is seen prior to Talk which is the expected order.", "True", "True" , true);
			} else {
				Report.ValidationPoint(testContext.getName(),"Text is seen prior to Talk which is the expected order.", "True", "False" , true);
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - ValidateSwitchOrderofTalk_Text_Data()
	 * Description -  Wireless with usage__RD2115 Identify type of Plan for Usage Reports
 	 * Test Case - USG06277 
	 * Parameters - None
	 * Date created -13th Jan 2016
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//USG06277
	public static void VerifyWirelessWithUsageRD2115(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
		try
		{
			Report.OperationPoint(testContext.getName(), "Navigate to Billing&Usage-> Reports");
	
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkBillReports);
			
			if(UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver)){
				
				     	WebElement startBillingDate = lDriver.findElement(By.xpath("//*[@id='ddShortcut1']/div"));
				    	WebElement endBillingDate = lDriver.findElement(By.xpath("//*[@id='ddShortcut2']/div"));
						
						List<WebElement> reportTypesList = lDriver.findElements(By.xpath("//*[@id='ddShortcutBox']//dd[@class='opt botMar7']"));
						WebElement dataUsageReportType = null;		
						Report.OperationPoint(testContext.getName(), "Click on Select Report drop down");
						oR_BillAndUsage.lstSelectReportType.click();
					   	for(WebElement e : reportTypesList){
					           WebElement reportType = e.findElement(By.tagName("a"));
						       UI.WaitForObject(e, 5);
						       if(reportType.getText().contains("Data Usage")){
						    	   Report.ValidationPoint(testContext.getName(), "Validate Data Usage report type is available", "True", "True", true);
						    	   dataUsageReportType = reportType;	 
						       }					 
					   	 } 
					   	
					   	if(dataUsageReportType != null){
					   		Report.OperationPoint(testContext.getName(), "Select Data Usage report type from drop down");
					   		dataUsageReportType.click();
					   		
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
							
							List<WebElement> dataLables = lDriver.findElements(By.cssSelector("g.highcharts-data-labels"));
							System.out.println("Size :" + dataLables.size());
							boolean flag = false;

							for(WebElement e : dataLables){
								WebElement dataLable = e.findElement(By.tagName("tspan"));
							    System.out.println(dataLable.getText());
								if(dataLable.getText().contains("0.1")){
									flag = true;
								    break;
								}					
							}
							
							if(flag){
							   Report.ValidationPoint(testContext.getName(), "Verify that When the greatest amount of usage for the range selected by the customer has 1 GB or more of usage,  the usage must be displayed to the tenths (1 place past the decimal point.) Example:  0.1 ", "True", "True", true);
							}else{
							   Report.ValidationPoint(testContext.getName(), "Verify that When the greatest amount of usage for the range selected by the customer has 1 GB or more of usage,  the usage must be displayed to the tenths (1 place past the decimal point.) Example:  0.1 ", "True", "False", true);
							}
					   		
					   	}else{
					   		Report.TestPoint(testContext.getName(), "Validate Data Usage report type is available", "True", "False", true);
					   	}
					
			}else{
				
				Report.ValidationPoint(testContext.getName(), "Validate user is navigated to Billing and Usage page", "True", "False", true);
			}
			
		 }catch (Exception e) {
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		 }
			
	}
	/**************************************************************
	 * Function Name - ValidateUsageTabForSingleLineFT()
	 * Description -  Validates Usage Tab For Single Line FT
 	 * Test Case - UAT_UUSG0116a
	 * Parameters - None
	 * Date created -12th Feb 2016
	 * Developed by - Hiral Jogi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_UUSG0116a
	public static void ValidateUsageTabForSingleLineFT(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			
			// Navigate to BAP page
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,null,lDriver);
			
			// Validate Billing page
			boolean bBilling=UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,20, lDriver);
			Report.ValidationPoint(testContext.getName(),"Validate Billing page is displayed","true",String.valueOf(bBilling),true);
			
			// Navigate to usage tab
			boolean bUsage = UI.WaitForObject(oR_BillAndUsage.lnkUsage,20, lDriver);
			Report.OperationPoint(testContext.getName(), "Clicking on Usage Tab.");
			oR_BillAndUsage.lnkUsage.click();
			Thread.sleep(10000);
			
			WebElement UsagePage = lDriver.findElement(By.xpath("//h2/strong[contains(text(),'Showing')]"));
			if(UsagePage.isDisplayed()){
				Report.ValidationPoint(testContext.getName(),"Validate Usage page is displayed.","true","true",true);
				
				// Validate Pie chart should not be present
		    	UI.VerifyElementNotPresent(oR_BillAndUsage.txtPieChart, "Pie Chart");
		    	
		    	// Validate that total amount of data usage in decimal and the total data amount table is not displayed
		    	UI.VerifyElementNotPresent(oR_BillAndUsage.tblBasicUsagePlan2, "Basic Plan Usage Table");
		    	
			} else {
				Report.ValidationPoint(testContext.getName(),"Validate Usage page is displayed.","true","false",true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name - ValidateEnablerEventAggregationNote()
	 * Description -  Validates 
 	 * Test Case - UUSG0113 - Details Modal - MS - Enabler Event Aggregation Note
	 * Parameters - None
	 * Date created -19th Feb 2016
	 * Developed by -Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_UUSG0113
	public static void ValidateEnablerEventAggregationNote(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Actions action = new Actions(lDriver);
			Boolean bViewDetailsDropDown, bShowDropDown, bNote;
			
			
			
		    Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			String sDataType = IO.GetParamVal(sTestParams, "DataType");
			if(sDataType.contains("MobileShareAccount"))
				Report.OperationPoint(testContext.getName(), "Login using a mobile share account where no recent usage exists. ");
			// Navigate to BAP page
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,null,lDriver);
		
			// Navigate to usage tab
			boolean bUsage = UI.WaitForObject(oR_BillAndUsage.lnkUsage,20, lDriver);
			Report.OperationPoint(testContext.getName(), "Clicking on Usage Tab.");
			oR_BillAndUsage.lnkUsage.click();
			Thread.sleep(5000);
			
			if(UI.WaitForObject(oR_BillAndUsage.lnkViewUsageDetails, 5))
				oR_BillAndUsage.lnkViewUsageDetails.click();
			else if(UI.WaitForObject(oR_BillAndUsage.lnkViewUsageDetails1, 5))
				{
					oR_BillAndUsage.lnkViewUsageDetails1.click();	
					Thread.sleep(5000);
					lDriver.switchTo().frame(oR_BillAndUsage.frmViewUsageDetails);
					bNote = UI.WaitForObject(oR_BillAndUsage.txtAggregationNote, 10, lDriver);
//					Report.ValidationPoint(testContext.getName(), "Validate the Enabler Event Aggregation note is presented at the bottom of the Usage Details modal", "true", bNote.toString(), true);
					if(!bNote)
					{
						if(sDataType.contains("MobileShareAccount"));
						else
						{
	//						lDriver.switchTo().defaultContent();
							lDriver.switchTo().parentFrame();
							oR_BillAndUsage.imgCloseModal.click();
							
							if(UI.WaitForObject(oR_BillAndUsage.lnkViewUsageDetails2, 5))
								oR_BillAndUsage.lnkViewUsageDetails2.click();
							if(UI.WaitForObject(oR_BillAndUsage.frmViewUsageDetails, 10, lDriver))
							{	
								Report.TestPoint(testContext.getName(), "1-Validate the Usage Details modal is displayed", "true", "true", true);
								lDriver.switchTo().frame(oR_BillAndUsage.frmViewUsageDetails);
							}	
							else
								Report.TestPoint(testContext.getName(), "1-Validate the Usage Details modal is displayed", "true", "false", true);
						}
					}					
				}
			
			
			if(UI.WaitForObject(oR_BillAndUsage.txtPresentBillingPeriod, 10, lDriver));
			else
			{
//					From the 'Billing Period' drop down choose  Recent Usage. 
				if(UI.WaitForObject(oR_BillAndUsage.lstChangeBiilingPeriodDropDown, 10, lDriver))
				{
					action.moveToElement(oR_BillAndUsage.lstChangeBiilingPeriodDropDown).click().build().perform();
					
//						oR_BillAndUsage.lstChangeBiilingPeriodDropDown.click();
					Thread.sleep(2000);
					if(UI.WaitForObject(oR_BillAndUsage.lnkRecentUsageFromDropDown, 10, lDriver))
					{
						Report.ValidationPoint(testContext.getName(), "Select Recent Usage from Billing Period Dropdown", "true", "true", true);
						oR_BillAndUsage.lnkRecentUsageFromDropDown.click();
						//action.moveToElement(oR_BillAndUsage.lstChangeBiilingPeriodDropDown).build().perform();
						Thread.sleep(5000);
						
						
						if(sDataType.contains("MobileShareAccount"))
						{
							bNote = UI.WaitForObject(oR_BillAndUsage.txtAggregationNote, 1, lDriver);
							Report.ValidationPoint(testContext.getName(), "4-Validate the note is suppressed when no usage exists. ", "false", bNote.toString(), true);
						
							lDriver.switchTo().parentFrame();
							try
							{
								oR_BillAndUsage.imgCloseModal.click();
							}
							catch(Exception e)
							{
								Report.ValidationPoint(testContext.getName(), "Close the modal", "pass", "Fail", true);
							}
							return;
						
						}
//							action.moveToElement(oR_BillAndUsage.lnkRecentUsageFromDropDown).click().perform();
//							action.moveToElement(oR_BillAndUsage.lstChangeBiilingPeriodDropDown).click().moveToElement(oR_BillAndUsage.lnkRecentUsageFromDropDown).click().perform();

					}
					else
						Report.TestPoint(testContext.getName(), "Recent Usage Not present in Billing Period Dropdown", "true", "false", true);
				}	
			}
//					From the 'View usage for' drop down, choose  Text/Data.  
//			action.moveToElement(oR_BillAndUsage.lnkViewUsageBy).build().perform();

			bViewDetailsDropDown = UI.WaitForObject(oR_BillAndUsage.lstViewUsageFor, 10, lDriver);
			if(bViewDetailsDropDown)
			{
				action.moveToElement(oR_BillAndUsage.lnkViewUsageBy).click().build().perform();
				Thread.sleep(1000);
				oR_BillAndUsage.lnkTextOrData.click();
				
			}
			else
				Report.TestPoint(testContext.getName(), "2-From the 'View usage for' drop down, choose  Text/Data.  ", "true", "false", true);
//					Then from the 'Show' choose 'All'
			bShowDropDown = UI.WaitForObject(oR_BillAndUsage.lstShow, 10, lDriver);
			if(bShowDropDown)
			{
				action.moveToElement(oR_BillAndUsage.lstShow).click().build().perform();
				oR_BillAndUsage.lnkAll.click();
				
			}
			else
				Report.TestPoint(testContext.getName(), "2-Then from the 'Show' choose 'All'", "true", "false", true);

//					"Validate the Enabler Event Aggregation note is presented at the bottom of the Usage Details modal: 

//					 - Note: Each total shown for data usage is the total amount of data used during a 3 hour interval. "
			Thread.sleep(4000);
			bNote = UI.WaitForObject(oR_BillAndUsage.txtAggregationNote, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate the Enabler Event Aggregation note is presented at the bottom of the Usage Details modal", "true", bNote.toString(), true);

//					Using the 'Show' drop down, toggle from 'All' to 'Internet/mobile data'
	
			action.moveToElement(oR_BillAndUsage.lstShow).click().build().perform();
			oR_BillAndUsage.lnkInternetOrMobileData.click();
			Report.ValidationPoint(testContext.getName(), "3-Using the 'Show' drop down, toggle from 'All' to 'Internet/mobile data'", "true", bNote.toString(), true);
			Thread.sleep(4000);
			
			bNote = UI.WaitForObject(oR_BillAndUsage.txtAggregationNote, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate the Enabler Event Aggregation note is presented at the bottom of the Usage Details modal", "true", bNote.toString(), true);

			lDriver.switchTo().parentFrame();
			try
			{
				oR_BillAndUsage.imgCloseModal.click();
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Close the modal", "pass", "Fail", true);
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	
	
	/**************************************************************
	 * Function Name - VerifyWirelessDashboardServiceSummary()
	 * Description -
 	 * Test Case - UUSG0267 - Wireless Dashboard New - Service Summary - FT 
	 * Parameters - None
	 * Date created - 23rd Feb 2016
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_UUSG0267
	public static void VerifyWirelessDashboardServiceSummary(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			//Confirm the following is displayed in the Service Summary:
			//1.1.'View all usage' link
			boolean bViewAllUsage = UI.WaitForObject(oR_AccountOverview.lnkViewAllUsage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that 'View all usage' link is displayed", "true",String.valueOf(bViewAllUsage), true);
			//1.2.  Device image for each CTN (If not available, default image)
			List<WebElement> DeviceImages = lDriver.findElements(By.xpath("//div[@id='phoneItemContainer']//img[@alt!='Add Phone']"));
			int iDeviceNum = DeviceImages.size();
			if(iDeviceNum>0)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that  Device image for each CTN/Default image is displayed", "true","true", true);

			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that  Device image for each CTN/Default image is displayed", "true","false", true);
			}
			
			//1.3 - CTN for each device
			boolean bCTN = UI.WaitForObject(oR_AccountOverview.txtSubscriberCTN, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that CTN for each device is displayed", "true",String.valueOf(bCTN), true);
			//1.4 - Subscriber first name
			boolean bFirstName = UI.WaitForObject(oR_AccountOverview.txtSubscriberFirstName, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Subscriber first name is displayed", "true",String.valueOf(bFirstName), true);
			//1.5  - 'View details' link
			boolean bViewDetails = UI.WaitForObject(oR_AccountOverview.lnkViewDetailsUnderUsageContainer, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that View details is displayed", "true",String.valueOf(bViewDetails), true);
			
			//2. Click 'View all usage' link, Validate the user is directed to the Usage landing page with the Shared tab in focus.
			oR_AccountOverview.lnkViewAllUsage.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'View all usage' link");
			Thread.sleep(2000);
			boolean bBillAndUsage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify user is redirected to Billing & Usage page", "true",String.valueOf(bBillAndUsage), true);
			String sTabPreSelected = oR_BillAndUsage.lnkBillTabPreSelected.getText().toString();
			if(sTabPreSelected.contains("Usage"))
			{
				Report.ValidationPoint(testContext.getName(), "Verify 'Usage' tab is preselcted", "true","true", true);
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify 'Usage' tab is preselcted", "true","false", true);
			}
			
			// - Navigate back to the previous page to continue the set.
			oR_AccountOverview.lnkOverview.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Overview' from Sec Nav");
			Thread.sleep(5000);
			boolean bAccPage = UI.WaitForObject(oR_AccountOverview.txtWelcome, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Overview page is displayed", "true",String.valueOf(bAccPage), true);
			/*
			 *3.1 Confirm they are displayed in alphabetical order by the first name.
				 - If there are multiple devices with the same first name, they will display in order of CTN.
				 - If there is no first name present, they will display with CTN first in numerical order.
			 */
			
			if(iDeviceNum==0)
			{
				Report.OperationPoint(testContext.getName(), "Review order of devices - *No devices are displayed to review the order");
			}else if(iDeviceNum==1)
			{
				Report.ValidationPoint(testContext.getName(), "Review order of devices - Only 1 device is displayed, Device placed as per BAU", "true","true", true);

			}else if(iDeviceNum>1)
			{
				Report.OperationPoint(testContext.getName(), "Review order of devices - SKIPPED step during development as there was no associated data available");

			}
			
			//5. Select the device image for each CTN, Validate the user is directed to the modal for that CTN.
			oR_AccountOverview.imgValidDeviceImage.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Device image, Waiting for modal window");
			try
			{
				Thread.sleep(4000);
				String sMainWindowHandle = lDriver.getWindowHandle();
				WebElement frameHandle = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
				lDriver.switchTo().frame(frameHandle);
					
					Report.ValidationPoint(testContext.getName(), "After selecting the device image for each CTN, Validate the user is directed to the modal for that CTN", "true","true", true);
					oR_AccountOverview.lnkClose.click();
					Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Close' button");
					Thread.sleep(2000);
					
				lDriver.switchTo().window(sMainWindowHandle);
				
			}catch(Exception e2)
			{
				Report.OperationPoint(testContext.getName(), "* Modal window is not opened after clicking on Device image, Device image must be invalid!");
				//Report.ValidationPoint(testContext.getName(), "After selecting the device image for each CTN, Validate the user is directed to the modal for that CTN", "true","false", true);

			}
			
			//6. Select the 'View details' link, Validate the user is directed to the modal for that CTN
			oR_AccountOverview.lnkViewDetailsUnderUsageContainer.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'View details' link");
			Thread.sleep(3000);
				
			//7.Confirm the following is displayed
			String sMainWindowHandle = lDriver.getWindowHandle();
			WebElement frameHandle = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
			lDriver.switchTo().frame(frameHandle);
			
				//7.1,7.2 Subscriber first name
				boolean bSubNameAndCTN = UI.WaitForObject(oR_AccountOverview.txtFirstNameAndCTNUnderViewDetailsModalWindow, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Subscriber first name and CTN is displayed", "true",String.valueOf(bSubNameAndCTN), true);
				//7.3 'View all usage' link
				boolean bViewallUsageunderModal = UI.WaitForObject(oR_AccountOverview.lnkViewAllUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that 'View all usage' link is displayed in Modal window", "true",String.valueOf(bViewallUsageunderModal), true);
				//7.4  Device image (If not available, default image)
				boolean bDeviceImage = UI.WaitForObject(oR_AccountOverview.imgDeviceImageUnderViewDetailsModal, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Device image is displayed in modal window", "true",String.valueOf(bDeviceImage), true);
				//7.5  'Close' link with associated X icon
				boolean bClose = UI.WaitForObject(oR_AccountOverview.lnkClose, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Close button is displayed in modal window", "true",String.valueOf(bClose), true);

				oR_AccountOverview.lnkViewAllUsage.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'View all usage' link from modal window");
				Thread.sleep(3000);
			
			lDriver.switchTo().window(sMainWindowHandle);
			
			boolean bBillAndUsagePage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify user is redirected to Billing & Usage page after clicking on 'View all usage' link from modal window", "true",String.valueOf(bBillAndUsagePage), true);
			String sTabPreSelected2 = oR_BillAndUsage.lnkBillTabPreSelected.getText().toString();
			if(sTabPreSelected2.contains("Usage"))
			{
				Report.ValidationPoint(testContext.getName(), "Verify 'Usage' tab is preselcted", "true","true", true);
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify 'Usage' tab is preselcted", "true","false", true);
			}


		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - VerifyUsageTabAlertCustomization()
	 * Description - Alert customization-Usage tab - Limited Data  - Between 75 and 89 - MS
 	 * Test Case -  UUSG0081 - Alert customization-Usage tab - Limited Data  - Between 75 and 89 - MS
	 * Parameters - None
	 * Date created - 26th Feb 2016
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_UUSG0081
	public static void VerifyUsageTabAlertCustomization(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);
			OR_ViewOrChangeRatePlan oR_ViewOrChangeRatePlan = PageFactory.initElements(lDriver, OR_ViewOrChangeRatePlan.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Billing, Usage and Payments' link");
			
			boolean bBillingPage = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Billing&Usage page is displayed", "true",String.valueOf(bBillingPage), true);
			
			//Go to Usage library
			oR_BillAndUsage.lnkUsage.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Usage' tab");
			Thread.sleep(6000);
			
			//1. Navigate to Bill and payments page and select Usage tab, select recent usage
			//new Actions(lDriver).moveToElement(oR_BillAndUsage.txtDefaultUsageBillSelection).click().moveToElement(oR_BillAndUsage.lnkRecentUsageDropdownOption).click().build().perform();
			
			if(UI.WaitForObject(oR_BillAndUsage.txtPresentBillingPeriod, 10, lDriver));
			else
			{
				//From the 'Billing Period' drop down choose  Recent Usage. 
				//if(UI.WaitForObject(oR_BillAndUsage.lstChangeBiilingPeriodDropDown, 10, lDriver))
				//{
					new Actions(lDriver).moveToElement(oR_BillAndUsage.lstChangeBiilingPeriodDropDown).click().build().perform();
					
					//oR_BillAndUsage.lstChangeBiilingPeriodDropDown.click();
					Thread.sleep(2000);
					if(UI.WaitForObject(oR_BillAndUsage.lnkRecentUsageFromDropDown, 10, lDriver))
					{
						Report.ValidationPoint(testContext.getName(), "Select Recent Usage from Billing Period Dropdown", "true", "true", true);
						oR_BillAndUsage.lnkRecentUsageFromDropDown.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on Recent usage drop-down");
						Thread.sleep(6000);
					}
					else
						Report.ValidationPoint(testContext.getName(), "Recent Usage Not present in Billing Period Dropdown", "true", "false", true);
				//}	
			}
			
			//2. Review Pie chart usage
			boolean bPieChart = UI.WaitForObject(oR_BillAndUsage.tblPieChartSection, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that usage in Pie chart is displayed", "true",String.valueOf(bPieChart), true);
			
			//2. Validate following is displayed:
			//-Yellow Alert icon and Change plan CTA link.
			//-Total  of allotted mins used
			boolean bAlertUnderChart = UI.WaitForObject(oR_BillAndUsage.imgAlertYellowUnderPieChart, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Alert icon is displayed Under pie chart", "true",String.valueOf(bAlertUnderChart), true);
			boolean bChangePlan = UI.WaitForObject(oR_BillAndUsage.lnkChangePlanCTAUnderChart, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Change plan CTA displayed Under pie chart", "true",String.valueOf(bChangePlan), true);
			
			//3. Hover over Alert Icon, Validate appropriate Alert message is displayed
			new Actions(lDriver).moveToElement(oR_BillAndUsage.imgAlertYellowUnderPieChart).build().perform();
			Thread.sleep(3000);
			Report.OperationPoint(testContext.getName(), "Operational - Moved cursor over Alert icon");
			boolean bAlertPopup = UI.WaitForObject(oR_BillAndUsage.txtAlertPopUp, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), " Validate appropriate Alert message is displayed after hovering over yellow alert icon", "true",String.valueOf(bAlertPopup), true);
				
			//5. Review Web Alert under Core usage table. validate Yellow Alert icon displayed next to the Web Column header.
			boolean bWebHeader = UI.WaitForObject(oR_BillAndUsage.imgAlertYellowUnderWebHeader, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), " Review Web Alert under Core usage table. validate Yellow Alert icon displayed next to the Web Column header", "true",String.valueOf(bWebHeader), true);
			//6. Hover over Alert Icon, Validate appropriate Alert message is displayed
			new Actions(lDriver).moveToElement(oR_BillAndUsage.imgAlertYellowUnderWebHeader).build().perform();
			Thread.sleep(3000);
			Report.OperationPoint(testContext.getName(), "Operational - Moved cursor over Alert icon");
			boolean bAlertPopupWebHeader = UI.WaitForObject(oR_BillAndUsage.txtAlertPopUpUnderWebHeader, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), " Validate appropriate Alert message is displayed after hovering over yellow alert icon next to Web column header", "true",String.valueOf(bAlertPopupWebHeader), true);
			
			//7. Validate Alert device image displayed instead of regular device image.
			boolean bAlertDeviceImage = UI.WaitForObject(oR_BillAndUsage.imgDeviceImageWithAlert, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate Alert device image displayed instead of regular device image", "true",String.valueOf(bAlertDeviceImage), true);

			//4. Select change plan CTA, Validate user is directed to change  plan page with a list of Mobile share plans.
			oR_BillAndUsage.lnkChangePlanCTAUnderChart.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Change plan CTA");
			Thread.sleep(3000);
			
			boolean bViewOrChangePlan = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtViewOrChangeRatePlan, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that View or change rate plan page is displayed", "true",String.valueOf(bViewOrChangePlan), true);
			
			boolean bMobileShare = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtMobileShare, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate user is directed to change plan page with a list of Mobile share plans", "true",String.valueOf(bMobileShare), true);

			//boolean bPlans = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtListOfPlansSection, UI.iObjTimeOut, lDriver);
//			if(bMobileShare==true && bPlans==true)
//			{
//				Report.ValidationPoint(testContext.getName(), "Validate user is directed to change plan page with a list of Mobile share plans", "true","true", true);
//
//			}else
//			{
//				Report.ValidationPoint(testContext.getName(), "Validate user is directed to change plan page with a list of Mobile share plans", "true","false", true);
//
//			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}



	

}


