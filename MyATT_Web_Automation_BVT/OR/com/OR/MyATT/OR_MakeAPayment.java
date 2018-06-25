package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_MakeAPayment {

	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[contains(@src,'editPaymentProfile')]")
	public WebElement frmEditPaymentProfile;
	//frame objs
	@FindBy(id="cvvcid")
	public WebElement txtPaymentProfileName;
	
	@FindBy(xpath="//label[text()='Payment Method']")
	public WebElement txtPaymentMethodfrm;
	
	@FindBy(xpath="//a[@id='delete_button']")
	public WebElement btnDeleteProfile;
	
	@FindBy(xpath="//a[text()='Save'] | //a[contains(text(),'Continue')]")
	public WebElement btnSave;
	
	@FindBy(xpath="//a[text()='Cancel']")
	public WebElement btnCancelFrm;
	
	@FindBy(xpath="//iframe[contains(@src,'Add Checking/Savings Account')]")
	public WebElement frmNewCheckingSavingPaymentMethod;

	@FindBy(id="colorbox")
	public WebElement frmPayoff;
	
	@FindBy(className="cboxIframe")
	public WebElement frmPayoffIFrame;
	
	@FindBy(xpath="//iframe[contains(@src,'setupPaymentPopup')]")
	public WebElement frmPendingOneTimePayments;
	
	@FindBy(xpath="//iframe[contains(@src,'CalendarView')]")
	public WebElement frmCalender;
	
	@FindBy(className="cboxIframe")
	public WebElement frmIncompPay;
	
	@FindBy(xpath="//iframe[contains(@src,'Add Credit/Debit Card')]")
	public WebElement frmNewDebitCreditCardPaymentMethod;
	
	//frmNewDebitCreditCardPaymentMethod objs
	@FindBy(xpath="//h1[contains(text(),'New Debit / Credit Card Payment Method')]")
	public WebElement frmTxtDebitCredit;
	
	@FindBy(xpath="//p[contains(text(),'Debit / Credit Card Information')]")
	public WebElement frmTxtCDinformation;
	
	@FindBy(xpath="//span[contains(text(),'Step 1 of 3: Enter payment information')]")
	public WebElement tabEnterPaymentInformation;
	
	@FindBy(xpath="//p[contains(text(),'Select Continue')]")
	public WebElement frmTxtSectionDesc;
	
	@FindBy(xpath="//div[@id='uniform-paymentMethodForm.storePaymentProfileInd']")
	public WebElement frmCheckBox;
	
	@FindBy(xpath="//div[@id='uniform-paymentMethodForm.storePaymentProfileInd']//span")
	public WebElement frmCheckBox1;
	
	@FindBy(id="pExpirationMonthErrorMessage")
	public WebElement frmExpirationMonthErrorMsg;
	
	@FindBy(xpath="//p[text()='Confirm name has no special characters']")
	public WebElement frmErrorProfile;
	
	
	
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	@FindBy(xpath="//img[contains(@alt,'Routing')]")
	public WebElement imgRoutingTransitNum;

	@FindBy(xpath="//img[@alt='Calendar'] | //a[@id='calendar_0_1']") 
	public WebElement imgCalender;

	@FindBy(xpath="//img[@alt='Help']")
	public WebElement imgToolTipIcon;

	@FindBy(xpath="//a[@id='calendar_0_1']") 
	public WebElement imgCalender1;
	
	@FindBy(xpath="//img[contains(@alt,'Code is')]") 
	public WebElement imgCreditCard;

	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/

	@FindBy(xpath="//input[@id='Checking' and @checked='checked']")
	public WebElement radCheckingSelectedByDefault;
	
	@FindBy(xpath="//input[@id='Saving']")
	public WebElement radSaving;
	
	@FindBy(xpath="//input[@name='paymentMethodForm.storePaymentProfileInd']")
	public WebElement radStorePaymentInfo;
	
	
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	@FindBy(id="makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.methodNameId")
	public WebElement lstPaymentMethod;
	
	@FindBy(xpath="//select[contains(@id,'makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.methodNameId')]")
	public WebElement lstPaymentMethodnew;
	
	@FindBy(xpath="//select[contains(@id,'expirationMonth')]") 
	public WebElement lstSelectCardExpirationMonth;

	@FindBy(xpath="//select[contains(@id,'paymentMethodForm.expirationYear')]") 
	public WebElement lstSelectCardExpirationYear;
	
	@FindBy(id="uniform-makePaymentForm.paymentRequestsCustomize.paymentRequests[1].paymentItem1.methodNameId")
	public WebElement lstPaymentMethodObj;
	
	@FindBy(id="makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.methodNameId")
	public WebElement lstPaymentMethod3;

	@FindBy(id="makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem2.methodNameId")
	public WebElement lstPaymentMethod2;
	
	@FindBy(xpath ="//input[contains(@id,'paymentMethod')]")
	public WebElement lstPaymentMethodRWD;
	
	
	
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	@FindBy(xpath="//*[@id=enrollInAutoPay0] | //*[@id='enrollInAutopayChkBox_0_1']")
	public WebElement chkEnrollInAutopayCheckbox;

	@FindBy(id="paymentMethodForm.storePaymentProfileInd")
	public WebElement chkPaymentFrameSaveMyPaymentInformationCheckbox;
	
	@FindBy(id="uniform-makePaymentForm.paymentRequestsCustomize.paymentRequests[0].processRequest") 
	public WebElement chkAccountSelectCheckbox;
	
	@FindBy(id="enrollInAutopayChkBox_0_1") 
	public WebElement chkAccountSelectCheckbox_1;
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/

	@FindBy(xpath="//input[@id='paymentAmount' or @title='Payment Amount'] | //input[@id='pmtAmount0']") 
	public WebElement edtPaymentAmount;

	@FindBy(xpath="//input[contains(@name,'CustomerName')]") 
	public WebElement edtPaymentFrameNameOnCard;

	@FindBy(xpath="//input[contains(@name,'cardNumber')]") 
	public WebElement edtPaymentFrameCardNumber;

	@FindBy(xpath="//input[contains(@name,'cardVerificationNumber')]") 
	public WebElement edtPaymentFrameSecurityNumber;

	@FindBy(xpath="//input[contains(@name,'zip')]") 
	public WebElement edtPaymentFrameZipCode;

	@FindBy(xpath="//input[contains(@name,'PaymentProfileName')] | //*[@id='storePaymentProfileName']") 
	public WebElement edtPaymentFrameProfileName;

	@FindBy(id="paymentMethodForm.newBankCustomerName")
	public WebElement edtNameOnBankAcc;

	@FindBy(xpath="//input[contains(@id,'routingnum')]")
	public WebElement edtRoutingNumber;

	@FindBy(xpath="//input[contains(@id,'accnum')]")
	public WebElement edtBankAccountNumber;

	@FindBy(id="paymentMethodForm.accountNumberConfirm")
	public WebElement edtReenterAccNum;

	@FindBy(xpath="//span[contains(text(),'calendar')]//parent::button")
	public WebElement edtDate;
	
	@FindBy(id="pdate_0_1")
	public WebElement edtDate1;
	
	@FindBy(xpath="//div[@id='amountDivChild0_1']//input | (//input[@id='paymentAmount'])[1]")
	public WebElement edtPaymentAmount1;
	
	@FindBy(xpath="//div[@id='amountDivChild0_2']//input")
	public WebElement edtPaymentAmount2;
	
	@FindBy(xpath="//input[@id='makePaymentForm.paymentRequestsCustomize.paymentRequests[1].paymentItem1.paymentAmount']")
	public WebElement edtPaymentAmountSlid;
	
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//h1[contains(text(),'Make a payment')]") 
	public WebElement txtMakeAPayment;

	@FindBy(xpath="//*[text()='Payment Calendar']") 
	public WebElement txtFramePaymentCalenderTitle;
	
	@FindBy(xpath="//p[@id='pNameOnBankErrorMessage']")
	public WebElement txtNameOnBankErrorMsg;

	@FindBy(xpath="//p[@id='pRoutingNumberErrorMessage']")
	public WebElement txtRoutingNumErrorMsg;

	@FindBy(xpath="//p[@id='pAccountNumberErrorMessage']")
	public WebElement txtAccNumErrorMsg;

	@FindBy(xpath="//p[@id='pAccountNumberConfirmErrorMessage']")
	public WebElement txtAccNumConfirmErrorMsg;
	
	@FindBy(id="uniform-paymentMethodForm.expirationMonth")
	public WebElement txtMonthDropDown;
	
	@FindBy(id="uniform-paymentMethodForm.expirationYear")
	public WebElement txtYearDropDown;
	
	@FindBy(xpath="//span[contains(text(),'Due Date')]")
	public WebElement txtDueDate;

	@FindBy(xpath="//*[@class='label'][contains(text(),'Account')]")
	public WebElement txtAccount;

	@FindBy(id="uniform-makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.methodNameId")
	public WebElement txtPaymentMethod;

	@FindBy(xpath="//p[@class='MarLeft0 botMar0  font16 bt_left']")
	public WebElement txtBAN;
	
	@FindBy(xpath="//span[contains(text(),'BTN')]")
	public WebElement txtBTN;

	@FindBy(tagName="h2")
	public WebElement txtPayoffHeading;
	
	@FindBy(tagName="p")
	public WebElement txtPayoffDescriptive;
	
	@FindBy(xpath="//div[@class='row-seam current top-round']")
	public WebElement txtPaymentStep1;
	
	@FindBy(xpath="//div[contains(text(),'Current')]")
	public WebElement txtCurrentPayment;
	
	@FindBy(xpath="//div[contains(text(),'Account/User')]")
	public WebElement txtAccountUser;

	@FindBy(xpath="//div[@class='section-title']/h2[text()='Choose Payment Method ']")
	public WebElement txtChoosePaymentMethod;
	
	@FindBy(xpath="//p/b/label[contains(text(),'Total Amount to Pay')]")
	public WebElement txtTotalAmtToPay;
	
	@FindBy(xpath="//p/b[contains(text(),'Total Payment')]")
	public WebElement txtTotalPayment;
	
	@FindBy(xpath="//p[@class='right PadTop15 MarRight15 botMar12']")
	public WebElement txtTotalAmtToPayEntire;
	
	@FindBy(xpath="//p[@class='right MarRight15 botMar18']")
	public WebElement txtTotalPaymentEntire;
	
	//@FindBy(xpath="//h2[contains(text(),'Payment Alert')] | //*[contains(text(),'Edit Stored Payment Method')]")
	//public WebElement txtPaymentAlert;
	
	@FindBy(xpath="//h2[contains(text(),'Payment Notice')] | //*[contains(text(),'Edit this payment')]")
	public WebElement txtPaymentAlert;
	
	
	@FindBy(xpath="//p[contains(text(),'Select Account to Pay')]")
	public WebElement txtSelectAccountToPay;
	
	@FindBy(xpath="//h1[contains(text(),'Pending One-Time')]")
	public WebElement txtPendingOneTimePayment;
	
	@FindBy(xpath="//div[@id='secondary-content']")
	public WebElement txtStepIndicator;
	
	@FindBy(xpath="//div[@id='secondary-content']//span[text()='Payment Entry']")
	public WebElement txtStepIndicatorPaymentEntry;
	
	@FindBy(xpath="//span[text()='Pay All Accounts']")
	public WebElement txtPayAllAccounts;
	
	@FindBy(xpath="//span[text()='Customize My Payments']")
	public WebElement txtCustomizePayments;
	
	@FindBy(xpath="//label[text()='Payment Amount']")
	public WebElement txtPaymentAmountLabel;
	
	@FindBy(xpath="//label[text()='Payment Date']")
	public WebElement txtPaymentDateLabel;
	
	@FindBy(xpath="//label[text()='Payment Method'] | (//label[text()='Payment Method'])[1]")
	public WebElement txtPaymentMethodLabel;
	
	@FindBy(xpath="//span[contains(text(),'$')]/parent::li[contains(@class,'balance')]//span[contains(text(),'$')]")
	public WebElement txtPaymenttxt;
	
	@FindBy(xpath="//label[@for='paymentAmount'][contains(text(),'$')] | //div[@id='amountDiv0_1']//label[contains(text(),'$')]")
	public WebElement txtDollarSign;
	
	@FindBy(xpath="//p[contains(text(),'The amount')]")
	public WebElement txtPaymentAlertText;
	
	@FindBy(xpath="//p[contains(text(),'Account Information')]")
	public WebElement txtPaymentMethodInformation;
	
	@FindBy(xpath="//h1[contains(text(),'New Checking / Savings Payment Method')] | //li[contains(text(),'New checking')]")
	public WebElement txtCheckingSaving;
	
	@FindBy(xpath="//h3[contains(text(),'Save my payment information')]")
	public WebElement txtSaveMyPaymentInformation;
	
	@FindBy(xpath="//p[contains(text(),'You can store')]")
	public WebElement txtSavePaymentSectionDescription;
			
	@FindBy(xpath="//label[contains(text(),'My payment profile name')]")
	public WebElement txtMyPaymentProfileName;
	
	@FindBy(xpath="//h2[contains(text(),'Are you sure')]")
	public WebElement txtAreYouSure;
	
	@FindBy(xpath="//h1[text()='Incomplete Payment']")
	public WebElement txtIncomplete;
	
	@FindBy(xpath="//span[@name='makePaymentForm.paymentRequestsCustomize.paymentRequests[0].paymentItem1.paymentDate']")
	public WebElement txtDate;
	
	@FindBy(xpath="//input[@id='pdate_0_1']")
	public WebElement txtPaymentDate;
	
	@FindBy(xpath="//li[@class='step-current']")
	public WebElement txtStep1New;
	
	@FindBy(xpath="//*[contains(text(),'Pay your way')]")
	public WebElement txtPayAllNowNote;
	
	@FindBy(xpath="//div[@class='step-indicator-hor box']")
	public WebElement txtProgressBar;
	
	@FindBy(xpath="//h1[text()='Manage AutoPay']")
	public WebElement txtManageAutoPay;
	
	@FindBy(xpath="//span[contains(text(),'Card expiration date')]")
	public WebElement txtCardExpirationDate;
	
	@FindBy(xpath="//span[contains(@id,'CardExp')]")
	public WebElement txtCardExpirationDate1format;
	
	@FindBy(xpath="//*[contains(text(),'View plan details')]")
	public WebElement txtViewPlanDetailsNote;
	
	@FindBy(xpath="//*[contains(text(),'Pay off remaining installments')]")
	public WebElement txtPayOffRemainingInstallments;
	
	@FindBy(xpath="//strong[contains(text(),'Notes:')]/parent::p/parent::div")
	public WebElement txtNotes;
	
	@FindBy(xpath="//div[@id='instructionNoteBox']/p[contains(text(),'Select a date when you')]")
	public WebElement txtCalenderNote;
	
	@FindBy(xpath="//strong[contains(text(),'Due date')]")
	public WebElement txtDueDatenotecalender;
	
	@FindBy(xpath="//tbody//tr//td[contains(@class,'red-ring')]")
	public WebElement txtDueDatecalender;
	
	@FindBy(xpath="//tbody//tr//td[contains(@class,'currentDate')] | (//td[@role='button']/div)[1]")
	public WebElement txtCurrentDatecalender;
	
	@FindBy(xpath="//*[contains(text(),'Payment Alert')]")
	public WebElement txtPaymentAlert1;
	
	@FindBy(xpath="(//p[contains(text(),'Enter amount')])[1]")
	public WebElement txtErrorMsgOnBlankPaymentAmt;
	
	@FindBy(xpath="(//p[contains(text(),'Select payment method')])[1]")
	public WebElement txtErrorMsgOnSelectingNoPaymentMethod;

	/*---------------------------
	 * Link objects add below
	 *---------------------------*/

	@FindBy(xpath="//a[contains(text(),'Continue')]")
	public WebElement lnkContinue;
	
	@FindBy(xpath="//div[@id='md']//a[contains(text(),'Continue')]")
	public WebElement lnkContinueDiv;
	
	@FindBy(xpath="//a[text()='Close']") 
	public WebElement lnkFramePaymentCalenderClose;

	@FindBy(xpath="//a[contains(text(),'Close')]")
	public WebElement lnkCloseFrame;

	@FindBy(xpath="//a[@id='ge5p_z1-language-drop-down-link']")
	public WebElement lnkLanguage;

	@FindBy(xpath="//a[contains(text(),'Página en Español')] | //a[contains(text(),'Sitio en Español')]")
	public WebElement lnkPaginaEnEspanol;

	@FindBy(xpath="//a[contains(@id,'profile_0_1')]")
	public WebElement lnkEditPaymentProfile;

	@FindBy(xpath="//a[contains(text(),'Save')]")
	public WebElement lnkSave;

	@FindBy(xpath="//a[contains(text(),'Split this payment')]")
	public WebElement lnkSplitThisPayment;

	@FindBy(id="payoff")
	public WebElement lnkPayoff;
	
	@FindBy(xpath="//a[contains(text(),'Manage payment profile')]")
	public WebElement lnkManagePaymentProfile;
	
	@FindBy(xpath="//a[contains(@class,'textLink')] | //a[contains(text(),'Cancel')] | //div[@id='cancel']")
	public WebElement lnkCancel;
	
	@FindBy(xpath="//a[@id='cancel']")
	public WebElement lnkCancelPayment;
	
	@FindBy(xpath="//a[text()='Cancel']")
	public WebElement lnkCancelFrm;
	
	@FindBy(xpath="//a[contains(text(),'Scheduled payment of')]")
	public WebElement lnkScheduledPayment;
	
	@FindBy(xpath="//*[@id='payCommonActionForm']/div[2]/div[2]/table/tbody/tr/td[6]/a")
	public WebElement lnkEditOnPendingOneTimePayment;
	
	@FindBy(xpath="//a[@id='acct_0_rmvpmtlink']")
	public WebElement lnkDeleteThisRow;
	
	@FindBy(xpath="//a[text()='Edit Payment'] | //a[contains(text(),'Edit this payment')]")
	public WebElement lnkEditPayment;
	
	@FindBy(xpath="//a[@class='closeModal']")
	public WebElement lnkCloseModal;
	
	@FindBy(xpath="//a[contains(text(),'No')]")
	public WebElement lnkNo;
	
	@FindBy(xpath="//a[@id='idBtnAnchor']")
	public WebElement lnkContinueDis;
	
	@FindBy(xpath="//a[contains(text(),'Flexible payment options')]")
	public WebElement lnkFlexiblepaymentoptions;
	
	@FindBy(xpath="//a[contains(text(),'Manage AutoPay settings')]")
	public WebElement lnkManageAutoPaySettings;
	
	
	@FindBy(xpath="//span[contains(text(),'Step 2 of 3: Review payment')]")
	public WebElement tabReviewPayment;
	
	@FindBy(xpath="//span[contains(text(),'Review payment. Current step')]")
	public WebElement txtReviewPaymentCurrentStep;
	
	@FindBy(xpath="//span[contains(text(),'Step 3 of 3: Payment status')]")
	public WebElement tabPaymentStatus;
	
	@FindBy(xpath="//span[contains(text(),'Payment status. Completed')]")
	public WebElement txtPaymentStatusCompleted;
	
	@FindBy(xpath="//*[contains(text(),'Payment confirmation')]")
	public WebElement txtPaymentConfirmation;
	
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[text()='Continue'][contains(@class,'button')]") 
	public WebElement btnPaymentFrameContinue;

	@FindBy(xpath="//a[contains(@id,'Anchor')][contains(text(),'Next')] | //a[contains(text(),'Continue')]")
	public WebElement btnNext;

	@FindBy(xpath="//a[contains(@class,'btn')]")
	public WebElement btnPayoffContinue;
	
	@FindBy(xpath="//a[contains(text(),'Yes,Continue')]")
	public WebElement btnYesCont;
	
	@FindBy(xpath="//a[contains(text(),'Submit')] | //button[contains(text(),'Submit')]")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//div[@class='p-split']//a/img")
	public WebElement btnSplitThisPaymentTooltip;

	@FindBy(xpath="(//div[contains(@class,'p-method')]/a/img)[1]")
	public WebElement btnPaymentMethodTooltip;

	@FindBy(xpath="(//a[contains(@id,'enrollinAutoPayHelp')]/img)[1]")
	public WebElement btnEnrollInAutopayTooltip;
	
	@FindBy(xpath="//button[contains(text(),'Continue')]")
	public WebElement btnContinue;
	
	@FindBy(xpath="//button[contains(text(),'No')]")
	public WebElement btnNo;

	@FindBy(xpath="//button[contains(text(),'OK')]")
	public WebElement btnOk;
	
	/* --------- Objects added by onshore - Start here -------------- */
	
	
	@FindBy(xpath="//a[@id='4300243' and contains(text(),'Flexible payment options')]")     //Added by Deepak on 07/29/15
	public WebElement lnkFlexiblePaymentOptions;
	
	@FindBy(xpath="//span[contains(text,'Please pay immediately')]")       //Added by Deepak on 07/29/15
	public WebElement txtPleasePayImmediately;
	
	@FindBy(xpath="//*[@id='ulAccount0']/li[1]")				//Added by Deepak on 08/04/15
	public WebElement txtMultipleAccNum;
	
	@FindBy(xpath="//*[@id='ulAccount0']/li[2]")				//Added by Deepak on 08/04/15
	public WebElement txtBalanceForAcc;
	
	@FindBy(xpath="//*[@id='ulAccount0']/li[3]")				//Added by Deepak on 08/04/15
	public WebElement txtDueDateForAcc;
	
	@FindBy(xpath="//*[@id='ulAccount0']/li[]4")				//Added by Deepak on 08/04/15
	public WebElement txtPastDueForAcc;
	
	@FindBy(xpath="//ul[@class='map-account-info botMar25 topMar25']/li[1] | //*[@id='ulAccount0']/li[1]")    //Added by Deepak on 08/04/15
	public WebElement txtBANAndAccNum;
	
	@FindBy(xpath="//ul[@class='map-account-info botMar25 topMar25']/li[2] | (//div[@class='p-amount']/div/label[contains(text(),'$')])[1]")    //Added by Deepak on 08/04/15
	public WebElement txtBalance;
	
	@FindBy(xpath="//ul[@class='map-account-info botMar25 topMar25']/li[3]")    //Added by Deepak on 08/04/15
	public WebElement txtDueDateForAccount;
	
	@FindBy(xpath="//ul[@class='map-account-info botMar25 topMar25']/li[4]")    //Added by Deepak on 08/04/15
	public WebElement txtPastDueDateForAccount;
	
	@FindBy(xpath="//*[@id='cboxLoadedContent']")                                            //Added by Deepak on 08/11/15
	public WebElement frmIncompletePayment;
	
	
	
	/* --------- Objects added by onshore - End here -------------- */
	
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	WebDriver driver;





	public OR_MakeAPayment(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
