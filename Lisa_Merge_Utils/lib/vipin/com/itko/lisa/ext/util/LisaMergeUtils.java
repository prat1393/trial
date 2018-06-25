package com.itko.lisa.ext.util;



import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.log4j.Logger;


/**  
 * LisaMergeUtils.java - Utilities supporting LISA Service Images  
 * @author  Prasad Kona (prasad.kona@ca.com)
 * @author  Ashutosh Satyam (ashutosh.satyam@ca.com)
 * @version 1.0 
 */ 

public class LisaMergeUtils {

	private static Logger log = Logger.getLogger(LisaMergeUtils.class);

	private static final String[] HELP_TEXT = {
		"This command line tool is used for the merge process for images.",
		"Usage : java com.itko.lisa.ext.util.LisaMergeUtils [arguments]",
		"Required Arguments :",
		"--operation <EXTRACT or MERGE> " ,
		"     EXTRACT is used to extract the transactions from the source images based on the data specified in the spreadsheet",
		"     MERGE is used to combine the service images that contain matched transactions created by the EXTRACT operation",
		"--properties-file <merge properties file>" ,
		"     Properties file contains the following information" ,
		"			1. names of http/java backends, " ,
		"			2. source vsi folder, " ,
		"			3. destination vsi folder for matched/merged images",
		"			4. spreadsheet containing matched transactions based on vse_matches",
		"Optional Arguments : NONE", "\n\n\n",
		"",
		"--operation <CSV2XLSX> " ,
		"     CSV2XLSX is used to convert a csv file to an Excel xlsx file",
		"--csv-file <input csv file>" ,
		"     complete path for csv input file" ,
		"--xlsx-file <output xlsx file>" ,
		"     complete path for xlsx output file" ,
		"Optional Arguments : NONE", "\n\n\n",
	};

	private static String [] prepareCommandLineParam(){
		String [] args = new String[6];
		args[0] = "--operation";
		args[1] = "CSV2XLSX";
		args[2] = "--csv-file";
		args[3] = "C:/sltest/trunk/Lisa_Merge_Utils/data/LisaMatchesReport_1304_Grid_On.csv";
		args[4] = "--xlsx-file";
		args[5] = "C:/sltest/trunk/Lisa_Merge_Utils/data/LisaMatchesReport_1304_Grid_On.xlsx";
		return args;
	}
	
	public static void main(String args[]) throws Throwable {
		LinkedList<String> params = new LinkedList<String>(Arrays.asList(args));
		Iterator<String> it =params.iterator();
		System.out.println("---------------Start --------------");
		while(it.hasNext()){System.out.println("input-param="+it.next());}
		System.out.println("---------------End --------------");
		process(params);
	}

	public static void process(LinkedList<String> params) throws Throwable {

		if (params == null || params.isEmpty() || params.size() < 4) {
			showHelpText();
			return;
		}
		if (!"--operation".equals(params.get(0))) {
			showHelpText();
			return;
		}

		if (("EXTRACT".equals(params.get(1)) || "MERGE".equals(params.get(1)))
				&& !"--properties-file".equals(params.get(2))) {
			showHelpText();
			return;
		}

		if (("CSV2XLSX".equals(params.get(1)))
				&& (!"--csv-file".equals(params.get(2)) || !"--xlsx-file"
						.equals(params.get(4)))) {
			showHelpText();
			return;
		}

		String operationName = params.get(1);

		String propertiesFile = params.get(3);
		String inputFile = "";
		String outputFile = "";
        String txMappingXlsFile = "";
        String vsiBasePath = "";
        String serFiles = "";
		if (("CSV2XLSX".equals(params.get(1)))) {
			inputFile = params.get(3);
			outputFile = params.get(5);
			System.out.println("inputCsvFile=" + inputFile);
			System.out.println("outputExcelFile=" + outputFile);
		}
		else if (("CreateTxMapping".equals(params.get(1)))) {
			vsiBasePath =  params.get(3);
			txMappingXlsFile = params.get(5);
			serFiles = params.get(7);
			System.out.println("txMappingXlsFile=" + txMappingXlsFile);
			System.out.println("vsiBasePath=" + vsiBasePath);
			System.out.println("serFiles=" + serFiles);
		} else {

			System.out.println("operationName=" + operationName);
			System.out.println("propertiesFile=" + propertiesFile);
		}
		if ("EXTRACT".equals(operationName)) {
			txMappingXlsFile = params.get(5);
			LisaServiceImageCleaner
					.startMatchesExtractionProcess(propertiesFile,txMappingXlsFile);
		} else if ("MERGE".equals(operationName)) {
			LisaServiceImageCleaner
					.startServiceImageMergeProcess(propertiesFile);
			;
		} else if ("CSV2XLSX".equals(operationName)) {
			LisaServiceImageCleaner.convertCsvToXlsx(inputFile, outputFile,
					"xlsx");
		}else if ("CreateTxMapping".equals(operationName)) {
			CreateTxMappingUtil txMapUtil = new CreateTxMappingUtil();
			txMapUtil.createTxMapping(vsiBasePath,txMappingXlsFile,serFiles);
		} else {
			showHelpText();
			return;
		}

	}


	private static void showHelpText() {
		for (String line : HELP_TEXT)
			log.info(line);
	}
}



