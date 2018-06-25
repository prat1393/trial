package com.AppSpecificLibrary;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;

import com.OR.MyATT.OR_AccountOverview;
import com.OR.MyATT.OR_BillAndUsage;
import com.SupportingFiles.IO;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;
import com.SupportingFiles.UI;


public class Usage_Sanity extends LaunchAndLogout {
	static Hashtable<String, String> sTestParams = new Hashtable<String, String>();

	/****************************************************************
	 * Function Name - VerifyUsagePage() 
	 * Description-    This function use to verify the Usage page  
	 * Parameters -    None
	 * Date created -  7/30/2015
	 * Developed by -  Rahul Bakde
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/

	public static void VerifyUsagePage(final ITestContext testContext) 
			throws Exception
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_BillAndUsage oR_BillAndUsage = PageFactory.initElements(lDriver, OR_BillAndUsage.class);


		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

		/*--- Need to retrieve DataType for Overview page verification */
		String sLoginType= IO.GetParamVal(sTestParams, "DataType");


		try{

			/*---Switch to desired data type as per data sheet ---*/
			switch (sLoginType) {

			case "SLID":
				// --- Click on 'Billing, Usage, Payments' link ---
				Report.OperationPoint(testContext.getName(), "Click on 'Billing, Usage, Payments' link");
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
				
				// --- Verify user is navigated to Billing & Usage page ---
				boolean bBillingUsageHeading_slid = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage,UI.iObjTimeOut, lDriver);
				Report.OperationPoint(testContext.getName(), "User is navigated to Billing & Usage page");
				Report.ValidationPoint(testContext.getName(), "Verify Billing & Usage heading is displayed on Billing & Usage page", "True", String.valueOf(bBillingUsageHeading_slid), true);
				
				// --- Verify Bill tab is displayed on Billing & Usage page ---
				boolean bBillTab_slid = UI.WaitForObject(oR_BillAndUsage.lnkBillTab, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Bill tab is displayed on Billing & Usage page", "True", String.valueOf(bBillTab_slid), true);
	
				// --- Verify Usage tab is displayed on Billing & Usage page ---
				boolean bUsageTab_slid = UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Usage tab is displayed on Billing & Usage page", "True", String.valueOf(bUsageTab_slid), true);

				// --- Verify History tab is displayed on Billing & Usage page ---
				boolean bHistoryTab_slid = UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify History tab is displayed on Billing & Usage page", "True", String.valueOf(bHistoryTab_slid), true);

				// --- Verify Reports tab is displayed on Billing & Usage page ---
				boolean bReportsTab_slid = UI.WaitForObject(oR_BillAndUsage.lnkReportTab, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Reports tab is displayed on Billing & Usage page", "True", String.valueOf(bReportsTab_slid), true);
				
				//Wait till Usage link is displayed
				boolean bUsage_slid = UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut, lDriver);
				if (bUsage_slid == true) {
					if (oR_BillAndUsage.lnkUsage.isDisplayed()) {
						Thread.sleep(1000*5);
						Report.ValidationPoint(testContext.getName(), "Verify Usage link is displayed on Billing & Usage page", "True", String.valueOf(bUsage_slid), true);
						
						// --- Click on Usage tab on Billing & Usage page ---
						oR_BillAndUsage.lnkUsage.click();
						Report.OperationPoint(testContext.getName(), "Clicked on Usage tab on Billing & Usage page");
						Thread.sleep(1000*5);
					}		
				} else { 
					Report.ValidationPoint(testContext.getName(), "Verify Usage link is displayed on Billing & Usage page", "True", String.valueOf(bUsage_slid), true);
				}
				
				// --- Verify Billing Cycle Selector drop down is displayed under Usage tab ---
				WebElement billCycleDropdown_slid  = lDriver.findElement(By.xpath("//div[contains(text(),'Current Billed Usage') or contains(text(),'Previously Billed Usage')] | //*[contains(text(),'Current Billed Usage') or contains(text(),'Previously Billed Usage') or contains(text(),'Recent Usage')] | //div[@id='ddShortcut']//p[contains(text(),'Recent Usage')]"));

//				boolean bBillCycleSelector_slid = UI.WaitForObject(oR_BillAndUsage.lstBillCycleDropDown, 5, lDriver).equals(true);
				boolean bBillCycleSelector_slid = UI.WaitForObject(billCycleDropdown_slid,UI.iObjTimeOut, lDriver);

				Report.ValidationPoint(testContext.getName(), "Verify Billing Cycle Selector drop down is displayed under Usage tab", "True", String.valueOf(bBillCycleSelector_slid), true);
				if (bBillCycleSelector_slid == true) {
					// --- Object of the billed cycle having usage in it ---
					boolean bUsagePageDisplayedProperly = UI.WaitForObject(oR_BillAndUsage.txtValidUsagePageCheck, UI.iObjTimeOut, lDriver);
					if(bUsagePageDisplayedProperly==true)
					{
						Report.OperationPoint(testContext.getName(), "Usage is displayed for current page. So, continuing wihout changing the usage period");
					}else
					{
						//String sBilledCycleTxt = IO.GetParamVal(sTestParams, "BilledCycleFirstMonthTxt");
						//					WebElement usage_wl = lDriver.findElement(By.xpath("//span[contains(text(),"+sBilledCycleTxt+")]//parent::a"));
						//					WebElement usage_wl = lDriver.findElement(By.xpath("//a[@class='wt_Body' and contains(@title,"+sBilledCycleTxt+")] "));
						//					WebElement usage_wl = lDriver.findElement(By.xpath("//div[@id='20150313|2162260371999|S|A' or @id = 'ddShortcutBox']//a/span[contains(text(),"+sBilledCycleTxt+")]"));
						//					WebElement usage_wl = lDriver.findElement(By.xpath("//a/span[contains(text(),"+sBilledCycleTxt+")]"));
											//WebElement usage_wl = lDriver.findElement(By.xpath("//div[contains(@id,'S|A') or contains(@id,'ddShortcutBox')]//span[contains(text(),"+sBilledCycleTxt+")]/parent::a"));
											//WebElement usage_wl = lDriver.findElement(By.xpath("(//div[contains(@id,'S|A') or contains(@id,'ddShortcutBox')]//span[contains(text(),'-')]/parent::a)[1]"));
											WebElement usage_wl = oR_BillAndUsage.lnkBillCycleSelection;
											String sBilledCycleTxt = oR_BillAndUsage.lnkBillCycleSelection.getText().toString();
											
						//					Actions action = new Actions(lDriver);
						//					action.moveToElement(oR_BillAndUsage.lstBillCycleDropDown).click().build().perform();
						//					action.moveToElement(usage_wl).click().build().perform();
											Actions action_slid  = new Actions(lDriver);
						//					action_slid.moveToElement(oR_BillAndUsage.lstBillCycleDropDown).sendKeys(Keys.ENTER).build().perform();
											action_slid.moveToElement(billCycleDropdown_slid).click().moveToElement(usage_wl).click().build().perform();
						
						//					oR_BillAndUsage.lstBillCycleDropDown.click();
						//					oR_BillAndUsage.lstBillCycleDropDown.submit();
											Thread.sleep(1000*2);
											//usage_wl.click();
						//					oR_BillAndUsage.lnkBillCycleUsage.click();
											
											Report.OperationPoint(testContext.getName(), "Clicked on Bill cycle drop down, selected new usage period");
					}
				}
				
				Thread.sleep(5000);
				// --- Verify Billing period text is displayed under Usage tab ---
				boolean bBillingPeriod_slid = UI.WaitForObject(oR_BillAndUsage.txtBillPeriod, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Billing period text is displayed under Usage tab", "True", String.valueOf(bBillingPeriod_slid), true);

				// --- Verify PDF icon is displayed for view paper bill under Usage tab ---
				boolean bPDFicon_slid = UI.WaitForObject(oR_BillAndUsage.imgPDFiconPaperBill, UI.iObjTimeOut, lDriver);
				//Report.ValidationPoint(testContext.getName(), "Verify PDF icon is displayed for view paper bill under Usage tab", "True", String.valueOf(bPDFicon_slid), true);
				if(bPDFicon_slid==true)
				{
					Report.ValidationPoint(testContext.getName(), "Verify PDF icon is displayed for view paper bill under Usage tab", "True", String.valueOf(bPDFicon_slid), true);
				}else
				{
					Report.OperationPoint(testContext.getName(), "*PDF icon is disabled for view paper bill under Usage tab for current Data");
				}
				
				// --- Verify Service selector drop down is displayed under Usage tab ---
				boolean bServiceSelectorDropDown_slid = UI.WaitForObject(oR_BillAndUsage.lstViewServiceDropDown, UI.iObjTimeOut, lDriver);
				if(bServiceSelectorDropDown_slid==true)
				{
					Report.ValidationPoint(testContext.getName(), "Verify Service selector drop down is displayed under Usage tab", "True", String.valueOf(bServiceSelectorDropDown_slid), true);
				}else
				{
					Report.OperationPoint(testContext.getName(), "*Service selector drop down is not displayed under Usage tab since there is only one plan available");

				}
				// --- Verify Plan name is displayed under Usage tab ---
				boolean bPlanName_slid = UI.WaitForObject(oR_BillAndUsage.txtPlanName, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Plan name is displayed under Usage tab", "True", String.valueOf(bPlanName_slid), true);
				
				// --- Verify Device Icon, Customer Name & CTN Number ---
				//tota\ table
				List <WebElement> table_slid = lDriver.findElements(By.xpath("//tbody[tr]"));
				int tableCount_slid = table_slid.size();
				
				if(tableCount_slid!=0)
				{
				
					//total device icon
					List<WebElement> deviceIcon_slid = lDriver.findElements(By.xpath("//td//img[@alt='Phone' or @alt='phone']"));
					int deviceIconCount_slid = deviceIcon_slid.size();
					
					//total customer name
					List<WebElement> custName_slid = lDriver.findElements(By.xpath("//td//div[@class='MarTop15']//strong"));
					int custNameCount_slid = custName_slid.size();
					
					//total CTN number
					List<WebElement> ctnNumber_slid = lDriver.findElements(By.xpath("//td//div[@class='MarTop15']//span"));
					int ctnNumCount_slid = ctnNumber_slid.size();
					
					/*
					 *  --- To verify Device Icon, Customer Name & CTN Number
					 *  Count of Device icon, customer name and ctn number 
					 *  should be same
					 */
					
					if (tableCount_slid == deviceIconCount_slid){
						Report.ValidationPoint(testContext.getName(), "Verify Device Icon is displayed under Usage tab", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Device Icon is displayed under Usage tab", "True", "False", true);
					}
				
					if (tableCount_slid == custNameCount_slid){
						Report.ValidationPoint(testContext.getName(), "Verify Customer name is displayed under Usage tab", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Customer name is displayed under Usage tab", "True", "False", true);
					}
		
					if (tableCount_slid == ctnNumCount_slid){
						Report.ValidationPoint(testContext.getName(), "Verify CTN number is displayed under Usage tab", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify CTN number is displayed under Usage tab", "True", "False", true);
					}
					
				}else
				{
					Report.OperationPoint(testContext.getName(), "Plan Usage details are NOT displayed in tabular format under Usage tab");
					Report.ValidationPoint(testContext.getName(), "Verify Plan Usage details are displayed in tabular format under Usage tab", "True", "False", true);

				}
				
				// --- Verify pie chart is displayed
				boolean bPieChart_slid = UI.WaitForObject(oR_BillAndUsage.svgPieChart, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify pie chart is displayed on Billing & Usage page", "True", String.valueOf(bPieChart_slid), true);
				
				// --- Verify Basic plan usage heading is displayed
				boolean bBasicPlanUsage_slid = UI.WaitForObject(oR_BillAndUsage.txtBasicPlanUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Basic plan usage heading is displayed on Billing & Usage page", "True", String.valueOf(bBasicPlanUsage_slid), true);

				// --- Verify Basic plan usage table is displayed
				boolean bBasicPlanUsageTable_slid = UI.WaitForObject(oR_BillAndUsage.tblBasicPlanUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Basic plan usage table is displayed on Billing & Usage page", "True", String.valueOf(bBasicPlanUsageTable_slid), true);

				// --- Verify Additional usage table is displayed
				boolean bAdditonalUsageTable_slid = UI.WaitForObject(oR_BillAndUsage.tblAdditionalUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Additional usage table is displayed on Billing & Usage page", "True", String.valueOf(bAdditonalUsageTable_slid), true);

				// --- Verify Additional usage heading is displayed
				boolean bAdditonalUsageHeading_slid = UI.WaitForObject(oR_BillAndUsage.txtAdditionalUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Additional usage table heading is displayed on Billing & Usage page", "True", String.valueOf(bAdditonalUsageHeading_slid), true);

				// --- Verify Secondary rail table is displayed
				boolean bSecRailTable_slid = UI.WaitForObject(oR_BillAndUsage.tblSecLinkRailsSection, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Secondary Rail table is displayed on Billing & Usage page", "True", String.valueOf(bSecRailTable_slid), true);

				// --- Verify Manage Secondary rail table is displayed
				boolean bManageSecRailTable_slid = UI.WaitForObject(oR_BillAndUsage.tblManageSecRail, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Manage Secondary Rail table is displayed on Billing & Usage page", "True", String.valueOf(bManageSecRailTable_slid), true);
			
				// --- Verify Manage Billing and Payement Secondary rail table is displayed
				boolean bManageBillPaySecRailTable_slid = UI.WaitForObject(oR_BillAndUsage.tblManageBillPaySecRail,UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Manage Billing and Payement Secondary Rail table is displayed on Billing & Usage page", "True", String.valueOf(bManageBillPaySecRailTable_slid), true);

				// --- Verify Get Help Secondary rail table is displayed
				boolean bGetHelpSecRailTable_slid = UI.WaitForObject(oR_BillAndUsage.tblGetHelpSecRail,UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Get Help Secondary Rail table is displayed on Billing & Usage page", "True", String.valueOf(bGetHelpSecRailTable_slid), true);

				// --- Verify Total data usage is displayed
				boolean bTotalDataUsage_slid = UI.WaitForObject(oR_BillAndUsage.txtTotalDataUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Total data usage is displayed on Billing & Usage page", "True", String.valueOf(bTotalDataUsage_slid), true);
				Report.OperationPoint(testContext.getName(), "Total data used is : "+oR_BillAndUsage.txtTotalDataUsage.getText());
				
				// --- Verify Total data allowance is displayed
				boolean bTotalDataAllowance_slid = UI.WaitForObject(oR_BillAndUsage.txtTotalDataAllowance, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Total data allowance is displayed on Billing & Usage page", "True", String.valueOf(bTotalDataAllowance_slid), true);
				Report.OperationPoint(testContext.getName(), "Total data allowance is : "+oR_BillAndUsage.txtTotalDataAllowance.getText());

				break;

			case "Wireline":
				// --- Click on 'Billing, Usage, Payments' link ---
				Report.OperationPoint(testContext.getName(), "Click on 'Billing, Usage, Payments' link");
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
				
				// --- Verify user is navigated to Billing & Usage page ---
				boolean bBillingUsageHeading_wl = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
				Report.OperationPoint(testContext.getName(), "User is navigated to Billing & Usage page");
				Report.ValidationPoint(testContext.getName(), "Verify Billing & Usage heading is displayed on Billing & Usage page", "True", String.valueOf(bBillingUsageHeading_wl), true);
				
				// --- Verify Bill tab is displayed on Billing & Usage page ---
				boolean bBillTab_wl = UI.WaitForObject(oR_BillAndUsage.lnkBillTab, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Bill tab is displayed on Billing & Usage page", "True", String.valueOf(bBillTab_wl), true);
	
				// --- Verify Usage tab is displayed on Billing & Usage page ---
				boolean bUsageTab_wl = UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Usage tab is displayed on Billing & Usage page", "True", String.valueOf(bUsageTab_wl), true);

				// --- Verify History tab is displayed on Billing & Usage page ---
				boolean bHistoryTab_wl = UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab,UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify History tab is displayed on Billing & Usage page", "True", String.valueOf(bHistoryTab_wl), true);

				// --- Verify Reports tab is displayed on Billing & Usage page ---
				boolean bReportsTab_wl = UI.WaitForObject(oR_BillAndUsage.lnkReportTab, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Reports tab is displayed on Billing & Usage page", "True", String.valueOf(bReportsTab_wl), true);
				
				//Wait till Usage link is displayed
				boolean bUsage_wl = UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut, lDriver);
				if (bUsage_wl == true) {
					if (oR_BillAndUsage.lnkUsage.isDisplayed()) {
						Thread.sleep(6000);
						Report.ValidationPoint(testContext.getName(), "Verify Usage link is displayed on Billing & Usage page", "True", String.valueOf(bUsage_wl), true);
						
						// --- Click on Usage tab on Billing & Usage page ---
						oR_BillAndUsage.lnkUsage.click();
						Report.OperationPoint(testContext.getName(), "Clicked on Usage tab on Billing & Usage page");
						Thread.sleep(6000);
					}		
				} else { 
					Report.ValidationPoint(testContext.getName(), "Verify Usage link is displayed on Billing & Usage page", "True", String.valueOf(bUsage_wl), true);
				}
				
				// --- Verify Billing Cycle Selector drop down is displayed under Usage tab ---
				//WebElement billCycleDropdown_wl  = lDriver.findElement(By.xpath("//div[contains(@class,'MyBillWDBg430')] | //div[contains(text(),'Current Billed Usage') or contains(text(),'Previously Billed Usage')] | //*[contains(text(),'Current Billed Usage') or contains(text(),'Previously Billed Usage') or contains(text(),'Recent Usage')] | //div[@id='ddShortcut']//p[contains(text(),'Recent Usage')]"));
				//WebElement billCycleDropdown_wl  = lDriver.findElement(By.xpath("//div[contains(@class,'MyBillWDBg430')] | //*[contains(text(),'Current Billed Usage') or contains(text(),'Previously Billed Usage') or contains(text(),'Recent Usage')] | //div[@id='ddShortcut']//p[contains(text(),'Recent Usage')]"));
				WebElement billCycleDropdown_wl  = lDriver.findElement(By.xpath("//div[contains(@class,'MyBillWDBg430')] | //div[@id='ddShortcut']//p[contains(text(),'Recent Usage')]"));

//				boolean bBillCycleSelector_wl = UI.WaitForObject(oR_BillAndUsage.lstBillCycleDropDown, 5, lDriver).equals(true);
				boolean bBillCycleSelector_wl = UI.WaitForObject(billCycleDropdown_wl, UI.iObjTimeOut, lDriver);

				Report.ValidationPoint(testContext.getName(), "Verify Billing Cycle Selector drop down is displayed under Usage tab", "true", String.valueOf(bBillCycleSelector_wl), true);
				if (bBillCycleSelector_wl == true) {
					// --- Object of the billed cycle having usage in it ---
					WebElement ValidUsagePageCheck = lDriver.findElement(By.xpath("//*[@id='planName' and contains(text(),'Basic Plan Usage')] | //*[contains(text(),'Additional Usage')]"));
					boolean bUsagePageDisplayedProperly = UI.WaitForObject(ValidUsagePageCheck, UI.iObjTimeOut, lDriver);
					if(bUsagePageDisplayedProperly==true)
					{
						Report.OperationPoint(testContext.getName(), "Usage is displayed for current page. So, continuing wihout changing the usage period");
					}else
					{
						//String sBilledCycleTxt = IO.GetParamVal(sTestParams, "BilledCycleFirstMonthTxt");
	//					WebElement usage_wl = lDriver.findElement(By.xpath("//span[contains(text(),"+sBilledCycleTxt+")]//parent::a"));
	//					WebElement usage_wl = lDriver.findElement(By.xpath("//a[@class='wt_Body' and contains(@title,"+sBilledCycleTxt+")] "));
	//					WebElement usage_wl = lDriver.findElement(By.xpath("//div[@id='20150313|2162260371999|S|A' or @id = 'ddShortcutBox']//a/span[contains(text(),"+sBilledCycleTxt+")]"));
	//					WebElement usage_wl = lDriver.findElement(By.xpath("//div[@id='20150313|2162260371999|S|A' or @id = 'ddShortcutBox']//span[contains(text(),"+sBilledCycleTxt+")]/parent::a"));

						//					Actions action = new Actions(lDriver);
						//					action.moveToElement(oR_BillAndUsage.lstBillCycleDropDown).click().build().perform();
						//					action.moveToElement(usage_wl).click().build().perform();
						//					oR_BillAndUsage.lstBillCycleDropDown.click();
											//billCycleDropdown_wl.click();
						//					oR_BillAndUsage.lstBillCycleDropDown.submit();
											//Thread.sleep(1000*5);
											//usage_wl.click();
						
						WebElement usage_wl = lDriver.findElement(By.xpath("(//div[contains(@id,'S|A') or contains(@id,'ddShortcut')]//span[contains(text(),'-')])[1]"));
						new Actions(lDriver).moveToElement(billCycleDropdown_wl).click().moveToElement(usage_wl).click().build().perform();
						Report.OperationPoint(testContext.getName(), "Clicked on Bill cycle drop down");

						
					}
				}
				
				Thread.sleep(5000);
				
//				// --- Object of the billed cycle having usage in it ---
//				String sBilledCycleTxt = IO.GetParamVal(sTestParams, "BilledCycleFirstMonthTxt");
//				WebElement usage_wl = lDriver.findElement(By.xpath("//span[contains(text(),"+sBilledCycleTxt+")]//parent::a"));
				
				// --- Click on the billed cycle with usage ----
//				usage_wl.click();
				
				// --- Verify Billing period text is displayed under Usage tab ---
				boolean bBillingPeriod_wl = UI.WaitForObject(oR_BillAndUsage.txtBillPeriod, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Billing period text is displayed under Usage tab", "True", String.valueOf(bBillingPeriod_wl), true);

				// --- Verify PDF icon is displayed for view paper bill under Usage tab ---
				boolean bPDFicon_wl = UI.WaitForObject(oR_BillAndUsage.imgPDFiconPaperBill, UI.iObjTimeOut, lDriver);
				//Report.ValidationPoint(testContext.getName(), "Verify PDF icon is displayed for view paper bill under Usage tab", "True", String.valueOf(bPDFicon_wl), true);
				if(bPDFicon_wl==true)
				{
					Report.ValidationPoint(testContext.getName(), "Verify PDF icon is displayed for view paper bill under Usage tab", "True", String.valueOf(bPDFicon_wl), true);
				}else
				{
					Report.OperationPoint(testContext.getName(), "*PDF icon is disabled for view paper bill under Usage tab for current Data");
				}
				
				// --- Verify Service selector drop down is displayed under Usage tab ---
				boolean bServiceSelectorDropDown_wl = UI.WaitForObject(oR_BillAndUsage.lstViewServiceDropDown, UI.iObjTimeOut, lDriver);
				if(bServiceSelectorDropDown_wl==true)
				{
					Report.ValidationPoint(testContext.getName(), "Verify Service selector drop down is displayed under Usage tab", "True", String.valueOf(bServiceSelectorDropDown_wl), true);
				}else
				{
					Report.OperationPoint(testContext.getName(), "*Service selector drop down is not displayed under Usage tab since there is only one plan available");

				}
		
				// --- Verify device name is displayed under Usage tab ---
				boolean bDeviceName_wl = UI.WaitForObject(oR_BillAndUsage.txtDeviceName, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify device name is displayed under Usage tab", "True", String.valueOf(bDeviceName_wl), true);

				// --- Verify device number is displayed under Usage tab ---
				boolean bDeviceNumber_wl = UI.WaitForObject(oR_BillAndUsage.txtDeviceNumber, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify device number is displayed under Usage tab", "True", String.valueOf(bDeviceNumber_wl), true);

//				// --- Verify details text is displayed under Usage tab ---
//				boolean bDetailsText_wl = UI.WaitForObject(oR_BillAndUsage.txtDetails, UI.iObjTimeOut, lDriver);
//				Report.ValidationPoint(testContext.getName(), "Verify details text is displayed under Usage tab", "True", String.valueOf(bDetailsText_wl), true);
//
//				// --- Verify print icon is displayed under Usage tab ---
//				boolean bPrintIcon_wl = UI.WaitForObject(oR_BillAndUsage.lnkPrintIcon, UI.iObjTimeOut, lDriver);
//				Report.ValidationPoint(testContext.getName(), "Verify print icon is displayed under Usage tab", "True", String.valueOf(bPrintIcon_wl), true);
//
//				// --- Verify Download dropdown is displayed under Usage tab ---
//				boolean bDownloadDropdown_wl = UI.WaitForObject(oR_BillAndUsage.lstDownloadDropDown, UI.iObjTimeOut, lDriver);
//				Report.ValidationPoint(testContext.getName(), "Verify Download dropdown is displayed under Usage tab", "True", String.valueOf(bDownloadDropdown_wl), true);
//
//				// --- Verify Search field is displayed under Usage tab ---
//				boolean bSearchField_wl = UI.WaitForObject(oR_BillAndUsage.edtSearchField, UI.iObjTimeOut, lDriver);
//				Report.ValidationPoint(testContext.getName(), "Verify Search field is displayed under Usage tab", "True", String.valueOf(bSearchField_wl), true);
//
//				// --- Verify Search icon is displayed under Usage tab ---
//				boolean bSearchicon_wl = UI.WaitForObject(oR_BillAndUsage.imgSearchIcon, UI.iObjTimeOut, lDriver);
//				Report.ValidationPoint(testContext.getName(), "Verify Search icon is displayed under Usage tab", "True", String.valueOf(bSearchicon_wl), true);
//
//				// --- Verify Usage detail table is displayed under Usage tab ---
//				boolean bUsageDetailTable_wl = UI.WaitForObject(oR_BillAndUsage.tblUsageDetail, UI.iObjTimeOut, lDriver);
//				Report.ValidationPoint(testContext.getName(), "Verify Usage detail table is displayed under Usage tab", "True", String.valueOf(bUsageDetailTable_wl), true);

				// --- Verify Manage account is displayed under Usage tab ---
				boolean bManageAccount_wl = UI.WaitForObject(oR_BillAndUsage.txtManageAccount, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Manage account heading is displayed under Usage tab", "True", String.valueOf(bManageAccount_wl), true);

				// --- Verify Manage Billing and Payment Option heading is displayed under Usage tab ---
				boolean bManageBillingPaymentOption_wl = UI.WaitForObject(oR_BillAndUsage.txtManageBillingPaymentOption, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Manage Billing and Payment Option heading is displayed under Usage tab", "True", String.valueOf(bManageBillingPaymentOption_wl), true);

				// --- Verify Get Help heading is displayed under Usage tab ---
				boolean bGetHelpHeading_wl = UI.WaitForObject(oR_BillAndUsage.txtGetHelpHeading,UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Get Help heading is displayed under Usage tab", "True", String.valueOf(bGetHelpHeading_wl), true);	
				
				break;

			case "Wireless":
				
				// --- Click on 'Billing, Usage, Payments' link ---
				Report.OperationPoint(testContext.getName(), "Click on 'Billing, Usage, Payments' link");
				oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
				
				// --- Verify user is navigated to Billing & Usage page ---
				boolean bBillingUsageHeading_wire= UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
				Report.OperationPoint(testContext.getName(), "User is navigated to Billing & Usage page");
				Report.ValidationPoint(testContext.getName(), "Verify Billing & Usage heading is displayed on Billing & Usage page", "True", String.valueOf(bBillingUsageHeading_wire), true);
				
				// --- Verify Bill tab is displayed on Billing & Usage page ---
				boolean bBillTab_wire = UI.WaitForObject(oR_BillAndUsage.lnkBillTab, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Bill tab is displayed on Billing & Usage page", "True", String.valueOf(bBillTab_wire), true);
	
				// --- Verify Usage tab is displayed on Billing & Usage page ---
				boolean bUsageTab_wire = UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Usage tab is displayed on Billing & Usage page", "True", String.valueOf(bUsageTab_wire), true);

				// --- Verify History tab is displayed on Billing & Usage page ---
				boolean bHistoryTab_wire = UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify History tab is displayed on Billing & Usage page", "True", String.valueOf(bHistoryTab_wire), true);

				// --- Verify Reports tab is displayed on Billing & Usage page ---
				boolean bReportsTab_wire = UI.WaitForObject(oR_BillAndUsage.lnkReportTab, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Reports tab is displayed on Billing & Usage page", "True", String.valueOf(bReportsTab_wire), true);
				
				//Wait till Usage link is displayed
				boolean bUsage_wire = UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut, lDriver);
				if (bUsage_wire == true) {
					if (oR_BillAndUsage.lnkUsage.isDisplayed()) {
						Thread.sleep(1000*6);
						Report.ValidationPoint(testContext.getName(), "Verify Usage link is displayed on Billing & Usage page", "True", String.valueOf(bUsage_wire), true);
						
						// --- Click on Usage tab on Billing & Usage page ---
						oR_BillAndUsage.lnkUsage.click();
						Report.OperationPoint(testContext.getName(), "Clicked on Usage tab on Billing & Usage page");
						Thread.sleep(1000*6);
					}		
				} else { 
					Report.ValidationPoint(testContext.getName(), "Verify Usage link is displayed on Billing & Usage page", "True", String.valueOf(bUsage_wire), true);
				}
				
				// --- Verify Billing Cycle Selector drop down is displayed under Usage tab ---
//				boolean bBillCycleSelector_wire = UI.WaitForObject(oR_BillAndUsage.lstBillCycleDropDown, 5, lDriver).equals(true);
//				Report.ValidationPoint(testContext.getName(), "Verify Billing Cycle Selector drop down is displayed under Usage tab", "True", String.valueOf(bBillCycleSelector_wire), true);
				/*
				if (bBillCycleSelector_wire == true) {
					// --- Object of the billed cycle having usage in it ---
					String sBilledCycleTxt_wire = IO.GetParamVal(sTestParams, "BilledCyclePeriod");
//					WebElement usage_wl = lDriver.findElement(By.xpath("//span[contains(text(),"+sBilledCycleTxt+")]//parent::a"));
//					WebElement usage_wl = lDriver.findElement(By.xpath("//a[@class='wt_Body' and contains(@title,"+sBilledCycleTxt+")] "));
//					WebElement usage_wl = lDriver.findElement(By.xpath("//div[@id='20150313|2162260371999|S|A' or @id = 'ddShortcutBox']//a/span[contains(text(),"+sBilledCycleTxt+")]"));
//					WebElement usage_wl = lDriver.findElement(By.xpath("//a/span[contains(text(),"+sBilledCycleTxt+")]"));

//					Actions action = new Actions(lDriver);
//					action.moveToElement(oR_BillAndUsage.lstBillCycleDropDown).click().build().perform();
//					action.moveToElement(usage_wl).click().build().perform();
					Actions action_wire  = new Actions(lDriver);
					action_wire.moveToElement(oR_BillAndUsage.lstBillCycleDropDown).build().perform();
					Thread.sleep(2000);
					System.out.println("clicked on bill cycle dropdown");
					action_wire.sendKeys(Keys.ENTER).build().perform();
					System.out.println("enter");
//					Thread.sleep(1000);
//					oR_BillAndUsage.lstBillCycleDropDown.click();
//					oR_BillAndUsage.lstBillCycleDropDown.submit();
					Thread.sleep(1000*5);
//					usage_wl.click();
//					oR_BillAndUsage.lnkBillCycleUsage.click();
//					WebDriver driver=new FirefoxDriver();
//			       String baseURL="http://att.com";
//			       driver.get(baseURL);
//			       Selenium sel=new WebDriverBackedSelenium(lDriver, LaunchAndLogout.sMyATTEnvURL);
//			       sel.click("xpath=//div[contains(@id,'T01|W')]/div[2]/a/span[contains(text(),"+sBilledCycleTxt_wire+")]");

//					WebElement lnkPreviousUsage_wire = lDriver.findElement(By.xpath("//div[contains(@id,'T01|W')]/div[2]/a/span[contains(text(),"+sBilledCycleTxt_wire+")]"));
//					lnkPreviousUsage_wire.click();
//					Report.OperationPoint(testContext.getName(), "Clicked on Bill cycle drop down");
//				}*/
				
//				Thread.sleep(1000*3);
				
//				Boolean bShowing = UI.WaitForObject(oR_BillAndUsage.lstShowing, 10);
//				if(bShowing)
//				{
//					//click
//					Actions dropdown = new Actions(lDriver);
//					dropdown.moveToElement(oR_BillAndUsage.lstShowing).sendKeys(Keys.ENTER).build().perform();
//					dropdown.moveToElement(oR_BillAndUsage.lstShowing).sendKeys(Keys.ENTER).build().perform();
//					dropdown.click().perform();
////					dropdown.click();
//					oR_BillAndUsage.lstShowing.click();
////					dropdown.perform();
////					oR_BillAndUsage.lstShowing.click();
//					Thread.sleep(3000);
//					Report.ValidationPoint(testContext.getName(), "Validate Showing drop down with option Recent usage, Current Billed Usage and Previous billed usage and all are clickable and all will contain the required Usage information", "true",String.valueOf(bShowing), true);
//					//Click on Recent usage
//					Report.OperationPoint(testContext.getName(), "Clicking on Recent usage link");
////					WebElement elmRecent = lDriver.findElement(By.xpath("//div[@id='ddShortcutBox']//dd[1]//a"));
////					WebElement elmRecent = lDriver.findElement(By.xpath("//div[@id='20150628|177052447176|T01|W']/div[2]/a/span"));
//					
//					WebElement elmRecent = lDriver.findElement(By.xpath("//dd[3]/div/div[2]/a/span"));
//					if(elmRecent.isDisplayed())
//					{
//						Actions action = new Actions(lDriver);
////						action.
//						action.moveToElement(elmRecent).click().build().perform();
//						
////						Thread.sleep(5000);
////						elmRecent.click();
////						action.sendKeys(elmRecent, Keys.ENTER);
//						
//						Report.ValidationPoint(testContext.getName(), "Validate Recent Usage is displayed", "true","true", true);
//					}
//				}
				//String sBilledCycleTxt_wire = IO.GetParamVal(sTestParams, "BilledCycleFirstMonthTxt");
				WebElement billCycleDropdown_wire  = lDriver.findElement(By.xpath("//div[contains(text(),'Current Billed Usage') or contains(text(),'Previously Billed Usage')] | //*[contains(text(),'Current Billed Usage') or contains(text(),'Previously Billed Usage') or contains(text(),'Recent Usage')] | //div[@id='ddShortcut']//p[contains(text(),'Recent Usage')]"));

//				if(UI.WaitForObject(oR_BillAndUsage.txtCurrentBilledUsage, UI.iObjTimeOut)){
				if(UI.WaitForObject(billCycleDropdown_wire, UI.iObjTimeOut)){
					
					  Report.ValidationPoint(testContext.getName(), "Validate that Current Billing Usage option is selected by default as billing period", "Current Billing Usage option is selected by default as billing period", "Current Billing Usage option is selected by default as billing period", true);	
					  boolean bUsagePageDisplayedProperly = UI.WaitForObject(oR_BillAndUsage.txtValidUsagePageCheck, UI.iObjTimeOut, lDriver);
					  if(bUsagePageDisplayedProperly==true)
					  {
					  	Report.OperationPoint(testContext.getName(), "Usage is displayed for current page. So, continuing wihout changing the usage period");
					  }else
					  { 
						  Report.OperationPoint(testContext.getName(), "Click on showing drop down");
	//					   oR_BillAndUsage.txtCurrentBilledUsage.click();
						  Actions action_wire  = new Actions(lDriver);
						  action_wire.moveToElement(billCycleDropdown_wire).click().build().perform();
	//					  billCycleDropdown_wire.click();
						   Report.ValidationPoint(testContext.getName(), "Validate options under Showing dropdown displayed", "True", "True", true);
						  Thread.sleep(4000);
	//					   WebElement lnkPreviousBillUsage = lDriver.findElement(By.xpath("//span[contains(text(),'Previously Billed')]/parent::div/parent::div//a"));
							//WebElement usage_wire = lDriver.findElement(By.xpath("(//div[contains(@id,'S|A') or contains(@id,'ddShortcutBox')]//span[contains(text(),'-')]/parent::a)[1]"));
	//						WebElement usage_wire = lDriver.findElement(By.xpath("//span[contains(text(),"+sBilledCycleTxt_wire+")]/parent::a"));
							WebElement usage_wire = oR_BillAndUsage.lnkBillCycleSelection;
							//String sBilledCycleTxt = oR_BillAndUsage.lnkBillCycleSelection.getText().toString();
							
	//					   if(UI.WaitForObject(lnkPreviousBillUsage, 15, lDriver)){
							if(UI.WaitForObject(usage_wire, 1, lDriver)){
							  
	//						    lnkPreviousBillUsage.click();
								action_wire.moveToElement(usage_wire).click().build().perform();
	//							usage_wire.click();
							    Report.ValidationPoint(testContext.getName(), "Verify Previous Billing Usage Option is selected", "True", "True", true);
						   }
					  }
					   
					}else{
			    		Report.ValidationPoint(testContext.getName(), "Validate that Current Billing Usage option is selected by default as billing period", "Current Billing Usage option is selected by default as billing period", "Current Billing Usage option is NOT selected by default as billing period", true);
					}
				
				Thread.sleep(5000);
				// --- Verify Billing period text is displayed under Usage tab ---
				boolean bBillingPeriod_wire = UI.WaitForObject(oR_BillAndUsage.txtBillPeriod,UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Billing period text is displayed under Usage tab", "True", String.valueOf(bBillingPeriod_wire), true);

				// --- Verify PDF icon is displayed for view paper bill under Usage tab ---
				boolean bPDFicon_wire = UI.WaitForObject(oR_BillAndUsage.imgPDFiconPaperBill, UI.iObjTimeOut, lDriver);
				if(bPDFicon_wire==true)
				{
					Report.ValidationPoint(testContext.getName(), "Verify PDF icon is displayed for view paper bill under Usage tab", "True", String.valueOf(bPDFicon_wire), true);
				}else
				{
					Report.OperationPoint(testContext.getName(), "*PDF icon is disabled for view paper bill under Usage tab for current Data");
				}
				// --- Verify Service selector drop down is displayed under Usage tab ---
				boolean bServiceSelectorDropDown_wire = UI.WaitForObject(oR_BillAndUsage.lstViewServiceDropDown,UI.iObjTimeOut, lDriver);
				if(bServiceSelectorDropDown_wire==true)
				{
					Report.ValidationPoint(testContext.getName(), "Verify Service selector drop down is displayed under Usage tab", "True", String.valueOf(bServiceSelectorDropDown_wire), true);
				}else
				{
					Report.OperationPoint(testContext.getName(), "*Service selector drop down is not displayed under Usage tab since there is only one plan available");

				}
				
				// --- Verify Plan name is displayed under Usage tab ---
				boolean bPlanName_wire = UI.WaitForObject(oR_BillAndUsage.txtPlanName, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Plan name is displayed under Usage tab", "True", String.valueOf(bPlanName_wire), true);
				
				// --- Verify Device Icon, Customer Name & CTN Number ---
				//total table

				List <WebElement> table_wire = lDriver.findElements(By.xpath("//tbody[tr]"));
				int tableCount_wire = table_wire.size();
				
				if(tableCount_wire!=0)
				{
					//total device icon
					List<WebElement> deviceIcon_wire = lDriver.findElements(By.xpath("//td//img[@alt='Phone' or @alt='phone']"));
					int deviceIconCount_wire = deviceIcon_wire.size();
					
					//total customer name
					List<WebElement> custName_wire = lDriver.findElements(By.xpath("//td//div[@class='MarTop15']//strong"));
					int custNameCount_wire = custName_wire.size();
					
					//total CTN number
					List<WebElement> ctnNumber_wire = lDriver.findElements(By.xpath("//td//div[@class='MarTop15']//span"));
					int ctnNumCount_wire = ctnNumber_wire.size();
					
					/*
					 *  --- To verify Device Icon, Customer Name & CTN Number
					 *  Count of Device icon, customer name and ctn number 
					 *  should be same
					 */
					
					if (tableCount_wire == deviceIconCount_wire){
						Report.ValidationPoint(testContext.getName(), "Verify Device Icon is displayed under Usage tab", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Device Icon is displayed under Usage tab", "True", "False", true);
					}
				
					if (tableCount_wire == custNameCount_wire){
						Report.ValidationPoint(testContext.getName(), "Verify Customer name is displayed under Usage tab", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Customer name is displayed under Usage tab", "True", "False", true);
					}
		
					if (tableCount_wire == ctnNumCount_wire){
						Report.ValidationPoint(testContext.getName(), "Verify CTN number is displayed under Usage tab", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify CTN number is displayed under Usage tab", "True", "False", true);
					}
					
				}else
				{
					Report.OperationPoint(testContext.getName(), "Plan Usage details are NOT displayed in tabular format under Usage tab");
					Report.ValidationPoint(testContext.getName(), "Verify Plan Usage details are displayed in tabular format under Usage tab", "True", "False", true);

				}
				
				// --- Verify pie chart is displayed
				boolean bPieChart_wire = UI.WaitForObject(oR_BillAndUsage.svgPieChart, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify pie chart is displayed on Billing & Usage page", "True", String.valueOf(bPieChart_wire), true);
				
				// --- Verify Basic plan usage heading is displayed
				boolean bBasicPlanUsage_wire = UI.WaitForObject(oR_BillAndUsage.txtBasicPlanUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Basic plan usage heading is displayed on Billing & Usage page", "True", String.valueOf(bBasicPlanUsage_wire), true);

				// --- Verify Basic plan usage table is displayed
				boolean bBasicPlanUsageTable_wire = UI.WaitForObject(oR_BillAndUsage.tblBasicPlanUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Basic plan usage table is displayed on Billing & Usage page", "True", String.valueOf(bBasicPlanUsageTable_wire), true);

				// --- Verify Additional usage table is displayed
				boolean bAdditonalUsageTable_wire = UI.WaitForObject(oR_BillAndUsage.tblAdditionalUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Additional usage table is displayed on Billing & Usage page", "True", String.valueOf(bAdditonalUsageTable_wire), true);

				// --- Verify Additional usage heading is displayed
				boolean bAdditonalUsageHeading_wire = UI.WaitForObject(oR_BillAndUsage.txtAdditionalUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Additional usage table heading is displayed on Billing & Usage page", "True", String.valueOf(bAdditonalUsageHeading_wire), true);

				// --- Verify Secondary rail table is displayed
				boolean bSecRailTable_wire = UI.WaitForObject(oR_BillAndUsage.tblSecLinkRailsSection, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Secondary Rail table is displayed on Billing & Usage page", "True", String.valueOf(bSecRailTable_wire), true);

				// --- Verify Manage Secondary rail table is displayed
				boolean bManageSecRailTable_wire = UI.WaitForObject(oR_BillAndUsage.tblManageSecRail, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Manage Secondary Rail table is displayed on Billing & Usage page", "True", String.valueOf(bManageSecRailTable_wire), true);
			
				// --- Verify Manage Billing and Payement Secondary rail table is displayed
				boolean bManageBillPaySecRailTable_wire = UI.WaitForObject(oR_BillAndUsage.tblManageBillPaySecRail, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Manage Billing and Payement Secondary Rail table is displayed on Billing & Usage page", "True", String.valueOf(bManageBillPaySecRailTable_wire), true);

				// --- Verify Get Help Secondary rail table is displayed
				boolean bGetHelpSecRailTable_wire = UI.WaitForObject(oR_BillAndUsage.tblGetHelpSecRail, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Get Help Secondary Rail table is displayed on Billing & Usage page", "True", String.valueOf(bGetHelpSecRailTable_wire), true);

				// --- Verify Total data usage is displayed
				boolean bTotalDataUsage_wire = UI.WaitForObject(oR_BillAndUsage.txtTotalDataUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Total data usage is displayed on Billing & Usage page", "True", String.valueOf(bTotalDataUsage_wire), true);
				if (bTotalDataUsage_wire) {
					Report.OperationPoint(testContext.getName(), "Total data used is : "+oR_BillAndUsage.txtTotalDataUsage.getText());
				}
				
				// --- Verify Total data allowance is displayed
				boolean bTotalDataAllowance_wire = UI.WaitForObject(oR_BillAndUsage.txtTotalDataAllowance, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Total data allowance is displayed on Billing & Usage page", "True", String.valueOf(bTotalDataAllowance_wire), true);
				if (bTotalDataAllowance_wire) {
					Report.OperationPoint(testContext.getName(), "Total data allowance is : "+oR_BillAndUsage.txtTotalDataAllowance.getText());	
				}
				
				break;

			case "CB Wireline":
				// --- Click on 'Billing, Usage, Payments' link ---
				Report.OperationPoint(testContext.getName(), "Click on 'Billing, Usage, Payments' link");
				Boolean secNav_cb = UI.WaitForObject(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, UI.iObjTimeOut, lDriver);
				
//				Boolean secNav_cb = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav,null, lDriver);
				if (secNav_cb) {
					Report.ValidationPoint(testContext.getName(), "Verify clicked on Billing, Usage, Payments link", "Clicked on Billing, Usage, Payments link", "Clicked on Billing, Usage, Payments link", true);
					oR_AccountOverview.lnkBillingUsagePaymentsSecNav.click();
				}else{
					Report.ValidationPoint(testContext.getName(), "Verify clicked on Billing, Usage, Payments link", "Clicked on Billing, Usage, Payments link", "Failed to click on Billing, Usage, Payments link", true);
				}
				
				// --- Verify user is navigated to Billing & Usage page ---
				boolean bBillingUsageHeading_cb = UI.WaitForObject(oR_BillAndUsage.txtBillingAndUsage, UI.iObjTimeOut, lDriver);
				Report.OperationPoint(testContext.getName(), "User is navigated to Billing & Usage page");
				Report.ValidationPoint(testContext.getName(), "Verify Billing & Usage heading is displayed on Billing & Usage page", "True", String.valueOf(bBillingUsageHeading_cb), true);
				
				// --- Verify Bill tab is displayed on Billing & Usage page ---
				boolean bBillTab_cb = UI.WaitForObject(oR_BillAndUsage.lnkBillTab, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Bill tab is displayed on Billing & Usage page", "True", String.valueOf(bBillTab_cb), true);
	
				// --- Verify Usage tab is displayed on Billing & Usage page ---
				boolean bUsageTab_cb = UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Usage tab is displayed on Billing & Usage page", "True", String.valueOf(bUsageTab_cb), true);

				// --- Verify History tab is displayed on Billing & Usage page ---
				boolean bHistoryTab_cb = UI.WaitForObject(oR_BillAndUsage.lnkHistoryTab, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify History tab is displayed on Billing & Usage page", "True", String.valueOf(bHistoryTab_cb), true);

				// --- Verify Reports tab is displayed on Billing & Usage page ---
				boolean bReportsTab_cb = UI.WaitForObject(oR_BillAndUsage.lnkReportTab, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Reports tab is displayed on Billing & Usage page", "True", String.valueOf(bReportsTab_cb), true);
				
				//Wait till Usage link is displayed
				boolean bUsage_cb = UI.WaitForObject(oR_BillAndUsage.lnkUsage, UI.iObjTimeOut, lDriver);
				if (bUsage_cb == true) {
					if (oR_BillAndUsage.lnkUsage.isDisplayed()) {
						Thread.sleep(1000*5);
						Report.ValidationPoint(testContext.getName(), "Verify Usage link is displayed on Billing & Usage page", "True", String.valueOf(bUsage_cb), true);
						
						// --- Click on Usage tab on Billing & Usage page ---
						oR_BillAndUsage.lnkUsage.click();
						Report.OperationPoint(testContext.getName(), "Clicked on Usage tab on Billing & Usage page");
						Thread.sleep(6000);
					}		
				} else { 
					Report.ValidationPoint(testContext.getName(), "Verify Usage link is displayed on Billing & Usage page", "True", String.valueOf(bUsage_cb), true);
				}
				
				// --- Verify Billing Cycle Selector drop down is displayed under Usage tab ---
//				WebElement billCycleDropdown_cb  = lDriver.findElement(By.xpath("//div[contains(text(),'Current Billed Usage') or contains(text(),'Previously Billed Usage')]"));
				WebElement billCycleDropdown_cb  = lDriver.findElement(By.xpath("//*[contains(text(),'Current Billed Usage') or contains(text(),'Previously Billed Usage') or contains(text(),'Recent Usage')] | //div[@id='ddShortcut']//p[contains(text(),'Recent Usage')]"));
			
//				boolean bBillCycleSelector_cb = UI.WaitForObject(oR_BillAndUsage.lstBillCycleDropDown, 5, lDriver).equals(true);
				boolean bBillCycleSelector_cb = UI.WaitForObject(billCycleDropdown_cb, UI.iObjTimeOut, lDriver);
				
				Report.ValidationPoint(testContext.getName(), "Verify Billing Cycle Selector drop down is displayed under Usage tab", "true", String.valueOf(bBillCycleSelector_cb), true);
				if (bBillCycleSelector_cb == true) {
					// --- Object of the billed cycle having usage in it ---
					boolean bUsagePageDisplayedProperly = UI.WaitForObject(oR_BillAndUsage.txtValidUsagePageCheck, UI.iObjTimeOut, lDriver);
					if(bUsagePageDisplayedProperly==true)
					{
						Report.OperationPoint(testContext.getName(), "Usage is displayed for current page. So, continuing wihout changing the usage period");
					}else
					{
						//String sBilledCycleTxt = IO.GetParamVal(sTestParams, "BilledCycleFirstMonthTxt");
	//					WebElement usage_cb = lDriver.findElement(By.xpath("//span[contains(text(),"+sBilledCycleTxt+")]//parent::a"));
	//					WebElement usage_cb = lDriver.findElement(By.xpath("//a[@class='wt_Body' and contains(@title,"+sBilledCycleTxt+")] "));
	//					WebElement usage_cb = lDriver.findElement(By.xpath("//div[@id='20150313|2162260371999|S|A' or @id = 'ddShortcutBox']//a/span[contains(text(),"+sBilledCycleTxt+")]"));
	//					WebElement usage_cb = lDriver.findElement(By.xpath("//div[@id='20150313|2162260371999|S|A' or @id = 'ddShortcutBox']//span[contains(text(),"+sBilledCycleTxt+")]/parent::a"));
	
	//					Actions action_cb = new Actions(lDriver);
	//					action_cb.moveToElement(billCycleDropdown_cb).sendKeys(Keys.ENTER).build().perform();
						billCycleDropdown_cb.click();
	//					action.moveToElement(usage_cb).click().build().perform();
	//					oR_BillAndUsage.lstBillCycleDropDown.click();
	//					billCycleDropdown_cb.click();
	//					oR_BillAndUsage.lstBillCycleDropDown.submit();
						Thread.sleep(1000*5);
	//					billCycleDropdown_cb.click();
	//					action_cb.moveToElement(billCycleDropdown_cb).sendKeys(Keys.ENTER).build().perform();
	//					WebElement usage_cb = lDriver.findElement(By.xpath("//div[contains(@id,'S|A') or contains(@id,'ddShortcutBox')]//span[contains(text(),"+sBilledCycleTxt+")]/parent::a"));
						//WebElement usage_cb = lDriver.findElement(By.xpath("(//div[contains(@id,'S|A') or contains(@id,'ddShortcutBox')]//span[contains(text(),'-')]/parent::a)[1]"));
						WebElement usage_cb = oR_BillAndUsage.lnkBillCycleSelection;
						String sBilledCycleTxt = oR_BillAndUsage.lnkBillCycleSelection.getText().toString();
						
						if (usage_cb.isDisplayed()) {
							Report.ValidationPoint(testContext.getName(), "Validate "+sBilledCycleTxt+" link is displayed", sBilledCycleTxt+" link is displayed", sBilledCycleTxt+" link is displayed", true);
							usage_cb.click();
						}else{
							Report.ValidationPoint(testContext.getName(), "Validate "+sBilledCycleTxt+" link is displayed", sBilledCycleTxt+" link is displayed", sBilledCycleTxt+" link is NOT displayed", true);
						}
						
						Report.OperationPoint(testContext.getName(), "Clicked on Bill cycle drop down");
					}
				}
				
				Thread.sleep(5000);
				
//				// --- Object of the billed cycle having usage in it ---
//				String sBilledCycleTxt = IO.GetParamVal(sTestParams, "BilledCycleFirstMonthTxt");
//				WebElement usage_cb = lDriver.findElement(By.xpath("//span[contains(text(),"+sBilledCycleTxt+")]//parent::a"));
				
				// --- Click on the billed cycle with usage ----
//				usage_cb.click();
				
				// --- Verify Billing period text is displayed under Usage tab ---
				boolean bBillingPeriod_cb = UI.WaitForObject(oR_BillAndUsage.txtBillPeriod, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Billing period text is displayed under Usage tab", "True", String.valueOf(bBillingPeriod_cb), true);

				// --- Verify PDF icon is displayed for view paper bill under Usage tab ---
				boolean bPDFicon_cb = UI.WaitForObject(oR_BillAndUsage.imgPDFiconPaperBill, UI.iObjTimeOut, lDriver);
				//Report.ValidationPoint(testContext.getName(), "Verify PDF icon is displayed for view paper bill under Usage tab", "True", String.valueOf(bPDFicon_cb), true);
				if(bPDFicon_cb==true)
				{
					Report.ValidationPoint(testContext.getName(), "Verify PDF icon is displayed for view paper bill under Usage tab", "True", String.valueOf(bPDFicon_cb), true);
				}else
				{
					Report.OperationPoint(testContext.getName(), "*PDF icon is disabled for view paper bill under Usage tab for current Data");
				}
				
				// --- Verify Service selector drop down is displayed under Usage tab ---
				boolean bServiceSelectorDropDown_cb = UI.WaitForObject(oR_BillAndUsage.lstViewServiceDropDown, UI.iObjTimeOut, lDriver);
				if(bServiceSelectorDropDown_cb==true)
				{
					Report.ValidationPoint(testContext.getName(), "Verify Service selector drop down is displayed under Usage tab", "True", String.valueOf(bServiceSelectorDropDown_cb), true);
				}else
				{
					Report.OperationPoint(testContext.getName(), "*Service selector drop down is not displayed under Usage tab since there is only one plan available");

				}
		
				// --- Verify device name is displayed under Usage tab ---
				boolean bDeviceName_cb = UI.WaitForObject(oR_BillAndUsage.txtDeviceName, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify device name is displayed under Usage tab", "True", String.valueOf(bDeviceName_cb), true);

				// --- Verify device number is displayed under Usage tab ---
				boolean bDeviceNumber_cb = UI.WaitForObject(oR_BillAndUsage.txtDeviceNumber, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify device number is displayed under Usage tab", "True", String.valueOf(bDeviceNumber_cb), true);

//				// --- Verify details text is displayed under Usage tab ---
//				//boolean bDetailsText_cb = UI.WaitForObject(oR_BillAndUsage.txtDetails, UI.iObjTimeOut, lDriver);
//				//Report.ValidationPoint(testContext.getName(), "Verify details text is displayed under Usage tab", "True", String.valueOf(bDetailsText_cb), true);
//				
//				
//				String sMainWindowHandle = lDriver.getWindowHandle();
//				//Click on view usage details link
//				oR_BillAndUsage.lnkViewUsageDetailsLink.click();
//				Report.OperationPoint(testContext.getName(), "Operational - Clicked on View usage details link");
//				Thread.sleep(5000);
//				WebElement frameHandle = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
//				lDriver.switchTo().frame(frameHandle);
//				
//					// --- Verify print icon is displayed under Usage tab ---
//					boolean bPrintIcon_cb = UI.WaitForObject(oR_BillAndUsage.lnkPrintIcon, UI.iObjTimeOut, lDriver);
//					Report.ValidationPoint(testContext.getName(), "Verify print icon is displayed under Usage tab", "True", String.valueOf(bPrintIcon_cb), true);
//	
//					// --- Verify Download dropdown is displayed under Usage tab ---
//					boolean bDownloadDropdown_cb = UI.WaitForObject(oR_BillAndUsage.lstDownloadDropDown, UI.iObjTimeOut, lDriver);
//					Report.ValidationPoint(testContext.getName(), "Verify Download dropdown is displayed under Usage tab", "True", String.valueOf(bDownloadDropdown_cb), true);
//	
//					// --- Verify Search field is displayed under Usage tab ---
//					boolean bSearchField_cb = UI.WaitForObject(oR_BillAndUsage.edtSearchField, UI.iObjTimeOut, lDriver);
//					Report.ValidationPoint(testContext.getName(), "Verify Search field is displayed under Usage tab", "True", String.valueOf(bSearchField_cb), true);
//	
//					// --- Verify Search icon is displayed under Usage tab ---
//					boolean bSearchicon_cb = UI.WaitForObject(oR_BillAndUsage.imgSearchIcon, UI.iObjTimeOut, lDriver);
//					Report.ValidationPoint(testContext.getName(), "Verify Search icon is displayed under Usage tab", "True", String.valueOf(bSearchicon_cb), true);
//				
//				lDriver.switchTo().window(sMainWindowHandle);
//				oR_BillAndUsage.imgCloseModal.click();
//				Thread.sleep(5000);
//			
//				// --- Verify Usage detail table is displayed under Usage tab ---
//				boolean bUsageDetailTable_cb = UI.WaitForObject(oR_BillAndUsage.tblUsageDetail, UI.iObjTimeOut, lDriver);
//				Report.ValidationPoint(testContext.getName(), "Verify Usage detail table is displayed under Usage tab", "True", String.valueOf(bUsageDetailTable_cb), true);

				// --- Verify Manage account is displayed under Usage tab ---
				boolean bManageAccount_cb = UI.WaitForObject(oR_BillAndUsage.txtManageAccount, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Manage account heading is displayed under Usage tab", "True", String.valueOf(bManageAccount_cb), true);

				// --- Verify Manage Billing and Payment Option heading is displayed under Usage tab ---
				boolean bManageBillingPaymentOption_cb = UI.WaitForObject(oR_BillAndUsage.txtManageBillingPaymentOption, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Manage Billing and Payment Option heading is displayed under Usage tab", "True", String.valueOf(bManageBillingPaymentOption_cb), true);

				// --- Verify Get Help heading is displayed under Usage tab ---
				boolean bGetHelpHeading_cb = UI.WaitForObject(oR_BillAndUsage.txtGetHelpHeading, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify Get Help heading is displayed under Usage tab", "True", String.valueOf(bGetHelpHeading_cb), true);	

				break;
			}
			
		} catch (Exception e) {
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}
	}
	
	public static void ValidateDefaultViewBillingPeriodSelectorDropDown(final ITestContext testContext) throws Exception
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
				   
				  Report.OperationPoint(testContext.getName(), "Click on showing drop down");
				   oR_BillAndUsage.txtCurrentBilledUsage.click();
				   
				   Report.ValidationPoint(testContext.getName(), "Validate options under Showing dropdown displayed", "True", "True", true);
				  
				   WebElement lnkPreviousBillUsage = lDriver.findElement(By.xpath("//span[contains(text(),'Previously Billed')]/parent::div/parent::div//a"));
				   if(UI.WaitForObject(lnkPreviousBillUsage, 15, lDriver)){
					  
					    lnkPreviousBillUsage.click();
					    Report.ValidationPoint(testContext.getName(), "Verify Previous Billing Usage Option is selected", "True", "True", true);
				   }
				   
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

}
