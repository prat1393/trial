package com.AppSpecificLibrary;


//import org.openqa.selenium.Alert;
//import org.openqa.selenium.support.PageFactory;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import com.OR.MyATT.OR_AddaDevice;
import com.OR.MyATT.OR_AutoPayEnrollment;
import com.OR.MyATT.OR_EditATTAccessIDInformation;
import com.OR.MyATT.OR_ForgotUserID;
import com.OR.MyATT.OR_LinkAnAccount;
import com.OR.MyATT.OR_Login;
import com.OR.MyATT.OR_MessageCenter;
import com.OR.MyATT.OR_MyAccountAccess;
import com.OR.MyATT.OR_MyWirelessPlan;
import com.OR.MyATT.OR_Profile;
import com.OR.MyATT.OR_RequestAccessConfirmation;
import com.OR.MyATT.OR_Shop;
import com.OR.MyATT.OR_SuspendReactivateService;
import com.OR.MyATT.OR_ViewOrChangeRatePlan;
import com.SupportingFiles.IO;
//import com.OR.BillAndPayments.OR_BillAndPayments;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;
import com.SupportingFiles.UI;
import com.gargoylesoftware.htmlunit.javascript.host.URL;

public class Profile extends LaunchAndLogout {
	private static final WebDriver IDriver = null;
	static Hashtable<String, String> sTestParams = new Hashtable<String, String>();

	/**************************************************************
	 * Function Name - VerifyProfilePage 
	 * Description- 
	 * Parameters - 
	 * Date created -
	 * Developed by - 
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void VerifyProfilePage(final ITestContext testContext)
			throws Exception {	
		
		try {
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			
			
			//validating profile link on secondary navigation
			if (UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav, 6)) {
				Report.TestPoint(testContext.getName(),
						"Validate Profile link on secondary navigation",
						"True", "True", true);
				oR_AccountOverview.lnkProfileSecondaryNav.click();

				//validating My Profile title on Profile page
				if (UI.WaitForObject(oR_Profile.txtMyProfileTitle, UI.iObjTimeOut)) {
					Report.ValidationPoint(testContext.getName(),
							"Validate MyProfile title", "True", "True",
							true);
				} else {
					Report.ValidationPoint(testContext.getName(),
							"Validate MyProfile title", "True", "False",
							true);
				}
			} else {
				Report.TestPoint(testContext.getName(),
						"Validate Profile link on secondary navigation",
						"True", "False", true);
			}
		} catch (Exception e) {
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}
	}

	/**************************************************************
	 * Function Name - VerifyContractDetailsLink 
	 * Description- 
	 * Parameters - 
	 * Date created -
	 * Developed by - 
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	public static void VerifyContractDetailsLink(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException{
		try {
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);

			//navigate to profile landing page by linking on Profile link on secondary navigation
			if (UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav, null)) {
				Report.OperationPoint(testContext.getName(), "Navigating to profile page");
				//validate navigated to profile page
				if (UI.WaitForObject(oR_Profile.txtMyProfileTitle, UI.iObjTimeOut)) {
					Report.TestPoint(testContext.getName(), "Validate navigated to profile page", "Navigated to profile page", "Navigated to profile page", true);
					//validate Account information, User information and Marketing preference is displayed
					//and validate by default Account information is selected 
					Boolean accInfo=UI.WaitForObject(oR_Profile.txtAccountInformationDefault, 10);
					Boolean userInfo=UI.WaitForObject(oR_Profile.txtUserInformation, 10);
					Boolean marketingPref=UI.WaitForObject(oR_Profile.txtMarketingPreference, 10);
					if (accInfo) {
						Report.ValidationPoint(testContext.getName(), "Validate Account information tab is displayed and selected by default", "Account information tab is displayed and selected by default", "Account information tab is displayed and selected by default", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Validate Account information tab is displayed and selected by default", "Account information tab is displayed and selected by default", "Account information tab is not selected by default", true);
					}
					if (userInfo) {
						Report.ValidationPoint(testContext.getName(), "Validate User information tab is displayed and selected by default", "User information tab is displayed", "User information tab is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Validate User information tab is displayed and selected by default", "User information tab is displayed", "User information tab is not displayed", true);
					}
					if (marketingPref) {
						Report.ValidationPoint(testContext.getName(), "Validate User Marketing preference is displayed and selected by default", "Marketing preference is displayed", "Marketing preference is displayed", true);
					} else {
						Report.ValidationPoint(testContext.getName(), "Validate User Marketing preference is displayed and selected by default", "Marketing preference is displayed", "Marketing preference is not displayed", true);
					}

				} else {
					Report.TestPoint(testContext.getName(), "Validate navigated to profile page", "Navigated to profile page", "Failed to navigated to profile page", true);
				}
			}
		} catch (Exception e) {
			Report.TestPoint(testContext.getName(), "Validate contract details method", "Should validate contract details method", "Some error occurred : \n\n "+e.getMessage(), true);
		}

	}

	/**************************************************************
	 * Function Name - VerifyLinkMyAccount() 
	 * Description- This functions validates Link Account flow for HomePhone, Internet & Digital TV
	 * Parameters - sAccType- Acc type to be linked.sBAN, sZipCode, sCustcCode - details required for linking 
	 * Date created - 6th Feb
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	//PRF08219
	public static void VerifyLinkMyAccount(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		
		Boolean bLinkMyAcc, bSelectOption, bImgYes;
		String sBAN, sAccType, sZipCode, sCustCode;
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

		sBAN = IO.GetParamVal(sTestParams, "BAN");
		sAccType = IO.GetParamVal(sTestParams, "AccType");
		sZipCode = IO.GetParamVal(sTestParams, "Zip");
		sCustCode = IO.GetParamVal(sTestParams, "Customer_Code");
		try		
		{
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
			OR_LinkAnAccount oR_LinkAnAccount = PageFactory.initElements(lDriver, OR_LinkAnAccount.class);

			
			Report.OperationPoint(testContext.getName(), "Validate Link Account Flow for HomePhone, Internet & Digital TV");
			bLinkMyAcc = UI.WaitForObject(oR_ATT.imgLinkMyAccount, UI.iObjTimeOut);
			if(bLinkMyAcc.equals(true))
			{

				//Click on Link my accounts.
				Report.TestPoint(testContext.getName(), "Link My Account Button", "true", String.valueOf(bLinkMyAcc), true);
				oR_ATT.imgLinkMyAccount.click();

				UI.WaitForObject(oR_LinkAnAccount.lstSelAcc, UI.iObjTimeOut);

				//Select account type as "Home Phone, Internet & Digital TV"	

				bSelectOption = UI.SelectOptionFromDropDown(oR_LinkAnAccount.lstSelAcc, sAccType);
				System.out.println("blnSelectOption " + bSelectOption);

				//Enter the BAN number, zip code and click on next.
				if(bSelectOption.equals(true))
				{
					if(UI.WaitForObject(oR_LinkAnAccount.edtBillingAccNum, UI.iObjTimeOut).equals(true))
					{	
						Report.ValidationPoint(testContext.getName(), "Field BAN/BTN", "true", String.valueOf(UI.WaitForObject(oR_LinkAnAccount.edtBillingAccNum, UI.iObjTimeOut)), true);
						oR_LinkAnAccount.edtBillingAccNum.sendKeys(sBAN);						
					}

					if(UI.WaitForObject(oR_LinkAnAccount.edtZipCode, 2).equals(true))
					{
						Report.ValidationPoint(testContext.getName(), "Field Billing ZIP Code", "true", String.valueOf(UI.WaitForObject(oR_LinkAnAccount.edtZipCode, UI.iObjTimeOut)), true);
						System.out.println("Zip Code :" + sZipCode);
						oR_LinkAnAccount.edtZipCode.sendKeys(sZipCode.trim());
						
					}

					if(UI.WaitForObject(oR_LinkAnAccount.imgNext,2).equals(true))
					{
						//Enter the CUST Code and click on Next.
						Report.TestPoint(testContext.getName(), "Click on Next Button", "true", String.valueOf(UI.WaitForObject(oR_LinkAnAccount.imgNext, UI.iObjTimeOut)), true);
						oR_LinkAnAccount.imgNext.click();
						
						/*** CODE MODIFIED AS PER UI CHANGES FOR 1508*/
						//Select Request Secondary Access Radio Button
						Boolean bRad = UI.WaitForObject(oR_LinkAnAccount.radReqForSecAccess, UI.iObjTimeOut);
						Report.ValidationPoint(testContext.getName(), "Select secondary access radio button", "true", String.valueOf(bRad), true);
						oR_LinkAnAccount.radReqForSecAccess.click();					
						
						//Enter the CUST Code 
						if(UI.WaitForObject(oR_ATT.edtCustomerCode,UI.iObjTimeOut))
						{
							Report.ValidationPoint(testContext.getName(), "Field Customer Code", "true", String.valueOf(UI.WaitForObject(oR_ATT.edtCustomerCode, UI.iObjTimeOut)), true);
							oR_ATT.edtCustomerCode.sendKeys(sCustCode);
						}
						
