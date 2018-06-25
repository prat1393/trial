package com.AppSpecificLibrary;

/**************************************************************
 * Function Name -  VerifyForEmptyFooterInRegistrationPage()
 * Description- This function validates that an empty footer is not presented on the registration page 
 * Parameters - None 
 * Date created - 17th Mar 2015
 * Developed by - Clint John
 * Last Modified By - 
 * Last Modified Date - 17th Mar 2015
 * @throws AWTException 
 * @throws IOException 
 * @throws HeadlessException 
 * @throws ParseException 
 ***************************************************************/

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;

import com.OR.MyATT.OR_ATT;
import com.OR.MyATT.OR_AccountOverview;
import com.OR.MyATT.OR_AccountRegistration;
import com.OR.MyATT.OR_Login;
import com.OR.MyATT.OR_PhoneAndDevices;
import com.OR.MyATT.OR_PlentiLogin;
import com.SupportingFiles.IO;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;
import com.SupportingFiles.UI;

public class Login extends LaunchAndLogout {
	static Hashtable<String, String> sTestParams = new Hashtable<String, String>();
	
	public static void VerifyForEmptyFooterInRegistrationPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		 WebDriver lDriver = UI.getDriver(testContext.getName()); 
	        Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
	        OR_ATT oR_ATT = PageFactory.initElements(lDriver, OR_ATT.class);
	        OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
	        OR_AccountRegistration oR_AccountRegistration= PageFactory.initElements(lDriver, OR_AccountRegistration.class);
	
