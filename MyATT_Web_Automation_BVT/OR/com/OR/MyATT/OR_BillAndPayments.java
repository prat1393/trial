package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_BillAndPayments {
	@FindBy(css="font#STM0XX0YY0ZZTX.st_ftcss strong") 
	public WebElement AccessCustomerAccounts_lblSearchMenu;
	
	@FindBy(css="td#STM0XX0YY1ZZMTD.st_tdcss font#STM0XX0YY1ZZTX.st_ftcss") 
	public WebElement AccessCustomerAccounts_lnkATTAccessID;
	
	@FindBy(xpath="//ol[@id='PrimaryNav']/li[5]/a/img") 
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
	
	@FindBy(xpath="//div[@id='_cpcPopup']/div[2]/div/strong") 
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
	
	//frame Objects
	@FindBy(xpath="//iframe[@name='view_or_print_as_table']")
	public WebElement frmPrint;

	WebDriver driver;

	public OR_BillAndPayments(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
