package com.att.dbvalidation.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.att.dbvalidation.dao.ValidationDAOUtility;

//import com.ibm.db2.jcc.a.s;

@Service
public class QueryProcessing {

	public ArrayList<String> chckbxCheckBanStatusinOMS(Connection con, String key, String sql) throws IOException{
		 ArrayList<String> list=new ArrayList<String>();
		 String status=null;
		 String action=null;
		 Date date=null;
		 String result=null;
		 try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					status=ValidationDAOUtility.res.getString("STATUS");
					action=ValidationDAOUtility.res.getString("ACTION_TYPE");
					date=ValidationDAOUtility.res.getDate("CTDB_CRE_DATETIME");
					if(status!=null && !status.isEmpty()){
						result=action+" / "+status+" / "+date;
						list.add(result);
					}
				}
			}catch (SQLException e) {
				//LoggerWrapper.logger.info("Exception :: OMS DB- BAN status: "+ e);
		}
		return list;
		}
		
		public int TBBanValidation_Address_OMS(Connection con, String key, String sql) throws IOException{
			int count=0;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					count=ValidationDAOUtility.res.getInt(1);
				}
			}catch (SQLException e) {
				//LoggerWrapper.logger.info("Exception :: OMS DB- TBBAN & Address Validation: "+ e);
		}
			return count;
		}
		
		public String checkCycleBanEnabler(Connection con, String key, String sql) throws IOException{
			String bill_cycle=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					bill_cycle=ValidationDAOUtility.res.getString("BILL_CYCLE");			
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Enabler DB- Bill Cycle: "+ e);
			}
			return bill_cycle;
		}
		public String checkAccountTypeEnabler(Connection con,String key, String sql) throws IOException{
			String acc_type=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
						acc_type=ValidationDAOUtility.res.getString("AR_ACCOUNT_SUB_TYPE");			
					}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Enabler DB- Account Type: "+ e);
			}
			return acc_type;
		}
		public String checkConvergeTypeEnabler(Connection con,String key, String sql) throws IOException{
			String convergeType=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					convergeType=ValidationDAOUtility.res.getString("L9_CONVERGE_TYPE");			
					}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Enabler DB- Converge Type: "+ e);
			}
			return convergeType;
		}
		public Integer checkDTVChargesQuery1Enabler(Connection con,String key, String sql) throws IOException{
			Integer count=0;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					count=ValidationDAOUtility.res.getInt(1);		
					}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Enabler DB- Converge Type: "+ e);
			}
			return count;
		}
		public ArrayList<String> checkDTVChargesQuery2Enabler(Connection con,String key, String sql) throws IOException{
			String chargeCode=null;
			String amount=null;
			String result=null;
			ArrayList<String> resultList=new ArrayList<String>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					chargeCode=ValidationDAOUtility.res.getString("CHARGE_CODE");	
					amount=ValidationDAOUtility.res.getString("AMOUNT");	
					if (chargeCode!=null) {
						result=chargeCode+" / "+ amount;
						resultList.add(result);
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Enabler DB- Converge Type: "+ e);
			}
			return resultList;
		}
		public String checkSynergyDTVUsageEnabler(Connection con,String key, String sql) throws IOException{
			String cycleInstance=null;
			String l9PPV=null;
			int usage=0;
			int usg=0;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.ps.setString(2, key);
				ValidationDAOUtility.ps.setString(3, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					cycleInstance=ValidationDAOUtility.res.getString("CYCLE_INSTANCE");	
					l9PPV=ValidationDAOUtility.res.getString("L9_PPV_DEL_METHOD");	
					usg=ValidationDAOUtility.res.getInt("L9_total_usage");
					usage+=usg;
				}
				if (cycleInstance!=null) {
					result=cycleInstance+" - "+usage+" - "+l9PPV;
				}
			}catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Enabler DB- Converge Type: "+ e);
			}
			return result;
		}
		public ArrayList<Date> checkActivationDateEnabler(Connection con,String key, String sql) throws IOException{
			Date date=null;
			ArrayList<Date> dateList=new ArrayList<Date>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					date=ValidationDAOUtility.res.getDate("EFFECTIVE_DATE");	
					if (date==null) {
						continue;
					}
					else{
						dateList.add(date);
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Enabler DB- Converge Type: "+ e);
			}
			return dateList;
		}
		public HashSet<Integer> chckbxCheckServiceId(Connection con,String key, String sql) throws IOException{
			int sId = 0;
			HashSet<Integer> set=new HashSet<>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					sId=ValidationDAOUtility.res.getInt("SERVICE_ID");
					set.add(sId);
				}
			}catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: MPS DB- CheckServiceId: "+ e);
			}
			return set;
		}
		public String chckbxCheckGhostId(Connection con,String key, String sql) throws IOException{
			String memberName= null;
			String activatedInd=null;
			String result = null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					memberName=ValidationDAOUtility.res.getString("MEMBER_NAME");
					activatedInd=ValidationDAOUtility.res.getString("activated_ind");
				}
				if (memberName!=null && !memberName.isEmpty()) {
					result=memberName+" / "+activatedInd;
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: MPS DB- CheckGhostId(MEMBER_NAME/ACTIVATED_IND): "+ e);
			}
			return result;
		}
		public ArrayList<String> checkParentMemberId(Connection con,String key, String sql) throws IOException{
			String parentMemId = null;
			ArrayList<String> parentMemIdList=new ArrayList<String>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					parentMemId=ValidationDAOUtility.res.getString("PARENTMEMBERID");
					if (parentMemId==null) {
						continue;
					}
					else{
						parentMemIdList.add(parentMemId);
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: MPS to CCR Replication(PARENT_MEMBERID): "+ e);
			}
			return parentMemIdList;
		}
		public int checkDomainId(Connection con,String key, String sql) throws IOException{
			int domainId=0;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					domainId=ValidationDAOUtility.res.getInt("DOMAIN_ID");
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: MPS to CCR Replication(MAIN_MEMBERID): "+ e);
			}
			return domainId;
		}
		public String checkMainMemberId(Connection con,String key, String sql) throws IOException{
			String memId=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					memId=ValidationDAOUtility.res.getString("MAIN_MEMBERID");
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: MPS to CCR Replication(MAIN_MEMBERID): "+ e);
			}
			return memId;
		}
		public String checkNameAddress(Connection con,String key, String sql) throws IOException{
			String name="";
			String zipCode="";
			String state="";
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
						name=ValidationDAOUtility.res.getString("FULLNAME");
						zipCode=ValidationDAOUtility.res.getString("ZIPCODE");	
						state=ValidationDAOUtility.res.getString("STATE");
						if (name==null ) {
							continue;
						}
						else
						{
							result=name+" / "+zipCode+" / "+state;
						}
					}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: MPS to CCR Replication(Name/Zipcode/STATE): "+ e);
			}
			return result;
		}
		public String chckbxPkeyForWireless(Connection con,String key, String sql) throws IOException{
			String pKey=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					pKey=ValidationDAOUtility.res.getString("EQUIFAX_PERSISTENT_KEY");
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- Persistent Key: "+ e);
			}
			return pKey;
		}
		public int chckbxCheckBanIn(Connection con,String key, String sql) throws IOException{
			int count=0;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res =ValidationDAOUtility. ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					count=ValidationDAOUtility.res.getInt(1);				
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- BAN in ccr.account_wireline_con: "+ e);
			}
			return count;
		}
		public String chckbxCheckVideoBill(Connection con,String key, String sql) throws IOException{
			String videoBill=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res =ValidationDAOUtility. ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					videoBill=ValidationDAOUtility.res.getString("video_bill_ind");				
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- Video Bill Indicator: "+ e);
			}
			return videoBill;
		}
		public String chckbxFindWlsBan(Connection con,String key, String sql) throws IOException{
			String acc=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res =ValidationDAOUtility. ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					acc=ValidationDAOUtility.res.getString("ACCTNUM");				
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB-Find Wls BAN for CB Uverse: "+ e);
			}
			return acc;
		}
		public ArrayList<Date> chckbxCheckWllBan(Connection con,String key, String sql) throws IOException{
			Date date=null;
			ArrayList<Date> dateList=new ArrayList<Date>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					date=ValidationDAOUtility.res.getDate("wdmtosdategmt");	
					if (date==null) {
						continue;
					}
					else{
						dateList.add(date);
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- WLL BAN Registration: "+ e);
			}
			return dateList;
		}
		public String chckbxCheckBrnInd(Connection con,String key, String sql) throws IOException{
			String indicator=null;
			String email=null;
			String type=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					indicator=ValidationDAOUtility.res.getString("BRNINDICATOR");
					email=ValidationDAOUtility.res.getString("BRNPRIMARYEMAIL");
					type=ValidationDAOUtility.res.getString("BRNNOTIFICATIONTYPE");
					if (indicator!=null) {
						result=indicator+" / "+email+" / "+type;
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- Check BRN Indicator(Indicator/Email/Type): "+ e);
			}
			return result;
		}
		public String chckbxDTVPackage(Connection con,String key, String sql) throws IOException{
			String categorytype=null;
			String svcname=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					categorytype=ValidationDAOUtility.res.getString("CATEGORY_TYPE");
					if (categorytype.equalsIgnoreCase("BasePkg")) {
						svcname=ValidationDAOUtility.res.getString("SVC_NAME");
						break;
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- Check DTV Package: "+ e);
			}
			return svcname;
		}
		public ArrayList<String> chckbxDTVEquipments(Connection con,String key, String sql) throws IOException{
			String attributeName=null;
			String attributeValue=null;
			ArrayList<String> attributeValueList=new ArrayList<String>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					attributeName=ValidationDAOUtility.res.getString("ATTRIBUTE_NAME");
					if (attributeName.equalsIgnoreCase("Receiver_type")) {
						attributeValue=ValidationDAOUtility.res.getString("ATTRIBUTE_VALUE");
						attributeValueList.add(attributeValue);
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- Check DTV Equipments: "+ e);
			}
			return attributeValueList;
		}
		public String chckbxBillingAddress(Connection con,String key, String sql) throws IOException{
			String stNum=null;
			String stName=null;
			String city=null;
			String stateProv=null;
			String zipcode=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					stNum=ValidationDAOUtility.res.getString("ST_Num");
					stName=ValidationDAOUtility.res.getString("ST_Name");
					city=ValidationDAOUtility.res.getString("City");
					stateProv=ValidationDAOUtility.res.getString("State_Prov");
					zipcode=ValidationDAOUtility.res.getString("Zip_CD").substring(0, 5);
					if (stNum!=null) {
						result=stNum+" / "+stName+" / "+city+" / "+stateProv+" / "+zipcode;
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- Billing Address): "+ e);
			}
			return result;
		}
		public ArrayList<String> chckbxWlsBanstatus(Connection con,String key, String sql) throws IOException{
			String status=null;
			Date date=null;
			String result=null;
			ArrayList<String> resultList=new ArrayList<String>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					status=ValidationDAOUtility.res.getString("BAN_STATUS");
					date=ValidationDAOUtility.res.getDate("START_SERVICE_DATE");
					if (status!=null) {
						result=status+" / "+ date.toString();
						resultList.add(result);
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- Check Wls Ban status: "+ e);
			}
			return resultList;
		}
		
		public String chckbxCheckCombinedBilling(Connection con,String key, String sql) throws IOException{
			String wirelessBAN=null;
			Date billEnd=null;
			Date wirelessEnd=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.ps.setString(2, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
					while(ValidationDAOUtility.res.next()){
						wirelessBAN=ValidationDAOUtility.res.getString("WIRELESS_BAN");
						billEnd=ValidationDAOUtility.res.getDate("BILL_END_DATE");
						wirelessEnd=ValidationDAOUtility.res.getDate("WIRELESS_BILL_END_DATE");	
						if (wirelessBAN!=null) {
							result=wirelessBAN+"/"+billEnd+"/"+wirelessEnd;
						}
					}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Batch DB- Combined Billing CB Uverse: "+ e);
			}
			return result;
		}
		public ArrayList<String> chckbxHsiaUsage(Connection con,String key, String sql) throws IOException{
			String serWirelessCombined=null;
			ArrayList<String> serWirelessCombinedList1=new ArrayList<String>();
			try{
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					serWirelessCombined=ValidationDAOUtility.res.getString("SERVSUM_WIRELESS_COMBINED");	
					if (serWirelessCombined==null) {
						continue;
					}
					serWirelessCombinedList1.add(serWirelessCombined);
				}
				}catch (Exception e) {
					 //LoggerWrapper.logger.info("Exception :: Batch DB- WLL DATA Usage: "+ e);
					}
			return serWirelessCombinedList1;
		}
		public String chckbxHsiaUsage1(Connection con,String key, String sql) throws IOException{	
			String serviceName=null;
			String incUnits=null;
			String usedUnits=null;
			String result=null;
			try{
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					serviceName=ValidationDAOUtility.res.getString("SERVICE_NAME");
					incUnits=ValidationDAOUtility.res.getString("INCLUDED_UNITS");
					usedUnits=ValidationDAOUtility.res.getString("USED_UNITS");
					if (serviceName!=null) {
						result=serviceName+"/"+incUnits+"/"+usedUnits;
					}
				}
			}
			catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Batch DB- HSIA Usage: "+ e);
			}
			return result;
		}
		public ArrayList<String> chckbxWllDataUsage(Connection con,String key, String sql) throws IOException{
			String serWLLInternet=null;
			ArrayList<String> serWLLInternetList=new ArrayList<String>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					serWLLInternet=ValidationDAOUtility.res.getString("SERVSUM_WLL_INTERNET");	
					if (serWLLInternet==null) {
						continue;
					}
					serWLLInternetList.add(serWLLInternet);
					}
			}
			catch (Exception e) {
				 //LoggerWrapper.logger.info("Exception :: Batch DB- WLL DATA Usage: "+ e);
			}
			return serWLLInternetList;
		}
		public String chckbxWllDataUsage1(Connection con,String key, String sql) throws IOException{
				String serviceName=null;
				String incUnits=null;
				String usedUnits=null;
				String result=null;
				try{
					ValidationDAOUtility.ps=con.prepareStatement(sql);
					ValidationDAOUtility.ps.setString(1, key);
					ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
					while(ValidationDAOUtility.res.next()){
						serviceName=ValidationDAOUtility.res.getString("SERVICE_NAME");
						incUnits=ValidationDAOUtility.res.getString("INCLUDED_UNITS");
						usedUnits=ValidationDAOUtility.res.getString("USED_UNITS");
						if (serviceName!=null) {
							result=serviceName+"/"+incUnits+"/"+usedUnits;
						}
					}
				}
				 catch (Exception e) {
					 //LoggerWrapper.logger.info("Exception :: Batch DB- Voip Usage: "+ e);
					}
				return result;
			}
		public HashMap<Integer, String> chckbxVoipIptvUsage(Connection con,String key, String sql) throws IOException{
			int idx=0;
			HashMap<Integer, String> voip=new HashMap<>();
			Date billStart=null;
			Date billEnd=null;
			try{
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					String test=null;
					idx=ValidationDAOUtility.res.getInt("IDX_VOL_NUM");
					billStart=ValidationDAOUtility.res.getDate("BILL_START_DATE");
					billEnd=ValidationDAOUtility.res.getDate("BILL_END_DATE");
					test=billStart+" & "+billEnd;
					voip.put(idx, test);
				}
			}catch (Exception e) {
				 //LoggerWrapper.logger.info("Exception :: Batch DB- Voip Usage: "+ e);
				}
			return voip;
		}
		
		public String chckbxBillingId(Connection con,String key, String sql) throws IOException{
			String billingId=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					billingId=ValidationDAOUtility.res.getString("Billing_ID");
				}
			} catch (Exception e) {
				 //LoggerWrapper.logger.info("Exception :: BDS DB- Billing_id: "+ e+" " +e.getMessage());
			}
			return billingId;
		}
		public ArrayList<Date> chckbxCheckPdf(Connection con,String key, String sql) throws IOException{
			Date date=null;
			ArrayList<Date> dateList=new ArrayList<Date>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					date=ValidationDAOUtility.res.getDate("bill_stmt_dt");
					dateList.add(date);
					}
				} catch (Exception e) {
				 //LoggerWrapper.logger.info("Exception :: BDS DB- Check Bill PDF: "+ e.getMessage());
				}
			return dateList;
		}
		
		public ArrayList<String> slidMemberNameList(Connection con,String key, String sql) throws IOException{
			String memberName=null;
			ArrayList<String> memberNameList= new ArrayList<String>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					memberName=ValidationDAOUtility.res.getString("MEMBER_NAME");
					if (memberName!=null) {
						if (memberName.contains("@att.net")) {
							continue;
						}
						else{
						memberNameList.add(memberName);
						}
					}
					}
			} catch (Exception e) {
				 //LoggerWrapper.logger.info("Exception :: SLID(In MPS):  "+ e);
				}
			return memberNameList;
		}
		public ArrayList<String> slidInMPS(Connection con,String key, String sql, String str) throws IOException{
			String s=null;
			ArrayList<String> sList= new ArrayList<String>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, str);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					s=ValidationDAOUtility.res.getString("SOR_INVARIANT_ID");
					if (s!=null) {
						sList.add(s);
					}
					}
			}catch (Exception e) {
				 //LoggerWrapper.logger.info("Exception :: SLID(In MPS):  "+ e);
				}
			return sList;
		}
		public Integer slidInCCR(Connection con,String key, String sql, String str) throws IOException{
			int count=0;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, str);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					count=ValidationDAOUtility.res.getInt(1);
					}
			}catch (Exception e) {
				 //LoggerWrapper.logger.info("Exception :: SLID(In MPS):  "+ e);
				}
			return count;
		}
		
		public String checkInCcrLegacy(Connection con,String key, String sql) throws IOException{
			String num=null;
			String stat=null;
			String bal=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					num=ValidationDAOUtility.res.getString("ACCT_NUM");	
					stat=ValidationDAOUtility.res.getString("ACCT_STAT");	
					bal=ValidationDAOUtility.res.getString("ACCT_BAL");	
					if (num!=null) {
						result=num+" / "+stat+" / "+bal;
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: DTV Enabler to CCR replication: "+ e);
			}
			return result;
		}
		public String checkPCAValidationForUverseWlsLegacyDtvBan(Connection con,String key, String sql) throws IOException{
			String afltCo=null;
			String afltType=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					afltCo=ValidationDAOUtility.res.getString("aflt_co_cd");	
					afltType=ValidationDAOUtility.res.getString("aflt_type_cd");
					if (afltCo!=null) {
						result=afltCo+" / "+afltType;
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: DTV Enabler to CCR replication: "+ e);
			}
			return result;
		}
		public String checkUhvAddressValidation(Connection con,String key, String sql) throws IOException{
			String addr=null;
			String city=null;
			String state=null;
			String zip=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					addr=ValidationDAOUtility.res.getString("HSHLD_STR_ADDR_TXT");	
					city=ValidationDAOUtility.res.getString("HSHLD_CTY_NM");	
					state=ValidationDAOUtility.res.getString("HSHLD_ST_CD");	
					zip=ValidationDAOUtility.res.getString("HSHLD_ZIP_CD");
					if (addr!=null) {
						result=addr+" / "+city+" / "+state+" / "+zip;
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: DTV Enabler to CCR replication: "+ e);
			}
			return result;
		}
		public String checkLegacyDTVAssosiationEnabler(Connection con,String key, String sql) throws IOException{
			String bill_cycle=null;
			String dtvBanId=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					bill_cycle=ValidationDAOUtility.res.getString("BILL_CYCLE");	
					dtvBanId=ValidationDAOUtility.res.getString("L9_DTV_Legacy_Ban_id");	
					if (dtvBanId!=null || bill_cycle!=null) {
						result=bill_cycle+" / "+dtvBanId;
					}
					
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Enabler DB- Bill Cycle: "+ e);
			}
			return result;
		}
		public String checkDTVCRMtoCCRreplication(Connection con,String key, String sql) throws IOException{
			String ban=null;
			String directv_acct_id=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					ban=ValidationDAOUtility.res.getString("ban");	
					directv_acct_id=ValidationDAOUtility.res.getString("directv_acct_id");	
					if (ban!=null) {
						result=ban+" / "+directv_acct_id;
					}
					
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception ::  DTV CRM to CCR replication : "+ e);
			}
			return result;
		}
		public String checkDTVEnablertoCCRreplication(Connection con,String key, String sql) throws IOException{
			String currentbalancedue=null;
			String pastduebalance=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					currentbalancedue=ValidationDAOUtility.res.getString("currentbalancedue");	
					pastduebalance=ValidationDAOUtility.res.getString("pastduebalance");	
					if (currentbalancedue!=null) {
						result=currentbalancedue+" / "+pastduebalance;
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: DTV Enabler to CCR replication: "+ e);
			}
			return result;
		}
		
		public ArrayList<String> retrieveBANfromCTN(Connection con, List<String> list, String sql, String sql1) throws IOException{
			ArrayList<String> resultList=new ArrayList<>();
			
			for (String banId : list) {
				String ctn=null;
				String result=null;
				String marketPlace=null;
				StringBuilder sf=new StringBuilder();
				try {
					ValidationDAOUtility.ps=con.prepareStatement(sql1);
					ValidationDAOUtility.ps.setString(1, banId);
					ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
					while(ValidationDAOUtility.res.next()){
						marketPlace=ValidationDAOUtility.res.getString("SUBRMKT");
					}
					
					ValidationDAOUtility.ps=con.prepareStatement(sql);
					ValidationDAOUtility.ps.setString(1, banId);
					ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
					while(ValidationDAOUtility.res.next()){
						ctn=ValidationDAOUtility.res.getString("SUBRNUM");
						sf.append(ctn).append("/");
					}
					} catch (Exception e) {
						
					}
				if (sf.length()==0) {
					sf=null;
					
				}
				result=banId+"-"+marketPlace+"-"+sf;		
				result=result.replaceAll(",$", "");			
				resultList.add(result);
			}
			return resultList;
		}
		//CCR
		/*public ArrayList<String> chckCTN(Connection con, String key, String sql) throws IOException{
			String subscriberNumber=null; 
			ArrayList<String> listOfsubscriberNumber=new ArrayList<String>();
			try {
				Validation_DAO_Utility.ps=con.prepareStatement(sql);
				//Array array=Validation_DAO_Utility.ps.getConnection().createArrayOf("VARCHAR", new Object[]{"2142081000","2142081015"});
				//Array array=Validation_DAO_Utility.ps.getConnection().createArrayOf(arg0, arg1)
				
				System.out.println("Key: "+key);
				System.out.println("SQL: "+sql);
				Validation_DAO_Utility.ps.setString(1, key);
				//Validation_DAO_Utility.ps.setArray(1, array);
				System.out.println("ABC");
				Validation_DAO_Utility.res = Validation_DAO_Utility.ps.executeQuery();
				System.out.println("ABCD");
				while(Validation_DAO_Utility.res.next()){
					System.out.println("Success");
					subscriberNumber=Validation_DAO_Utility.res.getString("subscriber_no");	
					listOfsubscriberNumber.add(subscriberNumber);
				}
				System.out.println("After Success");
			} catch (Exception e) {
				e.printStackTrace();
				////LoggerWrapper.logger.info("Exception :: Enabler DB- Bill Cycle: "+ e);
			}
			return listOfsubscriberNumber;
		}*/
		public String chckCTN(Connection con, String key, String sql) throws IOException{
			String subscriberNumber=null; 
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					subscriberNumber=ValidationDAOUtility.res.getString("subscriber_no");	
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- Check CTN: "+ e);
			}
			return subscriberNumber;
		}
		public String chckbxBAN(Connection con, String key, String sql) throws IOException{
			String bill_cycle=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					bill_cycle=ValidationDAOUtility.res.getString("BILL_CYCLE");			
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- Check BAN: "+ e);
			}
			return bill_cycle;
		}
		public String chckbxFindsubscribersSMS(Connection con, String key, String sql) throws IOException{
			String subrnum=null;
			String tnk=null;
			String subrmagid=null;
			String subrimsi=null;
			String subrdevimeiserialnum=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					subrnum=ValidationDAOUtility.res.getString("SUBRNUM");		
					tnk=ValidationDAOUtility.res.getString("'TNK'");	
					subrmagid=ValidationDAOUtility.res.getString("SUBEMAGID");	
					subrimsi=ValidationDAOUtility.res.getString("SUBRIMSI");	
					subrdevimeiserialnum=ValidationDAOUtility.res.getString("SUBRDEVIMEISERIALNUM");	
					if (subrnum!=null) {
						result=subrnum+"-"+tnk+"-"+subrmagid+"-"+subrimsi+"-"+subrdevimeiserialnum;
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- FindsubscribersSMS: "+ e);
			}
			return result;
		}
		public String findTuid(Connection con, String key, String sql) throws IOException{
			String tuid=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					tuid=ValidationDAOUtility.res.getString("TUID");			
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- Find Tuid: "+ e);
			}
			return tuid;
		}
		public String chckbxCheckSlidMemberId(Connection con, String key, String sql) throws IOException{
			String firstname=null;
			String lastname=null;
			String parentmemberid=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					firstname=ValidationDAOUtility.res.getString("FIRSTNAME");			
					lastname=ValidationDAOUtility.res.getString("LASTNAME");	
					parentmemberid=ValidationDAOUtility.res.getString("PARENTMEMBERID");	
					if (firstname!=null) {
						result=firstname+"-"+lastname+"-"+parentmemberid;
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- Check Slid Member Id: "+ e);
			}
			return result;
		}
		public String chckbxAccountlinkedPlentiID(Connection con, String key, String sql) throws IOException{
			String sorcode=null;
			String loyalityId=null;
			String loyalityProgram=null;
			String assoc=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					sorcode=ValidationDAOUtility.res.getString("SORCODE");
					loyalityId=ValidationDAOUtility.res.getString("LOYALITY_PROGRAM_ID");
					loyalityProgram=ValidationDAOUtility.res.getString("LOYALITY_PROGRAM");
					assoc=ValidationDAOUtility.res.getString("ASSOC_MADE_BY");
					if (sorcode!=null) {
						result=sorcode+"-"+loyalityId+"-"+loyalityProgram+"-"+assoc;
					}					
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: CCR DB- Account linked Plenti ID: "+ e);
			}
			return result;
		}		
		//MPS
		public String chckbxcBANflown(Connection con, String key, String sql) throws IOException{
			String mNum=null;
			String mName=null;
			String dId=null;
			String gNum=null;
			String sId=null;
			String accType=null;
			String sorInvariantId=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					mNum=ValidationDAOUtility.res.getString("MEMBER_NUM");		
					mName=ValidationDAOUtility.res.getString("MEMBER_NAME");	
					dId=ValidationDAOUtility.res.getString("DOMAIN_ID");	
					gNum=ValidationDAOUtility.res.getString("GROUP_NUM");	
					sId=ValidationDAOUtility.res.getString("SOR_ID");	
					accType=ValidationDAOUtility.res.getString("ACCOUNT_TYPE");	
					sorInvariantId=ValidationDAOUtility.res.getString("SOR_INVARIANT_ID");
					if (mNum!=null) {
						result=mNum+"-"+mName+"-"+dId+"-"+gNum+"-"+sId+"-"+accType+"-"+sorInvariantId;
					}
					
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: MPS DB- BAN flown: "+ e);
			}
			return result;
		}
		public String chckbxCheckSlidFlownMPS(Connection con, String key, String sql) throws IOException{
			String memName=null;
			String memNum=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					memName=ValidationDAOUtility.res.getString("MEMBER_NAME");		
					memNum=ValidationDAOUtility.res.getString("MEMBER_NUM");	
					if (memNum!=null) {
						result=memName+"-"+memNum;
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: MPS DB- Slid Flown: "+ e);
			}
			return result;
		}
		public String chckbxAccountLinkedPlentiIDMPS(Connection con, String key, String sql) throws IOException{
			String sorId=null;
			String sorInvariantId=null;
			String loyalityId=null;
			String assoc=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					sorId=ValidationDAOUtility.res.getString("SOR_ID");	
					sorInvariantId=ValidationDAOUtility.res.getString("SOR_INVARIANT_ID");
					loyalityId=ValidationDAOUtility.res.getString("LOYALITY_PROGRAM_ID");		
					assoc=ValidationDAOUtility.res.getString("ASSOC_MADE_BY");	
					if (sorId!=null) {
						result=sorId+"-"+sorInvariantId+"-"+loyalityId+"-"+assoc;
					}					
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: MPS DB- Account Linked Plenti ID: "+ e);
			}
			return result;
		}
		public String findCardNum(Connection con, String key, String sql) throws IOException{
			String cardNum=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					cardNum=ValidationDAOUtility.res.getString("LOYALITY_CARD_NUM");
					}					
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: MPS DB- Card Num: "+ e);
			}
			return cardNum;
		}
		public String findSlidMPS(Connection con, String key, String sql) throws IOException{
			String memName=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					memName=ValidationDAOUtility.res.getString("MEMBER_NAME");
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: MPS DB- Slid Flown: "+ e);
			}
			return memName;
		}
		public String chckbxDesignatedMainCTN(Connection con, String key, String sql) throws IOException{
			String mainCTN=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					mainCTN=ValidationDAOUtility.res.getString("Main_CTN");			
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: MPS DB- Check Designated Main CTN: "+ e);
			}
			return mainCTN;
		}
		
		//Enabler
		public ArrayList<String> chckbxCTNflownEnabler(Connection con, String key, String sql) throws IOException{
			 ArrayList<String> list=new ArrayList<String>();
			 String soc=null;
			 String status=null;
			 Date effectiveDate=null;
			 Date expirationDate=null;
			 String result=null;
			 try {
					ValidationDAOUtility.ps=con.prepareStatement(sql);
					ValidationDAOUtility.ps.setString(1, key);
					ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
					while(ValidationDAOUtility.res.next()){
						soc=ValidationDAOUtility.res.getString("TLG_SOC");
						status=ValidationDAOUtility.res.getString("SOC_STATUS");
						effectiveDate=ValidationDAOUtility.res.getDate("EFFECTIVE_DATE");
						expirationDate=ValidationDAOUtility.res.getDate("EXPIRATION_DATE");
						if(status!=null && !status.isEmpty()){
							result=soc+" / "+status+" / "+effectiveDate+" / "+expirationDate;
							list.add(result);
						}
					}
				}catch (SQLException e) {
					//LoggerWrapper.logger.info("Exception :: Enabler DB- CTN flown: "+ e);
				}
			return list;
		}
		
		public ArrayList<String> chckbxDataUsagesEnabler(Connection con, String key, String sql) throws IOException{
			ArrayList<String> list =new ArrayList<String>();
			String l9_external_subs_num=null;			
			Date start_time=null;
			String l9_session_id=null;
			String L9_CPW_BILL_CODE=null;
			String L9_CHARGE_AMOUNT=null;
			String L9_external_cust_id=null;
			String L9_group_name=null;
			String L9_offer_id=null;
			String L9_quality_of_service=null;
			String L9_RATE_PRICING_ITEM_NAME=null;
			String L9_ONSTAR_RATING_GROUP=null;
			String result=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){					
					l9_external_subs_num=ValidationDAOUtility.res.getString("l9_external_subs_num");	
					start_time=ValidationDAOUtility.res.getDate("start_time");
					l9_session_id=ValidationDAOUtility.res.getString("l9_session_id");
					L9_CPW_BILL_CODE=ValidationDAOUtility.res.getString("L9_CPW_BILL_CODE");
					L9_CHARGE_AMOUNT=ValidationDAOUtility.res.getString("L9_CHARGE_AMOUNT");					
					L9_external_cust_id=ValidationDAOUtility.res.getString("L9_external_cust_id");
					L9_group_name=ValidationDAOUtility.res.getString("L9_group_name");					
					L9_offer_id=ValidationDAOUtility.res.getString("L9_offer_id");
					L9_quality_of_service=ValidationDAOUtility.res.getString("L9_quality_of_service");
					L9_RATE_PRICING_ITEM_NAME=ValidationDAOUtility.res.getString("L9_RATE_PRICING_ITEM_NAME");
					L9_ONSTAR_RATING_GROUP=ValidationDAOUtility.res.getString("L9_ONSTAR_RATING_GROUP");
					
					result=l9_external_subs_num+"/"+start_time+"/"+l9_session_id+"/"+L9_CPW_BILL_CODE+"/"+L9_CHARGE_AMOUNT+"/"
							+L9_external_cust_id+"/"+L9_group_name+"/"+L9_offer_id+"/"+L9_quality_of_service+"/"+
							L9_RATE_PRICING_ITEM_NAME+"/"+L9_ONSTAR_RATING_GROUP;
					
					list.add(result);
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Enabler DB- Data Usages: "+ e);
			}
			return list;
		}
		public String chckbxGetImsiValue(Connection con, String key, String sql) throws IOException{
			String imsi=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					imsi=ValidationDAOUtility.res.getString("IMSI");			
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Enabler DB- Get Imsi Value: "+ e);
			}
			return imsi;
		}
		//TLG
		public ArrayList<String> chckbxGetSequenceNoTLG(Connection con, String ban, String ctn,String sql) throws IOException{
			String sequenceNum=null;
			ArrayList<String> result=new ArrayList<String>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, ban);
				ValidationDAOUtility.ps.setString(2, ctn);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					sequenceNum=ValidationDAOUtility.res.getString("SEQUENCE_NUM");	
					if (sequenceNum!=null && !sequenceNum.isEmpty()) {
						result.add(sequenceNum);						
					}
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: TLG DB- Get Sequence No: "+ e);
			}
			return result;
		}
		public List<String> chckbxFileSequenceIPusageTLG(Connection con, String key, String sql) throws IOException{
			String ctn=null;
			String imei=null;
			String imei_1=null;
			String result=null;
			List<String> resultList=new ArrayList<String>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					ctn=ValidationDAOUtility.res.getString("CTN");		
					imei=ValidationDAOUtility.res.getString("IMEI");	
					imei_1=ValidationDAOUtility.res.getString("IMEI_1");	
					result=ctn+"-"+imei+"-"+imei_1;
					resultList.add(result);
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: TLG DB- File Sequence IP usage: "+ e);
			}
			return resultList;
		}
		public String chckbxCheckSocsTLG(Connection con, String key, String sql) throws IOException{
			String bill_cycle=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					bill_cycle=ValidationDAOUtility.res.getString("BILL_CYCLE");			
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: TLG DB- Check Socs: "+ e);
			}
			return bill_cycle;
		}
		public List<String> chckbxDeviceAddedCTNTLG(Connection con, String key, String sql) throws IOException{
			String ctn=null;
			String imei=null;
			String imei_1=null;
			String result=null;
			List<String> resultList=new ArrayList<String>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				//System.out.println("Key: "+key+" ---------- "+sql);
				while(ValidationDAOUtility.res.next()){
					ctn=ValidationDAOUtility.res.getString("CTN");		
					imei=ValidationDAOUtility.res.getString("IMEI");	
					imei_1=ValidationDAOUtility.res.getString("IMEI_1");
					result=ctn+"-"+imei+"-"+imei_1;
					resultList.add(result);
				}
			} catch (Exception e) {
				e.printStackTrace();
				//LoggerWrapper.logger.info("Exception :: TLG DB- Device Added CTN: "+ e);
			}
			return resultList;
		}
		public String chckbxGetOldBansTLG(Connection con, String key, String sql) throws IOException{
			String bill_cycle=null;
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					bill_cycle=ValidationDAOUtility.res.getString("BILL_CYCLE");			
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: TLG DB- Get Old Bans: "+ e);
			}
			return bill_cycle;
		}
		public List<String> chckbxCheckEmailEffecttivity(Connection con, String key, String sql) throws IOException{
			String email=null;
			Date date=null;
			String result=null;
			List<String> resultList=new ArrayList<String>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					email=ValidationDAOUtility.res.getString("EMAIl_ID");	
					date=ValidationDAOUtility.res.getDate("EMAIL_EFF_DATE");
					if (!email.isEmpty()) {
						result=email+"/"+date;
						resultList.add(result);
					}					
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: TLG DB- Check Email Effecttivity: "+ e);
			}
			return resultList;
		}
		
		public List<String> checkBatchUsageWireless(Connection con, String key, String sql) throws IOException{
			String ser=null;
			List<String> resultList=new ArrayList<String>();
			try {
				ValidationDAOUtility.ps=con.prepareStatement(sql);
				ValidationDAOUtility.ps.setString(1, key);
				ValidationDAOUtility.res = ValidationDAOUtility.ps.executeQuery();
				while(ValidationDAOUtility.res.next()){
					ser=ValidationDAOUtility.res.getString("SERVSUM_WIRELESS");	
					if (ser!=null) {
						resultList.add(ser);
					}					
				}
			} catch (Exception e) {
				//LoggerWrapper.logger.info("Exception :: Batch DB- Check Usage Wireless: "+ e);
			}
			return resultList;
		}
		
}
	