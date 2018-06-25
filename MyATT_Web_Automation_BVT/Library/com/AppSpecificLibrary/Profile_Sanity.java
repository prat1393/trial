package com.AppSpecificLibrary;


//import org.openqa.selenium.Alert;
//import org.openqa.selenium.support.PageFactory;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;

import com.OR.MyATT.OR_ATT;
import com.OR.MyATT.OR_ATTAccessIDVerifyAccInfo;
import com.OR.MyATT.OR_ATT_AccessID_Profile;
import com.OR.MyATT.OR_AccountInformation;
import com.OR.MyATT.OR_AccountOverview;
import com.OR.MyATT.OR_AccountRegistration;
import com.OR.MyATT.OR_EditATTAccessIDInformation;
import com.OR.MyATT.OR_LinkAnAccount;
import com.OR.MyATT.OR_Login;
import com.OR.MyATT.OR_MyAccountAccess;
import com.OR.MyATT.OR_Profile;
import com.OR.MyATT.OR_RequestAccessConfirmation;
import com.SupportingFiles.IO;
//import com.OR.BillAndPayments.OR_BillAndPayments;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;
import com.SupportingFiles.UI;

public class Profile_Sanity extends LaunchAndLogout {
	private static final WebDriver IDriver = null;
	static Hashtable<String, String> sTestParams = new Hashtable<String, String>();

