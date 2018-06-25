package com.att.dbvalidation.services;

import static com.att.dbvalidation.properties.DataValidationEnum.getBEListFromPropertis;
import static com.att.dbvalidation.properties.DataValidationEnum.getEnumFromString;
import static com.att.dbvalidation.properties.DataValidationEnum.getEnumValueFromString;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.att.dbvalidation.dao.ValidationDAOUtility;
import com.att.dbvalidation.model.DBValidationmodel;
import com.att.dbvalidation.properties.DBConnectionProperties;
import com.att.dbvalidation.properties.DataValidationEnum;

@Service
public class WirelessService {
	
	@Autowired
	DataValidationCommonServices dvservices;
		
	@Autowired
	DBConnectionProperties dbconnectionproperties;
	
	public Set<String> getWLSQueries(Model model) {
		Set<String> queriesList=new LinkedHashSet<>();
		List<String> ccrQueries=getBEListFromPropertis("CCR_WLS_QUERY_",1);		 
		List<String> mpsQueries=getBEListFromPropertis("MPS_WLS_QUERY_",1);	
		List<String> enablerQueries=getBEListFromPropertis("ENABLER_WLS_QUERY_",1);
		List<String> tlgQueries=getBEListFromPropertis("TLG_WLS_QUERY_",1);
		List<String> batchQueries=getBEListFromPropertis("BATCH_WLS_QUERY_",1);
		model.addAttribute("ccrQueries", ccrQueries);
		model.addAttribute("mpsQueries", mpsQueries);
		model.addAttribute("enablerQueries", enablerQueries);
		model.addAttribute("tlgQueries", tlgQueries);
		model.addAttribute("batchQueries", batchQueries);
		queriesList.addAll(ccrQueries);
		queriesList.addAll(mpsQueries);
		queriesList.addAll(enablerQueries);
		queriesList.addAll(tlgQueries);
		queriesList.addAll(batchQueries);
		return queriesList;
	}
	
	public List<String> wirelessvalidation(DBValidationmodel dbmodel, Model model, 
			List<String> banList, String e2eQueries, String release) {
		Set<String> queryList;	
		List<String> result = new ArrayList<>();
		try {
			if (e2eQueries.equals("allQueries")) {
				queryList=getWLSQueries(model);
				allconnection(release,banList);
			}
			else{
				queryList=dvservices.uversewirelessselectedQueries(dbmodel.getQuery());
				individualconnection(dbmodel.getQuery(), release, banList);
			}			
			result=validation(queryList, banList, release);
		} catch (Exception e) {
		}
		finally{
			ValidationDAOUtility.ClosingConnection();
		}
		return result;
	}

	private void allconnection(String release, List<String> banList) {
		try {
			String[] CCR_Details = dbconnectionproperties.CCR_DB(release);
			ValidationDAOUtility.CCRConnection(CCR_Details);

			String[] MPS_Details = dbconnectionproperties.MPS_DB(release);
			ValidationDAOUtility.MPSConnection(MPS_Details);
			
			enablerwirelessdbconnection(release, banList);
			
			tlgwirelessdbconnection(release, banList);

			String[] Batch_Details = dbconnectionproperties.Batch_DB(release);
			ValidationDAOUtility.BatchConnection(Batch_Details);
				
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception ::"+e);
		}	
	}
	
