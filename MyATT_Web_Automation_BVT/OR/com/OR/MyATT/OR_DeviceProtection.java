package com.OR.MyATT;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OR_DeviceProtection {
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	
	/*---------------------------
	 * Text box objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//a[contains(text(),'Device protection')]")
	public WebElement txtDeviceProtection;
	
	@FindBy(xpath="//div[contains(@class,'btnChecked ')]//label")
	public WebElement txtDefaultSelectedFeature;
	
	/*---------------------------
	 * Frames add below
	 *---------------------------*/
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	
	/*---------------------------
	 * Button objects add below
	 *---------------------------*/
	
	@FindBy(xpath="//div[@id='btnNext']//button[contains(text(),'Select & Review')]")
	public WebElement btnSelectAndReviewEnabled;
	
	@FindBy(xpath="//*[@id='Continue'] | //button[contains(text(),'Continue')]")
	public WebElement btnContinue;
	
	/*---------------------------
	 * Frame objects add below
	 *---------------------------*/
	
	/*---------------------------
	 * WebDriver objects add below
	 *---------------------------*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

