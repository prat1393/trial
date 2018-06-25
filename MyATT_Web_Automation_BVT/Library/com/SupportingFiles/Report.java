package com.SupportingFiles;
import com.TestCase.*;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

@SuppressWarnings("all")
public class Report {
	
	//Global variable declaration
	public static String reportFile, iterStatus, testStatus, screenshotFile, exeBy, fmtRepData, imgSize, sMyATTEnvURL;
	private static Integer iter, itrPassPts, itrFailPts;
	static Hashtable<String, String> tcReport_TC_Map = new Hashtable<String, String>();
	static Hashtable<String, String> openReport = new Hashtable<String, String>();
	static Hashtable<String, String> closeReport = new Hashtable<String, String>();
	static Hashtable<String, String> tcChildReport_TC_Map = new Hashtable<String, String>();
	static Hashtable<String, Integer> stepCount = new Hashtable<String, Integer>();
	static Hashtable<String, Long> stepTimer = new Hashtable<String, Long>();
	static DecimalFormat twoDecimalDigits = new DecimalFormat ("0.00");
	public static int itestCasesCount=0;
	public static String consolidateData = "" ;
	public static Hashtable<String,ITestContext> testContext_Map = new Hashtable<String,ITestContext>();
	public static Hashtable<String, WebDriver> sTestName_Driver = new Hashtable<String, WebDriver>();
	
	public static String newtimeStamp = new SimpleDateFormat(
			"MM-dd-yyyy hh.mm.ss").format(Calendar.getInstance().getTime());

	
	private static void overwriteFile(String fileName, String data) throws IOException {

		File f = null; 
		String folderPath = IO.sFolder + "\\Reports\\" + fileName;
		
		if (!data.substring(0, 17).equals("<font color=blue>")) {  // For TC Report 
			
			if (ReadEnvPropFile.ReportsInFolders().equals("No")) {
				folderPath = IO.sFolder + "\\Reports\\Execution_" + newtimeStamp;
			}
			f = new File(folderPath);
			if (!(f.exists() && f.isDirectory())) {  f.mkdir();  }
			
			reportFile = folderPath + "\\" + fileName + "_" + newtimeStamp + ".html";
			tcReport_TC_Map.put(fileName, reportFile);
			
		} else { // For Iteration Report
			folderPath = IO.sFolder + "\\Reports\\Temp";
			f = new File(folderPath); if (!(f.exists() && f.isDirectory())) {  f.mkdir();  }
			reportFile = folderPath + "\\" + fileName + ".html";
		}
		
		File file = new File(reportFile);
		FileWriter FW = new FileWriter(file, false);
		FW.write(data);
		FW.close();
	}
	
