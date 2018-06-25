package com.OR.MyATTZone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OR_MyATTZone {

	@FindBy(css="font#STM0XX0YY0ZZTX.st_ftcss strong") 
	public WebElement AccessCustomerAccounts_lblSearchMenu;
	@FindBy(xpath="//*[@id='STM0XX0YY1ZZTX']") 
	public  WebElement AccessCustomerAccounts_lnkATTAccessID;
	@FindBy(xpath="//a[@id='PN-MSRT']")   //"//ol[@id='PrimaryNav']/li[5]/a/img") 
	public WebElement AccessCustomerAccounts_lnkMobileShareRecommender;
	@FindBy(css="td#STM0XX0YY3ZZMTD.st_tdcss font#STM0XX0YY3ZZTX.st_ftcss") 
	public WebElement AccessCustomerAccounts_lnkUverse;
	@FindBy(css="td#STM0XX2YY1ZZMTD.st_tdcss font#STM0XX2YY1ZZTX.st_ftcss") 
	public WebElement AccessCustomerAccounts_lnkUverse_BAN;
	@FindBy(css="td#STM0XX2YY0ZZMTD.st_tdcss font#STM0XX2YY0ZZTX.st_ftcss") 
	public WebElement AccessCustomerAccounts_lnkUverse_MemberID;
	@FindBy(css="font#STM0XX0YY2ZZTX.st_ftcss") 
	public WebElement AccessCustomerAccounts_lnkWireless;
	@FindBy(css="td#STM0XX1YY1ZZMTD.st_tdcss font#STM0XX1YY1ZZTX.st_ftcss") 
	public WebElement AccessCustomerAccounts_lnkWireless_BAN;
	@FindBy(css="font#STM0XX1YY0ZZTX.st_ftcss") 
	public WebElement AccessCustomerAccounts_lnkWireless_CTN;
	@FindBy(css="td#STM0XX0YY4ZZMTD.st_tdcss font#STM0XX0YY4ZZTX.st_ftcss") 
	public WebElement AccessCustomerAccounts_lnkWireline;
	@FindBy(css="td#STM0XX3YY1ZZMTD.st_tdcss font#STM0XX3YY1ZZTX.st_ftcss") 
	public WebElement AccessCustomerAccounts_lnkWireline_BTN;
	@FindBy(css="td#STM0XX3YY0ZZMTD.st_tdcss font#STM0XX3YY0ZZTX.st_ftcss") 
	public WebElement AccessCustomerAccounts_lnkWireline_LoginID;
	@FindBy(name="btnSubmit") 
	public WebElement Login_btnOK;
	@FindBy(name="userid") 
	public WebElement Login_txtATTUID;
	@FindBy(name="password") 
	public WebElement Login_txtPassword;
	@FindBy(name="successOK") 
	public WebElement LogOn_btnOK; 
	@FindBy(xpath="//input[contains(@onclick,'Token(false)')][@type='radio'][@name='authmethod']") 
	public WebElement radSecureIdToken; 
	@FindBy(xpath="//input[@type='checkbox' and contains(@name,'SoftToken')]") 
	public WebElement chkIamUsingSoftToken; 
	@FindBy(name="tokenpin") 
	public WebElement edtPasscode;
	
	@FindBy(id="srv_TitleMsg") 
	public WebElement LogOn_lblSuccessMessage;
	@FindBy(css="form#frmCtn table tbody tr td input") 
	public WebElement MobileShareDataPlanner_btnSubmit;
	@FindBy(id="ctn") 
	public WebElement MobileShareDataPlanner_txtCTN;
	@FindBy(css="div#cpcPlanDetailsButton.sectionHeadR img.currentPlanDetails") 
	public WebElement MobileShareTool_btnLeftPlanDetails;
	@FindBy(css="img.mobileshareDetails") 
	public WebElement MobileShareTool_btnRightPlanDetails;
	@FindBy(css="div.href_allPlans span a u b") 
	public WebElement MobileShareTool_lnkComparePlans;
	@FindBy(xpath="//span[@class='dynamicbar']/canvas")
	public WebElement MobileShareTool_drwPieChart;
	@FindBy(css="span#totalmrccollapse") 
	public WebElement MobileShareTool_lblLeftTotal;
	@FindBy(css="span#rstotalmrc") 
	public WebElement MobileShareTool_lblRightTotal;
	@FindBy(css="div#mrcSection.totalWrap.floatleft div#mobileShareMrcSection.mobileshareTotal div.floatleft span.font-12 strong") 
	public WebElement MobileShareTool_lblRightTotalMessage;
	@FindBy(css="div#_cpcPopup.planWrap div.plan-block div.heading p.font-15 strong") 
	public WebElement MobileShareTool_LightBox_lblPlanName;
	@FindBy(css="table#_totalDataSec.detaDetails.font-12 tbody") 
	public WebElement MobileShareTool_tblDataDetails;
	@FindBy(css="table#_totalTextSec.detaDetails.font-12 tbody") 
	public WebElement MobileShareTool_tblTextDetails;
	@FindBy(css="table#_totalTalkSec.detaDetails.font-12 tbody") 
	public WebElement MobileShareTool_tblTalkDetails;
	@FindBy(css="div#currentplanLB.lightboxWrap div.lightbox img.close") 
	public WebElement MobileShareTool_LightBox_btnClose;
	@FindBy(xpath="//*[@id='totalmrc']/parent::*") 
	public WebElement MobileShareTool_LightBox_lblTotalMRC;
	@FindBy(css="#_cpcPopup > div.plan-block > div.content.font-14 > #bordercollapse > tbody") 
	public WebElement MobileShareTool_LightBox_tblPlanDetails;
	@FindBy(css="input#usermrc.enterbox") 
	public WebElement MobileShareTool_LightBox_txtDiscount;
	@FindBy(css="div#allPlanDetails.lightboxWrap div.lightbox div#selectSuggestedPlan1 div.content.font-14 div.allplans-row") 
	public WebElement MobileShareTool_CompareBox_tblPlanDetails;
	@FindBy(css="div#allPlanDetails.lightboxWrap div.lightbox div.allplans-row div.grayrow-middle span img.close2") 
	public WebElement MobileShareTool_CompareBox_imgClose;
	@FindBy(partialLinkText="SmartLimits CSR Tool") 
	public WebElement OnlineAccountMgmt_lnkSmartLimitsTool;
	@FindBy(xpath="//input[@title='Cancel']") 
	public WebElement Option_SelectLoginID_btnCancel;
	@FindBy(id="continue") 
	public WebElement Option_SelectLoginID_btnContinue;
	@FindBy(xpath="//input[@id='selectedSlid'][1]") 
	public WebElement Option_SelectLoginID_rdoID;
	@FindBy(xpath="//input[@src='/tbmcsr/_assets/images/bt_search.png']") 
	public WebElement Select_UverseBAN_btnSearch;
	@FindBy(id="id_uvMemberID") 
	public WebElement Select_UverseMID_txtMemberID;
	@FindBy(css="tbody tr td.formlabel") 
	public WebElement Select_UverseBAN_lblUANumber;
	@FindBy(id="id_uvAccountNumber") 
	public WebElement Select_UverseBAN_txtAccountNum;
	@FindBy(xpath="//input[@src='/tbmcsr/_assets/images/bt_search.png']") 
	public WebElement Select_WirelessBAN_btnSearch;
	@FindBy(css="tbody tr td.formlabel") 
	public WebElement Select_WirelessBAN_lblUANumber;
	@FindBy(id="id_accountNumber") 
	public WebElement Select_WirelessBAN_txtAccountNum;
	@FindBy(xpath="//input[@src='/tbmcsr/_assets/images/bt_search.png']") 
	public WebElement Select_WirelessCTN_btnSearch;
	@FindBy(xpath="//td[contains(text(),'Mobile Number:')]") 
	public WebElement Select_WirelessCTN_lblMobileNum;
	@FindBy(css="a#tool_tip") 
	public WebElement Select_WirelessCTN_lnkDataInputRules;
	@FindBy(name="mobileNumber") 
	public WebElement Select_WirelessCTN_txtMobileNum;
	@FindBy(xpath="//input[@src='/tbmcsr/_assets/images/bt_search.png']") 
	public WebElement Select_WirelineBTN_btnSearch;
	@FindBy(name="wlBTN") 
	public WebElement Select_WirelineBTN_txtNum;
	@FindBy(xpath="//img[@src='/tbmcsr/_assets/images/bt_cust_act.png']") 
	public WebElement Wireless_Details_btnCustomerActivity;
	@FindBy(xpath="//img[@src='/tbmcsr/_assets/images/bt_mobile_impersonate.png']") 
	public WebElement Wireless_Details_btnMobileImpersonate;
	@FindBy(xpath="//img[@src='/tbmcsr/_assets/images/bt_msrt.png']") 
	public WebElement Wireless_Details_btnMobileShareRecommender;
	@FindBy(xpath="//img[@src='/tbmcsr/_assets/images/bt_sub_mgmt.png']") 
	public WebElement Wireless_Details_btnSubscriberManagement;
	@FindBy(xpath="//img[@src='/tbmcsr/_assets/images/bt_web_impersonate.png']") 
	public WebElement Wireless_Details_btnWebImpersonate;
	@FindBy(css="div#edocsArea table.moduleform tbody tr td") 
	public WebElement Wireless_Details_lblHeading;
	@FindBy(xpath="//table[5]/tbody/tr/td/strong") 
	public WebElement Wireline_SubscriberManagement_lblWirelineDetails;
	@FindBy(css="div.left-colWrap div.left-col div.slideDown") 
	public WebElement MobileShareTool_LeftSlider_btnSlideDown;
	@FindBy(css="div#mobileShareSection.contentArea div.deviceusage a u span b") 
	public WebElement MobileShareTool_RS_lnkModifyUsage;
	@FindBy(xpath="//div[@id='selectedDeviceLegend']/li[3]/span[2]") 
	public WebElement MobileShareTool_RS_lblFirstDeviceUsage;
	@FindBy(css="div.lightbox-top div.content.font-17 div.floatleft strong") 
	public WebElement MobileShareTool_DeviceUsageSumm_lblTitle;
	@FindBy(css="div#usagesliderdevice div.plan-block div.content span a u") 
	public WebElement MobileShareTool_DeviceUsageSumm_lnkUsageEstimator;
	@FindBy(css="div#eq") 
	public WebElement MobileShareTool_DeviceUsageSumm_tblDetails;
	@FindBy(id="applyquestion") 
	public WebElement MobileShareTool_LS_lnkDiscounts;
	@FindBy(id="PN-Reports") 
	public WebElement AccessCustomerAccounts_lnkAgentActivity;
	@FindBy(name="attUID") 
	public WebElement AgentActivity_txtUID;
	@FindBy(id="startDate") 
	public WebElement AgentActivity_txtFromDate;
	@FindBy(id="endDate") 
	public WebElement AgentActivity_txtEndDate;
	@FindBy(xpath="//input[@src='/tbmcsr/_assets/images/bt_search.png']") 
	public WebElement AgentActivity_btnSearch;
	@FindBy(css="table#insured_list.tablesorter tbody") 
	public WebElement AgentActivity_tblActivityDetails;
	@FindBy(xpath="//table[@id='bordercollapse'][2]")
	public WebElement MobileShareTool_RS_CompareDetails_tblPlanDetails;
	@FindBy(xpath="//img[@src='/tbmcsr/msrt/Sdg/images/bt_click_here.png'][2]") 
	public WebElement MobileShareTool_btnClickHere;
	@FindBy(css="div#_lsFanDetails span#iruapply") 
	public WebElement MobileShareTool_LeftSide_lblApplyDiscount;
	@FindBy(css="div#_lsFanDetails a u span#applyquestion.currentPlanDetails") 
	public WebElement MobileShareTool_LeftSide_lnkApplyDiscount;
	@FindBy(css="div.cpTotal-bg.floatleft.font-12 span#_maxmrc") 
	public WebElement MobileShareTool_LS_Lightbox_lblDiscountMaxMRC;
	@FindBy(css="div#_lsFanDetails a u span#changequestion.currentPlanDetails") 
	public WebElement MobileShareTool_LeftSide_lnkChangeDiscount;
	@FindBy(css="div.mobileShareWrap.floatleft div span#irursapply") 
	public WebElement MobileShareTool_RightSide_lblDiscount;
	@FindBy(css=" div a u span#rsapplyquestion.mobileshareDetails") 
	public WebElement MobileShareTool_RightSide_lnkDiscount;
	@FindBy(css="div.plan-block div.cpTotal-bg.font-12 div.floatleft") 
	public WebElement MobileShareTool_CompareBox_lblAccountInfo;
	@FindBy(css="input#userdiscountrs.enterbox") 
	public WebElement MobileShareTool_CompareBox_txtDiscount;
	@FindBy(css="span#originalmrc") 
	public WebElement MobileShareTool_CompareBox_lblOriginalMRC;
	@FindBy(css="span#greendiscount") 
	public WebElement MobileShareTool_CompareBox_lblGreenDiscount;
	@FindBy(css="span#deriveddiscount") 
	public WebElement MobileShareTool_CompareBox_lblDerivedDiscount;
	@FindBy(css="span#totalmrcrs") 
	public WebElement MobileShareTool_CompareBox_lblTotalMRCs;
	@FindBy(css=" img#btn_addDeviceWhite.addDeviceBTN") 
	public WebElement MobileShareTool_RS_btnAddDevice;
	@FindBy(css="div.styled-select.floatright select#select2.select2") 
	public WebElement AddDevice_lstOptions;
	@FindBy(css=" input#inp_device0.input-text") 
	public WebElement AddDevice_txtPlanGB;
	@FindBy(css="img.addbutton") 
	public WebElement AddDevice_btnAdd;
	@FindBy(css="img#btn_addAnotherDevice") 
	public WebElement AddDevice_btnAddAnotherDevice;
	@FindBy(css="img#btn_recommend") 
	public WebElement AddDevice_btnShowComparison;
	@FindBy(css="div.sectionHead div#_addDeviceAlert span") 
	public WebElement MobileShareTool_AddDevice_lblAlert;
	@FindBy(css="div#usageslider.lightboxWrap div.lightbox img.close2") 
	public WebElement MobileShareTool_DeviceUsageSumm_btnClose;
	@FindBy(css="div.lightbox img.close") 
	public WebElement MobileShareTool_RightSidePlanDetails_btnClose;
	@FindBy(xpath="//div[@class='grayrow-middle']") 
	public WebElement MobileShareTool_CompareBox_tblColHeading;
	@FindBy(css=" div.heading.clearfix p.font-15.floatleft strong")
	public WebElement MobileShareTool_RS_PlanDetails_lblPlanName;
	@FindBy(id="id_slidSearch") 
	public WebElement ATTAccessID_txtSLID;
	@FindBy(xpath="//input[@src='/tbmcsr/_assets/images/bt_search.png']") 
	public WebElement ATTAccessID_btnSearch;
	@FindBy(css="a#ge5p_z2_p2001.ge5p_z2-primary-nav-el")
	public WebElement WebImpersonate_lnkShop;
	
	// Web Impersonate
	@FindBy(css="a.posStatic button.medium.primary")
	public WebElement WebImpersonate_btnMakePayment;
	@FindBy(name="customerSearch")
	public WebElement WebImpersonate_lnkReturnToSearch;
	@FindBy(css="div#UserName h2.font16 span.fontWeightBoldForce.newGreyLT") 
	public WebElement WebImpersonate_lblUserName;
	@FindBy(id="ddShortcut") public WebElement WebImpersonate_ddnQuickLink;
	@FindBy(css="div.lineHeight30.right.float-left.w195") 
	public WebElement WebImpersonate_lblAmount;
	@FindBy(xpath="//*[contains(text(),'as of')]") 
	public WebElement WebImpersonate_lblAsOfToday;
	@FindBy(xpath="//a[contains(text(), 'View bill details')]") 
	public WebElement WebImpersonate_lnkViewBillDetails;
	@FindBy(xpath="//a[@alt='Enroll in Paperless Billing']") 
	public WebElement WebImpersonate_lnkEnrolPaperlessBilling;
	@FindBy(xpath="//a[contains(text(), 'AutoPay')]") 
	public WebElement WebImpersonate_lnkManageAutoPay;
	@FindBy(css="div#dashboardMessageCenterDiv.mod-message-center h5.font14.float-left.rel.top8") 
	public WebElement WebImpersonate_lblATTMessages;
	@FindBy(id="displayMessage") 
	public WebElement WebImpersonate_lblMessageArea;
	@FindBy(id="scrollUp") 
	public WebElement WebImpersonate_lnkMessageScrollUp;
	@FindBy(id="scrollDown") 
	public WebElement WebImpersonate_lnkMessageScrollDown;
	@FindBy(css="div.viewAll.float-left.rel.top10px a.wt_Body") 
	public WebElement WebImpersonate_lnkMessageViewAll;
	@FindBy(xpath="//h1[contains(text(), 'Billing & Usage')]") 
	public WebElement WebImpersonate_Billing_lblBillingUsage;
	@FindBy(xpath="//a[text()='Make a Payment']")
	public WebElement WebImpersonate_Billing_btnPayment;
	@FindBy(xpath="//a//*[text()='Enroll in paperless billing']") 
	public WebElement WebImpersonate_Billing_lnkEnrolPaperlessBilling;
	@FindBy(xpath="//a[text()='Billing, Usage, Payments']") 
	public WebElement WebImpersonate_lnkBillingUsage;
	@FindBy(xpath="//*[@alt='Go Paperless']")
	public WebElement WebImpersonate_imgGoPaperless;
	@FindBy(xpath="//img[@alt='Enroll']")
	public WebElement WebImpersonate_btnPaperlessEnrol;
	@FindBy(xpath="//h1[contains(text(), 'Paperless Billing Setting Confirmation')]") 
	public WebElement WebImpersonate_lblPaperlessConfirm;
	@FindBy(xpath="//h1[contains(text(), 'Make a Payment')]") 
	public WebElement WebImpersonate_MakePayment_lblTitle;
	@FindBy(xpath="//h1[contains(text(), 'New Checking / Savings Payment Method')]") 
	public WebElement WebImpersonate_PaymentMethod_lblTitle;
	@FindBy(id="paymentMethodForm.newBankCustomerName") 
	public WebElement WebImpersonate_PaymentPopUp_txtBankName;
	@FindBy(id="paymentMethodForm.routingNumber") 
	public WebElement WebImpersonate_PaymentPopUp_txtRoutingNum;
	@FindBy(id="paymentMethodForm.accountNumber") 
	public WebElement WebImpersonate_PaymentPopUp_txtAccountNum;
	@FindBy(id="paymentMethodForm.accountNumberConfirm") 
	public WebElement WebImpersonate_PaymentPopUp_txtAccountConfirm;
	@FindBy(xpath="//img[src='/olam/English/brand30/bt/btnContinue.gif']") 
	public WebElement WebImpersonate_PaymentPopUp_btnContinue;
	@FindBy(id="idBtnNext") 
	public WebElement WebImpersonate_PaymentPopUp_btnNext;
	@FindBy(xpath="//h1[contains(text(), 'Review Payment Details')]") 
	public WebElement WebImpersonate_ReviewPayment_lblTitle;
	@FindBy(id="cancel") 
	public WebElement WebImpersonate_ReviewPayment_lnkCancel;
	@FindBy(xpath="//a[@alt='Edit this payment method']") 
	public WebElement WebImpersonate_ReviewPayment_lnkEditPayment;
	@FindBy(xpath="//a[@href='/olam/passthroughAction.myworld?actionType=ProfileManageSub']") 
	public WebElement WebImpersonate_Profile_lnkManageSubAccounts;
	@FindBy(xpath="//img[contains(@alt, 'Uverse']") 
	public WebElement WebImpersonate_Promo_imgUverse;
	@FindBy(xpath="//a[@id='ge5p_z2_s3004']") 
	public WebElement WebImpersonate_myATT_DigitalTV;
	@FindBy(xpath="//a[@id='ge5p_z2_s3006']") 
	public WebElement WebImpersonate_myATT_HomePhone;
	@FindBy(xpath="//a[@id='ge5p_z2_s3005']") 
	public WebElement WebImpersonate_myATT_Internet;
	@FindBy(xpath="//a[@id='ge5p_z2_t3052']") 
	public WebElement WebImpersonate_myATT_DigitalTV_UpgradeToHD;
	@FindBy(xpath="//a[@id='ge5p_z2_t3048']") 
	public WebElement WebImpersonate_myATT_DigitalTV_AddReciever; 
	@FindBy(xpath="//a[contains(@class,'Tertiary')][text()='Make A Payment']") 
	public WebElement WebImpersonate_myATT_MakePayment_TerNavigation;
	@FindBy(id="payoff") 
	public WebElement WebImpersonate_myATT_lnkPayOffInstallments;
	
	@FindBy(xpath="//iframe[contains(@class,'cboxIframe')]") 
	public WebElement WebImpersonate_myATT_frmCBOX;
	
	@FindBy(xpath="//h1[contains(text(),'Pay Off Installment Plan')]") 
	public WebElement WebImpersonate_myATT_TXTPayOffInstallments;
	
	@FindBy(xpath="//a[contains(@linkname,'Make a Payment')]") 
	public WebElement WebImpersonate_myATT_btnMakeAPayment;
	
	@FindBy(xpath="//frame[contains(@src,'Impersonation')]") 
	public WebElement frmMobileInpersonate;
	
	@FindBy(xpath="//frame[contains(@src,'tabs')]") 
	public WebElement frmTabs;
	
	@FindBy(xpath="//frame[contains(@src,'loginAction')]") 
	public WebElement frmWebImpersonate;
	
	
	// Profile-SubAccounts
	@FindBy(xpath="//h1[Contains(text(), 'Manage Sub-Accounts')") 
	public WebElement WebImpersonate_SubAccounts_lblHeading;
	@FindBy(xpath="//a[Contains(text(), 'Edit')") 
	public WebElement WebImpersonate_SubAccounts_lnkEdit;
	@FindBy(xpath="//h1[Contains(text(), 'Delete')") 
	public WebElement WebImpersonate_SubAccounts_lnkDelete;
	@FindBy(xpath="//h1[Contains(text(), 'Disable')") 
	public WebElement WebImpersonate_SubAccounts_lnkDisable;
	@FindBy(id="emailAliasHandle") 
	public WebElement WebImpersonate_SubAccounts_txtEmailAlias;
	@FindBy(css="div.form_row div.text") 
	public WebElement WebImpersonate_SubAccounts_lblFName;
	
	@FindBy(id="filter") 
	public WebElement CustomerActivity_ddnSelect;
	@FindBy(id="startDate") 
	public WebElement CustomerActivity_txtStartDate;
	@FindBy(id="endDate") 
	public WebElement CustomerActivity_txtEndDate;
	@FindBy(xpath="//input[@src='/tbmcsr/_assets/images/bt_submit.png']") 
	public WebElement CustomerActivity_btnSubmit;
	@FindBy(id="insured_list") 
	public WebElement CustomerActivity_tblActivityDetails;
	@FindBy(xpath="//img[@src='/tbmcsr/_assets/images/bt_logout.png']") 
	public WebElement CSR_btnLogout;
	@FindBy(css=" bean:write table tbody tr td") 
	public WebElement SubscriberMgmt_lblTitle;
	@FindBy(css="bean:write ol li") 
	public WebElement SubscriberMgmt_lblNote;
	@FindBy(xpath="//*[@name='uverseSubMgmtForm']/table[3]") 
	public WebElement SubscriberMgmt_tblMemberDetails;
	@FindBy(xpath="//*[@name='uverseSubMgmtForm']/table[4]") 
	public WebElement SubscriberMgmt_tblSubMemberDetails;
	@FindBy(id="resetPwd") 
	public WebElement SubscriberMgmt_rdoResetPwd;
	@FindBy(id="resetPwdQA") 
	public WebElement SubscriberMgmt_rdoResetPwdQA;
	@FindBy(xpath="//img[@src='_assets/images/bt_submit.png']") 
	public WebElement SubscriberMgmt_btnSubmit;
	@FindBy(xpath="//select[@id='notifyBy']") 
	public WebElement SubscriberMgmt_ddnNotifyBy;
	@FindBy(css="div#errorDiv ul html:messages#error li") 
	public WebElement SubscriberMgmt_lblErrorMessage;
	@FindBy(xpath=".//*[@id='wrapper']/html:form/table[2]/tbody/tr[2]/td") 
	public WebElement SubscriberMgmt_lblHeading;
	@FindBy(xpath="//a[@onclick='return cbrAlertMessage();']/span") 
	public WebElement SubscriberMgmt_lnkExclamation;
	
	@FindBy(xpath="//a[contains(text(), 'View Details')]") 
	public WebElement SubscriberMgmt_lnkViewDetails;
	@FindBy(id="cbrValue") 
	public WebElement SubscriberMgmt_lblCBRValue;
	
	@FindBy(id="capturedCbr") 
	public WebElement SubscriberMgmt_ViewDetailsPop_txtCBRValue;
	@FindBy(id="selectTypeDropDown") 
	public WebElement SubscriberMgmt_ViewDetailsPop_ddlType;
	@FindBy(css="div#CbrValidUnchecked img") 
	public WebElement SubscriberMgmt_ViewDetailsPop_chkDeviceValidated;
	@FindBy(css="div#SmsMobileBlue a img") 
	public WebElement SubscriberMgmt_ViewDetailsPop_btnSendSMS;
	@FindBy(xpath="//img[@src='_assets/images/bt_verify_cbr.png']") 
	public WebElement SubscriberMgmt_ViewDetailsPop_btnVerifyCBR;
	@FindBy(id="cbrError") 
	public WebElement SubscriberMgmt_ViewDetailsPop_lblError;
	@FindBy(id="cbrSmsPopup") 
	public WebElement SubscriberMgmt_SendSMS;
	
	@FindBy(id="modifyCbrPopup") 
	public WebElement SubscriberMgmt_ModifyCBRPopUp;
	@FindBy(xpath="//a[@href='/tbmcsr/cbrConsent.do?validationStatus=NoConsent']/img") 
	public WebElement SubscriberMgmt_ModifyCBRPopUp_btnSubmit;
	
	
	// Subscriber Management - Exclamation Sign
	@FindBy(id="cbrAlertMessagePopup") 
	public WebElement SubscriberMgmt_popAlertMsg;
	
	@FindBy(xpath="*//a[text()='View Details'][contains(@onclick,'viewCbrDetails')]") 
	public WebElement lnkViewDetailsForCBR;
	
	// Added on 4/14
	@FindBy(xpath="//img[@src='/tbmcsr/_assets/images/myAT&T_header.png']") 
	public WebElement AccessCustomerAccounts_imgMyATTZone;
	@FindBy(xpath="//*[@id='STM0XX0YY5ZZTX']") 
	public WebElement AccessCustomerAccounts_lnkDSL;
	@FindBy(css="td#STM0XX0YY6ZZMTD.st_tdcss font#STM0XX0YY6ZZTX.st_ftcss") 
	public WebElement AccessCustomerAccounts_lnkFAN;
	@FindBy(xpath="//*[@id='STM0XX0YY7ZZTX']") 
	public WebElement AccessCustomerAccounts_lnkFreeEmail;

	@FindBy(xpath="//input[@id='dslMemberIDSearch']") 
	public WebElement Select_FreeEmail_txtFreeEmailID;
	@FindBy(xpath="//input[@src='/tbmcsr/_assets/images/bt_search.png']") 
	public WebElement Select_FreeEmail_imgSearch;
	@FindBy(xpath="//div[@class='errorMsg']") 
	public WebElement AccessCustomerAccounts_lblError;
	@FindBy(xpath="//a[contains(text(), 'Data input rules')]") 
	public WebElement AccessCustomerAccounts_lnkDataInputRules;
	
	// DSL
	@FindBy(xpath="//font[@id='STM0XX4YY0ZZTX']") 
	public WebElement AccessCustomerAccounts_lnkDSL_MemberID;
	@FindBy(id="dslMemberIDSearch") 
	public WebElement SelectDSL_txtMemberID;
	@FindBy(xpath="//input[@src='/tbmcsr/_assets/images/bt_search.png']") 
	public WebElement SelectDSL_btnSearch;
	@FindBy(id="wireless_num") 
	public WebElement MyATT_txtLoginID;
	@FindBy(id="password") 
	public WebElement MyATT_txtPwd;
	@FindBy(xpath="//input[@src='/olam/English/brand30/bt/LoginOLAM.png']") 
	public WebElement MyATT_btnLogIn;
	
	// FAN
	@FindBy(id="id_wlBTN") 
	public WebElement AccessCustomerAccounts_txtFAN;
	@FindBy(xpath="//div[@id='fandetails']/div/span[2]") 
	public WebElement FANPopUp_lblFAN;
	@FindBy(xpath="//input[@src='_assets/images/bt_No.png']")
	public WebElement FANPopUp_btnNo;
	@FindBy(id="continue")
	public WebElement FANPopUp_btnYes;
	
	
	@FindBy(xpath="(//td[@class='moduleheadercopy'])[2]") 
	public WebElement AccessATTID_lblSearchHeader;
	
	
	// Profile Tab Area
	@FindBy(id="PN-Profiles") 
	public WebElement AccessCustomerAccounts_tabProfile;
	@FindBy(xpath="//input[@name='csrId']") 
	public WebElement ManageCSR_txtATTUID;
	@FindBy(xpath="//input[@src='/tbmcsr/_assets/images/bt_search.png']") 
	public WebElement ManageCSR_btnSearch;
	@FindBy(xpath="//a[@text='Edit']") 
	public WebElement ManageCSR_lnkEdit;
	@FindBy(xpath="//div[contains(text(), 'Please edit CSR profile information')]") 
	public WebElement ManageCSR_lblEditCSRInfo;
	@FindBy(xpath="//select[@name='role']") 
	public WebElement ManageCSR_lstRoles;
	@FindBy(xpath="//input[@src='/tbmcsr/_assets/images/bt_submit.png']") 
	public WebElement ManageCSR_btnSubmit;
	
	// Paperless Billing
	@FindBy(xpath="//a[@href='/view/billPayLandingAction.do']") 
	public WebElement PaperlessBilling_lnkBillPayment;
	@FindBy(xpath="//a[@name='paperBill']") 
	public WebElement PaperlessBilling_lnkViewPaperBill;
	@FindBy(xpath="//a[contains(text(), 'Close window')]") 
	public WebElement PaperlessBilling_PopUpViewBill_lnkCloseWindow;
	
	// Digital TV
	@FindBy(id="ManageChannels") 
	public WebElement DigitalTV_lnkAddChangeChannels;
	@FindBy(css="html#ng-app.js head title") 
	public WebElement DigitalTV_AddChannels_lblDashboard;

	// Home Phone
	@FindBy(xpath="//img[@src='/OLAM_CMS_QA4//olam/English/brand30/bt/Chgplan86x24.png']") 
	public WebElement HomePhone_lnkChangePlan;
	
	// Internet
	@FindBy(css="div.page-title h1") 
	public WebElement Internet_lblMyInternetService;
	@FindBy(xpath="//img[@src='/OLAM_CMS_QA4//olam/English/brand30/bt/Chgplan86x24.png']") 
	public WebElement Internet_lnkChangePlan;
	@FindBy(id="userid") 
	public WebElement OLAM_txtLoginID;
	@FindBy(id="password") 
	public WebElement OLAM_txtPwd;
	@FindBy(id="tguardLoginButton") 
	public WebElement OLAM_btnLogIn;
	
	
	//Make a payment
	@FindBy(xpath="//iframe[contains(@src,'Add Credit/Debit Card')]")
	public WebElement frmNewDebitCreditCardPaymentMethod;
	@FindBy(xpath="//input[contains(@name,'CustomerName')]") 
	public WebElement edtPaymentFrameNameOnCard;
	@FindBy(xpath="//input[contains(@name,'cardNumber')]") 
	public WebElement edtPaymentFrameCardNumber;
	@FindBy(xpath="//input[contains(@name,'cardVerificationNumber')]") 
	public WebElement edtPaymentFrameSecurityNumber;
	@FindBy(xpath="//input[contains(@name,'zip')]") 
	public WebElement edtPaymentFrameZipCode;
	@FindBy(xpath="//input[contains(@name,'PaymentProfileName')]") 
	public WebElement edtPaymentFrameProfileName;
	@FindBy(xpath="//a[text()='Continue'][contains(@class,'button')]") 
	public WebElement btnPaymentFrameContinue;
	@FindBy(xpath="//a[contains(@id,'Anchor')][contains(text(),'Next')]") 
	public WebElement btnNext;  
	@FindBy(xpath="//a[contains(@id,'Anchor')][contains(text(),'Continue')]") 
	public WebElement btnContinue;
	@FindBy(xpath="//div[@id='amountDivChild0_1']//input")
	public WebElement edtPaymentAmount1;
	@FindBy(xpath="//a[@id='calendar_0_1']") 
	public WebElement imgCalender1;
	@FindBy(xpath="//iframe[contains(@src,'CalendarView')]")
	public WebElement frmCalender;
	@FindBy(xpath="//a[text()='Submit']|//a[contains(text(),'Submit')]") 
	public WebElement btnSubmit; 
	@FindBy(xpath="//*[contains(text(),'Confirmation')]") 
	public WebElement txtPaymentConfirmation;
	
	//Sales Support 
	@FindBy(xpath="//a[text()='Order Status']") 
	public WebElement lnkOrderStatus; 
	@FindBy(xpath="//a[text()='Order Management Portal']") 
	public WebElement lnkOrderManagementPortal;
	@FindBy(xpath="//a[text()='Wireless Activations']") 
	public WebElement lnkWirelessActivations;
	
	
	//------------------ Account Overview -------------------------//
	@FindBy(xpath="//div[@id='UserName']")  
	public WebElement txtWelcomeBack;
		
	@FindBy(xpath="//button[@id='iconMenu']") 
	public WebElement btnGlobalNavMenu_Mobile;
		
	@FindBy(xpath="//h1[contains(text(),'myAT&T Overview')] | //h1[contains(text(),'myAT&T Login - Pay Bills & Manage Your AT&T Account')]")
	public WebElement txtAccountOverview_Mobile;
	
	@FindBy(xpath="//div[@id='menuIcon']//a[contains(text(),'My service & plans')] | //div[@id='menuIcon']//a[contains(text(),'My plan & services')]")
	public WebElement lnk_MyPlanAndServicesUnderGlobalNav_Mobile;
	
	@FindBy(xpath="//h1[contains(text(),'Welcome')]")  
	public WebElement txtWelcome;
	
	//------------------ Billing Usage Payments Page -------------------------//
	@FindBy(xpath="//iframe[contains(@title,'TourMyBill')]") 
	public WebElement lnkTourMyBill;
	
	@FindBy(xpath="//iframe[contains(@title,'TourMyBill')]") 
	public WebElement frmTourMyBill;
	
	@FindBy(xpath="//a[contains(text(),'Close')]") 
	public WebElement lnkClose;
	
	
	
	//------------------ My wireless service -------------------------//
	
	@FindBy(xpath="//a[contains(text(),'Change plan')]") 
	public WebElement lnkChangePlan_Mobile;
	
	@FindBy(xpath="(//div[@id='CPMsrt']//table[@role='presentation'])[1]") 
	public WebElement elm_MSRTSection_Mobile;
	
	@FindBy(xpath="//button[@id='planbutton']//span[contains(text(),'Select')]") 
	public WebElement btnSelectPlan_Mobile;
	
	@FindBy(xpath="(//h2[contains(@ng-bind-html,'AddOns_Warning_Message')])[1]") 
	public WebElement txtWarningAlertMessage_Mobile;
	
	@FindBy(xpath="(//button[contains(text(),'Cancel')])[1]") 
	public WebElement btnAlertCancel_Mobile;
	
	@FindBy(xpath="(//button[contains(text(),'Change plan')])[1]") 
	public WebElement btnChangePlan_Mobile;
	
	@FindBy(xpath="//h1//span[contains(text(),'Change my plan')]") 
	public WebElement txtChangeMyPlanPage_Mobile;
	
	@FindBy(xpath="//h1[contains(text(),'Review changes')]") 
	public WebElement txtReviewChangesPage_Mobile;
	
	@FindBy(xpath="//input[@id='reviewTermsAgree']") 
	public WebElement rdoAgreeTerms_Mobile;
	
	@FindBy(xpath="//button[contains(text(),'Submit')]") 
	public WebElement btnSubmit_Mobile;
	
	@FindBy(xpath="//h1[contains(text(),'Your new rate plan')]") 
	public WebElement txtConfirmationPage_Mobile;
	
	
	WebDriver driver;
	
	public OR_MyATTZone(WebDriver driver){
		this.driver = driver;
	}
}
