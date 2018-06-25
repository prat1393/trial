package com.OR.MyATT;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OR_ChatWindow {
	
	/*---------------------------
	 * Edit box objects add below
	 *---------------------------*/
	
	@FindBy(xpath="a[@title='Need Help? Chat Live now']")
	public WebElement edtName;
	
	@FindBy(xpath="//input[@id='q8']")
	public WebElement edtWirelessNum;
	
	@FindBy(xpath="//input[@id='q10']")
	public WebElement edtBAN;
	
	@FindBy(id="q14")
	public WebElement edtSelectReason;
	
	@FindBy(xpath="//textarea[@id='q16']")
	public WebElement edtAssistYou;
	
	
	/*---------------------------
	 * Frames add below
	 *---------------------------*/
	

	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	

	
	
	/*---------------------------
	 * Image objects add below
	 *---------------------------*/

	@FindBy(xpath="//img[@title='Start chat']")
	public WebElement imgStartChat;
	
	
	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}