	private static void appendFile(String fileName, String data) {
		String reportFile = IO.sFolder + "\\Reports\\Temp\\" + fileName + ".html";
		try {
			File file = new File(reportFile);
            BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
			output.write("\n" + data);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String readFile(String fileName, Boolean withHead) {
		String reportFile = fileName;
		String data = "";
        try {
        	File file = new File(reportFile);
            BufferedReader reader = new BufferedReader(new FileReader(file));
    		String line = "";
            while((line = reader.readLine()) != null) {
                data += line + "\r\n";
            }
            reader.close();
          } catch ( IOException e ) {
             e.printStackTrace();
          }
		if (withHead) {	data = StringUtils.substringAfter(data, "</script></head>"); }
        return data;
	}

	public static void CloseIteration(String file) throws IOException, ParseException {
	
		String data = readFile(IO.sFolder + "\\Reports\\Temp\\" + file + ".html", false);
		
		String time = StringUtils.substringBetween(data, "<font color=blue>", "</font>"); Long stTime = Long.valueOf(time);
		Long entime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - stTime);
		data = StringUtils.replace(data, "<font color=blue>"+time+"</font>", "<font color=blue>"+entime+"</font>");
		Integer passPts = StringUtils.countMatches(data, "<Font COLOR=Green>Pass</Font>");
		Integer failPts = StringUtils.countMatches(data, "<Font COLOR=Red>Fail</Font>");
		iterStatus = (failPts>0) ? iterStatus = "<Font COLOR=Red>Fail</Font>" : "<Font COLOR=Green>Pass</Font>";
		data = data.replaceAll("ITR_STATUS", iterStatus);
		data = data.replace("ITR_PASSPTS", passPts.toString());
		data = data.replace("ITR_FAILPTS", failPts.toString());
		data = data.replace("ITR_EXE_TIME", DurationFormatUtils.formatDuration(entime*1000, "mm:ss") + " Mins");
		data = data + "</table><br/></div>";
		overwriteFile(file, data);
	} 
    
	
	public static void Consolidation(String file) throws IOException, ParseException {

		String tcData, itrData, tcName, itrInfo, testStatus, tcReportFile;
		Integer exeTime = 1;   /**Changed temp for Execution **/

		if(itestCasesCount == 0){
			XmlSuite suite = Report.testContext_Map.get(file).getSuite().getXmlSuite();
			List<XmlTest> tests = suite.getTests();
			itestCasesCount = tests.size();
			System.out.println("Total test case count"+itestCasesCount);

		}

		itestCasesCount--;
		System.out.println(itestCasesCount);

		tcName = tcChildReport_TC_Map.get(file);
		tcReportFile = tcReport_TC_Map.get(tcName);
		testStatus = "Pass"; tcData = readFile(tcReportFile, false);
		
		itrData = readFile(IO.sFolder + "\\Reports\\Temp\\" + file + ".html", false);
		String time = StringUtils.substringBetween(itrData, "<font color=blue>", "</font>"); 
		exeTime = exeTime + Integer.valueOf(time);
		itrData = StringUtils.substringAfter(itrData, "</style></head>");
		
		Integer failIdx = StringUtils.indexOf(itrData, "<td width=\"10%\"><Font COLOR=Red>Fail</Font></td>");
		if ((failIdx > 0) && (testStatus.equals("Pass"))) { testStatus = "Fail"; }
		tcData = tcData + itrData;
		
		String sTCStatus = StringUtils.substringBetween(tcData, "</td><td width=\"12%\">Status: ", "</td><td width=\"25%\">");
		String sTCStatus_Replace = "<Font COLOR=Red></Font><Font COLOR=Green>Pass</Font>";
		if (testStatus.equals("Fail")) { sTCStatus_Replace = "<Font COLOR=Red>Fail</Font><Font COLOR=Green></Font>"; }
		tcData = StringUtils.replace(tcData, sTCStatus, sTCStatus_Replace);
		
		String sTCExeTime = StringUtils.substringBetween(tcData, "<td width=\"22%\">Total Execution Time: ", "</td><td width=\"auto\">Executed By:");
		String sOldTime = sTCExeTime.replace(" Mins", ""); sOldTime = sOldTime.replace("EXE_TIME", "");
		
		String sComTime = DurationFormatUtils.formatDuration(exeTime*1000, "HH:mm:ss");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); 
		
		if (!sOldTime.isEmpty()) {
			Date date2 = timeFormat.parse(sOldTime); 
			Calendar instance = GregorianCalendar.getInstance(); instance.setTime(date2);
			instance.add(GregorianCalendar.MILLISECOND, exeTime*1000);
			sComTime = timeFormat.format(instance.getTime());
		}
		
		tcData = StringUtils.replace(tcData, sTCExeTime, sComTime + " Mins");
		overwriteFile(tcName, tcData);
	
		consolidateData = consolidateData + tcData;

		consolidateData = consolidateData.replaceAll("<Title>"+StringUtils.substringBetween(consolidateData, "<Title>", "</Title>")+"</Title>", "<Title>Consolidated_report</Title>");

		//String sConsolidateFileName = IO.sFolder + "\\Reports\\Consolidated_reports\\consolidate.html";

		String sTotalExecutionDetails=null;
		if(itestCasesCount==0)
		{
			int iPassedTests = StringUtils.countMatches(consolidateData, "Status: <Font COLOR=Red></Font><Font COLOR=Green>Pass</Font>");
			int iFailedTests = StringUtils.countMatches(consolidateData, "Status: <Font COLOR=Red>Fail</Font>");
		

			String sDate1 = new SimpleDateFormat(
					"MM-dd-yyyy").format(Calendar.getInstance().getTime());

			
			sTotalExecutionDetails = "<html><h3><center><font color=\"blue\">Execution Summery</font></center></h3><style>"+
					"table.exeDetailsTable{border: 1px solid #E8782B;width: 100%;border-collapse: collapse; background-color: #FFA740;}"+
					"exeDetails.td{border: 1px solid #E8782B; background-color: #FFA740;}</style>&nbsp;&nbsp;&nbsp;<table class=\"exeDetailsTable\""+
					"width=\"800\"><tr><td class=\"exeDetails\" width=\"25%\"><b><font color=\"black\">Total Test cases: "+(iFailedTests+iPassedTests)+
					"</font></b></td><td class=\"exeDetails\" width=\"25%\"><b><Font color=\"Green\">Passed Tests : "+iPassedTests+"</Font></b></td>"+
					"<td class=\"exeDetails\" width=\"25%\"><b><Font color=\"Red\">Failed Tests : "+iFailedTests+"</Font></b></td>"+
					"<td class=\"exeDetails\" width=\"25%\"><b><font color=\"black\">Execution date : "+sDate1+"</font></b></td>"+
					"</tr></table>&nbsp;&nbsp;&nbsp;<h3><center><font color=\"blue\">Execution Details</font></center></h3>&nbsp;&nbsp;</html>";

			consolidateData = sTotalExecutionDetails + consolidateData;

		}

		File consolidatedReportDir = new File(IO.sFolder+"\\Reports\\Consolidated_reports");

		if(!consolidatedReportDir.exists()){
			consolidatedReportDir.mkdir();
		}

		File sConsolidateFileName = new File(consolidatedReportDir,"consolidate.html");
		if(sConsolidateFileName.exists())
		{
			sConsolidateFileName.delete();
		}

		FileWriter FW = new FileWriter(sConsolidateFileName, true);
		FW.write(consolidateData);
		FW.close();

		if(itestCasesCount == 0)
		{

			String finaltimeStamp = new SimpleDateFormat(
					"MM-dd-yyyy hh.mm.ss").format(Calendar.getInstance().getTime());

			File renameFile = new File(IO.sFolder + "\\Reports\\Consolidated_reports\\consolidation_"+finaltimeStamp+".html");

			//sConsolidateFileName.renameTo(renameFile);
		}
 }
	
	
	

