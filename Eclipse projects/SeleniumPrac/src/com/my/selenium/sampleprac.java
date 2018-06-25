package com.my.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class sampleprac {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","C:\\Users\\ps439r\\eclipse-workspace\\SeleniumPrac\\Drivers\\chromedriver.exe");
		
WebDriver wb =new ChromeDriver();
wb.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
wb.manage().window().maximize();

wb.get("https://qkv10amp2.vci.att.com:9023/olam/loginAction.olamexecute");
wb.findElement(By.xpath("//*[@id=\"wireless_num\"]")).sendKeys("qay_slid_3240");
wb.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("test1ng");
wb.findElement(By.xpath("//*[@id=\"btnPrimarySmall\"]")).click();
if(wb.getPageSource().contains("Contact Email Address"))
{
	wb.findElement(By.xpath("//*[@id=\"primaryEmailAddress\"]")).sendKeys("abc@att.com");
	wb.findElement(By.xpath("//*[@id=\"confirmPrimaryEmailAddress\"]")).sendKeys("abc@att.com");
	if(wb.getPageSource().contains("Security Questions"))
	{
		Select s1 =new Select(wb.findElement(By.xpath("//*[@id=\"question1\"]")));
		s1.selectByIndex(1);
		wb.findElement(By.xpath("//*[@id=\"answer1\"]")).sendKeys("test");
		
		Select s2 =new Select(wb.findElement(By.xpath("//*[@id=\"question2\"]")));
		s2.selectByIndex(1);
		wb.findElement(By.xpath("//*[@id=\"answer2\"]")).sendKeys("test");

			}
	WebDriverWait ww=new WebDriverWait(wb, 20);
	WebElement element = ww.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"continue\"]/input")));
	element.click();

}


wb.close();

}
}
