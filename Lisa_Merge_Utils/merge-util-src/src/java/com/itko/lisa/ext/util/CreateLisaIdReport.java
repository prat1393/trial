package com.itko.lisa.ext.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;



/**
 *
 */

/**
 * @author Mandar Tadphale
 * Installation instructions for installing this on a BL Server
 * 1) Create folder on C:\ with name LisaMatchReport
 * 2) Share the folder with "EVERYONE" with read permission.
 * 3) Share the folder with "<your use id>" with read/write permission so you can copy this utility there.
 * 4) Copy binary files (.class) & config.properties for the utility to C:\LisaMatchReport
 * 5) Add / update C:\LisaMatchReport to CLASSPATH
 * 			My Computer -> properties -> Advanced system settings -> Environment variables -> System Variables -> CLASSPATH
 * 6) Create task to occur every 2 mins that calls this java program
 *    My Computer -> Manage -> Task Scheduler -> Task Scheduler Library -> Create New Task
 * 			General Tab -
 * 				Name - LisaMatchReport
 * 				Run whether user is logged on or not
 * 				Configure for - Winows 7
 * 			Triggers Tab -
 * 				New ->
 * 					Repeat task every 2 mins for duration - indefinitely
 * 					Stop task if it runs longer than 30 mins
 * 			Actions Tab -
 * 				New ->
 * 					Program - java.exe path
 * 					Add arguments -
 * 						CreateLisaIdReport C:\LisaMatchReport\LisaMatchReport.html > C:\LisaMatchReport\LisaMatchReport.log
 * 					Start In -
 * 						C:\LisaMatchReport
 */
public class CreateLisaIdReport
{
	public class LogLine
	{
		Timestamp timeStamp;
		String line;

		public LogLine(Timestamp t, String s)
		{
			timeStamp = t;
			line = s;
		}
	}

	public class CustomComparator implements Comparator<LogLine>
	{
	    public int compare(LogLine o1, LogLine o2)
	    {
	    	if (o1.timeStamp.after(o2.timeStamp))
	    		return 1;
	    	else if (o1.timeStamp.before(o2.timeStamp))
	    		return -1;
	    	else
	    		return 0;
	        //return o1.timeStamp.compareTo(o1.timeStamp);
	    }
	}

	ArrayList<LogLine> logList = new ArrayList<LogLine> ();
	int lookbacktime = 60;
	long now;

	ArrayList<String[]> listOfFiles = new ArrayList<String[]>();
	ArrayList<String> filterOps = new ArrayList<String>();
	boolean isMegedValidation;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
System.err.println("Inside main");

