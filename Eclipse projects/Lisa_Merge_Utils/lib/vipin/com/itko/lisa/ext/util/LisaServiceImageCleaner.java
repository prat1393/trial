package com.itko.lisa.ext.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itko.lisa.vse.stateful.model.Request;
import com.itko.lisa.vse.stateful.model.Response;
import com.itko.lisa.vse.stateful.model.ServiceImage;
import com.itko.lisa.vse.stateful.model.Transaction;
import com.itko.lisa.vse.stateful.model.TransactionNode;
import com.itko.lisa.vse.stateful.model.xml.ServiceImageContext;
import com.itko.util.ParameterList;
import com.itko.util.csv.CSVReader;

/**
 * LisaServiceImageCleaner.java - Utilities supporting LISA Service Images
 * 
 * @author Prasad Kona (prasad.kona@ca.com)
 * @author Ashutosh Satyam (ashutosh.satyam@ca.com)
 * @version 1.0
 */
public class LisaServiceImageCleaner {

	private static Log log = LogFactory.getLog(LisaServiceImageCleaner.class);

	private static final long serialVersionUID = 3927359428119376530L;
	// private static HashMap fileHash = new HashMap();
	private static Map<String, String> fileHash = new HashMap<String, String>();

	// map of matches information
	private static Map<String, ArrayList> mm = new HashMap<String, ArrayList>();

	// map of http backends (ArrayList of number strings, which is the same as
	// folder name and in the vsi name
	private static Map<String, ArrayList> httpBackendMap = new HashMap<String, ArrayList>();

	// map of java backends (ArrayList of number strings, which is the same as
	// folder name and in the vsi name
	private static Map<String, ArrayList> javaBackendMap = new HashMap<String, ArrayList>();
	
	//contains mapping of backend to list of operations where signature matching is allowed
	private static Map<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();

	private static String matchesExcelFile = "";
	private static String sourceVsiFolder = "";
	private static String destinationVsiFolder = "";
	private static String serviceImageManagerPath = "/Applications/Lisa/6.0.9.26/bin/ServiceImageManager";

	private static boolean debug = false;

	static void readServiceImage(String vsiFile, PrintWriter outVSI) {

		String exceptionString1 = "<(lisa.vse.java.response)>[\n\r][\n\r]<(lisa.vse.java.response.args)>[\n\r][\n\r]</(lisa.vse.java.response.args)>[\n\r][\n\r]</(lisa.vse.java.response)>[\n\r][\n\r]";
		String operationName1 = "AESLookupBean_fow2o_EOImpl_1035_WLStub.getSubscriberInfo";
		String newMetaResponseText = "throw new com.cingular.AES.AESinterface.AESLookupException(\"Subscriber Info not found\")";

		try {

			ServiceImage si = ServiceImageContext.readServiceImage(vsiFile);
			Set<String> resultset = new HashSet<String>();

			ServiceImage siNew = new ServiceImage();
			siNew.setCreated(si.getCreated());
			siNew.setName(si.getName());
			siNew.setLastModified(si.getLastModified());
			siNew.setNotes(si.getNotes());
			siNew.setUnknownConversationalRequestResponse(si
					.getUnknownConversationalRequestResponse());
			siNew.setUnknownStatelessRequestResponse(si
					.getUnknownStatelessRequestResponse());
			siNew.setVersion(si.getVersion());
			siNew.setId(si.getId());

			System.out.println("ServiceImageName:" + si.getName());

			List<TransactionNode> trans = si.getStatelessTransactions();
			Iterator itr = trans.iterator();

			while (itr.hasNext()) {

				// For a transaction
				TransactionNode tn = (TransactionNode) itr.next();

				// System.out.println("For Transaction: Id[" + tn.getId() +
				// "]");
				Request metaReq = tn.getRequest();
				// System.out.println("InitialMetaOperation=["
				// + metaReq.getOperation() + "];");
				List<Response> metaResp = tn.getResponses();

				// for a particular operation defined by operationName1
				if (tn.getRequest().getOperation()
						.compareToIgnoreCase(operationName1) == 0) {
					String resultStr = vsiFile + ","
							+ tn.getRequest().getOperation() + ",";

					// Get specific transactions
					List<Transaction> specificTNs = tn.getSpecifics();

					System.out.println("specific Tx count:"
							+ specificTNs.size());

					// Process the specific transactions
					specificTNs = returnProcessedSpecificTrans(specificTNs,
							exceptionString1);

					System.out.println("specific new Tx count:"
							+ specificTNs.size());

					// Add specific responses to the new TransactionNode
					CustomTransactionNode tnNew = new CustomTransactionNode();
					Iterator sitr = specificTNs.iterator();

					Boolean isFirst = true;
					while (sitr.hasNext()) {

						Transaction t = tnNew.createSpecificTransaction();
						Transaction stn = (Transaction) sitr.next();
						t = stn.createCopy();

						/*
						 * //set meta response to new string if (isFirst){
						 * List<Response> responses = t.getResponses(); Iterator
						 * it = responses.iterator(); Response newResponse =
						 * null; Response aResponse = null; while
						 * (it.hasNext()){ aResponse = (Response) it.next();
						 * newResponse = aResponse.createCopy(); }
						 * t.removeResponse(aResponse);
						 * newResponse.setBody(com.itko
						 * .util.XMLUtils.encodeString(newMetaResponseText));
						 * t.addResponse(newResponse);
						 * 
						 * isFirst=false; }
						 */
						// set transaction
						t.setParentTransaction(tnNew);

					}
					// add meta response to the transaction node
					for (Response response : tn.getResponses()) {
						tnNew.addResponse(response.createCopy());
					}

					// set meta request
					tnNew.setRequest(metaReq);

					Request metaReqNew = tnNew.getRequest();
					// System.out.println("NewMetaOperation=["
					// + metaReqNew.getOperation() + "];");

					// add the stateless transaction
					siNew.addStatelessTransaction(tnNew);

				}// end of if operation=
				else {
					siNew.addStatelessTransaction(tn);
				}
			} // end while

			ServiceImageContext.writeXML(outVSI, siNew);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			// e.printStackTrace();
		}

	}

	/*
	 * 
	 */
	static List<Transaction> returnProcessedSpecificTrans(
			List<Transaction> specificTNs, String respBodyPattern) {

		List<Transaction> specificTNsNew = new ArrayList<Transaction>();
		Iterator sitr = specificTNs.iterator();

		while (sitr.hasNext()) {
			Transaction stn = (Transaction) sitr.next();

			// System.out.println("For ExactMatch:" + stn.getId());
			List<Response> sResp = stn.getResponses();
			if (sResp.size() == 1) {
				Response resp = sResp.get(0);
				/*
				 * System.out.println("For ExactMatch:" + stn.getId() +
				 * "; response=[" + resp.getBodyAsString() + "];len=" +
				 * resp.getBodyAsString().length());
				 */
				if (resp.getBodyAsString().matches(respBodyPattern)) {
					// System.out.println("Response matches expression ....");

				}// end of response matches expression
				else {
					specificTNsNew.add(stn);
				}
			} // end process a response
		}// end of while

		return specificTNsNew;
	}

	/*
	 * Read a LISA Service Image file to extract details including operation
	 * names and arguments for all transactions
	 */

