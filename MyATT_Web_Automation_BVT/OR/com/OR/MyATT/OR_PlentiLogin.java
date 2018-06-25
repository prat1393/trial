package com.OR.MyATT;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OR_PlentiLogin {
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//input[@id='userID']|//input[@id='wireless_num']")
	public WebElement edtUserID;
	
	@FindBy(id="password")
	public WebElement edtPwd;
	
	@FindBy(xpath="//input[@id='plentiId']")
	public WebElement edtPlentiId;

	/*---------------------------
	 * Radio button objects add below
	 * *---------------------------*/
	@FindBy(id="havePlenti")
	public WebElement radLinkPlentiCard;
	
	@FindBy(id="NoPlenti")
	public WebElement radSignUpPlenti;
	
	@FindBy(id="consent1")
	public WebElement chkSignUpPlenti;
	
	@FindBy(id="consent2")
	public WebElement chkSignUpPlenti2;
	
	/*---------------------------
	 * Frames add below
	 *---------------------------*/
	
	@FindBy(xpath="//iframe[contains(@src,'WhatUserID')]")
	public WebElement frmWhichID;
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/


	
	@FindBy(xpath="//a[contains(text(),'Which ID should I use?')]")
	public WebElement lnkWhichID;
	
	@FindBy(xpath="//a[contains(text(),'AT&T Access ID')]/parent ::p")
	public WebElement lnkForgotUserID;
	
	@FindBy(xpath="//a[contains(text(),' Password')]/parent ::p")
	public WebElement lnkForgotPwd;
	
	@FindBy(xpath="//a[@title='registerToday']")
	public WebElement lnkRegisterToday;
	
	@FindBy(xpath="//a[contains(text(),'Close')]")
	public WebElement lnkCloseOnWhichID;
	
	
	@FindBy(xpath="//a[contains(text(),'Forgot user ID')]")
	public WebElement lnkForgotUserIDOnWhichID;
	
	
	@FindBy(xpath="//a[contains(text(),'Need a user')]")
	public WebElement lnkNeedAUserOnWhichID;
	
	@FindBy(xpath="//a[contains(text(),'Go to myAT&T overview')]")
	public WebElement lnkGoToAccOverview;
	

	@FindBy(xpath="//a[contains(text(),'Shop AT&T wireless service')]")
	public WebElement lnkShopATT;
	
	@FindBy(xpath="//a[@id='UserID1']")
	public WebElement lnkWhatIsThis;
	
	@FindBy(xpath="//a[@id='UserID2']")
	public WebElement lnkWhatIsThis2;
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/

	
	@FindBy(xpath="//button[contains(text(),'Log in')]")
	public WebElement btnLogin;
	
	
	@FindBy(xpath="//button[contains(text(),'OK')]")
	public WebElement btnOKOnWhichID;
	
	@FindBy(xpath="//button[contains(text(),'Log in with another account')]")
	public WebElement btnLoginWithAnotherAcc;
	
	@FindBy(xpath="//img[@alt='Log Out Button']")
	public WebElement btnLogOut;
	
	@FindBy(id="createPlentiLinkage1")
	public WebElement btnLYesLink;
	/*---------------------------
	 * Image objects add below
	 * *---------------------------*/
	@FindBy(xpath="//span[@id='remember']")
	public WebElement boxSave;

	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//label[@for='userID' and contains(text(),'AT&T Access ID')]")
	public WebElement txtUserID;
	
	@FindBy(xpath="//div[contains(text(),'Log in to your account to begin')]")
	public WebElement txtLoginToBegin;
	
	@FindBy(xpath="//label[contains(text(),'Password')]")
	public WebElement txtPwd;
	
	@FindBy(xpath="//label[contains(text(),'Save AT&T Access ID')]")
	public WebElement txtSaveUserID;
	
	@FindBy(xpath="//h1[contains(text(),'What ID')]")
	public WebElement txtWhichIDTitle;
	
	@FindBy(xpath="//p[contains(text(),'Plenti')]")
	public WebElement txtInfotmationalTxtOnWhichID;
	
	@FindBy(xpath="//p[contains(text(),'Wireless account')]")
	public WebElement txtWirelessAccountSectionOnWhichID;
	
	@FindBy(xpath="//p[contains(text(),'Combined bill')]")
	public WebElement txtCombinedBillSectionOnWhichID;
	
	@FindBy(xpath="//p[contains(text(),'Home phone')]")
	public WebElement txtHomePhoneSectionOnWhichID;
	
	@FindBy(xpath="//p[contains(text(),'You have an')]")
	public WebElement txtYouHaveAnSectionOnWhichID;
	
	@FindBy(xpath="//p[contains(text(),'U-verse service')]")
	public WebElement txtUverseServiceSectionOnWhichID;
	
	@FindBy(xpath="//h1[contains(text(),'Plenti')]")
	public WebElement txtPlentiLink;
	
	@FindBy(xpath="//p[contains(text(),'have any accounts  eligible')]")
	public WebElement txteligible;
	
	@FindBy(xpath="//h1[contains(text(),'Forgot User ID')]")
	public WebElement txtForgotUserIDTitle;
	
	
	@FindBy(xpath="//h2[contains(text(),'Provide Account Information')]")
	public WebElement txtAccountInformationTitle;
	
	@FindBy(xpath="//h1[contains(text(),'earning Plenti')]")
	public WebElement txtStartEarningTitle;
	
	@FindBy(xpath="//label[contains(text(),'Plenti card')]")
	public WebElement txtlabelPlantiId;
	
	@FindBy(xpath="//p[contains(text(),'Enter your last name')]")
	public WebElement txtAlertFieldMissing;
	
	@FindBy(xpath="//p[contains(text(),'Please enter a valid last name')]")
	public WebElement txtInvalidValueAlert;
	/*---------------------------
	 * Dropdown add below
	 *---------------------------*/

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}