	private void individualconnection(String backends, String release, List<String> banList) {

		if (backends.contains("CCR")){
			try {
				String[] CCR_Details = dbconnectionproperties.CCR_DB(release);
				System.out.println("CCR_Details: "+CCR_Details[0]+"---"+CCR_Details[1]+" --"+CCR_Details[2]);
				ValidationDAOUtility.CCRConnection(CCR_Details);
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Data_Sheet Reading CCR Connection parameters: "+ e);
			}				
		}
		
		if (backends.contains("MPS")) {
			try {
				String[] MPS_Details = dbconnectionproperties.MPS_DB(release);
				ValidationDAOUtility.MPSConnection(MPS_Details);
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Data_Sheet Reading MPS Connection parameters: "+ e);
			}
		}
		
		if (backends.contains("ENABLER")) {
			enablerwirelessdbconnection(release, banList);
		}
		
		if (backends.contains("TLG")) {
			tlgwirelessdbconnection(release, banList);
		}
		
		if (backends.contains("BATCH")) {
			try {
				String[] Batch_Details = dbconnectionproperties.Batch_DB(release);
				ValidationDAOUtility.BatchConnection(Batch_Details);
			 } catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Data_Sheet Reading Batch Connection parameters: "+ e);
			}
		}
	}

	private void enablerwirelessdbconnection(String release, List<String> banList) {
		try {
			Set<String> uniqueMarketList = new HashSet<String>();
			String market = null;
			Iterator<String> itr = banList.iterator();
			while (itr.hasNext()) {
				String key = itr.next().toString();
				String[] split = key.split("-");
				market = split[1];
				uniqueMarketList.add(market);
			}
			for (String uniqueMarket : uniqueMarketList) {
				switch (uniqueMarket) {
				case "DLS":
					String[] ENB_DLS_Details = dbconnectionproperties.ENB_DB_DLS(release);
					ValidationDAOUtility.ENB_DLS_Connection(ENB_DLS_Details);
					break;
				case "SAN":
					String[] ENB_SAN_Details = dbconnectionproperties.ENB_DB_DLS(release);
					ValidationDAOUtility.ENB_DLS_Connection(ENB_SAN_Details);
					break;
				case "GAC":
					String[] ENB_GAC_Details = dbconnectionproperties.ENB_DB_GAC(release);
					ValidationDAOUtility.ENB_GAC_Connection(ENB_GAC_Details);
					break;
				case "FLP":
					String[] ENB_FLP_Details = dbconnectionproperties.ENB_DB_GAC(release);
					ValidationDAOUtility.ENB_GAC_Connection(ENB_FLP_Details);
					break;
				case "MWR":
					String[] ENB_MWR_Details = dbconnectionproperties.ENB_DB_MWR(release);
					ValidationDAOUtility.ENB_MWR_Connection(ENB_MWR_Details);
					break;
				case "GLR":
					String[] ENB_GLR_Details = dbconnectionproperties.ENB_DB_MWR(release);
					ValidationDAOUtility.ENB_MWR_Connection(ENB_GLR_Details);
					break;
				case "ILL":
					String[] ENB_ILL_Details = dbconnectionproperties.ENB_DB_MWR(release);
					ValidationDAOUtility.ENB_MWR_Connection(ENB_ILL_Details);
					break;
				case "NBI":
					String[] ENB_NBI_Details = dbconnectionproperties.ENB_DB_NBI(release);
					ValidationDAOUtility.ENB_NBI_Connection(ENB_NBI_Details);
					break;
				case "PAC":
					String[] ENB_PAC_Details = dbconnectionproperties.ENB_DB_PAC(release);
					ValidationDAOUtility.ENB_PAC_Connection(ENB_PAC_Details);
					break;
				case "ALH":
					String[] ENB_ALH_Details = dbconnectionproperties.ENB_DB_PAC(release);
					ValidationDAOUtility.ENB_PAC_Connection(ENB_ALH_Details);
					break;
				}
			}
		} catch (Exception e) {
			//LoggerWrapper.loggerinfo("Exception :: Data_Sheet Reading Enabler Connection parameters: "+ e);
		}
	}
	
	private void tlgwirelessdbconnection(String release, List<String> banList) {
		try {
			Set<String> uniqueMarketList = new HashSet<String>();
			String market = null;
			Iterator<String> itr = banList.iterator();
			while (itr.hasNext()) {
				String key = itr.next().toString();
				String[] split = key.split("-");
				market = split[1];
				uniqueMarketList.add(market);
			}
			for (String uniqueMarket : uniqueMarketList) {
				switch (uniqueMarket) {
				case "DLS":
					String[] TLG_DLS_Details = dbconnectionproperties.TLG_DB_DLS(release);
					ValidationDAOUtility.TLG_DLS_Connection(TLG_DLS_Details);
					break;
				case "PAC":
					String[] TLG_PAC_Details = dbconnectionproperties.TLG_DB_PAC(release);
					ValidationDAOUtility.TLG_PAC_Connection(TLG_PAC_Details);
					break;
				case "SAN":
					String[] TLG_SAN_Details = dbconnectionproperties.TLG_DB_SAN(release);
					ValidationDAOUtility.TLG_SAN_Connection(TLG_SAN_Details);
					break;
				case "MWR":
					String[] TLG_MWR_Details = dbconnectionproperties.TLG_DB_MWR(release);
					ValidationDAOUtility.TLG_MWR_Connection(TLG_MWR_Details);
					break;
				case "GAC":
					String[] TLG_GAC_Details = dbconnectionproperties.TLG_DB_GAC(release);
					ValidationDAOUtility.TLG_GAC_Connection(TLG_GAC_Details);
					break;
				case "GLR":
					String[] TLG_GLR_Details = dbconnectionproperties.TLG_DB_GLR(release);
					ValidationDAOUtility.TLG_GLR_Connection(TLG_GLR_Details);
					break;
				case "NBI":
					String[] TLG_NBI_Details = dbconnectionproperties.TLG_DB_NBI(release);
					ValidationDAOUtility.TLG_NBI_Connection(TLG_NBI_Details);
					break;
				case "NWS":
					String[] TLG_NWS_Details = dbconnectionproperties.TLG_DB_NWS(release);
					ValidationDAOUtility.TLG_NWS_Connection(TLG_NWS_Details);
					break;
				}
			}
		} catch (Exception e) {
			//LoggerWrapper.logger.info("Exception :: Data_Sheet Reading TLG Connection parameters: "+ e);
		}
	}

	private List<String> validation(Set<String> queryList, List<String> banList,  String release){
		List<String> resultlist = new LinkedList<>();
		for (String ban : banList) {
			String[] split = ban.split("-");
			String wirelessBAN = split[0];
			String wirelessMarket = split[1];
			String wirelessCTN = split[2];
			validationQuery(wirelessBAN, wirelessMarket, wirelessCTN, queryList, resultlist);			
		}
		return resultlist;

	}
	
	private List<String> validationQuery(String wirelessBAN, String wirelessMarket, String wirelessCTN, Set<String> selectedQuery, List<String> resultList ){	
		try {
			 for (String finalQuery : selectedQuery) {
				DataValidationEnum queryPropKey=getEnumFromString(finalQuery);
				String query=getEnumValueFromString(finalQuery);
				Connection tlgcon=null;
				Connection enablercon=null;
				if (null==queryPropKey) {
					continue;
				}
				if (!(wirelessMarket.equals("null"))) {
					enablerTLGMarketconnection(wirelessMarket, tlgcon, enablercon);
				}
				switch (queryPropKey) {
				
				case CCR_WLS_QUERY_CCR_CHECK_CTN_IN_CCR:
					dvservices.wlsccrcheckctn(wirelessCTN, resultList, query);	
					break;
					
				case CCR_WLS_QUERY_CCR_CHECK_BAN_IN_CCR:
					dvservices.wlsccrcheckban(wirelessBAN, resultList, query);
					break;
					
				case CCR_WLS_QUERY_CCR_CHECK_SLID_MEMBER_ID:
					dvservices.wlsccrcheckslidmember(wirelessBAN,resultList, query);
					break;						
					
				case CCR_WLS_QUERY_CCR_CHECK_ACCOUNT_LINKED_TO_PLENTI_ID:
					dvservices.wlsccrcheckaccountlinkedplentiid(wirelessBAN, resultList, query);
					break;
					
				case MPS_WLS_QUERY_MPS_CHECK_BAN_FLOWN_IN_MPS_DB:
					dvservices.wlsmpscheckbanflown(wirelessBAN, resultList, query);	
					break;	
				
				case MPS_WLS_QUERY_MPS_CHECK_ACCOUNT_LINKED_TO_PLENTI_ID:
					dvservices.wlsmpscheckaccountlinkedplentiid(wirelessBAN,resultList, query);
					break;
					
				case MPS_WLS_QUERY_MPS_CHECK_PLENTI_ID_LINKED_TO_ANY_ACCOUNT:
					dvservices.wlsmpscheckplentiidlinkedtoaccount(wirelessBAN, resultList, query);		
					break;
					
				case MPS_WLS_QUERY_CHECK_DESIGNATED_MAIN_CTN:
					dvservices.wlsmpscheckdesignatedmainctn(wirelessBAN, resultList, query);
					break;
				
				case ENABLER_WLS_QUERY_ENABLER_CHECK_CTN_AND_SOC_FLOWN:
					dvservices.wlsenablercheckctnandsocflown(wirelessBAN,wirelessCTN, resultList, query,enablercon);	
					break;
					
				case ENABLER_WLS_QUERY_ENABLER_GET_IMSI_VALUE:
					dvservices.wlsenabletgetIMSI(wirelessBAN,wirelessCTN, resultList, query,enablercon);
					break;
					
				case ENABLER_WLS_QUERY_ENABLER_CHECK_DATA_USAGE:
					dvservices.wlsenablercheckDataUsage(wirelessBAN,wirelessCTN,resultList, query,enablercon);
					break;
					
				case TLG_WLS_QUERY_TLG_GET_SEQUENCE_NO:
					dvservices.wlstlggetsequencenumber(wirelessBAN,wirelessCTN,resultList, query,tlgcon);		
					break;
					
				case TLG_WLS_QUERY_TLG_CHECK_DEVICE_ADDED_IN_CTN:
					dvservices.wlstlgcheckdeviceadded(wirelessBAN,resultList, query,tlgcon);	
					break;
					
				case TLG_WLS_QUERY_TLG_CHECK_EMAIL_EFFECTIVITY:
					dvservices.wlstlgcheckemaileffectivity(wirelessBAN,resultList, query,tlgcon);
					break;
					
				case BATCH_WLS_QUERY_BATCH_CHECK_INDEXING:
					dvservices.wlsccrcheckslidmember(wirelessBAN,resultList, query);
					break;
					
				case BATCH_WLS_QUERY_BATCH_CHECK_USAGE:
					dvservices.ccrCheckBan(wirelessBAN, resultList, query);		
					break;
				
				default:
					break;
				}
				
					
				 }
			 
		 } 
		catch (Exception e) {
			}
		return resultList;
	}

	private void enablerTLGMarketconnection(String wirelessMarket, Connection tlgcon, Connection enablercon) {
		switch (wirelessMarket) {
		case "DLS":
			tlgcon = ValidationDAOUtility.con_TLG_DLS;
			break;
		case "PAC":
			tlgcon = ValidationDAOUtility.con_TLG_PAC;
			break;
		case "SAN":
			tlgcon = ValidationDAOUtility.con_TLG_SAN;
			break;
		case "MWR":
			tlgcon = ValidationDAOUtility.con_TLG_MWR;
			break;
		case "GAC":
			tlgcon = ValidationDAOUtility.con_TLG_GAC;
			break;
		case "GLR":
			tlgcon = ValidationDAOUtility.con_TLG_GLR;
			break;
		case "NBI":
			tlgcon = ValidationDAOUtility.con_TLG_NBI;
			break;
		case "NWS":
			tlgcon = ValidationDAOUtility.con_TLG_NWS;
			break;
		}
		
		switch (wirelessMarket) {
		case "DLS":
			enablercon = ValidationDAOUtility.con_ENB_DLS;
			break;
		case "SAN":
			enablercon = ValidationDAOUtility.con_ENB_DLS;
			break;
		case "GAC":
			enablercon = ValidationDAOUtility.con_ENB_GAC;
			break;
		case "FLP":
			enablercon = ValidationDAOUtility.con_ENB_GAC;
			break;
		case "MWR":
			enablercon = ValidationDAOUtility.con_ENB_MWR;
			break;
		case "GLR":
			enablercon = ValidationDAOUtility.con_ENB_MWR;
			break;
		case "ILL":
			enablercon = ValidationDAOUtility.con_ENB_MWR;
			break;
		case "NBI":
			enablercon = ValidationDAOUtility.con_ENB_NBI;
			break;
		case "PAC":
			enablercon = ValidationDAOUtility.con_ENB_PAC;
			break;
		case "ALH":
			enablercon = ValidationDAOUtility.con_ENB_PAC;
			break;
		}
	}
	
	
}
