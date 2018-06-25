package com.AppSpecificLibrary;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;

import com.OR.MyATT.OR_AccountOverview;
import com.OR.MyATT.OR_HomePhoneService;
import com.OR.MyATT.OR_InternetService;
import com.OR.MyATT.OR_MyWirelessPlan;
import com.OR.MyATT.OR_PhoneAndDevices;
import com.OR.MyATT.OR_TelevisionService;
import com.OR.MyATT.OR_ViewOrChangeRatePlan;
import com.SupportingFiles.IO;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;
import com.SupportingFiles.UI;
@SuppressWarnings("all")
public class MyServices_Sanity extends LaunchAndLogout  {
	
static Hashtable<String, String> sTestParams = new Hashtable<String, String>();
	
	/****************************************************************
	 * Function Name - VerifyMyServicesPage() 
	 * Description-    This function use to verify the MyServices page  
	 * Parameters -    None
	 * Date created -  30th July 2015
	 * Developed by -  Ravinder Mehra
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/

	public static void VerifyMyServicesPage(final ITestContext testContext) 
			throws Exception
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_MyWirelessPlan oR_MyWirelessPlan = PageFactory.initElements(lDriver, OR_MyWirelessPlan.class);
		
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		
		/*--- Need to retrieve DataType for Overview page verification */
		String sLoginType= IO.GetParamVal(sTestParams, "DataType");
		
		
		try{
			
			/*---Switch to desired data type as per data sheet ---*/
			switch (sLoginType) {
			
				case "Wireless": case "wireless":
					  
					  /*----Verify Wireless Product landing Page------------*/
					  VerifyWirelessProductLandingPage(testContext);	
					  
					  /*----Verify Manage My Device & Features page Page------------*/
					  VerifyManageMyDeviceAndFeaturePage(testContext);
					  
					  /*----Verify Change My Plan Page------------*/
					  VerifyChangeMyPlanPage(testContext);					  
																
					  break;
					
				case "Uverse": case "uverse":
					
					  /*----Verify My TV Service page------------*/
					  VerifyMyTVServicePage(testContext);		
					  
					  /*----Verify HomePhone Service page------------*/ 
					  VerifyMyHomePhoneServicePage(testContext);
					  
					  /*----Verify Internet Service page------------*/
					  VerifyMyInternetServicePage(testContext);
					  
					  break;
					  
			    case "Wireline":  case "wireline":
			    	
			    	  /*----Verify My TV Service page------------*/
			    	  VerifyHomePhonePageForWireline(testContext);
					
			    	  break;	
					
			
			}
			
		} catch (Exception e)
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}

	}
	
	/****************************************************************
	 * Function Name - VerifyWirelessProductLandingPage() 
	 * Description-    This function use to verify Wireless Product landing page  
	 * Parameters -    None
	 * Date created -  30th July 2015
	 * Developed by -  Ravinder Mehra
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws Exception 
	 * @throws  
	 * @throws HeadlessException 
	 ***************************************************************/

	public static void VerifyWirelessProductLandingPage(final ITestContext testContext) throws  Exception {
		
		WebDriver lDriver = UI.getDriver(testContext.getName()); 
		
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_MyWirelessPlan oR_MyWirelessPlan = PageFactory.initElements(lDriver, OR_MyWirelessPlan.class);
		
		try{
			
			/*--------Verify & Select Wireless link from 2nd Navigation--------*/
			if(UI.WaitForObject(oR_AccountOverview.lnkWirelessSecNav, UI.iObjTimeOut, lDriver).equals(true)){
				Report.ValidationPoint(testContext.getName(), "Verify & select Wireless link from 2ndry Nav bar", "True", "True", true);
				oR_AccountOverview.lnkWirelessSecNav.click();				
				Thread.sleep(1000);
				/*--------Verify Wireless Product landing page displayed--------*/
				if(UI.WaitForObject(oR_MyWirelessPlan.txtMyWirelessService, UI.iObjTimeOut, lDriver).equals(true)){
					Report.ValidationPoint(testContext.getName(), "Verify My Wireless Plan page displayed", "True", "True", true);
					Report.OperationPoint(testContext.getName(), "Information - Header- My Wireless Service displayed on page");
					
					/*--------Verify Plan friendly name displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.txtPlanSummarySectionHeader_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sPlanFriendlyName=oR_MyWirelessPlan.txtPlanSummarySectionHeader_1511.getText().trim();
						if(!sPlanFriendlyName.isEmpty()){
							Report.ValidationPoint(testContext.getName(), "Verify Plan Friendly Name displayed on wireless product landing page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Plan Friendly Name displayed as-"+sPlanFriendlyName);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify Plan Friendly Name displayed on wireless product landing page", "Plan Friendly Name displayed", "Plan Friendly Name not displayed", true);
						}					
						
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Plan Friendly Name displayed on wireless product landing page", "Plan Friendly Name displayed", "Plan Friendly Name not displayed", true);
					}
					
					/*--------Verify Heading "Monthly Cost" displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.txtMonthlyCostSubHeader, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Monthly Cost' displayed on wireless product landing page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Monthly Cost' displayed on wireless product landing page", "Heading 'Monthly Cost' displayed", "Heading 'Monthly Cost' not displayed", true);
					}
					
					/*--------Verify Cost of the plan with $ amount displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.txtMonthlyCost, UI.iObjTimeOut, lDriver).equals(true)){
						String sCostOfPlan=oR_MyWirelessPlan.txtMonthlyCost.getText().trim();
						if(sCostOfPlan.contains("$")){
							Report.ValidationPoint(testContext.getName(), "Verify cost of the plan with '$ amount displayed on wireless product landing page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Monthly cost displayed as-"+sCostOfPlan);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify cost of the plan with '$ amount displayed on wireless product landing page", "Cost of the plan with $ amount displayed", "Cost of the plan with $ amount not displayed", true);
						}					
						
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify cost of the plan with '$ amount displayed on wireless product landing page", "Cost of the plan with $ amount displayed", "Cost of the plan with $ amount not displayed", true);
					}
					
					/*--------Verify "Change Plan" CTA displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.btnChangePlan, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Change Plan' CTA displayed on wireless product landing page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Change Plan' CTA displayed on wireless product landing page", "Change Plan CTA displayed", "Change Plan CTA not displayed", true);
					}
					
					/*--------Verify "View all included feature" link displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.lnkViewAllFeatures, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'View all included feature' link displayed on wireless product landing page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'View all included feature' link displayed on wireless product landing page", "View all included feature link displayed", "View all included feature link not displayed", true);
					}
					
					/*--------Verify "View Cost Summary" link displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.lnkViewCostSummary, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'View Cost Summary' link displayed on wireless product landing page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'View Cost Summary' link displayed on wireless product landing page", "View Cost Summary link displayed", "View Cost Summary link not displayed", true);
					}
					
					/*--------Verify heading "Manage My Device & Features" displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.txtManageDeviceAndFeatures_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify heading 'Manage My Device & Features' displayed on wireless product landing page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify heading 'Manage My Device & Features' displayed on wireless product landing page", "Heading 'Manage My Device & Features' displayed", "Heading 'Manage My Device & Features' not displayed", true);
					}
					
					/*--------Verify Device details section--------*/
					
					/*--------Verify Device image displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.imgDevice_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify device image displayed under device details section on wireless product landing page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify device image displayed under device details section on wireless product landing page", "Device image displayed under device details section", "Device image not displayed under device details section", true);
					}
					
					/*--------Verify customer name displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.txtCustomerName_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sCustName=oR_MyWirelessPlan.txtCustomerName_1511.getText().trim();
						if(!sCustName.isEmpty()){
							Report.ValidationPoint(testContext.getName(), "Verify Customer Name displayed under device details section on wireless product landing page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Customer Name displayed as-"+sCustName);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify Customer Name displayed under device details section on wireless product landing page", "Customer Name displayed under device details section", "Customer Name not displayed under device details section", true);
						}																
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Customer Name displayed under device details section on wireless product landing page", "Customer Name displayed under device details section", "Customer Name not displayed under device details section", true);
					}
					
					/*--------Verify customer Number displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.txtCustomerNumber_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sCustNum=oR_MyWirelessPlan.txtCustomerNumber_1511.getText().trim();
						if(!sCustNum.isEmpty()){
							Report.ValidationPoint(testContext.getName(), "Verify Customer Number displayed under device details section on wireless product landing page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Customer Number displayed as-"+sCustNum);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify Customer Number displayed under device details section on wireless product landing page", "Customer Name displayed under device details section", "Customer Name not displayed under device details section", true);
						}																
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Customer Number displayed under device details section on wireless product landing page", "Customer Number displayed under device details section", "Customer Number not displayed under device details section", true);
					}
					
					/*--------Verify Manage link displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.lnkManage, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage' link displayed under device details section on wireless product landing page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage' link displayed under device details section on wireless product landing page", "Manage link displayed under device details section", "Manage link not displayed under device details section", true);
					}
					
					/*--------Verify "Add a new device" link displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.lnkAddADevice, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Add a new device' link displayed under device details section on wireless product landing page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Add a new device' link displayed under device details section on wireless product landing page", "Add a new device link displayed under device details section", "Add a new device link not displayed under device details section", true);
					}
					
					/*--------Verify Footer link displayed--------*/
					
					/*--------Verify Footer header 'Manage my account' displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.txtFooterManageMyAccount_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage My Account' heading displayed on page footer on wireless product landing page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage My Account' heading displayed on page footer on wireless product landing page", "Manage My Account heading displayed on page footer", "Manage My Account heading not displayed on page footer", true);
					}
					
					/*--------Verify Footer header 'My Wireless Services' displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.txtFooterMyWirelessService_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'My Wireless Services' heading displayed on page footer on wireless product landing page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'My Wireless Services' heading displayed on page footer on wireless product landing page", "My Wireless Services heading displayed on page footer", "My Wireless Services heading not displayed on page footer", true);
					}
					
					/*--------Verify Footer header 'Get help & Support' displayed--------*/
					if(UI.WaitForObject(oR_MyWirelessPlan.txtFooterGetHelpSupport_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Get help & Support' heading displayed on page footer on wireless product landing page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Get help & Support' heading displayed on page footer on wireless product landing page", "Get help & Support heading displayed on page footer", "Get help & Support heading not displayed on page footer", true);
					}	
									
									
				}else{
					Report.ValidationPoint(testContext.getName(), "Verify My Wireless Plan page displayed", "My Wireless Plan page displayed", "My Wireless Plan page not displayed", true);
				}				
				
			}else{
				Report.ValidationPoint(testContext.getName(), "Verify & select Wireless link from 2ndry Nav bar", "Wireless link selected from 2ndry Nav bar", "Wireless link not displayed on 2ndry Nav bar", true);
			}
			
			
			
			
		}catch(Exception e){
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);			
		}
		
		
		
		
		
	}
	
	/****************************************************************
	 * Function Name - VerifyManageMyDeviceAndFeaturePage() 
	 * Description-    This function use to verify Manage My Device and feature page  
	 * Parameters -    None
	 * Date created -  30th July 2015
	 * Developed by -  Ravinder Mehra
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws Exception 
	 * @throws  
	 * @throws HeadlessException 
	 ***************************************************************/
	public static void VerifyManageMyDeviceAndFeaturePage(final ITestContext testContext) throws  Exception {
				
        WebDriver lDriver = UI.getDriver(testContext.getName()); 		
		
        OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
        OR_PhoneAndDevices oR_PhoneAndDevices = PageFactory.initElements(lDriver, OR_PhoneAndDevices.class);
        /*----------Navigate to Manage My device and Feature page-------------*/
        //Select My Phone/Device under Wireless 2ndary Nav bar//
        
        
       try{        	
        	 if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav ,oR_AccountOverview.lnkPhoneDevicesTertNav_1511)){
        		    			
    			/*-----Verify Manage my device and feature page displayed------- */
        		if(UI.WaitForObject(oR_PhoneAndDevices.txtPgHrManageMyDeviceAndFeature_1511, UI.iObjTimeOut, lDriver).equals(true)){
        			Report.ValidationPoint(testContext.getName(), "Verify Manage My Device & Feature page displayed", "True", "True", true);
					Report.OperationPoint(testContext.getName(), "Information - Header- Manage My Device & Feature displayed on page");
					
					/*-----Verify Account selection module displayed------- */					
					if(UI.WaitForObject(oR_PhoneAndDevices.lnkDropdown, UI.iObjTimeOut, lDriver).equals(true)){		
						oR_PhoneAndDevices.lnkDropdown.click();
						Thread.sleep(500);
					   Report.ValidationPoint(testContext.getName(), "Verify Account selection module drop down displayed on Manage My Device & Feature page", "True", "True", true);					   												
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Account selection module drop down displayed on Manage My Device & Feature page", "Account selection module drop down displayed", "Account selection module drop down not displayed", true);
					}
					
                    /*--------Verify Device details section--------*/
					
					/*--------Verify Device image displayed--------*/
					if(UI.WaitForObject(oR_PhoneAndDevices.imgDevice_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify device image displayed under device details section on Manage My Device & Feature page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify device image displayed under device details section on Manage My Device & Feature page", "Device image displayed under device details section", "Device image not displayed under device details section", true);
					}					
					
					/*--------Verify customer name displayed--------*/
					if(UI.WaitForObject(oR_PhoneAndDevices.txtCustomerName_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sCustName=oR_PhoneAndDevices.txtCustomerName_1511.getText().trim();
						if(!sCustName.isEmpty()){
							Report.ValidationPoint(testContext.getName(), "Verify Customer Name displayed under device details section on Manage My Device & Feature page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Customer Name displayed as-"+sCustName);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify Customer Name displayed under device details section on Manage My Device & Feature page", "Customer Name displayed under device details section", "Customer Name not displayed under device details section", true);
						}																
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Customer Name displayed under device details section on Manage My Device & Feature page", "Customer Name displayed under device details section", "Customer Name not displayed under device details section", true);
					}
										
					/*--------Verify customer Number displayed--------*/
					if(UI.WaitForObject(oR_PhoneAndDevices.txtCustomerNumber_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sCustNum=oR_PhoneAndDevices.txtCustomerNumber_1511.getText().trim();
						if(!sCustNum.isEmpty()){
							Report.ValidationPoint(testContext.getName(), "Verify Customer Number displayed under device details section on Manage My Device & Feature page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Customer Number displayed as-"+sCustNum);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify Customer Number displayed under device details section on Manage My Device & Feature page", "Customer Name displayed under device details section", "Customer Name not displayed under device details section", true);
						}																
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Customer Number displayed under device details section on Manage My Device & Feature page", "Customer Number displayed under device details section", "Customer Number not displayed under device details section", true);
					}			
									
					
					/*--------Verify Manage link displayed--------
					if(UI.WaitForObject(oR_MyWirelessPlan.lnkManage, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage' link displayed under device details section on wireless product landing page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage' link displayed under device details section on wireless product landing page", "Manage link displayed under device details section", "Manage link not displayed under device details section", true);
					}*/
					
					/*--------Verify Manage Device heading displayed--------*/
					if(UI.WaitForObject(oR_PhoneAndDevices.txtHeaderManageDevice_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage Device' heading displayed on Manage My Device & Feature page", "True", "True", true);
						/*--------verify links under Manage Device section------------*/
						List<WebElement> links=lDriver.findElements(By.xpath("//div[@class='myAddOns']//a"));
						if(links.size()>0){
							Report.ValidationPoint(testContext.getName(), "Verify links displayed under Manage Device section heading", "True", "True", true);							
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify links displayed under Manage Device section heading", "links displayed under Manage Device heading", "There is no link present under Manage Device section heading", true);
						}
												
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage Device' heading displayed on Manage My Device & Feature page", "Manage Device heading displayed", "Manage Device heading not displayed", true);
					}
					
					/*--------Verify Heading 'My Current Feature' displayed--------*/
					if(UI.WaitForObject(oR_PhoneAndDevices.txtHeaderMyCurrentFeatures_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'My Current Features' heading displayed on Manage My Device & Feature page", "True", "True", true);
						/*--------verify current feature displayed------------*/
						List<WebElement> currFeatures=lDriver.findElements(By.xpath("//*[contains(@class,'myAccount bot')]//a"));
						if(currFeatures.size()>0){
							Report.ValidationPoint(testContext.getName(), "Verify current features displayed under My Current Features section heading", "True", "True", true);							
						}else{
							Report.OperationPoint(testContext.getName(),"*Current fetaures are NOT displayed under My Current features section");
						}
												
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'My Current Features' heading displayed on Manage My Device & Feature page", "My Current Features heading displayed", "My Current Features heading not displayed", true);
					}
					
					/*--------Verify Heading 'More features for my device' displayed--------*/
					if(UI.WaitForObject(oR_PhoneAndDevices.txtHeaderMoreFeaturesForMyDevice_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'More features for my device' heading displayed on Manage My Device & Feature page", "True", "True", true);
						/*--------verify available feature displayed------------*/
						List<WebElement> MoreFeatures=lDriver.findElements(By.xpath("//*[starts-with(@class,'toggleCheck ')]//a"));
						if(MoreFeatures.size()>0){
							Report.ValidationPoint(testContext.getName(), "Verify features eligible for the ctn displayed under More features section heading", "True", "True", true);							
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify features eligible for the ctn displayed under More features section heading", "Eligible features displayed under More features section heading", "There is no features displayed under More features section heading", true);
						}
												
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'More features for my device' heading displayed on Manage My Device & Feature page", "More features for my device heading displayed", "More features for my device heading not displayed", true);
					}
					
					/*--------Verify Footer link displayed--------*/
					
					/*--------Verify Footer header 'Manage my account' displayed--------*/
					if(UI.WaitForObject(oR_PhoneAndDevices.txtFooterManageMyAccount_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage My Account' heading displayed on page footer on Manage My Device & Feature page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage My Account' heading displayed on page footer on Manage My Device & Feature page", "Manage My Account heading displayed on page footer", "Manage My Account heading not displayed on page footer", true);
					}
					
					/*--------Verify Footer header 'My Device & Features' displayed--------*/
					if(UI.WaitForObject(oR_PhoneAndDevices.txtFooterMyDeviceFeatures_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'My Device & Features' heading displayed on page footer on Manage My Device & Feature page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'My Device & Features' heading displayed on page footer on Manage My Device & Feature page", "My Device & Features heading displayed on page footer", "My Device & Features heading not displayed on page footer", true);
					}
					
					/*--------Verify Footer header 'Get help & Support' displayed--------*/
					if(UI.WaitForObject(oR_PhoneAndDevices.txtFooterGetHelpSupport_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Get help & Support' heading displayed on page footer on Manage My Device & Feature page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Get help & Support' heading displayed on page footer on Manage My Device & Feature page", "Get help & Support heading displayed on page footer", "Get help & Support heading not displayed on page footer", true);
					}	
						
					
					
					
					
					
					
					
					
					
					
        		}else{
        			
        			Report.ValidationPoint(testContext.getName(), "Verify Manage My Device & Feature page displayed", "Manage My Device & Feature page displayed", "Manage My Device & Feature page not displayed", true);
        		}
    	    			
    		}   	
        	
         }
         catch(Exception e){
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);			
		}
		
        

		
	}

	/****************************************************************
	 * Function Name - VerifyChangeMyPlanPage() 
	 * Description-    This function use to verify Change my plan page  
	 * Parameters -    None
	 * Date created -  30th July 2015
	 * Developed by -  Ravinder Mehra
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws Exception 
	 * @throws  
	 * @throws HeadlessException 
	 ***************************************************************/
	public static void VerifyChangeMyPlanPage(final ITestContext testContext) throws  Exception {
				
        WebDriver lDriver = UI.getDriver(testContext.getName()); 		
		//Boolean clicked=false;
		
        OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
        OR_ViewOrChangeRatePlan oR_ViewOrChangeRatePlan = PageFactory.initElements(lDriver, OR_ViewOrChangeRatePlan.class);
        /*----------Navigate to Manage My device and Feature page-------------*/
        //Select My Phone/Device under Wireless 2ndary Nav bar//      
      
       
        Thread.sleep(3000);
       try{        	
        	 if(UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav ,oR_AccountOverview.lnkChangeMyPlanTertiaryNav)){
        		
        		Thread.sleep(3000);
    			/*-----Verify View or Change My Plan page displayed------- */
        		if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtViewOrChangeRatePlan_1511, UI.iObjTimeOut, lDriver).equals(true)){
        			Report.ValidationPoint(testContext.getName(), "Verify View or Change My Plan page displayed", "True", "True", true);
					Report.OperationPoint(testContext.getName(), "Information - Header- View or Change My Plan displayed on page");
					
					/*-----Verify Account heading displayed------- */					
					if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtAccount, UI.iObjTimeOut, lDriver).equals(true)){		
						Report.ValidationPoint(testContext.getName(), "Verify 'Account' heading displayed on Change My Plan page", "True", "True", true);					   												
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Account' heading displayed on Change My Plan page", "Account heading displayed", "Account heading not displayed", true);
					}
					
					/*--------Verify Account Number displayed--------*/
					if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtAccountNumber_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sAccNumber=oR_ViewOrChangeRatePlan.txtAccountNumber_1511.getText().trim();
						if(!sAccNumber.isEmpty()){
							Report.ValidationPoint(testContext.getName(), "Verify Account Number displayed on Change My Plan page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Account Number displayed as-"+sAccNumber);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify Account Number displayed on Change My Plan page", "Account Number displayed", "Account Number not displayed", true);
						}																
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Account Number displayed on Change My Plan page", "Account Number displayed", "Account Number not displayed", true);
					}					
					
					/*-----Verify Account selection module displayed------- */					
					if(UI.WaitForObject(oR_ViewOrChangeRatePlan.lnkUSM_1511, UI.iObjTimeOut, lDriver).equals(true)){		
						oR_ViewOrChangeRatePlan.lnkUSM_1511.click();
						Thread.sleep(500);
					   Report.ValidationPoint(testContext.getName(), "Verify Account selection module drop down displayed on Change My Plan page", "True", "True", true);					   												
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Account selection module drop down displayed on Change My Plan page", "Account selection module drop down displayed", "Account selection module drop down not displayed", true);
					}
					try
					{
					
						if(lDriver.findElement(By.xpath("(//table//*[contains(text(),'Mobile Share')])[1]")).getText().toString().contains("Mobile Share"))
						{
							//Skip following steps for MobileShare plan, these are only validated for individual plans :
							/*
							 * -----Verify 'Current Rate Plan' heading displayed-------
							 * ---------Verify current plan displayed---------
							 * ---------Verify heading 'Choose a New Rate Plan' displayed---------
							 * ---------Verify heading "Nation Plan" displayed---------
							 */
							
						}else
						{
							//Check the below validations for all plans except MobileShare plan
							/*-----Verify 'Current Rate Plan' heading displayed------- */
							
							if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtCurrentRatePlan, UI.iObjTimeOut, lDriver).equals(true)){	
								String sText=oR_ViewOrChangeRatePlan.txtCurrentRatePlan.getText().trim();
								String aSplit[]=sText.split(":");
								if(aSplit.length>1){
									String sCTN=aSplit[1].trim();				
									if(!sCTN.isEmpty()){									
										Report.ValidationPoint(testContext.getName(), "Verify 'Current Rate Plan' heading with CTN displayed on Change My Plan page", "True", "True", true);
										Report.OperationPoint(testContext.getName(), "Information - CTN displayed as-"+sCTN);
									}else{
										Report.ValidationPoint(testContext.getName(), "Verify 'Current Rate Plan' heading with CTN displayed on Change My Plan page", "Current Rate Plan heading with CTN displayed", "CTN not displayed with heading", true);
									}
									
								}else{
									Report.ValidationPoint(testContext.getName(), "Verify 'Current Rate Plan' heading with CTN displayed on Change My Plan page", "Current Rate Plan heading with CTN displayed", "CTN not displayed with heading", true);
								}						
												   												
							}else{
								Report.ValidationPoint(testContext.getName(), "Verify 'Current Rate Plan' heading with CTN displayed on Change My Plan page", "Current Rate Plan heading with CTN displayed", "Current Rate Plan heading and CTN not displayed", true);
							}
							
							/*---------Verify current plan displayed---------*/
							if(UI.WaitForObject(oR_ViewOrChangeRatePlan.tblNationPlans, UI.iObjTimeOut, lDriver).equals(true)){		
								
							   Report.ValidationPoint(testContext.getName(), "Verify current plan displayed on Change My Plan page", "True", "True", true);					   												
							}else{
								Report.ValidationPoint(testContext.getName(), "Verify current plan displayed on Change My Plan page", "current plan displayed", "current plan not displayed", true);
							}
							
							/*---------Verify heading 'Choose a New Rate Plan' displayed---------*/
							if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtChooseNewRatePlan_1511, UI.iObjTimeOut, lDriver).equals(true)){		
								
							   Report.ValidationPoint(testContext.getName(), "Verify 'Choose a New Rate Plan' heading displayed on Change My Plan page", "True", "True", true);					   												
							}else{
								Report.ValidationPoint(testContext.getName(), "Verify 'Choose a New Rate Plan' heading displayed on Change My Plan page", "Choose a New Rate Plan heading displayed", "Choose a New Rate Plan heading not displayed", true);
							}
							
							Thread.sleep(1000);
			
							/*---------Verify heading "Nation Plan" displayed---------*/
							if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtNationPlans, UI.iObjTimeOut, lDriver).equals(true)){							
							   Report.ValidationPoint(testContext.getName(), "Verify 'Nation Plans' heading displayed on Change My Plan page", "True", "True", true);					   												
							}else{
								Report.ValidationPoint(testContext.getName(), "Verify 'Nation Plans' heading displayed on Change My Plan page", "Nation Plans heading displayed", "Nation Plans heading not displayed", true);
							}
						}
					}catch(Exception e2)
					{
						Report.OperationPoint(testContext.getName(), "* No MobileShare plans are displayed");
						

						//Check the below validations for all plans except MobileShare plan
						/*-----Verify 'Current Rate Plan' heading displayed------- */
						
						if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtCurrentRatePlan, UI.iObjTimeOut, lDriver).equals(true)){	
							String sText=oR_ViewOrChangeRatePlan.txtCurrentRatePlan.getText().trim();
							String aSplit[]=sText.split(":");
							if(aSplit.length>1){
								String sCTN=aSplit[1].trim();				
								if(!sCTN.isEmpty()){									
									Report.ValidationPoint(testContext.getName(), "Verify 'Current Rate Plan' heading with CTN displayed on Change My Plan page", "True", "True", true);
									Report.OperationPoint(testContext.getName(), "Information - CTN displayed as-"+sCTN);
								}else{
									Report.ValidationPoint(testContext.getName(), "Verify 'Current Rate Plan' heading with CTN displayed on Change My Plan page", "Current Rate Plan heading with CTN displayed", "CTN not displayed with heading", true);
								}
								
							}else{
								Report.ValidationPoint(testContext.getName(), "Verify 'Current Rate Plan' heading with CTN displayed on Change My Plan page", "Current Rate Plan heading with CTN displayed", "CTN not displayed with heading", true);
							}						
											   												
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify 'Current Rate Plan' heading with CTN displayed on Change My Plan page", "Current Rate Plan heading with CTN displayed", "Current Rate Plan heading and CTN not displayed", true);
						}
						
						/*---------Verify current plan displayed---------*/
						if(UI.WaitForObject(oR_ViewOrChangeRatePlan.tblNationPlans, UI.iObjTimeOut, lDriver).equals(true)){		
							
						   Report.ValidationPoint(testContext.getName(), "Verify current plan displayed on Change My Plan page", "True", "True", true);					   												
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify current plan displayed on Change My Plan page", "current plan displayed", "current plan not displayed", true);
						}
						
						/*---------Verify heading 'Choose a New Rate Plan' displayed---------*/
						if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtChooseNewRatePlan_1511, UI.iObjTimeOut, lDriver).equals(true)){		
							
						   Report.ValidationPoint(testContext.getName(), "Verify 'Choose a New Rate Plan' heading displayed on Change My Plan page", "True", "True", true);					   												
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify 'Choose a New Rate Plan' heading displayed on Change My Plan page", "Choose a New Rate Plan heading displayed", "Choose a New Rate Plan heading not displayed", true);
						}
						
						Thread.sleep(1000);
		
						/*---------Verify heading "Nation Plan" displayed---------*/
						if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtNationPlans, UI.iObjTimeOut, lDriver).equals(true)){							
						   Report.ValidationPoint(testContext.getName(), "Verify 'Nation Plans' heading displayed on Change My Plan page", "True", "True", true);					   												
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify 'Nation Plans' heading displayed on Change My Plan page", "Nation Plans heading displayed", "Nation Plans heading not displayed", true);
						}
					}
					
					/*---------Verify Select this plan button should be displayed---------*/
					if(UI.WaitForObject(oR_ViewOrChangeRatePlan.btnSelectThisPlan_1511, UI.iObjTimeOut, lDriver).equals(true)){	
						
					   Report.ValidationPoint(testContext.getName(), "Verify 'Select this plan' CTA displayed on Change My Plan page", "True", "True", true);					   												
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Select this plan' CTA displayed on Change My Plan page", "Select this plan CTA displayed", "Select this plan CTA not displayed", true);
					}	
					
					
					
        		}else{
        			
        			Report.ValidationPoint(testContext.getName(), "Verify View or Change My Plan page displayed", "View or Change My Plan page displayed", "View or Change My Plan page not displayed", true);
        		}
    	    			
    		}   	
        	
         }
         catch(Exception e){
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);			
		}
		
        

		
	}

	/****************************************************************
	 * Function Name - VerifyMyTVServicePage() 
	 * Description-    This function use to verify My TV page for Uverse  
	 * Parameters -    None
	 * Date created -  12th Aug 2015
	 * Developed by -  Ravinder Mehra
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws Exception 
	 * @throws  
	 * @throws HeadlessException 
	 ***************************************************************/
	public static void VerifyMyTVServicePage(final ITestContext testContext) throws  Exception {
				
        WebDriver lDriver = UI.getDriver(testContext.getName()); 		
		//Boolean clicked=false;
		
        OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
        OR_TelevisionService oR_TelevisionService = PageFactory.initElements(lDriver, OR_TelevisionService.class);
                  
      
       
       /*-----Select 'Digital TV' from secondary navigation-------*/  
       try{        	
        	 if(UI.WaitForObject(oR_AccountOverview.lnkDigitalTVSecondaryNav, UI.iObjTimeOut, lDriver).equals(true)){
        		 Report.ValidationPoint(testContext.getName(), "Verify and select 'Digital TV' link from secondary Navigation", "True", "True", true);
        		 oR_AccountOverview.lnkDigitalTVSecondaryNav.click();
        		
    			/*-----Verify My TV Service page displayed------- */
        		if(UI.WaitForObject(oR_TelevisionService.txtMyTVService, UI.iObjTimeOut, lDriver).equals(true)){
        			//Actions action= new Actions(lDriver);
        			//action.moveToElement(oR_TelevisionService.txtMyTVService).build().perform();
        			//Thread.sleep(100);
        			Report.ValidationPoint(testContext.getName(), "Verify My TV Service page displayed", "True", "True", true);
					Report.OperationPoint(testContext.getName(), "Information - Header- My TV Service displayed on page");
					
					/*-----Verify TV Plan Friendly Name displayed------- */					
					if(UI.WaitForObject(oR_TelevisionService.txtMyPlanHeader, UI.iObjTimeOut, lDriver).equals(true)){
						String sTVFriendlyName=oR_TelevisionService.txtMyPlanHeader.getText().trim();
						if(!sTVFriendlyName.isEmpty()){
							Report.ValidationPoint(testContext.getName(), "Verify TV Plan friendly name displayed on My TV Service page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - TV Plan friendly name displayed as-"+sTVFriendlyName);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify TV Plan friendly name displayed on My TV Service page", "TV Plan friendly name displayed", "TV Plan friendly name not displayed", true);
						}																
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify TV Plan friendly name displayed on My TV Service page", "TV Plan friendly name displayed", "TV Plan friendly name not displayed", true);
					}	
					
					/*--------Verify Heading "Total Monthly Cost" displayed--------*/
					if(UI.WaitForObject(oR_TelevisionService.txtHrTotalMonthlyTVCost_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Total Monthly TV Cost' displayed on My TV Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Total Monthly TV Cost' displayed on My TV Service page", "Heading 'Total Monthly TV Cost' displayed", "Heading 'Total Monthly TV Cost' not displayed", true);
					}
					
										
					/*--------Verify Cost of the plan with $ amount displayed--------*/
					if(UI.WaitForObject(oR_TelevisionService.txtTotalMonthlyTVCost_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sCostOfPlan=oR_TelevisionService.txtTotalMonthlyTVCost_1511.getText().trim();
						if(sCostOfPlan.contains("$")){
							Report.ValidationPoint(testContext.getName(), "Verify cost of the plan with '$ amount displayed on My TV Service page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Monthly cost displayed as-"+sCostOfPlan);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify cost of the plan with '$ amount displayed on My TV Service page", "Cost of the plan with $ amount displayed", "Cost of the plan with $ amount not displayed", true);
						}					
						
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify cost of the plan with '$ amount displayed on My TV Service page", "Cost of the plan with $ amount displayed", "Cost of the plan with $ amount not displayed", true);
					}											

					/*--------Verify "View Cost summary" link displayed--------*/
					if(UI.WaitForObject(oR_TelevisionService.lnkViewCostSummary_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'View Cost summary' link displayed on My TV Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'View Cost summary' link displayed on My TV Service page", "View Cost summary link displayed", "View Cost summary link not displayed", true);
					}
					
					/*--------Verify "Change Plan' CTA displayed--------*/
					if(UI.WaitForObject(oR_TelevisionService.btnChangePlan, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Change Plan' CTA displayed on My TV Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Change Plan' CTA displayed on My TV Service page", "Change Plan CTA displayed", "Change Plan CTA not displayed", true);
					}
					
					/*--------Verify 'My Discount and offers' section displayed--------*/
					try
					{
						if(UI.WaitForObject(oR_TelevisionService.txtMyDiscountOffersSection_1511, UI.iObjTimeOut, lDriver).equals(true)){
							Report.ValidationPoint(testContext.getName(), "Verify 'My Discount and offers' section displayed on My TV Service page", "True", "True", true);															
						}else
						{
							Report.OperationPoint(testContext.getName(),"'My Discount and offers' section is NOT displayed on My TV Service page for this Datatype");
						}
//						else{
//							Report.ValidationPoint(testContext.getName(), "Verify 'My Discount and offers' section displayed on My TV Service page", "My Discount and offers section displayed", "My Discount and offers section not displayed", true);
//						}
						
					}catch(Exception e1)
					{
						Report.OperationPoint(testContext.getName(),"'My Discount and offers' section is NOT displayed on My TV Service page for this Datatype");
					}
					
					/*--------Verify 'My Programming' heading displayed--------*/
					if(UI.WaitForObject(oR_TelevisionService.txtMyProgramming, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'My Programming' heading displayed on My TV Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'My Programming' heading displayed on My TV Service page", "My Programming heading displayed", "My Programming heading not displayed", true);
					}
					
					/*--------Verify 'Package Name' displayed--------*/
					if(UI.WaitForObject(oR_TelevisionService.txtPackageName_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sPackageName=oR_TelevisionService.txtPackageName_1511.getText().trim();
						if(!sPackageName.isEmpty()){
							Report.ValidationPoint(testContext.getName(), "Verify Package name displayed on My TV Service page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Package name displayed as-"+sPackageName);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify Package name displayed on My TV Service page", "Package name displayed", "Package name not displayed", true);
						}						
																					
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Package name displayed on My TV Service page", "Package name displayed", "Package name not displayed", true);
					}
					
					/*--------Verify Package details displayed--------*/					
					List<WebElement> sChannels=lDriver.findElements(By.xpath(".//*[@class='channnelsList']//li"));
					if(sChannels.size()>0){
						Report.ValidationPoint(testContext.getName(), "Verify Package details displayed under My Programming section", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Package details displayed under My Programming section", "Package details displayed", "Package details not displayed", true);
					}
					
					/*--------Verify channels displayed--------*/					
					if(UI.WaitForObject(oR_TelevisionService.imgChannels_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify channels displayed under My Programming section", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify channels displayed under My Programming section", "Channels displayed", "Channels not displayed", true);
					}
					
					/*--------Verify My additional channels section displayed--------*/					
					if(UI.WaitForObject(oR_TelevisionService.txtMyAdditionalChannels_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'My additional channels' section displayed on My TV Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'My additional channels' section displayed on My TV Service page", "My additional channels section displayed", "My additional channels section not displayed", true);
					}
					
					/*--------Verify 'Watch TV now' button displayed--------*/					
					if(UI.WaitForObject(oR_TelevisionService.btnWatchTVNow_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Watch TV now' button displayed on My TV Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Watch TV now' button displayed on My TV Service page", "Watch TV now button displayed", "Watch TV now button not displayed", true);
					}
					
					/*--------Verify 'View Channel line up' link displayed--------*/					
					if(UI.WaitForObject(oR_TelevisionService.lnkViewChannel, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'View Channel line up' link displayed on My TV Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'View Channel line up' link displayed on My TV Service page", "View Channel line up link displayed", "View Channel line up link not displayed", true);
					}
					
					/*--------Verify 'Manage DVR' link displayed--------*/					
					if(UI.WaitForObject(oR_TelevisionService.lnkManageDVR_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage DVR' link displayed on My TV Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage DVR' link displayed on My TV Service page", "Manage DVR link displayed", "Manage DVR link not displayed", true);
					}
					
					/*--------Verify 'View recent video orders' link displayed--------*/					
					if(UI.WaitForObject(oR_TelevisionService.lnkViewRecentVideoOrders_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'View recent video orders' link displayed on My TV Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'View recent video orders' link displayed on My TV Service page", "View recent video orders link displayed", "View recent video orders link not displayed", true);
					}
					
					/*--------Verify "My Equipment" Heading displayed--------*/					
					if(UI.WaitForObject(oR_TelevisionService.txtMyEquipment, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'My Equipment' Heading displayed on My TV Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'My Equipment' Heading displayed on My TV Service page", "My Equipment Heading displayed", "My Equipment Heading not displayed", true);
					}
					
					/*--------Verify "Get the most from my U-verse TV" Heading displayed--------*/					
					/*if(UI.WaitForObject(oR_TelevisionService.txtGetTheMostFromMyTV_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Get the most from my U-verse TV' Heading displayed on My TV Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Get the most from my U-verse TV' Heading displayed on My TV Service page", "Get the most from my U-verse TV Heading displayed", "Get the most from my U-verse TV Heading not displayed", true);
					}*/
					
					/*--------Verify Promotion related to tv services displayed--------*/	
					List<WebElement> sPromotion=lDriver.findElements(By.xpath("//div[contains(@class,'myChannels')]//img"));
					if(sPromotion.size()>0){
						Report.ValidationPoint(testContext.getName(), "Verify Promotion related to the TV services displayed on My TV Service page", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Promotion related to the TV services displayed on My TV Service page", "Promotion related to the TV services displayed", "Promotion related to the TV services not displayed", true);
					}
					
					
					/*--------Verify "Manage my account" section displayed on page footer-------*/					
					if(UI.WaitForObject(oR_TelevisionService.txtManageMyAccount_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage my account' section displayed on My TV Service page", "True", "True", true);						
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage my account' section displayed on My TV Service page", "Manage my account section displayed", "Manage my account section not displayed", true);
					}
							
					/*--------Verify "TV Extras" section displayed on page footer-------*/					
					if(UI.WaitForObject(oR_TelevisionService.txtTVExtras_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'TV Extras' section displayed on My TV Service page", "True", "True", true);						
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'TV Extras' section displayed on My TV Service page", "TV Extras section displayed", "TV Extras section not displayed", true);
					}	
					
					/*--------Verify "Uverse TV Support " section displayed on page footer-------*/					
					if(UI.WaitForObject(oR_TelevisionService.txtUverseTVSupport, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Uverse TV Support ' section displayed on My TV Service page", "True", "True", true);						
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Uverse TV Support ' section displayed on My TV Service page", "Uverse TV Support  section displayed", "Uverse TV Support  section not displayed", true);
					}	
					
					
        		}else{
        			
        			Report.ValidationPoint(testContext.getName(), "Verify My TV Service page displayed", "My TV Service page displayed", "My TV Service page not displayed", true);
        		}
    	    			
    		}else{
    			Report.ValidationPoint(testContext.getName(), "Verify and select 'Digital TV' link from secondary Navigation", "Digital TV link displayed and selected", "Digital TV link not displayed", true);
    		}
        	
         }
         catch(Exception e){
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);			
		}
		
        

		
	}
	
	
	/****************************************************************
	 * Function Name - VerifyMyHomePhoneServicePage() 
	 * Description-    This function use to verify Uverse Home Phone Service page  
	 * Parameters -    None
	 * Date created -  14th Aug 2015
	 * Developed by -  Ravinder Mehra
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws Exception 
	 * @throws  
	 * @throws HeadlessException 
	 ***************************************************************/
	public static void VerifyMyHomePhoneServicePage(final ITestContext testContext) throws  Exception {
				
        WebDriver lDriver = UI.getDriver(testContext.getName()); 		
		//Boolean clicked=false;
		
        OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
        OR_HomePhoneService oR_HomePhoneService = PageFactory.initElements(lDriver, OR_HomePhoneService.class);                  
      
       
       /*-----Select 'Home Phone' from secondary navigation-------*/  
       try{        	
        	 if(UI.WaitForObject(oR_AccountOverview.lnkHomePhoneSecondaryNav, UI.iObjTimeOut, lDriver).equals(true)){
        		 Report.ValidationPoint(testContext.getName(), "Verify and select 'Home Phone' link from secondary Navigation", "True", "True", true);
        		 oR_AccountOverview.lnkHomePhoneSecondaryNav.click();
        		
    			/*-----Verify My Home Phone Service page displayed------- */
        		if(UI.WaitForObject(oR_HomePhoneService.elmHomePhoneService, UI.iObjTimeOut, lDriver).equals(true)){
        			//Actions action= new Actions(lDriver);
        			//action.moveToElement(oR_TelevisionService.txtMyTVService).build().perform();
        			//Thread.sleep(100);
        			Report.ValidationPoint(testContext.getName(), "Verify My Home Phone Service page displayed", "True", "True", true);
					Report.OperationPoint(testContext.getName(), "Information - Header- My Home Phone Service displayed on page");
					
					/*-----Verify Home Phone Plan Friendly Name displayed------- */					
					if(UI.WaitForObject(oR_HomePhoneService.txtPlanNameHeader_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sHomePhFriendlyName=oR_HomePhoneService.txtPlanNameHeader_1511.getText().trim();
						if(!sHomePhFriendlyName.isEmpty()){
							Report.ValidationPoint(testContext.getName(), "Verify Home Phone Plan friendly name displayed on My Home Phone Service page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Home Phone Plan friendly name displayed as-"+sHomePhFriendlyName);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify Home Phone Plan friendly name displayed on My Home Phone Service page", "Home Phone Plan friendly name displayed", "Home Phone Plan friendly name not displayed", true);
						}																
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Home Phone Plan friendly name displayed on My Home Phone Service page", "Home Phone Plan friendly name displayed", "Home Phone Plan friendly name not displayed", true);
					}					
										
					/*--------Verify Heading "Total Monthly Voice Cost" displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.txtHrTotalMonthlyVoiceCost_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Total Monthly Voice Cost' displayed on My Home Phone Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Total Monthly Voice Cost' displayed on My Home Phone Service page", "Heading 'Total Monthly Voice Cost' displayed", "Heading 'Total Monthly Voice Cost' not displayed", true);
					}
												
					/*--------Verify Cost of the plan with $ amount displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.txtTotalMonthlyVoiceCost_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sCostOfPlan=oR_HomePhoneService.txtTotalMonthlyVoiceCost_1511.getText().trim();
						if(sCostOfPlan.contains("$")){
							Report.ValidationPoint(testContext.getName(), "Verify cost of the plan with '$ amount displayed on My Home Phone Service page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Monthly cost displayed as-"+sCostOfPlan);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify cost of the plan with '$ amount displayed on My Home Phone Service page", "Cost of the plan with $ amount displayed", "Cost of the plan with $ amount not displayed", true);
						}					
						
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify cost of the plan with '$ amount displayed on My Home Phone Service page", "Cost of the plan with $ amount displayed", "Cost of the plan with $ amount not displayed", true);
					}	
										
					/*--------Verify "View Cost summary" link displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.lnkViewCostSummary_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'View Cost summary' link displayed on My Home Phone Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'View Cost summary' link displayed on My Home Phone Service page", "View Cost summary link displayed", "View Cost summary link not displayed", true);
					}					
								
					/*--------Verify "Change Plan' CTA displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.lnkChangePlan_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Change Plan' CTA displayed on My Home Phone Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Change Plan' CTA displayed on My Home Phone Service page", "Change Plan CTA displayed", "Change Plan CTA not displayed", true);
					}				
															
					/*--------Verify 'My Discount and offers' section displayed--------*/
					try
					{
						if(UI.WaitForObject(oR_HomePhoneService.txtMyDiscountOffersSection_1511, UI.iObjTimeOut, lDriver).equals(true)){
							Report.ValidationPoint(testContext.getName(), "Verify 'My Discount and offers' section displayed on My Home Phone Service page", "True", "True", true);															
						}else{
							Report.OperationPoint(testContext.getName(), "'My Discount and offers' section is NOT displayed on My Home Phone Service page for this DataType");
						}
						
					}catch(Exception e2)
					{
						Report.OperationPoint(testContext.getName(), "'My Discount and offers' section is NOT displayed on My Home Phone Service page for this DataType");
					}
									
					/*--------Verify "Plan Details" Heading displayed--------*/					
					if(UI.WaitForObject(oR_HomePhoneService.elmPlanDetails, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Plan Details' Heading displayed on My Home Phone Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Plan Details' Heading displayed on My Home Phone Service page", "Plan Details Heading displayed", "Plan Details Heading not displayed", true);
					}		
					
					/*--------Verify "View recent usage' link displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.lnkViewRecentUsage_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'View recent usage' link displayed on My Home Phone Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'View recent usage' link displayed on My Home Phone Service page", "View recent usage link displayed", "View recent usage link not displayed", true);
					}				
					
					/*--------Verify "View billed usage' link displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.lnkViewBilledUsage_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'View billed usage' link displayed on My Home Phone Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'View billed usage' link displayed on My Home Phone Service page", "View billed usage link displayed", "View billed usage link not displayed", true);
					}	
					
					/*--------Verify "Features" Heading displayed--------*/					
					if(UI.WaitForObject(oR_HomePhoneService.txtFeaturesDetails, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Features' Heading displayed on My Home Phone Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Features' Heading displayed on My Home Phone Service page", "Features Heading displayed", "Features Heading not displayed", true);
					}
					
					/*--------Verify "Tour my features' link displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.lnkTourMyFeatures_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Tour my features' link displayed under Features section", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Tour my features' link displayed under Features section", "Tour my features link displayed", "Tour my features link not displayed", true);
					}	
					
					/*--------Verify available feature displayed under Features section--------*/	
					List<WebElement> sFeatures=lDriver.findElements(By.xpath(".//*[@id='primary-content']//ul[@class='plDet']/li"));
					if(sFeatures.size()>0){
						Report.ValidationPoint(testContext.getName(), "Verify some features displayed under Features section", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify some features displayed under Features section", "Some features displayed", "There is no feature present under Features section", true);
					}
					
					/*--------Verify "View and manage all U-verse Voice Feature' link displayed--------*/
					try
					{
						if(UI.WaitForObject(oR_HomePhoneService.lnkViewManageAllVoiceFeatures_1511, UI.iObjTimeOut, lDriver).equals(true)){
							Report.ValidationPoint(testContext.getName(), "Verify 'View and manage all U-verse voice features' link displayed under Features section", "True", "True", true);															
						}else{
							Report.OperationPoint(testContext.getName(), " 'View and manage all U-verse voice features' link is NOT displayed under Features section");
						}
						
					}catch(Exception e3)
					{
						Report.OperationPoint(testContext.getName(), " 'View and manage all U-verse voice features' link is NOT displayed under Features section");
					}
					
					/*--------Verify "My Voicemail" Heading displayed--------*/					
					if(UI.WaitForObject(oR_HomePhoneService.txtHrMyVoiceMail_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'My voicemail' Heading displayed on My Home Phone Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'My voicemail' Heading displayed on My Home Phone Service page", "My voicemail Heading displayed", "My voicemail Heading not displayed", true);
					}
					
					/*--------Verify "Create or manage voicemail accounts' link displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.lnkCreateManageVoiceMailAcc_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Create or manage voicemail accounts' link displayed under My Voicemail section", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Create or manage voicemail accounts' link displayed under My Voicemail section", "Create or manage voicemail accounts link displayed", "Create or manage voicemail accounts link not displayed", true);
					}
					
					/*--------Verify "Set up voicemail' link displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.lnkSetupVoiceMail_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Set up voicemail' link displayed under My Voicemail section", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Set up voicemail' link displayed under My Voicemail section", "Set up voicemail link displayed", "Set up voicemail link not displayed", true);
					}	
					
					/*--------Verify "Change my PIN' link displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.lnkChangeMyPin_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Change my PIN' link displayed under My Voicemail section", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Change my PIN' link displayed under My Voicemail section", "Change my PIN link displayed", "Change my PIN link not displayed", true);
					}	
					
					/*--------Verify "Manage voicemail settings' link displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.lnkManageVoicemailSettings_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage voicemail settings' link displayed under My Voicemail section", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage voicemail settings' link displayed under My Voicemail section", "Manage voicemail settings link displayed", "Manage voicemail settings link not displayed", true);
					}
					
					/*--------Verify all associated emails displayed under Voice mail section--------*/	
					List<WebElement> sEmails=lDriver.findElements(By.xpath("//table[@class='table']/tbody//td[@class='eAddress']"));
					if(sEmails.size()>0){
						Report.ValidationPoint(testContext.getName(), "Verify emails associated with account displayed under My Voicemail section", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify emails associated with account displayed under My Voicemail section", "Emails associated with account displayed", "Emails associated with the account not displayed", true);
					}
												
					/*--------Verify "Get the most from my U-verse Voice" Heading displayed--------*/					
					if(UI.WaitForObject(oR_HomePhoneService.txtHrGetMostFromMyUverseVoice_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Get the most from my U-verse Voice' Heading displayed on My Home Phone Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Get the most from my U-verse Voice' Heading displayed on My Home Phone Service page", "Get the most from my U-verse Voice Heading displayed", "Get the most from my U-verse Voice Heading not displayed", true);
					}
					
					/*--------Verify Promotion related to tv services displayed--------*/	
					List<WebElement> sPromotion=lDriver.findElements(By.xpath("//div[contains(@class,'myChannels')]//img"));
					if(sPromotion.size()>0){
						Report.ValidationPoint(testContext.getName(), "Verify Promotion related to the Home Phone services displayed", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Promotion related to the Home Phone services displayed", "Promotion related to the Home Phone services displayed", "Promotion related to the Home Phone services not displayed", true);
					}
										
					/*--------Verify "Manage my account" section displayed on page footer-------*/					
					if(UI.WaitForObject(oR_HomePhoneService.txtManageMyAccount_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage My Account' section displayed on My Home Phone Service page", "True", "True", true);						
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage My Account' section displayed on My Home Phone Service page", "Manage My Account section displayed", "Manage My Account section not displayed", true);
					}
							
					/*--------Verify "Home Phone Extras" section displayed on page footer-------*/					
					if(UI.WaitForObject(oR_HomePhoneService.txtHomePhoneExtras_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Home Phone Extras' section displayed on My Home Phone Service page", "True", "True", true);						
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Home Phone Extras' section displayed on My Home Phone Service page", "Home Phone Extras section displayed", "Home Phone Extras section not displayed", true);
					}	
					
					/*--------Verify "Home Phone Support " section displayed on page footer-------*/					
					if(UI.WaitForObject(oR_HomePhoneService.txtHomePhoneSupport_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Home Phone Support' section displayed on My Home Phone Service page", "True", "True", true);						
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Home Phone Support' section displayed on My Home Phone Service page", "Home Phone Support  section displayed", "Home Phone Support  section not displayed", true);
					}	
					
					
        		}else{
        			
        			Report.ValidationPoint(testContext.getName(), "Verify My TV Service page displayed", "My TV Service page displayed", "My TV Service page not displayed", true);
        		}
    	    			
    		}else{
    			Report.ValidationPoint(testContext.getName(), "Verify and select 'Home Phone' link from secondary Navigation", "Home Phone link displayed and selected", "Home Phone link not displayed", true);
    		}
        	
         }
         catch(Exception e){
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);			
		}
		
        

		
	}
	
	
	/****************************************************************
	 * Function Name - VerifyMyInternetServicePage() 
	 * Description-    This function use to verify Uverse Internet Service page  
	 * Parameters -    None
	 * Date created -  17th Aug 2015
	 * Developed by -  Ravinder Mehra
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws Exception 
	 * @throws  
	 * @throws HeadlessException 
	 ***************************************************************/
	public static void VerifyMyInternetServicePage(final ITestContext testContext) throws  Exception {
				
        WebDriver lDriver = UI.getDriver(testContext.getName()); 		
		//Boolean clicked=false;
		
        OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
        OR_InternetService oR_InternetService = PageFactory.initElements(lDriver, OR_InternetService.class);                  
      
       
       /*-----Select 'Internet' from secondary navigation-------*/  
       try{        	
        	 if(UI.WaitForObject(oR_AccountOverview.lnkInternetSecondaryNav, UI.iObjTimeOut, lDriver).equals(true)){
        		 Report.ValidationPoint(testContext.getName(), "Verify and select 'Internet' link from secondary Navigation", "True", "True", true);
        		 oR_AccountOverview.lnkInternetSecondaryNav.click();
        		
    			/*-----Verify My Internet Service page displayed------- */
        		if(UI.WaitForObject(oR_InternetService.txtMyInternetService, UI.iObjTimeOut, lDriver).equals(true)){
        			//Actions action= new Actions(lDriver);
        			//action.moveToElement(oR_TelevisionService.txtMyTVService).build().perform();
        			//Thread.sleep(100);
        			Report.ValidationPoint(testContext.getName(), "Verify My Internet Service page displayed", "True", "True", true);
					Report.OperationPoint(testContext.getName(), "Information - Header- My Internet Service displayed on page");
					
					/*-----Verify Internet Plan Friendly Name displayed------- */					
					if(UI.WaitForObject(oR_InternetService.txtPlanNameHeader_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sInternetFriendlyName=oR_InternetService.txtPlanNameHeader_1511.getText().trim();
						if(!sInternetFriendlyName.isEmpty()){
							Report.ValidationPoint(testContext.getName(), "Verify Internet Plan friendly name displayed on My Internet Service page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Internet Plan friendly name displayed as-"+sInternetFriendlyName);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify Internet Plan friendly name displayed on My Internet Service page", "Internet Plan friendly name displayed", "Internet Plan friendly name not displayed", true);
						}																
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Internet Plan friendly name displayed on My Internet Service page", "Internet Plan friendly name displayed", "Internet Plan friendly name not displayed", true);
					}					
										
					/*--------Verify Heading "Total Monthly Internet Cost" displayed--------*/
					if(UI.WaitForObject(oR_InternetService.txtHrTotalMonthlyInternetCost_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Total Monthly Internet Cost' displayed on My Internet Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Total Monthly Internet Cost' displayed on My Internet Service page", "Heading 'Total Monthly Internet Cost' displayed", "Heading 'Total Monthly Internet Cost' not displayed", true);
					}
												
					/*--------Verify Cost of the plan with $ amount displayed--------*/
					if(UI.WaitForObject(oR_InternetService.txtTotalMonthlyInternetCost_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sCostOfPlan=oR_InternetService.txtTotalMonthlyInternetCost_1511.getText().trim();
						if(sCostOfPlan.contains("*")){
							Report.ValidationPoint(testContext.getName(), "Verify cost of the plan with '$ amount displayed on My Internet Service page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Monthly cost displayed as-"+sCostOfPlan);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify cost of the plan with '$ amount displayed on My Internet Service page", "Cost of the plan with $ amount displayed", "Cost of the plan with $ amount not displayed", true);
						}					
						
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify cost of the plan with '$ amount displayed on My Internet Service page", "Cost of the plan with $ amount displayed", "Cost of the plan with $ amount not displayed", true);
					}	
										
					/*--------Verify "View Cost summary" link displayed--------*/
					if(UI.WaitForObject(oR_InternetService.lnkViewCostSummary_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'View Cost summary' link displayed on My Internet Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'View Cost summary' link displayed on My Internet Service page", "View Cost summary link displayed", "View Cost summary link not displayed", true);
					}					
								
					/*--------Verify "Change Plan' CTA displayed--------*/
					if(UI.WaitForObject(oR_InternetService.btnChangePlanInInternetServices, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Change Plan' CTA displayed on My Internet Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Change Plan' CTA displayed on My Internet Service page", "Change Plan CTA displayed", "Change Plan CTA not displayed", true);
					}				
															
					/*--------Verify 'My Discount and offers' section displayed--------*/
					try
					{
						if(UI.WaitForObject(oR_InternetService.txtMyDiscountOffersSection_1511, UI.iObjTimeOut, lDriver).equals(true)){
							Report.ValidationPoint(testContext.getName(), "Verify 'My Discount and offers' section displayed on My Internet Service page", "True", "True", true);															
						}else{
							Report.OperationPoint(testContext.getName(), "'My Discount and offers' section is NOT displayed on My Internet Service page");
						}	
					}catch(Exception e4)
					{
						Report.OperationPoint(testContext.getName(), "'My Discount and offers' section is NOT displayed on My Internet Service page");
					}
									
					/*--------Verify "Plan Details" Heading displayed--------*/					
					if(UI.WaitForObject(oR_InternetService.txtHrPlanDetails_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Plan Details' Heading displayed on My Internet Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Plan Details' Heading displayed on My Internet Service page", "Plan Details Heading displayed", "Plan Details Heading not displayed", true);
					}	
					
					/*--------Verify 'Package Name' displayed--------*/
					if(UI.WaitForObject(oR_InternetService.txtPackageName_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sPackageName=oR_InternetService.txtPackageName_1511.getText().trim();
						if(!sPackageName.isEmpty()){
							Report.ValidationPoint(testContext.getName(), "Verify Package name displayed on My Internet Service page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Package name displayed as-"+sPackageName);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify Package name displayed on My Internet Service page", "Package name displayed", "Package name not displayed", true);
						}						
																					
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Package name displayed on My Internet Service page", "Package name displayed", "Package name not displayed", true);
					}
					
					/*--------Verify Package details displayed--------*/					
					List<WebElement> sOptions=lDriver.findElements(By.xpath("//ul[@class='plDet']/li"));
					if(sOptions.size()>0){
						Report.ValidationPoint(testContext.getName(), "Verify Package details displayed under Plan Details section", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Package details displayed under Plan Details section", "Package details displayed", "Package details not displayed", true);
					}			
						
					/*--------Verify "My Equipment" Heading displayed--------*/					
					if(UI.WaitForObject(oR_InternetService.txtHrMyEquipment_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'My Equipment' Heading displayed on My Internet Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'My Equipment' Heading displayed on My Internet Service page", "My Equipment Heading displayed", "My Equipment Heading not displayed", true);
					}	
					
					/*--------Verify all equipment associated with the Internet displayed--------*/					
					List<WebElement> sEquipment=lDriver.findElements(By.xpath("//*[contains(@class,'myChannels')]//*[contains(@class,'Pad')]/p"));
					if(sEquipment.size()>0){
						Report.ValidationPoint(testContext.getName(), "Verify all equipment associated with the Internet displayed under My Equipment section", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify all equipment associated with the Internet displayed under My Equipment section", "Equipment associated with the Internet displayed", "Equipment associated with the Internet not displayed", true);
					}	
					
					/*--------Verify "Help & support" Heading displayed--------*/					
					if(UI.WaitForObject(oR_InternetService.txtHrHelpnSupport_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Help & support' Heading displayed under My Equipment section", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Help & support' Heading displayed under My Equipment section", "Help & support Heading displayed", "Help & support Heading not displayed", true);
					}	
					
					/*--------Verify Help & support links displayed--------*/					
					List<WebElement> sLinks=lDriver.findElements(By.xpath("//*[contains(@class,'devTxt')]//a[contains(@href,'Equipment&actionType')]"));
					if(sLinks.size()>0){
						Report.ValidationPoint(testContext.getName(), "Verify Help & support links displayed under My Equipment section", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Help & support links displayed under My Equipment section", "Help & support links displayed", "Help & support links not displayed", true);
					}						
					
					/*--------Verify "My Email addresses" Heading displayed--------*/					
					if(UI.WaitForObject(oR_InternetService.txtMyEmailAddresses, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'My Email addresses' Heading displayed on My Internet Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'My Email addresses' Heading displayed on My Internet Service page", "My Email addresses Heading displayed", "My Email addresses Heading not displayed", true);
					}
									
					/*--------Verify "Create or manage email accounts' link displayed--------*/
					if(UI.WaitForObject(oR_InternetService.lnkCreateOrManageEmailAccount, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Create or manage email accounts' link displayed under My email section", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Create or manage email accounts' link displayed under My email section", "Create or manage email accounts link displayed", "Create or manage email accounts link not displayed", true);
					}					
												
					/*--------Verify "Change Password' link displayed--------*/
					if(UI.WaitForObject(oR_InternetService.lnkChangePassword, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Change Password' link displayed under My email section", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Change Password' link displayed under My email section", "Change Password link displayed", "Change Password link not displayed", true);
					}	
															
					/*--------Verify All the Emails associated with account  displayed under My Email address section--------*/	
					List<WebElement> sEmails=lDriver.findElements(By.xpath("//table[@class='table']//tbody//td[@class='eAddress']"));
					if(sEmails.size()>0){
						Report.ValidationPoint(testContext.getName(), "Verify emails associated with account displayed under My Email addresses section", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify emails associated with account displayed under My Email addresses section", "Emails associated with account displayed", "Emails associated with the account not displayed", true);
					}
												
					/*--------Verify "Get the most from my U-verse Internet" Heading displayed--------*/					
					if(UI.WaitForObject(oR_InternetService.txtHrGetMostFromMyUverseInternet_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Get the most from my U-verse Internet' Heading displayed on My Internet Service page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Get the most from my U-verse Internet' Heading displayed on My Internet Service page", "Get the most from my U-verse Internet Heading displayed", "Get the most from my U-verse Internet Heading not displayed", true);
					}
					
					/*--------Verify Promotion related to Internet services displayed--------*/	
					List<WebElement> sPromotion=lDriver.findElements(By.xpath("//div[contains(@class,'myChannels')]//img"));
					if(sPromotion.size()>0){
						Report.ValidationPoint(testContext.getName(), "Verify Promotion related to the Internet services displayed", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Promotion related to the Internet services displayed", "Promotion related to the Internet services displayed", "Promotion related to the Internet services not displayed", true);
					}
										
					/*--------Verify "Manage my account" section displayed on page footer-------*/					
					if(UI.WaitForObject(oR_InternetService.txtManageAccountInFoterSection, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage My Account' section displayed on My Internet Service page", "True", "True", true);						
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Manage My Account' section displayed on My Internet Service page", "Manage My Account section displayed", "Manage My Account section not displayed", true);
					}
							
					/*--------Verify "Internet Extras" section displayed on page footer-------*/					
					if(UI.WaitForObject(oR_InternetService.txtInternetExtrasInFooterSection, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Internet Extras' section displayed on My Internet Service page", "True", "True", true);						
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Internet Extras' section displayed on My Internet Service page", "Internet Extras section displayed", "Internet Extras section not displayed", true);
					}	
					
					/*--------Verify "Uverse Internet Support " section displayed on page footer-------*/					
					if(UI.WaitForObject(oR_InternetService.txtUverseInternetSupportInFooterSection, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Uverse Internet Support' section displayed on My Internet Service page", "True", "True", true);						
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Uverse Internet Support' section displayed on My Internet Service page", "Uverse Internet Support section displayed", "Uverse Internet Support section not displayed", true);
					}	
					
					
        		}else{
        			
        			Report.ValidationPoint(testContext.getName(), "Verify My Internet Service page displayed", "My Internet Service page displayed", "My Internet Service page not displayed", true);
        		}
    	    			
    		}else{
    			Report.ValidationPoint(testContext.getName(), "Verify and select 'Internet' link from secondary Navigation", "Internet link displayed and selected", "Internet link not displayed", true);
    		}
        	
         }
         catch(Exception e){
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);			
		}
		
        

		
	}
	
	/****************************************************************
	 * Function Name - VerifyHomePhonePageForWireline() 
	 * Description-    This function use to verify Home Phone Service page for Wireline customer
	 * Parameters -    None
	 * Date created -  20th Aug 2015
	 * Developed by -  Ravinder Mehra
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws Exception 
	 * @throws  
	 * @throws HeadlessException 
	 ***************************************************************/
	public static void VerifyHomePhonePageForWireline(final ITestContext testContext) throws  Exception {
				
        WebDriver lDriver = UI.getDriver(testContext.getName()); 		
		//Boolean clicked=false;
		
        OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
        OR_HomePhoneService oR_HomePhoneService = PageFactory.initElements(lDriver, OR_HomePhoneService.class);                  
      
       
       /*-----Select 'Home Phone' from secondary navigation-------*/  
       try{        	
        	 if(UI.WaitForObject(oR_AccountOverview.lnkHomePhoneSecondaryNav, UI.iObjTimeOut, lDriver).equals(true)){
        		 Report.ValidationPoint(testContext.getName(), "Verify and select 'Home Phone' link from secondary Navigation", "True", "True", true);
        		 oR_AccountOverview.lnkHomePhoneSecondaryNav.click();
        		 
        		 /*-----Verify My Home Phone Service page displayed------- */
        		 if(UI.WaitForObject(oR_HomePhoneService.elmHomePhoneService, UI.iObjTimeOut, lDriver).equals(true)){
        			 Report.ValidationPoint(testContext.getName(), "Verify My Home Phone Service Page displayed", "True", "True", true);
        			 /*---Verify abd select Locak Service details link------*/
        			 List<WebElement> links=lDriver.findElements(By.xpath(".//*[@id='primary-content']//a[text()='Local Service Details']"));        			 
        			 if(links.size()>0){
        				 Report.OperationPoint(testContext.getName(), "Information - Selected link- 'Local Service Details'");
        				 links.get(0).click();
        			 }       					 
        	     }        		  		 
        		 
        		
    			 /*-----Verify Local Service Details page displayed------- */
        		 if(UI.WaitForObject(oR_HomePhoneService.elmLocalServiceDetails, UI.iObjTimeOut, lDriver).equals(true)){
        			//Actions action= new Actions(lDriver);
        			//action.moveToElement(oR_TelevisionService.txtMyTVService).build().perform();
        			//Thread.sleep(100);
        			Report.ValidationPoint(testContext.getName(), "Verify Local Service Details page displayed", "True", "True", true);
					Report.OperationPoint(testContext.getName(), "Information - Header- Local Service Details displayed on page");
					
					/*-----Verify Account Name displayed------- */	
					if(UI.WaitForObject(oR_HomePhoneService.txtAccountName_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sAccName=oR_HomePhoneService.txtAccountName_1511.getText().trim();
						if(!sAccName.isEmpty()){
							Report.ValidationPoint(testContext.getName(), "Verify Account name displayed on Local Service Details page", "True", "True", true);	
							Report.OperationPoint(testContext.getName(), "Information - Account name displayed as- "+sAccName);
						}else {
							Report.ValidationPoint(testContext.getName(), "Verify Account name displayed on Local Service Details page", "Account name displayed", "Account name not displayed", true);
						}																				
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Account name displayed on Local Service Details page", "Account name displayed", "Account name not displayed", true);
					}
					
					/*-----Verify Account Number displayed------- */	
					if(UI.WaitForObject(oR_HomePhoneService.txtAccountNumber_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sAccNum=oR_HomePhoneService.txtAccountNumber_1511.getText().trim();
						if(!sAccNum.isEmpty()){
							Report.ValidationPoint(testContext.getName(), "Verify Account Number displayed on Local Service Details page", "True", "True", true);	
							Report.OperationPoint(testContext.getName(), "Information - Account Number displayed as- "+sAccNum);
						}else {
							Report.ValidationPoint(testContext.getName(), "Verify Account Number displayed on Local Service Details page", "Account Number displayed", "Account Number not displayed", true);
						}																				
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Account Number displayed on Local Service Details page", "Account Number displayed", "Account Number not displayed", true);
					}
					
					/*-----Verify Account Selection Module displayed------- */	
					////div[@class='section-title']//li[@class='account-number']
					//No drop-down will be displayed If, only 1 Account is available.
					try
					{
						if(UI.WaitForObject(oR_HomePhoneService.lnkUSM_1511, UI.iObjTimeOut, lDriver).equals(true)){						
							Report.ValidationPoint(testContext.getName(), "Verify Account Selection Module displayed on Local Service Details page", "True", "True", true);	
																																
						}else if(UI.WaitForObject(oR_HomePhoneService.lnkUSM_for_SingleAccount, UI.iObjTimeOut, lDriver).equals(true))
						{
							Report.ValidationPoint(testContext.getName(), "Verify Account Selection Module displayed on Local Service Details page. But Selection module is absent since only 1 account is avilable", "True", "True", true);	

						}else
						{
							Report.ValidationPoint(testContext.getName(), "Verify Account Selection Module on Local Service Details page", "Account Selection Module displayed", "Either Account Selection Module is displayed nor Single account section is displayed", true);
						}
						
					}catch(Exception ee)
					{
						if(UI.WaitForObject(oR_HomePhoneService.lnkUSM_for_SingleAccount, UI.iObjTimeOut, lDriver).equals(true))
						{
							Report.ValidationPoint(testContext.getName(), "Verify Account Selection Module displayed on Local Service Details page. But Selection module is absent since only 1 account is avilable", "True", "True", true);	

						}else
						{
							Report.ValidationPoint(testContext.getName(), "Verify Account Selection Module on Local Service Details page", "Account Selection Module displayed", "Either Account Selection Module is displayed nor Single account section is displayed", true);

						}
					}
					
					/*-----Verify Home Phone Plan Friendly Name displayed------- */	
					if(UI.WaitForObject(oR_HomePhoneService.txtPlanNameWireline_1511, UI.iObjTimeOut, lDriver).equals(true)){
						String sHomePhFriendlyName=oR_HomePhoneService.txtPlanNameWireline_1511.getText().trim();
						if(!sHomePhFriendlyName.isEmpty()){
							Report.ValidationPoint(testContext.getName(), "Verify Home Phone Plan friendly name displayed on Local Service Details page", "True", "True", true);
							Report.OperationPoint(testContext.getName(), "Information - Home Phone Plan friendly name displayed as-"+sHomePhFriendlyName);
						}else{
							Report.ValidationPoint(testContext.getName(), "Verify Home Phone Plan friendly name displayed on Local Service Details page", "Home Phone Plan friendly name displayed", "Home Phone Plan friendly name not displayed", true);
						}																
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Home Phone Plan friendly name displayed on Local Service Details page", "Home Phone Plan friendly name displayed", "Home Phone Plan friendly name not displayed", true);
					}					
										
					/*--------Verify Change Plan link displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.lnkChangePlan, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Change Plan' link displayed on Local Service Details page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Change Plan' link displayed on Local Service Details page", "Change Plan link displayed", "Change Plan link not displayed", true);
					}												
															
					/*--------Verify Heading "Home Phone Details" displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.txtHomePhoneDetails_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Home Phone Details' displayed on Local Service Details page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Home Phone Details' displayed on Local Service Details page", "Heading 'Home Phone Details' displayed", "Heading 'Home Phone Details' not displayed", true);
					}					
					
					/*--------Verify Heading "Included Features" displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.txtIncludeFeature_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Included Features' displayed on Local Service Details page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Included Features' displayed on Local Service Details page", "Heading 'Included Features' displayed", "Heading 'Included Features' not displayed", true);
					}	
					
					/*--------Verify All "Included Features" displayed--------*/
					List<WebElement> sAllFeatures=lDriver.findElements(By.xpath("//ul[contains(@class,'blueListDisc')]//a"));
					if(sAllFeatures.size()>0){
						Report.ValidationPoint(testContext.getName(), "Verify All Included Features displayed on Local Service Details page", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify All Included Features displayed on Local Service Details page", "All Included Features displayed", "Included Features not displayed", true);
					}
					
					/*--------Verify Manage Services Heading displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.txtManageServices_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Manage Services' displayed on Local Service Details page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Manage Services' displayed on Local Service Details page", "Heading 'Manage Services' displayed", "Heading 'Manage Services' not displayed", true);
					}	
					
					/*--------Verify All services for the phone displayed--------*/
					List<WebElement> sAllServices=lDriver.findElements(By.xpath("//dl[@class='profileLinks']//dd"));
					if(sAllServices.size()>0){
						Report.ValidationPoint(testContext.getName(), "Verify All the services for the Phone displayed on Local Service Details page", "True", "True", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify All the services for the Phone displayed on Local Service Details page", "All the services for the Phone displayed", "Services for the Phone not displayed", true);
					}
					
					/*--------Verify Additional Resources Heading displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.txtAdditionalResources_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Additional Resources' displayed on Local Service Details page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify Heading 'Additional Resources' displayed on Local Service Details page", "Heading 'Additional Resources' displayed", "Heading 'Additional Resources' not displayed", true);
					}	
					
					/*--------Verify Call Forwarding link displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.lnkCallForwarding_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Call Forwarding' link displayed on Local Service Details page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Call Forwarding' link displayed on Local Service Details page", "Call Forwarding link displayed", "Call Forwarding link not displayed", true);
					}	
					
					/*--------Verify 'Call Block/Call Screening' link displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.lnkCallBlockScreening_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Call Block/Call Screening' link displayed on Local Service Details page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Call Block/Call Screening' link displayed on Local Service Details page", "Call Block/Call Screening link displayed", "Call Block/Call Screening link not displayed", true);
					}	
					
					/*--------Verify 'Stopping annoying or harassing calls' link displayed--------*/
					if(UI.WaitForObject(oR_HomePhoneService.lnkStopCalls_1511, UI.iObjTimeOut, lDriver).equals(true)){
						Report.ValidationPoint(testContext.getName(), "Verify 'Stopping annoying or harassing calls' link displayed on Local Service Details page", "True", "True", true);															
					}else{
						Report.ValidationPoint(testContext.getName(), "Verify 'Stopping annoying or harassing calls' link displayed on Local Service Details page", "Stopping annoying or harassing calls link displayed", "Stopping annoying or harassing calls link not displayed", true);
					}	


					
										
										
					
        		}else{
        			
        			Report.ValidationPoint(testContext.getName(), "Verify My TV Service page displayed", "My TV Service page displayed", "My TV Service page not displayed", true);
        		}
    	    			
    		}else{
    			Report.ValidationPoint(testContext.getName(), "Verify and select 'Home Phone' link from secondary Navigation", "Home Phone link displayed and selected", "Home Phone link not displayed", true);
    		}
        	
         }
         catch(Exception e){
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);			
		}
		
        

		
	}
	

	


}