	private static String imgToBase64() {

		File file = new File(screenshotFile); String imgData = null;
	    try {          
	    	FileInputStream imageInFile = new FileInputStream(file);
	    	byte imageData[] = new byte[(int) file.length()];
	    	imageInFile.read(imageData); imgData = Base64.encodeBase64String(imageData); imageInFile.close();
	    } catch (FileNotFoundException e) {
	    	System.out.println("Image not found" + e);
	    } catch (IOException ioe) {
	    	System.out.println("Exception while reading the Image " + ioe);
	    }
	    return imgData;
    }
	
	private static String TakeScreenshot(WebDriver lDriver) throws IOException, HeadlessException, AWTException  { 
	    //String path;
		String screenshotBase64 = "";
		 if(((RemoteWebDriver)lDriver).getSessionId() != null)
			{
    	      screenshotBase64 = ((TakesScreenshot)lDriver).getScreenshotAs(OutputType.BASE64);
			}
    	   //File source = ((TakesScreenshot)UI.driver).getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(source, new File(screenshotFile));
	        
		//return imgToBase64();
		return screenshotBase64;
	}
	
public static void Start(String tcName) throws IOException {
		
		if (!openReport.containsKey(tcName)) {
			
			String strHTML = ""; String dt = new SimpleDateFormat("MM-dd-yyyy hh:mm").format(new Date());
			strHTML = strHTML + "<html><head><Title>" + tcName + "</Title>"
					+ "<script type=\"text/javascript\">function toggle2(id, link) {var e = document.getElementById(id);"
					+ "if (e.style.display == '') {e.style.display = 'none';link.innerHTML = '[ + ]';} else {e.style.display = ''; "
					+ "link.innerHTML = '[ - ]'; }  }var PopupImageContainer = new Image();var PopupImageCaption = new String();"
					+ "var PopupImageSRC = new String();function PopImage(caption, imagesrc) {if( length.imagesrc < 1 ) { return; }"
					+ "var loadDelay = PopupImageSRC.length ? 1 : 750;PopupImageSRC = imagesrc;PopupImageCaption = caption;"
					+ "PopupImageContainer.src = PopupImageSRC;setTimeout(\"PopupImageDisplay()\",loadDelay);} function PopupImageDisplay() "
					+ "{var iw = parseInt(PopupImageContainer.width);var ih = parseInt(PopupImageContainer.height);"
					+ "var properties = 'height=' + 600 + ',width=' + 900 + ',resizable=yes,location=no,scrollbars=yes,left=500,top=100';"
					+ "var picture = window.open('','',properties);picture.document.writeln('<html><head><title>' + PopupImageCaption + "
					+ "'</title>');picture.document.write('<img src=\"' + PopupImageSRC + '\" width=\"' + iw + '\" height=\"' + ih + '\" "
					+ "border=\"0\">');picture.document.writeln('<'+'/center><'+'/body><'+'/html>');}</script><style>#itrdata, "
					+ "#itrhead {    font-family: \"Trebuchet MS\";    width: 100%;    border-collapse: collapse;}"
					+ "#itrhead td {    font-size: 0.80em;	font-weight: bold;    text-align: left;	"
					+ "padding: 1px 5px 1px 5px;	border: 1px solid #E8782B;    background-color: #FFA740;    color: #ffffff;}#top td "
					+ "{font-size: 0.95em;	font-weight: bold; text-align: left; padding: 1px 5px 1px 5px;	background-color: #ffffff; "
					+ "color: #005B95;}#itrdata td, #itrdata th {    font-size: 0.75em;    border: 1px solid #E8782B;    "
					+ "padding: 1px 5px 1px 5px;}#itrdata th {    font-size: 0.70em;    text-align: left;    padding-top: 1px;    "
					+ "padding-bottom: 1px;    background-color: #FFBC64;}#itrdata tr.alt td {    color: #000000;    background-color: "
					+ "#FFE5BE;}</style></head>"
					+ "<table id=\"top\"><tr><td width=\"22%\">Test Name: "+ tcName +"</td><td width=\"12%\">"
					+ "Status: <Font COLOR=Red>Fail</Font><Font COLOR=Green></Font></td><td width=\"25%\"><b>Execution Date: "+ dt
					+ "</td><td width=\"22%\">Total Execution Time: EXE_TIME</td><td width=\"auto\">Executed By: "+ exeBy
					+ "</td></tr></table>&nbsp;";
//					+ "<strong>&nbsp;&nbsp;<Font COLOR=#005B95 font-family=Trebuchet MS font-weight = bold size=3>Environment URL: "+ sMyATTEnvURL +"</Font></strong>";
			
			overwriteFile(tcName, strHTML);
			testStatus = "Pass";
			openReport.put(tcName, "Y");
		}
	}
	
	
	public static void PrintIteration(String file, String brwsrName, String iterNum) throws IOException {
		
		stepCount.put(file,  1); stepTimer.put(file, System.currentTimeMillis());
		Hashtable<String, String> sTestParams = IO.getTestData(file);
		String strHTML = "<font color=blue>" + System.currentTimeMillis() + "</font>"; 
			strHTML = strHTML + "<html><head><Title>" + file + "</Title>"
					+ "<script type=\"text/javascript\">function toggle2(id, link) {var e = document.getElementById(id);"
					+ "if (e.style.display == '') {e.style.display = 'none';link.innerHTML = '[ + ]';} else {e.style.display = ''; "
					+ "link.innerHTML = '[ - ]'; }  }var PopupImageContainer = new Image();var PopupImageCaption = new String();"
					+ "var PopupImageSRC = new String();function PopImage(caption, imagesrc) {if( length.imagesrc < 1 ) { return; }"
					+ "var loadDelay = PopupImageSRC.length ? 1 : 750;PopupImageSRC = imagesrc;PopupImageCaption = caption;"
					+ "PopupImageContainer.src = PopupImageSRC;setTimeout(\"PopupImageDisplay()\",loadDelay);} function PopupImageDisplay() "
					+ "{var iw = parseInt(PopupImageContainer.width);var ih = parseInt(PopupImageContainer.height);"
					+ "var properties = 'height=' + 600 + ',width=' + 900 + ',resizable=yes,location=no,scrollbars=yes,left=500,top=100';"
					+ "var picture = window.open('','',properties);picture.document.writeln('<html><head><title>' + PopupImageCaption + "
					+ "'</title>');picture.document.write('<img src=\"' + PopupImageSRC + '\" width=\"' + iw + '\" height=\"' + ih + '\" "
					+ "border=\"0\">');picture.document.writeln('<'+'/center><'+'/body><'+'/html>');}</script><style>#itrdata, "
					+ "#itrhead {    font-family: \"Trebuchet MS\";    width: 100%;    border-collapse: collapse;}"
					+ "#itrhead td {    font-size: 0.80em;	font-weight: bold;    text-align: left;	"
					+ "padding: 1px 5px 1px 5px;	border: 1px solid #E8782B;    background-color: #FFA740;    color: #ffffff;}#top td "
					+ "{font-size: 0.95em;	font-weight: bold; text-align: left; padding: 1px 5px 1px 5px;	background-color: #ffffff; "
					+ "color: #005B95;}#itrdata td, #itrdata th {    font-size: 0.75em;    border: 1px solid #E8782B;    "
					+ "padding: 1px 5px 1px 5px;}#itrdata th {    font-size: 0.70em;    text-align: left;    padding-top: 1px;    "
					+ "padding-bottom: 1px;    background-color: #FFBC64;}#itrdata tr.alt td {    color: #000000;    background-color: "
					+ "#FFE5BE;}</style></head>"
					+ "<table id=\"itrhead\"><tr><td width=\"15%\">Iteration "+iterNum+"</td><td width=\"15%\">"+"Data Type: "+IO.GetParamVal(sTestParams,"DataType")+"</td><td width=\"15%\">" 
					+ brwsrName + "</td><td width=\"10%\">"
					+ "ITR_STATUS</td><td width=\"19%\">Execution Time: ITR_EXE_TIME</td><td width=\"19%\">Validation Points "
					+ "(Pass - <Font COLOR=Green>ITR_PASSPTS</Font> / Fail- <Font COLOR=Red>ITR_FAILPTS</Font> )</td><td>&nbsp;&nbsp;&nbsp;<a href=\"#\" "
					+ "onclick=\"toggle2('content"  + file + "', this)\">[ + ]</a></td></tr></table>&nbsp;"
					+ "<div id=\"content"  + file + "\" style=\"display:none\"><table id=\"itrdata\"><tr><th width=\"2.5%\">"
					+ "#</th><th width=\"4%\">Timer</th><th width=\"30%\">Step Details</th><th width=\"30%\">Expected</th><th width=\"30%\">Actual</th>"
					+ "<th width=\"3.5%\">Status</th></tr>";

		overwriteFile(file, strHTML);
		String tcName = file.substring(0, file.indexOf("-"));
		//System.out.println();
		
		tcChildReport_TC_Map.put(file, tcName);
		/*if (closeReport.containsKey(tcName)) {
			String oldVal = closeReport.get(tcName); 
			closeReport.remove(tcName); 
			closeReport.put(tcName, oldVal+"|"+file); 
		} else {
			closeReport.put(tcName, file);
		}*/
		iterStatus = "Pass";
	}
	