	/**************************************************************
	 * Function Name - VerifyProfilePage 
	 * Description-    
	 * Parameters -    None
	 * Date created -  08/10/2015
	 * Developed by -  Deepak
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void VerifyProfilePageDetails(final ITestContext testContext)
			throws Exception {	
		
		try {
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			/*--- Need to retrieve DataType for Overview page verification */
			 String sLoginType= IO.GetParamVal(sTestParams, "DataType");
			
			
			//validating profile link on secondary navigation
			if (UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav, UI.iObjTimeOut)) {
				Report.TestPoint(testContext.getName(),
						"Validate and click on Profile link on secondary navigation",
						"True", "True", true);
				oR_AccountOverview.lnkProfileSecondaryNav.click();
				
				//Clicking on Update My Profile link
				/*boolean bClickonBnPLink = UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecNav, oR_AccountOverview.lnkUpdateMyProfileTertNav);
				Thread.sleep(2000);
				if (bClickonBnPLink){		
					Report.ValidationPoint(testContext.getName(),"Clicking on Update My Profile link from Tertiary navigation", "True", "True",true);
				}*/
				

				if (sLoginType.equals("CB-Uverse") ||sLoginType.equals("Legacy Wireline")) {
					//validating My Profile title on Profile page
					if (UI.WaitForObject(oR_Profile.txtMyProfileTitle, UI.iObjTimeOut)) {
						Report.ValidationPoint(testContext.getName(),
								"Verify MyProfile title", "True", "True",
								true);
					}
				} else if(sLoginType.equals("Wireless")) {
						
						   if (UI.WaitForObject(oR_Profile.txtAccountInformationDefault, UI.iObjTimeOut)) {
							Report.ValidationPoint(testContext.getName(),
									"Verify Account Information title for Wireless Acount", "True", "True",
									true);
						   }
				} else {
						Report.ValidationPoint(testContext.getName(),
								"Validate MyProfile title", "True", "False",
								true);
					}
				}
			else {Report.TestPoint(testContext.getName(),"Validate Profile link on secondary navigation","True", "False", true);
			}
			
					
			
			/*---Switch to desired data type as per data sheet ---*/
			switch (sLoginType) {
			
				case "Wireless":
					
					//Verify Account information, User information and Marketing preference is displayed
					//and verify by default Account information is selected 
					Boolean accInfo_wls=UI.WaitForObject(oR_Profile.txtAccountInformationDefault, 15);
					Boolean userInfo_wls=UI.WaitForObject(oR_Profile.txtUserInformation_1511, 10);
					Boolean marketingPref_wls=UI.WaitForObject(oR_Profile.txtMarketingPreference, 10);
					if (accInfo_wls) {
						Report.ValidationPoint(testContext.getName(), "Verify Account information tab is displayed and selected by default", "Account information tab is displayed and selected by default", "Account information tab is displayed and selected by default", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Account information tab is displayed and selected by default", "Account information tab is displayed and selected by default", "Account information tab is not selected by default", true);
					}
					if (userInfo_wls) {
						Report.ValidationPoint(testContext.getName(), "Verify User information tab is displayed", "User information tab is displayed", "User information tab is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify User information tab is displayed", "User information tab is displayed", "User information tab is not displayed", true);
					}
					if (marketingPref_wls) {
						Report.ValidationPoint(testContext.getName(), "Verify User Marketing preference is displayed", "Marketing preference is displayed", "Marketing preference is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify User Marketing preference is displayed", "Marketing preference is displayed", "Marketing preference is not displayed", true);
					}
					
					//This Step is specific to only Slid Account type, we can exclude this step from other Account types - Yuvashree
					//commented by Clint on 18-Dec-2015
					/*boolean bAccessATTIDAndProfile_cbu= UI.WaitForObject(oR_Profile.lnkAttAccessIdProfile, 40, lDriver);
					Report.ValidationPoint(testContext.getName(), "Verify that At&t Access ID Profile link is displayed", "true", String.valueOf(bAccessATTIDAndProfile_cbu), true);*/
					
					/*---------------- Group 1:- Verify Account Information tab elements ---------------*/
					
					//Verifying Account Names section is displayed with Edit Link
					Boolean bAccInfoHdr_wls=UI.WaitForObject(oR_Profile.txtAccountNames, 10, lDriver);
					if (bAccInfoHdr_wls) {
						Report.ValidationPoint(testContext.getName(), "Verify Account Names header is displayed", "Account Names header is displayed", "Account Names header is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Account Names header is displayed", "Account Names header is displayed", "Account Names header is not displayed", true);
					}
				
					Boolean bAccInfoEditLink_wls=UI.WaitForObject(oR_Profile.lnkEditAccNames, 10, lDriver);
					if (bAccInfoEditLink_wls) {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Account Names link is displayed", "Edit Account Names link is displayed", "Edit Account Names link is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Account Names link is displayed", "Edit Account Names link is displayed", "Edit Account Names link is not displayed", true);
					}
					
					//Verifying Billing Contact Information section is displayed with Edit Link
					Boolean bBillConInfoHdr_wls=UI.WaitForObject(oR_Profile.txtBillingContactInfo, 10, lDriver);
					if (bBillConInfoHdr_wls) {
						Report.ValidationPoint(testContext.getName(), "Verify Billing Contact information section is displayed", "Billing Contact information section is displayed", "Billing Contact information section is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Billing Contact information section is displayed", "Billing Contact information section is displayed", "Billing Contact information section is not displayed", true);
					}			
					
					//Verifying Billing Notifications section is displayed with Edit Link
					Boolean bBillNotifications_wls=UI.WaitForObject(oR_Profile.txtBillingNotifications, 10, lDriver);
					if (bBillNotifications_wls) {
						Report.ValidationPoint(testContext.getName(), "Verify Billing Notifications section is displayed", "Billing Notifications section is displayed", "Billing Notifications section is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Billing Notifications section is displayed", "Billing Notifications section is displayed", "Billing Notifications section is not displayed", true);
					}
				
					Boolean bBillNotifyEditLink_wls=UI.WaitForObject(oR_Profile.lnkEditBillingNotifications_wls, 10, lDriver);
					if (bBillNotifyEditLink_wls) {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Billing Notifications link is displayed", "Edit Billing Notifications link is displayed", "Edit Billing Notifications link is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Billing Notifications link is displayed", "Edit Billing Notifications link is displayed", "Edit Billing Notifications link is not displayed", true);
					}	
					
					//validating Billing Threshold & Bill Ready notifications in account information tab
					boolean bBillThreshold_wls= UI.WaitForObject(oR_Profile.txtBillThresholdNotifications_wls, 5, lDriver);
					boolean bBillReady_wls= UI.WaitForObject(oR_Profile.txtBillReadyNotificationsByTextMsg, 5, lDriver);
					boolean bBillAlert_wls= UI.WaitForObject(oR_Profile.txtBillAlertNotifications,5, lDriver);
					if(bBillThreshold_wls && bBillAlert_wls && bBillReady_wls)
					{
						Report.TestPoint(testContext.getName(),"Verify Bill Threshold, Bill Ready & Bill Pay Reminder notifications are present","true", String.valueOf(bBillReady_wls), true);
					}
					
										
					//Verifying Payment options in account information tab
					Boolean bPaymentOptions_wls=UI.WaitForObject(oR_Profile.txtPaymentOptions, 10, lDriver);
					if (bPaymentOptions_wls) {
						Report.ValidationPoint(testContext.getName(), "Verify Payment Options text is displayed", "Payment Options text is displayed", "Payment Options text is displayed", true);
					}
					
					
					//Verifying Bill Options in account information tab
					Boolean bBillOptions_wls=UI.WaitForObject(oR_Profile.txtBillOptions, 10, lDriver);
					if (bBillOptions_wls) {
						Report.ValidationPoint(testContext.getName(), "Verify Bill Options text is displayed", "Bill Options text is displayed", "Bill Options text is displayed", true);
					}
					
					//Verifying Authorized Users in account information tab
					Boolean bAuthorizedUsers_wls=UI.WaitForObject(oR_Profile.txtAuthorizedUsers, 10, lDriver);
					if (bAuthorizedUsers_wls) {
						Report.ValidationPoint(testContext.getName(), "Verify Authorized Users text is displayed", "Authorized Users text is displayed", "Authorized Users text is displayed", true);
					}
					
					break;
				
				case "CB-Uverse":	
					
					
					//Verify Account information, User information and Marketing preference is displayed
					//and verify by default Account information is selected 
					Boolean accInfo_cbu=UI.WaitForObject(oR_Profile.txtAccountInformation, 15);
					Boolean userInfo_cbu=UI.WaitForObject(oR_Profile.txtUserInformation_1511, 10);
					Boolean marketingPref_cbu=UI.WaitForObject(oR_Profile.txtMarketingPreference, 10);
					if (accInfo_cbu) {
						Report.ValidationPoint(testContext.getName(), "Verify Account information tab is displayed and selected by default", "Account information tab is displayed and selected by default", "Account information tab is displayed and selected by default", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Account information tab is displayed and selected by default", "Account information tab is displayed and selected by default", "Account information tab is not selected by default", true);
					}
					if (userInfo_cbu) {
						Report.ValidationPoint(testContext.getName(), "Verify User information tab is displayed", "User information tab is displayed", "User information tab is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify User information tab is displayed", "User information tab is displayed", "User information tab is not displayed", true);
					}
					if (marketingPref_cbu) {
						Report.ValidationPoint(testContext.getName(), "Verify User Marketing preference is displayed", "Marketing preference is displayed", "Marketing preference is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify User Marketing preference is displayed", "Marketing preference is displayed", "Marketing preference is not displayed", true);
					}
					
					//This Step is specific to only Slid Account type, we can exclude this step from other Account types - Yuvashree
					//commented by Clint on 18-Dec-2015
					//boolean bAccessATTIDAndProfile_cbu= UI.WaitForObject(oR_Profile.lnkAttAccessIdProfile, 40, lDriver);
					//Report.ValidationPoint(testContext.getName(), "Verify that At&t Access ID Profile link is displayed", "true", String.valueOf(bAccessATTIDAndProfile_cbu), true);
					
					/*---------------- Group 1:- Verify Account Information tab elements ---------------*/
					
					//Verifying Account Information section is displayed with Edit Link
					Boolean bAccInfoHdr_cbu=UI.WaitForObject(oR_Profile.txtAccountInformationHdr, 10, lDriver);
					if (bAccInfoHdr_cbu) {
						Report.ValidationPoint(testContext.getName(), "Verify Account information header is displayed", "Account information header is displayed", "Account information header is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Account information header is displayed", "Account information header is displayed", "Account information header is not displayed", true);
					}
				
					Boolean bAccInfoEditLink_cbu=UI.WaitForObject(oR_Profile.lnkEditAccInfo, 10, lDriver);
					if (bAccInfoEditLink_cbu) {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Account information link is displayed", "Edit Account information link is displayed", "Edit Account information link is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Account information link is displayed", "Edit Account information link is displayed", "Edit Account information link is not displayed", true);
					}
					
					//Verifying Billing Contact Information section is displayed with Edit Link
					Boolean bBillConInfoHdr_cbu=UI.WaitForObject(oR_Profile.txtBillingContactInfo, 10, lDriver);
					if (bBillConInfoHdr_cbu) {
						Report.ValidationPoint(testContext.getName(), "Verify Billing Contact information section is displayed", "Billing Contact information section is displayed", "Billing Contact information section is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Billing Contact information section is displayed", "Billing Contact information section is displayed", "Billing Contact information section is not displayed", true);
					}
				
					Boolean bBillConEditLink_cbu=UI.WaitForObject(oR_Profile.lnkEditBillingContactInfo, 10, lDriver);
					if (bBillConEditLink_cbu) {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Billing Contact information link is displayed", "Edit Billing Contact information link is displayed", "Edit Billing Contact information link is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Billing Contact information link is displayed", "Edit Billing Contact information link is displayed", "Account information header is not displayed", true);
					}					
					
					//Verifying Billing Notifications section is displayed with Edit Link
					Boolean bBillNotifications_cbu=UI.WaitForObject(oR_Profile.txtBillingNotifications, 10, lDriver);
					if (bBillNotifications_cbu) {
						Report.ValidationPoint(testContext.getName(), "Verify Billing Notifications section is displayed", "Billing Notifications section is displayed", "Billing Notifications section is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Billing Notifications section is displayed", "Billing Notifications section is displayed", "Billing Notifications section is not displayed", true);
					}
				
					Boolean bBillNotifyEditLink_cbu=UI.WaitForObject(oR_Profile.lnkEditBillingNotification, 10, lDriver);
					if (bBillNotifyEditLink_cbu) {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Billing Notifications link is displayed", "Edit Billing Notifications link is displayed", "Edit Billing Notifications link is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Billing Notifications link is displayed", "Edit Billing Notifications link is displayed", "Edit Billing Notifications link is not displayed", true);
					}	
					
					//validating Billing Threshold & Bill Ready notifications in account information tab
					boolean bBillThreshold_cbu= UI.WaitForObject(oR_Profile.txtBillThresholdNotifications_1511, 5, lDriver);
					boolean bBillReady_cbu= UI.WaitForObject(oR_Profile.txtBillReadyNotifications_1511, 5, lDriver);
					boolean bBillPayReminder_cbu= UI.WaitForObject(oR_Profile.txtPayBillReminderNotification,5, lDriver);
					if(bBillThreshold_cbu && bBillReady_cbu && bBillPayReminder_cbu)
					{
						Report.TestPoint(testContext.getName(),"Verify Bill Threshold, Bill Ready & Bill Pay Reminder notifications are present","true", String.valueOf(bBillReady_cbu), true);
					}
					
					//Verifying email id present in bill Ready notifications
					boolean bEmailIDInBillReady_cbu= UI.WaitForObject(oR_Profile.txtEmailIDInBillReady, 5, lDriver);
					Report.TestPoint(testContext.getName(),"Verify Email Id present in Bill Ready notifications","true", String.valueOf(bEmailIDInBillReady_cbu), true);
					
					//Verifying email id present in payment received notifications
					boolean bEmailIDInPaymentNotifications_cbu= UI.WaitForObject(oR_Profile.txtEmailIDInBillReady, 5, lDriver);
					Report.TestPoint(testContext.getName(),"Verify Email Id present in Payment Received notifications","true", String.valueOf(bEmailIDInPaymentNotifications_cbu), true);
					
					//Verifying paper bill options in account information tab
					Boolean bPaperBillOptions_cbu=UI.WaitForObject(oR_Profile.txtPaperBillOptions, 10, lDriver);
					if (bPaperBillOptions_cbu) {
						Report.ValidationPoint(testContext.getName(), "Verify Paper Bill Options text is displayed", "Paper Bill Options text is displayed", "Paper Bill Options text is displayed", true);
					}
					
					
					//Verifying Service Address in account information tab
					Boolean bServiceAdd_cbu=UI.WaitForObject(oR_Profile.txtServiceAddress, 10, lDriver);
					if (bServiceAdd_cbu) {
						Report.ValidationPoint(testContext.getName(), "Verify Paper Bill Options text is displayed", "Paper Bill Options text is displayed", "Paper Bill Options text is displayed", true);
					}
					
					break;
					
				case "Legacy Wireline":
					
					//Verify Account information, User information and Marketing preference is displayed
					//and verify by default Account information is selected 
					Boolean accInfo_lwl=UI.WaitForObject(oR_Profile.txtAccountInformation, 10);
					Boolean userInfo_lwl=UI.WaitForObject(oR_Profile.txtUserInformation_1511, 10);
					Boolean marketingPref_lwl=UI.WaitForObject(oR_Profile.txtMarketingPreference, 10);
					if (accInfo_lwl) {
						Report.ValidationPoint(testContext.getName(), "Verify Account information tab is displayed and selected by default", "Account information tab is displayed and selected by default", "Account information tab is displayed and selected by default", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Account information tab is displayed and selected by default", "Account information tab is displayed and selected by default", "Account information tab is not selected by default", true);
					}
					if (userInfo_lwl) {
						Report.ValidationPoint(testContext.getName(), "Verify User information tab is displayed", "User information tab is displayed", "User information tab is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify User information tab is displayed", "User information tab is displayed", "User information tab is not displayed", true);
					}
					if (marketingPref_lwl) {
						Report.ValidationPoint(testContext.getName(), "Verify User Marketing preference is displayed", "Marketing preference is displayed", "Marketing preference is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify User Marketing preference is displayed", "Marketing preference is displayed", "Marketing preference is not displayed", true);
					}
					
					//This Step is specific to only Slid Account type, we can exclude this step from other Account types - Yuvashree
					//commented by Clint on 18-Dec-2015
					//Verifying Access ID and Profile link
					//boolean bAccessATTIDAndProfile_lwl= UI.WaitForObject(oR_Profile.lnkAttAccessIdProfile, 40, lDriver);
					//Report.ValidationPoint(testContext.getName(), "Verify that At&t Access ID Profile link is displayed", "true", String.valueOf(bAccessATTIDAndProfile_lwl), true);
					
					boolean bAccWireline_lwl= UI.WaitForObject(oR_Profile.txtAccWirelineOrBTN, 40, lDriver);
					if(bAccWireline_lwl){
						String sAccName=oR_Profile.txtAccWirelineOrBTN.getText();
						Report.ValidationPoint(testContext.getName(), "Verify that Wireline or BTN account is displayed", String.valueOf(sAccName), String.valueOf(sAccName), true);
					}
					/*---------------- Group 1:- Verify Account Information tab elements ---------------*/
					
					//Verifying Account Information section is displayed with Edit Link
					Boolean bAccInfoHdr_lwl=UI.WaitForObject(oR_Profile.txtAccountInformationHdr, 10, lDriver);
					if (bAccInfoHdr_lwl) {
						Report.ValidationPoint(testContext.getName(), "Verify Account information header is displayed", "Account information header is displayed", "Account information header is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Account information header is displayed", "Account information header is displayed", "Account information header is not displayed", true);
					}
				
					Boolean bAccInfoEditLink_lwl=UI.WaitForObject(oR_Profile.lnkEditAccInfo, 10, lDriver);
					if (bAccInfoEditLink_lwl) {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Account information link is displayed", "Edit Account information link is displayed", "Edit Account information link is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Account information link is displayed", "Edit Account information link is displayed", "Edit Account information link is not displayed", true);
					}
					
					//Verifying Billing Contact Information section is displayed with Edit Link
					Boolean bBillConInfoHdr_lwl=UI.WaitForObject(oR_Profile.txtBillingContactInfo, 10, lDriver);
					if (bBillConInfoHdr_lwl) {
						Report.ValidationPoint(testContext.getName(), "Verify Billing Contact information section is displayed", "Billing Contact information section is displayed", "Billing Contact information section is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Billing Contact information section is displayed", "Billing Contact information section is displayed", "Billing Contact information section is not displayed", true);
					}
				
					Boolean bBillConEditLink_lwl=UI.WaitForObject(oR_Profile.lnkEditBillingContactInfo, 10, lDriver);
					if (bBillConEditLink_lwl) {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Billing Contact information link is displayed", "Edit Billing Contact information link is displayed", "Edit Billing Contact information link is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Billing Contact information link is displayed", "Edit Billing Contact information link is displayed", "Account information header is not displayed", true);
					}					
					
					//Verifying Billing Notice Settings section is displayed with Edit Link
					Boolean bBillNotice_lwl=UI.WaitForObject(oR_Profile.txtBillingNoticeSettings, 10, lDriver);
					if (bBillNotice_lwl) {
						Report.ValidationPoint(testContext.getName(), "Verify Billing Notice Settings section is displayed", "Billing Notice Settings section is displayed", "Billing Notice Settings section is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Billing Notice Settings section is displayed", "Billing Notice Settings section is displayed", "Billing Notice Settings section is not displayed", true);
					}
				
					Boolean bBillNoticeEditLink_lwl=UI.WaitForObject(oR_Profile.lnkEditBillingNoticeSettings_wls, 10, lDriver);
					if (bBillNoticeEditLink_lwl) {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Billing Notice Settings link is displayed", "Edit Billing Notice Settings link is displayed", "Edit Billing Notice Settings link is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Verify Edit Billing Notice Settings link is displayed", "Edit Billing Notice Settings link is displayed", "Edit Billing Notice Settings link is not displayed", true);
					}	
					
					//validating Bill Ready notice in account information tab
					boolean bBillReadyNotice_lwl= UI.WaitForObject(oR_Profile.txtBillReadyNoticeSettings, 5, lDriver);
					Report.TestPoint(testContext.getName(),"Verify Bill Ready Notice Settings are present","true", String.valueOf(bBillReadyNotice_lwl), true);
					
					//validating Bill Ready notice in account information tab
					boolean bBillReadyDelivery_lwl= UI.WaitForObject(oR_Profile.txtBillReadyNoticeSettings, 5, lDriver);
					Report.TestPoint(testContext.getName(),"Verify Bill Ready Delivery are present","true", String.valueOf(bBillReadyDelivery_lwl), true);
					
					//validating Pay Bill Reminder notice in account information tab
					boolean bPayBillReminderNotice_lwl= UI.WaitForObject(oR_Profile.txtPayBillReminderNoticeSettings, 5, lDriver);
					Report.TestPoint(testContext.getName(),"Verify Pay Bill Reminder Notice Settings are present","true", String.valueOf(bPayBillReminderNotice_lwl), true);
					
					//validating Pay Bill Reminder notice delivery in account information tab
					boolean bPayBillReminderNoticeDelivery_lwl= UI.WaitForObject(oR_Profile.txtPayBillReminderNoticeDelivery, 5, lDriver);
					Report.TestPoint(testContext.getName(),"Verify Bill Ready Delivery are present","true", String.valueOf(bPayBillReminderNoticeDelivery_lwl), true);
					
					//validating Bill Threshold Amount notice in account information tab
					boolean bBillThresholdNotice_lwl= UI.WaitForObject(oR_Profile.txtBillThresholdAmtNoticeSettings, 5, lDriver);
					Report.TestPoint(testContext.getName(),"Verify Bill Threshold Amount notice Settings are present","true", String.valueOf(bBillThresholdNotice_lwl), true);
					
					//validating Bill Threshold Amount notice delivery in account information tab
					boolean bBillThresholdNoticeDelivery_lwl= UI.WaitForObject(oR_Profile.txtBillThresholdAmtNoticeDelivery, 5, lDriver);
					Report.TestPoint(testContext.getName(),"Verify Bill Threshold Amount notice Delivery are present","true", String.valueOf(bPayBillReminderNoticeDelivery_lwl), true);
					
										
					//Verifying Payment Profiles present in account information tab
					Boolean bPaymentProfiles_lwl=UI.WaitForObject(oR_Profile.txtPaymentProfiles, 10, lDriver);
					if (bPaymentProfiles_lwl) {
						Report.ValidationPoint(testContext.getName(), "Verify Payment Profile text is displayed", "true", String.valueOf(bPaymentProfiles_lwl), true);
						
						if(UI.WaitForObject(oR_Profile.lnkAddNewPaymentMethod, 2, lDriver)) {
							Report.ValidationPoint(testContext.getName(), "Verify Add new Payment Profile link is displayed", "true", "true", true);
						}
					}
					
					//Verifying paper bill options in account information tab
					Boolean bPaperBillOptions_lwl=UI.WaitForObject(oR_Profile.txtPaperBillOptions, 10, lDriver);
					if (bPaperBillOptions_lwl) {
						Report.ValidationPoint(testContext.getName(), "Verify Paper Bill Options text is displayed", "Paper Bill Options text is displayed", "Paper Bill Options text is displayed", true);
					}
					
					
					//Verifying Service Address in account information tab
					Boolean bServiceAdd_lwl=UI.WaitForObject(oR_Profile.txtServiceAddress, 10, lDriver);
					if (bServiceAdd_lwl) {
						Report.ValidationPoint(testContext.getName(), "Verify Service Address text is displayed", "Service Address text is displayed", "Service Address text is displayed", true);
					}
					
					break;
					
				}
			
		} catch (Exception e) {
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}
	}
	
}

	