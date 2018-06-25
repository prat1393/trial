
package com.itko.lisa.ext.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.itko.lisa.dynexec.dtodatasets.XLSUtils;
import com.itko.lisa.ext.enumeration.Column;
import com.itko.lisa.ext.enumeration.Operation;
import com.itko.lisa.ext.handlers.RequestHandler;
import com.itko.lisa.ext.handlers.ResponseHandler;
import com.itko.lisa.ext.vsi.Content;
import com.itko.lisa.ext.vsi.RequestContent;
import com.itko.lisa.ext.vsi.ResponseContent;
import com.itko.lisa.vse.stateful.common.MagicStringGenerator;
import com.itko.lisa.vse.stateful.model.Request;
import com.itko.lisa.vse.stateful.model.ServiceImage;
import com.itko.lisa.vse.stateful.model.ServiceImageCombiner;
import com.itko.lisa.vse.stateful.model.Transaction;
import com.itko.lisa.vse.stateful.model.TransactionNode;
import com.itko.lisa.vse.stateful.model.xml.ServiceImageContext;

/**
 * The responsibility of this executor class includes
 *  - Read and load all gap analysis relevant information from spreadsheet
 *    configuration files
 *  - Identify all the affected VSI and its transactions
 *  - Render the changes in Request and Response contents of each affected
 *    transaction in the service image
 * 
 * By design the complete process has been kept as single thread model. 
 *
 * @author Ashutosh Satyam (ashutosh.satyam@ca.com)
 * @version 1.0
 */
public class GapAnalysisExecutor {
	
	/**
	 * Fixed constants
	 */
	private static final String PROJECT_PATH = "com.att.myworld.sl.project.path.target";
	private static final String XLS_FILE = "GAP_ANALYSIS_EXCEL_FILE";
	private static final String CTXTL_XLS_FILE_PATH = "CTXTL_EXCEL_FILE_PATH";
	
	/**
	 * Default values
	 */
	private static String pathOfProject = "C:\\sltest\\branches\\"
			+ "b1304_P1_myATT_Validation_Merged\\"
			+ "IST_Stubbing_1304\\VServices\\MERGED";

	private static String gapAnalysisExcelFile = null;
	
	private static String ctxlExcelFilePath = "C:\\sltest\\trunk\\"
			+ "Lisa_Merge_Utils\\" + "1304_GRID_ON\\";

	/**
	 * Contains mapping of backend to content to be changed
	 */
	private static final Map<String, ArrayList<Content>> contentMap = 
			new HashMap<String, ArrayList<Content>>();
	
	/**
	 * Read properties file and update all static variables
	 * @param filename
	 */
	private static void readPropertiesFile(String filename) {
		Properties prop = new Properties();

		try {
			// load a properties file
			prop.load(new FileInputStream(filename));
			
			// Check for required properties
			if (!prop.containsKey(PROJECT_PATH)) {
				System.out.println("ERROR:Properties file does not contain "
						+ "required property:" + PROJECT_PATH);
				System.exit(0);
			}
			
			if (!prop.containsKey(XLS_FILE)) {
				System.out.println("ERROR:Properties file does not contain "
						+ "required property: " + XLS_FILE);
				System.exit(0);
			}
			
			if (!prop.containsKey(CTXTL_XLS_FILE_PATH)) {
				System.out.println("ERROR:Properties file does not contain "
						+ "required property: " + CTXTL_XLS_FILE_PATH);
				System.exit(0);
			}

			pathOfProject = getStringProperty(prop,PROJECT_PATH, pathOfProject);
			gapAnalysisExcelFile = prop.getProperty(XLS_FILE);
			ctxlExcelFilePath = getStringProperty(prop, CTXTL_XLS_FILE_PATH,
					ctxlExcelFilePath);

			// Check to see if XLS file exists
			if (gapAnalysisExcelFile == null)
				System.exit(0);
			if (!new File(gapAnalysisExcelFile).exists()) {
				System.out.println("ERROR:Required file " + XLS_FILE
						+ " does not exist:[" + gapAnalysisExcelFile + "]");
				System.exit(0);
			}
			
			//Debug statement
			//System.out.println(prop);
			//System.out.println("Property value read: ");
			//System.out.println(pathOfProject + "\n" + gapAnalysisExcelFile);

		} catch (IOException ex) {
			System.out.println("ERROR: Occured while reading properties file "
					+ filename + " \n Reason: " + ex.getMessage());
			System.out.println("Terminating the Gap Analysis abruptly.");
			System.exit(-1);
		}
	}
	
