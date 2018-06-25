/**************************************************************
   Library - UI 
   Description-   Includes all common UI handler functions/methods.
   Date created - 18-Dec-14
   Developed by - Rahul/Deepak
   Last Modified By - Rahul Bakde
   Last Modified Date - 3-Feb-2015
 ***************************************************************/

package com.SupportingFiles;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;








//import com.UI;
//import com.IO;
//import com.Report;
import com.SupportingFiles.LaunchAndLogout;
import com.OR.MyATT.*;
import com.TestCase.*;

@SuppressWarnings("all")
public class UI extends LaunchAndLogout{

	public static int iObjTimeOut;  
	static Report Report; 
	static IO IO;

	public static int iCurrIter; 
	public static WebDriver driver;
	public static WebDriverWait waitVar;
	public static String sTemp = ""; 
	public static int iTemp;


	public static ITestContext testContext;

	public UI(ITestContext testContext){
		this.testContext=testContext;
	}

	public static void putDriver(String testName, WebDriver driver1) {
		Report.sTestName_Driver.put(testName, driver1);
	}
	
	public static WebDriver getDriver(String testName) {
		return Report.sTestName_Driver.get(testName);
	}
	
	public static String CheckExist(WebElement objDesc) throws Exception {
		String sRes="False";
		try{
			if (objDesc.isDisplayed()) {
				sRes = "True"; 
			}
		}
		catch(Exception e){
			//			return sRes + "                                                           " + e.getMessage();
			return sRes;
		}
		return sRes;
	}

