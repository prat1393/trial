package com.OR.MyATT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR_PrintBillHistoryTab {

	/*---------------------------
	 * Text objects add below
	 *---------------------------*/
	@FindBy(xpath="//div[@class='noBillGradient']//p[1]")
	public WebElement txtAccNo;
	
	@FindBy(xpath="//div[@class='noBillGradient']//p[2]")
	public WebElement txtName;
	
	@FindBy(xpath="//div[@class='noBillGradient']//p[3]")
	public WebElement txtAdd;
	
	/*---------------------------
	 * Link objects add below
	 *---------------------------*/
	@FindBy(xpath="//a[@title='Print']")
	public WebElement lnkPrint;
	
	
	
	
	/*---------------------------
	 * Image objects add below
	 * *---------------------------*/
	@FindBy(xpath="//img[@alt='attlogo']")
	public WebElement imgATTlogo;

	WebDriver driver;
	
	
	
	
	public OR_PrintBillHistoryTab(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}


}