	public static void readServiceImageForReport(String vsiFile) {

		String exceptionString1 = "<(lisa.vse.java.response)>[\n\r][\n\r]<(lisa.vse.java.response.args)>[\n\r][\n\r]</(lisa.vse.java.response.args)>[\n\r][\n\r]</(lisa.vse.java.response)>[\n\r][\n\r]";
		String operationName1 = "AESLookupBean_fow2o_EOImpl_1035_WLStub.getSubscriberInfo";

		try {

			ServiceImage si = ServiceImageContext.readServiceImage(vsiFile);
			Set<String> resultset = new HashSet<String>();
			int transCountTotal = 0;
			int transCountMatchPattern = 0;

			System.out.println("START,ServiceImageName," + si.getName()
					+ ",FileName," + vsiFile + ",");

			List<TransactionNode> trans = si.getStatelessTransactions();
			Iterator itr = trans.iterator();

			while (itr.hasNext()) {

				// For a transaction
				TransactionNode tn = (TransactionNode) itr.next();

				// for a particular operation defined by operationName1
				// if
				// (tn.getRequest().getOperation().compareToIgnoreCase(operationName1)
				// == 0) {
				String resultStr = vsiFile + ","
						+ tn.getRequest().getOperation() + ",";

				// Get specific transactions
				List<Transaction> specificTNs = tn.getSpecifics();
				int specificTxCount = specificTNs.size();
				transCountTotal = transCountTotal + specificTxCount;
				// Process the specific transactions
				specificTNs = returnProcessedSpecificTrans(specificTNs,
						exceptionString1);
				int specificTxCountNew = specificTNs.size();
				int resultCount = specificTxCount - specificTxCountNew;
				transCountMatchPattern = transCountMatchPattern + resultCount;
				if (resultCount > 0)
					System.out.println("DETAIL,ServiceImageName,"
							+ si.getName() + ",LisaTransID," + tn.getId()
							+ ",Operation," + tn.getRequest().getOperation()
							+ ",SpecificTxCount," + specificTxCount
							+ ",ExceptionCount," + resultCount + ",");
				// System.out.println("LisaTransID["+tn.getId()+"];Operation["+tn.getRequest().getOperation()+"];SpecificTxCount["+specificTxCount+"];ExceptionCount["+resultCount+"]");

				// } end for a particular operation defined by operationName1

			} // end while
			System.out.println("DONE,ServiceImageName," + si.getName()
					+ ",TransactionCount," + trans.size()
					+ ",TotalSpecificTransCount," + transCountTotal
					+ ",SpecificTransMatchingPattern," + transCountMatchPattern
					+ ",FileName," + vsiFile + ",");
			// System.out.println("DONE:ServiceImageName[" +
			// si.getName()+"];TransactionCount["+trans.size()+"];TotalSpecificTransCount["+transCountTotal+"];SpecificTransMatchingPattern["+transCountMatchPattern+"];FileName["+vsiFile+"]");

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			// e.printStackTrace();
		}

	}

	/**
	 * Create a hash of all lisa virtual service image files that are present in
	 * a project directory matching regular expression
	 */