		CreateLisaIdReport thisObject = new CreateLisaIdReport();
    	thisObject.processFiles(args[0]);
	}

	public void processFiles(String opFileName)
	{
		try
		{
System.err.println("Inside processFiles " + opFileName);
			now = System.currentTimeMillis();

			PrintWriter out = new PrintWriter(new FileWriter(opFileName));
			out.write("<html><head><title>Lisa Matches Console</title></head><body>");
			if (!loadProperties())
			{
				out.write("<p>ERROR - Invalid config file</p>");
			    out.write("</body></html>");
			    out.close();
			}
			out.write("<p>Report As of - " + new Timestamp(now).toString() + "</p>");
			out.write("<p>If you see blank report then please wait, it typically takes 30 to 40 seconds to generate it afresh after every 2 minutes.</p>");
			out.flush();

			for (int i = 0; i < listOfFiles.size() ;i++)
		    {
		    	File f = new File ((listOfFiles.get(i))[1]);
		    	if (!f.exists())
		    	{
		    		out.write("<p>ERROR - Cannot find file " + (listOfFiles.get(i))[1] + "</p>");
		    		continue;
		    	}
		    	process((listOfFiles.get(i))[0],f,(listOfFiles.get(i))[1]);
		    }

		    Collections.sort(logList,new CustomComparator());
		    out.write("<p>Finished generating report</p>");
		    out.write("<P ALIGN='center'><TABLE BORDER=1>");
		    out.write("<TR>");
		    out.write("<TH>Machine</TH>");
		    out.write("<TH>Date</TH>");
		    out.write("<TH>Time</TH>");
		    out.write("<TH>Image</TH>");
		    
		    out.write("<TH>Lisa Id</TH>");
		    if(isMegedValidation){
			    out.write("<TH>Script Name</TH>");
			    out.write("<TH>Source TX ID</TH>");
			    out.write("<TH>Source VSI Date</TH>");
			    out.write("<TH>Req Mathing All ANY</TH>");
		    	
		    }
		    out.write("<TH>Match</TH>");
		    out.write("<TH>Operation</TH>");
		    out.write("<TH>Arguments Passed (by SL)</TH>");
		    out.write("<TH>Arguments Matched (from vsi)</TH>");
		    out.write("</TR>");
		    for(int i = logList.size() - 1;i >= 0;i--)
		    {
		    	out.write("<TR>" + logList.get(i).line + "<TR>");
		    }
		    out.write("</TABLE></P>");
		    out.write("</body></html>");
		    out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void process(String machineName,File file,String fileName) throws Exception
	{
		String currLine = "";
		String dateStr = "";
		String timeStr = "";
		String imageStr = "";
		String[] matchStr;
		String lisaId = "";
		String matchTolerance = "";
		String operation = "";
		Timestamp timeStamp;
		String inboundRequest = "";
		String inboundOperation = "";
		boolean itsInboundRequest;
		boolean itsNoMatchLine;
		String line = "";
System.err.println("Inside process " + machineName + fileName);
		int index = 8;
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((currLine = br.readLine()) != null)
			{
//System.err.println(currLine);
				if (currLine.contains("- Inbound Request"))
				{
					itsInboundRequest = true;
//System.err.println("Its input line");
				}
				else
				{
					itsInboundRequest = false;
//System.err.println("Its not input line");
				}

				if (!itsInboundRequest && currLine.contains("Transaction Navigator No stateless match found"))
				{
					itsNoMatchLine = true;
//System.err.println("Its not main line");
				}
				else
					itsNoMatchLine = false;

				if (!itsInboundRequest && !itsNoMatchLine && !currLine.contains("Transaction Navigator Respond from:"))
					continue;

				String[] result = currLine.split("\\s");

				dateStr = result[0];
				timeStr = result[1].replace(",", ".");
				//System.out.println(timeStr);
				//System.out.println(dateStr);
				//System.out.println(dateStr + " " + (timeStr.split("\\."))[0]);
				//System.out.println(currLine);
				timeStamp = Timestamp.valueOf(dateStr + " " + (timeStr.split("\\."))[0]);

				Timestamp t = new Timestamp(now - (lookbacktime * 60 * 1000));
				if (timeStamp.before(t))
					continue;

				//timeStamp = Timestamp.valueOf(dateStr + " " + (timeStr.split("\\."))[0]);
				imageStr = result[2].substring(1);

				if (imageStr.contains("TGUARD"))
				{
					imageStr = imageStr + "<br><p style=\"color:red\"><b>NEW LOGIN</b></p>";
				}

				if (filter(imageStr))
					continue;

				if (itsInboundRequest)
				{
					matchStr = result[9].split(":|,");
					inboundOperation = matchStr[3].replace("\"", "");
					inboundRequest = "<TD>";
					for (int j = 5; j < matchStr.length - 1; j+=2)
					{
						inboundRequest = inboundRequest + matchStr[j].replace("\"", "") + " = " + matchStr[j + 1].replace("\"", "") + "<br>";
					}
					inboundRequest = inboundRequest + "</TD>";
					continue;
				}
				else if (itsNoMatchLine)
				{
					line = "<TD>" + machineName + "</TD>";
					line = line + "<TD>" + dateStr + "</TD>";
					line = line + "<TD>" + timeStr + "</TD>";
					line = line + "<TD>" + imageStr + "</TD>";
					line = line + "<TD>&nbsp;</TD>";
					line = line + "<TD><FONT style=\"BACKGROUND-COLOR: yellow\">NO_MATCH</FONT></TD>";
					line = line + "<TD>" + inboundOperation + "</TD>";
					inboundOperation = "";
					line = line + inboundRequest;
					line = line + "<TD>&nbsp;</TD>";
				}
				else
				{
					matchStr = result[11].split(":|,");

					lisaId = matchStr[1];
					
					if (matchStr[5].replace("\"", "").equals("matchTolerance"))
						index = 6;
					else
						index = 8;
					matchTolerance = matchStr[index].replace("\"", "");
					operation = matchStr[index + 2].replace("\"", "");

					line = "<TD>" + machineName + "</TD>";
					line = line + "<TD>" + dateStr + "</TD>";
					line = line + "<TD>" + timeStr + "</TD>";
					line = line + "<TD>" + imageStr + "</TD>";
					line = line + "<TD>" + lisaId + "</TD>";
					if(imageStr.contains("Merged")){
						isMegedValidation = true;
						addMergedTXInformation(line,lisaId,imageStr);
					}
					if (matchTolerance.equals("EXACT"))
						line = line + "<TD>" + matchTolerance + "</TD>";
					else
						line = line + "<TD><FONT style=\"BACKGROUND-COLOR: yellow\">" + matchTolerance + "</FONT></TD>";
					line = line + "<TD>" + operation + "</TD>";
					if (!matchTolerance.equals("EXACT"))
					{
						if (operation.equals(inboundOperation))
						{
							line = line + inboundRequest;
							inboundRequest = "";
							inboundOperation = "";
						}
						else
							line = line + "<td>&nbsp;</td>";
						line = line + "<TD>";
						for (int j = index + 4; j < matchStr.length - 1; j+=2)
						{
							line = line + matchStr[j].replace("\"", "") + " = " + matchStr[j + 1].replace("\"", "") + "<br>";
						}
						line = line + "</TD>";
					}
					else
					{
						line = line + "<td>&nbsp;</td><td>&nbsp;</td>";
					}
				}
				logList.add(new LogLine(timeStamp,line));
				//System.out.println("[" + timeStampStr + "] [" + timeStr + "] [" + imageStr + "] [" + lisaId + "] [" + matchTolerance + "]");
			}
			br.close();
		}
		catch (IOException e)
		{
			System.err.println(currLine);
			e.printStackTrace();
		}
	}

	public boolean filter(String operation)
	{
		for (int i = 0;i < filterOps.size();i++)
		{
			if(operation.equals(filterOps.get(i)))
				return (true);
		}
		return (false);
	}

	public boolean loadProperties()
	{
		String totalVseStr;
		String machineStr;
		String vseMatchesLogStr;
		String lookbacktimeStr;
		String logPath;

    	Properties prop = new Properties();

    	try
    	{
    		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
    		if (stream == null)
    			return false;
    		prop.load(stream);

    		lookbacktimeStr = prop.getProperty("LookBackMins");
    		if (lookbacktimeStr != null && !lookbacktimeStr.trim().equals(""))
    			lookbacktime = Integer.parseInt(lookbacktimeStr.trim());

    		totalVseStr = prop.getProperty("TotalVSEs");
    		if (totalVseStr == null || totalVseStr.trim().equals(""))
    			return false;
            int totcnt = Integer.parseInt(totalVseStr.trim());

            logPath = prop.getProperty("VSELogPath");
    		if (logPath == null || logPath.trim().equals(""))
    			logPath = "\\Users\\lisauser\\lisatmp_6.0.9";

            for (int i = 1; i <= totcnt;i++)
            {
            	String[] machine = new String[2];

            	machineStr = prop.getProperty("Machine" + i);
            	if (machineStr == null || machineStr.trim().equals(""))
            		return false;
            	machine[0] = machineStr.trim();

            	vseMatchesLogStr = prop.getProperty("VSEMatchesLog" + i);
            	if (vseMatchesLogStr == null || vseMatchesLogStr.trim().equals(""))
            		return false;
            	machine[1] = "\\\\" + machine[0] + logPath + "\\\\" + vseMatchesLogStr.trim();
            	listOfFiles.add(machine);
            }

    		totalVseStr = prop.getProperty("TotalFilters");
    		if (totalVseStr != null && !totalVseStr.trim().equals(""))
    		{
	            totcnt = Integer.parseInt(totalVseStr.trim());

	            for (int i = 1; i <= totcnt;i++)
	            {
	            	String filterStr = prop.getProperty("Filter" + i);
	            	if (filterStr == null || filterStr.trim().equals(""))
	            		return false;
	            	filterOps.add(filterStr.trim());
	            }
            }
    	}
    	catch (IOException ex)
    	{
    		ex.printStackTrace();
        }
		return true;
	}
	private boolean readTXMappingDone;
	 

	private void  addMergedTXInformation(String line, String lisaId, String bakend) throws Exception{
		if(!readTXMappingDone){
			readTXMappingXLS("");
			
		}
		String[] backendName = bakend.split("_");
		Map<String, TxMappingPojo> bakendMap = txMap.get(backendName[0]);
		if(null != bakendMap){
			TxMappingPojo pojo = bakendMap.get(lisaId);
			 if(null != pojo){
					line = line + "<TD>" + pojo.getScriptName() + "</TD>";
					line = line + "<TD>" + pojo.getSourceLisaTxId() + "</TD>";
					line = line + "<TD>" + pojo.getSourceVsiDate() + "</TD>";
					line = line + "<TD>" + pojo.isAllRequestParamtersAnything() + "</TD>";
			 }
		}
		
		
	}
	private  void readTXMappingXLS(String xlsFilename) 
			throws Exception {
		FileInputStream fileIn = new FileInputStream(xlsFilename);
		HSSFWorkbook wb = new HSSFWorkbook(fileIn);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = 0;
		for (Row row : sheet) {
			if (rowCount == 0) {
				++rowCount;
				continue;
			}
			// Script Name
			Cell cell0 = row.getCell(0);
			
			// Source TX ID
			Cell cell = row.getCell(1);
			
			// Source TX ID
			Cell cell1 = row.getCell(2);
			// Source VSI Date
			Cell cell2 = row.getCell(3);
			// backend Name
			Cell cell4 = row.getCell(4);
			//ANY matching?
			Cell cell7 = row.getCell(7);
			
			
			String scriptName = cell0.getStringCellValue();
			String mergeLisaTxId = cell1.getStringCellValue();
			String sourceTXId = cell1.getStringCellValue();
			String sourceVSIDate = cell2.getStringCellValue();
			String backendName = cell4.getStringCellValue();
			String matchingANY = cell7.getStringCellValue();
			TxMappingPojo pojo = new TxMappingPojo();
			pojo.setScriptName(scriptName);
			pojo.setSourceLisaTxId(sourceTXId);
			pojo.setSourceVsiDate(sourceVSIDate);
			pojo.setBackendName(backendName);
			pojo.setAllRequestParamtersAnything(matchingANY);
			pojo.setMergeLisaTxId(mergeLisaTxId);
			Map<String,TxMappingPojo> bakendMap = txMap.get(backendName);
			 if(null == bakendMap){
				 bakendMap = new HashMap<String, CreateLisaIdReport.TxMappingPojo>();
			 }
			 bakendMap.put(mergeLisaTxId,pojo);
		}
		readTXMappingDone = true;
	}
	
private Map<String, Map<String,TxMappingPojo>> txMap= new  HashMap<String, Map<String,TxMappingPojo>>();


class TxMappingPojo{
	public String getScriptName() {
		return scriptName;
	}
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}
	public String getSourceVsiDate() {
		return sourceVsiDate;
	}
	public void setSourceVsiDate(String sourceVsiDate) {
		this.sourceVsiDate = sourceVsiDate;
	}
	public String getSourceLisaTxId() {
		return sourceLisaTxId;
	}
	public void setSourceLisaTxId(String sourceLisaTxId) {
		this.sourceLisaTxId = sourceLisaTxId;
	}
	public String getMergeLisaTxId() {
		return mergeLisaTxId;
	}
	public void setMergeLisaTxId(String mergeLisaTxId) {
		this.mergeLisaTxId = mergeLisaTxId;
	}
	
	
	public String getBackendName() {
		return backendName;
	}
	public void setBackendName(String backendName) {
		this.backendName = backendName;
	}


	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public int getNumberOfArguments() {
		return numberOfArguments;
	}
	public void setNumberOfArguments(int numberOfArguments) {
		this.numberOfArguments = numberOfArguments;
	}


	public String isAllRequestParamtersAnything() {
		return allRequestParamtersAnything;
	}
	public void setAllRequestParamtersAnything(String allRequestParamtersAnything) {
		this.allRequestParamtersAnything = allRequestParamtersAnything;
	}


	public String scriptName;
	public String sourceVsiDate;
	public String sourceLisaTxId;
	public String mergeLisaTxId;
	public String backendName;
	public String operationName;
	public int numberOfArguments;
	public String allRequestParamtersAnything;
}

}
