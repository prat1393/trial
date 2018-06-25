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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.att.dbvalidation.dao.ValidationDAOUtility;
import com.att.dbvalidation.model.DBValidationmodel;
import com.att.dbvalidation.properties.DBConnectionProperties;
import com.att.dbvalidation.properties.DataValidationEnum;

@Service
public class UnifiedService {
	
	@Autowired
	DataValidationCommonServices dvservices;
	
	@Autowired
	DBConnectionProperties dbconnectionproperties;

	public Set<String> getUnifiedQueries(Model model) {
		Set<String> queriesList=new LinkedHashSet<String>();
		
		List<String> omsQueries=getBEListFromPropertis("OMS_UVERSE_QUERY_",1);
		List<String> omstoccrQueries=getBEListFromPropertis("OMS_TO_CCR_REP_UVERSE_QUERY_",1);
		List<String> enablerUverseQueries=getBEListFromPropertis("ENABLER_UVERSE_QUERY_",1);	
		List<String> enablerQueries=getBEListFromPropertis("ENABLER_UNIFIED_QUERY_",1);	
		enablerQueries.addAll(enablerUverseQueries);
		List<String> mpsQueries=getBEListFromPropertis("MPS_UVERSE_QUERY_",1);	
		List<String> mpstoccrQueries=getBEListFromPropertis("MPS_TO_CCR_REP_UVERSE_QUERY_",1);		
		List<String> ccrUverseQueries=getBEListFromPropertis("CCR_UVERSE_QUERY_",1);		
		List<String> ccrQueries=getBEListFromPropertis("CCR_UNIFIED_QUERY_",1);	
		ccrQueries.addAll(ccrUverseQueries);	
		List<String> batchQueries=getBEListFromPropertis("BATCH_UVERSE_QUERY_",1);
		List<String> bdsQueries=getBEListFromPropertis("BDS_UVERSE_QUERY_",1);	
		List<String> pcaQueries=getBEListFromPropertis("PCA_UNIFIED_QUERY_",1);
		List<String> converteddtvQueries=getBEListFromPropertis("CONVERTED_DTV_UNIFIED_QUERY_",1);	
		
		
		model.addAttribute("omsQueries", omsQueries);
		model.addAttribute("omstoccrQueries", omstoccrQueries);
		model.addAttribute("enablerQueries", enablerQueries);
		model.addAttribute("mpsQueries", mpsQueries);
		model.addAttribute("mpstoccrQueries", mpstoccrQueries);
		model.addAttribute("ccrQueries", ccrQueries);
		model.addAttribute("batchQueries", batchQueries);
		model.addAttribute("bdsQueries", bdsQueries);
		model.addAttribute("pcaQueries", pcaQueries);
		model.addAttribute("converteddtvQueries", converteddtvQueries);
		
		queriesList.addAll(omsQueries);
		queriesList.addAll(omstoccrQueries);
		queriesList.addAll(enablerQueries);
		queriesList.addAll(mpsQueries);
		queriesList.addAll(mpstoccrQueries);
		queriesList.addAll(ccrQueries);
		queriesList.addAll(batchQueries);
		queriesList.addAll(bdsQueries);
		queriesList.addAll(pcaQueries);
		queriesList.addAll(converteddtvQueries);
		return queriesList;
	}
	