		try
		{
			//Verify that 'Register Today!' link is displayed below Account selection tab in Login page
			boolean bRegisterToday = UI.WaitForObject(oR_Login.lnkRegisterToday, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify that 'Register Today' link is displayed below account selection tab", "true", String.valueOf(bRegisterToday), true);
			oR_Login.lnkRegisterToday.click();
			Report.OperationPoint(testContext.getName(), "Operational - Clicked on 'Register Today!' link");
			
			//Check if the footer is not displayed in Registration page
			
			boolean bManageAccHeading = UI.WaitForObject(oR_AccountRegistration.txtAccInfoSubHeading, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify that Registration page is displayed", "true", String.valueOf(bManageAccHeading), true);
			boolean bEmptyFooter = UI.VerifyElementNotPresent(oR_AccountRegistration.tblEmptyFooter,"Footer");
			Report.TestPoint(testContext.getName(),"Verify that an empty footer is NOT displayed on the registration page", "true", String.valueOf(bEmptyFooter), true);
			
			
		}catch (Exception e)
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}
	
	}
	
	
	/**************************************************************
	 * Function Name -  VerifyCloseCTA()
	 * Description- This function validates the Microsite LoginPage and a new window opened after clicking on 'Which id Should I Use'
	 * Parameters - None 
	 * Date created - 29th June 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	//LGN04338

	public static void VerifyCloseCTA(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		 WebDriver lDriver = UI.getDriver(testContext.getName()); 
	     
	        OR_PlentiLogin oR_PlentiLogin = PageFactory.initElements(lDriver, OR_PlentiLogin.class);
	      
		try
		{
			//Verify Page title
			String Url = lDriver.getCurrentUrl();
			   if(Url.contains("PlentiLogin"))
			   {
				   Report.TestPoint(testContext.getName(), "Validate Page title is 'PlentiLogin'", "True", "True", true);
			   }
			   else
			   {
				   Report.TestPoint(testContext.getName(), "Validate Page title is 'PlentiLogin'", "True", "false", true);
			   }
			   
			//Verify Log in to your account to begin link
			boolean bAccessAcc = UI.WaitForObject(oR_PlentiLogin.txtLoginToBegin, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Log in to your account to begin is displayed", "true", String.valueOf(bAccessAcc), true);
			
			//Verify Label for User ID
			boolean bUserID = UI.WaitForObject(oR_PlentiLogin.txtUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Label for User ID is displayed", "true", String.valueOf(bUserID), true);
			
			//Verify Entry box for User ID
			boolean bUserIDBox = UI.WaitForObject(oR_PlentiLogin.edtUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Entry box for User ID is displayed", "true", String.valueOf(bUserIDBox), true);
			
			//Verify Label for Password
			boolean bPwd = UI.WaitForObject(oR_PlentiLogin.txtPwd, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Label for Password is displayed", "true", String.valueOf(bPwd), true);
			
			//Verify 'What ID should I use?' link
			boolean bWhichID = UI.WaitForObject(oR_PlentiLogin.lnkWhichID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify 'What ID should I use?' is displayed", "true", String.valueOf(bWhichID), true);
			
			//Verify Save User ID checkbox
			boolean bchkbox = UI.WaitForObject(oR_PlentiLogin.boxSave, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Save User ID checkbox is displayed", "true", String.valueOf(bchkbox), true);
			
			//Verify Save User ID label
			boolean bLabel = UI.WaitForObject(oR_PlentiLogin.txtSaveUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Save User ID label is displayed", "true", String.valueOf(bLabel), true);

			//Verify Login button
			boolean bLogin = UI.WaitForObject(oR_PlentiLogin.btnLogin, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Login button is displayed", "true", String.valueOf(bLogin), true);
			
			//Verify  Forgot UserID link 
			boolean bForgotID = UI.WaitForObject(oR_PlentiLogin.lnkForgotUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify  Forgot UserID link is displayed", "true", String.valueOf(bForgotID), true);
			
			//Verify Forgot Password link
			boolean bForgotPwd = UI.WaitForObject(oR_PlentiLogin.lnkForgotPwd, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Forgot Password link is displayed", "true", String.valueOf(bForgotPwd), true);
		
			//Verify Register Today link
			boolean bRegisterToday = UI.WaitForObject(oR_PlentiLogin.lnkRegisterToday, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Register Today link is displayed", "true", String.valueOf(bRegisterToday), true);
			
			// Validation on 'Which User ID should I Use?' model
			  if(UI.WaitForObject(oR_PlentiLogin.lnkWhichID, UI.iObjTimeOut)){
				  Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is displayed", true);
				  oR_PlentiLogin.lnkWhichID.click();
				  Thread.sleep(5000);
				  
				  //Navigate to 'Which User ID Should I use?' frame
				  lDriver.switchTo().frame(oR_PlentiLogin.frmWhichID);
				  Report.OperationPoint(testContext.getName(), "'Which User ID Should I use?' modal is displayed");
				  
				  //Validate Page Title
				  if(UI.WaitForObject(oR_PlentiLogin.txtWhichIDTitle, UI.iObjTimeOut)){
					Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is displayed", true);  
				  }else{
						Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is NOT displayed", true);  
				  }
				  
				  
				  //Validate Close CTA is displayed
				  if(UI.WaitForObject(oR_PlentiLogin.lnkCloseOnWhichID, 2)){
					Report.ValidationPoint(testContext.getName(), "Validate Close CTA is displayed", "Close CTA is displayed", "Close CTA is displayed", true);  
				  }else{
					  Report.ValidationPoint(testContext.getName(), "Validate Close CTA is displayed", "Close CTA is displayed", "Close CTA is NOT displayed", true);	 
				  }

				  
				  //Validate informational text is displayed
				  if(UI.WaitForObject(oR_PlentiLogin.txtInfotmationalTxtOnWhichID, 2)){
					Report.ValidationPoint(testContext.getName(), "Validate Informational text is displayed", "Informational text is displayed", "Informational text is displayed", true);  
				  }else{
					  Report.ValidationPoint(testContext.getName(), "Validate Informational text is displayed", "Informational text is displayed", "Informational text is NOT displayed", true);    
				  }
				  
				  //Validate Wireless Account section is displayed
				  if(UI.WaitForObject(oR_PlentiLogin.txtWirelessAccountSectionOnWhichID, 2)){
					Report.ValidationPoint(testContext.getName(), "Validate Wireless Account section is displayed", "Wireless Account section is displayed", "Wireless Account section is displayed", true);  
				  }else{
					Report.ValidationPoint(testContext.getName(), "Validate Wireless Account section is displayed", "Wireless Account section is displayed", "Wireless Account section is NOT displayed", true);   
				  }

				  //Validate Combined Bill section is displayed
				  if(UI.WaitForObject(oR_PlentiLogin.txtCombinedBillSectionOnWhichID, 2)){
					Report.ValidationPoint(testContext.getName(), "Validate Combined Bill section is displayed", "Combined Bill section is displayed", "Combined Bill section is displayed", true);  
				  }else{
					  Report.ValidationPoint(testContext.getName(), "Validate Combined Bill section is displayed", "Combined Bill section is displayed", "Combined Bill section is NOT displayed", true);     
				  }

				  //Validate U-verse service section is displayed
				  if(UI.WaitForObject(oR_PlentiLogin.txtUverseServiceSectionOnWhichID, 2)){
					Report.ValidationPoint(testContext.getName(), "Validate U-verse service section is displayed", "U-verse service section is displayed", "U-verse service section is displayed", true);  
				  }else{
					  Report.ValidationPoint(testContext.getName(), "Validate U-verse service section is displayed", "U-verse service section is displayed", "U-verse service section is NOT displayed", true);     
				  }
				  
				  
				  //Validate Home Phone/Internet service section is displayed
				  if(UI.WaitForObject(oR_PlentiLogin.txtHomePhoneSectionOnWhichID, 2)){
					Report.ValidationPoint(testContext.getName(), "Validate Home Phone/Internet service section is displayed", "Home Phone/Internet service section is displayed", "Home Phone/Internet service section is displayed", true);  
				  }else{
						Report.ValidationPoint(testContext.getName(), "Validate Home Phone/Internet service section is displayed", "Home Phone/Internet service section is displayed", "Home Phone/Internet service section is NOT displayed", true);      
				  }
				 			  
				  //Validate “You Have an AT&T Access ID if:” section is displayed
				  if(UI.WaitForObject(oR_PlentiLogin.txtYouHaveAnSectionOnWhichID, 2)){
					Report.ValidationPoint(testContext.getName(), "Validate 'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is displayed", true);  
				  }else{
						Report.ValidationPoint(testContext.getName(), "Validate 'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is NOT displayed", true);      
				  }
				  
				  //Validate Forgot USer ID link is displayed
				  if(UI.WaitForObject(oR_PlentiLogin.lnkForgotUserIDOnWhichID, 2)){
					Report.ValidationPoint(testContext.getName(), "Validate Forgot User ID link is displayed", "Forgot User ID link is displayed", "Forgot User ID link is displayed", true);  
				  }else{
					  Report.ValidationPoint(testContext.getName(), "Validate Forgot User ID link is displayed", "Forgot User ID link is displayed", "Forgot User ID link is NOT displayed", true);      
				  }
				  
				  //Validate Need a  User ID link is displayed
				  if(UI.WaitForObject(oR_PlentiLogin.lnkNeedAUserOnWhichID, 2)){
					Report.ValidationPoint(testContext.getName(), "Validate Need a User ID link is displayed", "Need a User ID link is displayed", "Need a User ID link is displayed", true);  
				  }else{
						Report.ValidationPoint(testContext.getName(), "Validate Need a User ID link is displayed", "Need a User ID link is displayed", "Need a User ID link is NOT displayed", true);      
				  }
			  
				  //Validate OK CTA is displayed
				  if(UI.WaitForObject(oR_PlentiLogin.btnOKOnWhichID, 2)){
					Report.ValidationPoint(testContext.getName(), "Validate OK CTA is displayed", "OK CTA is displayed", "OK CTA is displayed", true);  
					
				  }else{
						Report.ValidationPoint(testContext.getName(), "Validate OK CTA is displayed", "OK CTA is displayed", "OK CTA is NOT displayed", true);       
				  }
					
					// Click on Close CTA
					oR_PlentiLogin.lnkCloseOnWhichID.click();
					
					Thread.sleep(5000);
					lDriver.switchTo().defaultContent();
					
					//Validate login page is displayed
					if(UI.WaitForObject(oR_PlentiLogin.lnkWhichID,UI.iObjTimeOut)){
						Report.ValidationPoint(testContext.getName(), "Validate Plenti Login page is displayed", "Plenti Login page is displayed", "Plenti Login page is displayed", true);
					}else{
						Report.ValidationPoint(testContext.getName(), "Validate Plenti Login page is displayed", "Plenti Login page is displayed", "Plenti Login page is NOT displayed", true);	
					}

			  }
		}
			   catch (Exception e)
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}
	
	}
	
	
	
	/**************************************************************
	 * Function Name - VerifyOkCTAFromWhatUserIDModel()
	 * Description - This function is to validate OK CTA from what USer Model.
	 * Test Case - LGN04335  
	 * Parameters - None
	 * Date created - 29th June 2015
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyOkCTAFromWhatUserIDModel(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		 WebDriver lDriver = UI.getDriver(testContext.getName());
		 OR_PlentiLogin oR_PlentiLogin = PageFactory.initElements(lDriver, OR_PlentiLogin.class);
	  try{
		  
		  
			//Verify Page title
			String Url = lDriver.getCurrentUrl();
			   if(Url.contains("PlentiLogin"))
			   {
				   Report.TestPoint(testContext.getName(), "Validate Page title is 'PlentiLogin'", "True", "True", true);
			   }
			   else
			   {
				   Report.TestPoint(testContext.getName(), "Validate Page title is 'PlentiLogin'", "True", "false", true);
			   }
			   
			//Verify Log in to your account to begin link
			boolean bAccessAcc = UI.WaitForObject(oR_PlentiLogin.txtLoginToBegin, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Log in to your account to begin is displayed", "true", String.valueOf(bAccessAcc), true);
			
			//Verify Label for User ID
			boolean bUserID = UI.WaitForObject(oR_PlentiLogin.txtUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Label for User ID is displayed", "true", String.valueOf(bUserID), true);
			
			//Verify Entry box for User ID
			boolean bUserIDBox = UI.WaitForObject(oR_PlentiLogin.edtUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Entry box for User ID is displayed", "true", String.valueOf(bUserIDBox), true);
			
			//Verify Label for Password
			boolean bPwd = UI.WaitForObject(oR_PlentiLogin.txtPwd, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Label for Password is displayed", "true", String.valueOf(bPwd), true);
			
			//Verify 'What ID should I use?' link
			boolean bWhichID = UI.WaitForObject(oR_PlentiLogin.lnkWhichID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify 'What ID should I use?' is displayed", "true", String.valueOf(bWhichID), true);
			
			//Verify Save User ID checkbox
			boolean bchkbox = UI.WaitForObject(oR_PlentiLogin.boxSave, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Save User ID checkbox is displayed", "true", String.valueOf(bchkbox), true);
			
			//Verify Save User ID label
			boolean bLabel = UI.WaitForObject(oR_PlentiLogin.txtSaveUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Save User ID label is displayed", "true", String.valueOf(bLabel), true);

			//Verify Login button
			boolean bLogin = UI.WaitForObject(oR_PlentiLogin.btnLogin, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Login button is displayed", "true", String.valueOf(bLogin), true);
			
			//Verify  Forgot UserID link 
			boolean bForgotID = UI.WaitForObject(oR_PlentiLogin.lnkForgotUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify  Forgot UserID link is displayed", "true", String.valueOf(bForgotID), true);
			
			//Verify Forgot Password link
			boolean bForgotPwd = UI.WaitForObject(oR_PlentiLogin.lnkForgotPwd, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Forgot Password link is displayed", "true", String.valueOf(bForgotPwd), true);
		
			//Verify Register Today link
			boolean bRegisterToday = UI.WaitForObject(oR_PlentiLogin.lnkRegisterToday, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Register Today link is displayed", "true", String.valueOf(bRegisterToday), true);

			// Validation on 'Which User ID should I Use?' model
		  if(UI.WaitForObject(oR_PlentiLogin.lnkWhichID, UI.iObjTimeOut)){
			  Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is displayed", true);
			  oR_PlentiLogin.lnkWhichID.click();
			  Thread.sleep(5000);
			  
			  //Navigate to 'Which User ID Should I use?' frame
			  lDriver.switchTo().frame(oR_PlentiLogin.frmWhichID);
			  Report.OperationPoint(testContext.getName(), "'Which User ID Should I use?' modal is displayed");
			  
			  //Validate Page Title
			  if(UI.WaitForObject(oR_PlentiLogin.txtWhichIDTitle, UI.iObjTimeOut)){
				Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is displayed", true);  
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is NOT displayed", true);  
			  }
			  
			  
			  //Validate Close CTA is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.lnkCloseOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Close CTA is displayed", "Close CTA is displayed", "Close CTA is displayed", true);  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate Close CTA is displayed", "Close CTA is displayed", "Close CTA is NOT displayed", true);	 
			  }

			  
			  //Validate informational text is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtInfotmationalTxtOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Informational text is displayed", "Informational text is displayed", "Informational text is displayed", true);  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate Informational text is displayed", "Informational text is displayed", "Informational text is NOT displayed", true);    
			  }
			  
			  //Validate Wireless Account section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtWirelessAccountSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Wireless Account section is displayed", "Wireless Account section is displayed", "Wireless Account section is displayed", true);  
			  }else{
				Report.ValidationPoint(testContext.getName(), "Validate Wireless Account section is displayed", "Wireless Account section is displayed", "Wireless Account section is NOT displayed", true);   
			  }

			  //Validate Combined Bill section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtCombinedBillSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Combined Bill section is displayed", "Combined Bill section is displayed", "Combined Bill section is displayed", true);  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate Combined Bill section is displayed", "Combined Bill section is displayed", "Combined Bill section is NOT displayed", true);     
			  }

			  //Validate U-verse service section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtUverseServiceSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate U-verse service section is displayed", "U-verse service section is displayed", "U-verse service section is displayed", true);  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate U-verse service section is displayed", "U-verse service section is displayed", "U-verse service section is NOT displayed", true);     
			  }
			  
			  
			  //Validate Home Phone/Internet service section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtHomePhoneSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Home Phone/Internet service section is displayed", "Home Phone/Internet service section is displayed", "Home Phone/Internet service section is displayed", true);  
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate Home Phone/Internet service section is displayed", "Home Phone/Internet service section is displayed", "Home Phone/Internet service section is NOT displayed", true);      
			  }
			 			  
			  //Validate “You Have an AT&T Access ID if:” section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtYouHaveAnSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate 'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is displayed", true);  
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate 'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is NOT displayed", true);      
			  }
			  
			  //Validate Forgot USer ID link is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.lnkForgotUserIDOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Forgot User ID link is displayed", "Forgot User ID link is displayed", "Forgot User ID link is displayed", true);  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate Forgot User ID link is displayed", "Forgot User ID link is displayed", "Forgot User ID link is NOT displayed", true);      
			  }
			  
			  //Validate Need a  User ID link is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.lnkNeedAUserOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Need a User ID link is displayed", "Need a User ID link is displayed", "Need a User ID link is displayed", true);  
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate Need a User ID link is displayed", "Need a User ID link is displayed", "Need a User ID link is NOT displayed", true);      
			  }
		  
			  //Validate OK CTA is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.btnOKOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate OK CTA is displayed", "OK CTA is displayed", "OK CTA is displayed", true);  
			    
				// Click on OK CTA
				oR_PlentiLogin.btnOKOnWhichID.click();
				
				Thread.sleep(5000);
				lDriver.switchTo().defaultContent();
				
				//Validate login page is displayed
				if(UI.WaitForObject(oR_PlentiLogin.lnkWhichID,UI.iObjTimeOut)){
					Report.ValidationPoint(testContext.getName(), "Validate Plenti Login page is displayed", "Plenti Login page is displayed", "Plenti Login page is displayed", true);
				}else{
					Report.ValidationPoint(testContext.getName(), "Validate Plenti Login page is displayed", "Plenti Login page is displayed", "Plenti Login page is NOT displayed", true);	
				}
				
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate OK CTA is displayed", "OK CTA is displayed", "OK CTA is NOT displayed", true);       
			  }
			  
			
			  
		  }else{
			  Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is NOT displayed", true);
		  }
		  
		
		}catch (Exception e)
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}

}

	/**************************************************************
	 * Function Name -  VerifyLoginAccNotAvailable()
	 * Description- This function validates the Login – Account Not Eligible For Plenti page 
	 * Parameters - None 
	 * Date created - 30th June 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	//LGN04326

	public static void VerifyLoginAccNotAvailable(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		 WebDriver lDriver = UI.getDriver(testContext.getName()); 
	     
	        OR_PlentiLogin oR_PlentiLogin = PageFactory.initElements(lDriver, OR_PlentiLogin.class);
	      
		try
		{
			
			//Verify Page title
			String Url = lDriver.getCurrentUrl();
			   if(Url.contains("PlentiLogin"))
			   {
				   Report.TestPoint(testContext.getName(), "Validate Page title is 'PlentiLogin'", "True", "True", true);
			   }
			   else
			   {
				   Report.TestPoint(testContext.getName(), "Validate Page title is 'PlentiLogin'", "True", "false", true);
			   }
			   
			//Verify Log in to your account to begin link
			boolean bAccessAcc = UI.WaitForObject(oR_PlentiLogin.txtLoginToBegin, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Log in to your account to begin is displayed", "true", String.valueOf(bAccessAcc), true);
			
			//Verify Label for User ID
			boolean bUserID = UI.WaitForObject(oR_PlentiLogin.txtUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Label for User ID is displayed", "true", String.valueOf(bUserID), true);
			
			//Verify Entry box for User ID
			boolean bUserIDBox = UI.WaitForObject(oR_PlentiLogin.edtUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Entry box for User ID is displayed", "true", String.valueOf(bUserIDBox), true);
			
			//Verify Label for Password
			boolean bPwd = UI.WaitForObject(oR_PlentiLogin.txtPwd, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Label for Password is displayed", "true", String.valueOf(bPwd), true);
			
			//Verify 'What ID should I use?' link
			boolean bWhichID = UI.WaitForObject(oR_PlentiLogin.lnkWhichID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify 'What ID should I use?' is displayed", "true", String.valueOf(bWhichID), true);
			
			//Verify Save User ID checkbox
			boolean bchkbox = UI.WaitForObject(oR_PlentiLogin.boxSave, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Save User ID checkbox is displayed", "true", String.valueOf(bchkbox), true);
			
			//Verify Save User ID label
			boolean bLabel = UI.WaitForObject(oR_PlentiLogin.txtSaveUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Save User ID label is displayed", "true", String.valueOf(bLabel), true);

			//Verify Login button
			boolean bLogin = UI.WaitForObject(oR_PlentiLogin.btnLogin, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Login button is displayed", "true", String.valueOf(bLogin), true);
			
			//Verify  Forgot UserID link 
			boolean bForgotID = UI.WaitForObject(oR_PlentiLogin.lnkForgotUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify  Forgot UserID link is displayed", "true", String.valueOf(bForgotID), true);
			
			//Verify Forgot Password link
			boolean bForgotPwd = UI.WaitForObject(oR_PlentiLogin.lnkForgotPwd, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Forgot Password link is displayed", "true", String.valueOf(bForgotPwd), true);
		
			//Verify Register Today link
			boolean bRegisterToday = UI.WaitForObject(oR_PlentiLogin.lnkRegisterToday, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Register Today link is displayed", "true", String.valueOf(bRegisterToday), true);
			
			// Validation on 'Which User ID should I Use?' model
			  if(UI.WaitForObject(oR_PlentiLogin.lnkWhichID, UI.iObjTimeOut)){
				  Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is displayed", true);
				  oR_PlentiLogin.lnkWhichID.click();
				  Thread.sleep(5000);
				 
				 
				  //Enter User ID and Password
				  
				  Login.VerifyLogin(testContext);
				  
				//Verify Page title
					Url = lDriver.getCurrentUrl();
					   if(Url.contains("plentinotEligible"))
					   {
						   Report.TestPoint(testContext.getName(), "Validate Page title is 'plentinotEligible'", "True", "True", true);
					   }
					   else
					   {
						   Report.TestPoint(testContext.getName(), "Validate Page title is 'plentinotEligible'", "True", "false", true);
					   }
				
			    //Verify Log in with another account CTA		   
			   boolean bLoginlnk = UI.WaitForObject(oR_PlentiLogin.btnLoginWithAnotherAcc, UI.iObjTimeOut);
			   Report.TestPoint(testContext.getName(), "Verify Log in with another account CTA is displayed", "true", String.valueOf(bLoginlnk), true);
			   
			   //Verify • Account Overview CTA
			   boolean bAccOverview = UI.WaitForObject(oR_PlentiLogin.lnkGoToAccOverview, UI.iObjTimeOut);
			   Report.TestPoint(testContext.getName(), "Verify Account Overview CTA is displayed", "true", String.valueOf(bAccOverview), true);
			   
			   //Verify • Shop for Wireless service CTA
			   boolean bShop = UI.WaitForObject(oR_PlentiLogin.lnkShopATT, UI.iObjTimeOut);
			   Report.TestPoint(testContext.getName(), "Verify Shop for Wireless service CTA is displayed", "true", String.valueOf(bShop), true);
			   
			   //Verify • Page Description 
			   boolean bDesc = UI.WaitForObject(oR_PlentiLogin.txteligible, UI.iObjTimeOut);
			   Report.TestPoint(testContext.getName(), "Verify Page description 'you dont have any eligible account' is displayed", "true", String.valueOf(bDesc), true);
			   
			   if(UI.WaitForObject(oR_PlentiLogin.btnLogOut, UI.iObjTimeOut)){
					  Report.ValidationPoint(testContext.getName(), "Validate 'Log Out' button is displayed", "true", "true", true);
					  oR_PlentiLogin.btnLogOut.click();
			   }
			   else
			   {
				   Report.ValidationPoint(testContext.getName(), "Validate 'Log Out' button is displayed", "true", "false", true);
			   }
				   
			  
		}
		}
			   catch (Exception e)
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}
	
	}
	
	/**************************************************************
	 * Function Name -  VerifyLogin()
	 * Description- This function enters credential (user Id and password) on PLenri Login page
	 * Parameters - None 
	 * Date created - 30th June 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	public static void VerifyLogin(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{try
	{
	
	  Hashtable<String, String> sTestParams = IO.getTestData(testContext.getName());
	  WebDriver lDriver = UI.getDriver(testContext.getName());
	  OR_PlentiLogin oR_PlentiLogin = PageFactory.initElements(lDriver, OR_PlentiLogin.class);
	  String sUserID = IO.GetParamVal(sTestParams, "LoginID");
	  String sPwd = IO.GetParamVal(sTestParams, "Password");

		//Enter values in Current, New and Cofirm New password feilds
		oR_PlentiLogin.edtUserID.sendKeys(sUserID);
		Report.OperationPoint(testContext.getName(), "Operational - Entered User ID");

		oR_PlentiLogin.edtPwd.sendKeys(sPwd);
		Report.OperationPoint(testContext.getName(), "Operational -Password Entered");
		
		Thread.sleep(2000);
		oR_PlentiLogin.btnLogin.click();
		Thread.sleep(20000);
		boolean bPlenti = UI.WaitForObject(oR_PlentiLogin.txtPlentiLink, UI.iObjTimeOut);
		Report.TestPoint(testContext.getName(), "Verify User is navigated to Link Plenti page", "true", String.valueOf(bPlenti), true);
	}catch (Exception ee)
	{
		Report.ValidationPoint(testContext.getName(), "Verify that all the values are entered properly in Change password page", "true","false", true);
	}
}
	
	/**************************************************************
	 * Function Name - VerifyForgotUserIDCTAFromWhatUserIDModel()
	 * Description - This function is to validate forgot user ID CTA from what User ID Model.
	 * Test Case - LGN04336  
	 * Parameters - None
	 * Date created - 30th June 2015
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyForgotUserIDCTAFromWhatUserIDModel(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		 WebDriver lDriver = UI.getDriver(testContext.getName());
		 OR_PlentiLogin oR_PlentiLogin = PageFactory.initElements(lDriver, OR_PlentiLogin.class);
	  try{
		  
		  
			//Verify Page title
			String Url = lDriver.getCurrentUrl();
			   if(Url.contains("PlentiLogin"))
			   {
				   Report.TestPoint(testContext.getName(), "Validate Page title is 'PlentiLogin'", "True", "True", true);
			   }
			   else
			   {
				   Report.TestPoint(testContext.getName(), "Validate Page title is 'PlentiLogin'", "True", "false", true);
			   }
			   
			//Verify Log in to your account to begin link
			boolean bAccessAcc = UI.WaitForObject(oR_PlentiLogin.txtLoginToBegin, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Log in to your account to begin is displayed", "true", String.valueOf(bAccessAcc), true);
			
			//Verify Label for User ID
			boolean bUserID = UI.WaitForObject(oR_PlentiLogin.txtUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Label for User ID is displayed", "true", String.valueOf(bUserID), true);
			
			//Verify Entry box for User ID
			boolean bUserIDBox = UI.WaitForObject(oR_PlentiLogin.edtUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Entry box for User ID is displayed", "true", String.valueOf(bUserIDBox), true);
			
			//Verify Label for Password
			boolean bPwd = UI.WaitForObject(oR_PlentiLogin.txtPwd, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Label for Password is displayed", "true", String.valueOf(bPwd), true);
			
						
			//Verify Save User ID checkbox
			boolean bchkbox = UI.WaitForObject(oR_PlentiLogin.boxSave, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Save User ID checkbox is displayed", "true", String.valueOf(bchkbox), true);
			
			//Verify Save User ID label
			boolean bLabel = UI.WaitForObject(oR_PlentiLogin.txtSaveUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Save User ID label is displayed", "true", String.valueOf(bLabel), true);

			//Verify Login button
			boolean bLogin = UI.WaitForObject(oR_PlentiLogin.btnLogin, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Login button is displayed", "true", String.valueOf(bLogin), true);
			
			//Verify  Forgot UserID link 
			boolean bForgotID = UI.WaitForObject(oR_PlentiLogin.lnkForgotUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify  Forgot UserID link is displayed", "true", String.valueOf(bForgotID), true);
			
			//Verify Forgot Password link
			boolean bForgotPwd = UI.WaitForObject(oR_PlentiLogin.lnkForgotPwd, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Forgot Password link is displayed", "true", String.valueOf(bForgotPwd), true);
		
			//Verify Register Today link
			boolean bRegisterToday = UI.WaitForObject(oR_PlentiLogin.lnkRegisterToday, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Register Today link is displayed", "true", String.valueOf(bRegisterToday), true);

			// Validation on 'Which User ID should I Use?' model
		  if(UI.WaitForObject(oR_PlentiLogin.lnkWhichID, UI.iObjTimeOut)){
			  Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is displayed", true);
			  oR_PlentiLogin.lnkWhichID.click();
			  Thread.sleep(5000);
			  
			  //Navigate to 'Which User ID Should I use?' frame
			  lDriver.switchTo().frame(oR_PlentiLogin.frmWhichID);
			  Report.OperationPoint(testContext.getName(), "'Which User ID Should I use?' modal is displayed");
			  
			  //Validate Page Title
			  if(UI.WaitForObject(oR_PlentiLogin.txtWhichIDTitle, UI.iObjTimeOut)){
				Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is displayed", true);  
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is NOT displayed", true);  
			  }
			  
			  
			  //Validate Close CTA is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.lnkCloseOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Close CTA is displayed", "Close CTA is displayed", "Close CTA is displayed", true);  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate Close CTA is displayed", "Close CTA is displayed", "Close CTA is NOT displayed", true);	 
			  }

			  
			  //Validate informational text is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtInfotmationalTxtOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Informational text is displayed", "Informational text is displayed", "Informational text is displayed", true);  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate Informational text is displayed", "Informational text is displayed", "Informational text is NOT displayed", true);    
			  }
			  
			  //Validate Wireless Account section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtWirelessAccountSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Wireless Account section is displayed", "Wireless Account section is displayed", "Wireless Account section is displayed", true);  
			  }else{
				Report.ValidationPoint(testContext.getName(), "Validate Wireless Account section is displayed", "Wireless Account section is displayed", "Wireless Account section is NOT displayed", true);   
			  }

			  //Validate Combined Bill section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtCombinedBillSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Combined Bill section is displayed", "Combined Bill section is displayed", "Combined Bill section is displayed", true);  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate Combined Bill section is displayed", "Combined Bill section is displayed", "Combined Bill section is NOT displayed", true);     
			  }

			  //Validate U-verse service section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtUverseServiceSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate U-verse service section is displayed", "U-verse service section is displayed", "U-verse service section is displayed", true);  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate U-verse service section is displayed", "U-verse service section is displayed", "U-verse service section is NOT displayed", true);     
			  }
			  
			  
			  //Validate Home Phone/Internet service section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtHomePhoneSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Home Phone/Internet service section is displayed", "Home Phone/Internet service section is displayed", "Home Phone/Internet service section is displayed", true);  
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate Home Phone/Internet service section is displayed", "Home Phone/Internet service section is displayed", "Home Phone/Internet service section is NOT displayed", true);      
			  }
			 			  
			  //Validate “You Have an AT&T Access ID if:” section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtYouHaveAnSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate 'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is displayed", true);  
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate 'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is NOT displayed", true);      
			  }
			  
			  			  
			  //Validate Need a  User ID link is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.lnkNeedAUserOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Need a User ID link is displayed", "Need a User ID link is displayed", "Need a User ID link is displayed", true);  
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate Need a User ID link is displayed", "Need a User ID link is displayed", "Need a User ID link is NOT displayed", true);      
			  }
		  
			  //Validate OK CTA is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.btnOKOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate OK CTA is displayed", "OK CTA is displayed", "OK CTA is displayed", true);  
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate OK CTA is displayed", "OK CTA is displayed", "OK CTA is NOT displayed", true);       
			  }
			  
			  
			//Validate Forgot User ID link is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.lnkForgotUserIDOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Forgot User ID link is displayed", "Forgot User ID link is displayed", "Forgot User ID link is displayed", true);  
			    // Navigate to Forgot User ID / Password page
				Report.OperationPoint(testContext.getName(), "Navigate to Forgot User ID/ Password page");
			    oR_PlentiLogin.lnkForgotUserIDOnWhichID.click();
			    Thread.sleep(5000);
			    
			    // Validate Forgot User ID / Password page is displayed
			    if(UI.WaitForObject(oR_PlentiLogin.txtForgotUserIDTitle, UI.iObjTimeOut)){
			    	Report.ValidationPoint(testContext.getName(), "Validate Forgot User ID page is displayed", "Forgot User ID page is displayed", "Forgot User ID page is displayed", true);
			    }else{
			    	Report.ValidationPoint(testContext.getName(), "Validate Forgot User ID page is displayed", "Forgot User ID page is displayed", "Forgot User ID page is NOT displayed", true);
			    }
			  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate Forgot User ID link is displayed", "Forgot User ID link is displayed", "Forgot User ID link is NOT displayed", true);      
			  }
			  
		  }else{
			  Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is NOT displayed", true);
		  }
		  
		
		}catch (Exception e)
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}
	}
	
	/**************************************************************
	 * Function Name - VerifyNeedUserIDCTAFromWhatUserIDModel()
	 * Description - This function is to validate Need user ID CTA from what User ID Model.
	 * Test Case - LGN04336  
	 * Parameters - None
	 * Date created - 30th June 2015
	 * Developed by - Nachiket Pawar
	 * Last Modified By - 
	 * Last Modified Date - 
	 ***************************************************************/
	public static void VerifyNeedUserIDCTAFromWhatUserIDModel(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		 WebDriver lDriver = UI.getDriver(testContext.getName());
		 OR_PlentiLogin oR_PlentiLogin = PageFactory.initElements(lDriver, OR_PlentiLogin.class);
	  try{
		  
		  
			//Verify Page title
			String Url = lDriver.getCurrentUrl();
			   if(Url.contains("PlentiLogin"))
			   {
				   Report.TestPoint(testContext.getName(), "Validate Page title is 'PlentiLogin'", "True", "True", true);
			   }
			   else
			   {
				   Report.TestPoint(testContext.getName(), "Validate Page title is 'PlentiLogin'", "True", "false", true);
			   }
			   
			//Verify Log in to your account to begin link
			boolean bAccessAcc = UI.WaitForObject(oR_PlentiLogin.txtLoginToBegin, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Log in to your account to begin is displayed", "true", String.valueOf(bAccessAcc), true);
			
			//Verify Label for User ID
			boolean bUserID = UI.WaitForObject(oR_PlentiLogin.txtUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Label for User ID is displayed", "true", String.valueOf(bUserID), true);
			
			//Verify Entry box for User ID
			boolean bUserIDBox = UI.WaitForObject(oR_PlentiLogin.edtUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Entry box for User ID is displayed", "true", String.valueOf(bUserIDBox), true);
			
			//Verify Label for Password
			boolean bPwd = UI.WaitForObject(oR_PlentiLogin.txtPwd, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Label for Password is displayed", "true", String.valueOf(bPwd), true);
			
			//Verify Label for Password
			boolean bPwdBox = UI.WaitForObject(oR_PlentiLogin.edtPwd, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Entry box for Password is displayed", "true", String.valueOf(bPwdBox), true);
			
						
			//Verify Save User ID checkbox
			boolean bchkbox = UI.WaitForObject(oR_PlentiLogin.boxSave, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Save User ID checkbox is displayed", "true", String.valueOf(bchkbox), true);
			
			//Verify Save User ID label
			boolean bLabel = UI.WaitForObject(oR_PlentiLogin.txtSaveUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Save User ID label is displayed", "true", String.valueOf(bLabel), true);

			//Verify Login button
			boolean bLogin = UI.WaitForObject(oR_PlentiLogin.btnLogin, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Login button is displayed", "true", String.valueOf(bLogin), true);
			
			//Verify  Forgot UserID link 
			boolean bForgotID = UI.WaitForObject(oR_PlentiLogin.lnkForgotUserID, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify  Forgot UserID link is displayed", "true", String.valueOf(bForgotID), true);
			
			//Verify Forgot Password link
			boolean bForgotPwd = UI.WaitForObject(oR_PlentiLogin.lnkForgotPwd, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Forgot Password link is displayed", "true", String.valueOf(bForgotPwd), true);
		
			//Verify Register Today link
			boolean bRegisterToday = UI.WaitForObject(oR_PlentiLogin.lnkRegisterToday, UI.iObjTimeOut);
			Report.TestPoint(testContext.getName(), "Verify Register Today link is displayed", "true", String.valueOf(bRegisterToday), true);
		
	if(!testContext.getName().contains("LGN04310")){
			// Validation on 'Which User ID should I Use?' model
		  if(UI.WaitForObject(oR_PlentiLogin.lnkWhichID, UI.iObjTimeOut)){
			  Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is displayed", true);
			  oR_PlentiLogin.lnkWhichID.click();
			  Thread.sleep(5000);
			  
			  //Navigate to 'Which User ID Should I use?' frame
			  lDriver.switchTo().frame(oR_PlentiLogin.frmWhichID);
			  Report.OperationPoint(testContext.getName(), "'Which User ID Should I use?' modal is displayed");
			  
			  //Validate Page Title
			  if(UI.WaitForObject(oR_PlentiLogin.txtWhichIDTitle, UI.iObjTimeOut)){
				Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is displayed", true);  
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is displayed", "'Which User ID Should I use?' page title is NOT displayed", true);  
			  }
			  
			  
			  //Validate Close CTA is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.lnkCloseOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Close CTA is displayed", "Close CTA is displayed", "Close CTA is displayed", true);  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate Close CTA is displayed", "Close CTA is displayed", "Close CTA is NOT displayed", true);	 
			  }

		
			  //Validate informational text is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtInfotmationalTxtOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Informational text is displayed", "Informational text is displayed", "Informational text is displayed", true);  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate Informational text is displayed", "Informational text is displayed", "Informational text is NOT displayed", true);    
			  }
	
			  //Validate Wireless Account section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtWirelessAccountSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Wireless Account section is displayed", "Wireless Account section is displayed", "Wireless Account section is displayed", true);  
			  }else{
				Report.ValidationPoint(testContext.getName(), "Validate Wireless Account section is displayed", "Wireless Account section is displayed", "Wireless Account section is NOT displayed", true);   
			  }

			  //Validate Combined Bill section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtCombinedBillSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Combined Bill section is displayed", "Combined Bill section is displayed", "Combined Bill section is displayed", true);  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate Combined Bill section is displayed", "Combined Bill section is displayed", "Combined Bill section is NOT displayed", true);     
			  }

			  //Validate U-verse service section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtUverseServiceSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate U-verse service section is displayed", "U-verse service section is displayed", "U-verse service section is displayed", true);  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate U-verse service section is displayed", "U-verse service section is displayed", "U-verse service section is NOT displayed", true);     
			  }
			  
			  
			  //Validate Home Phone/Internet service section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtHomePhoneSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Home Phone/Internet service section is displayed", "Home Phone/Internet service section is displayed", "Home Phone/Internet service section is displayed", true);  
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate Home Phone/Internet service section is displayed", "Home Phone/Internet service section is displayed", "Home Phone/Internet service section is NOT displayed", true);      
			  }
			 			  
			  //Validate “You Have an AT&T Access ID if:” section is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.txtYouHaveAnSectionOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate 'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is displayed", true);  
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate 'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is displayed", "'You Have an AT&T Access ID if:' section is NOT displayed", true);      
			  }
			  
			  			  
			 	  
			  //Validate OK CTA is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.btnOKOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate OK CTA is displayed", "OK CTA is displayed", "OK CTA is displayed", true);  
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate OK CTA is displayed", "OK CTA is displayed", "OK CTA is NOT displayed", true);       
			  }
			  
			  
			//Validate Forgot User ID link is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.lnkForgotUserIDOnWhichID, 2)){
			      Report.ValidationPoint(testContext.getName(), "Validate Forgot User ID link is displayed", "Forgot User ID link is displayed", "Forgot User ID link is displayed", true);  
			  
			  }else{
				  Report.ValidationPoint(testContext.getName(), "Validate Forgot User ID link is displayed", "Forgot User ID link is displayed", "Forgot User ID link is NOT displayed", true);      
			  }
			  
			  
			  //Validate Need a  User ID link is displayed
			  if(UI.WaitForObject(oR_PlentiLogin.lnkNeedAUserOnWhichID, 2)){
				Report.ValidationPoint(testContext.getName(), "Validate Need a User ID link is displayed", "Need a User ID link is displayed", "Need a User ID link is displayed", true);  
			    //Click on Need a User ID link
				Report.OperationPoint(testContext.getName(), "Click on Need a User lnik");
				oR_PlentiLogin.lnkNeedAUserOnWhichID.click();
				Thread.sleep(5000);
				
				if(UI.WaitForObject(oR_PlentiLogin.txtAccountInformationTitle, UI.iObjTimeOut, lDriver)){
					Report.ValidationPoint(testContext.getName(), "Validate select account type page is displayed", "Select account type page is displayed", "Select account type page is displayed", true);
					
				}else{
					Report.ValidationPoint(testContext.getName(), "Validate select account type page is displayed", "Select account type page is displayed", "Select account type page is NOT displayed", true);
				}
				
				
			  }else{
					Report.ValidationPoint(testContext.getName(), "Validate Need a User ID link is displayed", "Need a User ID link is displayed", "Need a User ID link is NOT displayed", true);      
			  }
			  
		  }else{
			  Report.ValidationPoint(testContext.getName(), "Validate 'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is displayed", "'Which User ID should I use?' link is NOT displayed", true);
		  }
	  }
		
		}catch (Exception e)
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}
	}

/**************************************************************
 * Function Name -VerifyPlentiPage ()
 * Description - 
 * Test Case -   LGN04310
 * Parameters - None
 * Date created - 3rd nov 2015
 * Developed by - Gautham
 * Last Modified By - 
 * Last Modified Date - 
 ***************************************************************/
	public static void VerifyPlentiPage(final ITestContext testContext) throws HeadlessException, IOException, AWTException, ParseException
	{
		 WebDriver lDriver = UI.getDriver(testContext.getName());
		 OR_PlentiLogin oR_PlentiLogin = PageFactory.initElements(lDriver, OR_PlentiLogin.class);
		 int Count=0;
	  try{
		  
		  //validateplenti login page
		  VerifyNeedUserIDCTAFromWhatUserIDModel(testContext);
		  
		 /* //Validate informational text is displayed
		  if(UI.WaitForObject(oR_PlentiLogin.txtInfotmationalTxtOnWhichID, 2)){
			Report.ValidationPoint(testContext.getName(), "Validate Informational text is displayed", "Informational text is displayed", "Informational text is displayed", true);  
		  }else{
			  Report.ValidationPoint(testContext.getName(), "Validate Informational text is displayed", "Informational text is displayed", "Informational text is NOT displayed", true);    
		  }
		  */
		  //login
		  VerifyLogin(testContext);
		  
		  //Page title wth Enroll for plenti with AT&T
		  boolean bTitle = UI.WaitForObject(oR_PlentiLogin.txtStartEarningTitle, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify Page title for plenti is displayed as expected", "true", String.valueOf(bTitle), true);
		
			boolean bYes = oR_PlentiLogin.radLinkPlentiCard.isEnabled();
			Report.ValidationPoint(testContext.getName(), "Verify 'Yes' radio button option is available", "true", String.valueOf(bYes), true);
		
			boolean bNo = oR_PlentiLogin.radSignUpPlenti.isEnabled();
			Report.ValidationPoint(testContext.getName(), "Verify 'No' radio button option is available", "true", String.valueOf(bNo), true);
		
			//select frst radio button
			oR_PlentiLogin.radLinkPlentiCard.click();
			//validate tabs
			List<WebElement> tabs= lDriver.findElements(By.xpath("//ul[@id='tablist']//li"));
			List<String> TabNames= new ArrayList<String>();
			for(WebElement e: tabs)
			{
				TabNames.add(e.getText());
				if(!e.getText().isEmpty())
				{
					Count++;
				}
			}

			if(Count>0)
			{
				Report.ValidationPoint(testContext.getName(), "Verify "+ Count +" selectable tabs are present after clicking on 'Yes'  radio button", "true", String.valueOf(Count>0), true);
			}

			//validate tabs
			for(int i=0;i<TabNames.size();i++)
			{
				if(TabNames.get(i).contains("16-digit Plenti card number"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify "+i+" tab is "+TabNames.get(i)+" as expected", "true", "true", true);
				}

				else if(TabNames.get(i).contains("Phone number & last name"))
				{
					Report.ValidationPoint(testContext.getName(), "Verify "+i+" tab is "+TabNames.get(i)+" as expected", "true", "true", true);
				}
			}

			//
			boolean blabel= UI.WaitForObject(lDriver.findElement(By.xpath("//label[contains(text(),'phone number')]")), 2, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify label is available to for plenti phone number", "true", String.valueOf(blabel), true);

			//
			boolean bWhatsThis2 =  UI.WaitForObject(oR_PlentiLogin.lnkWhatIsThis2, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify 'What is This' is diplayed as expecetd", "true", String.valueOf(bWhatsThis2), true);

			//
			boolean bTextBox= UI.WaitForObject(lDriver.findElement(By.id("plentiLastName")), 2, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify Entry box is available for plenti last name", "true", String.valueOf(bTextBox), true);

			//
			boolean bchkBox= oR_PlentiLogin.chkSignUpPlenti2.isEnabled();
			Report.ValidationPoint(testContext.getName(), "Verify I consent to T&C checkbox with T&C link is diplayed as expecetd", "true", String.valueOf(bchkBox), true);

			//
			boolean bbtnYes2 = lDriver.findElement(By.id("phoneLnameCreateDisable")).isDisplayed();

			Report.ValidationPoint(testContext.getName(), "Verify Create Plenti Lname CTA which is disabled by default", "true", String.valueOf(bbtnYes2), true);


			//select last name box
			lDriver.findElement(By.id("plentiLastName")).click();
			Report.ValidationPoint(testContext.getName(), "Verify Last name is selected and left empty", "true", "true", true);

			//
			lDriver.findElement(By.id("plentiPhoneNumber")).click();
			boolean bAlert= UI.WaitForObject(oR_PlentiLogin.txtAlertFieldMissing, 2,lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify required alert is diplayed saying field value missing", "true", String.valueOf(bAlert), true);

			//select last name
			lDriver.findElement(By.id("plentiLastName")).click();
			boolean btext= UI.WaitForObject(oR_PlentiLogin.txtAlertFieldMissing, 3, lDriver);
			if(btext==false){
				Report.ValidationPoint(testContext.getName(), "Verify required alert is not diplayed saying field value missing after selecting last name again", "true", "true", true);
			}
			else
			{
				Report.ValidationPoint(testContext.getName(), "Verify required alert is not diplayed saying field value missing after selecting last name again", "true", "false", true);
			}
			//enter last name with spaces
			lDriver.findElement(By.id("plentiLastName")).sendKeys("GAUT@HAM");
			lDriver.findElement(By.id("plentiPhoneNumber")).click();
			boolean bAlert2= oR_PlentiLogin.txtInvalidValueAlert.isDisplayed();
			Report.ValidationPoint(testContext.getName(), "Verify required alert is diplayed saying invalid field value ", "true", String.valueOf(bAlert2), true);

			//
			lDriver.findElement(By.id("plentiLastName")).click();
			boolean btext2= UI.WaitForObject(oR_PlentiLogin.txtInvalidValueAlert, 3, lDriver);
			if(btext2==false)
				Report.ValidationPoint(testContext.getName(), "Verify required alert is not diplayed saying Invlaid field value after selecting last name again", "true","true", true);
			else
				Report.ValidationPoint(testContext.getName(), "Verify required alert is not diplayed saying Invlaid field value after selecting last name again", "true","false", true);

			//enter more than 50 char
			lDriver.findElement(By.id("plentiLastName")).clear();
			lDriver.findElement(By.id("plentiLastName")).sendKeys("I Just Want To Enter More Than 50 Charachters To Check Whether It Is Allowed Or Not");
			Report.ValidationPoint(testContext.getName(), "Verify no more than 50 characters are allowed.", "true", "true", true);

			//
			//boolean bbtnYes =   lDriver.findElement(By.id("plentiIdCreateDisable")).isDisplayed();
			//Report.ValidationPoint(testContext.getName(), "Verify Create Plenti Id CTA which is disabled by default", "true", String.valueOf(bbtnYes), true);

			//
			//boolean bInstead= UI.WaitForObject(lDriver.findElement(By.xpath("//a[contains(text(),'instead')]")), 2, lDriver);
			//Report.ValidationPoint(testContext.getName(), "Verify alternate option is available to link plenti", "true", String.valueOf(bInstead), true);

			//validate 2 tab
			lDriver.findElement(By.id("Tab2")).click();
			Report.OperationPoint(testContext.getName(),"Clickng on 2 Tab");

			//validate label
			boolean bPlentiLabel =  UI.WaitForObject(oR_PlentiLogin.txtlabelPlantiId, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify label for plenti id is diplayed as expecetd", "true", String.valueOf(bPlentiLabel), true);

			//validate whats this link
			boolean bWhatsThis =  UI.WaitForObject(oR_PlentiLogin.lnkWhatIsThis, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify 'What is This' is diplayed as expecetd", "true", String.valueOf(bWhatsThis), true);

			//				
			boolean bPlentiId =  UI.WaitForObject(oR_PlentiLogin.edtPlentiId, UI.iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), "Verify edit box for PLentiId is diplayed as expecetd", "true", String.valueOf(bPlentiId), true);

			//	
			boolean bchkPlenti =  oR_PlentiLogin.chkSignUpPlenti.isEnabled();
			Report.ValidationPoint(testContext.getName(), "Verify  I consent to T&C checkbox with T&C link is diplayed as expecetd", "true", String.valueOf(bchkPlenti), true);

			
	  }catch (Exception e)
		{
			String sErrMsg = e.getMessage();
			Report.TestPoint(testContext.getName(), sErrMsg, "True", "False", true);
		}
	}
	
 /**************************************************************
	 * Function Name - ValidateForgetIDFlow
	 * Description - The purpose of this test to validate the error msgs shown on Forgot ID page
 	 * Test Case -  UBIL1003 - 446626 - AUDI - Validate Bill charges
	 * Parameters - None
	 * Date created - 23rd feb 2016
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date - 
 ***************************************************************/
	// UAT_UIDM0299
	public static void ValidateForgetIDFlow(final ITestContext testContext) throws HeadlessException, IOException, AWTException
	{
		try
		{
			
			WebDriver lDriver = UI.getDriver(testContext.getName());
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class);
			OR_Login oR_Login = PageFactory.initElements(lDriver, OR_Login.class);
			Boolean bForgotUID, bLoginBtn, bNeedHelpLink, bAsRequestedText, bCheck, bUserIDSent, bError, bEnterEmail, bLogin, bContinue, bCancel, bUIDPwdRad, bPwdRad, bHeader, bDontKnow, bIForgotMy, bUIDRad, bContactEmail;
			
//	1		Select the Forgot ID Link on the Login screen
			bForgotUID = UI.WaitForObject(oR_Login.lnkForgotId, 10, lDriver);
			Report.TestPoint(testContext.getName(), "Select the Forgot ID Link on the Login screen", "true", bForgotUID.toString(), true);
			oR_Login.lnkForgotId.click();
//	2		Radio button pre-selected
			bUIDRad = UI.WaitForObject(oR_Login.radUserID, 10, lDriver);
			if(bUIDRad)
			{
				if(oR_Login.radUserID.findElement(By.id("forgotUserid")).isSelected())
					Report.ValidationPoint(testContext.getName(), "Verify the User ID radio button is pre-selected", "true", "true", true);
				else
					Report.ValidationPoint(testContext.getName(), "Verify the User ID radio button is pre-selected", "true", "false", true);
			}
			else
				Report.ValidationPoint(testContext.getName(), "User ID radio button is present", "true", "false", true);

//			Contact Email Address entry field is  also presented on the page
			bContactEmail = UI.WaitForObject(oR_Login.edtContactEmailAddress, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), " Contact Email Address entry field is  also presented on the page", "true", bContactEmail.toString(), true);

//	3		Verify page layout
			Report.OperationPoint(testContext.getName(), "3-Verify page layout");
			
			//-Forgot User ID/Password Title
			bHeader = UI.WaitForObject(oR_Login.txtForgotIDPasswordHeader, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "i-Forgot User ID/Password Title", "true", bHeader.toString(), true);
			//I forgot my...
			bIForgotMy = UI.WaitForObject(oR_Login.txtIForgotMy, 5, lDriver);
			Report.ValidationPoint(testContext.getName(), "ii- I Forgot My", "true", bIForgotMy.toString(), true);
			//User ID with Radio button
			bUIDRad = UI.WaitForObject(oR_Login.radUserID, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "iii-User ID with Radio button", "true", bUIDRad.toString(), true);
			//Don't know your contact email address? link
			bDontKnow = UI.WaitForObject(oR_Login.lnkDontKnowContactEmailAddressForUserIDAndPassword, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "iv- 'Don't know your contact email address?' link", "true", bDontKnow.toString(), true);
			//Password with Radio button
			bPwdRad = UI.WaitForObject(oR_Login.radPassword, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "v-Password with Radio button", "true", bPwdRad.toString(), true);
			//User ID and Password Radio button
			bUIDPwdRad = UI.WaitForObject(oR_Login.radUserIDPwd, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "vi-User ID and Password Radio button", "true", bUIDPwdRad.toString(), true);
			//Cancel Link
			bCancel = UI.WaitForObject(oR_Login.lnkCancel, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "vii-Cancel Link", "true", bCancel.toString(), true);
			//Continue button (active blue)
			bContinue = UI.WaitForObject(oR_Login.btnContinue, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "viii-Continue button is not active as the username field is blank(active blue)", "true", "true", true);

//	4		Verify Screen content display - spelling, format, field layout, etc
			Report.OperationPoint(testContext.getName(), "4-Verify Screen content display - spelling, format, field layout, etc");
		
//	5		Select Cancel Link 
			if(bCancel)
			{
				oR_Login.lnkCancel.click();
				Thread.sleep(2000);
				
				bCancel = UI.WaitForObject(oR_Login.lnkCancel, 10, lDriver);
				if(bCancel)
				oR_Login.lnkCancel.click();
				
				Report.ValidationPoint(testContext.getName(), "5-Select Cancel Link ", "true", bCancel.toString(), true);
			}
			else
				Report.TestPoint(testContext.getName(), "5-Select Cancel Link ", "true", bCancel.toString(), true);
			
			bLogin = UI.WaitForObject(oR_Login.btnLogin, 60 , lDriver);			
			Report.ValidationPoint(testContext.getName(), "Verify user is returned to originating page (AT&T Login Page)", "true", bLogin.toString(), true);
		
//	6		Select the Forgot User ID Link on the Login screen
			bForgotUID = UI.WaitForObject(oR_Login.lnkForgotId, 10, lDriver);
			Report.TestPoint(testContext.getName(), "Select the Forgot ID Link on the Login screen", "true", bForgotUID.toString(), true);
			oR_Login.lnkForgotId.click();
			
//	7		Radio buttons
			Report.OperationPoint(testContext.getName(), "7-Radio buttons");
			bUIDRad = UI.WaitForObject(oR_Login.radUserID, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "i-User ID Radio button", "true", bUIDRad.toString(), true);
			//Password with Radio button
			bPwdRad = UI.WaitForObject(oR_Login.radPassword, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "ii-Password with Radio button", "true", bPwdRad.toString(), true);
			//User ID and Password Radio button
			bUIDPwdRad = UI.WaitForObject(oR_Login.radUserIDPwd, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "iii-User ID and Password Radio button", "true", bUIDPwdRad.toString(), true);
	
//	8		Leave Contact Email Address entry field empty  - Select Continue 
			oR_Login.edtContactEmailAddress.sendKeys("");
//			bContinue = UI.WaitForObject(oR_Login.btnContinue, 10, lDriver);
//			Report.TestPoint(testContext.getName(), "8-Leave Contact Email Address entry field empty  - Select Continue ", "true", bContinue.toString(), true);
			oR_Login.txtForgotIDPasswordHeader.click();
			
			bEnterEmail = UI.WaitForObject(oR_Login.txtPleaseEnterEmailError, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify user is presented with an error message:'Please enter an email'", "true", bEnterEmail.toString(), true);
			
//	9		Enter an email address that is less than 7 characters long - Select Continue 
			oR_Login.edtContactEmailAddress.sendKeys("a@i.in");
			Report.ValidationPoint(testContext.getName(), "9-Enter an email address that is less than 7 characters long - Select Continue", "true", "true", true);
			oR_Login.btnContinue.click();
			
			bError = UI.WaitForObject(oR_Login.txtEmailMustBeMoreThan7CharactersError, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify user is presented with an error message:'Email must be more than 7 characters'", "true", bError.toString(), true);
			oR_Login.edtContactEmailAddress.clear();
			
//	10		Enter an email address that is greater than 80 characters long - Select Continue 
			oR_Login.edtContactEmailAddress.sendKeys("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz@efg.net");
			Report.ValidationPoint(testContext.getName(), "10-Enter an email address that is more than 80 characters long - Select Continue", "true", "true", true);
			oR_Login.btnContinue.click();
			
			bError = UI.WaitForObject(oR_Login.txtEmailMustBeLessThan80CharactersError, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify user is presented with an error message:'Email must be 80 characters or less'", "true", bError.toString(), true);
			oR_Login.edtContactEmailAddress.clear();
			
//	11		Enter an email address with an invalid format
			oR_Login.edtContactEmailAddress.sendKeys("abxahh.net");
			Report.ValidationPoint(testContext.getName(), "11-Enter an email address with an invalid format", "true", "true", true);
			oR_Login.btnContinue.click();
			
			bError = UI.WaitForObject(oR_Login.txtEnterValidEmailFormatErrors, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify user is presented with an error message:'Please enter a valid email format (ex. xyz@domain.net)'", "true", bError.toString(), true);
			oR_Login.edtContactEmailAddress.clear();
			
//	12		Enter a valid email address not associated to any account
			for(int i=0;i<=2;i++)
			{	
				oR_Login.edtContactEmailAddress.sendKeys("harrypotter@hotmail.com");
				Report.ValidationPoint(testContext.getName(), "12-Enter a valid email address not associated to any account", "true", "true", true);
				oR_Login.btnContinue.click();
				Thread.sleep(3000);
				bError = UI.WaitForObject(oR_Login.txtCantFindAMatchFor, 10, lDriver);
				if(i!=2)
				{
					Report.ValidationPoint(testContext.getName(), "Verify user is presented with an error message:'We can't find a match for that email address'", "true", bError.toString(), true);
					oR_Login.edtContactEmailAddress.clear();
				}
//				Enter an email address that is not associated to an account.  Repeat this step 3 times
				else
				{
					Report.ValidationPoint(testContext.getName(), "13-Enter an email address that is not associated to an account.  Repeat this step 3 times", "true", "true", true);
					break;
				}
			}
			
//			Verify User is directed to Forgot User ID - Select Account Type
			Boolean bForgotID = UI.WaitForObject(oR_Login.txtForgotID, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify User is directed to Forgot User ID - Select Account Type", "true", bForgotID.toString(), true);
			/** Navigate back to the forgot user ID /pwd page **/
			Report.OperationPoint(testContext.getName(), "-to continue with flow, return to step 1 of the Forgot User ID/Password page - Enter Contact Email Address");

			bCancel = UI.WaitForObject(oR_Login.lnkCancel, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "Cancel Link", "true", bCancel.toString(), true);

			if(bCancel)
			{
				oR_Login.lnkCancel.click();
				Thread.sleep(2000);
				
				bCancel = UI.WaitForObject(oR_Login.lnkCancel, 10, lDriver);
				if(bCancel)
				oR_Login.lnkCancel.click();
				
				Report.ValidationPoint(testContext.getName(), "Select Cancel Link ", "true", bCancel.toString(), true);
			}
			else
				Report.TestPoint(testContext.getName(), "Select Cancel Link ", "true", bCancel.toString(), true);

			bLogin = UI.WaitForObject(oR_Login.btnLogin, 60 , lDriver);			
			Report.ValidationPoint(testContext.getName(), "Verify user is returned to originating page (AT&T Login Page)", "true", bLogin.toString(), true);
		
//			Select the Forgot User ID Link on the Login screen
			bForgotUID = UI.WaitForObject(oR_Login.lnkForgotId, 10, lDriver);
			Report.TestPoint(testContext.getName(), "Select the Forgot ID Link on the Login screen", "true", bForgotUID.toString(), true);
			oR_Login.lnkForgotId.click();
			
//	14		Enter an email address that is associated with more than 5 USER IDs and at least one of those is a legacy CTN login

			oR_Login.edtContactEmailAddress.sendKeys("KeystoneUATOffshore@TechMahindra.com");
			Report.ValidationPoint(testContext.getName(), "14-Enter an email address that is associated with more than 5 USER IDs and at least one of those is a legacy CTN login", "true", "true", true);
			oR_Login.btnContinue.click();
	
			Report.OperationPoint(testContext.getName(), "15-User is directed to this page after entering an email address that is associated with more than 5 USER IDs and at least one of those is a legacy CTN login and selecting Continue");

//	15		User is directed to this page after entering an email address that is associated with more than 5 USER IDs and at least one of those is a legacy CTN login and selecting Continue
			bUserIDSent = UI.WaitForObject(oR_Login.txtUserIDSent, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "15-i-User ID Sent", "true", bUserIDSent.toString(), true);
			
			bCheck = UI.WaitForObject(oR_Login.imgCheckMark, 10, lDriver);
			bAsRequestedText =  UI.WaitForObject(oR_Login.txtAsRequestedWeHaveSent, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "15-ii- (checkmark) As requested, we,ve sent your User ID via email to (email address)", "true", String.valueOf(bCheck && bAsRequestedText), true);
				
			bNeedHelpLink =  UI.WaitForObject(oR_Login.lnkNeedHelpWithYourPassword, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "15-iii- Need help with your Password? Link", "true", bNeedHelpLink.toString(), true);

			bLoginBtn =  UI.WaitForObject(oR_Login.lnkLoginBtn, 10, lDriver);
			Report.ValidationPoint(testContext.getName(), "15-iv- Need help with your Password? Link", "true", bLoginBtn.toString(), true);

//	16		Progress Indicator - none
			Report.OperationPoint(testContext.getName(), "16-Progress Indicator - none");
			
//	17		Verify Screen content display - spelling, format, field layout, etc.
			Report.OperationPoint(testContext.getName(), "17-Verify Screen content display - spelling, format, field layout, etc.");

//  18      Select Need help with your password? Link
			Report.OperationPoint(testContext.getName(), "18-Select Need help with your password? Link");
			oR_Login.lnkNeedHelpWithYourPassword.click();
			
			bHeader = UI.WaitForObject(oR_Login.txtForgotIDPasswordHeader, 10, lDriver);
			bPwdRad = UI.WaitForObject(oR_Login.radPassword, 10, lDriver);
			if(bUIDRad)
			{
				if(oR_Login.radPassword.findElement(By.id("forgotPassword")).isSelected())
					Report.ValidationPoint(testContext.getName(), "Verify user is directed to step 1 of the Forgot User ID/Password page with Password Radio Button pre-selected", "true", "true", true);
				else
					Report.ValidationPoint(testContext.getName(), "Verify user is directed to step 1 of the Forgot User ID/Password page with Password Radio Button pre-selected", "true", "true", true);
			}
			else
				Report.ValidationPoint(testContext.getName(), "User ID radio button is present", "true", "false", true);

			Report.OperationPoint(testContext.getName(), "To continue with flow, return to User ID Sent page");
		
			lDriver.navigate().back();
			Thread.sleep(7000);
			
//	19		Select Login button

			bLoginBtn =  UI.WaitForObject(oR_Login.lnkLoginBtn, 20, lDriver);
			Report.ValidationPoint(testContext.getName(), "19-Select Login button", "true", bLoginBtn.toString(), true);
			oR_Login.lnkLoginBtn.click();
			
			bLogin = UI.WaitForObject(oR_Login.btnLogin, 20, lDriver);
			Report.ValidationPoint(testContext.getName(), "Verify user is directed to the myAT&T Login page", "true", bLogin.toString(), true);

//	20		Confirmation Email
			Report.OperationPoint(testContext.getName(), "Confirmation Email");

		}
		catch (Exception e)
		{
				Report.TestPoint(testContext.getName(), e.getMessage(), "True", "False", true);
		}
	}
}
