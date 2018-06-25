package com.SupportingFiles;
import com.TestCase.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Hashtable;
import java.util.Set;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;

@SuppressWarnings("all")
public class IO {

	//Variable declaration
	public static String sFolder,sImageFolder;
	public static String sTestDataFile;
	public static String sLogFile, sTcName, sBrwsrName, sActiveSheet;
	//public static Hashtable<String, String> sTestParams = new Hashtable<String, String>();
	public static Hashtable<String, Hashtable<String, String>> sTestName_TestData = new Hashtable<String, Hashtable<String, String>>();

	
	public static void putTestData(String testName, Hashtable<String, String> testData) {
		IO.sTestName_TestData.put(testName, testData);
	}
	
	public static Hashtable<String, String> getTestData(String testName) {
		return IO.sTestName_TestData.get(testName);
	}
	
	public static Hashtable<String, String> Read_Iter_Data(String sEnv, String sTcName, Integer iIter) throws IOException {
		sActiveSheet=sEnv;
		Hashtable<String, String> testParam = new Hashtable<String, String>();
		InputStream inp = new FileInputStream(sTestDataFile); 

		//local variable
		int iStRw = 0; 
		int iIterCnt = 0; 
		int iDtRw;
		
		String value, sPVal=null;

		Workbook oWb = new XSSFWorkbook(inp);
		try {
//			Sheet oSheet1 = oWb.getSheetAt(0); 
			Sheet oSheet1 = oWb.getSheet(sActiveSheet);
			for (Row oRw : oSheet1) {
				String sStr = (oRw.getCell(1) == null) ? "NA" : oRw.getCell(1).getStringCellValue();
				String sBatchRun = (oRw.getCell(0) == null) ? "NA" : oRw.getCell(0).getStringCellValue();
				if (sStr.equals(sTcName) && sBatchRun.equals("Y")) { 
					iStRw = oRw.getCell(1).getRowIndex(); 
					iDtRw = iStRw+1;

                    String sTCCol = (oSheet1.getRow(iDtRw).getCell(1) == null) ? "" : oSheet1.getRow(iDtRw).getCell(1).getStringCellValue();
                    String sBrwsrCol = (oSheet1.getRow(iDtRw).getCell(2) == null) ? "" : oSheet1.getRow(iDtRw).getCell(2).getStringCellValue();
                    
                    while( sTCCol.isEmpty() && (!sBrwsrCol.isEmpty())) {

         //           	while(oSheet1.getRow(iDtRw).getCell(1).getStringCellValue().isEmpty() && (!oSheet1.getRow(iDtRw).getCell(2).getStringCellValue().isEmpty())) {

						iIterCnt++;
						if (iIter.equals(iIterCnt)) {
							for (int itr=2; itr<oSheet1.getRow(iDtRw).getLastCellNum(); itr++) {
								String sParam = (oSheet1.getRow(iStRw).getCell(itr).getStringCellValue().isEmpty()) ? "NA" : oSheet1.getRow(iStRw).getCell(itr).toString();
//								String sPVal = (oSheet1.getRow(iDtRw).getCell(itr).getStringCellValue().isEmpty()) ? "NA" : oSheet1.getRow(iDtRw).getCell(itr).toString();
//								testParam.put(sParam+"_"+iIterCnt, sPVal);
								
								//code modified - Rahul Bakde
								//to handle numeric values as a parameter
								/*
								 * if (oSheet1.getRow(iDtRw).getCell(itr).getCellType() == Cell.CELL_TYPE_NUMERIC) {
//									String value;
									oSheet1.getRow(iDtRw).getCell(itr).setCellType(Cell.CELL_TYPE_STRING);
									value=oSheet1.getRow(iDtRw).getCell(itr).toString();
									sPVal = (oSheet1.getRow(iDtRw).getCell(itr).getStringCellValue().isEmpty()) ? "NA" : value;
									testParam.put(sParam+"_"+iIterCnt, sPVal);
								} else {
									value=oSheet1.getRow(iDtRw).getCell(itr).toString();
									sPVal = (oSheet1.getRow(iDtRw).getCell(itr).getStringCellValue().isEmpty()) ? "NA" : value;
									testParam.put(sParam+"_"+iIterCnt, sPVal);
								}
								 */
								
			
								oSheet1.getRow(iDtRw).getCell(itr).setCellType(Cell.CELL_TYPE_STRING);
								value=oSheet1.getRow(iDtRw).getCell(itr).toString();
								sPVal = (oSheet1.getRow(iDtRw).getCell(itr).getStringCellValue().isEmpty()) ? "NA" : value;
								//testParam.put(sParam+"_"+iIterCnt, sPVal);
								testParam.put(sParam, sPVal);
								
							}
						}
						iDtRw++;
	                    sTCCol = (oSheet1.getRow(iDtRw).getCell(1) == null) ? "" : oSheet1.getRow(iDtRw).getCell(1).getStringCellValue();
	                    sBrwsrCol = (oSheet1.getRow(iDtRw).getCell(2) == null) ? "" : oSheet1.getRow(iDtRw).getCell(2).getStringCellValue();
						
					}
                    break;
				}
				
			}
			inp.close();
		} catch(Exception ex) {
			inp.close();
		}
		//IO.sTestParams = testParam;
		return testParam;
	}

	public static void PrintLog(String sMsg) {
		try {
			File oFile = new File(sLogFile);
			BufferedWriter oOutput = new BufferedWriter(new FileWriter(oFile, true));
			sMsg = new SimpleDateFormat("yyMMdd-hh:mm'-> '").format(new Date()) + sMsg;
			oOutput.write("\n"+ sMsg);
			oOutput.close();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

/*	public static String GetParamVal(Hashtable<String, String> testParam, String sParamName, Integer iCurrIter) {
		sParamName = (String) testParam.get(sParamName+"_"+iCurrIter);
		return sParamName;
	}
*/
	public static String GetParamVal(Hashtable<String, String> testParam, String sParamName) {
		sParamName = (String) testParam.get(sParamName);
		return sParamName;
	}

	public static void Setup() throws IOException  {
		sTestDataFile = sFolder + "\\TestData\\TestData.xlsm";
		String sTmpStmpR = "Report_" + new SimpleDateFormat("yyyyMMddhhmm'.html'").format(new Date());
		Report.screenshotFile = sFolder + "\\TempScreenShot.png";
		sImageFolder = sFolder + "\\Images";//new changes - Rahul Bakde(2/26/2015)
	}
}


