package com.att.dbvalidation.services;

import static com.att.dbvalidation.properties.DataValidationEnum.getBEListFromPropertis;
import static com.att.dbvalidation.properties.DataValidationEnum.getEnumFromString;
import static com.att.dbvalidation.properties.DataValidationEnum.getEnumValueFromString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.att.dbvalidation.dao.ValidationDAOUtility;
import com.att.dbvalidation.model.DBValidationmodel;
import com.att.dbvalidation.properties.DBConnectionProperties;
import com.att.dbvalidation.properties.DataValidationEnum;

@Service
public class UverseService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	DataValidationCommonServices dvservices;
	
	@Autowired
	DBConnectionProperties dbconnectionproperties;
	
	public Set<String> getUverseQueries(Model model) {
		Set<String> queriesList=new LinkedHashSet<String>();
		List<String> omsQueries=getBEListFromPropertis("OMS_UVERSE_QUERY_",1);
		List<String> omstoccrQueries=getBEListFromPropertis("OMS_TO_CCR_REP_UVERSE_QUERY_",1);		 
		List<String> enablerQueries=getBEListFromPropertis("ENABLER_UVERSE_QUERY_",1);	
		List<String> mpsQueries=getBEListFromPropertis("MPS_UVERSE_QUERY_",1);	
		List<String> mpstoccrQueries=getBEListFromPropertis("MPS_TO_CCR_REP_UVERSE_QUERY_",1);
		List<String> ccrQueries=getBEListFromPropertis("CCR_UVERSE_QUERY_",1);	
		List<String> batchQueries=getBEListFromPropertis("BATCH_UVERSE_QUERY_",1);
		List<String> bdsQueries=getBEListFromPropertis("BDS_UVERSE_QUERY_",1);		 
		List<String> slidQueries=getBEListFromPropertis("SLID_UVERSE_QUERY_",1);	
		model.addAttribute("omsQueries", omsQueries);
		model.addAttribute("omstoccrQueries", omstoccrQueries);
		model.addAttribute("enablerQueries", enablerQueries);
		model.addAttribute("mpsQueries", mpsQueries);
		model.addAttribute("mpstoccrQueries", mpstoccrQueries);
		model.addAttribute("ccrQueries", ccrQueries);
		model.addAttribute("batchQueries", batchQueries);
		model.addAttribute("bdsQueries", bdsQueries);
		model.addAttribute("slidQueries", slidQueries);
		queriesList.addAll(omsQueries);
		queriesList.addAll(omstoccrQueries);
		queriesList.addAll(enablerQueries);
		queriesList.addAll(mpsQueries);
		queriesList.addAll(mpstoccrQueries);
		queriesList.addAll(ccrQueries);
		queriesList.addAll(batchQueries);
		queriesList.addAll(bdsQueries);
		queriesList.addAll(slidQueries);
		return queriesList;
	}
	
	public List<String> uversevalidation(DBValidationmodel dbmodel, Model model, 
			List<String> banList, String e2eQueries, String release) {
		logger.info("Uverse Validation Start ");	
		Set<String> queryList=null;	
		List<String> result = new ArrayList<>();
		try {
			if (e2eQueries.equals("allQueries")) {
				queryList=getUverseQueries(model);
				allconnection(release);
			}
			else{
				queryList=dvservices.uversewirelessselectedQueries(dbmodel.getQuery());
				individualconnection(dbmodel.getQuery(), release);
			}			
			result=validation(queryList, banList, release);
		} catch (Exception e) {
		}
		finally{
			ValidationDAOUtility.ClosingConnection();
		}
		return result;
	}

	private void allconnection(String release) {
		try {
			String[] CCR_Details = dbconnectionproperties.CCR_DB(release);
			ValidationDAOUtility.CCRConnection(CCR_Details);
			
			String[] MPS_Details = dbconnectionproperties.MPS_DB(release);
			ValidationDAOUtility.MPSConnection(MPS_Details);	
			
			omsdbconnection(release);				
			enablerdbconnection(release);	
			
		} catch (Exception e) {
		}	
	}

	private void individualconnection(String backends, String release) {

		if (backends.contains("CCR") || backends.contains("OMS_TO_CCR") || backends.contains("MPS_TO_CCR")){
			try {
				String[] CCR_Details = dbconnectionproperties.CCR_DB(release);
				ValidationDAOUtility.CCRConnection(CCR_Details);
			} catch (Exception e) {
			}				
		}
		if (backends.contains("MPS")) {
			try {
				String[] MPS_Details = dbconnectionproperties.MPS_DB(release);
				ValidationDAOUtility.MPSConnection(MPS_Details);
			} catch (Exception e) {
			}			
		}
		if (backends.contains("OMS")) {
			try {
				omsdbconnection(release);
			} 
			catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Data_Sheet Reading OMS Connection parameters: "+ e);
			}
		}
		if (backends.contains("ENABLER")) {
			try {
				enablerdbconnection(release);
			} catch (Exception e) {
			}			
		}
		
	
	}
	
	private void enablerdbconnection(String release) throws IOException {
		String[] EnbZ1_Details = dbconnectionproperties.EnbZ1_DB(release);
		ValidationDAOUtility.EZ1Connection(EnbZ1_Details);
		String[] EnbZ2_Details = dbconnectionproperties.EnbZ2_DB(release);
		ValidationDAOUtility.EZ2Connection(EnbZ2_Details);
		String[] EnbZ3_Details = dbconnectionproperties.EnbZ3_DB(release);
		ValidationDAOUtility.EZ3Connection(EnbZ3_Details);
	}

	private void omsdbconnection(String release) throws IOException {
		String OMS1_Details[] = dbconnectionproperties.OMS1_DB(release);
		ValidationDAOUtility.OMS1Connection(OMS1_Details);
		String[] OMS2_Details = dbconnectionproperties.OMS2_DB(release);
		ValidationDAOUtility.OMS2Connection(OMS2_Details);
		String[] OMS3_Details = dbconnectionproperties.OMS3_DB(release);
		ValidationDAOUtility.OMS3Connection(OMS3_Details);
	}
	
	public List<String> validation(Set<String> queryList, List<String> banList,  String release){
		List<String> resultlist = new LinkedList<>();
		for (String ban : banList) {
			validationQuery(ban, queryList, resultlist);
		}
		return resultlist;

	}
	
	public List<String> validationQuery(String key, Set<String> selectedQuery, List<String> resultList){	
		 try {
			 System.out.println("Inside uverse validationQuery");
			 for (String finalQuery : selectedQuery) {
				DataValidationEnum queryPropKey=getEnumFromString(finalQuery);
				String query=getEnumValueFromString(finalQuery);
				if (null==queryPropKey) {
					continue;
				}
					switch (queryPropKey) {
					case OMS_UVERSE_QUERY_OMS_CHECK_BAN_STATUS:
						System.out.println("OMS1");
						dvservices.omscheckbanstatus(key, resultList, query);	
						break;
						
					case OMS_UVERSE_QUERY_OMS_CHECK_VALIDATION_TBBAN:
						System.out.println("OMS2");
						dvservices.omscheckvalidationTBban(key, resultList, query);
						break;
						
					case OMS_UVERSE_QUERY_OMS_CHECK_ADDRESS:
						System.out.println("OMS3");
						dvservices.omscheckaddress(key,resultList, query);
						break;					
						
					case OMS_TO_CCR_UVERSE_QUERY_OMS_TO_CCR_REPLICATION:
						System.out.println("OMSCCR");
						dvservices.omsccrreplication(key, resultList, query);		
						break;
						
					case ENABLER_UVERSE_QUERY_ENABLER_CHECK_STATUS_PRODUCT:
						System.out.println("Enbaler1");
						dvservices.enablercheckstatusproduct(key, resultList, query);
						break;
						
					case ENABLER_UVERSE_QUERY_ENABLER_CHECK_CYCLE:
						System.out.println("Enbaler2");
						dvservices.enablercheckcycle(key, resultList, query);					
						break;
						
					case ENABLER_UVERSE_QUERY_ENABLER_CHECK_ACCOUNT_TYPE:
						System.out.println("Enbaler3");
						dvservices.enablercheckaccounttype(key,resultList, query);		
						break;
						
					case ENABLER_UVERSE_QUERY_ENABLER_CHECK_PLAN_AND_SOC:
						System.out.println("Enbaler4");
						dvservices.enablercheckplanandsoc(key,resultList, query);
						break;
						
					case ENABLER_UVERSE_QUERY_ENABLER_CHECK_TRB_ERRROS:
						System.out.println("Enbaler5");
						dvservices.enablercheckTRBerrors(key,resultList, query);		
						break;
						
					case ENABLER_UVERSE_QUERY_ENABLER_CHECK_USAGE:
						System.out.println("Enbaler6");
						dvservices.enablercheckusage(key,resultList, query);
						break;					
						
					case MPS_UVERSE_QUERY_MPS_CHECK_SERVICE_ID:
						System.out.println("MPS1");
						dvservices.mpsCheckServiceId(key, resultList, query);	
						break;
						
					case MPS_UVERSE_QUERY_MPS_CHECK_GHOST_ID_GENERATED:
						System.out.println("MPS2");
						dvservices.mpscheckGhostId(key, resultList, query);
						break;
						
					case MPS_TO_CCR_REP_UVERSE_QUERY_MPS_TO_CCR_CHECK_PARENT_MEMBERID:
						System.out.println("MPSCCR1");
						dvservices.mpsccrcheckParentMemberId(key, resultList, query);	
						break;
						
					case MPS_TO_CCR_REP_UVERSE_QUERY_MPS_TO_CCR_CHECK_MAIN_MEMBERID:
						System.out.println("MPSCCR2");
						dvservices.mpsccrcheckMainMemberId(key, resultList, query);
						break;
						
					case MPS_TO_CCR_REP_UVERSE_QUERY_MPS_TO_CCR_CHECK_NAME_AND_ADDRESS:
						System.out.println("MPSCCR3");
						dvservices.mpsccrcheckNameAddress(key,resultList, query);
						break;
						
					case CCR_UVERSE_QUERY_CCR_PKEY_FOR_WIRELESS_ID_MIGRATION:
						System.out.println("CCR1");
						dvservices.ccrCheckPkey(key,resultList, query);
						break;
						
					case CCR_UVERSE_QUERY_CCR_BAN_IN_CCR_ACCOUNT_WIRELINE_CON_TABLE:
						System.out.println("CCR2");
						dvservices.ccrCheckBan(key, resultList, query);		
						break;
						
					case CCR_UVERSE_QUERY_CCR_CHECK_VIDEO_BILL_IND:
						System.out.println("CCR3");
						dvservices.ccrCheckVideoBill(key, resultList, query);
						break;
						
					/*case CCR_UVERSE_QUERY_CCR_FIND_WLS_BAN_FOR_CB_UVERSE:
						System.out.println("CCR4");
						dvservices.ccrFindWlsBan(key, resultList, query);					
						break;*/
						
					case CCR_UVERSE_QUERY_CCR_CHECK_WLL_BAN_REGISTRATION:
						System.out.println("CCR5");
						dvservices.ccrCheckWllBanRegis(key,resultList, query);		
						break;
						
					case CCR_UVERSE_QUERY_CCR_CHECK_BRN_IND:
						System.out.println("CCR6");
						dvservices.ccrCheckBRNIndicator(key,resultList, query);
						break;
					
					case BATCH_UVERSE_QUERY_BATCH_CHECK_INDEXING_FOR_SA_UVERSE_WLL:
						System.out.println("Batch1");
						dvservices.batchcheckindexingforSAuverse(key, resultList, query);	
						break;
						
					case BATCH_UVERSE_QUERY_BATCH_CHECK_COMBINED_BILLING_FOR_CB_UVERSE:
						System.out.println("Batch2");
						dvservices.batchcheckcombinedbilling(key,resultList, query);
						break;
						
					case BATCH_UVERSE_QUERY_BATCH_HSIA_USAGE:
						System.out.println("Batch3");
						dvservices.batchCheckHSIAusage(key,resultList, query);
						break;
						
					case BATCH_UVERSE_QUERY_BATCH_WLL_DATA_USAGE:
						System.out.println("Batch4");
						dvservices.batchCheckWLLdatausage(key, resultList, query);		
						break;
						
					case BATCH_UVERSE_QUERY_BATCH_VOIP_USAGE:
						System.out.println("Batch5");
						dvservices.batchCheckVOIPusage(key, resultList, query);
						break;
						
					case BATCH_UVERSE_QUERY_BATCH_IPTV_USAGE:
						System.out.println("Batch6");
						dvservices.batchCheckIPTVusage(key, resultList, query);					
						break;
						
					/*case BDS_UVERSE_QUERY_BDS_CHECK_BILLING_ID:
						dvservices.ccrCheckWllBanRegis(key,resultList, query);		
						break;
						
					case BDS_UVERSE_QUERY_BDS_CHECK_BILL_PDF:
						dvservices.ccrCheckBRNIndicator(key,resultList, query);
						break;*/
						
					case SLID_UVERSE_QUERY_SLID_MPS_MEMBER_NAME_AND_ITS_ASSOCIATE_BANS:
						System.out.println("SLID1");
						dvservices.slidmpsmemberNameassociatesban(key,resultList, query);		
						break;
						
					case SLID_UVERSE_QUERY_SLID_CHECK_SLID_FLOWN_TO_CCR:
						System.out.println("SLID2");
						dvservices.slidcheckslidflowntoccr(key,resultList, query);
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
	
	
}