	private static String getStringProperty(Properties prop, String name,
			String defaultVal) {
		String valStr = prop.getProperty(name, defaultVal);
		if (null != valStr) {
			return valStr.trim();
		}
		return defaultVal;
	}
	
	private static void readMasterWorkbook(String xlsFilename) throws Exception {
		FileInputStream fileIn = new FileInputStream(xlsFilename);
		HSSFWorkbook wb = new HSSFWorkbook(fileIn);
		HSSFSheet sheet = wb.getSheetAt(0);
	
		XLSUtils utils = new XLSUtils(wb);
		
		try {
			int rowCount = 0;
			for (Row row : sheet) {
				//skip the header row
				if (rowCount == 0) {
					++rowCount;
					continue;
				}
				
				//breaking out of spreadsheet reading
				Cell cell0 = row.getCell(0);
				if (cell0 == null) {
					break;
				}
				
				// Read each cell of the sheet from main workbook
				String backend = utils.getCellValue(sheet, row.getRowNum(), 2);
				String opr = utils.getCellValue(sheet, row.getRowNum(), 3);
				String noofarg = utils.getCellValue(sheet, row.getRowNum(), 4);
				String change = utils.getCellValue(sheet, row.getRowNum(), 5);
				String argname = utils.getCellValue(sheet, row.getRowNum(), 6);
				String argvalue = utils.getCellValue(sheet, row.getRowNum(), 7);
				String oprtype = utils.getCellValue(sheet, row.getRowNum(), 8);
				String ctxtmapsheet = utils.getCellValue(sheet,
						row.getRowNum(), 9);
				String noofcolumn = utils.getCellValue(sheet, row.getRowNum(),
						10);
				String ctxtmapcolumn = utils.getCellValue(sheet,
						row.getRowNum(), 11);
				
				//Debug statement
				//System.out.println("=============================");
				//System.out.println("Backend: " + backend);
				//System.out.println("Operation:" + opr);
				//System.out.println("Num of Args: " + noofarg);
				//System.out.println("Applicable Change: " + change);
				//System.out.println("Argument Name: " + argname);
				//System.out.println("Argument Value: " + argvalue);
				//System.out.println("Operation Type: " + oprtype);
				//System.out.println("ContextualMapping Sheet: " + ctxtmapsheet);
				//System.out.println("No. of Column in Sheet: " + noofcolumn);
				//System.out.println("Column to read in Sheet: " + ctxtmapcolumn);
				//System.out.println("\n");
				
				Content pojo = new Content();
				pojo.setOprname(opr);
				if (noofarg != null) {
					pojo.setNoofargs(Integer.parseInt(noofarg));
				} else {
					pojo.setNoofargs(0);
				}
				
				if (contentMap.containsKey(backend)) {
					//System.out.println("Backend exists: " + backend);
					ArrayList<Content> list = contentMap.get(backend);
					if (list.contains(pojo)) {
						//System.out.println("Content already exists : "
						//		+ " OperationName and No. of Args are same");
						//Need to add new RequestContent or ResponseContent
						//to the existing Content object
						Content cont = list.get(list.indexOf(pojo));
						if (change.equals("Req")) {
							List<RequestContent> reqList = cont.getReqContent();
							RequestContent req = new RequestContent();
							req.setArgname(argname);
							if (argvalue != null) { 
								req.setArgvalue(argvalue);
							}
							if (oprtype.equals("INSERT")) {
								req.setOpr(Operation.INSERT);
							} else if (oprtype.equals("DELETE")) {
								req.setOpr(Operation.DELETE);
							} else if (oprtype.equals("UPDATE")) {
								req.setOpr(Operation.UPDATE);
							}
							
							if (ctxtmapsheet != null) {
								String xlsFile = ctxlExcelFilePath
										.concat(ctxtmapsheet);
								FileInputStream fis = new FileInputStream(xlsFile);
								try {
									HSSFWorkbook ctxtwb = new HSSFWorkbook(fis);
									readCtxtlSheetForReq(ctxtwb,
											Integer.parseInt(noofcolumn),
											Column.valueOf(ctxtmapcolumn), req);
								} finally {
									fis.close();
								}
							}
							
							if (reqList == null) {
								cont.addReqContent(req);
							} else {
								reqList.add(req);
							}
							
						} else {
							List<ResponseContent> rspList = cont.getRspContent();
							ResponseContent rsp = new ResponseContent();
							rsp.setXpath(argname);
							if (argvalue != null) {
								rsp.setXpathvalue(argvalue);
							}
							
							if (oprtype.equals("INSERT")) {
								rsp.setOpr(Operation.INSERT);
							} else if (oprtype.equals("DELETE")){
								rsp.setOpr(Operation.DELETE);
							} else if (oprtype.equals("UPDATE")){
								rsp.setOpr(Operation.UPDATE);
							}

							if (ctxtmapsheet != null) {
								String xlsFile = ctxlExcelFilePath
										.concat(ctxtmapsheet);
								FileInputStream fis = new FileInputStream(xlsFile);
								try {
									HSSFWorkbook ctxtwb = new HSSFWorkbook(fis);
									readCtxtlSheetForRsp(ctxtwb,
											Integer.parseInt(noofcolumn),
											Column.valueOf(ctxtmapcolumn), rsp);
								} finally {
									fis.close();
								}
							}
							
							if (rspList == null) {
								cont.addRspContent(rsp);
							} else {
								rspList.add(rsp);
							}
						}
						
					} else {
						//its a new Content
						//see if RequestContent or ResponseContent needs to be added
						//System.out.println("Content does not exists : "
						//		+ " OperationName and No. of Args are different");
						if (change.equals("Req")) {
							List<RequestContent> reqContent = new 
									ArrayList<RequestContent>();
							RequestContent req = new RequestContent();
							req.setArgname(argname);
							if (argvalue != null) {
								req.setArgvalue(argvalue);
							}
							
							if (oprtype.equals("INSERT")) {
								req.setOpr(Operation.INSERT);
							} else if (oprtype.equals("DELETE")) {
								req.setOpr(Operation.DELETE);
							} else if (oprtype.equals("UPDATE")) {
								req.setOpr(Operation.UPDATE);
							}

							if (ctxtmapsheet != null) {
								// read the contextual map sheet
								// construct ctxtKeys
								// construct ctxtMap
								String xlsFile = ctxlExcelFilePath
										.concat(ctxtmapsheet);
								FileInputStream fis = new FileInputStream(xlsFile);
								try {
									HSSFWorkbook ctxtwb = new HSSFWorkbook(fis);
									readCtxtlSheetForReq(ctxtwb,
											Integer.parseInt(noofcolumn),
											Column.valueOf(ctxtmapcolumn), req);
								} finally {
									fis.close();
								}
							}
							reqContent.add(req);
							pojo.setReqContent(reqContent);
							
						} else {
							List<ResponseContent> rspContent = new 
									ArrayList<ResponseContent>();
							ResponseContent rsp = new ResponseContent();
							rsp.setXpath(argname);
							if (argvalue != null) {
								rsp.setXpathvalue(argvalue);
							}
							
							if (oprtype.equals("INSERT")) {
								rsp.setOpr(Operation.INSERT);
							} else if (oprtype.equals("DELETE")) {
								rsp.setOpr(Operation.DELETE);
							} else if (oprtype.equals("UPDATE")) {
								rsp.setOpr(Operation.UPDATE);
							}

							if (ctxtmapsheet != null) {
								// read the contextual map sheet
								// construct ctxtKeys
								// construct ctxtMap
								String xlsFile = ctxlExcelFilePath
										.concat(ctxtmapsheet);
								FileInputStream fis = new FileInputStream(xlsFile);
								try {
									HSSFWorkbook ctxtwb = new HSSFWorkbook(fis);
									readCtxtlSheetForRsp(ctxtwb,
											Integer.parseInt(noofcolumn),
											Column.valueOf(ctxtmapcolumn), rsp);
								} finally {
									fis.close();
								}
							}
							rspContent.add(rsp);
							pojo.setRspContent(rspContent);
						}
						
						//System.out.println("New Content created to be added to "
						//		+ "existing backend: " + pojo);
						list.add(pojo);
					}
					
				} else {
					ArrayList<Content> list = new ArrayList<Content>();
					
					if (change.equals("Req")) {
						List<RequestContent> reqContent = new 
								ArrayList<RequestContent>();
						RequestContent req = new RequestContent();
						req.setArgname(argname);
						if (argvalue != null) {
							req.setArgvalue(argvalue);
						}
						if (oprtype.equals("INSERT")) {
							req.setOpr(Operation.INSERT);
						} else if (oprtype.equals("DELETE")) {
							req.setOpr(Operation.DELETE);
						} else if (oprtype.equals("UPDATE")) {
							req.setOpr(Operation.UPDATE);
						}
						if (ctxtmapsheet != null) {
							String xlsFile = ctxlExcelFilePath
									.concat(ctxtmapsheet);
							FileInputStream fis = new FileInputStream(xlsFile);
							try {
								HSSFWorkbook ctxtwb = new HSSFWorkbook(fis);
								readCtxtlSheetForReq(ctxtwb,
										Integer.parseInt(noofcolumn),
										Column.valueOf(ctxtmapcolumn), req);
							} finally {
								fis.close();
							}
						}
						reqContent.add(req);
						pojo.setReqContent(reqContent);
						
					} else {
						List<ResponseContent> rspContent = new 
								ArrayList<ResponseContent>();
						ResponseContent rsp = new ResponseContent();
						rsp.setXpath(argname);
						if (argvalue != null) {
							rsp.setXpathvalue(argvalue);
						}
						
						if (oprtype.equals("INSERT")) {
							rsp.setOpr(Operation.INSERT);
						} else if (oprtype.equals("DELETE")) {
							rsp.setOpr(Operation.DELETE);
						} else if (oprtype.equals("UPDATE")) {
							rsp.setOpr(Operation.UPDATE);
						}

						if (ctxtmapsheet != null) {
							String xlsFile = ctxlExcelFilePath
									.concat(ctxtmapsheet);
							FileInputStream fis = new FileInputStream(xlsFile);
							try {
								HSSFWorkbook ctxtwb = new HSSFWorkbook(fis);
								readCtxtlSheetForRsp(ctxtwb,
										Integer.parseInt(noofcolumn),
										Column.valueOf(ctxtmapcolumn), rsp);
							} finally {
								fis.close();
							}
						}
						rspContent.add(rsp);
						pojo.setRspContent(rspContent);
						
					}
					
					list.add(pojo);
					contentMap.put(backend, list);
				}
			} // end Row
		} finally {
			fileIn.close();
		}
		
		System.out.println("Total number of backend to be affected: "
				+ contentMap.size());
		System.out.println("Spreadsheet loaded in memory: " + contentMap);
	}
	