	public List<String> unifiedvalidation(DBValidationmodel dbmodel, Model model, List<String> banList,
		String e2eQueries, String release) {	
		Set<String> queryList=null;
		List<String> result = new ArrayList<>();
		try {
			if (e2eQueries.equals("allQueries")) {
				queryList=getUnifiedQueries(model);
				allconnection(release);
			}
			else{
				queryList=dvservices.unifiedselectedQueries(dbmodel.getQuery());
				individualconnection(dbmodel.getQuery(), release);
			}
			System.out.println("queryList: "+queryList);
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
			System.out.println("CCR_Details: "+CCR_Details[0]+"---"+CCR_Details[1]+" --"+CCR_Details[2]);
			ValidationDAOUtility.CCRConnection(CCR_Details);
			
			String[] MPS_Details = dbconnectionproperties.MPS_DB(release);
			System.out.println("MPS_Details: "+MPS_Details[0]+"---"+MPS_Details[1]+" --"+MPS_Details[2]);
			ValidationDAOUtility.MPSConnection(MPS_Details);	
			
			omsdbconnection(release);	
			
			enablerdbconnection(release);	
			
		} catch (Exception e) {
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
			} catch (Exception e) {
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
	
	private List<String> validation(Set<String> queryList, List<String> banList, String release) {		
		List<String> resultlist = new LinkedList<>();
		for (String ban : banList) {
			String[] split=ban.split("-");
			String uverseBAN=split[0];
			String wlsBAN=split[1];
			String dTVBAN=split[2];
			System.out.println("uverseBAN: "+uverseBAN+" ---wlsBAN: "+wlsBAN+" --- dTVBAN: "+dTVBAN);
			validationQuery(uverseBAN,wlsBAN,dTVBAN, queryList, resultlist);
		} 
		return resultlist;
	}


	
	
	private void validationQuery(String key, String wlsBAN, String dTVBAN, Set<String> selectedQuery, List<String> resultList){	
		 try {
			 System.out.println("Inside unified validationQuery");
			 for (String finalQuery : selectedQuery) {
				DataValidationEnum queryPropKey=getEnumFromString(finalQuery);
				String query=getEnumValueFromString(finalQuery);
				if (null==queryPropKey) {
					continue;
				}
					switch (queryPropKey) {
					case OMS_UVERSE_QUERY_OMS_CHECK_BAN_STATUS:
						dvservices.omscheckbanstatus(key, resultList, query);	
						break;
						
					case OMS_UVERSE_QUERY_OMS_CHECK_VALIDATION_TBBAN:
						dvservices.omscheckvalidationTBban(key, resultList, query);
						break;
						
					case OMS_UVERSE_QUERY_OMS_CHECK_ADDRESS:
						dvservices.omscheckaddress(key,resultList, query);
						break;
						
					case OMS_TO_CCR_UVERSE_QUERY_OMS_TO_CCR_REPLICATION:
						dvservices.omsccrreplication(key, resultList, query);		
						break;
						
					case ENABLER_UVERSE_QUERY_ENABLER_CHECK_STATUS_PRODUCT:
						dvservices.enablercheckstatusproduct(key, resultList, query);
						break;
						
					case ENABLER_UVERSE_QUERY_ENABLER_CHECK_CYCLE:
						dvservices.enablercheckcycle(key, resultList, query);					
						break;
						
					case ENABLER_UVERSE_QUERY_ENABLER_CHECK_ACCOUNT_TYPE:
						dvservices.enablercheckaccounttype(key,resultList, query);		
						break;
						
					case ENABLER_UVERSE_QUERY_ENABLER_CHECK_PLAN_AND_SOC:
						dvservices.enablercheckplanandsoc(key,resultList, query);
						break;
						
					case ENABLER_UVERSE_QUERY_ENABLER_CHECK_TRB_ERRROS:
						dvservices.enablercheckTRBerrors(key,resultList, query);		
						break;
						
					case ENABLER_UVERSE_QUERY_ENABLER_CHECK_USAGE:
						dvservices.enablercheckusage(key,resultList, query);
						break;		
						
					case ENABLER_UNIFIED_QUERY_ENABLER_TO_GET_CONVERGED_INDICATOR:
						dvservices.enablerunifiedgetconvergedindicator(key, resultList, query);					
						break;
						
					case ENABLER_UNIFIED_QUERY_ENABLER_DTV_CHARGES:
						dvservices.enablerunifieddtvcharges(key,dTVBAN,resultList, query);		
						break;
						
					case ENABLER_UNIFIED_QUERY_ENABLER_SYNERGY_DTV_USAGE:
						dvservices.enablerunifiedsynergydtvusage(key,resultList, query);
						break;
					case ENABLER_UNIFIED_QUERY_ENABLER_CHECK_BILLED_UNBILLED_USAGE:
						dvservices.enablerunifiedbilledunbilledusage(key,resultList, query);		
						break;
						
					case ENABLER_UNIFIED_QUERY_ENABLER_BAN_ACTIVATION_DATE:
						dvservices.enablerunifiedbanactivationdate(key,resultList, query);
						break;	
						
					case MPS_UVERSE_QUERY_MPS_CHECK_SERVICE_ID:
						dvservices.mpsCheckServiceId(key, resultList, query);	
						break;
						
					case MPS_UVERSE_QUERY_MPS_CHECK_GHOST_ID_GENERATED:
						dvservices.mpscheckGhostId(key, resultList, query);
						break;
						
					case MPS_TO_CCR_REP_UVERSE_QUERY_MPS_TO_CCR_CHECK_PARENT_MEMBERID:
						dvservices.mpsccrcheckParentMemberId(key, resultList, query);
							
						break;
						
					case MPS_TO_CCR_REP_UVERSE_QUERY_MPS_TO_CCR_CHECK_MAIN_MEMBERID:
						dvservices.mpsccrcheckMainMemberId(key, resultList, query);
						break;
						
					case MPS_TO_CCR_REP_UVERSE_QUERY_MPS_TO_CCR_CHECK_NAME_AND_ADDRESS:
						dvservices.mpsccrcheckNameAddress(key,resultList, query);
						break;
						
					case CCR_UVERSE_QUERY_CCR_PKEY_FOR_WIRELESS_ID_MIGRATION:
						dvservices.ccrCheckPkey(key,resultList, query);
						break;
						
					case CCR_UVERSE_QUERY_CCR_BAN_IN_CCR_ACCOUNT_WIRELINE_CON_TABLE:
						dvservices.ccrCheckBan(key, resultList, query);		
						break;
						
					case CCR_UVERSE_QUERY_CCR_CHECK_VIDEO_BILL_IND:
						dvservices.ccrCheckVideoBill(key, resultList, query);
						break;
						
					/*case CCR_UVERSE_QUERY_CCR_FIND_WLS_BAN_FOR_CB_UVERSE:
						dvservices.ccrFindWlsBan(key, resultList, query);					
						break;*/
						
					case CCR_UVERSE_QUERY_CCR_CHECK_WLL_BAN_REGISTRATION:
						dvservices.ccrCheckWllBanRegis(key,resultList, query);		
						break;
						
					case CCR_UVERSE_QUERY_CCR_CHECK_BRN_IND:
						dvservices.ccrCheckBRNIndicator(key,resultList, query);
						break;
						
					case CCR_UNIFIED_QUERY_CCR_DTV_PACKAGE_DETAILS:
						dvservices.ccrunifiedCheckdtvpackage(key, dTVBAN, resultList, query);
						break;
						
					case CCR_UNIFIED_QUERY_CCR_DTV_EQUIPMENT_DETAILS:
						dvservices.ccrunifieddtvequipment(key, dTVBAN, resultList, query);					
						break;
						
					case CCR_UNIFIED_QUERY_CCR_TO_GET_BILLING_ADDRESS_AND_CUSTOMER_NAME_OF_SADTV_NONSYN:
						dvservices.ccrunifiedgetbillingaddress(key,dTVBAN, resultList, query);		
						break;
						
					case CCR_UNIFIED_QUERY_CCR_WLS_BAN_STATUS_AND_ACTIVATION_DATE:
						dvservices.ccrunifiedwlsbanstatusanddate(key,wlsBAN,resultList, query);
						break;
						
					case BATCH_UVERSE_QUERY_BATCH_CHECK_INDEXING_FOR_SA_UVERSE_WLL:
						dvservices.mpsCheckServiceId(key, resultList, query);	
						break;
						
					case BATCH_UVERSE_QUERY_BATCH_CHECK_COMBINED_BILLING_FOR_CB_UVERSE:
						dvservices.ccrCheckPkey(key,resultList, query);
						break;
						
					case BATCH_UVERSE_QUERY_BATCH_HSIA_USAGE:
						dvservices.batchCheckHSIAusage(key,resultList, query);
						break;
						
					case BATCH_UVERSE_QUERY_BATCH_WLL_DATA_USAGE:
						dvservices.batchCheckWLLdatausage(key, resultList, query);		
						break;
						
					case BATCH_UVERSE_QUERY_BATCH_VOIP_USAGE:
						dvservices.batchCheckVOIPusage(key, resultList, query);
						break;
						
					case BATCH_UVERSE_QUERY_BATCH_IPTV_USAGE:
						dvservices.batchCheckIPTVusage(key, resultList, query);					
						break;
						
					/*case BDS_UVERSE_QUERY_BDS_CHECK_BILLING_ID:
						dvservices.ccrCheckWllBanRegis(key,resultList, query);		
						break;
						
					case BDS_UVERSE_QUERY_BDS_CHECK_BILL_PDF:
						dvservices.ccrCheckBRNIndicator(key,resultList, query);
						break;*/
						
					case PCA_UNIFIED_QUERY_IN_CCR_LEGACY_DTV_BAN:
						dvservices.pcaccrlegacydtvban(key,dTVBAN,resultList, query);
						break;
						
					case PCA_UNIFIED_QUERY_UHV_ADDRESS_VALIDATION:
						dvservices.pcaUHVaddress(key, resultList, query);		
						break;
						
					case PCA_UNIFIED_QUERY_CCR_UVERSE_WLL_WLS:
						dvservices.pcaccruverseWLLWLS(key,wlsBAN,dTVBAN,resultList, query);
						break;
						
					case CONVERTED_DTV_UNIFIED_QUERY_CONVERTED_DTV_BILL_CYCLE_AND_LEGACY_DTV_ASSOCIATION:
						dvservices.converteddtvbillcycle(key, resultList, query);					
						break;
						
					case CONVERTED_DTV_UNIFIED_QUERY_CONVERTED_DTV_CRM_TO_CCR_REP:
						dvservices.converteddtvcrmtoccr(key,resultList, query);		
						break;
						
					case CONVERTED_DTV_UNIFIED_QUERY_CONVERTED_DTV_ENABLER_TO_CCR_REP:
						dvservices.converteddtvenablertoccr(key,resultList, query);
						break;
						
					default:
						break;
					}
				 }
			 
		 } 
		 
		catch (Exception e) {
			}
	}
	
	
}