	public static void ValidationPoint(String iterFile, String validDtls, String validEXP, String validACT, Boolean takeScrnShot) throws HeadlessException, IOException, AWTException  {
				
		String stepStatus = "Fail";
		Hashtable<String, String> sTestParams = IO.getTestData(iterFile);
		if (validEXP.equalsIgnoreCase(validACT)) { stepStatus = "Pass";  }
		String stepHTML = "<Font COLOR=Red>"+stepStatus+"</Font>"; String baseSTR = ""; String strHTML="";
		String repMsg = validDtls.replace("'",  ""); repMsg = validDtls.replace("\\r",  ""); repMsg = validDtls.replace("\\n",  "");
		repMsg = validDtls.replace("\"",  "");
		
		
		
		UI.printMsg(iterFile.split("-")[0] + "::" + iterFile.split("-")[2] + "::"+ IO.GetParamVal(sTestParams,"DataType") + "::" + validDtls);

		if (stepStatus.equals("Pass")) { stepHTML = "<Font COLOR=Green>"+stepStatus+"</Font>";		}
		if (takeScrnShot) {	
			WebDriver driverSS = UI.getDriver(iterFile); baseSTR = TakeScreenshot(driverSS); 
			validDtls = validDtls.replaceAll("\"", "");
			stepHTML = "<a href=\"#\" alt=\"Click For Screenshot\""
			+ " onclick=\"PopImage('"+ validDtls.replace("'",  "") + " [" + stepStatus + "]', 'data:image/png;base64," + baseSTR + "')\" />"
			+ stepHTML + "</a>";
		}		
		Integer stepCtr = ((Integer)stepCount.get(iterFile));
		long stepTime = System.currentTimeMillis()-(Long) stepTimer.get(iterFile);
		String stpTmr = DurationFormatUtils.formatDuration(stepTime, "mm:ss:SSS");
		strHTML = (stepCtr%2 == 0) ? "<tr>" : "<tr class=\"alt\">"; 
		strHTML = strHTML + "<td>"+stepCtr+"</td><td>"+stpTmr+"</td>"
				+ "<td>"+validDtls+"</td><td>"+validEXP+"</td><td>"+validACT+"</td><td>"+stepHTML+"</td></tr>";
		appendFile(iterFile, strHTML); stepCtr++;
		stepCount.put(iterFile, stepCtr);
		//stepTimer.put(iterFile, System.currentTimeMillis());
	}
	
	
	public static void TestPoint(String iterFile, String validDtls, String validEXP, String validACT, Boolean takeScrnShot) throws HeadlessException, IOException, AWTException  {
		String stepStatus = "Fail"; if (validEXP.equalsIgnoreCase(validACT)) { stepStatus = "Pass";  }
		String stepHTML = "<Font COLOR=Red>"+stepStatus+"</Font>"; String baseSTR = ""; String strHTML="";
		String repMsg = validDtls.replace("'",  ""); repMsg = validDtls.replace("\\r",  ""); repMsg = validDtls.replace("\\n",  "");
		repMsg = validDtls.replace("\"",  "");
		Hashtable<String, String> sTestParams = IO.getTestData(iterFile);
		
	//	UI.printMsg(iterFile.split("-")[0] + "::" + iterFile.split("-")[2] + "::" + validDtls);
		UI.printMsg(iterFile.split("-")[0] + "::" + iterFile.split("-")[2] + "::"+ IO.GetParamVal(sTestParams,"DataType") + "::" + validDtls);
		
		if (stepStatus.equals("Pass")) { stepHTML = "<Font COLOR=Green>"+stepStatus+"</Font>";		}
		if (takeScrnShot) {	
			WebDriver driverSS = UI.getDriver(iterFile);  
			baseSTR = TakeScreenshot(driverSS); 
			validDtls = validDtls.replaceAll("\"", "");
			stepHTML = "<a href=\"#\" alt=\"Click For Screenshot\""
			+ " onclick=\"PopImage('"+ validDtls.replace("'",  "") + " [" + stepStatus + "]', 'data:image/png;base64," + baseSTR + "')\" />"
			+ stepHTML + "</a>";
		}		
		
		Integer stepCtr = ((Integer)stepCount.get(iterFile));
		long stepTime = System.currentTimeMillis()-(Long) stepTimer.get(iterFile);
		String stpTmr = DurationFormatUtils.formatDuration(stepTime, "mm:ss:SSS");
		strHTML = (stepCtr%2 == 0) ? "<tr>" : "<tr class=\"alt\">"; 
		strHTML = strHTML + "<td>"+stepCtr+"</td><td>"+stpTmr+"</td>"
				+ "<td>"+validDtls+"</td><td>"+validEXP+"</td><td>"+validACT+"</td><td>"+stepHTML+"</td></tr>";
		appendFile(iterFile, strHTML); stepCtr++;
		stepCount.put(iterFile, stepCtr);
		if (stepStatus.equals("Fail")) { 
			// Check if test script fails because of login issue
		 	if(validACT.toUpperCase().contains("FAILED TO LOGIN") || validACT.toUpperCase().contains("FAILED TO LAUNCH") ||
		 			validACT.toUpperCase().contains("logout button") || validACT.toUpperCase().contains("logged out")){
				try {
					LaunchAndLogout.CloseApplication(iterFile);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
			Assert.fail(validDtls);
		}
	}
	
	public static void OperationPoint(String iterFile, String opnDtls) throws HeadlessException, IOException, AWTException  {

		Hashtable<String, String> sTestParams = IO.getTestData(iterFile);
		opnDtls = opnDtls.replaceAll("\"", "");	opnDtls = opnDtls.replaceAll("'", "");
	//	UI.printMsg(iterFile.split("-")[0] + "::" + iterFile.split("-")[2] + "::" + opnDtls);
		UI.printMsg(iterFile.split("-")[0] + "::" + iterFile.split("-")[2] + "::"+ IO.GetParamVal(sTestParams,"DataType") + "::" + opnDtls );
		Integer stepCtr = ((Integer)stepCount.get(iterFile));
		long stepTime = System.currentTimeMillis()-(Long) stepTimer.get(iterFile);
		String stpTmr = DurationFormatUtils.formatDuration(stepTime, "mm:ss:SSS");
		String strHTML = ((stepCtr % 2)==0) ? "<tr class=\"alt\">" : "<tr>";
		strHTML = strHTML + "<td><Font COLOR=Grey>"+ stepCtr +"</font></td><td><Font COLOR=Grey>"+stpTmr+"</font></td>"
				+ "<td colspan=4><Font COLOR=Grey>"+ opnDtls +"</font></td></tr>";
		appendFile(iterFile, strHTML); stepCtr++;
		stepCount.put(iterFile, stepCtr);
		//stepTimer.put(iterFile, System.currentTimeMillis());
	}
	
	public static void BipmapPoint(String iterFile, String validDtls, boolean status, String diffMsg, String validEXP, String validACT, Boolean takeScrnShot) throws HeadlessException, IOException, AWTException  {
		
		String stepStatus = "Fail"; if (status) { stepStatus = "Pass";  }
		String stepHTML = "<Font COLOR=Red>"+stepStatus+"</Font>"; String baseSTR = ""; String strHTML="";
		String repMsg = validDtls.replace("'",  ""); repMsg = validDtls.replace("\\r",  ""); repMsg = validDtls.replace("\\n",  "");
		repMsg = validDtls.replace("\"",  "");

		if (stepStatus.equals("Pass")) { stepHTML = "<Font COLOR=Green>"+stepStatus+"</Font>";		}
		if (takeScrnShot) {	
			WebDriver driverSS = UI.getDriver(iterFile);  baseSTR = TakeScreenshot(driverSS); 
			validDtls = validDtls.replaceAll("\"", "");
			stepHTML = "<a href=\"#\" alt=\"Click For Screenshot\""
			+ " onclick=\"PopImage('"+ validDtls.replace("'",  "") + " [" + stepStatus + "]', 'data:image/png;base64," + baseSTR + "')\" />"
			+ stepHTML + "</a>";
		}
		validEXP = "<a href=\"#\" onclick=\"PopImage('Original', 'data:image/png;base64," + validEXP + "')\" />Original</a>";
		validACT = "<a href=\"#\" onclick=\"PopImage('Captured', 'data:image/png;base64," + validACT + "')\" />Captured</a>    <b>["+diffMsg+"]</b>";
		
		Integer stepCtr = ((Integer)stepCount.get(iterFile));
		long stepTime = System.currentTimeMillis()-(Long) stepTimer.get(iterFile);
		String stpTmr = DurationFormatUtils.formatDuration(stepTime, "mm:ss:SSS");
		strHTML = (stepCtr%2 == 0) ? "<tr>" : "<tr class=\"alt\">"; 
		strHTML = strHTML + "<td>"+stepCtr+"</td><td>"+stpTmr+"</td>"
				+ "<td>"+validDtls+"</td><td>"+validEXP+"</td><td>"+validACT+"</td><td>"+stepHTML+"</td></tr>";
		appendFile(iterFile, strHTML); stepCtr++;
		stepCount.put(iterFile, stepCtr);
		//stepTimer.put(iterFile, System.currentTimeMillis());
	}
	
	public static void ReportFailure(String iterFile, String strMsg) throws HeadlessException, IOException, AWTException  { 
		WebDriver driverSS = UI.getDriver(iterFile);  String baseSTR = TakeScreenshot(driverSS); 
		String strHTML = "<table width=\"965\" border=\"1\" style=\"font-family:Calibri; font-size:16px;vertical-align:middle;\"><tr style=\"background-color:#FF0000;\"><td>" + strMsg + "</td></tr></table>&nbsp;&nbsp;"
				+ "<table width=\"965\" border=\"1\"><tr align=\"center\"><td><br/><img width=\"900\" height=\"500\" align=\"center\" alt=\"embedded image\" src=\"data:image/png;base64," + baseSTR + "\" /></td></tr>"
				+ "</table>&nbsp;&nbsp;";
		appendFile(iterFile, strHTML);
		Assert.fail(strMsg);
	}
	
	public static void cleanTemp() throws IOException {
		File f = new File(IO.sFolder + "\\Reports\\Temp");	FileUtils.cleanDirectory(f); 
	}
}