	private static void readCtxtlSheetForReq(HSSFWorkbook wb, int noofcolumn,
			Column col, RequestContent req) {
		HSSFSheet sheet = wb.getSheetAt(0);
		XLSUtils xls = new XLSUtils(wb);
		
		int colIdx = col.getIndex();
		int rowCount = 0;
		
		List<String> keys = new ArrayList<String>();
		Map<String,String> ctxtMap = new HashMap<String,String>();
		
		
		for (Row row : sheet) {
			int colCountOuter = 0;
			StringBuilder sb = new StringBuilder();
			
			//Header needs to be read differently
			if (rowCount == 0) {
				int colCount = 0;
				while (colCount < colIdx) {
					String cellValue = xls.getCellValue(sheet, row.getRowNum(),
							colCount);
					String[] arr = cellValue.split(":");
					if (arr[0].equals("Key")) {
						String key = arr[1];
						keys.add(key);
					} else if (arr[0].equals("Value")) {
						break;
					}
					++colCount;
				}
				req.setCtxtKeys(keys);
				++rowCount;
				continue;
			}
			
			//breaking out of spreadsheet reading
			Cell cell0 = row.getCell(0);
			if (cell0 == null) {
				break;
			}
			
			while (colCountOuter < colIdx) {
				String cellValue = xls.getCellValue(sheet, row.getRowNum(),
						colCountOuter);
				sb.append(keys.get(colCountOuter));
				sb.append(":");
				sb.append(cellValue);
				if (colCountOuter < (keys.size() - 1)) {
					sb.append("::");
				}
				++colCountOuter;
				
				if (colCountOuter == keys.size())
					break;
			}
			
			String value = xls.getCellValue(sheet, row.getRowNum(),colIdx);
			//System.out.println("Adding following entry to Map: "
			//		+ sb.toString() + " " + value);
			ctxtMap.put(sb.toString(),value);
		}
		req.setCtxtMap(ctxtMap);
	}
	
