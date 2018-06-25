package com.itko.lisa.ext.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itko.lisa.vse.stateful.model.ArgumentType;
import com.itko.lisa.vse.stateful.model.ArgumentType.Operator;
import com.itko.lisa.vse.stateful.model.Request;
import com.itko.lisa.vse.stateful.model.ServiceImage;
import com.itko.lisa.vse.stateful.model.ServiceImageCombiner;
import com.itko.lisa.vse.stateful.model.Transaction;
import com.itko.lisa.vse.stateful.model.TransactionNode;
import com.itko.lisa.vse.stateful.model.xml.ServiceImageContext;
import com.itko.util.Parameter;
import com.itko.util.ParameterList;

public class LdapSignatureTask {

	
	
	private static String [] key = new String [] {"arg3_STRING-ARRAY_STRING_0","arg3_STRING-ARRAY_STRING_1","arg3_STRING-ARRAY_STRING_2","arg3_STRING-ARRAY_STRING_3","arg3_STRING-ARRAY_STRING_4","arg3_STRING-ARRAY_STRING_5","arg3_STRING-ARRAY_STRING_6","arg3_STRING-ARRAY_STRING_7","arg3_STRING-ARRAY_STRING_8","arg3_STRING-ARRAY_STRING_9","arg3_STRING-ARRAY_STRING_10","arg3_STRING-ARRAY_STRING_11","arg3_STRING-ARRAY_STRING_12","arg3_STRING-ARRAY_STRING_13","arg3_STRING-ARRAY_STRING_14","arg3_STRING-ARRAY_STRING_15","arg3_STRING-ARRAY_STRING_16","arg3_STRING-ARRAY_STRING_17","arg3_STRING-ARRAY_STRING_18","arg3_STRING-ARRAY_STRING_19","arg3_STRING-ARRAY_STRING_20","arg3_STRING-ARRAY_STRING_21","arg3_STRING-ARRAY_STRING_22","arg3_STRING-ARRAY_STRING_23","arg3_STRING-ARRAY_STRING_24","arg3_STRING-ARRAY_STRING_25","arg3_STRING-ARRAY_STRING_26","arg3_STRING-ARRAY_STRING_27","arg3_STRING-ARRAY_STRING_28","arg3_STRING-ARRAY_STRING_29","arg3_STRING-ARRAY_STRING_30","arg3_STRING-ARRAY_STRING_31","arg3_STRING-ARRAY_STRING_32","arg3_STRING-ARRAY_STRING_33","arg3_STRING-ARRAY_STRING_34","arg3_STRING-ARRAY_STRING_35","arg3_STRING-ARRAY_STRING_36","arg3_STRING-ARRAY_STRING_37","arg3_STRING-ARRAY_STRING_38","arg3_STRING-ARRAY_STRING_39","arg3_STRING-ARRAY_STRING_40","arg3_STRING-ARRAY_STRING_41","arg3_STRING-ARRAY_STRING_42","arg3_STRING-ARRAY_STRING_43","arg3_STRING-ARRAY_STRING_44","arg3_STRING-ARRAY_STRING_45","arg3_STRING-ARRAY_STRING_46","arg3_STRING-ARRAY_STRING_47","arg3_STRING-ARRAY_STRING_48","arg3_STRING-ARRAY_STRING_49","arg3_STRING-ARRAY_STRING_50","arg3_STRING-ARRAY_STRING_51","arg3_STRING-ARRAY_STRING_52","arg3_STRING-ARRAY_STRING_53","arg3_STRING-ARRAY_STRING_54","arg3_STRING-ARRAY_STRING_55","arg3_STRING-ARRAY_STRING_56","arg3_STRING-ARRAY_STRING_57","arg3_STRING-ARRAY_STRING_58","arg3_STRING-ARRAY_STRING_59","arg3_STRING-ARRAY_STRING_60","arg3_STRING-ARRAY_STRING_61","arg3_STRING-ARRAY_STRING_62","arg3_STRING-ARRAY_STRING_63","arg3_STRING-ARRAY_STRING_64","arg3_STRING-ARRAY_STRING_65","arg3_STRING-ARRAY_STRING_66","arg3_STRING-ARRAY_STRING_67","arg3_STRING-ARRAY_STRING_68","arg3_STRING-ARRAY_STRING_69","arg3_STRING-ARRAY_STRING_70","arg3_STRING-ARRAY_STRING_71","arg3_STRING-ARRAY_STRING_72","arg3_STRING-ARRAY_STRING_73","arg3_STRING-ARRAY_STRING_74","arg4_BOOLEAN"};
	private static String [] value = new String [] {"cingMobileOLAMRegistrationDate","cingMobileMWWPwdChangeRequired","cingMobileOLAMIVUDTempPWDCreateTime","cingMobileIMEI","cingMobileOLAMEmailValidationFlag","cingDeviceBearerTechnology","cingMobileOLAMNewsletterSubscriptionFlag","cingDeviceManufacturer","cingMobileOLAMSoftLockedDateTime","cingMobileOLAMPrimaryUserIndicator","cingMobileOLAMGenderIndicator","cingMobileOLAMPreferredBrowseLanguage","cingMobileIMEIType","cingMobileOLAMRegisteredFlag","cingDeviceChangeDate","preferredLanguage","cingMobileOLAMForumAlias","cingMobileOLAMNewsletterDeliveryMethod","cingMobileOLAMIVUDVerifiedTime","cingMobileOLAMHeritageIndicator","cingMobileOLAMLockedFlag","cingMobileOLAMSmartLimitIndicator","cingMobileOLAMFailedAttemptCount","cingDeviceIdentifier","cingMobilePrepaidPlatform","cingMobileOLAMLastLoginTimeStamp","userPassword","mobile","cingAccountNumber","cingAccountMarketIndicator","cingAccountSubMarketIndicator","uid","preferredLanguage","entrydn","ou","dn","cingMobileOLAMRegIncent","OLAMPBOStatus","cingSubscriberStatus","cingMobileAcctMgtSys","attSQID1","attSecAns1","vencSecAns1","attSQID2","attSecAns2","vencSecAns2","subrRoles","cingMobileMAGID","cingMobileNumberTimeZone","cingMobileFeatureCode","sn","givenName","cingmobileCreationDate","LFPPsetNbr","LFPPflAutnCnt","LFPPlckOutExpDt","LFPPlstFlAutnDt","LSASsetNbr","LSASflAutnCnt","LSASlckOutExpDt","LSASlstFlAutnDt","LPRRsetNbr","LPRRflAutnCnt","LPRRlckOutExpDt","LPRRlstFlAutnDt","LCPsetNbr","LCPflAutnCnt","LCPlckOutExpDt","LCPlstFlAutnDt","anchrInd","mail","cingDeviceManufacturer","cingDeviceIdentifier","cingDeviceBearerTechnology","SIMNbr","false"};
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String vsiFilePath = "C://sltest//branches//b1304_P1_myATT_Validation_Merged//IST_Stubbing_1304//VServices//MERGED//JAVA//Images//";
		String vsiFileName = "LDAP_Merged";
		String vsiFile = vsiFilePath + vsiFileName +".vsi";
		ldapSignUpdate(vsiFile);
	}
	
	public static void ldapSignUpdate(String vsiFile) throws Exception{
		System.out.println("vsiFile to update"+vsiFile);
		ServiceImage si = ServiceImageContext.readServiceImage(vsiFile);		
		List<TransactionNode> stTxs = si.getStatelessTransactions();	
		if(findMatchedSignature(stTxs)){
			System.out.println("vsiFile have matched signature");
			for (TransactionNode tnode : stTxs) {
				Request metaReq = tnode.getRequest();
				String operation = metaReq.getOperation();
				if ("LDAPConnection.search".equalsIgnoreCase(operation)) {
					ParameterList oldParams = metaReq.getArguments();
					if (null != oldParams && (oldParams.size() == 81)) {
						updateRequest(metaReq);
						List<Transaction> spTxs = tnode.getSpecifics();
						for (Transaction txn : spTxs) {
							Request req = txn.getRequest();
							updateRequest(req);				
						}
						System.out.println("Createing temp VSI");
						ServiceImage newSi = tempararyVsi(si,tnode);
						ServiceImageCombiner sic = new ServiceImageCombiner(vsiFile, true);
						sic.combineWith(newSi);
						sic.setTargetFile(vsiFile);
						sic.save();						
					}					
				}	
			}			
		}else{
			System.out.println("vsiFile have no matched signature");
			for (TransactionNode tnode : stTxs) {
				Request metaReq = tnode.getRequest();
				updateRequest(metaReq);
				List<Transaction> spTxs = tnode.getSpecifics();
				for (Transaction txn : spTxs) {
					Request req = txn.getRequest();
					updateRequest(req);				
				}
			}
			ServiceImageContext.writeXML(vsiFile, si);
		}		
	}
	
	private static ServiceImage tempararyVsi(ServiceImage si,TransactionNode newTxn){
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
		newSi.addStatelessTransaction(newTxn);		
		return newSi;
		
	}
	
	
	private static boolean findMatchedSignature(List<TransactionNode> txnNodeList)throws Exception{
		for (TransactionNode tnode : txnNodeList) {
			Request metaReq = tnode.getRequest();
			ParameterList metaParams = metaReq.getArguments();
			if (null != metaParams && (metaParams.size() == 79)) {
				System.out.println("Match Transaction SignatureID"+tnode.getId());
				return true;
			}
		}
		return false;
	}
	
	
	private static void updateRequest(Request req){
		String operation = req.getOperation();
		if ("LDAPConnection.search".equalsIgnoreCase(operation)) {
			ParameterList oldParams = req.getArguments();
			if (null != oldParams && (oldParams.size() == 81)) {
				System.out.println("Request ID:" + req.getId());
				ParameterList newParams = createNewParameterList(req.getArguments());
				req.setArguments(newParams);
				req.ensureBodyIsPresent();
			}
		}
	}
	
	
	private static ParameterList createNewParameterList(ParameterList oldParams) {
		ParameterList newParams = new ParameterList();
		Parameter arg0_STRING = new Parameter("arg0_STRING",
				oldParams.get("arg0_STRING"));
		newParams.addParameter(arg0_STRING);
		Parameter arg1_INT = new Parameter("arg1_INT",
				oldParams.get("arg1_INT"));
		newParams.addParameter(arg1_INT);
		Parameter arg2_STRING = new Parameter("arg2_STRING",
				oldParams.get("arg2_STRING"));
		newParams.addParameter(arg2_STRING);
		for (int i = 0; i < key.length; i++) {
			Parameter param = new Parameter(key[i], value[i]);
			ArgumentType type = new ArgumentType();
			type.setOperator(Operator.ANY);
			param.setType(type);
			newParams.addParameter(param);
		}
		return newParams;
	}	

}
