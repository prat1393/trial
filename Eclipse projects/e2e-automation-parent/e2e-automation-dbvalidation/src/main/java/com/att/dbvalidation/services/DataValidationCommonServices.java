package com.att.dbvalidation.services;

import static com.att.dbvalidation.properties.DataValidationEnum.BATCH_UVERSE_QUERY_BATCH_CHECK_INDEXING_FOR_SA_UVERSE_WLL;
import static com.att.dbvalidation.properties.DataValidationEnum.BATCH_VOIP_USAGE_1;
import static com.att.dbvalidation.properties.DataValidationEnum.CCR_CHECK_ACCOUNT_LINKED_TO_PLENTI_ID;
import static com.att.dbvalidation.properties.DataValidationEnum.CCR_CHECK_SLID_MEMBER_ID;
import static com.att.dbvalidation.properties.DataValidationEnum.ENABLER_CHECK_TRB_ERRROS_1;
import static com.att.dbvalidation.properties.DataValidationEnum.ENABLER_CHECK_TRB_ERRROS_2;
import static com.att.dbvalidation.properties.DataValidationEnum.ENABLER_DTV_CHARGES;
import static com.att.dbvalidation.properties.DataValidationEnum.MPS_TO_CCR_REP_UVERSE_QUERY_MPS_TO_CCR_CHECK_PARENT_MEMBERID;
import static com.att.dbvalidation.properties.DataValidationEnum.MPS_UVERSE_QUERY_MPS_CHECK_GHOST_ID_GENERATED;
import static com.att.dbvalidation.properties.DataValidationEnum.OMS_TO_CCR_REP_CCR;
import static com.att.dbvalidation.properties.DataValidationEnum.RELEASE_CURRENT;
import static com.att.dbvalidation.properties.DataValidationEnum.RELEASE_FUTURE;
import static com.att.dbvalidation.properties.DataValidationEnum.SLID_MPS_MEMBERNAME;
import static com.att.dbvalidation.properties.DataValidationEnum.WLS_FIND_CTN;
import static com.att.dbvalidation.properties.DataValidationEnum.WLS_FIND_MARKET_CODE;
import static com.att.dbvalidation.properties.DataValidationEnum.getBEListFromPropertis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.att.dbvalidation.dao.ValidationDAOUtility;
import com.att.dbvalidation.properties.DBConnectionProperties;

@Service
public class DataValidationCommonServices {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private QueryProcessing process;	
	private DBConnectionProperties dbconnectionproperties;
	private String flag=null;
	
	@Autowired
	public DataValidationCommonServices(QueryProcessing process, DBConnectionProperties dbconnectionproperties) {
		super();
		this.process = process;
		this.dbconnectionproperties = dbconnectionproperties;
	}
	
	public ArrayList<String> getRelease() {
		ArrayList<String> release = new ArrayList<>();
		release.add(RELEASE_CURRENT.getValue());
		release.add(RELEASE_FUTURE.getValue());
		return release;
	}
	
	private String uploadExcelFile(MultipartFile file) throws IOException, FileNotFoundException {
		InputStream in = file.getInputStream();
	 	String fileLocation;
	 	File currDir=null;
	 	if(System.getProperty("os.name").toLowerCase().contains("win")){
	 		currDir =new File("C:\\E2EAutomation\\e2eAutomation\\");
		} else {
			currDir =new File("/opt/app/stubdomains/component/LMR/");
		}
	    String path = currDir.getAbsolutePath();
	    fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
	    FileOutputStream f = new FileOutputStream(fileLocation);
	    int ch = 0;
	    while ((ch = in.read()) != -1) {
	        f.write(ch);
	    }
	    f.flush();
	    f.close();
		return fileLocation;
	}
	
	@SuppressWarnings("resource")
	public List<String> readUverseWirelessFile(MultipartFile file) throws IOException{	
		
		logger.info("Reading Uverse excel file ");		
		List<String> list=new ArrayList<>();
		String ban=null;		
		String fileLocation = uploadExcelFile(file);	    
	    FileInputStream file1 = new FileInputStream(fileLocation);
		XSSFWorkbook workbook = new XSSFWorkbook(file1);
		XSSFSheet sheet1 = workbook.getSheetAt(0);
		try {
			for(int k = 1; k <= sheet1.getLastRowNum() ; k++){	
				Row r = sheet1.getRow(k);
				if(!r.getCell(0).getCellTypeEnum().equals(CellType.STRING)){
					Double d=r.getCell(0).getNumericCellValue();
					ban=String.valueOf(d.longValue());
				}
				else{
					ban=r.getCell(0).getStringCellValue();
				}
				if (!ban.equals("0")) {
					list.add(ban);
				}
				else{
					break;
				}
			}
			file1.close();
		}catch (IOException e) {
			e.printStackTrace();
		
		}catch (Exception e) {
				e.printStackTrace();
		}	  
		return list;
		
	}	
	
	@SuppressWarnings("resource")
	public List<String> readUnifiedFile(MultipartFile file) throws IOException{	
		ArrayList<String> BAN_ID=new ArrayList<String>();
		String ban=null;
		String dtvBan=null;
		String wlsBan=null;
		String fileLocation = uploadExcelFile(file);
		
	    FileInputStream file1 = new FileInputStream(fileLocation);
		XSSFWorkbook workbook = new XSSFWorkbook(file1);
		XSSFSheet sheet1 = workbook.getSheetAt(0);
		
		try {
			for(int k = 1; k <= sheet1.getLastRowNum() ; k++){	
					Row r = sheet1.getRow(k);
					if(!(r.getCell(0).getCellTypeEnum().equals(CellType.STRING))){
						Double d=r.getCell(0).getNumericCellValue();
						ban=String.valueOf(d.longValue());
					}
					else{
						ban=r.getCell(0).getStringCellValue();
					}
					if(!r.getCell(1).getCellTypeEnum().equals(org.apache.poi.ss.usermodel.CellType.STRING)){
						Double d=r.getCell(1).getNumericCellValue();
						wlsBan=String.valueOf(d.longValue());
					}
					else{
						wlsBan=r.getCell(1).getStringCellValue();
					}
					if(!r.getCell(2).getCellTypeEnum().equals(org.apache.poi.ss.usermodel.CellType.STRING)){
						Double d=r.getCell(2).getNumericCellValue();
						dtvBan=String.valueOf(d.longValue());
					}
					else{
						dtvBan=r.getCell(2).getStringCellValue();
					}
					if (!ban.equals("0")) {
						if (!wlsBan.equals("0") || !dtvBan.equals("0")) {
							if (wlsBan.equals("0")) {
								wlsBan="NA";
							}
							if (dtvBan.equals("0")) {
								dtvBan="NA";
							}
							ban=ban+"-"+wlsBan+"-"+dtvBan;
						}
						BAN_ID.add(ban);
					}
					else{
						break;
					}
				}
			file1.close();
		}catch (IOException e11) {
			e11.printStackTrace();
		}
		return BAN_ID;
	}	
	
	public ArrayList<String> retrieveBANfromCTN( List<String> list, String release) throws IOException{
		String[] CCR_Details = dbconnectionproperties.CCR_DB(release);
		ValidationDAOUtility.CCRConnection(CCR_Details);		
		ArrayList<String> resultList= process.retrieveBANfromCTN(ValidationDAOUtility.con_CCR, list, WLS_FIND_CTN.getValue(), WLS_FIND_MARKET_CODE.getValue());
		return resultList;
	}
	
	public Set<String> uversewirelessselectedQueries(String selectedQueriesList) {		
		String spiltQueries[]=selectedQueriesList.split(",");
		Set<String> selectedQuery = new CopyOnWriteArraySet<String>(Arrays.asList(spiltQueries));
			for (String selected : selectedQuery) {
				if (selected.trim().contains("_ALL")) {
					String backends=selected.split("_ALL")[0];
					selectedQuery.addAll(getBEListFromPropertis(backends+"_QUERY_",1));
					selectedQuery.remove(selected);
				 }
			}
		return selectedQuery;
	}	

	public Set<String> unifiedselectedQueries(String selectedQueriesList) {		
		String spiltQueries[]=selectedQueriesList.split(",");
		Set<String> selectedQuery = new CopyOnWriteArraySet<String>(Arrays.asList(spiltQueries));
			for (String selected : selectedQuery) {
				if (selected.trim().contains("_ALL")) {
					String backends=selected.split("_")[0];
					selectedQuery.addAll(getBEListFromPropertis(backends+"_UVERSE_QUERY_",1));
					selectedQuery.addAll(getBEListFromPropertis(backends+"_UNIFIED_QUERY_",1));
					selectedQuery.remove(selected);
				 }
			}
		return selectedQuery;
	}
	