	static void listDirectoryContents(File dir, String regex) {
		try {
			File[] files = dir.listFiles();
			if (files != null)
				for (File file : files) {
					if (file.isDirectory()) {
						// System.out.println("directory:" +
						// file.getCanonicalPath());
						listDirectoryContents(file, regex);
					} else {
						if (file.getName().matches(regex)) {
							fileHash.put(file.getCanonicalPath(),
									file.getName());
							System.out.println("     file:"
									+ file.getCanonicalPath());
						}
					}
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Returns a set of folder names that matches regular expression For example
	 * dirRegex=".*[0-9]" to select folders that start with a number For all
	 * these directories For example fileRegex= "QP[_].*\\.vsi"; to return vsi
	 * files that start with QP_ and store them in the filehash.
	 */
	static void getFileNamesMatchingRegex(File dir, String dirRegex,
			String fileRegex) {
		try {
			File[] files = dir.listFiles();
			if (files != null)
				for (File file : files) {
					if (file.isDirectory()
							&& file.getCanonicalPath().matches(dirRegex)) {
						// System.out.println("directory:"
						// +file.getCanonicalPath());
						listDirectoryContents(file, fileRegex);
					} else {

					}
				}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Returns a set of folder names that matches regular expression For example
	 * regex=".*[0-9]"to return folders that start with a number
	 */
	static List<String> getDirectoryNamesOnlyMatchingRegex(File dir,
			String regex) {
		List<String> result = new ArrayList<String>();

		try {

			File[] files = dir.listFiles();
			if (files != null)
				for (File file : files) {
					if (file.isDirectory()
							&& file.getCanonicalPath().matches(regex)) {
						// System.out.println("directory:"
						// +file.getCanonicalPath());
						result.add(file.getCanonicalPath());
					}
				}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	static void cleanAllServiceImageFiles(String projFolder,
			String fileNameRegex) {

		try {
			File currentDir = new File(projFolder);

			listDirectoryContents(currentDir, fileNameRegex);
			for (String key : fileHash.keySet()) {
				PrintWriter outVSI = new PrintWriter(new FileWriter(key
						+ ".out"));
				readServiceImage(key, outVSI);
				File outFile = new File(key + ".out");
				File outFileNew = new File(key);

				File sourceFile = new File(key);
				File sourceFileNew = new File(key + ".original2");
				sourceFile.renameTo(sourceFileNew);
				outFile.renameTo(outFileNew);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static void reportAllServiceImageFiles(String projFolder,
			String fileNameRegex) {

		try {
			File currentDir = new File(projFolder);

			listDirectoryContents(currentDir, fileNameRegex);
			PrintWriter outVSI = new PrintWriter(new FileWriter("./areport"));

			for (String key : fileHash.keySet()) {
				readServiceImageForReport(key);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Read a LISA Service Image file to extract details including operation
	 * names and arguments for all transactions
	 */

	public static void searchServiceImageForTransaction(String vsiFile,
			String operationName, Map argHash) {

		try {

			ServiceImage si = ServiceImageContext.readServiceImage(vsiFile);
			Set<String> resultset = new HashSet<String>();
			int transCountTotal = 0;
			int transCountMatchPattern = 0;

			System.out.println("START,ServiceImageName," + si.getName()
					+ ",FileName," + vsiFile + ",");

			System.out.println("Service Image name      : " + si.getName());
			System.out.println("Service Image file name : " + vsiFile);

			List<TransactionNode> trans = si.getStatelessTransactions();
			Iterator itr = trans.iterator();

			while (itr.hasNext()) {

				// For a transaction
				TransactionNode tn = (TransactionNode) itr.next();

				// for a particular operation defined by operationName1
				if (tn.getRequest().getOperation()
						.compareToIgnoreCase(operationName) == 0) {
					String resultStr = vsiFile + ","
							+ tn.getRequest().getOperation() + ",";
					// compare signature
					if (checkPartialSignature(tn.getRequest(), argHash, false)) {
						// Get specific transactions
						List<Transaction> specificTNs = tn.getSpecifics();
						int specificTxCount = specificTNs.size();
						transCountTotal = transCountTotal + specificTxCount;
						// Process the specific transactions
						Iterator specificTxIterator = specificTNs.iterator();
						while (specificTxIterator.hasNext()) {
							Transaction sp = (Transaction) specificTxIterator
									.next();
							if (checkPartialSignature(sp.getRequest(), argHash,
									true))
								System.out
										.println("Matched : LisaOperationName="
												+ sp.getRequest()
														.getOperation()
												+ ";LisaTransactionID="
												+ sp.getId()
												+ ";LisaRequestId="
												+ sp.getRequest().getId() + ";");

						}

					}

				} // end for a particular operation defined by operationName1

			} // end while

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			// e.printStackTrace();
		}

	}

	/*
	 * Returns true if the arguments provided match the signature specified. the
	 * arguments specified are a subset of the actual arguments for the request
	 * The argument values are validated only when checkArgValue is set to true
	 */
	static Boolean checkPartialSignature(Request req, Map argHash,
			Boolean checkArgValue) {
		Boolean result = false;
		int matchCount = 0;
		Map<String, Object> reqArgs = req.getArguments().getAllKeyValuePairs();
		Iterator argIterator = argHash.keySet().iterator();
		while (argIterator.hasNext() && (result == false)) {
			String arg = (String) argIterator.next();
			Object argValue = (Object) argHash.get(arg);
			if (checkArgValue) {
				if (reqArgs.containsKey(arg)
						&& (reqArgs.get(arg).equals(argValue)))
					matchCount++;
			} else {
				if (reqArgs.containsKey(arg))
					matchCount++;
			}
		}
		if (matchCount == argHash.size())
			result = true;

		return result;
	}

	/*
	 * TODO Returns true if the arguments provided match the signature
	 * specified. the arguments specified are all the actual arguments for the
	 * request. The argument values are validated only when checkArgValue is set
	 * to true
	 */
	static Boolean checkSignature(Request req, Map argHash,
			Boolean checkArgValue) {
		Boolean result = false;
		int matchCount = 0;
		Map<String, Object> reqArgs = req.getArguments().getAllKeyValuePairs();
		Iterator argIterator = argHash.keySet().iterator();
		while (argIterator.hasNext() && (result == false)) {
			String arg = (String) argIterator.next();
			Object argValue = (Object) argHash.get(arg);
			if (checkArgValue) {
				if (reqArgs.containsKey(arg)
						&& (reqArgs.get(arg).equals(argValue)))
					matchCount++;
			} else {
				if (reqArgs.containsKey(arg))
					matchCount++;
			}
		}
		if (matchCount == argHash.size())
			result = true;

		return result;
	}

	/*
	 * if type is "NOT_FOUND" then print only the ones transactions the we did
	 * not find in the service images if type is "FOUND" then print only the
	 * ones transactions the we did not find in the service images default is
	 * "ALL"
	 */

	public static String toStringMM(String type) {
		String result = "";
		Set<String> keys = mm.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			System.out.println("	key=" + key);
			result = result + "	key=" + key + "\n";
			List list = (ArrayList) mm.get(key);
			Iterator it2 = list.iterator();
			while (it2.hasNext()) {
				AttScriptMatch att = (AttScriptMatch) it2.next();
				if (type.equals("NOT_FOUND")) {
					if (att.getTransList().size() == 0) {
						// System.out.println("	value="+att.toString());
						result = result + "	value=" + att.toString() + "\n";
					}
				} else if (type.equals("FOUND")) {
					if (att.getTransList().size() != 0) {
						// System.out.println("	value="+att.toString());
						result = result + "	value=" + att.toString() + "\n";
					}
				} else
					// System.out.println("	value="+att.toString());
					result = result + "	value=" + att.toString() + "\n";

			}

		}

		return result;
	}

	public static String toStringBackendMaps() {

		String result = "Excel file being processed                   : "
				+ matchesExcelFile + "\n";
		result = result + "Source Folder for VSIs                       : "
				+ sourceVsiFolder + "\n";
		result = result + "Destination folder for matched & merged VSIs : "
				+ destinationVsiFolder + "\n";

		result = result + "Java Backends :\n";
		Set<String> keys = javaBackendMap.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			result = result + "	key=" + key + "\n";
			List list = (ArrayList) javaBackendMap.get(key);
			if (list != null) {
				Iterator it2 = list.iterator();
				while (it2.hasNext()) {
					String str = (String) it2.next();
					result = result + "		value=" + str.toString() + "\n";
				}
			}
		}

		result = result + "\nHTTP Backends :\n";
		keys = httpBackendMap.keySet();
		it = keys.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			result = result + "	key=" + key + "\n";
			List list = (ArrayList) httpBackendMap.get(key);
			if (list != null) {
				Iterator it2 = list.iterator();
				while (it2.hasNext()) {
					String str = (String) it2.next();
					result = result + "		value=" + str.toString() + "\n";
				}
			}
		}

		return result;

	}

	static void readExcelSheet(String excelxFilename, String siNameFilter)
			throws Exception {
		mm = new HashMap<String, ArrayList>();
		FileInputStream fileIn = new FileInputStream(excelxFilename);

		XSSFWorkbook wb = new XSSFWorkbook(fileIn);
		XSSFSheet sheet = wb.getSheetAt(0);

		// set a date formater
		DataFormatter formatter = new DataFormatter(Locale.US);

		// init
		Map<String, ArrayList> resultHash = new HashMap<String, ArrayList>();

		for (Row row : sheet) {
			// filter by service image name pattern
			if (row.getCell(3).getStringCellValue().matches(siNameFilter)) {
				// System.out.print("Excel Row :"+row.getRowNum()+" ;");
				Cell cell0 = row.getCell(0); // scriptName
				Cell cell1 = row.getCell(1); // validation date
				String vDate = "";
				Cell cell2 = row.getCell(2); // validation time
				Cell cell3 = row.getCell(3); // si name
				Cell cell4 = row.getCell(4); // lisa transaction id
				Cell cell5 = row.getCell(5); // lisa request id
				Cell cell6 = row.getCell(6); // match style
				Cell cell7 = row.getCell(7); // operation

				vDate = "";

				/*
				 * commenting to prevent exception during merge all :
				 * java.lang.IllegalStateException: Cannot get a numeric value
				 * from a text cell at
				 * org.apache.poi.xssf.usermodel.XSSFCell.typeMismatch
				 * (XSSFCell.java:843) at
				 * org.apache.poi.xssf.usermodel.XSSFCell.
				 * getNumericCellValue(XSSFCell.java:208)
				 * if(DateUtil.isCellDateFormatted(cell1)) { vDate =
				 * formatter.formatCellValue(cell1); // date should be the same
				 * as shown in Excel } else{ vDate =
				 * cell1.getDateCellValue().toString(); }
				 */

//				String comment = "[scriptName="
//						+ cell0.getRichStringCellValue().getString()
//						+ ";validationDate=" + vDate + ";operation="
//						+ cell7.getRichStringCellValue().getString() + ";]";
				
				String script_name = cell0.getRichStringCellValue().getString();
				String time = cell2.getRichStringCellValue().getString();
						
				AttScriptMatch aMatchDetail = null;
				String cell6Value = cell6.getRichStringCellValue()
						.getString();
				String cell3Value = cell3.getRichStringCellValue()
						.getString().split("_")[0];
				String cell7Value = cell7.getRichStringCellValue()
						.getString();

				if ((("NO_MATCH".equals(cell6Value) == false) && ("SIGNATURE"
						.equals(cell6Value) == false))
						|| (map.containsKey(cell3Value) && (map.get(cell3Value)
								.contains(cell7Value)))) {

					aMatchDetail = new AttScriptMatch(cell3
							.getRichStringCellValue().getString(),
							(int) cell4.getNumericCellValue(),
							(int) cell5.getNumericCellValue(), cell6
									.getRichStringCellValue().getString(),
							script_name,time);
					// print matching row
					// System.out.println("Excel Row :"+row.getRowNum()+" ;"+aMatchDetail.toString());
					updateMatchesMap(aMatchDetail);
				}
			}// end of if col matches pattern

		}

	}

	/*
	 * Read excel file and process service image names. the service image names
	 * should be of the format "backend_numericdate"
	 */
	static void readExcelSheetForServiceImageNames(String excelxFilename)
			throws Exception {
		FileInputStream fileIn = new FileInputStream(excelxFilename);

		XSSFWorkbook wb = new XSSFWorkbook(fileIn);
		XSSFSheet sheet = wb.getSheetAt(0);

		Map m = new HashMap();

		// set a date formater
		DataFormatter formatter = new DataFormatter(Locale.US);

		// init
		Map<String, ArrayList> resultHash = new HashMap<String, ArrayList>();

		for (Row row : sheet) {
			// get si name from spreadsheet
			if (!(m.containsKey(row.getCell(3).getStringCellValue()))) {
				m.put(row.getCell(3).getStringCellValue(), "value");
			}
		}

		// process the si names from the excel file and update the backend hash
		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String siname = (String) it.next();
			String[] tokens = siname.split("[_]");
			if (javaBackendMap.containsKey(tokens[0])) {

				ArrayList alist = javaBackendMap.get(tokens[0]);
				if (alist == null) {
					alist = new ArrayList();
					alist.add(tokens[1]);
					javaBackendMap.put(tokens[0], alist);

				} else if (!alist.contains(tokens[1])) {
					ArrayList clonelist = new ArrayList();
					clonelist = (ArrayList) alist.clone();
					clonelist.add(tokens[1]);
					javaBackendMap.put(tokens[0], clonelist);

				}

			} else if (httpBackendMap.containsKey(tokens[0])) {
				ArrayList alist = httpBackendMap.get(tokens[0]);
				if (alist == null) {
					alist = new ArrayList();
					alist.add(tokens[1]);
					httpBackendMap.put(tokens[0], alist);

				} else if (!alist.contains(tokens[1])) {
					ArrayList clonelist = new ArrayList();
					clonelist = (ArrayList) alist.clone();
					clonelist.add(tokens[1]);
					httpBackendMap.put(tokens[0], clonelist);

				}

			} else {
				// si not known
				System.out.println("ERROR: Service image name mentioned in "
						+ "matches excel file doesnot follow naming "
						+ "convention:" + siname);
			}

		}

	}

	/*
	 * update the result hashmap that contains the list of all the transactions
	 * associated with a particular service image
	 */
	static Map<String, ArrayList> updateResultset(Map<String, ArrayList> map,
			AttScriptMatch aMatch) {
		Map<String, ArrayList> resultMap = new HashMap<String, ArrayList>();

		System.out.println("	processing:" + aMatch.toString());
		if ((map != null)) {
			if (map.containsKey(aMatch.getServiceImageName())) {
				List alist = (ArrayList) map.get(aMatch.getServiceImageName());
				List<AttScriptMatch> blist = new ArrayList<AttScriptMatch>();
				// check for unique obj
				for (int i = 0; i < alist.size(); i++) {
					AttScriptMatch a = (AttScriptMatch) alist.get(i);
					// blist.add(a);
					System.out.println("	check:" + a.toString());
					System.out.println("	and check:" + aMatch.toString());

					if ((a.getLisaRequestId() == aMatch.getLisaRequestId())
							&& (a.getLisaTransactionId() == aMatch
									.getLisaTransactionId())) {// &&(a.getMatchStyle().compareTo(aMatch.getMatchStyle()
																// ) ==0 ) ){
						System.out.println("its a match:" + a.toString());
						a.addComment(aMatch.getComment());
						// blist.remove(i);
						blist.add(a);
						// break;
					} else {
						blist.add(a);
					}
				}

				map.put(aMatch.getServiceImageName(), (ArrayList) blist);
			} else {
				List alist = new ArrayList();
				alist.add(aMatch);
				map.put(aMatch.getServiceImageName(), (ArrayList) alist);
			}
		}

		return map;
	}
	
	static void updateMatchesMap(AttScriptMatch aMatch) {
		// System.out.println("	processing:"+aMatch.toString());
		if (mm.containsKey(aMatch.getServiceImageName())) {
			List alist = (ArrayList) mm.get(aMatch.getServiceImageName());
			List blist = new ArrayList();

			boolean match = false;
			boolean added = false;

			// check for unique obj
			for (int i = 0; i < alist.size(); i++) {
				AttScriptMatch a = (AttScriptMatch) alist.get(i);
				AttScriptMatch newA = new AttScriptMatch(
						a.getServiceImageName(), a.getLisaTransactionId(),
						a.getLisaRequestId(), a.getMatchStyle(), a.getComment(),a.getTime());

				// System.out.println("		check:"+a.toString());
				// System.out.println("		and check:"+aMatch.toString());

				if ((a.getLisaRequestId() == aMatch.getLisaRequestId())
						&& (a.getLisaTransactionId() == aMatch
								.getLisaTransactionId())
						&& (a.getMatchStyle().compareTo(aMatch.getMatchStyle()) == 0)) {
					// System.out.println("	its a match:"+a.toString());
					//newA.addComment(aMatch.getComment());
					blist.add(newA);
					//match = true;
				} else {
					// System.out.println("not a match(adding new):"+aMatch.toString());
					blist.add(newA);
				}
				
			}
			if (!match) {
				blist.add(aMatch);
			}
			// System.out.println("test: "+blist.toString());
			mm.put(aMatch.getServiceImageName(), (ArrayList) blist);
		} else {
			List alist = new ArrayList();
			alist.add(aMatch);
			mm.put(aMatch.getServiceImageName(), (ArrayList) alist);
		}
		updateMatchesScriptMap(aMatch);

	}

	static void writeBackendScriptMap(String txMappingFile){
		  ObjectOutputStream outputStream = null;
		  ObjectInputStream inputStream = null;
			//String outputSerFile = "C:\\sltest\\trunk\\Lisa_Merge_Utils\\TXMapping\\TX_MAPPING_1304_Grid_On_Data.data";
			//matchesExcelFile
			int index = txMappingFile.indexOf(".");//"C:\\sltest\\trunk\\Lisa_Merge_Utils\\TXMapping\\TX_MAPPING_1304_Grid_On_Data.data";
	          String outputSerDataFile = txMappingFile.substring(0, index)+"_Backends_Mapping.data";
	        try {
	            
	            //Construct the LineNumberReader object
	            outputStream = new ObjectOutputStream(new FileOutputStream(outputSerDataFile));
	           
	            
	            outputStream.writeObject(backendMatchedBackendScriptMap);
	    		System.out.println("%%%%%%%%% writeBackendScriptMap write DONE");
            
	    		 	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            //Close the ObjectOutputStream
	            try {
	                if (outputStream != null) {
	                   outputStream.flush();
	                    outputStream.close();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	            
	        }
	}
	/*
	 * search the result hashmap that contains the list of all the transactions
	 * associated with a particular service image
	 */
	static boolean searchMatchesLog(String siName, Transaction stn,
			int lisaTransId, int lisaReqId) {

		if (mm.containsKey(siName)) {
			List<AttScriptMatch> alist = (ArrayList) mm.get(siName);
			Iterator it = alist.iterator();
			while (it.hasNext()) {
				AttScriptMatch obj = (AttScriptMatch) it.next();
				if ((obj.getLisaTransactionId() == lisaTransId)
						&& (obj.getLisaRequestId() == lisaReqId)) {
					obj.addTrans(stn);
					updateMatchedScriptMap(obj.getServiceImageName(),obj.getComment(),stn);
					return true;
				}
			}

		}
		return false;
	}
	
	static String updateMatchedScriptMap(String imageName,String scriptName,Transaction stn){
		//backend_operation_argslength_argsvalue
		String[] arr = imageName.split("_");
		String backendName = arr[0];
		Map<String, HashSet<String>> matchedBackendScriptMap = null;//new HashMap<String, HashSet<String>>();
		if(backendMatchedBackendScriptMap.containsKey(backendName)){
			 matchedBackendScriptMap = backendMatchedBackendScriptMap.get(backendName);
		}else{
			matchedBackendScriptMap = new HashMap<String, HashSet<String>>();
			backendMatchedBackendScriptMap.put(backendName,matchedBackendScriptMap);
		}
		
		
		String key = stn.getRequest().getOperation()+":"+stn.getRequest().getArguments().size()+":"+stn.getRequest().getArguments().toArgumentString();
		System.out.println("???????????? script name:"+scriptName+":Key:"+key);
		if(key.equals("LDAPConnection.search:79:arg0_STRING=ou=markets,ou=people,dc=cingular,dc=com&arg1_INT=2&arg2_STRING=mobile=8179462570&arg3_STRING-ARRAY_STRING_0=cingMobileOLAMRegistrationDate&arg3_STRING-ARRAY_STRING_1=cingMobileMWWPwdChangeRequired&arg3_STRING-ARRAY_STRING_2=cingMobileOLAMIVUDTempPWDCreateTime&arg3_STRING-ARRAY_STRING_3=cingMobileIMEI&arg3_STRING-ARRAY_STRING_4=cingMobileOLAMEmailValidationFlag&arg3_STRING-ARRAY_STRING_5=cingDeviceBearerTechnology&arg3_STRING-ARRAY_STRING_6=cingMobileOLAMNewsletterSubscriptionFlag&arg3_STRING-ARRAY_STRING_7=cingDeviceManufacturer&arg3_STRING-ARRAY_STRING_8=cingMobileOLAMSoftLockedDateTime&arg3_STRING-ARRAY_STRING_9=cingMobileOLAMPrimaryUserIndicator&arg3_STRING-ARRAY_STRING_10=cingMobileOLAMGenderIndicator&arg3_STRING-ARRAY_STRING_11=cingMobileOLAMPreferredBrowseLanguage&arg3_STRING-ARRAY_STRING_12=cingMobileIMEIType&arg3_STRING-ARRAY_STRING_13=cingMobileOLAMRegisteredFlag&arg3_STRING-ARRAY_STRING_14=cingDeviceChangeDate&arg3_STRING-ARRAY_STRING_15=preferredLanguage&arg3_STRING-ARRAY_STRING_16=cingMobileOLAMForumAlias&arg3_STRING-ARRAY_STRING_17=cingMobileOLAMNewsletterDeliveryMethod&arg3_STRING-ARRAY_STRING_18=cingMobileOLAMIVUDVerifiedTime&arg3_STRING-ARRAY_STRING_19=cingMobileOLAMHeritageIndicator&arg3_STRING-ARRAY_STRING_20=cingMobileOLAMLockedFlag&arg3_STRING-ARRAY_STRING_21=cingMobileOLAMSmartLimitIndicator&arg3_STRING-ARRAY_STRING_22=cingMobileOLAMFailedAttemptCount&arg3_STRING-ARRAY_STRING_23=cingDeviceIdentifier&arg3_STRING-ARRAY_STRING_24=cingMobilePrepaidPlatform&arg3_STRING-ARRAY_STRING_25=cingMobileOLAMLastLoginTimeStamp&arg3_STRING-ARRAY_STRING_26=userPassword&arg3_STRING-ARRAY_STRING_27=mobile&arg3_STRING-ARRAY_STRING_28=cingAccountNumber&arg3_STRING-ARRAY_STRING_29=cingAccountMarketIndicator&arg3_STRING-ARRAY_STRING_30=cingAccountSubMarketIndicator&arg3_STRING-ARRAY_STRING_31=uid&arg3_STRING-ARRAY_STRING_32=preferredLanguage&arg3_STRING-ARRAY_STRING_33=entrydn&arg3_STRING-ARRAY_STRING_34=ou&arg3_STRING-ARRAY_STRING_35=dn&arg3_STRING-ARRAY_STRING_36=cingMobileOLAMRegIncent&arg3_STRING-ARRAY_STRING_37=OLAMPBOStatus&arg3_STRING-ARRAY_STRING_38=cingSubscriberStatus&arg3_STRING-ARRAY_STRING_39=cingMobileAcctMgtSys&arg3_STRING-ARRAY_STRING_40=attSQID1&arg3_STRING-ARRAY_STRING_41=attSecAns1&arg3_STRING-ARRAY_STRING_42=vencSecAns1&arg3_STRING-ARRAY_STRING_43=attSQID2&arg3_STRING-ARRAY_STRING_44=attSecAns2&arg3_STRING-ARRAY_STRING_45=vencSecAns2&arg3_STRING-ARRAY_STRING_46=subrRoles&arg3_STRING-ARRAY_STRING_47=cingMobileMAGID&arg3_STRING-ARRAY_STRING_48=cingMobileNumberTimeZone&arg3_STRING-ARRAY_STRING_49=cingMobileFeatureCode&arg3_STRING-ARRAY_STRING_50=sn&arg3_STRING-ARRAY_STRING_51=givenName&arg3_STRING-ARRAY_STRING_52=cingmobileCreationDate&arg3_STRING-ARRAY_STRING_53=LFPPsetNbr&arg3_STRING-ARRAY_STRING_54=LFPPflAutnCnt&arg3_STRING-ARRAY_STRING_55=LFPPlckOutExpDt&arg3_STRING-ARRAY_STRING_56=LFPPlstFlAutnDt&arg3_STRING-ARRAY_STRING_57=LSASsetNbr&arg3_STRING-ARRAY_STRING_58=LSASflAutnCnt&arg3_STRING-ARRAY_STRING_59=LSASlckOutExpDt&arg3_STRING-ARRAY_STRING_60=LSASlstFlAutnDt&arg3_STRING-ARRAY_STRING_61=LPRRsetNbr&arg3_STRING-ARRAY_STRING_62=LPRRflAutnCnt&arg3_STRING-ARRAY_STRING_63=LPRRlckOutExpDt&arg3_STRING-ARRAY_STRING_64=LPRRlstFlAutnDt&arg3_STRING-ARRAY_STRING_65=LCPsetNbr&arg3_STRING-ARRAY_STRING_66=LCPflAutnCnt&arg3_STRING-ARRAY_STRING_67=LCPlckOutExpDt&arg3_STRING-ARRAY_STRING_68=LCPlstFlAutnDt&arg3_STRING-ARRAY_STRING_69=anchrInd&arg3_STRING-ARRAY_STRING_70=mail&arg3_STRING-ARRAY_STRING_71=cingDeviceManufacturer&arg3_STRING-ARRAY_STRING_72=cingDeviceIdentifier&arg3_STRING-ARRAY_STRING_73=cingDeviceBearerTechnology&arg3_STRING-ARRAY_STRING_74=SIMNbr&arg4_BOOLEAN=FALSE")){
			System.out.println("********************************* KEY FOUND");
			
		}
		if (matchedBackendScriptMap.containsKey(key)) {
			System.out.println("???????????? updateMatchedScriptMap Duplicate Key :"+key+": value"+scriptName);
			matchedBackendScriptMap.get(key).add(scriptName);
		}else{
			HashSet<String> scripts = new HashSet<String>();
			scripts.add(scriptName);
			matchedBackendScriptMap.put(key,scripts);
			System.out.println("???????????? updateMatchedScriptMap added Key :"+key +": value"+scriptName);
		}
		return key;
	}
	
	static Map<String, HashSet<String>> matchedScriptMap = new HashMap<String, HashSet<String>>();
	//static Map<String, HashSet<String>> matchedBackendScriptMap = new HashMap<String, HashSet<String>>();
	static Map<String, Map<String, HashSet<String>>> backendMatchedBackendScriptMap = new HashMap<String, Map<String, HashSet<String>>>();
	
	static void updateMatchesScriptMap(AttScriptMatch aMatch){
		String key = getScriptKey(aMatch.getServiceImageName(),aMatch.getLisaTransactionId(),aMatch.getLisaRequestId());
	    String scriptName = aMatch.getComment();
		if (matchedScriptMap.containsKey(key)) {
			System.out.println("############# updateMatchesScriptMap Duplicate Key :"+key+": value"+scriptName);
			matchedScriptMap.get(key).add(scriptName);
		}else{
			HashSet<String> scripts = new HashSet<String>();
			scripts.add(scriptName);
			matchedScriptMap.put(key,scripts);
			System.out.println("############# updateMatchesScriptMap added Key :"+key +": value"+scriptName);
		}
	}
	
	static String getScriptKey(String imageName,int lisaTxId, int lisaReqId){
		String key = imageName+":"+lisaTxId+":"+lisaReqId;
		//System.out.println("############# getScriptKey  Key :"+key);
		return key;
	}
	
	static String getScriptName(String serviceImageName , Transaction sp){
		String key = getScriptKey(serviceImageName,(int)sp.getId(),(int)sp.getRequest().getId());
		HashSet<String> scriptsNames  = matchedScriptMap.get(key);
		StringBuffer scripts = new StringBuffer("");
		
		if(null == scriptsNames){
			System.out.println("############# getScriptName scriptsNames NULL key:"+key);
			return scripts.toString();
		}
		int scriptSize = scriptsNames.size();
		
		Iterator<String> it = scriptsNames.iterator();
		int index =0;
		while(it.hasNext()){
			scripts.append(it.next());
			if( index != (scriptSize- 1)){
				scripts.append(",");
			}
			index++;
		}
		
		System.out.println("############# getScriptName Scripts :"+scripts.toString());
		return scripts.toString();
		
	}
	
	
	
	
	/**
	 * Extract the transaction from source VSI and generate a new VSI
	 * @param si input Service Image
	 * @param vsiOutputFile output Service Image file
	 */
	static void extractTransaction(ServiceImage si, String vsiOutputFile) {
		try {

			// Constructing a new SI using matching specific tx from source SI
			ServiceImage newSi = new ServiceImage();
			newSi.setName(si.getName() + "_Matched");
			newSi.setCreated(new Date());
			newSi.setNotes("This service image created using selected "
					+ "transactions from the " + si.getName() + ":"
					+ si.getId() + " , based on the vse matches excel report");
			newSi.setLastModified(new Date());
			newSi.setVersion(si.getVersion());
			newSi.setUnknownConversationalRequestResponse(si
					.getUnknownConversationalRequestResponse());
			newSi.setUnknownStatelessRequestResponse(si
					.getUnknownStatelessRequestResponse());

			Set<String> resultset = new HashSet<String>();
			int transCountTotal = 0;
			int transCountMatchPattern = 0;

			System.out.println("Processing Service Image name : "
					+ si.getName());

			// Get specific transactions
			List<Transaction> resultTxs = new ArrayList<Transaction>();

			List<TransactionNode> trans = si.getStatelessTransactions();
			Iterator itr = trans.iterator();

			// found a match
			boolean matched = false;
			// found multiple transactions that match same id
			boolean matchedMultiple = false;

			while (itr.hasNext()) {

				// For a transaction
				TransactionNode tn = (TransactionNode) itr.next();
				String resultStr = tn.getRequest().getOperation() + ",";

				CustomTransactionNode newTn = new CustomTransactionNode();
				List<Transaction> matchedTxs = new ArrayList<Transaction>();

				matched = false;
				matchedMultiple = false;

				// Get specific transactions
				List<Transaction> specificTNs = tn.getSpecifics();
				int specificTxCount = specificTNs.size();
				transCountTotal = transCountTotal + specificTxCount;

				// Process the specific transactions
				Iterator specificTxIterator = specificTNs.iterator();
				while (specificTxIterator.hasNext()) {
					Transaction sp = (Transaction) specificTxIterator.next();
					
					
					if (searchMatchesLog(si.getName(), sp, (int) sp.getId(),
							(int) sp.getRequest().getId())) {
						if(sp.getRequest().getOperation().contains("LDAPConnection.search")){
							System.out.println(" LDAP temp Matched args:"+sp.getRequest().getArguments().toArgumentString());
						}
						if (matched == true)
							matchedMultiple = true;
						else
							matched = true;
						resultTxs.add(sp.createCopy());
						Transaction matchedTx = sp.createCopy();

						String script_name = getScriptName(si.getName(),sp);
						/*
						String script_name = "";
						List<AttScriptMatch> alist = (ArrayList) mm.get(si
								.getName());
						for (AttScriptMatch script : alist) {
							if (script.getLisaTransactionId() == (int) sp
									.getId()
									&& script.getLisaRequestId() == (int) sp
											.getRequest().getId()) {
								script_name = script.getComment();
								break;
							}
						}*/

						Request req = matchedTx.getRequest();
						ParameterList pList = req.getMetaData();
						if (pList == null) {
							pList = new ParameterList();
						}

						String[] arr = si.getName().split("_");
						pList.setParameterValue("test.script.name", script_name);
						pList.setParameterValue("source.vsi.date", arr[1]);
						pList.setParameterValue("source.lisa.transaction.id",
								String.valueOf(sp.getId()));

						req.setMetaData(pList);
						Transaction tx = req.getTransaction();

						// matchedTx.setParentTransaction(newTn);
						tx.setParentTransaction(newTn);

						// Set a meta response to TxNode, if not already set
						if (newTn.getResponses().size() == 0) {
							List matchedTxResps = tn.getResponses();
							newTn.addResponse((Response) matchedTxResps.get(0));

						}
						// Set request args to TxNode, if not already set
						if (newTn.getRequest() != null) {
							Request matchedTxReq = tn.getRequest();
							newTn.setRequest(matchedTxReq);
						}
					}
				} // end while sp trans

				if (matched == true) {
					newSi.addStatelessTransaction(newTn);
				}

			} // end while trans

			ServiceImageContext.writeXML(vsiOutputFile, newSi);
			if(null!=newSi && newSi.getName().contains("LDAP")){
				System.out.println("LisaServiceImageCleaner:extractTrasaction():Updating LDAP signature ");
				try{
					LdapSignatureTask.ldapSignUpdate(vsiOutputFile);
				}catch(Exception e){
					System.out.println("LisaServiceImageCleaner:extractTrasaction():Updating LDAP signature got an Exception so printing exception and resuming to next task");
					e.printStackTrace();
				}
				
			}
			System.out.println("Original Service Image Unique stateless "
					+ "transactions : " + si.getStatelessTransactions().size());
			System.out.println("New Service Image Unique stateless "
					+ "transactions : "
					+ newSi.getStatelessTransactions().size());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}

	}

	public static void processAnSerciveImage(String vsiFile, String excelFile,
			String vsiOutputFile) {
		mm = new HashMap<String, ArrayList>();
		try {
			ServiceImage si = ServiceImageContext.readServiceImage(vsiFile);
			readExcelSheet(excelFile, si.getName());
			if (debug) {
				System.out.println("Completed reading Excel Spreadsheet: "
						+ "All transactions in excel sheet are \n"
						+ toStringMM("ALL"));
			}

			extractTransaction(si, vsiOutputFile);
			// if(debug)
			// System.out.println("Completed extracting transactions: " + "
			// ALL transactions in service image are \n"+toStringMM("ALL"));
			System.out.println("Completed extracting transactions: "
					+ "Transactions not found in service image are \n"
					+ toStringMM("NOT_FOUND"));
			if (debug) {
				System.out.println("Completed extracting transactions: "
						+ "Transactions found in service image are \n"
						+ toStringMM("FOUND"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized String createMergedServiceImage(String baseFolderPath,
			String serviceShortName, String transport) {
		fileHash = new HashMap<String, String>();
		File currentDir = new File(baseFolderPath);
		String outputFileName = baseFolderPath + "/MERGED/" + transport
				+ "/Images/" + serviceShortName + "_Merged.vsi";
		LisaServiceImageCleaner.listDirectoryContents(currentDir,
				serviceShortName + "_.*Matched.vsi");

		System.out.println("Merged Service Image File for " + serviceShortName
				+ " is :" + outputFileName);

		String emptyVsiString = "<?xml version=\"1.0\" ?>\n<serviceImage id=\"1\" name=\""
				+ serviceShortName
				+ "_Merged"
				+ "\" created=\"2012-12-30.21:02:20.150\" lastModified=\"2012-12-30.21:02:20.150\" version=\"6.0.9\">\n<ucrr>\n<rp id=\"2\" t=\"0\">\n</rp>\n</ucrr>\n<usrr>\n<rp id=\"3\" t=\"0\">\n</rp>\n</usrr>\n</serviceImage>\n";

		emptyVsiString = getEmptyVSI(transport, serviceShortName + "_Merged");

		// Create empty vsi file
		try {
			// Create file
			FileWriter fstream = new FileWriter(outputFileName);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(emptyVsiString);
			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
		}

		String command = serviceImageManagerPath + " --combine=";
		// command=
		// " /Applications/Lisa/6.0.9.26/bin/ServiceImageManager --combine=";
		command += outputFileName + " ";

		Set set = fileHash.keySet(); // get set-view of keys
		// get iterator
		Iterator itr = set.iterator();
		while (itr.hasNext()) {
			String str = (String) itr.next();
			command += str + " ";
		}
		System.out.println("Command to create Merged Service Image File for "
				+ serviceShortName + " is :" + command);

		try {
			Process cmdProc = Runtime.getRuntime().exec(command);

			BufferedReader stdoutReader = new BufferedReader(
					new InputStreamReader(cmdProc.getInputStream()));
			String line;
			while ((line = stdoutReader.readLine()) != null) {
				// process procs standard output here
			}

			BufferedReader stderrReader = new BufferedReader(
					new InputStreamReader(cmdProc.getErrorStream()));

			while ((line = stderrReader.readLine()) != null) {
				// process procs standard error here
			}

			int retValue = cmdProc.exitValue();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
		}
		return command;

	}

	/*
	 * Read properties file and update the static variables
	 */
	static void readPropertiesFile(String filename) {
		Properties prop = new Properties();

		try {
			// load a properties file
			prop.load(new FileInputStream(filename));

			// Check for required properties
			if (!prop.containsKey("JAVA_BACKEND_NAMES")) {
				System.out.println("ERROR:Properties file doesnot contain "
						+ "required property: JAVA_BACKEND_NAMES");
				System.exit(0);
			}
			if (!prop.containsKey("HTTP_BACKEND_NAMES")) {
				System.out.println("ERROR:Properties file doesnot contain "
						+ "required property: HTTP_BACKEND_NAMES");
				System.exit(0);
			}
			if (!prop.containsKey("MATCHES_EXCEL_FILE")) {
				System.out.println("ERROR:Properties file doesnot contain "
						+ "required property: MATCHES_EXCEL_FILE");
				System.exit(0);
			} else {
				// get the property value for MATCHES_EXCEL_FILE
				String matchesFile = prop.getProperty("MATCHES_EXCEL_FILE");
				if (new File(matchesFile).exists()) {
					matchesExcelFile = matchesFile;
				} else {
					System.out.println("ERROR:Required file MATCHES_EXCEL_FILE "
							+ "doesnot exist :[" + matchesFile + "]");
					System.exit(0);
				}
				
				// get the property value for MATCHES_EXCEL_FILE
				String txMappingFile = prop.getProperty("MATCHES_EXCEL_FILE");
				if (new File(matchesFile).exists()) {
					matchesExcelFile = matchesFile;
				} else {
					System.out.println("ERROR:Required file MATCHES_EXCEL_FILE "
							+ "doesnot exist :[" + matchesFile + "]");
					System.exit(0);
				}
				
				
			}
			if (!prop.containsKey("LISA_PROJECT_VSERVICES_FOLDER_VSI_SOURCE")) {
				System.out.println("ERROR:Properties file doesnot contain "
						+ "required property: "
						+ "LISA_PROJECT_VSERVICES_FOLDER_VSI_SOURCE");
				System.exit(0);
			} else {
				// get the property value for MATCHES_EXCEL_FILE
				String sFile = prop
						.getProperty("LISA_PROJECT_VSERVICES_FOLDER_VSI_SOURCE");
				if (new File(sFile).exists()) {
					sourceVsiFolder = sFile;
				} else {
					System.out.println("ERROR:Required folder "
							+ "LISA_PROJECT_VSERVICES_FOLDER_VSI_SOURCE "
							+ "(specified in properties file) doesnot exist :["
							+ sFile + "]");
					System.exit(0);

				}
			}
			if (!prop
					.containsKey("LISA_PROJECT_VSERVICES_FOLDER_VSI_MERGED_DESTINATION")) {
				System.out.println("ERROR:Properties file doesnot contain "
						+ "required property: "
						+ "LISA_PROJECT_VSERVICES_FOLDER_VSI_MERGED_DESTINATION");
				System.exit(0);
			} else {
				// get the property value for MATCHES_EXCEL_FILE
				String sFile = prop
						.getProperty("LISA_PROJECT_VSERVICES_FOLDER_VSI_MERGED_DESTINATION");
				if (new File(sFile).exists()) {
					destinationVsiFolder = sFile;
				} else {
					System.out
							.println("ERROR:Required folder "
									+ "LISA_PROJECT_VSERVICES_FOLDER_VSI_MERGED_DESTINATION "
									+ "(specified in properties file) doesnot exist :["
									+ sFile + "]");
					System.exit(0);

				}
			}

			if (!prop.containsKey("LISA_SERVICE_IMAGE_MANAGER")) {
				System.out.println("ERROR:Properties file doesnot contain "
						+ "required property: LISA_SERVICE_IMAGE_MANAGER");
				System.exit(0);
			} else {
				// get the property value for MATCHES_EXCEL_FILE
				String sFile = prop.getProperty("LISA_SERVICE_IMAGE_MANAGER");
				if (new File(sFile).exists()) {
					serviceImageManagerPath = sFile;
				} else {
					System.out.println("ERROR:Required file "
							+ "LISA_SERVICE_IMAGE_MANAGER "
							+ "(specified in properties file) "
							+ "doesnot exist :[" + sFile + "]");
					System.exit(0);

				}
			}

			// get the property value for JAVA_BACKEND_NAMES

			String backends = prop.getProperty("JAVA_BACKEND_NAMES");
			String delims = "[,]";
			String[] tokens = backends.split(delims);

			if (tokens.length == 0) {
				System.out.println("WARNING: No JAVA Backends specified in "
						+ "properties file");
			}
			// populate the javaBackendMap
			for (int i = 0; i < tokens.length; i++) {
				javaBackendMap.put(tokens[i], new ArrayList<String>());
			}

			// get the property value for HTTP_BACKEND_NAMES
			backends = prop.getProperty("HTTP_BACKEND_NAMES");
			tokens = backends.split(delims);

			if (tokens.length == 0) {
				System.out.println("WARNING: No HTTP Backends specified in "
						+ "properties file");
			}
			// populate the javaBackendMap
			for (int i = 0; i < tokens.length; i++) {
				httpBackendMap.put(tokens[i], new ArrayList<String>());
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Start the merge process based on the entries in the properties file
	 */
	public static void startMatchesExtractionProcess(String propertiesFileName,String txMappingFile) {
		try {
			// read properties file and perform initialization
			readPropertiesFile(propertiesFileName);
			readExcelSheetForServiceImageNames(matchesExcelFile);

			// print config used by this run
			System.out.println(toStringBackendMaps());

			String vsiFile = "";
			String vsiOutputFile = "";
			String vsiOutputFolder = "";

			// Processing Java Backends
			System.out.println("Start processing Java Backends");
			Set<String> keys = javaBackendMap.keySet();
			Iterator it = keys.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				// System.out.println("Processing Java Backends: "+key );
				List list = (ArrayList) javaBackendMap.get(key);
				if (list != null) {
					Iterator it2 = list.iterator();
					while (it2.hasNext()) {
						String str = (String) it2.next();
						System.out.println("Processing Java Backends: [" + key
								+ "] for [" + str.toString() + "]");

						// set the neames of the input/output vsi files
						vsiFile = sourceVsiFolder + str.toString()
								+ "/JAVA/Images/" + key + "_" + str.toString()
								+ ".vsi";
						vsiOutputFolder = destinationVsiFolder + str.toString()
								+ "/JAVA/Images/";
						vsiOutputFile = vsiOutputFolder + key + "_"
								+ str.toString() + "_Matched.vsi";

						// verify if vsi file exists
						System.out.println("\tvsiFile       = " + vsiFile
								+ "\n\tvsiOutputFile = " + vsiOutputFile);

						if (new File(vsiFile).exists()) {
							if (new File(vsiOutputFolder).exists()) {
								// source and destination exists.. process the
								// images to extract matched transactions
								processAnSerciveImage(vsiFile,
										matchesExcelFile, vsiOutputFile);

							} else {
								System.out.println("ERROR: Destination Folder "
									+ "for Matched VSI file doesnot exist :["
									+ vsiOutputFolder + "]");
							}

						} else {
							System.out.println("ERROR: Source VSI file doesnot "
									+ "exist :[" + vsiFile + "]");
						}

					}// end while dates for backend
				}
			} // end while java backends
			System.out.println("Completed processing Java Backends");

			// Processing http Backends
			System.out.println("Start processing Http Backends");
			keys = httpBackendMap.keySet();
			it = keys.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				List list = (ArrayList) httpBackendMap.get(key);
				if (list != null) {
					Iterator it2 = list.iterator();
					while (it2.hasNext()) {
						String str = (String) it2.next();
						System.out.println("Processing HTTP Backends: [" + key
								+ "] for [" + str.toString() + "]");

						// set the neames of the input/output vsi files
						vsiFile = sourceVsiFolder + str.toString()
								+ "/WEBSERVICES/Images/" + key + "_"
								+ str.toString() + ".vsi";
						vsiOutputFolder = destinationVsiFolder + str.toString()
								+ "/WEBSERVICES/Images/";
						vsiOutputFile = vsiOutputFolder + key + "_"
								+ str.toString() + "_Matched.vsi";

						// verify if vsi file exists
						System.out.println("\tvsiFile       = " + vsiFile
								+ "\n\tvsiOutputFile = " + vsiOutputFile);

						if (new File(vsiFile).exists()) {
							if (new File(vsiOutputFolder).exists()) {
								// source and destination exists.. process the
								// images to extract matched transactions
								processAnSerciveImage(vsiFile,
										matchesExcelFile, vsiOutputFile);

							} else {
								System.out.println("ERROR: Destination Folder "
									+ "for Matched VSI file doesnot exist :["
									+ vsiOutputFolder + "]");
							}
						} else {
							System.out.println("ERROR: Source VSI file "
									+ "doesnot exist :[" + vsiFile + "]");
						}

					}// end while dates for backend
				}
			} // end while http backends
			
			writeBackendScriptMap(txMappingFile);
			System.out.println("Completed processing Http Backends");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void startServiceImageMergeProcess(String propertiesFileName) {
		try {
			// read properties file and perform initialization
			readPropertiesFile(propertiesFileName);
			readExcelSheetForServiceImageNames(matchesExcelFile);

			// print config used by this run
			System.out.println(toStringBackendMaps());

			String vsiFile = "";
			String vsiOutputFile = "";
			String vsiOutputFolder = "";

			// Processing Java Backends
			System.out.println("Start Merge Process for Java Backends");
			Set<String> keys = javaBackendMap.keySet();
			Iterator it = keys.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				// System.out.println("Processing Java Backends: "+key );
				// List list = (ArrayList) javaBackendMap.get(key);
				// if (list != null) {
				// Iterator it2 = list.iterator();
				// if (it2.hasNext()) {
				System.out.println("Processing Java Backends: [" + key + "] ");
				createMergedServiceImage(destinationVsiFolder, key, "JAVA");
				// }

				// }
			} // end while java backends
			System.out.println("Completed Merge Process for Java Backends");

			// Processing http Backends
			System.out.println("Start Merge Process for Http Backends");
			keys = httpBackendMap.keySet();
			it = keys.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				// List list = (ArrayList) httpBackendMap.get(key);
				// if (list != null) {
				// Iterator it2 = list.iterator();
				// if (it2.hasNext()) {
				System.out.println("Processing Http Backends: [" + key + "] ");
				createMergedServiceImage(destinationVsiFolder, key,
						"WEBSERVICES");

				// }
				// }
			} // 
			System.out.println("Completed Merge Process for Http Backends");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static String getEmptyVSI(String protocol, String siName) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss.SSS");
		String dateStr = dateFormat.format(new Date());

		String resultStr = "";
		if ("JAVA".equalsIgnoreCase(protocol)) {
			resultStr = "<?xml version=\"1.0\" ?><serviceImage id=\"1\" name=\""
					+ siName
					+ "\" created=\""
					+ dateStr
					+ "\" lastModified=\""
					+ dateStr + "\" version=\"6.0.10\">";
			resultStr = resultStr
					+ "<ucrr><rp id=\"2\" t=\"0\"><bd>return com.itko.lisa.remote.instrument.Rules.NO_MATCH;</bd></rp></ucrr><usrr><rp id=\"3\" t=\"0\"><bd>return com.itko.lisa.remote.instrument.Rules.NO_MATCH;</bd></rp></usrr></serviceImage>";
		} else if ("WEBSERVICES".equalsIgnoreCase(protocol)) {

			resultStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><serviceImage created=\""
					+ dateStr
					+ "\" id=\"1\" lastModified=\""
					+ dateStr
					+ "\" name=\"" + siName + "\" version=\"6.0.10\">";
			resultStr = resultStr
					+ "<ucrr><rp id=\"2\" t=\"0\"><m><p n=\"HTTP-Response-Code\">200</p><p n=\"HTTP-Response-Code-Text\">OK</p><p n=\"Server\">LISA/Virtual-Environment-Server</p><p n=\"Date\">{{=httpNow()}}</p><p n=\"X-Powered-By\">LISA/{{=lisaVersionString()}}</p><p n=\"Content-Type\">text/xml</p></m>";
			resultStr = resultStr
					+ "<bd>&lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;&lt;S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"&gt;&lt;S:Body&gt;&lt;S:Fault xmlns:ns3=\"http://www.w3.org/2003/05/soap-envelope\"&gt;&lt;faultcode&gt;S:Server&lt;/faultcode&gt;";
			resultStr = resultStr
					+ "&lt;faultstring&gt;The LISA VSE service could not match your request to a recorded request. Consider expanding your service image.&lt;/faultstring&gt;&lt;detail&gt;&lt;ns2:exception xmlns:ns2=\"http://jax-ws.dev.java.net/\" class=\"java.lang.Exception\"&gt;";
			resultStr = resultStr
					+ "&lt;message&gt;The LISA VSE service could not match your request to a recorded request. Consider expanding your service image.&lt;/message&gt;&lt;ns2:stackTrace&gt;&lt;/ns2:stackTrace&gt;&lt;/ns2:exception&gt;&lt;/detail&gt;&lt;/S:Fault&gt;&lt;/S:Body&gt;&lt;/S:Envelope&gt;</bd></rp></ucrr>";
			resultStr = resultStr
					+ "<usrr><rp id=\"3\" t=\"0\"><m><p n=\"HTTP-Response-Code\">404</p><p n=\"HTTP-Response-Code-Text\">Not Found</p><p n=\"Server\">LISA/Virtual-Environment-Server</p><p n=\"Date\">{{=httpNow()}}</p><p n=\"X-Powered-By\">LISA/{{=lisaVersionString()}}</p><p n=\"Content-Type\">text/xml</p></m>";
			resultStr = resultStr
					+ "<bd>&lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;&lt;S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"&gt;&lt;S:Body&gt;&lt;S:Fault xmlns:ns3=\"http://www.w3.org/2003/05/soap-envelope\"&gt;&lt;faultcode&gt;S:Server&lt;/faultcode&gt;";
			resultStr = resultStr
					+ "&lt;faultstring&gt;The LISA VSE service could not match your request to a recorded request. Consider expanding your service image.&lt;/faultstring&gt;&lt;detail&gt;&lt;ns2:exception xmlns:ns2=\"http://jax-ws.dev.java.net/\" class=\"java.lang.Exception\"&gt;";
			resultStr = resultStr
					+ "&lt;message&gt;The LISA VSE service could not match your request to a recorded request. Consider expanding your service image.&lt;/message&gt;&lt;ns2:stackTrace&gt;&lt;/ns2:stackTrace&gt;&lt;/ns2:exception&gt;&lt;/detail&gt;&lt;/S:Fault&gt;&lt;/S:Body&gt;&lt;/S:Envelope&gt;</bd></rp></usrr></serviceImage>";
		}
		return resultStr;
	}

	/*
	 * method to convert a cvs file into an xlsx file outputfileType can be xls
	 * or xlsx
	 */
	public static void convertCsvToXlsx(String inputFile, String outputFile,
			String outputfileType) {
		FileOutputStream fileOut = null;
		InputStream fileIn = null;
		try {
			fileIn = new FileInputStream(inputFile);
			fileOut = new FileOutputStream(outputFile);

			// We know the output type and have the file in the input stream
			// now. We can now convert CSV to XLSX
			CSVReader reader = new CSVReader(fileIn); // reads the input CSV
														// file
			String[] nextLine;
			int lnNum = 0;
			/*
			 * Initialize both XLS and XLSX formats to start with. Only one will
			 * be used
			 */
			HSSFWorkbook new_workbook = new HSSFWorkbook();
			HSSFSheet sheet = new_workbook.createSheet("CSV2XLS");
			XSSFWorkbook new_workbook_xlsx = new XSSFWorkbook();
			XSSFSheet sheet_xlsx = new_workbook_xlsx.createSheet("CSV2XLS");
			Row row;

			Map<String, Object[]> excel_data = new HashMap<String, Object[]>(); // create
																				// a
																				// map
																				// and
																				// define
																				// data
			/* Populate data into logical Map */
			while ((nextLine = reader.getLine()) != null) {

				lnNum++;
				if ((nextLine[4] != "") && (nextLine[5] != ""))
					excel_data.put(
							Integer.toString(lnNum),
							new Object[] { nextLine[0], nextLine[1],
									nextLine[2], nextLine[3],
									Integer.parseInt(nextLine[4]),
									Integer.parseInt(nextLine[5]), nextLine[6],
									nextLine[7], nextLine[8] });
			}
			/* Ready to convert CSV to logical Excel object */
			Set<String> keyset = excel_data.keySet();
			int rownum = 0;
			for (String key : keyset) { // loop through the data and add them to
										// the cell
				if (outputfileType.equalsIgnoreCase("xls")) {
					row = sheet.createRow(rownum++); /*
													 * Create rows in the
													 * doucment using the right
													 * sheet object
													 */
				} else {
					row = sheet_xlsx.createRow(rownum++);
				}
				Object[] objArr = excel_data.get(key);
				int cellnum = 0;
				for (Object obj : objArr) {

					Cell cell = row.createCell(cellnum++);
					if ((cellnum == 5) || (cellnum == 6))
						cell.setCellValue((double) Integer.parseInt(obj
								.toString()));
					else
						cell.setCellValue((String) obj);

					/*
					 * if(obj instanceof Double){
					 * cell.setCellValue((Double)obj); } else
					 * cell.setCellValue((String)obj);
					 */
				}

			}
			/* Write response to output stream */
			if (outputfileType.equalsIgnoreCase("xls")) {
				new_workbook.write(fileOut);
			} else {
				new_workbook_xlsx.write(fileOut);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.toString()); /* Throw exceptions to log files */
		} finally {
			try {
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}/* Close the output stream */
		}

	}

	public static void main(String[] args) {
		PrintWriter outVSI = null;

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String dateStr = dateFormat.format(new Date());

		System.out.println("Start........." + dateStr);
//		String propertiesFile = "c:\\sltest\\trunk\\Lisa_Merge_Utils\\1303_GRID_ON\\lisa-merge-util.properties";
		String propertiesFile = "c:\\sltest\\trunk\\Lisa_Merge_Utils\\1304_GRID_ON\\lisa-merge-util-config.properties";
		
		startMatchesExtractionProcess(propertiesFile,"");
		//startServiceImageMergeProcess(propertiesFile);
		dateStr = dateFormat.format(new Date());
		System.out.println(".........End....." + dateStr);

	}

}
