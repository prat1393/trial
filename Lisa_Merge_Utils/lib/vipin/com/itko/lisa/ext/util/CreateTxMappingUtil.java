package com.itko.lisa.ext.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.itko.lisa.matches.TxMappingPojo;
import com.itko.lisa.vse.stateful.model.ServiceImage;
import com.itko.lisa.vse.stateful.model.xml.ServiceImageContext;

import java.util.concurrent.Semaphore;
import org.apache.commons.io.FileUtils;

import com.itko.lisa.vse.stateful.model.ArgumentType;
import com.itko.lisa.vse.stateful.model.ArgumentType.Operator;
import com.itko.lisa.vse.stateful.model.Request;
import com.itko.lisa.vse.stateful.model.Transaction;
import com.itko.lisa.vse.stateful.model.TransactionNode;
import com.itko.util.Parameter;
import com.itko.util.ParameterList;
public class CreateTxMappingUtil {
	
	
	/**
	 * Fixed constants
	 */
	private static final String VSI_FILE_EXTN = "vsi";


	private static  Map<String, ArrayList<TxMappingPojo>> backendTxMap = 
			new HashMap<String, ArrayList<TxMappingPojo>>();
	private static final List<Runnable> processes = new ArrayList<Runnable>();
	
	public static Semaphore sph = new Semaphore(1,true);

	//private static Map<String, HashSet<String>> matchedBackendScriptMap = null;//new HashMap<String, HashSet<String>>();
	static Map<String, Map<String, HashSet<String>>> backendMatchedBackendScriptMap = null;//new HashMap<String, Map<String, HashSet<String>>>();
	static Map<String, HashSet<String>> allScriptMap = new HashMap<String, HashSet<String>>();

	static synchronized void  readBackendScriptMap(String serFilesSeparatedByComma){
		if(null != backendMatchedBackendScriptMap){
			return;
		}
		String [] allDataFiles = serFilesSeparatedByComma.split(",");
		List<Map<String, Map<String, HashSet<String>>>> mapFiles = new ArrayList<Map<String,Map<String,HashSet<String>>>>();
		for(int index =0; index < allDataFiles.length; index++){
			mapFiles.add(readBackendScriptMap1(allDataFiles[index]));
		}
		//now make one mapFile..
		//backendMatchedBackendScriptMap = new  HashMap<String, Map<String, HashSet<String>>>();
		Iterator<Map<String, Map<String, HashSet<String>>>> it =mapFiles.iterator();
		Map<String, Map<String, HashSet<String>>> mapFile = null;
		while(it.hasNext()){
			mapFile = it.next();
			mergeSerFiles(mapFile);
		}

	}
	