	public void omscheckbanstatus(String key, List<String> resultList, String query) {
		ArrayList<String> list=new ArrayList<String>();
		try {
			if (ValidationDAOUtility.con_OMS1!=null) {
				list=process.chckbxCheckBanStatusinOMS(ValidationDAOUtility.con_OMS1,key,query);
			}
			if (!list.isEmpty()) {
				flag="Pass";
				for (String l : list) {
					resultList.add("<TD>"+key+"</TD><TD>OMS</TD><TD>BAN status: Seg1 (Action/STATUS/DATE)</TD><TD>"+l+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
			}	
			else {
				if (ValidationDAOUtility.con_OMS2!=null) {
					list=process.chckbxCheckBanStatusinOMS(ValidationDAOUtility.con_OMS2,key,query);
				}
				if (!list.isEmpty()) {
					flag="Pass";
					for (String l : list) {
						resultList.add("<TD>"+key+"</TD><TD>OMS</TD><TD>BAN status: Seg2 (Action/STATUS/DATE)</TD><TD>"+l+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");	
					}
				}		
				else {
					if (ValidationDAOUtility.con_OMS3!=null) {
						list=process.chckbxCheckBanStatusinOMS(ValidationDAOUtility.con_OMS3,key,query);
						if (!list.isEmpty()) {
							flag="Pass";
								for (String l : list) {
									resultList.add("<TD>"+key+"</TD><TD>OMS</TD><TD>BAN status: Seg3 (Action/STATUS/DATE)</TD><TD>"+l+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");	
								}
						}
						else	{
							flag="Fail";
							resultList.add("<TD>"+key+"</TD><TD>OMS</TD><TD>BAN status: Seg1, Seg, Seg3</TD><TD>Customer ID not flown to OMS</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}	
					}
				}
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check Ban Status in OMS: "+ e); 
		}
	}

	public void omscheckvalidationTBban(String key, List<String> resultList, String query) {
		int count=0;
		try {
			if (ValidationDAOUtility.con_OMS1!=null) {
				count=process.TBBanValidation_Address_OMS(ValidationDAOUtility.con_OMS1,key,query);
			}
			if (count!=0) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>OMS</TD><TD>TBBAN Validation: Seg1</TD><TD>Row Retrieved- BAN present in TBBAN Table</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else
			{
				if (ValidationDAOUtility.con_OMS2!=null) {
					count=process.TBBanValidation_Address_OMS(ValidationDAOUtility.con_OMS2,key,query);
				}
				if (count!=0) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>OMS</TD><TD>TBBAN Validation: Seg2</TD><TD>Row Retrieved- BAN present in TBBAN Table</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else
				{
					if (ValidationDAOUtility.con_OMS3!=null) {
						count=process.TBBanValidation_Address_OMS(ValidationDAOUtility.con_OMS3,key,query);
						if (count!=0) {
							flag="Pass";
							resultList.add("<TD>"+key+"</TD><TD>OMS</TD><TD>TBBAN Validation: Seg3</TD><TD>Row Retrieved- BAN present in TBBAN Table</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");	
						}
						else	{
							flag="Fail";
							resultList.add("<TD>"+key+"</TD><TD>OMS</TD><TD>TBBAN Validation: Seg1, Seg, Seg3</TD><TD>No Row Retrieved- BAN id not present in TBBAN Table</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
					}
				}
			}
		} 
		catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check Validation In TBBAN: "+ e); 
		}
	}

	public void omscheckaddress(String key, List<String> resultList, String query) {
		int count=0;
		try {
			if (ValidationDAOUtility.con_OMS1!=null) {
				count=process.TBBanValidation_Address_OMS(ValidationDAOUtility.con_OMS1,key,query);
			}
			if(count!=0){
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>OMS</TD><TD>Check Address: Seg1</TD><TD>Row Retrieved- Address is present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else{
				if (ValidationDAOUtility.con_OMS2!=null) {
					count=process.TBBanValidation_Address_OMS(ValidationDAOUtility.con_OMS2,key,query);
				}
				if(count!=0){
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>OMS</TD><TD>Check Address: Seg2</TD><TD>Row Retrieved- Address is present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else{
					if (ValidationDAOUtility.con_OMS3!=null) {
						count=process.TBBanValidation_Address_OMS(ValidationDAOUtility.con_OMS3,key,query);
						if(count!=0){
							flag="Pass";
							resultList.add("<TD>"+key+"</TD><TD>OMS</TD><TD>Check Address: Seg3</TD><TD>Row Retrieved- Address is present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
						else {
							flag="Fail";
							resultList.add("<TD>"+key+"</TD><TD>OMS</TD><TD>Check Address: Seg1, Seg2, Seg3</TD><TD>No Row Retrieved- Address is not present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
					}
				}
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check Address: "+ e); 
		}	
	}

	public void omsccrreplication(String key, List<String> resultList, String query) {
		try {			
			int countOMS=0;
			String serviceType=null;
			ArrayList<String> serviceTypeOMSList=null;
			if (ValidationDAOUtility.con_OMS1!=null) {
				serviceTypeOMSList=new ArrayList<String>();
				ValidationDAOUtility.ps=ValidationDAOUtility.con_OMS1.prepareStatement(query);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					++countOMS;
					serviceType=ValidationDAOUtility.res.getString("AP_ID");
					serviceTypeOMSList.add(serviceType);
				}
			}
			if(countOMS==0) {
				if (ValidationDAOUtility.con_OMS2!=null) {
					ValidationDAOUtility.ps=ValidationDAOUtility.con_OMS2.prepareStatement(query);
					ValidationDAOUtility.ps.setString(1, key);
					ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
					while(ValidationDAOUtility.res.next()){
						++countOMS;
						serviceType=ValidationDAOUtility.res.getString("AP_ID");
						serviceTypeOMSList.add(serviceType);
					}
				}
				if(countOMS==0) {
					if (ValidationDAOUtility.con_OMS3!=null) {
						ValidationDAOUtility.ps=ValidationDAOUtility.con_OMS3.prepareStatement(query);
						ValidationDAOUtility.ps.setString(1, key);
						ValidationDAOUtility.res= ValidationDAOUtility.ps.executeQuery();
						while(ValidationDAOUtility.res.next()){
							++countOMS;
							serviceType=ValidationDAOUtility.res.getString("AP_ID");		
							serviceTypeOMSList.add(serviceType);
						}
					}
				}
			} 
			//OMS DB end
			//CCR DB start
			int countCCR=0;
			String serviceTypeCCR=null;
			ArrayList<String> serviceTypeCCRList=new ArrayList<String>();
			String queryCCR=OMS_TO_CCR_REP_CCR.getValue();
			if (ValidationDAOUtility.con_CCR!=null) {
				ValidationDAOUtility.ps=ValidationDAOUtility.con_CCR.prepareStatement(queryCCR);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res =ValidationDAOUtility. ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					++countCCR;
					serviceTypeCCR=ValidationDAOUtility.res.getString("AP_ID");		
					serviceTypeCCRList.add(serviceTypeCCR);
				}
			}
			if (serviceTypeOMSList.size()>0) {
				if (serviceTypeCCRList.containsAll(serviceTypeOMSList)) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>OMS To CCR Replication</TD><TD>Check AP_IDs</TD><TD>Replicated: OMS AP_IDs are present in CCR DB</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					resultList.add("<TD>"+key+"</TD><TD>OMS To CCR Replication</TD><TD>Check AP_IDs</TD><TD>No. of rows: OMS("+countOMS+") & CCR("+countCCR+")</TD><TD>"+flag+"</TD><TD>"+queryCCR+"</TD>");
				}
				else{
					flag="Fail";
					resultList.add("<TD>"+key+"</TD><TD>OMS To CCR Replication</TD><TD>Check AP_IDs</TD><TD>Not Replicated</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					resultList.add("<TD>"+key+"</TD><TD>OMS To CCR Replication</TD><TD>Check AP_IDs</TD><TD>No. of rows: OMS("+countOMS+") & CCR("+countCCR+")</TD><TD>"+flag+"</TD><TD>"+queryCCR+"</TD>");
				}
			}
			else{
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>OMS To CCR Replication</TD><TD>Check AP_IDs</TD><TD>Not Replicated</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				resultList.add("<TD>"+key+"</TD><TD>OMS To CCR Replication</TD><TD>Check AP_IDs</TD><TD>No. of rows: OMS("+countOMS+") & CCR("+countCCR+")</TD><TD>"+flag+"</TD><TD>"+queryCCR+"</TD>");
			}
		}catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: OMS To CCR Replication: "+ e);
		}
	}
	
	public void enablercheckstatusproduct(String key, List<String> resultList, String query) {
		try {
			ArrayList<String> enablerStatusProduct_A=new ArrayList<String>();
			ArrayList<String> enablerStatusProduct_C=new ArrayList<String>();
			ArrayList<String> enablerStatusProduct_S=new ArrayList<String>();
			String status=null;
			String subscriberType=null;
			if (ValidationDAOUtility.con_EZ1!=null) {
				ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ1.prepareStatement(query);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					subscriberType=ValidationDAOUtility.res.getString("SUBSCRIBER_TYPE");
					status=ValidationDAOUtility.res.getString("SUB_STATUS");	
					if (!(subscriberType.contains("RG") || subscriberType.contains("SB"))){
						switch (status){
						case "A" : 
							enablerStatusProduct_A.add(subscriberType);
							break;
						case "C" : 
							enablerStatusProduct_C.add(subscriberType);
							break;
						case "S" :
							enablerStatusProduct_S.add(subscriberType);
							break;
						}
					}	
				}
			}
			if (!enablerStatusProduct_A.isEmpty() || !enablerStatusProduct_C.isEmpty() || !enablerStatusProduct_S.isEmpty()) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Product Status: Zone1</TD><TD>A-"+enablerStatusProduct_A+" & C-"+enablerStatusProduct_C+" & S-"+enablerStatusProduct_S+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else {
				if (ValidationDAOUtility.con_EZ2!=null) {
					ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ2.prepareStatement(query);
					ValidationDAOUtility.ps.setString(1, key);
					ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
					while(ValidationDAOUtility.res.next()){
						subscriberType=ValidationDAOUtility.res.getString("SUBSCRIBER_TYPE");
						status=ValidationDAOUtility.res.getString("SUB_STATUS");	
						if (!(subscriberType.contains("RG") || subscriberType.contains("SB"))) {
							switch (status){
							case "A" : 
								enablerStatusProduct_A.add(subscriberType);
								break;
							case "C" : 
								enablerStatusProduct_C.add(subscriberType);
								break;
							case "S" :	
								enablerStatusProduct_S.add(subscriberType);
								break;
							}
						}	
					}
				}
				if (!enablerStatusProduct_A.isEmpty() || !enablerStatusProduct_C.isEmpty() || !enablerStatusProduct_S.isEmpty()) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Product Status: Zone2</TD><TD>A-"+enablerStatusProduct_A+" & C-"+enablerStatusProduct_C+" & S-"+enablerStatusProduct_S+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					}
				else {
					if (ValidationDAOUtility.con_EZ3!=null) {
						ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ3.prepareStatement(query);
						ValidationDAOUtility.ps.setString(1, key);
						ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
						while(ValidationDAOUtility.res.next()){
							subscriberType=ValidationDAOUtility.res.getString("SUBSCRIBER_TYPE");
							status=ValidationDAOUtility.res.getString("SUB_STATUS");	
							if (!(subscriberType.contains("RG") || subscriberType.contains("SB"))) {
								switch (status){
								case "A" : 
									enablerStatusProduct_A.add(subscriberType);
									break;
								case "C" : 
									enablerStatusProduct_C.add(subscriberType);
									break;
								case "S" :	
									enablerStatusProduct_S.add(subscriberType);
			        				break;
								}
							}	
						}
						if (!enablerStatusProduct_A.isEmpty() || !enablerStatusProduct_C.isEmpty() || !enablerStatusProduct_S.isEmpty()) {
							flag="Pass";
							resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Product Status: Zone3</TD><TD>A-"+enablerStatusProduct_A+" & C-"+enablerStatusProduct_C+" & S-"+enablerStatusProduct_S+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
						else {	
							flag="Fail";
							resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Product Status: Zone1, Zone2, Zone3</TD><TD>Customer Id Not Flown to Enabler</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
					}
				}
			}
		}
		catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Enabler DB- Product Status: "+ e);
		}
	}
	
	public void enablercheckcycle(String key, List<String> resultList, String query) {
		String bill_cycle=null;
		try {
			if (ValidationDAOUtility.con_EZ1!=null) {
				bill_cycle= process.checkCycleBanEnabler(ValidationDAOUtility.con_EZ1,key,query);
			}
			if (bill_cycle!=null && !bill_cycle.isEmpty()) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Bill Cycle: Zone1</TD><TD>"+bill_cycle+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else {
				if (ValidationDAOUtility.con_EZ2!=null) {
					bill_cycle= process.checkCycleBanEnabler(ValidationDAOUtility.con_EZ2,key,query);
				}
				if (bill_cycle!=null && !bill_cycle.isEmpty()) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Bill Cycle: Zone2</TD><TD>"+bill_cycle+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else {
					if (ValidationDAOUtility.con_EZ3!=null) {
						bill_cycle= process.checkCycleBanEnabler(ValidationDAOUtility.con_EZ3,key,query);
						if (bill_cycle!=null && !bill_cycle.isEmpty()) {
							flag="Pass";
							resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Bill Cycle: Zone3</TD><TD>"+bill_cycle+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
						else {
							flag="Fail";
							resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Bill Cycle: Zone1, Zone2, Zone3</TD><TD>Bill Cycle Not present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
					}
				}
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check Cycle Ban in Enabler DB: "+ e); 
		}
	}
	
	public void enablercheckaccounttype(String key, List<String> resultList, String query) {
		String acc_type=null;
		try {
			if (ValidationDAOUtility.con_EZ1!=null) {
				acc_type= process.checkAccountTypeEnabler(ValidationDAOUtility.con_EZ1,key,query);
			}
			if (acc_type!=null && !acc_type.isEmpty()) {
				if(acc_type.charAt(0)=='R')
					{
					acc_type="Residential";
						flag="Pass";
					}
				else if (acc_type.charAt(0)=='9') {
					acc_type="Business";
						flag="Pass";
					}
				else if (acc_type.charAt(0)=='S') {
					acc_type="Employee";
					flag="Pass";
				}
				resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>BAN Account Type: Zone1</TD><TD>"+acc_type+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else {
				if (ValidationDAOUtility.con_EZ2!=null) {
					acc_type=process.checkAccountTypeEnabler(ValidationDAOUtility.con_EZ2,key,query);
				}
				if (acc_type!=null && !acc_type.isEmpty()) {
					if(acc_type.charAt(0)=='R')
						{
						acc_type="Residential";
							flag="Pass";
						}
					else if (acc_type.charAt(0)=='9') {
						acc_type="Business";
							flag="Pass";
						}
					else if (acc_type.charAt(0)=='S') {
						acc_type="Employee";
						flag="Pass";
					}
					resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>BAN Account Type: Zone2</TD><TD>"+acc_type+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else {
					if (ValidationDAOUtility.con_EZ3!=null) {
						acc_type= process.checkAccountTypeEnabler(ValidationDAOUtility.con_EZ3,key,query);
						if (acc_type!=null && !acc_type.isEmpty()) {
							if(acc_type.charAt(0)=='R') {
								acc_type="Residential";
								flag="Pass";
							}
							else if (acc_type.charAt(0)=='9') {
								acc_type="Business";
								flag="Pass";
							}
							else if (acc_type.charAt(0)=='S') {
								acc_type="Employee";
								flag="Pass";
							}
							resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>BAN Account Type: Zone3</TD><TD>"+acc_type+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
						else {
							flag="Fail";
							resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>BAN Account Type: Zone1, Zone2, Zone3</TD><TD>BAN Account Type not present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
					}
				} 
			}
		}catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check Cycle Ban in Enabler DB: "+ e); 
		}
	}

	public void enablercheckplanandsoc(String key, List<String> resultList, String query) {
		try {
			String socDescription=null;
			String number=null;
			String socName=null;
			ArrayList<String> enablerSocName=new ArrayList<String>();
			boolean status=false;
			if (ValidationDAOUtility.con_EZ1!=null) {
				ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ1.prepareStatement(query);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					socName=ValidationDAOUtility.res.getString("SOC_NAME");
					socDescription=ValidationDAOUtility.res.getString("SOC_DESCRIPTION");
					if (socDescription!=null) {
						if(socDescription.contains("Internet Usage Allowance 1TB")){
							status=true;
							number=ValidationDAOUtility.res.getString("AGREEMENT_NO");
						}
					}
					if (socName!=null) {
						if (socName.contains("AT&T Phone - ")) {
							status=true;
							socName=socName.substring(13);
							enablerSocName.add(socName);
						}
					}
				}
			}
			if(status){
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Check Plans: Zone1 (PLANs & AGREEMENT NO)</TD><TD>"+enablerSocName+" & "+number+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else {	
				if (ValidationDAOUtility.con_EZ2!=null) {
					ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ2.prepareStatement(query);
					ValidationDAOUtility.ps.setString(1, key);
					ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
					while(ValidationDAOUtility.res.next()){
							socName=ValidationDAOUtility.res.getString("SOC_NAME");
							socDescription=ValidationDAOUtility.res.getString("SOC_DESCRIPTION");
							if (socDescription!=null) {
								if (socDescription.contains("Internet Usage Allowance 1TB"))
								{
									status=true;
									number=ValidationDAOUtility.res.getString("AGREEMENT_NO");
								}
							}
							if (socName!=null) {
								if (socName.contains("AT&T Phone - ")) {
									status=true;
									socName=socName.substring(13);
									enablerSocName.add(socName);
								}
							}
					} 
				}
				if(status){
						flag="Pass";
						resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Check Plans: Zone2 (PLANs & AGREEMENT NO)</TD><TD>"+enablerSocName+" & "+number+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else {	
					if (ValidationDAOUtility.con_EZ3!=null) {
						ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ3.prepareStatement(query);
						ValidationDAOUtility.ps.setString(1, key);
						ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
						while(ValidationDAOUtility.res.next()){
							socName=ValidationDAOUtility.res.getString("SOC_NAME");
							socDescription=ValidationDAOUtility.res.getString("SOC_DESCRIPTION");
								if (socDescription!=null) {
									if(socDescription.contains("Internet Usage Allowance 1TB"))
									{
										status=true;
										number=ValidationDAOUtility.res.getString("AGREEMENT_NO");
									}
								}
								if (socName!=null) {
									 if (socName.contains("AT&T Phone - ")) {
										status=true;
										socName=socName.substring(13);
										enablerSocName.add(socName);
									 }
								}
							}
							if(status){
								flag="Pass";
								resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Check Plans: Zone3 (PLANs & AGREEMENT NO)</TD><TD>"+enablerSocName+" & "+number+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
							}
							else{
								flag="Fail";
								resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Check Plans: : Zone1, Zone2, Zone3</TD><TD>HISA and VOIP plan not found</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
							}
						}
					}
				}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Enabler DB- Check Plans: "+ e);
		}
	}

	public void enablercheckTRBerrors(String key, List<String> resultList, String query) {
		try {
			String query2 = ENABLER_CHECK_TRB_ERRROS_1.getValue();
			String query3 = ENABLER_CHECK_TRB_ERRROS_2.getValue();
			boolean count=false;
			if (ValidationDAOUtility.con_EZ1!=null) {
				ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ1.prepareStatement(query);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					count=true;
				} 
			}
			if(count)
			{
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query1): Zone1</TD><TD>Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				boolean count2=false;
					ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ1.prepareStatement(query2);
					ValidationDAOUtility.ps.setString(1, key);
					ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
					while(ValidationDAOUtility.res.next()){
						count2=true;
					} 				
				if(!count2){
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query2): Zone1</TD><TD>No Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query2+"</TD>");
				}
				else{
					flag="Fail";
					resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query2): Zone1</TD><TD>Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query2+"</TD>");
				}
				boolean count3=false;
				if (ValidationDAOUtility.con_EZ1!=null) {
					ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ1.prepareStatement(query3);
					ValidationDAOUtility.ps.setString(1, key);
					ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
					while(ValidationDAOUtility.res.next()){
						count3=true;
					} 
				}
				if(!count3){
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query3): Zone1</TD><TD>No Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query3+"</TD>");
				} 
				else { 
					flag="Fail";
					resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query3): Zone1</TD><TD>Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query3+"</TD>");
				}
			}
			else
			{
				if (ValidationDAOUtility.con_EZ2!=null) {
					ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ2.prepareStatement(query);
					ValidationDAOUtility.ps.setString(1, key);
					ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
					while(ValidationDAOUtility.res.next()){
						count=true;	
					} 
				}
				if(count)
				{
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query1): Zone2</TD><TD>Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					boolean count2=false;
						ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ2.prepareStatement(query2);
						ValidationDAOUtility.ps.setString(1, key);
						ValidationDAOUtility.res= ValidationDAOUtility.ps.executeQuery();
						while(ValidationDAOUtility.res.next()){
							count2=true;
						} 
					
					if(!count2){
						flag="Pass";
						resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query2): Zone2</TD><TD>No Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query2+"</TD>");
					}
					else{
						flag="Fail";
						resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query2): Zone2</TD><TD>Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query2+"</TD>");
					}
					boolean count3=false;
					if (ValidationDAOUtility.con_EZ2!=null) {
						ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ2.prepareStatement(query3);
						ValidationDAOUtility.ps.setString(1, key);
						ValidationDAOUtility.	res = ValidationDAOUtility.ps.executeQuery();
						while(ValidationDAOUtility.res.next()){
							count3=true;
						} 
					}
					if(!count3){
						flag="Pass";
						resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query3): Zone3</TD><TD>No Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query3+"</TD>");
					}
					else{
						flag="Fail";
						resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query3): Zone2</TD><TD>Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query3+"</TD>");
					}
				}
				else
				{
					if (ValidationDAOUtility.con_EZ3!=null) {
						ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ3.prepareStatement(query);
						ValidationDAOUtility.ps.setString(1, key);
						ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
						while(ValidationDAOUtility.res.next()){
							count=true;	
						} 
						if(count) {
							flag="Pass";
							resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query1): Zone3</TD><TD>Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
							boolean count2=false;
							ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ3.prepareStatement(query2);
							ValidationDAOUtility.ps.setString(1, key);
							ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
							while(ValidationDAOUtility.res.next()){
								count2=true;
							} 
						if(!count2){
							flag="Pass";
							resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query2): Zone3</TD><TD>No Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query2+"</TD>");
						}
						else{
							flag="Fail";
							resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query2): Zone3</TD><TD>Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query2+"</TD>");
						}
						boolean count3=false;
							ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ3.prepareStatement(query3);
							ValidationDAOUtility.ps.setString(1, key);
							ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
							while(ValidationDAOUtility.res.next()){
								count3=true;
							} 
						if(!count3){
							flag="Pass";
							resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query3): Zone3</TD><TD>No Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query3+"</TD>");
						}
						else{
							flag="Fail";
							resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query3): Zone3</TD><TD>Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query3+"</TD>");
						}
					}
					else
					{
						flag="Fail";
						resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>TRB Errors(Query1): Zone1, Zone2, Zone3</TD><TD>No Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					}
					}
				} 
			}
		}catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Enabler DB- TRB Errors: "+ e);
		}
	}

	public void enablercheckusage(String key, List<String> resultList, String query) {
		try {
			String serviceFilter=null;
			String cycleInstance=null;
			Set<String> serviceFilterList=new HashSet<String>();
			if (ValidationDAOUtility.con_EZ1!=null) {
				ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ1.prepareStatement(query);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.ps.setString(2, key);
				ValidationDAOUtility.ps.setString(3, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					cycleInstance=ValidationDAOUtility.res.getString("CYCLE_INSTANCE");
					serviceFilter=ValidationDAOUtility.res.getString("SERVICE_FILTER");
					if(serviceFilter!=null){
						serviceFilterList.add(serviceFilter);
					}
				} 
			}
			if(cycleInstance!=null && !cycleInstance.isEmpty()){
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Check Usage: Zone1(CYCLE_INSTANCE-SERVICE_FILTER)</TD><TD>"+cycleInstance+" - "+serviceFilterList+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else {	
				if (ValidationDAOUtility.con_EZ2!=null) {
					ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ2.prepareStatement(query);
					ValidationDAOUtility.ps.setString(1, key);
					ValidationDAOUtility.ps.setString(2, key);
					ValidationDAOUtility.ps.setString(3, key);
					ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
					while(ValidationDAOUtility.res.next()){
						cycleInstance=ValidationDAOUtility.res.getString("CYCLE_INSTANCE");
						serviceFilter=ValidationDAOUtility.res.getString("SERVICE_FILTER");
						if(serviceFilter!=null)
								{
								serviceFilterList.add(serviceFilter);
								}
						} 
					}	
					if(cycleInstance!=null && !cycleInstance.isEmpty())
						{
							flag="Pass";
							resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Check Usage: Zone2(CYCLE_INSTANCE-SERVICE_FILTER)</TD><TD>"+cycleInstance+" - "+serviceFilterList+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
						else {
							if (ValidationDAOUtility.con_EZ3!=null) {
								ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ3.prepareStatement(query);
								ValidationDAOUtility.ps.setString(1, key);
								ValidationDAOUtility.ps.setString(2, key);
								ValidationDAOUtility.ps.setString(3, key);
								ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
								while(ValidationDAOUtility.res.next()){
									cycleInstance=ValidationDAOUtility.res.getString("CYCLE_INSTANCE");
									serviceFilter=ValidationDAOUtility.res.getString("SERVICE_FILTER");
									if(serviceFilter!=null){
										serviceFilterList.add(serviceFilter);
									}
								} 
							if(cycleInstance!=null){
								flag="Pass";
								resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Check Usage: Zone3(CYCLE_INSTANCE-SERVICE_FILTER)</TD><TD>"+cycleInstance+" - "+serviceFilterList+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
							}
							else if(cycleInstance==null || cycleInstance.isEmpty()){		
								flag="Fail";
								resultList.add("<TD>"+key+"</TD><TD>Enabler</TD><TD>Check Usage: Zone1, Zone2, Zone3 (CYCLE_INSTANCE-SERVICE_FILTER)</TD><TD>No usage present on BAN</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
							}
						}
					}
				} 
			}
			catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Enabler DB- Check Usage: "+ e);
		}
	}
	
	public void mpsCheckServiceId(String key, List<String> resultList, String query) {
		HashSet<Integer> set=new HashSet<Integer>();
		try {
			set=process.chckbxCheckServiceId(ValidationDAOUtility.con_MPS,key, query);
			if (set.isEmpty()) {
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>MPS</TD><TD>Check Service Id</TD><TD>SERVICE ID not present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else{
				Iterator<Integer> itr=set.iterator();
				while (itr.hasNext()) {
					Integer in = (Integer) itr.next();
					flag="Pass";
					switch (in) {
					case 24:
						resultList.add("<TD>"+key+"</TD><TD>MPS</TD><TD>Check Service Id</TD><TD>SERVICE ID: "+in+" /Uverse</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						break;
					case 33:
						resultList.add("<TD>"+key+"</TD><TD>MPS</TD><TD>Check Service Id</TD><TD>SERVICE ID: "+in+" /WLL</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						break;
					case 42:
						resultList.add("<TD>"+key+"</TD><TD>MPS</TD><TD>Check Service Id</TD><TD>SERVICE ID: "+in+" /DTV</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						break;
					case 32:
						resultList.add("<TD>"+key+"</TD><TD>MPS</TD><TD>Check Service Id</TD><TD>SERVICE ID: "+in+" /Wireless</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						break;
					}
				} 
			}
		} catch (Exception e) {
		}
	}

	public void mpscheckGhostId(String key, List<String> resultList, String query) {
		String result=null;
		try {
			result=process.chckbxCheckGhostId(ValidationDAOUtility.con_MPS,key, query);
			if (result!=null && !result.isEmpty()) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>MPS</TD><TD>Check Ghost Id (MEMBER_NAME/ ACTIVATED_IND)</TD><TD>"+result+"</TD<TD>"+flag+"</TD><TD>"+query+"</TD>");
			} else {
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>MPS</TD><TD>Check Ghost Id (MEMBER_NAME/ ACTIVATED_IND)</TD><TD>GhostId not generated</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
		} catch (Exception e) {
		}
	}

	public void mpsccrcheckParentMemberId(String key, List<String> resultList, String query) {
		ArrayList<String> parentMemIdList=new ArrayList<String>();
		try {
			parentMemIdList=process.checkParentMemberId(ValidationDAOUtility.con_CCR,key, query);
			if (parentMemIdList.isEmpty()){
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>MPS to CCR Replication</TD><TD>Check PARENT_MEMBERID</TD><TD>slid/MID not flown to CCR</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else
			{
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>MPS to CCR Replication</TD><TD>Check PARENT_MEMBERID</TD><TD>"+parentMemIdList+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
		}
		catch (Exception e) {
		}
	}
	
	public void mpsccrcheckMainMemberId(String key, List<String> resultList, String query) {
		int domainId=0;
		ArrayList<String> parentMemIdList=new ArrayList<String>();
		String  memId=null;
		try {
			///find domainId and parentMemIdList:
			String query1=MPS_UVERSE_QUERY_MPS_CHECK_GHOST_ID_GENERATED.getValue();
			domainId=process.checkDomainId(ValidationDAOUtility.con_CCR,key, query1);
		
			//query = query.get(16).toString();
			String query2=MPS_TO_CCR_REP_UVERSE_QUERY_MPS_TO_CCR_CHECK_PARENT_MEMBERID.getValue();
			parentMemIdList=process.checkParentMemberId(ValidationDAOUtility.con_CCR,key, query2);
			
			memId=process.checkMainMemberId(ValidationDAOUtility.con_CCR,key, query);
			if (memId!=null && !memId.isEmpty()) {
				flag="Pass";
			} 
			else {
				if (domainId==15 && !parentMemIdList.isEmpty()) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>MPS to CCR Replication</TD><TD>Check MAIN_MEMBERID</TD><TD>Slid/MID flown to CCR</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else{
					flag="Fail";
					resultList.add("<TD>"+key+"</TD><TD>MPS to CCR Replication</TD><TD>Check MAIN_MEMBERID</TD><TD>Slid/MID not flown to CCR</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					}
			}
		} catch (IOException e) {
		}	
	}
	
	public void mpsccrcheckNameAddress(String key, List<String> resultList, String query) {
		String  result=null;
		try {
			result=process.checkNameAddress(ValidationDAOUtility.con_CCR,key, query);
			if (result==null || result.isEmpty()) {
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>MPS to CCR Replication</TD><TD>Check Address: Name/Zipcode/STATE</TD><TD>slid/MID not flown to CCR</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else{
			flag="Pass";
			resultList.add("<TD>"+key+"</TD><TD>MPS to CCR Replication</TD><TD>Check Address: Name/Zipcode/STATE</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
		
		} catch (Exception e) {
		}	
	}
	
	public void ccrCheckPkey(String key, List<String> resultList, String query) {
		String pKEY=null;
		try {
			System.out.println("ccrCheckPkey");
			pKEY=process.chckbxPkeyForWireless(ValidationDAOUtility.con_CCR,key, query);
			if (pKEY==null){
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>CCR</TD><TD>Persistent Key</TD><TD>No Persistent Key found</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			} else{
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>CCR</TD><TD>Persistent Key</TD><TD>"+pKEY+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
		} catch (Exception e) {
			
		}
	}
	
	public void ccrCheckBan(String key, List<String> resultList, String query) {
		int count=0;
		try {
			System.out.println("ccrCheckBan");
			count=process.chckbxCheckBanIn(ValidationDAOUtility.con_CCR,key, query);
			if (count!=0) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>CCR</TD><TD>BAN in ccr.account_wireline_con</TD><TD>PRESENT<TD>"+flag+"</TD><TD>"+query+"</TD>");
			} else {
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>CCR</TD><TD>BAN in ccr.account_wireline_con</TD><TD>NOT PRESENT<TD>"+flag+"</TD><TD>"+query+"</TD>");
		}
		} catch (Exception e) {
		}
	}
	
	public void ccrCheckVideoBill(String key,  List<String> resultList, String query) {
		String videoBill=null;
		try {
			videoBill=process.chckbxCheckVideoBill(ValidationDAOUtility.con_CCR,key, query);
			System.out.println("videoBill: "+videoBill);
			if (videoBill!=null) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>CCR</TD><TD>Check Video Bill Ind</TD><TD>"+videoBill+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			
			} else {
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>CCR</TD><TD>Check Video Bill Ind</TD><TD>NOT PRESENT</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");		
				
			} 
		}catch (Exception e) {
		}
	}
	
	public void ccrFindWlsBan(String key, List<String> resultList, String query) {
		String acc=null;
		try {
			acc=process.chckbxFindWlsBan(ValidationDAOUtility.con_CCR,key, query);
			if (acc!=null) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>CCR</TD><TD>Find Wls BAN for CB Uverse</TD><TD>WLS ban: "+acc+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			} else {
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>CCR</TD><TD>Find Wls BAN for CB Uverse</TD><TD>NOT PRESENT</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
		}
		} catch (IOException e) {
		}
	}
	
	public void ccrCheckWllBanRegis(String key, List<String> resultList, String query) {
		ArrayList<Date> dateList=new ArrayList<Date>();
		try {
			dateList=process.chckbxCheckWllBan(ValidationDAOUtility.con_CCR,key, query);
			if (dateList.isEmpty()) {
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>CCR</TD><TD> WLL BAN Registration</TD><TD>NOT Registered</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			} 
			else {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>CCR</TD><TD>WLL BAN Registration</TD><TD>Registered: "+dateList+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
		} catch (IOException e) {
		}
	}

	public void ccrCheckBRNIndicator(String key,  List<String> resultList, String query) {
		String result=null;
		try {
			result=process.chckbxCheckBrnInd(ValidationDAOUtility.con_CCR,key, query);
			if (result!=null) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>CCR</TD><TD>Check BRN Indicator (Indicator/Email/Type)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			} else {
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>CCR</TD><TD>Check BRN Indicator (Indicator/Email/Type)</TD><TD>NOT PRESENT</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
		}
		} catch (Exception e) { 
		}
	}
	
	public void batchcheckindexingforSAuverse(String key, List<String> resultList, String query) {
		try{
			int idx=0;
			ArrayList<Integer> idxList=new ArrayList<Integer>();
			Set<String> s=new HashSet<String>();
			String billType=null;
			Date billStart=null;
			Date billEnd=null;
			if (ValidationDAOUtility.con_Batch!=null) {
				ValidationDAOUtility.ps=ValidationDAOUtility.con_Batch.prepareStatement(query);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					idx=ValidationDAOUtility.res.getInt("IDX_VOL_NUM");
					billStart=ValidationDAOUtility.res.getDate("BILL_START_DATE");
					billEnd=ValidationDAOUtility.res.getDate("BILL_END_DATE");
					String test=billStart+" to "+billEnd;
					billType=ValidationDAOUtility.res.getString("BILL_TYPE_IND");
					s.add(test);
					idxList.add(idx);
				}
				if (idxList.size()!=0) {
					int a=Collections.max(idxList);
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>Indexing check: SA UVERSE,WLL (MAX_IDX_NUM/ BILL_Type /No.of Bills / Bill Dates)</TD><TD>"+a+" / "+billType+" / "+s.size()+" / " +s+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else {
					flag="Fail";
					resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>Indexing check: SA UVERSE,WLL</TD><TD>BAN not indexed</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
			}
		}
		catch (Exception e) {
			// LoggerWrapper.logger.info("Exception :: Batch DB- Indexing check(SA UVERSE,WLL): "+ e);
		}
	
		
	}

	public void batchcheckcombinedbilling(String key, List<String> resultList, String query) {		
		String query1=BATCH_UVERSE_QUERY_BATCH_CHECK_INDEXING_FOR_SA_UVERSE_WLL.getValue();
		ArrayList<String> serWirelessCombinedList1=new ArrayList<String>();
		String result=null;
		try {
			serWirelessCombinedList1= process.chckbxHsiaUsage(ValidationDAOUtility.con_Batch,key,query1);
			if (serWirelessCombinedList1.size()!=0) {
				result= process.chckbxCheckCombinedBilling(ValidationDAOUtility.con_Batch,key,query);
				if (result!=null) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>Combined Billing CB Uverse (WIRELESS_BAN/BILL_END_DATE/WIRELESS_BILL_END_DATE)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else{
					flag="Fail";
					resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>Combined Billing CB Uverse (WIRELESS_BAN/BILL_END_DATE/WIRELESS_BILL_END_DATE)</TD><TD>BAN not indexed</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
			}
			else
			{
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>Combined Billing CB Uverse</TD><TD>BAN not indexed</TD><TD>"+flag+"</TD><TD>"+query1+"</TD>");
			}
		} catch (IOException e) {
			//LoggerWrapper.logger.info("Exception :: Check Combined Billing for CB Uverse in Batch DB: "+ e); 
		}	
	}
	
	public void batchCheckHSIAusage(String key,  List<String> resultList, String query) {
		ArrayList<String> serWirelessCombinedList1=new ArrayList<String>();
		String result=null;
		String query1=BATCH_UVERSE_QUERY_BATCH_CHECK_INDEXING_FOR_SA_UVERSE_WLL.getValue();
		try {
			serWirelessCombinedList1= process.chckbxHsiaUsage(ValidationDAOUtility.con_Batch,key,query1);
			if (serWirelessCombinedList1.size()!=0) {
				result= process.chckbxHsiaUsage1(ValidationDAOUtility.con_Batch,key,query);
				if (result!=null) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>HSIA usage (SERVICE NAME/INCLUDED_UNITS/USED_UNITS)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else{
					flag="Fail";
					resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>HSIA usage(SERVICE NAME/INCLUDED_UNITS/USED_UNITS)</TD><TD>BAN not indexed</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
			}
			else {
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>HSIA usage(SERVICE NAME/INCLUDED_UNITS/USED_UNITS)</TD><TD>BAN not indexed</TD><TD>"+flag+"</TD><TD>"+query1+"</TD>");
			}
		} catch (Exception e) {
		}
	}

	public void batchCheckWLLdatausage(String key,  List<String> resultList, String query) {
		ArrayList<String> serWLLInternetList=new ArrayList<String>();
		String result=null;
		String query1=BATCH_UVERSE_QUERY_BATCH_CHECK_INDEXING_FOR_SA_UVERSE_WLL.getValue();
		try {
			serWLLInternetList=process.chckbxWllDataUsage(ValidationDAOUtility.con_Batch,key,query1);
			if (serWLLInternetList.size()!=0) {
				result=process.chckbxWllDataUsage1(ValidationDAOUtility.con_Batch,key,query);
				if (result!=null) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>WLL DATA usage (SERVICE NAME / INCLUDED_UNITS/USED_UNITS)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else{ 
					flag="Fail";
					resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>WLL DATA usage</TD><TD>BAN not indexed</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
			}
			else {
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>WLL  DATA usage</TD><TD>BAN not indexed</TD><TD>"+flag+"</TD><TD>"+query1+"</TD>");
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Wll Data Usage in Batch DB: "+ e); 
		}
	
	}

	public void batchCheckVOIPusage(String key,  List<String> resultList, String query) {
		//Two Queries	
		HashMap<Integer, String> voip=new HashMap<Integer, String>();
		HashMap<Integer, String> voip1=new HashMap<Integer, String>();
		try {
			voip=process.chckbxVoipIptvUsage(ValidationDAOUtility.con_Batch,key,query);
			if (!voip.isEmpty() ) {
				int maxkey=Collections.max(voip.keySet());
				String s=(String)voip.get(maxkey);
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>Voip Usage: Query1 (BILL_START_DATE & BILL_END_DATE )For MAX(IDX_VOL_NUM)</TD><TD>"+s+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				
			}
			else {
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>Voip Usage: Query1 (BILL_START_DATE & BILL_END_DATE) For MAX(IDX_VOL_NUM)</TD><TD>Voip Usage not present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			String query1=BATCH_VOIP_USAGE_1.getValue();
			voip1=process.chckbxVoipIptvUsage(ValidationDAOUtility.con_Batch,key,query1);
			if (!voip1.isEmpty() ) {
				int maxkey=Collections.max(voip.keySet());
				String s=(String)voip.get(maxkey);
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>Voip Usage:Query1(BILL_START_DATE & BILL_END_DATE)For MAX(IDX_VOL_NUM)</TD><TD>"+s+"</TD><TD>"+flag+"</TD><TD>"+query1+"</TD>");
			}
			else
			{
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>Voip Usage:Query2(BILL_START_DATE & BILL_END_DATE)For MAX(IDX_VOL_NUM)</TD><TD>Voip Usage not present</TD><TD>"+flag+"</TD><TD>"+query1+"</TD>");
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Voip Usage in Batch DB: "+ e); 
		}	
	}
		
	public void batchCheckIPTVusage(String key,  List<String> resultList, String query) {

		HashMap<Integer, String> iptv=new HashMap<>();
		try {
			iptv=process.chckbxVoipIptvUsage(ValidationDAOUtility.con_Batch,key,query);
			if (!iptv.isEmpty() ) {
				int maxkey=Collections.max(iptv.keySet());
				String s=(String)iptv.get(maxkey);
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>IPTV Usage (BILL_START_DATE & BILL_END_DATE) For MAX(IDX_VOL_NUM)</TD><TD>"+s+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else {
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>Batch</TD><TD>IPTV Usage (BILL_START_DATE & BILL_END_DATE) For MAX(IDX_VOL_NUM)</TD><TD>IPTV Usage not present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Iptv Usage in Batch DB: "+ e); 
		}
	}
	
	public void slidmpsmemberNameassociatesban(String key,  List<String> resultList, String query) {
		ArrayList<String> memberNameList=null;
		String query1=SLID_MPS_MEMBERNAME.getValue();
		try {
			memberNameList=process.slidMemberNameList(ValidationDAOUtility.con_MPS,key,query1);
			if (memberNameList.size()!=0) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>SLID(In MPS)</TD><TD>Member Name</TD><TD>"+memberNameList+"</TD><TD>"+flag+"</TD><TD>"+query1+"</TD>");				
				for (String str : memberNameList) {
					ArrayList<String> list=null;
					if (ValidationDAOUtility.con_MPS!=null) {
						list=process.slidInMPS(ValidationDAOUtility.con_MPS,key,query,str);
					}
					if(list.size()!=0){
						flag="Pass";
						resultList.add("<TD>"+key+"</TD><TD>SLID(In MPS)</TD><TD>Associate BANs</TD><TD>"+str+"-"+list+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					}
					else
					{
						flag="Fail";
						resultList.add("<TD>"+key+"</TD><TD>SLID(In MPS)</TD><TD>Associate BANs</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					}
				}
			}
			else {
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>SLID(In MPS)</TD><TD>Check Member Name & its Associate BANs</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query1+"</TD>");
			}		
		}catch (IOException e) {
			//LoggerWrapper.logger.info("Exception :: SLID QUERIES for MPS : "+ e); 
		}
	}
	
	public void slidcheckslidflowntoccr(String key,  List<String> resultList, String query) {
		ArrayList<String> memberNameList=null;
		String query1=SLID_MPS_MEMBERNAME.getValue();
		try {
			memberNameList=process.slidMemberNameList(ValidationDAOUtility.con_MPS,key,query1);
			if (memberNameList.size()!=0) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>SLID(In MPS)</TD><TD>Member Name</TD><TD>"+memberNameList+"</TD><TD>"+flag+"</TD><TD>"+query1+"</TD>");
				for (String str : memberNameList) {
					Integer count=0;
					String s=str.concat("@slid.dum");
					if (ValidationDAOUtility.con_CCR!=null) {
						count=process.slidInCCR(ValidationDAOUtility.con_CCR,key,query,s);
					}
					if(count==0){
						flag="Fail";
						resultList.add("<TD>"+key+"</TD><TD>SLID(In CCR)</TD><TD>Member Name</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					}
					else {
						flag="Pass";
						resultList.add("<TD>"+key+"</TD><TD>SLID(In CCR)</TD><TD>Member Name</TD><TD>"+str+":- Row Retrieved. Slid flown to CCR</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					}
				}
			
			}
			else {
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>SLID(In MPS)</TD><TD>Member Name</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query1+"</TD>");
			}		
		}catch (IOException e) {
			//LoggerWrapper.logger.info("Exception :: SLID QUERIES for MPS : "+ e); 
		}
	}
	//SLID
	
	
	/***************************************************************************************************************************************************/	
/**********************************************************************Uverse Query End*****************************************************************************/	
	/***************************************************************************************************************************************************/
	
	/***************************************************************************************************************************************************/	
/**********************************************************************Unified Individual Query Start*******************************************************************/
	/***************************************************************************************************************************************************/

	public void enablerunifiedgetconvergedindicator(String key, List<String> resultList, String query) {
		String convergeType=null;
		try {
			if (ValidationDAOUtility.con_EZ1!=null) {
				convergeType= process.checkConvergeTypeEnabler(ValidationDAOUtility.con_EZ1,key,query);
			}
			if (convergeType!=null && !convergeType.isEmpty()) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Converge Type: Zone1</TD><TD>"+convergeType+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else if (convergeType==null){
				if (ValidationDAOUtility.con_EZ2!=null) {
					convergeType= process.checkConvergeTypeEnabler(ValidationDAOUtility.con_EZ2,key,query);
				}
				if (convergeType!=null && !convergeType.isEmpty()) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Converge Type: Zone2</TD><TD>"+convergeType+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else if (convergeType==null){
					if (ValidationDAOUtility.con_EZ3!=null) {
						convergeType= process.checkConvergeTypeEnabler(ValidationDAOUtility.con_EZ3,key,query);
						if (convergeType!=null && !convergeType.isEmpty()) {
							flag="Pass";
							resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Converge Type: Zone3</TD><TD>"+convergeType+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
						else if (convergeType==null){
							flag="Fail";
							resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Converge Type: Zone1, Zone2, Zone3</TD><TD>Customer Id Not Flown to Enabler</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
					}
				} 
			}
		}catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check ConvergedIndicator in Enabler DB: "+ e); 
		}
	}

	public void enablerunifieddtvcharges(String key, String dTVBAN, List<String> resultList, String query) {
		Integer count=0;
		String sql1 = ENABLER_DTV_CHARGES.getValue();
		ArrayList<String> result=new ArrayList<String>();
		try {
			if (ValidationDAOUtility.con_EZ1!=null) {
				count= process.checkDTVChargesQuery1Enabler(ValidationDAOUtility.con_EZ1,dTVBAN,query);
			}
			if (count!=0) {
				flag="Pass";
				resultList.add("<TD>"+key+"-"+dTVBAN+"</TD><TD>ENABLER</TD><TD>DTV Charges(Query1): Zone1</TD><TD>Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else {
				if (ValidationDAOUtility.con_EZ2!=null) {
					count= process.checkDTVChargesQuery1Enabler(ValidationDAOUtility.con_EZ2,dTVBAN,query);
				}
				if (count!=0) {
					flag="Pass";
					resultList.add("<TD>"+key+"-"+dTVBAN+"</TD><TD>ENABLER</TD><TD>DTV Charges(Query1): Zone2</TD><TD>Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else {
					if (ValidationDAOUtility.con_EZ3!=null) {
						count= process.checkDTVChargesQuery1Enabler(ValidationDAOUtility.con_EZ3,dTVBAN,query);
						if (count!=0) {
							flag="Pass";
							resultList.add("<TD>"+key+"-"+dTVBAN+"</TD><TD>ENABLER</TD><TD>DTV Charges(Query1): Zone3</TD><TD>Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
						else {
							flag="Fail";
							resultList.add("<TD>"+key+"-"+dTVBAN+"</TD><TD>ENABLER</TD><TD>DTV Charges(Query1): Zone1, Zone2, Zone3</TD><TD>No Row Retrieved</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
					}
				} 
			}
			
			if (ValidationDAOUtility.con_EZ1!=null) {
					result= process.checkDTVChargesQuery2Enabler(ValidationDAOUtility.con_EZ1,dTVBAN,sql1);
			}
			if (!resultList.isEmpty()) {
					flag="Pass";
					resultList.add("<TD>"+key+"-"+dTVBAN+"</TD><TD>ENABLER</TD><TD>DTV Charges(Query2: ChargeCode/Amount): Zone1</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+sql1+"</TD>");
			}
			else {
				if (ValidationDAOUtility.con_EZ2!=null) {
						result= process.checkDTVChargesQuery2Enabler(ValidationDAOUtility.con_EZ2,dTVBAN,sql1);
				}
				if (!resultList.isEmpty()) {
					flag="Pass";
					resultList.add("<TD>"+key+"-"+dTVBAN+"</TD><TD>ENABLER</TD><TD>DTV Charges(Query2: ChargeCode/Amount): Zone2</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+sql1+"</TD>");
				}
				else {
					if (ValidationDAOUtility.con_EZ3!=null) {
						result= process.checkDTVChargesQuery2Enabler(ValidationDAOUtility.con_EZ3,dTVBAN,sql1);
						if (!resultList.isEmpty()) {
							flag="Pass";
							resultList.add("<TD>"+key+"-"+dTVBAN+"</TD><TD>ENABLER</TD><TD>DTV Charges(Query2: ChargeCode/Amount): Zone3</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+sql1+"</TD>");
						}
						else {
							flag="Fail";
							resultList.add("<TD>"+key+"-"+dTVBAN+"</TD><TD>ENABLER</TD><TD>DTV Charges(Query2: ChargeCode/Amount): Zone1, Zone2, Zone3</TD><TD>Chargecode not present</TD><TD>"+flag+"</TD><TD>"+sql1+"</TD>");
						}
					}
				} 
			}
		}catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check DtvCharges in Enabler DB: "+ e); 
		}
	}

	public void enablerunifiedsynergydtvusage(String key, List<String> resultList, String query) {
		String result=null;
		try {
			if (ValidationDAOUtility.con_EZ1!=null) {
				result= process.checkSynergyDTVUsageEnabler(ValidationDAOUtility.con_EZ1,key,query);
			}
			if (result!=null && !result.isEmpty()) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Synergy DTV Usage(CYCLE_INSTANCE-Total Usage-L9_PPV: Zone1</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else {
				if (ValidationDAOUtility.con_EZ2!=null) {
					result= process.checkSynergyDTVUsageEnabler(ValidationDAOUtility.con_EZ2,key,query);
				}
				if (result!=null && !result.isEmpty()) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Synergy DTV Usage(CYCLE_INSTANCE-Total Usage-L9_PPV: Zone2</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else {
					if (ValidationDAOUtility.con_EZ3!=null) {
						result= process.checkSynergyDTVUsageEnabler(ValidationDAOUtility.con_EZ3,key,query);
						if (result!=null && !result.isEmpty()) {
							flag="Pass";
							resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Synergy DTV Usage(CYCLE_INSTANCE-Total Usage-L9_PPV: Zone3</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
						else {
							flag="Fail";
							resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Synergy DTV Usage(CYCLE_INSTANCE-Total Usage-L9_PPV: Zone1, Zone2, Zone3</TD><TD>Customer Id Not Flown to Enabler</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
					}
				} 
			}
		}catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check Synergy DTV Usage in Enabler DB: "+ e); 
		}
	}
	
	public void enablerunifiedbilledunbilledusage(String key, List<String> resultList, String query) {
		try {
			String serviceFilter=null;
			String cycleInstance=null;
			Set<String> serviceFilterList=new HashSet<String>();
			if (ValidationDAOUtility.con_EZ1!=null) {
				ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ1.prepareStatement(query);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.ps.setString(2, key);
				ValidationDAOUtility.ps.setString(3, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					cycleInstance=ValidationDAOUtility.res.getString("CYCLE_INSTANCE");
					serviceFilter=ValidationDAOUtility.res.getString("SERVICE_FILTER");
					if(serviceFilter!=null){
						serviceFilterList.add(serviceFilter);
					}
				} 
			}
			if(cycleInstance!=null && !cycleInstance.isEmpty()){
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Check Usage: Zone1(CYCLE_INSTANCE-SERVICE_FILTER)</TD><TD>"+cycleInstance+" - "+serviceFilterList+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else {	
				if (ValidationDAOUtility.con_EZ2!=null) {
					ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ2.prepareStatement(query);
					ValidationDAOUtility.ps.setString(1, key);
					ValidationDAOUtility.ps.setString(2, key);
					ValidationDAOUtility.ps.setString(3, key);
					ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
					while(ValidationDAOUtility.res.next()){
						cycleInstance=ValidationDAOUtility.res.getString("CYCLE_INSTANCE");
						serviceFilter=ValidationDAOUtility.res.getString("SERVICE_FILTER");
						if(serviceFilter!=null)
								{
								serviceFilterList.add(serviceFilter);
								}
						} 
					}	
					if(cycleInstance!=null && !cycleInstance.isEmpty())
						{
							flag="Pass";
							resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Check Usage: Zone2(CYCLE_INSTANCE-SERVICE_FILTER)</TD><TD>"+cycleInstance+" - "+serviceFilterList+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
						else {
							if (ValidationDAOUtility.con_EZ3!=null) {
								ValidationDAOUtility.ps=ValidationDAOUtility.con_EZ3.prepareStatement(query);
								ValidationDAOUtility.ps.setString(1, key);
								ValidationDAOUtility.ps.setString(2, key);
								ValidationDAOUtility.ps.setString(3, key);
								ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
								while(ValidationDAOUtility.res.next()){
									cycleInstance=ValidationDAOUtility.res.getString("CYCLE_INSTANCE");
									serviceFilter=ValidationDAOUtility.res.getString("SERVICE_FILTER");
									if(serviceFilter!=null){
										serviceFilterList.add(serviceFilter);
									}
								} 
							if(cycleInstance!=null){
								flag="Pass";
								resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Check Usage: Zone3(CYCLE_INSTANCE-SERVICE_FILTER)</TD><TD>"+cycleInstance+" - "+serviceFilterList+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
							}
							else if(cycleInstance==null || cycleInstance.isEmpty()){		
								flag="Fail";
								resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Check Usage: Zone1, Zone2, Zone3 (CYCLE_INSTANCE-SERVICE_FILTER)</TD><TD>No usage present on BAN</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
							}
						}
					}
				} 
			}
			catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Enabler DB- Check Usage: "+ e);
			}
	}
	
	public void enablerunifiedbanactivationdate(String key, List<String> resultList, String query) {
		ArrayList<Date> dateList=new ArrayList<Date>();
		try {
			if (ValidationDAOUtility.con_EZ1!=null) {
				dateList=process.checkActivationDateEnabler(ValidationDAOUtility.con_EZ1,key,query);
			}
			if (!dateList.isEmpty()) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Check BAN Activation Date: Zone1</TD><TD>Effective Date: "+dateList.get(0)+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else {
				if (ValidationDAOUtility.con_EZ2!=null) {
					dateList= process.checkActivationDateEnabler(ValidationDAOUtility.con_EZ2,key,query);
				}
				if (!dateList.isEmpty()) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Check BAN Activation Date: Zone2</TD><TD>Effective Date: "+dateList.get(0)+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else {
					if (ValidationDAOUtility.con_EZ3!=null) {
						dateList= process.checkActivationDateEnabler(ValidationDAOUtility.con_EZ3,key,query);
						if (!dateList.isEmpty()) {
							flag="Pass";
							resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Check BAN Activation Date: Zone3</TD><TD>Effective Date: "+dateList.get(0)+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
						else {
							flag="Fail";
							resultList.add("<TD>"+key+"</TD><TD>ENABLER</TD><TD>Check BAN Activation Date: Zone1, Zone2, Zone3</TD><TD>Customer Id Not Flown to Enabler</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
					}
				} 
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check BAN Activation Date in Enabler DB: "+ e); 
		}
	}
	
	public void ccrunifiedCheckdtvpackage(String key, String dTVBAN, List<String> resultList, String query) {
		String result=null;
		try {
			result=process.chckbxDTVPackage(ValidationDAOUtility.con_CCR,dTVBAN, query);
			if (result!=null) {
				flag="Pass";
				resultList.add("<TD>"+key+"-"+dTVBAN+"</TD><TD>CCR</TD><TD>DTV package details(SVC_NAME for BasePkg category)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			
			} else {
				flag="Fail";
				resultList.add("<TD>"+key+"-"+dTVBAN+"</TD><TD>CCR</TD><TD>DTV package details(SVC_NAME for BasePkg category)</TD><TD>NOT PRESENT</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
		}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check Brn Indicator in CCR DB: "+ e); 
		}			
	}

	public void ccrunifieddtvequipment(String key, String dTVBAN, List<String> resultList, String query) {
		ArrayList<String> attributeValueList=new ArrayList<String>();
		try {
			attributeValueList=process.chckbxDTVEquipments(ValidationDAOUtility.con_CCR,dTVBAN, query);
			if (!attributeValueList.isEmpty()) {
				flag="Pass";
				resultList.add("<TD>"+key+"-"+dTVBAN+"</TD><TD>CCR</TD><TD>DTV Equipment details(Attribute Value for 'Receiver_type')</TD><TD>"+attributeValueList+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			} else {
				flag="Fail";
				resultList.add("<TD>"+key+"-"+dTVBAN+"</TD><TD>CCR</TD><TD>DTV Equipment details(Attribute Value for 'Receiver_type')</TD><TD>NOT PRESENT</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
		}
		}catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check Brn Indicator in CCR DB: "+ e); 
		}		
	}

	public void ccrunifiedgetbillingaddress(String key, String dTVBAN, List<String> resultList, String query) {
		String result=null;
		try {
			result=process.chckbxBillingAddress(ValidationDAOUtility.con_CCR,dTVBAN, query);
			if (result!=null) {
				flag="Pass";
				resultList.add("<TD>"+key+"-"+dTVBAN+"</TD><TD>CCR</TD><TD>Billing Address(stNum/stName/city/stateProv/zipcode)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
		
			} else {
				flag="Fail";
				resultList.add("<TD>"+key+"-"+dTVBAN+"</TD><TD>CCR</TD><TD>Billing Address(stNum/stName/city/stateProv/zipcode)</TD><TD>NOT PRESENT</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
		}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check Brn Indicator in CCR DB: "+ e); 
		}
	}
	
	public void ccrunifiedwlsbanstatusanddate(String key, String wlsBAN, List<String> resultList, String query) {
		ArrayList<String> result=new ArrayList<String>();
		try {
			result=process.chckbxWlsBanstatus(ValidationDAOUtility.con_CCR,wlsBAN, query);
			if (!result.isEmpty()) {
				flag="Pass";
				resultList.add("<TD>"+key+"-"+wlsBAN+"</TD><TD>CCR</TD><TD>Wls Ban Status(BAN_STATUS/START_SERVICE_DATE)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			} else {
				flag="Fail";
				resultList.add("<TD>"+key+"-"+wlsBAN+"</TD><TD>CCR</TD><TD>Wls Ban Status(BAN_STATUS/START_SERVICE_DATE)</TD><TD>NOT PRESENT</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Wls Ban Status in CCR DB: "+ e); 
		}
	}
	
	public void pcaccrlegacydtvban(String key, String dTVBan, List<String> resultList, String query) {
		if (!dTVBan.equals("NA")) {
		String result=null;
		try {
			result= process.checkInCcrLegacy(ValidationDAOUtility.con_CCR,dTVBan,query);
			if (result!=null && !result.isEmpty()) {
				flag="Pass";
				resultList.add("<TD>"+key+"-"+dTVBan+"</TD><TD>PCA Validation</TD><TD>In CCR  LEGACY DTV BAN(ACCT_NUM/ACCT_STAT/ACCT_BAL)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else{
				flag="Fail";
				resultList.add("<TD>"+key+"-"+dTVBan+"</TD><TD>PCA Validation</TD><TD>In CCR  LEGACY DTV BAN(ACCT_NUM/ACCT_STAT/ACCT_BAL)</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: PCA Validation: UHV Address Validation: "+ e); 
		}
		}	
	}

	public void pcaUHVaddress(String key, List<String> resultList, String query) {
		String result=null;
		try {
			result= process.checkUhvAddressValidation(ValidationDAOUtility.con_CCR,key,query);
			if (result!=null && !result.isEmpty()) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>PCA Validation</TD><TD>UHV Address Validation(ADDR/CITY/STATE/ZIP)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else{
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>PCA Validation</TD><TD>UHV Address Validation(ADDR/CITY/STATE/ZIP)</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
		} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception ::PCA Validation: UHV Address Validation: "+ e); 
		}			
	}

	public void pcaccruverseWLLWLS(String key, String wlsBAN, String dTVBAN, List<String> resultList, String query) {
		String result=null;
		try {
			result= process.checkPCAValidationForUverseWlsLegacyDtvBan(ValidationDAOUtility.con_CCR,key,query);
			if (result!=null && !result.isEmpty()) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>PCA Validation</TD><TD>In CCR -For UVERSE/WLL BAN(aflt_co_cd/aflt_type_cd)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else{
				flag="Fail";
				resultList.add("<TD>"+key+"</TD><TD>PCA Validation</TD><TD>In CCR -For UVERSE/WLL BAN(aflt_co_cd/aflt_type_cd)</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			if (!wlsBAN.equals("NA")) {
				result= process.checkPCAValidationForUverseWlsLegacyDtvBan(ValidationDAOUtility.con_CCR,wlsBAN,query);
				if (result!=null && !result.isEmpty()) {
					flag="Pass";
					resultList.add("<TD>"+key+"-"+wlsBAN+"</TD><TD>PCA Validation</TD><TD>In CCR -For UVERSE/WLL BAN(aflt_co_cd/aflt_type_cd)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else{
					flag="Fail";
					resultList.add("<TD>"+key+"-"+wlsBAN+"</TD><TD>PCA Validation</TD><TD>In CCR -For UVERSE/WLL BAN(aflt_co_cd/aflt_type_cd)</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
			}
			
			if (!dTVBAN.equals("NA")) {
				result= process.checkPCAValidationForUverseWlsLegacyDtvBan(ValidationDAOUtility.con_CCR,dTVBAN,query);
				if (result!=null && !result.isEmpty()) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>PCA Validation</TD><TD>In CCR -For UVERSE/WLL BAN(aflt_co_cd/aflt_type_cd)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else{
					flag="Fail";
					resultList.add("<TD>"+key+"</TD><TD>PCA Validation</TD><TD>In CCR -For UVERSE/WLL BAN(aflt_co_cd/aflt_type_cd)</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: PCA Validation: In CCR - For UVERSE/WLL, WLS AND LEGACY DTV BAN: "+ e); 
		}		
	}

	public void converteddtvenablertoccr(String key, List<String> resultList, String query) {
		String result=null;
		try {
			if (ValidationDAOUtility.con_CCR!=null) {
				result= process.checkDTVEnablertoCCRreplication(ValidationDAOUtility.con_CCR,key,query);
				if (result!=null && !result.isEmpty()) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>Converted DTV Validation</TD><TD>DTV Enabler to CCR replication(currentbalancedue/pastduebalance)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else {
					flag="Fail";
					resultList.add("<TD>"+key+"</TD><TD>Converted DTV Validation</TD><TD>DTV Enabler to CCR replication(currentbalancedue/pastduebalance)</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: DTV Enabler to CCR replication: "+ e); 
		}		
	}

	public void converteddtvcrmtoccr(String key, List<String> resultList, String query) {
		String result=null;
		try {
			if (ValidationDAOUtility.con_CCR!=null) {
				result= process.checkDTVCRMtoCCRreplication(ValidationDAOUtility.con_CCR,key,query);
				if (result!=null && !result.isEmpty()) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>Converted DTV Validation</TD><TD>DTV CRM to CCR replication(ban / directv_acct_id)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
				else{
					flag="Fail";
					resultList.add("<TD>"+key+"</TD><TD>Converted DTV Validation</TD><TD>DTV CRM to CCR replication(ban / directv_acct_id)</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: DTV CRM to CCR replication: "+ e); 
		}			
	}

	public void converteddtvbillcycle(String key, List<String> resultList, String query) {
		String result=null;
		try {
			if (ValidationDAOUtility.con_EZ1!=null) {
				result= process.checkLegacyDTVAssosiationEnabler(ValidationDAOUtility.con_EZ1,key,query);
			}
			if (result!=null && !result.isEmpty()) {
				flag="Pass";
				resultList.add("<TD>"+key+"</TD><TD>Converted DTV Validation</TD><TD>Bill Cycle and Legacy DTV Assosiation: Zone1</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			else {
				if (ValidationDAOUtility.con_EZ2!=null) {
					result= process.checkCycleBanEnabler(ValidationDAOUtility.con_EZ2,key,query);
				}
				if (result!=null && !result.isEmpty()) {
					flag="Pass";
					resultList.add("<TD>"+key+"</TD><TD>Converted DTV Validation</TD><TD>Bill Cycle and Legacy DTV Assosiation: Zone2</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					}
				else {
					if (ValidationDAOUtility.con_EZ3!=null) {
						result= process.checkCycleBanEnabler(ValidationDAOUtility.con_EZ3,key,query);
						if (result!=null && !result.isEmpty()) {
							flag="Pass";
							resultList.add("<TD>"+key+"</TD><TD>Converted DTV Validation</TD><TD>Bill Cycle and Legacy DTV Assosiation: Zone3</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
							}
						else {
							flag="Fail";
							resultList.add("<TD>"+key+"</TD><TD>Converted DTV Validation</TD><TD>Bill Cycle and Legacy DTV Assosiation: Zone1, Zone2, Zone3</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
					}
				}
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Bill Cycle and Legacy DTV Assosiation:: "+ e); 
		}
	
		
	}

	/***************************************************************************************************************************************************/
/**********************************************************************Unified Individual Query End*************************************************************************/	
	/***************************************************************************************************************************************************/

	/***************************************************************************************************************************************************/
/**********************************************************************Wireless Query Start*************************************************************************/
	/**********************************************************************Query End*****************************************************************************/
	public void wlsccrcheckctn(String wirelessCTN, List<String> resultList, String query) {
		if (!(wirelessCTN.equals("null"))) {
			String listOfsubscriberNumber;
			String[] ctnArray = null;
		
			ctnArray = wirelessCTN.split("/");
			for (String ctn : ctnArray) {
				try {
					listOfsubscriberNumber = process.chckCTN(ValidationDAOUtility.con_CCR, ctn, query);
					if (listOfsubscriberNumber==null) {
						flag = "Fail";
						resultList.add("<TD>"+ctn+"</TD><TD>CCR</TD><TD>Check CTN</TD><TD>No Row Retrieved- CTN("+ ctn+ ") is not present in CCR DB</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					} else {
					flag = "Pass";
					resultList.add("<TD>"+ctn+"</TD><TD>CCR</TD><TD>Check CTN</TD><TD>Row Retrieved- CTN("+ listOfsubscriberNumber+ ") is present in CCR DB</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					}
				} catch (Exception e) {
				}
			}
		}
	}
	
	public void wlsccrcheckban(String wirelessBAN, List<String> resultList, String query) {
		
		int count = 0;
		try {
			count = process.TBBanValidation_Address_OMS(
					ValidationDAOUtility.con_CCR, wirelessBAN, query);
			if (count != 0) {
				flag = "Pass";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>CCR</TD><TD>Check BAN</TD><TD>Row Retrieved- BAN is present in CCR DB</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			} else {
				flag = "Fail";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>CCR</TD><TD>Check BAN</TD><TD>No Row Retrieved- BAN is not present in CCR DB</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				
			}
		} catch (Exception e) {
		}
	}

	public void wlsccrcheckslidmember(String wirelessBAN, List<String> resultList, String query) {		
		String tuid = null;
		String result = null;
		try {
			tuid = process.findTuid(ValidationDAOUtility.con_CCR,
					wirelessBAN, query);
			if (tuid == null) {
				flag = "Fail";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>CCR</TD><TD>Check Slid Member Id</TD><TD>TUID NOT PRESENT</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				
			} else {
				flag = "Pass";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>CCR</TD><TD>Check Slid Member Id(TUID)</TD><TD>"+tuid+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				
				query=CCR_CHECK_SLID_MEMBER_ID.getValue();
				result = process.chckbxCheckSlidMemberId(ValidationDAOUtility.con_CCR, tuid, query);
				if (result != null) {
					flag = "Pass";
					resultList.add("<TD>"+wirelessBAN+"</TD><TD>CCR</TD><TD>Check Slid Member Id (FIRSTNAME-LASTNAME-PARENTMEMBERID)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				} else {
					flag = "Fail";
					resultList.add("<TD>"+wirelessBAN+"</TD><TD>CCR</TD><TD>Check Slid Member Id (FIRSTNAME-LASTNAME-PARENTMEMBERID)</TD><TD>NOT PRESENT</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					
				}
			}
		} catch (Exception e) {
		}
	}

	public void wlsccrcheckaccountlinkedplentiid(String wirelessBAN, List<String> resultList, String query) {

		String result = null;
		try {
			result = process.chckbxAccountlinkedPlentiID(
					ValidationDAOUtility.con_CCR, wirelessBAN, query);
			if (result != null) {
				flag = "Pass";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>CCR</TD><TD>Account linked Plenti ID:LOYALTYOFFERACCEPTANC (SORCODE-LOYALITY_ID-LOYALITY_PROGRAM-ASSOC_MADE_BY)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				
			} else {
				flag = "Fail";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>CCR</TD><TD>Account linked Plenti ID:LOYALTYOFFERACCEPTANC (SORCODE-LOYALITY_ID-LOYALITY_PROGRAM-ASSOC_MADE_BY)</TD><TD>NOT PRESENT</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				
			}
			query = CCR_CHECK_ACCOUNT_LINKED_TO_PLENTI_ID.getValue();
			result = process.chckbxAccountlinkedPlentiID(ValidationDAOUtility.con_CCR, wirelessBAN, query);
			if (result != null) {
				flag = "Pass";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>CCR</TD><TD>Account linked Plenti ID:LOYALTYPROGACCTASSOC (SORCODE-LOYALITY_ID-LOYALITY_PROGRAM-ASSOC_MADE_BY)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			} else {
				flag = "Fail";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>CCR</TD><TD>Account linked Plenti ID:LOYALTYPROGACCTASSOC (SORCODE-LOYALITY_ID-LOYALITY_PROGRAM-ASSOC_MADE_BY)</TD><TD>NOT PRESENT</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
		} catch (Exception e) {
		}	
	}
	
	public void wlsenablercheckctnandsocflown(String wirelessBAN, String wirelessCTN, List<String> resultList, String query,
			Connection enablercon) {
		if (!(wirelessCTN.equals("null"))) {	
			ArrayList<String> list = new ArrayList<String>();
			String[] ctnArray = wirelessCTN.split("/");
			for (String ctn : ctnArray) {
				try {
					list = process.chckbxCTNflownEnabler(enablercon, ctn, query);
					if (!list.isEmpty()) {
						flag = "Pass";
						for (String l : list) {
							resultList.add("<TD>"+wirelessBAN+ "-" + ctn+"</TD><TD>ENABLER</TD><TD>CTN and SOCs flown (TLG_SOC/SOC_STATUS/EFFECTIVE_DATE/EXPIRATION_DATE)</TD><TD>"+l+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
					} else {
						flag = "Fail";
						resultList.add("<TD>"+wirelessBAN+ "-" + ctn+"</TD><TD>ENABLER</TD><TD>CTN and SOCs flown (TLG_SOC/SOC_STATUS/EFFECTIVE_DATE/EXPIRATION_DATE)</TD><TD>Not flown</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					}
				} catch (Exception e) {
					e.printStackTrace();
				//LoggerWrapper.logger.info("Exception :: CTN and SOCs flown in Enabler DB: "+ e);
				}
			}
		}
	}

	public void wlsenabletgetIMSI(String wirelessBAN, String wirelessCTN, List<String> resultList, String query, Connection enablercon) {
		if (!(wirelessCTN.equals("null"))) {	
			String result = null;
			String[] ctnArray = null;
			ctnArray = wirelessCTN.split("/");
			for (String ctn : ctnArray) {
				try {
					result = process.chckbxGetImsiValue(enablercon, ctn, query);
					if (result == null || result.isEmpty()) {
						flag = "Fail";
						resultList.add("<TD>"+wirelessBAN+ "-" + ctn+"</TD><TD>ENABLER</TD><TD>Imsi Value</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					} else {
						flag = "Pass";
						resultList.add("<TD>"+wirelessBAN+ "-" + ctn+"</TD><TD>ENABLER</TD><TD>Imsi Value</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
				} catch (Exception e) {
					//LoggerWrapper.logger.info("Exception :: Get Imsi Value in Enabler DB: "+ e);
				}
			}
		}	
	}

	public void wlsenablercheckDataUsage(String wirelessBAN,String wirelessCTN, List<String> resultList, String query,
			Connection enablercon) {
		if (!(wirelessCTN.equals("null"))) {
			ArrayList<String> result = null;
			String[] ctnArray = null;
			ctnArray = wirelessCTN.split("/");
			for (String ctn : ctnArray) {
				try {
					result = process.chckbxDataUsagesEnabler(enablercon, ctn, query);
					if (result.isEmpty()) {
						flag = "Fail";
						resultList.add("<TD>"+wirelessBAN+ "-" + ctn+"</TD><TD>ENABLER</TD><TD>Data Usage</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					} 
					else {
						flag = "Pass";
						for (String l : result) {	
							resultList.add("<TD>"+wirelessBAN+ "-" + ctn+"</TD><TD>ENABLER</TD><TD>Data usage</TD><TD>"+l+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
					}
				} catch (Exception e) {
					//LoggerWrapper.logger.info("Exception :: Check data usage in Enabler DB: "+ e);
				}
			}
		}
	}

	public void wlstlggetsequencenumber(String wirelessBAN, String wirelessCTN, List<String> resultList, String query,
			Connection tlgcon) {
		if (!(wirelessCTN.equals("null"))) {
			String[] ctnArray = null;
			ctnArray = wirelessCTN.split("/");
			ArrayList<String> result = new ArrayList<String>();
			for (String ctn : ctnArray) {
				try {
					result = process.chckbxGetSequenceNoTLG(tlgcon,wirelessBAN, ctn, query);
					if (result.isEmpty()) {
						flag = "Fail";
						resultList.add("<TD>"+wirelessBAN+ "-" + ctn+"</TD><TD>TLG</TD><TD>Get Sequence No</TD><TD>SEQUENCE_NUM not present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					} else {
						flag = "Pass";
						resultList.add("<TD>"+wirelessBAN+ "-" + ctn+"</TD><TD>TLG</TD><TD>Get Sequence No</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
						}
				} catch (Exception e) {
					//LoggerWrapper.logger.info("Exception :: Get Sequence No in TLG DB: "+ e);
				}
			}
		}
	}

	public void wlstlgcheckdeviceadded(String wirelessBAN, List<String> resultList, String query, Connection tlgcon) {

		List<String> result = null;
		try {
			result = process.chckbxDeviceAddedCTNTLG(tlgcon,wirelessBAN, query);
			if (!resultList.isEmpty()) {
				flag = "Pass";
				for (String l : result) {
					resultList.add("<TD>"+wirelessBAN+"</TD><TD>TLG</TD><TD>Check Device added in the CTN (subscriber_no-unit_esn-equ_attr</TD><TD>"+l+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
			} else {
				flag = "Fail";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>TLG</TD><TD>Check Device added in the CTN</TD><TD>Device not added</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check Device added in the CTN in TLG DB:: "+ e);
		}
	}

	public void wlstlgcheckemaileffectivity(String wirelessBAN, List<String> resultList, String query,
			Connection tlgcon) {

		List<String> result = null;
		try {
			result = process.chckbxCheckEmailEffecttivity(tlgcon,wirelessBAN, query);
			if (result != null && !result.isEmpty()) {
				flag = "Pass";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>TLG</TD><TD>Check Email Effecttivity (EMAIL_ID/EMAIL_EFF_DATE)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			} else {
				flag = "Fail";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>TLG</TD><TD>Check Email Effecttivity</TD><TD>Not Present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check Email Effecttivity in TLG DB: "+ e);
		}
	
		
	}

	public void wlsmpscheckbanflown(String wirelessBAN, List<String> resultList, String query) {
		String result = null;
		try {
			result = process.chckbxcBANflown(ValidationDAOUtility.con_MPS, wirelessBAN, query);
			if (result != null) {
				flag = "Pass";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>MPS</TD><TD>Check BAN and Slid Flown (MEMBER_NUM- MEMBER_NAME- DOMAIN_ID- GROUP_NUM- SOR_ID- ACCOUNT_TYPE- SOR_INVARIANT_ID)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			} else {
				flag = "Fail";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>MPS</TD><TD>Check BAN and Slid Flown</TD><TD>Not Fown</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check Check BAN Flown in MPS DB: "+ e);
		}
	
		
	}

	public void wlsmpscheckaccountlinkedplentiid(String wirelessBAN, List<String> resultList, String query) {

		String result = null;
		try {
			result = process.chckbxAccountLinkedPlentiIDMPS(
					ValidationDAOUtility.con_MPS, wirelessBAN, query);
			if (result == null || result.isEmpty()) {
				flag = "Fail";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>MPS</TD><TD>Account is Linked to Plenti ID: LOYALTYPROGACCTASSOC</TD><TD>Not Linked</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			} else {
				flag = "Pass";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>MPS</TD><TD>Account is Linked to Plenti ID: LOYALTYPROGACCTASSOC (SOR_ID-SOR_INVARIANT_ID-LOYALITY_PROGRAM_ID-ASSOC_MADE_BY)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			}
			String query1=null;
			result = process.chckbxAccountLinkedPlentiIDMPS(
					ValidationDAOUtility.con_MPS, wirelessBAN, query1);
			if (result == null || result.isEmpty()) {
				flag = "Fail";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>MPS</TD><TD>Account is Linked to Plenti ID: LOYALTYOFFERACCEPTANCE</TD><TD>Not Linked</TD><TD>"+flag+"</TD><TD>"+query1+"</TD>");
			} else {
				flag = "Pass";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>MPS</TD><TD>Account is Linked to Plenti ID: LOYALTYOFFERACCEPTANCE (SOR_ID-SOR_INVARIANT_ID-LOYALITY_PROGRAM_ID-ASSOC_MADE_BY)</TD><TD>Not Linked</TD><TD>"+flag+"</TD><TD>"+query1+"</TD>");
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check Account is Linked to Plenti ID in MPS DB: "+ e);
		}
	
		
	}

	public void wlsmpscheckplentiidlinkedtoaccount(String wirelessBAN, List<String> resultList, String query) {

		String cardNum = null;
		String result = null;
		try {
			cardNum = process.findCardNum(ValidationDAOUtility.con_MPS, wirelessBAN, query);
			if (cardNum == null || cardNum.isEmpty()) {
				flag = "Fail";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>MPS</TD><TD>Plenti ID Linked to any Account</TD><TD>Not Linked: LOYALITY_CARD_NUM not Present</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
			} else {
				flag = "Pass";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>MPS</TD><TD>Plenti ID Linked to any Account</TD><TD>LOYALITY_CARD_NUM: "+cardNum+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
				
				String query1=null;/////////////////////////////////////////
				result = process.chckbxAccountLinkedPlentiIDMPS(ValidationDAOUtility.con_MPS, cardNum,query1);
				if (result == null || result.isEmpty()) {
					flag = "Fail";
					resultList.add("<TD>"+wirelessBAN+"</TD><TD>MPS</TD><TD>Plenti ID Linked to any Account</TD><TD>Not Linked</TD><TD>"+flag+"</TD><TD>"+query1+"</TD>");
				} else {
					flag = "Pass";
					resultList.add("<TD>"+wirelessBAN+"</TD><TD>MPS</TD><TD>Plenti ID Linked to any Account (SOR_ID-SOR_INVARIANT_ID-LOYALITY_PROGRAM_ID-ASSOC_MADE_BY)</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query1+"</TD>");
				}
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check plenti iD is linked to any account in MPS DB: "+ e);
		}
	
		
	}

	

	public void wlsmpscheckdesignatedmainctn(String wirelessBAN, List<String> resultList, String query) {
		String memName = null;
		String result = null;
		String query1=null;
		try {
			memName = process.findSlidMPS(ValidationDAOUtility.con_MPS, wirelessBAN, query1);
			if (memName != null && !memName.isEmpty()) {
				result = process.chckbxDesignatedMainCTN(ValidationDAOUtility.con_MPS, memName, query);
				if (result == null || result.isEmpty()) {
						flag = "Fail";
						resultList.add("<TD>"+wirelessBAN+"</TD><TD>MPS</TD><TD>Check Designated Main CTN</TD><TD>Not Present for the slid: "+memName+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					} else {
						flag = "Pass";
						resultList.add("<TD>"+wirelessBAN+"</TD><TD>MPS</TD><TD>Check Designated Main CTN</TD><TD>"+result+"</TD><TD>"+flag+"</TD><TD>"+query+"</TD>");
					}
			} 
			else {
				flag = "Fail";
				resultList.add("<TD>"+wirelessBAN+"</TD><TD>MPS</TD><TD>Check Designated Main CTN</TD><TD>Slid Not Present</TD><TD>"+flag+"</TD><TD>"+query1+"</TD>");
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Check Slid Flown in MPS DB: "+ e);
		}
	
		
	
		
	}

	





	
	
/**********************************************************************Wireless Query End *************************************************************************/

	






	
			 
 }