	private static void readCtxtlSheetForRsp(HSSFWorkbook wb, int noofcolumn,
			Column col, ResponseContent rsp) {
		HSSFSheet sheet = wb.getSheetAt(0);
		XLSUtils xls = new XLSUtils(wb);

		int colIdx = col.getIndex();
		int rowCount = 0;

		List<String> keys = new ArrayList<String>();
		Map<String, String> ctxtMap = new HashMap<String, String>();

		for (Row row : sheet) {

			int colCountOuter = 0;
			StringBuilder sb = new StringBuilder();

			// Header needs to be read differently
			if (rowCount == 0) {
				int colCount = 0;
				while (colCount < colIdx) {
					String cellValue = xls.getCellValue(sheet, row.getRowNum(),
							colCount);
					String[] arr = cellValue.split(":");
					if (arr[0].equals("Key")) {
						String key = arr[1];
						keys.add(key);
					} else {
						// Done with reading of keys
						break;
					}
					++colCount;
				}
				rsp.setCtxtKeys(keys);
				++rowCount;
				continue;
			}

			// breaking out of spreadsheet reading
			Cell cell0 = row.getCell(0);
			if (cell0 == null) {
				break;
			}

			while (colCountOuter < colIdx) {
				String cellValue = xls.getCellValue(sheet, row.getRowNum(),
						colCountOuter);
				sb.append(keys.get(colCountOuter));
				sb.append(":");
				sb.append(cellValue);
				if (colCountOuter < (keys.size() - 1)) {
					sb.append("::");
				}
				++colCountOuter;

				if (colCountOuter == keys.size())
					break;
			}

			String mapValue = null;
			if (rsp.getOpr().equals(Operation.INSERT)
					|| rsp.getOpr().equals(Operation.UPDATE)) {
				String xpath = xls.getCellValue(sheet, row.getRowNum(), colIdx);
				String xvalue = xls.getCellValue(sheet, row.getRowNum(),
						colIdx + 1);
				mapValue = xpath.concat(":").concat(xvalue);
			} else if (rsp.getOpr().equals(Operation.DELETE)) {
				String xpath = xls.getCellValue(sheet, row.getRowNum(), colIdx);
				mapValue = xpath;
			}
			
			System.out.println("Adding following entry to Map: "
					+ sb.toString() + " " + mapValue);
			ctxtMap.put(sb.toString(), mapValue);
		}
		rsp.setCtxtMap(ctxtMap);
	}
	