	private static void mergeSerFiles(Map<String, Map<String, HashSet<String>>> mapFile){
		if(backendMatchedBackendScriptMap == null){
			//first one..
			backendMatchedBackendScriptMap = mapFile;
			return;
		}
		Iterator<String> backends = mapFile.keySet().iterator();//all backends
		while(backends.hasNext()){
			String backendName = backends.next();
			Map<String, HashSet<String>> backendScriptsMap = mapFile.get(backendName);
			//get the master bakendScriptMap 
			Map<String, HashSet<String>> masterBackendScriptsMap = backendMatchedBackendScriptMap.get(backendName);
			if(null == masterBackendScriptsMap){
				//this backend tx not present in master map
				backendMatchedBackendScriptMap.put(backendName,backendScriptsMap);
				continue;
			}
			
			
			Iterator<String> keysIt = backendScriptsMap.keySet().iterator();//all unique keys for that backend Operation+ArgsSize+allargs
			while(keysIt.hasNext()){
				String key = keysIt.next();
				HashSet<String> allScriptsPerOperationSet = backendScriptsMap.get(key);
				//get the master allScriptsPerOperationSet
				HashSet<String> masterAllScriptsPerOperationSet = masterBackendScriptsMap.get(key);
				if(null == masterAllScriptsPerOperationSet){
					//we don't have this API in the master list so add all the scripts from 
					masterBackendScriptsMap.put(key, allScriptsPerOperationSet);
				}else{
					//we have the script names for this key
					masterAllScriptsPerOperationSet.addAll(allScriptsPerOperationSet);
				}
			}
		}

	}
	static Map<String, Map<String, HashSet<String>>>  readBackendScriptMap1(String inputSerDataFile){
		
		Map<String, Map<String, HashSet<String>>> mapFile = null;
		ObjectOutputStream outputStream = null;
		  ObjectInputStream inputStream = null;
			
	        try {
	                   
	            inputStream = new ObjectInputStream(new FileInputStream(inputSerDataFile));
	            Object obj = inputStream.readObject();
	            mapFile = (Map<String, Map<String, HashSet<String>>>)obj;
	    		//System.out.println("%%%%%%%%% readBackendScriptMap read DONE:"+backendMatchedBackendScriptMap);
	           // printScriptNames();	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            
	            try {
	                if (inputStream != null) {
	                	
	                	inputStream.close();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	        return mapFile;
	}
	
	static private void printScriptNames(){
		Iterator<String> backends = backendMatchedBackendScriptMap.keySet().iterator();//all backends
		System.out.println("######### Start Scripts from ser file #############");
		while(backends.hasNext()){
			String backendName = backends.next();
			Map<String, HashSet<String>> backendScriptsMap = backendMatchedBackendScriptMap.get(backendName);
			
			Iterator<String> keysIt = backendScriptsMap.keySet().iterator();//all unique keys for that backend Operation+ArgsSize+allargs
			while(keysIt.hasNext()){
				String key = keysIt.next();
				HashSet<String> allScriptsPerOperationSet = backendScriptsMap.get(key);
				System.out.println(allScriptsPerOperationSet);
			}
					
			
		}
		System.out.println("######### End Scripts from ser file #############");
		
	}
	
	private  void updateTxMap(String backend,
			ArrayList<TxMappingPojo> pojoList) {
		if (backendTxMap.containsKey(backend)) {
			//System.out.println("Duplicate KEY for backend:"+backend);
		} else {
			backendTxMap.put(backend,pojoList);
			//System.out.println("Backend Name:"+backend + " Transaction Count:"+pojoList.size());
		}
	}
	
	public static void main(String[] args){
//		String serFiles = "C:\\sltest\\trunk\\Lisa_Merge_Utils\\TXMapping\\TX_MAPPING_1304_Grid_On_Backends_Mapping.data";
//		String txMappingXlsFile = "C:\\sltest\\trunk\\Lisa_Merge_Utils\\TXMapping\\TX_MAPPING_1304_Grid_On.xls";
//		String vsiBasePath = "C:\\sltest\\branches\\b1304_P1_myATT_Validation_Merged\\IST_Stubbing_1304\\VServices\\";
//		new CreateTxMappingUtil().createTxMapping(vsiBasePath, txMappingXlsFile,serFiles);
		
		String serFiles = "C:\\sltest\\trunk\\Lisa_Merge_Utils\\TXMapping\\TX_MAPPING_1303_Grid_On_Backends_Mapping.data,C:\\sltest\\trunk\\Lisa_Merge_Utils\\TXMapping\\TX_MAPPING_1303_Grid_On_Round2_Backends_Mapping.data";
		String txMappingXlsFile = "C:\\sltest\\trunk\\Lisa_Merge_Utils\\TXMapping\\TX_MAPPING_1303_Grid_On_Round2.xls";
		String vsiBasePath = "C:\\sltest\\branches\\b1303_P1_myATT_Validation_Merged_Round2\\IST_Stubbing_1303_Merged_Round2\\VServices\\";
		new CreateTxMappingUtil().createTxMapping(vsiBasePath, txMappingXlsFile,serFiles);
	}
	
	public  void createTxMapping(String vsiBasePath, String txMappingXlsFile,String serFiles) {

		String webserviceVSIPath = vsiBasePath+"MERGED\\WEBSERVICES\\Images\\";
		String javaVSIPath = vsiBasePath+"MERGED\\JAVA\\Images\\";
		try {
				
				System.out.println(" JAVA Merged VSI Path:"+javaVSIPath);
				System.out.println(" webserviceVSIPath Merged VSI Path:"+webserviceVSIPath);
				
				Collection<File> javaVSIFileColl = FileUtils.listFiles(new File(javaVSIPath), new String[]{VSI_FILE_EXTN}, true);
				
				Collection<File> webserviceVSIFileColl= FileUtils.listFiles(new File(webserviceVSIPath), new String[]{VSI_FILE_EXTN}, true);
				List<File> allVSIFiles = new ArrayList<File>();
				allVSIFiles.addAll(javaVSIFileColl);
				allVSIFiles.addAll(webserviceVSIFileColl);
				//read the tx mapping object
				readBackendScriptMap(serFiles);
				
				for (File vsiFile : allVSIFiles) {
					Runnable runnable = new TxMappingTask(vsiFile);
					processes.add(runnable);
				}

				System.out.println("Total number of runnable tasks created: "
						+ processes.size());

				long timeTaken = timeTasks(processes);
				createTxMappingSerFile(txMappingXlsFile);
				createTxMappingXlsFile(txMappingXlsFile);
				long min = timeTaken / 1000;
				long sec = min % 60;
				min = min / 60;
				System.out.println("Processing Done, total time taken " + min
						+ " minute " + sec + " second");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private void createTxMappingSerFile(String outputXlsFile){
		
			  ObjectOutputStream outputStream = null;
			  ObjectInputStream inputStream = null;
				int index = outputXlsFile.indexOf(".");//"C:\\sltest\\trunk\\Lisa_Merge_Utils\\TXMapping\\TX_MAPPING_1304_Grid_On_Data.data";
	          String outputSerDataFile = outputXlsFile.substring(0,index)+".data";
		        try {
		            
		        	Map<String, Map<String,TxMappingPojo>> txMap  = new  HashMap<String, Map<String,TxMappingPojo>>();
		        	
		        	Iterator<String> itBackends = backendTxMap.keySet().iterator();
		        	
		        	while(itBackends.hasNext()){
		        		String backendName = itBackends.next();//each backend name
		        		 ArrayList<TxMappingPojo> backendList = backendTxMap.get(backendName);//each backend list
		        		 //find the Map for this backend
		        		 Map<String,TxMappingPojo> backendMap = txMap.get(backendName);
		        		 if(null == backendMap){
		        			 backendMap = new HashMap<String, TxMappingPojo>();
		        			 txMap.put(backendName, backendMap);
		        		 }
		        		 
		        		 for(TxMappingPojo pojo:backendList){
		        			 backendMap.put(pojo.getMergeLisaTxId(), pojo);
		        		 }
		        		 
		        		 
		        	}
		        			            //Construct the LineNumberReader object
		            outputStream = new ObjectOutputStream(new FileOutputStream(outputSerDataFile));
		           
		            
		            outputStream.writeObject(txMap);
		    		System.out.println("%%%%%%%%% backendTxMap write DONE"+txMap);
	            
		    		 	            
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
			
	
	private void createTxMappingXlsFile(String outputFile){

		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(outputFile);
			HSSFWorkbook hwb=new HSSFWorkbook();
			HSSFSheet sheet =  hwb.createSheet("TX_MAPPING");

			HSSFRow rowhead=   sheet.createRow((short)0);
			rowhead.createCell((short) 0).setCellValue("Script Name");
			rowhead.createCell((short) 1).setCellValue("Merged Tx ID");
			rowhead.createCell((short) 2).setCellValue("Source TX ID");
			rowhead.createCell((short) 3).setCellValue("Source VSI Date");
			rowhead.createCell((short) 4).setCellValue("Backend Name");
			rowhead.createCell((short) 5).setCellValue("Operation Name");
			rowhead.createCell((short) 6).setCellValue("No Of Arguments");
			//rowhead.createCell((short) 7).setCellValue("All Req Params set to ANY");
			

			Set<String> keyset = backendTxMap.keySet();
			int rowNum = 1;
			int totalTx = 0;
			for (String key : keyset) { 
				ArrayList<TxMappingPojo> txMappingPojoList = backendTxMap.get(key);
				totalTx = txMappingPojoList.size() + totalTx;
				//System.out.println("XLS Backend Name:"+key + " Transaction Count:"+txMappingPojoList.size());
				for (TxMappingPojo txMappingPojo : txMappingPojoList) {
					HSSFRow row=   sheet.createRow(rowNum++);
					row.createCell((short) 0).setCellValue(txMappingPojo.getScriptName());
					row.createCell((short) 1).setCellValue(txMappingPojo.getMergeLisaTxId());
					row.createCell((short) 2).setCellValue(txMappingPojo.getSourceLisaTxId());
					row.createCell((short) 3).setCellValue(txMappingPojo.getSourceVsiDate());
					row.createCell((short) 3).setCellValue(txMappingPojo.getSourceVsiDate());
					row.createCell((short) 4).setCellValue(txMappingPojo.getBackendName());
					row.createCell((short) 5).setCellValue(txMappingPojo.getOperationName());
					row.createCell((short) 6).setCellValue(txMappingPojo.getNumberOfArguments());
					//row.createCell((short) 7).setCellValue(""+txMappingPojo.isAllRequestParamtersAnything());
				}
			}
			System.out.println("TotalTxs:"+totalTx + " XLS Rows "+ (rowNum -1));
			hwb.write(fileOut);
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
	
	private long timeTasks(final List<Runnable> runnableList)
            throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(runnableList.size());

        for (final Runnable task : runnableList) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) { }
                }
            };
            t.start();
        }

        long start = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long end = System.currentTimeMillis();
        return end-start;
    }
    
	
	/*class TxMappingPojo{
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


		public boolean isAllRequestParamtersAnything() {
			return allRequestParamtersAnything;
		}
		public void setAllRequestParamtersAnything(boolean allRequestParamtersAnything) {
			this.allRequestParamtersAnything = allRequestParamtersAnything;
		}


		public String scriptName;
		public String sourceVsiDate;
		public String sourceLisaTxId;
		public String mergeLisaTxId;
		public String backendName;
		public String operationName;
		public int numberOfArguments;
		public boolean allRequestParamtersAnything;
	}*/
	
	
	class TxMappingTask implements Runnable {
		private File vsiFile;
		private String backendName;
		private ArrayList<TxMappingPojo> txMappinglist = new ArrayList<TxMappingPojo>();

		public TxMappingTask(File vsiFile) {
			this.vsiFile = vsiFile;
		}

		@Override
		public void run() {
			
			try {
				sph.acquire();
				long i = System.currentTimeMillis();
				System.out.println("Execution of " + vsiFile + " started");

				ServiceImage si = ServiceImageContext.readServiceImage(vsiFile.getAbsolutePath());
				String[] arr = si.getName().split("_");
				String backendName = arr[0];
				List<TransactionNode> stTxs = si.getStatelessTransactions();
				Map<String, HashSet<String>> matchedBackendScriptMap  = backendMatchedBackendScriptMap.get(backendName);
				for (TransactionNode tn : stTxs) {
					Request tnReq = tn.getRequest();
										
						List<Transaction> spTxs = tn.getSpecifics();
						for (Transaction tx : spTxs) {
							Request req = tx.getRequest();
							ParameterList pList = req.getMetaData();
							if (pList != null) {
								TxMappingPojo txMapping = new TxMappingPojo();
								txMapping.setScriptName(getMatchedScriptMapScriptName(backendName,matchedBackendScriptMap,tx,pList.getParameterValue("test.script.name")));//pList.getParameterValue("test.script.name"));
								txMapping.setSourceVsiDate(pList.getParameterValue("source.vsi.date"));
								txMapping.setSourceLisaTxId(pList.getParameterValue("source.lisa.transaction.id"));
								txMapping.setMergeLisaTxId(String.valueOf(req.getTransaction().getId()));
								txMapping.setBackendName(backendName);
								txMapping.setOperationName( req.getOperation());
								txMapping.setNumberOfArguments(req.getArguments().size());
								findBadRequestMatching(req.getArguments(),txMapping);
								txMappinglist.add(txMapping);
							}
						}// end tx
					}

				updateTxMap(backendName, txMappinglist);
				long j = System.currentTimeMillis();
				i = (j - i) / 1000;
				j = i % 60;
				i = i / 60;
				System.out.println("Execution of " + vsiFile + " completed");
				System.out.println("Total time taken for " + vsiFile + ":" + i
						+ " minunte " + j + " second");

			} catch (Exception ex) {
				System.out.println("ERROR occured during execution of replacing "
						+ "EQUALS with ANY for " + vsiFile + "\n Reason: "
						+ ex.getMessage());
				ex.printStackTrace();
			} finally {
				sph.release();
			}
		}
	}

	private  String getMatchedScriptMapScriptName(String backendName,Map<String, HashSet<String>> matchedBackendScriptMap , Transaction stn, String originalScriptName){
		//backend_operation_argslength_argsvalue
		String key = "";
		try {
			key = stn.getRequest().getOperation()+":"+stn.getRequest().getArguments().size()+":"+stn.getRequest().getArguments().toArgumentString();
			//key = key+stn.getRequest().getArguments().toArgumentString();
		} catch (Throwable e) {
			System.out.println("???????????? getMatchedScriptMapScriptName matchedBackendScriptMap Exception for key :"+key+": returning original scriptname:"+originalScriptName);
			return originalScriptName;
		}
		
		
	
		if(null == matchedBackendScriptMap){
			System.out.println("???????????? getMatchedScriptMapScriptName matchedBackendScriptMap NULL key:"+key + ": returning original scriptname:"+originalScriptName);
			return originalScriptName;
		}
		
		HashSet<String> scriptsNames  = matchedBackendScriptMap.get(key);
		//System.out.println("???????????? getMatchedScriptMapScriptName  Key :"+key);
	
		if(null == scriptsNames){
			//System.out.println("???????????? getMatchedScriptMapScriptName scriptsNames NULL key:"+key + ": returning original scriptname:"+originalScriptName);
			return originalScriptName;
		}
		HashSet<String> finalScriptsNames  = new HashSet<String>();
		addScripts(finalScriptsNames,originalScriptName);
		
		Iterator<String> scriptsIt = scriptsNames.iterator();
		while(scriptsIt.hasNext()){
			addScripts(finalScriptsNames,scriptsIt.next());
		}		
		HashSet<String> backendCalls = (HashSet<String>) allScriptMap.get(backendName);
		if(null == backendCalls){
			backendCalls = new HashSet<String>();
			backendCalls.addAll(finalScriptsNames);
		}else{
			backendCalls.addAll(finalScriptsNames);			
		}
		StringBuffer scripts = new StringBuffer("");
		int scriptSize = finalScriptsNames.size();
		
		Iterator<String> it = finalScriptsNames.iterator();
		int index =0;
		while(it.hasNext()){
			
			scripts.append(it.next());
			
			if( index != (scriptSize- 1)){
				scripts.append(",");
			}
			index++;
		}		

		return scripts.toString();
		
	}
	
	private void addScripts(HashSet<String> finalScriptsNames,String multileScriptNames){
		String[] names = multileScriptNames.toString().split(",");
		for(int i=0;i<names.length;i++){
			finalScriptsNames.add(names[i]);
		}
		
		
	}
	
	private void findBadRequestMatching(ParameterList pList,TxMappingPojo txMapping ){
		Enumeration<Parameter> pEnum = pList.terms();
		boolean allRequestParamtersAnything = true;
		while (pEnum.hasMoreElements()) {
			Parameter ele = pEnum.nextElement();
			ArgumentType type = (ArgumentType) ele.getType();

			Operator operator = type.getOperator();

			// check for Operator Type
			if (operator.equals(Operator.EQUALS)) {
				allRequestParamtersAnything = false;
				break;
			}
		}
		
		String anything = "";
		if(allRequestParamtersAnything){
			anything = "true";
		}else{
			anything = "false";
		}
		if(allRequestParamtersAnything && pList.size() != 0){
			txMapping.setAllRequestParamtersAnything(anything);
		}
}


	
	

	
}
