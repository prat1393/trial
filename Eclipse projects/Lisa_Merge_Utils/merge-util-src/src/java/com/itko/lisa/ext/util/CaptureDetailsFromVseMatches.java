package com.itko.lisa.ext.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Utility to parse the vse_matches log file and generates a CSV output 
 * file with following information:
 * 	Script Name, Date, Time, Image Name, Lisa Id, Lisa Req Id, Match Style,
 *  Operation, Time taken
 *  
 */
public class CaptureDetailsFromVseMatches {

	public class LogLine {
		Timestamp timeStamp;
		String line;

		public LogLine(Timestamp t, String s) {
			timeStamp = t;
			line = s;
		}
	}

	public class CustomComparator implements Comparator<LogLine> {
		public int compare(LogLine o1, LogLine o2) {
			if (o1.timeStamp.after(o2.timeStamp))
				return 1;
			else if (o1.timeStamp.before(o2.timeStamp))
				return -1;
			else
				return 0;
		}
	}

	ArrayList<LogLine> logList = new ArrayList<LogLine>();
	int lookbacktime = 20;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CaptureDetailsFromVseMatches thisObject = new CaptureDetailsFromVseMatches();
		if (args[0].equalsIgnoreCase("multi")) {
			thisObject.lookbacktime = Integer.parseInt(args[2]);
			File[] faFiles = new File(args[1]).listFiles();
			for (File file : faFiles) {
				if (file.isDirectory()) {
					thisObject.findAndProcessFiles(file);
				}
			}
		} else {
			thisObject.lookbacktime = Integer.parseInt(args[1]);
			thisObject.findAndProcessFiles(new File(args[0]));
		}
	}

	public void findAndProcessFiles(File dir) {
		File[] faFiles = dir.listFiles();

		for (File file : faFiles) {
			if (file.isFile()) {
				String directoryPath = file.getParent();
				int idx = directoryPath.lastIndexOf('\\');
				if (!(idx > 0))
					idx = directoryPath.lastIndexOf('/');

				String scriptName = (idx > 0) ? directoryPath
						.substring(idx + 1) : "";

				process(scriptName, file);
			}
		}

		Collections.sort(logList, new CustomComparator());
		int cnt = logList.size();
		if (cnt > 0) {
			Timestamp t = new Timestamp(
					logList.get(cnt - 1).timeStamp.getTime()
							- (lookbacktime * 60 * 1000));

			boolean itsFirstRecord = true;
			String strTimeTaken = "";
			for (int i = 0; i < cnt; i++) {
				if (logList.get(i).timeStamp.before(t))
					continue;
				if (itsFirstRecord) {
					long timeTaken = logList.get(cnt - 1).timeStamp.getTime()
							- logList.get(i).timeStamp.getTime();
					strTimeTaken = timeTaken / 60000 + "." + (timeTaken / 1000)
							% 60;
					itsFirstRecord = false;
				}
				System.out.println(logList.get(i).line + "," + strTimeTaken);
			}
			logList.clear();
		}
	}

	public void process(String scriptName, File file) {
		String currLine = "";
		String dateStr;
		String timeStr;
		String imageStr;
		String[] matchStr;
		String lisaId;
		String lisaReqId;
		String matchTolerance;
		Timestamp timeStamp;
		String inboundOperation = "";
		String operation;
		String[] result = { "" };

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((currLine = br.readLine()) != null) {
				if (!currLine.contains("Transaction Navigator Respond from:")
						&& !currLine
								.contains("Transaction Navigator No stateless match found")
						&& !currLine.contains("- Inbound Request"))
					continue;

				result = currLine.split("\\s");

				if (currLine.contains("- Inbound Request")) {
					if (result[3].equals("INFO"))
						matchStr = result[8].split(":|,");
					else
						matchStr = result[9].split(":|,");
					inboundOperation = matchStr[3].replace("\"", "");
					continue;
				}

				dateStr = result[0];
				timeStr = (result[1]).replace(",", ".");
				timeStamp = Timestamp.valueOf(dateStr + " "
						+ (timeStr.split("\\."))[0]);
				imageStr = result[2].substring(1);

				if (currLine.contains("Transaction Navigator Respond from:")) {
					if (result[3].equals("INFO"))
						matchStr = result[10].split(":|,");
					else
						matchStr = result[11].split(":|,");

					lisaId = matchStr[1];

					if (matchStr[5].replace("\"", "").equals("matchTolerance")) {
						lisaReqId = matchStr[4];
						matchTolerance = matchStr[6].replace("\"", "");
						operation = matchStr[8].replace("\"", "");
					} else {
						lisaReqId = matchStr[6];
						matchTolerance = matchStr[8].replace("\"", "");
						operation = matchStr[10].replace("\"", "");
					}
				} else {
					lisaId = "";
					lisaReqId = "";
					matchTolerance = "NO_MATCH";
					operation = inboundOperation;
					inboundOperation = "";
				}
				String line = scriptName + "," + dateStr + ",'" + timeStr + ","
						+ imageStr + "," + lisaId + "," + lisaReqId + ","
						+ matchTolerance + "," + operation;
				logList.add(new LogLine(timeStamp, line));
			}
			br.close();
		} catch (Exception e) {
			System.out.println("********** Error Occured in Parsing ***********");
			System.out.println(file.getName());
			System.out.println(scriptName);
			System.out.println(currLine);
			e.printStackTrace();
			System.out.println("********** Exiting Merge abruptly*************");
			//Added to forcefully terminate the process
			System.exit(-1);
		}
	}
}