	/**
	 * Entry point to analyze the required changes and then render those
	 * changes in the affected service images.
	 * @param propertiesFileName
	 */
	public static void analyzeAndRenderSIChanges(String propertiesFileName) {
		
		try {
			// read properties file and perform initialization
			readPropertiesFile(propertiesFileName);
			readMasterWorkbook(gapAnalysisExcelFile);
			
			for (String backend: contentMap.keySet()) {
				String bkd = backend.concat("_Merged.vsi");
				System.out.println("Starting to update the vsi: " + bkd);
				@SuppressWarnings("unchecked")
				Collection<File> vsmFileColl = FileUtils.listFiles(new File(
						pathOfProject), FileFilterUtils.nameFileFilter(bkd),
						TrueFileFilter.INSTANCE);
				File vsi = vsmFileColl.iterator().next();
				System.out.println("Path of vsi file: " + vsi.getAbsolutePath());
				ServiceImage si = ServiceImageContext.readServiceImage(vsi
						.getAbsolutePath());
				List<TransactionNode> stTxs = si.getStatelessTransactions();
				
				ArrayList<Content> contentList = contentMap.get(backend);
				List<TransactionNode> delTxNodeList = new ArrayList<TransactionNode>();
				List<ServiceImage> siList = new ArrayList<ServiceImage>();
				for (Content cont : contentList) {
					String opr = cont.getOprname();
					int noofargs = cont.getNoofargs();
					
					for (TransactionNode tnode : stTxs) {
						Request metaReq = tnode.getRequest();
						boolean oprmatch = metaReq.getOperation().equals(opr);
						boolean argcountmatch = metaReq.getArguments().size() == noofargs;
						boolean match = false;
						
						List<Transaction> spTxs = null;
						switch (noofargs) {
						case 0:
							if (oprmatch) {
								spTxs = tnode.getSpecifics();
								match = true;
							}
							break;
						default:
							if (oprmatch && argcountmatch) {
								spTxs = tnode.getSpecifics();
								match = true;
							}
							break;
						}
						
						if (match) {
							System.out.println("Matching Transaction Node "
									+ "found: " + tnode.getId());
							boolean reqflag = cont.getReqContent() != null
									&& !cont.getReqContent().isEmpty();
							ServiceImage newSi = null;
							
							if (reqflag) {
								System.out.println("Changes to Request exists.");
								System.out.println("First making changes to "
									+ " meta transaction using RequestHandler");
								RequestHandler.handleMetaRequest(
										cont.getReqContent(), tnode);
								System.out.println("Will now iteratively pass"
										+ " each specific transaction"
										+ " to Request Handler");
								for (Transaction txn : spTxs) {
									RequestHandler.handlleRequest(
											cont.getReqContent(), txn);
								}
								
								//extract and create new service image
								//add new service image to list for combining later
								//add the particular transaction node to list for deleting later
								newSi = createNewServiceImage(si, tnode);
								System.out
										.println("Extracted the changes to "
												+ "new service image: "
												+ newSi.getName());
								delTxNodeList.add(tnode);
								siList.add(newSi);
							}
							
							boolean rspflag = cont.getRspContent() != null
									&& !cont.getRspContent().isEmpty();

							if (rspflag && reqflag) {
								System.out.println("Changes to Response exists.");
								List<TransactionNode> newStTx = newSi
										.getStatelessTransactions();
								for (TransactionNode newtxnode : newStTx) {
									System.out.println("First making changes  "
											+ "to meta transaction using "
											+ "ResponseHandler");
									ResponseHandler.handleMetaResponse(
											cont.getRspContent(), newtxnode);
									System.out.println("Will now iteratively "
											+ "pass each specific transaction"
											+ " to Response Handler");
									List<Transaction> newSpTx = newtxnode
											.getSpecifics();
									for (Transaction newtxn : newSpTx) {
										ResponseHandler.handleResponse(
												cont.getRspContent(), newtxn);
									}
								}
							} else if (rspflag) {
								System.out.println("Changes to Response exists.");
								System.out.println("First making changes to "
									+ " meta transaction using ResponseHandler");
								ResponseHandler.handleMetaResponse(
										cont.getRspContent(), tnode);
								System.out.println("Will now iteratively pass"
										+ " each specific transaction"
										+ " to Response Handler");
								for (Transaction txn : spTxs) {
									// pass transaction from original SI
									ResponseHandler.handleResponse(
											cont.getRspContent(), txn);
								}
							}
							
							//break from transaction node looping
							if (noofargs != 0)
								break;
						}				
						
					}//end-of-TransactionNode
							
				}//end-of-Content loop
				
				if (!siList.isEmpty()) {
					//combine all the images if extracted
					System.out.println("Combining service images[count:"
							+ siList.size() + "] with " + vsi.getName());
					doCombine(vsi, true, siList);
					//Remove the touched txNode
					System.out.println("Removing the deprecated transaction "
							+ "nodes from " + vsi.getName());
					System.out.println("Transaction NodeId(s) to be deleted are: ");
					for (TransactionNode nd : delTxNodeList) {
						System.out.println(nd.getId());
					}
					removeTxNode(vsi,delTxNodeList);
					//create magic strings
					System.out.println("Generating magic strings for vsi: "
							+ vsi.getName());
					createMagicString(vsi);
				} else {
					//implies only changes are in response
					System.out.println("Only changes in response was found");
					System.out.println("Saving the updated service image "
							+ si.getName());
					ServiceImageContext.writeXML(vsi.getAbsolutePath(), si);
				}
				
				System.out.println("Updates completed for the vsi: " + bkd);
			}//end-of-backend loop

		} catch (Exception e) {
			System.out.println("Error encountered: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Utility function to create a new Service Image using the extracted
	 * Transaction Node and the original Service Image
	 * 
	 * @param si 	 Service Image to construct from
	 * @param newTxn Transaction Node to add to new Service Image
	 * @return @see ServiceImage
	 */
	private static ServiceImage createNewServiceImage(ServiceImage si,
			TransactionNode newTxn) {
		ServiceImage newSi = new ServiceImage();
		newSi.setName(si.getName() + newTxn.getId());
		newSi.setCreated(new Date());
		newSi.setLastModified(new Date());
		newSi.setVersion(si.getVersion());
		newSi.setUnknownConversationalRequestResponse(si
				.getUnknownConversationalRequestResponse());
		newSi.setUnknownStatelessRequestResponse(si
				.getUnknownStatelessRequestResponse());
		newSi.addStatelessTransaction(newTxn);
		
		//Note: Enable below code only for debugging purpose if required
		//This will generate new extracted service image files
		//New file generated is in same directory as original service image
		//and is of the format "BackendName_Merged_$TxNode.Id_New.vsi"
		
		/*@SuppressWarnings("unchecked")
		Collection<File> vsmFileColl = FileUtils.listFiles(new File(
				pathOfProject), FileFilterUtils.nameFileFilter(si.getName()
				+ ".vsi"), TrueFileFilter.INSTANCE);
		File vsi = vsmFileColl.iterator().next();
		String vsiFile = vsi.getAbsolutePath();
		String basePath = vsiFile.substring(0, vsiFile.lastIndexOf("/") + 1);
		vsiFile = vsiFile.substring(vsiFile.lastIndexOf("/") + 1);
		vsiFile = vsiFile.substring(0, vsiFile.indexOf("."));
		String newfileName = vsiFile + "_" + newTxn.getId() + "_NEW.vsi";
		String newfilePath = basePath + newfileName;
		System.out.println("New File Path: " + newfilePath);
		ServiceImageContext.writeXML(newfilePath, newSi);*/
		
		return newSi;
	}
	
	/**
	 * Combines a list of service image file with a provided target 
	 * service image file. The outcome is target service image file
	 * will have merged contents from all other source service image.
	 * 
	 * @param targetSIFile  target service image file
	 * @param favorSource   boolean to favor source image file or not
	 * @param sourceSI      list of source service image to combine with
	 */
	private static void doCombine(File targetSIFile, boolean favorSource,
			List<ServiceImage> sourceSI) {
		ServiceImageCombiner combiner = new ServiceImageCombiner(
				targetSIFile.getAbsolutePath(), favorSource);
		ServiceImage source;
		for (Iterator<ServiceImage> i$ = sourceSI.iterator(); i$.hasNext(); 
				combiner.combineWith(source)) {
			source = (ServiceImage) i$.next();
		}
		combiner.save();
	}
	
	
	/**
	 * Removes the passed transaction node if available in given Service Image
	 * 
	 * @param vsi Service Image file from which nodes has to be removed
	 * @param txNodeList List of transaction nodes to be removed
	 */
	private static void removeTxNode(File vsi, List<TransactionNode> txNodeList) {
		ServiceImage si = ServiceImageContext.readServiceImage(vsi
				.getAbsolutePath());
		List<TransactionNode> stTrans = si.getStatelessTransactions();
		Iterator<TransactionNode> itr = stTrans.iterator();
		while (itr.hasNext()) {
			TransactionNode tn = itr.next();
			long id = tn.getId();
			Request metaReq = tn.getRequest();
			String operation = metaReq.getOperation();
			// check if its a matching transaction node
			for (TransactionNode tnDel : txNodeList) {
				long idDel = tnDel.getId();
				Request metaReqDel = tnDel.getRequest();
				String operationDel = metaReqDel.getOperation();
				if (id == idDel && operation.equals(operationDel)) {
					System.out.println("Matched Transaction Node: " + id
							+ " Hence removing it from image.");
					itr.remove();
					break;
				}
			}
		}
		ServiceImageContext.writeXML(vsi.getAbsolutePath(), si);
	}

	/**
	 * Generates magic strings for each transaction in the given Service Image
	 * @param vsi Service Image file
	 */
	private static void createMagicString(File vsi) {
		ServiceImage si = ServiceImageContext.readServiceImage(vsi
				.getAbsolutePath());
		List<TransactionNode> stTxs = si.getStatelessTransactions();
		for (TransactionNode tnode : stTxs) {
			MagicStringGenerator.createMagicStrings(tnode);
			List<Transaction> spTxs = tnode.getSpecifics();
			MagicStringGenerator.createMagicStrings(spTxs.iterator());
		}
		ServiceImageContext.writeXML(vsi.getAbsolutePath(), si);
	}

}
