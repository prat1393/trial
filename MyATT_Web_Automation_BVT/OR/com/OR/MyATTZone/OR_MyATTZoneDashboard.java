package com.OR.MyATTZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class OR_MyATTZoneDashboard {
	WebDriver driver;

	@FindBy(id="STM0XX0YY1ZZMTD") 
	public WebElement elm_ATTAccessID;
	
	@FindBy(id="id_slidSearch") 
	public WebElement elm_ATTAccessIDTxtField;
	
	
	
	@FindBy(id="STM0XX1YY0ZZMTD")
	public WebElement elm_WirelessCTN ;
	
	
	
	@FindBy(id="STM0XX0YY3ZZMTD") 
	public WebElement elm_Uverse;
	
	@FindBy(id="STM0XX2YY0ZZMTD") 
	public WebElement elm_UverseMID;
	
	@FindBy(id="STM0XX2YY1ZZMTD") 
	public WebElement elm_UverseBAN;
	
	@FindBy(id="STM0XX0YY4ZZMTD") 
	public WebElement elm_Wireline;
	
	@FindBy(id="STM0XX3YY0ZZMTD") 
	public WebElement elm_WirelineLoginID;
	
	@FindBy(id="STM0XX3YY1ZZMTD") 
	public WebElement elm_WirelineBTN;
	
	@FindBy(id="STM0XX0YY5ZZMTD") 
	public WebElement elm_DSL;
	
	@FindBy(id="STM0XX4YY0ZZMTD") 
	public WebElement elm_DSLMID;
	
	@FindBy(id="STM0XX4YY1ZZMTD") 
	public WebElement elm_DSLWTN;
	
	@FindBy(id="STM0XX0YY6ZZMTD") 
	public WebElement elm_FAN;
	
	@FindBy(id="STM0XX0YY7ZZMTD") 
	public WebElement elm_FreeEmailID;
		
	@FindBy(xpath="//a[@class='cingularLink']/img[contains(@src,'bt_web_impersonate')]") 
	public WebElement myATTWeb;

	
	

	/*---------------------------
	 * Image objects add below
	 *---------------------------*/
	
	
	
	/*---------------------------
	 * Radio button objects add below
	 *---------------------------*/
	@FindBys(value = { @FindBy(xpath="//*[@id='selectedSlid']") })
	public WebElement radSelectSlid;

	
	
	/*---------------------------
	 * List objects add below
	 *---------------------------*/
	
	
	
	
	
	/*---------------------------
	 * Checkbox objects add below
	 *---------------------------*/
	
	
	
	
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	@FindBy(id="id_accountNumber")
	public WebElement edtWirelessBAN;
	
	
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(id="STM0XX0YY2ZZMTD")
	public WebElement txtWireless ;
	
	@FindBy(id="STM0XX1YY1ZZMTD")
	public WebElement txtWirelessBAN ;
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	

	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	@FindBy(xpath="//td/input[@type='image']") 
	public WebElement btnSearch;
	
	@FindBy(id="continue") 
	public WebElement btnContinue;
	
	
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	
	
	
	
	public OR_MyATTZoneDashboard(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