	public static void HighlightElement(WebDriver driver, WebElement elmElement) {
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor oJs = (JavascriptExecutor) driver;
			oJs.executeScript("arguments[0].setAttribute('style', arguments[1]);",
					elmElement, "color: yellow; border: 2px solid yellow;");
			oJs.executeScript("arguments[0].setAttribute('style', arguments[1]);",
					elmElement, "");
		}
	}

	public static void printMsg(String sMsg) {
		System.out.println(sMsg);
	}

	//	public static void WaitForObject(WebDriver driver, WebElement elmEle) {
	//		WebDriverWait oWait = new WebDriverWait(driver, iObjTimeOut);
	//		oWait.until(ExpectedConditions.visibilityOf(elmEle)); 
	//	}

	//	public static Boolean WaitForObject(WebDriver driver, WebElement elmEle,
	//			int iObjTimeOut) {
	//		try {
	//			WebDriverWait oWait = new WebDriverWait(driver, iObjTimeOut);
	//			oWait.until(ExpectedConditions.visibilityOf(elmEle));
	//			return true;
	//		} catch (Exception e) {
	//			return false;
	//		}
	//	}

	public static Boolean WaitForObject(WebElement elmEle, int iObjTimeOut) {
		
		
		try {
			WebDriverWait oWait = new WebDriverWait(driver, iObjTimeOut);
			oWait.until(ExpectedConditions.visibilityOf(elmEle));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
public static Boolean WaitForObject(WebElement elmEle, int iObjTimeOut,WebDriver lDriver) {
		try {
			WebDriverWait oWait = new WebDriverWait(lDriver, iObjTimeOut);
			oWait.until(ExpectedConditions.visibilityOf(elmEle));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

public static Boolean checkForScecondaryNavigation(WebElement elmEle1, WebElement elmEle2, WebDriver lDriver) {
	
	if(elmEle1.isDisplayed() == true){
		return true;
	}else{
		Actions action = new Actions(lDriver);
		action.moveToElement(elmEle2).build().perform();
			
		if(UI.WaitForObject(elmEle1, 15)){
			return true;
		}else{
			return false;
		}
   }

}

	public static void ForceClick(WebDriver driver, WebElement elmEle) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", elmEle);
	}

	public static void ForceEnter(WebDriver driver, WebElement ele, String val) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].value="+val+";", ele);
	}

	public static WebElement FindObj_WithAttribute(List <WebElement> ele, String atribName, String atribValue, boolean exct) {

		WebElement eR = null;
		for(WebElement e1 : ele) {
			eR = e1;
			if (exct) {
				if (e1.getAttribute(atribName).equals(atribValue)) {break;}
			} else {
				if (e1.getAttribute(atribName).contains(atribValue)) {break;}
			}
		}
		return eR; 
	}

	public static String NoOther_InList(List <WebElement> ele, String srchVal) {

		String srchStatus = "True";
		for(WebElement e1 : ele) {
			if (!e1.getText().contains(srchVal)) {
				UI.printMsg(e1.getText() + "--" + srchVal);
				srchStatus = "False"; break;}
		}
		return srchStatus; 
	}

	public static String VerifyNotInTable(String sSrchVal, WebElement elmEle, int iRw, int iCl) {

		String sFlag = "True";
		if (iRw!=0) {
			WebElement row = elmEle.findElements(By.tagName("tr")).get(iRw-1);
			String sSVal2 = row.findElements(By.tagName("td")).get(iCl).getText();
			if (!sSVal2.contains(sSrchVal)) { sFlag = "False"; }
		} else {

			List<WebElement> rows = elmEle.findElements(By.tagName("tr"));
			for(WebElement row : rows) {
				String sSVal2 = row.findElements(By.tagName("td")).get(iCl-1).getText().trim();
				if (!sSVal2.contains(sSrchVal)) { sFlag = "False"; break;}
			}
		}
		return sFlag;
	}

	public static String VerifyInMultipleTables(String sSrchVal, List<WebElement> elmEle, int iCl) {

		String sFlag = "False";
		for(WebElement tbl : elmEle) {
			List<WebElement> rows = tbl.findElements(By.tagName("tr"));
			for(WebElement row : rows) {
				List<WebElement> cols = row.findElements(By.tagName("td"));
				String sSVal2 = cols.get(iCl-1).getText().trim();
				if (sSrchVal.equalsIgnoreCase(sSVal2)) { sFlag = "True"; break;}
			}
		}
		return sFlag;
	}

	public static String VerifyInTable(String sSrchVal, WebElement elmEle, int iRw, int iCl) {

		String sFlag = "False";
		if (iRw!=0) {
			WebElement row = elmEle.findElements(By.tagName("tr")).get(iRw-1);
			String sSVal2 = row.findElements(By.tagName("td")).get(iCl).getText();
			if (sSrchVal.equalsIgnoreCase(sSVal2)) { sFlag = "True"; }
		} else {
			List<WebElement> rows = elmEle.findElements(By.tagName("tr"));
			for(WebElement row : rows) {
				List<WebElement> cols = row.findElements(By.tagName("td"));
				String sSVal2 = cols.get(iCl-1).getText().trim();
				if (sSrchVal.equalsIgnoreCase(sSVal2)) { sFlag = "True"; break;}
			}
		}
		return sFlag;
	}

	public static String GetColumn_Value(WebElement elmEle, int iRw, int iCl) {

		WebElement row = elmEle.findElements(By.tagName("tr")).get(iRw-1);
		String sFlag = row.findElements(By.tagName("td")).get(iCl).getText();
		return sFlag;
	}

	public static int GetRow_WithText(String sSrchVal, WebElement elmEle, int iCl) {

		int iRW = 0;
		List<WebElement> rows = elmEle.findElements(By.tagName("tr"));
		for (int iItr=0; iItr<rows.size(); iItr++) {
			String sSVal2 = rows.get(iItr).getText().trim();
			if (sSVal2.contains(sSrchVal)) { iRW = iItr+1; break;}
		}
		return iRW;
	}

	public static String VerifyText_WithString_Tables(String sSrchVal, String sMtchVal, List<WebElement> elmEle, int iSrchCl, int iMtchCl) {

		String sFlag = "Not Found";
		for(WebElement tbl : elmEle) {
			List<WebElement> rows = tbl.findElements(By.tagName("tr"));
			for(WebElement row : rows) {
				List<WebElement> cols = row.findElements(By.tagName("td"));
				String sSVal2 = cols.get(iSrchCl-1).getText().trim();
				if (sSrchVal.equalsIgnoreCase(sSVal2)) {
					sFlag = "True";
					String val3 = cols.get(iMtchCl-1).getText().trim();
					if (!sMtchVal.equalsIgnoreCase(val3)) { sFlag = "False"; break;}
				}
			}
		}
		return sFlag;
	}	

	public static String VerifyNotInDivList(String sSrchVal, String sSubLocator, WebElement elmEle, int iRw, int iCl) {

		String sFlag = "True";
		if (iRw!=0) {
			WebElement row = elmEle.findElements(By.cssSelector(sSubLocator)).get(iRw-1);
			String sSVal2 = row.findElements(By.tagName("div")).get(iCl).getText();
			if (!sSVal2.contains(sSrchVal)) { sFlag = "False"; }
		} else {

			List<WebElement> rows = elmEle.findElements(By.cssSelector(sSubLocator));
			for(WebElement row : rows) {
				String sSVal2 = row.findElements(By.tagName("div")).get(iCl-1).getText().trim();
				if (!sSVal2.contains(sSrchVal)) { sFlag = "False"; break;}
			}
		}
		return sFlag;
	}

	public static String VerifyInDivList(String sSrchVal, String sSubLocator, WebElement elmEle, int iRw, int iCl) {

		String sFlag = "False";
		if (iRw!=0) {
			WebElement row = elmEle.findElements(By.cssSelector(sSubLocator)).get(iRw-1);
			String sSVal2 = row.findElements(By.tagName("div")).get(iCl).getText();
			if (!sSVal2.contains(sSrchVal)) { sFlag = "True"; }
		} else {

			List<WebElement> rows = elmEle.findElements(By.cssSelector(sSubLocator));
			for(WebElement row : rows) {
				String sSVal2 = row.findElements(By.tagName("div")).get(iCl-1).getText().trim();
				if (sSVal2.contains(sSrchVal)) { sFlag = "True"; break;}
			}
		}
		return sFlag;
	}

	public static String VerifyText_WithString_Div(String sSrchVal, String sMtchVal, String sSubLocator, WebElement elmEle, int iSrchCl, int iMtchCl) {

		String sFlag = "Not Found";
		List<WebElement> rows = elmEle.findElements(By.cssSelector(sSubLocator));
		for(WebElement row : rows) {
			String sSVal2 = row.findElements(By.tagName("div")).get(iSrchCl-1).getText().trim();
			if (sSrchVal.equalsIgnoreCase(sSVal2)) { 
				sFlag = "True";
				String val3 = row.findElements(By.tagName("div")).get(iMtchCl-1).getText().trim();
				if (!sMtchVal.equalsIgnoreCase(val3)) { sFlag = "False"; break;}
			}
		}
		return sFlag;
	}

	public static String Verify_NoOther_InDivList(String sSrchVal, String sSubLocator, WebElement elmEle, int iRw, int iCl) {

		String sFlag = "True";
		if (iRw!=0) {
			WebElement row = elmEle.findElements(By.cssSelector(sSubLocator)).get(iRw-1);
			String sSVal2 = row.findElements(By.tagName("div")).get(iCl).getText();
			if (!sSVal2.contains(sSrchVal)) { sFlag = "False"; }
		} else {

			List<WebElement> rows = elmEle.findElements(By.cssSelector(sSubLocator));
			Boolean bRFlag;
			for(WebElement row : rows) {
				bRFlag = true;
				String sSVal2 = row.findElements(By.tagName("div")).get(iCl-1).getText().trim();
				for (String retval: sSrchVal.split("|")){
					if (!sSVal2.contains(retval)) { bRFlag = false;}
				}				
				if (!bRFlag) { sFlag = "False"; break;}
			}
		}
		return sFlag;
	}

	public static String GetColumnTotal(WebElement elmEle, int iCl) {

		float sumC = 0;
		List<WebElement> rows = elmEle.findElements(By.tagName("tr"));
		for(WebElement row : rows) {
			String sSVal2 = row.findElements(By.tagName("td")).get(iCl-1).getText().trim();
			sSVal2 = sSVal2.replace("$", "");
			sumC = sumC + Float.valueOf(sSVal2);
		}
		return String.valueOf(sumC);
	}

	public static void SelectMenuOption(WebDriver driver, WebElement ele1, WebElement ele2) throws Exception {

		Actions action = new Actions(driver);
		//action.moveToElement(ele1).perform();
		//Thread.sleep(5000L);
		//ele2.Click;
		UI.ForceClick(driver, ele2);
		Thread.sleep(5000L);
	}

	public static boolean acceptAlert(WebDriver driver) {
		try 
		{ 
			driver.switchTo().alert().accept(); 
			return true; 
		} 
		catch (NoAlertPresentException Ex) 
		{ 
			return false; 
		} 
	}

	public static String hasValue(String sVal) {
		String sFlag = "False";
		if (!sVal.equals("")) {
			sFlag = "True";
		}	
		return sFlag;
	}

	public static String getFontColor(WebElement elmEle) {

		String sColor = elmEle.getCssValue("color");
		String[] numbers = sColor.replace("rgba(", "").replace(")", "").split(",");
		int iR = Integer.parseInt(numbers[0].trim()); int iG = Integer.parseInt(numbers[1].trim()); int iB = Integer.parseInt(numbers[2].trim());
		String hex = String.format("#%02x%02x%02x", iR, iG, iB).toUpperCase();
		return hex;
	}


	public static boolean bitmapValidation(String sTcName, String sRepDtls, String sImageFile, WebDriver oDriver, WebElement elmEle) throws Exception {

		boolean flag = false; String errMsg = "";
		if (CheckExist(elmEle).equals("True")) {

			try { 
				File screenshot = ((TakesScreenshot)oDriver).getScreenshotAs(OutputType.FILE);
				BufferedImage fullImg = ImageIO.read(screenshot);
				Point point = elmEle.getLocation();
				int eleWidth = elmEle.getSize().getWidth(); int eleHeight = elmEle.getSize().getHeight();
				BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);

				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ImageIO.write(eleScreenshot, "png", out);
				byte[] bytes = out.toByteArray(); 
				String imgCap = Base64.encodeBase64String(bytes);			

				// Original Image
				File file = new File(IO.sImageFolder + sImageFile); String imgOrig = null;
				FileInputStream imageInFile = new FileInputStream(file);
				byte imageData[] = new byte[(int) file.length()];
				imageInFile.read(imageData); imgOrig = Base64.encodeBase64String(imageData); imageInFile.close();

				BufferedImage img2 = ImageIO.read(file);
				int width1 = eleScreenshot.getWidth(null);int width2 = img2.getWidth(null);int height1 = eleScreenshot.getHeight(null);int height2 = img2.getHeight(null);
				if ((width1 != width2) || (height1 != height2)) {
					errMsg = "Images dimensions mismatch";
				} else {
					long diff = 0;
					for (int y = 0; y < height1; y++) {
						for (int x = 0; x < width1; x++) {
							int rgb1 = eleScreenshot.getRGB(x, y);int rgb2 = img2.getRGB(x, y);int r1 = (rgb1 >> 16) & 0xff;int g1 = (rgb1 >>  8) & 0xff;
							int b1 = (rgb1      ) & 0xff;int r2 = (rgb2 >> 16) & 0xff;int g2 = (rgb2 >>  8) & 0xff;int b2 = (rgb2      ) & 0xff;
							diff += Math.abs(r1 - r2);diff += Math.abs(g1 - g2);diff += Math.abs(b1 - b2);
						}
					}
					double n = width1 * height1 * 3;double p = (diff / n / 255.0)*100.0;
					errMsg = "Percent Difference: " + String.format("%.2f", p);
					if (p<=0.0) { 	flag = true;    }
				}

				Report.BipmapPoint(sTcName, sRepDtls, flag, errMsg, imgOrig, imgCap, true);

			} catch (FileNotFoundException e) {
				System.out.println("Image not found" + e);
			} catch (IOException ioe) {
				System.out.println("Exception while reading the Image " + ioe);
			}
		}
		return flag;			
	}


	/**************************************************************
	 * Function Name -  SelectServiceFromSecondaryNavigation
	 * Description- this function selects service from secondary and tertiary navigation
	 * Parameters - eleSecondaryService , eleTertiaryService (Pass 'eleTertiaryService' as null if you want to click only secondary link not tertiary link)
	 * Date created - 2/4/15
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws ParseException 
	 ***************************************************************/


	public static  boolean SelectServiceFromSecondaryNavigation(WebElement eleSecondaryService, WebElement eleTertiaryService) throws HeadlessException, IOException, AWTException, ParseException
	{
		WebDriver driver = UI.getDriver(testContext.getName());
		
		try
		{
			String sSecondaryServiceText="";

			Actions action = new Actions(driver);

			if(WaitForObject(eleSecondaryService,iObjTimeOut))
			{
				sSecondaryServiceText=eleSecondaryService.getText();

				System.out.println(sSecondaryServiceText);

				action.moveToElement(eleSecondaryService).build().perform();

				if(eleTertiaryService==null)
				{
					Report.TestPoint(testContext.getName(), "Click on Secondary link '"+sSecondaryServiceText+"'", "Secondary link '"+sSecondaryServiceText+"' clicked", "Secondary link '"+sSecondaryServiceText+"' clicked" , true);
					action.moveToElement(eleSecondaryService).click().build().perform();
					return true;
				}
				else
				{
					String sTertiaryServiceText="";

					if(WaitForObject(eleTertiaryService,iObjTimeOut))
					{
						sTertiaryServiceText= eleTertiaryService.getText();

						action.moveToElement(eleSecondaryService).moveToElement(eleTertiaryService).build().perform();
						Report.TestPoint(testContext.getName(), "Click on Tertiary link '"+sTertiaryServiceText+"'", "Tertiary link '"+sTertiaryServiceText+"' clicked", "Tertiary link '"+sTertiaryServiceText+"' clicked" , true);
						action.moveToElement(eleSecondaryService).moveToElement(eleTertiaryService).click().build().perform();
						return true;
					}
					else
					{
						Report.TestPoint(testContext.getName(), "Hover over Tertiary link '"+sTertiaryServiceText+"'", "Tertiary link '"+sTertiaryServiceText+"' should be present", "Tertiary link '"+sTertiaryServiceText+"' is not present" , true);
						return false;
					}
				}
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Hover over Secondary link '"+sSecondaryServiceText+"'", "Secondary link '"+sSecondaryServiceText+"' should be present", "Secondary link '"+sSecondaryServiceText+"' is not present" , true);
				return false;
			}

		}catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Navigation", "Navigated successfully", "Navigation failed" , true);
			return false;
		}

	}
	
	/**************************************************************
	 * Function Name -  SelectServiceFromSecondaryNavigation
	 * Description- Overloaded Function with Parallel execution support. This function selects service from secondary and tertiary navigation
	 * Parameters - eleSecondaryService , eleTertiaryService (Pass 'eleTertiaryService' as null if you want to click only secondary link not tertiary link)
	 * Date created - 2/4/15
	 * Developed by - Sneha Pansare
	 * Last Modified By - Nachiket Pawar
	 * Last Modified Date - 29 May 2015
	 * @throws ParseException 
	 ***************************************************************/
	
	public static  boolean SelectServiceFromSecondaryNavigation(WebElement eleSecondaryService, WebElement eleTertiaryService,WebDriver IDriver) throws HeadlessException, IOException, AWTException, ParseException
	{
				
		try
		{
			String sSecondaryServiceText="";

			Actions action = new Actions(IDriver);

			if(WaitForObject(eleSecondaryService,iObjTimeOut))
			{
				sSecondaryServiceText=eleSecondaryService.getText();

				System.out.println(sSecondaryServiceText);

				action.moveToElement(eleSecondaryService).build().perform();

				if(eleTertiaryService==null)
				{
					Report.TestPoint(testContext.getName(), "Click on Secondary link '"+sSecondaryServiceText+"'", "Secondary link '"+sSecondaryServiceText+"' clicked", "Secondary link '"+sSecondaryServiceText+"' clicked" , true);
					action.moveToElement(eleSecondaryService).click().build().perform();
					return true;
				}
				else
				{
					String sTertiaryServiceText="";

					if(WaitForObject(eleTertiaryService,iObjTimeOut))
					{
						sTertiaryServiceText= eleTertiaryService.getText();

						action.moveToElement(eleSecondaryService).moveToElement(eleTertiaryService).build().perform();
						Report.TestPoint(testContext.getName(), "Click on Tertiary link '"+sTertiaryServiceText+"'", "Tertiary link '"+sTertiaryServiceText+"' clicked", "Tertiary link '"+sTertiaryServiceText+"' clicked" , true);
						action.moveToElement(eleSecondaryService).moveToElement(eleTertiaryService).click().build().perform();
						return true;
					}
					else
					{
						Report.TestPoint(testContext.getName(), "Hover over Tertiary link '"+sTertiaryServiceText+"'", "Tertiary link '"+sTertiaryServiceText+"' should be present", "Tertiary link '"+sTertiaryServiceText+"' is not present" , true);
						return false;
					}
				}
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Desired Secondary Link Should be present", "Desired Secondary Link Should be present", "Desired Secondary Link is not present" , true);
				return false;
			}

		}catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Navigation", "Navigated successfully", "Navigation failed" , true);
			return false;
		}

	}

	/**************************************************************
	 * Function Name -  VerifyElementNotPresent
	 * Description- This function validates the absence of an element 
	 * Parameters - WebElement Object, String Name - object name as desired in reporting e.g "Link-Make a Payment", "Image - TV icon", etc
	 * Date created - 5 Feb 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date -
	 ***************************************************************/

	public static boolean VerifyElementNotPresent(WebElement Object, String ElementName) throws Exception
	{
		try 
		{
			Boolean blnObjPresent = WaitForObject(Object,iObjTimeOut);
			Report.ValidationPoint(testContext.getName(), ElementName+" should not be not present", String.valueOf(blnObjPresent).toLowerCase(), "false", true);		
			return true;
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Some Error", "true", "false" , true);
			return false;
		}
	}

	/**************************************************************
	 * Function Name -  SelectOptionFromDropDown(WebElement WebList, String Option)
	 * Description- This function selects an option from the Dropdown
	 * Parameters - WebElement WebList, String Option 
	 * Date created - 6th Feb 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	public static boolean SelectOptionFromDropDown(WebElement lstWebList, String sOption) throws HeadlessException, IOException, AWTException, ParseException
	//use id as identifier for dropdown objects while using method SelectOptionFromDropDown
	{
		try
		{
			if(lstWebList.isEnabled() || lstWebList.isDisplayed() || lstWebList.isSelected() )
			{
				Report.ValidationPoint(testContext.getName(), "Weblist "+lstWebList.getText()+" is Present", "true" , "true", true);			
				//			System.out.println("Weblist " +lstWebList.getText());
				
				Select OptionSelect = new Select(lstWebList);
				OptionSelect.selectByVisibleText(sOption);	
				Report.TestPoint(testContext.getName(), "Select Option", sOption +" option is selected", sOption +" option is selected", true);
				return true;	
			}
			else
			{
				Report.TestPoint(testContext.getName(), "Select Option", sOption +" option is selected", sOption +" option is NOT selected", true);
				return false;
			}
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Web List Present", lstWebList +"WebList present", lstWebList +"WebList is NOT present", true);
			return false;
		}
	}
	
	/**************************************************************
	 * Function Name -  SelectOptionFromDropDown(WebElement WebList, String Option)
	 * Description- Overloaded Function with Parallel execution support.This function selects an option from the Dropdown
	 * Parameters - WebElement WebList, String Option 
	 * Date created - 6th Feb 2015
	 * Developed by - Monica Mohabansi
	 * Last Modified By - Nachiket Pawar
	 * Last Modified Date - 29 May 2015
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	
	public static boolean SelectOptionFromDropDown(WebElement lstWebList, String sOption,String tContext) throws HeadlessException, IOException, AWTException, ParseException
	//use id as identifier for dropdown objects while using method SelectOptionFromDropDown
	{
		try
		{
			if(lstWebList.isEnabled() || lstWebList.isDisplayed())
			{
				Report.ValidationPoint(tContext, "Weblist "+lstWebList.getText()+" is Present", "true" , "true", false);			
				//			System.out.println("Weblist " +lstWebList.getText());
				Select OptionSelect = new Select(lstWebList);
				OptionSelect.selectByVisibleText(sOption);	
				Report.TestPoint(tContext, "Select Option", sOption +" option is selected", sOption +" option is selected", false);
				return true;	
			}
			else
			{
				Report.TestPoint(tContext, "Select Option", sOption +" option is selected", sOption +" option is NOT selected", true);
				return false;
			}
		}
		catch(Exception e)
		{
			Report.TestPoint(tContext, "Web List Present", lstWebList +"WebList present", lstWebList +"WebList is NOT present", true);
			return false;
		}
	}

	/**************************************************************
	 * Function Name -  ValidateHeadingOfPage(String sHeading)
	 * Description- This function validates the page headings with type h1
	 * Parameters - String sHeading 
	 * Date created - 12th Feb 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	public static boolean ValidateHeadingOfPage(String sHeading) throws HeadlessException, IOException, AWTException, ParseException
	{
		try
		{
			WebElement elmHeading = driver.findElement(By.xpath("//h1[contains(text(),'"+sHeading+"')]"));
			boolean bHeading = UI.WaitForObject(elmHeading, 50);
			Report.ValidationPoint(testContext.getName(),"Validate heading of page as "+sHeading, "true", String.valueOf(bHeading), true);
			return bHeading;

		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Validate Page title or heading","Page title or heading present","Page title or heading is NOTpresent",true);
			return false;
		}
	}

	/**************************************************************
	 * Function Name -  ValidateSubHeadings(String sSubHeading)
	 * Description- This function validates the page sub-headings with type h2
	 * Parameters - String sHeading 
	 * Date created - 3rd Feb 2015
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	public static boolean ValidateSubHeadings(String sSubHeading) throws HeadlessException, IOException, AWTException, ParseException
	{
		try
		{
			WebElement elmSubHeading = driver.findElement(By.xpath("//h2[text()='"+sSubHeading+"']"));
			boolean bSubHeading = UI.WaitForObject(elmSubHeading, 50);
			Report.ValidationPoint(testContext.getName(),"Validate sub-heading in page as "+sSubHeading, "true", String.valueOf(bSubHeading), true);
			return bSubHeading;
		}
		catch(Exception e)
		{
			Report.TestPoint(testContext.getName(), "Validate sub-heading in page","sub-heading present","sub-heading is NOTpresent",true);
			return false;
		}
	}



	/************************************************************** 
	 * Function Name -  ClickOrVerifyLinkUnderIWantToDropdown 
	 * Description- this function clicks or verifies link under I want to dropdown 
	 * Parameters - 1- Operation to perform (click or verify) 
				2- Name(should be exactly same as displayed under dropdown) of primary category 
	 * 				3- Name(should be exactly same as displayed under dropdown) of secondary category 
	 * 				   (This category is present only in case of slid having different accounts with different types 
	 * 					i.e - slid having one(or more) wireless and one(or more) u-verse) 
	 * 				4- Name of the link to be clicked 
	 * 
	 * Date created - 3/2/15 
	 * Developed by - Sneha Pansare 
	 * Last Modified By - Clint (Modified xpath for I want to dropdown) 
	 * Last Modified Date - 23rd April 2015 
	 * Last Modified By - Krutika (Modified xpath for Suppressed link) 
	 * Last Modified Date - 17th June 2015 
	 * Last Modified By - Sneha (Modified code for Suppressed link) 
	 * Last Modified Date - 22th Jan 2016
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/ 
 
	public static boolean ClickOrVerifyLinkUnderIWantToDropdown(String sOperationToPerform, String sPrimaryCategory, String sSecondaryCategory , String sLinkToClick, String sPresent) throws InterruptedException, HeadlessException, IOException, AWTException, ParseException 
	{ 
		//click on I want to dropdown 
		if(UI.WaitForObject(driver.findElement(By.xpath("//button[@id='ddShortcut']")), 150)) 
		{ 
			WebElement btnIwantTo= driver.findElement(By.xpath("//button[@id='ddShortcut']")); 
			WebDriver lDriver = UI.getDriver(testContext.getName()); 
			OR_AccountOverview oR_AccountOverview = PageFactory.initElements(lDriver, OR_AccountOverview.class); 
			Actions action= new Actions(lDriver); 
			action.moveToElement(btnIwantTo).click().build().perform(); 
			Thread.sleep(7000); 

			try 
			{ 
				if(driver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'"+sPrimaryCategory+"')]")).isDisplayed()) 
				{ 
					String id = driver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'"+sPrimaryCategory+"')]/parent::*/parent::*")).getAttribute("id"); 
					WebElement ePrimaryCategory= driver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'"+sPrimaryCategory+"')]")); 
					action.moveToElement(ePrimaryCategory).build().perform(); 
					Thread.sleep(10000); 

					boolean bSecondaryCategoryPresent=false; 
					if(sSecondaryCategory==null) 
					{ 

					} 
					else 
					{ 
						bSecondaryCategoryPresent=true; 
						try 
						{ 
							//*[contains(@id,'"+id+"')]//*[contains(@id,'midMenu')]//span[text()='"+sSecondaryCategory+"'] 
							if(driver.findElement(By.xpath("//*[contains(@id,'"+id+"')]//*[contains(@id,'midMenu')]//span[text()='"+sSecondaryCategory+"']")).isDisplayed()) 
							{ 
								WebElement eSecondaryCategory = driver.findElement(By.xpath("//*[contains(@id,'"+id+"')]//*[contains(@id,'midMenu')]//span[text()='"+sSecondaryCategory+"']")); 
								action.moveToElement(eSecondaryCategory).build().perform(); 
								Thread.sleep(6000); 
							} 
							else 
							{ 
								//System.out.println("Secondary Category '"+sSecondaryCategory+"' is NOT displayed"); 
								Report.TestPoint(testContext.getName(), "Hover over Secondary Category", "Hovered over Secondary Category '"+sSecondaryCategory+"'", "Secondary Category '"+sSecondaryCategory+"' is NOT displayed", true); 
							} 
						} 
						catch(Exception e) 
						{ 
							//System.out.println("Secondary Category '"+sSecondaryCategory+"' is NOT present on page"); 
							Report.TestPoint(testContext.getName(), "Hover over Secondary Category", "Hovered over Secondary Category '"+sSecondaryCategory+"'", "Secondary Category '"+sSecondaryCategory+"' is NOT present on page", true); 
						} 
					} 

					WebElement eLinkToClick = null; 

					try 
					{ 

						if(sPresent.equalsIgnoreCase("Suppressed")) 
						{ 

							//action.moveToElement(btnIwantTo).click().build().perform(); 
							Boolean beLinkToClick= WaitForObject(eLinkToClick,iObjTimeOut); 
							if(beLinkToClick==false)
							{
								Report.TestPoint(testContext.getName(), "Verify Link is suppressed",  "Suppressed", "Suppressed", true);
								return true; 
							}
							else
							{
								Report.TestPoint(testContext.getName(), "Verify Link is suppressed",  "Suppressed", "NOT Suppressed", true);
								return false; 
							}

						} 



					}
					catch(Exception e)
					{ 

						try
						{


							try 
							{ 
								if(bSecondaryCategoryPresent== true) 
								{ 
									eLinkToClick=driver.findElement(By.xpath("//*[contains(@id,'"+id+"')]//*[contains(@id,'midMenu')]//a[text()='"+sLinkToClick+"']")); 
								} 
								else 
								{ 
									eLinkToClick=driver.findElement(By.xpath("//li[contains(@id,'"+id+"')]//*[contains(@id,'ShortcutFlyoutMenu')]//a[text()='"+sLinkToClick+"']")); 
								} 
							} 
							catch(Exception e2){ 
								Report.TestPoint(testContext.getName(), "Click on the link", "Link '"+sLinkToClick+"' is clicked", "Link '"+sLinkToClick+"' is NOT displayed", true);
								return false;
							} 

							if(eLinkToClick.isDisplayed()) 
							{ 

								if((sOperationToPerform.toUpperCase().equals("CLICK"))) 
								{ 
									action.moveToElement(eLinkToClick).click().build().perform(); 
									Thread.sleep(8000); 
									//System.out.println("Link '"+sLinkToClick+"' Clicked"); 
									Report.TestPoint(testContext.getName(), "Click on the link", "Link '"+sLinkToClick+"' is clicked", "Link '"+sLinkToClick+"' is clicked", true); 
									Thread.sleep(2000); 
									return true; 
								} 
								else if((sOperationToPerform.toUpperCase().equals("VERIFY"))) 
								{ 
									try{ 
										Thread.sleep(8000); 
										//System.out.println("Link '"+sLinkToClick+"' is present"); 
										action.moveToElement(eLinkToClick).build().perform(); 
										Report.TestPoint(testContext.getName(), "Verify the link", "Link '"+sLinkToClick+"' is present", "Link '"+sLinkToClick+"' is present", true); 
										Thread.sleep(2000); 
										action.moveToElement(btnIwantTo).click().build().perform(); 
										return true; 

									} 
									catch(Exception e3){ 
										return false; 
									} 
								} 
								else 
								{ 
									return false; 
								} 

							} 

							/*//Added for DBD10906 - Swagata Das 30-09-2015 
					else if(testContext.getName().contains("DBD10906")) 
					{ 
						if(sLinkToClick.contains("parental") || sLinkToClick.contains("Download")) 
						{ 
						Boolean lnkParental = UI.VerifyElementNotPresent(oR_AccountOverview.lnkParentalControls, "Manage parental controls"); 
						Boolean lnkDownload = UI.VerifyElementNotPresent(oR_AccountOverview.lnkDownloadInternetSecurity, "Download Internet Security"); 
						if(!lnkParental && !lnkDownload) 
						{ 
							Report.ValidationPoint(testContext.getName(), "Validate following links are suppressed: Manage parental controls and Download Internet Security", "true", "true", true); 
							return true; 
						} 


						} 
						return true; 
					}*/ 
							else 
							{ 
								//System.out.println("Link '"+sLinkToClick+"' is NOT displayed"); 
								Report.TestPoint(testContext.getName(), "Click or verify link", "Clicked or verified link '"+sLinkToClick+"'", "Link '"+sLinkToClick+"' is NOT displayed", true); 
								return false; 
							} 



						} 
						catch(Exception e4)
						{


							//System.out.println("Link '"+sLinkToClick+"' is NOT Present on page"); 
							Report.TestPoint(testContext.getName(), "Click on link", "Clicked on link '"+sLinkToClick+"'", "Not Clicked on Link '"+sLinkToClick+"'", true); 
							return false; 
						}
						
						
					}
					return true;
				}else 
				{ 
					//System.out.println("Primary Category '"+sPrimaryCategory+"' is NOT displayed"); 
					Report.TestPoint(testContext.getName(), "Hover over Primary Category", "Hovered over Primary Category '"+sPrimaryCategory+"'", "Primary Category '"+sPrimaryCategory+"' is NOT displayed", true); 
					return false; 
				} 


			} 
			catch(Exception e){ 
				//System.out.println("Primary Category '"+sPrimaryCategory+"' is NOT Present on page"); 
				Report.TestPoint(testContext.getName(), "Hover over Primary Category", "Hovered over Primary Category '"+sPrimaryCategory+"'", "Primary Category '"+sPrimaryCategory+"' is NOT Present on page", true); 
				return false; 
			} 


		} 
		else 
		{ 
			//System.out.println("I Want to Dropdown is NOT displayed"); 
			Report.TestPoint(testContext.getName(), "Click on link under I Want to Dropdown", "Clicked on link", "I Want to Dropdown is NOT displayed", true); 
			return false; 
		} 


	} 
 
	private static void exit() { 
		// TODO Auto-generated method stub 
 
	}

	/**************************************************************
	 * Function Name -  isAttribtuePresent(WebElement element, String attribute)
	 * Description- This function validates if an object has a particular attribute
	 * Parameters - WebElement element, String attribute
	 * Date created - 9th March 2015
	 * Developed by - Monica Mohabansi 
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	
	public static boolean isAttributePresent(WebElement element, String attribute) 
		{
		    Boolean result = false;
		   try 
		   {
		        String value = element.getAttribute(attribute);
		        if (value != null){
		            result = true;
		        }
		    } catch (Exception e) {}
	
		    return result;
		}
		
	
	/**************************************************************
	 * Function Name -  VerifyLinksFromSecondaryNav(WebElement elmSecondaryLink, WebElement elmTertiaryLink)
	 * Description- This function validates links on secondary and tertiary navigation
	 * Parameters - WebElement elmSecondaryLink, WebElement elmTertiaryLink
	 * Date created - 12 March 2015
	 * Developed by - Rahul Bakde
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	
	public static boolean VerifyLinksFromSecondaryNav(WebElement elmSecondaryLink, WebElement elmTertiaryLink) throws HeadlessException, IOException, AWTException 
		{
		   try 
		   {
			   Actions builder=new Actions(UI.driver);
			   if (UI.WaitForObject(elmSecondaryLink, UI.iObjTimeOut)) {
				   //secondary link exist
				   Report.TestPoint(testContext.getName(), "Validate "+elmSecondaryLink.getText()+" link exist on secondary navigation", "True", "True", true);
				   builder.moveToElement(elmSecondaryLink).build().perform();;
				if (elmTertiaryLink==null) {
					//do nothing
				} else {
					if (UI.WaitForObject(elmTertiaryLink, 5)) {
						//tertiary link exist
						Report.TestPoint(testContext.getName(), "Validate "+elmTertiaryLink.getText()+" link exist on tertiary navigation", "True", "True", true);
					} else {
						Report.TestPoint(testContext.getName(), "Validate "+elmTertiaryLink.getText()+" link exist on tertiary navigation", "True", "False", true);
					}
				}   
			} else {
				Report.TestPoint(testContext.getName(), "Validate "+elmSecondaryLink.getText()+" link exist on secondary navigation", "True", "False", true);
			}  
			   return true;
		   }catch(Exception e)
			{
			   Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
				return false;
			}
		}
	
	/**************************************************************
	 * Function Name -  VerifyURLofPage
	 * Description- This function gets the url of any page. useful in build validations
	 * Parameters - sDomain
	 * Date created - 27th March
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	
	public static boolean VerifyURLofPage(String sDomain) throws HeadlessException, IOException, AWTException 
	{
		
	   try 
	   {
		   String sCurrentURL = driver.getCurrentUrl();
		   if(sCurrentURL.contains(sDomain))
		   {
			   return true;
		   }
		   else
		   {
			   return false;
		   }
		   
	   }
	   catch(Exception e)
		{
		   Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		   return false;
		}
	}
	
	/**************************************************************
	 * Function Name -  VerifyURLofPage
	 * Description- This function gets the url of any page. useful in build validations
	 * Parameters - sDomain
	 * Date created - 27th March
	 * Developed by - Swagata Das
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	
	public static boolean VerifyURLofPage(String sDomain,WebDriver IDriver) throws HeadlessException, IOException, AWTException 
	{
		
	   try 
	   {
		   String sCurrentURL = IDriver.getCurrentUrl();
		   if(sCurrentURL.contains(sDomain))
		   {
			   return true;
		   }
		   else
		   {
			   return false;
		   }
		   
	   }
	   catch(Exception e)
		{
		   Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
		   return false;
		}
	}
	
	/**************************************************************
	 * Function Name -  VerifyCategoriesUnderIWantToDropdown
	 * Description- this function verifies Primary or secondary category under I want to dropdown
	 * Parameters - 1 - sPrimaryCategory - Primary category name
	 * 				2 - sSecondaryCategory - Secondary category name(is you want to verify Primary category then pass this as null)
	 *  
	 * Date created - 4/28/15
	 * Developed by - Sneha Pansare
	 * Last Modified By - 
	 * Last Modified Date - 
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 * @throws ParseException 
	 ***************************************************************/
	
	public static boolean VerifyCategoriesUnderIWantToDropdown(String sPrimaryCategory, String sSecondaryCategory) throws InterruptedException, HeadlessException, IOException, AWTException, ParseException
	{
		boolean bCategoryVerified = false;
		//click on I want to dropdown
		if(UI.WaitForObject(driver.findElement(By.xpath("//button[@id='ddShortcut']")), 150))
		{
			WebElement btnIwantTo= driver.findElement(By.xpath("//button[@id='ddShortcut']"));

			Actions action= new Actions(driver);
			action.moveToElement(btnIwantTo).click().build().perform();
			Thread.sleep(4000);

			try
			{
				if(driver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'"+sPrimaryCategory+"')]")).isDisplayed())
				{
					String id = driver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'"+sPrimaryCategory+"')]/parent::*/parent::*")).getAttribute("id"); 
					WebElement ePrimaryCategory= driver.findElement(By.xpath("//li[contains(@id,'ShortcutMenu')]//span[contains(text(),'"+sPrimaryCategory+"')]"));
					action.moveToElement(ePrimaryCategory).build().perform();
					Thread.sleep(4000);

					
					if(sSecondaryCategory==null)
					{
						Report.ValidationPoint(testContext.getName(), "Verify Primary Category", "Primary Category '"+sPrimaryCategory+"' is displayed", "Primary Category '"+sPrimaryCategory+"' is displayed", true);
						bCategoryVerified =true;
					}
					else
					{
						try
						{
							if(driver.findElement(By.xpath("//*[contains(@id,'"+id+"')]//*[contains(@id,'midMenu')]//span[text()='"+sSecondaryCategory+"']")).isDisplayed())
							{
								WebElement eSecondaryCategory = driver.findElement(By.xpath("//*[contains(@id,'"+id+"')]//*[contains(@id,'midMenu')]//span[text()='"+sSecondaryCategory+"']"));
								action.moveToElement(eSecondaryCategory).build().perform();
								Thread.sleep(6000);
								Report.ValidationPoint(testContext.getName(), "Verify Secondary Category", "Secondary Category '"+sSecondaryCategory+"' is displayed", "Secondary Category '"+sSecondaryCategory+"' is displayed", true);
								bCategoryVerified =true;
							}
							else
							{
								//System.out.println("Secondary Category '"+sSecondaryCategory+"' is NOT displayed");
								Report.ValidationPoint(testContext.getName(), "Verify Secondary Category", "Secondary Category '"+sSecondaryCategory+"' is displayed", "Secondary Category '"+sSecondaryCategory+"' is NOT displayed", true);
								bCategoryVerified =false;
							}
						}
						catch(Exception e)
						{
							//System.out.println("Secondary Category '"+sSecondaryCategory+"' is NOT present on page");
							Report.ValidationPoint(testContext.getName(), "Verify Secondary Category", "Secondary Category '"+sSecondaryCategory+"' is displayed", "Secondary Category '"+sSecondaryCategory+"' is NOT present on page", true);
							bCategoryVerified =false;
						}
					}

					
					
				}
				else
				{
					//System.out.println("Primary Category '"+sPrimaryCategory+"' is NOT displayed");
					Report.ValidationPoint(testContext.getName(), "Verify Primary Category", "Primary Category '"+sPrimaryCategory+"' is displayed", "Primary Category '"+sPrimaryCategory+"' is NOT displayed", true);
					bCategoryVerified =false;
				}
			}
			catch(Exception e)
			{
					//System.out.println("Primary Category '"+sPrimaryCategory+"' is NOT Present on page");
					Report.ValidationPoint(testContext.getName(), "Verify Primary Category", "Primary Category '"+sPrimaryCategory+"' is displayed", "Primary Category '"+sPrimaryCategory+"' is NOT Present on page", true);
					bCategoryVerified =false;
			}

			action.moveToElement(btnIwantTo).click().build().perform();

		 }
		 else
		{
				//System.out.println("I Want to Dropdown is NOT displayed");
				Report.ValidationPoint(testContext.getName(), "Click on link under I Want to Dropdown", "Clicked on link", "I Want to Dropdown is NOT displayed", true);
				bCategoryVerified =false;
		}


		return bCategoryVerified;
	}
	
	/**************************************************************
	 * Function Name -  VerifyLinkNavigatestoNextPage(WebElement Link, String URLcontains)
	 * Description- This function validates the link navigates user to the specified page
	 * Parameters - WebElement Link, String URLcontains
	 * Date created - 22 May 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	
	public static boolean VerifyLinkNavigatestoNextPage(WebElement Link, String URLcontains) throws HeadlessException, IOException, AWTException 
		{
		   try 
		   {
			   Actions builder=new Actions(UI.driver);
			   if (UI.WaitForObject(Link, UI.iObjTimeOut)) {
				  
				   Report.TestPoint(testContext.getName(), "Validate "+Link.getText()+" link exists", "True", "True", true);
				   Thread.sleep(2000);
				   String sLink = Link.getText();
				   Link.click();
				   Thread.sleep(20000);
				   //Navigates to new page
				   String Url = driver.getCurrentUrl();
				   Report.OperationPoint(testContext.getName(), "Navigating to " +Url);
				   if(Url.contains(URLcontains))
				   {
					   Report.ValidationPoint(testContext.getName(), "Validate "+sLink+" link navigates to required page", "True", "True", true);
				   }
				   else
				   {
					   Report.ValidationPoint(testContext.getName(), "Validate "+sLink+" link navigates to required page", "True", "false", true);
				   }
			   }  
			 else 
			 {
				 Report.ValidationPoint(testContext.getName(), "Validate "+Link.getText()+" link exists", "True", "false", true);
			}
			   return true;
		   }catch(Exception e){
			   
			   Report.ValidationPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
			   return false;
		}
	}
	
	
	
	/**************************************************************
	 * Function Name -  VerifyLinkNavigatestoNewWindow(WebElement Link, String URLcontains)
	 * Description- This function validates the link navigates user to the specified page opened in new tab
	 * Parameters - WebElement Link, String URLcontains
	 * Date created - 22 May 2015
	 * Developed by - Krutika Kamdi
	 * Last Modified By - 
	 * Last Modified Date -
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws HeadlessException 
	 ***************************************************************/
	
	public static boolean VerifyLinkNavigatestoNewWindow(WebElement Link, String URLcontains) throws HeadlessException, IOException, AWTException 
		{
		   try 
		   {
			   Actions builder=new Actions(UI.driver);
			   if (UI.WaitForObject(Link, UI.iObjTimeOut)) {
				  
				   Report.TestPoint(testContext.getName(), "Validate "+Link.getText()+" link exists", "True", "True", true);
				   Link.click();
				   Thread.sleep(5000);
				   //Navigates to new Window
				   String winHandleBefore = driver.getWindowHandle();

				    // Switch to new window opened

				    for (String winHandle : driver.getWindowHandles()) {
				        driver.switchTo().window(winHandle);
				    }
				    //check current window url
				    Thread.sleep(10000);
				    
				    String sURL=driver.getCurrentUrl();
				    		
					if(sURL.contains(URLcontains))
					{
						Report.ValidationPoint(testContext.getName(), "Verify link navigates user to required URL in new window", "True", "True", true);	
					}
					else
					{
						Report.ValidationPoint(testContext.getName(), "Verify link navigates user to required URL in new window", "True", "false", true);	
					}
					//back to home phones page
					driver.switchTo().window(winHandleBefore);
				} 
			 else 
			 {
				 Report.TestPoint(testContext.getName(), "Validate "+Link.getText()+" link exists", "True", "false", true);
			}
			   return true;
		   }catch(Exception e)
			{
			   Report.TestPoint(testContext.getName(),"Some error has occured", "True",e.getMessage(), true);
				return false;
			}
		}

	public static void NavigateToAccountOverview() throws HeadlessException, IOException, AWTException 
	{
		Report.OperationPoint(testContext.getName(), "Navigate back to Dashboard");
		/*String selectAll = Keys.chord(Keys.ALT, Keys.SHIFT,"z");
		driver.findElement(By.tagName("html")).sendKeys(selectAll);*/
		WebDriver driver = UI.driver;
		OR_AccountOverview oR_AccountOverview = PageFactory.initElements(driver, OR_AccountOverview.class); 
		try
		{
			if(UI.WaitForObject(oR_AccountOverview.img3BarMenu,10))
			{
				oR_AccountOverview.img3BarMenu.click();
				Thread.sleep(2000);
				if(UI.WaitForObject(oR_AccountOverview.lnkAccountAndServices,10))
				{
				 oR_AccountOverview.lnkAccountAndServices.click();{}
				 if(UI.WaitForObject(oR_AccountOverview.txtWelComeBack, 50)){return;}
				 else
				 Report.ValidationPoint(testContext.getName(), "Failed to Navigate to Dashboard", "true", "false", true);
				}
				else Report.ValidationPoint(testContext.getName(), "Click on link Account and Services", "true", "false", true);
			}
			else Report.ValidationPoint(testContext.getName(), "3 Bar Navigation", "true", "false", true);
		}
		catch(Exception e)
		{
			Report.ValidationPoint(testContext.getName(), "Failed to click on link Account And Services from global navigation", "true", "false", true);
		}
		
	}
	
	
}