						Boolean bNext = UI.WaitForObject(oR_LinkAnAccount.btnNext2, UI.iObjTimeOut);
						Report.ValidationPoint(testContext.getName(), "Click Next", "true", String.valueOf(bNext), true);
						oR_LinkAnAccount.btnNext2.click();
						
					
						Boolean bConfirmation = UI.WaitForObject(oR_ATT.txtRequestForAccess, UI.iObjTimeOut);
						Report.ValidationPoint(testContext.getName(), "Access Confirmation", "true", String.valueOf(bConfirmation), true);

//						//Enter the CUST Code 
//						if(UI.WaitForObject(oR_ATT.edtCustomerCode,UI.iObjTimeOut).equals(true))
//						{
//							Report.TestPoint(testContext.getName(), "Field Customer Code", "true", String.valueOf(UI.WaitForObject(oR_ATT.edtCustomerCode, UI.iObjTimeOut)), true);
//							oR_ATT.edtCustomerCode.sendKeys(sCustCode);
//
//							//Click on Cancel button click on Yes on the pop up window then click on Logout.
//							if(UI.WaitForObject(oR_ATT.lnkCancel,UI.iObjTimeOut).equals(true))
//							{
//								Report.TestPoint(testContext.getName(), "Link Cancel", "true", String.valueOf(UI.WaitForObject(oR_ATT.lnkCancel, UI.iObjTimeOut)), true);
//								oR_ATT.lnkCancel.click();		
//								Thread.sleep(5000);
//								//click on Yes on the pop up window 
//								if(UI.WaitForObject(oR_ATT.frmCancelRequest, UI.iObjTimeOut).equals(true))
//								{
//									Report.TestPoint(testContext.getName(), "Frame Cancel Request", "true", String.valueOf(lDriver.findElement(By.xpath("//*[contains(@src,'slidCancelPopup.jsp')]")).isDisplayed()), true);					
//									lDriver.switchTo().frame(oR_ATT.frmCancelRequest);
//									bImgYes = UI.WaitForObject(oR_ATT.imgYes, UI.iObjTimeOut);
//									if(bImgYes.equals(true))
//									{	
//										Report.ValidationPoint(testContext.getName(), "Yes Button", "Present", "Present", true);
//										lDriver.findElement(By.xpath("//img[@alt='Yes']")).click();			
//									}
//									else
//									{
//										Report.ValidationPoint(testContext.getName(), "Yes Button", "Present", "Absent", true);
//									}							
//								}
//
//
//							}


						}

					}

				}
		

		}		

		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Failed to verify Link My Account flow", "true", "false", true);
		}
	}
	
	


	/**************************************************************
	 * Function Name - VerifyLinkMyAccount() 
	 * Description- This functions validates wireline unregistered account in acc Info section of acc Selection page
	 * Parameters - sAccType- Acc type to be linked.sBAN, sZipCode- details required for linking 
	 * Date created - 19th Feb
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	public static void VerifyUnregisteredWirelineAcc(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		Boolean bRegister, bSelectOption;
		String sBAN, sAccType, sZipCode;
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

		sBAN = IO.GetParamVal(sTestParams, "BAN");
		sAccType = IO.GetParamVal(sTestParams, "AccType");
		sZipCode = IO.GetParamVal(sTestParams, "Zip");
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Login oR_Login =  PageFactory.initElements(lDriver, OR_Login.class);
			OR_AccountInformation oR_AccountInformation = PageFactory.initElements(lDriver, OR_AccountInformation.class);
			OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
			OR_LinkAnAccount oR_LinkAnAccount = PageFactory.initElements(lDriver, OR_LinkAnAccount.class);

			Report.OperationPoint(testContext.getName(), "Validate Wireline unregistered Flow for HomePhone, Internet & Digital TV");
			bRegister = UI.WaitForObject(oR_Login.lnkRegisterToday, UI.iObjTimeOut);
			if(bRegister.equals(true))
			{

				//Click on Link my accounts.
				Report.TestPoint(testContext.getName(), "Verify that Register Today link is displayed", "true", String.valueOf(bRegister), true);
				oR_Login.lnkRegisterToday.click();

				UI.WaitForObject(oR_AccountInformation.lstSelectAccount, UI.iObjTimeOut);

				//Select account type as "Home Phone, Internet & Digital TV"	

				bSelectOption = UI.SelectOptionFromDropDown(oR_AccountInformation.lstSelectAccount, sAccType);
				System.out.println("blnSelectOption " + bSelectOption);

				//Enter the BAN number, zip code and click on next.
				if(bSelectOption.equals(true))
				{
					if(UI.WaitForObject(oR_AccountInformation.edtBillingAccNum, UI.iObjTimeOut).equals(true))
					{	
						oR_AccountInformation.edtBillingAccNum.sendKeys(sBAN);
						Report.ValidationPoint(testContext.getName(), "Verify that field for BAN/BTN is displayed and values are entered", "true", String.valueOf(UI.WaitForObject(oR_AccountInformation.edtBillingAccNum, UI.iObjTimeOut)), true);
												
					}

					if(UI.WaitForObject(oR_AccountInformation.edtZipCode, 2).equals(true))
					{
						oR_AccountInformation.edtZipCode.sendKeys(sZipCode);
						Report.ValidationPoint(testContext.getName(), "Verify that field for Billing ZIP Code is displayed", "true", String.valueOf(UI.WaitForObject(oR_AccountInformation.edtZipCode, UI.iObjTimeOut)), true);
						
					}

					if(UI.WaitForObject(oR_AccountInformation.btnNext,2).equals(true))
					{
						//Click on Next.
						Report.TestPoint(testContext.getName(), "Click on Next Button", "true", String.valueOf(UI.WaitForObject(oR_AccountInformation.btnNext, UI.iObjTimeOut)), true);
						oR_AccountInformation.btnNext.click();
					
						/**Code Added to validate step -Monica 2nd April 2015 **/
						//Redirected to AT&T Access ID 
						Report.ValidationPoint(testContext.getName(), "Verify that ATT Access ID page is displayed", "true", String.valueOf(UI.WaitForObject(oR_LinkAnAccount.txtConverttoAnATTAccessID, UI.iObjTimeOut)), true);
						//Click on Back button 
						if(UI.WaitForObject(oR_ATT.lnkBack,UI.iObjTimeOut).equals(true))
						{
							
							Report.TestPoint(testContext.getName(), "Clicked on 'Back' button", "true", String.valueOf(UI.WaitForObject(oR_ATT.lnkBack, UI.iObjTimeOut)), true);
							oR_ATT.lnkBack.click();		
							
							/**Code Added to validate step -Monica 2nd April 2015 **/
							Thread.sleep(5000);
							//Navigates to Account selection page back 
							Report.ValidationPoint(testContext.getName(), "Verify that user is navigated back to Account Selection Page", "true", String.valueOf(oR_AccountInformation.lstSelectAccount.isDisplayed()| oR_AccountInformation.edtBillingAccNum.isDisplayed()), true);
							//Report.ValidationPoint(testContext.getName(), "Back to Account Selection Page", "true", "true", true);
						}

					}
				}						
			}
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Failed to verify Link My Account flow", "true", "false", true);
		}
	}


	/**************************************************************
	 * Function Name - VerifyLinkAccount
	 * Description- Verify for a naked SLID Account is linked successfully.Link using At&t email account and also verify linking confirmation page is displayed
	 * Parameters - None
	 * Date created - 19th Feb 2015
	 * Developed by - Clint John
	 * Last Modified By - Clint John
	 * Last Modified Date - 20th Feb 2015
	 * @throws ParseException 
	 ***************************************************************/
	public static void VerifyLinkAccount(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_LinkAnAccount oR_LinkAnAccount = PageFactory.initElements(lDriver, OR_LinkAnAccount.class);

			Boolean checkObject = UI.WaitForObject(oR_LinkAnAccount.txtAccountOverview, 40);

			//Validate the Title of the page - Account Overview
			Report.TestPoint(testContext.getName(), "Verify that Account Overview page is displayed for user with 'Link My Account' option displayed", "true", checkObject.toString(), true);

			//If true validate the sub elements in the Account overview page
			if(checkObject.equals(true))
			{
				Boolean checkSubObject1 = UI.WaitForObject(oR_LinkAnAccount.txtLinkAnAccount, 40);
				Report.ValidationPoint(testContext.getName(), "Verify that 'Link an Account to Your AT&T Access ID' sub title is displayed ", "true", checkSubObject1.toString(), true);
				checkSubObject1= UI.WaitForObject(oR_LinkAnAccount.txtMessageUnderLinkAnAccount, 40);
				Report.ValidationPoint(testContext.getName(), "Verify that message reagrding active accounts is displayed ", "true", checkSubObject1.toString(), true);
				checkSubObject1= UI.WaitForObject(oR_LinkAnAccount.lnkAttAccessIDProfile, 40);
				Report.ValidationPoint(testContext.getName(), "Verify that At&t Access ID Profile link is displayed", "true", checkSubObject1.toString(), true);
				checkSubObject1= UI.WaitForObject(oR_LinkAnAccount.lnkChangeAttAccessID, 40);
				Report.ValidationPoint(testContext.getName(), "Verify that Change At&t access ID link is displayed", "true", checkSubObject1.toString(), true);

				//Validate the button for Link My Account is displayed. If yes navigate to next page
				if(UI.WaitForObject(oR_LinkAnAccount.lnkLinkMyAccount, 40).equals(true))
				{
					Report.TestPoint(testContext.getName(), "Verify that the button for 'Link My Account is displayed", "'Link My Account' button is displayed", "'Link My Account' button is displayed", true);
					oR_LinkAnAccount.lnkLinkMyAccount.click();
					Report.OperationPoint(testContext.getName(), "Operational - Link My Account button is clicked");

					//Verify User lands on Account Type selection page
					checkObject = UI.WaitForObject(oR_LinkAnAccount.txtSelectAccountType, 40);
					Report.TestPoint(testContext.getName(), "Verify that user lands of Account Type Selection page", "true", checkObject.toString(), true);
					if(checkObject.equals(true))
					{
						Boolean checkSubObject2 = UI.WaitForObject(oR_LinkAnAccount.txtProvideAccountInformation, 40);
						Report.ValidationPoint(testContext.getName(), "Verify that sub-heading 'Provide Account Information' is displayed ", "true", checkSubObject2.toString(), true);
						checkSubObject2=UI.WaitForObject(oR_LinkAnAccount.txtAcctype, 40);
						Report.ValidationPoint(testContext.getName(), "Verify that label for Account type is displayed ", "true", checkSubObject2.toString(), true);
						checkSubObject2=UI.WaitForObject(oR_LinkAnAccount.lstSelAccType, 40);
						Report.TestPoint(testContext.getName(), "Verify that drop-down for account selection is displayed", "true", checkSubObject2.toString(), true);

						//Select At&t Email from drop down for account selection drop-down
						if(checkSubObject2.equals(true))
						{
							//Verify option for 'At&t email' is present under drop-down list. If yes, select it and continue
							checkSubObject2=UI.WaitForObject(oR_LinkAnAccount.txtAttmail, 40);
							Report.TestPoint(testContext.getName(), "Verify that option for 'At&t email' is displayed in drop-down for account selection", "true", checkSubObject2.toString(), true);

							//Click on At&t email from drop-down

							//UI.SelectOptionFromDropDown(lstWebList, sOption)
							new Actions(lDriver).moveToElement(oR_LinkAnAccount.txtAttmail).click().perform();
							Report.OperationPoint(testContext.getName(), "Operational - Clicked option 'At&t mail' from the drop-down list");

							//Verify At&t email address label and input box is displayed
							checkSubObject2=UI.WaitForObject(oR_LinkAnAccount.txtAttEmailAddress, 40);
							Report.ValidationPoint(testContext.getName(), "Verify that AT&T Email Address label is displayed", "true", checkSubObject2.toString(), true);

							//Enter email in email address text box
							String memberID="qay1210_dr63453@att.net";
							oR_LinkAnAccount.edtBillingAccNum.sendKeys(memberID);
							Report.OperationPoint(testContext.getName(), "Operational - At&t Email address in entered in the text box");

							//Check Next button is displayed
							checkSubObject2=UI.WaitForObject(oR_LinkAnAccount.btnNext, 40);
							Report.TestPoint(testContext.getName(), "Verify that Next button is displayed", "true", checkSubObject2.toString(), true);
							//Click on Next to navigate to new page
							oR_LinkAnAccount.btnNext.click();
							Report.OperationPoint(testContext.getName(), "Operational - Clicked on Next button");

							//Verify that AT&T Access ID – Verify Account Information page is displayed
							checkObject = UI.WaitForObject(oR_LinkAnAccount.txtVerifyAccountInformation, 40);
							Report.TestPoint(testContext.getName(), "Verify that AT&T Email Address label is displayed", "true", checkSubObject2.toString(), true);

							//Verify the objects are displayed in the page
							if(checkObject.equals(true))
							{
								Boolean checkSubObject3 = UI.WaitForObject(oR_LinkAnAccount.txtLinkAccount, 40);
								Report.ValidationPoint(testContext.getName(), "Verify that Link Account subheading is displayed", "true", checkSubObject3.toString(), true);

								//check for Member ID label
								checkSubObject3 = UI.WaitForObject(oR_LinkAnAccount.txtMemberIDHeading, 40);
								Report.ValidationPoint(testContext.getName(), "Verify Member ID label is displayed", "true", checkSubObject3.toString(), true);
								//validate correct for Member ID is displayed
								checkSubObject3 = UI.WaitForObject(oR_LinkAnAccount.txtMemberIDfeild, 40);
								Report.ValidationPoint(testContext.getName(), "Verify Member ID feild is displayed", "true", checkSubObject3.toString(), true);

								/*//Compare the member ID displayed and the member ID entered by user
									if(oR_LinkAnAccount.txtMemberIDfeild.equals(memberID))
									{
										Report.ValidationPoint(testContext.getName(), "Verify that Member ID displayed is matching to the entered Member ID", "Member ID displayed is matching to the entered Member ID","Member ID displayed is matching to the entered Member ID", true);
									}else
									{
										Report.ValidationPoint(testContext.getName(), "Verify that Member ID displayed is matching to the entered Member ID", "Member ID displayed is matching to the entered Member ID","Member ID displayed is NOT matching to the entered Member ID", true);
									}*/

								//check for password label
								checkSubObject3 = UI.WaitForObject(oR_LinkAnAccount.txtPasswordHeading, 40);
								Report.ValidationPoint(testContext.getName(), "Verify Password label is displayed", "true", checkSubObject3.toString(), true);
								checkSubObject3 = UI.WaitForObject(oR_LinkAnAccount.edtPassword, 40);
								Report.ValidationPoint(testContext.getName(), "Verify password edit box displayed", "true", checkSubObject3.toString(), true);
								//enter password
								String pswd="test1ng";
								oR_LinkAnAccount.edtPassword.sendKeys(pswd);
								Report.OperationPoint(testContext.getName(), "Operational - Password entered successfully");

								//Verify forgot your password link is displayed
								checkSubObject3 = UI.WaitForObject(oR_LinkAnAccount.lnkForgotYourPassword, 40);
								Report.ValidationPoint(testContext.getName(), "Verify that link for Forgot your password is displayed", "true", checkSubObject3.toString(), true);

								//Continue to next page
								checkSubObject3 = UI.WaitForObject(oR_LinkAnAccount.btnContinue, 40);
								Report.ValidationPoint(testContext.getName(), "Verify that continue button is displayed", "true", checkSubObject3.toString(), true);
								oR_LinkAnAccount.btnContinue.click();
								Report.OperationPoint(testContext.getName(), "Operational - Clicked on Continue button");

								//Verify Agree to account linking page is displayed
								checkObject=UI.WaitForObject(oR_LinkAnAccount.txtAgreeAccLink, 40);
								Report.TestPoint(testContext.getName(), "Verify Agree to account linking page is displayed", "true", checkObject.toString(), true);
								if(checkObject.equals(true))
								{
									Boolean checkSubObject4 = UI.WaitForObject(oR_LinkAnAccount.txtAttAccessSubHeading, 40);
									Report.ValidationPoint(testContext.getName(), "Verify that 'AT&T Access ID Linking' sub-title is displayed", "true", checkSubObject4.toString(), true);

									//Click on Continue button
									checkSubObject4 = UI.WaitForObject(oR_LinkAnAccount.btnContinue, 40);
									Report.TestPoint(testContext.getName(), "Verify that continue button is displayed", "true", checkSubObject4.toString(), true);
									oR_LinkAnAccount.btnContinue.click();
									Report.OperationPoint(testContext.getName(), "Operational - Clicked on Continue button");

									//Verify that Success/Confirmation page is displayed
									checkObject = UI.WaitForObject(oR_LinkAnAccount.txtSuccessTitle, 40);
									Report.TestPoint(testContext.getName(), "Verify that user lands on Confirmation page", "true", checkObject.toString(), true);

									//Verify sub elements in the page
									if(checkObject.equals(true))
									{
										Boolean checkSubObject5 = UI.WaitForObject(oR_LinkAnAccount.txtConfirmationTitle, 40);
										Report.ValidationPoint(testContext.getName(), "Verify that sub-heading 'Confirmation' is displayed ", "true", checkSubObject5.toString(), true);
										checkSubObject5 = UI.WaitForObject(oR_LinkAnAccount.txtSuccessfulMsg, 40);
										Report.ValidationPoint(testContext.getName(), "Verify that confirmation message is displayed ", "true", checkSubObject5.toString(), true);
										checkSubObject5 = UI.WaitForObject(oR_LinkAnAccount.txtlinkedUserid, 40);
										Report.ValidationPoint(testContext.getName(), "Verify that user ID which linked to new account is displayed ", "true", checkSubObject5.toString(), true);
										checkSubObject5 = UI.WaitForObject(oR_LinkAnAccount.rdoRadioOptionForm, 40);
										Report.ValidationPoint(testContext.getName(), "Verify that message along with radio buttons are displayed ", "true", checkSubObject5.toString(), true);

										//Verify continue button is displayed
										checkSubObject5 = UI.WaitForObject(oR_LinkAnAccount.btnContinue2, 40);
										Report.ValidationPoint(testContext.getName(), "Verify that continue button is displayed ", "true", checkSubObject5.toString(), true);

									}

								}
							}
						}
					}

				}	
				else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that the button for 'Link My Account is displayed", "'Link My Account' button is displayed", "'Link My Account' button is NOT displayed", true);					
				}
			}	


		}
		catch (Exception e) 
		{
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}

	}

	/**************************************************************
	 * Function Name - VerifyProfilePage 
	 * Description- verify add new Payment method modal
	 * Parameters - 
	 * Date created -27th-Feb-2015
	 * Developed by - Merrin Mathai
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void VerifyAddNewPaymentProfile(final ITestContext testContext)throws Exception {

		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			
//			Navigate to Make A Payment page.
			Report.OperationPoint(testContext.getName(), "2-Navigate to Make A Payment page.");
			oR_AccountOverview.btnMakeAPayment.click();
			
			Report.ValidationPoint(testContext.getName(), "Steps 3-6 : Chat Window Related Steps", "SPECIAL PASS", "SPECIAL PASS", false);
			//Click on Update Payment Profile link under bill and payment link In secondary navigation
			Report.OperationPoint(testContext.getName(), "7-Click on Update Stored Payment Method link under billing and payments link in secondary navigation");
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav ,oR_AccountOverview.lnkUpdatePaymentProfile);
			
			Thread.sleep(4000);

			if(UI.WaitForObject(oR_Profile.txtMyProfileTitle, UI.iObjTimeOut, lDriver)){
				Report.ValidationPoint(testContext.getName(), "Validate My Profile page displayed", "True", "True", true);
				
				//Click on Add new payment profile link under payment profile section
				Boolean bAddNewPayment=UI.WaitForObject(oR_Profile.lnkAddNewPaymentMethod, 120);
				Report.ValidationPoint(testContext.getName(), "Add new payment profile link", "true", String.valueOf(bAddNewPayment), true);
				oR_Profile.lnkAddNewPaymentMethod.click();
				Thread.sleep(5000);
				//Add new Payment profile div layer 
				lDriver.switchTo().frame(oR_Profile.frmAddProfile);
	
				//Verify that Checking /Saving account is the default payment method.
				UI.WaitForObject(oR_Profile.rbtnCheckingSaving, 20);
				Boolean bChecked = oR_Profile.rbtnCheckingSaving.isSelected();
				Report.ValidationPoint(testContext.getName(), "Checking /Saving account is the default payment method", "true", String.valueOf(bChecked), true);
	
				//Click on Credit/Debit Card radio button
				UI.WaitForObject(oR_Profile.rbtnDebitCredit,20);
				oR_Profile.rbtnDebitCredit.click();
	
				//Verify that only credit card images displayed on the div layer
				Boolean bCards=UI.WaitForObject(oR_Profile.imgCreditCards, 20);
				Report.ValidationPoint(testContext.getName(), "credit card images displayed on the div layer", "true", String.valueOf(bCards), true);
	
				//close
//				oR_Profile.lnkClose.click();
			
	
//				lDriver.switchTo().defaultContent();
		 }
		else
		 {
			 Report.ValidationPoint(testContext.getName(), "Validate My Profile page displayed", "True", "False", true);
		 }
			

		} catch (Exception e) {
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}
	}

	/**************************************************************
	 * Function Name - VerifyTelcosAccInfo 
	 * Description- verify add new Payment method modal
	 * Parameters - 
	 * Date created -13th-Mar-2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void VerifyTelcosAccInfo(final ITestContext testContext)throws Exception 
	{

		try 
		{
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);

			//Mouseover on Profile link under second global navigation and click update my profile link
			Boolean bUpdate=UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav,50);
			Report.TestPoint(testContext.getName(), "Verify Profile link under second global navigation", "true", String.valueOf(bUpdate), true);
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav ,oR_AccountOverview.lnkUpdateMyProfileTertNav);

			//Validate navigation to profile page
			Boolean bProfile=UI.WaitForObject(oR_Profile.txtMyProfileTitle,100);
			Report.TestPoint(testContext.getName(),"Validate Navigated to Profile page", "true",String.valueOf(bProfile),true);

			//Click on Select button and select carolina telco wireless account
			/*Boolean bSelect=UI.WaitForObject(oR_Profile.lnkUSM,5);
			Report.TestPoint(testContext.getName(),"Validate Select button", "true",String.valueOf(bSelect),true);
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on Select button");
			oR_Profile.lnkUSM.click();
			Boolean bCTN=UI.WaitForObject(oR_Profile.lnkCTN,5);
			Report.TestPoint(testContext.getName(),"Validate carolina telco wireless account", "true",String.valueOf(bCTN),true);
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on carolina telco wireless account");
			oR_Profile.lnkCTN.click();*/

			//Validate that Billing Contact section should have below message for carolina telco wireless customers :
			//Note: Your AT&T wireless service is part of a bundle with another provider. There is limited account management on myAT&T.
			Boolean bNote=UI.WaitForObject(oR_Profile.txtNote,50);
			Report.ValidationPoint(testContext.getName(),"Validate Billing contact section message about carolina telco", "true",String.valueOf(bNote),true);

			//Validate Additional information link
			Boolean bAdd=UI.WaitForObject(oR_Profile.lnkAdditionalInfo,5);
			Report.ValidationPoint(testContext.getName(),"Validate Additional information link", "true",String.valueOf(bAdd),true);

			//Validate payment options section
			Boolean bPayment=UI.WaitForObject(oR_Profile.txtPaymentOptions,50);
			Report.ValidationPoint(testContext.getName(),"Validate payment options section", "true",String.valueOf(bPayment),true);

			//Validate that Paperless Bill options should be suppressed
			Boolean bPaper=UI.VerifyElementNotPresent(oR_AccountOverview.lnkEnrollInPaperlessBilling,"Paperless billing");
			Report.ValidationPoint(testContext.getName(),"Validate Paperless Bill options should be suppressed", "true",String.valueOf(bPaper),true);

		} 
		catch (Exception e) 
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}
	}


	/**************************************************************
	 * Function Name - VerifyPendingPermissionLinkUverseAcc 
	 * Description- Purpose of this test case is to verify that
					1. Appropriate Confirmation message is displayed on the Account linking confirmation page.
					2. Pending permission for the approval of Linking the subaccount is displayed on the Naked SLID Overview page.
	 * Parameters - sBAN, sZipCode, sMID
	 * Date created -13th-Mar-2015
	 * Developed by - Rahul Bakde
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	public static void VerifyPendingPermissionLinkUverseAcc(final ITestContext testContext)throws Exception 
	{
		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			OR_LinkAnAccount oR_LinkAnAccount = PageFactory.initElements(lDriver, OR_LinkAnAccount.class);
			OR_ATTAccessIDVerifyAccInfo oR_ATTAccessIDVerifyAccInfo = PageFactory.initElements(lDriver, OR_ATTAccessIDVerifyAccInfo.class);
			OR_RequestAccessConfirmation oR_RequestAccessConfirmation = PageFactory.initElements(lDriver, OR_RequestAccessConfirmation.class);
			OR_MyAccountAccess oR_MyAccountAccess = PageFactory.initElements(lDriver, OR_MyAccountAccess.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			String sBAN, sZipCode, sMID;
			sBAN = IO.GetParamVal(sTestParams, "BAN");
			sZipCode = IO.GetParamVal(sTestParams, "Zip Code");
			sMID = IO.GetParamVal(sTestParams, "MID");
			if (UI.WaitForObject(oR_AccountOverview.btnLinkMyAccounts, UI.iObjTimeOut)) {
				Report.TestPoint(testContext.getName(),"Validate Link my accounts button is displayed", "True","True",true);
				oR_AccountOverview.btnLinkMyAccounts.click();
				//validate navigated to link an account page
				if (UI.WaitForObject(oR_LinkAnAccount.txtSelectAccountType, UI.iObjTimeOut)) {
					Report.OperationPoint(testContext.getName(), "Navigated to Link an Account - Select Account Type page");
					//select Uverse from account type drop down
					UI.SelectOptionFromDropDown(oR_LinkAnAccount.lstSelAcc, "U-verse TV, Internet & Voice");
					if (UI.WaitForObject(oR_LinkAnAccount.edtBillingAccNum, 1)) {
						oR_LinkAnAccount.edtBillingAccNum.sendKeys(sBAN);
						Report.OperationPoint(testContext.getName(), "Billing Account Number is entered in edit box");
					} else {
						Report.TestPoint(testContext.getName(),"Validate Billing account number edit box", "True","False",true);
					}
					if (UI.WaitForObject(oR_LinkAnAccount.edtZipCode, 1)) {
						oR_LinkAnAccount.edtZipCode.sendKeys(sZipCode);
						Report.OperationPoint(testContext.getName(), "Billing Zip Code is entered in edit box");
					} else {
						Report.TestPoint(testContext.getName(),"Validate Billing zip code edit box", "True","False",true);
					}
					if (UI.WaitForObject(oR_LinkAnAccount.btnNext, 1)) {
						oR_LinkAnAccount.btnNext.click();
						//verify navigated to AT&T Access ID - Verify Account Information page
						if (UI.WaitForObject(oR_ATTAccessIDVerifyAccInfo.txtVerifyAccInfoHeading, UI.iObjTimeOut)) {
							Report.OperationPoint(testContext.getName(), "Navigated to Verify Account Information page");
							if (UI.WaitForObject(oR_ATTAccessIDVerifyAccInfo.edtMemberID, 1)) {
								oR_ATTAccessIDVerifyAccInfo.edtMemberID.sendKeys(sMID);
								Report.TestPoint(testContext.getName(), "Validate Member ID edit box exist", "True", "True", true);
								Report.OperationPoint(testContext.getName(), "Member ID is entered in edit box");
							} else {
								Report.TestPoint(testContext.getName(),"Validate Member id edit field exist", "True","False",true);
							}
							if (UI.WaitForObject(oR_ATTAccessIDVerifyAccInfo.edtPassword, 1)) {
								Report.TestPoint(testContext.getName(), "Validate Password edit box exist", "True", "True", true);
								oR_ATTAccessIDVerifyAccInfo.edtPassword.sendKeys("test1ng");
								Report.OperationPoint(testContext.getName(), "Password is entered in edit box");
							} else {
								Report.TestPoint(testContext.getName(),"Validate Password edit field exist", "True","False",true);
							}
							if (UI.WaitForObject(oR_ATTAccessIDVerifyAccInfo.btnNext, 1)) {
								Report.TestPoint(testContext.getName(), "Validate Next button exist", "True", "True", true);
								oR_ATTAccessIDVerifyAccInfo.btnNext.click();
								//validate navigated to Request Access - Confirmation page
								if (UI.WaitForObject(oR_RequestAccessConfirmation.txtConfirmationHeading, UI.iObjTimeOut)) {
									Report.OperationPoint(testContext.getName(), "Navigated to Request Access - Confirmation page ");
									Report.ValidationPoint(testContext.getName(), "Validate Your request for access to the account associated with message is displayed", "True", UI.WaitForObject(oR_RequestAccessConfirmation.txtMsg1, 1).toString(), true);
									Report.ValidationPoint(testContext.getName(), "Validate We will send you an email  message is displayed", "True", UI.WaitForObject(oR_RequestAccessConfirmation.txtMsg2, 1).toString(), true);
									if (UI.WaitForObject(oR_RequestAccessConfirmation.radGoToMyATT, 1)) {
										Report.TestPoint(testContext.getName(), "Validate Go to account overview radio button exist", "True", "True", true);
										oR_RequestAccessConfirmation.radGoToMyATT.click();
										Report.OperationPoint(testContext.getName(), "Radio button is clicked");										
									} else {
										Report.TestPoint(testContext.getName(),"Validate radio button for Go to  MyATT exist on Request Access - Confirmation page ", "True","False",true);
									}
									if (UI.WaitForObject(oR_RequestAccessConfirmation.btnContinue, 1)) {
										Report.TestPoint(testContext.getName(), "Validate Continue button exist", "True", "True", true);
										oR_RequestAccessConfirmation.btnContinue.click();
										//validate navigated to My Account Access page
										if (UI.WaitForObject(oR_MyAccountAccess.txtMyAccountAccessHeading, UI.iObjTimeOut)) {
											Report.OperationPoint(testContext.getName(), "Navigated to My Account Access page");
											//verify pending acceptance text
											Report.TestPoint(testContext.getName(), "Validate pending acceptance text", "True", UI.WaitForObject(oR_MyAccountAccess.txtPendingAcceptance, 1).toString(), true);
											if (UI.WaitForObject(oR_MyAccountAccess.lnkOverview, 1)) {
												oR_MyAccountAccess.lnkOverview.click();
												Report.OperationPoint(testContext.getName(), "Clicked on Overview link from Secondary navigation");
												//validate successfully navigated to Account Overview page
												if (UI.WaitForObject(oR_AccountOverview.txtAccountOverviewHeading, UI.iObjTimeOut)) {
													Report.OperationPoint(testContext.getName(), "Navigated to Account Overview page");
													Report.TestPoint(testContext.getName(),"Validate Pending approval message is displayed", "True",UI.WaitForObject(oR_AccountOverview.txtYourAccessPending, 1).toString(),true);
													if (UI.WaitForObject(oR_AccountOverview.lnkCheckStatus, 1)) {
														Report.TestPoint(testContext.getName(), "Validate Check Status link exist", "True", "True", true);
														oR_AccountOverview.lnkCheckStatus.click();
														if (UI.WaitForObject(oR_MyAccountAccess.txtMyAccountAccessHeading, UI.iObjTimeOut)) {
															Report.OperationPoint(testContext.getName(), "Naviagted to My Account Access page");
															Report.TestPoint(testContext.getName(), "Validate Pending acceptance is displayed under secondary account access section", "True", UI.WaitForObject(oR_MyAccountAccess.txtPendingAcceptance, 1).toString(), true);
														} else {
															Report.TestPoint(testContext.getName(),"Validate naviagted to My Account Access page", "True","False",true);
														}
													} else {
														Report.TestPoint(testContext.getName(),"Validate check status link exist", "True","False",true);
													}
												} else {
													Report.TestPoint(testContext.getName(),"Validate navigated to Account Overview page", "True","False",true);
												}
											} else {
												Report.TestPoint(testContext.getName(),"Validate Overview link exist in secondary navigation ", "True","False",true);
											}
										} else {
											Report.TestPoint(testContext.getName(),"Validate navigated to My Account Access page ", "True","False",true);
										}							
										
									} else {
										Report.TestPoint(testContext.getName(),"Validate Continue button exist on Request Access - Confirmation page ", "True","False",true);
									}
								} else {
									Report.TestPoint(testContext.getName(),"Validate navigated to Request Access - Confirmation page", "True","False",true);
								}
																
							} else {
								Report.TestPoint(testContext.getName(),"Validate Next button exist", "True","False",true);
							}
						} else {
							Report.TestPoint(testContext.getName(),"Validate navigated to Verify Account Information page", "True","False",true);
						}
					} else {
						Report.TestPoint(testContext.getName(),"Validate Next button on Link an Account - Select Account Type page", "True","False",true);
					}
				} else {
					Report.TestPoint(testContext.getName(),"Validate navigated to link an account page", "True","False",true);
				}
			} else {
				Report.TestPoint(testContext.getName(),"Validate Link my accounts button is displayed", "True","False",true);
			}

		} catch (Exception e) 
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}
	}

	/**************************************************************
	 * Function Name -  VerifyUverseBillingNotifications()
	 * Description- This function validates the Billing notifications under Profile section
	 * Date created -25th Mar 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
		public static void VerifyUverseBillingNotifications(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
		{
			try
			{	
				WebDriver lDriver = UI.getDriver(testContext.getName()); 
				OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
				OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
				//Navigate to Profile page
				Report.OperationPoint(testContext.getName(), "Navigate to Profile page");
				Boolean bProfile = UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify profile page is displayed", "true", String.valueOf(bProfile), true);
				oR_AccountOverview.lnkProfileSecondaryNav.click();
				//Verify edit Billing information
				Boolean bEdit = UI.WaitForObject(lDriver.findElement(By.xpath("//a[@title='Edit Bill Notify']")), UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify Edit Billing information link is displayed", "true", String.valueOf(bEdit), true);
				if(bEdit.equals(true))
				{
					lDriver.findElement(By.xpath("//a[@title='Edit Bill Notify']")).click();
				}
				//Navigate to Edit Billing information page
				Boolean bEditInfo = UI.WaitForObject(lDriver.findElement(By.xpath("//h2[contains(text(),'Edit Bill Notifications')]")), UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify Edit Billing information page is displayed", "true", String.valueOf(bEditInfo), true);
				if(bEditInfo.equals(true))
				{
					//Verify Save button is always enabled
					Boolean bSave = UI.WaitForObject(lDriver.findElement(By.xpath("//a[@name='saveBlue']")), UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Verify Save button is always enabled", "true", String.valueOf(bSave), true);
					//Verify Bill Ready Notification
					Boolean bBillReady = UI.WaitForObject(lDriver.findElement(By.xpath("//h3[contains(text(),'Bill Ready and Payment Received Notifications')]")), UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Verify Bill Ready and Payment Received Notifications is displayed", "true", String.valueOf(bBillReady), true);
					String sStatus = lDriver.findElement(By.xpath("//label[@id='billReadyToggleLabel']")).getAttribute("class");
					if(sStatus.contains(" checked"))
					{	
						Report.ValidationPoint(testContext.getName(), "Bill Ready and Payment Received Notifications is ON", "true", "true", true);
					}
					if(sStatus.contains("UNchecked"))
					{
						Report.ValidationPoint(testContext.getName(), "Bill Ready and Payment Received Notifications is OFF", "true", "true", true);
					}
				//Verify Bill Threshold Notification 
					Boolean bThreshhold = UI.WaitForObject(lDriver.findElement(By.xpath("//h3[contains(text(),'Bill Threshold Notification')]")), UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Verify Bill Threshold Notification is displayed", "true", String.valueOf(bThreshhold), true);
					sStatus = lDriver.findElement(By.xpath("//label[@id='billThresholdToggleLabel']")).getAttribute("class");
					if(sStatus.contains(" checked"))
					{	
						Report.ValidationPoint(testContext.getName(), "Bill Threshold Notification is ON", "true", "true", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Bill Threshold Notification is OFF", "true", "true", true);
						lDriver.findElement(By.xpath("//label[@id='billThresholdToggleLabel']")).click();
						lDriver.findElement(By.xpath("//input[@id='billLimitAmount']")).sendKeys("100");
					}
					//Verify Pay Bill Reminder Notification 
					Boolean bBillReminder = UI.WaitForObject(lDriver.findElement(By.xpath("//h3[contains(text(),'Pay Bill Reminder Notification')]")), UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Verify Bill Threshold Notification is displayed", "true", String.valueOf(bBillReminder), true);
					sStatus = lDriver.findElement(By.xpath("//label[@id='paybillToggleLabel']")).getAttribute("class");
					if(sStatus.contains(" checked"))
					{	
						Report.ValidationPoint(testContext.getName(), "Bill Threshold Notification is ON", "true", "true", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Bill Threshold Notification is OFF", "true","true", true);
						lDriver.findElement(By.xpath("//label[@id='paybillToggleLabel']")).click();
						Thread.sleep(10000);
						lDriver.findElement(By.xpath("//input[@id='payBillEmail']")).click();
						Report.ValidationPoint(testContext.getName(), "Validate Email Notification checkbox checked", "true","true", true);
					} 
				} 
				lDriver.findElement(By.xpath("//a[@name='saveBlue']")).click();
				Thread.sleep(10000);
				//Verify Confirmation page
				Boolean bConfirmation = UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'Profile Change Confirmation')]")), UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Verify Profile Change Confirmation page is displayed", "true", String.valueOf(bConfirmation), true);
				if(bConfirmation.equals(true))
				{
					Boolean bprint = UI.WaitForObject(lDriver.findElement(By.xpath("//a[@linkname='Print this page']")), UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Verify Print this page link is displayed", "true", String.valueOf(bprint), true);
					
					Boolean bBillThresh = UI.WaitForObject(lDriver.findElement(By.xpath("//h2[contains(text(),'Bill Threshold Notification')]")), UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Verify Bill Threshold Notification is displayed", "true", String.valueOf(bBillThresh), true);
					
					Boolean bThreshAmt = UI.WaitForObject(lDriver.findElement(By.xpath("//p[strong='Threshold Amount']")), UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Verify Threshold Amount is displayed", "true", String.valueOf(bThreshAmt), true);
					
					Boolean bPayBill = UI.WaitForObject(lDriver.findElement(By.xpath("//h2[contains(text(),'Pay Bill Reminder Notifications')]")), UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Verify Pay Bill Reminder Notification is displayed", "true", String.valueOf(bPayBill), true);
					

					Boolean bReturn = UI.WaitForObject(lDriver.findElement(By.xpath("//a[@linkname='Return to Account Information']")), UI.iObjTimeOut);
					Report.ValidationPoint(testContext.getName(), "Verify Return to Account Information link is displayed", "true", String.valueOf(bReturn), true);
					if(bReturn.equals(true))
					{
						lDriver.findElement(By.xpath("//a[@linkname='Return to Account Information']")).click();
						Boolean bAccountInfo = UI.WaitForObject(oR_Profile.txtAccInfo, UI.iObjTimeOut);
						Report.ValidationPoint(testContext.getName(), "Verify Account Information page is displayed", "true", String.valueOf(bAccountInfo), true);
						
					}
				}
				
			}	
			catch(Exception e)
			{
				  
				  Report.TestPoint(testContext.getName(),
							"Some error has occured", "True",
							e.getMessage(), true);			}
		}


		
		/**************************************************************
		 * Function Name -  LinkAnAccountWithAccountTypeHomePhoneInternetDigitalTV
		 * Description- This function Links an account of the type 'Home Phone, Internet & Digital TV' and verifies confirmation of
		 * 				account registration and navigation to profile page after registration.
		 * Parameters - 
		 * Date created -30th March
		 * Developed by - Sneha Pansare
		 * Last Modified By - Clint (Changed button oR to oR_LinkAnAccount.lnkLinkMyAccounts)
		 * Last Modified Date - 5th Sept 2015
		 * @throws AWTException 
		 * @throws IOException 
		 * @throws HeadlessException 
	 * @throws ParseException 
		 ***************************************************************/
		//PRF09198
		
		public static void LinkAnAccountWithAccountTypeHomePhoneInternetDigitalTV(final ITestContext testContext) throws HeadlessException, IOException, AWTException, InterruptedException
		  {
		  try
		  {
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_LinkAnAccount oR_LinkAnAccount = PageFactory.initElements(lDriver, OR_LinkAnAccount.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			String sBAN = IO.GetParamVal(sTestParams, "BAN");
			String sZipCode = IO.GetParamVal(sTestParams, "Zip_Code");
			String sUserName = IO.GetParamVal(sTestParams, "User_Name");
			String sPassword = IO.GetParamVal(sTestParams, "Password");
			//Click on 'Link my accounts' button
			//if(UI.WaitForObject(oR_LinkAnAccount.btnLinkMyAccounts, 90))
			if(UI.WaitForObject(oR_LinkAnAccount.lnkLinkMyAccounts, UI.iObjTimeOut))
			{
				//System.out.println("'Link my accounts' button clicked");
				Report.ValidationPoint(testContext.getName(), "Click on 'Link my accounts' button", "Clicked","Clicked", true);
				//oR_LinkAnAccount.btnLinkMyAccounts.click();
				oR_LinkAnAccount.lnkLinkMyAccounts.click();
				
				//Verify navigation to page 'Link an Account - Select Account Type'
				if(UI.WaitForObject(oR_LinkAnAccount.txtSelectAccountType, 90))
				{
					//System.out.println("Successfully navigated to 'Link an Account - Select Account Type' page");
					Report.ValidationPoint(testContext.getName(), "Verify navigation to page 'Link an Account - Select Account Type'", "Successfully navigated","Successfully navigated", true);
					
					//Select Account type from 'Account Type' dropdown
					if(UI.SelectOptionFromDropDown(oR_LinkAnAccount.lstSelAcc, "Home Phone, Internet & Digital TV"))
					{
						//Enter BAN
						if(UI.WaitForObject(oR_LinkAnAccount.edtBillingAccNum, 10))
						{
							oR_LinkAnAccount.edtBillingAccNum.sendKeys(sBAN);
						}
						else
						{
							//System.out.println("Billing Account number input box NOT displayed");//add testpoint here
							Report.TestPoint(testContext.getName(), "Enter Billing Account number", "Entered","Input box NOT displayed", true);
						}
						
						//enter zip code
						if(UI.WaitForObject(oR_LinkAnAccount.edtZipCode, 2))
						{
							oR_LinkAnAccount.edtZipCode.sendKeys(sZipCode);
						}
						else
						{
							//System.out.println("Zip code input box NOT displayed");//add testpoint here
							Report.TestPoint(testContext.getName(), "Enter Zip code", "Entered","Input box NOT displayed", true);
						}
						
						//System.out.println("'Billing Account number' and 'Zip code' Entered");
						Report.ValidationPoint(testContext.getName(), "Enter 'Billing Account number' and 'Zip code'", "Entered","Entered", true);
					}
					else
					{
						//System.out.println("Failed to select option from 'Select Account' weblist");//add testpoint here
						Report.TestPoint(testContext.getName(), "Select option from 'Select Account' weblist", "Selected","Failed to select", true);
					}
					
					if(UI.WaitForObject(oR_LinkAnAccount.btnNext, 5))
					{
						//click on Next
						//System.out.println("Clicked on Next button of 'Link an Account - Select Account Type' page");
						Report.ValidationPoint(testContext.getName(), "Click on Next button of 'Link an Account - Select Account Type' page", "Clicked","Clicked", true);
						oR_LinkAnAccount.btnNext.click();
						
						//Verify Navigation to 'Verify Account Information' page
						if(UI.WaitForObject(oR_LinkAnAccount.txtVerifyAccountInformation2, 90))
						{
							//System.out.println("Successfully navigated to 'Verify Account Information' page");
							Report.ValidationPoint(testContext.getName(), "Verify Navigation to 'Verify Account Information' page", "Successfully navigated","Successfully navigated", true);
							//Set Username
							if(UI.WaitForObject(oR_LinkAnAccount.edtUserName, 20))
							{
								oR_LinkAnAccount.edtUserName.sendKeys(sUserName);
							}
							else
							{
								//System.out.println("User name input box NOT displayed");//add testpoint here
								Report.TestPoint(testContext.getName(), "Enter User name", "Entered","Input box NOT displayed", true);
							}
							
							//Set Password
							if(UI.WaitForObject(oR_LinkAnAccount.edtPassword, 20))
							{
								oR_LinkAnAccount.edtPassword.sendKeys(sPassword);
							}
							else
							{
								//System.out.println("Password input box NOT displayed");//add testpoint here
								Report.TestPoint(testContext.getName(), "Enter Password", "Entered","Input box NOT displayed", true);
							}
							
							//System.out.println("User name And password set Successfully");
							Report.ValidationPoint(testContext.getName(), "Enter User name And password", "Entered","Entered", true);
							
							//Click on Next button
							if(UI.WaitForObject(oR_LinkAnAccount.btnContinue, 10))
							{
								//System.out.println("Clicked on Next button of 'Verify Account Information' page");
								Report.ValidationPoint(testContext.getName(), "Click on Next button of 'Verify Account Information' page", "Clicked","Clicked", true);
								
								oR_LinkAnAccount.btnContinue.click();
								
								//Click on Next button of second 'Verify Account Information' page
								if(UI.WaitForObject(oR_LinkAnAccount.btnContinue, 40))
								{
									//System.out.println("Clicked on Next button of second 'Verify Account Information' page");
									Report.ValidationPoint(testContext.getName(), "Click on Next button of second 'Verify Account Information' page", "Clicked","Clicked", true);
									oR_LinkAnAccount.btnContinue.click();
								}
								
								//Verify Navigation to 'Agree to Account Linking' page
								
								if(UI.WaitForObject(oR_LinkAnAccount.txtAgreeAccLink, 90))
								{
									//System.out.println("Successfully navigated to 'Agree to Account Linking' page");
									Report.ValidationPoint(testContext.getName(), "Verify Navigation to 'Agree to Account Linking' page", "Successfully navigated","Successfully navigated", true);
									
									//Click on submit button
									if(UI.WaitForObject(oR_LinkAnAccount.btnContinue, 5))
									{
										//System.out.println("Clicked on Continue button of 'Agree to Account Linking' page");
										Report.ValidationPoint(testContext.getName(), "Click on Continue button of 'Agree to Account Linking' page", "Clicked","Clicked", true);
										oR_LinkAnAccount.btnContinue.click();
										
										//Verify Navigation to 'Confirmation' page
										if(UI.WaitForObject(oR_LinkAnAccount.txtConfirmationTitle2, 90))
										{
											//System.out.println("Successfully navigated to 'Confirmation' page");
											Report.ValidationPoint(testContext.getName(), "Verify Navigation to 'Confirmation' page", "Successfully navigated","Successfully navigated", true);
											
											try
											{
												WebElement eConfirmationMessage = lDriver.findElement(By.xpath("//*[contains(text(),'Your Account Registration is Complete')]"));
												if(eConfirmationMessage.isDisplayed())
												{
													//System.out.println("Account registration has been completed successfully");
													Report.ValidationPoint(testContext.getName(), "Account registration Should be completed successfully", "Completed successfully","Completed successfully", true);
												}
												else
												{
													//System.out.println("Account registration has NOT been completed");//add testpoint here
													Report.ValidationPoint(testContext.getName(), "Account registration Should be completed successfully", "Completed successfully","NOT Completed", true);
												}
											}
											catch(Exception e)
											{
												//System.out.println("Account registration has NOT been completed");//add testpoint here
												Report.ValidationPoint(testContext.getName(), "Account registration Should be completed successfully", "Completed successfully","NOT Completed", true);
											}
											
											//Select 'Go to myAT&T to manage my accounts' radio button
											Thread.sleep(10000);
											if(UI.WaitForObject(oR_LinkAnAccount.rdoGoToAttToManageAcc, 20))
											{
												oR_LinkAnAccount.rdoGoToAttToManageAcc.click();
												
											}
											else
											{
												//System.out.println("'Go to myAT&T to manage my accounts' radio button NOT displayed");
												Report.ValidationPoint(testContext.getName(), "Select 'Go to myAT&T to manage my accounts' radio button", "Selected","Radio button NOT displayed", true);
											}
											
											//Click on Continue button
											if(UI.WaitForObject(oR_LinkAnAccount.btnContinue2, 10))
											{
												//System.out.println("Clicked on Continue button of 'Confirmation' page");
												Report.ValidationPoint(testContext.getName(), "Click on Continue button of 'Confirmation' page", "Clicked","Clicked", true);
												
												oR_LinkAnAccount.btnContinue2.click();
												
												//Verify Navigation to 'My Account Access' page
												try
												{
													WebElement eMyAccountAccess = lDriver.findElement(By.xpath("//*[contains(@class,'title')]//*[contains(text(),'My Account Access')]"));
													
													if(eMyAccountAccess.isDisplayed())
													{
														//System.out.println("Successfully navigated to 'My Account Access' page");
														Report.ValidationPoint(testContext.getName(), "Verify Navigation to 'My Account Access' page", "Successfully navigated","Successfully navigated", true);
													}
													else
													{
														//System.out.println("Failed to navigate to 'My Account Access' page");
														Report.ValidationPoint(testContext.getName(), "Verify Navigation to 'My Account Access' page", "Successfully navigated","Failed to navigate", true);
													}
												}
												catch(Exception e)
												{
													//System.out.println("Failed to navigate to 'My Account Access' page");
													Report.ValidationPoint(testContext.getName(), "Verify Navigation to 'My Account Access' page", "Successfully navigated","Failed to navigate", true);
												}
											}
											else
											{
												//System.out.println("Continue button NOT displayed on 'Confirmation' page");
												Report.ValidationPoint(testContext.getName(), "Click on Continue button of 'Confirmation' page", "Clicked","Button NOT displayed", true);
											}
											
											
											Thread.sleep(25000);
										}
										else
										{
											//System.out.println("Failed to navigate to 'Confirmation' page");
											Report.ValidationPoint(testContext.getName(), "Verify Navigation to 'Confirmation' page", "Successfully navigated","Failed to navigate", true);
										}
									}
									else
									{
										//System.out.println("Continue button NOT displayed on 'Agree to Account Linking' page");
										Report.ValidationPoint(testContext.getName(), "Click on Continue button of 'Agree to Account Linking' page", "Clicked","Button NOT displayed", true);
									}
								}
								else
								{
									//System.out.println("Failed to navigate to 'Agree to Account Linking' page");
									Report.TestPoint(testContext.getName(), "Verify Navigation to 'Agree to Account Linking' page", "Successfully navigated","Failed to navigate", true);
								}
								
							}
							else
							{
								//System.out.println("'Next' button NOT displayed on 'Verify Account Information' page");//add testpoint here
								Report.TestPoint(testContext.getName(), "Click on Next button of 'Verify Account Information' page", "Clicked","Button NOT displayed", true);
							}
						}
						else
						{
							//System.out.println("Failed to navigate to 'Verify Account Information' page");
							Report.TestPoint(testContext.getName(), "Verify Navigation to 'Verify Account Information' page", "Successfully navigated","Failed to navigate", true);
						}
						
					}
					else
					{
						//System.out.println("'Next' button NOT displayed on 'Link an Account - Select Account Type' page");//add testpoint here
						Report.TestPoint(testContext.getName(), "click on Next button of 'Link an Account - Select Account Type' page", "Clicked","Button NOT displayed", true);
					}
					
					
				}
				else
				{
					//System.out.println("Failed to navigate to 'Link an Account - Select Account Type' page");
					Report.ValidationPoint(testContext.getName(), "Verify navigation to page 'Link an Account - Select Account Type'", "Successfully navigated","Navigation Failed", true);
				}
			}
			else
			{
				//System.out.println("'Link my accounts' button not displayed after login");
				Report.ValidationPoint(testContext.getName(), "Click on 'Link my accounts' button", "Clicked","Button NOT displayed", true);
			}
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true); 
		}
			
			
	}
		
		
		/**************************************************************
			* Function Name - VerifyBadEmailEntry()
			* Description - This function is to validate that user is not allowed to enter the bad email address as his/her contact email address while registering the account and linking it to SLID.
			* Parameters - CTN, BAN, Zip_Code, Online_Registration_Code
			* Date created - 10th Apr 2015
			* Developed by - Clint John
			* Last Modified By - 
			* Last Modified Date - 
			***************************************************************/
			//PRF08342
		public static void VerifyBadEmailEntry(final ITestContext testContext) throws HeadlessException, IOException, AWTException
		{
			try
			{
				WebDriver lDriver = UI.getDriver(testContext.getName()); 
				OR_AccountRegistration oR_AccountRegistration = PageFactory.initElements(lDriver, OR_AccountRegistration.class);
				OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
				Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

				String sCTN = IO.GetParamVal(sTestParams, "CTN");
				String sBAN = IO.GetParamVal(sTestParams, "BAN");
				String sZipCode = IO.GetParamVal(sTestParams, "Zip_Code");
				String sOnlineRegistrationCode = IO.GetParamVal(sTestParams, "Online_Registration_Code");
				String sSLID = IO.GetParamVal(sTestParams, "Preferred_Access_ID");

				
				boolean bRegister = UI.WaitForObject(oR_Login.lnkRegisterToday, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Validate that 'Register Today!' link is displayed in Login page", "true", String.valueOf(bRegister), true);
				oR_Login.lnkRegisterToday.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Register Today!' link");
				
				//Verify Account selection page is displayed
				boolean bSelectAccount = UI.WaitForObject(oR_AccountRegistration.txtSelectAccountHeading, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Validate that Account selection page is displayed", "true", String.valueOf(bSelectAccount), true);
				
				//Verify that select account drop-down is displayed
				//boolean bSelectAccDropdown = UI.WaitForObject(oR_AccountRegistration.txtSelectAccDropdownTab, UI.iObjTimeOut);
				//Report.TestPoint(testContext.getName(), "Validate that Drop-down for Select Account is displayed", "true", String.valueOf(bSelectAccDropdown), true);
				//Select Wireless Account from drop-down
				new Actions(lDriver).moveToElement(oR_AccountRegistration.txtWireless).click().perform();
				Report.OperationPoint(testContext.getName(), "Operational - Selected option for 'Wireless' from Account dropdown");
				
				//Enter WirelessNumber (CTN) and ZipCode
				boolean bCTN = UI.WaitForObject(oR_AccountRegistration.edtWirelessNumber, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate that edit box to insert Wireless number is displayed", "true", String.valueOf(bCTN), true);
				oR_AccountRegistration.edtWirelessNumber.sendKeys(sCTN);
				Report.OperationPoint(testContext.getName(), "Operational - entered Wireless Number(CTN)");

				boolean bZipCode = UI.WaitForObject(oR_AccountRegistration.edtZipCode, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate that edit box to enter ZipCode is displayed", "true", String.valueOf(bZipCode), true);
				oR_AccountRegistration.edtZipCode.sendKeys(sZipCode);
				Report.OperationPoint(testContext.getName(), "Operational - entered ZipCode");
				
				oR_AccountRegistration.btnNext.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on Next button");
				
				if(UI.CheckExist(oR_AccountRegistration.txtATTAccessIDRequest).equalsIgnoreCase("True"))
				{
						boolean bATTAccessID = UI.WaitForObject(oR_AccountRegistration.txtATTAccessIDRequest, UI.iObjTimeOut);
						Report.TestPoint(testContext.getName(), "Validate that 'Your AT&T Access ID request' page is displyed", "true", String.valueOf(bATTAccessID), true);
						
						//Select required radio button options
						lDriver.findElement(By.xpath("//input[@id='requestSecondaryAccess_true']")).click();
						Report.OperationPoint(testContext.getName(), "Operational - Selected option - My name is NOT on the bill, but I'd like to request access to this account");
						
						//boolean bGain= UI.WaitForObject(lDriver.findElement(By.id("gainAccessRadios_reqaccess")), 20, lDriver);
						if(lDriver.findElement(By.id("gainAccessRadios_reqaccess")).isEnabled())
						{
							lDriver.findElement(By.id("gainAccessRadios_reqaccess")).click();
							oR_AccountRegistration.btnNext.click();
							Report.OperationPoint(testContext.getName(), "Operational - Clicked on Next button");
						}
						/*//boolean bBAN = UI.WaitForObject(lDriver.findElement(By.id("BAN")), UI.iObjTimeOut);
						if(lDriver.findElement(By.id("BAN")).isEnabled()){
						Report.ValidationPoint(testContext.getName(), "Validate text box to enter Billing Account Number is displayed", "true", String.valueOf(lDriver.findElement(By.id("BAN")).isEnabled()), true);
						lDriver.findElement(By.id("BAN")).sendKeys(sBAN);
						Report.OperationPoint(testContext.getName(), "Operational - Billing Account Number is entered inside the text box");
			
						oR_AccountRegistration.btnNext.click();
						Report.OperationPoint(testContext.getName(), "Operational - Clicked on Next button");}
				*/}
				
				//Select radio button for Online registration code
				boolean bORC = UI.WaitForObject(oR_AccountRegistration.txtOnlineRegistrationCode, UI.iObjTimeOut);
				if(bORC){
				Report.TestPoint(testContext.getName(), "Validate that page to insert Online Registration Code is displayed", "true", String.valueOf(bORC), true);
				oR_AccountRegistration.rdoAvailableORC.click();
				//Report.ValidationPoint(testContext.getName(), "Validate that radiobutton is clicked to enter Online Registration Code", "true", String.valueOf(bORC), true);
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on radio button for 'I have already requested an Online Registration Code and have received it.' ");
				//Enter online registration code
				oR_AccountRegistration.edtEnterORC.sendKeys(sOnlineRegistrationCode);
				Report.OperationPoint(testContext.getName(), "Operational - Online registration Code entered successfully! ORC is"+sOnlineRegistrationCode);
				
				oR_AccountRegistration.btnNext.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on Next button");}
				
				//Validate Create an AT&T Access ID page is displayed and do all the validations in the page

				boolean bCreateAccessID = UI.WaitForObject(oR_AccountRegistration.txtCreateATTAccessID, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Validate that 'Create an AT&T Access ID' page is displayed", "true", String.valueOf(bCreateAccessID), true);
				//Click on radio-button for I need to create an AT&T Access ID 
				oR_AccountRegistration.rdoCreateAccessID.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on radio-button for I need to create an AT&T Access ID ");
				
				//Verify that Preferred access id, Contact email address, Confirm contact email address fields are blank if any value is not present in the data base for contact email address. 
				//If value is present, that value is displayed
				
				if(oR_AccountRegistration.edtPreferredAccessID.getText()==null && oR_AccountRegistration.edtEmailAddress.getText()==null && oR_AccountRegistration.edtConfirmEmailAddress.getText()==null)
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Preferred access id, Contact email address, Confirm contact email address fields are pre-populated if value is present in database for contact email address", "true","true", true);

				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify that Preferred access id, Contact email address, Confirm contact email address fields are NOT pre-populated if value is NOT present in database for contact email address", "true","true", true);
				}
				
				//Enter values inside all edit boxes
				oR_AccountRegistration.edtPreferredAccessID.sendKeys(sSLID);
				Report.OperationPoint(testContext.getName(), "Operational - Value for Preferred AT&T Access ID is entered successfully. ID is: "+sSLID);
				oR_AccountRegistration.edtPassword.sendKeys("test1ng");
				Report.OperationPoint(testContext.getName(), "Operational - Successfully entered value for New password feild");
				oR_AccountRegistration.edtConfirmPassword.sendKeys("test1ng");
				Report.OperationPoint(testContext.getName(), "Operational - Successfully entered value for confirm new password feild");
				oR_AccountRegistration.edtFirstName.clear();
				oR_AccountRegistration.edtFirstName.sendKeys("test");
				Report.OperationPoint(testContext.getName(), "Operational - Successfully entered value for First Name");
				oR_AccountRegistration.edtLastName.clear();
				oR_AccountRegistration.edtLastName.sendKeys("test");
				Report.OperationPoint(testContext.getName(), "Operational - Successfully entered value for Last Name");
				oR_AccountRegistration.edtEmailAddress.sendKeys("dummyemail@directv.com");
				Report.OperationPoint(testContext.getName(), "Operational - Successfully entered value for contact email");
				oR_AccountRegistration.edtConfirmEmailAddress.sendKeys("dummyemail@directv.com");
				Report.OperationPoint(testContext.getName(), "Operational - Successfully entered value for confirm contact email");
				
				//Select security ques and ans
				UI.SelectOptionFromDropDown(oR_AccountRegistration.lstSelectSecurityQues1Options, "What was the make and model of your first car?");
				Report.OperationPoint(testContext.getName(), "Operational - option 'What was the make and model of your first car?' is selected from Set-1 security question");
				oR_AccountRegistration.edtSecurityAns1.sendKeys("test");
				Report.OperationPoint(testContext.getName(), "Operational - Value entered in security answer feild");
				
				UI.SelectOptionFromDropDown(oR_AccountRegistration.lstSelectSecurityQues2Options, "What was the last name of your childhood best friend?");
				Report.OperationPoint(testContext.getName(), "Operational - option 'What was the last name of your childhood best friend?' is selected from Set-2 security question");
				oR_AccountRegistration.edtSecurityAns2.sendKeys("test");
				Report.OperationPoint(testContext.getName(), "Operational - Value entered in security answer feild");
		
					
				//Validate whether Alert message is displayed
				oR_AccountRegistration.chkTermsAndConditions.click();
				Report.OperationPoint(testContext.getName(), "Operational - Checkbox for Terms & Conditions is checked");
				oR_AccountRegistration.btnNext.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on Next button");
				
				boolean bAlert = UI.WaitForObject(oR_AccountRegistration.txtEmailAlert, UI.iObjTimeOut);
				Report.TestPoint(testContext.getName(), "Validate that Alert message for invalid Email is displayed on top of the page", "true", String.valueOf(bAlert), true);


			}
			catch (Exception e) 
			{
				e.printStackTrace();
				Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

			}
				
		}

	
	/**************************************************************
	 * Function Name -  verifyProfileDetailsAndSequenceForAllAccountsLinkedToSLID
	 * Description- This function retrieves all account types from 'My linked accounts' dropdown of dashboard
	 * 				Navigates to Go to my profile CTA under this dropdown
	 * 				after navigating to profile details page checkes for all CTA's , checks whether all linked accounts are displaying
	 * 				Checks whether Accounts of same type are displaying in alphabetical order(checks for account numbers are in increasing order)
	 * 				checks format of details for 'Home phone,Internet & Digital TV' account type
	 * 				Navigates to all CTA's and verifies whenther they are navigating to proper page
	 * Parameters - 
	 * Date created -17th March
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
 * @throws ParseException 
	 ***************************************************************/
	//PRF08602

	public static void verifyProfileDetailsAndSequenceForAllAccountsLinkedToSLID(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sName = IO.GetParamVal(sTestParams, "Name");
		String sEmailID = IO.GetParamVal(sTestParams, "EmailID");
		List<WebElement> allLinkedAccounts=null;
		String[] arrAccountName = new String[1] ; 
		String[] arrNumberOfAccountsOfSameType = new String[1] ; 
		Integer iTotalNumberOfAccountsOfAllTypes=0;
		
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountRegistration oR_AccountRegistration = PageFactory.initElements(lDriver, OR_AccountRegistration.class);
			OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_ATT_AccessID_Profile oR_ATT_AccessID_Profile = PageFactory.initElements(lDriver, OR_ATT_AccessID_Profile.class);
			
			
			//Verify My Linked Accounts CTA which is present on top of dashboard
			Boolean bLinkMyLinkedAccounts=UI.WaitForObject(oR_AccountOverview.lnkMyLinkedAccounts, 40);
			Report.TestPoint(testContext.getName(), "Verify My Linked Accounts CTA", "true", String.valueOf(bLinkMyLinkedAccounts), true);
			
			Actions action =   new Actions(lDriver);
			action.moveToElement(oR_AccountOverview.lnkMyLinkedAccounts).build().perform();
			
			//Extract all linked accounts from dropdown of  My Linked Accounts CTA
			
			
			try
			{
				//Xpath which will give all linked accounts list from link my account dropdown
				allLinkedAccounts = lDriver.findElements(By.xpath("//*[contains(@class,'mylinked-drop-down')]//*[contains(@class,'menuitem')]//*[contains(@href,'void')][contains(@style,'color:#000')]"));
				arrAccountName= new String[allLinkedAccounts.size()];
				arrNumberOfAccountsOfSameType= new String[allLinkedAccounts.size()];
				
				for(int count= 0 ;count <allLinkedAccounts.size() ; count++)
				{
					String sAccountNameText = allLinkedAccounts.get(count).getText();
					System.out.println("sAccountNameText : "+sAccountNameText);
					if(sAccountNameText.contains("("))
					{
						String[] arrSplitAccountNameAndNumber = sAccountNameText.split("\\(");
						
						arrAccountName[count]=arrSplitAccountNameAndNumber[0];
						String arrNumberOfAccountsOfSameTypeWithBracket = arrSplitAccountNameAndNumber[1];
						String[] arrNumberOfAccountsWithoutBracket=arrNumberOfAccountsOfSameTypeWithBracket.split("\\)");
						arrNumberOfAccountsOfSameType[count] = arrNumberOfAccountsWithoutBracket[0];
						
						iTotalNumberOfAccountsOfAllTypes= iTotalNumberOfAccountsOfAllTypes + Integer.parseInt(arrNumberOfAccountsOfSameType[count]);
					}
					else
					{
						arrAccountName[count] = sAccountNameText ;
						arrNumberOfAccountsOfSameType[count] = "1" ;
						iTotalNumberOfAccountsOfAllTypes= iTotalNumberOfAccountsOfAllTypes+1;
					}
					
					//System.out.println("Account name : "+arrAccountName[count]);
					//System.out.println("Number of Accounts : "+arrNumberOfAccountsOfSameType[count]);
				}
				
				Report.TestPoint(testContext.getName(), "Validate all accounts linked to Slid should display on Overview page", "Total "+iTotalNumberOfAccountsOfAllTypes+" accounts Displayed", "Total "+iTotalNumberOfAccountsOfAllTypes+" accounts Displayed", true);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Validate all accounts linked to Slid should display on Overview page", "All accounts Displayed", "NOT Displayed", true);
			}
			
			
			
			//Click on Go To My Profile CTA under My Linked Accounts dropdown
			Boolean bLinkGoToMyProfile=UI.WaitForObject(oR_AccountOverview.lnkGoToMyProfile, 40);
			action.moveToElement(oR_AccountOverview.lnkGoToMyProfile).build().perform();
			Report.TestPoint(testContext.getName(), "Click on Go To My Profile CTA under My Linked Accounts dropdown", "true", String.valueOf(bLinkGoToMyProfile), true);
			oR_AccountOverview.lnkGoToMyProfile.click();
			
			Thread.sleep(40000);
			
			//Verify navigation to AT&T access id profile page
			Boolean bTxtMyATTAccessIDProfile=UI.WaitForObject(oR_ATT_AccessID_Profile.txtMyATTAccessIDProfile, 40);
			Report.TestPoint(testContext.getName(), "Verify navigation to AT&T access id profile page", "true", String.valueOf(bTxtMyATTAccessIDProfile), true);
			
			//Verify AT&T Access ID Information Section
			
			//Verify AT&T Access ID field
			Boolean bTxtATTAccessID=UI.WaitForObject(oR_ATT_AccessID_Profile.txtATTAccessID, 40);
			Report.TestPoint(testContext.getName(), "Verify AT&T Access ID field is displayed", "true", String.valueOf(bTxtATTAccessID), true);
			
			//Verify Name field
			Boolean bTxtName=UI.WaitForObject(oR_ATT_AccessID_Profile.txtName, 40);
			Report.TestPoint(testContext.getName(), "Verify Name field is displayed", "true", String.valueOf(bTxtName), true);
			
			try
			{
				WebElement eName = lDriver.findElement(By.xpath("//*[contains(text(),'Name:')]/parent::*/parent::*/child::*/child::*[contains(text(),'"+sName+"')]"));
				if(eName.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(), "Verify Name is displayed in front of Name field", "Displayed", "Displayed", true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Name is displayed in front of Name field", "Displayed", "NOT Displayed", true);
			}
			
			//Verify  Contact E-mail Address field
			Boolean bTxtEmailID=UI.WaitForObject(oR_ATT_AccessID_Profile.txtEmailID, 40);
			Report.TestPoint(testContext.getName(), "Verify Contact E-mail Address field is displayed", "true", String.valueOf(bTxtEmailID), true);
			
			try
			{
				WebElement eEmailId = lDriver.findElement(By.xpath("//*[contains(text(),'Contact Email:')]/parent::*/parent::*/child::*/child::*[contains(text(),'"+sEmailID+"')]"));
				if(eEmailId.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(), "Verify Contact E-mail is displayed in front of Contact E-mail Address field", "Displayed", "Displayed", true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Contact E-mail is displayed in front of Contact E-mail Address field", "Displayed", "NOT Displayed", true);
			}
			
			//Verify AT&T Access ID profile link
			Boolean bLinkATTAccessIDProfile=UI.WaitForObject(oR_ATT_AccessID_Profile.lnkATTAccessIDProfile, 40);
			Report.TestPoint(testContext.getName(), "Verify AT&T Access ID profile link is displayed", "true", String.valueOf(bLinkATTAccessIDProfile), true);
			
			//Verify AT&T Access ID Account Access link
			Boolean bLinkManageAccountAccess=UI.WaitForObject(oR_ATT_AccessID_Profile.lnkManageAccountAccess, 40);
			Report.TestPoint(testContext.getName(), "Verify AT&T Access ID Account Access link is displayed", "true", String.valueOf(bLinkManageAccountAccess), true);
			
			//Verify Change AT&T Access ID link
			Boolean bLinkChangeATTaccessID=UI.WaitForObject(oR_ATT_AccessID_Profile.lnkChangeATTaccessID, 40);
			Report.TestPoint(testContext.getName(), "Verify Change AT&T Access ID link is displayed", "true", String.valueOf(bLinkChangeATTaccessID), true);
			
			//Verify Help Info Icon (?)
			Boolean bImgHelp=UI.WaitForObject(oR_ATT_AccessID_Profile.imgHelp, 40);
			Report.TestPoint(testContext.getName(), "Verify Help Info Icon (?) is displayed", "true", String.valueOf(bImgHelp), true);
			
			//**********Verification of AT&T Access ID Information Section Ends here***********
			
			//Verify My Linked Accounts section
			
			
			//Verify My Linked Accounts (section header)
			Boolean bTxtMyLinkedAccountsTitle=UI.WaitForObject(oR_ATT_AccessID_Profile.txtMyLinkedAccountsTitle, 40);
			Report.ValidationPoint(testContext.getName(), "Verify My Linked Accounts (section header) is displayed", "true", String.valueOf(bTxtMyLinkedAccountsTitle), true);
			
			//Verify Link Another Account link
			Boolean bLinkAnotherAccount=UI.WaitForObject(oR_ATT_AccessID_Profile.lnkLinkAnotherAccount, 40);
			Report.ValidationPoint(testContext.getName(), "Verify Link Another Account link is displayed", "true", String.valueOf(bLinkAnotherAccount), true);
			
			//Verify statement stating exactly how many accounts are currently linked to the AT&T Access ID
			try
			{
				WebElement eNumberOfAccountsLinkedStatement = lDriver.findElement(By.xpath("//*[contains(text(),'account(s) linked to your AT&T Access ID')]"));
				String sNumberOfAccountsLinkedStatementInnerText = eNumberOfAccountsLinkedStatement.getText();
				
				if(sNumberOfAccountsLinkedStatementInnerText.contains("You currently have") && sNumberOfAccountsLinkedStatementInnerText.contains(" "+iTotalNumberOfAccountsOfAllTypes))
				{
					Report.ValidationPoint(testContext.getName(), "Verify statement stating exactly how many accounts are currently linked to the AT&T Access ID is displayed", "Displayed", "Displayed", true);
				}
				
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Verify statement stating exactly how many accounts are currently linked to the AT&T Access ID is displayed", "Displayed", "NOT Displayed", true);
			}
			
			//Add loop to iterate through all accounts that are linked to slid
			for(int count = 0; count<allLinkedAccounts.size() ; count++)
			{
				String[] arrAccountNameFirstWord=new String[2];
				String sAccountName=null;
				List<WebElement> lstAccountNumber=null;
				
				//here we are taking only first word of account name which we extracted from dashboard my linked accounts dropdown
				
				if(arrAccountName[count].contains(" "))
				{
					arrAccountNameFirstWord= arrAccountName[count].split(" ");
					sAccountName = arrAccountNameFirstWord[0];
				}
				else
				{
					sAccountName = arrAccountName[count];
				}
				try
				{
					//check if current account details are expanded and if they are collapsed then expand it
					WebElement eExpandCollapseImage = lDriver.findElement(By.xpath("//*[contains(text(),'"+sAccountName+"')]/parent::*/child::a[contains(@class,'Img')]"));

					String sClass = eExpandCollapseImage.getAttribute("class");
					
					if(sClass.contains("expand"))
					{
						//it means account details are collapsed . so click on them to expand it
						eExpandCollapseImage.click();
						
					}
					
					Report.ValidationPoint(testContext.getName(), "Linked account '"+arrAccountName[count]+"' is Displayed","Displayed", "Displayed", true);
					
					//Validate that if there are multiple accounts attached that are of the same account type then
					//they are listed in alphabetical order by the account number
					
					try
					{
						lstAccountNumber = lDriver.findElements(By.xpath("//*[contains(text(),'"+sAccountName+"')]/parent::*/parent::*/parent::*/child::*[contains(@id,'toggle')]//*[contains(text(),'Account Number:')]/parent::*/parent::*"));
						long iPreviousAccountNumber = 0;
						int iAlphabeticalCount = 0;
						int indexNumberFromWhereAccountNumberStarts=0;
						
						for(int accNoCount = 0 ; accNoCount<lstAccountNumber.size() ; accNoCount++)
						{
							String sAccountNumberText = lstAccountNumber.get(accNoCount).getText();
							for(int cnt=0; cnt<sAccountNumberText.length();cnt++ )
							{
								
								char ch=sAccountNumberText.charAt(cnt);
								if(Character.isDigit(ch))
								{
									//if character is digit then take index of that digit
									indexNumberFromWhereAccountNumberStarts = cnt ; 
									break;
								}
							}
							
							String sAccountNumber=null;
							//Take substring from location where digits starts. this location is stored in indexNumberFromWhereAccountNumberStarts variable above
							sAccountNumber = sAccountNumberText.substring(indexNumberFromWhereAccountNumberStarts);
							
							//convert extracted account number to long
							Long iAccountNumber = Long.parseLong(sAccountNumber); 
							
							//Check if previous account number is small or not (should be small i.e alphabetical)
							if(iPreviousAccountNumber<iAccountNumber)
							{
								iAlphabeticalCount = iAlphabeticalCount +1 ;
							}
						}
						
						//if count matches then it means all accounts are in alphabetically increasing order
						if(iAlphabeticalCount==lstAccountNumber.size())
						{
							Report.ValidationPoint(testContext.getName(), "Validate that multiple accounts having same account type are listed in alphabetical order by the account number","Displayed in alphabetical order for account type '"+arrAccountName[count]+"'", "Displayed in alphabetical order for account type '"+arrAccountName[count]+"'", true);
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Validate that multiple accounts having same account type are listed in alphabetical order by the account number","Displayed in alphabetical order for account type '"+arrAccountName[count]+"'", "NOT Displayed in alphabetical order for account type '"+arrAccountName[count]+"'", true);
						}
						
						
						
					}
					catch(Exception e)
					{
						Report.ValidationPoint(testContext.getName(), "Validate that multiple accounts having same account type are listed in alphabetical order by the account number","Displayed in alphabetical order", "NOT Displayed in alphabetical order", true);
					}
					
					//Validate image, Account Nickname , number , email address , bill delivery method are displayed if account is home phone
					//*[contains(text(),'"+sAccountName+"')]/parent::*/parent::*/parent::*/child::*[contains(@id,'toggle')]
					
					//Validate image displayed for all accounts of the type home phone,internet & TV
					
					if(sAccountName.contains("Home"))
					{
						try
						{	
							List<WebElement> lstImages = lDriver.findElements(By.xpath("//*[contains(text(),'Home')]/parent::*/parent::*/parent::*/child::*[contains(@id,'toggle')]//img"));
						
							if(lstImages.size()==lstAccountNumber.size())
							{
								Report.ValidationPoint(testContext.getName(), "Validate images are present for all accounts of the type 'Home Phone, Internet & Digital TV'","Displayed", "Displayed", true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate images are present for all accounts of the type 'Home Phone, Internet & Digital TV'","Displayed", "NOT Displayed", true);
							}
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(), "Validate images are present for all accounts of the type 'Home Phone, Internet & Digital TV'","Displayed", "NOT Displayed", true);
						}
					
						//Validate Account number displayed for all accounts of the type home phone,internet & TV
						try
						{
							List<WebElement> lstImages = lDriver.findElements(By.xpath("//*[contains(text(),'Home')]/parent::*/parent::*/parent::*/child::*[contains(@id,'toggle')]//*[contains(text(),'Account Number:')]"));
							
							if(lstImages.size()==lstAccountNumber.size())
							{
								Report.ValidationPoint(testContext.getName(), "Validate Account number are present for all accounts of the type 'Home Phone, Internet & Digital TV'","Displayed", "Displayed", true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate Account number are present for all accounts of the type 'Home Phone, Internet & Digital TV'","Displayed", "NOT Displayed", true);
							}
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(), "Validate Account number are present for all accounts of the type 'Home Phone, Internet & Digital TV'","Displayed", "NOT Displayed", true);
						}
					
						//Validate email address displayed for all accounts of the type home phone,internet & TV
						try
						{
							List<WebElement> lstImages = lDriver.findElements(By.xpath("//*[contains(text(),'Home')]/parent::*/parent::*/parent::*/child::*[contains(@id,'toggle')]//*[contains(text(),'Account Email:')]"));
						
							if(lstImages.size()==lstAccountNumber.size())
							{
								Report.ValidationPoint(testContext.getName(), "Validate email address are present for all accounts of the type 'Home Phone, Internet & Digital TV'","Displayed", "Displayed", true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate email address are present for all accounts of the type 'Home Phone, Internet & Digital TV'","Displayed", "NOT Displayed", true);
							}
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(), "Validate email address are present for all accounts of the type 'Home Phone, Internet & Digital TV'","Displayed", "NOT Displayed", true);
						}
					
						//Validate bill delivery method displayed for all accounts of the type home phone,internet & TV
						try
						{
							List<WebElement> lstImages = lDriver.findElements(By.xpath("//*[contains(text(),'Home')]/parent::*/parent::*/parent::*/child::*[contains(@id,'toggle')]//*[contains(text(),'Bill Delivery Method:')]"));
						
							if(lstImages.size()==lstAccountNumber.size())
							{
								Report.ValidationPoint(testContext.getName(), "Validate bill delivery methods are present for all accounts of the type 'Home Phone, Internet & Digital TV'","Displayed", "Displayed", true);
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate bill delivery methods are present for all accounts of the type 'Home Phone, Internet & Digital TV'","Displayed", "NOT Displayed", true);
							}
						}
						catch(Exception e)
						{
							Report.ValidationPoint(testContext.getName(), "Validate bill delivery methods are present for all accounts of the type 'Home Phone, Internet & Digital TV'","Displayed", "NOT Displayed", true);
						}
					}
				}
				
				
				catch(Exception e)
				{
					Report.TestPoint(testContext.getName(), "Check if account details of account '"+sAccountName+"' are expandable","Expandable", "NOT Expandable", true);
				}
			}
			
			//Click on AT&T Access ID Profile link and verify navigation to AT&T Access ID profile page
			oR_ATT_AccessID_Profile.lnkATTAccessIDProfile.click();
			Thread.sleep(25000);
			
			try
			{
				WebElement txtATTaccessIDTitle = lDriver.findElement(By.xpath("//*[contains(@class,'title')]//*[text()='AT&T Access ID']"));
				
				if(txtATTaccessIDTitle.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(), "Click on 'AT&T Access ID Profile' link and verify navigation to 'AT&T Access ID profile' page","Navigated", "Navigated", true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Click on 'AT&T Access ID Profile' link and verify navigation to 'AT&T Access ID profile' page","Navigated", "Failed to Navigate", true);
			}
			
			lDriver.navigate().back();
			Thread.sleep(25000);
			
			//Click on 'Manage account access' link and verify navigation to 'My Account Access' page
			oR_ATT_AccessID_Profile.lnkManageAccountAccess.click();
			Thread.sleep(25000);
			
			try
			{
				WebElement txtATTaccessIDTitle = lDriver.findElement(By.xpath("//*[contains(@class,'title')]//*[text()='My Account Access']"));
				
				if(txtATTaccessIDTitle.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(), "Click on 'Manage account access' link and verify navigation to 'My Account Access' page","Navigated", "Navigated", true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Click on 'Manage account access' link and verify navigation to 'My Account Access' page","Navigated", "Failed to Navigate", true);
			}
			
			lDriver.navigate().back();
			Thread.sleep(25000);
			
			//Click on 'Change AT&T Access ID' link and verify navigation to 'Change AT&T Access ID' page
			oR_ATT_AccessID_Profile.lnkChangeATTaccessID.click();
			Thread.sleep(25000);
			
			try
			{
				WebElement txtATTaccessIDTitle = lDriver.findElement(By.xpath("//*[contains(@class,'title')]//*[text()='Change AT&T Access ID']"));
				
				if(txtATTaccessIDTitle.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(), "Click on 'Change AT&T Access ID' link and verify navigation to 'Change AT&T Access ID' page","Navigated", "Navigated", true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Click on 'Change AT&T Access ID' link and verify navigation to 'Change AT&T Access ID' page","Navigated", "Failed to Navigate", true);
			}
			
			lDriver.navigate().back();
			Thread.sleep(25000);
			
			//Click on 'Link Another Account' link and verify navigation to 'Link an Account' page
			oR_ATT_AccessID_Profile.lnkLinkAnotherAccount.click();
			Thread.sleep(25000);
			
			try
			{
				WebElement txtATTaccessIDTitle = lDriver.findElement(By.xpath("//*[contains(@class,'title')]//*[contains(text(),'Link an Account')]"));
				
				if(txtATTaccessIDTitle.isDisplayed())
				{
					Report.ValidationPoint(testContext.getName(), "Click on 'Link Another Account' link and verify navigation to 'Link an Account' page","Navigated", "Navigated", true);
				}
			}
			catch(Exception e)
			{
				Report.ValidationPoint(testContext.getName(), "Click on 'Link Another Account' link and verify navigation to 'Link an Account' page","Navigated", "Failed to Navigate", true);
			}
			
			lDriver.navigate().back();
			Thread.sleep(25000);
			
			
		}catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}

	}
	
	/**************************************************************
	 * Function Name - VerifyLinkMyAccont
	 * Description- This function links a new account by providing required details.
	 * Parameters - 
	 * Date created -24-Apr-15
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
//PRF04518
	public static void VerifyLinkMyAccont(final ITestContext testContext)
			throws Exception
	{

		try {
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_LinkAnAccount oR_LinkAnAccount = PageFactory.initElements(lDriver, OR_LinkAnAccount.class);
			
			//selecting link my account from I want to drop down
			boolean bIwantTo = UI.WaitForObject(oR_AccountOverview.btnIWantTo , UI.iObjTimeOut);
			oR_AccountOverview.btnIWantTo.click();
			Actions action =   new Actions(lDriver);
			action.moveToElement(oR_AccountOverview.txtGetHelpAndSupport).build().perform();
			Report.TestPoint(testContext.getName(),"Link MY Accounts is selected", "True","True", true);
			action.moveToElement(oR_AccountOverview.lnklinkMyATTAccountsInIWantTo).click().build().perform();
			
			//Select Uverse account from drop down
			boolean bWireless = UI.WaitForObject(oR_LinkAnAccount.lstSelAcc , UI.iObjTimeOut);
			Report.OperationPoint(testContext.getName(), "Selecting Uverse Account");
			Select Account= new Select(lDriver.findElement(By.id("acctSelectType")));
			Account.selectByValue("uverse");
			Report.TestPoint(testContext.getName(),"Uverse Account is selected", "True","True", true);
						
			//Entering BAN and Zip code values
			boolean bBAN = UI.WaitForObject(oR_LinkAnAccount.edtBillingAccNum , UI.iObjTimeOut);
			Report.OperationPoint(testContext.getName(), "Entering BAN");
			oR_LinkAnAccount.edtBillingAccNum.sendKeys("119047316");
			oR_LinkAnAccount.edtZipCode.sendKeys("60622");
			Report.TestPoint(testContext.getName(),"Billing Account Number and Zipcode values are entered", "True","True", true);
			oR_LinkAnAccount.btnNext.click();
			Report.TestPoint(testContext.getName(),"Clicking on Next", "True","True", true);
			
			
			//Enetering Member ID and Uverse password
			boolean bMemberId = UI.WaitForObject(oR_LinkAnAccount.edtMemberId , UI.iObjTimeOut);
			boolean bPassword = UI.WaitForObject(oR_LinkAnAccount.edtPassword , UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(),"Page is displayed as expected", "True",String.valueOf(bMemberId), true);
			Report.OperationPoint(testContext.getName(), "Entering Member Id and Password");
			oR_LinkAnAccount.edtMemberId.sendKeys("qayls22_119047316@att.net");
			oR_LinkAnAccount.edtPassword.sendKeys("test1ng");
			Report.TestPoint(testContext.getName(),"Member Id and password values are enetered ", "True","True", true);
			oR_LinkAnAccount.btnContinue.click();
			oR_LinkAnAccount.btnContinue.click();
			
			//confirming linking account.
			boolean bSuccess = UI.WaitForObject(oR_LinkAnAccount.txtSuccessTitle, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(),"Success.Linking accounts is Completed", "True",String.valueOf(bSuccess), true);
			oR_LinkAnAccount.btnContinue2.click();
			
			// User landed on dashboard
			boolean bWelcome = UI.WaitForObject(oR_AccountOverview.txtWelcome, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(),"User landed on dashboard and database is updated with added account", "True",String.valueOf(bWelcome), true);
			
			}
		catch(Exception e)
			{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
			}

	}
	/**************************************************************
	 * Function Name - VerifyManageSubAccountsTab
	 * Description- This function validates manage sub accounts tab and email address field
	 * Parameters - 
	 * Date created -28-Apr-15
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
//PRF13059
	public static void VerifyManageSubAccountsTab(final ITestContext testContext)
			throws Exception
	{
		
		try {
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);

			
			//Mouseover on Profile link under second global navigation and click update my profile link
			Boolean bUpdate=UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Profile link under second global navigation", "true", String.valueOf(bUpdate), true);
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav ,oR_AccountOverview.lnkUpdateMyProfileTertNav);
			
			//Selecting Manage sub accounts tab
			Boolean bManageSubTab=UI.WaitForObject(oR_Profile.btnManageSubAccountsTab,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Manage Sub account tab", "true", String.valueOf(bManageSubTab), true);
			oR_Profile.btnManageSubAccountsTab.click();
			
			//Selecting Add a Sub account link
			Boolean bAddASub=UI.WaitForObject(oR_Profile.lnkAddASubAccount,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Add a sub account link", "true", String.valueOf(bAddASub), true);
			oR_Profile.lnkAddASubAccount.click();
			
			//Selecting Accept button
			Boolean bTerms=UI.WaitForObject(oR_Profile.txtTermsAndConditions,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "terms and conditions are displayed", "true", String.valueOf(bTerms), true);
			Boolean bAccept=UI.WaitForObject(oR_Profile.btnAccept,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Accept button is selected", "true", String.valueOf(bAccept), true);
			oR_Profile.btnAccept.click();

			//Entering all required details
			Boolean bEmail=UI.WaitForObject(oR_Profile.edtMemberEmail,UI.iObjTimeOut);
			Boolean bAlternateEmail=UI.WaitForObject(oR_Profile.edtAlternateEmail,UI.iObjTimeOut);
			oR_Profile.edtMemberEmail.sendKeys("qayls10_122941934");
			oR_Profile.edtPassword.sendKeys("test1ng");
			oR_Profile.edtConfirmPassword.sendKeys("test1ng");
			oR_Profile.edtFirstName.sendKeys("A");
			oR_Profile.edtLastName.sendKeys("B");
			oR_Profile. edtNickName.sendKeys("C");
			
			// Entering ALternate email other than MID
			oR_Profile.edtAlternateEmail.sendKeys("10_122941934@att.net");
			oR_Profile.txtAlternateEmail.click();
			if(UI.WaitForObject(oR_Profile.txtUpdateContactDetails, 10).equals(false))
				{
				
				Report.ValidationPoint(testContext.getName(),
						"Validate pop up message for updating contact details", "Pop Up is not Displayed", "Pop Up is not Displayed",
						true);
				
				} 
			else 
				{
				Report.ValidationPoint(testContext.getName(),
						"Validate pop up message for updating contact details", "Pop Up is not  Displayed", "Pop Up is  Displayed" ,
						true);
				}
			
			//entering alternate email id same as MID
			oR_Profile.edtAlternateEmail.clear();
			Report.OperationPoint(testContext.getName(), "Entering Alternate Email same as MID");
			
			oR_Profile.edtAlternateEmail.sendKeys("qayls10_122941934@att.net");
			oR_Profile.txtAlternateEmail.click();
				if(UI.WaitForObject(oR_Profile.txtUpdateContactDetails, 2).equals(true))
					{
			
					Report.ValidationPoint(testContext.getName(),
					"Validate pop up message for updating contact details", "Pop Up Displayed", "Pop Up Displayed",
					true);
					Boolean bYes=UI.WaitForObject(oR_Profile.btnYes,UI.iObjTimeOut);
					Report.TestPoint(testContext.getName(), "CTA is displayed as YES in pop up", "true", String.valueOf(bYes), true);
					oR_Profile.btnYes.click();
					Report.TestPoint(testContext.getName(), "The warning pop-up should be closed and the focus is returned to the 'Alternate Email' field.", "true", String.valueOf(oR_Profile.edtAlternateEmail.isDisplayed()), true);
					oR_Profile.edtAlternateEmail.sendKeys("qayls10_122941934@att.net");
					oR_Profile.txtAlternateEmail.click();
						if(UI.WaitForObject(oR_Profile.txtUpdateContactDetails, 2).equals(true))
								{
			
									Boolean bNoKeepMyEmail=UI.WaitForObject(oR_Profile.lnkNoKeepMyCurrentEmail,UI.iObjTimeOut);
									Report.TestPoint(testContext.getName(), "CTA is displayed as No, keep my current contact  email address in pop up", "true", String.valueOf(bNoKeepMyEmail), true);
									oR_Profile.lnkNoKeepMyCurrentEmail.click();
									Report.TestPoint(testContext.getName(), "The warning pop-up should be closed and the focus is returned to the 'Alternate Email' field.", "true", String.valueOf(oR_Profile.edtAlternateEmail.isDisplayed()), true);
									
								} 
					}
		
					else 
					{
						Report.ValidationPoint(testContext.getName(),
								"Validate pop up message for updating contact details", "Pop Up Displayed", "Pop Up is not Displayed" ,
						true);
					}
			
			
		}
			catch(Exception e)
			{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
			}

	}
	/**************************************************************
	 * Function Name - VerifyEmailfieldandlanguageinManageSubAccountsTab
	 * Description- This function validates  email address field in manage sub accounts tab and language of the page
	 * Parameters - 
	 * Date created -29-Apr-15
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
//PRF13045
	public static void VerifyEmailfieldandlanguageinManageSubAccountsTab(final ITestContext testContext)
			throws Exception
	{

		try {	
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);

			//Mouseover on Profile link under second global navigation and click mnaage sub account link
			Boolean bUpdate=UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Profile link under second global navigation", "true", String.valueOf(bUpdate), true);
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav ,oR_AccountOverview.lnkManageSubAccountTertNav);
			
			//change language to spanish and back to english
			Thread.sleep(20000);
			oR_AccountOverview.lnkLanguage.click();
			oR_AccountOverview.lnkSpanish.click();
			Thread.sleep(10000);
			Boolean bLanguage=UI.WaitForObject(oR_Profile.txtSpanish,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Spanish language is selected", "true", String.valueOf(bLanguage), true);
			oR_AccountOverview.lnkLanguage.click();
			oR_AccountOverview.lnkEnglish.click();
			Thread.sleep(5000);
			
			//Selecting Add a Sub account link
			Boolean bAddASub=UI.WaitForObject(oR_Profile.lnkAddASubAccount,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Add a sub account link", "true", String.valueOf(bAddASub), true);
			oR_Profile.lnkAddASubAccount.click();
						
			//Selecting Accept button
			Boolean bTerms=UI.WaitForObject(oR_Profile.txtTermsAndConditions,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "terms and conditions are displayed", "true", String.valueOf(bTerms), true);
			Boolean bAccept=UI.WaitForObject(oR_Profile.btnAccept,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Accept button is selected", "true", String.valueOf(bAccept), true);
			oR_Profile.btnAccept.click();
			
			//change language to spanish and back to english
			Thread.sleep(20000);
			oR_AccountOverview.lnkLanguage.click();
			oR_AccountOverview.lnkSpanish.click();
			Thread.sleep(5000);
			Report.TestPoint(testContext.getName(), "Spanish language is selected", "true","true", true);
			if(oR_Profile.btnAccept.isDisplayed())
				{
				oR_Profile.btnAccept.click();
				}
			else
			{
				Report.TestPoint(testContext.getName(), "Accept button is not displayed", "true","fail", true);
					
			}
			Thread.sleep(15000);
			oR_AccountOverview.lnkLanguage.click();
			oR_AccountOverview.lnkEnglish.click();
			if(oR_Profile.btnAccept.isDisplayed())
					{
				oR_Profile.btnAccept.click();
					}
			else
			{
				Report.TestPoint(testContext.getName(), "Accept button is not displayed", "true","fail", true);
					
			}
			//validation short description regarding email
			Thread.sleep(10000);
			Boolean bEmail=UI.WaitForObject(oR_Profile.txtEmailDescription,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Short description regarding Email/ Member id is displayed", "true", String.valueOf(bEmail), true);
			
			// validation alternate email address field
			Boolean bAlternateEmail=UI.WaitForObject(oR_Profile.txtAlternateEmail,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Alternate Email Id field is displayed", "true", String.valueOf(bAlternateEmail), true);
			
			//validating if User is able  to view an at symbol ( @ ) between handle and domain
			Boolean bATTNet=UI.WaitForObject(oR_Profile.txtDomainATTNetAfterEmail,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "User is able  to view an at symbol ( @ ) between handle and domain", "true", String.valueOf(bATTNet), true);
			
			
			//change language to spanish and back to english
			Thread.sleep(20000);
			oR_AccountOverview.lnkLanguage.click();
			oR_AccountOverview.lnkSpanish.click();
			Thread.sleep(5000);
			Report.TestPoint(testContext.getName(), "Spanish language is selected", "true","true", true);
			if(oR_Profile.btnAccept.isDisplayed())
				{
				oR_Profile.btnAccept.click();
				}
			else
			{
				Report.TestPoint(testContext.getName(), "Accept button is not displayed", "true","fail", true);
					
			}
			Thread.sleep(20000);
			oR_AccountOverview.lnkLanguage.click();
			oR_AccountOverview.lnkEnglish.click();
			Thread.sleep(5000);
			if(oR_Profile.btnAccept.isDisplayed())
					{
				oR_Profile.btnAccept.click();
					}
			else
			{
				Report.TestPoint(testContext.getName(), "Accept button is not displayed", "true","fail", true);
					
			}
			//Validating short description regarding alternate email
			Thread.sleep(10000);
			Boolean bAlternateEmailDesc=UI.WaitForObject(oR_Profile.txtAlternateEmailDescription,UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Alternate Email Description is displayed", "true", String.valueOf(bAlternateEmailDesc), true);
						
			//Validating warning message when alternate message is same as  MID
			oR_Profile.edtAlternateEmail.clear();
			oR_Profile.edtMemberEmail.sendKeys("qayls10_122941934");
			Report.OperationPoint(testContext.getName(), "Entering Alternate Email same as MID");
			oR_Profile.edtAlternateEmail.sendKeys("qayls10_122941934@att.net");
			Report.ValidationPoint(testContext.getName(),
			"Validating warning message when alternate message is same as  MID", "true", "true" ,
			true);
			oR_Profile.txtAlternateEmail.click();
				if(UI.WaitForObject(oR_Profile.txtUpdateContactDetails, 2).equals(true))
					{
			
					Report.ValidationPoint(testContext.getName(),
					"Validate pop up message for Warning", "Pop Up Displayed", "Pop Up Displayed",
					true);
					oR_Profile.lnkNoKeepMyCurrentEmail.click();
					}
					else 
					{
					Report.ValidationPoint(testContext.getName(),
							"Validate pop up message for updating contact details", "Pop Up Displayed", "Pop Up is not Displayed" ,
					true);
					}
				
				//change language to spanish and back to english
				Thread.sleep(20000);
				oR_AccountOverview.lnkLanguage.click();
				oR_AccountOverview.lnkSpanish.click();
				Thread.sleep(5000);
				Report.TestPoint(testContext.getName(), "Spanish language is selected", "true","true", true);
				if(oR_Profile.btnAccept.isDisplayed())
					{
					oR_Profile.btnAccept.click();
					}
				else
				{
					Report.TestPoint(testContext.getName(), "Accept button is not displayed", "true","fail", true);
						
				}
				Thread.sleep(20000);
				oR_AccountOverview.lnkLanguage.click();
				oR_AccountOverview.lnkEnglish.click();
				Thread.sleep(5000);
				if(oR_Profile.btnAccept.isDisplayed())
						{
					oR_Profile.btnAccept.click();
						}
				else
				{
					Report.TestPoint(testContext.getName(), "Accept button is not displayed", "true","fail", true);
						
				}
				
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
			}
			
		
	}
	/**************************************************************
	 * Function Name - VerifyWirelessLinkingFlowWithSlid
	 * Description- This function links a new account 
	 * Parameters - 
	 * Date created -11-May-15
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
//PRF07654
	public static void VerifyWirelessLinkingFlowWithSlid(final ITestContext testContext)
			throws Exception
	{

		try {
			
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			OR_LinkAnAccount oR_LinkAnAccount = PageFactory.initElements(lDriver, OR_LinkAnAccount.class);

			//selecting link Another account from My Linked Account
			UI.WaitForObject(oR_AccountOverview.lnkMyLinkedAccounts , UI.iObjTimeOut);
			Actions action =   new Actions(lDriver);
			action.moveToElement(oR_AccountOverview.lnkMyLinkedAccounts);
			Report.TestPoint(testContext.getName(),"Selecting Link Another Account", "True","True", true);
			action.moveToElement(oR_AccountOverview.lnkLinkAnotherAccountInMyLinkedAccount).click().build().perform();
			
			//Select Wireless account from drop down
			UI.WaitForObject(oR_LinkAnAccount.lstSelAcc , UI.iObjTimeOut);
			Report.OperationPoint(testContext.getName(), "Selecting Wireless Account");
			Select Account= new Select(lDriver.findElement(By.id("acctSelectType")));
			Account.selectByValue("wireless");
			
			// Enterting details of member ID and Zip code
			boolean bWirelessNumber = UI.WaitForObject(oR_LinkAnAccount.edtBillingAccNum , UI.iObjTimeOut);
			UI.WaitForObject(oR_LinkAnAccount.edtZipCode , UI.iObjTimeOut);
			oR_LinkAnAccount.edtBillingAccNum.sendKeys("9727627826"); 
			oR_LinkAnAccount.edtZipCode.sendKeys("75081");
			Report.TestPoint(testContext.getName(),"Wireless Account details entered", "True",String.valueOf(bWirelessNumber), true);
			oR_LinkAnAccount.btnNext.click();
			
			//Entering SSN value
			Thread.sleep(15000);
			boolean bSSN = UI.WaitForObject(oR_LinkAnAccount.edtLast4SSN , UI.iObjTimeOut);
			oR_LinkAnAccount.edtLast4SSN.sendKeys("4545"); 
			Report.TestPoint(testContext.getName(),"SSN details entered", "True",String.valueOf(bSSN), true);
			oR_LinkAnAccount.btnContinue.click();
			
			//Navigating to confiramtion page
			boolean bCont = UI.WaitForObject(oR_LinkAnAccount.btnContinue2 , UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(),"Confirmation page dispalyed", "True",String.valueOf(bCont), true);
			oR_LinkAnAccount.btnContinue2.click();
			
			//Navigating to profile tab
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav, null);
			Report.TestPoint(testContext.getName(),
					"Validate Profile link on secondary navigation",
					"True", "True", true);
			oR_AccountOverview.lnkProfileSecondaryNav.click();
			
			oR_Profile.lnkAttAccessIdProfile.click();
			
			// selection ManageAccount Access link From My Linked Account Drop Down
			action.moveToElement(oR_AccountOverview.lnkMyLinkedAccounts);
			Report.TestPoint(testContext.getName(),"Selecting Manage Access Account", "True","True", true);
			action.moveToElement(oR_AccountOverview.lnkManageAccountAccessInMyLinkedAccount).click().build().perform();
			
			
			/*boolean bmanage = UI.WaitForObject(oR_Profile.lnkManageAccountAccess , UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(),"Manage Account Access link is dispalyed", "True",String.valueOf(bmanage), true);
			oR_Profile.lnkManageAccountAccess.click();*/
			
			//validating pending acceptance is present or not
			boolean bPending = UI.WaitForObject(oR_Profile.txtPendingAcceptance , UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(),"Pending acceptance is present ", "True",String.valueOf(bPending), true);
			
			
			}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
		
	
	}
	
	/**************************************************************
	 * Function Name - VerifyAccountNicknameAfterLinkingUverse
	 * Description- This function links a new account and then checks the nickname in the profile page
	 * Parameters - 
	 * Date created -15-May-15
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//PRF08241
	public static void VerifyAccountNicknameAfterLinkingUverse(final ITestContext testContext)throws Exception
	{
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

		String sBAN = IO.GetParamVal(sTestParams, "BAN");
		String sZipcode = IO.GetParamVal(sTestParams, "ZIPCODE");
		String sMemID = IO.GetParamVal(sTestParams, "MEMBER_ID");
		String sPassword = IO.GetParamVal(sTestParams, "PASSWORD");
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			OR_LinkAnAccount oR_LinkAnAccount = PageFactory.initElements(lDriver, OR_LinkAnAccount.class);

			//Validate Link accounts page
			//Boolean linkPg = UI.WaitForObject(oR_LinkAnAccount.txtLinkAnAccount, 30);
			Boolean linkPg = UI.WaitForObject(oR_LinkAnAccount.lnkLinkMyAccounts, 30);
			Report.TestPoint(testContext.getName(),"Validate Link accounts page ", "True",String.valueOf(linkPg), true);
			//Click on Link my account button
			//Boolean LinkAccBtn = UI.WaitForObject(oR_LinkAnAccount.btnLinkMyAccounts, 1);
			Boolean LinkAccBtn = UI.WaitForObject(oR_LinkAnAccount.lnkLinkMyAccounts, 20);
			Report.TestPoint(testContext.getName(),"Validate Link my account button", "True",String.valueOf(LinkAccBtn), true);
			Report.OperationPoint(testContext.getName(), "Click on Link my account button");
			//oR_LinkAnAccount.btnLinkMyAccounts.click();
			oR_LinkAnAccount.lnkLinkMyAccounts.click();
			
			//Validate Link an account - Select account type page
			Boolean linkAnAccPg = UI.WaitForObject(oR_LinkAnAccount.txtSelectAccountType, 50);
			Report.TestPoint(testContext.getName(),"Validate Link an account - Select account type  page ", "True",String.valueOf(linkAnAccPg), true);
			//Validate Account type text and dropdown
			Boolean linkAccountDrop = UI.WaitForObject(oR_LinkAnAccount.lstSelAccType, 1);
			Report.TestPoint(testContext.getName(),"Validate Account type text and dropdown", "True",String.valueOf(linkAccountDrop), true);
			//Select an account U-verse TV, Internet & Voice
			Report.OperationPoint(testContext.getName(), "Select an account U-verse TV, Internet & Voice");
			UI.SelectOptionFromDropDown(oR_LinkAnAccount.lstSelAcc, "U-verse TV, Internet & Voice");
			//Validate Billing account number editbox and Billing zipcode editbox
			Boolean AccNoEdt = UI.WaitForObject(oR_LinkAnAccount.edtBillingAccNum, 5);
			Boolean ZipCodeEdt = UI.WaitForObject(oR_LinkAnAccount.edtZipCode, 5);
			if(AccNoEdt.equals(true) && ZipCodeEdt.equals(true))
			{
				Report.TestPoint(testContext.getName(),"Validate Billing account number editbox and Billing zipcode editbox", "True","True", true);
				//Entering the values
				Report.OperationPoint(testContext.getName(), "Entering Billing account number");
				oR_LinkAnAccount.edtBillingAccNum.sendKeys(sBAN);
				Report.OperationPoint(testContext.getName(), "Entering Zipcode");
				oR_LinkAnAccount.edtZipCode.sendKeys(sZipcode);
				
				//Clicking on next button
				Report.OperationPoint(testContext.getName(), "Clicking on next button");
				oR_LinkAnAccount.btnNext.click();
				
				//Validate AT&T Access ID - Verify Account Information page
				Boolean VerifyPg = UI.WaitForObject(oR_LinkAnAccount.txtVerifyAccountInformation, 50);
				Report.TestPoint(testContext.getName(),"Validate AT&T Access ID - Verify Account Information page", "True",String.valueOf(VerifyPg), true);
				//Validate editboxes Member ID and Uverse password
				Boolean edtMem = UI.WaitForObject(oR_LinkAnAccount.edtMemberId, 5);
				Boolean edtPwd = UI.WaitForObject(oR_LinkAnAccount.edtPassword, 5);
				if(edtMem.equals(true) && edtPwd.equals(true))
				{
					Report.TestPoint(testContext.getName(),"Validate editboxes Member ID and Uverse password", "True","True", true);
					//Entering the values
					Report.OperationPoint(testContext.getName(), "Entering Member ID");
					oR_LinkAnAccount.edtMemberId.sendKeys(sMemID);
					Report.OperationPoint(testContext.getName(), "Entering Uverse password");
					oR_LinkAnAccount.edtPassword.sendKeys(sPassword);
					
					//Clicking on next button
					Report.OperationPoint(testContext.getName(), "Clicking on next button");
					oR_LinkAnAccount.btnNext1.click();
					
					//Validate Agree to Account link page
					Boolean AgreePg = UI.WaitForObject(oR_LinkAnAccount.txtAgreeAccLink,50);
					Report.TestPoint(testContext.getName(),"Validate Agree to Account link page", "True",String.valueOf(AgreePg), true);
					
					//Click on Continue
					Report.OperationPoint(testContext.getName(), "Click on Continue");
					oR_LinkAnAccount.btnContinue.click();
					
					//Validate success page
					Boolean successPg = UI.WaitForObject(oR_LinkAnAccount.txtSuccessTitle, 1);
					Report.TestPoint(testContext.getName(),"Validate success page", "True",String.valueOf(successPg), true);
					//Click on Continue button
					oR_LinkAnAccount.btnContinue2.click();
					
					//Validate Account overview page
					Boolean lnkOverview = UI.WaitForObject(oR_AccountOverview.lnkOverview, 50);
					Report.TestPoint(testContext.getName(),"Validate Account overview page", "True",String.valueOf(lnkOverview), true);
					
					//Click on secondary navigation link Profile
					UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav,null);
					
					//Validate profile page
					Boolean ProfilePg = UI.WaitForObject(oR_Profile.txtMyProfileTitle, 50);
					Report.TestPoint(testContext.getName(),"Validate profile page", "True",String.valueOf(ProfilePg), true);
					
					//Validate Text Account nickname
					Boolean AccountNickame = UI.WaitForObject(oR_Profile.txtAccountNickname, 50);
					Report.TestPoint(testContext.getName(),"Validate Text Account nickname", "True",String.valueOf(AccountNickame), true);
					//Validate Account nickname value
					String sNickname = oR_Profile.txtAccountNicknameValue.getText();
					//retrieving the MemberID from data sheet and splitting the first part
					String[] sNickNameArr = sMemID.split("_");
					String sNickNameIni = sNickNameArr[0];
					if(sNickname.contains(sNickNameIni))
					{
						Report.ValidationPoint(testContext.getName(),"Validate Text Account nickname should be same as member ID provided during linking of account", "True","True", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(),"Validate Text Account nickname should be same as member ID provided during linking of account", "True","False", true);
					}	
				}
				else
				{
					Report.TestPoint(testContext.getName(),"Validate editboxes Member ID and Uverse password", "True","False", true);
				}
			}
			else
			{
				Report.TestPoint(testContext.getName(),"Validate Billing account number editbox and Billing zipcode editbox", "True","False", true);
			}
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name - VerifyUnregWirelineORCoptions
	 * Description- This function verifies ORC options for unregistered Wireline
	 * Parameters - 
	 * Date created -18-May-15
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//PRF04478
	public static void VerifyUnregWirelineORCoptions(final ITestContext testContext)throws Exception
	{
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

		String sBAN = IO.GetParamVal(sTestParams, "BAN");
		String sZipcode = IO.GetParamVal(sTestParams, "ZIPCODE");
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_LinkAnAccount oR_LinkAnAccount = PageFactory.initElements(lDriver, OR_LinkAnAccount.class);

			/*** Code Modified -Monica 10th June 2015 ***/
			//Validate Link accounts page
//			Boolean linkPg = UI.WaitForObject(oR_LinkAnAccount.txtLinkAnAccount, 30);
//			Report.TestPoint(testContext.getName(),"Validate Link accounts page ", "True",String.valueOf(linkPg), true);
			
			//Click on Link my account button
			Boolean LinkAccBtn = UI.WaitForObject(lDriver.findElement(By.xpath("//a[contains(text(),'Link your accounts')]")), 1);
			Report.TestPoint(testContext.getName(),"Validate Link my account button", "true",String.valueOf(LinkAccBtn), true);
			Report.OperationPoint(testContext.getName(), "Click on Link my account button");
			lDriver.findElement(By.xpath("//a[contains(text(),'Link your accounts')]")).click();
			//Validate Link an account - Select account type page
			Boolean linkAnAccPg = UI.WaitForObject(oR_LinkAnAccount.txtSelectAccountType, 50);
			Report.TestPoint(testContext.getName(),"Validate Link an account - Select account type  page ", "true",String.valueOf(linkAnAccPg), true);
			//Validate 
			//Validate Account type text and dropdown
			Boolean linkAccountDrop = UI.WaitForObject(oR_LinkAnAccount.lstSelAccType, 1);
			Report.TestPoint(testContext.getName(),"Validate Account type text and dropdown", "true",String.valueOf(linkAccountDrop), true);
			//Select an account U-verse TV, Internet & Voice
			UI.SelectOptionFromDropDown(oR_LinkAnAccount.lstSelAcc, "Home Phone, Internet & Digital TV");
			//Validate Billing account number editbox and Billing zipcode editbox
			Boolean AccNoEdt = UI.WaitForObject(oR_LinkAnAccount.edtBillingAccNum, 5);
			Boolean ZipCodeEdt = UI.WaitForObject(oR_LinkAnAccount.edtZipCode, 5);
			if(AccNoEdt.equals(true) && ZipCodeEdt.equals(true))
			{
				Report.TestPoint(testContext.getName(),"Validate Billing account number editbox and Billing zipcode editbox", "True","True", true);
				//Entering the values
				Report.OperationPoint(testContext.getName(), "Entering Billing account number");
				oR_LinkAnAccount.edtBillingAccNum.sendKeys(sBAN);
				Report.OperationPoint(testContext.getName(), "Entering Zipcode");
				oR_LinkAnAccount.edtZipCode.sendKeys(sZipcode);
				
				//Clicking on next button
				Report.OperationPoint(testContext.getName(), "Clicking on next button");
				oR_LinkAnAccount.btnNext.click();
			}
			//Validate AT&T Access ID – Receive Online Registration Code page
			Boolean ORCpg = UI.WaitForObject(oR_LinkAnAccount.txtORCPg, 30);
			Report.TestPoint(testContext.getName(),"Validate AT&T Access ID – Receive Online Registration Code page", "true",String.valueOf(ORCpg), true);
			//Validate I already have an ORC checkbox field
			Boolean ORCihave = UI.WaitForObject(oR_LinkAnAccount.radAlreadyHaveOrc, 1);
			Report.ValidationPoint(testContext.getName(),"Validate I already have an ORC checkbox field", "true",String.valueOf(ORCihave), true);
			//Validate Radio button with different options to send the ORC - nothing selected by default
			Boolean ORCoptions = UI.WaitForObject(oR_LinkAnAccount.radAlreadyHaveOrc, 1);
			Report.ValidationPoint(testContext.getName(),"Validate Radio button with different options to send the ORC - nothing selected by default", "true",String.valueOf(ORCoptions), true);
			//Validate Cancel link
			
			/*** Code Modified for all reporting steps-Monica 10th June 2015 ***/
			Boolean lnkCancel = UI.WaitForObject(oR_LinkAnAccount.lnkCancel2, 1);
			Report.ValidationPoint(testContext.getName(),"Validate Cancel link", "true",String.valueOf(lnkCancel), true);
			//Validate back button
			Boolean btnBack = UI.WaitForObject(oR_LinkAnAccount.btnback, 1);
			Report.ValidationPoint(testContext.getName(),"Validate back button", "true",String.valueOf(btnBack), true);
			//Validate Next button
			/*** Code Modified -Monica 10th June 2015 ***/
			Boolean btnNext = UI.WaitForObject(oR_LinkAnAccount.btnNext3, 1);
			Report.ValidationPoint(testContext.getName(),"Validate Next button", "true",String.valueOf(btnNext), true);
			//Click on Radio button with different options to send the ORC
			Report.OperationPoint(testContext.getName(), "Click on Radio button");
			oR_LinkAnAccount.radORCclick.click();
			//Verify that the following options are present to send ORC: a. SMS B. Email
			//Validate radio button for email option
			Boolean radEmail = UI.WaitForObject(oR_LinkAnAccount.radEmail, 1);
			Report.ValidationPoint(testContext.getName(),"Validate radio button for email option", "true",String.valueOf(radEmail), true);
			//Validate account email address is displayed along with radio button
			Boolean radEmailTxt = UI.WaitForObject(oR_LinkAnAccount.txtEmailText, 1);
			if(oR_LinkAnAccount.txtEmailText.getText().contains("@att.com"))
			{
				Report.ValidationPoint(testContext.getName(),"Validate account email address is displayed along with radio button", "true",String.valueOf(radEmailTxt), true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(),"Validate account email address is displayed along with radio button", "true",String.valueOf(radEmailTxt), true);
			}
			//Verify the presence of the 'More Options' link beneath the displayed options
			Boolean lnkMore = UI.WaitForObject(oR_LinkAnAccount.lnkMoreOptions, 1);
			Report.ValidationPoint(testContext.getName(),"Validate the presence of the 'More Options' link beneath the displayed options", "true",String.valueOf(lnkMore), true);
			//Click on More options link
			Report.OperationPoint(testContext.getName(), "Click on More options link");
			oR_LinkAnAccount.lnkMoreOptions.click();
			//The radio Button for 'Telephone Number' and 'US Mail' will be displayed as options to send the ORC along with the radio buttons for SMS and Email
			Boolean radTelephone = UI.WaitForObject(oR_LinkAnAccount.radTelephone, 1);
			Report.ValidationPoint(testContext.getName(),"Validate radio button Telephone will be displayed as options to send the ORC along with the radio buttons for SMS and Email", "true",String.valueOf(radTelephone), true);
			Boolean radMail = UI.WaitForObject(oR_LinkAnAccount.radMail, 1);
			Report.ValidationPoint(testContext.getName(),"Validate radio button for Mail will be displayed as options to send the ORC along with the radio buttons for SMS and Email", "true",String.valueOf(radMail), true);
			//Click on Back button
			Report.OperationPoint(testContext.getName(), "Click on Back button");
			oR_LinkAnAccount.btnBack.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(),"Some error has occured", "true",e.getMessage(), true);
		}
	}
	/**************************************************************
	 * Function Name - Verifyfloprequirement
	 * Description- verify   flop requirement that maximum 9 BTNs should be displayed on link confirmation screen
	 * Parameters - 
	 * Date created -20th-May-2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//PRF08226
	public static void Verifyfloprequirement(final ITestContext testContext)throws Exception 
	{

		try 
		{
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_LinkAnAccount oR_LinkAnAccount = PageFactory.initElements(lDriver, OR_LinkAnAccount.class);
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			Actions driver= new Actions(lDriver);	
			// click on link my account
			UI.WaitForObject(oR_AccountOverview.lnkMyLinkedAccounts, 20, lDriver);
			driver.moveToElement(oR_AccountOverview.lnkMyLinkedAccounts);
			Boolean bLink = UI.WaitForObject(oR_AccountOverview.lnkLinkAnotherAccountInMyLinkedAccount, 5);
			Report.ValidationPoint(testContext.getName(),"Validate Link Another Account link is displayed", "True",String.valueOf(bLink), true);
			oR_AccountOverview.lnkLinkAnotherAccountInMyLinkedAccount.click();
			
			//Select Home Phone, Internet & Digital TV from drop down
			boolean bWireline = UI.WaitForObject(oR_LinkAnAccount.lstSelAccType , UI.iObjTimeOut);
			Report.OperationPoint(testContext.getName(), "Selecting Home Phone, Internet & Digital TV Account");
			Select Account= new Select(lDriver.findElement(By.id("acctSelectType")));
			Account.selectByValue("wireline");
			Report.TestPoint(testContext.getName(),"Validate account type is selected", "True",String.valueOf(bWireline), true);
						
			//Entering BAN and Zip code values
			boolean bBAN = UI.WaitForObject(oR_LinkAnAccount.edtBillingAccNum , UI.iObjTimeOut);
			Report.OperationPoint(testContext.getName(), "Entering BAN");
			oR_LinkAnAccount.edtBillingAccNum.sendKeys("8166370091258");
			oR_LinkAnAccount.edtZipCode.sendKeys("75081");
			Report.TestPoint(testContext.getName(),"Billing Account Number and Zipcode values are entered", "True","True", true);
			oR_LinkAnAccount.btnNext.click();
			
			//click on request secondary access radio button
			boolean bRequest = UI.WaitForObject(oR_LinkAnAccount.radRequestSecondaryAccesss , UI.iObjTimeOut);
			lDriver.findElement(By.id("requestSecondaryAccess")).click();
			Report.TestPoint(testContext.getName(),"clicking on request secondary access radio button", "True",String.valueOf(bRequest), true);
						
			//entering customer code
			boolean bCustomerCode= UI.WaitForObject(oR_LinkAnAccount.edtCustomerCode , UI.iObjTimeOut);
			oR_LinkAnAccount.edtCustomerCode.sendKeys("258");
			Report.TestPoint(testContext.getName(),"Entered details of customer code", "True",String.valueOf(bCustomerCode), true);
			boolean bNext=UI.WaitForObject(oR_LinkAnAccount.btnShowNext , UI.iObjTimeOut);
			oR_LinkAnAccount.btnShowNext.click();
			Report.OperationPoint(testContext.getName(), "Clicking on NEXT");
			
			boolean bConfirm= UI.WaitForObject(oR_LinkAnAccount.txtConfirmation , UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(),"Confirmation page is dispayed as expected", "True",String.valueOf(bConfirm), true);
			UI.WaitForObject(oR_LinkAnAccount.btnContinue2 , UI.iObjTimeOut);
			oR_LinkAnAccount.btnContinue2.click();
			Report.TestPoint(testContext.getName(),"My account access page is displayed", "True","True", true);
			
			
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyChangePasswordAndFmailyManagerWebsiteRedirection()
	 * Description -The purpose of this test is to check when user updates SLID password on the Change Password page of MyAT&T, he is redirected back to Family Manager Website.
 	 * Test Case - PRF09437
	 * Parameters - None
	 * Date created - 19th June 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyChangePasswordAndFmailyManagerWebsiteRedirection(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_EditATTAccessIDInformation oR_EditATTAccessIDInformation = PageFactory.initElements(lDriver, OR_EditATTAccessIDInformation.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			String sSimulatingURL = IO.GetParamVal(sTestParams, "New_URL_For_ChangePassword");
			String sReturnURL = IO.GetParamVal(sTestParams, "Return_URL");
			String sCurrPassword = IO.GetParamVal(sTestParams, "Password");
			String sNewPassword = IO.GetParamVal(sTestParams, "New_Password");
			String sSecAns1 = IO.GetParamVal(sTestParams, "Security_Answer_1");
			String sSecAns2 = IO.GetParamVal(sTestParams, "Security_Answer_2");
			
			/*WebDriver driver = new FirefoxDriver();
			String mainWindow = lDriver.getWindowHandle();
			 for (String winHandle : lDriver.getWindowHandles()) 
			    {
			    	lDriver.switchTo().window(winHandle);
			    }
			 Report.OperationPoint(testContext.getName(), "Navigating to cahnge password page "+sSimulatingURL);
			*/	
			lDriver.get(sSimulatingURL);
			boolean bPageHeading = UI.WaitForObject(oR_Profile.txtMyProfileTitle, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Validate that Change Password page is displayed", "true", String.valueOf(bPageHeading), true);
				
			try
			{
				//Enter values in Current, New and Cofirm New password feilds
				oR_EditATTAccessIDInformation.edtCurrentPassword.sendKeys(sCurrPassword);
				Report.OperationPoint(testContext.getName(), "Operational - Entered Current Password");
				oR_EditATTAccessIDInformation.edtNewPassword.sendKeys(sNewPassword);
				Report.OperationPoint(testContext.getName(), "Operational - Entered New Password");
				oR_EditATTAccessIDInformation.edtConfirmNewPassword.sendKeys(sNewPassword);
				Report.OperationPoint(testContext.getName(), "Operational - Entered confirm Password");
				
				//Select security question and enter value in answer feilds
				Select slt1 = new Select(oR_EditATTAccessIDInformation.lstSelectSecurityQues1);
				slt1.selectByIndex(2);
				Report.OperationPoint(testContext.getName(), "Operational - Selected Security Question from 1st drop-down");
				oR_EditATTAccessIDInformation.edtSecurityAns1.sendKeys(sSecAns1);
				
				Select slt2 = new Select(oR_EditATTAccessIDInformation.lstSelectSecurityQues2);
				slt2.selectByIndex(2);
				Report.OperationPoint(testContext.getName(), "Operational - Selected Security Question from 2nd drop-down");
				oR_EditATTAccessIDInformation.edtSecurityAns2.sendKeys(sSecAns2);
				
				
				Report.ValidationPoint(testContext.getName(), "Verify that all the values are entered properly in Change password page", "true","true", true);
			
			}catch (Exception ee)
			{
				Report.ValidationPoint(testContext.getName(), "Verify that all the values are entered properly in Change password page", "true","false", true);
			}
			
			oR_EditATTAccessIDInformation.btnSaveChanges.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Save Changes' button");
			
			//Verify Confirmation page is displayed
			boolean bConfirmPage = UI.WaitForObject(oR_EditATTAccessIDInformation.txtConfirmationPage, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that Confirmation page is displayed", "true",String.valueOf(bConfirmPage), true);
			
			//Verify Return to Smart Limits link is displayed
			boolean bReturnLink = UI.WaitForObject(oR_EditATTAccessIDInformation.lnkReturnToSmartLimits, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify that the link 'Return To Smart Limits' is displayed", "true",String.valueOf(bReturnLink), true);
			oR_EditATTAccessIDInformation.lnkReturnToSmartLimits.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Return to Smart Limits' link");
			
			//Verify the page is redirected to the specific return URL
			Thread.sleep(5000);
			String sRedirectedURL = lDriver.getCurrentUrl().toString();
			System.out.println(sRedirectedURL);
			if(sRedirectedURL.contains(sReturnURL))
			{
				Report.ValidationPoint(testContext.getName(), "Verify that the page is redirected to the specific URL: "+sReturnURL+" Page is redirected to following URL: "+sRedirectedURL, "true","true", true);

			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify that the page is redirected to the specific URL: "+sReturnURL+" Page is redirected to following URL: "+sRedirectedURL, "true","false", true);
			}
			//lDriver.switchTo().window(mainWindow);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	/**************************************************************
	 * Function Name - VerifyNotificationsPresence
	 * Description- 
	 * Parameters - 
	 * Date created -30th-Jun-2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//PRF11780
	public static void VerifyNotificationsPresence(final ITestContext testContext)throws Exception 
	{

		try 
		{
			// Navigate to my profile page on global navigation
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			if (UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav, 10)) 
				{
				Report.TestPoint(testContext.getName(),
						
						"Validate Profile link on secondary navigation",
						"True", "True", true);
				oR_AccountOverview.lnkProfileSecondaryNav.click();
				}
			
			//Validating separate section for billing notifications
			boolean bBillInfo =  UI.WaitForObject(oR_Profile.txtBillingNotifications, 10);
			Report.TestPoint(testContext.getName(),
					"Validate Separate section is present for Billing Notifiacation",
					"true", String.valueOf(bBillInfo), true);
			
			//validating texts in account information tab
			boolean bBillAlert= UI.WaitForObject(oR_Profile.txtBillAlertNotifications, 10);
			boolean bBillThreshold= UI.WaitForObject(oR_Profile.txtBillThresholdNotifications, 10);
			boolean bBillReady= UI.WaitForObject(oR_Profile.txtBillReadyNotifications, 10);
			if(bBillAlert && bBillThreshold && bBillReady )
			{
				Report.TestPoint(testContext.getName(),
						"Validate Bill alert, Bill Threshold, Bill Ready notifications are present",
						"true", String.valueOf(bBillReady), true);
			}
			
			//Clicking on edit and validating notifications
		
			oR_Profile.lnkEditBillingNotificationsNew.click();
			
			if(UI.WaitForObject(oR_Profile.btnSaveBillingNotificationsDisable, 10))
			{
				Report.ValidationPoint(testContext.getName(),
						"Validate Save button is Inactive until user made any changes",
						"Not Enabled", "Not Enabled", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(),
						"Validate Save button is Inactive until user made any changes",
						"Not Enabled", "Enabled", true);
			}
			
			//changing notifications to OFF
			for(int i=1;i<=3;i++)
			{
			WebElement Notification= lDriver.findElement(By.xpath("//label[@id='but"+i+"Label']"));
			Notification.click();
			}
			
			

			WebElement checkBox1 = lDriver.findElement(By.xpath("//input[contains(@id,'billAlertChkBox')]"));
			checkBox1.click();
			Thread.sleep(2000);
			WebElement checkBox2 = lDriver.findElement(By.xpath("//input[contains(@id,'billThreshChkBox')]"));
			checkBox2.click();
			Thread.sleep(2000);
			WebElement checkBox3 = lDriver.findElement(By.xpath("//input[contains(@id,'billBrnChkBox')]"));
			checkBox3.click();
			Thread.sleep(2000);
			
			//boolean bNotifcation= UI.WaitForObject(Notification, 10);
			
			if(UI.WaitForObject(oR_Profile.btnSaveBillingNotificationsActive, 10))
			{
				Report.ValidationPoint(testContext.getName(),
						"Validate Save button is Active after user made any changes",
						"true", "true", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(),
						"Validate Save button is Active after user made any changes",
						"true", "false", true);
			}
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - VerifyEditBillNotificationPage
	 * Description- 
	 * Parameters - 
	 * Date created -13th July 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//PRF15604
	public static void VerifyEditBillNotificationPage(final ITestContext testContext)throws Exception 
	{

		try 
		{
			// Navigate to my profile page on global navigation
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav, null);
			
			//Verify Bill Ready Section
			Boolean bBillReady = UI.WaitForObject(lDriver.findElement(By.xpath("//h3[contains(text(),'Bill ready notifications')]")), 5);
			Report.ValidationPoint(testContext.getName(),"Validate Bill Ready Section is displayed", "true",String.valueOf(bBillReady), true);
			
			//Verify Payment Received Notification
			Boolean bPaymentReceived = UI.WaitForObject(oR_Profile.txtPaymentReceived, 5);
			Report.ValidationPoint(testContext.getName(),"Validate Payment Received Notification is displayed", "true",String.valueOf(bPaymentReceived), true);
			
			//Verify Bill Ready Notification
			Boolean bBilReadyStatus = UI.WaitForObject(oR_Profile.txtBillReadyStatus, 5);
			Report.ValidationPoint(testContext.getName(),"Validate Payment Received Notification Status is ON", "true",String.valueOf(bBilReadyStatus), true);
			
			//Verify Payment Received Notification Status
			Boolean bPaymentStatus = UI.WaitForObject(oR_Profile.txtPaymentStatus, 5);
			Report.ValidationPoint(testContext.getName(),"Validate Payment Received Notification Status is ON", "true",String.valueOf(bPaymentStatus), true);
			
			//Verify Edit Link for Bill Notification 
			Boolean bEdit = UI.WaitForObject(oR_Profile.lnkEditBillingNotifications, 5);
			Report.ValidationPoint(testContext.getName(),"Validate Edit Link is displayed for Bill Notification", "true",String.valueOf(bEdit), true);
			oR_Profile.lnkEditBillingNotifications.click();
			
			/*Verify The following elements are getting displayed under "Edit bill notification"section:
				BAU page title
				Bill Ready Notification section
				Payment Received Notification section
				Bill Threshold Notification section
				Pay Bill Reminder Notification section
				BAU Cancel CTA 
				BAU Save CTA
*/
			Boolean bHeading = UI.WaitForObject(oR_Profile.txtEditBilling, 5);
			Report.ValidationPoint(testContext.getName(),"Validate Edit Billing Notification heading is displayed", "true",String.valueOf(bHeading), true);
			
			//Verify Bill Ready Notification section
			Boolean bReady = UI.WaitForObject(oR_Profile.txtBillReadyEdit, 5);
			Report.ValidationPoint(testContext.getName(),"Validate Bill ready section is displayed", "true",String.valueOf(bReady), true);
		
			//Verify Payment Received Notification section
		 WebElement Payment = lDriver.findElement(By.xpath("//div/h3[contains(text(),'Payment received notification')]"));
		  
		Boolean bPayment = UI.WaitForObject(Payment, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Payment Received section is displayed", "true",String.valueOf(bPayment), true);
			
		//Verify Bill Threshold Notification section	
		WebElement Reminder = lDriver.findElement(By.xpath("//div/h3[contains(text(),'Payment received notification')]"));
		  
		Boolean bReminder = UI.WaitForObject(Reminder, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Pay Bill Reminder Notification section is displayed", "true",String.valueOf(bReminder), true);
		
		//Verify Cancel CTA 
		WebElement Cancel = lDriver.findElement(By.xpath("//a[@linkname='Cancel']"));
		Boolean bCancel = UI.WaitForObject(Cancel, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Cancel link is displayed", "true",String.valueOf(bCancel), true);
		
		//Verify Save CTA
		WebElement Save = lDriver.findElement(By.id("save"));
		Boolean bSave = UI.WaitForObject(Save, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Save button is displayed", "true",String.valueOf(bSave), true);
		
		//Validate for " Bill Ready Notification "section
		
		WebElement Toggle = lDriver.findElement(By.xpath("//label[contains(@class,'disableSliderIE')]"));
		
		if(Toggle.isDisplayed())
		{
			Report.ValidationPoint(testContext.getName(), "Validate Toggle is disabled", "true", "true", true);
		}
		else
		{
			Report.ValidationPoint(testContext.getName(), "Validate Toggle is disabled", "true", "false", true);
		}
		
		//Verify Email Notification is always selected and disabled
		WebElement Email = lDriver.findElement(By.xpath("//div[@id='uniform-billReadyEmail']"));
		
		Boolean bEmail = UI.WaitForObject(Email, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Email Notification is always selected and disabled", "true",String.valueOf(bEmail), true);
		
		//Verify Bill Threshold Notification section
		WebElement Threshold = lDriver.findElement(By.xpath("//h3[contains(text(),'Bill threshold notification')]"));
		
		Boolean bThreshold = UI.WaitForObject(Threshold, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Bill Threshold Notification is displayed", "true",String.valueOf(bThreshold), true);
		
		//Verify status
		WebElement ThresholdStatus = lDriver.findElement(By.xpath("//label[contains(@class,'UNchecked')]/span[contains(@class,'off')]/parent::label[@id='billThresholdToggleLabel']"));
		
		Boolean bStatusthresh = UI.WaitForObject(ThresholdStatus, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Bill Threshold Notification is OFF", "true",String.valueOf(bStatusthresh), true);
		
		//Change to ON
		ThresholdStatus.click();
		Thread.sleep(5000);
		
		WebElement alert1 = lDriver.findElement(By.xpath("//label[@for='billThresholdEmail' and contains(text(),'Receive email')]"));
		Boolean balert1 = UI.WaitForObject(alert1, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Receive email alert is displayed", "true",String.valueOf(balert1), true);
		
		
		WebElement alert2 = lDriver.findElement(By.xpath("//label[@for='billThresholdNotificationCkbox' and contains(text(),'Text notification')]"));
		Boolean balert2 = UI.WaitForObject(alert2, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Text notification alert is displayed", "true",String.valueOf(balert2), true);
		
		//Verify Pay Bill Reminder notification section
		WebElement PayReminder = lDriver.findElement(By.xpath("//h3[contains(text(),'Pay Bill Reminder Notification')]"));
		
		Boolean bPayReminder = UI.WaitForObject(PayReminder, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Pay Bill Reminder Notification is displayed", "true",String.valueOf(bPayReminder), true);
		
		//Verify status
		WebElement ReminderStatus = lDriver.findElement(By.xpath("//label[contains(@class,'UNchecked')]/span[contains(@class,'off')]/parent::label[@id='paybillToggleLabel']"));
		
		Boolean bReminderStatus = UI.WaitForObject(ReminderStatus, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Pay Bill Reminder notification is OFF", "true",String.valueOf(bReminderStatus), true);
		
		//Change to ON
		ReminderStatus.click();
		Thread.sleep(5000);
		
		WebElement Remindalert1 = lDriver.findElement(By.xpath("//label[@for='payBillEmail' and contains(text(),'Receive email')]"));
		Boolean bRemindalert1 = UI.WaitForObject(Remindalert1, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Receive email alert is displayed", "true",String.valueOf(bRemindalert1), true);
		
		
		WebElement Remindalert2 = lDriver.findElement(By.xpath("//label[@for='payBillNotificationCkbox' and contains(text(),'Text notification')]"));
		Boolean bRemindalert2 = UI.WaitForObject(Remindalert2, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Text notification alert is displayed", "true",String.valueOf(bRemindalert2), true);
		
		
		/*WebElement Remindalert3 = lDriver.findElement(By.xpath("//label[@for='payBillAoPhone' and contains(text(),'Automated Call to Your Phone')]"));
		Boolean bRemindalert3 = UI.WaitForObject(Remindalert3, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Automated Call alert is displayed", "true",String.valueOf(bRemindalert3), true);*/
		
		WebElement Remindalert3cont = lDriver.findElement(By.xpath("//p[contains(text(),'You will receive an automated call')]"));
		Boolean bRemindalert3cont = UI.WaitForObject(Remindalert3cont, 5);
		Report.ValidationPoint(testContext.getName(),"Validate Automated Call alert is displayed", "true",String.valueOf(bRemindalert3cont), true);
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - VerifySendAnORCOptions()
	 * Description - This will validate what send options are available for a account to send an ORC.
 	 * Test Case -  PRF08024
	 * Parameters - None
	 * Date created - 27th July 2015
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifySendAnORCOptions(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
			OR_AccountRegistration oR_AccountRegistration = PageFactory.initElements(lDriver, OR_AccountRegistration.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			//Parameter declerations
			String sCTN = IO.GetParamVal(sTestParams, "CTN");
			String sZipCode = IO.GetParamVal(sTestParams, "Zip_Code");
			String sPasscode = IO.GetParamVal(sTestParams, "Passcode"); 
			
			boolean bRegisterToday = UI.WaitForObject(oR_Login.lnkRegisterToday, UI.iObjTimeOut, lDriver);
			//boolean bCreateID= UI.WaitForObject(oR_Login.lnkCreateID, UI.iObjTimeOut, lDriver);
			//Report.ValidationPoint(testContext.getName(), "Verify that Register link is displayed in login page", "true",String.valueOf(bRegisterToday), true);
			Report.ValidationPoint(testContext.getName(), "Verify that Create ID link/Register today link is displayed in login page", "true",String.valueOf(bRegisterToday), true);
			oR_Login.lnkRegisterToday.click();
			//oR_Login.lnkCreateID.click();
			//Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Register Today' link");
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Create ID'/'Register Today' link");
			
			//Verify account selection page is displayed
			boolean bAccSel = UI.WaitForObject(oR_AccountRegistration.txtSelectAccountHeading, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that the page is redirected to Account selection page", "true",String.valueOf(bAccSel), true);
			//Select Wireless from dropdown
			new Actions(lDriver).moveToElement(oR_AccountRegistration.txtWireless).click().perform();
			Report.OperationPoint(testContext.getName(), "Operational - Selected option for 'Wireless' from Account dropdown");		
			//Enter WirelessNumber (CTN) and ZipCode
			boolean bCTN = UI.WaitForObject(oR_AccountRegistration.edtWirelessNumber, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that edit box to insert Wireless number is displayed", "true", String.valueOf(bCTN), true);
			oR_AccountRegistration.edtWirelessNumber.sendKeys(sCTN);
			Report.OperationPoint(testContext.getName(), "Operational - entered Wireless Number(CTN)");

			boolean bZipCode = UI.WaitForObject(oR_AccountRegistration.edtZipCode, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate that edit box to enter ZipCode is displayed", "true", String.valueOf(bZipCode), true);
			oR_AccountRegistration.edtZipCode.sendKeys(sZipCode);
			Report.OperationPoint(testContext.getName(), "Operational - entered ZipCode");
			
			oR_AccountRegistration.btnNext2.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Next button");
			
			//Handle Passcode page
			Thread.sleep(5000);
			try
			{
				if(oR_AccountRegistration.edtPassocde.isDisplayed())
				{
					
						if(sPasscode != null)
						{
							oR_AccountRegistration.edtPassocde.sendKeys(sPasscode);
						}
						else
						{
							oR_AccountRegistration.edtPassocde.sendKeys("1234");
						}
						
						if(UI.WaitForObject(oR_AccountRegistration.btnNext4, UI.iObjTimeOut, lDriver))
						{
							oR_AccountRegistration.btnNext4.click();
						}
						Report.OperationPoint(testContext.getName(), "Pass code entered successfully and navigating to next page");
					
				}else
				{
					Report.ValidationPoint(testContext.getName(),"Validation for Passcode page done!", String.valueOf(oR_Login.edtPassocde.isDisplayed()),String.valueOf(oR_Login.edtPassocde.isDisplayed()), true);
				}
				
			}catch(Exception Ee)
			{
				Report.OperationPoint(testContext.getName(), "No PASSCODE page is displayed");
			}
			
			//Verify receive OCR page is displayed
			boolean bOCRpage = UI.WaitForObject(oR_AccountRegistration.txtOnlineRegistrationCode, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that page to Receive Online Registration Code is displayed", "true", String.valueOf(bOCRpage), true);
			
			//Verify Next button is disabled when no radio button is selected
			//String sAltValue = oR_AccountRegistration.btnNext3.getAttribute("alt").toLowerCase();
			//if(oR_AccountRegistration.btnNext3.isEnabled() || oR_AccountRegistration.btnNext3.isDisplayed() )
			try
			{
				if(UI.WaitForObject(oR_AccountRegistration.btnNextButtonDisabled, UI.iObjTimeOut, lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Verify Next button is disabled when no radio buttons are selected", "true","true", true);
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify Next button is disabled when no radio buttons are selected", "true","false", true);
				}
			}catch(Exception Ee)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Next button is disabled when no radio buttons are selected", "true","false", true);
			}
			
			//Select radio button for already requested Online Registration Code, enter invalid code and verify an error msg is displayed
			new Actions(lDriver).moveToElement(oR_AccountRegistration.rdoAvailableORC).click().build().perform();
			Report.OperationPoint(testContext.getName(), "Operational - Radio button selected for available ORC option");
			oR_AccountRegistration.edtEnterORC.sendKeys("12345678");
			Report.OperationPoint(testContext.getName(), "Operational - Entered 12345678 as invalid ORC");
			oR_AccountRegistration.btnNext3.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Next button");
			Thread.sleep(5000);
			//Verify an Error msg is displayed
			boolean bORCError = UI.WaitForObject(oR_AccountRegistration.txtORCErrorMsg, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that an Error message is displayed when incorrect ORC is entered", "true", String.valueOf(bORCError), true);
			
			//Select radio button for I need an Online Registration Code
			new Actions(lDriver).moveToElement(oR_AccountRegistration.rdoNeedORC).click().build().perform();
			Report.OperationPoint(testContext.getName(), "Operational - Radio button selected for Need an ORC");
			try
			{
				//Click on more options link
				UI.WaitForObject(oR_AccountRegistration.lnkMoreOptions, UI.iObjTimeOut, lDriver);
				new Actions(lDriver).moveToElement(oR_AccountRegistration.lnkMoreOptions).click().build().perform();
				Report.OperationPoint(testContext.getName(), "Operational - clicked on More options link to expand the list of options");
				
			}catch(Exception Ee)
			{
				Report.OperationPoint(testContext.getName(), " **More options link to expand is not present");
			}
			//Verify radio options for SMS, Email and US Post is displayed
			boolean bSMS = UI.WaitForObject(oR_AccountRegistration.txtOCRSMSOption, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that radio option for SMS is displayed", "true", String.valueOf(bSMS), true);
			boolean bEmail = UI.WaitForObject(oR_AccountRegistration.txtOCREmailOption, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that radio option for Email is displayed", "true", String.valueOf(bEmail), true);
			boolean bUSPost = UI.WaitForObject(oR_AccountRegistration.txtOCRUSPostOption, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that radio option for US Post is displayed", "true", String.valueOf(bUSPost), true);
			
			//Verify Next button is disabled when no radio button is selected
			//String sAltValue2 = oR_AccountRegistration.btnNextEnabledOrDisabled.getAttribute("alt").toLowerCase();
			//if(sAltValue2.contains("disabled"))
			/*if(oR_AccountRegistration.btnNext3.isEnabled() || oR_AccountRegistration.btnNext3.isDisplayed() )
			{
				Report.ValidationPoint(testContext.getName(), "Verify Next button is disabled when no radio buttons are selected", "true","true", true);
			}else
			{
				Report.ValidationPoint(testContext.getName(), "Verify Next button is disabled when no radio buttons are selected", "true","false", true);
			}*/
			
			try
			{
				if(UI.WaitForObject(oR_AccountRegistration.btnNextButtonDisabled, UI.iObjTimeOut, lDriver))
				{
					Report.ValidationPoint(testContext.getName(), "Verify Next button is disabled when no radio buttons are selected", "true","true", true);
				}else
				{
					Report.ValidationPoint(testContext.getName(), "Verify Next button is disabled when no radio buttons are selected", "true","false", true);
				}
			}catch(Exception Ee)
			{
				Report.ValidationPoint(testContext.getName(), "Verify Next button is disabled when no radio buttons are selected", "true","false", true);
			}
			
				//Select SMS and verify it redirected to a page where you can enter the received ORC 
				oR_AccountRegistration.rdoORCSendBySMS.click();
				Report.OperationPoint(testContext.getName(), "Operational - clicked on radio button for SMS");
				oR_AccountRegistration.btnNext3.click();
				Report.OperationPoint(testContext.getName(), "Operational - clicked on Next");	
				//Verify User is redirected to Enter ORC page
				boolean bEnterORCPage = UI.WaitForObject(oR_AccountRegistration.txtProceedWithOnlineAccReg, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that user is redirected to Enter ORC page", "true", String.valueOf(bEnterORCPage), true);
				//verify edit box to enter ORC is displayed
				boolean bORCEditBox = UI.WaitForObject(oR_AccountRegistration.txtEnterORCSection, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that edit box to enter ORC code is displayed", "true", String.valueOf(bORCEditBox), true);
				//Navigate back to previous page
				oR_AccountRegistration.btnBack.click();
				Report.OperationPoint(testContext.getName(), "Operational - clicked on Back button");
			
			Thread.sleep(5000);
			//Verify receive OCR page is displayed
			boolean bOCRpage1 = UI.WaitForObject(oR_AccountRegistration.txtOnlineRegistrationCode, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that page to Receive Online Registration Code is displayed", "true", String.valueOf(bOCRpage1), true);
			//Select radio button for I need an Online Registration Code
			new Actions(lDriver).moveToElement(oR_AccountRegistration.rdoNeedORC).click().build().perform();
			Report.OperationPoint(testContext.getName(), "Operational - Radio button selected for Need an ORC");
			
				//Select Email and verify it redirected to a page where you can enter the received ORC 
				oR_AccountRegistration.rdoORCSendByEmail.click();
				Report.OperationPoint(testContext.getName(), "Operational - clicked on radio button for Email");
				oR_AccountRegistration.btnNext3.click();
				Report.OperationPoint(testContext.getName(), "Operational - clicked on Next");	
				//Verify User is redirected to Enter ORC page
				boolean bEnterORCPage2 = UI.WaitForObject(oR_AccountRegistration.txtProceedWithOnlineAccReg, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that user is redirected to Enter ORC page", "true", String.valueOf(bEnterORCPage2), true);
				//verify edit box to enter ORC is displayed
				boolean bORCEditBox2 = UI.WaitForObject(oR_AccountRegistration.txtEnterORCSection, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that edit box to enter ORC code is displayed", "true", String.valueOf(bORCEditBox2), true);
				//Navigate back to previous page
				oR_AccountRegistration.btnBack.click();
				Report.OperationPoint(testContext.getName(), "Operational - clicked on Back button");
			
			Thread.sleep(5000);
			//Verify receive OCR page is displayed
			boolean bOCRpage2 = UI.WaitForObject(oR_AccountRegistration.txtOnlineRegistrationCode, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that page to Receive Online Registration Code is displayed", "true", String.valueOf(bOCRpage2), true);
			//Select radio button for I need an Online Registration Code
			new Actions(lDriver).moveToElement(oR_AccountRegistration.rdoNeedORC).click().build().perform();
			Report.OperationPoint(testContext.getName(), "Operational - Radio button selected for Need an ORC");
			try
			{
				//Click on more options link
				UI.WaitForObject(oR_AccountRegistration.lnkMoreOptions, UI.iObjTimeOut, lDriver);
				new Actions(lDriver).moveToElement(oR_AccountRegistration.lnkMoreOptions).click().build().perform();
				Report.OperationPoint(testContext.getName(), "Operational - clicked on More options link to expand the list of options");
				
			}catch(Exception Ee)
			{
				Report.OperationPoint(testContext.getName(), " **More options link to expand is not present");
			}
				//Select US mail and verify it redirected to a page where you wil be notified by a message to come back again
				oR_AccountRegistration.rdoORCSendByUSMail.click();
				Report.OperationPoint(testContext.getName(), "Operational - clicked on radio button for US Mail");
				oR_AccountRegistration.btnNext3.click();
				Report.OperationPoint(testContext.getName(), "Operational - clicked on Next");	
				//Verify User is redirected to Enter ORC page
				boolean bAccVer = UI.WaitForObject(oR_AccountRegistration.txtAccountVerification, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that user is redirected to Account verification page", "true", String.valueOf(bAccVer), true);
				//Verify User will be sent to a ORC sent USPS page indicating that the ORC has been sent to the address selected and to come back later to register and select the already have an ORC option
				boolean bUSMailMsg = UI.WaitForObject(oR_AccountRegistration.txtUSMailMessage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that a message is displayed saying that User will be sent to a ORC sent USPS page indicating that the ORC has been sent to the address selected and to come back later to register and select the already have an ORC option", "true", String.valueOf(bUSMailMsg), true);
				
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	/**************************************************************
	 * Function Name - ValidateManagePlentiCTA
	 * Description- 
	 * Parameters - 
	 * Date created -12th-August-2015
	 * Developed by - Gautham
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//PRF15476
	public static void ValidateManagePlentiCTA(final ITestContext testContext)throws Exception 
	{

		try 
		{
			// Navigate to my profile page on global navigation
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			
			//Select profile from global navigation
			if(UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav, 120, lDriver))
			{
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav, oR_AccountOverview.lnkUpdateMyProfileTertNav, lDriver);
				
				Actions driver = new Actions(lDriver);
				//Validate " Manage Plenti"   link
				if(UI.WaitForObject(oR_Profile.lnkManagePlenti,120, lDriver))
				{
					driver.moveToElement(oR_Profile.lnkManagePlenti).build().perform();
					Report.ValidationPoint(testContext.getName(), "Validate Manage Plenti Link is displayed", "true", String.valueOf(oR_Profile.lnkManagePlenti.isDisplayed()), true);
					
					//validate required details
					WebElement PlentiNumber= lDriver.findElement(By.xpath("//div//label[contains(text(),'Plenti card number ')]"));
					if(PlentiNumber.isDisplayed())
					{
						Report.ValidationPoint(testContext.getName(), "Validate Manage Plenti Id is displayed as expected", "true", String.valueOf(PlentiNumber.isDisplayed()), true);
						
						//Click on att access id profile
						if(UI.WaitForObject(oR_Profile.lnkAttAccessIdProfile, 120, lDriver))
						{
							oR_Profile.lnkAttAccessIdProfile.click();
							Report.OperationPoint(testContext.getName(), "clicked on ATT Access ID");
							if(UI.WaitForObject(oR_Profile.lnkManagePlenti,120, lDriver))
							{
								driver.moveToElement(oR_Profile.lnkManagePlenti).build().perform();
								Report.ValidationPoint(testContext.getName(), "Validate The Manage Plenti CTA is the last CTA in the list", "true", String.valueOf(oR_Profile.lnkManagePlenti.isDisplayed()), true);
								oR_Profile.lnkManagePlenti.click();
								if(UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'Manage Plenti')]")), 10, lDriver));
								{
									Report.ValidationPoint(testContext.getName(), "Validate Manage Plenti page is displayed as expected", "true", "true", true);
								}
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate Manage Plenti Link is displayed as expected", "true", String.valueOf(oR_Profile.lnkManagePlenti.isDisplayed()), true);
							}
						}
						else
						{
							Report.ValidationPoint(testContext.getName(), "Validate ATT Access ID Link is displayed", "true", String.valueOf(oR_Profile.lnkAttAccessIdProfile.isDisplayed()), true);
						}
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate Manage Plenti Id is displayed as expected", "true", String.valueOf(PlentiNumber.isDisplayed()), true);
					}	
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Manage Plenti Link is displayed", "true", String.valueOf(oR_Profile.lnkManagePlenti.isDisplayed()), true);
				}
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Validating Profile link is present in Global Navigation", "true", "Failed to validate", true);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	/**************************************************************
	* Function Name -  VerifyIDMAndDashboardfinal
	* Description- This function validates the flow of Change plan 
	* Parameters - 
	* Date created -26th Aug 15
    * Developed by - Krutika Kamdi
	* Last Modified By - Gautham
	* Last Modified Date - 28th Aug 15
	* Last Modified By - Krutika
	* Last Modified Date - 6th Feb 2016
	***************************************************************/
//PRF07700_C_02 //PRF07704_C_02
				public static void VerifyIDMAndDashboardfinal (ITestContext testContext)throws Exception
				{
					try
					{
						WebDriver lDriver = UI.getDriver(testContext.getName());   
						
						OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
						OR_ViewOrChangeRatePlan oR_ViewOrChangeRatePlan = PageFactory.initElements(lDriver, OR_ViewOrChangeRatePlan.class);
						
						
						//Validate and click link change plan
						UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkChangeMyPlanTertiaryNav);
							
							//Validate View or Change rate plan page
							//String sHeading="View Or Change Rate Plan";
							if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtViewOrChangeRatePlan, 100))
							{
								Report.ValidationPoint(testContext.getName(), "Validate View or Change rate plan page", "Navigated to View or Change rate plan page", "Navigated to View or Change rate plan page", true);
								//Validate Current rate plan section
								if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtCurrentRatePlan, 20))
								{
									Report.ValidationPoint(testContext.getName(), "Validate Current rate plan section", "Current rate plan section is present", "Current rate plan section is present", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate Current rate plan section", "Current rate plan section is present", "Current rate plan section is NOTpresent", true);
								}
								
								//Validate Current Rate Plan is displayed underneath the Account Number
								//Retrieving the locations of Current rate plan section & Account
								int iLocCurrentPlan = oR_ViewOrChangeRatePlan.txtCurrentRatePlan.getLocation().getY();
								Report.OperationPoint(testContext.getName()," Operation - Retrieving the locations of Current rate plan. The Y-axis location is "+iLocCurrentPlan);

								
								if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtBAN,1))
								{
									int iBan = oR_ViewOrChangeRatePlan.txtBAN.getLocation().getY();
									Report.OperationPoint(testContext.getName()," Operation - Retrieving the locations of Account Number. The Y-axis location is "+iBan);
									if(iLocCurrentPlan>iBan)
									{
										Report.ValidationPoint(testContext.getName(), "Validate Current Rate Plan is displayed underneath the BAN_Nickname", "Current Rate Plan is displayed underneath the BAN_Nickname", "Current Rate Plan is displayed underneath the BAN_Nickname", true);
									}
									else
									{
										Report.ValidationPoint(testContext.getName(), "Validate Current Rate Plan is displayed underneath the BAN_Nickname", "Current Rate Plan is displayed underneath the BAN_Nickname", "Current Rate Plan is NOTdisplayed underneath the BAN_Nickname", true);
									}
								}
								
								//Validate Nation Plan and Nation with Canada Plans is displayed on Change Rate Plan page
								//Validate Nation Plan is displayed on Change Rate Plan page
								if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtNationPlans, 10))
								{
									Report.ValidationPoint(testContext.getName(), "Validate Nation Plan is displayed on Change Rate Plan page", "Nation Plan is displayed on Change Rate Plan page", "Nation Plan is displayed on Change Rate Plan page", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate Nation Plan is displayed on Change Rate Plan page", "Nation Plan is displayed on Change Rate Plan page", "Nation Plan is NOTdisplayed on Change Rate Plan page", true);
								}
								//Validate Nation with Canada Plans is displayed on Change Rate Plan page
								if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtNationWithCanadaPlans, 10))
								{
									Report.ValidationPoint(testContext.getName(), "Validate Nation with Canada Plans is displayed on Change Rate Plan page", "Nation with Canada Plans is displayed on Change Rate Plan page", "Nation with Canada Plans is displayed on Change Rate Plan page", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate Nation with Canada Plans is displayed on Change Rate Plan page", "Nation with Canada Plans is displayed on Change Rate Plan page", "Nation with Canada Plans is NOTdisplayed on Change Rate Plan page", true);
								}
								//Validate Nation Plan is displayed above Nation with Canada Plans on Change Rate Plan page
								//Retrieving the locations of Nation Plan and Nation with Canada Plans
								int iLocNationPlan = oR_ViewOrChangeRatePlan.txtNationPlans.getLocation().getY();
								int iLocNationWithCanada = oR_ViewOrChangeRatePlan.txtNationWithCanadaPlans.getLocation().getY();
								Report.OperationPoint(testContext.getName()," Operation - Retrieving the locations of Nation plan. The Y-axis location is "+iLocNationPlan);
								Report.OperationPoint(testContext.getName()," Operation - Retrieving the locations of Nation with Canada plan. The Y-axis location is "+iLocNationWithCanada);					
								if(iLocNationWithCanada>iLocNationPlan)
								{
									Report.ValidationPoint(testContext.getName(), "Validate Nation Plan is displayed above Nation with Canada Plans on Change Rate Plan page", "Nation Plan is displayed above Nation with Canada Plans on Change Rate Plan page", "Nation Plan is displayed above Nation with Canada Plans on Change Rate Plan page", true);
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate Nation Plan is displayed above Nation with Canada Plans on Change Rate Plan page", "Nation Plan is displayed above Nation with Canada Plans on Change Rate Plan page", "Nation Plan is NOTdisplayed above Nation with Canada Plans on Change Rate Plan page", true);
								}
								
								//Validate columns of data that will display for the Nation Plans table should contains : 
								//Minutes, Monthly cost, Rollover, Unlimited Night/Wknd Minutes, Unlimited Mobile to Mobile and Additional Details
								if(UI.WaitForObject(oR_ViewOrChangeRatePlan.tblNationPlans, 10))
								{
									Report.ValidationPoint(testContext.getName(), "Validate the Nation plans table","Nation plans table is present", "Nation plans table is present", true);
									List<WebElement> tblNationPlans = lDriver.findElements(By.xpath("//table[@id='sortable_table']//th"));
									System.out.println(tblNationPlans.size());
									String [] sColumnHeading = new String[tblNationPlans.size()];
									for(int i=0; i<tblNationPlans.size(); i++)
									{
										sColumnHeading[i]=tblNationPlans.get(i).getText();
									}
									//Retrieving the column headings
									for(int j=0;j<tblNationPlans.size();j++)
									{
										if(sColumnHeading[j].length()>1)
										{
											//Report.ValidationPoint(testContext.getName(), "Validate the headings of the table", "Heading :"+sColumnHeading[j]+" is present", "Required heading are present", true);
											Report.OperationPoint(testContext.getName()," Operation - Column heading: "+sColumnHeading[j]);
										}
									}
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate the Nation plans table","Nation plans table is present", "Nation plans table is NOTpresent", true);
								}
								//Validate columns of data that will display for the Nation with Canada Plans table should contains : 
								//Minutes, Monthly cost, Rollover, Unlimited Night/Wknd Minutes, Unlimited Mobile to Mobile and Additional Details
								
								if(UI.WaitForObject(oR_ViewOrChangeRatePlan.tblNationWithCanada, 10))
								{
									Report.ValidationPoint(testContext.getName(), "Validate Nation with Canada plans table","Nation with Canada plans table is present", "Nation with Canada plans table is present", true);
									List<WebElement> tblNationPlansCanada = lDriver.findElements(By.xpath("//table[@id='nationcanada_sortable_table']//th"));
									String [] sColumnHeadingCanada = new String[tblNationPlansCanada.size()];
									for(int i=0; i<tblNationPlansCanada.size(); i++)
									{
										sColumnHeadingCanada[i]=tblNationPlansCanada.get(i).getText();
									}
									//Retrieving the column headings
									for(int j=0;j<tblNationPlansCanada.size();j++)
									{
										if(sColumnHeadingCanada[j].length()>1)
										{
											Report.OperationPoint(testContext.getName()," Operation - Column heading: "+sColumnHeadingCanada[j]);
										}
									}
								}
								else
								{
									Report.ValidationPoint(testContext.getName(), "Validate Nation with Canada plans table", "Nation with Canada plans table", "Nation with Canada plans table NOT exist", true);
								}
								

								//Validate Step indicators

									if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtStepIndicator1, 1))
									{
										Report.ValidationPoint(testContext.getName(), "Validate Step Indicator", "Step Indicator is present", "Step Indicator is present", true);
										
										List <WebElement> lstStepIndicator = lDriver.findElements(By.xpath("//div[@id='StepIndicator']/div"));
										String [] sStepIndicator = new String [lstStepIndicator.size()];
										for(int i=0;i<lstStepIndicator.size();i++)
										{
											sStepIndicator[i] = lstStepIndicator.get(i).getText();
										}
										//Retrieving the values
										for(int i=0;i<lstStepIndicator.size();i++)
										{
											Report.OperationPoint(testContext.getName()," Operation - Step Indicator : "+sStepIndicator[i]);
										}
										
									}
									else
									{
										Report.ValidationPoint(testContext.getName(), "Validate Step Indicator", "Step Indicator is present", "Step Indicator is NOTpresent", true);
									}
								
										//Validate Step 1 indicator is greened
									//	String scolor = lDriver.findElementByXPath("id('StepIndicator')/div[1]").getCssValue("color");
									String scolor =  lDriver.findElement(By.xpath("//*[@id='secondary-content']//span[contains(text(),'Select a Plan')]")).getCssValue("color"); 	
									if(scolor.contains("rgba(27, 126, 40, 1)"))
										{
											Report.ValidationPoint(testContext.getName(), "The 1st step indicator is highlighted in green colour", "true","true", true);
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "The 1st step indicator is not highlighted in green colour", "true","false", true);
										}
									
								List<WebElement> SelectBtns = lDriver.findElements(By.xpath("//img[@src='/olam/English/brand30/bt/btn_select_plan.gif']"));
									Report.ValidationPoint(testContext.getName(), "Validate Select a plan button", "Select a plan button is present", "Select a plan button is present", true);
									//Click on the button
									Report.OperationPoint(testContext.getName()," Operation - Click on the button 'Select a plan'");
									//oR_ViewOrChangeRatePlan.btnSelect2.click();
									SelectBtns.get(0).click();
									//Alert is displayed after clicking
									Thread.sleep(5000);
									
			/*				      //handle alert
									 Alert alert = lDriver.switchTo().alert();
									 String sAlertText= lDriver.switchTo().alert().getText();
									 System.out.println(sAlertText);
									 if(!sAlertText.isEmpty()){
									 alert.accept();}
									*/

									//Validate Select Effective Date title
									if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtSelectEffectiveDate,120))
									{
		
										//Validate Step 2 indicator is greened
											scolor = lDriver.findElement(By.xpath("//*[@id='secondary-content']//span[contains(text(),'Select Effective Date')]")).getCssValue("color");
											System.out.println(scolor);
											System.out.println(scolor);
											if(scolor.contains("rgba(27, 126, 40, 1)"))
											{
												Report.ValidationPoint(testContext.getName(), "The 2nd step indicator is highlighted in green colour", "true","true", true);
											}
											else
											{
												Report.ValidationPoint(testContext.getName(), "The 2nd step indicator is not highlighted in green colour", "true","false", true);
											}
										Report.ValidationPoint(testContext.getName(), "Validate Select Effective Date title", "Select Effective Date title is present", "Select Effective Date title is present", true);
										//Validate radio button and select make effective today
										if(oR_ViewOrChangeRatePlan.radEffdate.isEnabled())
										{
											Report.ValidationPoint(testContext.getName(), "Validate radio button make effective today", "radio button make effective today is present", "radio button make effective today is present", true);
											//Selecting the radio button
											Report.OperationPoint(testContext.getName()," Operation - Selecting radio button");
											oR_ViewOrChangeRatePlan.radEffdate.click();
											//oR_ViewOrChangeRatePlan.radEffdate.submit();
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Validate radio button make effective today", "radio button make effective today is present", "radio button make effective today is NOTpresent", true);
										}
										//Clicking on button next
										Report.OperationPoint(testContext.getName()," Operation - Clicking on button next");
										oR_ViewOrChangeRatePlan.btnNext.click();
										
										//Change Rate Plan Conflict
/*										WebElement btnAccept = lDriver.findElement(By.xpath("//img[@src='/olam/English/brand30/bt/bt_accept.png']"));
										btnAccept.click();*/
										
										//Validate Review & confirm title
										if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtReviewAndConfirm, 80))
										{
											Report.ValidationPoint(testContext.getName(), "Validate Review & confirm title", "successfully navigated to Review & confirm page and the title is displayed", "successfully navigated to Review & confirm page and the title is displayed", true);
											
											//Click on Submit button
											Report.OperationPoint(testContext.getName()," Operation - Clicking on submit next");
											oR_ViewOrChangeRatePlan.btnSubmit.click();
											Thread.sleep(3000);
											
											
											
											//Validate Confirmation page
											if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtConfirmation, 50))
											{
												Report.ValidationPoint(testContext.getName(), "Validate Confirmation title", "successfully navigated to Confirmation and the title is displayed", "successfully navigated to Confirmation and the title is displayed", true);
												
												//Validate The first 3 step indicators are displayed with tick mark indicating their successful completion
												List <WebElement> lstStepIndicator = lDriver.findElements(By.xpath("//div[@id='StepIndicator']/div[@class='row-seam past']"));
												if(lstStepIndicator.size()==3)
												{
													Report.ValidationPoint(testContext.getName(), "Validate The first 3 step indicators are displayed with tick mark indicating their successful completion", 
															"The first 3 step indicators are displayed with tick mark indicating their successful completion", 
															"The first 3 step indicators are displayed with tick mark indicating their successful completion", true);
												}
												else
												{
													Report.ValidationPoint(testContext.getName(), "Validate The first 3 step indicators are displayed with tick mark indicating their successful completion",
															"The first 3 step indicators are displayed with tick mark indicating their successful completion", 
															"The first 3 step indicators are NOTdisplayed with tick mark indicating their successful completion", true);
												}
												
												//Validate note
												Boolean bNote = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtNote, UI.iObjTimeOut);
												Report.ValidationPoint(testContext.getName(), "Validate that a message is displayed regarding change rate plan", "true", String.valueOf(bNote), true);
												
												//Validate Primary Line
												Boolean bPrimaryLine = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtPrimaryLine, UI.iObjTimeOut);
												Report.ValidationPoint(testContext.getName(), "Validate Primary Line is displayed", "true", String.valueOf(bPrimaryLine), true);
												
												//Validate Effective date
												Boolean bEffectiveDate = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtEffectiveDate, UI.iObjTimeOut);
												Report.ValidationPoint(testContext.getName(), "Validate Effective date is displayed", "true", String.valueOf(bEffectiveDate), true);
												
												//Validate Monthly Cost
												Boolean bMonthlyCost = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtMonthlyCost, UI.iObjTimeOut);
												Report.ValidationPoint(testContext.getName(), "Validate Monthly cost is displayed", "true", String.valueOf(bMonthlyCost), true);
												
												//Validate Plan Details
												Boolean bPlanDetails = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtPlanDetails, UI.iObjTimeOut);
												Report.ValidationPoint(testContext.getName(), "Validate Plan Details is displayed", "true", String.valueOf(bPlanDetails), true);
												
												//Validate Retained Features 
												Boolean bRetained = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtRetainedFeatures, UI.iObjTimeOut);
												Report.ValidationPoint(testContext.getName(), "Validate Retained features is displayed", "true", String.valueOf(bRetained), true);
												
												//Validate Summary
												
												Boolean bSummary = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtSummary, UI.iObjTimeOut);
												Report.ValidationPoint(testContext.getName(), "Validate Summary is displayed", "true", String.valueOf(bSummary), true);
												
												//Validate Additional fees
												Boolean bAddFee = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtAdditionalFees, UI.iObjTimeOut);
												Report.ValidationPoint(testContext.getName(), "Validate Additional Fees is displayed", "true", String.valueOf(bAddFee), true);
												
												/*//Validate Terms and conditions
												Boolean bTerms = UI.WaitForObject(oR_ViewOrChangeRatePlan.txtTerms, UI.iObjTimeOut);
												Report.ValidationPoint(testContext.getName(), "Validate Terms and Conditions is displayed", "true", String.valueOf(bTerms), true);*/
												
												//Validate 'Add or change services'  is not displayed
												UI.VerifyElementNotPresent(oR_ViewOrChangeRatePlan.lnkAddService, "Add or change Services");
												
												//Validate 'Print this page'  is displayed
												
												Boolean bPrint = UI.WaitForObject(oR_ViewOrChangeRatePlan.lnkPrint, UI.iObjTimeOut);
												Report.ValidationPoint(testContext.getName(), "Validate Print link is displayed", "true", String.valueOf(bPrint), true);
												
											}
											else
											{
												Report.ValidationPoint(testContext.getName(), "Validate Confirmation title", "navigated to Confirmation and the title is displayed", "failed to navigate to confirmation page", true);
											}
										}
										else
										{
											Report.ValidationPoint(testContext.getName(), "Validate Review & confirm title", "navigated to Review & confirm page and the title is displayed", "failed to navigate to Review & confirm page", true);
										}
									}
									else
									{
										Report.ValidationPoint(testContext.getName(), "Validate Select Effective Date title", "Select Effective Date title is present", "Select Effective Date title is NOTpresent", true);
									}
							}
							else
							{
								Report.ValidationPoint(testContext.getName(), "Validate View or Change rate plan page", "Navigated to View or Change rate plan page", "failed to Navigate to View or Change rate plan page", true);
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
	* Function Name -  VerifyIDMAndDashboardfinal
	* Description- This function validates the flow of Change plan 
	* Parameters - 
	* Date created -31st Aug 15
	* Developed by - Krutika Kamdi
	* Last Modified By -
	* Last Modified Date - 
	***************************************************************/
	//PRF07700_C_01 //PRF07704_C_01
	public static void VerifyIDMAndDashboardWireless(ITestContext testContext)throws Exception
	{
	  try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
										
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_AccountInformation oR_AccountInformation = PageFactory.initElements(lDriver, OR_AccountInformation.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			OR_ATT_AccessID_Profile oR_ATT_AccessID_Profile = PageFactory.initElements(lDriver, OR_ATT_AccessID_Profile.class);
			
	
			//1. Manage Paperless billing (depend upon the condition whether customer enrolled or not enrolled for Paperless billing)
			Boolean bEnrollPaperless = UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBillingServiveSummary, 2);
			Boolean bPaperless = UI.WaitForObject(oR_AccountOverview.lnkManagePaperlessBilling, 2);
			Boolean bEnrollPaperless1 = UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBilling, 2);
			if(bEnrollPaperless || bPaperless || bEnrollPaperless1)
			{
				Report.ValidationPoint(testContext.getName(), "Validate enroll in Paperless billing link", "true", "true", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate enroll in Paperless billing link", "true", "false", true);
			}
			
			//2. Enroll in Auto pay
			Boolean bEnrollAutopay = UI.WaitForObject(oR_AccountOverview.lnkManageAutopayDasboard, 2);
			Boolean bManageAutopay = UI.WaitForObject(oR_AccountOverview.lnkManageAutoPay, 2);
			if(bEnrollAutopay || bManageAutopay)
			{
				Report.ValidationPoint(testContext.getName(), "Validate Enroll in Auto pay link", "true", "true", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate Enroll in Auto pay link", "true", "false", true);
			}
			
			//Validate Bill Details 
			boolean bBillDetails = UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate Bill details is displayed", "true", String.valueOf(bBillDetails), true);
			
			//Validate Change Plan
			boolean bChanngePlan = UI.WaitForObject(oR_AccountOverview.lnkChangePlanDasboardNew, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate Change Plan is displayed", "true", String.valueOf(bChanngePlan), true);
			
			//Validate All Usage link
			boolean bViewAllUsage = UI.WaitForObject(oR_AccountOverview.lnkViewAllUsage, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate View All usage link is displayed", "true", String.valueOf(bViewAllUsage), true);
			
			//Validate view all link in Messages section
			boolean bViewAll = UI.WaitForObject(oR_AccountOverview.lnkViewAll, 60);
			Report.ValidationPoint(testContext.getName(), "Validate View All link in AT&T Message section is displayed", "true", String.valueOf(bViewAll), true);
			
			//Validate Make a Payment button
			Boolean bButtonMAP = UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentInOverviewPage, 60);
		//Boolean bButtonMAP1 = UI.WaitForObject(oR_AccountOverview.btnMakeAPayment, 20);
			if(bButtonMAP)
			{
				Report.ValidationPoint(testContext.getName(), "Validate Make a Payment button","true","true",true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate Make a Payment button", "true","false", true);
			}
			
			//Validate Options under I Want to drop down
			Report.OperationPoint(testContext.getName(), "	Clicking on I want to");
			oR_AccountOverview.btnIWantTo.click();
			List<WebElement> options = lDriver.findElements(By.xpath("//button[@id='ddShortcut']//parent :: div//ul[contains(@class,'main')]/li"));
//			options.get(0).getText();
//			for (int i=0; i<(options.size()-1); i++){
//				oR_AccountOverview.btnIWantTo.sendKeys(Keys.ARROW_DOWN);
//				new Actions(lDriver).moveToElement(options.get(i)).click().perform(); 
//				Thread.sleep(3000);
//				Report.ValidationPoint(testContext.getName(), "Validate '"+options.get(i).getText()+"' is displayed ", "true", "true", true);
//				Thread.sleep(2000);
//				}
			
			for(WebElement e : options){
				WebElement linkDropDownMenu = e.findElement(By.tagName("span"));
				new Actions(lDriver).moveToElement(linkDropDownMenu).build().perform(); 
				Report.ValidationPoint(testContext.getName(), "Validate link "+ linkDropDownMenu.getText()+" is displayed along with all it's sub links", "true", "true", true);
			
			}
			
			//Validate Alert Section
			Boolean bAlertBtn = UI.WaitForObject(oR_AccountOverview.btnAlertBtn, UI.iObjTimeOut);
			Boolean bAlertSection = UI.WaitForObject(oR_AccountOverview.txtAlertSection, UI.iObjTimeOut);
			
			if(bAlertBtn.equals(true) || bAlertSection.equals(true))
			{
				if(bAlertBtn.equals(true)){
				  oR_AccountOverview.btnAlertBtn.click();
				  Thread.sleep(2000);
			   }
				
				Report.ValidationPoint(testContext.getName(), "Validate Alert Section is  displayed", "true", "true", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Validate Alert Section is  displayed", "true", "false", true);
			}
			
            //Validate Profile under global navigation bar
				
				Report.OperationPoint(testContext.getName()," Navigation  - Navigating to Profile");
				
				UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav, null);
				
				if(testContext.getName().contains("PRF07700_C_01"))
				{	
					
					//Validate the Account information Tab
				//i) Account names - Change and delete Passcode
				
				boolean bAccName = UI.WaitForObject(oR_AccountInformation.txtAccountNames, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Account Name is displayed", "true", String.valueOf(bAccName), true);
				
				boolean bPasscode = UI.WaitForObject(oR_AccountInformation.txtChangePasscode, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Change Passcode is displayed", "true", String.valueOf(bPasscode), true);
				
				boolean bConInfo = UI.WaitForObject(oR_AccountInformation.txtBillingContInfo, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Contact Information is displayed", "true", String.valueOf(bConInfo), true);
				
				boolean bNotif = UI.WaitForObject(oR_AccountInformation.txtBillingNotif, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Billing Notification is displayed", "true", String.valueOf(bNotif), true);
				
				boolean bPayOptions = UI.WaitForObject(oR_AccountInformation.txtPaymentOption, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Payment Options is displayed", "true", String.valueOf(bPayOptions), true);
				
				boolean bAutho = UI.WaitForObject(oR_AccountInformation.txtAuthoUsers, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Authorised User is displayed", "true", String.valueOf(bAutho), true);
				
				//Validate User Information Tab  
				boolean bUserInfo = UI.WaitForObject(oR_Profile.txtUserInformation, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate User Info tab is displayed", "true", String.valueOf(bUserInfo), true);
				new Actions(lDriver).moveByOffset(0, 50).build().perform();
				
				oR_Profile.txtUserInformation.click();
				Report.OperationPoint(testContext.getName(),"Navigation  - Navigating to User Tab");
				Thread.sleep(5000);
				
				boolean bContractInfo = UI.WaitForObject(oR_Profile.txtContractInfo, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Contract Information is displayed", "true", String.valueOf(bContractInfo), true);
				
				boolean bPrimary = UI.WaitForObject(oR_Profile.txtPrimaryUser, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Primary User is displayed", "true", String.valueOf(bPrimary), true);
				
				boolean bCusReg = UI.WaitForObject(oR_Profile.txtCustReg, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Customer Communities Registration is displayed", "true", String.valueOf(bCusReg), true);
				
				//Validate Marketting preferences
//				boolean bMarket = UI.WaitForObject(oR_Profile.txtMarketPref, UI.iObjTimeOut);
//				Report.ValidationPoint(testContext.getName(), "Validate Market Preference tab is displayed", "true", String.valueOf(bMarket), true);
//				oR_Profile.txtMarketPref.click();
//				Thread.sleep(5000);
//			
//				boolean bViewPref = UI.WaitForObject(oR_Profile.txtViewPref, UI.iObjTimeOut);
//				Report.ValidationPoint(testContext.getName(), "Validate View Account Level Preferences is displayed", "true", String.valueOf(bViewPref), true);
//				
//				boolean bGeneralMarket = UI.WaitForObject(oR_Profile.txtGeneMarketing, UI.iObjTimeOut);
//				Report.ValidationPoint(testContext.getName(), "Validate General Marketing is displayed", "true", String.valueOf(bGeneralMarket), true);
//				
//				boolean bSubscript = UI.WaitForObject(oR_Profile.txtSubscription, UI.iObjTimeOut);
//				Report.ValidationPoint(testContext.getName(), "Validate GMarketing Subscription Settings is displayed", "true", String.valueOf(bSubscript), true);
			}		
				//Validate ' AT&T Access ID Information' section
				boolean bAccessID = UI.WaitForObject(oR_Profile.lnkAccessID, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate AT&T Access ID Information is displayed", "true", String.valueOf(bAccessID), true);
				
				oR_Profile.lnkAccessID.click();
				Report.OperationPoint(testContext.getName(),"Navigation  - Navigating to 'AT&T Access ID Information'");
				
				Thread.sleep(5000);
			if(testContext.getName().contains("PRF07700_C_01"))
			{	
				boolean bAccess = UI.WaitForObject(oR_ATT_AccessID_Profile.txtMyATTAccessIDProfile, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate AT&T Access ID with access ID details is displayed", "true", String.valueOf(bAccess), true);
				
				boolean bMyNo = UI.WaitForObject(oR_ATT_AccessID_Profile.txtMyWirelessNo, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate My Wireless Number is displayed", "true", String.valueOf(bMyNo), true);
				
				boolean bUpdate = UI.WaitForObject(oR_ATT_AccessID_Profile.lnkUpdate, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Update link is displayed", "true", String.valueOf(bUpdate), true);
				
				boolean bName = UI.WaitForObject(oR_ATT_AccessID_Profile.txtName, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Name is displayed", "true", String.valueOf(bName), true);
			    
				boolean bContactEmail = UI.WaitForObject(oR_ATT_AccessID_Profile.txtEmailID, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Contact Email is displayed", "true", String.valueOf(bContactEmail), true);
				
				boolean bATTLink = UI.WaitForObject(oR_ATT_AccessID_Profile.lnkATTAccessIDProfile, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate AT&T Access ID with access ID link is displayed", "true", String.valueOf(bATTLink), true);
				
				boolean bManage = UI.WaitForObject(oR_ATT_AccessID_Profile.lnkManageAccountAccess, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Manage account access link is displayed", "true", String.valueOf(bManage), true);
				
				boolean bChangeAccess = UI.WaitForObject(oR_ATT_AccessID_Profile.lnkChangeATTaccessID, UI.iObjTimeOut);
				Report.ValidationPoint(testContext.getName(), "Validate Change AT&T Access ID link is displayed", "true", String.valueOf(bChangeAccess), true);
			}
		
			//Validate My Linked Account Section
			boolean bLinked = UI.WaitForObject(oR_ATT_AccessID_Profile.txtMyLinkedAccountsTitle, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate My Linked Account title is displayed", "true", String.valueOf(bLinked), true);
			
			boolean bLink = UI.WaitForObject(oR_ATT_AccessID_Profile.lnkLinkAnotherAccount, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate Link another account is displayed", "true", String.valueOf(bLink), true);
			
			boolean bDeLink = UI.WaitForObject(oR_ATT_AccessID_Profile.lnkDeLink, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Validate De-Link Account is displayed", "true", String.valueOf(bDeLink), true);
			
			//Validate 'Add a line' from Global Navigation
			UI.SelectServiceFromSecondaryNavigation( oR_AccountOverview.lnkWirelessSecNav ,oR_AccountOverview.lnkAddADevice);
			Thread.sleep(3000);
			 String Url = lDriver.getCurrentUrl();
			   if(Url.contains("tst"))
			   {
				   Report.TestPoint(testContext.getName(), "Validate Add a line link navigates to Hardrock page", "True", "True", true);
			   }
			   else
			   {
				   Report.TestPoint(testContext.getName(), "Validate Add a line link navigates to Hardrock page", "True", "false", true);
			   }
			   lDriver.navigate().back();
			   Thread.sleep(3000);
			   
			   UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkOverview, null);
			   Thread.sleep(8000);
			   //Validate 'Add a Line' through I want to dropdown
			   UI.ClickOrVerifyLinkUnderIWantToDropdown("Click", "Manage my plan","Wireless" ,"Add a device", null);
			   
			   Thread.sleep(3000);
				 Url = lDriver.getCurrentUrl();
				   if(Url.contains("tst"))
				   {
					   Report.TestPoint(testContext.getName(), "Validate Add a line link navigates to Hardrock page", "True", "True", true);
				   }
				   else
				   {
					   Report.TestPoint(testContext.getName(), "Validate Add a line link navigates to Hardrock page", "True", "false", true);
				   }
				   lDriver.navigate().back();
				   Thread.sleep(8000);
		}
		catch (Exception e)
		{
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	}		
	/**************************************************************
	* Function Name -  VerifyIDMAndDashboardfinal
	* Description- This function validates the flow of Change plan 
	* Parameters - 
	* Date created -7th Sep 15
	* Developed by - Gautham
	* Last Modified By -
	* Last Modified Date - 
	***************************************************************/
	//PRF07709_C_1 
	public static void VerifyChangeRatePlanForTitanBundleCustomer(ITestContext testContext)throws Exception
	{
	  try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_AccountInformation oR_AccountInformation = PageFactory.initElements(lDriver, OR_AccountInformation.class);
			OR_ViewOrChangeRatePlan oR_ViewOrChangeRatePlan = PageFactory.initElements(lDriver, OR_ViewOrChangeRatePlan.class);
			OR_Shop oR_Shop = PageFactory.initElements(lDriver, OR_Shop.class);
				
			//Validate "Add a Device" flow from "I Want to…" quick links
			UI.ClickOrVerifyLinkUnderIWantToDropdown("click", "Manage my plan","Wireless", "Add a device", null);
			
			if(UI.WaitForObject(oR_Shop.txtSelectAnAccount, 20, lDriver))
			{
				UI.WaitForObject(lDriver.findElement(By.id("acctSelContinue")), 10, lDriver);
				lDriver.findElement(By.id("acctSelContinue")).click();
				boolean bAdd=UI.WaitForObject(lDriver.findElement(By.xpath("//h1//div[contains(text(),'Cell Phones & Mobile Devices')]")), 10, lDriver); 
				Report.ValidationPoint(testContext.getName(), "Validate Add a device page is displayed", "true", String.valueOf(bAdd), true);
				lDriver.navigate().back();
				lDriver.navigate().back();
			}
			boolean bAdd=UI.WaitForObject(lDriver.findElement(By.xpath("//h1//div[contains(text(),'Cell Phones & Mobile Devices')]")), 10, lDriver); 
			Report.ValidationPoint(testContext.getName(), "Validate Add a device page is displayed", "true", String.valueOf(bAdd), true);
			lDriver.navigate().back();
			
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkChangeMyPlanTertiaryNav, lDriver);
			
			if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtViewOrChangeRatePlan, 20))
			{
				Report.ValidationPoint(testContext.getName(), "Validate View or Change rate plan page is displayed", "true", String.valueOf(oR_ViewOrChangeRatePlan.txtViewOrChangeRatePlan.isDisplayed()), true);
				//Validate Current rate plan section
				if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtCurrentRatePlan, 20))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Current rate plan section is present", "true", String.valueOf(oR_ViewOrChangeRatePlan.txtCurrentRatePlan.isDisplayed()), true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Current rate plan section is present", "true", String.valueOf(oR_ViewOrChangeRatePlan.txtCurrentRatePlan.isDisplayed()), true);
				}
				
				//Validate Current Rate Plan is displayed underneath the Account Number
				//Retrieving the locations of Current rate plan section & Account
				int iLocCurrentPlan = oR_ViewOrChangeRatePlan.txtCurrentRatePlan.getLocation().getY();
				Report.OperationPoint(testContext.getName(),"The Y-axis location of Current rate plan is "+iLocCurrentPlan);

				
				if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtBAN,1))
				{
					int iBan = oR_ViewOrChangeRatePlan.txtBAN.getLocation().getY();
					Report.OperationPoint(testContext.getName(),"The Y-axis location of Account Number is "+iBan);
					if(iLocCurrentPlan>iBan)
					{
						Report.ValidationPoint(testContext.getName(), "Validate Current Rate Plan is displayed underneath the BAN_Nickname", "true", "true", true);
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Validate Current Rate Plan is displayed underneath the BAN_Nickname", "true", "false", true);
					}
				}
				
				//Validate Nation Plan and Nation with Canada Plans is displayed on Change Rate Plan page
				if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtNationPlans, 10))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Nation Plan is displayed on Change Rate Plan page", "true",String.valueOf(oR_ViewOrChangeRatePlan.txtNationPlans.isDisplayed()), true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Nation Plan is displayed on Change Rate Plan page", "true", String.valueOf(oR_ViewOrChangeRatePlan.txtNationPlans.isDisplayed()), true);
				}
				//Validate Nation with Canada Plans is displayed on Change Rate Plan page
				if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtNationWithCanadaPlans, 10))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Nation with Canada Plans is displayed on Change Rate Plan page", "true", String.valueOf(oR_ViewOrChangeRatePlan.txtNationWithCanadaPlans.isDisplayed()), true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Nation with Canada Plans is displayed on Change Rate Plan page", "true", String.valueOf(oR_ViewOrChangeRatePlan.txtNationWithCanadaPlans.isDisplayed()), true);
				}
				//Validate Nation Plan is displayed above Nation with Canada Plans on Change Rate Plan page
				//Retrieving the locations of Nation Plan and Nation with Canada Plans
				int iLocNationPlan = oR_ViewOrChangeRatePlan.txtNationPlans.getLocation().getY();
				int iLocNationWithCanada = oR_ViewOrChangeRatePlan.txtNationWithCanadaPlans.getLocation().getY();
				Report.OperationPoint(testContext.getName(),"The Y-axis location of Nation plan is "+iLocNationPlan);
				Report.OperationPoint(testContext.getName(),"The Y-axis location of Nation with Canada plan is "+iLocNationWithCanada);					
				if(iLocNationWithCanada>iLocNationPlan)
				{
					Report.ValidationPoint(testContext.getName(), "Validate Nation Plan is displayed above Nation with Canada Plans on Change Rate Plan page", "Nation Plan is displayed above Nation with Canada Plans on Change Rate Plan page", "Nation Plan is displayed above Nation with Canada Plans on Change Rate Plan page", true);
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Nation Plan is displayed above Nation with Canada Plans on Change Rate Plan page", "Nation Plan is displayed above Nation with Canada Plans on Change Rate Plan page", "Nation Plan is NOTdisplayed above Nation with Canada Plans on Change Rate Plan page", true);
				}
				
				//Validate columns of data that will display for the Nation Plans table should contains : 
				//Minutes, Monthly cost, Rollover, Unlimited Night/Wknd Minutes, Unlimited Mobile to Mobile and Additional Details
				if(UI.WaitForObject(oR_ViewOrChangeRatePlan.tblNationPlans, 10))
				{
					Report.ValidationPoint(testContext.getName(), "Validate the Nation plans table","Nation plans table is present", "Nation plans table is present", true);
					List<WebElement> tblNationPlans = lDriver.findElements(By.xpath("//table[@id='sortable_table']//th"));
					System.out.println(tblNationPlans.size());
					String [] sColumnHeading = new String[tblNationPlans.size()];
					for(int i=0; i<tblNationPlans.size(); i++)
					{
						sColumnHeading[i]=tblNationPlans.get(i).getText();
					}
					//Retrieving the column headings
					for(int j=0;j<tblNationPlans.size();j++)
					{
						if(sColumnHeading[j].length()>1)
						{
							//Report.ValidationPoint(testContext.getName(), "Validate the headings of the table", "Heading :"+sColumnHeading[j]+" is present", "Required heading are present", true);
							Report.OperationPoint(testContext.getName()," Operation - Column heading: "+sColumnHeading[j]);
						}
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate the Nation plans table","Nation plans table is present", "Nation plans table is NOTpresent", true);
				}
				//Validate columns of data that will display for the Nation with Canada Plans table should contains : 
				//Minutes, Monthly cost, Rollover, Unlimited Night/Wknd Minutes, Unlimited Mobile to Mobile and Additional Details
				
				if(UI.WaitForObject(oR_ViewOrChangeRatePlan.tblNationWithCanada, 10))
				{
					Report.ValidationPoint(testContext.getName(), "Validate Nation with Canada plans table","Nation with Canada plans table is present", "Nation with Canada plans table is present", true);
					List<WebElement> tblNationPlansCanada = lDriver.findElements(By.xpath("//table[@id='nationcanada_sortable_table']//th"));
					String [] sColumnHeadingCanada = new String[tblNationPlansCanada.size()];
					for(int i=0; i<tblNationPlansCanada.size(); i++)
					{
						sColumnHeadingCanada[i]=tblNationPlansCanada.get(i).getText();
					}
					//Retrieving the column headings
					for(int j=0;j<tblNationPlansCanada.size();j++)
					{
						if(sColumnHeadingCanada[j].length()>1)
						{
							Report.OperationPoint(testContext.getName()," Operation - Column heading: "+sColumnHeadingCanada[j]);
						}
					}
				}
				else
				{
					Report.ValidationPoint(testContext.getName(), "Validate Nation with Canada plans table", "Nation with Canada plans table", "Nation with Canada plans table NOT exist", true);
				}
				
				//Validating nams of step indicators
				if(UI.WaitForObject(oR_ViewOrChangeRatePlan.txtStepIndicator1, 60, lDriver))
				{
					List<WebElement> lstStepIndicators = lDriver.findElements(By.xpath("//div[@id='StepIndicator']//span"));
					String [] sStepIndicators = new String[lstStepIndicators.size()];
					for(int i=0; i<lstStepIndicators.size(); i++)
					{
						sStepIndicators[i]=lstStepIndicators.get(i).getText();
					}
					//Retrieving the column headings
					for(int j=0;j<lstStepIndicators.size();j++)
					{
						if(sStepIndicators[j].length()>1)
						{
							Report.OperationPoint(testContext.getName()," Step indicator "+sStepIndicators[j] +"is present");
						}
					}
				}
				
				//Click on select plan button
				if(UI.WaitForObject(oR_ViewOrChangeRatePlan.btnSelectPlan, 60, lDriver))
				{
					oR_ViewOrChangeRatePlan.btnSelectPlan.click();

					Alert alert = lDriver.switchTo().alert();
					if(alert !=null)
					{
						 lDriver.switchTo().alert().accept();
						 Report.ValidationPoint(testContext.getName(),"Validate Alert is present", "true", "true", true);
					}
					if(UI.WaitForObject(oR_AccountOverview.frmTourMyBill, 60, lDriver))
					{
						lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
						boolean bOK= UI.WaitForObject(lDriver.findElement(By.xpath("//a[@class='btnRt']")), 20, lDriver);
						Report.ValidationPoint(testContext.getName(), "Validate POP up is displayed", "true", String.valueOf(bOK), true);
						lDriver.findElement(By.xpath("//a[@class='btnRt']")).click();
					}
				}
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
	* Function Name -   VerifyProfileFlowForTitanBundleCustomer
	* Description- This function validates the flow of Change plan 
	* Parameters - 
	* Date created -7th Sep 15
	* Developed by - Gautham
	* Last Modified By -
	* Last Modified Date - 
	***************************************************************/
	//PRF07709_C_2 , //PRF07707_C_2 ,//PRF07703_C_02 , //PRF07705_B_01,////PRF07709_B_01
	public static void VerifyProfileFlowForTitanBundleCustomer(ITestContext testContext)throws Exception
	{
	  try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_AccountInformation oR_AccountInformation = PageFactory.initElements(lDriver, OR_AccountInformation.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			OR_ATT_AccessID_Profile oR_ATT_AccessID_Profile = PageFactory.initElements(lDriver, OR_ATT_AccessID_Profile.class);
			OR_MyAccountAccess oR_MyAccountAccess = PageFactory.initElements(lDriver, OR_MyAccountAccess.class);
			OR_Shop oR_Shop = PageFactory.initElements(lDriver, OR_Shop.class);
			OR_AddaDevice oR_AddaDevice = PageFactory.initElements(lDriver, OR_AddaDevice.class);
			
			//navigate to profile
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecNav, null, lDriver);
			
			//Click on ATT Access id profile tab
			UI.WaitForObject(oR_Profile.lnkAttAccessIdProfile, 30, lDriver);
			oR_Profile.lnkAttAccessIdProfile.click();
			
			//validate elements in att access id inforamtion
			boolean bInformation= UI.WaitForObject(oR_ATT_AccessID_Profile.txtAttAccessIdInformation, 10, lDriver);
			boolean bLinked= UI.WaitForObject(oR_ATT_AccessID_Profile.txtMyLinkedAccountsTitle, 5, lDriver);
			boolean bWireless= UI.WaitForObject(oR_ATT_AccessID_Profile.txtMyWirelessNo, 5, lDriver);
			if(bInformation && bLinked && bWireless)
			{
				Report.ValidationPoint(testContext.getName(), "Validate 'Att Access id information', 'My Linked account', 'Wireless account' is displayed", "true", String.valueOf(bInformation && bLinked && bWireless), true);
			}
			
			//validate required elements
			boolean bAcessId= UI.WaitForObject(oR_ATT_AccessID_Profile.txtATTAccessID, 5, lDriver);
			boolean bName= UI.WaitForObject(oR_ATT_AccessID_Profile.txtName, 5, lDriver);
			boolean bEmail= UI.WaitForObject(oR_ATT_AccessID_Profile.txtEmailID, 60, lDriver);
			boolean bAccessIdLink= UI.WaitForObject(oR_ATT_AccessID_Profile.lnkATTAccessIDProfile, 5, lDriver);
			boolean bManage= UI.WaitForObject(oR_ATT_AccessID_Profile.lnkManageAccountAccess, 5, lDriver);
			boolean bChange= UI.WaitForObject(oR_ATT_AccessID_Profile.lnkChangeATTaccessID, 5, lDriver);
			if(bAcessId&&bName&&bEmail&&bAccessIdLink&&bManage&&bChange&& bWireless)
			{
				Report.ValidationPoint(testContext.getName(), 
						"Validate 'Att Access id', 'Name', 'Email','Access Id Link','Manage Account access link','change att acessID link' is displayed", "true", String.valueOf(bAcessId&&bName&&bEmail&&bAccessIdLink&&bManage&&bChange&& bWireless), true);
			}
			
			//validate account information tab
			lDriver.navigate().back();
			
			if(UI.WaitForObject(oR_Profile.txtAccountInformationHdr,30, lDriver))
			{
				boolean bBillingContact= UI.WaitForObject(oR_Profile.txtBillingContactInfo, 20, lDriver);
				boolean bBillingNotification= UI.WaitForObject(oR_Profile.txtBillingNotifications, 5, lDriver);
				boolean bPayment= UI.WaitForObject(oR_Profile.txtPaymentOptions, 5, lDriver);
				boolean bBillOptions= UI.WaitForObject(oR_Profile.txtPaperBillOptions, 5, lDriver);
				boolean bAuthorizedUsers= UI.WaitForObject(oR_Profile.txtAuthorizedUsers, 5, lDriver);
				
				Report.ValidationPoint(testContext.getName(), 
						"Validate 'Billing Contact information', 'Billing Notification','Bill Options', are displayed", "true",
						String.valueOf(bBillingContact&&bBillingNotification&&bBillOptions), true);
			}
			if(testContext.getName().contains("PRF07707_C_02") | testContext.getName().contains("PRF07703_C_02")|testContext.getName().contains("PRF07705_B_01")|testContext.getName().contains("PRF07709_B_02"))
			{
				if(UI.WaitForObject(oR_Profile.lnkEditAccInfo, 5, lDriver)){
				oR_Profile.lnkEditAccInfo.click();
				UI.WaitForObject(oR_Profile.txtAccountInformationHdr, 5, lDriver);
				Report.ValidationPoint(testContext.getName(), 
						"Validate page navigated as expected after clicking on edit in account information", "true",
						String.valueOf(oR_Profile.txtAccountInformationHdr.isDisplayed()), true);
				lDriver.navigate().back();
				}
				if(UI.WaitForObject(oR_Profile.lnkEditBillingContactInfo, 30, lDriver)){
				oR_Profile.lnkEditBillingContactInfo.click();
				UI.WaitForObject(oR_Profile. txtBillingContactInfoNew, 30, lDriver);
				Report.ValidationPoint(testContext.getName(), 
						"Validate page navigated as expected after clicking on edit in billing contact information", "true",
						String.valueOf(oR_Profile. txtBillingContactInfoNew.isDisplayed()), true);
				lDriver.navigate().back();
				}
				if(UI.WaitForObject(oR_Profile.lnkEditBillingNotification , 30, lDriver)){
				oR_Profile.lnkEditBillingNotification .click();
				UI.WaitForObject(oR_Profile.txtEditBilling, 30, lDriver);
				Report.ValidationPoint(testContext.getName(), 
						"Validate page navigated as expected after clicking on edit in billing notification", "true",
						String.valueOf(oR_Profile.txtEditBilling.isDisplayed()), true);
				lDriver.navigate().back();
				} 
				if(testContext.getName().contains("PRF07703_C_02")|testContext.getName().contains("PRF07705_B_01"))
				{
					if(UI.WaitForObject(oR_Profile.lnkEditPaperBillOptions, 30, lDriver))
					{
						oR_Profile.lnkEditPaperBillOptions.click();
						boolean bPaper=UI.WaitForObject(lDriver.findElement(By.xpath("//h1[contains(text(),'Paperless Billing')]")), 30, lDriver);
						Report.ValidationPoint(testContext.getName(), 
								"Validate page navigated as expected after clicking on edit in paper bill option", "true",
								String.valueOf(bPaper), true);
						lDriver.navigate().back();
					}
					boolean bService= UI.WaitForObject(lDriver.findElement(By.xpath("//h2[contains(text(),'Service Address')]")), 20, lDriver);
					Report.ValidationPoint(testContext.getName(), 
							"Validate service address is displayed", "true",
							String.valueOf(bService), true);
				}
			}
			//click on user information tab
			if(UI.WaitForObject(oR_Profile.txtUserInformation_1511, 60, lDriver)){
			oR_Profile.txtUserInformation_1511.click();
			
			//validate elements in user information tab
			boolean bCSS= UI.WaitForObject(oR_Profile.lnkCustomerServiceSummary, 10, lDriver);
			boolean bPrimaryUser= UI.WaitForObject(oR_Profile.txtPrimaryUser, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), 
					"Validate 'Customer service summary', 'Primary user', is displayed", "true",
					String.valueOf(bCSS&&bPrimaryUser), true);
			}
			if(testContext.getName().contains("PRF07703_C_02")|testContext.getName().contains("PRF07709_B_02"))
			{
				WebElement EditPrimaryUser = lDriver.findElement(By.xpath("//li//a[contains(text(),'Edit')]"));
				EditPrimaryUser.click();
				boolean bProfile=UI.WaitForObject(lDriver.findElement(By.xpath("//h2[contains(text(),'Profile Information')]")), 30, lDriver);
				Report.ValidationPoint(testContext.getName(), 
							"Validate page navigated as expected after clicking on edit in primary user option", "true",
							String.valueOf(bProfile), true);
				lDriver.navigate().back();
				if(!testContext.getName().contains("PRF07709_B_02")){
				WebElement Register =lDriver.findElement(By.xpath("//a[contains(text(),'Register for Communities')]"));
				UI.VerifyLinkNavigatestoNextPage(Register, "forums.att.com");
				lDriver.navigate().back();}
			}
			//navigate to marketing preferences tab
			
			if(UI.WaitForObject(oR_Profile.txtMarketPref, 10, lDriver)){
			oR_Profile.txtMarketPref.click();
			if(!testContext.getName().contains("PRF07705_B_01"))
			{
			//validate elements in marketing preferences tab
			boolean bViewAccount= UI.WaitForObject(oR_Profile.txtViewPref,10, lDriver);
			Report.ValidationPoint(testContext.getName(), 
					"Validate all the required sections are present in marketing preference tab is displayed", "true",
					String.valueOf(bViewAccount), true);
			}
			else{
				try{
					WebElement errorDiv= lDriver.findElement(By.xpath("//div[contains(@class,'emphasized')]"));
					Report.ValidationPoint(testContext.getName(),"Validate Marketing preferences tab is displayed as expected","true",String.valueOf(errorDiv.isDisplayed()),true);
				}catch(Exception E)
				{
					Report.ValidationPoint(testContext.getName(),"Validate Marketing preferences tab is displayed as expected","true","false",true);
				}
				}
			
			}
			//Validate my linked account section
			if(!testContext.getName().contains("PRF07703_C_02") && !testContext.getName().contains("PRF07705_B_01")&&!testContext.getName().contains("PRF07709_B_02")){
			if(UI.WaitForObject(oR_AccountOverview.lnkMyLinkedAccounts, 60, lDriver))
			{
				Actions driver = new Actions(lDriver);
				driver.moveToElement(oR_AccountOverview.lnkMyLinkedAccounts).build().perform();
				boolean bLink= UI.WaitForObject(oR_AccountOverview.lnkLinkAnotherAccountInMyLinkedAccount, 60, lDriver);
				boolean bProfile= UI.WaitForObject(oR_AccountOverview.lnkGoToMyProfile, 60, lDriver);
				boolean bManageAccount= UI.WaitForObject(oR_AccountOverview.lnkManageAccountAccessInMyLinkedAccount, 60, lDriver);
				Report.ValidationPoint(testContext.getName(), 
						"Validate 'Link anoother account', ' Go to profile', 'Manage Account access' links are displayed", "true",
						String.valueOf(bLink&&bProfile&&bManageAccount), true);
				oR_AccountOverview.lnkManageAccountAccessInMyLinkedAccount.click();
				boolean bPrimary= UI.WaitForObject(oR_MyAccountAccess.txtPrimaryOnlineAccess, 60, lDriver);
				boolean bSecondary= UI.WaitForObject(oR_MyAccountAccess.txSecondaryOnlineAccess, 60, lDriver);
				boolean bOffers= UI.WaitForObject(oR_MyAccountAccess.lnkOffersOthersAccountAccess, 60, lDriver);
				Report.ValidationPoint(testContext.getName(), 
						"Validate 'Primary Online Access', ' Offers Others Account access link', 'Secondary online access'  are displayed", "true",
						String.valueOf(bPrimary&&bSecondary&&bOffers), true);
			}
			if(testContext.getName().contains("PRF07707_C_02")&&!testContext.getName().contains("PRF07709_B_01"))
			{
				if(UI.WaitForObject(oR_MyAccountAccess.lnkDeLinkEmailAccount, 30, lDriver)){
				oR_MyAccountAccess.lnkDeLinkEmailAccount .click();
				if(UI.WaitForObject(oR_AccountOverview.frmTourMyBill, 60, lDriver))
				{	
					lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
					UI.WaitForObject(lDriver.findElement(By.xpath("//a//img[@alt='Continue']")), 60, lDriver);
					lDriver.findElement(By.xpath("//a//img[@alt='Continue']")).click();
					lDriver.switchTo().defaultContent();
				}}
			}}
			
			if(!testContext.getName().contains("PRF07705_B_01")&&!testContext.getName().contains("PRF07709_B_02")&&!testContext.getName().contains("PRF07703_C_02")){
			//validate add a device page
			UI.WaitForObject(oR_AccountOverview.lnkWirelessSecNav, 20, lDriver);
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkAddADevice, lDriver);
			Thread.sleep(10000);
			String URl= lDriver.getCurrentUrl();
			String URlContains= "stage.att.com/shop";
			if(URl.contains(URlContains))
			{
				Report.ValidationPoint(testContext.getName(), "Validate Add a device page link is navigated to required page", "Ad a Device navigated to" +URlContains+ "page ", "Ad a Device navigated to" +URlContains+ "page ", true);
				lDriver.navigate().back();
			}
			else if(UI.WaitForObject(oR_AddaDevice.txtAddaDevice, 10,lDriver))
			{
				boolean bAdd= UI.WaitForObject(oR_AddaDevice.txtAddaDevice, 20,lDriver);
				Report.ValidationPoint(testContext.getName(),"validate Add a device page is displayed","true",String.valueOf(bAdd),true);
				lDriver.navigate().back();
			}
			Thread.sleep(4000);
			//Validate add a device from drop down
			if(!testContext.getName().contains("PRF07703_C_02"))
			{
			if(UI.WaitForObject(oR_AccountOverview.btnIWantTo, 60, lDriver)){
			UI.ClickOrVerifyLinkUnderIWantToDropdown("click", "Manage my plan","Wireless", "Add a device", null);
			String URl_1= lDriver.getCurrentUrl();
			if(URl_1.contains(URlContains))
			{
				Report.ValidationPoint(testContext.getName(), "Validate Add a device page link is navigated to required page", "Ad a Device navigated to" +URlContains+ "page ", "Ad a Device navigated to" +URlContains+ "page ", true);
				lDriver.navigate().back();
			}
			else if(UI.WaitForObject(oR_AddaDevice.txtAddaDevice, 10,lDriver))
			{
				boolean bAdd= UI.WaitForObject(oR_AddaDevice.txtAddaDevice, 20,lDriver);
				Report.ValidationPoint(testContext.getName(),"validate Add a device page is displayed","true",String.valueOf(bAdd),true);
				lDriver.navigate().back();
			}
			}
			}
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
	* Function Name -   VerifyLinksOnDashboardAndMyLinkedAccountsSection
	* Description- 
	* Parameters - 
	* Date created -21st Sep 15
	* Developed by - Gautham
	* Last Modified By -
	* Last Modified Date - 
	***************************************************************/
	//PRF07703_c_01 //PRF07705_B_01//PRF07709_B_01
	public static void VerifyLinksOnDashboardAndMyLinkedAccountsSection(ITestContext testContext)throws Exception
	{
	  try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());   
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_MyAccountAccess oR_MyAccountAccess = PageFactory.initElements(lDriver, OR_MyAccountAccess.class);
			OR_AddaDevice oR_AddaDevice = PageFactory.initElements(lDriver, OR_AddaDevice.class);
			Actions driver = new Actions(lDriver);
			if(!testContext.getName().contains("PRF07703_C_01"))
			{
				//validating dashboard elements
			boolean bManage= UI.WaitForObject(oR_AccountOverview.lnkManagePaperlessBilling, 10, lDriver);
			boolean bEnrollPaperless= UI.WaitForObject(oR_AccountOverview.lnkEnrollInPaperlessBilling, 30, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate manage paperless billing link is displayed", "true", String.valueOf(bManage | bEnrollPaperless), true);
			
			boolean bEnroll= UI.WaitForObject(oR_AccountOverview.lnkEnrollInAutopay2, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate Enroll in autopay link is displayed", "true", String.valueOf(bEnroll), true);
			
			boolean bPlan= UI.WaitForObject(oR_AccountOverview.lnkChangePlanDasboard, 5, lDriver);
			if(bPlan)
			Report.ValidationPoint(testContext.getName(), "Validate change plan link is displayed", "true", String.valueOf(bPlan), true);
			
			boolean bBill= UI.WaitForObject(oR_AccountOverview.lnkViewBillDetails, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate View Bill details is displayed", "true", String.valueOf(bBill), true);
			
			boolean bUsage= UI.WaitForObject(oR_AccountOverview.lnkViewAllUsage, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate View All usage link is displayed", "true", String.valueOf(bUsage), true);
			
			boolean bView= UI.WaitForObject(oR_AccountOverview.lnkViewAll,5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate View all link is displayed", "true", String.valueOf(bView), true);
			
			boolean bPayment= UI.WaitForObject(oR_AccountOverview.btnMakeAPaymentInOverviewPage, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate make a payment link is displayed", "true", String.valueOf(bPayment), true);
			
			boolean bIWantTo= UI.WaitForObject(oR_AccountOverview.btnIWantTo, 20, lDriver);
			oR_AccountOverview.btnIWantTo.click();
			List<WebElement> DropDown = lDriver.findElements(By.xpath("//div[@id='ddShortcutBox']//ul//li//div[@class='float-left MarLeft17']//span"));
			if(DropDown.size()>0){
				int i=1;
				for(WebElement e: DropDown)
				{
				Report.OperationPoint(testContext.getName(),+ i+"Element in Iwant To drop down is:" +e.getText());	
				i++;
				}
				Report.ValidationPoint(testContext.getName(), "Validate I Want to button is displayed as expected", "true", String.valueOf(bIWantTo), true);
				
			}
			//validating alerts
			if(UI.WaitForObject(oR_AccountOverview.btnAlert, 10, lDriver))
			{	
				oR_AccountOverview.btnAlert.click();
				Report.ValidationPoint(testContext.getName(), "Validate Alerts section  is displayed with all alerts", "true", String.valueOf(oR_AccountOverview.btnAlert.isDisplayed()), true);
			}
			
			//validating manage account section
			if(UI.WaitForObject(oR_AccountOverview.lnkMyLinkedAccounts,10, lDriver))
			{
				driver.moveToElement(oR_AccountOverview.lnkMyLinkedAccounts).build().perform();
				boolean bLink= UI.WaitForObject(oR_AccountOverview.lnkLinkAnotherAccountInMyLinkedAccount,10, lDriver);
				boolean bProfile= UI.WaitForObject(oR_AccountOverview.lnkGoToMyProfile, 10, lDriver);
				boolean bManageAccount= UI.WaitForObject(oR_AccountOverview.lnkManageAccountAccessInMyLinkedAccount,10, lDriver);
				Report.ValidationPoint(testContext.getName(), 
						"Validate 'Link another account', ' Go to profile', 'Manage Account access' links are displayed", "true",
						String.valueOf(bLink&&bProfile&&bManageAccount), true);
				oR_AccountOverview.lnkManageAccountAccessInMyLinkedAccount.click();
				boolean bPrimary= UI.WaitForObject(oR_MyAccountAccess.txtPrimaryOnlineAccess, 10, lDriver);
				boolean bSecondary= UI.WaitForObject(oR_MyAccountAccess.txSecondaryOnlineAccess, 5, lDriver);
				boolean bOffers= UI.WaitForObject(oR_MyAccountAccess.lnkOffersOthersAccountAccess, 5, lDriver);
				if(bPrimary)
				{
					Report.ValidationPoint(testContext.getName(), 
							"Validate 'Primary Online Access', ' Offers Others Account access link', 'Secondary online access'  are displayed", "true",
						String.valueOf(bPrimary&&bSecondary), true);
				}
				if(!testContext.getName().contains("PRF07705_B_01") | !testContext.getName().contains("PRF07709_B_01")){
				if(UI.WaitForObject(oR_MyAccountAccess.lnkDeLinkEmailAccount, 10, lDriver))
				{
					oR_MyAccountAccess.lnkDeLinkEmailAccount .click();
					if(UI.WaitForObject(oR_AccountOverview.frmTourMyBill, 10, lDriver))
					{	
						lDriver.switchTo().frame(oR_AccountOverview.frmTourMyBill);
					boolean bContinue=	UI.WaitForObject(lDriver.findElement(By.xpath("//a//img[@alt='Continue']")), 60, lDriver);
						Report.ValidationPoint(testContext.getName(), 
								"Validate De-liking an account", "true",String.valueOf(bContinue), true);
						lDriver.findElement(By.xpath("//a//img[@alt='Continue']")).click();
						lDriver.switchTo().defaultContent();
					}
				}
				}
				}
			}
				if(testContext.getName().contains("PRF07705_B_01") | testContext.getName().contains("PRF07709_B_01")| testContext.getName().contains("PRF07703_C_01")){
					UI.WaitForObject(oR_AccountOverview.lnkOverview, 10, lDriver);
					UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkOverview,null, lDriver);
					driver.moveToElement(oR_AccountOverview.lnkWirelessSecNav).build().perform();
					if(UI.WaitForObject(oR_AccountOverview.lnkAddADeviceStub, 10, lDriver))
					{
					UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkWirelessSecNav, oR_AccountOverview.lnkAddADeviceStub, lDriver);
					Thread.sleep(5000);
					String URl= lDriver.getCurrentUrl();
					String URlContains= "stage.att.com/shop";
					Report.OperationPoint(testContext.getName(),"Page Navigated to"+ URl);
					if(URl.contains(URlContains))
					{
						Report.ValidationPoint(testContext.getName(), "Validate Add a device page link is navigated to required page", "Ad a Device navigated to" +URlContains+ "page ", "Ad a Device navigated to" +URlContains+ "page ", true);
						lDriver.navigate().back();
					}
					else if(UI.WaitForObject(oR_AddaDevice.txtAddaDevice, 10,lDriver))
					{
						boolean bAdd= UI.WaitForObject(oR_AddaDevice.txtAddaDevice, 20,lDriver);
						Report.ValidationPoint(testContext.getName(),"validate Add a device page is displayed","true",String.valueOf(bAdd),true);
						lDriver.navigate().back();
					}
					Thread.sleep(4000);
					}
					//Validate add a device from drop down
					if(!testContext.getName().contains("PRF07703_C_02")&& !testContext.getName().contains("PRF07709_B_01"))
					{
					if(UI.WaitForObject(oR_AccountOverview.btnIWantTo, 60, lDriver)){
					UI.ClickOrVerifyLinkUnderIWantToDropdown("click", "Manage my plan","Wireless", "Add a device", null);
					String URlContains= "stage.att.com/shop";
					String URl_1= lDriver.getCurrentUrl();
					if(URl_1.contains(URlContains))
					{
						Report.ValidationPoint(testContext.getName(), "Validate Add a device page link is navigated to required page", "Ad a Device navigated to" +URlContains+ "page ", "Ad a Device navigated to" +URlContains+ "page ", true);
						lDriver.navigate().back();
					}
					else if(UI.WaitForObject(oR_AddaDevice.txtAddaDevice, 10,lDriver))
					{
						boolean bAdd= UI.WaitForObject(oR_AddaDevice.txtAddaDevice, 20,lDriver);
						Report.ValidationPoint(testContext.getName(),"validate Add a device page is displayed","true",String.valueOf(bAdd),true);
						lDriver.navigate().back();
					}
					}
					}
					else
					{
						Thread.sleep(4000);
						Report.ValidationPoint(testContext.getName(),"validate Overview page is displayed","true",String.valueOf(oR_AccountOverview.lnkOverview.isDisplayed()),true);
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
* Function Name -   VerifyAddLineToAutoPayStatus
* Description- This function validates the Add Line to AutoPay Status Area in the Payment Profile Section 
* Parameters - 
* Test Script ID - PRF13065
* Date created - 31st Oct 15
* Developed by - Nachiket Pawar
* Last Modified By -
* Last Modified Date - 
***************************************************************/
public static void VerifyAddLineToAutoPayStatus(ITestContext testContext)throws Exception
{
  try
	{
	  WebDriver lDriver = UI.getDriver(testContext.getName());   
	  OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
	  OR_ATT_AccessID_Profile oR_ATT_AccessID_Profile = PageFactory.initElements(lDriver, OR_ATT_AccessID_Profile.class);
	  Report.OperationPoint(testContext.getName(), "Navigate AT&T Access ID page");
	
	  // Click on Update Stored Payment Method from Sec Nav  
	  UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkBillingUsagePaymentsSecNav, oR_AccountOverview.lnkUpdatePaymentProfile);
	  if(UI.WaitForObject(oR_ATT_AccessID_Profile.txtATTAccessIDHeader,UI.iObjTimeOut,lDriver)){
		  Report.ValidationPoint(testContext.getName(), "Validate that user is navigated to AT&T Access ID page", "True", "True", true);  
		  
	  }else{
		  
		  Report.ValidationPoint(testContext.getName(), "Validate that user is navigated to AT&T Access ID page", "True", "False", true);
	  }
	    
	  // Check if verify that a line separates the AutoPay area, within the Payment Profile Section, from the rest of the page 
	  
	 WebElement sectionDivider = lDriver.findElement(By.xpath("//h2[contains(text(),'AutoPay Status')]/parent::div/parent::div"));
	  
	 String strClassName = sectionDivider.getAttribute("class");
	  if(strClassName.contains("divider")){
		 Report.ValidationPoint(testContext.getName(), "Validate a line separates the AutoPay area from the rest of the page", "True", "True", true); 
	  }else{
			 Report.ValidationPoint(testContext.getName(), "Validate a line separates the AutoPay area from the rest of the page", "True", "False", true);
	  }
	  
	  // Validate header "AutoPay Status" display 
	  if(UI.WaitForObject(oR_ATT_AccessID_Profile.txtAutoPayStatus, UI.iObjTimeOut, lDriver)){
		  Report.ValidationPoint(testContext.getName(), "Validate header AutoPay Status display ", "True", "True", true);
	  }else{
		  Report.ValidationPoint(testContext.getName(), "Validate header AutoPay Status display ", "True", "False", true);
	  }
	  
	  // Validate Account number and nickname to be right justified 
	  WebElement txtAccountNumber = lDriver.findElement(By.xpath("//label[contains(text(),'Account ')]/parent::div"));
	  WebElement txtAccountNickName = lDriver.findElement(By.xpath("//label[contains(text(),'NICKNAME')]/parent::div"));
	  
	  if(txtAccountNumber.getAttribute("class").contains("MarRight")){
		 Report.ValidationPoint(testContext.getName(), "Validate Account Number is right justified to align with the rest of page", "True", "True", true); 
	  }else{
		  Report.ValidationPoint(testContext.getName(), "Validate Account Number is right justified to align with the rest of page", "True", "False", true);  
	  }
	  
	  
	  if(txtAccountNickName.getAttribute("class").contains("MarRight")){
			Report.ValidationPoint(testContext.getName(), "Validate Account Nickname is right justified to align with the rest of page", "True", "True", true); 
	  }else{
		    Report.ValidationPoint(testContext.getName(), "Validate Account Nickname is right justified to align with the rest of page", "True", "False", true);  
	  }
	  
	  // Validate autopay status and AutoPay link to be left Justified
     
	  if(UI.WaitForObject(oR_ATT_AccessID_Profile.lnkSignUpForAutoPay, 10, lDriver)){
		   if(oR_ATT_AccessID_Profile.lnkSignUpForAutoPay.getAttribute("class").contains("Left")){
			  Report.ValidationPoint(testContext.getName(), "Validate Autopay Status and link to be left Justified", "True", "True", true);
		  }else{
			  Report.ValidationPoint(testContext.getName(), "Validate Autopay Status and link to be left Justified", "True", "False", true); 
		  }
		  
	  }else{
		  Report.ValidationPoint(testContext.getName(), "Validate AutoPay link is dispalyed", "True", "False", true);
	  }
	  
	}catch (Exception e)
	{
		Report.TestPoint(testContext.getName(),
				"Some error has occured", "True",
				e.getMessage(), true);
	}
  }

	/*************************************
	 * Code for Test case PRF11624
	 * Developed By - Clint John
	 * Date Created - 4th Nov 2015 
	 **************************************/
	public static void VerifyPRF11624(ITestContext testContext)throws Exception
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
	  try
		{
		  if(UI.WaitForObject(oR_Login.txtIsMYATTProfileUpToDate, UI.iObjTimeOut, lDriver))
			{
				Report.ValidationPoint(testContext.getName(), "Validate that section title - Profile up-to-date is displayed", "true", "true", true);
				
				//description and fields for First and Last Name
				boolean bWelcomeBack = UI.WaitForObject(oR_Login.txtWelcomeBackFirstNameLastName, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that First name and Last name is displayed", "true", String.valueOf(bWelcomeBack), true);
				boolean bDescri = UI.WaitForObject(oR_Login.txtWelcomeBackDescription, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that a description under feilds First name and last name is displayed", "true", String.valueOf(bDescri), true);
				
				//Validate that drop-downs for Security questions are displayed
				Boolean bSecurity = UI.WaitForObject(oR_Login.txtSecurityQ, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that feilds for Security questions are displayed", "true", String.valueOf(bSecurity), true);
				
				boolean bSecQ1 = UI.WaitForObject(oR_Login.btnSecurityQ1, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that drop-down for Security question 1 is displayed", "true", String.valueOf(bSecQ1), true);
				boolean bSecQAns1 = UI.WaitForObject(oR_Login.edtSecurityA1, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that Security answer feild 1 is displayed", "true", String.valueOf(bSecQAns1), true);
				boolean bSecQ2 = UI.WaitForObject(oR_Login.btnSecurityQ2, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that drop-down for Security question 2 is displayed", "true", String.valueOf(bSecQ2), true);
				boolean bSecQAns2 = UI.WaitForObject(oR_Login.edtSecurityA2, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that Security answer feild 2 is displayed", "true", String.valueOf(bSecQAns2), true);
				
				//Section description is displayed for designated main CTN
				boolean bWirelessDesc = UI.WaitForObject(oR_Login.txtMyWirelessNumberDesc, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that Section description is displayed for designated main CTN", "true", String.valueOf(bWirelessDesc), true);
				
				//Verify Yes, No radio buttons
				boolean bYes = UI.WaitForObject(oR_Login.radBtnYes, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that radio button for selecting Yes is displayed under My wireless number", "true", String.valueOf(bYes), true);

				boolean bNo = UI.WaitForObject(oR_Login.radBtnNo, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate that radio button for selecting No is displayed under My wireless number", "true", String.valueOf(bNo), true);

				if (UI.WaitForObject(oR_Login.btnContinueToAccount, 1)) {
					
					oR_Login.btnContinueToAccount.click();
					Report.ValidationPoint(testContext.getName(), "Validate that, Continue to Account button is displayed", "true","true", true);
				}
			}
		}catch (Exception e)
		{
			Report.TestPoint(testContext.getName(),
					"Some error has occured", "True",
					e.getMessage(), true);
		}
	  }
	/**************************************************************
	 * Function Name - VerifySecurityWarningOnLinkAccountPage 
	 * Description- Protection lock security warning on attempting to link a wireless account
	 * Parameters - None
	 * Date created - 16th Dec 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
//	#PRF17322
	public static void VerifySecurityWarningOnLinkAccountPage(ITestContext testContext)throws Exception
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
	    OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_LinkAnAccount oR_LinkAnAccount = PageFactory.initElements(lDriver, OR_LinkAnAccount.class);
		OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
		Boolean bManage, bProfile, bLinkAcc, bWarning, bList,  bZIP, bWireless, bWirelessNum, bZIPCode, bAccType;
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		String sWirelessNum = IO.GetParamVal(sTestParams, "Wireless_Num");
		String sZIP = IO.GetParamVal(sTestParams, "ZIPCODE");

	try
		{
//		  Click on Profile link on Overview Page.
		  bProfile = UI.WaitForObject(oR_AccountOverview.lnkProfileSecondaryNav, 20, lDriver);
		  Report.TestPoint(testContext.getName(), "3-Click on Profile link on Overview Page.", "true", bProfile.toString(), true);
		  oR_AccountOverview.lnkProfileSecondaryNav.click();
		  
//		  On Profile Page, Click on Manage My AT&T Access Id link.
		  bManage = UI.WaitForObject(oR_ATT.lnkManageMyATTAcessID, 20, lDriver);
		  Report.TestPoint(testContext.getName(), "4-On Profile Page, Click on Manage My AT&T Access Id link.", "true", bManage.toString(), true);
		  oR_ATT.lnkManageMyATTAcessID.click();
		  
		  if(UI.WaitForObject(oR_ATT.lnkManageMyATTAcessID, 20, lDriver))
		  	oR_ATT.lnkManageMyATTAcessID.click();
		  Thread.sleep(5000);
		  
		  bLinkAcc = UI.WaitForObject(oR_LinkAnAccount.lnkLinkAnotherAccount, 20, lDriver);
		  Report.TestPoint(testContext.getName(), "5-Click on Link Accounts link", "true", bLinkAcc.toString(), true);
		  oR_LinkAnAccount.lnkLinkAnotherAccount.click();
		  
		  bList = UI.WaitForObject(oR_LinkAnAccount.txtSelectAccount, 20, lDriver);
		  if(bList)
		  {
//			  bAccType = UI.WaitForObject(oR_LinkAnAccount.txtAcctype, 20, lDriver);
			  Report.ValidationPoint(testContext.getName(), "i- Text : Account Type with Account type drop down box.", "true", bList.toString(), true);
			  UI.SelectOptionFromDropDown(oR_LinkAnAccount.lstSelAcc, "Wireless / Wireless Home Phone");
			  Thread.sleep(5000);
			  
			  bWireless = UI.WaitForObject(oR_LinkAnAccount.txtWirelessNumber, 20, lDriver);
			  Report.ValidationPoint(testContext.getName(), "ii- Conditional : Wireless No. text with Drop down box.", "true", bWireless.toString(), true);

			  bZIP = UI.WaitForObject(oR_LinkAnAccount.txtBillingZIPCode, 20, lDriver);
			  Report.ValidationPoint(testContext.getName(), "iii- Conditional : Billing zipcode text with input box.", "true", bZIP.toString(), true);
			  
//			  
//			  Report.OperationPoint(testContext.getName(),"Select 'Wireless account' from Account Type drop down and enter the Wireless Number.After that click on Next CTA.");
//			  Report.ValidationPoint(testContext.getName(), "ii- Conditional : Wireless No. text with Drop down box.", "true", bList.toString(), true);
			  bWirelessNum = UI.WaitForObject(oR_LinkAnAccount.edtBillingAccNum, 20, lDriver);
			  bZIPCode = UI.WaitForObject(oR_LinkAnAccount.edtZipCode, 20, lDriver);
			  if(bWirelessNum && bZIPCode)
			  {
				  oR_LinkAnAccount.edtBillingAccNum.sendKeys(sWirelessNum);
				  oR_LinkAnAccount.edtZipCode.sendKeys(sZIP);		  
			  }
			  
			  Report.TestPoint(testContext.getName(), "6-Select 'Wireless account' from Account Type drop down and enter the Wireless Number.", "true", String.valueOf(bWirelessNum && bZIPCode), true);
//				After that click on Next CTA.
			  try
			  {  oR_LinkAnAccount.btnNext.click(); }
			  catch(Exception e){Report.TestPoint(testContext.getName(), "Next Button Not present", "true", "false", true);}
				
			  
//			  Select Account Type' page is displayed with Account type and wireless number prepopulated(last 4 digits) along with a message in red on top.The message is as follows:
//			  For your security, the account you're attempting to register has a temporary protection lock. To unlock it now, call us at 877.285.0144.
			  bWarning = UI.WaitForObject(oR_LinkAnAccount.txtSecurityWarning, 20, lDriver);
			  Report.ValidationPoint(testContext.getName(), "For your security, the account you're attempting to register has a temporary protection lock. To unlock it now, call us at 877.285.0144.", "true", bWarning.toString(), true);
		  }
		  else
			  Report.TestPoint(testContext.getName(), "i- Text : Account Type with Account type drop down box.", "true", bList.toString(), true);

		}
	  catch(Exception e)
		{
		  Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyCancelModalOnRegisterPage 
	 * Description- Checks for Cancel modal on Register page
	 * Parameters - 
	 * Date created - 17th Dec 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
//	#PRF17393
	public static void VerifyCancelModalOnRegisterPage(ITestContext testContext)throws Exception
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());   
		OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
	    Boolean bRegisterToday, bLoginPage, bSelectAccount, bCancel, bFrame, bNo, bYes;
	  try
	  {
//		  Click Register link on the Widget
		  bRegisterToday = UI.WaitForObject(oR_Login.lnkRegisterToday, 20, lDriver);
		  Report.TestPoint(testContext.getName(), "2-Click Register link on the Widget", "true", bRegisterToday.toString(), true);
		  oR_Login.lnkRegisterToday.click();
		  
//		  The Welcome page of the flip registration flow is displayed in the same browser window
		  bSelectAccount = UI.WaitForObject(oR_Login.txtSelectAccount, 20, lDriver);
		  Report.ValidationPoint(testContext.getName(), "The Welcome page of the flip registration flow is displayed in the same browser window", "true", bSelectAccount.toString(), true);

//		  Click Cancel
		  bCancel = UI.WaitForObject(oR_Login.lnkCancel, 20, lDriver);
		  oR_Login.lnkCancel.click();
		  Thread.sleep(5000);
		  Report.ValidationPoint(testContext.getName(), "3-Click Cancel", "true", bCancel.toString(), true);
		  
//		  A Cancel modal should come up with Yes and No buttons
		  bFrame = UI.WaitForObject(oR_Login.frmCancelRegistration, 10, lDriver);
		  if(bFrame)
		  {
			  Report.ValidationPoint(testContext.getName(), "A Cancel modal should come up with Yes and No buttons", "true", bFrame.toString(), true);
			  lDriver.switchTo().frame(oR_Login.frmCancelRegistration);
			  bNo = UI.WaitForObject(oR_Login.btnNo, 20, lDriver);
			  bYes = UI.WaitForObject(oR_Login.btnYes, 20, lDriver);
			  Report.ValidationPoint(testContext.getName(), "A Cancel modal should come up with Yes and No buttons", "true", String.valueOf(bNo && bYes), true);
		
			  if(bYes)
			  {
//				  Click Yes  
				  oR_Login.btnYes.click();
				  Report.ValidationPoint(testContext.getName(), "4-Click Yes", "true", "true", true);	  
				  bLoginPage = UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, 20, lDriver);
				  Report.ValidationPoint(testContext.getName(), "The Login page with the Login widget is displayed", "true", bLoginPage.toString(), true);	  
			  }
		  }
		  else
			Report.ValidationPoint(testContext.getName(), "A Cancel modal should come up", "true", "False", true);

	  } 
	  catch(Exception e)
		{
		  Report.TestPoint(testContext.getName(), "Some error has occured", "true", "Failed to validate"+e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyForgotIDPasswordFlowWirelessCancellation 
	 * Description- Checks for Login Page after clicking on Cancel for Forgot ID and password for wireless account
	 * Parameters - 
	 * Date created - 17th Dec 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
//	#PRF17446
	public static void VerifyForgotIDPasswordFlowWirelessCancellation(
			ITestContext testContext) throws Exception {
		WebDriver lDriver = UI.getDriver(testContext.getName());
		Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
		OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
		OR_LinkAnAccount oR_LinkAnAccount = PageFactory.initElements(lDriver,OR_LinkAnAccount.class);
		Boolean bPassword, bContinue, bLoginPage, bCancel, bZIPCode, bForgotPage, bWirelessNum, bDontKnow, bContactEmail, bForgotIDAndPassword, bForgotID, bAcc, bYes;
		String AudiCtn = IO.GetParamVal(sTestParams, "CTN");
		String ZipCode = IO.GetParamVal(sTestParams, "ZipCode");
		try {
			// Click Forgot Password link on the Login page
			bPassword = UI.WaitForObject(oR_Login.lnkForgotPassword, 20,
					lDriver);
			Report.ValidationPoint(testContext.getName(),
					"2-Click Forgot Password link on the Login page", "true",
					bPassword.toString(), true);
			oR_Login.lnkForgotPassword.click();

			bForgotPage = UI.WaitForObject(oR_Login.txtForgotIDPasswordHeader,
					20, lDriver);
			Report.ValidationPoint(testContext.getName(),
					"The Forgot ID/Password page is displayed", "true",
					bForgotPage.toString(), true);

			// Select the Forgot User ID and Password radio button
			bForgotIDAndPassword = UI.WaitForObject(
					oR_Login.txtUserIDAndPassword, 20, lDriver);
			if (bForgotIDAndPassword) {
				oR_Login.txtUserIDAndPassword.click();
				Report.ValidationPoint(
						testContext.getName(),
						"3-Select the Forgot User ID and Password radio button",
						"true", bForgotIDAndPassword.toString(), true);
			} else
				Report.ValidationPoint(
						testContext.getName(),
						"3-Select the Forgot User ID and Password radio button",
						"true", bForgotIDAndPassword.toString(), true);
			// The page expands to display the Contact email field

			bContactEmail = UI.WaitForObject(oR_Login.edtContactEmailAddress,
					20, lDriver);
			Report.ValidationPoint(testContext.getName(),
					"The page expands to display the Contact email field",
					"true", bContactEmail.toString(), true);
			// Click 'Don't know contact email address' link
			bDontKnow = UI
					.WaitForObject(
							oR_Login.lnkDontKnowContactEmailAddressForUserIDAndPassword,
							20, lDriver);
			if (bDontKnow) {
				oR_Login.lnkDontKnowContactEmailAddressForUserIDAndPassword
						.click();
				Report.ValidationPoint(testContext.getName(),
						"4-Click 'Don't know contact email address' link",
						"true", bDontKnow.toString(), true);
			} else
				Report.ValidationPoint(testContext.getName(),
						"4-Click 'Don't know contact email address' link",
						"true", bDontKnow.toString(), true);

			// The Select Credentials page is displayed
			bForgotID = UI.WaitForObject(oR_Login.txtForgotIDPasswordHeader,
					20, lDriver);
			Report.ValidationPoint(testContext.getName(),
					"The Select Credentials page is displayed", "true",
					bForgotID.toString(), true);

			// Select the Wireless option from the drop down
			bAcc = UI.WaitForObject(oR_Login.txtSelectAccountType, 20, lDriver);

			if (bAcc) {
				UI.SelectOptionFromDropDown(oR_Login.lstAccountDropDown,
						"Wireless");
				Thread.sleep(5000);
				Report.ValidationPoint(testContext.getName(),
						"Select the Wireless option from the drop down",
						"true", bAcc.toString(), true);
			}

			// The Wireless option is selected and fields to enter Wireless
			// Number and zip code is displayed
			bWirelessNum = UI.WaitForObject(oR_Login.edtWirelessNumber, 20,
					lDriver);
			bZIPCode = UI
					.WaitForObject(oR_Login.edtBillingZipCode, 20, lDriver);
			Report.ValidationPoint(
					testContext.getName(),
					"i - The Wireless option is selected and fields to enter Wireless Number and zip code is displayed",
					"true", String.valueOf(bWirelessNum && bZIPCode), true);

			// Enter wireless number and zipcode
			oR_Login.edtWirelessNumber.sendKeys(AudiCtn);
			// Report.ValidationPoint(testContext.getName(),
			// "Wireless number is entered", "True", "True", true);
			oR_Login.edtBillingZipCode.sendKeys(ZipCode);
			Report.ValidationPoint(testContext.getName(),
					"ii - Wireless number & Wrong Zipcode is entered", "True",
					"True", true);

			// Continue button is enabled
			bContinue = UI.WaitForObject(oR_Login.btnEnableContinue, 20,
					lDriver);
			if (bContinue) {
				Report.ValidationPoint(testContext.getName(),
						"ii- Continue button is enabled", "true",
						bContinue.toString(), true);
				oR_Login.btnEnableContinue.click();
				Report.ValidationPoint(testContext.getName(),
						"Continue button is clicked", "True", "True", true);

				// validate error msg
				String errMsg = oR_Login.txtErrorMsg.getText();
				if (errMsg.contains("register"))
					Report.ValidationPoint(testContext.getName(),
							"Verify error msg is displayed", "True", "True",
							true);

			} else {
				Report.ValidationPoint(testContext.getName(),
						"ii- Continue button is enabled", "true",
						bContinue.toString(), true);
			}
			/*
			 * //Continue button is disabled bContinue =
			 * UI.WaitForObject(oR_Login.btnDisableContinue, 20, lDriver);
			 * Report.ValidationPoint(testContext.getName(),
			 * "ii- Continue button is disabled", "true", bContinue.toString(),
			 * true);
			 * 
			 * 
			 * // Cancel button is active bCancel =
			 * UI.WaitForObject(oR_LinkAnAccount.lnkCancel, 20, lDriver);
			 * Report.ValidationPoint(testContext.getName(),
			 * "iii -  Cancel button is active", "true", bCancel.toString(),
			 * true); if(bCancel) { oR_LinkAnAccount.lnkCancel.click();
			 * Report.OperationPoint(testContext.getName(), "Click cancel");
			 * bLoginPage = UI.WaitForObject(oR_Login.txtLogInToManageUrAcc, 20,
			 * lDriver); Report.ValidationPoint(testContext.getName(),
			 * "The MyATT Login page is displayed", "true",
			 * bLoginPage.toString(), true);
			 * 
			 * }
			 */

		} catch (Exception e) 
		{	Report.TestPoint(testContext.getName(), "Some error has occured",
					"true", "Failed to validate" + e.getMessage(), true);
		}
	}
	
	/**************************************************************
	 * Function Name - ValidateDeleteATTAccessIDFrame 
	 * Description- Validates elements on Delete ATT Access ID profile modal 
	 * Parameters - 
	 * Date created - 19th Feb 2016
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
//	02 - Delete AT&T Access ID - ATTAID Profile
	public static void ValidateDeleteATTAccessIDFrame(ITestContext testContext) throws Exception 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
		Boolean bProfilePage, bDelete, bCancel, bverbiage, bContinueButton, bCloseLink, bDeleteFrame, bHeader;
		try
		{
//			Login to an account using an AT&T Access ID that has no other accounts linked and navigate to the My Profile page by selecting profile from the secondary navigation links.
			Report.OperationPoint(testContext.getName(), "1-Login to an account using an AT&T Access ID that has no other accounts linked and navigate to the My Profile page by selecting profile from the secondary navigation links.");
			try
			{
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecNav, null, lDriver);
			}
			catch(Exception e)
			{
				Report.TestPoint(testContext.getName(), "Profile Link not Selected", "True", "False", true);
			}
			
			bProfilePage = UI.WaitForObject(oR_ATT.txtATTAccess, 20, lDriver);
			Report.ValidationPoint(testContext.getName(), "1-Validate the user is directed to the My Profile page and has My Profile as the header of the page.", "True", bProfilePage.toString(), true);

//			Select the Delete AT&T Access ID link located in the Global Account manager section.
			bDelete = UI.WaitForObject(oR_ATT.lnkDeleteATTAccessID, 2, lDriver);
			Report.TestPoint(testContext.getName(), "2-Select the Delete AT&T Access ID link located in the Global Account manager section.", "True", bDelete.toString(), true);
			oR_ATT.lnkDeleteATTAccessID.click();
			
//			"Validate the Delete AT&T Access ID div layer displays: - Delete AT&T Access ID  - Close link Verbiage informing the customer they will be required to re-register the account. - Cancel button - Continue button"

			Report.OperationPoint(testContext.getName(), "Validate the Delete AT&T Access ID div layer displays");
			
			bDeleteFrame = UI.WaitForObject(oR_ATT.frmDeleteProfile, 10, lDriver);
			if(bDeleteFrame)
			{
				lDriver.switchTo().frame(oR_ATT.frmDeleteProfile);
				bHeader = UI.WaitForObject(oR_ATT.txtDeleteATTAccessID, 10);
				if(!bHeader)
					Report.ValidationPoint(testContext.getName(), "Delete AT&T Access ID is not present On Delete Modal", "true", "false", true);
				
				bCloseLink =  UI.WaitForObject(oR_ATT.lnkClose, 10);
				if(!bCloseLink)
					Report.ValidationPoint(testContext.getName(), "Close Link is not present On Delete Modal", "true", "false", true);
	
				bContinueButton =  UI.WaitForObject(oR_ATT.btnContinue, 10);
				if(!bContinueButton)
					Report.ValidationPoint(testContext.getName(), "Continue Button is not present On Delete Modal", "true", "false", true);
	
				bverbiage =  UI.WaitForObject(oR_ATT.txtDeleteFrameVerbiage, 10);
				if(!bverbiage)
					Report.ValidationPoint(testContext.getName(), "Verbiage informing the customer they will be required to re-register the account is not present On Delete Modal", "true", "false", true);
				
				bCancel =  UI.WaitForObject(oR_ATT.lnkCancel, 10);
				if(!bCancel)
					Report.ValidationPoint(testContext.getName(), "Link Cancel is not present On Delete Modal", "true", "false", true);
	
				Report.ValidationPoint(testContext.getName(), "Validate the Delete AT&T Access ID div layer displays: - Delete AT&T Access ID  - Close link Verbiage informing the customer they will be required to re-register the account. - Cancel button - Continue button", "true", 
										String.valueOf(bHeader && bCloseLink && bContinueButton && bverbiage && bCancel), true);

				oR_ATT.lnkClose.click();
				lDriver.switchTo().defaultContent();
			}
			else
				Report.ValidationPoint(testContext.getName(), "1-Validate the user is directed to the My Profile page and has My Profile as the header of the page.", "True", bDeleteFrame.toString(), true);

			
		}
		catch(Exception e)
		{
			
		}
	}
	
	/**************************************************************
	 * Function Name - ValidateDeLinkATTAccessID()
	 * Description - Validate De-link AT&T Access ID div layer displays
 	 * Test Case -  04c - De-link CPNI account - My Profile page - ATTAID Profile
	 * Parameters - None
	 * Date created - 22nd Feb 2016
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_04c_DeLinkATTAccessID
	public static void ValidateDeLinkATTAccessID(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			OR_ATT_AccessID_Profile oR_ATT_AccessID_Profile = PageFactory.initElements(lDriver, OR_ATT_AccessID_Profile.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
			
			oR_AccountOverview.lnkProfileSecondaryNav.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Profile link from secondary navigation");
			Thread.sleep(3000);
			
			boolean bMyProfilePage = UI.WaitForObject(oR_Profile.txtMyProfileTitle, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that Profile landing page is displayed", "true",String.valueOf(bMyProfilePage), true);
			
			new Actions(lDriver).moveToElement(oR_Profile.txtAccountInformation).perform();
			
			//Click on Manage my ATT Access ID
			oR_Profile.lnkAccessID.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on Manage my ATT Access ID profile");
			
			boolean bATTAccessIDPage = UI.WaitForObject(oR_ATT_AccessID_Profile.txtATTAccessIDHeader, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that ATT Access ID Profile page is displayed", "true", String.valueOf(bATTAccessIDPage), true);
				
			boolean bDeLinkAccessID = UI.WaitForObject(oR_ATT_AccessID_Profile.lnkDeLink, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that De-link ATT Access ID link is displayed", "true",String.valueOf(bDeLinkAccessID), true);
			oR_ATT_AccessID_Profile.lnkDeLink.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on De-link ATT Access ID link");
			Thread.sleep(3000);
			String sMainWindowHandle = lDriver.getWindowHandle();
			WebElement frameHandle = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
			lDriver.switchTo().frame(frameHandle);
				/* 
				 * Validate the De-link AT&T Access ID div layer displays the following:
					 - De-link AT&T Access ID
					 - Close link
					 - Verbiage informing the customer they will be able to use the member ID to access emails and other applications associated with the member ID
					 - Verbiage informing the customer this may impact account management access from a mobile device
					 - Verbiage asking if the customer is sure they want to de-link the single ID
					 - Cancel button
					 - Continue button
				*/
				
				boolean bDeLinkAccessHeading = UI.WaitForObject(oR_ATT_AccessID_Profile.txtDeLinkAccountHeading, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that De-link account heading is displayed in the modal window", "true",String.valueOf(bDeLinkAccessHeading), true);
				
				boolean bDelinkAccVerbiage = UI.WaitForObject(oR_ATT_AccessID_Profile.txtDelinkModalVerbiage, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that a verbiage is displayed in modal window asking if the customer is sure they want to de-link the Access ID", "true",String.valueOf(bDelinkAccVerbiage), true);
				
				boolean bClose = UI.WaitForObject(oR_ATT_AccessID_Profile.lnkClose, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Close button is displayed", "true",String.valueOf(bClose), true);
				
				boolean bContinue = UI.WaitForObject(oR_ATT_AccessID_Profile.lnkContinue, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Continue button is displayed", "true",String.valueOf(bContinue), true);
				
				boolean bCancel = UI.WaitForObject(oR_ATT_AccessID_Profile.lnkCancel, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that Cancel button is displayed", "true",String.valueOf(bCancel), true);
				
//				/Select the Close link in the De-link AT&T Access ID div layer.
				oR_ATT_AccessID_Profile.lnkClose.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on Close button");
				
			lDriver.switchTo().window(sMainWindowHandle);
			
			//Validate the div layer closes and the user is returned to the My Profile page.
			boolean bHomePage = UI.WaitForObject(oR_ATT_AccessID_Profile.txtATTAccessIDHeader, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that div layer closes and the user is returned to the My Profile page", "true", String.valueOf(bHomePage), true);
			
			oR_ATT_AccessID_Profile.lnkDeLink.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on De-link ATT Access ID link");
			
			Thread.sleep(3000);
			String sMainWindowHandle2 = lDriver.getWindowHandle();
			WebElement frameHandle2 = lDriver.findElement(By.xpath("//div[@id='cboxContent']//iframe"));
			lDriver.switchTo().frame(frameHandle2);
			
				boolean bModalWindow = UI.WaitForObject(oR_ATT_AccessID_Profile.txtDeLinkAccountHeading, UI.iObjTimeOut, lDriver);
				Report.ValidationPoint(testContext.getName(), "Verify that De-link account modal window is reponed again", "true",String.valueOf(bModalWindow), true);
				
				//Select the Cancel button in the De-link AT&T Access ID div layer.
				oR_ATT_AccessID_Profile.lnkCancel.click();
				Report.OperationPoint(testContext.getName(), "Operational - Clicked on Cancel button");
			
			lDriver.switchTo().window(sMainWindowHandle2);
			
			//Validate the div layer closes and the user is returned to the My Profile page.
			boolean bHomePage2 = UI.WaitForObject(oR_ATT_AccessID_Profile.txtATTAccessIDHeader, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that div layer closes and the user is returned to the My Profile page", "true", String.valueOf(bHomePage2), true);
			
			//Select the billing account for CPNI notifications from the drop down and select continue [SKIPPED step since, it will burn data]

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	
	/**************************************************************
	 * Function Name - ValidateErrorMessageEditBillingContactPg 
	 * Description- vaidation of Error message on edit billing contact information page
	 * Parameters - 
	 * Date created - 24th Feb 2016
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//PRF10407
	public static void ValidateErrorMessageEditBillingContactPg(ITestContext testContext) throws Exception 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
		try
		{
			//Click on profile sec nav link
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav, null, lDriver);
			//Validate profile landing page
			boolean bProfilePg = UI.WaitForObject(oR_Profile.txtMyProfileTitle, 20, lDriver);
			Report.TestPoint(testContext.getName(), "Validate profile landing page", "true", String.valueOf(bProfilePg), true);
			//Validate Billing Contact Information section
			boolean bBilling = UI.WaitForObject(oR_Profile.txtBillingContactInformationTitle, 5, lDriver);
			Report.TestPoint(testContext.getName(), "Validate Billing Contact Information section", "true", String.valueOf(bBilling), true);
			//Validate edit link in Billing Contact Information section
			boolean bLnkEdit = UI.WaitForObject(oR_Profile.lnkEditBillingContactInformation, 5, lDriver);
			Report.TestPoint(testContext.getName(), "Validate edit link in Billing Contact Information section", "true", String.valueOf(bLnkEdit), true);
			//Click on Edit link
			Report.OperationPoint(testContext.getName(), "Click on Edit link");
			oR_Profile.lnkEditBillingContactInformation.click();
			//Validate my profile page
			boolean bProfilePg1 = UI.WaitForObject(oR_Profile.txtMyProfileTitle, 20, lDriver);
			Report.TestPoint(testContext.getName(), "Validate profile landing page", "true", String.valueOf(bProfilePg1), true);
			//Validate User should be displayed with an error on the page
			boolean bError = UI.WaitForObject(lDriver.findElement(By.xpath("//div[@id='main-container']//div[@id='errorDiv']")), 5, lDriver);
			if(bError)
			{
				Report.TestPoint(testContext.getName(), "Validate Error stating the user cannot make any changes as the account is less than 30days old", "true", String.valueOf(bError), true);
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Validate Error stating the user cannot make any changes as the account is less than 30days old", "true", String.valueOf(bError), true);
			}
		}
		catch(Exception e)
		{
		
		}
	}
	
	/**************************************************************
	 * Function Name - ValidateFraudLockStatusManageAccAccessPg 
	 * Description- vaidation of Error message on edit billing contact information page
	 * Parameters - 
	 * Date created - 24th Feb 2016
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/
	//PRF21224
	public static void ValidateFraudLockStatusManageAccAccessPg(ITestContext testContext) throws Exception 
	{
		WebDriver lDriver = UI.getDriver(testContext.getName());
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
		OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
		OR_ATT_AccessID_Profile oR_OR_ATT_AccessID_Profile = PageFactory.initElements(lDriver, OR_ATT_AccessID_Profile.class);
		OR_MyAccountAccess oR_OR_MyAccountAccess = PageFactory.initElements(lDriver, OR_MyAccountAccess.class);
		try
		{
			//Click on profile sec nav link
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav, null, lDriver);
			//Validate profile landing page
			boolean bProfilePg = UI.WaitForObject(oR_Profile.txtMyProfileTitle, 20, lDriver);
			Report.TestPoint(testContext.getName(), "Validate profile landing page", "true", String.valueOf(bProfilePg), true);
			//Validate manage my AT&T Access ID link
			boolean bAccess = UI.WaitForObject(oR_Profile.lnkAccessID, 5, lDriver);
			Report.TestPoint(testContext.getName(), "Validate manage my AT&T Access ID link", "true", String.valueOf(bAccess), true);
			//Click on manage my AT&T Access ID link
			Report.OperationPoint(testContext.getName(), "Click on manage my AT&T Access ID link");
			oR_Profile.lnkAccessID.click();
			Thread.sleep(4000);
			//Validate My AT&T Access ID Profile
			boolean bAccessPg = UI.WaitForObject(oR_OR_ATT_AccessID_Profile.txtMyATTAccessIDProfile, 20, lDriver);
			Report.TestPoint(testContext.getName(), "Validate My AT&T Access ID Profile", "true", String.valueOf(bAccessPg), true);
			//Validate Manage account access link
			boolean bManageLnk = UI.WaitForObject(oR_Profile.lnkManageAccountAccess, 5, lDriver);
			Report.TestPoint(testContext.getName(), "Validate Manage account access link", "true", String.valueOf(bManageLnk), true);
			//Click on Manage account access link
			Report.OperationPoint(testContext.getName(), "Click on Manage account access link");
			oR_Profile.lnkManageAccountAccess.click();
			Thread.sleep(4000);
			//Validate My Account Access page
			boolean bAccount = UI.WaitForObject(oR_OR_MyAccountAccess.txtMyAccountAccessHeading, 50, lDriver);
			Report.TestPoint(testContext.getName(), "Validate My Account Access page", "true", String.valueOf(bAccount), true);
			//Validate the note and fraud lock status for secondary's AAID is fraud locked
			boolean bNote = UI.WaitForObject(lDriver.findElement(By.xpath("//*[contains(text(),'One or more users with secondary access')]")), 5, lDriver);
			Report.TestPoint(testContext.getName(), "Validate the note and fraud lock status for secondary's AAID is fraud locked", "true", String.valueOf(bNote), true);
			//Validate The note and fraud lock status should not be displayed
			UI.VerifyElementNotPresent(oR_Profile.txtNoteStatus, "fraud lock status");
		}
		catch(Exception e)
		{
		
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyAddAutopayStatus()
	 * Description - 101 - I57284 -ATTAID  Profile - Add AutoPay Status to the Payment Profile section - for not enrolled 
 	 * Test Case -  101 - I57284 -ATTAID  Profile - Add AutoPay Status to the Payment Profile section - for not enrolled 
	 * Parameters - None
	 * Date created - 24th Feb 2016
	 * Developed by - Clint John
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_101_I57284_AddAutopayStatus
	public static void VerifyAddAutopayStatus(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_ATT_AccessID_Profile oR_ATT_AccessID_Profile = PageFactory.initElements(lDriver, OR_ATT_AccessID_Profile.class);
			OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
			OR_AutoPayEnrollment oR_AutoPayEnrollment = PageFactory.initElements(lDriver, OR_AutoPayEnrollment.class);
			Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());

			//1. Vlaidate that User should login successfully and navigated to the dashboard. On overview check the Enroll for Auto Pay link.
			boolean bEnrollInAutopay = UI.WaitForObject(oR_AccountOverview.lnkEnrollInAutopay_OverviewPage, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that 'Enroll in Autopay' link is displayed in Overview page", "true",String.valueOf(bEnrollInAutopay), true);
			
			//2. Go to the  My Linked Accounts Link from Top Gray Bar --> Go To My Profile --> click on AT&T Access ID Profile
			new Actions(lDriver).moveToElement(oR_AccountOverview.lnkMyLinkedAccounts).moveToElement(oR_AccountOverview.lnkGoToMyProfile).click().build().perform();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Go to my profile' from My linked accounts drop-down");
			
			boolean bATTAccessIDPage = UI.WaitForObject(oR_ATT_AccessID_Profile.txtATTAccessIDHeader, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that AT&T access ID page is displayed", "true",String.valueOf(bATTAccessIDPage), true);
			
			//Click on link AT&T Access ID Profile
			boolean bATTAccessIDProfileLink = UI.WaitForObject(oR_ATT_AccessID_Profile.lnkATTAccessIDProfile, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that link for 'AT&T access ID profile' is displayed", "true",String.valueOf(bATTAccessIDProfileLink), true);
			oR_ATT_AccessID_Profile.lnkATTAccessIDProfile.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'ATT Accees ID profile' from My link");
			Thread.sleep(2000);
			
			boolean bATTAccessPage = UI.WaitForObject(oR_ATT.txtATTAccess, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify that user is redirected to AT&T access ID page", "true",String.valueOf(bATTAccessPage), true);

			//3. Validate that a self expalnatory text should be displayed below the Stored Payment Methods section header
			boolean bStoredPayment = UI.WaitForObject(oR_ATT.txtStoredPaymentMethod, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify header for Stored payment methods is displayed", "true",String.valueOf(bStoredPayment), true);
			boolean bStoredExplanation = UI.WaitForObject(oR_ATT.txtStoredPaymentMethodExplanation, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that a self expalnatory text should be displayed below the Stored Payment Methods section header", "true",String.valueOf(bStoredExplanation), true);
			
			//4. Validate For the accounts not enrolled in Autopay, the Not Enrolled status should be displayed against the Autopay Status label in Payment profile  section
			boolean bNotEnrolled = UI.WaitForObject(oR_ATT.txtNotEnrolledAutopay, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate For the accounts not enrolled in Autopay, the Not Enrolled status should be displayed against the Autopay Status label in Payment profile  section", "true",String.valueOf(bNotEnrolled), true);
			
			//5. Click Enroll in autopay, Auto Pay Enrollment page should be displayed.
			oR_ATT.lnkEnrollInAutopay.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Enroll in Autopay' link");
			Thread.sleep(2000);
			boolean bAutoPayPage = UI.WaitForObject(oR_AutoPayEnrollment.txtAutoPayEnrollment, UI.iObjTimeOut, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify user is successfully redirected to AutoPay Enrollment page", "true",String.valueOf(bAutoPayPage), true);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}
	/**************************************************************
	 * Function Name - VerifyAccountInfoTabValidations()
	 * Description -  Account Info tab - ATTAID Profile - Wireless
 	 * Test Case -   Account Info tab - ATTAID Profile - Wireless 
	 * Parameters - None
	 * Date created - 1st March 2016
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	//UAT_ProfileAccInfoTab
	public static void VerifyAccountInfoTabValidations(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Profile oR_Profile = PageFactory.initElements(lDriver, OR_Profile.class);
			OR_ATT_AccessID_Profile oR_ATT_AccessID_Profile = PageFactory.initElements(lDriver, OR_ATT_AccessID_Profile.class);
			
			//Click on profile sec nav link
			UI.SelectServiceFromSecondaryNavigation(oR_AccountOverview.lnkProfileSecondaryNav, null, lDriver);
			//Validate my profile page
			boolean bProfilePage = UI.WaitForObject(oR_Profile.txtMyProfileTitle, 20, lDriver);
			Report.TestPoint(testContext.getName(), "Verify my profile page", "true",String.valueOf(bProfilePage), true);
			//Validate Account Information tab
			boolean bAccInfo = UI.WaitForObject(oR_Profile.txtAccountInformationDefault, 5, lDriver);
			Report.TestPoint(testContext.getName(), "Verify Account Information tab", "true",String.valueOf(bAccInfo), true);
			//Validate - AT&T Access ID Profile banner
			boolean bBanner = UI.WaitForObject(oR_Profile.lnkAttAccessIdProfile, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - AT&T Access ID Profile banner", "true",String.valueOf(bBanner), true);
			//Account Module
			boolean bAccModule = UI.WaitForObject(oR_Profile.txtAccountModule, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Account Module", "true",String.valueOf(bAccModule), true);
			//Account Information Section
			boolean bInfo = UI.WaitForObject(oR_Profile.txtAccountNames, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Information Section", "true",String.valueOf(bInfo), true);
			//Billing Contact Information section
			boolean bContact = UI.WaitForObject(oR_Profile.txtBillingContactInformationTitle, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Billing Contact Information section", "true",String.valueOf(bContact), true);
			//Payment Option section
			boolean bBillOptions = UI.WaitForObject(oR_Profile.txtBillOptions, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Bill Option section", "true",String.valueOf(bBillOptions), true);
			//Paper Bill Option section
			boolean bPaperBillOptions = UI.WaitForObject(oR_Profile.txtPaperPaperless, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Paper Bill Option section", "true",String.valueOf(bPaperBillOptions), true);
			//Authorized User - Retail Store section
			boolean bAutho = UI.WaitForObject(oR_Profile.txtAuthorizedUsers, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Authorized User - Retail Store section", "true",String.valueOf(bAutho), true);
			
			//Validate
			//- AT&T Access Id Profile
			boolean bAccID = UI.WaitForObject(oR_Profile.lnkAccessID, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - AT&T Access Id Profile", "true",String.valueOf(bAccID), true);
			//- Edit your AT&T Access ID and manage profile settings.
			boolean bEditAccess = UI.WaitForObject(oR_Profile.lnkAccessID, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Edit your AT&T Access ID and manage profile settings", "true",String.valueOf(bEditAccess), true);
			//- Manage my AT&T Access ID>
			boolean bManage = UI.WaitForObject(oR_Profile.lnkAccessID, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Manage my AT&T Access ID", "true",String.valueOf(bManage), true);
			
			//Validate the Account Module accurately displays the following information
			//- Nickname
			//- Account Number
			boolean bAccouuntNo = UI.WaitForObject(oR_Profile.txtAccountNumber, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate the Account Module accurately displays the following information - Account Number", "true",String.valueOf(bAccouuntNo), true);
			//- Account selection module
			// not present with this data
			
			//Validate that the Account Information Section accurately displays the following information - edit link
			boolean bLnkEdit = UI.WaitForObject(oR_Profile.lnkEditAccName, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate that the Account Information Section accurately displays the following information - edit link", "true",String.valueOf(bLnkEdit), true);
			//Validate that the Account Information Section accurately displays the following information - Change Passcode link(present with this data)
			boolean bChangePasscode = UI.WaitForObject(oR_Profile.lnkChangePasscode, 5, lDriver);
			if(bChangePasscode)
			Report.ValidationPoint(testContext.getName(), "Validate that the Account Information Section accurately displays the following information - change passcode link", "true",String.valueOf(bChangePasscode), true);
			
			//Validate that the Billing Contact Information Section accurately displays the following information:
			//- Billing Contact Information (header)
			Report.OperationPoint(testContext.getName(), "Operational - Validate that the Billing Contact Information Section accurately displays the following information: ");
			//- Edit link / with helpful info icon (?)
			boolean bEdit = UI.WaitForObject(oR_Profile.lnkEditBillingContactInfo, 5, lDriver);
			boolean bEditQues = UI.WaitForObject(oR_Profile.imgEditIcon, 5, lDriver);
			if(bEdit && bEditQues)
			{
				Report.ValidationPoint(testContext.getName(), "Validate - Edit link / with helpful info icon (?)", "true","true", true);
			}
			//- Note describing section
			boolean bNote = UI.WaitForObject(oR_Profile.txtNoteBilling, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Note describing section", "true",String.valueOf(bNote), true);
			//- Billing Name
			boolean bBN = UI.WaitForObject(oR_Profile.txtBillName, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Billing Name", "true",String.valueOf(bBN), true);
			//- Attention
			boolean bAttention = UI.WaitForObject(oR_Profile.txtAttention, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Attention", "true",String.valueOf(bAttention), true);
			//- URB Code (dynamic: will only appear for Puerto Rico or Virgin Islands address) - not present
			//- Address line 1 / with helpful info icon (?)
			boolean bStrAdd = UI.WaitForObject(oR_Profile.txtStreetAddr, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Address line 1 / with helpful info icon (?)", "true",String.valueOf(bStrAdd), true);
			//- Address line 2
			boolean bStrAdd1 = UI.WaitForObject(oR_Profile.txtAppt, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Address line 2", "true",String.valueOf(bStrAdd1), true);
			//- City
			boolean bCity = UI.WaitForObject(oR_Profile.txtCity, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - City", "true",String.valueOf(bCity), true);
			//- State
			boolean bState = UI.WaitForObject(oR_Profile.txtState, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - State", "true",String.valueOf(bState), true);
			//- ZIP Code
			boolean bZIPCode = UI.WaitForObject(oR_Profile.txtZipCode, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - ZIP Code", "true",String.valueOf(bZIPCode), true);
			//- Home Phone
			boolean bHomePhone = UI.WaitForObject(oR_Profile.txtHomePhone, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Home Phone", "true",String.valueOf(bHomePhone), true);
			//- Work Phone
			boolean bWorkPhone = UI.WaitForObject(oR_Profile.txtWorkPhone, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Work Phone", "true",String.valueOf(bWorkPhone), true);
			//- Work Extension
			boolean bExtension = UI.WaitForObject(oR_Profile.txtWorkExt, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Work Extension", "true",String.valueOf(bExtension), true);
			//- Billing Email Address
			boolean bEmail = UI.WaitForObject(oR_Profile.txtBillingEmailAddr, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Billing Email Address", "true",String.valueOf(bEmail), true);
			
			//Validate that the Paper Bill Options Section accurately displays the following information:
			Report.OperationPoint(testContext.getName(), "Operational - Validate that the Paper Bill Options Section accurately displays the following information: ");
			//- Bill Options
			boolean bBillOptions1 = UI.WaitForObject(oR_Profile.txtBillOptions, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Bill Options", "true",String.valueOf(bBillOptions1), true);
			//- Bill Language (dynamic: English or Spanish)
			boolean btxtBillLang = UI.WaitForObject(oR_Profile.txtBillLang, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Bill Language (dynamic: English or Spanish)", "true",String.valueOf(btxtBillLang), true);
			//- Change Setting link / with helpful info icon (?)
			boolean blnkChange = UI.WaitForObject(oR_Profile.lnkChangeSettings, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Change Setting link / with helpful info icon (?)", "true",String.valueOf(blnkChange), true);
			//- Paper bill setting
			boolean btxtPaper = UI.WaitForObject(oR_Profile.txtPaper, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - bill setting", "true",String.valueOf(btxtPaper), true);
			//- Current status (receiving paper bill , not receiving paper bill) - not present
			
			//- Change setting link/ with helpful info icon (?)
			boolean blnkChange1 = UI.WaitForObject(oR_Profile.lnkChangeSettings, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Change Setting link / with helpful info icon (?)", "true",String.valueOf(blnkChange1), true);
			//- CheckFree eBill setting (dynamic: will only display if customer has CheckFree) - not present
			
			//Validate that the Authorized users Section accurately displays the following information:
			Report.OperationPoint(testContext.getName(), "Operational - Validate that the Authorized users Section accurately displays the following information: ");
			//- Note describing section
			boolean btxtNoteAutho = UI.WaitForObject(oR_Profile.txtNoteAutho, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Note describing section", "true",String.valueOf(btxtNoteAutho), true);
			//- Add/Delete New User link / with helpful info icon (?)
			boolean bNewUser = UI.WaitForObject(oR_Profile.lnkAddNewUser, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate - Add/Delete New User link / with helpful info icon (?)", "true",String.valueOf(bNewUser), true);
			
			//Validate that when scrolled over each Helpful Info Icon (?) displays a pop-up box that includes a brief description of the field it is next to.  
			//Verify that the content in each pop-up box accurately describes the item that it is next to and that grammar and spelling is correct.
			Report.OperationPoint(testContext.getName(), "Operational - Validate that when scrolled over each Helpful Info Icon (?) displays a pop-up box that includes a brief description of the field it is next to");
			// Primary Account Holder
			List<WebElement> imgIcon = lDriver.findElements(By.xpath("//img[@title='Help']"));
			if(imgIcon.size()>0)
			{
				for(int i=0;i<imgIcon.size();i++)
				{
					imgIcon.get(i).click();
					Thread.sleep(2000);
					Report.ValidationPoint(testContext.getName(), "Validate - Add/Delete New User link / with helpful info icon (?)", "true",String.valueOf(bNewUser), true);
				}
				
			}
			
			//Clicking on Select the AT&T Access ID banner
			Report.OperationPoint(testContext.getName(), "Operational - Clicking on Select the AT&T Access ID banner");
			oR_Profile.lnkAttAccessIdProfile.click();
			//Validate My AT&T Access ID Profile page
			boolean bAccess = UI.WaitForObject(oR_ATT_AccessID_Profile.txtMyATTAccessIDProfile, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "Validate My AT&T Access ID Profile page", "true",String.valueOf(bAccess), true);
			lDriver.navigate().back();
			Thread.sleep(10000);
			
			//Click on change passcode link
			boolean bChangePasscode1 = UI.WaitForObject(oR_Profile.lnkChangePasscode, 10, lDriver);
			if(bChangePasscode1)
			{
				oR_Profile.lnkChangePasscode.click();
				//Validate Change security passcode
				boolean bChangeSecurityPasscode = UI.WaitForObject(oR_Profile.txtChangeSecurityPasscode, 5, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate Change security passcode page", "true",String.valueOf(bChangeSecurityPasscode), true);
				lDriver.navigate().back();
				Thread.sleep(4000);
			}
			
			//Verify when the ‘Espanol’ link is selected, the user’s browser language is changed and the page is refreshed accordingly in Spanish.
			//Clicking on language change setting link
			boolean bChangeSetting = UI.WaitForObject(oR_Profile.lnkChangeSettingLang, 5, lDriver);
			if(bChangeSetting)
			{
				Report.OperationPoint(testContext.getName(), "Operational - Clicking on language change setting link");
				oR_Profile.lnkChangeSettingLang.click();
				Thread.sleep(7000);
				boolean bRad = UI.WaitForObject(oR_Profile.radEspanol, 25, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate My profile page", "true",String.valueOf(bRad), true);
				oR_Profile.radEspanol.click();
				boolean bSaveChanges = UI.WaitForObject(oR_Profile.btnSaveChanges1, 25, lDriver);
				Report.ValidationPoint(testContext.getName(), "Validate save changes button", "true",String.valueOf(bSaveChanges), true);
				oR_Profile.btnSaveChanges1.click();
				//Confirmation page
				boolean bConfirmation = UI.WaitForObject(oR_Profile.btnContinue, 25, lDriver);
				if(bConfirmation)
				{
					Report.ValidationPoint(testContext.getName(), "Validate Confirmation page", "true",String.valueOf(bConfirmation), true);
					Report.OperationPoint(testContext.getName(), "Operational - Clicking on continue button");
					oR_Profile.btnContinue.click();
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.TestPoint(testContext.getName(), "true", "true", "Failed to validate"+e.getMessage(), true);

		}
		
	}

}





















