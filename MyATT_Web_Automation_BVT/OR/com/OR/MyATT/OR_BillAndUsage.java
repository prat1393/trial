package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_BillAndUsage {
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	
	//after clicking on view paper bill button
	@FindBy(xpath="//img[@src='/olam/English/images/att_logo.gif']")
	public WebElement imgATTlogo;
	//End
	
	//after clicking on print link
	@FindBy(xpath="//img[@alt='attlogo']")
	public WebElement imgATTlogoPrint;
	//End
	
	@FindBy(xpath="//table//img[@alt='Phone']")
	public WebElement imgDevice;
	
	@FindBy(xpath="//td[@headers='member_head']//img[@alt='alert']")
	public WebElement imgDeviceImageWithAlert;
	
	@FindBy(xpath="//th[@id='bill_period']/img[@alt='araccRowDown']")
	public WebElement imgSortAscDesBillPeriod;
	
	@FindBy(xpath="//img[contains(@src,'modalClose')]")
	public WebElement imgClose;
	
	@FindBy(xpath="//th[@id='bill_total']")
	public WebElement imgBillTotal;
	
	@FindBy(xpath="//img[@alt='help']")
	public WebElement imgHelpIconForEmployerData;
	
	@FindBy(xpath="//div[contains(text(),'National Account Discount')]/a/img")
	public WebElement imgNationalAccountDiscountToolTip;
	
	@FindBy(xpath="//div[@class='tip-inner'][contains(text(),'re receiving this service discount as a benefit')]")
	public WebElement imgNationalAccountDiscountToolTipFrame;
	
	@FindBy(xpath="//img[@alt='Uverse Phone']")
	public WebElement imgUversePhone;
	
	@FindBy(xpath="//img[@alt='help_icon']")
	public WebElement imgDomesticHelpIcon;
	
	@FindBy(xpath="//img[@alt='warning_icon']")
	public WebElement imgDomesticWarningIcon;
	
	@FindBy(xpath="//img[@alt='close']")
	public WebElement imgClose1;
	
	@FindBy(xpath="//img[contains(@src,'usage_unavailable')]")
	public WebElement imgUsageUnavailable;
	
	@FindBy(xpath="//img[@alt='close']")
	public WebElement imgclose;
	
	@FindBy(xpath="//img[contains(@src,'iconExpand')]")
	public WebElement imgExpandIcon;
	
	@FindBy(xpath="//img[contains(@src,'iconCollapse')]")
	public WebElement imgCollapseIcon;
	
	@FindBy(xpath="//img[@alt='alertIcon']")
	public WebElement imgAlertIcon;
	
	@FindBy(xpath="//div[@id='toggleGraph']//*[@alt='Alert']")
	public WebElement imgAlertYellowUnderPieChart;
	
	@FindBy(xpath="//th[@id='web_head']//*[@alt='warning']")
	public WebElement imgAlertYellowUnderWebHeader;
	
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	@FindBy(xpath="//input[@id ='Tile-View']")
	public WebElement radCarouselView;
	
	
	@FindBy(xpath="//input[@id ='Table-View']")
	public WebElement radTableView;
	
	@FindBy(xpath="//div[@id='uniform-nick_Name']")
	public WebElement radNickname;
	
	@FindBy(xpath="//div[@id='uniform-show_numbers']")
	public WebElement radShowNumbers;
		
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[@id='ddShortcut']")
	public WebElement lstSelectReportType;
	
	@FindBy(xpath="//a[@id='ddShortcut1']")
	public WebElement lstStartDate;
	
	@FindBy(xpath="(//div[@id='ddShortcutBox1']//dl//a)[1]")
	public WebElement lstFirstAvailableStartDate;
	
	@FindBy(id="ddShortcut2")
	public WebElement lstEndDate;
	
	@FindBy(xpath="(//div[@id='ddShortcutBox2']//dl//a)[1]")
	public WebElement lstFirstAvailableEndDate;

	@FindBy(xpath="//a[contains(@id,'ddShortcut')] | //*[contains(text(),'Showing:')]")
	public WebElement lstShowing;
	
	@FindBy(xpath="//a[@id='ddShortcut1']")
	public WebElement lstBillStartDateDisabled;
	
	@FindBy(xpath="//a[@id='ddShortcut2']")
	public WebElement lstBillEndDateDisabled;
	
	@FindBy(xpath="//a[contains(@name,'Show')]")
	public WebElement lstShow;

	@FindBy(xpath="//a[contains(@name,'View usage for') or contains(@name,'View Usage for')]")
	public WebElement lstViewUsageFor;

	@FindBy(xpath="//div[contains(@id,'Usage')]//a[contains(@name,'View')]")
	public WebElement lnkViewUsageBy;
	
	@FindBy(xpath="//div[contains(@id,'show')]//a[contains(@name,'Show')] | //div[@id='timePeriod']/parent::div/parent::div/parent::a")
	public WebElement lnkShowDropDown;
	
	@FindBy(xpath="//a[@id='billingShortcut']//span[@id='timeRange']")
	public WebElement lstchangeBillingperiod;
	
	@FindBy(xpath="//a[@id='billingShortcut'] | //a[@id='ddShortcut']")
	public WebElement lstChangeBiilingPeriodDropDown;
	

	
	//
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[@class='print']")
	public WebElement lnkPrintTerms;

	@FindBy(xpath="//*[contains(text(),'Text') and contains(text(),'Data')]//parent::a")
	public WebElement lnkTextOrData;
	
	@FindBy(xpath="//*[contains(text(),'All')]//parent::a")
	public WebElement lnkAll;
	
	@FindBy(xpath="//*[contains(text(),'Internet / mobile data')]//parent::a")
	public WebElement lnkInternetOrMobileData;
	
	@FindBy(xpath="//input[@id='pmntProfileName']")
	public WebElement edtPaymentProfileName;
	
	@FindBy(xpath="//input[@id='newBankCustomerName']")
	public WebElement edtNameOnBankAccount;
	
	@FindBy(xpath="//input[@id='routingNumber']")
	public WebElement edtRoutingNumber;
	
	@FindBy(xpath="//input[@id='bankAccountNumber']")
	public WebElement edtBankAccountingNumber;
	
	@FindBy(xpath="//input[@id='bankAccountNumberConfirm']")
	public WebElement edtBankAccountingNumberConfirm;
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//h1[contains(text(),'Billing & Usage')] | //h1[contains(text(),'Usage')]")
	public WebElement txtBillingAndUsage;
	
	@FindBy(xpath="(//*[contains(@class,'date')]//*[contains(text(),'20')])[1]")
	public WebElement txtDateWithoutCalendar;
	
	@FindBy(xpath="//h2[contains(text(),'Bill Alerts')]")
	public WebElement txtBillAlerts;
	
	@FindBy(xpath="//span[contains(text(),'Card About to Expire')]")
	public WebElement txtCardHasExpired;
	
	@FindBy(xpath="//p[contains(text(),'Update the expiration date on your card ending in 7107')]")
	public WebElement txtAlertDescription;
	
	@FindBy(xpath="//div[@class='msg box botMar15']")
	public WebElement txtNoteForSuspension;
	
	@FindBy(xpath="//h2[contains(text(),'Account Details')]")
	public WebElement txtAccountDetails;
	
	@FindBy(xpath="//h2[contains(text(),'Payment Activity')]")
	public WebElement txtPaymentActivity;
	
	@FindBy(xpath="//div[@class='alertBoxGrad']//li[contains(@class,'SolidBorder')]")
	public WebElement txtAlertHEadingDyna;
	
	@FindBy(xpath="//div[@class='tip-white']//div[@class='tip-inner']")
	public WebElement txtNationalAccDiscountText;
	
	//payment options page
	@FindBy(xpath="//h1[contains(text(),'Payment Options')]")
	public WebElement txtPaymentOptions;
	//end of payment options page

	@FindBy(xpath="//div[@class='PadRight30 float-left PadLeft10']")
	public WebElement txtAccountDetailsSection;
	
	@FindBy(xpath="//h3[contains(text(),'Current Bill for')]")
	public WebElement txtCurrentBill;
	
	@FindBy(xpath="//div[contains(text(),'Previous Balance')]")
	public WebElement txtPreviousBalance;
	
	@FindBy(xpath="//p[contains(text(),'Present')] | //p[contains(text(),'Recent')]")
	public WebElement txtPresentBillingPeriod;
	
	@FindBy(xpath="//div[contains(text(),'Payment')] | //*[contains(text(),'Total Amount Due by')]")
	public WebElement txtPayment;
	
	@FindBy(xpath="//div[contains(text(),'Adjustments')]")
	public WebElement txtAdjustments;
	
	@FindBy(xpath="//p[contains(text(),'Total Amount Due')]|//*[contains(text(),'Total Amount Due')]")
	public WebElement txtTotalAmountDue;
	
	@FindBy(xpath="//p[contains(text(),'Amount Due in Full')]")
	public WebElement txtTotalAmountDueFullBy;
	
	@FindBy(xpath="//strong[contains(text(),'U-verse TV Installment Charges')]")
	public WebElement txtUverseTVInstallment;
	
	@FindBy(xpath="//div[@id='toggleGraph']//h2")
	public WebElement txtPlanName;
	//inside the change billing address frame
	@FindBy(xpath="//h2[contains(text(),'Change Billing Address')]")
	public WebElement txtChangeBillingAddress;
	//End

	@FindBy(xpath="//h3[contains(text(),'Previous Activity for')]")
	public WebElement txtPreviousActivityFor;
	
	@FindBy(xpath="//div[@id='toggleUTV4']//div[@class='float-left accRow bold ']")
	public WebElement txtUverseTVTotalSurchargeFees;
	
	@FindBy(xpath="//div[@id='toggleUTV5']//div[@class='float-left accRow bold ']")
	public WebElement txtUverseTVTotalGovernmentFees;
	
	@FindBy(xpath="//div[@id='toggleUVV6']//div[@class='float-left accRow bold ']")
	public WebElement txtUverseVoiceTotalSurchargeFees;
	
	@FindBy(xpath="//div[@id='toggleUVV7']//div[@class='float-left accRow bold ']")
	public WebElement txtUverseVoiceTotalGovernmentFees;
	
	@FindBy(xpath="//h2[text()='Compare Bills']")
	public WebElement txtCompareBills;
	
	@FindBy(xpath="//div[@class='accRow' and contains(text(),'Installment') and contains(text(),'Est')]")
	public WebElement txtInstallmentDetails;
	
	@FindBy(xpath="//p[contains(text(),'Total Amount Due by ')]")
	public WebElement txtTotalAmtDueBy;
	
	@FindBy(xpath="//h2[contains(text(),'OnStar Base Plan')]/sup")
	public WebElement txtOnStar;
	
	@FindBy(xpath="//p[@class='botMar5 font16 PadTop10']")
	public WebElement txtOnstarNicknameCTN;
	
	@FindBy(xpath="//h2[contains(text(),'OnStar Base Plan') and sup]")
	public WebElement txtOnstarLabel;
	
	@FindBy(xpath="//div[@class='float-left accRow'][contains(text(),'OnStar Base Plan')]")
	public WebElement txtOnstarBasePlan;
	
	@FindBy(xpath="//div[@class='float-left accRow'][contains(text(),'OnStar 1GB Data')]")
	public WebElement txtOnstarDataPlan;
	
	@FindBy(xpath="//p[contains(text(),'Account:')]")
	public WebElement txtAccountNoInBillAndUsagePage;
	
	@FindBy(xpath="//ul[contains(@class,'map-account-info')]//li | //span[@class=' top2px']")
	public WebElement txtAccountNoInManageAutopayPage;
	
	@FindBy(xpath="//h3[contains(text(),'Recent Activity')]")
	public WebElement txtRecentActivity;
	
	@FindBy(xpath="//div[contains(@class,'rror')]/p[contains(@class,'bot')] | //div[contains(@class,'bPODErrorMsg')]/p[contains(text(),'Your account has been suspended')] | //*[contains(text(),'Your account has been suspended. Please contact the Customer Care for your AT&T service:')] | //*[contains(text(),'suspended')]")
	public WebElement txtAccSuspendedMsg;

	@FindBy(xpath="//h2[@class='botMar15 font20 MarTop20' and contains(text(),'No Bills')]")
	public WebElement txtNoBills;
	
	@FindBy(xpath="//h3[contains(text(),'Manage Account')]")
	public WebElement txtManageAccount;
	
	@FindBy(xpath="//h3[contains(text(),'Change Billing Options')] | //h3[contains(text(),'Manage Billing & Payment Options')]|id('ChgBillAddr') | //h2[contains(text(),'Change billing options')]")
	public WebElement txtChangeBillingOptions;
	
	@FindBy(xpath="//h3[@class='font16 bold' and contains(text(),'Get Help')]")
	public WebElement txtGetHelp;
	
	@FindBy(xpath="//form[@id='modifySaveId']//div[2]")
	public WebElement txtCancelPaperlessBilling;
	
	@FindBy(xpath="//span[text()='Card About to Expire']|//span[contains(text(),'Card about to expire')]")
	public WebElement txtCardAboutToExpire;
	
	@FindBy(xpath="//span[contains(text(),'Card about to expire')]//parent::div//a[contains(text(),'AutoPay')] | //span[contains(text(),'expired')]//parent::div//a[contains(text(),'AutoPay')] | //span[contains(text(),'Card has expired')]//parent::div//a[contains(text(),'AutoPay')]")
	public WebElement txtAutoPayCardExpiredAlert;
	
	@FindBy(xpath="//div[@class='alertBoxGrad']//li//span[contains(text(),'Card About to Expire')]//parent::div/p[text()='Update the expiration date on your card ending in 7107']|//div[@class='alertBoxGrad']//li//p[contains(text(),'Update the expiration date on your card ending in')]")
	public WebElement txtCardAboutToExpireDesc;
	
	@FindBy(xpath="//span[contains(text(),'Last Payment Received')]")
	public WebElement txtlastPayemnt;
	
	@FindBy(xpath="//h1[contains(text(),'Wireless Support')]")
	public WebElement txtWirelessSupport;
	
	@FindBy(xpath="//h1[contains(text(),'Data Calculator')]")
	public WebElement txtDataCalculator;
	
	@FindBy(xpath="//div[@class='alertBoxGrad']")
	public WebElement txtBillAlertSection;
	
	@FindBy(xpath="//div[@id='toggleUTV2']//strong[contains(text(),'Total Additions & Changes to Service')]/parent::div/parent::div//div[contains(text(),'$')]")
	public WebElement txtUTVPlanChangesCharge;
	
	@FindBy(xpath="//div[@id='toggleUVI2']//strong[contains(text(),'Total Additions & Changes to Service')]/parent::div/parent::div//div[contains(text(),'$')]")
	public WebElement txtUInternetPlanChangesCharge;
	
	@FindBy(xpath="//div[@id='toggleUVV2']//strong[contains(text(),'Total Additions & Changes to Service')]/parent::div/parent::div//div[contains(text(),'$')]")
	public WebElement txtUVoicePlanChangesCharge;
	
	@FindBy(xpath="//*[text()='Billing & Payments History']")
	public WebElement txtBillAndPaymentHistory;		
	
	@FindBy(xpath="//a[contains(text(),'Payments')]//parent::*//parent::div//*[contains(text(),'$')]")
	public WebElement txtDollarAmountAtSectionTitlePayments;
	
	@FindBy(xpath="//span[contains(text(),'U-verse TV')]//parent::div/p[1]")
	public WebElement txtUverseTVAlertVerbiage;
	
	@FindBy(xpath="//span[contains(text(),'U-verse Internet')]//parent::div/p[1]")
	public WebElement txtUverseInternetAlertVerbiage;
	
	@FindBy(xpath="//span[contains(text(),'U-verse Voice')]//parent::div/p[1]")
	public WebElement txtUverseVoiceAlertVerbiage;
	
	@FindBy(xpath="//div[@id='toggleUTV2']")
	public WebElement txtUverseTVAdditionsAndChangesExpanded;
	
	@FindBy(xpath="//div[@id='toggleUVI2']")
	public WebElement txtUverseInternetAdditionsAndChangesExpanded;
	
	@FindBy(xpath="//*[contains(@id,'PrevActivity')]//contains(text(),'Total Payments')]")
	public WebElement txtTotalPaymentsUnderPreviousActivity;
	
	@FindBy(xpath="//div[@id='toggleUVV2']")
	public WebElement txtUverseVoiceAdditionsAndChangesExpanded;
	
	@FindBy(xpath="//h1[contains(text(),'Connecting to a Public Wi-Fi Hotspot')]")
	public WebElement txtConnectingToWiFiHotspot;
	
	@FindBy(xpath="//div[@id='toggleGraph']//h2[contains(text(),'Mobile Share')]")
	public WebElement txtMobileShare;
	
	@FindBy(xpath="//h2[text()='Audi Post Paid 1 GB']")
	public WebElement txtAudiPostPlan;
	
	@FindBy(xpath="//h2[text()='Contact column header']")
	public WebElement txtContactColumnHeader;
	
	@FindBy(xpath="//td[text()='Infotainment']")
	public WebElement txtInfotainment;
	
	@FindBy(xpath="//h1[contains(text(),'Add a Device')]")
	public WebElement txtManageMyDevice;
	
	@FindBy(xpath="//th[contains(text(),'Bill Total')]")
	public WebElement txtBillTotal;
	
	@FindBy(xpath="//th[contains(text(),'Payments Received')]")
	public WebElement txtPaymentsReceived;
	
	@FindBy(xpath="//th[contains(text(),'Amount')]")
	public WebElement txtAmount;

	@FindBy(xpath="//span[contains(@class,'msg box')]//strong[contains(text(),'Note')]")
	public WebElement txtAggregationNote;
	
	@FindBy(xpath="//th[contains(text(),'Payment Method')]")
	public WebElement txtPaymentMethod;
	
	@FindBy(xpath="//th[contains(text(),'AutoPay')]")
	public WebElement txtAutoPay;
	
	@FindBy(xpath="//th[contains(text(),'Status')]")
	public WebElement txtStatus;
	
	@FindBy(xpath="//th[contains(text(),'Confirmation')]")
	public WebElement txtConfirmation;
	@FindBy(xpath="//span[@class='bold'][contains(text(),'Most Recent Payment')]")
	public WebElement txtMostRecentPayment;
	
	@FindBy(xpath="//span[@class='bold'][contains(text(),'Most Recent Payment')]//parent::div/span[contains(@class,'bold')][contains(text(),'$')]")
	public WebElement txtMostRecentPaymentAmount;
	
	@FindBy(xpath="//p[contains(text(),'Bill Total')]//parent::div/div/span[contains(text(),'$')]")
	public WebElement txtBillTotalInCarousel;
	
	@FindBy(xpath="//span[contains(text(),'Payment Received')]//parent::div//span[contains(text(),'/')]")
	public WebElement txtPaymentReceivedInCarousel;
	
	@FindBy(xpath="//span[contains(text(),'Method:')]//parent::div/parent::div")
	public WebElement txtPaymentMethodInCarousel;
	
	@FindBy(xpath="//span[contains(text(),'AutoPay:')]//parent::div/span[2]")
	public WebElement txtAutoPayValueInCarousel;
	
	@FindBy(xpath="//span[contains(text(),'Payment Status:')]//parent::div/span[2]")
	public WebElement txtPaymentStatusInCarousel;
	
	@FindBy(xpath="//span[contains(text(),'Confirmation:')]//parent::div/span[2]")
	public WebElement txtConfirmationInCarousel;
	
	@FindBy(xpath="//div[contains(@class,'errorMsg')]/p/span")
	public WebElement txtErrorMsgInCarousel;
	
	@FindBy(xpath="//strong[contains(text(),'Billing Period:')]")
	public WebElement txtBillingPeriod;
	
	@FindBy(xpath="//div[contains(text(),'Current Billed Usage')]")
	public WebElement txtCurrentBilledUsage;
	
/*	@FindBy(xpath="//a[@name='Recent Usage']//div[@id='timePeriod']")
	public WebElement txtDefaultUsageBillSelection;*/
	
	@FindBy(xpath="//a[@id='ddShortcut']//div[@id='timePeriod']")
	public WebElement txtDefaultUsageBillSelection;
	
	@FindBy(xpath="//a[@title='Equipment Charges']")
	public WebElement txtEquipmentsChargesSection;
	
	@FindBy(xpath="//div[contains(text(),'Installment Plan ID')]")
	public WebElement txtInstallmentPlanID;
	
	@FindBy(xpath="//div[contains(text(),'Est. on')]")
	public WebElement txtEstablishedOnDate;
	
	@FindBy(xpath="//div[contains(text(),'Equipment Price:')]|//div[contains(text(),'Equipment Charge')]")
	public WebElement txtEquipmentPrice;
	
	@FindBy(xpath="//div[contains(text(),'Down Payment:')]")
	public WebElement txtDownPayment;
	
	@FindBy(xpath="//div[contains(text(),'Amount Financed:')]")
	public WebElement txtAmountFinanced;
	
	@FindBy(xpath="//div[contains(text(),'Balance Remaining after Current Installment:')] | //div[contains(text(),'Balance Remaining:')]")
	public WebElement txtBalanceRemainingAfterInst;
	
	@FindBy(xpath="//div[contains(text(),'Total Equipment Charges')]//parent::div/div[2]")
	public WebElement txtTotalEquipmentCharges;
	
	@FindBy(xpath="//span[contains(text(),'Last Payments Received')]")
	public WebElement txtLastPaymentsReceived;
	
	@FindBy(xpath="//div[@class='alertBoxGrad']//li//span[contains(text(),'Wireless')]")
	public WebElement txtWirelessServiceChangeAlert;
	
	@FindBy(xpath="//*[contains(text(),'Total U-verse Charges')]")
	public WebElement txtTotalUverseCharges;
	
	@FindBy(xpath="//*[contains(text(),'Total Wireless Charges')]")
	public WebElement txtTotalWirelessCharges;
	
	@FindBy(xpath="//*[contains(text(),'Balance') and contains(@class,'bold')]")
	public WebElement txtBalanceInPreviousActivity;
	
	@FindBy(xpath="	//*[contains(text(),'Balance') and contains(@class,'bold')]//parent::div//*[contains(text(),'$')]")
	public WebElement txtBalanceAmountInPrevActivity;
	
	@FindBy(xpath="//div[contains(text(),'Total U-verse TV Charges')]")
	public WebElement txtTotalUVerseTVCharges;
	
	@FindBy(id="UTV-PlanChanges")
	public WebElement txtTotalUVerseTVAdditionChanges;
	
	@FindBy(xpath="//*[contains(text(),'View usage for:')] | //p[contains(text(),'View details for:')]")
	public WebElement txtViewUsageForText;
	
	@FindBy(xpath="//strong[contains(text(),'Mobile Purchases')]")
	public WebElement txtMobilePurchases;
	
	//@FindBy(xpath="//div[@id='usageTableToggleId']")
	@FindBy(xpath="//div[@id='usageIndTableToggleId']")
	public WebElement txtBasicPlanUsageSection;
	
	@FindBy(xpath="//h2[contains(text(),'Usage Details')]")
	public WebElement txtUsageDetailsHeading;
	
	@FindBy(xpath="//a[contains(@id,'UsageDateTime')]")
	public WebElement txtDateAndTime;
	
	@FindBy(xpath="//a[contains(@id,'UsageContact')]")
	public WebElement txtContact;
	
	@FindBy(xpath="//a[contains(@id,'UsageLocation')]")
	public WebElement txtLocation;
	
	@FindBy(xpath="//a[contains(@id,'UsageCallType')]")
	public WebElement txtCallType;
	
	@FindBy(xpath="//a[contains(@id,'UsageMinutes')]")
	public WebElement txtMinutes;
	
	@FindBy(xpath="//a[contains(@id,'UsageCharges')]")
	public WebElement txtCharges;
	
	@FindBy(xpath="//*[contains(text(),'including employer contribution')]")
	public WebElement txtEmployerContributionNote;
	
	@FindBy(xpath="//strong[contains(text(),'Left')]")
	public WebElement txtRecentUsageDetailsCheck;
	
	@FindBy(xpath="//strong[contains(text(),'Manage Account')] | //h3[contains(text(),'Manage Account')]")
	public WebElement txtManageAccountNew;
	
	@FindBy(xpath="//div[contains(@class,'msg box')]/p[contains(text(),'Voice service was not')]")
	public WebElement txtMsgBoxVoiceServiceNotAvailable;
	
	@FindBy(xpath="//h1[contains(text(),'Explanation of services')]")
	public WebElement txtExplanationOfServicesFrameHeading;
	
	@FindBy(xpath="//h3[text()='Manage Billing & Payment Options']")
	public WebElement txtManageBillingAndPaymentOptions;
	
	@FindBy(xpath="//div[@id='RecentUnbilled']")
	public WebElement txtRecentUnbilled;
	
	@FindBy(xpath="//span[contains(text(),'Current Billed Usage')]")
	public WebElement txtCurrentBilled;
	
	@FindBy(xpath="//h1[contains(text(),'PDF')]")
	public WebElement txtPDF;
	
	@FindBy(xpath="//div[@id='usageContent']//h2[contains(text(),'Voice')]")
	public WebElement txtUverseVoicePlan;
	
	@FindBy(xpath="//div[@class='tip-inner'][contains(text(),'Domestic')]")
	public WebElement txtTipDomestic;
	
	@FindBy(xpath="//p[contains(text(),'Domestic Off-net Minutes')]")
	public WebElement txtDomestic;
	
	@FindBy(xpath="//p[contains(text(),'International')]")
	public WebElement txtInternational;
	
	@FindBy(xpath="//h2[text()='Additional Usage']")
	public WebElement txtAdditionalUsage;
	
	@FindBy(xpath="//p[text()='View: ']")
	public WebElement txtView;
	
	@FindBy(xpath="//img[@id='imgTableWhite']")
	public WebElement txtTableView;
	
	@FindBy(xpath="//img[@id='imgTileBlue']")
	public WebElement txtGridView;
	
	@FindBy(xpath="//strong[contains(text(),'Note')] | //p[contains(text(),'Note')] | //div[contains(@class,'msg box')]//p")
	public WebElement txtNote;
	
	@FindBy(xpath="//span[contains(@class,'iesh billing')] | //span[contains(@class,'iesh price')]")
	public WebElement txtTotalAmountDueAmount;
	
	@FindBy(xpath="//h2[contains(text(),'Total Amount Due')]//parent::div//parent::div//span[contains(text(),'$')]")
	public WebElement txtTotalAmountDueUnderAccountDetails;
	
	@FindBy(xpath="//span[@id='usageChart'] | //img[@name='noUsage']")
	public WebElement txtPieChart;
	
	@FindBy(xpath="//p[@class='left botMar0 center']/span")
	public WebElement txtDecimalUsage;
	
	@FindBy(xpath="(//h2[contains(text(),'Trial / prepaid data plan')])[1]")
	public WebElement txtTrialPlan;

    @FindBy(xpath="(id('popupDivContent')/div/div/p)[1]")
    public WebElement txtPlanDescription;
    
    @FindBy(xpath="id('popupDivContent')/div[10]/h2[contains(text(),'OnStar')]")
    public WebElement txtOnstarTitle;
    
	@FindBy(xpath="//a[@id='credit-alert']//parent::div//*[contains(text(),'$')]")
	public WebElement txtDollarAmountAtSectionTitleCreditsAndAdjustments;
  
	@FindBy(xpath="//p[contains(text(),'t available yet')]")
	public WebElement txtBillUnavailable;
	
	@FindBy(xpath="//h2[contains(text(),'About your bill')]")
	public WebElement txtAboutYourBill;

	@FindBy(xpath="//h2[contains(text(),'Get help with your bill')]")
	public WebElement txtGetHelpWithYourBill;
	
	@FindBy(xpath="//dd//a[contains(text(),'Total Account Charges')]")
	public WebElement txtTotalAccountCharges;
	
	@FindBy(xpath="//*[contains(text(),'Past due from previous activity')]")
	public WebElement txtPastDueFromPreviousActivity;
	
	@FindBy(xpath="//*[contains(text(),'Past due from previous activity')]//parent::div//*[contains(@class,'orange')]")
	public WebElement txtOrangeColorDollarAmountInPreviousActivity;
	
	@FindBy(xpath="(//div[@class='page-title ie7botMar0']/h1[contains(text(),'Turn Usage On or Off')]")
	public WebElement txtTurnUsageOnorOff;
	
	@FindBy(xpath="//div[@class='bPOD-bill-title PadBot3imp MarTop10']//*[contains(text(),'Wireless')]")
	public WebElement txtWirelessSection;
	
	@FindBy(xpath="//div[@id='md']//h1[contains(text(),'Your Personalized Bill Tour is Ready')]")
	public WebElement txtBillTour;
	
	@FindBy(xpath="//span[contains(text(),'Estimated Charges')]")
	public WebElement txtEstimatedCharges;
	
	@FindBy(xpath="//p[contains(text(),'Bill in Accessible Format')]")
	public WebElement txtBillAccessibleFormat;
	
	@FindBy(xpath="//p[contains(text(),'t available')]")
	public WebElement txtBillIsntUnavailable;
	
	@FindBy(xpath="//*[@id='planName' and contains(text(),'Basic Plan Usage')]")
	public WebElement txtValidUsagePageCheck;
	
	@FindBy(xpath="//strong[contains(text(),'Installment Plan Details')]")
	public WebElement txtInstallmentPlanDetails;
	
	@FindBy(xpath="//h2[contains(text(),'AT&T Next')]/parent::div/p")
	public WebElement txtATTNextPlanDescription;
	
	@FindBy(xpath="//h3[contains(text(),'Home Service Bill Details')]")
	public WebElement txtWirelineBillDetails;
	
	@FindBy(xpath="//div[contains(@class,'alertBox')]//*[contains(text(),'re receiving a discount of $')]")
	public WebElement txtAlertDiscount;
	
	@FindBy(xpath="//div[contains(@class,'alertBox')]//span[contains(text(),'discount')]")
	public WebElement txtAlertDiscountHeading;
	
	@FindBy(xpath="(//div[@id='popupDivContent']//h2)[1]")
	public WebElement txtViewPlanSummaryPlanName;
	
	@FindBy(xpath="//div[@id='popupDivContent']//*[contains(text(),'Trial / prepaid data plan')]")
	public WebElement txtViewPlanSummaryTrailPlan;
	
	@FindBy(xpath="//img[@alt='attlogo']//parent::div//parent::div/p[1]")
	public WebElement txtAccNumInPrintFriendlyView;
	
	@FindBy(xpath="//img[@alt='attlogo']//parent::div//parent::div/p[2]")
	public WebElement txtCustNameInPrintFriendlyView;
	
	@FindBy(xpath="//div[contains(@class,'accSummary')]//div[contains(text(),'Monthly Charges for')]")
	public WebElement txtMontlyServicePeriodInPrintFriendlyView;
	
	//@FindBy(xpath="//span[@id='tipAlert' and contains(text(),'% of your data allowance')]")
	@FindBy(xpath="//*[@class='tip-inner' and contains(text(),'% of your data allowance')]")
	public WebElement txtAlertPopUp;
	
	//@FindBy(xpath="//span[@id='groupdataheadertip' and contains(text(),'% of your data allowance')]")
	@FindBy(xpath="//*[@class='tip-inner' and contains(text(),'% of your data allowance')]")
	public WebElement txtAlertPopUpUnderWebHeader;
	
	@FindBy(xpath="//p[contains(text(),'No')]")
	public WebElement txtNoIntallments;

	@FindBy(xpath="(//div[contains(text(),'Installment Plan ID')])[2]")
	public WebElement txtInstallmentPlanID1;
	
	@FindBy(xpath="//div[@id='timePeriod']")
	public WebElement txtShowListDefaultSelectedUsage;
	
	@FindBy(xpath="//div[contains(text(),'Employer Data')]")
	public WebElement txtEmployerData;
	
	@FindBy(xpath="//div[contains(text(),'Video Details')]")
	public WebElement txtVideoDetails;
	
	@FindBy(xpath="//h1[contains(text(),'Usage')]")
	public WebElement txtUsageHeader;


	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//div[contains(@class,'alertBox')]//a[contains(text(),'View discount')] | //div[contains(@class,'alertBox')]//a[contains(text(),'View Discount')]")
	public WebElement lnkViewDiscountCTA;
	
	@FindBy(id="viewInstallment")
	public WebElement lnkManageInstallmentPlan;
	
	@FindBy(xpath="//a[contains(text(),'Payments') and contains(@name,'toggle')]")
	public WebElement lnkPaymentsWithExpandCollapseFunctionality;	
	
	@FindBy(xpath="//a[contains(text(),'Manage AutoPay settings')]|//a[contains(text(),'Edit AutoPay information')]")
	public WebElement lnkManageAutopaySettings;
	
	@FindBy(xpath="//a[@linkname='UsageTab']")
	public WebElement lnkUsage;
	
	@FindBy(xpath="//li[@class='CTNTab selected']//a | //*[contains(@class,'current') and @linkname='UsageTab']")
	public WebElement tabUsagePreselected;
	
	@FindBy(id="PaymentHistory")
	public WebElement lnkHistoryTab;
	
	@FindBy(xpath="//a[contains(@linkname,'View and Pay Your Bill')]")
	public WebElement lnkViewAndPayYourBill;
	
	//@FindBy(id="ChgBillAddr")
	@FindBy(xpath="//a[text()='Change billing address']")
	public WebElement lnkChangeBillingAddress;
	
	@FindBy(xpath="//a[contains(text(),'Mobile Share 10GB')]")
	public WebElement lnkMobileShare;
	
	@FindBy(xpath="//a[contains(text(),'Manage payment options')]")
	public WebElement lnkManagePaymentOptions;

	@FindBy(xpath="//a[@title='print'] | //a[@name='print'] | //a[@alt='Print']")
	public WebElement lnkPrint;
	
	@FindBy(xpath="//a[@title='print'] | //a[@name='print'] | //a[@alt='Print']")
	public WebElement lnkPrint1;
	
	@FindBy(xpath="//a[@linkname='ReportsTab']")
	public WebElement lnkReportTab;
	
	@FindBy(xpath="//div[@class='PadLeft20 btnimg']//a[@title='One Time Charges']")
	public WebElement lnkOneTimeCharges;
	//inside the change billing address frame
	@FindBy(xpath="//a[@title='Cancel']")
	public WebElement lnkCancel;
	//End
	
	//inside the change Enrollment setting Link
		@FindBy(xpath="//a[@class='textLink wt_Body']")
		public WebElement lnkCancelinEnrollmentSetting;
		//End
		//inside the change to paper billing frame
				@FindBy(xpath="//a[@class='closeModal alignIE']")
				public WebElement lnkClosePopupInContinuePaperBill;
				//End
				
		
	@FindBy(xpath="//a[@name='row1'][@linkname='View Paper Bill']")
	public WebElement lnkPaperBill;
	
	@FindBy(xpath="//a[@name='row1'][@linkname='View Online Bill']")
	public WebElement lnkOnlineBill;
	
	@FindBy(xpath="//span[contains(text(),'Enroll in paperless billing')]")
	public WebElement lnkEnrollPaperlessBilling;
	
	@FindBy(id="uverseTV")
	public WebElement lnkUverseTV;
	
	@FindBy(id="UTV-surcharge-fees")
	public WebElement lnkUverseTVSurchargeAndFees;
	
	@FindBy(id="UTV-governmenttax")
	public WebElement lnkUverseTVGovernmentFees;
	
	@FindBy(id="talk-usage")
	public WebElement lnkUverseVoice;
	
	@FindBy(id="UVV-surcharge-fees")
	public WebElement lnkUverseVoiceSurchargeAndFees;
	
	@FindBy(id="UVV-governmenttax")
	public WebElement lnkUverseVoiceGovernmentFees;
	
	@FindBy(xpath="//a[@linkname='ViewUsageDropDown_00']")
	public WebElement lnkRecentBill;
	
	@FindBy(xpath="//a[@linkname='PlanDropDown_View usage for_Bob Brigham']")
	public WebElement lnkOnstarCTN;
	
	@FindBy(xpath="//div[@id='selectUsageLine']//a//p")
	public WebElement lnkUsageFor;
	
	@FindBy(xpath="//a[@class='wt_LinkFarm_2' and contains(text(),'Manage AutoPay')]")
	public WebElement lnkManageAutoPay;

	@FindBy(xpath="//a[contains(@href,'billOverview')] | //span[@class='first1' and contains(text(),'Bill')]")
	public WebElement lnkBillTab;
	
	@FindBy(xpath="//li[contains(@class,'selected')]/a/span[@class='first1']")
	public WebElement lnkBillTabPreSelected;
	
	@FindBy(xpath="//a[contains(text(),'Update Payment Profile')]")
	public WebElement lnkUpdatePaymentProfile;
	
	@FindBy(id="View_all_payments")
	public WebElement lnkViewAllPaymentsCarousel;
	
	@FindBy(id="uverseAcct")
	public WebElement lnkAccountCharges;
	
	@FindBy(xpath="//a[text()='Enroll in AutoPay']")
	public WebElement lnkEnrollInAutopay;
	
	@FindBy(xpath="//a[contains(text(),'Billing & Payments')]")
	public WebElement lnkBillingAndPaymentSecNav;
	
	@FindBy(xpath="//a[@class='navTertiaryLink'][contains(text(),'Manage AutoPay')]")
	public WebElement lnkManageAutopayTertNav;
	
	@FindBy(id="planId")
	public WebElement lnkViewPlanSummary;
	
	@FindBy(id="popupDivContent")
	public WebElement lnkViewPlanSummaryDiv;
	
	@FindBy(xpath="//a[@title='Monthly Plan Charges for']")
	public WebElement lnkMonthlyCharges;
	
	@FindBy(xpath="//a[contains(text(),'Move my service')]")
	public WebElement lnkMoveMyService;
	
	@FindBy(xpath="//a[contains(text(),'Troubleshoot my service')] | //a[contains(text(),'Troubleshoot & Resolve tool')]")
	public WebElement lnkTroubleshootMyService;
	
	@FindBy(xpath="//a[contains(text(),'Visit Member Center')]")
	public WebElement lnkVisitMemberCenter;
	
	@FindBy(xpath="//a[contains(text(),'Set up Parental Controls for Internet')]")
	public WebElement lnkSetUpParentalControls;
	
	@FindBy(xpath="//a[text()='See more payment options']")
	public WebElement lnkSeeMorePaymentOptions;
	
	@FindBy(xpath="//a[text()='Update billing information']")
	public WebElement lnkUpdateBillingInfo;
	
	@FindBy(xpath="//a[text()='Transfer wireless billing responsibility']")
	public WebElement lnkTransferWirelessBillingResponsibility;
	
	@FindBy(xpath="//a[text()='Home phone support']")
	public WebElement lnkHomePhoneSupport;
	
	@FindBy(xpath="//a[text()='High Speed Internet (DSL) billing support']")
	public WebElement lnkHighSpeedInternetBillingSupport;
	
	@FindBy(xpath="//a[text()='Go to billing support']")
	public WebElement lnkGoToBillingSupport;
	
	@FindBy(xpath="//*[@id='viewUsageBox']//a[contains(text(),'Mobile Share')]")
	public WebElement lnkMobileSharePlanFromViewUsageForDropdown;
	
	@FindBy(xpath="//a[text()='Billing, Usage, Payments']")
	public WebElement lnkBillingUsagePaymentsSecNav;
	
	@FindBy(xpath="//a[@class='wt_Body'][contains(text(),'Change enrollment setting')]")
	public WebElement lnkBillingUsagePaymentsChangeEnrollment;
	
	@FindBy(id="WRRolloverInfoLink")
	public WebElement lnkRolloverdetails;
	
	@FindBy(xpath="//div[@class='alertBoxGrad']//li//span[contains(text(),'Card About to Expire')]//parent::div//a | //div[@class='alertBoxGrad']//li//a[contains(text(),'AutoPay')]")
	public WebElement lnkUpdatePaymentProfileCardAboutExpire;
	
	@FindBy(xpath="//a[@id='Collapse']")
	public WebElement lnkCollapseDummy;
	
	@FindBy(xpath="//a[@id='View Usage']")
	public WebElement lnkViewUsageDummy;
	
	@FindBy(xpath="//a[@title='print']")
	public WebElement lnkPrintPg;
	
	@FindBy(xpath="//a[@title='Print']")
	public WebElement lnkPrintBill;
	
	@FindBy(xpath="//a[text()='View Explaination of Charges'")
	public WebElement lnkViewExplainationCharges;
	
	@FindBy(xpath="//a[text()='View news you can use & promos'")
	public WebElement lnkViewNewsPromos;
	
	@FindBy(xpath="//*[@id='ajaxDiv1']//a[contains(text(),'Change rate plan')]")
	public WebElement lnkChangeRatePlan;
	
	@FindBy(xpath="//*[@id='popupDivContent']//a[contains(text(),'Change rate plan')]")
	public WebElement lnkChangeRatePlan1;
	
	@FindBy(xpath="//a[contains(text(),'View My Plans & Features')]")
	public WebElement lnkViewMyPlansAndFeatures;
	
	@FindBy(xpath="//a[contains(text(),'Upgrade device')]")
	public WebElement lnkUpgradeDevice;
	
	@FindBy(xpath="//a[contains(text(),'Set parental controls')]")
	public WebElement lnkSetParentalControl;
	
	@FindBy(xpath="//a[contains(text(),'Manage paperless billing')]")
	public WebElement lnkManagePaperlessBilling;
	
	@FindBy(xpath="//a/span[contains(text(),'Enroll in paperless billing')]")
	public WebElement lnkEnrollinPaperlessBilling;
	
	
	@FindBy(xpath="//a[contains(text(),'Learn how to manage usage')]")
	public WebElement lnkHowToManageUsage;
	
	@FindBy(xpath="//a[contains(text(),'Estimate monthly usage')]")
	public WebElement lnkEstimateMonthlyUsage;
		
	@FindBy(xpath="//a[contains(text(),'Manage your usage')]")
	public WebElement lnkManageYourUsage;
	
	@FindBy(xpath="//span[contains(text(),'U-verse TV')]//parent::div/p[2]/a")
	public WebElement lnkUverseTVAlertViewChanges;
	
	@FindBy(xpath="//span[contains(text(),'U-verse Internet')]//parent::div/p[2]/a")
	public WebElement lnkUverseInternetAlertViewChanges;
	
	@FindBy(xpath="//span[contains(text(),'U-verse Voice')]//parent::div/p[2]/a")
	public WebElement lnkUverseVoiceAlertViewChanges;
	
	@FindBy(xpath="//a[contains(text(),'Add a device')]")
	public WebElement lnkAddADevice;
	
	@FindBy(xpath="//a[contains(text(),'Connect & save with Wi-fi')]")
	public WebElement lnkConnectAndSaveWithWiFi;
	
	@FindBy(xpath="//a[text()='Ways to manage your wireless usage']")
	public WebElement lnkWaysManageWirelessUsage;
	
	@FindBy(xpath="//a[text()='Learn how to view your wireless usage']")
	public WebElement lnkLearnToViewWirelessUsage;
	
	@FindBy(xpath="//button[contains(text(),'Close')]")
	public WebElement lnkCloseOnConnectAndSaveWithWiFi;
	
	@FindBy(xpath="//*[@id='ajaxDiv1']//a[contains(text(),'Manage my device')]")
	public WebElement lnkManageMyDeviceAndFeatures;
	
	@FindBy(xpath="//*[@id='popupDivContent']//a[contains(text(),'Manage my device')]")
	public WebElement lnkManageMyDeviceAndFeatures1;
	
	@FindBy(id="view_or_print_as_table")
	public WebElement lnkBillPrint;
	
	@FindBy(xpath="//a[@linkname='ViewUsageDropDown_02']")
	public WebElement lnkPreviousBill;
	
	@FindBy(xpath="//div[contains(text(),'Apr 27, 2015 - Apr 28, 2015')]")
	public WebElement lnkPreviousBill3;
	
	
	@FindBy(id="View_Usage_Details")
	public WebElement lnkViewUsageDetails;
	
	@FindBy(xpath="//a[@title='Cancel']")
	public WebElement lnkCancelOnChangeBillingAddress;
	
	@FindBy(xpath="//div[@class='sub-group-title PadTop10 MarLeft10 botDotBorder Padbot5 ']//a[contains(text(),'OnStar')]")
	public WebElement lnkCTNOnStar;
	
	@FindBy(xpath="//div[@class='sub-group-title PadTop10 MarLeft10 botDotBorder Padbot5 ']//a[contains(text(),'OSDDATA DONOTUSE 972-415-1465')]")
	public WebElement lnkNonOnStarCTN;
	
	@FindBy(xpath="(//a[contains(@id,'Talk_Usage_Details')])[1] | //a[contains(@id,'ViewUsageDetails1')]")
	public WebElement lnkViewUsageDetails1;
	
	@FindBy(id="(//a[contains(@id,'Talk_Usage_Details')])[2] | //a[contains(@id,'ViewUsageDetails2')]")
	public WebElement lnkViewUsageDetails2;
	
	@FindBy(xpath="//div[@id='RecentUnbilled']//a")
	public WebElement lnkRecentUsageFromDropDown;
	
	@FindBy(xpath="//a[contains(text(),'Close')]")
	public WebElement lnkCloseForPopUpInATTSupportPage;
	
	@FindBy(xpath="//td[@headers='Payment_Received']")
	public WebElement lstPaymentsReceivedDates;
	
	@FindBy(xpath="//a[contains(text(),'Question a charge')]")
	public WebElement lnkQuestion;
	
	@FindBy(xpath="//a[contains(text(),'How to update a payment method')]")
	public WebElement lnkUpdatePaymentMethod;
	
	@FindBy(xpath="//a[contains(text(),'Wireless billing support')]")
	public WebElement lnkWirelessSupport;
	
	@FindBy(xpath="//a[contains(text(),'DIRECTV support')]")
	public WebElement lnkDIRECTV;
	
	@FindBy(xpath="//a[contains(text(),'Play video on view usage details')] | //a[contains(text(),'Play video on flexible payments')]")
	public WebElement lnkPlayVideoOnViewUsageDetails;
	
	@FindBy(xpath="//a[text()='Make payment arrangements']")
	public WebElement lnkMakePaymentArrangements;
	
	@FindBy(xpath="//a[text()='See ways to pay your bill']")
	public WebElement lnkSeewaysToPayYourBill;
		
	@FindBy(id="seeMoreAlert")
	public WebElement lnkSeeMoreAlerts;
	
	@FindBy(xpath="//span[contains(text(),'Wireless')]//parent::div/p[2]/a")
	public WebElement lnkWirelessServiceAlertViewChanges;
	
	@FindBy(id="uverseInt")
	public WebElement lnkUverseInternetSectionExpand;
	
	@FindBy(id="talk-usage")
	public WebElement lnkUverseVoiceSectionExpand;
	
	@FindBy(xpath="//*[contains(text(),'Wireless')]/parent::*/following-sibling::div//a[contains(@id,'monthly-alert')]")
	public WebElement lnkWirelessMonthlyPlanCharges;
	
	@FindBy(xpath="//*[contains(text(),'Wireless')]/parent::*/following-sibling::div//a[contains(@title,'Additions Changes')]")
	public WebElement lnkWirelessAdditionsAndChangesToService;
	
	@FindBy(xpath="//*[contains(text(),'Wireless')]/parent::*/following-sibling::div//a[contains(@title,'Surcharges And Fees')]")
	public WebElement lnkWirelessSurchargeAndFees;
	
	@FindBy(xpath="//*[contains(text(),'Wireless')]/parent::*/following-sibling::div//a[contains(@title,'Government Fees & Taxes')]")
	public WebElement lnkWirelessTotalGovernmentFees;
	
	@FindBy(xpath="//div[@id='RecentUnbilled']//a | //a[@linkname='ViewUsageDropDown_00'] | //div[contains(text(),'Recent Usage')]")
	public WebElement lnkRecentUsageDropdownOption;
	
	@FindBy(xpath="//a[@name='CSV']")
	public WebElement lnkCSV;

	@FindBy(xpath="//a[@name='excel']")
	public WebElement lnkExcel;
	
	@FindBy(id="viewExplanationOfServices")
	public WebElement lnkViewExplanationOfServices;

	@FindBy(xpath="//a[@href='/olam/handOffToURock.myworld?reportActionEvent=A_SVC_CHANGE_PLAN_LINK_CLICKED&newInFocusUverseBAN=192103938&newInFocusMemberID=qayls21_192103938@att.net&uRockHandoffReturn=Billing']")
	public WebElement lnkChangePlan;
	
	@FindBy(xpath="//a[contains(text(),'Move service')]")
	public WebElement lnkMoveService;
	
	@FindBy(xpath="//a[contains(text(),'Learn how to return equipment')]")
	public WebElement lnkHowToReturnEquipment;
	
	@FindBy(xpath="//a[contains(text(),'Set up parental controls for Internet')]")
	public WebElement lnkSetUpParentalControl;
	
	@FindBy(id="sharedDataOverageCharges")
	public WebElement lnkMobileShareOverageChargesInReportType;
	
	@FindBy(xpath="//a[contains(text(),'Paperless Billing')]")
	public WebElement lnkPaperlessBillingTertNAv;
	
	@FindBy(xpath="//a[text()='Total Account Charges']")
	public WebElement lnkTotalAccountCharges;
	
	@FindBy(xpath="//div[@id='selectUsageLineBox']//a[contains(text(),'AT&T U-verse Voice')]")
	public WebElement lnkUverseVoiceUsage;
	
	@FindBy(xpath="//li[@id='alert1']//a[text()='Reactivate service']")
	public WebElement lnkReactivateService;
	
	@FindBy(xpath="//a[@id='viewExplanationOfServices']")
	public WebElement lnkExplanationOfServices;
	
	@FindBy(xpath="//a[contains(text(),'View credits')]")
	public WebElement lnkViewcredits;
	
	@FindBy(xpath="//a[@id='credit-alert' and contains(@class,'toggle')]")
	public WebElement lnkCreditsAdjustments;
	
	@FindBy(xpath="//a[text()='Play video on flexible payments']")
	public WebElement lnkPlayVideo;
	
	@FindBy(xpath="//a[contains(text(),'View paper bill')]")
	public WebElement lnkViewPaperBill;

	@FindBy(xpath="//a[@name='usage line link']")
	public WebElement lnkViewUsageFor;
	
	@FindBy(xpath="//a[text()='AT&T U-verse Voice']")
	public WebElement lnkATTuverseVoice;
	
	@FindBy(id="WRViewUsagePlanDD")
	public WebElement lnkViewUsageFor1;
	
	@FindBy(xpath="//a[@name='viewUsage']")
	public WebElement lnkViewFor;
	
	@FindBy(xpath="//a[@linkname='ViewUsageDropDown_03'][@title='Apr 29, 2015 - May 28, 2015']")
	public WebElement lnkPeriod;
	
	@FindBy(xpath="//div[@id='additional']//a[text()='Change plan']")
	public WebElement lnkChangePlan1;
	
	@FindBy(xpath="//a[@linkname=' View bill info, news and promos']")
	public WebElement lnkViewBillInfoNewsPromos;
	
	@FindBy(id="billInfo")
	public WebElement lnkViewBillInfoNewsPromos1;
	
	@FindBy(xpath="//dd/a[text()='Link your account to Plenti']")
	public WebElement lnkLinkyourAccountToPlenti;
	
	@FindBy(xpath="//a[text()='Change wireless plan']")
	public WebElement lnkChangeWirelessPlan;
	
	@FindBy(xpath="//a[text()='Manage my wireless device & features']")
	public WebElement lnkManageWirelessDevicesFeatures;
	
	@FindBy(xpath="//a[text()='View or change my DIRECTV plan']")
	public WebElement lnkViewDIRECTVPlan;

	@FindBy(xpath="//a[text()='U-verse® voice charges']")
	public WebElement lnkUverseVoiceCharges;
	
	@FindBy(xpath="//img[@title='Call for help, landline number']")
	public WebElement lnkLandline;
	
	@FindBy(xpath="//a[@linkname='View Paper Bill']//span[contains(text(),'View Paper Bill')]")
	public WebElement lnkViewPaperBillBottom;
	
	@FindBy(xpath="//a[contains(text(),'View important information')]")
	public WebElement lnkViewImpInfoUsageDelays;
		
	@FindBy(xpath="//*[@id='colorbox']/a/img")
	public WebElement lnkCloseOnChangeBillingAddress;
	
	@FindBy(xpath=".//*[@id='usageTableToggleId']//tr[1]/td[2]/div/div[3]/a")
	public WebElement lnkSwitchTurnOn;
	
	@FindBy(xpath="//div[contains(text(),'Nights and Weekend Minutes')]//img[@alt='help']")
	public WebElement lnkTooltipNightsAndWeekendMin;
	
	@FindBy(xpath="//div[contains(text(),'Mobile to Mobile Minutes')]//img[@alt='help']")
	public WebElement lnkTooltipMobileToMobileMin;
	
	@FindBy(xpath="//a[@id='TourMyBill']")
	public WebElement lnkTourMyBill;
	
	@FindBy(xpath="//div[@id='md']//a")
	public WebElement lnkClosemodal;
	
	@FindBy(xpath="//a[contains(text(),'log in with your Wireline ID.')]")
	public WebElement lnkLoginWithYourWirelineID;
	
	@FindBy(xpath="//a[contains(text(),'Learn about account ownership')]")
	public WebElement lnkLearnAboutAccountOwnership;
	
	@FindBy(xpath="//a[contains(text(),'Learn about AutoPay')]")
	public WebElement lnkLearnAboutAutopay;
	
	@FindBy(xpath="//a[contains(text(),'Learn about paperless billing')]")
	public WebElement lnkLearnAboutPaperlessBilling;
	
	@FindBy(xpath="//a[contains(text(),'Understanding Your AT&T U-verse Bill')]")
	public WebElement lnkUnderstandingBill;
	
	@FindBy(xpath="//a[contains(text(),'View interactive sample bill')]")
	public WebElement lnkViewInteractiveBill;

	@FindBy(xpath="//a[contains(text(),'Learn About Your First U-verse Bill')]")
	public WebElement lnkLearnAboutBill;
	
	@FindBy(id="printReport")
	public WebElement lnkPrintReport;
	
	@FindBy(xpath="//a[contains(text(),'AT&T Next')]")
	public WebElement lnkATTNextPlan;
	
	@FindBy(xpath="//a[contains(text(),'Learn how to use my device')]")
	public WebElement lnkLearnHowToUseMyDevice;
	
	@FindBy(xpath="//div[@id='toggleGraph']//a[contains(text(),'Change plan')]")
	public WebElement lnkChangePlanCTAUnderChart;
	
	@FindBy(xpath="//span[contains(text(),'Current bill')]")
	public WebElement lnkCurrentBill;
	
		
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	
	//@FindBy(xpath="//a[1][@id='makePayment']")
	@FindBy(xpath="//a[@id='makePayment'] | (//a[@id='makePayment'])[1]")     //Updated by Deepak on 09-17 for 1511
	public WebElement btnMakePaymentInBillingPage;

	@FindBy(xpath="//a[contains(@linkname,'View Paper Bill')]")
	public WebElement btnViewPaperBill;
	
	@FindBy(xpath="//h4[@class='sub-group-title PadTop7 Padbot2 PadLeft10 botDotBorder PadBot0ie']/a[@href='#toggleEC252-655-1217']")
	public WebElement btnEquipmentCharges;
	
	@FindBy(xpath="//a[contains(text(),'View All Usage')]")
	public WebElement btnViewAllUsage;
	
	@FindBy(xpath="//div[@class='float-right MarTop10 ie7MarTop0']//img[@alt='paper bill not yet available']")
	public WebElement btnPaperBillNotAvailable;
	
	@FindBy(xpath="//a[@id='uverseInt']//img[@src='//www.att.com/images/global/iconExpand.png']")
	public WebElement btnUverseInt;
	
	@FindBy(xpath="//a[@id='talk-usage']//img[@src='//www.att.com/images/global/iconExpand.png']")
	public WebElement btnUverseVoice;
	
	@FindBy(xpath="//span[@class='first1' and contains(text(),'Reports')]")
	public WebElement btnReportsTab;
	
	@FindBy(xpath="//div[@id='uniform-paper']//input[@id='paper']")
	public WebElement btnCancelPaperlessBilling;
	
	@FindBy(xpath="//a[contains(text(),'Create Report')]")
	public WebElement btnCreateReport;
	
	@FindBy(xpath="//div[@id='createReportButton']/a")
	public WebElement btnCreateReportDisabled;
	
	@FindBy(xpath="//span[@class='first1' and contains(text(),'Bill')]")
	public WebElement btnBillTab;
	
	@FindBy(xpath="//button[contains(text(),'Close')]")
	public WebElement btnCloseOnViewWirelessUsageVideoWindow;
	
	@FindBy(xpath="//a[@name='Recent Usage']")
	public WebElement btnUsageSelectionDropdown;
	
	@FindBy(xpath="//a[@name='Change Billing Period']")
	public WebElement btnChangeBillingPeriodDropdown;
	
	@FindBy(xpath="//a[@name='View Usage for']")
	public WebElement btnViewUsageFor;
	
	@FindBy(xpath="//a[@id='printUsageBill']")
	public WebElement btnPrintUsageBill;
	
	@FindBy(xpath="//a[@name='Download']")
	public WebElement btnDownload;
	
	@FindBy(xpath="//a[@href='#Close']/img")
	public WebElement btnCloseFrame;
	
	@FindBy(xpath="//div[@class='PadLeft10 float-left rel top-2px']/a")
	public WebElement btnPaperBill;
	
	@FindBy(xpath="//input[@id='dataConsumptionSelection']")
	public WebElement btnTurnOn;
	
	@FindBy(xpath="//img[@src='/olam/English/brand30/bt/btnSubmitDisabled.png']")
	public WebElement btnSubmitDisabled;
	
	@FindBy(xpath="//img[@src='/olam/English/brand30/bt/btnSubmit.png']")
	public WebElement btnSubmit;
	
	
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	@FindBy(xpath="//iframe[@name='ChgBillAddr']")
	public WebElement frmChangeBillingAddress;
	
	@FindBy(xpath="//iframe[@name='view_or_print_as_table']")
	public WebElement frmPrint;
	
	@FindBy(xpath="//iframe[contains(@name,'View_Usage_Details')] | //iframe[contains(@class,'cboxIframe')]")
	public WebElement frmViewUsageDetails;

	//@FindBy(xpath="//*[contains(@src,'VIEW_BILL_PRINTABLE_TABLE')]")
	@FindBy(xpath="//iframe[@class='cboxIframe']")
	public WebElement frmNewPaymentDetails;
	
	@FindBy(name="view_or_print_as_table")
	public WebElement frmPrintFriendlyTable;
	
	@FindBy(xpath="//iframe[contains(@src,'BillUsageDetail')]")
	public WebElement frmBillUsageDetail;
	//Frame Obj
	@FindBy(xpath="//h2[contains(text(),'Details')]")
	public WebElement frmTitle;
	
	@FindBy(xpath="//iframe[@src='/olam/additionalInfo.myworld?mType=prodInfo']")
	public WebElement frmViewExplanationOfServices;
	
	//Frm (View bill details usage) obj 
	@FindBy(xpath="//a[@name='View Usage for']//div[@id='WirelessBilledViewUsageForDD']")
	public WebElement frmViewUsageDD;
	
	@FindBy(xpath="//a[text()='Talk']")
	public WebElement frmTalkLink;
	
	@FindBy(xpath="//div[contains(@class,'TopDotBorder BottomDotBorder')]")
	public WebElement frmUsageTable;

	@FindBy(xpath="//a[@id='WirelessUsageDateTime']")
	public WebElement frmWirelessUsageDateTime;
	
	@FindBy(id="NicknameANumberLink")
	public WebElement frmLnkNickname;
	
	@FindBy(xpath="//a[text()='Manage contacts']")
	public WebElement frmLnkManageContacts;
	
	@FindBy(id="uniform-search_by")
	public WebElement frmDrpSearchBy;
	
	@FindBy(id="search_by_button")
	public WebElement frmDrpSearchByButton;
	
	@FindBy(id="printUsageBill")
	public WebElement frmLnkPrint;
	
	@FindBy(xpath="//p[text()='Download']")
	public WebElement frmDrpDownload;
	
	@FindBy(id="addNicknamePopup")
	public WebElement frmAddNicknamePopup;
	
	@FindBy(xpath="//div[@id='addNicknamePopup']//img[@src='/olam/English/brand30/btn-cancel.png']")
	public WebElement frmCancel;
	
	@FindBy(id="addNumber")
	public WebElement frmEdtAddNo;
	
	@FindBy(id="addName")
	public WebElement frmEdtAddName;
	
	@FindBy(xpath="//img[@src='/olam/English/brand30/btn-save-nickname.png']")
	public WebElement frmSaveNickname;
	
	@FindBy(xpath="//iframe[contains(@src,'A_PMT_QUICK_PAY_SETUP&selectedAccount')]")
	public WebElement frmQuickPayment;
	//frm obj
	@FindBy(id="eneteredAmt")
	public WebElement edtEnteredAmt;
	
	@FindBy(id="idBtnAnchor")
	public WebElement btnNext;
	
	@FindBy(xpath="//a[text()='Close']")
	public WebElement lnkClose;
	
	@FindBy(xpath="//iframe[contains(@src,'additionalInfo')]")
	public WebElement frmExplanationOfCharges;
	
	@FindBy(xpath="//h1[contains(text(),'Explanation')]")
	public WebElement txtExplanationOfCharges;
	
	@FindBy(xpath="//div[@id='cboxLoadedContent']//iframe[1]")
	public WebElement frmBillUsageDetail1;
	
	@FindBy(xpath="//iframe[@src='/olam/urockErrormodal.myworld']")
	public WebElement frmUrockChangePlan;
	
	@FindBy(xpath="//iframe[@title='TourMyBill']")
	public WebElement frmTourMyBill;
	
	@FindBy(xpath="//button[contains(@aria-label,'Recent use')]")
	public WebElement btnRecentUse;	
	
	@FindBy(xpath="//span[contains(text(),'Usage options')]")
	public WebElement txtUsageOptions;
	/*---------------------------
	 * Table objects add below
	 *---------------------------*/
	@FindBy(xpath="//div[@class='box botMar0']")
	public WebElement tblBillingHistory;
	
	@FindBy(xpath="//table//caption[contains(text(), 'Basic Plan Usage')]")
	public WebElement tblBasicUsagePlan;
	
	@FindBy(xpath="//div[contains(@id,'carousel')]")
	public WebElement tblCarouselView;
	
	@FindBy(xpath="//div[@class='box']")
	public WebElement tblBasicUsagePlan2;
	
	@FindBy(xpath="//div[@id='additional']")
	public WebElement tblSecLinkRailsSection;
	
	@FindBy(xpath="//div[@id='chartContainer']")
	public WebElement tblPieChartSection;
	
	@FindBy(xpath="//div[@id='myDiv']")
	public WebElement tblBasicPlanData;
	
	
/********************** Objects added by onshore - starte here **************************/
	
	@FindBy(xpath="p[contains(text(), 'Total Amount Due by')]")                       // ------ Added by Deepak 0n 05-Aug-15 -------//
	public WebElement txtTotalAmountDueBy;
	
	@FindBy(xpath="//h1[contains(text(),'Billing & Usage')]//parent::div//p")  //Added by Clint on 27th Nov 2015
	public WebElement txtUserDetails;
	
	@FindBy(xpath="//a[@Linkname='BillTab')]")					 // --- Added by Deepak 0n 05-Aug-15 ----//
	public WebElement lnkBillTab_1511;
	
//	@FindBy(xpath="//div[@id='timePeriod']") 				// --- Object added by Rahul Bakde - 8/3/2015 ---//
	@FindBy(xpath="//a[@id='ddShortcut']//div[@id='timePeriod']") 
	public WebElement lstBillCycleDropDown;
	
	@FindBy(xpath="((//div[contains(@id,'S|A') or contains(@id,'ddShortcut')]//span[contains(text(),'-')]/parent::a)[1] | (//div[contains(@id,'S|A') or contains(@id,'ddShortcut')]//span[contains(text(),'-')])[1])[1]") 
	public WebElement lnkBillCycleSelection;

	@FindBy(xpath="//p[contains(text(),'Billing period')] | //strong[contains(text(),'Billing Period')] | //p[contains(text(),'Billing Period')] | //strong[contains(text(),'Billing period')]")			// --- Object added by Rahul Bakde - 8/3/2015 ---//
	public WebElement txtBillPeriod;

	@FindBy(xpath="//img[@alt='View Paper Bill']")			// --- Object added by Rahul Bakde - 8/3/2015 ---//
	public WebElement imgPDFiconPaperBill;

	@FindBy(xpath="//a[contains(@name,'View usage for') or contains(@name,'View Usage for') or contains(@name,'view usage for')] | //a[@id='WLServiceSelector']")			// --- Object modified by Rahul Bakde - 8/19/2015 ---//
	public WebElement lstViewServiceDropDown;

	@FindBy(xpath="//div[@class='botMar15']/h2 | (//td[@headers='member_head']//div/p/strong)[1]")			// --- Object added by Rahul Bakde - 8/3/2015 ---//
	public WebElement txtDeviceName;

	@FindBy(xpath="//img[contains(@alt,'Phone')]")			// --- Object added by Rahul Bakde - 8/3/2015 ---//
	public WebElement imgDeviceIcon;

	@FindBy(xpath="//div[contains(@class,'MarTopNeg')]//p | (//td[@headers='member_head']//div/span/p[contains(text(),'.')])[1]")			// --- Object added by Rahul Bakde - 8/3/2015 ---//
	public WebElement txtDeviceNumber;

	@FindBy(xpath="//h2[contains(text(),'Detail')] | (//td[@headers='member_head']//div//a[contains(text(),'View usage details')])[1]")			// --- Object added by Rahul Bakde - 8/3/2015 ---//
	public WebElement txtDetails;
	
	@FindBy(xpath="(//td[@headers='member_head']//div//a[contains(text(),'View usage details')])[1]")			// --- Object added by Rahul Bakde - 8/3/2015 ---//
	public WebElement lnkViewUsageDetailsLink;

	@FindBy(xpath="//a[contains(text(),'rint')]")			// --- Object added by Rahul Bakde - 8/3/2015 ---//
	public WebElement lnkPrintIcon;

	@FindBy(xpath="//p[contains(text(),'ownload')]//parent::div")			// --- Object added by Rahul Bakde - 8/3/2015 ---//
	public WebElement lstDownloadDropDown;

	@FindBy(xpath="//span/input[@id='srchText'] | //input[@title='searchweb']")			// --- Object added by Rahul Bakde - 8/3/2015 ---//
	public WebElement edtSearchField;

	@FindBy(xpath="//img[@alt='search']")			// --- Object added by Rahul Bakde - 8/3/2015 ---//
	public WebElement imgSearchIcon;
	
	@FindBy(xpath="//a[contains(@class,'closeModal')]")			//Added by Clint on 2nd Feb 2016
	public WebElement imgCloseModal;
	
	@FindBy(xpath="//table[@class='table']")			// --- Object added by Rahul Bakde - 8/3/2015 ---//
	public WebElement tblUsageDetail;

	@FindBy(xpath="//h3[contains(text(),'Manage Billing')]")			// --- Object added by Rahul Bakde - 8/3/2015 ---//
	public WebElement txtManageBillingPaymentOption;

	@FindBy(xpath="//div[@id='linkrailset3']/h3[contains(text(),'Get Help')]")			// --- Object added by Rahul Bakde - 8/3/2015 ---//
	public WebElement txtGetHelpHeading;

	@FindBy(xpath="//*[name()='svg']")			// --- Object to identify svg element like pie chart on data usage page - Rahul Bakde - 8/19/2015
	public WebElement svgPieChart;
	
	@FindBy(xpath="//div/h2[contains(text(),'Basic Plan') or contains(text(),'Basic plan') or contains(text(),'basic plan') ]")			// --- Object added by - Rahul Bakde - 8/19/2015
	public WebElement txtBasicPlanUsage;

	@FindBy(xpath="//div[@id='usageTableToggleId']")			// --- Object added by - Rahul Bakde - 8/19/2015
	public WebElement tblBasicPlanUsage;

	@FindBy(xpath="//div[contains(@class,'PadLeft30')]")			// --- Object added by - Rahul Bakde - 8/19/2015
	public WebElement tblAdditionalUsage;

	@FindBy(xpath="//h2[contains(text(),'dditional Usage') or contains(text(),'dditional usage')]")			// --- Object added by - Rahul Bakde - 8/19/2015
	public WebElement f;

	@FindBy(xpath="//h3[contains(text(),'anage Account') or contains(text(),'anage account')]/parent::div")			// --- Object added by - Rahul Bakde - 8/19/2015
	public WebElement tblManageSecRail;

	@FindBy(xpath="//h3[contains(text(),'Payment Option') or contains(text(),'ayment option')]/parent::div")			// --- Object added by - Rahul Bakde - 8/19/2015
	public WebElement tblManageBillPaySecRail;

	@FindBy(xpath="//h3[contains(text(),'et Help') or contains(text(),'et help')]/parent::div[contains(@id,'linkrail')]")			// --- Object added by - Rahul Bakde - 8/19/2015
	public WebElement tblGetHelpSecRail;

	@FindBy(xpath="//div[@id='usageContent']//span[contains(@class,'Weight')]")			// --- Object added by - Rahul Bakde - 8/19/2015
	public WebElement txtTotalDataUsage;

	@FindBy(xpath="//div[@id='usageContent']//span[contains(text(),'/')]")			// --- Object added by - Rahul Bakde - 8/19/2015
	public WebElement txtTotalDataAllowance;
	
	@FindBy(xpath="//div[@id='20150628|177052446643|T01|W']//div/a/span")			// --- Object added by - Rahul Bakde - 8/24/2015
	public WebElement lnkBillCycleUsage;

//	@FindBy(xpath="//*[contains(text(),'dditional Usage')]")			// --- Object added by - Rahul Bakde - 8/25/2015
//	public WebElement txtAdditionalUsage;
	
	@FindBy(xpath="//div[contains(@class,'Usage')][contains(text(),'Select a plan')]")			
	public WebElement txtSelectAPlan;
	

	/*******************Objects added by onshore - End here *********************************/
	
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;

	
	public OR_BillAndUsage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
